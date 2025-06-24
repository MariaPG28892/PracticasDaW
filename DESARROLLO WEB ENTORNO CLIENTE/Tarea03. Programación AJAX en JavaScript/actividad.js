$(document).ready(function(){

    //Guardamos la clave para la url
    const CLAVE = '9849fae07b4d4283949160944251705';

    //Comenzamos con el tiempo actual, ponemos el id y le damos un evento click con una función. 
    $('#tiempoActual').click(function(e){
        //Prevenimos cualquier evento que no sea el que nosotros realicemos al hacer click
        e.preventDefault();

        //Oculto el mapa del tiempo, lo he hecho así porque si del mapa pasabas al tiempo actual se quedaba, de manera que así lo oculto.
        $('#mapaTiempo').hide();
        //Añadimos el gif animado
        $('#resultado').html("<img src='ajax-loader.gif'>");
        //Guardamos la localidad que haya puesto el usuario en el input.
        let localidad = $('#localidad').val();

        //Hacemos un condicional donde si hay localidad en el input comenzamos con el código.
        if(localidad){

            //He hecho un ternario para que si en el input no se pone ,__ se añada ,es y se refiere a España, para tenerlo más organizado en la URL.
            let comprobarLocalizacion = localidad.includes(',') ? localidad : localidad + ',es';
            //Guardamos la url añadiendo la clave y la localización que ha introducido el usuario.
            let url = `https://api.weatherapi.com/v1/current.json?key=${CLAVE}&q=${comprobarLocalizacion}&aqi=yes&lang=es`;

            /*
            He decidido usar flech. Primero decimos que si no hay un resulta, se mande un json con el error, he usado los console log para que me den info.
            Si hay datos trabajaremos con ellos.
            Con catch controlamos los errores
            Y finally para indicar que ha terminado la función
            Y el else final que mandaría una alerta al usuario para que introduzca obligatoriamente una localidad.
            */
            fetch(url)
            .then((result) =>{
                console.log(result.status);                    
                console.log(result);
                return result.json();
            })
            .then((datos_devueltos) =>{
                console.log(datos_devueltos);
                //En lugar he guardado los datos relacionados con la localización.
                let lugar = datos_devueltos.location;
                //En tiempoCompleto los datos relacionados con el tiempo actual, para saber el tiempo limitado a un día se usa current.
                let tiempoCompleto = datos_devueltos.current;
                let mostrarTiempo = "";

                /*
                He ido mostrando cada parte para que quedara estético, para ello también he creado un div para más abajo poder tocar los 
                estilos del css una vez se creen.
                */
                mostrarTiempo = '<div class ="tiempoActual">';
                mostrarTiempo +='<h3>TIEMPO EN '+ lugar.name.toUpperCase()+'</h3>';
                mostrarTiempo += '<h5>' + lugar.region.toUpperCase() +', '+ lugar.country.toUpperCase() + '</h5>';
                mostrarTiempo += '<img src="https:' + tiempoCompleto.condition.icon + '" alt="' + tiempoCompleto.condition.text + '">';
                mostrarTiempo += '<p><strong>Estado:</strong> ' + tiempoCompleto.condition.text + '</p>';
                mostrarTiempo += '<p><strong>Temperatura:</strong> ' + tiempoCompleto.temp_c + '°C</p>';

                //Si hay viento, mostraré los kilómetros y también desde donde viene.
                if (tiempoCompleto.wind_kph > 0){
                    mostrarTiempo += '<p><strong>Viento:</strong> ' + tiempoCompleto.wind_kph + ' km/h desde ' + tiempoCompleto.wind_dir + '</p>';
                }
                
                //Si hay precipitaciones muestro de que forma lluvia, granizo, nieve... y el número de precipitación(no sabía si había que ponerlo, pero como lo he visto lo he añadido)
                if (tiempoCompleto.precip_mm > 0) {
                    mostrarTiempo += '<p><strong>Precipitación:</strong> ' + tiempoCompleto.condition.text  +' ('+tiempoCompleto.precip_mm  +'mm)</p>';
                }
                //Mostramos la calidad del aire por medio de una lista con air_quality y añadiendo, he usado fixed(2) para limitarlo a 2 decimales.
                mostrarTiempo += '<h3>Calidad del Aire</h3>';
                mostrarTiempo += '<ul>';
                mostrarTiempo += '<li>CO: ' + tiempoCompleto.air_quality.co.toFixed(2) + '</li>';
                mostrarTiempo += '<li>NO₂: ' + tiempoCompleto.air_quality.no2.toFixed(2) + '</li>';
                mostrarTiempo += '<li>SO₂: ' + tiempoCompleto.air_quality.so2.toFixed(2)  + '</li>';
                mostrarTiempo += '<li>PM10: '  + tiempoCompleto.air_quality.pm10.toFixed(2)  + '</li>';
                mostrarTiempo += '<li>O₃: '  + tiempoCompleto.air_quality.o3.toFixed(2)   + '</li>';
                mostrarTiempo += '</ul>'

                mostrarTiempo += '</div>';

                //Mostramos el tiempo en el id resultado.
                $('#resultado').html(mostrarTiempo);
                //Aquí he tocado el css, lo tenía fresco de DIW y lo he puesto lo más estético posible.
                $('.tiempoActual').css({ 
                        'background-color': 'rgb(110, 174, 237)',
                        'border': '1px solid coral',
                        'justify-content': 'center',
                        'align-items': 'center',
                        'display': 'flex', 
                        'flex-direction': 'column',
                        'padding': '10px',
                        'width': '50%',
                        'box-sizing': 'border-box',
                        'border-radius': '10px',
                        'color': 'white',
                        'box-shadow': '0 8px 20px rgba(0, 0, 0, 0.4)'
                });
            })
            .catch(error => {
                console.warn(error);
                $('#resultado').html('<p>Error al obtener los datos.</p>');
            })
            .finally(() => console.warn('La consulta ha finalizado'));
        } else {
            alert('Por favor, introduce una localidad');
        }
    });

    /*
    Si pulsamos el botón que tiene como id prevision pasamos a gestionae el código, como anteriormente prevenimos cualquier evento que no sea
    el que nosotro hemos manipulado, he ocultado el mapa por el mismo motivo que anteriormente, he puesto el gif animado y he guardo el valor
    de la localidad que el usuario introduce por el input.
    */
    $('#prevision').click(function(e){
        e.preventDefault();

        $('#mapaTiempo').hide();
        $('#resultado').html("<img src='ajax-loader.gif'>");
        let localidad = $('#localidad').val();

        //Si hay localidad volvemos a comprobar si tiene coma, sino añadimos ,es y buscará a nivel de España.
        if(localidad){
        let comprobarLocalizacion = localidad.includes(',') ? localidad : localidad + ',es';
        
        /*
        Para este he usado ajax, he introducido la URL con la calve y la localizacón, he añadido 3 días, el lenguaje en español y las alertas.
        Añadimos el tipo get, el tipo de datos recibido json y que sea una función asíncrona.
        */
        $.ajax({
            url: `https://api.weatherapi.com/v1/forecast.json?key=${CLAVE}&q=${comprobarLocalizacion}&days=3&aqi=no&lang=es&alerts=yes`,
            type: "GET",
            dataType: "json",
            async: true,
            //Si la petición recibe datos correctos, comenzamos con el código
            success: function(datos_devueltos){
                console.log(datos_devueltos);
                $("#resultado").html("<br />");
                //Hacemos una consulta forecast, esto me va a dar los 3 días completos.
                let tiempoDiasCompleto = datos_devueltos.forecast.forecastday;
                //Esto me va a dar los datos de la localización.
                let lugar = datos_devueltos.location;
                let mostrarTiempo = "";

                //Hacemos un bucle for para ir sacando cada día 
                for(let i = 0; i < tiempoDiasCompleto.length; i++){
                    //Separamos cada dia en una variable y con ello trabajamos.
                    let tiempoDia = tiempoDiasCompleto[i];
                    //También almacenamos las horas de ese día que queremos reflejar en la previsión.
                    let hora5 = tiempoDia.hour[5];
                    let hora14 = tiempoDia.hour[14];

                    //Aquí me he asegurado de que el índice del día de hoy es 0, por lo tanto le voy a añadir al div una clase, de lo contrario otra. 
                    //Para darle estilos diferentes al dia de hoy y que destaque.
                    let diaHoy = i === 0 
                            ? 'class="diaHoy"' 
                            : 'class="demasDias"';

                    //He mostradolos datos de manera estética como he hecho anteriormente.
                    mostrarTiempo += `<div ${diaHoy}>`;
                    mostrarTiempo += '<h3>Tiempo en ' + lugar.name + ' a día ' + tiempoDia.date + '</h3>';
                    mostrarTiempo += '<h5>' + lugar.region.toUpperCase() +', '+ lugar.country.toUpperCase() + '</h5>';
                    mostrarTiempo +=' <img src="https:' + tiempoDia.day.condition.icon + '" alt="' + tiempoDia.day.condition.text + '">';
                    mostrarTiempo += '<p><strong>Estado:</strong> ' + tiempoDia.day.condition.text + '</p>';
                    mostrarTiempo += '<p><strong>Temperatura Máxima: </strong>' + tiempoDia.day.maxtemp_c + '°C</p>';
                    mostrarTiempo += '<p><strong>Temperatura Mínima: </strong>' + tiempoDia.day.mintemp_c + '°C</p>';
                    mostrarTiempo += '<p><strong>Salida del sol:</strong> ' + tiempoDia.astro.sunrise + '</p>';
                    mostrarTiempo += '<p><strong>Puesta de sol:</strong> ' + tiempoDia.astro.sunset + '</p>';

                    //Me he asegurado de que en datos devueltos haya alerta y si es asímostrarla en color rojo, sino notificar que no hay alertas.
                    if(datos_devueltos.alerts && datos_devueltos.alerts.alert && datos_devueltos.alerts.alert.length > 0){
                        let alerta = datos_devueltos.alerts.alert.find(a => a.effective.includes(tiempoDia.date));
                        if(alerta){
                            mostrarTiempo += '<p style="color:red;"><strong>Alerta: </strong>' + alerta.event+ '</p>';
                        } else {
                            mostrarTiempo += '<p><strong>Alerta:</strong> No hay alertas para este día</p>';
                        }
                    } else {
                        mostrarTiempo += '<p><strong>Alerta:</strong> No hay alertas</p>';
                    }

                    //Aquí he mostrado los datos por las horas, lo he metido en un div con una clase diferente para darle otro estilo.
                    mostrarTiempo += '<div class="porDias">';

                        mostrarTiempo += '<h4>Previsión para las 5:00h</h4>';
                        mostrarTiempo += ' <img src="https:' + hora5.condition.icon + '" alt="' + hora5.condition.text + '">';
                        mostrarTiempo += '<p><strong>Estado: </strong>'+ hora5.condition.text +'</p>';
                        mostrarTiempo += '<p><strong>Temperatura: </strong>' + hora5.temp_c + '°C';
                        

                    mostrarTiempo += '</div>';
                    mostrarTiempo += '<div class="porDias">';

                        mostrarTiempo += '<h4>Previsión para las 14:00h</h4>';
                        mostrarTiempo +=' <img src="https:' + hora14.condition.icon + '" alt="' + hora14.condition.text + '">';
                        mostrarTiempo += '<p><strong>Estado: </strong>'+ hora14.condition.text +' </p>';
                        mostrarTiempo += '<p><strong>Temperatura: </strong>' + hora14.temp_c + '°C';
                        
                    mostrarTiempo += '</div>';

                    mostrarTiempo += '</div>';
                }

                //Muestro el resultado y cambio el css de la misma manera que anteriormente.
                $('#resultado').html(mostrarTiempo);
                $('.diaHoy').css({ 
                        'border': '4px solid coral',
                        'background-color': 'rgb(69, 150, 231)',
                        'justify-content': 'center',
                        'align-items': 'center',
                        'display': 'flex', 
                        'flex-direction': 'column',
                        'padding': '10px',
                        'width': '100%',
                        'box-sizing': 'border-box',
                        'border-radius': '10px',
                        'color': 'white'
                });

                $('.demasDias').css({ 
                        'background-color': 'rgb(125, 180, 235)',
                        'border': '1px solid coral',
                        'justify-content': 'center',
                        'align-items': 'center',
                        'display': 'flex', 
                        'flex-direction': 'column',
                        'padding': '10px',
                        'width': '100%',
                        'box-sizing': 'border-box',
                        'border-radius': '10px',
                        'color': 'white'
                });
                $(".porDias").css({
                        'border': '1px solid rgb(80, 130, 255)',
                        'border-radius':'10px',
                        'display':'flex',
                        'flex-direction': 'column',
                        'background-color': 'aliceblue',
                        'justify-content': 'center',
                        'align-items': 'center',
                        'color': 'rgb(80, 130, 255)',
                        'padding': '10px',
                        'margin': '10px',
                        'width': '80%',
                });
            },

            //Si hay un error lo mostramos por consola.
            error: function(xhr, estado, error_producido) {
                console.warn("Error producido: " + error_producido);
                console.warn("Estado: " + estado);
            },

            //Cuando se complete la función la mostramos por consola.
            complete: function(xhr, estado) {
                console.log("Petición completa");
            }
        });

    //Si el usuario no introduce la localidad mandamos una alerta para que la meta obligatoriamente.
    } else {
        alert('Por favor, introduce una localidad');
    }

    });

    //Vamos guardando los mapas, porque sino me salía en blanco.
    let mapaLeaflet;

    //Cuando pulsamos el botón con el id mapa realizamos una función
    $('#mapa').click(function () {
        //En el id resultado le decimos al usuario que pulse una localización por eso no he metido el gif animado, me parecía mejor informar
        $('#resultado').html('<p>Haz clic en el mapa para obtener la previsión.</p>');
        //Mostramos el mapa, he puesto que sea bloque porque sino tampoco conseguía que me lo mostrara.
        $('#mapaTiempo').css({
            'display': 'block',
            'border': '3px solid coral'    
        });

        //Si no hay un mapa lo creamos y lo incializamos.
        if (!mapaLeaflet) {
            //He usado setTimeout para que tarde un poco para que se cargue bien y no haya errores.
            setTimeout(() => {
                //Creamos el mapa y setView es para que elijas las coordenadas donde se va a mostar por defecto, he elegido españa a una escala de 6
                mapaLeaflet = L.map('mapaTiempo').setView([40.4168, -3.7038], 6);
                //Esto he buscado para ponerlo a color y que no saliera en blanco y negro.
                L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                    attribution: ''
                }).addTo(mapaLeaflet);

                //Ahora decimos que si clicamos en el mapa se guarde la latitud y la longitud en las variables.
                mapaLeaflet.on('click', function (e) {
                    let latitud = e.latlng.lat;
                    let longitud = e.latlng.lng;

                    // Por medio de axios y una consulta get, mandamos la clave la longitud y la latitud para que nos de el tiempos de ese dia.
                    axios.get(`https://api.weatherapi.com/v1/forecast.json?key=${CLAVE}&q=${latitud},${longitud}&days=1&lang=es`)
                    /*
                    Si hay datos devueltos guardamos la localización y el tiempo del día de hoy 
                     */
                        .then(datos_devueltos=> {
                            let lugar = datos_devueltos.data.location;
                            let dia = datos_devueltos.data.forecast.forecastday[0].day;

                            //Como hemos hecho anteriormente, le he añadido los datos de manera estetica y un div para poder cambiar el css.
                            //Intenté añadir el amanecer y anochecer, pero me daba error.
                            let info = '<div class="diasMapa">';
                            info += '<h3>Previsión para hoy en '+ lugar.name +' '+ lugar.country + '</h3>';
                            info += ' <img src="https:' + dia.condition.icon + '" alt="' + dia.condition.text + '">';
                            info += '<p><strong>Estado:</strong> '+ dia.condition.text+ '</p>';
                            info += '<p><strong>Temperatura máxima:</strong> '+ dia.maxtemp_c + '°C</p>';
                            info += '<p><strong>Temperatuta mínima:</strong> '+ dia.mintemp_c + '°C</p>';
                            //Mostramos el resultado y cambiamos el css.
                            $('#resultado').html(info);
                            $(".diasMapa").css({
                                    'background-color': 'rgb(125, 180, 235)',
                                    'justify-content': 'center',
                                    'align-items': 'center',
                                    'display': 'flex', 
                                    'flex-direction': 'column',
                                    'padding': '10px',
                                    'width': '60%',
                                    'box-sizing': 'border-box',
                                    'border-radius': '10px',
                                    'color': 'white'
                            });
                        })
                        //Capturamos los errores con catch e informamaos al usuario.
                        .catch(() => {
                            $('#resultado').html('<p>Error al obtener la previsión.</p>');
                        });
                });

                mapaLeaflet.invalidateSize(); //He tenido que usar esto por problemas de cargar el mapa cuando estaba oculto.
            }, 100);
        } else {
            setTimeout(() => {
                mapaLeaflet.invalidateSize();
            }, 100);
        }
    });




});