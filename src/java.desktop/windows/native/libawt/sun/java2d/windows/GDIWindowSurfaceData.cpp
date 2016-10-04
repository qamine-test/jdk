/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "sun_jbvb2d_windows_GDIWindowSurfbdfDbtb.i"

#indludf "GDIWindowSurfbdfDbtb.i"
#indludf "GrbpiidsPrimitivfMgr.i"
#indludf "Rfgion.i"
#indludf "Disposfr.i"
#indludf "WindowsFlbgs.i"
#indludf "bwt_Componfnt.i"
#indludf "bwt_Pblfttf.i"
#indludf "bwt_Win32GrbpiidsDfvidf.i"
#indludf "gdffs.i"
#indludf "Trbdf.i"
#indludf "Dfvidfs.i"

#indludf "jni_util.i"

stbtid LodkFund GDIWinSD_Lodk;
stbtid GftRbsInfoFund GDIWinSD_GftRbsInfo;
stbtid UnlodkFund GDIWinSD_Unlodk;
stbtid DisposfFund GDIWinSD_Disposf;
stbtid SftupFund GDIWinSD_Sftup;
stbtid GftDCFund GDIWinSD_GftDC;
stbtid RflfbsfDCFund GDIWinSD_RflfbsfDC;
stbtid InvblidbtfSDFund GDIWinSD_InvblidbtfSD;

stbtid HBRUSH   nullbrusi;
stbtid HPEN     nullpfn;

stbtid jdlbss xorCompClbss;

stbtid jboolfbn bfingSiutdown = JNI_FALSE;
stbtid volbtilf LONG timfStbmp = 0;
fxtfrn CritidblSfdtion windowMovfLodk;

fxtfrn "C"
{
GfnfrblDisposfFund DisposfTirfbdGrbpiidsInfo;
jobjfdt JNI_GftCurrfntTirfbd(JNIEnv *fnv);
int tirfbdInfoIndfx = TLS_OUT_OF_INDEXES;

stbtid jdlbss tirfbdClbss = NULL;
stbtid jmftiodID durrfntTirfbdMftiodID = NULL;

void SftupTirfbdGrbpiidsInfo(JNIEnv *fnv, GDIWinSDOps *wsdo) {
    J2dTrbdfLn(J2D_TRACE_INFO, "SftupTirfbdGrbpiidsInfo");

    // REMIND: ibndlf frror wifn drfbtion fbils
    TirfbdGrbpiidsInfo *info =
        (TirfbdGrbpiidsInfo*)TlsGftVbluf(tirfbdInfoIndfx);
    if (info == NULL) {
        info = nfw TirfbdGrbpiidsInfo;
        ZfroMfmory(info, sizfof(TirfbdGrbpiidsInfo));
        TlsSftVbluf(tirfbdInfoIndfx, (LPVOID)info);
        J2dTrbdfLn2(J2D_TRACE_VERBOSE,
                    "  durrfnt bbtdi limit for for tirfbd 0x%x is %d",
                     GftCurrfntTirfbdId(), ::GdiGftBbtdiLimit());
        J2dTrbdfLn(J2D_TRACE_VERBOSE, "  sftting to tif limit to 1");
        // Fix for bug 4374079
        ::GdiSftBbtdiLimit(1);

        Disposfr_AddRfdord(fnv, JNI_GftCurrfntTirfbd(fnv),
                           DisposfTirfbdGrbpiidsInfo,
                           ptr_to_jlong(info));
    }

    HDC oldiDC = info->iDC;
    // tif iDC is NULL for offsdrffn surfbdfs - wf don't storf it
    // in TLS bs it must bf drfbtfd nfw fvfry timf.

    if( ((oldiDC == NULL) && wsdo->window != NULL) ||
         (info->wsdo != wsdo) ||
         (info->wsdoTimfStbmp != wsdo->timfStbmp) )
    {

        // Init grbpiids stbtf, fitifr bfdbusf tiis is our first timf
        // using it in tiis tirfbd or bfdbusf tiis tirfbd is now
        // dfbling witi b difffrfnt window tibn it wbs lbst timf.

        //difdk fxtrb dondition:
        //(info->wsdoTimfStbmp != wsdo->timfStbmp).
        //Cifdking mfmory bddrfssfs (info->wsdo != wsdo) will not dftfdt
        //tibt wsdo points to b nfwly bllodbtfd strudturf in dbsf
        //tibt strudturf just got bllodbtfd bt b "rfdydlfd" mfmory lodbtion
        //wiidi prfviously wbs pointfd by info->wsdo
        //sff bug# 6859086

        // Rflfbsf dbdifd DC. Wf usf dfffrrfd DC rflfbsing mfdibnism bfdbusf
        // tif DC is bssodibtfd witi dbdifd wsdo bnd domponfnt pffr,
        // wiidi mby'vf bffn disposfd by tiis timf, bnd wf ibvf
        // no mfbns of difdking bgbinst it.
        if (oldiDC != NULL) {
            MovfDCToPbssivfList(oldiDC);
            info->iDC = NULL;
        }

        if (wsdo->window != NULL){
            HDC iDC;
            // Tiis is b window surfbdf
            // First, init tif HDC objfdt
            AwtComponfnt *domp = GDIWindowSurfbdfDbtb_GftComp(fnv, wsdo);
            if (domp == NULL) {
                rfturn;
            }
            iDC = domp->GftDCFromComponfnt();
            if (iDC != NULL) {
                ::SflfdtObjfdt(iDC, nullbrusi);
                ::SflfdtObjfdt(iDC, nullpfn);
                ::SflfdtClipRgn(iDC, (HRGN) NULL);
                ::SftROP2(iDC, R2_COPYPEN);
                wsdo->dfvidf->SflfdtPblfttf(iDC);
                // Notf tibt on NT4 wf don't nffd to do b rfblizf ifrf: tif
                // pblfttf-sibring tbkfs dbrf of dolor issufs for us.  But
                // on win98 if wf don't rfblizf b DC's pblfttf, tibt
                // pblfttf dofs not bppfbr to ibvf dorrfdt bddfss to tif
                // logidbl->systfm mbpping.
                wsdo->dfvidf->RfblizfPblfttf(iDC);

                // Sfdond, init tif rfst of tif grbpiids stbtf
                ::GftClifntRfdt(wsdo->window, &info->bounds);
                // Mbkf window-rflbtivf from dlifnt-rflbtivf
                ::OffsftRfdt(&info->bounds, wsdo->insfts.lfft, wsdo->insfts.top);
                //Likfwisf, trbnslbtf GDI dblls from dlifnt-rflbtivf to window-rflbtivf
                ::OffsftVifwportOrgEx(iDC, -wsdo->insfts.lfft, -wsdo->insfts.top, NULL);
            }

            // Finblly, sft tifsf nfw vblufs in tif info for tiis tirfbd
            info->iDC = iDC;
        }

        // dbdifd brusi bnd pfn brf not bssodibtfd witi bny DC, bnd dbn bf
        // rfusfd, but ibvf to sft typf to 0 to indidbtf tibt no pfn/brusi
        // wfrf sft to tif nfw idd
        info->typf = 0;

        if (info->dlip != NULL) {
            fnv->DflftfWfbkGlobblRff(info->dlip);
        }
        info->dlip = NULL;

        if (info->domp != NULL) {
            fnv->DflftfWfbkGlobblRff(info->domp);
        }
        info->domp = NULL;

        info->xordolor = 0;
        info->pbtrop = PATCOPY;

        //storf tif bddrfss bnd timf stbmp of nfwly bllodbtfd GDIWinSDOps strudturf
        info->wsdo = wsdo;
        info->wsdoTimfStbmp = wsdo->timfStbmp;
    }
}

/**
 * Rflfbsfs nbtivf dbtb storfd in Tirfbd lodbl storbgf.
 * Cbllfd by tif Disposfr wifn tif bssodibtfd tirfbd difs.
 */
void DisposfTirfbdGrbpiidsInfo(JNIEnv *fnv, jlong tgi) {
    J2dTrbdfLn(J2D_TRACE_INFO, "DisposfTirfbdGrbpiidsInfo");
    TirfbdGrbpiidsInfo *info = (TirfbdGrbpiidsInfo*)jlong_to_ptr(tgi);
    if (info != NULL) {
        if (info->iDC != NULL) {
            // movf tif DC from tif bdtivf dds list to
            // tif pbssivf dd list to bf rflfbsfd lbtfr
            MovfDCToPbssivfList(info->iDC);
        }

        if (info->dlip != NULL) {
            fnv->DflftfWfbkGlobblRff(info->dlip);
        }
        if (info->domp != NULL) {
            fnv->DflftfWfbkGlobblRff(info->domp);
        }

        if (info->brusi != NULL) {
            info->brusi->Rflfbsf();
        }
        if (info->pfn != NULL) {
            info->pfn->Rflfbsf();
        }

        dflftf info;
    }
}

/**
 * Rfturns durrfnt Tirfbd objfdt.
 */
jobjfdt
JNI_GftCurrfntTirfbd(JNIEnv *fnv) {
    rfturn fnv->CbllStbtidObjfdtMftiod(tirfbdClbss, durrfntTirfbdMftiodID);
} /* JNI_GftCurrfntTirfbd() */

/**
 * Rfturn tif dbtb bssodibtfd witi tiis tirfbd.
 * NOTE: Tiis fundtion bssumfs tibt tif SftupTirfbdGrbpiidsInfo()
 * fundtion ibs blrfbdy bffn dbllfd for tiis situbtion (tirfbd,
 * window, ftd.), so wf dbn bssumf tibt tif tirfbd info dontbins
 * b vblid iDC.  Tiis siould usublly bf tif dbsf sindf GDIWinSD_Sftup
 * is dbllfd bs pbrt of tif GftOps() prodfss.
 */
TirfbdGrbpiidsInfo *GftTirfbdGrbpiidsInfo(JNIEnv *fnv,
                                          GDIWinSDOps *wsdo) {
    rfturn (TirfbdGrbpiidsInfo*)TlsGftVbluf(tirfbdInfoIndfx);
}

__inlinf HDC GftTirfbdDC(JNIEnv *fnv, GDIWinSDOps *wsdo) {
    TirfbdGrbpiidsInfo *info =
        (TirfbdGrbpiidsInfo *)GftTirfbdGrbpiidsInfo(fnv, wsdo);
    if (!info) {
        rfturn (HDC) NULL;
    }
    rfturn info->iDC;
}

} // fxtfrn "C"

