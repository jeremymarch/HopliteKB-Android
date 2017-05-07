//
//  accent.c
//
//  Created by Jeremy on 4/15/15.
//  Copyright (c) 2015 Jeremy March. All rights reserved.
//

//#include <stdlib.h> // For random(), RAND_MAX
//#include <string.h>  //for strlen()
#include "GreekUnicode.h"
#include "accent.h"

#define ALLOW_PRIVATE_USE_AREA 1

#define MAX_COMBINING 5 //macron, breathing, accent, iota subscript || diaeresis, macron, accent

char unicode_mode = PRECOMPOSED_MODE; //set default

#define NUM_COMBINING_ACCENTS 8
//this is the order they will be added to a vowel
unsigned short combiningAccents[NUM_COMBINING_ACCENTS] = { COMBINING_MACRON, COMBINING_DIAERESIS, COMBINING_ROUGH_BREATHING, COMBINING_SMOOTH_BREATHING, COMBINING_ACUTE, COMBINING_GRAVE, COMBINING_CIRCUMFLEX                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        , COMBINING_IOTA_SUBSCRIPT };

//precomposed indices
enum {
    NORMAL,
    PSILI,                                  //smooth
    DASIA,                                  //rough
    OXIA,
    PSILI_AND_OXIA,
    DASIA_AND_OXIA,
    VARIA,
    PSILI_AND_VARIA,
    DASIA_AND_VARIA,
    PERISPOMENI,
    PSILI_AND_PERISPOMENI,
    DASIA_AND_PERISPOMENI,
    YPOGEGRAMMENI,
    PSILI_AND_YPOGEGRAMMENI,
    DASIA_AND_YPOGEGRAMMENI,
    OXIA_AND_YPOGEGRAMMENI,
    PSILI_AND_OXIA_AND_YPOGEGRAMMENI,
    DASIA_AND_OXIA_AND_YPOGEGRAMMENI,
    VARIA_AND_YPOGEGRAMMENI,
    PSILI_AND_VARIA_AND_YPOGEGRAMMENI,
    DASIA_AND_VARIA_AND_YPOGEGRAMMENI,
    PERISPOMENI_AND_YPOGEGRAMMENI,
    PSILI_AND_PERISPOMENI_AND_YPOGEGRAMMENI,
    DASIA_AND_PERISPOMENI_AND_YPOGEGRAMMENI,
    DIALYTIKA,
    DIALYTIKA_AND_OXIA,
    DIALYTIKA_AND_VARIA,
    DIALYTIKA_AND_PERISPOMENON,
    MACRON_PRECOMPOSED,
#ifdef ALLOW_PRIVATE_USE_AREA
    MACRON_AND_SMOOTH,
    MACRON_AND_SMOOTH_AND_ACUTE,
    MACRON_AND_SMOOTH_AND_GRAVE,
    MACRON_AND_ROUGH,
    MACRON_AND_ROUGH_AND_ACUTE,
    MACRON_AND_ROUGH_AND_GRAVE,
    MACRON_AND_ACUTE,
    MACRON_AND_GRAVE,
#endif
    NUM_ACCENT_CODES
};

//letterCodes
enum {
    ALPHA,
    EPSILON,
    ETA,
    IOTA,
    OMICRON,
    UPSILON,
    OMEGA,
    ALPHA_CAP,
    EPSILON_CAP,
    ETA_CAP,
    IOTA_CAP,
    OMICRON_CAP,
    UPSILON_CAP,
    OMEGA_CAP,
    NUM_VOWEL_CODES
};

