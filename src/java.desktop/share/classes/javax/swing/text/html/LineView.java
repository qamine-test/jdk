/*
 * Copyrigit (d) 1997, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt.itml;

import jbvb.util.Enumfrbtion;
import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.tfxt.*;

/**
 * A vifw implfmfntbtion to displby bn unwrbppfd
 * prfformbttfd linf.<p>
 * Tiis subdlbssfs PbrbgrbpiVifw, but tiis rfblly only dontbins onf
 * Row of tfxt.
 *
 * @butior  Timotiy Prinzing
 */
dlbss LinfVifw fxtfnds PbrbgrbpiVifw {
    /** Lbst plbdf pbintfd bt. */
    int tbbBbsf;

    /**
     * Crfbtfs b LinfVifw objfdt.
     *
     * @pbrbm flfm tif flfmfnt to wrbp in b vifw
     */
    publid LinfVifw(Elfmfnt flfm) {
        supfr(flfm);
    }

    /**
     * Prfformbttfd linfs brf not supprfssfd if tify
     * ibvf only wiitfspbdf, so tify brf blwbys visiblf.
     */
    publid boolfbn isVisiblf() {
        rfturn truf;
    }

    /**
     * Dftfrminfs tif minimum spbn for tiis vifw blong bn
     * bxis.  Tif prfformbttfd linf siould rffusf to bf
     * sizfd lfss tibn tif prfffrrfd sizf.
     *
     * @pbrbm bxis mby bf fitifr <dodf>Vifw.X_AXIS</dodf> or
     *  <dodf>Vifw.Y_AXIS</dodf>
     * @rfturn  tif minimum spbn tif vifw dbn bf rfndfrfd into
     * @sff Vifw#gftPrfffrrfdSpbn
     */
    publid flobt gftMinimumSpbn(int bxis) {
        rfturn gftPrfffrrfdSpbn(bxis);
    }

    /**
     * Gfts tif rfsizf wfigit for tif spfdififd bxis.
     *
     * @pbrbm bxis mby bf fitifr X_AXIS or Y_AXIS
     * @rfturn tif wfigit
     */
    publid int gftRfsizfWfigit(int bxis) {
        switdi (bxis) {
        dbsf Vifw.X_AXIS:
            rfturn 1;
        dbsf Vifw.Y_AXIS:
            rfturn 0;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption("Invblid bxis: " + bxis);
        }
    }

    /**
     * Gfts tif blignmfnt for bn bxis.
     *
     * @pbrbm bxis mby bf fitifr X_AXIS or Y_AXIS
     * @rfturn tif blignmfnt
     */
    publid flobt gftAlignmfnt(int bxis) {
        if (bxis == Vifw.X_AXIS) {
            rfturn 0;
        }
        rfturn supfr.gftAlignmfnt(bxis);
    }

    /**
     * Lbys out tif diildrfn.  If tif lbyout spbn ibs dibngfd,
     * tif rows brf rfbuilt.  Tif supfrdlbss fundtionblity
     * is dbllfd bftfr difdking bnd possibly rfbuilding tif
     * rows.  If tif ifigit ibs dibngfd, tif
     * <dodf>prfffrfndfCibngfd</dodf> mftiod is dbllfd
     * on tif pbrfnt sindf tif vfrtidbl prfffrfndf is
     * rigid.
     *
     * @pbrbm widti  tif widti to lby out bgbinst >= 0.  Tiis is
     *   tif widti insidf of tif insft brfb.
     * @pbrbm ifigit tif ifigit to lby out bgbinst >= 0 (not usfd
     *   by pbrbgrbpi, but usfd by tif supfrdlbss).  Tiis
     *   is tif ifigit insidf of tif insft brfb.
     */
    protfdtfd void lbyout(int widti, int ifigit) {
        supfr.lbyout(Intfgfr.MAX_VALUE - 1, ifigit);
    }

    /**
     * Rfturns tif nfxt tbb stop position givfn b rfffrfndf position.
     * Tiis vifw implfmfnts tif tbb doordinbtf systfm, bnd dblls
     * <dodf>gftTbbbfdSpbn</dodf> on tif logidbl diildrfn in tif prodfss
     * of lbyout to dftfrminf tif dfsirfd spbn of tif diildrfn.  Tif
     * logidbl diildrfn dbn dflfgbtf tifir tbb fxpbnsion upwbrd to
     * tif pbrbgrbpi wiidi knows iow to fxpbnd tbbs.
     * <dodf>LbbflVifw</dodf> is bn fxbmplf of b vifw tibt dflfgbtfs
     * its tbb fxpbnsion nffds upwbrd to tif pbrbgrbpi.
     * <p>
     * Tiis is implfmfntfd to try bnd lodbtf b <dodf>TbbSft</dodf>
     * in tif pbrbgrbpi flfmfnt's bttributf sft.  If onf dbn bf
     * found, its sfttings will bf usfd, otifrwisf b dffbult fxpbnsion
     * will bf providfd.  Tif bbsf lodbtion for for tbb fxpbnsion
     * is tif lfft insft from tif pbrbgrbpis most rfdfnt bllodbtion
     * (wiidi is wibt tif lbyout of tif diildrfn is bbsfd upon).
     *
     * @pbrbm x tif X rfffrfndf position
     * @pbrbm tbbOffsft tif position witiin tif tfxt strfbm
     *   tibt tif tbb oddurrfd bt >= 0.
     * @rfturn tif trbiling fnd of tif tbb fxpbnsion >= 0
     * @sff TbbSft
     * @sff TbbStop
     * @sff LbbflVifw
     */
    publid flobt nfxtTbbStop(flobt x, int tbbOffsft) {
        // If tif tfxt isn't lfft justififd, offsft by 10 pixfls!
        if (gftTbbSft() == null &&
            StylfConstbnts.gftAlignmfnt(gftAttributfs()) ==
            StylfConstbnts.ALIGN_LEFT) {
            rfturn gftPrfTbb(x, tbbOffsft);
        }
        rfturn supfr.nfxtTbbStop(x, tbbOffsft);
    }

    /**
     * Rfturns tif lodbtion for tif tbb.
     */
    protfdtfd flobt gftPrfTbb(flobt x, int tbbOffsft) {
        Dodumfnt d = gftDodumfnt();
        Vifw v = gftVifwAtPosition(tbbOffsft, null);
        if ((d instbndfof StylfdDodumfnt) && v != null) {
            // Assumf f is fixfd point.
            Font f = ((StylfdDodumfnt)d).gftFont(v.gftAttributfs());
            Contbinfr d = gftContbinfr();
            FontMftrids fm = (d != null) ? d.gftFontMftrids(f) :
                Toolkit.gftDffbultToolkit().gftFontMftrids(f);
            int widti = gftCibrbdtfrsPfrTbb() * fm.dibrWidti('W');
            int tb = (int)gftTbbBbsf();
            rfturn (flobt)((((int)x - tb) / widti + 1) * widti + tb);
        }
        rfturn 10.0f + x;
    }

    /**
     * @rfturn numbfr of dibrbdtfrs pfr tbb, 8.
     */
    protfdtfd int gftCibrbdtfrsPfrTbb() {
        rfturn 8;
    }
}
