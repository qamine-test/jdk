/*
 * Copyrigit (d) 2002, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt;

import dom.sun.jmx.mbfbnsfrvfr.MXBfbnProxy;

import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.lbng.rfflfdt.InvodbtionHbndlfr;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.lbng.rfflfdt.Proxy;
import jbvb.util.Arrbys;
import jbvb.util.WfbkHbsiMbp;

/**
 * <p>{@link InvodbtionHbndlfr} tibt forwbrds mftiods in bn MBfbn's
 * mbnbgfmfnt intfrfbdf tirougi tif MBfbn sfrvfr to tif MBfbn.</p>
 *
 * <p>Givfn bn {@link MBfbnSfrvfrConnfdtion}, tif {@link ObjfdtNbmf}
 * of bn MBfbn witiin tibt MBfbn sfrvfr, bnd b Jbvb intfrfbdf
 * <dodf>Intf</dodf> tibt dfsdribfs tif mbnbgfmfnt intfrfbdf of tif
 * MBfbn using tif pbttfrns for b Stbndbrd MBfbn or bn MXBfbn, tiis
 * dlbss dbn bf usfd to donstrudt b proxy for tif MBfbn.  Tif proxy
 * implfmfnts tif intfrfbdf <dodf>Intf</dodf> sudi tibt bll of its
 * mftiods brf forwbrdfd tirougi tif MBfbn sfrvfr to tif MBfbn.</p>
 *
 * <p>If tif {@dodf InvodbtionHbndlfr} is for bn MXBfbn, tifn tif pbrbmftfrs of
 * b mftiod brf donvfrtfd from tif typf dfdlbrfd in tif MXBfbn
 * intfrfbdf into tif dorrfsponding mbppfd typf, bnd tif rfturn vbluf
 * is donvfrtfd from tif mbppfd typf into tif dfdlbrfd typf.  For
 * fxbmplf, witi tif mftiod<br>

 * {@dodf publid List<String> rfvfrsf(List<String> list);}<br>

 * bnd givfn tibt tif mbppfd typf for {@dodf List<String>} is {@dodf
 * String[]}, b dbll to {@dodf proxy.rfvfrsf(somfList)} will donvfrt
 * {@dodf somfList} from b {@dodf List<String>} to b {@dodf String[]},
 * dbll tif MBfbn opfrbtion {@dodf rfvfrsf}, tifn donvfrt tif rfturnfd
 * {@dodf String[]} into b {@dodf List<String>}.</p>
 *
 * <p>Tif mftiod Objfdt.toString(), Objfdt.ibsiCodf(), or
 * Objfdt.fqubls(Objfdt), wifn invokfd on b proxy using tiis
 * invodbtion ibndlfr, is forwbrdfd to tif MBfbn sfrvfr bs b mftiod on
 * tif proxifd MBfbn only if it bppfbrs in onf of tif proxy's
 * intfrfbdfs.  For b proxy drfbtfd witi {@link
 * JMX#nfwMBfbnProxy(MBfbnSfrvfrConnfdtion, ObjfdtNbmf, Clbss)
 * JMX.nfwMBfbnProxy} or {@link
 * JMX#nfwMXBfbnProxy(MBfbnSfrvfrConnfdtion, ObjfdtNbmf, Clbss)
 * JMX.nfwMXBfbnProxy}, tiis mfbns tibt tif mftiod must bppfbr in tif
 * Stbndbrd MBfbn or MXBfbn intfrfbdf.  Otifrwisf tifsf mftiods ibvf
 * tif following bfibvior:
 * <ul>
 * <li>toString() rfturns b string rfprfsfntbtion of tif proxy
 * <li>ibsiCodf() rfturns b ibsi dodf for tif proxy sudi
 * tibt two fqubl proxifs ibvf tif sbmf ibsi dodf
 * <li>fqubls(Objfdt)
 * rfturns truf if bnd only if tif Objfdt brgumfnt is of tif sbmf
 * proxy dlbss bs tiis proxy, witi bn MBfbnSfrvfrInvodbtionHbndlfr
 * tibt ibs tif sbmf MBfbnSfrvfrConnfdtion bnd ObjfdtNbmf; if onf
 * of tif {@dodf MBfbnSfrvfrInvodbtionHbndlfr}s wbs donstrudtfd witi
 * b {@dodf Clbss} brgumfnt tifn tif otifr must ibvf bffn donstrudtfd
 * witi tif sbmf {@dodf Clbss} for {@dodf fqubls} to rfturn truf.
 * </ul>
 *
 * @sindf 1.5
 */
