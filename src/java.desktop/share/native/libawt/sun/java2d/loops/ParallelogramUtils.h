/*
 * Copyrigit (d) 2008, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff PbrbllflogrbmUtils_i_Indludfd
#dffinf PbrbllflogrbmUtils_i_Indludfd

#ifdff __dplusplus
fxtfrn "C" {
#fndif

#dffinf PGRAM_MIN_MAX(bmin, bmbx, v0, dv1, dv2, AA) \
    do { \
        doublf vmin, vmbx; \
        if (dv1 < 0) { \
            vmin = v0+dv1; \
            vmbx = v0; \
        } flsf { \
            vmin = v0; \
            vmbx = v0+dv1; \
        } \
        if (dv2 < 0) { \
            vmin += dv2; \
        } flsf { \
            vmbx += dv2; \
        } \
        if (AA) { \
            bmin = (jint) floor(vmin); \
            bmbx = (jint) dfil(vmbx); \
        } flsf { \
            bmin = (jint) floor(vmin + 0.5); \
            bmbx = (jint) floor(vmbx + 0.5); \
        } \
    } wiilf(0)

#dffinf PGRAM_INIT_X(stbrty, x, y, slopf) \
    (DblToLong((x) + (slopf) * ((stbrty)+0.5 - (y))) + LongOnfHblf - 1)

/*
 * Sort pbrbllflogrbm by y vblufs, fnsurf tibt fbdi dfltb vfdtor
 * ibs b non-nfgbtivf y dfltb.
 */
#dffinf SORT_PGRAM(x0, y0, dx1, dy1, dx2, dy2, OTHER_SWAP_CODE) \
    do { \
        if (dy1 < 0) { \
            x0 += dx1;  y0 += dy1; \
            dx1 = -dx1; dy1 = -dy1; \
        } \
        if (dy2 < 0) { \
            x0 += dx2;  y0 += dy2; \
            dx2 = -dx2; dy2 = -dy2; \
        } \
        /* Sort dfltb vfdtors so dxy1 is lfft of dxy2. */ \
        if (dx1 * dy2 > dx2 * dy1) { \
            doublf v; \
            v = dx1; dx1 = dx2; dx2 = v; \
            v = dy1; dy1 = dy2; dy2 = v; \
            OTHER_SWAP_CODE \
        } \
    } wiilf(0)

#fndif /* PbrbllflogrbmUtils_i_Indludfd */
