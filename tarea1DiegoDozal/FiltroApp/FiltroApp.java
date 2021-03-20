import javax.swing.*;
import javax.swing.text.AttributeSet.ColorAttribute;

import jdk.nashorn.api.tree.Tree;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.*; 
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;




/**
 * Clase que englboa tanto a la parte grafica como logica de una aplicacion que aplica 
 * filtros dependiendo de la entrada del usuario. 
 */
public class FiltroApp extends JFrame{

    private JPanel panelPrincipal; 
    private JLabel labelBusqueda; 
    private JLabel labelSeleccionFiltro; 
    private JLabel labelImagenOriginal;
    private JLabel labelImagenFiltro; 
    private JLabel labelBrillo; 
    private JLabel labelMosaico; 
    private JButton buttonBusqueda; 
    private JButton buttonAplicar; 
    private JButton buttonAplicarBrillo; 
    private JButton buttonAplicarMosaico; 
    private JComboBox <String>comboboxFiltros;  
    //private JScrollBar scrollBarBrillo; 
    private JTextField textFieldBrillo; 
    private JTextField textFieldAnchoMosaico; 
    private JTextField textFIeldAltoMosaico; 
    
    private ImageIcon imagenOriginal;
    private File file; 
    
    ///Para la modificacion de la imagen 


    public FiltroApp(){
        this.inicializarComponente();
    }


    private void inicializarComponente(){
        //Inicializa JFrame 
        
        this.setBounds(300, 200, 1400, 600);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        //Coloca los otros componentes 
        this.colocarPaneles();
        this.colocarLabels();
        this.colocarBotones();
        this.colocarEventos();
        this.colocarCombobox();
        this.colocarTextField();
        
    }


    private void colocarPaneles(){

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        this.getContentPane().add(panelPrincipal);
    }

 

     private void colocarLabels(){
        labelBusqueda = new JLabel("Seleccione una imagen"); 
        labelBusqueda.setBounds(30, 10, 200, 20);
        panelPrincipal.add(labelBusqueda);

        labelSeleccionFiltro = new JLabel("Seleccione un Filtro");
        labelSeleccionFiltro.setBounds(30, 70, 200, 20);
        panelPrincipal.add(labelSeleccionFiltro);

        labelImagenOriginal = new JLabel();
        labelImagenOriginal.setBounds(400, 20, 380, 480);
        panelPrincipal.add(labelImagenOriginal);
        

        labelImagenFiltro = new JLabel();
        labelImagenFiltro.setBounds(850, 20, 380, 480);
        panelPrincipal.add(labelImagenFiltro);

        labelBrillo = new JLabel("Ingrese el Brillo en Int");
        labelBrillo.setBounds(20, 330, 200, 20);
        labelBrillo.setVisible(false);
        panelPrincipal.add(labelBrillo);

        labelMosaico = new JLabel("Ingrese las dimensiones del Mosaico");
        labelMosaico.setBounds(20, 420, 300, 20);
        labelMosaico.setVisible(false);
        panelPrincipal.add(labelMosaico);

    }

    private void colocarBotones(){
        buttonBusqueda = new JButton("Buscar");
        buttonBusqueda.setBounds(30, 40, 200, 20);
        panelPrincipal.add(buttonBusqueda);


        buttonAplicar = new JButton("Aplicar");
        buttonAplicar.setBounds(20, 300, 200, 20);
        panelPrincipal.add(buttonAplicar);

        buttonAplicarBrillo = new JButton("Aplicar Brillo");
        buttonAplicarBrillo.setBounds(20, 390, 200, 20);
        panelPrincipal.add(buttonAplicarBrillo);
        buttonAplicarBrillo.setVisible(false);

        buttonAplicarMosaico = new JButton("Aplicar Mosaico");
        buttonAplicarMosaico.setBounds(20, 510, 200, 20);
        panelPrincipal.add(buttonAplicarMosaico);
        buttonAplicarMosaico.setVisible(false);

    }

    private void colocarCombobox(){
       
        comboboxFiltros = new JComboBox<String>();   
        comboboxFiltros.addItem("Filtro Gris 1");
        comboboxFiltros.addItem("Filtro Gris 2");
        comboboxFiltros.addItem("Filtro Gris 3");
        comboboxFiltros.addItem("Filtro Gris 4");
        comboboxFiltros.addItem("Filtro Gris 5");
        comboboxFiltros.addItem("Filtro Gris 6");
        comboboxFiltros.addItem("Filtro Gris 7");
        comboboxFiltros.addItem("Filtro Gris 8");
        comboboxFiltros.addItem("Filtro Gris 9");
        comboboxFiltros.addItem("Brillo"); //
        comboboxFiltros.addItem("Mosaico");
        comboboxFiltros.addItem("Alto Contraste");
        comboboxFiltros.addItem("Inverso");
        comboboxFiltros.setBounds(30, 90, 200, 20);
        panelPrincipal.add(comboboxFiltros);  
    }


