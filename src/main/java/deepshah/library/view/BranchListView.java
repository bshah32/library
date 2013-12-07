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

import deepshah.library.model.LibraryBranch;


public class BranchListView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<LibraryBranch> list =  (List<LibraryBranch>) model.get("library_branch_model");
		System.out.println("List size is : " + list.size());
		Paragraph title = new Paragraph("Branch Details",FontFactory.getFont(FontFactory.TIMES_BOLD, 16,Font.BOLD));
				title.setAlignment(Element.ALIGN_CENTER);
				document.add(title);
         
	        Table table = new Table(3);
	         
	        // define font for table header row
	         
	        // write table row data
	        table.setPadding(5);
	          for (LibraryBranch result : list) {
	        	  
	  				table.addCell(String.valueOf(result.getBranch_id()));
	  				table.addCell(String.valueOf(result.getBranch_name()));
	  				table.addCell(String.valueOf(result.getAddress()));
	  			}
	  		document.add(table);
	     
		// to open the PDF in 100% zoom
		writer.setOpenAction(PdfAction.gotoLocalPage(1, new PdfDestination(
				PdfDestination.XYZ, 0, 10000, 1), writer));
	}
}
