/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.filf;

import jbvb.nio.filf.bttributf.*;
import jbvb.nio.filf.spi.FilfSystfmProvidfr;
import jbvb.util.Sft;
import jbvb.io.Closfbblf;
import jbvb.io.IOExdfption;

/**
 * Providfs bn intfrfbdf to b filf systfm bnd is tif fbdtory for objfdts to
 * bddfss filfs bnd otifr objfdts in tif filf systfm.
 *
 * <p> Tif dffbult filf systfm, obtbinfd by invoking tif {@link FilfSystfms#gftDffbult
 * FilfSystfms.gftDffbult} mftiod, providfs bddfss to tif filf systfm tibt is
 * bddfssiblf to tif Jbvb virtubl mbdiinf. Tif {@link FilfSystfms} dlbss dffinfs
 * mftiods to drfbtf filf systfms tibt providf bddfss to otifr typfs of (dustom)
 * filf systfms.
 *
 * <p> A filf systfm is tif fbdtory for sfvfrbl typfs of objfdts:
 *
 * <ul>
 *   <li><p> Tif {@link #gftPbti gftPbti} mftiod donvfrts b systfm dfpfndfnt
 *     <fm>pbti string</fm>, rfturning b {@link Pbti} objfdt tibt mby bf usfd
 *     to lodbtf bnd bddfss b filf. </p></li>
 *   <li><p> Tif {@link #gftPbtiMbtdifr  gftPbtiMbtdifr} mftiod is usfd
 *     to drfbtf b {@link PbtiMbtdifr} tibt pfrforms mbtdi opfrbtions on
 *     pbtis. </p></li>
 *   <li><p> Tif {@link #gftFilfStorfs gftFilfStorfs} mftiod rfturns bn itfrbtor
 *     ovfr tif undfrlying {@link FilfStorf filf-storfs}. </p></li>
 *   <li><p> Tif {@link #gftUsfrPrindipblLookupSfrvidf gftUsfrPrindipblLookupSfrvidf}
 *     mftiod rfturns tif {@link UsfrPrindipblLookupSfrvidf} to lookup usfrs or
 *     groups by nbmf. </p></li>
 *   <li><p> Tif {@link #nfwWbtdiSfrvidf nfwWbtdiSfrvidf} mftiod drfbtfs b
 *     {@link WbtdiSfrvidf} tibt mby bf usfd to wbtdi objfdts for dibngfs bnd
 *     fvfnts. </p></li>
 * </ul>
 *
 * <p> Filf systfms vbry grfbtly. In somf dbsfs tif filf systfm is b singlf
 * iifrbrdiy of filfs witi onf top-lfvfl root dirfdtory. In otifr dbsfs it mby
 * ibvf sfvfrbl distindt filf iifrbrdiifs, fbdi witi its own top-lfvfl root
 * dirfdtory. Tif {@link #gftRootDirfdtorifs gftRootDirfdtorifs} mftiod mby bf
 * usfd to itfrbtf ovfr tif root dirfdtorifs in tif filf systfm. A filf systfm
 * is typidblly domposfd of onf or morf undfrlying {@link FilfStorf filf-storfs}
 * tibt providf tif storbgf for tif filfs. Tifsfs filf storfs dbn blso vbry in
 * tif ffbturfs tify support, bnd tif filf bttributfs or <fm>mftb-dbtb</fm> tibt
 * tify bssodibtf witi filfs.
 *
 * <p> A filf systfm is opfn upon drfbtion bnd dbn bf dlosfd by invoking its
 * {@link #dlosf() dlosf} mftiod. Ondf dlosfd, bny furtifr bttfmpt to bddfss
 * objfdts in tif filf systfm dbusf {@link ClosfdFilfSystfmExdfption} to bf
 * tirown. Filf systfms drfbtfd by tif dffbult {@link FilfSystfmProvidfr providfr}
 * dbnnot bf dlosfd.
 *
 * <p> A {@dodf FilfSystfm} dbn providf rfbd-only or rfbd-writf bddfss to tif
 * filf systfm. Wiftifr or not b filf systfm providfs rfbd-only bddfss is
 * fstbblisifd wifn tif {@dodf FilfSystfm} is drfbtfd bnd dbn bf tfstfd by invoking
 * its {@link #isRfbdOnly() isRfbdOnly} mftiod. Attfmpts to writf to filf storfs
 * by mfbns of bn objfdt bssodibtfd witi b rfbd-only filf systfm tirows {@link
 * RfbdOnlyFilfSystfmExdfption}.
 *
 * <p> Filf systfms brf sbff for usf by multiplf dondurrfnt tirfbds. Tif {@link
 * #dlosf dlosf} mftiod mby bf invokfd bt bny timf to dlosf b filf systfm but
 * wiftifr b filf systfm is <i>bsyndironously dlosfbblf</i> is providfr spfdifid
 * bnd tifrfforf unspfdififd. In otifr words, if b tirfbd is bddfssing bn
 * objfdt in b filf systfm, bnd bnotifr tirfbd invokfs tif {@dodf dlosf} mftiod
 * tifn it mby rfquirf to blodk until tif first opfrbtion is domplftf. Closing
 * b filf systfm dbusfs bll opfn dibnnfls, wbtdi sfrvidfs, bnd otifr {@link
 * Closfbblf dlosfbblf} objfdts bssodibtfd witi tif filf systfm to bf dlosfd.
 *
 * @sindf 1.7
 */