    private void colocarTextField(){
        textFieldBrillo = new JTextField();
        textFieldBrillo.setBounds(20, 360, 200, 20);
        panelPrincipal.add(textFieldBrillo);
        textFieldBrillo.setVisible(false);


        textFieldAnchoMosaico = new JTextField();
        textFieldAnchoMosaico.setBounds(20,450,200,20);
        panelPrincipal.add(textFieldAnchoMosaico);
        textFieldAnchoMosaico.setVisible(false);

        textFIeldAltoMosaico = new JTextField();
        textFIeldAltoMosaico.setBounds(20,480,200,20);
        panelPrincipal.add(textFIeldAltoMosaico);
        textFIeldAltoMosaico.setVisible(false);

    }

    private void mostrarComponentesBrilla(){
        labelBrillo.setVisible(true);
        textFieldBrillo.setVisible(true);
        buttonAplicarBrillo.setVisible(true);
        
    }

    private void mostrarComponentesMosaico(){
        labelMosaico.setVisible(true);
        textFIeldAltoMosaico.setVisible(true);
        textFieldAnchoMosaico.setVisible(true);
        buttonAplicarMosaico.setVisible(true);
    }



/*     private void colocarScrollBar(){
        scrollBarBrillo = new JScrollBar(0,0,1,0,100);
        scrollBarBrillo.setBounds(20, 360, 200, 20);
        panelPrincipal.add(scrollBarBrillo);

        labelBrillo.setVisible(true);
    }
 */

