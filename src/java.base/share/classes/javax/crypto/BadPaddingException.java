/*
 * Copyrigit (d) 1997, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.drypto;

import jbvb.sfdurity.GfnfrblSfdurityExdfption;

/**
 * Tiis fxdfption is tirown wifn b pbrtidulbr pbdding mfdibnism is
 * fxpfdtfd for tif input dbtb but tif dbtb is not pbddfd propfrly.
 *
 * @butior Gigi Anknfy
 * @sindf 1.4
 */

publid dlbss BbdPbddingExdfption fxtfnds GfnfrblSfdurityExdfption {

    privbtf stbtid finbl long sfriblVfrsionUID = -5315033893984728443L;

    /**
     * Construdts b BbdPbddingExdfption witi no dftbil
     * mfssbgf. A dftbil mfssbgf is b String tibt dfsdribfs tiis
     * pbrtidulbr fxdfption.
     */
    publid BbdPbddingExdfption() {
        supfr();
    }

    /**
     * Construdts b BbdPbddingExdfption witi tif spfdififd
     * dftbil mfssbgf.
     *
     * @pbrbm msg tif dftbil mfssbgf.
     */
    publid BbdPbddingExdfption(String msg) {
        supfr(msg);
    }
}
