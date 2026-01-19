# Thread scheduling

## Spis treści / Table of Contents
- [Polski](#polski)
- [English](#english)

# Polski

## Opis programu

Program został napisany w języku Java. Oraz przetestowany za pomocą [sprawdzarki](https://github.com/macwozni/Matrices).

## Instrukcja instalacji

Aplikacja wykorzystuje Gradle jako system budowania, co umożliwia łatwe uruchomienie projektu bez dodatkowej konfiguracji.

## Format pliku wejściowego

Program przyjmuje macierz kwadratową $M$ o rozmiarze $n \times n$ oraz wektor wynikowy $y$. Do aplikacji należy przekazywać pliki w formacie:
```
n
M_0,0 M_0,1 M_0,2 ... M_0,(n-1)
...
M_(n-1),0 M_(n-1),1 M_(n-1),2 ... M_(n-1),(n-1)
y_0 y_1 y_2 ... y_(n-1)
```


Przykład:
```
3
2.0 1.0 3.0
4.0 3.0 8.0
6.0 5.0 16.0
6.0 15.0 27.0
```

Plik ten zostanie zinterpretowany jako:

```math
\begin{bmatrix}
2 & 1 & 3 \\
4 & 3 & 8 \\
6 & 5 & 16
\end{bmatrix}
\cdot
\begin{bmatrix}
x_0 \\
x_1 \\
x_2
\end{bmatrix}
=
\begin{bmatrix}
6 \\
15 \\
27
\end{bmatrix}
```

## Przykład działania

Dla danego pliku `input_example1.txt`:

```
3
2.0 1.0 3.0
4.0 3.0 8.0
6.0 5.0 16.0
6.0 15.0 27.0
```

Otrzymamy plik `input_example1_out.txt`:

```
3
1.0 0.0 0.0 
0.0 1.0 0.0 
0.0 0.0 1.0 
1.0 1.0 1.0 
```

reprezentujący:

-   macierz $
    M =
    \begin{bmatrix}
    1 & 0 & 0 \\
    0 & 1 & 0 \\
    0 & 0 & 1
    \end{bmatrix}
    $ po przekształceniu,
-   wektor $ X =
    \begin{bmatrix}
    1 \\
    1 \\
    1
    \end{bmatrix}
    $ będący rozwiązaniem układu równań.


# English

## Program Description

The program was implemented in Java and verified using [checker](https://github.com/macwozni/Matrices).

## Installation Instructions

This application uses Gradle as its build system, allowing for easy setup and execution.

## Input file format

The program accepts a square matrix $M$ of size $n \times n$ and a result vector $y$. 

Input files must be provided in the following format:
```
n
M_0,0 M_0,1 M_0,2 ... M_0,(n-1)
...
M_(n-1),0 M_(n-1),1 M_(n-1),2 ... M_(n-1),(n-1)
y_0 y_1 y_2 ... y_(n-1)
```


Example:
```
3
2.0 1.0 3.0
4.0 3.0 8.0
6.0 5.0 16.0
6.0 15.0 27.0
```

This file will be interpreted as:

```math
\begin{bmatrix}
2 & 1 & 3 \\
4 & 3 & 8 \\
6 & 5 & 16
\end{bmatrix}
\cdot
\begin{bmatrix}
x_0 \\
x_1 \\
x_2
\end{bmatrix}
=
\begin{bmatrix}
6 \\
15 \\
27
\end{bmatrix}
```

## Example

For the given file `input_example1.txt`:

```
3
2.0 1.0 3.0
4.0 3.0 8.0
6.0 5.0 16.0
6.0 15.0 27.0
```

We will receive the file `input_example1_out.txt`:

```
3
1.0 0.0 0.0 
0.0 1.0 0.0 
0.0 0.0 1.0 
1.0 1.0 1.0 
```

representing:

-   the matrix $
    M =
    \begin{bmatrix}
    1 & 0 & 0 \\
    0 & 1 & 0 \\
    0 & 0 & 1
    \end{bmatrix}
    $ after transformation,
-   the vector $ X =
    \begin{bmatrix}
    1 \\
    1 \\
    1
    \end{bmatrix}

    $ which is the solution to the system of equations.
