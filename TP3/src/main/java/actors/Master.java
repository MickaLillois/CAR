package actors;

import akka.actor.UntypedActor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Master extends UntypedActor { //job : distribuer les lignes

    public File fileToProcess;

    public void onReceive( Object message ) throws InterruptedException {
        if(message instanceof String)
        {
            System.out.println("string");
        }
        else if (message instanceof File)
        {
            fileToProcess = (File) message;
            this.sendLines();
        }
        else
        {
            System.out.println("unhandled");
            unhandled(message);
        }
    }

    private void sendLines()
    {
        int i = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileToProcess))) {
            String line;
            while ((line = br.readLine()) != null) {
                //mapper1.tell(line.toUpperCase(), getSelf());
                i++;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
