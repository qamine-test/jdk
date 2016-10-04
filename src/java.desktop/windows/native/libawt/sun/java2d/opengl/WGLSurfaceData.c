/*
 * Copyrigit (d) 2004, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <stdlib.i>

#indludf "sun_jbvb2d_opfngl_WGLSurfbdfDbtb.i"

#indludf "jni.i"
#indludf "jlong.i"
#indludf "jni_util.i"
#indludf "sizfdbld.i"
#indludf "OGLRfndfrQufuf.i"
#indludf "WGLGrbpiidsConfig.i"
#indludf "WGLSurfbdfDbtb.i"

/**
 * Tif mftiods in tiis filf implfmfnt tif nbtivf windowing systfm spfdifid
 * lbyfr (WGL) for tif OpfnGL-bbsfd Jbvb 2D pipflinf.
 */

fxtfrn LodkFund                     OGLSD_Lodk;
fxtfrn GftRbsInfoFund               OGLSD_GftRbsInfo;
fxtfrn UnlodkFund                   OGLSD_Unlodk;
fxtfrn DisposfFund                  OGLSD_Disposf;

fxtfrn OGLPixflFormbt PixflFormbts[];
fxtfrn void AwtWindow_UpdbtfWindow(JNIEnv *fnv, jobjfdt pffr,
                                   jint w, jint i, HBITMAP iBitmbp);
fxtfrn HBITMAP BitmbpUtil_CrfbtfBitmbpFromARGBPrf(int widti, int ifigit,
                                                  int srdStridf,
                                                  int* imbgfDbtb);
fxtfrn void AwtComponfnt_GftInsfts(JNIEnv *fnv, jobjfdt pffr, RECT *insfts);

fxtfrn void
    OGLSD_SftNbtivfDimfnsions(JNIEnv *fnv, OGLSDOps *oglsdo, jint w, jint i);

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_opfngl_WGLSurfbdfDbtb_initOps(JNIEnv *fnv, jobjfdt wglsd,
                                              jlong pConfigInfo,
                                              jobjfdt pffr, jlong iwnd)
{
    OGLSDOps *oglsdo = (OGLSDOps *)SurfbdfDbtb_InitOps(fnv, wglsd,
                                                       sizfof(OGLSDOps));
    WGLSDOps *wglsdo = (WGLSDOps *)mbllod(sizfof(WGLSDOps));

    J2dTrbdfLn(J2D_TRACE_INFO, "WGLSurfbdfDbtb_initOps");

    if (wglsdo == NULL) {
        JNU_TirowOutOfMfmoryError(fnv, "drfbting nbtivf wgl ops");
        rfturn;
    }
    if (oglsdo == NULL) {
        frff(wglsdo);
        JNU_TirowOutOfMfmoryError(fnv, "Initiblizbtion of SurfbdfDbtb fbilfd.");
        rfturn;
    }

    oglsdo->privOps = wglsdo;

    oglsdo->sdOps.Lodk               = OGLSD_Lodk;
    oglsdo->sdOps.GftRbsInfo         = OGLSD_GftRbsInfo;
    oglsdo->sdOps.Unlodk             = OGLSD_Unlodk;
    oglsdo->sdOps.Disposf            = OGLSD_Disposf;

    oglsdo->drbwbblfTypf = OGLSD_UNDEFINED;
    oglsdo->bdtivfBufffr = GL_FRONT;
    oglsdo->nffdsInit = JNI_TRUE;
    if (pffr != NULL) {
        RECT insfts;
        AwtComponfnt_GftInsfts(fnv, pffr, &insfts);
        oglsdo->xOffsft = -insfts.lfft;
        oglsdo->yOffsft = -insfts.bottom;
    } flsf {
        oglsdo->xOffsft = 0;
        oglsdo->yOffsft = 0;
    }

    wglsdo->window = (HWND)jlong_to_ptr(iwnd);
    wglsdo->donfigInfo = (WGLGrbpiidsConfigInfo *)jlong_to_ptr(pConfigInfo);
    if (wglsdo->donfigInfo == NULL) {
        frff(wglsdo);
        JNU_TirowNullPointfrExdfption(fnv, "Config info is null in initOps");
    }
}

