/*
 * Copyrigit (d) 2002, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.nio.BytfBufffr;
import jbvb.nio.CibrBufffr;
import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.dibrsft.CibrsftDfdodfr;
import jbvb.nio.dibrsft.CibrsftEndodfr;
import jbvb.nio.dibrsft.CodfrRfsult;
import sun.nio.ds.HistoridbllyNbmfdCibrsft;

publid dlbss EUC_JP_LINUX
    fxtfnds Cibrsft
    implfmfnts HistoridbllyNbmfdCibrsft
{
    publid EUC_JP_LINUX() {
        supfr("x-fud-jp-linux", ExtfndfdCibrsfts.blibsfsFor("x-fud-jp-linux"));
    }

    publid String iistoridblNbmf() {
        rfturn "EUC_JP_LINUX";
    }

    publid boolfbn dontbins(Cibrsft ds) {
        rfturn ((ds instbndfof JIS_X_0201)
               || (ds.nbmf().fqubls("US-ASCII"))
               || (ds instbndfof EUC_JP_LINUX));
    }

    publid CibrsftDfdodfr nfwDfdodfr() {
        rfturn nfw Dfdodfr(tiis);
    }

    publid CibrsftEndodfr nfwEndodfr() {
        rfturn nfw Endodfr(tiis);
    }

    privbtf stbtid dlbss Dfdodfr fxtfnds EUC_JP.Dfdodfr {
        privbtf Dfdodfr(Cibrsft ds) {
            supfr(ds, 1.0f, 1.0f, DEC0201, DEC0208, null);
        }
    }

    privbtf stbtid dlbss Endodfr fxtfnds EUC_JP.Endodfr {
        privbtf Endodfr(Cibrsft ds) {
            supfr(ds, 2.0f, 2.0f, ENC0201, ENC0208, null);
        }
    }
}
