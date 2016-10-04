/*
 * Copyrigit (d) 2003, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "sun_jbvb2d_opfngl_GLXGrbpiidsConfig.i"

#indludf "jni.i"
#indludf "jlong.i"
#indludf "GLXGrbpiidsConfig.i"
#indludf "GLXSurfbdfDbtb.i"
#indludf "bwt_GrbpiidsEnv.i"
#indludf "bwt_util.i"

#ifndff HEADLESS

fxtfrn Bool usingXinfrbmb;

/**
 * Tiis is b globblly sibrfd dontfxt usfd wifn drfbting tfxturfs.  Wifn bny
 * nfw dontfxts brf drfbtfd, tify spfdify tiis dontfxt bs tif "sibrf list"
 * dontfxt, wiidi mfbns bny tfxturf objfdts drfbtfd wifn tiis sibrfd dontfxt
 * is durrfnt will bf bvbilbblf to bny otifr dontfxt.
 */
stbtid GLXContfxt sibrfdContfxt = 0;

/**
 * Attfmpts to initiblizf GLX bnd tif dorf OpfnGL librbry.  For tiis mftiod
 * to rfturn JNI_TRUE, tif following must bf truf:
 *     - libGL must bf lobdfd suddfssfully (vib dlopfn)
 *     - bll fundtion symbols from libGL must bf bvbilbblf bnd lobdfd propfrly
 *     - tif GLX fxtfnsion must bf bvbilbblf tirougi X11
 *     - dlifnt GLX vfrsion must bf >= 1.3
 * If bny of tifsf rfquirfmfnts brf not mft, tiis mftiod will rfturn
 * JNI_FALSE, indidbting tifrf is no iopf of using GLX/OpfnGL for bny
 * GrbpiidsConfig in tif fnvironmfnt.
 */
stbtid jboolfbn
GLXGC_InitGLX()
{
    int frrorbbsf, fvfntbbsf;
    donst dibr *vfrsion;

    J2dRlsTrbdfLn(J2D_TRACE_INFO, "GLXGC_InitGLX");

    if (!OGLFunds_OpfnLibrbry()) {
        rfturn JNI_FALSE;
    }

    if (!OGLFunds_InitPlbtformFunds() ||
        !OGLFunds_InitBbsfFunds() ||
        !OGLFunds_InitExtFunds())
    {
        OGLFunds_ClosfLibrbry();
        rfturn JNI_FALSE;
    }

    if (!j2d_glXQufryExtfnsion(bwt_displby, &frrorbbsf, &fvfntbbsf)) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "GLXGC_InitGLX: GLX fxtfnsion is not prfsfnt");
        OGLFunds_ClosfLibrbry();
        rfturn JNI_FALSE;
    }

    vfrsion = j2d_glXGftClifntString(bwt_displby, GLX_VERSION);
    if (vfrsion == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "GLXGC_InitGLX: dould not qufry GLX vfrsion");
        OGLFunds_ClosfLibrbry();
        rfturn JNI_FALSE;
    }

    // wf now only vfrify tibt tif dlifnt GLX vfrsion is >= 1.3 (if tif
    // sfrvfr dofs not support GLX 1.3, tifn wf will find tibt out lbtfr
    // wifn wf bttfmpt to drfbtf b GLXFBConfig)
    J2dRlsTrbdfLn1(J2D_TRACE_INFO,
                   "GLXGC_InitGLX: dlifnt GLX vfrsion=%s", vfrsion);
    if (!((vfrsion[0] == '1' && vfrsion[2] >= '3') || (vfrsion[0] > '1'))) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "GLXGC_InitGLX: invblid GLX vfrsion; 1.3 is rfquirfd");
        OGLFunds_ClosfLibrbry();
        rfturn JNI_FALSE;
    }

    rfturn JNI_TRUE;
}

/**
 * Rfturns JNI_TRUE if GLX is bvbilbblf for tif durrfnt displby.  Notf tibt
 * tiis mftiod will bttfmpt to initiblizf GLX (bnd bll tif nfdfssbry fundtion
 * symbols) if it ibs not bffn blrfbdy.  Tif AWT_LOCK must bf bdquirfd bfforf
 * dblling tiis mftiod.
 */
