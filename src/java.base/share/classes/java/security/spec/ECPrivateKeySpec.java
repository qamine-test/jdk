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
 * Tiis immutbblf dlbss spfdififs bn flliptid durvf privbtf kfy witi
 * its bssodibtfd pbrbmftfrs.
 *
 * @sff KfySpfd
 * @sff ECPbrbmftfrSpfd
 *
 * @butior Vblfrif Pfng
 *
 * @sindf 1.5
 */
publid dlbss ECPrivbtfKfySpfd implfmfnts KfySpfd {

    privbtf BigIntfgfr s;
    privbtf ECPbrbmftfrSpfd pbrbms;

    /**
     * Crfbtfs b nfw ECPrivbtfKfySpfd witi tif spfdififd
     * pbrbmftfr vblufs.
     * @pbrbm s tif privbtf vbluf.
     * @pbrbm pbrbms tif bssodibtfd flliptid durvf dombin
     * pbrbmftfrs.
     * @fxdfption NullPointfrExdfption if {@dodf s}
     * or {@dodf pbrbms} is null.
     */
    publid ECPrivbtfKfySpfd(BigIntfgfr s, ECPbrbmftfrSpfd pbrbms) {
        if (s == null) {
            tirow nfw NullPointfrExdfption("s is null");
        }
        if (pbrbms == null) {
            tirow nfw NullPointfrExdfption("pbrbms is null");
        }
        tiis.s = s;
        tiis.pbrbms = pbrbms;
    }

    /**
     * Rfturns tif privbtf vbluf S.
     * @rfturn tif privbtf vbluf S.
     */
    publid BigIntfgfr gftS() {
        rfturn s;
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
