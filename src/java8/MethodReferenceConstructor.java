package java8;

interface Messageable {
    Message getMessage(String msg);
}

class Message {
    Message(String msg) {
        System.out.println("Constructor is called");
        System.out.println(msg);
    }
}

public class MethodReferenceConstructor {
    public static void main(String[] args) {
        Messageable hello = Message::new;
        System.out.println("Constructor isn't called yet");
        hello.getMessage("Hello");
        System.out.println(hello);

    }
}
