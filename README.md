# ¡Bienvenido al emocionante juego de búsqueda del tesoro!

En este juego, tendrás la oportunidad de explorar la hermosa ciudad de Vigo mientras buscas tesoros ocultos. Todo lo que necesitas es descargar nuestra aplicación de búsqueda del tesoro y comezar tu aventura! 

Tu objetivo sera encontrar las distintas localizaciones ocultas dentro de la ciudad. Este juego es ideal para jugar solo o con amigos, embarcate en esta aventura con tus amigos y descubre istintas localizaciones de la ciudad de Vigo!


## Instrucciones

Este juego no cuenta con ninguna release asi que para jugarlo necesitaras tener los siguientes recursos a mano: 

- Android Studio 

- API key de google maps 

- Un dispositivo android 

Para instalar la aplicacion descargue este reppositorio y abralo en android studio. Luego enchufe su dispositivo andorid y active la depuracion USB en las opciones de desarrollador (esto varia segun cada dispositivo, lo mas sencillo seria buscar "'nombre dispositivo' activar modo administrador"). Si android studio lee su dispositvo solo le queda un ultimo paso, vaya a "local.properties" e introduzca la API Key de google maps en el siguiente campo:

```
MAPS_API_KEY="TU_API_KEY"
```

Por ultimo, con el telefono conectado por usb, instale la aplicacion y disfrute de su busqueda del tesoro!

## Instrucciones

Este necesita permisos de localizacion para poder determinar donde estas respecto a la marca del tesoro, si no concedes los permisos el juego no comenzara. Una vez concedidos los permisos se centrara el mapa sobre tu localizacion.

El juego consistira en encontrar las distinatas marcas por la ciudad. Una vez te acerques lo sufuciente surgira un circulito resaltando el area donde esta la marca. Cuanto mas te acerques al area mas pequeño se hara el circulo. Una vez hayass alcanzado la marca esta desaparecera. Realiza esto con cada marca hasta completar todas las  localizaciones! 



![GifBusquedaTesoro](https://github.com/AlexFerMar/Imagenes/blob/main/ezgif.com-video-to-gif.gif)




## Emplea tus propias marcas!

Una de las ventajas de tener acceso al codigo de la aplicacion es poder añadir tus propias marcas para que tus amigos realicen una busqueda del tesoro que tu has preparado!

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

En este trazo de codigo es donde se declaran los distintos puntos del tesoro. Para añadir tus propias localizaciones solo deberas crear una nueva "var tesoro" y dentro del init introducir la longitud y latitud que hayas obtenido en maps. Una vez este preparado tu punto del tesoro añadelo a la lista "locations" y ya se encargara el programa de todo lo demas. Prepara tu propia busqueda del tesoro y lleva a tus amigo de paso por tus lugares y comercios favoritos!




