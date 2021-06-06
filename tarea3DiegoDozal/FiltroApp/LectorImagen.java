import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.*; 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Clase que modela a un LectorImagen, se encarga de leer la imagen cargada por el usuario y 
 * aplica los filtros dependiendo de la entrada del usuario 
 */
class LectorImagen {

        private BufferedImage imagenFiltrada; 
        private int ancho; 
        private int alto;

        //Convoluciones 
        
        private double[][] blur = {{0.0, 0.2, 0.0}, 
                                   {0.2, 0.2, 0.2}, 
                                   {0.0, 0.2, 0.0}}; 

        private int[][] blurS = {{0, 0, 1, 0, 0}, 
                                 {0, 1, 1, 1, 0}, 
                                 {1,1,1,1,1}, 
                                 {0, 1, 1, 1, 0}, 
                                 {0, 0, 1, 0, 0}};

        private int[][] motionBlur = {{1,0,0,0,0,0,0,0,0}, 
                                      {0,1,0,0,0,0,0,0,0}, 
                                      {0,0,1,0,0,0,0,0,0}, 
                                      {0,0,0,1,0,0,0,0,0}, 
                                      {0,0,0,0,1,0,0,0,0}, 
                                      {0,0,0,0,0,1,0,0,0}, 
                                      {0,0,0,0,0,0,1,0,0}, 
                                      {0,0,0,0,0,0,0,1,0}, 
                                      {0,0,0,0,0,0,0,0,1}};

        private int[][] findEdges = {{-1,0,0,0,0}, 
                                     {0,-2,0,0,0}, 
                                     {0,0,6,0,0}, 
                                     {0,0,0,-2,0}, 
                                     {0,0,0,0,-1}};

        private int[][] sharpen = {{-1,-1,-1}, 
                                   {-1,9,-1}, 
                                   {-1,-1,-1}};

        private int[][] emboss = {{-1,-1,-1,-1,0}, 
                                  {-1,-1,-1,0,1}, 
                                  {-1,-1,0,1,1}, 
                                  {-1,0,1,1,1}, 
                                  {0,1,1,1,1}}; 

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

            
            for (int i = 0; i < ancho; i+=a) {
                for (int j = 0; j < alto; j+=l) { 

                    if(i+a > ancho && j+l > alto){
                        Color c = this.getColorPromedio(i, j, ancho, alto);
                        aplicarColor(c, i, j, ancho, alto);
                    }
                    else if(i+a > ancho){   
                        Color c = this.getColorPromedio(i, j, ancho, l+j);
                        aplicarColor(c, i, j, ancho, l+j);
                    }
                    else if(j+l > alto){
                        Color c = this.getColorPromedio(i, j, i+a, alto);
                        aplicarColor(c, i, j, i+a, alto);
                    }
                    else {
                        Color c = this.getColorPromedio(i, j, a+i, l+j);
                        aplicarColor(c, i, j, a+i, l+j);
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


        /**
         * Metodo de alto contraste 
         */
        public void filtroAltoContraste(){
            this.filtro_gris_(1);
            try {
                for(int i=0; i < ancho; i++){
                    for(int j=0; j< alto; j++){
                        //Obtiene el valor de cada pixel 
                        int pixel = imagenFiltrada.getRGB(i, j);
                        // Generamos el color que rellenara a cada pixel 
                        Color color = new Color(pixel,true);
                        //Obtenemos los colores de ese pixel 
                        int val = color.getRed(); 
                        
                        int newVal =0; 

                        if(val > 127 ){
                            newVal = 255; 
                        }
                        
                        color = new Color(newVal,newVal,newVal);
                        imagenFiltrada.setRGB(i, j, color.getRGB()); 
    
                    }
                }    
            } catch (Exception e) {
                //TODO: handle exception
            }
        }

        /**
         * Metodo para el filto de Inverso 
         */
        public void filtroInverso(){
            this.filtro_gris_(1);
            try {
                for(int i=0; i < ancho; i++){
                    for(int j=0; j< alto; j++){
                        //Obtiene el valor de cada pixel 
                        int pixel = imagenFiltrada.getRGB(i, j);
                        // Generamos el color que rellenara a cada pixel 
                        Color color = new Color(pixel,true);
                        //Obtenemos los colores de ese pixel 
                        int val = color.getRed(); 
                        
                        int newVal =255; 

                        if(val > 127 ){
                            newVal = 0; 
                        }
                        
                        color = new Color(newVal,newVal,newVal);
                        imagenFiltrada.setRGB(i, j, color.getRGB()); 
    
                    }
                }    
            } catch (Exception e) {
                //TODO: handle exception
            }
        }

        /**
         * Metodo para el filtro de Componentes RGB 
         * @param red
         * @param green 
         * @param blue 
         */
        public void filtroRGB(int red, int green, int blue){
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
                        int b = color.getGreen(); 

                        color = new Color(red&r, green&g, blue&b );
                        imagenFiltrada.setRGB(i, j, color.getRGB()); 
    
                    }
                }    
            } catch (Exception e) {
                //TODO: handle exception
            }
        }

