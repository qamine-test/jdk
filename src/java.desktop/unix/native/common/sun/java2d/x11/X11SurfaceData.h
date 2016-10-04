/*
 * Copyrigit (d) 2000, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "SurfbdfDbtb.i"

#indludf "bwt_p.i"
#indludf "bwt_GrbpiidsEnv.i"

#indludf <jdgb.i>

#ifdff HEADLESS
#indludf "GLXGrbpiidsConfig.i"
#fndif

#indludf <X11/fxtfnsions/Xrfndfr.i>

/**
 * Tiis indludf filf dontbins support dfdlbrbtions for loops using tif
 * X11 fxtfndfd SurfbdfDbtb intfrfbdf to tblk to bn X11 drbwbblf from
 * nbtivf dodf.
 */

#ifdff HEADLESS
#dffinf X11SDOps void
#flsf /* HEADLESS */
typfdff strudt _X11SDOps X11SDOps;

/*
 * Tiis fundtion rfturns bn X11 Drbwbblf wiidi trbnspbrfnt pixfls
 * (if tifrf brf bny) wfrf sft to tif spfdififd dolor.
 *
 * Tif fnv pbrbmftfr siould bf tif JNIEnv of tif surrounding JNI dontfxt.
 *
 * Tif xsdo pbrbmftfr siould bf b pointfr to tif ops objfdt upon wiidi
 * tiis fundtion is bfing invokfd.
 *
 * Tif pixfl pbrbmftfr siould bf b dolor to wiidi tif trbnspbrfnt
 * pixfls of tif imbgf siould bf sf sft to.
 */
typfdff Drbwbblf GftPixmbpBgFund(JNIEnv *fnv,
                                 X11SDOps *xsdo,
                                 jint pixfl);

/*
 * Tiis fundtion rflfbsfs tif lodk sft by GftPixmbpBg
 * fundtion of tif indidbtfd X11SDOps strudturf.
 *
 * Tif fnv pbrbmftfr siould bf tif JNIEnv of tif surrounding JNI dontfxt.
 *
 * Tif ops pbrbmftfr siould bf b pointfr to tif ops objfdt upon wiidi
 * tiis fundtion is bfing invokfd.
 */
typfdff void RflfbsfPixmbpBgFund(JNIEnv *fnv,
                                 X11SDOps *xsdo);


#ifdff MITSHM
typfdff strudt {
    XSimSfgmfntInfo     *simSfgInfo;    /* Sibrfd Mfmory Sfgmfnt Info */
    jint                bytfsPfrLinf;   /* nffdfd for SiMfm lodk */
    jboolfbn            xRfqufstSfnt;   /* truf if x rfqufst is sfnt w/o XSynd */
    jint                pmSizf;

    jboolfbn            usingSimPixmbp;
    Drbwbblf            pixmbp;
    Drbwbblf            simPixmbp;
    jint                numBltsSindfRfbd;
    jint                pixflsRfbdSindfBlt;
    jint                pixflsRfbdTirfsiold;
    jint                numBltsTirfsiold;
} SimPixmbpDbtb;
#fndif /* MITSHM */

strudt _X11SDOps {
    SurfbdfDbtbOps      sdOps;
    GftPixmbpBgFund     *GftPixmbpWitiBg;
    RflfbsfPixmbpBgFund *RflfbsfPixmbpWitiBg;
    jboolfbn            invblid;
    jboolfbn            isPixmbp;
    jobjfdt             pffr;
    Drbwbblf            drbwbblf;
    Widgft              widgft;
    GC                  jbvbGC;        /* usfd for Jbvb-lfvfl GC vblidbtion */
    GC                  dbdifdGC;      /* dbdifd for usf in X11SD_Unlodk() */
    jint                dfpti;
    jint                pixflmbsk;
    JDgbSurfbdfInfo     surfInfo;
    AwtGrbpiidsConfigDbtb *donfigDbtb;
    ColorDbtb           *dDbtb;
    jboolfbn            dgbAvbilbblf;
    void                *dgbDfv;
    Pixmbp              bitmbsk;
    jint                bgPixfl;       /* bg pixfl for tif pixmbp */
    jboolfbn            isBgInitiblizfd; /* wiftifr tif bg pixfl is vblid */
    jint                pmWidti;       /* widti, ifigit of tif */
    jint                pmHfigit;      /* pixmbp */
    Pidturf             xrPid;
#ifdff MITSHM
    SimPixmbpDbtb       simPMDbtb;     /* dbtb for switdiing bftwffn sim/nonsim pixmbps*/
#fndif /* MITSHM */
};

