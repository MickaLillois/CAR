import actors.Master;
import actors.Mapper;
import actors.Reducer;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("MySystem");
        ActorRef master;


        master = system.actorOf(Props.create(Master.class), "master");


        system.shutdown();
    }
}
