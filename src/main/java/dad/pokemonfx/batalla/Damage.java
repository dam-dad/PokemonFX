package dad.pokemonfx.batalla;

import java.util.Arrays;
import java.util.List;

public class Damage {
	
    private static final List<PokemonType> POKEMON_TYPES = Arrays.asList(PokemonType.values());

    private static final float[][] DAMAGE_MULTIPLIER = {
        //                   NOR  FIR  WAT  ELE  GRA  ICE  FIG  POI  GRO  FLY  PSY  BUG  ROC  GHO  DRA  DAR  STE 
        /*NOR*/new float[] { 1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  0.5f,0f,  1f,  1f,  0.5f},
        /*FUE*/new float[] { 1f, 0.5f, 0.5f,1f,  2f,  2f,  1f,  1f,  1f,  1f,  1f,  2f,  0.5f,1f,  0.5f,1f,  2f  },
        /*AGU*/new float[] { 1f,  2f,  0.5f,1f,  0.5f,1f,  1f,  1f,  2f,  1f,  1f,  1f,  2f,  1f,  0.5f,1f,  1f  },
        /*ELE*/new float[] { 1f,  1f,  2f,  0.5f,0.5f,1f,  1f,  1f,  0f,  2f,  1f,  1f,  1f,  1f,  0.5f,1f,  1f  },
        /*GRA*/new float[] { 1f,  0.5f,2f,  1f,  0.5f,1f,  1f,  0.5f,2f,  0.5f,1f,  0.5f,2f,  1f,  0.5f,1f,  0.5f},
        /*ICE*/new float[] { 1f,  0.5f,0.5f,1f,  2f,  0.5f,1f,  1f,  2f,  2f,  1f,  1f,  1f,  1f,  2f,  1f,  0.5f},
        /*FIG*/new float[] { 2f,  1f,  1f,  1f,  1f,  2f,  1f,  0.5f,1f,  0.5f,0.5f,0.5f,2f,  0f,  1f,  2f,  2f  },
        /*POI*/new float[] { 1f,  1f,  1f,  1f,  2f,  1f,  1f,  0.5f,0.5f,1f,  1f,  1f,  0.5f,0.5f,1f,  1f,  0f  },
        /*GRO*/new float[] { 1f,  2f,  1f,  2f,  0.5f,1f,  1f,  2f,  1f,  0f,  1f,  0.5f,2f,  1f,  1f,  1f,  2f  },
        /*FLY*/new float[] { 1f,  1f,  1f,  0.5f,1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  0.5f,1f,  1f,  1f,  0.5f},
        /*PSY*/new float[] { 1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  0.5f,1f,  1f,  1f,  1f,  1f,  0.5f},
        /*BUG*/new float[] { 1f,  0.5f,1f,  1f,  1f,  1f,  0.5f,0.5f,1f,  0.5f,1f,  1f,  1f,  0.5f,1f,  1f,  0.5f},
        /*ROC*/new float[] { 1f,  1f,  1f,  1f,  1f,  1f,  0.5f,1f,  0.5f,1f,  1f,  1f,  1f,  1f,  1f,  1f,  0.5f},
        /*GHO*/new float[] { 1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  0.5f,1f, },
        /*DRA*/new float[] { 1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  0.5f},
        /*DAR*/new float[] { 1f,  1f,  1f,  1f,  1f,  1f,  0.5f,1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  0.5f,1f, },
        /*STE*/new float[] { 1f,  0.5f,0.5f,0.5f,1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  1f,  0.5f},
    };

    public static double getEffectivity(PokemonType tipoAtaque, PokemonType tipoDefensa) {
        int row = POKEMON_TYPES.indexOf(tipoAtaque);
        int col = POKEMON_TYPES.indexOf(tipoDefensa);
        return DAMAGE_MULTIPLIER[row][col];        
    }
    
    
}
