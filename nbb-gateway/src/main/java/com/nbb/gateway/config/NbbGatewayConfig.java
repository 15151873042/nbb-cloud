package com.nbb.gateway.config;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.nbb.gateway.loadbalancer.HeaderSameClusterPriorityLoadBalancer;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

public class NbbGatewayConfig {

    @Bean
    public ReactorLoadBalancer<ServiceInstance> reactorServiceInstanceLoadBalancer(Environment environment,
                                                                                   LoadBalancerClientFactory loadBalancerClientFactory) {
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        ObjectProvider<ServiceInstanceListSupplier> objectProvider = loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class);

        return new HeaderSameClusterPriorityLoadBalancer(objectProvider, name);
    }
}
