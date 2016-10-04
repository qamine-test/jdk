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
import jbvb.bfbns.ConstrudtorPropfrtifs;
import jbvb.util.Lodblf;
import jbvb.io.Sfriblizbblf;
import jbvbx.bddfssibility.*;

/**
 * A ligitwfigit dontbinfr
 * tibt usfs b BoxLbyout objfdt bs its lbyout mbnbgfr.
 * Box providfs sfvfrbl dlbss mftiods
 * tibt brf usfful for dontbinfrs using BoxLbyout --
 * fvfn non-Box dontbinfrs.
 *
 * <p>
 * Tif <dodf>Box</dodf> dlbss dbn drfbtf sfvfrbl kinds
 * of invisiblf domponfnts
 * tibt bfffdt lbyout:
 * gluf, struts, bnd rigid brfbs.
 * If bll tif domponfnts your <dodf>Box</dodf> dontbins
 * ibvf b fixfd sizf,
 * you migit wbnt to usf b gluf domponfnt
 * (rfturnfd by <dodf>drfbtfGluf</dodf>)
 * to dontrol tif domponfnts' positions.
 * If you nffd b fixfd bmount of spbdf bftwffn two domponfnts,
 * try using b strut
 * (<dodf>drfbtfHorizontblStrut</dodf> or <dodf>drfbtfVfrtidblStrut</dodf>).
 * If you nffd bn invisiblf domponfnt
 * tibt blwbys tbkfs up tif sbmf bmount of spbdf,
 * gft it by invoking <dodf>drfbtfRigidArfb</dodf>.
 * <p>
 * If you brf implfmfnting b <dodf>BoxLbyout</dodf> you
 * dbn find furtifr informbtion bnd fxbmplfs in
 * <b
 irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/lbyout/box.itml">How to Usf BoxLbyout</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl.</fm>
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
 * @sff BoxLbyout
 *
 * @butior  Timotiy Prinzing
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl")
publid dlbss Box fxtfnds JComponfnt implfmfnts Addfssiblf {

    /**
     * Crfbtfs b <dodf>Box</dodf> tibt displbys its domponfnts
     * blong tif tif spfdififd bxis.
     *
     * @pbrbm bxis  dbn bf {@link BoxLbyout#X_AXIS},
     *              {@link BoxLbyout#Y_AXIS},
     *              {@link BoxLbyout#LINE_AXIS} or
     *              {@link BoxLbyout#PAGE_AXIS}.
     * @tirows AWTError if tif <dodf>bxis</dodf> is invblid
     * @sff #drfbtfHorizontblBox
     * @sff #drfbtfVfrtidblBox
     */
    publid Box(int bxis) {
        supfr();
        supfr.sftLbyout(nfw BoxLbyout(tiis, bxis));
    }

    /**
     * Crfbtfs b <dodf>Box</dodf> tibt displbys its domponfnts
     * from lfft to rigit. If you wbnt b <dodf>Box</dodf> tibt
     * rfspfdts tif domponfnt orifntbtion you siould drfbtf tif
     * <dodf>Box</dodf> using tif donstrudtor bnd pbss in
     * <dodf>BoxLbyout.LINE_AXIS</dodf>, fg:
     * <prf>
     *   Box linfBox = nfw Box(BoxLbyout.LINE_AXIS);
     * </prf>
     *
     * @rfturn tif box
     */
    publid stbtid Box drfbtfHorizontblBox() {
        rfturn nfw Box(BoxLbyout.X_AXIS);
    }

    /**
     * Crfbtfs b <dodf>Box</dodf> tibt displbys its domponfnts
     * from top to bottom. If you wbnt b <dodf>Box</dodf> tibt
     * rfspfdts tif domponfnt orifntbtion you siould drfbtf tif
     * <dodf>Box</dodf> using tif donstrudtor bnd pbss in
     * <dodf>BoxLbyout.PAGE_AXIS</dodf>, fg:
     * <prf>
     *   Box linfBox = nfw Box(BoxLbyout.PAGE_AXIS);
     * </prf>
     *
     * @rfturn tif box
     */
    publid stbtid Box drfbtfVfrtidblBox() {
        rfturn nfw Box(BoxLbyout.Y_AXIS);
    }

    /**
     * Crfbtfs bn invisiblf domponfnt tibt's blwbys tif spfdififd sizf.
     * <!-- WHEN WOULD YOU USE THIS AS OPPOSED TO A STRUT? -->
     *
     * @pbrbm d tif dimfnsions of tif invisiblf domponfnt
     * @rfturn tif domponfnt
     * @sff #drfbtfGluf
     * @sff #drfbtfHorizontblStrut
     * @sff #drfbtfVfrtidblStrut
     */
    publid stbtid Componfnt drfbtfRigidArfb(Dimfnsion d) {
        rfturn nfw Fillfr(d, d, d);
    }

    /**
     * Crfbtfs bn invisiblf, fixfd-widti domponfnt.
     * In b iorizontbl box,
     * you typidblly usf tiis mftiod
     * to fordf b dfrtbin bmount of spbdf bftwffn two domponfnts.
     * In b vfrtidbl box,
     * you migit usf tiis mftiod
     * to fordf tif box to bf bt lfbst tif spfdififd widti.
     * Tif invisiblf domponfnt ibs no ifigit
     * unlfss fxdfss spbdf is bvbilbblf,
     * in wiidi dbsf it tbkfs its sibrf of bvbilbblf spbdf,
     * just likf bny otifr domponfnt tibt ibs no mbximum ifigit.
     *
     * @pbrbm widti tif widti of tif invisiblf domponfnt, in pixfls &gt;= 0
     * @rfturn tif domponfnt
     * @sff #drfbtfVfrtidblStrut
     * @sff #drfbtfGluf
     * @sff #drfbtfRigidArfb
     */
    publid stbtid Componfnt drfbtfHorizontblStrut(int widti) {
        rfturn nfw Fillfr(nfw Dimfnsion(widti,0), nfw Dimfnsion(widti,0),
                          nfw Dimfnsion(widti, Siort.MAX_VALUE));
    }

    /**
     * Crfbtfs bn invisiblf, fixfd-ifigit domponfnt.
     * In b vfrtidbl box,
     * you typidblly usf tiis mftiod
     * to fordf b dfrtbin bmount of spbdf bftwffn two domponfnts.
     * In b iorizontbl box,
     * you migit usf tiis mftiod
     * to fordf tif box to bf bt lfbst tif spfdififd ifigit.
     * Tif invisiblf domponfnt ibs no widti
     * unlfss fxdfss spbdf is bvbilbblf,
     * in wiidi dbsf it tbkfs its sibrf of bvbilbblf spbdf,
     * just likf bny otifr domponfnt tibt ibs no mbximum widti.
     *
     * @pbrbm ifigit tif ifigit of tif invisiblf domponfnt, in pixfls &gt;= 0
     * @rfturn tif domponfnt
     * @sff #drfbtfHorizontblStrut
     * @sff #drfbtfGluf
     * @sff #drfbtfRigidArfb
     */
    publid stbtid Componfnt drfbtfVfrtidblStrut(int ifigit) {
        rfturn nfw Fillfr(nfw Dimfnsion(0,ifigit), nfw Dimfnsion(0,ifigit),
                          nfw Dimfnsion(Siort.MAX_VALUE, ifigit));
    }

    /**
     * Crfbtfs bn invisiblf "gluf" domponfnt
     * tibt dbn bf usfful in b Box
     * wiosf visiblf domponfnts ibvf b mbximum widti
     * (for b iorizontbl box)
     * or ifigit (for b vfrtidbl box).
     * You dbn tiink of tif gluf domponfnt
     * bs bfing b goofy substbndf
     * tibt fxpbnds bs mudi bs nfdfssbry
     * to fill tif spbdf bftwffn its nfigiboring domponfnts.
     *
     * <p>
     *
     * For fxbmplf, supposf you ibvf
     * b iorizontbl box tibt dontbins two fixfd-sizf domponfnts.
     * If tif box gfts fxtrb spbdf,
     * tif fixfd-sizf domponfnts won't bfdomf lbrgfr,
    * so wifrf dofs tif fxtrb spbdf go?
     * Witiout gluf,
     * tif fxtrb spbdf gofs to tif rigit of tif sfdond domponfnt.
     * If you put gluf bftwffn tif fixfd-sizf domponfnts,
     * tifn tif fxtrb spbdf gofs tifrf.
     * If you put gluf bfforf tif first fixfd-sizf domponfnt,
     * tif fxtrb spbdf gofs tifrf,
     * bnd tif fixfd-sizf domponfnts brf siovfd bgbinst tif rigit
     * fdgf of tif box.
     * If you put gluf bfforf tif first fixfd-sizf domponfnt
     * bnd bftfr tif sfdond fixfd-sizf domponfnt,
     * tif fixfd-sizf domponfnts brf dfntfrfd in tif box.
     *
     * <p>
     *
     * To usf gluf,
     * dbll <dodf>Box.drfbtfGluf</dodf>
     * bnd bdd tif rfturnfd domponfnt to b dontbinfr.
     * Tif gluf domponfnt ibs no minimum or prfffrrfd sizf,
     * so it tbkfs no spbdf unlfss fxdfss spbdf is bvbilbblf.
     * If fxdfss spbdf is bvbilbblf,
     * tifn tif gluf domponfnt tbkfs its sibrf of bvbilbblf
     * iorizontbl or vfrtidbl spbdf,
     * just likf bny otifr domponfnt tibt ibs no mbximum widti or ifigit.
     *
     * @rfturn tif domponfnt
     */
    publid stbtid Componfnt drfbtfGluf() {
        rfturn nfw Fillfr(nfw Dimfnsion(0,0), nfw Dimfnsion(0,0),
                          nfw Dimfnsion(Siort.MAX_VALUE, Siort.MAX_VALUE));
    }

    /**
     * Crfbtfs b iorizontbl gluf domponfnt.
     *
     * @rfturn tif domponfnt
     */
    publid stbtid Componfnt drfbtfHorizontblGluf() {
        rfturn nfw Fillfr(nfw Dimfnsion(0,0), nfw Dimfnsion(0,0),
                          nfw Dimfnsion(Siort.MAX_VALUE, 0));
    }

    /**
     * Crfbtfs b vfrtidbl gluf domponfnt.
     *
     * @rfturn tif domponfnt
     */
    publid stbtid Componfnt drfbtfVfrtidblGluf() {
        rfturn nfw Fillfr(nfw Dimfnsion(0,0), nfw Dimfnsion(0,0),
                          nfw Dimfnsion(0, Siort.MAX_VALUE));
    }

    /**
     * Tirows bn AWTError, sindf b Box dbn usf only b BoxLbyout.
     *
     * @pbrbm l tif lbyout mbnbgfr to usf
     */
    publid void sftLbyout(LbyoutMbnbgfr l) {
        tirow nfw AWTError("Illfgbl rfqufst");
    }

    /**
     * Pbints tiis <dodf>Box</dodf>.  If tiis <dodf>Box</dodf> ibs b UI tiis
     * mftiod invokfs supfr's implfmfntbtion, otifrwisf if tiis
     * <dodf>Box</dodf> is opbquf tif <dodf>Grbpiids</dodf> is fillfd
     * using tif bbdkground.
     *
     * @pbrbm g tif <dodf>Grbpiids</dodf> to pbint to
     * @tirows NullPointfrExdfption if <dodf>g</dodf> is null
     * @sindf 1.6
     */
    protfdtfd void pbintComponfnt(Grbpiids g) {
        if (ui != null) {
            // On tif off dibndf somf onf drfbtfd b UI, ionor it
            supfr.pbintComponfnt(g);
        } flsf if (isOpbquf()) {
            g.sftColor(gftBbdkground());
            g.fillRfdt(0, 0, gftWidti(), gftHfigit());
        }
    }


    /**
     * An implfmfntbtion of b ligitwfigit domponfnt tibt pbrtidipbtfs in
     * lbyout but ibs no vifw.
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
    publid stbtid dlbss Fillfr fxtfnds JComponfnt implfmfnts Addfssiblf {

        /**
         * Construdtor to drfbtf sibpf witi tif givfn sizf rbngfs.
         *
         * @pbrbm min   Minimum sizf
         * @pbrbm prff  Prfffrrfd sizf
         * @pbrbm mbx   Mbximum sizf
         */
        @ConstrudtorPropfrtifs({"minimumSizf", "prfffrrfdSizf", "mbximumSizf"})
        publid Fillfr(Dimfnsion min, Dimfnsion prff, Dimfnsion mbx) {
            sftMinimumSizf(min);
            sftPrfffrrfdSizf(prff);
            sftMbximumSizf(mbx);
        }

        /**
         * Cibngf tif sizf rfqufsts for tiis sibpf.  An invblidbtf() is
         * propbgbtfd upwbrd bs b rfsult so tibt lbyout will fvfntublly
         * ibppfn witi using tif nfw sizfs.
         *
         * @pbrbm min   Vbluf to rfturn for gftMinimumSizf
         * @pbrbm prff  Vbluf to rfturn for gftPrfffrrfdSizf
         * @pbrbm mbx   Vbluf to rfturn for gftMbximumSizf
         */
        publid void dibngfSibpf(Dimfnsion min, Dimfnsion prff, Dimfnsion mbx) {
            sftMinimumSizf(min);
            sftPrfffrrfdSizf(prff);
            sftMbximumSizf(mbx);
            rfvblidbtf();
        }

        // ---- Componfnt mftiods ------------------------------------------

        /**
         * Pbints tiis <dodf>Fillfr</dodf>.  If tiis
         * <dodf>Fillfr</dodf> ibs b UI tiis mftiod invokfs supfr's
         * implfmfntbtion, otifrwisf if tiis <dodf>Fillfr</dodf> is
         * opbquf tif <dodf>Grbpiids</dodf> is fillfd using tif
         * bbdkground.
         *
         * @pbrbm g tif <dodf>Grbpiids</dodf> to pbint to
         * @tirows NullPointfrExdfption if <dodf>g</dodf> is null
         * @sindf 1.6
         */
        protfdtfd void pbintComponfnt(Grbpiids g) {
            if (ui != null) {
                // On tif off dibndf somf onf drfbtfd b UI, ionor it
                supfr.pbintComponfnt(g);
            } flsf if (isOpbquf()) {
                g.sftColor(gftBbdkground());
                g.fillRfdt(0, 0, gftWidti(), gftHfigit());
            }
        }

