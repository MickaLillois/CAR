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
            if(word.equals("AFFICHAGE_FINAL"))
            {
                this.affichage();
            }
            else
            {
                if (!this.occurencesParMot.containsKey(word))
                {
                    occurencesParMot.put(word, 1);
                }
                else
                {
                    occurencesParMot.put(word, occurencesParMot.get(word) + 1);
                }
            }
        }
        else{
            unhandled(message);
        }
    }

    public void affichage(){
        int nbWords = 0;
        for (Map.Entry<String, Integer> entry : occurencesParMot.entrySet()) {
            System.out.println(getSelf() + ": " + entry.getKey() + " -> " + entry.getValue());
            nbWords += entry.getValue();
        }
        System.out.println("[" + getSelf().toString() + "] Il y a " + occurencesParMot.size() + " mots différents et " + nbWords + " mots lus au total sur ce reducer");
    }
}
