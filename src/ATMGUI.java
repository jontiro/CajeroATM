import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Semaphore;

class ATMGUI {
    private final JFrame frame = new JFrame("Cajero Automático");
    private final JLabel[] userLabels = new JLabel[5];

    // Semáforo para controlar el acceso al cajero automático
    private final Semaphore semaphore = new Semaphore(1);

    public ATMGUI() {
        frame.setLayout(new GridLayout(6, 1, 10, 10));
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("Simulación de Cajero Automático", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(title);

        for (int i = 0; i < userLabels.length; i++) {
            userLabels[i] = new JLabel("Usuario-" + (i + 1) + " esperando...", JLabel.CENTER);
            userLabels[i].setOpaque(true);
            userLabels[i].setBackground(Color.LIGHT_GRAY);
            frame.add(userLabels[i]);
        }

        frame.setVisible(true);

        // Lanzar los hilos (usuarios)
        for (int i = 0; i < userLabels.length; i++) {
            final int index = i;
            new Thread(() -> useATM(index)).start();
            try {
                Thread.sleep(500); // Simular llegada escalonada
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void useATM(int index) {
        String name = "Usuario-" + (index + 1);
        updateLabel(index, name + " en fila", Color.ORANGE);

        try {
            semaphore.acquire(); // Acceder al cajero
            updateLabel(index, name + " usando el cajero", Color.GREEN);
            Thread.sleep((long) (Math.random() * 3000) + 1000); // Simular uso
            updateLabel(index, name + " terminó", Color.CYAN);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
            updateLabel(index, name + " liberó el cajero", Color.LIGHT_GRAY);
        }
    }

    private void updateLabel(int index, String text, Color color) {
        SwingUtilities.invokeLater(() -> {
            userLabels[index].setText(text);
            userLabels[index].setBackground(color);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ATMGUI::new);
    }
}
