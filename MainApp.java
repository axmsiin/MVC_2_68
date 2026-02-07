import src.View.CitizenRegisterView;

public class MainApp {

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {
            new CitizenRegisterView();
        });
    }
}