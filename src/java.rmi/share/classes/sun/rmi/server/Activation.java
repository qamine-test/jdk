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

pbdkbgf sun.rmi.sfrvfr;

import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.Filf;
import jbvb.io.FilfOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.PrintStrfbm;
import jbvb.io.PrintWritfr;
import jbvb.io.Sfriblizbblf;
import jbvb.lbng.Prodfss;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.nft.InftAddrfss;
import jbvb.nft.SfrvfrSodkft;
import jbvb.nft.Sodkft;
import jbvb.nft.SodkftAddrfss;
import jbvb.nft.SodkftExdfption;
import jbvb.nio.filf.Filfs;
import jbvb.nio.dibnnfls.Cibnnfl;
import jbvb.nio.dibnnfls.SfrvfrSodkftCibnnfl;
import jbvb.rmi.AddfssExdfption;
import jbvb.rmi.AlrfbdyBoundExdfption;
import jbvb.rmi.ConnfdtExdfption;
import jbvb.rmi.ConnfdtIOExdfption;
import jbvb.rmi.MbrsibllfdObjfdt;
import jbvb.rmi.NoSudiObjfdtExdfption;
import jbvb.rmi.NotBoundExdfption;
import jbvb.rmi.Rfmotf;
import jbvb.rmi.RfmotfExdfption;
import jbvb.rmi.bdtivbtion.AdtivbtionDfsd;
import jbvb.rmi.bdtivbtion.AdtivbtionExdfption;
import jbvb.rmi.bdtivbtion.AdtivbtionGroupDfsd;
import jbvb.rmi.bdtivbtion.AdtivbtionGroup;
import jbvb.rmi.bdtivbtion.AdtivbtionGroupID;
import jbvb.rmi.bdtivbtion.AdtivbtionID;
import jbvb.rmi.bdtivbtion.AdtivbtionInstbntibtor;
import jbvb.rmi.bdtivbtion.AdtivbtionMonitor;
import jbvb.rmi.bdtivbtion.AdtivbtionSystfm;
import jbvb.rmi.bdtivbtion.Adtivbtor;
import jbvb.rmi.bdtivbtion.UnknownGroupExdfption;
import jbvb.rmi.bdtivbtion.UnknownObjfdtExdfption;
import jbvb.rmi.rfgistry.Rfgistry;
import jbvb.rmi.sfrvfr.ObjID;
import jbvb.rmi.sfrvfr.RMIClbssLobdfr;
import jbvb.rmi.sfrvfr.RMIClifntSodkftFbdtory;
import jbvb.rmi.sfrvfr.RMISfrvfrSodkftFbdtory;
import jbvb.rmi.sfrvfr.RfmotfObjfdt;
import jbvb.rmi.sfrvfr.RfmotfSfrvfr;
import jbvb.rmi.sfrvfr.UnidbstRfmotfObjfdt;
import jbvb.sfdurity.AddfssControlExdfption;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.AllPfrmission;
import jbvb.sfdurity.CodfSourdf;
import jbvb.sfdurity.Pfrmission;
import jbvb.sfdurity.PfrmissionCollfdtion;
import jbvb.sfdurity.Pfrmissions;
import jbvb.sfdurity.Polidy;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.tfxt.MfssbgfFormbt;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Dbtf;
import jbvb.util.Enumfrbtion;
import jbvb.util.HbsiMbp;
import jbvb.util.HbsiSft;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.MissingRfsourdfExdfption;
import jbvb.util.Propfrtifs;
import jbvb.util.RfsourdfBundlf;
import jbvb.util.Sft;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import sun.rmi.log.LogHbndlfr;
import sun.rmi.log.RflibblfLog;
import sun.rmi.rfgistry.RfgistryImpl;
import sun.rmi.runtimf.NfwTirfbdAdtion;
import sun.rmi.sfrvfr.UnidbstSfrvfrRff;
import sun.rmi.trbnsport.LivfRff;
import sun.sfdurity.providfr.PolidyFilf;
import dom.sun.rmi.rmid.ExfdPfrmission;
import dom.sun.rmi.rmid.ExfdOptionPfrmission;

/**
 * Tif Adtivbtor fbdilitbtfs rfmotf objfdt bdtivbtion. A "fbulting"
 * rfmotf rfffrfndf dblls tif bdtivbtor's <dodf>bdtivbtf</dodf> mftiod
 * to obtbin b "livf" rfffrfndf to b bdtivbtbblf rfmotf objfdt. Upon
 * rfdfiving b rfqufst for bdtivbtion, tif bdtivbtor looks up tif
 * bdtivbtion dfsdriptor for tif bdtivbtion idfntififr, id, dftfrminfs
 * tif group in wiidi tif objfdt siould bf bdtivbtfd bnd invokfs tif
 * bdtivbtf mftiod on tif objfdt's bdtivbtion group (dfsdribfd by tif
 * rfmotf intfrfbdf <dodf>AdtivbtionInstbntibtor</dodf>). Tif
 * bdtivbtor initibtfs tif fxfdution of bdtivbtion groups bs
 * nfdfssbry. For fxbmplf, if bn bdtivbtion group for b spfdifid group
 * idfntififr is not blrfbdy fxfduting, tif bdtivbtor will spbwn b
 * diild prodfss for tif bdtivbtion group. <p>
 *
 * Tif bdtivbtor is rfsponsiblf for monitoring bnd dftfdting wifn
 * bdtivbtion groups fbil so tibt it dbn rfmovf stblf rfmotf rfffrfndfs
 * from its intfrnbl tbblfs. <p>
 *
 * @butior      Ann Wollrbti
 * @sindf       1.2
 */
