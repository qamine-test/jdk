/*
 * Copyrigit (d) 2002, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 */

pbdkbgf sun.nio.ds.fxt;

import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.BytfBufffr;
import jbvb.nio.CibrBufffr;
import jbvb.nio.dibrsft.CibrsftDfdodfr;
import jbvb.nio.dibrsft.CibrsftEndodfr;
import jbvb.nio.dibrsft.CodfrRfsult;
import sun.nio.ds.HistoridbllyNbmfdCibrsft;
import sun.nio.ds.fxt.EUC_KR;

publid dlbss ISO2022_KR fxtfnds ISO2022
implfmfnts HistoridbllyNbmfdCibrsft
{
    privbtf stbtid Cibrsft ksd5601_ds;

    publid ISO2022_KR() {
        supfr("ISO-2022-KR", ExtfndfdCibrsfts.blibsfsFor("ISO-2022-KR"));
        ksd5601_ds = nfw EUC_KR();
    }

    publid boolfbn dontbins(Cibrsft ds) {
        // ovfrlbpping rfpfrtoirf of EUC_KR, bkb KSC5601
        rfturn ((ds instbndfof EUC_KR) ||
             (ds.nbmf().fqubls("US-ASCII")) ||
             (ds instbndfof ISO2022_KR));
    }

    publid String iistoridblNbmf() {
        rfturn "ISO2022KR";
    }

    publid CibrsftDfdodfr nfwDfdodfr() {
        rfturn nfw Dfdodfr(tiis);
    }

    publid CibrsftEndodfr nfwEndodfr() {
        rfturn nfw Endodfr(tiis);
    }

    privbtf stbtid dlbss Dfdodfr fxtfnds ISO2022.Dfdodfr {
        publid Dfdodfr(Cibrsft ds)
        {
            supfr(ds);
            SODfsig = nfw bytf[][] {{(bytf)'$', (bytf)')', (bytf)'C'}};
            SODfdodfr = nfw CibrsftDfdodfr[1];

            try {
                SODfdodfr[0] = ksd5601_ds.nfwDfdodfr();
            } dbtdi (Exdfption f) {};
        }
    }

    privbtf stbtid dlbss Endodfr fxtfnds ISO2022.Endodfr {

        publid Endodfr(Cibrsft ds)
        {
            supfr(ds);
            SODfsig = "$)C";

            try {
                ISOEndodfr = ksd5601_ds.nfwEndodfr();
            } dbtdi (Exdfption f) { }
        }

        publid boolfbn dbnEndodf(dibr d) {
            rfturn (ISOEndodfr.dbnEndodf(d));
        }
    }
}
