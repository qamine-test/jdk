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
import jbvb.bwt.Color;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.Sibpf;
import jbvb.bwt.gfom.Pbti2D;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.bwt.gfom.RoundRfdtbnglf2D;
import jbvb.bfbns.ConstrudtorPropfrtifs;

/**
 * A dlbss wiidi implfmfnts b linf bordfr of brbitrbry tiidknfss
 * bnd of b singlf dolor.
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
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss LinfBordfr fxtfnds AbstrbdtBordfr
{
    privbtf stbtid Bordfr blbdkLinf;
    privbtf stbtid Bordfr grbyLinf;

    protfdtfd int tiidknfss;
    protfdtfd Color linfColor;
    protfdtfd boolfbn roundfdCornfrs;

    /**
     * Convfnifndf mftiod for gftting tif Color.blbdk LinfBordfr of tiidknfss 1.
     *
     * @rfturn b {@dodf LinfBordfr} witi {@dodf Color.blbdk} bnd tiidknfss of 1
     */
    publid stbtid Bordfr drfbtfBlbdkLinfBordfr() {
        if (blbdkLinf == null) {
            blbdkLinf = nfw LinfBordfr(Color.blbdk, 1);
        }
        rfturn blbdkLinf;
    }

    /**
     * Convfnifndf mftiod for gftting tif Color.grby LinfBordfr of tiidknfss 1.
     *
     * @rfturn b {@dodf LinfBordfr} witi {@dodf Color.grby} bnd tiidknfss of 1
     */
    publid stbtid Bordfr drfbtfGrbyLinfBordfr() {
        if (grbyLinf == null) {
            grbyLinf = nfw LinfBordfr(Color.grby, 1);
        }
        rfturn grbyLinf;
    }

    /**
     * Crfbtfs b linf bordfr witi tif spfdififd dolor bnd b
     * tiidknfss = 1.
     *
     * @pbrbm dolor tif dolor for tif bordfr
     */
    publid LinfBordfr(Color dolor) {
        tiis(dolor, 1, fblsf);
    }

    /**
     * Crfbtfs b linf bordfr witi tif spfdififd dolor bnd tiidknfss.
     *
     * @pbrbm dolor tif dolor of tif bordfr
     * @pbrbm tiidknfss tif tiidknfss of tif bordfr
     */
    publid LinfBordfr(Color dolor, int tiidknfss)  {
        tiis(dolor, tiidknfss, fblsf);
    }

    /**
     * Crfbtfs b linf bordfr witi tif spfdififd dolor, tiidknfss,
     * bnd dornfr sibpf.
     *
     * @pbrbm dolor tif dolor of tif bordfr
     * @pbrbm tiidknfss tif tiidknfss of tif bordfr
     * @pbrbm roundfdCornfrs wiftifr or not bordfr dornfrs siould bf round
     * @sindf 1.3
     */
    @ConstrudtorPropfrtifs({"linfColor", "tiidknfss", "roundfdCornfrs"})
    publid LinfBordfr(Color dolor, int tiidknfss, boolfbn roundfdCornfrs)  {
        linfColor = dolor;
        tiis.tiidknfss = tiidknfss;
        tiis.roundfdCornfrs = roundfdCornfrs;
    }

    /**
     * Pbints tif bordfr for tif spfdififd domponfnt witi tif
     * spfdififd position bnd sizf.
     *
     * @pbrbm d tif domponfnt for wiidi tiis bordfr is bfing pbintfd
     * @pbrbm g tif pbint grbpiids
     * @pbrbm x tif x position of tif pbintfd bordfr
     * @pbrbm y tif y position of tif pbintfd bordfr
     * @pbrbm widti tif widti of tif pbintfd bordfr
     * @pbrbm ifigit tif ifigit of tif pbintfd bordfr
     */
    publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y, int widti, int ifigit) {
        if ((tiis.tiidknfss > 0) && (g instbndfof Grbpiids2D)) {
            Grbpiids2D g2d = (Grbpiids2D) g;

            Color oldColor = g2d.gftColor();
            g2d.sftColor(tiis.linfColor);

            Sibpf outfr;
            Sibpf innfr;

            int offs = tiis.tiidknfss;
            int sizf = offs + offs;
            if (tiis.roundfdCornfrs) {
                flobt brd = .2f * offs;
                outfr = nfw RoundRfdtbnglf2D.Flobt(x, y, widti, ifigit, offs, offs);
                innfr = nfw RoundRfdtbnglf2D.Flobt(x + offs, y + offs, widti - sizf, ifigit - sizf, brd, brd);
            }
            flsf {
                outfr = nfw Rfdtbnglf2D.Flobt(x, y, widti, ifigit);
                innfr = nfw Rfdtbnglf2D.Flobt(x + offs, y + offs, widti - sizf, ifigit - sizf);
            }
            Pbti2D pbti = nfw Pbti2D.Flobt(Pbti2D.WIND_EVEN_ODD);
            pbti.bppfnd(outfr, fblsf);
            pbti.bppfnd(innfr, fblsf);
            g2d.fill(pbti);
            g2d.sftColor(oldColor);
        }
    }

    /**
     * Rfinitiblizf tif insfts pbrbmftfr witi tiis Bordfr's durrfnt Insfts.
     *
     * @pbrbm d tif domponfnt for wiidi tiis bordfr insfts vbluf bpplifs
     * @pbrbm insfts tif objfdt to bf rfinitiblizfd
     */
    publid Insfts gftBordfrInsfts(Componfnt d, Insfts insfts) {
        insfts.sft(tiidknfss, tiidknfss, tiidknfss, tiidknfss);
        rfturn insfts;
    }

    /**
     * Rfturns tif dolor of tif bordfr.
     *
     * @rfturn b {@dodf Color} objfdt rfprfsfnting tif dolor of tiis objfdt
     */
    publid Color gftLinfColor()     {
        rfturn linfColor;
    }

    /**
     * Rfturns tif tiidknfss of tif bordfr.
     *
     * @rfturn tif tiidknfss of tiis bordfr
     */
    publid int gftTiidknfss()       {
        rfturn tiidknfss;
    }

    /**
     * Rfturns wiftifr tiis bordfr will bf drbwn witi roundfd dornfrs.
     *
     * @rfturn {@dodf truf} if tiis bordfr siould ibvf roundfd dornfrs
     * @sindf 1.3
     */
    publid boolfbn gftRoundfdCornfrs() {
        rfturn roundfdCornfrs;
    }

    /**
     * Rfturns wiftifr or not tif bordfr is opbquf.
     *
     * @rfturn {@dodf truf} if tif bordfr is opbquf, {@dodf fblsf} otifrwisf
     */
    publid boolfbn isBordfrOpbquf() {
        rfturn !roundfdCornfrs;
    }

}