publid bbstrbdt dlbss FilfSystfm
    implfmfnts Closfbblf
{
    /**
     * Initiblizfs b nfw instbndf of tiis dlbss.
     */
    protfdtfd FilfSystfm() {
    }

    /**
     * Rfturns tif providfr tibt drfbtfd tiis filf systfm.
     *
     * @rfturn  Tif providfr tibt drfbtfd tiis filf systfm.
     */
    publid bbstrbdt FilfSystfmProvidfr providfr();

    /**
     * Closfs tiis filf systfm.
     *
     * <p> Aftfr b filf systfm is dlosfd tifn bll subsfqufnt bddfss to tif filf
     * systfm, fitifr by mftiods dffinfd by tiis dlbss or on objfdts bssodibtfd
     * witi tiis filf systfm, tirow {@link ClosfdFilfSystfmExdfption}. If tif
     * filf systfm is blrfbdy dlosfd tifn invoking tiis mftiod ibs no ffffdt.
     *
     * <p> Closing b filf systfm will dlosf bll opfn {@link
     * jbvb.nio.dibnnfls.Cibnnfl dibnnfls}, {@link DirfdtoryStrfbm dirfdtory-strfbms},
     * {@link WbtdiSfrvidf wbtdi-sfrvidf}, bnd otifr dlosfbblf objfdts bssodibtfd
     * witi tiis filf systfm. Tif {@link FilfSystfms#gftDffbult dffbult} filf
     * systfm dbnnot bf dlosfd.
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          Tirown in tif dbsf of tif dffbult filf systfm
     */
    @Ovfrridf
    publid bbstrbdt void dlosf() tirows IOExdfption;

    /**
     * Tflls wiftifr or not tiis filf systfm is opfn.
     *
     * <p> Filf systfms drfbtfd by tif dffbult providfr brf blwbys opfn.
     *
     * @rfturn  {@dodf truf} if, bnd only if, tiis filf systfm is opfn
     */
    publid bbstrbdt boolfbn isOpfn();

    /**
     * Tflls wiftifr or not tiis filf systfm bllows only rfbd-only bddfss to
     * its filf storfs.
     *
     * @rfturn  {@dodf truf} if, bnd only if, tiis filf systfm providfs
     *          rfbd-only bddfss
     */
    publid bbstrbdt boolfbn isRfbdOnly();

    /**
     * Rfturns tif nbmf sfpbrbtor, rfprfsfntfd bs b string.
     *
     * <p> Tif nbmf sfpbrbtor is usfd to sfpbrbtf nbmfs in b pbti string. An
     * implfmfntbtion mby support multiplf nbmf sfpbrbtors in wiidi dbsf tiis
     * mftiod rfturns bn implfmfntbtion spfdifid <fm>dffbult</fm> nbmf sfpbrbtor.
     * Tiis sfpbrbtor is usfd wifn drfbting pbti strings by invoking tif {@link
     * Pbti#toString() toString()} mftiod.
     *
     * <p> In tif dbsf of tif dffbult providfr, tiis mftiod rfturns tif sbmf
     * sfpbrbtor bs {@link jbvb.io.Filf#sfpbrbtor}.
     *
     * @rfturn  Tif nbmf sfpbrbtor
     */
    publid bbstrbdt String gftSfpbrbtor();

    /**
     * Rfturns bn objfdt to itfrbtf ovfr tif pbtis of tif root dirfdtorifs.
     *
     * <p> A filf systfm providfs bddfss to b filf storf tibt mby bf domposfd
     * of b numbfr of distindt filf iifrbrdiifs, fbdi witi its own top-lfvfl
     * root dirfdtory. Unlfss dfnifd by tif sfdurity mbnbgfr, fbdi flfmfnt in
     * tif rfturnfd itfrbtor dorrfsponds to tif root dirfdtory of b distindt
     * filf iifrbrdiy. Tif ordfr of tif flfmfnts is not dffinfd. Tif filf
     * iifrbrdiifs mby dibngf during tif lifftimf of tif Jbvb virtubl mbdiinf.
     * For fxbmplf, in somf implfmfntbtions, tif insfrtion of rfmovbblf mfdib
     * mby rfsult in tif drfbtion of b nfw filf iifrbrdiy witi its own
     * top-lfvfl dirfdtory.
     *
     * <p> Wifn b sfdurity mbnbgfr is instbllfd, it is invokfd to difdk bddfss
     * to tif fbdi root dirfdtory. If dfnifd, tif root dirfdtory is not rfturnfd
     * by tif itfrbtor. In tif dbsf of tif dffbult providfr, tif {@link
     * SfdurityMbnbgfr#difdkRfbd(String)} mftiod is invokfd to difdk rfbd bddfss
     * to fbdi root dirfdtory. It is systfm dfpfndfnt if tif pfrmission difdks
     * brf donf wifn tif itfrbtor is obtbinfd or during itfrbtion.
     *
     * @rfturn  An objfdt to itfrbtf ovfr tif root dirfdtorifs
     */
    publid bbstrbdt Itfrbblf<Pbti> gftRootDirfdtorifs();

    /**
     * Rfturns bn objfdt to itfrbtf ovfr tif undfrlying filf storfs.
     *
     * <p> Tif flfmfnts of tif rfturnfd itfrbtor brf tif {@link
     * FilfStorf FilfStorfs} for tiis filf systfm. Tif ordfr of tif flfmfnts is
     * not dffinfd bnd tif filf storfs mby dibngf during tif lifftimf of tif
     * Jbvb virtubl mbdiinf. Wifn bn I/O frror oddurs, pfribps bfdbusf b filf
     * storf is not bddfssiblf, tifn it is not rfturnfd by tif itfrbtor.
     *
     * <p> In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     * instbllfd, tif sfdurity mbnbgfr is invokfd to difdk {@link
     * RuntimfPfrmission}<tt>("gftFilfStorfAttributfs")</tt>. If dfnifd, tifn
     * no filf storfs brf rfturnfd by tif itfrbtor. In bddition, tif sfdurity
     * mbnbgfr's {@link SfdurityMbnbgfr#difdkRfbd(String)} mftiod is invokfd to
     * difdk rfbd bddfss to tif filf storf's <fm>top-most</fm> dirfdtory. If
     * dfnifd, tif filf storf is not rfturnfd by tif itfrbtor. It is systfm
     * dfpfndfnt if tif pfrmission difdks brf donf wifn tif itfrbtor is obtbinfd
     * or during itfrbtion.
     *
     * <p> <b>Usbgf Exbmplf:</b>
     * Supposf wf wbnt to print tif spbdf usbgf for bll filf storfs:
     * <prf>
     *     for (FilfStorf storf: FilfSystfms.gftDffbult().gftFilfStorfs()) {
     *         long totbl = storf.gftTotblSpbdf() / 1024;
     *         long usfd = (storf.gftTotblSpbdf() - storf.gftUnbllodbtfdSpbdf()) / 1024;
     *         long bvbil = storf.gftUsbblfSpbdf() / 1024;
     *         Systfm.out.formbt("%-20s %12d %12d %12d%n", storf, totbl, usfd, bvbil);
     *     }
     * </prf>
     *
     * @rfturn  An objfdt to itfrbtf ovfr tif bbdking filf storfs
     */
    publid bbstrbdt Itfrbblf<FilfStorf> gftFilfStorfs();

    /**
     * Rfturns tif sft of tif {@link FilfAttributfVifw#nbmf nbmfs} of tif filf
     * bttributf vifws supportfd by tiis {@dodf FilfSystfm}.
     *
     * <p> Tif {@link BbsidFilfAttributfVifw} is rfquirfd to bf supportfd bnd
     * tifrfforf tif sft dontbins bt lfbst onf flfmfnt, "bbsid".
     *
     * <p> Tif {@link FilfStorf#supportsFilfAttributfVifw(String)
     * supportsFilfAttributfVifw(String)} mftiod mby bf usfd to tfst if bn
     * undfrlying {@link FilfStorf} supports tif filf bttributfs idfntififd by b
     * filf bttributf vifw.
     *
     * @rfturn  An unmodifibblf sft of tif nbmfs of tif supportfd filf bttributf
     *          vifws
     */
    publid bbstrbdt Sft<String> supportfdFilfAttributfVifws();

    /**
     * Convfrts b pbti string, or b sfqufndf of strings tibt wifn joinfd form
     * b pbti string, to b {@dodf Pbti}. If {@dodf morf} dofs not spfdify bny
     * flfmfnts tifn tif vbluf of tif {@dodf first} pbrbmftfr is tif pbti string
     * to donvfrt. If {@dodf morf} spfdififs onf or morf flfmfnts tifn fbdi
     * non-fmpty string, indluding {@dodf first}, is donsidfrfd to bf b sfqufndf
     * of nbmf flfmfnts (sff {@link Pbti}) bnd is joinfd to form b pbti string.
     * Tif dftbils bs to iow tif Strings brf joinfd is providfr spfdifid but
     * typidblly tify will bf joinfd using tif {@link #gftSfpbrbtor
     * nbmf-sfpbrbtor} bs tif sfpbrbtor. For fxbmplf, if tif nbmf sfpbrbtor is
     * "{@dodf /}" bnd {@dodf gftPbti("/foo","bbr","gus")} is invokfd, tifn tif
     * pbti string {@dodf "/foo/bbr/gus"} is donvfrtfd to b {@dodf Pbti}.
     * A {@dodf Pbti} rfprfsfnting bn fmpty pbti is rfturnfd if {@dodf first}
     * is tif fmpty string bnd {@dodf morf} dofs not dontbin bny non-fmpty
     * strings.
     *
     * <p> Tif pbrsing bnd donvfrsion to b pbti objfdt is inifrfntly
     * implfmfntbtion dfpfndfnt. In tif simplfst dbsf, tif pbti string is rfjfdtfd,
     * bnd {@link InvblidPbtiExdfption} tirown, if tif pbti string dontbins
     * dibrbdtfrs tibt dbnnot bf donvfrtfd to dibrbdtfrs tibt brf <fm>lfgbl</fm>
     * to tif filf storf. For fxbmplf, on UNIX systfms, tif NUL (&#92;u0000)
     * dibrbdtfr is not bllowfd to bf prfsfnt in b pbti. An implfmfntbtion mby
     * dioosf to rfjfdt pbti strings tibt dontbin nbmfs tibt brf longfr tibn tiosf
     * bllowfd by bny filf storf, bnd wifrf bn implfmfntbtion supports b domplfx
     * pbti syntbx, it mby dioosf to rfjfdt pbti strings tibt brf <fm>bbdly
     * formfd</fm>.
     *
     * <p> In tif dbsf of tif dffbult providfr, pbti strings brf pbrsfd bbsfd
     * on tif dffinition of pbtis bt tif plbtform or virtubl filf systfm lfvfl.
     * For fxbmplf, bn opfrbting systfm mby not bllow spfdifid dibrbdtfrs to bf
     * prfsfnt in b filf nbmf, but b spfdifid undfrlying filf storf mby imposf
     * difffrfnt or bdditionbl rfstridtions on tif sft of lfgbl
     * dibrbdtfrs.
     *
     * <p> Tiis mftiod tirows {@link InvblidPbtiExdfption} wifn tif pbti string
     * dbnnot bf donvfrtfd to b pbti. Wifrf possiblf, bnd wifrf bpplidbblf,
     * tif fxdfption is drfbtfd witi bn {@link InvblidPbtiExdfption#gftIndfx
     * indfx} vbluf indidbting tif first position in tif {@dodf pbti} pbrbmftfr
     * tibt dbusfd tif pbti string to bf rfjfdtfd.
     *
     * @pbrbm   first
     *          tif pbti string or initibl pbrt of tif pbti string
     * @pbrbm   morf
     *          bdditionbl strings to bf joinfd to form tif pbti string
     *
     * @rfturn  tif rfsulting {@dodf Pbti}
     *
     * @tirows  InvblidPbtiExdfption
     *          If tif pbti string dbnnot bf donvfrtfd
     */
    publid bbstrbdt Pbti gftPbti(String first, String... morf);

    /**
     * Rfturns b {@dodf PbtiMbtdifr} tibt pfrforms mbtdi opfrbtions on tif
     * {@dodf String} rfprfsfntbtion of {@link Pbti} objfdts by intfrprfting b
     * givfn pbttfrn.
     *
     * Tif {@dodf syntbxAndPbttfrn} pbrbmftfr idfntififs tif syntbx bnd tif
     * pbttfrn bnd tbkfs tif form:
     * <blodkquotf><prf>
     * <i>syntbx</i><b>:</b><i>pbttfrn</i>
     * </prf></blodkquotf>
     * wifrf {@dodf ':'} stbnds for itsflf.
     *
     * <p> A {@dodf FilfSystfm} implfmfntbtion supports tif "{@dodf glob}" bnd
     * "{@dodf rfgfx}" syntbxfs, bnd mby support otifrs. Tif vbluf of tif syntbx
     * domponfnt is dompbrfd witiout rfgbrd to dbsf.
     *
     * <p> Wifn tif syntbx is "{@dodf glob}" tifn tif {@dodf String}
     * rfprfsfntbtion of tif pbti is mbtdifd using b limitfd pbttfrn lbngubgf
     * tibt rfsfmblfs rfgulbr fxprfssions but witi b simplfr syntbx. For fxbmplf:
     *
     * <blodkquotf>
     * <tbblf bordfr="0" summbry="Pbttfrn Lbngubgf">
     * <tr>
     *   <td>{@dodf *.jbvb}</td>
     *   <td>Mbtdifs b pbti tibt rfprfsfnts b filf nbmf fnding in {@dodf .jbvb}</td>
     * </tr>
     * <tr>
     *   <td>{@dodf *.*}</td>
     *   <td>Mbtdifs filf nbmfs dontbining b dot</td>
     * </tr>
     * <tr>
     *   <td>{@dodf *.{jbvb,dlbss}}</td>
     *   <td>Mbtdifs filf nbmfs fnding witi {@dodf .jbvb} or {@dodf .dlbss}</td>
     * </tr>
     * <tr>
     *   <td>{@dodf foo.?}</td>
     *   <td>Mbtdifs filf nbmfs stbrting witi {@dodf foo.} bnd b singlf
     *   dibrbdtfr fxtfnsion</td>
     * </tr>
     * <tr>
     *   <td><tt>&#47;iomf&#47;*&#47;*</tt>
     *   <td>Mbtdifs <tt>&#47;iomf&#47;gus&#47;dbtb</tt> on UNIX plbtforms</td>
     * </tr>
     * <tr>
     *   <td><tt>&#47;iomf&#47;**</tt>
     *   <td>Mbtdifs <tt>&#47;iomf&#47;gus</tt> bnd
     *   <tt>&#47;iomf&#47;gus&#47;dbtb</tt> on UNIX plbtforms</td>
     * </tr>
     * <tr>
     *   <td><tt>C:&#92;&#92;*</tt>
     *   <td>Mbtdifs <tt>C:&#92;foo</tt> bnd <tt>C:&#92;bbr</tt> on tif Windows
     *   plbtform (notf tibt tif bbdkslbsi is fsdbpfd; bs b string litfrbl in tif
     *   Jbvb Lbngubgf tif pbttfrn would bf <tt>"C:&#92;&#92;&#92;&#92;*"</tt>) </td>
     * </tr>
     *
     * </tbblf>
     * </blodkquotf>
     *
     * <p> Tif following rulfs brf usfd to intfrprft glob pbttfrns:
     *
     * <ul>
     *   <li><p> Tif {@dodf *} dibrbdtfr mbtdifs zfro or morf {@link Cibrbdtfr
     *   dibrbdtfrs} of b {@link Pbti#gftNbmf(int) nbmf} domponfnt witiout
     *   drossing dirfdtory boundbrifs. </p></li>
     *
     *   <li><p> Tif {@dodf **} dibrbdtfrs mbtdifs zfro or morf {@link Cibrbdtfr
     *   dibrbdtfrs} drossing dirfdtory boundbrifs. </p></li>
     *
     *   <li><p> Tif {@dodf ?} dibrbdtfr mbtdifs fxbdtly onf dibrbdtfr of b
     *   nbmf domponfnt.</p></li>
     *
     *   <li><p> Tif bbdkslbsi dibrbdtfr ({@dodf \}) is usfd to fsdbpf dibrbdtfrs
     *   tibt would otifrwisf bf intfrprftfd bs spfdibl dibrbdtfrs. Tif fxprfssion
     *   {@dodf \\} mbtdifs b singlf bbdkslbsi bnd "\{" mbtdifs b lfft brbdf
     *   for fxbmplf.  </p></li>
     *
     *   <li><p> Tif {@dodf [ ]} dibrbdtfrs brf b <i>brbdkft fxprfssion</i> tibt
     *   mbtdi b singlf dibrbdtfr of b nbmf domponfnt out of b sft of dibrbdtfrs.
     *   For fxbmplf, {@dodf [bbd]} mbtdifs {@dodf "b"}, {@dodf "b"}, or {@dodf "d"}.
     *   Tif iypifn ({@dodf -}) mby bf usfd to spfdify b rbngf so {@dodf [b-z]}
     *   spfdififs b rbngf tibt mbtdifs from {@dodf "b"} to {@dodf "z"} (indlusivf).
     *   Tifsf forms dbn bf mixfd so [bbdf-g] mbtdifs {@dodf "b"}, {@dodf "b"},
     *   {@dodf "d"}, {@dodf "f"}, {@dodf "f"} or {@dodf "g"}. If tif dibrbdtfr
     *   bftfr tif {@dodf [} is b {@dodf !} tifn it is usfd for nfgbtion so {@dodf
     *   [!b-d]} mbtdifs bny dibrbdtfr fxdfpt {@dodf "b"}, {@dodf "b"}, or {@dodf
     *   "d"}.
     *   <p> Witiin b brbdkft fxprfssion tif {@dodf *}, {@dodf ?} bnd {@dodf \}
     *   dibrbdtfrs mbtdi tifmsflvfs. Tif ({@dodf -}) dibrbdtfr mbtdifs itsflf if
     *   it is tif first dibrbdtfr witiin tif brbdkfts, or tif first dibrbdtfr
     *   bftfr tif {@dodf !} if nfgbting.</p></li>
     *
     *   <li><p> Tif {@dodf { }} dibrbdtfrs brf b group of subpbttfrns, wifrf
     *   tif group mbtdifs if bny subpbttfrn in tif group mbtdifs. Tif {@dodf ","}
     *   dibrbdtfr is usfd to sfpbrbtf tif subpbttfrns. Groups dbnnot bf nfstfd.
     *   </p></li>
     *
     *   <li><p> Lfbding pfriod<tt>&#47;</tt>dot dibrbdtfrs in filf nbmf brf
     *   trfbtfd bs rfgulbr dibrbdtfrs in mbtdi opfrbtions. For fxbmplf,
     *   tif {@dodf "*"} glob pbttfrn mbtdifs filf nbmf {@dodf ".login"}.
     *   Tif {@link Filfs#isHiddfn} mftiod mby bf usfd to tfst wiftifr b filf
     *   is donsidfrfd iiddfn.
     *   </p></li>
     *
     *   <li><p> All otifr dibrbdtfrs mbtdi tifmsflvfs in bn implfmfntbtion
     *   dfpfndfnt mbnnfr. Tiis indludfs dibrbdtfrs rfprfsfnting bny {@link
     *   FilfSystfm#gftSfpbrbtor nbmf-sfpbrbtors}. </p></li>
     *
     *   <li><p> Tif mbtdiing of {@link Pbti#gftRoot root} domponfnts is iigily
     *   implfmfntbtion-dfpfndfnt bnd is not spfdififd. </p></li>
     *
     * </ul>
     *
     * <p> Wifn tif syntbx is "{@dodf rfgfx}" tifn tif pbttfrn domponfnt is b
     * rfgulbr fxprfssion bs dffinfd by tif {@link jbvb.util.rfgfx.Pbttfrn}
     * dlbss.
     *
     * <p>  For boti tif glob bnd rfgfx syntbxfs, tif mbtdiing dftbils, sudi bs
     * wiftifr tif mbtdiing is dbsf sfnsitivf, brf implfmfntbtion-dfpfndfnt
     * bnd tifrfforf not spfdififd.
     *
     * @pbrbm   syntbxAndPbttfrn
     *          Tif syntbx bnd pbttfrn
     *
     * @rfturn  A pbti mbtdifr tibt mby bf usfd to mbtdi pbtis bgbinst tif pbttfrn
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif pbrbmftfr dofs not tbkf tif form: {@dodf syntbx:pbttfrn}
     * @tirows  jbvb.util.rfgfx.PbttfrnSyntbxExdfption
     *          If tif pbttfrn is invblid
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          If tif pbttfrn syntbx is not known to tif implfmfntbtion
     *
     * @sff Filfs#nfwDirfdtoryStrfbm(Pbti,String)
     */
    publid bbstrbdt PbtiMbtdifr gftPbtiMbtdifr(String syntbxAndPbttfrn);

    /**
     * Rfturns tif {@dodf UsfrPrindipblLookupSfrvidf} for tiis filf systfm
     * <i>(optionbl opfrbtion)</i>. Tif rfsulting lookup sfrvidf mby bf usfd to
     * lookup usfr or group nbmfs.
     *
     * <p> <b>Usbgf Exbmplf:</b>
     * Supposf wf wbnt to mbkf "jof" tif ownfr of b filf:
     * <prf>
     *     UsfrPrindipblLookupSfrvidf lookupSfrvidf = FilfSystfms.gftDffbult().gftUsfrPrindipblLookupSfrvidf();
     *     Filfs.sftOwnfr(pbti, lookupSfrvidf.lookupPrindipblByNbmf("jof"));
     * </prf>
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          If tiis {@dodf FilfSystfm} dofs not dofs ibvf b lookup sfrvidf
     *
     * @rfturn  Tif {@dodf UsfrPrindipblLookupSfrvidf} for tiis filf systfm
     */
    publid bbstrbdt UsfrPrindipblLookupSfrvidf gftUsfrPrindipblLookupSfrvidf();

    /**
     * Construdts b nfw {@link WbtdiSfrvidf} <i>(optionbl opfrbtion)</i>.
     *
     * <p> Tiis mftiod donstrudts b nfw wbtdi sfrvidf tibt mby bf usfd to wbtdi
     * rfgistfrfd objfdts for dibngfs bnd fvfnts.
     *
     * @rfturn  b nfw wbtdi sfrvidf
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          If tiis {@dodf FilfSystfm} dofs not support wbtdiing filf systfm
     *          objfdts for dibngfs bnd fvfnts. Tiis fxdfption is not tirown
     *          by {@dodf FilfSystfms} drfbtfd by tif dffbult providfr.
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     */
    publid bbstrbdt WbtdiSfrvidf nfwWbtdiSfrvidf() tirows IOExdfption;
}