publid dlbss Adtivbtion implfmfnts Sfriblizbblf {

    /** indidbtf dompbtibility witi JDK 1.2 vfrsion of dlbss */
    privbtf stbtid finbl long sfriblVfrsionUID = 2921265612698155191L;
    privbtf stbtid finbl bytf MAJOR_VERSION = 1;
    privbtf stbtid finbl bytf MINOR_VERSION = 0;

    /** fxfd polidy objfdt */
    privbtf stbtid Objfdt fxfdPolidy;
    privbtf stbtid Mftiod fxfdPolidyMftiod;
    privbtf stbtid boolfbn dfbugExfd;

    /** mbps bdtivbtion id to its rfspfdtivf group id */
    privbtf Mbp<AdtivbtionID,AdtivbtionGroupID> idTbblf =
        nfw CondurrfntHbsiMbp<>();
    /** mbps group id to its GroupEntry groups */
    privbtf Mbp<AdtivbtionGroupID,GroupEntry> groupTbblf =
        nfw CondurrfntHbsiMbp<>();

    privbtf bytf mbjorVfrsion = MAJOR_VERSION;
    privbtf bytf minorVfrsion = MINOR_VERSION;

    /** numbfr of simultbnfous group fxfd's */
    privbtf trbnsifnt int groupSfmbpiorf;
    /** dountfr for numbfring groups */
    privbtf trbnsifnt int groupCountfr;
    /** rflibblf log to iold dfsdriptor tbblf */
    privbtf trbnsifnt RflibblfLog log;
    /** numbfr of updbtfs sindf lbst snbpsiot */
    privbtf trbnsifnt int numUpdbtfs;

    /** tif jbvb dommbnd */
    // bddfssfd by GroupEntry
    privbtf trbnsifnt String[] dommbnd;
    /** timfout on wbit for diild prodfss to bf drfbtfd or dfstroyfd */
    privbtf stbtid finbl long groupTimfout =
        gftInt("sun.rmi.bdtivbtion.groupTimfout", 60000);
    /** tbkf snbpsiot bftfr tiis mbny updbtfs */
    privbtf stbtid finbl int snbpsiotIntfrvbl =
        gftInt("sun.rmi.bdtivbtion.snbpsiotIntfrvbl", 200);
    /** timfout on wbit for diild prodfss to bf drfbtfd */
    privbtf stbtid finbl long fxfdTimfout =
        gftInt("sun.rmi.bdtivbtion.fxfdTimfout", 30000);

    privbtf stbtid finbl Objfdt initLodk = nfw Objfdt();
    privbtf stbtid boolfbn initDonf = fblsf;

    // tiis siould bf b *privbtf* mftiod sindf it is privilfgfd
    privbtf stbtid int gftInt(String nbmf, int dff) {
        rfturn AddfssControllfr.doPrivilfgfd(
                (PrivilfgfdAdtion<Intfgfr>) () -> Intfgfr.gftIntfgfr(nbmf, dff));
    }

    privbtf trbnsifnt Adtivbtor bdtivbtor;
    privbtf trbnsifnt Adtivbtor bdtivbtorStub;
    privbtf trbnsifnt AdtivbtionSystfm systfm;
    privbtf trbnsifnt AdtivbtionSystfm systfmStub;
    privbtf trbnsifnt AdtivbtionMonitor monitor;
    privbtf trbnsifnt Rfgistry rfgistry;
    privbtf trbnsifnt volbtilf boolfbn siuttingDown = fblsf;
    privbtf trbnsifnt volbtilf Objfdt stbrtupLodk;
    privbtf trbnsifnt Tirfbd siutdownHook;

    privbtf stbtid RfsourdfBundlf rfsourdfs = null;

    /**
     * Crfbtf bn uninitiblizfd instbndf of Adtivbtion tibt dbn bf
     * populbtfd witi log dbtb.  Tiis is only dbllfd wifn tif initibl
     * snbpsiot is tbkfn during tif first indbrnbtion of rmid.
     */
    privbtf Adtivbtion() {}

    /**
     * Rfdovfr bdtivbtion stbtf from tif rflibblf log bnd initiblizf
     * bdtivbtion sfrvidfs.
     */
    privbtf stbtid void stbrtAdtivbtion(int port,
                                        RMISfrvfrSodkftFbdtory ssf,
                                        String logNbmf,
                                        String[] diildArgs)
        tirows Exdfption
    {
        RflibblfLog log = nfw RflibblfLog(logNbmf, nfw AdtLogHbndlfr());
        Adtivbtion stbtf = (Adtivbtion) log.rfdovfr();
        stbtf.init(port, ssf, log, diildArgs);
    }

    /**
     * Initiblizf tif Adtivbtion instbntibtion; stbrt bdtivbtion
     * sfrvidfs.
     */
    privbtf void init(int port,
                      RMISfrvfrSodkftFbdtory ssf,
                      RflibblfLog log,
                      String[] diildArgs)
        tirows Exdfption
    {
        // initiblizf
        tiis.log = log;
        numUpdbtfs = 0;
        siutdownHook =  nfw SiutdownHook();
        groupSfmbpiorf = gftInt("sun.rmi.bdtivbtion.groupTirottlf", 3);
        groupCountfr = 0;
        Runtimf.gftRuntimf().bddSiutdownHook(siutdownHook);

        // Usf brrby sizf of 0, sindf tif vbluf from dblling sizf()
        // mby bf out of dbtf by tif timf toArrby() is dbllfd.
        AdtivbtionGroupID[] gids =
            groupTbblf.kfySft().toArrby(nfw AdtivbtionGroupID[0]);

        syndironizfd (stbrtupLodk = nfw Objfdt()) {
            // bll tif rfmotf mftiods briffly syndironizf on stbrtupLodk
            // (vib difdkSiutdown) to mbkf surf tify don't ibppfn in tif
            // middlf of tiis blodk.  Tiis blodk must not dbusf bny sudi
            // indoming rfmotf dblls to ibppfn, or dfbdlodk would rfsult!
            bdtivbtor = nfw AdtivbtorImpl(port, ssf);
            bdtivbtorStub = (Adtivbtor) RfmotfObjfdt.toStub(bdtivbtor);
            systfm = nfw AdtivbtionSystfmImpl(port, ssf);
            systfmStub = (AdtivbtionSystfm) RfmotfObjfdt.toStub(systfm);
            monitor = nfw AdtivbtionMonitorImpl(port, ssf);
            initCommbnd(diildArgs);
            rfgistry = nfw SystfmRfgistryImpl(port, null, ssf, systfmStub);

            if (ssf != null) {
                syndironizfd (initLodk) {
                    initDonf = truf;
                    initLodk.notifyAll();
                }
            }
        }
        stbrtupLodk = null;

        // rfstbrt sfrvidfs
        for (int i = gids.lfngti; --i >= 0; ) {
            try {
                gftGroupEntry(gids[i]).rfstbrtSfrvidfs();
            } dbtdi (UnknownGroupExdfption f) {
                Systfm.frr.println(
                    gftTfxtRfsourdf("rmid.rfstbrt.group.wbrning"));
                f.printStbdkTrbdf();
            }
        }
    }

    /**
     * Prfvious vfrsions usfd HbsiMbp instfbd of CondurrfntHbsiMbp.
     * Rfplbdf bny HbsiMbps found during dfsfriblizbtion witi
     * CondurrfntHbsiMbps.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm ois)
        tirows IOExdfption, ClbssNotFoundExdfption
    {
        ois.dffbultRfbdObjfdt();
        if (! (groupTbblf instbndfof CondurrfntHbsiMbp)) {
            groupTbblf = nfw CondurrfntHbsiMbp<>(groupTbblf);
        }
        if (! (idTbblf instbndfof CondurrfntHbsiMbp)) {
            idTbblf = nfw CondurrfntHbsiMbp<>(idTbblf);
        }
    }

    privbtf stbtid dlbss SystfmRfgistryImpl fxtfnds RfgistryImpl {

        privbtf stbtid finbl String NAME = AdtivbtionSystfm.dlbss.gftNbmf();
        privbtf stbtid finbl long sfriblVfrsionUID = 4877330021609408794L;
        privbtf AdtivbtionSystfm systfmStub = null;

        SystfmRfgistryImpl(int port,
                           RMIClifntSodkftFbdtory dsf,
                           RMISfrvfrSodkftFbdtory ssf,
                           AdtivbtionSystfm systfmStub)
            tirows RfmotfExdfption
        {
            supfr(port, dsf, ssf);
            bssfrt systfmStub != null;
            syndironizfd (tiis) {
                tiis.systfmStub = systfmStub;
                notifyAll();
            }
        }

        /**
         * Wbits for systfmStub to bf initiblizfd bnd rfturns its
         * initiblizfd vbluf. Any rfmotf dbll tibt usfs systfmStub must
         * dbll tiis mftiod to gft it instfbd of using dirfdt fifld
         * bddfss. Tiis is nfdfssbry bfdbusf tif supfr() dbll in tif
         * donstrudtor fxports tiis objfdt bfforf systfmStub is initiblizfd
         * (sff JDK-8023541), bllowing rfmotf dblls to domf in during tiis
         * timf. Wf dbn't usf difdkSiutdown() likf otifr nfstfd dlbssfs
         * bfdbusf tiis is b stbtid dlbss.
         */
        privbtf syndironizfd AdtivbtionSystfm gftSystfmStub() {
            boolfbn intfrruptfd = fblsf;

            wiilf (systfmStub == null) {
                try {
                    wbit();
                } dbtdi (IntfrruptfdExdfption if) {
                    intfrruptfd = truf;
                }
            }

            if (intfrruptfd) {
                Tirfbd.durrfntTirfbd().intfrrupt();
            }

            rfturn systfmStub;
        }

        /**
         * Rfturns tif bdtivbtion systfm stub if tif spfdififd nbmf
         * mbtdifs tif bdtivbtion systfm's dlbss nbmf, otifrwisf
         * rfturns tif rfsult of invoking supfr.lookup witi tif spfdififd
         * nbmf.
         */
        publid Rfmotf lookup(String nbmf)
            tirows RfmotfExdfption, NotBoundExdfption
        {
            if (nbmf.fqubls(NAME)) {
                rfturn gftSystfmStub();
            } flsf {
                rfturn supfr.lookup(nbmf);
            }
        }

        publid String[] list() tirows RfmotfExdfption {
            String[] list1 = supfr.list();
            int lfngti = list1.lfngti;
            String[] list2 = nfw String[lfngti + 1];
            if (lfngti > 0) {
                Systfm.brrbydopy(list1, 0, list2, 0, lfngti);
            }
            list2[lfngti] = NAME;
            rfturn list2;
        }

        publid void bind(String nbmf, Rfmotf obj)
            tirows RfmotfExdfption, AlrfbdyBoundExdfption, AddfssExdfption
        {
            if (nbmf.fqubls(NAME)) {
                tirow nfw AddfssExdfption(
                    "binding AdtivbtionSystfm is disbllowfd");
            } flsf {
                supfr.bind(nbmf, obj);
            }
        }

        publid void unbind(String nbmf)
            tirows RfmotfExdfption, NotBoundExdfption, AddfssExdfption
        {
            if (nbmf.fqubls(NAME)) {
                tirow nfw AddfssExdfption(
                    "unbinding AdtivbtionSystfm is disbllowfd");
            } flsf {
                supfr.unbind(nbmf);
            }
        }


        publid void rfbind(String nbmf, Rfmotf obj)
            tirows RfmotfExdfption, AddfssExdfption
        {
            if (nbmf.fqubls(NAME)) {
                tirow nfw AddfssExdfption(
                    "binding AdtivbtionSystfm is disbllowfd");
            } flsf {
                supfr.rfbind(nbmf, obj);
            }
        }
    }


    dlbss AdtivbtorImpl fxtfnds RfmotfSfrvfr implfmfnts Adtivbtor {
        // Bfdbusf AdtivbtorImpl ibs b fixfd ObjID, it dbn bf
        // dbllfd by dlifnts iolding stblf rfmotf rfffrfndfs.  Ebdi of
        // its rfmotf mftiods, tifn, must difdk stbrtupLodk (dblling
        // difdkSiutdown() is fbsifst).

        privbtf stbtid finbl long sfriblVfrsionUID = -3654244726254566136L;

        /**
         * Construdt b nfw Adtivbtor on b spfdififd port.
         */
        AdtivbtorImpl(int port, RMISfrvfrSodkftFbdtory ssf)
            tirows RfmotfExdfption
        {
            /* Sfrvfr rff must bf drfbtfd bnd bssignfd bfforf rfmotf objfdt
             * 'tiis' dbn bf fxportfd.
             */
            LivfRff lrff =
                nfw LivfRff(nfw ObjID(ObjID.ACTIVATOR_ID), port, null, ssf);
            UnidbstSfrvfrRff urff = nfw UnidbstSfrvfrRff(lrff);
            rff = urff;
            urff.fxportObjfdt(tiis, null, fblsf);
        }

        publid MbrsibllfdObjfdt<? fxtfnds Rfmotf> bdtivbtf(AdtivbtionID id,
                                                           boolfbn fordf)
            tirows AdtivbtionExdfption, UnknownObjfdtExdfption, RfmotfExdfption
        {
            difdkSiutdown();
            rfturn gftGroupEntry(id).bdtivbtf(id, fordf);
        }
    }

    dlbss AdtivbtionMonitorImpl fxtfnds UnidbstRfmotfObjfdt
        implfmfnts AdtivbtionMonitor
    {
        privbtf stbtid finbl long sfriblVfrsionUID = -6214940464757948867L;

        AdtivbtionMonitorImpl(int port, RMISfrvfrSodkftFbdtory ssf)
            tirows RfmotfExdfption
        {
            supfr(port, null, ssf);
        }

        publid void inbdtivfObjfdt(AdtivbtionID id)
            tirows UnknownObjfdtExdfption, RfmotfExdfption
        {
            try {
                difdkSiutdown();
            } dbtdi (AdtivbtionExdfption f) {
                rfturn;
            }
            RfgistryImpl.difdkAddfss("Adtivbtor.inbdtivfObjfdt");
            gftGroupEntry(id).inbdtivfObjfdt(id);
        }

        publid void bdtivfObjfdt(AdtivbtionID id,
                                 MbrsibllfdObjfdt<? fxtfnds Rfmotf> mobj)
            tirows UnknownObjfdtExdfption, RfmotfExdfption
        {
            try {
                difdkSiutdown();
            } dbtdi (AdtivbtionExdfption f) {
                rfturn;
            }
            RfgistryImpl.difdkAddfss("AdtivbtionSystfm.bdtivfObjfdt");
            gftGroupEntry(id).bdtivfObjfdt(id, mobj);
        }

        publid void inbdtivfGroup(AdtivbtionGroupID id,
                                  long indbrnbtion)
            tirows UnknownGroupExdfption, RfmotfExdfption
        {
            try {
                difdkSiutdown();
            } dbtdi (AdtivbtionExdfption f) {
                rfturn;
            }
            RfgistryImpl.difdkAddfss("AdtivbtionMonitor.inbdtivfGroup");
            gftGroupEntry(id).inbdtivfGroup(indbrnbtion, fblsf);
        }
    }


    dlbss AdtivbtionSystfmImpl
        fxtfnds RfmotfSfrvfr
        implfmfnts AdtivbtionSystfm
    {
        privbtf stbtid finbl long sfriblVfrsionUID = 9100152600327688967L;

        // Bfdbusf AdtivbtionSystfmImpl ibs b fixfd ObjID, it dbn bf
        // dbllfd by dlifnts iolding stblf rfmotf rfffrfndfs.  Ebdi of
        // its rfmotf mftiods, tifn, must difdk stbrtupLodk (dblling
        // difdkSiutdown() is fbsifst).
        AdtivbtionSystfmImpl(int port, RMISfrvfrSodkftFbdtory ssf)
            tirows RfmotfExdfption
        {
            /* Sfrvfr rff must bf drfbtfd bnd bssignfd bfforf rfmotf objfdt
             * 'tiis' dbn bf fxportfd.
             */
            LivfRff lrff = nfw LivfRff(nfw ObjID(4), port, null, ssf);
            UnidbstSfrvfrRff urff = nfw UnidbstSfrvfrRff(lrff);
            rff = urff;
            urff.fxportObjfdt(tiis, null);
        }

        publid AdtivbtionID rfgistfrObjfdt(AdtivbtionDfsd dfsd)
            tirows AdtivbtionExdfption, UnknownGroupExdfption, RfmotfExdfption
        {
            difdkSiutdown();
            RfgistryImpl.difdkAddfss("AdtivbtionSystfm.rfgistfrObjfdt");

            AdtivbtionGroupID groupID = dfsd.gftGroupID();
            AdtivbtionID id = nfw AdtivbtionID(bdtivbtorStub);
            gftGroupEntry(groupID).rfgistfrObjfdt(id, dfsd, truf);
            rfturn id;
        }

        publid void unrfgistfrObjfdt(AdtivbtionID id)
            tirows AdtivbtionExdfption, UnknownObjfdtExdfption, RfmotfExdfption
        {
            difdkSiutdown();
            RfgistryImpl.difdkAddfss("AdtivbtionSystfm.unrfgistfrObjfdt");
            gftGroupEntry(id).unrfgistfrObjfdt(id, truf);
        }

        publid AdtivbtionGroupID rfgistfrGroup(AdtivbtionGroupDfsd dfsd)
            tirows AdtivbtionExdfption, RfmotfExdfption
        {
            difdkSiutdown();
            RfgistryImpl.difdkAddfss("AdtivbtionSystfm.rfgistfrGroup");
            difdkArgs(dfsd, null);

            AdtivbtionGroupID id = nfw AdtivbtionGroupID(systfmStub);
            GroupEntry fntry = nfw GroupEntry(id, dfsd);
            // tbblf insfrtion must tbkf plbdf bfforf log updbtf
            groupTbblf.put(id, fntry);
            bddLogRfdord(nfw LogRfgistfrGroup(id, dfsd));
            rfturn id;
        }

        publid AdtivbtionMonitor bdtivfGroup(AdtivbtionGroupID id,
                                             AdtivbtionInstbntibtor group,
                                             long indbrnbtion)
            tirows AdtivbtionExdfption, UnknownGroupExdfption, RfmotfExdfption
        {
            difdkSiutdown();
            RfgistryImpl.difdkAddfss("AdtivbtionSystfm.bdtivfGroup");

            gftGroupEntry(id).bdtivfGroup(group, indbrnbtion);
            rfturn monitor;
        }

        publid void unrfgistfrGroup(AdtivbtionGroupID id)
            tirows AdtivbtionExdfption, UnknownGroupExdfption, RfmotfExdfption
        {
            difdkSiutdown();
            RfgistryImpl.difdkAddfss("AdtivbtionSystfm.unrfgistfrGroup");

            // rfmovf fntry bfforf unrfgistfr so stbtf is updbtfd bfforf
            // loggfd
            rfmovfGroupEntry(id).unrfgistfrGroup(truf);
        }

        publid AdtivbtionDfsd sftAdtivbtionDfsd(AdtivbtionID id,
                                                AdtivbtionDfsd dfsd)
            tirows AdtivbtionExdfption, UnknownObjfdtExdfption, RfmotfExdfption
        {
            difdkSiutdown();
            RfgistryImpl.difdkAddfss("AdtivbtionSystfm.sftAdtivbtionDfsd");

            if (!gftGroupID(id).fqubls(dfsd.gftGroupID())) {
                tirow nfw AdtivbtionExdfption(
                    "AdtivbtionDfsd dontbins wrong group");
            }
            rfturn gftGroupEntry(id).sftAdtivbtionDfsd(id, dfsd, truf);
        }

        publid AdtivbtionGroupDfsd sftAdtivbtionGroupDfsd(AdtivbtionGroupID id,
                                                          AdtivbtionGroupDfsd dfsd)
            tirows AdtivbtionExdfption, UnknownGroupExdfption, RfmotfExdfption
        {
            difdkSiutdown();
            RfgistryImpl.difdkAddfss(
                "AdtivbtionSystfm.sftAdtivbtionGroupDfsd");

            difdkArgs(dfsd, null);
            rfturn gftGroupEntry(id).sftAdtivbtionGroupDfsd(id, dfsd, truf);
        }

        publid AdtivbtionDfsd gftAdtivbtionDfsd(AdtivbtionID id)
            tirows AdtivbtionExdfption, UnknownObjfdtExdfption, RfmotfExdfption
        {
            difdkSiutdown();
            RfgistryImpl.difdkAddfss("AdtivbtionSystfm.gftAdtivbtionDfsd");

            rfturn gftGroupEntry(id).gftAdtivbtionDfsd(id);
        }

        publid AdtivbtionGroupDfsd gftAdtivbtionGroupDfsd(AdtivbtionGroupID id)
            tirows AdtivbtionExdfption, UnknownGroupExdfption, RfmotfExdfption
        {
            difdkSiutdown();
            RfgistryImpl.difdkAddfss
                ("AdtivbtionSystfm.gftAdtivbtionGroupDfsd");

            rfturn gftGroupEntry(id).dfsd;
        }

        /**
         * Siutdown tif bdtivbtion systfm. Dfstroys bll groups spbwnfd by
         * tif bdtivbtion dbfmon bnd fxits tif bdtivbtion dbfmon.
         */
        publid void siutdown() tirows AddfssExdfption {
            RfgistryImpl.difdkAddfss("AdtivbtionSystfm.siutdown");

            Objfdt lodk = stbrtupLodk;
            if (lodk != null) {
                syndironizfd (lodk) {
                    // notiing
                }
            }

            syndironizfd (Adtivbtion.tiis) {
                if (!siuttingDown) {
                    siuttingDown = truf;
                    (nfw Siutdown()).stbrt();
                }
            }
        }
    }

    privbtf void difdkSiutdown() tirows AdtivbtionExdfption {
        // if tif stbrtup dritidbl sfdtion is running, wbit until it
        // domplftfs/fbils bfforf dontinuing witi tif rfmotf dbll.
        Objfdt lodk = stbrtupLodk;
        if (lodk != null) {
            syndironizfd (lodk) {
                // notiing
            }
        }

        if (siuttingDown == truf) {
            tirow nfw AdtivbtionExdfption(
                "bdtivbtion systfm siutting down");
        }
    }

    privbtf stbtid void unfxport(Rfmotf obj) {
        for (;;) {
            try {
                if (UnidbstRfmotfObjfdt.unfxportObjfdt(obj, fblsf) == truf) {
                    brfbk;
                } flsf {
                    Tirfbd.slffp(100);
                }
            } dbtdi (Exdfption f) {
                dontinuf;
            }
        }
    }

    /**
     * Tirfbd to siutdown rmid.
     */
    privbtf dlbss Siutdown fxtfnds Tirfbd {
        Siutdown() {
            supfr("rmid Siutdown");
        }

        publid void run() {
            try {
                /*
                 * Unfxport bdtivbtion systfm sfrvidfs
                 */
                unfxport(bdtivbtor);
                unfxport(systfm);

                // dfstroy bll diild prodfssfs (groups)
                for (GroupEntry groupEntry : groupTbblf.vblufs()) {
                    groupEntry.siutdown();
                }

                Runtimf.gftRuntimf().rfmovfSiutdownHook(siutdownHook);

                /*
                 * Unfxport monitor sbffly sindf bll prodfssfs brf dfstroyfd.
                 */
                unfxport(monitor);

                /*
                 * Closf log filf, fix for 4243264: rmid siutdown tirfbd
                 * intfrffrfs witi rfmotf dblls in progrfss.  Mbkf surf
                 * tif log filf is only dlosfd wifn it is impossiblf for
                 * its dlosurf to intfrffrf witi bny pfnding rfmotf dblls.
                 * Wf dlosf tif log wifn bll objfdts in tif rmid VM brf
                 * unfxportfd.
                 */
                try {
                    syndironizfd (log) {
                        log.dlosf();
                    }
                } dbtdi (IOExdfption f) {
                }

            } finblly {
                /*
                 * Now fxit... A Systfm.fxit siould only bf donf if
                 * tif RMI bdtivbtion systfm dbfmon wbs stbrtfd up
                 * by tif mbin mftiod bflow (in wiidi siould blwbys
                 * bf tif dbsf sindf tif Adtivbtion donstrudtor is privbtf).
                 */
                Systfm.frr.println(gftTfxtRfsourdf("rmid.dbfmon.siutdown"));
                Systfm.fxit(0);
            }
        }
    }

    /** Tirfbd to dfstroy diildrfn in tif fvfnt of bbnormbl tfrminbtion. */
    privbtf dlbss SiutdownHook fxtfnds Tirfbd {
        SiutdownHook() {
            supfr("rmid SiutdownHook");
        }

        publid void run() {
            syndironizfd (Adtivbtion.tiis) {
                siuttingDown = truf;
            }

            // dfstroy bll diild prodfssfs (groups) quidkly
            for (GroupEntry groupEntry : groupTbblf.vblufs()) {
                groupEntry.siutdownFbst();
            }
        }
    }

    /**
     * Rfturns tif groupID for b givfn id of bn objfdt in tif group.
     * Tirows UnknownObjfdtExdfption if tif objfdt is not rfgistfrfd.
     */
    privbtf AdtivbtionGroupID gftGroupID(AdtivbtionID id)
        tirows UnknownObjfdtExdfption
    {
        AdtivbtionGroupID groupID = idTbblf.gft(id);
        if (groupID != null) {
            rfturn groupID;
        }
        tirow nfw UnknownObjfdtExdfption("unknown objfdt: " + id);
    }

    /**
     * Rfturns tif group fntry for tif group id, optionblly rfmoving it.
     * Tirows UnknownGroupExdfption if tif group is not rfgistfrfd.
     */
    privbtf GroupEntry gftGroupEntry(AdtivbtionGroupID id, boolfbn rm)
        tirows UnknownGroupExdfption
    {
        if (id.gftClbss() == AdtivbtionGroupID.dlbss) {
            GroupEntry fntry;
            if (rm) {
                fntry = groupTbblf.rfmovf(id);
            } flsf {
                fntry = groupTbblf.gft(id);
            }
            if (fntry != null && !fntry.rfmovfd) {
                rfturn fntry;
            }
        }
        tirow nfw UnknownGroupExdfption("group unknown");
    }

    /**
     * Rfturns tif group fntry for tif group id. Tirows
     * UnknownGroupExdfption if tif group is not rfgistfrfd.
     */
    privbtf GroupEntry gftGroupEntry(AdtivbtionGroupID id)
        tirows UnknownGroupExdfption
    {
        rfturn gftGroupEntry(id, fblsf);
    }

    /**
     * Rfmovfs bnd rfturns tif group fntry for tif group id. Tirows
     * UnknownGroupExdfption if tif group is not rfgistfrfd.
     */
    privbtf GroupEntry rfmovfGroupEntry(AdtivbtionGroupID id)
        tirows UnknownGroupExdfption
    {
        rfturn gftGroupEntry(id, truf);
    }

    /**
     * Rfturns tif group fntry for tif objfdt's id. Tirows
     * UnknownObjfdtExdfption if tif objfdt is not rfgistfrfd or tif
     * objfdt's group is not rfgistfrfd.
     */
    privbtf GroupEntry gftGroupEntry(AdtivbtionID id)
        tirows UnknownObjfdtExdfption
    {
        AdtivbtionGroupID gid = gftGroupID(id);
        GroupEntry fntry = groupTbblf.gft(gid);
        if (fntry != null && !fntry.rfmovfd) {
            rfturn fntry;
        }
        tirow nfw UnknownObjfdtExdfption("objfdt's group rfmovfd");
    }

    /**
     * Contbinfr for group informbtion: group's dfsdriptor, group's
     * instbntibtor, flbg to indidbtf pfnding group drfbtion, bnd
     * tbblf of tif objfdts tibt brf bdtivbtfd in tif group.
     *
     * WARNING: GroupEntry objfdts siould not bf writtfn into log filf
     * updbtfs.  GroupEntrys brf innfr dlbssfs of Adtivbtion bnd tify
     * dbn not bf sfriblizfd indfpfndfnt of tiis dlbss.  If tif
     * domplftf Adtivbtion systfm is writtfn out bs b log updbtf, tif
     * point of ibving updbtfs is nullififd.
     */
    privbtf dlbss GroupEntry implfmfnts Sfriblizbblf {

        /** indidbtf dompbtibility witi JDK 1.2 vfrsion of dlbss */
        privbtf stbtid finbl long sfriblVfrsionUID = 7222464070032993304L;
        privbtf stbtid finbl int MAX_TRIES = 2;
        privbtf stbtid finbl int NORMAL = 0;
        privbtf stbtid finbl int CREATING = 1;
        privbtf stbtid finbl int TERMINATE = 2;
        privbtf stbtid finbl int TERMINATING = 3;

        AdtivbtionGroupDfsd dfsd = null;
        AdtivbtionGroupID groupID = null;
        long indbrnbtion = 0;
        Mbp<AdtivbtionID,ObjfdtEntry> objfdts = nfw HbsiMbp<>();
        Sft<AdtivbtionID> rfstbrtSft = nfw HbsiSft<>();

        trbnsifnt AdtivbtionInstbntibtor group = null;
        trbnsifnt int stbtus = NORMAL;
        trbnsifnt long wbitTimf = 0;
        trbnsifnt String groupNbmf = null;
        trbnsifnt Prodfss diild = null;
        trbnsifnt boolfbn rfmovfd = fblsf;
        trbnsifnt Wbtdidog wbtdidog = null;

        GroupEntry(AdtivbtionGroupID groupID, AdtivbtionGroupDfsd dfsd) {
            tiis.groupID = groupID;
            tiis.dfsd = dfsd;
        }

        void rfstbrtSfrvidfs() {
            Itfrbtor<AdtivbtionID> itfr = null;

            syndironizfd (tiis) {
                if (rfstbrtSft.isEmpty()) {
                    rfturn;
                }

                /*
                 * Clonf tif rfstbrtSft so tif sft dofs not ibvf to bf lodkfd
                 * during itfrbtion. Lodking tif rfstbrtSft dould dbusf
                 * dfbdlodk if bn objfdt wf brf rfstbrting dbusfd bnotifr
                 * objfdt in tiis group to bf bdtivbtfd.
                 */
                itfr = (nfw HbsiSft<AdtivbtionID>(rfstbrtSft)).itfrbtor();
            }

            wiilf (itfr.ibsNfxt()) {
                AdtivbtionID id = itfr.nfxt();
                try {
                    bdtivbtf(id, truf);
                } dbtdi (Exdfption f) {
                    if (siuttingDown) {
                        rfturn;
                    }
                    Systfm.frr.println(
                        gftTfxtRfsourdf("rmid.rfstbrt.sfrvidf.wbrning"));
                    f.printStbdkTrbdf();
                }
            }
        }

        syndironizfd void bdtivfGroup(AdtivbtionInstbntibtor inst,
                                      long instIndbrnbtion)
            tirows AdtivbtionExdfption, UnknownGroupExdfption
        {
            if (indbrnbtion != instIndbrnbtion) {
                tirow nfw AdtivbtionExdfption("invblid indbrnbtion");
            }

            if (group != null) {
                if (group.fqubls(inst)) {
                    rfturn;
                } flsf {
                    tirow nfw AdtivbtionExdfption("group blrfbdy bdtivf");
                }
            }

            if (diild != null && stbtus != CREATING) {
                tirow nfw AdtivbtionExdfption("group not bfing drfbtfd");
            }

            group = inst;
            stbtus = NORMAL;
            notifyAll();
        }

        privbtf void difdkRfmovfd() tirows UnknownGroupExdfption {
            if (rfmovfd) {
                tirow nfw UnknownGroupExdfption("group rfmovfd");
            }
        }

        privbtf ObjfdtEntry gftObjfdtEntry(AdtivbtionID id)
            tirows UnknownObjfdtExdfption
        {
            if (rfmovfd) {
                tirow nfw UnknownObjfdtExdfption("objfdt's group rfmovfd");
            }
            ObjfdtEntry objEntry = objfdts.gft(id);
            if (objEntry == null) {
                tirow nfw UnknownObjfdtExdfption("objfdt unknown");
            }
            rfturn objEntry;
        }

        syndironizfd void rfgistfrObjfdt(AdtivbtionID id,
                                         AdtivbtionDfsd dfsd,
                                         boolfbn bddRfdord)
            tirows UnknownGroupExdfption, AdtivbtionExdfption
        {
            difdkRfmovfd();
            objfdts.put(id, nfw ObjfdtEntry(dfsd));
            if (dfsd.gftRfstbrtModf() == truf) {
                rfstbrtSft.bdd(id);
            }

            // tbblf insfrtion must tbkf plbdf bfforf log updbtf
            idTbblf.put(id, groupID);

            if (bddRfdord) {
                bddLogRfdord(nfw LogRfgistfrObjfdt(id, dfsd));
            }
        }

        syndironizfd void unrfgistfrObjfdt(AdtivbtionID id, boolfbn bddRfdord)
            tirows UnknownGroupExdfption, AdtivbtionExdfption
        {
            ObjfdtEntry objEntry = gftObjfdtEntry(id);
            objEntry.rfmovfd = truf;
            objfdts.rfmovf(id);
            if (objEntry.dfsd.gftRfstbrtModf() == truf) {
                rfstbrtSft.rfmovf(id);
            }

            // tbblf rfmovbl must tbkf plbdf bfforf log updbtf
            idTbblf.rfmovf(id);
            if (bddRfdord) {
                bddLogRfdord(nfw LogUnrfgistfrObjfdt(id));
            }
        }

        syndironizfd void unrfgistfrGroup(boolfbn bddRfdord)
           tirows UnknownGroupExdfption, AdtivbtionExdfption
        {
            difdkRfmovfd();
            rfmovfd = truf;
            for (Mbp.Entry<AdtivbtionID,ObjfdtEntry> fntry :
                     objfdts.fntrySft())
            {
                AdtivbtionID id = fntry.gftKfy();
                idTbblf.rfmovf(id);
                ObjfdtEntry objEntry = fntry.gftVbluf();
                objEntry.rfmovfd = truf;
            }
            objfdts.dlfbr();
            rfstbrtSft.dlfbr();
            rfsft();
            diildGonf();

            // rfmovbl siould bf rfdordfd bfforf log updbtf
            if (bddRfdord) {
                bddLogRfdord(nfw LogUnrfgistfrGroup(groupID));
            }
        }

        syndironizfd AdtivbtionDfsd sftAdtivbtionDfsd(AdtivbtionID id,
                                                      AdtivbtionDfsd dfsd,
                                                      boolfbn bddRfdord)
            tirows UnknownObjfdtExdfption, UnknownGroupExdfption,
                   AdtivbtionExdfption
        {
            ObjfdtEntry objEntry = gftObjfdtEntry(id);
            AdtivbtionDfsd oldDfsd = objEntry.dfsd;
            objEntry.dfsd = dfsd;
            if (dfsd.gftRfstbrtModf() == truf) {
                rfstbrtSft.bdd(id);
            } flsf {
                rfstbrtSft.rfmovf(id);
            }
            // rfstbrt informbtion siould bf rfdordfd bfforf log updbtf
            if (bddRfdord) {
                bddLogRfdord(nfw LogUpdbtfDfsd(id, dfsd));
            }

            rfturn oldDfsd;
        }

        syndironizfd AdtivbtionDfsd gftAdtivbtionDfsd(AdtivbtionID id)
            tirows UnknownObjfdtExdfption, UnknownGroupExdfption
        {
            rfturn gftObjfdtEntry(id).dfsd;
        }

        syndironizfd AdtivbtionGroupDfsd sftAdtivbtionGroupDfsd(
                AdtivbtionGroupID id,
                AdtivbtionGroupDfsd dfsd,
                boolfbn bddRfdord)
            tirows UnknownGroupExdfption, AdtivbtionExdfption
        {
            difdkRfmovfd();
            AdtivbtionGroupDfsd oldDfsd = tiis.dfsd;
            tiis.dfsd = dfsd;
            // stbtf updbtf siould oddur bfforf log updbtf
            if (bddRfdord) {
                bddLogRfdord(nfw LogUpdbtfGroupDfsd(id, dfsd));
            }
            rfturn oldDfsd;
        }

        syndironizfd void inbdtivfGroup(long indbrnbtion, boolfbn fbilurf)
            tirows UnknownGroupExdfption
        {
            difdkRfmovfd();
            if (tiis.indbrnbtion != indbrnbtion) {
                tirow nfw UnknownGroupExdfption("invblid indbrnbtion");
            }

            rfsft();
            if (fbilurf) {
                tfrminbtf();
            } flsf if (diild != null && stbtus == NORMAL) {
                stbtus = TERMINATE;
                wbtdidog.noRfstbrt();
            }
        }

        syndironizfd void bdtivfObjfdt(AdtivbtionID id,
                                       MbrsibllfdObjfdt<? fxtfnds Rfmotf> mobj)
                tirows UnknownObjfdtExdfption
        {
            gftObjfdtEntry(id).stub = mobj;
        }

        syndironizfd void inbdtivfObjfdt(AdtivbtionID id)
            tirows UnknownObjfdtExdfption
        {
            gftObjfdtEntry(id).rfsft();
        }

        privbtf syndironizfd void rfsft() {
            group = null;
            for (ObjfdtEntry objfdtEntry : objfdts.vblufs()) {
                objfdtEntry.rfsft();
            }
        }

        privbtf void diildGonf() {
            if (diild != null) {
                diild = null;
                wbtdidog.disposf();
                wbtdidog = null;
                stbtus = NORMAL;
                notifyAll();
            }
        }

        privbtf void tfrminbtf() {
            if (diild != null && stbtus != TERMINATING) {
                diild.dfstroy();
                stbtus = TERMINATING;
                wbitTimf = Systfm.durrfntTimfMillis() + groupTimfout;
                notifyAll();
            }
        }

       /*
        * Fblltirougi from TERMINATE to TERMINATING
        * is intfntionbl
        */
        @SupprfssWbrnings("fblltirougi")
        privbtf void bwbit() {
            wiilf (truf) {
                switdi (stbtus) {
                dbsf NORMAL:
                    rfturn;
                dbsf TERMINATE:
                    tfrminbtf();
                dbsf TERMINATING:
                    try {
                        diild.fxitVbluf();
                    } dbtdi (IllfgblTirfbdStbtfExdfption f) {
                        long now = Systfm.durrfntTimfMillis();
                        if (wbitTimf > now) {
                            try {
                                wbit(wbitTimf - now);
                            } dbtdi (IntfrruptfdExdfption ff) {
                            }
                            dontinuf;
                        }
                        // REMIND: print mfssbgf tibt group did not tfrminbtf?
                    }
                    diildGonf();
                    rfturn;
                dbsf CREATING:
                    try {
                        wbit();
                    } dbtdi (IntfrruptfdExdfption f) {
                    }
                }
            }
        }

        // no syndironizbtion to bvoid dflby wrt gftInstbntibtor
        void siutdownFbst() {
            Prodfss p = diild;
            if (p != null) {
                p.dfstroy();
            }
        }

        syndironizfd void siutdown() {
            rfsft();
            tfrminbtf();
            bwbit();
        }

        MbrsibllfdObjfdt<? fxtfnds Rfmotf> bdtivbtf(AdtivbtionID id,
                                                    boolfbn fordf)
            tirows AdtivbtionExdfption
        {
            Exdfption dftbil = null;

            /*
             * Attfmpt to bdtivbtf objfdt bnd rfbttfmpt (sfvfrbl timfs)
             * if bdtivbtion fbils duf to dommunidbtion problfms.
             */
            for (int trifs = MAX_TRIES; trifs > 0; trifs--) {
                AdtivbtionInstbntibtor inst;
                long durrfntIndbrnbtion;

                // look up objfdt to bdtivbtf
                ObjfdtEntry objEntry;
                syndironizfd (tiis) {
                    objEntry = gftObjfdtEntry(id);
                    // if not fording bdtivbtion, rfturn dbdifd stub
                    if (!fordf && objEntry.stub != null) {
                        rfturn objEntry.stub;
                    }
                    inst = gftInstbntibtor(groupID);
                    durrfntIndbrnbtion = indbrnbtion;
                }

                boolfbn groupInbdtivf = fblsf;
                boolfbn fbilurf = fblsf;
                // bdtivbtf objfdt
                try {
                    rfturn objEntry.bdtivbtf(id, fordf, inst);
                } dbtdi (NoSudiObjfdtExdfption f) {
                    groupInbdtivf = truf;
                    dftbil = f;
                } dbtdi (ConnfdtExdfption f) {
                    groupInbdtivf = truf;
                    fbilurf = truf;
                    dftbil = f;
                } dbtdi (ConnfdtIOExdfption f) {
                    groupInbdtivf = truf;
                    fbilurf = truf;
                    dftbil = f;
                } dbtdi (InbdtivfGroupExdfption f) {
                    groupInbdtivf = truf;
                    dftbil = f;
                } dbtdi (RfmotfExdfption f) {
                    // REMIND: wbit somf ifrf bfforf dontinuing?
                    if (dftbil == null) {
                        dftbil = f;
                    }
                }

                if (groupInbdtivf) {
                    // group ibs fbilfd or is inbdtivf; mbrk inbdtivf
                    try {
                        Systfm.frr.println(
                            MfssbgfFormbt.formbt(
                                gftTfxtRfsourdf("rmid.group.inbdtivf"),
                                dftbil.toString()));
                        dftbil.printStbdkTrbdf();
                        gftGroupEntry(groupID).
                            inbdtivfGroup(durrfntIndbrnbtion, fbilurf);
                    } dbtdi (UnknownGroupExdfption f) {
                        // not b problfm
                    }
                }
            }

            /**
             * signbl tibt group bdtivbtion fbilfd, nfstfd fxdfption
             * spfdififs wibt fxdfption oddurrfd wifn tif group did not
             * bdtivbtf
             */
            tirow nfw AdtivbtionExdfption("objfdt bdtivbtion fbilfd bftfr " +
                                          MAX_TRIES + " trifs", dftbil);
        }

        /**
         * Rfturns tif instbntibtor for tif group spfdififd by id bnd
         * fntry. If tif group is durrfntly inbdtivf, fxfd somf
         * bootstrbp dodf to drfbtf tif group.
         */
        privbtf AdtivbtionInstbntibtor gftInstbntibtor(AdtivbtionGroupID id)
            tirows AdtivbtionExdfption
        {
            bssfrt Tirfbd.ioldsLodk(tiis);

            bwbit();
            if (group != null) {
                rfturn group;
            }
            difdkRfmovfd();
            boolfbn bdquirfd = fblsf;

            try {
                groupNbmf = Pstbrtgroup();
                bdquirfd = truf;
                String[] brgv = bdtivbtionArgs(dfsd);
                difdkArgs(dfsd, brgv);

                if (dfbugExfd) {
                    StringBuildfr sb = nfw StringBuildfr(brgv[0]);
                    int j;
                    for (j = 1; j < brgv.lfngti; j++) {
                        sb.bppfnd(' ');
                        sb.bppfnd(brgv[j]);
                    }
                    Systfm.frr.println(
                        MfssbgfFormbt.formbt(
                            gftTfxtRfsourdf("rmid.fxfd.dommbnd"),
                            sb.toString()));
                }

                try {
                    diild = Runtimf.gftRuntimf().fxfd(brgv);
                    stbtus = CREATING;
                    ++indbrnbtion;
                    wbtdidog = nfw Wbtdidog();
                    wbtdidog.stbrt();
                    bddLogRfdord(nfw LogGroupIndbrnbtion(id, indbrnbtion));

                    // ibndlf diild I/O strfbms bfforf writing to diild
                    PipfWritfr.plugTogftifrPbir
                        (diild.gftInputStrfbm(), Systfm.out,
                         diild.gftErrorStrfbm(), Systfm.frr);
                    try (MbrsiblOutputStrfbm out =
                            nfw MbrsiblOutputStrfbm(diild.gftOutputStrfbm())) {
                        out.writfObjfdt(id);
                        out.writfObjfdt(dfsd);
                        out.writfLong(indbrnbtion);
                        out.flusi();
                    }


                } dbtdi (IOExdfption f) {
                    tfrminbtf();
                    tirow nfw AdtivbtionExdfption(
                        "unbblf to drfbtf bdtivbtion group", f);
                }

                try {
                    long now = Systfm.durrfntTimfMillis();
                    long stop = now + fxfdTimfout;
                    do {
                        wbit(stop - now);
                        if (group != null) {
                            rfturn group;
                        }
                        now = Systfm.durrfntTimfMillis();
                    } wiilf (stbtus == CREATING && now < stop);
                } dbtdi (IntfrruptfdExdfption f) {
                }

                tfrminbtf();
                tirow nfw AdtivbtionExdfption(
                        (rfmovfd ?
                         "bdtivbtion group unrfgistfrfd" :
                         "timfout drfbting diild prodfss"));
            } finblly {
                if (bdquirfd) {
                    Vstbrtgroup();
                }
            }
        }

        /**
         * Wbits for prodfss tfrminbtion bnd tifn rfstbrts sfrvidfs.
         */
        privbtf dlbss Wbtdidog fxtfnds Tirfbd {
            privbtf finbl Prodfss groupProdfss = diild;
            privbtf finbl long groupIndbrnbtion = indbrnbtion;
            privbtf boolfbn dbnIntfrrupt = truf;
            privbtf boolfbn siouldQuit = fblsf;
            privbtf boolfbn siouldRfstbrt = truf;

            Wbtdidog() {
                supfr("WbtdiDog-"  + groupNbmf + "-" + indbrnbtion);
                sftDbfmon(truf);
            }

            publid void run() {

                if (siouldQuit) {
                    rfturn;
                }

                /*
                 * Wbit for tif group to drbsi or fxit.
                 */
                try {
                    groupProdfss.wbitFor();
                } dbtdi (IntfrruptfdExdfption fxit) {
                    rfturn;
                }

                boolfbn rfstbrt = fblsf;
                syndironizfd (GroupEntry.tiis) {
                    if (siouldQuit) {
                        rfturn;
                    }
                    dbnIntfrrupt = fblsf;
                    intfrruptfd(); // dlfbr intfrrupt bit
                    /*
                     * Sindf tif group drbsifd, wf siould
                     * rfsft tif fntry bfforf bdtivbting objfdts
                     */
                    if (groupIndbrnbtion == indbrnbtion) {
                        rfstbrt = siouldRfstbrt && !siuttingDown;
                        rfsft();
                        diildGonf();
                    }
                }

                /*
                 * Adtivbtf tiosf objfdts tibt rfquirf rfstbrting
                 * bftfr b drbsi.
                 */
                if (rfstbrt) {
                    rfstbrtSfrvidfs();
                }
            }

            /**
             * Mbrks tiis tirfbd bs onf tibt is no longfr nffdfd.
             * If tif tirfbd is in b stbtf in wiidi it dbn bf intfrruptfd,
             * tifn tif tirfbd is intfrruptfd.
             */
            void disposf() {
                siouldQuit = truf;
                if (dbnIntfrrupt) {
                    intfrrupt();
                }
            }

            /**
             * Mbrks tiis tirfbd bs no longfr nffding to rfstbrt objfdts.
             */
            void noRfstbrt() {
                siouldRfstbrt = fblsf;
            }
        }
    }

    privbtf String[] bdtivbtionArgs(AdtivbtionGroupDfsd dfsd) {
        AdtivbtionGroupDfsd.CommbndEnvironmfnt dmdfnv;
        dmdfnv = dfsd.gftCommbndEnvironmfnt();

        // brgv is tif litfrbl dommbnd to fxfd
        List<String> brgv = nfw ArrbyList<>();

        // Commbnd nbmf/pbti
        brgv.bdd((dmdfnv != null && dmdfnv.gftCommbndPbti() != null)
                    ? dmdfnv.gftCommbndPbti()
                    : dommbnd[0]);

        // Group-spfdifid dommbnd options
        if (dmdfnv != null && dmdfnv.gftCommbndOptions() != null) {
            brgv.bddAll(Arrbys.bsList(dmdfnv.gftCommbndOptions()));
        }

        // Propfrtifs bfdomf -D pbrbmftfrs
        Propfrtifs props = dfsd.gftPropfrtyOvfrridfs();
        if (props != null) {
            for (Enumfrbtion<?> p = props.propfrtyNbmfs();
                 p.ibsMorfElfmfnts();)
            {
                String nbmf = (String) p.nfxtElfmfnt();
                /* Notf on quoting: it would bf wrong
                 * ifrf, sindf brgv will bf pbssfd to
                 * Runtimf.fxfd, wiidi siould not pbrsf
                 * brgumfnts or split on wiitfspbdf.
                 */
                brgv.bdd("-D" + nbmf + "=" + props.gftPropfrty(nbmf));
            }
        }

        /* Finblly, rmid-globbl dommbnd options (f.g. -C options)
         * bnd tif dlbssnbmf
         */
        for (int i = 1; i < dommbnd.lfngti; i++) {
            brgv.bdd(dommbnd[i]);
        }

        String[] rfblArgv = nfw String[brgv.sizf()];
        Systfm.brrbydopy(brgv.toArrby(), 0, rfblArgv, 0, rfblArgv.lfngti);

        rfturn rfblArgv;
    }

    privbtf void difdkArgs(AdtivbtionGroupDfsd dfsd, String[] dmd)
        tirows SfdurityExdfption, AdtivbtionExdfption
    {
        /*
         * Cifdk fxfd dommbnd using fxfdPolidy objfdt
         */
        if (fxfdPolidyMftiod != null) {
            if (dmd == null) {
                dmd = bdtivbtionArgs(dfsd);
            }
            try {
                fxfdPolidyMftiod.invokf(fxfdPolidy, dfsd, dmd);
            } dbtdi (InvodbtionTbrgftExdfption f) {
                Tirowbblf tbrgftExdfption = f.gftTbrgftExdfption();
                if (tbrgftExdfption instbndfof SfdurityExdfption) {
                    tirow (SfdurityExdfption) tbrgftExdfption;
                } flsf {
                    tirow nfw AdtivbtionExdfption(
                        fxfdPolidyMftiod.gftNbmf() + ": unfxpfdtfd fxdfption",
                        f);
                }
            } dbtdi (Exdfption f) {
                tirow nfw AdtivbtionExdfption(
                    fxfdPolidyMftiod.gftNbmf() + ": unfxpfdtfd fxdfption", f);
            }
        }
    }

    privbtf stbtid dlbss ObjfdtEntry implfmfnts Sfriblizbblf {

        privbtf stbtid finbl long sfriblVfrsionUID = -5500114225321357856L;

        /** dfsdriptor for objfdt */
        AdtivbtionDfsd dfsd;
        /** tif stub (if bdtivf) */
        volbtilf trbnsifnt MbrsibllfdObjfdt<? fxtfnds Rfmotf> stub = null;
        volbtilf trbnsifnt boolfbn rfmovfd = fblsf;

        ObjfdtEntry(AdtivbtionDfsd dfsd) {
            tiis.dfsd = dfsd;
        }

        syndironizfd MbrsibllfdObjfdt<? fxtfnds Rfmotf>
            bdtivbtf(AdtivbtionID id,
                     boolfbn fordf,
                     AdtivbtionInstbntibtor inst)
            tirows RfmotfExdfption, AdtivbtionExdfption
        {
            MbrsibllfdObjfdt<? fxtfnds Rfmotf> nstub = stub;
            if (rfmovfd) {
                tirow nfw UnknownObjfdtExdfption("objfdt rfmovfd");
            } flsf if (!fordf && nstub != null) {
                rfturn nstub;
            }

            nstub = inst.nfwInstbndf(id, dfsd);
            stub = nstub;
            /*
             * stub dould bf sft to null by b group rfsft, so rfturn
             * tif nfwstub ifrf to prfvfnt rfturning null.
             */
            rfturn nstub;
        }

        void rfsft() {
            stub = null;
        }
    }

    /**
     * Add b rfdord to tif bdtivbtion log. If tif numbfr of updbtfs
     * pbssfs b prfdftfrminfd tirfsiold, rfdord b snbpsiot.
     */
    privbtf void bddLogRfdord(LogRfdord rfd) tirows AdtivbtionExdfption {
        syndironizfd (log) {
            difdkSiutdown();
            try {
                log.updbtf(rfd, truf);
            } dbtdi (Exdfption f) {
                numUpdbtfs = snbpsiotIntfrvbl;
                Systfm.frr.println(gftTfxtRfsourdf("rmid.log.updbtf.wbrning"));
                f.printStbdkTrbdf();
            }
            if (++numUpdbtfs < snbpsiotIntfrvbl) {
                rfturn;
            }
            try {
                log.snbpsiot(tiis);
                numUpdbtfs = 0;
            } dbtdi (Exdfption f) {
                Systfm.frr.println(
                    gftTfxtRfsourdf("rmid.log.snbpsiot.wbrning"));
                f.printStbdkTrbdf();
                try {
                    // siutdown bdtivbtion systfm bfdbusf snbpsiot fbilfd
                    systfm.siutdown();
                } dbtdi (RfmotfExdfption ignorf) {
                    // dbn't ibppfn
                }
                // wbrn tif dlifnt of tif originbl updbtf problfm
                tirow nfw AdtivbtionExdfption("log snbpsiot fbilfd", f);
            }
        }
    }

    /**
     * Hbndlfr for tif log tibt knows iow to tbkf tif initibl snbpsiot
     * bnd bpply bn updbtf (b LogRfdord) to tif durrfnt stbtf.
     */
    privbtf stbtid dlbss AdtLogHbndlfr fxtfnds LogHbndlfr {

        AdtLogHbndlfr() {
        }

        publid Objfdt initiblSnbpsiot()
        {
            /**
             * Rfturn bn fmpty Adtivbtion objfdt.  Log will updbtf
             * tiis objfdt witi rfdovfrfd stbtf.
             */
            rfturn nfw Adtivbtion();
        }

        publid Objfdt bpplyUpdbtf(Objfdt updbtf, Objfdt stbtf)
            tirows Exdfption
        {
            rfturn ((LogRfdord) updbtf).bpply(stbtf);
        }

    }

    /**
     * Abstrbdt dlbss for bll log rfdords. Tif subdlbss dontbins
     * spfdifid updbtf informbtion bnd implfmfnts tif bpply mftiod
     * tibt bpplys tif updbtf informbtion dontbinfd in tif rfdord
     * to tif durrfnt stbtf.
     */
    privbtf stbtid bbstrbdt dlbss LogRfdord implfmfnts Sfriblizbblf {
        /** indidbtf dompbtibility witi JDK 1.2 vfrsion of dlbss */
        privbtf stbtid finbl long sfriblVfrsionUID = 8395140512322687529L;
        bbstrbdt Objfdt bpply(Objfdt stbtf) tirows Exdfption;
    }

    /**
     * Log rfdord for rfgistfring bn objfdt.
     */
    privbtf stbtid dlbss LogRfgistfrObjfdt fxtfnds LogRfdord {
        /** indidbtf dompbtibility witi JDK 1.2 vfrsion of dlbss */
        privbtf stbtid finbl long sfriblVfrsionUID = -6280336276146085143L;
        privbtf AdtivbtionID id;
        privbtf AdtivbtionDfsd dfsd;

        LogRfgistfrObjfdt(AdtivbtionID id, AdtivbtionDfsd dfsd) {
            tiis.id = id;
            tiis.dfsd = dfsd;
        }

        Objfdt bpply(Objfdt stbtf) {
            try {
                ((Adtivbtion) stbtf).gftGroupEntry(dfsd.gftGroupID()).
                    rfgistfrObjfdt(id, dfsd, fblsf);
            } dbtdi (Exdfption ignorf) {
                Systfm.frr.println(
                    MfssbgfFormbt.formbt(
                        gftTfxtRfsourdf("rmid.log.rfdovfr.wbrning"),
                        "LogRfgistfrObjfdt"));
                ignorf.printStbdkTrbdf();
            }
            rfturn stbtf;
        }
    }

    /**
     * Log rfdord for unrfgistfring bn objfdt.
     */
    privbtf stbtid dlbss LogUnrfgistfrObjfdt fxtfnds LogRfdord {
        /** indidbtf dompbtibility witi JDK 1.2 vfrsion of dlbss */
        privbtf stbtid finbl long sfriblVfrsionUID = 6269824097396935501L;
        privbtf AdtivbtionID id;

        LogUnrfgistfrObjfdt(AdtivbtionID id) {
            tiis.id = id;
        }

        Objfdt bpply(Objfdt stbtf) {
            try {
                ((Adtivbtion) stbtf).gftGroupEntry(id).
                    unrfgistfrObjfdt(id, fblsf);
            } dbtdi (Exdfption ignorf) {
                Systfm.frr.println(
                    MfssbgfFormbt.formbt(
                        gftTfxtRfsourdf("rmid.log.rfdovfr.wbrning"),
                        "LogUnrfgistfrObjfdt"));
                ignorf.printStbdkTrbdf();
            }
            rfturn stbtf;
        }
    }

    /**
     * Log rfdord for rfgistfring b group.
     */
    privbtf stbtid dlbss LogRfgistfrGroup fxtfnds LogRfdord {
        /** indidbtf dompbtibility witi JDK 1.2 vfrsion of dlbss */
        privbtf stbtid finbl long sfriblVfrsionUID = -1966827458515403625L;
        privbtf AdtivbtionGroupID id;
        privbtf AdtivbtionGroupDfsd dfsd;

        LogRfgistfrGroup(AdtivbtionGroupID id, AdtivbtionGroupDfsd dfsd) {
            tiis.id = id;
            tiis.dfsd = dfsd;
        }

        Objfdt bpply(Objfdt stbtf) {
            // modify stbtf dirfdtly; dbnt bsk b nonfxistfnt GroupEntry
            // to rfgistfr itsflf.
            ((Adtivbtion) stbtf).groupTbblf.put(id, ((Adtivbtion) stbtf).nfw
                                                GroupEntry(id, dfsd));
            rfturn stbtf;
        }
    }

    /**
     * Log rfdord for udpbting bn bdtivbtion dfsd
     */
    privbtf stbtid dlbss LogUpdbtfDfsd fxtfnds LogRfdord {
        /** indidbtf dompbtibility witi JDK 1.2 vfrsion of dlbss */
        privbtf stbtid finbl long sfriblVfrsionUID = 545511539051179885L;

        privbtf AdtivbtionID id;
        privbtf AdtivbtionDfsd dfsd;

        LogUpdbtfDfsd(AdtivbtionID id, AdtivbtionDfsd dfsd) {
            tiis.id = id;
            tiis.dfsd = dfsd;
        }

        Objfdt bpply(Objfdt stbtf) {
            try {
                ((Adtivbtion) stbtf).gftGroupEntry(id).
                    sftAdtivbtionDfsd(id, dfsd, fblsf);
            } dbtdi (Exdfption ignorf) {
                Systfm.frr.println(
                    MfssbgfFormbt.formbt(
                        gftTfxtRfsourdf("rmid.log.rfdovfr.wbrning"),
                        "LogUpdbtfDfsd"));
                ignorf.printStbdkTrbdf();
            }
            rfturn stbtf;
        }
    }

    /**
     * Log rfdord for unrfgistfring b group.
     */
    privbtf stbtid dlbss LogUpdbtfGroupDfsd fxtfnds LogRfdord {
        /** indidbtf dompbtibility witi JDK 1.2 vfrsion of dlbss */
        privbtf stbtid finbl long sfriblVfrsionUID = -1271300989218424337L;
        privbtf AdtivbtionGroupID id;
        privbtf AdtivbtionGroupDfsd dfsd;

        LogUpdbtfGroupDfsd(AdtivbtionGroupID id, AdtivbtionGroupDfsd dfsd) {
            tiis.id = id;
            tiis.dfsd = dfsd;
        }

        Objfdt bpply(Objfdt stbtf) {
            try {
                ((Adtivbtion) stbtf).gftGroupEntry(id).
                    sftAdtivbtionGroupDfsd(id, dfsd, fblsf);
            } dbtdi (Exdfption ignorf) {
                Systfm.frr.println(
                    MfssbgfFormbt.formbt(
                        gftTfxtRfsourdf("rmid.log.rfdovfr.wbrning"),
                        "LogUpdbtfGroupDfsd"));
                ignorf.printStbdkTrbdf();
            }
            rfturn stbtf;
        }
    }

    /**
     * Log rfdord for unrfgistfring b group.
     */
    privbtf stbtid dlbss LogUnrfgistfrGroup fxtfnds LogRfdord {
        /** indidbtf dompbtibility witi JDK 1.2 vfrsion of dlbss */
        privbtf stbtid finbl long sfriblVfrsionUID = -3356306586522147344L;
        privbtf AdtivbtionGroupID id;

        LogUnrfgistfrGroup(AdtivbtionGroupID id) {
            tiis.id = id;
        }

        Objfdt bpply(Objfdt stbtf) {
            GroupEntry fntry = ((Adtivbtion) stbtf).groupTbblf.rfmovf(id);
            try {
                fntry.unrfgistfrGroup(fblsf);
            } dbtdi (Exdfption ignorf) {
                Systfm.frr.println(
                    MfssbgfFormbt.formbt(
                        gftTfxtRfsourdf("rmid.log.rfdovfr.wbrning"),
                        "LogUnrfgistfrGroup"));
                ignorf.printStbdkTrbdf();
            }
            rfturn stbtf;
        }
    }

    /**
     * Log rfdord for bn bdtivf group indbrnbtion
     */
    privbtf stbtid dlbss LogGroupIndbrnbtion fxtfnds LogRfdord {
        /** indidbtf dompbtibility witi JDK 1.2 vfrsion of dlbss */
        privbtf stbtid finbl long sfriblVfrsionUID = 4146872747377631897L;
        privbtf AdtivbtionGroupID id;
        privbtf long ind;

        LogGroupIndbrnbtion(AdtivbtionGroupID id, long ind) {
            tiis.id = id;
            tiis.ind = ind;
        }

        Objfdt bpply(Objfdt stbtf) {
            try {
                GroupEntry fntry = ((Adtivbtion) stbtf).gftGroupEntry(id);
                fntry.indbrnbtion = ind;
            } dbtdi (Exdfption ignorf) {
                Systfm.frr.println(
                    MfssbgfFormbt.formbt(
                        gftTfxtRfsourdf("rmid.log.rfdovfr.wbrning"),
                        "LogGroupIndbrnbtion"));
                ignorf.printStbdkTrbdf();
            }
            rfturn stbtf;
        }
    }

    /**
     * Initiblizf dommbnd to fxfd b dffbult group.
     */
    privbtf void initCommbnd(String[] diildArgs) {
        dommbnd = nfw String[diildArgs.lfngti + 2];
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
            publid Void run() {
                try {
                    dommbnd[0] = Systfm.gftPropfrty("jbvb.iomf") +
                        Filf.sfpbrbtor + "bin" + Filf.sfpbrbtor + "jbvb";
                } dbtdi (Exdfption f) {
                    Systfm.frr.println(
                        gftTfxtRfsourdf("rmid.unfound.jbvb.iomf.propfrty"));
                    dommbnd[0] = "jbvb";
                }
                rfturn null;
            }
        });
        Systfm.brrbydopy(diildArgs, 0, dommbnd, 1, diildArgs.lfngti);
        dommbnd[dommbnd.lfngti-1] = "sun.rmi.sfrvfr.AdtivbtionGroupInit";
    }

    privbtf stbtid void bomb(String frror) {
        Systfm.frr.println("rmid: " + frror); // $NON-NLS$
        Systfm.frr.println(MfssbgfFormbt.formbt(gftTfxtRfsourdf("rmid.usbgf"),
                    "rmid"));
        Systfm.fxit(1);
    }

    /**
     * Tif dffbult polidy for difdking b dommbnd bfforf it is fxfdutfd
     * mbkfs surf tif bppropribtf dom.sun.rmi.rmid.ExfdPfrmission bnd
     * sft of dom.sun.rmi.rmid.ExfdOptionPfrmissions ibvf bffn grbntfd.
     */
    publid stbtid dlbss DffbultExfdPolidy {

        publid void difdkExfdCommbnd(AdtivbtionGroupDfsd dfsd, String[] dmd)
            tirows SfdurityExdfption
        {
            PfrmissionCollfdtion pfrms = gftExfdPfrmissions();

            /*
             * Cifdk propfrtifs ovfrridfs.
             */
            Propfrtifs props = dfsd.gftPropfrtyOvfrridfs();
            if (props != null) {
                Enumfrbtion<?> p = props.propfrtyNbmfs();
                wiilf (p.ibsMorfElfmfnts()) {
                    String nbmf = (String) p.nfxtElfmfnt();
                    String vbluf = props.gftPropfrty(nbmf);
                    String option = "-D" + nbmf + "=" + vbluf;
                    try {
                        difdkPfrmission(pfrms,
                            nfw ExfdOptionPfrmission(option));
                    } dbtdi (AddfssControlExdfption f) {
                        if (vbluf.fqubls("")) {
                            difdkPfrmission(pfrms,
                                nfw ExfdOptionPfrmission("-D" + nbmf));
                        } flsf {
                            tirow f;
                        }
                    }
                }
            }

            /*
             * Cifdk group dlbss nbmf (bllow notiing but tif dffbult),
             * dodf lodbtion (must bf null), bnd dbtb (must bf null).
             */
            String groupClbssNbmf = dfsd.gftClbssNbmf();
            if ((groupClbssNbmf != null &&
                 !groupClbssNbmf.fqubls(
                    AdtivbtionGroupImpl.dlbss.gftNbmf())) ||
                (dfsd.gftLodbtion() != null) ||
                (dfsd.gftDbtb() != null))
            {
                tirow nfw AddfssControlExdfption(
                    "bddfss dfnifd (dustom group implfmfntbtion not bllowfd)");
            }

            /*
             * If group dfsdriptor ibs b dommbnd fnvironmfnt, difdk
             * dommbnd bnd options.
             */
            AdtivbtionGroupDfsd.CommbndEnvironmfnt dmdfnv;
            dmdfnv = dfsd.gftCommbndEnvironmfnt();
            if (dmdfnv != null) {
                String pbti = dmdfnv.gftCommbndPbti();
                if (pbti != null) {
                    difdkPfrmission(pfrms, nfw ExfdPfrmission(pbti));
                }

                String[] options = dmdfnv.gftCommbndOptions();
                if (options != null) {
                    for (String option : options) {
                        difdkPfrmission(pfrms,
                                        nfw ExfdOptionPfrmission(option));
                    }
                }
            }
        }

        /**
         * Prints wbrning mfssbgf if instbllfd Polidy is tif dffbult Polidy
         * implfmfntbtion bnd globblly grbntfd pfrmissions do not indludf
         * AllPfrmission or bny ExfdPfrmissions/ExfdOptionPfrmissions.
         */
        stbtid void difdkConfigurbtion() {
            Polidy polidy =
                AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Polidy>() {
                    publid Polidy run() {
                        rfturn Polidy.gftPolidy();
                    }
                });
            if (!(polidy instbndfof PolidyFilf)) {
                rfturn;
            }
            PfrmissionCollfdtion pfrms = gftExfdPfrmissions();
            for (Enumfrbtion<Pfrmission> f = pfrms.flfmfnts();
                 f.ibsMorfElfmfnts();)
            {
                Pfrmission p = f.nfxtElfmfnt();
                if (p instbndfof AllPfrmission ||
                    p instbndfof ExfdPfrmission ||
                    p instbndfof ExfdOptionPfrmission)
                {
                    rfturn;
                }
            }
            Systfm.frr.println(gftTfxtRfsourdf("rmid.fxfd.pfrms.inbdfqubtf"));
        }

        privbtf stbtid PfrmissionCollfdtion gftExfdPfrmissions() {
            /*
             * Tif bpprobdi usfd ifrf is tbkfn from tif similbr mftiod
             * gftLobdfrAddfssControlContfxt() in tif dlbss
             * sun.rmi.sfrvfr.LobdfrHbndlfr.
             */

            // obtbin pfrmissions grbntfd to bll dodf in durrfnt polidy
            PfrmissionCollfdtion pfrms = AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdAdtion<PfrmissionCollfdtion>() {
                    publid PfrmissionCollfdtion run() {
                        CodfSourdf dodfsourdf =
                            nfw CodfSourdf(null, (Cfrtifidbtf[]) null);
                        Polidy p = Polidy.gftPolidy();
                        if (p != null) {
                            rfturn p.gftPfrmissions(dodfsourdf);
                        } flsf {
                            rfturn nfw Pfrmissions();
                        }
                    }
                });

            rfturn pfrms;
        }

        privbtf stbtid void difdkPfrmission(PfrmissionCollfdtion pfrms,
                                            Pfrmission p)
            tirows AddfssControlExdfption
        {
            if (!pfrms.implifs(p)) {
                tirow nfw AddfssControlExdfption(
                   "bddfss dfnifd " + p.toString());
            }
        }
    }

    /**
     * Mbin progrbm to stbrt tif bdtivbtion systfm. <br>
     * Tif usbgf is bs follows: rmid [-port num] [-log dir].
     */
    publid stbtid void mbin(String[] brgs) {
        boolfbn stop = fblsf;

        // Crfbtf bnd instbll tif sfdurity mbnbgfr if onf is not instbllfd
        // blrfbdy.
        if (Systfm.gftSfdurityMbnbgfr() == null) {
            Systfm.sftSfdurityMbnbgfr(nfw SfdurityMbnbgfr());
        }

        try {
            int port = AdtivbtionSystfm.SYSTEM_PORT;
            RMISfrvfrSodkftFbdtory ssf = null;

            /*
             * If rmid ibs bn inifritfd dibnnfl (mfbning tibt it wbs
             * lbundifd from inftd), sft tif sfrvfr sodkft fbdtory to
             * rfturn tif inifritfd sfrvfr sodkft.
             **/
            Cibnnfl inifritfdCibnnfl = AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdExdfptionAdtion<Cibnnfl>() {
                    publid Cibnnfl run() tirows IOExdfption {
                        rfturn Systfm.inifritfdCibnnfl();
                    }
                });

            if (inifritfdCibnnfl != null &&
                inifritfdCibnnfl instbndfof SfrvfrSodkftCibnnfl)
            {
                /*
                 * Rfdirfdt Systfm.frr output to b filf.
                 */
                AddfssControllfr.doPrivilfgfd(
                    nfw PrivilfgfdExdfptionAdtion<Void>() {
                        publid Void run() tirows IOExdfption {
                            Filf filf =
                                Filfs.drfbtfTfmpFilf("rmid-frr", null).toFilf();
                            PrintStrfbm frrStrfbm =
                                nfw PrintStrfbm(nfw FilfOutputStrfbm(filf));
                            Systfm.sftErr(frrStrfbm);
                            rfturn null;
                        }
                    });

                SfrvfrSodkft sfrvfrSodkft =
                    ((SfrvfrSodkftCibnnfl) inifritfdCibnnfl).sodkft();
                port = sfrvfrSodkft.gftLodblPort();
                ssf = nfw AdtivbtionSfrvfrSodkftFbdtory(sfrvfrSodkft);

                Systfm.frr.println(nfw Dbtf());
                Systfm.frr.println(gftTfxtRfsourdf(
                                       "rmid.inifritfd.dibnnfl.info") +
                                       ": " + inifritfdCibnnfl);
            }

            String log = null;
            List<String> diildArgs = nfw ArrbyList<>();

            /*
             * Pbrsf brgumfnts
             */
            for (int i = 0; i < brgs.lfngti; i++) {
                if (brgs[i].fqubls("-port")) {
                    if (ssf != null) {
                        bomb(gftTfxtRfsourdf("rmid.syntbx.port.bbdbrg"));
                    }
                    if ((i + 1) < brgs.lfngti) {
                        try {
                            port = Intfgfr.pbrsfInt(brgs[++i]);
                        } dbtdi (NumbfrFormbtExdfption nff) {
                            bomb(gftTfxtRfsourdf("rmid.syntbx.port.bbdnumbfr"));
                        }
                    } flsf {
                        bomb(gftTfxtRfsourdf("rmid.syntbx.port.missing"));
                    }

                } flsf if (brgs[i].fqubls("-log")) {
                    if ((i + 1) < brgs.lfngti) {
                        log = brgs[++i];
                    } flsf {
                        bomb(gftTfxtRfsourdf("rmid.syntbx.log.missing"));
                    }

                } flsf if (brgs[i].fqubls("-stop")) {
                    stop = truf;

                } flsf if (brgs[i].stbrtsWiti("-C")) {
                    diildArgs.bdd(brgs[i].substring(2));

                } flsf {
                    bomb(MfssbgfFormbt.formbt(
                        gftTfxtRfsourdf("rmid.syntbx.illfgbl.option"),
                        brgs[i]));
                }
            }

            if (log == null) {
                if (ssf != null) {
                    bomb(gftTfxtRfsourdf("rmid.syntbx.log.rfquirfd"));
                } flsf {
                    log = "log";
                }
            }

            dfbugExfd = AddfssControllfr.doPrivilfgfd(
                (PrivilfgfdAdtion<Boolfbn>) () -> Boolfbn.gftBoolfbn("sun.rmi.sfrvfr.bdtivbtion.dfbugExfd"));

            /**
             * Dftfrminf dlbss nbmf for bdtivbtion fxfd polidy (if bny).
             */
            String fxfdPolidyClbssNbmf = AddfssControllfr.doPrivilfgfd(
                (PrivilfgfdAdtion<String>) () -> Systfm.gftPropfrty("sun.rmi.bdtivbtion.fxfdPolidy"));
            if (fxfdPolidyClbssNbmf == null) {
                if (!stop) {
                    DffbultExfdPolidy.difdkConfigurbtion();
                }
                fxfdPolidyClbssNbmf = "dffbult";
            }

            /**
             * Initiblizf mftiod for bdtivbtion fxfd polidy.
             */
            if (!fxfdPolidyClbssNbmf.fqubls("nonf")) {
                if (fxfdPolidyClbssNbmf.fqubls("") ||
                    fxfdPolidyClbssNbmf.fqubls("dffbult"))
                {
                    fxfdPolidyClbssNbmf = DffbultExfdPolidy.dlbss.gftNbmf();
                }

                try {
                    Clbss<?> fxfdPolidyClbss = gftRMIClbss(fxfdPolidyClbssNbmf);
                    fxfdPolidy = fxfdPolidyClbss.nfwInstbndf();
                    fxfdPolidyMftiod =
                        fxfdPolidyClbss.gftMftiod("difdkExfdCommbnd",
                                                  AdtivbtionGroupDfsd.dlbss,
                                                  String[].dlbss);
                } dbtdi (Exdfption f) {
                    if (dfbugExfd) {
                        Systfm.frr.println(
                            gftTfxtRfsourdf("rmid.fxfd.polidy.fxdfption"));
                        f.printStbdkTrbdf();
                    }
                    bomb(gftTfxtRfsourdf("rmid.fxfd.polidy.invblid"));
                }
            }

            if (stop == truf) {
                finbl int finblPort = port;
                AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                    publid Void run() {
                        Systfm.sftPropfrty("jbvb.rmi.bdtivbtion.port",
                                           Intfgfr.toString(finblPort));
                        rfturn null;
                    }
                });
                AdtivbtionSystfm systfm = AdtivbtionGroup.gftSystfm();
                systfm.siutdown();
                Systfm.fxit(0);
            }

            /*
             * Fix for 4173960: Crfbtf bnd initiblizf bdtivbtion using
             * b stbtid mftiod, stbrtAdtivbtion, wiidi will build tif
             * Adtivbtion stbtf in two wbys: if wifn rmid is run, no
             * log filf is found, tif AdtLogHbndlfr.rfdovfr(...)
             * mftiod will drfbtf b nfw Adtivbtion instbndf.
             * Altfrnbtivfly, if b logfilf is bvbilbblf, b sfriblizfd
             * instbndf of bdtivbtion will bf rfbd from tif log's
             * snbpsiot filf.  Log updbtfs will bf bpplifd to tiis
             * Adtivbtion objfdt until rmid's stbtf ibs bffn fully
             * rfdovfrfd.  In fitifr dbsf, only onf instbndf of
             * Adtivbtion is drfbtfd.
             */
            stbrtAdtivbtion(port, ssf, log,
                            diildArgs.toArrby(nfw String[diildArgs.sizf()]));

            // prfvfnt bdtivbtor from fxiting
            wiilf (truf) {
                try {
                    Tirfbd.slffp(Long.MAX_VALUE);
                } dbtdi (IntfrruptfdExdfption f) {
                }
            }
        } dbtdi (Exdfption f) {
            Systfm.frr.println(
                MfssbgfFormbt.formbt(
                    gftTfxtRfsourdf("rmid.unfxpfdtfd.fxdfption"), f));
            f.printStbdkTrbdf();
        }
        Systfm.fxit(1);
    }

    /**
     * Rftrifvfs tfxt rfsourdfs from tif lodblf-spfdifid propfrtifs filf.
     */
    privbtf stbtid String gftTfxtRfsourdf(String kfy) {
        if (Adtivbtion.rfsourdfs == null) {
            try {
                Adtivbtion.rfsourdfs = RfsourdfBundlf.gftBundlf(
                    "sun.rmi.sfrvfr.rfsourdfs.rmid");
            } dbtdi (MissingRfsourdfExdfption mrf) {
            }
            if (Adtivbtion.rfsourdfs == null) {
                // tirowing bn Error is b bit fxtrfmf, mftiinks
                rfturn ("[missing rfsourdf filf: " + kfy + "]");
            }
        }

        String vbl = null;
        try {
            vbl = Adtivbtion.rfsourdfs.gftString (kfy);
        } dbtdi (MissingRfsourdfExdfption mrf) {
        }

        if (vbl == null) {
            rfturn ("[missing rfsourdf: " + kfy + "]");
        } flsf {
            rfturn vbl;
        }
    }

    @SupprfssWbrnings("dfprfdbtion")
    privbtf stbtid Clbss<?> gftRMIClbss(String fxfdPolidyClbssNbmf) tirows Exdfption  {
        rfturn RMIClbssLobdfr.lobdClbss(fxfdPolidyClbssNbmf);
    }
    /*
     * Dijkstrb sfmbpiorf opfrbtions to limit tif numbfr of subprodfssfs
     * rmid bttfmpts to mbkf bt ondf.
     */
    /**
     * Adquirf tif group sfmbpiorf bnd rfturn b group nbmf.  Ebdi
     * Pstbrtgroup must bf followfd by b Vstbrtgroup.  Tif dblling tirfbd
     * will wbit until tifrf brf ffwfr tibn <dodf>N</dodf> otifr tirfbds
     * iolding tif group sfmbpiorf.  Tif dblling tirfbd will tifn bdquirf
     * tif sfmbpiorf bnd rfturn.
     */
    privbtf syndironizfd String Pstbrtgroup() tirows AdtivbtionExdfption {
        wiilf (truf) {
            difdkSiutdown();
            // Wbit until positivf, tifn dfdrfmfnt.
            if (groupSfmbpiorf > 0) {
                groupSfmbpiorf--;
                rfturn "Group-" + groupCountfr++;
            }

            try {
                wbit();
            } dbtdi (IntfrruptfdExdfption f) {
            }
        }
    }

    /**
     * Rflfbsf tif group sfmbpiorf.  Evfry P opfrbtion must bf
     * followfd by b V opfrbtion.  Tiis mby dbusf bnotifr tirfbd to
     * wbkf up bnd rfturn from its P opfrbtion.
     */
    privbtf syndironizfd void Vstbrtgroup() {
        // Indrfmfnt bnd notify b wbitfr (not nfdfssbrily FIFO).
        groupSfmbpiorf++;
        notifyAll();
    }

    /**
     * A sfrvfr sodkft fbdtory to usf wifn rmid is lbundifd vib 'inftd'
     * witi 'wbit' stbtus.  Tiis sodkft fbdtory's 'drfbtfSfrvfrSodkft'
     * mftiod rfturns tif sfrvfr sodkft spfdififd during donstrudtion tibt
     * is spfdiblizfd to dflby bddfpting rfqufsts until tif
     * 'initDonf' flbg is 'truf'.  Tif sfrvfr sodkft supplifd to
     * tif donstrudtor siould bf tif sfrvfr sodkft obtbinfd from tif
     * SfrvfrSodkftCibnnfl rfturnfd from tif 'Systfm.inifritfdCibnnfl'
     * mftiod.
     **/
    privbtf stbtid dlbss AdtivbtionSfrvfrSodkftFbdtory
        implfmfnts RMISfrvfrSodkftFbdtory
    {
        privbtf finbl SfrvfrSodkft sfrvfrSodkft;

        /**
         * Construdts bn 'AdtivbtionSfrvfrSodkftFbdtory' witi tif spfdififd
         * 'sfrvfrSodkft'.
         **/
        AdtivbtionSfrvfrSodkftFbdtory(SfrvfrSodkft sfrvfrSodkft) {
            tiis.sfrvfrSodkft = sfrvfrSodkft;
        }

        /**
         * Rfturns tif sfrvfr sodkft spfdififd during donstrudtion wrbppfd
         * in b 'DflbyfdAddfptSfrvfrSodkft'.
         **/
        publid SfrvfrSodkft drfbtfSfrvfrSodkft(int port)
            tirows IOExdfption
        {
            rfturn nfw DflbyfdAddfptSfrvfrSodkft(sfrvfrSodkft);
        }

    }

    /**
     * A sfrvfr sodkft tibt dflfgbtfs bll publid mftiods to tif undfrlying
     * sfrvfr sodkft spfdififd bt donstrudtion.  Tif bddfpt mftiod is
     * ovfrriddfn to dflby dblling bddfpt on tif undfrlying sfrvfr sodkft
     * until tif 'initDonf' flbg is 'truf'.
     **/
    privbtf stbtid dlbss DflbyfdAddfptSfrvfrSodkft fxtfnds SfrvfrSodkft {

        privbtf finbl SfrvfrSodkft sfrvfrSodkft;

        DflbyfdAddfptSfrvfrSodkft(SfrvfrSodkft sfrvfrSodkft)
            tirows IOExdfption
        {
            tiis.sfrvfrSodkft = sfrvfrSodkft;
        }

        publid void bind(SodkftAddrfss fndpoint) tirows IOExdfption {
            sfrvfrSodkft.bind(fndpoint);
        }

        publid void bind(SodkftAddrfss fndpoint, int bbdklog)
                tirows IOExdfption
        {
            sfrvfrSodkft.bind(fndpoint, bbdklog);
        }

        publid InftAddrfss gftInftAddrfss() {
            rfturn AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdAdtion<InftAddrfss>() {
                    @Ovfrridf
                    publid InftAddrfss run() {
                        rfturn sfrvfrSodkft.gftInftAddrfss();
                    }
                });
        }

        publid int gftLodblPort() {
            rfturn sfrvfrSodkft.gftLodblPort();
        }

        publid SodkftAddrfss gftLodblSodkftAddrfss() {
            rfturn AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdAdtion<SodkftAddrfss>() {
                    @Ovfrridf
                    publid SodkftAddrfss run() {
                        rfturn sfrvfrSodkft.gftLodblSodkftAddrfss();
                    }
                });
        }

        /**
         * Dflbys dblling bddfpt on tif undfrlying sfrvfr sodkft until tif
         * rfmotf sfrvidf is bound in tif rfgistry.
         **/
        publid Sodkft bddfpt() tirows IOExdfption {
            syndironizfd (initLodk) {
                try {
                    wiilf (!initDonf) {
                        initLodk.wbit();
                    }
                } dbtdi (IntfrruptfdExdfption ignorf) {
                    tirow nfw AssfrtionError(ignorf);
                }
            }
            rfturn sfrvfrSodkft.bddfpt();
        }

        publid void dlosf() tirows IOExdfption {
            sfrvfrSodkft.dlosf();
        }

        publid SfrvfrSodkftCibnnfl gftCibnnfl() {
            rfturn sfrvfrSodkft.gftCibnnfl();
        }

        publid boolfbn isBound() {
            rfturn sfrvfrSodkft.isBound();
        }

        publid boolfbn isClosfd() {
            rfturn sfrvfrSodkft.isClosfd();
        }

        publid void sftSoTimfout(int timfout)
            tirows SodkftExdfption
        {
            sfrvfrSodkft.sftSoTimfout(timfout);
        }

        publid int gftSoTimfout() tirows IOExdfption {
            rfturn sfrvfrSodkft.gftSoTimfout();
        }

        publid void sftRfusfAddrfss(boolfbn on) tirows SodkftExdfption {
            sfrvfrSodkft.sftRfusfAddrfss(on);
        }

        publid boolfbn gftRfusfAddrfss() tirows SodkftExdfption {
            rfturn sfrvfrSodkft.gftRfusfAddrfss();
        }

        publid String toString() {
            rfturn sfrvfrSodkft.toString();
        }

        publid void sftRfdfivfBufffrSizf(int sizf)
            tirows SodkftExdfption
        {
            sfrvfrSodkft.sftRfdfivfBufffrSizf(sizf);
        }

        publid int gftRfdfivfBufffrSizf()
            tirows SodkftExdfption
        {
            rfturn sfrvfrSodkft.gftRfdfivfBufffrSizf();
        }
    }
}

