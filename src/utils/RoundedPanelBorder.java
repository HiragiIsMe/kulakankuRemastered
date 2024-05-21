/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author hirag
 */
public class RoundedPanelBorder extends JPanel {
    private Color borderColor;
    private int borderRadius;
    private int borderThickness;

    public RoundedPanelBorder(Color backgroundColor, Color borderColor, int borderRadius, int borderThickness) {
        this.setBackground(backgroundColor);
        this.borderColor = borderColor;
        this.borderRadius = borderRadius;
        this.borderThickness = borderThickness;
        this.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();
        
        // Anti-aliasing for smooth corners
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw the background with rounded corners
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width, height, borderRadius, borderRadius);
        g2d.setColor(getBackground());
        g2d.fill(roundedRectangle);
        
        // Draw the border with rounded corners
        if (borderThickness > 0) {
            g2d.setColor(borderColor);
            g2d.setStroke(new BasicStroke(borderThickness));
            g2d.draw(roundedRectangle);
        }
        
        g2d.dispose();
    }
}