    public void colocarEventos(){

            //Evento de busqueda 
            buttonBusqueda.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   JFileChooser fc = new JFileChooser();
                   int returnVal = fc.showOpenDialog(panelPrincipal);

                   if(returnVal == JFileChooser.APPROVE_OPTION){
                       file = fc.getSelectedFile(); 
                       try{
                        BufferedImage img = ImageIO.read(file);
                        Image imgScale = img.getScaledInstance(labelImagenOriginal.getWidth(), labelImagenOriginal.getHeight(), Image.SCALE_SMOOTH);
                        imagenOriginal = new ImageIcon(imgScale);
                        labelImagenOriginal.setIcon(imagenOriginal);
                    } catch (Exception ex){}   
                   }
               }
            });
            

            buttonAplicar.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e){
                   
                    String filtro = (String)comboboxFiltros.getSelectedItem();

                    if(filtro.equals("Filtro Gris 1")){

                        try{
                            LectorImagen lector = new LectorImagen(file.getAbsolutePath());
                            lector.filtro_gris_(1);
                            Image imgScale = lector.getImagenFiltrada().getScaledInstance(labelImagenFiltro.getWidth(), labelImagenFiltro.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon nuevaImagen = new ImageIcon(imgScale);
                            labelImagenFiltro.setIcon(nuevaImagen);
                        } catch (Exception ex){} 
                    }
                    if(filtro.equals("Filtro Gris 2")){
                        try{
                            LectorImagen lector = new LectorImagen(file.getAbsolutePath());
                            lector.filtro_gris_(2);
                            Image imgScale = lector.getImagenFiltrada().getScaledInstance(labelImagenFiltro.getWidth(), labelImagenFiltro.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon nuevaImagen = new ImageIcon(imgScale);
                            labelImagenFiltro.setIcon(nuevaImagen);
                        } catch (Exception ex){} 
                        
                    }
                    if(filtro.equals("Filtro Gris 3")){
                        try{
                            LectorImagen lector = new LectorImagen(file.getAbsolutePath());
                            lector.filtro_gris_(3);
                            Image imgScale = lector.getImagenFiltrada().getScaledInstance(labelImagenFiltro.getWidth(), labelImagenFiltro.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon nuevaImagen = new ImageIcon(imgScale);
                            labelImagenFiltro.setIcon(nuevaImagen);
                        } catch (Exception ex){} 
                        
                    }
                    if(filtro.equals("Filtro Gris 4")){
                        try{
                            LectorImagen lector = new LectorImagen(file.getAbsolutePath());
                            lector.filtro_gris_(4);
                            Image imgScale = lector.getImagenFiltrada().getScaledInstance(labelImagenFiltro.getWidth(), labelImagenFiltro.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon nuevaImagen = new ImageIcon(imgScale);
                            labelImagenFiltro.setIcon(nuevaImagen);
                        } catch (Exception ex){} 
                    }
                    if(filtro.equals("Filtro Gris 5")){
                        try{
                            LectorImagen lector = new LectorImagen(file.getAbsolutePath());
                            lector.filtro_gris_(5);
                            Image imgScale = lector.getImagenFiltrada().getScaledInstance(labelImagenFiltro.getWidth(), labelImagenFiltro.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon nuevaImagen = new ImageIcon(imgScale);
                            labelImagenFiltro.setIcon(nuevaImagen);
                        } catch (Exception ex){} 
                        
                    }
                    if(filtro.equals("Filtro Gris 6")){
                        try{
                            LectorImagen lector = new LectorImagen(file.getAbsolutePath());
                            lector.filtro_gris_(6);
                            Image imgScale = lector.getImagenFiltrada().getScaledInstance(labelImagenFiltro.getWidth(), labelImagenFiltro.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon nuevaImagen = new ImageIcon(imgScale);
                            labelImagenFiltro.setIcon(nuevaImagen);
                        } catch (Exception ex){} 
                        
                    }
                    if(filtro.equals("Filtro Gris 7")){
                        try{
                            LectorImagen lector = new LectorImagen(file.getAbsolutePath());
                            lector.filtro_gris_(7);
                            Image imgScale = lector.getImagenFiltrada().getScaledInstance(labelImagenFiltro.getWidth(), labelImagenFiltro.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon nuevaImagen = new ImageIcon(imgScale);
                            labelImagenFiltro.setIcon(nuevaImagen);
                        } catch (Exception ex){} 
                    }
                    if(filtro.equals("Filtro Gris 8")){
                        try{
                            LectorImagen lector = new LectorImagen(file.getAbsolutePath());
                            lector.filtro_gris_(8);
                            Image imgScale = lector.getImagenFiltrada().getScaledInstance(labelImagenFiltro.getWidth(), labelImagenFiltro.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon nuevaImagen = new ImageIcon(imgScale);
                            labelImagenFiltro.setIcon(nuevaImagen);
                        } catch (Exception ex){} 
                    }
                    if(filtro.equals("Filtro Gris 9")){
                        try{
                            LectorImagen lector = new LectorImagen(file.getAbsolutePath());
                            lector.filtro_gris_(9);
                            Image imgScale = lector.getImagenFiltrada().getScaledInstance(labelImagenFiltro.getWidth(), labelImagenFiltro.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon nuevaImagen = new ImageIcon(imgScale);
                            labelImagenFiltro.setIcon(nuevaImagen);
                        } catch (Exception ex){} 
                        
                    }
                    if(filtro.equals("Alto Contraste")){
                        try{
                            LectorImagen lector = new LectorImagen(file.getAbsolutePath());
                            lector.filtroAltoContraste();
                            Image imgScale = lector.getImagenFiltrada().getScaledInstance(labelImagenFiltro.getWidth(), labelImagenFiltro.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon nuevaImagen = new ImageIcon(imgScale);
                            labelImagenFiltro.setIcon(nuevaImagen);
                        } catch (Exception ex){} 
                        
                    }
                    if(filtro.equals("Inverso")){
                        try{
                            LectorImagen lector = new LectorImagen(file.getAbsolutePath());
                            lector.filtroInverso();
                            Image imgScale = lector.getImagenFiltrada().getScaledInstance(labelImagenFiltro.getWidth(), labelImagenFiltro.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon nuevaImagen = new ImageIcon(imgScale);
                            labelImagenFiltro.setIcon(nuevaImagen);
                        } catch (Exception ex){} 
                        
                    }
                    if(filtro.equals("Brillo")){
                        mostrarComponentesBrilla();
                        
                    }
                    if(filtro.equals("Mosaico")){

                        mostrarComponentesMosaico();
                    }

                }
                

            } );
            

            buttonAplicarBrillo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try{
                        LectorImagen lector = new LectorImagen(file.getAbsolutePath());
                        int cons = Integer.parseInt(textFieldBrillo.getText());
                        lector.filtroBrillo(cons);
                        Image imgScale = lector.getImagenFiltrada().getScaledInstance(labelImagenFiltro.getWidth(), labelImagenFiltro.getHeight(), Image.SCALE_SMOOTH);
                        ImageIcon nuevaImagen = new ImageIcon(imgScale);
                        labelImagenFiltro.setIcon(nuevaImagen);
                    } catch (Exception ex){} 
                }
             });


             buttonAplicarMosaico.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int a = Integer.parseInt(textFieldAnchoMosaico.getText());
                    int b = Integer.parseInt(textFIeldAltoMosaico.getText());
                    try{
                        LectorImagen lector = new LectorImagen(file.getAbsolutePath());
                        lector.filtroMosaico(a, b);
                        Image imgScale = lector.getImagenFiltrada().getScaledInstance(labelImagenFiltro.getWidth(), labelImagenFiltro.getHeight(), Image.SCALE_SMOOTH);
                        ImageIcon nuevaImagen = new ImageIcon(imgScale);
                        labelImagenFiltro.setIcon(nuevaImagen);
                    } catch (Exception ex){} 
                }
             });


             

    }   



    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                   new FiltroApp().setVisible(true);
               }
           });
  
    }


    

}
