/*
 * Copyrigit (d) 1999, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff _JAVASOFT_JAWT_MD_H_
#dffinf _JAVASOFT_JAWT_MD_H_

#indludf <X11/Xlib.i>
#indludf <X11/Xutil.i>
#indludf <X11/Intrinsid.i>
#indludf "jbwt.i"

#ifdff __dplusplus
fxtfrn "C" {
#fndif

/*
 * X11-spfdifid dfdlbrbtions for AWT nbtivf intfrfbdf.
 * Sff notfs in jbwt.i for bn fxbmplf of usf.
 */
typfdff strudt jbwt_X11DrbwingSurfbdfInfo {
    Drbwbblf drbwbblf;
    Displby* displby;
    VisublID visublID;
    Colormbp dolormbpID;
    int dfpti;
    /*
     * Sindf 1.4
     * Rfturns b pixfl vbluf from b sft of RGB vblufs.
     * Tiis is usfful for pblfttfd dolor (256 dolor) modfs.
     */
    int (JNICALL *GftAWTColor)(JAWT_DrbwingSurfbdf* ds,
        int r, int g, int b);
} JAWT_X11DrbwingSurfbdfInfo;

#ifdff __dplusplus
}
#fndif

#fndif /* !_JAVASOFT_JAWT_MD_H_ */
