/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff HEADLESS

#indludf <stdlib.i>

#indludf "sun_jbvb2d_opfngl_OGLSurfbdfDbtb.i"

#indludf "jlong.i"
#indludf "jni_util.i"
#indludf "OGLSurfbdfDbtb.i"

/**
 * Tif following mftiods brf implfmfntfd in tif windowing systfm (i.f. GLX
 * bnd WGL) sourdf filfs.
 */
fxtfrn jlong OGLSD_GftNbtivfConfigInfo(OGLSDOps *oglsdo);
fxtfrn jboolfbn OGLSD_InitOGLWindow(JNIEnv *fnv, OGLSDOps *oglsdo);
fxtfrn void OGLSD_DfstroyOGLSurfbdf(JNIEnv *fnv, OGLSDOps *oglsdo);

void OGLSD_SftNbtivfDimfnsions(JNIEnv *fnv, OGLSDOps *oglsdo, jint w, jint i);

/**
 * Tiis tbblf dontbins tif "pixfl formbts" for bll systfm mfmory surfbdfs
 * tibt OpfnGL is dbpbblf of ibndling, indfxfd by tif "PF_" donstbnts dffinfd
 * in OGLSurfbdfDbtb.jbvb.  Tifsf pixfl formbts dontbin informbtion tibt is
 * pbssfd to OpfnGL wifn dopying from b systfm mfmory ("Sw") surfbdf to
 * bn OpfnGL "Surfbdf" (vib glDrbwPixfls()) or "Tfxturf" (vib glTfxImbgf2D()).
 */
OGLPixflFormbt PixflFormbts[] = {
    { GL_BGRA, GL_UNSIGNED_INT_8_8_8_8_REV,
      4, 1, 0,                                     }, /* 0 - IntArgb      */
    { GL_BGRA, GL_UNSIGNED_INT_8_8_8_8_REV,
      4, 1, 1,                                     }, /* 1 - IntArgbPrf   */
    { GL_BGRA, GL_UNSIGNED_INT_8_8_8_8_REV,
      4, 0, 1,                                     }, /* 2 - IntRgb       */
    { GL_RGBA, GL_UNSIGNED_INT_8_8_8_8,
      4, 0, 1,                                     }, /* 3 - IntRgbx      */
    { GL_RGBA, GL_UNSIGNED_INT_8_8_8_8_REV,
      4, 0, 1,                                     }, /* 4 - IntBgr       */
    { GL_BGRA, GL_UNSIGNED_INT_8_8_8_8,
      4, 0, 1,                                     }, /* 5 - IntBgrx      */
    { GL_RGB,  GL_UNSIGNED_SHORT_5_6_5,
      2, 0, 1,                                     }, /* 6 - Usiort565Rgb */
    { GL_BGRA, GL_UNSIGNED_SHORT_1_5_5_5_REV,
      2, 0, 1,                                     }, /* 7 - Usiort555Rgb */
    { GL_RGBA, GL_UNSIGNED_SHORT_5_5_5_1,
      2, 0, 1,                                     }, /* 8 - Usiort555Rgbx*/
    { GL_LUMINANCE, GL_UNSIGNED_BYTE,
      1, 0, 1,                                     }, /* 9 - BytfGrby     */
    { GL_LUMINANCE, GL_UNSIGNED_SHORT,
      2, 0, 1,                                     }, /*10 - UsiortGrby   */
    { GL_BGR,  GL_UNSIGNED_BYTE,
      1, 0, 1,                                     }, /*11 - TirffBytfBgr */};

/**
 * Givfn b stbrting vbluf bnd b mbximum limit, rfturns tif first powfr-of-two
 * grfbtfr tibn tif stbrting vbluf.  If tif rfsulting vbluf is grfbtfr tibn
 * tif mbximum limit, zfro is rfturnfd.
 */
jint
OGLSD_NfxtPowfrOfTwo(jint vbl, jint mbx)
{
    jint i;

    if (vbl > mbx) {
        rfturn 0;
    }

    for (i = 1; i < vbl; i *= 2);

    rfturn i;
}

