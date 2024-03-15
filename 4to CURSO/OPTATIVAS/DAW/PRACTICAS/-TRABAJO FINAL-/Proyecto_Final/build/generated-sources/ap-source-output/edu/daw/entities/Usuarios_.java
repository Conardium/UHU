package edu.daw.entities;

import edu.daw.entities.Articulos;
import edu.daw.entities.Comentarios;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-01-24T14:51:55")
@StaticMetamodel(Usuarios.class)
public class Usuarios_ { 

    public static volatile SingularAttribute<Usuarios, String> password;
    public static volatile ListAttribute<Usuarios, Articulos> articulos;
    public static volatile SingularAttribute<Usuarios, String> codpostal;
    public static volatile SingularAttribute<Usuarios, Long> id;
    public static volatile SingularAttribute<Usuarios, String> telefono;
    public static volatile SingularAttribute<Usuarios, String> nombre;
    public static volatile ListAttribute<Usuarios, Comentarios> comentarios;
    public static volatile SingularAttribute<Usuarios, String> email;

}