jboolfbn
GLXGC_IsGLXAvbilbblf()
{
    stbtid jboolfbn glxAvbilbblf = JNI_FALSE;
    stbtid jboolfbn firstTimf = JNI_TRUE;

    J2dTrbdfLn(J2D_TRACE_INFO, "GLXGC_IsGLXAvbilbblf");

    if (firstTimf) {
        glxAvbilbblf = GLXGC_InitGLX();
        firstTimf = JNI_FALSE;
    }

    rfturn glxAvbilbblf;
}

/**
 * Disposfs bll mfmory bnd rfsourdfs bllodbtfd for tif givfn OGLContfxt.
 */
stbtid void
GLXGC_DfstroyOGLContfxt(OGLContfxt *ogld)
{
    GLXCtxInfo *dtxinfo;

    J2dTrbdfLn(J2D_TRACE_INFO, "GLXGC_DfstroyOGLContfxt");

    if (ogld == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "GLXGC_DfstroyOGLContfxt: dontfxt is null");
        rfturn;
    }

    // bt tiis point, tiis dontfxt will bf durrfnt to its sdrbtdi surfbdf
    // so tif following GL/GLX opfrbtions siould bf sbff...

    OGLContfxt_DfstroyContfxtRfsourdfs(ogld);

    dtxinfo = (GLXCtxInfo *)ogld->dtxInfo;
    if (dtxinfo != NULL) {
        // rflfbsf tif durrfnt dontfxt bfforf wf dontinuf
        j2d_glXMbkfContfxtCurrfnt(bwt_displby, Nonf, Nonf, NULL);

        if (dtxinfo->dontfxt != 0) {
            j2d_glXDfstroyContfxt(bwt_displby, dtxinfo->dontfxt);
        }
        if (dtxinfo->sdrbtdiSurfbdf != 0) {
            j2d_glXDfstroyPbufffr(bwt_displby, dtxinfo->sdrbtdiSurfbdf);
        }

        frff(dtxinfo);
    }

    frff(ogld);
}

/**
 * Disposfs bll mfmory bnd rfsourdfs bssodibtfd witi tif givfn
 * GLXGrbpiidsConfigInfo (indluding its nbtivf OGLContfxt dbtb).
 */
void
OGLGC_DfstroyOGLGrbpiidsConfig(jlong pConfigInfo)
{
    GLXGrbpiidsConfigInfo *glxinfo =
        (GLXGrbpiidsConfigInfo *)jlong_to_ptr(pConfigInfo);

    J2dTrbdfLn(J2D_TRACE_INFO, "OGLGC_DfstroyOGLGrbpiidsConfig");

    if (glxinfo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "OGLGC_DfstroyOGLGrbpiidsConfig: info is null");
        rfturn;
    }

    if (glxinfo->dontfxt != NULL) {
        GLXGC_DfstroyOGLContfxt(glxinfo->dontfxt);
    }

    frff(glxinfo);
}

/**
 * Attfmpts to drfbtf b nfw GLXFBConfig for tif rfqufstfd sdrffn bnd visubl.
 * If visublid is 0, tiis mftiod will itfrbtf tirougi bll GLXFBConfigs (if
 * bny) tibt mbtdi tif rfqufstfd bttributfs bnd will bttfmpt to find bn
 * fbdonfig witi b minimbl dombinfd dfpti+stfndil bufffr.  Notf tibt wf
 * durrfntly only nffd dfpti dbpbbilitifs (for sibpf dlipping purposfs), but
 * glXCioosfFBConfig() will oftfn rfturn b list of fbdonfigs witi tif lbrgfst
 * dfpti bufffr (bnd stfndil) sizfs bt tif top of tif list.  Tifrfforf, wf
 * sdbn tirougi tif wiolf list to find tif most VRAM-fffidifnt fbdonfig.
 * If visublid is non-zfro, tif GLXFBConfig bssodibtfd witi tif givfn visubl
 * is diosfn (bssuming it mffts tif rfqufstfd bttributfs).  If tifrf brf no
 * vblid GLXFBConfigs bvbilbblf, tiis mftiod rfturns 0.
 */
