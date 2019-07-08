package com.test.arrayToOther;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArrayToList {
    /**
     *  处理 JSON 中的数组.
     *  数组中的每个值都对应实体
     *
     * {"clientID":"866297039337253","
     * datas":["16637 110 20 20 10 1",
     * "16638 111 20 20 10 1",
     * "16639 112 20 20 10 1",
     * "16640 113 20 20 10 1",
     * "16641 114 20 20 10 1"],
     * "sn":"503677","mType":"REP"}
     */
    public static void main(String[] args) {

        String[] s = {"16637 110 20 20 10 1", "16638 111 20 20 10 1", "16639 112 20 20 10 1", "16640 113 20 20 10 1", "16641 114 20 20 10 1"};
        List<InventoryTo> toList = new ArrayList<>();
        for (String message : s) {
            //货道  容量 库存 价格 状态
            // 155    8      8    10     1
            System.out.println("message >>>>>       :    " + message);
            InventoryTo to = new InventoryTo();
            String[] change = message.split(" ");
            String id = change[0];
            if (!StringUtils.isEmpty(id)) {
                to.setInventoryId(Integer.parseInt(id));
            }
            String capacity = change[2];
            if (!StringUtils.isEmpty(capacity)) {
                to.setCapacity(Integer.parseInt(capacity));
            }
            String margin = change[3];
            if (!StringUtils.isEmpty(margin)) {
                to.setMargin(Integer.parseInt(margin));
            }
            toList.add(to);
        }
        System.out.println(toList);

    }


    private static class InventoryTo {
        private int margin;
        private int capacity;
        private int inventoryId;

        public int getMargin() {
            return margin;
        }

        public void setMargin(int margin) {
            this.margin = margin;
        }

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }

        public int getInventoryId() {
            return inventoryId;
        }

        public void setInventoryId(int inventoryId) {
            this.inventoryId = inventoryId;
        }
    }
}