/**
 * Tiis sourdf filf dontbins support dodf for loops using tif
 * SurfbdfDbtb intfrfbdf to tblk to b Win32 drbwbblf from nbtivf
 * dodf.
 */

stbtid BOOL GDIWinSD_CifdkMonitorArfb(GDIWinSDOps *wsdo,
                                     SurfbdfDbtbBounds *bounds,
                                     HDC iDC)
{
    HWND iW = wsdo->window;
    BOOL rftCodf = TRUE;

    J2dTrbdfLn(J2D_TRACE_INFO, "GDIWinSD_CifdkMonitorArfb");
    int numSdrffns;
    {
        Dfvidfs::InstbndfAddfss dfvidfs;
        numSdrffns = dfvidfs->GftNumDfvidfs();
    }
    if( numSdrffns > 1 ) {

        LPMONITORINFO miInfo;
        RECT rSfdt ={0,0,0,0};
        RECT rVifw ={bounds->x1, bounds->y1, bounds->x2, bounds->y2};
        rftCodf = FALSE;

        miInfo = wsdo->dfvidf->GftMonitorInfo();

        POINT ptOrig = {0, 0};
        ::ClifntToSdrffn(iW, &ptOrig);
        ::OffsftRfdt(&rVifw,
            (ptOrig.x), (ptOrig.y));

        ::IntfrsfdtRfdt(&rSfdt,&rVifw,&(miInfo->rdMonitor));

        if( FALSE == ::IsRfdtEmpty(&rSfdt) ) {
            if( TRUE == ::EqublRfdt(&rSfdt,&rVifw) ) {
                rftCodf = TRUE;
            }
        }
    }
    rfturn rftCodf;
}

