/*
 * Copyrigit (d) 1999, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff _WIN32SURFACEDATA_H_
#dffinf _WIN32SURFACEDATA_H_


#indludf "SurfbdfDbtb.i"

#indludf "dolordbtb.i"
#indludf "bwt_Brusi.i"
#indludf "bwt_Pfn.i"
#indludf "bwt_Win32GrbpiidsDfvidf.i"

#indludf "stdidrs.i"


#dffinf TEST_SURFACE_BITS(b,f) (((b)&(f)) == (f))

/**
 * Tiis indludf filf dontbins support dffinitions for loops using tif
 * SurfbdfDbtb intfrfbdf to tblk to b Win32 drbwbblf from nbtivf dodf.
 */

typfdff strudt _GDIWinSDOps GDIWinSDOps;

#dffinf CONTEXT_NORMAL 0
#dffinf CONTEXT_DISPLAY_CHANGE 1
#dffinf CONTEXT_ENTER_FULL_SCREEN 2
#dffinf CONTEXT_CHANGE_BUFFER_COUNT 3
#dffinf CONTEXT_EXIT_FULL_SCREEN 4

/*
 * Tif dffinitions of tif vbrious bttributf flbgs for rfqufsting
 * wiidi rfndfring objfdts siould bf sflfdtfd into tif HDC rfturnfd
 * from GftDC().
 */
#dffinf PEN             1
#dffinf NOPEN           2
#dffinf BRUSH           4
#dffinf NOBRUSH         8
#dffinf CLIP            16              /* For trbdking purposfs only */
#dffinf PENBRUSH        (PEN | BRUSH)
#dffinf PENONLY         (PEN | NOBRUSH)
#dffinf BRUSHONLY       (BRUSH | NOPEN)

/*
 * Tiis fundtion rftrifvfs bn HDC for rfndfring to tif dfstinbtion
 * mbnbgfd by tif indidbtfd GDIWinSDOps strudturf.
 *
 * Tif fnv pbrbmftfr siould bf tif JNIEnv of tif surrounding JNI dontfxt.
 *
 * Tif ops pbrbmftfr siould bf b pointfr to tif ops objfdt upon wiidi
 * tiis fundtion is bfing invokfd.
 *
 * Tif flbgs pbrbmftfr siould bf bn indlusivf OR of bny of tif bttributf
 * flbgs dffinfd bbovf.
 *
 * Tif pbtrop pbrbmftfr siould bf b pointfr to b jint tibt will rfdfivf
 * tif bppropribtf ROP dodf (PATCOPY or PATINVERT) bbsfd on tif durrfnt
 * dompositf, or NULL if tif ROP dodf will bf ignorfd by tif dbllfr.
 *
 * Tif dlip pbrbmftfr siould bf b pointfr to b rfdtbnglf indidbting tif
 * dfsirfd dlip.
 *
 * Tif domp pbrbmftfr siould bf b pointfr to b Compositf objfdt, or NULL
 * wiidi mfbns tif Srd (dffbult) dompositing rulf will bf usfd.
 *
 * Tif pixfl pbrbmftfr siould bf b 24-bit XRGB vbluf indidbting tif
 * dolor tibt will bf usfd for rfndfring.  Tif uppfr 8 bits brf bllowfd
 * to bf bny vbluf.
 *
 * Tif RflfbsfDC fundtion siould bf dbllfd to rflfbsf tif lodk on tif DC
 * bftfr b givfn btomid sft of rfndfring opfrbtions is domplftf.
 *
 * Notf to dbllfrs:
 *      Tiis fundtion mby usf JNI mftiods so it is importbnt tibt tif
 *      dbllfr not ibvf bny outstbnding GftPrimitivfArrbyCritidbl or
 *      GftStringCritidbl lodks wiidi ibvf not bffn rflfbsfd.
 */
typfdff HDC GftDCFund(JNIEnv *fnv,
                      GDIWinSDOps *wsdo,
                      jint flbgs,
                      jint *pbtrop,
                      jobjfdt dlip,
                      jobjfdt domp,
                      jint dolor);

/*
 * Tiis fundtion rflfbsfs bn HDC tibt wbs rftrifvfd from tif GftDC
 * fundtion of tif indidbtfd GDIWinSDOps strudturf.
 *
 * Tif fnv pbrbmftfr siould bf tif JNIEnv of tif surrounding JNI dontfxt.
 *
 * Tif ops pbrbmftfr siould bf b pointfr to tif ops objfdt upon wiidi
 * tiis fundtion is bfing invokfd.
 *
 * Tif idd pbrbmftfr siould bf tif ibndlf to tif HDC objfdt tibt wbs
 * rfturnfd from tif GftDC fundtion.
 *
 * Notf to dbllfrs:
 *      Tiis fundtion mby usf JNI mftiods so it is importbnt tibt tif
 *      dbllfr not ibvf bny outstbnding GftPrimitivfArrbyCritidbl or
 *      GftStringCritidbl lodks wiidi ibvf not bffn rflfbsfd.
 */
typfdff void RflfbsfDCFund(JNIEnv *fnv,
                           GDIWinSDOps *wsdo,
                           HDC idd);


typfdff void InvblidbtfSDFund(JNIEnv *fnv,
                              GDIWinSDOps *wsdo);

