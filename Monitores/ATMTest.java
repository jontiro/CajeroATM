public class ATMTest {
    public static void main(String[] args) {
        ATM sharedAtm = new ATM();

        // Crear varios usuarios
        Thread[] users = new Thread[] {
                new User(sharedAtm, "Usuario-1"),
                new User(sharedAtm, "Usuario-2"),
                new User(sharedAtm, "Usuario-3"),
                new User(sharedAtm, "Usuario-4"),
                new User(sharedAtm, "Usuario-5")
        };

        // Iniciar todos los hilos (usuarios)
        for (Thread u : users) {
            u.start();
            try {
                Thread.sleep(500); // Simular llegada escalonada
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Esperar a que terminen todos
        for (Thread u : users) {
            try {
                u.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Todos los usuarios han terminado sus operaciones.");
    }
}
