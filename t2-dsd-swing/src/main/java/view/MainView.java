/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import myDefault.Spawner;
import factory.CreateMonitorBlock;
import factory.CreateSemaphoreBlock;
import factory.FactoryBlock;
import java.awt.BorderLayout;
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

/**
 *
 * @author Igor Meurer
 */
public class MainView extends JFrame {
    
    GridBagConstraints settingsLayoutConstraints;
    
    ArrayList<JComponent> settingsComp = new ArrayList<>();
    JButton btInit;      //Controle para iniciar e finaliza a op
    JLabel lblCar;           //Label de indicação: quantidade de carros
    JTextField jtCar;
    JComboBox<String> cbMesh;
    JComboBox<String> cbSemaMonitor;
    
    FactoryBlock factoryBlock;
    Block[][] mesh = null;

    
    public MainView(){
        setSize(1400, 825);
        buildSettings();
//        getContentPane().setBackground(Color.decode("#D3CAC5"));
        this.setVisible(true);
    }
    
    public void buildSettings(){
        JPanel settings = new JPanel();
        settings.setSize(1300, 65);
        
        settings.setLayout(new GridBagLayout());
        GridBagConstraints componentsLayout = new GridBagConstraints();

        btInit = new JButton("Iniciar");
        cbMesh = new JComboBox();
        loadMeshFiles();
        cbSemaMonitor = new JComboBox();
        loadSemaMonitor();      
        JPanel carPanel = new JPanel(new BorderLayout());
        lblCar = new JLabel("Quantidade de carros: ");
        jtCar = new JTextField(10);
        carPanel.add(lblCar, BorderLayout.WEST);
        carPanel.add(jtCar, BorderLayout.CENTER);
        
        settingsComp.add(btInit);
        settingsComp.add(cbMesh);
        settingsComp.add(cbSemaMonitor);
        settingsComp.add(carPanel);
        
        componentsLayout.gridx = 0;
        componentsLayout.gridy = 0;
        
        for(int r = 0; r < settingsComp.size(); r++){
            componentsLayout.gridx = r;
            componentsLayout.weightx = 1.0;
            settings.add(settingsComp.get(r), componentsLayout);
        }
//        settings.setBackground(Color.decode("#D3CAC5"));
        loadActions();
        add(settings, BorderLayout.NORTH);
       
    }
    
    public void loadActions(){
        btInit.addActionListener(((e) -> {
            String meshPath = (String) cbMesh.getSelectedItem();
            String semaOrMonitor = (String) cbSemaMonitor.getSelectedItem();
            
            var matrixFile = "/Users/Igor Meurer/Documents/Faculdade/dsd_trab2/road-network/t2-dsd-swing/src/main/resources/" + meshPath;
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
            
        var entries = CreateMesh.getEntries();
        var spawner = new Spawner(entries, 100, mesh);
        var threadSpawner = new Thread(spawner);
        threadSpawner.start();
        
        new MeshView(mesh);
            
        }));
    }
    
    public void loadMeshFiles(){
        cbMesh.addItem("malha-exemplo-1.txt");
        cbMesh.addItem("malha-exemplo-2.txt");
        cbMesh.addItem("malha-exemplo-3.txt");
    }
    
    public void loadSemaMonitor(){
        cbSemaMonitor.addItem("Semaforo");
        cbSemaMonitor.addItem("Monitor");
    }
    
}
