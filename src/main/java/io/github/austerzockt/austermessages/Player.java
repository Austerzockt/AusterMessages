package io.github.austerzockt.austermessages;

//just a class to test some stuff. yeah you might need to wrap a few bukkit classes because of the interface dependency, might provide libs for that
public class Player implements PlaceholderSupport{
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String placeholder() {
        return getName();
    }
}
