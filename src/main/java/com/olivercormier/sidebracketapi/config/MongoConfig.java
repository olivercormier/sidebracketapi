package com.olivercormier.sidebracketapi.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoConfig {

    @Value("mongodb+srv://Oliver:Password@cluster0.jauf8.mongodb.net/SideBracket-Database?retryWrites=true&w=majority")
    private String mongoUri;

    @Value("SideBracket-Database")
    private String mongoDatabase;

    @Bean
    protected String getDatabaseName() {
        return mongoDatabase;
    }

    @Bean
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(mongoUri);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString).build();
        return MongoClients.create(mongoClientSettings);
    }
}
