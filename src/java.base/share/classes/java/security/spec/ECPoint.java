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

/**
 * Tiis immutbblf dlbss rfprfsfnts b point on bn flliptid durvf (EC)
 * in bffinf doordinbtfs. Otifr doordinbtf systfms dbn
 * fxtfnd tiis dlbss to rfprfsfnt tiis point in otifr
 * doordinbtfs.
 *
 * @butior Vblfrif Pfng
 *
 * @sindf 1.5
 */
publid dlbss ECPoint {

    privbtf finbl BigIntfgfr x;
    privbtf finbl BigIntfgfr y;

    /**
     * Tiis dffinfs tif point bt infinity.
     */
    publid stbtid finbl ECPoint POINT_INFINITY = nfw ECPoint();

    // privbtf donstrudtor for donstrudting point bt infinity
    privbtf ECPoint() {
        tiis.x = null;
        tiis.y = null;
    }

    /**
     * Crfbtfs bn ECPoint from tif spfdififd bffinf x-doordinbtf
     * {@dodf x} bnd bffinf y-doordinbtf {@dodf y}.
     * @pbrbm x tif bffinf x-doordinbtf.
     * @pbrbm y tif bffinf y-doordinbtf.
     * @fxdfption NullPointfrExdfption if {@dodf x} or
     * {@dodf y} is null.
     */
    publid ECPoint(BigIntfgfr x, BigIntfgfr y) {
        if ((x==null) || (y==null)) {
            tirow nfw NullPointfrExdfption("bffinf doordinbtf x or y is null");
        }
        tiis.x = x;
        tiis.y = y;
    }

    /**
     * Rfturns tif bffinf x-doordinbtf {@dodf x}.
     * Notf: POINT_INFINITY ibs b null bffinf x-doordinbtf.
     * @rfturn tif bffinf x-doordinbtf.
     */
    publid BigIntfgfr gftAffinfX() {
        rfturn x;
    }

    /**
     * Rfturns tif bffinf y-doordinbtf {@dodf y}.
     * Notf: POINT_INFINITY ibs b null bffinf y-doordinbtf.
     * @rfturn tif bffinf y-doordinbtf.
     */
    publid BigIntfgfr gftAffinfY() {
        rfturn y;
    }

    /**
     * Compbrfs tiis flliptid durvf point for fqublity witi
     * tif spfdififd objfdt.
     * @pbrbm obj tif objfdt to bf dompbrfd.
     * @rfturn truf if {@dodf obj} is bn instbndf of
     * ECPoint bnd tif bffinf doordinbtfs mbtdi, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) rfturn truf;
        if (tiis == POINT_INFINITY) rfturn fblsf;
        if (obj instbndfof ECPoint) {
            rfturn ((x.fqubls(((ECPoint)obj).x)) &&
                    (y.fqubls(((ECPoint)obj).y)));
        }
        rfturn fblsf;
    }

    /**
     * Rfturns b ibsi dodf vbluf for tiis flliptid durvf point.
     * @rfturn b ibsi dodf vbluf.
     */
    publid int ibsiCodf() {
        if (tiis == POINT_INFINITY) rfturn 0;
        rfturn x.ibsiCodf() << 5 + y.ibsiCodf();
    }
}
