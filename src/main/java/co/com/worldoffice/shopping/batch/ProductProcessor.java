package co.com.worldoffice.shopping.batch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

public class ProductProcessor implements ItemProcessor<FileProduct,FileProduct>{

	private Logger logger = LogManager.getLogger(ProductProcessor.class);
	
	@Override
	public FileProduct process(FileProduct item) throws Exception {
		// TODO Auto-generated method stub
		logger.info("Este es el metodo process");
		logger.info(item.getName() + "," + item.getBrand());
		//if (item.getStock()==null)
		return item;
	}

}
