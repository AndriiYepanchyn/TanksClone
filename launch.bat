@echo off
REM Переконатися, що ми в кореневій директорії проекту
cd /d %~dp0

REM Встановити клас-путь
set CLASSPATH=bin

REM Запустити Main.class
java main.Main

REM Пауза, щоб бачити вихідні дані
pause