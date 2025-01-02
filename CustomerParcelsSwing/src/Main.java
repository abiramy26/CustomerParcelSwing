import controller.CustomerController;
import controller.MainController;
import controller.ParcelController;
import view.CustomerView;
import view.MainView;
import view.ParcelView;

public class Main {
    public static void main(String[] args) {
    	
    	MainController mainController = new MainController();
        new MainView(mainController); // Start with the MainView
        
//        ParcelController parcelController = new ParcelController();
//        new ParcelView(parcelController);
    }
}
