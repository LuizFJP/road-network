/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import factory.CreateMonitorBlock;
import factory.CreateSemaphoreBlock;
import factory.FactoryBlock;
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
import models.Block;
import models.CreateMesh;
import myDefault.Spawner;

/**
 *
 * @author Igor Meurer
 */
public class MainView extends JFrame {

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
    FactoryBlock factoryBlock;
    Block[][] mesh = null;
    Spawner spawner;

    public MainView() {
        setSize(1400, 825);
        buildSettings();
//        getContentPane().setBackground(Color.decode("#D3CAC5"));
        this.setVisible(true);
    }

    public void buildSettings() {
        JPanel settings = new JPanel();
        settings.setSize(1300, 65);

        settings.setLayout(new GridBagLayout());
        GridBagConstraints componentsLayout = new GridBagConstraints();

        btInit = new JButton("Iniciar");
        btPause = new JButton("Pausar/Reiniciar");
        btTearDown = new JButton("Finalizar");
        cbMesh = new JComboBox();
        loadMeshFiles();
        cbSemaMonitor = new JComboBox();
        loadSemaMonitor();
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
        loadActions();
        add(settings, BorderLayout.NORTH);

    }

    public void loadActions() {
        btInit.addActionListener(((e) -> {
            String meshPath = (String) cbMesh.getSelectedItem();
            String semaOrMonitor = (String) cbSemaMonitor.getSelectedItem();

            var matrixFile = getClass().getClassLoader().getResource(meshPath).getPath();
            System.out.println(matrixFile);
            factoryBlock = "Semaforo".equals(semaOrMonitor)
                    ? new CreateSemaphoreBlock()
                    : new CreateMonitorBlock();

            try {
                mesh = CreateMesh.generateRoads(matrixFile, factoryBlock);
                System.out.println(mesh);
            } catch (IOException ex) {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }

            new MeshView(mesh);
            limitCars = Integer.parseInt(jtCar.getText());
            interval = Integer.parseInt(jtInterval.getText());
            var entries = CreateMesh.getEntries();
            var spawner = new Spawner(entries, limitCars, mesh, interval);
            var threadSpawner = new Thread(spawner);
            threadSpawner.start();
            this.spawner = spawner;
        }));

        btPause.addActionListener(((e) -> {
            spawner.pause();
        }));

        btTearDown.addActionListener(((e) -> {
            spawner.tearDown();
        }));

    }

    public void loadMeshFiles() {
        cbMesh.addItem("malha-exemplo-1.txt");
        cbMesh.addItem("malha-exemplo-2.txt");
        cbMesh.addItem("malha-exemplo-3.txt");
    }

    public void loadSemaMonitor() {
        cbSemaMonitor.addItem("Semaforo");
        cbSemaMonitor.addItem("Monitor");
    }

}
