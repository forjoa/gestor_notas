import app.MainApp;
import app.SplashScreen;

public class Main {
    public static void main(String[] args) {
        SplashScreen splashScreen = new SplashScreen();
        splashScreen.setVisible(true);

        for (int i = 0; i <= 100; i++) {
            try {
                Thread.sleep(5);
                splashScreen
                        .updateProgress(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        splashScreen.dispose();

        MainApp mainApp = new MainApp();
        mainApp.setVisible(true);
    }
}
