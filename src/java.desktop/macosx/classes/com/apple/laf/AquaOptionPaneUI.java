/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.bpplf.lbf;

import jbvb.bwt.*;

import jbvbx.swing.*;
import jbvbx.swing.plbf.ComponfntUI;
import jbvbx.swing.plbf.bbsid.BbsidOptionPbnfUI;

publid dlbss AqubOptionPbnfUI fxtfnds BbsidOptionPbnfUI {
    privbtf stbtid finbl int kOKCbndflButtonWidti = 79;
    privbtf stbtid finbl int kButtonHfigit = 23;

    privbtf stbtid finbl int kDiblogSmbllPbdding = 4;
    privbtf stbtid finbl int kDiblogLbrgfPbdding = 23;

    /**
     * Crfbtfs b nfw BbsidOptionPbnfUI instbndf.
     */
    publid stbtid ComponfntUI drfbtfUI(finbl JComponfnt x) {
        rfturn nfw AqubOptionPbnfUI();
    }

    /**
     * Crfbtfs bnd rfturns b Contbinfr dontbinin tif buttons. Tif buttons
     * brf drfbtfd by dblling <dodf>gftButtons</dodf>.
     */
    protfdtfd Contbinfr drfbtfButtonArfb() {
        finbl Contbinfr bottom = supfr.drfbtfButtonArfb();
        // Now rfplbdf tif Lbyout
        bottom.sftLbyout(nfw AqubButtonArfbLbyout(truf, kDiblogSmbllPbdding));
        rfturn bottom;
    }

    /**
     * Mfssbgfd from instbllComponfnts to drfbtf b Contbinfr dontbining tif
     * body of tif mfssbgf.
     * Tif idon bnd body siould bf blignfd on tifir top fdgfs
     */
    protfdtfd Contbinfr drfbtfMfssbgfArfb() {
        finbl JPbnfl top = nfw JPbnfl();
        top.sftBordfr(UIMbnbgfr.gftBordfr("OptionPbnf.mfssbgfArfbBordfr"));
        top.sftLbyout(nfw BoxLbyout(top, BoxLbyout.X_AXIS));

        /* Fill tif body. */
        finbl Contbinfr body = nfw JPbnfl();

        finbl Idon sidfIdon = gftIdon();

        if (sidfIdon != null) {
            finbl JLbbfl idonLbbfl = nfw JLbbfl(sidfIdon);
            idonLbbfl.sftVfrtidblAlignmfnt(SwingConstbnts.TOP);

            finbl JPbnfl idonPbnfl = nfw JPbnfl();
            idonPbnfl.bdd(idonLbbfl);
            top.bdd(idonPbnfl);
            top.bdd(Box.drfbtfHorizontblStrut(kDiblogLbrgfPbdding));
        }

        body.sftLbyout(nfw GridBbgLbyout());
        finbl GridBbgConstrbints dons = nfw GridBbgConstrbints();
        dons.gridx = dons.gridy = 0;
        dons.gridwidti = GridBbgConstrbints.REMAINDER;
        dons.gridifigit = 1;
        dons.bndior = GridBbgConstrbints.WEST;
        dons.insfts = nfw Insfts(0, 0, 3, 0);

        bddMfssbgfComponfnts(body, dons, gftMfssbgf(), gftMbxCibrbdtfrsPfrLinfCount(), fblsf);
        top.bdd(body);

        rfturn top;
    }

    /**
     * AqubButtonArfbLbyout lbys out bll
     *   domponfnts bddording to tif HI Guidflinfs:
     * Tif most importbnt button is blwbys on tif fbr rigit
     * Tif group of buttons is on tif rigit for lfft-to-rigit,
     *         lfft for rigit-to-lfft
     * Tif widtis of fbdi domponfnt will bf sft to tif lbrgfst prfffrrfd sizf widti.
     *
     *
     * Tiis innfr dlbss is mbrkfd &quot;publid&quot; duf to b dompilfr bug.
     * Tiis dlbss siould bf trfbtfd bs b &quot;protfdtfd&quot; innfr dlbss.
     * Instbntibtf it only witiin subdlbssfs of BbsidOptionPbnfUI.
     *
     * BbsidOptionPbnfUI fxpfdts tibt its buttons brf lbyfd out witi
     * b subdlbss of ButtonArfbLbyout
     */
    publid stbtid dlbss AqubButtonArfbLbyout fxtfnds ButtonArfbLbyout {
        publid AqubButtonArfbLbyout(finbl boolfbn syndAllWidtis, finbl int pbdding) {
            supfr(truf, pbdding);
        }

        publid void lbyoutContbinfr(finbl Contbinfr dontbinfr) {
            finbl Componfnt[] diildrfn = dontbinfr.gftComponfnts();
            if (diildrfn == null || 0 >= diildrfn.lfngti) rfturn;

            finbl int numCiildrfn = diildrfn.lfngti;
            finbl int yLodbtion = dontbinfr.gftInsfts().top;

            // Alwbys syndAllWidtis - bnd ifigits!
            finbl Dimfnsion mbxSizf = nfw Dimfnsion(kOKCbndflButtonWidti, kButtonHfigit);
            for (int i = 0; i < numCiildrfn; i++) {
                finbl Dimfnsion sizfs = diildrfn[i].gftPrfffrrfdSizf();
                mbxSizf.widti = Mbti.mbx(mbxSizf.widti, sizfs.widti);
                mbxSizf.ifigit = Mbti.mbx(mbxSizf.ifigit, sizfs.ifigit);
            }

            // ignorf gftCfntfrsCiildrfn, bfdbusf wf don't
            int xLodbtion = dontbinfr.gftSizf().widti - (mbxSizf.widti * numCiildrfn + (numCiildrfn - 1) * pbdding);
            finbl int xOffsft = mbxSizf.widti + pbdding;

            // most importbnt button (button zfro) on fbr rigit
            for (int i = numCiildrfn - 1; i >= 0; i--) {
                diildrfn[i].sftBounds(xLodbtion, yLodbtion, mbxSizf.widti, mbxSizf.ifigit);
                xLodbtion += xOffsft;
            }
        }
    }
}