        /**
         * Filtro que aplica una convulsion con arreglo bidimensional de doubles 
         * @param convolution
         * @param f
         * @param b
         */
        public void aplicarConvolucion(double[][] convolution, double f, double b ){

            double factor = f; 
            double bias = b; 

            try {
                for(int i=0; i < ancho; i++){
                    for(int j=0; j< alto; j++){
        
                        double red=0.0;
                        double green=0.0; 
                        double blue=0.0; 
    
                        for (int filterY = 0; filterY < convolution.length; filterY++) {
                            for (int filterX = 0; filterX < convolution.length; filterX++ ) {
                                
                                int imageX = (i - convolution.length/2 + filterX + ancho ) % ancho; 
                                int imageY = (j - convolution.length/2 + filterY + alto ) % alto;

                                //Obtiene el valor de cada pixel 
                                int pixel = imagenFiltrada.getRGB(imageX, imageY);
                                // Generamos el color que rellenara a cada pixel 
                                Color color = new Color(pixel,false);
                                //Obtenemos los colores de ese pixel 

                                red += color.getRed()*convolution[filterY][filterX]; 
                                green += color.getGreen()*convolution[filterY][filterX];
                                blue += color.getBlue()*convolution[filterY][filterX];
                            
                            }
 
                        }
                        
                        int finalRed = Math.min(Math.max((int)(factor*red+bias), 0), 255); 
                        int finalGreen = Math.min(Math.max((int)(factor*green+bias), 0), 255); 
                        int finalBlue = Math.min(Math.max((int)(factor*blue+bias), 0), 255); 

                        Color color = new Color(finalRed, finalGreen, finalBlue );
                        imagenFiltrada.setRGB(i, j, color.getRGB());
    
                    }
                }    
            } catch (Exception e) {
                //TODO: handle exception
            }
        }



