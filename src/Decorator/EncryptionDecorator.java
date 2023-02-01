package Decorator;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import Main.Actor;
import Messages.Message;

public class EncryptionDecorator extends ActorDecorator {

    /*
    The EncryptionDecorator will encrypt (send) and decrypt
    message text (process) between communicating Actors.
     */

    public EncryptionDecorator(Actor actor){
        super(actor);
    }

    @Override
    public Message process(Message msg) {

        //Pre
        String newBody = "";
        try {
            newBody = DecryptPassword(msg.getBody());
            msg.setBody(newBody);
        } catch (Exception ex) {/*System.out.println(ex);*/}

        System.out.println("└-->DECRYPT message -> "+newBody);

        //body
        Message newMsg = super.process(msg);

        return newMsg;
    }

    @Override
    public void send(Message msg) {

        //Pre
        String body = msg.getBody();
        body = EncryptPassword(body);
        msg.setBody(body);

        //body
        super.send(msg);
    }

    public String EncryptPassword(String password)
    {
        //System.out.println("enc\t"+password);
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("IwantToPassPedro");
        encryptor.setAlgorithm("PBEWITHMD5ANDTRIPLEDES");
        String enc = encryptor.encrypt(password);
        return enc;
    }

    public String DecryptPassword(String enc)
    {
        //System.out.println("dec\t"+enc);
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("IwantToPassPedro");
        encryptor.setAlgorithm("PBEWITHMD5ANDTRIPLEDES");
        String dec = encryptor.decrypt(enc);
        return dec;
    }


}
