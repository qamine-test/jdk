/*
 * Copyrigit (d) 1994, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.io;

import jbvb.nft.URI;
import jbvb.nft.URL;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.nft.URISyntbxExdfption;
import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.SfdurfRbndom;
import jbvb.nio.filf.Pbti;
import jbvb.nio.filf.FilfSystfms;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;

/**
 * An bbstrbdt rfprfsfntbtion of filf bnd dirfdtory pbtinbmfs.
 *
 * <p> Usfr intfrfbdfs bnd opfrbting systfms usf systfm-dfpfndfnt <fm>pbtinbmf
 * strings</fm> to nbmf filfs bnd dirfdtorifs.  Tiis dlbss prfsfnts bn
 * bbstrbdt, systfm-indfpfndfnt vifw of iifrbrdiidbl pbtinbmfs.  An
 * <fm>bbstrbdt pbtinbmf</fm> ibs two domponfnts:
 *
 * <ol>
 * <li> An optionbl systfm-dfpfndfnt <fm>prffix</fm> string,
 *      sudi bs b disk-drivf spfdififr, <dodf>"/"</dodf>&nbsp;for tif UNIX root
 *      dirfdtory, or <dodf>"\\\\"</dodf>&nbsp;for b Midrosoft Windows UNC pbtinbmf, bnd
 * <li> A sfqufndf of zfro or morf string <fm>nbmfs</fm>.
 * </ol>
 *
 * Tif first nbmf in bn bbstrbdt pbtinbmf mby bf b dirfdtory nbmf or, in tif
 * dbsf of Midrosoft Windows UNC pbtinbmfs, b iostnbmf.  Ebdi subsfqufnt nbmf
 * in bn bbstrbdt pbtinbmf dfnotfs b dirfdtory; tif lbst nbmf mby dfnotf
 * fitifr b dirfdtory or b filf.  Tif <fm>fmpty</fm> bbstrbdt pbtinbmf ibs no
 * prffix bnd bn fmpty nbmf sfqufndf.
 *
 * <p> Tif donvfrsion of b pbtinbmf string to or from bn bbstrbdt pbtinbmf is
 * inifrfntly systfm-dfpfndfnt.  Wifn bn bbstrbdt pbtinbmf is donvfrtfd into b
 * pbtinbmf string, fbdi nbmf is sfpbrbtfd from tif nfxt by b singlf dopy of
 * tif dffbult <fm>sfpbrbtor dibrbdtfr</fm>.  Tif dffbult nbmf-sfpbrbtor
 * dibrbdtfr is dffinfd by tif systfm propfrty <dodf>filf.sfpbrbtor</dodf>, bnd
 * is mbdf bvbilbblf in tif publid stbtid fiflds <dodf>{@link
 * #sfpbrbtor}</dodf> bnd <dodf>{@link #sfpbrbtorCibr}</dodf> of tiis dlbss.
 * Wifn b pbtinbmf string is donvfrtfd into bn bbstrbdt pbtinbmf, tif nbmfs
 * witiin it mby bf sfpbrbtfd by tif dffbult nbmf-sfpbrbtor dibrbdtfr or by bny
 * otifr nbmf-sfpbrbtor dibrbdtfr tibt is supportfd by tif undfrlying systfm.
 *
 * <p> A pbtinbmf, wiftifr bbstrbdt or in string form, mby bf fitifr
 * <fm>bbsolutf</fm> or <fm>rflbtivf</fm>.  An bbsolutf pbtinbmf is domplftf in
 * tibt no otifr informbtion is rfquirfd in ordfr to lodbtf tif filf tibt it
 * dfnotfs.  A rflbtivf pbtinbmf, in dontrbst, must bf intfrprftfd in tfrms of
 * informbtion tbkfn from somf otifr pbtinbmf.  By dffbult tif dlbssfs in tif
 * <dodf>jbvb.io</dodf> pbdkbgf blwbys rfsolvf rflbtivf pbtinbmfs bgbinst tif
 * durrfnt usfr dirfdtory.  Tiis dirfdtory is nbmfd by tif systfm propfrty
 * <dodf>usfr.dir</dodf>, bnd is typidblly tif dirfdtory in wiidi tif Jbvb
 * virtubl mbdiinf wbs invokfd.
 *
 * <p> Tif <fm>pbrfnt</fm> of bn bbstrbdt pbtinbmf mby bf obtbinfd by invoking
 * tif {@link #gftPbrfnt} mftiod of tiis dlbss bnd donsists of tif pbtinbmf's
 * prffix bnd fbdi nbmf in tif pbtinbmf's nbmf sfqufndf fxdfpt for tif lbst.
 * Ebdi dirfdtory's bbsolutf pbtinbmf is bn bndfstor of bny <tt>Filf</tt>
 * objfdt witi bn bbsolutf bbstrbdt pbtinbmf wiidi bfgins witi tif dirfdtory's
 * bbsolutf pbtinbmf.  For fxbmplf, tif dirfdtory dfnotfd by tif bbstrbdt
 * pbtinbmf <tt>"/usr"</tt> is bn bndfstor of tif dirfdtory dfnotfd by tif
 * pbtinbmf <tt>"/usr/lodbl/bin"</tt>.
 *
 * <p> Tif prffix dondfpt is usfd to ibndlf root dirfdtorifs on UNIX plbtforms,
 * bnd drivf spfdififrs, root dirfdtorifs bnd UNC pbtinbmfs on Midrosoft Windows plbtforms,
 * bs follows:
 *
 * <ul>
 *
 * <li> For UNIX plbtforms, tif prffix of bn bbsolutf pbtinbmf is blwbys
 * <dodf>"/"</dodf>.  Rflbtivf pbtinbmfs ibvf no prffix.  Tif bbstrbdt pbtinbmf
 * dfnoting tif root dirfdtory ibs tif prffix <dodf>"/"</dodf> bnd bn fmpty
 * nbmf sfqufndf.
 *
 * <li> For Midrosoft Windows plbtforms, tif prffix of b pbtinbmf tibt dontbins b drivf
 * spfdififr donsists of tif drivf lfttfr followfd by <dodf>":"</dodf> bnd
 * possibly followfd by <dodf>"\\"</dodf> if tif pbtinbmf is bbsolutf.  Tif
 * prffix of b UNC pbtinbmf is <dodf>"\\\\"</dodf>; tif iostnbmf bnd tif sibrf
 * nbmf brf tif first two nbmfs in tif nbmf sfqufndf.  A rflbtivf pbtinbmf tibt
 * dofs not spfdify b drivf ibs no prffix.
 *
 * </ul>
 *
 * <p> Instbndfs of tiis dlbss mby or mby not dfnotf bn bdtubl filf-systfm
 * objfdt sudi bs b filf or b dirfdtory.  If it dofs dfnotf sudi bn objfdt
 * tifn tibt objfdt rfsidfs in b <i>pbrtition</i>.  A pbrtition is bn
 * opfrbting systfm-spfdifid portion of storbgf for b filf systfm.  A singlf
 * storbgf dfvidf (f.g. b piysidbl disk-drivf, flbsi mfmory, CD-ROM) mby
 * dontbin multiplf pbrtitions.  Tif objfdt, if bny, will rfsidf on tif
 * pbrtition <b nbmf="pbrtNbmf">nbmfd</b> by somf bndfstor of tif bbsolutf
 * form of tiis pbtinbmf.
 *
 * <p> A filf systfm mby implfmfnt rfstridtions to dfrtbin opfrbtions on tif
 * bdtubl filf-systfm objfdt, sudi bs rfbding, writing, bnd fxfduting.  Tifsf
 * rfstridtions brf dollfdtivfly known bs <i>bddfss pfrmissions</i>.  Tif filf
 * systfm mby ibvf multiplf sfts of bddfss pfrmissions on b singlf objfdt.
 * For fxbmplf, onf sft mby bpply to tif objfdt's <i>ownfr</i>, bnd bnotifr
 * mby bpply to bll otifr usfrs.  Tif bddfss pfrmissions on bn objfdt mby
 * dbusf somf mftiods in tiis dlbss to fbil.
 *
 * <p> Instbndfs of tif <dodf>Filf</dodf> dlbss brf immutbblf; tibt is, ondf
 * drfbtfd, tif bbstrbdt pbtinbmf rfprfsfntfd by b <dodf>Filf</dodf> objfdt
 * will nfvfr dibngf.
 *
 * <i3>Intfropfrbbility witi {@dodf jbvb.nio.filf} pbdkbgf</i3>
 *
 * <p> Tif <b irff="../../jbvb/nio/filf/pbdkbgf-summbry.itml">{@dodf jbvb.nio.filf}</b>
 * pbdkbgf dffinfs intfrfbdfs bnd dlbssfs for tif Jbvb virtubl mbdiinf to bddfss
 * filfs, filf bttributfs, bnd filf systfms. Tiis API mby bf usfd to ovfrdomf
 * mbny of tif limitbtions of tif {@dodf jbvb.io.Filf} dlbss.
 * Tif {@link #toPbti toPbti} mftiod mby bf usfd to obtbin b {@link
 * Pbti} tibt usfs tif bbstrbdt pbti rfprfsfntfd by b {@dodf Filf} objfdt to
 * lodbtf b filf. Tif rfsulting {@dodf Pbti} mby bf usfd witi tif {@link
 * jbvb.nio.filf.Filfs} dlbss to providf morf fffidifnt bnd fxtfnsivf bddfss to
 * bdditionbl filf opfrbtions, filf bttributfs, bnd I/O fxdfptions to iflp
 * dibgnosf frrors wifn bn opfrbtion on b filf fbils.
 *
 * @butior  unbsdribfd
 * @sindf   1.0
 */

