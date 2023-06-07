## Integrantes:
- Santiago Agredo Vallejo
- Braian Rey Castillo
- Arturo Restrepo Ruiz
- Pablo Jose Restrepo Ruiz
- Jojan Esteban Serna Serna

# !Nota!: El codigo solo funciona con windows
## Instrucciones de uso

### Cree las bases de datos con los scripts usando DB Browser for SQLite en las siguiente ruta
- C:\sqlite\db
#### Base de datos de openmarket, se debe llamar `openmarket.db`
#### Nota: Todas las contraseñas hasheadas aqui son la siguiente contraseña: 1
#### Base de datos de el servicio de pagos, se debe llamar `payment-service.db`

#### Ahora construya el proyecto, si desea correr pruebas use la interfaz de el ide de su preferencia `vscode` o `netbeans`

#### Ejecute los siguientes comandos

- Para instalar el proyecto desde la raiz de este repo usando el pom global: `mvn install -DskipTests`
- Para correr el servidor corra: `mvn exec:java -pl openmarket-server`, pase como argumento `5000` cuando se le solicite el puerto
- Para correr el cliente corra: `mvn exec:java -pl openmarket-client`

## Anexos:
- Trello (Solo crear cuenta y unirse): https://trello.com/invite/b/ts8SiGtt/ATTI4afe86b5fbe6357cd975ebd6b86a3da385B8811A/openmarket 
- Enlace de documentación del diseño: https://drive.google.com/drive/folders/1N8DY-TmoNFQnw6Cs0o7UKKWY6pedps18?usp=sharing
- Informe: https://unicaucaeduco-my.sharepoint.com/:w:/g/personal/jeserna_unicauca_edu_co/EVxYjpuzYBVLlKD3C4kJ7G8BZYQCsiOdNGrGVAplM6pFPA?e=L553kb
