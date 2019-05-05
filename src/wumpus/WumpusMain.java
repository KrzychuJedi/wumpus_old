package wumpus;

public class WumpusMain {

    private CommandParser commandParser;
    private WumpusGame game;

    public WumpusMain(WumpusGame game) {
        this.game = game;
        commandParser = new CommandParser();
        configureCommandParser();
    }

    void configureCommandParser() {
        getCommandParser().add("e", MoveEastCommand.class);
        getCommandParser().add("move e", MoveEastCommand.class);
        getCommandParser().add("w", MoveWestCommand.class);
        getCommandParser().add("move w", MoveWestCommand.class);
        getCommandParser().add("n", MoveNorthCommand.class);
        getCommandParser().add("move n", MoveNorthCommand.class);
        getCommandParser().add("s", MoveSouthCommand.class);
        getCommandParser().add("move s", MoveSouthCommand.class);
        getCommandParser().add("q", QuitCommand.class);
        getCommandParser().add("quit", QuitCommand.class);
        getCommandParser().add("shoot magic", ShootMagicCommand.class);
    }

    public static void main(String[] args) throws Exception {
        GameReporter reporter = new TextGameReporter();
        WumpusGame game = WumpusGameCreator.createTestGame(reporter);
        WumpusMain wumpusMain = new WumpusMain(game);
        wumpusMain.getGame().evaluate();
        while (!wumpusMain.game.gameOver()) {
            StringBuilder cmd = new StringBuilder();
            char c = (char) System.in.read();
            while (c != '\n') {
                cmd.append(c);
                c = (char) System.in.read();
            }
            wumpusMain.executeCommand(cmd.toString());
            continue;
        }
    }

    public boolean executeCommand(String cmd) {
        cmd = cmd.toLowerCase();
        Command command = getCommandParser().getCommand(cmd);
        if (command == null) {
            return false;
        }
        command.execute(getGame());
        getGame().evaluate();
        return true;
    }

    /**
     * @return the commandParser
     */
    public CommandParser getCommandParser() {
        return commandParser;
    }

    /**
     * @param commandParser the commandParser to set
     */
    public void setCommandParser(CommandParser commandParser) {
        this.commandParser = commandParser;
    }

    /**
     * @return the game
     */
    public WumpusGame getGame() {
        return game;
    }

    /**
     * @param game the game to set
     */
    public void setGame(WumpusGame game) {
        this.game = game;
    }
}