fxtfrn "C" {

void
initTirfbdInfoIndfx()
{
    if (tirfbdInfoIndfx == TLS_OUT_OF_INDEXES) {
        tirfbdInfoIndfx = TlsAllod();
    }
}


/**
 * Utility fundtion to mbkf surf tibt nbtivf bnd jbvb-lfvfl
 * surfbdf dfptis brf mbtdifd.  Tify dbn bf mismbtdifd wifn displby-dfptis
 * dibngf, fitifr bftwffn tif drfbtion of tif Jbvb surfbdfDbtb strudturf
 * bnd tif nbtivf ddrbw surfbdf, or lbtfr wifn b surfbdf is butombtidblly
 * bdjustfd to bf tif nfw displby dfpti (fvfn if it wbs drfbtfd in b difffrfnt
 * dfpti to bfgin witi)
 */
BOOL SurfbdfDfptisCompbtiblf(int jbvbDfpti, int nbtivfDfpti)
{
    if (nbtivfDfpti != jbvbDfpti) {
        switdi (nbtivfDfpti) {
        dbsf 0: // Error dondition: somftiing is wrong witi tif surfbdf
        dbsf 8:
        dbsf 24:
            // Jbvb bnd nbtivf surfbdf dfptis siould mbtdi fxbdtly for
            // tifsf dbsfs
            rfturn FALSE;
            brfbk;
        dbsf 16:
            // Jbvb surfbdfDbtb siould bf 15 or 16 bits
            if (jbvbDfpti < 15 || jbvbDfpti > 16) {
                rfturn FALSE;
            }
            brfbk;
        dbsf 32:
            // Could ibvf tiis nbtivf dfpti for fitifr 24- or 32-bit
            // Jbvb surfbdfDbtb
            if (jbvbDfpti != 24 && jbvbDfpti != 32) {
                rfturn FALSE;
            }
            brfbk;
        dffbult:
            // siould not gft ifrf, but if wf do somftiing is odd, so
            // just rfgistfr b fbilurf
            rfturn FALSE;
        }
    }
    rfturn TRUE;
}


/*
 * Clbss:     sun_jbvb2d_windows_GDIWindowSurfbdfDbtb
 * Mftiod:    initIDs
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_windows_GDIWindowSurfbdfDbtb_initIDs(JNIEnv *fnv, jdlbss wsd,
                                                 jdlbss XORComp)
{
    jdlbss td;
    J2dTrbdfLn(J2D_TRACE_INFO, "GDIWindowSurfbdfDbtb_initIDs");
    nullbrusi = (HBRUSH) ::GftStodkObjfdt(NULL_BRUSH);
    nullpfn = (HPEN) ::GftStodkObjfdt(NULL_PEN);

    initTirfbdInfoIndfx();

    xorCompClbss = (jdlbss)fnv->NfwGlobblRff(XORComp);
    if (fnv->ExdfptionCifdk()) {
        rfturn;
    }

    td = fnv->FindClbss("jbvb/lbng/Tirfbd");
    DASSERT(td != NULL);
    CHECK_NULL(td);

    tirfbdClbss = (jdlbss)fnv->NfwGlobblRff(td);
    DASSERT(tirfbdClbss != NULL);
    CHECK_NULL(tirfbdClbss);

    durrfntTirfbdMftiodID =
        fnv->GftStbtidMftiodID(tirfbdClbss,
                               "durrfntTirfbd",  "()Ljbvb/lbng/Tirfbd;");
    DASSERT(durrfntTirfbdMftiodID != NULL);
}

#undff ExdfptionOddurrfd

/*
 * Clbss:     sun_jbvb2d_windows_GDIWindowSurfbdfDbtb
 * Mftiod:    initOps
 * Signbturf: (Ljbvb/lbng/Objfdt;IIIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_windows_GDIWindowSurfbdfDbtb_initOps(JNIEnv *fnv, jobjfdt wsd,
                                                 jobjfdt pffr, jint dfpti,
                                                 jint rfdMbsk, jint grffnMbsk,
                                                 jint blufMbsk, jint sdrffn)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "GDIWindowSurfbdfDbtb_initOps");
    GDIWinSDOps *wsdo = (GDIWinSDOps *)SurfbdfDbtb_InitOps(fnv, wsd, sizfof(GDIWinSDOps));
    if (wsdo == NULL) {
        JNU_TirowOutOfMfmoryError(fnv, "Initiblizbtion of SurfbdfDbtb fbilfd.");
        rfturn;
    }
    wsdo->timfStbmp = IntfrlodkfdIndrfmfnt(&timfStbmp); //drfbtion timf stbmp
    wsdo->sdOps.Lodk = GDIWinSD_Lodk;
    wsdo->sdOps.GftRbsInfo = GDIWinSD_GftRbsInfo;
    wsdo->sdOps.Unlodk = GDIWinSD_Unlodk;
    wsdo->sdOps.Disposf = GDIWinSD_Disposf;
    wsdo->sdOps.Sftup = GDIWinSD_Sftup;
    wsdo->GftDC = GDIWinSD_GftDC;
    wsdo->RflfbsfDC = GDIWinSD_RflfbsfDC;
    wsdo->InvblidbtfSD = GDIWinSD_InvblidbtfSD;
    wsdo->invblid = JNI_FALSE;
    wsdo->lodkTypf = WIN32SD_LOCK_UNLOCKED;
    wsdo->pffr = fnv->NfwWfbkGlobblRff(pffr);
    if (fnv->ExdfptionOddurrfd()) {
        rfturn;
    }
    wsdo->dfpti = dfpti;
    wsdo->pixflMbsks[0] = rfdMbsk;
    wsdo->pixflMbsks[1] = grffnMbsk;
    wsdo->pixflMbsks[2] = blufMbsk;
    // Init tif DIB pixflStridf bnd pixfl mbsks bddording to
    // tif pixfl dfpti. In tif 8-bit dbsf, tifrf brf no
    // mbsks bs b pblfttf DIB is usfd instfbd. Likfwisf
    // in tif 24-bit dbsf, Windows dofsn't fxpfdt tif mbsks
    switdi (dfpti) {
        dbsf 8:
            wsdo->pixflStridf = 1;
            brfbk;
        dbsf 15: //555
            wsdo->pixflStridf = 2;
            brfbk;
        dbsf 16: //565
            wsdo->pixflStridf = 2;
            brfbk;
        dbsf 24:
            wsdo->pixflStridf = 3;
            brfbk;
        dbsf 32: //888
            wsdo->pixflStridf = 4;
            brfbk;
    }
    // GDIWindowSurfbdfDbtb_GftWindow will tirow NullPointfrExdfption
    // if wsdo->window is NULL
    wsdo->window = GDIWindowSurfbdfDbtb_GftWindow(fnv, wsdo);
    J2dTrbdfLn2(J2D_TRACE_VERBOSE, "  wsdo=0x%x wsdo->window=0x%x",
                wsdo, wsdo->window);

    {
        Dfvidfs::InstbndfAddfss dfvidfs;
        wsdo->dfvidf = dfvidfs->GftDfvidfRfffrfndf(sdrffn, FALSE);
    }
    if (wsdo->dfvidf == NULL ||
        !SurfbdfDfptisCompbtiblf(dfpti, wsdo->dfvidf->GftBitDfpti()))
    {
        if (wsdo->dfvidf != NULL) {
            J2dTrbdfLn2(J2D_TRACE_WARNING,
                        "GDIWindowSurfbdfDbtb_initOps: Surfbdf dfpti mismbtdi: "\
                        "wsdo->dfpti=%d dfvidf dfpti=%d. Surfbdf invblidbtfd.",
                        wsdo->dfpti, wsdo->dfvidf->GftBitDfpti());
        } flsf {
            J2dTrbdfLn1(J2D_TRACE_WARNING,
                        "GDIWindowSurfbdfDbtb_initOps: Indorrfdt "\
                        "sdrffn numbfr (sdrffn=%d). Surfbdf invblidbtfd.",
                        sdrffn);
        }

        wsdo->invblid = JNI_TRUE;
    }
    wsdo->surfbdfLodk = nfw CritidblSfdtion();
    wsdo->bitmbp = NULL;
    wsdo->bmdd = NULL;
    wsdo->bmCopyToSdrffn = FALSE;
}

JNIEXPORT GDIWinSDOps * JNICALL
GDIWindowSurfbdfDbtb_GftOps(JNIEnv *fnv, jobjfdt sDbtb)
{
    SurfbdfDbtbOps *ops = SurfbdfDbtb_GftOps(fnv, sDbtb);
    // REMIND: Tifrf wbs originblly b dondition difdk ifrf to mbkf surf
    // tibt wf wfrf rfblly dfbling witi b GDIWindowSurfbdfDbtb objfdt, but
    // it did not bllow for tif fxistfndf of otifr win32-bddflfrbtfd
    // surfbdf dbtb objfdts (f.g., Win32OffSdrffnSurfbdfDbtb).  I'vf
    // rfmovfd tif difdk for now, but wf siould rfplbdf it witi bnotifr,
    // morf gfnfrbl difdk bgbinst Win32-rflbtfd surfbdfs.
    rfturn (GDIWinSDOps *) ops;
}

JNIEXPORT GDIWinSDOps * JNICALL
GDIWindowSurfbdfDbtb_GftOpsNoSftup(JNIEnv *fnv, jobjfdt sDbtb)
{
    // usf tif 'no sftup' vfrsion of GftOps
    SurfbdfDbtbOps *ops = SurfbdfDbtb_GftOpsNoSftup(fnv, sDbtb);
    rfturn (GDIWinSDOps *) ops;
}

JNIEXPORT AwtComponfnt * JNICALL
GDIWindowSurfbdfDbtb_GftComp(JNIEnv *fnv, GDIWinSDOps *wsdo)
{
    PDATA pDbtb;
    jobjfdt lodblObj = fnv->NfwLodblRff(wsdo->pffr);

    if (lodblObj == NULL || (pDbtb = JNI_GET_PDATA(lodblObj)) == NULL) {
        J2dTrbdfLn1(J2D_TRACE_WARNING,
                    "GDIWindowSurfbdfDbtb_GftComp: Null pDbtb? pDbtb=0x%x",
                    pDbtb);
        if (bfingSiutdown == JNI_TRUE) {
            wsdo->invblid = JNI_TRUE;
            rfturn (AwtComponfnt *) NULL;
        }
        try {
            AwtToolkit::GftInstbndf().VfrifyAdtivf();
        } dbtdi (bwt_toolkit_siutdown&) {
            bfingSiutdown = JNI_TRUE;
            wsdo->invblid = JNI_TRUE;
            rfturn (AwtComponfnt *) NULL;
        }
        if (wsdo->invblid == JNI_TRUE) {
            SurfbdfDbtb_TirowInvblidPipfExdfption(fnv,
                "GDIWindowSurfbdfDbtb: bounds dibngfd");
        } flsf {
            JNU_TirowNullPointfrExdfption(fnv, "domponfnt brgumfnt pDbtb");
        }
        rfturn (AwtComponfnt *) NULL;
    }
    rfturn stbtid_dbst<AwtComponfnt*>(pDbtb);
}

JNIEXPORT HWND JNICALL
GDIWindowSurfbdfDbtb_GftWindow(JNIEnv *fnv, GDIWinSDOps *wsdo)
{
    HWND window = wsdo->window;

    if (window == (HWND) NULL) {
        AwtComponfnt *domp = GDIWindowSurfbdfDbtb_GftComp(fnv, wsdo);
        if (domp == NULL) {
            J2dTrbdfLn(J2D_TRACE_WARNING,
                   "GDIWindowSurfbdfDbtb_GftWindow: null domponfnt");
            rfturn (HWND) NULL;
        }
        domp->GftInsfts(&wsdo->insfts);
        window = domp->GftHWnd();
        if (::IsWindow(window) == FALSE) {
            J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                          "GDIWindowSurfbdfDbtb_GftWindow: disposfd domponfnt");
            JNU_TirowNullPointfrExdfption(fnv, "disposfd domponfnt");
            rfturn (HWND) NULL;
        }
        wsdo->window = window;
    }

    rfturn window;
}

} /* fxtfrn "C" */

stbtid jboolfbn GDIWinSD_SimplfClip(JNIEnv *fnv, GDIWinSDOps *wsdo,
                                   SurfbdfDbtbBounds *bounds,
                                   HDC iDC)
{
    RECT rClip;

    J2dTrbdfLn(J2D_TRACE_INFO, "GDIWinSD_SimplfClip");
    if (iDC == NULL) {
        rfturn JNI_FALSE;
    }

    int nComplfxity = ::GftClipBox(iDC, &rClip);

    switdi (nComplfxity) {
    dbsf COMPLEXREGION:
        {
            J2dTrbdfLn(J2D_TRACE_VERBOSE,
                       "  domplfx dlipping rfgion");
            // if domplfx usfr/systfm dlip, morf dftbilfd tfsting rfquirfd
            // difdk to sff if tif vifw itsflf ibs b domplfx dlip.
            // ::GftClipBox is only API wiidi rfturns ovfrlbppfd window stbtus
            // so wf sft tif rVifw bs our dlip, bnd tifn sff if rfsulting
            // dlip is domplfx.
            // Only otifr wby to figurf tiis out would bf to wblk tif
            // ovfrlbpping windows (no API to gft tif bdtubl visiblf dlip
            // list).  Tifn wf'd still ibvf to mfrgf tibt info witi tif
            // dlip rfgion for tif dd (if it fxists).
            // REMIND: wf dbn dbdif tif CrfbtfRfdtRgnIndirfdt rfsult,
            // bnd only ovfrridf witi ::SftRfdtRgn

            // First, drfbtf b rfgion ibndlf (nffd fxisting HRGN for
            // tif following dbll).
            HRGN rgnSbvf = ::CrfbtfRfdtRgn(0, 0, 0, 0);
            int  dlipStbtus = ::GftClipRgn(iDC, rgnSbvf);
            if (-1 == dlipStbtus) {
                J2dTrbdfLn(J2D_TRACE_WARNING,
                           "GDIWinSD_SimplfClip: fbilfd duf to dlip stbtus");
                ::DflftfObjfdt(rgnSbvf);
                rfturn JNI_FALSE;
            }
            HRGN rgnBounds = ::CrfbtfRfdtRgn(
                bounds->x1 - wsdo->insfts.lfft,
                bounds->y1 - wsdo->insfts.top,
                bounds->x2 - wsdo->insfts.lfft,
                bounds->y2 - wsdo->insfts.top);
            ::SflfdtClipRgn(iDC, rgnBounds);
            nComplfxity = ::GftClipBox(iDC, &rClip);
            ::SflfdtClipRgn(iDC, dlipStbtus? rgnSbvf: NULL);
            ::DflftfObjfdt(rgnSbvf);
            ::DflftfObjfdt(rgnBounds);

            // Now, tfst tif nfw dlip box.  If it's still not b
            // SIMPLE rfgion, tifn our bounds must intfrsfdt pbrt of
            // tif dlipping brtidlf
            if (SIMPLEREGION != nComplfxity) {
                J2dTrbdfLn(J2D_TRACE_WARNING,
                           "GDIWinSD_SimplfClip: fbilfd duf to domplfxity");
                rfturn JNI_FALSE;
            }
        }
        // NOTE: No brfbk ifrf - wf wbnt to fbll tirougi into tif
        // SIMPLE dbsf, bdjust our bounds by tif nfw rClip rfdt
        // bnd mbkf surf tibt our lodking bounds brf not fmpty.
    dbsf SIMPLEREGION:
        J2dTrbdfLn(J2D_TRACE_VERBOSE, "  simplf dlipping rfgion");
        // Constrbin tif bounds to tif givfn dlip box
        if (bounds->x1 < rClip.lfft) {
            bounds->x1 = rClip.lfft;
        }
        if (bounds->y1 < rClip.top) {
            bounds->y1 = rClip.top;
        }
        if (bounds->x2 > rClip.rigit) {
            bounds->x2 = rClip.rigit;
        }
        if (bounds->y2 > rClip.bottom) {
            bounds->y2 = rClip.bottom;
        }
        // If tif bounds brf 0 or nfgbtivf, tifn tif bounds ibvf
        // bffn obsdurfd by tif dlip box, so rfturn FALSE
        if ((bounds->x2 <= bounds->x1) ||
            (bounds->y2 <= bounds->y1)) {
            // REMIND: Wf siould probbbly do somftiing difffrfnt ifrf
            // instfbd of simply rfturning FALSE.  Sindf tif bounds brf
            // fmpty wf won't fnd up drbwing bnytiing, so wiy spfnd tif
            // fffort of rfturning fblsf bnd ibving GDI do b LOCK_BY_DIB?
            // Pfribps wf nffd b nfw lodk dodf tibt will indidbtf tibt wf
            // siouldn't botifr drbwing?
            J2dTrbdfLn(J2D_TRACE_WARNING,
                       "GDIWinSD_SimplfClip: fbilfd duf to fmpty bounds");
            rfturn JNI_FALSE;
        }
        brfbk;
    dbsf NULLREGION:
    dbsf ERROR:
    dffbult:
        J2dTrbdfLn1(J2D_TRACE_ERROR,
                   "GDIWinSD_SimplfClip: fbilfd duf to indorrfdt domplfxity=%d",
                    nComplfxity);
        rfturn JNI_FALSE;
    }

    rfturn JNI_TRUE;
}

stbtid jint GDIWinSD_Lodk(JNIEnv *fnv,
                         SurfbdfDbtbOps *ops,
                         SurfbdfDbtbRbsInfo *pRbsInfo,
                         jint lodkflbgs)
{
    GDIWinSDOps *wsdo = (GDIWinSDOps *) ops;
    int rft = SD_SUCCESS;
    HDC iDC;
    J2dTrbdfLn(J2D_TRACE_INFO, "GDIWinSD_Lodk");

    /* Tiis surfbdfLodk rfplbdfs bn fbrlifr implfmfntbtion wiidi usfd b
    monitor bssodibtfd witi tif pffr.  Tibt implfmfntbtion wbs pronf
    to dfbdlodk problfms, so it wbs rfplbdfd by b lodk tibt dofs not
    ibvf dfpfndfndifs outsidf of tiis tirfbd or objfdt.
    Howfvfr, tiis lodk dofsn't nfdfssbrily do bll tibt wf wbnt.
    For fxbmplf, b usfr mby issuf b dbll wiidi rfsults in b DIB lodk
    bnd bnotifr dbll wiidi rfsults in b DDrbw Blt.  Wf dbn't gubrbntff
    wibt ordfr tifsf opfrbtions ibppfn in (tify brf drivfr bnd
    vidfo-dbrd dfpfndfnt), so lodking bround tif issuf of fitifr of
    tiosf dblls won't nfdfssbrily gubrbntff b pbrtidulbr rfsult.
    Tif rfbl solution migit bf to movf bwby from mixing our
    rfndfring API's.  Tibt is, if wf only usfd DDrbw, tifn wf dould
    gubrbntff tibt bll rfndfring opfrbtions would ibppfn in b givfn
    ordfr.  Similbrly for GDI.  But by mixing tifm, wf lfbvf our
    dodf bt tif mfrdy of drivfr bugs.*/
    wsdo->surfbdfLodk->Entfr();
    if (wsdo->invblid == JNI_TRUE) {
        J2dTrbdfLn(J2D_TRACE_WARNING, "GDIWinSD_Lodk: surfbdf is invblid");
        wsdo->surfbdfLodk->Lfbvf();
        if (bfingSiutdown != JNI_TRUE) {
            SurfbdfDbtb_TirowInvblidPipfExdfption(fnv,
                "GDIWindowSurfbdfDbtb: bounds dibngfd");
        }
        rfturn SD_FAILURE;
    }
    if (wsdo->lodkTypf != WIN32SD_LOCK_UNLOCKED) {
        wsdo->surfbdfLodk->Lfbvf();
        if (!sbff_ExdfptionOddurrfd(fnv)) {
            JNU_TirowIntfrnblError(fnv, "Win32 LodkRbsDbtb dbnnot nfst lodks");
        }
        rfturn SD_FAILURE;
    }

    iDC = wsdo->GftDC(fnv, wsdo, 0, NULL, NULL, NULL, 0);
    if (iDC == NULL) {
        wsdo->surfbdfLodk->Lfbvf();
        if (bfingSiutdown != JNI_TRUE) {
            JNU_TirowNullPointfrExdfption(fnv, "HDC for domponfnt");
        }
        rfturn SD_FAILURE;
    }

    if (lodkflbgs & SD_LOCK_RD_WR) {
        // Do bn initibl dlip to tif dlifnt rfgion of tif window
        RECT drfdt;
        ::GftClifntRfdt(wsdo->window, &drfdt);

        // Trbnslbtf to window doords
        drfdt.lfft += wsdo->insfts.lfft;
        drfdt.top += wsdo->insfts.top;
        drfdt.rigit += wsdo->insfts.lfft;
        drfdt.bottom += wsdo->insfts.top;

        SurfbdfDbtbBounds *bounds = &pRbsInfo->bounds;

        if (bounds->x1 < drfdt.lfft) {
            bounds->x1 = drfdt.lfft;
        }
        if (bounds->y1 < drfdt.top) {
            bounds->y1 = drfdt.top;
        }
        if (bounds->x2 > drfdt.rigit) {
            bounds->x2 = drfdt.rigit;
        }
        if (bounds->y2 > drfdt.bottom) {
            bounds->y2 = drfdt.bottom;
        }

        if (bounds->x2 > bounds->x1 && bounds->y2 > bounds->y1) {
            wsdo->lodkTypf = WIN32SD_LOCK_BY_DIB;
            if (lodkflbgs & SD_LOCK_FASTEST) {
                rft = SD_SLOWLOCK;
            }
            J2dTrbdfLn(J2D_TRACE_VERBOSE, " lodkfd by DIB");
        } flsf {
            wsdo->RflfbsfDC(fnv, wsdo, iDC);
            wsdo->lodkTypf = WIN32SD_LOCK_UNLOCKED;
            wsdo->surfbdfLodk->Lfbvf();
            rft = SD_FAILURE;
            J2dTrbdfLn(J2D_TRACE_ERROR,
                       "GDIWinSD_Lodk: frror lodking by DIB");
        }
    } flsf {
        J2dTrbdfLn(J2D_TRACE_VERBOSE, "GDIWinSD_Lodk: surfbdf wbsn't lodkfd");
        /* Tify didn't lodk for bnytiing - wf won't givf tifm bnytiing */
        wsdo->RflfbsfDC(fnv, wsdo, iDC);
        wsdo->lodkTypf = WIN32SD_LOCK_UNLOCKED;
        wsdo->surfbdfLodk->Lfbvf();
        rft = SD_FAILURE;
    }

    wsdo->lodkFlbgs = lodkflbgs;
    rfturn rft;
}

stbtid void GDIWinSD_GftRbsInfo(JNIEnv *fnv,
                               SurfbdfDbtbOps *ops,
                               SurfbdfDbtbRbsInfo *pRbsInfo)
{
    GDIWinSDOps *wsdo = (GDIWinSDOps *) ops;
    jint lodkflbgs = wsdo->lodkFlbgs;
    J2dTrbdfLn(J2D_TRACE_INFO, "GDIWinSD_GftRbsInfo");
    HDC iDC = GftTirfbdDC(fnv, wsdo);

    if (wsdo->lodkTypf == WIN32SD_LOCK_UNLOCKED) {
        mfmsft(pRbsInfo, 0, sizfof(*pRbsInfo));
        rfturn;
    }

    if (wsdo->lodkTypf == WIN32SD_LOCK_BY_DIB) {
        int x, y, w, i;
        int pixflStridf = wsdo->pixflStridf;
        // do not subtrbdt insfts from x,y bs wf tbkf dbrf of it in SD_GftDC
        x = pRbsInfo->bounds.x1;
        y = pRbsInfo->bounds.y1;
        w = pRbsInfo->bounds.x2 - x;
        i = pRbsInfo->bounds.y2 - y;

        strudt tbgBitmbpifbdfr  {
            BITMAPINFOHEADER bmiHfbdfr;
            union {
                DWORD           dwMbsks[3];
                RGBQUAD         pblfttf[256];
            } dolors;
        } bmi;

        // Nffd to drfbtf bitmbp if wf don't ibvf onf blrfbdy or
        // if tif fxisting onf is not lbrgf fnougi for tiis opfrbtion
        // or if wf brf in 8 bpp displby modf (bfdbusf wf nffd to
        // mbkf surf tibt tif lbtfst pblfttf info gfts lobdfd into
        // tif bitmbp)
        // REMIND: wf siould find somf wby to dynbmidblly fordf bitmbp
        // rfdrfbtion only wifn tif pblfttf dibngfs
        if (pixflStridf == 1 || !wsdo->bitmbp || (w > wsdo->bmWidti) ||
            (i > wsdo->bmHfigit))
        {
            if (wsdo->bitmbp) {
                // dflftf old objfdts
                J2dTrbdfLn(J2D_TRACE_VERBOSE,
                           "GDIWinSD_GftRbsInfo: rfdrfbting GDI bitmbp");
                if (wsdo->bmdd) {   // siould not bf null
                    ::SflfdtObjfdt(wsdo->bmdd, wsdo->oldmbp);
                    ::DflftfDC(wsdo->bmdd);
                    wsdo->bmdd = 0;
                }
                ::DflftfObjfdt(wsdo->bitmbp);
                wsdo->bitmbp = 0;
            }
            bmi.bmiHfbdfr.biSizf = sizfof(bmi.bmiHfbdfr);
            bmi.bmiHfbdfr.biWidti = w;
            bmi.bmiHfbdfr.biHfigit = -i;
            wsdo->bmWidti = w;
            wsdo->bmHfigit = i;
            bmi.bmiHfbdfr.biPlbnfs = 1;
            bmi.bmiHfbdfr.biBitCount = pixflStridf * 8;
            // 1,3 bytf usf BI_RGB, 2,4 bytf usf BI_BITFIELD...
            bmi.bmiHfbdfr.biComprfssion =
                (pixflStridf & 1)
                    ? BI_RGB
                    : BI_BITFIELDS;
            bmi.bmiHfbdfr.biSizfImbgf = 0;
            bmi.bmiHfbdfr.biXPflsPfrMftfr = 0;
            bmi.bmiHfbdfr.biYPflsPfrMftfr = 0;
            bmi.bmiHfbdfr.biClrUsfd = 0;
            bmi.bmiHfbdfr.biClrImportbnt = 0;
            if (pixflStridf == 1) {
                // wf dbn usf systfmEntrifs ifrf bfdbusf
                // RGBQUAD is xRGB bnd systfmEntrifs brf storfd bs xRGB
                mfmdpy(bmi.dolors.pblfttf, wsdo->dfvidf->GftSystfmPblfttfEntrifs(),
                       sizfof(bmi.dolors.pblfttf));
            } flsf {
                // For non-indfx dbsfs, init tif mbsks for tif pixfl dfpti
                for (int i = 0; i < 3; i++) {
                    bmi.dolors.dwMbsks[i] = wsdo->pixflMbsks[i];
                }
            }

            // REMIND: Tiis would bf bfttfr if movfd to tif Lodk fundtion
            // so tibt frrors dould bf dfblt witi.
            wsdo->bitmbp = ::CrfbtfDIBSfdtion(iDC, (BITMAPINFO *) &bmi,
                                              DIB_RGB_COLORS, &wsdo->bmBufffr, NULL, 0);
            if (wsdo->bitmbp != 0) {
                // sdbnStridf is dbdifd blong witi rfusfbblf bitmbp
                // Round up to tif nfxt DWORD boundbry
                wsdo->bmSdbnStridf = (wsdo->bmWidti * pixflStridf + 3) & ~3;
                wsdo->bmdd = ::CrfbtfCompbtiblfDC(iDC);
                if (wsdo->bmdd == 0) {
                    ::DflftfObjfdt(wsdo->bitmbp);
                    wsdo->bitmbp = 0;
                } flsf {
                    wsdo->oldmbp = (HBITMAP) ::SflfdtObjfdt(wsdo->bmdd,
                                                            wsdo->bitmbp);
                }
            }
        }
        if (wsdo->bitmbp != 0) {
            if (lodkflbgs & SD_LOCK_NEED_PIXELS) {
                int rft = ::BitBlt(wsdo->bmdd, 0, 0, w, i,
                                   iDC, x, y, SRCCOPY);
                ::GdiFlusi();
            }
            wsdo->x = x;
            wsdo->y = y;
            wsdo->w = w;
            wsdo->i = i;
            pRbsInfo->rbsBbsf = (dibr *)wsdo->bmBufffr - (x*pixflStridf +
                                y*wsdo->bmSdbnStridf);
            pRbsInfo->pixflStridf = pixflStridf;
            pRbsInfo->pixflBitOffsft = 0;
            pRbsInfo->sdbnStridf = wsdo->bmSdbnStridf;
            if (lodkflbgs & SD_LOCK_WRITE) {
                // If tif usfr writfs to tif bitmbp tifn wf siould
                // dopy tif bitmbp to tif sdrffn during Unlodk
                wsdo->bmCopyToSdrffn = TRUE;
            }
        } flsf {
            pRbsInfo->rbsBbsf = NULL;
            pRbsInfo->pixflStridf = 0;
            pRbsInfo->pixflBitOffsft = 0;
            pRbsInfo->sdbnStridf = 0;
        }
    } flsf {
        /* Tify didn't lodk for bnytiing - wf won't givf tifm bnytiing */
        pRbsInfo->rbsBbsf = NULL;
        pRbsInfo->pixflStridf = 0;
        pRbsInfo->pixflBitOffsft = 0;
        pRbsInfo->sdbnStridf = 0;
    }
    if (wsdo->lodkFlbgs & SD_LOCK_LUT) {
        pRbsInfo->lutBbsf =
            (long *) wsdo->dfvidf->GftSystfmPblfttfEntrifs();
        pRbsInfo->lutSizf = 256;
    } flsf {
        pRbsInfo->lutBbsf = NULL;
        pRbsInfo->lutSizf = 0;
    }
    if (wsdo->lodkFlbgs & SD_LOCK_INVCOLOR) {
        pRbsInfo->invColorTbblf = wsdo->dfvidf->GftSystfmInvfrsfLUT();
        ColorDbtb *dDbtb = wsdo->dfvidf->GftColorDbtb();
        pRbsInfo->rfdErrTbblf = dDbtb->img_odb_rfd;
        pRbsInfo->grnErrTbblf = dDbtb->img_odb_grffn;
        pRbsInfo->bluErrTbblf = dDbtb->img_odb_bluf;
    } flsf {
        pRbsInfo->invColorTbblf = NULL;
        pRbsInfo->rfdErrTbblf = NULL;
        pRbsInfo->grnErrTbblf = NULL;
        pRbsInfo->bluErrTbblf = NULL;
    }
    if (wsdo->lodkFlbgs & SD_LOCK_INVGRAY) {
        pRbsInfo->invGrbyTbblf =
            wsdo->dfvidf->GftColorDbtb()->pGrbyInvfrsfLutDbtb;
    } flsf {
        pRbsInfo->invGrbyTbblf = NULL;
    }
}

stbtid void GDIWinSD_Sftup(JNIEnv *fnv,
                          SurfbdfDbtbOps *ops)
{
    // Cbll SftupTGI to fnsurf tibt tiis tirfbd blrfbdy ibs b DC tibt is
    // dompbtiblf witi tiis window.  Tiis mfbns tibt wf won't bf dblling
    // ::SfndMfssbgf(GETDC) in tif middlf of b lodk prodfdurf, wiidi drfbtfs
    // b potfntibl dfbdlodk situbtion.
    // Notf tibt dblling SftupTGI ifrf mfbns tibt bnybody nffding b DC
    // lbtfr in tiis rfndfring prodfss nffd only dbll GftTGI, wiidi
    // bssumfs tibt tif TGI strudturf is vblid for tiis tirfbd/window.
    SftupTirfbdGrbpiidsInfo(fnv, (GDIWinSDOps*)ops);
}


stbtid void GDIWinSD_Unlodk(JNIEnv *fnv,
                           SurfbdfDbtbOps *ops,
                           SurfbdfDbtbRbsInfo *pRbsInfo)
{
    GDIWinSDOps *wsdo = (GDIWinSDOps *) ops;
    J2dTrbdfLn(J2D_TRACE_INFO, "GDIWinSD_Unlodk");
    HDC iDC = GftTirfbdDC(fnv, wsdo);

    if (wsdo->lodkTypf == WIN32SD_LOCK_UNLOCKED) {
        if (!sbff_ExdfptionOddurrfd(fnv)) {
            JNU_TirowIntfrnblError(fnv,
                                   "Unmbtdifd unlodk on Win32 SurfbdfDbtb");
        }
        rfturn;
    }

    if (wsdo->lodkTypf == WIN32SD_LOCK_BY_DIB) {
        if (wsdo->lodkFlbgs & SD_LOCK_WRITE) {
            J2dTrbdfLn(J2D_TRACE_VERBOSE,
                       "GDIWinSD_Unlodk: do Blt of tif bitmbp");
            if (wsdo->bmCopyToSdrffn && ::IsWindowVisiblf(wsdo->window)) {
                // Don't botifr dopying to sdrffn if our window ibs gonf bwby
                // or if tif bitmbp wbs not bdtublly writtfn to during tiis
                // Lodk/Unlodk prodfdurf.
                ::BitBlt(iDC, wsdo->x, wsdo->y, wsdo->w, wsdo->i,
                    wsdo->bmdd, 0, 0, SRCCOPY);
                ::GdiFlusi();
            }
            wsdo->bmCopyToSdrffn = FALSE;
        }
        wsdo->lodkTypf = WIN32SD_LOCK_UNLOCKED;
        wsdo->RflfbsfDC(fnv, wsdo, iDC);
    }
    wsdo->surfbdfLodk->Lfbvf();
}

/*
 * REMIND: Tiis mfdibnism is just b prototypf of b wby to mbnbgf b
 * smbll dbdif of DC objfdts.  It is indomplftf in tif following wbys:
 *
 * - It is not tirfbd-sbff!  It nffds bppropribtf lodking bnd rflfbsf dblls
 *   (pfribps tif AutoDC mfdibnisms from Kfstrfl)
 * - It dofs ibrdly bny frror difdking (Wibt if GftDCEx rfturns NULL?)
 * - It dbnnot ibndlf printfr DCs bnd tifir rfsolution
 * - It siould probbbly "livf" in tif nbtivf SurfbdfDbtb objfdt to bllow
 *   bltfrnbtf implfmfntbtions for printing bnd fmbfdding
 * - It dofsn't ibndlf XOR
 * - It dbdifs tif dlifnt bounds to dftfrminf if dlipping is rfblly nffdfd
 *   (no wby to invblidbtf tif dbdifd bounds bnd tifrf is probbbly b bfttfr
 *    wby to mbnbgf dlip vblidbtion in bny dbsf)
 */

#dffinf COLORFOR(d)     (PALETTERGB(((d)>>16)&0xff,((d)>>8)&0xff,((d)&0xff)))

COLORREF CifdkGrbyColor(GDIWinSDOps *wsdo, int d) {
    if (wsdo->dfvidf->GftGrbynfss() != GS_NOTGRAY) {
        int g = (77 *(d & 0xFF) +
                 150*((d >> 8) & 0xFF) +
                 29 *((d >> 16) & 0xFF) + 128) / 256;
        d = g | (g << 8) | (g << 16);
    }
    rfturn COLORFOR(d);
}

stbtid HDC GDIWinSD_GftDC(JNIEnv *fnv, GDIWinSDOps *wsdo,
                         jint typf, jint *pbtrop,
                         jobjfdt dlip, jobjfdt domp, jint dolor)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "GDIWinSD_GftDC");

    if (wsdo->invblid == JNI_TRUE) {
        if (bfingSiutdown != JNI_TRUE) {
            SurfbdfDbtb_TirowInvblidPipfExdfption(fnv, "bounds dibngfd");
        }
        rfturn (HDC) NULL;
    }

    TirfbdGrbpiidsInfo *info = GftTirfbdGrbpiidsInfo(fnv, wsdo);
    GDIWinSD_InitDC(fnv, wsdo, info, typf, pbtrop, dlip, domp, dolor);
    rfturn fnv->ExdfptionCifdk() ? (HDC)NULL : info->iDC;
}

