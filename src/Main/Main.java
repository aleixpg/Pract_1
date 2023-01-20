import Decorator.*;
public class Main {

    public static void main(String[] args) throws InterruptedException {
        //Impossible to create instances using "new"
        //ActorContext actorContext=new ActorContext();
        ActorContext actorContext = ActorContext.getInstance();
        Actor pere = actorContext.spawnActor("Pere", new HelloWorldActor("Pere"));
        Actor moya = actorContext.spawnActor("Moya", new InsultActor("Moya"));
        Actor main = actorContext.spawnActor("Main", new MainActor("Main"));

        ActorProxy pauProxy = new ActorProxy(actorContext.spawnActor("Pau", new HelloWorldActor("Pere")));

        main = new TimeDecorator(main);

        //pere = new TimeDecorator(pere);
        //pere = new EncryptionDecorator(pere);

        //moya = new EncryptionDecorator(moya);
        //moya = new TimeDecorator(moya);

        //pauProxy = new TimeDecorator(pauProxy);

        //pere.send(new NormalMessage(main,"Hello World"));
        //moya.send(new QuitMessage(main,""));

        moya.send(new GetInsultMessage(main,""));
        //Message resultM = moya.process();
        //System.out.println(resultM.getBody());
        moya.send(new QuitMessage(main,"kill"));

        moya.send(new AddInsultMessage(main,"jodido"));
        //resultM = moya.process();
        //System.out.println(resultM.getBody());
        moya.send(new GetAllInsultsMessage(main,""));
        //resultM = moya.process();
        //System.out.println(resultM.getBody());
        //moya.interrupt();


        //actorContext2 = actorContext --> both are the same instance, according to Singleton
        ActorContext actorContext2 = ActorContext.getInstance();

        System.out.println("Name of all actors:");
        for (String name: actorContext2.getNames()) {
            System.out.println(name);

        }

        Thread.sleep(2000);
        //pere.send(new AddInsultMessage(main,"Hello World"));
        //pere.send(new QuitMessage(main,""));
        //moya.send(new QuitMessage(main,""));
        main.send(new QuitMessage(main,"kill"));


    }

}