unsigned short letters[NUM_VOWEL_CODES][NUM_ACCENT_CODES] = {
    { GREEK_SMALL_LETTER_ALPHA, GREEK_SMALL_LETTER_ALPHA_WITH_PSILI, GREEK_SMALL_LETTER_ALPHA_WITH_DASIA, GREEK_SMALL_LETTER_ALPHA_WITH_OXIA, GREEK_SMALL_LETTER_ALPHA_WITH_PSILI_AND_OXIA, GREEK_SMALL_LETTER_ALPHA_WITH_DASIA_AND_OXIA, GREEK_SMALL_LETTER_ALPHA_WITH_VARIA, GREEK_SMALL_LETTER_ALPHA_WITH_PSILI_AND_VARIA, GREEK_SMALL_LETTER_ALPHA_WITH_DASIA_AND_VARIA, GREEK_SMALL_LETTER_ALPHA_WITH_PERISPOMENI, GREEK_SMALL_LETTER_ALPHA_WITH_PSILI_AND_PERISPOMENI, GREEK_SMALL_LETTER_ALPHA_WITH_DASIA_AND_PERISPOMENI, GREEK_SMALL_LETTER_ALPHA_WITH_YPOGEGRAMMENI, GREEK_SMALL_LETTER_ALPHA_WITH_PSILI_AND_YPOGEGRAMMENI, GREEK_SMALL_LETTER_ALPHA_WITH_DASIA_AND_YPOGEGRAMMENI, GREEK_SMALL_LETTER_ALPHA_WITH_OXIA_AND_YPOGEGRAMMENI, GREEK_SMALL_LETTER_ALPHA_WITH_PSILI_AND_OXIA_AND_YPOGEGRAMMENI, GREEK_SMALL_LETTER_ALPHA_WITH_DASIA_AND_OXIA_AND_YPOGEGRAMMENI, GREEK_SMALL_LETTER_ALPHA_WITH_VARIA_AND_YPOGEGRAMMENI, GREEK_SMALL_LETTER_ALPHA_WITH_PSILI_AND_VARIA_AND_YPOGEGRAMMENI, GREEK_SMALL_LETTER_ALPHA_WITH_DASIA_AND_VARIA_AND_YPOGEGRAMMENI, GREEK_SMALL_LETTER_ALPHA_WITH_PERISPOMENI_AND_YPOGEGRAMMENI, GREEK_SMALL_LETTER_ALPHA_WITH_PSILI_AND_PERISPOMENI_AND_YPOGEGRAMMENI, GREEK_SMALL_LETTER_ALPHA_WITH_DASIA_AND_PERISPOMENI_AND_YPOGEGRAMMENI, 0, 0, 0, 0,
        GREEK_SMALL_LETTER_ALPHA_WITH_MACRON
#ifdef ALLOW_PRIVATE_USE_AREA
        , PUA_GREEK_SMALL_LETTER_ALPHA_WITH_PSILI_AND_MACRON,
        PUA_GREEK_SMALL_LETTER_ALPHA_WITH_PSILI_AND_OXIA_AND_MACRON,
        PUA_GREEK_SMALL_LETTER_ALPHA_WITH_PSILI_AND_VARIA_AND_MACRON,
        PUA_GREEK_SMALL_LETTER_ALPHA_WITH_DASIA_AND_MACRON,
        PUA_GREEK_SMALL_LETTER_ALPHA_WITH_DASIA_AND_OXIA_AND_MACRON2,
        PUA_GREEK_SMALL_LETTER_ALPHA_WITH_DASIA_AND_VARIA_AND_MACRON,
        PUA_GREEK_SMALL_LETTER_ALPHA_WITH_OXIA_AND_MACRON,
        PUA_GREEK_SMALL_LETTER_ALPHA_WITH_VARIA_AND_MACRON
#endif
    },
    
    { GREEK_SMALL_LETTER_EPSILON, GREEK_SMALL_LETTER_EPSILON_WITH_PSILI, GREEK_SMALL_LETTER_EPSILON_WITH_DASIA, GREEK_SMALL_LETTER_EPSILON_WITH_OXIA, GREEK_SMALL_LETTER_EPSILON_WITH_PSILI_AND_OXIA, GREEK_SMALL_LETTER_EPSILON_WITH_DASIA_AND_OXIA, GREEK_SMALL_LETTER_EPSILON_WITH_VARIA, GREEK_SMALL_LETTER_EPSILON_WITH_PSILI_AND_VARIA, GREEK_SMALL_LETTER_EPSILON_WITH_DASIA_AND_VARIA, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
#ifdef ALLOW_PRIVATE_USE_AREA
        , 0, 0, 0, 0, 0, 0, 0, 0
#endif
    },
    
    { GREEK_SMALL_LETTER_ETA, GREEK_SMALL_LETTER_ETA_WITH_PSILI, GREEK_SMALL_LETTER_ETA_WITH_DASIA, GREEK_SMALL_LETTER_ETA_WITH_OXIA, GREEK_SMALL_LETTER_ETA_WITH_PSILI_AND_OXIA, GREEK_SMALL_LETTER_ETA_WITH_DASIA_AND_OXIA, GREEK_SMALL_LETTER_ETA_WITH_VARIA, GREEK_SMALL_LETTER_ETA_WITH_PSILI_AND_VARIA, GREEK_SMALL_LETTER_ETA_WITH_DASIA_AND_VARIA, GREEK_SMALL_LETTER_ETA_WITH_PERISPOMENI, GREEK_SMALL_LETTER_ETA_WITH_PSILI_AND_PERISPOMENI, GREEK_SMALL_LETTER_ETA_WITH_DASIA_AND_PERISPOMENI, GREEK_SMALL_LETTER_ETA_WITH_YPOGEGRAMMENI, GREEK_SMALL_LETTER_ETA_WITH_PSILI_AND_YPOGEGRAMMENI, GREEK_SMALL_LETTER_ETA_WITH_DASIA_AND_YPOGEGRAMMENI, GREEK_SMALL_LETTER_ETA_WITH_OXIA_AND_YPOGEGRAMMENI, GREEK_SMALL_LETTER_ETA_WITH_PSILI_AND_OXIA_AND_YPOGEGRAMMENI, GREEK_SMALL_LETTER_ETA_WITH_DASIA_AND_OXIA_AND_YPOGEGRAMMENI, GREEK_SMALL_LETTER_ETA_WITH_VARIA_AND_YPOGEGRAMMENI, GREEK_SMALL_LETTER_ETA_WITH_PSILI_AND_VARIA_AND_YPOGEGRAMMENI, GREEK_SMALL_LETTER_ETA_WITH_DASIA_AND_VARIA_AND_YPOGEGRAMMENI, GREEK_SMALL_LETTER_ETA_WITH_PERISPOMENI_AND_YPOGEGRAMMENI, GREEK_SMALL_LETTER_ETA_WITH_PSILI_AND_PERISPOMENI_AND_YPOGEGRAMMENI, GREEK_SMALL_LETTER_ETA_WITH_DASIA_AND_PERISPOMENI_AND_YPOGEGRAMMENI, 0, 0, 0, 0, 0
#ifdef ALLOW_PRIVATE_USE_AREA
        , 0, 0, 0, 0, 0, 0, 0, 0
#endif
    },
    
    { GREEK_SMALL_LETTER_IOTA, GREEK_SMALL_LETTER_IOTA_WITH_PSILI, GREEK_SMALL_LETTER_IOTA_WITH_DASIA, GREEK_SMALL_LETTER_IOTA_WITH_OXIA, GREEK_SMALL_LETTER_IOTA_WITH_PSILI_AND_OXIA, GREEK_SMALL_LETTER_IOTA_WITH_DASIA_AND_OXIA, GREEK_SMALL_LETTER_IOTA_WITH_VARIA, GREEK_SMALL_LETTER_IOTA_WITH_PSILI_AND_VARIA, GREEK_SMALL_LETTER_IOTA_WITH_DASIA_AND_VARIA, GREEK_SMALL_LETTER_IOTA_WITH_PERISPOMENI, GREEK_SMALL_LETTER_IOTA_WITH_PSILI_AND_PERISPOMENI, GREEK_SMALL_LETTER_IOTA_WITH_DASIA_AND_PERISPOMENI, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, GREEK_SMALL_LETTER_IOTA_WITH_DIALYTIKA, GREEK_SMALL_LETTER_IOTA_WITH_DIALYTIKA_AND_OXIA, GREEK_SMALL_LETTER_IOTA_WITH_DIALYTIKA_AND_VARIA, GREEK_SMALL_LETTER_IOTA_WITH_DIALYTIKA_AND_PERISPOMENI,
        GREEK_SMALL_LETTER_IOTA_WITH_MACRON
#ifdef ALLOW_PRIVATE_USE_AREA
        , PUA_GREEK_SMALL_LETTER_IOTA_WITH_PSILI_AND_MACRON,
        PUA_GREEK_SMALL_LETTER_IOTA_WITH_PSILI_AND_OXIA_AND_MACRON,
        PUA_GREEK_SMALL_LETTER_IOTA_WITH_PSILI_AND_VARIA_AND_MACRON,
        PUA_GREEK_SMALL_LETTER_IOTA_WITH_DASIA_AND_MACRON,
        PUA_GREEK_SMALL_LETTER_IOTA_WITH_DASIA_AND_OXIA_AND_MACRON,
        PUA_GREEK_SMALL_LETTER_IOTA_WITH_DASIA_AND_VARIA_AND_MACRON,
        PUA_GREEK_SMALL_LETTER_IOTA_WITH_OXIA_AND_MACRON,
        PUA_GREEK_SMALL_LETTER_IOTA_WITH_VARIA_AND_MACRON
#endif
        },
    
    { GREEK_SMALL_LETTER_OMICRON, GREEK_SMALL_LETTER_OMICRON_WITH_PSILI, GREEK_SMALL_LETTER_OMICRON_WITH_DASIA, GREEK_SMALL_LETTER_OMICRON_WITH_OXIA, GREEK_SMALL_LETTER_OMICRON_WITH_PSILI_AND_OXIA, GREEK_SMALL_LETTER_OMICRON_WITH_DASIA_AND_OXIA, GREEK_SMALL_LETTER_OMICRON_WITH_VARIA, GREEK_SMALL_LETTER_OMICRON_WITH_PSILI_AND_VARIA, GREEK_SMALL_LETTER_OMICRON_WITH_DASIA_AND_VARIA, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
#ifdef ALLOW_PRIVATE_USE_AREA
        , 0, 0, 0, 0, 0, 0, 0, 0
#endif
    },
    
    { GREEK_SMALL_LETTER_UPSILON, GREEK_SMALL_LETTER_UPSILON_WITH_PSILI, GREEK_SMALL_LETTER_UPSILON_WITH_DASIA, GREEK_SMALL_LETTER_UPSILON_WITH_OXIA, GREEK_SMALL_LETTER_UPSILON_WITH_PSILI_AND_OXIA, GREEK_SMALL_LETTER_UPSILON_WITH_DASIA_AND_OXIA, GREEK_SMALL_LETTER_UPSILON_WITH_VARIA, GREEK_SMALL_LETTER_UPSILON_WITH_PSILI_AND_VARIA, GREEK_SMALL_LETTER_UPSILON_WITH_DASIA_AND_VARIA, GREEK_SMALL_LETTER_UPSILON_WITH_PERISPOMENI, GREEK_SMALL_LETTER_UPSILON_WITH_PSILI_AND_PERISPOMENI, GREEK_SMALL_LETTER_UPSILON_WITH_DASIA_AND_PERISPOMENI, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,GREEK_SMALL_LETTER_UPSILON_WITH_DIALYTIKA, GREEK_SMALL_LETTER_UPSILON_WITH_DIALYTIKA_AND_OXIA, GREEK_SMALL_LETTER_UPSILON_WITH_DIALYTIKA_AND_VARIA, GREEK_SMALL_LETTER_UPSILON_WITH_DIALYTIKA_AND_PERISPOMENI,
        GREEK_SMALL_LETTER_UPSILON_WITH_MACRON
#ifdef ALLOW_PRIVATE_USE_AREA
        , PUA_GREEK_SMALL_LETTER_UPSILON_WITH_PSILI_AND_MACRON,
        PUA_GREEK_SMALL_LETTER_UPSILON_WITH_PSILI_AND_OXIA_AND_MACRON,
        PUA_GREEK_SMALL_LETTER_UPSILON_WITH_PSILI_AND_VARIA_AND_MACRON,
        PUA_GREEK_SMALL_LETTER_UPSILON_WITH_DASIA_AND_MACRON,
        PUA_GREEK_SMALL_LETTER_UPSILON_WITH_DASIA_AND_OXIA_AND_MACRON,
        PUA_GREEK_SMALL_LETTER_UPSILON_WITH_DASIA_AND_VARIA_AND_MACRON,
        PUA_GREEK_SMALL_LETTER_UPSILON_WITH_OXIA_AND_MACRON,
        PUA_GREEK_SMALL_LETTER_UPSILON_WITH_VARIA_AND_MACRON
#endif
    },
    
    { GREEK_SMALL_LETTER_OMEGA, GREEK_SMALL_LETTER_OMEGA_WITH_PSILI, GREEK_SMALL_LETTER_OMEGA_WITH_DASIA, GREEK_SMALL_LETTER_OMEGA_WITH_OXIA, GREEK_SMALL_LETTER_OMEGA_WITH_PSILI_AND_OXIA, GREEK_SMALL_LETTER_OMEGA_WITH_DASIA_AND_OXIA, GREEK_SMALL_LETTER_OMEGA_WITH_VARIA, GREEK_SMALL_LETTER_OMEGA_WITH_PSILI_AND_VARIA, GREEK_SMALL_LETTER_OMEGA_WITH_DASIA_AND_VARIA, GREEK_SMALL_LETTER_OMEGA_WITH_PERISPOMENI, GREEK_SMALL_LETTER_OMEGA_WITH_PSILI_AND_PERISPOMENI, GREEK_SMALL_LETTER_OMEGA_WITH_DASIA_AND_PERISPOMENI, GREEK_SMALL_LETTER_OMEGA_WITH_YPOGEGRAMMENI, GREEK_SMALL_LETTER_OMEGA_WITH_PSILI_AND_YPOGEGRAMMENI, GREEK_SMALL_LETTER_OMEGA_WITH_DASIA_AND_YPOGEGRAMMENI, GREEK_SMALL_LETTER_OMEGA_WITH_OXIA_AND_YPOGEGRAMMENI, GREEK_SMALL_LETTER_OMEGA_WITH_PSILI_AND_OXIA_AND_YPOGEGRAMMENI, GREEK_SMALL_LETTER_OMEGA_WITH_DASIA_AND_OXIA_AND_YPOGEGRAMMENI, GREEK_SMALL_LETTER_OMEGA_WITH_VARIA_AND_YPOGEGRAMMENI, GREEK_SMALL_LETTER_OMEGA_WITH_PSILI_AND_VARIA_AND_YPOGEGRAMMENI, GREEK_SMALL_LETTER_OMEGA_WITH_DASIA_AND_VARIA_AND_YPOGEGRAMMENI, GREEK_SMALL_LETTER_OMEGA_WITH_PERISPOMENI_AND_YPOGEGRAMMENI, GREEK_SMALL_LETTER_OMEGA_WITH_PSILI_AND_PERISPOMENI_AND_YPOGEGRAMMENI, GREEK_SMALL_LETTER_OMEGA_WITH_DASIA_AND_PERISPOMENI_AND_YPOGEGRAMMENI, 0, 0, 0, 0, 0
#ifdef ALLOW_PRIVATE_USE_AREA
        , 0, 0, 0, 0, 0, 0, 0, 0
#endif 
    },
    
    /* START CAPITALS */
    { GREEK_CAPITAL_LETTER_ALPHA, GREEK_CAPITAL_LETTER_ALPHA_WITH_PSILI, GREEK_CAPITAL_LETTER_ALPHA_WITH_DASIA, GREEK_CAPITAL_LETTER_ALPHA_WITH_OXIA, GREEK_CAPITAL_LETTER_ALPHA_WITH_PSILI_AND_OXIA, GREEK_CAPITAL_LETTER_ALPHA_WITH_DASIA_AND_OXIA, GREEK_CAPITAL_LETTER_ALPHA_WITH_VARIA, GREEK_CAPITAL_LETTER_ALPHA_WITH_PSILI_AND_VARIA, GREEK_CAPITAL_LETTER_ALPHA_WITH_DASIA_AND_VARIA, 0/*GREEK_CAPITAL_LETTER_ALPHA_WITH_PERISPOMENI*/, GREEK_CAPITAL_LETTER_ALPHA_WITH_PSILI_AND_PERISPOMENI, GREEK_CAPITAL_LETTER_ALPHA_WITH_DASIA_AND_PERISPOMENI, GREEK_CAPITAL_LETTER_ALPHA_WITH_PROSGEGRAMMENI, GREEK_CAPITAL_LETTER_ALPHA_WITH_PSILI_AND_PROSGEGRAMMENI, GREEK_CAPITAL_LETTER_ALPHA_WITH_DASIA_AND_PROSGEGRAMMENI, 0/*GREEK_CAPITAL_LETTER_ALPHA_WITH_OXIA_AND_PROSGEGRAMMENI*/, GREEK_CAPITAL_LETTER_ALPHA_WITH_PSILI_AND_OXIA_AND_PROSGEGRAMMENI, GREEK_CAPITAL_LETTER_ALPHA_WITH_DASIA_AND_OXIA_AND_PROSGEGRAMMENI, 0/*GREEK_CAPITAL_LETTER_ALPHA_WITH_VARIA_AND_PROSGEGRAMMENI*/, GREEK_CAPITAL_LETTER_ALPHA_WITH_PSILI_AND_VARIA_AND_PROSGEGRAMMENI, GREEK_CAPITAL_LETTER_ALPHA_WITH_DASIA_AND_VARIA_AND_PROSGEGRAMMENI, 0/*GREEK_CAPITAL_LETTER_ALPHA_WITH_PERISPOMENI_AND_PROSGEGRAMMENI*/, GREEK_CAPITAL_LETTER_ALPHA_WITH_PSILI_AND_PERISPOMENI_AND_PROSGEGRAMMENI, GREEK_CAPITAL_LETTER_ALPHA_WITH_DASIA_AND_PERISPOMENI_AND_PROSGEGRAMMENI, 0, 0, 0, 0, 0
#ifdef ALLOW_PRIVATE_USE_AREA
        , 0, 0, 0, 0, 0, 0, 0, 0
#endif
    },
    
    { GREEK_CAPITAL_LETTER_EPSILON, GREEK_CAPITAL_LETTER_EPSILON_WITH_PSILI, GREEK_CAPITAL_LETTER_EPSILON_WITH_DASIA, GREEK_CAPITAL_LETTER_EPSILON_WITH_OXIA, GREEK_CAPITAL_LETTER_EPSILON_WITH_PSILI_AND_OXIA, GREEK_CAPITAL_LETTER_EPSILON_WITH_DASIA_AND_OXIA, GREEK_CAPITAL_LETTER_EPSILON_WITH_VARIA, GREEK_CAPITAL_LETTER_EPSILON_WITH_PSILI_AND_VARIA, GREEK_CAPITAL_LETTER_EPSILON_WITH_DASIA_AND_VARIA, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
#ifdef ALLOW_PRIVATE_USE_AREA
        , 0, 0, 0, 0, 0, 0, 0, 0
#endif
    },
    
    { GREEK_CAPITAL_LETTER_ETA, GREEK_CAPITAL_LETTER_ETA_WITH_PSILI, GREEK_CAPITAL_LETTER_ETA_WITH_DASIA, GREEK_CAPITAL_LETTER_ETA_WITH_OXIA, GREEK_CAPITAL_LETTER_ETA_WITH_PSILI_AND_OXIA, GREEK_CAPITAL_LETTER_ETA_WITH_DASIA_AND_OXIA, GREEK_CAPITAL_LETTER_ETA_WITH_VARIA, GREEK_CAPITAL_LETTER_ETA_WITH_PSILI_AND_VARIA, GREEK_CAPITAL_LETTER_ETA_WITH_DASIA_AND_VARIA, 0/*GREEK_CAPITAL_LETTER_ETA_WITH_PERISPOMENI*/, GREEK_CAPITAL_LETTER_ETA_WITH_PSILI_AND_PERISPOMENI, GREEK_CAPITAL_LETTER_ETA_WITH_DASIA_AND_PERISPOMENI, GREEK_CAPITAL_LETTER_ETA_WITH_PROSGEGRAMMENI, GREEK_CAPITAL_LETTER_ETA_WITH_PSILI_AND_PROSGEGRAMMENI, GREEK_CAPITAL_LETTER_ETA_WITH_DASIA_AND_PROSGEGRAMMENI, 0/*GREEK_CAPITAL_LETTER_ETA_WITH_OXIA_AND_PROSGEGRAMMENI*/, GREEK_CAPITAL_LETTER_ETA_WITH_PSILI_AND_OXIA_AND_PROSGEGRAMMENI, GREEK_CAPITAL_LETTER_ETA_WITH_DASIA_AND_OXIA_AND_PROSGEGRAMMENI, 0/*GREEK_CAPITAL_LETTER_ETA_WITH_VARIA_AND_PROSGEGRAMMENI*/, GREEK_CAPITAL_LETTER_ETA_WITH_PSILI_AND_VARIA_AND_PROSGEGRAMMENI, GREEK_CAPITAL_LETTER_ETA_WITH_DASIA_AND_VARIA_AND_PROSGEGRAMMENI, 0/*GREEK_CAPITAL_LETTER_ETA_WITH_PERISPOMENI_AND_PROSGEGRAMMENI*/, GREEK_CAPITAL_LETTER_ETA_WITH_PSILI_AND_PERISPOMENI_AND_PROSGEGRAMMENI, GREEK_CAPITAL_LETTER_ETA_WITH_DASIA_AND_PERISPOMENI_AND_PROSGEGRAMMENI, 0, 0, 0, 0, 0
#ifdef ALLOW_PRIVATE_USE_AREA
        , 0, 0, 0, 0, 0, 0, 0, 0
#endif
    },
    
    { GREEK_CAPITAL_LETTER_IOTA, GREEK_CAPITAL_LETTER_IOTA_WITH_PSILI, GREEK_CAPITAL_LETTER_IOTA_WITH_DASIA, GREEK_CAPITAL_LETTER_IOTA_WITH_OXIA, GREEK_CAPITAL_LETTER_IOTA_WITH_PSILI_AND_OXIA, GREEK_CAPITAL_LETTER_IOTA_WITH_DASIA_AND_OXIA, GREEK_CAPITAL_LETTER_IOTA_WITH_VARIA, GREEK_CAPITAL_LETTER_IOTA_WITH_PSILI_AND_VARIA, GREEK_CAPITAL_LETTER_IOTA_WITH_DASIA_AND_VARIA, 0/*GREEK_CAPITAL_LETTER_IOTA_WITH_PERISPOMENI*/, GREEK_CAPITAL_LETTER_IOTA_WITH_PSILI_AND_PERISPOMENI, GREEK_CAPITAL_LETTER_IOTA_WITH_DASIA_AND_PERISPOMENI, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, GREEK_CAPITAL_LETTER_IOTA_WITH_DIALYTIKA, 0, 0, 0, 0
#ifdef ALLOW_PRIVATE_USE_AREA
        , 0, 0, 0, 0, 0, 0, 0, 0
#endif
    },
    { GREEK_CAPITAL_LETTER_OMICRON, GREEK_CAPITAL_LETTER_OMICRON_WITH_PSILI, GREEK_CAPITAL_LETTER_OMICRON_WITH_DASIA, GREEK_CAPITAL_LETTER_OMICRON_WITH_OXIA, GREEK_CAPITAL_LETTER_OMICRON_WITH_PSILI_AND_OXIA, GREEK_CAPITAL_LETTER_OMICRON_WITH_DASIA_AND_OXIA, GREEK_CAPITAL_LETTER_OMICRON_WITH_VARIA, GREEK_CAPITAL_LETTER_OMICRON_WITH_PSILI_AND_VARIA, GREEK_CAPITAL_LETTER_OMICRON_WITH_DASIA_AND_VARIA, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
#ifdef ALLOW_PRIVATE_USE_AREA
        , 0, 0, 0, 0, 0, 0, 0, 0
#endif
    },
    
    { GREEK_CAPITAL_LETTER_UPSILON, 0/*GREEK_CAPITAL_LETTER_UPSILON_WITH_PSILI*/, GREEK_CAPITAL_LETTER_UPSILON_WITH_DASIA, GREEK_CAPITAL_LETTER_UPSILON_WITH_OXIA, 0/*GREEK_CAPITAL_LETTER_UPSILON_WITH_PSILI_AND_OXIA*/, GREEK_CAPITAL_LETTER_UPSILON_WITH_DASIA_AND_OXIA, GREEK_CAPITAL_LETTER_UPSILON_WITH_VARIA, 0/*GREEK_CAPITAL_LETTER_UPSILON_WITH_PSILI_AND_VARIA*/, GREEK_CAPITAL_LETTER_UPSILON_WITH_DASIA_AND_VARIA, 0/*GREEK_CAPITAL_LETTER_UPSILON_WITH_PERISPOMENI*/, 0/*GREEK_CAPITAL_LETTER_UPSILON_WITH_PSILI_AND_PERISPOMENI*/, GREEK_CAPITAL_LETTER_UPSILON_WITH_DASIA_AND_PERISPOMENI, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, GREEK_CAPITAL_LETTER_UPSILON_WITH_DIALYTIKA, 0, 0, 0, 0
#ifdef ALLOW_PRIVATE_USE_AREA
        , 0, 0, 0, 0, 0, 0, 0, 0
#endif
    },
    
    { GREEK_CAPITAL_LETTER_OMEGA, GREEK_CAPITAL_LETTER_OMEGA_WITH_PSILI, GREEK_CAPITAL_LETTER_OMEGA_WITH_DASIA, GREEK_CAPITAL_LETTER_OMEGA_WITH_OXIA, GREEK_CAPITAL_LETTER_OMEGA_WITH_PSILI_AND_OXIA, GREEK_CAPITAL_LETTER_OMEGA_WITH_DASIA_AND_OXIA, GREEK_CAPITAL_LETTER_OMEGA_WITH_VARIA, GREEK_CAPITAL_LETTER_OMEGA_WITH_PSILI_AND_VARIA, GREEK_CAPITAL_LETTER_OMEGA_WITH_DASIA_AND_VARIA, 0/*GREEK_CAPITAL_LETTER_OMEGA_WITH_PERISPOMENI*/, GREEK_CAPITAL_LETTER_OMEGA_WITH_PSILI_AND_PERISPOMENI, GREEK_CAPITAL_LETTER_OMEGA_WITH_DASIA_AND_PERISPOMENI, GREEK_CAPITAL_LETTER_OMEGA_WITH_PROSGEGRAMMENI, GREEK_CAPITAL_LETTER_OMEGA_WITH_PSILI_AND_PROSGEGRAMMENI, GREEK_CAPITAL_LETTER_OMEGA_WITH_DASIA_AND_PROSGEGRAMMENI, 0/*GREEK_CAPITAL_LETTER_OMEGA_WITH_OXIA_AND_PROSGEGRAMMENI*/, GREEK_CAPITAL_LETTER_OMEGA_WITH_PSILI_AND_OXIA_AND_PROSGEGRAMMENI, GREEK_CAPITAL_LETTER_OMEGA_WITH_DASIA_AND_OXIA_AND_PROSGEGRAMMENI, 0/*GREEK_CAPITAL_LETTER_OMEGA_WITH_VARIA_AND_PROSGEGRAMMENI*/, GREEK_CAPITAL_LETTER_OMEGA_WITH_PSILI_AND_VARIA_AND_PROSGEGRAMMENI, GREEK_CAPITAL_LETTER_OMEGA_WITH_DASIA_AND_VARIA_AND_PROSGEGRAMMENI, 0/*GREEK_CAPITAL_LETTER_OMEGA_WITH_PERISPOMENI_AND_PROSGEGRAMMENI*/, GREEK_CAPITAL_LETTER_OMEGA_WITH_PSILI_AND_PERISPOMENI_AND_PROSGEGRAMMENI, GREEK_CAPITAL_LETTER_OMEGA_WITH_DASIA_AND_PERISPOMENI_AND_PROSGEGRAMMENI, 0, 0, 0, 0, 0
#ifdef ALLOW_PRIVATE_USE_AREA
        , 0, 0, 0, 0, 0, 0, 0, 0
#endif
    }
};

