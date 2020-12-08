package com.lr.flink.flink.beaver;

import cn.yottabyte.beaver.cluster.ShardRouting;
import cn.yottabyte.beaver.grpc.NetAddress;
import cn.yottabyte.beaver.grpc.ShardCopy;

import java.io.Serializable;

public class BeaverShardCopy implements Serializable {
    private BeaverShardKey shardKey;
    private Long copyId;
    private int netAddressIp;
    private int netAddressPort;
    private Long indexId;

    public static BeaverShardCopy createWithShardRouting(ShardRouting shardRouting) {
        BeaverShardCopy shard = new BeaverShardCopy();
        shard.shardKey= new BeaverShardKey(shardRouting.getIndex(), shardRouting.getShardId());
        shard.copyId = shardRouting.getCopyId();
        shard.netAddressIp = shardRouting.getNetAddress().getIp();
        shard.netAddressPort = shardRouting.getNetAddress().getPort();
        shard.indexId = shardRouting.getIndexId();
        return shard;
    }

    public ShardRouting buildShartRouting() {
        NetAddress address = NetAddress.newBuilder()
                .setIp(netAddressIp)
                .setPort(netAddressPort)
                .build();
        ShardCopy shardCopy = ShardCopy.newBuilder()
                .setIndex(shardKey.getIndex())
                .setShardId(shardKey.getShardId())
                .setId(copyId)
                .setNetAddress(address)
                .build();
        return new ShardRouting(shardCopy, indexId);
    }

    public BeaverShardKey getShardKey() {
        return shardKey;
    }

    public void setShardKey(BeaverShardKey shardKey) {
        this.shardKey = shardKey;
    }

    public Long getCopyId() {
        return copyId;
    }

    public void setCopyId(Long copyId) {
        this.copyId = copyId;
    }

    public int getNetAddressIp() {
        return netAddressIp;
    }

    public void setNetAddressIp(int netAddressIp) {
        this.netAddressIp = netAddressIp;
    }

    public int getNetAddressPort() {
        return netAddressPort;
    }

    public void setNetAddressPort(int netAddressPort) {
        this.netAddressPort = netAddressPort;
    }

    public Long getIndexId() {
        return indexId;
    }

    public void setIndexId(Long indexId) {
        this.indexId = indexId;
    }

    @Override
    public String toString() {
        return "BeaverShardCopy{" +
                "shardKey=" + shardKey +
                ", copyId=" + copyId +
                ", netAddressIp=" + netAddressIp +
                ", netAddressPort=" + netAddressPort +
                ", indexId=" + indexId +
                '}';
    }
}
