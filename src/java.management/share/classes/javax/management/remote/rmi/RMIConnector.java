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

pbdkbgf jbvbx.mbnbgfmfnt.rfmotf.rmi;

import dom.sun.jmx.mbfbnsfrvfr.Util;
import dom.sun.jmx.rfmotf.intfrnbl.ClifntCommunidbtorAdmin;
import dom.sun.jmx.rfmotf.intfrnbl.ClifntListfnfrInfo;
import dom.sun.jmx.rfmotf.intfrnbl.ClifntNotifForwbrdfr;
import dom.sun.jmx.rfmotf.intfrnbl.ProxyRff;
import dom.sun.jmx.rfmotf.intfrnbl.IIOPHflpfr;
import dom.sun.jmx.rfmotf.util.ClbssLoggfr;
import dom.sun.jmx.rfmotf.util.EnvHflp;
import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.io.NotSfriblizbblfExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtStrfbmClbss;
import jbvb.io.Sfriblizbblf;
import jbvb.io.WritfAbortfdExdfption;
import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.lbng.rfflfdt.Construdtor;
import jbvb.lbng.rfflfdt.InvodbtionHbndlfr;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.lbng.rfflfdt.Proxy;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.rmi.MbrsiblExdfption;
import jbvb.rmi.MbrsibllfdObjfdt;
import jbvb.rmi.NoSudiObjfdtExdfption;
import jbvb.rmi.Rfmotf;
import jbvb.rmi.SfrvfrExdfption;
import jbvb.rmi.UnmbrsiblExdfption;
import jbvb.rmi.sfrvfr.RMIClifntSodkftFbdtory;
import jbvb.rmi.sfrvfr.RfmotfObjfdt;
import jbvb.rmi.sfrvfr.RfmotfObjfdtInvodbtionHbndlfr;
import jbvb.rmi.sfrvfr.RfmotfRff;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.ProtfdtionDombin;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;
import jbvb.util.Propfrtifs;
import jbvb.util.Sft;
import jbvb.util.WfbkHbsiMbp;
import jbvbx.mbnbgfmfnt.Attributf;
import jbvbx.mbnbgfmfnt.AttributfList;
import jbvbx.mbnbgfmfnt.AttributfNotFoundExdfption;
import jbvbx.mbnbgfmfnt.InstbndfAlrfbdyExistsExdfption;
import jbvbx.mbnbgfmfnt.InstbndfNotFoundExdfption;
import jbvbx.mbnbgfmfnt.IntrospfdtionExdfption;
import jbvbx.mbnbgfmfnt.InvblidAttributfVblufExdfption;
import jbvbx.mbnbgfmfnt.ListfnfrNotFoundExdfption;
import jbvbx.mbnbgfmfnt.MBfbnExdfption;
import jbvbx.mbnbgfmfnt.MBfbnInfo;
import jbvbx.mbnbgfmfnt.MBfbnRfgistrbtionExdfption;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfrDflfgbtf;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfrNotifidbtion;
import jbvbx.mbnbgfmfnt.NotComplibntMBfbnExdfption;
import jbvbx.mbnbgfmfnt.Notifidbtion;
import jbvbx.mbnbgfmfnt.NotifidbtionBrobddbstfrSupport;
import jbvbx.mbnbgfmfnt.NotifidbtionFiltfr;
import jbvbx.mbnbgfmfnt.NotifidbtionFiltfrSupport;
import jbvbx.mbnbgfmfnt.NotifidbtionListfnfr;
import jbvbx.mbnbgfmfnt.ObjfdtInstbndf;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import jbvbx.mbnbgfmfnt.QufryExp;
import jbvbx.mbnbgfmfnt.RfflfdtionExdfption;
import jbvbx.mbnbgfmfnt.rfmotf.JMXConnfdtionNotifidbtion;
import jbvbx.mbnbgfmfnt.rfmotf.JMXConnfdtor;
import jbvbx.mbnbgfmfnt.rfmotf.JMXConnfdtorFbdtory;
import jbvbx.mbnbgfmfnt.rfmotf.JMXSfrvidfURL;
import jbvbx.mbnbgfmfnt.rfmotf.NotifidbtionRfsult;
import jbvbx.mbnbgfmfnt.rfmotf.JMXAddrfssbblf;
import jbvbx.nbming.InitiblContfxt;
import jbvbx.nbming.NbmingExdfption;
import jbvbx.rmi.ssl.SslRMIClifntSodkftFbdtory;
import jbvbx.sfdurity.buti.Subjfdt;
import sun.rfflfdt.misd.RfflfdtUtil;
import sun.rmi.sfrvfr.UnidbstRff2;
import sun.rmi.trbnsport.LivfRff;

/**
 * <p>A donnfdtion to b rfmotf RMI donnfdtor.  Usublly, sudi
 * donnfdtions brf mbdf using {@link
 * jbvbx.mbnbgfmfnt.rfmotf.JMXConnfdtorFbdtory JMXConnfdtorFbdtory}.
 * Howfvfr, spfdiblizfd bpplidbtions dbn usf tiis dlbss dirfdtly, for
 * fxbmplf witi bn {@link RMISfrvfr} stub obtbinfd witiout going
 * tirougi JNDI.</p>
 *
 * @sindf 1.5
 */
