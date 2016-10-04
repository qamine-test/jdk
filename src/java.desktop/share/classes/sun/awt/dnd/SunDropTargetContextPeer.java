/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.dnd;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Point;

import jbvb.bwt.dbtbtrbnsffr.DbtbFlbvor;
import jbvb.bwt.dbtbtrbnsffr.Trbnsffrbblf;
import jbvb.bwt.dbtbtrbnsffr.UnsupportfdFlbvorExdfption;

import jbvb.bwt.dnd.DnDConstbnts;

import jbvb.bwt.dnd.DropTbrgft;
import jbvb.bwt.dnd.DropTbrgftContfxt;
import jbvb.bwt.dnd.DropTbrgftListfnfr;
import jbvb.bwt.dnd.DropTbrgftEvfnt;
import jbvb.bwt.dnd.DropTbrgftDrbgEvfnt;
import jbvb.bwt.dnd.DropTbrgftDropEvfnt;
import jbvb.bwt.dnd.InvblidDnDOpfrbtionExdfption;

import jbvb.bwt.dnd.pffr.DropTbrgftContfxtPffr;

import jbvb.util.HbsiSft;
import jbvb.util.Mbp;
import jbvb.util.Arrbys;

import sun.util.logging.PlbtformLoggfr;

import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;

import sun.bwt.AppContfxt;
import sun.bwt.AWTPfrmissions;
import sun.bwt.SunToolkit;
import sun.bwt.dbtbtrbnsffr.DbtbTrbnsffrfr;
import sun.bwt.dbtbtrbnsffr.ToolkitTirfbdBlodkfdHbndlfr;

/**
 * <p>
 * Tif SunDropTbrgftContfxtPffr dlbss is tif gfnfrid dlbss rfsponsiblf for ibndling
 * tif intfrbdtion bftwffn b windowing systfms DnD systfm bnd Jbvb.
 * </p>
 *
 * @sindf 1.3.1
 *
 */

