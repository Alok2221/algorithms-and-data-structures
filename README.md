# TaskManagerApp - System zarządzania zadaniami

## 📌 Opis projektu

Aplikacja konsolowa do zarządzania listą zadań z wykorzystaniem kolejki (implementacja `TQueue<E>`). Zapewnia:

- Dodawanie zadań z opisami
- Usuwanie zadań (FIFO)
- Oznaczanie zadań jako zakończone
- Zapis do pliku tekstowego
- Automatyczne tworzenie potrzebnych folderów

## 🚀 Jak uruchomić

#### 1. Wymagania:

    - JDK 17+
    - Maven (opcjonalnie)

#### 2.Kompilacja i uruchomienie:

Wybierz opcję:

1. ➕ Dodaj zadanie
2. ➖ Usuń zadanie
3. 💾 Zakończ i zapisz
4. ✅ Zakończ zadanie (po nr)

## Funkcjonalności

| Opcja | Akcja                           | Przykładowe użycie                                                       |
|-------|---------------------------------|--------------------------------------------------------------------------|
| 1     | Dodaje nowe zadanie             | **Podaj zadanie:** Spotkanie z klientem  <br> **Opis:** O 14:00 w biurze |
| 2     | Usuwa zadanie po numerze        | **Usunięto:** Kupić mleko                                                |
| 3     | Zapisuje do pliku i kończy      | **Zapisano do:** src/main/resources/tasks.txt                            |
| 4     | Oznacza zadanie jako zakończone | **Podaj nr zadania:** 2  <br> **Zakończono:** Napisać dokumentację       |


## Lista zadań  
### 💾 Zadania zostają zapisane do pliku [tasks](src/main/resources/tasks.txt)
#### Ścieżka: ****/src/main/resources/tasks.txt***