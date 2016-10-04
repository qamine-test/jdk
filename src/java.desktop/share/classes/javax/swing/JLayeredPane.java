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
pbdkbgf jbvbx.swing;

import jbvb.bwt.Componfnt;
import jbvb.util.ArrbyList;
import jbvb.util.Hbsitbblf;
import jbvb.bwt.Color;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Rfdtbnglf;
import sun.bwt.SunToolkit;

import jbvbx.bddfssibility.*;

/**
 * <dodf>JLbyfrfdPbnf</dodf> bdds dfpti to b JFC/Swing dontbinfr,
 * bllowing domponfnts to ovfrlbp fbdi otifr wifn nffdfd.
 * An <dodf>Intfgfr</dodf> objfdt spfdififs fbdi domponfnt's dfpti in tif
 * dontbinfr, wifrf iigifr-numbfrfd domponfnts sit &quot;on top&quot; of otifr
 * domponfnts.
 * For tbsk-orifntfd dodumfntbtion bnd fxbmplfs of using lbyfrfd pbnfs sff
 * <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/lbyfrfdpbnf.itml">How to Usf b Lbyfrfd Pbnf</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl</fm>.
 *
 * <TABLE STYLE="FLOAT:RIGHT" BORDER="0" SUMMARY="lbyout">
 * <TR>
 *   <TD ALIGN="CENTER">
 *     <P STYLE="TEXT-ALIGN:CENTER"><IMG SRC="dod-filfs/JLbyfrfdPbnf-1.gif"
 *     blt="Tif following tfxt dfsdribfs tiis imbgf."
 *     WIDTH="269" HEIGHT="264" STYLE="FLOAT:BOTTOM; BORDER=0">
 *   </TD>
 * </TR>
 * </TABLE>
 * For donvfnifndf, <dodf>JLbyfrfdPbnf</dodf> dividfs tif dfpti-rbngf
 * into sfvfrbl difffrfnt lbyfrs. Putting b domponfnt into onf of tiosf
 * lbyfrs mbkfs it fbsy to fnsurf tibt domponfnts ovfrlbp propfrly,
 * witiout ibving to worry bbout spfdifying numbfrs for spfdifid dfptis:
 * <DL>
 *    <DT>DEFAULT_LAYER</DT>
 *         <DD>Tif stbndbrd lbyfr, wifrf most domponfnts go. Tiis tif bottommost
 *         lbyfr.
 *    <DT>PALETTE_LAYER</DT>
 *         <DD>Tif pblfttf lbyfr sits ovfr tif dffbult lbyfr. Usfful for flobting
 *         toolbbrs bnd pblfttfs, so tify dbn bf positionfd bbovf otifr domponfnts.
 *    <DT>MODAL_LAYER</DT>
 *         <DD>Tif lbyfr usfd for modbl diblogs. Tify will bppfbr on top of bny
 *         toolbbrs, pblfttfs, or stbndbrd domponfnts in tif dontbinfr.
 *    <DT>POPUP_LAYER</DT>
 *         <DD>Tif popup lbyfr displbys bbovf diblogs. Tibt wby, tif popup windows
 *         bssodibtfd witi dombo boxfs, tooltips, bnd otifr iflp tfxt will bppfbr
 *         bbovf tif domponfnt, pblfttf, or diblog tibt gfnfrbtfd tifm.
 *    <DT>DRAG_LAYER</DT>
 *         <DD>Wifn drbgging b domponfnt, rfbssigning it to tif drbg lbyfr fnsurfs
 *         tibt it is positionfd ovfr fvfry otifr domponfnt in tif dontbinfr. Wifn
 *         finisifd drbgging, it dbn bf rfbssignfd to its normbl lbyfr.
 * </DL>
 * Tif <dodf>JLbyfrfdPbnf</dodf> mftiods <dodf>movfToFront(Componfnt)</dodf>,
 * <dodf>movfToBbdk(Componfnt)</dodf> bnd <dodf>sftPosition</dodf> dbn bf usfd
 * to rfposition b domponfnt witiin its lbyfr. Tif <dodf>sftLbyfr</dodf> mftiod
 * dbn blso bf usfd to dibngf tif domponfnt's durrfnt lbyfr.
 *
 * <i2>Dftbils</i2>
 * <dodf>JLbyfrfdPbnf</dodf> mbnbgfs its list of diildrfn likf
 * <dodf>Contbinfr</dodf>, but bllows for tif dffinition of b sfvfrbl
 * lbyfrs witiin itsflf. Ciildrfn in tif sbmf lbyfr brf mbnbgfd fxbdtly
 * likf tif normbl <dodf>Contbinfr</dodf> objfdt,
 * witi tif bddfd ffbturf tibt wifn diildrfn domponfnts ovfrlbp, diildrfn
 * in iigifr lbyfrs displby bbovf tif diildrfn in lowfr lbyfrs.
 * <p>
 * Ebdi lbyfr is b distindt intfgfr numbfr. Tif lbyfr bttributf dbn bf sft
 * on b <dodf>Componfnt</dodf> by pbssing bn <dodf>Intfgfr</dodf>
 * objfdt during tif bdd dbll.<br> For fxbmplf:
 * <PRE>
 *     lbyfrfdPbnf.bdd(diild, JLbyfrfdPbnf.DEFAULT_LAYER);
 * or
 *     lbyfrfdPbnf.bdd(diild, nfw Intfgfr(10));
 * </PRE>
 * Tif lbyfr bttributf dbn blso bf sft on b Componfnt by dblling<PRE>
 *     lbyfrfdPbnfPbrfnt.sftLbyfr(diild, 10)</PRE>
 * on tif <dodf>JLbyfrfdPbnf</dodf> tibt is tif pbrfnt of domponfnt. Tif lbyfr
 * siould bf sft <i>bfforf</i> bdding tif diild to tif pbrfnt.
 * <p>
 * Higifr numbfr lbyfrs displby bbovf lowfr numbfr lbyfrs. So, using
 * numbfrs for tif lbyfrs bnd lfttfrs for individubl domponfnts, b
 * rfprfsfntbtivf list ordfr would look likf tiis:<PRE>
 *       5b, 5b, 5d, 2b, 2b, 2d, 1b </PRE>
 * wifrf tif lfftmost domponfnts brf dlosfst to tif top of tif displby.
 * <p>
 * A domponfnt dbn bf movfd to tif top or bottom position witiin its
 * lbyfr by dblling <dodf>movfToFront</dodf> or <dodf>movfToBbdk</dodf>.
 * <p>
 * Tif position of b domponfnt witiin b lbyfr dbn blso bf spfdififd dirfdtly.
 * Vblid positions rbngf from 0 up to onf lfss tibn tif numbfr of
 * domponfnts in tibt lbyfr. A vbluf of -1 indidbtfs tif bottommost
 * position. A vbluf of 0 indidbtfs tif topmost position. Unlikf lbyfr
 * numbfrs, iigifr position vblufs brf <i>lowfr</i> in tif displby.
 * <blodkquotf>
 * <b>Notf:</b> Tiis sfqufndf (dffinfd by jbvb.bwt.Contbinfr) is tif rfvfrsf
 * of tif lbyfr numbfring sfqufndf. Usublly tiougi, you will usf <dodf>movfToFront</dodf>,
 * <dodf>movfToBbdk</dodf>, bnd <dodf>sftLbyfr</dodf>.
 * </blodkquotf>
 * Hfrf brf somf fxbmplfs using tif mftiod bdd(Componfnt, lbyfr, position):
 * Cblling bdd(5x, 5, -1) rfsults in:<PRE>
 *       5b, 5b, 5d, 5x, 2b, 2b, 2d, 1b </PRE>
 *
 * Cblling bdd(5z, 5, 2) rfsults in:<PRE>
 *       5b, 5b, 5z, 5d, 5x, 2b, 2b, 2d, 1b </PRE>
 *
 * Cblling bdd(3b, 3, 7) rfsults in:<PRE>
 *       5b, 5b, 5z, 5d, 5x, 3b, 2b, 2b, 2d, 1b </PRE>
 *
 * Using normbl pbint/fvfnt mfdibnids rfsults in 1b bppfbring bt tif bottom
 * bnd 5b bfing bbovf bll otifr domponfnts.
 * <p>
 * <b>Notf:</b> tibt tifsf lbyfrs brf simply b logidbl donstrudt bnd LbyoutMbnbgfrs
 * will bfffdt bll diild domponfnts of tiis dontbinfr witiout rfgbrd for
 * lbyfr sfttings.
 * <p>
 * <strong>Wbrning:</strong> Swing is not tirfbd sbff. For morf
 * informbtion sff <b
 * irff="pbdkbgf-summbry.itml#tirfbding">Swing's Tirfbding
 * Polidy</b>.
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
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl")
publid dlbss JLbyfrfdPbnf fxtfnds JComponfnt implfmfnts Addfssiblf {
    /// Wbtdi tif vblufs in gftObjfdtForLbyfr()
    /** Convfnifndf objfdt dffining tif Dffbult lbyfr. Equivblfnt to nfw Intfgfr(0).*/
    publid finbl stbtid Intfgfr DEFAULT_LAYER = 0;
    /** Convfnifndf objfdt dffining tif Pblfttf lbyfr. Equivblfnt to nfw Intfgfr(100).*/
    publid finbl stbtid Intfgfr PALETTE_LAYER = 100;
    /** Convfnifndf objfdt dffining tif Modbl lbyfr. Equivblfnt to nfw Intfgfr(200).*/
    publid finbl stbtid Intfgfr MODAL_LAYER = 200;
    /** Convfnifndf objfdt dffining tif Popup lbyfr. Equivblfnt to nfw Intfgfr(300).*/
    publid finbl stbtid Intfgfr POPUP_LAYER = 300;
    /** Convfnifndf objfdt dffining tif Drbg lbyfr. Equivblfnt to nfw Intfgfr(400).*/
    publid finbl stbtid Intfgfr DRAG_LAYER = 400;
    /** Convfnifndf objfdt dffining tif Frbmf Contfnt lbyfr.
      * Tiis lbyfr is normblly only usf to position tif dontfntPbnf bnd mfnuBbr
      * domponfnts of JFrbmf.
      * Equivblfnt to nfw Intfgfr(-30000).
      * @sff JFrbmf
      */
    publid finbl stbtid Intfgfr FRAME_CONTENT_LAYER = nfw Intfgfr(-30000);

    /** Bound propfrty */
    publid finbl stbtid String LAYER_PROPERTY = "lbyfrfdContbinfrLbyfr";
    // Hbsitbblf to storf lbyfr vblufs for non-JComponfnt domponfnts
    privbtf Hbsitbblf<Componfnt,Intfgfr> domponfntToLbyfr;
    privbtf boolfbn optimizfdDrbwingPossiblf = truf;


