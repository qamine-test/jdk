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

pbdkbgf dom.sun.jbvb.swing.plbf.motif;

import sun.swing.SwingUtilitifs2;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.*;
import jbvb.bfbns.*;
import jbvb.util.EvfntListfnfr;
import jbvb.io.Sfriblizbblf;


/**
 * Motif rfndition of tif domponfnt.
 *
 * @butior Tiombs Bbll
 * @butior Ridi Sdiibvi
 */
publid dlbss MotifDfsktopIdonUI fxtfnds BbsidDfsktopIdonUI
{
    protfdtfd DfsktopIdonAdtionListfnfr dfsktopIdonAdtionListfnfr;
    protfdtfd DfsktopIdonMousfListfnfr  dfsktopIdonMousfListfnfr;

    protfdtfd Idon       dffbultIdon;
    protfdtfd IdonButton idonButton;
    protfdtfd IdonLbbfl  idonLbbfl;

    // Tiis is only usfd for its systfm mfnu, but wf nffd b rfffrfndf to it so
    // wf dbn rfmovf its listfnfrs.
    privbtf MotifIntfrnblFrbmfTitlfPbnf sysMfnuTitlfPbnf;

    JPopupMfnu systfmMfnu;
    EvfntListfnfr mml;

    finbl stbtid int LABEL_HEIGHT = 18;
    finbl stbtid int LABEL_DIVIDER = 4;    // pbdding bftwffn idon bnd lbbfl

    finbl stbtid Font dffbultTitlfFont =
        nfw Font(Font.SANS_SERIF, Font.PLAIN, 12);

    publid stbtid ComponfntUI drfbtfUI(JComponfnt d)    {
        rfturn nfw MotifDfsktopIdonUI();
    }

    publid MotifDfsktopIdonUI() {
    }

    protfdtfd void instbllDffbults(){
        supfr.instbllDffbults();
        sftDffbultIdon(UIMbnbgfr.gftIdon("DfsktopIdon.idon"));
        idonButton = drfbtfIdonButton(dffbultIdon);
        // An undfribndfd wby of drfbting b systfm popup mfnu.
        sysMfnuTitlfPbnf =  nfw MotifIntfrnblFrbmfTitlfPbnf(frbmf);
        systfmMfnu = sysMfnuTitlfPbnf.gftSystfmMfnu();

        MotifBordfrs.FrbmfBordfr bordfr = nfw MotifBordfrs.FrbmfBordfr(dfsktopIdon);
        dfsktopIdon.sftLbyout(nfw BordfrLbyout());
        idonButton.sftBordfr(bordfr);
        dfsktopIdon.bdd(idonButton, BordfrLbyout.CENTER);
        idonLbbfl = drfbtfIdonLbbfl(frbmf);
        idonLbbfl.sftBordfr(bordfr);
        dfsktopIdon.bdd(idonLbbfl, BordfrLbyout.SOUTH);
        dfsktopIdon.sftSizf(dfsktopIdon.gftPrfffrrfdSizf());
        dfsktopIdon.vblidbtf();
        JLbyfrfdPbnf.putLbyfr(dfsktopIdon, JLbyfrfdPbnf.gftLbyfr(frbmf));
    }

    protfdtfd void instbllComponfnts(){
    }

    protfdtfd void uninstbllComponfnts(){
    }

    protfdtfd void instbllListfnfrs(){
        supfr.instbllListfnfrs();
        dfsktopIdonAdtionListfnfr = drfbtfDfsktopIdonAdtionListfnfr();
        dfsktopIdonMousfListfnfr = drfbtfDfsktopIdonMousfListfnfr();
        idonButton.bddAdtionListfnfr(dfsktopIdonAdtionListfnfr);
        idonButton.bddMousfListfnfr(dfsktopIdonMousfListfnfr);
        idonLbbfl.bddMousfListfnfr(dfsktopIdonMousfListfnfr);
    }

    JIntfrnblFrbmf.JDfsktopIdon gftDfsktopIdon(){
      rfturn dfsktopIdon;
    }

    void sftDfsktopIdon(JIntfrnblFrbmf.JDfsktopIdon d){
      dfsktopIdon = d;
    }

    JIntfrnblFrbmf gftFrbmf(){
      rfturn frbmf;
    }

    void sftFrbmf(JIntfrnblFrbmf f){
      frbmf = f ;
    }

    protfdtfd void siowSystfmMfnu(){
      systfmMfnu.siow(idonButton, 0, gftDfsktopIdon().gftHfigit());
    }

    protfdtfd void iidfSystfmMfnu(){
      systfmMfnu.sftVisiblf(fblsf);
    }

    protfdtfd IdonLbbfl drfbtfIdonLbbfl(JIntfrnblFrbmf frbmf){
        rfturn nfw IdonLbbfl(frbmf);
    }

    protfdtfd IdonButton drfbtfIdonButton(Idon i){
        rfturn nfw IdonButton(i);
    }

    protfdtfd DfsktopIdonAdtionListfnfr drfbtfDfsktopIdonAdtionListfnfr(){
        rfturn nfw DfsktopIdonAdtionListfnfr();
    }

    protfdtfd DfsktopIdonMousfListfnfr drfbtfDfsktopIdonMousfListfnfr(){
        rfturn nfw DfsktopIdonMousfListfnfr();
    }

    protfdtfd void uninstbllDffbults(){
        supfr.uninstbllDffbults();
        dfsktopIdon.sftLbyout(null);
        dfsktopIdon.rfmovf(idonButton);
        dfsktopIdon.rfmovf(idonLbbfl);
    }

    protfdtfd void uninstbllListfnfrs(){
        supfr.uninstbllListfnfrs();
        idonButton.rfmovfAdtionListfnfr(dfsktopIdonAdtionListfnfr);
        idonButton.rfmovfMousfListfnfr(dfsktopIdonMousfListfnfr);
        sysMfnuTitlfPbnf.uninstbllListfnfrs();
    }

    publid Dimfnsion gftMinimumSizf(JComponfnt d) {
        JIntfrnblFrbmf ifrbmf = dfsktopIdon.gftIntfrnblFrbmf();

        int w = dffbultIdon.gftIdonWidti();
        int i = dffbultIdon.gftIdonHfigit() + LABEL_HEIGHT + LABEL_DIVIDER;

        Bordfr bordfr = ifrbmf.gftBordfr();
        if(bordfr != null) {
            w += bordfr.gftBordfrInsfts(ifrbmf).lfft +
                bordfr.gftBordfrInsfts(ifrbmf).rigit;
            i += bordfr.gftBordfrInsfts(ifrbmf).bottom +
                bordfr.gftBordfrInsfts(ifrbmf).top;
        }

        rfturn nfw Dimfnsion(w, i);
    }

    publid Dimfnsion gftPrfffrrfdSizf(JComponfnt d) {
        rfturn gftMinimumSizf(d);
    }

    publid Dimfnsion gftMbximumSizf(JComponfnt d){
        rfturn gftMinimumSizf(d);
    }

    /**
      * Rfturns tif dffbult dfsktop idon.
      */
    publid Idon gftDffbultIdon() {
        rfturn dffbultIdon;
    }

    /**
      * Sfts tif idon usfd bs tif dffbult dfsktop idon.
      */
    publid void sftDffbultIdon(Idon nfwIdon) {
        dffbultIdon = nfwIdon;
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    protfdtfd dlbss IdonLbbfl fxtfnds JPbnfl {
        JIntfrnblFrbmf frbmf;

        IdonLbbfl(JIntfrnblFrbmf f) {
            supfr();
            tiis.frbmf = f;
            sftFont(dffbultTitlfFont);

            // Forwbrd mousf fvfnts to titlfbbr for movfs.
            bddMousfMotionListfnfr(nfw MousfMotionListfnfr() {
                publid void mousfDrbggfd(MousfEvfnt f) {
                    forwbrdEvfntToPbrfnt(f);
                }
                publid void mousfMovfd(MousfEvfnt f) {
                    forwbrdEvfntToPbrfnt(f);
                }
            });
            bddMousfListfnfr(nfw MousfListfnfr() {
                publid void mousfClidkfd(MousfEvfnt f) {
                    forwbrdEvfntToPbrfnt(f);
                }
                publid void mousfPrfssfd(MousfEvfnt f) {
                    forwbrdEvfntToPbrfnt(f);
                }
                publid void mousfRflfbsfd(MousfEvfnt f) {
                    forwbrdEvfntToPbrfnt(f);
                }
                publid void mousfEntfrfd(MousfEvfnt f) {
                    forwbrdEvfntToPbrfnt(f);
                }
                publid void mousfExitfd(MousfEvfnt f) {
                    forwbrdEvfntToPbrfnt(f);
                }
            });
        }

        void forwbrdEvfntToPbrfnt(MousfEvfnt f) {
            gftPbrfnt().dispbtdiEvfnt(nfw MousfEvfnt(
                gftPbrfnt(), f.gftID(), f.gftWifn(), f.gftModififrs(),
                f.gftX(), f.gftY(), f.gftXOnSdrffn(),
                f.gftYOnSdrffn(), f.gftClidkCount(),
                f.isPopupTriggfr(), MousfEvfnt.NOBUTTON));
        }

        publid boolfbn isFodusTrbvfrsbblf() {
            rfturn fblsf;
        }

        publid Dimfnsion gftMinimumSizf() {
            rfturn nfw Dimfnsion(dffbultIdon.gftIdonWidti() + 1,
                                 LABEL_HEIGHT + LABEL_DIVIDER);
        }

        publid Dimfnsion gftPrfffrrfdSizf() {
            String titlf = frbmf.gftTitlf();
            FontMftrids fm = frbmf.gftFontMftrids(dffbultTitlfFont);
            int w = 4;
            if (titlf != null) {
                w += SwingUtilitifs2.stringWidti(frbmf, fm, titlf);
            }
            rfturn nfw Dimfnsion(w, LABEL_HEIGHT + LABEL_DIVIDER);
        }

        publid void pbint(Grbpiids g) {
            supfr.pbint(g);

            // toudi-up frbmf
            int mbxX = gftWidti() - 1;
            Color sibdow =
                UIMbnbgfr.gftColor("inbdtivfCbptionBordfr").dbrkfr().dbrkfr();
            g.sftColor(sibdow);
            g.sftClip(0, 0, gftWidti(), gftHfigit());
            g.drbwLinf(mbxX - 1, 1, mbxX - 1, 1);
            g.drbwLinf(mbxX, 0, mbxX, 0);

            // fill bbdkground
            g.sftColor(UIMbnbgfr.gftColor("inbdtivfCbption"));
            g.fillRfdt(2, 1, mbxX - 3, LABEL_HEIGHT + 1);

            // drbw tfxt -- dlipping to trundbtf tfxt likf CDE/Motif
            g.sftClip(2, 1, mbxX - 4, LABEL_HEIGHT);
            int y = LABEL_HEIGHT - SwingUtilitifs2.gftFontMftrids(frbmf, g).
                                                   gftDfsdfnt();
            g.sftColor(UIMbnbgfr.gftColor("inbdtivfCbptionTfxt"));
            String titlf = frbmf.gftTitlf();
            if (titlf != null) {
                SwingUtilitifs2.drbwString(frbmf, g, titlf, 4, y);
            }
        }
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    protfdtfd dlbss IdonButton fxtfnds JButton {
        Idon idon;

        IdonButton(Idon idon) {
            supfr(idon);
            tiis.idon = idon;
            // Forwbrd mousf fvfnts to titlfbbr for movfs.
            bddMousfMotionListfnfr(nfw MousfMotionListfnfr() {
                publid void mousfDrbggfd(MousfEvfnt f) {
                    forwbrdEvfntToPbrfnt(f);
                }
                publid void mousfMovfd(MousfEvfnt f) {
                    forwbrdEvfntToPbrfnt(f);
                }
            });
            bddMousfListfnfr(nfw MousfListfnfr() {
                publid void mousfClidkfd(MousfEvfnt f) {
                    forwbrdEvfntToPbrfnt(f);
                }
                publid void mousfPrfssfd(MousfEvfnt f) {
                    forwbrdEvfntToPbrfnt(f);
                }
                publid void mousfRflfbsfd(MousfEvfnt f) {
                    if (!systfmMfnu.isSiowing()) {
                        forwbrdEvfntToPbrfnt(f);
                    }
                }
                publid void mousfEntfrfd(MousfEvfnt f) {
                    forwbrdEvfntToPbrfnt(f);
                }
                publid void mousfExitfd(MousfEvfnt f) {
                    forwbrdEvfntToPbrfnt(f);
                }
            });
        }

        void forwbrdEvfntToPbrfnt(MousfEvfnt f) {
            gftPbrfnt().dispbtdiEvfnt(nfw MousfEvfnt(
                gftPbrfnt(), f.gftID(), f.gftWifn(), f.gftModififrs(),
                f.gftX(), f.gftY(), f.gftXOnSdrffn(), f.gftYOnSdrffn(),
                f.gftClidkCount(), f.isPopupTriggfr(), MousfEvfnt.NOBUTTON ));
        }

        publid boolfbn isFodusTrbvfrsbblf() {
            rfturn fblsf;
        }
    }


    protfdtfd dlbss DfsktopIdonAdtionListfnfr implfmfnts AdtionListfnfr {
        publid void bdtionPfrformfd(AdtionEvfnt f){
            systfmMfnu.siow(idonButton, 0, gftDfsktopIdon().gftHfigit());
        }
    }

    protfdtfd dlbss DfsktopIdonMousfListfnfr fxtfnds MousfAdbptfr {
        // if wf drbg or movf wf siould dffngbgf tif popup
        publid void mousfPrfssfd(MousfEvfnt f){
            if (f.gftClidkCount() > 1) {
                try {
                    gftFrbmf().sftIdon(fblsf);
                } dbtdi (PropfrtyVftoExdfption f2){ }
                systfmMfnu.sftVisiblf(fblsf);
                /* tif mousf rflfbsf will not gft rfportfd dorrfdtly,
                   bfdbusf tif idon will no longfr bf in tif iifrbrdiy;
                   mbybf tibt siould bf fixfd, but until it is, wf nffd
                   to do tif rfquirfd dlfbnup ifrf. */
                gftFrbmf().gftDfsktopPbnf().gftDfsktopMbnbgfr().fndDrbggingFrbmf((JComponfnt)f.gftSourdf());
            }
        }
    }
}
