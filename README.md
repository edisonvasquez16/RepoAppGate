# Automatización ejemplo Appgate
> Repositorio donde se realiza la creación de Proyecto de Automatización para el ejercicio solicitado por Appgate.
Basada en un patrón de Diseño ScreenPlay el cual nos permite estructurar en el MAIN los paquetes de
>- Consequences: Validación de respuesta de servicio
>- Tasks: Ejecución de Pasos y acciones sobre los servicios
>- Endpoints: Almacena las clases como enums encargadas de contener los path de los endpoints a Automatizar
>- Factories: Clases encargadas de contruir los objetos java para enviar a los endPoints
>- Models: Su objetivo es facilitar el uso de los datos como objetos java
>
>Una estructura Test donde nos permite contruir
>- Features: Definición de test y escenarios a ejecutar
>- Conf: Configuración general que puede ser utilizada por cada uno de los features
>
>Se utilizó SerenityRest por la facilidad de integración y contruscción de test basado en BDD

**Pre-requisitos**
- jdk11
- maven 3.6.3

**Ejecución**
- mvn clean verify test

**Construido con**
- Maven
- Lombok
- Restassured
- SerenityRest
- Java