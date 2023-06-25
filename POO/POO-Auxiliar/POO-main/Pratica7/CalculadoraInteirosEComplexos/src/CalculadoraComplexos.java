public class CalculadoraComplexos extends Complexo implements IOperacoesBasicas<Complexo> {
    
    public Complexo somar(Complexo operador1, Complexo operador2){
        
        /*Complexo resultado = new Complexo();
        
        resultado.setReal(operador1.getReal() + operador2.getReal());
        resultado.setImaginaria(operador1.getImaginaria() + operador2.getImaginaria());

        return resultado; */

        return new Complexo(operador1.getReal() + operador2.getReal(),operador1.getImaginaria() + operador2.getImaginaria() );
    }

    public Complexo subtrair(Complexo operador1, Complexo operador2){
        
        /*Complexo resultado = new Complexo();
        
        resultado.setReal(operador1.getReal() - operador2.getReal());
        resultado.setImaginaria(operador1.getImaginaria() - operador2.getImaginaria());

        return resultado;*/

        return new Complexo(operador1.getReal() - operador2.getReal(),operador1.getImaginaria() - operador2.getImaginaria() );
    }

}