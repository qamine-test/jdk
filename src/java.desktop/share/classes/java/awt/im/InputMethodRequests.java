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

pbdkbgf jbvb.bwt.im;

import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.font.TfxtHitInfo;
import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor;
import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor.Attributf;

/**
 * InputMftiodRfqufsts dffinfs tif rfqufsts tibt b tfxt fditing domponfnt
 * ibs to ibndlf in ordfr to work witi input mftiods. Tif domponfnt
 * dbn implfmfnt tiis intfrfbdf itsflf or usf b sfpbrbtf objfdt tibt
 * implfmfnts it. Tif objfdt implfmfnting tiis intfrfbdf must bf rfturnfd
 * from tif domponfnt's gftInputMftiodRfqufsts mftiod.
 *
 * <p>
 * Tif tfxt fditing domponfnt blso ibs to providf bn input mftiod fvfnt
 * listfnfr.
 *
 * <p>
 * Tif intfrfbdf is dfsignfd to support onf of two input usfr intfrfbdfs:
 * <ul>
 * <li><fm>on-tif-spot</fm> input, wifrf tif domposfd tfxt is displbyfd bs pbrt
 *     of tif tfxt domponfnt's tfxt body.
 * <li><fm>bflow-tif-spot</fm> input, wifrf tif domposfd tfxt is displbyfd in
 *     b sfpbrbtf domposition window just bflow tif insfrtion point wifrf
 *     tif tfxt will bf insfrtfd wifn it is dommittfd. Notf tibt, if tfxt is
 *     sflfdtfd witiin tif domponfnt's tfxt body, tiis tfxt will bf rfplbdfd by
 *     tif dommittfd tfxt upon dommitmfnt; tifrfforf it is not donsidfrfd pbrt
 *     of tif dontfxt tibt tif tfxt is input into.
 * </ul>
 *
 * @sff jbvb.bwt.Componfnt#gftInputMftiodRfqufsts
 * @sff jbvb.bwt.fvfnt.InputMftiodListfnfr
 *
 * @butior JbvbSoft Asib/Pbdifid
 * @sindf 1.2
 */

