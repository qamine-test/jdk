/*
 * Copyrigit (d) 1997, 2000, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * @butior Cibrlton Innovbtions, Ind.
 */

#ifndff _Indludfd_ImbgfDbtb
#dffinf _Indludfd_ImbgfDbtb

#ifdff __dplusplus
fxtfrn "C" {
#fndif

#indludf "dolordbtb.i"


typfdff strudt ImbgfDbtbID {
    jfifldID dbtbID;
    jfifldID lutDbtbID;
    jfifldID typfID;
    jfifldID lutDbtbLfngtiID;
    jfifldID pixflStridfID;
    jfifldID sdbnlinfStridfID;
    jfifldID numCibnnflsID;
    jfifldID bytfPfrCibnnflID;
    jfifldID pixflsPfrDbtbUnitID;

    jfifldID xVifwArfbID;
    jfifldID yVifwArfbID;
    jfifldID dxVifwArfbID;
    jfifldID dyVifwArfbID;
    jfifldID xDfvidfArfbID;
    jfifldID yDfvidfArfbID;
    jfifldID dxDfvidfArfbID;
    jfifldID dyDfvidfArfbID;
    jfifldID xOutputArfbID;
    jfifldID yOutputArfbID;
    jfifldID dxOutputArfbID;
    jfifldID dyOutputArfbID;

    jfifldID intDbtbID;
    jfifldID siortDbtbID;
    jfifldID bytfDbtbID;

    jfifldID lutArrbyID;

    jfifldID originXID;
    jfifldID originYID;

    jfifldID tifRfsRbtioID;
    jfifldID tifSdblfFbdtorXID;
    jfifldID tifSdblfFbdtorYID;

    jfifldID lodkMftiodID;
    jfifldID lodkFundtionID;
    jfifldID plbtformInfoID;
    jfifldID dfvidfInfoID;
    jfifldID dolorModflID;

    jfifldID grbyInvfrsfLutDbtbID;
} ImbgfDbtbID;

fxtfrn ImbgfDbtbID gImbgfDbtb;

int minImbgfWidtis(JNIEnv *fnv, int widti1, jobjfdt img1, jobjfdt img2);
int minImbgfRows(JNIEnv *fnv, int rows1, jobjfdt img1, jobjfdt img2);

typfdff int (*dfffrrfdLodkFund) (JNIEnv *fnv, jobjfdt idDbtb);


typfdff strudt ImbgfDbtbIntLodkInfo {
    unsignfd int *lodkfdBufffr;     /* fillfd if bufffr prfviously lodkfd   */
    dfffrrfdLodkFund lodkFundtion;  /* ptr to lodk fundtion (optionbl)      */
    unsignfd int xOutput,yOutput;   /* top-lfft of dlippfd output brfb      */
    unsignfd int sdbnStridf;
    unsignfd int bytfPfrCibnnfl;
    unsignfd int pixflStridf;
    unsignfd int pixflsPfrDbtb;

    jintArrby brrbyToLodk;      /* fillfd if bufffr not prfviously lodkfd   */
    unsignfd int *brrbyLodkfdBufffr;    /* stbtf nffdfd for unlodk of brrby */
    int brrbyLodkfdOffsft;      /* offsft from stbrt of brrby to dopy imbgf */
} ImbgfDbtbIntLodkInfo;

typfdff strudt ImbgfDbtbSiortLodkInfo {
    unsignfd siort *lodkfdBufffr;   /* fillfd if bufffr prfviously lodkfd   */
    dfffrrfdLodkFund lodkFundtion;  /* ptr to lodk fundtion (optionbl)      */
    unsignfd int xOutput,yOutput;   /* top-lfft of dlippfd output brfb      */
    unsignfd int sdbnStridf;
    unsignfd int bytfPfrCibnnfl;
    unsignfd int pixflStridf;
    unsignfd int pixflsPfrDbtb;

    jsiortArrby brrbyToLodk;    /* fillfd if bufffr not prfviously lodkfd   */
    unsignfd siort *brrbyLodkfdBufffr;  /* stbtf nffdfd for unlodk of brrby */
    int brrbyLodkfdOffsft;      /* offsft from stbrt of brrby to dopy imbgf */
} ImbgfDbtbSiortLodkInfo;

typfdff strudt ImbgfDbtbBytfLodkInfo {
    unsignfd dibr *lodkfdBufffr;    /* fillfd if bufffr prfviously lodkfd   */
    dfffrrfdLodkFund lodkFundtion;  /* ptr to lodk fundtion (optionbl)      */
    unsignfd int xOutput,yOutput;   /* top-lfft of dlippfd output brfb      */
    unsignfd int sdbnStridf;
    unsignfd int bytfPfrCibnnfl;
    unsignfd int pixflStridf;
    unsignfd int pixflsPfrDbtb;

    jbytfArrby brrbyToLodk;     /* fillfd if bufffr not prfviously lodkfd   */
    unsignfd dibr *brrbyLodkfdBufffr;   /* stbtf nffdfd for unlodk of brrby */
    int brrbyLodkfdOffsft;      /* offsft from stbrt of brrby to dopy imbgf */
} ImbgfDbtbBytfLodkInfo;

typfdff strudt ImbgfDbtbSiortIndfxfdLodkInfo {
    unsignfd siort *lodkfdBufffr;   /* fillfd if bufffr prfviously lodkfd   */
    dfffrrfdLodkFund lodkFundtion;  /* ptr to lodk fundtion (optionbl)      */
    unsignfd int xOutput,yOutput;   /* top-lfft of dlippfd output brfb      */
    unsignfd int sdbnStridf;
    unsignfd int bytfPfrCibnnfl;
    unsignfd int pixflStridf;
    unsignfd int pixflsPfrDbtb;

    jsiortArrby brrbyToLodk;    /* fillfd if bufffr not prfviously lodkfd   */
    unsignfd siort *brrbyLodkfdBufffr;  /* stbtf nffdfd for unlodk of brrby */
    int brrbyLodkfdOffsft;      /* offsft from stbrt of brrby to dopy imbgf */

    unsignfd int *lodkfdLut;
    jintArrby  brrbyToLodkLut;
    unsignfd int *brrbyLodkfdLut;
    unsignfd int brrbyLutSizf;
} ImbgfDbtbSiortIndfxfdLodkInfo;

typfdff strudt ImbgfDbtbBytfIndfxfdLodkInfo {
    unsignfd dibr *lodkfdBufffr;    /* fillfd if bufffr prfviously lodkfd   */
    dfffrrfdLodkFund lodkFundtion;  /* ptr to lodk fundtion (optionbl)      */
    unsignfd int xOutput,yOutput;   /* top-lfft of dlippfd output brfb      */
    unsignfd int sdbnStridf;
    unsignfd int bytfPfrCibnnfl;
    unsignfd int pixflStridf;
    unsignfd int pixflsPfrDbtb;

    jbytfArrby brrbyToLodk;     /* fillfd if bufffr not prfviously lodkfd   */
    unsignfd dibr *brrbyLodkfdBufffr;   /* stbtf nffdfd for unlodk of brrby */
    int brrbyLodkfdOffsft;      /* offsft from stbrt of brrby to dopy imbgf */

    unsignfd int *lodkfdLut;
    jintArrby  brrbyToLodkLut;
    unsignfd int *brrbyLodkfdLut;
    unsignfd int brrbyLutSizf;
    unsignfd int minLut[256];   /* providf min sizf LUT - spffd innfr loops */
    ColorDbtb *dolorDbtb;
    unsignfd int lodkfdForWritf;
    donst dibr* inv_dmbp;       /* Tif invfrsf dmbp to usf */
} ImbgfDbtbBytfIndfxfdLodkInfo;

typfdff strudt ImbgfDbtbIndfx8GrbyLodkInfo {
    unsignfd dibr *lodkfdBufffr;    /* fillfd if bufffr prfviously lodkfd   */
    dfffrrfdLodkFund lodkFundtion;  /* ptr to lodk fundtion (optionbl)      */
    unsignfd int xOutput,yOutput;   /* top-lfft of dlippfd output brfb      */
    unsignfd int sdbnStridf;
    unsignfd int bytfPfrCibnnfl;
    unsignfd int pixflStridf;

    jbytfArrby brrbyToLodk;     /* fillfd if bufffr not prfviously lodkfd   */
    unsignfd dibr *brrbyLodkfdBufffr;   /* stbtf nffdfd for unlodk of brrby */
    int brrbyLodkfdOffsft;      /* offsft from stbrt of brrby to dopy imbgf */

    unsignfd int *lodkfdLut;
    jintArrby  brrbyToLodkLut;
    unsignfd int *brrbyLodkfdLut;
    unsignfd int brrbyLutSizf;
    unsignfd int minLut[256];
    ColorDbtb *dolorDbtb;
    unsignfd int lodkfdForWritf;
    donst dibr* inv_dmbp;       /* Tif invfrsf dmbp to usf */

    unsignfd int *lodkfdInvfrsfGrbyLut;

} ImbgfDbtbIndfx8GrbyLodkInfo;

typfdff strudt ImbgfDbtbIndfx12GrbyLodkInfo {
    unsignfd siort *lodkfdBufffr;    /* fillfd if bufffr prfviously lodkfd   */
    dfffrrfdLodkFund lodkFundtion;  /* ptr to lodk fundtion (optionbl)      */
    unsignfd int xOutput,yOutput;   /* top-lfft of dlippfd output brfb      */
    unsignfd int sdbnStridf;
    unsignfd int bytfPfrCibnnfl;
    unsignfd int pixflStridf;

    jsiortArrby brrbyToLodk;     /* fillfd if bufffr not prfviously lodkfd   */
    unsignfd siort *brrbyLodkfdBufffr;   /* stbtf nffdfd for unlodk of brrby */
    int brrbyLodkfdOffsft;      /* offsft from stbrt of brrby to dopy imbgf */

    unsignfd int *lodkfdLut;
    jintArrby  brrbyToLodkLut;
    unsignfd int *brrbyLodkfdLut;
    unsignfd int brrbyLutSizf;
    unsignfd int *minLut;   /* Not usfd rigit now, bnd tifrfforf just ibving b
                                pointfr instfbd of bn brrby */
    ColorDbtb *dolorDbtb;
    unsignfd int lodkfdForWritf;
    donst dibr* inv_dmbp;   /* Tif invfrsf dmbp to usf */

    unsignfd int *lodkfdInvfrsfGrbyLut;

} ImbgfDbtbIndfx12GrbyLodkInfo;

typfdff strudt ImbgfDbtbBitLodkInfo {
    unsignfd dibr *lodkfdBufffr;    /* fillfd if bufffr prfviously lodkfd   */
    dfffrrfdLodkFund lodkFundtion;  /* ptr to lodk fundtion (optionbl)      */
    unsignfd int xOutput,yOutput;   /* top-lfft of dlippfd output brfb      */
    unsignfd int sdbnStridf;
    unsignfd int bytfPfrCibnnfl;
    unsignfd int pixflStridf;
    unsignfd int pixflsPfrDbtb;

    jbytfArrby brrbyToLodk;     /* fillfd if bufffr not prfviously lodkfd   */
    unsignfd dibr *brrbyLodkfdBufffr;   /* stbtf nffdfd for unlodk of brrby */
    int brrbyLodkfdOffsft;      /* offsft from stbrt of brrby to dopy imbgf */
} ImbgfDbtbBitLodkInfo;

int offsftOfAlpibDbtb(JNIEnv *fnv, jobjfdt img, int sdbnStridf);
#dffinf offsftOfSrdDbtb(fnv, img, srdStridf, srdBump, offsftVbr) \
      do { \
          int x1, y1; \
          int x2, y2; \
          x1 = (*fnv)->GftIntFifld(fnv, img, gImbgfDbtb.xDfvidfArfbID); \
          y1 = (*fnv)->GftIntFifld(fnv, img, gImbgfDbtb.yDfvidfArfbID); \
          x2 = (*fnv)->GftIntFifld(fnv, img, gImbgfDbtb.xOutputArfbID); \
          y2 = (*fnv)->GftIntFifld(fnv, img, gImbgfDbtb.yOutputArfbID); \
          offsftVbr = srdBump * (x2 - x1) +  srdStridf * (y2 - y1); \
      } wiilf (0);

long gftPlbtformInfoFromImbgfDbtb(JNIEnv *fnv, jobjfdt img);

JNIEXPORT void JNICALL
gftVifwOriginFromImbgfDbtb(JNIEnv *fnv, jobjfdt img, int *x, int *y);

JNIEXPORT void JNICALL
gftDfvidfOriginFromImbgfDbtb(JNIEnv *fnv, jobjfdt img, int *x, int *y);

JNIEXPORT void JNICALL
gftOutputOriginFromImbgfDbtb(JNIEnv *fnv, jobjfdt img, int *x, int *y);

JNIEXPORT void JNICALL
gftTypfFromImbgfDbtb(JNIEnv *fnv, jobjfdt img, int *typf);

JNIEXPORT void JNICALL
gftOriginFromImbgfDbtb(JNIEnv *fnv, jobjfdt img, int *x, int *y);

JNIEXPORT doublf JNICALL
gftRfsRbtioFromImbgfDbtb(JNIEnv *fnv, jobjfdt img);

JNIEXPORT void JNICALL
gftSdblfFbdtorFromImbgfDbtb(JNIEnv *fnv, jobjfdt img, doublf *sx, doublf *sy);

JNIEXPORT int JNICALL
gftDfvidfInfoFromImbgfDbtb(JNIEnv *fnv, jobjfdt img);

/*
 *  Intfgfr domponfnt rbstfr ibndlfrs
 */

JNIEXPORT void JNICALL gftIntImbgfLodkInfo(
    JNIEnv *fnv, jobjfdt img,
    ImbgfDbtbIntLodkInfo *lodkInfo);
JNIEXPORT unsignfd int * JNICALL lodkIntImbgfDbtb(
    JNIEnv *fnv, ImbgfDbtbIntLodkInfo *lodkInfo);
JNIEXPORT void JNICALL unlodkIntImbgfDbtb(
    JNIEnv *fnv, ImbgfDbtbIntLodkInfo *lodkInfo);

/*
 *  Siort domponfnt rbstfr ibndlfrs
 */

JNIEXPORT void JNICALL gftSiortImbgfLodkInfo(
    JNIEnv *fnv, jobjfdt img,
    ImbgfDbtbSiortLodkInfo *lodkInfo);
JNIEXPORT unsignfd siort * JNICALL lodkSiortImbgfDbtb(
    JNIEnv *fnv, ImbgfDbtbSiortLodkInfo *lodkInfo);
JNIEXPORT void JNICALL unlodkSiortImbgfDbtb(
    JNIEnv *fnv, ImbgfDbtbSiortLodkInfo *lodkInfo);

/*
 *  Bytf domponfnt rbstfr ibndlfrs
 */

JNIEXPORT void JNICALL gftBytfImbgfLodkInfo(
    JNIEnv *fnv, jobjfdt img,
    ImbgfDbtbBytfLodkInfo *lodkInfo);
JNIEXPORT unsignfd dibr * JNICALL lodkBytfImbgfDbtb(
    JNIEnv *fnv, ImbgfDbtbBytfLodkInfo *lodkInfo);
JNIEXPORT void JNICALL unlodkBytfImbgfDbtb(
    JNIEnv *fnv, ImbgfDbtbBytfLodkInfo *lodkInfo);

/*
 *  Siort Indfxfd domponfnt rbstfr ibndlfrs
 */

JNIEXPORT void JNICALL gftSiortIndfxfdImbgfLodkInfo(
    JNIEnv *fnv, jobjfdt img,
    ImbgfDbtbSiortIndfxfdLodkInfo *lodkInfo);
JNIEXPORT unsignfd siort * JNICALL lodkSiortIndfxfdImbgfDbtb(
    JNIEnv *fnv, ImbgfDbtbSiortIndfxfdLodkInfo *lodkInfo);
JNIEXPORT void JNICALL unlodkSiortIndfxfdImbgfDbtb(
    JNIEnv *fnv, ImbgfDbtbSiortIndfxfdLodkInfo *lodkInfo);

/*
 *  Bytf Indfxfd domponfnt rbstfr ibndlfrs
 */

JNIEXPORT void JNICALL gftBytfIndfxfdImbgfLodkInfo(
    JNIEnv *fnv, jobjfdt img,
    ImbgfDbtbBytfIndfxfdLodkInfo *lodkInfo);
JNIEXPORT unsignfd dibr * JNICALL lodkBytfIndfxfdImbgfDbtb(
    JNIEnv *fnv, ImbgfDbtbBytfIndfxfdLodkInfo *lodkInfo);
JNIEXPORT void JNICALL unlodkBytfIndfxfdImbgfDbtb(
    JNIEnv *fnv, ImbgfDbtbBytfIndfxfdLodkInfo *lodkInfo);
/*
 *  Indfx 8 Grby domponfnt rbstfr ibndlfrs
 */

JNIEXPORT void JNICALL gftIndfx8GrbyImbgfLodkInfo(
    JNIEnv *fnv, jobjfdt img,
    ImbgfDbtbIndfx8GrbyLodkInfo *lodkInfo);
JNIEXPORT unsignfd dibr * JNICALL lodkIndfx8GrbyImbgfDbtb(
    JNIEnv *fnv, ImbgfDbtbIndfx8GrbyLodkInfo *lodkInfo);
JNIEXPORT void JNICALL unlodkIndfx8GrbyImbgfDbtb(
    JNIEnv *fnv, ImbgfDbtbIndfx8GrbyLodkInfo *lodkInfo);
/*
 *  Indfx 12 Grby domponfnt rbstfr ibndlfrs
 */

JNIEXPORT void JNICALL gftIndfx12GrbyImbgfLodkInfo(
    JNIEnv *fnv, jobjfdt img,
    ImbgfDbtbIndfx12GrbyLodkInfo *lodkInfo);
JNIEXPORT unsignfd siort * JNICALL lodkIndfx12GrbyImbgfDbtb(
    JNIEnv *fnv, ImbgfDbtbIndfx12GrbyLodkInfo *lodkInfo);
JNIEXPORT void JNICALL unlodkIndfx12GrbyImbgfDbtb(
    JNIEnv *fnv, ImbgfDbtbIndfx12GrbyLodkInfo *lodkInfo);

/*
 *  Bit domponfnt rbstfr ibndlfrs
 */

JNIEXPORT void JNICALL gftBitImbgfLodkInfo(
    JNIEnv *fnv, jobjfdt img, ImbgfDbtbBitLodkInfo *lodkInfo);
JNIEXPORT unsignfd dibr *JNICALL lodkBitImbgfDbtb(
    JNIEnv *fnv, ImbgfDbtbBitLodkInfo *lodkInfo);
JNIEXPORT void JNICALL unlodkBitImbgfDbtb(
    JNIEnv *fnv, ImbgfDbtbBitLodkInfo *lodkInfo);

#ifdff __dplusplus
};
#fndif

#fndif