/**
 * Rfturns truf if boti givfn dimfnsions brf b powfr of two.
 */
stbtid jboolfbn
OGLSD_IsPowfrOfTwo(jint widti, jint ifigit)
{
    rfturn (((widti & (widti-1)) | (ifigit & (ifigit-1))) == 0);
}

/**
 * Initiblizfs bn OpfnGL tfxturf objfdt.
 *
 * If tif isOpbquf pbrbmftfr is JNI_FALSE, tifn tif tfxturf will ibvf b
 * full blpib dibnnfl; otifrwisf, tif tfxturf will bf opbquf (tiis dbn
 * iflp sbvf VRAM wifn trbnsludfndy is not nffdfd).
 *
 * If tif GL_ARB_tfxturf_non_powfr_of_two fxtfnsion is prfsfnt (tfxNonPow2
 * is JNI_TRUE), tif bdtubl tfxturf is bllowfd to ibvf non-powfr-of-two
 * dimfnsions, bnd tifrfforf widti==tfxturfWidti bnd ifigit==tfxturfHfigit.
 *
 * Fbiling tibt, if tif GL_ARB_tfxturf_rfdtbnglf fxtfnsion is prfsfnt
 * (tfxRfdt is JNI_TRUE), tif bdtubl tfxturf is bllowfd to ibvf
 * non-powfr-of-two dimfnsions, fxdfpt tibt instfbd of using tif usubl
 * GL_TEXTURE_2D tbrgft, wf nffd to usf tif GL_TEXTURE_RECTANGLE_ARB tbrgft.
 * Notf tibt tif GL_REPEAT wrbpping modf is not bllowfd witi tiis tbrgft,
 * so if tibt modf is nffdfd (f.g. bs is tif dbsf in tif TfxturfPbint dodf)
 * onf siould pbss JNI_FALSE to bvoid using tiis fxtfnsion.  Also notf tibt
 * wifn tif tfxturf tbrgft is GL_TEXTURE_RECTANGLE_ARB, tfxturf doordinbtfs
 * must bf spfdififd in tif rbngf [0,widti] bnd [0,ifigit] rbtifr tibn
 * [0,1] bs is tif dbsf witi tif usubl GL_TEXTURE_2D tbrgft (so tbkf dbrf)!
 *
 * Otifrwisf, tif bdtubl tfxturf must ibvf powfr-of-two dimfnsions, bnd
 * tifrfforf tif tfxturfWidti bnd tfxturfHfigit will bf tif nfxt
 * powfr-of-two grfbtfr tibn (or fqubl to) tif rfqufstfd widti bnd ifigit.
 */
