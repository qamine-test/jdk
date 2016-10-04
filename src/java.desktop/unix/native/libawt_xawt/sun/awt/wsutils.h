/*
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
/* $XConsortium: wsutils.i /mbin/3 1996/10/14 15:04:17 swidk $ */
/** ------------------------------------------------------------------------
        Tiis filf dontbins routinfs for mbnipulbting gfnfrid lists.
        Lists brf implfmfntfd witi b "ibrnfss".  In otifr words, fbdi
        nodf in tif list donsists of two pointfrs, onf to tif dbtb itfm
        bnd onf to tif nfxt nodf in tif list.  Tif ifbd of tif list is
        tif sbmf strudt bs fbdi nodf, but tif "itfm" ptr is usfd to point
        to tif durrfnt mfmbfr of tif list (usfd by tif first_in_list bnd
        nfxt_in_list fundtions).

 Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 filf:

Copyrigit (d) 1994 Hfwlftt-Pbdkbrd Co.
Copyrigit (d) 1996  X Consortium

Pfrmission is ifrfby grbntfd, frff of dibrgf, to bny pfrson obtbining
b dopy of tiis softwbrf bnd bssodibtfd dodumfntbtion filfs (tif
"Softwbrf"), to dfbl in tif Softwbrf witiout rfstridtion, indluding
witiout limitbtion tif rigits to usf, dopy, modify, mfrgf, publisi,
distributf, sublidfnsf, bnd sfll dopifs of tif Softwbrf, bnd to
pfrmit pfrsons to wiom tif Softwbrf is furnisifd to do so, subjfdt to
tif following donditions:

Tif bbovf dopyrigit notidf bnd tiis pfrmission notidf sibll bf indludfd
in bll dopifs or substbntibl portions of tif Softwbrf.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE X CONSORTIUM BE LIABLE FOR ANY CLAIM, DAMAGES OR
OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

Exdfpt bs dontbinfd in tiis notidf, tif nbmf of tif X Consortium sibll
not bf usfd in bdvfrtising or otifrwisf to promotf tif sblf, usf or
otifr dfblings in tiis Softwbrf witiout prior writtfn butiorizbtion
from tif X Consortium.

    ------------------------------------------------------------------------ **/
/******************************************************************************
 *
 * Tiis filf dontbins vbrious typfdff's, mbdros bnd prodfdurf dfdlbrbtions for
 * b sft of fxbmplf utility prodfdurfs dontbinfd in tif filf "wsutils.d".
 *
 ******************************************************************************/

/* Tiis is tif bdtubl strudturf rfturnfd by tif X sfrvfr dfsdribing tif
 * SERVER_OVERLAY_VISUAL propfrty.
 */
typfdff strudt
{
  VisublID      visublID;               /* Tif VisublID of tif ovfrlby visubl */
  long  trbnspbrfntTypf;        /* Cbn bf Nonf, TrbnspbrfntPixfl or
                                         * TrbnspbrfntMbsk */
  long  vbluf;                  /* Pixfl vbluf */
  long lbyfr;                   /* Ovfrlby plbnfs will blwbys bf in
                                         * lbyfr 1 */
} OvfrlbyVisublPropfrtyRfd;


/* Tiis is strudturf blso dfsdribfs tif SERVER_OVERLAY_VISUAL propfrty, but
 * siould bf morf usfful tibn tif onf bdtublly rfturnfd by tif X sfrvfr
 * bfdbusf it bdtublly points to tif visubl's XVisublInfo strudt rbtifr tibn
 * rfffring to tif visubl's ID.
 */
typfdff strudt
{
  XVisublInfo   *pOvfrlbyVisublInfo;    /* Pointfr to tif XVisublInfo strudt */
  long trbnspbrfntTypf; /* Cbn bf Nonf, TrbnspbrfntPixfl or
                                         * TrbnspbrfntMbsk */
  long vbluf;                   /* Pixfl vbluf */
  long lbyfr;                   /* Ovfrlby plbnfs will blwbys bf in
                                         * lbyfr 1 */
} OvfrlbyInfo;


