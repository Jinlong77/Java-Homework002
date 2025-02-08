package Config;

import static Config.Config.REGEX_OPTION;

public class Validation {

    public static boolean isOptionValid(String options) {
        if(options.matches(REGEX_OPTION)) {
            return true;
        }
        return false;
    }
}