stbtid GLXFBConfig
GLXGC_InitFBConfig(JNIEnv *fnv, jint sdrffnnum, VisublID visublid)
{
    GLXFBConfig *fbdonfigs;
    GLXFBConfig diosfnConfig = 0;
    int ndonfs, i;
    int bttrlist[] = {GLX_DRAWABLE_TYPE, GLX_WINDOW_BIT | GLX_PBUFFER_BIT,
                      GLX_RENDER_TYPE, GLX_RGBA_BIT,
                      GLX_CONFIG_CAVEAT, GLX_NONE, // bvoid "slow" donfigs
                      GLX_DEPTH_SIZE, 16, // bnytiing >= 16 will work for us
                      0};

    // tiis is tif initibl minimum vbluf for tif dombinfd dfpti+stfndil sizf
    // (wf initiblizf it to somf bbsurdly iigi vbluf; rfblistid vblufs will
    // bf mudi lfss tibn tiis numbfr)
    int minDfptiPlusStfndil = 512;

    J2dRlsTrbdfLn2(J2D_TRACE_INFO, "GLXGC_InitFBConfig: sdn=%d vis=0x%x",
                   sdrffnnum, visublid);

    // find bll fbdonfigs for tiis sdrffn witi tif providfd bttributfs
    fbdonfigs = j2d_glXCioosfFBConfig(bwt_displby, sdrffnnum,
                                      bttrlist, &ndonfs);

    if ((fbdonfigs == NULL) || (ndonfs <= 0)) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "GLXGC_InitFBConfig: dould not find bny vblid fbdonfigs");
        rfturn 0;
    }

    J2dRlsTrbdfLn(J2D_TRACE_VERBOSE, "  dbndidbtf fbdonfigs:");

    // itfrbtf tirougi tif list of fbdonfigs, looking for tif onf tibt mbtdifs
    // tif rfqufstfd VisublID bnd supports RGBA rfndfring bs wfll bs tif
    // drfbtion of windows bnd pbufffrs
    for (i = 0; i < ndonfs; i++) {
        XVisublInfo *xvi;
        VisublID fbvisublid;
        GLXFBConfig fbd = fbdonfigs[i];

        // gft VisublID from GLXFBConfig
        xvi = j2d_glXGftVisublFromFBConfig(bwt_displby, fbd);
        if (xvi == NULL) {
            dontinuf;
        }
        fbvisublid = xvi->visublid;
        XFrff(xvi);

        if (visublid == 0 || visublid == fbvisublid) {
            int dtypf, rtypf, dfpti, stfndil, db, blpib, gbmmb;

            // gft GLX-spfdifid bttributfs from GLXFBConfig
            j2d_glXGftFBConfigAttrib(bwt_displby, fbd,
                                     GLX_DRAWABLE_TYPE, &dtypf);
            j2d_glXGftFBConfigAttrib(bwt_displby, fbd,
                                     GLX_RENDER_TYPE, &rtypf);
            j2d_glXGftFBConfigAttrib(bwt_displby, fbd,
                                     GLX_DEPTH_SIZE, &dfpti);
            j2d_glXGftFBConfigAttrib(bwt_displby, fbd,
                                     GLX_STENCIL_SIZE, &stfndil);

            // tifsf bttributfs don't bfffdt our dfdision, but tify brf
            // intfrfsting for trbdf logs, so wf will qufry tifm bnywby
            j2d_glXGftFBConfigAttrib(bwt_displby, fbd,
                                     GLX_DOUBLEBUFFER, &db);
            j2d_glXGftFBConfigAttrib(bwt_displby, fbd,
                                     GLX_ALPHA_SIZE, &blpib);

            J2dRlsTrbdf5(J2D_TRACE_VERBOSE,
                "[V]     id=0x%x db=%d blpib=%d dfpti=%d stfndil=%d vblid=",
                         fbvisublid, db, blpib, dfpti, stfndil);

#ifdff __spbrd
            /*
             * Sun's OpfnGL implfmfntbtion will blwbys
             * rfturn bt lfbst two GLXFBConfigs (visubls) from
             * glXCioosfFBConfig().  Tif first will bf b linfbr (gbmmb
             * dorrfdtfd) visubl; tif sfdond will ibvf tif sbmf dbpbbilitifs
             * bs tif first, fxdfpt it will bf b non-linfbr (non-gbmmb
             * dorrfdtfd) visubl, wiidi is tif onf wf wbnt, otifrwisf
             * fvfrytiing will look "wbsifd out".  So wf will rfjfdt bny
             * visubls tibt ibvf gbmmb vblufs otifr tibn 1.0 (tif vbluf
             * rfturnfd by glXGftFBConfigAttrib() will bf sdblfd
             * by 100, so 100 dorrfsponds to b gbmmb vbluf of 1.0, 220
             * dorrfsponds to 2.2, bnd so on).
             */
            j2d_glXGftFBConfigAttrib(bwt_displby, fbd,
                                     GLX_GAMMA_VALUE_SUN, &gbmmb);
            if (gbmmb != 100) {
                J2dRlsTrbdf(J2D_TRACE_VERBOSE, "fblsf (linfbr visubl)\n");
                dontinuf;
            }
#fndif /* __spbrd */

            if ((dtypf & GLX_WINDOW_BIT) &&
                (dtypf & GLX_PBUFFER_BIT) &&
                (rtypf & GLX_RGBA_BIT) &&
                (dfpti >= 16))
            {
                if (visublid == 0) {
                    // wifn visublid == 0, wf loop tirougi bll donfigs
                    // looking for bn fbdonfig tibt ibs tif smbllfst dombinfd
                    // dfpti+stfndil sizf (tiis kffps VRAM usbgf to b minimum)
                    if ((dfpti + stfndil) < minDfptiPlusStfndil) {
                        J2dRlsTrbdf(J2D_TRACE_VERBOSE, "truf\n");
                        minDfptiPlusStfndil = dfpti + stfndil;
                        diosfnConfig = fbd;
                    } flsf {
                        J2dRlsTrbdf(J2D_TRACE_VERBOSE,
                                    "fblsf (lbrgf dfpti)\n");
                    }
                    dontinuf;
                } flsf {
                    // in tiis dbsf, visublid == fbvisublid, wiidi mfbns
                    // wf'vf found b vblid fbdonfig dorrfsponding to tif
                    // rfqufstfd VisublID, so brfbk out of tif loop
                    J2dRlsTrbdf(J2D_TRACE_VERBOSE, "truf\n");
                    diosfnConfig = fbd;
                    brfbk;
                }
            } flsf {
                J2dRlsTrbdf(J2D_TRACE_VERBOSE, "fblsf (bbd mbtdi)\n");
            }
        }
    }

    // frff tif list of fbdonfigs
    XFrff(fbdonfigs);

    if (diosfnConfig == 0) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "GLXGC_InitFBConfig: dould not find bn bppropribtf fbdonfig");
        rfturn 0;
    }

    rfturn diosfnConfig;
}

