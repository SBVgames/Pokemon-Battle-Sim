import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class PokemonBattleSimulator {

    static Scanner input = new Scanner(System.in);
    static Random random = new Random();

    // Pokemon information
    static String[] pokemonNames = {
        "Raichu",
        "Arcanine",
        "Blastoise",
        "Venusaur",
        "Charizard",
        "Snorlax",
        "Dragonite",
        "Gengar",
        "Alakazam",
        "Machamp",
        "Gyarados",
        "Scizor",
        "Salamence",
        "Tyranitar",
        "Metagross",
        "Steelix",
        "Garchomp",
        "Blaziken",
        "Swampert",
        "Sceptile",
        "Absol",
        "Milotic",
        "Togekiss",
        "Heracross",
        "Gardevoir",
        "Breloom",
        "Typhlosion",
        "Feraligatr",
        "Ampharos",
        "Lapras",
        "Nidoking"

    };

    static String[][] pokemonTypes = {
        {"Electric"},
        {"Fire"},
        {"Water"},
        {"Grass", "Poison"},
        {"Fire", "Flying"},
        {"Normal"},
        {"Dragon", "Flying"},
        {"Ghost", "Poison"},
        {"Psychic"},
        {"Fighting"},
        {"Water", "Flying"},
        {"Bug", "Steel"},
        {"Dragon", "Flying"},
        {"Rock", "Dark"},
        {"Steel", "Psychic"},
        {"Steel", "Ground"},
        {"Dragon", "Ground"},
        {"Fire", "Fighting"},
        {"Water", "Ground"},
        {"Grass"},
        {"Dark"},
        {"Water"},
        {"Fairy", "Flying"},
        {"Bug", "Fighting"},
        {"Psychic", "Fairy"},
        {"Grass", "Fighting"},
        {"Fire"},
        {"Water"},
        {"Electric"},
        {"Water", "Ice"},
        {"Poison", "Ground"}
        
    };

    static int[][] moveAccuracy = {
    {70, 75, 100, 100},    // Thunder, Iron Tail, Thunder Punch
    {100, 100, 90, 100},   // Flamethrower, Extreme Speed, Fire Fang
    {80, 100, 95, 100},    // Hydro Pump, Iron Defense, Steel Cannon
    {100, 100, 75, 75},   // Giga Drain, Sludge Bomb, Poison Powder
    {100, 100, 100, 100},  // Flamethrower, Acrobatics, Dragon Claw
    {100, 100, 100, 100},  // Body Slam, Earthquake, Rest
    {100, 70, 90, 100},    // Dragon Claw, Hurricane, Hyper Beam
    {100, 100, 100, 100},  // Shadow Ball, Sludge Bomb, Destiny Bond
    {100, 100, 70, 100},   // Psychic, Shadow Ball, Focus Blast
    {50, 100, 100, 80},   // Dynamic Punch, Bullet Punch, Knock Off
    {100, 100, 100, 100},  // Waterfall, Ice Beam, Earthquake
    {100, 100, 100, 100},  // Bullet Punch, Close Combat, Swords Dance
    {95, 90, 100, 100},    // Air Slash, Heat Wave, Dragon Cheer
    {90, 100, 100, 100},   // Rock Slide, Knock Off, Low Kick
    {100, 100, 100, 100},  // Psychic, Bullet Punch, Heavy Slam
    {100, 100, 100, 100},  // Body Press, Iron Defense, Wide Guard
    {100, 100, 100, 100},  // Earthquake, Dragon Claw, Stomping Tantrum
    {100, 100, 100, 100},  // Flare Blitz, Close Combat, Swords Dance
    {100, 100, 100, 100},  // Wave Crash, Earthquake, Ice Punch
    {90, 100, 70, 100},    // Leaf Storm, Dragon Pulse, Focus Blast
    {100, 100, 100, 100},  // Sucker Punch, Night Slash, Psycho Cut
    {100, 95, 100, 100},   // Recover, Icy Wind, Scald
    {95, 100, 100, 90},   // Air Slash, Aura Sphere, Dazzling Gleam
    {100, 100, 100, 85},  // Close Combat, Facade, Knock Off
    {100, 100, 100, 100},  // Moonblast, Dazzling Gleam, Psychic
    {100, 100, 100, 95},  // Spore, Mach Punch, Bullet Seed
    {100, 90, 100, 70},   // Eruption, Heat Wave, Solar Beam
    {100, 100, 100, 100},  // Ice Punch, Waterfall, Dragon Dance
    {100, 100, 100, 100},  // Dazzling Gleam, Thunderbolt, Power Gem
    {100, 100, 30, 100},   // Perish Song, Ice Beam, Sheer Cold
    {100, 100, 100, 100}   // Earth Power, Ice Beam, Sludge Bomb
};

    static int[][] movePriority = {
    {0, 0, 0, 0},      // Thunder, Iron Tail, Thunder Punch
    {0, 2, 0, 0},      // Flamethrower, Extreme Speed, Fire Fang
    {0, 0, 0, 0},      // Hydro Pump, Iron Defense, Steel Cannon
    {0, 0, 0, 0},   // Giga Drain, Sludge Bomb, Poison Powder
    {0, 0, 0, 0},     // Flamethrower, Acrobatics, Dragon Claw
    {0, 0, 0, 0},     // Body Slam, Earthquake, Rest
    {0, 0, 0, 0},      // Dragon Claw, Hurricane, Hyper Beam
    {0, 0, 0, 0},      // Shadow Ball, Sludge Bomb, Destiny Bond
    {0, 0, 0, 0},      // Psychic, Shadow Ball, Focus Blast
    {0, 1, 0, 0},      // Dynamic Punch, Bullet Punch, Knock Off
    {0, 0, 0, 0},      // Waterfall, Ice Beam, Earthquake
    {1, 0, 0, 0},      // Bullet Punch, Close Combat, Swords Dance
    {0, 0, 0, 0},      // Air Slash, Heat Wave, Dragon Cheer
    {0, 0, 0, 0},     // Rock Slide, Knock Off, Low Kick
    {0, 1, 0, 0},     // Psychic, Bullet Punch, Heavy Slam
    {0, 0, 3, 0},      // Body Press, Iron Defense, Wide Guard
    {0, 0, 0, 0},      // Earthquake, Dragon Claw, Stomping Tantrum
    {0, 0, 0, 4},      // Flare Blitz, Close Combat, Swords Dance
    {0, 0, 0, 0},      // Wave Crash, Earthquake, Ice Punch
    {0, 0, 0, 0},      // Leaf Storm, Dragon Pulse, Focus Blast
    {1, 0, 0, 0},      // Sucker Punch, Night Slash, Psycho Cut
    {0, 0, 0, 0},      // Recover, Icy Wind, Scald
    {0, 0, 0, 0},      // Air Slash, Aura Sphere, Dazzling Gleam
    {0, 0, 0, 0},      // Close Combat, Facade, Knock Off
    {0, 0, 0, 0},      // Moonblast, Dazzling Gleam, Psychic
    {0, 1, 0, 0},      // Spore, Mach Punch, Bullet Seed
    {0, 0, 0, 0},      // Eruption, Heat Wave, Solar Beam
    {0, 0, 0, 0},      // Ice Punch, Waterfall, Dragon Dance
    {0, 0, 0, 0},      // Dazzling Gleam, Thunderbolt, Power Gem
    {0, 0, 0, 0},      // Perish Song, Ice Beam, Sheer Cold
    {0, 0, 0, 0}       // Earth Power, Ice Beam, Sludge Bomb
};
  

    static int[] maximumHP = {
        130, // Raichu
        175, // Arcanine
        180, // Blastoise
        155, // Venusaur
        175, // Charizard   
        230, // Snorlax
        191, // Dragonite
        166, // Gengar
        130, // Alakazam
        181, // Machamp
        175, // Gyarados
        150, // Scizor
        170, // Salamence
        200, // Tyranitar
        180, // Metagross
        160, // Steelix
        175, // Garchomp
        150, // Blaziken
        175,// Swampert
        152, // Sceptile
        150, // Absol
        190, // Milotic
        176, // Togekiss
        150, // Heracross
        165, // Gardevoir
        145, // Breloom
        145, // Typhlosion
        172, // Feraligatr
        166, // Ampharos
        210, // Lapras
        160  // Nidoking

    };


    // Each Pokemon has three moves
    static String[][] moveNames = {
        {"Thunder", "Iron Tail", "Thunder Punch", "Volt Switch"},
        {"Flamethrower", "Extreme Speed", "Fire Fang", "Morning Sun"},
        {"Hydro Pump", "Iron Defense", "Steel Cannon", "Ice Beam"},
        {"Giga Drain", "Sludge Bomb", "Poison Powder", "Sleep Powder"},
        {"Flamethrower", "Acrobatics", "Dragon Claw", "Roost"},
        {"Body Slam", "Earthquake", "Rest", "Curse"},
        {"Dragon Claw", "Hurricane", "Hyper Beam", "Drgon Dance"},
        {"Shadow Ball", "Sludge Bomb", "Destiny Bond", "Nasty Plot"},
        {"Psychic", "Shadow Ball", "Focus Blast", "Calm Mind"},
        {"Dynamic Punch", "Bullet Punch", "Knock Off", "Stone Edge"},
        {"Waterfall", "Ice Beam", "Earthquake", "Dragon Dance"},
        {"Bullet Punch", "Close Combat", "Swords Dance", "U-Turn"},
        {"Air Slash", "Heat Wave", "Dragon Cheer", "Dragon Dance"},
        {"Rock Slide", "Knock Off", "Low Kick", "Stealth Rock"},
        {"Psychic", "Bullet Punch", "Heavy Slam", "Earthquake"},
        {"Body Press", "Iron Defense", "Wide Guard", "Stealth Rock"},
        {"Earthquake", "Dragon Claw", "Stomping Tantrum", "Swords Dance"},
        {"Flare Blitz", "Close Combat", "Swords Dance", "Protect"},
        {"Wave Crash", "Earthquake", "Ice Punch", "Stealth Rock"},
        {"Leaf Storm", "Dragon Pulse", "Focus Blast", "LEaf Blade"},
        {"Sucker Punch", "Night Slash", "Psycho Cut", "Swords Dance"},
        {"Recover", "Icy Wind", "Scald", "Haze"},
        {"Air Slash", "Aura Sphere", "Dazzling Gleam", "Thunder Wave"},
        {"Close Combat", "Facade", "Knock Off", "Megahorn"},
        {"Moonblast", "Dazzling Gleam", "Psychic", "Calm Mind"},
        {"Spore", "Mach Punch", "Bullet Seed", "Rock Tomb"},
        {"Eruption", "Heat Wave", "Solar Beam", "Focus Blast"},
        {"Ice Punch", "Waterfall", "Dragon Dance", "Crunch"},
        {"Dazzling Gleam", "Thunderbolt", "Power Gem", "Volt Switch"},
        {"Perish Song", "Ice Beam", "Sheer Cold", "Freeze-Dry"},
        {"Earth Power", "Ice Beam", "Sludge Bomb", "Thunderbolt"}
    
    };

    static String[] abilities = {
    "Lightning Rod", // Raichu
    "Intimidate",    // Arcanine
    "Torrent",       // Blastoise
    "Overgrow",      // Venusaur
    "Blaze",         // Charizard
    "Thick Fat",     // Snorlax
    "Multiscale",    // Dragonite
    "Levitate",      // Gengar
    "Magic Guard",   // Alakazam
    "No Guard",      // Machamp
    "Intimidate",    // Gyarados
    "Technician",    // Scizor
    "Intimidate",    // Salamence
    "Sand Stream",   // Tyranitar
    "Clear Body",    // Metagross
    "Sturdy",        // Steelix
    "Rough Skin",    // Garchomp
    "Speed Boost",   // Blaziken
    "Torrent",       // Swampert
    "Overgrow",      // Sceptile
    "Super Luck",    // Absol
    "Marvel Scale",  // Milotic
    "Serene Grace",  // Togekiss
    "Guts",          // Heracross
    "Trace",         // Gardevoir
    "Technician",    // Breloom
    "Blaze",         // Typhlosion
    "Sheer Force",   // Feraligatr
    "Static",        // Ampharos
    "Water Absorb",  // Lapras
    "Sheer Force"    // Nidoking
};

    static String[][] moveTypes = {
     {"Electric", "Steel", "Electric", "Electric"},     // Raichu
    {"Fire", "Normal", "Fire", "Normal"},            // Arcanine
    {"Water", "Steel", "Steel", "Ice"},           // Blastoise
    {"Grass", "Poison", "Poison", "Grass"},        // Venusaur
    {"Fire", "Flying", "Dragon", "Flying"},         // Charizard
    {"Normal", "Ground", "Normal", "Ghost"},        // Snorlax
    {"Dragon", "Flying", "Normal", "Dragon"},        // Dragonite
    {"Ghost", "Poison", "Ghost", "Dark"},          // Gengar
    {"Psychic", "Ghost", "Fighting", "Psychic"},      // Alakazam
    {"Fighting", "Steel", "Dark", "Rock"},         // Machamp
    {"Water", "Ice", "Ground", "Dragon"},            // Gyarados
    {"Steel", "Fighting", "Normal", "Bug"},       // Scizor
    {"Flying", "Fire", "Dragon", "Dragon"},          // Salamence
    {"Rock", "Dark", "Fighting", "Rock"},          // Tyranitar
    {"Psychic", "Steel", "Steel", "Ground"},         // Metagross
    {"Fighting", "Steel", "Rock", "Rock"},         // Steelix
    {"Ground", "Dragon", "Ground", "Normal"},        // Garchomp
    {"Fire", "Fighting", "Normal", "Normal"},        // Blaziken
    {"Water", "Ground", "Ice", "Rock"},            // Swampert
    {"Grass", "Dragon", "Fighting", "Grass"},       // Sceptile
    {"Dark", "Dark", "Psychic", "Normal"},           // Absol
    {"Normal", "Ice", "Water", "Ice"},            // Milotic
    {"Flying", "Fighting", "Fairy", "Electric"},       // Togekiss
    {"Fighting", "Normal", "Dark", "Bug"},        // Heracross
    {"Fairy", "Fairy", "Psychic", "Psychic"},         // Gardevoir
    {"Grass", "Fighting", "Grass", "Rock"},        // Breloom
    {"Fire", "Fire", "Grass", "Fighting"},             // Typhlosion
    {"Ice", "Water", "Dragon", "Dark"},            // Feraligatr
    {"Fairy", "Electric", "Rock", "Electric"},         // Ampharos
    {"Normal", "Ice", "Ice", "Ice"},              // Lapras
    {"Ground", "Ice", "Poison", "Electric"}            // Nidoking
};

    static int[][] movePower = {
    {110, 100, 75, 70},   // Raichu
    {90, 80, 65, 0},     // Arcanine
    {110, 0, 100, 90},    // Blastoise
    {75, 90, 0, 0},      // Venusaur
    {90, 55, 80, 0},     // Charizard
    {85, 100, 0},     // Snorlax
    {80, 110, 150, 0},   // Dragonite
    {80, 90, 0, 0},      // Gengar
    {90, 80, 120, 0},    // Alakazam
    {100, 40, 65, 100},    // Machamp
    {80, 90, 100, 0},    // Gyarados
    {40, 120, 0, 70},     // Scizor
    {75, 95, 0, 0},      // Salamence
    {75, 65, 0, 0},      // Tyranitar
    {90, 40, 120, 100},    // Metagross
    {80, 0, 0, 0},       // Steelix
    {100, 80, 75, 0},    // Garchomp
    {120, 120, 0, 0},    // Blaziken
    {120, 100, 75, 0},   // Swampert
    {130, 85, 120, 90},   // Sceptile
    {70, 70, 70, 0},     // Absol
    {0, 55, 80, 0},      // Milotic
    {75, 80, 80, 0},     // Togekiss
    {120, 70, 65, 120},    // Heracross
    {95, 80, 90, 0},     // Gardevoir
    {0, 40, 25, 60},      // Breloom
    {150, 95, 120, 120},   // Typhlosion
    {75, 80, 0, 80},      // Feraligatr
    {80, 90, 80, 70},     // Ampharos
    {0, 90, 0, 70},       // Lapras
    {90, 90, 90, 90}      // Nidoking

    };
static int[] speed = {
    100, // Raichu
    95,  // Arcanine
    78,  // Blastoise
    80,  // Venusaur
    100, // Charizard
    30,  // Snorlax
    80,  // Dragonite
    110, // Gengar
    120, // Alakazam
    55,  // Machamp
    81, // Gyarados
    65, // Scizor
    100, // Salamence
    60, // Tyranitar
    70, // Metagross
    50, // Steelix
    102, // Garchomp
    80, // Blaziken
    60, // Swampert
    120, // Sceptile
    75, // Absol
    81, // Milotic
    80, // Togekiss
    85, // Heracross
    80, // Gardevoir
    70, // Breloom
    100, // Typhlosion
    78, // Feraligatr
    55, // Ampharos
    60, // Lapras
    85  // Nidoking
    
};
static int[] attack = {
    90, // Raichu
    110, // Arcanine
    83, // Blastoise
    82, // Venusaur
    84, // Charizard
    110, // Snorlax
    134, // Dragonite
    65, // Gengar
    50, // Alakazam
    130, // Machamp
    125, // Gyarados
    130, // Scizor
    135, // Salamence
    134, // Tyranitar
    135, // Metagross
    75, // Steelix
    130, // Garchomp
    120, // Blaziken
    110, // Swampert
    85, // Sceptile
    130, // Absol
    60, // Milotic
    50, // Togekiss
    125, // Heracross
    65, // Gardevoir
    130, // Breloom
    84, // Typhlosion
    105, // Feraligatr
    75, // Ampharos
    85, // Lapras
    102  // Nidoking
    
};
static int[] defense = {
    60, // Raichu
    80, // Arcanine
    100, // Blastoise
    83, // Venusaur
    78, // Charizard
    65, // Snorlax
    95, // Dragonite
    60, // Gengar
    45, // Alakazam
    80, // Machamp
    79, // Gyarados
    100, // Scizor
    80, // Salamence
    110, // Tyranitar
    130, // Metagross
    150, // Steelix
    95, // Garchomp
    70, // Blaziken
    90, // Swampert
    65, // Sceptile
    60, // Absol
    79, // Milotic
    95, // Togekiss
    75, // Heracross
    65, // Gardevoir
    80, // Breloom
    78, // Typhlosion
    100, // Feraligatr
    85, // Ampharos
    80, // Lapras
    77  // Nidoking

};

    public static void main(String[] args) {

        String playAgain;

        displayTitle();

        do {
            playGame();

            System.out.print("\nWould you like to play again? (yes/no): ");
            playAgain = input.next().toLowerCase();

        } while (playAgain.equals("yes") || playAgain.equals("y"));

        System.out.println("\nThanks for playing!");
        input.close();
    }

    // Runs one full battle
    public static void playGame() {

        // Let the player choose a Pokemon and pick a distinct computer Pokemon
        int[] playerTeam = chooseTeam();
        int[] computerTeam = generateComputerTeam();

int playerActive = 0;
int computerActive = 0;

int playerPokemon = playerTeam[playerActive];
int computerPokemon = computerTeam[computerActive];

        int playerHP = maximumHP[playerPokemon];
        int computerHP = maximumHP[computerPokemon];

        // initialize mutable attack values for this battle
        String playerAbility = abilities[playerPokemon];
        String computerAbility = abilities[computerPokemon];

int playerAttack = attack[playerPokemon];
int computerAttack = attack[computerPokemon];

int playerSpeed = speed[playerPokemon];
int computerSpeed = speed[computerPokemon];

boolean sandstormActive = false;
boolean playerParalyzed = false;
boolean computerParalyzed = false;

int effectivePlayerSpeed =
        playerParalyzed
        ? playerSpeed / 2
        : playerSpeed;

int effectiveComputerSpeed =
        computerParalyzed
        ? computerSpeed / 2
        : computerSpeed;

int playerDefenseStage = 1;
int computerDefenseStage = 1;

String originalPlayerAbility = playerAbility;
String originalComputerAbility = computerAbility;

if (originalPlayerAbility.equals("Trace")) {
    playerAbility = originalComputerAbility;

    System.out.println(
            pokemonNames[playerPokemon]
            + " traced "
            + playerAbility
            + "!"
    );
}


if (originalComputerAbility.equals("Trace")) {
    computerAbility = originalPlayerAbility;

    System.out.println(
            pokemonNames[computerPokemon]
            + " traced "
            + computerAbility
            + "!"
    );
}

if (playerAbility.equals("Intimidate")) {

    if (!computerAbility.equals("Clear Body")) {
        computerAttack = Math.max(1, computerAttack - 20);

        System.out.println(
                pokemonNames[playerPokemon]
                + "'s Intimidate lowered "
                + pokemonNames[computerPokemon]
                + "'s Attack!"
        );
    } else {
        System.out.println(
                pokemonNames[computerPokemon]
                + "'s Clear Body prevented the stat reduction!"
        );
    }
}

if (computerAbility.equals("Intimidate")) {

    if (!playerAbility.equals("Clear Body")) {
        playerAttack = Math.max(1, playerAttack - 20);

        System.out.println(
                pokemonNames[computerPokemon]
                + "'s Intimidate lowered "
                + pokemonNames[playerPokemon]
                + "'s Attack!"
        );
    } else {
        System.out.println(
                pokemonNames[playerPokemon]
                + "'s Clear Body prevented the stat reduction!"
        );
    }
}

if (playerAbility.equals("Sand Stream")
        || computerAbility.equals("Sand Stream")) {

    sandstormActive = true;
    System.out.println("A sandstorm began!");
}

        boolean playerPoisoned = false;
        boolean computerPoisoned = false;
        boolean playerRested = false;
        boolean computerRested = false;
        int playerDefense = 1;
        int computerDefense = 1;

        System.out.println("\nYou chose " + pokemonNames[playerPokemon] + "!");
        System.out.println("The computer chose "
                + pokemonNames[computerPokemon] + "!");

boolean playerGoesFirst;
if (speed[playerPokemon] > speed[computerPokemon]) {

    System.out.println(
        pokemonNames[playerPokemon]
        + " is faster and will attack first!"
    );

    playerGoesFirst = true;

} else if (speed[computerPokemon] > speed[playerPokemon]) {

    System.out.println(
        pokemonNames[computerPokemon]
        + " is faster and will attack first!"
    );

    playerGoesFirst = false;

} else {

    System.out.println(
        "Both Pokemon have the same speed!"
    );
    playerGoesFirst = random.nextBoolean();
}
        // Main battle loop
       while (playerActive < 6 && computerActive < 6) {

            displayBattleInfo(
                    playerPokemon,
                    playerHP,
                    playerPoisoned,
                    playerRested,
                    computerPokemon,
                    computerHP,
                    computerPoisoned,
                    computerRested
            );

           System.out.println("\nWhat would you like to do?");
System.out.println("1. Attack");
System.out.println("2. Switch Pokemon");

int actionChoice = readInt();

if (actionChoice == 2) {
    playerActive = switchPokemon(playerTeam, playerActive, playerPokemon);
    playerPokemon = playerTeam[playerActive];
    playerHP = maximumHP[playerPokemon];

    playerAbility = abilities[playerPokemon];
    playerAttack = attack[playerPokemon];
    playerSpeed = speed[playerPokemon];

    playerPoisoned = false;
    playerRested = false;
    playerDefenseStage = 1;
    playerDefense = 1;

    System.out.println("Go, " + pokemonNames[playerPokemon] + "!");

    int computerMove = chooseComputerMove(
        computerPokemon,
        playerPokemon,
        playerPoisoned
);

System.out.println(
        "\nThe computer chose "
        + moveNames[computerPokemon][computerMove]
);

int computerDamage = performAttack(
        computerPokemon,
        computerMove,
        playerPokemon,
        computerHP,
        playerHP,
        computerAttack,
        defense[playerPokemon] * playerDefenseStage,
        computerPoisoned,
        playerPoisoned,
        computerAbility,
        playerAbility
);

if (computerDamage < 1 && movePower[computerPokemon][computerMove] > 0) {
    computerDamage = 1;
}

playerHP = playerHP - computerDamage;

if (playerHP < 0) {
    playerHP = 0;
}

if (playerHP <= 0) {
    playerActive++;

    if (playerActive < 6) {
        System.out.println(pokemonNames[playerPokemon] + " fainted!");

        playerPokemon = switchPokemon(playerTeam, playerActive, playerPokemon);
        playerHP = maximumHP[playerPokemon];

        playerAbility = abilities[playerPokemon];
        playerAttack = attack[playerPokemon];
        playerSpeed = speed[playerPokemon];

        playerPoisoned = false;
        playerRested = false;
        playerDefenseStage = 1;
        playerDefense = 1;

        System.out.println("Go, " + pokemonNames[playerPokemon] + "!");
    } else {
        break;
    }
}

continue;
}

displayMoves(playerPokemon);
int playerMove = getMoveChoice();

int computerMove = chooseComputerMove(
    computerPokemon,
    playerPokemon,
    playerPoisoned
);

int playerPriority =
        movePriority[playerPokemon][playerMove];

int computerPriority =
        movePriority[computerPokemon][computerMove];

if (playerPriority > computerPriority) {
    playerGoesFirst = true;
} else if (computerPriority > playerPriority) {
    playerGoesFirst = false;
} else if (speed[playerPokemon]
        > speed[computerPokemon]) {
    playerGoesFirst = true;
} else if (speed[computerPokemon]
        > speed[playerPokemon]) {
    playerGoesFirst = false;
} else {
    playerGoesFirst = random.nextBoolean();
}

            if (playerGoesFirst) {
                // Player turn
       int playerDamage = performAttack(
        playerPokemon,
        playerMove,
        computerPokemon,
        playerHP,
        computerHP,
        playerAttack,
        defense[computerPokemon] * computerDefenseStage,
        playerPoisoned,
        computerPoisoned,
        playerAbility,
        computerAbility
);
                    

                // Rest heals Snorlax
                if (playerPokemon == 5 && playerMove == 2) {
                    playerHP = maximumHP[playerPokemon];
                    System.out.println(pokemonNames[playerPokemon] + " restored its HP!");
                    playerRested = true;
                }

                // Shell Smash raises Blastoise's defense
                if (playerPokemon == 2 && playerMove == 1) {
                    playerDefense++;
                    System.out.println(pokemonNames[playerPokemon] + "'s defense rose!");
                }

                if (playerDamage < 1 && movePower[playerPokemon][playerMove] > 0) {
                    playerDamage = 1;
                }

                computerHP = computerHP - playerDamage;

if (computerHP > maximumHP[computerPokemon]) {
    computerHP = maximumHP[computerPokemon];
}

if (computerAbility.equals("Rough Skin")
        && playerDamage > 0) {

    int roughSkinDamage =
            maximumHP[playerPokemon] / 8;

    playerHP -= roughSkinDamage;

    System.out.println(
            pokemonNames[playerPokemon]
            + " was hurt by Rough Skin!"
    );
}

if (computerAbility.equals("Static")
        && playerDamage > 0
        && random.nextInt(100) < 30) {

    playerParalyzed = true;

    System.out.println(
            pokemonNames[playerPokemon]
            + " was paralyzed by Static!"
    );
}

                // Giga Drain heals Venusaur for half the damage it dealt
                if (playerPokemon == 3 && playerMove == 0) {
                    int healAmount = playerDamage / 2;
                    playerHP = playerHP + healAmount;

                    if (playerHP > maximumHP[playerPokemon]) {
                        playerHP = maximumHP[playerPokemon];
                    }

                    System.out.println(
                            pokemonNames[playerPokemon]
                            + " restored "
                            + healAmount
                            + " HP!"
                    );
                }

                if (computerHP < 0) {
                    computerHP = 0;
                }

                // Poison Powder is Venusaur's third move
                if (playerPokemon == 3
                        && playerMove == 2
                        && !computerPoisoned) {

                    computerPoisoned = true;

                    System.out.println(
                            pokemonNames[computerPokemon]
                            + " was poisoned!"
                    );
                }

                // Apply poison damage to the computer
                if (computerPoisoned && computerHP > 0) {

                    int poisonDamage =
                            maximumHP[computerPokemon] / 8;

                    computerHP = computerHP - poisonDamage;

                    if (computerHP < 0) {
                        computerHP = 0;
                    }

                    System.out.println(
                            pokemonNames[computerPokemon]
                            + " lost "
                            + poisonDamage
                            + " HP from poison!"
                    );
                }

                if (computerHP <= 0) {
    computerActive++;

    if (computerActive < 6) {
        computerPokemon = computerTeam[computerActive];
        computerHP = maximumHP[computerPokemon];

        computerAbility = abilities[computerPokemon];
computerAttack = attack[computerPokemon];
computerSpeed = speed[computerPokemon];

computerPoisoned = false;
computerRested = false;

computerDefenseStage = 1;
computerDefense = 1;

        System.out.println(
                pokemonNames[computerPokemon]
                + " was sent out!"
        );
        continue;
    } else {
        break;
    }
}

                // Computer turn

                // Rest heals Snorlax
                if (computerPokemon == 5 && computerMove == 2) {
                    computerHP = maximumHP[computerPokemon];
                    System.out.println(pokemonNames[computerPokemon] + " restored its HP!");
                    computerRested = true;
                }

                System.out.println(
                        "\nThe computer chose "
                        + moveNames[computerPokemon][computerMove]
                );

                int computerDamage = performAttack(
        computerPokemon,
        computerMove,
        playerPokemon,
        computerHP,
        playerHP,
        computerAttack,
        defense[playerPokemon] * playerDefenseStage,
        computerPoisoned,
        playerPoisoned,
        computerAbility,
        playerAbility
);

                if (computerDamage < 1 && movePower[computerPokemon][computerMove] > 0) {
                    computerDamage = 1;
                }

                playerHP = playerHP - computerDamage;

                // Giga Drain heals Venusaur for half the damage it dealt
                if (computerPokemon == 3 && computerMove == 0) {
                    int healAmount = computerDamage / 2;
                    computerHP = computerHP + healAmount;

                    if (computerHP > maximumHP[computerPokemon]) {
                        computerHP = maximumHP[computerPokemon];
                    }

                    System.out.println(
                            pokemonNames[computerPokemon]
                            + " restored "
                            + healAmount
                            + " HP!"
                    );
                }

                if (playerHP < 0) {
                    playerHP = 0;
                }

                if (computerPokemon == 3
                        && computerMove == 2
                        && !playerPoisoned) {

                    playerPoisoned = true;

                    System.out.println(
                            pokemonNames[playerPokemon]
                            + " was poisoned!"
                    );
                }

                // Shell Smash raises Blastoise's defense
                if (computerPokemon == 2 && computerMove == 1) {
                    computerDefense++;
                    System.out.println(
                            pokemonNames[computerPokemon]
                            + "'s defense rose!"
                    );
                }

                // Apply poison damage to the player
               if (playerPoisoned
        && playerHP > 0
        && !playerAbility.equals("Magic Guard")) {

                    int poisonDamage =
                            maximumHP[playerPokemon] / 8;

                    playerHP = playerHP - poisonDamage;

                    if (playerHP < 0) {
                        playerHP = 0;
                    }

                    System.out.println(
                            pokemonNames[playerPokemon]
                            + " lost "
                            + poisonDamage
                            + " HP from poison!"
                    );
                }

if (playerHP <= 0) {
    playerActive++;

    if (playerActive < 6) {
        System.out.println(pokemonNames[playerPokemon] + " fainted!");

        playerPokemon = switchPokemon(playerTeam, playerActive, playerPokemon);
        playerHP = maximumHP[playerPokemon];

        playerAbility = abilities[playerPokemon];
        playerAttack = attack[playerPokemon];
        playerSpeed = speed[playerPokemon];

        playerPoisoned = false;
        playerRested = false;

        playerDefenseStage = 1;
        playerDefense = 1;

        System.out.println(
                "Go, "
                + pokemonNames[playerPokemon]
                + "!"
        );

        continue;
    } else {
        break;
    }
}

            } else {
                // Computer turn

                // Rest heals Snorlax
                if (computerPokemon == 5 && computerMove == 2) {
                    computerHP = maximumHP[computerPokemon];
                    System.out.println(pokemonNames[computerPokemon] + " restored its HP!");
                    computerRested = true;
                }

                System.out.println(
                        "\nThe computer chose "
                        + moveNames[computerPokemon][computerMove]
                );

                int computerDamage = performAttack(
        computerPokemon,
        computerMove,
        playerPokemon,
        computerHP,
        playerHP,
        computerAttack,
        defense[playerPokemon] * playerDefenseStage,
        computerPoisoned,
        playerPoisoned,
        computerAbility,
        playerAbility
);

                if (computerDamage < 1 && movePower[computerPokemon][computerMove] > 0) {
                    computerDamage = 1;
                }

                playerHP = playerHP - computerDamage;

                // Giga Drain heals Venusaur for half the damage it dealt
                if (computerPokemon == 3 && computerMove == 0) {
                    int healAmount = computerDamage / 2;
                    computerHP = computerHP + healAmount;

                    if (computerHP > maximumHP[computerPokemon]) {
                        computerHP = maximumHP[computerPokemon];
                    }

                    System.out.println(
                            pokemonNames[computerPokemon]
                            + " restored "
                            + healAmount
                            + " HP!"
                    );
                }

                if (playerHP < 0) {
                    playerHP = 0;
                }

                if (computerPokemon == 3
                        && computerMove == 2
                        && !playerPoisoned) {

                    playerPoisoned = true;

                    System.out.println(
                            pokemonNames[playerPokemon]
                            + " was poisoned!"
                    );
                }

                // Shell Smash raises Blastoise's defense
                if (computerPokemon == 2 && computerMove == 1) {
                    computerDefense++;
                    System.out.println(
                            pokemonNames[computerPokemon]
                            + "'s defense rose!"
                    );
                }

                // Apply poison damage to the player
                if (playerPoisoned && playerHP > 0) {

                    int poisonDamage =
                            maximumHP[playerPokemon] / 8;

                    playerHP = playerHP - poisonDamage;

                    if (playerHP < 0) {
                        playerHP = 0;
                    }

                    System.out.println(
                            pokemonNames[playerPokemon]
                            + " lost "
                            + poisonDamage
                            + " HP from poison!"
                    );
                }

if (playerHP <= 0) {
    playerActive++;

    if (playerActive < 6) {
        System.out.println(pokemonNames[playerPokemon] + " fainted!");

        playerPokemon = switchPokemon(playerTeam, playerActive, playerPokemon);
        playerHP = maximumHP[playerPokemon];

        playerAbility = abilities[playerPokemon];
        playerAttack = attack[playerPokemon];
        playerSpeed = speed[playerPokemon];

        playerPoisoned = false;
        playerRested = false;

        playerDefenseStage = 1;
        playerDefense = 1;

        System.out.println(
                "Go, "
                + pokemonNames[playerPokemon]
                + "!"
        );

        continue;
    } else {
        break;
    }
}

                // Player turn
                int playerDamage = performAttack(
        playerPokemon,
        playerMove,
        computerPokemon,
        playerHP,
        computerHP,
        playerAttack,
        defense[computerPokemon] * computerDefenseStage,
        playerPoisoned,
        computerPoisoned,
        playerAbility,
        computerAbility
);

                // Rest heals Snorlax
                if (playerPokemon == 5 && playerMove == 2) {
                    playerHP = maximumHP[playerPokemon];
                    System.out.println(pokemonNames[playerPokemon] + " restored its HP!");
                    playerRested = true;
                }

                // Shell Smash raises Blastoise's defense
                if (playerPokemon == 2 && playerMove == 1) {
                    playerDefense++;
                    System.out.println(pokemonNames[playerPokemon] + "'s defense rose!");
                }

                if (playerDamage < 1 && movePower[playerPokemon][playerMove] > 0) {
                    playerDamage = 1;
                }

                computerHP = computerHP - playerDamage;

                // Giga Drain heals Venusaur for half the damage it dealt
                if (playerPokemon == 3 && playerMove == 0) {
                    int healAmount = playerDamage / 2;
                    playerHP = playerHP + healAmount;

                    if (playerHP > maximumHP[playerPokemon]) {
                        playerHP = maximumHP[playerPokemon];
                    }

                    System.out.println(
                            pokemonNames[playerPokemon]
                            + " restored "
                            + healAmount
                            + " HP!"
                    );
                }

                if (computerHP < 0) {
                    computerHP = 0;
                }

                // Poison Powder is Venusaur's third move
                if (playerPokemon == 3
                        && playerMove == 2
                        && !computerPoisoned) {

                    computerPoisoned = true;

                    System.out.println(
                            pokemonNames[computerPokemon]
                            + " was poisoned!"
                    );
                }

                // Apply poison damage to the computer
                if (computerPoisoned && computerHP > 0) {

                    int poisonDamage =
                            maximumHP[computerPokemon] / 8;

                    computerHP = computerHP - poisonDamage;

                    if (computerHP < 0) {
                        computerHP = 0;
                    }

                    System.out.println(
                            pokemonNames[computerPokemon]
                            + " lost "
                            + poisonDamage
                            + " HP from poison!"
                    );
                }

                if (computerHP <= 0) {
    computerActive++;

    if (computerActive < 6) {
        computerPokemon = computerTeam[computerActive];
        computerHP = maximumHP[computerPokemon];

        computerAbility = abilities[computerPokemon];
        computerAttack = attack[computerPokemon];
        computerSpeed = speed[computerPokemon];

        computerPoisoned = false;
        computerRested = false;

        computerDefenseStage = 1;
        computerDefense = 1;

        System.out.println(
                pokemonNames[computerPokemon]
                + " was sent out!"
        );

        continue;
    } else {
        break;
    }
}

                if (playerAbility.equals("Speed Boost")
        && playerHP > 0) {

    playerSpeed = (int) (playerSpeed * 1.5);

    System.out.println(
            pokemonNames[playerPokemon]
            + "'s Speed Boost raised its Speed!"
    );
}

if (computerAbility.equals("Speed Boost")
        && computerHP > 0) {

    computerSpeed = (int) (computerSpeed * 1.5);

    System.out.println(
            pokemonNames[computerPokemon]
            + "'s Speed Boost raised its Speed!"
    );
}
                }

                if (sandstormActive) {

    boolean playerProtected =
            hasType(playerPokemon, "Rock")
            || hasType(playerPokemon, "Ground")
            || hasType(playerPokemon, "Steel")
            || playerAbility.equals("Magic Guard");

    boolean computerProtected =
            hasType(computerPokemon, "Rock")
            || hasType(computerPokemon, "Ground")
            || hasType(computerPokemon, "Steel")
            || computerAbility.equals("Magic Guard");

    if (!playerProtected && playerHP > 0) {
        playerHP -= maximumHP[playerPokemon] / 16;
        System.out.println(
                pokemonNames[playerPokemon]
                + " was hurt by the sandstorm!"
        );
    }

    if (!computerProtected && computerHP > 0) {
        computerHP -= maximumHP[computerPokemon] / 16;
        System.out.println(
                pokemonNames[computerPokemon]
                + " was hurt by the sandstorm!"
        );
    }

if (playerHP < 0) {
    playerHP = 0;
}

if (computerHP < 0) {
    computerHP = 0;
}

}

                }

     displayWinner(playerActive, computerActive);

        }

    public static boolean hasType(
            int pokemon,
            String type) {

        for (String pokemonType : pokemonTypes[pokemon]) {

            if (pokemonType.equals(type)) {
                return true;
            }
        }
        return false;
    }

    public static int switchPokemon(
        int[] playerTeam,
        int playerActive,
        int currentPokemon) {

    int choice;

    System.out.println("\nChoose a Pokemon to switch to:");

    for (int i = 0; i < 6; i++) {
        System.out.println(
                (i + 1) + ". "
                + pokemonNames[playerTeam[i]]
        );
    }

    do {
        System.out.print("Enter a Pokemon number: ");
        choice = readInt() - 1;

        if (choice < 0 || choice >= 6
        || playerTeam[choice] == currentPokemon) {
            System.out.println("That is not a valid switch.");
        }

    } while (choice < 0 || choice >= 6
        || playerTeam[choice] == currentPokemon);

    return choice;
}

    public static int[] chooseTeam() {
    int[] team = new int[6];

    for (int i = 0; i < 6; i++) {
        System.out.println("\nChoose Pokemon #" + (i + 1));
        team[i] = choosePokemon();
    }

    return team;
}

public static int[] generateComputerTeam() {
    int[] team = new int[6];

    for (int i = 0; i < 6; i++) {
        team[i] = random.nextInt(pokemonNames.length);
    }

    return team;
}

    // Displays the title and instructions
    public static void displayTitle() {

        System.out.println("================================");
        System.out.println("     Project PokeSimulator");
        System.out.println("================================");
        System.out.println("Choose a Pokemon and defeat");
        System.out.println("the computer-controlled Pokemon.");
        System.out.println("Type advantages affect damage.");
        System.out.println("Poison removes HP each turn.\n");
    }

    // Lets the player choose a Pokemon
    public static int choosePokemon() {

        System.out.println("Choose your Pokemon:");

        for (int i = 0; i < pokemonNames.length; i++) {

            System.out.println(
                    (i + 1) + ". "
                    + pokemonNames[i]
                    + " | Type: "
                    + Arrays.toString(pokemonTypes[i])
                    + " | HP: "
                    + maximumHP[i]
            );
        }

        int choice;

        do {
            System.out.print("Enter a number from 1 to " + pokemonNames.length + ": ");
            choice = readInt();

            if (choice < 1 || choice > pokemonNames.length) {
                System.out.println("That is not a valid choice.");
            }

        } while (choice < 1 || choice > pokemonNames.length);

        return choice - 1;
    }

    // Displays both Pokemon and their current HP
        public static void displayBattleInfo(
        int playerPokemon,
        int playerHP,
        boolean playerPoisoned,
        boolean playerRested,
        int computerPokemon,
        int computerHP,
        boolean computerPoisoned,
        boolean computerRested) {

        String playerStatus = "Normal";
        String computerStatus = "Normal";

        if (playerPoisoned) {
            playerStatus = "Poisoned";
        } else if (playerRested) {
            playerStatus = "Rested";
        }

        if (computerPoisoned) {
            computerStatus = "Poisoned";
        } else if (computerRested) {
            computerStatus = "Rested";
        }

        System.out.println("\n-------------------------------");

        System.out.println(
                "Your " + pokemonNames[playerPokemon]
                + ": " + playerHP
                + "/" + maximumHP[playerPokemon]
                + " HP | " + playerStatus
        );

        System.out.println(
                "Computer's " + pokemonNames[computerPokemon]
                + ": " + computerHP
                + "/" + maximumHP[computerPokemon]
                + " HP | " + computerStatus
        );

        System.out.println("-------------------------------");
    }

    // Displays the player's available moves
    public static void displayMoves(int pokemon) {

        System.out.println("\nChoose a move:");

        for (int i = 0; i < moveNames[pokemon].length; i++) {

            System.out.println(
                    (i + 1) + ". "
                    + moveNames[pokemon][i]
                    + " | Type: "
                    + moveTypes[pokemon][i]
                    + " | Power: "
                    + movePower[pokemon][i]
            );
        }
    }

    // Gets a valid move choice
    public static int getMoveChoice() {

        int choice;

        do {
            System.out.print("Enter a number from 1 to " + moveNames[0].length + ": ");
            choice = readInt();

            if (choice < 1 || choice > moveNames[0].length) {
                System.out.println("That is not a valid move.");
            }

        } while (choice < 1 || choice > moveNames[0].length);

        return choice - 1;
    }

    // Performs an attack and returns the damage
    public static int performAttack(
            int attacker,
            int selectedMove,
            int defender,
            int attackerHP,
            int defenderHP,
            int attackerAttack,
            int defenderDefense,
            boolean attackerPoisoned,
            boolean defenderPoisoned,
            String attackerAbility,
            String defenderAbility) {

                boolean noGuardActive =
        attackerAbility.equals("No Guard")
        || defenderAbility.equals("No Guard");

if (!noGuardActive) {

    int roll = random.nextInt(100) + 1;

    if (roll > moveAccuracy[attacker][selectedMove]) {

        System.out.println(
                pokemonNames[attacker]
                + " used "
                + moveNames[attacker][selectedMove]
                + ", but it missed!"
        );

        return 0;
    }
}

                int roll = random.nextInt(100) + 1;

                String moveType = moveTypes[attacker][selectedMove];

                if (roll > moveAccuracy[attacker][selectedMove]) {

                    System.out.println(
                            moveNames[attacker][selectedMove]
                            + " missed!"
                    );

                    return 0;
                }

                if (defenderAbility.equals("Levitate")
        && moveType.equals("Ground")) {

    System.out.println(
            pokemonNames[defender]
            + "'s Levitate made it immune!"
    );

    return 0;
}

if (defenderAbility.equals("Lightning Rod")
        && moveType.equals("Electric")) {

    System.out.println(
            pokemonNames[defender]
            + " absorbed the Electric move with Lightning Rod!"
    );

    return 0;
}

if (defenderAbility.equals("Water Absorb")
        && moveType.equals("Water")) {

    int healing = maximumHP[defender] / 4;

    System.out.println(
            pokemonNames[defender]
            + " absorbed the Water move and restored "
            + healing
            + " HP!"
    );

    // Negative damage heals when the caller subtracts it.
    return -healing;
}

        String selectedMoveName =
                moveNames[attacker][selectedMove];
        String selectedMoveType =
                moveTypes[attacker][selectedMove];

            // move name and power
            int power =
                movePower[attacker][selectedMove];

                if (attackerAbility.equals("Technician")
        && power > 0
        && power <= 60) {

    power = (int) (power * 1.5);
}

if (attackerHP <= maximumHP[attacker] / 3) {

    if (attackerAbility.equals("Blaze")
            && moveType.equals("Fire")) {

        power = (int) (power * 1.5);
        System.out.println("Blaze strengthened the move!");
    }

    if (attackerAbility.equals("Torrent")
            && moveType.equals("Water")) {

        power = (int) (power * 1.5);
        System.out.println("Torrent strengthened the move!");
    }

    if (attackerAbility.equals("Overgrow")
            && moveType.equals("Grass")) {

        power = (int) (power * 1.5);
        System.out.println("Overgrow strengthened the move!");
    }
}

double effectiveAttack = attackerAttack;

if (attackerAbility.equals("Guts")
        && attackerPoisoned) {

    effectiveAttack *= 1.5;
}

        System.out.println(
                "\n" + pokemonNames[attacker]
                + " used "
                + selectedMoveName
                + "!"
        );

        if (power == 0) {

            System.out.println(
                    "The move did no direct damage."
            );

            return 0;
        }

        double typeMultiplier = getTypeMultiplier(
                moveType,
                pokemonTypes[defender]
        );

double effectiveDefense = defenderDefense;

if (defenderAbility.equals("Marvel Scale")
        && defenderPoisoned) {

    effectiveDefense *= 1.5;
}

int randomDamage = random.nextInt(5);

int damage = (int) (
        power
        + effectiveAttack / 2.0
        - effectiveDefense / 3.0
);

damage = (int) (damage * typeMultiplier)
        + randomDamage;

if (damage < 1 && typeMultiplier > 0) {
    damage = 1;
}

                // 10% chance for a critical hit
int criticalChance = 10;

if (attackerAbility.equals("Super Luck")) {
    criticalChance = 20;
}

if (random.nextInt(100) < criticalChance) {

    damage *= 2;
    System.out.println("A critical hit!");
}

if (defenderAbility.equals("Thick Fat")
        && (moveType.equals("Fire")
        || moveType.equals("Ice"))) {

    damage /= 2;
    System.out.println("Thick Fat reduced the damage!");
}

if (defenderAbility.equals("Multiscale")
        && defenderHP == maximumHP[defender]) {

    damage /= 2;
    System.out.println("Multiscale reduced the damage!");
}

if (defenderAbility.equals("Sturdy")
        && defenderHP == maximumHP[defender]
        && damage >= defenderHP) {

    damage = defenderHP - 1;

    System.out.println(
            pokemonNames[defender]
            + " held on because of Sturdy!"
    );
}


        if (typeMultiplier == 2.0) {

            System.out.println(
                    "It was super effective!"
            );

        } else if (typeMultiplier == 0.5) {

            System.out.println(
                    "It was not very effective."
            );
        }

        System.out.println(
                pokemonNames[defender]
                + " lost "
                + damage
                + " HP!"
        );

        return damage;
    }

    // Checks type advantages and disadvantages
    /**
     * @param attackType
     * @param defenderTypes
     * @return
     */
    public static double getTypeMultiplier(
            String attackType,
            String[] defenderTypes) {

        double multiplier = 1.0;

        for (String defenderType : defenderTypes) {

            // Super effective attacks
            if (attackType.equals("Fire")) {

                if (defenderType.equals("Grass")
                        || defenderType.equals("Ice")
                        || defenderType.equals("Bug")
                        || defenderType.equals("Steel")) {

                    multiplier *= 2.0;
                }
            }
            if (attackType.equals("Water")) {

                if (defenderType.equals("Fire")
                        || defenderType.equals("Rock")
                        || defenderType.equals("Ground")) {

                    multiplier *= 2.0;
                }
            }
            if (attackType.equals("Grass")) {

                if (defenderType.equals("Water")
                        || defenderType.equals("Rock")
                        || defenderType.equals("Ground")) {

                    multiplier *= 2.0;
                }
            }
            if (attackType.equals("Electric")) {

                if (defenderType.equals("Water")
                        || defenderType.equals("Flying")) {

                    multiplier *= 2.0;
                }
            }
            if (attackType.equals("Ice")) {

                if (defenderType.equals("Ground")
                        || defenderType.equals("Grass")
                        || defenderType.equals("Dragon")
                        || defenderType.equals("Bug")) {

                    multiplier *= 2.0;
                }
            }
            if (attackType.equals("Fighting")) {

                if (defenderType.equals("Normal")
                        || defenderType.equals("Rock")
                        || defenderType.equals("Steel")
                        || defenderType.equals("Ice")
                        || defenderType.equals("Dark")) {

                    multiplier *= 2.0;
                }
            }
            if (attackType.equals("Ground")) {
    
                    if (defenderType.equals("Fire")
                            || defenderType.equals("Electric")
                            || defenderType.equals("Poison")
                            || defenderType.equals("Rock")
                            || defenderType.equals("Steel")) {
    
                        multiplier *= 2.0;
                    }
                }
                if (attackType.equals("Flying")) {
    
                    if (defenderType.equals("Grass")
                            || defenderType.equals("Fighting")
                            || defenderType.equals("Bug")) {
    
                        multiplier *= 2.0;
                    }
                }
                if (attackType.equals("Psychic")) {
    
                    if (defenderType.equals("Fighting")
                            || defenderType.equals("Poison")) {
    
                        multiplier *= 2.0;
                    }
                }
                if (attackType.equals("Bug")) {
    
                    if (defenderType.equals("Grass")
                            || defenderType.equals("Psychic")
                            || defenderType.equals("Dark")) {
    
                        multiplier *= 2.0;
                    }
                }
                if (attackType.equals("Rock")) {
    
                    if (defenderType.equals("Fire")
                            || defenderType.equals("Ice")
                            || defenderType.equals("Flying")
                            || defenderType.equals("Bug")) {
    
                        multiplier *= 2.0;
                    }
                }
                if (attackType.equals("Ghost")) {
    
                    if (defenderType.equals("Psychic")
                            || defenderType.equals("Ghost")) {
    
                        multiplier *= 2.0;
                    }
                }
                if (attackType.equals("Dragon")) {
    
                    if (defenderType.equals("Dragon")) {
    
                        multiplier *= 2.0;
                    }
                }
                if (attackType.equals("Dark")) {
    
                    if (defenderType.equals("Psychic")
                            || defenderType.equals("Ghost")) {
    
                        multiplier *= 2.0;
                    }
                }
                if (attackType.equals("Steel")) {
    
                    if (defenderType.equals("Rock")
                            || defenderType.equals("Ice")
                            || defenderType.equals("Fairy")) {
    
                        multiplier *= 2.0;
                    }
                }
                if (attackType.equals("Fairy")) {
    
                    if (defenderType.equals("Fighting")
                            || defenderType.equals("Dragon")
                            || defenderType.equals("Dark")) {
    
                        multiplier *= 2.0;
                    }
                }
                

            // Not very effective attacks
            if (attackType.equals("Fire")) {

                if (defenderType.equals("Fire")
                        || defenderType.equals("Water")
                        || defenderType.equals("Rock")
                        || defenderType.equals("Dragon")) {

                    multiplier *= 0.5;
                }
            }
            if (attackType.equals("Water")) {

                if (defenderType.equals("Water")
                        || defenderType.equals("Grass")
                        || defenderType.equals("Dragon")) {

                    multiplier *= 0.5;
                }
            }
            if (attackType.equals("Grass")) {

                if (defenderType.equals("Fire")
                        || defenderType.equals("Grass")
                        || defenderType.equals("Poison")
                        || defenderType.equals("Flying")
                        || defenderType.equals("Bug")
                        || defenderType.equals("Dragon")
                        || defenderType.equals("Steel")) {

                    multiplier *= 0.5;
                }
            }
            if (attackType.equals("Ice")) {

                if (defenderType.equals("Fire")
                        || defenderType.equals("Water")
                        || defenderType.equals("Ice")
                        || defenderType.equals("Steel")) {

                    multiplier *= 0.5;
                }
            }
            if (attackType.equals("Bug")) {

                if (defenderType.equals("Fire")
                        || defenderType.equals("Fighting")
                        || defenderType.equals("Poison")
                        || defenderType.equals("Flying")
                        || defenderType.equals("Ghost")
                        || defenderType.equals("Steel")
                        || defenderType.equals("Fairy")) {

                    multiplier *= 0.5;
                }
            }
            if (attackType.equals("Electric")) {

                if (defenderType.equals("Electric")
                        || defenderType.equals("Grass")
                        || defenderType.equals("Dragon")) {

                    multiplier *= 0.5;
                }
            }
            if (attackType.equals("Psychic")) {

                if (defenderType.equals("Psychic")
                        || defenderType.equals("Steel")) {

                    multiplier *= 0.5;
                }
            }
            if (attackType.equals("Rock")) {

                if (defenderType.equals("Fighting")
                        || defenderType.equals("Ground")
                        || defenderType.equals("Steel")) {

                    multiplier *= 0.5;
                }
            }
            if (attackType.equals("Ghost")) {

                if (defenderType.equals("Normal")
                        || defenderType.equals("Dark")) {

                    multiplier *= 0.5;
                }
            }
            if (attackType.equals("Dragon")) {

                if (defenderType.equals("Steel")) {

                    multiplier *= 0.5;
                }
            }
            if (attackType.equals("Dark")) {

                if (defenderType.equals("Fighting")
                        || defenderType.equals("Dark")
                        || defenderType.equals("Fairy")) {

                    multiplier *= 0.5;
                }
            }
            if (attackType.equals("Steel")) {

                if (defenderType.equals("Fire")
                        || defenderType.equals("Water")
                        || defenderType.equals("Electric")
                        || defenderType.equals("Steel")) {

                    multiplier *= 0.5;
                }
            }
            if (attackType.equals("Fairy")) {

                if (defenderType.equals("Fire")
                        || defenderType.equals("Poison")
                        || defenderType.equals("Steel")) {

                    multiplier *= 0.5;
                }
            }
            if (attackType.equals("Flying")) {

                if (defenderType.equals("Ground")
                        || defenderType.equals("Grass")
                        || defenderType.equals("Bug")) {

                    multiplier *= 0.5;
                }
            }
            // No effect attacks
            if (attackType.equals("Normal")) {

                if (defenderType.equals("Ghost")) {

                    multiplier *= 0.0;
                }
            }
            if (attackType.equals("Fighting")) {

                if(defenderType.equals("Ghost")) {

                    multiplier *= 0.0;
                }
            }
            if (attackType.equals("Poison")) {

                if (defenderType.equals("Steel")) {

                     multiplier *= 0.0;
                }
            }
            if (attackType.equals("Ground")) {

                if (defenderType.equals("Flying")) {

                    multiplier *=0.0;
                }
            }
            if (attackType.equals("Psychic")) {

                if (defenderType.equals("Dark")) {

                    multiplier *=0.0;
                }
            }
            if (attackType.equals("Ghost")) {

                if (defenderType.equals("Normal")) {

                    multiplier *=0.0;
                }
            }
            if (attackType.equals("Electric")) {

                if (defenderType.equals("Ground")) {

                    multiplier *=0.0;
                }
            }
            if (attackType.equals("Dragon")) {

                if (defenderType.equals("Fairy")) {

                    multiplier *=0.0;
                }
            }

        }

        return multiplier;
    }

    // Chooses the computer's best move
    public static int chooseComputerMove(
            int computerPokemon,
            int playerPokemon,
            boolean playerPoisoned) {

        int bestMove = 0;
        int highestScore = -1;

        for (int i = 0;
                i < moveNames[computerPokemon].length;
                i++) {

            double multiplier = getTypeMultiplier(
                    moveTypes[computerPokemon][i],
                    pokemonTypes[playerPokemon]
            );

            int score =
                    (int) (movePower[computerPokemon][i]
                    * multiplier);

            // Give Poison Powder value if the player is not poisoned
            if (computerPokemon == 3
                    && i == 2
                    && !playerPoisoned) {

                score = 25;
            }

            // Adds a small amount of randomness
            score = score + random.nextInt(4);

            if (score > highestScore) {

                highestScore = score;
                bestMove = i;
            }
        }

        return bestMove;
    }

    // Displays the result of the battle
   public static void displayWinner(int playerActive, int computerActive) {

    System.out.println("\n================================");

    if (computerActive >= 6) {
        System.out.println("You won the battle!");
    } else if (playerActive >= 6) {
        System.out.println("The computer won the battle.");
    }

    System.out.println("================================");
}

    // Prevents the program from crashing on non-number input
    public static int readInt() {

        while (!input.hasNextInt()) {

            System.out.print(
                    "Please enter a whole number: "
            );

            input.next();
        }

        return input.nextInt();
    }
}