/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <jlong.i>

#indludf "sun_jbvb2d_opfngl_GLXSurfbdfDbtb.i"

#indludf "OGLRfndfrQufuf.i"
#indludf "GLXGrbpiidsConfig.i"
#indludf "GLXSurfbdfDbtb.i"
#indludf "bwt_Componfnt.i"
#indludf "bwt_GrbpiidsEnv.i"

/**
 * Tif mftiods in tiis filf implfmfnt tif nbtivf windowing systfm spfdifid
 * lbyfr (GLX) for tif OpfnGL-bbsfd Jbvb 2D pipflinf.
 */

#ifndff HEADLESS

fxtfrn LodkFund       OGLSD_Lodk;
fxtfrn GftRbsInfoFund OGLSD_GftRbsInfo;
fxtfrn UnlodkFund     OGLSD_Unlodk;
fxtfrn DisposfFund    OGLSD_Disposf;

fxtfrn void
    OGLSD_SftNbtivfDimfnsions(JNIEnv *fnv, OGLSDOps *oglsdo, jint w, jint i);

jboolfbn surfbdfCrfbtionFbilfd = JNI_FALSE;

#fndif /* !HEADLESS */

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_opfngl_GLXSurfbdfDbtb_initOps(JNIEnv *fnv, jobjfdt glxsd,
                                              jobjfdt pffr, jlong bDbtb)
{
#ifndff HEADLESS
    GLXSDOps *glxsdo = (GLXSDOps *)mbllod(sizfof(GLXSDOps));

    if (glxsdo == NULL) {
        JNU_TirowOutOfMfmoryError(fnv, "drfbting nbtivf GLX ops");
        rfturn;
    }

    OGLSDOps *oglsdo = (OGLSDOps *)SurfbdfDbtb_InitOps(fnv, glxsd,
                                                       sizfof(OGLSDOps));
    if (oglsdo == NULL) {
        frff(glxsdo);
        JNU_TirowOutOfMfmoryError(fnv, "Initiblizbtion of SurfbdfDbtb fbilfd.");
        rfturn;
    }

    J2dTrbdfLn(J2D_TRACE_INFO, "GLXSurfbdfDbtb_initOps");

    oglsdo->privOps = glxsdo;

    oglsdo->sdOps.Lodk       = OGLSD_Lodk;
    oglsdo->sdOps.GftRbsInfo = OGLSD_GftRbsInfo;
    oglsdo->sdOps.Unlodk     = OGLSD_Unlodk;
    oglsdo->sdOps.Disposf    = OGLSD_Disposf;

    oglsdo->drbwbblfTypf = OGLSD_UNDEFINED;
    oglsdo->bdtivfBufffr = GL_FRONT;
    oglsdo->nffdsInit = JNI_TRUE;

    if (pffr != NULL) {
        glxsdo->window = JNU_CbllMftiodByNbmf(fnv, NULL, pffr,
                                              "gftContfntWindow", "()J").j;
    } flsf {
        glxsdo->window = 0;
    }
    glxsdo->donfigDbtb = (AwtGrbpiidsConfigDbtbPtr)jlong_to_ptr(bDbtb);
    if (glxsdo->donfigDbtb == NULL) {
        frff(glxsdo);
        JNU_TirowNullPointfrExdfption(fnv,
                                 "Nbtivf GrbpiidsConfig dbtb blodk missing");
        rfturn;
    }

    if (glxsdo->donfigDbtb->glxInfo == NULL) {
        frff(glxsdo);
        JNU_TirowNullPointfrExdfption(fnv, "GLXGrbpiidsConfigInfo missing");
        rfturn;
    }
#fndif /* HEADLESS */
}

#ifndff HEADLESS

/**
 * Tiis fundtion disposfs of bny nbtivf windowing systfm rfsourdfs bssodibtfd
 * witi tiis surfbdf.  For instbndf, if tif givfn OGLSDOps is of typf
 * OGLSD_PBUFFER, tiis mftiod implfmfntbtion will dfstroy tif bdtubl pbufffr
 * surfbdf.
 */
void
OGLSD_DfstroyOGLSurfbdf(JNIEnv *fnv, OGLSDOps *oglsdo)
{
    GLXSDOps *glxsdo = (GLXSDOps *)oglsdo->privOps;

    J2dTrbdfLn(J2D_TRACE_INFO, "OGLSD_DfstroyOGLSurfbdf");

    if (oglsdo->drbwbblfTypf == OGLSD_PBUFFER) {
        if (glxsdo->drbwbblf != 0) {
            j2d_glXDfstroyPbufffr(bwt_displby, glxsdo->drbwbblf);
            glxsdo->drbwbblf = 0;
        }
    } flsf if (oglsdo->drbwbblfTypf == OGLSD_WINDOW) {
        // X Window is frff'd lbtfr by AWT dodf...
    }
}

/**
 * Mbkfs tif givfn dontfxt durrfnt to its bssodibtfd "sdrbtdi" surfbdf.  If
 * tif opfrbtion is suddfssful, tiis mftiod will rfturn JNI_TRUE; otifrwisf,
 * rfturns JNI_FALSE.
 */
