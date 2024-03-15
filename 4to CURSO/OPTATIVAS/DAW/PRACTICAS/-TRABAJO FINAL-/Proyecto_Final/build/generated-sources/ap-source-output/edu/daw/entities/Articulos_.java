package edu.daw.entities;

import edu.daw.entities.Comentarios;
import edu.daw.entities.Usuarios;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-01-24T14:51:55")
@StaticMetamodel(Articulos.class)
public class Articulos_ { 

    public static volatile SingularAttribute<Articulos, Integer> precio;
    public static volatile SingularAttribute<Articulos, Integer> añoAdquisicion;
    public static volatile SingularAttribute<Articulos, String> genero;
    public static volatile SingularAttribute<Articulos, String> detalles;
    public static volatile SingularAttribute<Articulos, Usuarios> usuario;
    public static volatile SingularAttribute<Articulos, Long> id;
    public static volatile SingularAttribute<Articulos, String> nombre;
    public static volatile ListAttribute<Articulos, Comentarios> comentarios;

}