bool analyzePrecomposedLetter(unsigned short letter, int *l, int *a)
{
    for (*l = 0; *l < NUM_VOWEL_CODES; (*l)++)
    {
        for (*a = 0; *a < NUM_ACCENT_CODES; (*a)++)
        {
            if (letter == letters[*l][*a])
                return true;
        }
    }
    return false;
}

int precomposedIndexToBitMask(int precomposedIndex, int diacriticMask)
{
    //don't initialize to false here because diacriticMask could have combining accents already set to true
    //make sure this is in order of enum so compiler can optimize switch
    switch (precomposedIndex)
    {
        case PSILI:
            diacriticMask |= _SMOOTH;
            break;
        case DASIA:
            diacriticMask |= _ROUGH;
            break;
        case OXIA:
            diacriticMask |= _ACUTE;
            break;
        case PSILI_AND_OXIA:
            diacriticMask |= (_SMOOTH | _ACUTE);
            break;
        case DASIA_AND_OXIA:
            diacriticMask |= (_ROUGH | _ACUTE);
            break;
        case VARIA:
            diacriticMask |= _GRAVE;
            break;
        case PSILI_AND_VARIA:
            diacriticMask |= (_SMOOTH | _GRAVE);
            break;
        case DASIA_AND_VARIA:
            diacriticMask |= (_ROUGH | _GRAVE);
            break;
        case PERISPOMENI:
            diacriticMask |= _CIRCUMFLEX;
            break;
        case PSILI_AND_PERISPOMENI:
            diacriticMask |= (_SMOOTH | _CIRCUMFLEX);
            break;
        case DASIA_AND_PERISPOMENI:
            diacriticMask |= (_ROUGH | _CIRCUMFLEX);
            break;
        case YPOGEGRAMMENI:
            diacriticMask |= _IOTA_SUB;
            break;
        case PSILI_AND_YPOGEGRAMMENI:
            diacriticMask |= (_SMOOTH | _IOTA_SUB);
            break;
        case DASIA_AND_YPOGEGRAMMENI:
            diacriticMask |= (_ROUGH | _IOTA_SUB);
            break;
        case OXIA_AND_YPOGEGRAMMENI:
            diacriticMask |= (_ACUTE | _IOTA_SUB);
            break;
        case PSILI_AND_OXIA_AND_YPOGEGRAMMENI:
            diacriticMask |= (_SMOOTH | _ACUTE | _IOTA_SUB);
            break;
        case DASIA_AND_OXIA_AND_YPOGEGRAMMENI:
            diacriticMask |= (_ROUGH | _ACUTE | _IOTA_SUB);
            break;
        case VARIA_AND_YPOGEGRAMMENI:
            diacriticMask |= (_GRAVE | _IOTA_SUB);
            break;
        case PSILI_AND_VARIA_AND_YPOGEGRAMMENI:
            diacriticMask |= (_SMOOTH | _GRAVE | _IOTA_SUB);
            break;
        case DASIA_AND_VARIA_AND_YPOGEGRAMMENI:
            diacriticMask |= (_ROUGH | _GRAVE | _IOTA_SUB);
            break;
        case PERISPOMENI_AND_YPOGEGRAMMENI:
            diacriticMask |= (_CIRCUMFLEX | _IOTA_SUB);
            break;
        case PSILI_AND_PERISPOMENI_AND_YPOGEGRAMMENI:
            diacriticMask |= (_SMOOTH | _CIRCUMFLEX | _IOTA_SUB);
            break;
        case DASIA_AND_PERISPOMENI_AND_YPOGEGRAMMENI:
            diacriticMask |= (_ROUGH | _CIRCUMFLEX | _IOTA_SUB);
            break;
        case DIALYTIKA:
            diacriticMask |= _DIAERESIS;
            break;
        case DIALYTIKA_AND_OXIA:
            diacriticMask |= (_DIAERESIS | _ACUTE);
            break;
        case DIALYTIKA_AND_VARIA:
            diacriticMask |= (_DIAERESIS | _GRAVE);
            break;
        case DIALYTIKA_AND_PERISPOMENON:
            diacriticMask |= (_DIAERESIS | _CIRCUMFLEX);
            break;
        case MACRON_PRECOMPOSED:
            diacriticMask |= _MACRON;
            break;
#ifdef ALLOW_PRIVATE_USE_AREA
        case MACRON_AND_SMOOTH:
            diacriticMask |= (_MACRON | _SMOOTH);
            break;
        case MACRON_AND_SMOOTH_AND_ACUTE:
            diacriticMask |= (_MACRON | _SMOOTH | _ACUTE);
            break;
        case MACRON_AND_SMOOTH_AND_GRAVE:
            diacriticMask |= (_MACRON | _SMOOTH | _GRAVE);
            break;
        case MACRON_AND_ROUGH:
            diacriticMask |= (_MACRON | _ROUGH);
            break;
        case MACRON_AND_ROUGH_AND_ACUTE:
            diacriticMask |= (_MACRON | _ROUGH | _ACUTE);
            break;
        case MACRON_AND_ROUGH_AND_GRAVE:
            diacriticMask |= (_MACRON | _ROUGH | _GRAVE);
            break;
        case MACRON_AND_ACUTE:
            diacriticMask |= (_MACRON | _ACUTE);
            break;
        case MACRON_AND_GRAVE:
            diacriticMask |= (_MACRON | _GRAVE);
            break;
#endif
    }
    return diacriticMask;
}
/*
//for testing
//http://stackoverflow.com/questions/111928/is-there-a-printf-converter-to-print-in-binary-format
const char *byte_to_binary(int x)
{
    static char b[9];
    b[0] = '\0';
    
    int z;
    for (z = 128; z > 0; z >>= 1)
    {
        strcat(b, ((x & z) == z) ? "1" : "0");
    }
    
    return b;
}
*/
//return 0 for invalid letter
unsigned short getPrecomposedLetter(int letterIndex, int diacriticMask)
{
    int accentIndex = 0;
    
    switch (diacriticMask)
    {
        case 0:
            accentIndex = NORMAL;
            break;
        case (_SMOOTH):
            accentIndex = PSILI;
            break;
        case (_ROUGH):
            accentIndex = DASIA;
            break;
        case (_ACUTE):
            accentIndex = OXIA;
            break;
        case (_SMOOTH | _ACUTE):
            accentIndex = PSILI_AND_OXIA;
            break;
        case (_ROUGH | _ACUTE):
            accentIndex = DASIA_AND_OXIA;
            break;
        case  (_GRAVE):
            accentIndex = VARIA;
            break;
        case (_SMOOTH | _GRAVE):
            accentIndex = PSILI_AND_VARIA;
            break;
        case (_ROUGH | _GRAVE):
            accentIndex = DASIA_AND_VARIA;
            break;
        case (_CIRCUMFLEX):
            accentIndex = PERISPOMENI;
            break;
        case (_SMOOTH | _CIRCUMFLEX):
            accentIndex = PSILI_AND_PERISPOMENI;
            break;
        case (_ROUGH | _CIRCUMFLEX):
            accentIndex = DASIA_AND_PERISPOMENI;
            break;
        case (_IOTA_SUB):
            accentIndex = YPOGEGRAMMENI;
            break;
        case (_SMOOTH | _IOTA_SUB):
            accentIndex = PSILI_AND_YPOGEGRAMMENI;
            break;
        case (_ROUGH | _IOTA_SUB):
            accentIndex = DASIA_AND_YPOGEGRAMMENI;
            break;
        case (_ACUTE | _IOTA_SUB):
            accentIndex = OXIA_AND_YPOGEGRAMMENI;
            break;
        case (_SMOOTH | _ACUTE | _IOTA_SUB):
            accentIndex = PSILI_AND_OXIA_AND_YPOGEGRAMMENI;
            break;
        case (_ROUGH | _ACUTE | _IOTA_SUB):
            accentIndex = DASIA_AND_OXIA_AND_YPOGEGRAMMENI;
            break;
        case (_GRAVE | _IOTA_SUB):
            accentIndex = VARIA_AND_YPOGEGRAMMENI;
            break;
        case (_SMOOTH | _GRAVE | _IOTA_SUB):
            accentIndex = PSILI_AND_VARIA_AND_YPOGEGRAMMENI;
            break;
        case (_ROUGH | _GRAVE | _IOTA_SUB):
            accentIndex = DASIA_AND_VARIA_AND_YPOGEGRAMMENI;
            break;
        case (_CIRCUMFLEX | _IOTA_SUB):
            accentIndex = PERISPOMENI_AND_YPOGEGRAMMENI;
            break;
        case (_SMOOTH | _CIRCUMFLEX | _IOTA_SUB):
            accentIndex = PSILI_AND_PERISPOMENI_AND_YPOGEGRAMMENI;
            break;
        case (_ROUGH | _CIRCUMFLEX | _IOTA_SUB):
            accentIndex = DASIA_AND_PERISPOMENI_AND_YPOGEGRAMMENI;
            break;
        case (_DIAERESIS):
            accentIndex = DIALYTIKA;
            break;
        case (_ACUTE | _DIAERESIS):
            accentIndex = DIALYTIKA_AND_OXIA;
            break;
        case (_GRAVE | _DIAERESIS):
            accentIndex = DIALYTIKA_AND_VARIA;
            break;
        case (_CIRCUMFLEX | _DIAERESIS):
            accentIndex = DIALYTIKA_AND_PERISPOMENON;
            break;
        case (_MACRON):
            accentIndex = MACRON_PRECOMPOSED;
            break;
#ifdef ALLOW_PRIVATE_USE_AREA
        case (_MACRON | _SMOOTH):
            accentIndex = MACRON_AND_SMOOTH;
            break;
        case (_MACRON | _SMOOTH | _ACUTE):
            accentIndex = MACRON_AND_SMOOTH_AND_ACUTE;
            break;
        case (_MACRON | _SMOOTH | _GRAVE):
            accentIndex = MACRON_AND_SMOOTH_AND_GRAVE;
            break;
        case (_MACRON | _ROUGH):
            accentIndex = MACRON_AND_ROUGH;
            break;
        case (_MACRON | _ROUGH | _ACUTE):
            accentIndex = MACRON_AND_ROUGH_AND_ACUTE;
            break;
        case (_MACRON | _ROUGH | _GRAVE):
            accentIndex = MACRON_AND_ROUGH_AND_GRAVE;
            break;
        case (_MACRON | _ACUTE):
            accentIndex = MACRON_AND_ACUTE;
            break;
        case (_MACRON | _GRAVE):
            accentIndex = MACRON_AND_GRAVE;
            break;
#endif
        default:
            return 0; //or set accent = 0 if none of these?
            break;
    }
    if (letterIndex > -1)
        return letters[letterIndex][accentIndex];
    else
        return 0;
}

