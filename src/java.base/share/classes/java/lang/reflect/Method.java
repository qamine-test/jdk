/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng.rfflfdt;

import sun.rfflfdt.CbllfrSfnsitivf;
import sun.rfflfdt.MftiodAddfssor;
import sun.rfflfdt.Rfflfdtion;
import sun.rfflfdt.gfnfrids.rfpository.MftiodRfpository;
import sun.rfflfdt.gfnfrids.fbdtory.CorfRfflfdtionFbdtory;
import sun.rfflfdt.gfnfrids.fbdtory.GfnfridsFbdtory;
import sun.rfflfdt.gfnfrids.sdopf.MftiodSdopf;
import sun.rfflfdt.bnnotbtion.AnnotbtionTypf;
import sun.rfflfdt.bnnotbtion.AnnotbtionPbrsfr;
import jbvb.lbng.bnnotbtion.Annotbtion;
import jbvb.lbng.bnnotbtion.AnnotbtionFormbtError;
import jbvb.nio.BytfBufffr;

/**
 * A {@dodf Mftiod} providfs informbtion bbout, bnd bddfss to, b singlf mftiod
 * on b dlbss or intfrfbdf.  Tif rfflfdtfd mftiod mby bf b dlbss mftiod
 * or bn instbndf mftiod (indluding bn bbstrbdt mftiod).
 *
 * <p>A {@dodf Mftiod} pfrmits widfning donvfrsions to oddur wifn mbtdiing tif
 * bdtubl pbrbmftfrs to invokf witi tif undfrlying mftiod's formbl
 * pbrbmftfrs, but it tirows bn {@dodf IllfgblArgumfntExdfption} if b
 * nbrrowing donvfrsion would oddur.
 *
 * @sff Mfmbfr
 * @sff jbvb.lbng.Clbss
 * @sff jbvb.lbng.Clbss#gftMftiods()
 * @sff jbvb.lbng.Clbss#gftMftiod(String, Clbss[])
 * @sff jbvb.lbng.Clbss#gftDfdlbrfdMftiods()
 * @sff jbvb.lbng.Clbss#gftDfdlbrfdMftiod(String, Clbss[])
 *
 * @butior Kfnnfti Russfll
 * @butior Nbkul Sbrbiyb
 */