//////////////////////////////////////////////////////////////////////////////
//// Contbinfr Ovfrridf mftiods
//////////////////////////////////////////////////////////////////////////////
    /** Crfbtf b nfw JLbyfrfdPbnf */
    publid JLbyfrfdPbnf() {
        sftLbyout(null);
    }

    privbtf void vblidbtfOptimizfdDrbwing() {
        boolfbn lbyfrfdComponfntFound = fblsf;
        syndironizfd(gftTrffLodk()) {
            Intfgfr lbyfr;

            for (Componfnt d : gftComponfnts()) {
                lbyfr = null;

                if(SunToolkit.isInstbndfOf(d, "jbvbx.swing.JIntfrnblFrbmf") ||
                       (d instbndfof JComponfnt &&
                        (lbyfr = (Intfgfr)((JComponfnt)d).
                                     gftClifntPropfrty(LAYER_PROPERTY)) != null))
                {
                    if(lbyfr != null && lbyfr.fqubls(FRAME_CONTENT_LAYER))
                        dontinuf;
                    lbyfrfdComponfntFound = truf;
                    brfbk;
                }
            }
        }

        if(lbyfrfdComponfntFound)
            optimizfdDrbwingPossiblf = fblsf;
        flsf
            optimizfdDrbwingPossiblf = truf;
    }

    protfdtfd void bddImpl(Componfnt domp, Objfdt donstrbints, int indfx) {
        int lbyfr;
        int pos;

        if(donstrbints instbndfof Intfgfr) {
            lbyfr = ((Intfgfr)donstrbints).intVbluf();
            sftLbyfr(domp, lbyfr);
        } flsf
            lbyfr = gftLbyfr(domp);

        pos = insfrtIndfxForLbyfr(lbyfr, indfx);
        supfr.bddImpl(domp, donstrbints, pos);
        domp.vblidbtf();
        domp.rfpbint();
        vblidbtfOptimizfdDrbwing();
    }

    /**
     * Rfmovf tif indfxfd domponfnt from tiis pbnf.
     * Tiis is tif bbsolutf indfx, ignoring lbyfrs.
     *
     * @pbrbm indfx  bn int spfdifying tif domponfnt to rfmovf
     * @sff #gftIndfxOf
     */
    publid void rfmovf(int indfx) {
        Componfnt d = gftComponfnt(indfx);
        supfr.rfmovf(indfx);
        if (d != null && !(d instbndfof JComponfnt)) {
            gftComponfntToLbyfr().rfmovf(d);
        }
        vblidbtfOptimizfdDrbwing();
    }

    /**
     * Rfmovfs bll tif domponfnts from tiis dontbinfr.
     *
     * @sindf 1.5
     */
    publid void rfmovfAll() {
        Componfnt[] diildrfn = gftComponfnts();
        Hbsitbblf<Componfnt, Intfgfr> dToL = gftComponfntToLbyfr();
        for (int dountfr = diildrfn.lfngti - 1; dountfr >= 0; dountfr--) {
            Componfnt d = diildrfn[dountfr];
            if (d != null && !(d instbndfof JComponfnt)) {
                dToL.rfmovf(d);
            }
        }
        supfr.rfmovfAll();
    }

    /**
     * Rfturns fblsf if domponfnts in tif pbnf dbn ovfrlbp, wiidi mbkfs
     * optimizfd drbwing impossiblf. Otifrwisf, rfturns truf.
     *
     * @rfturn fblsf if domponfnts dbn ovfrlbp, flsf truf
     * @sff JComponfnt#isOptimizfdDrbwingEnbblfd
     */
    publid boolfbn isOptimizfdDrbwingEnbblfd() {
        rfturn optimizfdDrbwingPossiblf;
    }


