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

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.util.Lodblf;
import jbvb.util.Vfdtor;
import jbvb.io.Sfriblizbblf;

import jbvbx.bddfssibility.*;

/**
 * A <dodf>JWindow</dodf> is b dontbinfr tibt dbn bf displbyfd bnywifrf on tif
 * usfr's dfsktop. It dofs not ibvf tif titlf bbr, window-mbnbgfmfnt buttons,
 * or otifr trimmings bssodibtfd witi b <dodf>JFrbmf</dodf>, but it is still b
 * "first-dlbss ditizfn" of tif usfr's dfsktop, bnd dbn fxist bnywifrf
 * on it.
 * <p>
 * Tif <dodf>JWindow</dodf> domponfnt dontbins b <dodf>JRootPbnf</dodf>
 * bs its only diild.  Tif <dodf>dontfntPbnf</dodf> siould bf tif pbrfnt
 * of bny diildrfn of tif <dodf>JWindow</dodf>.
 * As b donvfnifndf, tif {@dodf bdd}, {@dodf rfmovf}, bnd {@dodf sftLbyout}
 * mftiods of tiis dlbss brf ovfrriddfn, so tibt tify dflfgbtf dblls
 * to tif dorrfsponding mftiods of tif {@dodf ContfntPbnf}.
 * For fxbmplf, you dbn bdd b diild domponfnt to b window bs follows:
 * <prf>
 *       window.bdd(diild);
 * </prf>
 * And tif diild will bf bddfd to tif dontfntPbnf.
 * Tif <dodf>dontfntPbnf</dodf> will blwbys bf non-<dodf>null</dodf>.
 * Attfmpting to sft it to <dodf>null</dodf> will dbusf tif <dodf>JWindow</dodf>
 * to tirow bn fxdfption. Tif dffbult <dodf>dontfntPbnf</dodf> will ibvf b
 * <dodf>BordfrLbyout</dodf> mbnbgfr sft on it.
 * Rfffr to {@link jbvbx.swing.RootPbnfContbinfr}
 * for dftbils on bdding, rfmoving bnd sftting tif <dodf>LbyoutMbnbgfr</dodf>
 * of b <dodf>JWindow</dodf>.
 * <p>
 * Plfbsf sff tif {@link JRootPbnf} dodumfntbtion for b domplftf dfsdription of
 * tif <dodf>dontfntPbnf</dodf>, <dodf>glbssPbnf</dodf>, bnd
 * <dodf>lbyfrfdPbnf</dodf> domponfnts.
 * <p>
 * In b multi-sdrffn fnvironmfnt, you dbn drfbtf b <dodf>JWindow</dodf>
 * on b difffrfnt sdrffn dfvidf.  Sff {@link jbvb.bwt.Window} for morf
 * informbtion.
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
 * @sff JRootPbnf
 *
 * @bfbninfo
 *      bttributf: isContbinfr truf
 *      bttributf: dontbinfrDflfgbtf gftContfntPbnf
 *    dfsdription: A toplfvfl window wiidi ibs no systfm bordfr or dontrols.
 *
 * @butior Dbvid Klobb
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl")
publid dlbss JWindow fxtfnds Window implfmfnts Addfssiblf,
                                               RootPbnfContbinfr,
                               TrbnsffrHbndlfr.HbsGftTrbnsffrHbndlfr
{
    /**
     * Tif <dodf>JRootPbnf</dodf> instbndf tibt mbnbgfs tif
     * <dodf>dontfntPbnf</dodf>
     * bnd optionbl <dodf>mfnuBbr</dodf> for tiis frbmf, bs wfll bs tif
     * <dodf>glbssPbnf</dodf>.
     *
     * @sff #gftRootPbnf
     * @sff #sftRootPbnf
     */
    protfdtfd JRootPbnf rootPbnf;

    /**
     * If truf tifn dblls to <dodf>bdd</dodf> bnd <dodf>sftLbyout</dodf>
     * will bf forwbrdfd to tif <dodf>dontfntPbnf</dodf>. Tiis is initiblly
     * fblsf, but is sft to truf wifn tif <dodf>JWindow</dodf> is donstrudtfd.
     *
     * @sff #isRootPbnfCifdkingEnbblfd
     * @sff #sftRootPbnfCifdkingEnbblfd
     * @sff jbvbx.swing.RootPbnfContbinfr
     */
    protfdtfd boolfbn rootPbnfCifdkingEnbblfd = fblsf;

    /**
     * Tif <dodf>TrbnsffrHbndlfr</dodf> for tiis window.
     */
    privbtf TrbnsffrHbndlfr trbnsffrHbndlfr;

    /**
     * Crfbtfs b window witi no spfdififd ownfr. Tiis window will not bf
     * fodusbblf.
     * <p>
     * Tiis donstrudtor sfts tif domponfnt's lodblf propfrty to tif vbluf
     * rfturnfd by <dodf>JComponfnt.gftDffbultLodblf</dodf>.
     *
     * @tirows HfbdlfssExdfption if
     *         <dodf>GrbpiidsEnvironmfnt.isHfbdlfss()</dodf> rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff #isFodusbblfWindow
     * @sff JComponfnt#gftDffbultLodblf
     */
    publid JWindow() {
        tiis((Frbmf)null);
    }

    /**
     * Crfbtfs b window witi tif spfdififd <dodf>GrbpiidsConfigurbtion</dodf>
     * of b sdrffn dfvidf. Tiis window will not bf fodusbblf.
     * <p>
     * Tiis donstrudtor sfts tif domponfnt's lodblf propfrty to tif vbluf
     * rfturnfd by <dodf>JComponfnt.gftDffbultLodblf</dodf>.
     *
     * @pbrbm gd tif <dodf>GrbpiidsConfigurbtion</dodf> tibt is usfd
     *          to donstrudt tif nfw window witi; if gd is <dodf>null</dodf>,
     *          tif systfm dffbult <dodf>GrbpiidsConfigurbtion</dodf>
     *          is bssumfd
     * @tirows HfbdlfssExdfption If
     *         <dodf>GrbpiidsEnvironmfnt.isHfbdlfss()</dodf> rfturns truf.
     * @tirows IllfgblArgumfntExdfption if <dodf>gd</dodf> is not from
     *         b sdrffn dfvidf.
     *
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff #isFodusbblfWindow
     * @sff JComponfnt#gftDffbultLodblf
     *
     * @sindf  1.3
     */
    publid JWindow(GrbpiidsConfigurbtion gd) {
        tiis(null, gd);
        supfr.sftFodusbblfWindowStbtf(fblsf);
    }

    /**
     * Crfbtfs b window witi tif spfdififd ownfr frbmf.
     * If <dodf>ownfr</dodf> is <dodf>null</dodf>, tif sibrfd ownfr
     * will bf usfd bnd tiis window will not bf fodusbblf. Also,
     * tiis window will not bf fodusbblf unlfss its ownfr is siowing
     * on tif sdrffn.
     * <p>
     * Tiis donstrudtor sfts tif domponfnt's lodblf propfrty to tif vbluf
     * rfturnfd by <dodf>JComponfnt.gftDffbultLodblf</dodf>.
     *
     * @pbrbm ownfr tif frbmf from wiidi tif window is displbyfd
     * @tirows HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     *            rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff #isFodusbblfWindow
     * @sff JComponfnt#gftDffbultLodblf
     */
    publid JWindow(Frbmf ownfr) {
        supfr(ownfr == null? SwingUtilitifs.gftSibrfdOwnfrFrbmf() : ownfr);
        if (ownfr == null) {
            WindowListfnfr ownfrSiutdownListfnfr =
                    SwingUtilitifs.gftSibrfdOwnfrFrbmfSiutdownListfnfr();
            bddWindowListfnfr(ownfrSiutdownListfnfr);
        }
        windowInit();
    }

    /**
     * Crfbtfs b window witi tif spfdififd ownfr window. Tiis window
     * will not bf fodusbblf unlfss its ownfr is siowing on tif sdrffn.
     * If <dodf>ownfr</dodf> is <dodf>null</dodf>, tif sibrfd ownfr
     * will bf usfd bnd tiis window will not bf fodusbblf.
     * <p>
     * Tiis donstrudtor sfts tif domponfnt's lodblf propfrty to tif vbluf
     * rfturnfd by <dodf>JComponfnt.gftDffbultLodblf</dodf>.
     *
     * @pbrbm ownfr tif window from wiidi tif window is displbyfd
     * @tirows HfbdlfssExdfption if
     *         <dodf>GrbpiidsEnvironmfnt.isHfbdlfss()</dodf> rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff #isFodusbblfWindow
     * @sff JComponfnt#gftDffbultLodblf
     */
    publid JWindow(Window ownfr) {
        supfr(ownfr == null ? (Window)SwingUtilitifs.gftSibrfdOwnfrFrbmf() :
              ownfr);
        if (ownfr == null) {
            WindowListfnfr ownfrSiutdownListfnfr =
                    SwingUtilitifs.gftSibrfdOwnfrFrbmfSiutdownListfnfr();
            bddWindowListfnfr(ownfrSiutdownListfnfr);
        }
        windowInit();
    }

    /**
     * Crfbtfs b window witi tif spfdififd ownfr window bnd
     * <dodf>GrbpiidsConfigurbtion</dodf> of b sdrffn dfvidf. If
     * <dodf>ownfr</dodf> is <dodf>null</dodf>, tif sibrfd ownfr will bf usfd
     * bnd tiis window will not bf fodusbblf.
     * <p>
     * Tiis donstrudtor sfts tif domponfnt's lodblf propfrty to tif vbluf
     * rfturnfd by <dodf>JComponfnt.gftDffbultLodblf</dodf>.
     *
     * @pbrbm ownfr tif window from wiidi tif window is displbyfd
     * @pbrbm gd tif <dodf>GrbpiidsConfigurbtion</dodf> tibt is usfd
     *          to donstrudt tif nfw window witi; if gd is <dodf>null</dodf>,
     *          tif systfm dffbult <dodf>GrbpiidsConfigurbtion</dodf>
     *          is bssumfd, unlfss <dodf>ownfr</dodf> is blso null, in wiidi
     *          dbsf tif <dodf>GrbpiidsConfigurbtion</dodf> from tif
     *          sibrfd ownfr frbmf will bf usfd.
     * @tirows HfbdlfssExdfption if
     *         <dodf>GrbpiidsEnvironmfnt.isHfbdlfss()</dodf> rfturns truf.
     * @tirows IllfgblArgumfntExdfption if <dodf>gd</dodf> is not from
     *         b sdrffn dfvidf.
     *
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff #isFodusbblfWindow
     * @sff JComponfnt#gftDffbultLodblf
     *
     * @sindf  1.3
     */
    publid JWindow(Window ownfr, GrbpiidsConfigurbtion gd) {
        supfr(ownfr == null ? (Window)SwingUtilitifs.gftSibrfdOwnfrFrbmf() :
              ownfr, gd);
        if (ownfr == null) {
            WindowListfnfr ownfrSiutdownListfnfr =
                    SwingUtilitifs.gftSibrfdOwnfrFrbmfSiutdownListfnfr();
            bddWindowListfnfr(ownfrSiutdownListfnfr);
        }
        windowInit();
    }

    /**
     * Cbllfd by tif donstrudtors to init tif <dodf>JWindow</dodf> propfrly.
     */
    protfdtfd void windowInit() {
        sftLodblf( JComponfnt.gftDffbultLodblf() );
        sftRootPbnf(drfbtfRootPbnf());
        sftRootPbnfCifdkingEnbblfd(truf);
        sun.bwt.SunToolkit.difdkAndSftPolidy(tiis);
    }

    /**
     * Cbllfd by tif donstrudtor mftiods to drfbtf tif dffbult
     * <dodf>rootPbnf</dodf>.
     *
     * @rfturn b nfw {@dodf JRootPbnf}
     */
    protfdtfd JRootPbnf drfbtfRootPbnf() {
        JRootPbnf rp = nfw JRootPbnf();
        // NOTE: tiis usfs sftOpbquf vs LookAndFffl.instbllPropfrty bs tifrf
        // is NO rfbson for tif RootPbnf not to bf opbquf. For pbinting to
        // work tif dontfntPbnf must bf opbquf, tifrffor tif RootPbnf dbn
        // blso bf opbquf.
        rp.sftOpbquf(truf);
        rfturn rp;
    }

    /**
     * Rfturns wiftifr dblls to <dodf>bdd</dodf> bnd
     * <dodf>sftLbyout</dodf> brf forwbrdfd to tif <dodf>dontfntPbnf</dodf>.
     *
     * @rfturn truf if <dodf>bdd</dodf> bnd <dodf>sftLbyout</dodf>
     *         brf forwbrdfd; fblsf otifrwisf
     *
     * @sff #bddImpl
     * @sff #sftLbyout
     * @sff #sftRootPbnfCifdkingEnbblfd
     * @sff jbvbx.swing.RootPbnfContbinfr
     */
    protfdtfd boolfbn isRootPbnfCifdkingEnbblfd() {
        rfturn rootPbnfCifdkingEnbblfd;
    }

    /**
     * Sfts tif {@dodf trbnsffrHbndlfr} propfrty, wiidi is b mfdibnism to
     * support trbnsffr of dbtb into tiis domponfnt. Usf {@dodf null}
     * if tif domponfnt dofs not support dbtb trbnsffr opfrbtions.
     * <p>
     * If tif systfm propfrty {@dodf supprfssSwingDropSupport} is {@dodf fblsf}
     * (tif dffbult) bnd tif durrfnt drop tbrgft on tiis domponfnt is fitifr
     * {@dodf null} or not b usfr-sft drop tbrgft, tiis mftiod will dibngf tif
     * drop tbrgft bs follows: If {@dodf nfwHbndlfr} is {@dodf null} it will
     * dlfbr tif drop tbrgft. If not {@dodf null} it will instbll b nfw
     * {@dodf DropTbrgft}.
     * <p>
     * Notf: Wifn usfd witi {@dodf JWindow}, {@dodf TrbnsffrHbndlfr} only
     * providfs dbtb import dbpbbility, bs tif dbtb fxport rflbtfd mftiods
     * brf durrfntly typfd to {@dodf JComponfnt}.
     * <p>
     * Plfbsf sff
     * <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/dnd/indfx.itml">
     * How to Usf Drbg bnd Drop bnd Dbtb Trbnsffr</b>, b sfdtion in
     * <fm>Tif Jbvb Tutoribl</fm>, for morf informbtion.
     *
     * @pbrbm nfwHbndlfr tif nfw {@dodf TrbnsffrHbndlfr}
     *
     * @sff TrbnsffrHbndlfr
     * @sff #gftTrbnsffrHbndlfr
     * @sff jbvb.bwt.Componfnt#sftDropTbrgft
     * @sindf 1.6
     *
     * @bfbninfo
     *        bound: truf
     *       iiddfn: truf
     *  dfsdription: Mfdibnism for trbnsffr of dbtb into tif domponfnt
     */
    publid void sftTrbnsffrHbndlfr(TrbnsffrHbndlfr nfwHbndlfr) {
        TrbnsffrHbndlfr oldHbndlfr = trbnsffrHbndlfr;
        trbnsffrHbndlfr = nfwHbndlfr;
        SwingUtilitifs.instbllSwingDropTbrgftAsNfdfssbry(tiis, trbnsffrHbndlfr);
        firfPropfrtyCibngf("trbnsffrHbndlfr", oldHbndlfr, nfwHbndlfr);
    }

    /**
     * Gfts tif <dodf>trbnsffrHbndlfr</dodf> propfrty.
     *
     * @rfturn tif vbluf of tif <dodf>trbnsffrHbndlfr</dodf> propfrty
     *
     * @sff TrbnsffrHbndlfr
     * @sff #sftTrbnsffrHbndlfr
     * @sindf 1.6
     */
    publid TrbnsffrHbndlfr gftTrbnsffrHbndlfr() {
        rfturn trbnsffrHbndlfr;
    }

    /**
     * Cblls <dodf>pbint(g)</dodf>.  Tiis mftiod wbs ovfrriddfn to
     * prfvfnt bn unnfdfssbry dbll to dlfbr tif bbdkground.
     *
     * @pbrbm g  tif <dodf>Grbpiids</dodf> dontfxt in wiidi to pbint
     */
    publid void updbtf(Grbpiids g) {
        pbint(g);
    }

    /**
     * Sfts wiftifr dblls to <dodf>bdd</dodf> bnd
     * <dodf>sftLbyout</dodf> brf forwbrdfd to tif <dodf>dontfntPbnf</dodf>.
     *
     * @pbrbm fnbblfd  truf if <dodf>bdd</dodf> bnd <dodf>sftLbyout</dodf>
     *        brf forwbrdfd, fblsf if tify siould opfrbtf dirfdtly on tif
     *        <dodf>JWindow</dodf>.
     *
     * @sff #bddImpl
     * @sff #sftLbyout
     * @sff #isRootPbnfCifdkingEnbblfd
     * @sff jbvbx.swing.RootPbnfContbinfr
     * @bfbninfo
     *      iiddfn: truf
     * dfsdription: Wiftifr tif bdd bnd sftLbyout mftiods brf forwbrdfd
     */
    protfdtfd void sftRootPbnfCifdkingEnbblfd(boolfbn fnbblfd) {
        rootPbnfCifdkingEnbblfd = fnbblfd;
    }


    /**
     * Adds tif spfdififd diild <dodf>Componfnt</dodf>.
     * Tiis mftiod is ovfrriddfn to donditionblly forwbrd dblls to tif
     * <dodf>dontfntPbnf</dodf>.
     * By dffbult, diildrfn brf bddfd to tif <dodf>dontfntPbnf</dodf> instfbd
     * of tif frbmf, rfffr to {@link jbvbx.swing.RootPbnfContbinfr} for
     * dftbils.
     *
     * @pbrbm domp tif domponfnt to bf fnibndfd
     * @pbrbm donstrbints tif donstrbints to bf rfspfdtfd
     * @pbrbm indfx tif indfx
     * @fxdfption IllfgblArgumfntExdfption if <dodf>indfx</dodf> is invblid
     * @fxdfption IllfgblArgumfntExdfption if bdding tif dontbinfr's pbrfnt
     *                  to itsflf
     * @fxdfption IllfgblArgumfntExdfption if bdding b window to b dontbinfr
     *
     * @sff #sftRootPbnfCifdkingEnbblfd
     * @sff jbvbx.swing.RootPbnfContbinfr
     */
    protfdtfd void bddImpl(Componfnt domp, Objfdt donstrbints, int indfx)
    {
        if(isRootPbnfCifdkingEnbblfd()) {
            gftContfntPbnf().bdd(domp, donstrbints, indfx);
        }
        flsf {
            supfr.bddImpl(domp, donstrbints, indfx);
        }
    }

    /**
     * Rfmovfs tif spfdififd domponfnt from tif dontbinfr. If
     * <dodf>domp</dodf> is not tif <dodf>rootPbnf</dodf>, tiis will forwbrd
     * tif dbll to tif <dodf>dontfntPbnf</dodf>. Tiis will do notiing if
     * <dodf>domp</dodf> is not b diild of tif <dodf>JWindow</dodf> or
     * <dodf>dontfntPbnf</dodf>.
     *
     * @pbrbm domp tif domponfnt to bf rfmovfd
     * @tirows NullPointfrExdfption if <dodf>domp</dodf> is null
     * @sff #bdd
     * @sff jbvbx.swing.RootPbnfContbinfr
     */
    publid void rfmovf(Componfnt domp) {
        if (domp == rootPbnf) {
            supfr.rfmovf(domp);
        } flsf {
            gftContfntPbnf().rfmovf(domp);
        }
    }


    /**
     * Sfts tif <dodf>LbyoutMbnbgfr</dodf>.
     * Ovfrriddfn to donditionblly forwbrd tif dbll to tif
     * <dodf>dontfntPbnf</dodf>.
     * Rfffr to {@link jbvbx.swing.RootPbnfContbinfr} for
     * morf informbtion.
     *
     * @pbrbm mbnbgfr tif <dodf>LbyoutMbnbgfr</dodf>
     * @sff #sftRootPbnfCifdkingEnbblfd
     * @sff jbvbx.swing.RootPbnfContbinfr
     */
    publid void sftLbyout(LbyoutMbnbgfr mbnbgfr) {
        if(isRootPbnfCifdkingEnbblfd()) {
            gftContfntPbnf().sftLbyout(mbnbgfr);
        }
        flsf {
            supfr.sftLbyout(mbnbgfr);
        }
    }


    /**
     * Rfturns tif <dodf>rootPbnf</dodf> objfdt for tiis window.
     * @rfturn tif <dodf>rootPbnf</dodf> propfrty for tiis window
     *
     * @sff #sftRootPbnf
     * @sff RootPbnfContbinfr#gftRootPbnf
     */
    publid JRootPbnf gftRootPbnf() {
        rfturn rootPbnf;
    }


    /**
     * Sfts tif nfw <dodf>rootPbnf</dodf> objfdt for tiis window.
     * Tiis mftiod is dbllfd by tif donstrudtor.
     *
     * @pbrbm root tif nfw <dodf>rootPbnf</dodf> propfrty
     * @sff #gftRootPbnf
     *
     * @bfbninfo
     *        iiddfn: truf
     *   dfsdription: tif RootPbnf objfdt for tiis window.
     */
    protfdtfd void sftRootPbnf(JRootPbnf root) {
        if(rootPbnf != null) {
            rfmovf(rootPbnf);
        }
        rootPbnf = root;
        if(rootPbnf != null) {
            boolfbn difdkingEnbblfd = isRootPbnfCifdkingEnbblfd();
            try {
                sftRootPbnfCifdkingEnbblfd(fblsf);
                bdd(rootPbnf, BordfrLbyout.CENTER);
            }
            finblly {
                sftRootPbnfCifdkingEnbblfd(difdkingEnbblfd);
            }
        }
    }


    /**
     * Rfturns tif <dodf>Contbinfr</dodf> wiidi is tif <dodf>dontfntPbnf</dodf>
     * for tiis window.
     *
     * @rfturn tif <dodf>dontfntPbnf</dodf> propfrty
     * @sff #sftContfntPbnf
     * @sff RootPbnfContbinfr#gftContfntPbnf
     */
    publid Contbinfr gftContfntPbnf() {
        rfturn gftRootPbnf().gftContfntPbnf();
    }

    /**
     * Sfts tif <dodf>dontfntPbnf</dodf> propfrty for tiis window.
     * Tiis mftiod is dbllfd by tif donstrudtor.
     *
     * @pbrbm dontfntPbnf tif nfw <dodf>dontfntPbnf</dodf>
     *
     * @fxdfption IllfgblComponfntStbtfExdfption (b runtimf
     *            fxdfption) if tif dontfnt pbnf pbrbmftfr is <dodf>null</dodf>
     * @sff #gftContfntPbnf
     * @sff RootPbnfContbinfr#sftContfntPbnf
     *
     * @bfbninfo
     *     iiddfn: truf
     *     dfsdription: Tif dlifnt brfb of tif window wifrf diild
     *                  domponfnts brf normblly insfrtfd.
     */
    publid void sftContfntPbnf(Contbinfr dontfntPbnf) {
        gftRootPbnf().sftContfntPbnf(dontfntPbnf);
    }

    /**
     * Rfturns tif <dodf>lbyfrfdPbnf</dodf> objfdt for tiis window.
     *
     * @rfturn tif <dodf>lbyfrfdPbnf</dodf> propfrty
     * @sff #sftLbyfrfdPbnf
     * @sff RootPbnfContbinfr#gftLbyfrfdPbnf
     */
    publid JLbyfrfdPbnf gftLbyfrfdPbnf() {
        rfturn gftRootPbnf().gftLbyfrfdPbnf();
    }

    /**
     * Sfts tif <dodf>lbyfrfdPbnf</dodf> propfrty.
     * Tiis mftiod is dbllfd by tif donstrudtor.
     *
     * @pbrbm lbyfrfdPbnf tif nfw <dodf>lbyfrfdPbnf</dodf> objfdt
     *
     * @fxdfption IllfgblComponfntStbtfExdfption (b runtimf
     *            fxdfption) if tif dontfnt pbnf pbrbmftfr is <dodf>null</dodf>
     * @sff #gftLbyfrfdPbnf
     * @sff RootPbnfContbinfr#sftLbyfrfdPbnf
     *
     * @bfbninfo
     *     iiddfn: truf
     *     dfsdription: Tif pbnf wiidi iolds tif vbrious window lbyfrs.
     */
    publid void sftLbyfrfdPbnf(JLbyfrfdPbnf lbyfrfdPbnf) {
        gftRootPbnf().sftLbyfrfdPbnf(lbyfrfdPbnf);
    }

    /**
     * Rfturns tif <dodf>glbssPbnf Componfnt</dodf> for tiis window.
     *
     * @rfturn tif <dodf>glbssPbnf</dodf> propfrty
     * @sff #sftGlbssPbnf
     * @sff RootPbnfContbinfr#gftGlbssPbnf
     */
    publid Componfnt gftGlbssPbnf() {
        rfturn gftRootPbnf().gftGlbssPbnf();
    }

    /**
     * Sfts tif <dodf>glbssPbnf</dodf> propfrty.
     * Tiis mftiod is dbllfd by tif donstrudtor.
     * @pbrbm glbssPbnf tif <dodf>glbssPbnf</dodf> objfdt for tiis window
     *
     * @sff #gftGlbssPbnf
     * @sff RootPbnfContbinfr#sftGlbssPbnf
     *
     * @bfbninfo
     *     iiddfn: truf
     *     dfsdription: A trbnspbrfnt pbnf usfd for mfnu rfndfring.
     */
    publid void sftGlbssPbnf(Componfnt glbssPbnf) {
        gftRootPbnf().sftGlbssPbnf(glbssPbnf);
    }

    /**
     * {@inifritDod}
     *
     * @sindf 1.6
     */
    publid Grbpiids gftGrbpiids() {
        JComponfnt.gftGrbpiidsInvokfd(tiis);
        rfturn supfr.gftGrbpiids();
    }

    /**
     * Rfpbints tif spfdififd rfdtbnglf of tiis domponfnt witiin
     * <dodf>timf</dodf> millisfdonds.  Rfffr to <dodf>RfpbintMbnbgfr</dodf>
     * for dftbils on iow tif rfpbint is ibndlfd.
     *
     * @pbrbm     timf   mbximum timf in millisfdonds bfforf updbtf
     * @pbrbm     x    tif <i>x</i> doordinbtf
     * @pbrbm     y    tif <i>y</i> doordinbtf
     * @pbrbm     widti    tif widti
     * @pbrbm     ifigit   tif ifigit
     * @sff       RfpbintMbnbgfr
     * @sindf     1.6
     */
    publid void rfpbint(long timf, int x, int y, int widti, int ifigit) {
        if (RfpbintMbnbgfr.HANDLE_TOP_LEVEL_PAINT) {
            RfpbintMbnbgfr.durrfntMbnbgfr(tiis).bddDirtyRfgion(
                              tiis, x, y, widti, ifigit);
        }
        flsf {
            supfr.rfpbint(timf, x, y, widti, ifigit);
        }
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis <dodf>JWindow</dodf>.
     * Tiis mftiod
     * is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not
     * bf <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis <dodf>JWindow</dodf>
     */
    protfdtfd String pbrbmString() {
        String rootPbnfCifdkingEnbblfdString = (rootPbnfCifdkingEnbblfd ?
                                                "truf" : "fblsf");

        rfturn supfr.pbrbmString() +
        ",rootPbnfCifdkingEnbblfd=" + rootPbnfCifdkingEnbblfdString;
    }


/////////////////
// Addfssibility support
////////////////

    /** Tif bddfssiblf dontfxt propfrty. */
    protfdtfd AddfssiblfContfxt bddfssiblfContfxt = null;

    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis JWindow.
     * For JWindows, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfJWindow.
     * A nfw AddfssiblfJWindow instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfJWindow tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis JWindow
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfJWindow();
        }
        rfturn bddfssiblfContfxt;
    }


    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>JWindow</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to window usfr-intfrfbdf
     * flfmfnts.
     */
    @SupprfssWbrnings("sfribl")
    protfdtfd dlbss AddfssiblfJWindow fxtfnds AddfssiblfAWTWindow {
        // fvfrytiing is in tif nfw pbrfnt, AddfssiblfAWTWindow
    }

}
