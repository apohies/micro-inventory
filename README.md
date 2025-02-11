[prueba minas.postman_collection.json](https://github.com/user-attachments/files/18754268/prueba.minas.postman_collection.json)la prueba esta los 3 repositorios
# video
[video de youtube](https://www.youtube.com/watch?v=HmhTPLw7Pus)

# producto micro
[producto](https://github.com/apohies/microproduct)
puerto 8081
# inventario micro
[inventario](https://github.com/apohies/micro-inventory)
puerto 8080

# front angular
[front-angular](https://github.com/apohies/inventory-frontend)
bajar dependencia npm .
npm install
- ng serve



# docker bases de datos 

docker run -d \
--name mongodb \
-p 27017:27017 \
-e MONGO_INITDB_ROOT_USERNAME=admin \
-e MONGO_INITDB_ROOT_PASSWORD=password123 \
mongo

docker run -d \
--name postgres-inventory \
-e POSTGRES_USER=postgres \
-e POSTGRES_PASSWORD=password123 \
-e POSTGRES_DB=inventorydb \
-p 5432:5432 \
postgres:latest



