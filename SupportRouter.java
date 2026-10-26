public class SupportRouter {

    public static void main(String[] args) {
        int menuOption = 3;

        switch (menuOption) {
            case 1:
                System.out.println("Transferring to Technical Support...");
                break;
            case 2:
                System.out.println("Transferring to Billings & Accounts...");
                break;
            case 3:
                System.out.println("Transferring to Sales & Inquiries...");
                break;
            case 4:
                System.out.println("Connecting to Human Representative...");
                break;
            default:
                System.out.println("Invalid Selection. Please choose option 1 to 4.");
                break;
        }
    }
}