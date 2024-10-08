# INDEX

- Identifiers
- Reserved Words
- Data Types
- Literals
- Arrays
- Types of variables
- var-arg methods
- main Method
- Command Line Arguments
- Java Coding Standards

# IDENTIFIERS

- A name in Java program
- It can be method name, variable name, class name or label name
```java
class Test {
	public static void main(String[] args) {
		int x = 10;
	}
}
// 5 identifiers - Test, main, String, args, x
```

## RULES FOR DEFINING JAVA IDENTIFIERS

- The only allowed characters are uppercase and lowercase letters, digits, $, _.
If we are using any other character we will compile time error
- Identifiers can't start with digit
- Java identifiers are case sensitive
- There is no length limit for Java identifiers, but it is not recommended to
take too lengthy identifiers
- We can't use reserved words as identifiers (`if`, `for`, `while`, etc.)
- All predefined Java class names and interface names can be used as identifiers
. But this is not recommended due to readability concerns
```java
int String = 999;   // valid
double Runnable = 888;  // valid
```

# RESERVED WORDS

- Words that are reserved to represent some meaning or functionality
```
Reserved Words (53)
|-- Keywords (50)
|-- |-- Used Keywords (48)
|-- |-- |-- Datatypes
|-- |-- |-- |-- byte
|-- |-- |-- |-- short 
|-- |-- |-- |-- int
|-- |-- |-- |-- long
|-- |-- |-- |-- float
|-- |-- |-- |-- double
|-- |-- |-- |-- boolean
|-- |-- |-- |-- char
|-- |-- |-- Flow control
|-- |-- |-- |-- if
|-- |-- |-- |-- else
|-- |-- |-- |-- switch
|-- |-- |-- |-- case
|-- |-- |-- |-- default
|-- |-- |-- |-- while
|-- |-- |-- |-- do
|-- |-- |-- |-- for
|-- |-- |-- |-- break
|-- |-- |-- |-- continue
|-- |-- |-- |-- return
|-- |-- |-- Modifiers
|-- |-- |-- |-- public
|-- |-- |-- |-- private
|-- |-- |-- |-- protected
|-- |-- |-- |-- static
|-- |-- |-- |-- final
|-- |-- |-- |-- abstract
|-- |-- |-- |-- synchronized
|-- |-- |-- |-- native
|-- |-- |-- |-- strictfp
|-- |-- |-- |-- transient
|-- |-- |-- |-- volatile
|-- |-- |-- Exception Handling
|-- |-- |-- |-- try
|-- |-- |-- |-- catch
|-- |-- |-- |-- finally
|-- |-- |-- |-- throw
|-- |-- |-- |-- throws
|-- |-- |-- |-- assert
|-- |-- |-- Class Related
|-- |-- |-- |-- class
|-- |-- |-- |-- interface
|-- |-- |-- |-- extends
|-- |-- |-- |-- implements
|-- |-- |-- |-- package
|-- |-- |-- |-- import
|-- |-- |-- Object Related
|-- |-- |-- |-- new
|-- |-- |-- |-- instanceof
|-- |-- |-- |-- super
|-- |-- |-- |-- this
|-- |-- |-- Others
|-- |-- |-- |-- void (used for methods that don't return any value since return
type is mandatory for methods)
|-- |-- |-- | -- enum (used for defining group of named constants)
|-- |-- Unused Keywords (2 - If used then we will get compile time error)
|-- |-- |-- goto (created problems in C/C++ and was banned here)
|-- |-- |-- const (use final to define constants instead)
|-- Reserved Literals (3)
|-- |-- |-- true (value for boolean data types)
|-- |-- |-- false (value for boolean data types)
|-- |-- |-- null (default value for object reference only)
```
- All reserved words in Java contains only lowercase alphabet symbols
- `new` keyword is present in Java but there is no `delete` keyword because
destruction of useless objects is the responsibility of garbage collector

# DATA TYPES