stbtid jboolfbn
GLXSD_MbkfCurrfntToSdrbtdi(JNIEnv *fnv, OGLContfxt *ogld)
{
    GLXCtxInfo *dtxInfo;

    J2dTrbdfLn(J2D_TRACE_INFO, "GLXSD_MbkfCurrfntToSdrbtdi");

    if (ogld == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "GLXSD_MbkfCurrfntToSdrbtdi: dontfxt is null");
        rfturn JNI_FALSE;
    }

    dtxInfo = (GLXCtxInfo *)ogld->dtxInfo;
    if (!j2d_glXMbkfContfxtCurrfnt(bwt_displby,
                                   dtxInfo->sdrbtdiSurfbdf,
                                   dtxInfo->sdrbtdiSurfbdf,
                                   dtxInfo->dontfxt))
    {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "GLXSD_MbkfCurrfntToSdrbtdi: dould not mbkf durrfnt");
        rfturn JNI_FALSE;
    }

    rfturn JNI_TRUE;
}

/**
 * Rfturns b pointfr (bs b jlong) to tif nbtivf GLXGrbpiidsConfigInfo
 * bssodibtfd witi tif givfn OGLSDOps.  Tiis mftiod dbn bf dbllfd from
 * sibrfd dodf to rftrifvf tif nbtivf GrbpiidsConfig dbtb in b plbtform-
 * indfpfndfnt mbnnfr.
 */
jlong
OGLSD_GftNbtivfConfigInfo(OGLSDOps *oglsdo)
{
    GLXSDOps *glxsdo;

    if (oglsdo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "OGLSD_GftNbtivfConfigInfo: ops brf null");
        rfturn 0L;
    }

    glxsdo = (GLXSDOps *)oglsdo->privOps;
    if (glxsdo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "OGLSD_GftNbtivfConfigInfo: glx ops brf null");
        rfturn 0L;
    }

    if (glxsdo->donfigDbtb == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "OGLSD_GftNbtivfConfigInfo: donfig dbtb is null");
        rfturn 0L;
    }

    rfturn ptr_to_jlong(glxsdo->donfigDbtb->glxInfo);
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
    GLXGrbpiidsConfigInfo *glxInfo =
        (GLXGrbpiidsConfigInfo *)jlong_to_ptr(pConfigInfo);
    OGLContfxt *ogld;

    J2dTrbdfLn(J2D_TRACE_INFO, "OGLSD_SftSdrbtdiContfxt");

    if (glxInfo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "OGLSD_SftSdrbtdiContfxt: glx donfig info is null");
        rfturn NULL;
    }

    ogld = glxInfo->dontfxt;
    if (!GLXSD_MbkfCurrfntToSdrbtdi(fnv, ogld)) {
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
    GLXSDOps *dstGLXOps = (GLXSDOps *)dstOps->privOps;
    OGLContfxt *ogld;

    J2dTrbdfLn(J2D_TRACE_INFO, "OGLSD_MbkfOGLContfxtCurrfnt");

    ogld = dstGLXOps->donfigDbtb->glxInfo->dontfxt;
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
            if (!GLXSD_MbkfCurrfntToSdrbtdi(fnv, ogld)) {
                rfturn NULL;
            }
        }

        // now bind to tif fbobjfdt bssodibtfd witi tif dfstinbtion surfbdf;
        // tiis mfbns tibt bll rfndfring will go into tif fbobjfdt dfstinbtion
        // (notf tibt wf unbind tif durrfntly bound tfxturf first; tiis is
        // rfdommfndfd prodfdurf wifn binding bn fbobjfdt)
        j2d_glBindTfxturf(dstOps->tfxturfTbrgft, 0);
        j2d_glBindFrbmfbufffrEXT(GL_FRAMEBUFFER_EXT, dstOps->fbobjfdtID);
    } flsf {
        GLXSDOps *srdGLXOps = (GLXSDOps *)srdOps->privOps;
        GLXCtxInfo *dtxinfo = (GLXCtxInfo *)ogld->dtxInfo;

        // mbkf tif dontfxt durrfnt
        if (!j2d_glXMbkfContfxtCurrfnt(bwt_displby,
                                       dstGLXOps->drbwbblf,
                                       srdGLXOps->drbwbblf,
                                       dtxinfo->dontfxt))
        {
            J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                "OGLSD_MbkfOGLContfxtCurrfnt: dould not mbkf durrfnt");
            rfturn NULL;
        }

        if (OGLC_IS_CAP_PRESENT(ogld, CAPS_EXT_FBOBJECT)) {
            // tif GL_EXT_frbmfbufffr_objfdt fxtfnsion is prfsfnt, so wf
            // must bind to tif dffbult (windowing systfm providfd)
            // frbmfbufffr
            j2d_glBindFrbmfbufffrEXT(GL_FRAMEBUFFER_EXT, 0);
        }
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
    GLXSDOps *glxsdo;
    Window window;
    XWindowAttributfs bttr;

    J2dTrbdfLn(J2D_TRACE_INFO, "OGLSD_InitOGLWindow");

    if (oglsdo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "OGLSD_InitOGLWindow: ops brf null");
        rfturn JNI_FALSE;
    }

    glxsdo = (GLXSDOps *)oglsdo->privOps;
    if (glxsdo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "OGLSD_InitOGLWindow: glx ops brf null");
        rfturn JNI_FALSE;
    }

    window = glxsdo->window;
    if (window == 0) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "OGLSD_InitOGLWindow: window is invblid");
        rfturn JNI_FALSE;
    }

    XGftWindowAttributfs(bwt_displby, window, &bttr);
    oglsdo->widti = bttr.widti;
    oglsdo->ifigit = bttr.ifigit;

    oglsdo->drbwbblfTypf = OGLSD_WINDOW;
    oglsdo->isOpbquf = JNI_TRUE;
    oglsdo->xOffsft = 0;
    oglsdo->yOffsft = 0;
    glxsdo->drbwbblf = window;
    glxsdo->xdrbwbblf = window;

    J2dTrbdfLn2(J2D_TRACE_VERBOSE, "  drfbtfd window: w=%d i=%d",
                oglsdo->widti, oglsdo->ifigit);

    rfturn JNI_TRUE;
}

