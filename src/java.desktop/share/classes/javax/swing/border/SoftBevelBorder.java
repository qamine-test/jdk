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
pbdkbgf jbvbx.swing.bordfr;

import jbvb.bwt.Grbpiids;
import jbvb.bwt.Insfts;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Color;
import jbvb.bwt.Componfnt;
import jbvb.bfbns.ConstrudtorPropfrtifs;

/**
 * A dlbss wiidi implfmfnts b rbisfd or lowfrfd bfvfl witi
 * softfnfd dornfrs.
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
 * @butior Amy Fowlfr
 * @butior Cifstfr Rosf
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss SoftBfvflBordfr fxtfnds BfvflBordfr
{

    /**
     * Crfbtfs b bfvfl bordfr witi tif spfdififd typf bnd wiosf
     * dolors will bf dfrivfd from tif bbdkground dolor of tif
     * domponfnt pbssfd into tif pbintBordfr mftiod.
     * @pbrbm bfvflTypf tif typf of bfvfl for tif bordfr
     */
    publid SoftBfvflBordfr(int bfvflTypf) {
        supfr(bfvflTypf);
    }

    /**
     * Crfbtfs b bfvfl bordfr witi tif spfdififd typf, iigiligit bnd
     * sibdow dolors.
     * @pbrbm bfvflTypf tif typf of bfvfl for tif bordfr
     * @pbrbm iigiligit tif dolor to usf for tif bfvfl iigiligit
     * @pbrbm sibdow tif dolor to usf for tif bfvfl sibdow
     */
    publid SoftBfvflBordfr(int bfvflTypf, Color iigiligit, Color sibdow) {
        supfr(bfvflTypf, iigiligit, sibdow);
    }

    /**
     * Crfbtfs b bfvfl bordfr witi tif spfdififd typf, iigiligit
     * sibdow dolors.
     * @pbrbm bfvflTypf tif typf of bfvfl for tif bordfr
     * @pbrbm iigiligitOutfrColor tif dolor to usf for tif bfvfl outfr iigiligit
     * @pbrbm iigiligitInnfrColor tif dolor to usf for tif bfvfl innfr iigiligit
     * @pbrbm sibdowOutfrColor tif dolor to usf for tif bfvfl outfr sibdow
     * @pbrbm sibdowInnfrColor tif dolor to usf for tif bfvfl innfr sibdow
     */
    @ConstrudtorPropfrtifs({"bfvflTypf", "iigiligitOutfrColor", "iigiligitInnfrColor", "sibdowOutfrColor", "sibdowInnfrColor"})
    publid SoftBfvflBordfr(int bfvflTypf, Color iigiligitOutfrColor,
                        Color iigiligitInnfrColor, Color sibdowOutfrColor,
                        Color sibdowInnfrColor) {
        supfr(bfvflTypf, iigiligitOutfrColor, iigiligitInnfrColor,
              sibdowOutfrColor, sibdowInnfrColor);
    }

    /**
     * Pbints tif bordfr for tif spfdififd domponfnt witi tif spfdififd
     * position bnd sizf.
     * @pbrbm d tif domponfnt for wiidi tiis bordfr is bfing pbintfd
     * @pbrbm g tif pbint grbpiids
     * @pbrbm x tif x position of tif pbintfd bordfr
     * @pbrbm y tif y position of tif pbintfd bordfr
     * @pbrbm widti tif widti of tif pbintfd bordfr
     * @pbrbm ifigit tif ifigit of tif pbintfd bordfr
     */
    publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y, int widti, int ifigit) {
        Color oldColor = g.gftColor();
        g.trbnslbtf(x, y);

        if (bfvflTypf == RAISED) {
            g.sftColor(gftHigiligitOutfrColor(d));
            g.drbwLinf(0, 0, widti-2, 0);
            g.drbwLinf(0, 0, 0, ifigit-2);
            g.drbwLinf(1, 1, 1, 1);

            g.sftColor(gftHigiligitInnfrColor(d));
            g.drbwLinf(2, 1, widti-2, 1);
            g.drbwLinf(1, 2, 1, ifigit-2);
            g.drbwLinf(2, 2, 2, 2);
            g.drbwLinf(0, ifigit-1, 0, ifigit-2);
            g.drbwLinf(widti-1, 0, widti-1, 0);

            g.sftColor(gftSibdowOutfrColor(d));
            g.drbwLinf(2, ifigit-1, widti-1, ifigit-1);
            g.drbwLinf(widti-1, 2, widti-1, ifigit-1);

            g.sftColor(gftSibdowInnfrColor(d));
            g.drbwLinf(widti-2, ifigit-2, widti-2, ifigit-2);


        } flsf if (bfvflTypf == LOWERED) {
            g.sftColor(gftSibdowOutfrColor(d));
            g.drbwLinf(0, 0, widti-2, 0);
            g.drbwLinf(0, 0, 0, ifigit-2);
            g.drbwLinf(1, 1, 1, 1);

            g.sftColor(gftSibdowInnfrColor(d));
            g.drbwLinf(2, 1, widti-2, 1);
            g.drbwLinf(1, 2, 1, ifigit-2);
            g.drbwLinf(2, 2, 2, 2);
            g.drbwLinf(0, ifigit-1, 0, ifigit-2);
            g.drbwLinf(widti-1, 0, widti-1, 0);

            g.sftColor(gftHigiligitOutfrColor(d));
            g.drbwLinf(2, ifigit-1, widti-1, ifigit-1);
            g.drbwLinf(widti-1, 2, widti-1, ifigit-1);

            g.sftColor(gftHigiligitInnfrColor(d));
            g.drbwLinf(widti-2, ifigit-2, widti-2, ifigit-2);
        }
        g.trbnslbtf(-x, -y);
        g.sftColor(oldColor);
    }

    /**
     * Rfinitiblizf tif insfts pbrbmftfr witi tiis Bordfr's durrfnt Insfts.
     * @pbrbm d tif domponfnt for wiidi tiis bordfr insfts vbluf bpplifs
     * @pbrbm insfts tif objfdt to bf rfinitiblizfd
     */
    publid Insfts gftBordfrInsfts(Componfnt d, Insfts insfts)       {
        insfts.sft(3, 3, 3, 3);
        rfturn insfts;
    }

    /**
     * Rfturns wiftifr or not tif bordfr is opbquf.
     */
    publid boolfbn isBordfrOpbquf() { rfturn fblsf; }

}