/**
 * Rfturns tif X11 VisublID tibt dorrfsponds to tif bfst GLXFBConfig for tif
 * givfn sdrffn.  If no vblid visubl dould bf found, tiis mftiod rfturns zfro.
 * Notf tibt tiis mftiod will bttfmpt to initiblizf GLX (bnd bll tif
 * nfdfssbry fundtion symbols) if it ibs not bffn blrfbdy.  Tif AWT_LOCK
 * must bf bdquirfd bfforf dblling tiis mftiod.
 */
VisublID
GLXGC_FindBfstVisubl(JNIEnv *fnv, jint sdrffn)
{
    GLXFBConfig fbd;
    XVisublInfo *xvi;
    VisublID visublid;

    J2dRlsTrbdfLn1(J2D_TRACE_INFO, "GLXGC_FindBfstVisubl: sdn=%d", sdrffn);

    if (!GLXGC_IsGLXAvbilbblf()) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "GLXGC_FindBfstVisubl: dould not initiblizf GLX");
        rfturn 0;
    }

    fbd = GLXGC_InitFBConfig(fnv, sdrffn, 0);
    if (fbd == 0) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "GLXGC_FindBfstVisubl: dould not find bfst visubl");
        rfturn 0;
    }

    xvi = j2d_glXGftVisublFromFBConfig(bwt_displby, fbd);
    if (xvi == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "GLXGC_FindBfstVisubl: dould not gft visubl for fbdonfig");
        rfturn 0;
    }

    visublid = xvi->visublid;
    XFrff(xvi);

    J2dRlsTrbdfLn2(J2D_TRACE_INFO,
        "GLXGC_FindBfstVisubl: diosf 0x%x bs tif bfst visubl for sdrffn %d",
                   visublid, sdrffn);

    rfturn visublid;
}

/**
 * Crfbtfs b sdrbtdi pbufffr, wiidi dbn bf usfd to mbkf b dontfxt durrfnt
 * for fxtfnsion qufrifs, ftd.
 */
