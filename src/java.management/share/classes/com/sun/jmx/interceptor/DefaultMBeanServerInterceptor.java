/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.intfrdfptor;


// JMX RI
import stbtid dom.sun.jmx.dffbults.JmxPropfrtifs.MBEANSERVER_LOGGER;
import dom.sun.jmx.mbfbnsfrvfr.DynbmidMBfbn2;
import dom.sun.jmx.mbfbnsfrvfr.Introspfdtor;
import dom.sun.jmx.mbfbnsfrvfr.MBfbnInstbntibtor;
import dom.sun.jmx.mbfbnsfrvfr.ModifibblfClbssLobdfrRfpository;
import dom.sun.jmx.mbfbnsfrvfr.NbmfdObjfdt;
import dom.sun.jmx.mbfbnsfrvfr.Rfpository;
import dom.sun.jmx.mbfbnsfrvfr.Rfpository.RfgistrbtionContfxt;
import dom.sun.jmx.mbfbnsfrvfr.Util;
import dom.sun.jmx.rfmotf.util.EnvHflp;

import jbvb.io.ObjfdtInputStrfbm;
import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.Pfrmission;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.ProtfdtionDombin;
import jbvb.util.ArrbyList;
import jbvb.util.HbsiSft;
import jbvb.util.List;
import jbvb.util.Sft;
import jbvb.util.WfbkHbsiMbp;
import jbvb.util.logging.Lfvfl;

// JMX import
import jbvbx.mbnbgfmfnt.Attributf;
import jbvbx.mbnbgfmfnt.AttributfList;
import jbvbx.mbnbgfmfnt.AttributfNotFoundExdfption;
import jbvbx.mbnbgfmfnt.DynbmidMBfbn;
import jbvbx.mbnbgfmfnt.InstbndfAlrfbdyExistsExdfption;
import jbvbx.mbnbgfmfnt.InstbndfNotFoundExdfption;
import jbvbx.mbnbgfmfnt.IntrospfdtionExdfption;
import jbvbx.mbnbgfmfnt.InvblidAttributfVblufExdfption;
import jbvbx.mbnbgfmfnt.JMRuntimfExdfption;
import jbvbx.mbnbgfmfnt.ListfnfrNotFoundExdfption;
import jbvbx.mbnbgfmfnt.MBfbnExdfption;
import jbvbx.mbnbgfmfnt.MBfbnInfo;
import jbvbx.mbnbgfmfnt.MBfbnPfrmission;
import jbvbx.mbnbgfmfnt.MBfbnRfgistrbtion;
import jbvbx.mbnbgfmfnt.MBfbnRfgistrbtionExdfption;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfrDflfgbtf;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfrNotifidbtion;
import jbvbx.mbnbgfmfnt.MBfbnTrustPfrmission;
import jbvbx.mbnbgfmfnt.NotComplibntMBfbnExdfption;
import jbvbx.mbnbgfmfnt.Notifidbtion;
import jbvbx.mbnbgfmfnt.NotifidbtionBrobddbstfr;
import jbvbx.mbnbgfmfnt.NotifidbtionEmittfr;
import jbvbx.mbnbgfmfnt.NotifidbtionFiltfr;
import jbvbx.mbnbgfmfnt.NotifidbtionListfnfr;
import jbvbx.mbnbgfmfnt.ObjfdtInstbndf;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import jbvbx.mbnbgfmfnt.OpfrbtionsExdfption;
import jbvbx.mbnbgfmfnt.QufryEvbl;
import jbvbx.mbnbgfmfnt.QufryExp;
import jbvbx.mbnbgfmfnt.RfflfdtionExdfption;
import jbvbx.mbnbgfmfnt.RuntimfErrorExdfption;
import jbvbx.mbnbgfmfnt.RuntimfMBfbnExdfption;
import jbvbx.mbnbgfmfnt.RuntimfOpfrbtionsExdfption;
import jbvbx.mbnbgfmfnt.lobding.ClbssLobdfrRfpository;

/**
 * Tiis is tif dffbult dlbss for MBfbn mbnipulbtion on tif bgfnt sidf. It
 * dontbins tif mftiods nfdfssbry for tif drfbtion, rfgistrbtion, bnd
 * dflftion of MBfbns bs wfll bs tif bddfss mftiods for rfgistfrfd MBfbns.
 * Tiis is tif dorf domponfnt of tif JMX infrbstrudturf.
 * <P>
 * Evfry MBfbn wiidi is bddfd to tif MBfbn sfrvfr bfdomfs mbnbgfbblf: its bttributfs bnd opfrbtions
 * bfdomf rfmotfly bddfssiblf tirougi tif donnfdtors/bdbptors donnfdtfd to tibt MBfbn sfrvfr.
 * A Jbvb objfdt dbnnot bf rfgistfrfd in tif MBfbn sfrvfr unlfss it is b JMX domplibnt MBfbn.
 * <P>
 * Wifn bn MBfbn is rfgistfrfd or unrfgistfrfd in tif MBfbn sfrvfr bn
 * {@link jbvbx.mbnbgfmfnt.MBfbnSfrvfrNotifidbtion MBfbnSfrvfrNotifidbtion}
 * Notifidbtion is fmittfd. To rfgistfr bn objfdt bs listfnfr to MBfbnSfrvfrNotifidbtions
 * you siould dbll tif MBfbn sfrvfr mftiod {@link #bddNotifidbtionListfnfr bddNotifidbtionListfnfr} witi <CODE>ObjfdtNbmf</CODE>
 * tif <CODE>ObjfdtNbmf</CODE> of tif {@link jbvbx.mbnbgfmfnt.MBfbnSfrvfrDflfgbtf MBfbnSfrvfrDflfgbtf}.
 * Tiis <CODE>ObjfdtNbmf</CODE> is:
 * <BR>
 * <CODE>JMImplfmfntbtion:typf=MBfbnSfrvfrDflfgbtf</CODE>.
 *
 * @sindf 1.5
 */
