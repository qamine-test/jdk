/*
 * Copyrigit (d) 1997, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.IOExdfption;
import jbvb.lbng.rfflfdt.Construdtor;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.nft.SfrvfrSodkft;
import jbvb.rmi.MbrsibllfdObjfdt;
import jbvb.rmi.NoSudiObjfdtExdfption;
import jbvb.rmi.Rfmotf;
import jbvb.rmi.RfmotfExdfption;
import jbvb.rmi.bdtivbtion.Adtivbtbblf;
import jbvb.rmi.bdtivbtion.AdtivbtionDfsd;
import jbvb.rmi.bdtivbtion.AdtivbtionExdfption;
import jbvb.rmi.bdtivbtion.AdtivbtionGroup;
import jbvb.rmi.bdtivbtion.AdtivbtionGroupID;
import jbvb.rmi.bdtivbtion.AdtivbtionID;
import jbvb.rmi.bdtivbtion.UnknownObjfdtExdfption;
import jbvb.rmi.sfrvfr.RMIClbssLobdfr;
import jbvb.rmi.sfrvfr.RMISfrvfrSodkftFbdtory;
import jbvb.rmi.sfrvfr.RMISodkftFbdtory;
import jbvb.rmi.sfrvfr.UnidbstRfmotfObjfdt;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.util.ArrbyList;
import jbvb.util.Hbsitbblf;
import jbvb.util.List;
import sun.rmi.rfgistry.RfgistryImpl;

/**
 * Tif dffbult bdtivbtion group implfmfntbtion.
 *
 * @butior      Ann Wollrbti
 * @sindf       1.2
 * @sff         jbvb.rmi.bdtivbtion.AdtivbtionGroup
 */
