package com.example.demo.health.indicator;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "zk.url")
public class ZookeeperIndicator implements HealthIndicator {

    @Value("${zk.url}")
    private String zkUrl;
    @Autowired(required = false)
    private CuratorFramework zkClient;

    @Override
    public Health health() {
        if (isDown()) {
            return Health.down().withDetail("url", zkUrl).withDetail("message", "Connection refused").build();
        }
        return Health.up().withDetail("url", zkUrl).build();
    }

    private boolean isDown() {
        if (zkClient == null) return true;
        boolean isConnected = false;
        try {
            isConnected = zkClient.getZookeeperClient()
                    .getZooKeeper().getState().isConnected();
        } catch (Exception ignored) {
        }
        return !isConnected;
    }
}
