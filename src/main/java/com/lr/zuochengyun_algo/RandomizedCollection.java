package com.lr.zuochengyun_algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//lc-381
public class RandomizedCollection {
    Map<Integer, List<Integer>> valueToIndeies = new HashMap<>();
    Map<Integer, Integer> indexToValue = new HashMap<>();
    int nextIndex = 0;

    public RandomizedCollection() {

    }

    public boolean insert(int val) {
        int index = nextIndex++;
        indexToValue.put(index, val);
        if (valueToIndeies.containsKey(val)) {
            List<Integer> indexList = valueToIndeies.get(val);
            indexList.add(index);
            return false;
        } else {
            List<Integer> indexList = new ArrayList<>();
            valueToIndeies.put(val, indexList);
            return true;
        }
    }

    public boolean remove(int val) {
        int lastIndex = nextIndex - 1;
        if (valueToIndeies.containsKey(val)) {
            List<Integer> indexList = valueToIndeies.get(val);
            int removedIndex = indexList.remove(0);
            indexToValue.remove(removedIndex);
            if (indexList.isEmpty()) {
                valueToIndeies.remove(val);
            }

            int lastValue = indexToValue.get(lastIndex);
            List<Integer> lastValueIndexList = valueToIndeies.get(lastValue);
            for (int i = 0; i < lastValueIndexList.size(); i++) {
                if (lastValueIndexList.get(i) == lastIndex) {
                    lastValueIndexList.remove(i);
                    break;
                }
            }
            lastValueIndexList.add(removedIndex);
            indexToValue.put(removedIndex, lastValue);
            nextIndex--;
            return true;
        } else {
            return false;
        }
    }

    public int getRandom() {
        int randomIndex = (int) ((nextIndex) * Math.random());
        return indexToValue.get(randomIndex);
    }
}
