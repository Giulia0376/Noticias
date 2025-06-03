
Aplicativo de Noticias 

Al abrir la app, se solicita al usuario ingresar su nombre y correo electrónico. Estos datos se guardan mediante SharedPreferences, lo que permite iniciar una sesión persistente.
Una vez iniciada la sesión, se presenta una lista de noticias obtenidas desde un API REST, cada una con su imagen y descripción. Al seleccionar una noticia, se abre una nueva pantalla donde se muestran todos los detalles de la misma, incluyendo opciones para compartir la noticia y visitar la fuente original.
La aplicación también permite cerrar sesión desde un menú desplegable ubicado en la barra superior, y cuenta con un ícono de búsqueda para facilitar la navegación entre noticias.


-	La aplicación tiene al menos cuatro actividades:
•	UsuarioActivity: para iniciar sesión.
•	MainActivity2: donde se muestra la lista de noticias.
•	DetailActivity2: muestra la información detallada de una noticia.
•	SettingsActivity1
-	El nombre y correo del usuario se guardan en SharedPreferences cuando presiona el botón "Guardar Usuario".
   Esto permite mantener la sesión activa incluso si se cierra y vuelve a abrir la app.
-	En la AppBar se encuentra un ícono de búsqueda y un menú con opciones como "Cerrar sesión".
-	Se ha incorporado el logo de noticias como ícono de la app, mejorando la presentación visual.



Practica realizada por: Giuliana Miranda Roman a fecha de 02/06/25
Repositorio de entrega: https://github.com/Giulia0376/Noticias.git