bool isCombiningDiacritic(UCS2 l)
{
    switch (l)
    {
        case COMBINING_MACRON:
        case COMBINING_ACUTE:
        case COMBINING_GRAVE:
        case COMBINING_CIRCUMFLEX:
        case COMBINING_IOTA_SUBSCRIPT:
        case COMBINING_SMOOTH_BREATHING:
        case COMBINING_ROUGH_BREATHING:
        case COMBINING_DIAERESIS:
            return true;
    }
    
    return false;
}

bool isBareVowel(UCS2 l)
{
    switch (l)
    {
        case GREEK_SMALL_LETTER_ALPHA:
        case GREEK_SMALL_LETTER_EPSILON:
        case GREEK_SMALL_LETTER_ETA:
        case GREEK_SMALL_LETTER_IOTA:
        case GREEK_SMALL_LETTER_UPSILON:
        case GREEK_SMALL_LETTER_OMEGA:
            return true;
    }
    
    return false;
}

int ucs2ToLetterCode(UCS2 l)
{
    if (l == GREEK_SMALL_LETTER_ALPHA)
        return ALPHA;
    else if (l == GREEK_SMALL_LETTER_EPSILON)
        return EPSILON;
    else if (l == GREEK_SMALL_LETTER_ETA)
        return ETA;
    else if (l == GREEK_SMALL_LETTER_IOTA)
        return IOTA;
    else if (l == GREEK_SMALL_LETTER_OMICRON)
        return OMICRON;
    else if (l == GREEK_SMALL_LETTER_UPSILON)
        return UPSILON;
    else if (l == GREEK_SMALL_LETTER_OMEGA)
        return OMEGA;
    else
        return 0;
}

