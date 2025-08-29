## Übung 1 – Rabattregeln (Äquivalenzklassen & Grenzwerte)

**Beschreibung:**  
- < 15’000 CHF → 0%  
- ≤ 20’000 CHF → 5%  
- < 25’000 CHF → 7%  
- ≥ 25’000 CHF → 8.5%

### 1) Abstrakte Testfälle (logische Operatoren)
| ID | Eingabe (Preis) | Erwarteter Rabatt |
|---:|------------------|-------------------|
| A1 | Preis < 15’000                  | 0%     |
| A2 | Preis = 15’000                  | 5%     |
| A3 | 15’000 < Preis < 20’000         | 5%     |
| A4 | Preis = 20’000                  | 5%     |
| A5 | 20’000 < Preis < 25’000         | 7%     |
| A6 | Preis = 25’000                  | 8.5%   |
| A7 | Preis > 25’000                  | 8.5%   |
| A8 | **Grenze unten**: Preis = 14’999.95 (falls Dezimal möglich) | 0% |
| A9 | **Grenze**: 19’999.99 (falls Dezimal möglich) | 5% |
| A10| **Grenze**: 24’999.99 (falls Dezimal möglich) | 7% |

> Hinweis: Grenzwerte bewusst dicht an den Schwellen, um Rundungs-/Vergleichsfehler aufzudecken.
### 2) Konkrete Testfälle (konkrete Werte)
| ID | Eingabe (Preis in CHF) | Erwarteter Rabatt |
|---:|------------------------:|-------------------|
| C1 | 14’000                 | 0%     |
| C2 | 15’000                 | 5%     |
| C3 | 18’500                 | 5%     |
| C4 | 20’000                 | 5%     |
| C5 | 22’000                 | 7%     |
| C6 | 25’000                 | 8.5%   |
| C7 | 30’000                 | 8.5%   |
| C8 | 14’999.99              | 0%     |
| C9 | 19’999.99              | 5%     |
| C10| 24’999.99              | 7%     |

---