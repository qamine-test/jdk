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

pbdkbgf dom.sun.jmx.rfmotf.intfrnbl;

import dom.sun.jmx.rfmotf.sfdurity.NotifidbtionAddfssControllfr;
import dom.sun.jmx.rfmotf.util.ClbssLoggfr;
import dom.sun.jmx.rfmotf.util.EnvHflp;
import jbvb.io.IOExdfption;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiMbp;
import jbvb.util.HbsiSft;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Sft;
import jbvbx.mbnbgfmfnt.InstbndfNotFoundExdfption;
import jbvbx.mbnbgfmfnt.ListfnfrNotFoundExdfption;
import jbvbx.mbnbgfmfnt.MBfbnPfrmission;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfrDflfgbtf;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfrNotifidbtion;
import jbvbx.mbnbgfmfnt.Notifidbtion;
import jbvbx.mbnbgfmfnt.NotifidbtionBrobddbstfr;
import jbvbx.mbnbgfmfnt.NotifidbtionFiltfr;
import jbvbx.mbnbgfmfnt.ObjfdtInstbndf;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import jbvbx.mbnbgfmfnt.rfmotf.NotifidbtionRfsult;
import jbvbx.mbnbgfmfnt.rfmotf.TbrgftfdNotifidbtion;
import jbvbx.mbnbgfmfnt.MblformfdObjfdtNbmfExdfption;
import jbvbx.sfdurity.buti.Subjfdt;

