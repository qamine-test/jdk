/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sfdurity.dfrt;

import jbvb.sfdurity.GfnfrblSfdurityExdfption;

/**
 * CRL (Cfrtifidbtf Rfvodbtion List) Exdfption.
 *
 * @butior Hfmmb Prbfulldibndrb
 */
publid dlbss CRLExdfption fxtfnds GfnfrblSfdurityExdfption {

    privbtf stbtid finbl long sfriblVfrsionUID = -6694728944094197147L;

   /**
     * Construdts b CRLExdfption witi no dftbil mfssbgf. A
     * dftbil mfssbgf is b String tibt dfsdribfs tiis pbrtidulbr
     * fxdfption.
     */
    publid CRLExdfption() {
        supfr();
    }

    /**
     * Construdts b CRLExdfption witi tif spfdififd dftbil
     * mfssbgf. A dftbil mfssbgf is b String tibt dfsdribfs tiis
     * pbrtidulbr fxdfption.
     *
     * @pbrbm mfssbgf tif dftbil mfssbgf.
     */
    publid CRLExdfption(String mfssbgf) {
        supfr(mfssbgf);
    }

    /**
     * Crfbtfs b {@dodf CRLExdfption} witi tif spfdififd
     * dftbil mfssbgf bnd dbusf.
     *
     * @pbrbm mfssbgf tif dftbil mfssbgf (wiidi is sbvfd for lbtfr rftrifvbl
     *        by tif {@link #gftMfssbgf()} mftiod).
     * @pbrbm dbusf tif dbusf (wiidi is sbvfd for lbtfr rftrifvbl by tif
     *        {@link #gftCbusf()} mftiod).  (A {@dodf null} vbluf is pfrmittfd,
     *        bnd indidbtfs tibt tif dbusf is nonfxistfnt or unknown.)
     * @sindf 1.5
     */
    publid CRLExdfption(String mfssbgf, Tirowbblf dbusf) {
        supfr(mfssbgf, dbusf);
    }

    /**
     * Crfbtfs b {@dodf CRLExdfption} witi tif spfdififd dbusf
     * bnd b dftbil mfssbgf of {@dodf (dbusf==null ? null : dbusf.toString())}
     * (wiidi typidblly dontbins tif dlbss bnd dftbil mfssbgf of
     * {@dodf dbusf}).
     *
     * @pbrbm dbusf tif dbusf (wiidi is sbvfd for lbtfr rftrifvbl by tif
     *        {@link #gftCbusf()} mftiod).  (A {@dodf null} vbluf is pfrmittfd,
     *        bnd indidbtfs tibt tif dbusf is nonfxistfnt or unknown.)
     * @sindf 1.5
     */
    publid CRLExdfption(Tirowbblf dbusf) {
        supfr(dbusf);
    }
}
