package com.web.blog.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.logging.LogFactory;
import org.apache.juli.logging.Log;
import org.apache.logging.log4j.LogManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraEntityClassScanner;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.CassandraAdminOperations;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.cql.CqlIdentifier;
import org.springframework.data.cassandra.core.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.SimpleUserTypeResolver;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;



import com.datastax.driver.core.utils.UUIDs;
import com.web.blog.configuration.CassandraConfig;
import com.web.blog.controllers.crudJokesController;
import com.web.blog.entity.Jokes;
import com.web.blog.repositories.JokesRepository;
import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.thrift.transport.TTransportException;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraAdminOperations;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertNull;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.core.utils.UUIDs;
import com.google.common.collect.ImmutableSet;
import org.springframework.test.annotation.DirtiesContext.ClassMode;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JokesRepositoryTest  {

	private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(JokesRepositoryTest.class);

	public static final UUID id = UUIDs.timeBased();

	final static Jokes table = new Jokes(id, new Date(), "jokesXaXaIamLaught",false, 1L, "");
	
	@Autowired
	JokesRepository repository;
	
    @Test
    public void whenSavingJoke_thenAvailableOnRetrievalEntityJokes() {

       	repository.save(table);
       	 
       	Optional<Jokes> jokes = repository.findById(id);
       	 
        assertEquals(jokes.get().getId(), table.getId()); 
    }

    @Test
    public void whenDeletingJoke_thenNotAvailableOnRetrievalEntityJokes() {
        repository.delete(table);
        
        Optional<Jokes> jokes = repository.findById(id);
        
        assertNotEquals(jokes.orElse(new Jokes()), table); 
    }

    @Test
    public void whenSavingAndDeletingJoke_thenNotAvailableOnRetrievalEntityJokes() {
      
       	repository.save(table);
       	repository.delete(table);
       	 
       	Optional<Jokes> jokes = repository.findById(id);
       	Jokes uid = jokes.orElse(new Jokes(UUIDs.timeBased()));
        assertNotEquals(uid.getId(), table.getId()); 
    }
    
    @Test
    public void whenUpdatingJoke_thenAvailableOnRetrievalEntityJokes() {

    	table.setJokes("YaxAXAXAXAxAxAXaXa");
    	
       	repository.save(table);
       	 
       	Optional<Jokes> jokes = repository.findById(id);
       	Jokes xaxa = jokes.orElse(new Jokes(UUIDs.timeBased()));

        assertEquals(xaxa.getJokes() , table.getJokes() ); 
    	

    }
    
}

