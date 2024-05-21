package utils;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hirag
 */
public class RoundedPanelGradient extends JPanel {
    private Color colorStart;
    private Color colorEnd;
    private int cornerRadius;

    public RoundedPanelGradient(Color colorStart, Color colorEnd, int cornerRadius) {
        this.colorStart = colorStart;
        this.colorEnd = colorEnd;
        this.cornerRadius = cornerRadius;
        setOpaque(false); // Membuat panel tidak opaque sehingga kita bisa menggambar dengan transparansi
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();

        // Menghaluskan tepian
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Membuat gradien vertikal dari colorStart ke colorEnd
        GradientPaint gradientPaint = new GradientPaint(0, 0, colorStart, 0, height, colorEnd);
        graphics.setPaint(gradientPaint);

        // Menggambar rounded rectangle dengan gradien
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(0, 0, width, height, cornerRadius, cornerRadius);
        graphics.fill(roundedRectangle);

        graphics.dispose();
    }
}
