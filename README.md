# Nicequest Appium Tests

Este repositorio contiene pruebas automatizadas para la app móvil de **Nicequest**, utilizando **Cucumber + Appium + Java (TestNG)**.  
El objetivo es validar los flujos principales de la aplicación mediante escenarios **end-to-end**.

---

## 📌 Requisitos

Antes de comenzar, asegúrate de tener instalado:

- [Java JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) o superior  
- [Maven 3.9+](https://maven.apache.org/download.cgi)  
- [Android Studio](https://developer.android.com/studio) (incluye SDK y AVD Manager)  
- [Appium Server v2+](https://appium.io/docs/en/about-appium/getting-started/)  
- [Node.js](https://nodejs.org/) (v18 o superior)  
- [Git](https://git-scm.com/)

---

## 📦 Instalación

Clonar el repositorio:
```bash
git clone https://github.com/cristiangarc94/nicequest-appium-tests.git
cd nicequest-appium-tests
```
Instalar dependencias (Maven lo hace automáticamente al correr los tests):
```
mvn clean install
```

---

## 🔧 Configuración
### 1️. Emulador Android
Debes tener configurado un AVD en Android Studio.
Ejemplo: Medium_Phone_API_35.
Verifica con:
```
emulator -list-avds
```

### 2️. Appium
Instalar Appium globalmente si no lo tienes:
```
npm install -g appium
```
Verificar drivers disponibles:
```
appium driver list
```
Debes tener instalados:
```
uiautomator2 (Android)
xcuitest (iOS, opcional)
```

### 3. Archivo `.gitignore`
Este archivo ya está incluido en el proyecto, pero asegúrate de que contiene:
```gitignore
/target/
/.idea/
/*.iml
*.log
*.env
```  
Esto evitará que se suban dependencias innecesarias y credenciales sensibles.

### 4️. Archivo de configuración `config.properties`

Las Appium capabilities están externalizadas en:
```
src/test/resources/config.properties
```

Ejemplo:

### Configuración común
appium.server=http://127.0.0.1:4723/

### Android capabilities
android.deviceName=Android Emulator
android.platformName=Android
android.appPackage=com.netquest.pokey
android.appActivity=com.netquest.pokey.MainActivity
android.noReset=true

### iOS capabilities
ios.deviceName=iPhone Simulator
ios.platformName=iOS
ios.bundleId=com.netquest.pokey
ios.noReset=true

---

## 🚀 Ejecución de Pruebas
### 1. Selección de plataforma
La plataforma se pasa como parámetro Maven:

Android:
```
mvn test -Dplatform=Android
```

iOS:
```
mvn test -Dplatform=iOS
```

Si no se especifica, el valor por defecto es Android.

### 2. Modo rápido (recomendado)
Arranca el emulador manualmente:
```
emulator -avd Medium_Phone_API_35
```
Arranca Appium:
```
appium
```
Ejecuta los tests:
```
mvn test
```

### 3. Modo demo (arranque automático)
En DriverManager está comentado el código para arrancar emulador y Appium desde Java:
```
/*
EmulatorManager.startEmulator("Medium_Phone_API_35");
AppiumManager.startAppium();
*/
```
👉 Útil en CI/CD o entornos donde quieras que todo sea automático.


## 📁 Estructura del Proyecto
```
Nicequest/
├── pom.xml
├── src/main/java/com/nicequest/utils/
│   ├── ConfigReader.java         # Lee config.properties
│   ├── DriverManager.java        # Configuración central del driver
│   ├── EmulatorManager.java      # Opcional (arranque automático de AVD)
│   ├── AppiumManager.java        # Opcional (arranque automático de Appium)
│   └── ScreenshotUtil.java       # Captura de pantallas en caso de fallo
├── src/test/java/com/nicequest/pages/
│   ├── DashboardPage.java        # Page Object del dashboard
│   └── LoginPage.java            # Page Object del login
├── src/test/java/com/nicequest/steps/
│   └── LoginSteps.java           # Step Definitions de Cucumber
├── src/test/java/com/nicequest/utils/
│   └── Hooks.java                # Hooks de Cucumber (before/after, screenshots)
├── src/test/java/com/nicequest/runners/
│   └── LoginRunner.java          # Runner de Cucumber (JUnit/TestNG)
└── src/test/resources/
    ├── config.properties         # Configuración de capabilities
    └── features/
        └── login.feature         # Escenarios en Gherkin
```

## 🚀 Ejecución de Pruebas

### Escenario: Validar login con múltiples credenciales
- **Archivo**: login.feature
- **Descripción**: Validar inicio de sesión con diferentes combinaciones de email y contraseña en Android e iOS.
- **Story Points**: 5
```
Feature: Login

  @positive @android @ios
  Scenario Outline: Successful login with valid credentials
    Given Open Nicequest login page on "<platform>"
    When I enter email "<email>"
    And I enter password "<password>"
    And I tap login button
    Then I should see "Dashboard visible"

    Examples:
      | platform | email              | password   |
      | Android  | valid.user@test.com | correct123 |
      | iOS      | valid.user@test.com | correct123 |

  @negative
  Scenario Outline: Login with invalid credentials
    Given Open Nicequest login page on "<platform>"
    When I enter email "<email>"
    And I enter password "<password>"
    And I tap login button
    Then I should see "<expectedMessage>"

    Examples:
      | platform | email               | password   | expectedMessage       |
      | Android  | bademail.com        | correct123 | Invalid email format  |
      | iOS      | bademail.com        | correct123 | Invalid email format  |
      | Android  | valid.user@test.com | wrongpass1 | Incorrect password    |
      | iOS      | valid.user@test.com | wrongpass1 | Incorrect password    |
      | Android  |                     |            | Email is required     |
      | iOS      |                     |            | Email is required     |
      | Android  | user@test.com       |            | Password is required  |
      | iOS      | user@test.com       |            | Password is required  |

```
Esto permite ejecutar todas las combinaciones de login válidas e inválidas de manera parametrizada.

## 🛠️ Buenas prácticas aplicadas
- Page Object Model (POM) → cada pantalla encapsula sus elementos y acciones.
- DriverManager + ConfigReader → centralizan y externalizan configuración del driver.
- Hooks + ScreenshotUtil → capturas automáticas en caso de fallo.
- Scenarios parametrizados → Scenario Outline + Examples para evitar duplicación.
- Tags en Gherkin → permiten ejecutar subsets (@positive, @negative, @android, @ios).
- Waits explícitos → sincronización estable antes de interactuar con elementos.

## 🔮 Futuras mejoras
- CI/CD: integración con GitHub Actions / Jenkins.
- Paralelización: ejecución en múltiples emuladores o dispositivos reales.
- Reportes: integración con Allure Reports para evidencias visuales.
- Cross-platform: extender pruebas a iOS con Appium + XCUITest.

## 📑 Conclusión
Este proyecto demuestra:
- Un diseño robusto y escalable con POM + DriverManager + ConfigReader.
- Ejecución flexible por plataforma con mvn test -Dplatform=Android|iOS.
- Escenarios positivos y negativos documentados en Gherkin.
- Base preparada para CI/CD, paralelización y cross-platform.