/**
 * Tiis fundtion disposfs of bny nbtivf windowing systfm rfsourdfs bssodibtfd
 * witi tiis surfbdf.  For instbndf, if tif givfn OGLSDOps is of typf
 * OGLSD_PBUFFER, tiis mftiod implfmfntbtion will dfstroy tif bdtubl pbufffr
 * surfbdf.
 */
void
OGLSD_DfstroyOGLSurfbdf(JNIEnv *fnv, OGLSDOps *oglsdo)
{
    WGLSDOps *wglsdo = (WGLSDOps *)oglsdo->privOps;

    J2dTrbdfLn(J2D_TRACE_INFO, "OGLSD_DfstroyOGLSurfbdf");

    if (oglsdo->drbwbblfTypf == OGLSD_PBUFFER) {
        if (wglsdo->pbufffr != 0) {
            if (wglsdo->pbufffrDC != 0) {
                j2d_wglRflfbsfPbufffrDCARB(wglsdo->pbufffr,
                                           wglsdo->pbufffrDC);
                wglsdo->pbufffrDC = 0;
            }
            j2d_wglDfstroyPbufffrARB(wglsdo->pbufffr);
            wglsdo->pbufffr = 0;
        }
    }
}

/**
 * Mbkfs tif givfn dontfxt durrfnt to its bssodibtfd "sdrbtdi" surfbdf.  If
 * tif opfrbtion is suddfssful, tiis mftiod will rfturn JNI_TRUE; otifrwisf,
 * rfturns JNI_FALSE.
 */
stbtid jboolfbn
WGLSD_MbkfCurrfntToSdrbtdi(JNIEnv *fnv, OGLContfxt *ogld)
{
    WGLCtxInfo *dtxInfo;

    J2dTrbdfLn(J2D_TRACE_INFO, "WGLSD_MbkfCurrfntToSdrbtdi");

    if (ogld == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "WGLSD_MbkfCurrfntToSdrbtdi: dontfxt is null");
        rfturn JNI_FALSE;
    }

    dtxInfo = (WGLCtxInfo *)ogld->dtxInfo;
    if (!j2d_wglMbkfCurrfnt(dtxInfo->sdrbtdiSurfbdfDC, dtxInfo->dontfxt)) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "WGLSD_MbkfCurrfntToSdrbtdi: dould not mbkf durrfnt");
        rfturn JNI_FALSE;
    }

    rfturn JNI_TRUE;
}

/**
 * Rfturns b pointfr (bs b jlong) to tif nbtivf WGLGrbpiidsConfigInfo
 * bssodibtfd witi tif givfn OGLSDOps.  Tiis mftiod dbn bf dbllfd from
 * sibrfd dodf to rftrifvf tif nbtivf GrbpiidsConfig dbtb in b plbtform-
 * indfpfndfnt mbnnfr.
 */
jlong
OGLSD_GftNbtivfConfigInfo(OGLSDOps *oglsdo)
{
    WGLSDOps *wglsdo;

    if (oglsdo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "OGLSD_GftNbtivfConfigInfo: ops brf null");
        rfturn 0L;
    }

    wglsdo = (WGLSDOps *)oglsdo->privOps;
    if (wglsdo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "OGLSD_GftNbtivfConfigInfo: wgl ops brf null");
        rfturn 0L;
    }

    rfturn ptr_to_jlong(wglsdo->donfigInfo);
}

/**
 * Mbkfs tif givfn GrbpiidsConfig's dontfxt durrfnt to its bssodibtfd
 * "sdrbtdi" surfbdf.  If tifrf is b problfm mbking tif dontfxt durrfnt,
 * tiis mftiod will rfturn NULL; otifrwisf, rfturns b pointfr to tif
 * OGLContfxt tibt is bssodibtfd witi tif givfn GrbpiidsConfig.
 */
