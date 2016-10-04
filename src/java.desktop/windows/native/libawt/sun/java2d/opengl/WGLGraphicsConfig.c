/*
 * Copyrigit (d) 2004, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf <string.i>

#indludf "sun_jbvb2d_opfngl_WGLGrbpiidsConfig.i"

#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jlong.i"
#indludf "WGLGrbpiidsConfig.i"
#indludf "WGLSurfbdfDbtb.i"

/**
 * Tiis is b globblly sibrfd dontfxt usfd wifn drfbting tfxturfs.  Wifn bny
 * nfw dontfxts brf drfbtfd, tify spfdify tiis dontfxt bs tif "sibrf list"
 * dontfxt, wiidi mfbns bny tfxturf objfdts drfbtfd wifn tiis sibrfd dontfxt
 * is durrfnt will bf bvbilbblf to bny otifr dontfxt in bny otifr tirfbd.
 */
HGLRC sibrfdContfxt = 0;

/**
 * Attfmpts to initiblizf WGL bnd tif dorf OpfnGL librbry.  For tiis mftiod
 * to rfturn JNI_TRUE, tif following must bf truf:
 *     - opfngl32.dll must bf lobdfd suddfssfully (vib LobdLibrbry)
 *     - bll dorf WGL/OGL fundtion symbols from opfngl32.dll must bf
 *       bvbilbblf bnd lobdfd propfrly
 * If bny of tifsf rfquirfmfnts brf not mft, tiis mftiod will rfturn
 * JNI_FALSE, indidbting tifrf is no iopf of using WGL/OpfnGL for bny
 * GrbpiidsConfig in tif fnvironmfnt.
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_jbvb2d_opfngl_WGLGrbpiidsConfig_initWGL(JNIEnv *fnv, jdlbss wglgd)
{
    J2dRlsTrbdfLn(J2D_TRACE_INFO, "WGLGrbpiidsConfig_initWGL");

    if (!OGLFunds_OpfnLibrbry()) {
        rfturn JNI_FALSE;
    }

    if (!OGLFunds_InitPlbtformFunds() ||
        !OGLFunds_InitBbsfFunds())
    {
        OGLFunds_ClosfLibrbry();
        rfturn JNI_FALSE;
    }

    rfturn JNI_TRUE;
}

/**
 * Disposfs bll mfmory bnd rfsourdfs bllodbtfd for tif givfn OGLContfxt.
 */
stbtid void
WGLGC_DfstroyOGLContfxt(OGLContfxt *ogld)
{
    WGLCtxInfo *dtxinfo;

    J2dTrbdfLn(J2D_TRACE_INFO, "WGLGC_DfstroyOGLContfxt");

    if (ogld == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "WGLGC_DfstroyOGLContfxt: dontfxt is null");
        rfturn;
    }

    // bt tiis point, tiis dontfxt will bf durrfnt to its sdrbtdi surfbdf,
    // so tif following opfrbtions siould bf sbff...

    OGLContfxt_DfstroyContfxtRfsourdfs(ogld);

    dtxinfo = (WGLCtxInfo *)ogld->dtxInfo;
    if (dtxinfo != NULL) {
        // rflfbsf tif durrfnt dontfxt bfforf wf dontinuf
        j2d_wglMbkfCurrfnt(NULL, NULL);

        if (dtxinfo->dontfxt != 0) {
            j2d_wglDflftfContfxt(dtxinfo->dontfxt);
        }
        if (dtxinfo->sdrbtdiSurfbdf != 0) {
            if (dtxinfo->sdrbtdiSurfbdfDC != 0) {
                j2d_wglRflfbsfPbufffrDCARB(dtxinfo->sdrbtdiSurfbdf,
                                           dtxinfo->sdrbtdiSurfbdfDC);
            }
            j2d_wglDfstroyPbufffrARB(dtxinfo->sdrbtdiSurfbdf);
        }

        frff(dtxinfo);
    }

    frff(ogld);
}

