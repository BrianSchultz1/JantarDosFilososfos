package packageunivers.jantardosfilosofos;

import java.util.ArrayList;

public class Jantar {

    public static void main(String[] args) throws InterruptedException {

        int totalFilosofos = 5;
        int tempoComendo = 1000; // Em milisegundos

        ArrayList<Garfo> garfos = new ArrayList<Garfo>();
        ArrayList<Filosofo> filosofos = new ArrayList<Filosofo>();

        for (int i = 0; i < totalFilosofos; i++) { // Cria X garfos.
            garfos.add(new Garfo(i));
        }

        for (int i = 0; i < totalFilosofos; i++) {

            int garfoProximo;

            if (i == totalFilosofos - 1) {
                garfoProximo = 0;
            } else {
                garfoProximo = i + 1;
            }

            // 0 = 1
            // 1 = 2
            // 2 = 3
            // 3 = 4
            // 4 = 0

            filosofos.add(new Filosofo(i, tempoComendo, garfos.get(i), garfos.get(garfoProximo)));

        }

        for (Filosofo f : filosofos) { // Chama o metodo run de todos os filosofos
            new Thread(f).start();
        }

    }

}
