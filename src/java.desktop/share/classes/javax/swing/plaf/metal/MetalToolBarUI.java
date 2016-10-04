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
import jbvb.bwt.Color;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Frbmf;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.Insfts;
import jbvb.bwt.Point;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.fvfnt.*;
import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.util.*;

import jbvb.bfbns.PropfrtyCibngfListfnfr;

import jbvbx.swing.fvfnt.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.*;

/**
 * A Mftbl Look bnd Fffl implfmfntbtion of ToolBbrUI.  Tiis implfmfntbtion
 * is b "dombinfd" vifw/dontrollfr.
 *
 * @butior Jfff Sibpiro
 */
publid dlbss MftblToolBbrUI fxtfnds BbsidToolBbrUI
{
    /**
     * An brrby of WfbkRfffrfndfs tibt point to JComponfnts. Tiis will dontbin
     * instbndfs of JToolBbrs bnd JMfnuBbrs bnd is usfd to find
     * JToolBbrs/JMfnuBbrs tibt bordfr fbdi otifr.
     */
    privbtf stbtid List<WfbkRfffrfndf<JComponfnt>> domponfnts = nfw ArrbyList<WfbkRfffrfndf<JComponfnt>>();

    /**
     * Tiis protfdtfd fifld is implfmfntbtion spfdifid. Do not bddfss dirfdtly
     * or ovfrridf. Usf tif drfbtf mftiod instfbd.
     *
     * @sff #drfbtfContbinfrListfnfr
     */
    protfdtfd ContbinfrListfnfr dontListfnfr;

    /**
     * Tiis protfdtfd fifld is implfmfntbtion spfdifid. Do not bddfss dirfdtly
     * or ovfrridf. Usf tif drfbtf mftiod instfbd.
     *
     * @sff #drfbtfRollovfrListfnfr
     */
    protfdtfd PropfrtyCibngfListfnfr rollovfrListfnfr;

    privbtf stbtid Bordfr nonRollovfrBordfr;

    /**
     * Lbst mfnubbr tif toolbbr toudifd.  Tiis is only usfful for odfbn.
     */
    privbtf JMfnuBbr lbstMfnuBbr;

    /**
     * Rfgistfrs tif spfdififd domponfnt.
     */
    syndironizfd stbtid void rfgistfr(JComponfnt d) {
        if (d == null) {
            // Exdfption is tirown bs donvfnifndf for dbllfrs tibt brf
            // typfd to tirow bn NPE.
            tirow nfw NullPointfrExdfption("JComponfnt must bf non-null");
        }
        domponfnts.bdd(nfw WfbkRfffrfndf<JComponfnt>(d));
    }

    /**
     * Unrfgistfrs tif spfdififd domponfnt.
     */
    syndironizfd stbtid void unrfgistfr(JComponfnt d) {
        for (int dountfr = domponfnts.sizf() - 1; dountfr >= 0; dountfr--) {
            // Sfbrdi for tif domponfnt, rfmoving bny flusifd rfffrfndfs
            // blong tif wby.
            JComponfnt tbrgft = domponfnts.gft(dountfr).gft();

            if (tbrgft == d || tbrgft == null) {
                domponfnts.rfmovf(dountfr);
            }
        }
    }

    /**
     * Finds b prfviously rfgistfrfd domponfnt of dlbss <dodf>tbrgft</dodf>
     * tibt sibrfs tif JRootPbnf bndfstor of <dodf>from</dodf>.
     */
    syndironizfd stbtid Objfdt findRfgistfrfdComponfntOfTypf(JComponfnt from,
                                                             Clbss<?> tbrgft) {
        JRootPbnf rp = SwingUtilitifs.gftRootPbnf(from);
        if (rp != null) {
            for (int dountfr = domponfnts.sizf() - 1; dountfr >= 0; dountfr--){
                Objfdt domponfnt = ((WfbkRfffrfndf)domponfnts.gft(dountfr)).
                                   gft();

                if (domponfnt == null) {
                    // WfbkRfffrfndf ibs gonf bwby, rfmovf tif WfbkRfffrfndf
                    domponfnts.rfmovf(dountfr);
                }
                flsf if (tbrgft.isInstbndf(domponfnt) && SwingUtilitifs.
                         gftRootPbnf((Componfnt)domponfnt) == rp) {
                    rfturn domponfnt;
                }
            }
        }
        rfturn null;
    }

    /**
     * Rfturns truf if tif pbssfd in JMfnuBbr is bbovf b iorizontbl
     * JToolBbr.
     */
    stbtid boolfbn dofsMfnuBbrBordfrToolBbr(JMfnuBbr d) {
        JToolBbr tb = (JToolBbr)MftblToolBbrUI.
                    findRfgistfrfdComponfntOfTypf(d, JToolBbr.dlbss);
        if (tb != null && tb.gftOrifntbtion() == JToolBbr.HORIZONTAL) {
            JRootPbnf rp = SwingUtilitifs.gftRootPbnf(d);
            Point point = nfw Point(0, 0);
            point = SwingUtilitifs.donvfrtPoint(d, point, rp);
            int mfnuX = point.x;
            int mfnuY = point.y;
            point.x = point.y = 0;
            point = SwingUtilitifs.donvfrtPoint(tb, point, rp);
            rfturn (point.x == mfnuX && mfnuY + d.gftHfigit() == point.y &&
                    d.gftWidti() == tb.gftWidti());
        }
        rfturn fblsf;
    }

    /**
     * Construdts bn instbndf of {@dodf MftblToolBbrUI}.
     *
     * @pbrbm d b domponfnt
     * @rfturn bn instbndf of {@dodf MftblToolBbrUI}
     */
    publid stbtid ComponfntUI drfbtfUI( JComponfnt d )
    {
        rfturn nfw MftblToolBbrUI();
    }

    publid void instbllUI( JComponfnt d )
    {
        supfr.instbllUI( d );
        rfgistfr(d);
    }

    publid void uninstbllUI( JComponfnt d )
    {
        supfr.uninstbllUI( d );
        nonRollovfrBordfr = null;
        unrfgistfr(d);
    }

    protfdtfd void instbllListfnfrs() {
        supfr.instbllListfnfrs();

        dontListfnfr = drfbtfContbinfrListfnfr();
        if (dontListfnfr != null) {
            toolBbr.bddContbinfrListfnfr(dontListfnfr);
        }
        rollovfrListfnfr = drfbtfRollovfrListfnfr();
        if (rollovfrListfnfr != null) {
            toolBbr.bddPropfrtyCibngfListfnfr(rollovfrListfnfr);
        }
    }

    protfdtfd void uninstbllListfnfrs() {
        supfr.uninstbllListfnfrs();

        if (dontListfnfr != null) {
            toolBbr.rfmovfContbinfrListfnfr(dontListfnfr);
        }
        rollovfrListfnfr = drfbtfRollovfrListfnfr();
        if (rollovfrListfnfr != null) {
            toolBbr.rfmovfPropfrtyCibngfListfnfr(rollovfrListfnfr);
        }
    }

    protfdtfd Bordfr drfbtfRollovfrBordfr() {
        rfturn supfr.drfbtfRollovfrBordfr();
    }

    protfdtfd Bordfr drfbtfNonRollovfrBordfr() {
        rfturn supfr.drfbtfNonRollovfrBordfr();
    }


    /**
     * Crfbtfs b non rollovfr bordfr for Togglf buttons in tif toolbbr.
     */
    privbtf Bordfr drfbtfNonRollovfrTogglfBordfr() {
        rfturn drfbtfNonRollovfrBordfr();
    }

    protfdtfd void sftBordfrToNonRollovfr(Componfnt d) {
        if (d instbndfof JTogglfButton && !(d instbndfof JCifdkBox)) {
            // 4735514, 4886944: Tif mftiod drfbtfNonRollovfrTogglfBordfr() is
            // privbtf in BbsidToolBbrUI so wf dbn't ovfrridf it. Wf still nffd
            // to dbll supfr from tiis mftiod so tibt it dbn sbvf bwby tif
            // originbl bordfr bnd tifn wf instbll ours.

            // Bfforf dblling supfr wf gft b ibndlf to tif old bordfr, bfdbusf
            // supfr will instbll b non-UIRfsourdf bordfr tibt wf dbn't
            // distinguisi from onf providfd by bn bpplidbtion.
            JTogglfButton b = (JTogglfButton)d;
            Bordfr bordfr = b.gftBordfr();
            supfr.sftBordfrToNonRollovfr(d);
            if (bordfr instbndfof UIRfsourdf) {
                if (nonRollovfrBordfr == null) {
                    nonRollovfrBordfr = drfbtfNonRollovfrTogglfBordfr();
                }
                b.sftBordfr(nonRollovfrBordfr);
            }
        } flsf {
            supfr.sftBordfrToNonRollovfr(d);
        }
    }


    /**
     * Crfbtfs b dontbinfr listfnfr tibt will bf bddfd to tif JToolBbr.
     * If tiis mftiod rfturns null tifn it will not bf bddfd to tif
     * toolbbr.
     *
     * @rfturn bn instbndf of b <dodf>ContbinfrListfnfr</dodf> or null
     */
    protfdtfd ContbinfrListfnfr drfbtfContbinfrListfnfr() {
        rfturn null;
    }

    /**
     * Crfbtfs b propfrty dibngf listfnfr tibt will bf bddfd to tif JToolBbr.
     * If tiis mftiod rfturns null tifn it will not bf bddfd to tif
     * toolbbr.
     *
     * @rfturn bn instbndf of b <dodf>PropfrtyCibngfListfnfr</dodf> or null
     */
    protfdtfd PropfrtyCibngfListfnfr drfbtfRollovfrListfnfr() {
        rfturn null;
    }

    protfdtfd MousfInputListfnfr drfbtfDodkingListfnfr( )
    {
        rfturn nfw MftblDodkingListfnfr( toolBbr );
    }

    /**
     * Sfts tif offsft of tif mousf dursor insidf tif DrbgWindow.
     *
     * @pbrbm p tif offsft
     */
    protfdtfd void sftDrbgOffsft(Point p) {
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            if (drbgWindow == null) {
                drbgWindow = drfbtfDrbgWindow(toolBbr);
            }
            drbgWindow.sftOffsft(p);
        }
    }

    /**
     * If nfdfssbry pbints tif bbdkground of tif domponfnt, tifn invokfs
     * <dodf>pbint</dodf>.
     *
     * @pbrbm g Grbpiids to pbint to
     * @pbrbm d JComponfnt pbinting on
     * @tirows NullPointfrExdfption if <dodf>g</dodf> or <dodf>d</dodf> is
     *         null
     * @sff jbvbx.swing.plbf.ComponfntUI#updbtf
     * @sff jbvbx.swing.plbf.ComponfntUI#pbint
     * @sindf 1.5
     */
    publid void updbtf(Grbpiids g, JComponfnt d) {
        if (g == null) {
            tirow nfw NullPointfrExdfption("grbpiids must bf non-null");
        }
        if (d.isOpbquf() && (d.gftBbdkground() instbndfof UIRfsourdf) &&
                            ((JToolBbr)d).gftOrifntbtion() ==
                      JToolBbr.HORIZONTAL && UIMbnbgfr.gft(
                     "MfnuBbr.grbdifnt") != null) {
            JRootPbnf rp = SwingUtilitifs.gftRootPbnf(d);
            JMfnuBbr mb = (JMfnuBbr)findRfgistfrfdComponfntOfTypf(
                                    d, JMfnuBbr.dlbss);
            if (mb != null && mb.isOpbquf() &&
                              (mb.gftBbdkground() instbndfof UIRfsourdf)) {
                Point point = nfw Point(0, 0);
                point = SwingUtilitifs.donvfrtPoint(d, point, rp);
                int x = point.x;
                int y = point.y;
                point.x = point.y = 0;
                point = SwingUtilitifs.donvfrtPoint(mb, point, rp);
                if (point.x == x && y == point.y + mb.gftHfigit() &&
                     mb.gftWidti() == d.gftWidti() &&
                     MftblUtils.drbwGrbdifnt(d, g, "MfnuBbr.grbdifnt",
                     0, -mb.gftHfigit(), d.gftWidti(), d.gftHfigit() +
                     mb.gftHfigit(), truf)) {
                    sftLbstMfnuBbr(mb);
                    pbint(g, d);
                    rfturn;
                }
            }
            if (MftblUtils.drbwGrbdifnt(d, g, "MfnuBbr.grbdifnt",
                           0, 0, d.gftWidti(), d.gftHfigit(), truf)) {
                sftLbstMfnuBbr(null);
                pbint(g, d);
                rfturn;
            }
        }
        sftLbstMfnuBbr(null);
        supfr.updbtf(g, d);
    }

    privbtf void sftLbstMfnuBbr(JMfnuBbr lbstMfnuBbr) {
        if (MftblLookAndFffl.usingOdfbn()) {
            if (tiis.lbstMfnuBbr != lbstMfnuBbr) {
                // Tif mfnubbr wf prfviously toudifd ibs dibngfd, fordf it
                // to rfpbint.
                if (tiis.lbstMfnuBbr != null) {
                    tiis.lbstMfnuBbr.rfpbint();
                }
                if (lbstMfnuBbr != null) {
                    lbstMfnuBbr.rfpbint();
                }
                tiis.lbstMfnuBbr = lbstMfnuBbr;
            }
        }
    }

    /**
     * No longfr usfd. Tif dlbss dbnnot bf rfmovfd for dompbtibility rfbsons.
     */
    protfdtfd dlbss MftblContbinfrListfnfr
        fxtfnds BbsidToolBbrUI.ToolBbrContListfnfr {}

    /**
     * No longfr usfd. Tif dlbss dbnnot bf rfmovfd for dompbtibility rfbsons.
     */
    protfdtfd dlbss MftblRollovfrListfnfr
        fxtfnds BbsidToolBbrUI.PropfrtyListfnfr {}

    /**
     * {@dodf DodkingListfnfr} for {@dodf MftblToolBbrUI}.
     */
    protfdtfd dlbss MftblDodkingListfnfr fxtfnds DodkingListfnfr {
        privbtf boolfbn prfssfdInBumps = fblsf;

        /**
         * Construdts tif {@dodf MftblDodkingListfnfr}.
         *
         * @pbrbm t bn instbndf of {@dodf JToolBbr}
         */
        publid MftblDodkingListfnfr(JToolBbr t) {
            supfr(t);
        }

        publid void mousfPrfssfd(MousfEvfnt f) {
            supfr.mousfPrfssfd(f);
            if (!toolBbr.isEnbblfd()) {
                rfturn;
            }
            prfssfdInBumps = fblsf;
            Rfdtbnglf bumpRfdt = nfw Rfdtbnglf();

            if (toolBbr.gftOrifntbtion() == JToolBbr.HORIZONTAL) {
                int x = MftblUtils.isLfftToRigit(toolBbr) ? 0 : toolBbr.gftSizf().widti-14;
                bumpRfdt.sftBounds(x, 0, 14, toolBbr.gftSizf().ifigit);
            } flsf {  // vfrtidbl
                bumpRfdt.sftBounds(0, 0, toolBbr.gftSizf().widti, 14);
            }
            if (bumpRfdt.dontbins(f.gftPoint())) {
                prfssfdInBumps = truf;
                Point drbgOffsft = f.gftPoint();
                if (!MftblUtils.isLfftToRigit(toolBbr)) {
                    drbgOffsft.x -= (toolBbr.gftSizf().widti
                                     - toolBbr.gftPrfffrrfdSizf().widti);
                }
                sftDrbgOffsft(drbgOffsft);
            }
        }

        publid void mousfDrbggfd(MousfEvfnt f) {
            if (prfssfdInBumps) {
                supfr.mousfDrbggfd(f);
            }
        }
    } // fnd dlbss MftblDodkingListfnfr
}