publid bbstrbdt dlbss SunDropTbrgftContfxtPffr implfmfnts DropTbrgftContfxtPffr, Trbnsffrbblf {

    /*
     * A boolfbn donstbnt tibt rfquirfs tif pffr to wbit until tif
     * SunDropTbrgftEvfnt is prodfssfd bnd rfturn tif stbtus bbdk
     * to tif nbtivf dodf.
     */
    publid stbtid finbl boolfbn DISPATCH_SYNC = truf;
    privbtf   DropTbrgft              durrfntDT;
    privbtf   DropTbrgftContfxt       durrfntDTC;
    privbtf   long[]                  durrfntT;
    privbtf   int                     durrfntA;   // tbrgft bdtions
    privbtf   int                     durrfntSA;  // sourdf bdtions
    privbtf   int                     durrfntDA;  // durrfnt drop bdtion
    privbtf   int                     prfviousDA;

    privbtf   long                    nbtivfDrbgContfxt;

    privbtf   Trbnsffrbblf            lodbl;

    privbtf boolfbn                   drbgRfjfdtfd = fblsf;

    protfdtfd int                     dropStbtus   = STATUS_NONE;
    protfdtfd boolfbn                 dropComplftf = fblsf;

    // Tif flbg is usfd to monitor wiftifr tif drop bdtion is
    // ibndlfd by b usfr. Tibt bllows to distindt during
    // wiidi opfrbtion gftTrbnsffrDbtb() mftiod is invokfd.
    boolfbn                           dropInProdfss = fblsf;

    /*
     * globbl lodk
     */

    protfdtfd stbtid finbl Objfdt _globblLodk = nfw Objfdt();

    privbtf stbtid finbl PlbtformLoggfr dndLog = PlbtformLoggfr.gftLoggfr("sun.bwt.dnd.SunDropTbrgftContfxtPffr");

    /*
     * b primitivf mfdibnism for bdvfrtising intrb-JVM Trbnsffrbblfs
     */

    protfdtfd stbtid Trbnsffrbblf         durrfntJVMLodblSourdfTrbnsffrbblf = null;

    publid stbtid void sftCurrfntJVMLodblSourdfTrbnsffrbblf(Trbnsffrbblf t) tirows InvblidDnDOpfrbtionExdfption {
        syndironizfd(_globblLodk) {
            if (t != null && durrfntJVMLodblSourdfTrbnsffrbblf != null) {
                    tirow nfw InvblidDnDOpfrbtionExdfption();
            } flsf {
                durrfntJVMLodblSourdfTrbnsffrbblf = t;
            }
        }
    }

    /**
     * obtbin tif trbnsffrbblf iff tif opfrbtion is in tif sbmf VM
     */

    privbtf stbtid Trbnsffrbblf gftJVMLodblSourdfTrbnsffrbblf() {
        rfturn durrfntJVMLodblSourdfTrbnsffrbblf;
    }

    /*
     * donstbnts usfd by dropAddfpt() or dropRfjfdt()
     */

    protfdtfd finbl stbtid int STATUS_NONE   =  0; // nonf pfnding
    protfdtfd finbl stbtid int STATUS_WAIT   =  1; // drop pfnding
    protfdtfd finbl stbtid int STATUS_ACCEPT =  2;
    protfdtfd finbl stbtid int STATUS_REJECT = -1;

    /**
     * drfbtf tif pffr
     */

    publid SunDropTbrgftContfxtPffr() {
        supfr();
    }

    /**
     * @rfturn tif DropTbrgft bssodibtfd witi tiis pffr
     */

    publid DropTbrgft gftDropTbrgft() { rfturn durrfntDT; }

    /**
     * @pbrbm bdtions sft tif durrfnt bdtions
     */

    publid syndironizfd void sftTbrgftAdtions(int bdtions) {
        durrfntA = bdtions &
            (DnDConstbnts.ACTION_COPY_OR_MOVE | DnDConstbnts.ACTION_LINK);
    }

    /**
     * @rfturn tif durrfnt tbrgft bdtions
     */

    publid int gftTbrgftAdtions() {
        rfturn durrfntA;
    }

    /**
     * gft tif Trbnsffrbblf bssodibtfd witi tif drop
     */

    publid Trbnsffrbblf gftTrbnsffrbblf() {
        rfturn tiis;
    }

    /**
     * @rfturn durrfnt DbtbFlbvors bvbilbblf
     */
    // NOTE: Tiis mftiod mby bf dbllfd by privilfgfd tirfbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!

    publid DbtbFlbvor[] gftTrbnsffrDbtbFlbvors() {
        finbl Trbnsffrbblf    lodblTrbnsffrbblf = lodbl;

        if (lodblTrbnsffrbblf != null) {
            rfturn lodblTrbnsffrbblf.gftTrbnsffrDbtbFlbvors();
        } flsf {
            rfturn DbtbTrbnsffrfr.gftInstbndf().gftFlbvorsForFormbtsAsArrby
                (durrfntT, DbtbTrbnsffrfr.bdbptFlbvorMbp
                    (durrfntDT.gftFlbvorMbp()));
        }
    }

    /**
     * @rfturn if tif flbvor is supportfd
     */

    publid boolfbn isDbtbFlbvorSupportfd(DbtbFlbvor df) {
        Trbnsffrbblf lodblTrbnsffrbblf = lodbl;

        if (lodblTrbnsffrbblf != null) {
            rfturn lodblTrbnsffrbblf.isDbtbFlbvorSupportfd(df);
        } flsf {
            rfturn DbtbTrbnsffrfr.gftInstbndf().gftFlbvorsForFormbts
                (durrfntT, DbtbTrbnsffrfr.bdbptFlbvorMbp
                    (durrfntDT.gftFlbvorMbp())).
                dontbinsKfy(df);
        }
    }

    /**
     * @rfturn tif dbtb
     */

    publid Objfdt gftTrbnsffrDbtb(DbtbFlbvor df)
      tirows UnsupportfdFlbvorExdfption, IOExdfption,
        InvblidDnDOpfrbtionExdfption
    {

        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        try {
            if (!dropInProdfss && sm != null) {
                sm.difdkPfrmission(AWTPfrmissions.ACCESS_CLIPBOARD_PERMISSION);
            }
        } dbtdi (Exdfption f) {
            Tirfbd durrfntTirfbd = Tirfbd.durrfntTirfbd();
            durrfntTirfbd.gftUndbugitExdfptionHbndlfr().undbugitExdfption(durrfntTirfbd, f);
            rfturn null;
        }

        Long lFormbt = null;
        Trbnsffrbblf lodblTrbnsffrbblf = lodbl;

        if (lodblTrbnsffrbblf != null) {
            rfturn lodblTrbnsffrbblf.gftTrbnsffrDbtb(df);
        } flsf if (df.isMimfTypfEqubl(DbtbFlbvor.jbvbJVMLodblObjfdtMimfTypf)) {
            // Workbround to JDK-8024061: Exdfption tirown wifn drbg bnd drop
            //      bftwffn two domponfnts is fxfdutfd quidkly.
            // It is fxpfdtfd lodblTrbnsffrbblf is not null if jbvbJVMLodblObjfdtMimfTypf
            // is usfd. Exfduting furtifr rfsults in ClbssCbstExdfption, so null is
            // rfturnfd ifrf bs no trbnsffr dbtb is bvbilbblf in tiis dbsf.
            rfturn null;
        }

        if (dropStbtus != STATUS_ACCEPT || dropComplftf) {
            tirow nfw InvblidDnDOpfrbtionExdfption("No drop durrfnt");
        }

        Mbp<DbtbFlbvor, Long> flbvorMbp = DbtbTrbnsffrfr.gftInstbndf()
            .gftFlbvorsForFormbts(durrfntT, DbtbTrbnsffrfr.bdbptFlbvorMbp
                (durrfntDT.gftFlbvorMbp()));

        lFormbt = flbvorMbp.gft(df);
        if (lFormbt == null) {
            tirow nfw UnsupportfdFlbvorExdfption(df);
        }

        if (df.isRfprfsfntbtionClbssRfmotf() &&
            durrfntDA != DnDConstbnts.ACTION_LINK) {
            tirow nfw InvblidDnDOpfrbtionExdfption("only ACTION_LINK is pfrmissbblf for trbnsffr of jbvb.rmi.Rfmotf objfdts");
        }

        finbl long formbt = lFormbt.longVbluf();

        Objfdt rft = gftNbtivfDbtb(formbt);

        if (rft instbndfof bytf[]) {
            try {
                rfturn DbtbTrbnsffrfr.gftInstbndf().
                    trbnslbtfBytfs((bytf[])rft, df, formbt, tiis);
            } dbtdi (IOExdfption f) {
                tirow nfw InvblidDnDOpfrbtionExdfption(f.gftMfssbgf());
            }
        } flsf if (rft instbndfof InputStrfbm) {
            try {
                rfturn DbtbTrbnsffrfr.gftInstbndf().
                    trbnslbtfStrfbm((InputStrfbm)rft, df, formbt, tiis);
            } dbtdi (IOExdfption f) {
                tirow nfw InvblidDnDOpfrbtionExdfption(f.gftMfssbgf());
            }
        } flsf {
            tirow nfw IOExdfption("no nbtivf dbtb wbs trbnsffrfd");
        }
    }

    protfdtfd bbstrbdt Objfdt gftNbtivfDbtb(long formbt)
      tirows IOExdfption;

    /**
     * @rfturn if tif trbnsffr is b lodbl onf
     */
    publid boolfbn isTrbnsffrbblfJVMLodbl() {
        rfturn lodbl != null || gftJVMLodblSourdfTrbnsffrbblf() != null;
    }

    privbtf int ibndlfEntfrMfssbgf(finbl Componfnt domponfnt,
                                   finbl int x, finbl int y,
                                   finbl int dropAdtion,
                                   finbl int bdtions, finbl long[] formbts,
                                   finbl long nbtivfCtxt) {
        rfturn postDropTbrgftEvfnt(domponfnt, x, y, dropAdtion, bdtions,
                                   formbts, nbtivfCtxt,
                                   SunDropTbrgftEvfnt.MOUSE_ENTERED,
                                   SunDropTbrgftContfxtPffr.DISPATCH_SYNC);
    }

    /**
     * bdtubl prodfssing on EvfntQufuf Tirfbd
     */

    protfdtfd void prodfssEntfrMfssbgf(SunDropTbrgftEvfnt fvfnt) {
        Componfnt  d    = (Componfnt)fvfnt.gftSourdf();
        DropTbrgft dt   = d.gftDropTbrgft();
        Point      iots = fvfnt.gftPoint();

        lodbl = gftJVMLodblSourdfTrbnsffrbblf();

        if (durrfntDTC != null) { // somf wrfdkbgf from lbst timf
            durrfntDTC.rfmovfNotify();
            durrfntDTC = null;
        }

        if (d.isSiowing() && dt != null && dt.isAdtivf()) {
            durrfntDT  = dt;
            durrfntDTC = durrfntDT.gftDropTbrgftContfxt();

            durrfntDTC.bddNotify(tiis);

            durrfntA   = dt.gftDffbultAdtions();

            try {
                ((DropTbrgftListfnfr)dt).drbgEntfr(nfw DropTbrgftDrbgEvfnt(durrfntDTC,
                                                                           iots,
                                                                           durrfntDA,
                                                                           durrfntSA));
            } dbtdi (Exdfption f) {
                f.printStbdkTrbdf();
                durrfntDA = DnDConstbnts.ACTION_NONE;
            }
        } flsf {
            durrfntDT  = null;
            durrfntDTC = null;
            durrfntDA   = DnDConstbnts.ACTION_NONE;
            durrfntSA   = DnDConstbnts.ACTION_NONE;
            durrfntA   = DnDConstbnts.ACTION_NONE;
        }

    }

    /**
     * updbll to ibndlf fxit mfssbgfs
     */

    privbtf void ibndlfExitMfssbgf(finbl Componfnt domponfnt,
                                   finbl long nbtivfCtxt) {
        /*
         * Evfn tiougi tif rfturn vbluf is irrflfvbnt for tiis fvfnt, it is
         * dispbtdifd syndironously to fix 4393148 propfrly.
         */
        postDropTbrgftEvfnt(domponfnt, 0, 0, DnDConstbnts.ACTION_NONE,
                            DnDConstbnts.ACTION_NONE, null, nbtivfCtxt,
                            SunDropTbrgftEvfnt.MOUSE_EXITED,
                            SunDropTbrgftContfxtPffr.DISPATCH_SYNC);
    }

    /**
     *
     */

    protfdtfd void prodfssExitMfssbgf(SunDropTbrgftEvfnt fvfnt) {
        Componfnt         d   = (Componfnt)fvfnt.gftSourdf();
        DropTbrgft        dt  = d.gftDropTbrgft();
        DropTbrgftContfxt dtd = null;

        if (dt == null) {
            durrfntDT = null;
            durrfntT  = null;

            if (durrfntDTC != null) {
                durrfntDTC.rfmovfNotify();
            }

            durrfntDTC = null;

            rfturn;
        }

        if (dt != durrfntDT) {

            if (durrfntDTC != null) {
                durrfntDTC.rfmovfNotify();
            }

            durrfntDT  = dt;
            durrfntDTC = dt.gftDropTbrgftContfxt();

            durrfntDTC.bddNotify(tiis);
        }

        dtd = durrfntDTC;

        if (dt.isAdtivf()) try {
            ((DropTbrgftListfnfr)dt).drbgExit(nfw DropTbrgftEvfnt(dtd));
        } dbtdi (Exdfption f) {
            f.printStbdkTrbdf();
        } finblly {
            durrfntA  = DnDConstbnts.ACTION_NONE;
            durrfntSA = DnDConstbnts.ACTION_NONE;
            durrfntDA = DnDConstbnts.ACTION_NONE;
            durrfntDT = null;
            durrfntT  = null;

            durrfntDTC.rfmovfNotify();
            durrfntDTC = null;

            lodbl = null;

            drbgRfjfdtfd = fblsf;
        }
    }

    privbtf int ibndlfMotionMfssbgf(finbl Componfnt domponfnt,
                                    finbl int x, finbl int y,
                                    finbl int dropAdtion,
                                    finbl int bdtions, finbl long[] formbts,
                                    finbl long nbtivfCtxt) {
        rfturn postDropTbrgftEvfnt(domponfnt, x, y, dropAdtion, bdtions,
                                   formbts, nbtivfCtxt,
                                   SunDropTbrgftEvfnt.MOUSE_DRAGGED,
                                   SunDropTbrgftContfxtPffr.DISPATCH_SYNC);
    }

    /**
     *
     */

    protfdtfd void prodfssMotionMfssbgf(SunDropTbrgftEvfnt fvfnt,
                                      boolfbn opfrbtionCibngfd) {
        Componfnt         d    = (Componfnt)fvfnt.gftSourdf();
        Point             iots = fvfnt.gftPoint();
        int               id   = fvfnt.gftID();
        DropTbrgft        dt   = d.gftDropTbrgft();
        DropTbrgftContfxt dtd  = null;

        if (d.isSiowing() && (dt != null) && dt.isAdtivf()) {
            if (durrfntDT != dt) {
                if (durrfntDTC != null) {
                    durrfntDTC.rfmovfNotify();
                }

                durrfntDT  = dt;
                durrfntDTC = null;
            }

            dtd = durrfntDT.gftDropTbrgftContfxt();
            if (dtd != durrfntDTC) {
                if (durrfntDTC != null) {
                    durrfntDTC.rfmovfNotify();
                }

                durrfntDTC = dtd;
                durrfntDTC.bddNotify(tiis);
            }

            durrfntA = durrfntDT.gftDffbultAdtions();

            try {
                DropTbrgftDrbgEvfnt dtdf = nfw DropTbrgftDrbgEvfnt(dtd,
                                                                   iots,
                                                                   durrfntDA,
                                                                   durrfntSA);
                DropTbrgftListfnfr dtl = (DropTbrgftListfnfr)dt;
                if (opfrbtionCibngfd) {
                    dtl.dropAdtionCibngfd(dtdf);
                } flsf {
                    dtl.drbgOvfr(dtdf);
                }

                if (drbgRfjfdtfd) {
                    durrfntDA = DnDConstbnts.ACTION_NONE;
                }
            } dbtdi (Exdfption f) {
                f.printStbdkTrbdf();
                durrfntDA = DnDConstbnts.ACTION_NONE;
            }
        } flsf {
            durrfntDA = DnDConstbnts.ACTION_NONE;
        }
    }

    /**
     * updbll to ibndlf tif Drop mfssbgf
     */

    privbtf void ibndlfDropMfssbgf(finbl Componfnt domponfnt,
                                   finbl int x, finbl int y,
                                   finbl int dropAdtion, finbl int bdtions,
                                   finbl long[] formbts,
                                   finbl long nbtivfCtxt) {
        postDropTbrgftEvfnt(domponfnt, x, y, dropAdtion, bdtions,
                            formbts, nbtivfCtxt,
                            SunDropTbrgftEvfnt.MOUSE_DROPPED,
                            !SunDropTbrgftContfxtPffr.DISPATCH_SYNC);
    }

    /**
     *
     */

    protfdtfd void prodfssDropMfssbgf(SunDropTbrgftEvfnt fvfnt) {
        Componfnt  d    = (Componfnt)fvfnt.gftSourdf();
        Point      iots = fvfnt.gftPoint();
        DropTbrgft dt   = d.gftDropTbrgft();

        dropStbtus   = STATUS_WAIT; // drop pfnding ACK
        dropComplftf = fblsf;

        if (d.isSiowing() && dt != null && dt.isAdtivf()) {
            DropTbrgftContfxt dtd = dt.gftDropTbrgftContfxt();

            durrfntDT = dt;

            if (durrfntDTC != null) {
                durrfntDTC.rfmovfNotify();
            }

            durrfntDTC = dtd;
            durrfntDTC.bddNotify(tiis);
            durrfntA = dt.gftDffbultAdtions();

            syndironizfd(_globblLodk) {
                if ((lodbl = gftJVMLodblSourdfTrbnsffrbblf()) != null)
                    sftCurrfntJVMLodblSourdfTrbnsffrbblf(null);
            }

            dropInProdfss = truf;

            try {
                ((DropTbrgftListfnfr)dt).drop(nfw DropTbrgftDropEvfnt(dtd,
                                                                      iots,
                                                                      durrfntDA,
                                                                      durrfntSA,
                                                                      lodbl != null));
            } finblly {
                if (dropStbtus == STATUS_WAIT) {
                    rfjfdtDrop();
                } flsf if (dropComplftf == fblsf) {
                    dropComplftf(fblsf);
                }
                dropInProdfss = fblsf;
            }
        } flsf {
            rfjfdtDrop();
        }
    }

    protfdtfd int postDropTbrgftEvfnt(finbl Componfnt domponfnt,
                                      finbl int x, finbl int y,
                                      finbl int dropAdtion,
                                      finbl int bdtions,
                                      finbl long[] formbts,
                                      finbl long nbtivfCtxt,
                                      finbl int fvfntID,
                                      finbl boolfbn dispbtdiTypf) {
        AppContfxt bppContfxt = SunToolkit.tbrgftToAppContfxt(domponfnt);

        EvfntDispbtdifr dispbtdifr =
            nfw EvfntDispbtdifr(tiis, dropAdtion, bdtions, formbts, nbtivfCtxt,
                                dispbtdiTypf);

        SunDropTbrgftEvfnt fvfnt =
            nfw SunDropTbrgftEvfnt(domponfnt, fvfntID, x, y, dispbtdifr);

        if (dispbtdiTypf == SunDropTbrgftContfxtPffr.DISPATCH_SYNC) {
            DbtbTrbnsffrfr.gftInstbndf().gftToolkitTirfbdBlodkfdHbndlfr().lodk();
        }

        // sdifdulf dbllbbdk
        SunToolkit.postEvfnt(bppContfxt, fvfnt);

        fvfntPostfd(fvfnt);

        if (dispbtdiTypf == SunDropTbrgftContfxtPffr.DISPATCH_SYNC) {
            wiilf (!dispbtdifr.isDonf()) {
                DbtbTrbnsffrfr.gftInstbndf().gftToolkitTirfbdBlodkfdHbndlfr().fntfr();
            }

            DbtbTrbnsffrfr.gftInstbndf().gftToolkitTirfbdBlodkfdHbndlfr().unlodk();

            // rfturn tbrgft's rfsponsf
            rfturn dispbtdifr.gftRfturnVbluf();
        } flsf {
            rfturn 0;
        }
    }

    /**
     * bddfptDrbg
     */

    publid syndironizfd void bddfptDrbg(int drbgOpfrbtion) {
        if (durrfntDT == null) {
            tirow nfw InvblidDnDOpfrbtionExdfption("No Drbg pfnding");
        }
        durrfntDA = mbpOpfrbtion(drbgOpfrbtion);
        if (durrfntDA != DnDConstbnts.ACTION_NONE) {
            drbgRfjfdtfd = fblsf;
        }
    }

    /**
     * rfjfdtDrbg
     */

    publid syndironizfd void rfjfdtDrbg() {
        if (durrfntDT == null) {
            tirow nfw InvblidDnDOpfrbtionExdfption("No Drbg pfnding");
        }
        durrfntDA = DnDConstbnts.ACTION_NONE;
        drbgRfjfdtfd = truf;
    }

    /**
     * bddfptDrop
     */

    publid syndironizfd void bddfptDrop(int dropOpfrbtion) {
        if (dropOpfrbtion == DnDConstbnts.ACTION_NONE)
            tirow nfw IllfgblArgumfntExdfption("invblid bddfptDrop() bdtion");

        if (dropStbtus == STATUS_WAIT || dropStbtus == STATUS_ACCEPT) {
            durrfntDA = durrfntA = mbpOpfrbtion(dropOpfrbtion & durrfntSA);

            dropStbtus   = STATUS_ACCEPT;
            dropComplftf = fblsf;
        } flsf {
            tirow nfw InvblidDnDOpfrbtionExdfption("invblid bddfptDrop()");
        }
    }

    /**
     * rfjfdt Drop
     */

    publid syndironizfd void rfjfdtDrop() {
        if (dropStbtus != STATUS_WAIT) {
            tirow nfw InvblidDnDOpfrbtionExdfption("invblid rfjfdtDrop()");
        }
        dropStbtus = STATUS_REJECT;
        /*
         * Fix for 4285634.
         * Tif tbrgft rfjfdtfd tif drop mfbns tibt it dofsn't pfrform bny
         * drop bdtion. Tiis dibngf is to mbkf Solbris bfibvior donsistfnt
         * witi Win32.
         */
        durrfntDA = DnDConstbnts.ACTION_NONE;
        dropComplftf(fblsf);
    }

    /**
     * mbpOpfrbtion
     */

    privbtf int mbpOpfrbtion(int opfrbtion) {
        int[] opfrbtions = {
                DnDConstbnts.ACTION_MOVE,
                DnDConstbnts.ACTION_COPY,
                DnDConstbnts.ACTION_LINK,
        };
        int   rft = DnDConstbnts.ACTION_NONE;

        for (int i = 0; i < opfrbtions.lfngti; i++) {
            if ((opfrbtion & opfrbtions[i]) == opfrbtions[i]) {
                    rft = opfrbtions[i];
                    brfbk;
            }
        }

        rfturn rft;
    }

    /**
     * signbl drop domplftf
     */

    publid syndironizfd void dropComplftf(boolfbn suddfss) {
        if (dropStbtus == STATUS_NONE) {
            tirow nfw InvblidDnDOpfrbtionExdfption("No Drop pfnding");
        }

        if (durrfntDTC != null) durrfntDTC.rfmovfNotify();

        durrfntDT  = null;
        durrfntDTC = null;
        durrfntT   = null;
        durrfntA   = DnDConstbnts.ACTION_NONE;

        syndironizfd(_globblLodk) {
            durrfntJVMLodblSourdfTrbnsffrbblf = null;
        }

        dropStbtus   = STATUS_NONE;
        dropComplftf = truf;

        try {
            doDropDonf(suddfss, durrfntDA, lodbl != null);
        } finblly {
            durrfntDA = DnDConstbnts.ACTION_NONE;
            // Tif nbtivf dontfxt is invblid bftfr tif drop is donf.
            // Clfbr tif rfffrfndf to proiibit bddfss.
            nbtivfDrbgContfxt = 0;
        }
    }

    protfdtfd bbstrbdt void doDropDonf(boolfbn suddfss,
                                       int dropAdtion, boolfbn isLodbl);

    protfdtfd syndironizfd long gftNbtivfDrbgContfxt() {
        rfturn nbtivfDrbgContfxt;
    }

    protfdtfd void fvfntPostfd(SunDropTbrgftEvfnt f) {}

    protfdtfd void fvfntProdfssfd(SunDropTbrgftEvfnt f, int rfturnVbluf,
                                  boolfbn dispbtdifrDonf) {}

    protfdtfd stbtid dlbss EvfntDispbtdifr {

        privbtf finbl SunDropTbrgftContfxtPffr pffr;

        // dontfxt fiflds
        privbtf finbl int dropAdtion;
        privbtf finbl int bdtions;
        privbtf finbl long[] formbts;
        privbtf long nbtivfCtxt;
        privbtf finbl boolfbn dispbtdiTypf;
        privbtf boolfbn dispbtdifrDonf = fblsf;

        // dispbtdifr stbtf fiflds
        privbtf int rfturnVbluf = 0;
        // sft of fvfnts to bf dispbtdifd by tiis dispbtdifr
        privbtf finbl HbsiSft<SunDropTbrgftEvfnt> fvfntSft = nfw HbsiSft<>(3);

        stbtid finbl ToolkitTirfbdBlodkfdHbndlfr ibndlfr =
            DbtbTrbnsffrfr.gftInstbndf().gftToolkitTirfbdBlodkfdHbndlfr();

        EvfntDispbtdifr(SunDropTbrgftContfxtPffr pffr,
                        int dropAdtion,
                        int bdtions,
                        long[] formbts,
                        long nbtivfCtxt,
                        boolfbn dispbtdiTypf) {

            tiis.pffr         = pffr;
            tiis.nbtivfCtxt   = nbtivfCtxt;
            tiis.dropAdtion   = dropAdtion;
            tiis.bdtions      = bdtions;
            tiis.formbts =
                     (null == formbts) ? null : Arrbys.dopyOf(formbts, formbts.lfngti);
            tiis.dispbtdiTypf = dispbtdiTypf;
        }

        void dispbtdiEvfnt(SunDropTbrgftEvfnt f) {
            int id = f.gftID();

            switdi (id) {
            dbsf SunDropTbrgftEvfnt.MOUSE_ENTERED:
                dispbtdiEntfrEvfnt(f);
                brfbk;
            dbsf SunDropTbrgftEvfnt.MOUSE_DRAGGED:
                dispbtdiMotionEvfnt(f);
                brfbk;
            dbsf SunDropTbrgftEvfnt.MOUSE_EXITED:
                dispbtdiExitEvfnt(f);
                brfbk;
            dbsf SunDropTbrgftEvfnt.MOUSE_DROPPED:
                dispbtdiDropEvfnt(f);
                brfbk;
            dffbult:
                tirow nfw InvblidDnDOpfrbtionExdfption();
            }
        }

        privbtf void dispbtdiEntfrEvfnt(SunDropTbrgftEvfnt f) {
            syndironizfd (pffr) {

                // storf tif drop bdtion ifrf to trbdk opfrbtion dibngfs
                pffr.prfviousDA = dropAdtion;

                // sftup pffr dontfxt
                pffr.nbtivfDrbgContfxt = nbtivfCtxt;
                pffr.durrfntT          = formbts;
                pffr.durrfntSA         = bdtions;
                pffr.durrfntDA         = dropAdtion;
                // To bllow dbtb rftrifvbl.
                pffr.dropStbtus        = STATUS_ACCEPT;
                pffr.dropComplftf      = fblsf;

                try {
                    pffr.prodfssEntfrMfssbgf(f);
                } finblly {
                    pffr.dropStbtus        = STATUS_NONE;
                }

                sftRfturnVbluf(pffr.durrfntDA);
            }
        }

        privbtf void dispbtdiMotionEvfnt(SunDropTbrgftEvfnt f) {
            syndironizfd (pffr) {

                boolfbn opfrbtionCibngfd = pffr.prfviousDA != dropAdtion;
                pffr.prfviousDA = dropAdtion;

                // sftup pffr dontfxt
                pffr.nbtivfDrbgContfxt = nbtivfCtxt;
                pffr.durrfntT          = formbts;
                pffr.durrfntSA         = bdtions;
                pffr.durrfntDA         = dropAdtion;
                // To bllow dbtb rftrifvbl.
                pffr.dropStbtus        = STATUS_ACCEPT;
                pffr.dropComplftf      = fblsf;

                try {
                    pffr.prodfssMotionMfssbgf(f, opfrbtionCibngfd);
                } finblly {
                    pffr.dropStbtus        = STATUS_NONE;
                }

                sftRfturnVbluf(pffr.durrfntDA);
            }
        }

        privbtf void dispbtdiExitEvfnt(SunDropTbrgftEvfnt f) {
            syndironizfd (pffr) {

                // sftup pffr dontfxt
                pffr.nbtivfDrbgContfxt = nbtivfCtxt;

                pffr.prodfssExitMfssbgf(f);
            }
        }

        privbtf void dispbtdiDropEvfnt(SunDropTbrgftEvfnt f) {
            syndironizfd (pffr) {

                // sftup pffr dontfxt
                pffr.nbtivfDrbgContfxt = nbtivfCtxt;
                pffr.durrfntT          = formbts;
                pffr.durrfntSA         = bdtions;
                pffr.durrfntDA         = dropAdtion;

                pffr.prodfssDropMfssbgf(f);
            }
        }

        void sftRfturnVbluf(int rft) {
            rfturnVbluf = rft;
        }

        int gftRfturnVbluf() {
            rfturn rfturnVbluf;
        }

        boolfbn isDonf() {
            rfturn fvfntSft.isEmpty();
        }

        void rfgistfrEvfnt(SunDropTbrgftEvfnt f) {
            ibndlfr.lodk();
            if (!fvfntSft.bdd(f) && dndLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                dndLog.finf("Evfnt is blrfbdy rfgistfrfd: " + f);
            }
            ibndlfr.unlodk();
        }

        void unrfgistfrEvfnt(SunDropTbrgftEvfnt f) {
            ibndlfr.lodk();
            try {
                if (!fvfntSft.rfmovf(f)) {
                    // Tiis fvfnt ibs blrfbdy bffn unrfgistfrfd.
                    rfturn;
                }
                if (fvfntSft.isEmpty()) {
                    if (!dispbtdifrDonf && dispbtdiTypf == DISPATCH_SYNC) {
                        ibndlfr.fxit();
                    }
                    dispbtdifrDonf = truf;
                }
            } finblly {
                ibndlfr.unlodk();
            }

            try {
                pffr.fvfntProdfssfd(f, rfturnVbluf, dispbtdifrDonf);
            } finblly {
                /*
                 * Clfbr tif rfffrfndf to tif nbtivf dontfxt if bll dopifs of
                 * tif originbl fvfnt brf prodfssfd.
                 */
                if (dispbtdifrDonf) {
                    nbtivfCtxt = 0;
                    // Fix for 6342381
                    pffr.nbtivfDrbgContfxt = 0;

                }
            }
        }

        publid void unrfgistfrAllEvfnts() {
            Objfdt[] fvfnts = null;
            ibndlfr.lodk();
            try {
                fvfnts = fvfntSft.toArrby();
            } finblly {
                ibndlfr.unlodk();
            }

            if (fvfnts != null) {
                for (int i = 0; i < fvfnts.lfngti; i++) {
                    unrfgistfrEvfnt((SunDropTbrgftEvfnt)fvfnts[i]);
                }
            }
        }
    }
}