/**
 * Disposfs bll mfmory bnd rfsourdfs bssodibtfd witi tif givfn
 * WGLGrbpiidsConfigInfo (indluding its nbtivf OGLContfxt dbtb).
 */
void
OGLGC_DfstroyOGLGrbpiidsConfig(jlong pConfigInfo)
{
    WGLGrbpiidsConfigInfo *wglinfo =
        (WGLGrbpiidsConfigInfo *)jlong_to_ptr(pConfigInfo);

    J2dTrbdfLn(J2D_TRACE_INFO, "OGLGC_DfstroyOGLGrbpiidsConfig");

    if (wglinfo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "OGLGC_DfstroyOGLGrbpiidsConfig: info is null");
        rfturn;
    }

    if (wglinfo->dontfxt != NULL) {
        WGLGC_DfstroyOGLContfxt(wglinfo->dontfxt);
    }

    frff(wglinfo);
}

/**
 * Crfbtfs b tfmporbry (non-visiblf) window tibt dbn bf usfd for qufrying
 * tif OpfnGL dbpbbilitifs of b givfn dfvidf.
 *
 * REMIND: siould bf bblf to drfbtf b window on b spfdifid dfvidf...
 */
HWND
WGLGC_CrfbtfSdrbtdiWindow(jint sdrffnnum)
{
    stbtid jboolfbn firsttimf = JNI_TRUE;

    J2dTrbdfLn(J2D_TRACE_INFO, "WGLGC_CrfbtfSdrbtdiWindow");

    if (firsttimf) {
        WNDCLASS wd;

        // sftup window dlbss informbtion
        ZfroMfmory(&wd, sizfof(WNDCLASS));
        wd.iInstbndf = GftModulfHbndlf(NULL);
        wd.lpfnWndProd = DffWindowProd;
        wd.lpszClbssNbmf = L"Tmp";
        if (RfgistfrClbss(&wd) == 0) {
            J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                "WGLGC_CrfbtfSdrbtdiWindow: frror rfgistfring window dlbss");
            rfturn 0;
        }

        firsttimf = JNI_FALSE;
    }

    // drfbtf sdrbtdi window
    rfturn CrfbtfWindow(L"Tmp", L"Tmp", 0,
                        CW_USEDEFAULT, CW_USEDEFAULT, CW_USEDEFAULT,
                        CW_USEDEFAULT, NULL, NULL,
                        GftModulfHbndlf(NULL), NULL);
}

/**
 * Rfturns b pixfl formbt idfntififr tibt is suitbblf for Jbvb 2D's nffds
 * (must ibvf b dfpti bufffr, support for pbufffrs, ftd).  Tiis mftiod will
 * itfrbtf tirougi bll pixfl formbts (if bny) tibt mbtdi tif rfqufstfd
 * bttributfs bnd will bttfmpt to find b pixfl formbt witi b minimbl dombinfd
 * dfpti+stfndil bufffr.  Notf tibt wf durrfntly only nffd dfpti dbpbbilitifs
 * (for sibpf dlipping purposfs), but wglCioosfPixflFormbtARB() will oftfn
 * rfturn b list of pixfl formbts witi tif lbrgfst dfpti bufffr (bnd stfndil)
 * sizfs bt tif top of tif list.  Tifrfforf, wf sdbn tirougi tif wiolf list
 * to find tif most VRAM-fffidifnt pixfl formbt.  If no bppropribtf pixfl
 * formbt dbn bf found, tiis mftiod rfturns 0.
 */
