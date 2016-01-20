/**
 * Dennis Sosa
 * Assignment: #3 (Python File Tracer)
 * @author DennisSosa
 * 
 */

Purpose Description:
 	• Most programming languages are organized as structured blocks of statements, with some blocks nested within others. Functions, which are examples of such blocks, execute statements and other blocks contained within the them. Similarly, flow control strucutres, such as for and while loops, are blocks which can be executed several times subject to some condition. The Python programming language is an example of a language which follows this principle, and is even flexible enough to allow functions to be nested within functions!

	In this assignment, you will create a code tracer program which takes the name of a Python file containing a single function and outputs the Big-Oh order of complexity of that function. To make things easier, several restrictions will be made on the format of the input code, and some techniques for text parsing will be described below. You must implement a BlockStack class to determine the complexity of blocks with nested blocks, and use the rules of Big-Oh complexity to determine the total complexity for the function. You are encouraged to use the Java API to help you complete this assignment, but you may implement the stack however you like.

	It should be noted that you do not need to actually learn Python to complete this assignment - simply following the instructions as detailed in this specification should suffice. However, if you would like some background on language, a good starting point would be the Python website, which should provide you with enough information to get a feel for how the language works.


 	• Please take a look at the "SampleInput-Output.pdf" file for an example of how the program is ran.


