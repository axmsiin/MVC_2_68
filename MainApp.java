import src.View.CitizenRegisterView;

public class MainApp {

    public static void main(String[] args) {

        // รัน GUI บน Event Dispatch Thread (Swing มาตรฐาน)
        javax.swing.SwingUtilities.invokeLater(() -> {
            new CitizenRegisterView();
        });
    }
}
