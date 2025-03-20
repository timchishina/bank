package org.example.Commands;

public class TimedCommand implements Command {
    private final Command command;

    public TimedCommand(Command command) {
        this.command = command;
    }

    @Override
    public void execute() {
        long startTime = System.nanoTime();
        command.execute();
        long endTime = System.nanoTime();
        System.out.println("Время выполнения: " + (endTime - startTime) / 1_000_000 + " мс");
    }
}
