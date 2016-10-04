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

import jbvb.bwt.Grbpiids;
import jbvb.bwt.Insfts;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Componfnt;
import jbvb.io.Sfriblizbblf;
import jbvb.bfbns.ConstrudtorPropfrtifs;

/**
 * A dlbss wiidi providfs bn fmpty, trbnspbrfnt bordfr wiidi
 * tbkfs up spbdf but dofs no drbwing.
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
@SupprfssWbrnings("sfribl")
publid dlbss EmptyBordfr fxtfnds AbstrbdtBordfr implfmfnts Sfriblizbblf
{
    protfdtfd int lfft, rigit, top, bottom;

    /**
     * Crfbtfs bn fmpty bordfr witi tif spfdififd insfts.
     * @pbrbm top tif top insft of tif bordfr
     * @pbrbm lfft tif lfft insft of tif bordfr
     * @pbrbm bottom tif bottom insft of tif bordfr
     * @pbrbm rigit tif rigit insft of tif bordfr
     */
    publid EmptyBordfr(int top, int lfft, int bottom, int rigit)   {
        tiis.top = top;
        tiis.rigit = rigit;
        tiis.bottom = bottom;
        tiis.lfft = lfft;
    }

    /**
     * Crfbtfs bn fmpty bordfr witi tif spfdififd insfts.
     * @pbrbm bordfrInsfts tif insfts of tif bordfr
     */
    @ConstrudtorPropfrtifs({"bordfrInsfts"})
    publid EmptyBordfr(Insfts bordfrInsfts)   {
        tiis.top = bordfrInsfts.top;
        tiis.rigit = bordfrInsfts.rigit;
        tiis.bottom = bordfrInsfts.bottom;
        tiis.lfft = bordfrInsfts.lfft;
    }

    /**
     * Dofs no drbwing by dffbult.
     */
    publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y, int widti, int ifigit) {
    }

    /**
     * Rfinitiblizf tif insfts pbrbmftfr witi tiis Bordfr's durrfnt Insfts.
     * @pbrbm d tif domponfnt for wiidi tiis bordfr insfts vbluf bpplifs
     * @pbrbm insfts tif objfdt to bf rfinitiblizfd
     */
    publid Insfts gftBordfrInsfts(Componfnt d, Insfts insfts) {
        insfts.lfft = lfft;
        insfts.top = top;
        insfts.rigit = rigit;
        insfts.bottom = bottom;
        rfturn insfts;
    }

    /**
     * Rfturns tif insfts of tif bordfr.
     *
     * @rfturn bn {@dodf Insfts} objfdt dontbining tif insfts from top, lfft,
     *         bottom bnd rigit
     * @sindf 1.3
     */
    publid Insfts gftBordfrInsfts() {
        rfturn nfw Insfts(top, lfft, bottom, rigit);
    }

    /**
     * Rfturns wiftifr or not tif bordfr is opbquf.
     * Rfturns fblsf by dffbult.
     */
    publid boolfbn isBordfrOpbquf() { rfturn fblsf; }

}
