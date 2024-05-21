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
public class PanelGradientHorizontal extends JPanel {

    private Color colorStart;
    private Color colorEnd;

    public PanelGradientHorizontal(Color colorStart, Color colorEnd) {
        this.colorStart = colorStart;
        this.colorEnd = colorEnd;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Membuat gradien horizontal dari kanan ke kiri
        Graphics2D graphics = (Graphics2D) g.create();
        int width = getWidth();
        GradientPaint gradientPaint = new GradientPaint(width, 0, colorEnd, 0, 0, colorStart);
        graphics.setPaint(gradientPaint);
        graphics.fillRect(0, 0, width, getHeight());
        graphics.dispose();
    }

}
