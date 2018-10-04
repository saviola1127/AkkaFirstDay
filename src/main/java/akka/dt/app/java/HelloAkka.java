package akka.dt.app.java;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.dt.app.java.actors.MasterActor;
import akka.dt.app.java.messages.Result;

/**
 * Created by savypan
 * On 2018/10/4 20:34
 */
public class HelloAkka {

    public static void main(String[] args) throws InterruptedException {
        ActorSystem _system = ActorSystem.create("HelloAkka");

        ActorRef master = _system.actorOf(new Props(MasterActor.class), "master");

        master.tell("Hi my name is Savy, I'm so so so happy to be here");
        master.tell("Today I'm going to read a letter for you");
        master.tell("I hope I'm sure you like Savy");

        Thread.sleep(500);

        master.tell(new Result());

        Thread.sleep(500);

        _system.shutdown();

    }
}
