import Decorator.ActorDecorator;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class EncryptionDecorator extends ActorDecorator {

    public EncryptionDecorator(Actor actor){
        super(actor);
    }

    @Override
    public Message process() {
        //Pre-funcionalitats

        System.out.println("DECRYPT");

        //body
        Message msg = super.process();

        //Post-funcionaliats
        String newBody = msg.getBody();
        try {
            newBody = DecryptPassword(newBody);
            msg.setBody(newBody);
        } catch (Exception e) {}

        return msg;
    }


    @Override
    public void send(Message msg) {
        String body = msg.getBody();
        body = EncryptPassword(body);
        msg.setBody(body);
        super.send(msg);
    }

    public String EncryptPassword(String password)
    {
        //System.out.println("enc\t"+password);

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("9daed9cd-e828-485f-a0a9-c63cfc364f4b");
        encryptor.setAlgorithm("PBEWITHMD5ANDTRIPLEDES");
        String enc = encryptor.encrypt(password);
        return enc;
    }

    public String DecryptPassword(String enc)
    {
        //System.out.println("dec\t"+enc);
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("9daed9cd-e828-485f-a0a9-c63cfc364f4b");
        encryptor.setAlgorithm("PBEWITHMD5ANDTRIPLEDES");
        String dec = encryptor.decrypt(enc);
        return dec;
    }


}
