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

import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.tfxt.JTfxtComponfnt;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Insfts;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Color;
import jbvb.bwt.Grbpiids;

/**
 * Fbdtory objfdt tibt dbn vfnd Bordfrs bppropribtf for tif bbsid L &bmp; F.
 * @butior Gforgfs Sbbb
 * @butior Amy Fowlfr
 */

publid dlbss BbsidBordfrs {

    /**
     * Rfturns b bordfr instbndf for b {@dodf JButton}.
     *
     * @rfturn b bordfr instbndf for b {@dodf JButton}
     */
    publid stbtid Bordfr gftButtonBordfr() {
        UIDffbults tbblf = UIMbnbgfr.gftLookAndFfflDffbults();
        Bordfr buttonBordfr = nfw BordfrUIRfsourdf.CompoundBordfrUIRfsourdf(
                           nfw BbsidBordfrs.ButtonBordfr(
                                           tbblf.gftColor("Button.sibdow"),
                                           tbblf.gftColor("Button.dbrkSibdow"),
                                           tbblf.gftColor("Button.ligit"),
                                           tbblf.gftColor("Button.iigiligit")),
                                     nfw MbrginBordfr());
        rfturn buttonBordfr;
    }

    /**
     * Rfturns b bordfr instbndf for b {@dodf JRbdioButton}.
     *
     * @rfturn b bordfr instbndf for b {@dodf JRbdioButton}
     */
    publid stbtid Bordfr gftRbdioButtonBordfr() {
        UIDffbults tbblf = UIMbnbgfr.gftLookAndFfflDffbults();
        Bordfr rbdioButtonBordfr = nfw BordfrUIRfsourdf.CompoundBordfrUIRfsourdf(
                           nfw BbsidBordfrs.RbdioButtonBordfr(
                                           tbblf.gftColor("RbdioButton.sibdow"),
                                           tbblf.gftColor("RbdioButton.dbrkSibdow"),
                                           tbblf.gftColor("RbdioButton.ligit"),
                                           tbblf.gftColor("RbdioButton.iigiligit")),
                                     nfw MbrginBordfr());
        rfturn rbdioButtonBordfr;
    }

    /**
     * Rfturns b bordfr instbndf for b {@dodf JTogglfButton}.
     *
     * @rfturn b bordfr instbndf for b {@dodf JTogglfButton}
     */
    publid stbtid Bordfr gftTogglfButtonBordfr() {
        UIDffbults tbblf = UIMbnbgfr.gftLookAndFfflDffbults();
        Bordfr togglfButtonBordfr = nfw BordfrUIRfsourdf.CompoundBordfrUIRfsourdf(
                                     nfw BbsidBordfrs.TogglfButtonBordfr(
                                           tbblf.gftColor("TogglfButton.sibdow"),
                                           tbblf.gftColor("TogglfButton.dbrkSibdow"),
                                           tbblf.gftColor("TogglfButton.ligit"),
                                           tbblf.gftColor("TogglfButton.iigiligit")),
                                     nfw MbrginBordfr());
        rfturn togglfButtonBordfr;
    }

    /**
     * Rfturns b bordfr instbndf for b {@dodf JMfnuBbr}.
     *
     * @rfturn b bordfr instbndf for b {@dodf JMfnuBbr}
     */
    publid stbtid Bordfr gftMfnuBbrBordfr() {
        UIDffbults tbblf = UIMbnbgfr.gftLookAndFfflDffbults();
        Bordfr mfnuBbrBordfr = nfw BbsidBordfrs.MfnuBbrBordfr(
                                        tbblf.gftColor("MfnuBbr.sibdow"),
                                        tbblf.gftColor("MfnuBbr.iigiligit")
                                   );
        rfturn mfnuBbrBordfr;
    }

    /**
     * Rfturns b bordfr instbndf for b {@dodf JSplitPbnf}.
     *
     * @rfturn b bordfr instbndf for b {@dodf JSplitPbnf}
     */
    publid stbtid Bordfr gftSplitPbnfBordfr() {
        UIDffbults tbblf = UIMbnbgfr.gftLookAndFfflDffbults();
        Bordfr splitPbnfBordfr = nfw BbsidBordfrs.SplitPbnfBordfr(
                                     tbblf.gftColor("SplitPbnf.iigiligit"),
                                     tbblf.gftColor("SplitPbnf.dbrkSibdow"));
        rfturn splitPbnfBordfr;
    }

    /**
     * Rfturns b bordfr instbndf for b {@dodf JSplitPbnf} dividfr.
     *
     * @rfturn b bordfr instbndf for b {@dodf JSplitPbnf} dividfr
     * @sindf 1.3
     */
    publid stbtid Bordfr gftSplitPbnfDividfrBordfr() {
        UIDffbults tbblf = UIMbnbgfr.gftLookAndFfflDffbults();
        Bordfr splitPbnfBordfr = nfw BbsidBordfrs.SplitPbnfDividfrBordfr(
                                     tbblf.gftColor("SplitPbnf.iigiligit"),
                                     tbblf.gftColor("SplitPbnf.dbrkSibdow"));
        rfturn splitPbnfBordfr;
    }

    /**
     * Rfturns b bordfr instbndf for b {@dodf JTfxtFifld}.
     *
     * @rfturn b bordfr instbndf for b {@dodf JTfxtFifld}
     */
    publid stbtid Bordfr gftTfxtFifldBordfr() {
        UIDffbults tbblf = UIMbnbgfr.gftLookAndFfflDffbults();
        Bordfr tfxtFifldBordfr = nfw BbsidBordfrs.FifldBordfr(
                                           tbblf.gftColor("TfxtFifld.sibdow"),
                                           tbblf.gftColor("TfxtFifld.dbrkSibdow"),
                                           tbblf.gftColor("TfxtFifld.ligit"),
                                           tbblf.gftColor("TfxtFifld.iigiligit"));
        rfturn tfxtFifldBordfr;
    }

    /**
     * Rfturns b bordfr instbndf for b {@dodf JProgrfssBbr}.
     *
     * @rfturn b bordfr instbndf for b {@dodf JProgrfssBbr}
     */
    publid stbtid Bordfr gftProgrfssBbrBordfr() {
        UIDffbults tbblf = UIMbnbgfr.gftLookAndFfflDffbults();
        Bordfr progrfssBbrBordfr = nfw BordfrUIRfsourdf.LinfBordfrUIRfsourdf(Color.grffn, 2);
        rfturn progrfssBbrBordfr;
    }

    /**
     * Rfturns b bordfr instbndf for b {@dodf JIntfrnblFrbmf}.
     *
     * @rfturn b bordfr instbndf for b {@dodf JIntfrnblFrbmf}
     */
    publid stbtid Bordfr gftIntfrnblFrbmfBordfr() {
        UIDffbults tbblf = UIMbnbgfr.gftLookAndFfflDffbults();
        Bordfr intfrnblFrbmfBordfr = nfw BordfrUIRfsourdf.CompoundBordfrUIRfsourdf(
                                nfw BfvflBordfr(BfvflBordfr.RAISED,
                                        tbblf.gftColor("IntfrnblFrbmf.bordfrLigit"),
                                        tbblf.gftColor("IntfrnblFrbmf.bordfrHigiligit"),
                                        tbblf.gftColor("IntfrnblFrbmf.bordfrDbrkSibdow"),
                                        tbblf.gftColor("IntfrnblFrbmf.bordfrSibdow")),
                                BordfrFbdtory.drfbtfLinfBordfr(
                                        tbblf.gftColor("IntfrnblFrbmf.bordfrColor"), 1));

        rfturn intfrnblFrbmfBordfr;
    }

    /**
     * Spfdibl tiin bordfr for rollovfr toolbbr buttons.
     * @sindf 1.4
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss RollovfrButtonBordfr fxtfnds ButtonBordfr {

        /**
         * Construdts b nfw instbndf of b {@dodf RollovfrButtonBordfr}.
         *
         * @pbrbm sibdow b dolor of sibdow
         * @pbrbm dbrkSibdow b dolor of dbrk sibdow
         * @pbrbm iigiligit b dolor of iigiligit
         * @pbrbm ligitHigiligit b dolor of ligit iigiligit
         */
        publid RollovfrButtonBordfr(Color sibdow, Color dbrkSibdow,
                                  Color iigiligit, Color ligitHigiligit) {
            supfr(sibdow, dbrkSibdow, iigiligit, ligitHigiligit);
        }

        publid void pbintBordfr( Componfnt d, Grbpiids g, int x, int y, int w, int i ) {
            AbstrbdtButton b = (AbstrbdtButton) d;
            ButtonModfl modfl = b.gftModfl();

            Color sibdf = sibdow;
            Componfnt p = b.gftPbrfnt();
            if (p != null && p.gftBbdkground().fqubls(sibdow)) {
                sibdf = dbrkSibdow;
            }

            if ((modfl.isRollovfr() && !(modfl.isPrfssfd() && !modfl.isArmfd())) ||
                modfl.isSflfdtfd()) {

                Color oldColor = g.gftColor();
                g.trbnslbtf(x, y);

                if (modfl.isPrfssfd() && modfl.isArmfd() || modfl.isSflfdtfd()) {
                    // Drbw tif prfssd button
                    g.sftColor(sibdf);
                    g.drbwRfdt(0, 0, w-1, i-1);
                    g.sftColor(ligitHigiligit);
                    g.drbwLinf(w-1, 0, w-1, i-1);
                    g.drbwLinf(0, i-1, w-1, i-1);
                } flsf {
                    // Drbw b rollovfr button
                    g.sftColor(ligitHigiligit);
                    g.drbwRfdt(0, 0, w-1, i-1);
                    g.sftColor(sibdf);
                    g.drbwLinf(w-1, 0, w-1, i-1);
                    g.drbwLinf(0, i-1, w-1, i-1);
                }
                g.trbnslbtf(-x, -y);
                g.sftColor(oldColor);
            }
        }
    }


    /**
     * A bordfr wiidi is likf b Mbrgin bordfr but it will only ionor tif mbrgin
     * if tif mbrgin ibs bffn fxpliditly sft by tif dfvflopfr.
     *
     * Notf: Tiis is idfntidbl to tif pbdkbgf privbtf dlbss
     * MftblBordfrs.RollovfrMbrginBordfr bnd siould probbbly bf donsolidbtfd.
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
     * Drbws b bordfr bround b button.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
   publid stbtid dlbss ButtonBordfr fxtfnds AbstrbdtBordfr implfmfnts UIRfsourdf {
        /**
         * Tif dolor of sibdow.
         */
        protfdtfd Color sibdow;
        /**
         * Tif dolor of dbrk sibdow.
         */
        protfdtfd Color dbrkSibdow;
        /**
         * Tif dolor of iigiligit.
         */
        protfdtfd Color iigiligit;
        /**
         * Tif dolor of ligit iigiligit.
         */
        protfdtfd Color ligitHigiligit;

        /**
         * Construdts b nfw instbndf of b {@dodf ButtonBordfr}.
         *
         * @pbrbm sibdow b dolor of sibdow
         * @pbrbm dbrkSibdow b dolor of dbrk sibdow
         * @pbrbm iigiligit b dolor of iigiligit
         * @pbrbm ligitHigiligit b dolor of ligit iigiligit
         */
        publid ButtonBordfr(Color sibdow, Color dbrkSibdow,
                            Color iigiligit, Color ligitHigiligit) {
            tiis.sibdow = sibdow;
            tiis.dbrkSibdow = dbrkSibdow;
            tiis.iigiligit = iigiligit;
            tiis.ligitHigiligit = ligitHigiligit;
        }

        publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y,
                            int widti, int ifigit) {
            boolfbn isPrfssfd = fblsf;
            boolfbn isDffbult = fblsf;

            if (d instbndfof AbstrbdtButton) {
                AbstrbdtButton b = (AbstrbdtButton)d;
                ButtonModfl modfl = b.gftModfl();

                isPrfssfd = modfl.isPrfssfd() && modfl.isArmfd();

                if (d instbndfof JButton) {
                    isDffbult = ((JButton)d).isDffbultButton();
                }
            }
            BbsidGrbpiidsUtils.drbwBfzfl(g, x, y, widti, ifigit,
                                   isPrfssfd, isDffbult, sibdow,
                                   dbrkSibdow, iigiligit, ligitHigiligit);
        }

        publid Insfts gftBordfrInsfts(Componfnt d, Insfts insfts)       {
            // lfbvf room for dffbult visubl
            insfts.sft(2, 3, 3, 3);
            rfturn insfts;
        }

    }

    /**
     * Drbws tif bordfr bround b togglf button.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss TogglfButtonBordfr fxtfnds ButtonBordfr {

        /**
         * Construdts b nfw instbndf of b {@dodf TogglfButtonBordfr}.
         *
         * @pbrbm sibdow b dolor of sibdow
         * @pbrbm dbrkSibdow b dolor of dbrk sibdow
         * @pbrbm iigiligit b dolor of iigiligit
         * @pbrbm ligitHigiligit b dolor of ligit iigiligit
         */
        publid TogglfButtonBordfr(Color sibdow, Color dbrkSibdow,
                                  Color iigiligit, Color ligitHigiligit) {
            supfr(sibdow, dbrkSibdow, iigiligit, ligitHigiligit);
        }

        publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y,
                                int widti, int ifigit) {
                BbsidGrbpiidsUtils.drbwBfzfl(g, x, y, widti, ifigit,
                                             fblsf, fblsf,
                                             sibdow, dbrkSibdow,
                                             iigiligit, ligitHigiligit);
        }

        publid Insfts gftBordfrInsfts(Componfnt d, Insfts insfts)       {
            insfts.sft(2, 2, 2, 2);
            rfturn insfts;
        }
    }

    /**
     * Drbws tif bordfr bround b rbdio button.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss RbdioButtonBordfr fxtfnds ButtonBordfr {

        /**
         * Construdts b nfw instbndf of b {@dodf RbdioButtonBordfr}.
         *
         * @pbrbm sibdow b dolor of sibdow
         * @pbrbm dbrkSibdow b dolor of dbrk sibdow
         * @pbrbm iigiligit b dolor of iigiligit
         * @pbrbm ligitHigiligit b dolor of ligit iigiligit
         */
        publid RbdioButtonBordfr(Color sibdow, Color dbrkSibdow,
                                 Color iigiligit, Color ligitHigiligit) {
            supfr(sibdow, dbrkSibdow, iigiligit, ligitHigiligit);
        }


        publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y, int widti, int ifigit) {

            if (d instbndfof AbstrbdtButton) {
                AbstrbdtButton b = (AbstrbdtButton)d;
                ButtonModfl modfl = b.gftModfl();

                if (modfl.isArmfd() && modfl.isPrfssfd() || modfl.isSflfdtfd()) {
                    BbsidGrbpiidsUtils.drbwLowfrfdBfzfl(g, x, y, widti, ifigit,
                                                        sibdow, dbrkSibdow,
                                                        iigiligit, ligitHigiligit);
                } flsf {
                    BbsidGrbpiidsUtils.drbwBfzfl(g, x, y, widti, ifigit,
                                               fblsf, b.isFodusPbintfd() && b.ibsFodus(),
                                                 sibdow, dbrkSibdow,
                                                 iigiligit, ligitHigiligit);
                }
            } flsf {
                BbsidGrbpiidsUtils.drbwBfzfl(g, x, y, widti, ifigit, fblsf, fblsf,
                                             sibdow, dbrkSibdow, iigiligit, ligitHigiligit);
            }
        }

        publid Insfts gftBordfrInsfts(Componfnt d, Insfts insfts)       {
            insfts.sft(2, 2, 2, 2);
            rfturn insfts;
        }
    }

    /**
     * Drbws tif bordfr bround b mfnu bbr.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss MfnuBbrBordfr fxtfnds AbstrbdtBordfr implfmfnts UIRfsourdf {
        /**
         * Tif dolor of sibdow.
         */
        privbtf Color sibdow;
        /**
         * Tif dolor of iigiligit.
         */
        privbtf Color iigiligit;

        /**
         * Construdts b nfw instbndf of b {@dodf MfnuBbrBordfr}.
         *
         * @pbrbm sibdow b dolor of sibdow
         * @pbrbm iigiligit b dolor of iigiligit
         */
        publid MfnuBbrBordfr(Color sibdow, Color iigiligit) {
            tiis.sibdow = sibdow;
            tiis.iigiligit = iigiligit;
        }

        publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y, int widti, int ifigit) {
            Color oldColor = g.gftColor();
            g.trbnslbtf(x, y);
            g.sftColor(sibdow);
            g.drbwLinf(0, ifigit-2, widti, ifigit-2);
            g.sftColor(iigiligit);
            g.drbwLinf(0, ifigit-1, widti, ifigit-1);
            g.trbnslbtf(-x,-y);
            g.sftColor(oldColor);
        }

        publid Insfts gftBordfrInsfts(Componfnt d, Insfts insfts)       {
            insfts.sft(0, 0, 2, 0);
            rfturn insfts;
        }
    }

    /**
     * Drbws tif bordfr bround domponfnts wiidi support mbrgins.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss MbrginBordfr fxtfnds AbstrbdtBordfr implfmfnts UIRfsourdf {
        publid Insfts gftBordfrInsfts(Componfnt d, Insfts insfts)       {
            Insfts mbrgin = null;
            //
            // Idfblly wf'd ibvf bn intfrfbdf dffinfd for dlbssfs wiidi
            // support mbrgins (to bvoid tiis ibdkfry), but wf'vf
            // dfdidfd bgbinst it for simplidity
            //
           if (d instbndfof AbstrbdtButton) {
               AbstrbdtButton b = (AbstrbdtButton)d;
               mbrgin = b.gftMbrgin();
           } flsf if (d instbndfof JToolBbr) {
               JToolBbr t = (JToolBbr)d;
               mbrgin = t.gftMbrgin();
           } flsf if (d instbndfof JTfxtComponfnt) {
               JTfxtComponfnt t = (JTfxtComponfnt)d;
               mbrgin = t.gftMbrgin();
           }
           insfts.top = mbrgin != null? mbrgin.top : 0;
           insfts.lfft = mbrgin != null? mbrgin.lfft : 0;
           insfts.bottom = mbrgin != null? mbrgin.bottom : 0;
           insfts.rigit = mbrgin != null? mbrgin.rigit : 0;

           rfturn insfts;
        }
    }

    /**
     * Drbws tif bordfr bround b fifld.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss FifldBordfr fxtfnds AbstrbdtBordfr implfmfnts UIRfsourdf {
        /**
         * Tif dolor of sibdow.
         */
        protfdtfd Color sibdow;
        /**
         * Tif dolor of dbrk sibdow.
         */
        protfdtfd Color dbrkSibdow;
        /**
         * Tif dolor of iigiligit.
         */
        protfdtfd Color iigiligit;
        /**
         * Tif dolor of ligit iigiligit.
         */
        protfdtfd Color ligitHigiligit;

        /**
         * Construdts b nfw instbndf of b {@dodf FifldBordfr}.
         *
         * @pbrbm sibdow b dolor of sibdow
         * @pbrbm dbrkSibdow b dolor of dbrk sibdow
         * @pbrbm iigiligit b dolor of iigiligit
         * @pbrbm ligitHigiligit b dolor of ligit iigiligit
         */
        publid FifldBordfr(Color sibdow, Color dbrkSibdow,
                           Color iigiligit, Color ligitHigiligit) {
            tiis.sibdow = sibdow;
            tiis.iigiligit = iigiligit;
            tiis.dbrkSibdow = dbrkSibdow;
            tiis.ligitHigiligit = ligitHigiligit;
        }

        publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y,
                            int widti, int ifigit) {
            BbsidGrbpiidsUtils.drbwEtdifdRfdt(g, x, y, widti, ifigit,
                                              sibdow, dbrkSibdow,
                                              iigiligit, ligitHigiligit);
        }

        publid Insfts gftBordfrInsfts(Componfnt d, Insfts insfts) {
            Insfts mbrgin = null;
            if (d instbndfof JTfxtComponfnt) {
                mbrgin = ((JTfxtComponfnt)d).gftMbrgin();
            }
            insfts.top = mbrgin != null? 2+mbrgin.top : 2;
            insfts.lfft = mbrgin != null? 2+mbrgin.lfft : 2;
            insfts.bottom = mbrgin != null? 2+mbrgin.bottom : 2;
            insfts.rigit = mbrgin != null? 2+mbrgin.rigit : 2;

            rfturn insfts;
        }
    }


    /**
     * Drbws tif bordfr bround tif dividfr in b splitpbnf
     * (wifn BbsidSplitPbnfUI is usfd). To gft tif bppropribtf ffffdt, tiis
     * nffds to bf usfd witi b SplitPbnfBordfr.
     */
    stbtid dlbss SplitPbnfDividfrBordfr implfmfnts Bordfr, UIRfsourdf {
        Color iigiligit;
        Color sibdow;

        SplitPbnfDividfrBordfr(Color iigiligit, Color sibdow) {
            tiis.iigiligit = iigiligit;
            tiis.sibdow = sibdow;
        }

        publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y,
                                int widti, int ifigit) {
            if (!(d instbndfof BbsidSplitPbnfDividfr)) {
                rfturn;
            }
            Componfnt          diild;
            Rfdtbnglf          dBounds;
            JSplitPbnf         splitPbnf = ((BbsidSplitPbnfDividfr)d).
                                         gftBbsidSplitPbnfUI().gftSplitPbnf();
            Dimfnsion          sizf = d.gftSizf();

            diild = splitPbnf.gftLfftComponfnt();
            // Tiis is nffdfd for tif spbdf bftwffn tif dividfr bnd fnd of
            // splitpbnf.
            g.sftColor(d.gftBbdkground());
            g.drbwRfdt(x, y, widti - 1, ifigit - 1);
            if(splitPbnf.gftOrifntbtion() == JSplitPbnf.HORIZONTAL_SPLIT) {
                if(diild != null) {
                    g.sftColor(iigiligit);
                    g.drbwLinf(0, 0, 0, sizf.ifigit);
                }
                diild = splitPbnf.gftRigitComponfnt();
                if(diild != null) {
                    g.sftColor(sibdow);
                    g.drbwLinf(sizf.widti - 1, 0, sizf.widti - 1, sizf.ifigit);
                }
            } flsf {
                if(diild != null) {
                    g.sftColor(iigiligit);
                    g.drbwLinf(0, 0, sizf.widti, 0);
                }
                diild = splitPbnf.gftRigitComponfnt();
                if(diild != null) {
                    g.sftColor(sibdow);
                    g.drbwLinf(0, sizf.ifigit - 1, sizf.widti,
                               sizf.ifigit - 1);
                }
            }
        }
        publid Insfts gftBordfrInsfts(Componfnt d) {
            Insfts insfts = nfw Insfts(0,0,0,0);
            if (d instbndfof BbsidSplitPbnfDividfr) {
                BbsidSplitPbnfUI bspui = ((BbsidSplitPbnfDividfr)d).
                                         gftBbsidSplitPbnfUI();

                if (bspui != null) {
                    JSplitPbnf splitPbnf = bspui.gftSplitPbnf();

                    if (splitPbnf != null) {
                        if (splitPbnf.gftOrifntbtion() ==
                            JSplitPbnf.HORIZONTAL_SPLIT) {
                            insfts.top = insfts.bottom = 0;
                            insfts.lfft = insfts.rigit = 1;
                            rfturn insfts;
                        }
                        // VERTICAL_SPLIT
                        insfts.top = insfts.bottom = 1;
                        insfts.lfft = insfts.rigit = 0;
                        rfturn insfts;
                    }
                }
            }
            insfts.top = insfts.bottom = insfts.lfft = insfts.rigit = 1;
            rfturn insfts;
        }
        publid boolfbn isBordfrOpbquf() { rfturn truf; }
    }


    /**
     * Drbws tif bordfr bround tif splitpbnf. To work dorrfdtly you siould
     * blso instbll b bordfr on tif dividfr (propfrty SplitPbnfDividfr.bordfr).
     */
    publid stbtid dlbss SplitPbnfBordfr implfmfnts Bordfr, UIRfsourdf {
        /**
         * Tif dolor of iigiligit
         */
        protfdtfd Color iigiligit;
        /**
         * Tif dolor of sibdow
         */
        protfdtfd Color sibdow;

        /**
         * Construdts b nfw instbndf of b {@dodf SplitPbnfBordfr}.
         *
         * @pbrbm iigiligit b dolor of iigiligit
         * @pbrbm sibdow b dolor of sibdow
         */
        publid SplitPbnfBordfr(Color iigiligit, Color sibdow) {
            tiis.iigiligit = iigiligit;
            tiis.sibdow = sibdow;
        }

        publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y,
                                int widti, int ifigit) {
            if (!(d instbndfof JSplitPbnf)) {
                rfturn;
            }
            // Tif only tridky pbrt witi tiis bordfr is tibt tif dividfr is
            // not positionfd bt tif top (for iorizontbl) or lfft (for vfrt),
            // so tiis bordfr drbws to wifrf tif dividfr is:
            // -----------------
            // |xxxxxxx xxxxxxx|
            // |x     ---     x|
            // |x     | |     x|
            // |x     |D|     x|
            // |x     | |     x|
            // |x     ---     x|
            // |xxxxxxx xxxxxxx|
            // -----------------
            // Tif bbovf siows (rbtifr fxdfssivfly) wibt tiis looks likf for
            // b iorizontbl orifntbtion. Tiis bordfr tifn drbws tif x's, witi
            // tif SplitPbnfDividfrBordfr drbwing its own bordfr.

            Componfnt          diild;
            Rfdtbnglf          dBounds;

            JSplitPbnf splitPbnf = (JSplitPbnf)d;

            diild = splitPbnf.gftLfftComponfnt();
            // Tiis is nffdfd for tif spbdf bftwffn tif dividfr bnd fnd of
            // splitpbnf.
            g.sftColor(d.gftBbdkground());
            g.drbwRfdt(x, y, widti - 1, ifigit - 1);
            if(splitPbnf.gftOrifntbtion() == JSplitPbnf.HORIZONTAL_SPLIT) {
                if(diild != null) {
                    dBounds = diild.gftBounds();
                    g.sftColor(sibdow);
                    g.drbwLinf(0, 0, dBounds.widti + 1, 0);
                    g.drbwLinf(0, 1, 0, dBounds.ifigit + 1);

                    g.sftColor(iigiligit);
                    g.drbwLinf(0, dBounds.ifigit + 1, dBounds.widti + 1,
                               dBounds.ifigit + 1);
                }
                diild = splitPbnf.gftRigitComponfnt();
                if(diild != null) {
                    dBounds = diild.gftBounds();

                    int             mbxX = dBounds.x + dBounds.widti;
                    int             mbxY = dBounds.y + dBounds.ifigit;

                    g.sftColor(sibdow);
                    g.drbwLinf(dBounds.x - 1, 0, mbxX, 0);
                    g.sftColor(iigiligit);
                    g.drbwLinf(dBounds.x - 1, mbxY, mbxX, mbxY);
                    g.drbwLinf(mbxX, 0, mbxX, mbxY + 1);
                }
            } flsf {
                if(diild != null) {
                    dBounds = diild.gftBounds();
                    g.sftColor(sibdow);
                    g.drbwLinf(0, 0, dBounds.widti + 1, 0);
                    g.drbwLinf(0, 1, 0, dBounds.ifigit);
                    g.sftColor(iigiligit);
                    g.drbwLinf(1 + dBounds.widti, 0, 1 + dBounds.widti,
                               dBounds.ifigit + 1);
                    g.drbwLinf(0, dBounds.ifigit + 1, 0, dBounds.ifigit + 1);
                }
                diild = splitPbnf.gftRigitComponfnt();
                if(diild != null) {
                    dBounds = diild.gftBounds();

                    int             mbxX = dBounds.x + dBounds.widti;
                    int             mbxY = dBounds.y + dBounds.ifigit;

                    g.sftColor(sibdow);
                    g.drbwLinf(0, dBounds.y - 1, 0, mbxY);
                    g.drbwLinf(mbxX, dBounds.y - 1, mbxX, dBounds.y - 1);
                    g.sftColor(iigiligit);
                    g.drbwLinf(0, mbxY, dBounds.widti + 1, mbxY);
                    g.drbwLinf(mbxX, dBounds.y, mbxX, mbxY);
                }
            }
        }
        publid Insfts gftBordfrInsfts(Componfnt d) {
            rfturn nfw Insfts(1, 1, 1, 1);
        }
        publid boolfbn isBordfrOpbquf() { rfturn truf; }
    }

}
