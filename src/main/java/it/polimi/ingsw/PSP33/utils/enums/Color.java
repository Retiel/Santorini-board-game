package it.polimi.ingsw.PSP33.utils.enums;

import java.util.HashMap;
import java.util.Map;

public enum Color {
    RED(1),
    BLUE(2),
    GREEN(3);

    private final int index;

    Color(int index) {
        this.index = index;
    }

    public static int getIndex(Color color) {
        return color.index;
    }

    private static final Map<Integer, Color> BY_INDEX = new HashMap<>();

    static {
        for(Color color : values()) {
            BY_INDEX.put(color.index, color);
        }
    }

    public static Color getColorByIndex(int index) {
        return BY_INDEX.get(index);
    }
}