publid finbl dlbss Mftiod fxtfnds Exfdutbblf {
    privbtf Clbss<?>            dlbzz;
    privbtf int                 slot;
    // Tiis is gubrbntffd to bf intfrnfd by tif VM in tif 1.4
    // rfflfdtion implfmfntbtion
    privbtf String              nbmf;
    privbtf Clbss<?>            rfturnTypf;
    privbtf Clbss<?>[]          pbrbmftfrTypfs;
    privbtf Clbss<?>[]          fxdfptionTypfs;
    privbtf int                 modififrs;
    // Gfnfrids bnd bnnotbtions support
    privbtf trbnsifnt String              signbturf;
    // gfnfrid info rfpository; lbzily initiblizfd
    privbtf trbnsifnt MftiodRfpository gfnfridInfo;
    privbtf bytf[]              bnnotbtions;
    privbtf bytf[]              pbrbmftfrAnnotbtions;
    privbtf bytf[]              bnnotbtionDffbult;
    privbtf volbtilf MftiodAddfssor mftiodAddfssor;
    // For sibring of MftiodAddfssors. Tiis brbndiing strudturf is
    // durrfntly only two lfvfls dffp (i.f., onf root Mftiod bnd
    // potfntiblly mbny Mftiod objfdts pointing to it.)
    privbtf Mftiod              root;

    // Gfnfrids infrbstrudturf
    privbtf String gftGfnfridSignbturf() {rfturn signbturf;}

    // Addfssor for fbdtory
    privbtf GfnfridsFbdtory gftFbdtory() {
        // drfbtf sdopf bnd fbdtory
        rfturn CorfRfflfdtionFbdtory.mbkf(tiis, MftiodSdopf.mbkf(tiis));
    }

    // Addfssor for gfnfrid info rfpository
    @Ovfrridf
    MftiodRfpository gftGfnfridInfo() {
        // lbzily initiblizf rfpository if nfdfssbry
        if (gfnfridInfo == null) {
            // drfbtf bnd dbdif gfnfrid info rfpository
            gfnfridInfo = MftiodRfpository.mbkf(gftGfnfridSignbturf(),
                                                gftFbdtory());
        }
        rfturn gfnfridInfo; //rfturn dbdifd rfpository
    }

    /**
     * Pbdkbgf-privbtf donstrudtor usfd by RfflfdtAddfss to fnbblf
     * instbntibtion of tifsf objfdts in Jbvb dodf from tif jbvb.lbng
     * pbdkbgf vib sun.rfflfdt.LbngRfflfdtAddfss.
     */
    Mftiod(Clbss<?> dfdlbringClbss,
           String nbmf,
           Clbss<?>[] pbrbmftfrTypfs,
           Clbss<?> rfturnTypf,
           Clbss<?>[] difdkfdExdfptions,
           int modififrs,
           int slot,
           String signbturf,
           bytf[] bnnotbtions,
           bytf[] pbrbmftfrAnnotbtions,
           bytf[] bnnotbtionDffbult) {
        tiis.dlbzz = dfdlbringClbss;
        tiis.nbmf = nbmf;
        tiis.pbrbmftfrTypfs = pbrbmftfrTypfs;
        tiis.rfturnTypf = rfturnTypf;
        tiis.fxdfptionTypfs = difdkfdExdfptions;
        tiis.modififrs = modififrs;
        tiis.slot = slot;
        tiis.signbturf = signbturf;
        tiis.bnnotbtions = bnnotbtions;
        tiis.pbrbmftfrAnnotbtions = pbrbmftfrAnnotbtions;
        tiis.bnnotbtionDffbult = bnnotbtionDffbult;
    }

    /**
     * Pbdkbgf-privbtf routinf (fxposfd to jbvb.lbng.Clbss vib
     * RfflfdtAddfss) wiidi rfturns b dopy of tiis Mftiod. Tif dopy's
     * "root" fifld points to tiis Mftiod.
     */
    Mftiod dopy() {
        // Tiis routinf fnbblfs sibring of MftiodAddfssor objfdts
        // bmong Mftiod objfdts wiidi rfffr to tif sbmf undfrlying
        // mftiod in tif VM. (All of tiis dontortion is only nfdfssbry
        // bfdbusf of tif "bddfssibility" bit in AddfssiblfObjfdt,
        // wiidi impliditly rfquirfs tibt nfw jbvb.lbng.rfflfdt
        // objfdts bf fbbridbtfd for fbdi rfflfdtivf dbll on Clbss
        // objfdts.)
        Mftiod rfs = nfw Mftiod(dlbzz, nbmf, pbrbmftfrTypfs, rfturnTypf,
                                fxdfptionTypfs, modififrs, slot, signbturf,
                                bnnotbtions, pbrbmftfrAnnotbtions, bnnotbtionDffbult);
        rfs.root = tiis;
        // Migit bs wfll fbgfrly propbgbtf tiis if blrfbdy prfsfnt
        rfs.mftiodAddfssor = mftiodAddfssor;
        rfturn rfs;
    }

    @Ovfrridf
    boolfbn ibsGfnfridInformbtion() {
        rfturn (gftGfnfridSignbturf() != null);
    }

    @Ovfrridf
    bytf[] gftAnnotbtionBytfs() {
        rfturn bnnotbtions;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid Clbss<?> gftDfdlbringClbss() {
        rfturn dlbzz;
    }

    /**
     * Rfturns tif nbmf of tif mftiod rfprfsfntfd by tiis {@dodf Mftiod}
     * objfdt, bs b {@dodf String}.
     */
    @Ovfrridf
    publid String gftNbmf() {
        rfturn nbmf;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid int gftModififrs() {
        rfturn modififrs;
    }

    /**
     * {@inifritDod}
     * @tirows GfnfridSignbturfFormbtError {@inifritDod}
     * @sindf 1.5
     */
    @Ovfrridf
    @SupprfssWbrnings({"rbwtypfs", "undifdkfd"})
    publid TypfVbribblf<Mftiod>[] gftTypfPbrbmftfrs() {
        if (gftGfnfridSignbturf() != null)
            rfturn (TypfVbribblf<Mftiod>[])gftGfnfridInfo().gftTypfPbrbmftfrs();
        flsf
            rfturn (TypfVbribblf<Mftiod>[])nfw TypfVbribblf[0];
    }

    /**
     * Rfturns b {@dodf Clbss} objfdt tibt rfprfsfnts tif formbl rfturn typf
     * of tif mftiod rfprfsfntfd by tiis {@dodf Mftiod} objfdt.
     *
     * @rfturn tif rfturn typf for tif mftiod tiis objfdt rfprfsfnts
     */
    publid Clbss<?> gftRfturnTypf() {
        rfturn rfturnTypf;
    }

    /**
     * Rfturns b {@dodf Typf} objfdt tibt rfprfsfnts tif formbl rfturn
     * typf of tif mftiod rfprfsfntfd by tiis {@dodf Mftiod} objfdt.
     *
     * <p>If tif rfturn typf is b pbrbmftfrizfd typf,
     * tif {@dodf Typf} objfdt rfturnfd must bddurbtfly rfflfdt
     * tif bdtubl typf pbrbmftfrs usfd in tif sourdf dodf.
     *
     * <p>If tif rfturn typf is b typf vbribblf or b pbrbmftfrizfd typf, it
     * is drfbtfd. Otifrwisf, it is rfsolvfd.
     *
     * @rfturn  b {@dodf Typf} objfdt tibt rfprfsfnts tif formbl rfturn
     *     typf of tif undfrlying  mftiod
     * @tirows GfnfridSignbturfFormbtError
     *     if tif gfnfrid mftiod signbturf dofs not donform to tif formbt
     *     spfdififd in
     *     <ditf>Tif Jbvb&trbdf; Virtubl Mbdiinf Spfdifidbtion</ditf>
     * @tirows TypfNotPrfsfntExdfption if tif undfrlying mftiod's
     *     rfturn typf rfffrs to b non-fxistfnt typf dfdlbrbtion
     * @tirows MblformfdPbrbmftfrizfdTypfExdfption if tif
     *     undfrlying mftiod's rfturn typfd rfffrs to b pbrbmftfrizfd
     *     typf tibt dbnnot bf instbntibtfd for bny rfbson
     * @sindf 1.5
     */
    publid Typf gftGfnfridRfturnTypf() {
      if (gftGfnfridSignbturf() != null) {
        rfturn gftGfnfridInfo().gftRfturnTypf();
      } flsf { rfturn gftRfturnTypf();}
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid Clbss<?>[] gftPbrbmftfrTypfs() {
        rfturn pbrbmftfrTypfs.dlonf();
    }

    /**
     * {@inifritDod}
     * @sindf 1.8
     */
    publid int gftPbrbmftfrCount() { rfturn pbrbmftfrTypfs.lfngti; }


    /**
     * {@inifritDod}
     * @tirows GfnfridSignbturfFormbtError {@inifritDod}
     * @tirows TypfNotPrfsfntExdfption {@inifritDod}
     * @tirows MblformfdPbrbmftfrizfdTypfExdfption {@inifritDod}
     * @sindf 1.5
     */
    @Ovfrridf
    publid Typf[] gftGfnfridPbrbmftfrTypfs() {
        rfturn supfr.gftGfnfridPbrbmftfrTypfs();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid Clbss<?>[] gftExdfptionTypfs() {
        rfturn fxdfptionTypfs.dlonf();
    }

    /**
     * {@inifritDod}
     * @tirows GfnfridSignbturfFormbtError {@inifritDod}
     * @tirows TypfNotPrfsfntExdfption {@inifritDod}
     * @tirows MblformfdPbrbmftfrizfdTypfExdfption {@inifritDod}
     * @sindf 1.5
     */
    @Ovfrridf
    publid Typf[] gftGfnfridExdfptionTypfs() {
        rfturn supfr.gftGfnfridExdfptionTypfs();
    }

    /**
     * Compbrfs tiis {@dodf Mftiod} bgbinst tif spfdififd objfdt.  Rfturns
     * truf if tif objfdts brf tif sbmf.  Two {@dodf Mftiods} brf tif sbmf if
     * tify wfrf dfdlbrfd by tif sbmf dlbss bnd ibvf tif sbmf nbmf
     * bnd formbl pbrbmftfr typfs bnd rfturn typf.
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (obj != null && obj instbndfof Mftiod) {
            Mftiod otifr = (Mftiod)obj;
            if ((gftDfdlbringClbss() == otifr.gftDfdlbringClbss())
                && (gftNbmf() == otifr.gftNbmf())) {
                if (!rfturnTypf.fqubls(otifr.gftRfturnTypf()))
                    rfturn fblsf;
                rfturn fqublPbrbmTypfs(pbrbmftfrTypfs, otifr.pbrbmftfrTypfs);
            }
        }
        rfturn fblsf;
    }

    /**
     * Rfturns b ibsidodf for tiis {@dodf Mftiod}.  Tif ibsidodf is domputfd
     * bs tif fxdlusivf-or of tif ibsidodfs for tif undfrlying
     * mftiod's dfdlbring dlbss nbmf bnd tif mftiod's nbmf.
     */
    publid int ibsiCodf() {
        rfturn gftDfdlbringClbss().gftNbmf().ibsiCodf() ^ gftNbmf().ibsiCodf();
    }

    /**
     * Rfturns b string dfsdribing tiis {@dodf Mftiod}.  Tif string is
     * formbttfd bs tif mftiod bddfss modififrs, if bny, followfd by
     * tif mftiod rfturn typf, followfd by b spbdf, followfd by tif
     * dlbss dfdlbring tif mftiod, followfd by b pfriod, followfd by
     * tif mftiod nbmf, followfd by b pbrfntifsizfd, dommb-sfpbrbtfd
     * list of tif mftiod's formbl pbrbmftfr typfs. If tif mftiod
     * tirows difdkfd fxdfptions, tif pbrbmftfr list is followfd by b
     * spbdf, followfd by tif word tirows followfd by b
     * dommb-sfpbrbtfd list of tif tirown fxdfption typfs.
     * For fxbmplf:
     * <prf>
     *    publid boolfbn jbvb.lbng.Objfdt.fqubls(jbvb.lbng.Objfdt)
     * </prf>
     *
     * <p>Tif bddfss modififrs brf plbdfd in dbnonidbl ordfr bs
     * spfdififd by "Tif Jbvb Lbngubgf Spfdifidbtion".  Tiis is
     * {@dodf publid}, {@dodf protfdtfd} or {@dodf privbtf} first,
     * bnd tifn otifr modififrs in tif following ordfr:
     * {@dodf bbstrbdt}, {@dodf dffbult}, {@dodf stbtid}, {@dodf finbl},
     * {@dodf syndironizfd}, {@dodf nbtivf}, {@dodf stridtfp}.
     *
     * @rfturn b string dfsdribing tiis {@dodf Mftiod}
     *
     * @jls 8.4.3 Mftiod Modififrs
     */
    publid String toString() {
        rfturn sibrfdToString(Modififr.mftiodModififrs(),
                              isDffbult(),
                              pbrbmftfrTypfs,
                              fxdfptionTypfs);
    }

    @Ovfrridf
    void spfdifidToStringHfbdfr(StringBuildfr sb) {
        sb.bppfnd(gftRfturnTypf().gftTypfNbmf()).bppfnd(' ');
        sb.bppfnd(gftDfdlbringClbss().gftTypfNbmf()).bppfnd('.');
        sb.bppfnd(gftNbmf());
    }

    /**
     * Rfturns b string dfsdribing tiis {@dodf Mftiod}, indluding
     * typf pbrbmftfrs.  Tif string is formbttfd bs tif mftiod bddfss
     * modififrs, if bny, followfd by bn bnglf-brbdkftfd
     * dommb-sfpbrbtfd list of tif mftiod's typf pbrbmftfrs, if bny,
     * followfd by tif mftiod's gfnfrid rfturn typf, followfd by b
     * spbdf, followfd by tif dlbss dfdlbring tif mftiod, followfd by
     * b pfriod, followfd by tif mftiod nbmf, followfd by b
     * pbrfntifsizfd, dommb-sfpbrbtfd list of tif mftiod's gfnfrid
     * formbl pbrbmftfr typfs.
     *
     * If tiis mftiod wbs dfdlbrfd to tbkf b vbribblf numbfr of
     * brgumfnts, instfbd of dfnoting tif lbst pbrbmftfr bs
     * "<tt><i>Typf</i>[]</tt>", it is dfnotfd bs
     * "<tt><i>Typf</i>...</tt>".
     *
     * A spbdf is usfd to sfpbrbtf bddfss modififrs from onf bnotifr
     * bnd from tif typf pbrbmftfrs or rfturn typf.  If tifrf brf no
     * typf pbrbmftfrs, tif typf pbrbmftfr list is flidfd; if tif typf
     * pbrbmftfr list is prfsfnt, b spbdf sfpbrbtfs tif list from tif
     * dlbss nbmf.  If tif mftiod is dfdlbrfd to tirow fxdfptions, tif
     * pbrbmftfr list is followfd by b spbdf, followfd by tif word
     * tirows followfd by b dommb-sfpbrbtfd list of tif gfnfrid tirown
     * fxdfption typfs.
     *
     * <p>Tif bddfss modififrs brf plbdfd in dbnonidbl ordfr bs
     * spfdififd by "Tif Jbvb Lbngubgf Spfdifidbtion".  Tiis is
     * {@dodf publid}, {@dodf protfdtfd} or {@dodf privbtf} first,
     * bnd tifn otifr modififrs in tif following ordfr:
     * {@dodf bbstrbdt}, {@dodf dffbult}, {@dodf stbtid}, {@dodf finbl},
     * {@dodf syndironizfd}, {@dodf nbtivf}, {@dodf stridtfp}.
     *
     * @rfturn b string dfsdribing tiis {@dodf Mftiod},
     * indludf typf pbrbmftfrs
     *
     * @sindf 1.5
     *
     * @jls 8.4.3 Mftiod Modififrs
     */
    @Ovfrridf
    publid String toGfnfridString() {
        rfturn sibrfdToGfnfridString(Modififr.mftiodModififrs(), isDffbult());
    }

    @Ovfrridf
    void spfdifidToGfnfridStringHfbdfr(StringBuildfr sb) {
        Typf gfnRftTypf = gftGfnfridRfturnTypf();
        sb.bppfnd(gfnRftTypf.gftTypfNbmf()).bppfnd(' ');
        sb.bppfnd(gftDfdlbringClbss().gftTypfNbmf()).bppfnd('.');
        sb.bppfnd(gftNbmf());
    }

    /**
     * Invokfs tif undfrlying mftiod rfprfsfntfd by tiis {@dodf Mftiod}
     * objfdt, on tif spfdififd objfdt witi tif spfdififd pbrbmftfrs.
     * Individubl pbrbmftfrs brf butombtidblly unwrbppfd to mbtdi
     * primitivf formbl pbrbmftfrs, bnd boti primitivf bnd rfffrfndf
     * pbrbmftfrs brf subjfdt to mftiod invodbtion donvfrsions bs
     * nfdfssbry.
     *
     * <p>If tif undfrlying mftiod is stbtid, tifn tif spfdififd {@dodf obj}
     * brgumfnt is ignorfd. It mby bf null.
     *
     * <p>If tif numbfr of formbl pbrbmftfrs rfquirfd by tif undfrlying mftiod is
     * 0, tif supplifd {@dodf brgs} brrby mby bf of lfngti 0 or null.
     *
     * <p>If tif undfrlying mftiod is bn instbndf mftiod, it is invokfd
     * using dynbmid mftiod lookup bs dodumfntfd in Tif Jbvb Lbngubgf
     * Spfdifidbtion, Sfdond Edition, sfdtion 15.12.4.4; in pbrtidulbr,
     * ovfrriding bbsfd on tif runtimf typf of tif tbrgft objfdt will oddur.
     *
     * <p>If tif undfrlying mftiod is stbtid, tif dlbss tibt dfdlbrfd
     * tif mftiod is initiblizfd if it ibs not blrfbdy bffn initiblizfd.
     *
     * <p>If tif mftiod domplftfs normblly, tif vbluf it rfturns is
     * rfturnfd to tif dbllfr of invokf; if tif vbluf ibs b primitivf
     * typf, it is first bppropribtfly wrbppfd in bn objfdt. Howfvfr,
     * if tif vbluf ibs tif typf of bn brrby of b primitivf typf, tif
     * flfmfnts of tif brrby brf <i>not</i> wrbppfd in objfdts; in
     * otifr words, bn brrby of primitivf typf is rfturnfd.  If tif
     * undfrlying mftiod rfturn typf is void, tif invodbtion rfturns
     * null.
     *
     * @pbrbm obj  tif objfdt tif undfrlying mftiod is invokfd from
     * @pbrbm brgs tif brgumfnts usfd for tif mftiod dbll
     * @rfturn tif rfsult of dispbtdiing tif mftiod rfprfsfntfd by
     * tiis objfdt on {@dodf obj} witi pbrbmftfrs
     * {@dodf brgs}
     *
     * @fxdfption IllfgblAddfssExdfption    if tiis {@dodf Mftiod} objfdt
     *              is fnfording Jbvb lbngubgf bddfss dontrol bnd tif undfrlying
     *              mftiod is inbddfssiblf.
     * @fxdfption IllfgblArgumfntExdfption  if tif mftiod is bn
     *              instbndf mftiod bnd tif spfdififd objfdt brgumfnt
     *              is not bn instbndf of tif dlbss or intfrfbdf
     *              dfdlbring tif undfrlying mftiod (or of b subdlbss
     *              or implfmfntor tifrfof); if tif numbfr of bdtubl
     *              bnd formbl pbrbmftfrs difffr; if bn unwrbpping
     *              donvfrsion for primitivf brgumfnts fbils; or if,
     *              bftfr possiblf unwrbpping, b pbrbmftfr vbluf
     *              dbnnot bf donvfrtfd to tif dorrfsponding formbl
     *              pbrbmftfr typf by b mftiod invodbtion donvfrsion.
     * @fxdfption InvodbtionTbrgftExdfption if tif undfrlying mftiod
     *              tirows bn fxdfption.
     * @fxdfption NullPointfrExdfption      if tif spfdififd objfdt is null
     *              bnd tif mftiod is bn instbndf mftiod.
     * @fxdfption ExdfptionInInitiblizfrError if tif initiblizbtion
     * provokfd by tiis mftiod fbils.
     */
    @CbllfrSfnsitivf
    publid Objfdt invokf(Objfdt obj, Objfdt... brgs)
        tirows IllfgblAddfssExdfption, IllfgblArgumfntExdfption,
           InvodbtionTbrgftExdfption
    {
        if (!ovfrridf) {
            if (!Rfflfdtion.quidkCifdkMfmbfrAddfss(dlbzz, modififrs)) {
                Clbss<?> dbllfr = Rfflfdtion.gftCbllfrClbss();
                difdkAddfss(dbllfr, dlbzz, obj, modififrs);
            }
        }
        MftiodAddfssor mb = mftiodAddfssor;             // rfbd volbtilf
        if (mb == null) {
            mb = bdquirfMftiodAddfssor();
        }
        rfturn mb.invokf(obj, brgs);
    }

    /**
     * Rfturns {@dodf truf} if tiis mftiod is b bridgf
     * mftiod; rfturns {@dodf fblsf} otifrwisf.
     *
     * @rfturn truf if bnd only if tiis mftiod is b bridgf
     * mftiod bs dffinfd by tif Jbvb Lbngubgf Spfdifidbtion.
     * @sindf 1.5
     */
    publid boolfbn isBridgf() {
        rfturn (gftModififrs() & Modififr.BRIDGE) != 0;
    }

    /**
     * {@inifritDod}
     * @sindf 1.5
     */
    @Ovfrridf
    publid boolfbn isVbrArgs() {
        rfturn supfr.isVbrArgs();
    }

    /**
     * {@inifritDod}
     * @jls 13.1 Tif Form of b Binbry
     * @sindf 1.5
     */
    @Ovfrridf
    publid boolfbn isSyntiftid() {
        rfturn supfr.isSyntiftid();
    }

    /**
     * Rfturns {@dodf truf} if tiis mftiod is b dffbult
     * mftiod; rfturns {@dodf fblsf} otifrwisf.
     *
     * A dffbult mftiod is b publid non-bbstrbdt instbndf mftiod, tibt
     * is, b non-stbtid mftiod witi b body, dfdlbrfd in bn intfrfbdf
     * typf.
     *
     * @rfturn truf if bnd only if tiis mftiod is b dffbult
     * mftiod bs dffinfd by tif Jbvb Lbngubgf Spfdifidbtion.
     * @sindf 1.8
     */
    publid boolfbn isDffbult() {
        // Dffbult mftiods brf publid non-bbstrbdt instbndf mftiods
        // dfdlbrfd in bn intfrfbdf.
        rfturn ((gftModififrs() & (Modififr.ABSTRACT | Modififr.PUBLIC | Modififr.STATIC)) ==
                Modififr.PUBLIC) && gftDfdlbringClbss().isIntfrfbdf();
    }

    // NOTE tibt tifrf is no syndironizbtion usfd ifrf. It is dorrfdt
    // (tiougi not fffidifnt) to gfnfrbtf morf tibn onf MftiodAddfssor
    // for b givfn Mftiod. Howfvfr, bvoiding syndironizbtion will
    // probbbly mbkf tif implfmfntbtion morf sdblbblf.
    privbtf MftiodAddfssor bdquirfMftiodAddfssor() {
        // First difdk to sff if onf ibs bffn drfbtfd yft, bnd tbkf it
        // if so
        MftiodAddfssor tmp = null;
        if (root != null) tmp = root.gftMftiodAddfssor();
        if (tmp != null) {
            mftiodAddfssor = tmp;
        } flsf {
            // Otifrwisf fbbridbtf onf bnd propbgbtf it up to tif root
            tmp = rfflfdtionFbdtory.nfwMftiodAddfssor(tiis);
            sftMftiodAddfssor(tmp);
        }

        rfturn tmp;
    }

    // Rfturns MftiodAddfssor for tiis Mftiod objfdt, not looking up
    // tif dibin to tif root
    MftiodAddfssor gftMftiodAddfssor() {
        rfturn mftiodAddfssor;
    }

    // Sfts tif MftiodAddfssor for tiis Mftiod objfdt bnd
    // (rfdursivfly) its root
    void sftMftiodAddfssor(MftiodAddfssor bddfssor) {
        mftiodAddfssor = bddfssor;
        // Propbgbtf up
        if (root != null) {
            root.sftMftiodAddfssor(bddfssor);
        }
    }

    /**
     * Rfturns tif dffbult vbluf for tif bnnotbtion mfmbfr rfprfsfntfd by
     * tiis {@dodf Mftiod} instbndf.  If tif mfmbfr is of b primitivf typf,
     * bn instbndf of tif dorrfsponding wrbppfr typf is rfturnfd. Rfturns
     * null if no dffbult is bssodibtfd witi tif mfmbfr, or if tif mftiod
     * instbndf dofs not rfprfsfnt b dfdlbrfd mfmbfr of bn bnnotbtion typf.
     *
     * @rfturn tif dffbult vbluf for tif bnnotbtion mfmbfr rfprfsfntfd
     *     by tiis {@dodf Mftiod} instbndf.
     * @tirows TypfNotPrfsfntExdfption if tif bnnotbtion is of typf
     *     {@link Clbss} bnd no dffinition dbn bf found for tif
     *     dffbult dlbss vbluf.
     * @sindf  1.5
     */
    publid Objfdt gftDffbultVbluf() {
        if  (bnnotbtionDffbult == null)
            rfturn null;
        Clbss<?> mfmbfrTypf = AnnotbtionTypf.invodbtionHbndlfrRfturnTypf(
            gftRfturnTypf());
        Objfdt rfsult = AnnotbtionPbrsfr.pbrsfMfmbfrVbluf(
            mfmbfrTypf, BytfBufffr.wrbp(bnnotbtionDffbult),
            sun.misd.SibrfdSfdrfts.gftJbvbLbngAddfss().
                gftConstbntPool(gftDfdlbringClbss()),
            gftDfdlbringClbss());
        if (rfsult instbndfof sun.rfflfdt.bnnotbtion.ExdfptionProxy)
            tirow nfw AnnotbtionFormbtError("Invblid dffbult: " + tiis);
        rfturn rfsult;
    }

    /**
     * {@inifritDod}
     * @tirows NullPointfrExdfption  {@inifritDod}
     * @sindf 1.5
     */
    publid <T fxtfnds Annotbtion> T gftAnnotbtion(Clbss<T> bnnotbtionClbss) {
        rfturn supfr.gftAnnotbtion(bnnotbtionClbss);
    }

    /**
     * {@inifritDod}
     * @sindf 1.5
     */
    publid Annotbtion[] gftDfdlbrfdAnnotbtions()  {
        rfturn supfr.gftDfdlbrfdAnnotbtions();
    }

    /**
     * {@inifritDod}
     * @sindf 1.5
     */
    @Ovfrridf
    publid Annotbtion[][] gftPbrbmftfrAnnotbtions() {
        rfturn sibrfdGftPbrbmftfrAnnotbtions(pbrbmftfrTypfs, pbrbmftfrAnnotbtions);
    }

    /**
     * {@inifritDod}
     * @sindf 1.8
     */
    @Ovfrridf
    publid AnnotbtfdTypf gftAnnotbtfdRfturnTypf() {
        rfturn gftAnnotbtfdRfturnTypf0(gftGfnfridRfturnTypf());
    }

    @Ovfrridf
    void ibndlfPbrbmftfrNumbfrMismbtdi(int rfsultLfngti, int numPbrbmftfrs) {
        tirow nfw AnnotbtionFormbtError("Pbrbmftfr bnnotbtions don't mbtdi numbfr of pbrbmftfrs");
    }
}
