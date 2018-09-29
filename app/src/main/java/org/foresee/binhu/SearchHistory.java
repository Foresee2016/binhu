package org.foresee.binhu;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * 负责记录搜索历史，使用SharedPreference，因为其中只能存基本类型，所以用字符串，维护定长数组。
 */
public class SearchHistory {
    private static final int HISTORY_ITEM_COUNT = 20; //记录搜索历史数量
    private static final String HISTORY_KEY = "search_history";

    public static void saveHistoryString(Context context, String item) {
        String[] items = getHistoryString(context);
        if (items.length >= HISTORY_ITEM_COUNT) {
            System.arraycopy(items, 0, items, 1, items.length - 1);
            items[0] = item;
        } else {
            String[] oldItems = items;
            items = new String[oldItems.length + 1];
            System.arraycopy(oldItems, 0, items, 1, oldItems.length);
            items[0] = item;
        }
        String newStr = Utils.join("#", items);
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(HISTORY_KEY, newStr).apply();
    }
    public static String[] getHistoryString(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String itemsStr = sharedPreferences.getString(HISTORY_KEY, "");
        if(itemsStr.length()==0){
            return new String[0];
        }
        return itemsStr.split("#");
    }
}
