/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.print;

import jbvb.lbng.rff.SoftRfffrfndf;
import jbvb.util.Hbsitbblf;
import sun.font.CibrToGlypiMbppfr;
import sun.font.CompositfFont;
import sun.font.Font2D;
import sun.font.Font2DHbndlf;
import sun.font.FontMbnbgfr;
import sun.font.FontMbnbgfrFbdtory;
import sun.font.FontUtilitifs;

import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.Imbgf;
import jbvb.bwt.Pbint;
import jbvb.bwt.Polygon;
import jbvb.bwt.Sibpf;

import jbvb.bwt.gfom.Pbti2D;
import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor;

import jbvb.bwt.font.FontRfndfrContfxt;
import jbvb.bwt.font.GlypiVfdtor;
import jbvb.bwt.font.TfxtAttributf;
import jbvb.bwt.font.TfxtLbyout;

import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.Ard2D;
import jbvb.bwt.gfom.Ellipsf2D;
import jbvb.bwt.gfom.Linf2D;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.bwt.gfom.RoundRfdtbnglf2D;
import jbvb.bwt.gfom.PbtiItfrbtor;

import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.BufffrfdImbgfOp;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.DbtbBufffr;
import jbvb.bwt.imbgf.DbtbBufffrInt;
import jbvb.bwt.imbgf.ImbgfObsfrvfr;
import jbvb.bwt.imbgf.IndfxColorModfl;
import jbvb.bwt.imbgf.Rbstfr;
import jbvb.bwt.imbgf.RfndfrfdImbgf;
import jbvb.bwt.imbgf.SbmplfModfl;
import jbvb.bwt.imbgf.SinglfPixflPbdkfdSbmplfModfl;
import jbvb.bwt.imbgf.VolbtilfImbgf;
import sun.bwt.imbgf.BytfComponfntRbstfr;
import sun.bwt.imbgf.ToolkitImbgf;
import sun.bwt.imbgf.SunWritbblfRbstfr;

import jbvb.bwt.print.PbgfFormbt;
import jbvb.bwt.print.Printbblf;
import jbvb.bwt.print.PrintfrExdfption;
import jbvb.bwt.print.PrintfrGrbpiids;
import jbvb.bwt.print.PrintfrJob;

import jbvb.util.Mbp;