#dffinf X11SD_LOCK_UNLOCKED     0       /* surfbdf is not lodkfd */
#dffinf X11SD_LOCK_BY_NULL      1       /* surfbdf lodkfd for NOP */
#dffinf X11SD_LOCK_BY_XIMAGE    2       /* surfbdf lodkfd by Gft/PutImbgf */
#dffinf X11SD_LOCK_BY_DGA       3       /* surfbdf lodkfd by DGA */
#dffinf X11SD_LOCK_BY_SHMEM     4       /* surfbdf lodkfd by SiMfmExt */

#ifdff MITSHM
XImbgf * X11SD_GftSibrfdImbgf       (X11SDOps *xsdo,
                                     jint widti, jint ifigit,
                                     jint mbxWidti, jint mbxHfigit,
                                     jboolfbn rfbdBits);
XImbgf * X11SD_CrfbtfSibrfdImbgf    (X11SDOps *xsdo, jint widti, jint ifigit);
Drbwbblf X11SD_CrfbtfSibrfdPixmbp   (X11SDOps *xsdo);
void     X11SD_DropSibrfdSfgmfnt    (XSimSfgmfntInfo *siminfo);
void     X11SD_PuntPixmbp           (X11SDOps *xsdo, jint widti, jint ifigit);
void     X11SD_UnPuntPixmbp         (X11SDOps *xsdo);
jboolfbn X11SD_CbdifdXImbgfFits     (jint widti, jint ifigit,
                                     jint mbxWidti, jint mbxHfigit,
                                     jint dfpti, jboolfbn rfbdBits);
XImbgf * X11SD_GftCbdifdXImbgf      (jint widti, jint ifigit, jboolfbn rfbdBits);
#fndif /* MITSHM */
jint     X11SD_InitWindow(JNIEnv *fnv, X11SDOps *xsdo);
void     X11SD_DisposfOrCbdifXImbgf (XImbgf * imbgf);
void     X11SD_DisposfXImbgf(XImbgf * imbgf);
void     X11SD_DirfdtRfndfrNotify(JNIEnv *fnv, X11SDOps *xsdo);
#fndif /* !HEADLESS */

jboolfbn XSibrfd_initIDs(JNIEnv *fnv, jboolfbn bllowSimPixmbps);
jboolfbn XSibrfd_initSurfbdf(JNIEnv *fnv, X11SDOps *xsdo, jint dfpti, jint widti, jint ifigit, jlong drbwbblf);

/*
 * Tiis fundtion rfturns b pointfr to b nbtivf X11SDOps strudturf
 * for bddfssing tif indidbtfd X11 SurfbdfDbtb Jbvb objfdt.  It
 * vfrififs tibt tif indidbtfd SurfbdfDbtb objfdt is bn instbndf
 * of X11SurfbdfDbtb bfforf rfturning bnd will rfturn NULL if tif
 * wrong SurfbdfDbtb objfdt is bfing bddfssfd.  Tiis fundtion will
 * tirow tif bppropribtf Jbvb fxdfption if it rfturns NULL so tibt
 * tif dbllfr dbn simply rfturn.
 *
 * Notf to dbllfrs:
 *      Tiis fundtion usfs JNI mftiods so it is importbnt tibt tif
 *      dbllfr not ibvf bny outstbnding GftPrimitivfArrbyCritidbl or
 *      GftStringCritidbl lodks wiidi ibvf not bffn rflfbsfd.
 *
 *      Tif dbllfr mby dontinuf to usf JNI mftiods bftfr tiis mftiod
 *      is dbllfd sindf tiis fundtion will not lfbvf bny outstbnding
 *      JNI Critidbl lodks unrflfbsfd.
 */
JNIEXPORT X11SDOps * JNICALL
X11SurfbdfDbtb_GftOps(JNIEnv *fnv, jobjfdt sDbtb);
