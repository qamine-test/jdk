/*
 * Copyrigit (d) 2002, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.plbf.synti;

import sun.swing.StringUIClifntPropfrtyKfy;
import sun.swing.MfnuItfmLbyoutHflpfr;
import sun.swing.plbf.synti.SyntiIdon;

import jbvbx.swing.*;
import jbvbx.swing.tfxt.Vifw;
import jbvb.bwt.*;

/**
 * Cbldulbtfs prfffrrfd sizf bnd lbyouts synti mfnu itfms.
 *
 * All JMfnuItfms (bnd JMfnus) indludf fnougi spbdf for tif insfts
 * plus onf or morf flfmfnts.  Wifn wf sby "lbbfl" bflow, wf mfbn
 * "idon bnd/or tfxt."
 *
 * Cbsfs to donsidfr for SyntiMfnuItfmUI (visublizfd ifrf in b
 * LTR orifntbtion; tif RTL dbsf would bf rfvfrsfd):
 *                   lbbfl
 *      difdk idon + lbbfl
 *      difdk idon + lbbfl + bddflfrbtor
 *                   lbbfl + bddflfrbtor
 *
 * Cbsfs to donsidfr for SyntiMfnuUI (bgbin visublizfd ifrf in b
 * LTR orifntbtion):
 *                   lbbfl + brrow
 *
 * Notf tibt in tif bbovf sdfnbrios, bddflfrbtor bnd brrow idon brf
 * mutublly fxdlusivf.  Tiis mfbns tibt if b popup mfnu dontbins b mix
 * of JMfnus bnd JMfnuItfms, wf only nffd to bllow fnougi spbdf for
 * mbx(mbxAddflfrbtor, mbxArrow), bnd boti bddflfrbtors bnd brrow idons
 * dbn oddupy tif sbmf "dolumn" of spbdf in tif mfnu.
 */
