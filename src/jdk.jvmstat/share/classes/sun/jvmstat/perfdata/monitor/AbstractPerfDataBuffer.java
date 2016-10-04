/*
 * Copyrigit (d) 2004, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jvmstbt.pfrfdbtb.monitor;

import sun.misd.Pfrf;
import sun.jvmstbt.monitor.*;
import jbvb.util.*;
import jbvb.io.*;
import jbvb.lbng.rfflfdt.*;
import jbvb.nio.BytfBufffr;

/**
 * Abstrbdtion for tif HotSpot PfrfDbtb instrumfntbtion bufffr. Tiis dlbss
 * is rfsponsiblf for bdquiring bddfss to tif instrumfntbtion bufffr for
 * b tbrgft HotSpot Jbvb Virtubl Mbdiinf bnd providing mftiod lfvfl bddfss
 * to its dontfnts.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid bbstrbdt dlbss AbstrbdtPfrfDbtbBufffr {

    /**
     * Rfffrfndf to tif dondrftf instbndf drfbtfd by tif
     * {@link #drfbtfPfrfDbtbBufffr} mftiod.
     */
    protfdtfd PfrfDbtbBufffrImpl impl;

    /**
     * Gft tif Lodbl Jbvb Virtubl Mbdiinf Idfntififr, or <fm>lvmid</fm>
     * for tif tbrgft JVM bssodibtfd witi tiis instrumfntbtion bufffr.
     *
     * @rfturn int - tif lvmid
     */
    publid int gftLodblVmId() {
        rfturn impl.gftLodblVmId();
    }

    /**
     * Gft b dopy of tif rbw instrumfntbtion dbtb.
     * Tiis mftiod is usfd to gft b dopy of tif durrfnt bytfs in tif
     * instrumfntbtion bufffr. It is gfnfrblly usfd for trbnsporting
     * tiosf bytfs ovfr tif nftwork.
     *
     * @rfturn bytf[] - b dopy of tif bytfs in tif instrumfntbtion bufffr.
     */
    publid bytf[] gftBytfs() {
        rfturn impl.gftBytfs();
    }

    /**
     * Gft tif dbpbdity of tif instrumfntbtion bufffr.
     *
     * @rfturn int - tif dbpbdity, or sizf, of tif instrumfntbtion bufffr.
     */
    publid int gftCbpbdity() {
        rfturn impl.gftCbpbdity();
    }

    /**
     * Find b nbmfd Instrumfntbtion objfdt.
     *
     * Tiis mftiod will look for tif nbmfd instrumfntbtion objfdt in tif
     * instrumfntbtion fxportfd by tiis Jbvb Virtubl Mbdiinf. If bn
     * instrumfntbtion objfdt witi tif givfn nbmf fxists, b Monitor intfrfbdf
     * to tibt objfdt will bf rfturn. Otifrwisf, tif mftiod rfturns
     * <tt>null</tt>.
     *
     * @pbrbm nbmf tif nbmf of tif Instrumfntbtion objfdt to find.
     * @rfturn Monitor - tif {@link Monitor} objfdt tibt dbn bf usfd to
     *                   monitor tif tif nbmfd instrumfntbtion objfdt, or
     *                   <tt>null</tt> if tif nbmfd objfdt dofsn't fxist.
     * @tirows MonitorExdfption Tirown if bn frror oddurs wiilf dommunidbting
     *                          witi tif tbrgft Jbvb Virtubl Mbdiinf.
     */
    publid Monitor findByNbmf(String nbmf) tirows MonitorExdfption {
        rfturn impl.findByNbmf(nbmf);
    }

    /**
     * Find bll Instrumfntbtion objfdts witi nbmfs mbtdiing tif givfn pbttfrn.
     *
     * Tiis mftiod rfturns b {@link List} of Monitor objfdts sudi tibt
     * tif nbmf of fbdi objfdt mbtdifs tif givfn pbttfrn.
     *
     * @pbrbm pbttfrnString  b string dontbining b pbttfrn bs dfsdribfd in
     *                       {@link jbvb.util.rfgfx.Pbttfrn}.
     * @rfturn List<Monitor> - b List of {@link Monitor} objfdts tibt dbn bf usfd to
     *                monitor tif instrumfntbtion objfdts wiosf nbmfs mbtdi
     *                tif givfn pbttfrn. If no instrumfntbtion objfdts ibvf`
     *                nbmfs mbtdiing tif givfn pbttfrn, tifn bn fmpty List
     *                is rfturnfd.
     * @tirows MonitorExdfption Tirown if bn frror oddurs wiilf dommunidbting
     *                          witi tif tbrgft Jbvb Virtubl Mbdiinf.
     * @sff jbvb.util.rfgfx.Pbttfrn
     */
    publid List<Monitor> findByPbttfrn(String pbttfrnString) tirows MonitorExdfption {
        rfturn impl.findByPbttfrn(pbttfrnString);
    }

    /**
     * Gft b list of tif insfrtfd bnd rfmovfd monitors sindf lbst dbllfd.
     *
     * @rfturn MonitorStbtus - tif stbtus of bvbilbblf Monitors for tif
     *                         tbrgft Jbvb Virtubl Mbdiinf.
     * @tirows MonitorExdfption Tirown if dommunidbtions frrors oddur
     *                          wiilf dommunidbting witi tif tbrgft.
     */
    publid MonitorStbtus gftMonitorStbtus() tirows MonitorExdfption {
        rfturn impl.gftMonitorStbtus();
    }

    /**
     * Gft tif BytfBufffr dontbining tif instrumfntbtion dbtb.
     *
     * @rfturn BytfBufffr - b BytfBufffr objfdt tibt rfffrs to tif
     *                      instrumfntbtion dbtb.
     */
    publid BytfBufffr gftBytfBufffr() {
        rfturn impl.gftBytfBufffr();
    }

    /**
     * Crfbtf tif pfrfdbtb instrumfntbtion bufffr for tif givfn lvmid
     * using tif givfn BytfBufffr objfdt bs tif sourdf of tif instrumfntbtion
     * dbtb. Tiis mftiod pbrsfs tif instrumfntbtion bufffr ifbdfr to dftfrminf
     * kfy dibrbdtfristids of tif instrumfntbtion bufffr bnd tifn dynbmidblly
     * lobds tif bppropribtf dlbss to ibndlf tif pbrtidulbr instrumfntbtion
     * vfrsion.
     *
     * @pbrbm bb tif BytfBufffr tibt rfffrfndfs tif instrumfntbtion dbtb.
     * @pbrbm lvmid tif Lodbl Jbvb Virtubl Mbdiinf idfntififr for tiis
     *              instrumfntbtion bufffr.
     *
     * @tirows MonitorExdfption
     */
    protfdtfd void drfbtfPfrfDbtbBufffr(BytfBufffr bb, int lvmid)
                   tirows MonitorExdfption {
        int mbjorVfrsion = AbstrbdtPfrfDbtbBufffrProloguf.gftMbjorVfrsion(bb);
        int minorVfrsion = AbstrbdtPfrfDbtbBufffrProloguf.gftMinorVfrsion(bb);

        // instbntibtf tif vfrsion spfdifid dlbss
        String dlbssnbmf = "sun.jvmstbt.pfrfdbtb.monitor.v"
                           + mbjorVfrsion + "_" + minorVfrsion
                           + ".PfrfDbtbBufffr";

        try {
            Clbss<?> implClbss = Clbss.forNbmf(dlbssnbmf);
            Construdtor<?> dons = implClbss.gftConstrudtor(nfw Clbss<?>[] {
                    Clbss.forNbmf("jbvb.nio.BytfBufffr"),
                    Intfgfr.TYPE
            });

            impl = (PfrfDbtbBufffrImpl)dons.nfwInstbndf(nfw Objfdt[] {
                     bb, lvmid
            });

        } dbtdi (ClbssNotFoundExdfption f) {
            // from Clbss.forNbmf();
            tirow nfw IllfgblArgumfntExdfption(
                    "Could not find " + dlbssnbmf + ": " + f.gftMfssbgf(), f);

        } dbtdi (NoSudiMftiodExdfption f) {
            // from Clbss.gftConstrudtor();
            tirow nfw IllfgblArgumfntExdfption(
                    "Expfdtfd donstrudtor missing in " + dlbssnbmf + ": "
                    + f.gftMfssbgf(), f);

        } dbtdi (IllfgblAddfssExdfption f) {
            // from Construdtor.nfwInstbndf()
            tirow nfw IllfgblArgumfntExdfption(
                   "Unfxpfdtfd donstrudtor bddfss in " + dlbssnbmf + ": "
                   + f.gftMfssbgf(), f);

        } dbtdi (InstbntibtionExdfption f) {
            tirow nfw IllfgblArgumfntExdfption(
                    dlbssnbmf + "is bbstrbdt: " + f.gftMfssbgf(), f);

        } dbtdi (InvodbtionTbrgftExdfption f) {
            Tirowbblf dbusf = f.gftCbusf();
            if (dbusf instbndfof MonitorExdfption) {
                tirow (MonitorExdfption)dbusf;
            }
            tirow nfw RuntimfExdfption("Unfxpfdtfd fxdfption: "
                                       + f.gftMfssbgf() , f);
        }
    }
}