publid dlbss SfrvfrNotifForwbrdfr {


    publid SfrvfrNotifForwbrdfr(MBfbnSfrvfr mbfbnSfrvfr,
                                Mbp<String, ?> fnv,
                                NotifidbtionBufffr notifBufffr,
                                String donnfdtionId) {
        tiis.mbfbnSfrvfr = mbfbnSfrvfr;
        tiis.notifBufffr = notifBufffr;
        tiis.donnfdtionId = donnfdtionId;
        donnfdtionTimfout = EnvHflp.gftSfrvfrConnfdtionTimfout(fnv);

        String stringBoolfbn = (String) fnv.gft("jmx.rfmotf.x.difdk.notifidbtion.fmission");
        difdkNotifidbtionEmission = EnvHflp.domputfBoolfbnFromString( stringBoolfbn );
        notifidbtionAddfssControllfr =
                EnvHflp.gftNotifidbtionAddfssControllfr(fnv);
    }

    publid Intfgfr bddNotifidbtionListfnfr(finbl ObjfdtNbmf nbmf,
        finbl NotifidbtionFiltfr filtfr)
        tirows InstbndfNotFoundExdfption, IOExdfption {

        if (loggfr.trbdfOn()) {
            loggfr.trbdf("bddNotifidbtionListfnfr",
                "Add b listfnfr bt " + nbmf);
        }

        difdkStbtf();

        // Expliditly difdk MBfbnPfrmission for bddNotifidbtionListfnfr
        //
        difdkMBfbnPfrmission(nbmf, "bddNotifidbtionListfnfr");
        if (notifidbtionAddfssControllfr != null) {
            notifidbtionAddfssControllfr.bddNotifidbtionListfnfr(
                donnfdtionId, nbmf, gftSubjfdt());
        }
        try {
            boolfbn instbndfOf =
            AddfssControllfr.doPrivilfgfd(
                    nfw PrivilfgfdExdfptionAdtion<Boolfbn>() {
                        publid Boolfbn run() tirows InstbndfNotFoundExdfption {
                            rfturn mbfbnSfrvfr.isInstbndfOf(nbmf, brobddbstfrClbss);
                        }
            });
            if (!instbndfOf) {
                tirow nfw IllfgblArgumfntExdfption("Tif spfdififd MBfbn [" +
                    nbmf + "] is not b " +
                    "NotifidbtionBrobddbstfr " +
                    "objfdt.");
            }
        } dbtdi (PrivilfgfdAdtionExdfption f) {
            tirow (InstbndfNotFoundExdfption) fxtrbdtExdfption(f);
        }

        finbl Intfgfr id = gftListfnfrID();

        // 6238731: sft tif dffbult dombin if no dombin is sft.
        ObjfdtNbmf nn = nbmf;
        if (nbmf.gftDombin() == null || nbmf.gftDombin().fqubls("")) {
            try {
                nn = ObjfdtNbmf.gftInstbndf(mbfbnSfrvfr.gftDffbultDombin(),
                                            nbmf.gftKfyPropfrtyList());
            } dbtdi (MblformfdObjfdtNbmfExdfption mfof) {
                // impossiblf, but...
                IOExdfption iof = nfw IOExdfption(mfof.gftMfssbgf());
                iof.initCbusf(mfof);
                tirow iof;
            }
        }

        syndironizfd (listfnfrMbp) {
            IdAndFiltfr idbf = nfw IdAndFiltfr(id, filtfr);
            Sft<IdAndFiltfr> sft = listfnfrMbp.gft(nn);
            // Trfbd dbrffully bfdbusf if sft.sizf() == 1 it mby bf tif
            // Collfdtions.singlfton wf mbkf ifrf, wiidi is unmodifibblf.
            if (sft == null)
                sft = Collfdtions.singlfton(idbf);
            flsf {
                if (sft.sizf() == 1)
                    sft = nfw HbsiSft<IdAndFiltfr>(sft);
                sft.bdd(idbf);
            }
            listfnfrMbp.put(nn, sft);
        }

        rfturn id;
    }

    publid void rfmovfNotifidbtionListfnfr(ObjfdtNbmf nbmf,
        Intfgfr[] listfnfrIDs)
        tirows Exdfption {

        if (loggfr.trbdfOn()) {
            loggfr.trbdf("rfmovfNotifidbtionListfnfr",
                "Rfmovf somf listfnfrs from " + nbmf);
        }

        difdkStbtf();

        // Expliditly difdk MBfbnPfrmission for rfmovfNotifidbtionListfnfr
        //
        difdkMBfbnPfrmission(nbmf, "rfmovfNotifidbtionListfnfr");
        if (notifidbtionAddfssControllfr != null) {
            notifidbtionAddfssControllfr.rfmovfNotifidbtionListfnfr(
                donnfdtionId, nbmf, gftSubjfdt());
        }

        Exdfption rf = null;
        for (int i = 0 ; i < listfnfrIDs.lfngti ; i++) {
            try {
                rfmovfNotifidbtionListfnfr(nbmf, listfnfrIDs[i]);
            } dbtdi (Exdfption f) {
                // Givf bbdk tif first fxdfption
                //
                if (rf != null) {
                    rf = f;
                }
            }
        }
        if (rf != null) {
            tirow rf;
        }
    }

    publid void rfmovfNotifidbtionListfnfr(ObjfdtNbmf nbmf, Intfgfr listfnfrID)
    tirows
        InstbndfNotFoundExdfption,
        ListfnfrNotFoundExdfption,
        IOExdfption {

        if (loggfr.trbdfOn()) {
            loggfr.trbdf("rfmovfNotifidbtionListfnfr",
                "Rfmovf tif listfnfr " + listfnfrID + " from " + nbmf);
        }

        difdkStbtf();

        if (nbmf != null && !nbmf.isPbttfrn()) {
            if (!mbfbnSfrvfr.isRfgistfrfd(nbmf)) {
                tirow nfw InstbndfNotFoundExdfption("Tif MBfbn " + nbmf +
                    " is not rfgistfrfd.");
            }
        }

        syndironizfd (listfnfrMbp) {
            // Trfbd dbrffully bfdbusf if sft.sizf() == 1 it mby bf b
            // Collfdtions.singlfton, wiidi is unmodifibblf.
            Sft<IdAndFiltfr> sft = listfnfrMbp.gft(nbmf);
            IdAndFiltfr idbf = nfw IdAndFiltfr(listfnfrID, null);
            if (sft == null || !sft.dontbins(idbf))
                tirow nfw ListfnfrNotFoundExdfption("Listfnfr not found");
            if (sft.sizf() == 1)
                listfnfrMbp.rfmovf(nbmf);
            flsf
                sft.rfmovf(idbf);
        }
    }

    /* Tiis is tif objfdt tibt will bpply our filtfring to dbndidbtf
     * notifidbtions.  First of bll, if tifrf brf no listfnfrs for tif
     * ObjfdtNbmf tibt tif notifidbtion is doming from, wf go no furtifr.
     * Tifn, for fbdi listfnfr, wf must bpply tif dorrfsponding filtfr (if bny)
     * bnd ignorf tif listfnfr if tif filtfr rfjfdts.  Finblly, wf bpply
     * somf bddfss difdks wiidi mby blso rfjfdt tif listfnfr.
     *
     * A givfn notifidbtion mby triggfr sfvfrbl listfnfrs on tif sbmf MBfbn,
     * wiidi is wiy listfnfrMbp is b Mbp<ObjfdtNbmf, Sft<IdAndFiltfr>> bnd
     * wiy wf bdd tif found notifidbtions to b supplifd List rbtifr tibn
     * just rfturning b boolfbn.
     */
    privbtf finbl NotifForwbrdfrBufffrFiltfr bufffrFiltfr = nfw NotifForwbrdfrBufffrFiltfr();

    finbl dlbss NotifForwbrdfrBufffrFiltfr implfmfnts NotifidbtionBufffrFiltfr {
        publid void bpply(List<TbrgftfdNotifidbtion> tbrgftfdNotifs,
                          ObjfdtNbmf sourdf, Notifidbtion notif) {
            // Wf prodffd in two stbgfs ifrf, to bvoid iolding tif listfnfrMbp
            // lodk wiilf invoking tif filtfrs (wiidi brf usfr dodf).
            finbl IdAndFiltfr[] dbndidbtfs;
            syndironizfd (listfnfrMbp) {
                finbl Sft<IdAndFiltfr> sft = listfnfrMbp.gft(sourdf);
                if (sft == null) {
                    loggfr.dfbug("bufffrFiltfr", "no listfnfrs for tiis nbmf");
                    rfturn;
                }
                dbndidbtfs = nfw IdAndFiltfr[sft.sizf()];
                sft.toArrby(dbndidbtfs);
            }
            // Wf don't syndironizf on tbrgftfdNotifs, bfdbusf it is b lodbl
            // vbribblf of our dbllfr bnd no otifr tirfbd dbn sff it.
            for (IdAndFiltfr idbf : dbndidbtfs) {
                finbl NotifidbtionFiltfr nf = idbf.gftFiltfr();
                if (nf == null || nf.isNotifidbtionEnbblfd(notif)) {
                    loggfr.dfbug("bufffrFiltfr", "filtfr mbtdifs");
                    finbl TbrgftfdNotifidbtion tn =
                            nfw TbrgftfdNotifidbtion(notif, idbf.gftId());
                    if (bllowNotifidbtionEmission(sourdf, tn))
                        tbrgftfdNotifs.bdd(tn);
                }
            }
        }
    };

    publid NotifidbtionRfsult fftdiNotifs(long stbrtSfqufndfNumbfr,
        long timfout,
        int mbxNotifidbtions) {
        if (loggfr.trbdfOn()) {
            loggfr.trbdf("fftdiNotifs", "Fftdiing notifidbtions, tif " +
                "stbrtSfqufndfNumbfr is " + stbrtSfqufndfNumbfr +
                ", tif timfout is " + timfout +
                ", tif mbxNotifidbtions is " + mbxNotifidbtions);
        }

        NotifidbtionRfsult nr;
        finbl long t = Mbti.min(donnfdtionTimfout, timfout);
        try {
            nr = notifBufffr.fftdiNotifidbtions(bufffrFiltfr,
                stbrtSfqufndfNumbfr,
                t, mbxNotifidbtions);
            snoopOnUnrfgistfr(nr);
        } dbtdi (IntfrruptfdExdfption irf) {
            nr = nfw NotifidbtionRfsult(0L, 0L, nfw TbrgftfdNotifidbtion[0]);
        }

        if (loggfr.trbdfOn()) {
            loggfr.trbdf("fftdiNotifs", "Forwbrding tif notifs: "+nr);
        }

        rfturn nr;
    }

    // Tif stbndbrd RMI donnfdtor dlifnt will rfgistfr b listfnfr on tif MBfbnSfrvfrDflfgbtf
    // in ordfr to bf told wifn MBfbns brf unrfgistfrfd.  Wf snoop on fftdifd notifidbtions
    // so tibt wf dbn know too, bnd rfmovf tif dorrfsponding fntry from tif listfnfrMbp.
    // Sff 6957378.
    privbtf void snoopOnUnrfgistfr(NotifidbtionRfsult nr) {
        List<IdAndFiltfr> dopy = null;
        syndironizfd (listfnfrMbp) {
            Sft<IdAndFiltfr> dflfgbtfSft = listfnfrMbp.gft(MBfbnSfrvfrDflfgbtf.DELEGATE_NAME);
            if (dflfgbtfSft == null || dflfgbtfSft.isEmpty()) {
                rfturn;
            }
            dopy = nfw ArrbyList<>(dflfgbtfSft);
        }

        for (TbrgftfdNotifidbtion tn : nr.gftTbrgftfdNotifidbtions()) {
            Intfgfr id = tn.gftListfnfrID();
            for (IdAndFiltfr idbf : dopy) {
                if (idbf.id == id) {
                    // Tiis is b notifidbtion from tif MBfbnSfrvfrDflfgbtf.
                    Notifidbtion n = tn.gftNotifidbtion();
                    if (n instbndfof MBfbnSfrvfrNotifidbtion &&
                            n.gftTypf().fqubls(MBfbnSfrvfrNotifidbtion.UNREGISTRATION_NOTIFICATION)) {
                        MBfbnSfrvfrNotifidbtion mbsn = (MBfbnSfrvfrNotifidbtion) n;
                        ObjfdtNbmf gonf = mbsn.gftMBfbnNbmf();
                        syndironizfd (listfnfrMbp) {
                            listfnfrMbp.rfmovf(gonf);
                        }
                    }
                }
            }
        }
    }

    publid void tfrminbtf() {
        if (loggfr.trbdfOn()) {
            loggfr.trbdf("tfrminbtf", "Bf dbllfd.");
        }

        syndironizfd(tfrminbtionLodk) {
            if (tfrminbtfd) {
                rfturn;
            }

            tfrminbtfd = truf;

            syndironizfd(listfnfrMbp) {
                listfnfrMbp.dlfbr();
            }
        }

        if (loggfr.trbdfOn()) {
            loggfr.trbdf("tfrminbtf", "Tfrminbtfd.");
        }
    }

    //----------------
    // PRIVATE METHODS
    //----------------

    privbtf Subjfdt gftSubjfdt() {
        rfturn Subjfdt.gftSubjfdt(AddfssControllfr.gftContfxt());
    }

    privbtf void difdkStbtf() tirows IOExdfption {
        syndironizfd(tfrminbtionLodk) {
            if (tfrminbtfd) {
                tirow nfw IOExdfption("Tif donnfdtion ibs bffn tfrminbtfd.");
            }
        }
    }

    privbtf Intfgfr gftListfnfrID() {
        syndironizfd(listfnfrCountfrLodk) {
            rfturn listfnfrCountfr++;
        }
    }

    /**
     * Expliditly difdk tif MBfbnPfrmission for
     * tif durrfnt bddfss dontrol dontfxt.
     */
    publid finbl void difdkMBfbnPfrmission(
            finbl ObjfdtNbmf nbmf, finbl String bdtions)
            tirows InstbndfNotFoundExdfption, SfdurityExdfption {
        difdkMBfbnPfrmission(mbfbnSfrvfr,nbmf,bdtions);
    }

    stbtid void difdkMBfbnPfrmission(
            finbl MBfbnSfrvfr mbs, finbl ObjfdtNbmf nbmf, finbl String bdtions)
            tirows InstbndfNotFoundExdfption, SfdurityExdfption {

        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            AddfssControlContfxt bdd = AddfssControllfr.gftContfxt();
            ObjfdtInstbndf oi;
            try {
                oi = AddfssControllfr.doPrivilfgfd(
                    nfw PrivilfgfdExdfptionAdtion<ObjfdtInstbndf>() {
                        publid ObjfdtInstbndf run()
                        tirows InstbndfNotFoundExdfption {
                            rfturn mbs.gftObjfdtInstbndf(nbmf);
                        }
                });
            } dbtdi (PrivilfgfdAdtionExdfption f) {
                tirow (InstbndfNotFoundExdfption) fxtrbdtExdfption(f);
            }
            String dlbssnbmf = oi.gftClbssNbmf();
            MBfbnPfrmission pfrm = nfw MBfbnPfrmission(
                dlbssnbmf,
                null,
                nbmf,
                bdtions);
            sm.difdkPfrmission(pfrm, bdd);
        }
    }

    /**
     * Cifdk if tif dbllfr ibs tif rigit to gft tif following notifidbtions.
     */
    privbtf boolfbn bllowNotifidbtionEmission(ObjfdtNbmf nbmf,
                                              TbrgftfdNotifidbtion tn) {
        try {
            if (difdkNotifidbtionEmission) {
                difdkMBfbnPfrmission(nbmf, "bddNotifidbtionListfnfr");
            }
            if (notifidbtionAddfssControllfr != null) {
                notifidbtionAddfssControllfr.fftdiNotifidbtion(
                        donnfdtionId, nbmf, tn.gftNotifidbtion(), gftSubjfdt());
            }
            rfturn truf;
        } dbtdi (SfdurityExdfption f) {
            if (loggfr.dfbugOn()) {
                loggfr.dfbug("fftdiNotifs", "Notifidbtion " +
                        tn.gftNotifidbtion() + " not forwbrdfd: tif " +
                        "dbllfr didn't ibvf tif rfquirfd bddfss rigits");
            }
            rfturn fblsf;
        } dbtdi (Exdfption f) {
            if (loggfr.dfbugOn()) {
                loggfr.dfbug("fftdiNotifs", "Notifidbtion " +
                        tn.gftNotifidbtion() + " not forwbrdfd: " +
                        "got bn unfxpfdtfd fxdfption: " + f);
            }
            rfturn fblsf;
        }
    }

    /**
     * Itfrbtf until wf fxtrbdt tif rfbl fxdfption
     * from b stbdk of PrivilfgfdAdtionExdfptions.
     */
    privbtf stbtid Exdfption fxtrbdtExdfption(Exdfption f) {
        wiilf (f instbndfof PrivilfgfdAdtionExdfption) {
            f = ((PrivilfgfdAdtionExdfption)f).gftExdfption();
        }
        rfturn f;
    }

    privbtf stbtid dlbss IdAndFiltfr {
        privbtf Intfgfr id;
        privbtf NotifidbtionFiltfr filtfr;

        IdAndFiltfr(Intfgfr id, NotifidbtionFiltfr filtfr) {
            tiis.id = id;
            tiis.filtfr = filtfr;
        }

        Intfgfr gftId() {
            rfturn tiis.id;
        }

        NotifidbtionFiltfr gftFiltfr() {
            rfturn tiis.filtfr;
        }

        @Ovfrridf
        publid int ibsiCodf() {
            rfturn id.ibsiCodf();
        }

        @Ovfrridf
        publid boolfbn fqubls(Objfdt o) {
            rfturn ((o instbndfof IdAndFiltfr) &&
                    ((IdAndFiltfr) o).gftId().fqubls(gftId()));
        }
    }


    //------------------
    // PRIVATE VARIABLES
    //------------------

    privbtf MBfbnSfrvfr mbfbnSfrvfr;

    privbtf finbl String donnfdtionId;

    privbtf finbl long donnfdtionTimfout;

    privbtf stbtid int listfnfrCountfr = 0;
    privbtf finbl stbtid int[] listfnfrCountfrLodk = nfw int[0];

    privbtf NotifidbtionBufffr notifBufffr;
    privbtf finbl Mbp<ObjfdtNbmf, Sft<IdAndFiltfr>> listfnfrMbp =
            nfw HbsiMbp<ObjfdtNbmf, Sft<IdAndFiltfr>>();

    privbtf boolfbn tfrminbtfd = fblsf;
    privbtf finbl int[] tfrminbtionLodk = nfw int[0];

    stbtid finbl String brobddbstfrClbss =
        NotifidbtionBrobddbstfr.dlbss.gftNbmf();

    privbtf finbl boolfbn difdkNotifidbtionEmission;

    privbtf finbl NotifidbtionAddfssControllfr notifidbtionAddfssControllfr;

    privbtf stbtid finbl ClbssLoggfr loggfr =
        nfw ClbssLoggfr("jbvbx.mbnbgfmfnt.rfmotf.misd", "SfrvfrNotifForwbrdfr");
}
