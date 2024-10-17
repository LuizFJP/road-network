/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import factory.RobsonCreateMonitorBlock;
import factory.RobsonCreateSemaphoreBlock;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import models.RobsonBlock;
import models.RobsonCreateMesh;
import myDefault.RobsonSpawner;
import factory.RobsonFactoryBlock;

/**
 *
 * @author Igor Meurer
 */
public class RobsonMainView extends JFrame {

    GridBagConstraints settingsLayoutConstraints;

    ArrayList<JComponent> settingsComp = new ArrayList<>();
    JButton btInit;
    JButton btPause;
    JButton btTearDown;
    JLabel lblCar;
    JTextField jtCar;
    JLabel lblInterval;
    JTextField jtInterval;
    JComboBox<String> cbMesh;
    JComboBox<String> cbSemaMonitor;
    int limitCars;
    int interval;
    RobsonFactoryBlock factoryBlock;
    RobsonBlock[][] mesh = null;
    RobsonSpawner spawner;

    public RobsonMainView() {
        setSize(1400, 825);
        robsonBuildSettings();
//        getContentPane().setBackground(Color.decode("#D3CAC5"));
        this.setVisible(true);
    }

    public void robsonBuildSettings() {
        JPanel settings = new JPanel();
        settings.setSize(1300, 65);

        settings.setLayout(new GridBagLayout());
        GridBagConstraints componentsLayout = new GridBagConstraints();

        btInit = new JButton("Iniciar");
        btPause = new JButton("Pausar/Reiniciar");
        btTearDown = new JButton("Finalizar");
        cbMesh = new JComboBox();
        robsonLoadMeshFiles();
        cbSemaMonitor = new JComboBox();
        robsonLoadSemaMonitor();
        JPanel carPanel = new JPanel(new BorderLayout());
        lblCar = new JLabel("Quantidade de carros: ");
        jtCar = new JTextField(10);
        lblInterval = new JLabel("Intervalo de carros(ms): ");
        jtInterval = new JTextField(10);
        JPanel carInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        carInputPanel.add(lblCar);
        carInputPanel.add(jtCar);

        JPanel intervalInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        intervalInputPanel.add(lblInterval);
        intervalInputPanel.add(jtInterval);
        carPanel.add(carInputPanel, BorderLayout.NORTH);
        carPanel.add(intervalInputPanel, BorderLayout.CENTER);

        settingsComp.add(btInit);
        settingsComp.add(cbMesh);
        settingsComp.add(cbSemaMonitor);
        settingsComp.add(btPause);
        settingsComp.add(btTearDown);
        settingsComp.add(carPanel);

        componentsLayout.gridx = 0;
        componentsLayout.gridy = 0;

        for (int r = 0; r < settingsComp.size(); r++) {
            componentsLayout.gridx = r;
            componentsLayout.weightx = 1.0;
            settings.add(settingsComp.get(r), componentsLayout);
        }
//        settings.setBackground(Color.decode("#D3CAC5"));
        robsonLoadActions();
        add(settings, BorderLayout.NORTH);

    }

    public void robsonLoadActions() {
        btInit.addActionListener(((e) -> {
            String meshPath = (String) cbMesh.getSelectedItem();
            String semaOrMonitor = (String) cbSemaMonitor.getSelectedItem();

            var matrixFile = getClass().getClassLoader().getResource(meshPath).getPath();
            System.out.println(matrixFile);
            factoryBlock = "Semaforo".equals(semaOrMonitor)
                    ? new RobsonCreateSemaphoreBlock()
                    : new RobsonCreateMonitorBlock();

            try {
                mesh = RobsonCreateMesh.robsonGenerateRoads(matrixFile, factoryBlock);
                System.out.println(mesh);
            } catch (IOException ex) {
                Logger.getLogger(RobsonMainView.class.getName()).log(Level.SEVERE, null, ex);
            }

            RobsonMeshView meshView = new RobsonMeshView(mesh);
            this.add(meshView, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            
            limitCars = Integer.parseInt(jtCar.getText());
            interval = Integer.parseInt(jtInterval.getText());
            var entries = RobsonCreateMesh.robsonGetEntries();
            var spawner = new RobsonSpawner(entries, limitCars, mesh, interval);
            var threadSpawner = new Thread(spawner);
            threadSpawner.start();
            this.spawner = spawner;
        }));

        btPause.addActionListener(((e) -> {
            spawner.robsonPause();
        }));

        btTearDown.addActionListener(((e) -> {
            spawner.robsonTearDown();
        }));

    }

    public void robsonLoadMeshFiles() {
        cbMesh.addItem("malha-exemplo-1.txt");
        cbMesh.addItem("malha-exemplo-2.txt");
        cbMesh.addItem("malha-exemplo-3.txt");
    }

    public void robsonLoadSemaMonitor() {
        cbSemaMonitor.addItem("Semaforo");
        cbSemaMonitor.addItem("Monitor");
    }

}
