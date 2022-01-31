package dad.pokemonfx.batalla;

public class TablaTipos {
    static float[][] tablaTipos =
    {   //                   NOR  FIR  WAT  ELE  GRA  ICE  FIG  POI  GRO  FLY  PSY  BUG  ROC  GHO  DRA  DAR  STE 
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

    public static float GetEfectividad(String tipoAtaque, String tipoDefensa)
    {
        if(tipoAtaque == "None" || tipoDefensa == "None")
        {
            return 1;
        }
        int row = Integer.parseInt(tipoAtaque) - 1;
        int col = Integer.parseInt(tipoDefensa) - 1;

        return tablaTipos[row][col];
    }
}
