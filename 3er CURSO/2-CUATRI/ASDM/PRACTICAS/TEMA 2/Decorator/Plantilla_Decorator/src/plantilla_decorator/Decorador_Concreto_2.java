
package plantilla_decorator;


public class Decorador_Concreto_2 extends Decorador{
    
    public Decorador_Concreto_2(I_Componente_Abstracto ca) {
        super(ca);
    }
    
    protected void visualizaInformacionDecorador2(){
        System.out.println("Información que añade el decorador 2");
    }
    
    @Override
    public void visualiza(){
        super.visualiza();
        this.visualizaInformacionDecorador2();
    }
}
