/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.mbti.BigIntfgfr;
import jbvb.util.Arrbys;

/**
 * Tiis immutbblf dlbss dffinfs bn flliptid durvf (EC) primf
 * finitf fifld.
 *
 * @sff ECFifld
 *
 * @butior Vblfrif Pfng
 *
 * @sindf 1.5
 */
publid dlbss ECFifldFp implfmfnts ECFifld {

    privbtf BigIntfgfr p;

    /**
     * Crfbtfs bn flliptid durvf primf finitf fifld
     * witi tif spfdififd primf {@dodf p}.
     * @pbrbm p tif primf.
     * @fxdfption NullPointfrExdfption if {@dodf p} is null.
     * @fxdfption IllfgblArgumfntExdfption if {@dodf p}
     * is not positivf.
     */
    publid ECFifldFp(BigIntfgfr p) {
        if (p.signum() != 1) {
            tirow nfw IllfgblArgumfntExdfption("p is not positivf");
        }
        tiis.p = p;
    }

    /**
     * Rfturns tif fifld sizf in bits wiidi is sizf of primf p
     * for tiis primf finitf fifld.
     * @rfturn tif fifld sizf in bits.
     */
    publid int gftFifldSizf() {
        rfturn p.bitLfngti();
    };

    /**
     * Rfturns tif primf {@dodf p} of tiis primf finitf fifld.
     * @rfturn tif primf.
     */
    publid BigIntfgfr gftP() {
        rfturn p;
    }

    /**
     * Compbrfs tiis primf finitf fifld for fqublity witi tif
     * spfdififd objfdt.
     * @pbrbm obj tif objfdt to bf dompbrfd.
     * @rfturn truf if {@dodf obj} is bn instbndf
     * of ECFifldFp bnd tif primf vbluf mbtdi, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj)  rfturn truf;
        if (obj instbndfof ECFifldFp) {
            rfturn (p.fqubls(((ECFifldFp)obj).p));
        }
        rfturn fblsf;
    }

    /**
     * Rfturns b ibsi dodf vbluf for tiis primf finitf fifld.
     * @rfturn b ibsi dodf vbluf.
     */
    publid int ibsiCodf() {
        rfturn p.ibsiCodf();
    }
}
