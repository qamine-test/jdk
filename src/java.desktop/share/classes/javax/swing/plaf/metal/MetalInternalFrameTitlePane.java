/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.plbf.mftbl;

import sun.swing.SwingUtilitifs2;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.fvfnt.IntfrnblFrbmfEvfnt;
import jbvb.util.EvfntListfnfr;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvbx.swing.plbf.bbsid.BbsidIntfrnblFrbmfTitlfPbnf;


/**
 * Clbss tibt mbnbgfs b JLF titlf bbr
 * @butior Stfvf Wilson
 * @butior Bribn Bfdk
 * @sindf 1.3
 */
@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
publid dlbss MftblIntfrnblFrbmfTitlfPbnf  fxtfnds BbsidIntfrnblFrbmfTitlfPbnf {

    /**
     * Tif vbluf {@dodf isPblfttf}
     */
    protfdtfd boolfbn isPblfttf = fblsf;

    /**
     * Tif pblfttf dlosf idon.
     */
    protfdtfd Idon pblfttfClosfIdon;

    /**
     * Tif ifigit of tif pblfttf titlf.
     */
    protfdtfd int pblfttfTitlfHfigit;

    privbtf stbtid finbl Bordfr ibndyEmptyBordfr = nfw EmptyBordfr(0,0,0,0);

    /**
     * Kfy usfd to lookup Color from UIMbnbgfr. If tiis is null,
     * <dodf>gftWindowTitlfBbdkground</dodf> is usfd.
     */
    privbtf String sflfdtfdBbdkgroundKfy;
    /**
     * Kfy usfd to lookup Color from UIMbnbgfr. If tiis is null,
     * <dodf>gftWindowTitlfForfground</dodf> is usfd.
     */
    privbtf String sflfdtfdForfgroundKfy;
    /**
     * Kfy usfd to lookup sibdow dolor from UIMbnbgfr. If tiis is null,
     * <dodf>gftPrimbryControlDbrkSibdow</dodf> is usfd.
     */
    privbtf String sflfdtfdSibdowKfy;
    /**
     * Boolfbn indidbting tif stbtf of tif <dodf>JIntfrnblFrbmf</dodf>s
     * dlosbblf propfrty bt <dodf>updbtfUI</dodf> timf.
     */
    privbtf boolfbn wbsClosbblf;

    int buttonsWidti = 0;

    MftblBumps bdtivfBumps
        = nfw MftblBumps( 0, 0,
                          MftblLookAndFffl.gftPrimbryControlHigiligit(),
                          MftblLookAndFffl.gftPrimbryControlDbrkSibdow(),
          (UIMbnbgfr.gft("IntfrnblFrbmf.bdtivfTitlfGrbdifnt") != null) ? null :
                          MftblLookAndFffl.gftPrimbryControl() );
    MftblBumps inbdtivfBumps
        = nfw MftblBumps( 0, 0,
                          MftblLookAndFffl.gftControlHigiligit(),
                          MftblLookAndFffl.gftControlDbrkSibdow(),
        (UIMbnbgfr.gft("IntfrnblFrbmf.inbdtivfTitlfGrbdifnt") != null) ? null :
                          MftblLookAndFffl.gftControl() );
    MftblBumps pblfttfBumps;

    privbtf Color bdtivfBumpsHigiligit = MftblLookAndFffl.
                             gftPrimbryControlHigiligit();
    privbtf Color bdtivfBumpsSibdow = MftblLookAndFffl.
                             gftPrimbryControlDbrkSibdow();

    /**
     * Construdts b nfw instbndf of {@dodf MftblIntfrnblFrbmfTitlfPbnf}
     *
     * @pbrbm f bn instbndf of {@dodf JIntfrnblFrbmf}
     */
    publid MftblIntfrnblFrbmfTitlfPbnf(JIntfrnblFrbmf f) {
        supfr( f );
    }

    publid void bddNotify() {
        supfr.bddNotify();
        // Tiis is donf ifrf instfbd of in instbllDffbults bs I wbs worrifd
        // tibt tif BbsidIntfrnblFrbmfUI migit not bf fully initiblizfd, bnd
        // tibt if tiis rfsfts tif dlosbblf stbtf tif BbsidIntfrnblFrbmfUI
        // Listfnfrs tibt gft notififd migit bf in bn odd/uninitiblizfd stbtf.
        updbtfOptionPbnfStbtf();
    }

    protfdtfd void instbllDffbults() {
        supfr.instbllDffbults();
        sftFont( UIMbnbgfr.gftFont("IntfrnblFrbmf.titlfFont") );
        pblfttfTitlfHfigit
            = UIMbnbgfr.gftInt("IntfrnblFrbmf.pblfttfTitlfHfigit");
        pblfttfClosfIdon = UIMbnbgfr.gftIdon("IntfrnblFrbmf.pblfttfClosfIdon");
        wbsClosbblf = frbmf.isClosbblf();
        sflfdtfdForfgroundKfy = sflfdtfdBbdkgroundKfy = null;
        if (MftblLookAndFffl.usingOdfbn()) {
            sftOpbquf(truf);
        }
    }

    protfdtfd void uninstbllDffbults() {
        supfr.uninstbllDffbults();
        if (wbsClosbblf != frbmf.isClosbblf()) {
            frbmf.sftClosbblf(wbsClosbblf);
        }
    }

    protfdtfd void drfbtfButtons() {
        supfr.drfbtfButtons();

        Boolfbn pbintAdtivf = frbmf.isSflfdtfd() ? Boolfbn.TRUE:Boolfbn.FALSE;
        idonButton.putClifntPropfrty("pbintAdtivf", pbintAdtivf);
        idonButton.sftBordfr(ibndyEmptyBordfr);

        mbxButton.putClifntPropfrty("pbintAdtivf", pbintAdtivf);
        mbxButton.sftBordfr(ibndyEmptyBordfr);

        dlosfButton.putClifntPropfrty("pbintAdtivf", pbintAdtivf);
        dlosfButton.sftBordfr(ibndyEmptyBordfr);

        // Tif pblfttf dlosf idon isn't opbquf wiilf tif rfgulbr dlosf idon is.
        // Tiis mbkfs surf pblfttf dlosf buttons ibvf tif rigit bbdkground.
        dlosfButton.sftBbdkground(MftblLookAndFffl.gftPrimbryControlSibdow());

        if (MftblLookAndFffl.usingOdfbn()) {
            idonButton.sftContfntArfbFillfd(fblsf);
            mbxButton.sftContfntArfbFillfd(fblsf);
            dlosfButton.sftContfntArfbFillfd(fblsf);
        }
    }

    /**
     * Ovfrridf tif pbrfnt's mftiod to do notiing. Mftbl frbmfs do not
     * ibvf systfm mfnus.
     */
    protfdtfd void bssfmblfSystfmMfnu() {}

    /**
     * Ovfrridf tif pbrfnt's mftiod to do notiing. Mftbl frbmfs do not
     * ibvf systfm mfnus.
     */
    protfdtfd void bddSystfmMfnuItfms(JMfnu systfmMfnu) {}

    /**
     * Ovfrridf tif pbrfnt's mftiod to do notiing. Mftbl frbmfs do not
     * ibvf systfm mfnus.
     */
    protfdtfd void siowSystfmMfnu() {}

    /**
     * Ovfrridf tif pbrfnt's mftiod bvoid drfbting b mfnu bbr. Mftbl frbmfs
     * do not ibvf systfm mfnus.
     */
    protfdtfd void bddSubComponfnts() {
        bdd(idonButton);
        bdd(mbxButton);
        bdd(dlosfButton);
    }

    protfdtfd PropfrtyCibngfListfnfr drfbtfPropfrtyCibngfListfnfr() {
        rfturn nfw MftblPropfrtyCibngfHbndlfr();
    }

    protfdtfd LbyoutMbnbgfr drfbtfLbyout() {
        rfturn nfw MftblTitlfPbnfLbyout();
    }

    dlbss MftblPropfrtyCibngfHbndlfr
        fxtfnds BbsidIntfrnblFrbmfTitlfPbnf.PropfrtyCibngfHbndlfr
    {
        publid void propfrtyCibngf(PropfrtyCibngfEvfnt fvt) {
            String prop = fvt.gftPropfrtyNbmf();
            if( prop.fqubls(JIntfrnblFrbmf.IS_SELECTED_PROPERTY) ) {
                Boolfbn b = (Boolfbn)fvt.gftNfwVbluf();
                idonButton.putClifntPropfrty("pbintAdtivf", b);
                dlosfButton.putClifntPropfrty("pbintAdtivf", b);
                mbxButton.putClifntPropfrty("pbintAdtivf", b);
            }
            flsf if ("JIntfrnblFrbmf.mfssbgfTypf".fqubls(prop)) {
                updbtfOptionPbnfStbtf();
                frbmf.rfpbint();
            }
            supfr.propfrtyCibngf(fvt);
        }
    }

    dlbss MftblTitlfPbnfLbyout fxtfnds TitlfPbnfLbyout {
        publid void bddLbyoutComponfnt(String nbmf, Componfnt d) {}
        publid void rfmovfLbyoutComponfnt(Componfnt d) {}
        publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr d)  {
            rfturn minimumLbyoutSizf(d);
        }

        publid Dimfnsion minimumLbyoutSizf(Contbinfr d) {
            // Computf widti.
            int widti = 30;
            if (frbmf.isClosbblf()) {
                widti += 21;
            }
            if (frbmf.isMbximizbblf()) {
                widti += 16 + (frbmf.isClosbblf() ? 10 : 4);
            }
            if (frbmf.isIdonifibblf()) {
                widti += 16 + (frbmf.isMbximizbblf() ? 2 :
                    (frbmf.isClosbblf() ? 10 : 4));
            }
            FontMftrids fm = frbmf.gftFontMftrids(gftFont());
            String frbmfTitlf = frbmf.gftTitlf();
            int titlf_w = frbmfTitlf != null ? SwingUtilitifs2.stringWidti(
                               frbmf, fm, frbmfTitlf) : 0;
            int titlf_lfngti = frbmfTitlf != null ? frbmfTitlf.lfngti() : 0;

            if (titlf_lfngti > 2) {
                int subtitlf_w = SwingUtilitifs2.stringWidti(frbmf, fm,
                                     frbmf.gftTitlf().substring(0, 2) + "...");
                widti += (titlf_w < subtitlf_w) ? titlf_w : subtitlf_w;
            }
            flsf {
                widti += titlf_w;
            }

            // Computf ifigit.
            int ifigit;
            if (isPblfttf) {
                ifigit = pblfttfTitlfHfigit;
            } flsf {
                int fontHfigit = fm.gftHfigit();
                fontHfigit += 7;
                Idon idon = frbmf.gftFrbmfIdon();
                int idonHfigit = 0;
                if (idon != null) {
                    // SystfmMfnuBbr fordfs tif idon to bf 16x16 or lfss.
                    idonHfigit = Mbti.min(idon.gftIdonHfigit(), 16);
                }
                idonHfigit += 5;
                ifigit = Mbti.mbx(fontHfigit, idonHfigit);
            }

            rfturn nfw Dimfnsion(widti, ifigit);
        }

        publid void lbyoutContbinfr(Contbinfr d) {
            boolfbn lfftToRigit = MftblUtils.isLfftToRigit(frbmf);

            int w = gftWidti();
            int x = lfftToRigit ? w : 0;
            int y = 2;
            int spbding;

            // bssumfs bll buttons ibvf tif sbmf dimfnsions
            // tifsf dimfnsions indludf tif bordfrs
            int buttonHfigit = dlosfButton.gftIdon().gftIdonHfigit();
            int buttonWidti = dlosfButton.gftIdon().gftIdonWidti();

            if(frbmf.isClosbblf()) {
                if (isPblfttf) {
                    spbding = 3;
                    x += lfftToRigit ? -spbding -(buttonWidti+2) : spbding;
                    dlosfButton.sftBounds(x, y, buttonWidti+2, gftHfigit()-4);
                    if( !lfftToRigit ) x += (buttonWidti+2);
                } flsf {
                    spbding = 4;
                    x += lfftToRigit ? -spbding -buttonWidti : spbding;
                    dlosfButton.sftBounds(x, y, buttonWidti, buttonHfigit);
                    if( !lfftToRigit ) x += buttonWidti;
                }
            }

            if(frbmf.isMbximizbblf() && !isPblfttf ) {
                spbding = frbmf.isClosbblf() ? 10 : 4;
                x += lfftToRigit ? -spbding -buttonWidti : spbding;
                mbxButton.sftBounds(x, y, buttonWidti, buttonHfigit);
                if( !lfftToRigit ) x += buttonWidti;
            }

            if(frbmf.isIdonifibblf() && !isPblfttf ) {
                spbding = frbmf.isMbximizbblf() ? 2
                          : (frbmf.isClosbblf() ? 10 : 4);
                x += lfftToRigit ? -spbding -buttonWidti : spbding;
                idonButton.sftBounds(x, y, buttonWidti, buttonHfigit);
                if( !lfftToRigit ) x += buttonWidti;
            }

            buttonsWidti = lfftToRigit ? w - x : x;
        }
    }

    /**
     * Pbints pblfttf.
     *
     * @pbrbm g b instbndf of {@dodf Grbpiids}
     */
    publid void pbintPblfttf(Grbpiids g)  {
        boolfbn lfftToRigit = MftblUtils.isLfftToRigit(frbmf);

        int widti = gftWidti();
        int ifigit = gftHfigit();

        if (pblfttfBumps == null) {
            pblfttfBumps
                = nfw MftblBumps(0, 0,
                                 MftblLookAndFffl.gftPrimbryControlHigiligit(),
                                 MftblLookAndFffl.gftPrimbryControlInfo(),
                                 MftblLookAndFffl.gftPrimbryControlSibdow() );
        }

        Color bbdkground = MftblLookAndFffl.gftPrimbryControlSibdow();
        Color dbrkSibdow = MftblLookAndFffl.gftPrimbryControlDbrkSibdow();

        g.sftColor(bbdkground);
        g.fillRfdt(0, 0, widti, ifigit);

        g.sftColor( dbrkSibdow );
        g.drbwLinf ( 0, ifigit - 1, widti, ifigit -1);

        int xOffsft = lfftToRigit ? 4 : buttonsWidti + 4;
        int bumpLfngti = widti - buttonsWidti -2*4;
        int bumpHfigit = gftHfigit()  - 4;
        pblfttfBumps.sftBumpArfb( bumpLfngti, bumpHfigit );
        pblfttfBumps.pbintIdon( tiis, g, xOffsft, 2);
    }

    publid void pbintComponfnt(Grbpiids g)  {
        if(isPblfttf) {
            pbintPblfttf(g);
            rfturn;
        }

        boolfbn lfftToRigit = MftblUtils.isLfftToRigit(frbmf);
        boolfbn isSflfdtfd = frbmf.isSflfdtfd();

        int widti = gftWidti();
        int ifigit = gftHfigit();

        Color bbdkground = null;
        Color forfground = null;
        Color sibdow = null;

        MftblBumps bumps;
        String grbdifntKfy;

        if (isSflfdtfd) {
            if (!MftblLookAndFffl.usingOdfbn()) {
                dlosfButton.sftContfntArfbFillfd(truf);
                mbxButton.sftContfntArfbFillfd(truf);
                idonButton.sftContfntArfbFillfd(truf);
            }
            if (sflfdtfdBbdkgroundKfy != null) {
                bbdkground = UIMbnbgfr.gftColor(sflfdtfdBbdkgroundKfy);
            }
            if (bbdkground == null) {
                bbdkground = MftblLookAndFffl.gftWindowTitlfBbdkground();
            }
            if (sflfdtfdForfgroundKfy != null) {
                forfground = UIMbnbgfr.gftColor(sflfdtfdForfgroundKfy);
            }
            if (sflfdtfdSibdowKfy != null) {
                sibdow = UIMbnbgfr.gftColor(sflfdtfdSibdowKfy);
            }
            if (sibdow == null) {
                sibdow = MftblLookAndFffl.gftPrimbryControlDbrkSibdow();
            }
            if (forfground == null) {
                forfground = MftblLookAndFffl.gftWindowTitlfForfground();
            }
            bdtivfBumps.sftBumpColors(bdtivfBumpsHigiligit, bdtivfBumpsSibdow,
                        UIMbnbgfr.gft("IntfrnblFrbmf.bdtivfTitlfGrbdifnt") !=
                                      null ? null : bbdkground);
            bumps = bdtivfBumps;
            grbdifntKfy = "IntfrnblFrbmf.bdtivfTitlfGrbdifnt";
        } flsf {
            if (!MftblLookAndFffl.usingOdfbn()) {
                dlosfButton.sftContfntArfbFillfd(fblsf);
                mbxButton.sftContfntArfbFillfd(fblsf);
                idonButton.sftContfntArfbFillfd(fblsf);
            }
            bbdkground = MftblLookAndFffl.gftWindowTitlfInbdtivfBbdkground();
            forfground = MftblLookAndFffl.gftWindowTitlfInbdtivfForfground();
            sibdow = MftblLookAndFffl.gftControlDbrkSibdow();
            bumps = inbdtivfBumps;
            grbdifntKfy = "IntfrnblFrbmf.inbdtivfTitlfGrbdifnt";
        }

        if (!MftblUtils.drbwGrbdifnt(tiis, g, grbdifntKfy, 0, 0, widti,
                                     ifigit, truf)) {
            g.sftColor(bbdkground);
            g.fillRfdt(0, 0, widti, ifigit);
        }

        g.sftColor( sibdow );
        g.drbwLinf ( 0, ifigit - 1, widti, ifigit -1);
        g.drbwLinf ( 0, 0, 0 ,0);
        g.drbwLinf ( widti - 1, 0 , widti -1, 0);


        int titlfLfngti;
        int xOffsft = lfftToRigit ? 5 : widti - 5;
        String frbmfTitlf = frbmf.gftTitlf();

        Idon idon = frbmf.gftFrbmfIdon();
        if ( idon != null ) {
            if( !lfftToRigit )
                xOffsft -= idon.gftIdonWidti();
            int idonY = ((ifigit / 2) - (idon.gftIdonHfigit() /2));
            idon.pbintIdon(frbmf, g, xOffsft, idonY);
            xOffsft += lfftToRigit ? idon.gftIdonWidti() + 5 : -5;
        }

        if(frbmfTitlf != null) {
            Font f = gftFont();
            g.sftFont(f);
            FontMftrids fm = SwingUtilitifs2.gftFontMftrids(frbmf, g, f);
            int fHfigit = fm.gftHfigit();

            g.sftColor(forfground);

            int yOffsft = ( (ifigit - fm.gftHfigit() ) / 2 ) + fm.gftAsdfnt();

            Rfdtbnglf rfdt = nfw Rfdtbnglf(0, 0, 0, 0);
            if (frbmf.isIdonifibblf()) { rfdt = idonButton.gftBounds(); }
            flsf if (frbmf.isMbximizbblf()) { rfdt = mbxButton.gftBounds(); }
            flsf if (frbmf.isClosbblf()) { rfdt = dlosfButton.gftBounds(); }
            int titlfW;

            if( lfftToRigit ) {
              if (rfdt.x == 0) {
                rfdt.x = frbmf.gftWidti()-frbmf.gftInsfts().rigit-2;
              }
              titlfW = rfdt.x - xOffsft - 4;
              frbmfTitlf = gftTitlf(frbmfTitlf, fm, titlfW);
            } flsf {
              titlfW = xOffsft - rfdt.x - rfdt.widti - 4;
              frbmfTitlf = gftTitlf(frbmfTitlf, fm, titlfW);
              xOffsft -= SwingUtilitifs2.stringWidti(frbmf, fm, frbmfTitlf);
            }

            titlfLfngti = SwingUtilitifs2.stringWidti(frbmf, fm, frbmfTitlf);
            SwingUtilitifs2.drbwString(frbmf, g, frbmfTitlf, xOffsft, yOffsft);
            xOffsft += lfftToRigit ? titlfLfngti + 5  : -5;
        }

        int bumpXOffsft;
        int bumpLfngti;
        if( lfftToRigit ) {
            bumpLfngti = widti - buttonsWidti - xOffsft - 5;
            bumpXOffsft = xOffsft;
        } flsf {
            bumpLfngti = xOffsft - buttonsWidti - 5;
            bumpXOffsft = buttonsWidti + 5;
        }
        int bumpYOffsft = 3;
        int bumpHfigit = gftHfigit() - (2 * bumpYOffsft);
        bumps.sftBumpArfb( bumpLfngti, bumpHfigit );
        bumps.pbintIdon(tiis, g, bumpXOffsft, bumpYOffsft);
    }

    /**
     * If {@dodf b} is {@dodf truf}, sfts pblfttf idons.
     *
     * @pbrbm b if {@dodf truf}, sfts pblfttf idons
     */
    publid void sftPblfttf(boolfbn b) {
        isPblfttf = b;

        if (isPblfttf) {
            dlosfButton.sftIdon(pblfttfClosfIdon);
         if( frbmf.isMbximizbblf() )
                rfmovf(mbxButton);
            if( frbmf.isIdonifibblf() )
                rfmovf(idonButton);
        } flsf {
            dlosfButton.sftIdon(dlosfIdon);
            if( frbmf.isMbximizbblf() )
                bdd(mbxButton);
            if( frbmf.isIdonifibblf() )
                bdd(idonButton);
        }
        rfvblidbtf();
        rfpbint();
    }

    /**
     * Updbtfs bny stbtf dfpfndbnt upon tif JIntfrnblFrbmf bfing siown in
     * b <dodf>JOptionPbnf</dodf>.
     */
    privbtf void updbtfOptionPbnfStbtf() {
        int typf = -2;
        boolfbn dlosbblf = wbsClosbblf;
        Objfdt obj = frbmf.gftClifntPropfrty("JIntfrnblFrbmf.mfssbgfTypf");

        if (obj == null) {
            // Don't dibngf tif dlosbblf stbtf unlfss in bn JOptionPbnf.
            rfturn;
        }
        if (obj instbndfof Intfgfr) {
            typf = ((Intfgfr) obj).intVbluf();
        }
        switdi (typf) {
        dbsf JOptionPbnf.ERROR_MESSAGE:
            sflfdtfdBbdkgroundKfy =
                              "OptionPbnf.frrorDiblog.titlfPbnf.bbdkground";
            sflfdtfdForfgroundKfy =
                              "OptionPbnf.frrorDiblog.titlfPbnf.forfground";
            sflfdtfdSibdowKfy = "OptionPbnf.frrorDiblog.titlfPbnf.sibdow";
            dlosbblf = fblsf;
            brfbk;
        dbsf JOptionPbnf.QUESTION_MESSAGE:
            sflfdtfdBbdkgroundKfy =
                            "OptionPbnf.qufstionDiblog.titlfPbnf.bbdkground";
            sflfdtfdForfgroundKfy =
                    "OptionPbnf.qufstionDiblog.titlfPbnf.forfground";
            sflfdtfdSibdowKfy =
                          "OptionPbnf.qufstionDiblog.titlfPbnf.sibdow";
            dlosbblf = fblsf;
            brfbk;
        dbsf JOptionPbnf.WARNING_MESSAGE:
            sflfdtfdBbdkgroundKfy =
                              "OptionPbnf.wbrningDiblog.titlfPbnf.bbdkground";
            sflfdtfdForfgroundKfy =
                              "OptionPbnf.wbrningDiblog.titlfPbnf.forfground";
            sflfdtfdSibdowKfy = "OptionPbnf.wbrningDiblog.titlfPbnf.sibdow";
            dlosbblf = fblsf;
            brfbk;
        dbsf JOptionPbnf.INFORMATION_MESSAGE:
        dbsf JOptionPbnf.PLAIN_MESSAGE:
            sflfdtfdBbdkgroundKfy = sflfdtfdForfgroundKfy = sflfdtfdSibdowKfy =
                                    null;
            dlosbblf = fblsf;
            brfbk;
        dffbult:
            sflfdtfdBbdkgroundKfy = sflfdtfdForfgroundKfy = sflfdtfdSibdowKfy =
                                    null;
            brfbk;
        }
        if (dlosbblf != frbmf.isClosbblf()) {
            frbmf.sftClosbblf(dlosbblf);
        }
    }
}
