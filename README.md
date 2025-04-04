# 📈 StockTracker - Spring Boot Challenge

![CI](https://github.com/cmbessone/LeadIQcodechallenge/actions/workflows/ci.yml/badge.svg)

Aplicación RESTful construida con Spring Boot para consultar y persistir precios históricos de acciones usando la API de [Polygon.io](https://polygon.io).

---

## ✅ Objetivo

Desarrollar una API que:

- 🔍 Consume datos de precios de acciones desde Polygon.io
- 🗃️ Persiste los datos en una base de datos MySQL
- 📡 Expone endpoints para consultar y guardar datos
- 🧪 Contiene tests unitarios e integrales
- 🔁 Se ejecuta con pipeline CI en GitHub Actions

---

## 🛠️ Stack Tecnológico

- Java 22 + Spring Boot 3.2
- Maven
- MySQL (via Docker)
- JPA + Hibernate
- JUnit 5 + Mockito + MockMvc
- GitHub Actions

---
## Instrucciones

- Crear una cuenta gratuita en polygon.io
- Obtener tu API key 
- Crear un archivo .env en la raíz del proyecto con:
```POLYGON_API_KEY=aca_va_tu_api_key```
- Levantar la base de datos MySQL con Docker 
```docker-compose up -d```
- Ejecutar la aplicación 
```./mvnw spring-boot:run```
  - si te da un 401 Unauthorized usar explicitamente el APIKEY
  
    - ejemplo: ```POLYGON_API_KEY=aca_va_tu_api_key mvn spring-boot:run```
- Correr los Tests
```./mvnw test```
---
## 📦 Endpoints

### `POST /stocks/fetch`

Consulta la API externa y guarda los datos en la base local.

**Parámetros (query):**
- `companySymbol` → e.g. AAPL
- `fromDate` → e.g. 2023-11-01
- `toDate` → e.g. 2023-11-10

📌 **Ejemplo:**

```bash
curl -X POST "http://localhost:8080/stocks/fetch?companySymbol=AAPL&fromDate=2023-11-01&toDate=2023-11-10"
```
### `GET /stocks/{symbol}?date=YYYY-MM-DD`

Permite consultar un registro por símbolo y fecha:


📌 **Ejemplo:**

```bash
curl "http://localhost:8080/stocks/AAPL?date=2023-11-06"
```