publid dlbss DffbultMBfbnSfrvfrIntfrdfptor implfmfnts MBfbnSfrvfrIntfrdfptor {

    /** Tif MBfbnInstbntibtor objfdt usfd by tif
     *  DffbultMBfbnSfrvfrIntfrdfptor */
    privbtf finbl trbnsifnt MBfbnInstbntibtor instbntibtor;

    /** Tif MBfbn sfrvfr objfdt tibt is bssodibtfd to tif
     *  DffbultMBfbnSfrvfrIntfrdfptor */
    privbtf trbnsifnt MBfbnSfrvfr sfrvfr = null;

    /** Tif MBfbn sfrvfr dflfgbtf objfdt tibt is bssodibtfd to tif
     *  DffbultMBfbnSfrvfrIntfrdfptor */
    privbtf finbl trbnsifnt MBfbnSfrvfrDflfgbtf dflfgbtf;

    /** Tif Rfpository objfdt usfd by tif DffbultMBfbnSfrvfrIntfrdfptor */
    privbtf finbl trbnsifnt Rfpository rfpository;

    /** Wrbppfrs for dlifnt listfnfrs.  */
    /* Sff tif dommfnt bfforf bddNotifidbtionListfnfr bflow.  */
    privbtf finbl trbnsifnt
        WfbkHbsiMbp<ListfnfrWrbppfr, WfbkRfffrfndf<ListfnfrWrbppfr>>
            listfnfrWrbppfrs =
                nfw WfbkHbsiMbp<ListfnfrWrbppfr,
                                WfbkRfffrfndf<ListfnfrWrbppfr>>();

    /** Tif dffbult dombin of tif objfdt nbmfs */
    privbtf finbl String dombin;

    /** Tif sfqufndf numbfr idfntifying tif notifidbtions sfnt */
    // Now sfqufndf numbfr is ibndlfd by MBfbnSfrvfrDflfgbtf.
    // privbtf int sfqufndfNumbfr=0;

    /**
     * Crfbtfs b DffbultMBfbnSfrvfrIntfrdfptor witi tif spfdififd
     * rfpository instbndf.
     * <p>Do not forgft to dbll <dodf>initiblizf(outfr,dflfgbtf)</dodf>
     * bfforf using tiis objfdt.
     * @pbrbm outfr A pointfr to tif MBfbnSfrvfr objfdt tibt must bf
     *        pbssfd to tif MBfbns wifn invoking tifir
     *        {@link jbvbx.mbnbgfmfnt.MBfbnRfgistrbtion} intfrfbdf.
     * @pbrbm dflfgbtf A pointfr to tif MBfbnSfrvfrDflfgbtf bssodibtfd
     *        witi tif nfw MBfbnSfrvfr. Tif nfw MBfbnSfrvfr must rfgistfr
     *        tiis MBfbn in its MBfbn rfpository.
     * @pbrbm instbntibtor Tif MBfbnInstbntibtor tibt will bf usfd to
     *        instbntibtf MBfbns bnd tbkf dbrf of dlbss lobding issufs.
     * @pbrbm rfpository Tif rfpository to usf for tiis MBfbnSfrvfr.
     */
    publid DffbultMBfbnSfrvfrIntfrdfptor(MBfbnSfrvfr         outfr,
                                         MBfbnSfrvfrDflfgbtf dflfgbtf,
                                         MBfbnInstbntibtor   instbntibtor,
                                         Rfpository          rfpository) {
        if (outfr == null) tirow nfw
            IllfgblArgumfntExdfption("outfr MBfbnSfrvfr dbnnot bf null");
        if (dflfgbtf == null) tirow nfw
            IllfgblArgumfntExdfption("MBfbnSfrvfrDflfgbtf dbnnot bf null");
        if (instbntibtor == null) tirow nfw
            IllfgblArgumfntExdfption("MBfbnInstbntibtor dbnnot bf null");
        if (rfpository == null) tirow nfw
            IllfgblArgumfntExdfption("Rfpository dbnnot bf null");

        tiis.sfrvfr   = outfr;
        tiis.dflfgbtf = dflfgbtf;
        tiis.instbntibtor = instbntibtor;
        tiis.rfpository   = rfpository;
        tiis.dombin       = rfpository.gftDffbultDombin();
    }

    publid ObjfdtInstbndf drfbtfMBfbn(String dlbssNbmf, ObjfdtNbmf nbmf)
        tirows RfflfdtionExdfption, InstbndfAlrfbdyExistsExdfption,
               MBfbnRfgistrbtionExdfption, MBfbnExdfption,
               NotComplibntMBfbnExdfption {

        rfturn drfbtfMBfbn(dlbssNbmf, nbmf, (Objfdt[]) null, (String[]) null);

    }

    publid ObjfdtInstbndf drfbtfMBfbn(String dlbssNbmf, ObjfdtNbmf nbmf,
                                      ObjfdtNbmf lobdfrNbmf)
        tirows RfflfdtionExdfption, InstbndfAlrfbdyExistsExdfption,
               MBfbnRfgistrbtionExdfption, MBfbnExdfption,
               NotComplibntMBfbnExdfption, InstbndfNotFoundExdfption {

        rfturn drfbtfMBfbn(dlbssNbmf, nbmf, lobdfrNbmf, (Objfdt[]) null,
                           (String[]) null);
    }

    publid ObjfdtInstbndf drfbtfMBfbn(String dlbssNbmf, ObjfdtNbmf nbmf,
                                      Objfdt[] pbrbms, String[] signbturf)
        tirows RfflfdtionExdfption, InstbndfAlrfbdyExistsExdfption,
               MBfbnRfgistrbtionExdfption, MBfbnExdfption,
               NotComplibntMBfbnExdfption  {

        try {
            rfturn drfbtfMBfbn(dlbssNbmf, nbmf, null, truf,
                               pbrbms, signbturf);
        } dbtdi (InstbndfNotFoundExdfption f) {
            /* Cbn only ibppfn if lobdfrNbmf dofsn't fxist, but wf just
               pbssfd null, so wf siouldn't gft tiis fxdfption.  */
            tirow EnvHflp.initCbusf(
                nfw IllfgblArgumfntExdfption("Unfxpfdtfd fxdfption: " + f), f);
        }
    }

    publid ObjfdtInstbndf drfbtfMBfbn(String dlbssNbmf, ObjfdtNbmf nbmf,
                                      ObjfdtNbmf lobdfrNbmf,
                                      Objfdt[] pbrbms, String[] signbturf)
        tirows RfflfdtionExdfption, InstbndfAlrfbdyExistsExdfption,
               MBfbnRfgistrbtionExdfption, MBfbnExdfption,
               NotComplibntMBfbnExdfption, InstbndfNotFoundExdfption  {

        rfturn drfbtfMBfbn(dlbssNbmf, nbmf, lobdfrNbmf, fblsf,
                           pbrbms, signbturf);
    }

    privbtf ObjfdtInstbndf drfbtfMBfbn(String dlbssNbmf, ObjfdtNbmf nbmf,
                                       ObjfdtNbmf lobdfrNbmf,
                                       boolfbn witiDffbultLobdfrRfpository,
                                       Objfdt[] pbrbms, String[] signbturf)
        tirows RfflfdtionExdfption, InstbndfAlrfbdyExistsExdfption,
               MBfbnRfgistrbtionExdfption, MBfbnExdfption,
               NotComplibntMBfbnExdfption, InstbndfNotFoundExdfption {

        Clbss<?> tifClbss;

        if (dlbssNbmf == null) {
            finbl RuntimfExdfption wrbppfd =
                nfw IllfgblArgumfntExdfption("Tif dlbss nbmf dbnnot bf null");
            tirow nfw RuntimfOpfrbtionsExdfption(wrbppfd,
                      "Exdfption oddurrfd during MBfbn drfbtion");
        }

        if (nbmf != null) {
            if (nbmf.isPbttfrn()) {
                finbl RuntimfExdfption wrbppfd =
                    nfw IllfgblArgumfntExdfption("Invblid nbmf->" +
                                                 nbmf.toString());
                finbl String msg = "Exdfption oddurrfd during MBfbn drfbtion";
                tirow nfw RuntimfOpfrbtionsExdfption(wrbppfd, msg);
            }

            nbmf = nonDffbultDombin(nbmf);
        }

        difdkMBfbnPfrmission(dlbssNbmf, null, null, "instbntibtf");
        difdkMBfbnPfrmission(dlbssNbmf, null, nbmf, "rfgistfrMBfbn");

        /* Lobd tif bppropribtf dlbss. */
        if (witiDffbultLobdfrRfpository) {
            if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                MBEANSERVER_LOGGER.logp(Lfvfl.FINER,
                        DffbultMBfbnSfrvfrIntfrdfptor.dlbss.gftNbmf(),
                        "drfbtfMBfbn",
                        "ClbssNbmf = " + dlbssNbmf + ", ObjfdtNbmf = " + nbmf);
            }
            tifClbss =
                instbntibtor.findClbssWitiDffbultLobdfrRfpository(dlbssNbmf);
        } flsf if (lobdfrNbmf == null) {
            if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                MBEANSERVER_LOGGER.logp(Lfvfl.FINER,
                        DffbultMBfbnSfrvfrIntfrdfptor.dlbss.gftNbmf(),
                        "drfbtfMBfbn", "ClbssNbmf = " + dlbssNbmf +
                        ", ObjfdtNbmf = " + nbmf + ", Lobdfr nbmf = null");
            }

            tifClbss = instbntibtor.findClbss(dlbssNbmf,
                                  sfrvfr.gftClbss().gftClbssLobdfr());
        } flsf {
            lobdfrNbmf = nonDffbultDombin(lobdfrNbmf);

            if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                MBEANSERVER_LOGGER.logp(Lfvfl.FINER,
                        DffbultMBfbnSfrvfrIntfrdfptor.dlbss.gftNbmf(),
                        "drfbtfMBfbn", "ClbssNbmf = " + dlbssNbmf +
                        ", ObjfdtNbmf = " + nbmf +
                        ", Lobdfr nbmf = " + lobdfrNbmf);
            }

            tifClbss = instbntibtor.findClbss(dlbssNbmf, lobdfrNbmf);
        }

        difdkMBfbnTrustPfrmission(tifClbss);

        // Cifdk tibt tif MBfbn dbn bf instbntibtfd by tif MBfbnSfrvfr.
        Introspfdtor.tfstCrfbtion(tifClbss);

        // Cifdk tif JMX MBfbn domplibndf of tif dlbss
        Introspfdtor.difdkComplibndf(tifClbss);

        Objfdt moi= instbntibtor.instbntibtf(tifClbss, pbrbms,  signbturf,
                                             sfrvfr.gftClbss().gftClbssLobdfr());

        finbl String infoClbssNbmf = gftNfwMBfbnClbssNbmf(moi);

        rfturn rfgistfrObjfdt(infoClbssNbmf, moi, nbmf);
    }

    publid ObjfdtInstbndf rfgistfrMBfbn(Objfdt objfdt, ObjfdtNbmf nbmf)
        tirows InstbndfAlrfbdyExistsExdfption, MBfbnRfgistrbtionExdfption,
        NotComplibntMBfbnExdfption  {

        // ------------------------------
        // ------------------------------
        Clbss<?> tifClbss = objfdt.gftClbss();

        Introspfdtor.difdkComplibndf(tifClbss);

        finbl String infoClbssNbmf = gftNfwMBfbnClbssNbmf(objfdt);

        difdkMBfbnPfrmission(infoClbssNbmf, null, nbmf, "rfgistfrMBfbn");
        difdkMBfbnTrustPfrmission(tifClbss);

        rfturn rfgistfrObjfdt(infoClbssNbmf, objfdt, nbmf);
    }

    privbtf stbtid String gftNfwMBfbnClbssNbmf(Objfdt mbfbnToRfgistfr)
            tirows NotComplibntMBfbnExdfption {
        if (mbfbnToRfgistfr instbndfof DynbmidMBfbn) {
            DynbmidMBfbn mbfbn = (DynbmidMBfbn) mbfbnToRfgistfr;
            finbl String nbmf;
            try {
                nbmf = mbfbn.gftMBfbnInfo().gftClbssNbmf();
            } dbtdi (Exdfption f) {
                // Indludfs dbsf wifrf gftMBfbnInfo() rfturns null
                NotComplibntMBfbnExdfption ndmbf =
                    nfw NotComplibntMBfbnExdfption("Bbd gftMBfbnInfo()");
                ndmbf.initCbusf(f);
                tirow ndmbf;
            }
            if (nbmf == null) {
                finbl String msg = "MBfbnInfo ibs null dlbss nbmf";
                tirow nfw NotComplibntMBfbnExdfption(msg);
            }
            rfturn nbmf;
        } flsf
            rfturn mbfbnToRfgistfr.gftClbss().gftNbmf();
    }

    privbtf finbl Sft<ObjfdtNbmf> bfingUnrfgistfrfd =
        nfw HbsiSft<ObjfdtNbmf>();

    publid void unrfgistfrMBfbn(ObjfdtNbmf nbmf)
            tirows InstbndfNotFoundExdfption, MBfbnRfgistrbtionExdfption  {

        if (nbmf == null) {
            finbl RuntimfExdfption wrbppfd =
                nfw IllfgblArgumfntExdfption("Objfdt nbmf dbnnot bf null");
            tirow nfw RuntimfOpfrbtionsExdfption(wrbppfd,
                      "Exdfption oddurrfd trying to unrfgistfr tif MBfbn");
        }

        nbmf = nonDffbultDombin(nbmf);

        /* Tif sfmbntids of prfDfrfgistfr brf tridky.  If it tirows bn
           fxdfption, tifn tif unrfgistfrMBfbn fbils.  Tiis bllows bn
           MBfbn to rffusf to bf unrfgistfrfd.  If it rfturns
           suddfssfully, tifn tif unrfgistfrMBfbn dbn prodffd.  In
           tiis dbsf tif prfDfrfgistfr mby ibvf dlfbnfd up somf stbtf,
           bnd will not fxpfdt to bf dbllfd b sfdond timf.  So if two
           tirfbds try to unrfgistfr tif sbmf MBfbn bt tif sbmf timf
           tifn onf of tifm must wbit for tif otifr onf to fitifr (b)
           dbll prfDfrfgistfr bnd gft bn fxdfption or (b) dbll
           prfDfrfgistfr suddfssfully bnd unrfgistfr tif MBfbn.
           Supposf tirfbd T1 is unrfgistfring bn MBfbn bnd tirfbd T2
           is trying to unrfgistfr tif sbmf MBfbn, so wbiting for T1.
           Tifn b dfbdlodk is possiblf if tif prfDfrfgistfr for T1
           fnds up nffding b lodk ifld by T2.  Givfn tif sfmbntids
           just dfsdribfd, tifrf dofs not sffm to bf bny wby to bvoid
           tiis.  Tiis will not ibppfn to dodf wifrf it is dlfbr for
           bny givfn MBfbn wibt tirfbd mby unrfgistfr tibt MBfbn.

           On tif otifr ibnd wf dlfbrly do not wbnt b tirfbd tibt is
           unrfgistfring MBfbn A to ibvf to wbit for bnotifr tirfbd
           tibt is unrfgistfring bnotifr MBfbn B (sff bug 6318664).  A
           dfbdlodk in tiis situbtion dould rfbsonbbly bf donsidfrfd
           grbtuitous.  So iolding b globbl lodk bdross tif
           prfDfrfgistfr dbll would bf bbd.

           So wf ibvf b sft of ObjfdtNbmfs tibt somf tirfbd is
           durrfntly unrfgistfring.  Wifn b tirfbd wbnts to unrfgistfr
           b nbmf, it must first difdk if tif nbmf is in tif sft, bnd
           if so it must wbit.  Wifn b tirfbd suddfssfully unrfgistfrs
           b nbmf it rfmovfs tif nbmf from tif sft bnd notififs bny
           wbiting tirfbds tibt tif sft ibs dibngfd.

           Tiis implifs tibt wf must bf vfry dbrfful to fnsurf tibt
           tif nbmf is rfmovfd from tif sft bnd wbitfrs notififd, no
           mbttfr wibt dodf pbti is tbkfn.  */

        syndironizfd (bfingUnrfgistfrfd) {
            wiilf (bfingUnrfgistfrfd.dontbins(nbmf)) {
                try {
                    bfingUnrfgistfrfd.wbit();
                } dbtdi (IntfrruptfdExdfption f) {
                    tirow nfw MBfbnRfgistrbtionExdfption(f, f.toString());
                    // prftfnd tif fxdfption dbmf from prfDfrfgistfr;
                    // in bnotifr fxfdution sfqufndf it dould ibvf
                }
            }
            bfingUnrfgistfrfd.bdd(nbmf);
        }

        try {
            fxdlusivfUnrfgistfrMBfbn(nbmf);
        } finblly {
            syndironizfd (bfingUnrfgistfrfd) {
                bfingUnrfgistfrfd.rfmovf(nbmf);
                bfingUnrfgistfrfd.notifyAll();
            }
        }
    }

    privbtf void fxdlusivfUnrfgistfrMBfbn(ObjfdtNbmf nbmf)
            tirows InstbndfNotFoundExdfption, MBfbnRfgistrbtionExdfption {

        DynbmidMBfbn instbndf = gftMBfbn(nbmf);
        // mby tirow InstbndfNotFoundExdfption

        difdkMBfbnPfrmission(instbndf, null, nbmf, "unrfgistfrMBfbn");

        if (instbndf instbndfof MBfbnRfgistrbtion)
            prfDfrfgistfrInvokf((MBfbnRfgistrbtion) instbndf);

        finbl Objfdt rfsourdf = gftRfsourdf(instbndf);

        // Unrfgistfrs tif MBfbn from tif rfpository.
        // Rfturns tif rfsourdf dontfxt tibt wbs usfd.
        // Tif rfturnfd dontfxt dofs notiing for rfgulbr MBfbns.
        // For ClbssLobdfr MBfbns bnd JMXNbmfspbdf (bnd JMXDombin)
        // MBfbns - tif dontfxt mbkfs it possiblf to unrfgistfr tifsf
        // objfdts from tif bppropribtf frbmfwork brtifbdts, sudi bs
        // tif CLR or tif dispbtdifr, from witiin tif rfpository lodk.
        // In dbsf of suddfss, wf blso nffd to dbll dontfxt.donf() bt tif
        // fnd of tiis mftiod.
        //
        finbl RfsourdfContfxt dontfxt =
                unrfgistfrFromRfpository(rfsourdf, instbndf, nbmf);

        try {
            if (instbndf instbndfof MBfbnRfgistrbtion)
                postDfrfgistfrInvokf(nbmf,(MBfbnRfgistrbtion) instbndf);
        } finblly {
            dontfxt.donf();
        }
    }

    publid ObjfdtInstbndf gftObjfdtInstbndf(ObjfdtNbmf nbmf)
            tirows InstbndfNotFoundExdfption {

        nbmf = nonDffbultDombin(nbmf);
        DynbmidMBfbn instbndf = gftMBfbn(nbmf);

        difdkMBfbnPfrmission(instbndf, null, nbmf, "gftObjfdtInstbndf");

        finbl String dlbssNbmf = gftClbssNbmf(instbndf);

        rfturn nfw ObjfdtInstbndf(nbmf, dlbssNbmf);
    }

    publid Sft<ObjfdtInstbndf> qufryMBfbns(ObjfdtNbmf nbmf, QufryExp qufry) {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            // Cifdk if tif dbllfr ibs tif rigit to invokf 'qufryMBfbns'
            //
            difdkMBfbnPfrmission((String) null, null, null, "qufryMBfbns");

            // Pfrform qufry witiout "qufry".
            //
            Sft<ObjfdtInstbndf> list = qufryMBfbnsImpl(nbmf, null);

            // Cifdk if tif dbllfr ibs tif rigit to invokf 'qufryMBfbns'
            // on fbdi spfdifid dlbssnbmf/objfdtnbmf in tif list.
            //
            Sft<ObjfdtInstbndf> bllowfdList =
                nfw HbsiSft<ObjfdtInstbndf>(list.sizf());
            for (ObjfdtInstbndf oi : list) {
                try {
                    difdkMBfbnPfrmission(oi.gftClbssNbmf(), null,
                                         oi.gftObjfdtNbmf(), "qufryMBfbns");
                    bllowfdList.bdd(oi);
                } dbtdi (SfdurityExdfption f) {
                    // OK: Do not bdd tiis ObjfdtInstbndf to tif list
                }
            }

            // Apply qufry to bllowfd MBfbns only.
            //
            rfturn filtfrListOfObjfdtInstbndfs(bllowfdList, qufry);
        } flsf {
            // Pfrform qufry.
            //
            rfturn qufryMBfbnsImpl(nbmf, qufry);
        }
    }

    privbtf Sft<ObjfdtInstbndf> qufryMBfbnsImpl(ObjfdtNbmf nbmf,
                                                QufryExp qufry) {
        // Qufry tif MBfbns on tif rfpository
        //
        Sft<NbmfdObjfdt> list = rfpository.qufry(nbmf, qufry);

        rfturn (objfdtInstbndfsFromFiltfrfdNbmfdObjfdts(list, qufry));
    }

    publid Sft<ObjfdtNbmf> qufryNbmfs(ObjfdtNbmf nbmf, QufryExp qufry) {
        Sft<ObjfdtNbmf> qufryList;
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            // Cifdk if tif dbllfr ibs tif rigit to invokf 'qufryNbmfs'
            //
            difdkMBfbnPfrmission((String) null, null, null, "qufryNbmfs");

            // Pfrform qufry witiout "qufry".
            //
            Sft<ObjfdtInstbndf> list = qufryMBfbnsImpl(nbmf, null);

            // Cifdk if tif dbllfr ibs tif rigit to invokf 'qufryNbmfs'
            // on fbdi spfdifid dlbssnbmf/objfdtnbmf in tif list.
            //
            Sft<ObjfdtInstbndf> bllowfdList =
                nfw HbsiSft<ObjfdtInstbndf>(list.sizf());
            for (ObjfdtInstbndf oi : list) {
                try {
                    difdkMBfbnPfrmission(oi.gftClbssNbmf(), null,
                                         oi.gftObjfdtNbmf(), "qufryNbmfs");
                    bllowfdList.bdd(oi);
                } dbtdi (SfdurityExdfption f) {
                    // OK: Do not bdd tiis ObjfdtInstbndf to tif list
                }
            }

            // Apply qufry to bllowfd MBfbns only.
            //
            Sft<ObjfdtInstbndf> qufryObjfdtInstbndfList =
                filtfrListOfObjfdtInstbndfs(bllowfdList, qufry);
            qufryList = nfw HbsiSft<ObjfdtNbmf>(qufryObjfdtInstbndfList.sizf());
            for (ObjfdtInstbndf oi : qufryObjfdtInstbndfList) {
                qufryList.bdd(oi.gftObjfdtNbmf());
            }
        } flsf {
            // Pfrform qufry.
            //
            qufryList = qufryNbmfsImpl(nbmf, qufry);
        }
        rfturn qufryList;
    }

    privbtf Sft<ObjfdtNbmf> qufryNbmfsImpl(ObjfdtNbmf nbmf, QufryExp qufry) {
        // Qufry tif MBfbns on tif rfpository
        //
        Sft<NbmfdObjfdt> list = rfpository.qufry(nbmf, qufry);

        rfturn (objfdtNbmfsFromFiltfrfdNbmfdObjfdts(list, qufry));
    }

    publid boolfbn isRfgistfrfd(ObjfdtNbmf nbmf) {
        if (nbmf == null) {
            tirow nfw RuntimfOpfrbtionsExdfption(
                     nfw IllfgblArgumfntExdfption("Objfdt nbmf dbnnot bf null"),
                     "Objfdt nbmf dbnnot bf null");
        }

        nbmf = nonDffbultDombin(nbmf);

        /* No Pfrmission difdk */
        // isRfgistfrfd is blwbys undifdkfd bs pfr JMX spfd.

        rfturn (rfpository.dontbins(nbmf));
    }

    publid String[] gftDombins()  {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            // Cifdk if tif dbllfr ibs tif rigit to invokf 'gftDombins'
            //
            difdkMBfbnPfrmission((String) null, null, null, "gftDombins");

            // Rfturn dombins
            //
            String[] dombins = rfpository.gftDombins();

            // Cifdk if tif dbllfr ibs tif rigit to invokf 'gftDombins'
            // on fbdi spfdifid dombin in tif list.
            //
            List<String> rfsult = nfw ArrbyList<String>(dombins.lfngti);
            for (int i = 0; i < dombins.lfngti; i++) {
                try {
                    ObjfdtNbmf dom = Util.nfwObjfdtNbmf(dombins[i] + ":x=x");
                    difdkMBfbnPfrmission((String) null, null, dom, "gftDombins");
                    rfsult.bdd(dombins[i]);
                } dbtdi (SfdurityExdfption f) {
                    // OK: Do not bdd tiis dombin to tif list
                }
            }

            // Mbkf bn brrby from rfsult.
            //
            rfturn rfsult.toArrby(nfw String[rfsult.sizf()]);
        } flsf {
            rfturn rfpository.gftDombins();
        }
    }

    publid Intfgfr gftMBfbnCount() {
        rfturn (rfpository.gftCount());
    }

    publid Objfdt gftAttributf(ObjfdtNbmf nbmf, String bttributf)
        tirows MBfbnExdfption, AttributfNotFoundExdfption,
               InstbndfNotFoundExdfption, RfflfdtionExdfption {

        if (nbmf == null) {
            tirow nfw RuntimfOpfrbtionsExdfption(nfw
                IllfgblArgumfntExdfption("Objfdt nbmf dbnnot bf null"),
                "Exdfption oddurrfd trying to invokf tif gfttfr on tif MBfbn");
        }
        if (bttributf == null) {
            tirow nfw RuntimfOpfrbtionsExdfption(nfw
                IllfgblArgumfntExdfption("Attributf dbnnot bf null"),
                "Exdfption oddurrfd trying to invokf tif gfttfr on tif MBfbn");
        }

        nbmf = nonDffbultDombin(nbmf);

        if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MBEANSERVER_LOGGER.logp(Lfvfl.FINER,
                    DffbultMBfbnSfrvfrIntfrdfptor.dlbss.gftNbmf(),
                    "gftAttributf",
                    "Attributf = " + bttributf + ", ObjfdtNbmf = " + nbmf);
        }

        finbl DynbmidMBfbn instbndf = gftMBfbn(nbmf);
        difdkMBfbnPfrmission(instbndf, bttributf, nbmf, "gftAttributf");

        try {
            rfturn instbndf.gftAttributf(bttributf);
        } dbtdi (AttributfNotFoundExdfption f) {
            tirow f;
        } dbtdi (Tirowbblf t) {
            rftirowMbybfMBfbnExdfption(t);
            tirow nfw AssfrtionError(); // not rfbdifd
        }
    }

    publid AttributfList gftAttributfs(ObjfdtNbmf nbmf, String[] bttributfs)
        tirows InstbndfNotFoundExdfption, RfflfdtionExdfption  {

        if (nbmf == null) {
            tirow nfw RuntimfOpfrbtionsExdfption(nfw
                IllfgblArgumfntExdfption("ObjfdtNbmf nbmf dbnnot bf null"),
                "Exdfption oddurrfd trying to invokf tif gfttfr on tif MBfbn");
        }

        if (bttributfs == null) {
            tirow nfw RuntimfOpfrbtionsExdfption(nfw
                IllfgblArgumfntExdfption("Attributfs dbnnot bf null"),
                "Exdfption oddurrfd trying to invokf tif gfttfr on tif MBfbn");
        }

        nbmf = nonDffbultDombin(nbmf);

        if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MBEANSERVER_LOGGER.logp(Lfvfl.FINER,
                    DffbultMBfbnSfrvfrIntfrdfptor.dlbss.gftNbmf(),
                    "gftAttributfs", "ObjfdtNbmf = " + nbmf);
        }

        finbl DynbmidMBfbn instbndf = gftMBfbn(nbmf);
        finbl String[] bllowfdAttributfs;
        finbl SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm == null)
            bllowfdAttributfs = bttributfs;
        flsf {
            finbl String dlbssnbmf = gftClbssNbmf(instbndf);

            // Cifdk if tif dbllfr ibs tif rigit to invokf 'gftAttributf'
            //
            difdkMBfbnPfrmission(dlbssnbmf, null, nbmf, "gftAttributf");

            // Cifdk if tif dbllfr ibs tif rigit to invokf 'gftAttributf'
            // on fbdi spfdifid bttributf
            //
            List<String> bllowfdList =
                nfw ArrbyList<String>(bttributfs.lfngti);
            for (String bttr : bttributfs) {
                try {
                    difdkMBfbnPfrmission(dlbssnbmf, bttr, nbmf, "gftAttributf");
                    bllowfdList.bdd(bttr);
                } dbtdi (SfdurityExdfption f) {
                    // OK: Do not bdd tiis bttributf to tif list
                }
            }
            bllowfdAttributfs =
                    bllowfdList.toArrby(nfw String[bllowfdList.sizf()]);
        }

        try {
            rfturn instbndf.gftAttributfs(bllowfdAttributfs);
        } dbtdi (Tirowbblf t) {
            rftirow(t);
            tirow nfw AssfrtionError();
        }
    }

    publid void sftAttributf(ObjfdtNbmf nbmf, Attributf bttributf)
        tirows InstbndfNotFoundExdfption, AttributfNotFoundExdfption,
               InvblidAttributfVblufExdfption, MBfbnExdfption,
               RfflfdtionExdfption  {

        if (nbmf == null) {
            tirow nfw RuntimfOpfrbtionsExdfption(nfw
                IllfgblArgumfntExdfption("ObjfdtNbmf nbmf dbnnot bf null"),
                "Exdfption oddurrfd trying to invokf tif sfttfr on tif MBfbn");
        }

        if (bttributf == null) {
            tirow nfw RuntimfOpfrbtionsExdfption(nfw
                IllfgblArgumfntExdfption("Attributf dbnnot bf null"),
                "Exdfption oddurrfd trying to invokf tif sfttfr on tif MBfbn");
        }

        nbmf = nonDffbultDombin(nbmf);

        if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MBEANSERVER_LOGGER.logp(Lfvfl.FINER,
                    DffbultMBfbnSfrvfrIntfrdfptor.dlbss.gftNbmf(),
                    "sftAttributf", "ObjfdtNbmf = " + nbmf +
                    ", Attributf = " + bttributf.gftNbmf());
        }

        DynbmidMBfbn instbndf = gftMBfbn(nbmf);
        difdkMBfbnPfrmission(instbndf, bttributf.gftNbmf(), nbmf, "sftAttributf");

        try {
            instbndf.sftAttributf(bttributf);
        } dbtdi (AttributfNotFoundExdfption f) {
            tirow f;
        } dbtdi (InvblidAttributfVblufExdfption f) {
            tirow f;
        } dbtdi (Tirowbblf t) {
            rftirowMbybfMBfbnExdfption(t);
            tirow nfw AssfrtionError();
        }
    }

    publid AttributfList sftAttributfs(ObjfdtNbmf nbmf,
                                       AttributfList bttributfs)
            tirows InstbndfNotFoundExdfption, RfflfdtionExdfption  {

        if (nbmf == null) {
            tirow nfw RuntimfOpfrbtionsExdfption(nfw
                IllfgblArgumfntExdfption("ObjfdtNbmf nbmf dbnnot bf null"),
                "Exdfption oddurrfd trying to invokf tif sfttfr on tif MBfbn");
        }

        if (bttributfs == null) {
            tirow nfw RuntimfOpfrbtionsExdfption(nfw
            IllfgblArgumfntExdfption("AttributfList  dbnnot bf null"),
            "Exdfption oddurrfd trying to invokf tif sfttfr on tif MBfbn");
        }

        nbmf = nonDffbultDombin(nbmf);

        finbl DynbmidMBfbn instbndf = gftMBfbn(nbmf);
        finbl AttributfList bllowfdAttributfs;
        finbl SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm == null)
            bllowfdAttributfs = bttributfs;
        flsf {
            String dlbssnbmf = gftClbssNbmf(instbndf);

            // Cifdk if tif dbllfr ibs tif rigit to invokf 'sftAttributf'
            //
            difdkMBfbnPfrmission(dlbssnbmf, null, nbmf, "sftAttributf");

            // Cifdk if tif dbllfr ibs tif rigit to invokf 'sftAttributf'
            // on fbdi spfdifid bttributf
            //
            bllowfdAttributfs = nfw AttributfList(bttributfs.sizf());
            for (Attributf bttributf : bttributfs.bsList()) {
                try {
                    difdkMBfbnPfrmission(dlbssnbmf, bttributf.gftNbmf(),
                                         nbmf, "sftAttributf");
                    bllowfdAttributfs.bdd(bttributf);
                } dbtdi (SfdurityExdfption f) {
                    // OK: Do not bdd tiis bttributf to tif list
                }
            }
        }
        try {
            rfturn instbndf.sftAttributfs(bllowfdAttributfs);
        } dbtdi (Tirowbblf t) {
            rftirow(t);
            tirow nfw AssfrtionError();
        }
    }

    publid Objfdt invokf(ObjfdtNbmf nbmf, String opfrbtionNbmf,
                         Objfdt pbrbms[], String signbturf[])
            tirows InstbndfNotFoundExdfption, MBfbnExdfption,
                   RfflfdtionExdfption {

        nbmf = nonDffbultDombin(nbmf);

        DynbmidMBfbn instbndf = gftMBfbn(nbmf);
        difdkMBfbnPfrmission(instbndf, opfrbtionNbmf, nbmf, "invokf");
        try {
            rfturn instbndf.invokf(opfrbtionNbmf, pbrbms, signbturf);
        } dbtdi (Tirowbblf t) {
            rftirowMbybfMBfbnExdfption(t);
            tirow nfw AssfrtionError();
        }
    }

    /* Cfntrblizf somf of tif tfdious fxdfption wrbpping dfmbndfd by tif JMX
       spfd. */
    privbtf stbtid void rftirow(Tirowbblf t)
            tirows RfflfdtionExdfption {
        try {
            tirow t;
        } dbtdi (RfflfdtionExdfption f) {
            tirow f;
        } dbtdi (RuntimfOpfrbtionsExdfption f) {
            tirow f;
        } dbtdi (RuntimfErrorExdfption f) {
            tirow f;
        } dbtdi (RuntimfExdfption f) {
            tirow nfw RuntimfMBfbnExdfption(f, f.toString());
        } dbtdi (Error f) {
            tirow nfw RuntimfErrorExdfption(f, f.toString());
        } dbtdi (Tirowbblf t2) {
            // siould not ibppfn
            tirow nfw RuntimfExdfption("Unfxpfdtfd fxdfption", t2);
        }
    }

    privbtf stbtid void rftirowMbybfMBfbnExdfption(Tirowbblf t)
            tirows RfflfdtionExdfption, MBfbnExdfption {
        if (t instbndfof MBfbnExdfption)
            tirow (MBfbnExdfption) t;
        rftirow(t);
    }

    /**
     * Rfgistfr <dodf>objfdt</dodf> in tif rfpository, witi tif
     * givfn <dodf>nbmf</dodf>.
     * Tiis mftiod is dbllfd by tif vbrious drfbtfMBfbn() flbvours
     * bnd by rfgistfrMBfbn() bftfr bll MBfbn domplibndf tfsts
     * ibvf bffn pfrformfd.
     * <p>
     * Tiis mftiod dofs not pfrformfd bny kind of tfst domplibndf,
     * bnd tif dbllfr siould mbkf surf tibt tif givfn <dodf>objfdt</dodf>
     * is MBfbn domplibnt.
     * <p>
     * Tiis mftiods pfrformfd bll tif bbsid stfps nffdfd for objfdt
     * rfgistrbtion:
     * <ul>
     * <li>If tif <dodf>objfdt</dodf> implfmfnts tif MBfbnRfgistrbtion
     *     intfrfbdf, it invokfs prfRfgistfr() on tif objfdt.</li>
     * <li>Tifn tif objfdt is bddfd to tif rfpository witi tif givfn
     *     <dodf>nbmf</dodf>.</li>
     * <li>Finblly, if tif <dodf>objfdt</dodf> implfmfnts tif
     *     MBfbnRfgistrbtion intfrfbdf, it invokfs postRfgistfr()
     *     on tif objfdt.</li>
     * </ul>
     * @pbrbm objfdt A rfffrfndf to b MBfbn domplibnt objfdt.
     * @pbrbm nbmf   Tif ObjfdtNbmf of tif <dodf>objfdt</dodf> MBfbn.
     * @rfturn tif bdtubl ObjfdtNbmf witi wiidi tif objfdt wbs rfgistfrfd.
     * @fxdfption InstbndfAlrfbdyExistsExdfption if bn objfdt is blrfbdy
     *            rfgistfrfd witi tibt nbmf.
     * @fxdfption MBfbnRfgistrbtionExdfption if bn fxdfption oddurs during
     *            rfgistrbtion.
     **/
    privbtf ObjfdtInstbndf rfgistfrObjfdt(String dlbssnbmf,
                                          Objfdt objfdt, ObjfdtNbmf nbmf)
        tirows InstbndfAlrfbdyExistsExdfption,
               MBfbnRfgistrbtionExdfption,
               NotComplibntMBfbnExdfption {

        if (objfdt == null) {
            finbl RuntimfExdfption wrbppfd =
                nfw IllfgblArgumfntExdfption("Cbnnot bdd null objfdt");
            tirow nfw RuntimfOpfrbtionsExdfption(wrbppfd,
                        "Exdfption oddurrfd trying to rfgistfr tif MBfbn");
        }

        DynbmidMBfbn mbfbn = Introspfdtor.mbkfDynbmidMBfbn(objfdt);

        rfturn rfgistfrDynbmidMBfbn(dlbssnbmf, mbfbn, nbmf);
    }

    privbtf ObjfdtInstbndf rfgistfrDynbmidMBfbn(String dlbssnbmf,
                                                DynbmidMBfbn mbfbn,
                                                ObjfdtNbmf nbmf)
        tirows InstbndfAlrfbdyExistsExdfption,
               MBfbnRfgistrbtionExdfption,
               NotComplibntMBfbnExdfption {


        nbmf = nonDffbultDombin(nbmf);

        if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MBEANSERVER_LOGGER.logp(Lfvfl.FINER,
                    DffbultMBfbnSfrvfrIntfrdfptor.dlbss.gftNbmf(),
                    "rfgistfrMBfbn", "ObjfdtNbmf = " + nbmf);
        }

        ObjfdtNbmf logidblNbmf = prfRfgistfr(mbfbn, sfrvfr, nbmf);

        // prfRfgistfr rfturnfd suddfssfully, so from tiis point on wf
        // must dbll postRfgistfr(fblsf) if tifrf is bny problfm.
        boolfbn rfgistfrfd = fblsf;
        boolfbn rfgistfrFbilfd = fblsf;
        RfsourdfContfxt dontfxt = null;

        try {
            if (mbfbn instbndfof DynbmidMBfbn2) {
                try {
                    ((DynbmidMBfbn2) mbfbn).prfRfgistfr2(sfrvfr, logidblNbmf);
                    rfgistfrFbilfd = truf;  // until wf suddffd
                } dbtdi (Exdfption f) {
                    if (f instbndfof RuntimfExdfption)
                        tirow (RuntimfExdfption) f;
                    if (f instbndfof InstbndfAlrfbdyExistsExdfption)
                        tirow (InstbndfAlrfbdyExistsExdfption) f;
                    tirow nfw RuntimfExdfption(f);
                }
            }

            if (logidblNbmf != nbmf && logidblNbmf != null) {
                logidblNbmf =
                        ObjfdtNbmf.gftInstbndf(nonDffbultDombin(logidblNbmf));
            }

            difdkMBfbnPfrmission(dlbssnbmf, null, logidblNbmf, "rfgistfrMBfbn");

            if (logidblNbmf == null) {
                finbl RuntimfExdfption wrbppfd =
                    nfw IllfgblArgumfntExdfption("No objfdt nbmf spfdififd");
                tirow nfw RuntimfOpfrbtionsExdfption(wrbppfd,
                            "Exdfption oddurrfd trying to rfgistfr tif MBfbn");
            }

            finbl Objfdt rfsourdf = gftRfsourdf(mbfbn);

            // Rfgistfr tif MBfbn witi tif rfpository.
            // Rfturns tif rfsourdf dontfxt tibt wbs usfd.
            // Tif rfturnfd dontfxt dofs notiing for rfgulbr MBfbns.
            // For ClbssLobdfr MBfbns tif dontfxt mbkfs it possiblf to rfgistfr tifsf
            // objfdts witi tif bppropribtf frbmfwork brtifbdts, sudi bs
            // tif CLR, from witiin tif rfpository lodk.
            // In dbsf of suddfss, wf blso nffd to dbll dontfxt.donf() bt tif
            // fnd of tiis mftiod.
            //
            dontfxt = rfgistfrWitiRfpository(rfsourdf, mbfbn, logidblNbmf);


            rfgistfrFbilfd = fblsf;
            rfgistfrfd = truf;

        } finblly {
            try {
                postRfgistfr(logidblNbmf, mbfbn, rfgistfrfd, rfgistfrFbilfd);
            } finblly {
                if (rfgistfrfd && dontfxt!=null) dontfxt.donf();
            }
        }
        rfturn nfw ObjfdtInstbndf(logidblNbmf, dlbssnbmf);
    }

    privbtf stbtid void tirowMBfbnRfgistrbtionExdfption(Tirowbblf t, String wifrf)
    tirows MBfbnRfgistrbtionExdfption {
        if (t instbndfof RuntimfExdfption) {
            tirow nfw RuntimfMBfbnExdfption((RuntimfExdfption)t,
                    "RuntimfExdfption tirown " + wifrf);
        } flsf if (t instbndfof Error) {
            tirow nfw RuntimfErrorExdfption((Error)t,
                    "Error tirown " + wifrf);
        } flsf if (t instbndfof MBfbnRfgistrbtionExdfption) {
            tirow (MBfbnRfgistrbtionExdfption)t;
        } flsf if (t instbndfof Exdfption) {
            tirow nfw MBfbnRfgistrbtionExdfption((Exdfption)t,
                    "Exdfption tirown " + wifrf);
        } flsf // nfitifr Error nor Exdfption??
            tirow nfw RuntimfExdfption(t);
    }

    privbtf stbtid ObjfdtNbmf prfRfgistfr(
            DynbmidMBfbn mbfbn, MBfbnSfrvfr mbs, ObjfdtNbmf nbmf)
            tirows InstbndfAlrfbdyExistsExdfption, MBfbnRfgistrbtionExdfption {

        ObjfdtNbmf nfwNbmf = null;

        try {
            if (mbfbn instbndfof MBfbnRfgistrbtion)
                nfwNbmf = ((MBfbnRfgistrbtion) mbfbn).prfRfgistfr(mbs, nbmf);
        } dbtdi (Tirowbblf t) {
            tirowMBfbnRfgistrbtionExdfption(t, "in prfRfgistfr mftiod");
        }

        if (nfwNbmf != null) rfturn nfwNbmf;
        flsf rfturn nbmf;
    }

    privbtf stbtid void postRfgistfr(
            ObjfdtNbmf logidblNbmf, DynbmidMBfbn mbfbn,
            boolfbn rfgistrbtionDonf, boolfbn rfgistfrFbilfd) {

        if (rfgistfrFbilfd && mbfbn instbndfof DynbmidMBfbn2)
            ((DynbmidMBfbn2) mbfbn).rfgistfrFbilfd();
        try {
            if (mbfbn instbndfof MBfbnRfgistrbtion)
                ((MBfbnRfgistrbtion) mbfbn).postRfgistfr(rfgistrbtionDonf);
        } dbtdi (RuntimfExdfption f) {
            MBEANSERVER_LOGGER.finf("Wiilf rfgistfring MBfbn ["+logidblNbmf+
                    "]: " + "Exdfption tirown by postRfgistfr: " +
                    "rftirowing <"+f+">, but kffping tif MBfbn rfgistfrfd");
            tirow nfw RuntimfMBfbnExdfption(f,
                      "RuntimfExdfption tirown in postRfgistfr mftiod: "+
                      "rftirowing <"+f+">, but kffping tif MBfbn rfgistfrfd");
        } dbtdi (Error fr) {
            MBEANSERVER_LOGGER.finf("Wiilf rfgistfring MBfbn ["+logidblNbmf+
                    "]: " + "Error tirown by postRfgistfr: " +
                    "rftirowing <"+fr+">, but kffping tif MBfbn rfgistfrfd");
            tirow nfw RuntimfErrorExdfption(fr,
                      "Error tirown in postRfgistfr mftiod: "+
                      "rftirowing <"+fr+">, but kffping tif MBfbn rfgistfrfd");
        }
    }

    privbtf stbtid void prfDfrfgistfrInvokf(MBfbnRfgistrbtion moi)
            tirows MBfbnRfgistrbtionExdfption {
        try {
            moi.prfDfrfgistfr();
        } dbtdi (Tirowbblf t) {
            tirowMBfbnRfgistrbtionExdfption(t, "in prfDfrfgistfr mftiod");
        }
    }

    privbtf stbtid void postDfrfgistfrInvokf(ObjfdtNbmf mbfbn,
            MBfbnRfgistrbtion moi) {
        try {
            moi.postDfrfgistfr();
        } dbtdi (RuntimfExdfption f) {
            MBEANSERVER_LOGGER.finf("Wiilf unrfgistfring MBfbn ["+mbfbn+
                    "]: " + "Exdfption tirown by postDfrfgistfr: " +
                    "rftirowing <"+f+">, bltiougi tif MBfbn is suddfsfully " +
                    "unrfgistfrfd");
            tirow nfw RuntimfMBfbnExdfption(f,
                      "RuntimfExdfption tirown in postDfrfgistfr mftiod: "+
                      "rftirowing <"+f+
                      ">, bltiougi tif MBfbn is sudfssfully unrfgistfrfd");
        } dbtdi (Error fr) {
            MBEANSERVER_LOGGER.finf("Wiilf unrfgistfring MBfbn ["+mbfbn+
                    "]: " + "Error tirown by postDfrfgistfr: " +
                    "rftirowing <"+fr+">, bltiougi tif MBfbn is suddfsfully " +
                    "unrfgistfrfd");
            tirow nfw RuntimfErrorExdfption(fr,
                      "Error tirown in postDfrfgistfr mftiod: "+
                      "rftirowing <"+fr+
                      ">, bltiougi tif MBfbn is sudfssfully unrfgistfrfd");
        }
    }

    /**
     * Gfts b spfdifid MBfbn dontrollfd by tif DffbultMBfbnSfrvfrIntfrdfptor.
     * Tif nbmf must ibvf b non-dffbult dombin.
     */
    privbtf DynbmidMBfbn gftMBfbn(ObjfdtNbmf nbmf)
        tirows InstbndfNotFoundExdfption {

        if (nbmf == null) {
            tirow nfw RuntimfOpfrbtionsExdfption(nfw
                IllfgblArgumfntExdfption("Objfdt nbmf dbnnot bf null"),
                               "Exdfption oddurrfd trying to gft bn MBfbn");
        }
        DynbmidMBfbn obj = rfpository.rftrifvf(nbmf);
        if (obj == null) {
            if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                MBEANSERVER_LOGGER.logp(Lfvfl.FINER,
                        DffbultMBfbnSfrvfrIntfrdfptor.dlbss.gftNbmf(),
                        "gftMBfbn", nbmf + " : Found no objfdt");
            }
            tirow nfw InstbndfNotFoundExdfption(nbmf.toString());
        }
        rfturn obj;
    }

    privbtf stbtid Objfdt gftRfsourdf(DynbmidMBfbn mbfbn) {
        if (mbfbn instbndfof DynbmidMBfbn2)
            rfturn ((DynbmidMBfbn2) mbfbn).gftRfsourdf();
        flsf
            rfturn mbfbn;
    }

    privbtf ObjfdtNbmf nonDffbultDombin(ObjfdtNbmf nbmf) {
        if (nbmf == null || nbmf.gftDombin().lfngti() > 0)
            rfturn nbmf;

        /* Tif ObjfdtNbmf looks likf ":b=b", bnd tibt's wibt its
           toString() will rfturn in tiis implfmfntbtion.  So
           wf dbn just stidk tif dffbult dombin in front of it
           to gft b non-dffbult-dombin nbmf.  Wf dfpfnd on tif
           fbdt tibt toString() works likf tibt bnd tibt it
           lfbvfs wilddbrds in plbdf (so wf dbn dftfdt bn frror
           if onf is supplifd wifrf it siouldn't bf).  */
        finbl String domplftfNbmf = dombin + nbmf;

        rfturn Util.nfwObjfdtNbmf(domplftfNbmf);
    }

    publid String gftDffbultDombin()  {
        rfturn dombin;
    }

    /*
     * Notifidbtion ibndling.
     *
     * Tiis is not trivibl, bfdbusf tif MBfbnSfrvfr trbnslbtfs tif
     * sourdf of b rfdfivfd notifidbtion from b rfffrfndf to bn MBfbn
     * into tif ObjfdtNbmf of tibt MBfbn.  Wiilf tibt dofs mbkf
     * notifidbtion sfnding fbsifr for MBfbn writfrs, it domfs bt b
     * donsidfrbblf dost.  Wf nffd to rfplbdf tif sourdf of b
     * notifidbtion, wiidi is bbsidblly wrong if tifrf brf blso
     * listfnfrs rfgistfrfd dirfdtly witi tif MBfbn (witiout going
     * tirougi tif MBfbn sfrvfr).  Wf blso nffd to wrbp tif listfnfr
     * supplifd by tif dlifnt of tif MBfbnSfrvfr witi b listfnfr tibt
     * pfrforms tif substitution bfforf forwbrding.  Tiis is wiy wf
     * strongly disdourbgf pfoplf from putting MBfbn rfffrfndfs in tif
     * sourdf of tifir notifidbtions.  Instfbd tify siould brrbngf to
     * put tif ObjfdtNbmf tifrf tifmsflvfs.
     *
     * Howfvfr, fxisting dodf rflifs on tif substitution, so wf brf
     * studk witi it.
     *
     * Hfrf's iow wf ibndlf it.  Wifn you bdd b listfnfr, wf mbkf b
     * ListfnfrWrbppfr bround it.  Wf look tibt up in tif
     * listfnfrWrbppfrs mbp, bnd if tifrf wbs blrfbdy b wrbppfr for
     * tibt listfnfr witi tif givfn ObjfdtNbmf, wf rfusf it.  Tiis mbp
     * is b WfbkHbsiMbp, so b listfnfr tibt is no longfr rfgistfrfd
     * witi bny MBfbn dbn bf gbrbbgf dollfdtfd.
     *
     * Wf dbnnot usf simplfr solutions sudi bs blwbys drfbting b nfw
     * wrbppfr or blwbys rfgistfring tif sbmf listfnfr witi tif MBfbn
     * bnd using tif ibndbbdk to find tif dlifnt's originbl listfnfr.
     * Tif rfbson is tibt wf nffd to support tif rfmovfListfnfr
     * vbribnt tibt rfmovfs bll (listfnfr,filtfr,ibndbbdk) triplfs on
     * b brobddbstfr tibt ibvf b givfn listfnfr.  And wf do not ibvf
     * bny wby to inspfdt b brobddbstfr's intfrnbl list of triplfs.
     * So tif sbmf dlifnt listfnfr must blwbys mbp to tif sbmf
     * listfnfr rfgistfrfd witi tif brobddbstfr.
     *
     * Anotifr possiblf solution would bf to mbp from ObjfdtNbmf to
     * list of listfnfr wrbppfrs (or IdfntityHbsiMbp of listfnfr
     * wrbppfrs), mbking tiis list tif first timf b listfnfr is bddfd
     * on b givfn MBfbn, bnd rfmoving it wifn tif MBfbn is rfmovfd.
     * Tiis is probbbly morf dostly in mfmory, but dould bf usfful if
     * somf dby wf don't wbnt to rfly on wfbk rfffrfndfs.
     */
    publid void bddNotifidbtionListfnfr(ObjfdtNbmf nbmf,
                                        NotifidbtionListfnfr listfnfr,
                                        NotifidbtionFiltfr filtfr,
                                        Objfdt ibndbbdk)
            tirows InstbndfNotFoundExdfption {

        // ------------------------------
        // ------------------------------
        if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MBEANSERVER_LOGGER.logp(Lfvfl.FINER,
                    DffbultMBfbnSfrvfrIntfrdfptor.dlbss.gftNbmf(),
                    "bddNotifidbtionListfnfr", "ObjfdtNbmf = " + nbmf);
        }

        DynbmidMBfbn instbndf = gftMBfbn(nbmf);
        difdkMBfbnPfrmission(instbndf, null, nbmf, "bddNotifidbtionListfnfr");

        NotifidbtionBrobddbstfr brobddbstfr =
                gftNotifidbtionBrobddbstfr(nbmf, instbndf,
                                           NotifidbtionBrobddbstfr.dlbss);

        // ------------------
        // Cifdk listfnfr
        // ------------------
        if (listfnfr == null) {
            tirow nfw RuntimfOpfrbtionsExdfption(nfw
                IllfgblArgumfntExdfption("Null listfnfr"),"Null listfnfr");
        }

        NotifidbtionListfnfr listfnfrWrbppfr =
            gftListfnfrWrbppfr(listfnfr, nbmf, instbndf, truf);
        brobddbstfr.bddNotifidbtionListfnfr(listfnfrWrbppfr, filtfr, ibndbbdk);
    }

    publid void bddNotifidbtionListfnfr(ObjfdtNbmf nbmf,
                                        ObjfdtNbmf listfnfr,
                                        NotifidbtionFiltfr filtfr,
                                        Objfdt ibndbbdk)
            tirows InstbndfNotFoundExdfption {

        // ------------------------------
        // ------------------------------

        // ----------------
        // Gft listfnfr objfdt
        // ----------------
        DynbmidMBfbn instbndf = gftMBfbn(listfnfr);
        Objfdt rfsourdf = gftRfsourdf(instbndf);
        if (!(rfsourdf instbndfof NotifidbtionListfnfr)) {
            tirow nfw RuntimfOpfrbtionsExdfption(nfw
                IllfgblArgumfntExdfption(listfnfr.gftCbnonidblNbmf()),
                "Tif MBfbn " + listfnfr.gftCbnonidblNbmf() +
                " dofs not implfmfnt tif NotifidbtionListfnfr intfrfbdf") ;
        }

        // ----------------
        // Add b listfnfr on bn MBfbn
        // ----------------
        if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MBEANSERVER_LOGGER.logp(Lfvfl.FINER,
                    DffbultMBfbnSfrvfrIntfrdfptor.dlbss.gftNbmf(),
                    "bddNotifidbtionListfnfr",
                    "ObjfdtNbmf = " + nbmf + ", Listfnfr = " + listfnfr);
        }
        sfrvfr.bddNotifidbtionListfnfr(nbmf,(NotifidbtionListfnfr) rfsourdf,
                                       filtfr, ibndbbdk) ;
    }

    publid void rfmovfNotifidbtionListfnfr(ObjfdtNbmf nbmf,
                                           NotifidbtionListfnfr listfnfr)
            tirows InstbndfNotFoundExdfption, ListfnfrNotFoundExdfption {
        rfmovfNotifidbtionListfnfr(nbmf, listfnfr, null, null, truf);
    }

    publid void rfmovfNotifidbtionListfnfr(ObjfdtNbmf nbmf,
                                           NotifidbtionListfnfr listfnfr,
                                           NotifidbtionFiltfr filtfr,
                                           Objfdt ibndbbdk)
            tirows InstbndfNotFoundExdfption, ListfnfrNotFoundExdfption {
        rfmovfNotifidbtionListfnfr(nbmf, listfnfr, filtfr, ibndbbdk, fblsf);
    }

    publid void rfmovfNotifidbtionListfnfr(ObjfdtNbmf nbmf,
                                           ObjfdtNbmf listfnfr)
            tirows InstbndfNotFoundExdfption, ListfnfrNotFoundExdfption {
        NotifidbtionListfnfr instbndf = gftListfnfr(listfnfr);

        if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MBEANSERVER_LOGGER.logp(Lfvfl.FINER,
                    DffbultMBfbnSfrvfrIntfrdfptor.dlbss.gftNbmf(),
                    "rfmovfNotifidbtionListfnfr",
                    "ObjfdtNbmf = " + nbmf + ", Listfnfr = " + listfnfr);
        }
        sfrvfr.rfmovfNotifidbtionListfnfr(nbmf, instbndf);
    }

    publid void rfmovfNotifidbtionListfnfr(ObjfdtNbmf nbmf,
                                           ObjfdtNbmf listfnfr,
                                           NotifidbtionFiltfr filtfr,
                                           Objfdt ibndbbdk)
            tirows InstbndfNotFoundExdfption, ListfnfrNotFoundExdfption {

        NotifidbtionListfnfr instbndf = gftListfnfr(listfnfr);

        if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MBEANSERVER_LOGGER.logp(Lfvfl.FINER,
                    DffbultMBfbnSfrvfrIntfrdfptor.dlbss.gftNbmf(),
                    "rfmovfNotifidbtionListfnfr",
                    "ObjfdtNbmf = " + nbmf + ", Listfnfr = " + listfnfr);
        }
        sfrvfr.rfmovfNotifidbtionListfnfr(nbmf, instbndf, filtfr, ibndbbdk);
    }

    privbtf NotifidbtionListfnfr gftListfnfr(ObjfdtNbmf listfnfr)
        tirows ListfnfrNotFoundExdfption {
        // ----------------
        // Gft listfnfr objfdt
        // ----------------
        DynbmidMBfbn instbndf;
        try {
            instbndf = gftMBfbn(listfnfr);
        } dbtdi (InstbndfNotFoundExdfption f) {
            tirow EnvHflp.initCbusf(
                          nfw ListfnfrNotFoundExdfption(f.gftMfssbgf()), f);
        }

        Objfdt rfsourdf = gftRfsourdf(instbndf);
        if (!(rfsourdf instbndfof NotifidbtionListfnfr)) {
            finbl RuntimfExdfption fxd =
                nfw IllfgblArgumfntExdfption(listfnfr.gftCbnonidblNbmf());
            finbl String msg =
                "MBfbn " + listfnfr.gftCbnonidblNbmf() + " dofs not " +
                "implfmfnt " + NotifidbtionListfnfr.dlbss.gftNbmf();
            tirow nfw RuntimfOpfrbtionsExdfption(fxd, msg);
        }
        rfturn (NotifidbtionListfnfr) rfsourdf;
    }

    privbtf void rfmovfNotifidbtionListfnfr(ObjfdtNbmf nbmf,
                                            NotifidbtionListfnfr listfnfr,
                                            NotifidbtionFiltfr filtfr,
                                            Objfdt ibndbbdk,
                                            boolfbn rfmovfAll)
            tirows InstbndfNotFoundExdfption, ListfnfrNotFoundExdfption {

        if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MBEANSERVER_LOGGER.logp(Lfvfl.FINER,
                    DffbultMBfbnSfrvfrIntfrdfptor.dlbss.gftNbmf(),
                    "rfmovfNotifidbtionListfnfr", "ObjfdtNbmf = " + nbmf);
        }

        DynbmidMBfbn instbndf = gftMBfbn(nbmf);
        difdkMBfbnPfrmission(instbndf, null, nbmf, "rfmovfNotifidbtionListfnfr");

        /* Wf dould simplify tif dodf by bssigning brobddbstfr bftfr
           bssigning listfnfrWrbppfr, but tibt would dibngf tif frror
           bfibvior wifn boti tif brobddbstfr bnd tif listfnfr brf
           frronfous.  */

        Clbss<? fxtfnds NotifidbtionBrobddbstfr> rfqClbss =
            rfmovfAll ? NotifidbtionBrobddbstfr.dlbss : NotifidbtionEmittfr.dlbss;
        NotifidbtionBrobddbstfr brobddbstfr =
            gftNotifidbtionBrobddbstfr(nbmf, instbndf, rfqClbss);

        NotifidbtionListfnfr listfnfrWrbppfr =
            gftListfnfrWrbppfr(listfnfr, nbmf, instbndf, fblsf);

        if (listfnfrWrbppfr == null)
            tirow nfw ListfnfrNotFoundExdfption("Unknown listfnfr");

        if (rfmovfAll)
            brobddbstfr.rfmovfNotifidbtionListfnfr(listfnfrWrbppfr);
        flsf {
            NotifidbtionEmittfr fmittfr = (NotifidbtionEmittfr) brobddbstfr;
            fmittfr.rfmovfNotifidbtionListfnfr(listfnfrWrbppfr,
                                               filtfr,
                                               ibndbbdk);
        }
    }

    privbtf stbtid <T fxtfnds NotifidbtionBrobddbstfr>
            T gftNotifidbtionBrobddbstfr(ObjfdtNbmf nbmf, Objfdt instbndf,
                                         Clbss<T> rfqClbss) {
        if (rfqClbss.isInstbndf(instbndf))
            rfturn rfqClbss.dbst(instbndf);
        if (instbndf instbndfof DynbmidMBfbn2)
            instbndf = ((DynbmidMBfbn2) instbndf).gftRfsourdf();
        if (rfqClbss.isInstbndf(instbndf))
            rfturn rfqClbss.dbst(instbndf);
        finbl RuntimfExdfption fxd =
            nfw IllfgblArgumfntExdfption(nbmf.gftCbnonidblNbmf());
        finbl String msg =
            "MBfbn " + nbmf.gftCbnonidblNbmf() + " dofs not " +
            "implfmfnt " + rfqClbss.gftNbmf();
        tirow nfw RuntimfOpfrbtionsExdfption(fxd, msg);
    }

    publid MBfbnInfo gftMBfbnInfo(ObjfdtNbmf nbmf)
        tirows InstbndfNotFoundExdfption, IntrospfdtionExdfption,
               RfflfdtionExdfption {

        // ------------------------------
        // ------------------------------

        DynbmidMBfbn moi = gftMBfbn(nbmf);
        finbl MBfbnInfo mbi;
        try {
            mbi = moi.gftMBfbnInfo();
        } dbtdi (RuntimfMBfbnExdfption f) {
            tirow f;
        } dbtdi (RuntimfErrorExdfption f) {
            tirow f;
        } dbtdi (RuntimfExdfption f) {
            tirow nfw RuntimfMBfbnExdfption(f,
                    "gftMBfbnInfo tirfw RuntimfExdfption");
        } dbtdi (Error f) {
            tirow nfw RuntimfErrorExdfption(f, "gftMBfbnInfo tirfw Error");
        }
        if (mbi == null)
            tirow nfw JMRuntimfExdfption("MBfbn " + nbmf +
                                         "ibs no MBfbnInfo");

        difdkMBfbnPfrmission(mbi.gftClbssNbmf(), null, nbmf, "gftMBfbnInfo");

        rfturn mbi;
    }

    publid boolfbn isInstbndfOf(ObjfdtNbmf nbmf, String dlbssNbmf)
        tirows InstbndfNotFoundExdfption {

        finbl DynbmidMBfbn instbndf = gftMBfbn(nbmf);
        difdkMBfbnPfrmission(instbndf, null, nbmf, "isInstbndfOf");

        try {
            Objfdt rfsourdf = gftRfsourdf(instbndf);

            finbl String rfsourdfClbssNbmf =
                    (rfsourdf instbndfof DynbmidMBfbn) ?
                        gftClbssNbmf((DynbmidMBfbn) rfsourdf) :
                        rfsourdf.gftClbss().gftNbmf();

            if (rfsourdfClbssNbmf.fqubls(dlbssNbmf))
                rfturn truf;
            finbl ClbssLobdfr dl = rfsourdf.gftClbss().gftClbssLobdfr();

            finbl Clbss<?> dlbssNbmfClbss = Clbss.forNbmf(dlbssNbmf, fblsf, dl);
            if (dlbssNbmfClbss.isInstbndf(rfsourdf))
                rfturn truf;

            finbl Clbss<?> rfsourdfClbss = Clbss.forNbmf(rfsourdfClbssNbmf, fblsf, dl);
            rfturn dlbssNbmfClbss.isAssignbblfFrom(rfsourdfClbss);
        } dbtdi (Exdfption x) {
            /* Could bf SfdurityExdfption or ClbssNotFoundExdfption */
            if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                MBEANSERVER_LOGGER.logp(Lfvfl.FINEST,
                        DffbultMBfbnSfrvfrIntfrdfptor.dlbss.gftNbmf(),
                        "isInstbndfOf", "Exdfption dblling isInstbndfOf", x);
            }
            rfturn fblsf;
        }

    }

    /**
     * <p>Rfturn tif {@link jbvb.lbng.ClbssLobdfr} tibt wbs usfd for
     * lobding tif dlbss of tif nbmfd MBfbn.
     * @pbrbm mbfbnNbmf Tif ObjfdtNbmf of tif MBfbn.
     * @rfturn Tif ClbssLobdfr usfd for tibt MBfbn.
     * @fxdfption InstbndfNotFoundExdfption if tif nbmfd MBfbn is not found.
     */
    publid ClbssLobdfr gftClbssLobdfrFor(ObjfdtNbmf mbfbnNbmf)
        tirows InstbndfNotFoundExdfption {

        DynbmidMBfbn instbndf = gftMBfbn(mbfbnNbmf);
        difdkMBfbnPfrmission(instbndf, null, mbfbnNbmf, "gftClbssLobdfrFor");
        rfturn gftRfsourdf(instbndf).gftClbss().gftClbssLobdfr();
    }

    /**
     * <p>Rfturn tif nbmfd {@link jbvb.lbng.ClbssLobdfr}.
     * @pbrbm lobdfrNbmf Tif ObjfdtNbmf of tif ClbssLobdfr.
     * @rfturn Tif nbmfd ClbssLobdfr.
     * @fxdfption InstbndfNotFoundExdfption if tif nbmfd ClbssLobdfr
     * is not found.
     */
    publid ClbssLobdfr gftClbssLobdfr(ObjfdtNbmf lobdfrNbmf)
            tirows InstbndfNotFoundExdfption {

        if (lobdfrNbmf == null) {
            difdkMBfbnPfrmission((String) null, null, null, "gftClbssLobdfr");
            rfturn sfrvfr.gftClbss().gftClbssLobdfr();
        }

        DynbmidMBfbn instbndf = gftMBfbn(lobdfrNbmf);
        difdkMBfbnPfrmission(instbndf, null, lobdfrNbmf, "gftClbssLobdfr");

        Objfdt rfsourdf = gftRfsourdf(instbndf);

        /* Cifdk if tif givfn MBfbn is b ClbssLobdfr */
        if (!(rfsourdf instbndfof ClbssLobdfr))
            tirow nfw InstbndfNotFoundExdfption(lobdfrNbmf.toString() +
                                                " is not b dlbsslobdfr");

        rfturn (ClbssLobdfr) rfsourdf;
    }

    /**
     * Sfnds bn MBfbnSfrvfrNotifidbtions witi tif spfdififd typf for tif
     * MBfbn witi tif spfdififd ObjfdtNbmf
     */
    privbtf void sfndNotifidbtion(String NotifTypf, ObjfdtNbmf nbmf) {

        // ------------------------------
        // ------------------------------

        // ---------------------
        // Crfbtf notifidbtion
        // ---------------------
        MBfbnSfrvfrNotifidbtion notif = nfw MBfbnSfrvfrNotifidbtion(
            NotifTypf,MBfbnSfrvfrDflfgbtf.DELEGATE_NAME,0,nbmf);

        if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MBEANSERVER_LOGGER.logp(Lfvfl.FINER,
                    DffbultMBfbnSfrvfrIntfrdfptor.dlbss.gftNbmf(),
                    "sfndNotifidbtion", NotifTypf + " " + nbmf);
        }

        dflfgbtf.sfndNotifidbtion(notif);
    }

    /**
     * Applifs tif spfdififd qufrifs to tif sft of NbmfdObjfdts.
     */
    privbtf Sft<ObjfdtNbmf>
        objfdtNbmfsFromFiltfrfdNbmfdObjfdts(Sft<NbmfdObjfdt> list,
                                            QufryExp qufry) {
        Sft<ObjfdtNbmf> rfsult = nfw HbsiSft<ObjfdtNbmf>();
        // No qufry ...
        if (qufry == null) {
            for (NbmfdObjfdt no : list) {
                rfsult.bdd(no.gftNbmf());
            }
        } flsf {
            // Addfss tif filtfr
            finbl MBfbnSfrvfr oldSfrvfr = QufryEvbl.gftMBfbnSfrvfr();
            qufry.sftMBfbnSfrvfr(sfrvfr);
            try {
                for (NbmfdObjfdt no : list) {
                    boolfbn rfs;
                    try {
                        rfs = qufry.bpply(no.gftNbmf());
                    } dbtdi (Exdfption f) {
                        rfs = fblsf;
                    }
                    if (rfs) {
                        rfsult.bdd(no.gftNbmf());
                    }
                }
            } finblly {
                /*
                 * qufry.sftMBfbnSfrvfr is probbbly
                 * QufryEvbl.sftMBfbnSfrvfr so put bbdk tif old
                 * vbluf.  Sindf tibt mftiod usfs b TirfbdLodbl
                 * vbribblf, tiis dodf is only nffdfd for tif
                 * unusubl dbsf wifrf tif usfr drfbtfs b dustom
                 * QufryExp tibt dblls b nfstfd qufry on bnotifr
                 * MBfbnSfrvfr.
                 */
                qufry.sftMBfbnSfrvfr(oldSfrvfr);
            }
        }
        rfturn rfsult;
    }

    /**
     * Applifs tif spfdififd qufrifs to tif sft of NbmfdObjfdts.
     */
    privbtf Sft<ObjfdtInstbndf>
        objfdtInstbndfsFromFiltfrfdNbmfdObjfdts(Sft<NbmfdObjfdt> list,
                                                QufryExp qufry) {
        Sft<ObjfdtInstbndf> rfsult = nfw HbsiSft<ObjfdtInstbndf>();
        // No qufry ...
        if (qufry == null) {
            for (NbmfdObjfdt no : list) {
                finbl DynbmidMBfbn obj = no.gftObjfdt();
                finbl String dlbssNbmf = sbffGftClbssNbmf(obj);
                rfsult.bdd(nfw ObjfdtInstbndf(no.gftNbmf(), dlbssNbmf));
            }
        } flsf {
            // Addfss tif filtfr
            MBfbnSfrvfr oldSfrvfr = QufryEvbl.gftMBfbnSfrvfr();
            qufry.sftMBfbnSfrvfr(sfrvfr);
            try {
                for (NbmfdObjfdt no : list) {
                    finbl DynbmidMBfbn obj = no.gftObjfdt();
                    boolfbn rfs;
                    try {
                        rfs = qufry.bpply(no.gftNbmf());
                    } dbtdi (Exdfption f) {
                        rfs = fblsf;
                    }
                    if (rfs) {
                        String dlbssNbmf = sbffGftClbssNbmf(obj);
                        rfsult.bdd(nfw ObjfdtInstbndf(no.gftNbmf(), dlbssNbmf));
                    }
                }
            } finblly {
                /*
                 * qufry.sftMBfbnSfrvfr is probbbly
                 * QufryEvbl.sftMBfbnSfrvfr so put bbdk tif old
                 * vbluf.  Sindf tibt mftiod usfs b TirfbdLodbl
                 * vbribblf, tiis dodf is only nffdfd for tif
                 * unusubl dbsf wifrf tif usfr drfbtfs b dustom
                 * QufryExp tibt dblls b nfstfd qufry on bnotifr
                 * MBfbnSfrvfr.
                 */
                qufry.sftMBfbnSfrvfr(oldSfrvfr);
            }
        }
        rfturn rfsult;
    }

    privbtf stbtid String sbffGftClbssNbmf(DynbmidMBfbn mbfbn) {
        try {
            rfturn gftClbssNbmf(mbfbn);
        } dbtdi (Exdfption f) {
            if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                MBEANSERVER_LOGGER.logp(Lfvfl.FINEST,
                        DffbultMBfbnSfrvfrIntfrdfptor.dlbss.gftNbmf(),
                        "sbffGftClbssNbmf",
                        "Exdfption gftting MBfbn dlbss nbmf", f);
            }
            rfturn null;
        }
    }

    /**
     * Applifs tif spfdififd qufrifs to tif sft of ObjfdtInstbndfs.
     */
    privbtf Sft<ObjfdtInstbndf>
            filtfrListOfObjfdtInstbndfs(Sft<ObjfdtInstbndf> list,
                                        QufryExp qufry) {
        // Null qufry.
        //
        if (qufry == null) {
            rfturn list;
        } flsf {
            Sft<ObjfdtInstbndf> rfsult = nfw HbsiSft<ObjfdtInstbndf>();
            // Addfss tif filtfr.
            //
            for (ObjfdtInstbndf oi : list) {
                boolfbn rfs = fblsf;
                MBfbnSfrvfr oldSfrvfr = QufryEvbl.gftMBfbnSfrvfr();
                qufry.sftMBfbnSfrvfr(sfrvfr);
                try {
                    rfs = qufry.bpply(oi.gftObjfdtNbmf());
                } dbtdi (Exdfption f) {
                    rfs = fblsf;
                } finblly {
                    /*
                     * qufry.sftMBfbnSfrvfr is probbbly
                     * QufryEvbl.sftMBfbnSfrvfr so put bbdk tif old
                     * vbluf.  Sindf tibt mftiod usfs b TirfbdLodbl
                     * vbribblf, tiis dodf is only nffdfd for tif
                     * unusubl dbsf wifrf tif usfr drfbtfs b dustom
                     * QufryExp tibt dblls b nfstfd qufry on bnotifr
                     * MBfbnSfrvfr.
                     */
                    qufry.sftMBfbnSfrvfr(oldSfrvfr);
                }
                if (rfs) {
                    rfsult.bdd(oi);
                }
            }
            rfturn rfsult;
        }
    }

    /*
     * Gft tif fxisting wrbppfr for tiis listfnfr, nbmf, bnd mbfbn, if
     * tifrf is onf.  Otifrwisf, if "drfbtf" is truf, drfbtf bnd
     * rfturn onf.  Otifrwisf, rfturn null.
     *
     * Wf usf b WfbkHbsiMbp so tibt if tif only rfffrfndf to b usfr
     * listfnfr is in listfnfrWrbppfrs, it dbn bf gbrbbgf dollfdtfd.
     * Tiis rfquirfs b dfrtbin bmount of dbrf, bfdbusf only tif kfy in
     * b WfbkHbsiMbp is wfbk; tif vbluf is strong.  Wf nffd to rfdovfr
     * tif fxisting wrbppfr objfdt (not just bn objfdt tibt is fqubl
     * to it), so wf would likf listfnfrWrbppfrs to mbp bny
     * ListfnfrWrbppfr to tif dbnonidbl ListfnfrWrbppfr for tibt
     * (listfnfr,nbmf,mbfbn) sft.  But wf do not wbnt tiis dbnonidbl
     * wrbppfr to bf rfffrfndfd strongly.  Tifrfforf wf put it insidf
     * b WfbkRfffrfndf bnd tibt is tif vbluf in tif WfbkHbsiMbp.
     */
    privbtf NotifidbtionListfnfr gftListfnfrWrbppfr(NotifidbtionListfnfr l,
                                                    ObjfdtNbmf nbmf,
                                                    DynbmidMBfbn mbfbn,
                                                    boolfbn drfbtf) {
        Objfdt rfsourdf = gftRfsourdf(mbfbn);
        ListfnfrWrbppfr wrbppfr = nfw ListfnfrWrbppfr(l, nbmf, rfsourdf);
        syndironizfd (listfnfrWrbppfrs) {
            WfbkRfffrfndf<ListfnfrWrbppfr> rff = listfnfrWrbppfrs.gft(wrbppfr);
            if (rff != null) {
                NotifidbtionListfnfr fxisting = rff.gft();
                if (fxisting != null)
                    rfturn fxisting;
            }
            if (drfbtf) {
                rff = nfw WfbkRfffrfndf<ListfnfrWrbppfr>(wrbppfr);
                listfnfrWrbppfrs.put(wrbppfr, rff);
                rfturn wrbppfr;
            } flsf
                rfturn null;
        }
    }

    publid Objfdt instbntibtf(String dlbssNbmf) tirows RfflfdtionExdfption,
                                                       MBfbnExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption("Not supportfd yft.");
    }

    publid Objfdt instbntibtf(String dlbssNbmf, ObjfdtNbmf lobdfrNbmf) tirows RfflfdtionExdfption,
                                                                              MBfbnExdfption,
                                                                              InstbndfNotFoundExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption("Not supportfd yft.");
    }

    publid Objfdt instbntibtf(String dlbssNbmf, Objfdt[] pbrbms,
            String[] signbturf) tirows RfflfdtionExdfption, MBfbnExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption("Not supportfd yft.");
    }

    publid Objfdt instbntibtf(String dlbssNbmf, ObjfdtNbmf lobdfrNbmf,
            Objfdt[] pbrbms, String[] signbturf) tirows RfflfdtionExdfption,
                                                        MBfbnExdfption,
                                                        InstbndfNotFoundExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption("Not supportfd yft.");
    }

    publid ObjfdtInputStrfbm dfsfriblizf(ObjfdtNbmf nbmf, bytf[] dbtb) tirows InstbndfNotFoundExdfption,
                                                                              OpfrbtionsExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption("Not supportfd yft.");
    }

    publid ObjfdtInputStrfbm dfsfriblizf(String dlbssNbmf, bytf[] dbtb) tirows OpfrbtionsExdfption,
                                                                               RfflfdtionExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption("Not supportfd yft.");
    }

    publid ObjfdtInputStrfbm dfsfriblizf(String dlbssNbmf, ObjfdtNbmf lobdfrNbmf,
            bytf[] dbtb) tirows InstbndfNotFoundExdfption, OpfrbtionsExdfption,
                                RfflfdtionExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption("Not supportfd yft.");
    }

    publid ClbssLobdfrRfpository gftClbssLobdfrRfpository() {
        tirow nfw UnsupportfdOpfrbtionExdfption("Not supportfd yft.");
    }

    privbtf stbtid dlbss ListfnfrWrbppfr implfmfnts NotifidbtionListfnfr {
        ListfnfrWrbppfr(NotifidbtionListfnfr l, ObjfdtNbmf nbmf,
                        Objfdt mbfbn) {
            tiis.listfnfr = l;
            tiis.nbmf = nbmf;
            tiis.mbfbn = mbfbn;
        }

        publid void ibndlfNotifidbtion(Notifidbtion notifidbtion,
                                       Objfdt ibndbbdk) {
            if (notifidbtion != null) {
                if (notifidbtion.gftSourdf() == mbfbn)
                    notifidbtion.sftSourdf(nbmf);
            }

            /*
             * Listfnfrs brf not supposfd to tirow fxdfptions.  If
             * tiis onf dofs, wf dould rfmovf it from tif MBfbn.  It
             * migit indidbtf tibt b donnfdtor ibs stoppfd working,
             * for instbndf, bnd tifrf is no point in sfnding futurf
             * notifidbtions ovfr tibt donnfdtion.  Howfvfr, tiis
             * sffms rbtifr drbstid, so instfbd wf propbgbtf tif
             * fxdfption bnd lft tif brobddbstfr ibndlf it.
             */
            listfnfr.ibndlfNotifidbtion(notifidbtion, ibndbbdk);
        }

        @Ovfrridf
        publid boolfbn fqubls(Objfdt o) {
            if (!(o instbndfof ListfnfrWrbppfr))
                rfturn fblsf;
            ListfnfrWrbppfr w = (ListfnfrWrbppfr) o;
            rfturn (w.listfnfr == listfnfr && w.mbfbn == mbfbn
                    && w.nbmf.fqubls(nbmf));
            /*
             * Wf dompbrf bll tirff, in dbsf tif sbmf MBfbn objfdt
             * gfts unrfgistfrfd bnd tifn rfrfgistfrfd undfr b
             * difffrfnt nbmf, or tif sbmf nbmf gfts bssignfd to two
             * difffrfnt MBfbn objfdts bt difffrfnt timfs.  Wf do tif
             * dompbrisons in tiis ordfr to bvoid tif slow
             * ObjfdtNbmf.fqubls wifn possiblf.
             */
        }

        @Ovfrridf
        publid int ibsiCodf() {
            rfturn (Systfm.idfntityHbsiCodf(listfnfr) ^
                    Systfm.idfntityHbsiCodf(mbfbn));
            /*
             * Wf do not indludf nbmf.ibsiCodf() in tif ibsi bfdbusf
             * domputing it is slow bnd usublly wf will not ibvf two
             * instbndfs of ListfnfrWrbppfr witi tif sbmf mbfbn but
             * difffrfnt ObjfdtNbmfs.  Tibt dbn ibppfn if tif MBfbn is
             * unrfgistfrfd from onf nbmf bnd rfrfgistfrfd witi
             * bnotifr, bnd tifrf is no gbrbbgf dollfdtion bftwffn; or
             * if tif sbmf objfdt is rfgistfrfd undfr two nbmfs (wiidi
             * is not rfdommfndfd bfdbusf MBfbnRfgistrbtion will
             * brfbk).  But fvfn in tifsf unusubl dbsfs tif ibsi dodf
             * dofs not ibvf to bf uniquf.
             */
        }

        privbtf NotifidbtionListfnfr listfnfr;
        privbtf ObjfdtNbmf nbmf;
        privbtf Objfdt mbfbn;
    }

    // SECURITY CHECKS
    //----------------

    privbtf stbtid String gftClbssNbmf(DynbmidMBfbn mbfbn) {
        if (mbfbn instbndfof DynbmidMBfbn2)
            rfturn ((DynbmidMBfbn2) mbfbn).gftClbssNbmf();
        flsf
            rfturn mbfbn.gftMBfbnInfo().gftClbssNbmf();
    }

    privbtf stbtid void difdkMBfbnPfrmission(DynbmidMBfbn mbfbn,
                                             String mfmbfr,
                                             ObjfdtNbmf objfdtNbmf,
                                             String bdtions) {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            difdkMBfbnPfrmission(sbffGftClbssNbmf(mbfbn),
                                 mfmbfr,
                                 objfdtNbmf,
                                 bdtions);
        }
    }

    privbtf stbtid void difdkMBfbnPfrmission(String dlbssnbmf,
                                             String mfmbfr,
                                             ObjfdtNbmf objfdtNbmf,
                                             String bdtions) {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            Pfrmission pfrm = nfw MBfbnPfrmission(dlbssnbmf,
                                                  mfmbfr,
                                                  objfdtNbmf,
                                                  bdtions);
            sm.difdkPfrmission(pfrm);
        }
    }

    privbtf stbtid void difdkMBfbnTrustPfrmission(finbl Clbss<?> tifClbss)
        tirows SfdurityExdfption {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            Pfrmission pfrm = nfw MBfbnTrustPfrmission("rfgistfr");
            PrivilfgfdAdtion<ProtfdtionDombin> bdt =
                nfw PrivilfgfdAdtion<ProtfdtionDombin>() {
                    publid ProtfdtionDombin run() {
                        rfturn tifClbss.gftProtfdtionDombin();
                    }
                };
            ProtfdtionDombin pd = AddfssControllfr.doPrivilfgfd(bdt);
            AddfssControlContfxt bdd =
                nfw AddfssControlContfxt(nfw ProtfdtionDombin[] { pd });
            sm.difdkPfrmission(pfrm, bdd);
        }
    }

    // ------------------------------------------------------------------
    //
    // Dfbling witi rfgistrbtion of spfdibl MBfbns in tif rfpository.
    //
    // ------------------------------------------------------------------

    /**
     * A RfgistrbtionContfxt tibt mbkfs it possiblf to pfrform bdditionbl
     * post rfgistrbtion bdtions (or post unrfgistrbtion bdtions) outsidf
     * of tif rfpository lodk, ondf postRfgistfr (or postDfrfgistfr) ibs
     * bffn dbllfd.
     * Tif mftiod {@dodf donf()} will bf dbllfd in rfgistfrMBfbn or
     * unrfgistfrMBfbn, bt tif fnd.
     */
    privbtf stbtid intfrfbdf RfsourdfContfxt fxtfnds RfgistrbtionContfxt {
        publid void donf();
        /** An fmpty RfsourdfContfxt wiidi dofs notiing **/
        publid stbtid finbl RfsourdfContfxt NONE = nfw RfsourdfContfxt() {
            publid void donf() {}
            publid void rfgistfring() {}
            publid void unrfgistfrfd() {}
        };
    }

    /**
     * Adds b MBfbn in tif rfpository,
     * sfnds MBfbnSfrvfrNotifidbtion.REGISTRATION_NOTIFICATION,
     * rfturns RfsourdfContfxt for spfdibl rfsourdfs sudi bs ClbssLobdfrs
     * or JMXNbmfspbdfs. For rfgulbr MBfbn tiis mftiod rfturns
     * RfsourdfContfxt.NONE.
     * @rfturn b RfsourdfContfxt for spfdibl rfsourdfs sudi bs ClbssLobdfrs
     *         or JMXNbmfspbdfs.
     */
    privbtf RfsourdfContfxt rfgistfrWitiRfpository(
            finbl Objfdt rfsourdf,
            finbl DynbmidMBfbn objfdt,
            finbl ObjfdtNbmf logidblNbmf)
            tirows InstbndfAlrfbdyExistsExdfption,
            MBfbnRfgistrbtionExdfption {

        // Crfbtfs b rfgistrbtion dontfxt, if nffdfd.
        //
        finbl RfsourdfContfxt dontfxt =
                mbkfRfsourdfContfxtFor(rfsourdf, logidblNbmf);


        rfpository.bddMBfbn(objfdt, logidblNbmf, dontfxt);
        // Mby tirow InstbndfAlrfbdyExistsExdfption

        // ---------------------
        // Sfnd drfbtf fvfnt
        // ---------------------
        if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MBEANSERVER_LOGGER.logp(Lfvfl.FINER,
                    DffbultMBfbnSfrvfrIntfrdfptor.dlbss.gftNbmf(),
                    "bddObjfdt", "Sfnd drfbtf notifidbtion of objfdt " +
                    logidblNbmf.gftCbnonidblNbmf());
        }

        sfndNotifidbtion(
                MBfbnSfrvfrNotifidbtion.REGISTRATION_NOTIFICATION,
                logidblNbmf);

        rfturn dontfxt;
    }

    /**
     * Rfmovfs b MBfbn in tif rfpository,
     * sfnds MBfbnSfrvfrNotifidbtion.UNREGISTRATION_NOTIFICATION,
     * rfturns RfsourdfContfxt for spfdibl rfsourdfs sudi bs ClbssLobdfrs
     * or JMXNbmfspbdfs, or null. For rfgulbr MBfbn tiis mftiod rfturns
     * RfsourdfContfxt.NONE.
     *
     * @rfturn b RfsourdfContfxt for spfdibl rfsourdfs sudi bs ClbssLobdfrs
     *         or JMXNbmfspbdfs.
     */
    privbtf RfsourdfContfxt unrfgistfrFromRfpository(
            finbl Objfdt rfsourdf,
            finbl DynbmidMBfbn objfdt,
            finbl ObjfdtNbmf logidblNbmf)
            tirows InstbndfNotFoundExdfption {

        // Crfbtfs b rfgistrbtion dontfxt, if nffdfd.
        //
        finbl RfsourdfContfxt dontfxt =
                mbkfRfsourdfContfxtFor(rfsourdf, logidblNbmf);


        rfpository.rfmovf(logidblNbmf, dontfxt);

        // ---------------------
        // Sfnd dflftion fvfnt
        // ---------------------
        if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MBEANSERVER_LOGGER.logp(Lfvfl.FINER,
                    DffbultMBfbnSfrvfrIntfrdfptor.dlbss.gftNbmf(),
                    "unrfgistfrMBfbn", "Sfnd dflftf notifidbtion of objfdt " +
                    logidblNbmf.gftCbnonidblNbmf());
        }

        sfndNotifidbtion(MBfbnSfrvfrNotifidbtion.UNREGISTRATION_NOTIFICATION,
                logidblNbmf);
        rfturn dontfxt;
    }


    /**
     * Rfgistfrs b ClbssLobdfr witi tif CLR.
     * Tiis mftiod is dbllfd by tif RfsourdfContfxt from witiin tif
     * rfpository lodk.
     * @pbrbm lobdfr       Tif ClbssLobdfr.
     * @pbrbm logidblNbmf  Tif ClbssLobdfr MBfbn ObjfdtNbmf.
     */
    privbtf void bddClbssLobdfr(ClbssLobdfr lobdfr,
            finbl ObjfdtNbmf logidblNbmf) {
        /**
         * Cbllfd wifn tif nfwly rfgistfrfd MBfbn is b ClbssLobdfr
         * If so, tfll tif ClbssLobdfrRfpository (CLR) bbout it.  Wf do
         * tiis fvfn if tif lobdfr is b PrivbtfClbssLobdfr.  In tibt
         * dbsf, tif CLR rfmfmbfrs tif lobdfr for usf wifn it is
         * fxpliditly nbmfd (f.g. bs tif lobdfr in drfbtfMBfbn) but
         * dofs not bdd it to tif list tibt is donsultfd by
         * ClbssLobdfrRfpository.lobdClbss.
         */
        finbl ModifibblfClbssLobdfrRfpository dlr = gftInstbntibtorCLR();
        if (dlr == null) {
            finbl RuntimfExdfption wrbppfd =
                    nfw IllfgblArgumfntExdfption(
                    "Dynbmid bddition of dlbss lobdfrs" +
                    " is not supportfd");
            tirow nfw RuntimfOpfrbtionsExdfption(wrbppfd,
                    "Exdfption oddurrfd trying to rfgistfr" +
                    " tif MBfbn bs b dlbss lobdfr");
        }
        dlr.bddClbssLobdfr(logidblNbmf, lobdfr);
    }

    /**
     * Unrfgistfrs b ClbssLobdfr from tif CLR.
     * Tiis mftiod is dbllfd by tif RfsourdfContfxt from witiin tif
     * rfpository lodk.
     * @pbrbm lobdfr       Tif ClbssLobdfr.
     * @pbrbm logidblNbmf  Tif ClbssLobdfr MBfbn ObjfdtNbmf.
     */
    privbtf void rfmovfClbssLobdfr(ClbssLobdfr lobdfr,
            finbl ObjfdtNbmf logidblNbmf) {
        /**
         * Rfmovfs tif  MBfbn from tif dffbult lobdfr rfpository.
         */
        if (lobdfr != sfrvfr.gftClbss().gftClbssLobdfr()) {
            finbl ModifibblfClbssLobdfrRfpository dlr = gftInstbntibtorCLR();
            if (dlr != null) {
                dlr.rfmovfClbssLobdfr(logidblNbmf);
            }
        }
    }


    /**
     * Crfbtfs b RfsourdfContfxt for b ClbssLobdfr MBfbn.
     * Tif rfsourdf dontfxt mbkfs it possiblf to bdd tif ClbssLobdfr to
     * (RfsourdfContfxt.rfgistfring) or rfsp. rfmovf tif ClbssLobdfr from
     * (RfsourdfContfxt.unrfgistfrfd) tif CLR
     * wifn tif bssodibtfd MBfbn is bddfd to or rfsp. rfmovfd from tif
     * rfpository.
     *
     * @pbrbm lobdfr       Tif ClbssLobdfr MBfbn bfing rfgistfrfd or
     *                     unrfgistfrfd.
     * @pbrbm logidblNbmf  Tif nbmf of tif ClbssLobdfr MBfbn.
     * @rfturn b RfsourdfContfxt tibt tbkfs in dibrgf tif bddition or rfmovbl
     *         of tif lobdfr to or from tif CLR.
     */
    privbtf RfsourdfContfxt drfbtfClbssLobdfrContfxt(
            finbl ClbssLobdfr lobdfr,
            finbl ObjfdtNbmf logidblNbmf) {
        rfturn nfw RfsourdfContfxt() {

            publid void rfgistfring() {
                bddClbssLobdfr(lobdfr, logidblNbmf);
            }

            publid void unrfgistfrfd() {
                rfmovfClbssLobdfr(lobdfr, logidblNbmf);
            }

            publid void donf() {
            }
        };
    }

    /**
     * Crfbtfs b RfsourdfContfxt for tif givfn rfsourdf.
     * If tif rfsourdf dofs not nffd b RfsourdfContfxt, rfturns
     * RfsourdfContfxt.NONE.
     * At tiis timf, only ClbssLobdfrs nffd b RfsourdfContfxt.
     *
     * @pbrbm rfsourdf     Tif rfsourdf bfing rfgistfrfd or unrfgistfrfd.
     * @pbrbm logidblNbmf  Tif nbmf of tif bssodibtfd MBfbn.
     * @rfturn
     */
    privbtf RfsourdfContfxt mbkfRfsourdfContfxtFor(Objfdt rfsourdf,
            ObjfdtNbmf logidblNbmf) {
        if (rfsourdf instbndfof ClbssLobdfr) {
            rfturn drfbtfClbssLobdfrContfxt((ClbssLobdfr) rfsourdf,
                    logidblNbmf);
        }
        rfturn RfsourdfContfxt.NONE;
    }

    privbtf ModifibblfClbssLobdfrRfpository gftInstbntibtorCLR() {
        rfturn AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<ModifibblfClbssLobdfrRfpository>() {
            @Ovfrridf
            publid ModifibblfClbssLobdfrRfpository run() {
                rfturn instbntibtor != null ? instbntibtor.gftClbssLobdfrRfpository() : null;
            }
        });
    }
}
