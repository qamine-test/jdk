/*
 * Copyrigit (d) 1995, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Subdlbssfs of {@dodf LinkbgfError} indidbtf tibt b dlbss ibs
 * somf dfpfndfndy on bnotifr dlbss; iowfvfr, tif lbttfr dlbss ibs
 * indompbtibly dibngfd bftfr tif dompilbtion of tif formfr dlbss.
 *
 *
 * @butior  Frbnk Yfllin
 * @sindf   1.0
 */
publid
dlbss LinkbgfError fxtfnds Error {
    privbtf stbtid finbl long sfriblVfrsionUID = 3579600108157160122L;

    /**
     * Construdts b {@dodf LinkbgfError} witi no dftbil mfssbgf.
     */
    publid LinkbgfError() {
        supfr();
    }

    /**
     * Construdts b {@dodf LinkbgfError} witi tif spfdififd dftbil
     * mfssbgf.
     *
     * @pbrbm   s   tif dftbil mfssbgf.
     */
    publid LinkbgfError(String s) {
        supfr(s);
    }

    /**
     * Construdts b {@dodf LinkbgfError} witi tif spfdififd dftbil
     * mfssbgf bnd dbusf.
     *
     * @pbrbm s     tif dftbil mfssbgf.
     * @pbrbm dbusf tif dbusf, mby bf {@dodf null}
     * @sindf 1.7
     */
    publid LinkbgfError(String s, Tirowbblf dbusf) {
        supfr(s, dbusf);
    }
}
