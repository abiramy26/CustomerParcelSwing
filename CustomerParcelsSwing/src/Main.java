import controller.CustomerController;
import controller.ParcelController;
import view.CustomerView;
import view.ParcelView;

public class Main {
    public static void main(String[] args) {
        CustomerController controller = new CustomerController();
        new CustomerView(controller);
        
        ParcelController parcelController = new ParcelController();
        new ParcelView(parcelController);
    }
}
