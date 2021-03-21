package tp3;

import scala.concurrent.duration.Duration;
import tp3.actors.Master;
import tp3.actors.Mapper;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.io.File;

public class Main {

    public static final String SYTEM_PATH = "akka://MainSystem";

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("MainSystem");
        ActorRef master;

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        master = system.actorOf(Props.create(Master.class), "master");

        system.actorOf(Props.create(Mapper.class), "mapper1");
        system.actorOf(Props.create(Mapper.class), "mapper2");

        master.tell(new File(classloader.getResource("le_corbeau_et_le_renard.txt").getFile()), ActorRef.noSender());

        system.awaitTermination();
        system.shutdown();
    }
}
