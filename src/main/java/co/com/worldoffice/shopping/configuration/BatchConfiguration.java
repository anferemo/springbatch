package co.com.worldoffice.shopping.configuration;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import co.com.worldoffice.shopping.batch.JobCompletionNotificationListener;
import co.com.worldoffice.shopping.batch.Product;
import co.com.worldoffice.shopping.batch.ProductProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	  @Autowired
	  public JobBuilderFactory jobBuilderFactory;

	  @Autowired
	  public StepBuilderFactory stepBuilderFactory;
	  
	  
	  private Logger logger = LogManager.getLogger(BatchConfiguration.class);
	  
	  @Bean
	  public FlatFileItemReader<Product> reader() {
		  FlatFileItemReader<Product> reader = new FlatFileItemReader<>();
		  reader.setLinesToSkip(1);
		  reader.setResource(new ClassPathResource("Productos.csv"));
		  reader.setLineMapper(new DefaultLineMapper() {
			  {
		            //3 columns in each row
		            setLineTokenizer(new DelimitedLineTokenizer() {
		                {
		                    setNames(new String[]{"name", "brand", "price", "stock", "state", "discount_percent"});
		                }
		            });
		            //Set values in Product class
		            setFieldSetMapper(new BeanWrapperFieldSetMapper<Product>() {
		                {
		                    setTargetType(Product.class);
		                }
		            });
		        }
		  });
		  return reader;
	  }
	  
	  @Bean
	  public ProductProcessor processor() {
		  return new ProductProcessor();
	  }
	  
	  @Bean
	  public JdbcBatchItemWriter<Product> writer(DataSource dataSource) {
	    return new JdbcBatchItemWriterBuilder<Product>()
	      .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
	      .sql("INSERT INTO products (product_name, brand, price, stock, state, discount_percent) VALUES (:name, :brand, :price, :stock, :state, :discount_percent)")
	      .dataSource(dataSource)
	      .build();
	  }
	  
	  
	  @Bean
	  public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
		  
	    return jobBuilderFactory.get("importUserJob")
	      .incrementer(new RunIdIncrementer())
	      .listener(listener)
	      .flow(step1)
	      .end()
	      .build();
	  }

	  @Bean
	  public Step step1(JdbcBatchItemWriter<Product> writer) {
	    return stepBuilderFactory.get("step1")
	      .<Product, Product> chunk(10)
	      .reader(reader())
	      .processor(processor())
	      .writer(writer)
	      .faultTolerant()
	      .skipLimit(10)
	      .skip(org.springframework.batch.item.file.FlatFileParseException.class)
	      .build();
	  }


}
