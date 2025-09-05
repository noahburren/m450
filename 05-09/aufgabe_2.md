# JUnit 5 – Kurz & Knackig

> Eine kompakte Übersicht über die gängigsten JUnit-Features mit kurzen Anwendungsfällen/Beispielen. Fokus: JUnit Jupiter (Tests) und JUnit Platform (Ausführung).

---

## 1) Grundlegend

**Wofür?** Unit-Tests schreiben/ausführen, Verhalten prüfen, Regressionen verhindern.

**Kernelemente:**

* `@Test` – markiert eine Testmethode
* Assertions – Ergebnisse prüfen (`Assertions.*`)
* Test-Lifecycle – Setup/Teardown (`@BeforeEach`, `@AfterEach`, …)
* Parametrisierte Tests – gleiche Logik über mehrere Daten laufen lassen

**Mini-Beispiel:**

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DemoTest {
  @Test
  void hello() {
    assertTrue("JUnit".startsWith("JU"));
  }
}
```

---

## 2) Test-Lifecycle

**Wofür?** Gemeinsames Setup/Teardown vor/nach Tests.

* `@BeforeAll` / `@AfterAll` – einmal pro Klasse (statisch oder per Test-Instance-Lifecycle)
* `@BeforeEach` / `@AfterEach` – vor/nach **jedem** Test

**Beispiel:**

```java
import org.junit.jupiter.api.*;

class LifecycleTest {
  @BeforeAll static void initAll() { /* DB starten */ }
  @AfterAll  static void tearDownAll() { /* DB stoppen */ }

  @BeforeEach void init() { /* Testdaten anlegen */ }
  @AfterEach  void tearDown() { /* Testdaten löschen */ }

  @Test void case1() { /* ... */ }
}
```

---

## 3) Assertions (Prüfungen)

**Wofür?** Erwartete Ergebnisse vs. tatsächliche Ergebnisse.

* `assertEquals(expected, actual[, delta])`
* `assertTrue/False(predicate)`
* `assertNull/NotNull(obj)`
* `assertThrows(Exception.class, executable)` – Exceptions prüfen
* `assertAll(name, () -> {...}, () -> {...})` – mehrere Asserts bündeln
* `assertIterableEquals`, `assertArrayEquals` – Collections/Arrays
* `assertTimeout(Duration, executable)` – Laufzeitgrenzen

**Beispiel (Exception & assertAll):**

```java
import static org.junit.jupiter.api.Assertions.*;

@Test
void assertions() {
  assertAll("kunde",
    () -> assertEquals("Max", kunde.getVorname()),
    () -> assertThrows(IllegalStateException.class, kunde::abschliessen)
  );
}
```

---

## 4) Assumptions (Voraussetzungen)

**Wofür?** Tests nur ausführen, wenn Bedingungen erfüllt sind (z. B. OS, ENV).

* `assumeTrue(cond[, message])`
* `assumeFalse(cond)`
* `assumingThat(cond, executable)` – nur Teil testen

**Beispiel:**

```java
import static org.junit.jupiter.api.Assumptions.*;

@Test
void onlyOnCi() {
  assumeTrue("true".equals(System.getenv("CI")), "läuft nur auf CI");
  // ... Testcode
}
```

---

## 5) Parametrisierte Tests

**Wofür?** Gleiche Testlogik mit verschiedenen Inputs/Erwartungen.

* `@ParameterizedTest`

  * `@ValueSource` (z. B. ints, strings)
  * `@CsvSource` (mehrere Spalten)
  * `@MethodSource` (Stream/Collection-Lieferant)
  * `@EnumSource`, `@NullSource`, `@EmptySource`, `@NullAndEmptySource`

**Beispiel (`@CsvSource`):**

```java
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

