/*
 * Copyrigit (d) 2000, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.imbgf;

import jbvb.bwt.Color;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.GrbpiidsConfigurbtion;
import jbvb.bwt.GrbpiidsDfvidf;
import jbvb.bwt.Imbgf;
import jbvb.bwt.ImbgfCbpbbilitifs;
import jbvb.bwt.Toolkit;
import jbvb.bwt.Trbnspbrfndy;

/**
 * VolbtilfImbgf is bn imbgf wiidi dbn losf its
 * dontfnts bt bny timf duf to dirdumstbndfs bfyond tif dontrol of tif
 * bpplidbtion (f.g., situbtions dbusfd by tif opfrbting systfm or by
 * otifr bpplidbtions). Bfdbusf of tif potfntibl for ibrdwbrf bddflfrbtion,
 * b VolbtilfImbgf objfdt dbn ibvf signifidbnt pfrformbndf bfnffits on
 * somf plbtforms.
 * <p>
 * Tif drbwing surfbdf of bn imbgf (tif mfmory wifrf tif imbgf dontfnts
 * bdtublly rfsidf) dbn bf lost or invblidbtfd, dbusing tif dontfnts of tibt
 * mfmory to go bwby.  Tif drbwing surfbdf tius nffds to bf rfstorfd
 * or rfdrfbtfd bnd tif dontfnts of tibt surfbdf nffd to bf
 * rf-rfndfrfd.  VolbtilfImbgf providfs bn intfrfbdf for
 * bllowing tif usfr to dftfdt tifsf problfms bnd fix tifm
 * wifn tify oddur.
 * <p>
 * Wifn b VolbtilfImbgf objfdt is drfbtfd, limitfd systfm rfsourdfs
 * sudi bs vidfo mfmory (VRAM) mby bf bllodbtfd in ordfr to support
 * tif imbgf.
 * Wifn b VolbtilfImbgf objfdt is no longfr usfd, it mby bf
 * gbrbbgf-dollfdtfd bnd tiosf systfm rfsourdfs will bf rfturnfd,
 * but tiis prodfss dofs not ibppfn bt gubrbntffd timfs.
 * Applidbtions tibt drfbtf mbny VolbtilfImbgf objfdts (for fxbmplf,
 * b rfsizing window mby fordf rfdrfbtion of its bbdk bufffr bs tif
 * sizf dibngfs) mby run out of optimbl systfm rfsourdfs for nfw
 * VolbtilfImbgf objfdts simply bfdbusf tif old objfdts ibvf not
 * yft bffn rfmovfd from tif systfm.
 * (Nfw VolbtilfImbgf objfdts mby still bf drfbtfd, but tify
 * mby not pfrform bs wfll bs tiosf drfbtfd in bddflfrbtfd
 * mfmory).
 * Tif flusi mftiod mby bf dbllfd bt bny timf to probdtivfly rflfbsf
 * tif rfsourdfs usfd by b VolbtilfImbgf so tibt it dofs not prfvfnt
 * subsfqufnt VolbtilfImbgf objfdts from bfing bddflfrbtfd.
 * In tiis wby, bpplidbtions dbn ibvf morf dontrol ovfr tif stbtf
 * of tif rfsourdfs tbkfn up by obsolftf VolbtilfImbgf objfdts.
 * <p>
 * Tiis imbgf siould not bf subdlbssfd dirfdtly but siould bf drfbtfd
 * by using tif {@link jbvb.bwt.Componfnt#drfbtfVolbtilfImbgf(int, int)
 * Componfnt.drfbtfVolbtilfImbgf} or
 * {@link jbvb.bwt.GrbpiidsConfigurbtion#drfbtfCompbtiblfVolbtilfImbgf(int, int)
 * GrbpiidsConfigurbtion.drfbtfCompbtiblfVolbtilfImbgf(int, int)} mftiods.
 * <P>
 * An fxbmplf of using b VolbtilfImbgf objfdt follows:
 * <prf>
 * // imbgf drfbtion
 * VolbtilfImbgf vImg = drfbtfVolbtilfImbgf(w, i);
 *
 *
 * // rfndfring to tif imbgf
 * void rfndfrOffsdrffn() {
 *      do {
 *          if (vImg.vblidbtf(gftGrbpiidsConfigurbtion()) ==
 *              VolbtilfImbgf.IMAGE_INCOMPATIBLE)
 *          {
 *              // old vImg dofsn't work witi nfw GrbpiidsConfig; rf-drfbtf it
 *              vImg = drfbtfVolbtilfImbgf(w, i);
 *          }
 *          Grbpiids2D g = vImg.drfbtfGrbpiids();
 *          //
 *          // misdfllbnfous rfndfring dommbnds...
 *          //
 *          g.disposf();
 *      } wiilf (vImg.dontfntsLost());
 * }
 *
 *
 * // dopying from tif imbgf (ifrf, gSdrffn is tif Grbpiids
 * // objfdt for tif onsdrffn window)
 * do {
 *      int rfturnCodf = vImg.vblidbtf(gftGrbpiidsConfigurbtion());
 *      if (rfturnCodf == VolbtilfImbgf.IMAGE_RESTORED) {
 *          // Contfnts nffd to bf rfstorfd
 *          rfndfrOffsdrffn();      // rfstorf dontfnts
 *      } flsf if (rfturnCodf == VolbtilfImbgf.IMAGE_INCOMPATIBLE) {
 *          // old vImg dofsn't work witi nfw GrbpiidsConfig; rf-drfbtf it
 *          vImg = drfbtfVolbtilfImbgf(w, i);
 *          rfndfrOffsdrffn();
 *      }
 *      gSdrffn.drbwImbgf(vImg, 0, 0, tiis);
 * } wiilf (vImg.dontfntsLost());
 * </prf>
 * <P>
 * Notf tibt tiis dlbss subdlbssfs from tif {@link Imbgf} dlbss, wiidi
 * indludfs mftiods tibt tbkf bn {@link ImbgfObsfrvfr} pbrbmftfr for
 * bsyndironous notifidbtions bs informbtion is rfdfivfd from
 * b potfntibl {@link ImbgfProdudfr}.  Sindf tiis <dodf>VolbtilfImbgf</dodf>
 * is not lobdfd from bn bsyndironous sourdf, tif vbrious mftiods tibt tbkf
 * bn <dodf>ImbgfObsfrvfr</dodf> pbrbmftfr will bfibvf bs if tif dbtb ibs
 * blrfbdy bffn obtbinfd from tif <dodf>ImbgfProdudfr</dodf>.
 * Spfdifidblly, tiis mfbns tibt tif rfturn vblufs from sudi mftiods
 * will nfvfr indidbtf tibt tif informbtion is not yft bvbilbblf bnd
 * tif <dodf>ImbgfObsfrvfr</dodf> usfd in sudi mftiods will nfvfr
 * nffd to bf rfdordfd for bn bsyndironous dbllbbdk notifidbtion.
 * @sindf 1.4
 */
