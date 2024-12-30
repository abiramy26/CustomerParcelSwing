import controller.CustomerController;
import view.CustomerView;

public class Main {
    public static void main(String[] args) {
        CustomerController controller = new CustomerController();
        new CustomerView(controller);
    }
}