@ParameterizedTest(name = "{0} + {1} = {2}")
@CsvSource({
  "1, 2, 3",
  "-2, 5, 3",
  "1.5, 2.5, 4.0"
})
void add(double a, double b, double expected) {
  assertEquals(expected, calculator.add(a, b), 1e-9);
}
```

---

## 6) Tests beschriften & strukturieren

* `@DisplayName("Lesbarer Testname")` – schöner Name im Report
* `@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)` – aus Methodennamen mit `_` lesen
* `@Tag("slow")` – Tests taggen/filtern (z. B. nur `-Dgroups=slow`)
* `@Disabled("Grund")` – temporär deaktivieren
* `@Nested` – Tests logisch gruppieren (Kontext/Szenario)

**Beispiel (`@Nested`, `@Tag`):**

```java
import org.junit.jupiter.api.*;

@Tag("repo")
class UserRepositoryTest {
  @Nested
  @DisplayName("findById")
  class FindById {
    @Test void returns_user_when_exists() { /* ... */ }
    @Test void returns_empty_when_missing() { /* ... */ }
  }
}
```

---

## 7) Wiederholte & geordnete Tests

* `@RepeatedTest(n)` – Test n‑mal laufen lassen (Flaky erkennen)
* `@TestMethodOrder(OrderAnnotation.class)` + `@Order(n)` – Ausführungsreihenfolge festlegen (sparsam verwenden)

**Beispiel:**

```java
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderedTests {
  @Test @Order(1) void init() { /* ... */ }
  @Test @Order(2) void verify() { /* ... */ }
}
```

---

## 8) Zeit & Ressourcen

* `@Timeout(2)` – Annotation-basierte Zeitbeschränkung (Sekunden)
* `assertTimeout(Duration.ofMillis(500), …)` – API-basierte Zeitbeschränkung
* `@TempDir` – temporäre Verzeichnisse/Dateien für IO-Tests

**Beispiel (`@TempDir`):**

```java
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.*;

@Test
void writes_file(@TempDir Path tmp) throws Exception {
  Path file = tmp.resolve("out.txt");
  Files.writeString(file, "hello");
  assertTrue(Files.exists(file));
}
```

---

## 9) Dependency Injection

JUnit injiziert nützliche Objekte:

* `TestInfo` – Metadaten (DisplayName, Tags)
* `TestReporter` – Key/Value in Report loggen

**Beispiel:**

```java
import org.junit.jupiter.api.*;

@Test
void uses_injected(TestInfo info, TestReporter reporter) {
  reporter.publishEntry("displayName", info.getDisplayName());
}
```

---

## 10) Test-Suites (JUnit Platform)

**Wofür?** Mehrere Testklassen/-Pakete als Suite starten.

```java
import org.junit.platform.suite.api.*;

@Suite
@SelectPackages("com.example")
@IncludeTags("integration")
class IntegrationSuite { }
```

---

## 11) Ausführen & Build-Tools

**Maven**

```xml
<dependency>
  <groupId>org.junit.jupiter</groupId>
  <artifactId>junit-jupiter</artifactId>
  <version>5.10.2</version>
  <scope>test</scope>
</dependency>
```

**Surefire** ab 2.22.0 erkennt JUnit 5 automatisch.

**Gradle (KTS)**

```kotlin
testing {
  suites {
    val test by getting(JvmTestSuite::class) {
      useJUnitJupiter("5.10.2")
    }
  }
}
```

---

## 12) Best Practices (Kurz)

* Klare **Arrange–Act–Assert**-Struktur.
* **Ein** fachlicher Aspekt pro Test (klein & lesbar).
* Testdaten sprechend benennen; **Given/When/Then** im Namen.
* Für `double`/`float` immer **Toleranz** (`delta`) verwenden.
* Parametrisierte Tests für Datenvarianten.
* Externe Abhängigkeiten mocken (z. B. Mockito) – aber: Logik im Unit-Test behalten.
* Schnelle Unit-Tests vs. separate Integrations-/Systemtests trennen (Tags).

---

## 13) Referenzen (empfohlen)

* **JUnit 5 User Guide (offiziell):** [https://junit.org/junit5/docs/current/user-guide/](https://junit.org/junit5/docs/current/user-guide/)
* **Baeldung – JUnit 5:** [https://www.baeldung.com/junit-5](https://www.baeldung.com/junit-5)

---

*Stand: JUnit 5.10.x – Inhalte sind in der Regel stabil. Für Details und Updates bitte in den User Guide schauen.*