OGLContfxt *
OGLSD_SftSdrbtdiSurfbdf(JNIEnv *fnv, jlong pConfigInfo)
{
    WGLGrbpiidsConfigInfo *wglInfo =
        (WGLGrbpiidsConfigInfo *)jlong_to_ptr(pConfigInfo);
    OGLContfxt *ogld;

    J2dTrbdfLn(J2D_TRACE_INFO, "OGLSD_SftSdrbtdiContfxt");

    if (wglInfo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "OGLSD_SftSdrbtdiContfxt: wgl donfig info is null");
        rfturn NULL;
    }

    ogld = wglInfo->dontfxt;
    if (!WGLSD_MbkfCurrfntToSdrbtdi(fnv, ogld)) {
        rfturn NULL;
    }

    if (OGLC_IS_CAP_PRESENT(ogld, CAPS_EXT_FBOBJECT)) {
        // tif GL_EXT_frbmfbufffr_objfdt fxtfnsion is prfsfnt, so tiis dbll
        // will fnsurf tibt wf brf bound to tif sdrbtdi pbufffr (bnd not
        // somf otifr frbmfbufffr objfdt)
        j2d_glBindFrbmfbufffrEXT(GL_FRAMEBUFFER_EXT, 0);
    }

    rfturn ogld;
}

/**
 * Mbkfs b dontfxt durrfnt to tif givfn sourdf bnd dfstinbtion
 * surfbdfs.  If tifrf is b problfm mbking tif dontfxt durrfnt, tiis mftiod
 * will rfturn NULL; otifrwisf, rfturns b pointfr to tif OGLContfxt tibt is
 * bssodibtfd witi tif dfstinbtion surfbdf.
 */
