package io.github.austerzockt.austermessages;

public class Main {
    public static void main(String[] args) {
       new Main().run();

    }
    public void run() {
        MessageFactory messageFactory = new MessageFactory();
        messageFactory.registerResolver(Player.class, Player::getName);
        var message = messageFactory.getMessage(TestMessages.class);
        System.out.println(message.join(new Player("X"), new Player("Y")));
    }
}