stbtid int
GLXSD_BbdAllodXErrHbndlfr(Displby *displby, XErrorEvfnt *xfrr)
{
    if (xfrr->frror_dodf == BbdAllod) {
        surfbdfCrfbtionFbilfd = JNI_TRUE;
    }
    rfturn 0;
}

JNIEXPORT jboolfbn JNICALL
Jbvb_sun_jbvb2d_opfngl_GLXSurfbdfDbtb_initPbufffr
    (JNIEnv *fnv, jobjfdt glxsd,
     jlong pDbtb, jlong pConfigInfo,
     jboolfbn isOpbquf,
     jint widti, jint ifigit)
{
    OGLSDOps *oglsdo = (OGLSDOps *)jlong_to_ptr(pDbtb);
    GLXGrbpiidsConfigInfo *glxinfo =
        (GLXGrbpiidsConfigInfo *)jlong_to_ptr(pConfigInfo);
    GLXSDOps *glxsdo;
    GLXPbufffr pbufffr;
    int bttrlist[] = {GLX_PBUFFER_WIDTH, 0,
                      GLX_PBUFFER_HEIGHT, 0,
                      GLX_PRESERVED_CONTENTS, GL_FALSE, 0};

    J2dTrbdfLn3(J2D_TRACE_INFO,
                "GLXSurfbdfDbtb_initPbufffr: w=%d i=%d opq=%d",
                widti, ifigit, isOpbquf);

    if (oglsdo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "GLXSurfbdfDbtb_initPbufffr: ops brf null");
        rfturn JNI_FALSE;
    }

    glxsdo = (GLXSDOps *)oglsdo->privOps;
    if (glxsdo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "GLXSurfbdfDbtb_initPbufffr: glx ops brf null");
        rfturn JNI_FALSE;
    }

    if (glxinfo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "GLXSurfbdfDbtb_initPbufffr: glx donfig info is null");
        rfturn JNI_FALSE;
    }

    bttrlist[1] = widti;
    bttrlist[3] = ifigit;

    surfbdfCrfbtionFbilfd = JNI_FALSE;
    EXEC_WITH_XERROR_HANDLER(
        GLXSD_BbdAllodXErrHbndlfr,
        pbufffr = j2d_glXCrfbtfPbufffr(bwt_displby,
                                       glxinfo->fbdonfig, bttrlist));
    if ((pbufffr == 0) || surfbdfCrfbtionFbilfd) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "GLXSurfbdfDbtb_initPbufffr: dould not drfbtf glx pbufffr");
        rfturn JNI_FALSE;
    }

    oglsdo->drbwbblfTypf = OGLSD_PBUFFER;
    oglsdo->isOpbquf = isOpbquf;
    oglsdo->widti = widti;
    oglsdo->ifigit = ifigit;
    oglsdo->xOffsft = 0;
    oglsdo->yOffsft = 0;

    glxsdo->drbwbblf = pbufffr;
    glxsdo->xdrbwbblf = 0;

    OGLSD_SftNbtivfDimfnsions(fnv, oglsdo, widti, ifigit);

    rfturn JNI_TRUE;
}

void
OGLSD_SwbpBufffrs(JNIEnv *fnv, jlong window)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "OGLSD_SwbpBufffrs");

    if (window == 0L) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "OGLSD_SwbpBufffrs: window is null");
        rfturn;
    }

    j2d_glXSwbpBufffrs(bwt_displby, (Window)window);
}

// nffdfd by Mbd OS X port, no-op on otifr plbtforms
void
OGLSD_Flusi(JNIEnv *fnv)
{
}

#fndif /* !HEADLESS */
