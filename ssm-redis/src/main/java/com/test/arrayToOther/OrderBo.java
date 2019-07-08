package com.test.arrayToOther;

import lombok.Data;

/**
 * Created by bobo on 2019/4/24 10:16
 */
@Data
public class OrderBo {

    /**
     * 30  ,      5,       1,           48,       52,     18100    count
     * 品牌      系列     商品ID         属性       规格      单价
     * brand   series   productId    attribute    spec    price
     */


    //    品牌
    private String brand;
    private String brandName;

    //    系列
    private String series;
    private String seriesName;

    //商品
    private String productId;
    private String productName;

    //规格
    private String spec;
    private String specName;

    //属性
    private String attribute;
    private String attributeName;

    //单价
    private Double price;

    //总数
    private String  count;


}