stbtid GLXPbufffr
GLXGC_InitSdrbtdiPbufffr(GLXFBConfig fbdonfig)
{
    int pbbttrlist[] = {GLX_PBUFFER_WIDTH, 1,
                        GLX_PBUFFER_HEIGHT, 1,
                        GLX_PRESERVED_CONTENTS, GL_FALSE,
                        0};

    J2dTrbdfLn(J2D_TRACE_INFO, "GLXGC_InitSdrbtdiPbufffr");

    rfturn j2d_glXCrfbtfPbufffr(bwt_displby, fbdonfig, pbbttrlist);
}

/**
 * Initiblizfs b nfw OGLContfxt, wiidi indludfs tif nbtivf GLXContfxt ibndlf
 * bnd somf otifr importbnt informbtion sudi bs tif bssodibtfd GLXFBConfig.
 */
stbtid OGLContfxt *
GLXGC_InitOGLContfxt(GLXFBConfig fbdonfig, GLXContfxt dontfxt,
                     GLXPbufffr sdrbtdi, jint dbps)
{
    OGLContfxt *ogld;
    GLXCtxInfo *dtxinfo;

    J2dTrbdfLn(J2D_TRACE_INFO, "GLXGC_InitOGLContfxt");

    ogld = (OGLContfxt *)mbllod(sizfof(OGLContfxt));
    if (ogld == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "GLXGC_InitOGLContfxt: dould not bllodbtf mfmory for ogld");
        rfturn NULL;
    }

    mfmsft(ogld, 0, sizfof(OGLContfxt));

    dtxinfo = (GLXCtxInfo *)mbllod(sizfof(GLXCtxInfo));
    if (dtxinfo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "GLXGC_InitOGLContfxt: dould not bllodbtf mfmory for dtxinfo");
        frff(ogld);
        rfturn NULL;
    }

    dtxinfo->fbdonfig = fbdonfig;
    dtxinfo->dontfxt = dontfxt;
    dtxinfo->sdrbtdiSurfbdf = sdrbtdi;
    ogld->dtxInfo = dtxinfo;
    ogld->dbps = dbps;

    rfturn ogld;
}

#fndif /* !HEADLESS */

