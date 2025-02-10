package config;

import static java.lang.String.valueOf;

public class Config {
//    public static final String REGEX_MONEY = "^[1-9]\\d{0,9}$";
    public static final String REGEX_MONEY = "^\\d+([.][0-9]{0,2})?$";
    public static final String REGEX_PHONE = "^0[0-9]{8,9}$";
    public static final String REGEX_GENDER = "^[Mm]ale$|^[Ff]emale$";
    public static final String REGEX_BIRTHDATE = "^[0-3][0-9]-[0-1][0-9]-[1-2][0-9]{3}$";
    public static final String REGEX_YN = "^[yn]$";
    public static final String REGEX_OPTION = "^[1-7]$";
    public static final String REGEX_CHOICE = "^[1-3]$";
    public static final String REGEX_USERNAME = "^[A-Za-z\\s]+$";
    public static final String BIRTHDATE_FORMAT = "dd-MM-yyyy";
}
