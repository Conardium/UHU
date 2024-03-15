<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="filtrar">
    <img src="/PFdaw/img/lupa.png" id="lupa" />
    <input id="fnombre" type="text" name="fnombre" placeholder="Nombre del juego">

    <select name="fgenero" id="fgenero">
        <option value="">Género</option>
        <option value="Acción">Acción</option>
        <option value="Shooter">Shooter</option>
        <option value="Aventura">Aventura</option>
        <option value="Estrategia">Estrategia</option>
        <option value="Simulación">Simulación</option>
        <option value="Deporte">Deporte</option>
        <option value="Carreras">Carreras</option>
        <option value="RPG">RPG</option>
        <option value="Muscical">Musical</option>
        <option value="Puzzles">Puzzles</option>
        <option value="Sandbox">Sandbox</option>
    </select>

    <select name="fprecio" id="fprecio">
        <option value="">Precio</option>
        <option value="Menor_a_Mayor">Menor a mayor</option>
        <option value="Mayor_a_Menor">Mayor a menor</option>
    </select>

    <input type="button" value="Buscar" id="buscar" onclick="filtrar();">
    <input type="button" value="Borrar" id="borrar" onclick="borrarFiltro();">
</div>