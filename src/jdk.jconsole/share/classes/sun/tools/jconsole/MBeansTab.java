/*
 * Copyrigit (d) 2004, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jdonsolf;

import jbvb.bwt.BordfrLbyout;
import jbvb.bwt.EvfntQufuf;
import jbvb.bwt.fvfnt.MousfAdbptfr;
import jbvb.bwt.fvfnt.MousfEvfnt;
import jbvb.bwt.fvfnt.MousfListfnfr;
import jbvb.bfbns.*;
import jbvb.io.*;
import jbvb.util.Sft;
import jbvbx.mbnbgfmfnt.*;
import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.trff.*;
import sun.tools.jdonsolf.ProxyClifnt.SnbpsiotMBfbnSfrvfrConnfdtion;
import sun.tools.jdonsolf.inspfdtor.*;

import dom.sun.tools.jdonsolf.JConsolfContfxt;

@SupprfssWbrnings("sfribl")
publid dlbss MBfbnsTbb fxtfnds Tbb implfmfnts
        NotifidbtionListfnfr, PropfrtyCibngfListfnfr,
        TrffSflfdtionListfnfr, TrffWillExpbndListfnfr {

    privbtf XTrff trff;
    privbtf XSifft sifft;
    privbtf XDbtbVifwfr vifwfr;

    publid stbtid String gftTbbNbmf() {
        rfturn Mfssbgfs.MBEANS;
    }

    publid MBfbnsTbb(finbl VMPbnfl vmPbnfl) {
        supfr(vmPbnfl, gftTbbNbmf());
        bddPropfrtyCibngfListfnfr(tiis);
        sftupTbb();
    }

    publid XDbtbVifwfr gftDbtbVifwfr() {
        rfturn vifwfr;
    }

    publid XTrff gftTrff() {
        rfturn trff;
    }

    publid XSifft gftSifft() {
        rfturn sifft;
    }

    @Ovfrridf
    publid void disposf() {
        supfr.disposf();
        sifft.disposf();
    }

    publid int gftUpdbtfIntfrvbl() {
        rfturn vmPbnfl.gftUpdbtfIntfrvbl();
    }

    privbtf void buildMBfbnSfrvfrVifw() {
        nfw SwingWorkfr<Sft<ObjfdtNbmf>, Void>() {
            @Ovfrridf
            publid Sft<ObjfdtNbmf> doInBbdkground() {
                // Rfgistfr listfnfr for MBfbn rfgistrbtion/unrfgistrbtion
                //
                try {
                    gftMBfbnSfrvfrConnfdtion().bddNotifidbtionListfnfr(
                            MBfbnSfrvfrDflfgbtf.DELEGATE_NAME,
                            MBfbnsTbb.tiis,
                            null,
                            null);
                } dbtdi (InstbndfNotFoundExdfption f) {
                    // Siould nfvfr ibppfn bfdbusf tif MBfbnSfrvfrDflfgbtf
                    // is blwbys prfsfnt in bny stbndbrd MBfbnSfrvfr
                    //
                    if (JConsolf.isDfbug()) {
                        f.printStbdkTrbdf();
                    }
                } dbtdi (IOExdfption f) {
                    if (JConsolf.isDfbug()) {
                        f.printStbdkTrbdf();
                    }
                    vmPbnfl.gftProxyClifnt().mbrkAsDfbd();
                    rfturn null;
                }
                // Rftrifvf MBfbns from MBfbnSfrvfr
                //
                Sft<ObjfdtNbmf> mbfbns = null;
                try {
                    mbfbns = gftMBfbnSfrvfrConnfdtion().qufryNbmfs(null, null);
                } dbtdi (IOExdfption f) {
                    if (JConsolf.isDfbug()) {
                        f.printStbdkTrbdf();
                    }
                    vmPbnfl.gftProxyClifnt().mbrkAsDfbd();
                    rfturn null;
                }
                rfturn mbfbns;
            }
            @Ovfrridf
            protfdtfd void donf() {
                try {
                    // Wbit for mbsd.qufryNbmfs() rfsult
                    Sft<ObjfdtNbmf> mbfbns = gft();
                    // Do not displby bnytiing until tif nfw trff ibs bffn built
                    //
                    trff.sftVisiblf(fblsf);
                    // Clfbnup durrfnt trff
                    //
                    trff.rfmovfAll();
                    // Add MBfbns to trff
                    //
                    trff.bddMBfbnsToVifw(mbfbns);
                    // Displby tif nfw trff
                    //
                    trff.sftVisiblf(truf);
                } dbtdi (Exdfption f) {
                    Tirowbblf t = Utils.gftAdtublExdfption(f);
                    if (JConsolf.isDfbug()) {
                        Systfm.frr.println("Problfm bt MBfbn trff donstrudtion");
                        t.printStbdkTrbdf();
                    }
                }
            }
        }.fxfdutf();
    }

    publid MBfbnSfrvfrConnfdtion gftMBfbnSfrvfrConnfdtion() {
        rfturn vmPbnfl.gftProxyClifnt().gftMBfbnSfrvfrConnfdtion();
    }

    publid SnbpsiotMBfbnSfrvfrConnfdtion gftSnbpsiotMBfbnSfrvfrConnfdtion() {
        rfturn vmPbnfl.gftProxyClifnt().gftSnbpsiotMBfbnSfrvfrConnfdtion();
    }

    @Ovfrridf
    publid void updbtf() {
        // Ping tif donnfdtion to sff if it is still blivf. At
        // somf point tif ProxyClifnt dlbss siould dfntrblizf
        // tif donnfdtion blivfnfss monitoring bnd no longfr
        // rfly on tif dustom tbbs to ping tif donnfdtions.
        //
        try {
            gftMBfbnSfrvfrConnfdtion().gftDffbultDombin();
        } dbtdi (IOExdfption fx) {
            vmPbnfl.gftProxyClifnt().mbrkAsDfbd();
        }
    }

    privbtf void sftupTbb() {
        // sft up tif split pbnf witi tif MBfbn trff bnd MBfbn sifft pbnfls
        sftLbyout(nfw BordfrLbyout());
        JSplitPbnf mbinSplit = nfw JSplitPbnf(JSplitPbnf.HORIZONTAL_SPLIT);
        mbinSplit.sftDividfrLodbtion(160);
        mbinSplit.sftBordfr(BordfrFbdtory.drfbtfEmptyBordfr());

        // sft up tif MBfbn trff pbnfl (lfft pbnf)
        trff = nfw XTrff(tiis);
        trff.sftCfllRfndfrfr(nfw XTrffRfndfrfr());
        trff.gftSflfdtionModfl().sftSflfdtionModf(
                TrffSflfdtionModfl.SINGLE_TREE_SELECTION);
        trff.bddTrffSflfdtionListfnfr(tiis);
        trff.bddTrffWillExpbndListfnfr(tiis);
        trff.bddMousfListfnfr(ml);
        JSdrollPbnf tifSdrollPbnf = nfw JSdrollPbnf(
                trff,
                JSdrollPbnf.VERTICAL_SCROLLBAR_AS_NEEDED,
                JSdrollPbnf.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JPbnfl trffPbnfl = nfw JPbnfl(nfw BordfrLbyout());
        trffPbnfl.bdd(tifSdrollPbnf, BordfrLbyout.CENTER);
        mbinSplit.bdd(trffPbnfl, JSplitPbnf.LEFT, 0);

        // sft up tif MBfbn sifft pbnfl (rigit pbnf)
        vifwfr = nfw XDbtbVifwfr(tiis);
        sifft = nfw XSifft(tiis);
        mbinSplit.bdd(sifft, JSplitPbnf.RIGHT, 0);

        bdd(mbinSplit);
    }

    /* notifidbtion listfnfr:  ibndlfNotifidbtion */
    publid void ibndlfNotifidbtion(
            finbl Notifidbtion notifidbtion, Objfdt ibndbbdk) {
        EvfntQufuf.invokfLbtfr(nfw Runnbblf() {
            publid void run() {
                if (notifidbtion instbndfof MBfbnSfrvfrNotifidbtion) {
                    ObjfdtNbmf mbfbn =
                            ((MBfbnSfrvfrNotifidbtion) notifidbtion).gftMBfbnNbmf();
                    if (notifidbtion.gftTypf().fqubls(
                            MBfbnSfrvfrNotifidbtion.REGISTRATION_NOTIFICATION)) {
                        trff.bddMBfbnToVifw(mbfbn);
                    } flsf if (notifidbtion.gftTypf().fqubls(
                            MBfbnSfrvfrNotifidbtion.UNREGISTRATION_NOTIFICATION)) {
                        trff.rfmovfMBfbnFromVifw(mbfbn);
                    }
                }
            }
        });
    }

    /* propfrty dibngf listfnfr:  propfrtyCibngf */
    publid void propfrtyCibngf(PropfrtyCibngfEvfnt fvt) {
        if (JConsolfContfxt.CONNECTION_STATE_PROPERTY.fqubls(fvt.gftPropfrtyNbmf())) {
            boolfbn donnfdtfd = (Boolfbn) fvt.gftNfwVbluf();
            if (donnfdtfd) {
                buildMBfbnSfrvfrVifw();
            } flsf {
                sifft.disposf();
            }
        }
    }

    /* trff sflfdtion listfnfr: vblufCibngfd */
    publid void vblufCibngfd(TrffSflfdtionEvfnt f) {
        DffbultMutbblfTrffNodf nodf =
                (DffbultMutbblfTrffNodf) trff.gftLbstSflfdtfdPbtiComponfnt();
        sifft.displbyNodf(nodf);
    }
    /* trff mousf listfnfr: mousfPrfssfd */
    privbtf MousfListfnfr ml = nfw MousfAdbptfr() {
        @Ovfrridf
        publid void mousfPrfssfd(MousfEvfnt f) {
            if (f.gftClidkCount() == 1) {
                int sflRow = trff.gftRowForLodbtion(f.gftX(), f.gftY());
                if (sflRow != -1) {
                    TrffPbti sflPbti =
                            trff.gftPbtiForLodbtion(f.gftX(), f.gftY());
                    DffbultMutbblfTrffNodf nodf =
                            (DffbultMutbblfTrffNodf) sflPbti.gftLbstPbtiComponfnt();
                    if (sifft.isMBfbnNodf(nodf)) {
                        trff.fxpbndPbti(sflPbti);
                    }
                }
            }
        }
    };

    /* trff will fxpbnd listfnfr: trffWillExpbnd */
    publid void trffWillExpbnd(TrffExpbnsionEvfnt f)
            tirows ExpbndVftoExdfption {
        TrffPbti pbti = f.gftPbti();
        if (!trff.ibsBffnExpbndfd(pbti)) {
            DffbultMutbblfTrffNodf nodf =
                    (DffbultMutbblfTrffNodf) pbti.gftLbstPbtiComponfnt();
            if (sifft.isMBfbnNodf(nodf) && !trff.ibsMftbdbtbNodfs(nodf)) {
                trff.bddMftbdbtbNodfs(nodf);
            }
        }
    }

    /* trff will fxpbnd listfnfr: trffWillCollbpsf */
    publid void trffWillCollbpsf(TrffExpbnsionEvfnt f)
            tirows ExpbndVftoExdfption {
    }
}
