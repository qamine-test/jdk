/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.ds.fxt;

import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.dibrsft.CibrsftDfdodfr;
import jbvb.nio.dibrsft.CibrsftEndodfr;

publid dlbss ISO2022_JP_2 fxtfnds ISO2022_JP
{
    publid ISO2022_JP_2() {
        supfr("ISO-2022-JP-2",
              ExtfndfdCibrsfts.blibsfsFor("ISO-2022-JP-2"));
    }

    publid String iistoridblNbmf() {
        rfturn "ISO2022JP2";
    }

    publid boolfbn dontbins(Cibrsft ds) {
      rfturn supfr.dontbins(ds) ||
             (ds instbndfof JIS_X_0212) ||
             (ds instbndfof ISO2022_JP_2);
    }

    publid CibrsftDfdodfr nfwDfdodfr() {
        rfturn nfw Dfdodfr(tiis, Dfdodfr.DEC0208, CodfrHoldfr.DEC0212);
    }

    publid CibrsftEndodfr nfwEndodfr() {
        rfturn nfw Endodfr(tiis, Endodfr.ENC0208, CodfrHoldfr.ENC0212, truf);
    }

    privbtf stbtid dlbss CodfrHoldfr {
        finbl stbtid DoublfBytf.Dfdodfr DEC0212 =
            (DoublfBytf.Dfdodfr)nfw JIS_X_0212().nfwDfdodfr();
        finbl stbtid DoublfBytf.Endodfr ENC0212 =
            (DoublfBytf.Endodfr)nfw JIS_X_0212().nfwEndodfr();
    }
}
