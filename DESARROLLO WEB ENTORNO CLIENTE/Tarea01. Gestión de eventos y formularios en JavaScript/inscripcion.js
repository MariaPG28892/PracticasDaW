
//Evento para cargar los contenidos, le pasamos la función iniciar para que cargue las demás funciones y los eventos.
window.addEventListener('load',iniciar,false);

//Función iniciar, aquí voy a programar los eventos que quiero que pasen cuando se inicie la página, si están fuera no funcionan. 
function iniciar()
{
    //Evento enviar: Cuando se pulsa el botón enviar, iniciamos la función de validación. 
	enviar.addEventListener('click',validar);

    //Evento mostrar contraseña.
    /*
    Programamos el evento recurriendo al id que le hemos puesto al botón y le añadimos el evento mousedown, que es cuando 
    pulsemo el botón, luego añadimos una función anónima.
    En esta función anómima almacenamos la contraseña repetida en una variable y luego esta la convertimos en tipo text y se mostraría
    el tipo text en lugar del password cuando pulsas el botón.
    */
    document.getElementById('mostrarContrasenia').addEventListener('mousedown', function() {
        let contraseniaTapada = document.getElementById('repetirContrasenia');
        contraseniaTapada.type = 'text';  
    });

    /*
    Aquí programamos el evento contrario que cuando levantes de pulsar el ratón la contraseña vuelva a ser de tipo password por lo tanto
    conseguiremos que no se muestre.
    */
    document.getElementById('mostrarContrasenia').addEventListener('mouseup', function() {
        let contraseniaTapada = document.getElementById('repetirContrasenia');
        contraseniaTapada.type = 'password';  
    });

    /*
    Este lo vi también y lo añadí para que la contraseña se oculte si quitas el ratón del botón, pero no me funciona del todo.
    */
    document.getElementById('mostrarContrasenia').addEventListener('mouseleave', function() {
        let contraseniaSinTapar = document.getElementById('repetirContrasenia');
        contraseniaSinTapar.type = 'password';
    });

    //CONVERTIR LA ULTIMA EN MAYUSCULA. Lo he comentado para que no intervenga en el ejercicio porque no lo he coseguido hacer bien

/*
    let valoresOriginales = {}; // Guarda los valores originales de cada input

    //Lo que hace es cuando el usuario pulsa el botón se produce el evento, y guardo en id el valor real de lo que escribe el usuario
    document.addEventListener("keydown", function (event) {
        if (event.target.tagName === "INPUT" && event.target.type === "text") {
            let id = event.target.id;
            valoresOriginales[id] = event.target.value;
        }
    });

    //Aquí uso focusin para que funcione el evento cuando el usuario pone el foco. 
    document.addEventListener("focusin", function (event) {
        if (event.target.tagName === "INPUT" && event.target.type === "text") {
            let texto = event.target.value;
            //verifico que haya escrito algo y cuando lo tengo, lo que hago es quitar la última letra y convertirla en mayúscula
            if (texto.length > 0) {
                event.target.value = texto.slice(0, -1) + texto.slice(-1).toUpperCase();
            }
        }
    });

    //Cuando el usuario quita el foco, lo que hace es devolver el valor original.
    document.addEventListener("focusout", function (event) {
        if (event.target.tagName === "INPUT" && event.target.type === "text") {
            let id = event.target.id;
            event.target.value = valoresOriginales[id];
        }
    });

*/
}

/*Función validar: Esta es la que le pasamos dentro de la función iniciar cuando pulsamos el botón enviar. Como parámetro le pasamos el evento
defecto que en nuestro caso será el click*/
function validar(eventopordefecto){

    let devolver=""; 
    //Inicializamos el innerHTML para que se carguen todos los mensajes que queremos que se nos muestren en las demás funciones.
	salida.innerHTML="";

    //Aqui hacemos un condicional donde pasamos que todas las funciones sean true y al final mande un formulario de confirmación, si todas son true.
    if(validarNombre() & validarCodigo() & validarContraseña() & validarRepetirContrasenia() 
        & validarGenero() && confirm("¿Deseas enviar el formulario?")){
        //Aqui programamos un preventDefault para que no mande nada  y devolvemos true  
        eventopordefecto.preventDefault();
        devolver=true;
    
    } else {
        //Si alguna de las funciones falla devolvemos false y usamos de nuevo preventDefault.
        eventopordefecto.preventDefault();		
		devolver=false;
    }

    return devolver;
}

