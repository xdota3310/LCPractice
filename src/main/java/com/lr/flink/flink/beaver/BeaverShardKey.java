package com.lr.flink.flink.beaver;

import java.io.Serializable;
import java.util.Objects;

public class BeaverShardKey implements Serializable {
    private String index;
    private Long shardId;

    public BeaverShardKey(String index, Long shardId) {
        this.index = index;
        this.shardId = shardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeaverShardKey shardKey = (BeaverShardKey) o;
        return Objects.equals(index, shardKey.index) &&
                Objects.equals(shardId, shardKey.shardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, shardId);
    }

    public String getIndex() {
        return index;
    }

    public Long getShardId() {
        return shardId;
    }

    @Override
    public String toString() {
        return "BeaverShardKey{" +
                "index='" + index + '\'' +
                ", shardId=" + shardId +
                '}';
    }
}
