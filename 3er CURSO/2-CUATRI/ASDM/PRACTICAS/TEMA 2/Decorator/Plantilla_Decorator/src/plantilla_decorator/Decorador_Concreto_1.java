
package plantilla_decorator;


public class Decorador_Concreto_1 extends Decorador{

    public Decorador_Concreto_1(I_Componente_Abstracto ca) {
        super(ca);
    }
    
    protected void visualizaInformacionDecorador1(){
        System.out.println("Información que añade el decorador 1");
    }
    
    @Override
    public void visualiza(){
        super.visualiza();
        this.visualizaInformacionDecorador1();
    }
}
