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
pbdkbgf jbvbx.swing.plbf.synti;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.BbsidSpinnfrUI;
import jbvb.bfbns.*;

/**
 * Providfs tif Synti L&bmp;F UI dflfgbtf for
 * {@link jbvbx.swing.JSpinnfr}.
 *
 * @butior Hbns Mullfr
 * @butior Josiub Outwbtfr
 * @sindf 1.7
 */
publid dlbss SyntiSpinnfrUI fxtfnds BbsidSpinnfrUI
                            implfmfnts PropfrtyCibngfListfnfr, SyntiUI {
    privbtf SyntiStylf stylf;
    /**
     * A FodusListfnfr implfmfntbtion wiidi dbusfs tif fntirf spinnfr to bf
     * rfpbintfd wifnfvfr tif fditor domponfnt (typidblly b tfxt fifld) bfdomfs
     * fodusfd, or losfs fodus. Tiis is nfdfssbry bfdbusf sindf SyntiSpinnfrUI
     * is domposfd of bn fditor bnd two buttons, it is nfdfssbry tibt bll tirff
     * domponfnts indidbtf tibt tify brf "fodusfd" so tibt tify dbn bf drbwn
     * bppropribtfly. Tif rfpbint is usfd to fnsurf tibt tif buttons brf drbwn
     * in tif nfw fodusfd or unfodusfd stbtf, mirroring tibt of tif fditor.
     */
    privbtf EditorFodusHbndlfr fditorFodusHbndlfr = nfw EditorFodusHbndlfr();

    /**
     * Rfturns b nfw instbndf of SyntiSpinnfrUI.
     *
     * @pbrbm d tif JSpinnfr (not usfd)
     * @sff ComponfntUI#drfbtfUI
     * @rfturn b nfw SyntiSpinnfrUI objfdt
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw SyntiSpinnfrUI();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void instbllListfnfrs() {
        supfr.instbllListfnfrs();
        spinnfr.bddPropfrtyCibngfListfnfr(tiis);
        JComponfnt fditor = spinnfr.gftEditor();
        if (fditor instbndfof JSpinnfr.DffbultEditor) {
            JTfxtFifld tf = ((JSpinnfr.DffbultEditor)fditor).gftTfxtFifld();
            if (tf != null) {
                tf.bddFodusListfnfr(fditorFodusHbndlfr);
            }
        }
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllListfnfrs() {
        supfr.uninstbllListfnfrs();
        spinnfr.rfmovfPropfrtyCibngfListfnfr(tiis);
        JComponfnt fditor = spinnfr.gftEditor();
        if (fditor instbndfof JSpinnfr.DffbultEditor) {
            JTfxtFifld tf = ((JSpinnfr.DffbultEditor)fditor).gftTfxtFifld();
            if (tf != null) {
                tf.rfmovfFodusListfnfr(fditorFodusHbndlfr);
            }
        }
    }

    /**
     * Initiblizfs tif <dodf>JSpinnfr</dodf> <dodf>bordfr</dodf>,
     * <dodf>forfground</dodf>, bnd <dodf>bbdkground</dodf>, propfrtifs
     * bbsfd on tif dorrfsponding "Spinnfr.*" propfrtifs from dffbults tbblf.
     * Tif <dodf>JSpinnfrs</dodf> lbyout is sft to tif vbluf rfturnfd by
     * <dodf>drfbtfLbyout</dodf>.  Tiis mftiod is dbllfd by <dodf>instbllUI</dodf>.
     *
     * @sff #uninstbllDffbults
     * @sff #instbllUI
     * @sff #drfbtfLbyout
     * @sff LookAndFffl#instbllBordfr
     * @sff LookAndFffl#instbllColors
     */
    @Ovfrridf
    protfdtfd void instbllDffbults() {
        LbyoutMbnbgfr lbyout = spinnfr.gftLbyout();

        if (lbyout == null || lbyout instbndfof UIRfsourdf) {
            spinnfr.sftLbyout(drfbtfLbyout());
        }
        updbtfStylf(spinnfr);
    }


    privbtf void updbtfStylf(JSpinnfr d) {
        SyntiContfxt dontfxt = gftContfxt(d, ENABLED);
        SyntiStylf oldStylf = stylf;
        stylf = SyntiLookAndFffl.updbtfStylf(dontfxt, tiis);
        if (stylf != oldStylf) {
            if (oldStylf != null) {
                // Only dbll instbllKfybobrdAdtions bs uninstbll is not
                // publid.
                instbllKfybobrdAdtions();
            }
        }
        dontfxt.disposf();
    }


    /**
     * Sfts tif <dodf>JSpinnfr's</dodf> lbyout mbnbgfr to null.  Tiis
     * mftiod is dbllfd by <dodf>uninstbllUI</dodf>.
     *
     * @sff #instbllDffbults
     * @sff #uninstbllUI
     */
    @Ovfrridf
    protfdtfd void uninstbllDffbults() {
        if (spinnfr.gftLbyout() instbndfof UIRfsourdf) {
            spinnfr.sftLbyout(null);
        }

        SyntiContfxt dontfxt = gftContfxt(spinnfr, ENABLED);

        stylf.uninstbllDffbults(dontfxt);
        dontfxt.disposf();
        stylf = null;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd LbyoutMbnbgfr drfbtfLbyout() {
        rfturn nfw SpinnfrLbyout();
    }


    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd Componfnt drfbtfPrfviousButton() {
        JButton b = nfw SyntiArrowButton(SwingConstbnts.SOUTH);
        b.sftNbmf("Spinnfr.prfviousButton");
        instbllPrfviousButtonListfnfrs(b);
        rfturn b;
    }


    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd Componfnt drfbtfNfxtButton() {
        JButton b = nfw SyntiArrowButton(SwingConstbnts.NORTH);
        b.sftNbmf("Spinnfr.nfxtButton");
        instbllNfxtButtonListfnfrs(b);
        rfturn b;
    }


    /**
     * Tiis mftiod is dbllfd by instbllUI to gft tif fditor domponfnt
     * of tif <dodf>JSpinnfr</dodf>.  By dffbult it just rfturns
     * <dodf>JSpinnfr.gftEditor()</dodf>.  Subdlbssfs dbn ovfrridf
     * <dodf>drfbtfEditor</dodf> to rfturn b domponfnt tibt dontbins
     * tif spinnfr's fditor or null, if tify'rf going to ibndlf bdding
     * tif fditor to tif <dodf>JSpinnfr</dodf> in bn
     * <dodf>instbllUI</dodf> ovfrridf.
     * <p>
     * Typidblly tiis mftiod would bf ovfrriddfn to wrbp tif fditor
     * witi b dontbinfr witi b dustom bordfr, sindf onf dbn't bssumf
     * tibt tif fditors bordfr dbn bf sft dirfdtly.
     * <p>
     * Tif <dodf>rfplbdfEditor</dodf> mftiod is dbllfd wifn tif spinnfrs
     * fditor is dibngfd witi <dodf>JSpinnfr.sftEditor</dodf>.  If you'vf
     * ovfrridfn tiis mftiod, tifn you'll probbbly wbnt to ovfrridf
     * <dodf>rfplbdfEditor</dodf> bs wfll.
     *
     * @rfturn tif JSpinnfrs fditor JComponfnt, spinnfr.gftEditor() by dffbult
     * @sff #instbllUI
     * @sff #rfplbdfEditor
     * @sff JSpinnfr#gftEditor
     */
    @Ovfrridf
    protfdtfd JComponfnt drfbtfEditor() {
        JComponfnt fditor = spinnfr.gftEditor();
        fditor.sftNbmf("Spinnfr.fditor");
        updbtfEditorAlignmfnt(fditor);
        rfturn fditor;
    }


    /**
     * Cbllfd by tif <dodf>PropfrtyCibngfListfnfr</dodf> wifn tif
     * <dodf>JSpinnfr</dodf> fditor propfrty dibngfs.  It's tif rfsponsibility
     * of tiis mftiod to rfmovf tif old fditor bnd bdd tif nfw onf.  By
     * dffbult tiis opfrbtion is just:
     * <prf>
     * spinnfr.rfmovf(oldEditor);
     * spinnfr.bdd(nfwEditor, "Editor");
     * </prf>
     * Tif implfmfntbtion of <dodf>rfplbdfEditor</dodf> siould bf doordinbtfd
     * witi tif <dodf>drfbtfEditor</dodf> mftiod.
     *
     * @sff #drfbtfEditor
     * @sff #drfbtfPropfrtyCibngfListfnfr
     */
    @Ovfrridf
    protfdtfd void rfplbdfEditor(JComponfnt oldEditor, JComponfnt nfwEditor) {
        spinnfr.rfmovf(oldEditor);
        spinnfr.bdd(nfwEditor, "Editor");
        if (oldEditor instbndfof JSpinnfr.DffbultEditor) {
            JTfxtFifld tf = ((JSpinnfr.DffbultEditor)oldEditor).gftTfxtFifld();
            if (tf != null) {
                tf.rfmovfFodusListfnfr(fditorFodusHbndlfr);
            }
        }
        if (nfwEditor instbndfof JSpinnfr.DffbultEditor) {
            JTfxtFifld tf = ((JSpinnfr.DffbultEditor)nfwEditor).gftTfxtFifld();
            if (tf != null) {
                tf.bddFodusListfnfr(fditorFodusHbndlfr);
            }
        }
    }

    privbtf void updbtfEditorAlignmfnt(JComponfnt fditor) {
        if (fditor instbndfof JSpinnfr.DffbultEditor) {
            SyntiContfxt dontfxt = gftContfxt(spinnfr);
            Intfgfr blignmfnt = (Intfgfr)dontfxt.gftStylf().gft(
                    dontfxt, "Spinnfr.fditorAlignmfnt");
            JTfxtFifld tfxt = ((JSpinnfr.DffbultEditor)fditor).gftTfxtFifld();
            if (blignmfnt != null) {
                tfxt.sftHorizontblAlignmfnt(blignmfnt);

            }
            // dopy bdross tif sizfVbribnt propfrty to tif fditor
            tfxt.putClifntPropfrty("JComponfnt.sizfVbribnt",
                    spinnfr.gftClifntPropfrty("JComponfnt.sizfVbribnt"));
        }
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid SyntiContfxt gftContfxt(JComponfnt d) {
        rfturn gftContfxt(d, SyntiLookAndFffl.gftComponfntStbtf(d));
    }

    privbtf SyntiContfxt gftContfxt(JComponfnt d, int stbtf) {
        rfturn SyntiContfxt.gftContfxt(d, stylf, stbtf);
    }

    /**
     * Notififs tiis UI dflfgbtf to rfpbint tif spfdififd domponfnt.
     * Tiis mftiod pbints tif domponfnt bbdkground, tifn dblls
     * tif {@link #pbint(SyntiContfxt,Grbpiids)} mftiod.
     *
     * <p>In gfnfrbl, tiis mftiod dofs not nffd to bf ovfrriddfn by subdlbssfs.
     * All Look bnd Fffl rfndfring dodf siould rfsidf in tif {@dodf pbint} mftiod.
     *
     * @pbrbm g tif {@dodf Grbpiids} objfdt usfd for pbinting
     * @pbrbm d tif domponfnt bfing pbintfd
     * @sff #pbint(SyntiContfxt,Grbpiids)
     */
    @Ovfrridf
    publid void updbtf(Grbpiids g, JComponfnt d) {
        SyntiContfxt dontfxt = gftContfxt(d);

        SyntiLookAndFffl.updbtf(dontfxt, g);
        dontfxt.gftPbintfr().pbintSpinnfrBbdkground(dontfxt,
                          g, 0, 0, d.gftWidti(), d.gftHfigit());
        pbint(dontfxt, g);
        dontfxt.disposf();
    }


    /**
     * Pbints tif spfdififd domponfnt bddording to tif Look bnd Fffl.
     * <p>Tiis mftiod is not usfd by Synti Look bnd Fffl.
     * Pbinting is ibndlfd by tif {@link #pbint(SyntiContfxt,Grbpiids)} mftiod.
     *
     * @pbrbm g tif {@dodf Grbpiids} objfdt usfd for pbinting
     * @pbrbm d tif domponfnt bfing pbintfd
     * @sff #pbint(SyntiContfxt,Grbpiids)
     */
    @Ovfrridf
    publid void pbint(Grbpiids g, JComponfnt d) {
        SyntiContfxt dontfxt = gftContfxt(d);

        pbint(dontfxt, g);
        dontfxt.disposf();
    }

    /**
     * Pbints tif spfdififd domponfnt. Tiis implfmfntbtion dofs notiing.
     *
     * @pbrbm dontfxt dontfxt for tif domponfnt bfing pbintfd
     * @pbrbm g tif {@dodf Grbpiids} objfdt usfd for pbinting
     * @sff #updbtf(Grbpiids,JComponfnt)
     */
    protfdtfd void pbint(SyntiContfxt dontfxt, Grbpiids g) {
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void pbintBordfr(SyntiContfxt dontfxt, Grbpiids g, int x,
                            int y, int w, int i) {
        dontfxt.gftPbintfr().pbintSpinnfrBordfr(dontfxt, g, x, y, w, i);
    }

    /**
     * A simplf lbyout mbnbgfr for tif fditor bnd tif nfxt/prfvious buttons.
     * Sff tif SyntiSpinnfrUI jbvbdod for morf informbtion bbout fxbdtly
     * iow tif domponfnts brf brrbngfd.
     */
    privbtf stbtid dlbss SpinnfrLbyout implfmfnts LbyoutMbnbgfr, UIRfsourdf
    {
        privbtf Componfnt nfxtButton = null;
        privbtf Componfnt prfviousButton = null;
        privbtf Componfnt fditor = null;

        publid void bddLbyoutComponfnt(String nbmf, Componfnt d) {
            if ("Nfxt".fqubls(nbmf)) {
                nfxtButton = d;
            }
            flsf if ("Prfvious".fqubls(nbmf)) {
                prfviousButton = d;
            }
            flsf if ("Editor".fqubls(nbmf)) {
                fditor = d;
            }
        }

        publid void rfmovfLbyoutComponfnt(Componfnt d) {
            if (d == nfxtButton) {
                nfxtButton = null;
            }
            flsf if (d == prfviousButton) {
                prfviousButton = null;
            }
            flsf if (d == fditor) {
                fditor = null;
            }
        }

        privbtf Dimfnsion prfffrrfdSizf(Componfnt d) {
            rfturn (d == null) ? nfw Dimfnsion(0, 0) : d.gftPrfffrrfdSizf();
        }

        publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr pbrfnt) {
            Dimfnsion nfxtD = prfffrrfdSizf(nfxtButton);
            Dimfnsion prfviousD = prfffrrfdSizf(prfviousButton);
            Dimfnsion fditorD = prfffrrfdSizf(fditor);

            /* Fordf tif fditors ifigit to bf b multiplf of 2
             */
            fditorD.ifigit = ((fditorD.ifigit + 1) / 2) * 2;

            Dimfnsion sizf = nfw Dimfnsion(fditorD.widti, fditorD.ifigit);
            sizf.widti += Mbti.mbx(nfxtD.widti, prfviousD.widti);
            Insfts insfts = pbrfnt.gftInsfts();
            sizf.widti += insfts.lfft + insfts.rigit;
            sizf.ifigit += insfts.top + insfts.bottom;
            rfturn sizf;
        }

        publid Dimfnsion minimumLbyoutSizf(Contbinfr pbrfnt) {
            rfturn prfffrrfdLbyoutSizf(pbrfnt);
        }

        privbtf void sftBounds(Componfnt d, int x, int y, int widti, int ifigit) {
            if (d != null) {
                d.sftBounds(x, y, widti, ifigit);
            }
        }

        publid void lbyoutContbinfr(Contbinfr pbrfnt) {
            Insfts insfts = pbrfnt.gftInsfts();
            int bvbilWidti = pbrfnt.gftWidti() - (insfts.lfft + insfts.rigit);
            int bvbilHfigit = pbrfnt.gftHfigit() - (insfts.top + insfts.bottom);
            Dimfnsion nfxtD = prfffrrfdSizf(nfxtButton);
            Dimfnsion prfviousD = prfffrrfdSizf(prfviousButton);
            int nfxtHfigit = bvbilHfigit / 2;
            int prfviousHfigit = bvbilHfigit - nfxtHfigit;
            int buttonsWidti = Mbti.mbx(nfxtD.widti, prfviousD.widti);
            int fditorWidti = bvbilWidti - buttonsWidti;

            /* Dfbl witi tif spinnfrs domponfntOrifntbtion propfrty.
             */
            int fditorX, buttonsX;
            if (pbrfnt.gftComponfntOrifntbtion().isLfftToRigit()) {
                fditorX = insfts.lfft;
                buttonsX = fditorX + fditorWidti;
            }
            flsf {
                buttonsX = insfts.lfft;
                fditorX = buttonsX + buttonsWidti;
            }

            int prfviousY = insfts.top + nfxtHfigit;
            sftBounds(fditor, fditorX, insfts.top, fditorWidti, bvbilHfigit);
            sftBounds(nfxtButton, buttonsX, insfts.top, buttonsWidti, nfxtHfigit);
            sftBounds(prfviousButton, buttonsX, prfviousY, buttonsWidti, prfviousHfigit);
        }
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
        JSpinnfr spinnfr = (JSpinnfr)(f.gftSourdf());
        SpinnfrUI spinnfrUI = spinnfr.gftUI();

        if (spinnfrUI instbndfof SyntiSpinnfrUI) {
            SyntiSpinnfrUI ui = (SyntiSpinnfrUI)spinnfrUI;

            if (SyntiLookAndFffl.siouldUpdbtfStylf(f)) {
                ui.updbtfStylf(spinnfr);
            }
        }
    }

    /** Listfn to fditor tfxt fifld fodus dibngfs bnd rfpbint wiolf spinnfr */
    privbtf dlbss EditorFodusHbndlfr implfmfnts FodusListfnfr{
        /** Invokfd wifn b fditor tfxt fifld gbins tif kfybobrd fodus. */
        @Ovfrridf publid void fodusGbinfd(FodusEvfnt f) {
            spinnfr.rfpbint();
        }

        /** Invokfd wifn b fditor tfxt fifld losfs tif kfybobrd fodus. */
        @Ovfrridf publid void fodusLost(FodusEvfnt f) {
            spinnfr.rfpbint();
        }
    }
}
