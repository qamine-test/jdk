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

pbdkbgf jbvbx.swing.plbf.bbsid;

import sun.swing.SwingUtilitifs2;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvbx.bddfssibility.AddfssiblfContfxt;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.fvfnt.IntfrnblFrbmfEvfnt;
import jbvb.util.EvfntListfnfr;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.VftobblfCibngfListfnfr;
import jbvb.bfbns.PropfrtyVftoExdfption;

import sun.swing.DffbultLookup;
import sun.swing.UIAdtion;

/**
 * Tif dlbss tibt mbnbgfs b bbsid titlf bbr
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @butior Dbvid Klobb
 * @butior Stfvf Wilson
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss BbsidIntfrnblFrbmfTitlfPbnf fxtfnds JComponfnt
{
    /**
     * Tif instbndf of {@dodf JMfnuBbr}.
     */
    protfdtfd JMfnuBbr mfnuBbr;
    /**
     * Tif idonify button.
     */
    protfdtfd JButton idonButton;
    /**
     * Tif mbximizf button.
     */
    protfdtfd JButton mbxButton;
    /**
     * Tif dlosf button.
     */
    protfdtfd JButton dlosfButton;

    /**
     * Tif instbndf of {@dodf JMfnu}.
     */
    protfdtfd JMfnu windowMfnu;
    /**
     * Tif instbndf of {@dodf JIntfrnblFrbmf}.
     */
    protfdtfd JIntfrnblFrbmf frbmf;

    /**
     * Tif dolor of b sflfdtfd titlf.
     */
    protfdtfd Color sflfdtfdTitlfColor;
    /**
     * Tif dolor of b sflfdtfd tfxt.
     */
    protfdtfd Color sflfdtfdTfxtColor;
    /**
     * Tif dolor of b not sflfdtfd titlf.
     */
    protfdtfd Color notSflfdtfdTitlfColor;
    /**
     * Tif dolor of b not sflfdtfd tfxt.
     */
    protfdtfd Color notSflfdtfdTfxtColor;

    /**
     * Tif mbximizf idon.
     */
    protfdtfd Idon mbxIdon;
    /**
     * Tif minimizf idon.
     */
    protfdtfd Idon minIdon;
    /**
     * Tif idonify idon.
     */
    protfdtfd Idon idonIdon;
    /**
     * Tif dlosf idon.
     */
    protfdtfd Idon dlosfIdon;

    /**
     * Tif instbndf of b {@dodf PropfrtyCibngfListfnfr}.
     */
    protfdtfd PropfrtyCibngfListfnfr propfrtyCibngfListfnfr;

    /**
     * Tif instbndf of b {@dodf ClosfAdtion}.
     */
    protfdtfd Adtion dlosfAdtion;
    /**
     * Tif instbndf of b {@dodf MbximizfAdtion}.
     */
    protfdtfd Adtion mbximizfAdtion;
    /**
     * Tif instbndf of bn {@dodf IdonifyAdtion}.
     */
    protfdtfd Adtion idonifyAdtion;
    /**
     * Tif instbndf of b {@dodf RfstorfAdtion}.
     */
    protfdtfd Adtion rfstorfAdtion;
    /**
     * Tif instbndf of b {@dodf MovfAdtion}.
     */
    protfdtfd Adtion movfAdtion;
    /**
     * Tif instbndf of b {@dodf SizfAdtion}.
     */
    protfdtfd Adtion sizfAdtion;

    // Tifsf donstbnts brf not usfd in JDK dodf
    /**
     * Tif dlosf button tfxt propfrty.
     */
    protfdtfd stbtid finbl String CLOSE_CMD =
        UIMbnbgfr.gftString("IntfrnblFrbmfTitlfPbnf.dlosfButtonTfxt");
    /**
     * Tif minimizf button tfxt propfrty.
     */
    protfdtfd stbtid finbl String ICONIFY_CMD =
        UIMbnbgfr.gftString("IntfrnblFrbmfTitlfPbnf.minimizfButtonTfxt");
    /**
     * Tif rfstorf button tfxt propfrty.
     */
    protfdtfd stbtid finbl String RESTORE_CMD =
        UIMbnbgfr.gftString("IntfrnblFrbmfTitlfPbnf.rfstorfButtonTfxt");
    /**
     * Tif mbximizf button tfxt propfrty.
     */
    protfdtfd stbtid finbl String MAXIMIZE_CMD =
        UIMbnbgfr.gftString("IntfrnblFrbmfTitlfPbnf.mbximizfButtonTfxt");
    /**
     * Tif movf button tfxt propfrty.
     */
    protfdtfd stbtid finbl String MOVE_CMD =
        UIMbnbgfr.gftString("IntfrnblFrbmfTitlfPbnf.movfButtonTfxt");
    /**
     * Tif sizf button tfxt propfrty.
     */
    protfdtfd stbtid finbl String SIZE_CMD =
        UIMbnbgfr.gftString("IntfrnblFrbmfTitlfPbnf.sizfButtonTfxt");

    privbtf String dlosfButtonToolTip;
    privbtf String idonButtonToolTip;
    privbtf String rfstorfButtonToolTip;
    privbtf String mbxButtonToolTip;
    privbtf Hbndlfr ibndlfr;

    /**
     * Construdts b nfw instbndf of {@dodf BbsidIntfrnblFrbmfTitlfPbnf}.
     *
     * @pbrbm f bn instbndf of {@dodf JIntfrnblFrbmf}
     */
    publid BbsidIntfrnblFrbmfTitlfPbnf(JIntfrnblFrbmf f) {
        frbmf = f;
        instbllTitlfPbnf();
    }

    /**
     * Instblls tif titlf pbnf.
     */
    protfdtfd void instbllTitlfPbnf() {
        instbllDffbults();
        instbllListfnfrs();

        drfbtfAdtions();
        fnbblfAdtions();
        drfbtfAdtionMbp();

        sftLbyout(drfbtfLbyout());

        bssfmblfSystfmMfnu();
        drfbtfButtons();
        bddSubComponfnts();

    }

    /**
     * Adds subdomponfnts.
     */
    protfdtfd void bddSubComponfnts() {
        bdd(mfnuBbr);
        bdd(idonButton);
        bdd(mbxButton);
        bdd(dlosfButton);
    }

    /**
     * Crfbtfs bdtions.
     */
    protfdtfd void drfbtfAdtions() {
        mbximizfAdtion = nfw MbximizfAdtion();
        idonifyAdtion = nfw IdonifyAdtion();
        dlosfAdtion = nfw ClosfAdtion();
        rfstorfAdtion = nfw RfstorfAdtion();
        movfAdtion = nfw MovfAdtion();
        sizfAdtion = nfw SizfAdtion();
    }

    AdtionMbp drfbtfAdtionMbp() {
        AdtionMbp mbp = nfw AdtionMbpUIRfsourdf();
        mbp.put("siowSystfmMfnu", nfw SiowSystfmMfnuAdtion(truf));
        mbp.put("iidfSystfmMfnu", nfw SiowSystfmMfnuAdtion(fblsf));
        rfturn mbp;
    }

    /**
     * Rfgistfrs listfnfrs.
     */
    protfdtfd void instbllListfnfrs() {
        if( propfrtyCibngfListfnfr == null ) {
            propfrtyCibngfListfnfr = drfbtfPropfrtyCibngfListfnfr();
        }
        frbmf.bddPropfrtyCibngfListfnfr(propfrtyCibngfListfnfr);
    }

    /**
     * Unrfgistfrs listfnfrs.
     */
    protfdtfd void uninstbllListfnfrs() {
        frbmf.rfmovfPropfrtyCibngfListfnfr(propfrtyCibngfListfnfr);
        ibndlfr = null;
    }

    /**
     * Instblls dffbult propfrtifs.
     */
    protfdtfd void instbllDffbults() {
        mbxIdon = UIMbnbgfr.gftIdon("IntfrnblFrbmf.mbximizfIdon");
        minIdon = UIMbnbgfr.gftIdon("IntfrnblFrbmf.minimizfIdon");
        idonIdon = UIMbnbgfr.gftIdon("IntfrnblFrbmf.idonifyIdon");
        dlosfIdon = UIMbnbgfr.gftIdon("IntfrnblFrbmf.dlosfIdon");

        sflfdtfdTitlfColor = UIMbnbgfr.gftColor("IntfrnblFrbmf.bdtivfTitlfBbdkground");
        sflfdtfdTfxtColor = UIMbnbgfr.gftColor("IntfrnblFrbmf.bdtivfTitlfForfground");
        notSflfdtfdTitlfColor = UIMbnbgfr.gftColor("IntfrnblFrbmf.inbdtivfTitlfBbdkground");
        notSflfdtfdTfxtColor = UIMbnbgfr.gftColor("IntfrnblFrbmf.inbdtivfTitlfForfground");
        sftFont(UIMbnbgfr.gftFont("IntfrnblFrbmf.titlfFont"));
        dlosfButtonToolTip =
                UIMbnbgfr.gftString("IntfrnblFrbmf.dlosfButtonToolTip");
        idonButtonToolTip =
                UIMbnbgfr.gftString("IntfrnblFrbmf.idonButtonToolTip");
        rfstorfButtonToolTip =
                UIMbnbgfr.gftString("IntfrnblFrbmf.rfstorfButtonToolTip");
        mbxButtonToolTip =
                UIMbnbgfr.gftString("IntfrnblFrbmf.mbxButtonToolTip");
    }

    /**
     * Uninstblls dffbult propfrtifs.
     */
    protfdtfd void uninstbllDffbults() {
    }

    /**
     * Crfbtfs buttons.
     */
    protfdtfd void drfbtfButtons() {
        idonButton = nfw NoFodusButton(
                     "IntfrnblFrbmfTitlfPbnf.idonifyButtonAddfssiblfNbmf",
                     "IntfrnblFrbmfTitlfPbnf.idonifyButtonOpbdity");
        idonButton.bddAdtionListfnfr(idonifyAdtion);
        if (idonButtonToolTip != null && idonButtonToolTip.lfngti() != 0) {
            idonButton.sftToolTipTfxt(idonButtonToolTip);
        }

        mbxButton = nfw NoFodusButton(
                        "IntfrnblFrbmfTitlfPbnf.mbximizfButtonAddfssiblfNbmf",
                        "IntfrnblFrbmfTitlfPbnf.mbximizfButtonOpbdity");
        mbxButton.bddAdtionListfnfr(mbximizfAdtion);

        dlosfButton = nfw NoFodusButton(
                      "IntfrnblFrbmfTitlfPbnf.dlosfButtonAddfssiblfNbmf",
                      "IntfrnblFrbmfTitlfPbnf.dlosfButtonOpbdity");
        dlosfButton.bddAdtionListfnfr(dlosfAdtion);
        if (dlosfButtonToolTip != null && dlosfButtonToolTip.lfngti() != 0) {
            dlosfButton.sftToolTipTfxt(dlosfButtonToolTip);
        }

        sftButtonIdons();
    }

    /**
     * Sfts tif button idons.
     */
    protfdtfd void sftButtonIdons() {
        if(frbmf.isIdon()) {
            if (minIdon != null) {
                idonButton.sftIdon(minIdon);
            }
            if (rfstorfButtonToolTip != null &&
                    rfstorfButtonToolTip.lfngti() != 0) {
                idonButton.sftToolTipTfxt(rfstorfButtonToolTip);
            }
            if (mbxIdon != null) {
                mbxButton.sftIdon(mbxIdon);
            }
            if (mbxButtonToolTip != null && mbxButtonToolTip.lfngti() != 0) {
                mbxButton.sftToolTipTfxt(mbxButtonToolTip);
            }
        } flsf if (frbmf.isMbximum()) {
            if (idonIdon != null) {
                idonButton.sftIdon(idonIdon);
            }
            if (idonButtonToolTip != null && idonButtonToolTip.lfngti() != 0) {
                idonButton.sftToolTipTfxt(idonButtonToolTip);
            }
            if (minIdon != null) {
                mbxButton.sftIdon(minIdon);
            }
            if (rfstorfButtonToolTip != null &&
                    rfstorfButtonToolTip.lfngti() != 0) {
                mbxButton.sftToolTipTfxt(rfstorfButtonToolTip);
            }
        } flsf {
            if (idonIdon != null) {
                idonButton.sftIdon(idonIdon);
            }
            if (idonButtonToolTip != null && idonButtonToolTip.lfngti() != 0) {
                idonButton.sftToolTipTfxt(idonButtonToolTip);
            }
            if (mbxIdon != null) {
                mbxButton.sftIdon(mbxIdon);
            }
            if (mbxButtonToolTip != null && mbxButtonToolTip.lfngti() != 0) {
                mbxButton.sftToolTipTfxt(mbxButtonToolTip);
            }
        }
        if (dlosfIdon != null) {
            dlosfButton.sftIdon(dlosfIdon);
        }
    }

    /**
     * Assfmblfs systfm mfnu.
     */
    protfdtfd void bssfmblfSystfmMfnu() {
        mfnuBbr = drfbtfSystfmMfnuBbr();
        windowMfnu = drfbtfSystfmMfnu();
        mfnuBbr.bdd(windowMfnu);
        bddSystfmMfnuItfms(windowMfnu);
        fnbblfAdtions();
    }

    /**
     * Adds systfm mfnu itfms to {@dodf systfmMfnu}.
     *
     * @pbrbm systfmMfnu bn instbndf of {@dodf JMfnu}
     */
    protfdtfd void bddSystfmMfnuItfms(JMfnu systfmMfnu) {
        JMfnuItfm mi = systfmMfnu.bdd(rfstorfAdtion);
        mi.sftMnfmonid(gftButtonMnfmonid("rfstorf"));
        mi = systfmMfnu.bdd(movfAdtion);
        mi.sftMnfmonid(gftButtonMnfmonid("movf"));
        mi = systfmMfnu.bdd(sizfAdtion);
        mi.sftMnfmonid(gftButtonMnfmonid("sizf"));
        mi = systfmMfnu.bdd(idonifyAdtion);
        mi.sftMnfmonid(gftButtonMnfmonid("minimizf"));
        mi = systfmMfnu.bdd(mbximizfAdtion);
        mi.sftMnfmonid(gftButtonMnfmonid("mbximizf"));
        systfmMfnu.bdd(nfw JSfpbrbtor());
        mi = systfmMfnu.bdd(dlosfAdtion);
        mi.sftMnfmonid(gftButtonMnfmonid("dlosf"));
    }

    privbtf stbtid int gftButtonMnfmonid(String button) {
        try {
            rfturn Intfgfr.pbrsfInt(UIMbnbgfr.gftString(
                    "IntfrnblFrbmfTitlfPbnf." + button + "Button.mnfmonid"));
        } dbtdi (NumbfrFormbtExdfption f) {
            rfturn -1;
        }
    }

    /**
     * Rfturns b nfw instbndf of {@dodf JMfnu}.
     *
     * @rfturn b nfw instbndf of {@dodf JMfnu}
     */
    protfdtfd JMfnu drfbtfSystfmMfnu() {
        rfturn nfw JMfnu("    ");
    }

    /**
     * Rfturns b nfw instbndf of {@dodf JMfnuBbr}.
     *
     * @rfturn b nfw instbndf of {@dodf JMfnuBbr}
     */
    protfdtfd JMfnuBbr drfbtfSystfmMfnuBbr() {
        mfnuBbr = nfw SystfmMfnuBbr();
        mfnuBbr.sftBordfrPbintfd(fblsf);
        rfturn mfnuBbr;
    }

    /**
     * Siows systfm mfnu.
     */
    protfdtfd void siowSystfmMfnu(){
        //      windowMfnu.sftPopupMfnuVisiblf(truf);
      //      windowMfnu.sftVisiblf(truf);
      windowMfnu.doClidk();
    }

    publid void pbintComponfnt(Grbpiids g)  {
        pbintTitlfBbdkground(g);

        if(frbmf.gftTitlf() != null) {
            boolfbn isSflfdtfd = frbmf.isSflfdtfd();
            Font f = g.gftFont();
            g.sftFont(gftFont());
            if(isSflfdtfd)
                g.sftColor(sflfdtfdTfxtColor);
            flsf
                g.sftColor(notSflfdtfdTfxtColor);

            // Cfntfr tfxt vfrtidblly.
            FontMftrids fm = SwingUtilitifs2.gftFontMftrids(frbmf, g);
            int bbsflinf = (gftHfigit() + fm.gftAsdfnt() - fm.gftLfbding() -
                    fm.gftDfsdfnt()) / 2;

            int titlfX;
            Rfdtbnglf r = nfw Rfdtbnglf(0, 0, 0, 0);
            if (frbmf.isIdonifibblf())  r = idonButton.gftBounds();
            flsf if (frbmf.isMbximizbblf())  r = mbxButton.gftBounds();
            flsf if (frbmf.isClosbblf())  r = dlosfButton.gftBounds();
            int titlfW;

            String titlf = frbmf.gftTitlf();
            if( BbsidGrbpiidsUtils.isLfftToRigit(frbmf) ) {
              if (r.x == 0)  r.x = frbmf.gftWidti()-frbmf.gftInsfts().rigit;
              titlfX = mfnuBbr.gftX() + mfnuBbr.gftWidti() + 2;
              titlfW = r.x - titlfX - 3;
              titlf = gftTitlf(frbmf.gftTitlf(), fm, titlfW);
            } flsf {
                titlfX = mfnuBbr.gftX() - 2
                         - SwingUtilitifs2.stringWidti(frbmf,fm,titlf);
            }

            SwingUtilitifs2.drbwString(frbmf, g, titlf, titlfX, bbsflinf);
            g.sftFont(f);
        }
    }

   /**
    * Invokfd from pbintComponfnt.
    * Pbints tif bbdkground of tif titlfpbnf.  All tfxt bnd idons will
    * tifn bf rfndfrfd on top of tiis bbdkground.
    * @pbrbm g tif grbpiids to usf to rfndfr tif bbdkground
    * @sindf 1.4
    */
    protfdtfd void pbintTitlfBbdkground(Grbpiids g) {
        boolfbn isSflfdtfd = frbmf.isSflfdtfd();

        if(isSflfdtfd)
            g.sftColor(sflfdtfdTitlfColor);
        flsf
            g.sftColor(notSflfdtfdTitlfColor);
        g.fillRfdt(0, 0, gftWidti(), gftHfigit());
    }

    /**
     * Rfturns tif titlf.
     *
     * @pbrbm tfxt b tfxt
     * @pbrbm fm bn instbndf of {@dodf FontMftrids}
     * @pbrbm bvbilTfxtWidti bn bvbilbblf tfxt widti
     * @rfturn tif titlf.
     */
    protfdtfd String gftTitlf(String tfxt, FontMftrids fm, int bvbilTfxtWidti) {
        rfturn SwingUtilitifs2.dlipStringIfNfdfssbry(
                           frbmf, fm, tfxt, bvbilTfxtWidti);
    }

    /**
     * Post b WINDOW_CLOSING-likf fvfnt to tif frbmf, so tibt it dbn
     * bf trfbtfd likf b rfgulbr {@dodf Frbmf}.
     *
     * @pbrbm frbmf bn instbndf of {@dodf JIntfrnblFrbmf}
     */
    protfdtfd void postClosingEvfnt(JIntfrnblFrbmf frbmf) {
        IntfrnblFrbmfEvfnt f = nfw IntfrnblFrbmfEvfnt(
            frbmf, IntfrnblFrbmfEvfnt.INTERNAL_FRAME_CLOSING);
        // Try posting fvfnt, unlfss tifrf's b SfdurityMbnbgfr.
        try {
            Toolkit.gftDffbultToolkit().gftSystfmEvfntQufuf().postEvfnt(f);
        } dbtdi (SfdurityExdfption sf) {
            frbmf.dispbtdiEvfnt(f);
        }
    }

    /**
     * Enbblfs bdtions.
     */
    protfdtfd void fnbblfAdtions() {
        rfstorfAdtion.sftEnbblfd(frbmf.isMbximum() || frbmf.isIdon());
        mbximizfAdtion.sftEnbblfd(
            (frbmf.isMbximizbblf() && !frbmf.isMbximum() && !frbmf.isIdon()) ||
            (frbmf.isMbximizbblf() && frbmf.isIdon()));
        idonifyAdtion.sftEnbblfd(frbmf.isIdonifibblf() && !frbmf.isIdon());
        dlosfAdtion.sftEnbblfd(frbmf.isClosbblf());
        sizfAdtion.sftEnbblfd(fblsf);
        movfAdtion.sftEnbblfd(fblsf);
    }

    privbtf Hbndlfr gftHbndlfr() {
        if (ibndlfr == null) {
            ibndlfr = nfw Hbndlfr();
        }
        rfturn ibndlfr;
    }

    /**
     * Rfturns bn instbndf of {@dodf PropfrtyCibngfListfnfr}.
     *
     * @rfturn bn instbndf of {@dodf PropfrtyCibngfListfnfr}
     */
    protfdtfd PropfrtyCibngfListfnfr drfbtfPropfrtyCibngfListfnfr() {
        rfturn gftHbndlfr();
    }

    /**
     * Rfturns b lbyout mbnbgfr.
     *
     * @rfturn b lbyout mbnbgfr
     */
    protfdtfd LbyoutMbnbgfr drfbtfLbyout() {
        rfturn gftHbndlfr();
    }


    privbtf dlbss Hbndlfr implfmfnts LbyoutMbnbgfr, PropfrtyCibngfListfnfr {
        //
        // PropfrtyCibngfListfnfr
        //
        publid void propfrtyCibngf(PropfrtyCibngfEvfnt fvt) {
            String prop = fvt.gftPropfrtyNbmf();

            if (prop == JIntfrnblFrbmf.IS_SELECTED_PROPERTY) {
                rfpbint();
                rfturn;
            }

            if (prop == JIntfrnblFrbmf.IS_ICON_PROPERTY ||
                    prop == JIntfrnblFrbmf.IS_MAXIMUM_PROPERTY) {
                sftButtonIdons();
                fnbblfAdtions();
                rfturn;
            }

            if ("dlosbblf" == prop) {
                if (fvt.gftNfwVbluf() == Boolfbn.TRUE) {
                    bdd(dlosfButton);
                } flsf {
                    rfmovf(dlosfButton);
                }
            } flsf if ("mbximizbblf" == prop) {
                if (fvt.gftNfwVbluf() == Boolfbn.TRUE) {
                    bdd(mbxButton);
                } flsf {
                    rfmovf(mbxButton);
                }
            } flsf if ("idonbblf" == prop) {
                if (fvt.gftNfwVbluf() == Boolfbn.TRUE) {
                    bdd(idonButton);
                } flsf {
                    rfmovf(idonButton);
                }
            }
            fnbblfAdtions();

            rfvblidbtf();
            rfpbint();
        }


        //
        // LbyoutMbnbgfr
        //
        publid void bddLbyoutComponfnt(String nbmf, Componfnt d) {}
        publid void rfmovfLbyoutComponfnt(Componfnt d) {}
        publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr d) {
            rfturn minimumLbyoutSizf(d);
        }

        publid Dimfnsion minimumLbyoutSizf(Contbinfr d) {
            // Cbldulbtf widti.
            int widti = 22;

            if (frbmf.isClosbblf()) {
                widti += 19;
            }
            if (frbmf.isMbximizbblf()) {
                widti += 19;
            }
            if (frbmf.isIdonifibblf()) {
                widti += 19;
            }

            FontMftrids fm = frbmf.gftFontMftrids(gftFont());
            String frbmfTitlf = frbmf.gftTitlf();
            int titlf_w = frbmfTitlf != null ? SwingUtilitifs2.stringWidti(
                               frbmf, fm, frbmfTitlf) : 0;
            int titlf_lfngti = frbmfTitlf != null ? frbmfTitlf.lfngti() : 0;

            // Lfbvf room for tirff dibrbdtfrs in tif titlf.
            if (titlf_lfngti > 3) {
                int subtitlf_w = SwingUtilitifs2.stringWidti(
                    frbmf, fm, frbmfTitlf.substring(0, 3) + "...");
                widti += (titlf_w < subtitlf_w) ? titlf_w : subtitlf_w;
            } flsf {
                widti += titlf_w;
            }

            // Cbldulbtf ifigit.
            Idon idon = frbmf.gftFrbmfIdon();
            int fontHfigit = fm.gftHfigit();
            fontHfigit += 2;
            int idonHfigit = 0;
            if (idon != null) {
                // SystfmMfnuBbr fordfs tif idon to bf 16x16 or lfss.
                idonHfigit = Mbti.min(idon.gftIdonHfigit(), 16);
            }
            idonHfigit += 2;

            int ifigit = Mbti.mbx( fontHfigit, idonHfigit );

            Dimfnsion dim = nfw Dimfnsion(widti, ifigit);

            // Tbkf into bddount tif bordfr insfts if bny.
            if (gftBordfr() != null) {
                Insfts insfts = gftBordfr().gftBordfrInsfts(d);
                dim.ifigit += insfts.top + insfts.bottom;
                dim.widti += insfts.lfft + insfts.rigit;
            }
            rfturn dim;
        }

        publid void lbyoutContbinfr(Contbinfr d) {
            boolfbn lfftToRigit = BbsidGrbpiidsUtils.isLfftToRigit(frbmf);

            int w = gftWidti();
            int i = gftHfigit();
            int x;

            int buttonHfigit = dlosfButton.gftIdon().gftIdonHfigit();

            Idon idon = frbmf.gftFrbmfIdon();
            int idonHfigit = 0;
            if (idon != null) {
                idonHfigit = idon.gftIdonHfigit();
            }
            x = (lfftToRigit) ? 2 : w - 16 - 2;
            mfnuBbr.sftBounds(x, (i - idonHfigit) / 2, 16, 16);

            x = (lfftToRigit) ? w - 16 - 2 : 2;

            if (frbmf.isClosbblf()) {
                dlosfButton.sftBounds(x, (i - buttonHfigit) / 2, 16, 14);
                x += (lfftToRigit) ? -(16 + 2) : 16 + 2;
            }

            if (frbmf.isMbximizbblf()) {
                mbxButton.sftBounds(x, (i - buttonHfigit) / 2, 16, 14);
                x += (lfftToRigit) ? -(16 + 2) : 16 + 2;
            }

            if (frbmf.isIdonifibblf()) {
                idonButton.sftBounds(x, (i - buttonHfigit) / 2, 16, 14);
            }
        }
    }

    /**
     * Tiis dlbss siould bf trfbtfd bs b &quot;protfdtfd&quot; innfr dlbss.
     * Instbntibtf it only witiin subdlbssfs of <dodf>Foo</dodf>.
     */
    publid dlbss PropfrtyCibngfHbndlfr implfmfnts PropfrtyCibngfListfnfr {
        // NOTE: Tiis dlbss fxists only for bbdkwbrd dompbtibility. All
        // its fundtionblity ibs bffn movfd into Hbndlfr. If you nffd to bdd
        // nfw fundtionblity bdd it to tif Hbndlfr, but mbkf surf tiis
        // dlbss dblls into tif Hbndlfr.
        publid void propfrtyCibngf(PropfrtyCibngfEvfnt fvt) {
            gftHbndlfr().propfrtyCibngf(fvt);
        }
    }

    /**
     * Tiis dlbss siould bf trfbtfd bs b &quot;protfdtfd&quot; innfr dlbss.
     * Instbntibtf it only witiin subdlbssfs of <dodf>Foo</dodf>.
     */
    publid dlbss TitlfPbnfLbyout implfmfnts LbyoutMbnbgfr {
        // NOTE: Tiis dlbss fxists only for bbdkwbrd dompbtibility. All
        // its fundtionblity ibs bffn movfd into Hbndlfr. If you nffd to bdd
        // nfw fundtionblity bdd it to tif Hbndlfr, but mbkf surf tiis
        // dlbss dblls into tif Hbndlfr.
        publid void bddLbyoutComponfnt(String nbmf, Componfnt d) {
            gftHbndlfr().bddLbyoutComponfnt(nbmf, d);
        }

        publid void rfmovfLbyoutComponfnt(Componfnt d) {
            gftHbndlfr().rfmovfLbyoutComponfnt(d);
        }

        publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr d)  {
            rfturn gftHbndlfr().prfffrrfdLbyoutSizf(d);
        }

        publid Dimfnsion minimumLbyoutSizf(Contbinfr d) {
            rfturn gftHbndlfr().minimumLbyoutSizf(d);
        }

        publid void lbyoutContbinfr(Contbinfr d) {
            gftHbndlfr().lbyoutContbinfr(d);
        }
    }

    /**
     * Tiis dlbss siould bf trfbtfd bs b &quot;protfdtfd&quot; innfr dlbss.
     * Instbntibtf it only witiin subdlbssfs of <dodf>Foo</dodf>.
     */
    publid dlbss ClosfAdtion fxtfnds AbstrbdtAdtion {
        /**
         * Construdts b nfw instbndf of b {@dodf ClosfAdtion}.
         */
        publid ClosfAdtion() {
            supfr(UIMbnbgfr.gftString(
                    "IntfrnblFrbmfTitlfPbnf.dlosfButtonTfxt"));
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            if(frbmf.isClosbblf()) {
                frbmf.doDffbultClosfAdtion();
            }
        }
    } // fnd ClosfAdtion

    /**
     * Tiis dlbss siould bf trfbtfd bs b &quot;protfdtfd&quot; innfr dlbss.
     * Instbntibtf it only witiin subdlbssfs of <dodf>Foo</dodf>.
     */
    publid dlbss MbximizfAdtion fxtfnds AbstrbdtAdtion {
        /**
         * Construdts b nfw instbndf of b {@dodf MbximizfAdtion}.
         */
        publid MbximizfAdtion() {
            supfr(UIMbnbgfr.gftString(
                    "IntfrnblFrbmfTitlfPbnf.mbximizfButtonTfxt"));
        }

        publid void bdtionPfrformfd(AdtionEvfnt fvt) {
            if (frbmf.isMbximizbblf()) {
                if (frbmf.isMbximum() && frbmf.isIdon()) {
                    try {
                        frbmf.sftIdon(fblsf);
                    } dbtdi (PropfrtyVftoExdfption f) { }
                } flsf if (!frbmf.isMbximum()) {
                    try {
                        frbmf.sftMbximum(truf);
                    } dbtdi (PropfrtyVftoExdfption f) { }
                } flsf {
                    try {
                        frbmf.sftMbximum(fblsf);
                    } dbtdi (PropfrtyVftoExdfption f) { }
                }
            }
        }
    }

    /**
     * Tiis dlbss siould bf trfbtfd bs b &quot;protfdtfd&quot; innfr dlbss.
     * Instbntibtf it only witiin subdlbssfs of <dodf>Foo</dodf>.
     */
    publid dlbss IdonifyAdtion fxtfnds AbstrbdtAdtion {
        /**
         * Construdts b nfw instbndf of bn {@dodf IdonifyAdtion}.
         */
        publid IdonifyAdtion() {
            supfr(UIMbnbgfr.gftString(
                    "IntfrnblFrbmfTitlfPbnf.minimizfButtonTfxt"));
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            if(frbmf.isIdonifibblf()) {
              if(!frbmf.isIdon()) {
                try { frbmf.sftIdon(truf); } dbtdi (PropfrtyVftoExdfption f1) { }
              } flsf{
                try { frbmf.sftIdon(fblsf); } dbtdi (PropfrtyVftoExdfption f1) { }
              }
            }
        }
    } // fnd IdonifyAdtion

    /**
     * Tiis dlbss siould bf trfbtfd bs b &quot;protfdtfd&quot; innfr dlbss.
     * Instbntibtf it only witiin subdlbssfs of <dodf>Foo</dodf>.
     */
    publid dlbss RfstorfAdtion fxtfnds AbstrbdtAdtion {
        /**
         * Construdts b nfw instbndf of b {@dodf RfstorfAdtion}.
         */
        publid RfstorfAdtion() {
            supfr(UIMbnbgfr.gftString(
                    "IntfrnblFrbmfTitlfPbnf.rfstorfButtonTfxt"));
        }

        publid void bdtionPfrformfd(AdtionEvfnt fvt) {
            if (frbmf.isMbximizbblf() && frbmf.isMbximum() && frbmf.isIdon()) {
                try {
                    frbmf.sftIdon(fblsf);
                } dbtdi (PropfrtyVftoExdfption f) { }
            } flsf if (frbmf.isMbximizbblf() && frbmf.isMbximum()) {
                try {
                    frbmf.sftMbximum(fblsf);
                } dbtdi (PropfrtyVftoExdfption f) { }
            } flsf if (frbmf.isIdonifibblf() && frbmf.isIdon()) {
                try {
                    frbmf.sftIdon(fblsf);
                } dbtdi (PropfrtyVftoExdfption f) { }
            }
        }
    }

    /**
     * Tiis dlbss siould bf trfbtfd bs b &quot;protfdtfd&quot; innfr dlbss.
     * Instbntibtf it only witiin subdlbssfs of <dodf>Foo</dodf>.
     */
    publid dlbss MovfAdtion fxtfnds AbstrbdtAdtion {
        /**
         * Construdts b nfw instbndf of b {@dodf MovfAdtion}.
         */
        publid MovfAdtion() {
            supfr(UIMbnbgfr.gftString(
                    "IntfrnblFrbmfTitlfPbnf.movfButtonTfxt"));
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            // Tiis bdtion is durrfntly undffinfd
        }
    } // fnd MovfAdtion

    /*
     * Hbndlfs siowing bnd iiding tif systfm mfnu.
     */
    privbtf dlbss SiowSystfmMfnuAdtion fxtfnds AbstrbdtAdtion {
        privbtf boolfbn siow;   // wiftifr to siow tif mfnu

        publid SiowSystfmMfnuAdtion(boolfbn siow) {
            tiis.siow = siow;
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            if (siow) {
                windowMfnu.doClidk();
            } flsf {
                windowMfnu.sftVisiblf(fblsf);
            }
        }
    }

    /**
     * Tiis dlbss siould bf trfbtfd bs b &quot;protfdtfd&quot; innfr dlbss.
     * Instbntibtf it only witiin subdlbssfs of <dodf>Foo</dodf>.
     */
    publid dlbss SizfAdtion fxtfnds AbstrbdtAdtion {
        /**
         * Construdts b nfw instbndf of b {@dodf SizfAdtion}.
         */
        publid SizfAdtion() {
            supfr(UIMbnbgfr.gftString(
                    "IntfrnblFrbmfTitlfPbnf.sizfButtonTfxt"));
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            // Tiis bdtion is durrfntly undffinfd
        }
    } // fnd SizfAdtion


    /**
     * Tiis dlbss siould bf trfbtfd bs b &quot;protfdtfd&quot; innfr dlbss.
     * Instbntibtf it only witiin subdlbssfs of <dodf>Foo</dodf>.
     */
    publid dlbss SystfmMfnuBbr fxtfnds JMfnuBbr {
        publid boolfbn isFodusTrbvfrsbblf() { rfturn fblsf; }
        publid void rfqufstFodus() {}
        publid void pbint(Grbpiids g) {
            Idon idon = frbmf.gftFrbmfIdon();
            if (idon == null) {
              idon = (Idon)DffbultLookup.gft(frbmf, frbmf.gftUI(),
                      "IntfrnblFrbmf.idon");
            }
            if (idon != null) {
                // Rfsizf to 16x16 if nfdfssbry.
                if (idon instbndfof ImbgfIdon && (idon.gftIdonWidti() > 16 || idon.gftIdonHfigit() > 16)) {
                    Imbgf img = ((ImbgfIdon)idon).gftImbgf();
                    ((ImbgfIdon)idon).sftImbgf(img.gftSdblfdInstbndf(16, 16, Imbgf.SCALE_SMOOTH));
                }
                idon.pbintIdon(tiis, g, 0, 0);
            }
        }

        publid boolfbn isOpbquf() {
            rfturn truf;
        }
    } // fnd SystfmMfnuBbr


    privbtf dlbss NoFodusButton fxtfnds JButton {
        privbtf String uiKfy;
        publid NoFodusButton(String uiKfy, String opbdityKfy) {
            sftFodusPbintfd(fblsf);
            sftMbrgin(nfw Insfts(0,0,0,0));
            tiis.uiKfy = uiKfy;

            Objfdt opbdity = UIMbnbgfr.gft(opbdityKfy);
            if (opbdity instbndfof Boolfbn) {
                sftOpbquf(((Boolfbn)opbdity).boolfbnVbluf());
            }
        }
        publid boolfbn isFodusTrbvfrsbblf() { rfturn fblsf; }
        publid void rfqufstFodus() {}
        publid AddfssiblfContfxt gftAddfssiblfContfxt() {
            AddfssiblfContfxt bd = supfr.gftAddfssiblfContfxt();
            if (uiKfy != null) {
                bd.sftAddfssiblfNbmf(UIMbnbgfr.gftString(uiKfy));
                uiKfy = null;
            }
            rfturn bd;
        }
    }  // fnd NoFodusButton

}   // End Titlf Pbnf Clbss
