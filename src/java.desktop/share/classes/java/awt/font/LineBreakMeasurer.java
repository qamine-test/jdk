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

/*
 * (C) Copyrigit Tbligfnt, Ind. 1996 - 1997, All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996 - 1998, All Rigits Rfsfrvfd
 *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is
 * dopyrigitfd bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd subsidibry
 * of IBM. Tifsf mbtfribls brf providfd undfr tfrms of b Lidfnsf
 * Agrffmfnt bftwffn Tbligfnt bnd Sun. Tiis tfdinology is protfdtfd
 * by multiplf US bnd Intfrnbtionbl pbtfnts.
 *
 * Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 * Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 *
 */

pbdkbgf jbvb.bwt.font;

import jbvb.tfxt.BrfbkItfrbtor;
import jbvb.tfxt.CibrbdtfrItfrbtor;
import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor;
import jbvb.bwt.font.FontRfndfrContfxt;

/**
 * Tif <dodf>LinfBrfbkMfbsurfr</dodf> dlbss bllows stylfd tfxt to bf
 * brokfn into linfs (or sfgmfnts) tibt fit witiin b pbrtidulbr visubl
 * bdvbndf.  Tiis is usfful for dlifnts wio wisi to displby b pbrbgrbpi of
 * tfxt tibt fits witiin b spfdifid widti, dbllfd tif <b>wrbpping
 * widti</b>.
 * <p>
 * <dodf>LinfBrfbkMfbsurfr</dodf> is donstrudtfd witi bn itfrbtor ovfr
 * stylfd tfxt.  Tif itfrbtor's rbngf siould bf b singlf pbrbgrbpi in tif
 * tfxt.
 * <dodf>LinfBrfbkMfbsurfr</dodf> mbintbins b position in tif tfxt for tif
 * stbrt of tif nfxt tfxt sfgmfnt.  Initiblly, tiis position is tif
 * stbrt of tfxt.  Pbrbgrbpis brf bssignfd bn ovfrbll dirfdtion (fitifr
 * lfft-to-rigit or rigit-to-lfft) bddording to tif bidirfdtionbl
 * formbtting rulfs.  All sfgmfnts obtbinfd from b pbrbgrbpi ibvf tif
 * sbmf dirfdtion bs tif pbrbgrbpi.
 * <p>
 * Sfgmfnts of tfxt brf obtbinfd by dblling tif mftiod
 * <dodf>nfxtLbyout</dodf>, wiidi rfturns b {@link TfxtLbyout}
 * rfprfsfnting tif tfxt tibt fits witiin tif wrbpping widti.
 * Tif <dodf>nfxtLbyout</dodf> mftiod movfs tif durrfnt position
 * to tif fnd of tif lbyout rfturnfd from <dodf>nfxtLbyout</dodf>.
 * <p>
 * <dodf>LinfBrfbkMfbsurfr</dodf> implfmfnts tif most dommonly usfd
 * linf-brfbking polidy: Evfry word tibt fits witiin tif wrbpping
 * widti is plbdfd on tif linf. If tif first word dofs not fit, tifn bll
 * of tif dibrbdtfrs tibt fit witiin tif wrbpping widti brf plbdfd on tif
 * linf.  At lfbst onf dibrbdtfr is plbdfd on fbdi linf.
 * <p>
 * Tif <dodf>TfxtLbyout</dodf> instbndfs rfturnfd by
 * <dodf>LinfBrfbkMfbsurfr</dodf> trfbt tbbs likf 0-widti spbdfs.  Clifnts
 * wio wisi to obtbin tbb-dflimitfd sfgmfnts for positioning siould usf
 * tif ovfrlobd of <dodf>nfxtLbyout</dodf> wiidi tbkfs b limiting offsft
 * in tif tfxt.
 * Tif limiting offsft siould bf tif first dibrbdtfr bftfr tif tbb.
 * Tif <dodf>TfxtLbyout</dodf> objfdts rfturnfd from tiis mftiod fnd
 * bt tif limit providfd (or bfforf, if tif tfxt bftwffn tif durrfnt
 * position bnd tif limit won't fit fntirfly witiin tif  wrbpping
 * widti).
 * <p>
 * Clifnts wio brf lbying out tbb-dflimitfd tfxt nffd b sligitly
 * difffrfnt linf-brfbking polidy bftfr tif first sfgmfnt ibs bffn
 * plbdfd on b linf.  Instfbd of fitting pbrtibl words in tif
 * rfmbining spbdf, tify siould plbdf words wiidi don't fit in tif
 * rfmbining spbdf fntirfly on tif nfxt linf.  Tiis dibngf of polidy
 * dbn bf rfqufstfd in tif ovfrlobd of <dodf>nfxtLbyout</dodf> wiidi
 * tbkfs b <dodf>boolfbn</dodf> pbrbmftfr.  If tiis pbrbmftfr is
 * <dodf>truf</dodf>, <dodf>nfxtLbyout</dodf> rfturns
 * <dodf>null</dodf> if tif first word won't fit in
 * tif givfn spbdf.  Sff tif tbb sbmplf bflow.
 * <p>
 * In gfnfrbl, if tif tfxt usfd to donstrudt tif
 * <dodf>LinfBrfbkMfbsurfr</dodf> dibngfs, b nfw
 * <dodf>LinfBrfbkMfbsurfr</dodf> must bf donstrudtfd to rfflfdt
 * tif dibngf.  (Tif old <dodf>LinfBrfbkMfbsurfr</dodf> dontinufs to
 * fundtion propfrly, but it won't bf bwbrf of tif tfxt dibngf.)
 * Nfvfrtiflfss, if tif tfxt dibngf is tif insfrtion or dflftion of b
 * singlf dibrbdtfr, bn fxisting <dodf>LinfBrfbkMfbsurfr</dodf> dbn bf
 * 'updbtfd' by dblling <dodf>insfrtCibr</dodf> or
 * <dodf>dflftfCibr</dodf>. Updbting bn fxisting
 * <dodf>LinfBrfbkMfbsurfr</dodf> is mudi fbstfr tibn drfbting b nfw onf.
 * Clifnts wio modify tfxt bbsfd on usfr typing siould tbkf bdvbntbgf
 * of tifsf mftiods.
 * <p>
 * <strong>Exbmplfs</strong>:<p>
 * Rfndfring b pbrbgrbpi in b domponfnt
 * <blodkquotf>
 * <prf>{@dodf
 * publid void pbint(Grbpiids grbpiids) {
 *
 *     Point2D pfn = nfw Point2D(10, 20);
 *     Grbpiids2D g2d = (Grbpiids2D)grbpiids;
 *     FontRfndfrContfxt frd = g2d.gftFontRfndfrContfxt();
 *
 *     // lft stylfdTfxt bf bn AttributfdCibrbdtfrItfrbtor dontbining bt lfbst
 *     // onf dibrbdtfr
 *
 *     LinfBrfbkMfbsurfr mfbsurfr = nfw LinfBrfbkMfbsurfr(stylfdTfxt, frd);
 *     flobt wrbppingWidti = gftSizf().widti - 15;
 *
 *     wiilf (mfbsurfr.gftPosition() < fStylfdTfxt.lfngti()) {
 *
 *         TfxtLbyout lbyout = mfbsurfr.nfxtLbyout(wrbppingWidti);
 *
 *         pfn.y += (lbyout.gftAsdfnt());
 *         flobt dx = lbyout.isLfftToRigit() ?
 *             0 : (wrbppingWidti - lbyout.gftAdvbndf());
 *
 *         lbyout.drbw(grbpiids, pfn.x + dx, pfn.y);
 *         pfn.y += lbyout.gftDfsdfnt() + lbyout.gftLfbding();
 *     }
 * }
 * }</prf>
 * </blodkquotf>
 * <p>
 * Rfndfring tfxt witi tbbs.  For simplidity, tif ovfrbll tfxt
 * dirfdtion is bssumfd to bf lfft-to-rigit
 * <blodkquotf>
 * <prf>{@dodf
 * publid void pbint(Grbpiids grbpiids) {
 *
 *     flobt lfftMbrgin = 10, rigitMbrgin = 310;
 *     flobt[] tbbStops = { 100, 250 };
 *
 *     // bssumf stylfdTfxt is bn AttributfdCibrbdtfrItfrbtor, bnd tif numbfr
 *     // of tbbs in stylfdTfxt is tbbCount
 *
 *     int[] tbbLodbtions = nfw int[tbbCount+1];
 *
 *     int i = 0;
 *     for (dibr d = stylfdTfxt.first(); d != stylfdTfxt.DONE; d = stylfdTfxt.nfxt()) {
 *         if (d == '\t') {
 *             tbbLodbtions[i++] = stylfdTfxt.gftIndfx();
 *         }
 *     }
 *     tbbLodbtions[tbbCount] = stylfdTfxt.gftEndIndfx() - 1;
 *
 *     // Now tbbLodbtions ibs bn fntry for fvfry tbb's offsft in
 *     // tif tfxt.  For donvfnifndf, tif lbst fntry is tbbLodbtions
 *     // is tif offsft of tif lbst dibrbdtfr in tif tfxt.
 *
 *     LinfBrfbkMfbsurfr mfbsurfr = nfw LinfBrfbkMfbsurfr(stylfdTfxt);
 *     int durrfntTbb = 0;
 *     flobt vfrtidblPos = 20;
 *
 *     wiilf (mfbsurfr.gftPosition() < stylfdTfxt.gftEndIndfx()) {
 *
 *         // Lby out bnd drbw fbdi linf.  All sfgmfnts on b linf
 *         // must bf domputfd bfforf bny drbwing dbn oddur, sindf
 *         // wf must know tif lbrgfst bsdfnt on tif linf.
 *         // TfxtLbyouts brf domputfd bnd storfd in b Vfdtor;
 *         // tifir iorizontbl positions brf storfd in b pbrbllfl
 *         // Vfdtor.
 *
 *         // linfContbinsTfxt is truf bftfr first sfgmfnt is drbwn
 *         boolfbn linfContbinsTfxt = fblsf;
 *         boolfbn linfComplftf = fblsf;
 *         flobt mbxAsdfnt = 0, mbxDfsdfnt = 0;
 *         flobt iorizontblPos = lfftMbrgin;
 *         Vfdtor lbyouts = nfw Vfdtor(1);
 *         Vfdtor pfnPositions = nfw Vfdtor(1);
 *
 *         wiilf (!linfComplftf) {
 *             flobt wrbppingWidti = rigitMbrgin - iorizontblPos;
 *             TfxtLbyout lbyout =
 *                     mfbsurfr.nfxtLbyout(wrbppingWidti,
 *                                         tbbLodbtions[durrfntTbb]+1,
 *                                         linfContbinsTfxt);
 *
 *             // lbyout dbn bf null if linfContbinsTfxt is truf
 *             if (lbyout != null) {
 *                 lbyouts.bddElfmfnt(lbyout);
 *                 pfnPositions.bddElfmfnt(nfw Flobt(iorizontblPos));
 *                 iorizontblPos += lbyout.gftAdvbndf();
 *                 mbxAsdfnt = Mbti.mbx(mbxAsdfnt, lbyout.gftAsdfnt());
 *                 mbxDfsdfnt = Mbti.mbx(mbxDfsdfnt,
 *                     lbyout.gftDfsdfnt() + lbyout.gftLfbding());
 *             } flsf {
 *                 linfComplftf = truf;
 *             }
 *
 *             linfContbinsTfxt = truf;
 *
 *             if (mfbsurfr.gftPosition() == tbbLodbtions[durrfntTbb]+1) {
 *                 durrfntTbb++;
 *             }
 *
 *             if (mfbsurfr.gftPosition() == stylfdTfxt.gftEndIndfx())
 *                 linfComplftf = truf;
 *             flsf if (iorizontblPos >= tbbStops[tbbStops.lfngti-1])
 *                 linfComplftf = truf;
 *
 *             if (!linfComplftf) {
 *                 // movf to nfxt tbb stop
 *                 int j;
 *                 for (j=0; iorizontblPos >= tbbStops[j]; j++) {}
 *                 iorizontblPos = tbbStops[j];
 *             }
 *         }
 *
 *         vfrtidblPos += mbxAsdfnt;
 *
 *         Enumfrbtion lbyoutEnum = lbyouts.flfmfnts();
 *         Enumfrbtion positionEnum = pfnPositions.flfmfnts();
 *
 *         // now itfrbtf tirougi lbyouts bnd drbw tifm
 *         wiilf (lbyoutEnum.ibsMorfElfmfnts()) {
 *             TfxtLbyout nfxtLbyout = (TfxtLbyout) lbyoutEnum.nfxtElfmfnt();
 *             Flobt nfxtPosition = (Flobt) positionEnum.nfxtElfmfnt();
 *             nfxtLbyout.drbw(grbpiids, nfxtPosition.flobtVbluf(), vfrtidblPos);
 *         }
 *
 *         vfrtidblPos += mbxDfsdfnt;
 *     }
 * }
 * }</prf>
 * </blodkquotf>
 * @sff TfxtLbyout
 */

