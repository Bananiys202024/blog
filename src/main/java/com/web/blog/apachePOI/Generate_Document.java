package com.web.blog.apachePOI;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xwpf.usermodel.VerticalAlign;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;


public class Generate_Document {

	public static void generate_doc(String name,String phone,String address,String email, String dateOfBirth, String nationality, String skills, String programming, String languages) {
		
		final Logger logger = LogManager.getLogger(Generate_Document.class);
		
		try {
			
		
		  XWPFDocument document = new XWPFDocument(); 
	      FileOutputStream out = new FileOutputStream(new File("src/main/resources/apachePOI/CV.docx"));
	        	      
	      
	      //create table
	      XWPFTable table = document.createTable();
			
	      //create first row
	      XWPFTableRow tableRowOne = table.getRow(0);
	      tableRowOne.getCell(0).setText("About me");
	      tableRowOne.addNewTableCell().setText("  ");;
	      
	      //create second row
	      XWPFTableRow tableRowTwo = table.createRow();
	      tableRowTwo.getCell(0).setText("Name");
	      tableRowTwo.getCell(1).setText(name);
			
	      //create third row
	      XWPFTableRow tableRowThree = table.createRow();
	      tableRowThree.getCell(0).setText("Address");
	      tableRowThree.getCell(1).setText(address);
		

	      XWPFTableRow tableRowFour = table.createRow();
	      tableRowFour.getCell(0).setText("Phone");
	      tableRowFour.getCell(1).setText(phone);
	      

	      XWPFTableRow tableRowFive = table.createRow();
	      tableRowFive.getCell(0).setText("Email");
	      tableRowFive.getCell(1).setText(email);
	      

	      XWPFTableRow tableRowSix = table.createRow();
	      tableRowSix.getCell(0).setText("dateOfBirth");
	      tableRowSix.getCell(1).setText(dateOfBirth);
	      

	      XWPFTableRow tableRowSeven = table.createRow();
	      tableRowSeven.getCell(0).setText("Nationality");
	      tableRowSeven.getCell(1).setText(nationality);
	      
	      XWPFTableRow tableRowEight = table.createRow();
	      tableRowEight.getCell(0).setText("Skills");
	      tableRowEight.getCell(1).setText(skills);
	      
	      XWPFTableRow tableRowNine = table.createRow();
	      tableRowNine.getCell(0).setText("Programming");
	      tableRowNine.getCell(1).setText(programming);
	      
	      XWPFTableRow tableRowTen = table.createRow();
	      tableRowTen.getCell(0).setText("Languages");
	      tableRowTen.getCell(1).setText(languages);
	      	      
		  document.write(out);
	      out.close();
	      logger.info("createparagraph.docx written successfully");
		} catch (Exception e) {
			logger.info("Errors---"+e);
		}
	}

}
