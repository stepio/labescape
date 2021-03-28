# Escape Maze

[![Build Status](https://api.travis-ci.org/repositories/stepio/labescape.svg?branch=master)](https://travis-ci.org/stepio/labescape)

Using the framework(s) of your choice (if any) implement a relatively simple API over HTTP which accepts a "maze" structure as input, along with starting point, and returns the solution to said maze.

## Example

Input

	OOOOOOOOOO
	O    O   O
	O OO O O O
	O  O O O O
	O OO   O  
	O OOOOOOOO
	O        O
	OOOOOOOOOO
	
Starting point: (x=3, y=1)
 	
 	OOOOOOOOOO
	O    O   O
	O OO O O O
	O• O O O O
	O OO   O  
	O OOOOOOOO
	O        O
	OOOOOOOOOO
	
Desired output:
	
	OOOOOOOOOO
	O••••O•••O
	O•OO•O•O•O
	O• O•O•O•O
	O OO•••O••
	O OOOOOOOO
	O        O
	OOOOOOOOOO