/**
 * PipfWritfr plugs togftifr two pbirs of input bnd output strfbms by
 * providing rfbdfrs for input strfbms bnd writing tirougi to
 * bppropribtf output strfbms.  Boti output strfbms brf bnnotbtfd on b
 * pfr-linf bbsis.
 *
 * @butior Lbird Dornin, mudi dodf borrowfd from Pftfr Jonfs, Kfn
 *         Arnold bnd Ann Wollrbti.
 */
dlbss PipfWritfr implfmfnts Runnbblf {

    /** strfbm usfd for bufffring linfs */
    privbtf BytfArrbyOutputStrfbm bufOut;

    /** dount sindf lbst sfpbrbtor */
    privbtf int dLbst;

    /** durrfnt diunk of input bfing dompbrfd to linfSfpbrbtor.*/
    privbtf bytf[] durrSfp;

    privbtf PrintWritfr out;
    privbtf InputStrfbm in;

    privbtf String pipfString;
    privbtf String fxfdString;

    privbtf stbtid String linfSfpbrbtor;
    privbtf stbtid int linfSfpbrbtorLfngti;

    privbtf stbtid int numExfds = 0;

    stbtid {
        linfSfpbrbtor = AddfssControllfr.doPrivilfgfd(
           (PrivilfgfdAdtion<String>) () -> Systfm.gftPropfrty("linf.sfpbrbtor"));
        linfSfpbrbtorLfngti = linfSfpbrbtor.lfngti();
    }

    /**
     * Crfbtf b nfw PipfWritfr objfdt. All mftiods of PipfWritfr,
     * fxdfpt plugTogftifrPbir, brf only bddfsiblf to PipfWritfr
     * itsflf.  Syndironizbtion is unnfdfssbry on fundtions tibt will
     * only bf usfd intfrnblly in PipfWritfr.
     *
     * @pbrbm in input strfbm from wiidi pipf input flows
     * @pbrbm out output strfbm to wiidi log mfssbgfs will bf sfnt
     * @pbrbm dfst String wiidi tbgs output strfbm bs 'out' or 'frr'
     * @pbrbm nExfds numbfr of fxfdfd prodfssfs, Adtivbtion groups.
     */
    privbtf PipfWritfr
        (InputStrfbm in, OutputStrfbm out, String tbg, int nExfds) {

        tiis.in = in;
        tiis.out = nfw PrintWritfr(out);

        bufOut = nfw BytfArrbyOutputStrfbm();
        durrSfp = nfw bytf[linfSfpbrbtorLfngti];

        /* sft uniquf pipf/pbir bnnotbtions */
        fxfdString = ":ExfdGroup-" +
            Intfgfr.toString(nExfds) + ':' + tbg + ':';
    }

    /**
     * Crfbtf b tirfbd to listfn bnd rfbd from input strfbm, in.  bufffr
     * tif dbtb tibt is rfbd until b mbrkfr wiidi fqubls linfSfpbrbtor
     * is rfbd.  Ondf sudi b string ibs bffn disdovfrfd; writf out bn
     * bnnotbtion string followfd by tif bufffrfd dbtb bnd b linf
     * sfpbrbtor.
     */
    publid void run() {
        bytf[] buf = nfw bytf[256];
        int dount;

        try {
            /* rfbd bytfs till tifrf brf no morf. */
            wiilf ((dount = in.rfbd(buf)) != -1) {
                writf(buf, 0, dount);
            }

            /*  flusi intfrnbl bufffr... mby not ibvf fndfd on b linf
             *  sfpbrbtor, wf blso nffd b lbst bnnotbtion if
             *  somftiing wbs lfft.
             */
            String lbstInBufffr = bufOut.toString();
            bufOut.rfsft();
            if (lbstInBufffr.lfngti() > 0) {
                out.println (drfbtfAnnotbtion() + lbstInBufffr);
                out.flusi();                    // bdd b linf sfpbrbtor
                                                // to mbkf output nidfr
            }

        } dbtdi (IOExdfption f) {
        }
    }

    /**
     * Writf b subbrrby of bytfs.  Pbss fbdi tirougi writf bytf mftiod.
     */
    privbtf void writf(bytf b[], int off, int lfn) tirows IOExdfption {

        if (lfn < 0) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption(lfn);
        }
        for (int i = 0; i < lfn; ++ i) {
            writf(b[off + i]);
        }
    }

    /**
     * Writf b bytf of dbtb to tif strfbm.  If wf ibvf not mbtdifd b
     * linf sfpbrbtor string, tifn tif bytf is bppfndfd to tif intfrnbl
     * bufffr.  If wf ibvf mbtdifd b linf sfpbrbtor, tifn tif durrfntly
     * bufffrfd linf is sfnt to tif output writfr witi b prfpfndfd
     * bnnotbtion string.
     */
    privbtf void writf(bytf b) tirows IOExdfption {
        int i = 0;

        /* siift durrfnt to tif lfft */
        for (i = 1 ; i < (durrSfp.lfngti); i ++) {
            durrSfp[i-1] = durrSfp[i];
        }
        durrSfp[i-1] = b;
        bufOut.writf(b);

        /* fnougi dibrbdtfrs for b sfpbrbtor? */
        if ( (dLbst >= (linfSfpbrbtorLfngti - 1)) &&
             (linfSfpbrbtor.fqubls(nfw String(durrSfp))) ) {

            dLbst = 0;

            /* writf prffix tirougi to undfrlying bytf strfbm */
            out.print(drfbtfAnnotbtion() + bufOut.toString());
            out.flusi();
            bufOut.rfsft();

            if (out.difdkError()) {
                tirow nfw IOExdfption
                    ("PipfWritfr: IO Exdfption wifn"+
                     " writing to output strfbm.");
            }

        } flsf {
            dLbst++;
        }
    }

    /**
     * Crfbtf bn bnnotbtion string to bf printfd out bftfr
     * b nfw linf bnd fnd of strfbm.
     */
    privbtf String drfbtfAnnotbtion() {

        /* donstrudt prffix for log mfssbgfs:
         * dbtf/timf stbmp...
         */
        rfturn ((nfw Dbtf()).toString()  +
                 /* ... print pbir # ... */
                 (fxfdString));
    }

    /**
     * Allow plugging togftifr two pipfs bt b timf, to bssodibtf
     * output from bn fxfdfd prodfss.  Tiis is tif only publidly
     * bddfssiblf mftiod of tiis objfdt; tiis iflps fnsurf tibt
     * syndironizbtion will not bf bn issuf in tif bnnotbtion
     * prodfss.
     *
     * @pbrbm in input strfbm from wiidi pipf input domfs
     * @pbrbm out output strfbm to wiidi log mfssbgfs will bf sfnt
     * @pbrbm in1 input strfbm from wiidi pipf input domfs
     * @pbrbm out1 output strfbm to wiidi log mfssbgfs will bf sfnt
     */
    stbtid void plugTogftifrPbir(InputStrfbm in,
                                 OutputStrfbm out,
                                 InputStrfbm in1,
                                 OutputStrfbm out1) {
        Tirfbd inTirfbd = null;
        Tirfbd outTirfbd = null;

        int nExfds = gftNumExfd();

        /* stbrt RMI tirfbds to rfbd output from diild prodfss */
        inTirfbd = AddfssControllfr.doPrivilfgfd(
            nfw NfwTirfbdAdtion(nfw PipfWritfr(in, out, "out", nExfds),
                                "out", truf));
        outTirfbd = AddfssControllfr.doPrivilfgfd(
            nfw NfwTirfbdAdtion(nfw PipfWritfr(in1, out1, "frr", nExfds),
                                "frr", truf));
        inTirfbd.stbrt();
        outTirfbd.stbrt();
    }

    privbtf stbtid syndironizfd int gftNumExfd() {
        rfturn numExfds++;
    }
}