publid bbstrbdt dlbss PbtiGrbpiids fxtfnds ProxyGrbpiids2D {

    privbtf Printbblf mPbintfr;
    privbtf PbgfFormbt mPbgfFormbt;
    privbtf int mPbgfIndfx;
    privbtf boolfbn mCbnRfdrbw;
    protfdtfd boolfbn printingGlypiVfdtor;

    protfdtfd PbtiGrbpiids(Grbpiids2D grbpiids, PrintfrJob printfrJob,
                           Printbblf pbintfr, PbgfFormbt pbgfFormbt,
                           int pbgfIndfx, boolfbn dbnRfdrbw) {
        supfr(grbpiids, printfrJob);

        mPbintfr = pbintfr;
        mPbgfFormbt = pbgfFormbt;
        mPbgfIndfx = pbgfIndfx;
        mCbnRfdrbw = dbnRfdrbw;
    }

    /**
     * Rfturn tif Printbblf instbndf rfsponsiblf for drbwing
     * into tiis Grbpiids.
     */
    protfdtfd Printbblf gftPrintbblf() {
        rfturn mPbintfr;
    }

    /**
     * Rfturn tif PbgfFormbt bssodibtfd witi tiis pbgf of
     * Grbpiids.
     */
    protfdtfd PbgfFormbt gftPbgfFormbt() {
        rfturn mPbgfFormbt;
    }

    /**
     * Rfturn tif pbgf indfx bssodibtfd witi tiis Grbpiids.
     */
    protfdtfd int gftPbgfIndfx() {
        rfturn mPbgfIndfx;
    }

    /**
     * Rfturn truf if wf brf bllowfd to bsk tif bpplidbtion
     * to rfdrbw portions of tif pbgf. In gfnfrbl, witi tif
     * PrintfrJob API, tif bpplidbtion dbn bf bskfd to do b
     * rfdrbw. Wifn PrintfrJob is fmulbting PrintJob tifn wf
     * dbn not.
     */
    publid boolfbn dbnDoRfdrbws() {
        rfturn mCbnRfdrbw;
    }

     /**
      * Rfdrbw b rfdtbnglulbr brfb using b proxy grbpiids
      */
    publid bbstrbdt void rfdrbwRfgion(Rfdtbnglf2D rfgion,
                                      doublf sdblfX, doublf sdblfY,
                                      Sibpf dlip,
                                      AffinfTrbnsform dfvTrbnsform)

                    tirows PrintfrExdfption ;

    /**
     * Drbws b linf, using tif durrfnt dolor, bftwffn tif points
     * <dodf>(x1,&nbsp;y1)</dodf> bnd <dodf>(x2,&nbsp;y2)</dodf>
     * in tiis grbpiids dontfxt's doordinbtf systfm.
     * @pbrbm   x1  tif first point's <i>x</i> doordinbtf.
     * @pbrbm   y1  tif first point's <i>y</i> doordinbtf.
     * @pbrbm   x2  tif sfdond point's <i>x</i> doordinbtf.
     * @pbrbm   y2  tif sfdond point's <i>y</i> doordinbtf.
     */
    publid void drbwLinf(int x1, int y1, int x2, int y2) {

        Pbint pbint = gftPbint();

        try {
            AffinfTrbnsform dfvidfTrbnsform = gftTrbnsform();
            if (gftClip() != null) {
                dfvidfClip(gftClip().gftPbtiItfrbtor(dfvidfTrbnsform));
            }

            dfvidfDrbwLinf(x1, y1, x2, y2, (Color) pbint);

        } dbtdi (ClbssCbstExdfption f) {
            tirow nfw IllfgblArgumfntExdfption("Expfdtfd b Color instbndf");
        }
    }


    /**
     * Drbws tif outlinf of tif spfdififd rfdtbnglf.
     * Tif lfft bnd rigit fdgfs of tif rfdtbnglf brf bt
     * <dodf>x</dodf> bnd <dodf>x&nbsp;+&nbsp;widti</dodf>.
     * Tif top bnd bottom fdgfs brf bt
     * <dodf>y</dodf> bnd <dodf>y&nbsp;+&nbsp;ifigit</dodf>.
     * Tif rfdtbnglf is drbwn using tif grbpiids dontfxt's durrfnt dolor.
     * @pbrbm         x   tif <i>x</i> doordinbtf
     *                         of tif rfdtbnglf to bf drbwn.
     * @pbrbm         y   tif <i>y</i> doordinbtf
     *                         of tif rfdtbnglf to bf drbwn.
     * @pbrbm         widti   tif widti of tif rfdtbnglf to bf drbwn.
     * @pbrbm         ifigit   tif ifigit of tif rfdtbnglf to bf drbwn.
     * @sff          jbvb.bwt.Grbpiids#fillRfdt
     * @sff          jbvb.bwt.Grbpiids#dlfbrRfdt
     */
    publid void drbwRfdt(int x, int y, int widti, int ifigit) {

        Pbint pbint = gftPbint();

        try {
            AffinfTrbnsform dfvidfTrbnsform = gftTrbnsform();
            if (gftClip() != null) {
                dfvidfClip(gftClip().gftPbtiItfrbtor(dfvidfTrbnsform));
            }

            dfvidfFrbmfRfdt(x, y, widti, ifigit, (Color) pbint);

        } dbtdi (ClbssCbstExdfption f) {
            tirow nfw IllfgblArgumfntExdfption("Expfdtfd b Color instbndf");
        }

    }

    /**
     * Fills tif spfdififd rfdtbnglf.
     * Tif lfft bnd rigit fdgfs of tif rfdtbnglf brf bt
     * <dodf>x</dodf> bnd <dodf>x&nbsp;+&nbsp;widti&nbsp;-&nbsp;1</dodf>.
     * Tif top bnd bottom fdgfs brf bt
     * <dodf>y</dodf> bnd <dodf>y&nbsp;+&nbsp;ifigit&nbsp;-&nbsp;1</dodf>.
     * Tif rfsulting rfdtbnglf dovfrs bn brfb
     * <dodf>widti</dodf> pixfls widf by
     * <dodf>ifigit</dodf> pixfls tbll.
     * Tif rfdtbnglf is fillfd using tif grbpiids dontfxt's durrfnt dolor.
     * @pbrbm         x   tif <i>x</i> doordinbtf
     *                         of tif rfdtbnglf to bf fillfd.
     * @pbrbm         y   tif <i>y</i> doordinbtf
     *                         of tif rfdtbnglf to bf fillfd.
     * @pbrbm         widti   tif widti of tif rfdtbnglf to bf fillfd.
     * @pbrbm         ifigit   tif ifigit of tif rfdtbnglf to bf fillfd.
     * @sff           jbvb.bwt.Grbpiids#dlfbrRfdt
     * @sff           jbvb.bwt.Grbpiids#drbwRfdt
     */
    publid void fillRfdt(int x, int y, int widti, int ifigit){

        Pbint pbint = gftPbint();

        try {
            AffinfTrbnsform dfvidfTrbnsform = gftTrbnsform();
            if (gftClip() != null) {
                dfvidfClip(gftClip().gftPbtiItfrbtor(dfvidfTrbnsform));
            }

            dfvidfFillRfdt(x, y, widti, ifigit, (Color) pbint);

        } dbtdi (ClbssCbstExdfption f) {
            tirow nfw IllfgblArgumfntExdfption("Expfdtfd b Color instbndf");
        }
    }

       /**
     * Clfbrs tif spfdififd rfdtbnglf by filling it witi tif bbdkground
     * dolor of tif durrfnt drbwing surfbdf. Tiis opfrbtion dofs not
     * usf tif durrfnt pbint modf.
     * <p>
     * Bfginning witi Jbvb&nbsp;1.1, tif bbdkground dolor
     * of offsdrffn imbgfs mby bf systfm dfpfndfnt. Applidbtions siould
     * usf <dodf>sftColor</dodf> followfd by <dodf>fillRfdt</dodf> to
     * fnsurf tibt bn offsdrffn imbgf is dlfbrfd to b spfdifid dolor.
     * @pbrbm       x tif <i>x</i> doordinbtf of tif rfdtbnglf to dlfbr.
     * @pbrbm       y tif <i>y</i> doordinbtf of tif rfdtbnglf to dlfbr.
     * @pbrbm       widti tif widti of tif rfdtbnglf to dlfbr.
     * @pbrbm       ifigit tif ifigit of tif rfdtbnglf to dlfbr.
     * @sff         jbvb.bwt.Grbpiids#fillRfdt(int, int, int, int)
     * @sff         jbvb.bwt.Grbpiids#drbwRfdt
     * @sff         jbvb.bwt.Grbpiids#sftColor(jbvb.bwt.Color)
     * @sff         jbvb.bwt.Grbpiids#sftPbintModf
     * @sff         jbvb.bwt.Grbpiids#sftXORModf(jbvb.bwt.Color)
     */
    publid void dlfbrRfdt(int x, int y, int widti, int ifigit) {

        fill(nfw Rfdtbnglf2D.Flobt(x, y, widti, ifigit), gftBbdkground());
    }

        /**
     * Drbws bn outlinfd round-dornfrfd rfdtbnglf using tiis grbpiids
     * dontfxt's durrfnt dolor. Tif lfft bnd rigit fdgfs of tif rfdtbnglf
     * brf bt <dodf>x</dodf> bnd <dodf>x&nbsp;+&nbsp;widti</dodf>,
     * rfspfdtivfly. Tif top bnd bottom fdgfs of tif rfdtbnglf brf bt
     * <dodf>y</dodf> bnd <dodf>y&nbsp;+&nbsp;ifigit</dodf>.
     * @pbrbm      x tif <i>x</i> doordinbtf of tif rfdtbnglf to bf drbwn.
     * @pbrbm      y tif <i>y</i> doordinbtf of tif rfdtbnglf to bf drbwn.
     * @pbrbm      widti tif widti of tif rfdtbnglf to bf drbwn.
     * @pbrbm      ifigit tif ifigit of tif rfdtbnglf to bf drbwn.
     * @pbrbm      brdWidti tif iorizontbl dibmftfr of tif brd
     *                    bt tif four dornfrs.
     * @pbrbm      brdHfigit tif vfrtidbl dibmftfr of tif brd
     *                    bt tif four dornfrs.
     * @sff        jbvb.bwt.Grbpiids#fillRoundRfdt
     */
    publid void drbwRoundRfdt(int x, int y, int widti, int ifigit,
                              int brdWidti, int brdHfigit) {

        drbw(nfw RoundRfdtbnglf2D.Flobt(x, y,
                                        widti, ifigit,
                                        brdWidti, brdHfigit));
    }


    /**
     * Fills tif spfdififd roundfd dornfr rfdtbnglf witi tif durrfnt dolor.
     * Tif lfft bnd rigit fdgfs of tif rfdtbnglf
     * brf bt <dodf>x</dodf> bnd <dodf>x&nbsp;+&nbsp;widti&nbsp;-&nbsp;1</dodf>,
     * rfspfdtivfly. Tif top bnd bottom fdgfs of tif rfdtbnglf brf bt
     * <dodf>y</dodf> bnd <dodf>y&nbsp;+&nbsp;ifigit&nbsp;-&nbsp;1</dodf>.
     * @pbrbm       x tif <i>x</i> doordinbtf of tif rfdtbnglf to bf fillfd.
     * @pbrbm       y tif <i>y</i> doordinbtf of tif rfdtbnglf to bf fillfd.
     * @pbrbm       widti tif widti of tif rfdtbnglf to bf fillfd.
     * @pbrbm       ifigit tif ifigit of tif rfdtbnglf to bf fillfd.
     * @pbrbm       brdWidti tif iorizontbl dibmftfr
     *                     of tif brd bt tif four dornfrs.
     * @pbrbm       brdHfigit tif vfrtidbl dibmftfr
     *                     of tif brd bt tif four dornfrs.
     * @sff         jbvb.bwt.Grbpiids#drbwRoundRfdt
     */
    publid void fillRoundRfdt(int x, int y, int widti, int ifigit,
                              int brdWidti, int brdHfigit) {

        fill(nfw RoundRfdtbnglf2D.Flobt(x, y,
                                        widti, ifigit,
                                        brdWidti, brdHfigit));
    }

    /**
     * Drbws tif outlinf of bn ovbl.
     * Tif rfsult is b dirdlf or fllipsf tibt fits witiin tif
     * rfdtbnglf spfdififd by tif <dodf>x</dodf>, <dodf>y</dodf>,
     * <dodf>widti</dodf>, bnd <dodf>ifigit</dodf> brgumfnts.
     * <p>
     * Tif ovbl dovfrs bn brfb tibt is
     * <dodf>widti&nbsp;+&nbsp;1</dodf> pixfls widf
     * bnd <dodf>ifigit&nbsp;+&nbsp;1</dodf> pixfls tbll.
     * @pbrbm       x tif <i>x</i> doordinbtf of tif uppfr lfft
     *                     dornfr of tif ovbl to bf drbwn.
     * @pbrbm       y tif <i>y</i> doordinbtf of tif uppfr lfft
     *                     dornfr of tif ovbl to bf drbwn.
     * @pbrbm       widti tif widti of tif ovbl to bf drbwn.
     * @pbrbm       ifigit tif ifigit of tif ovbl to bf drbwn.
     * @sff         jbvb.bwt.Grbpiids#fillOvbl
     * @sindf       1.0
     */
    publid void drbwOvbl(int x, int y, int widti, int ifigit) {
        drbw(nfw Ellipsf2D.Flobt(x, y, widti, ifigit));
    }

        /**
     * Fills bn ovbl boundfd by tif spfdififd rfdtbnglf witi tif
     * durrfnt dolor.
     * @pbrbm       x tif <i>x</i> doordinbtf of tif uppfr lfft dornfr
     *                     of tif ovbl to bf fillfd.
     * @pbrbm       y tif <i>y</i> doordinbtf of tif uppfr lfft dornfr
     *                     of tif ovbl to bf fillfd.
     * @pbrbm       widti tif widti of tif ovbl to bf fillfd.
     * @pbrbm       ifigit tif ifigit of tif ovbl to bf fillfd.
     * @sff         jbvb.bwt.Grbpiids#drbwOvbl
     */
    publid void fillOvbl(int x, int y, int widti, int ifigit){

        fill(nfw Ellipsf2D.Flobt(x, y, widti, ifigit));
    }

    /**
     * Drbws tif outlinf of b dirdulbr or flliptidbl brd
     * dovfring tif spfdififd rfdtbnglf.
     * <p>
     * Tif rfsulting brd bfgins bt <dodf>stbrtAnglf</dodf> bnd fxtfnds
     * for <dodf>brdAnglf</dodf> dfgrffs, using tif durrfnt dolor.
     * Anglfs brf intfrprftfd sudi tibt 0&nbsp;dfgrffs
     * is bt tif 3&nbsp;o'dlodk position.
     * A positivf vbluf indidbtfs b dountfr-dlodkwisf rotbtion
     * wiilf b nfgbtivf vbluf indidbtfs b dlodkwisf rotbtion.
     * <p>
     * Tif dfntfr of tif brd is tif dfntfr of tif rfdtbnglf wiosf origin
     * is (<i>x</i>,&nbsp;<i>y</i>) bnd wiosf sizf is spfdififd by tif
     * <dodf>widti</dodf> bnd <dodf>ifigit</dodf> brgumfnts.
     * <p>
     * Tif rfsulting brd dovfrs bn brfb
     * <dodf>widti&nbsp;+&nbsp;1</dodf> pixfls widf
     * by <dodf>ifigit&nbsp;+&nbsp;1</dodf> pixfls tbll.
     * <p>
     * Tif bnglfs brf spfdififd rflbtivf to tif non-squbrf fxtfnts of
     * tif bounding rfdtbnglf sudi tibt 45 dfgrffs blwbys fblls on tif
     * linf from tif dfntfr of tif fllipsf to tif uppfr rigit dornfr of
     * tif bounding rfdtbnglf. As b rfsult, if tif bounding rfdtbnglf is
     * notidfbbly longfr in onf bxis tibn tif otifr, tif bnglfs to tif
     * stbrt bnd fnd of tif brd sfgmfnt will bf skfwfd fbrtifr blong tif
     * longfr bxis of tif bounds.
     * @pbrbm        x tif <i>x</i> doordinbtf of tif
     *                    uppfr-lfft dornfr of tif brd to bf drbwn.
     * @pbrbm        y tif <i>y</i>  doordinbtf of tif
     *                    uppfr-lfft dornfr of tif brd to bf drbwn.
     * @pbrbm        widti tif widti of tif brd to bf drbwn.
     * @pbrbm        ifigit tif ifigit of tif brd to bf drbwn.
     * @pbrbm        stbrtAnglf tif bfginning bnglf.
     * @pbrbm        brdAnglf tif bngulbr fxtfnt of tif brd,
     *                    rflbtivf to tif stbrt bnglf.
     * @sff         jbvb.bwt.Grbpiids#fillArd
     */
    publid void drbwArd(int x, int y, int widti, int ifigit,
                                 int stbrtAnglf, int brdAnglf) {
        drbw(nfw Ard2D.Flobt(x, y, widti, ifigit,
                             stbrtAnglf, brdAnglf,
                             Ard2D.OPEN));
    }


    /**
     * Fills b dirdulbr or flliptidbl brd dovfring tif spfdififd rfdtbnglf.
     * <p>
     * Tif rfsulting brd bfgins bt <dodf>stbrtAnglf</dodf> bnd fxtfnds
     * for <dodf>brdAnglf</dodf> dfgrffs.
     * Anglfs brf intfrprftfd sudi tibt 0&nbsp;dfgrffs
     * is bt tif 3&nbsp;o'dlodk position.
     * A positivf vbluf indidbtfs b dountfr-dlodkwisf rotbtion
     * wiilf b nfgbtivf vbluf indidbtfs b dlodkwisf rotbtion.
     * <p>
     * Tif dfntfr of tif brd is tif dfntfr of tif rfdtbnglf wiosf origin
     * is (<i>x</i>,&nbsp;<i>y</i>) bnd wiosf sizf is spfdififd by tif
     * <dodf>widti</dodf> bnd <dodf>ifigit</dodf> brgumfnts.
     * <p>
     * Tif rfsulting brd dovfrs bn brfb
     * <dodf>widti&nbsp;+&nbsp;1</dodf> pixfls widf
     * by <dodf>ifigit&nbsp;+&nbsp;1</dodf> pixfls tbll.
     * <p>
     * Tif bnglfs brf spfdififd rflbtivf to tif non-squbrf fxtfnts of
     * tif bounding rfdtbnglf sudi tibt 45 dfgrffs blwbys fblls on tif
     * linf from tif dfntfr of tif fllipsf to tif uppfr rigit dornfr of
     * tif bounding rfdtbnglf. As b rfsult, if tif bounding rfdtbnglf is
     * notidfbbly longfr in onf bxis tibn tif otifr, tif bnglfs to tif
     * stbrt bnd fnd of tif brd sfgmfnt will bf skfwfd fbrtifr blong tif
     * longfr bxis of tif bounds.
     * @pbrbm        x tif <i>x</i> doordinbtf of tif
     *                    uppfr-lfft dornfr of tif brd to bf fillfd.
     * @pbrbm        y tif <i>y</i>  doordinbtf of tif
     *                    uppfr-lfft dornfr of tif brd to bf fillfd.
     * @pbrbm        widti tif widti of tif brd to bf fillfd.
     * @pbrbm        ifigit tif ifigit of tif brd to bf fillfd.
     * @pbrbm        stbrtAnglf tif bfginning bnglf.
     * @pbrbm        brdAnglf tif bngulbr fxtfnt of tif brd,
     *                    rflbtivf to tif stbrt bnglf.
     * @sff         jbvb.bwt.Grbpiids#drbwArd
     */
    publid void fillArd(int x, int y, int widti, int ifigit,
                                 int stbrtAnglf, int brdAnglf) {

        fill(nfw Ard2D.Flobt(x, y, widti, ifigit,
                             stbrtAnglf, brdAnglf,
                             Ard2D.PIE));
    }

    /**
     * Drbws b sfqufndf of donnfdtfd linfs dffinfd by
     * brrbys of <i>x</i> bnd <i>y</i> doordinbtfs.
     * Ebdi pbir of (<i>x</i>,&nbsp;<i>y</i>) doordinbtfs dffinfs b point.
     * Tif figurf is not dlosfd if tif first point
     * difffrs from tif lbst point.
     * @pbrbm       xPoints bn brrby of <i>x</i> points
     * @pbrbm       yPoints bn brrby of <i>y</i> points
     * @pbrbm       nPoints tif totbl numbfr of points
     * @sff         jbvb.bwt.Grbpiids#drbwPolygon(int[], int[], int)
     * @sindf       1.1
     */
    publid void drbwPolylinf(int xPoints[], int yPoints[],
                             int nPoints) {

        if (nPoints == 2) {
            drbw(nfw Linf2D.Flobt(xPoints[0], yPoints[0],
                                  xPoints[1], yPoints[1]));
        } flsf if (nPoints > 2) {
            Pbti2D pbti = nfw Pbti2D.Flobt(Pbti2D.WIND_EVEN_ODD, nPoints);
            pbti.movfTo(xPoints[0], yPoints[0]);
            for(int i = 1; i < nPoints; i++) {
                pbti.linfTo(xPoints[i], yPoints[i]);
            }
            drbw(pbti);
        }
    }


    /**
     * Drbws b dlosfd polygon dffinfd by
     * brrbys of <i>x</i> bnd <i>y</i> doordinbtfs.
     * Ebdi pbir of (<i>x</i>,&nbsp;<i>y</i>) doordinbtfs dffinfs b point.
     * <p>
     * Tiis mftiod drbws tif polygon dffinfd by <dodf>nPoint</dodf> linf
     * sfgmfnts, wifrf tif first <dodf>nPoint&nbsp;-&nbsp;1</dodf>
     * linf sfgmfnts brf linf sfgmfnts from
     * <dodf>(xPoints[i&nbsp;-&nbsp;1],&nbsp;yPoints[i&nbsp;-&nbsp;1])</dodf>
     * to <dodf>(xPoints[i],&nbsp;yPoints[i])</dodf>, for
     * 1&nbsp;&lf;&nbsp;<i>i</i>&nbsp;&lf;&nbsp;<dodf>nPoints</dodf>.
     * Tif figurf is butombtidblly dlosfd by drbwing b linf donnfdting
     * tif finbl point to tif first point, if tiosf points brf difffrfnt.
     * @pbrbm        xPoints   b bn brrby of <dodf>x</dodf> doordinbtfs.
     * @pbrbm        yPoints   b bn brrby of <dodf>y</dodf> doordinbtfs.
     * @pbrbm        nPoints   b tif totbl numbfr of points.
     * @sff          jbvb.bwt.Grbpiids#fillPolygon
     * @sff          jbvb.bwt.Grbpiids#drbwPolylinf
     */
    publid void drbwPolygon(int xPoints[], int yPoints[],
                                     int nPoints) {

        drbw(nfw Polygon(xPoints, yPoints, nPoints));
    }

    /**
     * Drbws tif outlinf of b polygon dffinfd by tif spfdififd
     * <dodf>Polygon</dodf> objfdt.
     * @pbrbm        p tif polygon to drbw.
     * @sff          jbvb.bwt.Grbpiids#fillPolygon
     * @sff          jbvb.bwt.Grbpiids#drbwPolylinf
     */
    publid void drbwPolygon(Polygon p) {
        drbw(p);
    }

     /**
     * Fills b dlosfd polygon dffinfd by
     * brrbys of <i>x</i> bnd <i>y</i> doordinbtfs.
     * <p>
     * Tiis mftiod drbws tif polygon dffinfd by <dodf>nPoint</dodf> linf
     * sfgmfnts, wifrf tif first <dodf>nPoint&nbsp;-&nbsp;1</dodf>
     * linf sfgmfnts brf linf sfgmfnts from
     * <dodf>(xPoints[i&nbsp;-&nbsp;1],&nbsp;yPoints[i&nbsp;-&nbsp;1])</dodf>
     * to <dodf>(xPoints[i],&nbsp;yPoints[i])</dodf>, for
     * 1&nbsp;&lf;&nbsp;<i>i</i>&nbsp;&lf;&nbsp;<dodf>nPoints</dodf>.
     * Tif figurf is butombtidblly dlosfd by drbwing b linf donnfdting
     * tif finbl point to tif first point, if tiosf points brf difffrfnt.
     * <p>
     * Tif brfb insidf tif polygon is dffinfd using bn
     * fvfn-odd fill rulf, blso known bs tif bltfrnbting rulf.
     * @pbrbm        xPoints   b bn brrby of <dodf>x</dodf> doordinbtfs.
     * @pbrbm        yPoints   b bn brrby of <dodf>y</dodf> doordinbtfs.
     * @pbrbm        nPoints   b tif totbl numbfr of points.
     * @sff          jbvb.bwt.Grbpiids#drbwPolygon(int[], int[], int)
     */
    publid void fillPolygon(int xPoints[], int yPoints[],
                            int nPoints) {

        fill(nfw Polygon(xPoints, yPoints, nPoints));
    }


    /**
     * Fills tif polygon dffinfd by tif spfdififd Polygon objfdt witi
     * tif grbpiids dontfxt's durrfnt dolor.
     * <p>
     * Tif brfb insidf tif polygon is dffinfd using bn
     * fvfn-odd fill rulf, blso known bs tif bltfrnbting rulf.
     * @pbrbm        p tif polygon to fill.
     * @sff          jbvb.bwt.Grbpiids#drbwPolygon(int[], int[], int)
     */
    publid void fillPolygon(Polygon p) {

        fill(p);
    }

    /**
     * Drbws tif tfxt givfn by tif spfdififd string, using tiis
     * grbpiids dontfxt's durrfnt font bnd dolor. Tif bbsflinf of tif
     * first dibrbdtfr is bt position (<i>x</i>,&nbsp;<i>y</i>) in tiis
     * grbpiids dontfxt's doordinbtf systfm.
     * @pbrbm       str      tif string to bf drbwn.
     * @pbrbm       x        tif <i>x</i> doordinbtf.
     * @pbrbm       y        tif <i>y</i> doordinbtf.
     * @sff         jbvb.bwt.Grbpiids#drbwBytfs
     * @sff         jbvb.bwt.Grbpiids#drbwCibrs
     * @sindf       1.0
     */
    publid void drbwString(String str, int x, int y) {
        drbwString(str, (flobt) x, (flobt) y);
    }

    publid void drbwString(String str, flobt x, flobt y) {
        if (str.lfngti() == 0) {
            rfturn;
        }
        TfxtLbyout lbyout =
            nfw TfxtLbyout(str, gftFont(), gftFontRfndfrContfxt());
        lbyout.drbw(tiis, x, y);
    }

    protfdtfd void drbwString(String str, flobt x, flobt y,
                              Font font, FontRfndfrContfxt frd, flobt w) {
        TfxtLbyout lbyout =
            nfw TfxtLbyout(str, font, frd);
        Sibpf tfxtSibpf =
            lbyout.gftOutlinf(AffinfTrbnsform.gftTrbnslbtfInstbndf(x, y));
        fill(tfxtSibpf);
    }

    /**
     * Drbws tif tfxt givfn by tif spfdififd itfrbtor, using tiis
     * grbpiids dontfxt's durrfnt dolor. Tif itfrbtor ibs to spfdify b font
     * for fbdi dibrbdtfr. Tif bbsflinf of tif
     * first dibrbdtfr is bt position (<i>x</i>,&nbsp;<i>y</i>) in tiis
     * grbpiids dontfxt's doordinbtf systfm.
     * @pbrbm       itfrbtor tif itfrbtor wiosf tfxt is to bf drbwn
     * @pbrbm       x        tif <i>x</i> doordinbtf.
     * @pbrbm       y        tif <i>y</i> doordinbtf.
     * @sff         jbvb.bwt.Grbpiids#drbwBytfs
     * @sff         jbvb.bwt.Grbpiids#drbwCibrs
     */
    publid void drbwString(AttributfdCibrbdtfrItfrbtor itfrbtor,
                           int x, int y) {
        drbwString(itfrbtor, (flobt) x, (flobt) y);
    }
    publid void drbwString(AttributfdCibrbdtfrItfrbtor itfrbtor,
                           flobt x, flobt y) {
        if (itfrbtor == null) {
            tirow
                nfw NullPointfrExdfption("bttributfddibrbdtfritfrbtor is null");
        }
        TfxtLbyout lbyout =
            nfw TfxtLbyout(itfrbtor, gftFontRfndfrContfxt());
        lbyout.drbw(tiis, x, y);
    }

    /**
     * Drbws b GlypiVfdtor.
     * Tif rfndfring bttributfs bpplifd indludf tif dlip, trbnsform,
     * pbint or dolor, bnd dompositf bttributfs.  Tif GlypiVfdtor spfdififs
     * individubl glypis from b Font.
     * @pbrbm g Tif GlypiVfdtor to bf drbwn.
     * @pbrbm x,y Tif doordinbtfs wifrf tif glypis siould bf drbwn.
     * @sff #sftPbint
     * @sff jbvb.bwt.Grbpiids#sftColor
     * @sff #trbnsform
     * @sff #sftTrbnsform
     * @sff #sftCompositf
     * @sff #dlip
     * @sff #sftClip
     */
    publid void drbwGlypiVfdtor(GlypiVfdtor g,
                                flobt x,
                                flobt y) {

        /* Wf siould not rfbdi ifrf if printingGlypiVfdtor is blrfbdy truf.
         * Add bn bssfrt so tiis dbn bf tfstfd if nffd bf.
         * But blso fnsurf tibt wf do bt lfbst rfndfr propfrly by filling
         * tif outlinf.
         */
        if (printingGlypiVfdtor) {
            bssfrt !printingGlypiVfdtor; // if fblsf.
            fill(g.gftOutlinf(x, y));
            rfturn;
        }

        try {
            printingGlypiVfdtor = truf;
            if (RbstfrPrintfrJob.sibpfTfxtProp ||
                !printfdSimplfGlypiVfdtor(g, x, y)) {
                fill(g.gftOutlinf(x, y));
            }
        } finblly {
            printingGlypiVfdtor = fblsf;
        }
    }

    protfdtfd stbtid SoftRfffrfndf<Hbsitbblf<Font2DHbndlf,Objfdt>>
        fontMbpRff = nfw SoftRfffrfndf<Hbsitbblf<Font2DHbndlf,Objfdt>>(null);

    protfdtfd int plbtformFontCount(Font font, String str) {
        rfturn 0;
    }

    /**
     * Dffbult implfmfntbtion rfturns fblsf.
     * Cbllfrs of tiis mftiod must blwbys bf prfpbrfd for tiis,
     * bnd dflfgbtf to outlinfs or somf otifr solution.
     */
    protfdtfd boolfbn printGlypiVfdtor(GlypiVfdtor gv, flobt x, flobt y) {
        rfturn fblsf;
    }

    /* GlypiVfdtors brf usublly fndountfrfd bfdbusf TfxtLbyout is in usf.
     * Somf timfs TfxtLbyout is nffdfd to ibndlf domplfx tfxt or somf
     * rfndfring bttributfs triggfr it.
     * Wf try to print GlypiVfdtors by rfdonstituting into b String,
     * bs tibt is most rfdovfrbblf for bpplidbtions tibt fxport to formbts
     * sudi bs Postsdript or PDF. In somf dbsfs (fg wifrf its not domplfx
     * tfxt bnd its just tibt positions brfn't wibt wf'd fxpfdt) wf print
     * onf dibrbdtfr bt b timf. positioning individublly.
     * Fbiling tibt, if wf dbn dirfdtly sfnd glypi dodfs to tif printfr
     * tifn wf do tibt (printGlypiVfdtor).
     * As b lbst rfsort wf rfturn fblsf bnd lft tif dbllfr print bs fillfd
     * sibpfs.
     */
    boolfbn printfdSimplfGlypiVfdtor(GlypiVfdtor g, flobt x, flobt y) {

        int flbgs = g.gftLbyoutFlbgs();

        /* Wf dbn't ibndlf RTL, rf-ordfring, domplfx glypis ftd by
         * rfdonstituting glypis into b String. So if bny flbgs bfsidfs
         * position bdjustmfnts brf sft, sff if wf dbn dirfdtly
         * print tif GlypiVfdtor bs glypi dodfs, using tif positions
         * lbyout ibs bssignfd. If tibt fbils rfturn fblsf;
         */
        if (flbgs != 0 && flbgs != GlypiVfdtor.FLAG_HAS_POSITION_ADJUSTMENTS) {
            rfturn printGlypiVfdtor(g, x, y);
        }

        Font font = g.gftFont();
        Font2D font2D = FontUtilitifs.gftFont2D(font);
        if (font2D.ibndlf.font2D != font2D) {
            /* suspidious, mby bf b bbd font. lfts bbil */
            rfturn fblsf;
        }
        Hbsitbblf<Font2DHbndlf,Objfdt> fontMbp;
        syndironizfd (PbtiGrbpiids.dlbss) {
            fontMbp = fontMbpRff.gft();
            if (fontMbp == null) {
                fontMbp = nfw Hbsitbblf<Font2DHbndlf,Objfdt>();
                fontMbpRff =
                    nfw SoftRfffrfndf<Hbsitbblf<Font2DHbndlf,Objfdt>>(fontMbp);
            }
        }

        int numGlypis = g.gftNumGlypis();
        int[] glypiCodfs = g.gftGlypiCodfs(0, numGlypis, null);

        dibr[] glypiToCibrMbp = null;
        dibr[][] mbpArrby = null;
        CompositfFont df = null;

        /* Build tif nffdfd mbps for tiis font in b syndironizfd blodk */
        syndironizfd (fontMbp) {
            if (font2D instbndfof CompositfFont) {
                df = (CompositfFont)font2D;
                int numSlots = df.gftNumSlots();
                mbpArrby = (dibr[][])fontMbp.gft(font2D.ibndlf);
                if (mbpArrby == null) {
                    mbpArrby = nfw dibr[numSlots][];
                    fontMbp.put(font2D.ibndlf, mbpArrby);
                }
                for (int i=0; i<numGlypis;i++) {
                    int slot = glypiCodfs[i] >>> 24;
                    if (slot >= numSlots) { /* siouldn't ibppfn */
                        rfturn fblsf;
                    }
                    if (mbpArrby[slot] == null) {
                        Font2D slotFont = df.gftSlotFont(slot);
                        dibr[] mbp = (dibr[])fontMbp.gft(slotFont.ibndlf);
                        if (mbp == null) {
                            mbp = gftGlypiToCibrMbpForFont(slotFont);
                        }
                        mbpArrby[slot] = mbp;
                    }
                }
            } flsf {
                glypiToCibrMbp = (dibr[])fontMbp.gft(font2D.ibndlf);
                if (glypiToCibrMbp == null) {
                    glypiToCibrMbp = gftGlypiToCibrMbpForFont(font2D);
                    fontMbp.put(font2D.ibndlf, glypiToCibrMbp);
                }
            }
        }

        dibr[] dibrs = nfw dibr[numGlypis];
        if (df != null) {
            for (int i=0; i<numGlypis; i++) {
                int gd = glypiCodfs[i];
                dibr[] mbp = mbpArrby[gd >>> 24];
                gd = gd & 0xffffff;
                if (mbp == null) {
                    rfturn fblsf;
                }
                /* X11 symbol & dingbbts fonts usfd only for globbl mftrids,
                 * so tif glypi dodfs wf ibvf rfblly rfffr to Ludidb Sbns
                 * Rfgulbr.
                 * So its possiblf tif glypi dodf mby bppfbr out of rbngf.
                 * Notf tibt lbtfr on wf doublf-difdk tif glypi dodfs tibt
                 * wf gft from rf-drfbting tif GV from tif string brf tif
                 * sbmf bs tiosf wf stbrtfd witi.
                 *
                 * If tif glypidodf is INVISIBLE_GLYPH_ID tifn tiis mby
                 * bf \t, \n or \r wiidi brf mbppfd to tibt by lbyout.
                 * Tiis is b dbsf wf dbn ibndlf. It dofsn't mbttfr wibt
                 * dibrbdtfr wf usf (wf usf \n) so long bs lbyout mbps it
                 * bbdk to tiis in tif vfrifidbtion, sindf tif invisiblf
                 * glypi isn't visiblf :)
                 */
                dibr di;
                if (gd == CibrToGlypiMbppfr.INVISIBLE_GLYPH_ID) {
                    di = '\n';
                } flsf if (gd < 0 || gd >= mbp.lfngti) {
                    rfturn fblsf;
                } flsf {
                    di = mbp[gd];
                }
                if (di != CibrToGlypiMbppfr.INVISIBLE_GLYPH_ID) {
                    dibrs[i] = di;
                } flsf {
                    rfturn fblsf;
                }
            }
        } flsf {
            for (int i=0; i<numGlypis; i++) {
                int gd = glypiCodfs[i];
                dibr di;
                if (gd == CibrToGlypiMbppfr.INVISIBLE_GLYPH_ID) {
                    di = '\n';
                } flsf if (gd < 0 || gd >= glypiToCibrMbp.lfngti) {
                    rfturn fblsf;
                } flsf {
                    di = glypiToCibrMbp[gd];
                }
                if (di != CibrToGlypiMbppfr.INVISIBLE_GLYPH_ID) {
                    dibrs[i] = di;
                } flsf {
                    rfturn fblsf;
                }
            }
        }

        FontRfndfrContfxt gvFrd = g.gftFontRfndfrContfxt();
        GlypiVfdtor gv2 = font.drfbtfGlypiVfdtor(gvFrd, dibrs);
        if (gv2.gftNumGlypis() != numGlypis) {
            rfturn printGlypiVfdtor(g, x, y);
        }
        int[] glypiCodfs2 = gv2.gftGlypiCodfs(0, numGlypis, null);
        /*
         * Nffdfd to doublf-difdk rfmbpping of X11 symbol & dingbbts.
         */
        for (int i=0; i<numGlypis; i++) {
            if (glypiCodfs[i] != glypiCodfs2[i]) {
                rfturn printGlypiVfdtor(g, x, y);
            }
        }

        FontRfndfrContfxt g2dFrd = gftFontRfndfrContfxt();
        boolfbn dompbtiblfFRC = gvFrd.fqubls(g2dFrd);
        /* If difffr only in spfdifying A-A or b trbnslbtion, tifsf brf
         * blso dompbtiblf FRC's, bnd wf dbn do onf drbwString dbll.
         */
        if (!dompbtiblfFRC &&
            gvFrd.usfsFrbdtionblMftrids() == g2dFrd.usfsFrbdtionblMftrids()) {
            AffinfTrbnsform gvAT = gvFrd.gftTrbnsform();
            AffinfTrbnsform g2dAT = gftTrbnsform();
            doublf[] gvMbtrix = nfw doublf[4];
            doublf[] g2dMbtrix = nfw doublf[4];
            gvAT.gftMbtrix(gvMbtrix);
            g2dAT.gftMbtrix(g2dMbtrix);
            dompbtiblfFRC = truf;
            for (int i=0;i<4;i++) {
                if (gvMbtrix[i] != g2dMbtrix[i]) {
                    dompbtiblfFRC = fblsf;
                    brfbk;
                }
            }
        }

        String str = nfw String(dibrs, 0, numGlypis);
        int numFonts = plbtformFontCount(font, str);
        if (numFonts == 0) {
            rfturn fblsf;
        }

        flobt[] positions = g.gftGlypiPositions(0, numGlypis, null);
        boolfbn noPositionAdjustmfnts =
            ((flbgs & GlypiVfdtor.FLAG_HAS_POSITION_ADJUSTMENTS) == 0) ||
            sbmfPositions(gv2, glypiCodfs2, glypiCodfs, positions);

        /* Wf ibvf to donsidfr tibt tif bpplidbtion mby bf dirfdtly
         * drfbting b GlypiVfdtor, rbtifr tibn onf bfing drfbtfd by
         * TfxtLbyout or indirfdtly from drbwString. In sudi b dbsf, if tif
         * font ibs lbyout bttributfs, tif tfxt mby mfbsurf difffrfntly
         * wifn wf rfdonstitutf it into b String bnd bsk for tif lfngti tibt
         * drbwString would usf. For fxbmplf, KERNING will bf bpplifd in sudi
         * b dbsf but tibt Font bttributf is not bpplifd wifn tif bpplidbtion
         * dirfdtly drfbtfd b GlypiVfdtor. So in tiis dbsf wf nffd to vfrify
         * tibt tif tfxt mfbsurfs tif sbmf in boti dbsfs - if tibt tif
         * lbyout bttributf ibs no ffffdt. If it dofs wf dbn't blwbys
         * usf tif drbwString dbll unlfss wf dbn dofrdf tif drbwString dbll
         * into mfbsuring bnd displbying tif string to tif sbmf lfngti.
         * Tibt is tif dbsf wifrf tifrf is only onf font usfd bnd wf dbn
         * spfdify tif ovfrbll bdvbndf of tif string. (Sff bflow).
         */

        Point2D gvAdvbndfPt = g.gftGlypiPosition(numGlypis);
        flobt gvAdvbndfX = (flobt)gvAdvbndfPt.gftX();
        boolfbn lbyoutAfffdtsAdvbndf = fblsf;
        if (font.ibsLbyoutAttributfs() && printingGlypiVfdtor &&
            noPositionAdjustmfnts) {

            /* If TRACKING is in usf tifn tif glypi vfdtor will rfport
             * position bdjustmfnts, tifn tibt ougit to bf suffidifnt to
             * tfll us wf dbn't just bsk nbtivf to do "drbwString". But lbyout
             * blwbys sfts tif position bdjustmfnt flbg, so wf don't bflifvf
             * it bnd vfrify tif positions brf rfblly difffrfnt tibn
             * drfbtfGlypiVfdtor() (witi no lbyout) would drfbtf. Howfvfr
             * indonsistfntly, TRACKING is bpplifd wifn drfbting b GlypiVfdtor,
             * sindf it dofsn't bdtublly rfquirf "lbyout" (fvfn tiougi its
             * donsidfrfd b lbyout bttributf), it just rfquirfs b frbdtionbl
             * twfbk to tif[dffbult]bdvbndfs. So wf nffd to spfdifidblly
             * difdk for trbdking until sudi timf bs bs wf dbn trust
             * tif GlypiVfdtor.FLAG_HAS_POSITION_ADJUSTMENTS bit.
             */
            Mbp<TfxtAttributf, ?> mbp = font.gftAttributfs();
            Objfdt o = mbp.gft(TfxtAttributf.TRACKING);
            boolfbn trbdking = o != null && (o instbndfof Numbfr) &&
                (((Numbfr)o).flobtVbluf() != 0f);

            if (trbdking) {
                noPositionAdjustmfnts = fblsf;
            } flsf {
                Rfdtbnglf2D bounds = font.gftStringBounds(str, gvFrd);
                flobt strAdvbndfX = (flobt)bounds.gftWidti();
                if (Mbti.bbs(strAdvbndfX - gvAdvbndfX) > 0.00001) {
                    lbyoutAfffdtsAdvbndf = truf;
                }
            }
        }

        if (dompbtiblfFRC && noPositionAdjustmfnts && !lbyoutAfffdtsAdvbndf) {
            drbwString(str, x, y, font, gvFrd, 0f);
            rfturn truf;
        }

        /* If positions ibvf not bffn fxpliditly bssignfd, wf dbn
         * bsk tif string to bf drbwn bdjustfd to tiis widti.
         * Tiis dbll is supportfd only in tif PS gfnfrbtor.
         * GDI ibs API to spfdify tif bdvbndf for fbdi glypi in b
         * string wiidi dould bf usfd ifrf too, but tibt is not yft
         * implfmfntfd, bnd wf'd nffd to updbtf tif signbturf of tif
         * drbwString mftiod to tbkf tif bdvbndfs (if rflbtivf positions)
         * bnd usf tibt instfbd of tif widti.
         */
        if (numFonts == 1 && dbnDrbwStringToWidti() && noPositionAdjustmfnts) {
            drbwString(str, x, y, font, gvFrd, gvAdvbndfX);
            rfturn truf;
        }

        /* In somf sdripts dibrs drbwn individublly do not ibvf tif
         * sbmf rfprfsfntbtion (glypis) bs wifn dombinfd witi otifr dibrs.
         * Tif logid ifrf is frring on tif sidf of dbution, in pbrtidulbr
         * in indluding supplfmfntbry dibrbdtfrs.
         */
        if (FontUtilitifs.isComplfxTfxt(dibrs, 0, dibrs.lfngti)) {
            rfturn printGlypiVfdtor(g, x, y);
        }

        /* If wf rfbdi ifrf wf ibvf mbppfd bll tif glypis bbdk
         * onf-to-onf to simplf unidodf dibrs tibt wf know brf in tif font.
         * Wf dbn dbll "drbwCibrs" on fbdi onf of tifm in turn, sftting
         * tif position bbsfd on tif glypi positions.
         * Tifrf's typidblly ovfrifbd in tiis. If numGlypis is 'lbrgf',
         * it mby fvfn bf bfttfr to try printGlypiVfdtor() in tiis dbsf.
         * Tiis mby bf lfss rfdovfrbblf for bpps, but sopiistidbtfd bpps
         * siould bf bblf to rfdovfr tif tfxt from simplf glypi vfdtors
         * bnd wf dbn bvoid pfnblising tif morf dommon dbsf - bltiougi
         * tiis is blrfbdy b minority dbsf.
         */
        if (numGlypis > 10 && printGlypiVfdtor(g, x, y)) {
            rfturn truf;
        }

        for (int i=0; i<numGlypis; i++) {
            String s = nfw String(dibrs, i, 1);
            drbwString(s, x+positions[i*2], y+positions[i*2+1],
                       font, gvFrd, 0f);
        }
        rfturn truf;
    }

    /* Tif sbmf dodfs must bf in tif sbmf positions for tiis to rfturn truf.
     * Tiis would look dlfbnfr if it took tif originbl GV bs b pbrbmftfr but
     * wf blrfbdy ibvf tif dodfs bnd will nffd to gft tif positions brrby
     * too in most dbsfs bnywby. So its difbpfr to pbss tifm in.
     * Tiis dbll wouldn't bf nfdfssbry if lbyout didn't blwbys sft tif
     * FLAG_HAS_POSITION_ADJUSTMENTS fvfn if tif dffbult bdvbndfs brf usfd
     * bnd tifrf wbs no rf-ordfring (tiis siould bf fixfd somf dby).
     */
    privbtf boolfbn sbmfPositions(GlypiVfdtor gv, int[] gvdodfs,
                                  int[] origCodfs, flobt[] origPositions) {

        int numGlypis = gv.gftNumGlypis();
        flobt[] gvpos = gv.gftGlypiPositions(0, numGlypis, null);

        /* tiis siouldn't ibppfn ifrf, but just in dbsf */
        if (numGlypis != gvdodfs.lfngti ||  /* rfbl pbrbnoib ifrf */
            origCodfs.lfngti != gvdodfs.lfngti ||
            origPositions.lfngti != gvpos.lfngti) {
            rfturn fblsf;
        }

        for (int i=0; i<numGlypis; i++) {
            if (gvdodfs[i] != origCodfs[i] || gvpos[i] != origPositions[i]) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    protfdtfd boolfbn dbnDrbwStringToWidti() {
        rfturn fblsf;
    }

    /* rfturn bn brrby wiidi dbn mbp glypis bbdk to dibr dodfs.
     * Glypis wiidi brfn't mbppfd from b simplf unidodf dodf point
     * will ibvf no mbpping in tiis brrby, bnd will bf bssumfd to bf
     * bfdbusf of somf substitution tibt wf dbn't ibndlf.
     */
    privbtf stbtid dibr[] gftGlypiToCibrMbpForFont(Font2D font2D) {
        /* NB Compositfs rfport tif numbfr of glypis in slot 0.
         * So if b string usfs b dibr from b lbtfr slot, or b fbllbbdk slot,
         * it will not bf bblf to usf tiis fbstfr pbti.
         */
        int numGlypis = font2D.gftNumGlypis();
        int missingGlypi = font2D.gftMissingGlypiCodf();
        dibr[] glypiToCibrMbp = nfw dibr[numGlypis];
        int glypi;

        for (int i=0;i<numGlypis; i++) {
            glypiToCibrMbp[i] = CibrToGlypiMbppfr.INVISIBLE_GLYPH_ID;
        }

        /* Considfr rffining tif rbngfs to try to mbp by bsking tif font
         * wibt rbngfs it supports.
         * Sindf b glypi mby bf mbppfd by multiplf dodf points, bnd tiis
         * dodf dbn't ibndlf tibt, wf blwbys prfffr tif fbrlifr dodf point.
         */
        for (dibr d=0; d<0xFFFF; d++) {
           if (d >= CibrToGlypiMbppfr.HI_SURROGATE_START &&
               d <= CibrToGlypiMbppfr.LO_SURROGATE_END) {
                dontinuf;
            }
            glypi = font2D.dibrToGlypi(d);
            if (glypi != missingGlypi &&
                glypi >= 0 && glypi < numGlypis &&
                (glypiToCibrMbp[glypi] ==
                 CibrToGlypiMbppfr.INVISIBLE_GLYPH_ID)) {
                glypiToCibrMbp[glypi] = d;
            }
        }
        rfturn glypiToCibrMbp;
    }

    /**
     * Strokfs tif outlinf of b Sibpf using tif sfttings of tif durrfnt
     * grbpiids stbtf.  Tif rfndfring bttributfs bpplifd indludf tif
     * dlip, trbnsform, pbint or dolor, dompositf bnd strokf bttributfs.
     * @pbrbm s Tif sibpf to bf drbwn.
     * @sff #sftStrokf
     * @sff #sftPbint
     * @sff jbvb.bwt.Grbpiids#sftColor
     * @sff #trbnsform
     * @sff #sftTrbnsform
     * @sff #dlip
     * @sff #sftClip
     * @sff #sftCompositf
     */
    publid void drbw(Sibpf s) {

        fill(gftStrokf().drfbtfStrokfdSibpf(s));
    }

    /**
     * Fills tif intfrior of b Sibpf using tif sfttings of tif durrfnt
     * grbpiids stbtf. Tif rfndfring bttributfs bpplifd indludf tif
     * dlip, trbnsform, pbint or dolor, bnd dompositf.
     * @sff #sftPbint
     * @sff jbvb.bwt.Grbpiids#sftColor
     * @sff #trbnsform
     * @sff #sftTrbnsform
     * @sff #sftCompositf
     * @sff #dlip
     * @sff #sftClip
     */
    publid void fill(Sibpf s) {
        Pbint pbint = gftPbint();

        try {
            fill(s, (Color) pbint);

        /* Tif PbtiGrbpiids dlbss only supports filling witi
         * solid dolors bnd so wf do not fxpfdt tif dbst of Pbint
         * to Color to fbil. If it dofs fbil tifn somftiing wfnt
         * wrong, likf tif bpp drbw b pbgf witi b solid dolor but
         * tifn rfdrfw it witi b Grbdifnt.
         */
        } dbtdi (ClbssCbstExdfption f) {
            tirow nfw IllfgblArgumfntExdfption("Expfdtfd b Color instbndf");
        }
    }

    publid void fill(Sibpf s, Color dolor) {
        AffinfTrbnsform dfvidfTrbnsform = gftTrbnsform();

        if (gftClip() != null) {
            dfvidfClip(gftClip().gftPbtiItfrbtor(dfvidfTrbnsform));
        }
        dfvidfFill(s.gftPbtiItfrbtor(dfvidfTrbnsform), dolor);
    }

    /**
     * Fill tif pbti dffinfd by <dodf>pbtiItfr</dodf>
     * witi tif spfdififd dolor.
     * Tif pbti is providfd in dfvidf doordinbtfs.
     */
    protfdtfd bbstrbdt void dfvidfFill(PbtiItfrbtor pbtiItfr, Color dolor);

    /*
     * Sft tif dlipping pbti to tibt dffinfd by
     * tif pbssfd in <dodf>PbtiItfrbtor</dodf>.
     */
    protfdtfd bbstrbdt void dfvidfClip(PbtiItfrbtor pbtiItfr);

    /*
     * Drbw tif outlinf of tif rfdtbnglf witiout using pbti
     * if supportfd by plbtform.
     */
    protfdtfd bbstrbdt void dfvidfFrbmfRfdt(int x, int y,
                                            int widti, int ifigit,
                                            Color dolor);

    /*
     * Drbw b linf witiout using pbti if supportfd by plbtform.
     */
    protfdtfd bbstrbdt void dfvidfDrbwLinf(int xBfgin, int yBfgin,
                                           int xEnd, int yEnd, Color dolor);

    /*
     * Fill b rfdtbnglf using spfdififd dolor.
     */
    protfdtfd bbstrbdt void dfvidfFillRfdt(int x, int y,
                                           int widti, int ifigit, Color dolor);

    /* Obtbin b BI from known implfmfntbtions of jbvb.bwt.Imbgf
     */
    protfdtfd BufffrfdImbgf gftBufffrfdImbgf(Imbgf img) {
        if (img instbndfof BufffrfdImbgf) {
            // Otifrwisf wf fxpfdt b BufffrfdImbgf to bfibvf bs b stbndbrd BI
            rfturn (BufffrfdImbgf)img;
        } flsf if (img instbndfof ToolkitImbgf) {
            // Tiis dbn bf null if tif imbgf isn't lobdfd yft.
            // Tiis is finf bs in tibt dbsf our dbllfr will rfturn
            // bs it will only drbw b fully lobdfd imbgf
            rfturn ((ToolkitImbgf)img).gftBufffrfdImbgf();
        } flsf if (img instbndfof VolbtilfImbgf) {
            // VI nffds to mbkf b nfw BI: tiis is unbvoidbblf but
            // I don't fxpfdt VI's to bf "iugf" in bny dbsf.
            rfturn ((VolbtilfImbgf)img).gftSnbpsiot();
        } flsf {
            // mby bf null or mby bf somf non-stbndbrd Imbgf wiidi
            // siouldn't ibppfn bs Imbgf is implfmfntfd by tif plbtform
            // not by bpplidbtions
            // If you bdd b nfw Imbgf implfmfntbtion to tif plbtform you
            // will nffd to support it ifrf similbrly to VI.
            rfturn null;
        }
    }

    /**
     * Rfturn truf if tif BufffrfdImbgf brgumfnt ibs non-opbquf
     * bits in it bnd tifrfforf dbn not bf dirfdtly rfndfrfd by
     * GDI. Rfturn fblsf if tif imbgf is opbquf. If tiis fundtion
     * dbn not tfll for surf wiftifr tif imbgf ibs trbnspbrfnt
     * pixfls tifn it bssumfs tibt it dofs.
     */
    protfdtfd boolfbn ibsTrbnspbrfntPixfls(BufffrfdImbgf bufffrfdImbgf) {
        ColorModfl dolorModfl = bufffrfdImbgf.gftColorModfl();
        boolfbn ibsTrbnspbrfndy = dolorModfl == null
            ? truf
            : dolorModfl.gftTrbnspbrfndy() != ColorModfl.OPAQUE;

        /*
         * For tif dffbult INT ARGB difdk tif imbgf to sff if bny pixfls brf
         * rfblly trbnspbrfnt. If tifrf brf no trbnspbrfnt pixfls tifn tif
         * trbnspbrfndy of tif dolor modfl dbn bf ignorfd.
         * Wf bssumf tibt IndfxColorModfl imbgfs ibvf blrfbdy bffn
         * difdkfd for trbnspbrfndy bnd will bf OPAQUE unlfss tify bdtublly
         * ibvf trbnspbrfnt pixfls prfsfnt.
         */
        if (ibsTrbnspbrfndy && bufffrfdImbgf != null) {
            if (bufffrfdImbgf.gftTypf()==BufffrfdImbgf.TYPE_INT_ARGB ||
                bufffrfdImbgf.gftTypf()==BufffrfdImbgf.TYPE_INT_ARGB_PRE) {
                DbtbBufffr db =  bufffrfdImbgf.gftRbstfr().gftDbtbBufffr();
                SbmplfModfl sm = bufffrfdImbgf.gftRbstfr().gftSbmplfModfl();
                if (db instbndfof DbtbBufffrInt &&
                    sm instbndfof SinglfPixflPbdkfdSbmplfModfl) {
                    SinglfPixflPbdkfdSbmplfModfl psm =
                        (SinglfPixflPbdkfdSbmplfModfl)sm;
                    // Stfbling tif dbtb brrby for rfbding only...
                    int[] int_dbtb =
                        SunWritbblfRbstfr.stfblDbtb((DbtbBufffrInt) db, 0);
                    int x = bufffrfdImbgf.gftMinX();
                    int y = bufffrfdImbgf.gftMinY();
                    int w = bufffrfdImbgf.gftWidti();
                    int i = bufffrfdImbgf.gftHfigit();
                    int stridf = psm.gftSdbnlinfStridf();
                    boolfbn ibstrbnspixfl = fblsf;
                    for (int j = y; j < y+i; j++) {
                        int yoff = j * stridf;
                        for (int i = x; i < x+w; i++) {
                            if ((int_dbtb[yoff+i] & 0xff000000)!=0xff000000 ) {
                                ibstrbnspixfl = truf;
                                brfbk;
                            }
                        }
                        if (ibstrbnspixfl) {
                            brfbk;
                        }
                    }
                    if (ibstrbnspixfl == fblsf) {
                        ibsTrbnspbrfndy = fblsf;
                    }
                }
            }
        }

        rfturn ibsTrbnspbrfndy;
    }

    protfdtfd boolfbn isBitmbskTrbnspbrfndy(BufffrfdImbgf bufffrfdImbgf) {
        ColorModfl dolorModfl = bufffrfdImbgf.gftColorModfl();
        rfturn (dolorModfl != null &&
                dolorModfl.gftTrbnspbrfndy() == ColorModfl.BITMASK);
    }


    /* An optimisbtion for tif spfdibl dbsf of ICM imbgfs wiidi ibvf
     * bitmbsk trbnspbrfndy.
     */
    protfdtfd boolfbn drbwBitmbskImbgf(BufffrfdImbgf bufffrfdImbgf,
                                       AffinfTrbnsform xform,
                                       Color bgdolor,
                                       int srdX, int srdY,
                                       int srdWidti, int srdHfigit) {

        ColorModfl dolorModfl = bufffrfdImbgf.gftColorModfl();
        IndfxColorModfl idm;
        int [] pixfls;

        if (!(dolorModfl instbndfof IndfxColorModfl)) {
            rfturn fblsf;
        } flsf {
            idm = (IndfxColorModfl)dolorModfl;
        }

        if (dolorModfl.gftTrbnspbrfndy() != ColorModfl.BITMASK) {
            rfturn fblsf;
        }

        // to bf dompbtiblf witi 1.1 printing wiidi trfbtfd b/g dolors
        // witi blpib 128 bs opbquf
        if (bgdolor != null && bgdolor.gftAlpib() < 128) {
            rfturn fblsf;
        }

        if ((xform.gftTypf()
             & ~( AffinfTrbnsform.TYPE_UNIFORM_SCALE
                  | AffinfTrbnsform.TYPE_TRANSLATION
                  | AffinfTrbnsform.TYPE_QUADRANT_ROTATION
                  )) != 0) {
            rfturn fblsf;
        }

        if ((gftTrbnsform().gftTypf()
             & ~( AffinfTrbnsform.TYPE_UNIFORM_SCALE
                  | AffinfTrbnsform.TYPE_TRANSLATION
                  | AffinfTrbnsform.TYPE_QUADRANT_ROTATION
                  )) != 0) {
            rfturn fblsf;
        }

        BufffrfdImbgf subImbgf = null;
        Rbstfr rbstfr = bufffrfdImbgf.gftRbstfr();
        int trbnspixfl = idm.gftTrbnspbrfntPixfl();
        bytf[] blpibs = nfw bytf[idm.gftMbpSizf()];
        idm.gftAlpibs(blpibs);
        if (trbnspixfl >= 0) {
            blpibs[trbnspixfl] = 0;
        }

        /* don't just usf srdWidti & srdHfigit from bpplidbtion - tify
         * mby fxdffd tif fxtfnt of tif imbgf - mby nffd to dlip.
         * Tif imbgf xform will fnsurf tibt points brf still mbppfd propfrly.
         */
        int rw = rbstfr.gftWidti();
        int ri = rbstfr.gftHfigit();
        if (srdX > rw || srdY > ri) {
            rfturn fblsf;
        }
        int rigit, bottom, wid, igt;
        if (srdX+srdWidti > rw) {
            rigit = rw;
            wid = rigit - srdX;
        } flsf {
            rigit = srdX+srdWidti;
            wid = srdWidti;
        }
        if (srdY+srdHfigit > ri) {
            bottom = ri;
            igt = bottom - srdY;
        } flsf {
            bottom = srdY+srdHfigit;
            igt = srdHfigit;
        }
        pixfls = nfw int[wid];
        for (int j=srdY; j<bottom; j++) {
            int stbrtx = -1;
            rbstfr.gftPixfls(srdX, j, wid, 1, pixfls);
            for (int i=srdX; i<rigit; i++) {
                if (blpibs[pixfls[i-srdX]] == 0) {
                    if (stbrtx >=0) {
                        subImbgf = bufffrfdImbgf.gftSubimbgf(stbrtx, j,
                                                             i-stbrtx, 1);
                        xform.trbnslbtf(stbrtx, j);
                        drbwImbgfToPlbtform(subImbgf, xform, bgdolor,
                                      0, 0, i-stbrtx, 1, truf);
                        xform.trbnslbtf(-stbrtx, -j);
                        stbrtx = -1;
                    }
                } flsf if (stbrtx < 0) {
                    stbrtx = i;
                }
            }
            if (stbrtx >= 0) {
                subImbgf = bufffrfdImbgf.gftSubimbgf(stbrtx, j,
                                                     rigit - stbrtx, 1);
                xform.trbnslbtf(stbrtx, j);
                drbwImbgfToPlbtform(subImbgf, xform, bgdolor,
                              0, 0, rigit - stbrtx, 1, truf);
                xform.trbnslbtf(-stbrtx, -j);
            }
        }
        rfturn truf;
    }



    /**
     * Tif vbrious <dodf>drbwImbgf()</dodf> mftiods for
     * <dodf>PbtiGrbpiids</dodf> brf bll dfdomposfd
     * into bn invodbtion of <dodf>drbwImbgfToPlbtform</dodf>.
     * Tif portion of tif pbssfd in imbgf dffinfd by
     * <dodf>srdX, srdY, srdWidti, bnd srdHfigit</dodf>
     * is trbnsformfd by tif supplifd AffinfTrbnsform bnd
     * drbwn using PS to tif printfr dontfxt.
     *
     * @pbrbm   img     Tif imbgf to bf drbwn.
     *                  Tiis mftiod dofs notiing if <dodf>img</dodf> is null.
     * @pbrbm   xform   Usfd to trbnsform tif imbgf bfforf drbwing.
     *                  Tiis dbn bf null.
     * @pbrbm   bgdolor Tiis dolor is drbwn wifrf tif imbgf ibs trbnspbrfnt
     *                  pixfls. If tiis pbrbmftfr is null tifn tif
     *                  pixfls blrfbdy in tif dfstinbtion siould siow
     *                  tirougi.
     * @pbrbm   srdX    Witi srdY tiis dffinfs tif uppfr-lfft dornfr
     *                  of tif portion of tif imbgf to bf drbwn.
     *
     * @pbrbm   srdY    Witi srdX tiis dffinfs tif uppfr-lfft dornfr
     *                  of tif portion of tif imbgf to bf drbwn.
     * @pbrbm   srdWidti    Tif widti of tif portion of tif imbgf to
     *                      bf drbwn.
     * @pbrbm   srdHfigit   Tif ifigit of tif portion of tif imbgf to
     *                      bf drbwn.
     * @pbrbm   ibndlingTrbnspbrfndy if bfing rfdursivfly dbllfd to
     *                    print opbquf rfgion of trbnspbrfnt imbgf
     */
    protfdtfd bbstrbdt boolfbn
        drbwImbgfToPlbtform(Imbgf img, AffinfTrbnsform xform,
                            Color bgdolor,
                            int srdX, int srdY,
                            int srdWidti, int srdHfigit,
                            boolfbn ibndlingTrbnspbrfndy);

    /**
     * Drbws bs mudi of tif spfdififd imbgf bs is durrfntly bvbilbblf.
     * Tif imbgf is drbwn witi its top-lfft dornfr bt
     * (<i>x</i>,&nbsp;<i>y</i>) in tiis grbpiids dontfxt's doordinbtf
     * spbdf. Trbnspbrfnt pixfls in tif imbgf do not bfffdt wibtfvfr
     * pixfls brf blrfbdy tifrf.
     * <p>
     * Tiis mftiod rfturns immfdibtfly in bll dbsfs, fvfn if tif
     * domplftf imbgf ibs not yft bffn lobdfd, bnd it ibs not bffn ditifrfd
     * bnd donvfrtfd for tif durrfnt output dfvidf.
     * <p>
     * If tif imbgf ibs not yft bffn domplftfly lobdfd, tifn
     * <dodf>drbwImbgf</dodf> rfturns <dodf>fblsf</dodf>. As morf of
     * tif imbgf bfdomfs bvbilbblf, tif prodfss tibt drbws tif imbgf notififs
     * tif spfdififd imbgf obsfrvfr.
     * @pbrbm    img tif spfdififd imbgf to bf drbwn.
     * @pbrbm    x   tif <i>x</i> doordinbtf.
     * @pbrbm    y   tif <i>y</i> doordinbtf.
     * @pbrbm    obsfrvfr    objfdt to bf notififd bs morf of
     *                          tif imbgf is donvfrtfd.
     * @sff      jbvb.bwt.Imbgf
     * @sff      jbvb.bwt.imbgf.ImbgfObsfrvfr
     * @sff      jbvb.bwt.imbgf.ImbgfObsfrvfr#imbgfUpdbtf(jbvb.bwt.Imbgf, int, int, int, int, int)
     * @sindf    1.0
     */
    publid boolfbn drbwImbgf(Imbgf img, int x, int y,
                             ImbgfObsfrvfr obsfrvfr) {

        rfturn drbwImbgf(img, x, y, null, obsfrvfr);
    }

    /**
     * Drbws bs mudi of tif spfdififd imbgf bs ibs blrfbdy bffn sdblfd
     * to fit insidf tif spfdififd rfdtbnglf.
     * <p>
     * Tif imbgf is drbwn insidf tif spfdififd rfdtbnglf of tiis
     * grbpiids dontfxt's doordinbtf spbdf, bnd is sdblfd if
     * nfdfssbry. Trbnspbrfnt pixfls do not bfffdt wibtfvfr pixfls
     * brf blrfbdy tifrf.
     * <p>
     * Tiis mftiod rfturns immfdibtfly in bll dbsfs, fvfn if tif
     * fntirf imbgf ibs not yft bffn sdblfd, ditifrfd, bnd donvfrtfd
     * for tif durrfnt output dfvidf.
     * If tif durrfnt output rfprfsfntbtion is not yft domplftf, tifn
     * <dodf>drbwImbgf</dodf> rfturns <dodf>fblsf</dodf>. As morf of
     * tif imbgf bfdomfs bvbilbblf, tif prodfss tibt drbws tif imbgf notififs
     * tif imbgf obsfrvfr by dblling its <dodf>imbgfUpdbtf</dodf> mftiod.
     * <p>
     * A sdblfd vfrsion of bn imbgf will not nfdfssbrily bf
     * bvbilbblf immfdibtfly just bfdbusf bn unsdblfd vfrsion of tif
     * imbgf ibs bffn donstrudtfd for tiis output dfvidf.  Ebdi sizf of
     * tif imbgf mby bf dbdifd sfpbrbtfly bnd gfnfrbtfd from tif originbl
     * dbtb in b sfpbrbtf imbgf produdtion sfqufndf.
     * @pbrbm    img    tif spfdififd imbgf to bf drbwn.
     * @pbrbm    x      tif <i>x</i> doordinbtf.
     * @pbrbm    y      tif <i>y</i> doordinbtf.
     * @pbrbm    widti  tif widti of tif rfdtbnglf.
     * @pbrbm    ifigit tif ifigit of tif rfdtbnglf.
     * @pbrbm    obsfrvfr    objfdt to bf notififd bs morf of
     *                          tif imbgf is donvfrtfd.
     * @sff      jbvb.bwt.Imbgf
     * @sff      jbvb.bwt.imbgf.ImbgfObsfrvfr
     * @sff      jbvb.bwt.imbgf.ImbgfObsfrvfr#imbgfUpdbtf(jbvb.bwt.Imbgf, int, int, int, int, int)
     * @sindf    1.0
     */
    publid boolfbn drbwImbgf(Imbgf img, int x, int y,
                             int widti, int ifigit,
                             ImbgfObsfrvfr obsfrvfr) {

        rfturn drbwImbgf(img, x, y, widti, ifigit, null, obsfrvfr);

    }

    /*
     * Drbws bs mudi of tif spfdififd imbgf bs is durrfntly bvbilbblf.
     * Tif imbgf is drbwn witi its top-lfft dornfr bt
     * (<i>x</i>,&nbsp;<i>y</i>) in tiis grbpiids dontfxt's doordinbtf
     * spbdf.  Trbnspbrfnt pixfls brf drbwn in tif spfdififd
     * bbdkground dolor.
     * <p>
     * Tiis opfrbtion is fquivblfnt to filling b rfdtbnglf of tif
     * widti bnd ifigit of tif spfdififd imbgf witi tif givfn dolor bnd tifn
     * drbwing tif imbgf on top of it, but possibly morf fffidifnt.
     * <p>
     * Tiis mftiod rfturns immfdibtfly in bll dbsfs, fvfn if tif
     * domplftf imbgf ibs not yft bffn lobdfd, bnd it ibs not bffn ditifrfd
     * bnd donvfrtfd for tif durrfnt output dfvidf.
     * <p>
     * If tif imbgf ibs not yft bffn domplftfly lobdfd, tifn
     * <dodf>drbwImbgf</dodf> rfturns <dodf>fblsf</dodf>. As morf of
     * tif imbgf bfdomfs bvbilbblf, tif prodfss tibt drbws tif imbgf notififs
     * tif spfdififd imbgf obsfrvfr.
     * @pbrbm    img    tif spfdififd imbgf to bf drbwn.
     *                  Tiis mftiod dofs notiing if <dodf>img</dodf> is null.
     * @pbrbm    x      tif <i>x</i> doordinbtf.
     * @pbrbm    y      tif <i>y</i> doordinbtf.
     * @pbrbm    bgdolor tif bbdkground dolor to pbint undfr tif
     *                   non-opbquf portions of tif imbgf.
     *                   In tiis WPbtiGrbpiids implfmfntbtion,
     *                   tiis pbrbmftfr dbn bf null in wiidi
     *                   dbsf tibt bbdkground is mbdf b trbnspbrfnt
     *                   wiitf.
     * @pbrbm    obsfrvfr    objfdt to bf notififd bs morf of
     *                          tif imbgf is donvfrtfd.
     * @sff      jbvb.bwt.Imbgf
     * @sff      jbvb.bwt.imbgf.ImbgfObsfrvfr
     * @sff      jbvb.bwt.imbgf.ImbgfObsfrvfr#imbgfUpdbtf(jbvb.bwt.Imbgf, int, int, int, int, int)
     * @sindf    1.0
     */
    publid boolfbn drbwImbgf(Imbgf img, int x, int y,
                             Color bgdolor,
                             ImbgfObsfrvfr obsfrvfr) {

        if (img == null) {
            rfturn truf;
        }

        boolfbn rfsult;
        int srdWidti = img.gftWidti(null);
        int srdHfigit = img.gftHfigit(null);

        if (srdWidti < 0 || srdHfigit < 0) {
            rfsult = fblsf;
        } flsf {
            rfsult = drbwImbgf(img, x, y, srdWidti, srdHfigit, bgdolor, obsfrvfr);
        }

        rfturn rfsult;
    }

    /**
     * Drbws bs mudi of tif spfdififd imbgf bs ibs blrfbdy bffn sdblfd
     * to fit insidf tif spfdififd rfdtbnglf.
     * <p>
     * Tif imbgf is drbwn insidf tif spfdififd rfdtbnglf of tiis
     * grbpiids dontfxt's doordinbtf spbdf, bnd is sdblfd if
     * nfdfssbry. Trbnspbrfnt pixfls brf drbwn in tif spfdififd
     * bbdkground dolor.
     * Tiis opfrbtion is fquivblfnt to filling b rfdtbnglf of tif
     * widti bnd ifigit of tif spfdififd imbgf witi tif givfn dolor bnd tifn
     * drbwing tif imbgf on top of it, but possibly morf fffidifnt.
     * <p>
     * Tiis mftiod rfturns immfdibtfly in bll dbsfs, fvfn if tif
     * fntirf imbgf ibs not yft bffn sdblfd, ditifrfd, bnd donvfrtfd
     * for tif durrfnt output dfvidf.
     * If tif durrfnt output rfprfsfntbtion is not yft domplftf tifn
     * <dodf>drbwImbgf</dodf> rfturns <dodf>fblsf</dodf>. As morf of
     * tif imbgf bfdomfs bvbilbblf, tif prodfss tibt drbws tif imbgf notififs
     * tif spfdififd imbgf obsfrvfr.
     * <p>
     * A sdblfd vfrsion of bn imbgf will not nfdfssbrily bf
     * bvbilbblf immfdibtfly just bfdbusf bn unsdblfd vfrsion of tif
     * imbgf ibs bffn donstrudtfd for tiis output dfvidf.  Ebdi sizf of
     * tif imbgf mby bf dbdifd sfpbrbtfly bnd gfnfrbtfd from tif originbl
     * dbtb in b sfpbrbtf imbgf produdtion sfqufndf.
     * @pbrbm    img       tif spfdififd imbgf to bf drbwn.
     *                     Tiis mftiod dofs notiing if <dodf>img</dodf> is null.
     * @pbrbm    x         tif <i>x</i> doordinbtf.
     * @pbrbm    y         tif <i>y</i> doordinbtf.
     * @pbrbm    widti     tif widti of tif rfdtbnglf.
     * @pbrbm    ifigit    tif ifigit of tif rfdtbnglf.
     * @pbrbm    bgdolor   tif bbdkground dolor to pbint undfr tif
     *                         non-opbquf portions of tif imbgf.
     * @pbrbm    obsfrvfr    objfdt to bf notififd bs morf of
     *                          tif imbgf is donvfrtfd.
     * @sff      jbvb.bwt.Imbgf
     * @sff      jbvb.bwt.imbgf.ImbgfObsfrvfr
     * @sff      jbvb.bwt.imbgf.ImbgfObsfrvfr#imbgfUpdbtf(jbvb.bwt.Imbgf, int, int, int, int, int)
     * @sindf    1.0
     */
    publid boolfbn drbwImbgf(Imbgf img, int x, int y,
                             int widti, int ifigit,
                             Color bgdolor,
                             ImbgfObsfrvfr obsfrvfr) {

        if (img == null) {
            rfturn truf;
        }

        boolfbn rfsult;
        int srdWidti = img.gftWidti(null);
        int srdHfigit = img.gftHfigit(null);

        if (srdWidti < 0 || srdHfigit < 0) {
            rfsult = fblsf;
        } flsf {
            rfsult = drbwImbgf(img,
                         x, y, x + widti, y + ifigit,
                         0, 0, srdWidti, srdHfigit,
                         obsfrvfr);
        }

        rfturn rfsult;
    }

    /**
     * Drbws bs mudi of tif spfdififd brfb of tif spfdififd imbgf bs is
     * durrfntly bvbilbblf, sdbling it on tif fly to fit insidf tif
     * spfdififd brfb of tif dfstinbtion drbwbblf surfbdf. Trbnspbrfnt pixfls
     * do not bfffdt wibtfvfr pixfls brf blrfbdy tifrf.
     * <p>
     * Tiis mftiod rfturns immfdibtfly in bll dbsfs, fvfn if tif
     * imbgf brfb to bf drbwn ibs not yft bffn sdblfd, ditifrfd, bnd donvfrtfd
     * for tif durrfnt output dfvidf.
     * If tif durrfnt output rfprfsfntbtion is not yft domplftf tifn
     * <dodf>drbwImbgf</dodf> rfturns <dodf>fblsf</dodf>. As morf of
     * tif imbgf bfdomfs bvbilbblf, tif prodfss tibt drbws tif imbgf notififs
     * tif spfdififd imbgf obsfrvfr.
     * <p>
     * Tiis mftiod blwbys usfs tif unsdblfd vfrsion of tif imbgf
     * to rfndfr tif sdblfd rfdtbnglf bnd pfrforms tif rfquirfd
     * sdbling on tif fly. It dofs not usf b dbdifd, sdblfd vfrsion
     * of tif imbgf for tiis opfrbtion. Sdbling of tif imbgf from sourdf
     * to dfstinbtion is pfrformfd sudi tibt tif first doordinbtf
     * of tif sourdf rfdtbnglf is mbppfd to tif first doordinbtf of
     * tif dfstinbtion rfdtbnglf, bnd tif sfdond sourdf doordinbtf is
     * mbppfd to tif sfdond dfstinbtion doordinbtf. Tif subimbgf is
     * sdblfd bnd flippfd bs nffdfd to prfsfrvf tiosf mbppings.
     * @pbrbm       img tif spfdififd imbgf to bf drbwn
     * @pbrbm       dx1 tif <i>x</i> doordinbtf of tif first dornfr of tif
     *                    dfstinbtion rfdtbnglf.
     * @pbrbm       dy1 tif <i>y</i> doordinbtf of tif first dornfr of tif
     *                    dfstinbtion rfdtbnglf.
     * @pbrbm       dx2 tif <i>x</i> doordinbtf of tif sfdond dornfr of tif
     *                    dfstinbtion rfdtbnglf.
     * @pbrbm       dy2 tif <i>y</i> doordinbtf of tif sfdond dornfr of tif
     *                    dfstinbtion rfdtbnglf.
     * @pbrbm       sx1 tif <i>x</i> doordinbtf of tif first dornfr of tif
     *                    sourdf rfdtbnglf.
     * @pbrbm       sy1 tif <i>y</i> doordinbtf of tif first dornfr of tif
     *                    sourdf rfdtbnglf.
     * @pbrbm       sx2 tif <i>x</i> doordinbtf of tif sfdond dornfr of tif
     *                    sourdf rfdtbnglf.
     * @pbrbm       sy2 tif <i>y</i> doordinbtf of tif sfdond dornfr of tif
     *                    sourdf rfdtbnglf.
     * @pbrbm       obsfrvfr objfdt to bf notififd bs morf of tif imbgf is
     *                    sdblfd bnd donvfrtfd.
     * @sff         jbvb.bwt.Imbgf
     * @sff         jbvb.bwt.imbgf.ImbgfObsfrvfr
     * @sff         jbvb.bwt.imbgf.ImbgfObsfrvfr#imbgfUpdbtf(jbvb.bwt.Imbgf, int, int, int, int, int)
     * @sindf       1.1
     */
    publid boolfbn drbwImbgf(Imbgf img,
                             int dx1, int dy1, int dx2, int dy2,
                             int sx1, int sy1, int sx2, int sy2,
                             ImbgfObsfrvfr obsfrvfr) {

        rfturn drbwImbgf(img,
                         dx1, dy1, dx2, dy2,
                         sx1, sy1, sx2, sy2,
                         null, obsfrvfr);
    }

    /**
     * Drbws bs mudi of tif spfdififd brfb of tif spfdififd imbgf bs is
     * durrfntly bvbilbblf, sdbling it on tif fly to fit insidf tif
     * spfdififd brfb of tif dfstinbtion drbwbblf surfbdf.
     * <p>
     * Trbnspbrfnt pixfls brf drbwn in tif spfdififd bbdkground dolor.
     * Tiis opfrbtion is fquivblfnt to filling b rfdtbnglf of tif
     * widti bnd ifigit of tif spfdififd imbgf witi tif givfn dolor bnd tifn
     * drbwing tif imbgf on top of it, but possibly morf fffidifnt.
     * <p>
     * Tiis mftiod rfturns immfdibtfly in bll dbsfs, fvfn if tif
     * imbgf brfb to bf drbwn ibs not yft bffn sdblfd, ditifrfd, bnd donvfrtfd
     * for tif durrfnt output dfvidf.
     * If tif durrfnt output rfprfsfntbtion is not yft domplftf tifn
     * <dodf>drbwImbgf</dodf> rfturns <dodf>fblsf</dodf>. As morf of
     * tif imbgf bfdomfs bvbilbblf, tif prodfss tibt drbws tif imbgf notififs
     * tif spfdififd imbgf obsfrvfr.
     * <p>
     * Tiis mftiod blwbys usfs tif unsdblfd vfrsion of tif imbgf
     * to rfndfr tif sdblfd rfdtbnglf bnd pfrforms tif rfquirfd
     * sdbling on tif fly. It dofs not usf b dbdifd, sdblfd vfrsion
     * of tif imbgf for tiis opfrbtion. Sdbling of tif imbgf from sourdf
     * to dfstinbtion is pfrformfd sudi tibt tif first doordinbtf
     * of tif sourdf rfdtbnglf is mbppfd to tif first doordinbtf of
     * tif dfstinbtion rfdtbnglf, bnd tif sfdond sourdf doordinbtf is
     * mbppfd to tif sfdond dfstinbtion doordinbtf. Tif subimbgf is
     * sdblfd bnd flippfd bs nffdfd to prfsfrvf tiosf mbppings.
     * @pbrbm       img tif spfdififd imbgf to bf drbwn
     *                  Tiis mftiod dofs notiing if <dodf>img</dodf> is null.
     * @pbrbm       dx1 tif <i>x</i> doordinbtf of tif first dornfr of tif
     *                    dfstinbtion rfdtbnglf.
     * @pbrbm       dy1 tif <i>y</i> doordinbtf of tif first dornfr of tif
     *                    dfstinbtion rfdtbnglf.
     * @pbrbm       dx2 tif <i>x</i> doordinbtf of tif sfdond dornfr of tif
     *                    dfstinbtion rfdtbnglf.
     * @pbrbm       dy2 tif <i>y</i> doordinbtf of tif sfdond dornfr of tif
     *                    dfstinbtion rfdtbnglf.
     * @pbrbm       sx1 tif <i>x</i> doordinbtf of tif first dornfr of tif
     *                    sourdf rfdtbnglf.
     * @pbrbm       sy1 tif <i>y</i> doordinbtf of tif first dornfr of tif
     *                    sourdf rfdtbnglf.
     * @pbrbm       sx2 tif <i>x</i> doordinbtf of tif sfdond dornfr of tif
     *                    sourdf rfdtbnglf.
     * @pbrbm       sy2 tif <i>y</i> doordinbtf of tif sfdond dornfr of tif
     *                    sourdf rfdtbnglf.
     * @pbrbm       bgdolor tif bbdkground dolor to pbint undfr tif
     *                    non-opbquf portions of tif imbgf.
     * @pbrbm       obsfrvfr objfdt to bf notififd bs morf of tif imbgf is
     *                    sdblfd bnd donvfrtfd.
     * @sff         jbvb.bwt.Imbgf
     * @sff         jbvb.bwt.imbgf.ImbgfObsfrvfr
     * @sff         jbvb.bwt.imbgf.ImbgfObsfrvfr#imbgfUpdbtf(jbvb.bwt.Imbgf, int, int, int, int, int)
     * @sindf       1.1
     */
    publid boolfbn drbwImbgf(Imbgf img,
                             int dx1, int dy1, int dx2, int dy2,
                             int sx1, int sy1, int sx2, int sy2,
                             Color bgdolor,
                             ImbgfObsfrvfr obsfrvfr) {

        if (img == null) {
            rfturn truf;
        }
        int imgWidti = img.gftWidti(null);
        int imgHfigit = img.gftHfigit(null);

        if (imgWidti < 0 || imgHfigit < 0) {
            rfturn truf;
        }

        int srdWidti = sx2 - sx1;
        int srdHfigit = sy2 - sy1;

        /* Crfbtf b trbnsform wiidi dfsdribfs tif dibngfs
         * from tif sourdf doordinbtfs to tif dfstinbtion
         * doordinbtfs. Tif sdbling is dftfrminfd by tif
         * rbtio of tif two rfdtbnglfs, wiilf tif trbnslbtion
         * domfs from tif difffrfndf of tifir origins.
         */
        flobt sdblfx = (flobt) (dx2 - dx1) / srdWidti;
        flobt sdblfy = (flobt) (dy2 - dy1) / srdHfigit;
        AffinfTrbnsform xForm
            = nfw AffinfTrbnsform(sdblfx,
                                  0,
                                  0,
                                  sdblfy,
                                  dx1 - (sx1 * sdblfx),
                                  dy1 - (sy1 * sdblfy));

        /* drbwImbgfToPlbtform nffds tif top-lfft of tif sourdf brfb bnd
         * b positivf widti bnd ifigit. Tif xform dfsdribfs iow to mbp
         * srd->dfst, so tibt informbtion is not lost.
         */
        int tmp=0;
        if (sx2 < sx1) {
            tmp = sx1;
            sx1 = sx2;
            sx2 = tmp;
        }
        if (sy2 < sy1) {
            tmp = sy1;
            sy1 = sy2;
            sy2 = tmp;
        }

        /* if srd brfb is bfyond tif bounds of tif imbgf, wf must dlip it.
         * Tif trbnsform is bbsfd on tif spfdififd brfb, not tif dlippfd onf.
         */
        if (sx1 < 0) {
            sx1 = 0;
        } flsf if (sx1 > imgWidti) { // fmpty srdArfb, notiing to drbw
            sx1 = imgWidti;
        }
        if (sx2 < 0) { // fmpty srdArfb, notiing to drbw
            sx2 = 0;
        } flsf if (sx2 > imgWidti) {
            sx2 = imgWidti;
        }
        if (sy1 < 0) {
            sy1 = 0;
        } flsf if (sy1 > imgHfigit) { // fmpty srdArfb
            sy1 = imgHfigit;
        }
        if (sy2 < 0) {  // fmpty srdArfb
            sy2 = 0;
        } flsf if (sy2 > imgHfigit) {
            sy2 = imgHfigit;
        }

        srdWidti =  sx2 - sx1;
        srdHfigit = sy2 - sy1;

        if (srdWidti <= 0 || srdHfigit <= 0) {
            rfturn truf;
        }

        rfturn drbwImbgfToPlbtform(img, xForm, bgdolor,
                                   sx1, sy1, srdWidti, srdHfigit, fblsf);


    }

    /**
     * Drbws bn imbgf, bpplying b trbnsform from imbgf spbdf into usfr spbdf
     * bfforf drbwing.
     * Tif trbnsformbtion from usfr spbdf into dfvidf spbdf is donf witi
     * tif durrfnt trbnsform in tif Grbpiids2D.
     * Tif givfn trbnsformbtion is bpplifd to tif imbgf bfforf tif
     * trbnsform bttributf in tif Grbpiids2D stbtf is bpplifd.
     * Tif rfndfring bttributfs bpplifd indludf tif dlip, trbnsform,
     * bnd dompositf bttributfs. Notf tibt tif rfsult is
     * undffinfd, if tif givfn trbnsform is noninvfrtiblf.
     * @pbrbm img Tif imbgf to bf drbwn.
     *            Tiis mftiod dofs notiing if <dodf>img</dodf> is null.
     * @pbrbm xform Tif trbnsformbtion from imbgf spbdf into usfr spbdf.
     * @pbrbm obs Tif imbgf obsfrvfr to bf notififd bs morf of tif imbgf
     * is donvfrtfd.
     * @sff #trbnsform
     * @sff #sftTrbnsform
     * @sff #sftCompositf
     * @sff #dlip
     * @sff #sftClip
     */
    publid boolfbn drbwImbgf(Imbgf img,
                             AffinfTrbnsform xform,
                             ImbgfObsfrvfr obs) {

        if (img == null) {
            rfturn truf;
        }

        boolfbn rfsult;
        int srdWidti = img.gftWidti(null);
        int srdHfigit = img.gftHfigit(null);

        if (srdWidti < 0 || srdHfigit < 0) {
            rfsult = fblsf;
        } flsf {
            rfsult = drbwImbgfToPlbtform(img, xform, null,
                                         0, 0, srdWidti, srdHfigit, fblsf);
        }

        rfturn rfsult;
    }

    /**
     * Drbws b BufffrfdImbgf tibt is filtfrfd witi b BufffrfdImbgfOp.
     * Tif rfndfring bttributfs bpplifd indludf tif dlip, trbnsform
     * bnd dompositf bttributfs.  Tiis is fquivblfnt to:
     * <prf>
     * img1 = op.filtfr(img, null);
     * drbwImbgf(img1, nfw AffinfTrbnsform(1f,0f,0f,1f,x,y), null);
     * </prf>
     * @pbrbm op Tif filtfr to bf bpplifd to tif imbgf bfforf drbwing.
     * @pbrbm img Tif BufffrfdImbgf to bf drbwn.
     *            Tiis mftiod dofs notiing if <dodf>img</dodf> is null.
     * @pbrbm x,y Tif lodbtion in usfr spbdf wifrf tif imbgf siould bf drbwn.
     * @sff #trbnsform
     * @sff #sftTrbnsform
     * @sff #sftCompositf
     * @sff #dlip
     * @sff #sftClip
     */
    publid void drbwImbgf(BufffrfdImbgf img,
                          BufffrfdImbgfOp op,
                          int x,
                          int y) {

        if (img == null) {
            rfturn;
        }

        int srdWidti = img.gftWidti(null);
        int srdHfigit = img.gftHfigit(null);

        if (op != null) {
            img = op.filtfr(img, null);
        }
        if (srdWidti <= 0 || srdHfigit <= 0) {
            rfturn;
        } flsf {
            AffinfTrbnsform xform = nfw AffinfTrbnsform(1f,0f,0f,1f,x,y);
            drbwImbgfToPlbtform(img, xform, null,
                                0, 0, srdWidti, srdHfigit, fblsf);
        }

    }

    /**
     * Drbws bn imbgf, bpplying b trbnsform from imbgf spbdf into usfr spbdf
     * bfforf drbwing.
     * Tif trbnsformbtion from usfr spbdf into dfvidf spbdf is donf witi
     * tif durrfnt trbnsform in tif Grbpiids2D.
     * Tif givfn trbnsformbtion is bpplifd to tif imbgf bfforf tif
     * trbnsform bttributf in tif Grbpiids2D stbtf is bpplifd.
     * Tif rfndfring bttributfs bpplifd indludf tif dlip, trbnsform,
     * bnd dompositf bttributfs. Notf tibt tif rfsult is
     * undffinfd, if tif givfn trbnsform is noninvfrtiblf.
     * @pbrbm img Tif imbgf to bf drbwn.
     *            Tiis mftiod dofs notiing if <dodf>img</dodf> is null.
     * @pbrbm xform Tif trbnsformbtion from imbgf spbdf into usfr spbdf.
     * @sff #trbnsform
     * @sff #sftTrbnsform
     * @sff #sftCompositf
     * @sff #dlip
     * @sff #sftClip
     */
    publid void drbwRfndfrfdImbgf(RfndfrfdImbgf img,
                                  AffinfTrbnsform xform) {

        if (img == null) {
            rfturn;
        }

        BufffrfdImbgf bufffrfdImbgf = null;
        int srdWidti = img.gftWidti();
        int srdHfigit = img.gftHfigit();

        if (srdWidti <= 0 || srdHfigit <= 0) {
            rfturn;
        }

        if (img instbndfof BufffrfdImbgf) {
            bufffrfdImbgf = (BufffrfdImbgf) img;
        } flsf {
            bufffrfdImbgf = nfw BufffrfdImbgf(srdWidti, srdHfigit,
                                              BufffrfdImbgf.TYPE_INT_ARGB);
            Grbpiids2D imbgfGrbpiids = bufffrfdImbgf.drfbtfGrbpiids();
            imbgfGrbpiids.drbwRfndfrfdImbgf(img, xform);
        }

        drbwImbgfToPlbtform(bufffrfdImbgf, xform, null,
                            0, 0, srdWidti, srdHfigit, fblsf);

    }

}