/*
Función validar nombre: Aquí validamos que el nombre contenga espacios, mayúsculas y minúsculas, también ñ y cedilla, añadí 
las tildes porque cuando probaba mi nombre solo salía error y era por eso. También debe tener una extensión de 10 a 45 caracteres. No
damos la posibilidad a que el campo este vacío por lo tanto será obligatorio.
*/
function validarNombre(){
    const patron = /^[A-Za-zñçÑÇÁÉÍÓÚáéíóú ]{10,45}$/;
    let nombre = document.getElementById("nombre").value;
    let devolver = true;

    //Aplicamos el patrón al input con el id nombre, si es correcto le añadimos el className correcto para que se ponga en verde y devolvemos true
    if(patron.test(nombre)){
        salida.innerHTML+="Nombre: "+nombre+"<br>";
        document.getElementById("nombre").className="correcto";
        devolver= true;
    }else{
        //En el caso que no cumpla el patrón no se cumpla mandamos un mensaje de error, false y damos focus y asignamos className error para que se ponga rojo
        salida.innerHTML+="El nombre no es correcto o no puede estar vacio.<br>";

        document.getElementById("nombre").focus();
		document.getElementById("nombre").className="error";	
		devolver=false;
    }

    return devolver;
}
/*
Función validar codigo: Aquí comprobamos que empiece por tres dígitos y que siga por dos letras mayúsculas donde no se incluyen las vocales.
En esta función seguimos los mismo pasos que la anterior para indicar acierto o error. 
*/
function validarCodigo(){
    const patron = /^\d{3}[BCDFGHJKLMNÑPQRSTVWXYZ]{2}$/;
    let codigo = document.getElementById("codigo").value
    let devolver = false;

    if(patron.test(codigo)){
        salida.innerHTML+="Código de invitación: "+codigo+"<br>";
        document.getElementById("codigo").className="correcto";
        devolver=true;
    }else{
        salida.innerHTML+="El código de invitación no es correcto o no puede estar vacio.Debe estar formado por tres números y dos consonantes en mayúscula.<br>";

        document.getElementById("codigo").className="error";
        devolver= false;
    }

    return devolver;
}

/*
Validación de contraseña. Aquí lo hice con lookaheads como se explica en clase. Empece diciendo que empezara obligartoriamente por mayúscula,
luego puede contener vocales, puede contener de 1 a 3 dígitos, para ello fui repitendo la secuencia y añadiendo ? para que fuera opcional, luego
debía de tener un ..A continuación, use un lookaheads negativo para que no dejara introducir palabras, solo las pedía en minúscula, pero las añadí
también en mayúscula para más seguridad. Luego le añadí que debía de terminar por , y ^, si ^ no va entre paréntesis no funciona. No hay ninguna restricción
para demás simbolos y para finalizar añadimos la extensión de 9 a 21 caracteres.
*/
function validarContraseña(){
    const patron = 
    /(?=^[A-Z])(?=.*[a-z])(?=^[^0-9]*[0-9][^0-9]*[0-9]?[^0-9]*[0-9]?[^0-9]*$)(?=.*[.])(?!.*from)(?!.*FROM)(?!.*insert)(?!.*INSERT)(?!.*delete)(?!.*DELETE)(?=.*,[^]$)(^.{9,21}$)/;
    let contrasenia = document.getElementById("contrasenia").value;
    let devolver = false;

    //Controlamos si cumple el patrón y asignamos el className y si no lo cumple le asignamos el className de error y mandamos el mensaje.
    if(patron.test(contrasenia)){
        document.getElementById("contrasenia").className = "correcto";
        devolver = true;
    } else {
        salida.innerHTML += "La contraseña no es correcta o no puede estar vacia. Debe de respetar el patrón indicado.<br>";
        document.getElementById("contrasenia").className = "error";
        document.getElementById("contrasenia").focus();
        devolver = false;
    }

    return devolver;
}

/*
Función validarRepetirContraseña. Aquí lo que he hecho es meter en contenido de los input corresponientes en dos variables. Primero debía comprobar
que la contraseá no estuviera vacía, en el caso, en el caso de estarlo lanzaría un error explicando que el campo contraseña esta vacía.
Si el campo de contraseña no esta vacío, comprobamos que sean iguales, si lo son asignamos el classname correcto y si no lanzamos el className
de error y su mensaje correspondiente.
 */
function validarRepetirContrasenia(){
    let contrasenia = document.getElementById("contrasenia").value;
    let contraseniaRepetida = document.getElementById("repetirContrasenia").value;
    let devolver = false;

    if (contrasenia === "") {
        salida.innerHTML += "Por favor, ingresa una contraseña primero.<br>";
        document.getElementById("repetirContrasenia").className = "error";
        document.getElementById("repetirContrasenia").focus();
        devolver = false;  
    }

    if(contrasenia){
        if(contrasenia == contraseniaRepetida){
            document.getElementById("repetirContrasenia").className = "correcto";
            devolver = true;
        } else {
            salida.innerHTML += "Las contraseñas no coinciden.<br>";
            document.getElementById("repetirContrasenia").className = "error";
            document.getElementById("repetirContrasenia").focus();
            devolver = false;
        }
    }

    return devolver;
}

/*
Función validar género: Aquí el patrón acepta solamente caracteres de letras, mayúsculas, mínusculas con tilde y espacios.
Lo diferente es que al ser un campo opcional, debemos aceptar que el genero mande una cadena vacía y tomarlo como acierto.
 */
function validarGenero(){
    const patron = /^[A-Za-zÑñÇçÁÉÍÓÚáéíóú ]+$/;
    let genero = document.getElementById("genero").value;
    let devolver = false;

    if(patron.test(genero) || genero === ""){
        salida.innerHTML += "Género: "+genero+"<br>";
        document.getElementById("genero").className = "correcto";
    } else {
        salida.innerHTML += "El género introducido no es correcto.<br>";
        document.getElementById("genero").focus();
        document.getElementById("genero").className = "error";
        devolver = false;
    }

    return devolver;
}

