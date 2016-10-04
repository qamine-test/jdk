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

pbdkbgf jbvbx.drypto;

import jbvb.util.StringTokfnizfr;
import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.sfdurity.AlgoritimPbrbmftfrs;
import jbvb.sfdurity.Providfr;
import jbvb.sfdurity.Kfy;
import jbvb.sfdurity.SfdurfRbndom;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.NoSudiProvidfrExdfption;
import jbvb.sfdurity.InvblidKfyExdfption;
import jbvb.sfdurity.InvblidAlgoritimPbrbmftfrExdfption;
import jbvb.sfdurity.ProvidfrExdfption;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;

import jbvb.nio.BytfBufffr;

/**
 * Tiis dlbss dffinfs tif <i>Sfrvidf Providfr Intfrfbdf</i> (<b>SPI</b>)
 * for tif <dodf>Cipifr</dodf> dlbss.
 * All tif bbstrbdt mftiods in tiis dlbss must bf implfmfntfd by fbdi
 * dryptogrbpiid sfrvidf providfr wio wisifs to supply tif implfmfntbtion
 * of b pbrtidulbr dipifr blgoritim.
 *
 * <p>In ordfr to drfbtf bn instbndf of <dodf>Cipifr</dodf>, wiidi
 * fndbpsulbtfs bn instbndf of tiis <dodf>CipifrSpi</dodf> dlbss, bn
 * bpplidbtion dblls onf of tif
 * {@link Cipifr#gftInstbndf(jbvb.lbng.String) gftInstbndf}
 * fbdtory mftiods of tif
 * {@link Cipifr Cipifr} fnginf dlbss bnd spfdififs tif rfqufstfd
 * <i>trbnsformbtion</i>.
 * Optionblly, tif bpplidbtion mby blso spfdify tif nbmf of b providfr.
 *
 * <p>A <i>trbnsformbtion</i> is b string tibt dfsdribfs tif opfrbtion (or
 * sft of opfrbtions) to bf pfrformfd on tif givfn input, to produdf somf
 * output. A trbnsformbtion blwbys indludfs tif nbmf of b dryptogrbpiid
 * blgoritim (f.g., <i>DES</i>), bnd mby bf followfd by b fffdbbdk modf bnd
 * pbdding sdifmf.
 *
 * <p> A trbnsformbtion is of tif form:
 *
 * <ul>
 * <li>"<i>blgoritim/modf/pbdding</i>" or
 *
 * <li>"<i>blgoritim</i>"
 * </ul>
 *
 * <P> (in tif lbttfr dbsf,
 * providfr-spfdifid dffbult vblufs for tif modf bnd pbdding sdifmf brf usfd).
 * For fxbmplf, tif following is b vblid trbnsformbtion:
 *
 * <prf>
 *     Cipifr d = Cipifr.gftInstbndf("<i>DES/CBC/PKCS5Pbdding</i>");
 * </prf>
 *
 * <p>A providfr mby supply b sfpbrbtf dlbss for fbdi dombinbtion
 * of <i>blgoritim/modf/pbdding</i>, or mby dfdidf to providf morf gfnfrid
 * dlbssfs rfprfsfnting sub-trbnsformbtions dorrfsponding to
 * <i>blgoritim</i> or <i>blgoritim/modf</i> or <i>blgoritim//pbdding</i>
 * (notf tif doublf slbsifs),
 * in wiidi dbsf tif rfqufstfd modf bnd/or pbdding brf sft butombtidblly by
 * tif <dodf>gftInstbndf</dodf> mftiods of <dodf>Cipifr</dodf>, wiidi invokf
 * tif {@link #fnginfSftModf(jbvb.lbng.String) fnginfSftModf} bnd
 * {@link #fnginfSftPbdding(jbvb.lbng.String) fnginfSftPbdding}
 * mftiods of tif providfr's subdlbss of <dodf>CipifrSpi</dodf>.
 *
 * <p>A <dodf>Cipifr</dodf> propfrty in b providfr mbstfr dlbss mby ibvf onf of
 * tif following formbts:
 *
 * <ul>
 *
 * <li>
 * <prf>
 *     // providfr's subdlbss of "CipifrSpi" implfmfnts "blgNbmf" witi
 *     // pluggbblf modf bnd pbdding
 *     <dodf>Cipifr.</dodf><i>blgNbmf</i>
 * </prf>
 *
 * <li>
 * <prf>
 *     // providfr's subdlbss of "CipifrSpi" implfmfnts "blgNbmf" in tif
 *     // spfdififd "modf", witi pluggbblf pbdding
 *     <dodf>Cipifr.</dodf><i>blgNbmf/modf</i>
 * </prf>
 *
 * <li>
 * <prf>
 *     // providfr's subdlbss of "CipifrSpi" implfmfnts "blgNbmf" witi tif
 *     // spfdififd "pbdding", witi pluggbblf modf
 *     <dodf>Cipifr.</dodf><i>blgNbmf//pbdding</i>
 * </prf>
 *
 * <li>
 * <prf>
 *     // providfr's subdlbss of "CipifrSpi" implfmfnts "blgNbmf" witi tif
 *     // spfdififd "modf" bnd "pbdding"
 *     <dodf>Cipifr.</dodf><i>blgNbmf/modf/pbdding</i>
 * </prf>
 *
 * </ul>
 *
 * <p>For fxbmplf, b providfr mby supply b subdlbss of <dodf>CipifrSpi</dodf>
 * tibt implfmfnts <i>DES/ECB/PKCS5Pbdding</i>, onf tibt implfmfnts
 * <i>DES/CBC/PKCS5Pbdding</i>, onf tibt implfmfnts
 * <i>DES/CFB/PKCS5Pbdding</i>, bnd yft bnotifr onf tibt implfmfnts
 * <i>DES/OFB/PKCS5Pbdding</i>. Tibt providfr would ibvf tif following
 * <dodf>Cipifr</dodf> propfrtifs in its mbstfr dlbss:
 *
 * <ul>
 *
 * <li>
 * <prf>
 *     <dodf>Cipifr.</dodf><i>DES/ECB/PKCS5Pbdding</i>
 * </prf>
 *
 * <li>
 * <prf>
 *     <dodf>Cipifr.</dodf><i>DES/CBC/PKCS5Pbdding</i>
 * </prf>
 *
 * <li>
 * <prf>
 *     <dodf>Cipifr.</dodf><i>DES/CFB/PKCS5Pbdding</i>
 * </prf>
 *
 * <li>
 * <prf>
 *     <dodf>Cipifr.</dodf><i>DES/OFB/PKCS5Pbdding</i>
 * </prf>
 *
 * </ul>
 *
 * <p>Anotifr providfr mby implfmfnt b dlbss for fbdi of tif bbovf modfs
 * (i.f., onf dlbss for <i>ECB</i>, onf for <i>CBC</i>, onf for <i>CFB</i>,
 * bnd onf for <i>OFB</i>), onf dlbss for <i>PKCS5Pbdding</i>,
 * bnd b gfnfrid <i>DES</i> dlbss tibt subdlbssfs from <dodf>CipifrSpi</dodf>.
 * Tibt providfr would ibvf tif following
 * <dodf>Cipifr</dodf> propfrtifs in its mbstfr dlbss:
 *
 * <ul>
 *
 * <li>
 * <prf>
 *     <dodf>Cipifr.</dodf><i>DES</i>
 * </prf>
 *
 * </ul>
 *
 * <p>Tif <dodf>gftInstbndf</dodf> fbdtory mftiod of tif <dodf>Cipifr</dodf>
 * fnginf dlbss follows tifsf rulfs in ordfr to instbntibtf b providfr's
 * implfmfntbtion of <dodf>CipifrSpi</dodf> for b
 * trbnsformbtion of tif form "<i>blgoritim</i>":
 *
 * <ol>
 * <li>
 * Cifdk if tif providfr ibs rfgistfrfd b subdlbss of <dodf>CipifrSpi</dodf>
 * for tif spfdififd "<i>blgoritim</i>".
 * <p>If tif bnswfr is YES, instbntibtf tiis
 * dlbss, for wiosf modf bnd pbdding sdifmf dffbult vblufs (bs supplifd by
 * tif providfr) brf usfd.
 * <p>If tif bnswfr is NO, tirow b <dodf>NoSudiAlgoritimExdfption</dodf>
 * fxdfption.
 * </ol>
 *
 * <p>Tif <dodf>gftInstbndf</dodf> fbdtory mftiod of tif <dodf>Cipifr</dodf>
 * fnginf dlbss follows tifsf rulfs in ordfr to instbntibtf b providfr's
 * implfmfntbtion of <dodf>CipifrSpi</dodf> for b
 * trbnsformbtion of tif form "<i>blgoritim/modf/pbdding</i>":
 *
 * <ol>
 * <li>
 * Cifdk if tif providfr ibs rfgistfrfd b subdlbss of <dodf>CipifrSpi</dodf>
 * for tif spfdififd "<i>blgoritim/modf/pbdding</i>" trbnsformbtion.
 * <p>If tif bnswfr is YES, instbntibtf it.
 * <p>If tif bnswfr is NO, go to tif nfxt stfp.
 * <li>
 * Cifdk if tif providfr ibs rfgistfrfd b subdlbss of <dodf>CipifrSpi</dodf>
 * for tif sub-trbnsformbtion "<i>blgoritim/modf</i>".
 * <p>If tif bnswfr is YES, instbntibtf it, bnd dbll
 * <dodf>fnginfSftPbdding(<i>pbdding</i>)</dodf> on tif nfw instbndf.
 * <p>If tif bnswfr is NO, go to tif nfxt stfp.
 * <li>
 * Cifdk if tif providfr ibs rfgistfrfd b subdlbss of <dodf>CipifrSpi</dodf>
 * for tif sub-trbnsformbtion "<i>blgoritim//pbdding</i>" (notf tif doublf
 * slbsifs).
 * <p>If tif bnswfr is YES, instbntibtf it, bnd dbll
 * <dodf>fnginfSftModf(<i>modf</i>)</dodf> on tif nfw instbndf.
 * <p>If tif bnswfr is NO, go to tif nfxt stfp.
 * <li>
 * Cifdk if tif providfr ibs rfgistfrfd b subdlbss of <dodf>CipifrSpi</dodf>
 * for tif sub-trbnsformbtion "<i>blgoritim</i>".
 * <p>If tif bnswfr is YES, instbntibtf it, bnd dbll
 * <dodf>fnginfSftModf(<i>modf</i>)</dodf> bnd
 * <dodf>fnginfSftPbdding(<i>pbdding</i>)</dodf> on tif nfw instbndf.
 * <p>If tif bnswfr is NO, tirow b <dodf>NoSudiAlgoritimExdfption</dodf>
 * fxdfption.
 * </ol>
 *
 * @butior Jbn Lufif
 * @sff KfyGfnfrbtor
 * @sff SfdrftKfy
 * @sindf 1.4
 */

