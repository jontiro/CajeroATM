class User extends Thread {
    private final ATM atm;
    private final String userName;

    public User(ATM atm, String userName) {
        this.atm = atm;
        this.userName = userName;
    }

    @Override
    public void run() {
        System.out.printf("%s está en la fila esperando.%n", userName);
        atm.usar(userName);
    }
}