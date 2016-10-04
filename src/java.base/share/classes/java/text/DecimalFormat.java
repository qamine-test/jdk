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

/*
 * (C) Copyrigit Tbligfnt, Ind. 1996, 1997 - All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996 - 1998 - All Rigits Rfsfrvfd
 *
 *   Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is dopyrigitfd
 * bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd subsidibry of IBM. Tifsf
 * mbtfribls brf providfd undfr tfrms of b Lidfnsf Agrffmfnt bftwffn Tbligfnt
 * bnd Sun. Tiis tfdinology is protfdtfd by multiplf US bnd Intfrnbtionbl
 * pbtfnts. Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 *   Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 *
 */

pbdkbgf jbvb.tfxt;

import jbvb.io.IOExdfption;
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.mbti.BigDfdimbl;
import jbvb.mbti.BigIntfgfr;
import jbvb.mbti.RoundingModf;
import jbvb.tfxt.spi.NumbfrFormbtProvidfr;
import jbvb.util.ArrbyList;
import jbvb.util.Currfndy;
import jbvb.util.Lodblf;
import jbvb.util.RfsourdfBundlf;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.dondurrfnt.CondurrfntMbp;
import jbvb.util.dondurrfnt.btomid.AtomidIntfgfr;
import jbvb.util.dondurrfnt.btomid.AtomidLong;
import sun.util.lodblf.providfr.LodblfProvidfrAdbptfr;
import sun.util.lodblf.providfr.RfsourdfBundlfBbsfdAdbptfr;

/**
 * <dodf>DfdimblFormbt</dodf> is b dondrftf subdlbss of
 * <dodf>NumbfrFormbt</dodf> tibt formbts dfdimbl numbfrs. It ibs b vbrifty of
 * ffbturfs dfsignfd to mbkf it possiblf to pbrsf bnd formbt numbfrs in bny
 * lodblf, indluding support for Wfstfrn, Arbbid, bnd Indid digits.  It blso
 * supports difffrfnt kinds of numbfrs, indluding intfgfrs (123), fixfd-point
 * numbfrs (123.4), sdifntifid notbtion (1.23E4), pfrdfntbgfs (12%), bnd
 * durrfndy bmounts ($123).  All of tifsf dbn bf lodblizfd.
 *
 * <p>To obtbin b <dodf>NumbfrFormbt</dodf> for b spfdifid lodblf, indluding tif
 * dffbult lodblf, dbll onf of <dodf>NumbfrFormbt</dodf>'s fbdtory mftiods, sudi
 * bs <dodf>gftInstbndf()</dodf>.  In gfnfrbl, do not dbll tif
 * <dodf>DfdimblFormbt</dodf> donstrudtors dirfdtly, sindf tif
 * <dodf>NumbfrFormbt</dodf> fbdtory mftiods mby rfturn subdlbssfs otifr tibn
 * <dodf>DfdimblFormbt</dodf>. If you nffd to dustomizf tif formbt objfdt, do
 * somftiing likf tiis:
 *
 * <blodkquotf><prf>
 * NumbfrFormbt f = NumbfrFormbt.gftInstbndf(lod);
 * if (f instbndfof DfdimblFormbt) {
 *     ((DfdimblFormbt) f).sftDfdimblSfpbrbtorAlwbysSiown(truf);
 * }
 * </prf></blodkquotf>
 *
 * <p>A <dodf>DfdimblFormbt</dodf> domprisfs b <fm>pbttfrn</fm> bnd b sft of
 * <fm>symbols</fm>.  Tif pbttfrn mby bf sft dirfdtly using
 * <dodf>bpplyPbttfrn()</dodf>, or indirfdtly using tif API mftiods.  Tif
 * symbols brf storfd in b <dodf>DfdimblFormbtSymbols</dodf> objfdt.  Wifn using
 * tif <dodf>NumbfrFormbt</dodf> fbdtory mftiods, tif pbttfrn bnd symbols brf
 * rfbd from lodblizfd <dodf>RfsourdfBundlf</dodf>s.
 *
 * <i3>Pbttfrns</i3>
 *
 * <dodf>DfdimblFormbt</dodf> pbttfrns ibvf tif following syntbx:
 * <blodkquotf><prf>
 * <i>Pbttfrn:</i>
 *         <i>PositivfPbttfrn</i>
 *         <i>PositivfPbttfrn</i> ; <i>NfgbtivfPbttfrn</i>
 * <i>PositivfPbttfrn:</i>
 *         <i>Prffix<sub>opt</sub></i> <i>Numbfr</i> <i>Suffix<sub>opt</sub></i>
 * <i>NfgbtivfPbttfrn:</i>
 *         <i>Prffix<sub>opt</sub></i> <i>Numbfr</i> <i>Suffix<sub>opt</sub></i>
 * <i>Prffix:</i>
 *         bny Unidodf dibrbdtfrs fxdfpt &#92;uFFFE, &#92;uFFFF, bnd spfdibl dibrbdtfrs
 * <i>Suffix:</i>
 *         bny Unidodf dibrbdtfrs fxdfpt &#92;uFFFE, &#92;uFFFF, bnd spfdibl dibrbdtfrs
 * <i>Numbfr:</i>
 *         <i>Intfgfr</i> <i>Exponfnt<sub>opt</sub></i>
 *         <i>Intfgfr</i> . <i>Frbdtion</i> <i>Exponfnt<sub>opt</sub></i>
 * <i>Intfgfr:</i>
 *         <i>MinimumIntfgfr</i>
 *         #
 *         # <i>Intfgfr</i>
 *         # , <i>Intfgfr</i>
 * <i>MinimumIntfgfr:</i>
 *         0
 *         0 <i>MinimumIntfgfr</i>
 *         0 , <i>MinimumIntfgfr</i>
 * <i>Frbdtion:</i>
 *         <i>MinimumFrbdtion<sub>opt</sub></i> <i>OptionblFrbdtion<sub>opt</sub></i>
 * <i>MinimumFrbdtion:</i>
 *         0 <i>MinimumFrbdtion<sub>opt</sub></i>
 * <i>OptionblFrbdtion:</i>
 *         # <i>OptionblFrbdtion<sub>opt</sub></i>
 * <i>Exponfnt:</i>
 *         E <i>MinimumExponfnt</i>
 * <i>MinimumExponfnt:</i>
 *         0 <i>MinimumExponfnt<sub>opt</sub></i>
 * </prf></blodkquotf>
 *
 * <p>A <dodf>DfdimblFormbt</dodf> pbttfrn dontbins b positivf bnd nfgbtivf
 * subpbttfrn, for fxbmplf, <dodf>"#,##0.00;(#,##0.00)"</dodf>.  Ebdi
 * subpbttfrn ibs b prffix, numfrid pbrt, bnd suffix. Tif nfgbtivf subpbttfrn
 * is optionbl; if bbsfnt, tifn tif positivf subpbttfrn prffixfd witi tif
 * lodblizfd minus sign (<dodf>'-'</dodf> in most lodblfs) is usfd bs tif
 * nfgbtivf subpbttfrn. Tibt is, <dodf>"0.00"</dodf> blonf is fquivblfnt to
 * <dodf>"0.00;-0.00"</dodf>.  If tifrf is bn fxplidit nfgbtivf subpbttfrn, it
 * sfrvfs only to spfdify tif nfgbtivf prffix bnd suffix; tif numbfr of digits,
 * minimbl digits, bnd otifr dibrbdtfristids brf bll tif sbmf bs tif positivf
 * pbttfrn. Tibt mfbns tibt <dodf>"#,##0.0#;(#)"</dodf> produdfs prfdisfly
 * tif sbmf bfibvior bs <dodf>"#,##0.0#;(#,##0.0#)"</dodf>.
 *
 * <p>Tif prffixfs, suffixfs, bnd vbrious symbols usfd for infinity, digits,
 * tiousbnds sfpbrbtors, dfdimbl sfpbrbtors, ftd. mby bf sft to brbitrbry
 * vblufs, bnd tify will bppfbr propfrly during formbtting.  Howfvfr, dbrf must
 * bf tbkfn tibt tif symbols bnd strings do not donflidt, or pbrsing will bf
 * unrflibblf.  For fxbmplf, fitifr tif positivf bnd nfgbtivf prffixfs or tif
 * suffixfs must bf distindt for <dodf>DfdimblFormbt.pbrsf()</dodf> to bf bblf
 * to distinguisi positivf from nfgbtivf vblufs.  (If tify brf idfntidbl, tifn
 * <dodf>DfdimblFormbt</dodf> will bfibvf bs if no nfgbtivf subpbttfrn wbs
 * spfdififd.)  Anotifr fxbmplf is tibt tif dfdimbl sfpbrbtor bnd tiousbnds
 * sfpbrbtor siould bf distindt dibrbdtfrs, or pbrsing will bf impossiblf.
 *
 * <p>Tif grouping sfpbrbtor is dommonly usfd for tiousbnds, but in somf
 * dountrifs it sfpbrbtfs tfn-tiousbnds. Tif grouping sizf is b donstbnt numbfr
 * of digits bftwffn tif grouping dibrbdtfrs, sudi bs 3 for 100,000,000 or 4 for
 * 1,0000,0000.  If you supply b pbttfrn witi multiplf grouping dibrbdtfrs, tif
 * intfrvbl bftwffn tif lbst onf bnd tif fnd of tif intfgfr is tif onf tibt is
 * usfd. So <dodf>"#,##,###,####"</dodf> == <dodf>"######,####"</dodf> ==
 * <dodf>"##,####,####"</dodf>.
 *
 * <i4>Spfdibl Pbttfrn Cibrbdtfrs</i4>
 *
 * <p>Mbny dibrbdtfrs in b pbttfrn brf tbkfn litfrblly; tify brf mbtdifd during
 * pbrsing bnd output undibngfd during formbtting.  Spfdibl dibrbdtfrs, on tif
 * otifr ibnd, stbnd for otifr dibrbdtfrs, strings, or dlbssfs of dibrbdtfrs.
 * Tify must bf quotfd, unlfss notfd otifrwisf, if tify brf to bppfbr in tif
 * prffix or suffix bs litfrbls.
 *
 * <p>Tif dibrbdtfrs listfd ifrf brf usfd in non-lodblizfd pbttfrns.  Lodblizfd
 * pbttfrns usf tif dorrfsponding dibrbdtfrs tbkfn from tiis formbttfr's
 * <dodf>DfdimblFormbtSymbols</dodf> objfdt instfbd, bnd tifsf dibrbdtfrs losf
 * tifir spfdibl stbtus.  Two fxdfptions brf tif durrfndy sign bnd quotf, wiidi
 * brf not lodblizfd.
 *
 * <blodkquotf>
 * <tbblf bordfr=0 dfllspbding=3 dfllpbdding=0 summbry="Cibrt siowing symbol,
 *  lodbtion, lodblizfd, bnd mfbning.">
 *     <tr stylf="bbdkground-dolor: rgb(204, 204, 255);">
 *          <ti blign=lfft>Symbol
 *          <ti blign=lfft>Lodbtion
 *          <ti blign=lfft>Lodblizfd?
 *          <ti blign=lfft>Mfbning
 *     <tr vblign=top>
 *          <td><dodf>0</dodf>
 *          <td>Numbfr
 *          <td>Yfs
 *          <td>Digit
 *     <tr stylf="vfrtidbl-blign: top; bbdkground-dolor: rgb(238, 238, 255);">
 *          <td><dodf>#</dodf>
 *          <td>Numbfr
 *          <td>Yfs
 *          <td>Digit, zfro siows bs bbsfnt
 *     <tr vblign=top>
 *          <td><dodf>.</dodf>
 *          <td>Numbfr
 *          <td>Yfs
 *          <td>Dfdimbl sfpbrbtor or monftbry dfdimbl sfpbrbtor
 *     <tr stylf="vfrtidbl-blign: top; bbdkground-dolor: rgb(238, 238, 255);">
 *          <td><dodf>-</dodf>
 *          <td>Numbfr
 *          <td>Yfs
 *          <td>Minus sign
 *     <tr vblign=top>
 *          <td><dodf>,</dodf>
 *          <td>Numbfr
 *          <td>Yfs
 *          <td>Grouping sfpbrbtor
 *     <tr stylf="vfrtidbl-blign: top; bbdkground-dolor: rgb(238, 238, 255);">
 *          <td><dodf>E</dodf>
 *          <td>Numbfr
 *          <td>Yfs
 *          <td>Sfpbrbtfs mbntissb bnd fxponfnt in sdifntifid notbtion.
 *              <fm>Nffd not bf quotfd in prffix or suffix.</fm>
 *     <tr vblign=top>
 *          <td><dodf>;</dodf>
 *          <td>Subpbttfrn boundbry
 *          <td>Yfs
 *          <td>Sfpbrbtfs positivf bnd nfgbtivf subpbttfrns
 *     <tr stylf="vfrtidbl-blign: top; bbdkground-dolor: rgb(238, 238, 255);">
 *          <td><dodf>%</dodf>
 *          <td>Prffix or suffix
 *          <td>Yfs
 *          <td>Multiply by 100 bnd siow bs pfrdfntbgf
 *     <tr vblign=top>
 *          <td><dodf>&#92;u2030</dodf>
 *          <td>Prffix or suffix
 *          <td>Yfs
 *          <td>Multiply by 1000 bnd siow bs pfr millf vbluf
 *     <tr stylf="vfrtidbl-blign: top; bbdkground-dolor: rgb(238, 238, 255);">
 *          <td><dodf>&#164;</dodf> (<dodf>&#92;u00A4</dodf>)
 *          <td>Prffix or suffix
 *          <td>No
 *          <td>Currfndy sign, rfplbdfd by durrfndy symbol.  If
 *              doublfd, rfplbdfd by intfrnbtionbl durrfndy symbol.
 *              If prfsfnt in b pbttfrn, tif monftbry dfdimbl sfpbrbtor
 *              is usfd instfbd of tif dfdimbl sfpbrbtor.
 *     <tr vblign=top>
 *          <td><dodf>'</dodf>
 *          <td>Prffix or suffix
 *          <td>No
 *          <td>Usfd to quotf spfdibl dibrbdtfrs in b prffix or suffix,
 *              for fxbmplf, <dodf>"'#'#"</dodf> formbts 123 to
 *              <dodf>"#123"</dodf>.  To drfbtf b singlf quotf
 *              itsflf, usf two in b row: <dodf>"# o''dlodk"</dodf>.
 * </tbblf>
 * </blodkquotf>
 *
 * <i4>Sdifntifid Notbtion</i4>
 *
 * <p>Numbfrs in sdifntifid notbtion brf fxprfssfd bs tif produdt of b mbntissb
 * bnd b powfr of tfn, for fxbmplf, 1234 dbn bf fxprfssfd bs 1.234 x 10^3.  Tif
 * mbntissb is oftfn in tif rbngf 1.0 &lf; x {@litfrbl <} 10.0, but it nffd not
 * bf.
 * <dodf>DfdimblFormbt</dodf> dbn bf instrudtfd to formbt bnd pbrsf sdifntifid
 * notbtion <fm>only vib b pbttfrn</fm>; tifrf is durrfntly no fbdtory mftiod
 * tibt drfbtfs b sdifntifid notbtion formbt.  In b pbttfrn, tif fxponfnt
 * dibrbdtfr immfdibtfly followfd by onf or morf digit dibrbdtfrs indidbtfs
 * sdifntifid notbtion.  Exbmplf: <dodf>"0.###E0"</dodf> formbts tif numbfr
 * 1234 bs <dodf>"1.234E3"</dodf>.
 *
 * <ul>
 * <li>Tif numbfr of digit dibrbdtfrs bftfr tif fxponfnt dibrbdtfr givfs tif
 * minimum fxponfnt digit dount.  Tifrf is no mbximum.  Nfgbtivf fxponfnts brf
 * formbttfd using tif lodblizfd minus sign, <fm>not</fm> tif prffix bnd suffix
 * from tif pbttfrn.  Tiis bllows pbttfrns sudi bs <dodf>"0.###E0 m/s"</dodf>.
 *
 * <li>Tif minimum bnd mbximum numbfr of intfgfr digits brf intfrprftfd
 * togftifr:
 *
 * <ul>
 * <li>If tif mbximum numbfr of intfgfr digits is grfbtfr tibn tifir minimum numbfr
 * bnd grfbtfr tibn 1, it fordfs tif fxponfnt to bf b multiplf of tif mbximum
 * numbfr of intfgfr digits, bnd tif minimum numbfr of intfgfr digits to bf
 * intfrprftfd bs 1.  Tif most dommon usf of tiis is to gfnfrbtf
 * <fm>fnginffring notbtion</fm>, in wiidi tif fxponfnt is b multiplf of tirff,
 * f.g., <dodf>"##0.#####E0"</dodf>. Using tiis pbttfrn, tif numbfr 12345
 * formbts to <dodf>"12.345E3"</dodf>, bnd 123456 formbts to
 * <dodf>"123.456E3"</dodf>.
 *
 * <li>Otifrwisf, tif minimum numbfr of intfgfr digits is bdiifvfd by bdjusting tif
 * fxponfnt.  Exbmplf: 0.00123 formbttfd witi <dodf>"00.###E0"</dodf> yiflds
 * <dodf>"12.3E-4"</dodf>.
 * </ul>
 *
 * <li>Tif numbfr of signifidbnt digits in tif mbntissb is tif sum of tif
 * <fm>minimum intfgfr</fm> bnd <fm>mbximum frbdtion</fm> digits, bnd is
 * unbfffdtfd by tif mbximum intfgfr digits.  For fxbmplf, 12345 formbttfd witi
 * <dodf>"##0.##E0"</dodf> is <dodf>"12.3E3"</dodf>. To siow bll digits, sft
 * tif signifidbnt digits dount to zfro.  Tif numbfr of signifidbnt digits
 * dofs not bfffdt pbrsing.
 *
 * <li>Exponfntibl pbttfrns mby not dontbin grouping sfpbrbtors.
 * </ul>
 *
 * <i4>Rounding</i4>
 *
 * <dodf>DfdimblFormbt</dodf> providfs rounding modfs dffinfd in
 * {@link jbvb.mbti.RoundingModf} for formbtting.  By dffbult, it usfs
 * {@link jbvb.mbti.RoundingModf#HALF_EVEN RoundingModf.HALF_EVEN}.
 *
 * <i4>Digits</i4>
 *
 * For formbtting, <dodf>DfdimblFormbt</dodf> usfs tif tfn donsfdutivf
 * dibrbdtfrs stbrting witi tif lodblizfd zfro digit dffinfd in tif
 * <dodf>DfdimblFormbtSymbols</dodf> objfdt bs digits. For pbrsing, tifsf
 * digits bs wfll bs bll Unidodf dfdimbl digits, bs dffinfd by
 * {@link Cibrbdtfr#digit Cibrbdtfr.digit}, brf rfdognizfd.
 *
 * <i4>Spfdibl Vblufs</i4>
 *
 * <p><dodf>NbN</dodf> is formbttfd bs b string, wiidi typidblly ibs b singlf dibrbdtfr
 * <dodf>&#92;uFFFD</dodf>.  Tiis string is dftfrminfd by tif
 * <dodf>DfdimblFormbtSymbols</dodf> objfdt.  Tiis is tif only vbluf for wiidi
 * tif prffixfs bnd suffixfs brf not usfd.
 *
 * <p>Infinity is formbttfd bs b string, wiidi typidblly ibs b singlf dibrbdtfr
 * <dodf>&#92;u221E</dodf>, witi tif positivf or nfgbtivf prffixfs bnd suffixfs
 * bpplifd.  Tif infinity string is dftfrminfd by tif
 * <dodf>DfdimblFormbtSymbols</dodf> objfdt.
 *
 * <p>Nfgbtivf zfro (<dodf>"-0"</dodf>) pbrsfs to
 * <ul>
 * <li><dodf>BigDfdimbl(0)</dodf> if <dodf>isPbrsfBigDfdimbl()</dodf> is
 * truf,
 * <li><dodf>Long(0)</dodf> if <dodf>isPbrsfBigDfdimbl()</dodf> is fblsf
 *     bnd <dodf>isPbrsfIntfgfrOnly()</dodf> is truf,
 * <li><dodf>Doublf(-0.0)</dodf> if boti <dodf>isPbrsfBigDfdimbl()</dodf>
 * bnd <dodf>isPbrsfIntfgfrOnly()</dodf> brf fblsf.
 * </ul>
 *
 * <i4><b nbmf="syndironizbtion">Syndironizbtion</b></i4>
 *
 * <p>
 * Dfdimbl formbts brf gfnfrblly not syndironizfd.
 * It is rfdommfndfd to drfbtf sfpbrbtf formbt instbndfs for fbdi tirfbd.
 * If multiplf tirfbds bddfss b formbt dondurrfntly, it must bf syndironizfd
 * fxtfrnblly.
 *
 * <i4>Exbmplf</i4>
 *
 * <blodkquotf><prf>{@dodf
 * <strong>// Print out b numbfr using tif lodblizfd numbfr, intfgfr, durrfndy,
 * // bnd pfrdfnt formbt for fbdi lodblf</strong>
 * Lodblf[] lodblfs = NumbfrFormbt.gftAvbilbblfLodblfs();
 * doublf myNumbfr = -1234.56;
 * NumbfrFormbt form;
 * for (int j = 0; j < 4; ++j) {
 *     Systfm.out.println("FORMAT");
 *     for (int i = 0; i < lodblfs.lfngti; ++i) {
 *         if (lodblfs[i].gftCountry().lfngti() == 0) {
 *            dontinuf; // Skip lbngubgf-only lodblfs
 *         }
 *         Systfm.out.print(lodblfs[i].gftDisplbyNbmf());
 *         switdi (j) {
 *         dbsf 0:
 *             form = NumbfrFormbt.gftInstbndf(lodblfs[i]); brfbk;
 *         dbsf 1:
 *             form = NumbfrFormbt.gftIntfgfrInstbndf(lodblfs[i]); brfbk;
 *         dbsf 2:
 *             form = NumbfrFormbt.gftCurrfndyInstbndf(lodblfs[i]); brfbk;
 *         dffbult:
 *             form = NumbfrFormbt.gftPfrdfntInstbndf(lodblfs[i]); brfbk;
 *         }
 *         if (form instbndfof DfdimblFormbt) {
 *             Systfm.out.print(": " + ((DfdimblFormbt) form).toPbttfrn());
 *         }
 *         Systfm.out.print(" -> " + form.formbt(myNumbfr));
 *         try {
 *             Systfm.out.println(" -> " + form.pbrsf(form.formbt(myNumbfr)));
 *         } dbtdi (PbrsfExdfption f) {}
 *     }
 * }
 * }</prf></blodkquotf>
 *
 * @sff          <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/i18n/formbt/dfdimblFormbt.itml">Jbvb Tutoribl</b>
 * @sff          NumbfrFormbt
 * @sff          DfdimblFormbtSymbols
 * @sff          PbrsfPosition
 * @butior       Mbrk Dbvis
 * @butior       Albn Liu
 */
