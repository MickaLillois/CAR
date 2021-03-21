package tp3.actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import java.util.HashMap;
import java.util.Map;

public class Reducer extends UntypedActor {

    private HashMap<String, Integer> occurencesParMot = new HashMap<String, Integer>();

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String)
        {
            String word = (String) message;
            if(word.equals("AFFICHAGE_FINAL")){
                //System.out.println("ici zebi");
                this.affichage();
            }
            else
            {
                //System.out.println("ici trou du cul");
                if (!this.occurencesParMot.containsKey(word))
                {
                    occurencesParMot.put(word, 1);
                }
                else
                {
                    occurencesParMot.put(word, occurencesParMot.get(word) + 1);
                }
            }

            //System.out.println("[" + getSelf().toString() + "] Mot reçu : " + word + " --> " + occurencesParMot.get(word) + " occurences");
        }
        else{
            unhandled(message);
        }
    }

    public void affichage(){
        for (Map.Entry<String, Integer> entry : occurencesParMot.entrySet()) {
            System.out.println(getSelf() + ": " + entry.getKey() + " -> " + entry.getValue());
        }
    }
}