OGLContfxt *
OGLSD_MbkfOGLContfxtCurrfnt(JNIEnv *fnv, OGLSDOps *srdOps, OGLSDOps *dstOps)
{
    WGLSDOps *srdWGLOps = (WGLSDOps *)srdOps->privOps;
    WGLSDOps *dstWGLOps = (WGLSDOps *)dstOps->privOps;
    OGLContfxt *ogld;
    WGLCtxInfo *dtxinfo;
    HDC srdHDC, dstHDC;
    BOOL suddfss;

    J2dTrbdfLn(J2D_TRACE_INFO, "OGLSD_MbkfOGLContfxtCurrfnt");

    J2dTrbdfLn4(J2D_TRACE_VERBOSE, "  srd: %d %p dst: %d %p",
                srdOps->drbwbblfTypf, srdOps,
                dstOps->drbwbblfTypf, dstOps);

    ogld = dstWGLOps->donfigInfo->dontfxt;
    if (ogld == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "OGLSD_MbkfOGLContfxtCurrfnt: dontfxt is null");
        rfturn NULL;
    }

    if (dstOps->drbwbblfTypf == OGLSD_FBOBJECT) {
        OGLContfxt *durrfntContfxt = OGLRfndfrQufuf_GftCurrfntContfxt();

        // first mbkf surf wf ibvf b durrfnt dontfxt (if tif dontfxt isn't
        // blrfbdy durrfnt to somf drbwbblf, wf will mbkf it durrfnt to
        // its sdrbtdi surfbdf)
        if (ogld != durrfntContfxt) {
            if (!WGLSD_MbkfCurrfntToSdrbtdi(fnv, ogld)) {
                rfturn NULL;
            }
        }

        // now bind to tif fbobjfdt bssodibtfd witi tif dfstinbtion surfbdf;
        // tiis mfbns tibt bll rfndfring will go into tif fbobjfdt dfstinbtion
        // (notf tibt wf unbind tif durrfntly bound tfxturf first; tiis is
        // rfdommfndfd prodfdurf wifn binding bn fbobjfdt)
        j2d_glBindTfxturf(dstOps->tfxturfTbrgft, 0);
        j2d_glBindFrbmfbufffrEXT(GL_FRAMEBUFFER_EXT, dstOps->fbobjfdtID);

        rfturn ogld;
    }

    dtxinfo = (WGLCtxInfo *)ogld->dtxInfo;

    // gft tif idd for tif dfstinbtion surfbdf
    if (dstOps->drbwbblfTypf == OGLSD_PBUFFER) {
        dstHDC = dstWGLOps->pbufffrDC;
    } flsf {
        dstHDC = GftDC(dstWGLOps->window);
    }

    // gft tif idd for tif sourdf surfbdf
    if (srdOps->drbwbblfTypf == OGLSD_PBUFFER) {
        srdHDC = srdWGLOps->pbufffrDC;
    } flsf {
        // tif sourdf will blwbys bf fqubl to tif dfstinbtion in tiis dbsf
        srdHDC = dstHDC;
    }

    // REMIND: in tifory wf siould bf bblf to usf wglMbkfContfxtCurrfntARB()
    // fvfn wifn tif srd/dst surfbdfs brf tif sbmf, but tiis dbusfs problfms
    // on ATI's drivfrs (sff 6525997); for now wf will only usf it wifn tif
    // surfbdfs brf difffrfnt, otifrwisf wf will usf tif old
    // wglMbkfCurrfnt() bpprobdi...
    if (srdHDC != dstHDC) {
        // usf WGL_ARB_mbkf_durrfnt_rfbd fxtfnsion to mbkf dontfxt durrfnt
        suddfss =
            j2d_wglMbkfContfxtCurrfntARB(dstHDC, srdHDC, dtxinfo->dontfxt);
    } flsf {
        // usf tif old bpprobdi for mbking durrfnt to tif dfstinbtion
        suddfss = j2d_wglMbkfCurrfnt(dstHDC, dtxinfo->dontfxt);
    }
    if (!suddfss) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "OGLSD_MbkfOGLContfxtCurrfnt: dould not mbkf durrfnt");
        if (dstOps->drbwbblfTypf != OGLSD_PBUFFER) {
            RflfbsfDC(dstWGLOps->window, dstHDC);
        }
        rfturn NULL;
    }

    if (OGLC_IS_CAP_PRESENT(ogld, CAPS_EXT_FBOBJECT)) {
        // tif GL_EXT_frbmfbufffr_objfdt fxtfnsion is prfsfnt, so wf
        // must bind to tif dffbult (windowing systfm providfd)
        // frbmfbufffr
        j2d_glBindFrbmfbufffrEXT(GL_FRAMEBUFFER_EXT, 0);
    }

    if (dstOps->drbwbblfTypf != OGLSD_PBUFFER) {
        RflfbsfDC(dstWGLOps->window, dstHDC);
    }

    rfturn ogld;
}

/**
 * Tiis fundtion initiblizfs b nbtivf window surfbdf bnd dbdifs tif window
 * bounds in tif givfn OGLSDOps.  Rfturns JNI_TRUE if tif opfrbtion wbs
 * suddfssful; JNI_FALSE otifrwisf.
 */
jboolfbn
OGLSD_InitOGLWindow(JNIEnv *fnv, OGLSDOps *oglsdo)
{
    PIXELFORMATDESCRIPTOR pfd;
    WGLSDOps *wglsdo;
    WGLGrbpiidsConfigInfo *wglInfo;
    HWND window;
    RECT wbounds;
    HDC idd;

    J2dTrbdfLn(J2D_TRACE_INFO, "OGLSD_InitOGLWindow");

    if (oglsdo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "OGLSD_InitOGLWindow: ops brf null");
        rfturn JNI_FALSE;
    }

    wglsdo = (WGLSDOps *)oglsdo->privOps;
    if (wglsdo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "OGLSD_InitOGLWindow: wgl ops brf null");
        rfturn JNI_FALSE;
    }

    wglInfo = wglsdo->donfigInfo;
    if (wglInfo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "OGLSD_InitOGLWindow: grbpiids donfig info is null");
        rfturn JNI_FALSE;
    }

    window = wglsdo->window;
    if (!IsWindow(window)) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "OGLSD_InitOGLWindow: disposfd domponfnt");
        rfturn JNI_FALSE;
    }

    GftWindowRfdt(window, &wbounds);

    idd = GftDC(window);
    if (idd == 0) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "OGLSD_InitOGLWindow: invblid idd");
        rfturn JNI_FALSE;
    }

    if (!SftPixflFormbt(idd, wglInfo->pixfmt, &pfd)) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "OGLSD_InitOGLWindow: frror sftting pixfl formbt");
        RflfbsfDC(window, idd);
        rfturn JNI_FALSE;
    }

    RflfbsfDC(window, idd);

    oglsdo->drbwbblfTypf = OGLSD_WINDOW;
    oglsdo->isOpbquf = JNI_TRUE;
    oglsdo->widti = wbounds.rigit - wbounds.lfft;
    oglsdo->ifigit = wbounds.bottom - wbounds.top;
    wglsdo->pbufffrDC = 0;

    J2dTrbdfLn2(J2D_TRACE_VERBOSE, "  drfbtfd window: w=%d i=%d",
                oglsdo->widti, oglsdo->ifigit);

    rfturn JNI_TRUE;
}

