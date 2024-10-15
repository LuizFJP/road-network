/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import models.Block;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import observe.Screen;

/**
 *
 * @author luizportela
 */
public class MeshView extends JFrame implements Screen {
    private Block[][] matrix;
    private JLabel[][] viewMatrix;

    // Constructor that accepts a Block[][] matrix
    public MeshView(Block[][] matrix) {
        this.matrix = matrix;
        this.setTitle("Matrix Board with Labels");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1920, 1080);

        JPanel boardPanel = new JPanel(new GridLayout(matrix.length, matrix[0].length));
        viewMatrix = new JLabel[matrix.length][matrix[0].length];
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                var square = createBlock(matrix[i][j]);
                boardPanel.add(square);
            }
        }
        this.add(boardPanel);
        this.setVisible(true);
    }
    
    @Override
    public void updateCarIcon(Block block){
        JLabel jLabel = viewMatrix[block.getLine()][block.getColumn()];
        if(block.hasCar()) {
            jLabel.setIcon(block.getCar().getImage());
        } else {
            jLabel.setIcon(block.getIcon());
        }
    }

    public JPanel createBlock(Block block) {
        JPanel square = new JPanel();
        square.setPreferredSize(new Dimension(50, 50));
        square.setLayout(new BorderLayout());

        ImageIcon image = getImageBlock(block);
        block.setIcon(image);
        block.setMeshView(this);

        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(image);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER); 
        imageLabel.setVerticalAlignment(SwingConstants.CENTER); 
        this.viewMatrix[block.getLine()][block.getColumn()] = imageLabel;
        square.add(imageLabel, BorderLayout.CENTER);
        square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        

        return square;
    }

    private ImageIcon getImageBlock(Block block) {
        switch (block.getDirection()) {
            case 1:
                return getImage("arrow-up.jpg");
            case 2:
                return getImage("arrow-right.jpg");
            case 3:
                return getImage("arrow-down.jpg");
            case 4:
                return getImage("arrow-left.jpg");
            default:
                return null; // No image for unknown directions
        }
    }
    
    

    private ImageIcon getImage(String imagePath) {
    try {
        var resource = getClass().getClassLoader().getResource(imagePath);
        if (resource == null) {
            throw new IOException("Arquivo não encontrado: " + imagePath);
        }
        BufferedImage image = ImageIO.read(resource);
        return new ImageIcon(image.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
}


}
