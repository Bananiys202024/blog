package com.web.blog.configuration;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.core.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.datastax.driver.core.Cluster;
import com.web.blog.controllers.crudDiaryController;

import org.springframework.core.env.Environment;


@Configuration
@PropertySource(value = { "classpath:cassandra.yml" })
@ConfigurationProperties("spring.data.cassandra")
@EnableCassandraRepositories
public class CassandraConfig extends AbstractCassandraConfiguration {

	private static final Logger logger = LogManager.getLogger(CassandraConfig.class);
	
    @Value("${keyspacename}")
    protected String keyspaceName;

    @Override
    protected String getKeyspaceName() {
        return this.keyspaceName;
    }
    
    @Override
    protected List getKeyspaceCreations() {
        return Collections.singletonList(CreateKeyspaceSpecification
                .createKeyspace(keyspaceName).ifNotExists()
                .with(KeyspaceOption.DURABLE_WRITES, true)
                .withSimpleReplication());
    }

    @Override
    protected List getStartupScripts() {
        return Collections.singletonList("CREATE KEYSPACE IF NOT EXISTS "
                + keyspaceName + " WITH replication = {"
                + " 'class': 'SimpleStrategy', "
                + " 'replication_factor': '3' " + "};");

    }
    
    
    @Override 
    protected boolean getMetricsEnabled() { return false; }
    
    
    @Override
    public String[] getEntityBasePackages() {
        return new String[]{"com.web.blog.entity"};
    }
    
    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.RECREATE_DROP_UNUSED;
    }

    @Bean
    public CassandraOperations cassandraOperations() throws Exception {
        return new CassandraTemplate(session().getObject());
    }
    
}
