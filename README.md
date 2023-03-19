# ¡Bienvenido al emocionante juego de búsqueda del tesoro!

En este juego, tendrás la oportunidad de explorar la hermosa ciudad de Vigo mientras buscas tesoros ocultos. Todo lo que necesitas es descargar nuestra aplicación de búsqueda del tesoro y comenzar tu aventura! 

Tu objetivo será encontrar las distintas localizaciones ocultas dentro de la ciudad. Este juego es ideal para jugar solo o con amigos, embárcate en esta aventura con tus amigos y descubre distintas localizaciones de la ciudad de Vigo!


## Instrucciones

Este juego no cuenta con ninguna release asi que para jugarlo necesitaras tener los siguientes recursos a mano: 

- Android Studio 

- API key de google maps 

- Un dispositivo android 

Para instalar la aplicación descargué este repositorio y ábralo en Android Studio. Luego enchufe su dispositivo Android y active la depuración USB en las opciones de desarrollador (esto varía según cada dispositivo, lo más sencillo sería buscar “’nombre dispositivo’ activar modo administrador"). Si Android Studio lee su dispositivo solo le queda un último paso, vaya a “local.properties” e introduzca la API Key de Google Maps en el siguiente campo:

```
MAPS_API_KEY="TU_API_KEY"
```

Por último, con el teléfono conectado por USB, instale la aplicación y disfrute de su búsqueda del tesoro!

## Instrucciones

Este necesita permisos de localización para poder determinar donde estás respecto a la marca del tesoro, si no concedes los permisos el juego no comenzará. Una vez concedidos los permisos se centrará el mapa sobre tu localización.

El juego consistirá en encontrar las distintas marcas por la ciudad. Una vez te acerques lo suficiente surgirá un círculo resaltando el área donde está la marca. Cuanto más te acerques al área más pequeño se hará el círculo. Una vez hayas alcanzado la marca, esta desaparecerá. Realiza esto con cada marca hasta completar todas las localizaciones!



![GifBusquedaTesoro](https://github.com/AlexFerMar/Imagenes/blob/main/ezgif.com-video-to-gif.gif)




## Emplea tus propias marcas!

Una de las ventajas de tener acceso al código de la aplicación es poder añadir tus propias marcas para que tus amigos realicen una búsqueda del tesoro que tú has preparado!

```kotlin

    var tesoro1 = Location("tesoro1")
    var tesoro2 = Location("tesoro2")
    var tesoro3 = Location("tesoro3")

    init {
        tesoro1.latitude = 42.23699434605001
        tesoro1.longitude = -8.714858173423254

        tesoro2.latitude = 42.23478669846102
        tesoro2.longitude = -8.719721794117492

        tesoro3.latitude = 42.241187652778876
        tesoro3.longitude = -8.72290017054465

    }

    private val locations = listOf(tesoro1, tesoro2, tesoro3)

```

En este trazo de código es donde se declaran los distintos puntos del tesoro. Para añadir tus propias localizaciones solo deberás crear una nueva "var tesoro" y dentro del init introducir la longitud y latitud que hayas obtenido en maps. Una vez este preparado tu punto del tesoro, añádelo a la lista "locations" y ya se encargará el programa de todo lo demás. Prepara tu propia búsqueda del tesoro y lleva a tus amigos de paso por tus lugares y comercios favoritos!