int letterCodeToUCS2(UCS2 l)
{
    if (l == ALPHA)
        return GREEK_SMALL_LETTER_ALPHA;
    else if (l == EPSILON)
        return GREEK_SMALL_LETTER_EPSILON;
    else if (l == ETA)
        return GREEK_SMALL_LETTER_ETA;
    else if (l == IOTA)
        return GREEK_SMALL_LETTER_IOTA;
    else if (l == OMICRON)
        return GREEK_SMALL_LETTER_OMICRON;
    else if (l == UPSILON)
        return GREEK_SMALL_LETTER_UPSILON;
    else if (l == OMEGA)
        return GREEK_SMALL_LETTER_OMEGA;
    else
        return 0;
}

bool isLegalDiacriticForLetter(int letterCode, int accentToAdd)
{
    switch (accentToAdd)
    {
        case CIRCUMFLEX:
            if (letterCode != ALPHA && letterCode != ETA && letterCode != IOTA && letterCode != UPSILON && letterCode != OMEGA)
            {
                return false;
            }
            break;
        case MACRON:
            if (letterCode != ALPHA && letterCode != IOTA && letterCode != UPSILON)
            {
                return false;
            }
            break;
        case IOTA_SUBSCRIPT:
            if (letterCode != ALPHA && letterCode != ETA && letterCode != OMEGA && letterCode != ALPHA_CAP && letterCode != ETA_CAP && letterCode != OMEGA_CAP)
            {
                return false;
            }
            break;
        case DIAERESIS:
            if (letterCode != IOTA && letterCode != UPSILON && letterCode != IOTA_CAP && letterCode != UPSILON_CAP)
            {
                return false;
            }
            break;
    }
    return true;
}

