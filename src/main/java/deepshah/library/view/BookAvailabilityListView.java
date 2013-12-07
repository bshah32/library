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
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfAction;
import com.lowagie.text.pdf.PdfDestination;
import com.lowagie.text.pdf.PdfWriter;


public class BookAvailabilityListView  extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<Object[]> list =  (List<Object[]>) model.get("custom");
		System.out.println("List size is : " + list.size());
		Paragraph title = new Paragraph("Available Book Details",FontFactory.getFont(FontFactory.TIMES_BOLD, 16,Font.BOLD));
				title.setAlignment(Element.ALIGN_CENTER);
				document.add(title);
			Table table = new Table(7);
	        table.addCell("Book Id");
	        table.addCell("Book Name");
	        table.addCell("Branch Id");
	        table.addCell("Branch Name");
	        table.addCell("Number of Copies");
	        table.addCell("Books Issued");
	        table.addCell("Books Available");
	        table.setPadding(5);
	        for (Object[] result : list) {
				for(int i = 0; i<result.length;i++){
					table.addCell(String.valueOf(result[i]));
				}
			  }
	          
	  		document.add(table);
	     
		// to open the PDF in 100% zoom
		writer.setOpenAction(PdfAction.gotoLocalPage(1, new PdfDestination(
				PdfDestination.XYZ, 0, 10000, 1), writer));
	}
}
