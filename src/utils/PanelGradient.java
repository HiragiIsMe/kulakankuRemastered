/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author hirag
 */

public class PanelGradient extends JPanel {

    private Color colorStart;
    private Color colorEnd;

    public PanelGradient(Color colorStart, Color colorEnd) {
        this.colorStart = colorStart;
        this.colorEnd = colorEnd;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Membuat gradien vertikal dari colorStart ke colorEnd
        Graphics2D graphics = (Graphics2D) g.create();
        int height = getHeight();
        GradientPaint gradientPaint = new GradientPaint(0, 0, colorStart, 0, height, colorEnd);
        graphics.setPaint(gradientPaint);
        graphics.fillRect(0, 0, getWidth(), height);
        graphics.dispose();
    }
}