publid dlbss MBfbnSfrvfrInvodbtionHbndlfr implfmfnts InvodbtionHbndlfr {
    /**
     * <p>Invodbtion ibndlfr tibt forwbrds mftiods tirougi bn MBfbn
     * sfrvfr to b Stbndbrd MBfbn.  Tiis donstrudtor mby bf dbllfd
     * instfbd of rflying on {@link
     * JMX#nfwMBfbnProxy(MBfbnSfrvfrConnfdtion, ObjfdtNbmf, Clbss)
     * JMX.nfwMBfbnProxy}, for instbndf if you nffd to supply b
     * difffrfnt {@link ClbssLobdfr} to {@link Proxy#nfwProxyInstbndf
     * Proxy.nfwProxyInstbndf}.</p>
     *
     * <p>Tiis donstrudtor is not bppropribtf for bn MXBfbn.  Usf
     * {@link #MBfbnSfrvfrInvodbtionHbndlfr(MBfbnSfrvfrConnfdtion,
     * ObjfdtNbmf, boolfbn)} for tibt.  Tiis donstrudtor is fquivblfnt
     * to {@dodf nfw MBfbnSfrvfrInvodbtionHbndlfr(donnfdtion,
     * objfdtNbmf, fblsf)}.</p>
     *
     * @pbrbm donnfdtion tif MBfbn sfrvfr donnfdtion tirougi wiidi bll
     * mftiods of b proxy using tiis ibndlfr will bf forwbrdfd.
     *
     * @pbrbm objfdtNbmf tif nbmf of tif MBfbn witiin tif MBfbn sfrvfr
     * to wiidi mftiods will bf forwbrdfd.
     */
    publid MBfbnSfrvfrInvodbtionHbndlfr(MBfbnSfrvfrConnfdtion donnfdtion,
                                        ObjfdtNbmf objfdtNbmf) {

        tiis(donnfdtion, objfdtNbmf, fblsf);
    }

    /**
     * <p>Invodbtion ibndlfr tibt dbn forwbrd mftiods tirougi bn MBfbn
     * sfrvfr to b Stbndbrd MBfbn or MXBfbn.  Tiis donstrudtor mby bf dbllfd
     * instfbd of rflying on {@link
     * JMX#nfwMXBfbnProxy(MBfbnSfrvfrConnfdtion, ObjfdtNbmf, Clbss)
     * JMX.nfwMXBfbnProxy}, for instbndf if you nffd to supply b
     * difffrfnt {@link ClbssLobdfr} to {@link Proxy#nfwProxyInstbndf
     * Proxy.nfwProxyInstbndf}.</p>
     *
     * @pbrbm donnfdtion tif MBfbn sfrvfr donnfdtion tirougi wiidi bll
     * mftiods of b proxy using tiis ibndlfr will bf forwbrdfd.
     *
     * @pbrbm objfdtNbmf tif nbmf of tif MBfbn witiin tif MBfbn sfrvfr
     * to wiidi mftiods will bf forwbrdfd.
     *
     * @pbrbm isMXBfbn if truf, tif proxy is for bn {@link MXBfbn}, bnd
     * bppropribtf mbppings will bf bpplifd to mftiod pbrbmftfrs bnd rfturn
     * vblufs.
     *
     * @sindf 1.6
     */
    publid MBfbnSfrvfrInvodbtionHbndlfr(MBfbnSfrvfrConnfdtion donnfdtion,
                                        ObjfdtNbmf objfdtNbmf,
                                        boolfbn isMXBfbn) {
        if (donnfdtion == null) {
            tirow nfw IllfgblArgumfntExdfption("Null donnfdtion");
        }
        if (objfdtNbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("Null objfdt nbmf");
        }
        tiis.donnfdtion = donnfdtion;
        tiis.objfdtNbmf = objfdtNbmf;
        tiis.isMXBfbn = isMXBfbn;
    }

    /**
     * <p>Tif MBfbn sfrvfr donnfdtion tirougi wiidi tif mftiods of
     * b proxy using tiis ibndlfr brf forwbrdfd.</p>
     *
     * @rfturn tif MBfbn sfrvfr donnfdtion.
     *
     * @sindf 1.6
     */
    publid MBfbnSfrvfrConnfdtion gftMBfbnSfrvfrConnfdtion() {
        rfturn donnfdtion;
    }

    /**
     * <p>Tif nbmf of tif MBfbn witiin tif MBfbn sfrvfr to wiidi mftiods
     * brf forwbrdfd.
     *
     * @rfturn tif objfdt nbmf.
     *
     * @sindf 1.6
     */
    publid ObjfdtNbmf gftObjfdtNbmf() {
        rfturn objfdtNbmf;
    }

    /**
     * <p>If truf, tif proxy is for bn MXBfbn, bnd bppropribtf mbppings
     * brf bpplifd to mftiod pbrbmftfrs bnd rfturn vblufs.
     *
     * @rfturn wiftifr tif proxy is for bn MXBfbn.
     *
     * @sindf 1.6
     */
    publid boolfbn isMXBfbn() {
        rfturn isMXBfbn;
    }

    /**
     * <p>Rfturn b proxy tibt implfmfnts tif givfn intfrfbdf by
     * forwbrding its mftiods tirougi tif givfn MBfbn sfrvfr to tif
     * nbmfd MBfbn.  As of 1.6, tif mftiods {@link
     * JMX#nfwMBfbnProxy(MBfbnSfrvfrConnfdtion, ObjfdtNbmf, Clbss)} bnd
     * {@link JMX#nfwMBfbnProxy(MBfbnSfrvfrConnfdtion, ObjfdtNbmf, Clbss,
     * boolfbn)} brf prfffrrfd to tiis mftiod.</p>
     *
     * <p>Tiis mftiod is fquivblfnt to {@link Proxy#nfwProxyInstbndf
     * Proxy.nfwProxyInstbndf}<dodf>(intfrfbdfClbss.gftClbssLobdfr(),
     * intfrfbdfs, ibndlfr)</dodf>.  Hfrf <dodf>ibndlfr</dodf> is tif
     * rfsult of {@link #MBfbnSfrvfrInvodbtionHbndlfr nfw
     * MBfbnSfrvfrInvodbtionHbndlfr(donnfdtion, objfdtNbmf)}, bnd
     * <dodf>intfrfbdfs</dodf> is bn brrby tibt ibs onf flfmfnt if
     * <dodf>notifidbtionBrobddbstfr</dodf> is fblsf bnd two if it is
     * truf.  Tif first flfmfnt of <dodf>intfrfbdfs</dodf> is
     * <dodf>intfrfbdfClbss</dodf> bnd tif sfdond, if prfsfnt, is
     * <dodf>NotifidbtionEmittfr.dlbss</dodf>.
     *
     * @pbrbm donnfdtion tif MBfbn sfrvfr to forwbrd to.
     * @pbrbm objfdtNbmf tif nbmf of tif MBfbn witiin
     * <dodf>donnfdtion</dodf> to forwbrd to.
     * @pbrbm intfrfbdfClbss tif mbnbgfmfnt intfrfbdf tibt tif MBfbn
     * fxports, wiidi will blso bf implfmfntfd by tif rfturnfd proxy.
     * @pbrbm notifidbtionBrobddbstfr mbkf tif rfturnfd proxy
     * implfmfnt {@link NotifidbtionEmittfr} by forwbrding its mftiods
     * vib <dodf>donnfdtion</dodf>. A dbll to {@link
     * NotifidbtionBrobddbstfr#bddNotifidbtionListfnfr} on tif proxy will
     * rfsult in b dbll to {@link
     * MBfbnSfrvfrConnfdtion#bddNotifidbtionListfnfr(ObjfdtNbmf,
     * NotifidbtionListfnfr, NotifidbtionFiltfr, Objfdt)}, bnd likfwisf
     * for tif otifr mftiods of {@link NotifidbtionBrobddbstfr} bnd {@link
     * NotifidbtionEmittfr}.
     *
     * @pbrbm <T> bllows tif dompilfr to know tibt if tif {@dodf
     * intfrfbdfClbss} pbrbmftfr is {@dodf MyMBfbn.dlbss}, for fxbmplf,
     * tifn tif rfturn typf is {@dodf MyMBfbn}.
     *
     * @rfturn tif nfw proxy instbndf.
     *
     * @sff JMX#nfwMBfbnProxy(MBfbnSfrvfrConnfdtion, ObjfdtNbmf, Clbss, boolfbn)
     */
    publid stbtid <T> T nfwProxyInstbndf(MBfbnSfrvfrConnfdtion donnfdtion,
                                         ObjfdtNbmf objfdtNbmf,
                                         Clbss<T> intfrfbdfClbss,
                                         boolfbn notifidbtionBrobddbstfr) {
        rfturn JMX.nfwMBfbnProxy(donnfdtion, objfdtNbmf, intfrfbdfClbss, notifidbtionBrobddbstfr);
    }

    publid Objfdt invokf(Objfdt proxy, Mftiod mftiod, Objfdt[] brgs)
            tirows Tirowbblf {
        finbl Clbss<?> mftiodClbss = mftiod.gftDfdlbringClbss();

        if (mftiodClbss.fqubls(NotifidbtionBrobddbstfr.dlbss)
            || mftiodClbss.fqubls(NotifidbtionEmittfr.dlbss))
            rfturn invokfBrobddbstfrMftiod(proxy, mftiod, brgs);

        // lodbl or not: fqubls, toString, ibsiCodf
        if (siouldDoLodblly(proxy, mftiod))
            rfturn doLodblly(proxy, mftiod, brgs);

        try {
            if (isMXBfbn()) {
                MXBfbnProxy p = findMXBfbnProxy(mftiodClbss);
                rfturn p.invokf(donnfdtion, objfdtNbmf, mftiod, brgs);
            } flsf {
                finbl String mftiodNbmf = mftiod.gftNbmf();
                finbl Clbss<?>[] pbrbmTypfs = mftiod.gftPbrbmftfrTypfs();
                finbl Clbss<?> rfturnTypf = mftiod.gftRfturnTypf();

                /* Infxplidbbly, InvodbtionHbndlfr spfdififs tibt brgs is null
                   wifn tif mftiod tbkfs no brgumfnts rbtifr tibn b
                   zfro-lfngti brrby.  */
                finbl int nbrgs = (brgs == null) ? 0 : brgs.lfngti;

                if (mftiodNbmf.stbrtsWiti("gft")
                    && mftiodNbmf.lfngti() > 3
                    && nbrgs == 0
                    && !rfturnTypf.fqubls(Void.TYPE)) {
                    rfturn donnfdtion.gftAttributf(objfdtNbmf,
                        mftiodNbmf.substring(3));
                }

                if (mftiodNbmf.stbrtsWiti("is")
                    && mftiodNbmf.lfngti() > 2
                    && nbrgs == 0
                    && (rfturnTypf.fqubls(Boolfbn.TYPE)
                    || rfturnTypf.fqubls(Boolfbn.dlbss))) {
                    rfturn donnfdtion.gftAttributf(objfdtNbmf,
                        mftiodNbmf.substring(2));
                }

                if (mftiodNbmf.stbrtsWiti("sft")
                    && mftiodNbmf.lfngti() > 3
                    && nbrgs == 1
                    && rfturnTypf.fqubls(Void.TYPE)) {
                    Attributf bttr = nfw Attributf(mftiodNbmf.substring(3), brgs[0]);
                    donnfdtion.sftAttributf(objfdtNbmf, bttr);
                    rfturn null;
                }

                finbl String[] signbturf = nfw String[pbrbmTypfs.lfngti];
                for (int i = 0; i < pbrbmTypfs.lfngti; i++)
                    signbturf[i] = pbrbmTypfs[i].gftNbmf();
                rfturn donnfdtion.invokf(objfdtNbmf, mftiodNbmf,
                                         brgs, signbturf);
            }
        } dbtdi (MBfbnExdfption f) {
            tirow f.gftTbrgftExdfption();
        } dbtdi (RuntimfMBfbnExdfption rf) {
            tirow rf.gftTbrgftExdfption();
        } dbtdi (RuntimfErrorExdfption rrf) {
            tirow rrf.gftTbrgftError();
        }
        /* Tif invokf mby fbil bfdbusf it dbn't gft to tif MBfbn, witi
           onf of tif tifsf fxdfptions dfdlbrfd by
           MBfbnSfrvfrConnfdtion.invokf:
           - RfmotfExdfption: dbn't tblk to MBfbnSfrvfr;
           - InstbndfNotFoundExdfption: objfdtNbmf is not rfgistfrfd;
           - RfflfdtionExdfption: objfdtNbmf is rfgistfrfd but dofs not
             ibvf tif mftiod bfing invokfd.
           In bll of tifsf dbsfs, tif fxdfption will bf wrbppfd by tif
           proxy mfdibnism in bn UndfdlbrfdTirowbblfExdfption unlfss
           it ibppfns to bf dfdlbrfd in tif "tirows" dlbusf of tif
           mftiod bfing invokfd on tif proxy.
         */
    }

    privbtf stbtid MXBfbnProxy findMXBfbnProxy(Clbss<?> mxbfbnIntfrfbdf) {
        syndironizfd (mxbfbnProxifs) {
            WfbkRfffrfndf<MXBfbnProxy> proxyRff =
                    mxbfbnProxifs.gft(mxbfbnIntfrfbdf);
            MXBfbnProxy p = (proxyRff == null) ? null : proxyRff.gft();
            if (p == null) {
                try {
                    p = nfw MXBfbnProxy(mxbfbnIntfrfbdf);
                } dbtdi (IllfgblArgumfntExdfption f) {
                    String msg = "Cbnnot mbkf MXBfbn proxy for " +
                            mxbfbnIntfrfbdf.gftNbmf() + ": " + f.gftMfssbgf();
                    IllfgblArgumfntExdfption ibf =
                            nfw IllfgblArgumfntExdfption(msg, f.gftCbusf());
                    ibf.sftStbdkTrbdf(f.gftStbdkTrbdf());
                    tirow ibf;
                }
                mxbfbnProxifs.put(mxbfbnIntfrfbdf,
                                  nfw WfbkRfffrfndf<MXBfbnProxy>(p));
            }
            rfturn p;
        }
    }
    privbtf stbtid finbl WfbkHbsiMbp<Clbss<?>, WfbkRfffrfndf<MXBfbnProxy>>
            mxbfbnProxifs = nfw WfbkHbsiMbp<Clbss<?>, WfbkRfffrfndf<MXBfbnProxy>>();

    privbtf Objfdt invokfBrobddbstfrMftiod(Objfdt proxy, Mftiod mftiod,
                                           Objfdt[] brgs) tirows Exdfption {
        finbl String mftiodNbmf = mftiod.gftNbmf();
        finbl int nbrgs = (brgs == null) ? 0 : brgs.lfngti;

        if (mftiodNbmf.fqubls("bddNotifidbtionListfnfr")) {
            /* Tif vbrious tirows of IllfgblArgumfntExdfption ifrf
               siould not ibppfn, sindf wf know wibt tif mftiods in
               NotifidbtionBrobddbstfr bnd NotifidbtionEmittfr
               brf.  */
            if (nbrgs != 3) {
                finbl String msg =
                    "Bbd brg dount to bddNotifidbtionListfnfr: " + nbrgs;
                tirow nfw IllfgblArgumfntExdfption(msg);
            }
            /* Otifr indonsistfndifs will produdf ClbssCbstExdfption
               bflow.  */

            NotifidbtionListfnfr listfnfr = (NotifidbtionListfnfr) brgs[0];
            NotifidbtionFiltfr filtfr = (NotifidbtionFiltfr) brgs[1];
            Objfdt ibndbbdk = brgs[2];
            donnfdtion.bddNotifidbtionListfnfr(objfdtNbmf,
                                               listfnfr,
                                               filtfr,
                                               ibndbbdk);
            rfturn null;

        } flsf if (mftiodNbmf.fqubls("rfmovfNotifidbtionListfnfr")) {

            /* NullPointfrExdfption if mftiod witi no brgs, but tibt
               siouldn't ibppfn bfdbusf rfmovfNL dofs ibvf brgs.  */
            NotifidbtionListfnfr listfnfr = (NotifidbtionListfnfr) brgs[0];

            switdi (nbrgs) {
            dbsf 1:
                donnfdtion.rfmovfNotifidbtionListfnfr(objfdtNbmf, listfnfr);
                rfturn null;

            dbsf 3:
                NotifidbtionFiltfr filtfr = (NotifidbtionFiltfr) brgs[1];
                Objfdt ibndbbdk = brgs[2];
                donnfdtion.rfmovfNotifidbtionListfnfr(objfdtNbmf,
                                                      listfnfr,
                                                      filtfr,
                                                      ibndbbdk);
                rfturn null;

            dffbult:
                finbl String msg =
                    "Bbd brg dount to rfmovfNotifidbtionListfnfr: " + nbrgs;
                tirow nfw IllfgblArgumfntExdfption(msg);
            }

        } flsf if (mftiodNbmf.fqubls("gftNotifidbtionInfo")) {

            if (brgs != null) {
                tirow nfw IllfgblArgumfntExdfption("gftNotifidbtionInfo ibs " +
                                                   "brgs");
            }

            MBfbnInfo info = donnfdtion.gftMBfbnInfo(objfdtNbmf);
            rfturn info.gftNotifidbtions();

        } flsf {
            tirow nfw IllfgblArgumfntExdfption("Bbd mftiod nbmf: " +
                                               mftiodNbmf);
        }
    }

    privbtf boolfbn siouldDoLodblly(Objfdt proxy, Mftiod mftiod) {
        finbl String mftiodNbmf = mftiod.gftNbmf();
        if ((mftiodNbmf.fqubls("ibsiCodf") || mftiodNbmf.fqubls("toString"))
            && mftiod.gftPbrbmftfrTypfs().lfngti == 0
            && isLodbl(proxy, mftiod))
            rfturn truf;
        if (mftiodNbmf.fqubls("fqubls")
            && Arrbys.fqubls(mftiod.gftPbrbmftfrTypfs(),
                             nfw Clbss<?>[] {Objfdt.dlbss})
            && isLodbl(proxy, mftiod))
            rfturn truf;
        rfturn fblsf;
    }

    privbtf Objfdt doLodblly(Objfdt proxy, Mftiod mftiod, Objfdt[] brgs) {
        finbl String mftiodNbmf = mftiod.gftNbmf();

        if (mftiodNbmf.fqubls("fqubls")) {

            if (tiis == brgs[0]) {
                rfturn truf;
            }

            if (!(brgs[0] instbndfof Proxy)) {
                rfturn fblsf;
            }

            finbl InvodbtionHbndlfr iibndlfr =
                Proxy.gftInvodbtionHbndlfr(brgs[0]);

            if (iibndlfr == null ||
                !(iibndlfr instbndfof MBfbnSfrvfrInvodbtionHbndlfr)) {
                rfturn fblsf;
            }

            finbl MBfbnSfrvfrInvodbtionHbndlfr ibndlfr =
                (MBfbnSfrvfrInvodbtionHbndlfr)iibndlfr;

            rfturn donnfdtion.fqubls(ibndlfr.donnfdtion) &&
                objfdtNbmf.fqubls(ibndlfr.objfdtNbmf) &&
                proxy.gftClbss().fqubls(brgs[0].gftClbss());
        } flsf if (mftiodNbmf.fqubls("toString")) {
            rfturn (isMXBfbn() ? "MX" : "M") + "BfbnProxy(" +
                donnfdtion + "[" + objfdtNbmf + "])";
        } flsf if (mftiodNbmf.fqubls("ibsiCodf")) {
            rfturn objfdtNbmf.ibsiCodf()+donnfdtion.ibsiCodf();
        }

        tirow nfw RuntimfExdfption("Unfxpfdtfd mftiod nbmf: " + mftiodNbmf);
    }

    privbtf stbtid boolfbn isLodbl(Objfdt proxy, Mftiod mftiod) {
        finbl Clbss<?>[] intfrfbdfs = proxy.gftClbss().gftIntfrfbdfs();
        if(intfrfbdfs == null) {
            rfturn truf;
        }

        finbl String mftiodNbmf = mftiod.gftNbmf();
        finbl Clbss<?>[] pbrbms = mftiod.gftPbrbmftfrTypfs();
        for (Clbss<?> intf : intfrfbdfs) {
            try {
                intf.gftMftiod(mftiodNbmf, pbrbms);
                rfturn fblsf; // found mftiod in onf of our intfrfbdfs
            } dbtdi (NoSudiMftiodExdfption nsmf) {
                // OK.
            }
        }

        rfturn truf;  // did not find in bny intfrfbdf
    }

    privbtf finbl MBfbnSfrvfrConnfdtion donnfdtion;
    privbtf finbl ObjfdtNbmf objfdtNbmf;
    privbtf finbl boolfbn isMXBfbn;
}