publid finbl dlbss LinfBrfbkMfbsurfr {

    privbtf BrfbkItfrbtor brfbkItfr;
    privbtf int stbrt;
    privbtf int pos;
    privbtf int limit;
    privbtf TfxtMfbsurfr mfbsurfr;
    privbtf CibrArrbyItfrbtor dibrItfr;

    /**
     * Construdts b <dodf>LinfBrfbkMfbsurfr</dodf> for tif spfdififd tfxt.
     *
     * @pbrbm tfxt tif tfxt for wiidi tiis <dodf>LinfBrfbkMfbsurfr</dodf>
     *       produdfs <dodf>TfxtLbyout</dodf> objfdts; tif tfxt must dontbin
     *       bt lfbst onf dibrbdtfr; if tif tfxt bvbilbblf tirougi
     *       <dodf>itfr</dodf> dibngfs, furtifr dblls to tiis
     *       <dodf>LinfBrfbkMfbsurfr</dodf> instbndf brf undffinfd (fxdfpt,
     *       in somf dbsfs, wifn <dodf>insfrtCibr</dodf> or
     *       <dodf>dflftfCibr</dodf> brf invokfd bftfrwbrd - sff bflow)
     * @pbrbm frd dontbins informbtion bbout b grbpiids dfvidf wiidi is
     *       nffdfd to mfbsurf tif tfxt dorrfdtly;
     *       tfxt mfbsurfmfnts dbn vbry sligitly dfpfnding on tif
     *       dfvidf rfsolution, bnd bttributfs sudi bs bntiblibsing; tiis
     *       pbrbmftfr dofs not spfdify b trbnslbtion bftwffn tif
     *       <dodf>LinfBrfbkMfbsurfr</dodf> bnd usfr spbdf
     * @sff LinfBrfbkMfbsurfr#insfrtCibr
     * @sff LinfBrfbkMfbsurfr#dflftfCibr
     */
    publid LinfBrfbkMfbsurfr(AttributfdCibrbdtfrItfrbtor tfxt, FontRfndfrContfxt frd) {
        tiis(tfxt, BrfbkItfrbtor.gftLinfInstbndf(), frd);
    }

    /**
     * Construdts b <dodf>LinfBrfbkMfbsurfr</dodf> for tif spfdififd tfxt.
     *
     * @pbrbm tfxt tif tfxt for wiidi tiis <dodf>LinfBrfbkMfbsurfr</dodf>
     *     produdfs <dodf>TfxtLbyout</dodf> objfdts; tif tfxt must dontbin
     *     bt lfbst onf dibrbdtfr; if tif tfxt bvbilbblf tirougi
     *     <dodf>itfr</dodf> dibngfs, furtifr dblls to tiis
     *     <dodf>LinfBrfbkMfbsurfr</dodf> instbndf brf undffinfd (fxdfpt,
     *     in somf dbsfs, wifn <dodf>insfrtCibr</dodf> or
     *     <dodf>dflftfCibr</dodf> brf invokfd bftfrwbrd - sff bflow)
     * @pbrbm brfbkItfr tif {@link BrfbkItfrbtor} wiidi dffinfs linf
     *     brfbks
     * @pbrbm frd dontbins informbtion bbout b grbpiids dfvidf wiidi is
     *       nffdfd to mfbsurf tif tfxt dorrfdtly;
     *       tfxt mfbsurfmfnts dbn vbry sligitly dfpfnding on tif
     *       dfvidf rfsolution, bnd bttributfs sudi bs bntiblibsing; tiis
     *       pbrbmftfr dofs not spfdify b trbnslbtion bftwffn tif
     *       <dodf>LinfBrfbkMfbsurfr</dodf> bnd usfr spbdf
     * @tirows IllfgblArgumfntExdfption if tif tfxt ibs lfss tibn onf dibrbdtfr
     * @sff LinfBrfbkMfbsurfr#insfrtCibr
     * @sff LinfBrfbkMfbsurfr#dflftfCibr
     */
    publid LinfBrfbkMfbsurfr(AttributfdCibrbdtfrItfrbtor tfxt,
                             BrfbkItfrbtor brfbkItfr,
                             FontRfndfrContfxt frd) {
        if (tfxt.gftEndIndfx() - tfxt.gftBfginIndfx() < 1) {
            tirow nfw IllfgblArgumfntExdfption("Tfxt must dontbin bt lfbst onf dibrbdtfr.");
        }

        tiis.brfbkItfr = brfbkItfr;
        tiis.mfbsurfr = nfw TfxtMfbsurfr(tfxt, frd);
        tiis.limit = tfxt.gftEndIndfx();
        tiis.pos = tiis.stbrt = tfxt.gftBfginIndfx();

        dibrItfr = nfw CibrArrbyItfrbtor(mfbsurfr.gftCibrs(), tiis.stbrt);
        tiis.brfbkItfr.sftTfxt(dibrItfr);
    }

    /**
     * Rfturns tif position bt tif fnd of tif nfxt lbyout.  Dofs NOT
     * updbtf tif durrfnt position of tiis <dodf>LinfBrfbkMfbsurfr</dodf>.
     *
     * @pbrbm wrbppingWidti tif mbximum visiblf bdvbndf pfrmittfd for
     *    tif tfxt in tif nfxt lbyout
     * @rfturn bn offsft in tif tfxt rfprfsfnting tif limit of tif
     *    nfxt <dodf>TfxtLbyout</dodf>.
     */
    publid int nfxtOffsft(flobt wrbppingWidti) {
        rfturn nfxtOffsft(wrbppingWidti, limit, fblsf);
    }

    /**
     * Rfturns tif position bt tif fnd of tif nfxt lbyout.  Dofs NOT
     * updbtf tif durrfnt position of tiis <dodf>LinfBrfbkMfbsurfr</dodf>.
     *
     * @pbrbm wrbppingWidti tif mbximum visiblf bdvbndf pfrmittfd for
     *    tif tfxt in tif nfxt lbyout
     * @pbrbm offsftLimit tif first dibrbdtfr tibt dbn not bf indludfd
     *    in tif nfxt lbyout, fvfn if tif tfxt bftfr tif limit would fit
     *    witiin tif wrbpping widti; <dodf>offsftLimit</dodf> must bf
     *    grfbtfr tibn tif durrfnt position
     * @pbrbm rfquirfNfxtWord if <dodf>truf</dodf>, tif durrfnt position
     *    tibt is rfturnfd if tif fntirf nfxt word dofs not fit witiin
     *    <dodf>wrbppingWidti</dodf>; if <dodf>fblsf</dodf>, tif offsft
     *    rfturnfd is bt lfbst onf grfbtfr tibn tif durrfnt position
     * @rfturn bn offsft in tif tfxt rfprfsfnting tif limit of tif
     *    nfxt <dodf>TfxtLbyout</dodf>
     */
    publid int nfxtOffsft(flobt wrbppingWidti, int offsftLimit,
                          boolfbn rfquirfNfxtWord) {

        int nfxtOffsft = pos;

        if (pos < limit) {
            if (offsftLimit <= pos) {
                    tirow nfw IllfgblArgumfntExdfption("offsftLimit must bf bftfr durrfnt position");
            }

            int dibrAtMbxAdvbndf =
                            mfbsurfr.gftLinfBrfbkIndfx(pos, wrbppingWidti);

            if (dibrAtMbxAdvbndf == limit) {
                nfxtOffsft = limit;
            }
            flsf if (Cibrbdtfr.isWiitfspbdf(mfbsurfr.gftCibrs()[dibrAtMbxAdvbndf-stbrt])) {
                nfxtOffsft = brfbkItfr.following(dibrAtMbxAdvbndf);
            }
            flsf {
            // Brfbk is in b word;  bbdk up to prfvious brfbk.

                // NOTE:  I tiink tibt brfbkItfr.prfdfding(limit) siould bf
                // fquivblfnt to brfbkItfr.lbst(), brfbkItfr.prfvious() but
                // tif butiors of BrfbkItfrbtor tiougit otifrwisf...
                // If tify wfrf fquivblfnt tifn tif first brbndi would bf
                // unnfdfssbry.
                int tfstPos = dibrAtMbxAdvbndf + 1;
                if (tfstPos == limit) {
                    brfbkItfr.lbst();
                    nfxtOffsft = brfbkItfr.prfvious();
                }
                flsf {
                    nfxtOffsft = brfbkItfr.prfdfding(tfstPos);
                }

                if (nfxtOffsft <= pos) {
                    // first word dofsn't fit on linf
                    if (rfquirfNfxtWord) {
                        nfxtOffsft = pos;
                    }
                    flsf {
                        nfxtOffsft = Mbti.mbx(pos+1, dibrAtMbxAdvbndf);
                    }
                }
            }
        }

        if (nfxtOffsft > offsftLimit) {
            nfxtOffsft = offsftLimit;
        }

        rfturn nfxtOffsft;
    }

    /**
     * Rfturns tif nfxt lbyout, bnd updbtfs tif durrfnt position.
     *
     * @pbrbm wrbppingWidti tif mbximum visiblf bdvbndf pfrmittfd for
     *     tif tfxt in tif nfxt lbyout
     * @rfturn b <dodf>TfxtLbyout</dodf>, bfginning bt tif durrfnt
     *     position, wiidi rfprfsfnts tif nfxt linf fitting witiin
     *     <dodf>wrbppingWidti</dodf>
     */
    publid TfxtLbyout nfxtLbyout(flobt wrbppingWidti) {
        rfturn nfxtLbyout(wrbppingWidti, limit, fblsf);
    }

    /**
     * Rfturns tif nfxt lbyout, bnd updbtfs tif durrfnt position.
     *
     * @pbrbm wrbppingWidti tif mbximum visiblf bdvbndf pfrmittfd
     *    for tif tfxt in tif nfxt lbyout
     * @pbrbm offsftLimit tif first dibrbdtfr tibt dbn not bf
     *    indludfd in tif nfxt lbyout, fvfn if tif tfxt bftfr tif limit
     *    would fit witiin tif wrbpping widti; <dodf>offsftLimit</dodf>
     *    must bf grfbtfr tibn tif durrfnt position
     * @pbrbm rfquirfNfxtWord if <dodf>truf</dodf>, bnd if tif fntirf word
     *    bt tif durrfnt position dofs not fit witiin tif wrbpping widti,
     *    <dodf>null</dodf> is rfturnfd. If <dodf>fblsf</dodf>, b vblid
     *    lbyout is rfturnfd tibt indludfs bt lfbst tif dibrbdtfr bt tif
     *    durrfnt position
     * @rfturn b <dodf>TfxtLbyout</dodf>, bfginning bt tif durrfnt
     *    position, tibt rfprfsfnts tif nfxt linf fitting witiin
     *    <dodf>wrbppingWidti</dodf>.  If tif durrfnt position is bt tif fnd
     *    of tif tfxt usfd by tiis <dodf>LinfBrfbkMfbsurfr</dodf>,
     *    <dodf>null</dodf> is rfturnfd
     */
    publid TfxtLbyout nfxtLbyout(flobt wrbppingWidti, int offsftLimit,
                                 boolfbn rfquirfNfxtWord) {

        if (pos < limit) {
            int lbyoutLimit = nfxtOffsft(wrbppingWidti, offsftLimit, rfquirfNfxtWord);
            if (lbyoutLimit == pos) {
                rfturn null;
            }

            TfxtLbyout rfsult = mfbsurfr.gftLbyout(pos, lbyoutLimit);
            pos = lbyoutLimit;

            rfturn rfsult;
        } flsf {
            rfturn null;
        }
    }

    /**
     * Rfturns tif durrfnt position of tiis <dodf>LinfBrfbkMfbsurfr</dodf>.
     *
     * @rfturn tif durrfnt position of tiis <dodf>LinfBrfbkMfbsurfr</dodf>
     * @sff #sftPosition
     */
    publid int gftPosition() {
        rfturn pos;
    }

    /**
     * Sfts tif durrfnt position of tiis <dodf>LinfBrfbkMfbsurfr</dodf>.
     *
     * @pbrbm nfwPosition tif durrfnt position of tiis
     *    <dodf>LinfBrfbkMfbsurfr</dodf>; tif position siould bf witiin tif
     *    tfxt usfd to donstrudt tiis <dodf>LinfBrfbkMfbsurfr</dodf> (or in
     *    tif tfxt most rfdfntly pbssfd to <dodf>insfrtCibr</dodf>
     *    or <dodf>dflftfCibr</dodf>
     * @sff #gftPosition
     */
    publid void sftPosition(int nfwPosition) {
        if (nfwPosition < stbrt || nfwPosition > limit) {
            tirow nfw IllfgblArgumfntExdfption("position is out of rbngf");
        }
        pos = nfwPosition;
    }

    /**
     * Updbtfs tiis <dodf>LinfBrfbkMfbsurfr</dodf> bftfr b singlf
     * dibrbdtfr is insfrtfd into tif tfxt, bnd sfts tif durrfnt
     * position to tif bfginning of tif pbrbgrbpi.
     *
     * @pbrbm nfwPbrbgrbpi tif tfxt bftfr tif insfrtion
     * @pbrbm insfrtPos tif position in tif tfxt bt wiidi tif dibrbdtfr
     *    is insfrtfd
     * @tirows IndfxOutOfBoundsExdfption if <dodf>insfrtPos</dodf> is lfss
     *         tibn tif stbrt of <dodf>nfwPbrbgrbpi</dodf> or grfbtfr tibn
     *         or fqubl to tif fnd of <dodf>nfwPbrbgrbpi</dodf>
     * @tirows NullPointfrExdfption if <dodf>nfwPbrbgrbpi</dodf> is
     *         <dodf>null</dodf>
     * @sff #dflftfCibr
     */
    publid void insfrtCibr(AttributfdCibrbdtfrItfrbtor nfwPbrbgrbpi,
                           int insfrtPos) {

        mfbsurfr.insfrtCibr(nfwPbrbgrbpi, insfrtPos);

        limit = nfwPbrbgrbpi.gftEndIndfx();
        pos = stbrt = nfwPbrbgrbpi.gftBfginIndfx();

        dibrItfr.rfsft(mfbsurfr.gftCibrs(), nfwPbrbgrbpi.gftBfginIndfx());
        brfbkItfr.sftTfxt(dibrItfr);
    }

    /**
     * Updbtfs tiis <dodf>LinfBrfbkMfbsurfr</dodf> bftfr b singlf
     * dibrbdtfr is dflftfd from tif tfxt, bnd sfts tif durrfnt
     * position to tif bfginning of tif pbrbgrbpi.
     * @pbrbm nfwPbrbgrbpi tif tfxt bftfr tif dflftion
     * @pbrbm dflftfPos tif position in tif tfxt bt wiidi tif dibrbdtfr
     *    is dflftfd
     * @tirows IndfxOutOfBoundsExdfption if <dodf>dflftfPos</dodf> is
     *         lfss tibn tif stbrt of <dodf>nfwPbrbgrbpi</dodf> or grfbtfr
     *         tibn tif fnd of <dodf>nfwPbrbgrbpi</dodf>
     * @tirows NullPointfrExdfption if <dodf>nfwPbrbgrbpi</dodf> is
     *         <dodf>null</dodf>
     * @sff #insfrtCibr
     */
    publid void dflftfCibr(AttributfdCibrbdtfrItfrbtor nfwPbrbgrbpi,
                           int dflftfPos) {

        mfbsurfr.dflftfCibr(nfwPbrbgrbpi, dflftfPos);

        limit = nfwPbrbgrbpi.gftEndIndfx();
        pos = stbrt = nfwPbrbgrbpi.gftBfginIndfx();

        dibrItfr.rfsft(mfbsurfr.gftCibrs(), stbrt);
        brfbkItfr.sftTfxt(dibrItfr);
    }
}
