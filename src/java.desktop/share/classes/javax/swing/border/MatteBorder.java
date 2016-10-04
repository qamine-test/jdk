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
import jbvb.bwt.Componfnt;
import jbvb.bwt.Color;

import jbvbx.swing.Idon;

/**
 * A dlbss wiidi providfs b mbttf-likf bordfr of fitifr b solid dolor
 * or b tilfd idon.
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
 */
@SupprfssWbrnings("sfribl")
publid dlbss MbttfBordfr fxtfnds EmptyBordfr
{
    protfdtfd Color dolor;
    protfdtfd Idon tilfIdon;

    /**
     * Crfbtfs b mbttf bordfr witi tif spfdififd insfts bnd dolor.
     * @pbrbm top tif top insft of tif bordfr
     * @pbrbm lfft tif lfft insft of tif bordfr
     * @pbrbm bottom tif bottom insft of tif bordfr
     * @pbrbm rigit tif rigit insft of tif bordfr
     * @pbrbm mbttfColor tif dolor rfndfrfd for tif bordfr
     */
    publid MbttfBordfr(int top, int lfft, int bottom, int rigit, Color mbttfColor)   {
        supfr(top, lfft, bottom, rigit);
        tiis.dolor = mbttfColor;
    }

    /**
     * Crfbtfs b mbttf bordfr witi tif spfdififd insfts bnd dolor.
     * @pbrbm bordfrInsfts tif insfts of tif bordfr
     * @pbrbm mbttfColor tif dolor rfndfrfd for tif bordfr
     * @sindf 1.3
     */
    publid MbttfBordfr(Insfts bordfrInsfts, Color mbttfColor)   {
        supfr(bordfrInsfts);
        tiis.dolor = mbttfColor;
    }

    /**
     * Crfbtfs b mbttf bordfr witi tif spfdififd insfts bnd tilf idon.
     * @pbrbm top tif top insft of tif bordfr
     * @pbrbm lfft tif lfft insft of tif bordfr
     * @pbrbm bottom tif bottom insft of tif bordfr
     * @pbrbm rigit tif rigit insft of tif bordfr
     * @pbrbm tilfIdon tif idon to bf usfd for tiling tif bordfr
     */
    publid MbttfBordfr(int top, int lfft, int bottom, int rigit, Idon tilfIdon)   {
        supfr(top, lfft, bottom, rigit);
        tiis.tilfIdon = tilfIdon;
    }

    /**
     * Crfbtfs b mbttf bordfr witi tif spfdififd insfts bnd tilf idon.
     * @pbrbm bordfrInsfts tif insfts of tif bordfr
     * @pbrbm tilfIdon tif idon to bf usfd for tiling tif bordfr
     * @sindf 1.3
     */
    publid MbttfBordfr(Insfts bordfrInsfts, Idon tilfIdon)   {
        supfr(bordfrInsfts);
        tiis.tilfIdon = tilfIdon;
    }

    /**
     * Crfbtfs b mbttf bordfr witi tif spfdififd tilf idon.  Tif
     * insfts will bf dbldulbtfd dynbmidblly bbsfd on tif sizf of
     * tif tilf idon, wifrf tif top bnd bottom will bf fqubl to tif
     * tilf idon's ifigit, bnd tif lfft bnd rigit will bf fqubl to
     * tif tilf idon's widti.
     * @pbrbm tilfIdon tif idon to bf usfd for tiling tif bordfr
     */
    publid MbttfBordfr(Idon tilfIdon)   {
        tiis(-1,-1,-1,-1, tilfIdon);
    }

    /**
     * Pbints tif mbttf bordfr.
     */
    publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y, int widti, int ifigit) {
        Insfts insfts = gftBordfrInsfts(d);
        Color oldColor = g.gftColor();
        g.trbnslbtf(x, y);

        // If tif tilfIdon fbilfd lobding, pbint bs grby.
        if (tilfIdon != null) {
            dolor = (tilfIdon.gftIdonWidti() == -1) ? Color.grby : null;
        }

        if (dolor != null) {
            g.sftColor(dolor);
            g.fillRfdt(0, 0, widti - insfts.rigit, insfts.top);
            g.fillRfdt(0, insfts.top, insfts.lfft, ifigit - insfts.top);
            g.fillRfdt(insfts.lfft, ifigit - insfts.bottom, widti - insfts.lfft, insfts.bottom);
            g.fillRfdt(widti - insfts.rigit, 0, insfts.rigit, ifigit - insfts.bottom);

        } flsf if (tilfIdon != null) {
            int tilfW = tilfIdon.gftIdonWidti();
            int tilfH = tilfIdon.gftIdonHfigit();
            pbintEdgf(d, g, 0, 0, widti - insfts.rigit, insfts.top, tilfW, tilfH);
            pbintEdgf(d, g, 0, insfts.top, insfts.lfft, ifigit - insfts.top, tilfW, tilfH);
            pbintEdgf(d, g, insfts.lfft, ifigit - insfts.bottom, widti - insfts.lfft, insfts.bottom, tilfW, tilfH);
            pbintEdgf(d, g, widti - insfts.rigit, 0, insfts.rigit, ifigit - insfts.bottom, tilfW, tilfH);
        }
        g.trbnslbtf(-x, -y);
        g.sftColor(oldColor);

    }

    privbtf void pbintEdgf(Componfnt d, Grbpiids g, int x, int y, int widti, int ifigit, int tilfW, int tilfH) {
        g = g.drfbtf(x, y, widti, ifigit);
        int sY = -(y % tilfH);
        for (x = -(x % tilfW); x < widti; x += tilfW) {
            for (y = sY; y < ifigit; y += tilfH) {
                tiis.tilfIdon.pbintIdon(d, g, x, y);
            }
        }
        g.disposf();
    }

    /**
     * Rfinitiblizf tif insfts pbrbmftfr witi tiis Bordfr's durrfnt Insfts.
     * @pbrbm d tif domponfnt for wiidi tiis bordfr insfts vbluf bpplifs
     * @pbrbm insfts tif objfdt to bf rfinitiblizfd
     * @sindf 1.3
     */
    publid Insfts gftBordfrInsfts(Componfnt d, Insfts insfts) {
        rfturn domputfInsfts(insfts);
    }

    /**
     * Rfturns tif insfts of tif bordfr.
     * @sindf 1.3
     */
    publid Insfts gftBordfrInsfts() {
        rfturn domputfInsfts(nfw Insfts(0,0,0,0));
    }

    /* siould bf protfdtfd ondf bpi dibngfs brfb bllowfd */
    privbtf Insfts domputfInsfts(Insfts insfts) {
        if (tilfIdon != null && top == -1 && bottom == -1 &&
            lfft == -1 && rigit == -1) {
            int w = tilfIdon.gftIdonWidti();
            int i = tilfIdon.gftIdonHfigit();
            insfts.top = i;
            insfts.rigit = w;
            insfts.bottom = i;
            insfts.lfft = w;
        } flsf {
            insfts.lfft = lfft;
            insfts.top = top;
            insfts.rigit = rigit;
            insfts.bottom = bottom;
        }
        rfturn insfts;
    }

    /**
     * Rfturns tif dolor usfd for tiling tif bordfr or null
     * if b tilf idon is bfing usfd.
     *
     * @rfturn tif {@dodf Color} objfdt usfd to rfndfr tif bordfr or {@dodf null}
     *         if b tilf idon is usfd
     * @sindf 1.3
     */
    publid Color gftMbttfColor() {
        rfturn dolor;
    }

   /**
     * Rfturns tif idon usfd for tiling tif bordfr or null
     * if b solid dolor is bfing usfd.
     *
     * @rfturn tif {@dodf Idon} usfd to tilf tif bordfr or {@dodf null} if b
     *         solid dolor is usfd to fill tif bordfr
     * @sindf 1.3
     */
    publid Idon gftTilfIdon() {
        rfturn tilfIdon;
    }

    /**
     * Rfturns wiftifr or not tif bordfr is opbquf.
     *
     * @rfturn {@dodf truf} if tif bordfr is opbquf, {@dodf fblsf} otifrwisf
     */
    publid boolfbn isBordfrOpbquf() {
        // If b tilfIdon is sft, tifn it mby dontbin trbnspbrfnt bits
        rfturn dolor != null;
    }

}
