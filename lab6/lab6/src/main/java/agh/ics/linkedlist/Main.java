package agh.ics.linkedlist;

import agh.ics.linkedlist.model.Linkedlist;
import agh.ics.linkedlist.model.List;
import agh.ics.linkedlist.model.LockedLinekdlist;

import java.util.concurrent.CountDownLatch;

//public class Main {
//    static void main(String[] args) {
//        List linkedlist = new Linkedlist();
//        List lockedLinkedlist = new LockedLinekdlist();
//    }
//
//    public static void test(List list) {
//
//    }
//
//}

public class Main {
    // --- Parametry testu ---
    /**
     * Symulowany, dodatkowy koszt czasowy operacji (porownania, wstawienia) w milisekundach.
     * Zmienna jest modyfikowana w pętli w metodzie main, aby przeprowadzić testy dla roznych scenariuszy.
     * Jest oznaczona jako `volatile`, aby zapewnić jej widocznosć między watkami, chociaz w tym
     * konkretnym scenariuszu (ustawiana tylko w watku glownym przed startem testu) nie jest to krytyczne.
     */
    public static volatile int OPERATION_COST_MS = 0;

    // Liczba watkow bioracych udzial w tescie
    private static final int NUM_THREADS = 10;
    // Liczba operacji (add/contains) wykonywanych przez kazdy watek
    private static final int OPERATIONS_PER_THREAD = 20;

    /**
     * Funkcja testujaca wydajnosć podanej listy w srodowisku wielowatkowym.
     * Mierzy calkowity czas potrzebny na wykonanie okreslonej liczby operacji
     * przez wiele watkow jednoczesnie.
     *
     * @param list Instancja listy do przetestowania (implementujaca interfejs List).
     * @throws InterruptedException jesli watek glowny zostanie przerwany podczas oczekiwania.
     */
    public static void test(List list) throws InterruptedException {
        System.out.printf("--- Testowanie: %s, Koszt operacji: 10 ms ---\n",
                list.getClass().getSimpleName());

        // Uzywamy CountDownLatch do synchronizacji startu i konca pracy watkow
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(NUM_THREADS);

        // Tworzenie i uruchamianie watkow roboczych
        for (int i = 0; i < NUM_THREADS; i++) {
            final int threadId = i;
            Runnable task = () -> {
                try {
                    startSignal.await(); // Czekaj na sygnal do jednoczesnego startu
                    for (int j = 0; j < OPERATIONS_PER_THREAD; j++) {
                        // Prosty podzial pracy w celu wywolania rywalizacji o zasoby:
                        // watki o parzystych ID dodaja elementy, a o nieparzystych - sprawdzaja ich obecnosć.
                        if (threadId % 2 == 0) {
                            list.add(threadId * OPERATIONS_PER_THREAD + j);
                        } else {
                            list.contains(threadId * OPERATIONS_PER_THREAD + j);
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    doneSignal.countDown(); // Sygnalizuj zakonczenie pracy przez watek
                }
            };
            new Thread(task).start();
        }

        // Pomiar czasu wykonania testu
        long startTime = System.nanoTime();
        startSignal.countDown(); // "START!" - pozwol wszystkim watkom rozpoczać pracę
        doneSignal.await();      // Czekaj, az wszystkie watki zakoncza swoje zadania
        long endTime = System.nanoTime();

        long durationMs = (endTime - startTime) / 1_000_000;
        System.out.printf("Calkowity czas wykonania: %d ms\n\n", durationMs);
    }

    /**
     * Punkt wejscia do programu. Konfiguruje i uruchamia serię testow.
     */
    public static void main(String[] args) throws InterruptedException {
        // Inicjalizacja testowanych implementacji list
        List multiLock = new Linkedlist();
        List singleLock = new LockedLinekdlist();

        // Definicja scenariuszy testowych - rozne wartosci kosztu operacji
        int[] costsInMs = {0};

        System.out.println("Rozpoczynanie pomiarow wydajnosci...");
        System.out.printf("Liczba watkow: %d, Operacji na watek: %d\n\n", NUM_THREADS, OPERATIONS_PER_THREAD);

        // Uruchomienie pętli testujacej dla kazdego zdefiniowanego kosztu
        for (int cost : costsInMs) {
            OPERATION_COST_MS = cost;

            // Testujemy obie implementacje dla danego kosztu, aby moc je porownać
            test(multiLock);
            test(singleLock);
        }
    }
}