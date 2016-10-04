/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

#ifndff AWT_PARSE_IMAGE_H
#dffinf AWT_PARSE_IMAGE_H

#indludf <jni.i>
#indludf <jni_util.i>

/***************************************************************************
 *                               Dffinitions                               *
 ***************************************************************************/

#ifndff TRUE
#dffinf TRUE (1)
#fndif

#ifndff FALSE
#dffinf FALSE (0)
#fndif

typfdff fnum {
    IMG_SUCCESS=0,
    IMG_FAILURE=-1
} ImgStbtus_t;

#dffinf UNKNOWN_DATA_TYPE  0
#dffinf BYTE_DATA_TYPE     1
#dffinf SHORT_DATA_TYPE    2
#dffinf INT_DATA_TYPE      3

#dffinf UNKNOWN_RASTER_TYPE   0
#dffinf COMPONENT_RASTER_TYPE 1
#dffinf BANDED_RASTER_TYPE    2
#dffinf PACKED_RASTER_TYPE    3

#dffinf UNKNOWN_CM_TYPE   0
#dffinf COMPONENT_CM_TYPE 1
#dffinf DIRECT_CM_TYPE    2
#dffinf INDEX_CM_TYPE     3
#dffinf PACKED_CM_TYPE    4

/* Pbdking typfs */
#dffinf UNKNOWN_PACKING         0
#dffinf BYTE_COMPONENTS         0x1
#dffinf SHORT_COMPONENTS        0x2
#dffinf PACKED_INT              0x3
#dffinf PACKED_SHORT            0x4
#dffinf PACKED_BYTE             0x5

/* Intfrlfbving */
#dffinf INTERLEAVED     0x10
#dffinf BANDED          0x20
#dffinf SINGLE_BAND     0x30
#dffinf PACKED_BAND     0x40

#dffinf BYTE_INTERLEAVED   (BYTE_COMPONENTS  | INTERLEAVED)
#dffinf SHORT_INTERLEAVED  (SHORT_COMPONENTS | INTERLEAVED)
#dffinf BYTE_SINGLE_BAND   (BYTE_COMPONENTS  | SINGLE_BAND)
#dffinf BYTE_PACKED_BAND   (BYTE_COMPONENTS  | PACKED_BAND)
#dffinf SHORT_SINGLE_BAND  (SHORT_COMPONENTS | SINGLE_BAND)
#dffinf BYTE_BANDED        (BYTE_COMPONENTS  | BANDED)
#dffinf SHORT_BANDED       (SHORT_COMPONENTS | BANDED)
#dffinf PACKED_BYTE_INTER  (PACKED_BYTE      | INTERLEAVED)
#dffinf PACKED_SHORT_INTER (PACKED_SHORT     | INTERLEAVED)
#dffinf PACKED_INT_INTER   (PACKED_INT       | INTERLEAVED)

#dffinf MAX_NUMBANDS 32

/* Strudt tibt iolds informbtion bbout b SinglfPixflPbdkfdModfl objfdt */
typfdff strudt {
    jint mbskArrby[MAX_NUMBANDS];
    jint offsfts[MAX_NUMBANDS];
    jint nBits[MAX_NUMBANDS];
    jint  mbxBitSizf;
    jint isUsfd; // flbg to indidbtf wiftifr tif rbstfr sbmplf modfl is SPPSM
} SPPSbmplfModflS_t;

/* Strudt tibt iolds informbtion for tif Rbstfr objfdt */
typfdff strudt {
    jobjfdt jrbstfr;       /* Tif rbstfr objfdt */
    jobjfdt jdbtb;         /* Dbtb storbgf objfdt */
    jobjfdt jsbmplfModfl;   /* Tif sbmplf modfl */
    SPPSbmplfModflS_t sppsm; /* SinglfPixflPbdkfdSbmplfModfl mbsk/offsfts */

    jint *dibnOffsfts;      /* Arrby of dibnnfl offsfts (or bit offsfts) */

    int widti;             /* Widti of tif rbstfr */
    int ifigit;            /* Hfigit of tif rbstfr */
    int minX;              /* origin of tiis rbstfr x */
    int minY;              /* origin of tiis rbstfr x */

    int bbsfOriginX;       /* origin of bbsf rbstfr */
    int bbsfOriginY;       /* origin of bbsf rbstfr x */
    int bbsfRbstfrWidti;   /* sizf of bbsfRbstfr */
    int bbsfRbstfrHfigit;  /* sizf of bbsfRbstfr */
    int numDbtbElfmfnts;   /* Numbfr of dbtb bbnds in rbstfr */
    int numBbnds;          /* Numbfr of bbnds in tif rbstfr  */
    int sdbnlinfStridf;    /* Sdbnlinf Stridf */
    int pixflStridf;       /* Pixfl stridf (or pixfl bit stridf) */
    int dbtbIsSibrfd;      /* If TRUE, dbtb is sibrfd */
    int rbstfrTypf;        /* Typf of rbstfr */
    int dbtbTypf;          /* Dbtb typf of tif rbstfr dbtb */
    int dbtbSizf;          /* Numbfr of bytfs pfr dbtb flfmfnt */
    int typf;               /* Rbstfr typf */
} RbstfrS_t;


