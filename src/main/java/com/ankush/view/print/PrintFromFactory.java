package com.ankush.view.print;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;

import com.ankush.entities.FromFactory;
import com.ankush.entities.FromFactoryTransaction;
import com.ankush.service.service.FromFactoryService;
import com.ankush.service.serviceImpl.FromFactoryServiceImpl;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PrintFromFactory {

	private FromFactoryService factoryService;
	private String fontName;
	public PrintFromFactory(int id) {
		this.fontName = "D:\\Madhuram\\images\\fonts\\kiran.ttf";
		this.factoryService = new FromFactoryServiceImpl();


		FromFactory fact = factoryService.getFromFactoryById(id);
		String date = fact.getDate().toString();
		// System.out.println(fact.getDate().get);
		System.out.println(date);
		DateTimeFormatter format;

		try {
			format = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss ");

			Font f2 = FontFactory.getFont(fontName, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 20f, Font.NORMAL,
					BaseColor.BLACK);

			PdfPTable table = new PdfPTable(3);
			table.setTotalWidth(new float[] { 30, 400, 50 });
			table.setLockedWidth(true);

			PdfPCell cell0 = new PdfPCell(new Phrase("A.k` ", f2));
			cell0.setFixedHeight(30);
			cell0.setBorder(Rectangle.BOX);
			table.addCell(cell0);

			PdfPCell cell = new PdfPCell(new Phrase("            tapiSala       ", f2));
			cell.setFixedHeight(30);
			cell.setBorder(Rectangle.BOX);
			table.addCell(cell);

			PdfPCell cell2 = new PdfPCell(new Phrase("naga", f2));
			cell2.setFixedHeight(30);
			cell2.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell2.setBorder(Rectangle.BOX);
			table.addCell(cell2);
			// Table data
			int sr = 0;
			for (FromFactoryTransaction tr : fact.getTransactions()) {
				PdfPCell cellSr = new PdfPCell(new Phrase("" + (++sr), f2));
				cellSr.setFixedHeight(30);
				cellSr.setVerticalAlignment(Element.ALIGN_RIGHT);
				cellSr.setBorder(Rectangle.BOX);
				table.addCell(cellSr);

				PdfPCell cellParticular = new PdfPCell(new Phrase("" + tr.getItem(), f2));
				cellParticular.setFixedHeight(30);
				cellParticular.setVerticalAlignment(Element.ALIGN_RIGHT);
				cellParticular.setBorder(Rectangle.BOX);
				table.addCell(cellParticular);

				PdfPCell cellQty = new PdfPCell(new Phrase("" + tr.getQty(), f2));
				cellQty.setFixedHeight(30);
				cellQty.setVerticalAlignment(Element.ALIGN_RIGHT);
				cellQty.setBorder(Rectangle.BOX);
				table.addCell(cellQty);
			}

			Rectangle pagesize = new Rectangle(616f, 600f + table.getTotalHeight());
			Document document = new Document(pagesize, 3f, 3f, 20f, 180f);

			PdfWriter.getInstance(document, new FileOutputStream("D:\\Madhuram\\images\\prints\\FormFactory.pdf"));
			document.open();
			Font font = FontFactory.getFont(fontName, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 35.8f, Font.NORMAL,
					BaseColor.BLACK);
			Paragraph p = new Paragraph("                               maQaurma baokrI", font);
			p.setLeading(15);
			document.add(p);
			
			Font f3 = FontFactory.getFont(fontName, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 20f, Font.NORMAL,
					BaseColor.BLACK);
			Paragraph p2 = new Paragraph(
					"                                                       ka{MTr maala paavataI", f3);
			p2.setLeading(25);
			document.add(p2);

			Paragraph pid = new Paragraph("          paavataI k`. :" + id, f3);
			pid.setLeading(25);
			document.add(pid);
			// Counter Name
			Paragraph p3 = new Paragraph("          ka{MTrcao naava :" + fact.getCounter().getCounterName(), f3);
			p3.setLeading(25);
			document.add(p3);

			// Date
			Paragraph p5 = new Paragraph("          idnaaMk :" + fact.getDate().format(format), f3);
			p5.setLeading(25);
			document.add(p5);

			Paragraph p4 = new Paragraph("   ", f3);
			p4.setLeading(25);
			document.add(p4);

			document.add(table);
			
			Paragraph p6 = new Paragraph(
					"          Gao{na jaaNaar :                                                                taabyaata GaoNaar : ",
					f3);
			p6.setLeading(25);
			document.add(p6);

			document.close();
			 try {
					
					File htmlFile = new File("D:\\Madhuram\\images\\prints\\FormFactory.pdf");
					Desktop.getDesktop().browse(htmlFile.toURI());

		     
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		new PrintFromFactory(2);

	}


}
