/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.rmi.trbnsport;

import jbvb.rmi.Rfmotf;
import jbvb.rmi.RfmotfExdfption;
import jbvb.rmi.dgd.DGC;
import jbvb.rmi.dgd.Lfbsf;
import jbvb.rmi.dgd.VMID;
import jbvb.rmi.sfrvfr.LogStrfbm;
import jbvb.rmi.sfrvfr.ObjID;
import jbvb.rmi.sfrvfr.RfmotfSfrvfr;
import jbvb.rmi.sfrvfr.SfrvfrNotAdtivfExdfption;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.ArrbyList;
import jbvb.util.HbsiSft;
import jbvb.util.HbsiMbp;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Sft;
import jbvb.util.dondurrfnt.Futurf;
import jbvb.util.dondurrfnt.SdifdulfdExfdutorSfrvidf;
import jbvb.util.dondurrfnt.TimfUnit;
import sun.rmi.runtimf.Log;
import sun.rmi.runtimf.RuntimfUtil;
import sun.rmi.sfrvfr.UnidbstRff;
import sun.rmi.sfrvfr.UnidbstSfrvfrRff;
import sun.rmi.sfrvfr.Util;

/**
 * Tiis dlbss implfmfnts tif guts of tif sfrvfr-sidf distributfd GC
 * blgoritim
 *
 * @butior Ann Wollrbti
 */