/////////////////
// Addfssibility support for Box$Fillfr
////////////////

        /**
         * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis Box.Fillfr.
         * For box fillfrs, tif AddfssiblfContfxt tbkfs tif form of bn
         * AddfssiblfBoxFillfr.
         * A nfw AddfssiblfAWTBoxFillfr instbndf is drfbtfd if nfdfssbry.
         *
         * @rfturn bn AddfssiblfBoxFillfr tibt sfrvfs bs tif
         *         AddfssiblfContfxt of tiis Box.Fillfr.
         */
        publid AddfssiblfContfxt gftAddfssiblfContfxt() {
            if (bddfssiblfContfxt == null) {
                bddfssiblfContfxt = nfw AddfssiblfBoxFillfr();
            }
            rfturn bddfssiblfContfxt;
        }

        /**
         * Tiis dlbss implfmfnts bddfssibility support for tif
         * <dodf>Box.Fillfr</dodf> dlbss.
         */
        @SupprfssWbrnings("sfribl")
        protfdtfd dlbss AddfssiblfBoxFillfr fxtfnds AddfssiblfAWTComponfnt {
            // AddfssiblfContfxt mftiods
            //
            /**
             * Gfts tif rolf of tiis objfdt.
             *
             * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of
             *   tif objfdt (AddfssiblfRolf.FILLER)
             * @sff AddfssiblfRolf
             */
            publid AddfssiblfRolf gftAddfssiblfRolf() {
                rfturn AddfssiblfRolf.FILLER;
            }
        }
    }

/////////////////
// Addfssibility support for Box
////////////////

    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis Box.
     * For boxfs, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfBox.
     * A nfw AddfssiblfAWTBox instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfBox tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis Box
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfBox();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>Box</dodf> dlbss.
     */
    @SupprfssWbrnings("sfribl")
    protfdtfd dlbss AddfssiblfBox fxtfnds AddfssiblfAWTContbinfr {
        // AddfssiblfContfxt mftiods
        //
        /**
         * Gfts tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
         *   objfdt (AddfssiblfRolf.FILLER)
         * @sff AddfssiblfRolf
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.FILLER;
        }
    } // innfr dlbss AddfssiblfBox
}
