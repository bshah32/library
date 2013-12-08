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

import deepshah.library.model.Book;
import deepshah.library.model.LibraryBranch;


public class BookListView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<Book> list =  (List<Book>) model.get("book_model");
		Paragraph title = new Paragraph("Branch Details",FontFactory.getFont(FontFactory.TIMES_BOLD, 16,Font.BOLD));
				title.setAlignment(Element.ALIGN_CENTER);
				document.add(title);
         
	        Table table = new Table(2);
	        table.addCell("Book Id");
	        table.addCell("Title");
	        table.setPadding(5);
	          for (Book result : list) {
	    			table.addCell(String.valueOf(result.getBook_id()));
	  				table.addCell(String.valueOf(result.getTitle()));
	  			}
	  		document.add(table);
	     
		// to open the PDF in 100% zoom
		writer.setOpenAction(PdfAction.gotoLocalPage(1, new PdfDestination(
				PdfDestination.XYZ, 0, 10000, 1), writer));
	}
}