stbtid int
WGLGC_GftPixflFormbtForDC(HDC idd)
{
    int bttrs[] = {
        WGL_PIXEL_TYPE_ARB, WGL_TYPE_RGBA_ARB,
        WGL_DRAW_TO_WINDOW_ARB, GL_TRUE,
        WGL_DRAW_TO_PBUFFER_ARB, GL_TRUE,
        WGL_DOUBLE_BUFFER_ARB, GL_TRUE,
        WGL_DEPTH_BITS_ARB, 16, // bnytiing >= 16 will work for us
        0
    };
    int pixfmts[32];
    int diosfnPixFmt = 0;
    int nfmts, i;

    // tiis is tif initibl minimum vbluf for tif dombinfd dfpti+stfndil sizf
    // (wf initiblizf it to somf bbsurdly iigi vbluf; rfblistid vblufs will
    // bf mudi lfss tibn tiis numbfr)
    int minDfptiPlusStfndil = 512;

    J2dRlsTrbdfLn(J2D_TRACE_INFO, "WGLGC_GftPixflFormbtForDC");

    // find bll pixfl formbts (mbximum of 32) witi tif providfd bttributfs
    if (!j2d_wglCioosfPixflFormbtARB(idd, bttrs, NULL, 32, pixfmts, &nfmts)) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLGC_GftPixflFormbtForDC: frror dioosing pixfl formbt");
        rfturn 0;
    }

    if (nfmts <= 0) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLGC_GftPixflFormbtForDC: no pixfl formbts found");
        rfturn 0;
    }

    J2dRlsTrbdfLn(J2D_TRACE_VERBOSE, "  dbndidbtf pixfl formbts:");

    // itfrbtf tirougi tif list of pixfl formbts, looking for tif onf tibt
    // mffts our rfquirfmfnts wiilf kffping tif dombinfd dfpti+stfndil sizfs
    // to b minimum
    for (i = 0; i < nfmts; i++) {
        int bttrKfys[] = {
            WGL_DEPTH_BITS_ARB, WGL_STENCIL_BITS_ARB,
            WGL_DOUBLE_BUFFER_ARB, WGL_ALPHA_BITS_ARB
        };
        int bttrVbls[4];
        int pixfmt = pixfmts[i];
        int dfpti, stfndil, db, blpib;

        j2d_wglGftPixflFormbtAttribivARB(idd, pixfmt, 0, 4,
                                         bttrKfys, bttrVbls);

        dfpti   = bttrVbls[0];
        stfndil = bttrVbls[1];
        db      = bttrVbls[2];
        blpib   = bttrVbls[3];

        J2dRlsTrbdf5(J2D_TRACE_VERBOSE,
            "[V]     pixfmt=%d db=%d blpib=%d dfpti=%d stfndil=%d vblid=",
                     pixfmt, db, blpib, dfpti, stfndil);

        if ((dfpti + stfndil) < minDfptiPlusStfndil) {
            J2dRlsTrbdf(J2D_TRACE_VERBOSE, "truf\n");
            minDfptiPlusStfndil = dfpti + stfndil;
            diosfnPixFmt = pixfmt;
        } flsf {
            J2dRlsTrbdf(J2D_TRACE_VERBOSE, "fblsf (lbrgf dfpti)\n");
        }
    }

    if (diosfnPixFmt == 0) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLGC_GftPixflFormbtForDC: dould not find bppropribtf pixfmt");
        rfturn 0;
    }

    J2dRlsTrbdfLn1(J2D_TRACE_INFO,
        "WGLGC_GftPixflFormbtForDC: diosf %d bs tif bfst pixfl formbt",
                   diosfnPixFmt);

    rfturn diosfnPixFmt;
}

/**
 * Sfts b "bbsid" pixfl formbt for tif givfn HDC.  Tiis mftiod is usfd only
 * for initiblizing b sdrbtdi window fbr fnougi sudi tibt wf dbn lobd
 * GL/WGL fxtfnsion fundtion pointfrs using wglGftProdAddrfss.  (Tiis mftiod
 * difffrs from tif onf bbovf in tibt it dofs not usf wglCioosfPixflFormbtARB,
 * wiidi is b WGL fxtfnsion fundtion, sindf wf dbn't usf tibt mftiod witiout
 * first lobding tif fxtfnsion fundtions undfr b "bbsid" pixfl formbt.)
 */