- In Java every variable and every expression has some type
- Each and every data type is clearly defined
- Every assignment should be checked by compiler for type compatibility
- Because of above reasons we can conclude Java language is a strongly typed
programming language

- Java is not considered as pure object oriented programming language because
several OOP features are not satisfied by Java (like operator overloading,
multiple inheritance, etc.)
- Moreover we are depending on primitive data types which are non-objects

```
Primitive Data types (8)
|-- Numeric (signed - can represent +ve (MSB=0) and -ve numbers (MSB=1))
|-- |-- Integral (Range = -pow(2, n-1) to pow(2, n-1)-1; n = size of data type)
Possible loss of precision - When value is greater than range of data type
Incompatible types - When value is of a different type than the data type
|-- |-- |-- byte (8 bits) - Best used when handling data in terms of streams
|-- |-- |-- short (16 bits) - Used only with 16-bit processors
|-- |-- |-- int (32 bits) - Out of range value gives Integer number too large CE
Wrapper Class - Integer
|-- |-- |-- long (64 bits) - For numbers greater than int
|-- |-- Floating-point
|-- |-- |-- float (32 bits) - Upto 6 places of precision
|-- |-- |-- double (64 bits) - Upto 15 places of precision
|-- Non-numeric 
|-- |-- char (16 bits) - Unicode representation; Wrapper class Character
|-- |-- boolean - Virtual machine dependent size; False values of different data
types are not interpreted as false boolean value (will get CE incompatible type)
```

# MAIN METHOD

- Whether class contains `main()` method or not, and whether `main()` method is
declared according to requirement are not -> these things won't be checked by
compiler. At runtime JVM is responsible to check these things. If JVM is unable
to find `main()` method then we will get runtime exception
- At runtime JVM searches for `main()` method with the following prototype
	- `public` - So that JVM can call from anywhere
	- `static` - Without an object JVM needs to call this method
	- `void` - `main()` method won't return anything to JVM
	- `main` - This is the name which is configured in JVM
	- `String[] args` - command line arguments
```java
public static void main(String[] args) {}
```
- The above syntax is very strict and if we perform any change then we will get
runtime exception. However the following changes are acceptables:
	- Instead of `public static` we can take `static public` - the order of
	modifiers is not important
	- We can declare `String` array in any acceptable form - `String[] durga`
	- We can replace `String` array with var-arg parameter - `String... args`
	- We can declare `main()` method with the modifiers - `final`, `synchronized`,
	`strictfp`

- Overloading of `main()` method is possible but JVM will always call `String[]`
argument `main()` method only. The other overloaded methods we have to call
explicitly like normal method calls
```java
class Test {
	public static void main(String[] args) {
		System.out.println("String[]");
	}
	public static void main(int[] args) {
		System.out.println("int[]");
	}
}
// OUTPUT - String[]
```
- Inheritance concept applicable for `main()` method. Hence while executing
child class if it does not contain `main()` method, then parent class `main()`
method will be executed
```java
class P {
	public static void main(String[] args) {
		System.out.println("Parent main");
	}
}
class C extends P {}
/*
java P.java -> P.class and C.class
java P -> Parent main
java C -> Parent main
*/
```
- It seems overriding concept is applicable for `main()` method but it is not
overriding - it is method hiding
```java
class P {
	public static void main(String[] args) {
		System.out.println("Parent main");
	}
}
class C extends P {
	public static void main(String[] args) {
		System.out.println("Child main");
	}
}
/*
java P.java -> P.class and C.class
java P -> Parent main
java C -> Child main (this is method hiding not overriding)
*/
```

- Execution flow:
Check for main method
	- If it is not available - Error - Main method not found
	- If it is available
		- Identification of static member
		- Execution of static variable assignments and static blocks
		- Execution of main
```java
class Test {
	static {
		System.out.println("Static block");
	}
	public static void main(String[] args) {
		System.out.println("Main method");
	}
}
/*
javac Test.java
java Test

// OUTPUT
Static block
main method
```