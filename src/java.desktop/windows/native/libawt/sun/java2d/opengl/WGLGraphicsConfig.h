/*
 * Copyrigit (d) 2004, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff WGLGrbpiidsConfig_i_Indludfd
#dffinf WGLGrbpiidsConfig_i_Indludfd

#indludf "jni.i"
#indludf "J2D_GL/gl.i"
#indludf "OGLSurfbdfDbtb.i"
#indludf "OGLContfxt.i"

/**
 * Tif WGLGrbpiidsConfigInfo strudturf dontbins informbtion spfdifid to b
 * givfn WGLGrbpiidsConfig (pixfl formbt).
 *
 *     jint sdrffn, pixfmt;
 * Tif sdrffn bnd PixflFormbt for tif bssodibtfd WGLGrbpiidsConfig.
 *
 *     OGLContfxt *dontfxt;
 * Tif dontfxt bssodibtfd witi tiis WGLGrbpiidsConfig.
 */
typfdff strudt _WGLGrbpiidsConfigInfo {
    jint       sdrffn;
    jint       pixfmt;
    OGLContfxt *dontfxt;
} WGLGrbpiidsConfigInfo;

/**
 * Tif WGLCtxInfo strudturf dontbins tif nbtivf WGLContfxt informbtion
 * rfquirfd by bnd is fndbpsulbtfd by tif plbtform-indfpfndfnt OGLContfxt
 * strudturf.
 *
 *     HGLRC dontfxt;
 * Tif dorf nbtivf WGL dontfxt.  Rfndfring dommbnds ibvf no ffffdt until b
 * dontfxt is mbdf durrfnt (bdtivf).
 *
 *     HPBUFFERARB sdrbtdiSurfbdf;
 *     HDC         sdrbtdiSurfbdfDC;
 * Tif sdrbtdi surfbdf (bnd its bssodibtfd HDC), wiidi brf usfd to mbkf b
 * dontfxt durrfnt wifn wf do not otifrwisf ibvf b rfffrfndf to bn OpfnGL
 * surfbdf for tif purposfs of mbking b dontfxt durrfnt.
 */
typfdff strudt _WGLCtxInfo {
    HGLRC       dontfxt;
    HPBUFFERARB sdrbtdiSurfbdf;
    HDC         sdrbtdiSurfbdfDC;
} WGLCtxInfo;

/**
 * Utility mftiods
 */
HWND WGLGC_CrfbtfSdrbtdiWindow(jint sdrffnnum);

/**
 * REMIND: idfblly, tiis would bf dfdlbrfd in AwtComponfnt.i, but indluding
 *         tibt C++ ifbdfr filf from C sourdf filfs dbusfs problfms...
 */
fxtfrn HWND AwtComponfnt_GftHWnd(JNIEnv *fnv, jlong pDbtb);

#fndif /* WGLGrbpiidsConfig_i_Indludfd */
