package com.viajesglobal.controller;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.viajesglobal.dto.UsuarioDTO;
import com.viajesglobal.service.RegistroDAO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class VentasController {

    @Autowired
    private RegistroDAO registroDAO;

     @GetMapping("/reporteVentas")
    public void reporteVentas(HttpServletResponse response) throws IOException, com.lowagie.text.DocumentException {

         response.setContentType("application/pdf");
         response.setHeader("Content-Disposition", "attachment; filename=reporte_ventas.pdf");

         // Crear el documento PDF
         Document document = new Document();

         // Crear el escritor de PDF
         PdfWriter.getInstance(document, response.getOutputStream());

         // Abrir el documento para agregar contenido
         document.open();

         // Agregar un título al reporte
         document.add(new Paragraph("Reporte de Ventas"));

         document.add(com.lowagie.text.Chunk.NEWLINE);  // Salto de línea simple

         PdfPTable table = new PdfPTable(6);  // Aquí se definieron 6 columnas

         // Establecer anchos de las columnas
         float[] columnWidths = {2.5f, 2.5f, 3.5f, 2.4f, 2.5f, 2.8f};  // 6 anchos de columna
         table.setWidths(columnWidths);

         // 6 celdas
         table.addCell("ID Usuario");
         table.addCell("Nombre");
         table.addCell("Correo");
         table.addCell("Telefono");
         table.addCell("Contraseña");
         table.addCell("Pref. de Notificacion");

         // Definir un tamaño de fuente más pequeño para las celdas
         Font font = new Font(Font.ITALIC, 8, Font.NORMAL);

         List<UsuarioDTO> ventas = registroDAO.getUsuarios();

         for (UsuarioDTO venta : ventas) {
             table.addCell(new Phrase(String.valueOf(venta.getIdUsuario()), font));
             table.addCell(new Phrase(venta.getNombre(), font));
             table.addCell(new Phrase(venta.getCorreo(), font));
             table.addCell(new Phrase(venta.getTelefono(), font));
             table.addCell(new Phrase(venta.getContrasena(), font));
             table.addCell(new Phrase(venta.getPreferenciaNotificacion().toString(), font));
         }

         // Agregar la tabla al documento
         document.add(table);

         document.close();
     }
}
