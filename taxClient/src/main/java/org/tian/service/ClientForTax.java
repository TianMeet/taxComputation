package org.tian.service;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.tian.service.TaxComputationService;

import javax.jws.WebService;

@WebService
public class ClientForTax {
    // 服务接口的访问地址URL
    final static String URL = "http://localhost:9000/ws/taxComputation";
    public static double[] computeTax(double money){
        // 创建一个代理工厂
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();

        // 设置服务的地址
        jaxWsProxyFactoryBean.setAddress(URL);

        // 设置服务的类型
        jaxWsProxyFactoryBean.setServiceClass(TaxComputationService.class);

        // 对接口生成代理对象
        TaxComputationService taxComputationService = jaxWsProxyFactoryBean.create(TaxComputationService.class);

        // 代理对象的类型
        System.out.println(taxComputationService.getClass());

        // 远程访问服务端方法
        double[] res = taxComputationService.computationTax(money);
        return res;
    }
}
