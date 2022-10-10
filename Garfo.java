package packageunivers.jantardosfilosofos;

public class Garfo {
    private int id;

    private Thread filosofo;

    public Garfo(int id) {

        this.id = id;

        this.filosofo = null; // Garfo na mesa

    }

    // synchronized: Garante que nao vai haver conflito de Thread

    public synchronized void acquire() throws InterruptedException {

        if (this.filosofo != null) {
            wait(); // O filosofo espera o garfo até que ele seja solto.
        }

        this.filosofo = Thread.currentThread(); // Botou garfo na mão.

    }

    public synchronized void release() {

        if (this.filosofo == Thread.currentThread()) { // Verifica se o filosofo está segurando esse garfo
            this.filosofo = null;
        }

        notify(); // Notifica que a thread pausada pode continuar.

    }
}
