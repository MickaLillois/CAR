package tp3.actors;

import akka.actor.ActorRef;
import akka.actor.Inbox;
import akka.actor.UntypedActor;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import tp3.Main;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Mapper extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof String)
        {
            this.partition((String) message);
        }
        else
        {
            System.out.println("unhandled");
            unhandled(message);
        }
    }

    private void partition(String line) {
        String clearLine = Mapper.cleanText(line);
        String[] lineSplitted = clearLine.split(" ");

        for (String word : lineSplitted) {
            getContext().actorSelection(Main.SYTEM_PATH + "/user/reducer" + Math.abs(word.hashCode() % Main.NB_REDUCERS)).tell(word,ActorRef.noSender());
        }
    }

    public static String cleanText(String text) {
        //stackoverflow
        String result = text.replaceAll("[\"()\n,.!?;':]", " ");
        result = result.trim();
        result = result.replaceAll("( )+", " ");

        return result;
    }

}
