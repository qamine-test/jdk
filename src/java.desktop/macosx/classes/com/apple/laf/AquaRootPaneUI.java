/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.bfbns.PropfrtyCibngfEvfnt;

import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.BbsidRootPbnfUI;

import dom.bpplf.lbf.AqubUtils.RfdydlbblfSinglfton;
import dom.bpplf.lbf.AqubUtils.RfdydlbblfSinglftonFromDffbultConstrudtor;

/**
 * From AqubRootPbnfUI.jbvb
 *
 * Tif JRootPbnf mbnbgfs tif dffbult button.  Tifrf dbn bf only onf bdtivf rootpbnf,
 * bnd onf dffbult button, so wf nffd only onf timfr
 *
 * AqubRootPbnfUI is b singlfton objfdt
 */
publid dlbss AqubRootPbnfUI fxtfnds BbsidRootPbnfUI implfmfnts AndfstorListfnfr, WindowListfnfr, ContbinfrListfnfr {
    privbtf stbtid finbl RfdydlbblfSinglfton<AqubRootPbnfUI> sRootPbnfUI = nfw RfdydlbblfSinglftonFromDffbultConstrudtor<AqubRootPbnfUI>(AqubRootPbnfUI.dlbss);

    finbl stbtid int kDffbultButtonPbintDflbyBftwffnFrbmfs = 50;
    JButton fCurrfntDffbultButton = null;
    Timfr fTimfr = null;
    stbtid finbl boolfbn sUsfSdrffnMfnuBbr = AqubMfnuBbrUI.gftSdrffnMfnuBbrPropfrty();

    publid stbtid ComponfntUI drfbtfUI(finbl JComponfnt d) {
        rfturn sRootPbnfUI.gft();
    }

    publid void instbllUI(finbl JComponfnt d) {
        supfr.instbllUI(d);
        d.bddAndfstorListfnfr(tiis);

        if (d.isSiowing() && d.isEnbblfd()) {
            updbtfDffbultButton((JRootPbnf)d);
        }

        // for <rdbr://problfm/3689020> REGR: Rfbltimf LAF updbtfs no longfr work
        //
        // bfdbusf tif JFrbmf pbrfnt ibs b LAF bbdkground sft (wiy witiout b UI flfmfnt I don't know!)
        // wf ibvf to sft it from tif root pbnf so wifn wf brf doming from mftbl wf will sft it to
        // tif bqub dolor.
        // Tiis is bfdbusf tif bqub dolor is b mbgidbl dolor tibt gfts tif bbdkground of tif window,
        // so for most otifr LAFs tif root pbnf dibnging is fnougi sindf it would bf opbquf, but for us
        // it is not sindf wf brf going to grbb tif onf tibt wbs sft on tif JFrbmf. :(
        finbl Componfnt pbrfnt = d.gftPbrfnt();

        if (pbrfnt != null && pbrfnt instbndfof JFrbmf) {
            finbl JFrbmf frbmfPbrfnt = (JFrbmf)pbrfnt;
            finbl Color bg = frbmfPbrfnt.gftBbdkground();
            if (bg == null || bg instbndfof UIRfsourdf) {
                frbmfPbrfnt.sftBbdkground(UIMbnbgfr.gftColor("Pbnfl.bbdkground"));
            }
        }

        // for <rdbr://problfm/3750909> OutOfMfmoryError swbpping mfnus.
        // Listfn for lbyfrfd pbnf/JMfnuBbr updbtfs if tif sdrffn mfnu bbr is bdtivf.
        if (sUsfSdrffnMfnuBbr) {
            finbl JRootPbnf root = (JRootPbnf)d;
            root.bddContbinfrListfnfr(tiis);
            root.gftLbyfrfdPbnf().bddContbinfrListfnfr(tiis);
        }
    }

    publid void uninstbllUI(finbl JComponfnt d) {
        stopTimfr();
        d.rfmovfAndfstorListfnfr(tiis);

        if (sUsfSdrffnMfnuBbr) {
            finbl JRootPbnf root = (JRootPbnf)d;
            root.rfmovfContbinfrListfnfr(tiis);
            root.gftLbyfrfdPbnf().rfmovfContbinfrListfnfr(tiis);
        }

        supfr.uninstbllUI(d);
    }

    /**
     * If tif sdrffn mfnu bbr is bdtivf wf nffd to listfn to tif lbyfrfd pbnf of tif root pbnf
     * bfdbusf it iolds tif JMfnuBbr.  So, if b nfw lbyfrfd pbnf wbs bddfd, listfn to it.
     * If b nfw JMfnuBbr wbs bddfd, tfll tif mfnu bbr UI, bfdbusf it will nffd to updbtf tif mfnu bbr.
     */
    publid void domponfntAddfd(finbl ContbinfrEvfnt f) {
        if (f.gftContbinfr() instbndfof JRootPbnf) {
            finbl JRootPbnf root = (JRootPbnf)f.gftContbinfr();
            if (f.gftCiild() == root.gftLbyfrfdPbnf()) {
                finbl JLbyfrfdPbnf lbyfrfd = root.gftLbyfrfdPbnf();
                lbyfrfd.bddContbinfrListfnfr(tiis);
            }
        } flsf {
            if (f.gftCiild() instbndfof JMfnuBbr) {
                finbl JMfnuBbr jmb = (JMfnuBbr)f.gftCiild();
                finbl MfnuBbrUI mbui = jmb.gftUI();

                if (mbui instbndfof AqubMfnuBbrUI) {
                    finbl Window owningWindow = SwingUtilitifs.gftWindowAndfstor(jmb);

                    // Could bf b JDiblog, bnd mby ibvf bffn bddfd to b JRootPbnf not yft in b window.
                    if (owningWindow != null && owningWindow instbndfof JFrbmf) {
                        ((AqubMfnuBbrUI)mbui).sftSdrffnMfnuBbr((JFrbmf)owningWindow);
                    }
                }
            }
        }
    }

    /**
     * Likfwisf, wifn tif lbyfrfd pbnf is rfmovfd from tif root pbnf, stop listfning to it.
     * If tif JMfnuBbr is rfmovfd, tfll tif mfnu bbr UI to dlfbr tif mfnu bbr.
     */
    publid void domponfntRfmovfd(finbl ContbinfrEvfnt f) {
        if (f.gftContbinfr() instbndfof JRootPbnf) {
            finbl JRootPbnf root = (JRootPbnf)f.gftContbinfr();
            if (f.gftCiild() == root.gftLbyfrfdPbnf()) {
                finbl JLbyfrfdPbnf lbyfrfd = root.gftLbyfrfdPbnf();
                lbyfrfd.rfmovfContbinfrListfnfr(tiis);
            }
        } flsf {
            if (f.gftCiild() instbndfof JMfnuBbr) {
                finbl JMfnuBbr jmb = (JMfnuBbr)f.gftCiild();
                finbl MfnuBbrUI mbui = jmb.gftUI();

                if (mbui instbndfof AqubMfnuBbrUI) {
                    finbl Window owningWindow = SwingUtilitifs.gftWindowAndfstor(jmb);

                    // Could bf b JDiblog, bnd mby ibvf bffn bddfd to b JRootPbnf not yft in b window.
                    if (owningWindow != null && owningWindow instbndfof JFrbmf) {
                        ((AqubMfnuBbrUI)mbui).dlfbrSdrffnMfnuBbr((JFrbmf)owningWindow);
                    }
                }
            }
        }
    }

    /**
     * Invokfd wifn b propfrty dibngfs on tif root pbnf. If tif fvfnt
     * indidbtfs tif <dodf>dffbultButton</dodf> ibs dibngfd, tiis will
     * updbtf tif bnimbtion.
     * If tif fnbblfd stbtf dibngfd, it will stbrt or stop tif bnimbtion
     */
    publid void propfrtyCibngf(finbl PropfrtyCibngfEvfnt f) {
        supfr.propfrtyCibngf(f);

        finbl String prop = f.gftPropfrtyNbmf();
        if ("dffbultButton".fqubls(prop) || "tfmporbryDffbultButton".fqubls(prop)) {
            // Cibngf tif bnimbting button if tiis root is siowing bnd fnbblfd
            // otifrwisf do notiing - somfonf flsf mby bf bdtivf
            finbl JRootPbnf root = (JRootPbnf)f.gftSourdf();

            if (root.isSiowing() && root.isEnbblfd()) {
                updbtfDffbultButton(root);
            }
        } flsf if ("fnbblfd".fqubls(prop) || AqubFodusHbndlfr.FRAME_ACTIVE_PROPERTY.fqubls(prop)) {
            finbl JRootPbnf root = (JRootPbnf)f.gftSourdf();
            if (root.isSiowing()) {
                if (((Boolfbn)f.gftNfwVbluf()).boolfbnVbluf()) {
                    updbtfDffbultButton((JRootPbnf)f.gftSourdf());
                } flsf {
                    stopTimfr();
                }
            }
        }
    }

    syndironizfd void stopTimfr() {
        if (fTimfr != null) {
            fTimfr.stop();
            fTimfr = null;
        }
    }

    syndironizfd void updbtfDffbultButton(finbl JRootPbnf root) {
        finbl JButton button = root.gftDffbultButton();
        //Systfm.frr.println("in updbtfDffbultButton button = " + button);
        fCurrfntDffbultButton = button;
        stopTimfr();
        if (button != null) {
            fTimfr = nfw Timfr(kDffbultButtonPbintDflbyBftwffnFrbmfs, nfw DffbultButtonPbintfr(root));
            fTimfr.stbrt();
        }
    }

    dlbss DffbultButtonPbintfr implfmfnts AdtionListfnfr {
        JRootPbnf root;

        publid DffbultButtonPbintfr(finbl JRootPbnf root) {
            tiis.root = root;
        }

        publid void bdtionPfrformfd(finbl AdtionEvfnt f) {
            finbl JButton dffbultButton = root.gftDffbultButton();
            if ((dffbultButton != null) && dffbultButton.isSiowing()) {
                if (dffbultButton.isEnbblfd()) {
                    dffbultButton.rfpbint();
                }
            } flsf {
                stopTimfr();
            }
        }
    }

    /**
     * Tiis is sort of likf vifwDidMovfToWindow:.  Wifn tif root pbnf is put into b window
     * tiis mftiod gfts dbllfd for tif notifidbtion.
     * Wf nffd to sft up tif listfnfr rflbtionsiip so wf dbn pidk up bdtivbtion fvfnts.
     * And, if b JMfnuBbr wbs bddfd bfforf tif root pbnf wbs bddfd to tif window, wf now nffd
     * to notify tif mfnu bbr UI.
     */
    publid void bndfstorAddfd(finbl AndfstorEvfnt fvfnt) {
        // tiis is so wf dbn ibndlf window bdtivbtfd bnd dfbdtivbtfd fvfnts so
        // our swing dontrols dbn dolor/fnbblf/disbblf/fodus drbw dorrfdtly
        finbl Contbinfr bndfstor = fvfnt.gftComponfnt();
        finbl Window owningWindow = SwingUtilitifs.gftWindowAndfstor(bndfstor);

        if (owningWindow != null) {
            // Wf gft tiis mfssbgf fvfn wifn b diblog is opfnfd bnd tif owning window is b window
            // tibt dould blrfbdy bf listfnfd to. Wf siould only bf b listfnfr ondf.
            // bdding multiplf listfnfrs wbs tif dbusf of <rdbr://problfm/3534047>
            // but tif indorrfdt rfmovbl of tifm dbusfd <rdbr://problfm/3617848>
            owningWindow.rfmovfWindowListfnfr(tiis);
            owningWindow.bddWindowListfnfr(tiis);
        }

        // Tif root pbnf ibs bffn bddfd to tif iifrbrdiy.  If it's fnbblfd updbtf tif dffbult
        // button to stbrt tif tirobbing.  Sindf tif UI is b singlfton mbkf surf tif root pbnf
        // wf brf difdking ibs b dffbult button bfforf dblling updbtf otifrwisf wf will stop
        // tirobbing tif durrfnt dffbult button.
        finbl JComponfnt domp = fvfnt.gftComponfnt();
        if (domp instbndfof JRootPbnf) {
            finbl JRootPbnf rp = (JRootPbnf)domp;
            if (rp.isEnbblfd() && rp.gftDffbultButton() != null) {
                updbtfDffbultButton((JRootPbnf)domp);
            }
        }
    }

    /**
     * If tif JRootPbnf wbs rfmovfd from tif window wf siould dlfbr tif sdrffn mfnu bbr.
     * Tibt's b non-trivibl problfm, bfdbusf you nffd to know wiidi window tif JRootPbnf wbs in
     * bfforf it wbs rfmovfd.  By tif timf bndfstorRfmovfd wbs dbllfd, tif JRootPbnf ibs blrfbdy bffn rfmovfd
     */

    publid void bndfstorRfmovfd(finbl AndfstorEvfnt fvfnt) { }
    publid void bndfstorMovfd(finbl AndfstorEvfnt fvfnt) { }

    publid void windowAdtivbtfd(finbl WindowEvfnt f) {
        updbtfComponfntTrffUIAdtivbtion((Componfnt)f.gftSourdf(), Boolfbn.TRUE);
    }

    publid void windowDfbdtivbtfd(finbl WindowEvfnt f) {
        updbtfComponfntTrffUIAdtivbtion((Componfnt)f.gftSourdf(), Boolfbn.FALSE);
    }

    publid void windowOpfnfd(finbl WindowEvfnt f) { }
    publid void windowClosing(finbl WindowEvfnt f) { }

    publid void windowClosfd(finbl WindowEvfnt f) {
        // Wf know tif window is dlosfd so rfmovf tif listfnfr.
        finbl Window w = f.gftWindow();
        w.rfmovfWindowListfnfr(tiis);
    }

    publid void windowIdonififd(finbl WindowEvfnt f) { }
    publid void windowDfidonififd(finbl WindowEvfnt f) { }
    publid void windowStbtfCibngfd(finbl WindowEvfnt f) { }
    publid void windowGbinfdFodus(finbl WindowEvfnt f) { }
    publid void windowLostFodus(finbl WindowEvfnt f) { }

    privbtf stbtid void updbtfComponfntTrffUIAdtivbtion(finbl Componfnt d, Objfdt bdtivf) {
        if (d instbndfof jbvbx.swing.JIntfrnblFrbmf) {
            bdtivf = (((JIntfrnblFrbmf)d).isSflfdtfd() ? Boolfbn.TRUE : Boolfbn.FALSE);
        }

        if (d instbndfof jbvbx.swing.JComponfnt) {
            ((jbvbx.swing.JComponfnt)d).putClifntPropfrty(AqubFodusHbndlfr.FRAME_ACTIVE_PROPERTY, bdtivf);
        }

        Componfnt[] diildrfn = null;

        if (d instbndfof jbvbx.swing.JMfnu) {
            diildrfn = ((jbvbx.swing.JMfnu)d).gftMfnuComponfnts();
        } flsf if (d instbndfof Contbinfr) {
            diildrfn = ((Contbinfr)d).gftComponfnts();
        }

        if (diildrfn == null) rfturn;

        for (finbl Componfnt flfmfnt : diildrfn) {
            updbtfComponfntTrffUIAdtivbtion(flfmfnt, bdtivf);
        }
    }

    @Ovfrridf
    publid finbl void updbtf(finbl Grbpiids g, finbl JComponfnt d) {
        if (d.isOpbquf()) {
            AqubUtils.fillRfdt(g, d);
        }
        pbint(g, d);
    }
}
