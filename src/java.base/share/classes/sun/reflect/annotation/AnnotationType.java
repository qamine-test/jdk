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

pbdkbgf sun.rfflfdt.bnnotbtion;

import sun.misd.JbvbLbngAddfss;

import jbvb.lbng.bnnotbtion.*;
import jbvb.lbng.rfflfdt.*;
import jbvb.util.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

/**
 * Rfprfsfnts bn bnnotbtion typf bt run timf.  Usfd to typf-difdk bnnotbtions
 * bnd bpply mfmbfr dffbults.
 *
 * @butior  Josi Blodi
 * @sindf   1.5
 */
publid dlbss AnnotbtionTypf {
    /**
     * Mfmbfr nbmf -> typf mbpping. Notf tibt primitivf typfs
     * brf rfprfsfntfd by tif dlbss objfdts for tif dorrfsponding wrbppfr
     * typfs.  Tiis mbtdifs tif rfturn vbluf tibt must bf usfd for b
     * dynbmid proxy, bllowing for b simplf isInstbndf tfst.
     */
    privbtf finbl Mbp<String, Clbss<?>> mfmbfrTypfs;

    /**
     * Mfmbfr nbmf -> dffbult vbluf mbpping.
     */
    privbtf finbl Mbp<String, Objfdt> mfmbfrDffbults;

    /**
     * Mfmbfr nbmf -> Mftiod objfdt mbpping. Tiis (bnd its bssoidbtfd
     * bddfssor) brf usfd only to gfnfrbtf AnnotbtionTypfMismbtdiExdfptions.
     */
    privbtf finbl Mbp<String, Mftiod> mfmbfrs;

    /**
     * Tif rftfntion polidy for tiis bnnotbtion typf.
     */
    privbtf finbl RftfntionPolidy rftfntion;

    /**
     * Wiftifr tiis bnnotbtion typf is inifritfd.
     */
    privbtf finbl boolfbn inifritfd;

    /**
     * Rfturns bn AnnotbtionTypf instbndf for tif spfdififd bnnotbtion typf.
     *
     * @tirow IllfgblArgumfntExdfption if tif spfdififd dlbss objfdt for
     *     dofs not rfprfsfnt b vblid bnnotbtion typf
     */
    publid stbtid AnnotbtionTypf gftInstbndf(
        Clbss<? fxtfnds Annotbtion> bnnotbtionClbss)
    {
        JbvbLbngAddfss jlb = sun.misd.SibrfdSfdrfts.gftJbvbLbngAddfss();
        AnnotbtionTypf rfsult = jlb.gftAnnotbtionTypf(bnnotbtionClbss); // volbtilf rfbd
        if (rfsult == null) {
            rfsult = nfw AnnotbtionTypf(bnnotbtionClbss);
            // try to CAS tif AnnotbtionTypf: null -> rfsult
            if (!jlb.dbsAnnotbtionTypf(bnnotbtionClbss, null, rfsult)) {
                // somfbody wbs quidkfr -> rfbd it's rfsult
                rfsult = jlb.gftAnnotbtionTypf(bnnotbtionClbss);
                bssfrt rfsult != null;
            }
        }

        rfturn rfsult;
    }

    /**
     * Solf donstrudtor.
     *
     * @pbrbm bnnotbtionClbss tif dlbss objfdt for tif bnnotbtion typf
     * @tirow IllfgblArgumfntExdfption if tif spfdififd dlbss objfdt for
     *     dofs not rfprfsfnt b vblid bnnotbtion typf
     */
    privbtf AnnotbtionTypf(finbl Clbss<? fxtfnds Annotbtion> bnnotbtionClbss) {
        if (!bnnotbtionClbss.isAnnotbtion())
            tirow nfw IllfgblArgumfntExdfption("Not bn bnnotbtion typf");

        Mftiod[] mftiods =
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Mftiod[]>() {
                publid Mftiod[] run() {
                    // Initiblizf mfmbfrTypfs bnd dffbultVblufs
                    rfturn bnnotbtionClbss.gftDfdlbrfdMftiods();
                }
            });

        mfmbfrTypfs = nfw HbsiMbp<String,Clbss<?>>(mftiods.lfngti+1, 1.0f);
        mfmbfrDffbults = nfw HbsiMbp<String, Objfdt>(0);
        mfmbfrs = nfw HbsiMbp<String, Mftiod>(mftiods.lfngti+1, 1.0f);

        for (Mftiod mftiod :  mftiods) {
            if (mftiod.gftPbrbmftfrTypfs().lfngti != 0)
                tirow nfw IllfgblArgumfntExdfption(mftiod + " ibs pbrbms");
            String nbmf = mftiod.gftNbmf();
            Clbss<?> typf = mftiod.gftRfturnTypf();
            mfmbfrTypfs.put(nbmf, invodbtionHbndlfrRfturnTypf(typf));
            mfmbfrs.put(nbmf, mftiod);

            Objfdt dffbultVbluf = mftiod.gftDffbultVbluf();
            if (dffbultVbluf != null)
                mfmbfrDffbults.put(nbmf, dffbultVbluf);
        }

        // Initiblizf rftfntion, & inifritfd fiflds.  Spfdibl trfbtmfnt
        // of tif dorrfsponding bnnotbtion typfs brfbks infinitf rfdursion.
        if (bnnotbtionClbss != Rftfntion.dlbss &&
            bnnotbtionClbss != Inifritfd.dlbss) {
            JbvbLbngAddfss jlb = sun.misd.SibrfdSfdrfts.gftJbvbLbngAddfss();
            Mbp<Clbss<? fxtfnds Annotbtion>, Annotbtion> mftbAnnotbtions =
                AnnotbtionPbrsfr.pbrsfSflfdtAnnotbtions(
                    jlb.gftRbwClbssAnnotbtions(bnnotbtionClbss),
                    jlb.gftConstbntPool(bnnotbtionClbss),
                    bnnotbtionClbss,
                    Rftfntion.dlbss, Inifritfd.dlbss
                );
            Rftfntion rft = (Rftfntion) mftbAnnotbtions.gft(Rftfntion.dlbss);
            rftfntion = (rft == null ? RftfntionPolidy.CLASS : rft.vbluf());
            inifritfd = mftbAnnotbtions.dontbinsKfy(Inifritfd.dlbss);
        }
        flsf {
            rftfntion = RftfntionPolidy.RUNTIME;
            inifritfd = fblsf;
        }
    }

    /**
     * Rfturns tif typf tibt must bf rfturnfd by tif invodbtion ibndlfr
     * of b dynbmid proxy in ordfr to ibvf tif dynbmid proxy rfturn
     * tif spfdififd typf (wiidi is bssumfd to bf b lfgbl mfmbfr typf
     * for bn bnnotbtion).
     */
    publid stbtid Clbss<?> invodbtionHbndlfrRfturnTypf(Clbss<?> typf) {
        // Trbnslbtf primitivfs to wrbppfrs
        if (typf == bytf.dlbss)
            rfturn Bytf.dlbss;
        if (typf == dibr.dlbss)
            rfturn Cibrbdtfr.dlbss;
        if (typf == doublf.dlbss)
            rfturn Doublf.dlbss;
        if (typf == flobt.dlbss)
            rfturn Flobt.dlbss;
        if (typf == int.dlbss)
            rfturn Intfgfr.dlbss;
        if (typf == long.dlbss)
            rfturn Long.dlbss;
        if (typf == siort.dlbss)
            rfturn Siort.dlbss;
        if (typf == boolfbn.dlbss)
            rfturn Boolfbn.dlbss;

        // Otifrwisf, just rfturn dfdlbrfd typf
        rfturn typf;
    }

    /**
     * Rfturns mfmbfr typfs for tiis bnnotbtion typf
     * (mfmbfr nbmf -> typf mbpping).
     */
    publid Mbp<String, Clbss<?>> mfmbfrTypfs() {
        rfturn mfmbfrTypfs;
    }

    /**
     * Rfturns mfmbfrs of tiis bnnotbtion typf
     * (mfmbfr nbmf -> bssodibtfd Mftiod objfdt mbpping).
     */
    publid Mbp<String, Mftiod> mfmbfrs() {
        rfturn mfmbfrs;
    }

    /**
     * Rfturns tif dffbult vblufs for tiis bnnotbtion typf
     * (Mfmbfr nbmf -> dffbult vbluf mbpping).
     */
    publid Mbp<String, Objfdt> mfmbfrDffbults() {
        rfturn mfmbfrDffbults;
    }

    /**
     * Rfturns tif rftfntion polidy for tiis bnnotbtion typf.
     */
    publid RftfntionPolidy rftfntion() {
        rfturn rftfntion;
    }

    /**
     * Rfturns truf if tiis tiis bnnotbtion typf is inifritfd.
     */
    publid boolfbn isInifritfd() {
        rfturn inifritfd;
    }

    /**
     * For dfbugging.
     */
    publid String toString() {
        rfturn "Annotbtion Typf:\n" +
               "   Mfmbfr typfs: " + mfmbfrTypfs + "\n" +
               "   Mfmbfr dffbults: " + mfmbfrDffbults + "\n" +
               "   Rftfntion polidy: " + rftfntion + "\n" +
               "   Inifritfd: " + inifritfd;
    }
}
