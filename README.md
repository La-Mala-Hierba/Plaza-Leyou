# Plaza-Leyou
Plaza Leyou es una plataforma de comercio electrónico (B2C) con cobertura a todas las secciones de artículos, desarrolladas con separación de backend y front-end. Consiste en dos partes:

Front-End, incluye leyou-portal y leyou-manager-web

leyou-portal 🡪 proyecto realizado con la tecnología Server-side Rendering (combinado por Vue y Nuxt), dirigido a los usuarios quienes pueden:
  1) buscar los productos interesados con palabra de clave.
  2) añadir los artículos a la cesta.
  3) confirmar el pedido y realizar la compra online.
  4) colocar sus comentarios sobre los productos comprados.
  
leyou-manager-web 🡪 Gestión de artículos. Proyecto realizado en base SPA con el framework Vue.js, dirigido a los administradores de la plataforma, pueden realizar todo tipo de gestiones sobre los artículos, incluye: 
  1) hacer el alta y la baja de los productos, hacer CRUD de las marcas, las categorías, las especificaciones de los artículos.
  2) los análisis de venta, incluye: estadísticas de peticiones, gestiones de reembolsos, promociones, etc.
  3) los usuarios, incluye: el control, el bloqueo y el desbloqueo de sus cuentas.
  4) la autentificación de toda la plataforma, incluye: la activación de la autenticación, la introducción de información de autenticación para el inicio de sesión y las especificaciones de las funciones disponibles.
  
Back-End: leyou con 12 microservicios, basado en microservicios de SpringCloud, aporta interfaces de las API RESTful a clientes externos. Tanto leyou-manager-web como leyou-portal comparten estas interfaces. Con JWT, los microservicios validen la identidad de los clientes externos, y después autorizan el acceso de API interfaz a ellos.

Estructura del proyecto
![Image text](https://github.com/La-Mala-Hierba/Plaza-Leyou/blob/master/Plaza%20Leyou/img/Estrucutra%20del%20proyecto.png)

Estructura de microservicios
![Image text](https://github.com/La-Mala-Hierba/Plaza-Leyou/blob/master/Plaza%20Leyou/img/Whole%20Process.pngg)

item-service análisis
![Image text](https://github.com/La-Mala-Hierba/Plaza-Leyou/blob/master/Plaza%20Leyou/img/item-service-databases-anaylisis.png)

Messeage Queue application
![Image text](https://github.com/La-Mala-Hierba/Plaza-Leyou/blob/master/Plaza%20Leyou/img/MQ.png)

JWT application
![Image text](https://github.com/La-Mala-Hierba/Plaza-Leyou/blob/master/Plaza%20Leyou/img/jwt%20process.png)

Cesta de compra
![Image text](https://github.com/La-Mala-Hierba/Plaza-Leyou/blob/master/Plaza%20Leyou/img/Cesta.png)

leyou-portal
![Image text](https://github.com/La-Mala-Hierba/Plaza-Leyou/blob/master/Plaza%20Leyou/img/homepage.jpg)

Proceso de hacer pedido
![Image text](https://github.com/La-Mala-Hierba/Plaza-Leyou/blob/master/Plaza%20Leyou/img/PO%20Process.png)

leyou-manager-web
![Image text](https://github.com/La-Mala-Hierba/Plaza-Leyou/blob/master/Plaza%20Leyou/img/leyou-manage.jpg)
![Image text](https://github.com/La-Mala-Hierba/Plaza-Leyou/blob/master/Plaza%20Leyou/img/Category.png)
![Image text](https://github.com/La-Mala-Hierba/Plaza-Leyou/blob/master/Plaza%20Leyou/img/brands.png)
![Image text](https://github.com/La-Mala-Hierba/Plaza-Leyou/blob/master/Plaza%20Leyou/img/Product.png)
![Image text](https://github.com/La-Mala-Hierba/Plaza-Leyou/blob/master/Plaza%20Leyou/img/Specification.png)