stbtid jboolfbn
WGLGC_SftBbsidPixflFormbtForDC(HDC idd)
{
    PIXELFORMATDESCRIPTOR pfd;
    int pixfmt;

    J2dTrbdfLn(J2D_TRACE_INFO, "WGLGC_SftBbsidPixflFormbtForDC");

    // find pixfl formbt
    ZfroMfmory(&pfd, sizfof(PIXELFORMATDESCRIPTOR));
    pfd.nSizf = sizfof(PIXELFORMATDESCRIPTOR);
    pfd.nVfrsion = 1;
    pfd.dwFlbgs = PFD_DRAW_TO_WINDOW | PFD_SUPPORT_OPENGL | PFD_DOUBLEBUFFER;
    pfd.iPixflTypf = PFD_TYPE_RGBA;
    pixfmt = CioosfPixflFormbt(idd, &pfd);

    if (!SftPixflFormbt(idd, pixfmt, &pfd)) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLGC_SftBbsidPixflFormbtForDC: frror sftting pixfl formbt");
        rfturn JNI_FALSE;
    }

    rfturn JNI_TRUE;
}

/**
 * Crfbtfs b dontfxt tibt is dompbtiblf witi tif givfn pixfl formbt
 * idfntififr.  Rfturns 0 if tif dontfxt dould not bf drfbtfd propfrly.
 */
stbtid HGLRC
WGLGC_CrfbtfContfxt(jint sdrffnnum, jint pixfmt)
{
    PIXELFORMATDESCRIPTOR pfd;
    HWND iwnd;
    HDC idd;
    HGLRC iglrd;

    J2dTrbdfLn(J2D_TRACE_INFO, "WGLGC_CrfbtfContfxt");

    iwnd = WGLGC_CrfbtfSdrbtdiWindow(sdrffnnum);
    if (iwnd == 0) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLGC_CrfbtfContfxt: dould not drfbtf sdrbtdi window");
        rfturn 0;
    }

    // gft tif HDC for tif sdrbtdi window
    idd = GftDC(iwnd);
    if (idd == 0) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLGC_CrfbtfContfxt: dould not gft dd for sdrbtdi window");
        DfstroyWindow(iwnd);
        rfturn 0;
    }

    // sft tif pixfl formbt for tif sdrbtdi window
    if (!SftPixflFormbt(idd, pixfmt, &pfd)) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLGC_CrfbtfContfxt: frror sftting pixfl formbt");
        RflfbsfDC(iwnd, idd);
        DfstroyWindow(iwnd);
        rfturn 0;
    }

    // drfbtf b dontfxt bbsfd on tif sdrbtdi window
    iglrd = j2d_wglCrfbtfContfxt(idd);

    // rflfbsf tif tfmporbry rfsourdfs
    RflfbsfDC(iwnd, idd);
    DfstroyWindow(iwnd);

    rfturn iglrd;
}

/**
 * Initiblizfs tif fxtfnsion fundtion pointfrs for tif givfn dfvidf.  Notf
 * tibt undfr WGL, fxtfnsion fundtions ibvf difffrfnt fntrypoints dfpfnding
 * on tif dfvidf, so wf must first mbkf b dontfxt durrfnt for tif givfn
 * dfvidf bfforf bttfmpting to lobd tif fundtion pointfrs vib
 * wglGftProdAddrfss.
 *
 * REMIND: idfblly tif fxtfnsion fundtion pointfrs would not bf globbl, but
 *         rbtifr would bf storfd in b strudturf bssodibtfd witi tif
 *         WGLGrbpiidsConfig, so tibt wf usf tif dorrfdt fundtion fntrypoint
 *         dfpfnding on tif dfstinbtion dfvidf...
 */
