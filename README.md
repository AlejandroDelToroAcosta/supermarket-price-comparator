# 🛒 MarketComparator

MarketComparator is a supermarket price comparison platform that integrates data from multiple stores (currently **Mercadona** and **Carrefour**) through custom scraping, ETL, and a web interface.  
The system fetches products, builds a **Data Lake** and a **Data Mart**, and serves results via an **API + Mustache-based web frontend**.

---

## 📑 Table of Contents
1. [Project Overview](#project-overview)
2. [Features](#features)
3. [Architecture](#architecture)
4. [Technologies Used](#technologies-used)
5. [Installation & Setup](#installation--setup)
6. [Database Schema](#database-schema)
7. [API Documentation](#api-documentation)
8. [Example Usage](#example-usage)
9. [Development & Testing](#development--testing)
10. [Project Structure](#project-structure)
11. [Future Improvements](#future-improvements)
12. [License](#license)

---

## 1. Project Overview

MarketComparator automates:
1. **Scraping** product data from supermarkets.
2. **Transforming** and cleaning raw product data into a Data Mart.
3. **Serving** product search, filtering, and storage operations via an API.
4. **Displaying** results in a web interface with product cards (including images).

The goal is to enable quick price comparisons and category filtering between supermarkets.

---

## 2. Features

- 🛍 **Multi-supermarket scraping** (Mercadona, Carrefour).
- 🗄 **Data Lake** (CSV storage) and **Data Mart** (SQLite DB).
- 🔍 **Full-text search** on product names.
- 🗂 **Category-based filtering**.
- 💾 Save and view favorite products.
- 🖼 Product image display (via image URL).
- 🌐 Web frontend (Mustache templates).
- ⚡ Lightweight backend using Spark Java.

---

## 3. Architecture

```
+--------------------+        +-------------------+        +------------------+
|  Scrapers          |  --->  |   Data Lake (CSV) |  --->  |  Data Mart (SQLite) |
+--------------------+        +-------------------+        +------------------+
         |                                                          |
         v                                                          v
   Java CLI Modules                                           Query Engine API
         |                                                          |
         +--------------------> Web Frontend (Mustache) <-----------+
```

---

## 4. Technologies Used

- **Java 17**
- **Maven** (build tool)
- **Spark Java** (lightweight HTTP framework)
- **Mustache** (server-side templates)
- **SQLite** (Data Mart database)
- **Jsoup** (HTML parsing)
- **Gson** (JSON processing)

---

## 5. Installation & Setup

### Prerequisites
- Java 17+
- Maven 3.x
- SQLite 3 (optional for local inspection)

### Clone repository
```bash
git clone https://github.com/your-username/market-comparator.git
cd market-comparator
```

### Build
```bash
mvn clean package
```

### Running modules

#### 5.1 Scraping Carrefour
```bash
java -jar target/carrefour-scraper-1.0.jar "https://www.carrefour.es" "/path/to/output.csv"
```

#### 5.2 Scraping Mercadona
```bash
java -jar target/mercadona-scraper-1.0.jar "/path/to/output.csv"
```

#### 5.3 Building Data Mart
```bash
java -jar target/datamart-builder-1.0.jar "/path/to/input.csv" "/path/to/database.db"
```

#### 5.4 Starting API + Web
```bash
java -jar target/query-engine-1.0.jar "/path/to/database.db"
```
Open: [http://localhost:4567](http://localhost:4567)

---

## 6. Database Schema

### mercadona_products
```sql
CREATE TABLE IF NOT EXISTS mercadona_products (
    product_id TEXT PRIMARY KEY,
    display_name TEXT,
    unit_price REAL,
    category_name TEXT,
    category_id INTEGER,
    format TEXT,
    unit_size REAL,
    slug TEXT,
    packaging TEXT,
    url TEXT,
    image_url TEXT
);
```

### carrefour_products
```sql
CREATE TABLE IF NOT EXISTS carrefour_products (
    product_id TEXT PRIMARY KEY,
    name TEXT,
    unit_price REAL,
    unit TEXT,
    url TEXT,
    category_id TEXT,
    category TEXT,
    image_url TEXT,
    supermarket TEXT
);
```

---

## 7. API Documentation

| Method | Route           | Parameters                              | Description                          |
|--------|----------------|-----------------------------------------|--------------------------------------|
| GET    | `/`            | —                                       | Main web interface                   |
| GET    | `/buscar`      | `keyword`                               | Search products by name               |
| GET    | `/filtrar`     | `market`, `keyword`, `category`         | Filter results by supermarket/category |
| POST   | `/guardar`     | `name`, `price`, `supermarket`, `unitSize?` | Save product in memory                |
| GET    | `/guardados`   | —                                       | View saved products                   |

---

## 8. Example Usage

Example search request:
```bash
curl "http://localhost:4567/buscar?keyword=milk"
```

Example category filter:
```bash
curl "http://localhost:4567/filtrar?market=carrefour&keyword=milk&category=Dairy"
```

---

## 9. Development & Testing

Run tests:
```bash
mvn test
```

Check DB content manually:
```bash
sqlite3 database.db
sqlite> SELECT name, unit_price FROM carrefour_products LIMIT 5;
```

---

## 10. Project Structure
```
market-comparator/
│
├── scrapers/
│   ├── mercadona/
│   └── carrefour/
│
├── datalake-builder/
├── datamart-builder/
├── query-engine/
│   ├── controllers/
│   ├── services/
│   └── templates/   # Mustache views
│
├── docs/            # Technical documentation
└── README.md
```

---

## 11. Future Improvements
- Add more supermarkets (Dia, Lidl).
- Improve image caching (download locally to reduce load times).
- Add pagination to search results.
- Implement authentication for saved lists.
- Dockerize the entire stack.

---

## 12. License
This project is licensed under the [MIT License](LICENSE).

---

**For detailed technical documentation, database diagrams, and scraping strategy, see [`docs/`](docs/).**
