//package com.demo.basis.net;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.function.Consumer;
//
///**
// * @Auther: zhouwenbin
// * @Date: 2019/8/17 11:57
// */
//public class Test {
//    private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);
//
//    public static void main(String[] args) {
//        //new MessageHandler("sever-meesage-handler", null)
//        TcpServerInitializer initializer = new TcpServerInitializer();
//        TcpServer server = new TcpServer("server", 8999, initializer);
//        server.startup(throwable -> System.out.println("fail"));
//
//
//    }
//}