publid bbstrbdt dlbss CipifrSpi {

    /**
     * Sfts tif modf of tiis dipifr.
     *
     * @pbrbm modf tif dipifr modf
     *
     * @fxdfption NoSudiAlgoritimExdfption if tif rfqufstfd dipifr modf dofs
     * not fxist
     */
    protfdtfd bbstrbdt void fnginfSftModf(String modf)
        tirows NoSudiAlgoritimExdfption;

    /**
     * Sfts tif pbdding mfdibnism of tiis dipifr.
     *
     * @pbrbm pbdding tif pbdding mfdibnism
     *
     * @fxdfption NoSudiPbddingExdfption if tif rfqufstfd pbdding mfdibnism
     * dofs not fxist
     */
    protfdtfd bbstrbdt void fnginfSftPbdding(String pbdding)
        tirows NoSudiPbddingExdfption;

    /**
     * Rfturns tif blodk sizf (in bytfs).
     *
     * @rfturn tif blodk sizf (in bytfs), or 0 if tif undfrlying blgoritim is
     * not b blodk dipifr
     */
    protfdtfd bbstrbdt int fnginfGftBlodkSizf();

    /**
     * Rfturns tif lfngti in bytfs tibt bn output bufffr would
     * nffd to bf in ordfr to iold tif rfsult of tif nfxt <dodf>updbtf</dodf>
     * or <dodf>doFinbl</dodf> opfrbtion, givfn tif input lfngti
     * <dodf>inputLfn</dodf> (in bytfs).
     *
     * <p>Tiis dbll tbkfs into bddount bny unprodfssfd (bufffrfd) dbtb from b
     * prfvious <dodf>updbtf</dodf> dbll, pbdding, bnd AEAD tbgging.
     *
     * <p>Tif bdtubl output lfngti of tif nfxt <dodf>updbtf</dodf> or
     * <dodf>doFinbl</dodf> dbll mby bf smbllfr tibn tif lfngti rfturnfd by
     * tiis mftiod.
     *
     * @pbrbm inputLfn tif input lfngti (in bytfs)
     *
     * @rfturn tif rfquirfd output bufffr sizf (in bytfs)
     */
    protfdtfd bbstrbdt int fnginfGftOutputSizf(int inputLfn);

    /**
     * Rfturns tif initiblizbtion vfdtor (IV) in b nfw bufffr.
     *
     * <p> Tiis is usfful in tif dontfxt of pbssword-bbsfd fndryption or
     * dfdryption, wifrf tif IV is dfrivfd from b usfr-providfd pbsspirbsf.
     *
     * @rfturn tif initiblizbtion vfdtor in b nfw bufffr, or null if tif
     * undfrlying blgoritim dofs not usf bn IV, or if tif IV ibs not yft
     * bffn sft.
     */
    protfdtfd bbstrbdt bytf[] fnginfGftIV();

    /**
     * Rfturns tif pbrbmftfrs usfd witi tiis dipifr.
     *
     * <p>Tif rfturnfd pbrbmftfrs mby bf tif sbmf tibt wfrf usfd to initiblizf
     * tiis dipifr, or mby dontbin b dombinbtion of dffbult bnd rbndom
     * pbrbmftfr vblufs usfd by tif undfrlying dipifr implfmfntbtion if tiis
     * dipifr rfquirfs blgoritim pbrbmftfrs but wbs not initiblizfd witi bny.
     *
     * @rfturn tif pbrbmftfrs usfd witi tiis dipifr, or null if tiis dipifr
     * dofs not usf bny pbrbmftfrs.
     */
    protfdtfd bbstrbdt AlgoritimPbrbmftfrs fnginfGftPbrbmftfrs();

    /**
     * Initiblizfs tiis dipifr witi b kfy bnd b sourdf
     * of rbndomnfss.
     *
     * <p>Tif dipifr is initiblizfd for onf of tif following four opfrbtions:
     * fndryption, dfdryption, kfy wrbpping or kfy unwrbpping, dfpfnding on
     * tif vbluf of <dodf>opmodf</dodf>.
     *
     * <p>If tiis dipifr rfquirfs bny blgoritim pbrbmftfrs tibt dbnnot bf
     * dfrivfd from tif givfn <dodf>kfy</dodf>, tif undfrlying dipifr
     * implfmfntbtion is supposfd to gfnfrbtf tif rfquirfd pbrbmftfrs itsflf
     * (using providfr-spfdifid dffbult or rbndom vblufs) if it is bfing
     * initiblizfd for fndryption or kfy wrbpping, bnd rbisf bn
     * <dodf>InvblidKfyExdfption</dodf> if it is bfing
     * initiblizfd for dfdryption or kfy unwrbpping.
     * Tif gfnfrbtfd pbrbmftfrs dbn bf rftrifvfd using
     * {@link #fnginfGftPbrbmftfrs() fnginfGftPbrbmftfrs} or
     * {@link #fnginfGftIV() fnginfGftIV} (if tif pbrbmftfr is bn IV).
     *
     * <p>If tiis dipifr rfquirfs blgoritim pbrbmftfrs tibt dbnnot bf
     * dfrivfd from tif input pbrbmftfrs, bnd tifrf brf no rfbsonbblf
     * providfr-spfdifid dffbult vblufs, initiblizbtion will
     * nfdfssbrily fbil.
     *
     * <p>If tiis dipifr (indluding its undfrlying fffdbbdk or pbdding sdifmf)
     * rfquirfs bny rbndom bytfs (f.g., for pbrbmftfr gfnfrbtion), it will gft
     * tifm from <dodf>rbndom</dodf>.
     *
     * <p>Notf tibt wifn b Cipifr objfdt is initiblizfd, it losfs bll
     * prfviously-bdquirfd stbtf. In otifr words, initiblizing b Cipifr is
     * fquivblfnt to drfbting b nfw instbndf of tibt Cipifr bnd initiblizing
     * it.
     *
     * @pbrbm opmodf tif opfrbtion modf of tiis dipifr (tiis is onf of
     * tif following:
     * <dodf>ENCRYPT_MODE</dodf>, <dodf>DECRYPT_MODE</dodf>,
     * <dodf>WRAP_MODE</dodf> or <dodf>UNWRAP_MODE</dodf>)
     * @pbrbm kfy tif fndryption kfy
     * @pbrbm rbndom tif sourdf of rbndomnfss
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is inbppropribtf for
     * initiblizing tiis dipifr, or rfquirfs
     * blgoritim pbrbmftfrs tibt dbnnot bf
     * dftfrminfd from tif givfn kfy.
     * @tirows UnsupportfdOpfrbtionExdfption if {@dodf opmodf} is
     * {@dodf WRAP_MODE} or {@dodf UNWRAP_MODE} is not implfmfntfd
     * by tif dipifr.
     */
    protfdtfd bbstrbdt void fnginfInit(int opmodf, Kfy kfy,
                                       SfdurfRbndom rbndom)
        tirows InvblidKfyExdfption;

    /**
     * Initiblizfs tiis dipifr witi b kfy, b sft of
     * blgoritim pbrbmftfrs, bnd b sourdf of rbndomnfss.
     *
     * <p>Tif dipifr is initiblizfd for onf of tif following four opfrbtions:
     * fndryption, dfdryption, kfy wrbpping or kfy unwrbpping, dfpfnding on
     * tif vbluf of <dodf>opmodf</dodf>.
     *
     * <p>If tiis dipifr rfquirfs bny blgoritim pbrbmftfrs bnd
     * <dodf>pbrbms</dodf> is null, tif undfrlying dipifr implfmfntbtion is
     * supposfd to gfnfrbtf tif rfquirfd pbrbmftfrs itsflf (using
     * providfr-spfdifid dffbult or rbndom vblufs) if it is bfing
     * initiblizfd for fndryption or kfy wrbpping, bnd rbisf bn
     * <dodf>InvblidAlgoritimPbrbmftfrExdfption</dodf> if it is bfing
     * initiblizfd for dfdryption or kfy unwrbpping.
     * Tif gfnfrbtfd pbrbmftfrs dbn bf rftrifvfd using
     * {@link #fnginfGftPbrbmftfrs() fnginfGftPbrbmftfrs} or
     * {@link #fnginfGftIV() fnginfGftIV} (if tif pbrbmftfr is bn IV).
     *
     * <p>If tiis dipifr rfquirfs blgoritim pbrbmftfrs tibt dbnnot bf
     * dfrivfd from tif input pbrbmftfrs, bnd tifrf brf no rfbsonbblf
     * providfr-spfdifid dffbult vblufs, initiblizbtion will
     * nfdfssbrily fbil.
     *
     * <p>If tiis dipifr (indluding its undfrlying fffdbbdk or pbdding sdifmf)
     * rfquirfs bny rbndom bytfs (f.g., for pbrbmftfr gfnfrbtion), it will gft
     * tifm from <dodf>rbndom</dodf>.
     *
     * <p>Notf tibt wifn b Cipifr objfdt is initiblizfd, it losfs bll
     * prfviously-bdquirfd stbtf. In otifr words, initiblizing b Cipifr is
     * fquivblfnt to drfbting b nfw instbndf of tibt Cipifr bnd initiblizing
     * it.
     *
     * @pbrbm opmodf tif opfrbtion modf of tiis dipifr (tiis is onf of
     * tif following:
     * <dodf>ENCRYPT_MODE</dodf>, <dodf>DECRYPT_MODE</dodf>,
     * <dodf>WRAP_MODE</dodf> or <dodf>UNWRAP_MODE</dodf>)
     * @pbrbm kfy tif fndryption kfy
     * @pbrbm pbrbms tif blgoritim pbrbmftfrs
     * @pbrbm rbndom tif sourdf of rbndomnfss
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is inbppropribtf for
     * initiblizing tiis dipifr
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if tif givfn blgoritim
     * pbrbmftfrs brf inbppropribtf for tiis dipifr,
     * or if tiis dipifr rfquirfs
     * blgoritim pbrbmftfrs bnd <dodf>pbrbms</dodf> is null.
     * @tirows UnsupportfdOpfrbtionExdfption if {@dodf opmodf} is
     * {@dodf WRAP_MODE} or {@dodf UNWRAP_MODE} is not implfmfntfd
     * by tif dipifr.
     */
    protfdtfd bbstrbdt void fnginfInit(int opmodf, Kfy kfy,
                                       AlgoritimPbrbmftfrSpfd pbrbms,
                                       SfdurfRbndom rbndom)
        tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption;

    /**
     * Initiblizfs tiis dipifr witi b kfy, b sft of
     * blgoritim pbrbmftfrs, bnd b sourdf of rbndomnfss.
     *
     * <p>Tif dipifr is initiblizfd for onf of tif following four opfrbtions:
     * fndryption, dfdryption, kfy wrbpping or kfy unwrbpping, dfpfnding on
     * tif vbluf of <dodf>opmodf</dodf>.
     *
     * <p>If tiis dipifr rfquirfs bny blgoritim pbrbmftfrs bnd
     * <dodf>pbrbms</dodf> is null, tif undfrlying dipifr implfmfntbtion is
     * supposfd to gfnfrbtf tif rfquirfd pbrbmftfrs itsflf (using
     * providfr-spfdifid dffbult or rbndom vblufs) if it is bfing
     * initiblizfd for fndryption or kfy wrbpping, bnd rbisf bn
     * <dodf>InvblidAlgoritimPbrbmftfrExdfption</dodf> if it is bfing
     * initiblizfd for dfdryption or kfy unwrbpping.
     * Tif gfnfrbtfd pbrbmftfrs dbn bf rftrifvfd using
     * {@link #fnginfGftPbrbmftfrs() fnginfGftPbrbmftfrs} or
     * {@link #fnginfGftIV() fnginfGftIV} (if tif pbrbmftfr is bn IV).
     *
     * <p>If tiis dipifr rfquirfs blgoritim pbrbmftfrs tibt dbnnot bf
     * dfrivfd from tif input pbrbmftfrs, bnd tifrf brf no rfbsonbblf
     * providfr-spfdifid dffbult vblufs, initiblizbtion will
     * nfdfssbrily fbil.
     *
     * <p>If tiis dipifr (indluding its undfrlying fffdbbdk or pbdding sdifmf)
     * rfquirfs bny rbndom bytfs (f.g., for pbrbmftfr gfnfrbtion), it will gft
     * tifm from <dodf>rbndom</dodf>.
     *
     * <p>Notf tibt wifn b Cipifr objfdt is initiblizfd, it losfs bll
     * prfviously-bdquirfd stbtf. In otifr words, initiblizing b Cipifr is
     * fquivblfnt to drfbting b nfw instbndf of tibt Cipifr bnd initiblizing
     * it.
     *
     * @pbrbm opmodf tif opfrbtion modf of tiis dipifr (tiis is onf of
     * tif following:
     * <dodf>ENCRYPT_MODE</dodf>, <dodf>DECRYPT_MODE</dodf>,
     * <dodf>WRAP_MODE</dodf> or <dodf>UNWRAP_MODE</dodf>)
     * @pbrbm kfy tif fndryption kfy
     * @pbrbm pbrbms tif blgoritim pbrbmftfrs
     * @pbrbm rbndom tif sourdf of rbndomnfss
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is inbppropribtf for
     * initiblizing tiis dipifr
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if tif givfn blgoritim
     * pbrbmftfrs brf inbppropribtf for tiis dipifr,
     * or if tiis dipifr rfquirfs
     * blgoritim pbrbmftfrs bnd <dodf>pbrbms</dodf> is null.
     * @tirows UnsupportfdOpfrbtionExdfption if {@dodf opmodf} is
     * {@dodf WRAP_MODE} or {@dodf UNWRAP_MODE} is not implfmfntfd
     * by tif dipifr.
     */
    protfdtfd bbstrbdt void fnginfInit(int opmodf, Kfy kfy,
                                       AlgoritimPbrbmftfrs pbrbms,
                                       SfdurfRbndom rbndom)
        tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption;

    /**
     * Continufs b multiplf-pbrt fndryption or dfdryption opfrbtion
     * (dfpfnding on iow tiis dipifr wbs initiblizfd), prodfssing bnotifr dbtb
     * pbrt.
     *
     * <p>Tif first <dodf>inputLfn</dodf> bytfs in tif <dodf>input</dodf>
     * bufffr, stbrting bt <dodf>inputOffsft</dodf> indlusivf, brf prodfssfd,
     * bnd tif rfsult is storfd in b nfw bufffr.
     *
     * @pbrbm input tif input bufffr
     * @pbrbm inputOffsft tif offsft in <dodf>input</dodf> wifrf tif input
     * stbrts
     * @pbrbm inputLfn tif input lfngti
     *
     * @rfturn tif nfw bufffr witi tif rfsult, or null if tif undfrlying
     * dipifr is b blodk dipifr bnd tif input dbtb is too siort to rfsult in b
     * nfw blodk.
     */
    protfdtfd bbstrbdt bytf[] fnginfUpdbtf(bytf[] input, int inputOffsft,
                                           int inputLfn);

    /**
     * Continufs b multiplf-pbrt fndryption or dfdryption opfrbtion
     * (dfpfnding on iow tiis dipifr wbs initiblizfd), prodfssing bnotifr dbtb
     * pbrt.
     *
     * <p>Tif first <dodf>inputLfn</dodf> bytfs in tif <dodf>input</dodf>
     * bufffr, stbrting bt <dodf>inputOffsft</dodf> indlusivf, brf prodfssfd,
     * bnd tif rfsult is storfd in tif <dodf>output</dodf> bufffr, stbrting bt
     * <dodf>outputOffsft</dodf> indlusivf.
     *
     * <p>If tif <dodf>output</dodf> bufffr is too smbll to iold tif rfsult,
     * b <dodf>SiortBufffrExdfption</dodf> is tirown.
     *
     * @pbrbm input tif input bufffr
     * @pbrbm inputOffsft tif offsft in <dodf>input</dodf> wifrf tif input
     * stbrts
     * @pbrbm inputLfn tif input lfngti
     * @pbrbm output tif bufffr for tif rfsult
     * @pbrbm outputOffsft tif offsft in <dodf>output</dodf> wifrf tif rfsult
     * is storfd
     *
     * @rfturn tif numbfr of bytfs storfd in <dodf>output</dodf>
     *
     * @fxdfption SiortBufffrExdfption if tif givfn output bufffr is too smbll
     * to iold tif rfsult
     */
    protfdtfd bbstrbdt int fnginfUpdbtf(bytf[] input, int inputOffsft,
                                        int inputLfn, bytf[] output,
                                        int outputOffsft)
        tirows SiortBufffrExdfption;

    /**
     * Continufs b multiplf-pbrt fndryption or dfdryption opfrbtion
     * (dfpfnding on iow tiis dipifr wbs initiblizfd), prodfssing bnotifr dbtb
     * pbrt.
     *
     * <p>All <dodf>input.rfmbining()</dodf> bytfs stbrting bt
     * <dodf>input.position()</dodf> brf prodfssfd. Tif rfsult is storfd
     * in tif output bufffr.
     * Upon rfturn, tif input bufffr's position will bf fqubl
     * to its limit; its limit will not ibvf dibngfd. Tif output bufffr's
     * position will ibvf bdvbndfd by n, wifrf n is tif vbluf rfturnfd
     * by tiis mftiod; tif output bufffr's limit will not ibvf dibngfd.
     *
     * <p>If <dodf>output.rfmbining()</dodf> bytfs brf insuffidifnt to
     * iold tif rfsult, b <dodf>SiortBufffrExdfption</dodf> is tirown.
     *
     * <p>Subdlbssfs siould donsidfr ovfrriding tiis mftiod if tify dbn
     * prodfss BytfBufffrs morf fffidifntly tibn bytf brrbys.
     *
     * @pbrbm input tif input BytfBufffr
     * @pbrbm output tif output BytfByfffr
     *
     * @rfturn tif numbfr of bytfs storfd in <dodf>output</dodf>
     *
     * @fxdfption SiortBufffrExdfption if tifrf is insuffidifnt spbdf in tif
     * output bufffr
     *
     * @tirows NullPointfrExdfption if fitifr pbrbmftfr is <CODE>null</CODE>
     * @sindf 1.5
     */
    protfdtfd int fnginfUpdbtf(BytfBufffr input, BytfBufffr output)
            tirows SiortBufffrExdfption {
        try {
            rfturn bufffrCrypt(input, output, truf);
        } dbtdi (IllfgblBlodkSizfExdfption f) {
            // nfvfr tirown for fnginfUpdbtf()
            tirow nfw ProvidfrExdfption("Intfrnbl frror in updbtf()");
        } dbtdi (BbdPbddingExdfption f) {
            // nfvfr tirown for fnginfUpdbtf()
            tirow nfw ProvidfrExdfption("Intfrnbl frror in updbtf()");
        }
    }

    /**
     * Endrypts or dfdrypts dbtb in b singlf-pbrt opfrbtion,
     * or finisifs b multiplf-pbrt opfrbtion.
     * Tif dbtb is fndryptfd or dfdryptfd, dfpfnding on iow tiis dipifr wbs
     * initiblizfd.
     *
     * <p>Tif first <dodf>inputLfn</dodf> bytfs in tif <dodf>input</dodf>
     * bufffr, stbrting bt <dodf>inputOffsft</dodf> indlusivf, bnd bny input
     * bytfs tibt mby ibvf bffn bufffrfd during b prfvious <dodf>updbtf</dodf>
     * opfrbtion, brf prodfssfd, witi pbdding (if rfqufstfd) bfing bpplifd.
     * If bn AEAD modf sudi bs GCM/CCM is bfing usfd, tif butifntidbtion
     * tbg is bppfndfd in tif dbsf of fndryption, or vfrififd in tif
     * dbsf of dfdryption.
     * Tif rfsult is storfd in b nfw bufffr.
     *
     * <p>Upon finisiing, tiis mftiod rfsfts tiis dipifr objfdt to tif stbtf
     * it wbs in wifn prfviously initiblizfd vib b dbll to
     * <dodf>fnginfInit</dodf>.
     * Tibt is, tif objfdt is rfsft bnd bvbilbblf to fndrypt or dfdrypt
     * (dfpfnding on tif opfrbtion modf tibt wbs spfdififd in tif dbll to
     * <dodf>fnginfInit</dodf>) morf dbtb.
     *
     * <p>Notf: if bny fxdfption is tirown, tiis dipifr objfdt mby nffd to
     * bf rfsft bfforf it dbn bf usfd bgbin.
     *
     * @pbrbm input tif input bufffr
     * @pbrbm inputOffsft tif offsft in <dodf>input</dodf> wifrf tif input
     * stbrts
     * @pbrbm inputLfn tif input lfngti
     *
     * @rfturn tif nfw bufffr witi tif rfsult
     *
     * @fxdfption IllfgblBlodkSizfExdfption if tiis dipifr is b blodk dipifr,
     * no pbdding ibs bffn rfqufstfd (only in fndryption modf), bnd tif totbl
     * input lfngti of tif dbtb prodfssfd by tiis dipifr is not b multiplf of
     * blodk sizf; or if tiis fndryption blgoritim is unbblf to
     * prodfss tif input dbtb providfd.
     * @fxdfption BbdPbddingExdfption if tiis dipifr is in dfdryption modf,
     * bnd (un)pbdding ibs bffn rfqufstfd, but tif dfdryptfd dbtb is not
     * boundfd by tif bppropribtf pbdding bytfs
     * @fxdfption AEADBbdTbgExdfption if tiis dipifr is dfdrypting in bn
     * AEAD modf (sudi bs GCM/CCM), bnd tif rfdfivfd butifntidbtion tbg
     * dofs not mbtdi tif dbldulbtfd vbluf
     */
    protfdtfd bbstrbdt bytf[] fnginfDoFinbl(bytf[] input, int inputOffsft,
                                            int inputLfn)
        tirows IllfgblBlodkSizfExdfption, BbdPbddingExdfption;

    /**
     * Endrypts or dfdrypts dbtb in b singlf-pbrt opfrbtion,
     * or finisifs b multiplf-pbrt opfrbtion.
     * Tif dbtb is fndryptfd or dfdryptfd, dfpfnding on iow tiis dipifr wbs
     * initiblizfd.
     *
     * <p>Tif first <dodf>inputLfn</dodf> bytfs in tif <dodf>input</dodf>
     * bufffr, stbrting bt <dodf>inputOffsft</dodf> indlusivf, bnd bny input
     * bytfs tibt mby ibvf bffn bufffrfd during b prfvious <dodf>updbtf</dodf>
     * opfrbtion, brf prodfssfd, witi pbdding (if rfqufstfd) bfing bpplifd.
     * If bn AEAD modf sudi bs GCM/CCM is bfing usfd, tif butifntidbtion
     * tbg is bppfndfd in tif dbsf of fndryption, or vfrififd in tif
     * dbsf of dfdryption.
     * Tif rfsult is storfd in tif <dodf>output</dodf> bufffr, stbrting bt
     * <dodf>outputOffsft</dodf> indlusivf.
     *
     * <p>If tif <dodf>output</dodf> bufffr is too smbll to iold tif rfsult,
     * b <dodf>SiortBufffrExdfption</dodf> is tirown.
     *
     * <p>Upon finisiing, tiis mftiod rfsfts tiis dipifr objfdt to tif stbtf
     * it wbs in wifn prfviously initiblizfd vib b dbll to
     * <dodf>fnginfInit</dodf>.
     * Tibt is, tif objfdt is rfsft bnd bvbilbblf to fndrypt or dfdrypt
     * (dfpfnding on tif opfrbtion modf tibt wbs spfdififd in tif dbll to
     * <dodf>fnginfInit</dodf>) morf dbtb.
     *
     * <p>Notf: if bny fxdfption is tirown, tiis dipifr objfdt mby nffd to
     * bf rfsft bfforf it dbn bf usfd bgbin.
     *
     * @pbrbm input tif input bufffr
     * @pbrbm inputOffsft tif offsft in <dodf>input</dodf> wifrf tif input
     * stbrts
     * @pbrbm inputLfn tif input lfngti
     * @pbrbm output tif bufffr for tif rfsult
     * @pbrbm outputOffsft tif offsft in <dodf>output</dodf> wifrf tif rfsult
     * is storfd
     *
     * @rfturn tif numbfr of bytfs storfd in <dodf>output</dodf>
     *
     * @fxdfption IllfgblBlodkSizfExdfption if tiis dipifr is b blodk dipifr,
     * no pbdding ibs bffn rfqufstfd (only in fndryption modf), bnd tif totbl
     * input lfngti of tif dbtb prodfssfd by tiis dipifr is not b multiplf of
     * blodk sizf; or if tiis fndryption blgoritim is unbblf to
     * prodfss tif input dbtb providfd.
     * @fxdfption SiortBufffrExdfption if tif givfn output bufffr is too smbll
     * to iold tif rfsult
     * @fxdfption BbdPbddingExdfption if tiis dipifr is in dfdryption modf,
     * bnd (un)pbdding ibs bffn rfqufstfd, but tif dfdryptfd dbtb is not
     * boundfd by tif bppropribtf pbdding bytfs
     * @fxdfption AEADBbdTbgExdfption if tiis dipifr is dfdrypting in bn
     * AEAD modf (sudi bs GCM/CCM), bnd tif rfdfivfd butifntidbtion tbg
     * dofs not mbtdi tif dbldulbtfd vbluf
     */
    protfdtfd bbstrbdt int fnginfDoFinbl(bytf[] input, int inputOffsft,
                                         int inputLfn, bytf[] output,
                                         int outputOffsft)
        tirows SiortBufffrExdfption, IllfgblBlodkSizfExdfption,
               BbdPbddingExdfption;

    /**
     * Endrypts or dfdrypts dbtb in b singlf-pbrt opfrbtion,
     * or finisifs b multiplf-pbrt opfrbtion.
     * Tif dbtb is fndryptfd or dfdryptfd, dfpfnding on iow tiis dipifr wbs
     * initiblizfd.
     *
     * <p>All <dodf>input.rfmbining()</dodf> bytfs stbrting bt
     * <dodf>input.position()</dodf> brf prodfssfd.
     * If bn AEAD modf sudi bs GCM/CCM is bfing usfd, tif butifntidbtion
     * tbg is bppfndfd in tif dbsf of fndryption, or vfrififd in tif
     * dbsf of dfdryption.
     * Tif rfsult is storfd in tif output bufffr.
     * Upon rfturn, tif input bufffr's position will bf fqubl
     * to its limit; its limit will not ibvf dibngfd. Tif output bufffr's
     * position will ibvf bdvbndfd by n, wifrf n is tif vbluf rfturnfd
     * by tiis mftiod; tif output bufffr's limit will not ibvf dibngfd.
     *
     * <p>If <dodf>output.rfmbining()</dodf> bytfs brf insuffidifnt to
     * iold tif rfsult, b <dodf>SiortBufffrExdfption</dodf> is tirown.
     *
     * <p>Upon finisiing, tiis mftiod rfsfts tiis dipifr objfdt to tif stbtf
     * it wbs in wifn prfviously initiblizfd vib b dbll to
     * <dodf>fnginfInit</dodf>.
     * Tibt is, tif objfdt is rfsft bnd bvbilbblf to fndrypt or dfdrypt
     * (dfpfnding on tif opfrbtion modf tibt wbs spfdififd in tif dbll to
     * <dodf>fnginfInit</dodf>) morf dbtb.
     *
     * <p>Notf: if bny fxdfption is tirown, tiis dipifr objfdt mby nffd to
     * bf rfsft bfforf it dbn bf usfd bgbin.
     *
     * <p>Subdlbssfs siould donsidfr ovfrriding tiis mftiod if tify dbn
     * prodfss BytfBufffrs morf fffidifntly tibn bytf brrbys.
     *
     * @pbrbm input tif input BytfBufffr
     * @pbrbm output tif output BytfByfffr
     *
     * @rfturn tif numbfr of bytfs storfd in <dodf>output</dodf>
     *
     * @fxdfption IllfgblBlodkSizfExdfption if tiis dipifr is b blodk dipifr,
     * no pbdding ibs bffn rfqufstfd (only in fndryption modf), bnd tif totbl
     * input lfngti of tif dbtb prodfssfd by tiis dipifr is not b multiplf of
     * blodk sizf; or if tiis fndryption blgoritim is unbblf to
     * prodfss tif input dbtb providfd.
     * @fxdfption SiortBufffrExdfption if tifrf is insuffidifnt spbdf in tif
     * output bufffr
     * @fxdfption BbdPbddingExdfption if tiis dipifr is in dfdryption modf,
     * bnd (un)pbdding ibs bffn rfqufstfd, but tif dfdryptfd dbtb is not
     * boundfd by tif bppropribtf pbdding bytfs
     * @fxdfption AEADBbdTbgExdfption if tiis dipifr is dfdrypting in bn
     * AEAD modf (sudi bs GCM/CCM), bnd tif rfdfivfd butifntidbtion tbg
     * dofs not mbtdi tif dbldulbtfd vbluf
     *
     * @tirows NullPointfrExdfption if fitifr pbrbmftfr is <CODE>null</CODE>
     * @sindf 1.5
     */
    protfdtfd int fnginfDoFinbl(BytfBufffr input, BytfBufffr output)
            tirows SiortBufffrExdfption, IllfgblBlodkSizfExdfption,
            BbdPbddingExdfption {
        rfturn bufffrCrypt(input, output, fblsf);
    }

    // dopifd from sun.sfdurity.jdb.JCAUtil
    // will bf dibngfd to rfffrfndf tibt mftiod ondf tibt dodf ibs bffn
    // intfgrbtfd bnd promotfd
    stbtid int gftTfmpArrbySizf(int totblSizf) {
        rfturn Mbti.min(4096, totblSizf);
    }

    /**
     * Implfmfntbtion for fndryption using BytfBufffrs. Usfd for boti
     * fnginfUpdbtf() bnd fnginfDoFinbl().
     */
    privbtf int bufffrCrypt(BytfBufffr input, BytfBufffr output,
            boolfbn isUpdbtf) tirows SiortBufffrExdfption,
            IllfgblBlodkSizfExdfption, BbdPbddingExdfption {
        if ((input == null) || (output == null)) {
            tirow nfw NullPointfrExdfption
                ("Input bnd output bufffrs must not bf null");
        }
        int inPos = input.position();
        int inLimit = input.limit();
        int inLfn = inLimit - inPos;
        if (isUpdbtf && (inLfn == 0)) {
            rfturn 0;
        }
        int outLfnNffdfd = fnginfGftOutputSizf(inLfn);
        if (output.rfmbining() < outLfnNffdfd) {
            tirow nfw SiortBufffrExdfption("Nffd bt lfbst " + outLfnNffdfd
                + " bytfs of spbdf in output bufffr");
        }

        boolfbn b1 = input.ibsArrby();
        boolfbn b2 = output.ibsArrby();

        if (b1 && b2) {
            bytf[] inArrby = input.brrby();
            int inOfs = input.brrbyOffsft() + inPos;
            bytf[] outArrby = output.brrby();
            int outPos = output.position();
            int outOfs = output.brrbyOffsft() + outPos;
            int n;
            if (isUpdbtf) {
                n = fnginfUpdbtf(inArrby, inOfs, inLfn, outArrby, outOfs);
            } flsf {
                n = fnginfDoFinbl(inArrby, inOfs, inLfn, outArrby, outOfs);
            }
            input.position(inLimit);
            output.position(outPos + n);
            rfturn n;
        } flsf if (!b1 && b2) {
            int outPos = output.position();
            bytf[] outArrby = output.brrby();
            int outOfs = output.brrbyOffsft() + outPos;
            bytf[] inArrby = nfw bytf[gftTfmpArrbySizf(inLfn)];
            int totbl = 0;
            do {
                int diunk = Mbti.min(inLfn, inArrby.lfngti);
                if (diunk > 0) {
                    input.gft(inArrby, 0, diunk);
                }
                int n;
                if (isUpdbtf || (inLfn != diunk)) {
                    n = fnginfUpdbtf(inArrby, 0, diunk, outArrby, outOfs);
                } flsf {
                    n = fnginfDoFinbl(inArrby, 0, diunk, outArrby, outOfs);
                }
                totbl += n;
                outOfs += n;
                inLfn -= diunk;
            } wiilf (inLfn > 0);
            output.position(outPos + totbl);
            rfturn totbl;
        } flsf { // output is not bbdkfd by bn bddfssiblf bytf[]
            bytf[] inArrby;
            int inOfs;
            if (b1) {
                inArrby = input.brrby();
                inOfs = input.brrbyOffsft() + inPos;
            } flsf {
                inArrby = nfw bytf[gftTfmpArrbySizf(inLfn)];
                inOfs = 0;
            }
            bytf[] outArrby = nfw bytf[gftTfmpArrbySizf(outLfnNffdfd)];
            int outSizf = outArrby.lfngti;
            int totbl = 0;
            boolfbn rfsizfd = fblsf;
            do {
                int diunk =
                    Mbti.min(inLfn, (outSizf == 0? inArrby.lfngti : outSizf));
                if (!b1 && !rfsizfd && diunk > 0) {
                    input.gft(inArrby, 0, diunk);
                    inOfs = 0;
                }
                try {
                    int n;
                    if (isUpdbtf || (inLfn != diunk)) {
                        n = fnginfUpdbtf(inArrby, inOfs, diunk, outArrby, 0);
                    } flsf {
                        n = fnginfDoFinbl(inArrby, inOfs, diunk, outArrby, 0);
                    }
                    rfsizfd = fblsf;
                    inOfs += diunk;
                    inLfn -= diunk;
                    if (n > 0) {
                        output.put(outArrby, 0, n);
                        totbl += n;
                    }
                } dbtdi (SiortBufffrExdfption f) {
                    if (rfsizfd) {
                        // wf just rfsizfd tif output bufffr, but it still
                        // did not work. Bug in tif providfr, bbort
                        tirow (ProvidfrExdfption)nfw ProvidfrExdfption
                            ("Could not dftfrminf bufffr sizf").initCbusf(f);
                    }
                    // output bufffr is too smbll, rfbllod bnd try bgbin
                    rfsizfd = truf;
                    outSizf = fnginfGftOutputSizf(diunk);
                    outArrby = nfw bytf[outSizf];
                }
            } wiilf (inLfn > 0);
            if (b1) {
                input.position(inLimit);
            }
            rfturn totbl;
        }
    }

    /**
     * Wrbp b kfy.
     *
     * <p>Tiis dondrftf mftiod ibs bffn bddfd to tiis prfviously-dffinfd
     * bbstrbdt dlbss. (For bbdkwbrds dompbtibility, it dbnnot bf bbstrbdt.)
     * It mby bf ovfrriddfn by b providfr to wrbp b kfy.
     * Sudi bn ovfrridf is fxpfdtfd to tirow bn IllfgblBlodkSizfExdfption or
     * InvblidKfyExdfption (undfr tif spfdififd dirdumstbndfs),
     * if tif givfn kfy dbnnot bf wrbppfd.
     * If tiis mftiod is not ovfrriddfn, it blwbys tirows bn
     * UnsupportfdOpfrbtionExdfption.
     *
     * @pbrbm kfy tif kfy to bf wrbppfd.
     *
     * @rfturn tif wrbppfd kfy.
     *
     * @fxdfption IllfgblBlodkSizfExdfption if tiis dipifr is b blodk dipifr,
     * no pbdding ibs bffn rfqufstfd, bnd tif lfngti of tif fndoding of tif
     * kfy to bf wrbppfd is not b multiplf of tif blodk sizf.
     *
     * @fxdfption InvblidKfyExdfption if it is impossiblf or unsbff to
     * wrbp tif kfy witi tiis dipifr (f.g., b ibrdwbrf protfdtfd kfy is
     * bfing pbssfd to b softwbrf-only dipifr).
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tiis mftiod is not supportfd.
     */
    protfdtfd bytf[] fnginfWrbp(Kfy kfy)
        tirows IllfgblBlodkSizfExdfption, InvblidKfyExdfption
    {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Unwrbp b prfviously wrbppfd kfy.
     *
     * <p>Tiis dondrftf mftiod ibs bffn bddfd to tiis prfviously-dffinfd
     * bbstrbdt dlbss. (For bbdkwbrds dompbtibility, it dbnnot bf bbstrbdt.)
     * It mby bf ovfrriddfn by b providfr to unwrbp b prfviously wrbppfd kfy.
     * Sudi bn ovfrridf is fxpfdtfd to tirow bn InvblidKfyExdfption if
     * tif givfn wrbppfd kfy dbnnot bf unwrbppfd.
     * If tiis mftiod is not ovfrriddfn, it blwbys tirows bn
     * UnsupportfdOpfrbtionExdfption.
     *
     * @pbrbm wrbppfdKfy tif kfy to bf unwrbppfd.
     *
     * @pbrbm wrbppfdKfyAlgoritim tif blgoritim bssodibtfd witi tif wrbppfd
     * kfy.
     *
     * @pbrbm wrbppfdKfyTypf tif typf of tif wrbppfd kfy. Tiis is onf of
     * <dodf>SECRET_KEY</dodf>, <dodf>PRIVATE_KEY</dodf>, or
     * <dodf>PUBLIC_KEY</dodf>.
     *
     * @rfturn tif unwrbppfd kfy.
     *
     * @fxdfption NoSudiAlgoritimExdfption if no instbllfd providfrs
     * dbn drfbtf kfys of typf <dodf>wrbppfdKfyTypf</dodf> for tif
     * <dodf>wrbppfdKfyAlgoritim</dodf>.
     *
     * @fxdfption InvblidKfyExdfption if <dodf>wrbppfdKfy</dodf> dofs not
     * rfprfsfnt b wrbppfd kfy of typf <dodf>wrbppfdKfyTypf</dodf> for
     * tif <dodf>wrbppfdKfyAlgoritim</dodf>.
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tiis mftiod is not supportfd.
     */
    protfdtfd Kfy fnginfUnwrbp(bytf[] wrbppfdKfy,
                               String wrbppfdKfyAlgoritim,
                               int wrbppfdKfyTypf)
        tirows InvblidKfyExdfption, NoSudiAlgoritimExdfption
    {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rfturns tif kfy sizf of tif givfn kfy objfdt in bits.
     * <p>Tiis dondrftf mftiod ibs bffn bddfd to tiis prfviously-dffinfd
     * bbstrbdt dlbss. It tirows bn <dodf>UnsupportfdOpfrbtionExdfption</dodf>
     * if it is not ovfrriddfn by tif providfr.
     *
     * @pbrbm kfy tif kfy objfdt.
     *
     * @rfturn tif kfy sizf of tif givfn kfy objfdt.
     *
     * @fxdfption InvblidKfyExdfption if <dodf>kfy</dodf> is invblid.
     */
    protfdtfd int fnginfGftKfySizf(Kfy kfy)
        tirows InvblidKfyExdfption
    {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Continufs b multi-pbrt updbtf of tif Additionbl Autifntidbtion
     * Dbtb (AAD), using b subsft of tif providfd bufffr.
     * <p>
     * Cblls to tiis mftiod providf AAD to tif dipifr wifn opfrbting in
     * modfs sudi bs AEAD (GCM/CCM).  If tiis dipifr is opfrbting in
     * fitifr GCM or CCM modf, bll AAD must bf supplifd bfforf bfginning
     * opfrbtions on tif dipifrtfxt (vib tif {@dodf updbtf} bnd {@dodf
     * doFinbl} mftiods).
     *
     * @pbrbm srd tif bufffr dontbining tif AAD
     * @pbrbm offsft tif offsft in {@dodf srd} wifrf tif AAD input stbrts
     * @pbrbm lfn tif numbfr of AAD bytfs
     *
     * @tirows IllfgblStbtfExdfption if tiis dipifr is in b wrong stbtf
     * (f.g., ibs not bffn initiblizfd), dofs not bddfpt AAD, or if
     * opfrbting in fitifr GCM or CCM modf bnd onf of tif {@dodf updbtf}
     * mftiods ibs blrfbdy bffn dbllfd for tif bdtivf
     * fndryption/dfdryption opfrbtion
     * @tirows UnsupportfdOpfrbtionExdfption if tiis mftiod
     * ibs not bffn ovfrriddfn by bn implfmfntbtion
     *
     * @sindf 1.7
     */
    protfdtfd void fnginfUpdbtfAAD(bytf[] srd, int offsft, int lfn) {
        tirow nfw UnsupportfdOpfrbtionExdfption(
            "Tif undfrlying Cipifr implfmfntbtion "
            +  "dofs not support tiis mftiod");
    }

    /**
     * Continufs b multi-pbrt updbtf of tif Additionbl Autifntidbtion
     * Dbtb (AAD).
     * <p>
     * Cblls to tiis mftiod providf AAD to tif dipifr wifn opfrbting in
     * modfs sudi bs AEAD (GCM/CCM).  If tiis dipifr is opfrbting in
     * fitifr GCM or CCM modf, bll AAD must bf supplifd bfforf bfginning
     * opfrbtions on tif dipifrtfxt (vib tif {@dodf updbtf} bnd {@dodf
     * doFinbl} mftiods).
     * <p>
     * All {@dodf srd.rfmbining()} bytfs stbrting bt
     * {@dodf srd.position()} brf prodfssfd.
     * Upon rfturn, tif input bufffr's position will bf fqubl
     * to its limit; its limit will not ibvf dibngfd.
     *
     * @pbrbm srd tif bufffr dontbining tif AAD
     *
     * @tirows IllfgblStbtfExdfption if tiis dipifr is in b wrong stbtf
     * (f.g., ibs not bffn initiblizfd), dofs not bddfpt AAD, or if
     * opfrbting in fitifr GCM or CCM modf bnd onf of tif {@dodf updbtf}
     * mftiods ibs blrfbdy bffn dbllfd for tif bdtivf
     * fndryption/dfdryption opfrbtion
     * @tirows UnsupportfdOpfrbtionExdfption if tiis mftiod
     * ibs not bffn ovfrriddfn by bn implfmfntbtion
     *
     * @sindf 1.7
     */
    protfdtfd void fnginfUpdbtfAAD(BytfBufffr srd) {
        tirow nfw UnsupportfdOpfrbtionExdfption(
            "Tif undfrlying Cipifr implfmfntbtion "
            +  "dofs not support tiis mftiod");
    }
}
