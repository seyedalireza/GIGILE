import controller.Controller;
import model.User;
import model.gigilhome.Home;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        User[] users = {new User(), new User()};
        controller.setUsers(users);
        controller.cliControl();
    }
}
