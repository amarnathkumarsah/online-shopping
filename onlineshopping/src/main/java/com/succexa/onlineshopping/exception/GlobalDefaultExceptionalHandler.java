package com.succexa.onlineshopping.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice//every controller will take a particular advice for any Exception
public class GlobalDefaultExceptionalHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerNoHandlerFoundException(){
		ModelAndView mv = new ModelAndView("error");
		
		mv.addObject("errorTitle","The page is not constructed!");
		mv.addObject("errorDescription","The page you are looking for is not available now");
		mv.addObject("title","404 Error page");
		return mv;
		
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView productNotFoundException(){
		ModelAndView mv = new ModelAndView("error");
		
		mv.addObject("errorTitle",new ProductNotFoundException().getMessage());
		mv.addObject("errorDescription","The prodcut you are looking for is not available right now");
		mv.addObject("title","500 Error page");
		return mv;
		
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView exceptionHandler(Exception ex){
		ModelAndView mv = new ModelAndView("error");
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		ex.printStackTrace(pw);
		
		mv.addObject("errorTitle", "Contact your Aministrator!");
		mv.addObject("errorDescription",/*ex.toString()*/ sw.toString());
		mv.addObject("title","Error");
		return mv;
		
	}
	
}
