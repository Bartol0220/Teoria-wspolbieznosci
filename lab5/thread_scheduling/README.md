# Thread scheduling

## Spis treści / Table of Contents
- [Polski](#polski)
- [English](#english)

# Polski
## Instrukcja instalacji
W głównym katalogu projektu wpisz:
```bash
pip install .
```
Aby uruchomić program dla pliku o nazwie `file_name` znajdującego się w katalogu `data` w projekcie wpisz:
```bash
startapp file_name
```
Domyślnie użyty zostanie plik `example1.txt` po wpisaniu komendy `startapp`.

## Format pliku wejściowego
Program przyjmuje pliki w formacie:
```
(<litera>) <akcja>
...
(<litera>) <akcja>
A = {<alfabet>}
w = <słowo>
```
Gdzie:
- zmienna - pojedyncza litera
- akcja - wyrażenie w postaci `zmienna := <wyrażenie składające się ze zmiennych, cyfr oraz podstawowych operacji matematycznych zapisanych w poprawny sposób>`


Przykład:
```
(a) x := x + 1
(b) y := y + 2z
(c) x := 3x + z
(d) w := w + v
(e) z := y - z
(f) v := x + v
A = {a, b, c, d, e, f}
w = acdcfbbe
```

# English
## Installation Instructions
In the project root directory, enter:
```bash
pip install .
```
To run the program for a file named `file_name` located in the `data` directory in the project, type:
```bash
startapp file_name
```
By default, the `example1.txt` file will be used after entering the `startapp` command.

## Input file format
The program accepts files in the following format:
```
(<letter>) <action>
...
(<letter>) <action>
A = {<alphabet>}
w = <word>
```
Where:
- variable - a single letter
- action - an expression in the form `variable := <an expression consisting of variables, numbers, and basic mathematical operations written correctly>`

Example:
```
(a) x := x + 1
(b) y := y + 2z
(c) x := 3x + z
(d) w := w + v
(e) z := y - z
(f) v := x + v
A = {a, b, c, d, e, f}
w = acdcfbbe
```