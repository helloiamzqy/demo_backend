package com.example.demo.config;


import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
@EnableConfigurationProperties(MongoProperties.class)
public class MongoConfig extends AbstractMongoConfiguration {

    @Autowired
    private MongoProperties mongoProperties;

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(mongoProperties.getHost(), mongoProperties.getPort());
    }

    @Override
    protected String getDatabaseName() {
        return mongoProperties.getDatabase();
    }
}