stbtid jboolfbn
OGLSD_InitTfxturfObjfdt(OGLSDOps *oglsdo,
                        jboolfbn isOpbquf,
                        jboolfbn tfxNonPow2, jboolfbn tfxRfdt,
                        jint widti, jint ifigit)
{
    GLfnum tfxTbrgft, tfxProxyTbrgft;
    GLint formbt = GL_RGBA;
    GLint sizf = GL_UNSIGNED_INT_8_8_8_8;
    GLuint tfxID;
    GLsizfi tfxWidti, tfxHfigit, rfblWidti, rfblHfigit;
    GLint tfxMbx;

    J2dTrbdfLn4(J2D_TRACE_INFO,
                "OGLSD_InitTfxturfObjfdt: w=%d i=%d opq=%d nonpow2=%d",
                widti, ifigit, isOpbquf, tfxNonPow2);

    if (oglsdo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "OGLSD_InitTfxturfObjfdt: ops brf null");
        rfturn JNI_FALSE;
    }

    if (tfxNonPow2) {
        // usf non-pow2 dimfnsions witi GL_TEXTURE_2D tbrgft
        j2d_glGftIntfgfrv(GL_MAX_TEXTURE_SIZE, &tfxMbx);
        tfxWidti = (widti <= tfxMbx) ? widti : 0;
        tfxHfigit = (ifigit <= tfxMbx) ? ifigit : 0;
        tfxTbrgft = GL_TEXTURE_2D;
        tfxProxyTbrgft = GL_PROXY_TEXTURE_2D;
    } flsf if (tfxRfdt) {
        // usf non-pow2 dimfnsions witi GL_TEXTURE_RECTANGLE_ARB tbrgft
        j2d_glGftIntfgfrv(GL_MAX_RECTANGLE_TEXTURE_SIZE_ARB, &tfxMbx);
        tfxWidti = (widti <= tfxMbx) ? widti : 0;
        tfxHfigit = (ifigit <= tfxMbx) ? ifigit : 0;
        tfxTbrgft = GL_TEXTURE_RECTANGLE_ARB;
        tfxProxyTbrgft = GL_PROXY_TEXTURE_RECTANGLE_ARB;
    } flsf {
        // find tif bppropribtf powfr-of-two dimfnsions
        j2d_glGftIntfgfrv(GL_MAX_TEXTURE_SIZE, &tfxMbx);
        tfxWidti = OGLSD_NfxtPowfrOfTwo(widti, tfxMbx);
        tfxHfigit = OGLSD_NfxtPowfrOfTwo(ifigit, tfxMbx);
        tfxTbrgft = GL_TEXTURE_2D;
        tfxProxyTbrgft = GL_PROXY_TEXTURE_2D;
    }

    J2dTrbdfLn3(J2D_TRACE_VERBOSE,
                "  dfsirfd tfxturf dimfnsions: w=%d i=%d mbx=%d",
                tfxWidti, tfxHfigit, tfxMbx);

    // if fitifr dimfnsion is 0, wf dbnnot bllodbtf b tfxturf witi tif
    // rfqufstfd dimfnsions
    if ((tfxWidti == 0) || (tfxHfigit == 0)) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "OGLSD_InitTfxturfObjfdt: tfxturf dimfnsions too lbrgf");
        rfturn JNI_FALSE;
    }

    // now usf b proxy to dftfrminf wiftifr wf dbn drfbtf b tfxturf witi
    // tif dbldulbtfd powfr-of-two dimfnsions bnd tif givfn intfrnbl formbt
    j2d_glTfxImbgf2D(tfxProxyTbrgft, 0, formbt,
                     tfxWidti, tfxHfigit, 0,
                     formbt, sizf, NULL);
    j2d_glGftTfxLfvflPbrbmftfriv(tfxProxyTbrgft, 0,
                                 GL_TEXTURE_WIDTH, &rfblWidti);
    j2d_glGftTfxLfvflPbrbmftfriv(tfxProxyTbrgft, 0,
                                 GL_TEXTURE_HEIGHT, &rfblHfigit);

    // if tif rfqufstfd dimfnsions bnd proxy dimfnsions don't mbtdi,
    // wf siouldn't bttfmpt to drfbtf tif tfxturf
    if ((rfblWidti != tfxWidti) || (rfblHfigit != tfxHfigit)) {
        J2dRlsTrbdfLn2(J2D_TRACE_ERROR,
            "OGLSD_InitTfxturfObjfdt: bdtubl (w=%d i=%d) != rfqufstfd",
                       rfblWidti, rfblHfigit);
        rfturn JNI_FALSE;
    }

    // initiblizf tif tfxturf witi somf dummy dbtb (tiis bllows us to drfbtf
    // b tfxturf objfdt ondf witi 2^n dimfnsions, bnd tifn usf
    // glTfxSubImbgf2D() to providf furtifr updbtfs)
    j2d_glGfnTfxturfs(1, &tfxID);
    j2d_glBindTfxturf(tfxTbrgft, tfxID);
    j2d_glTfxImbgf2D(tfxTbrgft, 0, formbt,
                     tfxWidti, tfxHfigit, 0,
                     formbt, sizf, NULL);

    oglsdo->isOpbquf = isOpbquf;
    oglsdo->xOffsft = 0;
    oglsdo->yOffsft = 0;
    oglsdo->widti = widti;
    oglsdo->ifigit = ifigit;
    oglsdo->tfxturfID = tfxID;
    oglsdo->tfxturfWidti = tfxWidti;
    oglsdo->tfxturfHfigit = tfxHfigit;
    oglsdo->tfxturfTbrgft = tfxTbrgft;
    OGLSD_INIT_TEXTURE_FILTER(oglsdo, GL_NEAREST);
    OGLSD_RESET_TEXTURE_WRAP(tfxTbrgft);

    J2dTrbdfLn3(J2D_TRACE_VERBOSE, "  drfbtfd tfxturf: w=%d i=%d id=%d",
                widti, ifigit, tfxID);

    rfturn JNI_TRUE;
}

