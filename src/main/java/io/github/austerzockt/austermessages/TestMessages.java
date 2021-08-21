package io.github.austerzockt.austermessages;

@AusterMessage
public interface TestMessages {
    @Message(message = "$player1$ has joined the Server, as has $player2$")
    String join(@Placeholder(name = "player1") Player player, @Placeholder(name = "player2") Player player2);
}
