import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Clase que representa el cajero automático
public class ATM {
    private final Lock lock = new ReentrantLock();

    // Método para usar el cajero
    public void use(String userName) {
        lock.lock();
        try {
            System.out.printf("%s ha accedido al cajero.%n", userName);
            // Simular tiempo de operación en el cajero
            Thread.sleep((long) (Math.random() * 3000) + 1000);
            System.out.printf("%s ha terminado en el cajero.%n", userName);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.printf("%s libera el cajero.%n", userName);
            lock.unlock();
        }
    }
}