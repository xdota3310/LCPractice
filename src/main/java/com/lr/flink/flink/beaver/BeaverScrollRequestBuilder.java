package com.lr.flink.flink.beaver;

import cn.yottabyte.beaver.action.search.ShardsSearchRequestBuilder;
import cn.yottabyte.beaver.client.BeaverClient;
import cn.yottabyte.beaver.grpc.*;
import cn.yottabyte.beaver.search.query.QueryStringQueryBuilder;
import org.apache.commons.collections.CollectionUtils;
import org.apache.directory.api.util.Strings;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author chengtao
 * @date 21/11/2020 - 23:26
 */
public class BeaverScrollRequestBuilder {
    private final BeaverClient client;

    // NOTE: now we use queryString query ONLY!
    private String queryString;

    private long earliest;
    private long latest;
    private int scrollSize = 10000;
    private Set<String> fields;
    private List<SortField> sortFields;
    private BeaverShard beaverShard;

    private static class SortField {
        String field;
        SortOrder order;
        SortField(String field, SortOrder order) {
            this.field = field;
            this.order = order;
        }
    }

    public BeaverScrollRequestBuilder(BeaverClient client) {
        this.client = client;
    }

    public ShardsSearchRequestBuilder build() {
        if (Strings.isEmpty(queryString)) {
            throw new IllegalStateException("query is null");
        }
        ShardsSearchRequestBuilder srb = new ShardsSearchRequestBuilder(client);
        // 1) build Query
        srb.setQuery(new QueryStringQueryBuilder(queryString));

        // 2) build TimeRange
        QueryTimeRange.Builder trBuilder =
                QueryTimeRange.newBuilder().setTimeRange(
                        TimeRange.newBuilder().setMin(earliest).setMax(latest)
                ).setType(TimeRangeType.kEvent);
        srb.setQueryTimeRange(trBuilder);

        // 3) build Scroll
        srb.setSize(scrollSize);

        // 4) add fields
        if (CollectionUtils.isNotEmpty(fields)) {
            for (String field : fields) {
                srb.addField(field);
                srb.setFetchSource(true);
            }
        }

        // 5) add sort orders
        if (CollectionUtils.isNotEmpty(sortFields)) {
            for (SortField sort : sortFields) {
                srb.addSort(sort.field, sort.order);
            }
        }

        // 6) add shard info
        BeaverShardCopy shardCopy = beaverShard.getShardCopies().get(0);
        srb.setShardRoutings(Arrays.asList(shardCopy.buildShartRouting()));

        NetAddress netAddress = NetAddress.newBuilder()
                .setIp(shardCopy.getNetAddressIp())
                .setPort(shardCopy.getNetAddressPort())
                .build();
        srb.setNetAddress(netAddress);

        return  srb;
    }


    public String getQueryString() {
        return queryString;
    }

    public BeaverScrollRequestBuilder setQueryString(String queryString) {
        this.queryString = queryString;
        return this;
    }

    public long getEarliest() {
        return earliest;
    }

    public BeaverScrollRequestBuilder setEarliest(long earliest) {
        this.earliest = earliest;
        return this;
    }

    public long getLatest() {
        return latest;
    }

    public BeaverScrollRequestBuilder setLatest(long latest) {
        this.latest = latest;
        return this;
    }

    public int getScrollSize() {
        return scrollSize;
    }

    public BeaverScrollRequestBuilder setScrollSize(int scrollSize) {
        this.scrollSize = scrollSize;
        return this;
    }

    public Set<String> getFields() {
        return fields;
    }

    public BeaverScrollRequestBuilder setFields(Set<String> fields) {
        this.fields = fields;
        return this;
    }

    public List<SortField> getSortFields() {
        return sortFields;
    }

    public BeaverScrollRequestBuilder setSortFields(List<SortField> sortFields) {
        this.sortFields = sortFields;
        return this;
    }

    public BeaverShard getBeaverShard() {
        return beaverShard;
    }

    public BeaverScrollRequestBuilder setBeaverShard(BeaverShard beaverShard) {
        this.beaverShard = beaverShard;
        return this;
    }
}
