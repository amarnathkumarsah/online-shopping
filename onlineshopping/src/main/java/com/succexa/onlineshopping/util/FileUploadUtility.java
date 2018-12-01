package com.succexa.onlineshopping.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

/*	@MultipartConfig(
//location="/tmp", 
fileSizeThreshold= 1024*1024, //1 MB
maxFileSize= 1024*1024*5, // 4 MB
maxRequestSize= 1024*1024*5*5)// 25 MB
*/
public class FileUploadUtility {

	private static final String ABS_PATH = "F:/springProjects/online_shopping/onlineshopping/src/main/webapp/assets/images/"; 
	private static String REAL_PATH = "";
	

	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
		
		// TODO Auto-generated method stub
		REAL_PATH =  request.getSession().getServletContext().getRealPath("/assets/images/").toString().replace('\\', '/');

		//if not exist then create
		
		File fileREAL_PATH = new File(REAL_PATH);
		System.out.println(REAL_PATH);
		
		 
		if (!fileREAL_PATH.exists()) {
      
			fileREAL_PATH.mkdir();
           
        }
		
		
		File fileABS_PATH = new File(ABS_PATH);
		System.out.println(ABS_PATH);
		
		if (!fileABS_PATH.exists()) {
      
			fileABS_PATH.mkdir();;
           
        }
		
		
		try {
			
			//server upload
			file.transferTo(new File(REAL_PATH+"/"+code+".jpg"));
			
			//project directory upload
			file.transferTo(new File(ABS_PATH+"/"+code+".jpg"));
		} catch (IOException e) {


		}
	
	}
	
	

}