//adjusts diacritics based on one being added
int updateDiacritics(int letterCode, int accentToAdd, int accentBitMask, bool toggleOff)
{
    //keep in order of enum so compiler can optimize switch
    switch (accentToAdd)
    {
        case ACUTE:
            if (toggleOff && (accentBitMask & _ACUTE) == _ACUTE)
                accentBitMask &= ~_ACUTE;
            else
                accentBitMask |= _ACUTE;
            accentBitMask &= ~(_GRAVE | _CIRCUMFLEX); //turn off...
            break;
        case CIRCUMFLEX:
            if (toggleOff && (accentBitMask & _CIRCUMFLEX) == _CIRCUMFLEX)
                accentBitMask &= ~_CIRCUMFLEX;
            else
                accentBitMask |= _CIRCUMFLEX;
            accentBitMask &= ~(_ACUTE | _GRAVE | _MACRON); //turn off
            break;
        case GRAVE:
            if (toggleOff && (accentBitMask & _GRAVE) == _GRAVE)
                accentBitMask &= ~_GRAVE;
            else
                accentBitMask |= _GRAVE;
            accentBitMask &= ~(_ACUTE | _CIRCUMFLEX);
            break;
        case MACRON:
            if (toggleOff && (accentBitMask & _MACRON) == _MACRON)
                accentBitMask &= ~_MACRON;
            else
                accentBitMask |= _MACRON;
            accentBitMask &= ~_CIRCUMFLEX;
            break;
        case ROUGH_BREATHING:
            if (toggleOff && (accentBitMask & _ROUGH) == _ROUGH)
                accentBitMask &= ~_ROUGH;
            else
                accentBitMask |= _ROUGH;
            accentBitMask &= ~(_SMOOTH | _DIAERESIS);
            break;
        case SMOOTH_BREATHING:
            if (toggleOff && (accentBitMask & _SMOOTH) == _SMOOTH)
                accentBitMask &= ~_SMOOTH;
            else
                accentBitMask |= _SMOOTH;
            accentBitMask &= ~(_ROUGH | _DIAERESIS);
            break;
        case IOTA_SUBSCRIPT:
            if (toggleOff && (accentBitMask & _IOTA_SUB) == _IOTA_SUB)
                accentBitMask &= ~_IOTA_SUB;
            else
                accentBitMask |= _IOTA_SUB;
            break;
        case DIAERESIS:
            if (letterCode == IOTA_CAP || letterCode == UPSILON_CAP)
            {
                accentBitMask &= ~(_ACUTE | _GRAVE | _CIRCUMFLEX | _MACRON);
            }
            
            if (toggleOff && (accentBitMask & _DIAERESIS) == _DIAERESIS)
                accentBitMask &= ~_DIAERESIS;
            else
                accentBitMask |= _DIAERESIS;
            accentBitMask &= ~(_SMOOTH | _ROUGH);
            break;
    }
    return accentBitMask;
}

