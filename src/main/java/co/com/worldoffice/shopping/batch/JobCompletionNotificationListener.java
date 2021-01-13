package co.com.worldoffice.shopping.batch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport{
	private static Logger log = LogManager.getLogger(JobCompletionNotificationListener.class);
	
	private final JdbcTemplate jdbcTemplate;

	  @Autowired
	  public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
	    this.jdbcTemplate = jdbcTemplate;
	  }

	  @Override
	  public void afterJob(JobExecution jobExecution) {
	    if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
	      log.info("!!! JOB FINISHED! Time to verify the results");

	      jdbcTemplate.query("SELECT product_name, brand, price, stock, state, discount_percent FROM products",
	        (rs, row) -> new Product(
	          rs.getString(1),
	          rs.getString(2),
	          rs.getBigDecimal(3),
	          rs.getInt(4),
	          rs.getString(5),
	          rs.getBigDecimal(6))
	      ).forEach(product -> log.info("Found <" + product + "> in the database."));
	    }
	  }
	
}
