package gq.bxteam.ndailyrewards.cfg;

import java.util.ArrayList;
import java.util.List;

import gq.bxteam.ndailyrewards.NDailyRewards;

@SuppressWarnings("javadoc")
public enum Lang {
    Prefix("Prefix", 0, "&6[&eNDailyRewards&6] &7"),
    Commands_Help_Usage("Commands_Help_Usage", 1, "&cUsage: &e/ndailyrewards %command% %usage%"),
    Commands_Help_Commands("Commands_Help_Commands", 2, "&8&m-----------&8&l[ &aNDailyRewards &8&l]&8&m-----------\n\n&2» &a/ndailyrewards &7- Open GUI.\n&2» &a/ndailyrewards help &7- Help page.\n&2» &a/ndailyrewards reload &7- Reload the plugin.\n&2» &a/ndailyrewards version &7- Sends current plugin version.\n&2» &a/ndailyrewards backup &7- Saves your config and database.\n\n&8&m--------------------------------------"),
    Command_Reload_Exec("Command_Reload_Exec", 3, "&aConfiguration reloaded!"),
    Time_Sec("Time_Sec", 4, "sec."),
    Time_Min("Time_Min", 5, "m."),
    Time_Hour("Time_Hour", 6, "h."),
    Time_Day("Time_Day", 7, "d."),
    Error_NoPerm("Error_NoPerm", 8, "&cYou don't have permissions to do that."),
    Error_Number("Error_Number", 9, "&cInvalid number: &e%num%"),
    Error_NoPlayer("Error_NoPlayer", 10, "&cPlayer not found!"),
    Error_Console("Error_Console", 11, "&cThis command is only for players!");

    private final String msg;
    private static MyConfig config;

    Lang(final String name, final int ordinal, final String msg) {
        this.msg = msg;
    }

    public String getPath() {
        return this.name().replace("_", ".");
    }

    public String getMsg() {
        return this.msg;
    }

    public String toMsg() {
        return NDailyRewards.replaceHEXColorCode(Lang.config.getConfig().getString(this.getPath()));
    }

    public List<String> getList() {
        final List<String> list = new ArrayList<>();
        for (final String s : Lang.config.getConfig().getStringList(this.getPath())) {
            list.add(NDailyRewards.replaceHEXColorCode(s));
        }
        return list;
    }

    public static String getCustom(final String path) {
        return NDailyRewards.replaceHEXColorCode(Lang.config.getConfig().getString(path));
    }

    public static void setup(final MyConfig config) {
        Lang.config = config;
        load();
    }

    private static void load() {
        Lang[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final Lang lang = values[i];
            if (Lang.config.getConfig().getString(lang.getPath()) == null) {
                if (lang.getMsg().contains("\n")) {
                    final List<String> list = new ArrayList<>();
                    final String[] ss = lang.getMsg().split("\n");
                    String[] array;
                    for (int length2 = (array = ss).length, j = 0; j < length2; ++j) {
                        final String s = array[j];
                        list.add(s);
                    }
                    Lang.config.getConfig().set(lang.getPath(), list);
                } else {
                    Lang.config.getConfig().set(lang.getPath(), lang.getMsg());
                }
            }
        }
        Lang.config.save();
    }
}