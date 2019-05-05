package wumpus;

import java.util.*;

public class CommandParser {

    Map commandMap = new Hashtable();

    public void add(String s, Class commandClass) {
        commandMap.put(s, commandClass);
    }

    public Command getCommand(String s) {
        Command command = null;
        String currentCommandString = s;
        while (!"".equals(currentCommandString)) {
            if (command != null) {
                String arguments =s.substring(currentCommandString.length(),s.length()).trim();
                List wordsList = buildListOfWords(arguments);
                for (Iterator i = wordsList.iterator(); i.hasNext(); ) {
                    command.addArg(((String)i.next()).toUpperCase());
                }
                break;
            }
            else if (command == null) {
                Class commandClass = (Class) commandMap.get(currentCommandString);
                if (commandClass == null) {
                    currentCommandString = removeLastWord(currentCommandString);
                }
                else {
                    try
                    {
                    command = (Command)commandClass.newInstance();
                    }catch(Exception e)
                    {
                        command = null;
                    }
                }
            }
        }
        if(command == null)
            return null;

        else if(command.validate() )
        {
            return command;
        }else
        {
            return null;
        }
    }

    private List buildListOfWords(String s) {
        if ("".equals(s))
            return new ArrayList();

        List wordsList;
        String[] words = s.trim().split(" ");
        wordsList = Arrays.asList(words);
        return wordsList;
    }

    public String removeLastWord(String s) {
        String[] words = s.split(" ");
        StringBuffer buff = new StringBuffer();
        for(int i=0;i < words.length-1; i++){
            buff.append(words[i] + " ");
        }
        return buff.toString().trim();
    }
}
