import actors.Master;
import actors.Mapper;
import actors.Reducer;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("MySystem");
        ActorRef master;

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        master = system.actorOf(Props.create(Master.class), "master");

        system.actorOf(Props.create(Mapper.class), "mapper1");
        system.actorOf(Props.create(Mapper.class), "mapper2");

        master.tell( "le message",ActorRef.noSender());
        master.tell( new Object(),ActorRef.noSender());

        master.tell(new File(classloader.getResource("le_corbeau_et_le_renard.txt").getFile()), ActorRef.noSender());

        system.shutdown();
    }
}
