package com.pdfgen.service;

import com.pdfgen.model.InvoiceRequest;
import com.pdfgen.repository.PDFRepository;
import com.pdfgen.util.PDFGenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class PDFService {
    @Autowired
    private PDFRepository pdfRepository;

    @Autowired
    private PDFGenUtil pdfGeneratorUtil;

    public String generatePdf(InvoiceRequest invoiceRequest) {
        String pdfFileName = "invoice_" + invoiceRequest.hashCode() + ".pdf";
        File pdfFile = pdfRepository.getFile(pdfFileName);

        if (!pdfFile.exists()) {
            pdfGeneratorUtil.generateInvoicePdf(invoiceRequest, pdfFile);
        }
        return pdfFile.getAbsolutePath();
    }




    public File getPdf(String fileName) {
        return pdfRepository.getFile(fileName);
    }

    public byte[] readFileToByteArray(File file) {
        return pdfRepository.readFileToByteArray(file);
    }
}
