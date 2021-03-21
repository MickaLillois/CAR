package tp3.actors;

import akka.actor.UntypedActor;

import java.io.File;

public class Mapper extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof String)
        {
            System.out.println("[" + getSelf().toString() + "] Ligne reçue : " + (String) message);
        }
        else
        {
            System.out.println("unhandled");
            unhandled(message);
        }
    }
}
