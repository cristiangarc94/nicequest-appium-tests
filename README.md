# 📱 Nicequest Appium Tests

Este repositorio contiene pruebas automatizadas para la app móvil de **Nicequest**, utilizando **Cucumber + Appium + Java (JUnit)**.  
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
Instalar dependencias (Maven lo hace automáticamente al correr los tests):

bash
Copiar código
mvn clean install
🔧 Configuración
1️⃣ Emulador Android
Debes tener configurado un AVD en Android Studio.
Ejemplo: Medium_Phone_API_35.

Verifica con:

bash
Copiar código
emulator -list-avds
2️⃣ Appium
Instalar Appium globalmente si no lo tienes:

bash
Copiar código
npm install -g appium
Verificar drivers disponibles:

bash
Copiar código
appium driver list
Debes tener instalados:

uiautomator2 (Android)

xcuitest (iOS, opcional)

🚀 Ejecución de Pruebas
🔹 Opción 1: Modo rápido (recomendado)
Arranca el emulador manualmente:

bash
Copiar código
emulator -avd Medium_Phone_API_35
Arranca Appium:

bash
Copiar código
appium
Ejecuta los tests:

bash
Copiar código
mvn test
🔹 Opción 2: Modo demo (arranque automático)
En DriverManager está comentado el código para arrancar emulador y Appium desde Java:

java
Copiar código
/*
EmulatorManager.startEmulator("Medium_Phone_API_35");
AppiumManager.startAppium();
*/
👉 Útil en CI/CD o entornos donde quieras que todo sea automático.

📁 Estructura del Proyecto
swift
Copiar código
Nicequest/
├── pom.xml
├── src/main/java/com/nicequest/utils/
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
│   └── LoginRunner.java          # Runner de Cucumber (JUnit)
└── src/test/resources/features/
    └── login.feature             # Escenarios en Gherkin


🚀 Ejecución de Pruebas
✅ QA-01 – Login exitoso
Descripción: Validar el inicio de sesión con credenciales válidas.
Archivo: login.feature
Story Points: 5

gherkin
Copiar código
Scenario: Successful login
  Given Open Nicequest login page
  When I enter email "user@example.com"
  And I enter password "ValidPass123"
  And I tap login button
  Then I should see the dashboard

❌ QA-02 – Login inválido (comentado como propuesta futura)
Descripción: Validar mensaje de error con credenciales incorrectas.
Story Points: 3

gherkin
Copiar código
# Scenario: Invalid login
#   Given Open Nicequest login page
#   When I enter email "user@example.com"
#   And I enter password "wrongPass"
#   And I tap login button
#   Then I should see an error message "Invalid email or password"

🛠️ Buenas prácticas aplicadas
- Page Object Model (POM) → cada pantalla encapsula sus elementos y acciones.
- DriverManager → centraliza la configuración del driver (modo rápido y demo).
- Hooks + ScreenshotUtil → captura de pantallas automáticas al fallar los tests.
- Waits explícitos → se espera a que los elementos sean visibles/clicables antes de interactuar.

🔮 Futuras mejoras
- CI/CD: integración con GitHub Actions / Jenkins.
- Paralelización: ejecución en múltiples emuladores o dispositivos reales.
- Reportes: integración con Allure Reports para evidencias visuales.
- Cross-platform: extender pruebas a iOS con Appium + XCUITest.

📑 Conclusión
Este proyecto demuestra:
- Un diseño robusto y escalable con POM + DriverManager.
- Ejecución flexible en modo rápido y modo demo.
- Escenarios positivos y negativos documentados en Gherkin
- Base preparada para CI/CD, paralelización y cross-platform.
