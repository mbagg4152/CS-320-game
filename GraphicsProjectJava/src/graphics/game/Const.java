package graphics.game;

import java.awt.*;

public class Const {
    public static final String USER_DIR = System.getProperty("user.dir");
    
    // icon paths
    public static final String ICON_FELLA = USER_DIR + "/res/icons/big-purp.png";
    public static final String ICON_BASIC = USER_DIR + "/res/icons/enemy-basic.png";
    public static final String ICON_FIRE = USER_DIR + "/res/icons/enemy-fire.png";
    public static final String ICON_PLAYER = USER_DIR + "/res/icons/player-blue.png";
    public static final String ICON_SLIME = USER_DIR + "/res/icons/enemy-slime.png";
    public static final String IMG_BG = USER_DIR + "/res/icons/background.png";
    public static final String IMG_START = USER_DIR + "/res/icons/start-btn.png";
    public static final String IMG_TITLE = USER_DIR + "/res/icons/title.png";
    
    // colors
    public static final Color COLOR_BASIC = new Color(205, 137, 0);
    public static final Color COLOR_FELLA = new Color(118, 0, 205);
    public static final Color COLOR_FLAME = new Color(176, 6, 42);
    public static final Color COLOR_PLAYER = new Color(27, 61, 163);
    public static final Color COLOR_SLIME = new Color(7, 154, 22);
    
    // sound paths
    public static final String OOF_ROBLOX = USER_DIR + "/res/sound/oof_roblox.wav";
    
    // sizes
    public static final int SIZE_BASIC = 64;
    public static final int SIZE_FLAME = 96;
    public static final int SIZE_PLAYER = 96;
    public static final int SIZE_SLIME = 128;
    public static final int SIZE_FELLA = 216;
    
}
