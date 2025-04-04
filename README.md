# 📈 StockTracker

Code challenge de seguimiento de acciones utilizando Spring Boot, MySQL y la API de Polygon.io.  
Arquitectura limpia, con enfoque modular y buenas prácticas.

---

## 🚀 Requisitos

- Java 17+
- Maven 3.8+
- Docker (para levantar la base de datos)
- Cuenta en [Polygon.io](https://polygon.io/) con una API Key válida

---

## ⚙️ Configuración del entorno

1. **Clonar el proyecto**
```bash
git clone https://github.com/tu-usuario/stocktracker.git
cd stocktracker
```

2. **Crear archivo .env en la raíz del proyecto**
```bash
touch .env
```
3. **Agregar dentro del archivo tu api key**
```bash
POLYGON_API_KEY=tu_api_key_de_polygon
```
4. **Levantar base de datos MySQL con Docker**
```bash
docker run --name mysql-stocktracker -e MYSQL_ROOT_PASSWORD=root \
-e MYSQL_DATABASE=stocktracker -p 3306:3306 -d mysql:8.0
```

5. **Ejecutar la aplicacion**
```bash
./mvnw spring-boot:run
```