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
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

/**
 *
 * @author hirag
 */
public class RoundedTextField extends JTextField {
    private int cornerRadius;
    private int borderThickness;

    public RoundedTextField(int columns, int cornerRadius, int borderThickness) {
        super(columns);
        this.cornerRadius = cornerRadius;
        this.borderThickness = borderThickness;
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(borderThickness, borderThickness, borderThickness, borderThickness));
    
    }

    protected void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g.create();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        super.paintComponent(graphics);
        graphics.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D graphics = (Graphics2D) g.create();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setStroke(new BasicStroke(borderThickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        graphics.setColor(new Color(127, 39, 255));
        graphics.drawRoundRect(borderThickness / 2, borderThickness / 2, getWidth() - borderThickness - 1, getHeight() - borderThickness - 1, cornerRadius, cornerRadius);
        graphics.dispose();
    }
}


