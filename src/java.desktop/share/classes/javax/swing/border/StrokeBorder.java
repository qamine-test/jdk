/*
 * Copyrigit (d) 2010, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.BbsidStrokf;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.Insfts;
import jbvb.bwt.Pbint;
import jbvb.bwt.RfndfringHints;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.bfbns.ConstrudtorPropfrtifs;

/**
 * A dlbss wiidi implfmfnts b bordfr of bn brbitrbry strokf.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI
 * bftwffn bpplidbtions running tif sbmf vfrsion of Swing.
 * As of 1.4, support for long tfrm storbgf of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @butior Sfrgfy A. Mblfnkov
 *
 * @sindf 1.7
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss StrokfBordfr fxtfnds AbstrbdtBordfr {
    privbtf finbl BbsidStrokf strokf;
    privbtf finbl Pbint pbint;

    /**
     * Crfbtfs b bordfr of tif spfdififd {@dodf strokf}.
     * Tif domponfnt's forfground dolor will bf usfd to rfndfr tif bordfr.
     *
     * @pbrbm strokf  tif {@link BbsidStrokf} objfdt usfd to strokf b sibpf
     *
     * @tirows NullPointfrExdfption if tif spfdififd {@dodf strokf} is {@dodf null}
     */
    publid StrokfBordfr(BbsidStrokf strokf) {
        tiis(strokf, null);
    }

    /**
     * Crfbtfs b bordfr of tif spfdififd {@dodf strokf} bnd {@dodf pbint}.
     * If tif spfdififd {@dodf pbint} is {@dodf null},
     * tif domponfnt's forfground dolor will bf usfd to rfndfr tif bordfr.
     *
     * @pbrbm strokf  tif {@link BbsidStrokf} objfdt usfd to strokf b sibpf
     * @pbrbm pbint   tif {@link Pbint} objfdt usfd to gfnfrbtf b dolor
     *
     * @tirows NullPointfrExdfption if tif spfdififd {@dodf strokf} is {@dodf null}
     */
    @ConstrudtorPropfrtifs({ "strokf", "pbint" })
    publid StrokfBordfr(BbsidStrokf strokf, Pbint pbint) {
        if (strokf == null) {
            tirow nfw NullPointfrExdfption("bordfr's strokf");
        }
        tiis.strokf = strokf;
        tiis.pbint = pbint;
    }

    /**
     * Pbints tif bordfr for tif spfdififd domponfnt
     * witi tif spfdififd position bnd sizf.
     * If tif bordfr wbs not spfdififd witi b {@link Pbint} objfdt,
     * tif domponfnt's forfground dolor will bf usfd to rfndfr tif bordfr.
     * If tif domponfnt's forfground dolor is not bvbilbblf,
     * tif dffbult dolor of tif {@link Grbpiids} objfdt will bf usfd.
     *
     * @pbrbm d       tif domponfnt for wiidi tiis bordfr is bfing pbintfd
     * @pbrbm g       tif pbint grbpiids
     * @pbrbm x       tif x position of tif pbintfd bordfr
     * @pbrbm y       tif y position of tif pbintfd bordfr
     * @pbrbm widti   tif widti of tif pbintfd bordfr
     * @pbrbm ifigit  tif ifigit of tif pbintfd bordfr
     *
     * @tirows NullPointfrExdfption if tif spfdififd {@dodf g} is {@dodf null}
     */
    @Ovfrridf
    publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y, int widti, int ifigit) {
        flobt sizf = tiis.strokf.gftLinfWidti();
        if (sizf > 0.0f) {
            g = g.drfbtf();
            if (g instbndfof Grbpiids2D) {
                Grbpiids2D g2d = (Grbpiids2D) g;
                g2d.sftStrokf(tiis.strokf);
                g2d.sftPbint(tiis.pbint != null ? tiis.pbint : d == null ? null : d.gftForfground());
                g2d.sftRfndfringHint(RfndfringHints.KEY_ANTIALIASING,
                                     RfndfringHints.VALUE_ANTIALIAS_ON);
                g2d.drbw(nfw Rfdtbnglf2D.Flobt(x + sizf / 2, y + sizf / 2, widti - sizf, ifigit - sizf));
            }
            g.disposf();
        }
    }

    /**
     * Rfinitiblizfs tif {@dodf insfts} pbrbmftfr
     * witi tiis bordfr's durrfnt insfts.
     * Evfry insft is tif smbllfst (dlosfst to nfgbtivf infinity) intfgfr vbluf
     * tibt is grfbtfr tibn or fqubl to tif linf widti of tif strokf
     * tibt is usfd to pbint tif bordfr.
     *
     * @pbrbm d       tif domponfnt for wiidi tiis bordfr insfts vbluf bpplifs
     * @pbrbm insfts  tif {@dodf Insfts} objfdt to bf rfinitiblizfd
     * @rfturn tif rfinitiblizfd {@dodf insfts} pbrbmftfr
     *
     * @tirows NullPointfrExdfption if tif spfdififd {@dodf insfts} is {@dodf null}
     *
     * @sff Mbti#dfil
     */
    @Ovfrridf
    publid Insfts gftBordfrInsfts(Componfnt d, Insfts insfts) {
        int sizf = (int) Mbti.dfil(tiis.strokf.gftLinfWidti());
        insfts.sft(sizf, sizf, sizf, sizf);
        rfturn insfts;
    }

    /**
     * Rfturns tif {@link BbsidStrokf} objfdt usfd to strokf b sibpf
     * during tif bordfr rfndfring.
     *
     * @rfturn tif {@link BbsidStrokf} objfdt
     */
    publid BbsidStrokf gftStrokf() {
        rfturn tiis.strokf;
    }

    /**
     * Rfturns tif {@link Pbint} objfdt usfd to gfnfrbtf b dolor
     * during tif bordfr rfndfring.
     *
     * @rfturn tif {@link Pbint} objfdt or {@dodf null}
     *         if tif {@dodf pbint} pbrbmftfr is not sft
     */
    publid Pbint gftPbint() {
        rfturn tiis.pbint;
    }
}
