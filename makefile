compile: bin
	javac -cp :.src -d bin src/*.java
run:
	java -cp :./bin ExpressionsTest	
bin:
	mkdir bin