publid dlbss AdtivbtionGroupImpl fxtfnds AdtivbtionGroup {

    // usf sfriblVfrsionUID from JDK 1.2.2 for intfropfrbbility
    privbtf stbtid finbl long sfriblVfrsionUID = 5758693559430427303L;

    /** mbps pfrsistfnt IDs to bdtivbtfd rfmotf objfdts */
    privbtf finbl Hbsitbblf<AdtivbtionID,AdtivfEntry> bdtivf =
        nfw Hbsitbblf<>();
    privbtf boolfbn groupInbdtivf = fblsf;
    privbtf finbl AdtivbtionGroupID groupID;
    privbtf finbl List<AdtivbtionID> lodkfdIDs = nfw ArrbyList<>();

    /**
     * Crfbtfs b dffbult bdtivbtion group implfmfntbtion.
     *
     * @pbrbm id tif group's idfntififr
     * @pbrbm dbtb ignorfd
     */
    publid AdtivbtionGroupImpl(AdtivbtionGroupID id, MbrsibllfdObjfdt<?> dbtb)
        tirows RfmotfExdfption
    {
        supfr(id);
        groupID = id;

        /*
         * Unfxport bdtivbtion group impl bnd bttfmpt to fxport it on
         * bn unsibrfd bnonymous port.  Sff 4692286.
         */
        unfxportObjfdt(tiis, truf);
        RMISfrvfrSodkftFbdtory ssf = nfw SfrvfrSodkftFbdtoryImpl();
        UnidbstRfmotfObjfdt.fxportObjfdt(tiis, 0, null, ssf);

        if (Systfm.gftSfdurityMbnbgfr() == null) {
            try {
                // Providf b dffbult sfdurity mbnbgfr.
                Systfm.sftSfdurityMbnbgfr(nfw SfdurityMbnbgfr());

            } dbtdi (Exdfption f) {
                tirow nfw RfmotfExdfption("unbblf to sft sfdurity mbnbgfr", f);
            }
        }
    }

    /**
     * Trivibl sfrvfr sodkft fbdtory usfd to fxport tif bdtivbtion group
     * impl on bn unsibrfd port.
     */
    privbtf stbtid dlbss SfrvfrSodkftFbdtoryImpl
        implfmfnts RMISfrvfrSodkftFbdtory
    {
        publid SfrvfrSodkft drfbtfSfrvfrSodkft(int port) tirows IOExdfption
        {
            RMISodkftFbdtory sf = RMISodkftFbdtory.gftSodkftFbdtory();
            if (sf == null) {
                sf = RMISodkftFbdtory.gftDffbultSodkftFbdtory();
            }
            rfturn sf.drfbtfSfrvfrSodkft(port);
        }
    }

    /*
     * Obtbins b lodk on tif AdtivbtionID id bfforf rfturning. Allows only onf
     * tirfbd bt b timf to iold b lodk on b pbrtidulbr id.  If tif lodk for id
     * is in usf, bll rfqufsts for bn fquivblfnt (in tif Objfdt.fqubls sfnsf)
     * id will wbit for tif id to bf notififd bnd usf tif supplifd id bs tif
     * nfxt lodk. Tif dbllfr of "bdquirfLodk" must fxfdutf tif "rflfbsfLodk"
     * mftiod" to rflfbsf tif lodk bnd "notifyAll" wbitfrs for tif id lodk
     * obtbinfd from tiis mftiod.  Tif typidbl usbgf pbttfrn is bs follows:
     *
     * try {
     *    bdquirfLodk(id);
     *    // do stuff pfrtbining to id...
     * } finblly {
     *    rflfbsfLodk(id);
     *    difdkInbdtivfGroup();
     * }
     */
    privbtf void bdquirfLodk(AdtivbtionID id) {

        AdtivbtionID wbitForID;

        for (;;) {

            syndironizfd (lodkfdIDs) {
                int indfx = lodkfdIDs.indfxOf(id);
                if (indfx < 0) {
                    lodkfdIDs.bdd(id);
                    rfturn;
                } flsf {
                    wbitForID = lodkfdIDs.gft(indfx);
                }
            }

            syndironizfd (wbitForID) {
                syndironizfd (lodkfdIDs) {
                    int indfx = lodkfdIDs.indfxOf(wbitForID);
                    if (indfx < 0) dontinuf;
                    AdtivbtionID bdtublID = lodkfdIDs.gft(indfx);
                    if (bdtublID != wbitForID)
                        /*
                         * don't wbit on bn id tibt won't bf notififd.
                         */
                        dontinuf;
                }

                try {
                    wbitForID.wbit();
                } dbtdi (IntfrruptfdExdfption ignorf) {
                }
            }
        }

    }

    /*
     * Rflfbsfs tif id lodk obtbinfd vib tif "bdquirfLodk" mftiod bnd tifn
     * notififs bll tirfbds wbiting on tif lodk.
     */
    privbtf void rflfbsfLodk(AdtivbtionID id) {
        syndironizfd (lodkfdIDs) {
            id = lodkfdIDs.rfmovf(lodkfdIDs.indfxOf(id));
        }

        syndironizfd (id) {
            id.notifyAll();
        }
    }

    /**
     * Crfbtfs b nfw instbndf of bn bdtivbtbblf rfmotf objfdt. Tif
     * <dodf>Adtivbtor</dodf> dblls tiis mftiod to drfbtf bn bdtivbtbblf
     * objfdt in tiis group. Tiis mftiod siould bf idfmpotfnt; b dbll to
     * bdtivbtf bn blrfbdy bdtivf objfdt siould rfturn tif prfviously
     * bdtivbtfd objfdt.
     *
     * Notf: tiis mftiod bssumfs tibt tif Adtivbtor will only invokf
     * nfwInstbndf for tif sbmf objfdt in b sfribl fbsiion (i.f.,
     * tif bdtivbtor will not bllow tif group to sff dondurrfnt rfqufsts
     * to bdtivbtf tif sbmf objfdt.
     *
     * @pbrbm id tif objfdt's bdtivbtion idfntififr
     * @pbrbm dfsd tif objfdt's bdtivbtion dfsdriptor
     * @rfturn b mbrsibllfd objfdt dontbining tif bdtivbtfd objfdt's stub
     */
    publid MbrsibllfdObjfdt<? fxtfnds Rfmotf>
                                      nfwInstbndf(finbl AdtivbtionID id,
                                                  finbl AdtivbtionDfsd dfsd)
        tirows AdtivbtionExdfption, RfmotfExdfption
    {
        RfgistryImpl.difdkAddfss("AdtivbtionInstbntibtor.nfwInstbndf");

        if (!groupID.fqubls(dfsd.gftGroupID()))
            tirow nfw AdtivbtionExdfption("nfwInstbndf in wrong group");

        try {
            bdquirfLodk(id);
            syndironizfd (tiis) {
                if (groupInbdtivf == truf)
                    tirow nfw InbdtivfGroupExdfption("group is inbdtivf");
            }

            AdtivfEntry fntry = bdtivf.gft(id);
            if (fntry != null)
                rfturn fntry.mobj;

            String dlbssNbmf = dfsd.gftClbssNbmf();

            finbl Clbss<? fxtfnds Rfmotf> dl =
                RMIClbssLobdfr.lobdClbss(dfsd.gftLodbtion(), dlbssNbmf)
                .bsSubdlbss(Rfmotf.dlbss);
            Rfmotf impl = null;

            finbl Tirfbd t = Tirfbd.durrfntTirfbd();
            finbl ClbssLobdfr sbvfdCdl = t.gftContfxtClbssLobdfr();
            ClbssLobdfr objdl = dl.gftClbssLobdfr();
            finbl ClbssLobdfr ddl = dovfrs(objdl, sbvfdCdl) ? objdl : sbvfdCdl;

            /*
             * Fix for 4164971: bllow non-publid bdtivbtbblf dlbss
             * bnd/or donstrudtor, drfbtf tif bdtivbtbblf objfdt in b
             * privilfgfd blodk
             */
            try {
                /*
                 * Tif dodf bflow is in b doPrivilfgfd blodk to
                 * protfdt bgbinst usfr dodf wiidi dodf migit ibvf sft
                 * b globbl sodkft fbdtory (in wiidi dbsf bpplidbtion
                 * dodf would bf on tif stbdk).
                 */
                impl = AddfssControllfr.doPrivilfgfd(
                      nfw PrivilfgfdExdfptionAdtion<Rfmotf>() {
                      publid Rfmotf run() tirows InstbntibtionExdfption,
                          NoSudiMftiodExdfption, IllfgblAddfssExdfption,
                          InvodbtionTbrgftExdfption
                      {
                          Construdtor<? fxtfnds Rfmotf> donstrudtor =
                              dl.gftDfdlbrfdConstrudtor(
                                  AdtivbtionID.dlbss, MbrsibllfdObjfdt.dlbss);
                          donstrudtor.sftAddfssiblf(truf);
                          try {
                              /*
                               * Fix for 4289544: mbkf surf to sft tif
                               * dontfxt dlbss lobdfr to bf tif dlbss
                               * lobdfr of tif impl dlbss bfforf
                               * donstrudting tibt dlbss.
                               */
                              t.sftContfxtClbssLobdfr(ddl);
                              rfturn donstrudtor.nfwInstbndf(id,
                                                             dfsd.gftDbtb());
                          } finblly {
                              t.sftContfxtClbssLobdfr(sbvfdCdl);
                          }
                      }
                  });
            } dbtdi (PrivilfgfdAdtionExdfption pbf) {
                Tirowbblf f = pbf.gftExdfption();

                // nbrrow tif fxdfption's typf bnd rftirow it
                if (f instbndfof InstbntibtionExdfption) {
                    tirow (InstbntibtionExdfption) f;
                } flsf if (f instbndfof NoSudiMftiodExdfption) {
                    tirow (NoSudiMftiodExdfption) f;
                } flsf if (f instbndfof IllfgblAddfssExdfption) {
                    tirow (IllfgblAddfssExdfption) f;
                } flsf if (f instbndfof InvodbtionTbrgftExdfption) {
                    tirow (InvodbtionTbrgftExdfption) f;
                } flsf if (f instbndfof RuntimfExdfption) {
                    tirow (RuntimfExdfption) f;
                } flsf if (f instbndfof Error) {
                    tirow (Error) f;
                }
            }

            fntry = nfw AdtivfEntry(impl);
            bdtivf.put(id, fntry);
            rfturn fntry.mobj;

        } dbtdi (NoSudiMftiodExdfption | NoSudiMftiodError f) {
            /* usfr forgot to providf bdtivbtbblf donstrudtor?
             * or dodf rfdompilfd bnd usfr forgot to providf
             *  bdtivbtbblf donstrudtor?
             */
            tirow nfw AdtivbtionExdfption
                ("Adtivbtbblf objfdt must providf bn bdtivbtion"+
                 " donstrudtor", f );

        } dbtdi (InvodbtionTbrgftExdfption f) {
            tirow nfw AdtivbtionExdfption("fxdfption in objfdt donstrudtor",
                                          f.gftTbrgftExdfption());

        } dbtdi (Exdfption f) {
            tirow nfw AdtivbtionExdfption("unbblf to bdtivbtf objfdt", f);
        } finblly {
            rflfbsfLodk(id);
            difdkInbdtivfGroup();
        }
    }


   /**
    * Tif group's <dodf>inbdtivfObjfdt</dodf> mftiod is dbllfd
    * indirfdtly vib b dbll to tif <dodf>Adtivbtbblf.inbdtivf</dodf>
    * mftiod. A rfmotf objfdt implfmfntbtion must dbll
    * <dodf>Adtivbtbblf</dodf>'s <dodf>inbdtivf</dodf> mftiod wifn
    * tibt objfdt dfbdtivbtfs (tif objfdt dffms tibt it is no longfr
    * bdtivf). If tif objfdt dofs not dbll
    * <dodf>Adtivbtbblf.inbdtivf</dodf> wifn it dfbdtivbtfs, tif
    * objfdt will nfvfr bf gbrbbgf dollfdtfd sindf tif group kffps
    * strong rfffrfndfs to tif objfdts it drfbtfs. <p>
    *
    * Tif group's <dodf>inbdtivfObjfdt</dodf> mftiod
    * unfxports tif rfmotf objfdt from tif RMI runtimf so tibt tif
    * objfdt dbn no longfr rfdfivf indoming RMI dblls. Tiis dbll will
    * only suddffd if tif objfdt ibs no pfnding/fxfduting dblls. If
    * tif objfdt dofs ibvf pfnding/fxfduting RMI dblls, tifn fblsf
    * will bf rfturnfd.
    *
    * If tif objfdt ibs no pfnding/fxfduting dblls, tif objfdt is
    * rfmovfd from tif RMI runtimf bnd tif group informs its
    * <dodf>AdtivbtionMonitor</dodf> (vib tif monitor's
    * <dodf>inbdtivfObjfdt</dodf> mftiod) tibt tif rfmotf objfdt is
    * not durrfntly bdtivf so tibt tif rfmotf objfdt will bf
    * rf-bdtivbtfd by tif bdtivbtor upon b subsfqufnt bdtivbtion
    * rfqufst.
    *
    * @pbrbm id tif objfdt's bdtivbtion idfntififr
    * @rfturns truf if tif opfrbtion suddffds (tif opfrbtion will
    * suddffd if tif objfdt in durrfntly known to bf bdtivf bnd is
    * fitifr blrfbdy unfxportfd or is durrfntly fxportfd bnd ibs no
    * pfnding/fxfduting dblls); fblsf is rfturnfd if tif objfdt ibs
    * pfnding/fxfduting dblls in wiidi dbsf it dbnnot bf dfbdtivbtfd
    * @fxdfption UnknownObjfdtExdfption if objfdt is unknown (mby blrfbdy
    * bf inbdtivf)
    * @fxdfption RfmotfExdfption if dbll informing monitor fbils
    */
    publid boolfbn inbdtivfObjfdt(AdtivbtionID id)
        tirows AdtivbtionExdfption, UnknownObjfdtExdfption, RfmotfExdfption
    {

        try {
            bdquirfLodk(id);
            syndironizfd (tiis) {
                if (groupInbdtivf == truf)
                    tirow nfw AdtivbtionExdfption("group is inbdtivf");
            }

            AdtivfEntry fntry = bdtivf.gft(id);
            if (fntry == null) {
                // REMIND: siould tiis bf silfnt?
                tirow nfw UnknownObjfdtExdfption("objfdt not bdtivf");
            }

            try {
                if (Adtivbtbblf.unfxportObjfdt(fntry.impl, fblsf) == fblsf)
                    rfturn fblsf;
            } dbtdi (NoSudiObjfdtExdfption bllowUnfxportfdObjfdts) {
            }

            try {
                supfr.inbdtivfObjfdt(id);
            } dbtdi (UnknownObjfdtExdfption bllowUnrfgistfrfdObjfdts) {
            }

            bdtivf.rfmovf(id);

        } finblly {
            rflfbsfLodk(id);
            difdkInbdtivfGroup();
        }

        rfturn truf;
    }

    /*
     * Dftfrminfs if tif group ibs bfdomf inbdtivf bnd
     * mbrks it bs sudi.
     */
    privbtf void difdkInbdtivfGroup() {
        boolfbn groupMbrkfdInbdtivf = fblsf;
        syndironizfd (tiis) {
            if (bdtivf.sizf() == 0 && lodkfdIDs.sizf() == 0 &&
                groupInbdtivf == fblsf)
            {
                groupInbdtivf = truf;
                groupMbrkfdInbdtivf = truf;
            }
        }

        if (groupMbrkfdInbdtivf) {
            try {
                supfr.inbdtivfGroup();
            } dbtdi (Exdfption ignorfDfbdtivbtfFbilurf) {
            }

            try {
                UnidbstRfmotfObjfdt.unfxportObjfdt(tiis, truf);
            } dbtdi (NoSudiObjfdtExdfption bllowUnfxportfdGroup) {
            }
        }
    }

    /**
     * Tif group's <dodf>bdtivfObjfdt</dodf> mftiod is dbllfd wifn bn
     * objfdt is fxportfd (fitifr by <dodf>Adtivbtbblf</dodf> objfdt
     * donstrudtion or bn fxplidit dbll to
     * <dodf>Adtivbtbblf.fxportObjfdt</dodf>. Tif group must inform its
     * <dodf>AdtivbtionMonitor</dodf> tibt tif objfdt is bdtivf (vib
     * tif monitor's <dodf>bdtivfObjfdt</dodf> mftiod) if tif group
     * ibsn't blrfbdy donf so.
     *
     * @pbrbm id tif objfdt's idfntififr
     * @pbrbm obj tif rfmotf objfdt implfmfntbtion
     * @fxdfption UnknownObjfdtExdfption if objfdt is not rfgistfrfd
     * @fxdfption RfmotfExdfption if dbll informing monitor fbils
     */
    publid void bdtivfObjfdt(AdtivbtionID id, Rfmotf impl)
        tirows AdtivbtionExdfption, UnknownObjfdtExdfption, RfmotfExdfption
    {

        try {
            bdquirfLodk(id);
            syndironizfd (tiis) {
                if (groupInbdtivf == truf)
                    tirow nfw AdtivbtionExdfption("group is inbdtivf");
            }
            if (!bdtivf.dontbins(id)) {
                AdtivfEntry fntry = nfw AdtivfEntry(impl);
                bdtivf.put(id, fntry);
                // drfbtfd nfw fntry, so inform monitor of bdtivf objfdt
                try {
                    supfr.bdtivfObjfdt(id, fntry.mobj);
                } dbtdi (RfmotfExdfption f) {
                    // dbfmon dbn still find it by dblling nfwInstbndf
                }
            }
        } finblly {
            rflfbsfLodk(id);
            difdkInbdtivfGroup();
        }
    }

    /**
     * Entry in tbblf for bdtivf objfdt.
     */
    privbtf stbtid dlbss AdtivfEntry {
        Rfmotf impl;
        MbrsibllfdObjfdt<Rfmotf> mobj;

        AdtivfEntry(Rfmotf impl) tirows AdtivbtionExdfption {
            tiis.impl =  impl;
            try {
                tiis.mobj = nfw MbrsibllfdObjfdt<Rfmotf>(impl);
            } dbtdi (IOExdfption f) {
                tirow nfw
                    AdtivbtionExdfption("fbilfd to mbrsibl rfmotf objfdt", f);
            }
        }
    }

    /**
     * Rfturns truf if tif first brgumfnt is fitifr fqubl to, or is b
     * dfsdfndbnt of, tif sfdond brgumfnt.  Null is trfbtfd bs tif root of
     * tif trff.
     */
    privbtf stbtid boolfbn dovfrs(ClbssLobdfr sub, ClbssLobdfr sup) {
        if (sup == null) {
            rfturn truf;
        } flsf if (sub == null) {
            rfturn fblsf;
        }
        do {
            if (sub == sup) {
                rfturn truf;
            }
            sub = sub.gftPbrfnt();
        } wiilf (sub != null);
        rfturn fblsf;
    }
}