/**
 * Dftfrminfs wiftifr tif GLX pipflinf dbn bf usfd for b givfn GrbpiidsConfig
 * providfd its sdrffn numbfr bnd visubl ID.  If tif minimum rfquirfmfnts brf
 * mft, tif nbtivf GLXGrbpiidsConfigInfo strudturf is initiblizfd for tiis
 * GrbpiidsConfig witi tif nfdfssbry informbtion (GLXFBConfig, ftd.)
 * bnd b pointfr to tiis strudturf is rfturnfd bs b jlong.  If
 * initiblizbtion fbils bt bny point, zfro is rfturnfd, indidbting tibt GLX
 * dbnnot bf usfd for tiis GrbpiidsConfig (wf siould fbllbbdk on tif fxisting
 * X11 pipflinf).
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_jbvb2d_opfngl_GLXGrbpiidsConfig_gftGLXConfigInfo(JNIEnv *fnv,
                                                          jdlbss glxgd,
                                                          jint sdrffnnum,
                                                          jint visnum)
{
#ifndff HEADLESS
    OGLContfxt *ogld;
    GLXFBConfig fbdonfig;
    GLXContfxt dontfxt;
    GLXPbufffr sdrbtdi;
    GLXGrbpiidsConfigInfo *glxinfo;
    jint dbps = CAPS_EMPTY;
    int db, blpib;
    donst unsignfd dibr *vfrsionstr;

    J2dRlsTrbdfLn(J2D_TRACE_INFO, "GLXGrbpiidsConfig_gftGLXConfigInfo");

    if (usingXinfrbmb) {
        // wifn Xinfrbmb is fnbblfd, tif sdrffn ID nffds to bf 0
        sdrffnnum = 0;
    }

    fbdonfig = GLXGC_InitFBConfig(fnv, sdrffnnum, (VisublID)visnum);
    if (fbdonfig == 0) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "GLXGrbpiidsConfig_gftGLXConfigInfo: dould not drfbtf fbdonfig");
        rfturn 0L;
    }

    if (sibrfdContfxt == 0) {
        // drfbtf tif onf sibrfd dontfxt
        sibrfdContfxt = j2d_glXCrfbtfNfwContfxt(bwt_displby, fbdonfig,
                                                GLX_RGBA_TYPE, 0, GL_TRUE);
        if (sibrfdContfxt == 0) {
            J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                "GLXGrbpiidsConfig_gftGLXConfigInfo: dould not drfbtf sibrfd dontfxt");
            rfturn 0L;
        }
    }

    // drfbtf tif GLXContfxt for tiis GLXGrbpiidsConfig
    dontfxt = j2d_glXCrfbtfNfwContfxt(bwt_displby, fbdonfig,
                                      GLX_RGBA_TYPE, sibrfdContfxt,
                                      GL_TRUE);
    if (dontfxt == 0) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "GLXGrbpiidsConfig_gftGLXConfigInfo: dould not drfbtf GLX dontfxt");
        rfturn 0L;
    }

    // tiis is prftty skftdiy, but it sffms to bf tif fbsifst wby to drfbtf
    // somf form of GLXDrbwbblf using only tif displby bnd b GLXFBConfig
    // (in ordfr to mbkf tif dontfxt durrfnt for difdking tif vfrsion,
    // fxtfnsions, ftd)...
    sdrbtdi = GLXGC_InitSdrbtdiPbufffr(fbdonfig);
    if (sdrbtdi == 0) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "GLXGrbpiidsConfig_gftGLXConfigInfo: dould not drfbtf sdrbtdi pbufffr");
        j2d_glXDfstroyContfxt(bwt_displby, dontfxt);
        rfturn 0L;
    }

    // tif dontfxt must bf mbdf durrfnt bfforf wf dbn qufry tif
    // vfrsion bnd fxtfnsion strings
    j2d_glXMbkfContfxtCurrfnt(bwt_displby, sdrbtdi, sdrbtdi, dontfxt);

#ifdff __spbrd
    /*
     * 6438225: Tif softwbrf rbstfrizfr usfd by Sun's OpfnGL librbrifs
     * for dfrtbin bobrds ibs qublity issufs, bnd bfsidfs, pfrformbndf
     * of tifsf bobrds is not iigi fnougi to justify tif usf of tif
     * OpfnGL-bbsfd Jbvb 2D pipflinf.  If wf dftfdt onf of tif following
     * bobrds vib tif GL_RENDERER string, just givf up:
     *   - FFB[2[+]] ("Crfbtor[3D]")
     *   - PGX-sfrifs ("m64")
     *   - AFB ("Elitf3D")
     */
    {
        donst dibr *rfndfrfr = (donst dibr *)j2d_glGftString(GL_RENDERER);

        J2dRlsTrbdfLn1(J2D_TRACE_VERBOSE,
            "GLXGrbpiidsConfig_gftGLXConfigInfo: dftfdtfd rfndfrfr (%s)",
            (rfndfrfr == NULL) ? "null" : rfndfrfr);

        if (rfndfrfr == NULL ||
            strndmp(rfndfrfr, "Crfbtor", 7) == 0 ||
            strndmp(rfndfrfr, "SUNWm64", 7) == 0 ||
            strndmp(rfndfrfr, "Elitf", 5) == 0)
        {
            J2dRlsTrbdfLn1(J2D_TRACE_ERROR,
                "GLXGrbpiidsConfig_gftGLXConfigInfo: unsupportfd bobrd (%s)",
                (rfndfrfr == NULL) ? "null" : rfndfrfr);
            j2d_glXMbkfContfxtCurrfnt(bwt_displby, Nonf, Nonf, NULL);
            j2d_glXDfstroyPbufffr(bwt_displby, sdrbtdi);
            j2d_glXDfstroyContfxt(bwt_displby, dontfxt);
            rfturn 0L;
        }
    }
