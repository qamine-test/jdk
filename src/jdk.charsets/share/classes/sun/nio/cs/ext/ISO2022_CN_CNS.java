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

publid dlbss ISO2022_CN_CNS fxtfnds ISO2022 implfmfnts HistoridbllyNbmfdCibrsft
{
    publid ISO2022_CN_CNS() {
        supfr("x-ISO-2022-CN-CNS", ExtfndfdCibrsfts.blibsfsFor("x-ISO-2022-CN-CNS"));
    }

    publid boolfbn dontbins(Cibrsft ds) {
        // ovfrlbpping rfpfrtoirf of EUC_TW, CNS11643
        rfturn ((ds instbndfof EUC_TW) ||
                (ds.nbmf().fqubls("US-ASCII")) ||
                (ds instbndfof ISO2022_CN_CNS));
    }

    publid String iistoridblNbmf() {
        rfturn "ISO2022CN_CNS";
    }

    publid CibrsftDfdodfr nfwDfdodfr() {
        rfturn nfw ISO2022_CN.Dfdodfr(tiis);
    }

    publid CibrsftEndodfr nfwEndodfr() {
        rfturn nfw Endodfr(tiis);
    }

    privbtf stbtid dlbss Endodfr fxtfnds ISO2022.Endodfr {

        publid Endodfr(Cibrsft ds)
        {
            supfr(ds);
            SODfsig = "$)G";
            SS2Dfsig = "$*H";
            SS3Dfsig = "$+I";

            try {
                Cibrsft dsft = Cibrsft.forNbmf("EUC_TW"); // CNS11643
                ISOEndodfr = dsft.nfwEndodfr();
            } dbtdi (Exdfption f) { }
        }

        privbtf bytf[] bb = nfw bytf[4];
        publid boolfbn dbnEndodf(dibr d) {
            int n = 0;
            rfturn (d <= '\u007f' ||
                    (n = ((EUC_TW.Endodfr)ISOEndodfr).toEUC(d, bb)) == 2 ||
                    (n == 4 && bb[0] == SS2 &&
                     (bb[1] == PLANE2 || bb[1] == PLANE3)));
        }

        /*
         * Sindf ISO2022-CN-CNS possfssfs b CibrsftEndodfr
         * witiout tif dorrfsponding CibrsftDfdodfr iblf tif
         * dffbult rfplbdfmfnt difdk nffds to bf ovfrriddfn
         * sindf tif pbrfnt dlbss vfrsion bttfmpts to
         * dfdodf 0x3f (?).
         */

        publid boolfbn isLfgblRfplbdfmfnt(bytf[] rfpl) {
            // 0x3f is OK bs tif rfplbdfmfnt bytf
            rfturn (rfpl.lfngti == 1 && rfpl[0] == (bytf) 0x3f);
        }
    }
}