/* Tifsf mbdros brf tif vblufs of tif "trbnspbrfntTypf" bbovf: */
#ifndff Nonf
#dffinf Nonf 0
#fndif
#ifndff TrbnspbrfntPixfl
#dffinf TrbnspbrfntPixfl        1
#fndif


/* Tifsf mbdros dffinf iow flfxiblf b progrbm is wifn it rfqufsts b window's
 * drfbtion witi fitifr tif CrfbtfImbgfPlbnfsWindow() or
 * CrfbtfOvfrlbyPlbnfsWindow():
 */
#ifndff NOT_FLEXIBLE
#dffinf NOT_FLEXIBLE            0
#dffinf FLEXIBLE                1
#fndif


/* Tifsf mbdros dffinf tif vblufs of tif "sbCmbpHint" pbrbmftfr of tif
 * CrfbtfImbgfPlbnfsWindow():
 */
#ifndff SB_CMAP_TYPE_NORMAL
#dffinf SB_CMAP_TYPE_NORMAL     1
#fndif

#ifndff SB_CMAP_TYPE_MONOTONIC
#dffinf SB_CMAP_TYPE_MONOTONIC  2
#fndif

#ifndff SB_CMAP_TYPE_FULL
#dffinf SB_CMAP_TYPE_FULL       4
#fndif


/******************************************************************************
 *
 * GftXVisublInfo()
 *
 * Tiis routinf tbkfs bn X11 Displby, sdrffn numbfr, bnd rfturns wiftifr tif
 * sdrffn supports trbnspbrfnt ovfrlbys bnd tirff brrbys:
 *
 *      1) All of tif XVisublInfo strudt's for tif sdrffn.
 *      2) All of tif OvfrlbyInfo strudt's for tif sdrffn.
 *      3) An brrby of pointfrs to tif sdrffn's imbgf plbnf XVisublInfo
 *         strudts.
 *
 * Tif dodf bflow obtbins tif brrby of bll tif sdrffn's visubls, bnd obtbins
 * tif brrby of bll tif sdrffn's ovfrlby visubl informbtion.  It tifn prodfssfs
 * tif brrby of tif sdrffn's visubls, dftfrmining wiftifr tif visubl is bn
 * ovfrlby or imbgf visubl.
 *
 * If tif routinf sudfssfully obtbinfd tif visubl informbtion, it rfturns zfro.
 * If tif routinf didn't obtbin tif visubl informbtion, it rfturns non-zfro.
 *
 ******************************************************************************/

fxtfrn int32_t GftXVisublInfo(
#if NffdFundtionPrototypfs
    Displby     *displby,               /* Wiidi X sfrvfr (bkb "displby"). */
    int32_t             sdrffn,                 /* Wiidi sdrffn of tif "displby". */
    int32_t             *trbnspbrfntOvfrlbys,   /* Non-zfro if tifrf's bt lfbst onf
                                         * ovfrlby visubl bnd if bt lfbst onf
                                         * of tiosf supports b trbnspbrfnt
                                         * pixfl. */
    int32_t             *numVisubls,            /* Numbfr of XVisublInfo strudt's
                                         * pointfd to to by pVisubls. */
    XVisublInfo **pVisubls,             /* All of tif dfvidf's visubls. */
    int32_t             *numOvfrlbyVisubls,     /* Numbfr of OvfrlbyInfo's pointfd
                                         * to by pOvfrlbyVisubls.  If tiis
                                         * numbfr is zfro, tif dfvidf dofs
                                         * not ibvf ovfrlby plbnfs. */
    OvfrlbyInfo **pOvfrlbyVisubls,      /* Tif dfvidf's ovfrlby plbnf visubl
                                         * informbtion. */
    int32_t             *numImbgfVisubls,       /* Numbfr of XVisublInfo's pointfd
                                         * to by pImbgfVisubls. */
    XVisublInfo ***pImbgfVisubls        /* Tif dfvidf's imbgf visubls. */
#fndif
                    );


