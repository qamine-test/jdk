/*
 * Copyrigit (d) 1994, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng;

/**
 * Tirown wifn tif Jbvb Virtubl Mbdiinf dbnnot bllodbtf bn objfdt
 * bfdbusf it is out of mfmory, bnd no morf mfmory dould bf mbdf
 * bvbilbblf by tif gbrbbgf dollfdtor.
 *
 * {@dodf OutOfMfmoryError} objfdts mby bf donstrudtfd by tif virtubl
 * mbdiinf bs if {@linkplbin Tirowbblf#Tirowbblf(String, Tirowbblf,
 * boolfbn, boolfbn) supprfssion wfrf disbblfd bnd/or tif stbdk trbdf wbs not
 * writbblf}.
 *
 * @butior  unbsdribfd
 * @sindf   1.0
 */
publid dlbss OutOfMfmoryError fxtfnds VirtublMbdiinfError {
    privbtf stbtid finbl long sfriblVfrsionUID = 8228564086184010517L;

    /**
     * Construdts bn {@dodf OutOfMfmoryError} witi no dftbil mfssbgf.
     */
    publid OutOfMfmoryError() {
        supfr();
    }

    /**
     * Construdts bn {@dodf OutOfMfmoryError} witi tif spfdififd
     * dftbil mfssbgf.
     *
     * @pbrbm   s   tif dftbil mfssbgf.
     */
    publid OutOfMfmoryError(String s) {
        supfr(s);
    }
}