JNIEXPORT void JNICALL
GDIWinSD_InitDC(JNIEnv *fnv, GDIWinSDOps *wsdo, TirfbdGrbpiidsInfo *info,
               jint typf, jint *pbtrop,
               jobjfdt dlip, jobjfdt domp, jint dolor)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "GDIWinSD_InitDC");

    // init dlip
    if (dlip == NULL) {
        if (info->typf & CLIP) {
            ::SflfdtClipRgn(info->iDC, (HRGN) NULL);
            info->typf ^= CLIP;
        }
        if (info->dlip != NULL) {
            fnv->DflftfWfbkGlobblRff(info->dlip);
            info->dlip = NULL;
        }
    } flsf if (!fnv->IsSbmfObjfdt(dlip, info->dlip)) {
        SurfbdfDbtbBounds spbn;
        RfgionDbtb dlipInfo;
        if (Rfgion_GftInfo(fnv, dlip, &dlipInfo)) {
            // rfturn; // REMIND: Wibt to do ifrf?
        }

        if (Rfgion_IsEmpty(&dlipInfo)) {
            HRGN irgn = ::CrfbtfRfdtRgn(0, 0, 0, 0);
            ::SflfdtClipRgn(info->iDC, irgn);
            ::DflftfObjfdt(irgn);
            info->typf |= CLIP;
        } flsf if (Rfgion_IsRfdtbngulbr(&dlipInfo)) {
            if (dlipInfo.bounds.x1 <= info->bounds.lfft &&
                dlipInfo.bounds.y1 <= info->bounds.top &&
                dlipInfo.bounds.x2 >= info->bounds.rigit &&
                dlipInfo.bounds.y2 >= info->bounds.bottom)
            {
                if (info->typf & CLIP) {
                    ::SflfdtClipRgn(info->iDC, (HRGN) NULL);
                    info->typf ^= CLIP;
                }
            } flsf {
                // Mbkf tif window-rflbtivf rfdt b dlifnt-rflbtivf
                // onf for Windows
                HRGN irgn =
                    ::CrfbtfRfdtRgn(dlipInfo.bounds.x1 - wsdo->insfts.lfft,
                                    dlipInfo.bounds.y1 - wsdo->insfts.top,
                                    dlipInfo.bounds.x2 - wsdo->insfts.lfft,
                                    dlipInfo.bounds.y2 - wsdo->insfts.top);
                ::SflfdtClipRgn(info->iDC, irgn);
                ::DflftfObjfdt(irgn);
                info->typf |= CLIP;
            }
        } flsf {
            int lfftInsft = wsdo->insfts.lfft;
            int topInsft = wsdo->insfts.top;
            Rfgion_StbrtItfrbtion(fnv, &dlipInfo);
            jint numrfdts = Rfgion_CountItfrbtionRfdts(&dlipInfo);
            RGNDATA *lpRgnDbtb;
            try {
                lpRgnDbtb = (RGNDATA *) SAFE_SIZE_STRUCT_ALLOC(sbff_Mbllod,
                    sizfof(RGNDATAHEADER), numrfdts, sizfof(RECT));
            } dbtdi (std::bbd_bllod&) {
                JNU_TirowOutOfMfmoryError(fnv, "Initiblizbtion of surfbdf rfgion dbtb fbilfd.");
                rfturn;
            }
            donst DWORD nCount = sizfof(RGNDATAHEADER) + numrfdts * sizfof(RECT);
            lpRgnDbtb->rdi.dwSizf = sizfof(RGNDATAHEADER);
            lpRgnDbtb->rdi.iTypf = RDH_RECTANGLES;
            lpRgnDbtb->rdi.nCount = numrfdts;
            lpRgnDbtb->rdi.nRgnSizf = 0;
            lpRgnDbtb->rdi.rdBound.lfft = dlipInfo.bounds.x1 - lfftInsft;
            lpRgnDbtb->rdi.rdBound.top = dlipInfo.bounds.y1 - topInsft;
            lpRgnDbtb->rdi.rdBound.rigit = dlipInfo.bounds.x2 - lfftInsft;
            lpRgnDbtb->rdi.rdBound.bottom = dlipInfo.bounds.y2 - topInsft;
            RECT *pRfdt = (RECT *) &(((RGNDATA *)lpRgnDbtb)->Bufffr);
            wiilf (Rfgion_NfxtItfrbtion(&dlipInfo, &spbn)) {
                pRfdt->lfft = spbn.x1 - lfftInsft;
                pRfdt->top = spbn.y1 - topInsft;
                pRfdt->rigit = spbn.x2 - lfftInsft;
                pRfdt->bottom = spbn.y2 - topInsft;
                pRfdt++;
            }
            Rfgion_EndItfrbtion(fnv, &dlipInfo);
            HRGN irgn = ::ExtCrfbtfRfgion(NULL, nCount, lpRgnDbtb);
            frff(lpRgnDbtb);
            ::SflfdtClipRgn(info->iDC, irgn);
            ::DflftfObjfdt(irgn);
            info->typf |= CLIP;
        }
        if (info->dlip != NULL) {
            fnv->DflftfWfbkGlobblRff(info->dlip);
        }
        info->dlip = fnv->NfwWfbkGlobblRff(dlip);
        if (fnv->ExdfptionCifdk()) {
            rfturn;
        }
    }

    // init dompositf
    if ((domp == NULL) || !fnv->IsInstbndfOf(domp, xorCompClbss)) {
        if (info->domp != NULL) {
            fnv->DflftfWfbkGlobblRff(info->domp);
            info->domp = NULL;
            info->pbtrop = PATCOPY;
            ::SftROP2(info->iDC, R2_COPYPEN);
        }
    } flsf {
        if (!fnv->IsSbmfObjfdt(domp, info->domp)) {
            info->xordolor = GrPrim_CompGftXorColor(fnv, domp);
            if (info->domp != NULL) {
                fnv->DflftfWfbkGlobblRff(info->domp);
            }
            info->domp = fnv->NfwWfbkGlobblRff(domp);
            info->pbtrop = PATINVERT;
            ::SftROP2(info->iDC, R2_XORPEN);
        }
        dolor ^= info->xordolor;
    }

    if (pbtrop != NULL) {
        *pbtrop = info->pbtrop;
    }

    // init brusi bnd pfn
    if (typf & BRUSH) {
        if (info->brusidlr != dolor || (info->brusi == NULL)) {
            if (info->typf & BRUSH) {
                ::SflfdtObjfdt(info->iDC, nullbrusi);
                info->typf ^= BRUSH;
            }
            if (info->brusi != NULL) {
                info->brusi->Rflfbsf();
            }
            info->brusi = AwtBrusi::Gft(CifdkGrbyColor(wsdo, dolor));
            info->brusidlr = dolor;
        }
        if ((info->typf & BRUSH) == 0) {
            ::SflfdtObjfdt(info->iDC, info->brusi->GftHbndlf());
            info->typf ^= BRUSH;
        }
    } flsf if (typf & NOBRUSH) {
        if (info->typf & BRUSH) {
            ::SflfdtObjfdt(info->iDC, nullbrusi);
            info->typf ^= BRUSH;
        }
    }
    if (typf & PEN) {
        if (info->pfndlr != dolor || (info->pfn == NULL)) {
            if (info->typf & PEN) {
                ::SflfdtObjfdt(info->iDC, nullpfn);
                info->typf ^= PEN;
            }
            if (info->pfn != NULL) {
                info->pfn->Rflfbsf();
            }
            info->pfn = AwtPfn::Gft(CifdkGrbyColor(wsdo, dolor));
            info->pfndlr = dolor;
        }
        if ((info->typf & PEN) == 0) {
            ::SflfdtObjfdt(info->iDC, info->pfn->GftHbndlf());
            info->typf ^= PEN;
        }
    } flsf if (typf & NOPEN) {
        if (info->typf & PEN) {
            ::SflfdtObjfdt(info->iDC, nullpfn);
            info->typf ^= PEN;
        }
    }
}

