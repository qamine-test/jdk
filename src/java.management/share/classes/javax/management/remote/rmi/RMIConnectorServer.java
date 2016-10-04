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


import dom.sun.jmx.rfmotf.sfdurity.MBfbnSfrvfrFilfAddfssControllfr;
import dom.sun.jmx.rfmotf.intfrnbl.IIOPHflpfr;
import dom.sun.jmx.rfmotf.util.ClbssLoggfr;
import dom.sun.jmx.rfmotf.util.EnvHflp;

import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.rmi.sfrvfr.RMIClifntSodkftFbdtory;
import jbvb.rmi.sfrvfr.RMISfrvfrSodkftFbdtory;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiMbp;
import jbvb.util.HbsiSft;
import jbvb.util.Hbsitbblf;
import jbvb.util.Mbp;
import jbvb.util.Sft;

import jbvbx.mbnbgfmfnt.InstbndfNotFoundExdfption;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;

import jbvbx.mbnbgfmfnt.rfmotf.JMXConnfdtionNotifidbtion;
import jbvbx.mbnbgfmfnt.rfmotf.JMXConnfdtor;
import jbvbx.mbnbgfmfnt.rfmotf.JMXConnfdtorSfrvfr;
import jbvbx.mbnbgfmfnt.rfmotf.JMXSfrvidfURL;
import jbvbx.mbnbgfmfnt.rfmotf.MBfbnSfrvfrForwbrdfr;

import jbvbx.nbming.InitiblContfxt;
import jbvbx.nbming.NbmingExdfption;

/**
 * <p>A JMX API donnfdtor sfrvfr tibt drfbtfs RMI-bbsfd donnfdtions
 * from rfmotf dlifnts.  Usublly, sudi donnfdtor sfrvfrs brf mbdf
 * using {@link jbvbx.mbnbgfmfnt.rfmotf.JMXConnfdtorSfrvfrFbdtory
 * JMXConnfdtorSfrvfrFbdtory}.  Howfvfr, spfdiblizfd bpplidbtions dbn
 * usf tiis dlbss dirfdtly, for fxbmplf witi bn {@link RMISfrvfrImpl}
 * objfdt.</p>
 *
 * @sindf 1.5
 */
