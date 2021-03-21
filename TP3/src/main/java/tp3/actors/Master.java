package tp3.actors;

import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import tp3.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Master extends UntypedActor { //job : distribuer les lignes

    public File fileToProcess;

    @Override
    public void onReceive( Object message ) throws InterruptedException {
        if(message instanceof String)
        {
            this.affichage();
        }
        else if (message instanceof File)
        {
            System.out.println("[Master] Fichier reçu");
            fileToProcess = (File) message;
            this.sendLines();
        }
        else
        {
            System.out.println("unhandled");
            unhandled(message);
        }
    }

    private void affichage() {
        for (int i=0; i < Main.NB_REDUCERS; i++) {
            getContext().actorSelection(Main.SYTEM_PATH + "/user/reducer" + i%Main.NB_REDUCERS).tell("AFFICHAGE_FINAL", getSelf());
        }
    }

    private void sendLines()
    {
        int i = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileToProcess))) {
            String line;
            while ((line = br.readLine()) != null) {
                getContext().actorSelection(Main.SYTEM_PATH + "/user/mapper" + i%Main.NB_MAPPERS).tell(line.toUpperCase(), getSelf());
                i++;
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
