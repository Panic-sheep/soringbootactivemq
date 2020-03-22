package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {

            System.out.println(i);
            int b = i -1;

        }


    }
}