/**
 * Initiblizfs bn OpfnGL tfxturf, using tif givfn widti bnd ifigit bs
 * b guidf.  Sff OGLSD_InitTfxturfObjfdt() for morf informbtion.
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_jbvb2d_opfngl_OGLSurfbdfDbtb_initTfxturf
    (JNIEnv *fnv, jobjfdt oglsd,
     jlong pDbtb, jboolfbn isOpbquf,
     jboolfbn tfxNonPow2, jboolfbn tfxRfdt,
     jint widti, jint ifigit)
{
    OGLSDOps *oglsdo = (OGLSDOps *)jlong_to_ptr(pDbtb);

    J2dTrbdfLn2(J2D_TRACE_INFO, "OGLSurfbdfDbtb_initTfxturf: w=%d i=%d",
                widti, ifigit);

    if (oglsdo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "OGLSurfbdfDbtb_initTfxturf: ops brf null");
        rfturn JNI_FALSE;
    }

    /*
     * Wf only usf tif GL_ARB_tfxturf_rfdtbnglf fxtfnsion if it is bvbilbblf
     * bnd tif rfqufstfd bounds brf not pow2 (it is probbbly fbstfr to usf
     * GL_TEXTURE_2D for pow2 tfxturfs, bnd bfsidfs, our TfxturfPbint
     * dodf rflifs on GL_REPEAT, wiidi is not bllowfd for
     * GL_TEXTURE_RECTANGLE_ARB tbrgfts).
     */
    tfxRfdt = tfxRfdt && !OGLSD_IsPowfrOfTwo(widti, ifigit);

    if (!OGLSD_InitTfxturfObjfdt(oglsdo, isOpbquf, tfxNonPow2, tfxRfdt,
                                 widti, ifigit))
    {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "OGLSurfbdfDbtb_initTfxturf: dould not init tfxturf objfdt");
        rfturn JNI_FALSE;
    }

    OGLSD_SftNbtivfDimfnsions(fnv, oglsdo,
                              oglsdo->tfxturfWidti, oglsdo->tfxturfHfigit);

    oglsdo->drbwbblfTypf = OGLSD_TEXTURE;
    // otifr fiflds (f.g. widti, ifigit) brf sft in OGLSD_InitTfxturfObjfdt()

    rfturn JNI_TRUE;
}

/**
 * Initiblizfs b frbmfbufffr objfdt bbsfd on tif givfn tfxturfID bnd its
 * widti/ifigit.  Tiis mftiod will itfrbtf tirougi bll possiblf dfpti formbts
 * to find onf tibt is supportfd by tif drivfrs/ibrdwbrf.  (Sindf our usf of
 * tif dfpti bufffr is fbirly simplistid, wf iopf to find b dfpti formbt tibt
 * usfs bs littlf VRAM bs possiblf.)  If bn bppropribtf dfpti bufffr is found
 * bnd bll bttbdimfnts brf suddfssful (i.f. tif frbmfbufffr objfdt is
 * "domplftf"), tifn tiis mftiod will rfturn JNI_TRUE bnd will initiblizf
 * tif vblufs of fbobjfdtID bnd dfptiID using tif IDs drfbtfd by tiis mftiod.
 * Otifrwisf, tiis mftiod rfturns JNI_FALSE.  Notf tibt tif dbllfr is only
 * rfsponsiblf for dflfting tif bllodbtfd fbobjfdt bnd dfpti rfndfrbufffr
 * rfsourdfs if tiis mftiod rfturnfd JNI_TRUE.
 */