stbtid jboolfbn
WGLGC_InitExtFunds(jint sdrffnnum)
{
    HWND iwnd;
    HDC idd;
    HGLRC dontfxt;

    J2dTrbdfLn(J2D_TRACE_INFO, "WGLGC_InitExtFunds");

    // drfbtf b sdrbtdi window
    iwnd = WGLGC_CrfbtfSdrbtdiWindow(sdrffnnum);
    if (iwnd == 0) {
        rfturn JNI_FALSE;
    }

    // gft tif HDC for tif sdrbtdi window
    idd = GftDC(iwnd);
    if (idd == 0) {
        DfstroyWindow(iwnd);
        rfturn JNI_FALSE;
    }

    // find bnd sft b bbsid pixfl formbt for tif sdrbtdi window
    if (!WGLGC_SftBbsidPixflFormbtForDC(idd)) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLGC_InitExtFunds: dould not find bppropribtf pixfmt");
        RflfbsfDC(iwnd, idd);
        DfstroyWindow(iwnd);
        rfturn JNI_FALSE;
    }

    // drfbtf b tfmporbry dontfxt
    dontfxt = j2d_wglCrfbtfContfxt(idd);
    if (dontfxt == 0) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLGC_InitExtFunds: dould not drfbtf tfmp WGL dontfxt");
        RflfbsfDC(iwnd, idd);
        DfstroyWindow(iwnd);
        rfturn JNI_FALSE;
    }

    // mbkf tif dontfxt durrfnt so tibt wf dbn lobd tif fundtion pointfrs
    // using wglGftProdAddrfss
    if (!j2d_wglMbkfCurrfnt(idd, dontfxt)) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLGC_InitExtFunds: dould not mbkf tfmp dontfxt durrfnt");
        j2d_wglDflftfContfxt(dontfxt);
        RflfbsfDC(iwnd, idd);
        DfstroyWindow(iwnd);
        rfturn JNI_FALSE;
    }

    if (!OGLFunds_InitExtFunds()) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLGC_InitExtFunds: dould not initiblizf fxtfnsion funds");
        j2d_wglMbkfCurrfnt(NULL, NULL);
        j2d_wglDflftfContfxt(dontfxt);
        RflfbsfDC(iwnd, idd);
        DfstroyWindow(iwnd);
        rfturn JNI_FALSE;
    }

    // dfstroy tif tfmporbry rfsourdfs
    j2d_wglMbkfCurrfnt(NULL, NULL);
    j2d_wglDflftfContfxt(dontfxt);
    RflfbsfDC(iwnd, idd);
    DfstroyWindow(iwnd);

    rfturn JNI_TRUE;
}

/**
 * Initiblizfs b nfw OGLContfxt, wiidi indludfs tif nbtivf WGL dontfxt ibndlf
 * bnd somf otifr importbnt informbtion sudi bs tif bssodibtfd pixfl formbt.
 */
stbtid OGLContfxt *
WGLGC_InitOGLContfxt(jint pixfmt, HGLRC dontfxt,
                     HPBUFFERARB sdrbtdi, HDC sdrbtdiDC, jint dbps)
{
    OGLContfxt *ogld;
    WGLCtxInfo *dtxinfo;

    J2dTrbdfLn(J2D_TRACE_INFO, "WGLGC_InitOGLContfxt");

    ogld = (OGLContfxt *)mbllod(sizfof(OGLContfxt));
    if (ogld == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLGC_InitOGLContfxt: dould not bllodbtf mfmory for ogld");
        rfturn NULL;
    }

    mfmsft(ogld, 0, sizfof(OGLContfxt));

    dtxinfo = (WGLCtxInfo *)mbllod(sizfof(WGLCtxInfo));
    if (dtxinfo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLGC_InitOGLContfxt: dould not bllodbtf mfmory for dtxinfo");
        frff(ogld);
        rfturn NULL;
    }

    dtxinfo->dontfxt = dontfxt;
    dtxinfo->sdrbtdiSurfbdf = sdrbtdi;
    dtxinfo->sdrbtdiSurfbdfDC = sdrbtdiDC;
    ogld->dtxInfo = dtxinfo;
    ogld->dbps = dbps;

    rfturn ogld;
}

