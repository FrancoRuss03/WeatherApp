# 🌤️ WeatherApp

[![Java Version](https://img.shields.io/badge/Java-17-blue.svg)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Build](https://img.shields.io/badge/Build-Maven-orange.svg)](https://maven.apache.org/)
[![Status](https://img.shields.io/badge/Status-Active-success.svg)]()

> Applicazione web meteo in tempo reale, sviluppata con Java e Spring Boot, con interfaccia dinamica e sfondi adattativi basati sulla temperatura.

---

## 📋 Indice

- [Panoramica](#-panoramica)
- [Caratteristiche](#-caratteristiche)
- [Tecnologie Utilizzate](#-tecnologie-utilizzate)
- [Requisiti di Sistema](#-requisiti-di-sistema)
- [Installazione](#-installazione)
- [Guida all'Uso](#-guida-alluso)
- [Architettura del Progetto](#-architettura-del-progetto)
- [Funzionalità Dettagliate](#-funzionalità-dettagliate)
- [Test](#-test)
- [Logging](#-logging)
- [Miglioramenti Futuri](#-miglioramenti-futuri)
- [Risoluzione Problemi](#-risoluzione-problemi)
- [Contributi](#-contributi)
- [Licenza](#-licenza)
- [Contatti](#-contatti)

---

## 📌 Panoramica

**WeatherApp** è un'applicazione web sviluppata in **Java con Spring Boot** che permette di ottenere informazioni meteorologiche in tempo reale per qualsiasi città nel mondo.

L'applicazione si integra con le API gratuite di **[Open-Meteo](https://open-meteo.com/)** per recuperare dati accurati e aggiornati, presentandoli tramite un'interfaccia moderna, responsive e con sfondi dinamici che si adattano alla temperatura rilevata.

---

## ✨ Caratteristiche

| Categoria | Funzionalità |
|-----------|--------------|
| 🔍 **Ricerca** | Ricerca meteo per nome città con supporto a caratteri speciali e accenti |
| 🌡️ **Dati Meteo** | Temperatura attuale, percepita, umidità e velocità del vento |
| 🎨 **UI Dinamica** | Sfondi e animazioni che cambiano in base alla temperatura |
| 📱 **Responsive** | Design ottimizzato per desktop, tablet e mobile |
| 🛡️ **Gestione Errori** | Copertura completa di città non valide, errori API e timeout |
| 📝 **Logging** | Registrazione automatica di tutte le richieste su file di log |
| 🖼️ **Icone Dinamiche** | Icone meteorologiche contestuali alle condizioni rilevate |
| 🌍 **Supporto Globale** | Compatibile con città di tutto il mondo |

---

## 🛠️ Tecnologie Utilizzate

### Backend

| Tecnologia | Versione | Descrizione |
|------------|----------|-------------|
| Java | 17 | Linguaggio di programmazione principale |
| Spring Boot | 3.1.5 | Framework per applicazioni enterprise |
| Spring Web | — | Gestione richieste HTTP e REST |
| Thymeleaf | — | Template engine per HTML dinamico lato server |
| Maven | 3.6+ | Build tool e gestione delle dipendenze |

### Frontend

| Tecnologia | Descrizione |
|------------|-------------|
| HTML5 | Struttura semantica delle pagine |
| CSS3 | Stili personalizzati e animazioni |
| Bootstrap 5 | Framework CSS per layout responsive |

### API & Servizi Esterni

| Servizio | Descrizione |
|----------|-------------|
| [Open-Meteo](https://open-meteo.com/) | API gratuita per dati meteorologici in tempo reale |
| [Open-Meteo Geocoding](https://open-meteo.com/en/docs/geocoding-api) | Conversione nome città → coordinate geografiche |

---

## 💻 Requisiti di Sistema

Prima di procedere con l'installazione, assicurati di avere:

- **Java 17** o superiore
- **Apache Maven 3.6** o superiore
- Connessione internet attiva (necessaria per le chiamate alle API)

Per verificare le versioni installate:

```bash
java -version
mvn -version
```

---

## 🚀 Installazione

### 1. Clona il repository

```bash
git clone https://github.com/yourusername/weather-app.git
cd weather-app
```

### 2. Compila il progetto

```bash
mvn clean compile
```

### 3. Avvia l'applicazione

```bash
mvn spring-boot:run
```

### 4. Apri nel browser

```
http://localhost:8080
```

### Alternativa: esecuzione tramite JAR

```bash
# Genera il package eseguibile
mvn clean package

# Avvia il JAR
java -jar target/weather-spring-1.0.0.jar
```

> **Nota:** se la porta `8080` è già occupata, puoi cambiarla modificando il file `src/main/resources/application.properties`:
> ```properties
> server.port=9090
> ```

---

## 📖 Guida all'Uso

1. Apri l'applicazione nel browser all'indirizzo `http://localhost:8080`
2. Inserisci il nome di una città nel campo di ricerca
3. Clicca su **"Get Weather"** per visualizzare i risultati

### Dati visualizzati

| Elemento | Descrizione |
|----------|-------------|
| 🌍 Nome Città | Nome ufficiale della città cercata |
| 🌡️ Temperatura | Temperatura attuale in gradi Celsius |
| 🤔 Percepita | Temperatura percepita (wind chill / heat index) |
| 💧 Umidità | Percentuale di umidità relativa |
| 🌬️ Vento | Velocità del vento in km/h |
| 📝 Condizioni | Descrizione testuale delle condizioni climatiche |
| 🎨 Sfondo | Immagine dinamica adattiva alla temperatura |

### Gestione degli errori

| Scenario | Messaggio mostrato |
|----------|--------------------|
| Città non trovata | `❌ Città non trovata: [nome città]` |
| Errore interno API | `❌ Errore API: 500 - Internal Server Error` |
| Timeout connessione | `❌ Timeout connessione API` |
| Campo di ricerca vuoto | `❌ Città non trovata` |

### Esempio di output

```
========================================
           📊 PREVISIONI METEO
========================================

🌍 Città:        Roma
🌡️ Temperatura:  18.5°C
🤔 Percepita:    17.2°C
💧 Umidità:      65%
🌬️ Vento:        12.3 km/h
📝 Condizioni:   Leggermente fresco

========================================
```

---

## 🏗️ Architettura del Progetto

```
weather-spring/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/weather/spring/
│   │   │       ├── WeatherApplication.java       # Entry point dell'applicazione
│   │   │       ├── controller/
│   │   │       │   └── WeatherController.java    # Gestione delle richieste HTTP
│   │   │       ├── service/
│   │   │       │   ├── WeatherService.java       # Interfaccia del servizio meteo
│   │   │       │   └── OpenMeteoService.java     # Implementazione con API Open-Meteo
│   │   │       ├── model/
│   │   │       │   └── WeatherData.java          # Modello dati meteo
│   │   │       └── utils/
│   │   │           └── ApiClient.java            # Client HTTP per chiamate esterne
│   │   └── resources/
│   │       ├── templates/
│   │       │   └── weather.html                  # Template Thymeleaf
│   │       └── static/
│   │           └── css/
│   │               └── style.css                 # Stili CSS e animazioni
│   └── test/                                     # Test unitari e di integrazione
├── logs/                                         # File di log generati a runtime
└── pom.xml                                       # Configurazione Maven
```

### Flusso dei dati

```
Utente
  └─► WeatherController
        └─► OpenMeteoService
              ├─► Geocoding API  →  Coordinate geografiche
              └─► Weather API    →  Dati meteo
                    └─► WeatherData
                          └─► Thymeleaf  →  Pagina HTML  →  Utente
```

---

## 🎨 Funzionalità Dettagliate

### Descrizioni meteo per fascia di temperatura

| Temperatura | Descrizione | Icona |
|-------------|-------------|-------|
| ≤ −10°C | Freddo polare | ❄️ |
| −10°C ÷ 0°C | Gelido | 🧊 |
| 0°C ÷ 5°C | Molto freddo | 🧣 |
| 5°C ÷ 10°C | Freddo | 🍂 |
| 10°C ÷ 15°C | Fresco | 🌬️ |
| 15°C ÷ 20°C | Leggermente fresco | 🌿 |
| 20°C ÷ 25°C | Mite | 🌸 |
| 25°C ÷ 30°C | Caldo | ☀️ |
| 30°C ÷ 35°C | Molto caldo | 🔥 |
| ≥ 35°C | Torrido | 🌋 |

### Sfondi dinamici e animazioni

| Temperatura | Sfondo | Animazione | Durata |
|-------------|--------|------------|--------|
| ≤ −15°C | Freddo polare | Slow zoom | 25s |
| −15°C ÷ −10°C | Aurora boreale | Aurora move | 20s |
| −10°C ÷ −5°C | Montagne innevate | Slow zoom | 22s |
| −5°C ÷ 0°C | Paesaggio invernale | Snow fall | 15s |
| 0°C ÷ 5°C | Neve che si scioglie | Slow zoom | 20s |
| 5°C ÷ 10°C | Primo verde primaverile | Gentle breeze | 18s |
| 10°C ÷ 13°C | Fiori di melo | Gentle pulse | 20s |
| 13°C ÷ 16°C | Prati fioriti | Gentle breeze | 20s |
| 16°C ÷ 19°C | Colline toscane | Slow zoom | 25s |
| 19°C ÷ 22°C | Laghi e foreste | Gentle breeze | 20s |
| 22°C ÷ 25°C | Girasoli | Gentle pulse | 25s |
| 25°C ÷ 28°C | Spiaggia dorata | Gentle pulse | 18s |
| 28°C ÷ 32°C | Mare mediterraneo | Gentle pulse | 15s |
| 32°C ÷ 35°C | Deserto | Heat haze | 12s |
| ≥ 35°C | Vulcano | Volcano pulse | 8s |

---

## 🧪 Test

### Esecuzione dei test

```bash
# Tutti i test
mvn test

# Solo test unitari
mvn test -Punit-tests

# Solo test di integrazione
mvn verify -Pintegration-tests

# Genera report di coverage (JaCoCo)
mvn test jacoco:report
```

Il report HTML di coverage sarà disponibile in `target/site/jacoco/index.html`.

### Copertura attuale

| Modulo | Copertura | Stato |
|--------|-----------|-------|
| Controller | 85% | ✅ |
| Service | 90% | ✅ |
| Utils | 80% | ✅ |
| Model | 95% | ✅ |

---

## 📝 Logging

L'applicazione registra automaticamente tutte le operazioni nel file:

```
logs/weather-app.log
```

### Formato dei log

```
[2024-01-15 14:30:45] INFO  - Richiesta meteo: città=Roma
[2024-01-15 14:30:46] INFO  - API chiamata: geocoding.open-meteo.com
[2024-01-15 14:30:47] INFO  - Dati meteo recuperati: 18.5°C, 65% umidità
[2024-01-15 14:30:47] INFO  - Risposta inviata all'utente
```

---

## 🔮 Miglioramenti Futuri

### 🔴 Priorità alta

- **Cache delle richieste** — memorizzare i risultati recenti per ridurre le chiamate API
- **Previsioni a 5 giorni** — aggiungere previsioni meteo estese
- **Supporto multilingua** — traduzioni in inglese, francese, tedesco e spagnolo
- **Geolocalizzazione automatica** — rilevamento automatico della posizione dell'utente

### 🟡 Priorità media

- **Città preferite** — permettere agli utenti di salvare le città più cercate
- **Grafici meteo** — visualizzare trend di temperatura con grafici interattivi
- **Notifiche meteo** — invio di notifiche push per condizioni meteorologiche estreme
- **Condivisione social** — condivisione rapida del meteo su Facebook, Twitter, WhatsApp

### 🟢 Priorità bassa

- **Widget embeddable** — widget da incorporare in siti web di terze parti
- **API REST pubblica** — esporre un'API per sviluppatori esterni
- **Dark mode automatica** — tema scuro attivato in base all'orario del giorno
- **Podcast meteo giornaliero** — audio generato con le previsioni del giorno

---

## 🐛 Risoluzione Problemi

| Problema | Possibile causa | Soluzione |
|----------|-----------------|-----------|
| ❌ Città non trovata | Nome errato o non supportato | Verifica l'ortografia; prova con il nome inglese |
| ⏱️ Timeout connessione | Connessione assente o instabile | Controlla la connessione; aumenta il timeout in `application.properties` |
| 🔌 Porta 8080 occupata | Un'altra applicazione usa la stessa porta | Cambia porta in `application.properties` (`server.port=9090`) |
| 🔨 Errori di compilazione | Dipendenze Maven non risolte | Esegui `mvn clean install` |
| 🌐 API non risponde | Server Open-Meteo temporaneamente non disponibile | Riprova più tardi; consulta lo [stato dei servizi Open-Meteo](https://open-meteo.com/) |

---

## 👥 Contributi

I contributi sono benvenuti! Segui questi passaggi per proporre una modifica:

1. **Fork** del repository
2. Crea un branch dedicato alla tua feature:
   ```bash
   git checkout -b feature/NomeFeature
   ```
3. Effettua le modifiche e committa:
   ```bash
   git commit -m "feat: descrizione della modifica"
   ```
4. Pusha il branch:
   ```bash
   git push origin feature/NomeFeature
   ```
5. Apri una **Pull Request** descrivendo le modifiche apportate

Per segnalare bug o richiedere nuove funzionalità, apri una [issue su GitHub](https://github.com/yourusername/weather-app/issues).

---

## 📄 Licenza

Questo progetto è distribuito sotto licenza **MIT**. Consulta il file [`LICENSE`](./LICENSE) per i dettagli completi.

---

## 📞 Contatti

| Tipo | Riferimento |
|------|-------------|
| 📧 Email | russo.franco369@gmail.com |
| 🐛 Segnalazioni bug | [Apri una issue](https://github.com/yourusername/weather-app/issues) |
| 💬 Discussioni | [GitHub Discussions](https://github.com/yourusername/weather-app/discussions) |

---

<div align="center">

🌤️ **WeatherApp** — Il tuo compagno meteorologico quotidiano

Sviluppato con ❤️ · [⬆ Torna all'inizio](#-weatherapp)

</div>
