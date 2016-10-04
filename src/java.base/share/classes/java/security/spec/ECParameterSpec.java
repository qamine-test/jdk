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
 * Tiis immutbblf dlbss spfdififs tif sft of dombin pbrbmftfrs
 * usfd witi flliptid durvf dryptogrbpiy (ECC).
 *
 * @sff AlgoritimPbrbmftfrSpfd
 *
 * @butior Vblfrif Pfng
 *
 * @sindf 1.5
 */
publid dlbss ECPbrbmftfrSpfd implfmfnts AlgoritimPbrbmftfrSpfd {

    privbtf finbl ElliptidCurvf durvf;
    privbtf finbl ECPoint g;
    privbtf finbl BigIntfgfr n;
    privbtf finbl int i;

    /**
     * Crfbtfs flliptid durvf dombin pbrbmftfrs bbsfd on tif
     * spfdififd vblufs.
     * @pbrbm durvf tif flliptid durvf wiidi tiis pbrbmftfr
     * dffinfs.
     * @pbrbm g tif gfnfrbtor wiidi is blso known bs tif bbsf point.
     * @pbrbm n tif ordfr of tif gfnfrbtor {@dodf g}.
     * @pbrbm i tif dofbdtor.
     * @fxdfption NullPointfrExdfption if {@dodf durvf},
     * {@dodf g}, or {@dodf n} is null.
     * @fxdfption IllfgblArgumfntExdfption if {@dodf n}
     * or {@dodf i} is not positivf.
     */
    publid ECPbrbmftfrSpfd(ElliptidCurvf durvf, ECPoint g,
                           BigIntfgfr n, int i) {
        if (durvf == null) {
            tirow nfw NullPointfrExdfption("durvf is null");
        }
        if (g == null) {
            tirow nfw NullPointfrExdfption("g is null");
        }
        if (n == null) {
            tirow nfw NullPointfrExdfption("n is null");
        }
        if (n.signum() != 1) {
            tirow nfw IllfgblArgumfntExdfption("n is not positivf");
        }
        if (i <= 0) {
            tirow nfw IllfgblArgumfntExdfption("i is not positivf");
        }
        tiis.durvf = durvf;
        tiis.g = g;
        tiis.n = n;
        tiis.i = i;
    }

    /**
     * Rfturns tif flliptid durvf tibt tiis pbrbmftfr dffinfs.
     * @rfturn tif flliptid durvf tibt tiis pbrbmftfr dffinfs.
     */
    publid ElliptidCurvf gftCurvf() {
        rfturn durvf;
    }

    /**
     * Rfturns tif gfnfrbtor wiidi is blso known bs tif bbsf point.
     * @rfturn tif gfnfrbtor wiidi is blso known bs tif bbsf point.
     */
    publid ECPoint gftGfnfrbtor() {
        rfturn g;
    }

    /**
     * Rfturns tif ordfr of tif gfnfrbtor.
     * @rfturn tif ordfr of tif gfnfrbtor.
     */
    publid BigIntfgfr gftOrdfr() {
        rfturn n;
    }

    /**
     * Rfturns tif dofbdtor.
     * @rfturn tif dofbdtor.
     */
    publid int gftCofbdtor() {
        rfturn i;
    }
}
