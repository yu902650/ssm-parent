package com.test.去重;

/**
 * Created by bobo on 2019/4/26 9:15
 */
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Person2 p1 = new Person2("张三", new BigDecimal("10.0"));
        Person2 p2 = new Person2("王五", new BigDecimal("10.0"));
        Person2 p3 = new Person2("张三", new BigDecimal("10.0"));
        Person2 p4 = new Person2("李明", new BigDecimal("10.0"));
        Person2 p5 = new Person2("李明", new BigDecimal("10.0"));
        /**
         * 求薪资总和，名字相同的只加一次
         */
        List<Person2> pList = new ArrayList<Person2>();
        pList.add(p1);
        pList.add(p2);
        pList.add(p3);
        pList.add(p4);
        pList.add(p5);

        StringBuilder dealBankNumber = new StringBuilder();
        BigDecimal sum = pList.stream().filter(v -> {
            boolean flag = !dealBankNumber.toString().contains(v.getName());
            dealBankNumber.append("_").append(v.getName());
            return flag;
        }).map(Person2::getSalary).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(sum);
    }
}

class Person2 {
    private String name;// 姓名
    private BigDecimal salary;// 工资

    public Person2(String name, BigDecimal salary) {
        this.salary = salary;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

}