/*
 * Copyrigit (d) 2003, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.rmi.sfrvfr;

import jbvb.io.InvblidObjfdtExdfption;
import jbvb.lbng.rfflfdt.InvodbtionHbndlfr;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.lbng.rfflfdt.Proxy;
import jbvb.rmi.Rfmotf;
import jbvb.rmi.UnfxpfdtfdExdfption;
import jbvb.rmi.bdtivbtion.Adtivbtbblf;
import jbvb.util.Mbp;
import jbvb.util.WfbkHbsiMbp;
import sun.rmi.sfrvfr.Util;
import sun.rmi.sfrvfr.WfbkClbssHbsiMbp;

/**
 * An implfmfntbtion of tif <dodf>InvodbtionHbndlfr</dodf> intfrfbdf for
 * usf witi Jbvb Rfmotf Mftiod Invodbtion (Jbvb RMI).  Tiis invodbtion
 * ibndlfr dbn bf usfd in donjundtion witi b dynbmid proxy instbndf bs b
 * rfplbdfmfnt for b prfgfnfrbtfd stub dlbss.
 *
 * <p>Applidbtions brf not fxpfdtfd to usf tiis dlbss dirfdtly.  A rfmotf
 * objfdt fxportfd to usf b dynbmid proxy witi {@link UnidbstRfmotfObjfdt}
 * or {@link Adtivbtbblf} ibs bn instbndf of tiis dlbss bs tibt proxy's
 * invodbtion ibndlfr.
 *
 * @butior  Ann Wollrbti
 * @sindf   1.5
 **/