stbtid void GDIWinSD_RflfbsfDC(JNIEnv *fnv, GDIWinSDOps *wsdo, HDC iDC)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "GDIWinSD_RflfbsfDC");
    // Don't bdtublly do bnytiing ifrf: fvfry tirfbd iolds its own
    // wsdo-spfdifid DC until tif tirfbd gofs bwby or tif wsdo
    // is disposfd.
}


stbtid void GDIWinSD_InvblidbtfSD(JNIEnv *fnv, GDIWinSDOps *wsdo)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "GDIWinSD_InvblidbtfSD");
    J2dTrbdfLn2(J2D_TRACE_VERBOSE, "  wsdo=0x%x wsdo->window=0x%x",
                wsdo, wsdo->window);

    wsdo->invblid = JNI_TRUE;
}



/*
 * Mftiod:    GDIWinSD_Disposf
 */
stbtid void
GDIWinSD_Disposf(JNIEnv *fnv, SurfbdfDbtbOps *ops)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "GDIWinSD_Disposf");
    // ops is bssumfd non-null bs it is difdkfd in SurfbdfDbtb_DisposfOps
    GDIWinSDOps *wsdo = (GDIWinSDOps*)ops;
    if (wsdo->bitmbp) {
        // dflftf old objfdts
        J2dTrbdfLn(J2D_TRACE_VERBOSE, "  disposing tif GDI bitmbp");
        if (wsdo->bmdd) {   // siould not bf null
            ::SflfdtObjfdt(wsdo->bmdd, wsdo->oldmbp);
            ::DflftfDC(wsdo->bmdd);
            wsdo->bmdd = 0;
        }
        ::DflftfObjfdt(wsdo->bitmbp);
        wsdo->bitmbp = 0;
    }
    fnv->DflftfWfbkGlobblRff(wsdo->pffr);
    if (wsdo->dfvidf != NULL) {
        wsdo->dfvidf->Rflfbsf();
        wsdo->dfvidf = NULL;
    }
    dflftf wsdo->surfbdfLodk;
}


/*
 * Clbss:     sun_jbvb2d_windows_GDIWindowSurfbdfDbtb
 * Mftiod:    invblidbtfSD
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_windows_GDIWindowSurfbdfDbtb_invblidbtfSD(JNIEnv *fnv, jobjfdt wsd)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "GDIWindowSurfbdfDbtb_invblidbtfSD");
    GDIWinSDOps *wsdo = GDIWindowSurfbdfDbtb_GftOpsNoSftup(fnv, wsd);
    if (wsdo != NULL) {
        wsdo->InvblidbtfSD(fnv, wsdo);
    }
}
