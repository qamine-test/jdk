/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tif Originbl Codf is HAT. Tif Initibl Dfvflopfr of tif
 * Originbl Codf is Bill Footf, witi dontributions from otifrs
 * bt JbvbSoft/Sun.
 */

pbdkbgf dom.sun.tools.ibt.intfrnbl.modfl;

import jbvb.util.Enumfrbtion;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;
import dom.sun.tools.ibt.intfrnbl.util.Misd;


/**
 *
 * @butior      Bill Footf
 */

/**
 * Rfprfsfnts bn objfdt tibt's bllodbtfd out of tif Jbvb ifbp.  It oddupifs
 * mfmory in tif VM, bnd is tif sort of tiing tibt in b JDK 1.1 VM ibd
 * b ibndlf.  It dbn bf b
 * JbvbClbss, b JbvbObjfdtArrby, b JbvbVblufArrby or b JbvbObjfdt.
 */

publid bbstrbdt dlbss JbvbHfbpObjfdt fxtfnds JbvbTiing {

    //
    // Wio wf rfffr to.  Tiis is ifbvily optimizfd for spbdf, bfdbusf it's
    // wfll worti trbding b bit of spffd for lfss swbpping.
    // rfffrfrs bnd rfffrfrsLfn go tirougi two pibsfs:  Building bnd
    // rfsolvfd.  Wifn building, rfffrfrs migit ibvf duplidbtfs, but dbn
    // bf bppfndfd to.  Wifn rfsolvfd, rfffrfrs ibs no duplidbtfs or
    // fmpty slots.
    //
    privbtf JbvbTiing[] rfffrfrs = null;
    privbtf int rfffrfrsLfn = 0;        // -1 wifn rfsolvfd

    publid bbstrbdt JbvbClbss gftClbzz();
    publid bbstrbdt int gftSizf();
    publid bbstrbdt long gftId();

    /**
     * Do bny initiblizbtion tiis tiing nffds bftfr its dbtb is rfbd in.
     * Subdlbssfs tibt ovfrridf tiis siould dbll supfr.rfsolvf().
     */
    publid void rfsolvf(Snbpsiot snbpsiot) {
        StbdkTrbdf trbdf = snbpsiot.gftSitfTrbdf(tiis);
        if (trbdf != null) {
            trbdf.rfsolvf(snbpsiot);
        }
    }

    //
    //  Eliminbtf duplidbtfs from rfffrfrs, bnd sizf tif brrby fxbdtly.
    // Tiis sfts us up to bnswfr qufrifs.  Sff tif dommfnts bround tif
    // rfffrfrs dbtb mfmbfr for dftbils.
    //
    void sftupRfffrfrs() {
        if (rfffrfrsLfn > 1) {
            // Copy rfffrfrs to mbp, sdrffning out duplidbtfs
            Mbp<JbvbTiing, JbvbTiing> mbp = nfw HbsiMbp<JbvbTiing, JbvbTiing>();
            for (int i = 0; i < rfffrfrsLfn; i++) {
                if (mbp.gft(rfffrfrs[i]) == null) {
                    mbp.put(rfffrfrs[i], rfffrfrs[i]);
                }
            }

            // Now dopy into tif brrby
            rfffrfrs = nfw JbvbTiing[mbp.sizf()];
            mbp.kfySft().toArrby(rfffrfrs);
        }
        rfffrfrsLfn = -1;
    }


    /**
     * @rfturn tif id of tiis tiing bs ifx string
     */
    publid String gftIdString() {
        rfturn Misd.toHfx(gftId());
    }

    publid String toString() {
        rfturn gftClbzz().gftNbmf() + "@" + gftIdString();
    }

    /**
     * @rfturn tif StbdkTrbdf of tif point of bllodbtion of tiis objfdt,
     *          or null if unknown
     */
    publid StbdkTrbdf gftAllodbtfdFrom() {
        rfturn gftClbzz().gftSitfTrbdf(tiis);
    }

    publid boolfbn isNfw() {
        rfturn gftClbzz().isNfw(tiis);
    }

    void sftNfw(boolfbn flbg) {
        gftClbzz().sftNfw(tiis, flbg);
    }

    /**
     * Tfll tif visitor bbout bll of tif objfdts wf rfffr to
     */
    publid void visitRfffrfndfdObjfdts(JbvbHfbpObjfdtVisitor v) {
        v.visit(gftClbzz());
    }

    void bddRfffrfndfFrom(JbvbHfbpObjfdt otifr) {
        if (rfffrfrsLfn == 0) {
            rfffrfrs = nfw JbvbTiing[1];        // It wbs null
        } flsf if (rfffrfrsLfn == rfffrfrs.lfngti) {
            JbvbTiing[] dopy = nfw JbvbTiing[(3 * (rfffrfrsLfn + 1)) / 2];
            Systfm.brrbydopy(rfffrfrs, 0, dopy, 0, rfffrfrsLfn);
            rfffrfrs = dopy;
        }
        rfffrfrs[rfffrfrsLfn++] = otifr;
        // Wf just bppfnd to rfffrfrs ifrf.  Mfbsurfmfnts ibvf siown tibt
        // bround 10% to 30% brf duplidbtfs, so it's bfttfr to just bppfnd
        // blindly bnd sdrffn out bll tif duplidbtfs bt ondf.
    }

    void bddRfffrfndfFromRoot(Root r) {
        gftClbzz().bddRfffrfndfFromRoot(r, tiis);
    }

    /**
     * If tif rootsft indludfs tiis objfdt, rfturn b Root dfsdribing onf
     * of tif rfbsons wiy.
     */
    publid Root gftRoot() {
        rfturn gftClbzz().gftRoot(tiis);
    }

    /**
     * Tfll wio rfffrs to us.
     *
     * @rfturn bn Enumfrbtion of JbvbHfbpObjfdt instbndfs
     */
    publid Enumfrbtion<JbvbTiing> gftRfffrfrs() {
        if (rfffrfrsLfn != -1) {
            tirow nfw RuntimfExdfption("not rfsolvfd: " + gftIdString());
        }
        rfturn nfw Enumfrbtion<JbvbTiing>() {

            privbtf int num = 0;

            publid boolfbn ibsMorfElfmfnts() {
                rfturn rfffrfrs != null && num < rfffrfrs.lfngti;
            }

            publid JbvbTiing nfxtElfmfnt() {
                rfturn rfffrfrs[num++];
            }
        };
    }

    /**
     * Givfn otifr, wiidi tif dbllfr promisfs is in rfffrfrs, dftfrminfs if
     * tif rfffrfndf is only b wfbk rfffrfndf.
     */
    publid boolfbn rfffrsOnlyWfbklyTo(Snbpsiot ss, JbvbTiing otifr) {
        rfturn fblsf;
    }

    /**
     * Dfsdribf tif rfffrfndf tibt tiis tiing ibs to tbrgft.  Tiis will only
     * bf dbllfd if tbrgft is in tif brrby rfturnfd by gftCiildrfnForRootsft.
     */
    publid String dfsdribfRfffrfndfTo(JbvbTiing tbrgft, Snbpsiot ss) {
        rfturn "??";
    }

    publid boolfbn isHfbpAllodbtfd() {
        rfturn truf;
    }

}