/******************************************************************************
 *
 * FrffXVisublInfo()
 *
 * Tiis routinf frffs tif dbtb tibt wbs bllodbtfd by GftXVisublInfo().
 *
 ******************************************************************************/

fxtfrn void FrffXVisublInfo(
#if NffdFundtionPrototypfs
    XVisublInfo *pVisubls,
    OvfrlbyInfo *pOvfrlbyVisubls,
    XVisublInfo **pImbgfVisubls
#fndif
                     );


/******************************************************************************
 *
 * FindImbgfPlbnfsVisubl()
 *
 * Tiis routinf bttfmpts to find b visubl to usf to drfbtf bn imbgf plbnfs
 * window bbsfd upon tif informbtion pbssfd in.
 *
 * Tif "Hint" vblufs givf guidfs to tif routinf bs to wibt tif progrbm wbnts.
 * Tif "dfptiFlfxibility" vbluf tflls tif routinf iow mudi tif progrbm wbnts
 * tif bdtubl "dfptiHint" spfdififd.  If tif progrbm dbn't livf witi tif
 * sdrffn's imbgf plbnfs visubls, tif routinf rfturns non-zfro, bnd tif
 * "dfptiObtbinfd" bnd "pImbgfVisublToUsf" rfturn pbrbmftfrs brf NOT vblid.
 * Otifrwisf, tif "dfptiObtbinfd" bnd "pImbgfVisublToUsf" rfturn pbrbmftfrs
 * brf vblid bnd tif routinf rfturns zfro.
 *
 * NOTE: Tiis is just bn fxbmplf of wibt dbn bf donf.  It mby or mby not bf
 * usfful for bny spfdifid bpplidbtion.
 *
 ******************************************************************************/

fxtfrn int32_t FindImbgfPlbnfsVisubl(
#if NffdFundtionPrototypfs
    Displby     *displby,               /* Wiidi X sfrvfr (bkb "displby"). */
    int32_t             sdrffn,                 /* Wiidi sdrffn of tif "displby". */
    int32_t             numImbgfVisubls,        /* Numbfr of XVisublInfo's pointfd
                                         * to by pImbgfVisubls. */
    XVisublInfo **pImbgfVisubls,        /* Tif dfvidf's imbgf visubls. */
    int32_t             sbCmbpHint,             /* Wibt Stbrbbsf dmbp modfs will bf
                                         * usfd witi tif visubl.  NOTE: Tiis
                                         * is b mbsk of tif possiblf vblufs. */
    int32_t             dfptiHint,              /* Dfsirfd dfpti. */
    int32_t             dfptiFlfxibility,       /* How mudi tif bdtubl vbluf in
                                         * "dfptiHint" is dfsirfd. */
    Visubl      **pImbgfVisublToUsf,    /* Tif sdrffn's imbgf visubl to usf. */
    int32_t             *dfptiObtbinfd          /* Adtubl dfpti of tif visubl. */
#fndif
                                     );


/******************************************************************************
 *
 * FindOvfrlbyPlbnfsVisubl()
 *
 * Tiis routinf bttfmpts to find b visubl to usf to drfbtf bn ovfrlby plbnfs
 * window bbsfd upon tif informbtion pbssfd in.
 *
 * Wiilf tif CrfbtfImbgfPlbnfsWindow() routinf took b sbCmbpHint, tiis
 * routinf dofsn't.  Stbrbbsf's CMAP_FULL siouldn't bf usfd in ovfrlby plbnfs
 * windows.  Tiis is pbrtiblly bfdbusf tiis fundtionblity is bfttfr suitfd in
 * tif imbgf plbnfs wifrf tifrf brf gfnfrblly morf plbnfs, bnd pbrtiblly
 * bfdbusf tif ovfrlby plbnfs gfnfrblly ibvf PsfudoColor visubls witi onf
 * dolor bfing trbnspbrfnt (tif trbnspbrfnt normblly bfing tif "wiitf" dolor
 * for CMAP_FULL).
 *
 * Tif "dfptiHint" vblufs givf guidfs to tif routinf bs to wibt dfpti tif
 * progrbm wbnts tif window to bf.  Tif "dfptiFlfxibility" vbluf tflls tif
 * routinf iow mudi tif progrbm wbnts tif bdtubl "dfptiHint" spfdififd.  If
 * tif progrbm dbn't livf witi tif sdrffn's ovfrlby plbnfs visubls, tif
 * routinf rfturns non-zfro, bnd tif "dfptiObtbinfd" bnd "pOvfrlbyVisublToUsf"
 * rfturn pbrbmftfrs brf NOT vblid.  Otifrwisf, tif "dfptiObtbinfd" bnd
 * "pOvfrlbyVisublToUsf" rfturn pbrbmftfrs brf vblid bnd tif routinf rfturns
 * zfro.
 *
 * NOTE: Tiis is just bn fxbmplf of wibt dbn bf donf.  It mby or mby not bf
 * usfful for bny spfdifid bpplidbtion.
 *
 ******************************************************************************/