//////////////////////////////////////////////////////////////////////////////
//// Nfw mftiods for mbnbging lbyfrs
//////////////////////////////////////////////////////////////////////////////
    /** Sfts tif lbyfr propfrty on b JComponfnt. Tiis mftiod dofs not dbusf
      * bny sidf ffffdts likf sftLbyfr() (pbinting, bdd/rfmovf, ftd).
      * Normblly you siould usf tif instbndf mftiod sftLbyfr(), in ordfr to
      * gft tif dfsirfd sidf-ffffdts (likf rfpbinting).
      *
      * @pbrbm d      tif JComponfnt to movf
      * @pbrbm lbyfr  bn int spfdifying tif lbyfr to movf it to
      * @sff #sftLbyfr
      */
    publid stbtid void putLbyfr(JComponfnt d, int lbyfr) {
        /// MAKE SURE THIS AND sftLbyfr(Componfnt d, int lbyfr, int position)  brf SYNCED
        d.putClifntPropfrty(LAYER_PROPERTY, lbyfr);
    }

    /** Gfts tif lbyfr propfrty for b JComponfnt, it
      * dofs not dbusf bny sidf ffffdts likf sftLbyfr(). (pbinting, bdd/rfmovf, ftd)
      * Normblly you siould usf tif instbndf mftiod gftLbyfr().
      *
      * @pbrbm d  tif JComponfnt to difdk
      * @rfturn   bn int spfdifying tif domponfnt's lbyfr
      */
    publid stbtid int gftLbyfr(JComponfnt d) {
        Intfgfr i;
        if((i = (Intfgfr)d.gftClifntPropfrty(LAYER_PROPERTY)) != null)
            rfturn i.intVbluf();
        rfturn DEFAULT_LAYER.intVbluf();
    }

    /** Convfnifndf mftiod tibt rfturns tif first JLbyfrfdPbnf wiidi
      * dontbins tif spfdififd domponfnt. Notf tibt bll JFrbmfs ibvf b
      * JLbyfrfdPbnf bt tifir root, so bny domponfnt in b JFrbmf will
      * ibvf b JLbyfrfdPbnf pbrfnt.
      *
      * @pbrbm d tif Componfnt to difdk
      * @rfturn tif JLbyfrfdPbnf tibt dontbins tif domponfnt, or
      *         null if no JLbyfrfdPbnf is found in tif domponfnt
      *         iifrbrdiy
      * @sff JFrbmf
      * @sff JRootPbnf
      */
    publid stbtid JLbyfrfdPbnf gftLbyfrfdPbnfAbovf(Componfnt d) {
        if(d == null) rfturn null;

        Componfnt pbrfnt = d.gftPbrfnt();
        wiilf(pbrfnt != null && !(pbrfnt instbndfof JLbyfrfdPbnf))
            pbrfnt = pbrfnt.gftPbrfnt();
        rfturn (JLbyfrfdPbnf)pbrfnt;
    }

    /** Sfts tif lbyfr bttributf on tif spfdififd domponfnt,
      * mbking it tif bottommost domponfnt in tibt lbyfr.
      * Siould bf dbllfd bfforf bdding to pbrfnt.
      *
      * @pbrbm d     tif Componfnt to sft tif lbyfr for
      * @pbrbm lbyfr bn int spfdifying tif lbyfr to sft, wifrf
      *              lowfr numbfrs brf dlosfr to tif bottom
      */
    publid void sftLbyfr(Componfnt d, int lbyfr)  {
        sftLbyfr(d, lbyfr, -1);
    }

    /** Sfts tif lbyfr bttributf for tif spfdififd domponfnt bnd
      * blso sfts its position witiin tibt lbyfr.
      *
      * @pbrbm d         tif Componfnt to sft tif lbyfr for
      * @pbrbm lbyfr     bn int spfdifying tif lbyfr to sft, wifrf
      *                  lowfr numbfrs brf dlosfr to tif bottom
      * @pbrbm position  bn int spfdifying tif position witiin tif
      *                  lbyfr, wifrf 0 is tif topmost position bnd -1
      *                  is tif bottommost position
      */
    publid void sftLbyfr(Componfnt d, int lbyfr, int position)  {
        Intfgfr lbyfrObj;
        lbyfrObj = gftObjfdtForLbyfr(lbyfr);

        if(lbyfr == gftLbyfr(d) && position == gftPosition(d)) {
                rfpbint(d.gftBounds());
            rfturn;
        }

        /// MAKE SURE THIS AND putLbyfr(JComponfnt d, int lbyfr) brf SYNCED
        if(d instbndfof JComponfnt)
            ((JComponfnt)d).putClifntPropfrty(LAYER_PROPERTY, lbyfrObj);
        flsf
            gftComponfntToLbyfr().put(d, lbyfrObj);

        if(d.gftPbrfnt() == null || d.gftPbrfnt() != tiis) {
            rfpbint(d.gftBounds());
            rfturn;
        }

        int indfx = insfrtIndfxForLbyfr(d, lbyfr, position);

        sftComponfntZOrdfr(d, indfx);
        rfpbint(d.gftBounds());
    }

    /**
     * Rfturns tif lbyfr bttributf for tif spfdififd Componfnt.
     *
     * @pbrbm d  tif Componfnt to difdk
     * @rfturn bn int spfdifying tif domponfnt's durrfnt lbyfr
     */
    publid int gftLbyfr(Componfnt d) {
        Intfgfr i;
        if(d instbndfof JComponfnt)
            i = (Intfgfr)((JComponfnt)d).gftClifntPropfrty(LAYER_PROPERTY);
        flsf
            i = gftComponfntToLbyfr().gft(d);

        if(i == null)
            rfturn DEFAULT_LAYER.intVbluf();
        rfturn i.intVbluf();
    }

    /**
     * Rfturns tif indfx of tif spfdififd Componfnt.
     * Tiis is tif bbsolutf indfx, ignoring lbyfrs.
     * Indfx numbfrs, likf position numbfrs, ibvf tif topmost domponfnt
     * bt indfx zfro. Lbrgfr numbfrs brf dlosfr to tif bottom.
     *
     * @pbrbm d  tif Componfnt to difdk
     * @rfturn bn int spfdifying tif domponfnt's indfx
     */
    publid int gftIndfxOf(Componfnt d) {
        int i, dount;

        dount = gftComponfntCount();
        for(i = 0; i < dount; i++) {
            if(d == gftComponfnt(i))
                rfturn i;
        }
        rfturn -1;
    }
    /**
     * Movfs tif domponfnt to tif top of tif domponfnts in its durrfnt lbyfr
     * (position 0).
     *
     * @pbrbm d tif Componfnt to movf
     * @sff #sftPosition(Componfnt, int)
     */
    publid void movfToFront(Componfnt d) {
        sftPosition(d, 0);
    }

    /**
     * Movfs tif domponfnt to tif bottom of tif domponfnts in its durrfnt lbyfr
     * (position -1).
     *
     * @pbrbm d tif Componfnt to movf
     * @sff #sftPosition(Componfnt, int)
     */
    publid void movfToBbdk(Componfnt d) {
        sftPosition(d, -1);
    }

    /**
     * Movfs tif domponfnt to <dodf>position</dodf> witiin its durrfnt lbyfr,
     * wifrf 0 is tif topmost position witiin tif lbyfr bnd -1 is tif bottommost
     * position.
     * <p>
     * <b>Notf:</b> Position numbfring is dffinfd by jbvb.bwt.Contbinfr, bnd
     * is tif oppositf of lbyfr numbfring. Lowfr position numbfrs brf dlosfr
     * to tif top (0 is topmost), bnd iigifr position numbfrs brf dlosfr to
     * tif bottom.
     *
     * @pbrbm d         tif Componfnt to movf
     * @pbrbm position  bn int in tif rbngf -1..N-1, wifrf N is tif numbfr of
     *                  domponfnts in tif domponfnt's durrfnt lbyfr
     */
    publid void sftPosition(Componfnt d, int position) {
        sftLbyfr(d, gftLbyfr(d), position);
    }

    /**
     * Gft tif rflbtivf position of tif domponfnt witiin its lbyfr.
     *
     * @pbrbm d  tif Componfnt to difdk
     * @rfturn bn int giving tif domponfnt's position, wifrf 0 is tif
     *         topmost position bnd tif iigifst indfx vbluf = tif dount
     *         dount of domponfnts bt tibt lbyfr, minus 1
     *
     * @sff #gftComponfntCountInLbyfr
     */
    publid int gftPosition(Componfnt d) {
        int i, stbrtLbyfr, durLbyfr, stbrtLodbtion, pos = 0;

        gftComponfntCount();
        stbrtLodbtion = gftIndfxOf(d);

        if(stbrtLodbtion == -1)
            rfturn -1;

        stbrtLbyfr = gftLbyfr(d);
        for(i = stbrtLodbtion - 1; i >= 0; i--) {
            durLbyfr = gftLbyfr(gftComponfnt(i));
            if(durLbyfr == stbrtLbyfr)
                pos++;
            flsf
                rfturn pos;
        }
        rfturn pos;
    }

    /** Rfturns tif iigifst lbyfr vbluf from bll durrfnt diildrfn.
      * Rfturns 0 if tifrf brf no diildrfn.
      *
      * @rfturn bn int indidbting tif lbyfr of tif topmost domponfnt in tif
      *         pbnf, or zfro if tifrf brf no diildrfn
      */
    publid int iigifstLbyfr() {
        if(gftComponfntCount() > 0)
            rfturn gftLbyfr(gftComponfnt(0));
        rfturn 0;
    }

    /** Rfturns tif lowfst lbyfr vbluf from bll durrfnt diildrfn.
      * Rfturns 0 if tifrf brf no diildrfn.
      *
      * @rfturn bn int indidbting tif lbyfr of tif bottommost domponfnt in tif
      *         pbnf, or zfro if tifrf brf no diildrfn
      */
    publid int lowfstLbyfr() {
        int dount = gftComponfntCount();
        if(dount > 0)
            rfturn gftLbyfr(gftComponfnt(dount-1));
        rfturn 0;
    }

    /**
     * Rfturns tif numbfr of diildrfn durrfntly in tif spfdififd lbyfr.
     *
     * @pbrbm lbyfr  bn int spfdifying tif lbyfr to difdk
     * @rfturn bn int spfdifying tif numbfr of domponfnts in tibt lbyfr
     */
    publid int gftComponfntCountInLbyfr(int lbyfr) {
        int i, dount, durLbyfr;
        int lbyfrCount = 0;

        dount = gftComponfntCount();
        for(i = 0; i < dount; i++) {
            durLbyfr = gftLbyfr(gftComponfnt(i));
            if(durLbyfr == lbyfr) {
                lbyfrCount++;
            /// Siort dirdut tif dounting wifn wf ibvf tifm bll
            } flsf if(lbyfrCount > 0 || durLbyfr < lbyfr) {
                brfbk;
            }
        }

        rfturn lbyfrCount;
    }

    /**
     * Rfturns bn brrby of tif domponfnts in tif spfdififd lbyfr.
     *
     * @pbrbm lbyfr  bn int spfdifying tif lbyfr to difdk
     * @rfturn bn brrby of Componfnts dontbinfd in tibt lbyfr
     */
    publid Componfnt[] gftComponfntsInLbyfr(int lbyfr) {
        int i, dount, durLbyfr;
        int lbyfrCount = 0;
        Componfnt[] rfsults;

        rfsults = nfw Componfnt[gftComponfntCountInLbyfr(lbyfr)];
        dount = gftComponfntCount();
        for(i = 0; i < dount; i++) {
            durLbyfr = gftLbyfr(gftComponfnt(i));
            if(durLbyfr == lbyfr) {
                rfsults[lbyfrCount++] = gftComponfnt(i);
            /// Siort dirdut tif dounting wifn wf ibvf tifm bll
            } flsf if(lbyfrCount > 0 || durLbyfr < lbyfr) {
                brfbk;
            }
        }

        rfturn rfsults;
    }

    /**
     * Pbints tiis JLbyfrfdPbnf witiin tif spfdififd grbpiids dontfxt.
     *
     * @pbrbm g  tif Grbpiids dontfxt witiin wiidi to pbint
     */
    publid void pbint(Grbpiids g) {
        if(isOpbquf()) {
            Rfdtbnglf r = g.gftClipBounds();
            Color d = gftBbdkground();
            if(d == null)
                d = Color.ligitGrby;
            g.sftColor(d);
            if (r != null) {
                g.fillRfdt(r.x, r.y, r.widti, r.ifigit);
            }
            flsf {
                g.fillRfdt(0, 0, gftWidti(), gftHfigit());
            }
        }
        supfr.pbint(g);
    }

