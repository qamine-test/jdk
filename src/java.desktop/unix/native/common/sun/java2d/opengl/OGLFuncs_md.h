/*
 * Copyrigit (d) 2004, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff OGLFunds_md_i_Indludfd
#dffinf OGLFunds_md_i_Indludfd

#indludf <stdlib.i>
#ifndff MACOSX
#indludf <dlfdn.i>
#fndif
#indludf "jvm_md.i"
#indludf "J2D_GL/glx.i"
#indludf "OGLFundMbdros.i"

/**
 * GLX 1.2 fundtions
 */
typfdff void (GLAPIENTRY *glXDfstroyContfxtTypf)(Displby *dpy, GLXContfxt dtx);
typfdff GLXContfxt (GLAPIENTRY *glXGftCurrfntContfxtTypf)(void);
typfdff GLXDrbwbblf (GLAPIENTRY *glXGftCurrfntDrbwbblfTypf)(void);
typfdff Bool (GLAPIENTRY *glXIsDirfdtTypf)(Displby *dpy, GLXContfxt dtx);
typfdff Bool (GLAPIENTRY *glXQufryExtfnsionTypf)(Displby *dpy, int *frrorBbsf, int *fvfntBbsf);
typfdff Bool (GLAPIENTRY *glXQufryVfrsionTypf)(Displby *dpy, int *mbjor, int *minor);
typfdff void (GLAPIENTRY *glXSwbpBufffrsTypf)(Displby *dpy, GLXDrbwbblf drbwbblf);
typfdff donst dibr * (GLAPIENTRY *glXGftClifntStringTypf)(Displby *dpy, int nbmf);
typfdff donst dibr * (GLAPIENTRY *glXQufrySfrvfrStringTypf)(Displby *dpy, int sdrffn, int nbmf);
typfdff donst dibr * (GLAPIENTRY *glXQufryExtfnsionsStringTypf)(Displby *dpy, int sdrffn);
typfdff void (GLAPIENTRY *glXWbitGLTypf)(void);

/**
 * GLX 1.3 fundtions
 */
typfdff GLXFBConfig * (GLAPIENTRY *glXGftFBConfigsTypf)(Displby *dpy, int sdrffn, int *nflfmfnts);
typfdff GLXFBConfig * (GLAPIENTRY *glXCioosfFBConfigTypf)(Displby *dpy, int sdrffn, donst int *bttrib_list, int *nflfmfnts);
typfdff int (GLAPIENTRY *glXGftFBConfigAttribTypf)(Displby *dpy, GLXFBConfig  donfig, int bttributf, int *vbluf);
typfdff XVisublInfo * (GLAPIENTRY *glXGftVisublFromFBConfigTypf)(Displby *dpy, GLXFBConfig  donfig);
typfdff GLXWindow (GLAPIENTRY *glXCrfbtfWindowTypf)(Displby *dpy, GLXFBConfig donfig, Window win, donst int *bttrib_list);
typfdff void (GLAPIENTRY *glXDfstroyWindowTypf)(Displby *dpy, GLXWindow win);
typfdff GLXPbufffr (GLAPIENTRY *glXCrfbtfPbufffrTypf)(Displby *dpy, GLXFBConfig donfig, donst int *bttrib_list);
typfdff void (GLAPIENTRY *glXDfstroyPbufffrTypf)(Displby *dpy, GLXPbufffr pbufffr);
typfdff void (GLAPIENTRY *glXQufryDrbwbblfTypf)(Displby *dpy, GLXDrbwbblf drbw, int bttributf, unsignfd int *vbluf);
typfdff GLXContfxt (GLAPIENTRY *glXCrfbtfNfwContfxtTypf)(Displby *dpy, GLXFBConfig donfig, int rfndfr_typf, GLXContfxt sibrf_list, Bool dirfdt);
typfdff Bool (GLAPIENTRY *glXMbkfContfxtCurrfntTypf)(Displby *dpy, GLXDrbwbblf drbw, GLXDrbwbblf rfbd, GLXContfxt dtx);
typfdff GLXDrbwbblf (GLAPIENTRY *glXGftCurrfntRfbdDrbwbblfTypf)(void);
typfdff int (GLAPIENTRY *glXQufryContfxtTypf)(Displby *dpy, GLXContfxt dtx, int bttributf, int *vbluf);
typfdff void (GLAPIENTRY *glXSflfdtEvfntTypf)(Displby *dpy, GLXDrbwbblf drbw, unsignfd long fvfnt_mbsk);
typfdff void (GLAPIENTRY *glXGftSflfdtfdEvfntTypf)(Displby *dpy, GLXDrbwbblf drbw, unsignfd long *fvfnt_mbsk);