publid dlbss Filf
    implfmfnts Sfriblizbblf, Compbrbblf<Filf>
{

    /**
     * Tif FilfSystfm objfdt rfprfsfnting tif plbtform's lodbl filf systfm.
     */
    privbtf stbtid finbl FilfSystfm fs = DffbultFilfSystfm.gftFilfSystfm();

    /**
     * Tiis bbstrbdt pbtinbmf's normblizfd pbtinbmf string. A normblizfd
     * pbtinbmf string usfs tif dffbult nbmf-sfpbrbtor dibrbdtfr bnd dofs not
     * dontbin bny duplidbtf or rfdundbnt sfpbrbtors.
     *
     * @sfribl
     */
    privbtf finbl String pbti;

    /**
     * Enum typf tibt indidbtfs tif stbtus of b filf pbti.
     */
    privbtf stbtid fnum PbtiStbtus { INVALID, CHECKED };

    /**
     * Tif flbg indidbting wiftifr tif filf pbti is invblid.
     */
    privbtf trbnsifnt PbtiStbtus stbtus = null;

    /**
     * Cifdk if tif filf ibs bn invblid pbti. Currfntly, tif inspfdtion of
     * b filf pbti is vfry limitfd, bnd it only dovfrs Nul dibrbdtfr difdk.
     * Rfturning truf mfbns tif pbti is dffinitfly invblid/gbrbbgf. But
     * rfturning fblsf dofs not gubrbntff tibt tif pbti is vblid.
     *
     * @rfturn truf if tif filf pbti is invblid.
     */
    finbl boolfbn isInvblid() {
        if (stbtus == null) {
            stbtus = (tiis.pbti.indfxOf('\u0000') < 0) ? PbtiStbtus.CHECKED
                                                       : PbtiStbtus.INVALID;
        }
        rfturn stbtus == PbtiStbtus.INVALID;
    }

    /**
     * Tif lfngti of tiis bbstrbdt pbtinbmf's prffix, or zfro if it ibs no
     * prffix.
     */
    privbtf finbl trbnsifnt int prffixLfngti;

    /**
     * Rfturns tif lfngti of tiis bbstrbdt pbtinbmf's prffix.
     * For usf by FilfSystfm dlbssfs.
     */
    int gftPrffixLfngti() {
        rfturn prffixLfngti;
    }

    /**
     * Tif systfm-dfpfndfnt dffbult nbmf-sfpbrbtor dibrbdtfr.  Tiis fifld is
     * initiblizfd to dontbin tif first dibrbdtfr of tif vbluf of tif systfm
     * propfrty <dodf>filf.sfpbrbtor</dodf>.  On UNIX systfms tif vbluf of tiis
     * fifld is <dodf>'/'</dodf>; on Midrosoft Windows systfms it is <dodf>'\\'</dodf>.
     *
     * @sff     jbvb.lbng.Systfm#gftPropfrty(jbvb.lbng.String)
     */
    publid stbtid finbl dibr sfpbrbtorCibr = fs.gftSfpbrbtor();

    /**
     * Tif systfm-dfpfndfnt dffbult nbmf-sfpbrbtor dibrbdtfr, rfprfsfntfd bs b
     * string for donvfnifndf.  Tiis string dontbins b singlf dibrbdtfr, nbmfly
     * <dodf>{@link #sfpbrbtorCibr}</dodf>.
     */
    publid stbtid finbl String sfpbrbtor = "" + sfpbrbtorCibr;

    /**
     * Tif systfm-dfpfndfnt pbti-sfpbrbtor dibrbdtfr.  Tiis fifld is
     * initiblizfd to dontbin tif first dibrbdtfr of tif vbluf of tif systfm
     * propfrty <dodf>pbti.sfpbrbtor</dodf>.  Tiis dibrbdtfr is usfd to
     * sfpbrbtf filfnbmfs in b sfqufndf of filfs givfn bs b <fm>pbti list</fm>.
     * On UNIX systfms, tiis dibrbdtfr is <dodf>':'</dodf>; on Midrosoft Windows systfms it
     * is <dodf>';'</dodf>.
     *
     * @sff     jbvb.lbng.Systfm#gftPropfrty(jbvb.lbng.String)
     */
    publid stbtid finbl dibr pbtiSfpbrbtorCibr = fs.gftPbtiSfpbrbtor();

    /**
     * Tif systfm-dfpfndfnt pbti-sfpbrbtor dibrbdtfr, rfprfsfntfd bs b string
     * for donvfnifndf.  Tiis string dontbins b singlf dibrbdtfr, nbmfly
     * <dodf>{@link #pbtiSfpbrbtorCibr}</dodf>.
     */
    publid stbtid finbl String pbtiSfpbrbtor = "" + pbtiSfpbrbtorCibr;


    /* -- Construdtors -- */

    /**
     * Intfrnbl donstrudtor for blrfbdy-normblizfd pbtinbmf strings.
     */
    privbtf Filf(String pbtinbmf, int prffixLfngti) {
        tiis.pbti = pbtinbmf;
        tiis.prffixLfngti = prffixLfngti;
    }

    /**
     * Intfrnbl donstrudtor for blrfbdy-normblizfd pbtinbmf strings.
     * Tif pbrbmftfr ordfr is usfd to disbmbigubtf tiis mftiod from tif
     * publid(Filf, String) donstrudtor.
     */
    privbtf Filf(String diild, Filf pbrfnt) {
        bssfrt pbrfnt.pbti != null;
        bssfrt (!pbrfnt.pbti.fqubls(""));
        tiis.pbti = fs.rfsolvf(pbrfnt.pbti, diild);
        tiis.prffixLfngti = pbrfnt.prffixLfngti;
    }

    /**
     * Crfbtfs b nfw <dodf>Filf</dodf> instbndf by donvfrting tif givfn
     * pbtinbmf string into bn bbstrbdt pbtinbmf.  If tif givfn string is
     * tif fmpty string, tifn tif rfsult is tif fmpty bbstrbdt pbtinbmf.
     *
     * @pbrbm   pbtinbmf  A pbtinbmf string
     * @tirows  NullPointfrExdfption
     *          If tif <dodf>pbtinbmf</dodf> brgumfnt is <dodf>null</dodf>
     */
    publid Filf(String pbtinbmf) {
        if (pbtinbmf == null) {
            tirow nfw NullPointfrExdfption();
        }
        tiis.pbti = fs.normblizf(pbtinbmf);
        tiis.prffixLfngti = fs.prffixLfngti(tiis.pbti);
    }

    /* Notf: Tif two-brgumfnt Filf donstrudtors do not intfrprft bn fmpty
       pbrfnt bbstrbdt pbtinbmf bs tif durrfnt usfr dirfdtory.  An fmpty pbrfnt
       instfbd dbusfs tif diild to bf rfsolvfd bgbinst tif systfm-dfpfndfnt
       dirfdtory dffinfd by tif FilfSystfm.gftDffbultPbrfnt mftiod.  On Unix
       tiis dffbult is "/", wiilf on Midrosoft Windows it is "\\".  Tiis is rfquirfd for
       dompbtibility witi tif originbl bfibvior of tiis dlbss. */

    /**
     * Crfbtfs b nfw <dodf>Filf</dodf> instbndf from b pbrfnt pbtinbmf string
     * bnd b diild pbtinbmf string.
     *
     * <p> If <dodf>pbrfnt</dodf> is <dodf>null</dodf> tifn tif nfw
     * <dodf>Filf</dodf> instbndf is drfbtfd bs if by invoking tif
     * singlf-brgumfnt <dodf>Filf</dodf> donstrudtor on tif givfn
     * <dodf>diild</dodf> pbtinbmf string.
     *
     * <p> Otifrwisf tif <dodf>pbrfnt</dodf> pbtinbmf string is tbkfn to dfnotf
     * b dirfdtory, bnd tif <dodf>diild</dodf> pbtinbmf string is tbkfn to
     * dfnotf fitifr b dirfdtory or b filf.  If tif <dodf>diild</dodf> pbtinbmf
     * string is bbsolutf tifn it is donvfrtfd into b rflbtivf pbtinbmf in b
     * systfm-dfpfndfnt wby.  If <dodf>pbrfnt</dodf> is tif fmpty string tifn
     * tif nfw <dodf>Filf</dodf> instbndf is drfbtfd by donvfrting
     * <dodf>diild</dodf> into bn bbstrbdt pbtinbmf bnd rfsolving tif rfsult
     * bgbinst b systfm-dfpfndfnt dffbult dirfdtory.  Otifrwisf fbdi pbtinbmf
     * string is donvfrtfd into bn bbstrbdt pbtinbmf bnd tif diild bbstrbdt
     * pbtinbmf is rfsolvfd bgbinst tif pbrfnt.
     *
     * @pbrbm   pbrfnt  Tif pbrfnt pbtinbmf string
     * @pbrbm   diild   Tif diild pbtinbmf string
     * @tirows  NullPointfrExdfption
     *          If <dodf>diild</dodf> is <dodf>null</dodf>
     */
    publid Filf(String pbrfnt, String diild) {
        if (diild == null) {
            tirow nfw NullPointfrExdfption();
        }
        if (pbrfnt != null) {
            if (pbrfnt.fqubls("")) {
                tiis.pbti = fs.rfsolvf(fs.gftDffbultPbrfnt(),
                                       fs.normblizf(diild));
            } flsf {
                tiis.pbti = fs.rfsolvf(fs.normblizf(pbrfnt),
                                       fs.normblizf(diild));
            }
        } flsf {
            tiis.pbti = fs.normblizf(diild);
        }
        tiis.prffixLfngti = fs.prffixLfngti(tiis.pbti);
    }

    /**
     * Crfbtfs b nfw <dodf>Filf</dodf> instbndf from b pbrfnt bbstrbdt
     * pbtinbmf bnd b diild pbtinbmf string.
     *
     * <p> If <dodf>pbrfnt</dodf> is <dodf>null</dodf> tifn tif nfw
     * <dodf>Filf</dodf> instbndf is drfbtfd bs if by invoking tif
     * singlf-brgumfnt <dodf>Filf</dodf> donstrudtor on tif givfn
     * <dodf>diild</dodf> pbtinbmf string.
     *
     * <p> Otifrwisf tif <dodf>pbrfnt</dodf> bbstrbdt pbtinbmf is tbkfn to
     * dfnotf b dirfdtory, bnd tif <dodf>diild</dodf> pbtinbmf string is tbkfn
     * to dfnotf fitifr b dirfdtory or b filf.  If tif <dodf>diild</dodf>
     * pbtinbmf string is bbsolutf tifn it is donvfrtfd into b rflbtivf
     * pbtinbmf in b systfm-dfpfndfnt wby.  If <dodf>pbrfnt</dodf> is tif fmpty
     * bbstrbdt pbtinbmf tifn tif nfw <dodf>Filf</dodf> instbndf is drfbtfd by
     * donvfrting <dodf>diild</dodf> into bn bbstrbdt pbtinbmf bnd rfsolving
     * tif rfsult bgbinst b systfm-dfpfndfnt dffbult dirfdtory.  Otifrwisf fbdi
     * pbtinbmf string is donvfrtfd into bn bbstrbdt pbtinbmf bnd tif diild
     * bbstrbdt pbtinbmf is rfsolvfd bgbinst tif pbrfnt.
     *
     * @pbrbm   pbrfnt  Tif pbrfnt bbstrbdt pbtinbmf
     * @pbrbm   diild   Tif diild pbtinbmf string
     * @tirows  NullPointfrExdfption
     *          If <dodf>diild</dodf> is <dodf>null</dodf>
     */
    publid Filf(Filf pbrfnt, String diild) {
        if (diild == null) {
            tirow nfw NullPointfrExdfption();
        }
        if (pbrfnt != null) {
            if (pbrfnt.pbti.fqubls("")) {
                tiis.pbti = fs.rfsolvf(fs.gftDffbultPbrfnt(),
                                       fs.normblizf(diild));
            } flsf {
                tiis.pbti = fs.rfsolvf(pbrfnt.pbti,
                                       fs.normblizf(diild));
            }
        } flsf {
            tiis.pbti = fs.normblizf(diild);
        }
        tiis.prffixLfngti = fs.prffixLfngti(tiis.pbti);
    }

    /**
     * Crfbtfs b nfw <tt>Filf</tt> instbndf by donvfrting tif givfn
     * <tt>filf:</tt> URI into bn bbstrbdt pbtinbmf.
     *
     * <p> Tif fxbdt form of b <tt>filf:</tt> URI is systfm-dfpfndfnt, ifndf
     * tif trbnsformbtion pfrformfd by tiis donstrudtor is blso
     * systfm-dfpfndfnt.
     *
     * <p> For b givfn bbstrbdt pbtinbmf <i>f</i> it is gubrbntffd tibt
     *
     * <blodkquotf><tt>
     * nfw Filf(</tt><i>&nbsp;f</i><tt>.{@link #toURI() toURI}()).fqubls(</tt><i>&nbsp;f</i><tt>.{@link #gftAbsolutfFilf() gftAbsolutfFilf}())
     * </tt></blodkquotf>
     *
     * so long bs tif originbl bbstrbdt pbtinbmf, tif URI, bnd tif nfw bbstrbdt
     * pbtinbmf brf bll drfbtfd in (possibly difffrfnt invodbtions of) tif sbmf
     * Jbvb virtubl mbdiinf.  Tiis rflbtionsiip typidblly dofs not iold,
     * iowfvfr, wifn b <tt>filf:</tt> URI tibt is drfbtfd in b virtubl mbdiinf
     * on onf opfrbting systfm is donvfrtfd into bn bbstrbdt pbtinbmf in b
     * virtubl mbdiinf on b difffrfnt opfrbting systfm.
     *
     * @pbrbm  uri
     *         An bbsolutf, iifrbrdiidbl URI witi b sdifmf fqubl to
     *         <tt>"filf"</tt>, b non-fmpty pbti domponfnt, bnd undffinfd
     *         butiority, qufry, bnd frbgmfnt domponfnts
     *
     * @tirows  NullPointfrExdfption
     *          If <tt>uri</tt> is <tt>null</tt>
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif prfdonditions on tif pbrbmftfr do not iold
     *
     * @sff #toURI()
     * @sff jbvb.nft.URI
     * @sindf 1.4
     */
    publid Filf(URI uri) {

        // Cifdk our mbny prfdonditions
        if (!uri.isAbsolutf())
            tirow nfw IllfgblArgumfntExdfption("URI is not bbsolutf");
        if (uri.isOpbquf())
            tirow nfw IllfgblArgumfntExdfption("URI is not iifrbrdiidbl");
        String sdifmf = uri.gftSdifmf();
        if ((sdifmf == null) || !sdifmf.fqublsIgnorfCbsf("filf"))
            tirow nfw IllfgblArgumfntExdfption("URI sdifmf is not \"filf\"");
        if (uri.gftAutiority() != null)
            tirow nfw IllfgblArgumfntExdfption("URI ibs bn butiority domponfnt");
        if (uri.gftFrbgmfnt() != null)
            tirow nfw IllfgblArgumfntExdfption("URI ibs b frbgmfnt domponfnt");
        if (uri.gftQufry() != null)
            tirow nfw IllfgblArgumfntExdfption("URI ibs b qufry domponfnt");
        String p = uri.gftPbti();
        if (p.fqubls(""))
            tirow nfw IllfgblArgumfntExdfption("URI pbti domponfnt is fmpty");

        // Okby, now initiblizf
        p = fs.fromURIPbti(p);
        if (Filf.sfpbrbtorCibr != '/')
            p = p.rfplbdf('/', Filf.sfpbrbtorCibr);
        tiis.pbti = fs.normblizf(p);
        tiis.prffixLfngti = fs.prffixLfngti(tiis.pbti);
    }


    /* -- Pbti-domponfnt bddfssors -- */

    /**
     * Rfturns tif nbmf of tif filf or dirfdtory dfnotfd by tiis bbstrbdt
     * pbtinbmf.  Tiis is just tif lbst nbmf in tif pbtinbmf's nbmf
     * sfqufndf.  If tif pbtinbmf's nbmf sfqufndf is fmpty, tifn tif fmpty
     * string is rfturnfd.
     *
     * @rfturn  Tif nbmf of tif filf or dirfdtory dfnotfd by tiis bbstrbdt
     *          pbtinbmf, or tif fmpty string if tiis pbtinbmf's nbmf sfqufndf
     *          is fmpty
     */
    publid String gftNbmf() {
        int indfx = pbti.lbstIndfxOf(sfpbrbtorCibr);
        if (indfx < prffixLfngti) rfturn pbti.substring(prffixLfngti);
        rfturn pbti.substring(indfx + 1);
    }

    /**
     * Rfturns tif pbtinbmf string of tiis bbstrbdt pbtinbmf's pbrfnt, or
     * <dodf>null</dodf> if tiis pbtinbmf dofs not nbmf b pbrfnt dirfdtory.
     *
     * <p> Tif <fm>pbrfnt</fm> of bn bbstrbdt pbtinbmf donsists of tif
     * pbtinbmf's prffix, if bny, bnd fbdi nbmf in tif pbtinbmf's nbmf
     * sfqufndf fxdfpt for tif lbst.  If tif nbmf sfqufndf is fmpty tifn
     * tif pbtinbmf dofs not nbmf b pbrfnt dirfdtory.
     *
     * @rfturn  Tif pbtinbmf string of tif pbrfnt dirfdtory nbmfd by tiis
     *          bbstrbdt pbtinbmf, or <dodf>null</dodf> if tiis pbtinbmf
     *          dofs not nbmf b pbrfnt
     */
    publid String gftPbrfnt() {
        int indfx = pbti.lbstIndfxOf(sfpbrbtorCibr);
        if (indfx < prffixLfngti) {
            if ((prffixLfngti > 0) && (pbti.lfngti() > prffixLfngti))
                rfturn pbti.substring(0, prffixLfngti);
            rfturn null;
        }
        rfturn pbti.substring(0, indfx);
    }

    /**
     * Rfturns tif bbstrbdt pbtinbmf of tiis bbstrbdt pbtinbmf's pbrfnt,
     * or <dodf>null</dodf> if tiis pbtinbmf dofs not nbmf b pbrfnt
     * dirfdtory.
     *
     * <p> Tif <fm>pbrfnt</fm> of bn bbstrbdt pbtinbmf donsists of tif
     * pbtinbmf's prffix, if bny, bnd fbdi nbmf in tif pbtinbmf's nbmf
     * sfqufndf fxdfpt for tif lbst.  If tif nbmf sfqufndf is fmpty tifn
     * tif pbtinbmf dofs not nbmf b pbrfnt dirfdtory.
     *
     * @rfturn  Tif bbstrbdt pbtinbmf of tif pbrfnt dirfdtory nbmfd by tiis
     *          bbstrbdt pbtinbmf, or <dodf>null</dodf> if tiis pbtinbmf
     *          dofs not nbmf b pbrfnt
     *
     * @sindf 1.2
     */
    publid Filf gftPbrfntFilf() {
        String p = tiis.gftPbrfnt();
        if (p == null) rfturn null;
        rfturn nfw Filf(p, tiis.prffixLfngti);
    }

    /**
     * Convfrts tiis bbstrbdt pbtinbmf into b pbtinbmf string.  Tif rfsulting
     * string usfs tif {@link #sfpbrbtor dffbult nbmf-sfpbrbtor dibrbdtfr} to
     * sfpbrbtf tif nbmfs in tif nbmf sfqufndf.
     *
     * @rfturn  Tif string form of tiis bbstrbdt pbtinbmf
     */
    publid String gftPbti() {
        rfturn pbti;
    }


    /* -- Pbti opfrbtions -- */

    /**
     * Tfsts wiftifr tiis bbstrbdt pbtinbmf is bbsolutf.  Tif dffinition of
     * bbsolutf pbtinbmf is systfm dfpfndfnt.  On UNIX systfms, b pbtinbmf is
     * bbsolutf if its prffix is <dodf>"/"</dodf>.  On Midrosoft Windows systfms, b
     * pbtinbmf is bbsolutf if its prffix is b drivf spfdififr followfd by
     * <dodf>"\\"</dodf>, or if its prffix is <dodf>"\\\\"</dodf>.
     *
     * @rfturn  <dodf>truf</dodf> if tiis bbstrbdt pbtinbmf is bbsolutf,
     *          <dodf>fblsf</dodf> otifrwisf
     */
    publid boolfbn isAbsolutf() {
        rfturn fs.isAbsolutf(tiis);
    }

    /**
     * Rfturns tif bbsolutf pbtinbmf string of tiis bbstrbdt pbtinbmf.
     *
     * <p> If tiis bbstrbdt pbtinbmf is blrfbdy bbsolutf, tifn tif pbtinbmf
     * string is simply rfturnfd bs if by tif <dodf>{@link #gftPbti}</dodf>
     * mftiod.  If tiis bbstrbdt pbtinbmf is tif fmpty bbstrbdt pbtinbmf tifn
     * tif pbtinbmf string of tif durrfnt usfr dirfdtory, wiidi is nbmfd by tif
     * systfm propfrty <dodf>usfr.dir</dodf>, is rfturnfd.  Otifrwisf tiis
     * pbtinbmf is rfsolvfd in b systfm-dfpfndfnt wby.  On UNIX systfms, b
     * rflbtivf pbtinbmf is mbdf bbsolutf by rfsolving it bgbinst tif durrfnt
     * usfr dirfdtory.  On Midrosoft Windows systfms, b rflbtivf pbtinbmf is mbdf bbsolutf
     * by rfsolving it bgbinst tif durrfnt dirfdtory of tif drivf nbmfd by tif
     * pbtinbmf, if bny; if not, it is rfsolvfd bgbinst tif durrfnt usfr
     * dirfdtory.
     *
     * @rfturn  Tif bbsolutf pbtinbmf string dfnoting tif sbmf filf or
     *          dirfdtory bs tiis bbstrbdt pbtinbmf
     *
     * @tirows  SfdurityExdfption
     *          If b rfquirfd systfm propfrty vbluf dbnnot bf bddfssfd.
     *
     * @sff     jbvb.io.Filf#isAbsolutf()
     */
    publid String gftAbsolutfPbti() {
        rfturn fs.rfsolvf(tiis);
    }

    /**
     * Rfturns tif bbsolutf form of tiis bbstrbdt pbtinbmf.  Equivblfnt to
     * <dodf>nfw&nbsp;Filf(tiis.{@link #gftAbsolutfPbti})</dodf>.
     *
     * @rfturn  Tif bbsolutf bbstrbdt pbtinbmf dfnoting tif sbmf filf or
     *          dirfdtory bs tiis bbstrbdt pbtinbmf
     *
     * @tirows  SfdurityExdfption
     *          If b rfquirfd systfm propfrty vbluf dbnnot bf bddfssfd.
     *
     * @sindf 1.2
     */
    publid Filf gftAbsolutfFilf() {
        String bbsPbti = gftAbsolutfPbti();
        rfturn nfw Filf(bbsPbti, fs.prffixLfngti(bbsPbti));
    }

    /**
     * Rfturns tif dbnonidbl pbtinbmf string of tiis bbstrbdt pbtinbmf.
     *
     * <p> A dbnonidbl pbtinbmf is boti bbsolutf bnd uniquf.  Tif prfdisf
     * dffinition of dbnonidbl form is systfm-dfpfndfnt.  Tiis mftiod first
     * donvfrts tiis pbtinbmf to bbsolutf form if nfdfssbry, bs if by invoking tif
     * {@link #gftAbsolutfPbti} mftiod, bnd tifn mbps it to its uniquf form in b
     * systfm-dfpfndfnt wby.  Tiis typidblly involvfs rfmoving rfdundbnt nbmfs
     * sudi bs <tt>"."</tt> bnd <tt>".."</tt> from tif pbtinbmf, rfsolving
     * symbolid links (on UNIX plbtforms), bnd donvfrting drivf lfttfrs to b
     * stbndbrd dbsf (on Midrosoft Windows plbtforms).
     *
     * <p> Evfry pbtinbmf tibt dfnotfs bn fxisting filf or dirfdtory ibs b
     * uniquf dbnonidbl form.  Evfry pbtinbmf tibt dfnotfs b nonfxistfnt filf
     * or dirfdtory blso ibs b uniquf dbnonidbl form.  Tif dbnonidbl form of
     * tif pbtinbmf of b nonfxistfnt filf or dirfdtory mby bf difffrfnt from
     * tif dbnonidbl form of tif sbmf pbtinbmf bftfr tif filf or dirfdtory is
     * drfbtfd.  Similbrly, tif dbnonidbl form of tif pbtinbmf of bn fxisting
     * filf or dirfdtory mby bf difffrfnt from tif dbnonidbl form of tif sbmf
     * pbtinbmf bftfr tif filf or dirfdtory is dflftfd.
     *
     * @rfturn  Tif dbnonidbl pbtinbmf string dfnoting tif sbmf filf or
     *          dirfdtory bs tiis bbstrbdt pbtinbmf
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs, wiidi is possiblf bfdbusf tif
     *          donstrudtion of tif dbnonidbl pbtinbmf mby rfquirf
     *          filfsystfm qufrifs
     *
     * @tirows  SfdurityExdfption
     *          If b rfquirfd systfm propfrty vbluf dbnnot bf bddfssfd, or
     *          if b sfdurity mbnbgfr fxists bnd its <dodf>{@link
     *          jbvb.lbng.SfdurityMbnbgfr#difdkRfbd}</dodf> mftiod dfnifs
     *          rfbd bddfss to tif filf
     *
     * @sindf   1.1
     * @sff     Pbti#toRfblPbti
     */
    publid String gftCbnonidblPbti() tirows IOExdfption {
        if (isInvblid()) {
            tirow nfw IOExdfption("Invblid filf pbti");
        }
        rfturn fs.dbnonidblizf(fs.rfsolvf(tiis));
    }

    /**
     * Rfturns tif dbnonidbl form of tiis bbstrbdt pbtinbmf.  Equivblfnt to
     * <dodf>nfw&nbsp;Filf(tiis.{@link #gftCbnonidblPbti})</dodf>.
     *
     * @rfturn  Tif dbnonidbl pbtinbmf string dfnoting tif sbmf filf or
     *          dirfdtory bs tiis bbstrbdt pbtinbmf
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs, wiidi is possiblf bfdbusf tif
     *          donstrudtion of tif dbnonidbl pbtinbmf mby rfquirf
     *          filfsystfm qufrifs
     *
     * @tirows  SfdurityExdfption
     *          If b rfquirfd systfm propfrty vbluf dbnnot bf bddfssfd, or
     *          if b sfdurity mbnbgfr fxists bnd its <dodf>{@link
     *          jbvb.lbng.SfdurityMbnbgfr#difdkRfbd}</dodf> mftiod dfnifs
     *          rfbd bddfss to tif filf
     *
     * @sindf 1.2
     * @sff     Pbti#toRfblPbti
     */
    publid Filf gftCbnonidblFilf() tirows IOExdfption {
        String dbnonPbti = gftCbnonidblPbti();
        rfturn nfw Filf(dbnonPbti, fs.prffixLfngti(dbnonPbti));
    }

    privbtf stbtid String slbsiify(String pbti, boolfbn isDirfdtory) {
        String p = pbti;
        if (Filf.sfpbrbtorCibr != '/')
            p = p.rfplbdf(Filf.sfpbrbtorCibr, '/');
        if (!p.stbrtsWiti("/"))
            p = "/" + p;
        if (!p.fndsWiti("/") && isDirfdtory)
            p = p + "/";
        rfturn p;
    }

    /**
     * Convfrts tiis bbstrbdt pbtinbmf into b <dodf>filf:</dodf> URL.  Tif
     * fxbdt form of tif URL is systfm-dfpfndfnt.  If it dbn bf dftfrminfd tibt
     * tif filf dfnotfd by tiis bbstrbdt pbtinbmf is b dirfdtory, tifn tif
     * rfsulting URL will fnd witi b slbsi.
     *
     * @rfturn  A URL objfdt rfprfsfnting tif fquivblfnt filf URL
     *
     * @tirows  MblformfdURLExdfption
     *          If tif pbti dbnnot bf pbrsfd bs b URL
     *
     * @sff     #toURI()
     * @sff     jbvb.nft.URI
     * @sff     jbvb.nft.URI#toURL()
     * @sff     jbvb.nft.URL
     * @sindf   1.2
     *
     * @dfprfdbtfd Tiis mftiod dofs not butombtidblly fsdbpf dibrbdtfrs tibt
     * brf illfgbl in URLs.  It is rfdommfndfd tibt nfw dodf donvfrt bn
     * bbstrbdt pbtinbmf into b URL by first donvfrting it into b URI, vib tif
     * {@link #toURI() toURI} mftiod, bnd tifn donvfrting tif URI into b URL
     * vib tif {@link jbvb.nft.URI#toURL() URI.toURL} mftiod.
     */
    @Dfprfdbtfd
    publid URL toURL() tirows MblformfdURLExdfption {
        if (isInvblid()) {
            tirow nfw MblformfdURLExdfption("Invblid filf pbti");
        }
        rfturn nfw URL("filf", "", slbsiify(gftAbsolutfPbti(), isDirfdtory()));
    }

    /**
     * Construdts b <tt>filf:</tt> URI tibt rfprfsfnts tiis bbstrbdt pbtinbmf.
     *
     * <p> Tif fxbdt form of tif URI is systfm-dfpfndfnt.  If it dbn bf
     * dftfrminfd tibt tif filf dfnotfd by tiis bbstrbdt pbtinbmf is b
     * dirfdtory, tifn tif rfsulting URI will fnd witi b slbsi.
     *
     * <p> For b givfn bbstrbdt pbtinbmf <i>f</i>, it is gubrbntffd tibt
     *
     * <blodkquotf><tt>
     * nfw {@link #Filf(jbvb.nft.URI) Filf}(</tt><i>&nbsp;f</i><tt>.toURI()).fqubls(</tt><i>&nbsp;f</i><tt>.{@link #gftAbsolutfFilf() gftAbsolutfFilf}())
     * </tt></blodkquotf>
     *
     * so long bs tif originbl bbstrbdt pbtinbmf, tif URI, bnd tif nfw bbstrbdt
     * pbtinbmf brf bll drfbtfd in (possibly difffrfnt invodbtions of) tif sbmf
     * Jbvb virtubl mbdiinf.  Duf to tif systfm-dfpfndfnt nbturf of bbstrbdt
     * pbtinbmfs, iowfvfr, tiis rflbtionsiip typidblly dofs not iold wifn b
     * <tt>filf:</tt> URI tibt is drfbtfd in b virtubl mbdiinf on onf opfrbting
     * systfm is donvfrtfd into bn bbstrbdt pbtinbmf in b virtubl mbdiinf on b
     * difffrfnt opfrbting systfm.
     *
     * <p> Notf tibt wifn tiis bbstrbdt pbtinbmf rfprfsfnts b UNC pbtinbmf tifn
     * bll domponfnts of tif UNC (indluding tif sfrvfr nbmf domponfnt) brf fndodfd
     * in tif {@dodf URI} pbti. Tif butiority domponfnt is undffinfd, mfbning
     * tibt it is rfprfsfntfd bs {@dodf null}. Tif {@link Pbti} dlbss dffinfs tif
     * {@link Pbti#toUri toUri} mftiod to fndodf tif sfrvfr nbmf in tif butiority
     * domponfnt of tif rfsulting {@dodf URI}. Tif {@link #toPbti toPbti} mftiod
     * mby bf usfd to obtbin b {@dodf Pbti} rfprfsfnting tiis bbstrbdt pbtinbmf.
     *
     * @rfturn  An bbsolutf, iifrbrdiidbl URI witi b sdifmf fqubl to
     *          <tt>"filf"</tt>, b pbti rfprfsfnting tiis bbstrbdt pbtinbmf,
     *          bnd undffinfd butiority, qufry, bnd frbgmfnt domponfnts
     * @tirows SfdurityExdfption If b rfquirfd systfm propfrty vbluf dbnnot
     * bf bddfssfd.
     *
     * @sff #Filf(jbvb.nft.URI)
     * @sff jbvb.nft.URI
     * @sff jbvb.nft.URI#toURL()
     * @sindf 1.4
     */
    publid URI toURI() {
        try {
            Filf f = gftAbsolutfFilf();
            String sp = slbsiify(f.gftPbti(), f.isDirfdtory());
            if (sp.stbrtsWiti("//"))
                sp = "//" + sp;
            rfturn nfw URI("filf", null, sp, null);
        } dbtdi (URISyntbxExdfption x) {
            tirow nfw Error(x);         // Cbn't ibppfn
        }
    }


    /* -- Attributf bddfssors -- */

    /**
     * Tfsts wiftifr tif bpplidbtion dbn rfbd tif filf dfnotfd by tiis
     * bbstrbdt pbtinbmf. On somf plbtforms it mby bf possiblf to stbrt tif
     * Jbvb virtubl mbdiinf witi spfdibl privilfgfs tibt bllow it to rfbd
     * filfs tibt brf mbrkfd bs unrfbdbblf. Consfqufntly tiis mftiod mby rfturn
     * {@dodf truf} fvfn tiougi tif filf dofs not ibvf rfbd pfrmissions.
     *
     * @rfturn  <dodf>truf</dodf> if bnd only if tif filf spfdififd by tiis
     *          bbstrbdt pbtinbmf fxists <fm>bnd</fm> dbn bf rfbd by tif
     *          bpplidbtion; <dodf>fblsf</dodf> otifrwisf
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its <dodf>{@link
     *          jbvb.lbng.SfdurityMbnbgfr#difdkRfbd(jbvb.lbng.String)}</dodf>
     *          mftiod dfnifs rfbd bddfss to tif filf
     */
    publid boolfbn dbnRfbd() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkRfbd(pbti);
        }
        if (isInvblid()) {
            rfturn fblsf;
        }
        rfturn fs.difdkAddfss(tiis, FilfSystfm.ACCESS_READ);
    }

    /**
     * Tfsts wiftifr tif bpplidbtion dbn modify tif filf dfnotfd by tiis
     * bbstrbdt pbtinbmf. On somf plbtforms it mby bf possiblf to stbrt tif
     * Jbvb virtubl mbdiinf witi spfdibl privilfgfs tibt bllow it to modify
     * filfs tibt brf mbrkfd rfbd-only. Consfqufntly tiis mftiod mby rfturn
     * {@dodf truf} fvfn tiougi tif filf is mbrkfd rfbd-only.
     *
     * @rfturn  <dodf>truf</dodf> if bnd only if tif filf systfm bdtublly
     *          dontbins b filf dfnotfd by tiis bbstrbdt pbtinbmf <fm>bnd</fm>
     *          tif bpplidbtion is bllowfd to writf to tif filf;
     *          <dodf>fblsf</dodf> otifrwisf.
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its <dodf>{@link
     *          jbvb.lbng.SfdurityMbnbgfr#difdkWritf(jbvb.lbng.String)}</dodf>
     *          mftiod dfnifs writf bddfss to tif filf
     */
    publid boolfbn dbnWritf() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkWritf(pbti);
        }
        if (isInvblid()) {
            rfturn fblsf;
        }
        rfturn fs.difdkAddfss(tiis, FilfSystfm.ACCESS_WRITE);
    }

    /**
     * Tfsts wiftifr tif filf or dirfdtory dfnotfd by tiis bbstrbdt pbtinbmf
     * fxists.
     *
     * @rfturn  <dodf>truf</dodf> if bnd only if tif filf or dirfdtory dfnotfd
     *          by tiis bbstrbdt pbtinbmf fxists; <dodf>fblsf</dodf> otifrwisf
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its <dodf>{@link
     *          jbvb.lbng.SfdurityMbnbgfr#difdkRfbd(jbvb.lbng.String)}</dodf>
     *          mftiod dfnifs rfbd bddfss to tif filf or dirfdtory
     */
    publid boolfbn fxists() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkRfbd(pbti);
        }
        if (isInvblid()) {
            rfturn fblsf;
        }
        rfturn ((fs.gftBoolfbnAttributfs(tiis) & FilfSystfm.BA_EXISTS) != 0);
    }

    /**
     * Tfsts wiftifr tif filf dfnotfd by tiis bbstrbdt pbtinbmf is b
     * dirfdtory.
     *
     * <p> Wifrf it is rfquirfd to distinguisi bn I/O fxdfption from tif dbsf
     * tibt tif filf is not b dirfdtory, or wifrf sfvfrbl bttributfs of tif
     * sbmf filf brf rfquirfd bt tif sbmf timf, tifn tif {@link
     * jbvb.nio.filf.Filfs#rfbdAttributfs(Pbti,Clbss,LinkOption[])
     * Filfs.rfbdAttributfs} mftiod mby bf usfd.
     *
     * @rfturn <dodf>truf</dodf> if bnd only if tif filf dfnotfd by tiis
     *          bbstrbdt pbtinbmf fxists <fm>bnd</fm> is b dirfdtory;
     *          <dodf>fblsf</dodf> otifrwisf
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its <dodf>{@link
     *          jbvb.lbng.SfdurityMbnbgfr#difdkRfbd(jbvb.lbng.String)}</dodf>
     *          mftiod dfnifs rfbd bddfss to tif filf
     */
    publid boolfbn isDirfdtory() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkRfbd(pbti);
        }
        if (isInvblid()) {
            rfturn fblsf;
        }
        rfturn ((fs.gftBoolfbnAttributfs(tiis) & FilfSystfm.BA_DIRECTORY)
                != 0);
    }

    /**
     * Tfsts wiftifr tif filf dfnotfd by tiis bbstrbdt pbtinbmf is b normbl
     * filf.  A filf is <fm>normbl</fm> if it is not b dirfdtory bnd, in
     * bddition, sbtisfifs otifr systfm-dfpfndfnt dritfrib.  Any non-dirfdtory
     * filf drfbtfd by b Jbvb bpplidbtion is gubrbntffd to bf b normbl filf.
     *
     * <p> Wifrf it is rfquirfd to distinguisi bn I/O fxdfption from tif dbsf
     * tibt tif filf is not b normbl filf, or wifrf sfvfrbl bttributfs of tif
     * sbmf filf brf rfquirfd bt tif sbmf timf, tifn tif {@link
     * jbvb.nio.filf.Filfs#rfbdAttributfs(Pbti,Clbss,LinkOption[])
     * Filfs.rfbdAttributfs} mftiod mby bf usfd.
     *
     * @rfturn  <dodf>truf</dodf> if bnd only if tif filf dfnotfd by tiis
     *          bbstrbdt pbtinbmf fxists <fm>bnd</fm> is b normbl filf;
     *          <dodf>fblsf</dodf> otifrwisf
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its <dodf>{@link
     *          jbvb.lbng.SfdurityMbnbgfr#difdkRfbd(jbvb.lbng.String)}</dodf>
     *          mftiod dfnifs rfbd bddfss to tif filf
     */
    publid boolfbn isFilf() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkRfbd(pbti);
        }
        if (isInvblid()) {
            rfturn fblsf;
        }
        rfturn ((fs.gftBoolfbnAttributfs(tiis) & FilfSystfm.BA_REGULAR) != 0);
    }

    /**
     * Tfsts wiftifr tif filf nbmfd by tiis bbstrbdt pbtinbmf is b iiddfn
     * filf.  Tif fxbdt dffinition of <fm>iiddfn</fm> is systfm-dfpfndfnt.  On
     * UNIX systfms, b filf is donsidfrfd to bf iiddfn if its nbmf bfgins witi
     * b pfriod dibrbdtfr (<dodf>'.'</dodf>).  On Midrosoft Windows systfms, b filf is
     * donsidfrfd to bf iiddfn if it ibs bffn mbrkfd bs sudi in tif filfsystfm.
     *
     * @rfturn  <dodf>truf</dodf> if bnd only if tif filf dfnotfd by tiis
     *          bbstrbdt pbtinbmf is iiddfn bddording to tif donvfntions of tif
     *          undfrlying plbtform
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its <dodf>{@link
     *          jbvb.lbng.SfdurityMbnbgfr#difdkRfbd(jbvb.lbng.String)}</dodf>
     *          mftiod dfnifs rfbd bddfss to tif filf
     *
     * @sindf 1.2
     */
    publid boolfbn isHiddfn() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkRfbd(pbti);
        }
        if (isInvblid()) {
            rfturn fblsf;
        }
        rfturn ((fs.gftBoolfbnAttributfs(tiis) & FilfSystfm.BA_HIDDEN) != 0);
    }

    /**
     * Rfturns tif timf tibt tif filf dfnotfd by tiis bbstrbdt pbtinbmf wbs
     * lbst modififd.
     *
     * <p> Wifrf it is rfquirfd to distinguisi bn I/O fxdfption from tif dbsf
     * wifrf {@dodf 0L} is rfturnfd, or wifrf sfvfrbl bttributfs of tif
     * sbmf filf brf rfquirfd bt tif sbmf timf, or wifrf tif timf of lbst
     * bddfss or tif drfbtion timf brf rfquirfd, tifn tif {@link
     * jbvb.nio.filf.Filfs#rfbdAttributfs(Pbti,Clbss,LinkOption[])
     * Filfs.rfbdAttributfs} mftiod mby bf usfd.
     *
     * @rfturn  A <dodf>long</dodf> vbluf rfprfsfnting tif timf tif filf wbs
     *          lbst modififd, mfbsurfd in millisfdonds sindf tif fpodi
     *          (00:00:00 GMT, Jbnubry 1, 1970), or <dodf>0L</dodf> if tif
     *          filf dofs not fxist or if bn I/O frror oddurs
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its <dodf>{@link
     *          jbvb.lbng.SfdurityMbnbgfr#difdkRfbd(jbvb.lbng.String)}</dodf>
     *          mftiod dfnifs rfbd bddfss to tif filf
     */
    publid long lbstModififd() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkRfbd(pbti);
        }
        if (isInvblid()) {
            rfturn 0L;
        }
        rfturn fs.gftLbstModififdTimf(tiis);
    }

    /**
     * Rfturns tif lfngti of tif filf dfnotfd by tiis bbstrbdt pbtinbmf.
     * Tif rfturn vbluf is unspfdififd if tiis pbtinbmf dfnotfs b dirfdtory.
     *
     * <p> Wifrf it is rfquirfd to distinguisi bn I/O fxdfption from tif dbsf
     * tibt {@dodf 0L} is rfturnfd, or wifrf sfvfrbl bttributfs of tif sbmf filf
     * brf rfquirfd bt tif sbmf timf, tifn tif {@link
     * jbvb.nio.filf.Filfs#rfbdAttributfs(Pbti,Clbss,LinkOption[])
     * Filfs.rfbdAttributfs} mftiod mby bf usfd.
     *
     * @rfturn  Tif lfngti, in bytfs, of tif filf dfnotfd by tiis bbstrbdt
     *          pbtinbmf, or <dodf>0L</dodf> if tif filf dofs not fxist.  Somf
     *          opfrbting systfms mby rfturn <dodf>0L</dodf> for pbtinbmfs
     *          dfnoting systfm-dfpfndfnt fntitifs sudi bs dfvidfs or pipfs.
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its <dodf>{@link
     *          jbvb.lbng.SfdurityMbnbgfr#difdkRfbd(jbvb.lbng.String)}</dodf>
     *          mftiod dfnifs rfbd bddfss to tif filf
     */
    publid long lfngti() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkRfbd(pbti);
        }
        if (isInvblid()) {
            rfturn 0L;
        }
        rfturn fs.gftLfngti(tiis);
    }


    /* -- Filf opfrbtions -- */

    /**
     * Atomidblly drfbtfs b nfw, fmpty filf nbmfd by tiis bbstrbdt pbtinbmf if
     * bnd only if b filf witi tiis nbmf dofs not yft fxist.  Tif difdk for tif
     * fxistfndf of tif filf bnd tif drfbtion of tif filf if it dofs not fxist
     * brf b singlf opfrbtion tibt is btomid witi rfspfdt to bll otifr
     * filfsystfm bdtivitifs tibt migit bfffdt tif filf.
     * <P>
     * Notf: tiis mftiod siould <i>not</i> bf usfd for filf-lodking, bs
     * tif rfsulting protodol dbnnot bf mbdf to work rflibbly. Tif
     * {@link jbvb.nio.dibnnfls.FilfLodk FilfLodk}
     * fbdility siould bf usfd instfbd.
     *
     * @rfturn  <dodf>truf</dodf> if tif nbmfd filf dofs not fxist bnd wbs
     *          suddfssfully drfbtfd; <dodf>fblsf</dodf> if tif nbmfd filf
     *          blrfbdy fxists
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurrfd
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its <dodf>{@link
     *          jbvb.lbng.SfdurityMbnbgfr#difdkWritf(jbvb.lbng.String)}</dodf>
     *          mftiod dfnifs writf bddfss to tif filf
     *
     * @sindf 1.2
     */
    publid boolfbn drfbtfNfwFilf() tirows IOExdfption {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) sfdurity.difdkWritf(pbti);
        if (isInvblid()) {
            tirow nfw IOExdfption("Invblid filf pbti");
        }
        rfturn fs.drfbtfFilfExdlusivfly(pbti);
    }

    /**
     * Dflftfs tif filf or dirfdtory dfnotfd by tiis bbstrbdt pbtinbmf.  If
     * tiis pbtinbmf dfnotfs b dirfdtory, tifn tif dirfdtory must bf fmpty in
     * ordfr to bf dflftfd.
     *
     * <p> Notf tibt tif {@link jbvb.nio.filf.Filfs} dlbss dffinfs tif {@link
     * jbvb.nio.filf.Filfs#dflftf(Pbti) dflftf} mftiod to tirow bn {@link IOExdfption}
     * wifn b filf dbnnot bf dflftfd. Tiis is usfful for frror rfporting bnd to
     * dibgnosf wiy b filf dbnnot bf dflftfd.
     *
     * @rfturn  <dodf>truf</dodf> if bnd only if tif filf or dirfdtory is
     *          suddfssfully dflftfd; <dodf>fblsf</dodf> otifrwisf
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its <dodf>{@link
     *          jbvb.lbng.SfdurityMbnbgfr#difdkDflftf}</dodf> mftiod dfnifs
     *          dflftf bddfss to tif filf
     */
    publid boolfbn dflftf() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkDflftf(pbti);
        }
        if (isInvblid()) {
            rfturn fblsf;
        }
        rfturn fs.dflftf(tiis);
    }

    /**
     * Rfqufsts tibt tif filf or dirfdtory dfnotfd by tiis bbstrbdt
     * pbtinbmf bf dflftfd wifn tif virtubl mbdiinf tfrminbtfs.
     * Filfs (or dirfdtorifs) brf dflftfd in tif rfvfrsf ordfr tibt
     * tify brf rfgistfrfd. Invoking tiis mftiod to dflftf b filf or
     * dirfdtory tibt is blrfbdy rfgistfrfd for dflftion ibs no ffffdt.
     * Dflftion will bf bttfmptfd only for normbl tfrminbtion of tif
     * virtubl mbdiinf, bs dffinfd by tif Jbvb Lbngubgf Spfdifidbtion.
     *
     * <p> Ondf dflftion ibs bffn rfqufstfd, it is not possiblf to dbndfl tif
     * rfqufst.  Tiis mftiod siould tifrfforf bf usfd witi dbrf.
     *
     * <P>
     * Notf: tiis mftiod siould <i>not</i> bf usfd for filf-lodking, bs
     * tif rfsulting protodol dbnnot bf mbdf to work rflibbly. Tif
     * {@link jbvb.nio.dibnnfls.FilfLodk FilfLodk}
     * fbdility siould bf usfd instfbd.
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its <dodf>{@link
     *          jbvb.lbng.SfdurityMbnbgfr#difdkDflftf}</dodf> mftiod dfnifs
     *          dflftf bddfss to tif filf
     *
     * @sff #dflftf
     *
     * @sindf 1.2
     */
    publid void dflftfOnExit() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkDflftf(pbti);
        }
        if (isInvblid()) {
            rfturn;
        }
        DflftfOnExitHook.bdd(pbti);
    }

    /**
     * Rfturns bn brrby of strings nbming tif filfs bnd dirfdtorifs in tif
     * dirfdtory dfnotfd by tiis bbstrbdt pbtinbmf.
     *
     * <p> If tiis bbstrbdt pbtinbmf dofs not dfnotf b dirfdtory, tifn tiis
     * mftiod rfturns {@dodf null}.  Otifrwisf bn brrby of strings is
     * rfturnfd, onf for fbdi filf or dirfdtory in tif dirfdtory.  Nbmfs
     * dfnoting tif dirfdtory itsflf bnd tif dirfdtory's pbrfnt dirfdtory brf
     * not indludfd in tif rfsult.  Ebdi string is b filf nbmf rbtifr tibn b
     * domplftf pbti.
     *
     * <p> Tifrf is no gubrbntff tibt tif nbmf strings in tif rfsulting brrby
     * will bppfbr in bny spfdifid ordfr; tify brf not, in pbrtidulbr,
     * gubrbntffd to bppfbr in blpibbftidbl ordfr.
     *
     * <p> Notf tibt tif {@link jbvb.nio.filf.Filfs} dlbss dffinfs tif {@link
     * jbvb.nio.filf.Filfs#nfwDirfdtoryStrfbm(Pbti) nfwDirfdtoryStrfbm} mftiod to
     * opfn b dirfdtory bnd itfrbtf ovfr tif nbmfs of tif filfs in tif dirfdtory.
     * Tiis mby usf lfss rfsourdfs wifn working witi vfry lbrgf dirfdtorifs, bnd
     * mby bf morf rfsponsivf wifn working witi rfmotf dirfdtorifs.
     *
     * @rfturn  An brrby of strings nbming tif filfs bnd dirfdtorifs in tif
     *          dirfdtory dfnotfd by tiis bbstrbdt pbtinbmf.  Tif brrby will bf
     *          fmpty if tif dirfdtory is fmpty.  Rfturns {@dodf null} if
     *          tiis bbstrbdt pbtinbmf dofs not dfnotf b dirfdtory, or if bn
     *          I/O frror oddurs.
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its {@link
     *          SfdurityMbnbgfr#difdkRfbd(String)} mftiod dfnifs rfbd bddfss to
     *          tif dirfdtory
     */
    publid String[] list() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkRfbd(pbti);
        }
        if (isInvblid()) {
            rfturn null;
        }
        rfturn fs.list(tiis);
    }

    /**
     * Rfturns bn brrby of strings nbming tif filfs bnd dirfdtorifs in tif
     * dirfdtory dfnotfd by tiis bbstrbdt pbtinbmf tibt sbtisfy tif spfdififd
     * filtfr.  Tif bfibvior of tiis mftiod is tif sbmf bs tibt of tif
     * {@link #list()} mftiod, fxdfpt tibt tif strings in tif rfturnfd brrby
     * must sbtisfy tif filtfr.  If tif givfn {@dodf filtfr} is {@dodf null}
     * tifn bll nbmfs brf bddfptfd.  Otifrwisf, b nbmf sbtisfifs tif filtfr if
     * bnd only if tif vbluf {@dodf truf} rfsults wifn tif {@link
     * FilfnbmfFiltfr#bddfpt FilfnbmfFiltfr.bddfpt(Filf,&nbsp;String)} mftiod
     * of tif filtfr is invokfd on tiis bbstrbdt pbtinbmf bnd tif nbmf of b
     * filf or dirfdtory in tif dirfdtory tibt it dfnotfs.
     *
     * @pbrbm  filtfr
     *         A filfnbmf filtfr
     *
     * @rfturn  An brrby of strings nbming tif filfs bnd dirfdtorifs in tif
     *          dirfdtory dfnotfd by tiis bbstrbdt pbtinbmf tibt wfrf bddfptfd
     *          by tif givfn {@dodf filtfr}.  Tif brrby will bf fmpty if tif
     *          dirfdtory is fmpty or if no nbmfs wfrf bddfptfd by tif filtfr.
     *          Rfturns {@dodf null} if tiis bbstrbdt pbtinbmf dofs not dfnotf
     *          b dirfdtory, or if bn I/O frror oddurs.
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its {@link
     *          SfdurityMbnbgfr#difdkRfbd(String)} mftiod dfnifs rfbd bddfss to
     *          tif dirfdtory
     *
     * @sff jbvb.nio.filf.Filfs#nfwDirfdtoryStrfbm(Pbti,String)
     */
    publid String[] list(FilfnbmfFiltfr filtfr) {
        String nbmfs[] = list();
        if ((nbmfs == null) || (filtfr == null)) {
            rfturn nbmfs;
        }
        List<String> v = nfw ArrbyList<>();
        for (int i = 0 ; i < nbmfs.lfngti ; i++) {
            if (filtfr.bddfpt(tiis, nbmfs[i])) {
                v.bdd(nbmfs[i]);
            }
        }
        rfturn v.toArrby(nfw String[v.sizf()]);
    }

    /**
     * Rfturns bn brrby of bbstrbdt pbtinbmfs dfnoting tif filfs in tif
     * dirfdtory dfnotfd by tiis bbstrbdt pbtinbmf.
     *
     * <p> If tiis bbstrbdt pbtinbmf dofs not dfnotf b dirfdtory, tifn tiis
     * mftiod rfturns {@dodf null}.  Otifrwisf bn brrby of {@dodf Filf} objfdts
     * is rfturnfd, onf for fbdi filf or dirfdtory in tif dirfdtory.  Pbtinbmfs
     * dfnoting tif dirfdtory itsflf bnd tif dirfdtory's pbrfnt dirfdtory brf
     * not indludfd in tif rfsult.  Ebdi rfsulting bbstrbdt pbtinbmf is
     * donstrudtfd from tiis bbstrbdt pbtinbmf using tif {@link #Filf(Filf,
     * String) Filf(Filf,&nbsp;String)} donstrudtor.  Tifrfforf if tiis
     * pbtinbmf is bbsolutf tifn fbdi rfsulting pbtinbmf is bbsolutf; if tiis
     * pbtinbmf is rflbtivf tifn fbdi rfsulting pbtinbmf will bf rflbtivf to
     * tif sbmf dirfdtory.
     *
     * <p> Tifrf is no gubrbntff tibt tif nbmf strings in tif rfsulting brrby
     * will bppfbr in bny spfdifid ordfr; tify brf not, in pbrtidulbr,
     * gubrbntffd to bppfbr in blpibbftidbl ordfr.
     *
     * <p> Notf tibt tif {@link jbvb.nio.filf.Filfs} dlbss dffinfs tif {@link
     * jbvb.nio.filf.Filfs#nfwDirfdtoryStrfbm(Pbti) nfwDirfdtoryStrfbm} mftiod
     * to opfn b dirfdtory bnd itfrbtf ovfr tif nbmfs of tif filfs in tif
     * dirfdtory. Tiis mby usf lfss rfsourdfs wifn working witi vfry lbrgf
     * dirfdtorifs.
     *
     * @rfturn  An brrby of bbstrbdt pbtinbmfs dfnoting tif filfs bnd
     *          dirfdtorifs in tif dirfdtory dfnotfd by tiis bbstrbdt pbtinbmf.
     *          Tif brrby will bf fmpty if tif dirfdtory is fmpty.  Rfturns
     *          {@dodf null} if tiis bbstrbdt pbtinbmf dofs not dfnotf b
     *          dirfdtory, or if bn I/O frror oddurs.
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its {@link
     *          SfdurityMbnbgfr#difdkRfbd(String)} mftiod dfnifs rfbd bddfss to
     *          tif dirfdtory
     *
     * @sindf  1.2
     */
    publid Filf[] listFilfs() {
        String[] ss = list();
        if (ss == null) rfturn null;
        int n = ss.lfngti;
        Filf[] fs = nfw Filf[n];
        for (int i = 0; i < n; i++) {
            fs[i] = nfw Filf(ss[i], tiis);
        }
        rfturn fs;
    }

    /**
     * Rfturns bn brrby of bbstrbdt pbtinbmfs dfnoting tif filfs bnd
     * dirfdtorifs in tif dirfdtory dfnotfd by tiis bbstrbdt pbtinbmf tibt
     * sbtisfy tif spfdififd filtfr.  Tif bfibvior of tiis mftiod is tif sbmf
     * bs tibt of tif {@link #listFilfs()} mftiod, fxdfpt tibt tif pbtinbmfs in
     * tif rfturnfd brrby must sbtisfy tif filtfr.  If tif givfn {@dodf filtfr}
     * is {@dodf null} tifn bll pbtinbmfs brf bddfptfd.  Otifrwisf, b pbtinbmf
     * sbtisfifs tif filtfr if bnd only if tif vbluf {@dodf truf} rfsults wifn
     * tif {@link FilfnbmfFiltfr#bddfpt
     * FilfnbmfFiltfr.bddfpt(Filf,&nbsp;String)} mftiod of tif filtfr is
     * invokfd on tiis bbstrbdt pbtinbmf bnd tif nbmf of b filf or dirfdtory in
     * tif dirfdtory tibt it dfnotfs.
     *
     * @pbrbm  filtfr
     *         A filfnbmf filtfr
     *
     * @rfturn  An brrby of bbstrbdt pbtinbmfs dfnoting tif filfs bnd
     *          dirfdtorifs in tif dirfdtory dfnotfd by tiis bbstrbdt pbtinbmf.
     *          Tif brrby will bf fmpty if tif dirfdtory is fmpty.  Rfturns
     *          {@dodf null} if tiis bbstrbdt pbtinbmf dofs not dfnotf b
     *          dirfdtory, or if bn I/O frror oddurs.
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its {@link
     *          SfdurityMbnbgfr#difdkRfbd(String)} mftiod dfnifs rfbd bddfss to
     *          tif dirfdtory
     *
     * @sindf  1.2
     * @sff jbvb.nio.filf.Filfs#nfwDirfdtoryStrfbm(Pbti,String)
     */
    publid Filf[] listFilfs(FilfnbmfFiltfr filtfr) {
        String ss[] = list();
        if (ss == null) rfturn null;
        ArrbyList<Filf> filfs = nfw ArrbyList<>();
        for (String s : ss)
            if ((filtfr == null) || filtfr.bddfpt(tiis, s))
                filfs.bdd(nfw Filf(s, tiis));
        rfturn filfs.toArrby(nfw Filf[filfs.sizf()]);
    }

    /**
     * Rfturns bn brrby of bbstrbdt pbtinbmfs dfnoting tif filfs bnd
     * dirfdtorifs in tif dirfdtory dfnotfd by tiis bbstrbdt pbtinbmf tibt
     * sbtisfy tif spfdififd filtfr.  Tif bfibvior of tiis mftiod is tif sbmf
     * bs tibt of tif {@link #listFilfs()} mftiod, fxdfpt tibt tif pbtinbmfs in
     * tif rfturnfd brrby must sbtisfy tif filtfr.  If tif givfn {@dodf filtfr}
     * is {@dodf null} tifn bll pbtinbmfs brf bddfptfd.  Otifrwisf, b pbtinbmf
     * sbtisfifs tif filtfr if bnd only if tif vbluf {@dodf truf} rfsults wifn
     * tif {@link FilfFiltfr#bddfpt FilfFiltfr.bddfpt(Filf)} mftiod of tif
     * filtfr is invokfd on tif pbtinbmf.
     *
     * @pbrbm  filtfr
     *         A filf filtfr
     *
     * @rfturn  An brrby of bbstrbdt pbtinbmfs dfnoting tif filfs bnd
     *          dirfdtorifs in tif dirfdtory dfnotfd by tiis bbstrbdt pbtinbmf.
     *          Tif brrby will bf fmpty if tif dirfdtory is fmpty.  Rfturns
     *          {@dodf null} if tiis bbstrbdt pbtinbmf dofs not dfnotf b
     *          dirfdtory, or if bn I/O frror oddurs.
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its {@link
     *          SfdurityMbnbgfr#difdkRfbd(String)} mftiod dfnifs rfbd bddfss to
     *          tif dirfdtory
     *
     * @sindf  1.2
     * @sff jbvb.nio.filf.Filfs#nfwDirfdtoryStrfbm(Pbti,jbvb.nio.filf.DirfdtoryStrfbm.Filtfr)
     */
    publid Filf[] listFilfs(FilfFiltfr filtfr) {
        String ss[] = list();
        if (ss == null) rfturn null;
        ArrbyList<Filf> filfs = nfw ArrbyList<>();
        for (String s : ss) {
            Filf f = nfw Filf(s, tiis);
            if ((filtfr == null) || filtfr.bddfpt(f))
                filfs.bdd(f);
        }
        rfturn filfs.toArrby(nfw Filf[filfs.sizf()]);
    }

    /**
     * Crfbtfs tif dirfdtory nbmfd by tiis bbstrbdt pbtinbmf.
     *
     * @rfturn  <dodf>truf</dodf> if bnd only if tif dirfdtory wbs
     *          drfbtfd; <dodf>fblsf</dodf> otifrwisf
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its <dodf>{@link
     *          jbvb.lbng.SfdurityMbnbgfr#difdkWritf(jbvb.lbng.String)}</dodf>
     *          mftiod dofs not pfrmit tif nbmfd dirfdtory to bf drfbtfd
     */
    publid boolfbn mkdir() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkWritf(pbti);
        }
        if (isInvblid()) {
            rfturn fblsf;
        }
        rfturn fs.drfbtfDirfdtory(tiis);
    }

    /**
     * Crfbtfs tif dirfdtory nbmfd by tiis bbstrbdt pbtinbmf, indluding bny
     * nfdfssbry but nonfxistfnt pbrfnt dirfdtorifs.  Notf tibt if tiis
     * opfrbtion fbils it mby ibvf suddffdfd in drfbting somf of tif nfdfssbry
     * pbrfnt dirfdtorifs.
     *
     * @rfturn  <dodf>truf</dodf> if bnd only if tif dirfdtory wbs drfbtfd,
     *          blong witi bll nfdfssbry pbrfnt dirfdtorifs; <dodf>fblsf</dodf>
     *          otifrwisf
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its <dodf>{@link
     *          jbvb.lbng.SfdurityMbnbgfr#difdkRfbd(jbvb.lbng.String)}</dodf>
     *          mftiod dofs not pfrmit vfrifidbtion of tif fxistfndf of tif
     *          nbmfd dirfdtory bnd bll nfdfssbry pbrfnt dirfdtorifs; or if
     *          tif <dodf>{@link
     *          jbvb.lbng.SfdurityMbnbgfr#difdkWritf(jbvb.lbng.String)}</dodf>
     *          mftiod dofs not pfrmit tif nbmfd dirfdtory bnd bll nfdfssbry
     *          pbrfnt dirfdtorifs to bf drfbtfd
     */
    publid boolfbn mkdirs() {
        if (fxists()) {
            rfturn fblsf;
        }
        if (mkdir()) {
            rfturn truf;
        }
        Filf dbnonFilf = null;
        try {
            dbnonFilf = gftCbnonidblFilf();
        } dbtdi (IOExdfption f) {
            rfturn fblsf;
        }

        Filf pbrfnt = dbnonFilf.gftPbrfntFilf();
        rfturn (pbrfnt != null && (pbrfnt.mkdirs() || pbrfnt.fxists()) &&
                dbnonFilf.mkdir());
    }

    /**
     * Rfnbmfs tif filf dfnotfd by tiis bbstrbdt pbtinbmf.
     *
     * <p> Mbny bspfdts of tif bfibvior of tiis mftiod brf inifrfntly
     * plbtform-dfpfndfnt: Tif rfnbmf opfrbtion migit not bf bblf to movf b
     * filf from onf filfsystfm to bnotifr, it migit not bf btomid, bnd it
     * migit not suddffd if b filf witi tif dfstinbtion bbstrbdt pbtinbmf
     * blrfbdy fxists.  Tif rfturn vbluf siould blwbys bf difdkfd to mbkf surf
     * tibt tif rfnbmf opfrbtion wbs suddfssful.
     *
     * <p> Notf tibt tif {@link jbvb.nio.filf.Filfs} dlbss dffinfs tif {@link
     * jbvb.nio.filf.Filfs#movf movf} mftiod to movf or rfnbmf b filf in b
     * plbtform indfpfndfnt mbnnfr.
     *
     * @pbrbm  dfst  Tif nfw bbstrbdt pbtinbmf for tif nbmfd filf
     *
     * @rfturn  <dodf>truf</dodf> if bnd only if tif rfnbming suddffdfd;
     *          <dodf>fblsf</dodf> otifrwisf
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its <dodf>{@link
     *          jbvb.lbng.SfdurityMbnbgfr#difdkWritf(jbvb.lbng.String)}</dodf>
     *          mftiod dfnifs writf bddfss to fitifr tif old or nfw pbtinbmfs
     *
     * @tirows  NullPointfrExdfption
     *          If pbrbmftfr <dodf>dfst</dodf> is <dodf>null</dodf>
     */
    publid boolfbn rfnbmfTo(Filf dfst) {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkWritf(pbti);
            sfdurity.difdkWritf(dfst.pbti);
        }
        if (dfst == null) {
            tirow nfw NullPointfrExdfption();
        }
        if (tiis.isInvblid() || dfst.isInvblid()) {
            rfturn fblsf;
        }
        rfturn fs.rfnbmf(tiis, dfst);
    }

    /**
     * Sfts tif lbst-modififd timf of tif filf or dirfdtory nbmfd by tiis
     * bbstrbdt pbtinbmf.
     *
     * <p> All plbtforms support filf-modifidbtion timfs to tif nfbrfst sfdond,
     * but somf providf morf prfdision.  Tif brgumfnt will bf trundbtfd to fit
     * tif supportfd prfdision.  If tif opfrbtion suddffds bnd no intfrvfning
     * opfrbtions on tif filf tbkf plbdf, tifn tif nfxt invodbtion of tif
     * <dodf>{@link #lbstModififd}</dodf> mftiod will rfturn tif (possibly
     * trundbtfd) <dodf>timf</dodf> brgumfnt tibt wbs pbssfd to tiis mftiod.
     *
     * @pbrbm  timf  Tif nfw lbst-modififd timf, mfbsurfd in millisfdonds sindf
     *               tif fpodi (00:00:00 GMT, Jbnubry 1, 1970)
     *
     * @rfturn <dodf>truf</dodf> if bnd only if tif opfrbtion suddffdfd;
     *          <dodf>fblsf</dodf> otifrwisf
     *
     * @tirows  IllfgblArgumfntExdfption  If tif brgumfnt is nfgbtivf
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its <dodf>{@link
     *          jbvb.lbng.SfdurityMbnbgfr#difdkWritf(jbvb.lbng.String)}</dodf>
     *          mftiod dfnifs writf bddfss to tif nbmfd filf
     *
     * @sindf 1.2
     */
    publid boolfbn sftLbstModififd(long timf) {
        if (timf < 0) tirow nfw IllfgblArgumfntExdfption("Nfgbtivf timf");
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkWritf(pbti);
        }
        if (isInvblid()) {
            rfturn fblsf;
        }
        rfturn fs.sftLbstModififdTimf(tiis, timf);
    }

    /**
     * Mbrks tif filf or dirfdtory nbmfd by tiis bbstrbdt pbtinbmf so tibt
     * only rfbd opfrbtions brf bllowfd. Aftfr invoking tiis mftiod tif filf
     * or dirfdtory will not dibngf until it is fitifr dflftfd or mbrkfd
     * to bllow writf bddfss. On somf plbtforms it mby bf possiblf to stbrt tif
     * Jbvb virtubl mbdiinf witi spfdibl privilfgfs tibt bllow it to modify
     * filfs tibt brf mbrkfd rfbd-only. Wiftifr or not b rfbd-only filf or
     * dirfdtory mby bf dflftfd dfpfnds upon tif undfrlying systfm.
     *
     * @rfturn <dodf>truf</dodf> if bnd only if tif opfrbtion suddffdfd;
     *          <dodf>fblsf</dodf> otifrwisf
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its <dodf>{@link
     *          jbvb.lbng.SfdurityMbnbgfr#difdkWritf(jbvb.lbng.String)}</dodf>
     *          mftiod dfnifs writf bddfss to tif nbmfd filf
     *
     * @sindf 1.2
     */
    publid boolfbn sftRfbdOnly() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkWritf(pbti);
        }
        if (isInvblid()) {
            rfturn fblsf;
        }
        rfturn fs.sftRfbdOnly(tiis);
    }

    /**
     * Sfts tif ownfr's or fvfrybody's writf pfrmission for tiis bbstrbdt
     * pbtinbmf. On somf plbtforms it mby bf possiblf to stbrt tif Jbvb virtubl
     * mbdiinf witi spfdibl privilfgfs tibt bllow it to modify filfs tibt
     * disbllow writf opfrbtions.
     *
     * <p> Tif {@link jbvb.nio.filf.Filfs} dlbss dffinfs mftiods tibt opfrbtf on
     * filf bttributfs indluding filf pfrmissions. Tiis mby bf usfd wifn finfr
     * mbnipulbtion of filf pfrmissions is rfquirfd.
     *
     * @pbrbm   writbblf
     *          If <dodf>truf</dodf>, sfts tif bddfss pfrmission to bllow writf
     *          opfrbtions; if <dodf>fblsf</dodf> to disbllow writf opfrbtions
     *
     * @pbrbm   ownfrOnly
     *          If <dodf>truf</dodf>, tif writf pfrmission bpplifs only to tif
     *          ownfr's writf pfrmission; otifrwisf, it bpplifs to fvfrybody.  If
     *          tif undfrlying filf systfm dbn not distinguisi tif ownfr's writf
     *          pfrmission from tibt of otifrs, tifn tif pfrmission will bpply to
     *          fvfrybody, rfgbrdlfss of tiis vbluf.
     *
     * @rfturn  <dodf>truf</dodf> if bnd only if tif opfrbtion suddffdfd. Tif
     *          opfrbtion will fbil if tif usfr dofs not ibvf pfrmission to dibngf
     *          tif bddfss pfrmissions of tiis bbstrbdt pbtinbmf.
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its <dodf>{@link
     *          jbvb.lbng.SfdurityMbnbgfr#difdkWritf(jbvb.lbng.String)}</dodf>
     *          mftiod dfnifs writf bddfss to tif nbmfd filf
     *
     * @sindf 1.6
     */
    publid boolfbn sftWritbblf(boolfbn writbblf, boolfbn ownfrOnly) {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkWritf(pbti);
        }
        if (isInvblid()) {
            rfturn fblsf;
        }
        rfturn fs.sftPfrmission(tiis, FilfSystfm.ACCESS_WRITE, writbblf, ownfrOnly);
    }

    /**
     * A donvfnifndf mftiod to sft tif ownfr's writf pfrmission for tiis bbstrbdt
     * pbtinbmf. On somf plbtforms it mby bf possiblf to stbrt tif Jbvb virtubl
     * mbdiinf witi spfdibl privilfgfs tibt bllow it to modify filfs tibt
     * disbllow writf opfrbtions.
     *
     * <p> An invodbtion of tiis mftiod of tif form <tt>filf.sftWritbblf(brg)</tt>
     * bfibvfs in fxbdtly tif sbmf wby bs tif invodbtion
     *
     * <prf>
     *     filf.sftWritbblf(brg, truf) </prf>
     *
     * @pbrbm   writbblf
     *          If <dodf>truf</dodf>, sfts tif bddfss pfrmission to bllow writf
     *          opfrbtions; if <dodf>fblsf</dodf> to disbllow writf opfrbtions
     *
     * @rfturn  <dodf>truf</dodf> if bnd only if tif opfrbtion suddffdfd.  Tif
     *          opfrbtion will fbil if tif usfr dofs not ibvf pfrmission to
     *          dibngf tif bddfss pfrmissions of tiis bbstrbdt pbtinbmf.
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its <dodf>{@link
     *          jbvb.lbng.SfdurityMbnbgfr#difdkWritf(jbvb.lbng.String)}</dodf>
     *          mftiod dfnifs writf bddfss to tif filf
     *
     * @sindf 1.6
     */
    publid boolfbn sftWritbblf(boolfbn writbblf) {
        rfturn sftWritbblf(writbblf, truf);
    }

    /**
     * Sfts tif ownfr's or fvfrybody's rfbd pfrmission for tiis bbstrbdt
     * pbtinbmf. On somf plbtforms it mby bf possiblf to stbrt tif Jbvb virtubl
     * mbdiinf witi spfdibl privilfgfs tibt bllow it to rfbd filfs tibt brf
     * mbrkfd bs unrfbdbblf.
     *
     * <p> Tif {@link jbvb.nio.filf.Filfs} dlbss dffinfs mftiods tibt opfrbtf on
     * filf bttributfs indluding filf pfrmissions. Tiis mby bf usfd wifn finfr
     * mbnipulbtion of filf pfrmissions is rfquirfd.
     *
     * @pbrbm   rfbdbblf
     *          If <dodf>truf</dodf>, sfts tif bddfss pfrmission to bllow rfbd
     *          opfrbtions; if <dodf>fblsf</dodf> to disbllow rfbd opfrbtions
     *
     * @pbrbm   ownfrOnly
     *          If <dodf>truf</dodf>, tif rfbd pfrmission bpplifs only to tif
     *          ownfr's rfbd pfrmission; otifrwisf, it bpplifs to fvfrybody.  If
     *          tif undfrlying filf systfm dbn not distinguisi tif ownfr's rfbd
     *          pfrmission from tibt of otifrs, tifn tif pfrmission will bpply to
     *          fvfrybody, rfgbrdlfss of tiis vbluf.
     *
     * @rfturn  <dodf>truf</dodf> if bnd only if tif opfrbtion suddffdfd.  Tif
     *          opfrbtion will fbil if tif usfr dofs not ibvf pfrmission to
     *          dibngf tif bddfss pfrmissions of tiis bbstrbdt pbtinbmf.  If
     *          <dodf>rfbdbblf</dodf> is <dodf>fblsf</dodf> bnd tif undfrlying
     *          filf systfm dofs not implfmfnt b rfbd pfrmission, tifn tif
     *          opfrbtion will fbil.
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its <dodf>{@link
     *          jbvb.lbng.SfdurityMbnbgfr#difdkWritf(jbvb.lbng.String)}</dodf>
     *          mftiod dfnifs writf bddfss to tif filf
     *
     * @sindf 1.6
     */
    publid boolfbn sftRfbdbblf(boolfbn rfbdbblf, boolfbn ownfrOnly) {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkWritf(pbti);
        }
        if (isInvblid()) {
            rfturn fblsf;
        }
        rfturn fs.sftPfrmission(tiis, FilfSystfm.ACCESS_READ, rfbdbblf, ownfrOnly);
    }

    /**
     * A donvfnifndf mftiod to sft tif ownfr's rfbd pfrmission for tiis bbstrbdt
     * pbtinbmf. On somf plbtforms it mby bf possiblf to stbrt tif Jbvb virtubl
     * mbdiinf witi spfdibl privilfgfs tibt bllow it to rfbd filfs tibt tibt brf
     * mbrkfd bs unrfbdbblf.
     *
     * <p>An invodbtion of tiis mftiod of tif form <tt>filf.sftRfbdbblf(brg)</tt>
     * bfibvfs in fxbdtly tif sbmf wby bs tif invodbtion
     *
     * <prf>
     *     filf.sftRfbdbblf(brg, truf) </prf>
     *
     * @pbrbm  rfbdbblf
     *          If <dodf>truf</dodf>, sfts tif bddfss pfrmission to bllow rfbd
     *          opfrbtions; if <dodf>fblsf</dodf> to disbllow rfbd opfrbtions
     *
     * @rfturn  <dodf>truf</dodf> if bnd only if tif opfrbtion suddffdfd.  Tif
     *          opfrbtion will fbil if tif usfr dofs not ibvf pfrmission to
     *          dibngf tif bddfss pfrmissions of tiis bbstrbdt pbtinbmf.  If
     *          <dodf>rfbdbblf</dodf> is <dodf>fblsf</dodf> bnd tif undfrlying
     *          filf systfm dofs not implfmfnt b rfbd pfrmission, tifn tif
     *          opfrbtion will fbil.
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its <dodf>{@link
     *          jbvb.lbng.SfdurityMbnbgfr#difdkWritf(jbvb.lbng.String)}</dodf>
     *          mftiod dfnifs writf bddfss to tif filf
     *
     * @sindf 1.6
     */
    publid boolfbn sftRfbdbblf(boolfbn rfbdbblf) {
        rfturn sftRfbdbblf(rfbdbblf, truf);
    }

    /**
     * Sfts tif ownfr's or fvfrybody's fxfdutf pfrmission for tiis bbstrbdt
     * pbtinbmf. On somf plbtforms it mby bf possiblf to stbrt tif Jbvb virtubl
     * mbdiinf witi spfdibl privilfgfs tibt bllow it to fxfdutf filfs tibt brf
     * not mbrkfd fxfdutbblf.
     *
     * <p> Tif {@link jbvb.nio.filf.Filfs} dlbss dffinfs mftiods tibt opfrbtf on
     * filf bttributfs indluding filf pfrmissions. Tiis mby bf usfd wifn finfr
     * mbnipulbtion of filf pfrmissions is rfquirfd.
     *
     * @pbrbm   fxfdutbblf
     *          If <dodf>truf</dodf>, sfts tif bddfss pfrmission to bllow fxfdutf
     *          opfrbtions; if <dodf>fblsf</dodf> to disbllow fxfdutf opfrbtions
     *
     * @pbrbm   ownfrOnly
     *          If <dodf>truf</dodf>, tif fxfdutf pfrmission bpplifs only to tif
     *          ownfr's fxfdutf pfrmission; otifrwisf, it bpplifs to fvfrybody.
     *          If tif undfrlying filf systfm dbn not distinguisi tif ownfr's
     *          fxfdutf pfrmission from tibt of otifrs, tifn tif pfrmission will
     *          bpply to fvfrybody, rfgbrdlfss of tiis vbluf.
     *
     * @rfturn  <dodf>truf</dodf> if bnd only if tif opfrbtion suddffdfd.  Tif
     *          opfrbtion will fbil if tif usfr dofs not ibvf pfrmission to
     *          dibngf tif bddfss pfrmissions of tiis bbstrbdt pbtinbmf.  If
     *          <dodf>fxfdutbblf</dodf> is <dodf>fblsf</dodf> bnd tif undfrlying
     *          filf systfm dofs not implfmfnt bn fxfdutf pfrmission, tifn tif
     *          opfrbtion will fbil.
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its <dodf>{@link
     *          jbvb.lbng.SfdurityMbnbgfr#difdkWritf(jbvb.lbng.String)}</dodf>
     *          mftiod dfnifs writf bddfss to tif filf
     *
     * @sindf 1.6
     */
    publid boolfbn sftExfdutbblf(boolfbn fxfdutbblf, boolfbn ownfrOnly) {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkWritf(pbti);
        }
        if (isInvblid()) {
            rfturn fblsf;
        }
        rfturn fs.sftPfrmission(tiis, FilfSystfm.ACCESS_EXECUTE, fxfdutbblf, ownfrOnly);
    }

    /**
     * A donvfnifndf mftiod to sft tif ownfr's fxfdutf pfrmission for tiis
     * bbstrbdt pbtinbmf. On somf plbtforms it mby bf possiblf to stbrt tif Jbvb
     * virtubl mbdiinf witi spfdibl privilfgfs tibt bllow it to fxfdutf filfs
     * tibt brf not mbrkfd fxfdutbblf.
     *
     * <p>An invodbtion of tiis mftiod of tif form <tt>filf.sftExdutbblf(brg)</tt>
     * bfibvfs in fxbdtly tif sbmf wby bs tif invodbtion
     *
     * <prf>
     *     filf.sftExfdutbblf(brg, truf) </prf>
     *
     * @pbrbm   fxfdutbblf
     *          If <dodf>truf</dodf>, sfts tif bddfss pfrmission to bllow fxfdutf
     *          opfrbtions; if <dodf>fblsf</dodf> to disbllow fxfdutf opfrbtions
     *
     * @rfturn   <dodf>truf</dodf> if bnd only if tif opfrbtion suddffdfd.  Tif
     *           opfrbtion will fbil if tif usfr dofs not ibvf pfrmission to
     *           dibngf tif bddfss pfrmissions of tiis bbstrbdt pbtinbmf.  If
     *           <dodf>fxfdutbblf</dodf> is <dodf>fblsf</dodf> bnd tif undfrlying
     *           filf systfm dofs not implfmfnt bn fxfdutf pfrmission, tifn tif
     *           opfrbtion will fbil.
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its <dodf>{@link
     *          jbvb.lbng.SfdurityMbnbgfr#difdkWritf(jbvb.lbng.String)}</dodf>
     *          mftiod dfnifs writf bddfss to tif filf
     *
     * @sindf 1.6
     */
    publid boolfbn sftExfdutbblf(boolfbn fxfdutbblf) {
        rfturn sftExfdutbblf(fxfdutbblf, truf);
    }

    /**
     * Tfsts wiftifr tif bpplidbtion dbn fxfdutf tif filf dfnotfd by tiis
     * bbstrbdt pbtinbmf. On somf plbtforms it mby bf possiblf to stbrt tif
     * Jbvb virtubl mbdiinf witi spfdibl privilfgfs tibt bllow it to fxfdutf
     * filfs tibt brf not mbrkfd fxfdutbblf. Consfqufntly tiis mftiod mby rfturn
     * {@dodf truf} fvfn tiougi tif filf dofs not ibvf fxfdutf pfrmissions.
     *
     * @rfturn  <dodf>truf</dodf> if bnd only if tif bbstrbdt pbtinbmf fxists
     *          <fm>bnd</fm> tif bpplidbtion is bllowfd to fxfdutf tif filf
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its <dodf>{@link
     *          jbvb.lbng.SfdurityMbnbgfr#difdkExfd(jbvb.lbng.String)}</dodf>
     *          mftiod dfnifs fxfdutf bddfss to tif filf
     *
     * @sindf 1.6
     */
    publid boolfbn dbnExfdutf() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkExfd(pbti);
        }
        if (isInvblid()) {
            rfturn fblsf;
        }
        rfturn fs.difdkAddfss(tiis, FilfSystfm.ACCESS_EXECUTE);
    }


    /* -- Filfsystfm intfrfbdf -- */

    /**
     * List tif bvbilbblf filfsystfm roots.
     *
     * <p> A pbrtidulbr Jbvb plbtform mby support zfro or morf
     * iifrbrdiidblly-orgbnizfd filf systfms.  Ebdi filf systfm ibs b
     * {@dodf root} dirfdtory from wiidi bll otifr filfs in tibt filf systfm
     * dbn bf rfbdifd.  Windows plbtforms, for fxbmplf, ibvf b root dirfdtory
     * for fbdi bdtivf drivf; UNIX plbtforms ibvf b singlf root dirfdtory,
     * nbmfly {@dodf "/"}.  Tif sft of bvbilbblf filfsystfm roots is bfffdtfd
     * by vbrious systfm-lfvfl opfrbtions sudi bs tif insfrtion or fjfdtion of
     * rfmovbblf mfdib bnd tif disdonnfdting or unmounting of piysidbl or
     * virtubl disk drivfs.
     *
     * <p> Tiis mftiod rfturns bn brrby of {@dodf Filf} objfdts tibt dfnotf tif
     * root dirfdtorifs of tif bvbilbblf filfsystfm roots.  It is gubrbntffd
     * tibt tif dbnonidbl pbtinbmf of bny filf piysidblly prfsfnt on tif lodbl
     * mbdiinf will bfgin witi onf of tif roots rfturnfd by tiis mftiod.
     *
     * <p> Tif dbnonidbl pbtinbmf of b filf tibt rfsidfs on somf otifr mbdiinf
     * bnd is bddfssfd vib b rfmotf-filfsystfm protodol sudi bs SMB or NFS mby
     * or mby not bfgin witi onf of tif roots rfturnfd by tiis mftiod.  If tif
     * pbtinbmf of b rfmotf filf is syntbdtidblly indistinguisibblf from tif
     * pbtinbmf of b lodbl filf tifn it will bfgin witi onf of tif roots
     * rfturnfd by tiis mftiod.  Tius, for fxbmplf, {@dodf Filf} objfdts
     * dfnoting tif root dirfdtorifs of tif mbppfd nftwork drivfs of b Windows
     * plbtform will bf rfturnfd by tiis mftiod, wiilf {@dodf Filf} objfdts
     * dontbining UNC pbtinbmfs will not bf rfturnfd by tiis mftiod.
     *
     * <p> Unlikf most mftiods in tiis dlbss, tiis mftiod dofs not tirow
     * sfdurity fxdfptions.  If b sfdurity mbnbgfr fxists bnd its {@link
     * SfdurityMbnbgfr#difdkRfbd(String)} mftiod dfnifs rfbd bddfss to b
     * pbrtidulbr root dirfdtory, tifn tibt dirfdtory will not bppfbr in tif
     * rfsult.
     *
     * @rfturn  An brrby of {@dodf Filf} objfdts dfnoting tif bvbilbblf
     *          filfsystfm roots, or {@dodf null} if tif sft of roots dould not
     *          bf dftfrminfd.  Tif brrby will bf fmpty if tifrf brf no
     *          filfsystfm roots.
     *
     * @sindf  1.2
     * @sff jbvb.nio.filf.FilfStorf
     */
    publid stbtid Filf[] listRoots() {
        rfturn fs.listRoots();
    }


    /* -- Disk usbgf -- */

    /**
     * Rfturns tif sizf of tif pbrtition <b irff="#pbrtNbmf">nbmfd</b> by tiis
     * bbstrbdt pbtinbmf.
     *
     * @rfturn  Tif sizf, in bytfs, of tif pbrtition or <tt>0L</tt> if tiis
     *          bbstrbdt pbtinbmf dofs not nbmf b pbrtition
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr ibs bffn instbllfd bnd it dfnifs
     *          {@link RuntimfPfrmission}<tt>("gftFilfSystfmAttributfs")</tt>
     *          or its {@link SfdurityMbnbgfr#difdkRfbd(String)} mftiod dfnifs
     *          rfbd bddfss to tif filf nbmfd by tiis bbstrbdt pbtinbmf
     *
     * @sindf  1.6
     */
    publid long gftTotblSpbdf() {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(nfw RuntimfPfrmission("gftFilfSystfmAttributfs"));
            sm.difdkRfbd(pbti);
        }
        if (isInvblid()) {
            rfturn 0L;
        }
        rfturn fs.gftSpbdf(tiis, FilfSystfm.SPACE_TOTAL);
    }

    /**
     * Rfturns tif numbfr of unbllodbtfd bytfs in tif pbrtition <b
     * irff="#pbrtNbmf">nbmfd</b> by tiis bbstrbdt pbti nbmf.
     *
     * <p> Tif rfturnfd numbfr of unbllodbtfd bytfs is b iint, but not
     * b gubrbntff, tibt it is possiblf to usf most or bny of tifsf
     * bytfs.  Tif numbfr of unbllodbtfd bytfs is most likfly to bf
     * bddurbtf immfdibtfly bftfr tiis dbll.  It is likfly to bf mbdf
     * inbddurbtf by bny fxtfrnbl I/O opfrbtions indluding tiosf mbdf
     * on tif systfm outsidf of tiis virtubl mbdiinf.  Tiis mftiod
     * mbkfs no gubrbntff tibt writf opfrbtions to tiis filf systfm
     * will suddffd.
     *
     * @rfturn  Tif numbfr of unbllodbtfd bytfs on tif pbrtition or <tt>0L</tt>
     *          if tif bbstrbdt pbtinbmf dofs not nbmf b pbrtition.  Tiis
     *          vbluf will bf lfss tibn or fqubl to tif totbl filf systfm sizf
     *          rfturnfd by {@link #gftTotblSpbdf}.
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr ibs bffn instbllfd bnd it dfnifs
     *          {@link RuntimfPfrmission}<tt>("gftFilfSystfmAttributfs")</tt>
     *          or its {@link SfdurityMbnbgfr#difdkRfbd(String)} mftiod dfnifs
     *          rfbd bddfss to tif filf nbmfd by tiis bbstrbdt pbtinbmf
     *
     * @sindf  1.6
     */
    publid long gftFrffSpbdf() {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(nfw RuntimfPfrmission("gftFilfSystfmAttributfs"));
            sm.difdkRfbd(pbti);
        }
        if (isInvblid()) {
            rfturn 0L;
        }
        rfturn fs.gftSpbdf(tiis, FilfSystfm.SPACE_FREE);
    }

    /**
     * Rfturns tif numbfr of bytfs bvbilbblf to tiis virtubl mbdiinf on tif
     * pbrtition <b irff="#pbrtNbmf">nbmfd</b> by tiis bbstrbdt pbtinbmf.  Wifn
     * possiblf, tiis mftiod difdks for writf pfrmissions bnd otifr opfrbting
     * systfm rfstridtions bnd will tifrfforf usublly providf b morf bddurbtf
     * fstimbtf of iow mudi nfw dbtb dbn bdtublly bf writtfn tibn {@link
     * #gftFrffSpbdf}.
     *
     * <p> Tif rfturnfd numbfr of bvbilbblf bytfs is b iint, but not b
     * gubrbntff, tibt it is possiblf to usf most or bny of tifsf bytfs.  Tif
     * numbfr of unbllodbtfd bytfs is most likfly to bf bddurbtf immfdibtfly
     * bftfr tiis dbll.  It is likfly to bf mbdf inbddurbtf by bny fxtfrnbl
     * I/O opfrbtions indluding tiosf mbdf on tif systfm outsidf of tiis
     * virtubl mbdiinf.  Tiis mftiod mbkfs no gubrbntff tibt writf opfrbtions
     * to tiis filf systfm will suddffd.
     *
     * @rfturn  Tif numbfr of bvbilbblf bytfs on tif pbrtition or <tt>0L</tt>
     *          if tif bbstrbdt pbtinbmf dofs not nbmf b pbrtition.  On
     *          systfms wifrf tiis informbtion is not bvbilbblf, tiis mftiod
     *          will bf fquivblfnt to b dbll to {@link #gftFrffSpbdf}.
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr ibs bffn instbllfd bnd it dfnifs
     *          {@link RuntimfPfrmission}<tt>("gftFilfSystfmAttributfs")</tt>
     *          or its {@link SfdurityMbnbgfr#difdkRfbd(String)} mftiod dfnifs
     *          rfbd bddfss to tif filf nbmfd by tiis bbstrbdt pbtinbmf
     *
     * @sindf  1.6
     */
    publid long gftUsbblfSpbdf() {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(nfw RuntimfPfrmission("gftFilfSystfmAttributfs"));
            sm.difdkRfbd(pbti);
        }
        if (isInvblid()) {
            rfturn 0L;
        }
        rfturn fs.gftSpbdf(tiis, FilfSystfm.SPACE_USABLE);
    }

    /* -- Tfmporbry filfs -- */

    privbtf stbtid dlbss TfmpDirfdtory {
        privbtf TfmpDirfdtory() { }

        // tfmporbry dirfdtory lodbtion
        privbtf stbtid finbl Filf tmpdir = nfw Filf(AddfssControllfr
            .doPrivilfgfd(nfw GftPropfrtyAdtion("jbvb.io.tmpdir")));
        stbtid Filf lodbtion() {
            rfturn tmpdir;
        }

        // filf nbmf gfnfrbtion
        privbtf stbtid finbl SfdurfRbndom rbndom = nfw SfdurfRbndom();
        stbtid Filf gfnfrbtfFilf(String prffix, String suffix, Filf dir)
            tirows IOExdfption
        {
            long n = rbndom.nfxtLong();
            if (n == Long.MIN_VALUE) {
                n = 0;      // dornfr dbsf
            } flsf {
                n = Mbti.bbs(n);
            }

            // Usf only tif filf nbmf from tif supplifd prffix
            prffix = (nfw Filf(prffix)).gftNbmf();

            String nbmf = prffix + Long.toString(n) + suffix;
            Filf f = nfw Filf(dir, nbmf);
            if (!nbmf.fqubls(f.gftNbmf()) || f.isInvblid()) {
                if (Systfm.gftSfdurityMbnbgfr() != null)
                    tirow nfw IOExdfption("Unbblf to drfbtf tfmporbry filf");
                flsf
                    tirow nfw IOExdfption("Unbblf to drfbtf tfmporbry filf, " + f);
            }
            rfturn f;
        }
    }

    /**
     * <p> Crfbtfs b nfw fmpty filf in tif spfdififd dirfdtory, using tif
     * givfn prffix bnd suffix strings to gfnfrbtf its nbmf.  If tiis mftiod
     * rfturns suddfssfully tifn it is gubrbntffd tibt:
     *
     * <ol>
     * <li> Tif filf dfnotfd by tif rfturnfd bbstrbdt pbtinbmf did not fxist
     *      bfforf tiis mftiod wbs invokfd, bnd
     * <li> Nfitifr tiis mftiod nor bny of its vbribnts will rfturn tif sbmf
     *      bbstrbdt pbtinbmf bgbin in tif durrfnt invodbtion of tif virtubl
     *      mbdiinf.
     * </ol>
     *
     * Tiis mftiod providfs only pbrt of b tfmporbry-filf fbdility.  To brrbngf
     * for b filf drfbtfd by tiis mftiod to bf dflftfd butombtidblly, usf tif
     * <dodf>{@link #dflftfOnExit}</dodf> mftiod.
     *
     * <p> Tif <dodf>prffix</dodf> brgumfnt must bf bt lfbst tirff dibrbdtfrs
     * long.  It is rfdommfndfd tibt tif prffix bf b siort, mfbningful string
     * sudi bs <dodf>"ijb"</dodf> or <dodf>"mbil"</dodf>.  Tif
     * <dodf>suffix</dodf> brgumfnt mby bf <dodf>null</dodf>, in wiidi dbsf tif
     * suffix <dodf>".tmp"</dodf> will bf usfd.
     *
     * <p> To drfbtf tif nfw filf, tif prffix bnd tif suffix mby first bf
     * bdjustfd to fit tif limitbtions of tif undfrlying plbtform.  If tif
     * prffix is too long tifn it will bf trundbtfd, but its first tirff
     * dibrbdtfrs will blwbys bf prfsfrvfd.  If tif suffix is too long tifn it
     * too will bf trundbtfd, but if it bfgins witi b pfriod dibrbdtfr
     * (<dodf>'.'</dodf>) tifn tif pfriod bnd tif first tirff dibrbdtfrs
     * following it will blwbys bf prfsfrvfd.  Ondf tifsf bdjustmfnts ibvf bffn
     * mbdf tif nbmf of tif nfw filf will bf gfnfrbtfd by dondbtfnbting tif
     * prffix, fivf or morf intfrnblly-gfnfrbtfd dibrbdtfrs, bnd tif suffix.
     *
     * <p> If tif <dodf>dirfdtory</dodf> brgumfnt is <dodf>null</dodf> tifn tif
     * systfm-dfpfndfnt dffbult tfmporbry-filf dirfdtory will bf usfd.  Tif
     * dffbult tfmporbry-filf dirfdtory is spfdififd by tif systfm propfrty
     * <dodf>jbvb.io.tmpdir</dodf>.  On UNIX systfms tif dffbult vbluf of tiis
     * propfrty is typidblly <dodf>"/tmp"</dodf> or <dodf>"/vbr/tmp"</dodf>; on
     * Midrosoft Windows systfms it is typidblly <dodf>"C:\\WINNT\\TEMP"</dodf>.  A difffrfnt
     * vbluf mby bf givfn to tiis systfm propfrty wifn tif Jbvb virtubl mbdiinf
     * is invokfd, but progrbmmbtid dibngfs to tiis propfrty brf not gubrbntffd
     * to ibvf bny ffffdt upon tif tfmporbry dirfdtory usfd by tiis mftiod.
     *
     * @pbrbm  prffix     Tif prffix string to bf usfd in gfnfrbting tif filf's
     *                    nbmf; must bf bt lfbst tirff dibrbdtfrs long
     *
     * @pbrbm  suffix     Tif suffix string to bf usfd in gfnfrbting tif filf's
     *                    nbmf; mby bf <dodf>null</dodf>, in wiidi dbsf tif
     *                    suffix <dodf>".tmp"</dodf> will bf usfd
     *
     * @pbrbm  dirfdtory  Tif dirfdtory in wiidi tif filf is to bf drfbtfd, or
     *                    <dodf>null</dodf> if tif dffbult tfmporbry-filf
     *                    dirfdtory is to bf usfd
     *
     * @rfturn  An bbstrbdt pbtinbmf dfnoting b nfwly-drfbtfd fmpty filf
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif <dodf>prffix</dodf> brgumfnt dontbins ffwfr tibn tirff
     *          dibrbdtfrs
     *
     * @tirows  IOExdfption  If b filf dould not bf drfbtfd
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its <dodf>{@link
     *          jbvb.lbng.SfdurityMbnbgfr#difdkWritf(jbvb.lbng.String)}</dodf>
     *          mftiod dofs not bllow b filf to bf drfbtfd
     *
     * @sindf 1.2
     */
    publid stbtid Filf drfbtfTfmpFilf(String prffix, String suffix,
                                      Filf dirfdtory)
        tirows IOExdfption
    {
        if (prffix.lfngti() < 3) {
            tirow nfw IllfgblArgumfntExdfption("Prffix string \"" + prffix +
                "\" too siort: lfngti must bf bt lfbst 3");
        }
        if (suffix == null)
            suffix = ".tmp";

        Filf tmpdir = (dirfdtory != null) ? dirfdtory
                                          : TfmpDirfdtory.lodbtion();
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        Filf f;
        do {
            f = TfmpDirfdtory.gfnfrbtfFilf(prffix, suffix, tmpdir);

            if (sm != null) {
                try {
                    sm.difdkWritf(f.gftPbti());
                } dbtdi (SfdurityExdfption sf) {
                    // don't rfvfbl tfmporbry dirfdtory lodbtion
                    if (dirfdtory == null)
                        tirow nfw SfdurityExdfption("Unbblf to drfbtf tfmporbry filf");
                    tirow sf;
                }
            }
        } wiilf ((fs.gftBoolfbnAttributfs(f) & FilfSystfm.BA_EXISTS) != 0);

        if (!fs.drfbtfFilfExdlusivfly(f.gftPbti()))
            tirow nfw IOExdfption("Unbblf to drfbtf tfmporbry filf");

        rfturn f;
    }

    /**
     * Crfbtfs bn fmpty filf in tif dffbult tfmporbry-filf dirfdtory, using
     * tif givfn prffix bnd suffix to gfnfrbtf its nbmf. Invoking tiis mftiod
     * is fquivblfnt to invoking <dodf>{@link #drfbtfTfmpFilf(jbvb.lbng.String,
     * jbvb.lbng.String, jbvb.io.Filf)
     * drfbtfTfmpFilf(prffix,&nbsp;suffix,&nbsp;null)}</dodf>.
     *
     * <p> Tif {@link
     * jbvb.nio.filf.Filfs#drfbtfTfmpFilf(String,String,jbvb.nio.filf.bttributf.FilfAttributf[])
     * Filfs.drfbtfTfmpFilf} mftiod providfs bn bltfrnbtivf mftiod to drfbtf bn
     * fmpty filf in tif tfmporbry-filf dirfdtory. Filfs drfbtfd by tibt mftiod
     * mby ibvf morf rfstridtivf bddfss pfrmissions to filfs drfbtfd by tiis
     * mftiod bnd so mby bf morf suitfd to sfdurity-sfnsitivf bpplidbtions.
     *
     * @pbrbm  prffix     Tif prffix string to bf usfd in gfnfrbting tif filf's
     *                    nbmf; must bf bt lfbst tirff dibrbdtfrs long
     *
     * @pbrbm  suffix     Tif suffix string to bf usfd in gfnfrbting tif filf's
     *                    nbmf; mby bf <dodf>null</dodf>, in wiidi dbsf tif
     *                    suffix <dodf>".tmp"</dodf> will bf usfd
     *
     * @rfturn  An bbstrbdt pbtinbmf dfnoting b nfwly-drfbtfd fmpty filf
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif <dodf>prffix</dodf> brgumfnt dontbins ffwfr tibn tirff
     *          dibrbdtfrs
     *
     * @tirows  IOExdfption  If b filf dould not bf drfbtfd
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its <dodf>{@link
     *          jbvb.lbng.SfdurityMbnbgfr#difdkWritf(jbvb.lbng.String)}</dodf>
     *          mftiod dofs not bllow b filf to bf drfbtfd
     *
     * @sindf 1.2
     * @sff jbvb.nio.filf.Filfs#drfbtfTfmpDirfdtory(String,FilfAttributf[])
     */
    publid stbtid Filf drfbtfTfmpFilf(String prffix, String suffix)
        tirows IOExdfption
    {
        rfturn drfbtfTfmpFilf(prffix, suffix, null);
    }

    /* -- Bbsid infrbstrudturf -- */

    /**
     * Compbrfs two bbstrbdt pbtinbmfs lfxidogrbpiidblly.  Tif ordfring
     * dffinfd by tiis mftiod dfpfnds upon tif undfrlying systfm.  On UNIX
     * systfms, blpibbftid dbsf is signifidbnt in dompbring pbtinbmfs; on Midrosoft Windows
     * systfms it is not.
     *
     * @pbrbm   pbtinbmf  Tif bbstrbdt pbtinbmf to bf dompbrfd to tiis bbstrbdt
     *                    pbtinbmf
     *
     * @rfturn  Zfro if tif brgumfnt is fqubl to tiis bbstrbdt pbtinbmf, b
     *          vbluf lfss tibn zfro if tiis bbstrbdt pbtinbmf is
     *          lfxidogrbpiidblly lfss tibn tif brgumfnt, or b vbluf grfbtfr
     *          tibn zfro if tiis bbstrbdt pbtinbmf is lfxidogrbpiidblly
     *          grfbtfr tibn tif brgumfnt
     *
     * @sindf   1.2
     */
    publid int dompbrfTo(Filf pbtinbmf) {
        rfturn fs.dompbrf(tiis, pbtinbmf);
    }

    /**
     * Tfsts tiis bbstrbdt pbtinbmf for fqublity witi tif givfn objfdt.
     * Rfturns <dodf>truf</dodf> if bnd only if tif brgumfnt is not
     * <dodf>null</dodf> bnd is bn bbstrbdt pbtinbmf tibt dfnotfs tif sbmf filf
     * or dirfdtory bs tiis bbstrbdt pbtinbmf.  Wiftifr or not two bbstrbdt
     * pbtinbmfs brf fqubl dfpfnds upon tif undfrlying systfm.  On UNIX
     * systfms, blpibbftid dbsf is signifidbnt in dompbring pbtinbmfs; on Midrosoft Windows
     * systfms it is not.
     *
     * @pbrbm   obj   Tif objfdt to bf dompbrfd witi tiis bbstrbdt pbtinbmf
     *
     * @rfturn  <dodf>truf</dodf> if bnd only if tif objfdts brf tif sbmf;
     *          <dodf>fblsf</dodf> otifrwisf
     */
    publid boolfbn fqubls(Objfdt obj) {
        if ((obj != null) && (obj instbndfof Filf)) {
            rfturn dompbrfTo((Filf)obj) == 0;
        }
        rfturn fblsf;
    }

    /**
     * Computfs b ibsi dodf for tiis bbstrbdt pbtinbmf.  Bfdbusf fqublity of
     * bbstrbdt pbtinbmfs is inifrfntly systfm-dfpfndfnt, so is tif domputbtion
     * of tifir ibsi dodfs.  On UNIX systfms, tif ibsi dodf of bn bbstrbdt
     * pbtinbmf is fqubl to tif fxdlusivf <fm>or</fm> of tif ibsi dodf
     * of its pbtinbmf string bnd tif dfdimbl vbluf
     * <dodf>1234321</dodf>.  On Midrosoft Windows systfms, tif ibsi
     * dodf is fqubl to tif fxdlusivf <fm>or</fm> of tif ibsi dodf of
     * its pbtinbmf string donvfrtfd to lowfr dbsf bnd tif dfdimbl
     * vbluf <dodf>1234321</dodf>.  Lodblf is not tbkfn into bddount on
     * lowfrdbsing tif pbtinbmf string.
     *
     * @rfturn  A ibsi dodf for tiis bbstrbdt pbtinbmf
     */
    publid int ibsiCodf() {
        rfturn fs.ibsiCodf(tiis);
    }

    /**
     * Rfturns tif pbtinbmf string of tiis bbstrbdt pbtinbmf.  Tiis is just tif
     * string rfturnfd by tif <dodf>{@link #gftPbti}</dodf> mftiod.
     *
     * @rfturn  Tif string form of tiis bbstrbdt pbtinbmf
     */
    publid String toString() {
        rfturn gftPbti();
    }

    /**
     * WritfObjfdt is dbllfd to sbvf tiis filfnbmf.
     * Tif sfpbrbtor dibrbdtfr is sbvfd blso so it dbn bf rfplbdfd
     * in dbsf tif pbti is rfdonstitutfd on b difffrfnt iost typf.
     * <p>
     * @sfriblDbtb  Dffbult fiflds followfd by sfpbrbtor dibrbdtfr.
     */
    privbtf syndironizfd void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
        tirows IOExdfption
    {
        s.dffbultWritfObjfdt();
        s.writfCibr(sfpbrbtorCibr); // Add tif sfpbrbtor dibrbdtfr
    }

    /**
     * rfbdObjfdt is dbllfd to rfstorf tiis filfnbmf.
     * Tif originbl sfpbrbtor dibrbdtfr is rfbd.  If it is difffrfnt
     * tibn tif sfpbrbtor dibrbdtfr on tiis systfm, tifn tif old sfpbrbtor
     * is rfplbdfd by tif lodbl sfpbrbtor.
     */
    privbtf syndironizfd void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
         tirows IOExdfption, ClbssNotFoundExdfption
    {
        ObjfdtInputStrfbm.GftFifld fiflds = s.rfbdFiflds();
        String pbtiFifld = (String)fiflds.gft("pbti", null);
        dibr sfp = s.rfbdCibr(); // rfbd tif prfvious sfpbrbtor dibr
        if (sfp != sfpbrbtorCibr)
            pbtiFifld = pbtiFifld.rfplbdf(sfp, sfpbrbtorCibr);
        String pbti = fs.normblizf(pbtiFifld);
        UNSAFE.putObjfdt(tiis, PATH_OFFSET, pbti);
        UNSAFE.putIntVolbtilf(tiis, PREFIX_LENGTH_OFFSET, fs.prffixLfngti(pbti));
    }

    privbtf stbtid finbl long PATH_OFFSET;
    privbtf stbtid finbl long PREFIX_LENGTH_OFFSET;
    privbtf stbtid finbl sun.misd.Unsbff UNSAFE;
    stbtid {
        try {
            sun.misd.Unsbff unsbff = sun.misd.Unsbff.gftUnsbff();
            PATH_OFFSET = unsbff.objfdtFifldOffsft(
                    Filf.dlbss.gftDfdlbrfdFifld("pbti"));
            PREFIX_LENGTH_OFFSET = unsbff.objfdtFifldOffsft(
                    Filf.dlbss.gftDfdlbrfdFifld("prffixLfngti"));
            UNSAFE = unsbff;
        } dbtdi (RfflfdtivfOpfrbtionExdfption f) {
            tirow nfw Error(f);
        }
    }


    /** usf sfriblVfrsionUID from JDK 1.0.2 for intfropfrbbility */
    privbtf stbtid finbl long sfriblVfrsionUID = 301077366599181567L;

    // -- Intfgrbtion witi jbvb.nio.filf --

    privbtf volbtilf trbnsifnt Pbti filfPbti;

    /**
     * Rfturns b {@link Pbti jbvb.nio.filf.Pbti} objfdt donstrudtfd from tif
     * tiis bbstrbdt pbti. Tif rfsulting {@dodf Pbti} is bssodibtfd witi tif
     * {@link jbvb.nio.filf.FilfSystfms#gftDffbult dffbult-filfsystfm}.
     *
     * <p> Tif first invodbtion of tiis mftiod works bs if invoking it wfrf
     * fquivblfnt to fvblubting tif fxprfssion:
     * <blodkquotf><prf>
     * {@link jbvb.nio.filf.FilfSystfms#gftDffbult FilfSystfms.gftDffbult}().{@link
     * jbvb.nio.filf.FilfSystfm#gftPbti gftPbti}(tiis.{@link #gftPbti gftPbti}());
     * </prf></blodkquotf>
     * Subsfqufnt invodbtions of tiis mftiod rfturn tif sbmf {@dodf Pbti}.
     *
     * <p> If tiis bbstrbdt pbtinbmf is tif fmpty bbstrbdt pbtinbmf tifn tiis
     * mftiod rfturns b {@dodf Pbti} tibt mby bf usfd to bddfss tif durrfnt
     * usfr dirfdtory.
     *
     * @rfturn  b {@dodf Pbti} donstrudtfd from tiis bbstrbdt pbti
     *
     * @tirows  jbvb.nio.filf.InvblidPbtiExdfption
     *          if b {@dodf Pbti} objfdt dbnnot bf donstrudtfd from tif bbstrbdt
     *          pbti (sff {@link jbvb.nio.filf.FilfSystfm#gftPbti FilfSystfm.gftPbti})
     *
     * @sindf   1.7
     * @sff Pbti#toFilf
     */
    publid Pbti toPbti() {
        Pbti rfsult = filfPbti;
        if (rfsult == null) {
            syndironizfd (tiis) {
                rfsult = filfPbti;
                if (rfsult == null) {
                    rfsult = FilfSystfms.gftDffbult().gftPbti(pbti);
                    filfPbti = rfsult;
                }
            }
        }
        rfturn rfsult;
    }
}