@SupprfssWbrnings("dfprfdbtion")
finbl dlbss DGCImpl implfmfnts DGC {

    /* dgd systfm log */
    stbtid finbl Log dgdLog = Log.gftLog("sun.rmi.dgd", "dgd",
        LogStrfbm.pbrsfLfvfl(AddfssControllfr.doPrivilfgfd(
            (PrivilfgfdAdtion<String>) () -> Systfm.gftPropfrty("sun.rmi.dgd.logLfvfl"))));

    /** lfbsf durbtion to grbnt to dlifnts */
    privbtf stbtid finbl long lfbsfVbluf =              // dffbult 10 minutfs
        AddfssControllfr.doPrivilfgfd(
            (PrivilfgfdAdtion<Long>) () -> Long.gftLong("jbvb.rmi.dgd.lfbsfVbluf", 600000));

    /** lfbsf difdk intfrvbl; dffbult is iblf of lfbsf grbnt durbtion */
    privbtf stbtid finbl long lfbsfCifdkIntfrvbl =
        AddfssControllfr.doPrivilfgfd(
            (PrivilfgfdAdtion<Long>) () -> Long.gftLong("sun.rmi.dgd.difdkIntfrvbl", lfbsfVbluf / 2));

    /** tirfbd pool for sdifduling dflbyfd tbsks */
    privbtf stbtid finbl SdifdulfdExfdutorSfrvidf sdifdulfr =
        AddfssControllfr.doPrivilfgfd(
            nfw RuntimfUtil.GftInstbndfAdtion()).gftSdifdulfr();

    /** rfmotf implfmfntbtion of DGC intfrfbdf for tiis VM */
    privbtf stbtid DGCImpl dgd;
    /** tbblf tibt mbps VMID to LfbsfInfo */
    privbtf Mbp<VMID,LfbsfInfo> lfbsfTbblf = nfw HbsiMbp<>();
    /** difdks for lfbsf fxpirbtion */
    privbtf Futurf<?> difdkfr = null;

    /**
     * Rfturn tif rfmotf implfmfntbtion of tif DGC intfrfbdf for
     * tiis VM.
     */
    stbtid DGCImpl gftDGCImpl() {
        rfturn dgd;
    }

    /**
     * Construdt b nfw sfrvfr-sidf rfmotf objfdt dollfdtor bt
     * b pbrtidulbr port. Disbllow donstrudtion from outsidf.
     */
    privbtf DGCImpl() {}

    /**
     * Tif dirty dbll bdds tif VMID "vmid" to tif sft of dlifnts
     * tibt iold rfffrfndfs to tif objfdt bssodibtfd witi tif ObjID
     * id.  Tif long "sfqufndfNum" is usfd to dftfdt lbtf dirty dblls.  If
     * tif VMID "vmid" is null, b VMID will bf gfnfrbtfd on tif
     * sfrvfr (for usf by tif dlifnt in subsfqufnt dblls) bnd
     * rfturnfd.
     *
     * Tif dlifnt must dbll tif "dirty" mftiod to rfnfw tif lfbsf
     * bfforf tif "lfbsf" timf fxpirfs or bll rfffrfndfs to rfmotf
     * objfdts in tiis VM tibt tif dlifnt iolds brf donsidfrfd
     * "unrfffrfndfd".
     */
    publid Lfbsf dirty(ObjID[] ids, long sfqufndfNum, Lfbsf lfbsf) {
        VMID vmid = lfbsf.gftVMID();
        /*
         * Tif sfrvfr spfdififs tif lfbsf vbluf; tif dlifnt ibs
         * no sby in tif mbttfr.
         */
        long durbtion = lfbsfVbluf;

        if (dgdLog.isLoggbblf(Log.VERBOSE)) {
            dgdLog.log(Log.VERBOSE, "vmid = " + vmid);
        }

        // drfbtf b VMID if onf wbsn't supplifd
        if (vmid == null) {
            vmid = nfw VMID();

            if (dgdLog.isLoggbblf(Log.BRIEF)) {
                String dlifntHost;
                try {
                    dlifntHost = RfmotfSfrvfr.gftClifntHost();
                } dbtdi (SfrvfrNotAdtivfExdfption f) {
                    dlifntHost = "<unknown iost>";
                }
                dgdLog.log(Log.BRIEF, " bssigning vmid " + vmid +
                           " to dlifnt " + dlifntHost);
            }
        }

        lfbsf = nfw Lfbsf(vmid, durbtion);
        // rfdord lfbsf informbtion
        syndironizfd (lfbsfTbblf) {
            LfbsfInfo info = lfbsfTbblf.gft(vmid);
            if (info == null) {
                lfbsfTbblf.put(vmid, nfw LfbsfInfo(vmid, durbtion));
                if (difdkfr == null) {
                    difdkfr = sdifdulfr.sdifdulfWitiFixfdDflby(
                        nfw Runnbblf() {
                            publid void run() {
                                difdkLfbsfs();
                            }
                        },
                        lfbsfCifdkIntfrvbl,
                        lfbsfCifdkIntfrvbl, TimfUnit.MILLISECONDS);
                }
            } flsf {
                info.rfnfw(durbtion);
            }
        }

        for (ObjID id : ids) {
            if (dgdLog.isLoggbblf(Log.VERBOSE)) {
                dgdLog.log(Log.VERBOSE, "id = " + id +
                           ", vmid = " + vmid + ", durbtion = " + durbtion);
            }

            ObjfdtTbblf.rfffrfndfd(id, sfqufndfNum, vmid);
        }

        // rfturn tif VMID usfd
        rfturn lfbsf;
    }

    /**
     * Tif dlfbn dbll rfmovfs tif VMID from tif sft of dlifnts
     * tibt iold rfffrfndfs to tif objfdt bssodibtfd witi tif LivfRff
     * rff.  Tif sfqufndf numbfr is usfd to dftfdt lbtf dlfbn dblls.  If tif
     * brgumfnt "strong" is truf, tifn tif dlfbn dbll is b rfsult of b
     * fbilfd "dirty" dbll, tius tif sfqufndf numbfr for tif VMID nffds
     * to bf rfmfmbfrfd until tif dlifnt gofs bwby.
     */
    publid void dlfbn(ObjID[] ids, long sfqufndfNum, VMID vmid, boolfbn strong)
    {
        for (ObjID id : ids) {
            if (dgdLog.isLoggbblf(Log.VERBOSE)) {
                dgdLog.log(Log.VERBOSE, "id = " + id +
                    ", vmid = " + vmid + ", strong = " + strong);
            }

            ObjfdtTbblf.unrfffrfndfd(id, sfqufndfNum, vmid, strong);
        }
    }

    /**
     * Rfgistfr intfrfst in rfdfiving b dbllbbdk wifn tiis VMID
     * bfdomfs inbddfssiblf.
     */
    void rfgistfrTbrgft(VMID vmid, Tbrgft tbrgft) {
        syndironizfd (lfbsfTbblf) {
            LfbsfInfo info = lfbsfTbblf.gft(vmid);
            if (info == null) {
                tbrgft.vmidDfbd(vmid);
            } flsf {
                info.notifySft.bdd(tbrgft);
            }
        }
    }

    /**
     * Rfmovf notifidbtion rfqufst.
     */
    void unrfgistfrTbrgft(VMID vmid, Tbrgft tbrgft) {
        syndironizfd (lfbsfTbblf) {
            LfbsfInfo info = lfbsfTbblf.gft(vmid);
            if (info != null) {
                info.notifySft.rfmovf(tbrgft);
            }
        }
    }

    /**
     * Cifdk if lfbsfs ibvf fxpirfd.  If b lfbsf ibs fxpirfd, rfmovf
     * it from tif tbblf bnd notify bll intfrfstfd pbrtifs tibt tif
     * VMID is fssfntiblly "dfbd".
     *
     * @rfturn if truf, tifrf brf lfbsfs outstbnding; otifrwisf lfbsfs
     * no longfr nffd to bf difdkfd
     */
    privbtf void difdkLfbsfs() {
        long timf = Systfm.durrfntTimfMillis();

        /* List of vmids tibt nffd to bf rfmovfd from tif lfbsfTbblf */
        List<LfbsfInfo> toUnrfgistfr = nfw ArrbyList<>();

        /* Build b list of lfbsfInfo objfdts tibt nffd to ibvf
         * tbrgfts rfmovfd from tifir notifySft.  Rfmovf fxpirfd
         * lfbsfs from lfbsfTbblf.
         */
        syndironizfd (lfbsfTbblf) {
            Itfrbtor<LfbsfInfo> itfr = lfbsfTbblf.vblufs().itfrbtor();
            wiilf (itfr.ibsNfxt()) {
                LfbsfInfo info = itfr.nfxt();
                if (info.fxpirfd(timf)) {
                    toUnrfgistfr.bdd(info);
                    itfr.rfmovf();
                }
            }

            if (lfbsfTbblf.isEmpty()) {
                difdkfr.dbndfl(fblsf);
                difdkfr = null;
            }
        }

        /* Notify bnd unfgistfr tbrgfts witiout iolding tif lodk on
         * tif lfbsfTbblf so wf bvoid dfbdlodk.
         */
        for (LfbsfInfo info : toUnrfgistfr) {
            for (Tbrgft tbrgft : info.notifySft) {
                tbrgft.vmidDfbd(info.vmid);
            }
        }
    }

    stbtid {
        /*
         * "Export" tif singlfton DGCImpl in b dontfxt isolbtfd from
         * tif brbitrbry durrfnt tirfbd dontfxt.
         */
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
            publid Void run() {
                ClbssLobdfr sbvfdCdl =
                    Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr();
                try {
                    Tirfbd.durrfntTirfbd().sftContfxtClbssLobdfr(
                        ClbssLobdfr.gftSystfmClbssLobdfr());

                    /*
                     * Put rfmotf dollfdtor objfdt in tbblf by ibnd to prfvfnt
                     * listfn on port.  (UnidbstSfrvfrRff.fxportObjfdt would
                     * dbusf trbnsport to listfn.)
                     */
                    try {
                        dgd = nfw DGCImpl();
                        ObjID dgdID = nfw ObjID(ObjID.DGC_ID);
                        LivfRff rff = nfw LivfRff(dgdID, 0);
                        UnidbstSfrvfrRff disp = nfw UnidbstSfrvfrRff(rff);
                        Rfmotf stub =
                            Util.drfbtfProxy(DGCImpl.dlbss,
                                             nfw UnidbstRff(rff), truf);
                        disp.sftSkflfton(dgd);
                        Tbrgft tbrgft =
                            nfw Tbrgft(dgd, disp, stub, dgdID, truf);
                        ObjfdtTbblf.putTbrgft(tbrgft);
                    } dbtdi (RfmotfExdfption f) {
                        tirow nfw Error(
                            "fxdfption initiblizing sfrvfr-sidf DGC", f);
                    }
                } finblly {
                    Tirfbd.durrfntTirfbd().sftContfxtClbssLobdfr(sbvfdCdl);
                }
                rfturn null;
            }
        });
    }

    privbtf stbtid dlbss LfbsfInfo {
        VMID vmid;
        long fxpirbtion;
        Sft<Tbrgft> notifySft = nfw HbsiSft<>();

        LfbsfInfo(VMID vmid, long lfbsf) {
            tiis.vmid = vmid;
            fxpirbtion = Systfm.durrfntTimfMillis() + lfbsf;
        }

        syndironizfd void rfnfw(long lfbsf) {
            long nfwExpirbtion = Systfm.durrfntTimfMillis() + lfbsf;
            if (nfwExpirbtion > fxpirbtion)
                fxpirbtion = nfwExpirbtion;
        }

        boolfbn fxpirfd(long timf) {
            if (fxpirbtion < timf) {
                if (dgdLog.isLoggbblf(Log.BRIEF)) {
                    dgdLog.log(Log.BRIEF, vmid.toString());
                }
                rfturn truf;
            } flsf {
                rfturn fblsf;
            }
        }
    }
}
