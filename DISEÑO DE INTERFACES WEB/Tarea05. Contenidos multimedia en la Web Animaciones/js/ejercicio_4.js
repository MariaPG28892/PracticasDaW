window.addEventListener("load", function () {
    //Primero lo que hacemos es coger los elementos del html que vamos a usar. y decimos que va a ser en 2D
    const canvas = document.getElementById("mesaCanvas");
    const ctx = canvas.getContext("2d");
    const boton = document.getElementById("iniciarAnimacion");

    //Declaramos las variables con los ejes y el ancho y el alto, aunque se puede hacer dentro de cada sección
    let palo = { x: 150, y: 130, ancho: 100, alto: 10 };
    let bola = { x: 300, y: 135, radio: 10, velocidadX: 2 };
    //declaramos que no muestre mensaje ni animación ni ningún movimiento hasta que no entre dentro de la función correspondiente
    let mostrarMensaje = false;
    let animando = false;
    let paloDesplazamiento = 0;
    let golpeado = false; 

    //En esta funcion hacemos que se dibuje todo al cargar la página
    function dibujarTodo() {
        dibujarMesa();
        dibujarPalo();
        dibujarBola();
        if (mostrarMensaje) dibujarMensaje();
    }

    /*
    Dibujamos la mesa, con fillStile dibujamos un cuadro relleno del color que le pasemos, luego situamos las coordenadas para que 
    este centrado y con strokem le damos bode y lo situamos con las coordenadas. 
    */
    function dibujarMesa() {
        ctx.fillStyle = "#006400";
        ctx.fillRect(70, 30, 460, 160);
        ctx.strokeStyle = "#8B4513";
        ctx.lineWidth = 15;
        ctx.strokeRect(70, 30, 460, 160);
    }

    /*
    Para dibujar el palo hacemos los mismo que anteriormente, pero en este caso también le añadimos la punta de otro color,
    le ponemos las coordenadas y las medidas correspondientes.
     */
    function dibujarPalo() {
        let xFinal = palo.x + paloDesplazamiento;

        // Parte principal del palo (color madera)
        ctx.fillStyle = "#f5a623";
        ctx.fillRect(xFinal, palo.y, palo.ancho, palo.alto);
        ctx.strokeStyle = "#000";
        ctx.lineWidth = 2;
        ctx.strokeRect(xFinal, palo.y, palo.ancho, palo.alto);

        // Punta del palo azul, le añadimos una parte final y la cambiamos de color
        ctx.fillStyle = "blue";
        const puntaAncho = 5; 
        ctx.fillRect(xFinal + palo.ancho, palo.y, puntaAncho, palo.alto);
    }

    /*
    Para dibujar la bola seguimos los mismos pasos que anteriormente pero en este caso usamos arc, usamos fillstyle para 
    el relleno y stroke para los bordes.
    */
    function dibujarBola() {
        ctx.beginPath();
        ctx.arc(bola.x, bola.y, bola.radio, 0, 2 * Math.PI);
        ctx.fillStyle = "white";
        ctx.fill();
        ctx.strokeStyle = "black";
        ctx.lineWidth = 2;
        ctx.stroke();
    }

    /*
    Para dibujar el texto lo que hacemos es asignarle la fuente, luego le he puesto contorno a las letras y el texto como se
    ponía en el ejemplo.
    */
    function dibujarMensaje() {
        ctx.font = "30px Times New Roman";
        ctx.strokeStyle = "black";
        ctx.lineWidth = 2;
        ctx.strokeText("¡Billar Pro 15/04/25!", 170, 80);
        ctx.fillStyle = "yellowgreen";
        ctx.fillText("¡Billar Pro 15/04/25!", 170, 80);
    }

    /*
    Realizamos la función de animar, para ello lo hacemos por fase, comprobamos si ya esta animando, si no continuamos. Comprobamos 
    eque el palo no ha golpeado, y hacemos que lo haga a 40 pixel en un segundo, ccuando golpea cambiamos las banderas a true
    Cuando golpea pasamos a la bola, si el palo a golpeado decimos a la bola que se mueva a una velocidad establecida en la varible
    Cuando la bola llega a 510 píxeles se detiene y llamariamos a la función de dibujar todo.
    */
    function animar() {
        if (!animando) return;

        // FASE 1: mover el palo hacia la bola
        if (!golpeado) {
            if (paloDesplazamiento < 40) { // Llega hasta cerca de la bola
                paloDesplazamiento += 1; // Más lento
            } else {
                golpeado = true;
                mostrarMensaje = true;
            }
        }

        // FASE 2: cuando golpea, la bola empieza a moverse
        if (golpeado) {
            bola.x += bola.velocidadX;
        }

        // Detener animación cuando la bola llegue al borde
        if (bola.x + bola.radio >= 510) {
            animando = false;
        }

        dibujarTodo();
        requestAnimationFrame(animar);
    }

    /*
    Realizamos el evento onclik para que al pulsar el botón todo se reinicie y que llame a la función animar.
    */
    boton.addEventListener("click", () => {
        paloDesplazamiento = 0;
        golpeado = false;
        bola.x = 300;
        mostrarMensaje = false;
        animando = true;
        animar();
    });

    /*
    Dibujamos todo lo que nos hace falta para comenzar la partida.
    */
    dibujarTodo();
});