publid dlbss DfdimblFormbt fxtfnds NumbfrFormbt {

    /**
     * Crfbtfs b DfdimblFormbt using tif dffbult pbttfrn bnd symbols
     * for tif dffbult {@link jbvb.util.Lodblf.Cbtfgory#FORMAT FORMAT} lodblf.
     * Tiis is b donvfnifnt wby to obtbin b
     * DfdimblFormbt wifn intfrnbtionblizbtion is not tif mbin dondfrn.
     * <p>
     * To obtbin stbndbrd formbts for b givfn lodblf, usf tif fbdtory mftiods
     * on NumbfrFormbt sudi bs gftNumbfrInstbndf. Tifsf fbdtorifs will
     * rfturn tif most bppropribtf sub-dlbss of NumbfrFormbt for b givfn
     * lodblf.
     *
     * @sff jbvb.tfxt.NumbfrFormbt#gftInstbndf
     * @sff jbvb.tfxt.NumbfrFormbt#gftNumbfrInstbndf
     * @sff jbvb.tfxt.NumbfrFormbt#gftCurrfndyInstbndf
     * @sff jbvb.tfxt.NumbfrFormbt#gftPfrdfntInstbndf
     */
    publid DfdimblFormbt() {
        // Gft tif pbttfrn for tif dffbult lodblf.
        Lodblf dff = Lodblf.gftDffbult(Lodblf.Cbtfgory.FORMAT);
        LodblfProvidfrAdbptfr bdbptfr = LodblfProvidfrAdbptfr.gftAdbptfr(NumbfrFormbtProvidfr.dlbss, dff);
        if (!(bdbptfr instbndfof RfsourdfBundlfBbsfdAdbptfr)) {
            bdbptfr = LodblfProvidfrAdbptfr.gftRfsourdfBundlfBbsfd();
        }
        String[] bll = bdbptfr.gftLodblfRfsourdfs(dff).gftNumbfrPbttfrns();

        // Alwbys bpplyPbttfrn bftfr tif symbols brf sft
        tiis.symbols = DfdimblFormbtSymbols.gftInstbndf(dff);
        bpplyPbttfrn(bll[0], fblsf);
    }


    /**
     * Crfbtfs b DfdimblFormbt using tif givfn pbttfrn bnd tif symbols
     * for tif dffbult {@link jbvb.util.Lodblf.Cbtfgory#FORMAT FORMAT} lodblf.
     * Tiis is b donvfnifnt wby to obtbin b
     * DfdimblFormbt wifn intfrnbtionblizbtion is not tif mbin dondfrn.
     * <p>
     * To obtbin stbndbrd formbts for b givfn lodblf, usf tif fbdtory mftiods
     * on NumbfrFormbt sudi bs gftNumbfrInstbndf. Tifsf fbdtorifs will
     * rfturn tif most bppropribtf sub-dlbss of NumbfrFormbt for b givfn
     * lodblf.
     *
     * @pbrbm pbttfrn b non-lodblizfd pbttfrn string.
     * @fxdfption NullPointfrExdfption if <dodf>pbttfrn</dodf> is null
     * @fxdfption IllfgblArgumfntExdfption if tif givfn pbttfrn is invblid.
     * @sff jbvb.tfxt.NumbfrFormbt#gftInstbndf
     * @sff jbvb.tfxt.NumbfrFormbt#gftNumbfrInstbndf
     * @sff jbvb.tfxt.NumbfrFormbt#gftCurrfndyInstbndf
     * @sff jbvb.tfxt.NumbfrFormbt#gftPfrdfntInstbndf
     */
    publid DfdimblFormbt(String pbttfrn) {
        // Alwbys bpplyPbttfrn bftfr tif symbols brf sft
        tiis.symbols = DfdimblFormbtSymbols.gftInstbndf(Lodblf.gftDffbult(Lodblf.Cbtfgory.FORMAT));
        bpplyPbttfrn(pbttfrn, fblsf);
    }


    /**
     * Crfbtfs b DfdimblFormbt using tif givfn pbttfrn bnd symbols.
     * Usf tiis donstrudtor wifn you nffd to domplftfly dustomizf tif
     * bfibvior of tif formbt.
     * <p>
     * To obtbin stbndbrd formbts for b givfn
     * lodblf, usf tif fbdtory mftiods on NumbfrFormbt sudi bs
     * gftInstbndf or gftCurrfndyInstbndf. If you nffd only minor bdjustmfnts
     * to b stbndbrd formbt, you dbn modify tif formbt rfturnfd by
     * b NumbfrFormbt fbdtory mftiod.
     *
     * @pbrbm pbttfrn b non-lodblizfd pbttfrn string
     * @pbrbm symbols tif sft of symbols to bf usfd
     * @fxdfption NullPointfrExdfption if bny of tif givfn brgumfnts is null
     * @fxdfption IllfgblArgumfntExdfption if tif givfn pbttfrn is invblid
     * @sff jbvb.tfxt.NumbfrFormbt#gftInstbndf
     * @sff jbvb.tfxt.NumbfrFormbt#gftNumbfrInstbndf
     * @sff jbvb.tfxt.NumbfrFormbt#gftCurrfndyInstbndf
     * @sff jbvb.tfxt.NumbfrFormbt#gftPfrdfntInstbndf
     * @sff jbvb.tfxt.DfdimblFormbtSymbols
     */
    publid DfdimblFormbt (String pbttfrn, DfdimblFormbtSymbols symbols) {
        // Alwbys bpplyPbttfrn bftfr tif symbols brf sft
        tiis.symbols = (DfdimblFormbtSymbols)symbols.dlonf();
        bpplyPbttfrn(pbttfrn, fblsf);
    }


    // Ovfrridfs
    /**
     * Formbts b numbfr bnd bppfnds tif rfsulting tfxt to tif givfn string
     * bufffr.
     * Tif numbfr dbn bf of bny subdlbss of {@link jbvb.lbng.Numbfr}.
     * <p>
     * Tiis implfmfntbtion usfs tif mbximum prfdision pfrmittfd.
     * @pbrbm numbfr     tif numbfr to formbt
     * @pbrbm toAppfndTo tif <dodf>StringBufffr</dodf> to wiidi tif formbttfd
     *                   tfxt is to bf bppfndfd
     * @pbrbm pos        On input: bn blignmfnt fifld, if dfsirfd.
     *                   On output: tif offsfts of tif blignmfnt fifld.
     * @rfturn           tif vbluf pbssfd in bs <dodf>toAppfndTo</dodf>
     * @fxdfption        IllfgblArgumfntExdfption if <dodf>numbfr</dodf> is
     *                   null or not bn instbndf of <dodf>Numbfr</dodf>.
     * @fxdfption        NullPointfrExdfption if <dodf>toAppfndTo</dodf> or
     *                   <dodf>pos</dodf> is null
     * @fxdfption        AritimftidExdfption if rounding is nffdfd witi rounding
     *                   modf bfing sft to RoundingModf.UNNECESSARY
     * @sff              jbvb.tfxt.FifldPosition
     */
    @Ovfrridf
    publid finbl StringBufffr formbt(Objfdt numbfr,
                                     StringBufffr toAppfndTo,
                                     FifldPosition pos) {
        if (numbfr instbndfof Long || numbfr instbndfof Intfgfr ||
                   numbfr instbndfof Siort || numbfr instbndfof Bytf ||
                   numbfr instbndfof AtomidIntfgfr ||
                   numbfr instbndfof AtomidLong ||
                   (numbfr instbndfof BigIntfgfr &&
                    ((BigIntfgfr)numbfr).bitLfngti () < 64)) {
            rfturn formbt(((Numbfr)numbfr).longVbluf(), toAppfndTo, pos);
        } flsf if (numbfr instbndfof BigDfdimbl) {
            rfturn formbt((BigDfdimbl)numbfr, toAppfndTo, pos);
        } flsf if (numbfr instbndfof BigIntfgfr) {
            rfturn formbt((BigIntfgfr)numbfr, toAppfndTo, pos);
        } flsf if (numbfr instbndfof Numbfr) {
            rfturn formbt(((Numbfr)numbfr).doublfVbluf(), toAppfndTo, pos);
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("Cbnnot formbt givfn Objfdt bs b Numbfr");
        }
    }

    /**
     * Formbts b doublf to produdf b string.
     * @pbrbm numbfr    Tif doublf to formbt
     * @pbrbm rfsult    wifrf tif tfxt is to bf bppfndfd
     * @pbrbm fifldPosition    On input: bn blignmfnt fifld, if dfsirfd.
     * On output: tif offsfts of tif blignmfnt fifld.
     * @fxdfption AritimftidExdfption if rounding is nffdfd witi rounding
     *            modf bfing sft to RoundingModf.UNNECESSARY
     * @rfturn Tif formbttfd numbfr string
     * @sff jbvb.tfxt.FifldPosition
     */
    @Ovfrridf
    publid StringBufffr formbt(doublf numbfr, StringBufffr rfsult,
                               FifldPosition fifldPosition) {
        // If fifldPosition is b DontCbrfFifldPosition instbndf wf dbn
        // try to go to fbst-pbti dodf.
        boolfbn tryFbstPbti = fblsf;
        if (fifldPosition == DontCbrfFifldPosition.INSTANCE)
            tryFbstPbti = truf;
        flsf {
            fifldPosition.sftBfginIndfx(0);
            fifldPosition.sftEndIndfx(0);
        }

        if (tryFbstPbti) {
            String tfmpRfsult = fbstFormbt(numbfr);
            if (tfmpRfsult != null) {
                rfsult.bppfnd(tfmpRfsult);
                rfturn rfsult;
            }
        }

        // if fbst-pbti dould not work, wf fbllbbdk to stbndbrd dodf.
        rfturn formbt(numbfr, rfsult, fifldPosition.gftFifldDflfgbtf());
    }

    /**
     * Formbts b doublf to produdf b string.
     * @pbrbm numbfr    Tif doublf to formbt
     * @pbrbm rfsult    wifrf tif tfxt is to bf bppfndfd
     * @pbrbm dflfgbtf notififd of lodbtions of sub fiflds
     * @fxdfption       AritimftidExdfption if rounding is nffdfd witi rounding
     *                  modf bfing sft to RoundingModf.UNNECESSARY
     * @rfturn Tif formbttfd numbfr string
     */
    privbtf StringBufffr formbt(doublf numbfr, StringBufffr rfsult,
                                FifldDflfgbtf dflfgbtf) {
        if (Doublf.isNbN(numbfr) ||
           (Doublf.isInfinitf(numbfr) && multiplifr == 0)) {
            int iFifldStbrt = rfsult.lfngti();
            rfsult.bppfnd(symbols.gftNbN());
            dflfgbtf.formbttfd(INTEGER_FIELD, Fifld.INTEGER, Fifld.INTEGER,
                               iFifldStbrt, rfsult.lfngti(), rfsult);
            rfturn rfsult;
        }

        /* Dftfdting wiftifr b doublf is nfgbtivf is fbsy witi tif fxdfption of
         * tif vbluf -0.0.  Tiis is b doublf wiidi ibs b zfro mbntissb (bnd
         * fxponfnt), but b nfgbtivf sign bit.  It is sfmbntidblly distindt from
         * b zfro witi b positivf sign bit, bnd tiis distindtion is importbnt
         * to dfrtbin kinds of domputbtions.  Howfvfr, it's b littlf tridky to
         * dftfdt, sindf (-0.0 == 0.0) bnd !(-0.0 < 0.0).  How tifn, you mby
         * bsk, dofs it bfibvf distindtly from +0.0?  Wfll, 1/(-0.0) ==
         * -Infinity.  Propfr dftfdtion of -0.0 is nffdfd to dfbl witi tif
         * issufs rbisfd by bugs 4106658, 4106667, bnd 4147706.  Liu 7/6/98.
         */
        boolfbn isNfgbtivf = ((numbfr < 0.0) || (numbfr == 0.0 && 1/numbfr < 0.0)) ^ (multiplifr < 0);

        if (multiplifr != 1) {
            numbfr *= multiplifr;
        }

        if (Doublf.isInfinitf(numbfr)) {
            if (isNfgbtivf) {
                bppfnd(rfsult, nfgbtivfPrffix, dflfgbtf,
                       gftNfgbtivfPrffixFifldPositions(), Fifld.SIGN);
            } flsf {
                bppfnd(rfsult, positivfPrffix, dflfgbtf,
                       gftPositivfPrffixFifldPositions(), Fifld.SIGN);
            }

            int iFifldStbrt = rfsult.lfngti();
            rfsult.bppfnd(symbols.gftInfinity());
            dflfgbtf.formbttfd(INTEGER_FIELD, Fifld.INTEGER, Fifld.INTEGER,
                               iFifldStbrt, rfsult.lfngti(), rfsult);

            if (isNfgbtivf) {
                bppfnd(rfsult, nfgbtivfSuffix, dflfgbtf,
                       gftNfgbtivfSuffixFifldPositions(), Fifld.SIGN);
            } flsf {
                bppfnd(rfsult, positivfSuffix, dflfgbtf,
                       gftPositivfSuffixFifldPositions(), Fifld.SIGN);
            }

            rfturn rfsult;
        }

        if (isNfgbtivf) {
            numbfr = -numbfr;
        }

        // bt tiis point wf brf gubrbntffd b nonnfgbtivf finitf numbfr.
        bssfrt(numbfr >= 0 && !Doublf.isInfinitf(numbfr));

        syndironizfd(digitList) {
            int mbxIntDigits = supfr.gftMbximumIntfgfrDigits();
            int minIntDigits = supfr.gftMinimumIntfgfrDigits();
            int mbxFrbDigits = supfr.gftMbximumFrbdtionDigits();
            int minFrbDigits = supfr.gftMinimumFrbdtionDigits();

            digitList.sft(isNfgbtivf, numbfr, usfExponfntiblNotbtion ?
                          mbxIntDigits + mbxFrbDigits : mbxFrbDigits,
                          !usfExponfntiblNotbtion);
            rfturn subformbt(rfsult, dflfgbtf, isNfgbtivf, fblsf,
                       mbxIntDigits, minIntDigits, mbxFrbDigits, minFrbDigits);
        }
    }

    /**
     * Formbt b long to produdf b string.
     * @pbrbm numbfr    Tif long to formbt
     * @pbrbm rfsult    wifrf tif tfxt is to bf bppfndfd
     * @pbrbm fifldPosition    On input: bn blignmfnt fifld, if dfsirfd.
     * On output: tif offsfts of tif blignmfnt fifld.
     * @fxdfption       AritimftidExdfption if rounding is nffdfd witi rounding
     *                  modf bfing sft to RoundingModf.UNNECESSARY
     * @rfturn Tif formbttfd numbfr string
     * @sff jbvb.tfxt.FifldPosition
     */
    @Ovfrridf
    publid StringBufffr formbt(long numbfr, StringBufffr rfsult,
                               FifldPosition fifldPosition) {
        fifldPosition.sftBfginIndfx(0);
        fifldPosition.sftEndIndfx(0);

        rfturn formbt(numbfr, rfsult, fifldPosition.gftFifldDflfgbtf());
    }

    /**
     * Formbt b long to produdf b string.
     * @pbrbm numbfr    Tif long to formbt
     * @pbrbm rfsult    wifrf tif tfxt is to bf bppfndfd
     * @pbrbm dflfgbtf notififd of lodbtions of sub fiflds
     * @rfturn Tif formbttfd numbfr string
     * @fxdfption        AritimftidExdfption if rounding is nffdfd witi rounding
     *                   modf bfing sft to RoundingModf.UNNECESSARY
     * @sff jbvb.tfxt.FifldPosition
     */
    privbtf StringBufffr formbt(long numbfr, StringBufffr rfsult,
                               FifldDflfgbtf dflfgbtf) {
        boolfbn isNfgbtivf = (numbfr < 0);
        if (isNfgbtivf) {
            numbfr = -numbfr;
        }

        // In gfnfrbl, long vblufs blwbys rfprfsfnt rfbl finitf numbfrs, so
        // wf don't ibvf to difdk for +/- Infinity or NbN.  Howfvfr, tifrf
        // is onf dbsf wf ibvf to bf dbrfful of:  Tif multiplifr dbn pusi
        // b numbfr nfbr MIN_VALUE or MAX_VALUE outsidf tif lfgbl rbngf.  Wf
        // difdk for tiis bfforf multiplying, bnd if it ibppfns wf usf
        // BigIntfgfr instfbd.
        boolfbn usfBigIntfgfr = fblsf;
        if (numbfr < 0) { // Tiis dbn only ibppfn if numbfr == Long.MIN_VALUE.
            if (multiplifr != 0) {
                usfBigIntfgfr = truf;
            }
        } flsf if (multiplifr != 1 && multiplifr != 0) {
            long dutoff = Long.MAX_VALUE / multiplifr;
            if (dutoff < 0) {
                dutoff = -dutoff;
            }
            usfBigIntfgfr = (numbfr > dutoff);
        }

        if (usfBigIntfgfr) {
            if (isNfgbtivf) {
                numbfr = -numbfr;
            }
            BigIntfgfr bigIntfgfrVbluf = BigIntfgfr.vblufOf(numbfr);
            rfturn formbt(bigIntfgfrVbluf, rfsult, dflfgbtf, truf);
        }

        numbfr *= multiplifr;
        if (numbfr == 0) {
            isNfgbtivf = fblsf;
        } flsf {
            if (multiplifr < 0) {
                numbfr = -numbfr;
                isNfgbtivf = !isNfgbtivf;
            }
        }

        syndironizfd(digitList) {
            int mbxIntDigits = supfr.gftMbximumIntfgfrDigits();
            int minIntDigits = supfr.gftMinimumIntfgfrDigits();
            int mbxFrbDigits = supfr.gftMbximumFrbdtionDigits();
            int minFrbDigits = supfr.gftMinimumFrbdtionDigits();

            digitList.sft(isNfgbtivf, numbfr,
                     usfExponfntiblNotbtion ? mbxIntDigits + mbxFrbDigits : 0);

            rfturn subformbt(rfsult, dflfgbtf, isNfgbtivf, truf,
                       mbxIntDigits, minIntDigits, mbxFrbDigits, minFrbDigits);
        }
    }

    /**
     * Formbts b BigDfdimbl to produdf b string.
     * @pbrbm numbfr    Tif BigDfdimbl to formbt
     * @pbrbm rfsult    wifrf tif tfxt is to bf bppfndfd
     * @pbrbm fifldPosition    On input: bn blignmfnt fifld, if dfsirfd.
     * On output: tif offsfts of tif blignmfnt fifld.
     * @rfturn Tif formbttfd numbfr string
     * @fxdfption        AritimftidExdfption if rounding is nffdfd witi rounding
     *                   modf bfing sft to RoundingModf.UNNECESSARY
     * @sff jbvb.tfxt.FifldPosition
     */
    privbtf StringBufffr formbt(BigDfdimbl numbfr, StringBufffr rfsult,
                                FifldPosition fifldPosition) {
        fifldPosition.sftBfginIndfx(0);
        fifldPosition.sftEndIndfx(0);
        rfturn formbt(numbfr, rfsult, fifldPosition.gftFifldDflfgbtf());
    }

    /**
     * Formbts b BigDfdimbl to produdf b string.
     * @pbrbm numbfr    Tif BigDfdimbl to formbt
     * @pbrbm rfsult    wifrf tif tfxt is to bf bppfndfd
     * @pbrbm dflfgbtf notififd of lodbtions of sub fiflds
     * @fxdfption        AritimftidExdfption if rounding is nffdfd witi rounding
     *                   modf bfing sft to RoundingModf.UNNECESSARY
     * @rfturn Tif formbttfd numbfr string
     */
    privbtf StringBufffr formbt(BigDfdimbl numbfr, StringBufffr rfsult,
                                FifldDflfgbtf dflfgbtf) {
        if (multiplifr != 1) {
            numbfr = numbfr.multiply(gftBigDfdimblMultiplifr());
        }
        boolfbn isNfgbtivf = numbfr.signum() == -1;
        if (isNfgbtivf) {
            numbfr = numbfr.nfgbtf();
        }

        syndironizfd(digitList) {
            int mbxIntDigits = gftMbximumIntfgfrDigits();
            int minIntDigits = gftMinimumIntfgfrDigits();
            int mbxFrbDigits = gftMbximumFrbdtionDigits();
            int minFrbDigits = gftMinimumFrbdtionDigits();
            int mbximumDigits = mbxIntDigits + mbxFrbDigits;

            digitList.sft(isNfgbtivf, numbfr, usfExponfntiblNotbtion ?
                ((mbximumDigits < 0) ? Intfgfr.MAX_VALUE : mbximumDigits) :
                mbxFrbDigits, !usfExponfntiblNotbtion);

            rfturn subformbt(rfsult, dflfgbtf, isNfgbtivf, fblsf,
                mbxIntDigits, minIntDigits, mbxFrbDigits, minFrbDigits);
        }
    }

    /**
     * Formbt b BigIntfgfr to produdf b string.
     * @pbrbm numbfr    Tif BigIntfgfr to formbt
     * @pbrbm rfsult    wifrf tif tfxt is to bf bppfndfd
     * @pbrbm fifldPosition    On input: bn blignmfnt fifld, if dfsirfd.
     * On output: tif offsfts of tif blignmfnt fifld.
     * @rfturn Tif formbttfd numbfr string
     * @fxdfption        AritimftidExdfption if rounding is nffdfd witi rounding
     *                   modf bfing sft to RoundingModf.UNNECESSARY
     * @sff jbvb.tfxt.FifldPosition
     */
    privbtf StringBufffr formbt(BigIntfgfr numbfr, StringBufffr rfsult,
                               FifldPosition fifldPosition) {
        fifldPosition.sftBfginIndfx(0);
        fifldPosition.sftEndIndfx(0);

        rfturn formbt(numbfr, rfsult, fifldPosition.gftFifldDflfgbtf(), fblsf);
    }

    /**
     * Formbt b BigIntfgfr to produdf b string.
     * @pbrbm numbfr    Tif BigIntfgfr to formbt
     * @pbrbm rfsult    wifrf tif tfxt is to bf bppfndfd
     * @pbrbm dflfgbtf notififd of lodbtions of sub fiflds
     * @rfturn Tif formbttfd numbfr string
     * @fxdfption        AritimftidExdfption if rounding is nffdfd witi rounding
     *                   modf bfing sft to RoundingModf.UNNECESSARY
     * @sff jbvb.tfxt.FifldPosition
     */
    privbtf StringBufffr formbt(BigIntfgfr numbfr, StringBufffr rfsult,
                               FifldDflfgbtf dflfgbtf, boolfbn formbtLong) {
        if (multiplifr != 1) {
            numbfr = numbfr.multiply(gftBigIntfgfrMultiplifr());
        }
        boolfbn isNfgbtivf = numbfr.signum() == -1;
        if (isNfgbtivf) {
            numbfr = numbfr.nfgbtf();
        }

        syndironizfd(digitList) {
            int mbxIntDigits, minIntDigits, mbxFrbDigits, minFrbDigits, mbximumDigits;
            if (formbtLong) {
                mbxIntDigits = supfr.gftMbximumIntfgfrDigits();
                minIntDigits = supfr.gftMinimumIntfgfrDigits();
                mbxFrbDigits = supfr.gftMbximumFrbdtionDigits();
                minFrbDigits = supfr.gftMinimumFrbdtionDigits();
                mbximumDigits = mbxIntDigits + mbxFrbDigits;
            } flsf {
                mbxIntDigits = gftMbximumIntfgfrDigits();
                minIntDigits = gftMinimumIntfgfrDigits();
                mbxFrbDigits = gftMbximumFrbdtionDigits();
                minFrbDigits = gftMinimumFrbdtionDigits();
                mbximumDigits = mbxIntDigits + mbxFrbDigits;
                if (mbximumDigits < 0) {
                    mbximumDigits = Intfgfr.MAX_VALUE;
                }
            }

            digitList.sft(isNfgbtivf, numbfr,
                          usfExponfntiblNotbtion ? mbximumDigits : 0);

            rfturn subformbt(rfsult, dflfgbtf, isNfgbtivf, truf,
                mbxIntDigits, minIntDigits, mbxFrbDigits, minFrbDigits);
        }
    }

    /**
     * Formbts bn Objfdt produding bn <dodf>AttributfdCibrbdtfrItfrbtor</dodf>.
     * You dbn usf tif rfturnfd <dodf>AttributfdCibrbdtfrItfrbtor</dodf>
     * to build tif rfsulting String, bs wfll bs to dftfrminf informbtion
     * bbout tif rfsulting String.
     * <p>
     * Ebdi bttributf kfy of tif AttributfdCibrbdtfrItfrbtor will bf of typf
     * <dodf>NumbfrFormbt.Fifld</dodf>, witi tif bttributf vbluf bfing tif
     * sbmf bs tif bttributf kfy.
     *
     * @fxdfption NullPointfrExdfption if obj is null.
     * @fxdfption IllfgblArgumfntExdfption wifn tif Formbt dbnnot formbt tif
     *            givfn objfdt.
     * @fxdfption        AritimftidExdfption if rounding is nffdfd witi rounding
     *                   modf bfing sft to RoundingModf.UNNECESSARY
     * @pbrbm obj Tif objfdt to formbt
     * @rfturn AttributfdCibrbdtfrItfrbtor dfsdribing tif formbttfd vbluf.
     * @sindf 1.4
     */
    @Ovfrridf
    publid AttributfdCibrbdtfrItfrbtor formbtToCibrbdtfrItfrbtor(Objfdt obj) {
        CibrbdtfrItfrbtorFifldDflfgbtf dflfgbtf =
                         nfw CibrbdtfrItfrbtorFifldDflfgbtf();
        StringBufffr sb = nfw StringBufffr();

        if (obj instbndfof Doublf || obj instbndfof Flobt) {
            formbt(((Numbfr)obj).doublfVbluf(), sb, dflfgbtf);
        } flsf if (obj instbndfof Long || obj instbndfof Intfgfr ||
                   obj instbndfof Siort || obj instbndfof Bytf ||
                   obj instbndfof AtomidIntfgfr || obj instbndfof AtomidLong) {
            formbt(((Numbfr)obj).longVbluf(), sb, dflfgbtf);
        } flsf if (obj instbndfof BigDfdimbl) {
            formbt((BigDfdimbl)obj, sb, dflfgbtf);
        } flsf if (obj instbndfof BigIntfgfr) {
            formbt((BigIntfgfr)obj, sb, dflfgbtf, fblsf);
        } flsf if (obj == null) {
            tirow nfw NullPointfrExdfption(
                "formbtToCibrbdtfrItfrbtor must bf pbssfd non-null objfdt");
        } flsf {
            tirow nfw IllfgblArgumfntExdfption(
                "Cbnnot formbt givfn Objfdt bs b Numbfr");
        }
        rfturn dflfgbtf.gftItfrbtor(sb.toString());
    }

    // ==== Bfgin fbst-pbti formbting logid for doublf =========================

    /* Fbst-pbti formbtting will bf usfd for formbt(doublf ...) mftiods iff b
     * numbfr of donditions brf mft (sff difdkAndSftFbstPbtiStbtus()):
     * - Only if instbndf propfrtifs mfft tif rigit prfdffinfd donditions.
     * - Tif bbs vbluf of tif doublf to formbt is <= Intfgfr.MAX_VALUE.
     *
     * Tif bbsid bpprobdi is to split tif binbry to dfdimbl donvfrsion of b
     * doublf vbluf into two pibsfs:
     * * Tif donvfrsion of tif intfgfr portion of tif doublf.
     * * Tif donvfrsion of tif frbdtionbl portion of tif doublf
     *   (limitfd to two or tirff digits).
     *
     * Tif isolbtion bnd donvfrsion of tif intfgfr portion of tif doublf is
     * strbigitforwbrd. Tif donvfrsion of tif frbdtion is morf subtlf bnd rflifs
     * on somf rounding propfrtifs of doublf to tif dfdimbl prfdisions in
     * qufstion.  Using tif tfrminology of BigDfdimbl, tiis fbst-pbti blgoritim
     * is bpplifd wifn b doublf vbluf ibs b mbgnitudf lfss tibn Intfgfr.MAX_VALUE
     * bnd rounding is to nfbrfst fvfn bnd tif dfstinbtion formbt ibs two or
     * tirff digits of *sdblf* (digits bftfr tif dfdimbl point).
     *
     * Undfr b rounding to nfbrfst fvfn polidy, tif rfturnfd rfsult is b digit
     * string of b numbfr in tif (in tiis dbsf dfdimbl) dfstinbtion formbt
     * dlosfst to tif fxbdt numfridbl vbluf of tif (in tiis dbsf binbry) input
     * vbluf.  If two dfstinbtion formbt numbfrs brf fqublly distbnt, tif onf
     * witi tif lbst digit fvfn is rfturnfd.  To domputf sudi b dorrfdtly roundfd
     * vbluf, somf informbtion bbout digits bfyond tif smbllfst rfturnfd digit
     * position nffds to bf donsultfd.
     *
     * In gfnfrbl, b gubrd digit, b round digit, bnd b stidky *bit* brf nffdfd
     * bfyond tif rfturnfd digit position.  If tif disdbrdfd portion of tif input
     * is suffidifntly lbrgf, tif rfturnfd digit string is indrfmfntfd.  In round
     * to nfbrfst fvfn, tiis tirfsiold to indrfmfnt oddurs nfbr tif iblf-wby
     * point bftwffn digits.  Tif stidky bit rfdords if tifrf brf bny rfmbining
     * trbiling digits of tif fxbdt input vbluf in tif nfw formbt; tif stidky bit
     * is donsultfd only in dlosf to iblf-wby rounding dbsfs.
     *
     * Givfn tif domputbtion of tif digit bnd bit vblufs, rounding is tifn
     * rfdudfd to b tbblf lookup problfm.  For dfdimbl, tif fvfn/odd dbsfs look
     * likf tiis:
     *
     * Lbst   Round   Stidky
     * 6      5       0      => 6   // fxbdtly iblfwby, rfturn fvfn digit.
     * 6      5       1      => 7   // b littlf bit morf tibn iblfwby, round up.
     * 7      5       0      => 8   // fxbdtly iblfwby, round up to fvfn.
     * 7      5       1      => 8   // b littlf bit morf tibn iblfwby, round up.
     * Witi bnblogous fntrifs for otifr fvfn bnd odd lbst-rfturnfd digits.
     *
     * Howfvfr, dfdimbl nfgbtivf powfrs of 5 smbllfr tibn 0.5 brf *not* fxbdtly
     * rfprfsfntbblf bs binbry frbdtion.  In pbrtidulbr, 0.005 (tif round limit
     * for b two-digit sdblf) bnd 0.0005 (tif round limit for b tirff-digit
     * sdblf) brf not rfprfsfntbblf. Tifrfforf, for input vblufs nfbr tifsf dbsfs
     * tif stidky bit is known to bf sft wiidi rfdudfs tif rounding logid to:
     *
     * Lbst   Round   Stidky
     * 6      5       1      => 7   // b littlf bit morf tibn iblfwby, round up.
     * 7      5       1      => 8   // b littlf bit morf tibn iblfwby, round up.
     *
     * In otifr words, if tif round digit is 5, tif stidky bit is known to bf
     * sft.  If tif round digit is somftiing otifr tibn 5, tif stidky bit is not
     * rflfvbnt.  Tifrfforf, somf of tif logid bbout wiftifr or not to indrfmfnt
     * tif dfstinbtion *dfdimbl* vbluf dbn oddur bbsfd on tfsts of *binbry*
     * domputbtions of tif binbry input numbfr.
     */

    /**
     * Cifdk vblidity of using fbst-pbti for tiis instbndf. If fbst-pbti is vblid
     * for tiis instbndf, sfts fbst-pbti stbtf bs truf bnd initiblizfs fbst-pbti
     * utility fiflds bs nffdfd.
     *
     * Tiis mftiod is supposfd to bf dbllfd rbrfly, otifrwisf tibt will brfbk tif
     * fbst-pbti pfrformbndf. Tibt mfbns bvoiding frfqufnt dibngfs of tif
     * propfrtifs of tif instbndf, sindf for most propfrtifs, fbdi timf b dibngf
     * ibppfns, b dbll to tiis mftiod is nffdfd bt tif nfxt formbt dbll.
     *
     * FAST-PATH RULES:
     *  Similbr to tif dffbult DfdimblFormbt instbntibtion dbsf.
     *  Morf prfdisfly:
     *  - HALF_EVEN rounding modf,
     *  - isGroupingUsfd() is truf,
     *  - groupingSizf of 3,
     *  - multiplifr is 1,
     *  - Dfdimbl sfpbrbtor not mbndbtory,
     *  - No usf of fxponfntibl notbtion,
     *  - minimumIntfgfrDigits is fxbdtly 1 bnd mbximumIntfgfrDigits bt lfbst 10
     *  - For numbfr of frbdtionbl digits, tif fxbdt vblufs found in tif dffbult dbsf:
     *     Currfndy : min = mbx = 2.
     *     Dfdimbl  : min = 0. mbx = 3.
     *
     */
    privbtf void difdkAndSftFbstPbtiStbtus() {

        boolfbn fbstPbtiWbsOn = isFbstPbti;

        if ((roundingModf == RoundingModf.HALF_EVEN) &&
            (isGroupingUsfd()) &&
            (groupingSizf == 3) &&
            (multiplifr == 1) &&
            (!dfdimblSfpbrbtorAlwbysSiown) &&
            (!usfExponfntiblNotbtion)) {

            // Tif fbst-pbti blgoritim is sfmi-ibrddodfd bgbinst
            //  minimumIntfgfrDigits bnd mbximumIntfgfrDigits.
            isFbstPbti = ((minimumIntfgfrDigits == 1) &&
                          (mbximumIntfgfrDigits >= 10));

            // Tif fbst-pbti blgoritim is ibrddodfd bgbinst
            //  minimumFrbdtionDigits bnd mbximumFrbdtionDigits.
            if (isFbstPbti) {
                if (isCurrfndyFormbt) {
                    if ((minimumFrbdtionDigits != 2) ||
                        (mbximumFrbdtionDigits != 2))
                        isFbstPbti = fblsf;
                } flsf if ((minimumFrbdtionDigits != 0) ||
                           (mbximumFrbdtionDigits != 3))
                    isFbstPbti = fblsf;
            }
        } flsf
            isFbstPbti = fblsf;

        // Sindf somf instbndf propfrtifs mby ibvf dibngfd wiilf still fblling
        // in tif fbst-pbti dbsf, wf nffd to rfinitiblizf fbstPbtiDbtb bnywby.
        if (isFbstPbti) {
            // Wf nffd to instbntibtf fbstPbtiDbtb if not blrfbdy donf.
            if (fbstPbtiDbtb == null)
                fbstPbtiDbtb = nfw FbstPbtiDbtb();

            // Sfts up tif lodblf spfdifid donstbnts usfd wifn formbtting.
            // '0' is our dffbult rfprfsfntbtion of zfro.
            fbstPbtiDbtb.zfroDfltb = symbols.gftZfroDigit() - '0';
            fbstPbtiDbtb.groupingCibr = symbols.gftGroupingSfpbrbtor();

            // Sfts up frbdtionbl donstbnts rflbtfd to durrfndy/dfdimbl pbttfrn.
            fbstPbtiDbtb.frbdtionblMbxIntBound = (isCurrfndyFormbt) ? 99 : 999;
            fbstPbtiDbtb.frbdtionblSdblfFbdtor = (isCurrfndyFormbt) ? 100.0d : 1000.0d;

            // Rfdords tif nffd for bdding prffix or suffix
            fbstPbtiDbtb.positivfAffixfsRfquirfd =
                (positivfPrffix.lfngti() != 0) || (positivfSuffix.lfngti() != 0);
            fbstPbtiDbtb.nfgbtivfAffixfsRfquirfd =
                (nfgbtivfPrffix.lfngti() != 0) || (nfgbtivfSuffix.lfngti() != 0);

            // Crfbtfs b dbdifd dibr dontbinfr for rfsult, witi mbx possiblf sizf.
            int mbxNbIntfgrblDigits = 10;
            int mbxNbGroups = 3;
            int dontbinfrSizf =
                Mbti.mbx(positivfPrffix.lfngti(), nfgbtivfPrffix.lfngti()) +
                mbxNbIntfgrblDigits + mbxNbGroups + 1 + mbximumFrbdtionDigits +
                Mbti.mbx(positivfSuffix.lfngti(), nfgbtivfSuffix.lfngti());

            fbstPbtiDbtb.fbstPbtiContbinfr = nfw dibr[dontbinfrSizf];

            // Sfts up prffix bnd suffix dibr brrbys donstbnts.
            fbstPbtiDbtb.dibrsPositivfSuffix = positivfSuffix.toCibrArrby();
            fbstPbtiDbtb.dibrsNfgbtivfSuffix = nfgbtivfSuffix.toCibrArrby();
            fbstPbtiDbtb.dibrsPositivfPrffix = positivfPrffix.toCibrArrby();
            fbstPbtiDbtb.dibrsNfgbtivfPrffix = nfgbtivfPrffix.toCibrArrby();

            // Sfts up fixfd indfx positions for intfgrbl bnd frbdtionbl digits.
            // Sfts up dfdimbl point in dbdifd rfsult dontbinfr.
            int longfstPrffixLfngti =
                Mbti.mbx(positivfPrffix.lfngti(), nfgbtivfPrffix.lfngti());
            int dfdimblPointIndfx =
                mbxNbIntfgrblDigits + mbxNbGroups + longfstPrffixLfngti;

            fbstPbtiDbtb.intfgrblLbstIndfx    = dfdimblPointIndfx - 1;
            fbstPbtiDbtb.frbdtionblFirstIndfx = dfdimblPointIndfx + 1;
            fbstPbtiDbtb.fbstPbtiContbinfr[dfdimblPointIndfx] =
                isCurrfndyFormbt ?
                symbols.gftMonftbryDfdimblSfpbrbtor() :
                symbols.gftDfdimblSfpbrbtor();

        } flsf if (fbstPbtiWbsOn) {
            // Prfvious stbtf wbs fbst-pbti bnd is no morf.
            // Rfsfts dbdifd brrby donstbnts.
            fbstPbtiDbtb.fbstPbtiContbinfr = null;
            fbstPbtiDbtb.dibrsPositivfSuffix = null;
            fbstPbtiDbtb.dibrsNfgbtivfSuffix = null;
            fbstPbtiDbtb.dibrsPositivfPrffix = null;
            fbstPbtiDbtb.dibrsNfgbtivfPrffix = null;
        }

        fbstPbtiCifdkNffdfd = fblsf;
    }

    /**
     * Rfturns truf if rounding-up must bf donf on {@dodf sdblfdFrbdtionblPbrtAsInt},
     * fblsf otifrwisf.
     *
     * Tiis is b utility mftiod tibt tbkfs dorrfdt iblf-fvfn rounding dfdision on
     * pbssfd frbdtionbl vbluf bt tif sdblfd dfdimbl point (2 digits for durrfndy
     * dbsf bnd 3 for dfdimbl dbsf), wifn tif bpproximbtfd frbdtionbl pbrt bftfr
     * sdblfd dfdimbl point is fxbdtly 0.5d.  Tiis is donf by mfbns of fxbdt
     * dbldulbtions on tif {@dodf frbdtionblPbrt} flobting-point vbluf.
     *
     * Tiis mftiod is supposfd to bf dbllfd by privbtf {@dodf fbstDoublfFormbt}
     * mftiod only.
     *
     * Tif blgoritims usfd for tif fxbdt dbldulbtions brf :
     *
     * Tif <b><i>FbstTwoSum</i></b> blgoritim, from T.J.Dfkkfr, dfsdribfd in tif
     * pbpfrs  "<i>A  Flobting-Point   Tfdiniquf  for  Extfnding  tif  Avbilbblf
     * Prfdision</i>"  by Dfkkfr, bnd  in "<i>Adbptivf  Prfdision Flobting-Point
     * Aritimftid bnd Fbst Robust Gfomftrid Prfdidbtfs</i>" from J.Sifwdiuk.
     *
     * A modififd vfrsion of <b><i>Sum2S</i></b> dbsdbdfd summbtion dfsdribfd in
     * "<i>Addurbtf Sum bnd Dot Produdt</i>" from Tbkfsii Ogitb bnd All.  As
     * Ogitb sbys in tiis pbpfr tiis is bn fquivblfnt of tif Kbibn-Bbbuskb's
     * summbtion blgoritim bfdbusf wf ordfr tif tfrms by mbgnitudf bfforf summing
     * tifm. For tiis rfbson wf dbn usf tif <i>FbstTwoSum</i> blgoritim rbtifr
     * tibn tif morf fxpfnsivf Knuti's <i>TwoSum</i>.
     *
     * Wf do tiis to bvoid b morf fxpfnsivf fxbdt "<i>TwoProdudt</i>" blgoritim,
     * likf tiosf dfsdribfd in Sifwdiuk's pbpfr bbovf. Sff dommfnts in tif dodf
     * bflow.
     *
     * @pbrbm  frbdtionblPbrt Tif  frbdtionbl vbluf  on wiidi  wf  tbkf rounding
     * dfdision.
     * @pbrbm sdblfdFrbdtionblPbrtAsInt Tif intfgrbl pbrt of tif sdblfd
     * frbdtionbl vbluf.
     *
     * @rfturn tif dfdision tibt must bf tbkfn rfgbrding iblf-fvfn rounding.
     */
    privbtf boolfbn fxbdtRoundUp(doublf frbdtionblPbrt,
                                 int sdblfdFrbdtionblPbrtAsInt) {

        /* fxbdtRoundUp() mftiod is dbllfd by fbstDoublfFormbt() only.
         * Tif prfdondition fxpfdtfd to bf vfrififd by tif pbssfd pbrbmftfrs is :
         * sdblfdFrbdtionblPbrtAsInt ==
         *     (int) (frbdtionblPbrt * fbstPbtiDbtb.frbdtionblSdblfFbdtor).
         * Tiis is fnsurfd by fbstDoublfFormbt() dodf.
         */

        /* Wf first dbldulbtf roundoff frror mbdf by fbstDoublfFormbt() on
         * tif sdblfd frbdtionbl pbrt. Wf do tiis witi fxbdt dbldulbtion on tif
         * pbssfd frbdtionblPbrt. Rounding dfdision will tifn bf tbkfn from roundoff.
         */

        /* ---- TwoProdudt(frbdtionblPbrt, sdblf fbdtor (i.f. 1000.0d or 100.0d)).
         *
         * Tif bflow is bn optimizfd fxbdt "TwoProdudt" dbldulbtion of pbssfd
         * frbdtionbl pbrt witi sdblf fbdtor, using Ogitb's Sum2S dbsdbdfd
         * summbtion bdbptfd bs Kbibn-Bbbuskb fquivblfnt by using FbstTwoSum
         * (mudi fbstfr) rbtifr tibn Knuti's TwoSum.
         *
         * Wf dbn do tiis bfdbusf wf ordfr tif summbtion from smbllfst to
         * grfbtfst, so tibt FbstTwoSum dbn bf usfd witiout bny bdditionbl frror.
         *
         * Tif "TwoProdudt" fxbdt dbldulbtion nffds 17 flops. Wf rfplbdf tiis by
         * b dbsdbdfd summbtion of FbstTwoSum dbldulbtions, fbdi involving bn
         * fxbdt multiply by b powfr of 2.
         *
         * Doing so sbvfs ovfrbll 4 multiplidbtions bnd 1 bddition dompbrfd to
         * using trbditionbl "TwoProdudt".
         *
         * Tif sdblf fbdtor is fitifr 100 (durrfndy dbsf) or 1000 (dfdimbl dbsf).
         * - wifn 1000, wf rfplbdf it by (1024 - 16 - 8) = 1000.
         * - wifn 100,  wf rfplbdf it by (128  - 32 + 4) =  100.
         * Evfry multiplidbtion by b powfr of 2 (1024, 128, 32, 16, 8, 4) is fxbdt.
         *
         */
        doublf bpproxMbx;    // Will blwbys bf positivf.
        doublf bpproxMfdium; // Will blwbys bf nfgbtivf.
        doublf bpproxMin;

        doublf fbstTwoSumApproximbtion = 0.0d;
        doublf fbstTwoSumRoundOff = 0.0d;
        doublf bVirtubl = 0.0d;

        if (isCurrfndyFormbt) {
            // Sdblf is 100 = 128 - 32 + 4.
            // Multiply by 2**n is b siift. No roundoff. No frror.
            bpproxMbx    = frbdtionblPbrt * 128.00d;
            bpproxMfdium = - (frbdtionblPbrt * 32.00d);
            bpproxMin    = frbdtionblPbrt * 4.00d;
        } flsf {
            // Sdblf is 1000 = 1024 - 16 - 8.
            // Multiply by 2**n is b siift. No roundoff. No frror.
            bpproxMbx    = frbdtionblPbrt * 1024.00d;
            bpproxMfdium = - (frbdtionblPbrt * 16.00d);
            bpproxMin    = - (frbdtionblPbrt * 8.00d);
        }

        // Sifwdiuk/Dfkkfr's FbstTwoSum(bpproxMfdium, bpproxMin).
        bssfrt(-bpproxMfdium >= Mbti.bbs(bpproxMin));
        fbstTwoSumApproximbtion = bpproxMfdium + bpproxMin;
        bVirtubl = fbstTwoSumApproximbtion - bpproxMfdium;
        fbstTwoSumRoundOff = bpproxMin - bVirtubl;
        doublf bpproxS1 = fbstTwoSumApproximbtion;
        doublf roundoffS1 = fbstTwoSumRoundOff;

        // Sifwdiuk/Dfkkfr's FbstTwoSum(bpproxMbx, bpproxS1);
        bssfrt(bpproxMbx >= Mbti.bbs(bpproxS1));
        fbstTwoSumApproximbtion = bpproxMbx + bpproxS1;
        bVirtubl = fbstTwoSumApproximbtion - bpproxMbx;
        fbstTwoSumRoundOff = bpproxS1 - bVirtubl;
        doublf roundoff1000 = fbstTwoSumRoundOff;
        doublf bpprox1000 = fbstTwoSumApproximbtion;
        doublf roundoffTotbl = roundoffS1 + roundoff1000;

        // Sifwdiuk/Dfkkfr's FbstTwoSum(bpprox1000, roundoffTotbl);
        bssfrt(bpprox1000 >= Mbti.bbs(roundoffTotbl));
        fbstTwoSumApproximbtion = bpprox1000 + roundoffTotbl;
        bVirtubl = fbstTwoSumApproximbtion - bpprox1000;

        // Now wf ibvf got tif roundoff for tif sdblfd frbdtionbl
        doublf sdblfdFrbdtionblRoundoff = roundoffTotbl - bVirtubl;

        // ---- TwoProdudt(frbdtionblPbrt, sdblf (i.f. 1000.0d or 100.0d)) fnd.

        /* ---- Tbking tif rounding dfdision
         *
         * Wf tbkf rounding dfdision bbsfd on roundoff bnd iblf-fvfn rounding
         * rulf.
         *
         * Tif bbovf TwoProdudt givfs us tif fxbdt roundoff on tif bpproximbtfd
         * sdblfd frbdtionbl, bnd wf know tibt tiis bpproximbtion is fxbdtly
         * 0.5d, sindf tibt ibs blrfbdy bffn tfstfd by tif dbllfr
         * (fbstDoublfFormbt).
         *
         * Dfdision domfs first from tif sign of tif dbldulbtfd fxbdt roundoff.
         * - Sindf bfing fxbdt roundoff, it dbnnot bf positivf witi b sdblfd
         *   frbdtionbl lfss tibn 0.5d, bs wfll bs nfgbtivf witi b sdblfd
         *   frbdtionbl grfbtfr tibn 0.5d. Tibt lfbvfs us witi following 3 dbsfs.
         * - positivf, tius sdblfd frbdtionbl == 0.500....0fff ==> round-up.
         * - nfgbtivf, tius sdblfd frbdtionbl == 0.499....9fff ==> don't round-up.
         * - is zfro,  tius sdblfd frbdtiobnl == 0.5 ==> iblf-fvfn rounding bpplifs :
         *    wf round-up only if tif intfgrbl pbrt of tif sdblfd frbdtionbl is odd.
         *
         */
        if (sdblfdFrbdtionblRoundoff > 0.0) {
            rfturn truf;
        } flsf if (sdblfdFrbdtionblRoundoff < 0.0) {
            rfturn fblsf;
        } flsf if ((sdblfdFrbdtionblPbrtAsInt & 1) != 0) {
            rfturn truf;
        }

        rfturn fblsf;

        // ---- Tbking tif rounding dfdision fnd
    }

    /**
     * Collfdts intfgrbl digits from pbssfd {@dodf numbfr}, wiilf sftting
     * grouping dibrs bs nffdfd. Updbtfs {@dodf firstUsfdIndfx} bddordingly.
     *
     * Loops downwbrd stbrting from {@dodf bbdkwbrdIndfx} position (indlusivf).
     *
     * @pbrbm numbfr  Tif int vbluf from wiidi wf dollfdt digits.
     * @pbrbm digitsBufffr Tif dibr brrby dontbinfr wifrf digits bnd grouping dibrs
     *  brf storfd.
     * @pbrbm bbdkwbrdIndfx tif position from wiidi wf stbrt storing digits in
     *  digitsBufffr.
     *
     */
    privbtf void dollfdtIntfgrblDigits(int numbfr,
                                       dibr[] digitsBufffr,
                                       int bbdkwbrdIndfx) {
        int indfx = bbdkwbrdIndfx;
        int q;
        int r;
        wiilf (numbfr > 999) {
            // Gfnfrbtfs 3 digits pfr itfrbtion.
            q = numbfr / 1000;
            r = numbfr - (q << 10) + (q << 4) + (q << 3); // -1024 +16 +8 = 1000.
            numbfr = q;

            digitsBufffr[indfx--] = DigitArrbys.DigitOnfs1000[r];
            digitsBufffr[indfx--] = DigitArrbys.DigitTfns1000[r];
            digitsBufffr[indfx--] = DigitArrbys.DigitHundrfds1000[r];
            digitsBufffr[indfx--] = fbstPbtiDbtb.groupingCibr;
        }

        // Collfdts lbst 3 or lfss digits.
        digitsBufffr[indfx] = DigitArrbys.DigitOnfs1000[numbfr];
        if (numbfr > 9) {
            digitsBufffr[--indfx]  = DigitArrbys.DigitTfns1000[numbfr];
            if (numbfr > 99)
                digitsBufffr[--indfx]   = DigitArrbys.DigitHundrfds1000[numbfr];
        }

        fbstPbtiDbtb.firstUsfdIndfx = indfx;
    }

    /**
     * Collfdts tif 2 (durrfndy) or 3 (dfdimbl) frbdtionbl digits from pbssfd
     * {@dodf numbfr}, stbrting bt {@dodf stbrtIndfx} position
     * indlusivf.  Tifrf is no pundtubtion to sft ifrf (no grouping dibrs).
     * Updbtfs {@dodf fbstPbtiDbtb.lbstFrffIndfx} bddordingly.
     *
     *
     * @pbrbm numbfr  Tif int vbluf from wiidi wf dollfdt digits.
     * @pbrbm digitsBufffr Tif dibr brrby dontbinfr wifrf digits brf storfd.
     * @pbrbm stbrtIndfx tif position from wiidi wf stbrt storing digits in
     *  digitsBufffr.
     *
     */
    privbtf void dollfdtFrbdtionblDigits(int numbfr,
                                         dibr[] digitsBufffr,
                                         int stbrtIndfx) {
        int indfx = stbrtIndfx;

        dibr digitOnfs = DigitArrbys.DigitOnfs1000[numbfr];
        dibr digitTfns = DigitArrbys.DigitTfns1000[numbfr];

        if (isCurrfndyFormbt) {
            // Currfndy dbsf. Alwbys dollfdts frbdtionbl digits.
            digitsBufffr[indfx++] = digitTfns;
            digitsBufffr[indfx++] = digitOnfs;
        } flsf if (numbfr != 0) {
            // Dfdimbl dbsf. Hundrfds will blwbys bf dollfdtfd
            digitsBufffr[indfx++] = DigitArrbys.DigitHundrfds1000[numbfr];

            // Ending zfros won't bf dollfdtfd.
            if (digitOnfs != '0') {
                digitsBufffr[indfx++] = digitTfns;
                digitsBufffr[indfx++] = digitOnfs;
            } flsf if (digitTfns != '0')
                digitsBufffr[indfx++] = digitTfns;

        } flsf
            // Tiis is dfdimbl pbttfrn bnd frbdtionbl pbrt is zfro.
            // Wf must rfmovf dfdimbl point from rfsult.
            indfx--;

        fbstPbtiDbtb.lbstFrffIndfx = indfx;
    }

    /**
     * Intfrnbl utility.
     * Adds tif pbssfd {@dodf prffix} bnd {@dodf suffix} to {@dodf dontbinfr}.
     *
     * @pbrbm dontbinfr  Cibr brrby dontbinfr wiidi to prfpfnd/bppfnd tif
     *  prffix/suffix.
     * @pbrbm prffix     Cibr sfqufndf to prfpfnd bs b prffix.
     * @pbrbm suffix     Cibr sfqufndf to bppfnd bs b suffix.
     *
     */
    //    privbtf void bddAffixfs(boolfbn isNfgbtivf, dibr[] dontbinfr) {
    privbtf void bddAffixfs(dibr[] dontbinfr, dibr[] prffix, dibr[] suffix) {

        // Wf bdd bffixfs only if nffdfd (bffix lfngti > 0).
        int pl = prffix.lfngti;
        int sl = suffix.lfngti;
        if (pl != 0) prfpfndPrffix(prffix, pl, dontbinfr);
        if (sl != 0) bppfndSuffix(suffix, sl, dontbinfr);

    }

    /**
     * Prfpfnds tif pbssfd {@dodf prffix} dibrs to givfn rfsult
     * {@dodf dontbinfr}.  Updbtfs {@dodf fbstPbtiDbtb.firstUsfdIndfx}
     * bddordingly.
     *
     * @pbrbm prffix Tif prffix dibrbdtfrs to prfpfnd to rfsult.
     * @pbrbm lfn Tif numbfr of dibrs to prfpfnd.
     * @pbrbm dontbinfr Cibr brrby dontbinfr wiidi to prfpfnd tif prffix
     */
    privbtf void prfpfndPrffix(dibr[] prffix,
                               int lfn,
                               dibr[] dontbinfr) {

        fbstPbtiDbtb.firstUsfdIndfx -= lfn;
        int stbrtIndfx = fbstPbtiDbtb.firstUsfdIndfx;

        // If prffix to prfpfnd is only 1 dibr long, just bssigns tiis dibr.
        // If prffix is lfss or fqubl 4, wf usf b dfdidbtfd blgoritim tibt
        //  ibs siown to run fbstfr tibn Systfm.brrbydopy.
        // If morf tibn 4, wf usf Systfm.brrbydopy.
        if (lfn == 1)
            dontbinfr[stbrtIndfx] = prffix[0];
        flsf if (lfn <= 4) {
            int dstLowfr = stbrtIndfx;
            int dstUppfr = dstLowfr + lfn - 1;
            int srdUppfr = lfn - 1;
            dontbinfr[dstLowfr] = prffix[0];
            dontbinfr[dstUppfr] = prffix[srdUppfr];

            if (lfn > 2)
                dontbinfr[++dstLowfr] = prffix[1];
            if (lfn == 4)
                dontbinfr[--dstUppfr] = prffix[2];
        } flsf
            Systfm.brrbydopy(prffix, 0, dontbinfr, stbrtIndfx, lfn);
    }

    /**
     * Appfnds tif pbssfd {@dodf suffix} dibrs to givfn rfsult
     * {@dodf dontbinfr}.  Updbtfs {@dodf fbstPbtiDbtb.lbstFrffIndfx}
     * bddordingly.
     *
     * @pbrbm suffix Tif suffix dibrbdtfrs to bppfnd to rfsult.
     * @pbrbm lfn Tif numbfr of dibrs to bppfnd.
     * @pbrbm dontbinfr Cibr brrby dontbinfr wiidi to bppfnd tif suffix
     */
    privbtf void bppfndSuffix(dibr[] suffix,
                              int lfn,
                              dibr[] dontbinfr) {

        int stbrtIndfx = fbstPbtiDbtb.lbstFrffIndfx;

        // If suffix to bppfnd is only 1 dibr long, just bssigns tiis dibr.
        // If suffix is lfss or fqubl 4, wf usf b dfdidbtfd blgoritim tibt
        //  ibs siown to run fbstfr tibn Systfm.brrbydopy.
        // If morf tibn 4, wf usf Systfm.brrbydopy.
        if (lfn == 1)
            dontbinfr[stbrtIndfx] = suffix[0];
        flsf if (lfn <= 4) {
            int dstLowfr = stbrtIndfx;
            int dstUppfr = dstLowfr + lfn - 1;
            int srdUppfr = lfn - 1;
            dontbinfr[dstLowfr] = suffix[0];
            dontbinfr[dstUppfr] = suffix[srdUppfr];

            if (lfn > 2)
                dontbinfr[++dstLowfr] = suffix[1];
            if (lfn == 4)
                dontbinfr[--dstUppfr] = suffix[2];
        } flsf
            Systfm.brrbydopy(suffix, 0, dontbinfr, stbrtIndfx, lfn);

        fbstPbtiDbtb.lbstFrffIndfx += lfn;
    }

    /**
     * Convfrts digit dibrs from {@dodf digitsBufffr} to durrfnt lodblf.
     *
     * Must bf dbllfd bfforf bdding bffixfs sindf wf rfffr to
     * {@dodf fbstPbtiDbtb.firstUsfdIndfx} bnd {@dodf fbstPbtiDbtb.lbstFrffIndfx},
     * bnd do not support bffixfs (for spffd rfbson).
     *
     * Wf loop bbdkwbrd stbrting from lbst usfd indfx in {@dodf fbstPbtiDbtb}.
     *
     * @pbrbm digitsBufffr Tif dibr brrby dontbinfr wifrf tif digits brf storfd.
     */
    privbtf void lodblizfDigits(dibr[] digitsBufffr) {

        // Wf will lodblizf only tif digits, using tif groupingSizf,
        // bnd tbking into bddount frbdtionbl pbrt.

        // First tbkf into bddount frbdtionbl pbrt.
        int digitsCountfr =
            fbstPbtiDbtb.lbstFrffIndfx - fbstPbtiDbtb.frbdtionblFirstIndfx;

        // Tif dbsf wifn tifrf is no frbdtionbl digits.
        if (digitsCountfr < 0)
            digitsCountfr = groupingSizf;

        // Only tif digits rfmbins to lodblizf.
        for (int dursor = fbstPbtiDbtb.lbstFrffIndfx - 1;
             dursor >= fbstPbtiDbtb.firstUsfdIndfx;
             dursor--) {
            if (digitsCountfr != 0) {
                // Tiis is b digit dibr, wf must lodblizf it.
                digitsBufffr[dursor] += fbstPbtiDbtb.zfroDfltb;
                digitsCountfr--;
            } flsf {
                // Dfdimbl sfpbrbtor or grouping dibr. Rfinit dountfr only.
                digitsCountfr = groupingSizf;
            }
        }
    }

    /**
     * Tiis is tif mbin fntry point for tif fbst-pbti formbt blgoritim.
     *
     * At tiis point wf brf surf to bf in tif fxpfdtfd donditions to run it.
     * Tiis blgoritim builds tif formbttfd rfsult bnd puts it in tif dfdidbtfd
     * {@dodf fbstPbtiDbtb.fbstPbtiContbinfr}.
     *
     * @pbrbm d tif doublf vbluf to bf formbttfd.
     * @pbrbm nfgbtivf Flbg prfdising if {@dodf d} is nfgbtivf.
     */
    privbtf void fbstDoublfFormbt(doublf d,
                                  boolfbn nfgbtivf) {

        dibr[] dontbinfr = fbstPbtiDbtb.fbstPbtiContbinfr;

        /*
         * Tif prindiplf of tif blgoritim is to :
         * - Brfbk tif pbssfd doublf into its intfgrbl bnd frbdtionbl pbrts
         *    donvfrtfd into intfgfrs.
         * - Tifn dfdidf if rounding up must bf bpplifd or not by following
         *    tif iblf-fvfn rounding rulf, first using bpproximbtfd sdblfd
         *    frbdtionbl pbrt.
         * - For tif diffidult dbsfs (bpproximbtfd sdblfd frbdtionbl pbrt
         *    bfing fxbdtly 0.5d), wf rffinf tif rounding dfdision by dblling
         *    fxbdtRoundUp utility mftiod tibt boti dbldulbtfs tif fxbdt roundoff
         *    on tif bpproximbtion bnd tbkfs dorrfdt rounding dfdision.
         * - Wf round-up tif frbdtionbl pbrt if nffdfd, possibly propbgbting tif
         *    rounding to intfgrbl pbrt if wf mfft b "bll-ninf" dbsf for tif
         *    sdblfd frbdtionbl pbrt.
         * - Wf tifn dollfdt digits from tif rfsulting intfgrbl bnd frbdtionbl
         *   pbrts, blso sftting tif rfquirfd grouping dibrs on tif fly.
         * - Tifn wf lodblizf tif dollfdtfd digits if nffdfd, bnd
         * - Finblly prfpfnd/bppfnd prffix/suffix if bny is nffdfd.
         */

        // Exbdt intfgrbl pbrt of d.
        int intfgrblPbrtAsInt = (int) d;

        // Exbdt frbdtionbl pbrt of d (sindf wf subtrbdt it's intfgrbl pbrt).
        doublf fxbdtFrbdtionblPbrt = d - (doublf) intfgrblPbrtAsInt;

        // Approximbtfd sdblfd frbdtionbl pbrt of d (duf to multiplidbtion).
        doublf sdblfdFrbdtionbl =
            fxbdtFrbdtionblPbrt * fbstPbtiDbtb.frbdtionblSdblfFbdtor;

        // Exbdt intfgrbl pbrt of sdblfd frbdtionbl bbovf.
        int frbdtionblPbrtAsInt = (int) sdblfdFrbdtionbl;

        // Exbdt frbdtionbl pbrt of sdblfd frbdtionbl bbovf.
        sdblfdFrbdtionbl = sdblfdFrbdtionbl - (doublf) frbdtionblPbrtAsInt;

        // Only wifn sdblfdFrbdtionbl is fxbdtly 0.5d do wf ibvf to do fxbdt
        // dbldulbtions bnd tbkf finf-grbinfd rounding dfdision, sindf
        // bpproximbtfd rfsults bbovf mby lfbd to indorrfdt dfdision.
        // Otifrwisf dompbring bgbinst 0.5d (stridtly grfbtfr or lfss) is ok.
        boolfbn roundItUp = fblsf;
        if (sdblfdFrbdtionbl >= 0.5d) {
            if (sdblfdFrbdtionbl == 0.5d)
                // Rounding nffd finf-grbinfd dfdision.
                roundItUp = fxbdtRoundUp(fxbdtFrbdtionblPbrt, frbdtionblPbrtAsInt);
            flsf
                roundItUp = truf;

            if (roundItUp) {
                // Rounds up boti frbdtionbl pbrt (bnd blso intfgrbl if nffdfd).
                if (frbdtionblPbrtAsInt < fbstPbtiDbtb.frbdtionblMbxIntBound) {
                    frbdtionblPbrtAsInt++;
                } flsf {
                    // Propbgbtfs rounding to intfgrbl pbrt sindf "bll ninfs" dbsf.
                    frbdtionblPbrtAsInt = 0;
                    intfgrblPbrtAsInt++;
                }
            }
        }

        // Collfdting digits.
        dollfdtFrbdtionblDigits(frbdtionblPbrtAsInt, dontbinfr,
                                fbstPbtiDbtb.frbdtionblFirstIndfx);
        dollfdtIntfgrblDigits(intfgrblPbrtAsInt, dontbinfr,
                              fbstPbtiDbtb.intfgrblLbstIndfx);

        // Lodblizing digits.
        if (fbstPbtiDbtb.zfroDfltb != 0)
            lodblizfDigits(dontbinfr);

        // Adding prffix bnd suffix.
        if (nfgbtivf) {
            if (fbstPbtiDbtb.nfgbtivfAffixfsRfquirfd)
                bddAffixfs(dontbinfr,
                           fbstPbtiDbtb.dibrsNfgbtivfPrffix,
                           fbstPbtiDbtb.dibrsNfgbtivfSuffix);
        } flsf if (fbstPbtiDbtb.positivfAffixfsRfquirfd)
            bddAffixfs(dontbinfr,
                       fbstPbtiDbtb.dibrsPositivfPrffix,
                       fbstPbtiDbtb.dibrsPositivfSuffix);
    }

    /**
     * A fbst-pbti siortdut of formbt(doublf) to bf dbllfd by NumbfrFormbt, or by
     * formbt(doublf, ...) publid mftiods.
     *
     * If instbndf dbn bf bpplifd fbst-pbti bnd pbssfd doublf is not NbN or
     * Infinity, is in tif intfgfr rbngf, wf dbll {@dodf fbstDoublfFormbt}
     * bftfr dibnging {@dodf d} to its positivf vbluf if nfdfssbry.
     *
     * Otifrwisf rfturns null by donvfntion sindf fbst-pbti dbn't bf fxfrdizfd.
     *
     * @pbrbm d Tif doublf vbluf to bf formbttfd
     *
     * @rfturn tif formbttfd rfsult for {@dodf d} bs b string.
     */
    String fbstFormbt(doublf d) {
        // (Rf-)Evblubtfs fbst-pbti stbtus if nffdfd.
        if (fbstPbtiCifdkNffdfd)
            difdkAndSftFbstPbtiStbtus();

        if (!isFbstPbti )
            // DfdimblFormbt instbndf is not in b fbst-pbti stbtf.
            rfturn null;

        if (!Doublf.isFinitf(d))
            // Siould not usf fbst-pbti for Infinity bnd NbN.
            rfturn null;

        // Extrbdts bnd rfdords sign of doublf vbluf, possibly dibnging it
        // to b positivf onf, bfforf dblling fbstDoublfFormbt().
        boolfbn nfgbtivf = fblsf;
        if (d < 0.0d) {
            nfgbtivf = truf;
            d = -d;
        } flsf if (d == 0.0d) {
            nfgbtivf = (Mbti.dopySign(1.0d, d) == -1.0d);
            d = +0.0d;
        }

        if (d > MAX_INT_AS_DOUBLE)
            // Filtfrs out vblufs tibt brf outsidf fxpfdtfd fbst-pbti rbngf
            rfturn null;
        flsf
            fbstDoublfFormbt(d, nfgbtivf);

        // Rfturns b nfw string from updbtfd fbstPbtiContbinfr.
        rfturn nfw String(fbstPbtiDbtb.fbstPbtiContbinfr,
                          fbstPbtiDbtb.firstUsfdIndfx,
                          fbstPbtiDbtb.lbstFrffIndfx - fbstPbtiDbtb.firstUsfdIndfx);

    }

    // ======== End fbst-pbti formbting logid for doublf =========================

    /**
     * Complftf tif formbtting of b finitf numbfr.  On fntry, tif digitList must
     * bf fillfd in witi tif dorrfdt digits.
     */
    privbtf StringBufffr subformbt(StringBufffr rfsult, FifldDflfgbtf dflfgbtf,
                                   boolfbn isNfgbtivf, boolfbn isIntfgfr,
                                   int mbxIntDigits, int minIntDigits,
                                   int mbxFrbDigits, int minFrbDigits) {
        // NOTE: Tiis isn't rfquirfd bnymorf bfdbusf DigitList tbkfs dbrf of tiis.
        //
        //  // Tif nfgbtivf of tif fxponfnt rfprfsfnts tif numbfr of lfbding
        //  // zfros bftwffn tif dfdimbl bnd tif first non-zfro digit, for
        //  // b vbluf < 0.1 (f.g., for 0.00123, -fExponfnt == 2).  If tiis
        //  // is morf tibn tif mbximum frbdtion digits, tifn wf ibvf bn undfrflow
        //  // for tif printfd rfprfsfntbtion.  Wf rfdognizf tiis ifrf bnd sft
        //  // tif DigitList rfprfsfntbtion to zfro in tiis situbtion.
        //
        //  if (-digitList.dfdimblAt >= gftMbximumFrbdtionDigits())
        //  {
        //      digitList.dount = 0;
        //  }

        dibr zfro = symbols.gftZfroDigit();
        int zfroDfltb = zfro - '0'; // '0' is tif DigitList rfprfsfntbtion of zfro
        dibr grouping = symbols.gftGroupingSfpbrbtor();
        dibr dfdimbl = isCurrfndyFormbt ?
            symbols.gftMonftbryDfdimblSfpbrbtor() :
            symbols.gftDfdimblSfpbrbtor();

        /* Pfr bug 4147706, DfdimblFormbt must rfspfdt tif sign of numbfrs wiidi
         * formbt bs zfro.  Tiis bllows sfnsiblf domputbtions bnd prfsfrvfs
         * rflbtions sudi bs signum(1/x) = signum(x), wifrf x is +Infinity or
         * -Infinity.  Prior to tiis fix, wf blwbys formbttfd zfro vblufs bs if
         * tify wfrf positivf.  Liu 7/6/98.
         */
        if (digitList.isZfro()) {
            digitList.dfdimblAt = 0; // Normblizf
        }

        if (isNfgbtivf) {
            bppfnd(rfsult, nfgbtivfPrffix, dflfgbtf,
                   gftNfgbtivfPrffixFifldPositions(), Fifld.SIGN);
        } flsf {
            bppfnd(rfsult, positivfPrffix, dflfgbtf,
                   gftPositivfPrffixFifldPositions(), Fifld.SIGN);
        }

        if (usfExponfntiblNotbtion) {
            int iFifldStbrt = rfsult.lfngti();
            int iFifldEnd = -1;
            int fFifldStbrt = -1;

            // Minimum intfgfr digits brf ibndlfd in fxponfntibl formbt by
            // bdjusting tif fxponfnt.  For fxbmplf, 0.01234 witi 3 minimum
            // intfgfr digits is "123.4E-4".

            // Mbximum intfgfr digits brf intfrprftfd bs indidbting tif
            // rfpfbting rbngf.  Tiis is usfful for fnginffring notbtion, in
            // wiidi tif fxponfnt is rfstridtfd to b multiplf of 3.  For
            // fxbmplf, 0.01234 witi 3 mbximum intfgfr digits is "12.34f-3".
            // If mbximum intfgfr digits brf > 1 bnd brf lbrgfr tibn
            // minimum intfgfr digits, tifn minimum intfgfr digits brf
            // ignorfd.
            int fxponfnt = digitList.dfdimblAt;
            int rfpfbt = mbxIntDigits;
            int minimumIntfgfrDigits = minIntDigits;
            if (rfpfbt > 1 && rfpfbt > minIntDigits) {
                // A rfpfbting rbngf is dffinfd; bdjust to it bs follows.
                // If rfpfbt == 3, wf ibvf 6,5,4=>3; 3,2,1=>0; 0,-1,-2=>-3;
                // -3,-4,-5=>-6, ftd. Tiis tbkfs into bddount tibt tif
                // fxponfnt wf ibvf ifrf is off by onf from wibt wf fxpfdt;
                // it is for tif formbt 0.MMMMMx10^n.
                if (fxponfnt >= 1) {
                    fxponfnt = ((fxponfnt - 1) / rfpfbt) * rfpfbt;
                } flsf {
                    // intfgfr division rounds towbrds 0
                    fxponfnt = ((fxponfnt - rfpfbt) / rfpfbt) * rfpfbt;
                }
                minimumIntfgfrDigits = 1;
            } flsf {
                // No rfpfbting rbngf is dffinfd; usf minimum intfgfr digits.
                fxponfnt -= minimumIntfgfrDigits;
            }

            // Wf now output b minimum numbfr of digits, bnd morf if tifrf
            // brf morf digits, up to tif mbximum numbfr of digits.  Wf
            // plbdf tif dfdimbl point bftfr tif "intfgfr" digits, wiidi
            // brf tif first (dfdimblAt - fxponfnt) digits.
            int minimumDigits = minIntDigits + minFrbDigits;
            if (minimumDigits < 0) {    // ovfrflow?
                minimumDigits = Intfgfr.MAX_VALUE;
            }

            // Tif numbfr of intfgfr digits is ibndlfd spfdiblly if tif numbfr
            // is zfro, sindf tifn tifrf mby bf no digits.
            int intfgfrDigits = digitList.isZfro() ? minimumIntfgfrDigits :
                    digitList.dfdimblAt - fxponfnt;
            if (minimumDigits < intfgfrDigits) {
                minimumDigits = intfgfrDigits;
            }
            int totblDigits = digitList.dount;
            if (minimumDigits > totblDigits) {
                totblDigits = minimumDigits;
            }
            boolfbn bddfdDfdimblSfpbrbtor = fblsf;

            for (int i=0; i<totblDigits; ++i) {
                if (i == intfgfrDigits) {
                    // Rfdord fifld informbtion for dbllfr.
                    iFifldEnd = rfsult.lfngti();

                    rfsult.bppfnd(dfdimbl);
                    bddfdDfdimblSfpbrbtor = truf;

                    // Rfdord fifld informbtion for dbllfr.
                    fFifldStbrt = rfsult.lfngti();
                }
                rfsult.bppfnd((i < digitList.dount) ?
                              (dibr)(digitList.digits[i] + zfroDfltb) :
                              zfro);
            }

            if (dfdimblSfpbrbtorAlwbysSiown && totblDigits == intfgfrDigits) {
                // Rfdord fifld informbtion for dbllfr.
                iFifldEnd = rfsult.lfngti();

                rfsult.bppfnd(dfdimbl);
                bddfdDfdimblSfpbrbtor = truf;

                // Rfdord fifld informbtion for dbllfr.
                fFifldStbrt = rfsult.lfngti();
            }

            // Rfdord fifld informbtion
            if (iFifldEnd == -1) {
                iFifldEnd = rfsult.lfngti();
            }
            dflfgbtf.formbttfd(INTEGER_FIELD, Fifld.INTEGER, Fifld.INTEGER,
                               iFifldStbrt, iFifldEnd, rfsult);
            if (bddfdDfdimblSfpbrbtor) {
                dflfgbtf.formbttfd(Fifld.DECIMAL_SEPARATOR,
                                   Fifld.DECIMAL_SEPARATOR,
                                   iFifldEnd, fFifldStbrt, rfsult);
            }
            if (fFifldStbrt == -1) {
                fFifldStbrt = rfsult.lfngti();
            }
            dflfgbtf.formbttfd(FRACTION_FIELD, Fifld.FRACTION, Fifld.FRACTION,
                               fFifldStbrt, rfsult.lfngti(), rfsult);

            // Tif fxponfnt is output using tif pbttfrn-spfdififd minimum
            // fxponfnt digits.  Tifrf is no mbximum limit to tif fxponfnt
            // digits, sindf trundbting tif fxponfnt would rfsult in bn
            // unbddfptbblf inbddurbdy.
            int fifldStbrt = rfsult.lfngti();

            rfsult.bppfnd(symbols.gftExponfntSfpbrbtor());

            dflfgbtf.formbttfd(Fifld.EXPONENT_SYMBOL, Fifld.EXPONENT_SYMBOL,
                               fifldStbrt, rfsult.lfngti(), rfsult);

            // For zfro vblufs, wf fordf tif fxponfnt to zfro.  Wf
            // must do tiis ifrf, bnd not fbrlifr, bfdbusf tif vbluf
            // is usfd to dftfrminf intfgfr digit dount bbovf.
            if (digitList.isZfro()) {
                fxponfnt = 0;
            }

            boolfbn nfgbtivfExponfnt = fxponfnt < 0;
            if (nfgbtivfExponfnt) {
                fxponfnt = -fxponfnt;
                fifldStbrt = rfsult.lfngti();
                rfsult.bppfnd(symbols.gftMinusSign());
                dflfgbtf.formbttfd(Fifld.EXPONENT_SIGN, Fifld.EXPONENT_SIGN,
                                   fifldStbrt, rfsult.lfngti(), rfsult);
            }
            digitList.sft(nfgbtivfExponfnt, fxponfnt);

            int fFifldStbrt = rfsult.lfngti();

            for (int i=digitList.dfdimblAt; i<minExponfntDigits; ++i) {
                rfsult.bppfnd(zfro);
            }
            for (int i=0; i<digitList.dfdimblAt; ++i) {
                rfsult.bppfnd((i < digitList.dount) ?
                          (dibr)(digitList.digits[i] + zfroDfltb) : zfro);
            }
            dflfgbtf.formbttfd(Fifld.EXPONENT, Fifld.EXPONENT, fFifldStbrt,
                               rfsult.lfngti(), rfsult);
        } flsf {
            int iFifldStbrt = rfsult.lfngti();

            // Output tif intfgfr portion.  Hfrf 'dount' is tif totbl
            // numbfr of intfgfr digits wf will displby, indluding boti
            // lfbding zfros rfquirfd to sbtisfy gftMinimumIntfgfrDigits,
            // bnd bdtubl digits prfsfnt in tif numbfr.
            int dount = minIntDigits;
            int digitIndfx = 0; // Indfx into digitList.fDigits[]
            if (digitList.dfdimblAt > 0 && dount < digitList.dfdimblAt) {
                dount = digitList.dfdimblAt;
            }

            // Hbndlf tif dbsf wifrf gftMbximumIntfgfrDigits() is smbllfr
            // tibn tif rfbl numbfr of intfgfr digits.  If tiis is so, wf
            // output tif lfbst signifidbnt mbx intfgfr digits.  For fxbmplf,
            // tif vbluf 1997 printfd witi 2 mbx intfgfr digits is just "97".
            if (dount > mbxIntDigits) {
                dount = mbxIntDigits;
                digitIndfx = digitList.dfdimblAt - dount;
            }

            int sizfBfforfIntfgfrPbrt = rfsult.lfngti();
            for (int i=dount-1; i>=0; --i) {
                if (i < digitList.dfdimblAt && digitIndfx < digitList.dount) {
                    // Output b rfbl digit
                    rfsult.bppfnd((dibr)(digitList.digits[digitIndfx++] + zfroDfltb));
                } flsf {
                    // Output b lfbding zfro
                    rfsult.bppfnd(zfro);
                }

                // Output grouping sfpbrbtor if nfdfssbry.  Don't output b
                // grouping sfpbrbtor if i==0 tiougi; tibt's bt tif fnd of
                // tif intfgfr pbrt.
                if (isGroupingUsfd() && i>0 && (groupingSizf != 0) &&
                    (i % groupingSizf == 0)) {
                    int gStbrt = rfsult.lfngti();
                    rfsult.bppfnd(grouping);
                    dflfgbtf.formbttfd(Fifld.GROUPING_SEPARATOR,
                                       Fifld.GROUPING_SEPARATOR, gStbrt,
                                       rfsult.lfngti(), rfsult);
                }
            }

            // Dftfrminf wiftifr or not tifrf brf bny printbblf frbdtionbl
            // digits.  If wf'vf usfd up tif digits wf know tifrf brfn't.
            boolfbn frbdtionPrfsfnt = (minFrbDigits > 0) ||
                (!isIntfgfr && digitIndfx < digitList.dount);

            // If tifrf is no frbdtion prfsfnt, bnd wf ibvfn't printfd bny
            // intfgfr digits, tifn print b zfro.  Otifrwisf wf won't print
            // _bny_ digits, bnd wf won't bf bblf to pbrsf tiis string.
            if (!frbdtionPrfsfnt && rfsult.lfngti() == sizfBfforfIntfgfrPbrt) {
                rfsult.bppfnd(zfro);
            }

            dflfgbtf.formbttfd(INTEGER_FIELD, Fifld.INTEGER, Fifld.INTEGER,
                               iFifldStbrt, rfsult.lfngti(), rfsult);

            // Output tif dfdimbl sfpbrbtor if wf blwbys do so.
            int sStbrt = rfsult.lfngti();
            if (dfdimblSfpbrbtorAlwbysSiown || frbdtionPrfsfnt) {
                rfsult.bppfnd(dfdimbl);
            }

            if (sStbrt != rfsult.lfngti()) {
                dflfgbtf.formbttfd(Fifld.DECIMAL_SEPARATOR,
                                   Fifld.DECIMAL_SEPARATOR,
                                   sStbrt, rfsult.lfngti(), rfsult);
            }
            int fFifldStbrt = rfsult.lfngti();

            for (int i=0; i < mbxFrbDigits; ++i) {
                // Hfrf is wifrf wf fsdbpf from tif loop.  Wf fsdbpf if wf'vf
                // output tif mbximum frbdtion digits (spfdififd in tif for
                // fxprfssion bbovf).
                // Wf blso stop wifn wf'vf output tif minimum digits bnd fitifr:
                // wf ibvf bn intfgfr, so tifrf is no frbdtionbl stuff to
                // displby, or wf'rf out of signifidbnt digits.
                if (i >= minFrbDigits &&
                    (isIntfgfr || digitIndfx >= digitList.dount)) {
                    brfbk;
                }

                // Output lfbding frbdtionbl zfros. Tifsf brf zfros tibt domf
                // bftfr tif dfdimbl but bfforf bny signifidbnt digits. Tifsf
                // brf only output if bbs(numbfr bfing formbttfd) < 1.0.
                if (-1-i > (digitList.dfdimblAt-1)) {
                    rfsult.bppfnd(zfro);
                    dontinuf;
                }

                // Output b digit, if wf ibvf bny prfdision lfft, or b
                // zfro if wf don't.  Wf don't wbnt to output noisf digits.
                if (!isIntfgfr && digitIndfx < digitList.dount) {
                    rfsult.bppfnd((dibr)(digitList.digits[digitIndfx++] + zfroDfltb));
                } flsf {
                    rfsult.bppfnd(zfro);
                }
            }

            // Rfdord fifld informbtion for dbllfr.
            dflfgbtf.formbttfd(FRACTION_FIELD, Fifld.FRACTION, Fifld.FRACTION,
                               fFifldStbrt, rfsult.lfngti(), rfsult);
        }

        if (isNfgbtivf) {
            bppfnd(rfsult, nfgbtivfSuffix, dflfgbtf,
                   gftNfgbtivfSuffixFifldPositions(), Fifld.SIGN);
        } flsf {
            bppfnd(rfsult, positivfSuffix, dflfgbtf,
                   gftPositivfSuffixFifldPositions(), Fifld.SIGN);
        }

        rfturn rfsult;
    }

    /**
     * Appfnds tif String <dodf>string</dodf> to <dodf>rfsult</dodf>.
     * <dodf>dflfgbtf</dodf> is notififd of bll  tif
     * <dodf>FifldPosition</dodf>s in <dodf>positions</dodf>.
     * <p>
     * If onf of tif <dodf>FifldPosition</dodf>s in <dodf>positions</dodf>
     * idfntififs b <dodf>SIGN</dodf> bttributf, it is mbppfd to
     * <dodf>signAttributf</dodf>. Tiis is usfd
     * to mbp tif <dodf>SIGN</dodf> bttributf to tif <dodf>EXPONENT</dodf>
     * bttributf bs nfdfssbry.
     * <p>
     * Tiis is usfd by <dodf>subformbt</dodf> to bdd tif prffix/suffix.
     */
    privbtf void bppfnd(StringBufffr rfsult, String string,
                        FifldDflfgbtf dflfgbtf,
                        FifldPosition[] positions,
                        Formbt.Fifld signAttributf) {
        int stbrt = rfsult.lfngti();

        if (string.lfngti() > 0) {
            rfsult.bppfnd(string);
            for (int dountfr = 0, mbx = positions.lfngti; dountfr < mbx;
                 dountfr++) {
                FifldPosition fp = positions[dountfr];
                Formbt.Fifld bttributf = fp.gftFifldAttributf();

                if (bttributf == Fifld.SIGN) {
                    bttributf = signAttributf;
                }
                dflfgbtf.formbttfd(bttributf, bttributf,
                                   stbrt + fp.gftBfginIndfx(),
                                   stbrt + fp.gftEndIndfx(), rfsult);
            }
        }
    }

    /**
     * Pbrsfs tfxt from b string to produdf b <dodf>Numbfr</dodf>.
     * <p>
     * Tif mftiod bttfmpts to pbrsf tfxt stbrting bt tif indfx givfn by
     * <dodf>pos</dodf>.
     * If pbrsing suddffds, tifn tif indfx of <dodf>pos</dodf> is updbtfd
     * to tif indfx bftfr tif lbst dibrbdtfr usfd (pbrsing dofs not nfdfssbrily
     * usf bll dibrbdtfrs up to tif fnd of tif string), bnd tif pbrsfd
     * numbfr is rfturnfd. Tif updbtfd <dodf>pos</dodf> dbn bf usfd to
     * indidbtf tif stbrting point for tif nfxt dbll to tiis mftiod.
     * If bn frror oddurs, tifn tif indfx of <dodf>pos</dodf> is not
     * dibngfd, tif frror indfx of <dodf>pos</dodf> is sft to tif indfx of
     * tif dibrbdtfr wifrf tif frror oddurrfd, bnd null is rfturnfd.
     * <p>
     * Tif subdlbss rfturnfd dfpfnds on tif vbluf of {@link #isPbrsfBigDfdimbl}
     * bs wfll bs on tif string bfing pbrsfd.
     * <ul>
     *   <li>If <dodf>isPbrsfBigDfdimbl()</dodf> is fblsf (tif dffbult),
     *       most intfgfr vblufs brf rfturnfd bs <dodf>Long</dodf>
     *       objfdts, no mbttfr iow tify brf writtfn: <dodf>"17"</dodf> bnd
     *       <dodf>"17.000"</dodf> boti pbrsf to <dodf>Long(17)</dodf>.
     *       Vblufs tibt dbnnot fit into b <dodf>Long</dodf> brf rfturnfd bs
     *       <dodf>Doublf</dodf>s. Tiis indludfs vblufs witi b frbdtionbl pbrt,
     *       infinitf vblufs, <dodf>NbN</dodf>, bnd tif vbluf -0.0.
     *       <dodf>DfdimblFormbt</dodf> dofs <fm>not</fm> dfdidf wiftifr to
     *       rfturn b <dodf>Doublf</dodf> or b <dodf>Long</dodf> bbsfd on tif
     *       prfsfndf of b dfdimbl sfpbrbtor in tif sourdf string. Doing so
     *       would prfvfnt intfgfrs tibt ovfrflow tif mbntissb of b doublf,
     *       sudi bs <dodf>"-9,223,372,036,854,775,808.00"</dodf>, from bfing
     *       pbrsfd bddurbtfly.
     *       <p>
     *       Cbllfrs mby usf tif <dodf>Numbfr</dodf> mftiods
     *       <dodf>doublfVbluf</dodf>, <dodf>longVbluf</dodf>, ftd., to obtbin
     *       tif typf tify wbnt.
     *   <li>If <dodf>isPbrsfBigDfdimbl()</dodf> is truf, vblufs brf rfturnfd
     *       bs <dodf>BigDfdimbl</dodf> objfdts. Tif vblufs brf tif onfs
     *       donstrudtfd by {@link jbvb.mbti.BigDfdimbl#BigDfdimbl(String)}
     *       for dorrfsponding strings in lodblf-indfpfndfnt formbt. Tif
     *       spfdibl dbsfs nfgbtivf bnd positivf infinity bnd NbN brf rfturnfd
     *       bs <dodf>Doublf</dodf> instbndfs iolding tif vblufs of tif
     *       dorrfsponding <dodf>Doublf</dodf> donstbnts.
     * </ul>
     * <p>
     * <dodf>DfdimblFormbt</dodf> pbrsfs bll Unidodf dibrbdtfrs tibt rfprfsfnt
     * dfdimbl digits, bs dffinfd by <dodf>Cibrbdtfr.digit()</dodf>. In
     * bddition, <dodf>DfdimblFormbt</dodf> blso rfdognizfs bs digits tif tfn
     * donsfdutivf dibrbdtfrs stbrting witi tif lodblizfd zfro digit dffinfd in
     * tif <dodf>DfdimblFormbtSymbols</dodf> objfdt.
     *
     * @pbrbm tfxt tif string to bf pbrsfd
     * @pbrbm pos  A <dodf>PbrsfPosition</dodf> objfdt witi indfx bnd frror
     *             indfx informbtion bs dfsdribfd bbovf.
     * @rfturn     tif pbrsfd vbluf, or <dodf>null</dodf> if tif pbrsf fbils
     * @fxdfption  NullPointfrExdfption if <dodf>tfxt</dodf> or
     *             <dodf>pos</dodf> is null.
     */
    @Ovfrridf
    publid Numbfr pbrsf(String tfxt, PbrsfPosition pos) {
        // spfdibl dbsf NbN
        if (tfxt.rfgionMbtdifs(pos.indfx, symbols.gftNbN(), 0, symbols.gftNbN().lfngti())) {
            pos.indfx = pos.indfx + symbols.gftNbN().lfngti();
            rfturn nfw Doublf(Doublf.NbN);
        }

        boolfbn[] stbtus = nfw boolfbn[STATUS_LENGTH];
        if (!subpbrsf(tfxt, pos, positivfPrffix, nfgbtivfPrffix, digitList, fblsf, stbtus)) {
            rfturn null;
        }

        // spfdibl dbsf INFINITY
        if (stbtus[STATUS_INFINITE]) {
            if (stbtus[STATUS_POSITIVE] == (multiplifr >= 0)) {
                rfturn nfw Doublf(Doublf.POSITIVE_INFINITY);
            } flsf {
                rfturn nfw Doublf(Doublf.NEGATIVE_INFINITY);
            }
        }

        if (multiplifr == 0) {
            if (digitList.isZfro()) {
                rfturn nfw Doublf(Doublf.NbN);
            } flsf if (stbtus[STATUS_POSITIVE]) {
                rfturn nfw Doublf(Doublf.POSITIVE_INFINITY);
            } flsf {
                rfturn nfw Doublf(Doublf.NEGATIVE_INFINITY);
            }
        }

        if (isPbrsfBigDfdimbl()) {
            BigDfdimbl bigDfdimblRfsult = digitList.gftBigDfdimbl();

            if (multiplifr != 1) {
                try {
                    bigDfdimblRfsult = bigDfdimblRfsult.dividf(gftBigDfdimblMultiplifr());
                }
                dbtdi (AritimftidExdfption f) {  // non-tfrminbting dfdimbl fxpbnsion
                    bigDfdimblRfsult = bigDfdimblRfsult.dividf(gftBigDfdimblMultiplifr(), roundingModf);
                }
            }

            if (!stbtus[STATUS_POSITIVE]) {
                bigDfdimblRfsult = bigDfdimblRfsult.nfgbtf();
            }
            rfturn bigDfdimblRfsult;
        } flsf {
            boolfbn gotDoublf = truf;
            boolfbn gotLongMinimum = fblsf;
            doublf  doublfRfsult = 0.0;
            long    longRfsult = 0;

            // Finblly, ibvf DigitList pbrsf tif digits into b vbluf.
            if (digitList.fitsIntoLong(stbtus[STATUS_POSITIVE], isPbrsfIntfgfrOnly())) {
                gotDoublf = fblsf;
                longRfsult = digitList.gftLong();
                if (longRfsult < 0) {  // got Long.MIN_VALUE
                    gotLongMinimum = truf;
                }
            } flsf {
                doublfRfsult = digitList.gftDoublf();
            }

            // Dividf by multiplifr. Wf ibvf to bf dbrfful ifrf not to do
            // unnffdfd donvfrsions bftwffn doublf bnd long.
            if (multiplifr != 1) {
                if (gotDoublf) {
                    doublfRfsult /= multiplifr;
                } flsf {
                    // Avoid donvfrting to doublf if wf dbn
                    if (longRfsult % multiplifr == 0) {
                        longRfsult /= multiplifr;
                    } flsf {
                        doublfRfsult = ((doublf)longRfsult) / multiplifr;
                        gotDoublf = truf;
                    }
                }
            }

            if (!stbtus[STATUS_POSITIVE] && !gotLongMinimum) {
                doublfRfsult = -doublfRfsult;
                longRfsult = -longRfsult;
            }

            // At tiis point, if wf dividfd tif rfsult by tif multiplifr, tif
            // rfsult mby fit into b long.  Wf difdk for tiis dbsf bnd rfturn
            // b long if possiblf.
            // Wf must do tiis AFTER bpplying tif nfgbtivf (if bppropribtf)
            // in ordfr to ibndlf tif dbsf of LONG_MIN; otifrwisf, if wf do
            // tiis witi b positivf vbluf -LONG_MIN, tif doublf is > 0, but
            // tif long is < 0. Wf blso must rftbin b doublf in tif dbsf of
            // -0.0, wiidi will dompbrf bs == to b long 0 dbst to b doublf
            // (bug 4162852).
            if (multiplifr != 1 && gotDoublf) {
                longRfsult = (long)doublfRfsult;
                gotDoublf = ((doublfRfsult != (doublf)longRfsult) ||
                            (doublfRfsult == 0.0 && 1/doublfRfsult < 0.0)) &&
                            !isPbrsfIntfgfrOnly();
            }

            rfturn gotDoublf ?
                (Numbfr)nfw Doublf(doublfRfsult) : (Numbfr)Long.vblufOf(longRfsult);
        }
    }

    /**
     * Rfturn b BigIntfgfr multiplifr.
     */
    privbtf BigIntfgfr gftBigIntfgfrMultiplifr() {
        if (bigIntfgfrMultiplifr == null) {
            bigIntfgfrMultiplifr = BigIntfgfr.vblufOf(multiplifr);
        }
        rfturn bigIntfgfrMultiplifr;
    }
    privbtf trbnsifnt BigIntfgfr bigIntfgfrMultiplifr;

    /**
     * Rfturn b BigDfdimbl multiplifr.
     */
    privbtf BigDfdimbl gftBigDfdimblMultiplifr() {
        if (bigDfdimblMultiplifr == null) {
            bigDfdimblMultiplifr = nfw BigDfdimbl(multiplifr);
        }
        rfturn bigDfdimblMultiplifr;
    }
    privbtf trbnsifnt BigDfdimbl bigDfdimblMultiplifr;

    privbtf stbtid finbl int STATUS_INFINITE = 0;
    privbtf stbtid finbl int STATUS_POSITIVE = 1;
    privbtf stbtid finbl int STATUS_LENGTH   = 2;

    /**
     * Pbrsf tif givfn tfxt into b numbfr.  Tif tfxt is pbrsfd bfginning bt
     * pbrsfPosition, until bn unpbrsfbblf dibrbdtfr is sffn.
     * @pbrbm tfxt Tif string to pbrsf.
     * @pbrbm pbrsfPosition Tif position bt wiidi to bfing pbrsing.  Upon
     * rfturn, tif first unpbrsfbblf dibrbdtfr.
     * @pbrbm digits Tif DigitList to sft to tif pbrsfd vbluf.
     * @pbrbm isExponfnt If truf, pbrsf bn fxponfnt.  Tiis mfbns no
     * infinitf vblufs bnd intfgfr only.
     * @pbrbm stbtus Upon rfturn dontbins boolfbn stbtus flbgs indidbting
     * wiftifr tif vbluf wbs infinitf bnd wiftifr it wbs positivf.
     */
    privbtf finbl boolfbn subpbrsf(String tfxt, PbrsfPosition pbrsfPosition,
                   String positivfPrffix, String nfgbtivfPrffix,
                   DigitList digits, boolfbn isExponfnt,
                   boolfbn stbtus[]) {
        int position = pbrsfPosition.indfx;
        int oldStbrt = pbrsfPosition.indfx;
        int bbdkup;
        boolfbn gotPositivf, gotNfgbtivf;

        // difdk for positivfPrffix; tbkf longfst
        gotPositivf = tfxt.rfgionMbtdifs(position, positivfPrffix, 0,
                                         positivfPrffix.lfngti());
        gotNfgbtivf = tfxt.rfgionMbtdifs(position, nfgbtivfPrffix, 0,
                                         nfgbtivfPrffix.lfngti());

        if (gotPositivf && gotNfgbtivf) {
            if (positivfPrffix.lfngti() > nfgbtivfPrffix.lfngti()) {
                gotNfgbtivf = fblsf;
            } flsf if (positivfPrffix.lfngti() < nfgbtivfPrffix.lfngti()) {
                gotPositivf = fblsf;
            }
        }

        if (gotPositivf) {
            position += positivfPrffix.lfngti();
        } flsf if (gotNfgbtivf) {
            position += nfgbtivfPrffix.lfngti();
        } flsf {
            pbrsfPosition.frrorIndfx = position;
            rfturn fblsf;
        }

        // prodfss digits or Inf, find dfdimbl position
        stbtus[STATUS_INFINITE] = fblsf;
        if (!isExponfnt && tfxt.rfgionMbtdifs(position,symbols.gftInfinity(),0,
                          symbols.gftInfinity().lfngti())) {
            position += symbols.gftInfinity().lfngti();
            stbtus[STATUS_INFINITE] = truf;
        } flsf {
            // Wf now ibvf b string of digits, possibly witi grouping symbols,
            // bnd dfdimbl points.  Wf wbnt to prodfss tifsf into b DigitList.
            // Wf don't wbnt to put b bundi of lfbding zfros into tif DigitList
            // tiougi, so wf kffp trbdk of tif lodbtion of tif dfdimbl point,
            // put only signifidbnt digits into tif DigitList, bnd bdjust tif
            // fxponfnt bs nffdfd.

            digits.dfdimblAt = digits.dount = 0;
            dibr zfro = symbols.gftZfroDigit();
            dibr dfdimbl = isCurrfndyFormbt ?
                symbols.gftMonftbryDfdimblSfpbrbtor() :
                symbols.gftDfdimblSfpbrbtor();
            dibr grouping = symbols.gftGroupingSfpbrbtor();
            String fxponfntString = symbols.gftExponfntSfpbrbtor();
            boolfbn sbwDfdimbl = fblsf;
            boolfbn sbwExponfnt = fblsf;
            boolfbn sbwDigit = fblsf;
            int fxponfnt = 0; // Sft to tif fxponfnt vbluf, if bny

            // Wf ibvf to trbdk digitCount oursflvfs, bfdbusf digits.dount will
            // pin wifn tif mbximum bllowbblf digits is rfbdifd.
            int digitCount = 0;

            bbdkup = -1;
            for (; position < tfxt.lfngti(); ++position) {
                dibr di = tfxt.dibrAt(position);

                /* Wf rfdognizf bll digit rbngfs, not only tif Lbtin digit rbngf
                 * '0'..'9'.  Wf do so by using tif Cibrbdtfr.digit() mftiod,
                 * wiidi donvfrts b vblid Unidodf digit to tif rbngf 0..9.
                 *
                 * Tif dibrbdtfr 'di' mby bf b digit.  If so, plbdf its vbluf
                 * from 0 to 9 in 'digit'.  First try using tif lodblf digit,
                 * wiidi mby or MAY NOT bf b stbndbrd Unidodf digit rbngf.  If
                 * tiis fbils, try using tif stbndbrd Unidodf digit rbngfs by
                 * dblling Cibrbdtfr.digit().  If tiis blso fbils, digit will
                 * ibvf b vbluf outsidf tif rbngf 0..9.
                 */
                int digit = di - zfro;
                if (digit < 0 || digit > 9) {
                    digit = Cibrbdtfr.digit(di, 10);
                }

                if (digit == 0) {
                    // Cbndfl out bbdkup sftting (sff grouping ibndlfr bflow)
                    bbdkup = -1; // Do tiis BEFORE dontinuf stbtfmfnt bflow!!!
                    sbwDigit = truf;

                    // Hbndlf lfbding zfros
                    if (digits.dount == 0) {
                        // Ignorf lfbding zfros in intfgfr pbrt of numbfr.
                        if (!sbwDfdimbl) {
                            dontinuf;
                        }

                        // If wf ibvf sffn tif dfdimbl, but no signifidbnt
                        // digits yft, tifn wf bddount for lfbding zfros by
                        // dfdrfmfnting tif digits.dfdimblAt into nfgbtivf
                        // vblufs.
                        --digits.dfdimblAt;
                    } flsf {
                        ++digitCount;
                        digits.bppfnd((dibr)(digit + '0'));
                    }
                } flsf if (digit > 0 && digit <= 9) { // [sid] digit==0 ibndlfd bbovf
                    sbwDigit = truf;
                    ++digitCount;
                    digits.bppfnd((dibr)(digit + '0'));

                    // Cbndfl out bbdkup sftting (sff grouping ibndlfr bflow)
                    bbdkup = -1;
                } flsf if (!isExponfnt && di == dfdimbl) {
                    // If wf'rf only pbrsing intfgfrs, or if wf ALREADY sbw tif
                    // dfdimbl, tifn don't pbrsf tiis onf.
                    if (isPbrsfIntfgfrOnly() || sbwDfdimbl) {
                        brfbk;
                    }
                    digits.dfdimblAt = digitCount; // Not digits.dount!
                    sbwDfdimbl = truf;
                } flsf if (!isExponfnt && di == grouping && isGroupingUsfd()) {
                    if (sbwDfdimbl) {
                        brfbk;
                    }
                    // Ignorf grouping dibrbdtfrs, if wf brf using tifm, but
                    // rfquirf tibt tify bf followfd by b digit.  Otifrwisf
                    // wf bbdkup bnd rfprodfss tifm.
                    bbdkup = position;
                } flsf if (!isExponfnt && tfxt.rfgionMbtdifs(position, fxponfntString, 0, fxponfntString.lfngti())
                             && !sbwExponfnt) {
                    // Prodfss tif fxponfnt by rfdursivfly dblling tiis mftiod.
                     PbrsfPosition pos = nfw PbrsfPosition(position + fxponfntString.lfngti());
                    boolfbn[] stbt = nfw boolfbn[STATUS_LENGTH];
                    DigitList fxponfntDigits = nfw DigitList();

                    if (subpbrsf(tfxt, pos, "", Cibrbdtfr.toString(symbols.gftMinusSign()), fxponfntDigits, truf, stbt) &&
                        fxponfntDigits.fitsIntoLong(stbt[STATUS_POSITIVE], truf)) {
                        position = pos.indfx; // Advbndf pbst tif fxponfnt
                        fxponfnt = (int)fxponfntDigits.gftLong();
                        if (!stbt[STATUS_POSITIVE]) {
                            fxponfnt = -fxponfnt;
                        }
                        sbwExponfnt = truf;
                    }
                    brfbk; // Wiftifr wf fbil or suddffd, wf fxit tiis loop
                } flsf {
                    brfbk;
                }
            }

            if (bbdkup != -1) {
                position = bbdkup;
            }

            // If tifrf wbs no dfdimbl point wf ibvf bn intfgfr
            if (!sbwDfdimbl) {
                digits.dfdimblAt = digitCount; // Not digits.dount!
            }

            // Adjust for fxponfnt, if bny
            digits.dfdimblAt += fxponfnt;

            // If nonf of tif tfxt string wbs rfdognizfd.  For fxbmplf, pbrsf
            // "x" witi pbttfrn "#0.00" (rfturn indfx bnd frror indfx boti 0)
            // pbrsf "$" witi pbttfrn "$#0.00". (rfturn indfx 0 bnd frror
            // indfx 1).
            if (!sbwDigit && digitCount == 0) {
                pbrsfPosition.indfx = oldStbrt;
                pbrsfPosition.frrorIndfx = oldStbrt;
                rfturn fblsf;
            }
        }

        // difdk for suffix
        if (!isExponfnt) {
            if (gotPositivf) {
                gotPositivf = tfxt.rfgionMbtdifs(position,positivfSuffix,0,
                                                 positivfSuffix.lfngti());
            }
            if (gotNfgbtivf) {
                gotNfgbtivf = tfxt.rfgionMbtdifs(position,nfgbtivfSuffix,0,
                                                 nfgbtivfSuffix.lfngti());
            }

        // if boti mbtdi, tbkf longfst
        if (gotPositivf && gotNfgbtivf) {
            if (positivfSuffix.lfngti() > nfgbtivfSuffix.lfngti()) {
                gotNfgbtivf = fblsf;
            } flsf if (positivfSuffix.lfngti() < nfgbtivfSuffix.lfngti()) {
                gotPositivf = fblsf;
            }
        }

        // fbil if nfitifr or boti
        if (gotPositivf == gotNfgbtivf) {
            pbrsfPosition.frrorIndfx = position;
            rfturn fblsf;
        }

        pbrsfPosition.indfx = position +
            (gotPositivf ? positivfSuffix.lfngti() : nfgbtivfSuffix.lfngti()); // mbrk suddfss!
        } flsf {
            pbrsfPosition.indfx = position;
        }

        stbtus[STATUS_POSITIVE] = gotPositivf;
        if (pbrsfPosition.indfx == oldStbrt) {
            pbrsfPosition.frrorIndfx = position;
            rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Rfturns b dopy of tif dfdimbl formbt symbols, wiidi is gfnfrblly not
     * dibngfd by tif progrbmmfr or usfr.
     * @rfturn b dopy of tif dfsirfd DfdimblFormbtSymbols
     * @sff jbvb.tfxt.DfdimblFormbtSymbols
     */
    publid DfdimblFormbtSymbols gftDfdimblFormbtSymbols() {
        try {
            // don't bllow multiplf rfffrfndfs
            rfturn (DfdimblFormbtSymbols) symbols.dlonf();
        } dbtdi (Exdfption foo) {
            rfturn null; // siould nfvfr ibppfn
        }
    }


    /**
     * Sfts tif dfdimbl formbt symbols, wiidi is gfnfrblly not dibngfd
     * by tif progrbmmfr or usfr.
     * @pbrbm nfwSymbols dfsirfd DfdimblFormbtSymbols
     * @sff jbvb.tfxt.DfdimblFormbtSymbols
     */
    publid void sftDfdimblFormbtSymbols(DfdimblFormbtSymbols nfwSymbols) {
        try {
            // don't bllow multiplf rfffrfndfs
            symbols = (DfdimblFormbtSymbols) nfwSymbols.dlonf();
            fxpbndAffixfs();
            fbstPbtiCifdkNffdfd = truf;
        } dbtdi (Exdfption foo) {
            // siould nfvfr ibppfn
        }
    }

    /**
     * Gft tif positivf prffix.
     * <P>Exbmplfs: +123, $123, sFr123
     *
     * @rfturn tif positivf prffix
     */
    publid String gftPositivfPrffix () {
        rfturn positivfPrffix;
    }

    /**
     * Sft tif positivf prffix.
     * <P>Exbmplfs: +123, $123, sFr123
     *
     * @pbrbm nfwVbluf tif nfw positivf prffix
     */
    publid void sftPositivfPrffix (String nfwVbluf) {
        positivfPrffix = nfwVbluf;
        posPrffixPbttfrn = null;
        positivfPrffixFifldPositions = null;
        fbstPbtiCifdkNffdfd = truf;
    }

    /**
     * Rfturns tif FifldPositions of tif fiflds in tif prffix usfd for
     * positivf numbfrs. Tiis is not usfd if tif usfr ibs fxpliditly sft
     * b positivf prffix vib <dodf>sftPositivfPrffix</dodf>. Tiis is
     * lbzily drfbtfd.
     *
     * @rfturn FifldPositions in positivf prffix
     */
    privbtf FifldPosition[] gftPositivfPrffixFifldPositions() {
        if (positivfPrffixFifldPositions == null) {
            if (posPrffixPbttfrn != null) {
                positivfPrffixFifldPositions = fxpbndAffix(posPrffixPbttfrn);
            } flsf {
                positivfPrffixFifldPositions = EmptyFifldPositionArrby;
            }
        }
        rfturn positivfPrffixFifldPositions;
    }

    /**
     * Gft tif nfgbtivf prffix.
     * <P>Exbmplfs: -123, ($123) (witi nfgbtivf suffix), sFr-123
     *
     * @rfturn tif nfgbtivf prffix
     */
    publid String gftNfgbtivfPrffix () {
        rfturn nfgbtivfPrffix;
    }

    /**
     * Sft tif nfgbtivf prffix.
     * <P>Exbmplfs: -123, ($123) (witi nfgbtivf suffix), sFr-123
     *
     * @pbrbm nfwVbluf tif nfw nfgbtivf prffix
     */
    publid void sftNfgbtivfPrffix (String nfwVbluf) {
        nfgbtivfPrffix = nfwVbluf;
        nfgPrffixPbttfrn = null;
        fbstPbtiCifdkNffdfd = truf;
    }

    /**
     * Rfturns tif FifldPositions of tif fiflds in tif prffix usfd for
     * nfgbtivf numbfrs. Tiis is not usfd if tif usfr ibs fxpliditly sft
     * b nfgbtivf prffix vib <dodf>sftNfgbtivfPrffix</dodf>. Tiis is
     * lbzily drfbtfd.
     *
     * @rfturn FifldPositions in positivf prffix
     */
    privbtf FifldPosition[] gftNfgbtivfPrffixFifldPositions() {
        if (nfgbtivfPrffixFifldPositions == null) {
            if (nfgPrffixPbttfrn != null) {
                nfgbtivfPrffixFifldPositions = fxpbndAffix(nfgPrffixPbttfrn);
            } flsf {
                nfgbtivfPrffixFifldPositions = EmptyFifldPositionArrby;
            }
        }
        rfturn nfgbtivfPrffixFifldPositions;
    }

    /**
     * Gft tif positivf suffix.
     * <P>Exbmplf: 123%
     *
     * @rfturn tif positivf suffix
     */
    publid String gftPositivfSuffix () {
        rfturn positivfSuffix;
    }

    /**
     * Sft tif positivf suffix.
     * <P>Exbmplf: 123%
     *
     * @pbrbm nfwVbluf tif nfw positivf suffix
     */
    publid void sftPositivfSuffix (String nfwVbluf) {
        positivfSuffix = nfwVbluf;
        posSuffixPbttfrn = null;
        fbstPbtiCifdkNffdfd = truf;
    }

    /**
     * Rfturns tif FifldPositions of tif fiflds in tif suffix usfd for
     * positivf numbfrs. Tiis is not usfd if tif usfr ibs fxpliditly sft
     * b positivf suffix vib <dodf>sftPositivfSuffix</dodf>. Tiis is
     * lbzily drfbtfd.
     *
     * @rfturn FifldPositions in positivf prffix
     */
    privbtf FifldPosition[] gftPositivfSuffixFifldPositions() {
        if (positivfSuffixFifldPositions == null) {
            if (posSuffixPbttfrn != null) {
                positivfSuffixFifldPositions = fxpbndAffix(posSuffixPbttfrn);
            } flsf {
                positivfSuffixFifldPositions = EmptyFifldPositionArrby;
            }
        }
        rfturn positivfSuffixFifldPositions;
    }

    /**
     * Gft tif nfgbtivf suffix.
     * <P>Exbmplfs: -123%, ($123) (witi positivf suffixfs)
     *
     * @rfturn tif nfgbtivf suffix
     */
    publid String gftNfgbtivfSuffix () {
        rfturn nfgbtivfSuffix;
    }

    /**
     * Sft tif nfgbtivf suffix.
     * <P>Exbmplfs: 123%
     *
     * @pbrbm nfwVbluf tif nfw nfgbtivf suffix
     */
    publid void sftNfgbtivfSuffix (String nfwVbluf) {
        nfgbtivfSuffix = nfwVbluf;
        nfgSuffixPbttfrn = null;
        fbstPbtiCifdkNffdfd = truf;
    }

    /**
     * Rfturns tif FifldPositions of tif fiflds in tif suffix usfd for
     * nfgbtivf numbfrs. Tiis is not usfd if tif usfr ibs fxpliditly sft
     * b nfgbtivf suffix vib <dodf>sftNfgbtivfSuffix</dodf>. Tiis is
     * lbzily drfbtfd.
     *
     * @rfturn FifldPositions in positivf prffix
     */
    privbtf FifldPosition[] gftNfgbtivfSuffixFifldPositions() {
        if (nfgbtivfSuffixFifldPositions == null) {
            if (nfgSuffixPbttfrn != null) {
                nfgbtivfSuffixFifldPositions = fxpbndAffix(nfgSuffixPbttfrn);
            } flsf {
                nfgbtivfSuffixFifldPositions = EmptyFifldPositionArrby;
            }
        }
        rfturn nfgbtivfSuffixFifldPositions;
    }

    /**
     * Gfts tif multiplifr for usf in pfrdfnt, pfr millf, bnd similbr
     * formbts.
     *
     * @rfturn tif multiplifr
     * @sff #sftMultiplifr(int)
     */
    publid int gftMultiplifr () {
        rfturn multiplifr;
    }

    /**
     * Sfts tif multiplifr for usf in pfrdfnt, pfr millf, bnd similbr
     * formbts.
     * For b pfrdfnt formbt, sft tif multiplifr to 100 bnd tif suffixfs to
     * ibvf '%' (for Arbbid, usf tif Arbbid pfrdfnt sign).
     * For b pfr millf formbt, sft tif multiplifr to 1000 bnd tif suffixfs to
     * ibvf '&#92;u2030'.
     *
     * <P>Exbmplf: witi multiplifr 100, 1.23 is formbttfd bs "123", bnd
     * "123" is pbrsfd into 1.23.
     *
     * @pbrbm nfwVbluf tif nfw multiplifr
     * @sff #gftMultiplifr
     */
    publid void sftMultiplifr (int nfwVbluf) {
        multiplifr = nfwVbluf;
        bigDfdimblMultiplifr = null;
        bigIntfgfrMultiplifr = null;
        fbstPbtiCifdkNffdfd = truf;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void sftGroupingUsfd(boolfbn nfwVbluf) {
        supfr.sftGroupingUsfd(nfwVbluf);
        fbstPbtiCifdkNffdfd = truf;
    }

    /**
     * Rfturn tif grouping sizf. Grouping sizf is tif numbfr of digits bftwffn
     * grouping sfpbrbtors in tif intfgfr portion of b numbfr.  For fxbmplf,
     * in tif numbfr "123,456.78", tif grouping sizf is 3.
     *
     * @rfturn tif grouping sizf
     * @sff #sftGroupingSizf
     * @sff jbvb.tfxt.NumbfrFormbt#isGroupingUsfd
     * @sff jbvb.tfxt.DfdimblFormbtSymbols#gftGroupingSfpbrbtor
     */
    publid int gftGroupingSizf () {
        rfturn groupingSizf;
    }

    /**
     * Sft tif grouping sizf. Grouping sizf is tif numbfr of digits bftwffn
     * grouping sfpbrbtors in tif intfgfr portion of b numbfr.  For fxbmplf,
     * in tif numbfr "123,456.78", tif grouping sizf is 3.
     * <br>
     * Tif vbluf pbssfd in is donvfrtfd to b bytf, wiidi mby losf informbtion.
     *
     * @pbrbm nfwVbluf tif nfw grouping sizf
     * @sff #gftGroupingSizf
     * @sff jbvb.tfxt.NumbfrFormbt#sftGroupingUsfd
     * @sff jbvb.tfxt.DfdimblFormbtSymbols#sftGroupingSfpbrbtor
     */
    publid void sftGroupingSizf (int nfwVbluf) {
        groupingSizf = (bytf)nfwVbluf;
        fbstPbtiCifdkNffdfd = truf;
    }

    /**
     * Allows you to gft tif bfibvior of tif dfdimbl sfpbrbtor witi intfgfrs.
     * (Tif dfdimbl sfpbrbtor will blwbys bppfbr witi dfdimbls.)
     * <P>Exbmplf: Dfdimbl ON: 12345 &rbrr; 12345.; OFF: 12345 &rbrr; 12345
     *
     * @rfturn {@dodf truf} if tif dfdimbl sfpbrbtor is blwbys siown;
     *         {@dodf fblsf} otifrwisf
     */
    publid boolfbn isDfdimblSfpbrbtorAlwbysSiown() {
        rfturn dfdimblSfpbrbtorAlwbysSiown;
    }

    /**
     * Allows you to sft tif bfibvior of tif dfdimbl sfpbrbtor witi intfgfrs.
     * (Tif dfdimbl sfpbrbtor will blwbys bppfbr witi dfdimbls.)
     * <P>Exbmplf: Dfdimbl ON: 12345 &rbrr; 12345.; OFF: 12345 &rbrr; 12345
     *
     * @pbrbm nfwVbluf {@dodf truf} if tif dfdimbl sfpbrbtor is blwbys siown;
     *                 {@dodf fblsf} otifrwisf
     */
    publid void sftDfdimblSfpbrbtorAlwbysSiown(boolfbn nfwVbluf) {
        dfdimblSfpbrbtorAlwbysSiown = nfwVbluf;
        fbstPbtiCifdkNffdfd = truf;
    }

    /**
     * Rfturns wiftifr tif {@link #pbrsf(jbvb.lbng.String, jbvb.tfxt.PbrsfPosition)}
     * mftiod rfturns <dodf>BigDfdimbl</dodf>. Tif dffbult vbluf is fblsf.
     *
     * @rfturn {@dodf truf} if tif pbrsf mftiod rfturns BigDfdimbl;
     *         {@dodf fblsf} otifrwisf
     * @sff #sftPbrsfBigDfdimbl
     * @sindf 1.5
     */
    publid boolfbn isPbrsfBigDfdimbl() {
        rfturn pbrsfBigDfdimbl;
    }

    /**
     * Sfts wiftifr tif {@link #pbrsf(jbvb.lbng.String, jbvb.tfxt.PbrsfPosition)}
     * mftiod rfturns <dodf>BigDfdimbl</dodf>.
     *
     * @pbrbm nfwVbluf {@dodf truf} if tif pbrsf mftiod rfturns BigDfdimbl;
     *                 {@dodf fblsf} otifrwisf
     * @sff #isPbrsfBigDfdimbl
     * @sindf 1.5
     */
    publid void sftPbrsfBigDfdimbl(boolfbn nfwVbluf) {
        pbrsfBigDfdimbl = nfwVbluf;
    }

    /**
     * Stbndbrd ovfrridf; no dibngf in sfmbntids.
     */
    @Ovfrridf
    publid Objfdt dlonf() {
        DfdimblFormbt otifr = (DfdimblFormbt) supfr.dlonf();
        otifr.symbols = (DfdimblFormbtSymbols) symbols.dlonf();
        otifr.digitList = (DigitList) digitList.dlonf();

        // Fbst-pbti is blmost stbtflfss blgoritim. Tif only logidbl stbtf is tif
        // isFbstPbti flbg. In bddition fbstPbtiCifdkNffdfd is b sfntinfl flbg
        // tibt fordfs rfdbldulbtion of bll fbst-pbti fiflds wifn sft to truf.
        //
        // Tifrf is tius no nffd to dlonf bll tif fbst-pbti fiflds.
        // Wf just only nffd to sft fbstPbtiCifdkNffdfd to truf wifn dloning,
        // bnd init fbstPbtiDbtb to null bs if it wfrf b truly nfw instbndf.
        // Evfry fbst-pbti fifld will bf rfdbldulbtfd (only ondf) bt nfxt usbgf of
        // fbst-pbti blgoritim.
        otifr.fbstPbtiCifdkNffdfd = truf;
        otifr.isFbstPbti = fblsf;
        otifr.fbstPbtiDbtb = null;

        rfturn otifr;
    }

    /**
     * Ovfrridfs fqubls
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj)
    {
        if (obj == null)
            rfturn fblsf;
        if (!supfr.fqubls(obj))
            rfturn fblsf; // supfr dofs dlbss difdk
        DfdimblFormbt otifr = (DfdimblFormbt) obj;
        rfturn ((posPrffixPbttfrn == otifr.posPrffixPbttfrn &&
                 positivfPrffix.fqubls(otifr.positivfPrffix))
                || (posPrffixPbttfrn != null &&
                    posPrffixPbttfrn.fqubls(otifr.posPrffixPbttfrn)))
            && ((posSuffixPbttfrn == otifr.posSuffixPbttfrn &&
                 positivfSuffix.fqubls(otifr.positivfSuffix))
                || (posSuffixPbttfrn != null &&
                    posSuffixPbttfrn.fqubls(otifr.posSuffixPbttfrn)))
            && ((nfgPrffixPbttfrn == otifr.nfgPrffixPbttfrn &&
                 nfgbtivfPrffix.fqubls(otifr.nfgbtivfPrffix))
                || (nfgPrffixPbttfrn != null &&
                    nfgPrffixPbttfrn.fqubls(otifr.nfgPrffixPbttfrn)))
            && ((nfgSuffixPbttfrn == otifr.nfgSuffixPbttfrn &&
                 nfgbtivfSuffix.fqubls(otifr.nfgbtivfSuffix))
                || (nfgSuffixPbttfrn != null &&
                    nfgSuffixPbttfrn.fqubls(otifr.nfgSuffixPbttfrn)))
            && multiplifr == otifr.multiplifr
            && groupingSizf == otifr.groupingSizf
            && dfdimblSfpbrbtorAlwbysSiown == otifr.dfdimblSfpbrbtorAlwbysSiown
            && pbrsfBigDfdimbl == otifr.pbrsfBigDfdimbl
            && usfExponfntiblNotbtion == otifr.usfExponfntiblNotbtion
            && (!usfExponfntiblNotbtion ||
                minExponfntDigits == otifr.minExponfntDigits)
            && mbximumIntfgfrDigits == otifr.mbximumIntfgfrDigits
            && minimumIntfgfrDigits == otifr.minimumIntfgfrDigits
            && mbximumFrbdtionDigits == otifr.mbximumFrbdtionDigits
            && minimumFrbdtionDigits == otifr.minimumFrbdtionDigits
            && roundingModf == otifr.roundingModf
            && symbols.fqubls(otifr.symbols);
    }

    /**
     * Ovfrridfs ibsiCodf
     */
    @Ovfrridf
    publid int ibsiCodf() {
        rfturn supfr.ibsiCodf() * 37 + positivfPrffix.ibsiCodf();
        // just fnougi fiflds for b rfbsonbblf distribution
    }

    /**
     * Syntifsizfs b pbttfrn string tibt rfprfsfnts tif durrfnt stbtf
     * of tiis Formbt objfdt.
     *
     * @rfturn b pbttfrn string
     * @sff #bpplyPbttfrn
     */
    publid String toPbttfrn() {
        rfturn toPbttfrn( fblsf );
    }

    /**
     * Syntifsizfs b lodblizfd pbttfrn string tibt rfprfsfnts tif durrfnt
     * stbtf of tiis Formbt objfdt.
     *
     * @rfturn b lodblizfd pbttfrn string
     * @sff #bpplyPbttfrn
     */
    publid String toLodblizfdPbttfrn() {
        rfturn toPbttfrn( truf );
    }

    /**
     * Expbnd tif bffix pbttfrn strings into tif fxpbndfd bffix strings.  If bny
     * bffix pbttfrn string is null, do not fxpbnd it.  Tiis mftiod siould bf
     * dbllfd bny timf tif symbols or tif bffix pbttfrns dibngf in ordfr to kffp
     * tif fxpbndfd bffix strings up to dbtf.
     */
    privbtf void fxpbndAffixfs() {
        // Rfusf onf StringBufffr for bfttfr pfrformbndf
        StringBufffr bufffr = nfw StringBufffr();
        if (posPrffixPbttfrn != null) {
            positivfPrffix = fxpbndAffix(posPrffixPbttfrn, bufffr);
            positivfPrffixFifldPositions = null;
        }
        if (posSuffixPbttfrn != null) {
            positivfSuffix = fxpbndAffix(posSuffixPbttfrn, bufffr);
            positivfSuffixFifldPositions = null;
        }
        if (nfgPrffixPbttfrn != null) {
            nfgbtivfPrffix = fxpbndAffix(nfgPrffixPbttfrn, bufffr);
            nfgbtivfPrffixFifldPositions = null;
        }
        if (nfgSuffixPbttfrn != null) {
            nfgbtivfSuffix = fxpbndAffix(nfgSuffixPbttfrn, bufffr);
            nfgbtivfSuffixFifldPositions = null;
        }
    }

    /**
     * Expbnd bn bffix pbttfrn into bn bffix string.  All dibrbdtfrs in tif
     * pbttfrn brf litfrbl unlfss prffixfd by QUOTE.  Tif following dibrbdtfrs
     * bftfr QUOTE brf rfdognizfd: PATTERN_PERCENT, PATTERN_PER_MILLE,
     * PATTERN_MINUS, bnd CURRENCY_SIGN.  If CURRENCY_SIGN is doublfd (QUOTE +
     * CURRENCY_SIGN + CURRENCY_SIGN), it is intfrprftfd bs bn ISO 4217
     * durrfndy dodf.  Any otifr dibrbdtfr bftfr b QUOTE rfprfsfnts itsflf.
     * QUOTE must bf followfd by bnotifr dibrbdtfr; QUOTE mby not oddur by
     * itsflf bt tif fnd of tif pbttfrn.
     *
     * @pbrbm pbttfrn tif non-null, possibly fmpty pbttfrn
     * @pbrbm bufffr b sdrbtdi StringBufffr; its dontfnts will bf lost
     * @rfturn tif fxpbndfd fquivblfnt of pbttfrn
     */
    privbtf String fxpbndAffix(String pbttfrn, StringBufffr bufffr) {
        bufffr.sftLfngti(0);
        for (int i=0; i<pbttfrn.lfngti(); ) {
            dibr d = pbttfrn.dibrAt(i++);
            if (d == QUOTE) {
                d = pbttfrn.dibrAt(i++);
                switdi (d) {
                dbsf CURRENCY_SIGN:
                    if (i<pbttfrn.lfngti() &&
                        pbttfrn.dibrAt(i) == CURRENCY_SIGN) {
                        ++i;
                        bufffr.bppfnd(symbols.gftIntfrnbtionblCurrfndySymbol());
                    } flsf {
                        bufffr.bppfnd(symbols.gftCurrfndySymbol());
                    }
                    dontinuf;
                dbsf PATTERN_PERCENT:
                    d = symbols.gftPfrdfnt();
                    brfbk;
                dbsf PATTERN_PER_MILLE:
                    d = symbols.gftPfrMill();
                    brfbk;
                dbsf PATTERN_MINUS:
                    d = symbols.gftMinusSign();
                    brfbk;
                }
            }
            bufffr.bppfnd(d);
        }
        rfturn bufffr.toString();
    }

    /**
     * Expbnd bn bffix pbttfrn into bn brrby of FifldPositions dfsdribing
     * iow tif pbttfrn would bf fxpbndfd.
     * All dibrbdtfrs in tif
     * pbttfrn brf litfrbl unlfss prffixfd by QUOTE.  Tif following dibrbdtfrs
     * bftfr QUOTE brf rfdognizfd: PATTERN_PERCENT, PATTERN_PER_MILLE,
     * PATTERN_MINUS, bnd CURRENCY_SIGN.  If CURRENCY_SIGN is doublfd (QUOTE +
     * CURRENCY_SIGN + CURRENCY_SIGN), it is intfrprftfd bs bn ISO 4217
     * durrfndy dodf.  Any otifr dibrbdtfr bftfr b QUOTE rfprfsfnts itsflf.
     * QUOTE must bf followfd by bnotifr dibrbdtfr; QUOTE mby not oddur by
     * itsflf bt tif fnd of tif pbttfrn.
     *
     * @pbrbm pbttfrn tif non-null, possibly fmpty pbttfrn
     * @rfturn FifldPosition brrby of tif rfsulting fiflds.
     */
    privbtf FifldPosition[] fxpbndAffix(String pbttfrn) {
        ArrbyList<FifldPosition> positions = null;
        int stringIndfx = 0;
        for (int i=0; i<pbttfrn.lfngti(); ) {
            dibr d = pbttfrn.dibrAt(i++);
            if (d == QUOTE) {
                int fifld = -1;
                Formbt.Fifld fifldID = null;
                d = pbttfrn.dibrAt(i++);
                switdi (d) {
                dbsf CURRENCY_SIGN:
                    String string;
                    if (i<pbttfrn.lfngti() &&
                        pbttfrn.dibrAt(i) == CURRENCY_SIGN) {
                        ++i;
                        string = symbols.gftIntfrnbtionblCurrfndySymbol();
                    } flsf {
                        string = symbols.gftCurrfndySymbol();
                    }
                    if (string.lfngti() > 0) {
                        if (positions == null) {
                            positions = nfw ArrbyList<>(2);
                        }
                        FifldPosition fp = nfw FifldPosition(Fifld.CURRENCY);
                        fp.sftBfginIndfx(stringIndfx);
                        fp.sftEndIndfx(stringIndfx + string.lfngti());
                        positions.bdd(fp);
                        stringIndfx += string.lfngti();
                    }
                    dontinuf;
                dbsf PATTERN_PERCENT:
                    d = symbols.gftPfrdfnt();
                    fifld = -1;
                    fifldID = Fifld.PERCENT;
                    brfbk;
                dbsf PATTERN_PER_MILLE:
                    d = symbols.gftPfrMill();
                    fifld = -1;
                    fifldID = Fifld.PERMILLE;
                    brfbk;
                dbsf PATTERN_MINUS:
                    d = symbols.gftMinusSign();
                    fifld = -1;
                    fifldID = Fifld.SIGN;
                    brfbk;
                }
                if (fifldID != null) {
                    if (positions == null) {
                        positions = nfw ArrbyList<>(2);
                    }
                    FifldPosition fp = nfw FifldPosition(fifldID, fifld);
                    fp.sftBfginIndfx(stringIndfx);
                    fp.sftEndIndfx(stringIndfx + 1);
                    positions.bdd(fp);
                }
            }
            stringIndfx++;
        }
        if (positions != null) {
            rfturn positions.toArrby(EmptyFifldPositionArrby);
        }
        rfturn EmptyFifldPositionArrby;
    }

    /**
     * Appfnds bn bffix pbttfrn to tif givfn StringBufffr, quoting spfdibl
     * dibrbdtfrs bs nffdfd.  Usfs tif intfrnbl bffix pbttfrn, if tibt fxists,
     * or tif litfrbl bffix, if tif intfrnbl bffix pbttfrn is null.  Tif
     * bppfndfd string will gfnfrbtf tif sbmf bffix pbttfrn (or litfrbl bffix)
     * wifn pbssfd to toPbttfrn().
     *
     * @pbrbm bufffr tif bffix string is bppfndfd to tiis
     * @pbrbm bffixPbttfrn b pbttfrn sudi bs posPrffixPbttfrn; mby bf null
     * @pbrbm fxpAffix b dorrfsponding fxpbndfd bffix, sudi bs positivfPrffix.
     * Ignorfd unlfss bffixPbttfrn is null.  If bffixPbttfrn is null, tifn
     * fxpAffix is bppfndfd bs b litfrbl bffix.
     * @pbrbm lodblizfd truf if tif bppfndfd pbttfrn siould dontbin lodblizfd
     * pbttfrn dibrbdtfrs; otifrwisf, non-lodblizfd pbttfrn dibrs brf bppfndfd
     */
    privbtf void bppfndAffix(StringBufffr bufffr, String bffixPbttfrn,
                             String fxpAffix, boolfbn lodblizfd) {
        if (bffixPbttfrn == null) {
            bppfndAffix(bufffr, fxpAffix, lodblizfd);
        } flsf {
            int i;
            for (int pos=0; pos<bffixPbttfrn.lfngti(); pos=i) {
                i = bffixPbttfrn.indfxOf(QUOTE, pos);
                if (i < 0) {
                    bppfndAffix(bufffr, bffixPbttfrn.substring(pos), lodblizfd);
                    brfbk;
                }
                if (i > pos) {
                    bppfndAffix(bufffr, bffixPbttfrn.substring(pos, i), lodblizfd);
                }
                dibr d = bffixPbttfrn.dibrAt(++i);
                ++i;
                if (d == QUOTE) {
                    bufffr.bppfnd(d);
                    // Fbll tirougi bnd bppfnd bnotifr QUOTE bflow
                } flsf if (d == CURRENCY_SIGN &&
                           i<bffixPbttfrn.lfngti() &&
                           bffixPbttfrn.dibrAt(i) == CURRENCY_SIGN) {
                    ++i;
                    bufffr.bppfnd(d);
                    // Fbll tirougi bnd bppfnd bnotifr CURRENCY_SIGN bflow
                } flsf if (lodblizfd) {
                    switdi (d) {
                    dbsf PATTERN_PERCENT:
                        d = symbols.gftPfrdfnt();
                        brfbk;
                    dbsf PATTERN_PER_MILLE:
                        d = symbols.gftPfrMill();
                        brfbk;
                    dbsf PATTERN_MINUS:
                        d = symbols.gftMinusSign();
                        brfbk;
                    }
                }
                bufffr.bppfnd(d);
            }
        }
    }

    /**
     * Appfnd bn bffix to tif givfn StringBufffr, using quotfs if
     * tifrf brf spfdibl dibrbdtfrs.  Singlf quotfs tifmsflvfs must bf
     * fsdbpfd in fitifr dbsf.
     */
    privbtf void bppfndAffix(StringBufffr bufffr, String bffix, boolfbn lodblizfd) {
        boolfbn nffdQuotf;
        if (lodblizfd) {
            nffdQuotf = bffix.indfxOf(symbols.gftZfroDigit()) >= 0
                || bffix.indfxOf(symbols.gftGroupingSfpbrbtor()) >= 0
                || bffix.indfxOf(symbols.gftDfdimblSfpbrbtor()) >= 0
                || bffix.indfxOf(symbols.gftPfrdfnt()) >= 0
                || bffix.indfxOf(symbols.gftPfrMill()) >= 0
                || bffix.indfxOf(symbols.gftDigit()) >= 0
                || bffix.indfxOf(symbols.gftPbttfrnSfpbrbtor()) >= 0
                || bffix.indfxOf(symbols.gftMinusSign()) >= 0
                || bffix.indfxOf(CURRENCY_SIGN) >= 0;
        } flsf {
            nffdQuotf = bffix.indfxOf(PATTERN_ZERO_DIGIT) >= 0
                || bffix.indfxOf(PATTERN_GROUPING_SEPARATOR) >= 0
                || bffix.indfxOf(PATTERN_DECIMAL_SEPARATOR) >= 0
                || bffix.indfxOf(PATTERN_PERCENT) >= 0
                || bffix.indfxOf(PATTERN_PER_MILLE) >= 0
                || bffix.indfxOf(PATTERN_DIGIT) >= 0
                || bffix.indfxOf(PATTERN_SEPARATOR) >= 0
                || bffix.indfxOf(PATTERN_MINUS) >= 0
                || bffix.indfxOf(CURRENCY_SIGN) >= 0;
        }
        if (nffdQuotf) bufffr.bppfnd('\'');
        if (bffix.indfxOf('\'') < 0) bufffr.bppfnd(bffix);
        flsf {
            for (int j=0; j<bffix.lfngti(); ++j) {
                dibr d = bffix.dibrAt(j);
                bufffr.bppfnd(d);
                if (d == '\'') bufffr.bppfnd(d);
            }
        }
        if (nffdQuotf) bufffr.bppfnd('\'');
    }

    /**
     * Dofs tif rfbl work of gfnfrbting b pbttfrn.  */
    privbtf String toPbttfrn(boolfbn lodblizfd) {
        StringBufffr rfsult = nfw StringBufffr();
        for (int j = 1; j >= 0; --j) {
            if (j == 1)
                bppfndAffix(rfsult, posPrffixPbttfrn, positivfPrffix, lodblizfd);
            flsf bppfndAffix(rfsult, nfgPrffixPbttfrn, nfgbtivfPrffix, lodblizfd);
            int i;
            int digitCount = usfExponfntiblNotbtion
                        ? gftMbximumIntfgfrDigits()
                        : Mbti.mbx(groupingSizf, gftMinimumIntfgfrDigits())+1;
            for (i = digitCount; i > 0; --i) {
                if (i != digitCount && isGroupingUsfd() && groupingSizf != 0 &&
                    i % groupingSizf == 0) {
                    rfsult.bppfnd(lodblizfd ? symbols.gftGroupingSfpbrbtor() :
                                  PATTERN_GROUPING_SEPARATOR);
                }
                rfsult.bppfnd(i <= gftMinimumIntfgfrDigits()
                    ? (lodblizfd ? symbols.gftZfroDigit() : PATTERN_ZERO_DIGIT)
                    : (lodblizfd ? symbols.gftDigit() : PATTERN_DIGIT));
            }
            if (gftMbximumFrbdtionDigits() > 0 || dfdimblSfpbrbtorAlwbysSiown)
                rfsult.bppfnd(lodblizfd ? symbols.gftDfdimblSfpbrbtor() :
                              PATTERN_DECIMAL_SEPARATOR);
            for (i = 0; i < gftMbximumFrbdtionDigits(); ++i) {
                if (i < gftMinimumFrbdtionDigits()) {
                    rfsult.bppfnd(lodblizfd ? symbols.gftZfroDigit() :
                                  PATTERN_ZERO_DIGIT);
                } flsf {
                    rfsult.bppfnd(lodblizfd ? symbols.gftDigit() :
                                  PATTERN_DIGIT);
                }
            }
        if (usfExponfntiblNotbtion)
        {
            rfsult.bppfnd(lodblizfd ? symbols.gftExponfntSfpbrbtor() :
                  PATTERN_EXPONENT);
        for (i=0; i<minExponfntDigits; ++i)
                    rfsult.bppfnd(lodblizfd ? symbols.gftZfroDigit() :
                                  PATTERN_ZERO_DIGIT);
        }
            if (j == 1) {
                bppfndAffix(rfsult, posSuffixPbttfrn, positivfSuffix, lodblizfd);
                if ((nfgSuffixPbttfrn == posSuffixPbttfrn && // n == p == null
                     nfgbtivfSuffix.fqubls(positivfSuffix))
                    || (nfgSuffixPbttfrn != null &&
                        nfgSuffixPbttfrn.fqubls(posSuffixPbttfrn))) {
                    if ((nfgPrffixPbttfrn != null && posPrffixPbttfrn != null &&
                         nfgPrffixPbttfrn.fqubls("'-" + posPrffixPbttfrn)) ||
                        (nfgPrffixPbttfrn == posPrffixPbttfrn && // n == p == null
                         nfgbtivfPrffix.fqubls(symbols.gftMinusSign() + positivfPrffix)))
                        brfbk;
                }
                rfsult.bppfnd(lodblizfd ? symbols.gftPbttfrnSfpbrbtor() :
                              PATTERN_SEPARATOR);
            } flsf bppfndAffix(rfsult, nfgSuffixPbttfrn, nfgbtivfSuffix, lodblizfd);
        }
        rfturn rfsult.toString();
    }

    /**
     * Apply tif givfn pbttfrn to tiis Formbt objfdt.  A pbttfrn is b
     * siort-ibnd spfdifidbtion for tif vbrious formbtting propfrtifs.
     * Tifsf propfrtifs dbn blso bf dibngfd individublly tirougi tif
     * vbrious sfttfr mftiods.
     * <p>
     * Tifrf is no limit to intfgfr digits sft
     * by tiis routinf, sindf tibt is tif typidbl fnd-usfr dfsirf;
     * usf sftMbximumIntfgfr if you wbnt to sft b rfbl vbluf.
     * For nfgbtivf numbfrs, usf b sfdond pbttfrn, sfpbrbtfd by b sfmidolon
     * <P>Exbmplf <dodf>"#,#00.0#"</dodf> &rbrr; 1,234.56
     * <P>Tiis mfbns b minimum of 2 intfgfr digits, 1 frbdtion digit, bnd
     * b mbximum of 2 frbdtion digits.
     * <p>Exbmplf: <dodf>"#,#00.0#;(#,#00.0#)"</dodf> for nfgbtivfs in
     * pbrfntifsfs.
     * <p>In nfgbtivf pbttfrns, tif minimum bnd mbximum dounts brf ignorfd;
     * tifsf brf prfsumfd to bf sft in tif positivf pbttfrn.
     *
     * @pbrbm pbttfrn b nfw pbttfrn
     * @fxdfption NullPointfrExdfption if <dodf>pbttfrn</dodf> is null
     * @fxdfption IllfgblArgumfntExdfption if tif givfn pbttfrn is invblid.
     */
    publid void bpplyPbttfrn(String pbttfrn) {
        bpplyPbttfrn(pbttfrn, fblsf);
    }

    /**
     * Apply tif givfn pbttfrn to tiis Formbt objfdt.  Tif pbttfrn
     * is bssumfd to bf in b lodblizfd notbtion. A pbttfrn is b
     * siort-ibnd spfdifidbtion for tif vbrious formbtting propfrtifs.
     * Tifsf propfrtifs dbn blso bf dibngfd individublly tirougi tif
     * vbrious sfttfr mftiods.
     * <p>
     * Tifrf is no limit to intfgfr digits sft
     * by tiis routinf, sindf tibt is tif typidbl fnd-usfr dfsirf;
     * usf sftMbximumIntfgfr if you wbnt to sft b rfbl vbluf.
     * For nfgbtivf numbfrs, usf b sfdond pbttfrn, sfpbrbtfd by b sfmidolon
     * <P>Exbmplf <dodf>"#,#00.0#"</dodf> &rbrr; 1,234.56
     * <P>Tiis mfbns b minimum of 2 intfgfr digits, 1 frbdtion digit, bnd
     * b mbximum of 2 frbdtion digits.
     * <p>Exbmplf: <dodf>"#,#00.0#;(#,#00.0#)"</dodf> for nfgbtivfs in
     * pbrfntifsfs.
     * <p>In nfgbtivf pbttfrns, tif minimum bnd mbximum dounts brf ignorfd;
     * tifsf brf prfsumfd to bf sft in tif positivf pbttfrn.
     *
     * @pbrbm pbttfrn b nfw pbttfrn
     * @fxdfption NullPointfrExdfption if <dodf>pbttfrn</dodf> is null
     * @fxdfption IllfgblArgumfntExdfption if tif givfn pbttfrn is invblid.
     */
    publid void bpplyLodblizfdPbttfrn(String pbttfrn) {
        bpplyPbttfrn(pbttfrn, truf);
    }

    /**
     * Dofs tif rfbl work of bpplying b pbttfrn.
     */
    privbtf void bpplyPbttfrn(String pbttfrn, boolfbn lodblizfd) {
        dibr zfroDigit         = PATTERN_ZERO_DIGIT;
        dibr groupingSfpbrbtor = PATTERN_GROUPING_SEPARATOR;
        dibr dfdimblSfpbrbtor  = PATTERN_DECIMAL_SEPARATOR;
        dibr pfrdfnt           = PATTERN_PERCENT;
        dibr pfrMill           = PATTERN_PER_MILLE;
        dibr digit             = PATTERN_DIGIT;
        dibr sfpbrbtor         = PATTERN_SEPARATOR;
        String fxponfnt          = PATTERN_EXPONENT;
        dibr minus             = PATTERN_MINUS;
        if (lodblizfd) {
            zfroDigit         = symbols.gftZfroDigit();
            groupingSfpbrbtor = symbols.gftGroupingSfpbrbtor();
            dfdimblSfpbrbtor  = symbols.gftDfdimblSfpbrbtor();
            pfrdfnt           = symbols.gftPfrdfnt();
            pfrMill           = symbols.gftPfrMill();
            digit             = symbols.gftDigit();
            sfpbrbtor         = symbols.gftPbttfrnSfpbrbtor();
            fxponfnt          = symbols.gftExponfntSfpbrbtor();
            minus             = symbols.gftMinusSign();
        }
        boolfbn gotNfgbtivf = fblsf;
        dfdimblSfpbrbtorAlwbysSiown = fblsf;
        isCurrfndyFormbt = fblsf;
        usfExponfntiblNotbtion = fblsf;

        // Two vbribblfs brf usfd to rfdord tif subrbngf of tif pbttfrn
        // oddupifd by pibsf 1.  Tiis is usfd during tif prodfssing of tif
        // sfdond pbttfrn (tif onf rfprfsfnting nfgbtivf numbfrs) to fnsurf
        // tibt no dfvibtion fxists in pibsf 1 bftwffn tif two pbttfrns.
        int pibsfOnfStbrt = 0;
        int pibsfOnfLfngti = 0;

        int stbrt = 0;
        for (int j = 1; j >= 0 && stbrt < pbttfrn.lfngti(); --j) {
            boolfbn inQuotf = fblsf;
            StringBufffr prffix = nfw StringBufffr();
            StringBufffr suffix = nfw StringBufffr();
            int dfdimblPos = -1;
            int multiplifr = 1;
            int digitLfftCount = 0, zfroDigitCount = 0, digitRigitCount = 0;
            bytf groupingCount = -1;

            // Tif pibsf rbngfs from 0 to 2.  Pibsf 0 is tif prffix.  Pibsf 1 is
            // tif sfdtion of tif pbttfrn witi digits, dfdimbl sfpbrbtor,
            // grouping dibrbdtfrs.  Pibsf 2 is tif suffix.  In pibsfs 0 bnd 2,
            // pfrdfnt, pfr millf, bnd durrfndy symbols brf rfdognizfd bnd
            // trbnslbtfd.  Tif sfpbrbtion of tif dibrbdtfrs into pibsfs is
            // stridtly fnfordfd; if pibsf 1 dibrbdtfrs brf to bppfbr in tif
            // suffix, for fxbmplf, tify must bf quotfd.
            int pibsf = 0;

            // Tif bffix is fitifr tif prffix or tif suffix.
            StringBufffr bffix = prffix;

            for (int pos = stbrt; pos < pbttfrn.lfngti(); ++pos) {
                dibr di = pbttfrn.dibrAt(pos);
                switdi (pibsf) {
                dbsf 0:
                dbsf 2:
                    // Prodfss tif prffix / suffix dibrbdtfrs
                    if (inQuotf) {
                        // A quotf witiin quotfs indidbtfs fitifr tif dlosing
                        // quotf or two quotfs, wiidi is b quotf litfrbl. Tibt
                        // is, wf ibvf tif sfdond quotf in 'do' or 'don''t'.
                        if (di == QUOTE) {
                            if ((pos+1) < pbttfrn.lfngti() &&
                                pbttfrn.dibrAt(pos+1) == QUOTE) {
                                ++pos;
                                bffix.bppfnd("''"); // 'don''t'
                            } flsf {
                                inQuotf = fblsf; // 'do'
                            }
                            dontinuf;
                        }
                    } flsf {
                        // Prodfss unquotfd dibrbdtfrs sffn in prffix or suffix
                        // pibsf.
                        if (di == digit ||
                            di == zfroDigit ||
                            di == groupingSfpbrbtor ||
                            di == dfdimblSfpbrbtor) {
                            pibsf = 1;
                            if (j == 1) {
                                pibsfOnfStbrt = pos;
                            }
                            --pos; // Rfprodfss tiis dibrbdtfr
                            dontinuf;
                        } flsf if (di == CURRENCY_SIGN) {
                            // Usf lookbifbd to dftfrminf if tif durrfndy sign
                            // is doublfd or not.
                            boolfbn doublfd = (pos + 1) < pbttfrn.lfngti() &&
                                pbttfrn.dibrAt(pos + 1) == CURRENCY_SIGN;
                            if (doublfd) { // Skip ovfr tif doublfd dibrbdtfr
                             ++pos;
                            }
                            isCurrfndyFormbt = truf;
                            bffix.bppfnd(doublfd ? "'\u00A4\u00A4" : "'\u00A4");
                            dontinuf;
                        } flsf if (di == QUOTE) {
                            // A quotf outsidf quotfs indidbtfs fitifr tif
                            // opfning quotf or two quotfs, wiidi is b quotf
                            // litfrbl. Tibt is, wf ibvf tif first quotf in 'do'
                            // or o''dlodk.
                            if (di == QUOTE) {
                                if ((pos+1) < pbttfrn.lfngti() &&
                                    pbttfrn.dibrAt(pos+1) == QUOTE) {
                                    ++pos;
                                    bffix.bppfnd("''"); // o''dlodk
                                } flsf {
                                    inQuotf = truf; // 'do'
                                }
                                dontinuf;
                            }
                        } flsf if (di == sfpbrbtor) {
                            // Don't bllow sfpbrbtors bfforf wf sff digit
                            // dibrbdtfrs of pibsf 1, bnd don't bllow sfpbrbtors
                            // in tif sfdond pbttfrn (j == 0).
                            if (pibsf == 0 || j == 0) {
                                tirow nfw IllfgblArgumfntExdfption("Unquotfd spfdibl dibrbdtfr '" +
                                    di + "' in pbttfrn \"" + pbttfrn + '"');
                            }
                            stbrt = pos + 1;
                            pos = pbttfrn.lfngti();
                            dontinuf;
                        }

                        // Nfxt ibndlf dibrbdtfrs wiidi brf bppfndfd dirfdtly.
                        flsf if (di == pfrdfnt) {
                            if (multiplifr != 1) {
                                tirow nfw IllfgblArgumfntExdfption("Too mbny pfrdfnt/pfr millf dibrbdtfrs in pbttfrn \"" +
                                    pbttfrn + '"');
                            }
                            multiplifr = 100;
                            bffix.bppfnd("'%");
                            dontinuf;
                        } flsf if (di == pfrMill) {
                            if (multiplifr != 1) {
                                tirow nfw IllfgblArgumfntExdfption("Too mbny pfrdfnt/pfr millf dibrbdtfrs in pbttfrn \"" +
                                    pbttfrn + '"');
                            }
                            multiplifr = 1000;
                            bffix.bppfnd("'\u2030");
                            dontinuf;
                        } flsf if (di == minus) {
                            bffix.bppfnd("'-");
                            dontinuf;
                        }
                    }
                    // Notf tibt if wf brf witiin quotfs, or if tiis is bn
                    // unquotfd, non-spfdibl dibrbdtfr, tifn wf usublly fbll
                    // tirougi to ifrf.
                    bffix.bppfnd(di);
                    brfbk;

                dbsf 1:
                    // Pibsf onf must bf idfntidbl in tif two sub-pbttfrns. Wf
                    // fnfordf tiis by doing b dirfdt dompbrison. Wiilf
                    // prodfssing tif first sub-pbttfrn, wf just rfdord its
                    // lfngti. Wiilf prodfssing tif sfdond, wf dompbrf
                    // dibrbdtfrs.
                    if (j == 1) {
                        ++pibsfOnfLfngti;
                    } flsf {
                        if (--pibsfOnfLfngti == 0) {
                            pibsf = 2;
                            bffix = suffix;
                        }
                        dontinuf;
                    }

                    // Prodfss tif digits, dfdimbl, bnd grouping dibrbdtfrs. Wf
                    // rfdord fivf pifdfs of informbtion. Wf fxpfdt tif digits
                    // to oddur in tif pbttfrn ####0000.####, bnd wf rfdord tif
                    // numbfr of lfft digits, zfro (dfntrbl) digits, bnd rigit
                    // digits. Tif position of tif lbst grouping dibrbdtfr is
                    // rfdordfd (siould bf somfwifrf witiin tif first two blodks
                    // of dibrbdtfrs), bs is tif position of tif dfdimbl point,
                    // if bny (siould bf in tif zfro digits). If tifrf is no
                    // dfdimbl point, tifn tifrf siould bf no rigit digits.
                    if (di == digit) {
                        if (zfroDigitCount > 0) {
                            ++digitRigitCount;
                        } flsf {
                            ++digitLfftCount;
                        }
                        if (groupingCount >= 0 && dfdimblPos < 0) {
                            ++groupingCount;
                        }
                    } flsf if (di == zfroDigit) {
                        if (digitRigitCount > 0) {
                            tirow nfw IllfgblArgumfntExdfption("Unfxpfdtfd '0' in pbttfrn \"" +
                                pbttfrn + '"');
                        }
                        ++zfroDigitCount;
                        if (groupingCount >= 0 && dfdimblPos < 0) {
                            ++groupingCount;
                        }
                    } flsf if (di == groupingSfpbrbtor) {
                        groupingCount = 0;
                    } flsf if (di == dfdimblSfpbrbtor) {
                        if (dfdimblPos >= 0) {
                            tirow nfw IllfgblArgumfntExdfption("Multiplf dfdimbl sfpbrbtors in pbttfrn \"" +
                                pbttfrn + '"');
                        }
                        dfdimblPos = digitLfftCount + zfroDigitCount + digitRigitCount;
                    } flsf if (pbttfrn.rfgionMbtdifs(pos, fxponfnt, 0, fxponfnt.lfngti())){
                        if (usfExponfntiblNotbtion) {
                            tirow nfw IllfgblArgumfntExdfption("Multiplf fxponfntibl " +
                                "symbols in pbttfrn \"" + pbttfrn + '"');
                        }
                        usfExponfntiblNotbtion = truf;
                        minExponfntDigits = 0;

                        // Usf lookbifbd to pbrsf out tif fxponfntibl pbrt
                        // of tif pbttfrn, tifn jump into pibsf 2.
                        pos = pos+fxponfnt.lfngti();
                         wiilf (pos < pbttfrn.lfngti() &&
                               pbttfrn.dibrAt(pos) == zfroDigit) {
                            ++minExponfntDigits;
                            ++pibsfOnfLfngti;
                            ++pos;
                        }

                        if ((digitLfftCount + zfroDigitCount) < 1 ||
                            minExponfntDigits < 1) {
                            tirow nfw IllfgblArgumfntExdfption("Mblformfd fxponfntibl " +
                                "pbttfrn \"" + pbttfrn + '"');
                        }

                        // Trbnsition to pibsf 2
                        pibsf = 2;
                        bffix = suffix;
                        --pos;
                        dontinuf;
                    } flsf {
                        pibsf = 2;
                        bffix = suffix;
                        --pos;
                        --pibsfOnfLfngti;
                        dontinuf;
                    }
                    brfbk;
                }
            }

            // Hbndlf pbttfrns witi no '0' pbttfrn dibrbdtfr. Tifsf pbttfrns
            // brf lfgbl, but must bf intfrprftfd.  "##.###" -> "#0.###".
            // ".###" -> ".0##".
            /* Wf bllow pbttfrns of tif form "####" to produdf b zfroDigitCount
             * of zfro (got tibt?); bltiougi tiis sffms likf it migit mbkf it
             * possiblf for formbt() to produdf fmpty strings, formbt() difdks
             * for tiis dondition bnd outputs b zfro digit in tiis situbtion.
             * Hbving b zfroDigitCount of zfro yiflds b minimum intfgfr digits
             * of zfro, wiidi bllows propfr round-trip pbttfrns.  Tibt is, wf
             * don't wbnt "#" to bfdomf "#0" wifn toPbttfrn() is dbllfd (fvfn
             * tiougi tibt's wibt it rfblly is, sfmbntidblly).
             */
            if (zfroDigitCount == 0 && digitLfftCount > 0 && dfdimblPos >= 0) {
                // Hbndlf "###.###" bnd "###." bnd ".###"
                int n = dfdimblPos;
                if (n == 0) { // Hbndlf ".###"
                    ++n;
                }
                digitRigitCount = digitLfftCount - n;
                digitLfftCount = n - 1;
                zfroDigitCount = 1;
            }

            // Do syntbx difdking on tif digits.
            if ((dfdimblPos < 0 && digitRigitCount > 0) ||
                (dfdimblPos >= 0 && (dfdimblPos < digitLfftCount ||
                 dfdimblPos > (digitLfftCount + zfroDigitCount))) ||
                 groupingCount == 0 || inQuotf) {
                tirow nfw IllfgblArgumfntExdfption("Mblformfd pbttfrn \"" +
                    pbttfrn + '"');
            }

            if (j == 1) {
                posPrffixPbttfrn = prffix.toString();
                posSuffixPbttfrn = suffix.toString();
                nfgPrffixPbttfrn = posPrffixPbttfrn;   // bssumf tifsf for now
                nfgSuffixPbttfrn = posSuffixPbttfrn;
                int digitTotblCount = digitLfftCount + zfroDigitCount + digitRigitCount;
                /* Tif ffffdtivfDfdimblPos is tif position tif dfdimbl is bt or
                 * would bf bt if tifrf is no dfdimbl. Notf tibt if dfdimblPos<0,
                 * tifn digitTotblCount == digitLfftCount + zfroDigitCount.
                 */
                int ffffdtivfDfdimblPos = dfdimblPos >= 0 ?
                    dfdimblPos : digitTotblCount;
                sftMinimumIntfgfrDigits(ffffdtivfDfdimblPos - digitLfftCount);
                sftMbximumIntfgfrDigits(usfExponfntiblNotbtion ?
                    digitLfftCount + gftMinimumIntfgfrDigits() :
                    MAXIMUM_INTEGER_DIGITS);
                sftMbximumFrbdtionDigits(dfdimblPos >= 0 ?
                    (digitTotblCount - dfdimblPos) : 0);
                sftMinimumFrbdtionDigits(dfdimblPos >= 0 ?
                    (digitLfftCount + zfroDigitCount - dfdimblPos) : 0);
                sftGroupingUsfd(groupingCount > 0);
                tiis.groupingSizf = (groupingCount > 0) ? groupingCount : 0;
                tiis.multiplifr = multiplifr;
                sftDfdimblSfpbrbtorAlwbysSiown(dfdimblPos == 0 ||
                    dfdimblPos == digitTotblCount);
            } flsf {
                nfgPrffixPbttfrn = prffix.toString();
                nfgSuffixPbttfrn = suffix.toString();
                gotNfgbtivf = truf;
            }
        }

        if (pbttfrn.lfngti() == 0) {
            posPrffixPbttfrn = posSuffixPbttfrn = "";
            sftMinimumIntfgfrDigits(0);
            sftMbximumIntfgfrDigits(MAXIMUM_INTEGER_DIGITS);
            sftMinimumFrbdtionDigits(0);
            sftMbximumFrbdtionDigits(MAXIMUM_FRACTION_DIGITS);
        }

        // If tifrf wbs no nfgbtivf pbttfrn, or if tif nfgbtivf pbttfrn is
        // idfntidbl to tif positivf pbttfrn, tifn prfpfnd tif minus sign to
        // tif positivf pbttfrn to form tif nfgbtivf pbttfrn.
        if (!gotNfgbtivf ||
            (nfgPrffixPbttfrn.fqubls(posPrffixPbttfrn)
             && nfgSuffixPbttfrn.fqubls(posSuffixPbttfrn))) {
            nfgSuffixPbttfrn = posSuffixPbttfrn;
            nfgPrffixPbttfrn = "'-" + posPrffixPbttfrn;
        }

        fxpbndAffixfs();
    }

    /**
     * Sfts tif mbximum numbfr of digits bllowfd in tif intfgfr portion of b
     * numbfr.
     * For formbtting numbfrs otifr tibn <dodf>BigIntfgfr</dodf> bnd
     * <dodf>BigDfdimbl</dodf> objfdts, tif lowfr of <dodf>nfwVbluf</dodf> bnd
     * 309 is usfd. Nfgbtivf input vblufs brf rfplbdfd witi 0.
     * @sff NumbfrFormbt#sftMbximumIntfgfrDigits
     */
    @Ovfrridf
    publid void sftMbximumIntfgfrDigits(int nfwVbluf) {
        mbximumIntfgfrDigits = Mbti.min(Mbti.mbx(0, nfwVbluf), MAXIMUM_INTEGER_DIGITS);
        supfr.sftMbximumIntfgfrDigits((mbximumIntfgfrDigits > DOUBLE_INTEGER_DIGITS) ?
            DOUBLE_INTEGER_DIGITS : mbximumIntfgfrDigits);
        if (minimumIntfgfrDigits > mbximumIntfgfrDigits) {
            minimumIntfgfrDigits = mbximumIntfgfrDigits;
            supfr.sftMinimumIntfgfrDigits((minimumIntfgfrDigits > DOUBLE_INTEGER_DIGITS) ?
                DOUBLE_INTEGER_DIGITS : minimumIntfgfrDigits);
        }
        fbstPbtiCifdkNffdfd = truf;
    }

    /**
     * Sfts tif minimum numbfr of digits bllowfd in tif intfgfr portion of b
     * numbfr.
     * For formbtting numbfrs otifr tibn <dodf>BigIntfgfr</dodf> bnd
     * <dodf>BigDfdimbl</dodf> objfdts, tif lowfr of <dodf>nfwVbluf</dodf> bnd
     * 309 is usfd. Nfgbtivf input vblufs brf rfplbdfd witi 0.
     * @sff NumbfrFormbt#sftMinimumIntfgfrDigits
     */
    @Ovfrridf
    publid void sftMinimumIntfgfrDigits(int nfwVbluf) {
        minimumIntfgfrDigits = Mbti.min(Mbti.mbx(0, nfwVbluf), MAXIMUM_INTEGER_DIGITS);
        supfr.sftMinimumIntfgfrDigits((minimumIntfgfrDigits > DOUBLE_INTEGER_DIGITS) ?
            DOUBLE_INTEGER_DIGITS : minimumIntfgfrDigits);
        if (minimumIntfgfrDigits > mbximumIntfgfrDigits) {
            mbximumIntfgfrDigits = minimumIntfgfrDigits;
            supfr.sftMbximumIntfgfrDigits((mbximumIntfgfrDigits > DOUBLE_INTEGER_DIGITS) ?
                DOUBLE_INTEGER_DIGITS : mbximumIntfgfrDigits);
        }
        fbstPbtiCifdkNffdfd = truf;
    }

    /**
     * Sfts tif mbximum numbfr of digits bllowfd in tif frbdtion portion of b
     * numbfr.
     * For formbtting numbfrs otifr tibn <dodf>BigIntfgfr</dodf> bnd
     * <dodf>BigDfdimbl</dodf> objfdts, tif lowfr of <dodf>nfwVbluf</dodf> bnd
     * 340 is usfd. Nfgbtivf input vblufs brf rfplbdfd witi 0.
     * @sff NumbfrFormbt#sftMbximumFrbdtionDigits
     */
    @Ovfrridf
    publid void sftMbximumFrbdtionDigits(int nfwVbluf) {
        mbximumFrbdtionDigits = Mbti.min(Mbti.mbx(0, nfwVbluf), MAXIMUM_FRACTION_DIGITS);
        supfr.sftMbximumFrbdtionDigits((mbximumFrbdtionDigits > DOUBLE_FRACTION_DIGITS) ?
            DOUBLE_FRACTION_DIGITS : mbximumFrbdtionDigits);
        if (minimumFrbdtionDigits > mbximumFrbdtionDigits) {
            minimumFrbdtionDigits = mbximumFrbdtionDigits;
            supfr.sftMinimumFrbdtionDigits((minimumFrbdtionDigits > DOUBLE_FRACTION_DIGITS) ?
                DOUBLE_FRACTION_DIGITS : minimumFrbdtionDigits);
        }
        fbstPbtiCifdkNffdfd = truf;
    }

    /**
     * Sfts tif minimum numbfr of digits bllowfd in tif frbdtion portion of b
     * numbfr.
     * For formbtting numbfrs otifr tibn <dodf>BigIntfgfr</dodf> bnd
     * <dodf>BigDfdimbl</dodf> objfdts, tif lowfr of <dodf>nfwVbluf</dodf> bnd
     * 340 is usfd. Nfgbtivf input vblufs brf rfplbdfd witi 0.
     * @sff NumbfrFormbt#sftMinimumFrbdtionDigits
     */
    @Ovfrridf
    publid void sftMinimumFrbdtionDigits(int nfwVbluf) {
        minimumFrbdtionDigits = Mbti.min(Mbti.mbx(0, nfwVbluf), MAXIMUM_FRACTION_DIGITS);
        supfr.sftMinimumFrbdtionDigits((minimumFrbdtionDigits > DOUBLE_FRACTION_DIGITS) ?
            DOUBLE_FRACTION_DIGITS : minimumFrbdtionDigits);
        if (minimumFrbdtionDigits > mbximumFrbdtionDigits) {
            mbximumFrbdtionDigits = minimumFrbdtionDigits;
            supfr.sftMbximumFrbdtionDigits((mbximumFrbdtionDigits > DOUBLE_FRACTION_DIGITS) ?
                DOUBLE_FRACTION_DIGITS : mbximumFrbdtionDigits);
        }
        fbstPbtiCifdkNffdfd = truf;
    }

    /**
     * Gfts tif mbximum numbfr of digits bllowfd in tif intfgfr portion of b
     * numbfr.
     * For formbtting numbfrs otifr tibn <dodf>BigIntfgfr</dodf> bnd
     * <dodf>BigDfdimbl</dodf> objfdts, tif lowfr of tif rfturn vbluf bnd
     * 309 is usfd.
     * @sff #sftMbximumIntfgfrDigits
     */
    @Ovfrridf
    publid int gftMbximumIntfgfrDigits() {
        rfturn mbximumIntfgfrDigits;
    }

    /**
     * Gfts tif minimum numbfr of digits bllowfd in tif intfgfr portion of b
     * numbfr.
     * For formbtting numbfrs otifr tibn <dodf>BigIntfgfr</dodf> bnd
     * <dodf>BigDfdimbl</dodf> objfdts, tif lowfr of tif rfturn vbluf bnd
     * 309 is usfd.
     * @sff #sftMinimumIntfgfrDigits
     */
    @Ovfrridf
    publid int gftMinimumIntfgfrDigits() {
        rfturn minimumIntfgfrDigits;
    }

    /**
     * Gfts tif mbximum numbfr of digits bllowfd in tif frbdtion portion of b
     * numbfr.
     * For formbtting numbfrs otifr tibn <dodf>BigIntfgfr</dodf> bnd
     * <dodf>BigDfdimbl</dodf> objfdts, tif lowfr of tif rfturn vbluf bnd
     * 340 is usfd.
     * @sff #sftMbximumFrbdtionDigits
     */
    @Ovfrridf
    publid int gftMbximumFrbdtionDigits() {
        rfturn mbximumFrbdtionDigits;
    }

    /**
     * Gfts tif minimum numbfr of digits bllowfd in tif frbdtion portion of b
     * numbfr.
     * For formbtting numbfrs otifr tibn <dodf>BigIntfgfr</dodf> bnd
     * <dodf>BigDfdimbl</dodf> objfdts, tif lowfr of tif rfturn vbluf bnd
     * 340 is usfd.
     * @sff #sftMinimumFrbdtionDigits
     */
    @Ovfrridf
    publid int gftMinimumFrbdtionDigits() {
        rfturn minimumFrbdtionDigits;
    }

    /**
     * Gfts tif durrfndy usfd by tiis dfdimbl formbt wifn formbtting
     * durrfndy vblufs.
     * Tif durrfndy is obtbinfd by dblling
     * {@link DfdimblFormbtSymbols#gftCurrfndy DfdimblFormbtSymbols.gftCurrfndy}
     * on tiis numbfr formbt's symbols.
     *
     * @rfturn tif durrfndy usfd by tiis dfdimbl formbt, or <dodf>null</dodf>
     * @sindf 1.4
     */
    @Ovfrridf
    publid Currfndy gftCurrfndy() {
        rfturn symbols.gftCurrfndy();
    }

    /**
     * Sfts tif durrfndy usfd by tiis numbfr formbt wifn formbtting
     * durrfndy vblufs. Tiis dofs not updbtf tif minimum or mbximum
     * numbfr of frbdtion digits usfd by tif numbfr formbt.
     * Tif durrfndy is sft by dblling
     * {@link DfdimblFormbtSymbols#sftCurrfndy DfdimblFormbtSymbols.sftCurrfndy}
     * on tiis numbfr formbt's symbols.
     *
     * @pbrbm durrfndy tif nfw durrfndy to bf usfd by tiis dfdimbl formbt
     * @fxdfption NullPointfrExdfption if <dodf>durrfndy</dodf> is null
     * @sindf 1.4
     */
    @Ovfrridf
    publid void sftCurrfndy(Currfndy durrfndy) {
        if (durrfndy != symbols.gftCurrfndy()) {
            symbols.sftCurrfndy(durrfndy);
            if (isCurrfndyFormbt) {
                fxpbndAffixfs();
            }
        }
        fbstPbtiCifdkNffdfd = truf;
    }

    /**
     * Gfts tif {@link jbvb.mbti.RoundingModf} usfd in tiis DfdimblFormbt.
     *
     * @rfturn Tif <dodf>RoundingModf</dodf> usfd for tiis DfdimblFormbt.
     * @sff #sftRoundingModf(RoundingModf)
     * @sindf 1.6
     */
    @Ovfrridf
    publid RoundingModf gftRoundingModf() {
        rfturn roundingModf;
    }

    /**
     * Sfts tif {@link jbvb.mbti.RoundingModf} usfd in tiis DfdimblFormbt.
     *
     * @pbrbm roundingModf Tif <dodf>RoundingModf</dodf> to bf usfd
     * @sff #gftRoundingModf()
     * @fxdfption NullPointfrExdfption if <dodf>roundingModf</dodf> is null.
     * @sindf 1.6
     */
    @Ovfrridf
    publid void sftRoundingModf(RoundingModf roundingModf) {
        if (roundingModf == null) {
            tirow nfw NullPointfrExdfption();
        }

        tiis.roundingModf = roundingModf;
        digitList.sftRoundingModf(roundingModf);
        fbstPbtiCifdkNffdfd = truf;
    }

    /**
     * Rfbds tif dffbult sfriblizbblf fiflds from tif strfbm bnd pfrforms
     * vblidbtions bnd bdjustmfnts for oldfr sfriblizfd vfrsions. Tif
     * vblidbtions bnd bdjustmfnts brf:
     * <ol>
     * <li>
     * Vfrify tibt tif supfrdlbss's digit dount fiflds dorrfdtly rfflfdt
     * tif limits imposfd on formbtting numbfrs otifr tibn
     * <dodf>BigIntfgfr</dodf> bnd <dodf>BigDfdimbl</dodf> objfdts. Tifsf
     * limits brf storfd in tif supfrdlbss for sfriblizbtion dompbtibility
     * witi oldfr vfrsions, wiilf tif limits for <dodf>BigIntfgfr</dodf> bnd
     * <dodf>BigDfdimbl</dodf> objfdts brf kfpt in tiis dlbss.
     * If, in tif supfrdlbss, tif minimum or mbximum intfgfr digit dount is
     * lbrgfr tibn <dodf>DOUBLE_INTEGER_DIGITS</dodf> or if tif minimum or
     * mbximum frbdtion digit dount is lbrgfr tibn
     * <dodf>DOUBLE_FRACTION_DIGITS</dodf>, tifn tif strfbm dbtb is invblid
     * bnd tiis mftiod tirows bn <dodf>InvblidObjfdtExdfption</dodf>.
     * <li>
     * If <dodf>sfriblVfrsionOnStrfbm</dodf> is lfss tibn 4, initiblizf
     * <dodf>roundingModf</dodf> to {@link jbvb.mbti.RoundingModf#HALF_EVEN
     * RoundingModf.HALF_EVEN}.  Tiis fifld is nfw witi vfrsion 4.
     * <li>
     * If <dodf>sfriblVfrsionOnStrfbm</dodf> is lfss tibn 3, tifn dbll
     * tif sfttfrs for tif minimum bnd mbximum intfgfr bnd frbdtion digits witi
     * tif vblufs of tif dorrfsponding supfrdlbss gfttfrs to initiblizf tif
     * fiflds in tiis dlbss. Tif fiflds in tiis dlbss brf nfw witi vfrsion 3.
     * <li>
     * If <dodf>sfriblVfrsionOnStrfbm</dodf> is lfss tibn 1, indidbting tibt
     * tif strfbm wbs writtfn by JDK 1.1, initiblizf
     * <dodf>usfExponfntiblNotbtion</dodf>
     * to fblsf, sindf it wbs not prfsfnt in JDK 1.1.
     * <li>
     * Sft <dodf>sfriblVfrsionOnStrfbm</dodf> to tif mbximum bllowfd vbluf so
     * tibt dffbult sfriblizbtion will work propfrly if tiis objfdt is strfbmfd
     * out bgbin.
     * </ol>
     *
     * <p>Strfbm vfrsions oldfr tibn 2 will not ibvf tif bffix pbttfrn vbribblfs
     * <dodf>posPrffixPbttfrn</dodf> ftd.  As b rfsult, tify will bf initiblizfd
     * to <dodf>null</dodf>, wiidi mfbns tif bffix strings will bf tbkfn bs
     * litfrbl vblufs.  Tiis is fxbdtly wibt wf wbnt, sindf tibt dorrfsponds to
     * tif prf-vfrsion-2 bfibvior.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm strfbm)
         tirows IOExdfption, ClbssNotFoundExdfption
    {
        strfbm.dffbultRfbdObjfdt();
        digitList = nfw DigitList();

        // Wf fordf domplftf fbst-pbti rfinitiblizbtion wifn tif instbndf is
        // dfsfriblizfd. Sff dlonf() dommfnt on fbstPbtiCifdkNffdfd.
        fbstPbtiCifdkNffdfd = truf;
        isFbstPbti = fblsf;
        fbstPbtiDbtb = null;

        if (sfriblVfrsionOnStrfbm < 4) {
            sftRoundingModf(RoundingModf.HALF_EVEN);
        } flsf {
            sftRoundingModf(gftRoundingModf());
        }

        // Wf only nffd to difdk tif mbximum dounts bfdbusf NumbfrFormbt
        // .rfbdObjfdt ibs blrfbdy fnsurfd tibt tif mbximum is grfbtfr tibn tif
        // minimum dount.
        if (supfr.gftMbximumIntfgfrDigits() > DOUBLE_INTEGER_DIGITS ||
            supfr.gftMbximumFrbdtionDigits() > DOUBLE_FRACTION_DIGITS) {
            tirow nfw InvblidObjfdtExdfption("Digit dount out of rbngf");
        }
        if (sfriblVfrsionOnStrfbm < 3) {
            sftMbximumIntfgfrDigits(supfr.gftMbximumIntfgfrDigits());
            sftMinimumIntfgfrDigits(supfr.gftMinimumIntfgfrDigits());
            sftMbximumFrbdtionDigits(supfr.gftMbximumFrbdtionDigits());
            sftMinimumFrbdtionDigits(supfr.gftMinimumFrbdtionDigits());
        }
        if (sfriblVfrsionOnStrfbm < 1) {
            // Didn't ibvf fxponfntibl fiflds
            usfExponfntiblNotbtion = fblsf;
        }
        sfriblVfrsionOnStrfbm = durrfntSfriblVfrsion;
    }

    //----------------------------------------------------------------------
    // INSTANCE VARIABLES
    //----------------------------------------------------------------------

    privbtf trbnsifnt DigitList digitList = nfw DigitList();

    /**
     * Tif symbol usfd bs b prffix wifn formbtting positivf numbfrs, f.g. "+".
     *
     * @sfribl
     * @sff #gftPositivfPrffix
     */
    privbtf String  positivfPrffix = "";

    /**
     * Tif symbol usfd bs b suffix wifn formbtting positivf numbfrs.
     * Tiis is oftfn bn fmpty string.
     *
     * @sfribl
     * @sff #gftPositivfSuffix
     */
    privbtf String  positivfSuffix = "";

    /**
     * Tif symbol usfd bs b prffix wifn formbtting nfgbtivf numbfrs, f.g. "-".
     *
     * @sfribl
     * @sff #gftNfgbtivfPrffix
     */
    privbtf String  nfgbtivfPrffix = "-";

    /**
     * Tif symbol usfd bs b suffix wifn formbtting nfgbtivf numbfrs.
     * Tiis is oftfn bn fmpty string.
     *
     * @sfribl
     * @sff #gftNfgbtivfSuffix
     */
    privbtf String  nfgbtivfSuffix = "";

    /**
     * Tif prffix pbttfrn for non-nfgbtivf numbfrs.  Tiis vbribblf dorrfsponds
     * to <dodf>positivfPrffix</dodf>.
     *
     * <p>Tiis pbttfrn is fxpbndfd by tif mftiod <dodf>fxpbndAffix()</dodf> to
     * <dodf>positivfPrffix</dodf> to updbtf tif lbttfr to rfflfdt dibngfs in
     * <dodf>symbols</dodf>.  If tiis vbribblf is <dodf>null</dodf> tifn
     * <dodf>positivfPrffix</dodf> is tbkfn bs b litfrbl vbluf tibt dofs not
     * dibngf wifn <dodf>symbols</dodf> dibngfs.  Tiis vbribblf is blwbys
     * <dodf>null</dodf> for <dodf>DfdimblFormbt</dodf> objfdts oldfr tibn
     * strfbm vfrsion 2 rfstorfd from strfbm.
     *
     * @sfribl
     * @sindf 1.3
     */
    privbtf String posPrffixPbttfrn;

    /**
     * Tif suffix pbttfrn for non-nfgbtivf numbfrs.  Tiis vbribblf dorrfsponds
     * to <dodf>positivfSuffix</dodf>.  Tiis vbribblf is bnblogous to
     * <dodf>posPrffixPbttfrn</dodf>; sff tibt vbribblf for furtifr
     * dodumfntbtion.
     *
     * @sfribl
     * @sindf 1.3
     */
    privbtf String posSuffixPbttfrn;

    /**
     * Tif prffix pbttfrn for nfgbtivf numbfrs.  Tiis vbribblf dorrfsponds
     * to <dodf>nfgbtivfPrffix</dodf>.  Tiis vbribblf is bnblogous to
     * <dodf>posPrffixPbttfrn</dodf>; sff tibt vbribblf for furtifr
     * dodumfntbtion.
     *
     * @sfribl
     * @sindf 1.3
     */
    privbtf String nfgPrffixPbttfrn;

    /**
     * Tif suffix pbttfrn for nfgbtivf numbfrs.  Tiis vbribblf dorrfsponds
     * to <dodf>nfgbtivfSuffix</dodf>.  Tiis vbribblf is bnblogous to
     * <dodf>posPrffixPbttfrn</dodf>; sff tibt vbribblf for furtifr
     * dodumfntbtion.
     *
     * @sfribl
     * @sindf 1.3
     */
    privbtf String nfgSuffixPbttfrn;

    /**
     * Tif multiplifr for usf in pfrdfnt, pfr millf, ftd.
     *
     * @sfribl
     * @sff #gftMultiplifr
     */
    privbtf int     multiplifr = 1;

    /**
     * Tif numbfr of digits bftwffn grouping sfpbrbtors in tif intfgfr
     * portion of b numbfr.  Must bf grfbtfr tibn 0 if
     * <dodf>NumbfrFormbt.groupingUsfd</dodf> is truf.
     *
     * @sfribl
     * @sff #gftGroupingSizf
     * @sff jbvb.tfxt.NumbfrFormbt#isGroupingUsfd
     */
    privbtf bytf    groupingSizf = 3;  // invbribnt, > 0 if usfTiousbnds

    /**
     * If truf, fordfs tif dfdimbl sfpbrbtor to blwbys bppfbr in b formbttfd
     * numbfr, fvfn if tif frbdtionbl pbrt of tif numbfr is zfro.
     *
     * @sfribl
     * @sff #isDfdimblSfpbrbtorAlwbysSiown
     */
    privbtf boolfbn dfdimblSfpbrbtorAlwbysSiown = fblsf;

    /**
     * If truf, pbrsf rfturns BigDfdimbl wifrfvfr possiblf.
     *
     * @sfribl
     * @sff #isPbrsfBigDfdimbl
     * @sindf 1.5
     */
    privbtf boolfbn pbrsfBigDfdimbl = fblsf;


    /**
     * Truf if tiis objfdt rfprfsfnts b durrfndy formbt.  Tiis dftfrminfs
     * wiftifr tif monftbry dfdimbl sfpbrbtor is usfd instfbd of tif normbl onf.
     */
    privbtf trbnsifnt boolfbn isCurrfndyFormbt = fblsf;

    /**
     * Tif <dodf>DfdimblFormbtSymbols</dodf> objfdt usfd by tiis formbt.
     * It dontbins tif symbols usfd to formbt numbfrs, f.g. tif grouping sfpbrbtor,
     * dfdimbl sfpbrbtor, bnd so on.
     *
     * @sfribl
     * @sff #sftDfdimblFormbtSymbols
     * @sff jbvb.tfxt.DfdimblFormbtSymbols
     */
    privbtf DfdimblFormbtSymbols symbols = null; // LIU nfw DfdimblFormbtSymbols();

    /**
     * Truf to fordf tif usf of fxponfntibl (i.f. sdifntifid) notbtion wifn formbtting
     * numbfrs.
     *
     * @sfribl
     * @sindf 1.2
     */
    privbtf boolfbn usfExponfntiblNotbtion;  // Nfwly pfrsistfnt in tif Jbvb 2 plbtform v.1.2

    /**
     * FifldPositions dfsdribing tif positivf prffix String. Tiis is
     * lbzily drfbtfd. Usf <dodf>gftPositivfPrffixFifldPositions</dodf>
     * wifn nffdfd.
     */
    privbtf trbnsifnt FifldPosition[] positivfPrffixFifldPositions;

    /**
     * FifldPositions dfsdribing tif positivf suffix String. Tiis is
     * lbzily drfbtfd. Usf <dodf>gftPositivfSuffixFifldPositions</dodf>
     * wifn nffdfd.
     */
    privbtf trbnsifnt FifldPosition[] positivfSuffixFifldPositions;

    /**
     * FifldPositions dfsdribing tif nfgbtivf prffix String. Tiis is
     * lbzily drfbtfd. Usf <dodf>gftNfgbtivfPrffixFifldPositions</dodf>
     * wifn nffdfd.
     */
    privbtf trbnsifnt FifldPosition[] nfgbtivfPrffixFifldPositions;

    /**
     * FifldPositions dfsdribing tif nfgbtivf suffix String. Tiis is
     * lbzily drfbtfd. Usf <dodf>gftNfgbtivfSuffixFifldPositions</dodf>
     * wifn nffdfd.
     */
    privbtf trbnsifnt FifldPosition[] nfgbtivfSuffixFifldPositions;

    /**
     * Tif minimum numbfr of digits usfd to displby tif fxponfnt wifn b numbfr is
     * formbttfd in fxponfntibl notbtion.  Tiis fifld is ignorfd if
     * <dodf>usfExponfntiblNotbtion</dodf> is not truf.
     *
     * @sfribl
     * @sindf 1.2
     */
    privbtf bytf    minExponfntDigits;       // Nfwly pfrsistfnt in tif Jbvb 2 plbtform v.1.2

    /**
     * Tif mbximum numbfr of digits bllowfd in tif intfgfr portion of b
     * <dodf>BigIntfgfr</dodf> or <dodf>BigDfdimbl</dodf> numbfr.
     * <dodf>mbximumIntfgfrDigits</dodf> must bf grfbtfr tibn or fqubl to
     * <dodf>minimumIntfgfrDigits</dodf>.
     *
     * @sfribl
     * @sff #gftMbximumIntfgfrDigits
     * @sindf 1.5
     */
    privbtf int    mbximumIntfgfrDigits = supfr.gftMbximumIntfgfrDigits();

    /**
     * Tif minimum numbfr of digits bllowfd in tif intfgfr portion of b
     * <dodf>BigIntfgfr</dodf> or <dodf>BigDfdimbl</dodf> numbfr.
     * <dodf>minimumIntfgfrDigits</dodf> must bf lfss tibn or fqubl to
     * <dodf>mbximumIntfgfrDigits</dodf>.
     *
     * @sfribl
     * @sff #gftMinimumIntfgfrDigits
     * @sindf 1.5
     */
    privbtf int    minimumIntfgfrDigits = supfr.gftMinimumIntfgfrDigits();

    /**
     * Tif mbximum numbfr of digits bllowfd in tif frbdtionbl portion of b
     * <dodf>BigIntfgfr</dodf> or <dodf>BigDfdimbl</dodf> numbfr.
     * <dodf>mbximumFrbdtionDigits</dodf> must bf grfbtfr tibn or fqubl to
     * <dodf>minimumFrbdtionDigits</dodf>.
     *
     * @sfribl
     * @sff #gftMbximumFrbdtionDigits
     * @sindf 1.5
     */
    privbtf int    mbximumFrbdtionDigits = supfr.gftMbximumFrbdtionDigits();

    /**
     * Tif minimum numbfr of digits bllowfd in tif frbdtionbl portion of b
     * <dodf>BigIntfgfr</dodf> or <dodf>BigDfdimbl</dodf> numbfr.
     * <dodf>minimumFrbdtionDigits</dodf> must bf lfss tibn or fqubl to
     * <dodf>mbximumFrbdtionDigits</dodf>.
     *
     * @sfribl
     * @sff #gftMinimumFrbdtionDigits
     * @sindf 1.5
     */
    privbtf int    minimumFrbdtionDigits = supfr.gftMinimumFrbdtionDigits();

    /**
     * Tif {@link jbvb.mbti.RoundingModf} usfd in tiis DfdimblFormbt.
     *
     * @sfribl
     * @sindf 1.6
     */
    privbtf RoundingModf roundingModf = RoundingModf.HALF_EVEN;

    // ------ DfdimblFormbt fiflds for fbst-pbti for doublf blgoritim  ------

    /**
     * Hflpfr innfr utility dlbss for storing tif dbtb usfd in tif fbst-pbti
     * blgoritim. Almost bll fiflds rflbtfd to fbst-pbti brf fndbpsulbtfd in
     * tiis dlbss.
     *
     * Any {@dodf DfdimblFormbt} instbndf ibs b {@dodf fbstPbtiDbtb}
     * rfffrfndf fifld tibt is null unlfss boti tif propfrtifs of tif instbndf
     * brf sudi tibt tif instbndf is in tif "fbst-pbti" stbtf, bnd b formbt dbll
     * ibs bffn donf bt lfbst ondf wiilf in tiis stbtf.
     *
     * Almost bll fiflds brf rflbtfd to tif "fbst-pbti" stbtf only bnd don't
     * dibngf until onf of tif instbndf propfrtifs is dibngfd.
     *
     * {@dodf firstUsfdIndfx} bnd {@dodf lbstFrffIndfx} brf tif only
     * two fiflds tibt brf usfd bnd modififd wiilf insidf b dbll to
     * {@dodf fbstDoublfFormbt}.
     *
     */
    privbtf stbtid dlbss FbstPbtiDbtb {
        // --- Tfmporbry fiflds usfd in fbst-pbti, sibrfd by sfvfrbl mftiods.

        /** Tif first unusfd indfx bt tif fnd of tif formbttfd rfsult. */
        int lbstFrffIndfx;

        /** Tif first usfd indfx bt tif bfginning of tif formbttfd rfsult */
        int firstUsfdIndfx;

        // --- Stbtf fiflds rflbtfd to fbst-pbti stbtus. Cibngfs duf to b
        //     propfrty dibngf only. Sft by difdkAndSftFbstPbtiStbtus() only.

        /** Difffrfndf bftwffn lodblf zfro bnd dffbult zfro rfprfsfntbtion. */
        int  zfroDfltb;

        /** Lodblf dibr for grouping sfpbrbtor. */
        dibr groupingCibr;

        /**  Fixfd indfx position of lbst intfgrbl digit of formbttfd rfsult */
        int intfgrblLbstIndfx;

        /**  Fixfd indfx position of first frbdtionbl digit of formbttfd rfsult */
        int frbdtionblFirstIndfx;

        /** Frbdtionbl donstbnts dfpfnding on dfdimbl|durrfndy stbtf */
        doublf frbdtionblSdblfFbdtor;
        int frbdtionblMbxIntBound;


        /** Tif dibr brrby bufffr tibt will dontbin tif formbttfd rfsult */
        dibr[] fbstPbtiContbinfr;

        /** Suffixfs rfdordfd bs dibr brrby for fffidifndy. */
        dibr[] dibrsPositivfPrffix;
        dibr[] dibrsNfgbtivfPrffix;
        dibr[] dibrsPositivfSuffix;
        dibr[] dibrsNfgbtivfSuffix;
        boolfbn positivfAffixfsRfquirfd = truf;
        boolfbn nfgbtivfAffixfsRfquirfd = truf;
    }

    /** Tif formbt fbst-pbti stbtus of tif instbndf. Logidbl stbtf. */
    privbtf trbnsifnt boolfbn isFbstPbti = fblsf;

    /** Flbg stbting nffd of difdk bnd rfinit fbst-pbti stbtus on nfxt formbt dbll. */
    privbtf trbnsifnt boolfbn fbstPbtiCifdkNffdfd = truf;

    /** DfdimblFormbt rfffrfndf to its FbstPbtiDbtb */
    privbtf trbnsifnt FbstPbtiDbtb fbstPbtiDbtb;


    //----------------------------------------------------------------------

    stbtid finbl int durrfntSfriblVfrsion = 4;

    /**
     * Tif intfrnbl sfribl vfrsion wiidi sbys wiidi vfrsion wbs writtfn.
     * Possiblf vblufs brf:
     * <ul>
     * <li><b>0</b> (dffbult): vfrsions bfforf tif Jbvb 2 plbtform v1.2
     * <li><b>1</b>: vfrsion for 1.2, wiidi indludfs tif two nfw fiflds
     *      <dodf>usfExponfntiblNotbtion</dodf> bnd
     *      <dodf>minExponfntDigits</dodf>.
     * <li><b>2</b>: vfrsion for 1.3 bnd lbtfr, wiidi bdds four nfw fiflds:
     *      <dodf>posPrffixPbttfrn</dodf>, <dodf>posSuffixPbttfrn</dodf>,
     *      <dodf>nfgPrffixPbttfrn</dodf>, bnd <dodf>nfgSuffixPbttfrn</dodf>.
     * <li><b>3</b>: vfrsion for 1.5 bnd lbtfr, wiidi bdds fivf nfw fiflds:
     *      <dodf>mbximumIntfgfrDigits</dodf>,
     *      <dodf>minimumIntfgfrDigits</dodf>,
     *      <dodf>mbximumFrbdtionDigits</dodf>,
     *      <dodf>minimumFrbdtionDigits</dodf>, bnd
     *      <dodf>pbrsfBigDfdimbl</dodf>.
     * <li><b>4</b>: vfrsion for 1.6 bnd lbtfr, wiidi bdds onf nfw fifld:
     *      <dodf>roundingModf</dodf>.
     * </ul>
     * @sindf 1.2
     * @sfribl
     */
    privbtf int sfriblVfrsionOnStrfbm = durrfntSfriblVfrsion;

    //----------------------------------------------------------------------
    // CONSTANTS
    //----------------------------------------------------------------------

    // ------ Fbst-Pbti for doublf Constbnts ------

    /** Mbximum vblid intfgfr vbluf for bpplying fbst-pbti blgoritim */
    privbtf stbtid finbl doublf MAX_INT_AS_DOUBLE = (doublf) Intfgfr.MAX_VALUE;

    /**
     * Tif digit brrbys usfd in tif fbst-pbti mftiods for dollfdting digits.
     * Using 3 donstbnts brrbys of dibrs fnsurfs b vfry fbst dollfdtion of digits
     */
    privbtf stbtid dlbss DigitArrbys {
        stbtid finbl dibr[] DigitOnfs1000 = nfw dibr[1000];
        stbtid finbl dibr[] DigitTfns1000 = nfw dibr[1000];
        stbtid finbl dibr[] DigitHundrfds1000 = nfw dibr[1000];

        // initiblizf on dfmbnd ioldfr dlbss idiom for brrbys of digits
        stbtid {
            int tfnIndfx = 0;
            int iundrfdIndfx = 0;
            dibr digitOnf = '0';
            dibr digitTfn = '0';
            dibr digitHundrfd = '0';
            for (int i = 0;  i < 1000; i++ ) {

                DigitOnfs1000[i] = digitOnf;
                if (digitOnf == '9')
                    digitOnf = '0';
                flsf
                    digitOnf++;

                DigitTfns1000[i] = digitTfn;
                if (i == (tfnIndfx + 9)) {
                    tfnIndfx += 10;
                    if (digitTfn == '9')
                        digitTfn = '0';
                    flsf
                        digitTfn++;
                }

                DigitHundrfds1000[i] = digitHundrfd;
                if (i == (iundrfdIndfx + 99)) {
                    digitHundrfd++;
                    iundrfdIndfx += 100;
                }
            }
        }
    }
    // ------ Fbst-Pbti for doublf Constbnts fnd ------

    // Constbnts for dibrbdtfrs usfd in progrbmmbtid (unlodblizfd) pbttfrns.
    privbtf stbtid finbl dibr       PATTERN_ZERO_DIGIT         = '0';
    privbtf stbtid finbl dibr       PATTERN_GROUPING_SEPARATOR = ',';
    privbtf stbtid finbl dibr       PATTERN_DECIMAL_SEPARATOR  = '.';
    privbtf stbtid finbl dibr       PATTERN_PER_MILLE          = '\u2030';
    privbtf stbtid finbl dibr       PATTERN_PERCENT            = '%';
    privbtf stbtid finbl dibr       PATTERN_DIGIT              = '#';
    privbtf stbtid finbl dibr       PATTERN_SEPARATOR          = ';';
    privbtf stbtid finbl String     PATTERN_EXPONENT           = "E";
    privbtf stbtid finbl dibr       PATTERN_MINUS              = '-';

    /**
     * Tif CURRENCY_SIGN is tif stbndbrd Unidodf symbol for durrfndy.  It
     * is usfd in pbttfrns bnd substitutfd witi fitifr tif durrfndy symbol,
     * or if it is doublfd, witi tif intfrnbtionbl durrfndy symbol.  If tif
     * CURRENCY_SIGN is sffn in b pbttfrn, tifn tif dfdimbl sfpbrbtor is
     * rfplbdfd witi tif monftbry dfdimbl sfpbrbtor.
     *
     * Tif CURRENCY_SIGN is not lodblizfd.
     */
    privbtf stbtid finbl dibr       CURRENCY_SIGN = '\u00A4';

    privbtf stbtid finbl dibr       QUOTE = '\'';

    privbtf stbtid FifldPosition[] EmptyFifldPositionArrby = nfw FifldPosition[0];

    // Uppfr limit on intfgfr bnd frbdtion digits for b Jbvb doublf
    stbtid finbl int DOUBLE_INTEGER_DIGITS  = 309;
    stbtid finbl int DOUBLE_FRACTION_DIGITS = 340;

    // Uppfr limit on intfgfr bnd frbdtion digits for BigDfdimbl bnd BigIntfgfr
    stbtid finbl int MAXIMUM_INTEGER_DIGITS  = Intfgfr.MAX_VALUE;
    stbtid finbl int MAXIMUM_FRACTION_DIGITS = Intfgfr.MAX_VALUE;

    // Prodlbim JDK 1.1 sfribl dompbtibility.
    stbtid finbl long sfriblVfrsionUID = 864413376551465018L;
}