        /**
         * Filtro que aplica una convulsion con arreglo bidimensional de enteros 
         * @param convolution
         * @param f
         * @param b
         */
        public void aplicarConvolucion(int[][] convolution, double f, double b ){

            	
            BufferedImage copia = deepCopy(imagenFiltrada);

            double factor = f; 
            double bias = b; 
 

            try {
                for(int i=0; i < alto; i++){

                    for(int j=0; j< ancho; j++){
        
                        double red=0.0;
                        double green=0.0; 
                        double blue=0.0; 
    
                        for (int filterY = 0; filterY < convolution.length; filterY++) {
                            for (int filterX = 0; filterX < convolution.length; filterX++ ) {
                                
                                ///Cambio 
                                int imageX = (j - convolution.length/2 + filterX + ancho ) % ancho; 
                                int imageY = (i - convolution.length/2 + filterY + alto ) % alto;
                                ///Cambio 


                                //Obtiene el valor de cada pixel 
                                int pixel = imagenFiltrada.getRGB(imageX, imageY);
                                // Generamos el color que rellenara a cada pixel 
                                Color color = new Color(pixel);
                                //Obtenemos los colores de ese pixel 
                                
                                int tmpR = color.getRed();
                                int tmpG = color.getGreen(); 
                                int tmpB = color.getBlue();    


                                red += tmpR*convolution[filterY][filterX]; 
                                green += tmpG*convolution[filterY][filterX];
                                blue += tmpB*convolution[filterY][filterX];
                                
                            
                            }
 
                        }
                        
                        
                        Color color = new Color(Math.min(Math.max((int)(factor*red+bias), 0), 255), 
                                                Math.min(Math.max((int)(factor*green+bias) , 0), 255), 
                                                Math.min(Math.max((int)(factor*blue+bias), 0), 255));
                        
                                            //Cambio 
                        copia.setRGB(j, i, color.getRGB());
                                            //Cambio 
                        
                    }

                     
                }    
                this.imagenFiltrada = copia; 
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

       
        /**
         * 
         */
        public void filtroConvolucion(int option){
           switch (option) {
                case 1:
                   aplicarConvolucion(blur, 1.0, 0.0);
                   break;
                case 2:
                   aplicarConvolucion(blurS, (1.0/13.0), 0.0);
                   break;
                case 3:
                   aplicarConvolucion(motionBlur, (1.0/9.0), 0.0);
                   break;
                case 4:
                   aplicarConvolucion(findEdges, 1.0, 0.0);
                   break;
                case 5:
                   aplicarConvolucion(sharpen, 1.0, 0.0);
                   break;
                case 6:
                   aplicarConvolucion(emboss, 1.0, 128.0);
                   break;
                   
                default:
                   break;
           }
        }

        //Suponiendo que piden un rectangulo de 10x10     
        public void drawM2D(int a, int b){
            
            //Obtenemos una copia en blanco de la imagen en la cual pintar 
            BufferedImage canvas = deepCopy(imagenFiltrada);
            limpiarImagen(canvas);

            //Se genera un objeto 2d a partir de la imagen ingresada 
            Graphics2D img = canvas.createGraphics(); 
            String chain = "M";
            //String chain = "HOLA COMO ANDAMOS"; 
            //Generamos los Fonts 
            Font font = new Font("Arial", Font.PLAIN,6);
            //Para modificar los atributos del Font 
            AttributedString as = new AttributedString(chain);
            as.addAttribute(TextAttribute.FONT, font);
            //Color a usar 
            Color c;
            

            img.setFont(font);

            for (int i = 0; i < ancho; i+=a) {
                for (int j = 0; j < alto; j+=b) { 
                    
                    if(i+a > ancho && j+b > alto){
                        c = this.getColorPromedio(i, j, ancho, alto);
                        as.addAttribute(TextAttribute.FOREGROUND, c,0,chain.length());
                        img.drawString(as.getIterator(), i, j);
                    }
                    else if(i+10 > ancho){   
                        c = this.getColorPromedio(i, j, ancho, b+j);
                        as.addAttribute(TextAttribute.FOREGROUND, c,0,chain.length());
                        img.drawString(as.getIterator(), i, j);
                    }
                    else if(j+10 > alto){
                        c = this.getColorPromedio(i, j, i+a, alto);
                        as.addAttribute(TextAttribute.FOREGROUND, c,0,chain.length());
                        img.drawString(as.getIterator(), i, j);
                    }
                    else {
                        c = this.getColorPromedio(i, j, a+i, b+j);
                        as.addAttribute(TextAttribute.FOREGROUND, c,0,chain.length());
                        img.drawString(as.getIterator(), i, j);
                        
                    }
                    
                }
            }
            this.imagenFiltrada = canvas; 
        }


        public void drawM2DBlanckAndWhite(int a, int b){
            filtro_gris_(1);
            drawM2D(a, b); 

        }


        /**
         * Metodo para Imprimir caracteres en negro que representen la imagen 
         * @param a
         * @param b
         */
        public void drawOnlyLetters(int a, int b){
            
            //Obtenemos una copia en blanco de la imagen en la cual pintar 
            //filtro_gris_(1);
            BufferedImage canvas = deepCopy(imagenFiltrada);
            limpiarImagen(canvas);

            //Se genera un objeto 2d a partir de la imagen ingresada 
            Graphics2D img = canvas.createGraphics(); 
            ///ESTA CADENA PUEDEN SER TODOS LOS POSIBLES CARACTERES A TOMAR Y CON EL METODO 
            /// as.addAttribute(TextAttribute.FOREGROUND, c,0,chain.length()); DETERMINAMOS QUE RAGO TOMAR 
            //METODO/S QUE INGRESE UN entero y de los indices de la cadena o 
            String chain = "MNH#QUAD0Y2$%+, ";
            //String chain = "HOLA COMO ANDAMOS"; 
            //Generamos los Fonts 
            Font font = new Font("Arial", Font.PLAIN,6);
            //Para modificar los atributos del Font 
            AttributedString as = new AttributedString(chain);
            as.addAttribute(TextAttribute.FONT, font);
            //Color a usar 
            Color c = Color.black;
            

            img.setFont(font);

            for (int i = 0; i < ancho; i+=a) {
                for (int j = 0; j < alto; j+=b) { 
                    
                    if(i+a > ancho && j+b > alto){
                        c = this.getColorPromedio(i, j, ancho, alto);
                        as.addAttribute(TextAttribute.FOREGROUND, Color.black,0,chain.length());
                        img.drawString(as.getIterator(null,determinarCaracter(c.getRed())[0],determinarCaracter(c.getRed())[1]), i, j);
                    }
                    else if(i+10 > ancho){   
                        c = this.getColorPromedio(i, j, ancho, b+j);
                        as.addAttribute(TextAttribute.FOREGROUND, Color.black,0,chain.length());
                        img.drawString(as.getIterator(null,determinarCaracter(c.getRed())[0],determinarCaracter(c.getRed())[1]), i, j);
                    }
                    else if(j+10 > alto){
                        c = this.getColorPromedio(i, j, i+a, alto);
                        as.addAttribute(TextAttribute.FOREGROUND, Color.black,0,chain.length());
                        img.drawString(as.getIterator(null,determinarCaracter(c.getRed())[0],determinarCaracter(c.getRed())[1]), i, j);
                    }
                    else {
                        c = this.getColorPromedio(i, j, a+i, b+j);
                        as.addAttribute(TextAttribute.FOREGROUND, Color.black,0,chain.length());
                        img.drawString(as.getIterator(null,determinarCaracter(c.getRed())[0],determinarCaracter(c.getRed())[1]), i, j);
                        
                    }
                    
                }
            }
            this.imagenFiltrada = canvas; 
        }

        /**
         * Metodo para Imprimir caracteres en los colores que representen la imagen 
         * @param a
         * @param b
         */
        public void drawLetters(int a, int b){
            
            //Obtenemos una copia en blanco de la imagen en la cual pintar 
            //filtro_gris_(1);
            BufferedImage canvas = deepCopy(imagenFiltrada);
            limpiarImagen(canvas);

            //Se genera un objeto 2d a partir de la imagen ingresada 
            Graphics2D img = canvas.createGraphics(); 
            ///ESTA CADENA PUEDEN SER TODOS LOS POSIBLES CARACTERES A TOMAR Y CON EL METODO 
            /// as.addAttribute(TextAttribute.FOREGROUND, c,0,chain.length()); DETERMINAMOS QUE RAGO TOMAR 
            //METODO/S QUE INGRESE UN entero y de los indices de la cadena o 
            String chain = "MNH#QUAD0Y2$%+, ";
            //String chain = "HOLA COMO ANDAMOS"; 
            //Generamos los Fonts 
            Font font = new Font("Arial", Font.PLAIN,6);
            //Para modificar los atributos del Font 
            AttributedString as = new AttributedString(chain);
            as.addAttribute(TextAttribute.FONT, font);
            Color c; 
            

            img.setFont(font);

            for (int i = 0; i < ancho; i+=a) {
                for (int j = 0; j < alto; j+=b) { 
                    
                    if(i+a > ancho && j+b > alto){
                        c = this.getColorPromedio(i, j, ancho, alto);
                        as.addAttribute(TextAttribute.FOREGROUND, c,0,chain.length());
                        img.drawString(as.getIterator(null,determinarCaracter(c.getRed())[0],determinarCaracter(c.getRed())[1]), i, j);
                    }
                    else if(i+10 > ancho){   
                        c = this.getColorPromedio(i, j, ancho, b+j);
                        as.addAttribute(TextAttribute.FOREGROUND, c,0,chain.length());
                        img.drawString(as.getIterator(null,determinarCaracter(c.getRed())[0],determinarCaracter(c.getRed())[1]), i, j);
                    }
                    else if(j+10 > alto){
                        c = this.getColorPromedio(i, j, i+a, alto);
                        as.addAttribute(TextAttribute.FOREGROUND, c,0,chain.length());
                        img.drawString(as.getIterator(null,determinarCaracter(c.getRed())[0],determinarCaracter(c.getRed())[1]), i, j);
                    }
                    else {
                        c = this.getColorPromedio(i, j, a+i, b+j);
                        as.addAttribute(TextAttribute.FOREGROUND, c,0,chain.length());
                        img.drawString(as.getIterator(null,determinarCaracter(c.getRed())[0],determinarCaracter(c.getRed())[1]), i, j);
                        
                    }
                    
                }
            }
            this.imagenFiltrada = canvas; 
        }

        /**
         * Metodo para Imprimir caracteres en tonos de gris  que representen la imagen
         * @param a
         * @param b
         */
        public void drawGreyLetters(int a, int b){
            filtro_gris_(1);
            drawLetters(a, b);
        }






        /// METODO QUE DETERMINA EL RANGO PARA CONSIDERAR DE UNA CADENA DADO EL VALOR INTRODUCIDO
        public int[] determinarCaracter(int value){

            int[] rango = {0,0}; 
    
            if (value >= 0 &&  value <= 15) {
                rango[0]=0;
                rango[1]=1;
                return rango; 
            }
            if (value >= 16 &&  value <= 31) {
                rango[0]=1;
                rango[1]=2;
                return rango; 
            }
            if (value >= 32 &&  value <= 47) {
                rango[0]=2;
                rango[1]=3;
                return rango; 
            }
            if (value >= 48 &&  value <= 63) {
                rango[0]=3;
                rango[1]=4;
                return rango; 
            }
            if (value >= 64 &&  value <= 79) {
                rango[0]=4;
                rango[1]=5;
                return rango; 
            }
            if (value >= 80 &&  value <= 95) {
                rango[0]=5;
                rango[1]=6;
                return rango;  
            }
            if (value >= 96 &&  value <= 111) {
                rango[0]=6;
                rango[1]=7;
                return rango; 
            }
            if (value >= 112 &&  value <= 127) {
                rango[0]=7;
                rango[1]=8;
                return rango;  
            }
            if (value >= 128 &&  value <= 143) {
                rango[0]=8;
                rango[1]=9;
                return rango;  
            }
            if (value >= 144 &&  value <= 159) {
                rango[0]=9;
                rango[1]=10;
                return rango;  
            }
            if (value >= 160 &&  value <= 175) {
                rango[0]=10;
                rango[1]=11;
                return rango;  
            }
            if (value >= 176 &&  value <= 191) {
                rango[0]=11;
                rango[1]=12;
                return rango;  
            }
            if (value >= 192 &&  value <= 209) {
                rango[0]=12;
                rango[1]=13;
                return rango;  
            }
            if (value >= 210 &&  value <= 225) {
                rango[0]=13;
                rango[1]=14;
                return rango; 
            }
            if (value >= 226 &&  value <= 239) {
                rango[0]=14;
                rango[1]=15;
                return rango; 
            }
            if (value >= 240 &&  value <= 255) {
                rango[0]=15;
                rango[1]=16;
                return rango;  
            }
    
            return rango;
    
        }

    /**
     * Metodo para blanquear la superficie de la imagen
     */
    public void limpiarImagen(BufferedImage img ){

        Color white = new Color(255,255,255);

        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                img.setRGB(i, j, white.getRGB());
            }
        }
    }



    static BufferedImage deepCopy(BufferedImage bi) {
    ColorModel cm = bi.getColorModel();
    boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
    WritableRaster raster = bi.copyData(null);
    return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }    

} 