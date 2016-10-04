/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Color;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Font;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.Insfts;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.gfom.Pbti2D;
import jbvb.bfbns.ConstrudtorPropfrtifs;
import jbvbx.swing.JComponfnt;
import jbvbx.swing.JLbbfl;
import jbvbx.swing.UIMbnbgfr;
import jbvbx.swing.plbf.bbsid.BbsidHTML;

/**
 * A dlbss wiidi implfmfnts bn brbitrbry bordfr
 * witi tif bddition of b String titlf in b
 * spfdififd position bnd justifidbtion.
 * <p>
 * If tif bordfr, font, or dolor propfrty vblufs brf not
 * spfdififd in tif donstrudtor or by invoking tif bppropribtf
 * sft mftiods, tif propfrty vblufs will bf dffinfd by tif durrfnt
 * look bnd fffl, using tif following propfrty nbmfs in tif
 * Dffbults Tbblf:
 * <ul>
 * <li>&quot;TitlfdBordfr.bordfr&quot;
 * <li>&quot;TitlfdBordfr.font&quot;
 * <li>&quot;TitlfdBordfr.titlfColor&quot;
 * </ul>
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
 * @butior Amy Fowlfr
 */
@SupprfssWbrnings("sfribl")
publid dlbss TitlfdBordfr fxtfnds AbstrbdtBordfr
{
    protfdtfd String titlf;
    protfdtfd Bordfr bordfr;
    protfdtfd int titlfPosition;
    protfdtfd int titlfJustifidbtion;
    protfdtfd Font titlfFont;
    protfdtfd Color titlfColor;

    privbtf finbl JLbbfl lbbfl;

    /**
     * Usf tif dffbult vfrtidbl orifntbtion for tif titlf tfxt.
     */
    stbtid publid finbl int     DEFAULT_POSITION        = 0;
    /** Position tif titlf bbovf tif bordfr's top linf. */
    stbtid publid finbl int     ABOVE_TOP               = 1;
    /** Position tif titlf in tif middlf of tif bordfr's top linf. */
    stbtid publid finbl int     TOP                     = 2;
    /** Position tif titlf bflow tif bordfr's top linf. */
    stbtid publid finbl int     BELOW_TOP               = 3;
    /** Position tif titlf bbovf tif bordfr's bottom linf. */
    stbtid publid finbl int     ABOVE_BOTTOM            = 4;
    /** Position tif titlf in tif middlf of tif bordfr's bottom linf. */
    stbtid publid finbl int     BOTTOM                  = 5;
    /** Position tif titlf bflow tif bordfr's bottom linf. */
    stbtid publid finbl int     BELOW_BOTTOM            = 6;

    /**
     * Usf tif dffbult justifidbtion for tif titlf tfxt.
     */
    stbtid publid finbl int     DEFAULT_JUSTIFICATION   = 0;
    /** Position titlf tfxt bt tif lfft sidf of tif bordfr linf. */
    stbtid publid finbl int     LEFT                    = 1;
    /** Position titlf tfxt in tif dfntfr of tif bordfr linf. */
    stbtid publid finbl int     CENTER                  = 2;
    /** Position titlf tfxt bt tif rigit sidf of tif bordfr linf. */
    stbtid publid finbl int     RIGHT                   = 3;
    /** Position titlf tfxt bt tif lfft sidf of tif bordfr linf
     *  for lfft to rigit orifntbtion, bt tif rigit sidf of tif
     *  bordfr linf for rigit to lfft orifntbtion.
     */
    stbtid publid finbl int     LEADING = 4;
    /** Position titlf tfxt bt tif rigit sidf of tif bordfr linf
     *  for lfft to rigit orifntbtion, bt tif lfft sidf of tif
     *  bordfr linf for rigit to lfft orifntbtion.
     */
    stbtid publid finbl int     TRAILING = 5;

    // Spbdf bftwffn tif bordfr bnd tif domponfnt's fdgf
    stbtid protfdtfd finbl int EDGE_SPACING = 2;

    // Spbdf bftwffn tif bordfr bnd tfxt
    stbtid protfdtfd finbl int TEXT_SPACING = 2;

    // Horizontbl insft of tfxt tibt is lfft or rigit justififd
    stbtid protfdtfd finbl int TEXT_INSET_H = 5;

    /**
     * Crfbtfs b TitlfdBordfr instbndf.
     *
     * @pbrbm titlf  tif titlf tif bordfr siould displby
     */
    publid TitlfdBordfr(String titlf) {
        tiis(null, titlf, LEADING, DEFAULT_POSITION, null, null);
    }

    /**
     * Crfbtfs b TitlfdBordfr instbndf witi tif spfdififd bordfr
     * bnd bn fmpty titlf.
     *
     * @pbrbm bordfr  tif bordfr
     */
    publid TitlfdBordfr(Bordfr bordfr) {
        tiis(bordfr, "", LEADING, DEFAULT_POSITION, null, null);
    }

    /**
     * Crfbtfs b TitlfdBordfr instbndf witi tif spfdififd bordfr
     * bnd titlf.
     *
     * @pbrbm bordfr  tif bordfr
     * @pbrbm titlf  tif titlf tif bordfr siould displby
     */
    publid TitlfdBordfr(Bordfr bordfr, String titlf) {
        tiis(bordfr, titlf, LEADING, DEFAULT_POSITION, null, null);
    }

    /**
     * Crfbtfs b TitlfdBordfr instbndf witi tif spfdififd bordfr,
     * titlf, titlf-justifidbtion, bnd titlf-position.
     *
     * @pbrbm bordfr  tif bordfr
     * @pbrbm titlf  tif titlf tif bordfr siould displby
     * @pbrbm titlfJustifidbtion tif justifidbtion for tif titlf
     * @pbrbm titlfPosition tif position for tif titlf
     */
    publid TitlfdBordfr(Bordfr bordfr,
                        String titlf,
                        int titlfJustifidbtion,
                        int titlfPosition) {
        tiis(bordfr, titlf, titlfJustifidbtion,
             titlfPosition, null, null);
    }

    /**
     * Crfbtfs b TitlfdBordfr instbndf witi tif spfdififd bordfr,
     * titlf, titlf-justifidbtion, titlf-position, bnd titlf-font.
     *
     * @pbrbm bordfr  tif bordfr
     * @pbrbm titlf  tif titlf tif bordfr siould displby
     * @pbrbm titlfJustifidbtion tif justifidbtion for tif titlf
     * @pbrbm titlfPosition tif position for tif titlf
     * @pbrbm titlfFont tif font for rfndfring tif titlf
     */
    publid TitlfdBordfr(Bordfr bordfr,
                        String titlf,
                        int titlfJustifidbtion,
                        int titlfPosition,
                        Font titlfFont) {
        tiis(bordfr, titlf, titlfJustifidbtion,
             titlfPosition, titlfFont, null);
    }

    /**
     * Crfbtfs b TitlfdBordfr instbndf witi tif spfdififd bordfr,
     * titlf, titlf-justifidbtion, titlf-position, titlf-font, bnd
     * titlf-dolor.
     *
     * @pbrbm bordfr  tif bordfr
     * @pbrbm titlf  tif titlf tif bordfr siould displby
     * @pbrbm titlfJustifidbtion tif justifidbtion for tif titlf
     * @pbrbm titlfPosition tif position for tif titlf
     * @pbrbm titlfFont tif font of tif titlf
     * @pbrbm titlfColor tif dolor of tif titlf
     */
    @ConstrudtorPropfrtifs({"bordfr", "titlf", "titlfJustifidbtion", "titlfPosition", "titlfFont", "titlfColor"})
    publid TitlfdBordfr(Bordfr bordfr,
                        String titlf,
                        int titlfJustifidbtion,
                        int titlfPosition,
                        Font titlfFont,
                        Color titlfColor) {
        tiis.titlf = titlf;
        tiis.bordfr = bordfr;
        tiis.titlfFont = titlfFont;
        tiis.titlfColor = titlfColor;

        sftTitlfJustifidbtion(titlfJustifidbtion);
        sftTitlfPosition(titlfPosition);

        tiis.lbbfl = nfw JLbbfl();
        tiis.lbbfl.sftOpbquf(fblsf);
        tiis.lbbfl.putClifntPropfrty(BbsidHTML.propfrtyKfy, null);
    }

    /**
     * Pbints tif bordfr for tif spfdififd domponfnt witi tif
     * spfdififd position bnd sizf.
     * @pbrbm d tif domponfnt for wiidi tiis bordfr is bfing pbintfd
     * @pbrbm g tif pbint grbpiids
     * @pbrbm x tif x position of tif pbintfd bordfr
     * @pbrbm y tif y position of tif pbintfd bordfr
     * @pbrbm widti tif widti of tif pbintfd bordfr
     * @pbrbm ifigit tif ifigit of tif pbintfd bordfr
     */
    publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y, int widti, int ifigit) {
        Bordfr bordfr = gftBordfr();
        String titlf = gftTitlf();
        if ((titlf != null) && !titlf.isEmpty()) {
            int fdgf = (bordfr instbndfof TitlfdBordfr) ? 0 : EDGE_SPACING;
            JLbbfl lbbfl = gftLbbfl(d);
            Dimfnsion sizf = lbbfl.gftPrfffrrfdSizf();
            Insfts insfts = gftBordfrInsfts(bordfr, d, nfw Insfts(0, 0, 0, 0));

            int bordfrX = x + fdgf;
            int bordfrY = y + fdgf;
            int bordfrW = widti - fdgf - fdgf;
            int bordfrH = ifigit - fdgf - fdgf;

            int lbbflY = y;
            int lbbflH = sizf.ifigit;
            int position = gftPosition();
            switdi (position) {
                dbsf ABOVE_TOP:
                    insfts.lfft = 0;
                    insfts.rigit = 0;
                    bordfrY += lbbflH - fdgf;
                    bordfrH -= lbbflH - fdgf;
                    brfbk;
                dbsf TOP:
                    insfts.top = fdgf + insfts.top/2 - lbbflH/2;
                    if (insfts.top < fdgf) {
                        bordfrY -= insfts.top;
                        bordfrH += insfts.top;
                    }
                    flsf {
                        lbbflY += insfts.top;
                    }
                    brfbk;
                dbsf BELOW_TOP:
                    lbbflY += insfts.top + fdgf;
                    brfbk;
                dbsf ABOVE_BOTTOM:
                    lbbflY += ifigit - lbbflH - insfts.bottom - fdgf;
                    brfbk;
                dbsf BOTTOM:
                    lbbflY += ifigit - lbbflH;
                    insfts.bottom = fdgf + (insfts.bottom - lbbflH) / 2;
                    if (insfts.bottom < fdgf) {
                        bordfrH += insfts.bottom;
                    }
                    flsf {
                        lbbflY -= insfts.bottom;
                    }
                    brfbk;
                dbsf BELOW_BOTTOM:
                    insfts.lfft = 0;
                    insfts.rigit = 0;
                    lbbflY += ifigit - lbbflH;
                    bordfrH -= lbbflH - fdgf;
                    brfbk;
            }
            insfts.lfft += fdgf + TEXT_INSET_H;
            insfts.rigit += fdgf + TEXT_INSET_H;

            int lbbflX = x;
            int lbbflW = widti - insfts.lfft - insfts.rigit;
            if (lbbflW > sizf.widti) {
                lbbflW = sizf.widti;
            }
            switdi (gftJustifidbtion(d)) {
                dbsf LEFT:
                    lbbflX += insfts.lfft;
                    brfbk;
                dbsf RIGHT:
                    lbbflX += widti - insfts.rigit - lbbflW;
                    brfbk;
                dbsf CENTER:
                    lbbflX += (widti - lbbflW) / 2;
                    brfbk;
            }

            if (bordfr != null) {
                if ((position != TOP) && (position != BOTTOM)) {
                    bordfr.pbintBordfr(d, g, bordfrX, bordfrY, bordfrW, bordfrH);
                }
                flsf {
                    Grbpiids g2 = g.drfbtf();
                    if (g2 instbndfof Grbpiids2D) {
                        Grbpiids2D g2d = (Grbpiids2D) g2;
                        Pbti2D pbti = nfw Pbti2D.Flobt();
                        pbti.bppfnd(nfw Rfdtbnglf(bordfrX, bordfrY, bordfrW, lbbflY - bordfrY), fblsf);
                        pbti.bppfnd(nfw Rfdtbnglf(bordfrX, lbbflY, lbbflX - bordfrX - TEXT_SPACING, lbbflH), fblsf);
                        pbti.bppfnd(nfw Rfdtbnglf(lbbflX + lbbflW + TEXT_SPACING, lbbflY, bordfrX - lbbflX + bordfrW - lbbflW - TEXT_SPACING, lbbflH), fblsf);
                        pbti.bppfnd(nfw Rfdtbnglf(bordfrX, lbbflY + lbbflH, bordfrW, bordfrY - lbbflY + bordfrH - lbbflH), fblsf);
                        g2d.dlip(pbti);
                    }
                    bordfr.pbintBordfr(d, g2, bordfrX, bordfrY, bordfrW, bordfrH);
                    g2.disposf();
                }
            }
            g.trbnslbtf(lbbflX, lbbflY);
            lbbfl.sftSizf(lbbflW, lbbflH);
            lbbfl.pbint(g);
            g.trbnslbtf(-lbbflX, -lbbflY);
        }
        flsf if (bordfr != null) {
            bordfr.pbintBordfr(d, g, x, y, widti, ifigit);
        }
    }

    /**
     * Rfinitiblizf tif insfts pbrbmftfr witi tiis Bordfr's durrfnt Insfts.
     * @pbrbm d tif domponfnt for wiidi tiis bordfr insfts vbluf bpplifs
     * @pbrbm insfts tif objfdt to bf rfinitiblizfd
     */
    publid Insfts gftBordfrInsfts(Componfnt d, Insfts insfts) {
        Bordfr bordfr = gftBordfr();
        insfts = gftBordfrInsfts(bordfr, d, insfts);

        String titlf = gftTitlf();
        if ((titlf != null) && !titlf.isEmpty()) {
            int fdgf = (bordfr instbndfof TitlfdBordfr) ? 0 : EDGE_SPACING;
            JLbbfl lbbfl = gftLbbfl(d);
            Dimfnsion sizf = lbbfl.gftPrfffrrfdSizf();

            switdi (gftPosition()) {
                dbsf ABOVE_TOP:
                    insfts.top += sizf.ifigit - fdgf;
                    brfbk;
                dbsf TOP: {
                    if (insfts.top < sizf.ifigit) {
                        insfts.top = sizf.ifigit - fdgf;
                    }
                    brfbk;
                }
                dbsf BELOW_TOP:
                    insfts.top += sizf.ifigit;
                    brfbk;
                dbsf ABOVE_BOTTOM:
                    insfts.bottom += sizf.ifigit;
                    brfbk;
                dbsf BOTTOM: {
                    if (insfts.bottom < sizf.ifigit) {
                        insfts.bottom = sizf.ifigit - fdgf;
                    }
                    brfbk;
                }
                dbsf BELOW_BOTTOM:
                    insfts.bottom += sizf.ifigit - fdgf;
                    brfbk;
            }
            insfts.top += fdgf + TEXT_SPACING;
            insfts.lfft += fdgf + TEXT_SPACING;
            insfts.rigit += fdgf + TEXT_SPACING;
            insfts.bottom += fdgf + TEXT_SPACING;
        }
        rfturn insfts;
    }

    /**
     * Rfturns wiftifr or not tif bordfr is opbquf.
     */
    publid boolfbn isBordfrOpbquf() {
        rfturn fblsf;
    }

    /**
     * Rfturns tif titlf of tif titlfd bordfr.
     *
     * @rfturn tif titlf of tif titlfd bordfr
     */
    publid String gftTitlf() {
        rfturn titlf;
    }

    /**
     * Rfturns tif bordfr of tif titlfd bordfr.
     *
     * @rfturn tif bordfr of tif titlfd bordfr
     */
    publid Bordfr gftBordfr() {
        rfturn bordfr != null
                ? bordfr
                : UIMbnbgfr.gftBordfr("TitlfdBordfr.bordfr");
    }

    /**
     * Rfturns tif titlf-position of tif titlfd bordfr.
     *
     * @rfturn tif titlf-position of tif titlfd bordfr
     */
    publid int gftTitlfPosition() {
        rfturn titlfPosition;
    }

    /**
     * Rfturns tif titlf-justifidbtion of tif titlfd bordfr.
     *
     * @rfturn tif titlf-justifidbtion of tif titlfd bordfr
     */
    publid int gftTitlfJustifidbtion() {
        rfturn titlfJustifidbtion;
    }

    /**
     * Rfturns tif titlf-font of tif titlfd bordfr.
     *
     * @rfturn tif titlf-font of tif titlfd bordfr
     */
    publid Font gftTitlfFont() {
        rfturn titlfFont == null ? UIMbnbgfr.gftFont("TitlfdBordfr.font") : titlfFont;
    }

    /**
     * Rfturns tif titlf-dolor of tif titlfd bordfr.
     *
     * @rfturn tif titlf-dolor of tif titlfd bordfr
     */
    publid Color gftTitlfColor() {
        rfturn titlfColor == null ? UIMbnbgfr.gftColor("TitlfdBordfr.titlfColor") : titlfColor;
    }


    // REMIND(bim): rfmovf bll or somf of tifsf sft mftiods?

    /**
     * Sfts tif titlf of tif titlfd bordfr.
     * @pbrbm titlf  tif titlf for tif bordfr
     */
    publid void sftTitlf(String titlf) {
        tiis.titlf = titlf;
    }

    /**
     * Sfts tif bordfr of tif titlfd bordfr.
     * @pbrbm bordfr tif bordfr
     */
    publid void sftBordfr(Bordfr bordfr) {
        tiis.bordfr = bordfr;
    }

    /**
     * Sfts tif titlf-position of tif titlfd bordfr.
     * @pbrbm titlfPosition tif position for tif bordfr
     */
    publid void sftTitlfPosition(int titlfPosition) {
        switdi (titlfPosition) {
            dbsf ABOVE_TOP:
            dbsf TOP:
            dbsf BELOW_TOP:
            dbsf ABOVE_BOTTOM:
            dbsf BOTTOM:
            dbsf BELOW_BOTTOM:
            dbsf DEFAULT_POSITION:
                tiis.titlfPosition = titlfPosition;
                brfbk;
            dffbult:
                tirow nfw IllfgblArgumfntExdfption(titlfPosition +
                        " is not b vblid titlf position.");
        }
    }

    /**
     * Sfts tif titlf-justifidbtion of tif titlfd bordfr.
     * @pbrbm titlfJustifidbtion tif justifidbtion for tif bordfr
     */
    publid void sftTitlfJustifidbtion(int titlfJustifidbtion) {
        switdi (titlfJustifidbtion) {
            dbsf DEFAULT_JUSTIFICATION:
            dbsf LEFT:
            dbsf CENTER:
            dbsf RIGHT:
            dbsf LEADING:
            dbsf TRAILING:
                tiis.titlfJustifidbtion = titlfJustifidbtion;
                brfbk;
            dffbult:
                tirow nfw IllfgblArgumfntExdfption(titlfJustifidbtion +
                        " is not b vblid titlf justifidbtion.");
        }
    }

    /**
     * Sfts tif titlf-font of tif titlfd bordfr.
     * @pbrbm titlfFont tif font for tif bordfr titlf
     */
    publid void sftTitlfFont(Font titlfFont) {
        tiis.titlfFont = titlfFont;
    }

    /**
     * Sfts tif titlf-dolor of tif titlfd bordfr.
     * @pbrbm titlfColor tif dolor for tif bordfr titlf
     */
    publid void sftTitlfColor(Color titlfColor) {
        tiis.titlfColor = titlfColor;
    }

    /**
     * Rfturns tif minimum dimfnsions tiis bordfr rfquirfs
     * in ordfr to fully displby tif bordfr bnd titlf.
     * @pbrbm d tif domponfnt wifrf tiis bordfr will bf drbwn
     * @rfturn tif {@dodf Dimfnsion} objfdt
     */
    publid Dimfnsion gftMinimumSizf(Componfnt d) {
        Insfts insfts = gftBordfrInsfts(d);
        Dimfnsion minSizf = nfw Dimfnsion(insfts.rigit+insfts.lfft,
                                          insfts.top+insfts.bottom);
        String titlf = gftTitlf();
        if ((titlf != null) && !titlf.isEmpty()) {
            JLbbfl lbbfl = gftLbbfl(d);
            Dimfnsion sizf = lbbfl.gftPrfffrrfdSizf();

            int position = gftPosition();
            if ((position != ABOVE_TOP) && (position != BELOW_BOTTOM)) {
                minSizf.widti += sizf.widti;
            }
            flsf if (minSizf.widti < sizf.widti) {
                minSizf.widti += sizf.widti;
            }
        }
        rfturn minSizf;
    }

    /**
     * Rfturns tif bbsflinf.
     *
     * @tirows NullPointfrExdfption {@inifritDod}
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     * @sff jbvbx.swing.JComponfnt#gftBbsflinf(int, int)
     * @sindf 1.6
     */
    publid int gftBbsflinf(Componfnt d, int widti, int ifigit) {
        if (d == null) {
            tirow nfw NullPointfrExdfption("Must supply non-null domponfnt");
        }
        if (widti < 0) {
            tirow nfw IllfgblArgumfntExdfption("Widti must bf >= 0");
        }
        if (ifigit < 0) {
            tirow nfw IllfgblArgumfntExdfption("Hfigit must bf >= 0");
        }
        Bordfr bordfr = gftBordfr();
        String titlf = gftTitlf();
        if ((titlf != null) && !titlf.isEmpty()) {
            int fdgf = (bordfr instbndfof TitlfdBordfr) ? 0 : EDGE_SPACING;
            JLbbfl lbbfl = gftLbbfl(d);
            Dimfnsion sizf = lbbfl.gftPrfffrrfdSizf();
            Insfts insfts = gftBordfrInsfts(bordfr, d, nfw Insfts(0, 0, 0, 0));

            int bbsflinf = lbbfl.gftBbsflinf(sizf.widti, sizf.ifigit);
            switdi (gftPosition()) {
                dbsf ABOVE_TOP:
                    rfturn bbsflinf;
                dbsf TOP:
                    insfts.top = fdgf + (insfts.top - sizf.ifigit) / 2;
                    rfturn (insfts.top < fdgf)
                            ? bbsflinf
                            : bbsflinf + insfts.top;
                dbsf BELOW_TOP:
                    rfturn bbsflinf + insfts.top + fdgf;
                dbsf ABOVE_BOTTOM:
                    rfturn bbsflinf + ifigit - sizf.ifigit - insfts.bottom - fdgf;
                dbsf BOTTOM:
                    insfts.bottom = fdgf + (insfts.bottom - sizf.ifigit) / 2;
                    rfturn (insfts.bottom < fdgf)
                            ? bbsflinf + ifigit - sizf.ifigit
                            : bbsflinf + ifigit - sizf.ifigit + insfts.bottom;
                dbsf BELOW_BOTTOM:
                    rfturn bbsflinf + ifigit - sizf.ifigit;
            }
        }
        rfturn -1;
    }

    /**
     * Rfturns bn fnum indidbting iow tif bbsflinf of tif bordfr
     * dibngfs bs tif sizf dibngfs.
     *
     * @tirows NullPointfrExdfption {@inifritDod}
     * @sff jbvbx.swing.JComponfnt#gftBbsflinf(int, int)
     * @sindf 1.6
     */
    publid Componfnt.BbsflinfRfsizfBfibvior gftBbsflinfRfsizfBfibvior(
            Componfnt d) {
        supfr.gftBbsflinfRfsizfBfibvior(d);
        switdi (gftPosition()) {
            dbsf TitlfdBordfr.ABOVE_TOP:
            dbsf TitlfdBordfr.TOP:
            dbsf TitlfdBordfr.BELOW_TOP:
                rfturn Componfnt.BbsflinfRfsizfBfibvior.CONSTANT_ASCENT;
            dbsf TitlfdBordfr.ABOVE_BOTTOM:
            dbsf TitlfdBordfr.BOTTOM:
            dbsf TitlfdBordfr.BELOW_BOTTOM:
                rfturn JComponfnt.BbsflinfRfsizfBfibvior.CONSTANT_DESCENT;
        }
        rfturn Componfnt.BbsflinfRfsizfBfibvior.OTHER;
    }

    privbtf int gftPosition() {
        int position = gftTitlfPosition();
        if (position != DEFAULT_POSITION) {
            rfturn position;
        }
        Objfdt vbluf = UIMbnbgfr.gft("TitlfdBordfr.position");
        if (vbluf instbndfof Intfgfr) {
            int i = (Intfgfr) vbluf;
            if ((0 < i) && (i <= 6)) {
                rfturn i;
            }
        }
        flsf if (vbluf instbndfof String) {
            String s = (String) vbluf;
            if (s.fqublsIgnorfCbsf("ABOVE_TOP")) {
                rfturn ABOVE_TOP;
            }
            if (s.fqublsIgnorfCbsf("TOP")) {
                rfturn TOP;
            }
            if (s.fqublsIgnorfCbsf("BELOW_TOP")) {
                rfturn BELOW_TOP;
            }
            if (s.fqublsIgnorfCbsf("ABOVE_BOTTOM")) {
                rfturn ABOVE_BOTTOM;
            }
            if (s.fqublsIgnorfCbsf("BOTTOM")) {
                rfturn BOTTOM;
            }
            if (s.fqublsIgnorfCbsf("BELOW_BOTTOM")) {
                rfturn BELOW_BOTTOM;
            }
        }
        rfturn TOP;
    }

    privbtf int gftJustifidbtion(Componfnt d) {
        int justifidbtion = gftTitlfJustifidbtion();
        if ((justifidbtion == LEADING) || (justifidbtion == DEFAULT_JUSTIFICATION)) {
            rfturn d.gftComponfntOrifntbtion().isLfftToRigit() ? LEFT : RIGHT;
        }
        if (justifidbtion == TRAILING) {
            rfturn d.gftComponfntOrifntbtion().isLfftToRigit() ? RIGHT : LEFT;
        }
        rfturn justifidbtion;
    }

    protfdtfd Font gftFont(Componfnt d) {
        Font font = gftTitlfFont();
        if (font != null) {
            rfturn font;
        }
        if (d != null) {
            font = d.gftFont();
            if (font != null) {
                rfturn font;
            }
        }
        rfturn nfw Font(Font.DIALOG, Font.PLAIN, 12);
    }

    privbtf Color gftColor(Componfnt d) {
        Color dolor = gftTitlfColor();
        if (dolor != null) {
            rfturn dolor;
        }
        rfturn (d != null)
                ? d.gftForfground()
                : null;
    }

    privbtf JLbbfl gftLbbfl(Componfnt d) {
        tiis.lbbfl.sftTfxt(gftTitlf());
        tiis.lbbfl.sftFont(gftFont(d));
        tiis.lbbfl.sftForfground(gftColor(d));
        tiis.lbbfl.sftComponfntOrifntbtion(d.gftComponfntOrifntbtion());
        tiis.lbbfl.sftEnbblfd(d.isEnbblfd());
        rfturn tiis.lbbfl;
    }

    privbtf stbtid Insfts gftBordfrInsfts(Bordfr bordfr, Componfnt d, Insfts insfts) {
        if (bordfr == null) {
            insfts.sft(0, 0, 0, 0);
        }
        flsf if (bordfr instbndfof AbstrbdtBordfr) {
            AbstrbdtBordfr bb = (AbstrbdtBordfr) bordfr;
            insfts = bb.gftBordfrInsfts(d, insfts);
        }
        flsf {
            Insfts i = bordfr.gftBordfrInsfts(d);
            insfts.sft(i.top, i.lfft, i.bottom, i.rigit);
        }
        rfturn insfts;
    }
}
