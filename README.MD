## Integrantes:
- Santiago Agredo Vallejo
- Brain Rey Castillo
- Arturo Restrepo Ruiz
- Pablo Jose Restrepo Ruiz
- Jojan Esteban Serna Serna

# !Nota!: El codigo solo funciona con windows
## Instrucciones de uso

### Cree las bases de datos con los scripts usando DB Browser for SQLite en las siguiente ruta
- C:\sqlite\db
#### Base de datos de openmarket, se debe llamar `openmarket.db`
#### Base de datos de el servicio de pagos, se debe llamar `payment-service.db`

#### Ahora construya el proyecto, si desea correr pruebas use la interfaz de el ide de su preferencia `vscode` o `netbeans`

#### Ejecute los siguientes comandos

- Para instalar el proyecto desde la raiz de este repo usando el pom global: `mvn install -DskipTests`
- Para correr el servidor corra: `mvn exec:java -pl openmarket-server`, pase como argumento `5000` cuando se le solicite el puerto
- Para correr el cliente corra: `mvn exec:java -pl openmarket-client`

