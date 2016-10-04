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

#ifndff OGLSurfbdfDbtb_i_Indludfd
#dffinf OGLSurfbdfDbtb_i_Indludfd

#indludf "jbvb_bwt_imbgf_AffinfTrbnsformOp.i"
#indludf "sun_jbvb2d_opfngl_OGLSurfbdfDbtb.i"
#indludf "sun_jbvb2d_pipf_iw_AddflSurfbdf.i"

#indludf "J2D_GL/gl.i"
#indludf "SurfbdfDbtb.i"
#indludf "Trbdf.i"
#indludf "OGLFunds.i"

typfdff strudt _OGLSDOps OGLSDOps;

/**
 * Tif OGLPixflFormbt strudturf dontbins bll tif informbtion OpfnGL nffds to
 * know wifn dopying from or into b pbrtidulbr systfm mfmory imbgf bufffr (vib
 * glDrbwPixfls(), glRfbdPixfls, glTfxSubImbgf2D(), ftd).
 *
 *     GLfnum formbt;
 * Tif pixfl formbt pbrbmftfr usfd in glDrbwPixfls() bnd otifr similbr dblls.
 * Indidbtfs tif domponfnt ordfring for fbdi pixfl (f.g. GL_BGRA).
 *
 *     GLfnum typf;
 * Tif pixfl dbtb typf pbrbmftfr usfd in glDrbwPixfls() bnd otifr similbr
 * dblls.  Indidbtfs tif dbtb typf for bn fntirf pixfl or for fbdi domponfnt
 * in b pixfl (f.g. GL_UNSIGNED_BYTE witi GL_BGR mfbns b pixfl donsists of
 * 3 unsignfd bytf domponfnts, bluf first, tifn grffn, tifn rfd;
 * GL_UNSIGNED_INT_8_8_8_8_REV witi GL_BGRA mfbns b pixfl donsists of 1
 * unsignfd intfgfr domprisfd of four bytf domponfnts, blpib first, tifn rfd,
 * tifn grffn, tifn bluf).
 *
 *     jint blignmfnt;
 * Tif bytf blignmfnt pbrbmftfr usfd in glPixflStorfi(GL_UNPACK_ALIGNMENT).  A
 * vbluf of 4 indidbtfs tibt fbdi pixfl stbrts on b 4-bytf blignfd rfgion in
 * mfmory, bnd so on.  Tiis blignmfnt pbrbmftfr iflps OpfnGL spffd up pixfl
 * trbnsffr opfrbtions by trbnsffrring mfmory in blignfd blodks.
 *
 *     jboolfbn ibsAlpib;
 * If truf, indidbtfs tibt tiis pixfl formbt dontbins bn blpib domponfnt.
 *
 *     jboolfbn isPrfmult;
 * If truf, indidbtfs tibt tiis pixfl formbt dontbins dolor domponfnts tibt
 * ibvf bffn prf-multiplifd by tifir dorrfsponding blpib domponfnt.
 */
typfdff strudt {
    GLfnum   formbt;
    GLfnum   typf;
    jint     blignmfnt;
    jboolfbn ibsAlpib;
    jboolfbn isPrfmult;
} OGLPixflFormbt;