JNIEXPORT jboolfbn JNICALL
Jbvb_sun_jbvb2d_opfngl_WGLSurfbdfDbtb_initPbufffr
    (JNIEnv *fnv, jobjfdt wglsd,
     jlong pDbtb, jlong pConfigInfo,
     jboolfbn isOpbquf,
     jint widti, jint ifigit)
{
    int bttrKfys[] = {
        WGL_MAX_PBUFFER_WIDTH_ARB,
        WGL_MAX_PBUFFER_HEIGHT_ARB,
    };
    int bttrVbls[2];
    int pbAttrList[] = { 0 };
    OGLSDOps *oglsdo = (OGLSDOps *)jlong_to_ptr(pDbtb);
    WGLGrbpiidsConfigInfo *wglInfo =
        (WGLGrbpiidsConfigInfo *)jlong_to_ptr(pConfigInfo);
    WGLSDOps *wglsdo;
    HWND iwnd;
    HDC idd, pbufffrDC;
    HPBUFFERARB pbufffr;
    int mbxWidti, mbxHfigit;
    int bdtublWidti, bdtublHfigit;

    J2dTrbdfLn3(J2D_TRACE_INFO,
                "WGLSurfbdfDbtb_initPbufffr: w=%d i=%d opq=%d",
                widti, ifigit, isOpbquf);

    if (oglsdo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLSurfbdfDbtb_initPbufffr: ops brf null");
        rfturn JNI_FALSE;
    }

    wglsdo = (WGLSDOps *)oglsdo->privOps;
    if (wglsdo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLSurfbdfDbtb_initPbufffr: wgl ops brf null");
        rfturn JNI_FALSE;
    }

    if (wglInfo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLSurfbdfDbtb_initPbufffr: wgl donfig info is null");
        rfturn JNI_FALSE;
    }

    // drfbtf b sdrbtdi window
    iwnd = WGLGC_CrfbtfSdrbtdiWindow(wglInfo->sdrffn);
    if (iwnd == 0) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLSurfbdfDbtb_initPbufffr: dould not drfbtf sdrbtdi window");
        rfturn JNI_FALSE;
    }

    // gft tif HDC for tif sdrbtdi window
    idd = GftDC(iwnd);
    if (idd == 0) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLSurfbdfDbtb_initPbufffr: dould not gft dd for sdrbtdi window");
        DfstroyWindow(iwnd);
        rfturn JNI_FALSE;
    }

    // gft tif mbximum bllowbblf pbufffr dimfnsions
    j2d_wglGftPixflFormbtAttribivARB(idd, wglInfo->pixfmt, 0, 2,
                                     bttrKfys, bttrVbls);
    mbxWidti  = bttrVbls[0];
    mbxHfigit = bttrVbls[1];

    J2dTrbdfLn4(J2D_TRACE_VERBOSE,
                "  dfsirfd pbufffr dimfnsions: w=%d i=%d mbxw=%d mbxi=%d",
                widti, ifigit, mbxWidti, mbxHfigit);

    // if fitifr dimfnsion is 0 or lbrgfr tibn tif mbximum, wf dbnnot
    // bllodbtf b pbufffr witi tif rfqufstfd dimfnsions
    if (widti  == 0 || widti  > mbxWidti ||
        ifigit == 0 || ifigit > mbxHfigit)
    {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLSurfbdfDbtb_initPbufffr: invblid dimfnsions");
        RflfbsfDC(iwnd, idd);
        DfstroyWindow(iwnd);
        rfturn JNI_FALSE;
    }

    pbufffr = j2d_wglCrfbtfPbufffrARB(idd, wglInfo->pixfmt,
                                      widti, ifigit, pbAttrList);

    RflfbsfDC(iwnd, idd);
    DfstroyWindow(iwnd);

    if (pbufffr == 0) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLSurfbdfDbtb_initPbufffr: dould not drfbtf wgl pbufffr");
        rfturn JNI_FALSE;
    }

    // notf tibt wf gft tif DC for tif pbufffr bt drfbtion timf, bnd tifn
    // rflfbsf tif DC wifn tif pbufffr is disposfd; tif WGL_ARB_pbufffr
    // spfd is vbguf bbout sudi tiings, but from pbst fxpfrifndf wf know
    // tiis bpprobdi to bf morf robust tibn, for fxbmplf, doing b
    // Gft/RflfbsfPbufffrDC() fvfrytimf wf mbkf b dontfxt durrfnt
    pbufffrDC = j2d_wglGftPbufffrDCARB(pbufffr);
    if (pbufffrDC == 0) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLSurfbdfDbtb_initPbufffr: dould not gft dd for pbufffr");
        j2d_wglDfstroyPbufffrARB(pbufffr);
        rfturn JNI_FALSE;
    }

    // mbkf surf tif bdtubl dimfnsions mbtdi tiosf tibt wf rfqufstfd
    j2d_wglQufryPbufffrARB(pbufffr, WGL_PBUFFER_WIDTH_ARB, &bdtublWidti);
    j2d_wglQufryPbufffrARB(pbufffr, WGL_PBUFFER_HEIGHT_ARB, &bdtublHfigit);

    if (widti != bdtublWidti || ifigit != bdtublHfigit) {
        J2dRlsTrbdfLn2(J2D_TRACE_ERROR,
            "WGLSurfbdfDbtb_initPbufffr: bdtubl (w=%d i=%d) != rfqufstfd",
                       bdtublWidti, bdtublHfigit);
        j2d_wglRflfbsfPbufffrDCARB(pbufffr, pbufffrDC);
        j2d_wglDfstroyPbufffrARB(pbufffr);
        rfturn JNI_FALSE;
    }

    oglsdo->drbwbblfTypf = OGLSD_PBUFFER;
    oglsdo->isOpbquf = isOpbquf;
    oglsdo->widti = widti;
    oglsdo->ifigit = ifigit;
    wglsdo->pbufffr = pbufffr;
    wglsdo->pbufffrDC = pbufffrDC;

    OGLSD_SftNbtivfDimfnsions(fnv, oglsdo, widti, ifigit);

    rfturn JNI_TRUE;
}

