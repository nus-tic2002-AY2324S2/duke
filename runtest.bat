@ECHO OFF

REM create bin directory if it doesn't exist
if not exist .\bin mkdir .\bin

REM delete output from previous run
del output.TXT

REM compile the code into the bin folder
javac -encoding ISO-8859-1 -cp .\src -Xlint:none -d .\bin .\src\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath .\bin BigChungus < input.txt > output.txt

REM compare the output to the expected output
FC output.txt expected.txt