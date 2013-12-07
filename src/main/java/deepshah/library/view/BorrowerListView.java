package deepshah.library.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfAction;
import com.lowagie.text.pdf.PdfDestination;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfWriter;

import deepshah.library.model.Borrower;
import deepshah.library.model.LibraryBranch;


public class BorrowerListView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<Borrower> list =  (List<Borrower>) model.get("borrower_model");
		Paragraph title = new Paragraph("Borrower Details",FontFactory.getFont(FontFactory.TIMES_BOLD, 16,Font.BOLD));
				title.setAlignment(Element.ALIGN_CENTER);
				document.add(title);
         
	        Table table = new Table(5);
	        table.addCell("Card No");
	        table.addCell("First Name");
	        table.addCell("Last Name");
	        table.addCell("Address");
	        table.addCell("Phone");
	        table.setPadding(5);
	          for (Borrower result : list) {
	    			table.addCell(String.valueOf(result.getCard_no()));
	  				table.addCell(String.valueOf(result.getFname()));
	  				table.addCell(String.valueOf(result.getLname()));
	  				table.addCell(String.valueOf(result.getAddress()));
	  				table.addCell(String.valueOf(result.getPhone()));
	  			}
	  		document.add(table);
	     
		// to open the PDF in 100% zoom
		writer.setOpenAction(PdfAction.gotoLocalPage(1, new PdfDestination(
				PdfDestination.XYZ, 0, 10000, 1), writer));
	}
}