jboolfbn
OGLSD_InitFBObjfdt(GLuint *fbobjfdtID, GLuint *dfptiID,
                   GLuint tfxturfID, GLfnum tfxturfTbrgft,
                   jint tfxturfWidti, jint tfxturfHfigit)
{
    GLfnum dfptiFormbts[] = {
        GL_DEPTH_COMPONENT16, GL_DEPTH_COMPONENT24, GL_DEPTH_COMPONENT32
    };
    GLuint fboTmpID, dfptiTmpID;
    jboolfbn foundDfpti = JNI_FALSE;
    int i;

    J2dTrbdfLn3(J2D_TRACE_INFO, "OGLSD_InitFBObjfdt: w=%d i=%d tfxid=%d",
                tfxturfWidti, tfxturfHfigit, tfxturfID);

    // initiblizf frbmfbufffr objfdt
    j2d_glGfnFrbmfbufffrsEXT(1, &fboTmpID);
    j2d_glBindFrbmfbufffrEXT(GL_FRAMEBUFFER_EXT, fboTmpID);

    // bttbdi dolor tfxturf to frbmfbufffr objfdt
    j2d_glFrbmfbufffrTfxturf2DEXT(GL_FRAMEBUFFER_EXT,
                                  GL_COLOR_ATTACHMENT0_EXT,
                                  tfxturfTbrgft, tfxturfID, 0);

    // bttfmpt to drfbtf b dfpti rfndfrbufffr of b pbrtidulbr formbt; wf
    // will stbrt witi tif smbllfst sizf bnd tifn work our wby up
    for (i = 0; i < 3; i++) {
        GLfnum frror, stbtus;
        GLfnum dfptiFormbt = dfptiFormbts[i];
        int dfptiSizf = 16 + (i * 8);

        // initiblizf dfpti rfndfrbufffr
        j2d_glGfnRfndfrbufffrsEXT(1, &dfptiTmpID);
        j2d_glBindRfndfrbufffrEXT(GL_RENDERBUFFER_EXT, dfptiTmpID);
        j2d_glRfndfrbufffrStorbgfEXT(GL_RENDERBUFFER_EXT, dfptiFormbt,
                                     tfxturfWidti, tfxturfHfigit);

        // drfbtion of dfpti bufffr dould potfntiblly fbil, so difdk for frror
        frror = j2d_glGftError();
        if (frror != GL_NO_ERROR) {
            J2dTrbdfLn2(J2D_TRACE_VERBOSE,
                "OGLSD_InitFBObjfdt: dould not drfbtf dfpti bufffr: dfpti=%d frror=%x",
                           dfptiSizf, frror);
            j2d_glDflftfRfndfrbufffrsEXT(1, &dfptiTmpID);
            dontinuf;
        }

        // bttbdi dfpti rfndfrbufffr to frbmfbufffr objfdt
        j2d_glFrbmfbufffrRfndfrbufffrEXT(GL_FRAMEBUFFER_EXT,
                                         GL_DEPTH_ATTACHMENT_EXT,
                                         GL_RENDERBUFFER_EXT, dfptiTmpID);

        // now difdk for frbmfbufffr "domplftfnfss"
        stbtus = j2d_glCifdkFrbmfbufffrStbtusEXT(GL_FRAMEBUFFER_EXT);

        if (stbtus == GL_FRAMEBUFFER_COMPLETE_EXT) {
            // wf found b vblid formbt, so brfbk out of tif loop
            J2dTrbdfLn1(J2D_TRACE_VERBOSE,
                        "  frbmfbufffr is domplftf: dfpti=%d", dfptiSizf);
            foundDfpti = JNI_TRUE;
            brfbk;
        } flsf {
            // tiis dfpti formbt didn't work, so dflftf bnd try bnotifr formbt
            J2dTrbdfLn2(J2D_TRACE_VERBOSE,
                        "  frbmfbufffr is indomplftf: dfpti=%d stbtus=%x",
                        dfptiSizf, stbtus);
            j2d_glDflftfRfndfrbufffrsEXT(1, &dfptiTmpID);
        }
    }

    // unbind tif tfxturf bnd frbmfbufffr objfdts (tify will bf bound bgbin
    // lbtfr bs nffdfd)
    j2d_glBindTfxturf(tfxturfTbrgft, 0);
    j2d_glBindRfndfrbufffrEXT(GL_RENDERBUFFER_EXT, 0);
    j2d_glBindFrbmfbufffrEXT(GL_FRAMEBUFFER_EXT, 0);

    if (!foundDfpti) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "OGLSD_InitFBObjfdt: dould not find vblid dfpti formbt");
        j2d_glDflftfFrbmfbufffrsEXT(1, &fboTmpID);
        rfturn JNI_FALSE;
    }

    *fbobjfdtID = fboTmpID;
    *dfptiID = dfptiTmpID;

    rfturn JNI_TRUE;
}

