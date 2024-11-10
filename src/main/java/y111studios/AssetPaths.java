package y111studios;

import lombok.Getter;

/**
 * AssetPaths class used to store the paths to the assets used in the game.
 * 
 * <p>
 * This class is used to store the constant paths to the internal assets to reduce the occurence of
 * magic strings in the code.
 * </p>
 */
public enum AssetPaths {
    START_SCREEN("assets/StartScreen.png"),
    MAP_BACKGROUND("assets/UnisimMap.png"),
    MENU("assets/Menu.png"),
    ACCOMMODATION_MENU("assets/Accommodation.png"),
    CATERING_MENU("assets/Catering.png"),
    TEACHING_MENU("assets/Teaching.png"),
    PAUSE("assets/Pause.png"),
    ACC1("assets/Acc1.png"),
    ACC2("assets/Acc2.png"),
    ACC3("assets/Acc3.png"),
    ACC4("assets/Acc4.png"),
    ACC5("assets/Acc5.png"),
    CATER1("assets/Cater1.png"),
    CATER2("assets/Cater2.png"),
    CATER3("assets/Cater3.png"),
    REC1("assets/Rec1.png"),
    REC2("assets/Rec2.png"),
    TEACH1("assets/Teach1.png"),
    TEACH2("assets/Teach2.png"),
    TEACH3("assets/Teach3.png"),
    TEACH4("assets/Teach4.png"),
    TEACH5("assets/Teach5.png"),
    TRASH("custom_asset/trash.png");

    private @Getter final String path;

    AssetPaths(String path) {
        this.path = path;
    }

    // public final static String START_SCREEN = "assets/StartScreen.png";
    // public final static String MAP_BACKGROUND = "assets/UnisimMap.png";
    // public final static String MENU = "assets/Menu.png";
    // public final static String ACCOMMODATION_MENU = "assets/Accommodation.png";
    // public final static String CATERING_MENU = "assets/Catering.png";
    // public final static String TEACHING_MENU = "assets/Teaching.png";
    // public final static String PAUSE = "assets/Pause.png";

    // public final static String ACC1 = "assets/Acc1.png";
    // public final static String ACC2 = "assets/Acc2.png";
    // public final static String ACC3 = "assets/Acc3.png";
    // public final static String ACC4 = "assets/Acc4.png";
    // public final static String ACC5 = "assets/Acc5.png";
    // public final static String CATER1 = "assets/Cater1.png";
    // public final static String CATER2 = "assets/Cater2.png";
    // public final static String CATER3 = "assets/Cater3.png";
    // public final static String REC1 = "assets/Rec1.png";
    // public final static String REC2 = "assets/Rec2.png";
    // public final static String TEACH1 = "assets/Teach1.png";
    // public final static String TEACH2 = "assets/Teach2.png";
    // public final static String TEACH3 = "assets/Teach3.png";
    // public final static String TEACH4 = "assets/Teach4.png";
    // public final static String TEACH5 = "assets/Teach5.png";
    
}