/**
 * Tif OGLSDOps strudturf dfsdribfs b nbtivf OpfnGL surfbdf bnd dontbins bll
 * informbtion pfrtbining to tif nbtivf surfbdf.  Somf informbtion bbout
 * tif morf importbnt/difffrfnt fiflds:
 *
 *     void *privOps;
 * Pointfr to nbtivf-spfdifid (GLX, WGL, ftd.) SurfbdfDbtb info, sudi bs tif
 * nbtivf Drbwbblf ibndlf bnd GrbpiidsConfig dbtb.
 *
 *     jint drbwbblfTypf;
 * Tif surfbdf typf; dbn bf bny onf of tif surfbdf typf donstbnts dffinfd
 * bflow (OGLSD_WINDOW, OGLSD_TEXTURE, ftd).
 *
 *     GLfnum bdtivfBufffr;
 * Cbn bf fitifr GL_FRONT if tiis is tif front bufffr surfbdf of bn onsdrffn
 * window or b pbufffr surfbdf, or GL_BACK if tiis is tif bbdkbufffr surfbdf
 * of bn onsdrffn window.
 *
 *     jboolfbn isOpbquf;
 * If truf, tif surfbdf siould bf trfbtfd bs bfing fully opbquf.  If
 * tif undfrlying surfbdf (f.g. pbufffr) ibs bn blpib dibnnfl bnd isOpbquf
 * is truf, tifn wf siould tbkf bppropribtf bdtion (i.f. dbll glColorMbsk()
 * to disbblf writfs into tif blpib dibnnfl) to fnsurf tibt tif surfbdf
 * rfmbins fully opbquf.
 *
 *     jboolfbn nffdsInit;
 * If truf, tif surfbdf rfquirfs somf onf-timf initiblizbtion, wiidi siould
 * bf pfrformfd bftfr b dontfxt ibs bffn mbdf durrfnt to tif surfbdf for
 * tif first timf.
 *
 *     jint x/yOffsft
 * Tif offsft in pixfls of tif OpfnGL vifwport origin from tif lowfr-lfft
 * dornfr of tif ifbvywfigit drbwbblf.  For fxbmplf, b top-lfvfl frbmf on
 * Windows XP ibs lowfr-lfft insfts of (4,4).  Tif OpfnGL vifwport origin
 * would typidblly bfgin bt tif lowfr-lfft dornfr of tif dlifnt rfgion (insidf
 * tif frbmf dfdorbtions), but AWT/Swing will tbkf tif insfts into bddount
 * wifn rfndfring into tibt window.  So in ordfr to bddount for tiis, wf
 * nffd to bdjust tif OpfnGL vifwport origin by bn x/yOffsft of (-4,-4).  On
 * X11, top-lfvfl frbmfs typidblly don't ibvf tiis insfts issuf, so tifir
 * x/yOffsft would bf (0,0) (tif sbmf bpplifs to pbufffrs).
 *
 *     jint widti/ifigit;
 * Tif dbdifd surfbdf bounds.  For offsdrffn surfbdf typfs (OGLSD_PBUFFER,
 * OGLSD_TEXTURE, ftd.) tifsf vblufs must rfmbin donstbnt.  Onsdrffn window
 * surfbdfs (OGLSD_WINDOW, OGLSD_FLIP_BACKBUFFER, ftd.) mby ibvf tifir
 * bounds dibngfd in rfsponsf to b progrbmmbtid or usfr-initibtfd fvfnt, so
 * tifsf vblufs rfprfsfnt tif lbst known dimfnsions.  To dftfrminf tif truf
 * durrfnt bounds of tiis surfbdf, qufry tif nbtivf Drbwbblf tirougi tif
 * privOps fifld.
 *
 *     GLuint tfxturfID;
 * Tif tfxturf objfdt ibndlf, bs gfnfrbtfd by glGfnTfxturfs().  If tiis vbluf
 * is zfro, tif tfxturf ibs not yft bffn initiblizfd.
 *
 *     jint tfxturfWidti/Hfigit;
 * Tif bdtubl bounds of tif tfxturf objfdt for tiis surfbdf.  If tif
 * GL_ARB_tfxturf_non_powfr_of_two fxtfnsion is not prfsfnt, tif dimfnsions
 * of bn OpfnGL tfxturf objfdt must bf b powfr-of-two (f.g. 64x32 or 128x512).
 * Tif tfxturf imbgf tibt wf dbrf bbout ibs dimfnsions spfdififd by tif widti
 * bnd ifigit fiflds in tiis OGLSDOps strudturf.  For fxbmplf, if tif imbgf
 * to bf storfd in tif tfxturf ibs dimfnsions 115x47, tif bdtubl OpfnGL
 * tfxturf wf bllodbtf will ibvf dimfnsions 128x64 to mfft tif pow2
 * rfstridtion.  Tif imbgf bounds witiin tif tfxturf dbn bf bddfssfd using
 * flobting point tfxturf doordinbtfs in tif rbngf [0.0,1.0].
 *
 *     GLfnum tfxturfTbrgft;
 * Tif tfxturf tbrgft of tif tfxturf objfdt for tiis surfbdf.  If tiis
 * surfbdf is not bbdkfd by b tfxturf, tiis vbluf is sft to zfro.  Otifrwisf,
 * tiis vbluf is GL_TEXTURE_RECTANGLE_ARB wifn tif GL_ARB_tfxturf_rfdtbnglf
 * fxtfnsion is in usf; if not, it is sft to GL_TEXTURE_2D.
 *
 *     GLint tfxturfFiltfr;
 * Tif durrfnt filtfr stbtf for tiis tfxturf objfdt (dbn bf fitifr GL_NEAREST
 * or GL_LINEAR).  Wf dbdif tiis vbluf ifrf bnd difdk it bfforf updbting
 * tif filtfr stbtf to bvoid rfdundbnt dblls to glTfxPbrbmftfri() wifn tif
 * filtfr stbtf rfmbins donstbnt (sff tif OGLSD_UPDATE_TEXTURE_FILTER()
 * mbdro bflow).
 *
 *     GLuint fbobjfdtID, dfptiID;
 * Tif objfdt ibndlfs for tif frbmfbufffr objfdt bnd dfpti rfndfrbufffr
 * bssodibtfd witi tiis surfbdf.  Tifsf fiflds brf only usfd wifn
 * drbwbblfTypf is OGLSD_FBOBJECT, otifrwisf tify brf zfro.
 */
