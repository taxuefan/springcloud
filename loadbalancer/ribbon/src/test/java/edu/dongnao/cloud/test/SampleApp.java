package edu.dongnao.cloud.test;

import java.net.URI;

import com.netflix.client.ClientFactory;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import com.netflix.niws.client.http.RestClient;
import org.apiguardian.api.API;

public class SampleApp {
    public static void main(String[] args) throws Exception {
        // 使用配置管理器加载配置资源：sample-client.properties
        ConfigurationManager.loadPropertiesFromResources("sample-client.properties");
        System.out.println(ConfigurationManager.getConfigInstance().getProperty("sample-client.ribbon.listOfServers"));
        
        // 使用ClientFactory创建Ribbon负载均衡器的Rest客户端
        RestClient client = (RestClient) ClientFactory.getNamedClient("sample-client");
        // 使用构建器构建http请求。请注意，我们仅提供URI的路径部分（"/"）。负载均衡器选择服务器后，客户端将计算出完整的URI
        HttpRequest request = HttpRequest.newBuilder().uri(new URI("/")).build();
        
        for (int i = 0; i < 20; i++)  {
            // 调用client.executeWithLoadBalancer() API而不是execute() API
            HttpResponse response = client.executeWithLoadBalancer(request);
            System.out.println("Status code for " + response.getRequestedURI() + "  :" + response.getStatus());
        }
        @SuppressWarnings("rawtypes")
        ZoneAwareLoadBalancer lb = (ZoneAwareLoadBalancer) client.getLoadBalancer();
        System.out.println(lb.getLoadBalancerStats());
        // 从配置动态更改服务器池
        ConfigurationManager.getConfigInstance().setProperty(
                "sample-client.ribbon.listOfServers", "www.weibo.com:80,www.taobao.com:80");
        System.out.println("changing servers ...");
        
        // 等待刷新服务器列表（属性文件中定义的2秒参考间隔）
        Thread.sleep(3000); // 6
        for (int i = 0; i < 20; i++)  {
            HttpResponse response = null;
            try {
                response = client.executeWithLoadBalancer(request);
                System.out.println("Status code for " + response.getRequestedURI() + "  : " + response.getStatus());
            } finally {
                if (response != null) {
                    response.close();
                }
            }
        }
        
        // 打印出负载均衡器记录的服务器统计信息
        System.out.println(lb.getLoadBalancerStats());
    }
}
