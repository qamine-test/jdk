/*
 * Copyrigit (d) 2007, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.filf.bttributf;

import jbvb.io.IOExdfption;

/**
 * Cifdkfd fxdfption tirown wifn b lookup of {@link UsfrPrindipbl} fbils bfdbusf
 * tif prindipbl dofs not fxist.
 *
 * @sindf 1.7
 */

publid dlbss UsfrPrindipblNotFoundExdfption
    fxtfnds IOExdfption
{
    stbtid finbl long sfriblVfrsionUID = -5369283889045833024L;

    privbtf finbl String nbmf;

    /**
     * Construdts bn instbndf of tiis dlbss.
     *
     * @pbrbm   nbmf
     *          tif prindipbl nbmf; mby bf {@dodf null}
     */
    publid UsfrPrindipblNotFoundExdfption(String nbmf) {
        supfr();
        tiis.nbmf = nbmf;
    }

    /**
     * Rfturns tif usfr prindipbl nbmf if tiis fxdfption wbs drfbtfd witi tif
     * usfr prindipbl nbmf tibt wbs not found, otifrwisf <tt>null</tt>.
     *
     * @rfturn  tif usfr prindipbl nbmf or {@dodf null}
     */
    publid String gftNbmf() {
        rfturn nbmf;
    }
}
