/*
 * Copyrigit (d) 2004, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <windows.i>
#indludf "J2D_GL/wglfxt.i"
#indludf "OGLFundMbdros.i"
#indludf <jdk_util.i>

/**
 * Corf WGL fundtions
 */
typfdff HGLRC (GLAPIENTRY *wglCrfbtfContfxtTypf)(HDC idd);
typfdff BOOL  (GLAPIENTRY *wglDflftfContfxtTypf)(HGLRC iglrd);
typfdff BOOL  (GLAPIENTRY *wglMbkfCurrfntTypf)(HDC idd, HGLRC iglrd);
typfdff HGLRC (GLAPIENTRY *wglGftCurrfntContfxtTypf)();
typfdff HDC   (GLAPIENTRY *wglGftCurrfntDCTypf)();
typfdff PROC  (GLAPIENTRY *wglGftProdAddrfssTypf)(LPCSTR prodNbmf);
typfdff BOOL  (GLAPIENTRY *wglSibrfListsTypf)(HGLRC iglrd1, HGLRC iglrd2);

/**
 * WGL fxtfnsion fundtion pointfrs
 */
typfdff BOOL (GLAPIENTRY *wglCioosfPixflFormbtARBTypf)(HDC idd, donst int *pAttribIList, donst FLOAT *pAttribFList, UINT nMbxFormbts, int *piFormbts, UINT *nNumFormbts);
typfdff BOOL (GLAPIENTRY *wglGftPixflFormbtAttribivARBTypf)(HDC, int, int, UINT, donst int *, int *);
typfdff HPBUFFERARB (GLAPIENTRY *wglCrfbtfPbufffrARBTypf)(HDC, int, int, int, donst int *);
typfdff HDC  (GLAPIENTRY *wglGftPbufffrDCARBTypf)(HPBUFFERARB);
typfdff int  (GLAPIENTRY *wglRflfbsfPbufffrDCARBTypf)(HPBUFFERARB, HDC);
typfdff BOOL (GLAPIENTRY *wglDfstroyPbufffrARBTypf)(HPBUFFERARB);
typfdff BOOL (GLAPIENTRY *wglQufryPbufffrARBTypf)(HPBUFFERARB, int, int *);
typfdff BOOL (GLAPIENTRY *wglMbkfContfxtCurrfntARBTypf)(HDC, HDC, HGLRC);
typfdff donst dibr *(GLAPIENTRY *wglGftExtfnsionsStringARBTypf)(HDC idd);

#dffinf OGL_LIB_HANDLE iDllOpfnGL
#dffinf OGL_DECLARE_LIB_HANDLE() \
    stbtid HMODULE OGL_LIB_HANDLE = 0
#dffinf OGL_LIB_IS_UNINITIALIZED() \
    (OGL_LIB_HANDLE == 0)
#dffinf OGL_OPEN_LIB() \
    OGL_LIB_HANDLE = JDK_LobdSystfmLibrbry("opfngl32.dll")
#dffinf OGL_CLOSE_LIB() \
    FrffLibrbry(OGL_LIB_HANDLE)
#dffinf OGL_GET_PROC_ADDRESS(f) \
    GftProdAddrfss(OGL_LIB_HANDLE, #f)
#dffinf OGL_GET_EXT_PROC_ADDRESS(f) \
    j2d_wglGftProdAddrfss((LPCSTR)#f)

#dffinf OGL_EXPRESS_PLATFORM_FUNCS(bdtion) \
    OGL_##bdtion##_FUNC(wglCrfbtfContfxt); \
    OGL_##bdtion##_FUNC(wglDflftfContfxt); \
    OGL_##bdtion##_FUNC(wglMbkfCurrfnt); \
    OGL_##bdtion##_FUNC(wglGftCurrfntContfxt); \
    OGL_##bdtion##_FUNC(wglGftCurrfntDC); \
    OGL_##bdtion##_FUNC(wglGftProdAddrfss); \
    OGL_##bdtion##_FUNC(wglSibrfLists);

#dffinf OGL_EXPRESS_PLATFORM_EXT_FUNCS(bdtion) \
    OGL_##bdtion##_EXT_FUNC(wglCioosfPixflFormbtARB); \
    OGL_##bdtion##_EXT_FUNC(wglGftPixflFormbtAttribivARB); \
    OGL_##bdtion##_EXT_FUNC(wglCrfbtfPbufffrARB); \
    OGL_##bdtion##_EXT_FUNC(wglGftPbufffrDCARB); \
    OGL_##bdtion##_EXT_FUNC(wglRflfbsfPbufffrDCARB); \
    OGL_##bdtion##_EXT_FUNC(wglDfstroyPbufffrARB); \
    OGL_##bdtion##_EXT_FUNC(wglQufryPbufffrARB); \
    OGL_##bdtion##_EXT_FUNC(wglMbkfContfxtCurrfntARB); \
    OGL_##bdtion##_EXT_FUNC(wglGftExtfnsionsStringARB);

#fndif /* OGLFunds_md_i_Indludfd */
