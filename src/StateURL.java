import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class StateURL {

    StateURL(){
        this.yesterdaySites = new Hashtable<>();
        this.todaySites = new Hashtable<>();
    }

    public void loadFromJson(String pathYesterdaySites, String pathTodaySites) throws IOException {

        this.loadYesterdaySitesFromJson(pathYesterdaySites);
        this.loadTodaySitesFromJson(pathTodaySites);

    }

    public void updateTodaySitesFromJson(String pathTodaySites) throws IOException {

        this.yesterdaySites = (Hashtable<String, String>) this.todaySites.clone();
        this.todaySites.clear();
        this.loadTodaySitesFromJson(pathTodaySites);

    }

    public ArrayList<String> getDisappearedUrl(){
        ArrayList<String> disappear = new ArrayList<>();
        Enumeration<String> urls = this.yesterdaySites.keys();

        while(urls.hasMoreElements()){

            String currentUrl = urls.nextElement();

            if(!this.todaySites.containsKey(currentUrl))
                disappear.add(currentUrl);
        }

        return disappear;
    }

    public ArrayList<String> getAppearedUrl(){
        ArrayList<String> appear = new ArrayList<>();
        Enumeration<String> urls = this.todaySites.keys();

        while(urls.hasMoreElements()){

            String currentUrl = urls.nextElement();

            if(!this.yesterdaySites.containsKey(currentUrl))
                appear.add(currentUrl);
        }

        return appear;
    }

    public ArrayList<String> getChangedUrl(){
        ArrayList<String> change = new ArrayList<>();
        Enumeration<String> urls = this.todaySites.keys();

        while(urls.hasMoreElements()){

            String currentUrl = urls.nextElement();

            if(this.yesterdaySites.containsKey(currentUrl) &&
                    !this.yesterdaySites.get(currentUrl).equals(this.todaySites.get(currentUrl)))
                change.add(currentUrl);
        }

        return change;
    }

    private void loadYesterdaySitesFromJson(String pathYesterdaySites) throws IOException {

        FileReader reader = new FileReader(pathYesterdaySites);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine(); //read "{"
        line = bufferedReader.readLine();

        while(!line.equals("}")){

            this.yesterdaySites.put(getKey(line), getValue(line));
            line = bufferedReader.readLine();
        }
    }

    private void loadTodaySitesFromJson(String pathTodaySites) throws IOException {

        FileReader reader = new FileReader(pathTodaySites);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine(); //read "{"
        line = bufferedReader.readLine();

        while(!line.equals("}")){

            this.todaySites.put(getKey(line), getValue(line));
            line = bufferedReader.readLine();
        }

    }

    private String getKey(String line){
        return line.substring(line.indexOf("\"") + 1, line.indexOf("\":\""));
    }

    private String getValue(String line){
        line = line.substring(line.indexOf("\":\"") + 2);

        if(line.contains(","))
            return line.substring(line.indexOf("\"") + 1, line.indexOf(",") - 1);
        else
            return line.substring(line.indexOf("\"") + 1, line.length() - 1);
    }

    private Hashtable<String, String> yesterdaySites;
    private Hashtable<String, String> todaySites;
}
