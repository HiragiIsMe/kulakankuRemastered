/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author hirag
 */
public class CustomButton extends JButton {
    
    public CustomButton(String text) {
        super(text);
        setOpaque(false);
        setFocusPainted(false);
        setBorderPainted(false); // Tidak menampilkan border
        setContentAreaFilled(false); // Tidak mengisi area konten dengan warna
        setForeground(Color.BLACK); // Warna teks
        setFont(new Font("Arial", Font.PLAIN, 14)); // Atur font sesuai kebutuhan Anda
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g.create();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Menggambar latar belakang dengan sudut yang dibulatkan
        graphics.setColor(getBackground());
        graphics.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, 20, 20));

        super.paintComponent(graphics);
        graphics.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Tidak melakukan apa-apa untuk menghindari gambar border bawaan
    }
}
