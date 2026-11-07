/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.table.TableModel;

/**
 *
 * @author HP
 */
public class PdfLaporanService {
    // Membuat PDF dari isi TableModel (sama persis dengan yang tampil di jTable6)
    public void buat(TableModel model, File file, String judul, String subjudul) throws Exception {
        Document doc = new Document(PageSize.A4.rotate(), 36, 36, 36, 36);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            PdfWriter.getInstance(doc, fos);
            doc.open();

            Font fJudul = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Font fSub   = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.DARK_GRAY);
            Font fHead  = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9, Color.WHITE);
            Font fIsi   = FontFactory.getFont(FontFactory.HELVETICA, 9);

            doc.add(new Paragraph(judul, fJudul));
            Paragraph sub = new Paragraph(subjudul, fSub);
            sub.setSpacingAfter(12);
            doc.add(sub);

            int kol = model.getColumnCount();
            PdfPTable tabel = new PdfPTable(kol);
            tabel.setWidthPercentage(100);
            tabel.setHeaderRows(1);

            for (int c = 0; c < kol; c++) {
                PdfPCell h = new PdfPCell(new Phrase(model.getColumnName(c), fHead));
                h.setBackgroundColor(new Color(14, 165, 233));
                h.setHorizontalAlignment(Element.ALIGN_CENTER);
                h.setPadding(6);
                tabel.addCell(h);
            }
            for (int r = 0; r < model.getRowCount(); r++) {
                for (int c = 0; c < kol; c++) {
                    Object v = model.getValueAt(r, c);
                    PdfPCell sel = new PdfPCell(new Phrase(v == null ? "-" : String.valueOf(v), fIsi));
                    sel.setPadding(5);
                    if (r % 2 == 1) sel.setBackgroundColor(new Color(242, 243, 255));
                    tabel.addCell(sel);
                }
            }
            doc.add(tabel);
            doc.close();
        }
    }
}
