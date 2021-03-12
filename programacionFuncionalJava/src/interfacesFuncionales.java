import java.util.function.*;

public class interfacesFuncionales {
    public static void main(String args[]){
        // interfaz funcional operador binario - con expresion lambda asociada
        BinaryOperator<Integer> f1 = (Integer x, Integer y) -> {return x*y;};

        int resultado1 = f1.apply(3, 4);
        System.out.println("resultado1: " + resultado1);

        // f1 es una referencia y puede apuntar a otra función

        f1 = (Integer x, Integer y) -> {return  x+y;};

        int resultado2 = f1.apply(3, 4);
        System.out.println("resultado2: " + resultado2);

        // Existen versiones específicas de las interfaces para los tipos concretos

        IntBinaryOperator f2 = (int x, int y) -> { // No hace falta usar objetos
            return x-y;
        };

        // Si no hay ambigüedad, se simplifica la expresión

        IntBinaryOperator f3 = (x, y) -> (x+y);

        // En realidad, por debajo de f3 hay...
        IntBinaryOperator f3Completa = new IntBinaryOperator(){
            @Override
            public int applyAsInt(int left, int rigth){
                return 0;
            }
        };

        // También con otros tipos
        DoubleBinaryOperator f4 = (x,y) -> (x*y);

        // Expresión tipo consumidor
        Consumer<Integer> f5 = valor -> System.out.println("Valor mostrado: " + valor);
        f5.accept(17);

        // Expresion lambda sin argumentos
        Runnable expo = () -> System.out.println("Hola mundo");
        expo.run();

        // Expresiones lambda para predicados
        IntPredicate f7 = x -> x>0;

        boolean res3 = f7.test(17);
        System.out.println("res3: " + res3);
    }
}
