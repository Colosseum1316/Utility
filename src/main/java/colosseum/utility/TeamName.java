package colosseum.utility;

public enum TeamName {
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

    TeamName(final int data) {
        this.data = (byte) data;
    }

    public static TeamName getByDyeColor(String color) {
        final String c = color.toUpperCase();
        TeamName r;
        switch (c) {
            case "WHITE":
                r = TeamName.White;
                break;
            case "ORANGE":
                r = TeamName.Orange;
                break;
            case "MAGENTA":
                r = TeamName.Magenta;
                break;
            case "LIGHT_BLUE":
                r = TeamName.Sky;
                break;
            case "YELLOW":
                r = TeamName.Yellow;
                break;
            case "LIME":
                r = TeamName.Lime;
                break;
            case "PINK":
                r = TeamName.Pink;
                break;
            case "GRAY":
                r = TeamName.Gray;
                break;
            case "SILVER":
                r = TeamName.LGray;
                break;
            case "CYAN":
                r = TeamName.Cyan;
                break;
            case "PURPLE":
                r = TeamName.Purple;
                break;
            case "BLUE":
                r = TeamName.Blue;
                break;
            case "BROWN":
                r = TeamName.Brown;
                break;
            case "GREEN":
                r = TeamName.Green;
                break;
            case "RED":
                r = TeamName.Red;
                break;
            case "BLACK":
                r = TeamName.Black;
                break;
            default:
                throw new IllegalArgumentException("Unknown dye color: " + color);
        }
        return r;
    }

    public byte getWoolData() {
        return data;
    }
}
