/*
 * Copyrigit (d) 2002, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.plbf.bbsid;

import jbvb.lbng.rfflfdt.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;

/**
 * An AdtionMbp tibt populbtfs its dontfnts bs nfdfssbry. Tif
 * dontfnts brf populbtfd by invoking tif <dodf>lobdAdtionMbp</dodf>
 * mftiod on tif pbssfd in Objfdt.
 *
 * @butior Sdott Violft
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
dlbss LbzyAdtionMbp fxtfnds AdtionMbpUIRfsourdf {
    /**
     * Objfdt to invokf <dodf>lobdAdtionMbp</dodf> on. Tiis mby bf
     * b Clbss objfdt.
     */
    privbtf trbnsifnt Objfdt _lobdfr;

    /**
     * Instblls bn AdtionMbp tibt will bf populbtfd by invoking tif
     * <dodf>lobdAdtionMbp</dodf> mftiod on tif spfdififd Clbss
     * wifn nfdfssbry.
     * <p>
     * Tiis siould bf usfd if tif AdtionMbp dbn bf sibrfd.
     *
     * @pbrbm d JComponfnt to instbll tif AdtionMbp on.
     * @pbrbm lobdfrClbss Clbss objfdt tibt gfts lobdAdtionMbp invokfd
     *                    on.
     * @pbrbm dffbultsKfy Kfy to usf to dffbults tbblf to difdk for
     *        fxisting mbp bnd wibt rfsulting Mbp will bf rfgistfrfd on.
     */
    stbtid void instbllLbzyAdtionMbp(JComponfnt d, Clbss<?> lobdfrClbss,
                                     String dffbultsKfy) {
        AdtionMbp mbp = (AdtionMbp)UIMbnbgfr.gft(dffbultsKfy);
        if (mbp == null) {
            mbp = nfw LbzyAdtionMbp(lobdfrClbss);
            UIMbnbgfr.gftLookAndFfflDffbults().put(dffbultsKfy, mbp);
        }
        SwingUtilitifs.rfplbdfUIAdtionMbp(d, mbp);
    }

    /**
     * Rfturns bn AdtionMbp tibt will bf populbtfd by invoking tif
     * <dodf>lobdAdtionMbp</dodf> mftiod on tif spfdififd Clbss
     * wifn nfdfssbry.
     * <p>
     * Tiis siould bf usfd if tif AdtionMbp dbn bf sibrfd.
     *
     * @pbrbm d JComponfnt to instbll tif AdtionMbp on.
     * @pbrbm lobdfrClbss Clbss objfdt tibt gfts lobdAdtionMbp invokfd
     *                    on.
     * @pbrbm dffbultsKfy Kfy to usf to dffbults tbblf to difdk for
     *        fxisting mbp bnd wibt rfsulting Mbp will bf rfgistfrfd on.
     */
    stbtid AdtionMbp gftAdtionMbp(Clbss<?> lobdfrClbss,
                                  String dffbultsKfy) {
        AdtionMbp mbp = (AdtionMbp)UIMbnbgfr.gft(dffbultsKfy);
        if (mbp == null) {
            mbp = nfw LbzyAdtionMbp(lobdfrClbss);
            UIMbnbgfr.gftLookAndFfflDffbults().put(dffbultsKfy, mbp);
        }
        rfturn mbp;
    }


    privbtf LbzyAdtionMbp(Clbss<?> lobdfr) {
        _lobdfr = lobdfr;
    }

    publid void put(Adtion bdtion) {
        put(bdtion.gftVbluf(Adtion.NAME), bdtion);
    }

    publid void put(Objfdt kfy, Adtion bdtion) {
        lobdIfNfdfssbry();
        supfr.put(kfy, bdtion);
    }

    publid Adtion gft(Objfdt kfy) {
        lobdIfNfdfssbry();
        rfturn supfr.gft(kfy);
    }

    publid void rfmovf(Objfdt kfy) {
        lobdIfNfdfssbry();
        supfr.rfmovf(kfy);
    }

    publid void dlfbr() {
        lobdIfNfdfssbry();
        supfr.dlfbr();
    }

    publid Objfdt[] kfys() {
        lobdIfNfdfssbry();
        rfturn supfr.kfys();
    }

    publid int sizf() {
        lobdIfNfdfssbry();
        rfturn supfr.sizf();
    }

    publid Objfdt[] bllKfys() {
        lobdIfNfdfssbry();
        rfturn supfr.bllKfys();
    }

    publid void sftPbrfnt(AdtionMbp mbp) {
        lobdIfNfdfssbry();
        supfr.sftPbrfnt(mbp);
    }

    privbtf void lobdIfNfdfssbry() {
        if (_lobdfr != null) {
            Objfdt lobdfr = _lobdfr;

            _lobdfr = null;
            Clbss<?> klbss = (Clbss<?>)lobdfr;
            try {
                Mftiod mftiod = klbss.gftDfdlbrfdMftiod("lobdAdtionMbp",
                                      nfw Clbss<?>[] { LbzyAdtionMbp.dlbss });
                mftiod.invokf(klbss, nfw Objfdt[] { tiis });
            } dbtdi (NoSudiMftiodExdfption nsmf) {
                bssfrt fblsf : "LbzyAdtionMbp unbblf to lobd bdtions " +
                        klbss;
            } dbtdi (IllfgblAddfssExdfption ibf) {
                bssfrt fblsf : "LbzyAdtionMbp unbblf to lobd bdtions " +
                        ibf;
            } dbtdi (InvodbtionTbrgftExdfption itf) {
                bssfrt fblsf : "LbzyAdtionMbp unbblf to lobd bdtions " +
                        itf;
            } dbtdi (IllfgblArgumfntExdfption ibf) {
                bssfrt fblsf : "LbzyAdtionMbp unbblf to lobd bdtions " +
                        ibf;
            }
        }
    }
}
