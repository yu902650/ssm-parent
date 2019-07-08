package com.dataType;

/**
 * Created by bobo on 2019/5/4 8:34
 * <p>
 * 缓存池
 */
public class CachePool {

    public static void main(String[] args) {


        /**
         * new Integer(123) 与 Integer.valueOf(123) 的区别在于：
         *
         * `new Integer(123) 每次都会新建一个对象；
         * `Integer.valueOf(123) 会使用缓存池中的对象，多次调用会取得同一个对象的引用。
         */
        Integer x = new Integer(123);
        Integer y = new Integer(123);
//        System.out.println(x == y);    // false
        System.out.println(x.equals(y));
        Integer z = Integer.valueOf(123);
        Integer k = Integer.valueOf(123);
//        System.out.println(z == k);   // true
        System.out.println(z.equals(k));


//            valueOf() 方法的实现比较简单，就是先判断值是否在缓存池中，如果在的话就直接返回缓存池的内容。
//
//            public static Integer valueOf(int i) {
//                if (i >= IntegerCache.low && i <= IntegerCache.high)
//                    return IntegerCache.cache[i + (-IntegerCache.low)];
//                return new Integer(i);
//            }
//
//
//            在 Java 8 中，Integer 缓存池的大小默认为 -128~127。
//
//            static final int low = -128;
//            static final int high;
//            static final Integer cache[];
//
//            static {
//                // high value may be configured by property
//                int h = 127;
//                String integerCacheHighPropValue =
//                    sun.misc.VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
//                if (integerCacheHighPropValue != null) {
//                    try {
//                        int i = parseInt(integerCacheHighPropValue);
//                        i = Math.max(i, 127);
//                        // Maximum array size is Integer.MAX_VALUE
//                        h = Math.min(i, Integer.MAX_VALUE - (-low) -1);
//                    } catch( NumberFormatException nfe) {
//                        // If the property cannot be parsed into an int, ignore it.
//                    }
//                }
//                high = h;
//
//                cache = new Integer[(high - low) + 1];
//                int j = low;
//                for(int k = 0; k < cache.length; k++)
//                    cache[k] = new Integer(j++);
//
//                // range [-128, 127] must be interned (JLS7 5.1.7)
//                assert IntegerCache.high >= 127;
//            }
//            编译器会在自动装箱过程调用 valueOf() 方法，因此多个值相同且值在缓存池范围内的 Integer 实例使用自动装箱来创建，那么就会引用相同的对象。
//
//            Integer m = 123;
//            Integer n = 123;
//            System.out.println(m == n); // true
//            基本类型对应的缓冲池如下：
//
//            boolean values true and false
//            all byte values
//            short values between -128 and 127
//            int values between -128 and 127
//            char in the range \u0000 to \u007F
//            在使用这些基本类型对应的包装类型时，就可以直接使用缓冲池中的对象。
    }
}
