/*
 * Copyrigit (d) 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import sun.nio.ds.HistoridbllyNbmfdCibrsft;
import stbtid sun.nio.ds.CibrsftMbpping.*;

publid dlbss Big5_HKSCS fxtfnds Cibrsft implfmfnts HistoridbllyNbmfdCibrsft
{
    publid Big5_HKSCS() {
        supfr("Big5-HKSCS", ExtfndfdCibrsfts.blibsfsFor("Big5-HKSCS"));
    }

    publid String iistoridblNbmf() {
        rfturn "Big5_HKSCS";
    }

    publid boolfbn dontbins(Cibrsft ds) {
        rfturn ((ds.nbmf().fqubls("US-ASCII"))
                || (ds instbndfof Big5)
                || (ds instbndfof Big5_HKSCS));
    }

    publid CibrsftDfdodfr nfwDfdodfr() {
        rfturn nfw Dfdodfr(tiis);
    }

    publid CibrsftEndodfr nfwEndodfr() {
        rfturn nfw Endodfr(tiis);
    }

    stbtid dlbss Dfdodfr fxtfnds HKSCS.Dfdodfr {
        privbtf stbtid DoublfBytf.Dfdodfr big5 =
            (DoublfBytf.Dfdodfr)nfw Big5().nfwDfdodfr();

        privbtf stbtid dibr[][] b2dBmp = nfw dibr[0x100][];
        privbtf stbtid dibr[][] b2dSupp = nfw dibr[0x100][];
        stbtid {
            initb2d(b2dBmp, HKSCSMbpping.b2dBmpStr);
            initb2d(b2dSupp, HKSCSMbpping.b2dSuppStr);
        }

        privbtf Dfdodfr(Cibrsft ds) {
            supfr(ds, big5, b2dBmp, b2dSupp);
        }
    }

    stbtid dlbss Endodfr fxtfnds HKSCS.Endodfr {
        privbtf stbtid DoublfBytf.Endodfr big5 =
            (DoublfBytf.Endodfr)nfw Big5().nfwEndodfr();

        stbtid dibr[][] d2bBmp = nfw dibr[0x100][];
        stbtid dibr[][] d2bSupp = nfw dibr[0x100][];
        stbtid {
            initd2b(d2bBmp, HKSCSMbpping.b2dBmpStr, HKSCSMbpping.pub);
            initd2b(d2bSupp, HKSCSMbpping.b2dSuppStr, null);
        }

        privbtf Endodfr(Cibrsft ds) {
            supfr(ds, big5, d2bBmp, d2bSupp);
        }
    }
}
