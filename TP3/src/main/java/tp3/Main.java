package tp3;

import com.typesafe.config.ConfigFactory;
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

    public static final String MASTER_SYSTEM = "MasterSystem";
    public static final String MAPPER_SYSTEM = "MapperSystem";
    public static final String MASTERSYSTEM_PATH = "akka.tcp://" + MASTER_SYSTEM + "@127.0.0.1:8081";
    public static final String MAPPERSYSTEM_PATH = "akka.tcp://" + MAPPER_SYSTEM + "@127.0.0.1:8080";

    public static final int NB_MAPPERS = 3;
    public static final int NB_REDUCERS = 2;

    public static void main(String[] args) {
        if (args.length == 0 || args[0].equals("mapper")) {
            startMapperSystem();
        }
        if (args.length == 0 || args[0].equals("master")) {
            startMasterSystem();
        }
    }

    private static void startMasterSystem() {
        ActorSystem masterSystem = ActorSystem.create(MASTER_SYSTEM,ConfigFactory.load("masterSystem"));
        ActorRef master;

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        master = masterSystem.actorOf(Props.create(Master.class), "master");

        for (int i=0; i < NB_REDUCERS ; i++) {
            masterSystem.actorOf(Props.create(Reducer.class), "reducer" + i);
        }

        master.tell(new File(classloader.getResource("demain_des_l_aube.txt").getFile()), ActorRef.noSender());

        try
        {
            Scanner in = new Scanner(System.in);
            System.out.println(">>> Press ENTER to print occurences by word for each reducer <<<");
            System.in.read();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        master.tell("AFFICHAGE_FINAL", ActorRef.noSender());


        masterSystem.awaitTermination();
        masterSystem.shutdown();
    }

    private static void startMapperSystem() {
        ActorSystem mapperSystem = ActorSystem.create(MAPPER_SYSTEM, ConfigFactory.load("mapperSystem"));
        for (int i=0; i < NB_MAPPERS ; i++) {
            mapperSystem.actorOf(Props.create(Mapper.class), "mapper" + i);
        }
    }
}