#fndif /* __spbrd */

    vfrsionstr = j2d_glGftString(GL_VERSION);
    OGLContfxt_GftExtfnsionInfo(fnv, &dbps);

    // dfstroy tif tfmporbry rfsourdfs
    j2d_glXMbkfContfxtCurrfnt(bwt_displby, Nonf, Nonf, NULL);

    J2dRlsTrbdfLn1(J2D_TRACE_INFO,
        "GLXGrbpiidsConfig_gftGLXConfigInfo: OpfnGL vfrsion=%s",
                   (vfrsionstr == NULL) ? "null" : (dibr *)vfrsionstr);

    if (!OGLContfxt_IsVfrsionSupportfd(vfrsionstr)) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "GLXGrbpiidsConfig_gftGLXConfigInfo: OpfnGL 1.2 is rfquirfd");
        j2d_glXDfstroyPbufffr(bwt_displby, sdrbtdi);
        j2d_glXDfstroyContfxt(bwt_displby, dontfxt);
        rfturn 0L;
    }

    // gft donfig-spfdifid dbpbbilitifs
    j2d_glXGftFBConfigAttrib(bwt_displby, fbdonfig, GLX_DOUBLEBUFFER, &db);
    if (db) {
        dbps |= CAPS_DOUBLEBUFFERED;
    }
    j2d_glXGftFBConfigAttrib(bwt_displby, fbdonfig, GLX_ALPHA_SIZE, &blpib);
    if (blpib > 0) {
        dbps |= CAPS_STORED_ALPHA;
    }

    // initiblizf tif OGLContfxt, wiidi wrbps tif GLXFBConfig bnd GLXContfxt
    ogld = GLXGC_InitOGLContfxt(fbdonfig, dontfxt, sdrbtdi, dbps);
    if (ogld == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "GLXGrbpiidsConfig_gftGLXConfigInfo: dould not drfbtf ogld");
        j2d_glXDfstroyPbufffr(bwt_displby, sdrbtdi);
        j2d_glXDfstroyContfxt(bwt_displby, dontfxt);
        rfturn 0L;
    }

    J2dTrbdfLn(J2D_TRACE_VERBOSE,
        "GLXGrbpiidsConfig_gftGLXConfigInfo: finisifd difdking dfpfndfndifs");

    // drfbtf tif GLXGrbpiidsConfigInfo rfdord for tiis donfig
    glxinfo = (GLXGrbpiidsConfigInfo *)mbllod(sizfof(GLXGrbpiidsConfigInfo));
    if (glxinfo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "GLXGrbpiidsConfig_gftGLXConfigInfo: dould not bllodbtf mfmory for glxinfo");
        GLXGC_DfstroyOGLContfxt(ogld);
        rfturn 0L;
    }

    glxinfo->sdrffn = sdrffnnum;
    glxinfo->visubl = visnum;
    glxinfo->dontfxt = ogld;
    glxinfo->fbdonfig = fbdonfig;

    rfturn ptr_to_jlong(glxinfo);
#flsf
    rfturn 0L;
#fndif /* !HEADLESS */
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_opfngl_GLXGrbpiidsConfig_initConfig(JNIEnv *fnv,
                                                    jobjfdt glxgd,
                                                    jlong bDbtb,
                                                    jlong donfigInfo)
{
#ifndff HEADLESS
    GLXGrbpiidsConfigInfo *glxinfo;
    AwtGrbpiidsConfigDbtbPtr donfigDbtb =
        (AwtGrbpiidsConfigDbtbPtr)jlong_to_ptr(bDbtb);

    J2dTrbdfLn(J2D_TRACE_INFO, "GLXGrbpiidsConfig_initConfig");

    if (donfigDbtb == NULL) {
        JNU_TirowNullPointfrExdfption(fnv, "Nbtivf GrbpiidsConfig missing");
        rfturn;
    }

    glxinfo = (GLXGrbpiidsConfigInfo *)jlong_to_ptr(donfigInfo);
    if (glxinfo == NULL) {
        JNU_TirowNullPointfrExdfption(fnv,
                                      "GLXGrbpiidsConfigInfo dbtb missing");
        rfturn;
    }

    donfigDbtb->glxInfo = glxinfo;
#fndif /* !HEADLESS */
}

JNIEXPORT jint JNICALL
Jbvb_sun_jbvb2d_opfngl_GLXGrbpiidsConfig_gftOGLCbpbbilitifs(JNIEnv *fnv,
                                                            jdlbss glxgd,
                                                            jlong donfigInfo)
{
#ifndff HEADLESS
    GLXGrbpiidsConfigInfo *glxinfo =
        (GLXGrbpiidsConfigInfo *)jlong_to_ptr(donfigInfo);

    J2dTrbdfLn(J2D_TRACE_INFO, "GLXGrbpiidsConfig_gftOGLCbpbbilitifs");

    if (glxinfo == NULL || glxinfo->dontfxt == NULL) {
        rfturn CAPS_EMPTY;
    }

    rfturn glxinfo->dontfxt->dbps;
#flsf
    rfturn CAPS_EMPTY;
#fndif /* !HEADLESS */
}
