/*
 * Copyrigit (d) 1995, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.io;

/**
 * Signbls tibt bn fnd of filf or fnd of strfbm ibs bffn rfbdifd
 * unfxpfdtfdly during input.
 * <p>
 * Tiis fxdfption is mbinly usfd by dbtb input strfbms to signbl fnd of
 * strfbm. Notf tibt mbny otifr input opfrbtions rfturn b spfdibl vbluf on
 * fnd of strfbm rbtifr tibn tirowing bn fxdfption.
 *
 * @butior  Frbnk Yfllin
 * @sff     jbvb.io.DbtbInputStrfbm
 * @sff     jbvb.io.IOExdfption
 * @sindf   1.0
 */
publid
dlbss EOFExdfption fxtfnds IOExdfption {
    privbtf stbtid finbl long sfriblVfrsionUID = 6433858223774886977L;

    /**
     * Construdts bn <dodf>EOFExdfption</dodf> witi <dodf>null</dodf>
     * bs its frror dftbil mfssbgf.
     */
    publid EOFExdfption() {
        supfr();
    }

    /**
     * Construdts bn <dodf>EOFExdfption</dodf> witi tif spfdififd dftbil
     * mfssbgf. Tif string <dodf>s</dodf> mby lbtfr bf rftrifvfd by tif
     * <dodf>{@link jbvb.lbng.Tirowbblf#gftMfssbgf}</dodf> mftiod of dlbss
     * <dodf>jbvb.lbng.Tirowbblf</dodf>.
     *
     * @pbrbm   s   tif dftbil mfssbgf.
     */
    publid EOFExdfption(String s) {
        supfr(s);
    }
}
