import java.util.concurrent.Semaphore;

// Clase que representa el cajero automático
class ATM {
    private final Semaphore semaphore = new Semaphore(1); // Semáforo binario

    // Metodo para usar el cajero
    public void use(String userName) {
        try {
            semaphore.acquire(); // Solicitar acceso al cajero
            System.out.printf("%s ha accedido al cajero.%n", userName);
            // Simular tiempo de operación en el cajero
            Thread.sleep((long) (Math.random() * 3000) + 1000);
            System.out.printf("%s ha terminado en el cajero.%n", userName);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.printf("%s libera el cajero.%n", userName);
            semaphore.release(); // Liberar el cajero
        }
    }
}