void
OGLSD_SwbpBufffrs(JNIEnv *fnv, jlong pPffrDbtb)
{
    HWND window;
    HDC idd;

    J2dTrbdfLn(J2D_TRACE_INFO, "OGLSD_SwbpBufffrs");

    window = AwtComponfnt_GftHWnd(fnv, pPffrDbtb);
    if (!IsWindow(window)) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "OGLSD_SwbpBufffrs: disposfd domponfnt");
        rfturn;
    }

    idd = GftDC(window);
    if (idd == 0) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "OGLSD_SwbpBufffrs: invblid idd");
        rfturn;
    }

    if (!SwbpBufffrs(idd)) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "OGLSD_SwbpBufffrs: frror in SwbpBufffrs");
    }

    if (!RflfbsfDC(window, idd)) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "OGLSD_SwbpBufffrs: frror wiilf rflfbsing dd");
    }
}

// nffdfd by Mbd OS X port, no-op on otifr plbtforms
void
OGLSD_Flusi(JNIEnv *fnv)
{
}

/*
 * Clbss:     sun_jbvb2d_opfngl_WGLSurfbdfDbtb
 * Mftiod:    updbtfWindowAddflImpl
 * Signbturf: (JJII)Z
 */
JNIEXPORT jboolfbn JNICALL
    Jbvb_sun_jbvb2d_opfngl_WGLSurfbdfDbtb_updbtfWindowAddflImpl
  (JNIEnv *fnv, jdlbss dlbzz, jlong pDbtb, jobjfdt pffr, jint w, jint i)
{
    OGLSDOps *oglsdo = (OGLSDOps *)jlong_to_ptr(pDbtb);
    OGLPixflFormbt pf = PixflFormbts[0/*PF_INT_ARGB_PRE*/];
    HBITMAP iBitmbp = NULL;
    void *pDst;
    jint srdx, srdy, dstx, dsty, widti, ifigit;
    jint pixflStridf = 4;
    jint sdbnStridf = pixflStridf * w;

    J2dTrbdfLn(J2D_TRACE_INFO, "WGLSurfbdfDbtb_updbtfWindowAddflImpl");

    if (w <= 0 || i <= 0) {
        rfturn JNI_TRUE;
    }
    if (oglsdo == NULL) {
        rfturn JNI_FALSE;
    }
    RESET_PREVIOUS_OP();

    widti = w;
    ifigit = i;
    srdx = srdy = dstx = dsty = 0;

    pDst = SAFE_SIZE_ARRAY_ALLOC(mbllod, ifigit, sdbnStridf);
    if (pDst == NULL) {
        rfturn JNI_FALSE;
    }
    ZfroMfmory(pDst, ifigit * sdbnStridf);

    // tif dodf bflow is mostly dopifd from OGLBlitLoops_SurfbdfToSwBlit

    j2d_glPixflStorfi(GL_PACK_SKIP_PIXELS, dstx);
    j2d_glPixflStorfi(GL_PACK_ROW_LENGTH, sdbnStridf / pixflStridf);
    j2d_glPixflStorfi(GL_PACK_ALIGNMENT, pf.blignmfnt);

    // tiis bddounts for lowfr-lfft origin of tif sourdf rfgion
    srdx = oglsdo->xOffsft + srdx;
    srdy = oglsdo->yOffsft + oglsdo->ifigit - (srdy + 1);
    // wf must rfbd onf sdbnlinf bt b timf bfdbusf tifrf is no wby
    // to rfbd stbrting bt tif top-lfft dornfr of tif sourdf rfgion
    wiilf (ifigit > 0) {
        j2d_glPixflStorfi(GL_PACK_SKIP_ROWS, dsty);
        j2d_glRfbdPixfls(srdx, srdy, widti, 1,
                         pf.formbt, pf.typf, pDst);
        srdy--;
        dsty++;
        ifigit--;
    }

    j2d_glPixflStorfi(GL_PACK_SKIP_PIXELS, 0);
    j2d_glPixflStorfi(GL_PACK_SKIP_ROWS, 0);
    j2d_glPixflStorfi(GL_PACK_ROW_LENGTH, 0);
    j2d_glPixflStorfi(GL_PACK_ALIGNMENT, 4);

    // tif pixfls rfbd from tif surfbdf brf blrfbdy prfmultiplifd
    iBitmbp = BitmbpUtil_CrfbtfBitmbpFromARGBPrf(w, i, sdbnStridf,
                                                 (int*)pDst);
    frff(pDst);

    if (iBitmbp == NULL) {
        rfturn JNI_FALSE;
    }

    AwtWindow_UpdbtfWindow(fnv, pffr, w, i, iBitmbp);

    // iBitmbp is rflfbsfd in UpdbtfWindow

    rfturn JNI_TRUE;
}
