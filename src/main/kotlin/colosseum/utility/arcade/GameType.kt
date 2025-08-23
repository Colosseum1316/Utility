package colosseum.utility.arcade

enum class GameType {
    /**
     * For unknown/unparsable GameType data, always use [None].
     */
    None("None"),

    /**
     * Run from dragon.
     */
    DragonEscape("Dragon Escape"),

    /**
     * Colosseum in Rome.
     */
    Gladiators("Gladiators"),

    /**
     * Hunters versus Hiders.
     */
    HideAndSeek("Hide and Seek"),

    /**
     * TNT booming.
     */
    Bombers("Bombers"),

    /**
     * Hunger games.
     */
    SurvivalGames("Survival Games"),

    /**
     * Battle among teams in small arena.
     */
    MicroBattle("Micro Battle"),

    /**
     * Counter Strike in Minecraft.
     */
    MineStrike("MineStrike"),

    /**
     * Classic minigames rotation 24/7.
     */
    NanoGames("Nano Games"),

    /**
     * Fire Snowballs.
     */
    Paintball("Paintball"),

    /**
     * Knife, bow and arrow.
     */
    OneInTheQuiver("One in the Quiver"),

    /**
     * Hex-A-Gone in Fall Guys.
     */
    Runner("Runner"),

    /**
     * Hunger games, but on floating islands above void.
     */
    SkyWars("SkyWars"),

    /**
     * Winter time throwing snow balls at each other.
     */
    SnowFight("Snow Fight"),

    /**
     * Basically shoveling snowy ground.
     */
    Spleef("Spleef"),

    /**
     * Fire for land.
     */
    TurfWars("Turf Wars");

    val gameName: String
    val lobbyName: String

    constructor(gameName: String) : this(gameName, gameName)

    constructor(gameName: String, lobbyName: String) {
        this.gameName = gameName
        this.lobbyName = lobbyName
    }

    fun getName(): String {
        return gameName
    }
}