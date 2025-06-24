//Ponemos que cargue las funciones cuando la página cargue y aquí dentro hacems tanto la estructyura como las funciones.
window.onload = () => {

    //Para ver si había enlazado bien el script y funcionaba
    //console.log("Se carga el script");

    /*
    Primero creamos un contendor principal que será hijo del body, le he dado un id para poder cambiarle los estilos desde el css y quede
    más limpio y el js no quede tan cargado. Le asigno el contenedor principal al body así, el body se queda más libre como se explica en la clase
    de dudas. 
    */
    let contenedorPrincipal = document.createElement('div');
    contenedorPrincipal.id = 'contenedorPrincipal';
    document.body.appendChild(contenedorPrincipal);

    /*
    Ahora creamos las dos partes que vamos a tener, por una parte el lienzo principal y por otra parte la barra izquierda, la barra izquierda 
    a su vez va a tener dos hijos, donde el primero contendrá las imágenes y el segundo los radios para controlar lo demás.
    */
    let lienzo = document.createElement('main');
    lienzo.id = 'lienzo';
    //Aquí no hace falta poner document porque es ya una parte del DOM.
    contenedorPrincipal.appendChild(lienzo);

    let barraLateral = document.createElement('aside');
    barraLateral.id = 'barraLateral';
    contenedorPrincipal.appendChild(barraLateral);

    //Creamos dos div para que sean hijos de la barra lateral.
    let parteSuperior = document.createElement('div');
    parteSuperior.id = 'parteSuperior';
    barraLateral.appendChild(parteSuperior);

    let parteInferior = document.createElement('div');
    parteInferior.id = 'parteInferior';
    barraLateral.appendChild(parteInferior);

    //Radio de aumentar
    let labelAumentar = document.createElement('label');
    labelAumentar.id = 'labelAumentar';
    let radioAumentar = document.createElement('input');
    radioAumentar.type = 'radio';
    radioAumentar.value = 'aumentar';
    radioAumentar.name = 'accionSeleccionada';
    labelAumentar.appendChild(radioAumentar);
    labelAumentar.appendChild(document.createTextNode('Aumentar Tamaño'));
    parteInferior.appendChild(labelAumentar);

    //Radio de dismunuir
    let labelDisminuir = document.createElement('label');
    labelDisminuir.id = 'labelDisminuir';
    let radioDisminuir = document.createElement('input');
    radioDisminuir.type = 'radio';
    radioDisminuir.value = 'disminuir';
    radioDisminuir.name = 'accionSeleccionada';
    labelDisminuir.appendChild(radioDisminuir);
    labelDisminuir.appendChild(document.createTextNode('Disminuir Tamaño'));
    parteInferior.appendChild(labelDisminuir);

    //Radio de borrar
    let labelBorrar = document.createElement('label');
    labelBorrar.id = 'labelBorrar';
    let radioBorrar = document.createElement('input');
    radioBorrar.type = 'radio';
    radioBorrar.value = 'borrar';
    radioBorrar.name = 'accionSeleccionada';
    labelBorrar.appendChild(radioBorrar);
    labelBorrar.appendChild(document.createTextNode('Borrar'));
    parteInferior.appendChild(labelBorrar);

    //Botón para guardar JPG
    let botonGuardar = document.createElement('button');
    botonGuardar.textContent = 'Guardar JPG';
    botonGuardar.id = 'botonGuardar';
    parteInferior.appendChild(botonGuardar);

    /*
    Esta parte no la podía hacer con canvas y como es opcional y he visto que los compañeros estaban en la misma situación que yo, he decidido
    añadirla. He usado un evento click con una función que  permite como "hacer una captura del lienzo" mediante un enlace, luego crea un enlace, la convierte
    y la descarga. 
     */
    botonGuardar.addEventListener('click', () => {
        html2canvas(lienzo).then(canvas => {
            let enlace = document.createElement('a');
            enlace.href = canvas.toDataURL('image/jpeg');
            enlace.download = 'lienzo.jpg';
            enlace.click();
        });
    });

    //Utilizamos la función para integrar las 5 imágenes dentro del div de la parte superior de la barra lateral.
    imagenes(5, parteSuperior);

    /*
    Creamos la función imágenes, esta se usa para ir insertando las imágenes de la carpeta dentro del div, por lo tanto le pasamos el
    número de imágenes y el contendor donde queremos meterlas.
    */
    function imagenes(numero, contenedor){
        for(let i = 0; i < numero; i++){
            let imagen = document.createElement('img');
            imagen.id = 'imagenesBarra';
            imagen.src = `./imagenes/im${i + 1}.png`;
            contenedor.appendChild(imagen);

            let copiada = false;

            //Dentro de las imágenes creamos el evento para que se aplique a todas, le damos el id y lo ponemos como hija del lienzo y cambiamos la bandera.
            imagen.addEventListener('click', function(){
                //Si no estan copiadas, usamos cloneNode, le añadimos el id y lo ponemos como hijo del lienzo y cambiamos la bandera.
                if(!copiada){
                    let copia = imagen.cloneNode(true);
                    copia.id = 'imagenCopiada';
                    lienzo.appendChild(copia);
                    copiada = true;
                    //Aquí añadimos las dos funciones creadas posteriormente, para seleccionar y mover la imágen o aplicarle los cambios.
                    seleccionarImagen(copia);
                    cambiarImagen(copia);
                }
            });
        }
    }

    /*
    A continuación creamos la función, la cual, nos va a permitir seleccionar la imágen y ponerla en la capa superior a todas y dentro
    de esta función he creado la función para mover la imagen porque me parecía una manera más fácil que crearlas por separado y así
    poder usar la variable imagen. Me resultaba más sencillo.
    */

    //Creamos una variable para que sea la primera capa
    let primeraCapa = 1;

    function seleccionarImagen(imagen) {
        //Bandera con la que vamos a ir trabajando.
        let moviendo = false;
    
        //Aquí almacenamos las dimensiones que tiene el lienzo con este método que calcula la posición y las dimensiones que ocupa.
        let posicionLienzo = lienzo.getBoundingClientRect();
        
        //Función para mover la imágen.
        function moverImagen(e) {
            //Si moviendo esta en false, lo que hacemos es lo siguiente:
            if (moviendo) {
                //Almacenamos el movimiento del cursor con respecto al lienzo. La parte de la imagen.offset es para colocar el cursor en medio de la imagen, por eso lo dividimos entre 2.
                //e.client lo que hace es calcular la posición del ratón a la ventana.
                //posicionLienzo es lo que nos calcula la distancia del borde que escojamos al ratón.
                let nuevaX = e.clientX - posicionLienzo.left - imagen.offsetWidth / 2;
                let nuevaY = e.clientY - posicionLienzo.top - imagen.offsetHeight / 2;
    
                /*
                Aquí lo que hacemos es controlar que la imagen no se salga del área del lienzo horizontalmente. Primero limita la posición nuevaX 
                para que no sobrepase el borde derecho del lienzo (con Math.min), y luego garantiza que no se mueva hacia la izquierda más allá 
                del borde izquierdo (con Math.max).
                */
                nuevaX = Math.max(0, Math.min(nuevaX, posicionLienzo.width - imagen.offsetWidth));
                nuevaY = Math.max(0, Math.min(nuevaY, posicionLienzo.height - imagen.offsetHeight));
    
                //Le asignamos las nuevas posiciones.
                imagen.style.left = nuevaX + 'px';
                imagen.style.top = nuevaY + 'px';
            }
        }
    
        // Creamos el evento click con una función para hacer que cuando se pulse se vaya a la primera capa y se mueva y el segundo click lo pare.
        imagen.addEventListener('click', function (e) {
            //Aquí la bandera la he puesto al revés. Si es false, se cambia a true.
            if (!moviendo) {
                moviendo = true;
                //Se pone en la primera capa.
                imagen.style.zIndex = primeraCapa++;
                //Use esto para ver si entraba en los bucles y cuando.
                //console.log("Se mueve");
    
                // Agregar el evento de movimiento para mover la imagen
                document.addEventListener('mousemove', moverImagen);
            } else {
                moviendo = false;
                //console.log("Se suelta");
                // Eliminar el evento de movimiento para detener el movimiento
                document.removeEventListener('mousemove', moverImagen);
            }
        });
    }
    
    /*
    Al ser otro ejercicio diferente, he optado por hacer la función fuera de la función imagenes y agregarla allí cuando la terminara. La
    he llamado cambiar imagen, por los cambios de la barra lateral.
    */
    function cambiarImagen(imagen){
        //Para aplicar el click con el botón derecho he usado contextmenu porque click tiene como prederminado el derecho y no me salia bien.
        imagen.addEventListener('contextmenu', function (e) {
            //Dentro de esta variable, almaceno el radio seleccionado.
            let opcion = document.querySelector('input[name="accionSeleccionada"]:checked');

            /*
            Digo que si hay opción, según lo que tenga en su valor, que almacenamos dentro de la variable valorOpcion hacemos lo que vaya
            correspondiendo, se puede hacer también con switch, pero como era corto he decidido usar if-else.
            */
            if(opcion){
                let valorOpcion = opcion.value;

                if(valorOpcion === 'borrar'){
                    imagen.remove();
                    //console.log("Esta borrando");
                } else if (valorOpcion === 'aumentar'){
                    //Aquí lo que hacemoe es sacar el ancho y el largo de la foto y multiplicarlo por un valor mayor que 1 y la escala aumentará
                    imagen.style.width = (imagen.width * 1.2) + 'px';  
                    imagen.style.height = (imagen.height * 1.2) + 'px'; 
                    //console.log("Esta aumentando");
                } else if(valorOpcion === 'disminuir'){
                    //Aquí por el contrario el valor es menor que 1 por lo que la imagen disminuye de escala.
                    imagen.style.width = (imagen.width * 0.8) + 'px';
                    imagen.style.height = (imagen.height * 0.8) + 'px'; 
                    //console.log("Esta disminuyendo");
                }
            }

             //Con esto evito que salga el menú de cuando pulsas el botón derecho porque era molesto.
             e.preventDefault();
        });
    }



}