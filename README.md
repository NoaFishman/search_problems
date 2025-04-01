# search_problems

## Overview

`search_problems` is a Java implementation of various search algorithms to solve a board game problem. This is a final assignment in the "Search Problems" course, where different types of search algorithms were studied and implemented to find solutions to game problems with specific rules. This project is an implementation of some of the algorithms we learned in the course.

## Problem Description

The game is played on a 3x3 board with six marbles of three colors:

- **2 Red**
- **2 Blue**
- **2 Green**

The marbles start in a given initial configuration, and the goal is to move them into a specified final configuration using the fewest possible moves. The board is circular, meaning a marble moving off one edge appears on the opposite side. Marbles can only move horizontally or vertically (not diagonally) into empty spaces and cannot move onto black (blocked) cells.

Each move has a cost:

- Moving a **blue** marble costs **1**
- Moving a **green** marble costs **3**
- Moving a **red** marble costs **10**

## Implemented Search Algorithms

The program supports multiple search algorithms:

- **BFS (Breadth-First Search)**
- **DFID (Depth-First Iterative Deepening)**
- **A**\* (A-Star Search)
- **IDA**\* (Iterative Deepening A-Star)
- **DFBnB (Depth-First Branch and Bound)**

## Input Format

The program reads input from a file named `input.txt`:

1. The first line specifies the search algorithm to use (`BFS`, `DFID`, `A*`, `IDA*`, or `DFBnB`).
2. The second line indicates whether to print the runtime (`with time` or `no time`).
3. The third line specifies whether to print the `open list` at each step (`with open` or `no open`).
4. The next lines contain the initial board state, formatted as follows:
   - `R` for red marbles
   - `B` for blue marbles
   - `G` for green marbles
   - `_` for empty spaces
   - `X` for blocked cells
5. A line stating `Goal state:` followed by the final board configuration.

## Output Format

The program writes the solution to `output.txt`:

1. The first line contains the sequence of moves in the format `(row,column):color:(new_row,new_column)--...`.
2. The second line records the number of generated nodes as `Num: <count>`.
3. The third line records the cost of the solution as `Cost: <value>`.
4. The fourth line (if requested) records the runtime in seconds.

If no solution is found, the output will be:

```
no path
Num: <count>
Cost: inf
<runtime if requested>
```
