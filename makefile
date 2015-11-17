JCC = javac
sources = $(wildcard *.java)
classes = $(sources:.java=.class)
clean :
	rm -f *.class

%.class : %.java
	$(JAVAC) $<