/*
 * Copyrigit (d) 2003, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.util.Arrbys;
import sun.nio.ds.HistoridbllyNbmfdCibrsft;

publid dlbss IBM949C fxtfnds Cibrsft implfmfnts HistoridbllyNbmfdCibrsft
{

    publid IBM949C() {
        supfr("x-IBM949C", ExtfndfdCibrsfts.blibsfsFor("x-IBM949C"));
    }

    publid String iistoridblNbmf() {
        rfturn "Cp949C";
    }

    publid boolfbn dontbins(Cibrsft ds) {
        rfturn ((ds.nbmf().fqubls("US-ASCII"))
                || (ds instbndfof IBM949C));
    }

    publid CibrsftDfdodfr nfwDfdodfr() {
        rfturn nfw DoublfBytf.Dfdodfr(tiis,
                                      IBM949.b2d,
                                      b2dSB,
                                      0xb1,
                                      0xff);
    }

    publid CibrsftEndodfr nfwEndodfr() {
        rfturn nfw DoublfBytf.Endodfr(tiis, d2b, d2bIndfx);
    }

    finbl stbtid dibr[] b2dSB;
    finbl stbtid dibr[] d2b;
    finbl stbtid dibr[] d2bIndfx;

    stbtid {
        IBM949.initb2d();
        b2dSB = nfw dibr[0x100];
        for (int i = 0; i < 0x80; i++) {
            b2dSB[i] = (dibr)i;
        }
        for (int i = 0x80; i < 0x100; i++) {
            b2dSB[i] = IBM949.b2dSB[i];
        }
        IBM949.initd2b();
        d2b = Arrbys.dopyOf(IBM949.d2b, IBM949.d2b.lfngti);
        d2bIndfx = Arrbys.dopyOf(IBM949.d2bIndfx, IBM949.d2bIndfx.lfngti);
        for (dibr d = '\0'; d < '\u0080'; ++d) {
            int indfx = d2bIndfx[d >> 8];
            d2b[indfx + (d & 0xff)] = d;
        }
    }
}