//passes back the letterCode and diacriticMask for this letter
//returns the number of characters or -1, if not valid or unknown
int analyzeLetter(UCS2 *ucs2String, int i, int len, int *letterCode, int *accentBitMask)
{
    unsigned char letterLen = 1;
    int precomposedIndex = 0;
    
    //this needs some fixing
    if (len > 1 )
    {
        for (int j = 1; j <= MAX_COMBINING && i + j < len; j++)
        {
            if (ucs2String[i + j] == COMBINING_ROUGH_BREATHING)
            {
                letterLen++;
                *accentBitMask |= _ROUGH;
            }
            else if (ucs2String[i + j] == COMBINING_SMOOTH_BREATHING)
            {
                letterLen++;
                *accentBitMask |= _SMOOTH;
            }
            else if (ucs2String[i + j] == COMBINING_ACUTE)
            {
                letterLen++;
                *accentBitMask |= _ACUTE;
            }
            else if (ucs2String[i + j] == COMBINING_GRAVE)
            {
                letterLen++;
                *accentBitMask |= _GRAVE;
            }
            else if (ucs2String[i + j] == COMBINING_CIRCUMFLEX)
            {
                letterLen++;
                *accentBitMask |= _CIRCUMFLEX;
            }
            else if (ucs2String[i + j] == COMBINING_MACRON)
            {
                letterLen++;
                *accentBitMask |= _MACRON;
            }
            else if (ucs2String[i + j] == COMBINING_IOTA_SUBSCRIPT)
            {
                letterLen++;
                *accentBitMask |= _IOTA_SUB;
            }
            else if (ucs2String[i + j] == COMBINING_DIAERESIS)
            {
                letterLen++;
                *accentBitMask |= _DIAERESIS;
            }
            else
            {
                break;
            }
        }
    }
    
    if (!analyzePrecomposedLetter(ucs2String[i], letterCode, &precomposedIndex))
    {
        return -1;
    }
    *accentBitMask = precomposedIndexToBitMask(precomposedIndex, *accentBitMask);
    
    return letterLen;
}

