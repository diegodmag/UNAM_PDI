import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.*; 
import java.awt.event.*;
import java.awt.Graphics;
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
    private JTextField textFieldBrillo; 
    private JTextField textFieldAnchoMosaico; 
    private JTextField textFIeldAltoMosaico; 
    
    private ImageIcon imagenOriginal;
    private File file; 

    ///Para la modificacion de la imagenRGB 
    private JPanel panelRGB;
    private JFrame frameRGB; 
    private JSlider sliderRed; 
    private JSlider sliderGreen; 
    private JSlider sliderBlue; 
    private JLabel redLabel;
    private JLabel greenLabel;
    private JLabel blueLabel; 
    private JLabel redValueLabel;
    private JLabel greenValueLabel;
    private JLabel blueValueLabel; 
    ///PRUEBA 
     
    
    //Para convertir una imagen en letras 
    private JPanel panelLetters; 
    private JFrame frameLetters; 
    private JTextField textFieldAnchoLetters;
    private JTextField textFieldAltoLetters;
    private JButton buttonAplicarLetters; 




    private JButton buttonAplicarComponenteRGB;


    public FiltroApp(){
        this.inicializarComponente();
    }


    private void inicializarComponente(){
        //Inicializa JFrame 
        this.colocarFrames();    
        //Coloca los otros componentes 
        this.colocarPaneles();
        this.colocarLabels();
        this.colocarBotones();
        this.colocarCombobox();
        this.colocarTextField();

        //Coloca los componentes de ventanas hijas 
        this.colocarComponentesRGB();

        this.colocarComponentesLetters();
        

        //Coloca los eventos
        this.colocarEventos();
        
    }

    private void colocarFrames(){
        this.setBounds(75, 100, 1800, 800);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        frameRGB = new JFrame();
        frameRGB.setBounds(600, 300, 600, 400);
        frameRGB.setBackground(Color.RED);

        frameLetters = new JFrame();
        frameLetters.setBounds(750,300,300,250);
        frameLetters.setBackground(Color.RED);

    }

    private void colocarPaneles(){

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        this.getContentPane().add(panelPrincipal);

        panelRGB = new JPanel();
        panelRGB.setLayout(null);
        frameRGB.getContentPane().add(panelRGB);
        //panelPrincipal.add(panelRGB);

        panelLetters = new JPanel();
        panelLetters.setLayout(null);
        frameLetters.getContentPane().add(panelLetters); 
        
    }

 
     private void colocarLabels(){
        labelBusqueda = new JLabel("Seleccione una imagen"); 
        labelBusqueda.setBounds(30, 10, 200, 20);
        panelPrincipal.add(labelBusqueda);

        labelSeleccionFiltro = new JLabel("Seleccione un Filtro");
        labelSeleccionFiltro.setBounds(30, 70, 200, 20);
        panelPrincipal.add(labelSeleccionFiltro);

        labelImagenOriginal = new JLabel();
        labelImagenOriginal.setBounds(400, 20, 580, 680);
        panelPrincipal.add(labelImagenOriginal);
        

        labelImagenFiltro = new JLabel();
        labelImagenFiltro.setBounds(1050, 20, 580, 680);
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
        comboboxFiltros.addItem("Componentes RGB");
        comboboxFiltros.addItem("Blur 1");
        comboboxFiltros.addItem("Blur 2");
        comboboxFiltros.addItem("Motion Blur");
        comboboxFiltros.addItem("Encontrar Bordes");
        comboboxFiltros.addItem("Sharpen");
        comboboxFiltros.addItem("Emboss");
        //PRUEBA DE DIBUJO EN GRAPHICS2D 
        comboboxFiltros.addItem("Dibujar M");
        //
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

    private void colocarComponentesLetters(){
         
        JLabel jlabelrecomendacion = new JLabel("Dimensiones recomendadas: 5x5");
        jlabelrecomendacion.setBounds(20,20,250,20);
        panelLetters.add(jlabelrecomendacion);

        textFieldAnchoLetters = new JTextField();
        textFieldAnchoLetters.setBounds(100, 70, 100, 20);
        panelLetters.add(textFieldAnchoLetters);
        
        //textFieldAnchoLetters.setBounds(x, y, width, height);
        
        textFieldAltoLetters = new JTextField();
        textFieldAltoLetters.setBounds(100, 120, 100, 20);
        panelLetters.add(textFieldAltoLetters);

        buttonAplicarLetters = new JButton("Aplicar");
        buttonAplicarLetters.setBounds(100, 180, 100, 20);
        panelLetters.add(buttonAplicarLetters);


    }




    private void colocarComponentesRGB(){
        
        //ROJO
        sliderRed = new JSlider(0,255);
        sliderRed.setBounds(130, 30, 350, 50);
        sliderRed.setPaintLabels(true);
        sliderRed.setPaintTicks(true);
        sliderRed.setMajorTickSpacing(51);
        sliderRed.setMinorTickSpacing(5);
        panelRGB.add(sliderRed);

        redLabel = new JLabel("RED");
        redLabel.setBounds(60, 30, 50, 30);
        panelRGB.add(redLabel);

        redValueLabel = new JLabel(" ");
        redValueLabel.setBounds(500, 30, 50, 30);
        panelRGB.add(redValueLabel);

        //VERDE
        sliderGreen = new JSlider(0,255);
        sliderGreen.setBounds(130, 90, 350, 50);
        sliderGreen.setPaintLabels(true);
        sliderGreen.setPaintTicks(true);
        sliderGreen.setMajorTickSpacing(51);
        sliderGreen.setMinorTickSpacing(5);
        panelRGB.add(sliderGreen);

        greenLabel = new JLabel("GREEN");
        greenLabel.setBounds(60, 90, 50, 30);
        panelRGB.add(greenLabel);

        greenValueLabel = new JLabel(" ");
        greenValueLabel.setBounds(500, 90, 50, 30);
        panelRGB.add(greenValueLabel);

        //AZUL
        sliderBlue = new JSlider(0,255);
        sliderBlue.setBounds(130, 150, 350, 50);
        sliderBlue.setPaintLabels(true);
        sliderBlue.setPaintTicks(true);
        sliderBlue.setMajorTickSpacing(51);
        sliderBlue.setMinorTickSpacing(5);
        panelRGB.add(sliderBlue);

        blueLabel = new JLabel("GREEN");
        blueLabel.setBounds(60, 150, 50, 30);
        panelRGB.add(blueLabel);

        blueValueLabel = new JLabel(" ");
        blueValueLabel.setBounds(500, 150, 50, 30);
        panelRGB.add(blueValueLabel);



        buttonAplicarComponenteRGB = new JButton("Aplicar");
        buttonAplicarComponenteRGB.setBounds(250, 300, 100, 30);
        panelRGB.add(buttonAplicarComponenteRGB);
       

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
                    if(filtro.equals("Blur 1")){
                        try{
                            LectorImagen lector = new LectorImagen(file.getAbsolutePath());
                            lector.filtroConvolucion(1);
                            Image imgScale = lector.getImagenFiltrada().getScaledInstance(labelImagenFiltro.getWidth(), labelImagenFiltro.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon nuevaImagen = new ImageIcon(imgScale);
                            labelImagenFiltro.setIcon(nuevaImagen);
                        } catch (Exception ex){} 
                        
                    }
                    if(filtro.equals("Blur 2")){
                        try{
                            LectorImagen lector = new LectorImagen(file.getAbsolutePath());
                            lector.filtroConvolucion(2);
                            Image imgScale = lector.getImagenFiltrada().getScaledInstance(labelImagenFiltro.getWidth(), labelImagenFiltro.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon nuevaImagen = new ImageIcon(imgScale);
                            labelImagenFiltro.setIcon(nuevaImagen);
                        } catch (Exception ex){} 
                        
                    }
                    if(filtro.equals("Motion Blur")){
                        try{
                            LectorImagen lector = new LectorImagen(file.getAbsolutePath());
                            lector.filtroConvolucion(3);
                            Image imgScale = lector.getImagenFiltrada().getScaledInstance(labelImagenFiltro.getWidth(), labelImagenFiltro.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon nuevaImagen = new ImageIcon(imgScale);
                            labelImagenFiltro.setIcon(nuevaImagen);
                        } catch (Exception ex){} 
                        
                    }
                    if(filtro.equals("Encontrar Bordes")){
                        try{
                            LectorImagen lector = new LectorImagen(file.getAbsolutePath());
                            lector.filtroConvolucion(4);
                            Image imgScale = lector.getImagenFiltrada().getScaledInstance(labelImagenFiltro.getWidth(), labelImagenFiltro.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon nuevaImagen = new ImageIcon(imgScale);
                            labelImagenFiltro.setIcon(nuevaImagen);
                        } catch (Exception ex){} 
                        
                    }
                    if(filtro.equals("Sharpen")){
                        try{
                            LectorImagen lector = new LectorImagen(file.getAbsolutePath());
                            lector.filtroConvolucion(5);
                            Image imgScale = lector.getImagenFiltrada().getScaledInstance(labelImagenFiltro.getWidth(), labelImagenFiltro.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon nuevaImagen = new ImageIcon(imgScale);
                            labelImagenFiltro.setIcon(nuevaImagen);
                        } catch (Exception ex){} 
                        
                    }
                    if(filtro.equals("Emboss")){
                        try{
                            LectorImagen lector = new LectorImagen(file.getAbsolutePath());
                            lector.filtroConvolucion(6);
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
                    if(filtro.equals("Componentes RGB")){
                        frameRGB.setVisible(true);                     
                        
                    }

                    if(filtro.equals("Dibujar M")){
                        //System.out.println("Evento ");
                        frameLetters.setVisible(true); 

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



             /**Corregir  */
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

             buttonAplicarComponenteRGB.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try{
                        LectorImagen lector = new LectorImagen(file.getAbsolutePath());
                        lector.filtroRGB(sliderRed.getValue(), sliderGreen.getValue(), sliderBlue.getValue());;
                        Image imgScale = lector.getImagenFiltrada().getScaledInstance(labelImagenFiltro.getWidth(), labelImagenFiltro.getHeight(), Image.SCALE_SMOOTH);
                        ImageIcon nuevaImagen = new ImageIcon(imgScale);
                        labelImagenFiltro.setIcon(nuevaImagen);
                        
                    } catch (Exception ex){} 
                }
             });

             
             buttonAplicarLetters.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try{
                        accionRealizadaLetters(2);

                    } catch (Exception ex){} 
                }
             });

             sliderRed.addChangeListener(new ChangeListener(){
                public void stateChanged(ChangeEvent e){
                    redValueLabel.setText(String.valueOf(sliderRed.getValue())); 
                }
             });;

             sliderGreen.addChangeListener(new ChangeListener(){
                public void stateChanged(ChangeEvent e){
                    greenValueLabel.setText(String.valueOf(sliderGreen.getValue())); 
                }
             });;

             sliderBlue.addChangeListener(new ChangeListener(){
                public void stateChanged(ChangeEvent e){
                    blueValueLabel.setText(String.valueOf(sliderBlue.getValue())); 
                }
             });;

    }   

    public void accionRealizadaLetters(int n){

        int ancho = Integer.parseInt(textFieldAnchoLetters.getText());
        int alto = Integer.parseInt(textFieldAltoLetters.getText());

        LectorImagen lector = new LectorImagen(file.getAbsolutePath());
        Image imgScale; 
        ImageIcon nuevaImagen; 

       switch (n) {
           case 1:
           lector.drawM2D(ancho,alto);
           imgScale = lector.getImagenFiltrada().getScaledInstance(labelImagenFiltro.getWidth(), labelImagenFiltro.getHeight(), Image.SCALE_SMOOTH);
           nuevaImagen = new ImageIcon(imgScale);
           labelImagenFiltro.setIcon(nuevaImagen);
            break; 
           case 2: 
           lector.drawM2DBlanckAndWhite(ancho,alto);
           imgScale = lector.getImagenFiltrada().getScaledInstance(labelImagenFiltro.getWidth(), labelImagenFiltro.getHeight(), Image.SCALE_SMOOTH);
           nuevaImagen = new ImageIcon(imgScale);
           labelImagenFiltro.setIcon(nuevaImagen);
           break;
       
           default:
               break;
       }


    }
    




    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                   new FiltroApp().setVisible(true);
               }
           });
  
    }




    

}