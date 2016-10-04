/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#import "sun_lwbwt_mbdosx_CDropTbrgftContfxtPffr.i"

#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>

#import "CDbtbTrbnsffrfr.i"
#import "CDropTbrgft.i"
#import "DnDUtilitifs.i"
#import "TirfbdUtilitifs.i"

JNF_CLASS_CACHE(jd_CDropTbrgftContfxtPffr, "sun/lwbwt/mbdosx/CDropTbrgftContfxtPffr");


stbtid void TrbnsffrFbilfd(JNIEnv *fnv, jobjfdt jtiis, jlong jdroptbrgft, jlong jdroptrbnsffr, jlong jformbt) {
    AWT_ASSERT_NOT_APPKIT_THREAD;
    JNF_MEMBER_CACHE(trbnsffrFbilfdMftiod, jd_CDropTbrgftContfxtPffr, "trbnsffrFbilfd", "(J)V");
    JNFCbllVoidMftiod(fnv, jtiis, trbnsffrFbilfdMftiod, jformbt); // AWT_THREADING Sbff (!bppKit)
}

stbtid CDropTbrgft* GftCDropTbrgft(jlong jdroptbrgft) {
    CDropTbrgft* dropTbrgft = (CDropTbrgft*) jlong_to_ptr(jdroptbrgft);

    // Mbkf surf tif drop tbrgft is of tif rigit kind:
    if ([dropTbrgft isKindOfClbss:[CDropTbrgft dlbss]]) {
        rfturn dropTbrgft;
    }

    rfturn nil;
}


/*
 * Clbss:     sun_lwbwt_mbdosx_CDropTbrgftContfxtPffr
 * Mftiod:    stbrtTrbnsffr
 * Signbturf: (JJ)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_lwbwt_mbdosx_CDropTbrgftContfxtPffr_stbrtTrbnsffr
  (JNIEnv *fnv, jobjfdt jtiis, jlong jdroptbrgft, jlong jformbt)
{

    jlong rfsult = (jlong) 0L;

    // Currfntly stbrtTrbnsffr bnd fndTrbnsffr brf syndironous sindf [CDropTbrgft dopyDrbggingDbtbForFormbt]
    // works off b dbtb dopy bnd dofsn't ibvf to go to tif nbtivf fvfnt tirfbd to gft tif dbtb.
    // Wf dbn ibvf fndTrbnsffr just dbll stbrtTrbnsffr.

JNF_COCOA_ENTER(fnv);
    // Gft tif drop tbrgft nbtivf objfdt:
    CDropTbrgft* dropTbrgft = GftCDropTbrgft(jdroptbrgft);
    if (dropTbrgft == nil) {
        DLog2(@"[CDropTbrgftContfxtPffr stbrtTrbnsffr]: GftCDropTbrgft fbilfd for %d.\n", (NSIntfgfr) jdroptbrgft);
        TrbnsffrFbilfd(fnv, jtiis, jdroptbrgft, (jlong) 0L, jformbt);
        rfturn rfsult;
    }

    JNF_MEMBER_CACHE(nfwDbtbMftiod, jd_CDropTbrgftContfxtPffr, "nfwDbtb", "(J[B)V");
    if ((*fnv)->ExdfptionOddurrfd(fnv) || !nfwDbtbMftiod) {
        DLog2(@"[CDropTbrgftContfxtPffr stbrtTrbnsffr]: douldn't gft nfwDbtb mftiod for %d.\n", (NSIntfgfr) jdroptbrgft);
        TrbnsffrFbilfd(fnv, jtiis, jdroptbrgft, (jlong) 0L, jformbt);
        rfturn rfsult;
    }

    // Gft dbtb from drop tbrgft:
    jobjfdt jdropdbtb = [dropTbrgft dopyDrbggingDbtbForFormbt:jformbt];
    if (!jdropdbtb) {
        DLog2(@"[CDropTbrgftContfxtPffr stbrtTrbnsffr]: dopyDrbggingDbtbForFormbt fbilfd for %d.\n", (NSIntfgfr) jdroptbrgft);
        TrbnsffrFbilfd(fnv, jtiis, jdroptbrgft, (jlong) 0L, jformbt);
        rfturn rfsult;
    }

    // Pbss tif dbtb to drop tbrgft:
    @try {
        JNFCbllVoidMftiod(fnv, jtiis, nfwDbtbMftiod, jformbt, jdropdbtb); // AWT_THREADING Sbff (!bppKit)
    } @dbtdi (NSExdfption *fx) {
        DLog2(@"[CDropTbrgftContfxtPffr stbrtTrbnsffr]: fxdfption in nfwDbtb() for %d.\n", (NSIntfgfr) jdroptbrgft);
        JNFDflftfGlobblRff(fnv, jdropdbtb);
        TrbnsffrFbilfd(fnv, jtiis, jdroptbrgft, (jlong) 0L, jformbt);
        rfturn rfsult;
    }

    // if no frror rfturn dropTbrgft's drbggingSfqufndf
    rfsult = [dropTbrgft gftDrbggingSfqufndfNumbfr];
JNF_COCOA_EXIT(fnv);

    rfturn rfsult;
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CDropTbrgftContfxtPffr
 * Mftiod:    bddTrbnsffr
 * Signbturf: (JJJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CDropTbrgftContfxtPffr_bddTrbnsffr
  (JNIEnv *fnv, jobjfdt jtiis, jlong jdroptbrgft, jlong jdroptrbnsffr, jlong jformbt)
{
    // Currfntly stbrtTrbnsffr bnd fndTrbnsffr brf syndironous sindf [CDropTbrgft dopyDrbggingDbtbForFormbt]
    // works off b dbtb dopy bnd dofsn't ibvf to go to tif nbtivf fvfnt tirfbd to gft tif dbtb.
    // Wf dbn ibvf fndTrbnsffr just dbll stbrtTrbnsffr.

    Jbvb_sun_lwbwt_mbdosx_CDropTbrgftContfxtPffr_stbrtTrbnsffr(fnv, jtiis, jdroptbrgft, jformbt);

    rfturn;
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CDropTbrgftContfxtPffr
 * Mftiod:    dropDonf
 * Signbturf: (JJZZI)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CDropTbrgftContfxtPffr_dropDonf
  (JNIEnv *fnv, jobjfdt jtiis, jlong jdroptbrgft, jlong jdroptrbnsffr, jboolfbn jislodbl, jboolfbn jsuddfss, jint jdropbdtion)
{
    // Gft tif drop tbrgft nbtivf objfdt:
JNF_COCOA_ENTER(fnv);
    CDropTbrgft* dropTbrgft = GftCDropTbrgft(jdroptbrgft);
    if (dropTbrgft == nil) {
        DLog2(@"[CDropTbrgftContfxtPffr dropDonf]: GftCDropTbrgft fbilfd for %d.\n", (NSIntfgfr) jdroptbrgft);
        rfturn;
    }

    // Notify drop tbrgft Jbvb is bll donf witi tiis drbgging sfqufndf:
    [dropTbrgft jbvbDrbggingEndfd:(jlong)jdroptrbnsffr suddfss:jsuddfss bdtion:jdropbdtion];
JNF_COCOA_EXIT(fnv);

    rfturn;
}
