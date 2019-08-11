package com.web.blog.apachePOI;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import com.web.blog.util.Convertor;


public class Read_Doc_Table {
		
	private static XWPFDocument xdoc;

	public static LinkedHashMap readDocx() {

		ArrayList<String> collection = new ArrayList();

		try {
			FileInputStream fis = new FileInputStream("src/main/resources/apachePOI/CV.docx");
			xdoc = new XWPFDocument(OPCPackage.open(fis));
			Iterator<?> bodyElementIterator = xdoc.getBodyElementsIterator();
			while (bodyElementIterator.hasNext()) {
				IBodyElement element = (IBodyElement) bodyElementIterator.next();

				if ("TABLE".equalsIgnoreCase(element.getElementType().name())) {
					List<XWPFTable> tableList = element.getBody().getTables();
					for (XWPFTable table : tableList) {
						for (int i = 0; i < table.getRows().size(); i++) {

							for (int j = 0; j < table.getRow(i).getTableCells().size(); j++) {

								collection.add(table.getRow(i).getCell(j).getText());
							
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		

		
		return Convertor.convertListToTableOfKeyAndValueMap(collection);
	}
	
	
	
	
}
