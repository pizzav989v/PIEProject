package com.gestiunemagazin.util;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import com.gestiunemagazin.model.Angajat;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;



public class PDFGenerator {
	private static PDFGenerator INSTANCE = new PDFGenerator();

	
	private PDFGenerator() {

	}

	
	public static PDFGenerator getInstance() {
		return INSTANCE;
	}

	
	public void genereazaRaportAngajati(HttpServletResponse response, ArrayList<Angajat> angajati) throws IOException {
		Document doc = new Document();
		PdfWriter docWriter = null;
		try {
			// special font sizes
			Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));
			Font bf12 = new Font(FontFamily.TIMES_ROMAN, 12);

			// file path
			docWriter = PdfWriter.getInstance(doc, response.getOutputStream());

			// document header attributes
			doc.addAuthor("Cosmina Musat");
			doc.addCreationDate();
			doc.addProducer();
			doc.addCreator("Gestiune Hotel");
			doc.addTitle("Raport lunar angajari");
			doc.setPageSize(PageSize.LETTER);

			// open document
			doc.open();

			Paragraph paragraph = null;

			if (angajati.isEmpty()) {
				paragraph = new Paragraph("Nu s-a facut nicio angajare in luna curenta!");
			} else {
				// create a paragraph
				paragraph = new Paragraph("Raport lunar angajari");

				// specify column widths: id, nume, prenume, data, functie, email
				float[] columnWidths = { 0.5f, 2f, 2f, 2f, 3f, 3.5f };
				// create PDF table with the given widths
				PdfPTable table = new PdfPTable(columnWidths);
				// set table width a percentage of the page width
				table.setWidthPercentage(90f);

				// insert column headings
				insertCell(table, "Id", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Nume", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Prenume", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Data angajare", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Functie", Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, "Email", Element.ALIGN_CENTER, 1, bfBold12);
				table.setHeaderRows(1);

				// just some random data to fill
				for (Angajat angajat : angajati) {

					insertCell(table, String.valueOf(angajat.getIdAngajat()), Element.ALIGN_CENTER, 1, bf12);
					insertCell(table, angajat.getNume(), Element.ALIGN_CENTER, 1, bf12);
					insertCell(table, angajat.getPrenume(), Element.ALIGN_CENTER, 1, bf12);
					insertCell(table, String.valueOf(angajat.getDataAngajare()), Element.ALIGN_CENTER, 1, bf12);
					insertCell(table, angajat.getFunctie(), Element.ALIGN_CENTER, 1, bf12);
					insertCell(table, angajat.getEmail(), Element.ALIGN_CENTER, 1, bf12);
				}
				// add the PDF table to the paragraph
				paragraph.add(table);
			}

			// add the paragraph to the document
			doc.add(paragraph);

		} catch (DocumentException dex) {
			dex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (doc != null) {
				// close the document
				doc.close();
			}
			if (docWriter != null) {
				// close the writer
				docWriter.close();
			}
		}
	}

	
	private void insertCell(PdfPTable table, String text, int align, int colspan, Font font) {

		// create a new cell with the specified Text and Font
		PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
		// set the cell alignment
		cell.setHorizontalAlignment(align);
		// set the cell column span in case you want to merge two or more cells
		cell.setColspan(colspan);
		// in case there is no text and you wan to create an empty row
		if (text.trim().equalsIgnoreCase("")) {
			cell.setMinimumHeight(10f);
		}
		// add the call to the table
		table.addCell(cell);

	}

}
