package graphics.game;

import java.awt.*;

public class Const {
    public static final String USER_DIR = System.getProperty("user.dir");

    // icon paths
    public static final String
            ICON_FELLA = USER_DIR + "/res/icons/big-purp.png",
            ICON_BASIC = USER_DIR + "/res/icons/enemy-basic.png",
            ICON_FIRE = USER_DIR + "/res/icons/enemy-fire.png",
            ICON_PLAYER = USER_DIR + "/res/icons/player-blue.png",
            ICON_SLIME = USER_DIR + "/res/icons/enemy-slime.png",
            IMG_BG = USER_DIR + "/res/icons/background.png",
            IMG_START = USER_DIR + "/res/icons/start-btn.png",
            IMG_AGAIN = "res/icons/again-btn.png",
            IMG_TITLE = USER_DIR + "/res/icons/title.png",
            IMG_DIED = USER_DIR + "/res/icons/title-again.png",
            PAST_TIME = USER_DIR + "/res/past-time.txt";

    // colors
    public static final Color COLOR_FELLA = new Color(118, 0, 205);
    public static final Color COLOR_BASIC = new Color(78, 78, 78);
    public static final Color COLOR_FLAME = new Color(176, 6, 42);
    public static final Color COLOR_PLAYER = new Color(27, 61, 163);
    public static final Color COLOR_SLIME = new Color(7, 154, 22);

    // sound paths
    public static final String OOF_ROBLOX = USER_DIR + "/res/sound/oof_roblox.wav";

    // sizes
    public static final int SIZE_BASIC = 48;
    public static final int SIZE_FLAME = 96;
    public static final int SIZE_PLAYER = 96;
    public static final int SIZE_SLIME = 128;
    public static final int SIZE_FELLA = 216;

}