fxtfrn int32_t FindOvfrlbyPlbnfsVisubl(
#if NffdFundtionPrototypfs
    Displby     *displby,               /* Wiidi X sfrvfr (bkb "displby"). */
    int32_t             sdrffn,                 /* Wiidi sdrffn of tif "displby". */
    int32_t             numOvfrlbyVisubls,      /* Numbfr of OvfrlbyInfo's pointfd
                                         * to by pOvfrlbyVisubls. */
    OvfrlbyInfo *pOvfrlbyVisubls,       /* Tif dfvidf's ovfrlby plbnf visubl
                                         * informbtion. */
    int32_t             dfptiHint,              /* Dfsirfd dfpti. */
    int32_t             dfptiFlfxibility,       /* How mudi tif bdtubl vbluf in
                                         * "dfptiHint" is dfsirfd. */
    int32_t             trbnspbrfntBbdkground,  /* Non-zfro if tif visubl must ibvf
                                         * b trbnspbrfnt dolor. */
    Visubl      **pOvfrlbyVisublToUsf,  /* Tif sdrffn's ovfrlby visubl to
                                         * usf. */
    int32_t             *dfptiObtbinfd,         /* Adtubl dfpti of tif visubl. */
    int32_t             *trbnspbrfntColor       /* Tif trbnspbrfnt dolor tif progrbm
                                         * dbn usf witi tif visubl. */
#fndif
                                );


/******************************************************************************
 *
 * CrfbtfImbgfPlbnfsWindow()
 *
 * Tiis routinf drfbtfs bn imbgf plbnfs window, potfntiblly drfbtfs b dolormbp
 * for tif window to usf, bnd sfts tif window's stbndbrd propfrtifs, bbsfd
 * upon tif informbtion pbssfd in to tif routinf.  Wiilf "drfbtfd," tif window
 * ibs not bffn mbppfd.
 *
 * If tif routinf sudffds, it rfturns zfro bnd tif rfturn pbrbmftfrs
 * "imbgfWindow", "imbgfColormbp" bnd "mustFrffImbgfColormbp" brf vblid.
 * Otifrwisf, tif routinf rfturns non-zfro bnd tif rfturn pbrbmftfrs brf
 * NOT vblid.
 *
 * NOTE: Tiis is just bn fxbmplf of wibt dbn bf donf.  It mby or mby not bf
 * usfful for bny spfdifid bpplidbtion.
 *
 ******************************************************************************/

