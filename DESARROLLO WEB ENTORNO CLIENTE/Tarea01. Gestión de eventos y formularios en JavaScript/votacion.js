//LOCAL STORAGE
/*
Lo he puesto al principio, pero no se si es correcto, decimos que si no hay local stroge con el nombre envío lo cree y vaya contabilizando
de la misma manera que hacemos los errores.
*/
if (!localStorage['envios']){
    localStorage.setItem('envios', 0);
}

if (!localStorage['errores']) {
    localStorage.setItem('errores', 0); 
}

//Cargamos el formulario y le pasamos la función iniciar.
window.addEventListener('load',iniciar,false);

//Función iniciar: Aquí vamos metiendo los eventos que queremos que carguen en nuestro formulario.
function iniciar(){
    //Programamos los dos botones que tenemos pasandoles las funciones correspondientes. 
    enviar.addEventListener('click',validar);
    borrar.addEventListener('click', borrarFormulario, false);

    //Aquí programamos la parte de la calificacion, si la calificación esta puesta, habilitamos el campo de descripción, mientras
    //que si el campo de calificación está vacío se deshabililta el campo de descripción.
    calificacion.addEventListener('input', function() {
        if (calificacion.value) {
            descripcion.disabled = false;
        } else {
            descripcion.disabled = true;
            descripcion.value = ''; 
        }
    });
}

/*
En la funcion validad haremos lo mismo que en el anterior, le pasamos un evento por defecto, que sería en este caso click que se manda por el
botón enviar. Dentro de esta función vamos a inicializar el iinerHTML.
*/
function validar(eventopordefecto){
    let devolver=""; 
	salida.innerHTML="";

    /*
    Cuando validamos la pelicula, la descripcion y la calificación, hacemos varias cosas, por un lado aumentamos el contador de envios 
    correctos, sumandole al setItem ('envios') +1. Lo convertir en integer porque me dio un fallo. Y lo mandamos a localStorage.
    Por otro lado, cuando envío hago que se vuelva a desahibilitar el campo de descripción porque en algunos intentos se me quedaba 
    habilitado y así lo aseguraba. Por otro lado, he creado una funcíon paraque cuando lo envie nos muestre por innetHTML cuantos 
    formulario correctos y erroneos se han enviado. Si le damos a borrar se borraria esta información.
    */
    if(validarPelicula() & validarDescripcion() & validarCalificacion() && confirm("¿Deseas enviar el formulario?")){

        let envios = parseInt(localStorage.getItem('envios')) + 1;
        localStorage.setItem('envios', envios);

        document.getElementById("descripcion").disabled = true;
        mostrarContadores();

        eventopordefecto.preventDefault();

        devolver=true;
    
    } else {

        /*
        De la misma manera voy contabilizando los errores en localStorage, si un formulario no se envía porque ha saltado algún error
        lo iríamos contabilizando aquí.
        */
        let errores = parseInt(localStorage.getItem('errores')) + 1;
        localStorage.setItem('errores', errores);

        eventopordefecto.preventDefault();		
		devolver=false;
    }

    return devolver;
}

/*
Función validar película: Metemos todos los input por medio de elementsbyName dentro de una variable. Luego vamos recorreiendo
esa variable con un for viendo si alguna esta marcada, si es asi, lo asumimos como correcto, si no desmarcamos cualquier className.
Lo tuve que hacer así porque si no no me salía.
 */

function validarPelicula() {
    let devolver = false;
    let peliculas = document.getElementsByName("pelicula"); 

    
    for (let i = 0; i < peliculas.length; i++) {
        if (peliculas[i].checked) { 
            devolver = true;  
            salida.innerHTML += "La película seleccionada es "+peliculas[i].value+".<br>";
            peliculas[i].className = "correcto"; 
        } else {
            peliculas[i].className = ""; 
        }
    }

    // Si no se ha seleccionado ninguna película, mostramos el mensaje de error y le damos el focus al primer radio.
    if (!devolver) {
        salida.innerHTML += "Debe de marcar una película obligatoriamente.<br>";
        peliculas[0].className = "error";
        peliculas[0].focus();
    }

    return devolver;
}

/*
Validamos la descripcion. Aquí le decimos que si no está desabilitada si es menor que 0 o menor que 5 de un error, pero aqui estamos
danod la posibilidad de que pueda ser 0, por lo que permitiría que el campo sea opcional, si no lo cumple se mandará un error y si 
lo cumple se mandará como correcto.
*/
function validarDescripcion() {
    let descripcion = document.getElementById("descripcion").value;
    let devolver = false;

    if (!document.getElementById("descripcion").disabled) {
        if (descripcion.length > 0 && descripcion.length < 5) {
            document.getElementById("descripcion").focus();
            document.getElementById("descripcion").className = "error";
            devolver = false;
        } else {
            document.getElementById("descripcion").className = "correcto";
            salida.innerHTML += "La descripción de la pelicula: "+descripcion+"<br>";
            devolver = true;
        }
    } else {
        devolver = true;
    }

    return devolver;
}

/*
Para validar la calificacion, comprobamos qe si esta vacio, no es un numero y es menor que 0 o 10 salte un error, se ponga rojo y mande
un mensaje, de lo contrario lo daría como correcto.
*/
function validarCalificacion() {
    let devolver = false;
    let calificacion = document.getElementById("calificacion").value;

    if (calificacion.trim() === "" || isNaN(calificacion) || calificacion < 0 || calificacion > 10) {
        salida.innerHTML += "Es obligatorio introducir una calificación de 0 a 10<br>";
        document.getElementById("calificacion").focus();
        document.getElementById("calificacion").className = "error"; 
    } else {
        salida.innerHTML += "La calificación de la pelicula: "+calificacion+"<br>";
        document.getElementById("calificacion").className = "correcto"; 
        devolver = true;
    }

    return devolver;
}

/*
Función para el botón de borrar formulario. Para ello almacenamos el formulario dentro de una variable y le aplicamos la función reset.
Aquí también me aseguro de volver a deshabilitar la parte de descripción y también no tener ninguna pelicula seleccionada. 
*/
function borrarFormulario() {
    let formulario = document.getElementById('formulario');
    formulario.reset();
    salida.innerHTML="Formulario borrado con éxito<br>";

    document.getElementById("descripcion").disabled = true;
    document.getElementById("descripcion").className = ""; 

    
    document.getElementById("calificacion").className = "";
    let peliculas = document.getElementsByName("pelicula");
    for (let i = 0; i < peliculas.length; i++) {
        peliculas[i].className = "";
    }

 
}

//Función oara mostrar el contador del localstorage.
function mostrarContadores() {
    let envios = localStorage.getItem('envios');
    let errores = localStorage.getItem('errores');
    salida.innerHTML += `Número de envíos correctos: ${envios} <br>`;
    salida.innerHTML += `Número de errores cometidos: ${errores} <br>`;
}


