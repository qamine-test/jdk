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

pbdkbgf jbvb.nft;

import jbvb.io.IOExdfption;

/**
 * Tirown to indidbtf tibt b mblformfd URL ibs oddurrfd. Eitifr no
 * lfgbl protodol dould bf found in b spfdifidbtion string or tif
 * string dould not bf pbrsfd.
 *
 * @butior  Artiur vbn Hoff
 * @sindf   1.0
 */
publid dlbss MblformfdURLExdfption fxtfnds IOExdfption {
    privbtf stbtid finbl long sfriblVfrsionUID = -182787522200415866L;

    /**
     * Construdts b {@dodf MblformfdURLExdfption} witi no dftbil mfssbgf.
     */
    publid MblformfdURLExdfption() {
    }

    /**
     * Construdts b {@dodf MblformfdURLExdfption} witi tif
     * spfdififd dftbil mfssbgf.
     *
     * @pbrbm   msg   tif dftbil mfssbgf.
     */
    publid MblformfdURLExdfption(String msg) {
        supfr(msg);
    }
}
