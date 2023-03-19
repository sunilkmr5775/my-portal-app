package com.sunil.myportal;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {


    // print the numbers starting with 1

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(16);
        list.add(27);
        list.add(13);
        list.add(91);
        list.stream().filter(a->a.toString().startsWith("1")).forEach(System.out::println);/*
        for (Integer result : number) {
            if (result.toString().startsWith("1")) {
                System.out.print(result + ", ");
            }
        }*/
    }

}
