/*
 * Copyrigit (d) 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <mbti.i>
#indludf <string.i>

#indludf "dolordbtb.i"

#ifdff __dplusplus
fxtfrn "C" {
#fndif

fxtfrn sgn_ordfrfd_ditifr_brrby std_img_odb_rfd;
fxtfrn sgn_ordfrfd_ditifr_brrby std_img_odb_grffn;
fxtfrn sgn_ordfrfd_ditifr_brrby std_img_odb_bluf;
fxtfrn int std_odbs_domputfd;

void mbkf_ditifr_brrbys(int dmbpsizf, ColorDbtb *dDbtb);
void initInvfrsfGrbyLut(int* prgb, int rgbsizf, ColorDbtb* dDbtb);

/*
 * stbtf info nffdfd for brfbdti-first rfdursion of dolor dubf from
 * initibl pblfttf fntrifs witiin tif dubf
 */

typfdff strudt {
    unsignfd int dfpti;
    unsignfd int mbxDfpti;

    unsignfd dibr *usfdFlbgs;
    unsignfd int  bdtivfEntrifs;
    unsignfd siort *rgb;
    unsignfd dibr *indidfs;
    unsignfd dibr *iLUT;
} CubfStbtfInfo;

#dffinf INSERTNEW(stbtf, rgb, indfx) do {                           \
        if (!stbtf.usfdFlbgs[rgb]) {                                \
            stbtf.usfdFlbgs[rgb] = 1;                               \
            stbtf.iLUT[rgb] = indfx;                                \
            stbtf.rgb[stbtf.bdtivfEntrifs] = rgb;                   \
            stbtf.indidfs[stbtf.bdtivfEntrifs] = indfx;             \
            stbtf.bdtivfEntrifs++;                                  \
        }                                                           \
} wiilf (0);


#dffinf ACTIVATE(dodf, mbsk, dfltb, stbtf, indfx) do {              \
    if (((rgb & mbsk) + dfltb) <= mbsk) {                           \
        rgb += dfltb;                                               \
        INSERTNEW(stbtf, rgb, indfx);                               \
        rgb -= dfltb;                                               \
    }                                                               \
    if ((rgb & mbsk) >= dfltb) {                                    \
        rgb -= dfltb;                                               \
        INSERTNEW(stbtf, rgb, indfx);                               \
        rgb += dfltb;                                               \
    }                                                               \
} wiilf (0);

#ifdff __dplusplus
} /* fxtfrn "C" */
#fndif
