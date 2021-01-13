package co.com.worldoffice.shopping.batch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

public class ProductProcessor implements ItemProcessor<Product,Product>{

	private Logger logger = LogManager.getLogger(ProductProcessor.class);
	
	@Override
	public Product process(Product item) throws Exception {
		// TODO Auto-generated method stub
		logger.info("Este es el metodo process");
		logger.info(item.getName() + "," + item.getBrand());
		//if (item.getStock()==null)
		return item;
	}

}