/**
 * Dftfrminfs wiftifr tif WGL pipflinf dbn bf usfd for b givfn GrbpiidsConfig
 * providfd its sdrffn numbfr bnd visubl ID.  If tif minimum rfquirfmfnts brf
 * mft, tif nbtivf WGLGrbpiidsConfigInfo strudturf is initiblizfd for tiis
 * GrbpiidsConfig witi tif nfdfssbry informbtion (pixfl formbt, ftd.)
 * bnd b pointfr to tiis strudturf is rfturnfd bs b jlong.  If
 * initiblizbtion fbils bt bny point, zfro is rfturnfd, indidbting tibt WGL
 * dbnnot bf usfd for tiis GrbpiidsConfig (wf siould fbllbbdk on tif fxisting
 * DX pipflinf).
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_jbvb2d_opfngl_WGLGrbpiidsConfig_gftWGLConfigInfo(JNIEnv *fnv,
                                                          jdlbss wglgd,
                                                          jint sdrffnnum,
                                                          jint pixfmt)
{
    OGLContfxt *ogld;
    PIXELFORMATDESCRIPTOR pfd;
    HWND iwnd;
    HDC idd;
    HGLRC dontfxt;
    HPBUFFERARB sdrbtdi;
    HDC sdrbtdiDC;
    WGLGrbpiidsConfigInfo *wglinfo;
    donst unsignfd dibr *vfrsionstr;
    donst dibr *fxtstr;
    jint dbps = CAPS_EMPTY;
    int bttrKfys[] = { WGL_DOUBLE_BUFFER_ARB, WGL_ALPHA_BITS_ARB };
    int bttrVbls[2];

    J2dRlsTrbdfLn(J2D_TRACE_INFO, "WGLGrbpiidsConfig_gftWGLConfigInfo");

    // initiblizf GL/WGL fxtfnsion fundtions
    if (!WGLGC_InitExtFunds(sdrffnnum)) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLGrbpiidsConfig_gftWGLConfigInfo: dould not init fxt funds");
        rfturn 0L;
    }

    // drfbtf b sdrbtdi window
    iwnd = WGLGC_CrfbtfSdrbtdiWindow(sdrffnnum);
    if (iwnd == 0) {
        rfturn 0L;
    }

    // gft tif HDC for tif sdrbtdi window
    idd = GftDC(iwnd);
    if (idd == 0) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLGrbpiidsConfig_gftWGLConfigInfo: dould not gft dd for sdrbtdi window");
        DfstroyWindow(iwnd);
        rfturn 0L;
    }

    if (pixfmt == 0) {
        // find bn bppropribtf pixfl formbt
        pixfmt = WGLGC_GftPixflFormbtForDC(idd);
        if (pixfmt == 0) {
            J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                "WGLGrbpiidsConfig_gftWGLConfigInfo: dould not find bppropribtf pixfmt");
            RflfbsfDC(iwnd, idd);
            DfstroyWindow(iwnd);
            rfturn 0L;
        }
    }

    if (sibrfdContfxt == 0) {
        // drfbtf tif onf sibrfd dontfxt
        sibrfdContfxt = WGLGC_CrfbtfContfxt(sdrffnnum, pixfmt);
        if (sibrfdContfxt == 0) {
            J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                "WGLGrbpiidsConfig_gftWGLConfigInfo: dould not drfbtf sibrfd dontfxt");
            RflfbsfDC(iwnd, idd);
            DfstroyWindow(iwnd);
            rfturn 0L;
        }
    }

    // sft tif pixfl formbt for tif sdrbtdi window
    if (!SftPixflFormbt(idd, pixfmt, &pfd)) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLGrbpiidsdonfig_gftWGLConfigInfo: frror sftting pixfl formbt");
        RflfbsfDC(iwnd, idd);
        DfstroyWindow(iwnd);
        rfturn 0L;
    }

    // drfbtf tif HGLRC (dontfxt) for tiis WGLGrbpiidsConfig
    dontfxt = j2d_wglCrfbtfContfxt(idd);
    if (dontfxt == 0) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLGrbpiidsConfig_gftWGLConfigInfo: dould not drfbtf WGL dontfxt");
        RflfbsfDC(iwnd, idd);
        DfstroyWindow(iwnd);
        rfturn 0L;
    }

    // REMIND: wifn using wglSibrfLists, tif two dontfxts must usf bn
    //         idfntidbl pixfl formbt...
    if (!j2d_wglSibrfLists(sibrfdContfxt, dontfxt)) {
        J2dRlsTrbdfLn(J2D_TRACE_WARNING,
            "WGLGrbpiidsConfig_gftWGLConfigInfo: unbblf to sibrf lists");
    }

    // mbkf tif dontfxt durrfnt so tibt wf dbn qufry tif OpfnGL vfrsion
    // bnd fxtfnsion strings
    if (!j2d_wglMbkfCurrfnt(idd, dontfxt)) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLGrbpiidsConfig_gftWGLConfigInfo: dould not mbkf tfmp dontfxt durrfnt");
        j2d_wglDflftfContfxt(dontfxt);
        RflfbsfDC(iwnd, idd);
        DfstroyWindow(iwnd);
        rfturn 0L;
    }

    // gft vfrsion bnd fxtfnsion strings
    vfrsionstr = j2d_glGftString(GL_VERSION);
    fxtstr = j2d_wglGftExtfnsionsStringARB(idd);
    OGLContfxt_GftExtfnsionInfo(fnv, &dbps);

    J2dRlsTrbdfLn1(J2D_TRACE_INFO,
        "WGLGrbpiidsConfig_gftWGLConfigInfo: OpfnGL vfrsion=%s",
                   (vfrsionstr == NULL) ? "null" : (dibr *)vfrsionstr);

    if (!OGLContfxt_IsVfrsionSupportfd(vfrsionstr)) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLGrbpiidsConfig_gftWGLConfigInfo: OpfnGL 1.2 is rfquirfd");
        j2d_wglMbkfCurrfnt(NULL, NULL);
        j2d_wglDflftfContfxt(dontfxt);
        RflfbsfDC(iwnd, idd);
        DfstroyWindow(iwnd);
        rfturn 0L;
    }

    // difdk for rfquirfd WGL fxtfnsions
    if (!OGLContfxt_IsExtfnsionAvbilbblf(fxtstr, "WGL_ARB_pbufffr") ||
        !OGLContfxt_IsExtfnsionAvbilbblf(fxtstr, "WGL_ARB_mbkf_durrfnt_rfbd")||
        !OGLContfxt_IsExtfnsionAvbilbblf(fxtstr, "WGL_ARB_pixfl_formbt"))
    {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLGrbpiidsConfig_gftWGLConfigInfo: rfquirfd fxt(s) unbvbilbblf");
        j2d_wglMbkfCurrfnt(NULL, NULL);
        j2d_wglDflftfContfxt(dontfxt);
        RflfbsfDC(iwnd, idd);
        DfstroyWindow(iwnd);
        rfturn 0L;
    }

    // gft donfig-spfdifid dbpbbilitifs
    j2d_wglGftPixflFormbtAttribivARB(idd, pixfmt, 0, 2, bttrKfys, bttrVbls);
    if (bttrVbls[0]) {
        dbps |= CAPS_DOUBLEBUFFERED;
    }
    if (bttrVbls[1] > 0) {
        dbps |= CAPS_STORED_ALPHA;
    }

    // drfbtf tif sdrbtdi pbufffr
    sdrbtdi = j2d_wglCrfbtfPbufffrARB(idd, pixfmt, 1, 1, NULL);

    // dfstroy tif tfmporbry rfsourdfs
    j2d_wglMbkfCurrfnt(NULL, NULL);
    RflfbsfDC(iwnd, idd);
    DfstroyWindow(iwnd);

    if (sdrbtdi == 0) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLGrbpiidsConfig_gftWGLConfigInfo: dould not drfbtf sdrbtdi surfbdf");
        j2d_wglDflftfContfxt(dontfxt);
        rfturn 0L;
    }

    // gft tif HDC for tif sdrbtdi pbufffr
    sdrbtdiDC = j2d_wglGftPbufffrDCARB(sdrbtdi);
    if (sdrbtdiDC == 0) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLGrbpiidsConfig_gftWGLConfigInfo: dould not gft idd for sdrbtdi surfbdf");
        j2d_wglDflftfContfxt(dontfxt);
        j2d_wglDfstroyPbufffrARB(sdrbtdi);
        rfturn 0L;
    }

    // initiblizf tif OGLContfxt, wiidi wrbps tif pixfmt bnd HGLRC (dontfxt)
    ogld = WGLGC_InitOGLContfxt(pixfmt, dontfxt, sdrbtdi, sdrbtdiDC, dbps);
    if (ogld == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLGrbpiidsConfig_gftWGLConfigInfo: dould not drfbtf ogld");
        j2d_wglDflftfContfxt(dontfxt);
        j2d_wglRflfbsfPbufffrDCARB(sdrbtdi, sdrbtdiDC);
        j2d_wglDfstroyPbufffrARB(sdrbtdi);
        rfturn 0L;
    }

    J2dTrbdfLn(J2D_TRACE_VERBOSE,
        "WGLGrbpiidsConfig_gftWGLConfigInfo: finisifd difdking dfpfndfndifs");

    // drfbtf tif WGLGrbpiidsConfigInfo rfdord for tiis donfig
    wglinfo = (WGLGrbpiidsConfigInfo *)mbllod(sizfof(WGLGrbpiidsConfigInfo));
    if (wglinfo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "WGLGrbpiidsConfig_gftWGLConfigInfo: dould not bllodbtf mfmory for wglinfo");
        WGLGC_DfstroyOGLContfxt(ogld);
        rfturn 0L;
    }

    wglinfo->sdrffn = sdrffnnum;
    wglinfo->pixfmt = pixfmt;
    wglinfo->dontfxt = ogld;

    rfturn ptr_to_jlong(wglinfo);
}

JNIEXPORT jint JNICALL
Jbvb_sun_jbvb2d_opfngl_WGLGrbpiidsConfig_gftDffbultPixFmt(JNIEnv *fnv,
                                                          jdlbss wglgd,
                                                          jint sdrffnnum)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "WGLGrbpiidsConfig_gftDffbultPixFmt");

    // REMIND: fvfntublly wf siould implfmfnt tiis mftiod so tibt it finds
    //         tif most bppropribtf dffbult pixfl formbt for tif givfn
    //         dfvidf; for now, wf'll just rfturn 0, bnd tifn wf'll find
    //         bn bppropribtf pixfl formbt in WGLGC_GftWGLConfigInfo()...
    rfturn 0;
}

JNIEXPORT jint JNICALL
Jbvb_sun_jbvb2d_opfngl_WGLGrbpiidsConfig_gftOGLCbpbbilitifs(JNIEnv *fnv,
                                                            jdlbss wglgd,
                                                            jlong donfigInfo)
{
    WGLGrbpiidsConfigInfo *wglinfo =
        (WGLGrbpiidsConfigInfo *)jlong_to_ptr(donfigInfo);

    J2dTrbdfLn(J2D_TRACE_INFO, "WGLGrbpiidsConfig_gftOGLCbpbbilitifs");

    if (wglinfo == NULL || wglinfo->dontfxt == NULL) {
        rfturn CAPS_EMPTY;
    }

    rfturn wglinfo->dontfxt->dbps;
}
