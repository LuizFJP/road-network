/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import models.RobsonBlock;
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
import observe.RobsonScreen;

/**
 *
 * @author luizportela
 */
public class RobsonMeshView extends JPanel implements RobsonScreen {
    private RobsonBlock[][] matrix;
    private JLabel[][] viewMatrix;

    public RobsonMeshView(RobsonBlock[][] matrix) {
        this.matrix = matrix;
        setLayout(new BorderLayout());
        this.setVisible(true);

        JPanel boardPanel = new JPanel(new GridLayout(matrix.length, matrix[0].length));
        viewMatrix = new JLabel[matrix.length][matrix[0].length];
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                var square = robsonCreateBlock(matrix[i][j]);
                boardPanel.add(square);
            }
        }
        this.add(boardPanel);
        this.setVisible(true);
    }
    
    @Override
    public void robsonUpdateCarIcon(RobsonBlock block){
        JLabel jLabel = viewMatrix[block.robsonGetLine()][block.robsonGetColumn()];
        if(block.robsonHasCar()) {
            jLabel.setIcon(block.robsonGetCar().robsonGetImage());
        } else {
            jLabel.setIcon(block.robsonGetIcon());
        }
    }

    public JPanel robsonCreateBlock(RobsonBlock block) {
        JPanel square = new JPanel();
        square.setPreferredSize(new Dimension(50, 50));
        square.setLayout(new BorderLayout());

        ImageIcon image = robsonGetImageBlock(block);
        block.robsonSetIcon(image);
        block.robsonSetMeshView(this);

        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(image);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER); 
        imageLabel.setVerticalAlignment(SwingConstants.CENTER); 
        this.viewMatrix[block.robsonGetLine()][block.robsonGetColumn()] = imageLabel;
        square.add(imageLabel, BorderLayout.CENTER);
        square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        

        return square;
    }

    private ImageIcon robsonGetImageBlock(RobsonBlock block) {
        switch (block.robsonGetDirection()) {
            case 1:
                return robosnGetImage("arrow-up.jpg");
            case 2:
                return robosnGetImage("arrow-right.jpg");
            case 3:
                return robosnGetImage("arrow-down.jpg");
            case 4:
                return robosnGetImage("arrow-left.jpg");
            case 0:
                return null;
            default:
                return robosnGetImage("cross.png");
        }
    }
    ;
    

    private ImageIcon robosnGetImage(String imagePath) {
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
