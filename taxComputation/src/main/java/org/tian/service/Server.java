package org.tian.service;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.tian.service.impl.TaxComputationServiceImpl;


public class Server {
    public static void main(String[] args) {
        // 发布服务的工厂
        JaxWsServerFactoryBean factoryBean = new JaxWsServerFactoryBean();

        // 设置服务的地址
        factoryBean.setAddress("http://localhost:9000/ws/taxComputation");

        // 设置服务类
        factoryBean.setServiceBean(new TaxComputationServiceImpl());

        // 添加日志输入、输出拦截器、观察soap请求、soap响应内容
        factoryBean.getInInterceptors().add(new LoggingInInterceptor());
        factoryBean.getOutInterceptors().add(new LoggingOutInterceptor());

        // 发布服务
        factoryBean.create();

        System.out.println("发布服务成功，端口为9000");
    }
}
