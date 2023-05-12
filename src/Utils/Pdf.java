/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import Entity.Appointment;
import Services.ServiceRednezVous;
import com.itextpdf.text.Document;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author oumayma
 */
public class Pdf {
        public void GeneratePdf(String filename) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        Document document= new  Document() {};
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
      
        ServiceRednezVous m=new ServiceRednezVous();
        List<Appointment> list=m.readAll();    
        document.add(new Paragraph("La liste des Rendez Vous :"));
        document.add(new Paragraph("     "));
        // Create a table with one column and set the border
    PdfPTable table = new PdfPTable(1);
    table.getDefaultCell().setBorder(Rectangle.BOX);

    for(Appointment u : list) {
        // Add content to the table cell
        PdfPCell cell = new PdfPCell();
        cell.addElement(new Paragraph("nom du patient:"+u.getUser().getUserName()));
        cell.addElement(new Paragraph("nom du Docteur :"+u.getDoctor().getFirstName()));
        cell.addElement(new Paragraph("DATE :"+u.getAppointment_date()));
        cell.addElement(new Paragraph("catégorie du Rendez Vous:"+u.getCategorie()));
        cell.addElement(new Paragraph("type du rendez Vous:"+u.getType().getNomtype()));
        
        // Add the table cell to the table
        table.addCell(cell);
    }

    // Add the table to the document
    document.add(table);

        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }
    
}