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

    /**
     * Clase que modela a un LectorImagen, se encarga de leer la imagen cargada por el usuario y 
     * aplica los filtros dependiendo de la entrada del usuario 
     */
    class LectorImagen {

        private BufferedImage imagenFiltrada; 
        private int ancho; 
        private int alto;
        
        public LectorImagen(String path){
            try {
                File input = new File(path);
                imagenFiltrada= ImageIO.read(input);
                ancho = imagenFiltrada.getWidth();
                alto = imagenFiltrada.getHeight();      
             } catch (Exception e) {}  
        }
        
        /**
         * Metodo para aplicar un filtro de gris dada la entrada del usuario 
         * @param tipo
         */
        public void filtro_gris_(int tipo){

            try{
                for(int i=0; i<ancho; i++) {
                    
                    for(int j=0; j<alto; j++) {
                    
                       //Obtiene el valor de cada pixel 
                       int pixel = imagenFiltrada.getRGB(i, j);
                      // Generamos el color que rellenara a cada pixel 
                       Color color = new Color(pixel,true);
                      //Obtenemos los colores de ese pixel 
                       int r = color.getRed(); 
                       int g = color.getGreen(); 
                       int b = color.getBlue(); 
                       // Se generan los nuevos valores para el color utiizando la funcion generaGris 
                       int gray = this.generaGris(tipo, r, g, b);  
                       
    
                       //Se genera un nuevo color usando los generados 
                       color = new Color(gray,gray, gray);
                       imagenFiltrada.setRGB(i, j, color.getRGB());
                     
                    }
                 }
                }
                catch(Exception e){}

        }

        /**
         * Metodo para generar un valor en entero dado el tipo de gris que el usuario desea,
         * y los 3 colores 
         * @param tipo
         * @param red
         * @param green
         * @param blue
         * @return
         */
        public int generaGris(int tipo, int red, int green, int blue){
            switch(tipo){
                case 1: 
                    return (red+green+blue)/3;
                    
                case 2: 
                    return (int)(.3*red)+(int)(.59*green)+(int)(.11*blue); 

                case 3:
                    return (int)(.2126*red)+(int)(.7152*green)+(int)(.0722*blue);

                case 4: 
                    return  (Math.max(Math.max(red, green), blue) + Math.min(Math.min(red, green), blue))/2; 

                case 5: 
                    return Math.max(Math.max(red, green), blue); 

                case 6:
                    return Math.min(Math.min(red, green), blue);

                case 7: 
                    return red; 

                case 8: 
                    return green; 

                case 9:
                    return blue; 

                default: 
                    return 0;     
            

            }
        }

        /**
         * Metodo para aplicar el filtro brillo a una Imagen dado la constante 
         * @param constante
         */
        public void filtroBrillo(int constante){

            //Verifica que la constante sea un numero 
    
            try {
                for(int i=0; i < ancho; i++){
                    for(int j=0; j< alto; j++){
                        //Obtiene el valor de cada pixel 
                        int pixel = imagenFiltrada.getRGB(i, j);
                        // Generamos el color que rellenara a cada pixel 
                        Color color = new Color(pixel,true);
                        //Obtenemos los colores de ese pixel 
                        int r = color.getRed(); 
                        int g = color.getGreen(); 
                        int b = color.getBlue();
    
                        int nr=0;
                        int ng=0; 
                        int nb=0; 
    
                        if(constante >= 0){
                            nr = (r+constante > 255)? 255:r+constante; 
                            ng = (g+constante > 255)? 255:g+constante; 
                            nb = (b+constante > 255)? 255:b+constante; 
                        }
                        if(constante < 0){
                            nr = (r+constante < 0)? 0:r+constante; 
                            ng = (g+constante < 0)? 0:g+constante; 
                            nb = (b+constante < 0)? 0:b+constante; 
                        }
                        
                        color = new Color(nr,ng,nb);
                        imagenFiltrada.setRGB(i, j, color.getRGB()); 
    
                    }
                }    
            } catch (Exception e) {
                //TODO: handle exception
            }
            
        }


        public BufferedImage getImagenFiltrada(){
            return imagenFiltrada; 
        }

        /**
         * Metodo para aplicar el filtro Mosaico a la imagen dadas las dimensiones del mosaico 
         * @param a
         * @param l
         */
        public void filtroMosaico(int a, int l){

            int totalIteraciones = 0; 

            for (int i = 0; i < ancho; i+=a) {
                for (int j = 0; j < alto; j+=l) { 
                    Color c = this.getColorPromedio(i, j, a+i, l+j);
                    aplicarColor(c, i, j, a+i, l+j);
                    if(j == alto-l){
                        aplicarColor(c, i, alto-l, a+i, alto);
                    }  
                    if (i == ancho-a && j < alto) {
                        aplicarColor(c, ancho-a, j, ancho, l+j);
                    }                     
                    
                }
                
            }
            
        }

        /**
         * Metodo para aplicar un color a una region dada 
         * @param c
         * @param rengI
         * @param colI
         * @param rengF
         * @param colF
         */
        public void aplicarColor(Color c, int rengI, int colI, int rengF, int colF){
            try {
                for (int renglon = rengI; renglon < rengF; renglon++) {
                    for (int columna = colI ; columna < colF; columna++) {
                        imagenFiltrada.setRGB(renglon, columna, c.getRGB());
                    }     
                }
            } catch (Exception e) {
                //TODO: handle exception
            }
        }

        /**
         * Metodo para obtener el color promedio dada una region
         * @param rengI
         * @param colI
         * @param rengF
         * @param colF
         * @return
         */
        public Color getColorPromedio(int rengI, int colI, int rengF, int colF){


            int totaRed = 0; 
            int totalGreen = 0;
            int totalBlue = 0; 

            int promRed=0; 
            int promGreen =0; 
            int promBlue =0; 
  
            //Se obtiene el total de pixeles para obtener el video 
            int pixelesTotales = (rengF - rengI)*(colF-colI);

            try {
                for (int renglon = rengI; renglon < rengF; renglon++) {
                    for (int columna = colI ; columna < colF; columna++) {
                        int pixel = imagenFiltrada.getRGB(renglon, columna);
                        // Generamos el color de que obtendremos informacion 
                        Color color = new Color(pixel,true);
    
                        totaRed += color.getRed();
                        totalGreen += color.getGreen(); 
                        totalBlue += color.getBlue(); 
                    }     
                }
                
            } catch (Exception e) {
                
            }
             
            promRed = totaRed/pixelesTotales; 
            promGreen = totalGreen/pixelesTotales; 
            promBlue = totalBlue/pixelesTotales; 

            

            Color promColor = new Color(promRed, promGreen, promBlue);
            return promColor; 

        }


    }


    

}
