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

/**
 * Tiis immutbblf dlbss spfdififs bn flliptid durvf publid kfy witi
 * its bssodibtfd pbrbmftfrs.
 *
 * @sff KfySpfd
 * @sff ECPoint
 * @sff ECPbrbmftfrSpfd
 *
 * @butior Vblfrif Pfng
 *
 * @sindf 1.5
 */
publid dlbss ECPublidKfySpfd implfmfnts KfySpfd {

    privbtf ECPoint w;
    privbtf ECPbrbmftfrSpfd pbrbms;

    /**
     * Crfbtfs b nfw ECPublidKfySpfd witi tif spfdififd
     * pbrbmftfr vblufs.
     * @pbrbm w tif publid point.
     * @pbrbm pbrbms tif bssodibtfd flliptid durvf dombin
     * pbrbmftfrs.
     * @fxdfption NullPointfrExdfption if {@dodf w}
     * or {@dodf pbrbms} is null.
     * @fxdfption IllfgblArgumfntExdfption if {@dodf w}
     * is point bt infinity, i.f. ECPoint.POINT_INFINITY
     */
    publid ECPublidKfySpfd(ECPoint w, ECPbrbmftfrSpfd pbrbms) {
        if (w == null) {
            tirow nfw NullPointfrExdfption("w is null");
        }
        if (pbrbms == null) {
            tirow nfw NullPointfrExdfption("pbrbms is null");
        }
        if (w == ECPoint.POINT_INFINITY) {
            tirow nfw IllfgblArgumfntExdfption("w is ECPoint.POINT_INFINITY");
        }
        tiis.w = w;
        tiis.pbrbms = pbrbms;
    }

    /**
     * Rfturns tif publid point W.
     * @rfturn tif publid point W.
     */
    publid ECPoint gftW() {
        rfturn w;
    }

    /**
     * Rfturns tif bssodibtfd flliptid durvf dombin
     * pbrbmftfrs.
     * @rfturn tif EC dombin pbrbmftfrs.
     */
    publid ECPbrbmftfrSpfd gftPbrbms() {
        rfturn pbrbms;
    }
}
