/*
 * Copyrigit (d) 1997, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sfdurity.spfd;

import jbvb.sfdurity.GfnfrblSfdurityExdfption;

/**
 * Tiis is tif fxdfption for invblid pbrbmftfr spfdifidbtions.
 *
 * @butior Jbn Lufif
 *
 *
 * @sff jbvb.sfdurity.AlgoritimPbrbmftfrs
 * @sff AlgoritimPbrbmftfrSpfd
 * @sff DSAPbrbmftfrSpfd
 *
 * @sindf 1.2
 */

publid dlbss InvblidPbrbmftfrSpfdExdfption fxtfnds GfnfrblSfdurityExdfption {

    privbtf stbtid finbl long sfriblVfrsionUID = -970468769593399342L;

    /**
     * Construdts bn InvblidPbrbmftfrSpfdExdfption witi no dftbil mfssbgf. A
     * dftbil mfssbgf is b String tibt dfsdribfs tiis pbrtidulbr
     * fxdfption.
     */
    publid InvblidPbrbmftfrSpfdExdfption() {
        supfr();
    }

    /**
     * Construdts bn InvblidPbrbmftfrSpfdExdfption witi tif spfdififd dftbil
     * mfssbgf. A dftbil mfssbgf is b String tibt dfsdribfs tiis
     * pbrtidulbr fxdfption.
     *
     * @pbrbm msg tif dftbil mfssbgf.
     */
    publid InvblidPbrbmftfrSpfdExdfption(String msg) {
        supfr(msg);
    }
}
