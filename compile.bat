@echo off
title Compile Arcade OS Simulator
echo Compiling Java source files...
if not exist bin mkdir bin

REM Prioritize installed JDK if Oracle javapath stub is present
for /d %%D in ("C:\Program Files\Java\jdk*") do (
    if exist "%%D\bin\javac.exe" (
        set "PATH=%%D\bin;%PATH%"
    )
)

javac -d bin -cp "bin" arcadeos\*.java game\snake\*.java
if %ERRORLEVEL% EQU 0 (
    echo Compilation successful! Generating ArcadeOS.jar...
    jar cfe ArcadeOS.jar arcadeos.ArcadeOS -C bin .
    echo Build complete! Run run.bat or java -jar ArcadeOS.jar to start.
) else (
    echo Compilation failed.
)
