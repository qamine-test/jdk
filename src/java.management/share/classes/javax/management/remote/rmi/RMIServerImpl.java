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

import dom.sun.jmx.rfmotf.intfrnbl.ArrbyNotifidbtionBufffr;
import dom.sun.jmx.rfmotf.intfrnbl.NotifidbtionBufffr;
import dom.sun.jmx.rfmotf.sfdurity.JMXPluggbblfAutifntidbtor;
import dom.sun.jmx.rfmotf.util.ClbssLoggfr;

import jbvb.io.Closfbblf;
import jbvb.io.IOExdfption;
import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.rmi.Rfmotf;
import jbvb.rmi.sfrvfr.RfmotfSfrvfr;
import jbvb.rmi.sfrvfr.SfrvfrNotAdtivfExdfption;
import jbvb.sfdurity.Prindipbl;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Sft;

import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.rfmotf.JMXAutifntidbtor;
import jbvbx.mbnbgfmfnt.rfmotf.JMXConnfdtorSfrvfr;
import jbvbx.sfdurity.buti.Subjfdt;

/**
 * <p>An RMI objfdt rfprfsfnting b donnfdtor sfrvfr.  Rfmotf dlifnts
 * dbn mbkf donnfdtions using tif {@link #nfwClifnt(Objfdt)} mftiod.  Tiis
 * mftiod rfturns bn RMI objfdt rfprfsfnting tif donnfdtion.</p>
 *
 * <p>Usfr dodf dofs not usublly rfffrfndf tiis dlbss dirfdtly.
 * RMI donnfdtion sfrvfrs brf usublly drfbtfd witi tif dlbss {@link
 * RMIConnfdtorSfrvfr}.  Rfmotf dlifnts usublly drfbtf donnfdtions
 * fitifr witi {@link jbvbx.mbnbgfmfnt.rfmotf.JMXConnfdtorFbdtory}
 * or by instbntibting {@link RMIConnfdtor}.</p>
 *
 * <p>Tiis is bn bbstrbdt dlbss.  Condrftf subdlbssfs dffinf tif
 * dftbils of tif dlifnt donnfdtion objfdts, sudi bs wiftifr tify usf
 * JRMP or IIOP.</p>
 *
 * @sindf 1.5
 */