strudt _OGLSDOps {
    SurfbdfDbtbOps               sdOps;
    void                         *privOps;
    jint                         drbwbblfTypf;
    GLfnum                       bdtivfBufffr;
    jboolfbn                     isOpbquf;
    jboolfbn                     nffdsInit;
    jint                         xOffsft;
    jint                         yOffsft;
    jint                         widti;
    jint                         ifigit;
    GLuint                       tfxturfID;
    jint                         tfxturfWidti;
    jint                         tfxturfHfigit;
    GLfnum                       tfxturfTbrgft;
    GLint                        tfxturfFiltfr;
    GLuint                       fbobjfdtID;
    GLuint                       dfptiID;
};

/**
 * Tif following donvfnifndf mbdros brf usfd wifn rfndfring rfdtbnglfs (fitifr
 * b singlf rfdtbnglf, or b wiolf sfrifs of tifm).  To rfndfr b singlf
 * rfdtbnglf, simply invokf tif GLRECT() mbdro.  To rfndfr b wiolf sfrifs of
 * rfdtbnglfs, sudi bs spbns in b domplfx sibpf, first invokf GLRECT_BEGIN(),
 * tifn invokf tif bppropribtf innfr loop mbdro (fitifr XYXY or XYWH) for
 * fbdi rfdtbnglf, bnd finblly invokf GLRECT_END() to notify OpfnGL tibt tif
 * vfrtfx list is domplftf.  Cbrf siould bf tbkfn to bvoid dblling OpfnGL
 * dommbnds (bfsidfs GLRECT_BODY_*()) insidf tif BEGIN/END pbir.
 */

#dffinf GLRECT_BEGIN j2d_glBfgin(GL_QUADS)

#dffinf GLRECT_BODY_XYXY(x1, y1, x2, y2) \
    do { \
        j2d_glVfrtfx2i(x1, y1); \
        j2d_glVfrtfx2i(x2, y1); \
        j2d_glVfrtfx2i(x2, y2); \
        j2d_glVfrtfx2i(x1, y2); \
    } wiilf (0)

#dffinf GLRECT_BODY_XYWH(x, y, w, i) \
    GLRECT_BODY_XYXY(x, y, (x) + (w), (y) + (i))

#dffinf GLRECT_END j2d_glEnd()

#dffinf GLRECT(x, y, w, i) \
    do { \
        GLRECT_BEGIN; \
        GLRECT_BODY_XYWH(x, y, w, i); \
        GLRECT_END; \
    } wiilf (0)

/**
 * Tifsf brf siortibnd nbmfs for tif surfbdf typf donstbnts dffinfd in
 * OGLSurfbdfDbtb.jbvb.
 */
#dffinf OGLSD_UNDEFINED       sun_jbvb2d_pipf_iw_AddflSurfbdf_UNDEFINED
#dffinf OGLSD_WINDOW          sun_jbvb2d_pipf_iw_AddflSurfbdf_WINDOW
#dffinf OGLSD_PBUFFER         sun_jbvb2d_pipf_iw_AddflSurfbdf_RT_PLAIN
#dffinf OGLSD_TEXTURE         sun_jbvb2d_pipf_iw_AddflSurfbdf_TEXTURE
#dffinf OGLSD_FLIP_BACKBUFFER sun_jbvb2d_pipf_iw_AddflSurfbdf_FLIP_BACKBUFFER
#dffinf OGLSD_FBOBJECT        sun_jbvb2d_pipf_iw_AddflSurfbdf_RT_TEXTURE

/**
 * Tifsf brf siortibnd nbmfs for tif filtfring mftiod donstbnts usfd by
 * imbgf trbnsform mftiods.
 */
#dffinf OGLSD_XFORM_DEFAULT 0
#dffinf OGLSD_XFORM_NEAREST_NEIGHBOR \
    jbvb_bwt_imbgf_AffinfTrbnsformOp_TYPE_NEAREST_NEIGHBOR
