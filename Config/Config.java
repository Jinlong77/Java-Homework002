package Config;

import static java.lang.String.valueOf;

public class Config {
    public static final int COLUMN = 5;
    public static final String REGEX_ID = "^([1-9]\\d){1,9}$";
    public static final String REGEX_CHOICE_DELETE = "^[12]$";
    public static final String REGEX_PHONE = "^0[0-9]{9}$";
    public static final String REGEX_GENDER = "^[Mm]ale$|^[Ff]emale$";
    public static final String REGEX_YN = "^[yn]$";
    public static final String REGEX_BOOK_TITLE = "^[a-zA-Z0-9\\s]+$";
    public static final String REGEX_ADDRESS = "^[A-Za-z\\s]+$";
    public static final String REGEX_OPTION = "^[1-7]$";
    public static final String REGEX_BIRTHDATE = "^[1-2][0-9]{3}$";
    public static final String REGEX_USERNAME = "^[A-Za-z\\s]+$";
    public static final String REGEX_TABLE_OPTION = "^[1-5]$";
    public static final String REGEX_AUTHOR_YEAR_ACTIVE = "^[1-2][0-9]{3}-[1-2][0-9]{3}$";
    public static final String REGEX_SET_ROW = "^[1-9]\\d{0,2}$";
    public static final String ZONE_ID = "Asia/Bangkok";
    public static final String BIRTHDATE_FORMAT = "dd-mm-yyyy";


//    public static void tableShowing(Book[] books, int start, int end) {
//        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
//        Table table = new Table(COLUMN, UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
//        table.setColumnWidth(0, 8, 16);
//        table.setColumnWidth(1, 24, 26);
//        table.setColumnWidth(2, 33, 36);
//        table.setColumnWidth(3, 16, 20);
//        table.setColumnWidth(4, 16, 20);
//        table.addCell(BOLD + BLUE + "ID", cellStyle);
//        table.addCell(BOLD + CYAN + "TITLE", cellStyle);
//        table.addCell(BOLD + PURPLE + "Author", cellStyle);
//        table.addCell(BOLD + GREEN + "Publish Year", cellStyle);
//        table.addCell(BOLD + GREEN + "Status", cellStyle);
//
//        for (int i = start; i < end; i++) {
//            table.addCell(valueOf(GREEN + books[i].getId()), cellStyle);
//            table.addCell(PURPLE + books[i].getTitle(), cellStyle);
//            table.addCell(ITALIC + books[i].getAuthor().authorInfo(), cellStyle);
//            table.addCell(books[i].getPublishYear(), cellStyle);
//            table.addCell(books[i].getStatus().equals("Available") ? LIGHT_GREEN + books[i].getStatus() : RED + books[i].getStatus(), cellStyle);
//        }
//        System.out.println(table.render());
//    }
}