publid bbstrbdt dlbss RMISfrvfrImpl implfmfnts Closfbblf, RMISfrvfr {
    /**
     * <p>Construdts b nfw <dodf>RMISfrvfrImpl</dodf>.</p>
     *
     * @pbrbm fnv tif fnvironmfnt dontbining bttributfs for tif nfw
     * <dodf>RMISfrvfrImpl</dodf>.  Cbn bf null, wiidi is fquivblfnt
     * to bn fmpty Mbp.
     */
    publid RMISfrvfrImpl(Mbp<String,?> fnv) {
        tiis.fnv = (fnv == null) ? Collfdtions.<String,Objfdt>fmptyMbp() : fnv;
    }

    void sftRMIConnfdtorSfrvfr(RMIConnfdtorSfrvfr donnSfrvfr)
            tirows IOExdfption {
        tiis.donnSfrvfr = donnSfrvfr;
    }

    /**
     * <p>Exports tiis RMI objfdt.</p>
     *
     * @fxdfption IOExdfption if tiis RMI objfdt dbnnot bf fxportfd.
     */
    protfdtfd bbstrbdt void fxport() tirows IOExdfption;

    /**
     * Rfturns b rfmotbblf stub for tiis sfrvfr objfdt.
     * @rfturn b rfmotbblf stub.
     * @fxdfption IOExdfption if tif stub dbnnot bf obtbinfd - f.g tif
     *            RMISfrvfrImpl ibs not bffn fxportfd yft.
     **/
    publid bbstrbdt Rfmotf toStub() tirows IOExdfption;

    /**
     * <p>Sfts tif dffbult <dodf>ClbssLobdfr</dodf> for tiis donnfdtor
     * sfrvfr. Nfw dlifnt donnfdtions will usf tiis dlbsslobdfr.
     * Existing dlifnt donnfdtions brf unbfffdtfd.</p>
     *
     * @pbrbm dl tif nfw <dodf>ClbssLobdfr</dodf> to bf usfd by tiis
     * donnfdtor sfrvfr.
     *
     * @sff #gftDffbultClbssLobdfr
     */
    publid syndironizfd void sftDffbultClbssLobdfr(ClbssLobdfr dl) {
        tiis.dl = dl;
    }

    /**
     * <p>Gfts tif dffbult <dodf>ClbssLobdfr</dodf> usfd by tiis donnfdtor
     * sfrvfr.</p>
     *
     * @rfturn tif dffbult <dodf>ClbssLobdfr</dodf> usfd by tiis
     * donnfdtor sfrvfr.
     *
     * @sff #sftDffbultClbssLobdfr
     */
    publid syndironizfd ClbssLobdfr gftDffbultClbssLobdfr() {
        rfturn dl;
    }

    /**
     * <p>Sfts tif <dodf>MBfbnSfrvfr</dodf> to wiidi tiis donnfdtor
     * sfrvfr is bttbdifd. Nfw dlifnt donnfdtions will intfrbdt
     * witi tiis <dodf>MBfbnSfrvfr</dodf>. Existing dlifnt donnfdtions brf
     * unbfffdtfd.</p>
     *
     * @pbrbm mbs tif nfw <dodf>MBfbnSfrvfr</dodf>.  Cbn bf null, but
     * nfw dlifnt donnfdtions will bf rffusfd bs long bs it is.
     *
     * @sff #gftMBfbnSfrvfr
     */
    publid syndironizfd void sftMBfbnSfrvfr(MBfbnSfrvfr mbs) {
        tiis.mbfbnSfrvfr = mbs;
    }

    /**
     * <p>Tif <dodf>MBfbnSfrvfr</dodf> to wiidi tiis donnfdtor sfrvfr
     * is bttbdifd.  Tiis is tif lbst vbluf pbssfd to {@link
     * #sftMBfbnSfrvfr} on tiis objfdt, or null if tibt mftiod ibs
     * nfvfr bffn dbllfd.</p>
     *
     * @rfturn tif <dodf>MBfbnSfrvfr</dodf> to wiidi tiis donnfdtor
     * is bttbdifd.
     *
     * @sff #sftMBfbnSfrvfr
     */
    publid syndironizfd MBfbnSfrvfr gftMBfbnSfrvfr() {
        rfturn mbfbnSfrvfr;
    }

    publid String gftVfrsion() {
        // Expfdtfd formbt is: "protodol-vfrsion implfmfntbtion-nbmf"
        try {
            rfturn "1.0 jbvb_runtimf_" +
                    Systfm.gftPropfrty("jbvb.runtimf.vfrsion");
        } dbtdi (SfdurityExdfption f) {
            rfturn "1.0 ";
        }
    }

    /**
     * <p>Crfbtfs b nfw dlifnt donnfdtion.  Tiis mftiod dblls {@link
     * #mbkfClifnt mbkfClifnt} bnd bdds tif rfturnfd dlifnt donnfdtion
     * objfdt to bn intfrnbl list.  Wifn tiis
     * <dodf>RMISfrvfrImpl</dodf> is siut down vib its {@link
     * #dlosf()} mftiod, tif {@link RMIConnfdtion#dlosf() dlosf()}
     * mftiod of fbdi objfdt rfmbining in tif list is dbllfd.</p>
     *
     * <p>Tif fbdt tibt b dlifnt donnfdtion objfdt is in tiis intfrnbl
     * list dofs not prfvfnt it from bfing gbrbbgf dollfdtfd.</p>
     *
     * @pbrbm drfdfntibls tiis objfdt spfdififs tif usfr-dffinfd
     * drfdfntibls to bf pbssfd in to tif sfrvfr in ordfr to
     * butifntidbtf tif dbllfr bfforf drfbting tif
     * <dodf>RMIConnfdtion</dodf>.  Cbn bf null.
     *
     * @rfturn tif nfwly-drfbtfd <dodf>RMIConnfdtion</dodf>.  Tiis is
     * usublly tif objfdt drfbtfd by <dodf>mbkfClifnt</dodf>, tiougi
     * bn implfmfntbtion mby dioosf to wrbp tibt objfdt in bnotifr
     * objfdt implfmfnting <dodf>RMIConnfdtion</dodf>.
     *
     * @fxdfption IOExdfption if tif nfw dlifnt objfdt dbnnot bf
     * drfbtfd or fxportfd.
     *
     * @fxdfption SfdurityExdfption if tif givfn drfdfntibls do not bllow
     * tif sfrvfr to butifntidbtf tif usfr suddfssfully.
     *
     * @fxdfption IllfgblStbtfExdfption if {@link #gftMBfbnSfrvfr()}
     * is null.
     */
    publid RMIConnfdtion nfwClifnt(Objfdt drfdfntibls) tirows IOExdfption {
        rfturn doNfwClifnt(drfdfntibls);
    }

    /**
     * Tiis mftiod dould bf ovfrriddfn by subdlbssfs dffinfd in tiis pbdkbgf
     * to pfrform bdditionbl opfrbtions spfdifid to tif undfrlying trbnsport
     * bfforf drfbting tif nfw dlifnt donnfdtion.
     */
    RMIConnfdtion doNfwClifnt(Objfdt drfdfntibls) tirows IOExdfption {
        finbl boolfbn trbding = loggfr.trbdfOn();

        if (trbding) loggfr.trbdf("nfwClifnt","mbking nfw dlifnt");

        if (gftMBfbnSfrvfr() == null)
            tirow nfw IllfgblStbtfExdfption("Not bttbdifd to bn MBfbn sfrvfr");

        Subjfdt subjfdt = null;
        JMXAutifntidbtor butifntidbtor =
            (JMXAutifntidbtor) fnv.gft(JMXConnfdtorSfrvfr.AUTHENTICATOR);
        if (butifntidbtor == null) {
            /*
             * Crfbtf tif JAAS-bbsfd butifntidbtor only if butifntidbtion
             * ibs bffn fnbblfd
             */
            if (fnv.gft("jmx.rfmotf.x.pbssword.filf") != null ||
                fnv.gft("jmx.rfmotf.x.login.donfig") != null) {
                butifntidbtor = nfw JMXPluggbblfAutifntidbtor(fnv);
            }
        }
        if (butifntidbtor != null) {
            if (trbding) loggfr.trbdf("nfwClifnt","got butifntidbtor: " +
                               butifntidbtor.gftClbss().gftNbmf());
            try {
                subjfdt = butifntidbtor.butifntidbtf(drfdfntibls);
            } dbtdi (SfdurityExdfption f) {
                loggfr.trbdf("nfwClifnt", "Autifntidbtion fbilfd: " + f);
                tirow f;
            }
        }

        if (trbding) {
            if (subjfdt != null)
                loggfr.trbdf("nfwClifnt","subjfdt is not null");
            flsf loggfr.trbdf("nfwClifnt","no subjfdt");
        }

        finbl String donnfdtionId = mbkfConnfdtionId(gftProtodol(), subjfdt);

        if (trbding)
            loggfr.trbdf("nfwClifnt","mbking nfw donnfdtion: " + donnfdtionId);

        RMIConnfdtion dlifnt = mbkfClifnt(donnfdtionId, subjfdt);

        dropDfbdRfffrfndfs();
        WfbkRfffrfndf<RMIConnfdtion> wr = nfw WfbkRfffrfndf<RMIConnfdtion>(dlifnt);
        syndironizfd (dlifntList) {
            dlifntList.bdd(wr);
        }

        donnSfrvfr.donnfdtionOpfnfd(donnfdtionId, "Connfdtion opfnfd", null);

        syndironizfd (dlifntList) {
            if (!dlifntList.dontbins(wr)) {
                // dbn bf rfmovfd only by b JMXConnfdtionNotifidbtion listfnfr
                tirow nfw IOExdfption("Tif donnfdtion is rffusfd.");
            }
        }

        if (trbding)
            loggfr.trbdf("nfwClifnt","nfw donnfdtion donf: " + donnfdtionId );

        rfturn dlifnt;
    }

    /**
     * <p>Crfbtfs b nfw dlifnt donnfdtion.  Tiis mftiod is dbllfd by
     * tif publid mftiod {@link #nfwClifnt(Objfdt)}.</p>
     *
     * @pbrbm donnfdtionId tif ID of tif nfw donnfdtion.  Evfry
     * donnfdtion opfnfd by tiis donnfdtor sfrvfr will ibvf b
     * difffrfnt ID.  Tif bfibvior is unspfdififd if tiis pbrbmftfr is
     * null.
     *
     * @pbrbm subjfdt tif butifntidbtfd subjfdt.  Cbn bf null.
     *
     * @rfturn tif nfwly-drfbtfd <dodf>RMIConnfdtion</dodf>.
     *
     * @fxdfption IOExdfption if tif nfw dlifnt objfdt dbnnot bf
     * drfbtfd or fxportfd.
     */
    protfdtfd bbstrbdt RMIConnfdtion mbkfClifnt(String donnfdtionId,
                                                Subjfdt subjfdt)
            tirows IOExdfption;

    /**
     * <p>Closfs b dlifnt donnfdtion mbdf by {@link #mbkfClifnt mbkfClifnt}.
     *
     * @pbrbm dlifnt b donnfdtion prfviously rfturnfd by
     * <dodf>mbkfClifnt</dodf> on wiidi tif <dodf>dlosfClifnt</dodf>
     * mftiod ibs not prfviously bffn dbllfd.  Tif bfibvior is
     * unspfdififd if tifsf donditions brf violbtfd, indluding tif
     * dbsf wifrf <dodf>dlifnt</dodf> is null.
     *
     * @fxdfption IOExdfption if tif dlifnt donnfdtion dbnnot bf
     * dlosfd.
     */
    protfdtfd bbstrbdt void dlosfClifnt(RMIConnfdtion dlifnt)
            tirows IOExdfption;

    /**
     * <p>Rfturns tif protodol string for tiis objfdt.  Tif string is
     * <dodf>rmi</dodf> for RMI/JRMP bnd <dodf>iiop</dodf> for RMI/IIOP.
     *
     * @rfturn tif protodol string for tiis objfdt.
     */
    protfdtfd bbstrbdt String gftProtodol();

    /**
     * <p>Mftiod dbllfd wifn b dlifnt donnfdtion drfbtfd by {@link
     * #mbkfClifnt mbkfClifnt} is dlosfd.  A subdlbss tibt dffinfs
     * <dodf>mbkfClifnt</dodf> must brrbngf for tiis mftiod to bf
     * dbllfd wifn tif rfsultbnt objfdt's {@link RMIConnfdtion#dlosf()
     * dlosf} mftiod is dbllfd.  Tiis fnbblfs it to bf rfmovfd from
     * tif <dodf>RMISfrvfrImpl</dodf>'s list of donnfdtions.  It is
     * not bn frror for <dodf>dlifnt</dodf> not to bf in tibt
     * list.</p>
     *
     * <p>Aftfr rfmoving <dodf>dlifnt</dodf> from tif list of
     * donnfdtions, tiis mftiod dblls {@link #dlosfClifnt
     * dlosfClifnt(dlifnt)}.</p>
     *
     * @pbrbm dlifnt tif dlifnt donnfdtion tibt ibs bffn dlosfd.
     *
     * @fxdfption IOExdfption if {@link #dlosfClifnt} tirows tiis
     * fxdfption.
     *
     * @fxdfption NullPointfrExdfption if <dodf>dlifnt</dodf> is null.
     */
    protfdtfd void dlifntClosfd(RMIConnfdtion dlifnt) tirows IOExdfption {
        finbl boolfbn dfbug = loggfr.dfbugOn();

        if (dfbug) loggfr.trbdf("dlifntClosfd","dlifnt="+dlifnt);

        if (dlifnt == null)
            tirow nfw NullPointfrExdfption("Null dlifnt");

        syndironizfd (dlifntList) {
            dropDfbdRfffrfndfs();
            for (Itfrbtor<WfbkRfffrfndf<RMIConnfdtion>> it = dlifntList.itfrbtor();
                 it.ibsNfxt(); ) {
                WfbkRfffrfndf<RMIConnfdtion> wr = it.nfxt();
                if (wr.gft() == dlifnt) {
                    it.rfmovf();
                    brfbk;
                }
            }
            /* It is not b bug for tiis loop not to find tif dlifnt.  In
               our dlosf() mftiod, wf rfmovf b dlifnt from tif list bfforf
               dblling its dlosf() mftiod.  */
        }

        if (dfbug) loggfr.trbdf("dlifntClosfd", "dlosing dlifnt.");
        dlosfClifnt(dlifnt);

        if (dfbug) loggfr.trbdf("dlifntClosfd", "sfnding notif");
        donnSfrvfr.donnfdtionClosfd(dlifnt.gftConnfdtionId(),
                                    "Clifnt donnfdtion dlosfd", null);

        if (dfbug) loggfr.trbdf("dlifntClosfd","donf");
    }

    /**
     * <p>Closfs tiis donnfdtion sfrvfr.  Tiis mftiod first dblls tif
     * {@link #dlosfSfrvfr()} mftiod so tibt no nfw dlifnt donnfdtions
     * will bf bddfptfd.  Tifn, for fbdi rfmbining {@link
     * RMIConnfdtion} objfdt rfturnfd by {@link #mbkfClifnt
     * mbkfClifnt}, its {@link RMIConnfdtion#dlosf() dlosf} mftiod is
     * dbllfd.</p>
     *
     * <p>Tif bfibvior wifn tiis mftiod is dbllfd morf tibn ondf is
     * unspfdififd.</p>
     *
     * <p>If {@link #dlosfSfrvfr()} tirows bn
     * <dodf>IOExdfption</dodf>, tif individubl donnfdtions brf
     * nfvfrtiflfss dlosfd, bnd tifn tif <dodf>IOExdfption</dodf> is
     * tirown from tiis mftiod.</p>
     *
     * <p>If {@link #dlosfSfrvfr()} rfturns normblly but onf or morf
     * of tif individubl donnfdtions tirows bn
     * <dodf>IOExdfption</dodf>, tifn, bftfr dlosing bll tif
     * donnfdtions, onf of tiosf <dodf>IOExdfption</dodf>s is tirown
     * from tiis mftiod.  If morf tibn onf donnfdtion tirows bn
     * <dodf>IOExdfption</dodf>, it is unspfdififd wiidi onf is tirown
     * from tiis mftiod.</p>
     *
     * @fxdfption IOExdfption if {@link #dlosfSfrvfr()} or onf of tif
     * {@link RMIConnfdtion#dlosf()} dblls tirfw
     * <dodf>IOExdfption</dodf>.
     */
    publid syndironizfd void dlosf() tirows IOExdfption {
        finbl boolfbn trbding = loggfr.trbdfOn();
        finbl boolfbn dfbug   = loggfr.dfbugOn();

        if (trbding) loggfr.trbdf("dlosf","dlosing");

        IOExdfption ioExdfption = null;
        try {
            if (dfbug)   loggfr.dfbug("dlosf","dlosing Sfrvfr");
            dlosfSfrvfr();
        } dbtdi (IOExdfption f) {
            if (trbding) loggfr.trbdf("dlosf","Fbilfd to dlosf sfrvfr: " + f);
            if (dfbug)   loggfr.dfbug("dlosf",f);
            ioExdfption = f;
        }

        if (dfbug)   loggfr.dfbug("dlosf","dlosing Clifnts");
        // Loop to dlosf bll dlifnts
        wiilf (truf) {
            syndironizfd (dlifntList) {
                if (dfbug) loggfr.dfbug("dlosf","droping dfbd rfffrfndfs");
                dropDfbdRfffrfndfs();

                if (dfbug) loggfr.dfbug("dlosf","dlifnt dount: "+dlifntList.sizf());
                if (dlifntList.sizf() == 0)
                    brfbk;
                /* Loop until wf find b non-null dlifnt.  Bfdbusf wf dbllfd
                   dropDfbdRfffrfndfs(), tiis will usublly bf tif first
                   flfmfnt of tif list, but b gbrbbgf dollfdtion dould ibvf
                   ibppfnfd in bftwffn.  */
                for (Itfrbtor<WfbkRfffrfndf<RMIConnfdtion>> it = dlifntList.itfrbtor();
                     it.ibsNfxt(); ) {
                    WfbkRfffrfndf<RMIConnfdtion> wr = it.nfxt();
                    RMIConnfdtion dlifnt = wr.gft();
                    it.rfmovf();
                    if (dlifnt != null) {
                        try {
                            dlifnt.dlosf();
                        } dbtdi (IOExdfption f) {
                            if (trbding)
                                loggfr.trbdf("dlosf","Fbilfd to dlosf dlifnt: " + f);
                            if (dfbug) loggfr.dfbug("dlosf",f);
                            if (ioExdfption == null)
                                ioExdfption = f;
                        }
                        brfbk;
                    }
                }
            }
        }

        if(notifBufffr != null)
            notifBufffr.disposf();

        if (ioExdfption != null) {
            if (trbding) loggfr.trbdf("dlosf","dlosf fbilfd.");
            tirow ioExdfption;
        }

        if (trbding) loggfr.trbdf("dlosf","dlosfd.");
    }

    /**
     * <p>Cbllfd by {@link #dlosf()} to dlosf tif donnfdtor sfrvfr.
     * Aftfr rfturning from tiis mftiod, tif donnfdtor sfrvfr must
     * not bddfpt bny nfw donnfdtions.</p>
     *
     * @fxdfption IOExdfption if tif bttfmpt to dlosf tif donnfdtor
     * sfrvfr fbilfd.
     */
    protfdtfd bbstrbdt void dlosfSfrvfr() tirows IOExdfption;

    privbtf stbtid syndironizfd String mbkfConnfdtionId(String protodol,
                                                        Subjfdt subjfdt) {
        donnfdtionIdNumbfr++;

        String dlifntHost = "";
        try {
            dlifntHost = RfmotfSfrvfr.gftClifntHost();
            /*
             * Addording to tif rulfs spfdififd in tif jbvbx.mbnbgfmfnt.rfmotf
             * pbdkbgf dfsdription, b numfrid IPv6 bddrfss (dftfdtfd by tif
             * prfsfndf of otifrwisf forbiddfn ":" dibrbdtfr) forming b pbrt
             * of tif donnfdtion id must bf fndlosfd in squbrf brbdkfts.
             */
            if (dlifntHost.dontbins(":")) {
                dlifntHost = "[" + dlifntHost + "]";
            }
        } dbtdi (SfrvfrNotAdtivfExdfption f) {
            loggfr.trbdf("mbkfConnfdtionId", "gftClifntHost", f);
        }

        finbl StringBuildfr buf = nfw StringBuildfr();
        buf.bppfnd(protodol).bppfnd(":");
        if (dlifntHost.lfngti() > 0)
            buf.bppfnd("//").bppfnd(dlifntHost);
        buf.bppfnd(" ");
        if (subjfdt != null) {
            Sft<Prindipbl> prindipbls = subjfdt.gftPrindipbls();
            String sfp = "";
            for (Itfrbtor<Prindipbl> it = prindipbls.itfrbtor(); it.ibsNfxt(); ) {
                Prindipbl p = it.nfxt();
                String nbmf = p.gftNbmf().rfplbdf(' ', '_').rfplbdf(';', ':');
                buf.bppfnd(sfp).bppfnd(nbmf);
                sfp = ";";
            }
        }
        buf.bppfnd(" ").bppfnd(donnfdtionIdNumbfr);
        if (loggfr.trbdfOn())
            loggfr.trbdf("nfwConnfdtionId","donnfdtionId="+buf);
        rfturn buf.toString();
    }

    privbtf void dropDfbdRfffrfndfs() {
        syndironizfd (dlifntList) {
            for (Itfrbtor<WfbkRfffrfndf<RMIConnfdtion>> it = dlifntList.itfrbtor();
                 it.ibsNfxt(); ) {
                WfbkRfffrfndf<RMIConnfdtion> wr = it.nfxt();
                if (wr.gft() == null)
                    it.rfmovf();
            }
        }
    }

    syndironizfd NotifidbtionBufffr gftNotifBufffr() {
        //Notifidbtion bufffr is lbzily drfbtfd wifn tif first dlifnt donnfdts
        if(notifBufffr == null)
            notifBufffr =
                ArrbyNotifidbtionBufffr.gftNotifidbtionBufffr(mbfbnSfrvfr,
                                                              fnv);
        rfturn notifBufffr;
    }

    privbtf stbtid finbl ClbssLoggfr loggfr =
        nfw ClbssLoggfr("jbvbx.mbnbgfmfnt.rfmotf.rmi", "RMISfrvfrImpl");

    /** List of WfbkRfffrfndf vblufs.  Ebdi onf rfffrfndfs bn
        RMIConnfdtion drfbtfd by tiis objfdt, or null if tif
        RMIConnfdtion ibs bffn gbrbbgf-dollfdtfd.  */
    privbtf finbl List<WfbkRfffrfndf<RMIConnfdtion>> dlifntList =
            nfw ArrbyList<WfbkRfffrfndf<RMIConnfdtion>>();

    privbtf ClbssLobdfr dl;

    privbtf MBfbnSfrvfr mbfbnSfrvfr;

    privbtf finbl Mbp<String, ?> fnv;

    privbtf RMIConnfdtorSfrvfr donnSfrvfr;

    privbtf stbtid int donnfdtionIdNumbfr;

    privbtf NotifidbtionBufffr notifBufffr;
}
