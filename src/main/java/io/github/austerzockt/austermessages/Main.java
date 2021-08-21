package io.github.austerzockt.austermessages;

public class Main {
    public static void main(String[] args) {
        var message = MessageFactory.getMessage(TestMessages.class);
        System.out.println(message.join(new Player("X"), new Player("Y")));
        System.out.println(message.leave(() -> {
            return "X";
        }));
    }
}
