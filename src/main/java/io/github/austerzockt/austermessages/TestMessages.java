package io.github.austerzockt.austermessages;

@AusterMessage
public interface TestMessages {
    @Message(message = "$1 has joined the Server, as has $2")
    String join(Player player, Player player2);
    @Message(message = "$1 has left the server")
    String leave(PlaceholderSupport placeholderSupport);
}
