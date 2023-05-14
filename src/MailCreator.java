import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MailCreator {

    MailCreator(String directorySave, String nameSecretary){
        this.directorySave = directorySave;
        this.nameSecretary = nameSecretary;
    }

    public void createMail(StateURL state) throws IOException {

        ArrayList<String> disappeared = state.getDisappearedUrl();
        ArrayList<String> appeared = state.getAppearedUrl();
        ArrayList<String> change = state.getChangedUrl();

        SimpleDateFormat formatter= new SimpleDateFormat("HH-mm-ss");
        Date date = new Date(System.currentTimeMillis());
        String fileName = formatter.format(date);

        File file = new File(this.directorySave + fileName + ".txt");

        FileWriter writer = new FileWriter(file);
        writer.write("Здравствуйте, дорогая " + this.nameSecretary + "\n\n");
        writer.write("За последние сутки во вверенных Вам сайтах произошли следующие изменения:\n\n");
        this.addToMailWithFormat(writer, "Исчезли следующие страницы:", disappeared);
        this.addToMailWithFormat(writer, "Появились следующие новые страницы:", appeared);
        this.addToMailWithFormat(writer, "Изменились следующие страницы:", change);
        writer.write("\n\nС уважением,\n" +
                "автоматизированная система\n" +
                "мониторинга.");
        writer.flush();

        writer.close();
    }

    private void addToMailWithFormat(FileWriter writer, String message, ArrayList<String> values) throws IOException {

        writer.write(message + "\n");

        for(String i: values)
            writer.write("\t--- " + i + "\n");

        writer.write("\n\n");
    }

    private String directorySave;
    private String nameSecretary;
}
