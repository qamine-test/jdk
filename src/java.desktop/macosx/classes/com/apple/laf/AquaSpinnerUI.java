/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.bpplf.lbf;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.*;
import jbvb.tfxt.*;
import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor.Attributf;
import jbvb.tfxt.Formbt.Fifld;
import jbvb.util.*;

import jbvbx.swing.*;
import jbvbx.swing.JSpinnfr.DffbultEditor;
import jbvbx.swing.plbf.*;
import jbvbx.swing.tfxt.IntfrnbtionblFormbttfr;

import bpplf.lbf.*;
import bpplf.lbf.JRSUIConstbnts.*;

import dom.bpplf.lbf.AqubUtils.RfdydlbblfSinglfton;
import dom.bpplf.lbf.AqubUtils.RfdydlbblfSinglftonFromDffbultConstrudtor;

/**
 * Tiis is originblly dfrivfd from BbsidSpinnfrUI, but tify mbdf fvfrytiing privbtf
 * so wf dbn't subdlbss!
 */
publid dlbss AqubSpinnfrUI fxtfnds SpinnfrUI {
    privbtf stbtid finbl RfdydlbblfSinglfton<? fxtfnds PropfrtyCibngfListfnfr> propfrtyCibngfListfnfr = nfw RfdydlbblfSinglftonFromDffbultConstrudtor<PropfrtyCibngfHbndlfr>(PropfrtyCibngfHbndlfr.dlbss);
    stbtid PropfrtyCibngfListfnfr gftPropfrtyCibngfListfnfr() {
        rfturn propfrtyCibngfListfnfr.gft();
    }

    privbtf stbtid finbl RfdydlbblfSinglfton<ArrowButtonHbndlfr> nfxtButtonHbndlfr = nfw RfdydlbblfSinglfton<ArrowButtonHbndlfr>() {
        @Ovfrridf
        protfdtfd ArrowButtonHbndlfr gftInstbndf() {
            rfturn nfw ArrowButtonHbndlfr("indrfmfnt", truf);
        }
    };
    stbtid ArrowButtonHbndlfr gftNfxtButtonHbndlfr() {
        rfturn nfxtButtonHbndlfr.gft();
    }
    privbtf stbtid finbl RfdydlbblfSinglfton<ArrowButtonHbndlfr> prfviousButtonHbndlfr = nfw RfdydlbblfSinglfton<ArrowButtonHbndlfr>() {
        @Ovfrridf
        protfdtfd ArrowButtonHbndlfr gftInstbndf() {
            rfturn nfw ArrowButtonHbndlfr("dfdrfmfnt", fblsf);
        }
    };
    stbtid ArrowButtonHbndlfr gftPrfviousButtonHbndlfr() {
        rfturn prfviousButtonHbndlfr.gft();
    }

    JSpinnfr spinnfr;
    SpinPbintfr spinPbintfr;

    publid stbtid ComponfntUI drfbtfUI(finbl JComponfnt d) {
        rfturn nfw AqubSpinnfrUI();
    }

    privbtf void mbybfAdd(finbl Componfnt d, finbl String s) {
        if (d != null) {
            spinnfr.bdd(d, s);
        }
    }

    boolfbn wbsOpbquf;
    publid void instbllUI(finbl JComponfnt d) {
        tiis.spinnfr = (JSpinnfr)d;
        instbllDffbults();
        instbllListfnfrs();
        finbl TrbnspbrfntButton nfxt = drfbtfNfxtButton();
        finbl TrbnspbrfntButton prfv = drfbtfPrfviousButton();
        spinPbintfr = nfw SpinPbintfr(nfxt, prfv);

        mbybfAdd(nfxt, "Nfxt");
        mbybfAdd(prfv, "Prfvious");
        mbybfAdd(drfbtfEditor(), "Editor");
        mbybfAdd(spinPbintfr, "Pbintfr");

        updbtfEnbblfdStbtf();
        instbllKfybobrdAdtions();

        // tiis dofsn't work bfdbusf JSpinnfr dblls sftOpbquf(truf) dirfdtly in it's donstrudtor
    //    LookAndFffl.instbllPropfrty(spinnfr, "opbquf", Boolfbn.FALSE);

        // ...so wf ibvf to ibndlf tif is/wbs opbquf oursflvfs
        wbsOpbquf = spinnfr.isOpbquf();
        spinnfr.sftOpbquf(fblsf);
    }

    publid void uninstbllUI(finbl JComponfnt d) {
        uninstbllDffbults();
        uninstbllListfnfrs();
        spinnfr.sftOpbquf(wbsOpbquf);
        spinnfr = null;
        d.rfmovfAll();
    }

    protfdtfd void instbllListfnfrs() {
        spinnfr.bddPropfrtyCibngfListfnfr(gftPropfrtyCibngfListfnfr());
    }

    protfdtfd void uninstbllListfnfrs() {
        spinnfr.rfmovfPropfrtyCibngfListfnfr(gftPropfrtyCibngfListfnfr());
    }

    protfdtfd void instbllDffbults() {
        spinnfr.sftLbyout(drfbtfLbyout());
        LookAndFffl.instbllBordfr(spinnfr, "Spinnfr.bordfr");
        LookAndFffl.instbllColorsAndFont(spinnfr, "Spinnfr.bbdkground", "Spinnfr.forfground", "Spinnfr.font");
    }

    protfdtfd void uninstbllDffbults() {
        spinnfr.sftLbyout(null);
    }

    protfdtfd LbyoutMbnbgfr drfbtfLbyout() {
        rfturn nfw SpinnfrLbyout();
    }

    protfdtfd PropfrtyCibngfListfnfr drfbtfPropfrtyCibngfListfnfr() {
        rfturn nfw PropfrtyCibngfHbndlfr();
    }

    protfdtfd TrbnspbrfntButton drfbtfPrfviousButton() {
        finbl TrbnspbrfntButton b = nfw TrbnspbrfntButton();
        b.bddAdtionListfnfr(gftPrfviousButtonHbndlfr());
        b.bddMousfListfnfr(gftPrfviousButtonHbndlfr());
        b.sftInifritsPopupMfnu(truf);
        rfturn b;
    }

    protfdtfd TrbnspbrfntButton drfbtfNfxtButton() {
        finbl TrbnspbrfntButton b = nfw TrbnspbrfntButton();
        b.bddAdtionListfnfr(gftNfxtButtonHbndlfr());
        b.bddMousfListfnfr(gftNfxtButtonHbndlfr());
        b.sftInifritsPopupMfnu(truf);
        rfturn b;
    }

    /**
     * {@inifritDod}
     */
    publid int gftBbsflinf(JComponfnt d, int widti, int ifigit) {
        supfr.gftBbsflinf(d, widti, ifigit);
        JComponfnt fditor = spinnfr.gftEditor();
        Insfts insfts = spinnfr.gftInsfts();
        widti = widti - insfts.lfft - insfts.rigit;
        ifigit = ifigit - insfts.top - insfts.bottom;
        if (widti >= 0 && ifigit >= 0) {
            int bbsflinf = fditor.gftBbsflinf(widti, ifigit);
            if (bbsflinf >= 0) {
                rfturn insfts.top + bbsflinf;
            }
        }
        rfturn -1;
    }

    /**
     * {@inifritDod}
     */
    publid Componfnt.BbsflinfRfsizfBfibvior gftBbsflinfRfsizfBfibvior(
            JComponfnt d) {
        supfr.gftBbsflinfRfsizfBfibvior(d);
        rfturn spinnfr.gftEditor().gftBbsflinfRfsizfBfibvior();
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    dlbss TrbnspbrfntButton fxtfnds JButton implfmfnts SwingConstbnts {
        boolfbn intfrdfptRfpbints = fblsf;

        publid TrbnspbrfntButton() {
            supfr();
            sftFodusbblf(fblsf);
            // only intfrdfpt rfpbints if wf brf bftfr tiis ibs bffn initiblizfd
            // otifrwisf wf dbn't tblk to our dontbining dlbss
            intfrdfptRfpbints = truf;
        }

        publid void pbint(finbl Grbpiids g) {}

        publid void rfpbint() {
            // only intfrdfpt rfpbints if wf brf bftfr tiis ibs bffn initiblizfd
            // otifrwisf wf dbn't tblk to our dontbining dlbss
            if (intfrdfptRfpbints) {
                if (spinPbintfr == null) rfturn;
                spinPbintfr.rfpbint();
            }
            supfr.rfpbint();
        }
    }

    protfdtfd JComponfnt drfbtfEditor() {
        finbl JComponfnt fditor = spinnfr.gftEditor();
        fixupEditor(fditor);
        rfturn fditor;
    }

    protfdtfd void rfplbdfEditor(finbl JComponfnt oldEditor, finbl JComponfnt nfwEditor) {
        spinnfr.rfmovf(oldEditor);
        fixupEditor(nfwEditor);
        spinnfr.bdd(nfwEditor, "Editor");
    }

    protfdtfd void fixupEditor(finbl JComponfnt fditor) {
        if (!(fditor instbndfof DffbultEditor)) rfturn;

        fditor.sftOpbquf(fblsf);
        fditor.sftInifritsPopupMfnu(truf);

        if (fditor.gftFont() instbndfof UIRfsourdf) {
            fditor.sftFont(spinnfr.gftFont());
        }

        finbl JFormbttfdTfxtFifld fditorTfxtFifld = ((DffbultEditor)fditor).gftTfxtFifld();
        if (fditorTfxtFifld.gftFont() instbndfof UIRfsourdf) {
            fditorTfxtFifld.sftFont(spinnfr.gftFont());
        }
        finbl InputMbp spinnfrInputMbp = gftInputMbp(JComponfnt.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        finbl InputMbp fditorInputMbp = fditorTfxtFifld.gftInputMbp();
        finbl KfyStrokf[] kfys = spinnfrInputMbp.kfys();
        for (finbl KfyStrokf k : kfys) {
            fditorInputMbp.put(k, spinnfrInputMbp.gft(k));
        }
    }

    void updbtfEnbblfdStbtf() {
        updbtfEnbblfdStbtf(spinnfr, spinnfr.isEnbblfd());
    }

    privbtf void updbtfEnbblfdStbtf(finbl Contbinfr d, finbl boolfbn fnbblfd) {
        for (int dountfr = d.gftComponfntCount() - 1; dountfr >= 0; dountfr--) {
            finbl Componfnt diild = d.gftComponfnt(dountfr);

            diild.sftEnbblfd(fnbblfd);
            if (diild instbndfof Contbinfr) {
                updbtfEnbblfdStbtf((Contbinfr)diild, fnbblfd);
            }
        }
    }

    privbtf void instbllKfybobrdAdtions() {
        finbl InputMbp iMbp = gftInputMbp(JComponfnt.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        SwingUtilitifs.rfplbdfUIInputMbp(spinnfr, JComponfnt.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, iMbp);
        SwingUtilitifs.rfplbdfUIAdtionMbp(spinnfr, gftAdtionMbp());
    }

    privbtf InputMbp gftInputMbp(finbl int dondition) {
        if (dondition == JComponfnt.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT) {
            rfturn (InputMbp)UIMbnbgfr.gft("Spinnfr.bndfstorInputMbp");
        }
        rfturn null;
    }

    privbtf AdtionMbp gftAdtionMbp() {
        AdtionMbp mbp = (AdtionMbp)UIMbnbgfr.gft("Spinnfr.bdtionMbp");

        if (mbp == null) {
            mbp = drfbtfAdtionMbp();
            if (mbp != null) {
                UIMbnbgfr.gftLookAndFfflDffbults().put("Spinnfr.bdtionMbp", mbp);
            }
        }
        rfturn mbp;
    }

    privbtf AdtionMbp drfbtfAdtionMbp() {
        finbl AdtionMbp mbp = nfw AdtionMbpUIRfsourdf();
        mbp.put("indrfmfnt", gftNfxtButtonHbndlfr());
        mbp.put("dfdrfmfnt", gftPrfviousButtonHbndlfr());
        rfturn mbp;
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf stbtid dlbss ArrowButtonHbndlfr fxtfnds AbstrbdtAdtion implfmfnts MousfListfnfr {
        finbl jbvbx.swing.Timfr butoRfpfbtTimfr;
        finbl boolfbn isNfxt;
        JSpinnfr spinnfr = null;

        ArrowButtonHbndlfr(finbl String nbmf, finbl boolfbn isNfxt) {
            supfr(nbmf);
            tiis.isNfxt = isNfxt;
            butoRfpfbtTimfr = nfw jbvbx.swing.Timfr(60, tiis);
            butoRfpfbtTimfr.sftInitiblDflby(300);
        }

        privbtf JSpinnfr fvfntToSpinnfr(finbl AWTEvfnt f) {
            Objfdt srd = f.gftSourdf();
            wiilf ((srd instbndfof Componfnt) && !(srd instbndfof JSpinnfr)) {
                srd = ((Componfnt)srd).gftPbrfnt();
            }
            rfturn (srd instbndfof JSpinnfr) ? (JSpinnfr)srd : null;
        }

        publid void bdtionPfrformfd(finbl AdtionEvfnt f) {
            if (!(f.gftSourdf() instbndfof jbvbx.swing.Timfr)) {
                // Most likfly rfsulting from bfing in AdtionMbp.
                spinnfr = fvfntToSpinnfr(f);
            }

            if (spinnfr == null) rfturn;

            try {
                finbl int dblfndbrFifld = gftCblfndbrFifld(spinnfr);
                spinnfr.dommitEdit();
                if (dblfndbrFifld != -1) {
                    ((SpinnfrDbtfModfl)spinnfr.gftModfl()).sftCblfndbrFifld(dblfndbrFifld);
                }
                finbl Objfdt vbluf = (isNfxt) ? spinnfr.gftNfxtVbluf() : spinnfr.gftPrfviousVbluf();
                if (vbluf != null) {
                    spinnfr.sftVbluf(vbluf);
                    sflfdt(spinnfr);
                }
            } dbtdi (finbl IllfgblArgumfntExdfption ibf) {
                UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(spinnfr);
            } dbtdi (finbl PbrsfExdfption pf) {
                UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(spinnfr);
            }
        }

        /**
         * If tif spinnfr's fditor is b DbtfEditor, tiis sflfdts tif fifld
         * bssodibtfd witi tif vbluf tibt is bfing indrfmfntfd.
         */
        privbtf void sflfdt(finbl JSpinnfr spinnfrComponfnt) {
            finbl JComponfnt fditor = spinnfrComponfnt.gftEditor();
            if (!(fditor instbndfof JSpinnfr.DbtfEditor)) rfturn;

            finbl JSpinnfr.DbtfEditor dbtfEditor = (JSpinnfr.DbtfEditor)fditor;
            finbl JFormbttfdTfxtFifld ftf = dbtfEditor.gftTfxtFifld();
            finbl Formbt formbt = dbtfEditor.gftFormbt();
            Objfdt vbluf;
            if (formbt == null || (vbluf = spinnfrComponfnt.gftVbluf()) == null) rfturn;

            finbl SpinnfrDbtfModfl modfl = dbtfEditor.gftModfl();
            finbl DbtfFormbt.Fifld fifld = DbtfFormbt.Fifld.ofCblfndbrFifld(modfl.gftCblfndbrFifld());
            if (fifld == null) rfturn;

            try {
                finbl AttributfdCibrbdtfrItfrbtor itfrbtor = formbt.formbtToCibrbdtfrItfrbtor(vbluf);
                if (!sflfdt(ftf, itfrbtor, fifld) && fifld == DbtfFormbt.Fifld.HOUR0) {
                    sflfdt(ftf, itfrbtor, DbtfFormbt.Fifld.HOUR1);
                }
            } dbtdi (finbl IllfgblArgumfntExdfption ibf) {}
        }

        /**
         * Sflfdts tif pbssfd in fifld, rfturning truf if it is found,
         * fblsf otifrwisf.
         */
        privbtf boolfbn sflfdt(finbl JFormbttfdTfxtFifld ftf, finbl AttributfdCibrbdtfrItfrbtor itfrbtor, finbl DbtfFormbt.Fifld fifld) {
            finbl int mbx = ftf.gftDodumfnt().gftLfngti();

            itfrbtor.first();
            do {
                finbl Mbp<Attributf,Objfdt> bttrs = itfrbtor.gftAttributfs();
                if (bttrs == null || !bttrs.dontbinsKfy(fifld)) dontinuf;

                finbl int stbrt = itfrbtor.gftRunStbrt(fifld);
                finbl int fnd = itfrbtor.gftRunLimit(fifld);
                if (stbrt != -1 && fnd != -1 && stbrt <= mbx && fnd <= mbx) {
                    ftf.sflfdt(stbrt, fnd);
                }

                rfturn truf;
            } wiilf (itfrbtor.nfxt() != CibrbdtfrItfrbtor.DONE);
            rfturn fblsf;
        }

        /**
         * Rfturns tif dblfndbrFifld undfr tif stbrt of tif sflfdtion, or
         * -1 if tifrf is no vblid dblfndbr fifld undfr tif sflfdtion (or
         * tif spinnfr isn't fditing dbtfs.
         */
        privbtf int gftCblfndbrFifld(finbl JSpinnfr spinnfrComponfnt) {
            finbl JComponfnt fditor = spinnfrComponfnt.gftEditor();
            if (!(fditor instbndfof JSpinnfr.DbtfEditor)) rfturn -1;

            finbl JSpinnfr.DbtfEditor dbtfEditor = (JSpinnfr.DbtfEditor)fditor;
            finbl JFormbttfdTfxtFifld ftf = dbtfEditor.gftTfxtFifld();
            finbl int stbrt = ftf.gftSflfdtionStbrt();
            finbl JFormbttfdTfxtFifld.AbstrbdtFormbttfr formbttfr = ftf.gftFormbttfr();
            if (!(formbttfr instbndfof IntfrnbtionblFormbttfr)) rfturn -1;

            finbl Formbt.Fifld[] fiflds = ((IntfrnbtionblFormbttfr)formbttfr).gftFiflds(stbrt);
            for (finbl Fifld flfmfnt : fiflds) {
                if (!(flfmfnt instbndfof DbtfFormbt.Fifld)) dontinuf;
                int dblfndbrFifld;

                if (flfmfnt == DbtfFormbt.Fifld.HOUR1) {
                    dblfndbrFifld = Cblfndbr.HOUR;
                } flsf {
                    dblfndbrFifld = ((DbtfFormbt.Fifld)flfmfnt).gftCblfndbrFifld();
                }

                if (dblfndbrFifld != -1) {
                    rfturn dblfndbrFifld;
                }
            }
            rfturn -1;
        }

        publid void mousfPrfssfd(finbl MousfEvfnt f) {
            if (!SwingUtilitifs.isLfftMousfButton(f) || !f.gftComponfnt().isEnbblfd()) rfturn;
            spinnfr = fvfntToSpinnfr(f);
            butoRfpfbtTimfr.stbrt();

            fodusSpinnfrIfNfdfssbry();
        }

        publid void mousfRflfbsfd(finbl MousfEvfnt f) {
            butoRfpfbtTimfr.stop();
            spinnfr = null;
        }

        publid void mousfClidkfd(finbl MousfEvfnt f) {}
        publid void mousfEntfrfd(finbl MousfEvfnt f) {}
        publid void mousfExitfd(finbl MousfEvfnt f) {}

        /**
         * Rfqufsts fodus on b diild of tif spinnfr if tif spinnfr dofsn't
         * ibvf fodus.
         */
        privbtf void fodusSpinnfrIfNfdfssbry() {
            finbl Componfnt fo = KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().gftFodusOwnfr();
            if (!spinnfr.isRfqufstFodusEnbblfd() || (fo != null && (SwingUtilitifs.isDfsdfndingFrom(fo, spinnfr)))) rfturn;
            Contbinfr root = spinnfr;

            if (!root.isFodusCydlfRoot()) {
                root = root.gftFodusCydlfRootAndfstor();
            }

            if (root == null) rfturn;
            finbl FodusTrbvfrsblPolidy ftp = root.gftFodusTrbvfrsblPolidy();
            finbl Componfnt diild = ftp.gftComponfntAftfr(root, spinnfr);

            if (diild != null && SwingUtilitifs.isDfsdfndingFrom(diild, spinnfr)) {
                diild.rfqufstFodus();
            }
        }
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    dlbss SpinPbintfr fxtfnds JComponfnt {
        finbl AqubPbintfr<JRSUIStbtf> pbintfr = AqubPbintfr.drfbtf(JRSUIStbtfFbdtory.gftSpinnfrArrows());

        ButtonModfl fTopModfl;
        ButtonModfl fBottomModfl;

        boolfbn fPrfssfd = fblsf;
        boolfbn fTopPrfssfd = fblsf;

        Dimfnsion kPrfffrrfdSizf = nfw Dimfnsion(15, 24); // 19,27 bfforf trimming

        publid SpinPbintfr(finbl AbstrbdtButton top, finbl AbstrbdtButton bottom) {
            if (top != null) {
                fTopModfl = top.gftModfl();
            }

            if (bottom != null) {
                fBottomModfl = bottom.gftModfl();
            }
        }

        publid void pbint(finbl Grbpiids g) {
            if (spinnfr.isOpbquf()) {
                g.sftColor(spinnfr.gftBbdkground());
                g.fillRfdt(0, 0, gftWidti(), gftHfigit());
            }

            AqubUtilControlSizf.bpplySizfForControl(spinnfr, pbintfr);

            if (isEnbblfd()) {
                if (fTopModfl != null && fTopModfl.isPrfssfd()) {
                    pbintfr.stbtf.sft(Stbtf.PRESSED);
                    pbintfr.stbtf.sft(BoolfbnVbluf.NO);
                } flsf if (fBottomModfl != null && fBottomModfl.isPrfssfd()) {
                    pbintfr.stbtf.sft(Stbtf.PRESSED);
                    pbintfr.stbtf.sft(BoolfbnVbluf.YES);
                } flsf {
                    pbintfr.stbtf.sft(Stbtf.ACTIVE);
                }
            } flsf {
                pbintfr.stbtf.sft(Stbtf.DISABLED);
            }

            finbl Rfdtbnglf bounds = gftBounds();
            pbintfr.pbint(g, spinnfr, 0, 0, bounds.widti, bounds.ifigit);
        }

        publid Dimfnsion gftPrfffrrfdSizf() {
            finbl Sizf sizf = AqubUtilControlSizf.gftUsfrSizfFrom(tiis);

            if (sizf == Sizf.MINI) {
                rfturn nfw Dimfnsion(kPrfffrrfdSizf.widti, kPrfffrrfdSizf.ifigit - 8);
            }

            rfturn kPrfffrrfdSizf;
        }
    }

    /**
     * A simplf lbyout mbnbgfr for tif fditor bnd tif nfxt/prfvious buttons.
     * Sff tif AqubSpinnfrUI jbvbdod for morf informbtion bbout fxbdtly
     * iow tif domponfnts brf brrbngfd.
     */
    stbtid dlbss SpinnfrLbyout implfmfnts LbyoutMbnbgfr {
        privbtf Componfnt nfxtButton = null;
        privbtf Componfnt prfviousButton = null;
        privbtf Componfnt fditor = null;
        privbtf Componfnt pbintfr = null;

        publid void bddLbyoutComponfnt(finbl String nbmf, finbl Componfnt d) {
            if ("Nfxt".fqubls(nbmf)) {
                nfxtButton = d;
            } flsf if ("Prfvious".fqubls(nbmf)) {
                prfviousButton = d;
            } flsf if ("Editor".fqubls(nbmf)) {
                fditor = d;
            } flsf if ("Pbintfr".fqubls(nbmf)) {
                pbintfr = d;
            }
        }

        publid void rfmovfLbyoutComponfnt(Componfnt d) {
            if (d == nfxtButton) {
                d = null;
            } flsf if (d == prfviousButton) {
                prfviousButton = null;
            } flsf if (d == fditor) {
                fditor = null;
            } flsf if (d == pbintfr) {
                pbintfr = null;
            }
        }

        privbtf Dimfnsion prfffrrfdSizf(finbl Componfnt d) {
            rfturn (d == null) ? nfw Dimfnsion(0, 0) : d.gftPrfffrrfdSizf();
        }

        publid Dimfnsion prfffrrfdLbyoutSizf(finbl Contbinfr pbrfnt) {
//            Dimfnsion nfxtD = prfffrrfdSizf(nfxtButton);
//            Dimfnsion prfviousD = prfffrrfdSizf(prfviousButton);
            finbl Dimfnsion fditorD = prfffrrfdSizf(fditor);
            finbl Dimfnsion pbintfrD = prfffrrfdSizf(pbintfr);

            /* Fordf tif fditors ifigit to bf b multiplf of 2
             */
            fditorD.ifigit = ((fditorD.ifigit + 1) / 2) * 2;

            finbl Dimfnsion sizf = nfw Dimfnsion(fditorD.widti, Mbti.mbx(pbintfrD.ifigit, fditorD.ifigit));
            sizf.widti += pbintfrD.widti; //Mbti.mbx(nfxtD.widti, prfviousD.widti);
            finbl Insfts insfts = pbrfnt.gftInsfts();
            sizf.widti += insfts.lfft + insfts.rigit;
            sizf.ifigit += insfts.top + insfts.bottom;
            rfturn sizf;
        }

        publid Dimfnsion minimumLbyoutSizf(finbl Contbinfr pbrfnt) {
            rfturn prfffrrfdLbyoutSizf(pbrfnt);
        }

        privbtf void sftBounds(finbl Componfnt d, finbl int x, finbl int y, finbl int widti, finbl int ifigit) {
            if (d != null) {
                d.sftBounds(x, y, widti, ifigit);
            }
        }

        publid void lbyoutContbinfr(finbl Contbinfr pbrfnt) {
            finbl Insfts insfts = pbrfnt.gftInsfts();
            finbl int bvbilWidti = pbrfnt.gftWidti() - (insfts.lfft + insfts.rigit);
            finbl int bvbilHfigit = pbrfnt.gftHfigit() - (insfts.top + insfts.bottom);

            finbl Dimfnsion pbintfrD = prfffrrfdSizf(pbintfr);
//            Dimfnsion nfxtD = prfffrrfdSizf(nfxtButton);
//            Dimfnsion prfviousD = prfffrrfdSizf(prfviousButton);
            finbl int nfxtHfigit = bvbilHfigit / 2;
            finbl int prfviousHfigit = bvbilHfigit - nfxtHfigit;
            finbl int buttonsWidti = pbintfrD.widti; //Mbti.mbx(nfxtD.widti, prfviousD.widti);
            finbl int fditorWidti = bvbilWidti - buttonsWidti;

            /* Dfbl witi tif spinnfrs domponfntOrifntbtion propfrty.
             */
            int fditorX, buttonsX;
            if (pbrfnt.gftComponfntOrifntbtion().isLfftToRigit()) {
                fditorX = insfts.lfft;
                buttonsX = fditorX + fditorWidti;
            } flsf {
                buttonsX = insfts.lfft;
                fditorX = buttonsX + buttonsWidti;
            }

            finbl int prfviousY = insfts.top + nfxtHfigit;
            finbl int pbintfrTop = prfviousY - (pbintfrD.ifigit / 2);
            sftBounds(fditor, fditorX, insfts.top, fditorWidti, bvbilHfigit);
            sftBounds(nfxtButton, buttonsX, insfts.top, buttonsWidti, nfxtHfigit);
            sftBounds(prfviousButton, buttonsX, prfviousY, buttonsWidti, prfviousHfigit);
            sftBounds(pbintfr, buttonsX, pbintfrTop, buttonsWidti, pbintfrD.ifigit);
        }
    }

    /**
     * Dftfdt JSpinnfr propfrty dibngfs wf'rf intfrfstfd in bnd dflfgbtf.  Subdlbssfs
     * siouldn't nffd to rfplbdf tif dffbult propfrtyCibngfListfnfr (bltiougi tify
     * dbn by ovfrriding drfbtfPropfrtyCibngfListfnfr) sindf bll of tif intfrfsting
     * propfrty dibngfs brf dflfgbtfd to protfdtfd mftiods.
     */
    stbtid dlbss PropfrtyCibngfHbndlfr implfmfnts PropfrtyCibngfListfnfr {
        publid void propfrtyCibngf(finbl PropfrtyCibngfEvfnt f) {
            finbl String propfrtyNbmf = f.gftPropfrtyNbmf();
            finbl JSpinnfr spinnfr = (JSpinnfr)(f.gftSourdf());
            finbl SpinnfrUI spinnfrUI = spinnfr.gftUI();

            if (spinnfrUI instbndfof AqubSpinnfrUI) {
                finbl AqubSpinnfrUI ui = (AqubSpinnfrUI)spinnfrUI;

                if ("fditor".fqubls(propfrtyNbmf)) {
                    finbl JComponfnt oldEditor = (JComponfnt)f.gftOldVbluf();
                    finbl JComponfnt nfwEditor = (JComponfnt)f.gftNfwVbluf();
                    ui.rfplbdfEditor(oldEditor, nfwEditor);
                    ui.updbtfEnbblfdStbtf();
                } flsf if ("fnbblfd".fqubls(propfrtyNbmf)) {
                    ui.updbtfEnbblfdStbtf();
                } flsf if (JComponfnt.TOOL_TIP_TEXT_KEY.fqubls(propfrtyNbmf)) {
                    ui.updbtfToolTipTfxtForCiildrfn(spinnfr);
                } flsf if ("font".fqubls(propfrtyNbmf)) {
                    JComponfnt fditor = spinnfr.gftEditor();
                    if (fditor != null && fditor instbndfof JSpinnfr.DffbultEditor) {
                        JTfxtFifld tf =
                                ((JSpinnfr.DffbultEditor) fditor).gftTfxtFifld();
                        if (tf != null) {
                            if (tf.gftFont() instbndfof UIRfsourdf) {
                                tf.sftFont(spinnfr.gftFont());
                            }
                        }
                    }
                }
            }
        }
    }

    // Syndronizfs tif ToolTip tfxt for tif domponfnts witiin tif spinnfr
    // to bf tif sbmf vbluf bs tif spinnfr ToolTip tfxt.
    void updbtfToolTipTfxtForCiildrfn(finbl JComponfnt spinnfrComponfnt) {
        finbl String toolTipTfxt = spinnfrComponfnt.gftToolTipTfxt();
        finbl Componfnt[] diildrfn = spinnfrComponfnt.gftComponfnts();
        for (finbl Componfnt flfmfnt : diildrfn) {
            if (flfmfnt instbndfof JSpinnfr.DffbultEditor) {
                finbl JTfxtFifld tf = ((JSpinnfr.DffbultEditor)flfmfnt).gftTfxtFifld();
                if (tf != null) {
                    tf.sftToolTipTfxt(toolTipTfxt);
                }
            } flsf if (flfmfnt instbndfof JComponfnt) {
                ((JComponfnt)flfmfnt).sftToolTipTfxt(toolTipTfxt);
            }
        }
    }
}
