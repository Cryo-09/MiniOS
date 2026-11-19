@echo off
title Arcade OS - 8-Bit Retro Operating System Simulator
echo Starting Arcade OS Simulator...

for /d %%D in ("C:\Program Files\Java\jdk*") do (
    if exist "%%D\bin\java.exe" (
        set "PATH=%%D\bin;%PATH%"
    )
)

if exist ArcadeOS.jar (
    java -jar ArcadeOS.jar
) else (
    if not exist bin\arcadeos\ArcadeOS.class (
        echo Compiling source files...
        call compile.bat
    )
    java -cp "bin" arcadeos.ArcadeOS
)
