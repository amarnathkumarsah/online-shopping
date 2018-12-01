package com.succexa.onlineshopping.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.succexa.shoppingbackend.dto.Product;

public class UploadValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Product product = (Product)target;

		if (product.getFile() == null ||product.getFile().getOriginalFilename() == ""||product.getFile().getContentType().equals("application/octet-stream")) {
			errors.rejectValue("file", null, "Please select an image file to upload");
			return;
		}

		
		if (!(product.getFile().getContentType().equals("image/jpeg")
				|| product.getFile().getContentType().equals("image/png")
				|| product.getFile().getContentType().equals("image/jpg")
				|| product.getFile().getContentType().equals("image/gif"))) {

			errors.rejectValue("file",null, "Please select only valid image to upload");
			
		}
		
	}

}