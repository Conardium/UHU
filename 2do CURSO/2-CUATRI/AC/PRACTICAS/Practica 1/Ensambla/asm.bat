echo off
:inicio
cls
edit %1.asm
tasm/zi/l %1
if errorlevel 1 goto fin1
tlink/v %1 
if errorlevel 1 goto fin1
choice Entrar en el Turbo Debugger
if errorlevel 2 goto fin1
td %1
:fin1
choice Salir de la aplicaci¢n
if errorlevel 2 goto inicio
:fin