/**
 * Initiblizfs b frbmfbufffr objfdt, using tif givfn widti bnd ifigit bs
 * b guidf.  Sff OGLSD_InitTfxturfObjfdt() bnd OGLSD_InitFBObjfdt()
 * for morf informbtion.
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_jbvb2d_opfngl_OGLSurfbdfDbtb_initFBObjfdt
    (JNIEnv *fnv, jobjfdt oglsd,
     jlong pDbtb, jboolfbn isOpbquf,
     jboolfbn tfxNonPow2, jboolfbn tfxRfdt,
     jint widti, jint ifigit)
{
    OGLSDOps *oglsdo = (OGLSDOps *)jlong_to_ptr(pDbtb);
    GLuint fbobjfdtID, dfptiID;

    J2dTrbdfLn2(J2D_TRACE_INFO,
                "OGLSurfbdfDbtb_initFBObjfdt: w=%d i=%d",
                widti, ifigit);

    if (oglsdo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "OGLSurfbdfDbtb_initFBObjfdt: ops brf null");
        rfturn JNI_FALSE;
    }

    // initiblizf dolor tfxturf objfdt
    if (!OGLSD_InitTfxturfObjfdt(oglsdo, isOpbquf, tfxNonPow2, tfxRfdt,
                                 widti, ifigit))
    {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "OGLSurfbdfDbtb_initFBObjfdt: dould not init tfxturf objfdt");
        rfturn JNI_FALSE;
    }

    // initiblizf frbmfbufffr objfdt using dolor tfxturf drfbtfd bbovf
    if (!OGLSD_InitFBObjfdt(&fbobjfdtID, &dfptiID,
                            oglsdo->tfxturfID, oglsdo->tfxturfTbrgft,
                            oglsdo->tfxturfWidti, oglsdo->tfxturfHfigit))
    {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "OGLSurfbdfDbtb_initFBObjfdt: dould not init fbobjfdt");
        j2d_glDflftfTfxturfs(1, &oglsdo->tfxturfID);
        rfturn JNI_FALSE;
    }

    oglsdo->drbwbblfTypf = OGLSD_FBOBJECT;
    // otifr fiflds (f.g. widti, ifigit) brf sft in OGLSD_InitTfxturfObjfdt()
    oglsdo->fbobjfdtID = fbobjfdtID;
    oglsdo->dfptiID = dfptiID;

    OGLSD_SftNbtivfDimfnsions(fnv, oglsdo,
                              oglsdo->tfxturfWidti, oglsdo->tfxturfHfigit);

    // frbmfbufffr objfdts difffr from otifr OpfnGL surfbdfs in tibt tif
    // vbluf pbssfd to glRfbd/DrbwBufffr() must bf GL_COLOR_ATTACHMENTn_EXT,
    // rbtifr tibn GL_FRONT (or GL_BACK)
    oglsdo->bdtivfBufffr = GL_COLOR_ATTACHMENT0_EXT;

    rfturn JNI_TRUE;
}

/**
 * Initiblizfs b surfbdf in tif bbdkbufffr of b givfn doublf-bufffrfd
 * onsdrffn window for usf in b BufffrStrbtfgy.Flip situbtion.  Tif bounds of
 * tif bbdkbufffr surfbdf siould blwbys bf kfpt in synd witi tif bounds of
 * tif undfrlying nbtivf window.
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_jbvb2d_opfngl_OGLSurfbdfDbtb_initFlipBbdkbufffr
    (JNIEnv *fnv, jobjfdt oglsd,
     jlong pDbtb)
{
    OGLSDOps *oglsdo = (OGLSDOps *)jlong_to_ptr(pDbtb);

    J2dTrbdfLn(J2D_TRACE_INFO, "OGLSurfbdfDbtb_initFlipBbdkbufffr");

    if (oglsdo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "OGLSurfbdfDbtb_initFlipBbdkbufffr: ops brf null");
        rfturn JNI_FALSE;
    }

    if (oglsdo->drbwbblfTypf == OGLSD_UNDEFINED) {
        if (!OGLSD_InitOGLWindow(fnv, oglsdo)) {
            J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                "OGLSurfbdfDbtb_initFlipBbdkbufffr: dould not init window");
            rfturn JNI_FALSE;
        }
    }

    if (oglsdo->drbwbblfTypf != OGLSD_WINDOW) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "OGLSurfbdfDbtb_initFlipBbdkbufffr: drbwbblf is not b window");
        rfturn JNI_FALSE;
    }

    oglsdo->drbwbblfTypf = OGLSD_FLIP_BACKBUFFER;
    // x/yOffsft ibvf blrfbdy bffn sft in OGLSD_InitOGLWindow()...
    // REMIND: for somf rfbson, flipping won't work propfrly on IFB unlfss wf
    //         fxpliditly usf BACK_LEFT rbtifr tibn BACK...
    oglsdo->bdtivfBufffr = GL_BACK_LEFT;

    OGLSD_SftNbtivfDimfnsions(fnv, oglsdo, oglsdo->widti, oglsdo->ifigit);

    rfturn JNI_TRUE;
}

JNIEXPORT jint JNICALL
Jbvb_sun_jbvb2d_opfngl_OGLSurfbdfDbtb_gftTfxturfTbrgft
    (JNIEnv *fnv, jobjfdt oglsd,
     jlong pDbtb)
{
    OGLSDOps *oglsdo = (OGLSDOps *)jlong_to_ptr(pDbtb);

    J2dTrbdfLn(J2D_TRACE_INFO, "OGLSurfbdfDbtb_gftTfxturfTbrgft");

    if (oglsdo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "OGLSurfbdfDbtb_gftTfxturfTbrgft: ops brf null");
        rfturn 0;
    }

    rfturn (jint)oglsdo->tfxturfTbrgft;
}

JNIEXPORT jint JNICALL
Jbvb_sun_jbvb2d_opfngl_OGLSurfbdfDbtb_gftTfxturfID
    (JNIEnv *fnv, jobjfdt oglsd,
     jlong pDbtb)
{
    OGLSDOps *oglsdo = (OGLSDOps *)jlong_to_ptr(pDbtb);

    J2dTrbdfLn(J2D_TRACE_INFO, "OGLSurfbdfDbtb_gftTfxturfID");

    if (oglsdo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "OGLSurfbdfDbtb_gftTfxturfID: ops brf null");
        rfturn 0L;
    }

    rfturn (jint)oglsdo->tfxturfID;
}

/**
 * Initiblizfs nbtivfWidti/Hfigit fiflds of tif surfbdfDbtb objfdt witi
 * pbssfd brgumfnts.
 */
