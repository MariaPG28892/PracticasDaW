package tarea09;

//Librerías para poder utilizar JavaFX
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//Librerías específicas para evaluar expresiones exp4j
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

//He tenido que importar esta libreria para que me cogiera el logo
import java.io.InputStream;
/**  
 * La típica calculadora básica para realizar cálculos artitméticos como la suma, 
 * resta, multiplicación y división, en la que se permite el cálculo de 
 * expresiones combinadas.
 * 
 * @author María Palomares Gallo
 */
public class Calculadora extends Application{
 //----------------------------------------------
    //          Declaración de variables 
    //----------------------------------------------
    //Declaro dos atributos fuera para poder usarlos en los dos métodos. 
    private final StringBuilder pantallaContenido = new StringBuilder();
    private TextField pantalla; 
    //----------------------------------------------
    //          Declaración de variables auxiliares 
    //----------------------------------------------  
    
    /*El método start es el punto de entrada para una aplicación JavaFX.
    Su función principal es inicializar y mostrar la interfaz 
    gráfica de usuario (GUI) de la aplicación. Se crea un diseño (layout), 
    se añaden controles (botones, etiquetas, campos, ...) y se crea la escena
    con un estilo, y se muestra el escenario.*/
    
    @Override
    public void start(Stage escenario) {
    
    // Primero le damos nombre al escenario, usamos setTitle esto cambiará el nombre que aparece en la ventana.
    escenario.setTitle("Mi calculadora");
    //Para hacer que la ventana no pueda editarse lo hacemos con setResizable y ponemos false, por defecto está en true.
    escenario.setResizable(false);

    /*
    Para cargar el icono no podía como me daba en los apuntes de clase, así que busqué un tutorial en internet y encontré
    que con inputStream se podía hacer de esta manera y usando un try-catch me aseguraba de que si fallaba me diera el error
    de que no había encontrado bien la ruta o la imagen.
    */
    InputStream iconoStream = getClass().getResourceAsStream("/tarea09/logoCalcu.png");
    if (iconoStream == null) {
        System.out.println("No se pudo cargar la imagen del icono.");
    } else {
        Image icono = new Image(iconoStream);
        escenario.getIcons().add(icono);
    }

    /*
    Primero hago lo que va a contener el VBox y después hago este porque sino me da error si lo pongo antes,
    porque sino me daría error porque las cosas que queremos que sean sus hijos no están definidas.
    */
    
    /*
    Par hacer la pantalla he usado textField, es un cuadro de texto donde van a aparecer lo que vayamos tecleando 
    en la calculadora.
    */
    pantalla = new TextField();
    
    //Para hacer los botones lo hacemos por medio de grid que es una red de espacios a los que podemos dar forma o meter datos
    GridPane grid = new GridPane();
    grid.setHgap(10);  // Espacio horizontal entre las columnas
    grid.setVgap(10);  // Espacio vertical entre las filas
    grid.setAlignment(Pos.CENTER); //Hacemos que se posicione en el centro.
    
    /*
    Hacemos una matriz para almacenar los botones de la calculadora y establecemos los sitios que van a ocupar cada 
    uno de ellos, podemos darle la ubicación que queramos dentro de la matriz y así se colocaran.
    */
    String[][] botones = {
        {"7", "8", "9", "/", "("},
        {"4", "5", "6", "*", ")"},
        {"1", "2", "3", "-", "."},
        {"0", "C", "<", "+", "="}
    };

    // Inicializamos la matriz estableciendo las filas y las columnas.
    for (int fila = 0; fila < botones.length; fila++) {
        for (int col = 0; col < botones[fila].length; col++) {
            /*
            Dentro de las columnas extraemos el texto que tiene cada una de ellas y cuando creamos el botón 
            lo añadimos comos su símbolo, le añadimos el nombre que va a tener para asignarle estilo, aquí decidí
            dejarle button para no equivocarme.
            */
            String texto = botones[fila][col];
            Button button = new Button(texto);
            
            button.getStyleClass().add("button");
        
            // Agregamos los eventos de entrada y añadimos el método que se va a generar cuando se produzca el evento de presionar dl botón
            button.setOnAction(event -> {
                procesoDeEntrada(texto); 
            });

            /*
            Para asignar el nombre del css a cada botón lo he hecho con switch y he puesto los nombres que ya venían
            definidos en el css.
            */
            switch (texto) {
                case "/":
                case "*":
                case "-":
                case "+":
                case "(":
                case ")":
                case ".":
                    button.getStyleClass().add("operador");
                    break;
                case "=":
                    button.getStyleClass().add("igual");
                    break;
                case "C":
                case "<":
                    button.getStyleClass().add("limpiar");
                    break;
                default:
                    break;
            }
            //Aquí añadimos el botón a la columna y a la fila que le pertenece.
            grid.add(button, col, fila);
        }
    }

    //La caja que contiene tanto la pantalla como el grid he usado Vbox y la he nombrado root por el css. 
    VBox root = new VBox(10, pantalla, grid);
    root.getStyleClass().add("root");
    //Luego creamos la escena y le damos las medidas y con setAligment la centramos dentro de VBox
    Scene escena = new Scene(root, 380, 380);
    root.setAlignment(Pos.CENTER);

    // Enlazar el archivo CSS a la escena
    escena.getStylesheets().add(getClass().getResource("calculadora.css").toExternalForm());
    //Enlazamos el escenario con la escena y decimos que se muestre.
    escenario.setScene(escena);
    escenario.show();
    }

