package in.co.rajpusht.rajpusht.Helper;

import java.util.ArrayList;

/**
 * Created by LENOVO on 10-Sep-18.
 */

public class ColorHelper {
    static ArrayList<String> list = new ArrayList<>();


    public static ArrayList<String> getList() {
        return list;
    }

    public static void setList(ArrayList<String> list) {
        ColorHelper.list = list;
    }
}