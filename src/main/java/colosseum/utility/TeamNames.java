package colosseum.utility;

public enum TeamNames {
    White(0x0),
    Orange(0x1),
    Magenta(0x2),
    Sky(0x3),
    Yellow(0x4),
    Lime(0x5),
    Pink(0x6),
    Gray(0x7),
    LGray(0x8),
    Cyan(0x9),
    Purple(0xA),
    Blue(0xB),
    Brown(0xC),
    Green(0xD),
    Red(0xE),
    Black(0xF);

    private final byte data;

    TeamNames(final int data) {
        this.data = (byte) data;
    }

    public byte getWoolData() {
        return data;
    }

    public static TeamNames getByDyeColor(String color) {
        final String c = color.toUpperCase();
        return switch (c) {
            case "WHITE" -> TeamNames.White;
            case "ORANGE" -> TeamNames.Orange;
            case "MAGENTA" -> TeamNames.Magenta;
            case "LIGHT_BLUE" -> TeamNames.Sky;
            case "YELLOW" -> TeamNames.Yellow;
            case "LIME" -> TeamNames.Lime;
            case "PINK" -> TeamNames.Pink;
            case "GRAY" -> TeamNames.Gray;
            case "SILVER" -> TeamNames.LGray;
            case "CYAN" -> TeamNames.Cyan;
            case "PURPLE" -> TeamNames.Purple;
            case "BLUE" -> TeamNames.Blue;
            case "BROWN" -> TeamNames.Brown;
            case "GREEN" -> TeamNames.Green;
            case "RED" -> TeamNames.Red;
            case "BLACK" -> TeamNames.Black;
            default -> throw new IllegalArgumentException("Unknown dye color: " + color);
        };
    }
}
