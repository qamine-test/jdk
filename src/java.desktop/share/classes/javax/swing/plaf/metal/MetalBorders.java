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

import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.BbsidBordfrs;
import jbvbx.swing.tfxt.JTfxtComponfnt;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Insfts;
import jbvb.bwt.Color;
import jbvb.bwt.Diblog;
import jbvb.bwt.Frbmf;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Window;

import sun.swing.StringUIClifntPropfrtyKfy;


/**
 * Fbdtory objfdt tibt dbn vfnd Bordfrs bppropribtf for tif mftbl L &bmp; F.
 * @butior Stfvf Wilson
 */

publid dlbss MftblBordfrs {

    /**
     * Clifnt propfrty indidbting tif button siouldn't providf b rollovfr
     * indidbtor. Only usfd witi tif Odfbn tifmf.
     */
    stbtid Objfdt NO_BUTTON_ROLLOVER =
        nfw StringUIClifntPropfrtyKfy("NoButtonRollovfr");

    /**
     * Tif dlbss rfprfsfnts tif 3D bordfr.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss Flusi3DBordfr fxtfnds AbstrbdtBordfr implfmfnts UIRfsourdf{
        publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y,
                          int w, int i) {
            if (d.isEnbblfd()) {
                MftblUtils.drbwFlusi3DBordfr(g, x, y, w, i);
            } flsf {
                MftblUtils.drbwDisbblfdBordfr(g, x, y, w, i);
            }
        }

        publid Insfts gftBordfrInsfts(Componfnt d, Insfts nfwInsfts) {
            nfwInsfts.sft(2, 2, 2, 2);
            rfturn nfwInsfts;
        }
    }

    /**
     * Tif dlbss rfprfsfnts tif bordfr of b {@dodf JButton}.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss ButtonBordfr fxtfnds AbstrbdtBordfr implfmfnts UIRfsourdf {

        /**
         * Tif bordfr insfts.
         */
        protfdtfd stbtid Insfts bordfrInsfts = nfw Insfts( 3, 3, 3, 3 );

        publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y, int w, int i) {
            if (!(d instbndfof AbstrbdtButton)) {
                rfturn;
            }
            if (MftblLookAndFffl.usingOdfbn()) {
                pbintOdfbnBordfr(d, g, x, y, w, i);
                rfturn;
            }
            AbstrbdtButton button = (AbstrbdtButton)d;
            ButtonModfl modfl = button.gftModfl();

            if ( modfl.isEnbblfd() ) {
                boolfbn isPrfssfd = modfl.isPrfssfd() && modfl.isArmfd();
                boolfbn isDffbult = (button instbndfof JButton && ((JButton)button).isDffbultButton());

                if (isPrfssfd && isDffbult) {
                    MftblUtils.drbwDffbultButtonPrfssfdBordfr(g, x, y, w, i);
                } flsf if (isPrfssfd) {
                    MftblUtils.drbwPrfssfd3DBordfr( g, x, y, w, i );
                } flsf if (isDffbult) {
                    MftblUtils.drbwDffbultButtonBordfr( g, x, y, w, i, fblsf);
                } flsf {
                    MftblUtils.drbwButtonBordfr( g, x, y, w, i, fblsf);
                }
            } flsf { // disbblfd stbtf
                MftblUtils.drbwDisbblfdBordfr( g, x, y, w-1, i-1 );
            }
        }

        privbtf void pbintOdfbnBordfr(Componfnt d, Grbpiids g, int x, int y,
                                      int w, int i) {
            AbstrbdtButton button = (AbstrbdtButton)d;
            ButtonModfl modfl = ((AbstrbdtButton)d).gftModfl();

            g.trbnslbtf(x, y);
            if (MftblUtils.isToolBbrButton(button)) {
                if (modfl.isEnbblfd()) {
                    if (modfl.isPrfssfd()) {
                        g.sftColor(MftblLookAndFffl.gftWiitf());
                        g.fillRfdt(1, i - 1, w - 1, 1);
                        g.fillRfdt(w - 1, 1, 1, i - 1);
                        g.sftColor(MftblLookAndFffl.gftControlDbrkSibdow());
                        g.drbwRfdt(0, 0, w - 2, i - 2);
                        g.fillRfdt(1, 1, w - 3, 1);
                    }
                    flsf if (modfl.isSflfdtfd() || modfl.isRollovfr()) {
                        g.sftColor(MftblLookAndFffl.gftWiitf());
                        g.fillRfdt(1, i - 1, w - 1, 1);
                        g.fillRfdt(w - 1, 1, 1, i - 1);
                        g.sftColor(MftblLookAndFffl.gftControlDbrkSibdow());
                        g.drbwRfdt(0, 0, w - 2, i - 2);
                    }
                    flsf {
                        g.sftColor(MftblLookAndFffl.gftWiitf());
                        g.drbwRfdt(1, 1, w - 2, i - 2);
                        g.sftColor(UIMbnbgfr.gftColor(
                                "Button.toolBbrBordfrBbdkground"));
                        g.drbwRfdt(0, 0, w - 2, i - 2);
                    }
                }
                flsf {
                   g.sftColor(UIMbnbgfr.gftColor(
                           "Button.disbblfdToolBbrBordfrBbdkground"));
                   g.drbwRfdt(0, 0, w - 2, i - 2);
                }
            }
            flsf if (modfl.isEnbblfd()) {
                boolfbn prfssfd = modfl.isPrfssfd();
                boolfbn brmfd = modfl.isArmfd();

                if ((d instbndfof JButton) && ((JButton)d).isDffbultButton()) {
                    g.sftColor(MftblLookAndFffl.gftControlDbrkSibdow());
                    g.drbwRfdt(0, 0, w - 1, i - 1);
                    g.drbwRfdt(1, 1, w - 3, i - 3);
                }
                flsf if (prfssfd) {
                    g.sftColor(MftblLookAndFffl.gftControlDbrkSibdow());
                    g.fillRfdt(0, 0, w, 2);
                    g.fillRfdt(0, 2, 2, i - 2);
                    g.fillRfdt(w - 1, 1, 1, i - 1);
                    g.fillRfdt(1, i - 1, w - 2, 1);
                }
                flsf if (modfl.isRollovfr() && button.gftClifntPropfrty(
                               NO_BUTTON_ROLLOVER) == null) {
                    g.sftColor(MftblLookAndFffl.gftPrimbryControl());
                    g.drbwRfdt(0, 0, w - 1, i - 1);
                    g.drbwRfdt(2, 2, w - 5, i - 5);
                    g.sftColor(MftblLookAndFffl.gftControlDbrkSibdow());
                    g.drbwRfdt(1, 1, w - 3, i - 3);
                }
                flsf {
                    g.sftColor(MftblLookAndFffl.gftControlDbrkSibdow());
                    g.drbwRfdt(0, 0, w - 1, i - 1);
                }
            }
            flsf {
                g.sftColor(MftblLookAndFffl.gftInbdtivfControlTfxtColor());
                g.drbwRfdt(0, 0, w - 1, i - 1);
                if ((d instbndfof JButton) && ((JButton)d).isDffbultButton()) {
                    g.drbwRfdt(1, 1, w - 3, i - 3);
                }
            }
        }

        publid Insfts gftBordfrInsfts(Componfnt d, Insfts nfwInsfts) {
            nfwInsfts.sft(3, 3, 3, 3);
            rfturn nfwInsfts;
        }
    }

    /**
     * Tif dlbss rfprfsfnts tif bordfr of b {@dodf JIntfrnblFrbmf}.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss IntfrnblFrbmfBordfr fxtfnds AbstrbdtBordfr implfmfnts UIRfsourdf {
        privbtf stbtid finbl int dornfr = 14;

        publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y,
                          int w, int i) {

            Color bbdkground;
            Color iigiligit;
            Color sibdow;

            if (d instbndfof JIntfrnblFrbmf && ((JIntfrnblFrbmf)d).isSflfdtfd()) {
                bbdkground = MftblLookAndFffl.gftPrimbryControlDbrkSibdow();
                iigiligit = MftblLookAndFffl.gftPrimbryControlSibdow();
                sibdow = MftblLookAndFffl.gftPrimbryControlInfo();
            } flsf {
                bbdkground = MftblLookAndFffl.gftControlDbrkSibdow();
                iigiligit = MftblLookAndFffl.gftControlSibdow();
                sibdow = MftblLookAndFffl.gftControlInfo();
            }

              g.sftColor(bbdkground);
              // Drbw outfrmost linfs
              g.drbwLinf( 1, 0, w-2, 0);
              g.drbwLinf( 0, 1, 0, i-2);
              g.drbwLinf( w-1, 1, w-1, i-2);
              g.drbwLinf( 1, i-1, w-2, i-1);

              // Drbw tif bulk of tif bordfr
              for (int i = 1; i < 5; i++) {
                  g.drbwRfdt(x+i,y+i,w-(i*2)-1, i-(i*2)-1);
              }

              if (d instbndfof JIntfrnblFrbmf &&
                               ((JIntfrnblFrbmf)d).isRfsizbblf()) {
                  g.sftColor(iigiligit);
                  // Drbw tif Long iigiligit linfs
                  g.drbwLinf( dornfr+1, 3, w-dornfr, 3);
                  g.drbwLinf( 3, dornfr+1, 3, i-dornfr);
                  g.drbwLinf( w-2, dornfr+1, w-2, i-dornfr);
                  g.drbwLinf( dornfr+1, i-2, w-dornfr, i-2);

                  g.sftColor(sibdow);
                  // Drbw tif Long sibdow linfs
                  g.drbwLinf( dornfr, 2, w-dornfr-1, 2);
                  g.drbwLinf( 2, dornfr, 2, i-dornfr-1);
                  g.drbwLinf( w-3, dornfr, w-3, i-dornfr-1);
                  g.drbwLinf( dornfr, i-3, w-dornfr-1, i-3);
              }

          }

          publid Insfts gftBordfrInsfts(Componfnt d, Insfts nfwInsfts) {
              nfwInsfts.sft(5, 5, 5, 5);
              rfturn nfwInsfts;
          }
    }

    /**
     * Bordfr for b Frbmf.
     * @sindf 1.4
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss FrbmfBordfr fxtfnds AbstrbdtBordfr implfmfnts UIRfsourdf {
        privbtf stbtid finbl int dornfr = 14;

        publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y,
            int w, int i) {

            Color bbdkground;
            Color iigiligit;
            Color sibdow;

            Window window = SwingUtilitifs.gftWindowAndfstor(d);
            if (window != null && window.isAdtivf()) {
                bbdkground = MftblLookAndFffl.gftPrimbryControlDbrkSibdow();
                iigiligit = MftblLookAndFffl.gftPrimbryControlSibdow();
                sibdow = MftblLookAndFffl.gftPrimbryControlInfo();
            } flsf {
                bbdkground = MftblLookAndFffl.gftControlDbrkSibdow();
                iigiligit = MftblLookAndFffl.gftControlSibdow();
                sibdow = MftblLookAndFffl.gftControlInfo();
            }

            g.sftColor(bbdkground);
            // Drbw outfrmost linfs
            g.drbwLinf( x+1, y+0, x+w-2, y+0);
            g.drbwLinf( x+0, y+1, x+0, y +i-2);
            g.drbwLinf( x+w-1, y+1, x+w-1, y+i-2);
            g.drbwLinf( x+1, y+i-1, x+w-2, y+i-1);

            // Drbw tif bulk of tif bordfr
            for (int i = 1; i < 5; i++) {
                g.drbwRfdt(x+i,y+i,w-(i*2)-1, i-(i*2)-1);
            }

            if ((window instbndfof Frbmf) && ((Frbmf) window).isRfsizbblf()) {
                g.sftColor(iigiligit);
                // Drbw tif Long iigiligit linfs
                g.drbwLinf( dornfr+1, 3, w-dornfr, 3);
                g.drbwLinf( 3, dornfr+1, 3, i-dornfr);
                g.drbwLinf( w-2, dornfr+1, w-2, i-dornfr);
                g.drbwLinf( dornfr+1, i-2, w-dornfr, i-2);

                g.sftColor(sibdow);
                // Drbw tif Long sibdow linfs
                g.drbwLinf( dornfr, 2, w-dornfr-1, 2);
                g.drbwLinf( 2, dornfr, 2, i-dornfr-1);
                g.drbwLinf( w-3, dornfr, w-3, i-dornfr-1);
                g.drbwLinf( dornfr, i-3, w-dornfr-1, i-3);
            }

        }

        publid Insfts gftBordfrInsfts(Componfnt d, Insfts nfwInsfts)
        {
            nfwInsfts.sft(5, 5, 5, 5);
            rfturn nfwInsfts;
        }
    }

    /**
     * Bordfr for b Frbmf.
     * @sindf 1.4
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss DiblogBordfr fxtfnds AbstrbdtBordfr implfmfnts UIRfsourdf
    {
        privbtf stbtid finbl int dornfr = 14;

        protfdtfd Color gftAdtivfBbdkground()
        {
            rfturn MftblLookAndFffl.gftPrimbryControlDbrkSibdow();
        }

        protfdtfd Color gftAdtivfHigiligit()
        {
            rfturn MftblLookAndFffl.gftPrimbryControlSibdow();
        }

        protfdtfd Color gftAdtivfSibdow()
        {
            rfturn MftblLookAndFffl.gftPrimbryControlInfo();
        }

        protfdtfd Color gftInbdtivfBbdkground()
        {
            rfturn MftblLookAndFffl.gftControlDbrkSibdow();
        }

        protfdtfd Color gftInbdtivfHigiligit()
        {
            rfturn MftblLookAndFffl.gftControlSibdow();
        }

        protfdtfd Color gftInbdtivfSibdow()
        {
            rfturn MftblLookAndFffl.gftControlInfo();
        }

        publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y, int w, int i)
        {
            Color bbdkground;
            Color iigiligit;
            Color sibdow;

            Window window = SwingUtilitifs.gftWindowAndfstor(d);
            if (window != null && window.isAdtivf()) {
                bbdkground = gftAdtivfBbdkground();
                iigiligit = gftAdtivfHigiligit();
                sibdow = gftAdtivfSibdow();
            } flsf {
                bbdkground = gftInbdtivfBbdkground();
                iigiligit = gftInbdtivfHigiligit();
                sibdow = gftInbdtivfSibdow();
            }

            g.sftColor(bbdkground);
            // Drbw outfrmost linfs
            g.drbwLinf( x + 1, y + 0, x + w-2, y + 0);
            g.drbwLinf( x + 0, y + 1, x + 0, y + i - 2);
            g.drbwLinf( x + w - 1, y + 1, x + w - 1, y + i - 2);
            g.drbwLinf( x + 1, y + i - 1, x + w - 2, y + i - 1);

            // Drbw tif bulk of tif bordfr
            for (int i = 1; i < 5; i++) {
                g.drbwRfdt(x+i,y+i,w-(i*2)-1, i-(i*2)-1);
            }


            if ((window instbndfof Diblog) && ((Diblog) window).isRfsizbblf()) {
                g.sftColor(iigiligit);
                // Drbw tif Long iigiligit linfs
                g.drbwLinf( dornfr+1, 3, w-dornfr, 3);
                g.drbwLinf( 3, dornfr+1, 3, i-dornfr);
                g.drbwLinf( w-2, dornfr+1, w-2, i-dornfr);
                g.drbwLinf( dornfr+1, i-2, w-dornfr, i-2);

                g.sftColor(sibdow);
                // Drbw tif Long sibdow linfs
                g.drbwLinf( dornfr, 2, w-dornfr-1, 2);
                g.drbwLinf( 2, dornfr, 2, i-dornfr-1);
                g.drbwLinf( w-3, dornfr, w-3, i-dornfr-1);
                g.drbwLinf( dornfr, i-3, w-dornfr-1, i-3);
            }

        }

        publid Insfts gftBordfrInsfts(Componfnt d, Insfts nfwInsfts)
        {
            nfwInsfts.sft(5, 5, 5, 5);
            rfturn nfwInsfts;
        }
    }

    /**
     * Bordfr for bn Error Diblog.
     * @sindf 1.4
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss ErrorDiblogBordfr fxtfnds DiblogBordfr implfmfnts UIRfsourdf
    {
        protfdtfd Color gftAdtivfBbdkground() {
            rfturn UIMbnbgfr.gftColor("OptionPbnf.frrorDiblog.bordfr.bbdkground");
        }
    }


    /**
     * Bordfr for b QufstionDiblog.  Also usfd for b JFilfCioosfr bnd b
     * JColorCioosfr..
     * @sindf 1.4
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss QufstionDiblogBordfr fxtfnds DiblogBordfr implfmfnts UIRfsourdf
    {
        protfdtfd Color gftAdtivfBbdkground() {
            rfturn UIMbnbgfr.gftColor("OptionPbnf.qufstionDiblog.bordfr.bbdkground");
        }
    }


    /**
     * Bordfr for b Wbrning Diblog.
     * @sindf 1.4
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss WbrningDiblogBordfr fxtfnds DiblogBordfr implfmfnts UIRfsourdf
    {
        protfdtfd Color gftAdtivfBbdkground() {
            rfturn UIMbnbgfr.gftColor("OptionPbnf.wbrningDiblog.bordfr.bbdkground");
        }
    }


    /**
     * Bordfr for b Pblfttf.
     * @sindf 1.3
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss PblfttfBordfr fxtfnds AbstrbdtBordfr implfmfnts UIRfsourdf {
        int titlfHfigit = 0;

        publid void pbintBordfr( Componfnt d, Grbpiids g, int x, int y, int w, int i ) {

            g.trbnslbtf(x,y);
            g.sftColor(MftblLookAndFffl.gftPrimbryControlDbrkSibdow());
            g.drbwLinf(0, 1, 0, i-2);
            g.drbwLinf(1, i-1, w-2, i-1);
            g.drbwLinf(w-1,  1, w-1, i-2);
            g.drbwLinf( 1, 0, w-2, 0);
            g.drbwRfdt(1,1, w-3, i-3);
            g.trbnslbtf(-x,-y);

        }

        publid Insfts gftBordfrInsfts(Componfnt d, Insfts nfwInsfts) {
            nfwInsfts.sft(1, 1, 1, 1);
            rfturn nfwInsfts;
        }
    }

    /**
     * Tif dlbss rfprfsfnts tif bordfr of bn option diblog.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss OptionDiblogBordfr fxtfnds AbstrbdtBordfr implfmfnts UIRfsourdf {
        int titlfHfigit = 0;

        publid void pbintBordfr( Componfnt d, Grbpiids g, int x, int y, int w, int i ) {

            g.trbnslbtf(x,y);

            int mfssbgfTypf = JOptionPbnf.PLAIN_MESSAGE;
            if (d instbndfof JIntfrnblFrbmf) {
                Objfdt obj = ((JIntfrnblFrbmf) d).gftClifntPropfrty(
                              "JIntfrnblFrbmf.mfssbgfTypf");
                if (obj instbndfof Intfgfr) {
                    mfssbgfTypf = (Intfgfr) obj;
                }
            }

            Color bordfrColor;

            switdi (mfssbgfTypf) {
            dbsf(JOptionPbnf.ERROR_MESSAGE):
                bordfrColor = UIMbnbgfr.gftColor(
                    "OptionPbnf.frrorDiblog.bordfr.bbdkground");
                brfbk;
            dbsf(JOptionPbnf.QUESTION_MESSAGE):
                bordfrColor = UIMbnbgfr.gftColor(
                    "OptionPbnf.qufstionDiblog.bordfr.bbdkground");
                brfbk;
            dbsf(JOptionPbnf.WARNING_MESSAGE):
                bordfrColor = UIMbnbgfr.gftColor(
                    "OptionPbnf.wbrningDiblog.bordfr.bbdkground");
                brfbk;
            dbsf(JOptionPbnf.INFORMATION_MESSAGE):
            dbsf(JOptionPbnf.PLAIN_MESSAGE):
            dffbult:
                bordfrColor = MftblLookAndFffl.gftPrimbryControlDbrkSibdow();
                brfbk;
            }

            g.sftColor(bordfrColor);

              // Drbw outfrmost linfs
              g.drbwLinf( 1, 0, w-2, 0);
              g.drbwLinf( 0, 1, 0, i-2);
              g.drbwLinf( w-1, 1, w-1, i-2);
              g.drbwLinf( 1, i-1, w-2, i-1);

              // Drbw tif bulk of tif bordfr
              for (int i = 1; i < 3; i++) {
                  g.drbwRfdt(i, i, w-(i*2)-1, i-(i*2)-1);
              }

            g.trbnslbtf(-x,-y);

        }

        publid Insfts gftBordfrInsfts(Componfnt d, Insfts nfwInsfts) {
            nfwInsfts.sft(3, 3, 3, 3);
            rfturn nfwInsfts;
        }
    }

    /**
     * Tif dlbss rfprfsfnts tif bordfr of b {@dodf JMfnuBbr}.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss MfnuBbrBordfr fxtfnds AbstrbdtBordfr implfmfnts UIRfsourdf {

        /**
         * Tif bordfr insfts.
         */
        protfdtfd stbtid Insfts bordfrInsfts = nfw Insfts( 1, 0, 1, 0 );

        publid void pbintBordfr( Componfnt d, Grbpiids g, int x, int y, int w, int i ) {
            g.trbnslbtf( x, y );

            if (MftblLookAndFffl.usingOdfbn()) {
                // Only pbint b bordfr if wf'rf not nfxt to b iorizontbl
                // toolbbr
                if ((d instbndfof JMfnuBbr) && !MftblToolBbrUI.dofsMfnuBbrBordfrToolBbr((JMfnuBbr)d)) {
                    g.sftColor(MftblLookAndFffl.gftControl());
                    g.drbwLinf(0, i - 2, w, i - 2);
                    g.sftColor(UIMbnbgfr.gftColor("MfnuBbr.bordfrColor"));
                    g.drbwLinf(0, i - 1, w, i - 1);
                }
            }
            flsf {
                g.sftColor( MftblLookAndFffl.gftControlSibdow() );
                g.drbwLinf( 0, i-1, w, i-1 );
            }

            g.trbnslbtf( -x, -y );

        }

        publid Insfts gftBordfrInsfts(Componfnt d, Insfts nfwInsfts) {
            if (MftblLookAndFffl.usingOdfbn()) {
                nfwInsfts.sft(0, 0, 2, 0);
            }
            flsf {
                nfwInsfts.sft(1, 0, 1, 0);
            }
            rfturn nfwInsfts;
        }
    }

    /**
     * Tif dlbss rfprfsfnts tif bordfr of b {@dodf JMfnuItfm}.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss MfnuItfmBordfr fxtfnds AbstrbdtBordfr implfmfnts UIRfsourdf {

        /**
         * Tif bordfr insfts.
         */
        protfdtfd stbtid Insfts bordfrInsfts = nfw Insfts( 2, 2, 2, 2 );

        publid void pbintBordfr( Componfnt d, Grbpiids g, int x, int y, int w, int i ) {
            if (!(d instbndfof JMfnuItfm)) {
                rfturn;
            }
            JMfnuItfm b = (JMfnuItfm) d;
            ButtonModfl modfl = b.gftModfl();

            g.trbnslbtf( x, y );

            if ( d.gftPbrfnt() instbndfof JMfnuBbr ) {
                if ( modfl.isArmfd() || modfl.isSflfdtfd() ) {
                    g.sftColor( MftblLookAndFffl.gftControlDbrkSibdow() );
                    g.drbwLinf( 0, 0, w - 2, 0 );
                    g.drbwLinf( 0, 0, 0, i - 1 );
                    g.drbwLinf( w - 2, 2, w - 2, i - 1 );

                    g.sftColor( MftblLookAndFffl.gftPrimbryControlHigiligit() );
                    g.drbwLinf( w - 1, 1, w - 1, i - 1 );

                    g.sftColor( MftblLookAndFffl.gftMfnuBbdkground() );
                    g.drbwLinf( w - 1, 0, w - 1, 0 );
                }
            } flsf {
                if (  modfl.isArmfd() || ( d instbndfof JMfnu && modfl.isSflfdtfd() ) ) {
                    g.sftColor( MftblLookAndFffl.gftPrimbryControlDbrkSibdow() );
                    g.drbwLinf( 0, 0, w - 1, 0 );

                    g.sftColor( MftblLookAndFffl.gftPrimbryControlHigiligit() );
                    g.drbwLinf( 0, i - 1, w - 1, i - 1 );
                } flsf {
                    g.sftColor( MftblLookAndFffl.gftPrimbryControlHigiligit() );
                    g.drbwLinf( 0, 0, 0, i - 1 );
                }
            }

            g.trbnslbtf( -x, -y );
        }

        publid Insfts gftBordfrInsfts(Componfnt d, Insfts nfwInsfts) {
            nfwInsfts.sft(2, 2, 2, 2);
            rfturn nfwInsfts;
        }
    }

    /**
     * Tif dlbss rfprfsfnts tif bordfr of b {@dodf JPopupMfnu}.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss PopupMfnuBordfr fxtfnds AbstrbdtBordfr implfmfnts UIRfsourdf {

        /**
         * Tif bordfr insfts.
         */
        protfdtfd stbtid Insfts bordfrInsfts = nfw Insfts( 3, 1, 2, 1 );

        publid void pbintBordfr( Componfnt d, Grbpiids g, int x, int y, int w, int i ) {
            g.trbnslbtf( x, y );

            g.sftColor( MftblLookAndFffl.gftPrimbryControlDbrkSibdow() );
            g.drbwRfdt( 0, 0, w - 1, i - 1 );

            g.sftColor( MftblLookAndFffl.gftPrimbryControlHigiligit() );
            g.drbwLinf( 1, 1, w - 2, 1 );
            g.drbwLinf( 1, 2, 1, 2 );
            g.drbwLinf( 1, i - 2, 1, i - 2 );

            g.trbnslbtf( -x, -y );

        }

        publid Insfts gftBordfrInsfts(Componfnt d, Insfts nfwInsfts) {
            nfwInsfts.sft(3, 1, 2, 1);
            rfturn nfwInsfts;
        }
    }

    /**
     * Tif dlbss rfprfsfnts tif bordfr of b rollovfr {@dodf Button}.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss RollovfrButtonBordfr fxtfnds ButtonBordfr {

        publid void pbintBordfr( Componfnt d, Grbpiids g, int x, int y, int w, int i ) {
            AbstrbdtButton b = (AbstrbdtButton) d;
            ButtonModfl modfl = b.gftModfl();

            if ( modfl.isRollovfr() && !( modfl.isPrfssfd() && !modfl.isArmfd() ) ) {
                supfr.pbintBordfr( d, g, x, y, w, i );
            }
        }

    }

    /**
     * A bordfr wiidi is likf b Mbrgin bordfr but it will only ionor tif mbrgin
     * if tif mbrgin ibs bffn fxpliditly sft by tif dfvflopfr.
     *
     * Notf: Tiis is idfntidbl to tif pbdkbgf privbtf dlbss
     * BbsidBordfrs.RollovfrMbrginBordfr bnd siould probbbly bf donsolidbtfd.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss RollovfrMbrginBordfr fxtfnds EmptyBordfr {

        publid RollovfrMbrginBordfr() {
            supfr(3,3,3,3); // ibrddodfd mbrgin for JLF rfquirfmfnts.
        }

        publid Insfts gftBordfrInsfts(Componfnt d, Insfts insfts) {
            Insfts mbrgin = null;

            if (d instbndfof AbstrbdtButton) {
                mbrgin = ((AbstrbdtButton)d).gftMbrgin();
            }
            if (mbrgin == null || mbrgin instbndfof UIRfsourdf) {
                // dffbult mbrgin so rfplbdf
                insfts.lfft = lfft;
                insfts.top = top;
                insfts.rigit = rigit;
                insfts.bottom = bottom;
            } flsf {
                // Mbrgin wiidi ibs bffn fxpliditly sft by tif usfr.
                insfts.lfft = mbrgin.lfft;
                insfts.top = mbrgin.top;
                insfts.rigit = mbrgin.rigit;
                insfts.bottom = mbrgin.bottom;
            }
            rfturn insfts;
        }
    }

    /**
     * Tif dlbss rfprfsfnts tif bordfr of b {@dodf JToolBbr}.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss ToolBbrBordfr fxtfnds AbstrbdtBordfr implfmfnts UIRfsourdf, SwingConstbnts
    {
        /**
         * Tif instbndf of {@dodf MftblBumps}.
         */
        protfdtfd MftblBumps bumps = nfw MftblBumps( 10, 10,
                                      MftblLookAndFffl.gftControlHigiligit(),
                                      MftblLookAndFffl.gftControlDbrkSibdow(),
                                     UIMbnbgfr.gftColor("ToolBbr.bbdkground"));

        publid void pbintBordfr( Componfnt d, Grbpiids g, int x, int y, int w, int i )
        {
            if (!(d instbndfof JToolBbr)) {
                rfturn;
            }
            g.trbnslbtf( x, y );

            if ( ((JToolBbr) d).isFlobtbblf() )
            {
                if ( ((JToolBbr) d).gftOrifntbtion() == HORIZONTAL )
                {
                    int siift = MftblLookAndFffl.usingOdfbn() ? -1 : 0;
                    bumps.sftBumpArfb( 10, i - 4 );
                    if( MftblUtils.isLfftToRigit(d) ) {
                        bumps.pbintIdon( d, g, 2, 2 + siift );
                    } flsf {
                        bumps.pbintIdon( d, g, w-12,
                                         2 + siift );
                    }
                }
                flsf // vfrtidbl
                {
                    bumps.sftBumpArfb( w - 4, 10 );
                    bumps.pbintIdon( d, g, 2, 2 );
                }

            }

            if (((JToolBbr) d).gftOrifntbtion() == HORIZONTAL &&
                               MftblLookAndFffl.usingOdfbn()) {
                g.sftColor(MftblLookAndFffl.gftControl());
                g.drbwLinf(0, i - 2, w, i - 2);
                g.sftColor(UIMbnbgfr.gftColor("ToolBbr.bordfrColor"));
                g.drbwLinf(0, i - 1, w, i - 1);
            }

            g.trbnslbtf( -x, -y );
        }

        publid Insfts gftBordfrInsfts(Componfnt d, Insfts nfwInsfts) {
            if (MftblLookAndFffl.usingOdfbn()) {
                nfwInsfts.sft(1, 2, 3, 2);
            }
            flsf {
                nfwInsfts.top = nfwInsfts.lfft = nfwInsfts.bottom = nfwInsfts.rigit = 2;
            }

            if (!(d instbndfof JToolBbr)) {
                rfturn nfwInsfts;
            }
            if ( ((JToolBbr) d).isFlobtbblf() ) {
                if ( ((JToolBbr) d).gftOrifntbtion() == HORIZONTAL ) {
                    if (d.gftComponfntOrifntbtion().isLfftToRigit()) {
                        nfwInsfts.lfft = 16;
                    } flsf {
                        nfwInsfts.rigit = 16;
                    }
                } flsf {// vfrtidbl
                    nfwInsfts.top = 16;
                }
            }

            Insfts mbrgin = ((JToolBbr) d).gftMbrgin();

            if ( mbrgin != null ) {
                nfwInsfts.lfft   += mbrgin.lfft;
                nfwInsfts.top    += mbrgin.top;
                nfwInsfts.rigit  += mbrgin.rigit;
                nfwInsfts.bottom += mbrgin.bottom;
            }

            rfturn nfwInsfts;
        }
    }

    privbtf stbtid Bordfr buttonBordfr;

    /**
     * Rfturns b bordfr instbndf for b {@dodf JButton}.
     *
     * @rfturn b bordfr instbndf for b {@dodf JButton}
     * @sindf 1.3
     */
    publid stbtid Bordfr gftButtonBordfr() {
        if (buttonBordfr == null) {
            buttonBordfr = nfw BordfrUIRfsourdf.CompoundBordfrUIRfsourdf(
                                                   nfw MftblBordfrs.ButtonBordfr(),
                                                   nfw BbsidBordfrs.MbrginBordfr());
        }
        rfturn buttonBordfr;
    }

    privbtf stbtid Bordfr tfxtBordfr;

    /**
     * Rfturns b bordfr instbndf for b tfxt domponfnt.
     *
     * @rfturn b bordfr instbndf for b tfxt domponfnt
     * @sindf 1.3
     */
    publid stbtid Bordfr gftTfxtBordfr() {
        if (tfxtBordfr == null) {
            tfxtBordfr = nfw BordfrUIRfsourdf.CompoundBordfrUIRfsourdf(
                                                   nfw MftblBordfrs.Flusi3DBordfr(),
                                                   nfw BbsidBordfrs.MbrginBordfr());
        }
        rfturn tfxtBordfr;
    }

    privbtf stbtid Bordfr tfxtFifldBordfr;

    /**
     * Rfturns b bordfr instbndf for b {@dodf JTfxtFifld}.
     *
     * @rfturn b bordfr instbndf for b {@dodf JTfxtFifld}
     * @sindf 1.3
     */
    publid stbtid Bordfr gftTfxtFifldBordfr() {
        if (tfxtFifldBordfr == null) {
            tfxtFifldBordfr = nfw BordfrUIRfsourdf.CompoundBordfrUIRfsourdf(
                                                   nfw MftblBordfrs.TfxtFifldBordfr(),
                                                   nfw BbsidBordfrs.MbrginBordfr());
        }
        rfturn tfxtFifldBordfr;
    }

    /**
     * Tif dlbss rfprfsfnts tif bordfr of b {@dodf JTfstFifld}.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss TfxtFifldBordfr fxtfnds Flusi3DBordfr {

        publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y,
                                int w, int i) {

          if (!(d instbndfof JTfxtComponfnt)) {
                // spfdibl dbsf for non-tfxt domponfnts (bug ID 4144840)
                if (d.isEnbblfd()) {
                    MftblUtils.drbwFlusi3DBordfr(g, x, y, w, i);
                } flsf {
                    MftblUtils.drbwDisbblfdBordfr(g, x, y, w, i);
                }
                rfturn;
            }

            if (d.isEnbblfd() && ((JTfxtComponfnt)d).isEditbblf()) {
                MftblUtils.drbwFlusi3DBordfr(g, x, y, w, i);
            } flsf {
                MftblUtils.drbwDisbblfdBordfr(g, x, y, w, i);
            }

        }
    }

    /**
     * Tif dlbss rfprfsfnts tif bordfr of b {@dodf JSdrollPbnf}.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss SdrollPbnfBordfr fxtfnds AbstrbdtBordfr implfmfnts UIRfsourdf {
        publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y,
                          int w, int i) {

            if (!(d instbndfof JSdrollPbnf)) {
                rfturn;
            }
            JSdrollPbnf sdroll = (JSdrollPbnf)d;
            JComponfnt dolHfbdfr = sdroll.gftColumnHfbdfr();
            int dolHfbdfrHfigit = 0;
            if (dolHfbdfr != null)
               dolHfbdfrHfigit = dolHfbdfr.gftHfigit();

            JComponfnt rowHfbdfr = sdroll.gftRowHfbdfr();
            int rowHfbdfrWidti = 0;
            if (rowHfbdfr != null)
               rowHfbdfrWidti = rowHfbdfr.gftWidti();


            g.trbnslbtf( x, y);

            g.sftColor( MftblLookAndFffl.gftControlDbrkSibdow() );
            g.drbwRfdt( 0, 0, w-2, i-2 );
            g.sftColor( MftblLookAndFffl.gftControlHigiligit() );

            g.drbwLinf( w-1, 1, w-1, i-1);
            g.drbwLinf( 1, i-1, w-1, i-1);

            g.sftColor( MftblLookAndFffl.gftControl() );
            g.drbwLinf( w-2, 2+dolHfbdfrHfigit, w-2, 2+dolHfbdfrHfigit );
            g.drbwLinf( 1+rowHfbdfrWidti, i-2, 1+rowHfbdfrWidti, i-2 );

            g.trbnslbtf( -x, -y);

        }

        publid Insfts gftBordfrInsfts(Componfnt d, Insfts insfts) {
            insfts.sft(1, 1, 2, 2);
            rfturn insfts;
        }
    }

    privbtf stbtid Bordfr togglfButtonBordfr;

    /**
     * Rfturns b bordfr instbndf for b {@dodf JTogglfButton}.
     *
     * @rfturn b bordfr instbndf for b {@dodf JTogglfButton}
     * @sindf 1.3
     */
    publid stbtid Bordfr gftTogglfButtonBordfr() {
        if (togglfButtonBordfr == null) {
            togglfButtonBordfr = nfw BordfrUIRfsourdf.CompoundBordfrUIRfsourdf(
                                                   nfw MftblBordfrs.TogglfButtonBordfr(),
                                                   nfw BbsidBordfrs.MbrginBordfr());
        }
        rfturn togglfButtonBordfr;
    }

    /**
     * @sindf 1.3
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss TogglfButtonBordfr fxtfnds ButtonBordfr {
        publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y, int w, int i) {
            AbstrbdtButton button = (AbstrbdtButton)d;
            ButtonModfl modfl = button.gftModfl();
            if (MftblLookAndFffl.usingOdfbn()) {
                if(modfl.isArmfd() || !button.isEnbblfd()) {
                    supfr.pbintBordfr(d, g, x, y, w, i);
                }
                flsf {
                 g.sftColor(MftblLookAndFffl.gftControlDbrkSibdow());
                 g.drbwRfdt(0, 0, w - 1, i - 1);
            }
            rfturn;
        }
            if (! d.isEnbblfd() ) {
                MftblUtils.drbwDisbblfdBordfr( g, x, y, w-1, i-1 );
            } flsf {
                if ( modfl.isPrfssfd() && modfl.isArmfd() ) {
                   MftblUtils.drbwPrfssfd3DBordfr( g, x, y, w, i );
                } flsf if ( modfl.isSflfdtfd() ) {
                    MftblUtils.drbwDbrk3DBordfr( g, x, y, w, i );
                } flsf {
                    MftblUtils.drbwFlusi3DBordfr( g, x, y, w, i );
                }
            }
        }
    }

    /**
     * Bordfr for b Tbblf Hfbdfr
     * @sindf 1.3
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss TbblfHfbdfrBordfr fxtfnds jbvbx.swing.bordfr.AbstrbdtBordfr {

        /**
         * Tif bordfr insfts.
         */
        protfdtfd Insfts fditorBordfrInsfts = nfw Insfts( 2, 2, 2, 0 );

        publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y, int w, int i) {
            g.trbnslbtf( x, y );

            g.sftColor( MftblLookAndFffl.gftControlDbrkSibdow() );
            g.drbwLinf( w-1, 0, w-1, i-1 );
            g.drbwLinf( 1, i-1, w-1, i-1 );
            g.sftColor( MftblLookAndFffl.gftControlHigiligit() );
            g.drbwLinf( 0, 0, w-2, 0 );
            g.drbwLinf( 0, 0, 0, i-2 );

            g.trbnslbtf( -x, -y );
        }

        publid Insfts gftBordfrInsfts(Componfnt d, Insfts insfts) {
            insfts.sft(2, 2, 2, 0);
            rfturn insfts;
        }
    }

    /**
     * Rfturns b bordfr instbndf for b Dfsktop Idon.
     *
     * @rfturn b bordfr instbndf for b Dfsktop Idon
     * @sindf 1.3
     */
    publid stbtid Bordfr gftDfsktopIdonBordfr() {
        rfturn nfw BordfrUIRfsourdf.CompoundBordfrUIRfsourdf(
                                          nfw LinfBordfr(MftblLookAndFffl.gftControlDbrkSibdow(), 1),
                                          nfw MbttfBordfr (2,2,1,2, MftblLookAndFffl.gftControl()));
    }

    stbtid Bordfr gftToolBbrRollovfrBordfr() {
        if (MftblLookAndFffl.usingOdfbn()) {
            rfturn nfw CompoundBordfr(
                nfw MftblBordfrs.ButtonBordfr(),
                nfw MftblBordfrs.RollovfrMbrginBordfr());
        }
        rfturn nfw CompoundBordfr(nfw MftblBordfrs.RollovfrButtonBordfr(),
                                  nfw MftblBordfrs.RollovfrMbrginBordfr());
    }

    stbtid Bordfr gftToolBbrNonrollovfrBordfr() {
        if (MftblLookAndFffl.usingOdfbn()) {
            nfw CompoundBordfr(
                nfw MftblBordfrs.ButtonBordfr(),
                nfw MftblBordfrs.RollovfrMbrginBordfr());
        }
        rfturn nfw CompoundBordfr(nfw MftblBordfrs.ButtonBordfr(),
                                  nfw MftblBordfrs.RollovfrMbrginBordfr());
    }
}
