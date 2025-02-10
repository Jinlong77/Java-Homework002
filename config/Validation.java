package config;

import static config.Config.REGEX_CHOICE;
import static config.Config.REGEX_OPTION;
import static config.Message.printError;

public class Validation {

    public static String isOptionValid(String options) {
        if (options.matches(REGEX_OPTION)) {
            return options;
        }
        printError("Please enter a valid option. [1-7]");
        return null;
    }

    public static String isChoiceValid(String choice) {
        if (choice.matches(REGEX_CHOICE)) {
            return choice;
        }
        printError("Please enter a valid choice. [1-3]");
        return null;
    }
}