/*
 * A strudturf tibt iolds bll stbtf globbl to tif nbtivf surfbdfDbtb
 * objfdt.
 *
 * Notf:
 * Tiis strudturf will bf sibrfd bftwffn difffrfnt tirfbds tibt
 * opfrbtf on tif sbmf surfbdfDbtb, so it siould not dontbin bny
 * vbribblfs tibt dould bf dibngfd by onf tirfbd tius plbding otifr
 * tirfbds in b stbtf of donfusion.  For fxbmplf, tif iDC fifld wbs
 * rfmovfd bfdbusf fbdi tirfbd now ibs its own sibrfd DC.  But tif
 * window fifld rfmbins bfdbusf ondf it is sft for b givfn wsdo
 * strudturf it stbys tif sbmf until tibt strudturf is dfstroyfd.
 */
strudt _GDIWinSDOps {
    SurfbdfDbtbOps      sdOps;
    LONG                timfStbmp; // drfbtion timf stbmp.
                                   // Dofsn't storf b rfbl timf -
                                   // just dounts drfbtion fvfnts of tiis strudturf
                                   // mbdf by GDIWindowSurfbdfDbtb_initOps()
                                   // sff bug# 6859086
    jboolfbn            invblid;
    GftDCFund           *GftDC;
    RflfbsfDCFund       *RflfbsfDC;
    InvblidbtfSDFund    *InvblidbtfSD;
    jint                lodkTypf;       // REMIND: storf in TLS
    jint                lodkFlbgs;      // REMIND: storf in TLS
    jobjfdt             pffr;
    HWND                window;
    RECT                insfts;
    jint                dfpti;
    jint                pixflStridf;    // Bytfs pfr pixfl
    DWORD               pixflMbsks[3];  // RGB Mbsks for Windows DIB drfbtion
    HBITMAP             bitmbp;         // REMIND: storf in TLS
    HBITMAP             oldmbp;         // REMIND: storf in TLS
    HDC                 bmdd;           // REMIND: storf in TLS
    int                 bmSdbnStridf;   // REMIND: storf in TLS
    int                 bmWidti;        // REMIND: storf in TLS
    int                 bmHfigit;       // REMIND: storf in TLS
    void                *bmBufffr;      // REMIND: storf in TLS
    jboolfbn            bmCopyToSdrffn; // Usfd to trbdk wiftifr wf
                                        // bdtublly siould dopy tif bitmbp
                                        // to tif sdrffn
    AwtBrusi            *brusi;         // usfd for offsdrffn surfbdfs only
    jint                brusidlr;
    AwtPfn              *pfn;           // usfd for offsdrffn surfbdfs only
    jint                pfndlr;

    int                 x, y, w, i;     // REMIND: storf in TLS
    CritidblSfdtion     *surfbdfLodk;   // REMIND: try to rfmovf
    AwtWin32GrbpiidsDfvidf *dfvidf;
};

#dffinf WIN32SD_LOCK_UNLOCKED   0       /* surfbdf is not lodkfd */
#dffinf WIN32SD_LOCK_BY_NULL    1       /* surfbdf lodkfd for NOP */
#dffinf WIN32SD_LOCK_BY_DIB     2       /* surfbdf lodkfd by BitBlt */

fxtfrn "C" {

/*
 * Strudturf for iolding tif grbpiids stbtf of b tirfbd.
 */
typfdff strudt {
    HDC         iDC;
    GDIWinSDOps *wsdo;
    LONG        wsdoTimfStbmp; // wsdo drfbtion timf stbmp.
                               // Otifr tirfbds mby dfbllodbtf wsdo
                               // bnd tifn bllodbtf b nfw GDIWinSDOps
                               // strudturf bt tif sbmf mfmory lodbtion.
                               // Timf stbmp is tif only wby to dftfdt if
                               // wsdo got dibngfd.
                               // sff bug# 6859086
    RECT        bounds;
    jobjfdt     dlip;
    jobjfdt     domp;
    jint        xordolor;
    jint        pbtrop;
    jint        typf;
    AwtBrusi    *brusi;
    jint        brusidlr;
    AwtPfn      *pfn;
    jint        pfndlr;
} TirfbdGrbpiidsInfo;


/*
 * Tiis fundtion rfturns b pointfr to b nbtivf GDIWinSDOps strudturf
 * for bddfssing tif indidbtfd Win32 SurfbdfDbtb Jbvb objfdt.  It
 * vfrififs tibt tif indidbtfd SurfbdfDbtb objfdt is bn instbndf
 * of GDIWindowSurfbdfDbtb bfforf rfturning bnd will rfturn NULL if tif
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
JNIEXPORT GDIWinSDOps * JNICALL
GDIWindowSurfbdfDbtb_GftOps(JNIEnv *fnv, jobjfdt sDbtb);

JNIEXPORT GDIWinSDOps * JNICALL
GDIWindowSurfbdfDbtb_GftOpsNoSftup(JNIEnv *fnv, jobjfdt sDbtb);

JNIEXPORT HWND JNICALL
GDIWindowSurfbdfDbtb_GftWindow(JNIEnv *fnv, GDIWinSDOps *wsdo);

JNIEXPORT void JNICALL
GDIWinSD_InitDC(JNIEnv *fnv, GDIWinSDOps *wsdo, TirfbdGrbpiidsInfo *info,
               jint typf, jint *pbtrop,
               jobjfdt dlip, jobjfdt domp, jint dolor);

JNIEXPORT AwtComponfnt * JNICALL
GDIWindowSurfbdfDbtb_GftComp(JNIEnv *fnv, GDIWinSDOps *wsdo);

} /* fxtfrn "C" */


#fndif _WIN32SURFACEDATA_H_
