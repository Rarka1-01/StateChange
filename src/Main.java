import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        MailCreator mc = new MailCreator("src/outMessage/", "Надежда Ивановна");

        try{

            StateURL state = new StateURL();
            state.loadFromJson("src/resources/yesterday.json", "src/resources/today.json");

            mc.createMail(state);

        }catch (IOException e){

            System.out.println(e.getMessage());
        }

    }

}