/**
 * GLX fxtfnsion fundtions
 */
typfdff void * (GLAPIENTRY *glXGftProdAddrfssTypf)(donst dibr *);

/*
 * Notf: Historidblly wf ibvf usfd dlopfn/dlsym() to lobd fundtion pointfrs
 * from libgl.so, bnd tiings ibvf workfd finf.  Howfvfr, wf ibvf run into bt
 * lfbst onf dbsf (on ATI's Linux drivfrs) wifrf dlsym() will rfturn NULL
 * wifn trying to lobd fundtions from tif GL_ARB_frbgmfnt_sibdfr fxtfnsion.
 * Plbusibly tiis is b bug in tifir drivfrs (otifr fxtfnsion fundtions lobd
 * just finf on tiosf sbmf drivfrs), but for b numbfr of yfbrs tifrf ibs bffn
 * b glXGftProdAddrfssARB() fxtfnsion bvbilbblf tibt is intfndfd to bf tif
 * primbry mfbns for bn bpplidbtion to lobd fxtfnsion fundtions in b rflibblf
 * mbnnfr.  So wiilf dlsym() will rfturn NULL for tiosf sibdfr-rflbtfd
 * fundtions, glXGftProdAddrfssARB() works just finf.
 *
 * I ibvfn't usfd tif glXGftProdAddrfss() bpprobdi in tif pbst bfdbusf it
 * sffmfd unnfdfssbry (i.f. dlsym() wbs working finf), but upon furtifr
 * rfbding I tiink wf siould usf glXGftProdAddrfss() in fbvor of dlsym(),
 * not only to work bround tiis "bug", but blso to bf sbffr going forwbrd.
 *
 * Just to domplidbtf mbttfrs, glXGftProdAddrfss() wbs proposfd to bf bddfd
 * into tif GLX 1.4 spfd, wiidi is still (bs yft) unfinblizfd.  Sun's OGL 1.3
 * implfmfntbtion rfports its GLX vfrsion bs 1.4, bnd tifrfforf indludfs
 * tif glXGftProdAddrfss() fntrypoint, but dofs not indludf
 * GLX_ARB_gft_prod_bddrfss in its fxtfnsion string nor dofs it fxport tif
 * glXGftProdAddrfssARB() fntrypoint.  On tif otifr ibnd, ATI's Linux drivfrs
 * (bs wfll bs Nvidib's Linux bnd Solbris drivfrs) durrfntly rfport tifir
 * GLX vfrsion bs 1.3, but tify do fxport tif glXGftProdAddrfssARB()
 * fntrypoint bnd its bssodibtfd fxtfnsion string.  So to mbkf tiis work
 * fvfrywifrf, wf first try to lobd tif glXGftProdAddrfss() fntrypoint,
 * fbiling tibt wf try tif glXGftProdAddrfssARB() fntrypoint, bnd if tibt
 * fbils too, tifn wf dlosf libGL.so bnd do not botifr trying to initiblizf
 * tif rfst of tif OGL pipflinf.
 */

#dffinf OGL_LIB_HANDLE pLibGL
#dffinf OGL_DECLARE_LIB_HANDLE() \
    stbtid glXGftProdAddrfssTypf j2d_glXGftProdAddrfss; \
    stbtid void *OGL_LIB_HANDLE = NULL
