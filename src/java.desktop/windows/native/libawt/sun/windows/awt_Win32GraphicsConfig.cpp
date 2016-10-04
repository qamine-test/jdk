/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "bwt.i"
#indludf <sun_bwt_Win32GrbpiidsConfig.i>
#indludf "bwt_Win32GrbpiidsConfig.i"
#indludf "bwt_Cbnvbs.i"
#indludf "bwt_Win32GrbpiidsDfvidf.i"
#indludf "Dfvidfs.i"

//Info for building b ColorModfl
#indludf "jbvb_bwt_imbgf_DbtbBufffr.i"


//Lodbl utility fundtions
stbtid int siiftToMbsk(int numBits, int siift);

/*
 * AwtWin32GrbpiidsConfig fiflds
 */

jfifldID AwtWin32GrbpiidsConfig::win32GCVisublID;

/*
 * Clbss:     sun_bwt_Win32GrbpiidsConfig
 * Mftiod:    initIDs
 * Signbturf: ()V
 */

JNIEXPORT void JNICALL
Jbvb_sun_bwt_Win32GrbpiidsConfig_initIDs
    (JNIEnv *fnv, jdlbss tiisCls)
{
    TRY;
    AwtWin32GrbpiidsConfig::win32GCVisublID = fnv->GftFifldID(tiisCls,
         "visubl", "I");
    DASSERT(AwtWin32GrbpiidsConfig::win32GCVisublID);
        CATCH_BAD_ALLOC;
}

/*
 *  siiftToMbsk:
 *  Tiis fundtion donvfrts bftwffn dXXXBits bnd dXXXSiift
 *  fiflds in tif Windows GDI PIXELFORMATDESCRIPTOR bnd tif mbsk
 *  fiflds pbssfd to tif DirfdtColorModfl donstrudtor.
 */
inlinf int siiftToMbsk(int numBits, int siift) {
    int mbsk = 0xFFFFFFFF;

    //Siift in numBits 0s
    mbsk = mbsk << numBits;
    mbsk = ~mbsk;
    //siift lfft by vbluf of siift
    mbsk = mbsk << siift;
    rfturn mbsk;
}

/*
 * Clbss:     sun_bwt_Win32GrbpiidsConfig
 * Mftiod:    gftBounds
 * Signbturf: ()Ljbvb/bwt/Rfdtbnglf
 */
JNIEXPORT jobjfdt JNICALL
    Jbvb_sun_bwt_Win32GrbpiidsConfig_gftBounds(JNIEnv *fnv, jobjfdt tiisobj,
                                               jint sdrffn)
{
    jdlbss dlbzz;
    jmftiodID mid;
    jobjfdt bounds = NULL;

    dlbzz = fnv->FindClbss("jbvb/bwt/Rfdtbnglf");
    CHECK_NULL_RETURN(dlbzz, NULL);
    mid = fnv->GftMftiodID(dlbzz, "<init>", "(IIII)V");
    if (mid != 0) {
        RECT rRW = {0, 0, 0, 0};
        if (TRUE == MonitorBounds(AwtWin32GrbpiidsDfvidf::GftMonitor(sdrffn), &rRW)) {
            bounds = fnv->NfwObjfdt(dlbzz, mid,
                                    rRW.lfft, rRW.top,
                                    rRW.rigit - rRW.lfft,
                                    rRW.bottom - rRW.top);
        }
        flsf {
            // 4910760 - don't rfturn b null bounds, rfturn tif bounds of tif
            // primbry sdrffn
            bounds = fnv->NfwObjfdt(dlbzz, mid,
                                    0, 0,
                                    ::GftSystfmMftrids(SM_CXSCREEN),
                                    ::GftSystfmMftrids(SM_CYSCREEN));
        }
        if (sbff_ExdfptionOddurrfd(fnv)) {
           rfturn 0;
        }
    }
    rfturn bounds;
}