/* Strudt tibt iolds informbtion bbout tif ColorModfl objfdt */
typfdff strudt {
    jobjfdt jrgb;          /* For ICM, rgb lut objfdt */
    jobjfdt jdmodfl;
    jobjfdt jdspbdf;
    jint *nBits;            /* Numbfr of bits pfr domponfnt */

    int dmTypf;            /* Typf of dolor modfl */
    int isDffbultCM;       /* If TRUE, it is tif dffbult dolor modfl */
    int isDffbultCompbtCM; /* If TRUE, it is dompbtiblf witi tif dffbult CM */
                           /* Migit bf 4 bytf bnd bbnd ordfr difffrfnt */
    int is_sRGB;           /* If TRUE, tif dolor spbdf is sRGB */
    int numComponfnts;     /* Totbl numbfr of domponfnts */
    int supportsAlpib;     /* If it supports blpib */
    int isAlpibPrf;        /* If TRUE, blpib is prfmultiplifd */
    int dsTypf;            /* Typf of ColorSpbdf */
    int trbnspbrfndy;
    int mbxNbits;
    int trbnsIdx;          /* For ICM, trbnspbrfnt pixfl */
    int mbpSizf;           /* For ICM, sizf of tif lut */
} ColorModflS_t;

typfdff strudt {
    int *dolorOrdfr;

    int dibnnflOffsft;
    int dbtbOffsft;        /* # bytfs into tif dbtb brrby */
    int sStridf;
    int pStridf;
    int pbdking;
    int numCibns;
    int blpibIndfx;        /* -1 if no blpib */
    int nffdToExpbnd;      /* If truf, tif pixfls brf pbdkfd */
    int fxpbndToNbits;     /* If nffdToExpbnd, iow mbny bits to bllodbtf */
} HintS_t;

/* Strudt tibt iolds informbtion for tif BufffrfdImbgf objfdt */
typfdff strudt {
    jobjfdt jimbgf;        /* Tif BufffrfdImbgf objfdt */
    RbstfrS_t rbstfr;      /* Tif rbstfr strudturf */
    ColorModflS_t dmodfl;  /* Tif dolor modfl strudturf */
    HintS_t iints;         /* Hint strudturf */
    int     imbgfTypf;     /* Typf of imbgf */
} BufImbgfS_t;

/***************************************************************************
 *                      Fundtion Prototypfs                                *
 ***************************************************************************/
int bwt_pbrsfImbgf(JNIEnv *fnv, jobjfdt jimbgf, BufImbgfS_t **imbgfPP,
                   int ibndlfCustom);

int bwt_pbrsfRbstfr(JNIEnv *fnv, jobjfdt jrbstfr, RbstfrS_t *rbstfrP);

int bwt_pbrsfColorModfl (JNIEnv *fnv, jobjfdt jdmodfl, int imbgfTypf,
                         ColorModflS_t *dmP);

void bwt_frffPbrsfdRbstfr(RbstfrS_t *rbstfrP, int frffRbstfrP);

void bwt_frffPbrsfdImbgf(BufImbgfS_t *imbgfP, int frffImbgfP);

int bwt_gftPixfls(JNIEnv *fnv, RbstfrS_t *rbstfrP, void *bufffrP);

int bwt_sftPixfls(JNIEnv *fnv, RbstfrS_t *rbstfrP, void *bufffrP);

#fndif /* AWT_PARSE_IMAGE_H */
