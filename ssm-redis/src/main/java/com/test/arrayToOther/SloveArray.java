package com.test.arrayToOther;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bobo on 2019/4/24 14:09
 */
public class SloveArray {

        public static void main(String[] args) {
            /**
             * 30  ,      5,       1,           48,       52,     18100    count
             * 品牌      系列     商品ID         属性       规格      单价       1
             * brand   series   productId    attribute    spec    price      1
             */
            String curr = "30,5,1,48,52,18100,1|30,5,2,38,59,20000,1";
            String [] order = curr.split("\\|");
            List<OrderBo> orderBoList = new ArrayList<>();

            for (String record : order){
                OrderBo orderBo = new OrderBo();
                String [] chang = record.split(",");
                //品牌
                String brand = chang[0];
                //系列
                String series = chang[1];
                //
                String productId = chang[2];
                String attribute = chang[3];
                String spec =chang[4];
                Double price = Double.valueOf(chang[5]);
                int count = Integer.parseInt(chang[6]);
                orderBo.setBrand(brand);
                orderBo.setSeries(series);
                orderBo.setProductId(productId);
                orderBo.setAttribute(attribute);
                orderBo.setSpec(spec);
                orderBo.setPrice(price);
                orderBo.setCount(String.valueOf(count));
                orderBoList.add(orderBo);
            }
            System.out.println(orderBoList);
            System.out.println(orderBoList.size());
        }
    }


/**
 *
 *
 *         Map<String, Object> respMap = new HashMap<>(16);
 *         //all 查询目前customerId  所有订单
 *         List<Order> allOrder = orderDao.findAllOrder(map);
 *         List<Order> currOrder= new ArrayList<>();
 *         if (allOrder.size()>0){
 *             for (Order o :  allOrder){
 * //                String curr = "30,5,1,48,52,18100,1|30,5,2,38,59,20000,1";
 *                 String curr = o.getProductId();
 *                 String [] order = curr.split("\\|");
 *                 List<OrderBo> orderBoList = new ArrayList<>();
 *
 *                 for (String record : order){
 *                     OrderBo orderBo = new OrderBo();
 *                     String [] chang = record.split(",");
 *                     //品牌
 *                     String brand = chang[0];
 *                     //系列
 *                     String series = chang[1];
 *                     String productId = chang[2];
 *                     //属性
 *                     String attribute = chang[3];
 *                     //规格
 *                     String spec =chang[4];
 *                     Double price = Double.valueOf(chang[5]);
 *                     String count = chang[6];
 *                     String brandName = shopCarService.findBrandName(brand);
 *                     orderBo.setBrandName(brandName);
 *                     String seriesName = shopCarService.findSeriesName(series);
 *                     orderBo.setSeriesName(seriesName);
 *                     String productName = productService.findByProductId(productId);
 *                     orderBo.setProductName(productName);
 *                     String specName = shopCarService.findSpecName(spec);
 *                     orderBo.setSpecName(specName);
 *                     String attributeName = shopCarService.findAttributeName(attribute);
 *                     orderBo.setAttributeName(attributeName);
 *                     orderBo.setBrand(brand);
 *                     orderBo.setSeries(series);
 *                     orderBo.setProductId(productId);
 *                     orderBo.setAttribute(attribute);
 *                     orderBo.setSpec(spec);
 *                     orderBo.setPrice(price);
 *                     orderBo.setCount(count);
 *                     orderBoList.add(orderBo);
 *                 }
 *                 o.setOrderBos(orderBoList);
 *                 currOrder.add(o);
 *             }
 *             respMap.put("allOrder",currOrder);
 *         }else {
 *             respMap.put("allOrder",null);
 *         }
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