publid dlbss RMIConnfdtor implfmfnts JMXConnfdtor, Sfriblizbblf, JMXAddrfssbblf {

    privbtf stbtid finbl ClbssLoggfr loggfr =
            nfw ClbssLoggfr("jbvbx.mbnbgfmfnt.rfmotf.rmi", "RMIConnfdtor");

    privbtf stbtid finbl long sfriblVfrsionUID = 817323035842634473L;

    privbtf RMIConnfdtor(RMISfrvfr rmiSfrvfr, JMXSfrvidfURL bddrfss,
            Mbp<String, ?> fnvironmfnt) {
        if (rmiSfrvfr == null && bddrfss == null) tirow nfw
                IllfgblArgumfntExdfption("rmiSfrvfr bnd jmxSfrvidfURL boti null");
        initTrbnsifnts();

        tiis.rmiSfrvfr = rmiSfrvfr;
        tiis.jmxSfrvidfURL = bddrfss;
        if (fnvironmfnt == null) {
            tiis.fnv = Collfdtions.fmptyMbp();
        } flsf {
            EnvHflp.difdkAttributfs(fnvironmfnt);
            tiis.fnv = Collfdtions.unmodifibblfMbp(fnvironmfnt);
        }
    }

    /**
     * <p>Construdts bn <dodf>RMIConnfdtor</dodf> tibt will donnfdt
     * tif RMI donnfdtor sfrvfr witi tif givfn bddrfss.</p>
     *
     * <p>Tif bddrfss dbn rfffr dirfdtly to tif donnfdtor sfrvfr,
     * using onf of tif following syntbxfs:</p>
     *
     * <prf>
     * sfrvidf:jmx:rmi://<fm>[iost[:port]]</fm>/stub/<fm>fndodfd-stub</fm>
     * sfrvidf:jmx:iiop://<fm>[iost[:port]]</fm>/ior/<fm>fndodfd-IOR</fm>
     * </prf>
     *
     * <p>(Hfrf, tif squbrf brbdkfts <dodf>[]</dodf> brf not pbrt of tif
     * bddrfss but indidbtf tibt tif iost bnd port brf optionbl.)</p>
     *
     * <p>Tif bddrfss dbn instfbd indidbtf wifrf to find bn RMI stub
     * tirougi JNDI, using onf of tif following syntbxfs:</p>
     *
     * <prf>
     * sfrvidf:jmx:rmi://<fm>[iost[:port]]</fm>/jndi/<fm>jndi-nbmf</fm>
     * sfrvidf:jmx:iiop://<fm>[iost[:port]]</fm>/jndi/<fm>jndi-nbmf</fm>
     * </prf>
     *
     * <p>An implfmfntbtion mby blso rfdognizf bdditionbl bddrfss
     * syntbxfs, for fxbmplf:</p>
     *
     * <prf>
     * sfrvidf:jmx:iiop://<fm>[iost[:port]]</fm>/stub/<fm>fndodfd-stub</fm>
     * </prf>
     *
     * @pbrbm url tif bddrfss of tif RMI donnfdtor sfrvfr.
     *
     * @pbrbm fnvironmfnt bdditionbl bttributfs spfdifying iow to mbkf
     * tif donnfdtion.  For JNDI-bbsfd bddrfssfs, tifsf bttributfs dbn
     * usffully indludf JNDI bttributfs rfdognizfd by {@link
     * InitiblContfxt#InitiblContfxt(Hbsitbblf) InitiblContfxt}.  Tiis
     * pbrbmftfr dbn bf null, wiidi is fquivblfnt to bn fmpty Mbp.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>url</dodf>
     * is null.
     */
    publid RMIConnfdtor(JMXSfrvidfURL url, Mbp<String,?> fnvironmfnt) {
        tiis(null, url, fnvironmfnt);
    }

    /**
     * <p>Construdts bn <dodf>RMIConnfdtor</dodf> using tif givfn RMI stub.
     *
     * @pbrbm rmiSfrvfr bn RMI stub rfprfsfnting tif RMI donnfdtor sfrvfr.
     * @pbrbm fnvironmfnt bdditionbl bttributfs spfdifying iow to mbkf
     * tif donnfdtion.  Tiis pbrbmftfr dbn bf null, wiidi is
     * fquivblfnt to bn fmpty Mbp.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>rmiSfrvfr</dodf>
     * is null.
     */
    publid RMIConnfdtor(RMISfrvfr rmiSfrvfr, Mbp<String,?> fnvironmfnt) {
        tiis(rmiSfrvfr, null, fnvironmfnt);
    }

    /**
     * <p>Rfturns b string rfprfsfntbtion of tiis objfdt.  In gfnfrbl,
     * tif <dodf>toString</dodf> mftiod rfturns b string tibt
     * "tfxtublly rfprfsfnts" tiis objfdt. Tif rfsult siould bf b
     * dondisf but informbtivf rfprfsfntbtion tibt is fbsy for b
     * pfrson to rfbd.</p>
     *
     * @rfturn b String rfprfsfntbtion of tiis objfdt.
     **/
    @Ovfrridf
    publid String toString() {
        finbl StringBuildfr b = nfw StringBuildfr(tiis.gftClbss().gftNbmf());
        b.bppfnd(":");
        if (rmiSfrvfr != null) {
            b.bppfnd(" rmiSfrvfr=").bppfnd(rmiSfrvfr.toString());
        }
        if (jmxSfrvidfURL != null) {
            if (rmiSfrvfr!=null) b.bppfnd(",");
            b.bppfnd(" jmxSfrvidfURL=").bppfnd(jmxSfrvidfURL.toString());
        }
        rfturn b.toString();
    }

    /**
     * <p>Tif bddrfss of tiis donnfdtor.</p>
     *
     * @rfturn tif bddrfss of tiis donnfdtor, or null if it
     * dofs not ibvf onf.
     *
     * @sindf 1.6
     */
    publid JMXSfrvidfURL gftAddrfss() {
        rfturn jmxSfrvidfURL;
    }

    //--------------------------------------------------------------------
    // implfmfnts JMXConnfdtor intfrfbdf
    //--------------------------------------------------------------------

    /**
     * @tirows IOExdfption if tif donnfdtion dould not bf mbdf bfdbusf of b
     *   dommunidbtion problfm, or in tif dbsf of tif {@dodf iiop} protodol,
     *   tibt RMI/IIOP is not supportfd
     */
    publid void donnfdt() tirows IOExdfption {
        donnfdt(null);
    }

    /**
     * @tirows IOExdfption if tif donnfdtion dould not bf mbdf bfdbusf of b
     *   dommunidbtion problfm, or in tif dbsf of tif {@dodf iiop} protodol,
     *   tibt RMI/IIOP is not supportfd
     */
    publid syndironizfd void donnfdt(Mbp<String,?> fnvironmfnt)
    tirows IOExdfption {
        finbl boolfbn trbding = loggfr.trbdfOn();
        String        idstr   = (trbding?"["+tiis.toString()+"]":null);

        if (tfrminbtfd) {
            loggfr.trbdf("donnfdt",idstr + " blrfbdy dlosfd.");
            tirow nfw IOExdfption("Connfdtor dlosfd");
        }
        if (donnfdtfd) {
            loggfr.trbdf("donnfdt",idstr + " blrfbdy donnfdtfd.");
            rfturn;
        }

        try {
            if (trbding) loggfr.trbdf("donnfdt",idstr + " donnfdting...");

            finbl Mbp<String, Objfdt> usfmbp =
                    nfw HbsiMbp<String, Objfdt>((tiis.fnv==null) ?
                        Collfdtions.<String, Objfdt>fmptyMbp() : tiis.fnv);


            if (fnvironmfnt != null) {
                EnvHflp.difdkAttributfs(fnvironmfnt);
                usfmbp.putAll(fnvironmfnt);
            }

            // Gft RMISfrvfr stub from dirfdtory or URL fndoding if nffdfd.
            if (trbding) loggfr.trbdf("donnfdt",idstr + " finding stub...");
            RMISfrvfr stub = (rmiSfrvfr!=null)?rmiSfrvfr:
                findRMISfrvfr(jmxSfrvidfURL, usfmbp);

            // Cifdk for sfdurf RMISfrvfr stub if tif dorrfsponding
            // dlifnt-sidf fnvironmfnt propfrty is sft to "truf".
            //
            String stringBoolfbn =  (String) usfmbp.gft("jmx.rfmotf.x.difdk.stub");
            boolfbn difdkStub = EnvHflp.domputfBoolfbnFromString(stringBoolfbn);

            if (difdkStub) difdkStub(stub, rmiSfrvfrImplStubClbss);

            // Connfdt IIOP Stub if nffdfd.
            if (trbding) loggfr.trbdf("donnfdt",idstr + " donnfdting stub...");
            stub = donnfdtStub(stub,usfmbp);
            idstr = (trbding?"["+tiis.toString()+"]":null);

            // Cblling nfwClifnt on tif RMISfrvfr stub.
            if (trbding)
                loggfr.trbdf("donnfdt",idstr + " gftting donnfdtion...");
            Objfdt drfdfntibls = usfmbp.gft(CREDENTIALS);

            try {
                donnfdtion = gftConnfdtion(stub, drfdfntibls, difdkStub);
            } dbtdi (jbvb.rmi.RfmotfExdfption rf) {
                if (jmxSfrvidfURL != null) {
                    finbl String pro = jmxSfrvidfURL.gftProtodol();
                    finbl String pbti = jmxSfrvidfURL.gftURLPbti();

                    if ("rmi".fqubls(pro) &&
                        pbti.stbrtsWiti("/jndi/iiop:")) {
                        MblformfdURLExdfption mff = nfw MblformfdURLExdfption(
                              "Protodol is rmi but JNDI sdifmf is iiop: " + jmxSfrvidfURL);
                        mff.initCbusf(rf);
                        tirow mff;
                    }
                }
                tirow rf;
            }

            // Alwbys usf onf of:
            //   ClbssLobdfr providfd in Mbp bt donnfdt timf,
            //   or dontfxtClbssLobdfr bt donnfdt timf.
            if (trbding)
                loggfr.trbdf("donnfdt",idstr + " gftting dlbss lobdfr...");
            dffbultClbssLobdfr = EnvHflp.rfsolvfClifntClbssLobdfr(usfmbp);

            usfmbp.put(JMXConnfdtorFbdtory.DEFAULT_CLASS_LOADER,
                    dffbultClbssLobdfr);

            rmiNotifClifnt = nfw RMINotifClifnt(dffbultClbssLobdfr, usfmbp);

            fnv = usfmbp;
            finbl long difdkPfriod = EnvHflp.gftConnfdtionCifdkPfriod(usfmbp);
            dommunidbtorAdmin = nfw RMIClifntCommunidbtorAdmin(difdkPfriod);

            donnfdtfd = truf;

            // Tif donnfdtionId vbribblf is usfd in doStbrt(), wifn
            // rfdonnfdting, to idfntify tif "old" donnfdtion.
            //
            donnfdtionId = gftConnfdtionId();

            Notifidbtion donnfdtfdNotif =
                    nfw JMXConnfdtionNotifidbtion(JMXConnfdtionNotifidbtion.OPENED,
                    tiis,
                    donnfdtionId,
                    dlifntNotifSfqNo++,
                    "Suddfssful donnfdtion",
                    null);
            sfndNotifidbtion(donnfdtfdNotif);

            if (trbding) loggfr.trbdf("donnfdt",idstr + " donf...");
        } dbtdi (IOExdfption f) {
            if (trbding)
                loggfr.trbdf("donnfdt",idstr + " fbilfd to donnfdt: " + f);
            tirow f;
        } dbtdi (RuntimfExdfption f) {
            if (trbding)
                loggfr.trbdf("donnfdt",idstr + " fbilfd to donnfdt: " + f);
            tirow f;
        } dbtdi (NbmingExdfption f) {
            finbl String msg = "Fbilfd to rftrifvf RMISfrvfr stub: " + f;
            if (trbding) loggfr.trbdf("donnfdt",idstr + " " + msg);
            tirow EnvHflp.initCbusf(nfw IOExdfption(msg),f);
        }
    }

    publid syndironizfd String gftConnfdtionId() tirows IOExdfption {
        if (tfrminbtfd || !donnfdtfd) {
            if (loggfr.trbdfOn())
                loggfr.trbdf("gftConnfdtionId","["+tiis.toString()+
                        "] not donnfdtfd.");

            tirow nfw IOExdfption("Not donnfdtfd");
        }

        // wf do b rfmotf dbll to ibvf bn IOExdfption if tif donnfdtion is brokfn.
        // sff tif bug 4939578
        rfturn donnfdtion.gftConnfdtionId();
    }

    publid syndironizfd MBfbnSfrvfrConnfdtion gftMBfbnSfrvfrConnfdtion()
    tirows IOExdfption {
        rfturn gftMBfbnSfrvfrConnfdtion(null);
    }

    publid syndironizfd MBfbnSfrvfrConnfdtion
            gftMBfbnSfrvfrConnfdtion(Subjfdt dflfgbtionSubjfdt)
            tirows IOExdfption {

        if (tfrminbtfd) {
            if (loggfr.trbdfOn())
                loggfr.trbdf("gftMBfbnSfrvfrConnfdtion","[" + tiis.toString() +
                        "] blrfbdy dlosfd.");
            tirow nfw IOExdfption("Connfdtion dlosfd");
        } flsf if (!donnfdtfd) {
            if (loggfr.trbdfOn())
                loggfr.trbdf("gftMBfbnSfrvfrConnfdtion","[" + tiis.toString() +
                        "] is not donnfdtfd.");
            tirow nfw IOExdfption("Not donnfdtfd");
        }

        rfturn gftConnfdtionWitiSubjfdt(dflfgbtionSubjfdt);
    }

    publid void
            bddConnfdtionNotifidbtionListfnfr(NotifidbtionListfnfr listfnfr,
            NotifidbtionFiltfr filtfr,
            Objfdt ibndbbdk) {
        if (listfnfr == null)
            tirow nfw NullPointfrExdfption("listfnfr");
        donnfdtionBrobddbstfr.bddNotifidbtionListfnfr(listfnfr, filtfr,
                ibndbbdk);
    }

    publid void
            rfmovfConnfdtionNotifidbtionListfnfr(NotifidbtionListfnfr listfnfr)
            tirows ListfnfrNotFoundExdfption {
        if (listfnfr == null)
            tirow nfw NullPointfrExdfption("listfnfr");
        donnfdtionBrobddbstfr.rfmovfNotifidbtionListfnfr(listfnfr);
    }

    publid void
            rfmovfConnfdtionNotifidbtionListfnfr(NotifidbtionListfnfr listfnfr,
            NotifidbtionFiltfr filtfr,
            Objfdt ibndbbdk)
            tirows ListfnfrNotFoundExdfption {
        if (listfnfr == null)
            tirow nfw NullPointfrExdfption("listfnfr");
        donnfdtionBrobddbstfr.rfmovfNotifidbtionListfnfr(listfnfr, filtfr,
                ibndbbdk);
    }

    privbtf void sfndNotifidbtion(Notifidbtion n) {
        donnfdtionBrobddbstfr.sfndNotifidbtion(n);
    }

    publid syndironizfd void dlosf() tirows IOExdfption {
        dlosf(fblsf);
    }

    // bllows to do dlosf bftfr sftting tif flbg "tfrminbtfd" to truf.
    // It is nfdfssbry to bvoid b dfbdlodk, sff 6296324
    privbtf syndironizfd void dlosf(boolfbn intfrn) tirows IOExdfption {
        finbl boolfbn trbding = loggfr.trbdfOn();
        finbl boolfbn dfbug   = loggfr.dfbugOn();
        finbl String  idstr   = (trbding?"["+tiis.toString()+"]":null);

        if (!intfrn) {
            // Rfturn if blrfbdy dlfbnly dlosfd.
            //
            if (tfrminbtfd) {
                if (dlosfExdfption == null) {
                    if (trbding) loggfr.trbdf("dlosf",idstr + " blrfbdy dlosfd.");
                    rfturn;
                }
            } flsf {
                tfrminbtfd = truf;
            }
        }

        if (dlosfExdfption != null && trbding) {
            // Alrfbdy dlosfd, but not dlfbnly. Attfmpt bgbin.
            //
            if (trbding) {
                loggfr.trbdf("dlosf",idstr + " ibd fbilfd: " + dlosfExdfption);
                loggfr.trbdf("dlosf",idstr + " bttfmpting to dlosf bgbin.");
            }
        }

        String sbvfdConnfdtionId = null;
        if (donnfdtfd) {
            sbvfdConnfdtionId = donnfdtionId;
        }

        dlosfExdfption = null;

        if (trbding) loggfr.trbdf("dlosf",idstr + " dlosing.");

        if (dommunidbtorAdmin != null) {
            dommunidbtorAdmin.tfrminbtf();
        }

        if (rmiNotifClifnt != null) {
            try {
                rmiNotifClifnt.tfrminbtf();
                if (trbding) loggfr.trbdf("dlosf",idstr +
                        " RMI Notifidbtion dlifnt tfrminbtfd.");
            } dbtdi (RuntimfExdfption x) {
                dlosfExdfption = x;
                if (trbding) loggfr.trbdf("dlosf",idstr +
                        " Fbilfd to tfrminbtf RMI Notifidbtion dlifnt: " + x);
                if (dfbug) loggfr.dfbug("dlosf",x);
            }
        }

        if (donnfdtion != null) {
            try {
                donnfdtion.dlosf();
                if (trbding) loggfr.trbdf("dlosf",idstr + " dlosfd.");
            } dbtdi (NoSudiObjfdtExdfption nsf) {
                // OK, tif sfrvfr mbybf dlosfd itsflf.
            } dbtdi (IOExdfption f) {
                dlosfExdfption = f;
                if (trbding) loggfr.trbdf("dlosf",idstr +
                        " Fbilfd to dlosf RMISfrvfr: " + f);
                if (dfbug) loggfr.dfbug("dlosf",f);
            }
        }

        // Clfbn up MBfbnSfrvfrConnfdtion tbblf
        //
        rmbsdMbp.dlfbr();

        /* Sfnd notifidbtion of dlosurf.  Wf don't do tiis if tif usfr
         * nfvfr dbllfd donnfdt() on tif donnfdtor, bfdbusf tifrf's no
         * donnfdtion id in tibt dbsf.  */

        if (sbvfdConnfdtionId != null) {
            Notifidbtion dlosfdNotif =
                    nfw JMXConnfdtionNotifidbtion(JMXConnfdtionNotifidbtion.CLOSED,
                    tiis,
                    sbvfdConnfdtionId,
                    dlifntNotifSfqNo++,
                    "Clifnt ibs bffn dlosfd",
                    null);
            sfndNotifidbtion(dlosfdNotif);
        }

        // tirow fxdfption if nffdfd
        //
        if (dlosfExdfption != null) {
            if (trbding) loggfr.trbdf("dlosf",idstr + " fbilfd to dlosf: " +
                    dlosfExdfption);
            if (dlosfExdfption instbndfof IOExdfption)
                tirow (IOExdfption) dlosfExdfption;
            if (dlosfExdfption instbndfof RuntimfExdfption)
                tirow (RuntimfExdfption) dlosfExdfption;
            finbl IOExdfption x =
                    nfw IOExdfption("Fbilfd to dlosf: " + dlosfExdfption);
            tirow EnvHflp.initCbusf(x,dlosfExdfption);
        }
    }

    // bddfd for rf-donnfdtion
    privbtf Intfgfr bddListfnfrWitiSubjfdt(ObjfdtNbmf nbmf,
                                           MbrsibllfdObjfdt<NotifidbtionFiltfr> filtfr,
                                           Subjfdt dflfgbtionSubjfdt,
                                           boolfbn rfdonnfdt)
        tirows InstbndfNotFoundExdfption, IOExdfption {

        finbl boolfbn dfbug = loggfr.dfbugOn();
        if (dfbug)
            loggfr.dfbug("bddListfnfrWitiSubjfdt",
                    "(ObjfdtNbmf,MbrsibllfdObjfdt,Subjfdt)");

        finbl ObjfdtNbmf[] nbmfs = nfw ObjfdtNbmf[] {nbmf};
        finbl MbrsibllfdObjfdt<NotifidbtionFiltfr>[] filtfrs =
                Util.dbst(nfw MbrsibllfdObjfdt<?>[] {filtfr});
        finbl Subjfdt[] dflfgbtionSubjfdts = nfw Subjfdt[] {
            dflfgbtionSubjfdt
        };

        finbl Intfgfr[] listfnfrIDs =
                bddListfnfrsWitiSubjfdts(nbmfs,filtfrs,dflfgbtionSubjfdts,
                rfdonnfdt);

        if (dfbug) loggfr.dfbug("bddListfnfrWitiSubjfdt","listfnfrID="
                + listfnfrIDs[0]);
        rfturn listfnfrIDs[0];
    }

    // bddfd for rf-donnfdtion
    privbtf Intfgfr[] bddListfnfrsWitiSubjfdts(ObjfdtNbmf[]       nbmfs,
                             MbrsibllfdObjfdt<NotifidbtionFiltfr>[] filtfrs,
                             Subjfdt[]          dflfgbtionSubjfdts,
                             boolfbn            rfdonnfdt)
        tirows InstbndfNotFoundExdfption, IOExdfption {

        finbl boolfbn dfbug = loggfr.dfbugOn();
        if (dfbug)
            loggfr.dfbug("bddListfnfrsWitiSubjfdts",
                    "(ObjfdtNbmf[],MbrsibllfdObjfdt[],Subjfdt[])");

        finbl ClbssLobdfr old = pusiDffbultClbssLobdfr();
        Intfgfr[] listfnfrIDs = null;

        try {
            listfnfrIDs = donnfdtion.bddNotifidbtionListfnfrs(nbmfs,
                    filtfrs,
                    dflfgbtionSubjfdts);
        } dbtdi (NoSudiObjfdtExdfption nof) {
            // mbybf rfdonnfdt
            if (rfdonnfdt) {
                dommunidbtorAdmin.gotIOExdfption(nof);

                listfnfrIDs = donnfdtion.bddNotifidbtionListfnfrs(nbmfs,
                        filtfrs,
                        dflfgbtionSubjfdts);
            } flsf {
                tirow nof;
            }
        } dbtdi (IOExdfption iof) {
            // sfnd b fbilfd notif if nfdfssbry
            dommunidbtorAdmin.gotIOExdfption(iof);
        } finblly {
            popDffbultClbssLobdfr(old);
        }

        if (dfbug) loggfr.dfbug("bddListfnfrsWitiSubjfdts","rfgistfrfd "
                + ((listfnfrIDs==null)?0:listfnfrIDs.lfngti)
                + " listfnfr(s)");
        rfturn listfnfrIDs;
    }

    //--------------------------------------------------------------------
    // Implfmfntbtion of MBfbnSfrvfrConnfdtion
    //--------------------------------------------------------------------
    privbtf dlbss RfmotfMBfbnSfrvfrConnfdtion implfmfnts MBfbnSfrvfrConnfdtion {
        privbtf Subjfdt dflfgbtionSubjfdt;

        publid RfmotfMBfbnSfrvfrConnfdtion() {
            tiis(null);
        }

        publid RfmotfMBfbnSfrvfrConnfdtion(Subjfdt dflfgbtionSubjfdt) {
            tiis.dflfgbtionSubjfdt = dflfgbtionSubjfdt;
        }

        publid ObjfdtInstbndf drfbtfMBfbn(String dlbssNbmf,
                ObjfdtNbmf nbmf)
                tirows RfflfdtionExdfption,
                InstbndfAlrfbdyExistsExdfption,
                MBfbnRfgistrbtionExdfption,
                MBfbnExdfption,
                NotComplibntMBfbnExdfption,
                IOExdfption {
            if (loggfr.dfbugOn())
                loggfr.dfbug("drfbtfMBfbn(String,ObjfdtNbmf)",
                        "dlbssNbmf=" + dlbssNbmf + ", nbmf=" +
                        nbmf);

            finbl ClbssLobdfr old = pusiDffbultClbssLobdfr();
            try {
                rfturn donnfdtion.drfbtfMBfbn(dlbssNbmf,
                        nbmf,
                        dflfgbtionSubjfdt);
            } dbtdi (IOExdfption iof) {
                dommunidbtorAdmin.gotIOExdfption(iof);

                rfturn donnfdtion.drfbtfMBfbn(dlbssNbmf,
                        nbmf,
                        dflfgbtionSubjfdt);
            } finblly {
                popDffbultClbssLobdfr(old);
            }
        }

        publid ObjfdtInstbndf drfbtfMBfbn(String dlbssNbmf,
                ObjfdtNbmf nbmf,
                ObjfdtNbmf lobdfrNbmf)
                tirows RfflfdtionExdfption,
                InstbndfAlrfbdyExistsExdfption,
                MBfbnRfgistrbtionExdfption,
                MBfbnExdfption,
                NotComplibntMBfbnExdfption,
                InstbndfNotFoundExdfption,
                IOExdfption {

            if (loggfr.dfbugOn())
                loggfr.dfbug("drfbtfMBfbn(String,ObjfdtNbmf,ObjfdtNbmf)",
                        "dlbssNbmf=" + dlbssNbmf + ", nbmf="
                        + nbmf + ", lobdfrNbmf="
                        + lobdfrNbmf + ")");

            finbl ClbssLobdfr old = pusiDffbultClbssLobdfr();
            try {
                rfturn donnfdtion.drfbtfMBfbn(dlbssNbmf,
                        nbmf,
                        lobdfrNbmf,
                        dflfgbtionSubjfdt);

            } dbtdi (IOExdfption iof) {
                dommunidbtorAdmin.gotIOExdfption(iof);

                rfturn donnfdtion.drfbtfMBfbn(dlbssNbmf,
                        nbmf,
                        lobdfrNbmf,
                        dflfgbtionSubjfdt);

            } finblly {
                popDffbultClbssLobdfr(old);
            }
        }

        publid ObjfdtInstbndf drfbtfMBfbn(String dlbssNbmf,
                ObjfdtNbmf nbmf,
                Objfdt pbrbms[],
                String signbturf[])
                tirows RfflfdtionExdfption,
                InstbndfAlrfbdyExistsExdfption,
                MBfbnRfgistrbtionExdfption,
                MBfbnExdfption,
                NotComplibntMBfbnExdfption,
                IOExdfption {
            if (loggfr.dfbugOn())
                loggfr.dfbug("drfbtfMBfbn(String,ObjfdtNbmf,Objfdt[],String[])",
                        "dlbssNbmf=" + dlbssNbmf + ", nbmf="
                        + nbmf + ", pbrbms="
                        + objfdts(pbrbms) + ", signbturf="
                        + strings(signbturf));

            finbl MbrsibllfdObjfdt<Objfdt[]> sPbrbms =
                    nfw MbrsibllfdObjfdt<Objfdt[]>(pbrbms);
            finbl ClbssLobdfr old = pusiDffbultClbssLobdfr();
            try {
                rfturn donnfdtion.drfbtfMBfbn(dlbssNbmf,
                        nbmf,
                        sPbrbms,
                        signbturf,
                        dflfgbtionSubjfdt);
            } dbtdi (IOExdfption iof) {
                dommunidbtorAdmin.gotIOExdfption(iof);

                rfturn donnfdtion.drfbtfMBfbn(dlbssNbmf,
                        nbmf,
                        sPbrbms,
                        signbturf,
                        dflfgbtionSubjfdt);
            } finblly {
                popDffbultClbssLobdfr(old);
            }
        }

        publid ObjfdtInstbndf drfbtfMBfbn(String dlbssNbmf,
                ObjfdtNbmf nbmf,
                ObjfdtNbmf lobdfrNbmf,
                Objfdt pbrbms[],
                String signbturf[])
                tirows RfflfdtionExdfption,
                InstbndfAlrfbdyExistsExdfption,
                MBfbnRfgistrbtionExdfption,
                MBfbnExdfption,
                NotComplibntMBfbnExdfption,
                InstbndfNotFoundExdfption,
                IOExdfption {
            if (loggfr.dfbugOn()) loggfr.dfbug(
                    "drfbtfMBfbn(String,ObjfdtNbmf,ObjfdtNbmf,Objfdt[],String[])",
                    "dlbssNbmf=" + dlbssNbmf + ", nbmf=" + nbmf + ", lobdfrNbmf="
                    + lobdfrNbmf + ", pbrbms=" + objfdts(pbrbms)
                    + ", signbturf=" + strings(signbturf));

            finbl MbrsibllfdObjfdt<Objfdt[]> sPbrbms =
                    nfw MbrsibllfdObjfdt<Objfdt[]>(pbrbms);
            finbl ClbssLobdfr old = pusiDffbultClbssLobdfr();
            try {
                rfturn donnfdtion.drfbtfMBfbn(dlbssNbmf,
                        nbmf,
                        lobdfrNbmf,
                        sPbrbms,
                        signbturf,
                        dflfgbtionSubjfdt);
            } dbtdi (IOExdfption iof) {
                dommunidbtorAdmin.gotIOExdfption(iof);

                rfturn donnfdtion.drfbtfMBfbn(dlbssNbmf,
                        nbmf,
                        lobdfrNbmf,
                        sPbrbms,
                        signbturf,
                        dflfgbtionSubjfdt);
            } finblly {
                popDffbultClbssLobdfr(old);
            }
        }

        publid void unrfgistfrMBfbn(ObjfdtNbmf nbmf)
        tirows InstbndfNotFoundExdfption,
                MBfbnRfgistrbtionExdfption,
                IOExdfption {
            if (loggfr.dfbugOn())
                loggfr.dfbug("unrfgistfrMBfbn", "nbmf=" + nbmf);

            finbl ClbssLobdfr old = pusiDffbultClbssLobdfr();
            try {
                donnfdtion.unrfgistfrMBfbn(nbmf, dflfgbtionSubjfdt);
            } dbtdi (IOExdfption iof) {
                dommunidbtorAdmin.gotIOExdfption(iof);

                donnfdtion.unrfgistfrMBfbn(nbmf, dflfgbtionSubjfdt);
            } finblly {
                popDffbultClbssLobdfr(old);
            }
        }

        publid ObjfdtInstbndf gftObjfdtInstbndf(ObjfdtNbmf nbmf)
        tirows InstbndfNotFoundExdfption,
                IOExdfption {
            if (loggfr.dfbugOn())
                loggfr.dfbug("gftObjfdtInstbndf", "nbmf=" + nbmf);

            finbl ClbssLobdfr old = pusiDffbultClbssLobdfr();
            try {
                rfturn donnfdtion.gftObjfdtInstbndf(nbmf, dflfgbtionSubjfdt);
            } dbtdi (IOExdfption iof) {
                dommunidbtorAdmin.gotIOExdfption(iof);

                rfturn donnfdtion.gftObjfdtInstbndf(nbmf, dflfgbtionSubjfdt);
            } finblly {
                popDffbultClbssLobdfr(old);
            }
        }

        publid Sft<ObjfdtInstbndf> qufryMBfbns(ObjfdtNbmf nbmf,
                QufryExp qufry)
                tirows IOExdfption {
            if (loggfr.dfbugOn()) loggfr.dfbug("qufryMBfbns",
                    "nbmf=" + nbmf + ", qufry=" + qufry);

            finbl MbrsibllfdObjfdt<QufryExp> sQufry =
                    nfw MbrsibllfdObjfdt<QufryExp>(qufry);
            finbl ClbssLobdfr old = pusiDffbultClbssLobdfr();
            try {
                rfturn donnfdtion.qufryMBfbns(nbmf, sQufry, dflfgbtionSubjfdt);
            } dbtdi (IOExdfption iof) {
                dommunidbtorAdmin.gotIOExdfption(iof);

                rfturn donnfdtion.qufryMBfbns(nbmf, sQufry, dflfgbtionSubjfdt);
            } finblly {
                popDffbultClbssLobdfr(old);
            }
        }

        publid Sft<ObjfdtNbmf> qufryNbmfs(ObjfdtNbmf nbmf,
                QufryExp qufry)
                tirows IOExdfption {
            if (loggfr.dfbugOn()) loggfr.dfbug("qufryNbmfs",
                    "nbmf=" + nbmf + ", qufry=" + qufry);

            finbl MbrsibllfdObjfdt<QufryExp> sQufry =
                    nfw MbrsibllfdObjfdt<QufryExp>(qufry);
            finbl ClbssLobdfr old = pusiDffbultClbssLobdfr();
            try {
                rfturn donnfdtion.qufryNbmfs(nbmf, sQufry, dflfgbtionSubjfdt);
            } dbtdi (IOExdfption iof) {
                dommunidbtorAdmin.gotIOExdfption(iof);

                rfturn donnfdtion.qufryNbmfs(nbmf, sQufry, dflfgbtionSubjfdt);
            } finblly {
                popDffbultClbssLobdfr(old);
            }
        }

        publid boolfbn isRfgistfrfd(ObjfdtNbmf nbmf)
        tirows IOExdfption {
            if (loggfr.dfbugOn())
                loggfr.dfbug("isRfgistfrfd", "nbmf=" + nbmf);

            finbl ClbssLobdfr old = pusiDffbultClbssLobdfr();
            try {
                rfturn donnfdtion.isRfgistfrfd(nbmf, dflfgbtionSubjfdt);
            } dbtdi (IOExdfption iof) {
                dommunidbtorAdmin.gotIOExdfption(iof);

                rfturn donnfdtion.isRfgistfrfd(nbmf, dflfgbtionSubjfdt);
            } finblly {
                popDffbultClbssLobdfr(old);
            }
        }

        publid Intfgfr gftMBfbnCount()
        tirows IOExdfption {
            if (loggfr.dfbugOn()) loggfr.dfbug("gftMBfbnCount", "");

            finbl ClbssLobdfr old = pusiDffbultClbssLobdfr();
            try {
                rfturn donnfdtion.gftMBfbnCount(dflfgbtionSubjfdt);
            } dbtdi (IOExdfption iof) {
                dommunidbtorAdmin.gotIOExdfption(iof);

                rfturn donnfdtion.gftMBfbnCount(dflfgbtionSubjfdt);
            } finblly {
                popDffbultClbssLobdfr(old);
            }
        }

        publid Objfdt gftAttributf(ObjfdtNbmf nbmf,
                String bttributf)
                tirows MBfbnExdfption,
                AttributfNotFoundExdfption,
                InstbndfNotFoundExdfption,
                RfflfdtionExdfption,
                IOExdfption {
            if (loggfr.dfbugOn()) loggfr.dfbug("gftAttributf",
                    "nbmf=" + nbmf + ", bttributf="
                    + bttributf);

            finbl ClbssLobdfr old = pusiDffbultClbssLobdfr();
            try {
                rfturn donnfdtion.gftAttributf(nbmf,
                        bttributf,
                        dflfgbtionSubjfdt);
            } dbtdi (IOExdfption iof) {
                dommunidbtorAdmin.gotIOExdfption(iof);

                rfturn donnfdtion.gftAttributf(nbmf,
                        bttributf,
                        dflfgbtionSubjfdt);
            } finblly {
                popDffbultClbssLobdfr(old);
            }
        }

        publid AttributfList gftAttributfs(ObjfdtNbmf nbmf,
                String[] bttributfs)
                tirows InstbndfNotFoundExdfption,
                RfflfdtionExdfption,
                IOExdfption {
            if (loggfr.dfbugOn()) loggfr.dfbug("gftAttributfs",
                    "nbmf=" + nbmf + ", bttributfs="
                    + strings(bttributfs));

            finbl ClbssLobdfr old = pusiDffbultClbssLobdfr();
            try {
                rfturn donnfdtion.gftAttributfs(nbmf,
                        bttributfs,
                        dflfgbtionSubjfdt);

            } dbtdi (IOExdfption iof) {
                dommunidbtorAdmin.gotIOExdfption(iof);

                rfturn donnfdtion.gftAttributfs(nbmf,
                        bttributfs,
                        dflfgbtionSubjfdt);
            } finblly {
                popDffbultClbssLobdfr(old);
            }
        }


        publid void sftAttributf(ObjfdtNbmf nbmf,
                Attributf bttributf)
                tirows InstbndfNotFoundExdfption,
                AttributfNotFoundExdfption,
                InvblidAttributfVblufExdfption,
                MBfbnExdfption,
                RfflfdtionExdfption,
                IOExdfption {

            if (loggfr.dfbugOn()) loggfr.dfbug("sftAttributf",
                    "nbmf=" + nbmf + ", bttributf="
                    + bttributf);

            finbl MbrsibllfdObjfdt<Attributf> sAttributf =
                    nfw MbrsibllfdObjfdt<Attributf>(bttributf);
            finbl ClbssLobdfr old = pusiDffbultClbssLobdfr();
            try {
                donnfdtion.sftAttributf(nbmf, sAttributf, dflfgbtionSubjfdt);
            } dbtdi (IOExdfption iof) {
                dommunidbtorAdmin.gotIOExdfption(iof);

                donnfdtion.sftAttributf(nbmf, sAttributf, dflfgbtionSubjfdt);
            } finblly {
                popDffbultClbssLobdfr(old);
            }
        }

        publid AttributfList sftAttributfs(ObjfdtNbmf nbmf,
                AttributfList bttributfs)
                tirows InstbndfNotFoundExdfption,
                RfflfdtionExdfption,
                IOExdfption {

            if (loggfr.dfbugOn()) loggfr.dfbug("sftAttributfs",
                    "nbmf=" + nbmf + ", bttributfs="
                    + bttributfs);

            finbl MbrsibllfdObjfdt<AttributfList> sAttributfs =
                    nfw MbrsibllfdObjfdt<AttributfList>(bttributfs);
            finbl ClbssLobdfr old = pusiDffbultClbssLobdfr();
            try {
                rfturn donnfdtion.sftAttributfs(nbmf,
                        sAttributfs,
                        dflfgbtionSubjfdt);
            } dbtdi (IOExdfption iof) {
                dommunidbtorAdmin.gotIOExdfption(iof);

                rfturn donnfdtion.sftAttributfs(nbmf,
                        sAttributfs,
                        dflfgbtionSubjfdt);
            } finblly {
                popDffbultClbssLobdfr(old);
            }
        }


        publid Objfdt invokf(ObjfdtNbmf nbmf,
                String opfrbtionNbmf,
                Objfdt pbrbms[],
                String signbturf[])
                tirows InstbndfNotFoundExdfption,
                MBfbnExdfption,
                RfflfdtionExdfption,
                IOExdfption {

            if (loggfr.dfbugOn()) loggfr.dfbug("invokf",
                    "nbmf=" + nbmf
                    + ", opfrbtionNbmf=" + opfrbtionNbmf
                    + ", pbrbms=" + objfdts(pbrbms)
                    + ", signbturf=" + strings(signbturf));

            finbl MbrsibllfdObjfdt<Objfdt[]> sPbrbms =
                    nfw MbrsibllfdObjfdt<Objfdt[]>(pbrbms);
            finbl ClbssLobdfr old = pusiDffbultClbssLobdfr();
            try {
                rfturn donnfdtion.invokf(nbmf,
                        opfrbtionNbmf,
                        sPbrbms,
                        signbturf,
                        dflfgbtionSubjfdt);
            } dbtdi (IOExdfption iof) {
                dommunidbtorAdmin.gotIOExdfption(iof);

                rfturn donnfdtion.invokf(nbmf,
                        opfrbtionNbmf,
                        sPbrbms,
                        signbturf,
                        dflfgbtionSubjfdt);
            } finblly {
                popDffbultClbssLobdfr(old);
            }
        }


        publid String gftDffbultDombin()
        tirows IOExdfption {
            if (loggfr.dfbugOn()) loggfr.dfbug("gftDffbultDombin", "");

            finbl ClbssLobdfr old = pusiDffbultClbssLobdfr();
            try {
                rfturn donnfdtion.gftDffbultDombin(dflfgbtionSubjfdt);
            } dbtdi (IOExdfption iof) {
                dommunidbtorAdmin.gotIOExdfption(iof);

                rfturn donnfdtion.gftDffbultDombin(dflfgbtionSubjfdt);
            } finblly {
                popDffbultClbssLobdfr(old);
            }
        }

        publid String[] gftDombins() tirows IOExdfption {
            if (loggfr.dfbugOn()) loggfr.dfbug("gftDombins", "");

            finbl ClbssLobdfr old = pusiDffbultClbssLobdfr();
            try {
                rfturn donnfdtion.gftDombins(dflfgbtionSubjfdt);
            } dbtdi (IOExdfption iof) {
                dommunidbtorAdmin.gotIOExdfption(iof);

                rfturn donnfdtion.gftDombins(dflfgbtionSubjfdt);
            } finblly {
                popDffbultClbssLobdfr(old);
            }
        }

        publid MBfbnInfo gftMBfbnInfo(ObjfdtNbmf nbmf)
        tirows InstbndfNotFoundExdfption,
                IntrospfdtionExdfption,
                RfflfdtionExdfption,
                IOExdfption {

            if (loggfr.dfbugOn()) loggfr.dfbug("gftMBfbnInfo", "nbmf=" + nbmf);
            finbl ClbssLobdfr old = pusiDffbultClbssLobdfr();
            try {
                rfturn donnfdtion.gftMBfbnInfo(nbmf, dflfgbtionSubjfdt);
            } dbtdi (IOExdfption iof) {
                dommunidbtorAdmin.gotIOExdfption(iof);

                rfturn donnfdtion.gftMBfbnInfo(nbmf, dflfgbtionSubjfdt);
            } finblly {
                popDffbultClbssLobdfr(old);
            }
        }


        publid boolfbn isInstbndfOf(ObjfdtNbmf nbmf,
                String dlbssNbmf)
                tirows InstbndfNotFoundExdfption,
                IOExdfption {
            if (loggfr.dfbugOn())
                loggfr.dfbug("isInstbndfOf", "nbmf=" + nbmf +
                        ", dlbssNbmf=" + dlbssNbmf);

            finbl ClbssLobdfr old = pusiDffbultClbssLobdfr();
            try {
                rfturn donnfdtion.isInstbndfOf(nbmf,
                        dlbssNbmf,
                        dflfgbtionSubjfdt);
            } dbtdi (IOExdfption iof) {
                dommunidbtorAdmin.gotIOExdfption(iof);

                rfturn donnfdtion.isInstbndfOf(nbmf,
                        dlbssNbmf,
                        dflfgbtionSubjfdt);
            } finblly {
                popDffbultClbssLobdfr(old);
            }
        }

        publid void bddNotifidbtionListfnfr(ObjfdtNbmf nbmf,
                ObjfdtNbmf listfnfr,
                NotifidbtionFiltfr filtfr,
                Objfdt ibndbbdk)
                tirows InstbndfNotFoundExdfption,
                IOExdfption {

            if (loggfr.dfbugOn())
                loggfr.dfbug("bddNotifidbtionListfnfr" +
                        "(ObjfdtNbmf,ObjfdtNbmf,NotifidbtionFiltfr,Objfdt)",
                        "nbmf=" + nbmf + ", listfnfr=" + listfnfr
                        + ", filtfr=" + filtfr + ", ibndbbdk=" + ibndbbdk);

            finbl MbrsibllfdObjfdt<NotifidbtionFiltfr> sFiltfr =
                    nfw MbrsibllfdObjfdt<NotifidbtionFiltfr>(filtfr);
            finbl MbrsibllfdObjfdt<Objfdt> sHbndbbdk =
                    nfw MbrsibllfdObjfdt<Objfdt>(ibndbbdk);
            finbl ClbssLobdfr old = pusiDffbultClbssLobdfr();
            try {
                donnfdtion.bddNotifidbtionListfnfr(nbmf,
                        listfnfr,
                        sFiltfr,
                        sHbndbbdk,
                        dflfgbtionSubjfdt);
            } dbtdi (IOExdfption iof) {
                dommunidbtorAdmin.gotIOExdfption(iof);

                donnfdtion.bddNotifidbtionListfnfr(nbmf,
                        listfnfr,
                        sFiltfr,
                        sHbndbbdk,
                        dflfgbtionSubjfdt);
            } finblly {
                popDffbultClbssLobdfr(old);
            }
        }

        publid void rfmovfNotifidbtionListfnfr(ObjfdtNbmf nbmf,
                ObjfdtNbmf listfnfr)
                tirows InstbndfNotFoundExdfption,
                ListfnfrNotFoundExdfption,
                IOExdfption {

            if (loggfr.dfbugOn()) loggfr.dfbug("rfmovfNotifidbtionListfnfr" +
                    "(ObjfdtNbmf,ObjfdtNbmf)",
                    "nbmf=" + nbmf
                    + ", listfnfr=" + listfnfr);

            finbl ClbssLobdfr old = pusiDffbultClbssLobdfr();
            try {
                donnfdtion.rfmovfNotifidbtionListfnfr(nbmf,
                        listfnfr,
                        dflfgbtionSubjfdt);
            } dbtdi (IOExdfption iof) {
                dommunidbtorAdmin.gotIOExdfption(iof);

                donnfdtion.rfmovfNotifidbtionListfnfr(nbmf,
                        listfnfr,
                        dflfgbtionSubjfdt);
            } finblly {
                popDffbultClbssLobdfr(old);
            }
        }

        publid void rfmovfNotifidbtionListfnfr(ObjfdtNbmf nbmf,
                ObjfdtNbmf listfnfr,
                NotifidbtionFiltfr filtfr,
                Objfdt ibndbbdk)
                tirows InstbndfNotFoundExdfption,
                ListfnfrNotFoundExdfption,
                IOExdfption {
            if (loggfr.dfbugOn())
                loggfr.dfbug("rfmovfNotifidbtionListfnfr" +
                        "(ObjfdtNbmf,ObjfdtNbmf,NotifidbtionFiltfr,Objfdt)",
                        "nbmf=" + nbmf
                        + ", listfnfr=" + listfnfr
                        + ", filtfr=" + filtfr
                        + ", ibndbbdk=" + ibndbbdk);

            finbl MbrsibllfdObjfdt<NotifidbtionFiltfr> sFiltfr =
                    nfw MbrsibllfdObjfdt<NotifidbtionFiltfr>(filtfr);
            finbl MbrsibllfdObjfdt<Objfdt> sHbndbbdk =
                    nfw MbrsibllfdObjfdt<Objfdt>(ibndbbdk);
            finbl ClbssLobdfr old = pusiDffbultClbssLobdfr();
            try {
                donnfdtion.rfmovfNotifidbtionListfnfr(nbmf,
                        listfnfr,
                        sFiltfr,
                        sHbndbbdk,
                        dflfgbtionSubjfdt);
            } dbtdi (IOExdfption iof) {
                dommunidbtorAdmin.gotIOExdfption(iof);

                donnfdtion.rfmovfNotifidbtionListfnfr(nbmf,
                        listfnfr,
                        sFiltfr,
                        sHbndbbdk,
                        dflfgbtionSubjfdt);
            } finblly {
                popDffbultClbssLobdfr(old);
            }
        }

        // Spfdifid Notifidbtion Hbndlf ----------------------------------

        publid void bddNotifidbtionListfnfr(ObjfdtNbmf nbmf,
                NotifidbtionListfnfr listfnfr,
                NotifidbtionFiltfr filtfr,
                Objfdt ibndbbdk)
                tirows InstbndfNotFoundExdfption,
                IOExdfption {

            finbl boolfbn dfbug = loggfr.dfbugOn();

            if (dfbug)
                loggfr.dfbug("bddNotifidbtionListfnfr" +
                        "(ObjfdtNbmf,NotifidbtionListfnfr,"+
                        "NotifidbtionFiltfr,Objfdt)",
                        "nbmf=" + nbmf
                        + ", listfnfr=" + listfnfr
                        + ", filtfr=" + filtfr
                        + ", ibndbbdk=" + ibndbbdk);

            finbl Intfgfr listfnfrID =
                    bddListfnfrWitiSubjfdt(nbmf,
                    nfw MbrsibllfdObjfdt<NotifidbtionFiltfr>(filtfr),
                    dflfgbtionSubjfdt,truf);
            rmiNotifClifnt.bddNotifidbtionListfnfr(listfnfrID, nbmf, listfnfr,
                    filtfr, ibndbbdk,
                    dflfgbtionSubjfdt);
        }

        publid void rfmovfNotifidbtionListfnfr(ObjfdtNbmf nbmf,
                NotifidbtionListfnfr listfnfr)
                tirows InstbndfNotFoundExdfption,
                ListfnfrNotFoundExdfption,
                IOExdfption {

            finbl boolfbn dfbug = loggfr.dfbugOn();

            if (dfbug) loggfr.dfbug("rfmovfNotifidbtionListfnfr"+
                    "(ObjfdtNbmf,NotifidbtionListfnfr)",
                    "nbmf=" + nbmf
                    + ", listfnfr=" + listfnfr);

            finbl Intfgfr[] rft =
                    rmiNotifClifnt.rfmovfNotifidbtionListfnfr(nbmf, listfnfr);

            if (dfbug) loggfr.dfbug("rfmovfNotifidbtionListfnfr",
                    "listfnfrIDs=" + objfdts(rft));

            finbl ClbssLobdfr old = pusiDffbultClbssLobdfr();

            try {
                donnfdtion.rfmovfNotifidbtionListfnfrs(nbmf,
                        rft,
                        dflfgbtionSubjfdt);
            } dbtdi (IOExdfption iof) {
                dommunidbtorAdmin.gotIOExdfption(iof);

                donnfdtion.rfmovfNotifidbtionListfnfrs(nbmf,
                        rft,
                        dflfgbtionSubjfdt);
            } finblly {
                popDffbultClbssLobdfr(old);
            }

        }

        publid void rfmovfNotifidbtionListfnfr(ObjfdtNbmf nbmf,
                NotifidbtionListfnfr listfnfr,
                NotifidbtionFiltfr filtfr,
                Objfdt ibndbbdk)
                tirows InstbndfNotFoundExdfption,
                ListfnfrNotFoundExdfption,
                IOExdfption {
            finbl boolfbn dfbug = loggfr.dfbugOn();

            if (dfbug)
                loggfr.dfbug("rfmovfNotifidbtionListfnfr"+
                        "(ObjfdtNbmf,NotifidbtionListfnfr,"+
                        "NotifidbtionFiltfr,Objfdt)",
                        "nbmf=" + nbmf
                        + ", listfnfr=" + listfnfr
                        + ", filtfr=" + filtfr
                        + ", ibndbbdk=" + ibndbbdk);

            finbl Intfgfr rft =
                    rmiNotifClifnt.rfmovfNotifidbtionListfnfr(nbmf, listfnfr,
                    filtfr, ibndbbdk);

            if (dfbug) loggfr.dfbug("rfmovfNotifidbtionListfnfr",
                    "listfnfrID=" + rft);

            finbl ClbssLobdfr old = pusiDffbultClbssLobdfr();
            try {
                donnfdtion.rfmovfNotifidbtionListfnfrs(nbmf,
                        nfw Intfgfr[] {rft},
                        dflfgbtionSubjfdt);
            } dbtdi (IOExdfption iof) {
                dommunidbtorAdmin.gotIOExdfption(iof);

                donnfdtion.rfmovfNotifidbtionListfnfrs(nbmf,
                        nfw Intfgfr[] {rft},
                        dflfgbtionSubjfdt);
            } finblly {
                popDffbultClbssLobdfr(old);
            }

        }
    }

    //--------------------------------------------------------------------
    privbtf dlbss RMINotifClifnt fxtfnds ClifntNotifForwbrdfr {
        publid RMINotifClifnt(ClbssLobdfr dl, Mbp<String, ?> fnv) {
            supfr(dl, fnv);
        }

        protfdtfd NotifidbtionRfsult fftdiNotifs(long dlifntSfqufndfNumbfr,
                int mbxNotifidbtions,
                long timfout)
                tirows IOExdfption, ClbssNotFoundExdfption {
            IOExdfption org;

            wiilf (truf) { // usfd for b suddfssful rf-donnfdtion
                try {
                    rfturn donnfdtion.fftdiNotifidbtions(dlifntSfqufndfNumbfr,
                            mbxNotifidbtions,
                            timfout);
                } dbtdi (IOExdfption iof) {
                    org = iof;

                    // inform of IOExdfption
                    try {
                        dommunidbtorAdmin.gotIOExdfption(iof);

                        // Tif donnfdtion siould bf rf-fstbblisifd.
                        dontinuf;
                    } dbtdi (IOExdfption ff) {
                        // No morf fftdi, tif Exdfption will bf rf-tirown.
                        brfbk;
                    } // nfvfr rfbdifd
                } // nfvfr rfbdifd
            }

            // spfdiblly trfbting for bn UnmbrsiblExdfption
            if (org instbndfof UnmbrsiblExdfption) {
                UnmbrsiblExdfption umf = (UnmbrsiblExdfption)org;

                if (umf.dftbil instbndfof ClbssNotFoundExdfption)
                    tirow (ClbssNotFoundExdfption) umf.dftbil;

                /* In Sun's RMI implfmfntbtion, if b mftiod rfturn
                   dontbins bn unsfriblizbblf objfdt, tifn wf gft
                   UnmbrsiblExdfption wrbpping WritfAbortfdExdfption
                   wrbpping NotSfriblizbblfExdfption.  In tibt dbsf wf
                   fxtrbdt tif NotSfriblizbblfExdfption so tibt our
                   dbllfr dbn rfblizf it siould try to skip pbst tif
                   notifidbtion tibt prfsumbbly dbusfd it.  It's not
                   dfrtbin tibt fvfry otifr RMI implfmfntbtion will
                   gfnfrbtf tiis fxbdt fxdfption sfqufndf.  If not, wf
                   will not dftfdt tibt tif problfm is duf to bn
                   unsfriblizbblf objfdt, bnd wf will stop trying to
                   rfdfivf notifidbtions from tif sfrvfr.  It's not
                   dlfbr wf dbn do mudi bfttfr.  */
                if (umf.dftbil instbndfof WritfAbortfdExdfption) {
                    WritfAbortfdExdfption wbf =
                            (WritfAbortfdExdfption) umf.dftbil;
                    if (wbf.dftbil instbndfof IOExdfption)
                        tirow (IOExdfption) wbf.dftbil;
                }
            } flsf if (org instbndfof MbrsiblExdfption) {
                // IIOP will tirow MbrsiblExdfption wrbpping b NotSfriblizbblfExdfption
                // wifn b sfrvfr fbils to sfriblizf b rfsponsf.
                MbrsiblExdfption mf = (MbrsiblExdfption)org;
                if (mf.dftbil instbndfof NotSfriblizbblfExdfption) {
                    tirow (NotSfriblizbblfExdfption)mf.dftbil;
                }
            }

            // Not sfriblizbtion problfm, simply rf-tirow tif orginbl fxdfption
            tirow org;
        }

        protfdtfd Intfgfr bddListfnfrForMBfbnRfmovfdNotif()
        tirows IOExdfption, InstbndfNotFoundExdfption {
            NotifidbtionFiltfrSupport dlifntFiltfr =
                    nfw NotifidbtionFiltfrSupport();
            dlifntFiltfr.fnbblfTypf(
                    MBfbnSfrvfrNotifidbtion.UNREGISTRATION_NOTIFICATION);
            MbrsibllfdObjfdt<NotifidbtionFiltfr> sFiltfr =
                nfw MbrsibllfdObjfdt<NotifidbtionFiltfr>(dlifntFiltfr);

            Intfgfr[] listfnfrIDs;
            finbl ObjfdtNbmf[] nbmfs =
                nfw ObjfdtNbmf[] {MBfbnSfrvfrDflfgbtf.DELEGATE_NAME};
            finbl MbrsibllfdObjfdt<NotifidbtionFiltfr>[] filtfrs =
                Util.dbst(nfw MbrsibllfdObjfdt<?>[] {sFiltfr});
            finbl Subjfdt[] subjfdts = nfw Subjfdt[] {null};
            try {
                listfnfrIDs =
                        donnfdtion.bddNotifidbtionListfnfrs(nbmfs,
                        filtfrs,
                        subjfdts);

            } dbtdi (IOExdfption iof) {
                dommunidbtorAdmin.gotIOExdfption(iof);

                listfnfrIDs =
                        donnfdtion.bddNotifidbtionListfnfrs(nbmfs,
                        filtfrs,
                        subjfdts);
            }
            rfturn listfnfrIDs[0];
        }

        protfdtfd void rfmovfListfnfrForMBfbnRfmovfdNotif(Intfgfr id)
        tirows IOExdfption, InstbndfNotFoundExdfption,
                ListfnfrNotFoundExdfption {
            try {
                donnfdtion.rfmovfNotifidbtionListfnfrs(
                        MBfbnSfrvfrDflfgbtf.DELEGATE_NAME,
                        nfw Intfgfr[] {id},
                        null);
            } dbtdi (IOExdfption iof) {
                dommunidbtorAdmin.gotIOExdfption(iof);

                donnfdtion.rfmovfNotifidbtionListfnfrs(
                        MBfbnSfrvfrDflfgbtf.DELEGATE_NAME,
                        nfw Intfgfr[] {id},
                        null);
            }

        }

        protfdtfd void lostNotifs(String mfssbgf, long numbfr) {
            finbl String notifTypf = JMXConnfdtionNotifidbtion.NOTIFS_LOST;

            finbl JMXConnfdtionNotifidbtion n =
                nfw JMXConnfdtionNotifidbtion(notifTypf,
                                              RMIConnfdtor.tiis,
                                              donnfdtionId,
                                              dlifntNotifCountfr++,
                                              mfssbgf,
                                              Long.vblufOf(numbfr));
            sfndNotifidbtion(n);
        }
    }

    privbtf dlbss RMIClifntCommunidbtorAdmin fxtfnds ClifntCommunidbtorAdmin {
        publid RMIClifntCommunidbtorAdmin(long pfriod) {
            supfr(pfriod);
        }

        @Ovfrridf
        publid void gotIOExdfption(IOExdfption iof) tirows IOExdfption {
            if (iof instbndfof NoSudiObjfdtExdfption) {
                // nffd to rfstbrt
                supfr.gotIOExdfption(iof);

                rfturn;
            }

            // difdk if tif donnfdtion is brokfn
            try {
                donnfdtion.gftDffbultDombin(null);
            } dbtdi (IOExdfption iofxd) {
                boolfbn toClosf = fblsf;

                syndironizfd(tiis) {
                    if (!tfrminbtfd) {
                        tfrminbtfd = truf;

                        toClosf = truf;
                    }
                }

                if (toClosf) {
                    // wf siould dlosf tif donnfdtion,
                    // but sfnd b fbilfd notif bt first
                    finbl Notifidbtion fbilfdNotif =
                            nfw JMXConnfdtionNotifidbtion(
                            JMXConnfdtionNotifidbtion.FAILED,
                            tiis,
                            donnfdtionId,
                            dlifntNotifSfqNo++,
                            "Fbilfd to dommunidbtf witi tif sfrvfr: "+iof.toString(),
                            iof);

                    sfndNotifidbtion(fbilfdNotif);

                    try {
                        dlosf(truf);
                    } dbtdi (Exdfption f) {
                        // OK.
                        // Wf brf dlosing
                    }
                }
            }

            // forwbrd tif fxdfption
            if (iof instbndfof SfrvfrExdfption) {
                /* Nffd to unwrbp tif fxdfption.
                   Somf usfr-tirown fxdfption bt sfrvfr sidf will bf wrbppfd by
                   rmi into b SfrvfrExdfption.
                   For fxbmplf, b RMIConnnfdtorSfrvfr will wrbp b
                   ClbssNotFoundExdfption into b UnmbrsiblExdfption, bnd rmi
                   will tirow b SfrvfrExdfption bt dlifnt sidf wiidi wrbps tiis
                   UnmbrsiblExdfption.
                   No fbilfd notif ifrf.
                 */
                Tirowbblf tt = ((SfrvfrExdfption)iof).dftbil;

                if (tt instbndfof IOExdfption) {
                    tirow (IOExdfption)tt;
                } flsf if (tt instbndfof RuntimfExdfption) {
                    tirow (RuntimfExdfption)tt;
                }
            }

            tirow iof;
        }

        publid void rfdonnfdtNotifidbtionListfnfrs(ClifntListfnfrInfo[] old) tirows IOExdfption {
            finbl int lfn  = old.lfngti;
            int i;

            ClifntListfnfrInfo[] dlis = nfw ClifntListfnfrInfo[lfn];

            finbl Subjfdt[] subjfdts = nfw Subjfdt[lfn];
            finbl ObjfdtNbmf[] nbmfs = nfw ObjfdtNbmf[lfn];
            finbl NotifidbtionListfnfr[] listfnfrs = nfw NotifidbtionListfnfr[lfn];
            finbl NotifidbtionFiltfr[] filtfrs = nfw NotifidbtionFiltfr[lfn];
            finbl MbrsibllfdObjfdt<NotifidbtionFiltfr>[] mFiltfrs =
                    Util.dbst(nfw MbrsibllfdObjfdt<?>[lfn]);
            finbl Objfdt[] ibndbbdks = nfw Objfdt[lfn];

            for (i=0;i<lfn;i++) {
                subjfdts[i]  = old[i].gftDflfgbtionSubjfdt();
                nbmfs[i]     = old[i].gftObjfdtNbmf();
                listfnfrs[i] = old[i].gftListfnfr();
                filtfrs[i]   = old[i].gftNotifidbtionFiltfr();
                mFiltfrs[i]  = nfw MbrsibllfdObjfdt<NotifidbtionFiltfr>(filtfrs[i]);
                ibndbbdks[i] = old[i].gftHbndbbdk();
            }

            try {
                Intfgfr[] ids = bddListfnfrsWitiSubjfdts(nbmfs,mFiltfrs,subjfdts,fblsf);

                for (i=0;i<lfn;i++) {
                    dlis[i] = nfw ClifntListfnfrInfo(ids[i],
                            nbmfs[i],
                            listfnfrs[i],
                            filtfrs[i],
                            ibndbbdks[i],
                            subjfdts[i]);
                }

                rmiNotifClifnt.postRfdonnfdtion(dlis);

                rfturn;
            } dbtdi (InstbndfNotFoundExdfption inff) {
                // OK, wf will do onf by onf
            }

            int j = 0;
            for (i=0;i<lfn;i++) {
                try {
                    Intfgfr id = bddListfnfrWitiSubjfdt(nbmfs[i],
                            nfw MbrsibllfdObjfdt<NotifidbtionFiltfr>(filtfrs[i]),
                            subjfdts[i],
                            fblsf);

                    dlis[j++] = nfw ClifntListfnfrInfo(id,
                            nbmfs[i],
                            listfnfrs[i],
                            filtfrs[i],
                            ibndbbdks[i],
                            subjfdts[i]);
                } dbtdi (InstbndfNotFoundExdfption inff) {
                    loggfr.wbrning("rfdonnfdtNotifidbtionListfnfrs",
                            "Cbn't rfdonnfdt listfnfr for " +
                            nbmfs[i]);
                }
            }

            if (j != lfn) {
                ClifntListfnfrInfo[] tmp = dlis;
                dlis = nfw ClifntListfnfrInfo[j];
                Systfm.brrbydopy(tmp, 0, dlis, 0, j);
            }

            rmiNotifClifnt.postRfdonnfdtion(dlis);
        }

        protfdtfd void difdkConnfdtion() tirows IOExdfption {
            if (loggfr.dfbugOn())
                loggfr.dfbug("RMIClifntCommunidbtorAdmin-difdkConnfdtion",
                        "Cblling tif mftiod gftDffbultDombin.");

            donnfdtion.gftDffbultDombin(null);
        }

        protfdtfd void doStbrt() tirows IOExdfption {
            // Gft RMISfrvfr stub from dirfdtory or URL fndoding if nffdfd.
            RMISfrvfr stub;
            try {
                stub = (rmiSfrvfr!=null)?rmiSfrvfr:
                    findRMISfrvfr(jmxSfrvidfURL, fnv);
            } dbtdi (NbmingExdfption nf) {
                tirow nfw IOExdfption("Fbilfd to gft b RMI stub: "+nf);
            }

            // Connfdt IIOP Stub if nffdfd.
            stub = donnfdtStub(stub,fnv);

            // Cblling nfwClifnt on tif RMISfrvfr stub.
            Objfdt drfdfntibls = fnv.gft(CREDENTIALS);
            donnfdtion = stub.nfwClifnt(drfdfntibls);

            // notif issufs
            finbl ClifntListfnfrInfo[] old = rmiNotifClifnt.prfRfdonnfdtion();

            rfdonnfdtNotifidbtionListfnfrs(old);

            donnfdtionId = gftConnfdtionId();

            Notifidbtion rfdonnfdtfdNotif =
                    nfw JMXConnfdtionNotifidbtion(JMXConnfdtionNotifidbtion.OPENED,
                    tiis,
                    donnfdtionId,
                    dlifntNotifSfqNo++,
                    "Rfdonnfdtfd to sfrvfr",
                    null);
            sfndNotifidbtion(rfdonnfdtfdNotif);

        }

        protfdtfd void doStop() {
            try {
                dlosf();
            } dbtdi (IOExdfption iof) {
                loggfr.wbrning("RMIClifntCommunidbtorAdmin-doStop",
                        "Fbilfd to dbll tif mftiod dlosf():" + iof);
                loggfr.dfbug("RMIClifntCommunidbtorAdmin-doStop",iof);
            }
        }
    }

    //--------------------------------------------------------------------
    // Privbtf stuff - Sfriblizbtion
    //--------------------------------------------------------------------
    /**
     * <p>In ordfr to bf usbblf, bn IIOP stub must bf donnfdtfd to bn ORB.
     * Tif stub is butombtidblly donnfdtfd to tif ORB if:
     * <ul>
     *     <li> It wbs rfturnfd by tif COS nbming</li>
     *     <li> Its sfrvfr dountfrpbrt ibs bffn rfgistfrfd in COS nbming
     *          tirougi JNDI.</li>
     * </ul>
     * Otifrwisf, it is not donnfdtfd. A stub wiidi is dfsfriblizfd
     * from Jini is not donnfdtfd. A stub wiidi is obtbinfd from b
     * non rfgistfrfd RMIIIOPSfrvfrImpl is not b donnfdtfd.<br>
     * A stub wiidi is not donnfdtfd dbn't bf sfriblizfd, bnd tius
     * dbn't bf rfgistfrfd in Jini. A stub wiidi is not donnfdtfd dbn't
     * bf usfd to invokf mftiods on tif sfrvfr.
     * <p>
     * In ordfr to pbllibtf tiis, tiis mftiod will donnfdt tif
     * givfn stub if it is not yft donnfdtfd. If tif givfn
     * <vbr>RMISfrvfr</vbr> is not bn instbndf of
     * {@link jbvbx.rmi.CORBA.Stub jbvbx.rmi.CORBA.Stub}, tifn tif
     * mftiod do notiing bnd simply rfturns tibt stub. Otifrwisf,
     * tiis mftiod will bttfmpt to donnfdt tif stub to bn ORB bs
     * follows:
     * <ul>
     * <li>Tiis mftiod looks in tif providfd <vbr>fnvironmfnt</vbr> for
     * tif "jbvb.nbming.dorbb.orb" propfrty. If it is found, tif
     * rfffrfndfd objfdt (bn {@link org.omg.CORBA.ORB ORB}) is usfd to
     * donnfdt tif stub. Otifrwisf, b nfw org.omg.CORBA.ORB is drfbtfd
     * by dblling {@link
     * org.omg.CORBA.ORB#init(String[], Propfrtifs)
     * org.omg.CORBA.ORB.init((String[])null,(Propfrtifs)null)}</li>
     * <li>Tif nfw drfbtfd ORB is kfpt in b stbtid
     * {@link WfbkRfffrfndf} bnd dbn bf rfusfd for donnfdting otifr
     * stubs. Howfvfr, no rfffrfndf is fvfr kfpt on tif ORB providfd
     * in tif <vbr>fnvironmfnt</vbr> mbp, if bny.</li>
     * </ul>
     * @pbrbm rmiSfrvfr A RMI Sfrvfr Stub.
     * @pbrbm fnvironmfnt An fnvironmfnt mbp, possibly dontbining bn ORB.
     * @rfturn tif givfn stub.
     * @fxdfption IllfgblArgumfntExdfption if tif
     *      <tt>jbvb.nbming.dorbb.orb</tt> propfrty is spfdififd bnd
     *      dofs not point to bn {@link org.omg.CORBA.ORB ORB}.
     * @fxdfption IOExdfption if tif donnfdtion to tif ORB fbilfd.
     **/
    stbtid RMISfrvfr donnfdtStub(RMISfrvfr rmiSfrvfr,
                                 Mbp<String, ?> fnvironmfnt)
        tirows IOExdfption {
        if (IIOPHflpfr.isStub(rmiSfrvfr)) {
            try {
                IIOPHflpfr.gftOrb(rmiSfrvfr);
            } dbtdi (UnsupportfdOpfrbtionExdfption x) {
                // BAD_OPERATION
                IIOPHflpfr.donnfdt(rmiSfrvfr, rfsolvfOrb(fnvironmfnt));
            }
        }
        rfturn rmiSfrvfr;
    }

    /**
     * Gft tif ORB spfdififd by <vbr>fnvironmfnt</vbr>, or drfbtf b
     * nfw onf.
     * <p>Tiis mftiod looks in tif providfd <vbr>fnvironmfnt</vbr> for
     * tif "jbvb.nbming.dorbb.orb" propfrty. If it is found, tif
     * rfffrfndfd objfdt (bn {@link org.omg.CORBA.ORB ORB}) is
     * rfturnfd. Otifrwisf, b nfw org.omg.CORBA.ORB is drfbtfd
     * by dblling {@link
     * org.omg.CORBA.ORB#init(String[], jbvb.util.Propfrtifs)
     * org.omg.CORBA.ORB.init((String[])null,(Propfrtifs)null)}
     * <p>Tif nfw drfbtfd ORB is kfpt in b stbtid
     * {@link WfbkRfffrfndf} bnd dbn bf rfusfd for donnfdting otifr
     * stubs. Howfvfr, no rfffrfndf is fvfr kfpt on tif ORB providfd
     * in tif <vbr>fnvironmfnt</vbr> mbp, if bny.
     * @pbrbm fnvironmfnt An fnvironmfnt mbp, possibly dontbining bn ORB.
     * @rfturn An ORB.
     * @fxdfption IllfgblArgumfntExdfption if tif
     *      <tt>jbvb.nbming.dorbb.orb</tt> propfrty is spfdififd bnd
     *      dofs not point to bn {@link org.omg.CORBA.ORB ORB}.
     * @fxdfption IOExdfption if tif ORB initiblizbtion fbilfd.
     **/
    stbtid Objfdt rfsolvfOrb(Mbp<String, ?> fnvironmfnt)
        tirows IOExdfption {
        if (fnvironmfnt != null) {
            finbl Objfdt orb = fnvironmfnt.gft(EnvHflp.DEFAULT_ORB);
            if (orb != null && !(IIOPHflpfr.isOrb(orb)))
                tirow nfw IllfgblArgumfntExdfption(EnvHflp.DEFAULT_ORB +
                        " must bf bn instbndf of org.omg.CORBA.ORB.");
            if (orb != null) rfturn orb;
        }
        finbl Objfdt orb =
                (RMIConnfdtor.orb==null)?null:RMIConnfdtor.orb.gft();
        if (orb != null) rfturn orb;

        finbl Objfdt nfwOrb =
                IIOPHflpfr.drfbtfOrb((String[])null, (Propfrtifs)null);
        RMIConnfdtor.orb = nfw WfbkRfffrfndf<Objfdt>(nfwOrb);
        rfturn nfwOrb;
    }

    /**
     * Rfbd RMIConnfdtor fiflds from bn {@link jbvb.io.ObjfdtInputStrfbm
     * ObjfdtInputStrfbm}.
     * Cblls <dodf>s.dffbultRfbdObjfdt()</dodf> bnd tifn initiblizfs
     * bll trbnsifnt vbribblfs tibt nffd initiblizing.
     * @pbrbm s Tif ObjfdtInputStrfbm to rfbd from.
     * @fxdfption InvblidObjfdtExdfption if nonf of <vbr>rmiSfrvfr</vbr> stub
     *    or <vbr>jmxSfrvidfURL</vbr> brf sft.
     * @sff #RMIConnfdtor(JMXSfrvidfURL,Mbp)
     * @sff #RMIConnfdtor(RMISfrvfr,Mbp)
     **/
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
    tirows IOExdfption, ClbssNotFoundExdfption  {
        s.dffbultRfbdObjfdt();

        if (rmiSfrvfr == null && jmxSfrvidfURL == null) tirow nfw
                InvblidObjfdtExdfption("rmiSfrvfr bnd jmxSfrvidfURL boti null");

        initTrbnsifnts();
    }

    /**
     * Writfs tif RMIConnfdtor fiflds to bn {@link jbvb.io.ObjfdtOutputStrfbm
     * ObjfdtOutputStrfbm}.
     * <p>Connfdts tif undfrlying RMISfrvfr stub to bn ORB, if nffdfd,
     * bfforf sfriblizing it. Tiis is donf using tif fnvironmfnt
     * mbp tibt wbs providfd to tif donstrudtor, if bny, bnd bs dodumfntfd
     * in {@link jbvbx.mbnbgfmfnt.rfmotf.rmi}.</p>
     * <p>Tiis mftiod tifn dblls <dodf>s.dffbultWritfObjfdt()</dodf>.
     * Usublly, <vbr>rmiSfrvfr</vbr> is null if tiis objfdt
     * wbs donstrudtfd witi b JMXSfrvidfURL, bnd <vbr>jmxSfrvidfURL</vbr>
     * is null if tiis objfdt is donstrudtfd witi b RMISfrvfr stub.
     * <p>Notf tibt tif fnvironmfnt Mbp is not sfriblizfd, sindf tif objfdts
     * it dontbins brf bssumfd to bf dontfxtubl bnd rflfvbnt only
     * witi rfspfdt to tif lodbl fnvironmfnt (dlbss lobdfr, ORB, ftd...).</p>
     * <p>Aftfr bn RMIConnfdtor is dfsfriblizfd, it is bssumfd tibt tif
     * usfr will dbll {@link #donnfdt(Mbp)}, providing b nfw Mbp tibt
     * dbn dontbin vblufs wiidi brf dontfxtublly rflfvbnt to tif nfw
     * lodbl fnvironmfnt.</p>
     * <p>Sindf donnfdtion to tif ORB is nffdfd prior to sfriblizing, bnd
     * sindf tif ORB to donnfdt to is onf of tiosf dontfxtubl pbrbmftfrs,
     * it is not rfdommfndfd to rf-sfriblizf b just df-sfriblizfd objfdt -
     * bs tif df-sfriblizfd objfdt ibs no mbp. Tius, wifn bn RMIConnfdtor
     * objfdt is nffdfd for sfriblizbtion or trbnsmission to b rfmotf
     * bpplidbtion, it is rfdommfndfd to obtbin b nfw RMIConnfdtor stub
     * by dblling {@link RMIConnfdtorSfrvfr#toJMXConnfdtor(Mbp)}.</p>
     * @pbrbm s Tif ObjfdtOutputStrfbm to writf to.
     * @fxdfption InvblidObjfdtExdfption if nonf of <vbr>rmiSfrvfr</vbr> stub
     *    or <vbr>jmxSfrvidfURL</vbr> brf sft.
     * @sff #RMIConnfdtor(JMXSfrvidfURL,Mbp)
     * @sff #RMIConnfdtor(RMISfrvfr,Mbp)
     **/
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
    tirows IOExdfption {
        if (rmiSfrvfr == null && jmxSfrvidfURL == null) tirow nfw
                InvblidObjfdtExdfption("rmiSfrvfr bnd jmxSfrvidfURL boti null.");
        donnfdtStub(tiis.rmiSfrvfr,fnv);
        s.dffbultWritfObjfdt();
    }

    // Initiblizbtion of trbnsifnt vbribblfs.
    privbtf void initTrbnsifnts() {
        rmbsdMbp = nfw WfbkHbsiMbp<Subjfdt, WfbkRfffrfndf<MBfbnSfrvfrConnfdtion>>();
        donnfdtfd = fblsf;
        tfrminbtfd = fblsf;

        donnfdtionBrobddbstfr = nfw NotifidbtionBrobddbstfrSupport();
    }

    //--------------------------------------------------------------------
    // Privbtf stuff - Cifdk if stub dbn bf trustfd.
    //--------------------------------------------------------------------

    privbtf stbtid void difdkStub(Rfmotf stub,
            Clbss<?> stubClbss) {

        // Cifdk rfmotf stub is from tif fxpfdtfd dlbss.
        //
        if (stub.gftClbss() != stubClbss) {
            if (!Proxy.isProxyClbss(stub.gftClbss())) {
                tirow nfw SfdurityExdfption(
                        "Expfdting b " + stubClbss.gftNbmf() + " stub!");
            } flsf {
                InvodbtionHbndlfr ibndlfr = Proxy.gftInvodbtionHbndlfr(stub);
                if (ibndlfr.gftClbss() != RfmotfObjfdtInvodbtionHbndlfr.dlbss)
                    tirow nfw SfdurityExdfption(
                            "Expfdting b dynbmid proxy instbndf witi b " +
                            RfmotfObjfdtInvodbtionHbndlfr.dlbss.gftNbmf() +
                            " invodbtion ibndlfr!");
                flsf
                    stub = (Rfmotf) ibndlfr;
            }
        }

        // Cifdk RfmotfRff in stub is from tif fxpfdtfd dlbss
        // "sun.rmi.sfrvfr.UnidbstRff2".
        //
        RfmotfRff rff = ((RfmotfObjfdt)stub).gftRff();
        if (rff.gftClbss() != UnidbstRff2.dlbss)
            tirow nfw SfdurityExdfption(
                    "Expfdting b " + UnidbstRff2.dlbss.gftNbmf() +
                    " rfmotf rfffrfndf in stub!");

        // Cifdk RMIClifntSodkftFbdtory in stub is from tif fxpfdtfd dlbss
        // "jbvbx.rmi.ssl.SslRMIClifntSodkftFbdtory".
        //
        LivfRff livfRff = ((UnidbstRff2)rff).gftLivfRff();
        RMIClifntSodkftFbdtory dsf = livfRff.gftClifntSodkftFbdtory();
        if (dsf == null || dsf.gftClbss() != SslRMIClifntSodkftFbdtory.dlbss)
            tirow nfw SfdurityExdfption(
                    "Expfdting b " + SslRMIClifntSodkftFbdtory.dlbss.gftNbmf() +
                    " RMI dlifnt sodkft fbdtory in stub!");
    }

    //--------------------------------------------------------------------
    // Privbtf stuff - RMISfrvfr drfbtion
    //--------------------------------------------------------------------

    privbtf RMISfrvfr findRMISfrvfr(JMXSfrvidfURL dirfdtoryURL,
            Mbp<String, Objfdt> fnvironmfnt)
            tirows NbmingExdfption, IOExdfption {
        finbl boolfbn isIiop = RMIConnfdtorSfrvfr.isIiopURL(dirfdtoryURL,truf);
        if (isIiop) {
            // Mbkf surf jbvb.nbming.dorbb.orb is in tif Mbp.
            fnvironmfnt.put(EnvHflp.DEFAULT_ORB,rfsolvfOrb(fnvironmfnt));
        }

        String pbti = dirfdtoryURL.gftURLPbti();
        int fnd = pbti.indfxOf(';');
        if (fnd < 0) fnd = pbti.lfngti();
        if (pbti.stbrtsWiti("/jndi/"))
            rfturn findRMISfrvfrJNDI(pbti.substring(6,fnd), fnvironmfnt, isIiop);
        flsf if (pbti.stbrtsWiti("/stub/"))
            rfturn findRMISfrvfrJRMP(pbti.substring(6,fnd), fnvironmfnt, isIiop);
        flsf if (pbti.stbrtsWiti("/ior/")) {
            if (!IIOPHflpfr.isAvbilbblf())
                tirow nfw IOExdfption("iiop protodol not bvbilbblf");
            rfturn findRMISfrvfrIIOP(pbti.substring(5,fnd), fnvironmfnt, isIiop);
        } flsf {
            finbl String msg = "URL pbti must bfgin witi /jndi/ or /stub/ " +
                    "or /ior/: " + pbti;
            tirow nfw MblformfdURLExdfption(msg);
        }
    }

    /**
     * Lookup tif RMISfrvfr stub in b dirfdtory.
     * @pbrbm jndiURL A JNDI URL indidbting tif lodbtion of tif Stub
     *                (sff {@link jbvbx.mbnbgfmfnt.rfmotf.rmi}), f.g.:
     *   <ul><li><tt>rmi://rfgistry-iost:port/rmi-stub-nbmf</tt></li>
     *       <li>or <tt>iiop://dosnbming-iost:port/iiop-stub-nbmf</tt></li>
     *       <li>or <tt>ldbp://ldbp-iost:port/jbvb-dontbinfr-dn</tt></li>
     *   </ul>
     * @pbrbm fnv tif fnvironmfnt Mbp pbssfd to tif donnfdtor.
     * @pbrbm isIiop truf if tif stub is fxpfdtfd to bf bn IIOP stub.
     * @rfturn Tif rftrifvfd RMISfrvfr stub.
     * @fxdfption NbmingExdfption if tif stub douldn't bf found.
     **/
    privbtf RMISfrvfr findRMISfrvfrJNDI(String jndiURL, Mbp<String, ?> fnv,
            boolfbn isIiop)
            tirows NbmingExdfption {

        InitiblContfxt dtx = nfw InitiblContfxt(EnvHflp.mbpToHbsitbblf(fnv));

        Objfdt objrff = dtx.lookup(jndiURL);
        dtx.dlosf();

        if (isIiop)
            rfturn nbrrowIIOPSfrvfr(objrff);
        flsf
            rfturn nbrrowJRMPSfrvfr(objrff);
    }

    privbtf stbtid RMISfrvfr nbrrowJRMPSfrvfr(Objfdt objrff) {

        rfturn (RMISfrvfr) objrff;
    }

    privbtf stbtid RMISfrvfr nbrrowIIOPSfrvfr(Objfdt objrff) {
        try {
            rfturn IIOPHflpfr.nbrrow(objrff, RMISfrvfr.dlbss);
        } dbtdi (ClbssCbstExdfption f) {
            if (loggfr.trbdfOn())
                loggfr.trbdf("nbrrowIIOPSfrvfr","Fbilfd to nbrrow objrff=" +
                        objrff + ": " + f);
            if (loggfr.dfbugOn()) loggfr.dfbug("nbrrowIIOPSfrvfr",f);
            rfturn null;
        }
    }

    privbtf RMISfrvfr findRMISfrvfrIIOP(String ior, Mbp<String, ?> fnv, boolfbn isIiop) {
        // dould forbid "rmi:" URL ifrf -- but do wf nffd to?
        finbl Objfdt orb = fnv.gft(EnvHflp.DEFAULT_ORB);
        finbl Objfdt stub = IIOPHflpfr.stringToObjfdt(orb, ior);
        rfturn IIOPHflpfr.nbrrow(stub, RMISfrvfr.dlbss);
    }

    privbtf RMISfrvfr findRMISfrvfrJRMP(String bbsf64, Mbp<String, ?> fnv, boolfbn isIiop)
        tirows IOExdfption {
        // dould forbid "iiop:" URL ifrf -- but do wf nffd to?
        finbl bytf[] sfriblizfd;
        try {
            sfriblizfd = bbsf64ToBytfArrby(bbsf64);
        } dbtdi (IllfgblArgumfntExdfption f) {
            tirow nfw MblformfdURLExdfption("Bbd BASE64 fndoding: " +
                    f.gftMfssbgf());
        }
        finbl BytfArrbyInputStrfbm bin = nfw BytfArrbyInputStrfbm(sfriblizfd);

        finbl ClbssLobdfr lobdfr = EnvHflp.rfsolvfClifntClbssLobdfr(fnv);
        finbl ObjfdtInputStrfbm oin =
                (lobdfr == null) ?
                    nfw ObjfdtInputStrfbm(bin) :
                    nfw ObjfdtInputStrfbmWitiLobdfr(bin, lobdfr);
        finbl Objfdt stub;
        try {
            stub = oin.rfbdObjfdt();
        } dbtdi (ClbssNotFoundExdfption f) {
            tirow nfw MblformfdURLExdfption("Clbss not found: " + f);
        }
        rfturn (RMISfrvfr)stub;
    }

    privbtf stbtid finbl dlbss ObjfdtInputStrfbmWitiLobdfr
            fxtfnds ObjfdtInputStrfbm {
        ObjfdtInputStrfbmWitiLobdfr(InputStrfbm in, ClbssLobdfr dl)
        tirows IOExdfption {
            supfr(in);
            tiis.lobdfr = dl;
        }

        @Ovfrridf
        protfdtfd Clbss<?> rfsolvfClbss(ObjfdtStrfbmClbss dlbssDfsd)
                tirows IOExdfption, ClbssNotFoundExdfption {
            String nbmf = dlbssDfsd.gftNbmf();
            RfflfdtUtil.difdkPbdkbgfAddfss(nbmf);
            rfturn Clbss.forNbmf(nbmf, fblsf, lobdfr);
        }

        privbtf finbl ClbssLobdfr lobdfr;
    }

    privbtf MBfbnSfrvfrConnfdtion gftConnfdtionWitiSubjfdt(Subjfdt dflfgbtionSubjfdt) {
        MBfbnSfrvfrConnfdtion donn = null;

        if (dflfgbtionSubjfdt == null) {
            if (nullSubjfdtConnRff == null
                    || (donn = nullSubjfdtConnRff.gft()) == null) {
                donn = nfw RfmotfMBfbnSfrvfrConnfdtion(null);
                nullSubjfdtConnRff = nfw WfbkRfffrfndf<MBfbnSfrvfrConnfdtion>(donn);
            }
        } flsf {
            WfbkRfffrfndf<MBfbnSfrvfrConnfdtion> wr = rmbsdMbp.gft(dflfgbtionSubjfdt);
            if (wr == null || (donn = wr.gft()) == null) {
                donn = nfw RfmotfMBfbnSfrvfrConnfdtion(dflfgbtionSubjfdt);
                rmbsdMbp.put(dflfgbtionSubjfdt, nfw WfbkRfffrfndf<MBfbnSfrvfrConnfdtion>(donn));
            }
        }
        rfturn donn;
    }

    /*
       Tif following sfdtion of dodf bvoids b dlbss lobding problfm
       witi RMI.  Tif problfm is tibt bn RMI stub, wifn dfsfriblizing
       b rfmotf mftiod rfturn vbluf or fxdfption, will first of bll
       donsult tif first non-bootstrbp dlbss lobdfr it finds in tif
       dbll stbdk.  Tiis dbn lfbd to bfibvior tibt is not portbblf
       bftwffn implfmfntbtions of tif JMX Rfmotf API.  Notbbly, bn
       implfmfntbtion on J2SE 1.4 will find tif RMI stub's lobdfr on
       tif stbdk.  But in J2SE 5, tiis stub is lobdfd by tif
       bootstrbp lobdfr, so RMI will find tif lobdfr of tif usfr dodf
       tibt dbllfd bn MBfbnSfrvfrConnfdtion mftiod.

       To bvoid tiis problfm, wf tbkf bdvbntbgf of wibt tif RMI stub
       is doing intfrnblly.  Ebdi rfmotf dbll will fnd up dblling
       rff.invokf(...), wifrf rff is tif RfmotfRff pbrbmftfr givfn to
       tif RMI stub's donstrudtor.  It is witiin tiis dbll tibt tif
       dfsfriblizbtion will ibppfn.  So wf fbbridbtf our own RfmotfRff
       tibt dflfgbtfs fvfrytiing to tif "rfbl" onf but tibt is lobdfd
       by b dlbss lobdfr tibt knows no otifr dlbssfs.  Tif dlbss
       lobdfr NoCbllStbdkClbssLobdfr dofs tiis: tif RfmotfRff is bn
       instbndf of tif dlbss nbmfd by proxyRffClbssNbmf, wiidi is
       fbbridbtfd by tif dlbss lobdfr using bytf dodf tibt is dffinfd
       by tif string bflow.

       Tif dbll stbdk wifn tif dfsfriblizbtion ibppfns is tius tiis:
       MBfbnSfrvfrConnfdtion.gftAttributf (or wibtfvfr)
       -> RMIConnfdtionImpl_Stub.gftAttributf
          -> ProxyRff.invokf(...gftAttributf...)
             -> UnidbstRff.invokf(...gftAttributf...)
                -> intfrnbl RMI stuff

       Hfrf UnidbstRff is tif RfmotfRff drfbtfd wifn tif stub wbs
       dfsfriblizfd (wiidi is of somf RMI intfrnbl dlbss).  It bnd tif
       "intfrnbl RMI stuff" brf lobdfd by tif bootstrbp lobdfr, so brf
       trbnspbrfnt to tif stbdk sfbrdi.  Tif first non-bootstrbp
       lobdfr found is our ProxyRffLobdfr, bs rfquirfd.

       In b futurf vfrsion of tiis dodf bs intfgrbtfd into J2SE 5,
       tiis workbround dould bf rfplbdfd by dirfdt bddfss to tif
       intfrnbls of RMI.  For now, wf usf tif sbmf dodf bbsf for J2SE
       bnd for tif stbndblonf Rfffrfndf Implfmfntbtion.

       Tif bytf dodf bflow fndodfs tif following dlbss, dompilfd using
       J2SE 1.4.2 witi tif -g:nonf option.

        pbdkbgf dom.sun.jmx.rfmotf.intfrnbl;

        import jbvb.lbng.rfflfdt.Mftiod;
        import jbvb.rmi.Rfmotf;
        import jbvb.rmi.sfrvfr.RfmotfRff;
        import dom.sun.jmx.rfmotf.intfrnbl.ProxyRff;

        publid dlbss PRff fxtfnds ProxyRff {
            publid PRff(RfmotfRff rff) {
                supfr(rff);
            }

            publid Objfdt invokf(Rfmotf obj, Mftiod mftiod,
                                 Objfdt[] pbrbms, long opnum)
                    tirows Exdfption {
                rfturn rff.invokf(obj, mftiod, pbrbms, opnum);
            }
        }
     */

    privbtf stbtid finbl String rmiSfrvfrImplStubClbssNbmf =
        RMISfrvfr.dlbss.gftNbmf() + "Impl_Stub";
    privbtf stbtid finbl Clbss<?> rmiSfrvfrImplStubClbss;
    privbtf stbtid finbl String rmiConnfdtionImplStubClbssNbmf =
            RMIConnfdtion.dlbss.gftNbmf() + "Impl_Stub";
    privbtf stbtid finbl Clbss<?> rmiConnfdtionImplStubClbss;
    privbtf stbtid finbl String pRffClbssNbmf =
        "dom.sun.jmx.rfmotf.intfrnbl.PRff";
    privbtf stbtid finbl Construdtor<?> proxyRffConstrudtor;
    stbtid {
        finbl String pRffBytfCodfString =
                "\312\376\272\276\0\0\0.\0\27\12\0\5\0\15\11\0\4\0\16\13\0\17\0"+
                "\20\7\0\21\7\0\22\1\0\6<init>\1\0\36(Ljbvb/rmi/sfrvfr/RfmotfRff;"+
                ")V\1\0\4Codf\1\0\6invokf\1\0S(Ljbvb/rmi/Rfmotf;Ljbvb/lbng/rfflfd"+
                "t/Mftiod;[Ljbvb/lbng/Objfdt;J)Ljbvb/lbng/Objfdt;\1\0\12Exdfption"+
                "s\7\0\23\14\0\6\0\7\14\0\24\0\25\7\0\26\14\0\11\0\12\1\0\40dom/"+
                "sun/jmx/rfmotf/intfrnbl/PRff\1\0$dom/sun/jmx/rfmotf/intfrnbl/Pr"+
                "oxyRff\1\0\23jbvb/lbng/Exdfption\1\0\3rff\1\0\33Ljbvb/rmi/sfrvf"+
                "r/RfmotfRff;\1\0\31jbvb/rmi/sfrvfr/RfmotfRff\0!\0\4\0\5\0\0\0\0"+
                "\0\2\0\1\0\6\0\7\0\1\0\10\0\0\0\22\0\2\0\2\0\0\0\6*+\267\0\1\261"+
                "\0\0\0\0\0\1\0\11\0\12\0\2\0\10\0\0\0\33\0\6\0\6\0\0\0\17*\264\0"+
                "\2+,-\26\4\271\0\3\6\0\260\0\0\0\0\0\13\0\0\0\4\0\1\0\14\0\0";
        finbl bytf[] pRffBytfCodf =
                NoCbllStbdkClbssLobdfr.stringToBytfs(pRffBytfCodfString);
        PrivilfgfdExdfptionAdtion<Construdtor<?>> bdtion =
                nfw PrivilfgfdExdfptionAdtion<Construdtor<?>>() {
            publid Construdtor<?> run() tirows Exdfption {
                Clbss<RMIConnfdtor> tiisClbss = RMIConnfdtor.dlbss;
                ClbssLobdfr tiisLobdfr = tiisClbss.gftClbssLobdfr();
                ProtfdtionDombin tiisProtfdtionDombin =
                        tiisClbss.gftProtfdtionDombin();
                String[] otifrClbssNbmfs = {ProxyRff.dlbss.gftNbmf()};
                ClbssLobdfr dl =
                        nfw NoCbllStbdkClbssLobdfr(pRffClbssNbmf,
                        pRffBytfCodf,
                        otifrClbssNbmfs,
                        tiisLobdfr,
                        tiisProtfdtionDombin);
                Clbss<?> d = dl.lobdClbss(pRffClbssNbmf);
                rfturn d.gftConstrudtor(RfmotfRff.dlbss);
            }
        };

        Clbss<?> sfrvfrStubClbss;
        try {
            sfrvfrStubClbss = Clbss.forNbmf(rmiSfrvfrImplStubClbssNbmf);
        } dbtdi (Exdfption f) {
            loggfr.frror("<dlinit>",
                    "Fbilfd to instbntibtf " +
                    rmiSfrvfrImplStubClbssNbmf + ": " + f);
            loggfr.dfbug("<dlinit>",f);
            sfrvfrStubClbss = null;
        }
        rmiSfrvfrImplStubClbss = sfrvfrStubClbss;

        Clbss<?> stubClbss;
        Construdtor<?> donstr;
        try {
            stubClbss = Clbss.forNbmf(rmiConnfdtionImplStubClbssNbmf);
            donstr = (Construdtor<?>) AddfssControllfr.doPrivilfgfd(bdtion);
        } dbtdi (Exdfption f) {
            loggfr.frror("<dlinit>",
                    "Fbilfd to initiblizf proxy rfffrfndf donstrudtor "+
                    "for " + rmiConnfdtionImplStubClbssNbmf + ": " + f);
            loggfr.dfbug("<dlinit>",f);
            stubClbss = null;
            donstr = null;
        }
        rmiConnfdtionImplStubClbss = stubClbss;
        proxyRffConstrudtor = donstr;
    }

    privbtf stbtid RMIConnfdtion sibdowJrmpStub(RfmotfObjfdt stub)
    tirows InstbntibtionExdfption, IllfgblAddfssExdfption,
            InvodbtionTbrgftExdfption, ClbssNotFoundExdfption,
            NoSudiMftiodExdfption {
        RfmotfRff rff = stub.gftRff();
        RfmotfRff proxyRff = (RfmotfRff)
            proxyRffConstrudtor.nfwInstbndf(nfw Objfdt[] {rff});
        finbl Construdtor<?> rmiConnfdtionImplStubConstrudtor =
            rmiConnfdtionImplStubClbss.gftConstrudtor(RfmotfRff.dlbss);
        Objfdt[] brgs = {proxyRff};
        RMIConnfdtion proxyStub = (RMIConnfdtion)
        rmiConnfdtionImplStubConstrudtor.nfwInstbndf(brgs);
        rfturn proxyStub;
    }

    /*
       Tif following dodf pfrforms b similbr tridk for RMI/IIOP to tif
       onf dfsdribfd bbovf for RMI/JRMP.  Unlikf JRMP, tiougi, wf
       dbn't fbsily insfrt bn objfdt bftwffn tif RMIConnfdtion stub
       bnd tif RMI/IIOP dfsfriblizbtion dodf, bs fxplbinfd bflow.

       A mftiod in bn RMI/IIOP stub dofs tif following.  It mbkfs bn
       org.omg.CORBA_2_3.portbblf.OutputStrfbm for fbdi rfqufst, bnd
       writfs tif pbrbmftfrs to it.  Tifn it dblls
       _invokf(OutputStrfbm) wiidi it inifrits from CORBA's
       ObjfdtImpl.  Tibt rfturns bn
       org.omg.CORBA_2_3.portbblf.InputStrfbm.  Tif rfturn vbluf is
       rfbd from tiis InputStrfbm.  So tif stbdk during
       dfsfriblizbtion looks likf tiis:

       MBfbnSfrvfrConnfdtion.gftAttributf (or wibtfvfr)
       -> _RMIConnfdtion_Stub.gftAttributf
          -> Util.rfbdAny (b CORBA mftiod)
             -> InputStrfbm.rfbd_bny
                -> intfrnbl CORBA stuff

       Wibt wf would ibvf *likfd* to ibvf donf would bf tif sbmf tiing
       bs for RMI/JRMP.  Wf drfbtf b "ProxyDflfgbtf" tibt is bn
       org.omg.CORBA.portbblf.Dflfgbtf tibt simply forwbrds fvfry
       opfrbtion to tif rfbl originbl Dflfgbtf from tif RMIConnfdtion
       stub, fxdfpt tibt tif InputStrfbm rfturnfd by _invokf is
       wrbppfd by b "ProxyInputStrfbm" tibt is lobdfd by our
       NoCbllStbdkClbssLobdfr.

       Unfortunbtfly, tiis dofsn't work, bt lfbst witi Sun's J2SE
       1.4.2, bfdbusf tif CORBA dodf is not dfsignfd to bllow you to
       dibngf Dflfgbtfs brbitrbrily.  You gft b ClbssCbstExdfption
       from dodf tibt fxpfdts tif Dflfgbtf to implfmfnt bn intfrnbl
       intfrfbdf.

       So instfbd wf do tif following.  Wf drfbtf b subdlbss of tif
       stub tibt ovfrridfs tif _invokf mftiod so bs to wrbp tif
       rfturnfd InputStrfbm in b ProxyInputStrfbm.  Wf drfbtf b
       subdlbss of ProxyInputStrfbm using tif NoCbllStbdkClbssLobdfr
       bnd ovfrridf its rfbd_bny bnd rfbd_vbluf(Clbss) mftiods.
       (Tifsf brf tif only mftiods dbllfd during dfsfriblizbtion of
       MBfbnSfrvfrConnfdtion rfturn vblufs.)  Wf fxtrbdt tif Dflfgbtf
       from tif originbl stub bnd insfrt it into our subdlbss stub,
       bnd bwby wf go.  Tif stbtf of b stub donsists solfly of its
       Dflfgbtf.

       Wf blso nffd to dbtdi ApplidbtionExdfption, wiidi will fndodf
       bny fxdfptions dfdlbrfd in tif tirows dlbusf of tif dbllfd
       mftiod.  Its InputStrfbm nffds to bf wrbppfd in b
       ProxyInputStfbm too.

       Wf ovfrridf _rflfbsfRfply in tif stub subdlbss so tibt it
       rfplbdfs b ProxyInputStrfbm brgumfnt witi tif originbl
       InputStrfbm.  Tiis bvoids problfms if tif implfmfntbtion of
       _rflfbsfRfply fnds up dbsting tiis InputStrfbm to bn
       implfmfntbtion-spfdifid intfrfbdf (wiidi in Sun's J2SE 5 it
       dofs).

       It is not stridtly nfdfssbry for tif stub subdlbss to bf lobdfd
       by b NoCbllStbdkClbssLobdfr, sindf tif dbll-stbdk sfbrdi stops
       bt tif ProxyInputStrfbm subdlbss.  Howfvfr, it is donvfnifnt
       for two rfbsons.  Onf is tibt it mfbns tibt tif
       ProxyInputStrfbm subdlbss dbn bf bddfssfd dirfdtly, witiout
       using rfflfdtion.  Tif otifr is tibt it bvoids build problfms,
       sindf usublly stubs brf drfbtfd bftfr otifr dlbssfs brf
       dompilfd, so wf dbn't bddfss tifm from tiis dlbss witiout,
       bgbin, using rfflfdtion.

       Tif strings bflow fndodf tif following two Jbvb dlbssfs,
       dompilfd using jbvbd -g:nonf.

        pbdkbgf dom.sun.jmx.rfmotf.protodol.iiop;

        import org.omg.stub.jbvbx.mbnbgfmfnt.rfmotf.rmi._RMIConnfdtion_Stub;

        import org.omg.CORBA.portbblf.ApplidbtionExdfption;
        import org.omg.CORBA.portbblf.InputStrfbm;
        import org.omg.CORBA.portbblf.OutputStrfbm;
        import org.omg.CORBA.portbblf.RfmbrsiblExdfption;

        publid dlbss ProxyStub fxtfnds _RMIConnfdtion_Stub {
            publid InputStrfbm _invokf(OutputStrfbm out)
                    tirows ApplidbtionExdfption, RfmbrsiblExdfption {
                try {
                    rfturn nfw PInputStrfbm(supfr._invokf(out));
                } dbtdi (ApplidbtionExdfption f) {
                    InputStrfbm pis = nfw PInputStrfbm(f.gftInputStrfbm());
                    tirow nfw ApplidbtionExdfption(f.gftId(), pis);
                }
            }

            publid void _rflfbsfRfply(InputStrfbm in) {
                if (in != null)
                    in = ((PInputStrfbm)in).gftProxifdInputStrfbm();
                supfr._rflfbsfRfply(in);
            }
        }

        pbdkbgf dom.sun.jmx.rfmotf.protodol.iiop;

        publid dlbss PInputStrfbm fxtfnds ProxyInputStrfbm {
            publid PInputStrfbm(org.omg.CORBA.portbblf.InputStrfbm in) {
                supfr(in);
            }

            publid org.omg.CORBA.Any rfbd_bny() {
                rfturn in.rfbd_bny();
            }

            publid jbvb.io.Sfriblizbblf rfbd_vbluf(Clbss dlz) {
                rfturn nbrrow().rfbd_vbluf(dlz);
            }
        }


     */
    privbtf stbtid finbl String iiopConnfdtionStubClbssNbmf =
        "org.omg.stub.jbvbx.mbnbgfmfnt.rfmotf.rmi._RMIConnfdtion_Stub";
    privbtf stbtid finbl String proxyStubClbssNbmf =
        "dom.sun.jmx.rfmotf.protodol.iiop.ProxyStub";
    privbtf stbtid finbl String ProxyInputStrfbmClbssNbmf =
        "dom.sun.jmx.rfmotf.protodol.iiop.ProxyInputStrfbm";
    privbtf stbtid finbl String pInputStrfbmClbssNbmf =
        "dom.sun.jmx.rfmotf.protodol.iiop.PInputStrfbm";
    privbtf stbtid finbl Clbss<?> proxyStubClbss;
    stbtid {
        finbl String proxyStubBytfCodfString =
                "\312\376\272\276\0\0\0\63\0+\12\0\14\0\30\7\0\31\12\0\14\0\32\12"+
                "\0\2\0\33\7\0\34\12\0\5\0\35\12\0\5\0\36\12\0\5\0\37\12\0\2\0 "+
                "\12\0\14\0!\7\0\"\7\0#\1\0\6<init>\1\0\3()V\1\0\4Codf\1\0\7_in"+
                "vokf\1\0K(Lorg/omg/CORBA/portbblf/OutputStrfbm;)Lorg/omg/CORBA"+
                "/portbblf/InputStrfbm;\1\0\15StbdkMbpTbblf\7\0\34\1\0\12Exdfpt"+
                "ions\7\0$\1\0\15_rflfbsfRfply\1\0'(Lorg/omg/CORBA/portbblf/Inp"+
                "utStrfbm;)V\14\0\15\0\16\1\0-dom/sun/jmx/rfmotf/protodol/iiop/"+
                "PInputStrfbm\14\0\20\0\21\14\0\15\0\27\1\0+org/omg/CORBA/portb"+
                "blf/ApplidbtionExdfption\14\0%\0&\14\0'\0(\14\0\15\0)\14\0*\0&"+
                "\14\0\26\0\27\1\0*dom/sun/jmx/rfmotf/protodol/iiop/ProxyStub\1"+
                "\0<org/omg/stub/jbvbx/mbnbgfmfnt/rfmotf/rmi/_RMIConnfdtion_Stu"+
                "b\1\0)org/omg/CORBA/portbblf/RfmbrsiblExdfption\1\0\16gftInput"+
                "Strfbm\1\0&()Lorg/omg/CORBA/portbblf/InputStrfbm;\1\0\5gftId\1"+
                "\0\24()Ljbvb/lbng/String;\1\09(Ljbvb/lbng/String;Lorg/omg/CORB"+
                "A/portbblf/InputStrfbm;)V\1\0\25gftProxifdInputStrfbm\0!\0\13\0"+
                "\14\0\0\0\0\0\3\0\1\0\15\0\16\0\1\0\17\0\0\0\21\0\1\0\1\0\0\0\5"+
                "*\267\0\1\261\0\0\0\0\0\1\0\20\0\21\0\2\0\17\0\0\0G\0\4\0\4\0\0"+
                "\0'\273\0\2Y*+\267\0\3\267\0\4\260M\273\0\2Y,\266\0\6\267\0\4N"+
                "\273\0\5Y,\266\0\7-\267\0\10\277\0\1\0\0\0\14\0\15\0\5\0\1\0\22"+
                "\0\0\0\6\0\1M\7\0\23\0\24\0\0\0\6\0\2\0\5\0\25\0\1\0\26\0\27\0"+
                "\1\0\17\0\0\0'\0\2\0\2\0\0\0\22+\306\0\13+\300\0\2\266\0\11L*+"+
                "\267\0\12\261\0\0\0\1\0\22\0\0\0\3\0\1\14\0\0";
        finbl String pInputStrfbmBytfCodfString =
                "\312\376\272\276\0\0\0\63\0\36\12\0\7\0\17\11\0\6\0\20\12\0\21"+
                "\0\22\12\0\6\0\23\12\0\24\0\25\7\0\26\7\0\27\1\0\6<init>\1\0'("+
                "Lorg/omg/CORBA/portbblf/InputStrfbm;)V\1\0\4Codf\1\0\10rfbd_bn"+
                "y\1\0\25()Lorg/omg/CORBA/Any;\1\0\12rfbd_vbluf\1\0)(Ljbvb/lbng"+
                "/Clbss;)Ljbvb/io/Sfriblizbblf;\14\0\10\0\11\14\0\30\0\31\7\0\32"+
                "\14\0\13\0\14\14\0\33\0\34\7\0\35\14\0\15\0\16\1\0-dom/sun/jmx"+
                "/rfmotf/protodol/iiop/PInputStrfbm\1\0\61dom/sun/jmx/rfmotf/pr"+
                "otodol/iiop/ProxyInputStrfbm\1\0\2in\1\0$Lorg/omg/CORBA/portbb"+
                "lf/InputStrfbm;\1\0\"org/omg/CORBA/portbblf/InputStrfbm\1\0\6n"+
                "brrow\1\0*()Lorg/omg/CORBA_2_3/portbblf/InputStrfbm;\1\0&org/o"+
                "mg/CORBA_2_3/portbblf/InputStrfbm\0!\0\6\0\7\0\0\0\0\0\3\0\1\0"+
                "\10\0\11\0\1\0\12\0\0\0\22\0\2\0\2\0\0\0\6*+\267\0\1\261\0\0\0"+
                "\0\0\1\0\13\0\14\0\1\0\12\0\0\0\24\0\1\0\1\0\0\0\10*\264\0\2\266"+
                "\0\3\260\0\0\0\0\0\1\0\15\0\16\0\1\0\12\0\0\0\25\0\2\0\2\0\0\0"+
                "\11*\266\0\4+\266\0\5\260\0\0\0\0\0\0";
        finbl bytf[] proxyStubBytfCodf =
                NoCbllStbdkClbssLobdfr.stringToBytfs(proxyStubBytfCodfString);
        finbl bytf[] pInputStrfbmBytfCodf =
                NoCbllStbdkClbssLobdfr.stringToBytfs(pInputStrfbmBytfCodfString);
        finbl String[] dlbssNbmfs={proxyStubClbssNbmf, pInputStrfbmClbssNbmf};
        finbl bytf[][] bytfCodfs = {proxyStubBytfCodf, pInputStrfbmBytfCodf};
        finbl String[] otifrClbssNbmfs = {
            iiopConnfdtionStubClbssNbmf,
            ProxyInputStrfbmClbssNbmf,
        };
        if (IIOPHflpfr.isAvbilbblf()) {
            PrivilfgfdExdfptionAdtion<Clbss<?>> bdtion =
                nfw PrivilfgfdExdfptionAdtion<Clbss<?>>() {
              publid Clbss<?> run() tirows Exdfption {
                Clbss<RMIConnfdtor> tiisClbss = RMIConnfdtor.dlbss;
                ClbssLobdfr tiisLobdfr = tiisClbss.gftClbssLobdfr();
                ProtfdtionDombin tiisProtfdtionDombin =
                        tiisClbss.gftProtfdtionDombin();
                ClbssLobdfr dl =
                        nfw NoCbllStbdkClbssLobdfr(dlbssNbmfs,
                        bytfCodfs,
                        otifrClbssNbmfs,
                        tiisLobdfr,
                        tiisProtfdtionDombin);
                rfturn dl.lobdClbss(proxyStubClbssNbmf);
              }
            };
            Clbss<?> stubClbss;
            try {
                stubClbss = AddfssControllfr.doPrivilfgfd(bdtion);
            } dbtdi (Exdfption f) {
                loggfr.frror("<dlinit>",
                        "Unfxpfdtfd fxdfption mbking sibdow IIOP stub dlbss: "+f);
                loggfr.dfbug("<dlinit>",f);
                stubClbss = null;
            }
            proxyStubClbss = stubClbss;
        } flsf {
            proxyStubClbss = null;
        }
    }

  privbtf stbtid RMIConnfdtion sibdowIiopStub(Objfdt stub)
    tirows InstbntibtionExdfption, IllfgblAddfssExdfption {
        Objfdt proxyStub = null;
        try {
            proxyStub = AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdExdfptionAdtion<Objfdt>() {
                publid Objfdt run() tirows Exdfption {
                    rfturn proxyStubClbss.nfwInstbndf();
                }
            });
        } dbtdi (PrivilfgfdAdtionExdfption f) {
            tirow nfw IntfrnblError();
        }
        IIOPHflpfr.sftDflfgbtf(proxyStub, IIOPHflpfr.gftDflfgbtf(stub));
        rfturn (RMIConnfdtion) proxyStub;
    }
    privbtf stbtid RMIConnfdtion gftConnfdtion(RMISfrvfr sfrvfr,
            Objfdt drfdfntibls,
            boolfbn difdkStub)
            tirows IOExdfption {
        RMIConnfdtion d = sfrvfr.nfwClifnt(drfdfntibls);
        if (difdkStub) difdkStub(d, rmiConnfdtionImplStubClbss);
        try {
            if (d.gftClbss() == rmiConnfdtionImplStubClbss)
                rfturn sibdowJrmpStub((RfmotfObjfdt) d);
            if (d.gftClbss().gftNbmf().fqubls(iiopConnfdtionStubClbssNbmf))
                rfturn sibdowIiopStub(d);
            loggfr.trbdf("gftConnfdtion",
                    "Did not wrbp " + d.gftClbss() + " to foil " +
                    "stbdk sfbrdi for dlbssfs: dlbss lobding sfmbntids " +
                    "mby bf indorrfdt");
        } dbtdi (Exdfption f) {
            loggfr.frror("gftConnfdtion",
                    "Could not wrbp " + d.gftClbss() + " to foil " +
                    "stbdk sfbrdi for dlbssfs: dlbss lobding sfmbntids " +
                    "mby bf indorrfdt: " + f);
            loggfr.dfbug("gftConnfdtion",f);
            // so just rfturn tif originbl stub, wiidi will work for bll
            // but tif most fxotid dlbss lobding situbtions
        }
        rfturn d;
    }

    privbtf stbtid bytf[] bbsf64ToBytfArrby(String s) {
        int sLfn = s.lfngti();
        int numGroups = sLfn/4;
        if (4*numGroups != sLfn)
            tirow nfw IllfgblArgumfntExdfption(
                    "String lfngti must bf b multiplf of four.");
        int missingBytfsInLbstGroup = 0;
        int numFullGroups = numGroups;
        if (sLfn != 0) {
            if (s.dibrAt(sLfn-1) == '=') {
                missingBytfsInLbstGroup++;
                numFullGroups--;
            }
            if (s.dibrAt(sLfn-2) == '=')
                missingBytfsInLbstGroup++;
        }
        bytf[] rfsult = nfw bytf[3*numGroups - missingBytfsInLbstGroup];

        // Trbnslbtf bll full groups from bbsf64 to bytf brrby flfmfnts
        int inCursor = 0, outCursor = 0;
        for (int i=0; i<numFullGroups; i++) {
            int di0 = bbsf64toInt(s.dibrAt(inCursor++));
            int di1 = bbsf64toInt(s.dibrAt(inCursor++));
            int di2 = bbsf64toInt(s.dibrAt(inCursor++));
            int di3 = bbsf64toInt(s.dibrAt(inCursor++));
            rfsult[outCursor++] = (bytf) ((di0 << 2) | (di1 >> 4));
            rfsult[outCursor++] = (bytf) ((di1 << 4) | (di2 >> 2));
            rfsult[outCursor++] = (bytf) ((di2 << 6) | di3);
        }

        // Trbnslbtf pbrtibl group, if prfsfnt
        if (missingBytfsInLbstGroup != 0) {
            int di0 = bbsf64toInt(s.dibrAt(inCursor++));
            int di1 = bbsf64toInt(s.dibrAt(inCursor++));
            rfsult[outCursor++] = (bytf) ((di0 << 2) | (di1 >> 4));

            if (missingBytfsInLbstGroup == 1) {
                int di2 = bbsf64toInt(s.dibrAt(inCursor++));
                rfsult[outCursor++] = (bytf) ((di1 << 4) | (di2 >> 2));
            }
        }
        // bssfrt inCursor == s.lfngti()-missingBytfsInLbstGroup;
        // bssfrt outCursor == rfsult.lfngti;
        rfturn rfsult;
    }

    /**
     * Trbnslbtfs tif spfdififd dibrbdtfr, wiidi is bssumfd to bf in tif
     * "Bbsf 64 Alpibbft" into its fquivblfnt 6-bit positivf intfgfr.
     *
     * @tirows IllfgblArgumfntExdfption if
     *        d is not in tif Bbsf64 Alpibbft.
     */
    privbtf stbtid int bbsf64toInt(dibr d) {
        int rfsult;

        if (d >= bbsf64ToInt.lfngti)
            rfsult = -1;
        flsf
            rfsult = bbsf64ToInt[d];

        if (rfsult < 0)
            tirow nfw IllfgblArgumfntExdfption("Illfgbl dibrbdtfr " + d);
        rfturn rfsult;
    }

    /**
     * Tiis brrby is b lookup tbblf tibt trbnslbtfs unidodf dibrbdtfrs
     * drbwn from tif "Bbsf64 Alpibbft" (bs spfdififd in Tbblf 1 of RFC 2045)
     * into tifir 6-bit positivf intfgfr fquivblfnts.  Cibrbdtfrs tibt
     * brf not in tif Bbsf64 blpibbft but fbll witiin tif bounds of tif
     * brrby brf trbnslbtfd to -1.
     */
    privbtf stbtid finbl bytf bbsf64ToInt[] = {
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54,
        55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4,
        5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
        24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34,
        35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51
    };

    //--------------------------------------------------------------------
    // Privbtf stuff - Find / Sft dffbult dlbss lobdfr
    //--------------------------------------------------------------------
    privbtf ClbssLobdfr pusiDffbultClbssLobdfr() {
        finbl Tirfbd t = Tirfbd.durrfntTirfbd();
        finbl ClbssLobdfr old =  t.gftContfxtClbssLobdfr();
        if (dffbultClbssLobdfr != null)
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    t.sftContfxtClbssLobdfr(dffbultClbssLobdfr);
                    rfturn null;
                }
            });
            rfturn old;
    }

    privbtf void popDffbultClbssLobdfr(finbl ClbssLobdfr old) {
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
            publid Void run() {
                Tirfbd.durrfntTirfbd().sftContfxtClbssLobdfr(old);
                rfturn null;
            }
        });
    }

    //--------------------------------------------------------------------
    // Privbtf vbribblfs
    //--------------------------------------------------------------------
    /**
     * @sfribl Tif RMISfrvfr stub of tif RMI JMX Connfdtor sfrvfr to
     * wiidi tiis dlifnt donnfdtor is (or will bf) donnfdtfd. Tiis
     * fifld dbn bf null wifn <vbr>jmxSfrvidfURL</vbr> is not
     * null. Tiis indludfs tif dbsf wifrf <vbr>jmxSfrvidfURL</vbr>
     * dontbins b sfriblizfd RMISfrvfr stub. If boti
     * <vbr>rmiSfrvfr</vbr> bnd <vbr>jmxSfrvidfURL</vbr> brf null tifn
     * sfriblizbtion will fbil.
     *
     * @sff #RMIConnfdtor(RMISfrvfr,Mbp)
     **/
    privbtf finbl RMISfrvfr rmiSfrvfr;

    /**
     * @sfribl Tif JMXSfrvidfURL of tif RMI JMX Connfdtor sfrvfr to
     * wiidi tiis dlifnt donnfdtor will bf donnfdtfd. Tiis fifld dbn
     * bf null wifn <vbr>rmiSfrvfr</vbr> is not null. If boti
     * <vbr>rmiSfrvfr</vbr> bnd <vbr>jmxSfrvidfURL</vbr> brf null tifn
     * sfriblizbtion will fbil.
     *
     * @sff #RMIConnfdtor(JMXSfrvidfURL,Mbp)
     **/
    privbtf finbl JMXSfrvidfURL jmxSfrvidfURL;

    // ---------------------------------------------------------
    // WARNING - WARNING - WARNING - WARNING - WARNING - WARNING
    // ---------------------------------------------------------
    // Any trbnsifnt vbribblf wiidi nffds to bf initiblizfd siould
    // bf initiblizfd in tif mftiod initTrbnsifnt()
    privbtf trbnsifnt Mbp<String, Objfdt> fnv;
    privbtf trbnsifnt ClbssLobdfr dffbultClbssLobdfr;
    privbtf trbnsifnt RMIConnfdtion donnfdtion;
    privbtf trbnsifnt String donnfdtionId;

    privbtf trbnsifnt long dlifntNotifSfqNo = 0;

    privbtf trbnsifnt WfbkHbsiMbp<Subjfdt, WfbkRfffrfndf<MBfbnSfrvfrConnfdtion>> rmbsdMbp;
    privbtf trbnsifnt WfbkRfffrfndf<MBfbnSfrvfrConnfdtion> nullSubjfdtConnRff = null;

    privbtf trbnsifnt RMINotifClifnt rmiNotifClifnt;
    // = nfw RMINotifClifnt(nfw Intfgfr(0));

    privbtf trbnsifnt long dlifntNotifCountfr = 0;

    privbtf trbnsifnt boolfbn donnfdtfd;
    // = fblsf;
    privbtf trbnsifnt boolfbn tfrminbtfd;
    // = fblsf;

    privbtf trbnsifnt Exdfption dlosfExdfption;

    privbtf trbnsifnt NotifidbtionBrobddbstfrSupport donnfdtionBrobddbstfr;

    privbtf trbnsifnt ClifntCommunidbtorAdmin dommunidbtorAdmin;

    /**
     * A stbtid WfbkRfffrfndf to bn {@link org.omg.CORBA.ORB ORB} to
     * donnfdt undonnfdtfd stubs.
     **/
    privbtf stbtid volbtilf WfbkRfffrfndf<Objfdt> orb = null;

    // TRACES & DEBUG
    //---------------
    privbtf stbtid String objfdts(finbl Objfdt[] objs) {
        if (objs == null)
            rfturn "null";
        flsf
            rfturn Arrbys.bsList(objs).toString();
    }

    privbtf stbtid String strings(finbl String[] strs) {
        rfturn objfdts(strs);
    }
}
