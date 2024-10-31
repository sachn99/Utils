package com.pdfgen.controller;

import com.pdfgen.model.InvoiceRequest;
import com.pdfgen.service.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
@RestController
@RequestMapping("/api/pdf")
public class InvoiceController {

    @Autowired
    private PDFService pdfService;

    @PostMapping("/generate")
    public ResponseEntity<String> generatePdf(@RequestBody InvoiceRequest invoiceRequest) {
        String pdfFilePath = pdfService.generatePdf(invoiceRequest);
        return ResponseEntity.ok("PDF generated and stored at: " + pdfFilePath);
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadPdf(@RequestParam String fileName) {
        File pdfFile = pdfService.getPdf(fileName);

        byte[] pdfData = pdfService.readFileToByteArray(pdfFile);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfData);
    }
}

