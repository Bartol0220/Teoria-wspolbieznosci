# Thread scheduling

## Spis treści / Table of Contents
- [Polski](#polski)
- [English](#english)

# Polski

## Opis programu

Program został napisany w języku Python. Do programu zostały dostarczone testy. Użyte biblioteki to:
- [NumPy](https://numpy.org/),
- [Matplotlib](https://matplotlib.org/),
- [NetworkX](https://networkx.github.io/),
- [pytest](https://pytest.org/).

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

## Przykład działania

Dla danego pliku `example1.txt`:

```
(a) x := x + y
(b) y := y + 2z
(c) x := 3x + z
(d) z := y - z
A = {a, b, c, d}
w = baadcb
```

Otrzymamy plik `res_example1.txt`:

```
D = {(a, a),(a, c),(a, b),(b, a),(b, d),(b, b),(c, a),(c, d),(c, c),(d, d),(d, c),(d, b)}
I = {(a, d),(d, a),(b, c),(c, b)}
FNF([w]) = (b)(ad)(a)(bc)
digraph g{
1 -> 2
1 -> 4
2 -> 3
3 -> 5
3 -> 6
4 -> 5
4 -> 6
1[label=b]
2[label=a]
3[label=a]
4[label=d]
5[label=c]
6[label=b]
}
```

Otrzymany graf:

![Graf example1](results/graph_example1.png)


# English

## Program Description

The program was written in Python. Tests were provided for the program. The following libraries were used:
- [NumPy](https://numpy.org/)
- [Matplotlib](https://matplotlib.org/)
- [NetworkX](https://networkx.github.io/)
- [pytest](https://pytest.org/)

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

## Example

For the given file `example1.txt`:

```
(a) x := x + y
(b) y := y + 2z
(c) x := 3x + z
(d) z := y - z
A = {a, b, c, d}
w = baadcb
```

We will receive the file `res_example1.txt`:

```
D = {(a, a),(a, c),(a, b),(b, a),(b, d),(b, b),(c, a),(c, d),(c, c),(d, d),(d, c),(d, b)}
I = {(a, d),(d, a),(b, c),(c, b)}
FNF([w]) = (b)(ad)(a)(bc)
digraph g{
1 -> 2
1 -> 4
2 -> 3
3 -> 5
3 -> 6
4 -> 5
4 -> 6
1[label=b]
2[label=a]
3[label=a]
4[label=d]
5[label=c]
6[label=b]
}
```

The resulting graph:

![Graph example1](results/graph_example1.png)