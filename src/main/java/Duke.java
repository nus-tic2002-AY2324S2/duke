public class Duke {
    private static Gui gui;

    public static void main(String[] args) {
        gui = new Gui();
        gui.welcome();

        String userinput = gui.userInput(); //Takes in user input

        System.out.println(userinput); //Prints out user input

        switch(userinput) {
            case "LIST":
                break;
            case "BYE":
                gui.bye();
                break;
            default:
                System.out.println("Invalid Command! Please try again!");
        }

    }
}