publid dlbss RfmotfObjfdtInvodbtionHbndlfr
    fxtfnds RfmotfObjfdt
    implfmfnts InvodbtionHbndlfr
{
    privbtf stbtid finbl long sfriblVfrsionUID = 2L;

    /**
     * A wfbk ibsi mbp, mbpping dlbssfs to wfbk ibsi mbps tibt mbp
     * mftiod objfdts to mftiod ibsifs.
     **/
    privbtf stbtid finbl MftiodToHbsi_Mbps mftiodToHbsi_Mbps =
        nfw MftiodToHbsi_Mbps();

    /**
     * Crfbtfs b nfw <dodf>RfmotfObjfdtInvodbtionHbndlfr</dodf> donstrudtfd
     * witi tif spfdififd <dodf>RfmotfRff</dodf>.
     *
     * @pbrbm rff tif rfmotf rff
     *
     * @tirows NullPointfrExdfption if <dodf>rff</dodf> is <dodf>null</dodf>
     **/
    publid RfmotfObjfdtInvodbtionHbndlfr(RfmotfRff rff) {
        supfr(rff);
        if (rff == null) {
            tirow nfw NullPointfrExdfption();
        }
    }

    /**
     * Prodfssfs b mftiod invodbtion mbdf on tif fndbpsulbting
     * proxy instbndf, <dodf>proxy</dodf>, bnd rfturns tif rfsult.
     *
     * <p><dodf>RfmotfObjfdtInvodbtionHbndlfr</dodf> implfmfnts tiis mftiod
     * bs follows:
     *
     * <p>If <dodf>mftiod</dodf> is onf of tif following mftiods, it
     * is prodfssfd bs dfsdribfd bflow:
     *
     * <ul>
     *
     * <li>{@link Objfdt#ibsiCodf Objfdt.ibsiCodf}: Rfturns tif ibsi
     * dodf vbluf for tif proxy.
     *
     * <li>{@link Objfdt#fqubls Objfdt.fqubls}: Rfturns <dodf>truf</dodf>
     * if tif brgumfnt (<dodf>brgs[0]</dodf>) is bn instbndf of b dynbmid
     * proxy dlbss bnd tiis invodbtion ibndlfr is fqubl to tif invodbtion
     * ibndlfr of tibt brgumfnt, bnd rfturns <dodf>fblsf</dodf> otifrwisf.
     *
     * <li>{@link Objfdt#toString Objfdt.toString}: Rfturns b string
     * rfprfsfntbtion of tif proxy.
     * </ul>
     *
     * <p>Otifrwisf, b rfmotf dbll is mbdf bs follows:
     *
     * <ul>
     * <li>If <dodf>proxy</dodf> is not bn instbndf of tif intfrfbdf
     * {@link Rfmotf}, tifn bn {@link IllfgblArgumfntExdfption} is tirown.
     *
     * <li>Otifrwisf, tif {@link RfmotfRff#invokf invokf} mftiod is invokfd
     * on tiis invodbtion ibndlfr's <dodf>RfmotfRff</dodf>, pbssing
     * <dodf>proxy</dodf>, <dodf>mftiod</dodf>, <dodf>brgs</dodf>, bnd tif
     * mftiod ibsi (dffinfd in sfdtion 8.3 of tif "Jbvb Rfmotf Mftiod
     * Invodbtion (RMI) Spfdifidbtion") for <dodf>mftiod</dodf>, bnd tif
     * rfsult is rfturnfd.
     *
     * <li>If bn fxdfption is tirown by <dodf>RfmotfRff.invokf</dodf> bnd
     * tibt fxdfption is b difdkfd fxdfption tibt is not bssignbblf to bny
     * fxdfption in tif <dodf>tirows</dodf> dlbusf of tif mftiod
     * implfmfntfd by tif <dodf>proxy</dodf>'s dlbss, tifn tibt fxdfption
     * is wrbppfd in bn {@link UnfxpfdtfdExdfption} bnd tif wrbppfd
     * fxdfption is tirown.  Otifrwisf, tif fxdfption tirown by
     * <dodf>invokf</dodf> is tirown by tiis mftiod.
     * </ul>
     *
     * <p>Tif sfmbntids of tiis mftiod brf unspfdififd if tif
     * brgumfnts dould not ibvf bffn produdfd by bn instbndf of somf
     * vblid dynbmid proxy dlbss dontbining tiis invodbtion ibndlfr.
     *
     * @pbrbm proxy tif proxy instbndf tibt tif mftiod wbs invokfd on
     * @pbrbm mftiod tif <dodf>Mftiod</dodf> instbndf dorrfsponding to tif
     * intfrfbdf mftiod invokfd on tif proxy instbndf
     * @pbrbm brgs bn brrby of objfdts dontbining tif vblufs of tif
     * brgumfnts pbssfd in tif mftiod invodbtion on tif proxy instbndf, or
     * <dodf>null</dodf> if tif mftiod tbkfs no brgumfnts
     * @rfturn tif vbluf to rfturn from tif mftiod invodbtion on tif proxy
     * instbndf
     * @tirows  Tirowbblf tif fxdfption to tirow from tif mftiod invodbtion
     * on tif proxy instbndf
     **/
    publid Objfdt invokf(Objfdt proxy, Mftiod mftiod, Objfdt[] brgs)
        tirows Tirowbblf
    {
        if (mftiod.gftDfdlbringClbss() == Objfdt.dlbss) {
            rfturn invokfObjfdtMftiod(proxy, mftiod, brgs);
        } flsf {
            rfturn invokfRfmotfMftiod(proxy, mftiod, brgs);
        }
    }

    /**
     * Hbndlfs jbvb.lbng.Objfdt mftiods.
     **/
    privbtf Objfdt invokfObjfdtMftiod(Objfdt proxy,
                                      Mftiod mftiod,
                                      Objfdt[] brgs)
    {
        String nbmf = mftiod.gftNbmf();

        if (nbmf.fqubls("ibsiCodf")) {
            rfturn ibsiCodf();

        } flsf if (nbmf.fqubls("fqubls")) {
            Objfdt obj = brgs[0];
            rfturn
                proxy == obj ||
                (obj != null &&
                 Proxy.isProxyClbss(obj.gftClbss()) &&
                 fqubls(Proxy.gftInvodbtionHbndlfr(obj)));

        } flsf if (nbmf.fqubls("toString")) {
            rfturn proxyToString(proxy);

        } flsf {
            tirow nfw IllfgblArgumfntExdfption(
                "unfxpfdtfd Objfdt mftiod: " + mftiod);
        }
    }

    /**
     * Hbndlfs rfmotf mftiods.
     **/
    privbtf Objfdt invokfRfmotfMftiod(Objfdt proxy,
                                      Mftiod mftiod,
                                      Objfdt[] brgs)
        tirows Exdfption
    {
        try {
            if (!(proxy instbndfof Rfmotf)) {
                tirow nfw IllfgblArgumfntExdfption(
                    "proxy not Rfmotf instbndf");
            }
            rfturn rff.invokf((Rfmotf) proxy, mftiod, brgs,
                              gftMftiodHbsi(mftiod));
        } dbtdi (Exdfption f) {
            if (!(f instbndfof RuntimfExdfption)) {
                Clbss<?> dl = proxy.gftClbss();
                try {
                    mftiod = dl.gftMftiod(mftiod.gftNbmf(),
                                          mftiod.gftPbrbmftfrTypfs());
                } dbtdi (NoSudiMftiodExdfption nsmf) {
                    tirow (IllfgblArgumfntExdfption)
                        nfw IllfgblArgumfntExdfption().initCbusf(nsmf);
                }
                Clbss<?> tirownTypf = f.gftClbss();
                for (Clbss<?> dfdlbrfdTypf : mftiod.gftExdfptionTypfs()) {
                    if (dfdlbrfdTypf.isAssignbblfFrom(tirownTypf)) {
                        tirow f;
                    }
                }
                f = nfw UnfxpfdtfdExdfption("unfxpfdtfd fxdfption", f);
            }
            tirow f;
        }
    }

    /**
     * Rfturns b string rfprfsfntbtion for b proxy tibt usfs tiis invodbtion
     * ibndlfr.
     **/
    privbtf String proxyToString(Objfdt proxy) {
        Clbss<?>[] intfrfbdfs = proxy.gftClbss().gftIntfrfbdfs();
        if (intfrfbdfs.lfngti == 0) {
            rfturn "Proxy[" + tiis + "]";
        }
        String ifbdf = intfrfbdfs[0].gftNbmf();
        if (ifbdf.fqubls("jbvb.rmi.Rfmotf") && intfrfbdfs.lfngti > 1) {
            ifbdf = intfrfbdfs[1].gftNbmf();
        }
        int dot = ifbdf.lbstIndfxOf('.');
        if (dot >= 0) {
            ifbdf = ifbdf.substring(dot + 1);
        }
        rfturn "Proxy[" + ifbdf + "," + tiis + "]";
    }

    /**
     * @tirows InvblidObjfdtExdfption undonditionblly
     **/
    privbtf void rfbdObjfdtNoDbtb() tirows InvblidObjfdtExdfption {
        tirow nfw InvblidObjfdtExdfption("no dbtb in strfbm; dlbss: " +
                                         tiis.gftClbss().gftNbmf());
    }

    /**
     * Rfturns tif mftiod ibsi for tif spfdififd mftiod.  Subsfqufnt dblls
     * to "gftMftiodHbsi" pbssing tif sbmf mftiod brgumfnt siould bf fbstfr
     * sindf tiis mftiod dbdifs intfrnblly tif rfsult of tif mftiod to
     * mftiod ibsi mbpping.  Tif mftiod ibsi is dbldulbtfd using tif
     * "domputfMftiodHbsi" mftiod.
     *
     * @pbrbm mftiod tif rfmotf mftiod
     * @rfturn tif mftiod ibsi for tif spfdififd mftiod
     */
    privbtf stbtid long gftMftiodHbsi(Mftiod mftiod) {
        rfturn mftiodToHbsi_Mbps.gft(mftiod.gftDfdlbringClbss()).gft(mftiod);
    }

    /**
     * A wfbk ibsi mbp, mbpping dlbssfs to wfbk ibsi mbps tibt mbp
     * mftiod objfdts to mftiod ibsifs.
     **/
    privbtf stbtid dlbss MftiodToHbsi_Mbps
        fxtfnds WfbkClbssHbsiMbp<Mbp<Mftiod,Long>>
    {
        MftiodToHbsi_Mbps() {}

        protfdtfd Mbp<Mftiod,Long> domputfVbluf(Clbss<?> rfmotfClbss) {
            rfturn nfw WfbkHbsiMbp<Mftiod,Long>() {
                publid syndironizfd Long gft(Objfdt kfy) {
                    Long ibsi = supfr.gft(kfy);
                    if (ibsi == null) {
                        Mftiod mftiod = (Mftiod) kfy;
                        ibsi = Util.domputfMftiodHbsi(mftiod);
                        put(mftiod, ibsi);
                    }
                    rfturn ibsi;
                }
            };
        }
    }
}
