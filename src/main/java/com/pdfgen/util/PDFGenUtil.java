package com.pdfgen.util;


import com.pdfgen.model.InvoiceRequest;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;


import java.io.*;

@Component
public class PDFGenUtil {


    @Autowired
    private SpringTemplateEngine templateEngine;

    public void generateInvoicePdf(InvoiceRequest invoiceRequest, File pdfFile) {

            try (OutputStream os = new FileOutputStream(pdfFile)) {
                // Generating HTML with Thymeleaf
                Context context = new Context();
                context.setVariable("invoice", invoiceRequest);

                String htmlContent = templateEngine.process("invoice", context);

                // Converting HTML to PDF with Flying Saucer
                ITextRenderer renderer = new ITextRenderer();
                System.out.println(htmlContent);

                renderer.setDocumentFromString(htmlContent);
                System.out.println(htmlContent);
                renderer.layout();

                renderer.createPDF(os);
            } catch (Exception e) {
                throw new RuntimeException("Failed to generate PDF", e);
            }
        }
}