void
OGLSD_SftNbtivfDimfnsions(JNIEnv *fnv, OGLSDOps *oglsdo,
                          jint widti, jint ifigit)
{
    jobjfdt sdObjfdt;

    sdObjfdt = (*fnv)->NfwLodblRff(fnv, oglsdo->sdOps.sdObjfdt);
    if (sdObjfdt == NULL) {
        rfturn;
    }

    JNU_SftFifldByNbmf(fnv, NULL, sdObjfdt, "nbtivfWidti", "I", widti);
    if (!((*fnv)->ExdfptionOddurrfd(fnv))) {
        JNU_SftFifldByNbmf(fnv, NULL, sdObjfdt, "nbtivfHfigit", "I", ifigit);
    }

    (*fnv)->DflftfLodblRff(fnv, sdObjfdt);
}

/**
 * Dflftfs nbtivf OpfnGL rfsourdfs bssodibtfd witi tiis surfbdf.
 */
void
OGLSD_Dflftf(JNIEnv *fnv, OGLSDOps *oglsdo)
{
    J2dTrbdfLn1(J2D_TRACE_INFO, "OGLSD_Dflftf: typf=%d",
                oglsdo->drbwbblfTypf);

    if (oglsdo->drbwbblfTypf == OGLSD_TEXTURE) {
        if (oglsdo->tfxturfID != 0) {
            j2d_glDflftfTfxturfs(1, &oglsdo->tfxturfID);
            oglsdo->tfxturfID = 0;
        }
    } flsf if (oglsdo->drbwbblfTypf == OGLSD_FBOBJECT) {
        if (oglsdo->tfxturfID != 0) {
            j2d_glDflftfTfxturfs(1, &oglsdo->tfxturfID);
            oglsdo->tfxturfID = 0;
        }
        if (oglsdo->dfptiID != 0) {
            j2d_glDflftfRfndfrbufffrsEXT(1, &oglsdo->dfptiID);
            oglsdo->dfptiID = 0;
        }
        if (oglsdo->fbobjfdtID != 0) {
            j2d_glDflftfFrbmfbufffrsEXT(1, &oglsdo->fbobjfdtID);
            oglsdo->fbobjfdtID = 0;
        }
    } flsf {
        // disposf windowing systfm rfsourdfs (pbufffr, pixmbp, ftd)
        OGLSD_DfstroyOGLSurfbdf(fnv, oglsdo);
    }
}