    /**
    * El método procesoDeEntrada maneja la entrada de datos en una calculadora.
    * Toma una cadena de texto (entrada) y realiza diferentes acciones 
    * según el contenido de esa cadena, agregando al campo de texto, estableciendo 
    * el estado, controlando la adición de puntos decimales para evitar múltiples 
    * decimales en un número o evaluando la expresión mátemática para mostrar el 
    * resultado haciendo uso de la librería Exp4J.
    * @param entrada Texto recogido de los diferentes textoBotones de  la calculadora.
    */
    public void procesoDeEntrada(String entrada) {
    /*
    Aquí lo que tenemos que hacer es ir validando cada botón y en el igual con la librería se crearía la expresión.
    */
    
    //Si la entrada es un dígito entre 0 y 9. No hacemos nada y lo agregamos a la pantalla.
    if (entrada.matches("[0-9]")) {  
        pantallaContenido.append(entrada);  
    //Si la entrada es una "C" borramos todo el contenido de la pantalla usando setLength y lo ponemos a 0
    } else if (entrada.equals("C")) {  
        pantallaContenido.setLength(0);
    //Si la entrada es "<" lo que hacemos es comprobar que la entrada contiene más de un caracter y borramos el último dígito
    } else if (entrada.equals("<")) {  
        if (pantallaContenido.length() > 0) {
            //usamos deleteCharAt y le indicamos -1 que será el último dígito.
            pantallaContenido.deleteCharAt(pantallaContenido.length() - 1);
        }
    /*
    Si la entrada es un punto, lo que hago es convertir el contenido de la pantala en un string, dividirlo en partes,
    estas partes se dividen por los signos y comprobamos cada parte, si esa parte tiene un punto, no dejará que se 
    le añadan más, sino si que le deja que se añada. Así podremos tener decimales reales.
    */
    } else if (entrada.equals(".")) {  
        String contenido = pantallaContenido.toString();
        String[] partes = contenido.split("[+\\-*/()]");
        String ultimoNumero = partes.length > 0 ? partes[partes.length - 1] : "";

        if (!ultimoNumero.contains(".")) {
            pantallaContenido.append(".");
        }
    //Si la entrada es un operador, se mostrará solamente si el carácter anterior es un número o un )
    } else if (entrada.matches("[+\\-*/]")) {  // Si es un operador
        if (pantallaContenido.length() > 0 && 
            (Character.isDigit(pantallaContenido.charAt(pantallaContenido.length() - 1)) || 
            pantallaContenido.charAt(pantallaContenido.length() - 1) == ')')) {
            pantallaContenido.append(entrada); 
        }
    //Si la entrada es un "(" y el último caracter es un número se añade un * y por lo tanto luego sera multiplicación, sino se añade "(" solamente
    } else if (entrada.equals("(")) { 
        if (pantallaContenido.length() > 0 && Character.isDigit(pantallaContenido.charAt(pantallaContenido.length() - 1))) {
            pantallaContenido.append("*(");
        } else {
            pantallaContenido.append("(");
        }
    } else if (entrada.equals(")")) {  
        if (pantallaContenido.length() > 0 && 
            (Character.isDigit(pantallaContenido.charAt(pantallaContenido.length() - 1)) || 
            pantallaContenido.charAt(pantallaContenido.length() - 1) == ')')) {
            pantallaContenido.append(")");
        }
    /*
    Si es "=" realizamos el calculo, metemos el contenido de la pantalla en un toString, luego lo construimos con
    expressionBuilder y lo evaluamos (He seguido los pasos de la tarea) y luego lo sacamos por pantalla, si falla
    controlamos el fallo y ponemos Error en la expresión como se muestra en el vídeo del ejemplo.
    */
    } else if (entrada.equals("=")) {  
        String expresion = pantallaContenido.toString();
        try {
            Expression e = new ExpressionBuilder(expresion).build();
            double resultado = e.evaluate();  
            pantallaContenido.append("=").append(resultado);  
        } catch (Exception ex) {
            pantallaContenido.setLength(0);
            pantallaContenido.append("Error en la expresión");
        }
    }

    // Actualizar la pantalla con el contenido
    pantalla.setText(pantallaContenido.toString());
    }

    /**
     * Programa principal, lanza la aplicación.
     * @param args Argumentos o parámetros (no hay en este caso)
     */
    public static void main(String[] args) {
        launch(args);
    }
}