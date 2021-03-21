package tp3;

import scala.concurrent.duration.Duration;
import tp3.actors.Master;
import tp3.actors.Mapper;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import tp3.actors.Reducer;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static final String SYTEM_PATH = "akka://MainSystem";
    public static final int NB_MAPPERS = 3;
    public static final int NB_REDUCERS = 2;

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("MainSystem");
        ActorRef master;

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        master = system.actorOf(Props.create(Master.class), "master");

        for (int i=0; i < NB_MAPPERS ; i++) {
            system.actorOf(Props.create(Mapper.class), "mapper" + i);
        }

        for (int i=0; i < NB_REDUCERS ; i++) {
            system.actorOf(Props.create(Reducer.class), "reducer" + i);
        }

        master.tell(new File(classloader.getResource("le_corbeau_et_le_renard.txt").getFile()), ActorRef.noSender());

        try
        {
            Scanner in = new Scanner(System.in);
            System.out.println(">>> Press ENTER to print occurences by word for each reducer <<<");
            System.in.read();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        master.tell("AFFICHAGE_FINAL", ActorRef.noSender());


        system.awaitTermination();
        system.shutdown();
    }
}
