package co.com.worldoffice.shopping.batch;

import javax.validation.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.SmartValidator;

import java.util.Set;

import javax.validation.ConstraintViolation;

public class ProductProcessor implements ItemProcessor<FileProduct,FileProduct>{

	private Logger logger = LogManager.getLogger(ProductProcessor.class);
	
	@Autowired
	Validator validator;
	
	@Override
	public FileProduct process(FileProduct item) throws Exception {
		// TODO Auto-generated method stub
		logger.info("Este es el metodo process");
		logger.info(item.getName() + "," + item.getBrand());
		Set<ConstraintViolation<FileProduct>> constraintViolation = validator.validate(item);
		if(!constraintViolation.isEmpty()) {
			return null; 
		} else {
			return item;
		}
		/*if(item.getBrand()==null || item.getBrand().isEmpty()) {
			return null;
		} else {
			return item;
		}*/
	}

}
