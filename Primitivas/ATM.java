class ATM {
    private boolean uso = false; // Simula el estado del cajero

    // Método para usar el cajero
    public void use(String userName) {
        synchronized (this) {
            while (uso) {
                try {
                    wait(); // Esperar si el cajero está en uso
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            uso = true; // Marcar el cajero como en uso
        }

        try {
            System.out.printf("%s ha accedido al cajero.%n", userName);
            Thread.sleep((long) (Math.random() * 3000) + 1000); // Simular operación
            System.out.printf("%s ha terminado en el cajero.%n", userName);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            synchronized (this) {
                uso = false; // Marcar el cajero como libre
                notify(); // Notificar a otros hilos que pueden continuar
                System.out.printf("%s libera el cajero.%n", userName);
            }
        }
    }
}