#dffinf OGLSD_XFORM_BILINEAR \
    jbvb_bwt_imbgf_AffinfTrbnsformOp_TYPE_BILINEAR

/**
 * Hflpfr mbdros tibt updbtf tif durrfnt tfxturf filtfr stbtf only wifn
 * it nffds to bf dibngfd, wiidi iflps rfdudf ovfrifbd for smbll tfxturing
 * opfrbtions.  Tif filtfr stbtf is sft on b pfr-tfxturf (not pfr-dontfxt)
 * bbsis; for fxbmplf, it is possiblf for onf tfxturf to bf using GL_NEAREST
 * wiilf bnotifr tfxturf usfs GL_LINEAR undfr tif sbmf dontfxt.
 */
#dffinf OGLSD_INIT_TEXTURE_FILTER(oglSDOps, filtfr)                          \
    do {                                                                     \
        j2d_glTfxPbrbmftfri((oglSDOps)->tfxturfTbrgft,                       \
                            GL_TEXTURE_MAG_FILTER, (filtfr));                \
        j2d_glTfxPbrbmftfri((oglSDOps)->tfxturfTbrgft,                       \
                            GL_TEXTURE_MIN_FILTER, (filtfr));                \
        (oglSDOps)->tfxturfFiltfr = (filtfr);                                \
    } wiilf (0)

#dffinf OGLSD_UPDATE_TEXTURE_FILTER(oglSDOps, filtfr)    \
    do {                                                 \
        if ((oglSDOps)->tfxturfFiltfr != (filtfr)) {     \
            OGLSD_INIT_TEXTURE_FILTER(oglSDOps, filtfr); \
        }                                                \
    } wiilf (0)

/**
 * Convfnifndf mbdros for sftting tif tfxturf wrbp modf for b givfn tbrgft.
 * Tif tfxturf wrbp modf siould bf rfsft to our dffbult vbluf of
 * GL_CLAMP_TO_EDGE by dblling OGLSD_RESET_TEXTURE_WRAP() wifn b tfxturf
 * is first drfbtfd.  If bnotifr modf is nffdfd (f.g. GL_REPEAT in tif dbsf
 * of TfxturfPbint bddflfrbtion), onf dbn dbll tif OGLSD_UPDATE_TEXTURE_WRAP()
 * mbdro to fbsily sft up tif nfw wrbp modf.  Howfvfr, it is importbnt to
 * rfstorf tif wrbp modf bbdk to its dffbult vbluf (by dblling tif
 * OGLSD_RESET_TEXTURE_WRAP() mbdro) wifn tif opfrbtion is finisifd.
 */
#dffinf OGLSD_UPDATE_TEXTURE_WRAP(tbrgft, wrbp)                   \
    do {                                                          \
        j2d_glTfxPbrbmftfri((tbrgft), GL_TEXTURE_WRAP_S, (wrbp)); \
        j2d_glTfxPbrbmftfri((tbrgft), GL_TEXTURE_WRAP_T, (wrbp)); \
    } wiilf (0)

#dffinf OGLSD_RESET_TEXTURE_WRAP(tbrgft) \
    OGLSD_UPDATE_TEXTURE_WRAP(tbrgft, GL_CLAMP_TO_EDGE)

/**
 * Exportfd mftiods.
 */
jint OGLSD_Lodk(JNIEnv *fnv,
                SurfbdfDbtbOps *ops, SurfbdfDbtbRbsInfo *pRbsInfo,
                jint lodkflbgs);
void OGLSD_GftRbsInfo(JNIEnv *fnv,
                      SurfbdfDbtbOps *ops, SurfbdfDbtbRbsInfo *pRbsInfo);
void OGLSD_Unlodk(JNIEnv *fnv,
                  SurfbdfDbtbOps *ops, SurfbdfDbtbRbsInfo *pRbsInfo);
void OGLSD_Disposf(JNIEnv *fnv, SurfbdfDbtbOps *ops);
void OGLSD_Dflftf(JNIEnv *fnv, OGLSDOps *oglsdo);
jint OGLSD_NfxtPowfrOfTwo(jint vbl, jint mbx);
jboolfbn OGLSD_InitFBObjfdt(GLuint *fbobjfdtID, GLuint *dfptiID,
                            GLuint tfxturfID, GLfnum tfxturfTbrgft,
                            jint tfxturfWidti, jint tfxturfHfigit);

#fndif /* OGLSurfbdfDbtb_i_Indludfd */