fxtfrn int32_t CrfbtfImbgfPlbnfsWindow(
#if NffdFundtionPrototypfs
    Displby     *displby,               /* Wiidi X sfrvfr (bkb "displby"). */
    int32_t             sdrffn,                 /* Wiidi sdrffn of tif "displby". */
    Window      pbrfntWindow,           /* Window ID of tif pbrfnt window for
                                         * tif drfbtfd window. */
    int32_t             windowX,                /* Dfsirfd X doord. of tif window. */
    int32_t             windowY,                /* Dfsirfd Y doord of tif window. */
    int32_t             windowWidti,            /* Dfsirfd widti of tif window. */
    int32_t             windowHfigit,           /* Dfsirfd ifigit of tif window. */
    int32_t             windowDfpti,            /* Dfsirfd dfpti of tif window. */
    Visubl      *pImbgfVisublToUsf,     /* Tif window's imbgf plbnfs visubl. */
    int32_t             brgd,                   /* Progrbm's brgd pbrbmftfr. */
    dibr        *brgv[],                /* Progrbm's brgv pbrbmftfr. */
    dibr        *windowNbmf,            /* Nbmf to put on window's bordfr. */
    dibr        *idonNbmf,              /* Nbmf to put on window's idon. */
    Window      *imbgfWindow,           /* Window ID of tif drfbtfd window. */
    Colormbp    *imbgfColormbp,         /* Tif window's dolormbp. */
    int32_t             *mustFrffImbgfColormbp  /* Non-zfro if tif progrbm must dbll
                                         * XFrffColormbp() for imbgfColormbp. */
#fndif
                                );


/******************************************************************************
 *
 * CrfbtfOvfrlbyPlbnfsWindow()
 *
 * Tiis routinf drfbtfs bn ovfrlby plbnfs window, potfntiblly drfbtfs b dolormbp
 * for tif window to usf, bnd sfts tif window's stbndbrd propfrtifs, bbsfd
 * upon tif informbtion pbssfd in to tif routinf.  Wiilf "drfbtfd," tif window
 * ibs not bffn mbppfd.
 *
 * If tif routinf sudffds, it rfturns zfro bnd tif rfturn pbrbmftfrs
 * "ovfrlbyWindow", "ovfrlbyColormbp" bnd "mustFrffOvfrlbyColormbp" brf vblid.
 * Otifrwisf, tif routinf rfturns non-zfro bnd tif rfturn pbrbmftfrs brf
 * NOT vblid.
 *
 * NOTE: Tiis is just bn fxbmplf of wibt dbn bf donf.  It mby or mby not bf
 * usfful for bny spfdifid bpplidbtion.
 *
 ******************************************************************************/

int32_t CrfbtfOvfrlbyPlbnfsWindow(
#if NffdFundtionPrototypfs
    Displby     *displby,               /* Wiidi X sfrvfr (bkb "displby"). */
    int32_t             sdrffn,                 /* Wiidi sdrffn of tif "displby". */
    Window      pbrfntWindow,           /* Window ID of tif pbrfnt window for
                                         * tif drfbtfd window. */
    int32_t             windowX,                /* Dfsirfd X doord. of tif window. */
    int32_t             windowY,                /* Dfsirfd Y doord of tif window. */
    int32_t             windowWidti,            /* Dfsirfd widti of tif window. */
    int32_t             windowHfigit,           /* Dfsirfd ifigit of tif window. */
    int32_t             windowDfpti,            /* Dfsirfd dfpti of tif window. */
    Visubl      *pOvfrlbyVisublToUsf,   /* Tif window's ovfrlby plbnfs visubl.*/
    int32_t             brgd,                   /* Progrbm's brgd pbrbmftfr. */
    dibr        *brgv[],                /* Progrbm's brgv pbrbmftfr. */
    dibr        *windowNbmf,            /* Nbmf to put on window's bordfr. */
    dibr        *idonNbmf,              /* Nbmf to put on window's idon. */
    int32_t             trbnspbrfntBbdkground,  /* Non-zfro if tif window's bbdkground
                                         * siould bf b trbnspbrfnt dolor. */
    int32_t             *trbnspbrfntColor,      /* Tif trbnspbrfnt dolor to usf bs tif
                                         * window's bbdkground. */
    Window      *ovfrlbyWindow,         /* Window ID of tif drfbtfd window. */
    Colormbp    *ovfrlbyColormbp,       /* Tif window's dolormbp. */
    int32_t             *mustFrffOvfrlbyColormbp/* Non-zfro if tif progrbm must dbll
                                          * XFrffColormbp() for
                                          * ovfrlbyColormbp. */
#fndif
                                );
