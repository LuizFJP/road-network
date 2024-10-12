/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import br.udesc.t2.dsd.swing.models.Block;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Image;

/**
 *
 * @author luizportela
 */
public class MeshView extends JFrame {

    // Constructor that accepts a Block[][] matrix
    public MeshView(Block[][] matrix) {
        this.setTitle("Matrix Board with Labels");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);

        JPanel boardPanel = new JPanel(new GridLayout(matrix.length, matrix[0].length));

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                var square = createBlock(matrix[i][j]);
                boardPanel.add(square);
            }
        }

        this.add(boardPanel);
        this.setVisible(true);
    }

    private JPanel createBlock(Block block) {
        JPanel square = new JPanel();
        square.setPreferredSize(new Dimension(50, 50));
        square.setLayout(new BorderLayout());

        ImageIcon image = getImageBlock(block);

        JLabel imageLabel = new JLabel(image);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER); 
        imageLabel.setVerticalAlignment(SwingConstants.CENTER); 
        square.add(imageLabel, BorderLayout.CENTER);
        square.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return square;
    }

    private ImageIcon getImageBlock(Block block) {
        switch (block.getDirection()) {
            case 1:
                return getImage("arrow-up.jpg"); // Replace with actual paths
            case 2:
                return getImage("arrow-right.jpg"); // Replace with actual paths
            case 3:
                return getImage("arrow-down.jpg"); // Replace with actual paths
            case 4:
                return getImage("arrow-left.jpg"); // Replace with actual paths
            default:
                return null; // No image for unknown directions
        }
    }

    private ImageIcon getImage(String imagePath) {
        BufferedImage backgroundImage;
        try {
            BufferedImage image = ImageIO.read(new File(getClass().getClassLoader().getResource(imagePath).getPath()));
            return new ImageIcon(image.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