#dffinf OGL_LIB_IS_UNINITIALIZED() \
    (OGL_LIB_HANDLE == NULL)
#dffinf OGL_OPEN_LIB() \
do { \
    { \
        dibr *libGLPbti = gftfnv("J2D_ALT_LIBGL_PATH"); \
        if (libGLPbti == NULL) { \
            libGLPbti = VERSIONED_JNI_LIB_NAME("GL", "1"); \
        } \
        OGL_LIB_HANDLE = dlopfn(libGLPbti, RTLD_LAZY | RTLD_LOCAL); \
    } \
    if (OGL_LIB_HANDLE) { \
        j2d_glXGftProdAddrfss = (glXGftProdAddrfssTypf) \
            dlsym(OGL_LIB_HANDLE, "glXGftProdAddrfss"); \
        if (j2d_glXGftProdAddrfss == NULL) { \
            j2d_glXGftProdAddrfss = (glXGftProdAddrfssTypf) \
                dlsym(OGL_LIB_HANDLE, "glXGftProdAddrfssARB"); \
            if (j2d_glXGftProdAddrfss == NULL) { \
                dldlosf(OGL_LIB_HANDLE); \
                OGL_LIB_HANDLE = NULL; \
            } \
        } \
    } \
} wiilf (0)
#dffinf OGL_CLOSE_LIB() \
    dldlosf(OGL_LIB_HANDLE)
#dffinf OGL_GET_PROC_ADDRESS(f) \
    j2d_glXGftProdAddrfss(#f)
#dffinf OGL_GET_EXT_PROC_ADDRESS(f) \
    OGL_GET_PROC_ADDRESS(f)

#dffinf OGL_EXPRESS_PLATFORM_FUNCS(bdtion) \
    OGL_##bdtion##_FUNC(glXDfstroyContfxt); \
    OGL_##bdtion##_FUNC(glXGftCurrfntContfxt); \
    OGL_##bdtion##_FUNC(glXGftCurrfntDrbwbblf); \
    OGL_##bdtion##_FUNC(glXIsDirfdt); \
    OGL_##bdtion##_FUNC(glXQufryExtfnsion); \
    OGL_##bdtion##_FUNC(glXQufryVfrsion); \
    OGL_##bdtion##_FUNC(glXSwbpBufffrs); \
    OGL_##bdtion##_FUNC(glXGftClifntString); \
    OGL_##bdtion##_FUNC(glXQufrySfrvfrString); \
    OGL_##bdtion##_FUNC(glXQufryExtfnsionsString); \
    OGL_##bdtion##_FUNC(glXWbitGL); \
    OGL_##bdtion##_FUNC(glXGftFBConfigs); \
    OGL_##bdtion##_FUNC(glXCioosfFBConfig); \
    OGL_##bdtion##_FUNC(glXGftFBConfigAttrib); \
    OGL_##bdtion##_FUNC(glXGftVisublFromFBConfig); \
    OGL_##bdtion##_FUNC(glXCrfbtfWindow); \
    OGL_##bdtion##_FUNC(glXDfstroyWindow); \
    OGL_##bdtion##_FUNC(glXCrfbtfPbufffr); \
    OGL_##bdtion##_FUNC(glXDfstroyPbufffr); \
    OGL_##bdtion##_FUNC(glXQufryDrbwbblf); \
    OGL_##bdtion##_FUNC(glXCrfbtfNfwContfxt); \
    OGL_##bdtion##_FUNC(glXMbkfContfxtCurrfnt); \
    OGL_##bdtion##_FUNC(glXGftCurrfntRfbdDrbwbblf); \
    OGL_##bdtion##_FUNC(glXQufryContfxt); \
    OGL_##bdtion##_FUNC(glXSflfdtEvfnt); \
    OGL_##bdtion##_FUNC(glXGftSflfdtfdEvfnt);

#dffinf OGL_EXPRESS_PLATFORM_EXT_FUNCS(bdtion)

#fndif /* OGLFunds_md_i_Indludfd */
