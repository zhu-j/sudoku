# chess sudoku

This is a java program for a variation of sudoku called chess sudoku.

It follows the traditional rules of a sudoku puzzle but with the addition of 3 chess-inspired rules:

Knight rule - a digit must not appear a chess knight’s move away from itself. Knights in chess move forming an L shape: either two squares vertically and one horizontally, or two squares horizontally and one square vertically

Queen rule - every 9 in the grid acts like a chess Queen and must not be in the same row/- column/3x3 box or diagonal of any other 9

King rule - a digit must not be a King’s move away from itself. Which to the classical rules of Sudoku only adds the fact that a digit cannot be a single diagonal move away from itself

It is possible for this program to solve a regular sudoku if the rules specific to chess sudoku ( knight, queen and king rules) are turned off.

This sudoku program is written using a backtracking algorithm.
