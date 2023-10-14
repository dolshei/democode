package com.example.democode.domain.sample.simple.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressExample {
    public static void main(String[] args) {
        try {
            InetAddress local = InetAddress.getLocalHost();
            System.out.println("내 컴퓨터 IP 주소 : " + local.getHostAddress());

            InetAddress[] inetAddressesArr = InetAddress.getAllByName("www.naver.com");
            for (InetAddress remote : inetAddressesArr) {
                System.out.println("www.naver.com IP 주소 : " + remote.getHostAddress());
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
