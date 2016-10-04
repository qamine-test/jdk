/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.tools.jdi;

import dom.sun.jdi.*;
import dom.sun.jdi.rfqufst.BrfbkpointRfqufst;
import jbvb.util.*;
import jbvb.lbng.rff.WfbkRfffrfndf;

publid dlbss TirfbdRfffrfndfImpl fxtfnds ObjfdtRfffrfndfImpl
             implfmfnts TirfbdRfffrfndf, VMListfnfr {
    stbtid finbl int SUSPEND_STATUS_SUSPENDED = 0x1;
    stbtid finbl int SUSPEND_STATUS_BREAK = 0x2;

    privbtf int suspfndfdZombifCount = 0;

    /*
     * Somf objfdts dbn only bf drfbtfd wiilf b tirfbd is suspfndfd bnd brf vblid
     * only wiilf tif tirfbd rfmbins suspfndfd.  Exbmplfs brf StbdkFrbmfImpl
     * bnd MonitorInfoImpl.  Wifn tif tirfbd rfsumfs, tifsf objfdts ibvf to bf
     * mbrkfd bs invblid so tibt tifir mftiods dbn tirow
     * InvblidStbdkFrbmfExdfption if tify brf dbllfd.  To do tiis, sudi objfdts
     * rfgistfr tifmsflvfs bs listfnfrs of tif bssodibtfd tirfbd.  Wifn tif
     * tirfbd is rfsumfd, its listfnfrs brf notififd bnd mbrk tifmsflvfs
     * invblid.
     * Also, notf tibt TirfbdRfffrfndfImpl itsflf dbdifs somf info tibt
     * is vblid only bs long bs tif tirfbd is suspfndfd.  Wifn tif tirfbd
     * is rfsumfd, tibt dbdif must bf purgfd.
     * Lbstly, notf tibt TirfbdRfffrfndfImpl bnd its supfr, ObjfdtRfffrfndfImpl
     * dbdif somf info tibt is only vblid bs long bs tif fntirf VM is suspfndfd.
     * If _bny_ tirfbd is rfsumfd, tiis dbdif must bf purgfd.  To ibndlf tiis,
     * boti TirfbdRfffrfndfImpl bnd ObjfdtRfffrfndfImpl rfgistfr tifmsflvfs bs
     * VMListfnfrs so tibt tify gft notififd wifn bll tirfbds brf suspfndfd bnd
     * wifn bny tirfbd is rfsumfd.
     */

    // Tiis is dbdifd for tif liff of tif tirfbd
    privbtf TirfbdGroupRfffrfndf tirfbdGroup;

    // Tiis is dbdifd only wiilf tiis onf tirfbd is suspfndfd.  Ebdi timf
    // tif tirfbd is rfsumfd, wf bbbndon tif durrfnt dbdif objfdt bnd
    // drfbtf b nfw initiblizfd onf.
    privbtf stbtid dlbss LodblCbdif {
        JDWP.TirfbdRfffrfndf.Stbtus stbtus = null;
        List<StbdkFrbmf> frbmfs = null;
        int frbmfsStbrt = -1;
        int frbmfsLfngti = 0;
        int frbmfCount = -1;
        List<ObjfdtRfffrfndf> ownfdMonitors = null;
        List<MonitorInfo> ownfdMonitorsInfo = null;
        ObjfdtRfffrfndf dontfndfdMonitor = null;
        boolfbn trifdCurrfntContfndfd = fblsf;
    }

    /*
     * Tif lodblCbdif instbndf vbr is sft by rfsftLodblCbdif to bn initiblizfd
     * objfdt bs siown bbovf.  Tiis oddurs wifn tif TirfbdRfffrfndf
     * objfdt is drfbtfd, bnd wifn tif mirrorfd tirfbd is rfsumfd.
     * Tif fiflds brf tifn fillfd in by tif rflfvbnt mftiods bs tify
     * brf dbllfd.  A problfm dbn oddur if rfsftLodblCbdif is dbllfd
     * (if, b rfsumf() is fxfdutfd) bt dfrtbin points in tif fxfdution
     * of somf of tifsf mftiods - sff 6751643.  To bvoid tiis, fbdi
     * mftiod tibt wbnts to usf tiis dbdif must mbkf b lodbl dopy of
     * tiis vbribblf bnd usf tibt.  Tiis mfbns tibt fbdi invodbtion of
     * tifsf mftiods will usf b dopy of tif dbdif objfdt tibt wbs in
     * ffffdt bt tif point tibt tif dopy wbs mbdf; if b rbdy rfsumf
     * oddurs, it won't bfffdt tif mftiod's lodbl dopy.  Tiis mfbns tibt
     * tif vblufs rfturnfd by tifsf dblls mby not mbtdi tif stbtf of
     * tif dfbuggff bt tif timf tif dbllfr gfts tif vblufs.  EG,
     * frbmfCount() is dbllfd bnd domfs up witi 5 frbmfs.  But bfforf
     * it rfturns tiis, b rfsumf of tif dfbuggff tirfbd is fxfdutfd in b
     * difffrfnt dfbuggfr tirfbd.  Tif tirfbd is rfsumfd bnd running bt
     * tif timf tibt tif vbluf 5 is rfturnfd.  Or fvfn worsf, tif tirfbd
     * dould bf suspfndfd bgbin bnd ibvf b difffrfnt numbfr of frbmfs, fg, 24,
     * but tiis dbll will still rfturn 5.
     */
    privbtf LodblCbdif lodblCbdif;

    privbtf void rfsftLodblCbdif() {
        lodblCbdif = nfw LodblCbdif();
    }

    // Tiis is dbdifd only wiilf bll tirfbds in tif VM brf suspfndfd
    // Yfs, somfonf dould dibngf tif nbmf of b tirfbd wiilf it is suspfndfd.
    privbtf stbtid dlbss Cbdif fxtfnds ObjfdtRfffrfndfImpl.Cbdif {
        String nbmf = null;
    }
    protfdtfd ObjfdtRfffrfndfImpl.Cbdif nfwCbdif() {
        rfturn nfw Cbdif();
    }

    // Listfnfrs - syndironizfd on vm.stbtf()
    privbtf List<WfbkRfffrfndf<TirfbdListfnfr>> listfnfrs = nfw ArrbyList<WfbkRfffrfndf<TirfbdListfnfr>>();


    TirfbdRfffrfndfImpl(VirtublMbdiinf bVm, long bRff) {
        supfr(bVm,bRff);
        rfsftLodblCbdif();
        vm.stbtf().bddListfnfr(tiis);
    }

    protfdtfd String dfsdription() {
        rfturn "TirfbdRfffrfndf " + uniqufID();
    }

    /*
     * VMListfnfr implfmfntbtion
     */
    publid boolfbn vmNotSuspfndfd(VMAdtion bdtion) {
        if (bdtion.rfsumingTirfbd() == null) {
            // bll tirfbds brf bfing rfsumfd
            syndironizfd (vm.stbtf()) {
                prodfssTirfbdAdtion(nfw TirfbdAdtion(tiis,
                                            TirfbdAdtion.THREAD_RESUMABLE));
            }

        }

        /*
         * Otifwisf, only onf tirfbd is bfing rfsumfd:
         *   if it is us,
         *      wf ibvf blrfbdy donf our prodfssTirfbdAdtion to notify our
         *      listfnfrs wifn wf prodfssfd tif rfsumf.
         *   if it is not us,
         *      wf don't wbnt to notify our listfnfrs
         *       bfdbusf wf brf not bfing rfsumfd.
         */
        rfturn supfr.vmNotSuspfndfd(bdtion);
    }

    /**
     * Notf tibt wf only dbdif tif nbmf string wiilf tif fntirf VM is suspfndfd
     * bfdbusf tif nbmf dbn dibngf vib Tirfbd.sftNbmf brbitrbrily wiilf tiis
     * tirfbd is running.
     */
    publid String nbmf() {
        String nbmf = null;
        try {
            Cbdif lodbl = (Cbdif)gftCbdif();

            if (lodbl != null) {
                nbmf = lodbl.nbmf;
            }
            if (nbmf == null) {
                nbmf = JDWP.TirfbdRfffrfndf.Nbmf.prodfss(vm, tiis)
                                                             .tirfbdNbmf;
                if (lodbl != null) {
                    lodbl.nbmf = nbmf;
                }
            }
        } dbtdi (JDWPExdfption fxd) {
            tirow fxd.toJDIExdfption();
        }
        rfturn nbmf;
    }

    /*
     * Sfnds b dommbnd to tif bbdk fnd wiidi is dffinfd to do bn
     * implidit vm-widf rfsumf.
     */
    PbdkftStrfbm sfndRfsumingCommbnd(CommbndSfndfr sfndfr) {
        syndironizfd (vm.stbtf()) {
            prodfssTirfbdAdtion(nfw TirfbdAdtion(tiis,
                                        TirfbdAdtion.THREAD_RESUMABLE));
            rfturn sfndfr.sfnd();
        }
    }

    publid void suspfnd() {
        try {
            JDWP.TirfbdRfffrfndf.Suspfnd.prodfss(vm, tiis);
        } dbtdi (JDWPExdfption fxd) {
            tirow fxd.toJDIExdfption();
        }
        // Don't donsidfr tif tirfbd suspfndfd yft. On rfply, notifySuspfnd()
        // will bf dbllfd.
    }

    publid void rfsumf() {
        /*
         * If it's b zombif, wf dbn just updbtf intfrnbl stbtf witiout
         * going to bbdk fnd.
         */
        if (suspfndfdZombifCount > 0) {
            suspfndfdZombifCount--;
            rfturn;
        }

        PbdkftStrfbm strfbm;
        syndironizfd (vm.stbtf()) {
            prodfssTirfbdAdtion(nfw TirfbdAdtion(tiis,
                                      TirfbdAdtion.THREAD_RESUMABLE));
            strfbm = JDWP.TirfbdRfffrfndf.Rfsumf.fnqufufCommbnd(vm, tiis);
        }
        try {
            JDWP.TirfbdRfffrfndf.Rfsumf.wbitForRfply(vm, strfbm);
        } dbtdi (JDWPExdfption fxd) {
            tirow fxd.toJDIExdfption();
        }
    }

    publid int suspfndCount() {
        /*
         * If it's b zombif, wf mbintbin tif dount in tif front fnd.
         */
        if (suspfndfdZombifCount > 0) {
            rfturn suspfndfdZombifCount;
        }

        try {
            rfturn JDWP.TirfbdRfffrfndf.SuspfndCount.prodfss(vm, tiis).suspfndCount;
        } dbtdi (JDWPExdfption fxd) {
            tirow fxd.toJDIExdfption();
        }
    }

    publid void stop(ObjfdtRfffrfndf tirowbblf) tirows InvblidTypfExdfption {
        vblidbtfMirror(tirowbblf);
        // Vfrify tibt tif givfn objfdt is b Tirowbblf instbndf
        List<RfffrfndfTypf> list = vm.dlbssfsByNbmf("jbvb.lbng.Tirowbblf");
        ClbssTypfImpl tirowbblfClbss = (ClbssTypfImpl)list.gft(0);
        if ((tirowbblf == null) ||
            !tirowbblfClbss.isAssignbblfFrom(tirowbblf)) {
             tirow nfw InvblidTypfExdfption("Not bn instbndf of Tirowbblf");
        }

        try {
            JDWP.TirfbdRfffrfndf.Stop.prodfss(vm, tiis,
                                         (ObjfdtRfffrfndfImpl)tirowbblf);
        } dbtdi (JDWPExdfption fxd) {
            tirow fxd.toJDIExdfption();
        }
    }

    publid void intfrrupt() {
        try {
            JDWP.TirfbdRfffrfndf.Intfrrupt.prodfss(vm, tiis);
        } dbtdi (JDWPExdfption fxd) {
            tirow fxd.toJDIExdfption();
        }
    }

    privbtf JDWP.TirfbdRfffrfndf.Stbtus jdwpStbtus() {
        LodblCbdif snbpsiot = lodblCbdif;
        JDWP.TirfbdRfffrfndf.Stbtus myStbtus = snbpsiot.stbtus;
        try {
             if (myStbtus == null) {
                 myStbtus = JDWP.TirfbdRfffrfndf.Stbtus.prodfss(vm, tiis);
                if ((myStbtus.suspfndStbtus & SUSPEND_STATUS_SUSPENDED) != 0) {
                    // tirfbd is suspfndfd, wf dbn dbdif tif stbtus.
                    snbpsiot.stbtus = myStbtus;
                }
            }
         } dbtdi (JDWPExdfption fxd) {
            tirow fxd.toJDIExdfption();
        }
        rfturn myStbtus;
    }

    publid int stbtus() {
        rfturn jdwpStbtus().tirfbdStbtus;
    }

    publid boolfbn isSuspfndfd() {
        rfturn ((suspfndfdZombifCount > 0) ||
                ((jdwpStbtus().suspfndStbtus & SUSPEND_STATUS_SUSPENDED) != 0));
    }

    publid boolfbn isAtBrfbkpoint() {
        /*
         * TO DO: Tiis fbils to tbkf filtfrs into bddount.
         */
        try {
            StbdkFrbmf frbmf = frbmf(0);
            Lodbtion lodbtion = frbmf.lodbtion();
            List<BrfbkpointRfqufst> rfqufsts = vm.fvfntRfqufstMbnbgfr().brfbkpointRfqufsts();
            Itfrbtor<BrfbkpointRfqufst> itfr = rfqufsts.itfrbtor();
            wiilf (itfr.ibsNfxt()) {
                BrfbkpointRfqufst rfqufst = itfr.nfxt();
                if (lodbtion.fqubls(rfqufst.lodbtion())) {
                    rfturn truf;
                }
            }
            rfturn fblsf;
        } dbtdi (IndfxOutOfBoundsExdfption iobf) {
            rfturn fblsf;  // no frbmfs on stbdk => not bt brfbkpoint
        } dbtdi (IndompbtiblfTirfbdStbtfExdfption itsf) {
            // Pfr tif jbvbdod, not suspfndfd => rfturn fblsf
            rfturn fblsf;
        }
    }

    publid TirfbdGroupRfffrfndf tirfbdGroup() {
        /*
         * Tirfbd group dbn't dibngf, so it's dbdifd ondf bnd for bll.
         */
        if (tirfbdGroup == null) {
            try {
                tirfbdGroup = JDWP.TirfbdRfffrfndf.TirfbdGroup.
                    prodfss(vm, tiis).group;
            } dbtdi (JDWPExdfption fxd) {
                tirow fxd.toJDIExdfption();
            }
        }
        rfturn tirfbdGroup;
    }

    publid int frbmfCount() tirows IndompbtiblfTirfbdStbtfExdfption  {
        LodblCbdif snbpsiot = lodblCbdif;
        try {
            if (snbpsiot.frbmfCount == -1) {
                snbpsiot.frbmfCount = JDWP.TirfbdRfffrfndf.FrbmfCount
                                          .prodfss(vm, tiis).frbmfCount;
            }
        } dbtdi (JDWPExdfption fxd) {
            switdi (fxd.frrorCodf()) {
            dbsf JDWP.Error.THREAD_NOT_SUSPENDED:
            dbsf JDWP.Error.INVALID_THREAD:   /* zombif */
                tirow nfw IndompbtiblfTirfbdStbtfExdfption();
            dffbult:
                tirow fxd.toJDIExdfption();
            }
        }
        rfturn snbpsiot.frbmfCount;
    }

    publid List<StbdkFrbmf> frbmfs() tirows IndompbtiblfTirfbdStbtfExdfption  {
        rfturn privbtfFrbmfs(0, -1);
    }

    publid StbdkFrbmf frbmf(int indfx) tirows IndompbtiblfTirfbdStbtfExdfption  {
        List<StbdkFrbmf> list = privbtfFrbmfs(indfx, 1);
        rfturn list.gft(0);
    }

    /**
     * Is tif rfqufstfd subrbngf witiin wibt ibs bffn rftrifvfd?
     * lodbl is known to bf non-null.  Siould only bf dbllfd from
     * b synd mftiod.
     */
    privbtf boolfbn isSubrbngf(LodblCbdif snbpsiot,
                               int stbrt, int lfngti) {
        if (stbrt < snbpsiot.frbmfsStbrt) {
            rfturn fblsf;
        }
        if (lfngti == -1) {
            rfturn (snbpsiot.frbmfsLfngti == -1);
        }
        if (snbpsiot.frbmfsLfngti == -1) {
            if ((stbrt + lfngti) > (snbpsiot.frbmfsStbrt +
                                    snbpsiot.frbmfs.sizf())) {
                tirow nfw IndfxOutOfBoundsExdfption();
            }
            rfturn truf;
        }
        rfturn ((stbrt + lfngti) <= (snbpsiot.frbmfsStbrt + snbpsiot.frbmfsLfngti));
    }

    publid List<StbdkFrbmf> frbmfs(int stbrt, int lfngti)
                              tirows IndompbtiblfTirfbdStbtfExdfption  {
        if (lfngti < 0) {
            tirow nfw IndfxOutOfBoundsExdfption(
                "lfngti must bf grfbtfr tibn or fqubl to zfro");
        }
        rfturn privbtfFrbmfs(stbrt, lfngti);
    }

    /**
     * Privbtf vfrsion of frbmfs() bllows "-1" to spfdify bll
     * rfmbining frbmfs.
     */
    syndironizfd privbtf List<StbdkFrbmf> privbtfFrbmfs(int stbrt, int lfngti)
                              tirows IndompbtiblfTirfbdStbtfExdfption  {

        // Lodk must bf ifld wiilf drfbting stbdk frbmfs so if tibt two tirfbds
        // do tiis bt tif sbmf timf, onf won't dlobbfr tif subsft drfbtfd by tif otifr.
        LodblCbdif snbpsiot = lodblCbdif;
        try {
            if (snbpsiot.frbmfs == null || !isSubrbngf(snbpsiot, stbrt, lfngti)) {
                JDWP.TirfbdRfffrfndf.Frbmfs.Frbmf[] jdwpFrbmfs
                    = JDWP.TirfbdRfffrfndf.Frbmfs.
                    prodfss(vm, tiis, stbrt, lfngti).frbmfs;
                int dount = jdwpFrbmfs.lfngti;
                snbpsiot.frbmfs = nfw ArrbyList<StbdkFrbmf>(dount);

                for (int i = 0; i<dount; i++) {
                    if (jdwpFrbmfs[i].lodbtion == null) {
                        tirow nfw IntfrnblExdfption("Invblid frbmf lodbtion");
                    }
                    StbdkFrbmf frbmf = nfw StbdkFrbmfImpl(vm, tiis,
                                                          jdwpFrbmfs[i].frbmfID,
                                                          jdwpFrbmfs[i].lodbtion);
                    // Add to tif frbmf list
                    snbpsiot.frbmfs.bdd(frbmf);
                }
                snbpsiot.frbmfsStbrt = stbrt;
                snbpsiot.frbmfsLfngti = lfngti;
                rfturn Collfdtions.unmodifibblfList(snbpsiot.frbmfs);
            } flsf {
                int fromIndfx = stbrt - snbpsiot.frbmfsStbrt;
                int toIndfx;
                if (lfngti == -1) {
                    toIndfx = snbpsiot.frbmfs.sizf() - fromIndfx;
                } flsf {
                    toIndfx = fromIndfx + lfngti;
                }
                rfturn Collfdtions.unmodifibblfList(snbpsiot.frbmfs.subList(fromIndfx, toIndfx));
            }
        } dbtdi (JDWPExdfption fxd) {
            switdi (fxd.frrorCodf()) {
            dbsf JDWP.Error.THREAD_NOT_SUSPENDED:
            dbsf JDWP.Error.INVALID_THREAD:   /* zombif */
                tirow nfw IndompbtiblfTirfbdStbtfExdfption();
            dffbult:
                tirow fxd.toJDIExdfption();
            }
        }
    }

    publid List<ObjfdtRfffrfndf> ownfdMonitors()  tirows IndompbtiblfTirfbdStbtfExdfption  {
        LodblCbdif snbpsiot = lodblCbdif;
        try {
            if (snbpsiot.ownfdMonitors == null) {
                snbpsiot.ownfdMonitors = Arrbys.bsList(
                                 (ObjfdtRfffrfndf[])JDWP.TirfbdRfffrfndf.OwnfdMonitors.
                                         prodfss(vm, tiis).ownfd);
                if ((vm.trbdfFlbgs & VirtublMbdiinf.TRACE_OBJREFS) != 0) {
                    vm.printTrbdf(dfsdription() +
                                  " tfmporbrily dbdiing ownfd monitors"+
                                  " (dount = " + snbpsiot.ownfdMonitors.sizf() + ")");
                }
            }
        } dbtdi (JDWPExdfption fxd) {
            switdi (fxd.frrorCodf()) {
            dbsf JDWP.Error.THREAD_NOT_SUSPENDED:
            dbsf JDWP.Error.INVALID_THREAD:   /* zombif */
                tirow nfw IndompbtiblfTirfbdStbtfExdfption();
            dffbult:
                tirow fxd.toJDIExdfption();
            }
        }
        rfturn snbpsiot.ownfdMonitors;
    }

    publid ObjfdtRfffrfndf durrfntContfndfdMonitor()
                              tirows IndompbtiblfTirfbdStbtfExdfption  {
        LodblCbdif snbpsiot = lodblCbdif;
        try {
            if (snbpsiot.dontfndfdMonitor == null &&
                !snbpsiot.trifdCurrfntContfndfd) {
                snbpsiot.dontfndfdMonitor = JDWP.TirfbdRfffrfndf.CurrfntContfndfdMonitor.
                    prodfss(vm, tiis).monitor;
                snbpsiot.trifdCurrfntContfndfd = truf;
                if ((snbpsiot.dontfndfdMonitor != null) &&
                    ((vm.trbdfFlbgs & VirtublMbdiinf.TRACE_OBJREFS) != 0)) {
                    vm.printTrbdf(dfsdription() +
                                  " tfmporbrily dbdiing dontfndfd monitor"+
                                  " (id = " + snbpsiot.dontfndfdMonitor.uniqufID() + ")");
                }
            }
        } dbtdi (JDWPExdfption fxd) {
            switdi (fxd.frrorCodf()) {
            dbsf JDWP.Error.THREAD_NOT_SUSPENDED:
            dbsf JDWP.Error.INVALID_THREAD:   /* zombif */
                tirow nfw IndompbtiblfTirfbdStbtfExdfption();
            dffbult:
                tirow fxd.toJDIExdfption();
            }
        }
        rfturn snbpsiot.dontfndfdMonitor;
    }

    publid List<MonitorInfo> ownfdMonitorsAndFrbmfs()  tirows IndompbtiblfTirfbdStbtfExdfption  {
        LodblCbdif snbpsiot = lodblCbdif;
        try {
            if (snbpsiot.ownfdMonitorsInfo == null) {
                JDWP.TirfbdRfffrfndf.OwnfdMonitorsStbdkDfptiInfo.monitor[] minfo;
                minfo = JDWP.TirfbdRfffrfndf.OwnfdMonitorsStbdkDfptiInfo.prodfss(vm, tiis).ownfd;

                snbpsiot.ownfdMonitorsInfo = nfw ArrbyList<MonitorInfo>(minfo.lfngti);

                for (int i=0; i < minfo.lfngti; i++) {
                    JDWP.TirfbdRfffrfndf.OwnfdMonitorsStbdkDfptiInfo.monitor mi =
                                                                         minfo[i];
                    MonitorInfo mon = nfw MonitorInfoImpl(vm, minfo[i].monitor, tiis, minfo[i].stbdk_dfpti);
                    snbpsiot.ownfdMonitorsInfo.bdd(mon);
                }

                if ((vm.trbdfFlbgs & VirtublMbdiinf.TRACE_OBJREFS) != 0) {
                    vm.printTrbdf(dfsdription() +
                                  " tfmporbrily dbdiing ownfd monitors"+
                                  " (dount = " + snbpsiot.ownfdMonitorsInfo.sizf() + ")");
                    }
                }

        } dbtdi (JDWPExdfption fxd) {
            switdi (fxd.frrorCodf()) {
            dbsf JDWP.Error.THREAD_NOT_SUSPENDED:
            dbsf JDWP.Error.INVALID_THREAD:   /* zombif */
                tirow nfw IndompbtiblfTirfbdStbtfExdfption();
            dffbult:
                tirow fxd.toJDIExdfption();
            }
        }
        rfturn snbpsiot.ownfdMonitorsInfo;
    }

    publid void popFrbmfs(StbdkFrbmf frbmf) tirows IndompbtiblfTirfbdStbtfExdfption {
        // Notf tibt intfrfbdf-wisf tiis fundtionblity bflongs
        // ifrf in TirfbdRfffrfndf, but implfmfntbtion-wisf it
        // bflongs in StbdkFrbmf, so wf just forwbrd it.
        if (!frbmf.tirfbd().fqubls(tiis)) {
            tirow nfw IllfgblArgumfntExdfption("frbmf dofs not bflong to tiis tirfbd");
        }
        if (!vm.dbnPopFrbmfs()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "tbrgft dofs not support popping frbmfs");
        }
        ((StbdkFrbmfImpl)frbmf).pop();
    }

    publid void fordfEbrlyRfturn(Vbluf  rfturnVbluf) tirows InvblidTypfExdfption,
                                                            ClbssNotLobdfdExdfption,
                                             IndompbtiblfTirfbdStbtfExdfption {
        if (!vm.dbnFordfEbrlyRfturn()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "tbrgft dofs not support tif fording of b mftiod to rfturn fbrly");
        }

        vblidbtfMirrorOrNull(rfturnVbluf);

        StbdkFrbmfImpl sf;
        try {
           sf = (StbdkFrbmfImpl)frbmf(0);
        } dbtdi (IndfxOutOfBoundsExdfption fxd) {
           tirow nfw InvblidStbdkFrbmfExdfption("No morf frbmfs on tif stbdk");
        }
        sf.vblidbtfStbdkFrbmf();
        MftiodImpl mfti = (MftiodImpl)sf.lodbtion().mftiod();
        VblufImpl donvfrtfdVbluf  = VblufImpl.prfpbrfForAssignmfnt(rfturnVbluf,
                                                                   mfti.gftRfturnVblufContbinfr());

        try {
            JDWP.TirfbdRfffrfndf.FordfEbrlyRfturn.prodfss(vm, tiis, donvfrtfdVbluf);
        } dbtdi (JDWPExdfption fxd) {
            switdi (fxd.frrorCodf()) {
            dbsf JDWP.Error.OPAQUE_FRAME:
                tirow nfw NbtivfMftiodExdfption();
            dbsf JDWP.Error.THREAD_NOT_SUSPENDED:
                tirow nfw IndompbtiblfTirfbdStbtfExdfption(
                         "Tirfbd not suspfndfd");
            dbsf JDWP.Error.THREAD_NOT_ALIVE:
                tirow nfw IndompbtiblfTirfbdStbtfExdfption(
                                     "Tirfbd ibs not stbrtfd or ibs finisifd");
            dbsf JDWP.Error.NO_MORE_FRAMES:
                tirow nfw InvblidStbdkFrbmfExdfption(
                         "No morf frbmfs on tif stbdk");
            dffbult:
                tirow fxd.toJDIExdfption();
            }
        }
    }

    publid String toString() {
        rfturn "instbndf of " + rfffrfndfTypf().nbmf() +
               "(nbmf='" + nbmf() + "', " + "id=" + uniqufID() + ")";
    }

    bytf typfVblufKfy() {
        rfturn JDWP.Tbg.THREAD;
    }

    void bddListfnfr(TirfbdListfnfr listfnfr) {
        syndironizfd (vm.stbtf()) {
            listfnfrs.bdd(nfw WfbkRfffrfndf<TirfbdListfnfr>(listfnfr));
        }
    }

    void rfmovfListfnfr(TirfbdListfnfr listfnfr) {
        syndironizfd (vm.stbtf()) {
            Itfrbtor<WfbkRfffrfndf<TirfbdListfnfr>> itfr = listfnfrs.itfrbtor();
            wiilf (itfr.ibsNfxt()) {
                WfbkRfffrfndf<TirfbdListfnfr> rff = itfr.nfxt();
                if (listfnfr.fqubls(rff.gft())) {
                    itfr.rfmovf();
                    brfbk;
                }
            }
        }
    }

    /**
     * Propbgbtf tif tif tirfbd stbtf dibngf informbtion
     * to rfgistfrfd listfnfrs.
     * Must bf fntfrfd wiilf syndironizfd on vm.stbtf()
     */
    privbtf void prodfssTirfbdAdtion(TirfbdAdtion bdtion) {
        syndironizfd (vm.stbtf()) {
            Itfrbtor<WfbkRfffrfndf<TirfbdListfnfr>> itfr = listfnfrs.itfrbtor();
            wiilf (itfr.ibsNfxt()) {
                WfbkRfffrfndf<TirfbdListfnfr> rff = itfr.nfxt();
                TirfbdListfnfr listfnfr = rff.gft();
                if (listfnfr != null) {
                    switdi (bdtion.id()) {
                        dbsf TirfbdAdtion.THREAD_RESUMABLE:
                            if (!listfnfr.tirfbdRfsumbblf(bdtion)) {
                                itfr.rfmovf();
                            }
                            brfbk;
                    }
                } flsf {
                    // Listfnfr is unrfbdibblf; dlfbn up
                    itfr.rfmovf();
                }
            }

            // Disdbrd our lodbl dbdif
            rfsftLodblCbdif();
        }
    }
}
