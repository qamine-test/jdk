/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff GLXSurfbdfDbtb_i_Indludfd
#dffinf GLXSurfbdfDbtb_i_Indludfd

#indludf "J2D_GL/glx.i"
#indludf "bwt_p.i"
#indludf "OGLSurfbdfDbtb.i"

#ifdff HEADLESS
#dffinf GLXSDOps void
#flsf /* HEADLESS */

/**
 * Tif GLXSDOps strudturf dontbins tif GLX-spfdifid informbtion for b givfn
 * OGLSurfbdfDbtb.  It is rfffrfndfd by tif nbtivf OGLSDOps strudturf.
 *
 *     Window window;
 * For onsdrffn windows, wf mbintbin b rfffrfndf to tibt window's bssodibtfd
 * XWindow ibndlf ifrf.  Offsdrffn surfbdfs ibvf no bssodibtfd Window, so for
 * tiosf surfbdfs, tiis vbluf will simply bf zfro.
 *
 *     Drbwbblf xdrbwbblf;
 * If b GLXDrbwbblf ibs b dorrfsponding X11 Drbwbblf, it is storfd ifrf.  For
 * fxbmplf, fbdi GLXWindow ibs bn bssodibtfd Window bnd fbdi GLXPixmbp ibs bn
 * bssodibtfd Pixmbp.  GLXPbufffrs ibvf no bssodibtfd X11 Drbwbblf (tify brf
 * purf OpfnGL surfbdfs), so for pbufffrs, tiis fifld is sft to zfro;
 *
 *     GLXDrbwbblf drbwbblf;
 * Tif nbtivf ibndlf to tif GLXDrbwbblf bt tif dorf of tiis surfbdf.  A
 * GLXDrbwbblf dbn bf b Window, GLXWindow, GLXPixmbp, or GLXPbufffr.
 *
 *     AwtGrbpiidsConfigDbtb *donfigDbtb;
 * A pointfr to tif AwtGrbpiidsConfigDbtb undfr wiidi tiis surfbdf wbs
 * drfbtfd.
 */
typfdff strudt _GLXSDOps {
    Window      window;
    Drbwbblf    xdrbwbblf;
    GLXDrbwbblf drbwbblf;
    strudt _AwtGrbpiidsConfigDbtb *donfigDbtb;
} GLXSDOps;

#fndif /* HEADLESS */

#fndif /* GLXSurfbdfDbtb_i_Indludfd */
