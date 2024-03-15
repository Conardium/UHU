
package plantilla_decorator;


public abstract class Decorador implements I_Componente_Abstracto{

    protected I_Componente_Abstracto componente;

    public Decorador(I_Componente_Abstracto ca) {
        componente = ca;
    }
    
    @Override
    public void visualiza() {
        componente.visualiza();
    }
    
}
