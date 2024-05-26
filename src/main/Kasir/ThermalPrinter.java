/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.Kasir;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.*;
import javax.imageio.ImageIO;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PrintQuality;
import javax.swing.ImageIcon;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

public class ThermalPrinter {
    public static void printReceipt(File file) throws PrinterException {
//        try {
//            // Menggunakan PDFBox untuk memuat dokumen PDF
//            PDDocument document = PDDocument.load(new FileInputStream(file));
//            PDFRenderer pdfRenderer = new PDFRenderer(document);
//
//            // Mencari layanan printer yang tersedia
//            PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
//
//            if (printService != null) {
//                // Membuat PrintJob
//                PrinterJob job = PrinterJob.getPrinterJob();
//                job.setPrintService(printService);
//
//                // Menyiapkan atribut cetak
//                PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
//                attributes.add(new Copies(1));
//                attributes.add(new MediaPrintableArea(0, 0, 58, 3276, MediaPrintableArea.MM)); // Ukuran kertas thermal 58mm
//                attributes.add(OrientationRequested.PORTRAIT);
//                attributes.add(PrintQuality.DRAFT);
//
//                // Mengatur Printable
//                job.setPrintable((Graphics g, PageFormat pageFormat, int pageIndex) -> {
//                    if (pageIndex >= document.getNumberOfPages()) {
//                        return Printable.NO_SUCH_PAGE;
//                    }
//                    Graphics2D g2d = (Graphics2D) g;
//                    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
//
//                    // Skala konten agar sesuai dengan printer thermal 58mm
//                    float scaleX = (float) pageFormat.getImageableWidth() / document.getPage(pageIndex).getMediaBox().getWidth();
//                    float scaleY = (float) pageFormat.getImageableHeight() / document.getPage(pageIndex).getMediaBox().getHeight();
//                    float scale = Math.min(scaleX, scaleY);
//                    g2d.scale(scale, scale);
//
//                    try {
//                        pdfRenderer.renderPageToGraphics(pageIndex, g2d);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    return Printable.PAGE_EXISTS;
//                });
//
//                // Menampilkan dialog print (opsional)
//                if (job.printDialog(attributes)) {
//                    // Mencetak dokumen
//                    job.print(attributes);
//                }
//            } else {
//                System.out.println("Tidak ada layanan printer yang tersedia.");
//            }
//
//            // Menutup dokumen PDF
//            document.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
            try (PDDocument document = PDDocument.load(file)) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);

