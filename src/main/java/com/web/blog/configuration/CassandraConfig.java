package com.web.blog.configuration;

import java.util.Arrays;
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
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.core.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.datastax.driver.core.Cluster;
import com.web.blog.controllers.crudDiaryController;

import org.springframework.core.env.Environment;


@Configuration
@ConfigurationProperties("spring.data.cassandra")
@EnableCassandraRepositories
public class CassandraConfig extends AbstractCassandraConfiguration {

	private static final Logger logger = LogManager.getLogger(CassandraConfig.class);
	
	private final String contactPoints;

	private final int port;

	private final String basePackages;
	
    private final String keyspaceName;

    
    CassandraConfig(
    	      @Value("${cassandra.keyspace}") String keyspace,
    	      @Value("${cassandra.port}") int port,
    	      @Value("${cassandra.contactpoints}") String contactPoints,
    	      @Value("${cassandra.basepackages}") String basePackages) {
    	    this.keyspaceName = keyspace;
    	    this.basePackages = basePackages;
    	    this.port = port;
    	    this.contactPoints=contactPoints;
    }

    @Override
    protected String getContactPoints() {
      return contactPoints;
    }

    @Override
    protected int getPort() {
      return port;
    }
 
    @Override
    protected String getKeyspaceName() {
        return this.keyspaceName;
    }
    
    @Override
    protected List<String> getStartupScripts() {
      final String script =
          "CREATE KEYSPACE IF NOT EXISTS "
              + keyspaceName
              + " WITH durable_writes = true"
              + " AND replication = {'class' : 'SimpleStrategy', 'replication_factor' : 1};";
      return Arrays.asList(script);
    }
    
    @Override 
    protected boolean getMetricsEnabled() { return false; }
    
    
    @Override
    public String[] getEntityBasePackages() {
        return new String[]{this.basePackages};
    }
    
    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.RECREATE;
    }

    @Bean
    public CassandraOperations cassandraOperations() throws Exception {
        return new CassandraTemplate(session().getObject());
    }
    
    @Override
    protected List<String> getShutdownScripts() {
      return Arrays.asList("DROP KEYSPACE IF EXISTS " + keyspaceName + ";");
    }
    
}
