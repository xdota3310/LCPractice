package com.lr.flink.flink.beaver;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BeaverShard implements PartitionDef {
    private BeaverShardKey shardKey;
    private List<BeaverShardCopy> shardCopies;

    public static class Builder {
        private String index;
        private Long shardId;
        private List<BeaverShardCopy> shardCopies;

        public BeaverShard build() {
            Preconditions.checkNotNull(shardCopies, "no shards!");
            Preconditions.checkNotNull(index, "index is null!");
            Preconditions.checkNotNull(shardId, "shard id is null");
            BeaverShard shardDef = new BeaverShard();
            shardDef.shardKey = new BeaverShardKey(index, shardId);
            shardDef.shardCopies = Collections.unmodifiableList(new ArrayList<>(shardCopies));
            return shardDef;
        }

        public Builder setIndex(String index) {
            this.index = index;
            return this;
        }

        public Builder setShardId(Long shardId) {
            this.shardId = shardId;
            return this;
        }

        public Builder setShardKey(BeaverShardKey shardKey) {
            setIndex(shardKey.getIndex());
            setShardId(shardKey.getShardId());
            return this;
        }

        public Builder addShardCopy(BeaverShardCopy shardCopy) {
            if (shardCopies == null) {
                shardCopies = new ArrayList<>();
            }
            shardCopies.add(shardCopy);
            return this;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public BeaverShardKey getShardKey() {
        return shardKey;
    }

    public List<BeaverShardCopy> getShardCopies() {
        return shardCopies;
    }

    @Override
    public String toString() {
        return "BeaverShard{\n" +
                "shardKey=" + shardKey +
                ",\n shardCopies=" + shardCopies +
                "\n}";
    }
}