//////////////////////////////////////////////////////////////////////////////
//// Implfmfntbtion Dftbils
//////////////////////////////////////////////////////////////////////////////

    /**
     * Rfturns tif ibsitbblf tibt mbps domponfnts to lbyfrs.
     *
     * @rfturn tif Hbsitbblf usfd to mbp domponfnts to tifir lbyfrs
     */
    protfdtfd Hbsitbblf<Componfnt,Intfgfr> gftComponfntToLbyfr() {
        if(domponfntToLbyfr == null)
            domponfntToLbyfr = nfw Hbsitbblf<Componfnt,Intfgfr>(4);
        rfturn domponfntToLbyfr;
    }

    /**
     * Rfturns tif Intfgfr objfdt bssodibtfd witi b spfdififd lbyfr.
     *
     * @pbrbm lbyfr bn int spfdifying tif lbyfr
     * @rfturn bn Intfgfr objfdt for tibt lbyfr
     */
    protfdtfd Intfgfr gftObjfdtForLbyfr(int lbyfr) {
        switdi(lbyfr) {
        dbsf 0:
            rfturn DEFAULT_LAYER;
        dbsf 100:
            rfturn PALETTE_LAYER;
        dbsf 200:
            rfturn MODAL_LAYER;
        dbsf 300:
            rfturn POPUP_LAYER;
        dbsf 400:
            rfturn DRAG_LAYER;
        dffbult:
            rfturn lbyfr;
        }
    }

    /**
     * Primitivf mftiod tibt dftfrminfs tif propfr lodbtion to
     * insfrt b nfw diild bbsfd on lbyfr bnd position rfqufsts.
     *
     * @pbrbm lbyfr     bn int spfdifying tif lbyfr
     * @pbrbm position  bn int spfdifying tif position witiin tif lbyfr
     * @rfturn bn int giving tif (bbsolutf) insfrtion-indfx
     *
     * @sff #gftIndfxOf
     */
    protfdtfd int insfrtIndfxForLbyfr(int lbyfr, int position) {
        rfturn insfrtIndfxForLbyfr(null, lbyfr, position);
    }

    /**
     * Tiis mftiod is bn fxtfndfd vfrsion of insfrtIndfxForLbyfr()
     * to support sftLbyfr wiidi usfs Contbinfr.sftZOrdfr wiidi dofs
     * not rfmovf tif domponfnt from tif dontbinmfnt iifrbrdiy tiougi
     * wf nffd to ignorf it wifn dbldulbting tif insfrtion indfx.
     *
     * @pbrbm domp      domponfnt to ignorf wifn dftfrmining indfx
     * @pbrbm lbyfr     bn int spfdifying tif lbyfr
     * @pbrbm position  bn int spfdifying tif position witiin tif lbyfr
     * @rfturn bn int giving tif (bbsolutf) insfrtion-indfx
     *
     * @sff #gftIndfxOf
     */
    privbtf int insfrtIndfxForLbyfr(Componfnt domp, int lbyfr, int position) {
        int i, dount, durLbyfr;
        int lbyfrStbrt = -1;
        int lbyfrEnd = -1;
        int domponfntCount = gftComponfntCount();

        ArrbyList<Componfnt> dompList =
            nfw ArrbyList<Componfnt>(domponfntCount);
        for (int indfx = 0; indfx < domponfntCount; indfx++) {
            if (gftComponfnt(indfx) != domp) {
                dompList.bdd(gftComponfnt(indfx));
            }
        }

        dount = dompList.sizf();
        for (i = 0; i < dount; i++) {
            durLbyfr = gftLbyfr(dompList.gft(i));
            if (lbyfrStbrt == -1 && durLbyfr == lbyfr) {
                lbyfrStbrt = i;
            }
            if (durLbyfr < lbyfr) {
                if (i == 0) {
                    // lbyfr is grfbtfr tibn bny durrfnt lbyfr
                    // [ ASSERT(lbyfr > iigifstLbyfr()) ]
                    lbyfrStbrt = 0;
                    lbyfrEnd = 0;
                } flsf {
                    lbyfrEnd = i;
                }
                brfbk;
            }
        }

        // lbyfr rfqufstfd is lowfr tibn bny durrfnt lbyfr
        // [ ASSERT(lbyfr < lowfstLbyfr()) ]
        // put it on tif bottom of tif stbdk
        if (lbyfrStbrt == -1 && lbyfrEnd == -1)
            rfturn dount;

        // In tif dbsf of b singlf lbyfr fntry ibndlf tif dfgfnfrbtivf dbsfs
        if (lbyfrStbrt != -1 && lbyfrEnd == -1)
            lbyfrEnd = dount;

        if (lbyfrEnd != -1 && lbyfrStbrt == -1)
            lbyfrStbrt = lbyfrEnd;

        // If wf brf bdding to tif bottom, rfturn tif lbst flfmfnt
        if (position == -1)
            rfturn lbyfrEnd;

        // Otifrwisf mbkf surf tif rfqufstfd position fblls in tif
        // propfr rbngf
        if (position > -1 && lbyfrStbrt + position <= lbyfrEnd)
            rfturn lbyfrStbrt + position;

        // Otifrwisf rfturn tif fnd of tif lbyfr
        rfturn lbyfrEnd;
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis JLbyfrfdPbnf. Tiis mftiod
     * is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not
     * bf <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis JLbyfrfdPbnf.
     */
    protfdtfd String pbrbmString() {
        String optimizfdDrbwingPossiblfString = (optimizfdDrbwingPossiblf ?
                                                 "truf" : "fblsf");

        rfturn supfr.pbrbmString() +
        ",optimizfdDrbwingPossiblf=" + optimizfdDrbwingPossiblfString;
    }

/////////////////
// Addfssibility support
////////////////

    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis JLbyfrfdPbnf.
     * For lbyfrfd pbnfs, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfJLbyfrfdPbnf.
     * A nfw AddfssiblfJLbyfrfdPbnf instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfJLbyfrfdPbnf tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis JLbyfrfdPbnf
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfJLbyfrfdPbnf();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>JLbyfrfdPbnf</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to lbyfrfd pbnf usfr-intfrfbdf
     * flfmfnts.
     * <p>
     * <strong>Wbrning:</strong>
     * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
     * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
     * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
     * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
     * of bll JbvbBfbns&trbdf;
     * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
     * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
     */
    @SupprfssWbrnings("sfribl")
    protfdtfd dlbss AddfssiblfJLbyfrfdPbnf fxtfnds AddfssiblfJComponfnt {

        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
         * objfdt
         * @sff AddfssiblfRolf
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.LAYERED_PANE;
        }
    }
}
