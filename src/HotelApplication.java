import ui.MainMenu;


public class HotelApplication {

    public static void main(String[] args){
        MainMenu mainMenu = new MainMenu();
        while(mainMenu.getKey()) {
            mainMenu.chooseOption();
        }
    }
}