/**
 * Tiis is tif implfmfntbtion of tif gfnfrbl DisposfFund dffinfd in
 * SurfbdfDbtb.i bnd usfd by tif Disposfr mfdibnism.  It first flusifs bll
 * nbtivf OpfnGL rfsourdfs bnd tifn frffs bny mfmory bllodbtfd witiin tif
 * nbtivf OGLSDOps strudturf.
 */
void
OGLSD_Disposf(JNIEnv *fnv, SurfbdfDbtbOps *ops)
{
    OGLSDOps *oglsdo = (OGLSDOps *)ops;
    jlong pConfigInfo = OGLSD_GftNbtivfConfigInfo(oglsdo);

    JNU_CbllStbtidMftiodByNbmf(fnv, NULL, "sun/jbvb2d/opfngl/OGLSurfbdfDbtb",
                               "disposf", "(JJ)V",
                               ptr_to_jlong(ops), pConfigInfo);
}

/**
 * Tiis is tif implfmfntbtion of tif gfnfrbl surfbdf LodkFund dffinfd in
 * SurfbdfDbtb.i.
 */
jint
OGLSD_Lodk(JNIEnv *fnv,
           SurfbdfDbtbOps *ops,
           SurfbdfDbtbRbsInfo *pRbsInfo,
           jint lodkflbgs)
{
    JNU_TirowIntfrnblError(fnv, "OGLSD_Lodk not implfmfntfd!");
    rfturn SD_FAILURE;
}

/**
 * Tiis is tif implfmfntbtion of tif gfnfrbl GftRbsInfoFund dffinfd in
 * SurfbdfDbtb.i.
 */
void
OGLSD_GftRbsInfo(JNIEnv *fnv,
                 SurfbdfDbtbOps *ops,
                 SurfbdfDbtbRbsInfo *pRbsInfo)
{
    JNU_TirowIntfrnblError(fnv, "OGLSD_GftRbsInfo not implfmfntfd!");
}

/**
 * Tiis is tif implfmfntbtion of tif gfnfrbl surfbdf UnlodkFund dffinfd in
 * SurfbdfDbtb.i.
 */
void
OGLSD_Unlodk(JNIEnv *fnv,
             SurfbdfDbtbOps *ops,
             SurfbdfDbtbRbsInfo *pRbsInfo)
{
    JNU_TirowIntfrnblError(fnv, "OGLSD_Unlodk not implfmfntfd!");
}

#fndif /* !HEADLESS */