            for (int i = 0; i < document.getNumberOfPages(); i++) {
                BufferedImage image = pdfRenderer.renderImageWithDPI(i, 300); // Render at 300 DPI
                File imageFile = new File("page_" + (i + 1) + ".png");
                ImageIO.write(image, "PNG", imageFile);

                // Print the image
                printImage(imageFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void printImage(File imageFile) throws PrinterException {
        try {
            // Load the image
            BufferedImage image = ImageIO.read(imageFile);

            // Find available print services
            PrintService printService = PrintServiceLookup.lookupDefaultPrintService();

            if (printService != null) {
                // Create a PrinterJob
                PrinterJob job = PrinterJob.getPrinterJob();
                job.setPrintService(printService);

                // Prepare print attributes
                PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
                attributes.add(new Copies(1));
                attributes.add(new MediaPrintableArea(0, 0, 58, 3276, MediaPrintableArea.MM)); // Thermal printer paper size
                attributes.add(OrientationRequested.PORTRAIT);
                attributes.add(PrintQuality.DRAFT);

                // Set the Printable
                job.setPrintable((Graphics g, PageFormat pageFormat, int pageIndex) -> {
                    if (pageIndex != 0) {
                        return Printable.NO_SUCH_PAGE;
                    }

                    Graphics2D g2d = (Graphics2D) g;
                    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

                    // Scale the image to fit the page
                    double scaleX = pageFormat.getImageableWidth() / (double) image.getWidth();
                    double scaleY = pageFormat.getImageableHeight() / (double) image.getHeight();
                    double scale = Math.min(scaleX, scaleY);
                    g2d.scale(scale, scale);

                    // Draw the image
                    g.drawImage(image, 0, 0, null);

                    return Printable.PAGE_EXISTS;
                });

                // Display the print dialog (optional)
                if (job.printDialog(attributes)) {
                    // Print the document
                    job.print(attributes);
                }
            } else {
                System.out.println("No printer services available.");
            }
        } catch (IOException | PrinterException e) {
            e.printStackTrace();
        }
    }
//    static class PDFPrintable implements Printable {
//        private PdfReader pdfReader;
//
//        public PDFPrintable(PdfReader pdfReader) {
//            this.pdfReader = pdfReader;
//        }
//
//        @Override
//        public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
//            Float totalAmount = 0.0F;
//            Float change = 0.0F;
//            String readFile = System.getProperty("user.dir") + "/src/main/resources/images/my pic.png";
//            ImageIcon icon = new ImageIcon(readFile);
//            int result = NO_SUCH_PAGE;
//            if (pageIndex == 0) {
//
//            Graphics2D g2d = (Graphics2D) g;
//            double width = pageFormat.getImageableWidth();
//            g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());
//
//            FontMetrics metrics = g2d.getFontMetrics(new Font("Arial", Font.BOLD, 7));
//            try {
//                int y = 15;
//                int yShift = 10;
//                int headerRectHeight = 15;
//                g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());
//                double wh = pageFormat.getImageableWidth();
//                double ht = pageFormat.getImageableHeight();
//                g2d.drawImage(null, 0, 0, (int) wh, (int) ht, null);
//
//                g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));
//                y += yShift + 30;
//                g2d.drawString("------------------------------", 10, y);
//                y += yShift;
//                g2d.drawString(" Java Programming with Aldrin", 10, y);
//                y += yShift;
//                g2d.drawString("  Rufino St.,Legaspi Village ", 10, y);
//                y += yShift;
//                g2d.drawString("   Makati City, Metro Manila ", 10, y);
//                y += yShift;
//                y += yShift;
//                y += yShift;
//                g2d.drawString("------------------------------", 10, y);
//                y += headerRectHeight;
//
//                g2d.drawString(" Item                 Price  ", 10, y);
//                y += yShift;
//                g2d.drawString("------------------------------", 10, y);
//                y += headerRectHeight;
//
//                for (Item item : createItemList()) {
//                    g2d.drawString(" " + item.getItem() + "                    ", 10, y);
//                    y += yShift;
//                    g2d.drawString("     " + item.getQty() + " x " + item.getPrice(), 5, y);
//                    g2d.drawString(String.valueOf(df.format((Float.parseFloat(item.getQty())) * (Float.parseFloat(item.getPrice())))), 130, y);
//                    y += yShift;
//                    totalAmount = totalAmount + (Float.parseFloat(item.getQty())) * (Float.parseFloat(item.getPrice()));
//
//                }
//                Float cash = Float.parseFloat(jTextFieldCash.getText());
//
//                g2d.drawString("------------------------------", 10, y);
//                y += yShift;
//                g2d.drawString(" Total   :            " + String.valueOf(df.format(totalAmount)) + "   ", 10, y);
//                y += yShift;
//                g2d.drawString("------------------------------", 10, y);
//                y += yShift;
//                g2d.drawString(" Cash    :            " + String.valueOf(df.format(cash)) + "   ", 10, y);
//                y += yShift;
//                g2d.drawString("------------------------------", 10, y);
//                y += yShift;
//                g2d.drawString(" Change  :            " + String.valueOf(df.format(Float.parseFloat(jTextFieldCash.getText()) - totalAmount)) + "   ", 10, y);
//                y += yShift;
//                y += yShift;
//                y += yShift;
//
//                g2d.drawString("******************************", 10, y);
//                y += yShift;
//                g2d.drawString("    Terima , COME AGAIN!!    ", 10, y);
//                y += yShift;
//                g2d.drawString("******************************", 10, y);
//                y += yShift;
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            result = PAGE_EXISTS;
//        }
//        return result;
//        }
//    }
}