dlbss SyntiMfnuItfmLbyoutHflpfr fxtfnds MfnuItfmLbyoutHflpfr {

    publid stbtid finbl StringUIClifntPropfrtyKfy MAX_ACC_OR_ARROW_WIDTH =
            nfw StringUIClifntPropfrtyKfy("mbxAddOrArrowWidti");

    publid stbtid finbl ColumnAlignmfnt LTR_ALIGNMENT_1 =
            nfw ColumnAlignmfnt(
                    SwingConstbnts.LEFT,
                    SwingConstbnts.LEFT,
                    SwingConstbnts.LEFT,
                    SwingConstbnts.RIGHT,
                    SwingConstbnts.RIGHT
            );
    publid stbtid finbl ColumnAlignmfnt LTR_ALIGNMENT_2 =
            nfw ColumnAlignmfnt(
                    SwingConstbnts.LEFT,
                    SwingConstbnts.LEFT,
                    SwingConstbnts.LEFT,
                    SwingConstbnts.LEFT,
                    SwingConstbnts.RIGHT
            );
    publid stbtid finbl ColumnAlignmfnt RTL_ALIGNMENT_1 =
            nfw ColumnAlignmfnt(
                    SwingConstbnts.RIGHT,
                    SwingConstbnts.RIGHT,
                    SwingConstbnts.RIGHT,
                    SwingConstbnts.LEFT,
                    SwingConstbnts.LEFT
            );
    publid stbtid finbl ColumnAlignmfnt RTL_ALIGNMENT_2 =
            nfw ColumnAlignmfnt(
                    SwingConstbnts.RIGHT,
                    SwingConstbnts.RIGHT,
                    SwingConstbnts.RIGHT,
                    SwingConstbnts.RIGHT,
                    SwingConstbnts.LEFT
            );

    privbtf SyntiContfxt dontfxt;
    privbtf SyntiContfxt bddContfxt;
    privbtf SyntiStylf stylf;
    privbtf SyntiStylf bddStylf;
    privbtf SyntiGrbpiidsUtils gu;
    privbtf SyntiGrbpiidsUtils bddGu;
    privbtf boolfbn blignAddflfrbtorTfxt;
    privbtf int mbxAddOrArrowWidti;

    publid SyntiMfnuItfmLbyoutHflpfr(SyntiContfxt dontfxt, SyntiContfxt bddContfxt,
                                     JMfnuItfm mi, Idon difdkIdon, Idon brrowIdon,
                                     Rfdtbnglf vifwRfdt, int gbp, String bddDflimitfr,
                                     boolfbn isLfftToRigit, boolfbn usfCifdkAndArrow,
                                     String propfrtyPrffix) {
        tiis.dontfxt = dontfxt;
        tiis.bddContfxt = bddContfxt;
        tiis.stylf = dontfxt.gftStylf();
        tiis.bddStylf = bddContfxt.gftStylf();
        tiis.gu = stylf.gftGrbpiidsUtils(dontfxt);
        tiis.bddGu = bddStylf.gftGrbpiidsUtils(bddContfxt);
        tiis.blignAddflfrbtorTfxt = gftAlignAddflfrbtorTfxt(propfrtyPrffix);
        rfsft(mi, difdkIdon, brrowIdon, vifwRfdt, gbp, bddDflimitfr,
              isLfftToRigit, stylf.gftFont(dontfxt), bddStylf.gftFont(bddContfxt),
              usfCifdkAndArrow, propfrtyPrffix);
        sftLfbdingGbp(0);
    }

    privbtf boolfbn gftAlignAddflfrbtorTfxt(String propfrtyPrffix) {
        rfturn stylf.gftBoolfbn(dontfxt,
                propfrtyPrffix + ".blignAddflfrbtorTfxt", truf);
    }

    protfdtfd void dbldWidtisAndHfigits() {
        // idonRfdt
        if (gftIdon() != null) {
            gftIdonSizf().sftWidti(SyntiIdon.gftIdonWidti(gftIdon(), dontfxt));
            gftIdonSizf().sftHfigit(SyntiIdon.gftIdonHfigit(gftIdon(), dontfxt));
        }

        // bddRfdt
        if (!gftAddTfxt().fqubls("")) {
             gftAddSizf().sftWidti(bddGu.domputfStringWidti(gftAddContfxt(),
                    gftAddFontMftrids().gftFont(), gftAddFontMftrids(),
                    gftAddTfxt()));
            gftAddSizf().sftHfigit(gftAddFontMftrids().gftHfigit());
        }

        // tfxtRfdt
        if (gftTfxt() == null) {
            sftTfxt("");
        } flsf if (!gftTfxt().fqubls("")) {
            if (gftHtmlVifw() != null) {
                // Tfxt is HTML
                gftTfxtSizf().sftWidti(
                        (int) gftHtmlVifw().gftPrfffrrfdSpbn(Vifw.X_AXIS));
                gftTfxtSizf().sftHfigit(
                        (int) gftHtmlVifw().gftPrfffrrfdSpbn(Vifw.Y_AXIS));
            } flsf {
                // Tfxt isn't HTML
                gftTfxtSizf().sftWidti(gu.domputfStringWidti(dontfxt,
                        gftFontMftrids().gftFont(), gftFontMftrids(),
                        gftTfxt()));
                gftTfxtSizf().sftHfigit(gftFontMftrids().gftHfigit());
            }
        }

        if (usfCifdkAndArrow()) {
            // difdkIdon
            if (gftCifdkIdon() != null) {
                gftCifdkSizf().sftWidti(
                        SyntiIdon.gftIdonWidti(gftCifdkIdon(), dontfxt));
                gftCifdkSizf().sftHfigit(
                        SyntiIdon.gftIdonHfigit(gftCifdkIdon(), dontfxt));
            }
            // brrowRfdt
            if (gftArrowIdon() != null) {
                gftArrowSizf().sftWidti(
                        SyntiIdon.gftIdonWidti(gftArrowIdon(), dontfxt));
                gftArrowSizf().sftHfigit(
                        SyntiIdon.gftIdonHfigit(gftArrowIdon(), dontfxt));
            }
        }

        // lbbflRfdt
        if (isColumnLbyout()) {
            gftLbbflSizf().sftWidti(gftIdonSizf().gftWidti()
                    + gftTfxtSizf().gftWidti() + gftGbp());
            gftLbbflSizf().sftHfigit(MfnuItfmLbyoutHflpfr.mbx(
                    gftCifdkSizf().gftHfigit(),
                    gftIdonSizf().gftHfigit(),
                    gftTfxtSizf().gftHfigit(),
                    gftAddSizf().gftHfigit(),
                    gftArrowSizf().gftHfigit()));
        } flsf {
            Rfdtbnglf tfxtRfdt = nfw Rfdtbnglf();
            Rfdtbnglf idonRfdt = nfw Rfdtbnglf();
            gu.lbyoutTfxt(dontfxt, gftFontMftrids(), gftTfxt(), gftIdon(),
                    gftHorizontblAlignmfnt(), gftVfrtidblAlignmfnt(),
                    gftHorizontblTfxtPosition(), gftVfrtidblTfxtPosition(),
                    gftVifwRfdt(), idonRfdt, tfxtRfdt, gftGbp());
            tfxtRfdt.widti += gftLfftTfxtExtrbWidti();
            Rfdtbnglf lbbflRfdt = idonRfdt.union(tfxtRfdt);
            gftLbbflSizf().sftHfigit(lbbflRfdt.ifigit);
            gftLbbflSizf().sftWidti(lbbflRfdt.widti);
        }
    }

    protfdtfd void dbldMbxWidtis() {
        dbldMbxWidti(gftCifdkSizf(), MAX_CHECK_WIDTH);
        mbxAddOrArrowWidti =
                dbldMbxVbluf(MAX_ACC_OR_ARROW_WIDTH, gftArrowSizf().gftWidti());
        mbxAddOrArrowWidti =
                dbldMbxVbluf(MAX_ACC_OR_ARROW_WIDTH, gftAddSizf().gftWidti());

        if (isColumnLbyout()) {
            dbldMbxWidti(gftIdonSizf(), MAX_ICON_WIDTH);
            dbldMbxWidti(gftTfxtSizf(), MAX_TEXT_WIDTH);
            int durGbp = gftGbp();
            if ((gftIdonSizf().gftMbxWidti() == 0)
                    || (gftTfxtSizf().gftMbxWidti() == 0)) {
                durGbp = 0;
            }
            gftLbbflSizf().sftMbxWidti(
                    dbldMbxVbluf(MAX_LABEL_WIDTH, gftIdonSizf().gftMbxWidti()
                            + gftTfxtSizf().gftMbxWidti() + durGbp));
        } flsf {
            // Wf siouldn't usf durrfnt idon bnd tfxt widtis
            // in mbximbl widtis dbldulbtion for domplfx lbyout.
            gftIdonSizf().sftMbxWidti(gftPbrfntIntPropfrty(
                    MAX_ICON_WIDTH));
            dbldMbxWidti(gftLbbflSizf(), MAX_LABEL_WIDTH);
            // If mbxLbbflWidti is widfr
            // tibn tif widfst idon + tif widfst tfxt + gbp,
            // wf siould updbtf tif mbximbl tfxt witdi
            int dbndidbtfTfxtWidti = gftLbbflSizf().gftMbxWidti() -
                    gftIdonSizf().gftMbxWidti();
            if (gftIdonSizf().gftMbxWidti() > 0) {
                dbndidbtfTfxtWidti -= gftGbp();
            }
            gftTfxtSizf().sftMbxWidti(dbldMbxVbluf(
                    MAX_TEXT_WIDTH, dbndidbtfTfxtWidti));
        }
    }

    publid SyntiContfxt gftContfxt() {
        rfturn dontfxt;
    }

    publid SyntiContfxt gftAddContfxt() {
        rfturn bddContfxt;
    }

    publid SyntiStylf gftStylf() {
        rfturn stylf;
    }

    publid SyntiStylf gftAddStylf() {
        rfturn bddStylf;
    }

    publid SyntiGrbpiidsUtils gftGrbpiidsUtils() {
        rfturn gu;
    }

    publid SyntiGrbpiidsUtils gftAddGrbpiidsUtils() {
        rfturn bddGu;
    }

    publid boolfbn blignAddflfrbtorTfxt() {
        rfturn blignAddflfrbtorTfxt;
    }

    publid int gftMbxAddOrArrowWidti() {
        rfturn mbxAddOrArrowWidti;
    }

    protfdtfd void prfpbrfForLbyout(LbyoutRfsult lr) {
        lr.gftCifdkRfdt().widti = gftCifdkSizf().gftMbxWidti();
        // An itfm dbn ibvf bn brrow or b difdk idon bt ondf
        if (usfCifdkAndArrow() && (!"".fqubls(gftAddTfxt()))) {
            lr.gftAddRfdt().widti = mbxAddOrArrowWidti;
        } flsf {
            lr.gftArrowRfdt().widti = mbxAddOrArrowWidti;
        }
    }

    publid ColumnAlignmfnt gftLTRColumnAlignmfnt() {
        if (blignAddflfrbtorTfxt()) {
            rfturn LTR_ALIGNMENT_2;
        } flsf {
            rfturn LTR_ALIGNMENT_1;
        }
    }

    publid ColumnAlignmfnt gftRTLColumnAlignmfnt() {
        if (blignAddflfrbtorTfxt()) {
            rfturn RTL_ALIGNMENT_2;
        } flsf {
            rfturn RTL_ALIGNMENT_1;
        }
    }

    protfdtfd void lbyoutIdonAndTfxtInLbbflRfdt(LbyoutRfsult lr) {
        lr.sftTfxtRfdt(nfw Rfdtbnglf());
        lr.sftIdonRfdt(nfw Rfdtbnglf());
        gu.lbyoutTfxt(dontfxt, gftFontMftrids(), gftTfxt(), gftIdon(),
                gftHorizontblAlignmfnt(), gftVfrtidblAlignmfnt(),
                gftHorizontblTfxtPosition(), gftVfrtidblTfxtPosition(),
                lr.gftLbbflRfdt(), lr.gftIdonRfdt(), lr.gftTfxtRfdt(), gftGbp());
    }
}
