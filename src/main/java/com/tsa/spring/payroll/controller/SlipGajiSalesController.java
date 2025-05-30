package com.tsa.spring.payroll.controller;

import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/slip-gaji")
public class SlipGajiSalesController {

    @Autowired
    private TemplateEngine templateEngine;
    @GetMapping("/sales")
    public void slipgajiSales(
        HttpServletResponse response){
        try{

            Context context = new Context();
            String htmlContent = templateEngine.process("slip_gaji_sales", context);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=lembur-landscape.pdf");

            OutputStream outputStream = response.getOutputStream();
            ITextRenderer renderer = new ITextRenderer();
            
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(outputStream);

            response.flushBuffer();
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