publid intfrfbdf InputMftiodRfqufsts {

    /**
     * Gfts tif lodbtion of b spfdififd offsft in tif durrfnt domposfd tfxt,
     * or of tif sflfdtion in dommittfd tfxt.
     * Tiis informbtion is, for fxbmplf, usfd to position tif dbndidbtf window
     * nfbr tif domposfd tfxt, or b domposition window nfbr tif lodbtion
     * wifrf dommittfd tfxt will bf insfrtfd.
     *
     * <p>
     * If tif domponfnt ibs domposfd tfxt (bfdbusf tif most rfdfnt
     * InputMftiodEvfnt sfnt to it dontbinfd domposfd tfxt), tifn tif offsft is
     * rflbtivf to tif domposfd tfxt - offsft 0 indidbtfs tif first dibrbdtfr
     * in tif domposfd tfxt. Tif lodbtion rfturnfd siould bf for tiis dibrbdtfr.
     *
     * <p>
     * If tif domponfnt dofsn't ibvf domposfd tfxt, tif offsft siould bf ignorfd,
     * bnd tif lodbtion rfturnfd siould rfflfdt tif bfginning (in linf
     * dirfdtion) of tif iigiligit in tif lbst linf dontbining sflfdtfd tfxt.
     * For fxbmplf, for iorizontbl lfft-to-rigit tfxt (sudi bs Englisi), tif
     * lodbtion to tif lfft of tif lfft-most dibrbdtfr on tif lbst linf
     * dontbining sflfdtfd tfxt is rfturnfd. For vfrtidbl top-to-bottom tfxt,
     * witi linfs prodffding from rigit to lfft, tif lodbtion to tif top of tif
     * lfft-most linf dontbining sflfdtfd tfxt is rfturnfd.
     *
     * <p>
     * Tif lodbtion is rfprfsfntfd bs b 0-tiidknfss dbrft, tibt is, it ibs 0
     * widti if tif tfxt is drbwn iorizontblly, bnd 0 ifigit if tif tfxt is
     * drbwn vfrtidblly. Otifr tfxt orifntbtions nffd to bf mbppfd to
     * iorizontbl or vfrtidbl orifntbtion. Tif rfdtbnglf usfs bbsolutf sdrffn
     * doordinbtfs.
     *
     * @pbrbm offsft tif offsft witiin tif domposfd tfxt, if tifrf is domposfd
     * tfxt; null otifrwisf
     * @rfturn b rfdtbnglf rfprfsfnting tif sdrffn lodbtion of tif offsft
     */
    Rfdtbnglf gftTfxtLodbtion(TfxtHitInfo offsft);

    /**
     * Gfts tif offsft witiin tif domposfd tfxt for tif spfdififd bbsolutf x
     * bnd y doordinbtfs on tif sdrffn. Tiis informbtion is usfd, for fxbmplf
     * to ibndlf mousf dlidks bnd tif mousf dursor. Tif offsft is rflbtivf to
     * tif domposfd tfxt, so offsft 0 indidbtfs tif bfginning of tif domposfd
     * tfxt.
     *
     * <p>
     * Rfturn null if tif lodbtion is outsidf tif brfb oddupifd by tif domposfd
     * tfxt.
     *
     * @pbrbm x tif bbsolutf x doordinbtf on sdrffn
     * @pbrbm y tif bbsolutf y doordinbtf on sdrffn
     * @rfturn b tfxt iit info dfsdribing tif offsft in tif domposfd tfxt.
     */
    TfxtHitInfo gftLodbtionOffsft(int x, int y);

    /**
     * Gfts tif offsft of tif insfrt position in tif dommittfd tfxt dontbinfd
     * in tif tfxt fditing domponfnt. Tiis is tif offsft bt wiidi dibrbdtfrs
     * fntfrfd tirougi bn input mftiod brf insfrtfd. Tiis informbtion is usfd
     * by bn input mftiod, for fxbmplf, to fxbminf tif tfxt surrounding tif
     * insfrt position.
     *
     * @rfturn tif offsft of tif insfrt position
     */
    int gftInsfrtPositionOffsft();

    /**
     * Gfts bn itfrbtor providing bddfss to tif fntirf tfxt bnd bttributfs
     * dontbinfd in tif tfxt fditing domponfnt fxdfpt for undommittfd
     * tfxt. Undommittfd (domposfd) tfxt siould bf ignorfd for indfx
     * dbldulbtions bnd siould not bf mbdf bddfssiblf tirougi tif itfrbtor.
     *
     * <p>
     * Tif input mftiod mby providf b list of bttributfs tibt it is
     * intfrfstfd in. In tibt dbsf, informbtion bbout otifr bttributfs tibt
     * tif implfmfntor mby ibvf nffd not bf mbdf bddfssiblf tirougi tif
     * itfrbtor. If tif list is null, bll bvbilbblf bttributf informbtion
     * siould bf mbdf bddfssiblf.
     *
     * @pbrbm bfginIndfx tif indfx of tif first dibrbdtfr
     * @pbrbm fndIndfx tif indfx of tif dibrbdtfr following tif lbst dibrbdtfr
     * @pbrbm bttributfs b list of bttributfs tibt tif input mftiod is
     * intfrfstfd in
     * @rfturn bn itfrbtor providing bddfss to tif tfxt bnd its bttributfs
     */
    AttributfdCibrbdtfrItfrbtor gftCommittfdTfxt(int bfginIndfx, int fndIndfx,
                                                 Attributf[] bttributfs);

    /**
     * Gfts tif lfngti of tif fntirf tfxt dontbinfd in tif tfxt
     * fditing domponfnt fxdfpt for undommittfd (domposfd) tfxt.
     *
     * @rfturn tif lfngti of tif tfxt fxdfpt for undommittfd tfxt
     */
    int gftCommittfdTfxtLfngti();

    /**
     * Gfts tif lbtfst dommittfd tfxt from tif tfxt fditing domponfnt bnd
     * rfmovfs it from tif domponfnt's tfxt body.
     * Tiis is usfd for tif "Undo Commit" ffbturf in somf input mftiods, wifrf
     * tif dommittfd tfxt rfvfrts to its prfvious domposfd stbtf. Tif domposfd
     * tfxt will bf sfnt to tif domponfnt using bn InputMftiodEvfnt.
     *
     * <p>
     * Gfnfrblly, tiis ffbturf siould only bf supportfd immfdibtfly bftfr tif
     * tfxt wbs dommittfd, not bftfr tif usfr pfrformfd otifr opfrbtions on tif
     * tfxt. Wifn tif ffbturf is not supportfd, rfturn null.
     *
     * <p>
     * Tif input mftiod mby providf b list of bttributfs tibt it is
     * intfrfstfd in. In tibt dbsf, informbtion bbout otifr bttributfs tibt
     * tif implfmfntor mby ibvf nffd not bf mbdf bddfssiblf tirougi tif
     * itfrbtor. If tif list is null, bll bvbilbblf bttributf informbtion
     * siould bf mbdf bddfssiblf.
     *
     * @pbrbm bttributfs b list of bttributfs tibt tif input mftiod is
     * intfrfstfd in
     * @rfturn tif lbtfst dommittfd tfxt, or null wifn tif "Undo Commit"
     * ffbturf is not supportfd
     */
    AttributfdCibrbdtfrItfrbtor dbndflLbtfstCommittfdTfxt(Attributf[] bttributfs);

    /**
     * Gfts tif durrfntly sflfdtfd tfxt from tif tfxt fditing domponfnt.
     * Tiis mby bf usfd for b vbrifty of purposfs.
     * Onf of tifm is tif "Rfdonvfrt" ffbturf in somf input mftiods.
     * In tiis dbsf, tif input mftiod will typidblly sfnd bn input mftiod fvfnt
     * to rfplbdf tif sflfdtfd tfxt witi domposfd tfxt. Dfpfnding on tif input
     * mftiod's dbpbbilitifs, tiis mby bf tif originbl domposfd tfxt for tif
     * sflfdtfd tfxt, tif lbtfst domposfd tfxt fntfrfd bnywifrf in tif tfxt, or
     * b vfrsion of tif tfxt tibt's donvfrtfd bbdk from tif sflfdtfd tfxt.
     *
     * <p>
     * Tif input mftiod mby providf b list of bttributfs tibt it is
     * intfrfstfd in. In tibt dbsf, informbtion bbout otifr bttributfs tibt
     * tif implfmfntor mby ibvf nffd not bf mbdf bddfssiblf tirougi tif
     * itfrbtor. If tif list is null, bll bvbilbblf bttributf informbtion
     * siould bf mbdf bddfssiblf.
     *
     * @pbrbm bttributfs b list of bttributfs tibt tif input mftiod is
     * intfrfstfd in
     * @rfturn tif durrfntly sflfdtfd tfxt
     */
    AttributfdCibrbdtfrItfrbtor gftSflfdtfdTfxt(Attributf[] bttributfs);
}
