# 🌤️ WeatherApp - Applicazione Meteo

[![Java Version](https://img.shields.io/badge/Java-17-blue.svg)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

---

## 📋 Indice
- [Panoramica](#-panoramica)
- [Caratteristiche](#-caratteristiche)
- [Tecnologie Utilizzate](#-tecnologie-utilizzate)
- [Installazione](#-installazione)
- [Guida all'Uso](#-guida-alluso)
- [Output di Esempio](#-output-di-esempio)
- [Architettura](#-architettura-del-progetto)
- [Funzionalità Dettagliate](#-funzionalità-dettagliate)
- [Test](#-test)
- [Miglioramenti Futuri](#-miglioramenti-futuri)
- [Risoluzione Problemi](#-risoluzione-problemi)
- [Contributi](#-contributi)
- [Licenza](#-licenza)

---

## 📌 Panoramica

**WeatherApp** è un'applicazione web sviluppata in **Java con Spring Boot** che permette agli utenti di ottenere informazioni meteorologiche in tempo reale per qualsiasi città nel mondo. L'applicazione utilizza le API gratuite di **Open-Meteo** per recuperare dati meteorologici accurati e presenta i risultati in un'interfaccia utente moderna, responsive e con sfondi dinamici che cambiano in base alla temperatura.

---

## ✨ Caratteristiche

| Categoria | Funzionalità |
|-----------|--------------|
| **🔍 Ricerca** | Ricerca meteo per nome città con supporto a caratteri speciali e accenti |
| **🌡️ Dati Meteo** | Temperatura attuale, temperatura percepita, umidità, velocità del vento |
| **🎨 UI Dinamica** | Sfondi che cambiano in base alla temperatura con animazioni fluide |
| **📱 Responsive** | Design completamente responsive per desktop, tablet e mobile |
| **🛡️ Gestione Errori** | Gestione completa di città non valide, errori API e timeout |
| **📝 Logging** | Registrazione automatica di tutte le richieste su file di log |
| **🖼️ Icone Dinamiche** | Icone meteorologiche che cambiano in base alle condizioni |
| **🌍 Supporto Globale** | Supporto per città di tutto il mondo |

---

## 🛠️ Tecnologie Utilizzate

### Backend
| Tecnologia | Versione | Descrizione |
|------------|----------|-------------|
| **Java** | 17 | Linguaggio di programmazione |
| **Spring Boot** | 3.1.5 | Framework per applicazioni enterprise |
| **Spring Web** | - | Gestione richieste HTTP REST |
| **Thymeleaf** | - | Template engine per HTML dinamico |
| **Maven** | 3.6+ | Build tool e gestione dipendenze |

### Frontend
| Tecnologia | Descrizione |
|------------|-------------|
| **HTML5** | Struttura delle pagine |
| **CSS3** | Stili e animazioni personalizzate |
| **Bootstrap 5** | Framework CSS per design responsive |

### API & Servizi
| Servizio | Descrizione |
|----------|-------------|
| **Open-Meteo** | API gratuita per dati meteorologici |
| **Open-Meteo Geocoding** | Conversione nomi città in coordinate |

---

## 🚀 Installazione

### Prerequisiti

Assicurati di avere installato:

```bash
# Verifica Java
java -version
# Output richiesto: java version "17" o superiore

# Verifica Maven
mvn -version
# Output richiesto: Apache Maven 3.6.x o superiore
