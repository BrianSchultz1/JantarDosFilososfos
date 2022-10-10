package packageunivers.jantardosfilosofos;

import java.util.Random;

public class Filosofo implements Runnable {

    private static final int TEMPO_MAXIMO_PENSANDO = 1000;

    private int id;
    private int tempoComendo;
    private Garfo meuGarfo, garfoVizinho;

    private Random rand = new Random();

    public Filosofo(int id, int tempoComendo, Garfo esquerda, Garfo direita) {
        this.id = id;
        this.tempoComendo = tempoComendo;
        this.meuGarfo = esquerda;
        this.garfoVizinho = direita;
    }

    private void pensar() {
        System.out.println("Filósofo " + id + " está PENSANDO.");

        try {
            int tempoPensando = rand.nextInt(TEMPO_MAXIMO_PENSANDO);

            Thread.sleep(tempoPensando);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void pegarGarfos() {
        try {
            if (rand.nextInt(2) == 1) {
                meuGarfo.acquire();
                System.out.println("Filósofo " + id + " PEGOU o 1° garfo (ESQUERDO).");
                garfoVizinho.acquire();
                System.out.println("Filósofo " + id + " PEGOU o 2° garfo (DIREITO).");
            } else {
                garfoVizinho.acquire();
                System.out.println("Filósofo " + id + " PEGOU o 1° garfo (DIREITO).");
                meuGarfo.acquire();
                System.out.println("Filósofo " + id + " PEGOU o 2° garfo (ESQUERDO).");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void soltarGarfos() {
        if (rand.nextInt(2) == 1) {
            meuGarfo.release();
            System.out.println("Filósofo " + id + " LARGOU o 1° garfo (ESQUERDO).");
            garfoVizinho.release();
            System.out.println("Filósofo " + id + " LARGOU o 2° garfo (DIREITO).");
        } else {
            garfoVizinho.release();
            System.out.println("Filósofo " + id + " LARGOU o 1° garfo (DIREITO).");
            meuGarfo.release();
            System.out.println("Filósofo " + id + " LARGOU o 2° garfo (ESQUERDO).");
        }
    }

    private void comer() throws InterruptedException {
        System.out.println("Filósofo " + id + " está COMENDO.");

        Thread.sleep(tempoComendo);
    }

    @Override
    public void run() {

        try {
            while (true) {
                pensar();
                pegarGarfos();
                comer();
                soltarGarfos();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}