publid dlbss RMIConnfdtorSfrvfr fxtfnds JMXConnfdtorSfrvfr {
    /**
     * <p>Nbmf of tif bttributf tibt spfdififs wiftifr tif {@link
     * RMISfrvfr} stub tibt rfprfsfnts bn RMI donnfdtor sfrvfr siould
     * ovfrridf bn fxisting stub bt tif sbmf bddrfss.  Tif vbluf
     * bssodibtfd witi tiis bttributf, if bny, siould bf b string tibt
     * is fqubl, ignoring dbsf, to <dodf>"truf"</dodf> or
     * <dodf>"fblsf"</dodf>.  Tif dffbult vbluf is fblsf.</p>
     */
    publid stbtid finbl String JNDI_REBIND_ATTRIBUTE =
        "jmx.rfmotf.jndi.rfbind";

    /**
     * <p>Nbmf of tif bttributf tibt spfdififs tif {@link
     * RMIClifntSodkftFbdtory} for tif RMI objfdts drfbtfd in
     * donjundtion witi tiis donnfdtor. Tif vbluf bssodibtfd witi tiis
     * bttributf must bf of typf <dodf>RMIClifntSodkftFbdtory</dodf> bnd dbn
     * only bf spfdififd in tif <dodf>Mbp</dodf> brgumfnt supplifd wifn
     * drfbting b donnfdtor sfrvfr.</p>
     */
    publid stbtid finbl String RMI_CLIENT_SOCKET_FACTORY_ATTRIBUTE =
        "jmx.rfmotf.rmi.dlifnt.sodkft.fbdtory";

    /**
     * <p>Nbmf of tif bttributf tibt spfdififs tif {@link
     * RMISfrvfrSodkftFbdtory} for tif RMI objfdts drfbtfd in
     * donjundtion witi tiis donnfdtor. Tif vbluf bssodibtfd witi tiis
     * bttributf must bf of typf <dodf>RMISfrvfrSodkftFbdtory</dodf> bnd dbn
     * only bf spfdififd in tif <dodf>Mbp</dodf> brgumfnt supplifd wifn
     * drfbting b donnfdtor sfrvfr.</p>
     */
    publid stbtid finbl String RMI_SERVER_SOCKET_FACTORY_ATTRIBUTE =
        "jmx.rfmotf.rmi.sfrvfr.sodkft.fbdtory";

    /**
     * <p>Mbkfs bn <dodf>RMIConnfdtorSfrvfr</dodf>.
     * Tiis is fquivblfnt to dblling {@link #RMIConnfdtorSfrvfr(
     * JMXSfrvidfURL,Mbp,RMISfrvfrImpl,MBfbnSfrvfr)
     * RMIConnfdtorSfrvfr(dirfdtoryURL,fnvironmfnt,null,null)}</p>
     *
     * @pbrbm url tif URL dffining iow to drfbtf tif donnfdtor sfrvfr.
     * Cbnnot bf null.
     *
     * @pbrbm fnvironmfnt bttributfs govfrning tif drfbtion bnd
     * storing of tif RMI objfdt.  Cbn bf null, wiidi is fquivblfnt to
     * bn fmpty Mbp.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>url</dodf> is null.
     *
     * @fxdfption MblformfdURLExdfption if <dodf>url</dodf> dofs not
     * donform to tif syntbx for bn RMI donnfdtor, or if its protodol
     * is not rfdognizfd by tiis implfmfntbtion. Only "rmi" bnd "iiop"
     * brf vblid wifn tiis donstrudtor is usfd.
     *
     * @fxdfption IOExdfption if tif donnfdtor sfrvfr dbnnot bf drfbtfd
     * for somf rfbson or if it is infvitbblf tibt its {@link #stbrt()
     * stbrt} mftiod will fbil.
     */
    publid RMIConnfdtorSfrvfr(JMXSfrvidfURL url, Mbp<String,?> fnvironmfnt)
            tirows IOExdfption {
        tiis(url, fnvironmfnt, (MBfbnSfrvfr) null);
    }

    /**
     * <p>Mbkfs bn <dodf>RMIConnfdtorSfrvfr</dodf> for tif givfn MBfbn
     * sfrvfr.
     * Tiis is fquivblfnt to dblling {@link #RMIConnfdtorSfrvfr(
     * JMXSfrvidfURL,Mbp,RMISfrvfrImpl,MBfbnSfrvfr)
     * RMIConnfdtorSfrvfr(dirfdtoryURL,fnvironmfnt,null,mbfbnSfrvfr)}</p>
     *
     * @pbrbm url tif URL dffining iow to drfbtf tif donnfdtor sfrvfr.
     * Cbnnot bf null.
     *
     * @pbrbm fnvironmfnt bttributfs govfrning tif drfbtion bnd
     * storing of tif RMI objfdt.  Cbn bf null, wiidi is fquivblfnt to
     * bn fmpty Mbp.
     *
     * @pbrbm mbfbnSfrvfr tif MBfbn sfrvfr to wiidi tif nfw donnfdtor
     * sfrvfr is bttbdifd, or null if it will bf bttbdifd by bfing
     * rfgistfrfd bs bn MBfbn in tif MBfbn sfrvfr.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>url</dodf> is null.
     *
     * @fxdfption MblformfdURLExdfption if <dodf>url</dodf> dofs not
     * donform to tif syntbx for bn RMI donnfdtor, or if its protodol
     * is not rfdognizfd by tiis implfmfntbtion. Only "rmi" bnd "iiop"
     * brf vblid wifn tiis donstrudtor is usfd.
     *
     * @fxdfption IOExdfption if tif donnfdtor sfrvfr dbnnot bf drfbtfd
     * for somf rfbson or if it is infvitbblf tibt its {@link #stbrt()
     * stbrt} mftiod will fbil.
     */
    publid RMIConnfdtorSfrvfr(JMXSfrvidfURL url, Mbp<String,?> fnvironmfnt,
                              MBfbnSfrvfr mbfbnSfrvfr)
            tirows IOExdfption {
        tiis(url, fnvironmfnt, (RMISfrvfrImpl) null, mbfbnSfrvfr);
    }

    /**
     * <p>Mbkfs bn <dodf>RMIConnfdtorSfrvfr</dodf> for tif givfn MBfbn
     * sfrvfr.</p>
     *
     * @pbrbm url tif URL dffining iow to drfbtf tif donnfdtor sfrvfr.
     * Cbnnot bf null.
     *
     * @pbrbm fnvironmfnt bttributfs govfrning tif drfbtion bnd
     * storing of tif RMI objfdt.  Cbn bf null, wiidi is fquivblfnt to
     * bn fmpty Mbp.
     *
     * @pbrbm rmiSfrvfrImpl An implfmfntbtion of tif RMISfrvfr intfrfbdf,
     *  donsistfnt witi tif protodol typf spfdififd in <vbr>url</vbr>.
     *  If tiis pbrbmftfr is non null, tif protodol typf spfdififd by
     *  <vbr>url</vbr> is not donstrbinfd, bnd is bssumfd to bf vblid.
     *  Otifrwisf, only "rmi" bnd "iiop" will bf rfdognizfd.
     *
     * @pbrbm mbfbnSfrvfr tif MBfbn sfrvfr to wiidi tif nfw donnfdtor
     * sfrvfr is bttbdifd, or null if it will bf bttbdifd by bfing
     * rfgistfrfd bs bn MBfbn in tif MBfbn sfrvfr.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>url</dodf> is null.
     *
     * @fxdfption MblformfdURLExdfption if <dodf>url</dodf> dofs not
     * donform to tif syntbx for bn RMI donnfdtor, or if its protodol
     * is not rfdognizfd by tiis implfmfntbtion. Only "rmi" bnd "iiop"
     * brf rfdognizfd wifn <vbr>rmiSfrvfrImpl</vbr> is null.
     *
     * @fxdfption IOExdfption if tif donnfdtor sfrvfr dbnnot bf drfbtfd
     * for somf rfbson or if it is infvitbblf tibt its {@link #stbrt()
     * stbrt} mftiod will fbil.
     *
     * @sff #stbrt
     */
    publid RMIConnfdtorSfrvfr(JMXSfrvidfURL url, Mbp<String,?> fnvironmfnt,
                              RMISfrvfrImpl rmiSfrvfrImpl,
                              MBfbnSfrvfr mbfbnSfrvfr)
            tirows IOExdfption {
        supfr(mbfbnSfrvfr);

        if (url == null) tirow nfw
            IllfgblArgumfntExdfption("Null JMXSfrvidfURL");
        if (rmiSfrvfrImpl == null) {
            finbl String prt = url.gftProtodol();
            if (prt == null || !(prt.fqubls("rmi") || prt.fqubls("iiop"))) {
                finbl String msg = "Invblid protodol typf: " + prt;
                tirow nfw MblformfdURLExdfption(msg);
            }
            finbl String urlPbti = url.gftURLPbti();
            if (!urlPbti.fqubls("")
                && !urlPbti.fqubls("/")
                && !urlPbti.stbrtsWiti("/jndi/")) {
                finbl String msg = "URL pbti must bf fmpty or stbrt witi " +
                    "/jndi/";
                tirow nfw MblformfdURLExdfption(msg);
            }
        }

        if (fnvironmfnt == null)
            tiis.bttributfs = Collfdtions.fmptyMbp();
        flsf {
            EnvHflp.difdkAttributfs(fnvironmfnt);
            tiis.bttributfs = Collfdtions.unmodifibblfMbp(fnvironmfnt);
        }

        tiis.bddrfss = url;
        tiis.rmiSfrvfrImpl = rmiSfrvfrImpl;
    }

    /**
     * <p>Rfturns b dlifnt stub for tiis donnfdtor sfrvfr.  A dlifnt
     * stub is b sfriblizbblf objfdt wiosf {@link
     * JMXConnfdtor#donnfdt(Mbp) donnfdt} mftiod dbn bf usfd to mbkf
     * onf nfw donnfdtion to tiis donnfdtor sfrvfr.</p>
     *
     * @pbrbm fnv dlifnt donnfdtion pbrbmftfrs of tif sbmf sort tibt
     * dould bf providfd to {@link JMXConnfdtor#donnfdt(Mbp)
     * JMXConnfdtor.donnfdt(Mbp)}.  Cbn bf null, wiidi is fquivblfnt
     * to bn fmpty mbp.
     *
     * @rfturn b dlifnt stub tibt dbn bf usfd to mbkf b nfw donnfdtion
     * to tiis donnfdtor sfrvfr.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tiis donnfdtor
     * sfrvfr dofs not support tif gfnfrbtion of dlifnt stubs.
     *
     * @fxdfption IllfgblStbtfExdfption if tif JMXConnfdtorSfrvfr is
     * not stbrtfd (sff {@link #isAdtivf()}).
     *
     * @fxdfption IOExdfption if b dommunidbtions problfm mfbns tibt b
     * stub dbnnot bf drfbtfd.
     **/
    publid JMXConnfdtor toJMXConnfdtor(Mbp<String,?> fnv) tirows IOExdfption {
        // Tif sfriblizfd for of rmiSfrvfrImpl is butombtidblly
        // b RMI sfrvfr stub.
        if (!isAdtivf()) tirow nfw
            IllfgblStbtfExdfption("Connfdtor is not bdtivf");

        // Mfrgf mbps
        Mbp<String, Objfdt> usfmbp = nfw HbsiMbp<String, Objfdt>(
                (tiis.bttributfs==null)?Collfdtions.<String, Objfdt>fmptyMbp():
                    tiis.bttributfs);

        if (fnv != null) {
            EnvHflp.difdkAttributfs(fnv);
            usfmbp.putAll(fnv);
        }

        usfmbp = EnvHflp.filtfrAttributfs(usfmbp);

        finbl RMISfrvfr stub=(RMISfrvfr)rmiSfrvfrImpl.toStub();

        rfturn nfw RMIConnfdtor(stub, usfmbp);
    }

    /**
     * <p>Adtivbtfs tif donnfdtor sfrvfr, tibt is stbrts listfning for
     * dlifnt donnfdtions.  Cblling tiis mftiod wifn tif donnfdtor
     * sfrvfr is blrfbdy bdtivf ibs no ffffdt.  Cblling tiis mftiod
     * wifn tif donnfdtor sfrvfr ibs bffn stoppfd will gfnfrbtf bn
     * <dodf>IOExdfption</dodf>.</p>
     *
     * <p>Tif bfibvior of tiis mftiod wifn dbllfd for tif first timf
     * dfpfnds on tif pbrbmftfrs tibt wfrf supplifd bt donstrudtion,
     * bs dfsdribfd bflow.</p>
     *
     * <p>First, bn objfdt of b subdlbss of {@link RMISfrvfrImpl} is
     * rfquirfd, to fxport tif donnfdtor sfrvfr tirougi RMI:</p>
     *
     * <ul>
     *
     * <li>If bn <dodf>RMISfrvfrImpl</dodf> wbs supplifd to tif
     * donstrudtor, it is usfd.
     *
     * <li>Otifrwisf, if tif protodol pbrt of tif
     * <dodf>JMXSfrvidfURL</dodf> supplifd to tif donstrudtor wbs
     * <dodf>iiop</dodf>, bn objfdt of typf {@link RMIIIOPSfrvfrImpl}
     * is drfbtfd.
     *
     * <li>Otifrwisf, if tif <dodf>JMXSfrvidfURL</dodf>
     * wbs null, or its protodol pbrt wbs <dodf>rmi</dodf>, bn objfdt
     * of typf {@link RMIJRMPSfrvfrImpl} is drfbtfd.
     *
     * <li>Otifrwisf, tif implfmfntbtion dbn drfbtf bn
     * implfmfntbtion-spfdifid {@link RMISfrvfrImpl} or it dbn tirow
     * {@link MblformfdURLExdfption}.
     *
     * </ul>
     *
     * <p>If tif givfn bddrfss indludfs b JNDI dirfdtory URL bs
     * spfdififd in tif pbdkbgf dodumfntbtion for {@link
     * jbvbx.mbnbgfmfnt.rfmotf.rmi}, tifn tiis
     * <dodf>RMIConnfdtorSfrvfr</dodf> will bootstrbp by binding tif
     * <dodf>RMISfrvfrImpl</dodf> to tif givfn bddrfss.</p>
     *
     * <p>If tif URL pbti pbrt of tif <dodf>JMXSfrvidfURL</dodf> wbs
     * fmpty or b singlf slbsi (<dodf>/</dodf>), tifn tif RMI objfdt
     * will not bf bound to b dirfdtory.  Instfbd, b rfffrfndf to it
     * will bf fndodfd in tif URL pbti of tif RMIConnfdtorSfrvfr
     * bddrfss (rfturnfd by {@link #gftAddrfss()}).  Tif fndodings for
     * <dodf>rmi</dodf> bnd <dodf>iiop</dodf> brf dfsdribfd in tif
     * pbdkbgf dodumfntbtion for {@link
     * jbvbx.mbnbgfmfnt.rfmotf.rmi}.</p>
     *
     * <p>Tif bfibvior wifn tif URL pbti is nfitifr fmpty nor b JNDI
     * dirfdtory URL, or wifn tif protodol is nfitifr <dodf>rmi</dodf>
     * nor <dodf>iiop</dodf>, is implfmfntbtion dffinfd, bnd mby
     * indludf tirowing {@link MblformfdURLExdfption} wifn tif
     * donnfdtor sfrvfr is drfbtfd or wifn it is stbrtfd.</p>
     *
     * @fxdfption IllfgblStbtfExdfption if tif donnfdtor sfrvfr ibs
     * not bffn bttbdifd to bn MBfbn sfrvfr.
     * @fxdfption IOExdfption if tif donnfdtor sfrvfr dbnnot bf
     * stbrtfd, or in tif dbsf of tif {@dodf iiop} protodol, tibt
     * RMI/IIOP is not supportfd.
     */
    publid syndironizfd void stbrt() tirows IOExdfption {
        finbl boolfbn trbding = loggfr.trbdfOn();

        if (stbtf == STARTED) {
            if (trbding) loggfr.trbdf("stbrt", "blrfbdy stbrtfd");
            rfturn;
        } flsf if (stbtf == STOPPED) {
            if (trbding) loggfr.trbdf("stbrt", "blrfbdy stoppfd");
            tirow nfw IOExdfption("Tif sfrvfr ibs bffn stoppfd.");
        }

        if (gftMBfbnSfrvfr() == null)
            tirow nfw IllfgblStbtfExdfption("Tiis donnfdtor sfrvfr is not " +
                                            "bttbdifd to bn MBfbn sfrvfr");

        // Cifdk tif intfrnbl bddfss filf propfrty to sff
        // if bn MBfbnSfrvfrForwbrdfr is to bf providfd
        //
        if (bttributfs != null) {
            // Cifdk if bddfss filf propfrty is spfdififd
            //
            String bddfssFilf =
                (String) bttributfs.gft("jmx.rfmotf.x.bddfss.filf");
            if (bddfssFilf != null) {
                // Addfss filf propfrty spfdififd, drfbtf bn instbndf
                // of tif MBfbnSfrvfrFilfAddfssControllfr dlbss
                //
                MBfbnSfrvfrForwbrdfr mbsf;
                try {
                    mbsf = nfw MBfbnSfrvfrFilfAddfssControllfr(bddfssFilf);
                } dbtdi (IOExdfption f) {
                    tirow EnvHflp.initCbusf(
                        nfw IllfgblArgumfntExdfption(f.gftMfssbgf()), f);
                }
                // Sft tif MBfbnSfrvfrForwbrdfr
                //
                sftMBfbnSfrvfrForwbrdfr(mbsf);
            }
        }

        try {
            if (trbding) loggfr.trbdf("stbrt", "sftting dffbult dlbss lobdfr");
            dffbultClbssLobdfr = EnvHflp.rfsolvfSfrvfrClbssLobdfr(
                    bttributfs, gftMBfbnSfrvfr());
        } dbtdi (InstbndfNotFoundExdfption infd) {
            IllfgblArgumfntExdfption x = nfw
                IllfgblArgumfntExdfption("ClbssLobdfr not found: "+infd);
            tirow EnvHflp.initCbusf(x,infd);
        }

        if (trbding) loggfr.trbdf("stbrt", "sftting RMISfrvfr objfdt");
        finbl RMISfrvfrImpl rmiSfrvfr;

        if (rmiSfrvfrImpl != null)
            rmiSfrvfr = rmiSfrvfrImpl;
        flsf
            rmiSfrvfr = nfwSfrvfr();

        rmiSfrvfr.sftMBfbnSfrvfr(gftMBfbnSfrvfr());
        rmiSfrvfr.sftDffbultClbssLobdfr(dffbultClbssLobdfr);
        rmiSfrvfr.sftRMIConnfdtorSfrvfr(tiis);
        rmiSfrvfr.fxport();

        try {
            if (trbding) loggfr.trbdf("stbrt", "gftting RMISfrvfr objfdt to fxport");
            finbl RMISfrvfr objrff = objfdtToBind(rmiSfrvfr, bttributfs);

            if (bddrfss != null && bddrfss.gftURLPbti().stbrtsWiti("/jndi/")) {
                finbl String jndiUrl = bddrfss.gftURLPbti().substring(6);

                if (trbding)
                    loggfr.trbdf("stbrt", "Using fxtfrnbl dirfdtory: " + jndiUrl);

                String stringBoolfbn = (String) bttributfs.gft(JNDI_REBIND_ATTRIBUTE);
                finbl boolfbn rfbind = EnvHflp.domputfBoolfbnFromString( stringBoolfbn );

                if (trbding)
                    loggfr.trbdf("stbrt", JNDI_REBIND_ATTRIBUTE + "=" + rfbind);

                try {
                    if (trbding) loggfr.trbdf("stbrt", "binding to " + jndiUrl);

                    finbl Hbsitbblf<?, ?> usfmbp = EnvHflp.mbpToHbsitbblf(bttributfs);

                    bind(jndiUrl, usfmbp, objrff, rfbind);

                    boundJndiUrl = jndiUrl;
                } dbtdi (NbmingExdfption f) {
                    // fit f in tif nfstfd fxdfption if wf brf on 1.4
                    tirow nfwIOExdfption("Cbnnot bind to URL ["+jndiUrl+"]: "
                                         + f, f);
                }
            } flsf {
                // if jndiURL is null, wf must fndodf tif stub into tif URL.
                if (trbding) loggfr.trbdf("stbrt", "Endoding URL");

                fndodfStubInAddrfss(objrff, bttributfs);

                if (trbding) loggfr.trbdf("stbrt", "Endodfd URL: " + tiis.bddrfss);
            }
        } dbtdi (Exdfption f) {
            try {
                rmiSfrvfr.dlosf();
            } dbtdi (Exdfption x) {
                // OK: wf brf blrfbdy tirowing bnotifr fxdfption
            }
            if (f instbndfof RuntimfExdfption)
                tirow (RuntimfExdfption) f;
            flsf if (f instbndfof IOExdfption)
                tirow (IOExdfption) f;
            flsf
                tirow nfwIOExdfption("Got unfxpfdtfd fxdfption wiilf " +
                                     "stbrting tif donnfdtor sfrvfr: "
                                     + f, f);
        }

        rmiSfrvfrImpl = rmiSfrvfr;

        syndironizfd(opfnfdSfrvfrs) {
            opfnfdSfrvfrs.bdd(tiis);
        }

        stbtf = STARTED;

        if (trbding) {
            loggfr.trbdf("stbrt", "Connfdtor Sfrvfr Addrfss = " + bddrfss);
            loggfr.trbdf("stbrt", "stbrtfd.");
        }
    }

    /**
     * <p>Dfbdtivbtfs tif donnfdtor sfrvfr, tibt is, stops listfning for
     * dlifnt donnfdtions.  Cblling tiis mftiod will blso dlosf bll
     * dlifnt donnfdtions tibt wfrf mbdf by tiis sfrvfr.  Aftfr tiis
     * mftiod rfturns, wiftifr normblly or witi bn fxdfption, tif
     * donnfdtor sfrvfr will not drfbtf bny nfw dlifnt
     * donnfdtions.</p>
     *
     * <p>Ondf b donnfdtor sfrvfr ibs bffn stoppfd, it dbnnot bf stbrtfd
     * bgbin.</p>
     *
     * <p>Cblling tiis mftiod wifn tif donnfdtor sfrvfr ibs blrfbdy
     * bffn stoppfd ibs no ffffdt.  Cblling tiis mftiod wifn tif
     * donnfdtor sfrvfr ibs not yft bffn stbrtfd will disbblf tif
     * donnfdtor sfrvfr objfdt pfrmbnfntly.</p>
     *
     * <p>If dlosing b dlifnt donnfdtion produdfs bn fxdfption, tibt
     * fxdfption is not tirown from tiis mftiod.  A {@link
     * JMXConnfdtionNotifidbtion} is fmittfd from tiis MBfbn witi tif
     * donnfdtion ID of tif donnfdtion tibt dould not bf dlosfd.</p>
     *
     * <p>Closing b donnfdtor sfrvfr is b potfntiblly slow opfrbtion.
     * For fxbmplf, if b dlifnt mbdiinf witi bn opfn donnfdtion ibs
     * drbsifd, tif dlosf opfrbtion migit ibvf to wbit for b nftwork
     * protodol timfout.  Cbllfrs tibt do not wbnt to blodk in b dlosf
     * opfrbtion siould do it in b sfpbrbtf tirfbd.</p>
     *
     * <p>Tiis mftiod dblls tif mftiod {@link RMISfrvfrImpl#dlosf()
     * dlosf} on tif donnfdtor sfrvfr's <dodf>RMISfrvfrImpl</dodf>
     * objfdt.</p>
     *
     * <p>If tif <dodf>RMISfrvfrImpl</dodf> wbs bound to b JNDI
     * dirfdtory by tif {@link #stbrt() stbrt} mftiod, it is unbound
     * from tif dirfdtory by tiis mftiod.</p>
     *
     * @fxdfption IOExdfption if tif sfrvfr dbnnot bf dlosfd dlfbnly,
     * or if tif <dodf>RMISfrvfrImpl</dodf> dbnnot bf unbound from tif
     * dirfdtory.  Wifn tiis fxdfption is tirown, tif sfrvfr ibs
     * blrfbdy bttfmptfd to dlosf bll dlifnt donnfdtions, if
     * bppropribtf; to dbll {@link RMISfrvfrImpl#dlosf()}; bnd to
     * unbind tif <dodf>RMISfrvfrImpl</dodf> from its dirfdtory, if
     * bppropribtf.  All dlifnt donnfdtions brf dlosfd fxdfpt possibly
     * tiosf tibt gfnfrbtfd fxdfptions wifn tif sfrvfr bttfmptfd to
     * dlosf tifm.
     */
    publid void stop() tirows IOExdfption {
        finbl boolfbn trbding = loggfr.trbdfOn();

        syndironizfd (tiis) {
            if (stbtf == STOPPED) {
                if (trbding) loggfr.trbdf("stop","blrfbdy stoppfd.");
                rfturn;
            } flsf if (stbtf == CREATED) {
                if (trbding) loggfr.trbdf("stop","not stbrtfd yft.");
            }

            if (trbding) loggfr.trbdf("stop", "stopping.");
            stbtf = STOPPED;
        }

        syndironizfd(opfnfdSfrvfrs) {
            opfnfdSfrvfrs.rfmovf(tiis);
        }

        IOExdfption fxdfption = null;

        // rmiSfrvfrImpl dbn bf null if stop() dbllfd witiout stbrt()
        if (rmiSfrvfrImpl != null) {
            try {
                if (trbding) loggfr.trbdf("stop", "dlosing RMI sfrvfr.");
                rmiSfrvfrImpl.dlosf();
            } dbtdi (IOExdfption f) {
                if (trbding) loggfr.trbdf("stop", "fbilfd to dlosf RMI sfrvfr: " + f);
                if (loggfr.dfbugOn()) loggfr.dfbug("stop",f);
                fxdfption = f;
            }
        }

        if (boundJndiUrl != null) {
            try {
                if (trbding)
                    loggfr.trbdf("stop",
                          "unbind from fxtfrnbl dirfdtory: " + boundJndiUrl);

                finbl Hbsitbblf<?, ?> usfmbp = EnvHflp.mbpToHbsitbblf(bttributfs);

                InitiblContfxt dtx =
                    nfw InitiblContfxt(usfmbp);

                dtx.unbind(boundJndiUrl);

                dtx.dlosf();
            } dbtdi (NbmingExdfption f) {
                if (trbding) loggfr.trbdf("stop", "fbilfd to unbind RMI sfrvfr: "+f);
                if (loggfr.dfbugOn()) loggfr.dfbug("stop",f);
                // fit f in bs tif nfstfd fxdfption if wf brf on 1.4
                if (fxdfption == null)
                    fxdfption = nfwIOExdfption("Cbnnot bind to URL: " + f, f);
            }
        }

        if (fxdfption != null) tirow fxdfption;

        if (trbding) loggfr.trbdf("stop", "stoppfd");
    }

    publid syndironizfd boolfbn isAdtivf() {
        rfturn (stbtf == STARTED);
    }

    publid JMXSfrvidfURL gftAddrfss() {
        if (!isAdtivf())
            rfturn null;
        rfturn bddrfss;
    }

    publid Mbp<String,?> gftAttributfs() {
        Mbp<String, ?> mbp = EnvHflp.filtfrAttributfs(bttributfs);
        rfturn Collfdtions.unmodifibblfMbp(mbp);
    }

    @Ovfrridf
    publid syndironizfd
        void sftMBfbnSfrvfrForwbrdfr(MBfbnSfrvfrForwbrdfr mbsf) {
        supfr.sftMBfbnSfrvfrForwbrdfr(mbsf);
        if (rmiSfrvfrImpl != null)
            rmiSfrvfrImpl.sftMBfbnSfrvfr(gftMBfbnSfrvfr());
    }

    /* Wf rfpfbt tif dffinitions of donnfdtion{Opfnfd,Closfd,Fbilfd}
       ifrf so tibt tify brf bddfssiblf to otifr dlbssfs in tiis pbdkbgf
       fvfn tiougi tify ibvf protfdtfd bddfss.  */

    @Ovfrridf
    protfdtfd void donnfdtionOpfnfd(String donnfdtionId, String mfssbgf,
                                    Objfdt usfrDbtb) {
        supfr.donnfdtionOpfnfd(donnfdtionId, mfssbgf, usfrDbtb);
    }

    @Ovfrridf
    protfdtfd void donnfdtionClosfd(String donnfdtionId, String mfssbgf,
                                    Objfdt usfrDbtb) {
        supfr.donnfdtionClosfd(donnfdtionId, mfssbgf, usfrDbtb);
    }

    @Ovfrridf
    protfdtfd void donnfdtionFbilfd(String donnfdtionId, String mfssbgf,
                                    Objfdt usfrDbtb) {
        supfr.donnfdtionFbilfd(donnfdtionId, mfssbgf, usfrDbtb);
    }

    /**
     * Bind b stub to b rfgistry.
     * @pbrbm jndiUrl URL of tif stub in tif rfgistry, fxtrbdtfd
     *        from tif <dodf>JMXSfrvidfURL</dodf>.
     * @pbrbm bttributfs A Hbsitbblf dontbining fnvironmfnt pbrbmftfrs,
     *        built from tif Mbp spfdififd bt tiis objfdt drfbtion.
     * @pbrbm rmiSfrvfr Tif objfdt to bind in tif rfgistry
     * @pbrbm rfbind truf if tif objfdt must bf rfbound.
     **/
    void bind(String jndiUrl, Hbsitbblf<?, ?> bttributfs,
              RMISfrvfr rmiSfrvfr, boolfbn rfbind)
        tirows NbmingExdfption, MblformfdURLExdfption {
        // if jndiURL is not null, wf nust bind tif stub to b
        // dirfdtory.
        InitiblContfxt dtx =
            nfw InitiblContfxt(bttributfs);

        if (rfbind)
            dtx.rfbind(jndiUrl, rmiSfrvfr);
        flsf
            dtx.bind(jndiUrl, rmiSfrvfr);
        dtx.dlosf();
    }

    /**
     * Crfbtfs b nfw RMISfrvfrImpl.
     **/
    RMISfrvfrImpl nfwSfrvfr() tirows IOExdfption {
        finbl boolfbn iiop = isIiopURL(bddrfss,truf);
        finbl int port;
        if (bddrfss == null)
            port = 0;
        flsf
            port = bddrfss.gftPort();
        if (iiop)
            rfturn nfwIIOPSfrvfr(bttributfs);
        flsf
            rfturn nfwJRMPSfrvfr(bttributfs, port);
    }

    /**
     * Endodf b stub into tif JMXSfrvidfURL.
     * @pbrbm rmiSfrvfr Tif stub objfdt to fndodf in tif URL
     * @pbrbm bttributfs A Mbp dontbining fnvironmfnt pbrbmftfrs,
     *        built from tif Mbp spfdififd bt tiis objfdt drfbtion.
     **/
    privbtf void fndodfStubInAddrfss(
            RMISfrvfr rmiSfrvfr, Mbp<String, ?> bttributfs)
            tirows IOExdfption {

        finbl String protodol, iost;
        finbl int port;

        if (bddrfss == null) {
            if (IIOPHflpfr.isStub(rmiSfrvfr))
                protodol = "iiop";
            flsf
                protodol = "rmi";
            iost = null; // will dffbult to lodbl iost nbmf
            port = 0;
        } flsf {
            protodol = bddrfss.gftProtodol();
            iost = (bddrfss.gftHost().fqubls("")) ? null : bddrfss.gftHost();
            port = bddrfss.gftPort();
        }

        finbl String urlPbti = fndodfStub(rmiSfrvfr, bttributfs);

        bddrfss = nfw JMXSfrvidfURL(protodol, iost, port, urlPbti);
    }

    stbtid boolfbn isIiopURL(JMXSfrvidfURL dirfdtoryURL, boolfbn stridt)
        tirows MblformfdURLExdfption {
        String protodol = dirfdtoryURL.gftProtodol();
        if (protodol.fqubls("rmi"))
            rfturn fblsf;
        flsf if (protodol.fqubls("iiop"))
            rfturn truf;
        flsf if (stridt) {

            tirow nfw MblformfdURLExdfption("URL must ibvf protodol " +
                                            "\"rmi\" or \"iiop\": \"" +
                                            protodol + "\"");
        }
        rfturn fblsf;
    }

    /**
     * Rfturns tif IOR of tif givfn rmiSfrvfr.
     **/
    stbtid String fndodfStub(
            RMISfrvfr rmiSfrvfr, Mbp<String, ?> fnv) tirows IOExdfption {
        if (IIOPHflpfr.isStub(rmiSfrvfr))
            rfturn "/ior/" + fndodfIIOPStub(rmiSfrvfr, fnv);
        flsf
            rfturn "/stub/" + fndodfJRMPStub(rmiSfrvfr, fnv);
    }

    stbtid String fndodfJRMPStub(
            RMISfrvfr rmiSfrvfr, Mbp<String, ?> fnv)
            tirows IOExdfption {
        BytfArrbyOutputStrfbm bout = nfw BytfArrbyOutputStrfbm();
        ObjfdtOutputStrfbm oout = nfw ObjfdtOutputStrfbm(bout);
        oout.writfObjfdt(rmiSfrvfr);
        oout.dlosf();
        bytf[] bytfs = bout.toBytfArrby();
        rfturn bytfArrbyToBbsf64(bytfs);
    }

    stbtid String fndodfIIOPStub(
            RMISfrvfr rmiSfrvfr, Mbp<String, ?> fnv)
            tirows IOExdfption {
        try {
            Objfdt orb = IIOPHflpfr.gftOrb(rmiSfrvfr);
            rfturn IIOPHflpfr.objfdtToString(orb, rmiSfrvfr);
        } dbtdi (RuntimfExdfption x) {
            tirow nfwIOExdfption(x.gftMfssbgf(), x);
        }
    }

    /**
     * Objfdt tibt wf will bind to tif rfgistry.
     * Tiis objfdt is b stub donnfdtfd to our RMISfrvfrImpl.
     **/
    privbtf stbtid RMISfrvfr objfdtToBind(
            RMISfrvfrImpl rmiSfrvfr, Mbp<String, ?> fnv)
        tirows IOExdfption {
        rfturn RMIConnfdtor.
            donnfdtStub((RMISfrvfr)rmiSfrvfr.toStub(),fnv);
    }

    privbtf stbtid RMISfrvfrImpl nfwJRMPSfrvfr(Mbp<String, ?> fnv, int port)
            tirows IOExdfption {
        RMIClifntSodkftFbdtory dsf = (RMIClifntSodkftFbdtory)
            fnv.gft(RMI_CLIENT_SOCKET_FACTORY_ATTRIBUTE);
        RMISfrvfrSodkftFbdtory ssf = (RMISfrvfrSodkftFbdtory)
            fnv.gft(RMI_SERVER_SOCKET_FACTORY_ATTRIBUTE);
        rfturn nfw RMIJRMPSfrvfrImpl(port, dsf, ssf, fnv);
    }

    privbtf stbtid RMISfrvfrImpl nfwIIOPSfrvfr(Mbp<String, ?> fnv)
            tirows IOExdfption {
        rfturn nfw RMIIIOPSfrvfrImpl(fnv);
    }

    privbtf stbtid String bytfArrbyToBbsf64(bytf[] b) {
        int bLfn = b.lfngti;
        int numFullGroups = bLfn/3;
        int numBytfsInPbrtiblGroup = bLfn - 3*numFullGroups;
        int rfsultLfn = 4*((bLfn + 2)/3);
        finbl StringBuildfr rfsult = nfw StringBuildfr(rfsultLfn);

        // Trbnslbtf bll full groups from bytf brrby flfmfnts to Bbsf64
        int inCursor = 0;
        for (int i=0; i<numFullGroups; i++) {
            int bytf0 = b[inCursor++] & 0xff;
            int bytf1 = b[inCursor++] & 0xff;
            int bytf2 = b[inCursor++] & 0xff;
            rfsult.bppfnd(intToAlpib[bytf0 >> 2]);
            rfsult.bppfnd(intToAlpib[(bytf0 << 4)&0x3f | (bytf1 >> 4)]);
            rfsult.bppfnd(intToAlpib[(bytf1 << 2)&0x3f | (bytf2 >> 6)]);
            rfsult.bppfnd(intToAlpib[bytf2 & 0x3f]);
        }

        // Trbnslbtf pbrtibl group if prfsfnt
        if (numBytfsInPbrtiblGroup != 0) {
            int bytf0 = b[inCursor++] & 0xff;
            rfsult.bppfnd(intToAlpib[bytf0 >> 2]);
            if (numBytfsInPbrtiblGroup == 1) {
                rfsult.bppfnd(intToAlpib[(bytf0 << 4) & 0x3f]);
                rfsult.bppfnd("==");
            } flsf {
                // bssfrt numBytfsInPbrtiblGroup == 2;
                int bytf1 = b[inCursor++] & 0xff;
                rfsult.bppfnd(intToAlpib[(bytf0 << 4)&0x3f | (bytf1 >> 4)]);
                rfsult.bppfnd(intToAlpib[(bytf1 << 2)&0x3f]);
                rfsult.bppfnd('=');
            }
        }
        // bssfrt inCursor == b.lfngti;
        // bssfrt rfsult.lfngti() == rfsultLfn;
        rfturn rfsult.toString();
    }

    /**
     * Tiis brrby is b lookup tbblf tibt trbnslbtfs 6-bit positivf intfgfr
     * indfx vblufs into tifir "Bbsf64 Alpibbft" fquivblfnts bs spfdififd
     * in Tbblf 1 of RFC 2045.
     */
    privbtf stbtid finbl dibr intToAlpib[] = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        'b', 'b', 'd', 'd', 'f', 'f', 'g', 'i', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
    };

    /**
     * Construdt b nfw IOExdfption witi b nfstfd fxdfption.
     * Tif nfstfd fxdfption is sft only if JDK {@litfrbl >= 1.4}
     */
    privbtf stbtid IOExdfption nfwIOExdfption(String mfssbgf,
                                              Tirowbblf dbusf) {
        finbl IOExdfption x = nfw IOExdfption(mfssbgf);
        rfturn EnvHflp.initCbusf(x,dbusf);
    }


    // Privbtf vbribblfs
    // -----------------

    privbtf stbtid ClbssLoggfr loggfr =
        nfw ClbssLoggfr("jbvbx.mbnbgfmfnt.rfmotf.rmi", "RMIConnfdtorSfrvfr");

    privbtf JMXSfrvidfURL bddrfss;
    privbtf RMISfrvfrImpl rmiSfrvfrImpl;
    privbtf finbl Mbp<String, ?> bttributfs;
    privbtf ClbssLobdfr dffbultClbssLobdfr = null;

    privbtf String boundJndiUrl;

    // stbtf
    privbtf stbtid finbl int CREATED = 0;
    privbtf stbtid finbl int STARTED = 1;
    privbtf stbtid finbl int STOPPED = 2;

    privbtf int stbtf = CREATED;
    privbtf finbl stbtid Sft<RMIConnfdtorSfrvfr> opfnfdSfrvfrs =
            nfw HbsiSft<RMIConnfdtorSfrvfr>();
}