bool makeLetter(UCS2 *ucs2String, int *newLetterLen, int letterCode, int accentBitMask, int unicodeMode)
{
    //Use PUA, - almost all precomposing except alpha macron, breathing, accent, iota_sub, if iota_sub use combining
    //Use both, if macron use combining
    //Use only combining accents
    int i = 0;
    
    //fallback if macron + one more diacritic
    bool precomposingFallbackToComposing = false;
    if ((unicode_mode == PRECOMPOSED_MODE && (accentBitMask & _MACRON) == _MACRON) || (unicodeMode == PRECOMPOSED_WITH_PUA_MODE &&  (accentBitMask & (_MACRON | _DIAERESIS)) == (_MACRON | _DIAERESIS)))
    {
        if ((accentBitMask & ~_MACRON) != 0)//if any other bits set besides macron
        {
            precomposingFallbackToComposing = true;
        }
    }
    else if (unicodeMode == PRECOMPOSED_HC_MODE && (accentBitMask & _MACRON) == _MACRON)
    {
        //this is legacy for the hoplite challenge app which uses combining macron even if no other diacritics
        precomposingFallbackToComposing = true;
    }
    
    *newLetterLen = 1;
    if (unicode_mode == COMBINING_ONLY_MODE || precomposingFallbackToComposing)
    {
        if ((accentBitMask & _MACRON) == _MACRON)
            (*newLetterLen)++;
        if ((accentBitMask & _SMOOTH) == _SMOOTH)
            (*newLetterLen)++;
        if ((accentBitMask & _ROUGH) == _ROUGH)
            (*newLetterLen)++;
        if ((accentBitMask & _ACUTE) == _ACUTE)
            (*newLetterLen)++;
        if ((accentBitMask & _GRAVE) == _GRAVE)
            (*newLetterLen)++;
        if ((accentBitMask & _CIRCUMFLEX) == _CIRCUMFLEX)
            (*newLetterLen)++;
        if ((accentBitMask & _IOTA_SUB) == _IOTA_SUB)
            (*newLetterLen)++;
        if ((accentBitMask & _DIAERESIS) == _DIAERESIS)
            (*newLetterLen)++;

        ucs2String[i] = letterCodeToUCS2(letterCode); //set base letter
        
        unsigned char numAccents = 1;
        //loop so that order is determined by combiningAccents array
        for (int k = 0; k < NUM_COMBINING_ACCENTS; k++)
        {
            if (combiningAccents[k] == COMBINING_MACRON && (accentBitMask & _MACRON) == _MACRON)
            {
                ucs2String[i + numAccents] = combiningAccents[k];
                numAccents++;
            }
            else if (combiningAccents[k] == COMBINING_ROUGH_BREATHING && (accentBitMask & _ROUGH) == _ROUGH)
            {
                ucs2String[i + numAccents] = combiningAccents[k];
                numAccents++;
            }
            else if (combiningAccents[k] == COMBINING_SMOOTH_BREATHING && (accentBitMask & _SMOOTH) == _SMOOTH)
            {
                ucs2String[i + numAccents] = combiningAccents[k];
                numAccents++;
            }
            else if (combiningAccents[k] == COMBINING_ACUTE && (accentBitMask & _ACUTE) == _ACUTE)
            {
                ucs2String[i + numAccents] = combiningAccents[k];
                numAccents++;
            }
            else if (combiningAccents[k] == COMBINING_GRAVE && (accentBitMask & _GRAVE) == _GRAVE)
            {
                ucs2String[i + numAccents] = combiningAccents[k];
                numAccents++;
            }
            else if (combiningAccents[k] == COMBINING_CIRCUMFLEX && (accentBitMask & _CIRCUMFLEX) == _CIRCUMFLEX)
            {
                ucs2String[i + numAccents] = combiningAccents[k];
                numAccents++;
            }
            else if (combiningAccents[k] == COMBINING_IOTA_SUBSCRIPT && (accentBitMask & _IOTA_SUB) == _IOTA_SUB)
            {
                ucs2String[i + numAccents] = combiningAccents[k];
                numAccents++;
            }
            else if (combiningAccents[k] == COMBINING_DIAERESIS && (accentBitMask & _DIAERESIS) == _DIAERESIS)
            {
                ucs2String[i + numAccents] = combiningAccents[k];
                numAccents++;
            }
        }
        return true;
    }
    else
    {
        if (unicode_mode == PRECOMPOSED_WITH_PUA_MODE && (accentBitMask & (_IOTA_SUB | _MACRON)) == (_IOTA_SUB | _MACRON))
        {
            (*newLetterLen)++;
        }

        if (unicode_mode == PRECOMPOSED_WITH_PUA_MODE && (accentBitMask & (_IOTA_SUB | _MACRON)) == (_IOTA_SUB | _MACRON))
        {
            ucs2String[i+1] = COMBINING_IOTA_SUBSCRIPT;
            accentBitMask &= ~_IOTA_SUB; //so we don't get two iota subscripts
        }
        
        unsigned short ucs2 = getPrecomposedLetter(letterCode, accentBitMask);
        if (ucs2 != 0)
        {
            ucs2String[i] = ucs2;
            return true;
        }
        else
        {
            return false;
        }
        
        //fprintf(stderr, "final len: %d\n", *len);
    }
}

//there should be room for a least MAX_COMBINING more characters at the end of ucs2String, in case it needs to grow
void accentSyllable(UCS2 *ucs2String, int i, int *len, int accentToAdd, bool toggleOff, int unicodeMode)
{
    unicode_mode = unicodeMode;
    
    if (*len < 1)
        return;
    
    //1. handle consonants
    if (ucs2String[i] == GREEK_SMALL_LETTER_RHO && accentToAdd == ROUGH_BREATHING)
    {
        ucs2String[i] = GREEK_SMALL_LETTER_RHO_WITH_DASIA;
        return;
    }
    else if (ucs2String[i] == GREEK_SMALL_LETTER_RHO_WITH_DASIA && accentToAdd == ROUGH_BREATHING)
    {
        ucs2String[i] = GREEK_SMALL_LETTER_RHO;
        return;
    }
    else if (ucs2String[i] == GREEK_CAPITAL_LETTER_RHO && accentToAdd == ROUGH_BREATHING)
    {
        ucs2String[i] = GREEK_CAPITAL_LETTER_RHO_WITH_DASIA;
        return;
    }
    else if (ucs2String[i] == GREEK_CAPITAL_LETTER_RHO_WITH_DASIA && accentToAdd == ROUGH_BREATHING)
    {
        ucs2String[i] = GREEK_CAPITAL_LETTER_RHO;
        return;
    }
    else if (ucs2String[i] == GREEK_SMALL_LETTER_NU && accentToAdd == SURROUNDING_PARENTHESES)
    {
        rightShiftFromOffsetSteps(ucs2String, i, 2, len);
        ucs2String[i] = LEFT_PARENTHESIS;
        ucs2String[i+1] = GREEK_SMALL_LETTER_NU;
        ucs2String[i+2] = RIGHT_PARENTHESIS;
        return;
    }
    
    //2. now analyze what is currently there
    int letterCode = 0;
    int accentBitMask = 0;
    
    //this will be -1 on error
    char letterLen = analyzeLetter(ucs2String, i, *len, &letterCode, &accentBitMask);
    if (letterLen < 1)
        return;
    
    //2.5: return if this diacritic isn't legal for the letter it's being added to
    if (!isLegalDiacriticForLetter(letterCode, accentToAdd))
        return;
    
    //3. this changes old letter analysis to the one we want
    accentBitMask = updateDiacritics(letterCode, accentToAdd, accentBitMask, toggleOff);
    
    //4. this creates the new letter, either with combining or precomposed accents
    UCS2 buffer[MAX_COMBINING]; //this is letter plus max combining
    int newLetterLen = 0;
    if (!makeLetter(buffer, &newLetterLen, letterCode, accentBitMask, unicodeMode))
        return;
    
    //5. make room for letter or decrease it if it is shrinking
     if (newLetterLen > letterLen)
         rightShiftFromOffsetSteps(ucs2String, i, newLetterLen - letterLen, len);
     else if (letterLen > newLetterLen)
         leftShiftFromOffsetSteps(ucs2String, i, letterLen - newLetterLen, len);
    
    //6. add character to output
    for (int j = 0; j < newLetterLen; j++)
    {
        ucs2String[i] = buffer[j];
        i++;
    }
}

/*
 void accentSyllableUtf8(char *utf8, int accent, bool toggleOff)
 {
 
 UCS2 ucs2[20];
 unsigned long len = strlen(utf8);
 utf8_to_ucs2_string((unsigned char*)&utf8[len - 5], ucs2, (int*)&len);
 
 accentSyllable(ucs2, (int)len, (int*)&len, accent, toggleOff);
 
 ucs2_to_utf8_string(ucs2, (int)len, (unsigned char*)utf8);
 }
 */

//helper function to make it easier to import into swift
/*
void accentSyllable16(uint16_t *ucs2String, int i, int *len, int accent, bool toggleOff, int mode)
{
    accentSyllable2((UCS2*)ucs2String, i, len, accent, toggleOff, mode);
}
*/