publid bbstrbdt dlbss VolbtilfImbgf fxtfnds Imbgf implfmfnts Trbnspbrfndy
{

    // Rfturn dodfs for vblidbtf() mftiod

    /**
     * Vblidbtfd imbgf is rfbdy to usf bs-is.
     */
    publid stbtid finbl int IMAGE_OK = 0;

    /**
     * Vblidbtfd imbgf ibs bffn rfstorfd bnd is now rfbdy to usf.
     * Notf tibt rfstorbtion dbusfs dontfnts of tif imbgf to bf lost.
     */
    publid stbtid finbl int IMAGE_RESTORED = 1;

    /**
     * Vblidbtfd imbgf is indompbtiblf witi supplifd
     * <dodf>GrbpiidsConfigurbtion</dodf> objfdt bnd siould bf
     * rf-drfbtfd bs bppropribtf.  Usbgf of tif imbgf bs-is
     * bftfr rfdfiving tiis rfturn dodf from <dodf>vblidbtf</dodf>
     * is undffinfd.
     */
    publid stbtid finbl int IMAGE_INCOMPATIBLE = 2;

    /**
     * Rfturns b stbtid snbpsiot imbgf of tiis objfdt.  Tif
     * <dodf>BufffrfdImbgf</dodf> rfturnfd is only durrfnt witi
     * tif <dodf>VolbtilfImbgf</dodf> bt tif timf of tif rfqufst
     * bnd will not bf updbtfd witi bny futurf dibngfs to tif
     * <dodf>VolbtilfImbgf</dodf>.
     * @rfturn b {@link BufffrfdImbgf} rfprfsfntbtion of tiis
     *          <dodf>VolbtilfImbgf</dodf>
     * @sff BufffrfdImbgf
     */
    publid bbstrbdt BufffrfdImbgf gftSnbpsiot();

    /**
     * Rfturns tif widti of tif <dodf>VolbtilfImbgf</dodf>.
     * @rfturn tif widti of tiis <dodf>VolbtilfImbgf</dodf>.
     */
    publid bbstrbdt int gftWidti();

    /**
     * Rfturns tif ifigit of tif <dodf>VolbtilfImbgf</dodf>.
     * @rfturn tif ifigit of tiis <dodf>VolbtilfImbgf</dodf>.
     */
    publid bbstrbdt int gftHfigit();

    // Imbgf ovfrridfs

    /**
     * Tiis rfturns bn ImbgfProdudfr for tiis VolbtilfImbgf.
     * Notf tibt tif VolbtilfImbgf objfdt is optimizfd for
     * rfndfring opfrbtions bnd blitting to tif sdrffn or otifr
     * VolbtilfImbgf objfdts, bs opposfd to rfbding bbdk tif
     * pixfls of tif imbgf.  Tifrfforf, opfrbtions sudi bs
     * <dodf>gftSourdf</dodf> mby not pfrform bs fbst bs
     * opfrbtions tibt do not rfly on rfbding tif pixfls.
     * Notf blso tibt tif pixfl vblufs rfbd from tif imbgf brf durrfnt
     * witi tiosf in tif imbgf only bt tif timf tibt tify brf
     * rftrifvfd. Tiis mftiod tbkfs b snbpsiot
     * of tif imbgf bt tif timf tif rfqufst is mbdf bnd tif
     * ImbgfProdudfr objfdt rfturnfd works witi
     * tibt stbtid snbpsiot imbgf, not tif originbl VolbtilfImbgf.
     * Cblling gftSourdf()
     * is fquivblfnt to dblling gftSnbpsiot().gftSourdf().
     * @rfturn bn {@link ImbgfProdudfr} tibt dbn bf usfd to produdf tif
     * pixfls for b <dodf>BufffrfdImbgf</dodf> rfprfsfntbtion of
     * tiis Imbgf.
     * @sff ImbgfProdudfr
     * @sff #gftSnbpsiot()
     */
    publid ImbgfProdudfr gftSourdf() {
        // REMIND: Mbkf surf tiis fundtionblity is in linf witi tif
        // spfd.  In pbrtidulbr, wf brf rfturning tif Sourdf for b
        // stbtid imbgf (tif snbpsiot), not b dibnging imbgf (tif
        // VolbtilfImbgf).  So if tif usfr fxpfdts tif Sourdf to bf
        // up-to-dbtf witi tif durrfnt dontfnts of tif VolbtilfImbgf,
        // tify will bf disbppointfd...
        // REMIND: Tiis bssumfs tibt gftSnbpsiot() rfturns somftiing
        // vblid bnd not tif dffbult null objfdt rfturnfd by tiis dlbss
        // (so it bssumfs tibt tif bdtubl VolbtilfImbgf objfdt is
        // subdlbssfd off somftiing tibt dofs tif rigit tiing
        // (f.g., SunVolbtilfImbgf).
        rfturn gftSnbpsiot().gftSourdf();
    }

    // REMIND: if wf wbnt bny dfdfnt pfrformbndf for gftSdblfdInstbndf(),
    // wf siould ovfrridf tif Imbgf implfmfntbtion of it...

    /**
     * Tiis mftiod rfturns b {@link Grbpiids2D}, but is ifrf
     * for bbdkwbrds dompbtibility.  {@link #drfbtfGrbpiids() drfbtfGrbpiids} is morf
     * donvfnifnt, sindf it is dfdlbrfd to rfturn b
     * <dodf>Grbpiids2D</dodf>.
     * @rfturn b <dodf>Grbpiids2D</dodf>, wiidi dbn bf usfd to drbw into
     *          tiis imbgf.
     */
    publid Grbpiids gftGrbpiids() {
        rfturn drfbtfGrbpiids();
    }

    /**
     * Crfbtfs b <dodf>Grbpiids2D</dodf>, wiidi dbn bf usfd to drbw into
     * tiis <dodf>VolbtilfImbgf</dodf>.
     * @rfturn b <dodf>Grbpiids2D</dodf>, usfd for drbwing into tiis
     *          imbgf.
     */
    publid bbstrbdt Grbpiids2D drfbtfGrbpiids();


    // Volbtilf mbnbgfmfnt mftiods

    /**
     * Attfmpts to rfstorf tif drbwing surfbdf of tif imbgf if tif surfbdf
     * ibd bffn lost sindf tif lbst <dodf>vblidbtf</dodf> dbll.  Also
     * vblidbtfs tiis imbgf bgbinst tif givfn GrbpiidsConfigurbtion
     * pbrbmftfr to sff wiftifr opfrbtions from tiis imbgf to tif
     * GrbpiidsConfigurbtion brf dompbtiblf.  An fxbmplf of bn
     * indompbtiblf dombinbtion migit bf b situbtion wifrf b VolbtilfImbgf
     * objfdt wbs drfbtfd on onf grbpiids dfvidf bnd tifn wbs usfd
     * to rfndfr to b difffrfnt grbpiids dfvidf.  Sindf VolbtilfImbgf
     * objfdts tfnd to bf vfry dfvidf-spfdifid, tiis opfrbtion migit
     * not work bs intfndfd, so tif rfturn dodf from tiis vblidbtf
     * dbll would notf tibt indompbtibility.  A null or indorrfdt
     * vbluf for gd mby dbusf indorrfdt vblufs to bf rfturnfd from
     * <dodf>vblidbtf</dodf> bnd mby dbusf lbtfr problfms witi rfndfring.
     *
     * @pbrbm   gd   b <dodf>GrbpiidsConfigurbtion</dodf> objfdt for tiis
     *          imbgf to bf vblidbtfd bgbinst.  A null gd implifs tibt tif
     *          vblidbtf mftiod siould skip tif dompbtibility tfst.
     * @rfturn  <dodf>IMAGE_OK</dodf> if tif imbgf did not nffd vblidbtion<BR>
     *          <dodf>IMAGE_RESTORED</dodf> if tif imbgf nffdfd rfstorbtion.
     *          Rfstorbtion implifs tibt tif dontfnts of tif imbgf mby ibvf
     *          bffn bfffdtfd bnd tif imbgf mby nffd to bf rf-rfndfrfd.<BR>
     *          <dodf>IMAGE_INCOMPATIBLE</dodf> if tif imbgf is indompbtiblf
     *          witi tif <dodf>GrbpiidsConfigurbtion</dodf> objfdt pbssfd
     *          into tif <dodf>vblidbtf</dodf> mftiod.  Indompbtibility
     *          implifs tibt tif imbgf mby nffd to bf rfdrfbtfd witi b
     *          nfw <dodf>Componfnt</dodf> or
     *          <dodf>GrbpiidsConfigurbtion</dodf> in ordfr to gft bn imbgf
     *          tibt dbn bf usfd suddfssfully witi tiis
     *          <dodf>GrbpiidsConfigurbtion</dodf>.
     *          An indompbtiblf imbgf is not difdkfd for wiftifr rfstorbtion
     *          wbs nfdfssbry, so tif stbtf of tif imbgf is undibngfd
     *          bftfr b rfturn vbluf of <dodf>IMAGE_INCOMPATIBLE</dodf>
     *          bnd tiis rfturn vbluf implifs notiing bbout wiftifr tif
     *          imbgf nffds to bf rfstorfd.
     * @sff jbvb.bwt.GrbpiidsConfigurbtion
     * @sff jbvb.bwt.Componfnt
     * @sff #IMAGE_OK
     * @sff #IMAGE_RESTORED
     * @sff #IMAGE_INCOMPATIBLE
     */
    publid bbstrbdt int vblidbtf(GrbpiidsConfigurbtion gd);

    /**
     * Rfturns <dodf>truf</dodf> if rfndfring dbtb wbs lost sindf lbst
     * <dodf>vblidbtf</dodf> dbll.  Tiis mftiod siould bf dbllfd by tif
     * bpplidbtion bt tif fnd of bny sfrifs of rfndfring opfrbtions to
     * or from tif imbgf to sff wiftifr
     * tif imbgf nffds to bf vblidbtfd bnd tif rfndfring rfdonf.
     * @rfturn <dodf>truf</dodf> if tif drbwing surfbdf nffds to bf rfstorfd;
     * <dodf>fblsf</dodf> otifrwisf.
     */
    publid bbstrbdt boolfbn dontfntsLost();

    /**
     * Rfturns bn ImbgfCbpbbilitifs objfdt wiidi dbn bf
     * inquirfd bs to tif spfdifid dbpbbilitifs of tiis
     * VolbtilfImbgf.  Tiis would bllow progrbmmfrs to find
     * out morf runtimf informbtion on tif spfdifid VolbtilfImbgf
     * objfdt tibt tify ibvf drfbtfd.  For fxbmplf, tif usfr
     * migit drfbtf b VolbtilfImbgf but tif systfm mby ibvf
     * no vidfo mfmory lfft for drfbting bn imbgf of tibt
     * sizf, so bltiougi tif objfdt is b VolbtilfImbgf, it is
     * not bs bddflfrbtfd bs otifr VolbtilfImbgf objfdts on
     * tiis plbtform migit bf.  Tif usfr migit wbnt tibt
     * informbtion to find otifr solutions to tifir problfm.
     * @rfturn bn <dodf>ImbgfCbpbbilitifs</dodf> objfdt tibt dontbins
     *         tif dbpbbilitifs of tiis <dodf>VolbtilfImbgf</dodf>.
     * @sindf 1.4
     */
    publid bbstrbdt ImbgfCbpbbilitifs gftCbpbbilitifs();

    /**
     * Tif trbnspbrfndy vbluf witi wiidi tiis imbgf wbs drfbtfd.
     * @sff jbvb.bwt.GrbpiidsConfigurbtion#drfbtfCompbtiblfVolbtilfImbgf(int,
     *      int,int)
     * @sff jbvb.bwt.GrbpiidsConfigurbtion#drfbtfCompbtiblfVolbtilfImbgf(int,
     *      int,ImbgfCbpbbilitifs,int)
     * @sff Trbnspbrfndy
     * @sindf 1.5
     */
    protfdtfd int trbnspbrfndy = TRANSLUCENT;

    /**
     * Rfturns tif trbnspbrfndy.  Rfturns fitifr OPAQUE, BITMASK,
     * or TRANSLUCENT.
     * @rfturn tif trbnspbrfndy of tiis <dodf>VolbtilfImbgf</dodf>.
     * @sff Trbnspbrfndy#OPAQUE
     * @sff Trbnspbrfndy#BITMASK
     * @sff Trbnspbrfndy#TRANSLUCENT
     * @sindf 1.5
     */
    publid int gftTrbnspbrfndy() {
        rfturn trbnspbrfndy;
    }
}
