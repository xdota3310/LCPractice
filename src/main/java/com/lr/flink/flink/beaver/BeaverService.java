package com.lr.flink.flink.beaver;


import cn.yottabyte.beaver.action.search.ShardsSearchRequestBuilder;
import cn.yottabyte.beaver.client.BeaverClient;
import cn.yottabyte.beaver.cluster.SearchRouting;
import cn.yottabyte.beaver.cluster.SearchShardsRequest;
import cn.yottabyte.beaver.cluster.SearchShardsResponse;
import cn.yottabyte.beaver.cluster.ShardRouting;
import cn.yottabyte.beaver.grpc.GetIndicesRequest;
import cn.yottabyte.beaver.grpc.GetIndicesResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class BeaverService {
    private static final Logger logger = LoggerFactory.getLogger(BeaverService.class);
    private static final int GET_INDICES_TIMEOUT = 30000;
    private static final int GET_ROUTING_TIMEOUT = 30000;
    private final BeaverClient client;

    public BeaverService(BeaverClient client) {
        this.client = client;
    }

    public BeaverService(BeaverConfig beaverConfig) {
        this(makeBeaverClient(beaverConfig));
    }

    public BeaverScrollQuery executeScroll(BeaverScrollRequestBuilder requestBuilder,
                                           TimeValue keepAlive,
                                           TimeValue timeout) {
        ShardsSearchRequestBuilder searchRequestBuilder = requestBuilder.build();
        return new BeaverScrollQuery(client, searchRequestBuilder, keepAlive, timeout.getMillis());
    }

    public Set<String> getIndices() {
        GetIndicesRequest request = GetIndicesRequest.newBuilder().build();
        Set<String> indices = new HashSet<>();
        GetIndicesResponse response = null;
        try {
            response = client.getIndices(request).get(GET_INDICES_TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        int count = response.getIndexCount();
        for (int i = 0; i < count; i++) {
            indices.add(response.getIndex(i));
        }
        return indices;
    }

    public BeaverClient getClient() {
        return client;
    }

    public void close() {
        if (client != null) {
            try {
                client.shutdown();
            } catch (Exception e) {
                logger.error("shut down beaver client error", e);
            }
        }
    }

    public List<PartitionDef> listPartitionDef(Set<String> indices) {
        SearchShardsRequest request = new SearchShardsRequest(new ArrayList<>(indices));
        SearchShardsResponse response = null;
        try {
            response = client.searchShardsFuture(request).get(GET_ROUTING_TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Map<String, SearchRouting> index2Routing = response.getIndexToSearchRouting();
        Map<BeaverShardKey, BeaverShard.Builder> partitionDefMap = new HashMap<>();
        for (Map.Entry<String, SearchRouting> entry : index2Routing.entrySet()) {
            String index = entry.getKey();
            SearchRouting searchRouting = entry.getValue();
            for (ShardRouting shardRouting : searchRouting.getShardRoutings()) {
                BeaverShardCopy shardCopy = BeaverShardCopy.createWithShardRouting(shardRouting);
                BeaverShard.Builder builder = partitionDefMap.get(shardCopy.getShardKey());
                if (builder == null) {
                    builder = BeaverShard.newBuilder().setShardKey(shardCopy.getShardKey());
                    partitionDefMap.put(shardCopy.getShardKey(), builder);
                }
                builder.addShardCopy(shardCopy);
            }
        }

        List<PartitionDef> partitionDefs = new ArrayList<>(partitionDefMap.size());
        for (BeaverShard.Builder builder : partitionDefMap.values()) {
            partitionDefs.add(builder.build());
        }
        return partitionDefs;
    }

    public static BeaverClient makeBeaverClient(BeaverConfig beaverConfig) {
        BeaverClient client = new BeaverClient();
        client.init(beaverConfig.toTypeSafeConfig());
        return client;
    }

}

