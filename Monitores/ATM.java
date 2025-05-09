class ATM {
    private boolean cajeroLibre = true; // condición del monitor

    public synchronized void usar(String userName) {
        while (!cajeroLibre) { // condición de espera
            try {
                wait(); // esperar hasta que el cajero esté libre
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        cajeroLibre = false; // entrar a la sección crítica

        // Operación del cajero fuera del `synchronized` para no bloquear el monitor tanto tiempo
        try {
            System.out.printf("%s ha accedido al cajero.%n", userName);
            Thread.sleep((long) (Math.random() * 3000) + 1000);
            System.out.printf("%s ha terminado en el cajero.%n", userName);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Salir del monitor
        synchronized (this) {
            cajeroLibre = true;
            System.out.printf("%s libera el cajero.%n", userName);
            notify(); // avisar a otro hilo en espera
        }
    }
}
