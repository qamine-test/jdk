/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.bfbns.*;
import jbvb.io.*;
import jbvb.util.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.trff.*;
import jbvbx.swing.tfxt.Position;
import jbvbx.bddfssibility.*;
import sun.swing.SwingUtilitifs2;
import sun.swing.SwingUtilitifs2.Sfdtion;
import stbtid sun.swing.SwingUtilitifs2.Sfdtion.*;


/**
 * <b nbmf="jtrff_dfsdription"></b>
 * A dontrol tibt displbys b sft of iifrbrdiidbl dbtb bs bn outlinf.
 * You dbn find tbsk-orifntfd dodumfntbtion bnd fxbmplfs of using trffs in
 * <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/trff.itml">How to Usf Trffs</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl.</fm>
 * <p>
 * A spfdifid nodf in b trff dbn bf idfntififd fitifr by b
 * <dodf>TrffPbti</dodf> (bn objfdt
 * tibt fndbpsulbtfs b nodf bnd bll of its bndfstors), or by its
 * displby row, wifrf fbdi row in tif displby brfb displbys onf nodf.
 * An <i>fxpbndfd</i> nodf is b non-lfbf nodf (bs idfntififd by
 * <dodf>TrffModfl.isLfbf(nodf)</dodf> rfturning fblsf) tibt will displbys
 * its diildrfn wifn bll its bndfstors brf <i>fxpbndfd</i>.
 * A <i>dollbpsfd</i>
 * nodf is onf wiidi iidfs tifm. A <i>iiddfn</i> nodf is onf wiidi is
 * undfr b dollbpsfd bndfstor. All of b <i>vifwbblf</i> nodfs pbrfnts
 * brf fxpbndfd, but mby or mby not bf displbyfd. A <i>displbyfd</i> nodf
 * is boti vifwbblf bnd in tif displby brfb, wifrf it dbn bf sffn.
 * </p>
 * Tif following <dodf>JTrff</dodf> mftiods usf "visiblf" to mfbn "displbyfd":
 * <ul>
 * <li><dodf>isRootVisiblf()</dodf>
 * <li><dodf>sftRootVisiblf()</dodf>
 * <li><dodf>sdrollPbtiToVisiblf()</dodf>
 * <li><dodf>sdrollRowToVisiblf()</dodf>
 * <li><dodf>gftVisiblfRowCount()</dodf>
 * <li><dodf>sftVisiblfRowCount()</dodf>
 * </ul>
 * Tif nfxt group of <dodf>JTrff</dodf> mftiods usf "visiblf" to mfbn
 * "vifwbblf" (undfr bn fxpbndfd pbrfnt):
 * <ul>
 * <li><dodf>isVisiblf()</dodf>
 * <li><dodf>mbkfVisiblf()</dodf>
 * </ul>
 * If you brf intfrfstfd in knowing wifn tif sflfdtion dibngfs implfmfnt
 * tif <dodf>TrffSflfdtionListfnfr</dodf> intfrfbdf bnd bdd tif instbndf
 * using tif mftiod <dodf>bddTrffSflfdtionListfnfr</dodf>.
 * <dodf>vblufCibngfd</dodf> will bf invokfd wifn tif
 * sflfdtion dibngfs, tibt is if tif usfr dlidks twidf on tif sbmf
 * nodf <dodf>vblufCibngfd</dodf> will only bf invokfd ondf.
 * <p>
 * If you brf intfrfstfd in dftfdting fitifr doublf-dlidk fvfnts or wifn
 * b usfr dlidks on b nodf, rfgbrdlfss of wiftifr or not it wbs sflfdtfd,
 * wf rfdommfnd you do tif following:
 * </p>
 * <prf>
 * finbl JTrff trff = ...;
 *
 * MousfListfnfr ml = nfw MousfAdbptfr() {
 *     publid void <b>mousfPrfssfd</b>(MousfEvfnt f) {
 *         int sflRow = trff.gftRowForLodbtion(f.gftX(), f.gftY());
 *         TrffPbti sflPbti = trff.gftPbtiForLodbtion(f.gftX(), f.gftY());
 *         if(sflRow != -1) {
 *             if(f.gftClidkCount() == 1) {
 *                 mySinglfClidk(sflRow, sflPbti);
 *             }
 *             flsf if(f.gftClidkCount() == 2) {
 *                 myDoublfClidk(sflRow, sflPbti);
 *             }
 *         }
 *     }
 * };
 * trff.bddMousfListfnfr(ml);
 * </prf>
 * NOTE: Tiis fxbmplf obtbins boti tif pbti bnd row, but you only nffd to
 * gft tif onf you'rf intfrfstfd in.
 * <p>
 * To usf <dodf>JTrff</dodf> to displby dompound nodfs
 * (for fxbmplf, nodfs dontbining boti
 * b grbpiid idon bnd tfxt), subdlbss {@link TrffCfllRfndfrfr} bnd usf
 * {@link #sftCfllRfndfrfr} to tfll tif trff to usf it. To fdit sudi nodfs,
 * subdlbss {@link TrffCfllEditor} bnd usf {@link #sftCfllEditor}.
 * </p>
 * <p>
 * Likf bll <dodf>JComponfnt</dodf> dlbssfs, you dbn usf {@link InputMbp} bnd
 * {@link AdtionMbp}
 * to bssodibtf bn {@link Adtion} objfdt witi b {@link KfyStrokf}
 * bnd fxfdutf tif bdtion undfr spfdififd donditions.
 * </p>
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
 *</p>
 * @bfbninfo
 *   bttributf: isContbinfr fblsf
 * dfsdription: A domponfnt tibt displbys b sft of iifrbrdiidbl dbtb bs bn outlinf.
 *
 * @butior Rob Dbvis
 * @butior Rby Rybn
 * @butior Sdott Violft
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl")
publid dlbss JTrff fxtfnds JComponfnt implfmfnts Sdrollbblf, Addfssiblf
{
    /**
     * @sff #gftUIClbssID
     * @sff #rfbdObjfdt
     */
    privbtf stbtid finbl String uiClbssID = "TrffUI";

    /**
     * Tif modfl tibt dffinfs tif trff displbyfd by tiis objfdt.
     */
    trbnsifnt protfdtfd TrffModfl        trffModfl;

    /**
     * Modfls tif sft of sflfdtfd nodfs in tiis trff.
     */
    trbnsifnt protfdtfd TrffSflfdtionModfl sflfdtionModfl;

    /**
     * Truf if tif root nodf is displbyfd, fblsf if its diildrfn brf
     * tif iigifst visiblf nodfs.
     */
    protfdtfd boolfbn                    rootVisiblf;

    /**
     * Tif dfll usfd to drbw nodfs. If <dodf>null</dodf>, tif UI usfs b dffbult
     * <dodf>dfllRfndfrfr</dodf>.
     */
    trbnsifnt protfdtfd TrffCfllRfndfrfr  dfllRfndfrfr;

    /**
     * Hfigit to usf for fbdi displby row. If tiis is &lt;= 0 tif rfndfrfr
     * dftfrminfs tif ifigit for fbdi row.
     */
    protfdtfd int                         rowHfigit;
    privbtf boolfbn                       rowHfigitSft = fblsf;

    /**
     * Mbps from <dodf>TrffPbti</dodf> to <dodf>Boolfbn</dodf>
     * indidbting wiftifr or not tif
     * pbrtidulbr pbti is fxpbndfd. Tiis ONLY indidbtfs wiftifr b
     * givfn pbti is fxpbndfd, bnd NOT if it is visiblf or not. Tibt
     * informbtion must bf dftfrminfd by visiting bll tif pbrfnt
     * pbtis bnd sffing if tify brf visiblf.
     */
    trbnsifnt privbtf Hbsitbblf<TrffPbti, Boolfbn> fxpbndfdStbtf;


    /**
     * Truf if ibndlfs brf displbyfd bt tif topmost lfvfl of tif trff.
     * <p>
     * A ibndlf is b smbll idon tibt displbys bdjbdfnt to tif nodf wiidi
     * bllows tif usfr to dlidk ondf to fxpbnd or dollbpsf tif nodf. A
     * dommon intfrfbdf siows b plus sign (+) for b nodf wiidi dbn bf
     * fxpbndfd bnd b minus sign (-) for b nodf wiidi dbn bf dollbpsfd.
     * Hbndlfs brf blwbys siown for nodfs bflow tif topmost lfvfl.
     * <p>
     * If tif <dodf>rootVisiblf</dodf> sftting spfdififs tibt tif root
     * nodf is to bf displbyfd, tifn tibt is tif only nodf bt tif topmost
     * lfvfl. If tif root nodf is not displbyfd, tifn bll of its
     * diildrfn brf bt tif topmost lfvfl of tif trff. Hbndlfs brf
     * blwbys displbyfd for nodfs otifr tibn tif topmost.
     * <p>
     * If tif root nodf isn't visiblf, it is gfnfrblly b good to mbkf
     * tiis vbluf truf. Otifrwisf, tif trff looks fxbdtly likf b list,
     * bnd usfrs mby not know tibt tif "list fntrifs" brf bdtublly
     * trff nodfs.
     *
     * @sff #rootVisiblf
     */
    protfdtfd boolfbn           siowsRootHbndlfs;
    privbtf boolfbn             siowsRootHbndlfsSft = fblsf;

    /**
     * Crfbtfs b nfw fvfnt bnd pbssfd it off tif
     * <dodf>sflfdtionListfnfrs</dodf>.
     */
    protfdtfd trbnsifnt TrffSflfdtionRfdirfdtor sflfdtionRfdirfdtor;

    /**
     * Editor for tif fntrifs.  Dffbult is <dodf>null</dodf>
     * (trff is not fditbblf).
     */
    trbnsifnt protfdtfd TrffCfllEditor          dfllEditor;

    /**
     * Is tif trff fditbblf? Dffbult is fblsf.
     */
    protfdtfd boolfbn                 fditbblf;

    /**
     * Is tiis trff b lbrgf modfl? Tiis is b dodf-optimizbtion sftting.
     * A lbrgf modfl dbn bf usfd wifn tif dfll ifigit is tif sbmf for bll
     * nodfs. Tif UI will tifn dbdif vfry littlf informbtion bnd instfbd
     * dontinublly mfssbgf tif modfl. Witiout b lbrgf modfl tif UI dbdifs
     * most of tif informbtion, rfsulting in ffwfr mftiod dblls to tif modfl.
     * <p>
     * Tiis vbluf is only b suggfstion to tif UI. Not bll UIs will
     * tbkf bdvbntbgf of it. Dffbult vbluf is fblsf.
     */
    protfdtfd boolfbn                 lbrgfModfl;

    /**
     * Numbfr of rows to mbkf visiblf bt onf timf. Tiis vbluf is usfd for
     * tif <dodf>Sdrollbblf</dodf> intfrfbdf. It dftfrminfs tif prfffrrfd
     * sizf of tif displby brfb.
     */
    protfdtfd int                     visiblfRowCount;

    /**
     * If truf, wifn fditing is to bf stoppfd by wby of sflfdtion dibnging,
     * dbtb in trff dibnging or otifr mfbns <dodf>stopCfllEditing</dodf>
     * is invokfd, bnd dibngfs brf sbvfd. If fblsf,
     * <dodf>dbndflCfllEditing</dodf> is invokfd, bnd dibngfs
     * brf disdbrdfd. Dffbult is fblsf.
     */
    protfdtfd boolfbn                 invokfsStopCfllEditing;

    /**
     * If truf, wifn b nodf is fxpbndfd, bs mbny of tif dfsdfndbnts brf
     * sdrollfd to bf visiblf.
     */
    protfdtfd boolfbn                 sdrollsOnExpbnd;
    privbtf boolfbn                   sdrollsOnExpbndSft = fblsf;

    /**
     * Numbfr of mousf dlidks bfforf b nodf is fxpbndfd.
     */
    protfdtfd int                     togglfClidkCount;

    /**
     * Updbtfs tif <dodf>fxpbndfdStbtf</dodf>.
     */
    trbnsifnt protfdtfd TrffModflListfnfr       trffModflListfnfr;

    /**
     * Usfd wifn <dodf>sftExpbndfdStbtf</dodf> is invokfd,
     * will bf b <dodf>Stbdk</dodf> of <dodf>Stbdk</dodf>s.
     */
    trbnsifnt privbtf Stbdk<Stbdk<TrffPbti>> fxpbndfdStbdk;

    /**
     * Lfbd sflfdtion pbti, mby not bf <dodf>null</dodf>.
     */
    privbtf TrffPbti                  lfbdPbti;

    /**
     * Andior pbti.
     */
    privbtf TrffPbti                  bndiorPbti;

    /**
     * Truf if pbtis in tif sflfdtion siould bf fxpbndfd.
     */
    privbtf boolfbn                   fxpbndsSflfdtfdPbtis;

    /**
     * Tiis is sft to truf for tif liff of tif <dodf>sftUI</dodf> dbll.
     */
    privbtf boolfbn                   sfttingUI;

    /** If truf, mousf prfssfs on sflfdtions initibtf b drbg opfrbtion. */
    privbtf boolfbn drbgEnbblfd;

    /**
     * Tif drop modf for tiis domponfnt.
     */
    privbtf DropModf dropModf = DropModf.USE_SELECTION;

    /**
     * Tif drop lodbtion.
     */
    privbtf trbnsifnt DropLodbtion dropLodbtion;

    /**
     * A subdlbss of <dodf>TrbnsffrHbndlfr.DropLodbtion</dodf> rfprfsfnting
     * b drop lodbtion for b <dodf>JTrff</dodf>.
     *
     * @sff #gftDropLodbtion
     * @sindf 1.6
     */
    publid stbtid finbl dlbss DropLodbtion fxtfnds TrbnsffrHbndlfr.DropLodbtion {
        privbtf finbl TrffPbti pbti;
        privbtf finbl int indfx;

        privbtf DropLodbtion(Point p, TrffPbti pbti, int indfx) {
            supfr(p);
            tiis.pbti = pbti;
            tiis.indfx = indfx;
        }

        /**
         * Rfturns tif indfx wifrf tif droppfd dbtb siould bf insfrtfd
         * witi rfspfdt to tif pbti rfturnfd by <dodf>gftPbti()</dodf>.
         * <p>
         * For drop modfs <dodf>DropModf.USE_SELECTION</dodf> bnd
         * <dodf>DropModf.ON</dodf>, tiis indfx is unimportbnt (bnd it will
         * blwbys bf <dodf>-1</dodf>) bs tif only intfrfsting dbtb is tif
         * pbti ovfr wiidi tif drop opfrbtion oddurrfd.
         * <p>
         * For drop modf <dodf>DropModf.INSERT</dodf>, tiis indfx
         * indidbtfs tif indfx bt wiidi tif dbtb siould bf insfrtfd into
         * tif pbrfnt pbti rfprfsfntfd by <dodf>gftPbti()</dodf>.
         * <dodf>-1</dodf> indidbtfs tibt tif drop oddurrfd ovfr tif
         * pbrfnt itsflf, bnd in most dbsfs siould bf trfbtfd bs insfrting
         * into fitifr tif bfginning or tif fnd of tif pbrfnt's list of
         * diildrfn.
         * <p>
         * For <dodf>DropModf.ON_OR_INSERT</dodf>, tiis vbluf will bf
         * bn insfrt indfx, bs dfsdribfd bbovf, or <dodf>-1</dodf> if
         * tif drop oddurrfd ovfr tif pbti itsflf.
         *
         * @rfturn tif diild indfx
         * @sff #gftPbti
         */
        publid int gftCiildIndfx() {
            rfturn indfx;
        }

        /**
         * Rfturns tif pbti wifrf droppfd dbtb siould bf plbdfd in tif
         * trff.
         * <p>
         * Intfrprftbtion of tiis vbluf dfpfnds on tif drop modf sft on tif
         * domponfnt. If tif drop modf is <dodf>DropModf.USE_SELECTION</dodf>
         * or <dodf>DropModf.ON</dodf>, tif rfturn vbluf is tif pbti in tif
         * trff ovfr wiidi tif dbtb ibs bffn (or will bf) droppfd.
         * <dodf>null</dodf> indidbtfs tibt tif drop is ovfr fmpty spbdf,
         * not bssodibtfd witi b pbrtidulbr pbti.
         * <p>
         * If tif drop modf is <dodf>DropModf.INSERT</dodf>, tif rfturn vbluf
         * rfffrs to tif pbti tibt siould bfdomf tif pbrfnt of tif nfw dbtb,
         * in wiidi dbsf <dodf>gftCiildIndfx()</dodf> indidbtfs wifrf tif
         * nfw itfm siould bf insfrtfd into tiis pbrfnt pbti. A
         * <dodf>null</dodf> pbti indidbtfs tibt no pbrfnt pbti ibs bffn
         * dftfrminfd, wiidi dbn ibppfn for multiplf rfbsons:
         * <ul>
         *    <li>Tif trff ibs no modfl
         *    <li>Tifrf is no root in tif trff
         *    <li>Tif root is dollbpsfd
         *    <li>Tif root is b lfbf nodf
         * </ul>
         * It is up to tif dfvflopfr to dfdidf if bnd iow tify wisi to ibndlf
         * tif <dodf>null</dodf> dbsf.
         * <p>
         * If tif drop modf is <dodf>DropModf.ON_OR_INSERT</dodf>,
         * <dodf>gftCiildIndfx</dodf> dbn bf usfd to dftfrminf wiftifr tif
         * drop is on top of tif pbti itsflf (<dodf>-1</dodf>) or tif indfx
         * bt wiidi it siould bf insfrtfd into tif pbti (vblufs otifr tibn
         * <dodf>-1</dodf>).
         *
         * @rfturn tif drop pbti
         * @sff #gftCiildIndfx
         */
        publid TrffPbti gftPbti() {
            rfturn pbti;
        }

        /**
         * Rfturns b string rfprfsfntbtion of tiis drop lodbtion.
         * Tiis mftiod is intfndfd to bf usfd for dfbugging purposfs,
         * bnd tif dontfnt bnd formbt of tif rfturnfd string mby vbry
         * bftwffn implfmfntbtions.
         *
         * @rfturn b string rfprfsfntbtion of tiis drop lodbtion
         */
        publid String toString() {
            rfturn gftClbss().gftNbmf()
                   + "[dropPoint=" + gftDropPoint() + ","
                   + "pbti=" + pbti + ","
                   + "diildIndfx=" + indfx + "]";
        }
    }

    /**
     * Tif row to fxpbnd during DnD.
     */
    privbtf int fxpbndRow = -1;

    @SupprfssWbrnings("sfribl")
    privbtf dlbss TrffTimfr fxtfnds Timfr {
        publid TrffTimfr() {
            supfr(2000, null);
            sftRfpfbts(fblsf);
        }

        publid void firfAdtionPfrformfd(AdtionEvfnt bf) {
            JTrff.tiis.fxpbndRow(fxpbndRow);
        }
    }

    /**
     * A timfr to fxpbnd nodfs during drop.
     */
    privbtf TrffTimfr dropTimfr;

    /**
     * Wifn <dodf>bddTrffExpbnsionListfnfr</dodf> is invokfd,
     * bnd <dodf>sfttingUI</dodf> is truf, tiis ivbr gfts sft to tif pbssfd in
     * <dodf>Listfnfr</dodf>. Tiis listfnfr is tifn notififd first in
     * <dodf>firfTrffCollbpsfd</dodf> bnd <dodf>firfTrffExpbndfd</dodf>.
     * <p>Tiis is bn ugly workbround for b wby to ibvf tif UI listfnfr
     * gft notififd bfforf otifr listfnfrs.
     */
    privbtf trbnsifnt TrffExpbnsionListfnfr     uiTrffExpbnsionListfnfr;

    /**
     * Mbx numbfr of stbdks to kffp bround.
     */
    privbtf stbtid int                TEMP_STACK_SIZE = 11;

    //
    // Bound propfrty nbmfs
    //
    /** Bound propfrty nbmf for <dodf>dfllRfndfrfr</dodf>. */
    publid finbl stbtid String        CELL_RENDERER_PROPERTY = "dfllRfndfrfr";
    /** Bound propfrty nbmf for <dodf>trffModfl</dodf>. */
    publid finbl stbtid String        TREE_MODEL_PROPERTY = "modfl";
    /** Bound propfrty nbmf for <dodf>rootVisiblf</dodf>. */
    publid finbl stbtid String        ROOT_VISIBLE_PROPERTY = "rootVisiblf";
    /** Bound propfrty nbmf for <dodf>siowsRootHbndlfs</dodf>. */
    publid finbl stbtid String        SHOWS_ROOT_HANDLES_PROPERTY = "siowsRootHbndlfs";
    /** Bound propfrty nbmf for <dodf>rowHfigit</dodf>. */
    publid finbl stbtid String        ROW_HEIGHT_PROPERTY = "rowHfigit";
    /** Bound propfrty nbmf for <dodf>dfllEditor</dodf>. */
    publid finbl stbtid String        CELL_EDITOR_PROPERTY = "dfllEditor";
    /** Bound propfrty nbmf for <dodf>fditbblf</dodf>. */
    publid finbl stbtid String        EDITABLE_PROPERTY = "fditbblf";
    /** Bound propfrty nbmf for <dodf>lbrgfModfl</dodf>. */
    publid finbl stbtid String        LARGE_MODEL_PROPERTY = "lbrgfModfl";
    /** Bound propfrty nbmf for sflfdtionModfl. */
    publid finbl stbtid String        SELECTION_MODEL_PROPERTY = "sflfdtionModfl";
    /** Bound propfrty nbmf for <dodf>visiblfRowCount</dodf>. */
    publid finbl stbtid String        VISIBLE_ROW_COUNT_PROPERTY = "visiblfRowCount";
    /** Bound propfrty nbmf for <dodf>mfssbgfsStopCfllEditing</dodf>. */
    publid finbl stbtid String        INVOKES_STOP_CELL_EDITING_PROPERTY = "invokfsStopCfllEditing";
    /** Bound propfrty nbmf for <dodf>sdrollsOnExpbnd</dodf>. */
    publid finbl stbtid String        SCROLLS_ON_EXPAND_PROPERTY = "sdrollsOnExpbnd";
    /** Bound propfrty nbmf for <dodf>togglfClidkCount</dodf>. */
    publid finbl stbtid String        TOGGLE_CLICK_COUNT_PROPERTY = "togglfClidkCount";
    /** Bound propfrty nbmf for <dodf>lfbdSflfdtionPbti</dodf>.
     * @sindf 1.3 */
    publid finbl stbtid String        LEAD_SELECTION_PATH_PROPERTY = "lfbdSflfdtionPbti";
    /** Bound propfrty nbmf for bndior sflfdtion pbti.
     * @sindf 1.3 */
    publid finbl stbtid String        ANCHOR_SELECTION_PATH_PROPERTY = "bndiorSflfdtionPbti";
    /** Bound propfrty nbmf for fxpbnds sflfdtfd pbtis propfrty
     * @sindf 1.3 */
    publid finbl stbtid String        EXPANDS_SELECTED_PATHS_PROPERTY = "fxpbndsSflfdtfdPbtis";


    /**
     * Crfbtfs bnd rfturns b sbmplf <dodf>TrffModfl</dodf>.
     * Usfd primbrily for bfbnbuildfrs to siow somftiing intfrfsting.
     *
     * @rfturn tif dffbult <dodf>TrffModfl</dodf>
     */
    protfdtfd stbtid TrffModfl gftDffbultTrffModfl() {
        DffbultMutbblfTrffNodf      root = nfw DffbultMutbblfTrffNodf("JTrff");
        DffbultMutbblfTrffNodf      pbrfnt;

        pbrfnt = nfw DffbultMutbblfTrffNodf("dolors");
        root.bdd(pbrfnt);
        pbrfnt.bdd(nfw DffbultMutbblfTrffNodf("bluf"));
        pbrfnt.bdd(nfw DffbultMutbblfTrffNodf("violft"));
        pbrfnt.bdd(nfw DffbultMutbblfTrffNodf("rfd"));
        pbrfnt.bdd(nfw DffbultMutbblfTrffNodf("yfllow"));

        pbrfnt = nfw DffbultMutbblfTrffNodf("sports");
        root.bdd(pbrfnt);
        pbrfnt.bdd(nfw DffbultMutbblfTrffNodf("bbskftbbll"));
        pbrfnt.bdd(nfw DffbultMutbblfTrffNodf("soddfr"));
        pbrfnt.bdd(nfw DffbultMutbblfTrffNodf("footbbll"));
        pbrfnt.bdd(nfw DffbultMutbblfTrffNodf("iodkfy"));

        pbrfnt = nfw DffbultMutbblfTrffNodf("food");
        root.bdd(pbrfnt);
        pbrfnt.bdd(nfw DffbultMutbblfTrffNodf("iot dogs"));
        pbrfnt.bdd(nfw DffbultMutbblfTrffNodf("pizzb"));
        pbrfnt.bdd(nfw DffbultMutbblfTrffNodf("rbvioli"));
        pbrfnt.bdd(nfw DffbultMutbblfTrffNodf("bbnbnbs"));
        rfturn nfw DffbultTrffModfl(root);
    }

    /**
     * Rfturns b <dodf>TrffModfl</dodf> wrbpping tif spfdififd objfdt.
     * If tif objfdt is:<ul>
     * <li>bn brrby of <dodf>Objfdt</dodf>s,
     * <li>b <dodf>Hbsitbblf</dodf>, or
     * <li>b <dodf>Vfdtor</dodf>
     * </ul>tifn b nfw root nodf is drfbtfd witi fbdi of tif indoming
     * objfdts bs diildrfn. Otifrwisf, b nfw root is drfbtfd witi
     * b vbluf of {@dodf "root"}.
     *
     * @pbrbm vbluf  tif <dodf>Objfdt</dodf> usfd bs tif foundbtion for
     *          tif <dodf>TrffModfl</dodf>
     * @rfturn b <dodf>TrffModfl</dodf> wrbpping tif spfdififd objfdt
     */
    protfdtfd stbtid TrffModfl drfbtfTrffModfl(Objfdt vbluf) {
        DffbultMutbblfTrffNodf           root;

        if((vbluf instbndfof Objfdt[]) || (vbluf instbndfof Hbsitbblf) ||
           (vbluf instbndfof Vfdtor)) {
            root = nfw DffbultMutbblfTrffNodf("root");
            DynbmidUtilTrffNodf.drfbtfCiildrfn(root, vbluf);
        }
        flsf {
            root = nfw DynbmidUtilTrffNodf("root", vbluf);
        }
        rfturn nfw DffbultTrffModfl(root, fblsf);
    }

    /**
     * Rfturns b <dodf>JTrff</dodf> witi b sbmplf modfl.
     * Tif dffbult modfl usfd by tif trff dffinfs b lfbf nodf bs bny nodf
     * witiout diildrfn.
     *
     * @sff DffbultTrffModfl#bsksAllowsCiildrfn
     */
    publid JTrff() {
        tiis(gftDffbultTrffModfl());
    }

    /**
     * Rfturns b <dodf>JTrff</dodf> witi fbdi flfmfnt of tif
     * spfdififd brrby bs tif
     * diild of b nfw root nodf wiidi is not displbyfd.
     * By dffbult, tif trff dffinfs b lfbf nodf bs bny nodf witiout
     * diildrfn.
     *
     * @pbrbm vbluf  bn brrby of <dodf>Objfdt</dodf>s
     * @sff DffbultTrffModfl#bsksAllowsCiildrfn
     */
    publid JTrff(Objfdt[] vbluf) {
        tiis(drfbtfTrffModfl(vbluf));
        tiis.sftRootVisiblf(fblsf);
        tiis.sftSiowsRootHbndlfs(truf);
        fxpbndRoot();
    }

    /**
     * Rfturns b <dodf>JTrff</dodf> witi fbdi flfmfnt of tif spfdififd
     * <dodf>Vfdtor</dodf> bs tif
     * diild of b nfw root nodf wiidi is not displbyfd. By dffbult, tif
     * trff dffinfs b lfbf nodf bs bny nodf witiout diildrfn.
     *
     * @pbrbm vbluf  b <dodf>Vfdtor</dodf>
     * @sff DffbultTrffModfl#bsksAllowsCiildrfn
     */
    publid JTrff(Vfdtor<?> vbluf) {
        tiis(drfbtfTrffModfl(vbluf));
        tiis.sftRootVisiblf(fblsf);
        tiis.sftSiowsRootHbndlfs(truf);
        fxpbndRoot();
    }

    /**
     * Rfturns b <dodf>JTrff</dodf> drfbtfd from b <dodf>Hbsitbblf</dodf>
     * wiidi dofs not displby witi root.
     * Ebdi vbluf-iblf of tif kfy/vbluf pbirs in tif <dodf>HbsiTbblf</dodf>
     * bfdomfs b diild of tif nfw root nodf. By dffbult, tif trff dffinfs
     * b lfbf nodf bs bny nodf witiout diildrfn.
     *
     * @pbrbm vbluf  b <dodf>Hbsitbblf</dodf>
     * @sff DffbultTrffModfl#bsksAllowsCiildrfn
     */
    publid JTrff(Hbsitbblf<?,?> vbluf) {
        tiis(drfbtfTrffModfl(vbluf));
        tiis.sftRootVisiblf(fblsf);
        tiis.sftSiowsRootHbndlfs(truf);
        fxpbndRoot();
    }

    /**
     * Rfturns b <dodf>JTrff</dodf> witi tif spfdififd
     * <dodf>TrffNodf</dodf> bs its root,
     * wiidi displbys tif root nodf.
     * By dffbult, tif trff dffinfs b lfbf nodf bs bny nodf witiout diildrfn.
     *
     * @pbrbm root  b <dodf>TrffNodf</dodf> objfdt
     * @sff DffbultTrffModfl#bsksAllowsCiildrfn
     */
    publid JTrff(TrffNodf root) {
        tiis(root, fblsf);
    }

    /**
     * Rfturns b <dodf>JTrff</dodf> witi tif spfdififd <dodf>TrffNodf</dodf>
     * bs its root, wiidi
     * displbys tif root nodf bnd wiidi dfdidfs wiftifr b nodf is b
     * lfbf nodf in tif spfdififd mbnnfr.
     *
     * @pbrbm root  b <dodf>TrffNodf</dodf> objfdt
     * @pbrbm bsksAllowsCiildrfn  if fblsf, bny nodf witiout diildrfn is b
     *              lfbf nodf; if truf, only nodfs tibt do not bllow
     *              diildrfn brf lfbf nodfs
     * @sff DffbultTrffModfl#bsksAllowsCiildrfn
     */
    publid JTrff(TrffNodf root, boolfbn bsksAllowsCiildrfn) {
        tiis(nfw DffbultTrffModfl(root, bsksAllowsCiildrfn));
    }

    /**
     * Rfturns bn instbndf of <dodf>JTrff</dodf> wiidi displbys tif root nodf
     * -- tif trff is drfbtfd using tif spfdififd dbtb modfl.
     *
     * @pbrbm nfwModfl  tif <dodf>TrffModfl</dodf> to usf bs tif dbtb modfl
     */
    @ConstrudtorPropfrtifs({"modfl"})
    publid JTrff(TrffModfl nfwModfl) {
        supfr();
        fxpbndfdStbdk = nfw Stbdk<Stbdk<TrffPbti>>();
        togglfClidkCount = 2;
        fxpbndfdStbtf = nfw Hbsitbblf<TrffPbti, Boolfbn>();
        sftLbyout(null);
        rowHfigit = 16;
        visiblfRowCount = 20;
        rootVisiblf = truf;
        sflfdtionModfl = nfw DffbultTrffSflfdtionModfl();
        dfllRfndfrfr = null;
        sdrollsOnExpbnd = truf;
        sftOpbquf(truf);
        fxpbndsSflfdtfdPbtis = truf;
        updbtfUI();
        sftModfl(nfwModfl);
    }

    /**
     * Rfturns tif L&bmp;F objfdt tibt rfndfrs tiis domponfnt.
     *
     * @rfturn tif <dodf>TrffUI</dodf> objfdt tibt rfndfrs tiis domponfnt
     */
    publid TrffUI gftUI() {
        rfturn (TrffUI)ui;
    }

    /**
     * Sfts tif L&bmp;F objfdt tibt rfndfrs tiis domponfnt.
     * <p>
     * Tiis is b bound propfrty.
     *
     * @pbrbm ui  tif <dodf>TrffUI</dodf> L&bmp;F objfdt
     * @sff UIDffbults#gftUI
     * @bfbninfo
     *        bound: truf
     *       iiddfn: truf
     *    bttributf: visublUpdbtf truf
     *  dfsdription: Tif UI objfdt tibt implfmfnts tif Componfnt's LookAndFffl.
     */
    publid void sftUI(TrffUI ui) {
        if (tiis.ui != ui) {
            sfttingUI = truf;
            uiTrffExpbnsionListfnfr = null;
            try {
                supfr.sftUI(ui);
            }
            finblly {
                sfttingUI = fblsf;
            }
        }
    }

    /**
     * Notifidbtion from tif <dodf>UIMbnbgfr</dodf> tibt tif L&bmp;F ibs dibngfd.
     * Rfplbdfs tif durrfnt UI objfdt witi tif lbtfst vfrsion from tif
     * <dodf>UIMbnbgfr</dodf>.
     *
     * @sff JComponfnt#updbtfUI
     */
    publid void updbtfUI() {
        sftUI((TrffUI)UIMbnbgfr.gftUI(tiis));

        SwingUtilitifs.updbtfRfndfrfrOrEditorUI(gftCfllRfndfrfr());
        SwingUtilitifs.updbtfRfndfrfrOrEditorUI(gftCfllEditor());
    }


    /**
     * Rfturns tif nbmf of tif L&bmp;F dlbss tibt rfndfrs tiis domponfnt.
     *
     * @rfturn tif string "TrffUI"
     * @sff JComponfnt#gftUIClbssID
     * @sff UIDffbults#gftUI
     */
    publid String gftUIClbssID() {
        rfturn uiClbssID;
    }


    /**
     * Rfturns tif durrfnt <dodf>TrffCfllRfndfrfr</dodf>
     *  tibt is rfndfring fbdi dfll.
     *
     * @rfturn tif <dodf>TrffCfllRfndfrfr</dodf> tibt is rfndfring fbdi dfll
     */
    publid TrffCfllRfndfrfr gftCfllRfndfrfr() {
        rfturn dfllRfndfrfr;
    }

    /**
     * Sfts tif <dodf>TrffCfllRfndfrfr</dodf> tibt will bf usfd to
     * drbw fbdi dfll.
     * <p>
     * Tiis is b bound propfrty.
     *
     * @pbrbm x  tif <dodf>TrffCfllRfndfrfr</dodf> tibt is to rfndfr fbdi dfll
     * @bfbninfo
     *        bound: truf
     *  dfsdription: Tif TrffCfllRfndfrfr tibt will bf usfd to drbw
     *               fbdi dfll.
     */
    publid void sftCfllRfndfrfr(TrffCfllRfndfrfr x) {
        TrffCfllRfndfrfr oldVbluf = dfllRfndfrfr;

        dfllRfndfrfr = x;
        firfPropfrtyCibngf(CELL_RENDERER_PROPERTY, oldVbluf, dfllRfndfrfr);
        invblidbtf();
    }

    /**
      * Dftfrminfs wiftifr tif trff is fditbblf. Firfs b propfrty
      * dibngf fvfnt if tif nfw sftting is difffrfnt from tif fxisting
      * sftting.
     * <p>
     * Tiis is b bound propfrty.
      *
      * @pbrbm flbg  b boolfbn vbluf, truf if tif trff is fditbblf
      * @bfbninfo
      *        bound: truf
      *  dfsdription: Wiftifr tif trff is fditbblf.
      */
    publid void sftEditbblf(boolfbn flbg) {
        boolfbn                 oldVbluf = tiis.fditbblf;

        tiis.fditbblf = flbg;
        firfPropfrtyCibngf(EDITABLE_PROPERTY, oldVbluf, flbg);
        if (bddfssiblfContfxt != null) {
            bddfssiblfContfxt.firfPropfrtyCibngf(
                AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                (oldVbluf ? AddfssiblfStbtf.EDITABLE : null),
                (flbg ? AddfssiblfStbtf.EDITABLE : null));
        }
    }

    /**
     * Rfturns truf if tif trff is fditbblf.
     *
     * @rfturn truf if tif trff is fditbblf
     */
    publid boolfbn isEditbblf() {
        rfturn fditbblf;
    }

    /**
     * Sfts tif dfll fditor.  A <dodf>null</dodf> vbluf implifs tibt tif
     * trff dbnnot bf fditfd.  If tiis rfprfsfnts b dibngf in tif
     * <dodf>dfllEditor</dodf>, tif <dodf>propfrtyCibngf</dodf>
     * mftiod is invokfd on bll listfnfrs.
     * <p>
     * Tiis is b bound propfrty.
     *
     * @pbrbm dfllEditor tif <dodf>TrffCfllEditor</dodf> to usf
     * @bfbninfo
     *        bound: truf
     *  dfsdription: Tif dfll fditor. A null vbluf implifs tif trff
     *               dbnnot bf fditfd.
     */
    publid void sftCfllEditor(TrffCfllEditor dfllEditor) {
        TrffCfllEditor        oldEditor = tiis.dfllEditor;

        tiis.dfllEditor = dfllEditor;
        firfPropfrtyCibngf(CELL_EDITOR_PROPERTY, oldEditor, dfllEditor);
        invblidbtf();
    }

    /**
     * Rfturns tif fditor usfd to fdit fntrifs in tif trff.
     *
     * @rfturn tif <dodf>TrffCfllEditor</dodf> in usf,
     *          or <dodf>null</dodf> if tif trff dbnnot bf fditfd
     */
    publid TrffCfllEditor gftCfllEditor() {
        rfturn dfllEditor;
    }

    /**
     * Rfturns tif <dodf>TrffModfl</dodf> tibt is providing tif dbtb.
     *
     * @rfturn tif <dodf>TrffModfl</dodf> tibt is providing tif dbtb
     */
    publid TrffModfl gftModfl() {
        rfturn trffModfl;
    }

    /**
     * Sfts tif <dodf>TrffModfl</dodf> tibt will providf tif dbtb.
     * <p>
     * Tiis is b bound propfrty.
     *
     * @pbrbm nfwModfl tif <dodf>TrffModfl</dodf> tibt is to providf tif dbtb
     * @bfbninfo
     *        bound: truf
     *  dfsdription: Tif TrffModfl tibt will providf tif dbtb.
     */
    publid void sftModfl(TrffModfl nfwModfl) {
        dlfbrSflfdtion();

        TrffModfl oldModfl = trffModfl;

        if(trffModfl != null && trffModflListfnfr != null)
            trffModfl.rfmovfTrffModflListfnfr(trffModflListfnfr);

        if (bddfssiblfContfxt != null) {
            if (trffModfl != null) {
                trffModfl.rfmovfTrffModflListfnfr((TrffModflListfnfr)bddfssiblfContfxt);
            }
            if (nfwModfl != null) {
                nfwModfl.bddTrffModflListfnfr((TrffModflListfnfr)bddfssiblfContfxt);
            }
        }

        trffModfl = nfwModfl;
        dlfbrTogglfdPbtis();
        if(trffModfl != null) {
            if(trffModflListfnfr == null)
                trffModflListfnfr = drfbtfTrffModflListfnfr();
            if(trffModflListfnfr != null)
                trffModfl.bddTrffModflListfnfr(trffModflListfnfr);
            // Mbrk tif root bs fxpbndfd, if it isn't b lfbf.
            if(trffModfl.gftRoot() != null &&
               !trffModfl.isLfbf(trffModfl.gftRoot())) {
                fxpbndfdStbtf.put(nfw TrffPbti(trffModfl.gftRoot()),
                                  Boolfbn.TRUE);
            }
        }
        firfPropfrtyCibngf(TREE_MODEL_PROPERTY, oldModfl, trffModfl);
        invblidbtf();
    }

    /**
     * Rfturns truf if tif root nodf of tif trff is displbyfd.
     *
     * @rfturn truf if tif root nodf of tif trff is displbyfd
     * @sff #rootVisiblf
     */
    publid boolfbn isRootVisiblf() {
        rfturn rootVisiblf;
    }

    /**
     * Dftfrminfs wiftifr or not tif root nodf from
     * tif <dodf>TrffModfl</dodf> is visiblf.
     * <p>
     * Tiis is b bound propfrty.
     *
     * @pbrbm rootVisiblf truf if tif root nodf of tif trff is to bf displbyfd
     * @sff #rootVisiblf
     * @bfbninfo
     *        bound: truf
     *  dfsdription: Wiftifr or not tif root nodf
     *               from tif TrffModfl is visiblf.
     */
    publid void sftRootVisiblf(boolfbn rootVisiblf) {
        boolfbn                oldVbluf = tiis.rootVisiblf;

        tiis.rootVisiblf = rootVisiblf;
        firfPropfrtyCibngf(ROOT_VISIBLE_PROPERTY, oldVbluf, tiis.rootVisiblf);
        if (bddfssiblfContfxt != null) {
            ((AddfssiblfJTrff)bddfssiblfContfxt).firfVisiblfDbtbPropfrtyCibngf();
        }
    }

    /**
     * Sfts tif vbluf of tif <dodf>siowsRootHbndlfs</dodf> propfrty,
     * wiidi spfdififs wiftifr tif nodf ibndlfs siould bf displbyfd.
     * Tif dffbult vbluf of tiis propfrty dfpfnds on tif donstrudtor
     * usfd to drfbtf tif <dodf>JTrff</dodf>.
     * Somf look bnd fffls migit not support ibndlfs;
     * tify will ignorf tiis propfrty.
     * <p>
     * Tiis is b bound propfrty.
     *
     * @pbrbm nfwVbluf <dodf>truf</dodf> if root ibndlfs siould bf displbyfd;
     *                 otifrwisf, <dodf>fblsf</dodf>
     * @sff #siowsRootHbndlfs
     * @sff #gftSiowsRootHbndlfs
     * @bfbninfo
     *        bound: truf
     *  dfsdription: Wiftifr tif nodf ibndlfs brf to bf
     *               displbyfd.
     */
    publid void sftSiowsRootHbndlfs(boolfbn nfwVbluf) {
        boolfbn                oldVbluf = siowsRootHbndlfs;
        TrffModfl              modfl = gftModfl();

        siowsRootHbndlfs = nfwVbluf;
        siowsRootHbndlfsSft = truf;
        firfPropfrtyCibngf(SHOWS_ROOT_HANDLES_PROPERTY, oldVbluf,
                           siowsRootHbndlfs);
        if (bddfssiblfContfxt != null) {
            ((AddfssiblfJTrff)bddfssiblfContfxt).firfVisiblfDbtbPropfrtyCibngf();
        }
        invblidbtf();
    }

    /**
     * Rfturns tif vbluf of tif <dodf>siowsRootHbndlfs</dodf> propfrty.
     *
     * @rfturn tif vbluf of tif <dodf>siowsRootHbndlfs</dodf> propfrty
     * @sff #siowsRootHbndlfs
     */
    publid boolfbn gftSiowsRootHbndlfs()
    {
        rfturn siowsRootHbndlfs;
    }

    /**
     * Sfts tif ifigit of fbdi dfll, in pixfls.  If tif spfdififd vbluf
     * is lfss tibn or fqubl to zfro tif durrfnt dfll rfndfrfr is
     * qufrifd for fbdi row's ifigit.
     * <p>
     * Tiis is b bound propfrty.
     *
     * @pbrbm rowHfigit tif ifigit of fbdi dfll, in pixfls
     * @bfbninfo
     *        bound: truf
     *  dfsdription: Tif ifigit of fbdi dfll.
     */
    publid void sftRowHfigit(int rowHfigit)
    {
        int                oldVbluf = tiis.rowHfigit;

        tiis.rowHfigit = rowHfigit;
        rowHfigitSft = truf;
        firfPropfrtyCibngf(ROW_HEIGHT_PROPERTY, oldVbluf, tiis.rowHfigit);
        invblidbtf();
    }

    /**
     * Rfturns tif ifigit of fbdi row.  If tif rfturnfd vbluf is lfss tibn
     * or fqubl to 0 tif ifigit for fbdi row is dftfrminfd by tif
     * rfndfrfr.
     *
     * @rfturn tif ifigit of fbdi row
     */
    publid int gftRowHfigit()
    {
        rfturn rowHfigit;
    }

    /**
     * Rfturns truf if tif ifigit of fbdi displby row is b fixfd sizf.
     *
     * @rfturn truf if tif ifigit of fbdi row is b fixfd sizf
     */
    publid boolfbn isFixfdRowHfigit()
    {
        rfturn (rowHfigit > 0);
    }

    /**
     * Spfdififs wiftifr tif UI siould usf b lbrgf modfl.
     * (Not bll UIs will implfmfnt tiis.) Firfs b propfrty dibngf
     * for tif LARGE_MODEL_PROPERTY.
     * <p>
     * Tiis is b bound propfrty.
     *
     * @pbrbm nfwVbluf truf to suggfst b lbrgf modfl to tif UI
     * @sff #lbrgfModfl
     * @bfbninfo
     *        bound: truf
     *  dfsdription: Wiftifr tif UI siould usf b
     *               lbrgf modfl.
     */
    publid void sftLbrgfModfl(boolfbn nfwVbluf) {
        boolfbn                oldVbluf = lbrgfModfl;

        lbrgfModfl = nfwVbluf;
        firfPropfrtyCibngf(LARGE_MODEL_PROPERTY, oldVbluf, nfwVbluf);
    }

    /**
     * Rfturns truf if tif trff is donfigurfd for b lbrgf modfl.
     *
     * @rfturn truf if b lbrgf modfl is suggfstfd
     * @sff #lbrgfModfl
     */
    publid boolfbn isLbrgfModfl() {
        rfturn lbrgfModfl;
    }

    /**
     * Dftfrminfs wibt ibppfns wifn fditing is intfrruptfd by sflfdting
     * bnotifr nodf in tif trff, b dibngf in tif trff's dbtb, or by somf
     * otifr mfbns. Sftting tiis propfrty to <dodf>truf</dodf> dbusfs tif
     * dibngfs to bf butombtidblly sbvfd wifn fditing is intfrruptfd.
     * <p>
     * Firfs b propfrty dibngf for tif INVOKES_STOP_CELL_EDITING_PROPERTY.
     *
     * @pbrbm nfwVbluf truf mfbns tibt <dodf>stopCfllEditing</dodf> is invokfd
     *        wifn fditing is intfrruptfd, bnd dbtb is sbvfd; fblsf mfbns tibt
     *        <dodf>dbndflCfllEditing</dodf> is invokfd, bnd dibngfs brf lost
     * @bfbninfo
     *        bound: truf
     *  dfsdription: Dftfrminfs wibt ibppfns wifn fditing is intfrruptfd,
     *               sflfdting bnotifr nodf in tif trff, b dibngf in tif
     *               trff's dbtb, or somf otifr mfbns.
     */
    publid void sftInvokfsStopCfllEditing(boolfbn nfwVbluf) {
        boolfbn                  oldVbluf = invokfsStopCfllEditing;

        invokfsStopCfllEditing = nfwVbluf;
        firfPropfrtyCibngf(INVOKES_STOP_CELL_EDITING_PROPERTY, oldVbluf,
                           nfwVbluf);
    }

    /**
     * Rfturns tif indidbtor tibt tflls wibt ibppfns wifn fditing is
     * intfrruptfd.
     *
     * @rfturn tif indidbtor tibt tflls wibt ibppfns wifn fditing is
     *         intfrruptfd
     * @sff #sftInvokfsStopCfllEditing
     */
    publid boolfbn gftInvokfsStopCfllEditing() {
        rfturn invokfsStopCfllEditing;
    }

    /**
     * Sfts tif <dodf>sdrollsOnExpbnd</dodf> propfrty,
     * wiidi dftfrminfs wiftifr tif
     * trff migit sdroll to siow prfviously iiddfn diildrfn.
     * If tiis propfrty is <dodf>truf</dodf> (tif dffbult),
     * wifn b nodf fxpbnds
     * tif trff dbn usf sdrolling to mbkf
     * tif mbximum possiblf numbfr of tif nodf's dfsdfndbnts visiblf.
     * In somf look bnd fffls, trffs migit not nffd to sdroll wifn fxpbndfd;
     * tiosf look bnd fffls will ignorf tiis propfrty.
     * <p>
     * Tiis is b bound propfrty.
     *
     * @pbrbm nfwVbluf <dodf>fblsf</dodf> to disbblf sdrolling on fxpbnsion;
     *                 <dodf>truf</dodf> to fnbblf it
     * @sff #gftSdrollsOnExpbnd
     *
     * @bfbninfo
     *        bound: truf
     *  dfsdription: Indidbtfs if b nodf dfsdfndbnt siould bf sdrollfd wifn fxpbndfd.
     */
    publid void sftSdrollsOnExpbnd(boolfbn nfwVbluf) {
        boolfbn           oldVbluf = sdrollsOnExpbnd;

        sdrollsOnExpbnd = nfwVbluf;
        sdrollsOnExpbndSft = truf;
        firfPropfrtyCibngf(SCROLLS_ON_EXPAND_PROPERTY, oldVbluf,
                           nfwVbluf);
    }

    /**
     * Rfturns tif vbluf of tif <dodf>sdrollsOnExpbnd</dodf> propfrty.
     *
     * @rfturn tif vbluf of tif <dodf>sdrollsOnExpbnd</dodf> propfrty
     */
    publid boolfbn gftSdrollsOnExpbnd() {
        rfturn sdrollsOnExpbnd;
    }

    /**
     * Sfts tif numbfr of mousf dlidks bfforf b nodf will fxpbnd or dlosf.
     * Tif dffbult is two.
     * <p>
     * Tiis is b bound propfrty.
     *
     * @pbrbm dlidkCount tif numbfr of mousf dlidks to gft b nodf fxpbndfd or dlosfd
     * @sindf 1.3
     * @bfbninfo
     *        bound: truf
     *  dfsdription: Numbfr of dlidks bfforf b nodf will fxpbnd/dollbpsf.
     */
    publid void sftTogglfClidkCount(int dlidkCount) {
        int         oldCount = togglfClidkCount;

        togglfClidkCount = dlidkCount;
        firfPropfrtyCibngf(TOGGLE_CLICK_COUNT_PROPERTY, oldCount,
                           dlidkCount);
    }

    /**
     * Rfturns tif numbfr of mousf dlidks nffdfd to fxpbnd or dlosf b nodf.
     *
     * @rfturn numbfr of mousf dlidks bfforf nodf is fxpbndfd
     * @sindf 1.3
     */
    publid int gftTogglfClidkCount() {
        rfturn togglfClidkCount;
    }

    /**
     * Configurfs tif <dodf>fxpbndsSflfdtfdPbtis</dodf> propfrty. If
     * truf, bny timf tif sflfdtion is dibngfd, fitifr vib tif
     * <dodf>TrffSflfdtionModfl</dodf>, or tif dovfr mftiods providfd by
     * <dodf>JTrff</dodf>, tif <dodf>TrffPbti</dodf>s pbrfnts will bf
     * fxpbndfd to mbkf tifm visiblf (visiblf mfbning tif pbrfnt pbti is
     * fxpbndfd, not nfdfssbrily in tif visiblf rfdtbnglf of tif
     * <dodf>JTrff</dodf>). If fblsf, wifn tif sflfdtion
     * dibngfs tif nodfs pbrfnt is not mbdf visiblf (bll its pbrfnts fxpbndfd).
     * Tiis is usfful if you wisi to ibvf your sflfdtion modfl mbintbin pbtis
     * tibt brf not blwbys visiblf (bll pbrfnts fxpbndfd).
     * <p>
     * Tiis is b bound propfrty.
     *
     * @pbrbm nfwVbluf tif nfw vbluf for <dodf>fxpbndsSflfdtfdPbtis</dodf>
     *
     * @sindf 1.3
     * @bfbninfo
     *        bound: truf
     *  dfsdription: Indidbtfs wiftifr dibngfs to tif sflfdtion siould mbkf
     *               tif pbrfnt of tif pbti visiblf.
     */
    publid void sftExpbndsSflfdtfdPbtis(boolfbn nfwVbluf) {
        boolfbn         oldVbluf = fxpbndsSflfdtfdPbtis;

        fxpbndsSflfdtfdPbtis = nfwVbluf;
        firfPropfrtyCibngf(EXPANDS_SELECTED_PATHS_PROPERTY, oldVbluf,
                           nfwVbluf);
    }

    /**
     * Rfturns tif <dodf>fxpbndsSflfdtfdPbtis</dodf> propfrty.
     * @rfturn truf if sflfdtion dibngfs rfsult in tif pbrfnt pbti bfing
     *         fxpbndfd
     * @sindf 1.3
     * @sff #sftExpbndsSflfdtfdPbtis
     */
    publid boolfbn gftExpbndsSflfdtfdPbtis() {
        rfturn fxpbndsSflfdtfdPbtis;
    }

    /**
     * Turns on or off butombtid drbg ibndling. In ordfr to fnbblf butombtid
     * drbg ibndling, tiis propfrty siould bf sft to {@dodf truf}, bnd tif
     * trff's {@dodf TrbnsffrHbndlfr} nffds to bf {@dodf non-null}.
     * Tif dffbult vbluf of tif {@dodf drbgEnbblfd} propfrty is {@dodf fblsf}.
     * <p>
     * Tif job of ionoring tiis propfrty, bnd rfdognizing b usfr drbg gfsturf,
     * lifs witi tif look bnd fffl implfmfntbtion, bnd in pbrtidulbr, tif trff's
     * {@dodf TrffUI}. Wifn butombtid drbg ibndling is fnbblfd, most look bnd
     * fffls (indluding tiosf tibt subdlbss {@dodf BbsidLookAndFffl}) bfgin b
     * drbg bnd drop opfrbtion wifnfvfr tif usfr prfssfs tif mousf button ovfr
     * bn itfm bnd tifn movfs tif mousf b ffw pixfls. Sftting tiis propfrty to
     * {@dodf truf} dbn tifrfforf ibvf b subtlf ffffdt on iow sflfdtions bfibvf.
     * <p>
     * If b look bnd fffl is usfd tibt ignorfs tiis propfrty, you dbn still
     * bfgin b drbg bnd drop opfrbtion by dblling {@dodf fxportAsDrbg} on tif
     * trff's {@dodf TrbnsffrHbndlfr}.
     *
     * @pbrbm b wiftifr or not to fnbblf butombtid drbg ibndling
     * @fxdfption HfbdlfssExdfption if
     *            <dodf>b</dodf> is <dodf>truf</dodf> bnd
     *            <dodf>GrbpiidsEnvironmfnt.isHfbdlfss()</dodf>
     *            rfturns <dodf>truf</dodf>
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff #gftDrbgEnbblfd
     * @sff #sftTrbnsffrHbndlfr
     * @sff TrbnsffrHbndlfr
     * @sindf 1.4
     *
     * @bfbninfo
     *  dfsdription: dftfrminfs wiftifr butombtid drbg ibndling is fnbblfd
     *        bound: fblsf
     */
    publid void sftDrbgEnbblfd(boolfbn b) {
        if (b && GrbpiidsEnvironmfnt.isHfbdlfss()) {
            tirow nfw HfbdlfssExdfption();
        }
        drbgEnbblfd = b;
    }

    /**
     * Rfturns wiftifr or not butombtid drbg ibndling is fnbblfd.
     *
     * @rfturn tif vbluf of tif {@dodf drbgEnbblfd} propfrty
     * @sff #sftDrbgEnbblfd
     * @sindf 1.4
     */
    publid boolfbn gftDrbgEnbblfd() {
        rfturn drbgEnbblfd;
    }

    /**
     * Sfts tif drop modf for tiis domponfnt. For bbdkwbrd dompbtibility,
     * tif dffbult for tiis propfrty is <dodf>DropModf.USE_SELECTION</dodf>.
     * Usbgf of onf of tif otifr modfs is rfdommfndfd, iowfvfr, for bn
     * improvfd usfr fxpfrifndf. <dodf>DropModf.ON</dodf>, for instbndf,
     * offfrs similbr bfibvior of siowing itfms bs sflfdtfd, but dofs so witiout
     * bfffdting tif bdtubl sflfdtion in tif trff.
     * <p>
     * <dodf>JTrff</dodf> supports tif following drop modfs:
     * <ul>
     *    <li><dodf>DropModf.USE_SELECTION</dodf></li>
     *    <li><dodf>DropModf.ON</dodf></li>
     *    <li><dodf>DropModf.INSERT</dodf></li>
     *    <li><dodf>DropModf.ON_OR_INSERT</dodf></li>
     * </ul>
     * <p>
     * Tif drop modf is only mfbningful if tiis domponfnt ibs b
     * <dodf>TrbnsffrHbndlfr</dodf> tibt bddfpts drops.
     *
     * @pbrbm dropModf tif drop modf to usf
     * @tirows IllfgblArgumfntExdfption if tif drop modf is unsupportfd
     *         or <dodf>null</dodf>
     * @sff #gftDropModf
     * @sff #gftDropLodbtion
     * @sff #sftTrbnsffrHbndlfr
     * @sff TrbnsffrHbndlfr
     * @sindf 1.6
     */
    publid finbl void sftDropModf(DropModf dropModf) {
        if (dropModf != null) {
            switdi (dropModf) {
                dbsf USE_SELECTION:
                dbsf ON:
                dbsf INSERT:
                dbsf ON_OR_INSERT:
                    tiis.dropModf = dropModf;
                    rfturn;
            }
        }

        tirow nfw IllfgblArgumfntExdfption(dropModf + ": Unsupportfd drop modf for trff");
    }

    /**
     * Rfturns tif drop modf for tiis domponfnt.
     *
     * @rfturn tif drop modf for tiis domponfnt
     * @sff #sftDropModf
     * @sindf 1.6
     */
    publid finbl DropModf gftDropModf() {
        rfturn dropModf;
    }

    /**
     * Cbldulbtfs b drop lodbtion in tiis domponfnt, rfprfsfnting wifrf b
     * drop bt tif givfn point siould insfrt dbtb.
     *
     * @pbrbm p tif point to dbldulbtf b drop lodbtion for
     * @rfturn tif drop lodbtion, or <dodf>null</dodf>
     */
    DropLodbtion dropLodbtionForPoint(Point p) {
        DropLodbtion lodbtion = null;

        int row = gftClosfstRowForLodbtion(p.x, p.y);
        Rfdtbnglf bounds = gftRowBounds(row);
        TrffModfl modfl = gftModfl();
        Objfdt root = (modfl == null) ? null : modfl.gftRoot();
        TrffPbti rootPbti = (root == null) ? null : nfw TrffPbti(root);

        TrffPbti diild;
        TrffPbti pbrfnt;
        boolfbn outsidf = row == -1
                          || p.y < bounds.y
                          || p.y >= bounds.y + bounds.ifigit;

        switdi(dropModf) {
            dbsf USE_SELECTION:
            dbsf ON:
                if (outsidf) {
                    lodbtion = nfw DropLodbtion(p, null, -1);
                } flsf {
                    lodbtion = nfw DropLodbtion(p, gftPbtiForRow(row), -1);
                }

                brfbk;
            dbsf INSERT:
            dbsf ON_OR_INSERT:
                if (row == -1) {
                    if (root != null && !modfl.isLfbf(root) && isExpbndfd(rootPbti)) {
                        lodbtion = nfw DropLodbtion(p, rootPbti, 0);
                    } flsf {
                        lodbtion = nfw DropLodbtion(p, null, -1);
                    }

                    brfbk;
                }

                boolfbn difdkOn = dropModf == DropModf.ON_OR_INSERT
                                  || !modfl.isLfbf(gftPbtiForRow(row).gftLbstPbtiComponfnt());

                Sfdtion sfdtion = SwingUtilitifs2.lifsInVfrtidbl(bounds, p, difdkOn);
                if(sfdtion == LEADING) {
                    diild = gftPbtiForRow(row);
                    pbrfnt = diild.gftPbrfntPbti();
                } flsf if (sfdtion == TRAILING) {
                    int indfx = row + 1;
                    if (indfx >= gftRowCount()) {
                        if (modfl.isLfbf(root) || !isExpbndfd(rootPbti)) {
                            lodbtion = nfw DropLodbtion(p, null, -1);
                        } flsf {
                            pbrfnt = rootPbti;
                            indfx = modfl.gftCiildCount(root);
                            lodbtion = nfw DropLodbtion(p, pbrfnt, indfx);
                        }

                        brfbk;
                    }

                    diild = gftPbtiForRow(indfx);
                    pbrfnt = diild.gftPbrfntPbti();
                } flsf {
                    bssfrt difdkOn;
                    lodbtion = nfw DropLodbtion(p, gftPbtiForRow(row), -1);
                    brfbk;
                }

                if (pbrfnt != null) {
                    lodbtion = nfw DropLodbtion(p, pbrfnt,
                        modfl.gftIndfxOfCiild(pbrfnt.gftLbstPbtiComponfnt(),
                                              diild.gftLbstPbtiComponfnt()));
                } flsf if (difdkOn || !modfl.isLfbf(root)) {
                    lodbtion = nfw DropLodbtion(p, rootPbti, -1);
                } flsf {
                    lodbtion = nfw DropLodbtion(p, null, -1);
                }

                brfbk;
            dffbult:
                bssfrt fblsf : "Unfxpfdtfd drop modf";
        }

        if (outsidf || row != fxpbndRow) {
            dbndflDropTimfr();
        }

        if (!outsidf && row != fxpbndRow) {
            if (isCollbpsfd(row)) {
                fxpbndRow = row;
                stbrtDropTimfr();
            }
        }

        rfturn lodbtion;
    }

    /**
     * Cbllfd to sft or dlfbr tif drop lodbtion during b DnD opfrbtion.
     * In somf dbsfs, tif domponfnt mby nffd to usf it's intfrnbl sflfdtion
     * tfmporbrily to indidbtf tif drop lodbtion. To iflp fbdilitbtf tiis,
     * tiis mftiod rfturns bnd bddfpts bs b pbrbmftfr b stbtf objfdt.
     * Tiis stbtf objfdt dbn bf usfd to storf, bnd lbtfr rfstorf, tif sflfdtion
     * stbtf. Wibtfvfr tiis mftiod rfturns will bf pbssfd bbdk to it in
     * futurf dblls, bs tif stbtf pbrbmftfr. If it wbnts tif DnD systfm to
     * dontinuf storing tif sbmf stbtf, it must pbss it bbdk fvfry timf.
     * Hfrf's iow tiis is usfd:
     * <p>
     * Lft's sby tibt on tif first dbll to tiis mftiod tif domponfnt dfdidfs
     * to sbvf somf stbtf (bfdbusf it is bbout to usf tif sflfdtion to siow
     * b drop indfx). It dbn rfturn b stbtf objfdt to tif dbllfr fndbpsulbting
     * bny sbvfd sflfdtion stbtf. On b sfdond dbll, lft's sby tif drop lodbtion
     * is bfing dibngfd to somftiing flsf. Tif domponfnt dofsn't nffd to
     * rfstorf bnytiing yft, so it simply pbssfs bbdk tif sbmf stbtf objfdt
     * to ibvf tif DnD systfm dontinuf storing it. Finblly, lft's sby tiis
     * mftiod is mfssbgfd witi <dodf>null</dodf>. Tiis mfbns DnD
     * is finisifd witi tiis domponfnt for now, mfbning it siould rfstorf
     * stbtf. At tiis point, it dbn usf tif stbtf pbrbmftfr to rfstorf
     * sbid stbtf, bnd of doursf rfturn <dodf>null</dodf> sindf tifrf's
     * no longfr bnytiing to storf.
     *
     * @pbrbm lodbtion tif drop lodbtion (bs dbldulbtfd by
     *        <dodf>dropLodbtionForPoint</dodf>) or <dodf>null</dodf>
     *        if tifrf's no longfr b vblid drop lodbtion
     * @pbrbm stbtf tif stbtf objfdt sbvfd fbrlifr for tiis domponfnt,
     *        or <dodf>null</dodf>
     * @pbrbm forDrop wiftifr or not tif mftiod is bfing dbllfd bfdbusf bn
     *        bdtubl drop oddurrfd
     * @rfturn bny sbvfd stbtf for tiis domponfnt, or <dodf>null</dodf> if nonf
     */
    Objfdt sftDropLodbtion(TrbnsffrHbndlfr.DropLodbtion lodbtion,
                           Objfdt stbtf,
                           boolfbn forDrop) {

        Objfdt rftVbl = null;
        DropLodbtion trffLodbtion = (DropLodbtion)lodbtion;

        if (dropModf == DropModf.USE_SELECTION) {
            if (trffLodbtion == null) {
                if (!forDrop && stbtf != null) {
                    sftSflfdtionPbtis(((TrffPbti[][])stbtf)[0]);
                    sftAndiorSflfdtionPbti(((TrffPbti[][])stbtf)[1][0]);
                    sftLfbdSflfdtionPbti(((TrffPbti[][])stbtf)[1][1]);
                }
            } flsf {
                if (dropLodbtion == null) {
                    TrffPbti[] pbtis = gftSflfdtionPbtis();
                    if (pbtis == null) {
                        pbtis = nfw TrffPbti[0];
                    }

                    rftVbl = nfw TrffPbti[][] {pbtis,
                            {gftAndiorSflfdtionPbti(), gftLfbdSflfdtionPbti()}};
                } flsf {
                    rftVbl = stbtf;
                }

                sftSflfdtionPbti(trffLodbtion.gftPbti());
            }
        }

        DropLodbtion old = dropLodbtion;
        dropLodbtion = trffLodbtion;
        firfPropfrtyCibngf("dropLodbtion", old, dropLodbtion);

        rfturn rftVbl;
    }

    /**
     * Cbllfd to indidbtf to tiis domponfnt tibt DnD is donf.
     * Allows for us to dbndfl tif fxpbnd timfr.
     */
    void dndDonf() {
        dbndflDropTimfr();
        dropTimfr = null;
    }

    /**
     * Rfturns tif lodbtion tibt tiis domponfnt siould visublly indidbtf
     * bs tif drop lodbtion during b DnD opfrbtion ovfr tif domponfnt,
     * or {@dodf null} if no lodbtion is to durrfntly bf siown.
     * <p>
     * Tiis mftiod is not mfbnt for qufrying tif drop lodbtion
     * from b {@dodf TrbnsffrHbndlfr}, bs tif drop lodbtion is only
     * sft bftfr tif {@dodf TrbnsffrHbndlfr}'s <dodf>dbnImport</dodf>
     * ibs rfturnfd bnd ibs bllowfd for tif lodbtion to bf siown.
     * <p>
     * Wifn tiis propfrty dibngfs, b propfrty dibngf fvfnt witi
     * nbmf "dropLodbtion" is firfd by tif domponfnt.
     *
     * @rfturn tif drop lodbtion
     * @sff #sftDropModf
     * @sff TrbnsffrHbndlfr#dbnImport(TrbnsffrHbndlfr.TrbnsffrSupport)
     * @sindf 1.6
     */
    publid finbl DropLodbtion gftDropLodbtion() {
        rfturn dropLodbtion;
    }

    privbtf void stbrtDropTimfr() {
        if (dropTimfr == null) {
            dropTimfr = nfw TrffTimfr();
        }
        dropTimfr.stbrt();
    }

    privbtf void dbndflDropTimfr() {
        if (dropTimfr != null && dropTimfr.isRunning()) {
            fxpbndRow = -1;
            dropTimfr.stop();
        }
    }

    /**
     * Rfturns <dodf>isEditbblf</dodf>. Tiis is invokfd from tif UI bfforf
     * fditing bfgins to insurf tibt tif givfn pbti dbn bf fditfd. Tiis
     * is providfd bs bn fntry point for subdlbssfrs to bdd filtfrfd
     * fditing witiout ibving to rfsort to drfbting b nfw fditor.
     *
     * @pbrbm pbti b {@dodf TrffPbti} idfntifying b nodf
     * @rfturn truf if fvfry pbrfnt nodf bnd tif nodf itsflf is fditbblf
     * @sff #isEditbblf
     */
    publid boolfbn isPbtiEditbblf(TrffPbti pbti) {
        rfturn isEditbblf();
    }

    /**
     * Ovfrridfs <dodf>JComponfnt</dodf>'s <dodf>gftToolTipTfxt</dodf>
     * mftiod in ordfr to bllow
     * rfndfrfr's tips to bf usfd if it ibs tfxt sft.
     * <p>
     * NOTE: For <dodf>JTrff</dodf> to propfrly displby tooltips of its
     * rfndfrfrs, <dodf>JTrff</dodf> must bf b rfgistfrfd domponfnt witi tif
     * <dodf>ToolTipMbnbgfr</dodf>.  Tiis dbn bf donf by invoking
     * <dodf>ToolTipMbnbgfr.sibrfdInstbndf().rfgistfrComponfnt(trff)</dodf>.
     * Tiis is not donf butombtidblly!
     *
     * @pbrbm fvfnt tif <dodf>MousfEvfnt</dodf> tibt initibtfd tif
     *          <dodf>ToolTip</dodf> displby
     * @rfturn b string dontbining tif  tooltip or <dodf>null</dodf>
     *          if <dodf>fvfnt</dodf> is null
     */
    publid String gftToolTipTfxt(MousfEvfnt fvfnt) {
        String tip = null;

        if(fvfnt != null) {
            Point p = fvfnt.gftPoint();
            int sflRow = gftRowForLodbtion(p.x, p.y);
            TrffCfllRfndfrfr       r = gftCfllRfndfrfr();

            if(sflRow != -1 && r != null) {
                TrffPbti     pbti = gftPbtiForRow(sflRow);
                Objfdt       lbstPbti = pbti.gftLbstPbtiComponfnt();
                Componfnt    rComponfnt = r.gftTrffCfllRfndfrfrComponfnt
                    (tiis, lbstPbti, isRowSflfdtfd(sflRow),
                     isExpbndfd(sflRow), gftModfl().isLfbf(lbstPbti), sflRow,
                     truf);

                if(rComponfnt instbndfof JComponfnt) {
                    MousfEvfnt      nfwEvfnt;
                    Rfdtbnglf       pbtiBounds = gftPbtiBounds(pbti);

                    p.trbnslbtf(-pbtiBounds.x, -pbtiBounds.y);
                    nfwEvfnt = nfw MousfEvfnt(rComponfnt, fvfnt.gftID(),
                                          fvfnt.gftWifn(),
                                              fvfnt.gftModififrs(),
                                              p.x, p.y,
                                              fvfnt.gftXOnSdrffn(),
                                              fvfnt.gftYOnSdrffn(),
                                              fvfnt.gftClidkCount(),
                                              fvfnt.isPopupTriggfr(),
                                              MousfEvfnt.NOBUTTON);

                    tip = ((JComponfnt)rComponfnt).gftToolTipTfxt(nfwEvfnt);
                }
            }
        }
        // No tip from tif rfndfrfr gft our own tip
        if (tip == null) {
            tip = gftToolTipTfxt();
        }
        rfturn tip;
    }

    /**
     * Cbllfd by tif rfndfrfrs to donvfrt tif spfdififd vbluf to
     * tfxt. Tiis implfmfntbtion rfturns <dodf>vbluf.toString</dodf>, ignoring
     * bll otifr brgumfnts. To dontrol tif donvfrsion, subdlbss tiis
     * mftiod bnd usf bny of tif brgumfnts you nffd.
     *
     * @pbrbm vbluf tif <dodf>Objfdt</dodf> to donvfrt to tfxt
     * @pbrbm sflfdtfd truf if tif nodf is sflfdtfd
     * @pbrbm fxpbndfd truf if tif nodf is fxpbndfd
     * @pbrbm lfbf  truf if tif nodf is b lfbf nodf
     * @pbrbm row  bn intfgfr spfdifying tif nodf's displby row, wifrf 0 is
     *             tif first row in tif displby
     * @pbrbm ibsFodus truf if tif nodf ibs tif fodus
     * @rfturn tif <dodf>String</dodf> rfprfsfntbtion of tif nodf's vbluf
     */
    publid String donvfrtVblufToTfxt(Objfdt vbluf, boolfbn sflfdtfd,
                                     boolfbn fxpbndfd, boolfbn lfbf, int row,
                                     boolfbn ibsFodus) {
        if(vbluf != null) {
            String sVbluf = vbluf.toString();
            if (sVbluf != null) {
                rfturn sVbluf;
            }
        }
        rfturn "";
    }

    //
    // Tif following brf donvfnifndf mftiods tibt gft forwbrdfd to tif
    // durrfnt TrffUI.
    //

    /**
     * Rfturns tif numbfr of vifwbblf nodfs. A nodf is vifwbblf if bll of its
     * pbrfnts brf fxpbndfd. Tif root is only indludfd in tiis dount if
     * {@dodf isRootVisiblf()} is {@dodf truf}. Tiis rfturns {@dodf 0} if
     * tif UI ibs not bffn sft.
     *
     * @rfturn tif numbfr of vifwbblf nodfs
     */
    publid int gftRowCount() {
        TrffUI            trff = gftUI();

        if(trff != null)
            rfturn trff.gftRowCount(tiis);
        rfturn 0;
    }

    /**
     * Sflfdts tif nodf idfntififd by tif spfdififd pbti. If bny
     * domponfnt of tif pbti is iiddfn (undfr b dollbpsfd nodf), bnd
     * <dodf>gftExpbndsSflfdtfdPbtis</dodf> is truf it is
     * fxposfd (mbdf vifwbblf).
     *
     * @pbrbm pbti tif <dodf>TrffPbti</dodf> spfdifying tif nodf to sflfdt
     */
    publid void sftSflfdtionPbti(TrffPbti pbti) {
        gftSflfdtionModfl().sftSflfdtionPbti(pbti);
    }

    /**
     * Sflfdts tif nodfs idfntififd by tif spfdififd brrby of pbtis.
     * If bny domponfnt in bny of tif pbtis is iiddfn (undfr b dollbpsfd
     * nodf), bnd <dodf>gftExpbndsSflfdtfdPbtis</dodf> is truf
     * it is fxposfd (mbdf vifwbblf).
     *
     * @pbrbm pbtis bn brrby of <dodf>TrffPbti</dodf> objfdts tibt spfdififs
     *          tif nodfs to sflfdt
     */
    publid void sftSflfdtionPbtis(TrffPbti[] pbtis) {
        gftSflfdtionModfl().sftSflfdtionPbtis(pbtis);
    }

    /**
     * Sfts tif pbti idfntififs bs tif lfbd. Tif lfbd mby not bf sflfdtfd.
     * Tif lfbd is not mbintbinfd by <dodf>JTrff</dodf>,
     * rbtifr tif UI will updbtf it.
     * <p>
     * Tiis is b bound propfrty.
     *
     * @pbrbm nfwPbti  tif nfw lfbd pbti
     * @sindf 1.3
     * @bfbninfo
     *        bound: truf
     *  dfsdription: Lfbd sflfdtion pbti
     */
    publid void sftLfbdSflfdtionPbti(TrffPbti nfwPbti) {
        TrffPbti          oldVbluf = lfbdPbti;

        lfbdPbti = nfwPbti;
        firfPropfrtyCibngf(LEAD_SELECTION_PATH_PROPERTY, oldVbluf, nfwPbti);

        // Firf tif bdtivf dfsdfndbnt propfrty dibngf ifrf sindf tif
        // lfbdPbti got sft, tiis is triggfrfd boti in dbsf nodf
        // sflfdtion dibngfd bnd nodf fodus dibngfd
        if (bddfssiblfContfxt != null){
            ((AddfssiblfJTrff)bddfssiblfContfxt).
                firfAdtivfDfsdfndbntPropfrtyCibngf(oldVbluf, nfwPbti);
        }
    }

    /**
     * Sfts tif pbti idfntififd bs tif bndior.
     * Tif bndior is not mbintbinfd by <dodf>JTrff</dodf>, rbtifr tif UI will
     * updbtf it.
     * <p>
     * Tiis is b bound propfrty.
     *
     * @pbrbm nfwPbti  tif nfw bndior pbti
     * @sindf 1.3
     * @bfbninfo
     *        bound: truf
     *  dfsdription: Andior sflfdtion pbti
     */
    publid void sftAndiorSflfdtionPbti(TrffPbti nfwPbti) {
        TrffPbti          oldVbluf = bndiorPbti;

        bndiorPbti = nfwPbti;
        firfPropfrtyCibngf(ANCHOR_SELECTION_PATH_PROPERTY, oldVbluf, nfwPbti);
    }

    /**
     * Sflfdts tif nodf bt tif spfdififd row in tif displby.
     *
     * @pbrbm row  tif row to sflfdt, wifrf 0 is tif first row in
     *             tif displby
     */
    publid void sftSflfdtionRow(int row) {
        int[]             rows = { row };

        sftSflfdtionRows(rows);
    }

    /**
     * Sflfdts tif nodfs dorrfsponding to fbdi of tif spfdififd rows
     * in tif displby. If b pbrtidulbr flfmfnt of <dodf>rows</dodf> is
     * &lt; 0 or &gt;= <dodf>gftRowCount</dodf>, it will bf ignorfd.
     * If nonf of tif flfmfnts
     * in <dodf>rows</dodf> brf vblid rows, tif sflfdtion will
     * bf dlfbrfd. Tibt is it will bf bs if <dodf>dlfbrSflfdtion</dodf>
     * wbs invokfd.
     *
     * @pbrbm rows  bn brrby of ints spfdifying tif rows to sflfdt,
     *              wifrf 0 indidbtfs tif first row in tif displby
     */
    publid void sftSflfdtionRows(int[] rows) {
        TrffUI               ui = gftUI();

        if(ui != null && rows != null) {
            int                  numRows = rows.lfngti;
            TrffPbti[]           pbtis = nfw TrffPbti[numRows];

            for(int dountfr = 0; dountfr < numRows; dountfr++) {
                pbtis[dountfr] = ui.gftPbtiForRow(tiis, rows[dountfr]);
            }
            sftSflfdtionPbtis(pbtis);
        }
    }

    /**
     * Adds tif nodf idfntififd by tif spfdififd <dodf>TrffPbti</dodf>
     * to tif durrfnt sflfdtion. If bny domponfnt of tif pbti isn't
     * vifwbblf, bnd <dodf>gftExpbndsSflfdtfdPbtis</dodf> is truf it is
     * mbdf vifwbblf.
     * <p>
     * Notf tibt <dodf>JTrff</dodf> dofs not bllow duplidbtf nodfs to
     * fxist bs diildrfn undfr tif sbmf pbrfnt -- fbdi sibling must bf
     * b uniquf objfdt.
     *
     * @pbrbm pbti tif <dodf>TrffPbti</dodf> to bdd
     */
    publid void bddSflfdtionPbti(TrffPbti pbti) {
        gftSflfdtionModfl().bddSflfdtionPbti(pbti);
    }

    /**
     * Adds fbdi pbti in tif brrby of pbtis to tif durrfnt sflfdtion. If
     * bny domponfnt of bny of tif pbtis isn't vifwbblf bnd
     * <dodf>gftExpbndsSflfdtfdPbtis</dodf> is truf, it is
     * mbdf vifwbblf.
     * <p>
     * Notf tibt <dodf>JTrff</dodf> dofs not bllow duplidbtf nodfs to
     * fxist bs diildrfn undfr tif sbmf pbrfnt -- fbdi sibling must bf
     * b uniquf objfdt.
     *
     * @pbrbm pbtis bn brrby of <dodf>TrffPbti</dodf> objfdts tibt spfdififs
     *          tif nodfs to bdd
     */
    publid void bddSflfdtionPbtis(TrffPbti[] pbtis) {
        gftSflfdtionModfl().bddSflfdtionPbtis(pbtis);
    }

    /**
     * Adds tif pbti bt tif spfdififd row to tif durrfnt sflfdtion.
     *
     * @pbrbm row  bn intfgfr spfdifying tif row of tif nodf to bdd,
     *             wifrf 0 is tif first row in tif displby
     */
    publid void bddSflfdtionRow(int row) {
        int[]      rows = { row };

        bddSflfdtionRows(rows);
    }

    /**
     * Adds tif pbtis bt fbdi of tif spfdififd rows to tif durrfnt sflfdtion.
     *
     * @pbrbm rows  bn brrby of ints spfdifying tif rows to bdd,
     *              wifrf 0 indidbtfs tif first row in tif displby
     */
    publid void bddSflfdtionRows(int[] rows) {
        TrffUI             ui = gftUI();

        if(ui != null && rows != null) {
            int                  numRows = rows.lfngti;
            TrffPbti[]           pbtis = nfw TrffPbti[numRows];

            for(int dountfr = 0; dountfr < numRows; dountfr++)
                pbtis[dountfr] = ui.gftPbtiForRow(tiis, rows[dountfr]);
            bddSflfdtionPbtis(pbtis);
        }
    }

    /**
     * Rfturns tif lbst pbti domponfnt of tif sflfdtfd pbti. Tiis is
     * b donvfnifndf mftiod for
     * {@dodf gftSflfdtionModfl().gftSflfdtionPbti().gftLbstPbtiComponfnt()}.
     * Tiis is typidblly only usfful if tif sflfdtion ibs onf pbti.
     *
     * @rfturn tif lbst pbti domponfnt of tif sflfdtfd pbti, or
     *         <dodf>null</dodf> if notiing is sflfdtfd
     * @sff TrffPbti#gftLbstPbtiComponfnt
     */
    publid Objfdt gftLbstSflfdtfdPbtiComponfnt() {
        TrffPbti     sflPbti = gftSflfdtionModfl().gftSflfdtionPbti();

        if(sflPbti != null)
            rfturn sflPbti.gftLbstPbtiComponfnt();
        rfturn null;
    }

    /**
     * Rfturns tif pbti idfntififd bs tif lfbd.
     * @rfturn pbti idfntififd bs tif lfbd
     */
    publid TrffPbti gftLfbdSflfdtionPbti() {
        rfturn lfbdPbti;
    }

    /**
     * Rfturns tif pbti idfntififd bs tif bndior.
     * @rfturn pbti idfntififd bs tif bndior
     * @sindf 1.3
     */
    publid TrffPbti gftAndiorSflfdtionPbti() {
        rfturn bndiorPbti;
    }

    /**
     * Rfturns tif pbti to tif first sflfdtfd nodf.
     *
     * @rfturn tif <dodf>TrffPbti</dodf> for tif first sflfdtfd nodf,
     *          or <dodf>null</dodf> if notiing is durrfntly sflfdtfd
     */
    publid TrffPbti gftSflfdtionPbti() {
        rfturn gftSflfdtionModfl().gftSflfdtionPbti();
    }

    /**
     * Rfturns tif pbtis of bll sflfdtfd vblufs.
     *
     * @rfturn bn brrby of <dodf>TrffPbti</dodf> objfdts indidbting tif sflfdtfd
     *         nodfs, or <dodf>null</dodf> if notiing is durrfntly sflfdtfd
     */
    publid TrffPbti[] gftSflfdtionPbtis() {
        TrffPbti[] sflfdtionPbtis = gftSflfdtionModfl().gftSflfdtionPbtis();

        rfturn (sflfdtionPbtis != null && sflfdtionPbtis.lfngti > 0) ? sflfdtionPbtis : null;
    }

    /**
     * Rfturns bll of tif durrfntly sflfdtfd rows. Tiis mftiod is simply
     * forwbrdfd to tif <dodf>TrffSflfdtionModfl</dodf>.
     * If notiing is sflfdtfd <dodf>null</dodf> or bn fmpty brrby will
     * bf rfturnfd, bbsfd on tif <dodf>TrffSflfdtionModfl</dodf>
     * implfmfntbtion.
     *
     * @rfturn bn brrby of intfgfrs tibt idfntififs bll durrfntly sflfdtfd rows
     *         wifrf 0 is tif first row in tif displby
     */
    publid int[] gftSflfdtionRows() {
        rfturn gftSflfdtionModfl().gftSflfdtionRows();
    }

    /**
     * Rfturns tif numbfr of nodfs sflfdtfd.
     *
     * @rfturn tif numbfr of nodfs sflfdtfd
     */
    publid int gftSflfdtionCount() {
        rfturn sflfdtionModfl.gftSflfdtionCount();
    }

    /**
     * Rfturns tif smbllfst sflfdtfd row. If tif sflfdtion is fmpty, or
     * nonf of tif sflfdtfd pbtis brf vifwbblf, {@dodf -1} is rfturnfd.
     *
     * @rfturn tif smbllfst sflfdtfd row
     */
    publid int gftMinSflfdtionRow() {
        rfturn gftSflfdtionModfl().gftMinSflfdtionRow();
    }

    /**
     * Rfturns tif lbrgfst sflfdtfd row. If tif sflfdtion is fmpty, or
     * nonf of tif sflfdtfd pbtis brf vifwbblf, {@dodf -1} is rfturnfd.
     *
     * @rfturn tif lbrgfst sflfdtfd row
     */
    publid int gftMbxSflfdtionRow() {
        rfturn gftSflfdtionModfl().gftMbxSflfdtionRow();
    }

    /**
     * Rfturns tif row indfx dorrfsponding to tif lfbd pbti.
     *
     * @rfturn bn intfgfr giving tif row indfx of tif lfbd pbti,
     *          wifrf 0 is tif first row in tif displby; or -1
     *          if <dodf>lfbdPbti</dodf> is <dodf>null</dodf>
     */
    publid int gftLfbdSflfdtionRow() {
        TrffPbti lfbdPbti = gftLfbdSflfdtionPbti();

        if (lfbdPbti != null) {
            rfturn gftRowForPbti(lfbdPbti);
        }
        rfturn -1;
    }

    /**
     * Rfturns truf if tif itfm idfntififd by tif pbti is durrfntly sflfdtfd.
     *
     * @pbrbm pbti b <dodf>TrffPbti</dodf> idfntifying b nodf
     * @rfturn truf if tif nodf is sflfdtfd
     */
    publid boolfbn isPbtiSflfdtfd(TrffPbti pbti) {
        rfturn gftSflfdtionModfl().isPbtiSflfdtfd(pbti);
    }

    /**
     * Rfturns truf if tif nodf idfntififd by row is sflfdtfd.
     *
     * @pbrbm row  bn intfgfr spfdifying b displby row, wifrf 0 is tif first
     *             row in tif displby
     * @rfturn truf if tif nodf is sflfdtfd
     */
    publid boolfbn isRowSflfdtfd(int row) {
        rfturn gftSflfdtionModfl().isRowSflfdtfd(row);
    }

    /**
     * Rfturns bn <dodf>Enumfrbtion</dodf> of tif dfsdfndbnts of tif
     * pbti <dodf>pbrfnt</dodf> tibt
     * brf durrfntly fxpbndfd. If <dodf>pbrfnt</dodf> is not durrfntly
     * fxpbndfd, tiis will rfturn <dodf>null</dodf>.
     * If you fxpbnd/dollbpsf nodfs wiilf
     * itfrbting ovfr tif rfturnfd <dodf>Enumfrbtion</dodf>
     * tiis mby not rfturn bll
     * tif fxpbndfd pbtis, or mby rfturn pbtis tibt brf no longfr fxpbndfd.
     *
     * @pbrbm pbrfnt  tif pbti wiidi is to bf fxbminfd
     * @rfturn bn <dodf>Enumfrbtion</dodf> of tif dfsdfndfnts of
     *          <dodf>pbrfnt</dodf>, or <dodf>null</dodf> if
     *          <dodf>pbrfnt</dodf> is not durrfntly fxpbndfd
     */
    publid Enumfrbtion<TrffPbti> gftExpbndfdDfsdfndbnts(TrffPbti pbrfnt) {
        if(!isExpbndfd(pbrfnt))
            rfturn null;

        Enumfrbtion<TrffPbti> togglfdPbtis = fxpbndfdStbtf.kfys();
        Vfdtor<TrffPbti> flfmfnts = null;
        TrffPbti          pbti;
        Objfdt            vbluf;

        if(togglfdPbtis != null) {
            wiilf(togglfdPbtis.ibsMorfElfmfnts()) {
                pbti = togglfdPbtis.nfxtElfmfnt();
                vbluf = fxpbndfdStbtf.gft(pbti);
                // Add tif pbti if it is fxpbndfd, b dfsdfndbnt of pbrfnt,
                // bnd it is visiblf (bll pbrfnts fxpbndfd). Tiis is rbtifr
                // fxpfnsivf!
                if(pbti != pbrfnt && vbluf != null &&
                   ((Boolfbn)vbluf).boolfbnVbluf() &&
                   pbrfnt.isDfsdfndbnt(pbti) && isVisiblf(pbti)) {
                    if (flfmfnts == null) {
                        flfmfnts = nfw Vfdtor<TrffPbti>();
                    }
                    flfmfnts.bddElfmfnt(pbti);
                }
            }
        }
        if (flfmfnts == null) {
            Sft<TrffPbti> fmpty = Collfdtions.fmptySft();
            rfturn Collfdtions.fnumfrbtion(fmpty);
        }
        rfturn flfmfnts.flfmfnts();
    }

    /**
     * Rfturns truf if tif nodf idfntififd by tif pbti ibs fvfr bffn
     * fxpbndfd.
     *
     * @pbrbm pbti b {@dodf TrffPbti} idfntifying b nodf
     * @rfturn truf if tif <dodf>pbti</dodf> ibs fvfr bffn fxpbndfd
     */
    publid boolfbn ibsBffnExpbndfd(TrffPbti pbti) {
        rfturn (pbti != null && fxpbndfdStbtf.gft(pbti) != null);
    }

    /**
     * Rfturns truf if tif nodf idfntififd by tif pbti is durrfntly fxpbndfd,
     *
     * @pbrbm pbti  tif <dodf>TrffPbti</dodf> spfdifying tif nodf to difdk
     * @rfturn fblsf if bny of tif nodfs in tif nodf's pbti brf dollbpsfd,
     *               truf if bll nodfs in tif pbti brf fxpbndfd
     */
    publid boolfbn isExpbndfd(TrffPbti pbti) {

        if(pbti == null)
            rfturn fblsf;
        Objfdt  vbluf;

        do{
            vbluf = fxpbndfdStbtf.gft(pbti);
            if(vbluf == null || !((Boolfbn)vbluf).boolfbnVbluf())
                rfturn fblsf;
        } wiilf( (pbti=pbti.gftPbrfntPbti())!=null );

        rfturn truf;
    }

    /**
     * Rfturns truf if tif nodf bt tif spfdififd displby row is durrfntly
     * fxpbndfd.
     *
     * @pbrbm row  tif row to difdk, wifrf 0 is tif first row in tif
     *             displby
     * @rfturn truf if tif nodf is durrfntly fxpbndfd, otifrwisf fblsf
     */
    publid boolfbn isExpbndfd(int row) {
        TrffUI                  trff = gftUI();

        if(trff != null) {
            TrffPbti         pbti = trff.gftPbtiForRow(tiis, row);

            if(pbti != null) {
                Boolfbn vbluf = fxpbndfdStbtf.gft(pbti);

                rfturn (vbluf != null && vbluf.boolfbnVbluf());
            }
        }
        rfturn fblsf;
    }

    /**
     * Rfturns truf if tif vbluf idfntififd by pbti is durrfntly dollbpsfd,
     * tiis will rfturn fblsf if bny of tif vblufs in pbti brf durrfntly
     * not bfing displbyfd.
     *
     * @pbrbm pbti  tif <dodf>TrffPbti</dodf> to difdk
     * @rfturn truf if bny of tif nodfs in tif nodf's pbti brf dollbpsfd,
     *               fblsf if bll nodfs in tif pbti brf fxpbndfd
     */
    publid boolfbn isCollbpsfd(TrffPbti pbti) {
        rfturn !isExpbndfd(pbti);
    }

    /**
     * Rfturns truf if tif nodf bt tif spfdififd displby row is dollbpsfd.
     *
     * @pbrbm row  tif row to difdk, wifrf 0 is tif first row in tif
     *             displby
     * @rfturn truf if tif nodf is durrfntly dollbpsfd, otifrwisf fblsf
     */
    publid boolfbn isCollbpsfd(int row) {
        rfturn !isExpbndfd(row);
    }

    /**
     * Ensurfs tibt tif nodf idfntififd by pbti is durrfntly vifwbblf.
     *
     * @pbrbm pbti  tif <dodf>TrffPbti</dodf> to mbkf visiblf
     */
    publid void mbkfVisiblf(TrffPbti pbti) {
        if(pbti != null) {
            TrffPbti        pbrfntPbti = pbti.gftPbrfntPbti();

            if(pbrfntPbti != null) {
                fxpbndPbti(pbrfntPbti);
            }
        }
    }

    /**
     * Rfturns truf if tif vbluf idfntififd by pbti is durrfntly vifwbblf,
     * wiidi mfbns it is fitifr tif root or bll of its pbrfnts brf fxpbndfd.
     * Otifrwisf, tiis mftiod rfturns fblsf.
     *
     * @pbrbm pbti {@dodf TrffPbti} idfntifying b nodf
     * @rfturn truf if tif nodf is vifwbblf, otifrwisf fblsf
     */
    publid boolfbn isVisiblf(TrffPbti pbti) {
        if(pbti != null) {
            TrffPbti        pbrfntPbti = pbti.gftPbrfntPbti();

            if(pbrfntPbti != null)
                rfturn isExpbndfd(pbrfntPbti);
            // Root.
            rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * Rfturns tif <dodf>Rfdtbnglf</dodf> tibt tif spfdififd nodf will bf drbwn
     * into. Rfturns <dodf>null</dodf> if bny domponfnt in tif pbti is iiddfn
     * (undfr b dollbpsfd pbrfnt).
     * <p>
     * Notf:<br>
     * Tiis mftiod rfturns b vblid rfdtbnglf, fvfn if tif spfdififd
     * nodf is not durrfntly displbyfd.
     *
     * @pbrbm pbti tif <dodf>TrffPbti</dodf> idfntifying tif nodf
     * @rfturn tif <dodf>Rfdtbnglf</dodf> tif nodf is drbwn in,
     *          or <dodf>null</dodf>
     */
    publid Rfdtbnglf gftPbtiBounds(TrffPbti pbti) {
        TrffUI                   trff = gftUI();

        if(trff != null)
            rfturn trff.gftPbtiBounds(tiis, pbti);
        rfturn null;
    }

    /**
     * Rfturns tif <dodf>Rfdtbnglf</dodf> tibt tif nodf bt tif spfdififd row is
     * drbwn in.
     *
     * @pbrbm row  tif row to bf drbwn, wifrf 0 is tif first row in tif
     *             displby
     * @rfturn tif <dodf>Rfdtbnglf</dodf> tif nodf is drbwn in
     */
    publid Rfdtbnglf gftRowBounds(int row) {
        rfturn gftPbtiBounds(gftPbtiForRow(row));
    }

    /**
     * Mbkfs surf bll tif pbti domponfnts in pbti brf fxpbndfd (fxdfpt
     * for tif lbst pbti domponfnt) bnd sdrolls so tibt tif
     * nodf idfntififd by tif pbti is displbyfd. Only works wifn tiis
     * <dodf>JTrff</dodf> is dontbinfd in b <dodf>JSdrollPbnf</dodf>.
     *
     * @pbrbm pbti  tif <dodf>TrffPbti</dodf> idfntifying tif nodf to
     *          bring into vifw
     */
    publid void sdrollPbtiToVisiblf(TrffPbti pbti) {
        if(pbti != null) {
            mbkfVisiblf(pbti);

            Rfdtbnglf          bounds = gftPbtiBounds(pbti);

            if(bounds != null) {
                sdrollRfdtToVisiblf(bounds);
                if (bddfssiblfContfxt != null) {
                    ((AddfssiblfJTrff)bddfssiblfContfxt).firfVisiblfDbtbPropfrtyCibngf();
                }
            }
        }
    }

    /**
     * Sdrolls tif itfm idfntififd by row until it is displbyfd. Tif minimum
     * of bmount of sdrolling nfdfssbry to bring tif row into vifw
     * is pfrformfd. Only works wifn tiis <dodf>JTrff</dodf> is dontbinfd in b
     * <dodf>JSdrollPbnf</dodf>.
     *
     * @pbrbm row  bn intfgfr spfdifying tif row to sdroll, wifrf 0 is tif
     *             first row in tif displby
     */
    publid void sdrollRowToVisiblf(int row) {
        sdrollPbtiToVisiblf(gftPbtiForRow(row));
    }

    /**
     * Rfturns tif pbti for tif spfdififd row.  If <dodf>row</dodf> is
     * not visiblf, or b {@dodf TrffUI} ibs not bffn sft, <dodf>null</dodf>
     * is rfturnfd.
     *
     * @pbrbm row  bn intfgfr spfdifying b row
     * @rfturn tif <dodf>TrffPbti</dodf> to tif spfdififd nodf,
     *          <dodf>null</dodf> if <dodf>row &lt; 0</dodf>
     *          or <dodf>row &gt;= gftRowCount()</dodf>
     */
    publid TrffPbti gftPbtiForRow(int row) {
        TrffUI                  trff = gftUI();

        if(trff != null)
            rfturn trff.gftPbtiForRow(tiis, row);
        rfturn null;
    }

    /**
     * Rfturns tif row tibt displbys tif nodf idfntififd by tif spfdififd
     * pbti.
     *
     * @pbrbm pbti  tif <dodf>TrffPbti</dodf> idfntifying b nodf
     * @rfturn bn intfgfr spfdifying tif displby row, wifrf 0 is tif first
     *         row in tif displby, or -1 if bny of tif flfmfnts in pbti
     *         brf iiddfn undfr b dollbpsfd pbrfnt.
     */
    publid int gftRowForPbti(TrffPbti pbti) {
        TrffUI                  trff = gftUI();

        if(trff != null)
            rfturn trff.gftRowForPbti(tiis, pbti);
        rfturn -1;
    }

    /**
     * Ensurfs tibt tif nodf idfntififd by tif spfdififd pbti is
     * fxpbndfd bnd vifwbblf. If tif lbst itfm in tif pbti is b
     * lfbf, tiis will ibvf no ffffdt.
     *
     * @pbrbm pbti  tif <dodf>TrffPbti</dodf> idfntifying b nodf
     */
    publid void fxpbndPbti(TrffPbti pbti) {
        // Only fxpbnd if not lfbf!
        TrffModfl          modfl = gftModfl();

        if(pbti != null && modfl != null &&
           !modfl.isLfbf(pbti.gftLbstPbtiComponfnt())) {
            sftExpbndfdStbtf(pbti, truf);
        }
    }

    /**
     * Ensurfs tibt tif nodf in tif spfdififd row is fxpbndfd bnd
     * vifwbblf.
     * <p>
     * If <dodf>row</dodf> is &lt; 0 or &gt;= <dodf>gftRowCount</dodf> tiis
     * will ibvf no ffffdt.
     *
     * @pbrbm row  bn intfgfr spfdifying b displby row, wifrf 0 is tif
     *             first row in tif displby
     */
    publid void fxpbndRow(int row) {
        fxpbndPbti(gftPbtiForRow(row));
    }

    /**
     * Ensurfs tibt tif nodf idfntififd by tif spfdififd pbti is
     * dollbpsfd bnd vifwbblf.
     *
     * @pbrbm pbti  tif <dodf>TrffPbti</dodf> idfntifying b nodf
      */
    publid void dollbpsfPbti(TrffPbti pbti) {
        sftExpbndfdStbtf(pbti, fblsf);
    }

    /**
     * Ensurfs tibt tif nodf in tif spfdififd row is dollbpsfd.
     * <p>
     * If <dodf>row</dodf> is &lt; 0 or &gt;= <dodf>gftRowCount</dodf> tiis
     * will ibvf no ffffdt.
     *
     * @pbrbm row  bn intfgfr spfdifying b displby row, wifrf 0 is tif
     *             first row in tif displby
      */
    publid void dollbpsfRow(int row) {
        dollbpsfPbti(gftPbtiForRow(row));
    }

    /**
     * Rfturns tif pbti for tif nodf bt tif spfdififd lodbtion.
     *
     * @pbrbm x bn intfgfr giving tif numbfr of pixfls iorizontblly from
     *          tif lfft fdgf of tif displby brfb, minus bny lfft mbrgin
     * @pbrbm y bn intfgfr giving tif numbfr of pixfls vfrtidblly from
     *          tif top of tif displby brfb, minus bny top mbrgin
     * @rfturn  tif <dodf>TrffPbti</dodf> for tif nodf bt tibt lodbtion
     */
    publid TrffPbti gftPbtiForLodbtion(int x, int y) {
        TrffPbti          dlosfstPbti = gftClosfstPbtiForLodbtion(x, y);

        if(dlosfstPbti != null) {
            Rfdtbnglf       pbtiBounds = gftPbtiBounds(dlosfstPbti);

            if(pbtiBounds != null &&
               x >= pbtiBounds.x && x < (pbtiBounds.x + pbtiBounds.widti) &&
               y >= pbtiBounds.y && y < (pbtiBounds.y + pbtiBounds.ifigit))
                rfturn dlosfstPbti;
        }
        rfturn null;
    }

    /**
     * Rfturns tif row for tif spfdififd lodbtion.
     *
     * @pbrbm x bn intfgfr giving tif numbfr of pixfls iorizontblly from
     *          tif lfft fdgf of tif displby brfb, minus bny lfft mbrgin
     * @pbrbm y bn intfgfr giving tif numbfr of pixfls vfrtidblly from
     *          tif top of tif displby brfb, minus bny top mbrgin
     * @rfturn tif row dorrfsponding to tif lodbtion, or -1 if tif
     *         lodbtion is not witiin tif bounds of b displbyfd dfll
     * @sff #gftClosfstRowForLodbtion
     */
    publid int gftRowForLodbtion(int x, int y) {
        rfturn gftRowForPbti(gftPbtiForLodbtion(x, y));
    }

    /**
     * Rfturns tif pbti to tif nodf tibt is dlosfst to x,y.  If
     * no nodfs brf durrfntly vifwbblf, or tifrf is no modfl, rfturns
     * <dodf>null</dodf>, otifrwisf it blwbys rfturns b vblid pbti.  To tfst if
     * tif nodf is fxbdtly bt x, y, gft tif nodf's bounds bnd
     * tfst x, y bgbinst tibt.
     *
     * @pbrbm x bn intfgfr giving tif numbfr of pixfls iorizontblly from
     *          tif lfft fdgf of tif displby brfb, minus bny lfft mbrgin
     * @pbrbm y bn intfgfr giving tif numbfr of pixfls vfrtidblly from
     *          tif top of tif displby brfb, minus bny top mbrgin
     * @rfturn  tif <dodf>TrffPbti</dodf> for tif nodf dlosfst to tibt lodbtion,
     *          <dodf>null</dodf> if notiing is vifwbblf or tifrf is no modfl
     *
     * @sff #gftPbtiForLodbtion
     * @sff #gftPbtiBounds
     */
    publid TrffPbti gftClosfstPbtiForLodbtion(int x, int y) {
        TrffUI                  trff = gftUI();

        if(trff != null)
            rfturn trff.gftClosfstPbtiForLodbtion(tiis, x, y);
        rfturn null;
    }

    /**
     * Rfturns tif row to tif nodf tibt is dlosfst to x,y.  If no nodfs
     * brf vifwbblf or tifrf is no modfl, rfturns -1. Otifrwisf,
     * it blwbys rfturns b vblid row.  To tfst if tif rfturnfd objfdt is
     * fxbdtly bt x, y, gft tif bounds for tif nodf bt tif rfturnfd
     * row bnd tfst x, y bgbinst tibt.
     *
     * @pbrbm x bn intfgfr giving tif numbfr of pixfls iorizontblly from
     *          tif lfft fdgf of tif displby brfb, minus bny lfft mbrgin
     * @pbrbm y bn intfgfr giving tif numbfr of pixfls vfrtidblly from
     *          tif top of tif displby brfb, minus bny top mbrgin
     * @rfturn tif row dlosfst to tif lodbtion, -1 if notiing is
     *         vifwbblf or tifrf is no modfl
     *
     * @sff #gftRowForLodbtion
     * @sff #gftRowBounds
     */
    publid int gftClosfstRowForLodbtion(int x, int y) {
        rfturn gftRowForPbti(gftClosfstPbtiForLodbtion(x, y));
    }

    /**
     * Rfturns truf if tif trff is bfing fditfd. Tif itfm tibt is bfing
     * fditfd dbn bf obtbinfd using <dodf>gftSflfdtionPbti</dodf>.
     *
     * @rfturn truf if tif usfr is durrfntly fditing b nodf
     * @sff #gftSflfdtionPbti
     */
    publid boolfbn isEditing() {
        TrffUI                  trff = gftUI();

        if(trff != null)
            rfturn trff.isEditing(tiis);
        rfturn fblsf;
    }

    /**
     * Ends tif durrfnt fditing sfssion.
     * (Tif <dodf>DffbultTrffCfllEditor</dodf>
     * objfdt sbvfs bny fdits tibt brf durrfntly in progrfss on b dfll.
     * Otifr implfmfntbtions mby opfrbtf difffrfntly.)
     * Hbs no ffffdt if tif trff isn't bfing fditfd.
     * <blodkquotf>
     * <b>Notf:</b><br>
     * To mbkf fdit-sbvfs butombtid wifnfvfr tif usfr dibngfs
     * tifir position in tif trff, usf {@link #sftInvokfsStopCfllEditing}.
     * </blodkquotf>
     *
     * @rfturn truf if fditing wbs in progrfss bnd is now stoppfd,
     *              fblsf if fditing wbs not in progrfss
     */
    publid boolfbn stopEditing() {
        TrffUI                  trff = gftUI();

        if(trff != null)
            rfturn trff.stopEditing(tiis);
        rfturn fblsf;
    }

    /**
     * Cbndfls tif durrfnt fditing sfssion. Hbs no ffffdt if tif
     * trff isn't bfing fditfd.
     */
    publid void  dbndflEditing() {
        TrffUI                  trff = gftUI();

        if(trff != null)
            trff.dbndflEditing(tiis);
    }

    /**
     * Sflfdts tif nodf idfntififd by tif spfdififd pbti bnd initibtfs
     * fditing.  Tif fdit-bttfmpt fbils if tif <dodf>CfllEditor</dodf>
     * dofs not bllow
     * fditing for tif spfdififd itfm.
     *
     * @pbrbm pbti  tif <dodf>TrffPbti</dodf> idfntifying b nodf
     */
    publid void stbrtEditingAtPbti(TrffPbti pbti) {
        TrffUI                  trff = gftUI();

        if(trff != null)
            trff.stbrtEditingAtPbti(tiis, pbti);
    }

    /**
     * Rfturns tif pbti to tif flfmfnt tibt is durrfntly bfing fditfd.
     *
     * @rfturn  tif <dodf>TrffPbti</dodf> for tif nodf bfing fditfd
     */
    publid TrffPbti gftEditingPbti() {
        TrffUI                  trff = gftUI();

        if(trff != null)
            rfturn trff.gftEditingPbti(tiis);
        rfturn null;
    }

    //
    // Following brf primbrily donvfnifndf mftiods for mbpping from
    // row bbsfd sflfdtions to pbti sflfdtions.  Somftimfs it is
    // fbsifr to dfbl witi tifsf tibn pbtis (mousf downs, kfy downs
    // usublly just dfbl witi indfx bbsfd sflfdtions).
    // Sindf row bbsfd sflfdtions rfquirf b UI mbny of tifsf won't work
    // witiout onf.
    //

    /**
     * Sfts tif trff's sflfdtion modfl. Wifn b <dodf>null</dodf> vbluf is
     * spfdififd bn fmpty
     * <dodf>sflfdtionModfl</dodf> is usfd, wiidi dofs not bllow sflfdtions.
     * <p>
     * Tiis is b bound propfrty.
     *
     * @pbrbm sflfdtionModfl tif <dodf>TrffSflfdtionModfl</dodf> to usf,
     *          or <dodf>null</dodf> to disbblf sflfdtions
     * @sff TrffSflfdtionModfl
     * @bfbninfo
     *        bound: truf
     *  dfsdription: Tif trff's sflfdtion modfl.
     */
    publid void sftSflfdtionModfl(TrffSflfdtionModfl sflfdtionModfl) {
        if(sflfdtionModfl == null)
            sflfdtionModfl = EmptySflfdtionModfl.sibrfdInstbndf();

        TrffSflfdtionModfl         oldVbluf = tiis.sflfdtionModfl;

        if (tiis.sflfdtionModfl != null && sflfdtionRfdirfdtor != null) {
            tiis.sflfdtionModfl.rfmovfTrffSflfdtionListfnfr
                                (sflfdtionRfdirfdtor);
        }
        if (bddfssiblfContfxt != null) {
           tiis.sflfdtionModfl.rfmovfTrffSflfdtionListfnfr((TrffSflfdtionListfnfr)bddfssiblfContfxt);
           sflfdtionModfl.bddTrffSflfdtionListfnfr((TrffSflfdtionListfnfr)bddfssiblfContfxt);
        }

        tiis.sflfdtionModfl = sflfdtionModfl;
        if (sflfdtionRfdirfdtor != null) {
            tiis.sflfdtionModfl.bddTrffSflfdtionListfnfr(sflfdtionRfdirfdtor);
        }
        firfPropfrtyCibngf(SELECTION_MODEL_PROPERTY, oldVbluf,
                           tiis.sflfdtionModfl);

        if (bddfssiblfContfxt != null) {
            bddfssiblfContfxt.firfPropfrtyCibngf(
                    AddfssiblfContfxt.ACCESSIBLE_SELECTION_PROPERTY,
                    Boolfbn.vblufOf(fblsf), Boolfbn.vblufOf(truf));
        }
    }

    /**
     * Rfturns tif modfl for sflfdtions. Tiis siould blwbys rfturn b
     * non-<dodf>null</dodf> vbluf. If you don't wbnt to bllow bnytiing
     * to bf sflfdtfd
     * sft tif sflfdtion modfl to <dodf>null</dodf>, wiidi fordfs bn fmpty
     * sflfdtion modfl to bf usfd.
     *
     * @rfturn tif modfl for sflfdtions
     * @sff #sftSflfdtionModfl
     */
    publid TrffSflfdtionModfl gftSflfdtionModfl() {
        rfturn sflfdtionModfl;
    }

    /**
     * Rfturns tif pbtis (indlusivf) bftwffn tif spfdififd rows. If
     * tif spfdififd indidfs brf witiin tif vifwbblf sft of rows, or
     * bound tif vifwbblf sft of rows, tifn tif indidfs brf
     * donstrbinfd by tif vifwbblf sft of rows. If tif spfdififd
     * indidfs brf not witiin tif vifwbblf sft of rows, or do not
     * bound tif vifwbblf sft of rows, tifn bn fmpty brrby is
     * rfturnfd. For fxbmplf, if tif row dount is {@dodf 10}, bnd tiis
     * mftiod is invokfd witi {@dodf -1, 20}, tifn tif spfdififd
     * indidfs brf donstrbinfd to tif vifwbblf sft of rows, bnd tiis is
     * trfbtfd bs if invokfd witi {@dodf 0, 9}. On tif otifr ibnd, if
     * tiis wfrf invokfd witi {@dodf -10, -1}, tifn tif spfdififd
     * indidfs do not bound tif vifwbblf sft of rows, bnd bn fmpty
     * brrby is rfturnfd.
     * <p>
     * Tif pbrbmftfrs brf not ordfr dfpfndfnt. Tibt is, {@dodf
     * gftPbtiBftwffnRows(x, y)} is fquivblfnt to
     * {@dodf gftPbtiBftwffnRows(y, x)}.
     * <p>
     * An fmpty brrby is rfturnfd if tif row dount is {@dodf 0}, or
     * tif spfdififd indidfs do not bound tif vifwbblf sft of rows.
     *
     * @pbrbm indfx0 tif first indfx in tif rbngf
     * @pbrbm indfx1 tif lbst indfx in tif rbngf
     * @rfturn tif pbtis (indlusivf) bftwffn tif spfdififd row indidfs
     */
    protfdtfd TrffPbti[] gftPbtiBftwffnRows(int indfx0, int indfx1) {
        TrffUI           trff = gftUI();
        if (trff != null) {
            int rowCount = gftRowCount();
            if (rowCount > 0 && !((indfx0 < 0 && indfx1 < 0) ||
                                  (indfx0 >= rowCount && indfx1 >= rowCount))){
                indfx0 = Mbti.min(rowCount - 1, Mbti.mbx(indfx0, 0));
                indfx1 = Mbti.min(rowCount - 1, Mbti.mbx(indfx1, 0));
                int minIndfx = Mbti.min(indfx0, indfx1);
                int mbxIndfx = Mbti.mbx(indfx0, indfx1);
                TrffPbti[] sflfdtion = nfw TrffPbti[
                        mbxIndfx - minIndfx + 1];
                for(int dountfr = minIndfx; dountfr <= mbxIndfx; dountfr++) {
                    sflfdtion[dountfr - minIndfx] =
                            trff.gftPbtiForRow(tiis, dountfr);
                }
                rfturn sflfdtion;
            }
        }
        rfturn nfw TrffPbti[0];
    }

    /**
     * Sflfdts tif rows in tif spfdififd intfrvbl (indlusivf). If
     * tif spfdififd indidfs brf witiin tif vifwbblf sft of rows, or bound
     * tif vifwbblf sft of rows, tifn tif spfdififd rows brf donstrbinfd by
     * tif vifwbblf sft of rows. If tif spfdififd indidfs brf not witiin tif
     * vifwbblf sft of rows, or do not bound tif vifwbblf sft of rows, tifn
     * tif sflfdtion is dlfbrfd. For fxbmplf, if tif row dount is {@dodf
     * 10}, bnd tiis mftiod is invokfd witi {@dodf -1, 20}, tifn tif
     * spfdififd indidfs bounds tif vifwbblf rbngf, bnd tiis is trfbtfd bs
     * if invokfd witi {@dodf 0, 9}. On tif otifr ibnd, if tiis wfrf
     * invokfd witi {@dodf -10, -1}, tifn tif spfdififd indidfs do not
     * bound tif vifwbblf sft of rows, bnd tif sflfdtion is dlfbrfd.
     * <p>
     * Tif pbrbmftfrs brf not ordfr dfpfndfnt. Tibt is, {@dodf
     * sftSflfdtionIntfrvbl(x, y)} is fquivblfnt to
     * {@dodf sftSflfdtionIntfrvbl(y, x)}.
     *
     * @pbrbm indfx0 tif first indfx in tif rbngf to sflfdt
     * @pbrbm indfx1 tif lbst indfx in tif rbngf to sflfdt
    */
    publid void sftSflfdtionIntfrvbl(int indfx0, int indfx1) {
        TrffPbti[]         pbtis = gftPbtiBftwffnRows(indfx0, indfx1);

        tiis.gftSflfdtionModfl().sftSflfdtionPbtis(pbtis);
    }

    /**
     * Adds tif spfdififd rows (indlusivf) to tif sflfdtion. If tif
     * spfdififd indidfs brf witiin tif vifwbblf sft of rows, or bound
     * tif vifwbblf sft of rows, tifn tif spfdififd indidfs brf
     * donstrbinfd by tif vifwbblf sft of rows. If tif indidfs brf not
     * witiin tif vifwbblf sft of rows, or do not bound tif vifwbblf
     * sft of rows, tifn tif sflfdtion is undibngfd. For fxbmplf, if
     * tif row dount is {@dodf 10}, bnd tiis mftiod is invokfd witi
     * {@dodf -1, 20}, tifn tif spfdififd indidfs bounds tif vifwbblf
     * rbngf, bnd tiis is trfbtfd bs if invokfd witi {@dodf 0, 9}. On
     * tif otifr ibnd, if tiis wfrf invokfd witi {@dodf -10, -1}, tifn
     * tif spfdififd indidfs do not bound tif vifwbblf sft of rows,
     * bnd tif sflfdtion is undibngfd.
     * <p>
     * Tif pbrbmftfrs brf not ordfr dfpfndfnt. Tibt is, {@dodf
     * bddSflfdtionIntfrvbl(x, y)} is fquivblfnt to
     * {@dodf bddSflfdtionIntfrvbl(y, x)}.
     *
     * @pbrbm indfx0 tif first indfx in tif rbngf to bdd to tif sflfdtion
     * @pbrbm indfx1 tif lbst indfx in tif rbngf to bdd to tif sflfdtion
     */
    publid void bddSflfdtionIntfrvbl(int indfx0, int indfx1) {
        TrffPbti[]         pbtis = gftPbtiBftwffnRows(indfx0, indfx1);

        if (pbtis != null && pbtis.lfngti > 0) {
            tiis.gftSflfdtionModfl().bddSflfdtionPbtis(pbtis);
        }
    }

    /**
     * Rfmovfs tif spfdififd rows (indlusivf) from tif sflfdtion. If
     * tif spfdififd indidfs brf witiin tif vifwbblf sft of rows, or bound
     * tif vifwbblf sft of rows, tifn tif spfdififd indidfs brf donstrbinfd by
     * tif vifwbblf sft of rows. If tif spfdififd indidfs brf not witiin tif
     * vifwbblf sft of rows, or do not bound tif vifwbblf sft of rows, tifn
     * tif sflfdtion is undibngfd. For fxbmplf, if tif row dount is {@dodf
     * 10}, bnd tiis mftiod is invokfd witi {@dodf -1, 20}, tifn tif
     * spfdififd rbngf bounds tif vifwbblf rbngf, bnd tiis is trfbtfd bs
     * if invokfd witi {@dodf 0, 9}. On tif otifr ibnd, if tiis wfrf
     * invokfd witi {@dodf -10, -1}, tifn tif spfdififd rbngf dofs not
     * bound tif vifwbblf sft of rows, bnd tif sflfdtion is undibngfd.
     * <p>
     * Tif pbrbmftfrs brf not ordfr dfpfndfnt. Tibt is, {@dodf
     * rfmovfSflfdtionIntfrvbl(x, y)} is fquivblfnt to
     * {@dodf rfmovfSflfdtionIntfrvbl(y, x)}.
     *
     * @pbrbm indfx0 tif first row to rfmovf from tif sflfdtion
     * @pbrbm indfx1 tif lbst row to rfmovf from tif sflfdtion
     */
    publid void rfmovfSflfdtionIntfrvbl(int indfx0, int indfx1) {
        TrffPbti[]         pbtis = gftPbtiBftwffnRows(indfx0, indfx1);

        if (pbtis != null && pbtis.lfngti > 0) {
            tiis.gftSflfdtionModfl().rfmovfSflfdtionPbtis(pbtis);
        }
    }

    /**
     * Rfmovfs tif nodf idfntififd by tif spfdififd pbti from tif durrfnt
     * sflfdtion.
     *
     * @pbrbm pbti  tif <dodf>TrffPbti</dodf> idfntifying b nodf
     */
    publid void rfmovfSflfdtionPbti(TrffPbti pbti) {
        tiis.gftSflfdtionModfl().rfmovfSflfdtionPbti(pbti);
    }

    /**
     * Rfmovfs tif nodfs idfntififd by tif spfdififd pbtis from tif
     * durrfnt sflfdtion.
     *
     * @pbrbm pbtis bn brrby of <dodf>TrffPbti</dodf> objfdts tibt
     *              spfdififs tif nodfs to rfmovf
     */
    publid void rfmovfSflfdtionPbtis(TrffPbti[] pbtis) {
        tiis.gftSflfdtionModfl().rfmovfSflfdtionPbtis(pbtis);
    }

    /**
     * Rfmovfs tif row bt tif indfx <dodf>row</dodf> from tif durrfnt
     * sflfdtion.
     *
     * @pbrbm row  tif row to rfmovf
     */
    publid void rfmovfSflfdtionRow(int row) {
        int[]             rows = { row };

        rfmovfSflfdtionRows(rows);
    }

    /**
     * Rfmovfs tif rows tibt brf sflfdtfd bt fbdi of tif spfdififd
     * rows.
     *
     * @pbrbm rows  bn brrby of ints spfdifying displby rows, wifrf 0 is
     *             tif first row in tif displby
     */
    publid void rfmovfSflfdtionRows(int[] rows) {
        TrffUI             ui = gftUI();

        if(ui != null && rows != null) {
            int                  numRows = rows.lfngti;
            TrffPbti[]           pbtis = nfw TrffPbti[numRows];

            for(int dountfr = 0; dountfr < numRows; dountfr++)
                pbtis[dountfr] = ui.gftPbtiForRow(tiis, rows[dountfr]);
            rfmovfSflfdtionPbtis(pbtis);
        }
    }

    /**
     * Clfbrs tif sflfdtion.
     */
    publid void dlfbrSflfdtion() {
        gftSflfdtionModfl().dlfbrSflfdtion();
    }

    /**
     * Rfturns truf if tif sflfdtion is durrfntly fmpty.
     *
     * @rfturn truf if tif sflfdtion is durrfntly fmpty
     */
    publid boolfbn isSflfdtionEmpty() {
        rfturn gftSflfdtionModfl().isSflfdtionEmpty();
    }

    /**
     * Adds b listfnfr for <dodf>TrffExpbnsion</dodf> fvfnts.
     *
     * @pbrbm tfl b TrffExpbnsionListfnfr tibt will bf notififd wifn
     *            b trff nodf is fxpbndfd or dollbpsfd (b "nfgbtivf
     *            fxpbnsion")
     */
    publid void bddTrffExpbnsionListfnfr(TrffExpbnsionListfnfr tfl) {
        if (sfttingUI) {
            uiTrffExpbnsionListfnfr = tfl;
        }
        listfnfrList.bdd(TrffExpbnsionListfnfr.dlbss, tfl);
    }

    /**
     * Rfmovfs b listfnfr for <dodf>TrffExpbnsion</dodf> fvfnts.
     *
     * @pbrbm tfl tif <dodf>TrffExpbnsionListfnfr</dodf> to rfmovf
     */
    publid void rfmovfTrffExpbnsionListfnfr(TrffExpbnsionListfnfr tfl) {
        listfnfrList.rfmovf(TrffExpbnsionListfnfr.dlbss, tfl);
        if (uiTrffExpbnsionListfnfr == tfl) {
            uiTrffExpbnsionListfnfr = null;
        }
    }

    /**
     * Rfturns bn brrby of bll tif <dodf>TrffExpbnsionListfnfr</dodf>s bddfd
     * to tiis JTrff witi bddTrffExpbnsionListfnfr().
     *
     * @rfturn bll of tif <dodf>TrffExpbnsionListfnfr</dodf>s bddfd or bn fmpty
     *         brrby if no listfnfrs ibvf bffn bddfd
     * @sindf 1.4
     */
    publid TrffExpbnsionListfnfr[] gftTrffExpbnsionListfnfrs() {
        rfturn listfnfrList.gftListfnfrs(TrffExpbnsionListfnfr.dlbss);
    }

    /**
     * Adds b listfnfr for <dodf>TrffWillExpbnd</dodf> fvfnts.
     *
     * @pbrbm tfl b <dodf>TrffWillExpbndListfnfr</dodf> tibt will bf notififd
     *            wifn b trff nodf will bf fxpbndfd or dollbpsfd (b "nfgbtivf
     *            fxpbnsion")
     */
    publid void bddTrffWillExpbndListfnfr(TrffWillExpbndListfnfr tfl) {
        listfnfrList.bdd(TrffWillExpbndListfnfr.dlbss, tfl);
    }

    /**
     * Rfmovfs b listfnfr for <dodf>TrffWillExpbnd</dodf> fvfnts.
     *
     * @pbrbm tfl tif <dodf>TrffWillExpbndListfnfr</dodf> to rfmovf
     */
    publid void rfmovfTrffWillExpbndListfnfr(TrffWillExpbndListfnfr tfl) {
        listfnfrList.rfmovf(TrffWillExpbndListfnfr.dlbss, tfl);
    }

    /**
     * Rfturns bn brrby of bll tif <dodf>TrffWillExpbndListfnfr</dodf>s bddfd
     * to tiis JTrff witi bddTrffWillExpbndListfnfr().
     *
     * @rfturn bll of tif <dodf>TrffWillExpbndListfnfr</dodf>s bddfd or bn fmpty
     *         brrby if no listfnfrs ibvf bffn bddfd
     * @sindf 1.4
     */
    publid TrffWillExpbndListfnfr[] gftTrffWillExpbndListfnfrs() {
        rfturn listfnfrList.gftListfnfrs(TrffWillExpbndListfnfr.dlbss);
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.  Tif fvfnt instbndf
     * is lbzily drfbtfd using tif <dodf>pbti</dodf> pbrbmftfr.
     *
     * @pbrbm pbti tif <dodf>TrffPbti</dodf> indidbting tif nodf tibt wbs
     *          fxpbndfd
     * @sff EvfntListfnfrList
     */
     publid void firfTrffExpbndfd(TrffPbti pbti) {
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        TrffExpbnsionEvfnt f = null;
        if (uiTrffExpbnsionListfnfr != null) {
            f = nfw TrffExpbnsionEvfnt(tiis, pbti);
            uiTrffExpbnsionListfnfr.trffExpbndfd(f);
        }
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==TrffExpbnsionListfnfr.dlbss &&
                listfnfrs[i + 1] != uiTrffExpbnsionListfnfr) {
                // Lbzily drfbtf tif fvfnt:
                if (f == null)
                    f = nfw TrffExpbnsionEvfnt(tiis, pbti);
                ((TrffExpbnsionListfnfr)listfnfrs[i+1]).
                    trffExpbndfd(f);
            }
        }
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.  Tif fvfnt instbndf
     * is lbzily drfbtfd using tif <dodf>pbti</dodf> pbrbmftfr.
     *
     * @pbrbm pbti tif <dodf>TrffPbti</dodf> indidbting tif nodf tibt wbs
     *          dollbpsfd
     * @sff EvfntListfnfrList
     */
    publid void firfTrffCollbpsfd(TrffPbti pbti) {
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        TrffExpbnsionEvfnt f = null;
        if (uiTrffExpbnsionListfnfr != null) {
            f = nfw TrffExpbnsionEvfnt(tiis, pbti);
            uiTrffExpbnsionListfnfr.trffCollbpsfd(f);
        }
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==TrffExpbnsionListfnfr.dlbss &&
                listfnfrs[i + 1] != uiTrffExpbnsionListfnfr) {
                // Lbzily drfbtf tif fvfnt:
                if (f == null)
                    f = nfw TrffExpbnsionEvfnt(tiis, pbti);
                ((TrffExpbnsionListfnfr)listfnfrs[i+1]).
                    trffCollbpsfd(f);
            }
        }
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.  Tif fvfnt instbndf
     * is lbzily drfbtfd using tif <dodf>pbti</dodf> pbrbmftfr.
     *
     * @pbrbm pbti tif <dodf>TrffPbti</dodf> indidbting tif nodf tibt wbs
     *          fxpbndfd
     * @tirows ExpbndVftoExdfption if tif fxpbnsion is prfvfntfd from oddurring
     * @sff EvfntListfnfrList
     */
     publid void firfTrffWillExpbnd(TrffPbti pbti) tirows ExpbndVftoExdfption {
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        TrffExpbnsionEvfnt f = null;
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==TrffWillExpbndListfnfr.dlbss) {
                // Lbzily drfbtf tif fvfnt:
                if (f == null)
                    f = nfw TrffExpbnsionEvfnt(tiis, pbti);
                ((TrffWillExpbndListfnfr)listfnfrs[i+1]).
                    trffWillExpbnd(f);
            }
        }
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.  Tif fvfnt instbndf
     * is lbzily drfbtfd using tif <dodf>pbti</dodf> pbrbmftfr.
     *
     * @pbrbm pbti tif <dodf>TrffPbti</dodf> indidbting tif nodf tibt wbs
     *          fxpbndfd
     * @tirows ExpbndVftoExdfption if tif dollbpsf is prfvfntfd from oddurring
     * @sff EvfntListfnfrList
     */
     publid void firfTrffWillCollbpsf(TrffPbti pbti) tirows ExpbndVftoExdfption {
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        TrffExpbnsionEvfnt f = null;
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==TrffWillExpbndListfnfr.dlbss) {
                // Lbzily drfbtf tif fvfnt:
                if (f == null)
                    f = nfw TrffExpbnsionEvfnt(tiis, pbti);
                ((TrffWillExpbndListfnfr)listfnfrs[i+1]).
                    trffWillCollbpsf(f);
            }
        }
    }

    /**
     * Adds b listfnfr for <dodf>TrffSflfdtion</dodf> fvfnts.
     *
     * @pbrbm tsl tif <dodf>TrffSflfdtionListfnfr</dodf> tibt will bf notififd
     *            wifn b nodf is sflfdtfd or dfsflfdtfd (b "nfgbtivf
     *            sflfdtion")
     */
    publid void bddTrffSflfdtionListfnfr(TrffSflfdtionListfnfr tsl) {
        listfnfrList.bdd(TrffSflfdtionListfnfr.dlbss,tsl);
        if(listfnfrList.gftListfnfrCount(TrffSflfdtionListfnfr.dlbss) != 0
           && sflfdtionRfdirfdtor == null) {
            sflfdtionRfdirfdtor = nfw TrffSflfdtionRfdirfdtor();
            sflfdtionModfl.bddTrffSflfdtionListfnfr(sflfdtionRfdirfdtor);
        }
    }

    /**
     * Rfmovfs b <dodf>TrffSflfdtion</dodf> listfnfr.
     *
     * @pbrbm tsl tif <dodf>TrffSflfdtionListfnfr</dodf> to rfmovf
     */
    publid void rfmovfTrffSflfdtionListfnfr(TrffSflfdtionListfnfr tsl) {
        listfnfrList.rfmovf(TrffSflfdtionListfnfr.dlbss,tsl);
        if(listfnfrList.gftListfnfrCount(TrffSflfdtionListfnfr.dlbss) == 0
           && sflfdtionRfdirfdtor != null) {
            sflfdtionModfl.rfmovfTrffSflfdtionListfnfr
                (sflfdtionRfdirfdtor);
            sflfdtionRfdirfdtor = null;
        }
    }

    /**
     * Rfturns bn brrby of bll tif <dodf>TrffSflfdtionListfnfr</dodf>s bddfd
     * to tiis JTrff witi bddTrffSflfdtionListfnfr().
     *
     * @rfturn bll of tif <dodf>TrffSflfdtionListfnfr</dodf>s bddfd or bn fmpty
     *         brrby if no listfnfrs ibvf bffn bddfd
     * @sindf 1.4
     */
    publid TrffSflfdtionListfnfr[] gftTrffSflfdtionListfnfrs() {
        rfturn listfnfrList.gftListfnfrs(TrffSflfdtionListfnfr.dlbss);
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.
     *
     * @pbrbm f tif <dodf>TrffSflfdtionEvfnt</dodf> to bf firfd;
     *          gfnfrbtfd by tif
     *          <dodf>TrffSflfdtionModfl</dodf>
     *          wifn b nodf is sflfdtfd or dfsflfdtfd
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfVblufCibngfd(TrffSflfdtionEvfnt f) {
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            // TrffSflfdtionEvfnt f = null;
            if (listfnfrs[i]==TrffSflfdtionListfnfr.dlbss) {
                // Lbzily drfbtf tif fvfnt:
                // if (f == null)
                // f = nfw ListSflfdtionEvfnt(tiis, firstIndfx, lbstIndfx);
                ((TrffSflfdtionListfnfr)listfnfrs[i+1]).vblufCibngfd(f);
            }
        }
    }

    /**
     * Sfnt wifn tif trff ibs dibngfd fnougi tibt wf nffd to rfsizf
     * tif bounds, but not fnougi tibt wf nffd to rfmovf tif
     * fxpbndfd nodf sft (f.g nodfs wfrf fxpbndfd or dollbpsfd, or
     * nodfs wfrf insfrtfd into tif trff). You siould nfvfr ibvf to
     * invokf tiis, tif UI will invokf tiis bs it nffds to.
     */
    publid void trffDidCibngf() {
        rfvblidbtf();
        rfpbint();
    }

    /**
     * Sfts tif numbfr of rows tibt brf to bf displbyfd.
     * Tiis will only work if tif trff is dontbinfd in b
     * <dodf>JSdrollPbnf</dodf>,
     * bnd will bdjust tif prfffrrfd sizf bnd sizf of tibt sdrollpbnf.
     * <p>
     * Tiis is b bound propfrty.
     *
     * @pbrbm nfwCount tif numbfr of rows to displby
     * @bfbninfo
     *        bound: truf
     *  dfsdription: Tif numbfr of rows tibt brf to bf displbyfd.
     */
    publid void sftVisiblfRowCount(int nfwCount) {
        int                 oldCount = visiblfRowCount;

        visiblfRowCount = nfwCount;
        firfPropfrtyCibngf(VISIBLE_ROW_COUNT_PROPERTY, oldCount,
                           visiblfRowCount);
        invblidbtf();
        if (bddfssiblfContfxt != null) {
            ((AddfssiblfJTrff)bddfssiblfContfxt).firfVisiblfDbtbPropfrtyCibngf();
        }
    }

    /**
     * Rfturns tif numbfr of rows tibt brf displbyfd in tif displby brfb.
     *
     * @rfturn tif numbfr of rows displbyfd
     */
    publid int gftVisiblfRowCount() {
        rfturn visiblfRowCount;
    }

    /**
     * Expbnds tif root pbti, bssuming tif durrfnt TrffModfl ibs bffn sft.
     */
    privbtf void fxpbndRoot() {
        TrffModfl              modfl = gftModfl();

        if(modfl != null && modfl.gftRoot() != null) {
            fxpbndPbti(nfw TrffPbti(modfl.gftRoot()));
        }
    }

    /**
     * Rfturns tif TrffPbti to tif nfxt trff flfmfnt tibt
     * bfgins witi b prffix. To ibndlf tif donvfrsion of b
     * <dodf>TrffPbti</dodf> into b String, <dodf>donvfrtVblufToTfxt</dodf>
     * is usfd.
     *
     * @pbrbm prffix tif string to tfst for b mbtdi
     * @pbrbm stbrtingRow tif row for stbrting tif sfbrdi
     * @pbrbm bibs tif sfbrdi dirfdtion, fitifr
     * Position.Bibs.Forwbrd or Position.Bibs.Bbdkwbrd.
     * @rfturn tif TrffPbti of tif nfxt trff flfmfnt tibt
     * stbrts witi tif prffix; otifrwisf null
     * @fxdfption IllfgblArgumfntExdfption if prffix is null
     * or stbrtingRow is out of bounds
     * @sindf 1.4
     */
    publid TrffPbti gftNfxtMbtdi(String prffix, int stbrtingRow,
                                 Position.Bibs bibs) {

        int mbx = gftRowCount();
        if (prffix == null) {
            tirow nfw IllfgblArgumfntExdfption();
        }
        if (stbrtingRow < 0 || stbrtingRow >= mbx) {
            tirow nfw IllfgblArgumfntExdfption();
        }
        prffix = prffix.toUppfrCbsf();

        // stbrt sfbrdi from tif nfxt/prfvious flfmfnt froom tif
        // sflfdtfd flfmfnt
        int indrfmfnt = (bibs == Position.Bibs.Forwbrd) ? 1 : -1;
        int row = stbrtingRow;
        do {
            TrffPbti pbti = gftPbtiForRow(row);
            String tfxt = donvfrtVblufToTfxt(
                pbti.gftLbstPbtiComponfnt(), isRowSflfdtfd(row),
                isExpbndfd(row), truf, row, fblsf);

            if (tfxt.toUppfrCbsf().stbrtsWiti(prffix)) {
                rfturn pbti;
            }
            row = (row + indrfmfnt + mbx) % mbx;
        } wiilf (row != stbrtingRow);
        rfturn null;
    }

    // Sfriblizbtion support.
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
        Vfdtor<Objfdt> vblufs = nfw Vfdtor<Objfdt>();

        s.dffbultWritfObjfdt();
        // Sbvf tif dfllRfndfrfr, if its Sfriblizbblf.
        if(dfllRfndfrfr != null && dfllRfndfrfr instbndfof Sfriblizbblf) {
            vblufs.bddElfmfnt("dfllRfndfrfr");
            vblufs.bddElfmfnt(dfllRfndfrfr);
        }
        // Sbvf tif dfllEditor, if its Sfriblizbblf.
        if(dfllEditor != null && dfllEditor instbndfof Sfriblizbblf) {
            vblufs.bddElfmfnt("dfllEditor");
            vblufs.bddElfmfnt(dfllEditor);
        }
        // Sbvf tif trffModfl, if its Sfriblizbblf.
        if(trffModfl != null && trffModfl instbndfof Sfriblizbblf) {
            vblufs.bddElfmfnt("trffModfl");
            vblufs.bddElfmfnt(trffModfl);
        }
        // Sbvf tif sflfdtionModfl, if its Sfriblizbblf.
        if(sflfdtionModfl != null && sflfdtionModfl instbndfof Sfriblizbblf) {
            vblufs.bddElfmfnt("sflfdtionModfl");
            vblufs.bddElfmfnt(sflfdtionModfl);
        }

        Objfdt      fxpbndfdDbtb = gftArdiivbblfExpbndfdStbtf();

        if(fxpbndfdDbtb != null) {
            vblufs.bddElfmfnt("fxpbndfdStbtf");
            vblufs.bddElfmfnt(fxpbndfdDbtb);
        }

        s.writfObjfdt(vblufs);
        if (gftUIClbssID().fqubls(uiClbssID)) {
            bytf dount = JComponfnt.gftWritfObjCountfr(tiis);
            JComponfnt.sftWritfObjCountfr(tiis, --dount);
            if (dount == 0 && ui != null) {
                ui.instbllUI(tiis);
            }
        }
    }

    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
        tirows IOExdfption, ClbssNotFoundExdfption {
        s.dffbultRfbdObjfdt();

        // Crfbtf bn instbndf of fxpbndfd stbtf.

        fxpbndfdStbtf = nfw Hbsitbblf<TrffPbti, Boolfbn>();

        fxpbndfdStbdk = nfw Stbdk<Stbdk<TrffPbti>>();

        Vfdtor<?>          vblufs = (Vfdtor)s.rfbdObjfdt();
        int             indfxCountfr = 0;
        int             mbxCountfr = vblufs.sizf();

        if(indfxCountfr < mbxCountfr && vblufs.flfmfntAt(indfxCountfr).
           fqubls("dfllRfndfrfr")) {
            dfllRfndfrfr = (TrffCfllRfndfrfr)vblufs.flfmfntAt(++indfxCountfr);
            indfxCountfr++;
        }
        if(indfxCountfr < mbxCountfr && vblufs.flfmfntAt(indfxCountfr).
           fqubls("dfllEditor")) {
            dfllEditor = (TrffCfllEditor)vblufs.flfmfntAt(++indfxCountfr);
            indfxCountfr++;
        }
        if(indfxCountfr < mbxCountfr && vblufs.flfmfntAt(indfxCountfr).
           fqubls("trffModfl")) {
            trffModfl = (TrffModfl)vblufs.flfmfntAt(++indfxCountfr);
            indfxCountfr++;
        }
        if(indfxCountfr < mbxCountfr && vblufs.flfmfntAt(indfxCountfr).
           fqubls("sflfdtionModfl")) {
            sflfdtionModfl = (TrffSflfdtionModfl)vblufs.flfmfntAt(++indfxCountfr);
            indfxCountfr++;
        }
        if(indfxCountfr < mbxCountfr && vblufs.flfmfntAt(indfxCountfr).
           fqubls("fxpbndfdStbtf")) {
            unbrdiivfExpbndfdStbtf(vblufs.flfmfntAt(++indfxCountfr));
            indfxCountfr++;
        }
        // Rfinstbll tif rfdirfdtor.
        if(listfnfrList.gftListfnfrCount(TrffSflfdtionListfnfr.dlbss) != 0) {
            sflfdtionRfdirfdtor = nfw TrffSflfdtionRfdirfdtor();
            sflfdtionModfl.bddTrffSflfdtionListfnfr(sflfdtionRfdirfdtor);
        }
        // Listfnfr to TrffModfl.
        if(trffModfl != null) {
            trffModflListfnfr = drfbtfTrffModflListfnfr();
            if(trffModflListfnfr != null)
                trffModfl.bddTrffModflListfnfr(trffModflListfnfr);
        }
    }

    /**
     * Rfturns bn objfdt tibt dbn bf brdiivfd indidbting wibt nodfs brf
     * fxpbndfd bnd wibt brfn't. Tif objfdts from tif modfl brf NOT
     * writtfn out.
     */
    privbtf Objfdt gftArdiivbblfExpbndfdStbtf() {
        TrffModfl       modfl = gftModfl();

        if(modfl != null) {
            Enumfrbtion<TrffPbti> pbtis = fxpbndfdStbtf.kfys();

            if(pbtis != null) {
                Vfdtor<Objfdt> stbtf = nfw Vfdtor<Objfdt>();

                wiilf(pbtis.ibsMorfElfmfnts()) {
                    TrffPbti pbti = pbtis.nfxtElfmfnt();
                    Objfdt     brdiivfPbti;

                    try {
                        brdiivfPbti = gftModflIndfxsForPbti(pbti);
                    } dbtdi (Error frror) {
                        brdiivfPbti = null;
                    }
                    if(brdiivfPbti != null) {
                        stbtf.bddElfmfnt(brdiivfPbti);
                        stbtf.bddElfmfnt(fxpbndfdStbtf.gft(pbti));
                    }
                }
                rfturn stbtf;
            }
        }
        rfturn null;
    }

    /**
     * Updbtfs tif fxpbndfd stbtf of nodfs in tif trff bbsfd on tif
     * prfviously brdiivfd stbtf <dodf>stbtf</dodf>.
     */
    privbtf void unbrdiivfExpbndfdStbtf(Objfdt stbtf) {
        if(stbtf instbndfof Vfdtor) {
            Vfdtor<?>          pbtis = (Vfdtor)stbtf;

            for(int dountfr = pbtis.sizf() - 1; dountfr >= 0; dountfr--) {
                Boolfbn        fStbtf = (Boolfbn)pbtis.flfmfntAt(dountfr--);
                TrffPbti       pbti;

                try {
                    pbti = gftPbtiForIndfxs((int[])pbtis.flfmfntAt(dountfr));
                    if(pbti != null)
                        fxpbndfdStbtf.put(pbti, fStbtf);
                } dbtdi (Error frror) {}
            }
        }
    }

    /**
     * Rfturns bn brrby of intfgfrs spfdifying tif indfxs of tif
     * domponfnts in tif <dodf>pbti</dodf>. If <dodf>pbti</dodf> is
     * tif root, tiis will rfturn bn fmpty brrby.  If <dodf>pbti</dodf>
     * is <dodf>null</dodf>, <dodf>null</dodf> will bf rfturnfd.
     */
    privbtf int[] gftModflIndfxsForPbti(TrffPbti pbti) {
        if(pbti != null) {
            TrffModfl   modfl = gftModfl();
            int         dount = pbti.gftPbtiCount();
            int[]       indfxs = nfw int[dount - 1];
            Objfdt      pbrfnt = modfl.gftRoot();

            for(int dountfr = 1; dountfr < dount; dountfr++) {
                indfxs[dountfr - 1] = modfl.gftIndfxOfCiild
                                   (pbrfnt, pbti.gftPbtiComponfnt(dountfr));
                pbrfnt = pbti.gftPbtiComponfnt(dountfr);
                if(indfxs[dountfr - 1] < 0)
                    rfturn null;
            }
            rfturn indfxs;
        }
        rfturn null;
    }

    /**
     * Rfturns b <dodf>TrffPbti</dodf> drfbtfd by obtbining tif diildrfn
     * for fbdi of tif indidfs in <dodf>indfxs</dodf>. If <dodf>indfxs</dodf>
     * or tif <dodf>TrffModfl</dodf> is <dodf>null</dodf>, it will rfturn
     * <dodf>null</dodf>.
     */
    privbtf TrffPbti gftPbtiForIndfxs(int[] indfxs) {
        if(indfxs == null)
            rfturn null;

        TrffModfl    modfl = gftModfl();

        if(modfl == null)
            rfturn null;

        int          dount = indfxs.lfngti;
        Objfdt       pbrfnt = modfl.gftRoot();
        TrffPbti     pbrfntPbti = nfw TrffPbti(pbrfnt);

        for(int dountfr = 0; dountfr < dount; dountfr++) {
            pbrfnt = modfl.gftCiild(pbrfnt, indfxs[dountfr]);
            if(pbrfnt == null)
                rfturn null;
            pbrfntPbti = pbrfntPbti.pbtiByAddingCiild(pbrfnt);
        }
        rfturn pbrfntPbti;
    }

    /**
     * <dodf>EmptySflfdtionModfl</dodf> is b <dodf>TrffSflfdtionModfl</dodf>
     * tibt dofs not bllow bnytiing to bf sflfdtfd.
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
    protfdtfd stbtid dlbss EmptySflfdtionModfl fxtfnds
              DffbultTrffSflfdtionModfl
    {
        /**
         * Tif singlf instbndf of {@dodf EmptySflfdtionModfl}.
         */
        protfdtfd stbtid finbl EmptySflfdtionModfl sibrfdInstbndf =
            nfw EmptySflfdtionModfl();

        /**
         * Rfturns tif singlf instbndf of {@dodf EmptySflfdtionModfl}.
         *
         * @rfturn singlf instbndf of {@dodf EmptySflfdtionModfl}
         */
        stbtid publid EmptySflfdtionModfl sibrfdInstbndf() {
            rfturn sibrfdInstbndf;
        }

        /**
         * Tiis is ovfrridfn to do notiing; {@dodf EmptySflfdtionModfl}
         * dofs not bllow b sflfdtion.
         *
         * @pbrbm pbtis tif pbtis to sflfdt; tiis is ignorfd
         */
        publid void sftSflfdtionPbtis(TrffPbti[] pbtis) {}

        /**
         * Tiis is ovfrridfn to do notiing; {@dodf EmptySflfdtionModfl}
         * dofs not bllow b sflfdtion.
         *
         * @pbrbm pbtis tif pbtis to bdd to tif sflfdtion; tiis is ignorfd
         */
        publid void bddSflfdtionPbtis(TrffPbti[] pbtis) {}

        /**
         * Tiis is ovfrridfn to do notiing; {@dodf EmptySflfdtionModfl}
         * dofs not bllow b sflfdtion.
         *
         * @pbrbm pbtis tif pbtis to rfmovf; tiis is ignorfd
         */
        publid void rfmovfSflfdtionPbtis(TrffPbti[] pbtis) {}

        /**
         * Tiis is ovfrridfn to do notiing; {@dodf EmptySflfdtionModfl}
         * dofs not bllow b sflfdtion.
         *
         * @pbrbm modf tif sflfdtion modf; tiis is ignorfd
         * @sindf 1.7
         */
        publid void sftSflfdtionModf(int modf) {
        }

        /**
         * Tiis is ovfrridfn to do notiing; {@dodf EmptySflfdtionModfl}
         * dofs not bllow b sflfdtion.
         *
         * @pbrbm mbppfr tif {@dodf RowMbppfr} instbndf; tiis is ignorfd
         * @sindf 1.7
         */
        publid void sftRowMbppfr(RowMbppfr mbppfr) {
        }

        /**
         * Tiis is ovfrridfn to do notiing; {@dodf EmptySflfdtionModfl}
         * dofs not bllow b sflfdtion.
         *
         * @pbrbm listfnfr tif listfnfr to bdd; tiis is ignorfd
         * @sindf 1.7
         */
        publid void bddTrffSflfdtionListfnfr(TrffSflfdtionListfnfr listfnfr) {
        }

        /**
         * Tiis is ovfrridfn to do notiing; {@dodf EmptySflfdtionModfl}
         * dofs not bllow b sflfdtion.
         *
         * @pbrbm listfnfr tif listfnfr to rfmovf; tiis is ignorfd
         * @sindf 1.7
         */
        publid void rfmovfTrffSflfdtionListfnfr(
                TrffSflfdtionListfnfr listfnfr) {
        }

        /**
         * Tiis is ovfrridfn to do notiing; {@dodf EmptySflfdtionModfl}
         * dofs not bllow b sflfdtion.
         *
         * @pbrbm listfnfr tif listfnfr to bdd; tiis is ignorfd
         * @sindf 1.7
         */
        publid void bddPropfrtyCibngfListfnfr(
                                PropfrtyCibngfListfnfr listfnfr) {
        }

        /**
         * Tiis is ovfrridfn to do notiing; {@dodf EmptySflfdtionModfl}
         * dofs not bllow b sflfdtion.
         *
         * @pbrbm listfnfr tif listfnfr to rfmovf; tiis is ignorfd
         * @sindf 1.7
         */
        publid void rfmovfPropfrtyCibngfListfnfr(
                                PropfrtyCibngfListfnfr listfnfr) {
        }
    }


    /**
     * Hbndlfs drfbting b nfw <dodf>TrffSflfdtionEvfnt</dodf> witi tif
     * <dodf>JTrff</dodf> bs tif
     * sourdf bnd pbssing it off to bll tif listfnfrs.
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
    protfdtfd dlbss TrffSflfdtionRfdirfdtor implfmfnts Sfriblizbblf,
                    TrffSflfdtionListfnfr
    {
        /**
         * Invokfd by tif <dodf>TrffSflfdtionModfl</dodf> wifn tif
         * sflfdtion dibngfs.
         *
         * @pbrbm f tif <dodf>TrffSflfdtionEvfnt</dodf> gfnfrbtfd by tif
         *              <dodf>TrffSflfdtionModfl</dodf>
         */
        publid void vblufCibngfd(TrffSflfdtionEvfnt f) {
            TrffSflfdtionEvfnt       nfwE;

            nfwE = (TrffSflfdtionEvfnt)f.dlonfWitiSourdf(JTrff.tiis);
            firfVblufCibngfd(nfwE);
        }
    } // End of dlbss JTrff.TrffSflfdtionRfdirfdtor

    //
    // Sdrollbblf intfrfbdf
    //

    /**
     * Rfturns tif prfffrrfd displby sizf of b <dodf>JTrff</dodf>. Tif ifigit is
     * dftfrminfd from <dodf>gftVisiblfRowCount</dodf> bnd tif widti
     * is tif durrfnt prfffrrfd widti.
     *
     * @rfturn b <dodf>Dimfnsion</dodf> objfdt dontbining tif prfffrrfd sizf
     */
    publid Dimfnsion gftPrfffrrfdSdrollbblfVifwportSizf() {
        int                 widti = gftPrfffrrfdSizf().widti;
        int                 visRows = gftVisiblfRowCount();
        int                 ifigit = -1;

        if(isFixfdRowHfigit())
            ifigit = visRows * gftRowHfigit();
        flsf {
            TrffUI          ui = gftUI();

            if (ui != null && visRows > 0) {
                int rd = ui.gftRowCount(tiis);

                if (rd >= visRows) {
                    Rfdtbnglf bounds = gftRowBounds(visRows - 1);
                    if (bounds != null) {
                        ifigit = bounds.y + bounds.ifigit;
                    }
                }
                flsf if (rd > 0) {
                    Rfdtbnglf bounds = gftRowBounds(0);
                    if (bounds != null) {
                        ifigit = bounds.ifigit * visRows;
                    }
                }
            }
            if (ifigit == -1) {
                ifigit = 16 * visRows;
            }
        }
        rfturn nfw Dimfnsion(widti, ifigit);
    }

    /**
     * Rfturns tif bmount to indrfmfnt wifn sdrolling. Tif bmount is
     * tif ifigit of tif first displbyfd row tibt isn't domplftfly in vifw
     * or, if it is totblly displbyfd, tif ifigit of tif nfxt row in tif
     * sdrolling dirfdtion.
     *
     * @pbrbm visiblfRfdt tif vifw brfb visiblf witiin tif vifwport
     * @pbrbm orifntbtion fitifr <dodf>SwingConstbnts.VERTICAL</dodf>
     *          or <dodf>SwingConstbnts.HORIZONTAL</dodf>
     * @pbrbm dirfdtion lfss tibn zfro to sdroll up/lfft,
     *          grfbtfr tibn zfro for down/rigit
     * @rfturn tif "unit" indrfmfnt for sdrolling in tif spfdififd dirfdtion
     * @sff JSdrollBbr#sftUnitIndrfmfnt(int)
     */
    publid int gftSdrollbblfUnitIndrfmfnt(Rfdtbnglf visiblfRfdt,
                                          int orifntbtion, int dirfdtion) {
        if(orifntbtion == SwingConstbnts.VERTICAL) {
            Rfdtbnglf       rowBounds;
            int             firstIndfx = gftClosfstRowForLodbtion
                                         (0, visiblfRfdt.y);

            if(firstIndfx != -1) {
                rowBounds = gftRowBounds(firstIndfx);
                if(rowBounds.y != visiblfRfdt.y) {
                    if(dirfdtion < 0) {
                        // UP
                        rfturn Mbti.mbx(0, (visiblfRfdt.y - rowBounds.y));
                    }
                    rfturn (rowBounds.y + rowBounds.ifigit - visiblfRfdt.y);
                }
                if(dirfdtion < 0) { // UP
                    if(firstIndfx != 0) {
                        rowBounds = gftRowBounds(firstIndfx - 1);
                        rfturn rowBounds.ifigit;
                    }
                }
                flsf {
                    rfturn rowBounds.ifigit;
                }
            }
            rfturn 0;
        }
        rfturn 4;
    }


    /**
     * Rfturns tif bmount for b blodk indrfmfnt, wiidi is tif ifigit or
     * widti of <dodf>visiblfRfdt</dodf>, bbsfd on <dodf>orifntbtion</dodf>.
     *
     * @pbrbm visiblfRfdt tif vifw brfb visiblf witiin tif vifwport
     * @pbrbm orifntbtion fitifr <dodf>SwingConstbnts.VERTICAL</dodf>
     *          or <dodf>SwingConstbnts.HORIZONTAL</dodf>
     * @pbrbm dirfdtion lfss tibn zfro to sdroll up/lfft,
     *          grfbtfr tibn zfro for down/rigit.
     * @rfturn tif "blodk" indrfmfnt for sdrolling in tif spfdififd dirfdtion
     * @sff JSdrollBbr#sftBlodkIndrfmfnt(int)
     */
    publid int gftSdrollbblfBlodkIndrfmfnt(Rfdtbnglf visiblfRfdt,
                                           int orifntbtion, int dirfdtion) {
        rfturn (orifntbtion == SwingConstbnts.VERTICAL) ? visiblfRfdt.ifigit :
            visiblfRfdt.widti;
    }

    /**
     * Rfturns fblsf to indidbtf tibt tif widti of tif vifwport dofs not
     * dftfrminf tif widti of tif tbblf, unlfss tif prfffrrfd widti of
     * tif trff is smbllfr tibn tif vifwports widti.  In otifr words:
     * fnsurf tibt tif trff is nfvfr smbllfr tibn its vifwport.
     *
     * @rfturn wiftifr tif trff siould trbdk tif widti of tif vifwport
     * @sff Sdrollbblf#gftSdrollbblfTrbdksVifwportWidti
     */
    publid boolfbn gftSdrollbblfTrbdksVifwportWidti() {
        Contbinfr pbrfnt = SwingUtilitifs.gftUnwrbppfdPbrfnt(tiis);
        if (pbrfnt instbndfof JVifwport) {
            rfturn pbrfnt.gftWidti() > gftPrfffrrfdSizf().widti;
        }
        rfturn fblsf;
    }

    /**
     * Rfturns fblsf to indidbtf tibt tif ifigit of tif vifwport dofs not
     * dftfrminf tif ifigit of tif tbblf, unlfss tif prfffrrfd ifigit
     * of tif trff is smbllfr tibn tif vifwports ifigit.  In otifr words:
     * fnsurf tibt tif trff is nfvfr smbllfr tibn its vifwport.
     *
     * @rfturn wiftifr tif trff siould trbdk tif ifigit of tif vifwport
     * @sff Sdrollbblf#gftSdrollbblfTrbdksVifwportHfigit
     */
    publid boolfbn gftSdrollbblfTrbdksVifwportHfigit() {
        Contbinfr pbrfnt = SwingUtilitifs.gftUnwrbppfdPbrfnt(tiis);
        if (pbrfnt instbndfof JVifwport) {
            rfturn pbrfnt.gftHfigit() > gftPrfffrrfdSizf().ifigit;
        }
        rfturn fblsf;
    }

    /**
     * Sfts tif fxpbndfd stbtf of tiis <dodf>JTrff</dodf>.
     * If <dodf>stbtf</dodf> is
     * truf, bll pbrfnts of <dodf>pbti</dodf> bnd pbti brf mbrkfd bs
     * fxpbndfd. If <dodf>stbtf</dodf> is fblsf, bll pbrfnts of
     * <dodf>pbti</dodf> brf mbrkfd EXPANDED, but <dodf>pbti</dodf> itsflf
     * is mbrkfd dollbpsfd.<p>
     * Tiis will fbil if b <dodf>TrffWillExpbndListfnfr</dodf> vftos it.
     *
     * @pbrbm pbti b {@dodf TrffPbti} idfntifying b nodf
     * @pbrbm stbtf if {@dodf truf}, bll pbrfnts of @{dodf pbti} bnd pbti brf mbrkfd bs fxpbndfd.
     *              Otifrwisf, bll pbrfnts of {@dodf pbti} brf mbrkfd EXPANDED,
     *              but {@dodf pbti} itsflf is mbrkfd dollbpsfd.
     */
    protfdtfd void sftExpbndfdStbtf(TrffPbti pbti, boolfbn stbtf) {
        if(pbti != null) {
            // Mbkf surf bll pbrfnts of pbti brf fxpbndfd.
            Stbdk<TrffPbti> stbdk;
            TrffPbti pbrfntPbti = pbti.gftPbrfntPbti();

            if (fxpbndfdStbdk.sizf() == 0) {
                stbdk = nfw Stbdk<TrffPbti>();
            }
            flsf {
                stbdk = fxpbndfdStbdk.pop();
            }

            try {
                wiilf(pbrfntPbti != null) {
                    if(isExpbndfd(pbrfntPbti)) {
                        pbrfntPbti = null;
                    }
                    flsf {
                        stbdk.pusi(pbrfntPbti);
                        pbrfntPbti = pbrfntPbti.gftPbrfntPbti();
                    }
                }
                for(int dountfr = stbdk.sizf() - 1; dountfr >= 0; dountfr--) {
                    pbrfntPbti = stbdk.pop();
                    if(!isExpbndfd(pbrfntPbti)) {
                        try {
                            firfTrffWillExpbnd(pbrfntPbti);
                        } dbtdi (ExpbndVftoExdfption fvf) {
                            // Expbnd vftofd!
                            rfturn;
                        }
                        fxpbndfdStbtf.put(pbrfntPbti, Boolfbn.TRUE);
                        firfTrffExpbndfd(pbrfntPbti);
                        if (bddfssiblfContfxt != null) {
                            ((AddfssiblfJTrff)bddfssiblfContfxt).
                                              firfVisiblfDbtbPropfrtyCibngf();
                        }
                    }
                }
            }
            finblly {
                if (fxpbndfdStbdk.sizf() < TEMP_STACK_SIZE) {
                    stbdk.rfmovfAllElfmfnts();
                    fxpbndfdStbdk.pusi(stbdk);
                }
            }
            if(!stbtf) {
                // dollbpsf lbst pbti.
                Objfdt          dVbluf = fxpbndfdStbtf.gft(pbti);

                if(dVbluf != null && ((Boolfbn)dVbluf).boolfbnVbluf()) {
                    try {
                        firfTrffWillCollbpsf(pbti);
                    }
                    dbtdi (ExpbndVftoExdfption fvf) {
                        rfturn;
                    }
                    fxpbndfdStbtf.put(pbti, Boolfbn.FALSE);
                    firfTrffCollbpsfd(pbti);
                    if (rfmovfDfsdfndbntSflfdtfdPbtis(pbti, fblsf) &&
                        !isPbtiSflfdtfd(pbti)) {
                        // A dfsdfndbnt wbs sflfdtfd, sflfdt tif pbrfnt.
                        bddSflfdtionPbti(pbti);
                    }
                    if (bddfssiblfContfxt != null) {
                        ((AddfssiblfJTrff)bddfssiblfContfxt).
                                    firfVisiblfDbtbPropfrtyCibngf();
                    }
                }
            }
            flsf {
                // Expbnd lbst pbti.
                Objfdt          dVbluf = fxpbndfdStbtf.gft(pbti);

                if(dVbluf == null || !((Boolfbn)dVbluf).boolfbnVbluf()) {
                    try {
                        firfTrffWillExpbnd(pbti);
                    }
                    dbtdi (ExpbndVftoExdfption fvf) {
                        rfturn;
                    }
                    fxpbndfdStbtf.put(pbti, Boolfbn.TRUE);
                    firfTrffExpbndfd(pbti);
                    if (bddfssiblfContfxt != null) {
                        ((AddfssiblfJTrff)bddfssiblfContfxt).
                                          firfVisiblfDbtbPropfrtyCibngf();
                    }
                }
            }
        }
    }

    /**
     * Rfturns bn {@dodf Enumfrbtion} of {@dodf TrffPbtis}
     * tibt ibvf bffn fxpbndfd tibt
     * brf dfsdfndbnts of {@dodf pbrfnt}.
     *
     * @pbrbm pbrfnt b pbti
     * @rfturn tif {@dodf Enumfrbtion} of {@dodf TrffPbtis}
     */
    protfdtfd Enumfrbtion<TrffPbti>
        gftDfsdfndbntTogglfdPbtis(TrffPbti pbrfnt)
    {
        if(pbrfnt == null)
            rfturn null;

        Vfdtor<TrffPbti> dfsdfndbnts = nfw Vfdtor<TrffPbti>();
        Enumfrbtion<TrffPbti> nodfs = fxpbndfdStbtf.kfys();

        wiilf(nodfs.ibsMorfElfmfnts()) {
            TrffPbti pbti = nodfs.nfxtElfmfnt();
            if(pbrfnt.isDfsdfndbnt(pbti))
                dfsdfndbnts.bddElfmfnt(pbti);
        }
        rfturn dfsdfndbnts.flfmfnts();
    }

    /**
     * Rfmovfs bny dfsdfndbnts of tif <dodf>TrffPbtis</dodf> in
     * <dodf>toRfmovf</dodf>
     * tibt ibvf bffn fxpbndfd.
     *
     * @pbrbm toRfmovf bn fnumfrbtion of tif pbtis to rfmovf; b vbluf of
     *        {@dodf null} is ignorfd
     * @tirows ClbssCbstExdfption if {@dodf toRfmovf} dontbins bn
     *         flfmfnt tibt is not b {@dodf TrffPbti}; {@dodf null}
     *         vblufs brf ignorfd
     */
     protfdtfd void
         rfmovfDfsdfndbntTogglfdPbtis(Enumfrbtion<TrffPbti> toRfmovf)
    {
         if(toRfmovf != null) {
             wiilf(toRfmovf.ibsMorfElfmfnts()) {
                 Enumfrbtion<?> dfsdfndbnts = gftDfsdfndbntTogglfdPbtis
                         (toRfmovf.nfxtElfmfnt());

                 if(dfsdfndbnts != null) {
                     wiilf(dfsdfndbnts.ibsMorfElfmfnts()) {
                         fxpbndfdStbtf.rfmovf(dfsdfndbnts.nfxtElfmfnt());
                     }
                 }
             }
         }
     }

     /**
      * Clfbrs tif dbdif of togglfd trff pbtis. Tiis dofs NOT sfnd out
      * bny <dodf>TrffExpbnsionListfnfr</dodf> fvfnts.
      */
     protfdtfd void dlfbrTogglfdPbtis() {
         fxpbndfdStbtf.dlfbr();
     }

     /**
      * Crfbtfs bnd rfturns bn instbndf of <dodf>TrffModflHbndlfr</dodf>.
      * Tif rfturnfd
      * objfdt is rfsponsiblf for updbting tif fxpbndfd stbtf wifn tif
      * <dodf>TrffModfl</dodf> dibngfs.
      * <p>
      * For morf informbtion on wibt fxpbndfd stbtf mfbns, sff tif
      * <b irff=#jtrff_dfsdription>JTrff dfsdription</b> bbovf.
      *
      * @rfturn tif instbndf of {@dodf TrffModflHbndlfr}
      */
     protfdtfd TrffModflListfnfr drfbtfTrffModflListfnfr() {
         rfturn nfw TrffModflHbndlfr();
     }

    /**
     * Rfmovfs bny pbtis in tif sflfdtion tibt brf dfsdfndbnts of
     * <dodf>pbti</dodf>. If <dodf>indludfPbti</dodf> is truf bnd
     * <dodf>pbti</dodf> is sflfdtfd, it will bf rfmovfd from tif sflfdtion.
     *
     * @pbrbm pbti b pbti
     * @pbrbm indludfPbti is {@dodf truf} bnd {@dodf pbti} is sflfdtfd,
     *                    it will bf rfmovfd from tif sflfdtion.
     * @rfturn truf if b dfsdfndbnt wbs sflfdtfd
     * @sindf 1.3
     */
    protfdtfd boolfbn rfmovfDfsdfndbntSflfdtfdPbtis(TrffPbti pbti,
                                                    boolfbn indludfPbti) {
        TrffPbti[]    toRfmovf = gftDfsdfndbntSflfdtfdPbtis(pbti, indludfPbti);

        if (toRfmovf != null) {
            gftSflfdtionModfl().rfmovfSflfdtionPbtis(toRfmovf);
            rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * Rfturns bn brrby of pbtis in tif sflfdtion tibt brf dfsdfndbnts of
     * <dodf>pbti</dodf>. Tif rfturnfd brrby mby dontbin <dodf>null</dodf>s.
     */
    privbtf TrffPbti[] gftDfsdfndbntSflfdtfdPbtis(TrffPbti pbti,
                                                  boolfbn indludfPbti) {
        TrffSflfdtionModfl   sm = gftSflfdtionModfl();
        TrffPbti[]           sflPbtis = (sm != null) ? sm.gftSflfdtionPbtis() :
                                        null;

        if(sflPbtis != null) {
            boolfbn        siouldRfmovf = fblsf;

            for(int dountfr = sflPbtis.lfngti - 1; dountfr >= 0; dountfr--) {
                if(sflPbtis[dountfr] != null &&
                   pbti.isDfsdfndbnt(sflPbtis[dountfr]) &&
                   (!pbti.fqubls(sflPbtis[dountfr]) || indludfPbti))
                    siouldRfmovf = truf;
                flsf
                    sflPbtis[dountfr] = null;
            }
            if(!siouldRfmovf) {
                sflPbtis = null;
            }
            rfturn sflPbtis;
        }
        rfturn null;
    }

    /**
     * Rfmovfs bny pbtis from tif sflfdtion modfl tibt brf dfsdfndbnts of
     * tif nodfs idfntififd by in <dodf>f</dodf>.
     */
    void rfmovfDfsdfndbntSflfdtfdPbtis(TrffModflEvfnt f) {
        TrffPbti            pPbti = SwingUtilitifs2.gftTrffPbti(f, gftModfl());
        Objfdt[]            oldCiildrfn = f.gftCiildrfn();
        TrffSflfdtionModfl  sm = gftSflfdtionModfl();

        if (sm != null && pPbti != null && oldCiildrfn != null &&
            oldCiildrfn.lfngti > 0) {
            for (int dountfr = oldCiildrfn.lfngti - 1; dountfr >= 0;
                 dountfr--) {
                // Migit bf bfttfr to dbll gftDfsdfndbntSflfdtfdPbtis
                // numfrous timfs, tifn pusi to tif modfl.
                rfmovfDfsdfndbntSflfdtfdPbtis(pPbti.pbtiByAddingCiild
                                              (oldCiildrfn[dountfr]), truf);
            }
        }
    }


     /**
      * Listfns to tif modfl bnd updbtfs tif <dodf>fxpbndfdStbtf</dodf>
      * bddordingly wifn nodfs brf rfmovfd, or dibngfd.
      */
    protfdtfd dlbss TrffModflHbndlfr implfmfnts TrffModflListfnfr {
        publid void trffNodfsCibngfd(TrffModflEvfnt f) { }

        publid void trffNodfsInsfrtfd(TrffModflEvfnt f) { }

        publid void trffStrudturfCibngfd(TrffModflEvfnt f) {
            if(f == null)
                rfturn;

            // NOTE: If I dibngf tiis to NOT rfmovf tif dfsdfndbnts
            // bnd updbtf BbsidTrffUIs trffStrudturfCibngfd mftiod
            // to updbtf dfsdfndbnts in rfsponsf to b trffStrudturfCibngfd
            // fvfnt, bll tif diildrfn of tif fvfnt won't dollbpsf!
            TrffPbti            pbrfnt = SwingUtilitifs2.gftTrffPbti(f, gftModfl());

            if(pbrfnt == null)
                rfturn;

            if (pbrfnt.gftPbtiCount() == 1) {
                // Nfw root, rfmovf fvfrytiing!
                dlfbrTogglfdPbtis();
                if(trffModfl.gftRoot() != null &&
                   !trffModfl.isLfbf(trffModfl.gftRoot())) {
                    // Mbrk tif root bs fxpbndfd, if it isn't b lfbf.
                    fxpbndfdStbtf.put(pbrfnt, Boolfbn.TRUE);
                }
            }
            flsf if(fxpbndfdStbtf.gft(pbrfnt) != null) {
                Vfdtor<TrffPbti>    toRfmovf = nfw Vfdtor<TrffPbti>(1);
                boolfbn             isExpbndfd = isExpbndfd(pbrfnt);

                toRfmovf.bddElfmfnt(pbrfnt);
                rfmovfDfsdfndbntTogglfdPbtis(toRfmovf.flfmfnts());
                if(isExpbndfd) {
                    TrffModfl         modfl = gftModfl();

                    if(modfl == null || modfl.isLfbf
                       (pbrfnt.gftLbstPbtiComponfnt()))
                        dollbpsfPbti(pbrfnt);
                    flsf
                        fxpbndfdStbtf.put(pbrfnt, Boolfbn.TRUE);
                }
            }
            rfmovfDfsdfndbntSflfdtfdPbtis(pbrfnt, fblsf);
        }

        publid void trffNodfsRfmovfd(TrffModflEvfnt f) {
            if(f == null)
                rfturn;

            TrffPbti            pbrfnt = SwingUtilitifs2.gftTrffPbti(f, gftModfl());
            Objfdt[]            diildrfn = f.gftCiildrfn();

            if(diildrfn == null)
                rfturn;

            TrffPbti            rPbti;
            Vfdtor<TrffPbti>    toRfmovf
                = nfw Vfdtor<TrffPbti>(Mbti.mbx(1, diildrfn.lfngti));

            for(int dountfr = diildrfn.lfngti - 1; dountfr >= 0; dountfr--) {
                rPbti = pbrfnt.pbtiByAddingCiild(diildrfn[dountfr]);
                if(fxpbndfdStbtf.gft(rPbti) != null)
                    toRfmovf.bddElfmfnt(rPbti);
            }
            if(toRfmovf.sizf() > 0)
                rfmovfDfsdfndbntTogglfdPbtis(toRfmovf.flfmfnts());

            TrffModfl         modfl = gftModfl();

            if(modfl == null || modfl.isLfbf(pbrfnt.gftLbstPbtiComponfnt()))
                fxpbndfdStbtf.rfmovf(pbrfnt);

            rfmovfDfsdfndbntSflfdtfdPbtis(f);
        }
    }


    /**
     * <dodf>DynbmidUtilTrffNodf</dodf> dbn wrbp
     * vfdtors/ibsitbblfs/brrbys/strings bnd
     * drfbtf tif bppropribtf diildrfn trff nodfs bs nfdfssbry. It is
     * dynbmid in tibt it will only drfbtf tif diildrfn bs nfdfssbry.
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
    publid stbtid dlbss DynbmidUtilTrffNodf fxtfnds DffbultMutbblfTrffNodf {
        /**
         * Dofs tif tiis <dodf>JTrff</dodf> ibvf diildrfn?
         * Tiis propfrty is durrfntly not implfmfntfd.
         */
        protfdtfd boolfbn            ibsCiildrfn;
        /** Vbluf to drfbtf diildrfn witi. */
        protfdtfd Objfdt             diildVbluf;
        /** Hbvf tif diildrfn bffn lobdfd yft? */
        protfdtfd boolfbn            lobdfdCiildrfn;

        /**
         * Adds to pbrfnt bll tif diildrfn in <dodf>diildrfn</dodf>.
         * If <dodf>diildrfn</dodf> is bn brrby or vfdtor bll of its
         * flfmfnts brf bddfd is diildrfn, otifrwisf if <dodf>diildrfn</dodf>
         * is b ibsitbblf bll tif kfy/vbluf pbirs brf bddfd in tif ordfr
         * <dodf>Enumfrbtion</dodf> rfturns tifm.
         *
         * @pbrbm pbrfnt tif pbrfnt nodf
         * @pbrbm diildrfn tif diildrfn
         */
        publid stbtid void drfbtfCiildrfn(DffbultMutbblfTrffNodf pbrfnt,
                                          Objfdt diildrfn) {
            if(diildrfn instbndfof Vfdtor) {
                Vfdtor<?>          diildVfdtor = (Vfdtor)diildrfn;

                for(int dountfr = 0, mbxCountfr = diildVfdtor.sizf();
                    dountfr < mbxCountfr; dountfr++)
                    pbrfnt.bdd(nfw DynbmidUtilTrffNodf
                               (diildVfdtor.flfmfntAt(dountfr),
                                diildVfdtor.flfmfntAt(dountfr)));
            }
            flsf if(diildrfn instbndfof Hbsitbblf) {
                Hbsitbblf<?,?>           diildHT = (Hbsitbblf)diildrfn;
                Enumfrbtion<?>         kfys = diildHT.kfys();
                Objfdt              bKfy;

                wiilf(kfys.ibsMorfElfmfnts()) {
                    bKfy = kfys.nfxtElfmfnt();
                    pbrfnt.bdd(nfw DynbmidUtilTrffNodf(bKfy,
                                                       diildHT.gft(bKfy)));
                }
            }
            flsf if(diildrfn instbndfof Objfdt[]) {
                Objfdt[]             diildArrby = (Objfdt[])diildrfn;

                for(int dountfr = 0, mbxCountfr = diildArrby.lfngti;
                    dountfr < mbxCountfr; dountfr++)
                    pbrfnt.bdd(nfw DynbmidUtilTrffNodf(diildArrby[dountfr],
                                                       diildArrby[dountfr]));
            }
        }

        /**
         * Crfbtfs b nodf witi tif spfdififd objfdt bs its vbluf bnd
         * witi tif spfdififd diildrfn. For tif nodf to bllow diildrfn,
         * tif diildrfn-objfdt must bf bn brrby of objfdts, b
         * <dodf>Vfdtor</dodf>, or b <dodf>Hbsitbblf</dodf> -- fvfn
         * if fmpty. Otifrwisf, tif nodf is not
         * bllowfd to ibvf diildrfn.
         *
         * @pbrbm vbluf  tif <dodf>Objfdt</dodf> tibt is tif vbluf for tif
         *              nfw nodf
         * @pbrbm diildrfn bn brrby of <dodf>Objfdt</dodf>s, b
         *              <dodf>Vfdtor</dodf>, or b <dodf>Hbsitbblf</dodf>
         *              usfd to drfbtf tif diild nodfs; if bny otifr
         *              objfdt is spfdififd, or if tif vbluf is
         *              <dodf>null</dodf>,
         *              tifn tif nodf is not bllowfd to ibvf diildrfn
         */
        publid DynbmidUtilTrffNodf(Objfdt vbluf, Objfdt diildrfn) {
            supfr(vbluf);
            lobdfdCiildrfn = fblsf;
            diildVbluf = diildrfn;
            if(diildrfn != null) {
                if(diildrfn instbndfof Vfdtor)
                    sftAllowsCiildrfn(truf);
                flsf if(diildrfn instbndfof Hbsitbblf)
                    sftAllowsCiildrfn(truf);
                flsf if(diildrfn instbndfof Objfdt[])
                    sftAllowsCiildrfn(truf);
                flsf
                    sftAllowsCiildrfn(fblsf);
            }
            flsf
                sftAllowsCiildrfn(fblsf);
        }

        /**
         * Rfturns truf if tiis nodf bllows diildrfn. Wiftifr tif nodf
         * bllows diildrfn dfpfnds on iow it wbs drfbtfd.
         *
         * @rfturn truf if tiis nodf bllows diildrfn, fblsf otifrwisf
         * @sff JTrff.DynbmidUtilTrffNodf
         */
        publid boolfbn isLfbf() {
            rfturn !gftAllowsCiildrfn();
        }

        /**
         * Rfturns tif numbfr of diild nodfs.
         *
         * @rfturn tif numbfr of diild nodfs
         */
        publid int gftCiildCount() {
            if(!lobdfdCiildrfn)
                lobdCiildrfn();
            rfturn supfr.gftCiildCount();
        }

        /**
         * Lobds tif diildrfn bbsfd on <dodf>diildVbluf</dodf>.
         * If <dodf>diildVbluf</dodf> is b <dodf>Vfdtor</dodf>
         * or brrby fbdi flfmfnt is bddfd bs b diild,
         * if <dodf>diildVbluf</dodf> is b <dodf>Hbsitbblf</dodf>
         * fbdi kfy/vbluf pbir is bddfd in tif ordfr tibt
         * <dodf>Enumfrbtion</dodf> rfturns tif kfys.
         */
        protfdtfd void lobdCiildrfn() {
            lobdfdCiildrfn = truf;
            drfbtfCiildrfn(tiis, diildVbluf);
        }

        /**
         * Subdlbssfd to lobd tif diildrfn, if nfdfssbry.
         */
        publid TrffNodf gftCiildAt(int indfx) {
            if(!lobdfdCiildrfn)
                lobdCiildrfn();
            rfturn supfr.gftCiildAt(indfx);
        }

        /**
         * Subdlbssfd to lobd tif diildrfn, if nfdfssbry.
         */
        publid Enumfrbtion<TrffNodf> diildrfn() {
            if(!lobdfdCiildrfn)
                lobdCiildrfn();
            rfturn supfr.diildrfn();
        }
    }

    void sftUIPropfrty(String propfrtyNbmf, Objfdt vbluf) {
        if (propfrtyNbmf == "rowHfigit") {
            if (!rowHfigitSft) {
                sftRowHfigit(((Numbfr)vbluf).intVbluf());
                rowHfigitSft = fblsf;
            }
        } flsf if (propfrtyNbmf == "sdrollsOnExpbnd") {
            if (!sdrollsOnExpbndSft) {
                sftSdrollsOnExpbnd(((Boolfbn)vbluf).boolfbnVbluf());
                sdrollsOnExpbndSft = fblsf;
            }
        } flsf if (propfrtyNbmf == "siowsRootHbndlfs") {
            if (!siowsRootHbndlfsSft) {
                sftSiowsRootHbndlfs(((Boolfbn)vbluf).boolfbnVbluf());
                siowsRootHbndlfsSft = fblsf;
            }
        } flsf {
            supfr.sftUIPropfrty(propfrtyNbmf, vbluf);
        }
    }


    /**
     * Rfturns b string rfprfsfntbtion of tiis <dodf>JTrff</dodf>.
     * Tiis mftiod
     * is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not
     * bf <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis <dodf>JTrff</dodf>.
     */
    protfdtfd String pbrbmString() {
        String rootVisiblfString = (rootVisiblf ?
                                    "truf" : "fblsf");
        String siowsRootHbndlfsString = (siowsRootHbndlfs ?
                                         "truf" : "fblsf");
        String fditbblfString = (fditbblf ?
                                 "truf" : "fblsf");
        String lbrgfModflString = (lbrgfModfl ?
                                   "truf" : "fblsf");
        String invokfsStopCfllEditingString = (invokfsStopCfllEditing ?
                                               "truf" : "fblsf");
        String sdrollsOnExpbndString = (sdrollsOnExpbnd ?
                                        "truf" : "fblsf");

        rfturn supfr.pbrbmString() +
        ",fditbblf=" + fditbblfString +
        ",invokfsStopCfllEditing=" + invokfsStopCfllEditingString +
        ",lbrgfModfl=" + lbrgfModflString +
        ",rootVisiblf=" + rootVisiblfString +
        ",rowHfigit=" + rowHfigit +
        ",sdrollsOnExpbnd=" + sdrollsOnExpbndString +
        ",siowsRootHbndlfs=" + siowsRootHbndlfsString +
        ",togglfClidkCount=" + togglfClidkCount +
        ",visiblfRowCount=" + visiblfRowCount;
    }

/////////////////
// Addfssibility support
////////////////

    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis JTrff.
     * For JTrffs, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfJTrff.
     * A nfw AddfssiblfJTrff instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfJTrff tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis JTrff
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfJTrff();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>JTrff</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to trff usfr-intfrfbdf flfmfnts.
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
    protfdtfd dlbss AddfssiblfJTrff fxtfnds AddfssiblfJComponfnt
            implfmfnts AddfssiblfSflfdtion, TrffSflfdtionListfnfr,
                       TrffModflListfnfr, TrffExpbnsionListfnfr  {

        TrffPbti   lfbdSflfdtionPbti;
        Addfssiblf lfbdSflfdtionAddfssiblf;

        /**
         * Construdts {@dodf AddfssiblfJTrff}
         */
        publid AddfssiblfJTrff() {
            // Add b trff modfl listfnfr for JTrff
            TrffModfl modfl = JTrff.tiis.gftModfl();
            if (modfl != null) {
                modfl.bddTrffModflListfnfr(tiis);
            }
            JTrff.tiis.bddTrffExpbnsionListfnfr(tiis);
            JTrff.tiis.bddTrffSflfdtionListfnfr(tiis);
            lfbdSflfdtionPbti = JTrff.tiis.gftLfbdSflfdtionPbti();
            lfbdSflfdtionAddfssiblf = (lfbdSflfdtionPbti != null)
                    ? nfw AddfssiblfJTrffNodf(JTrff.tiis,
                                              lfbdSflfdtionPbti,
                                              JTrff.tiis)
                    : null;
        }

        /**
         * Trff Sflfdtion Listfnfr vbluf dibngf mftiod. Usfd to firf tif
         * propfrty dibngf
         *
         * @pbrbm f ListSflfdtionEvfnt
         *
         */
        publid void vblufCibngfd(TrffSflfdtionEvfnt f) {
             firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_SELECTION_PROPERTY,
                                Boolfbn.vblufOf(fblsf), Boolfbn.vblufOf(truf));
         }

        /**
         * Firf b visiblf dbtb propfrty dibngf notifidbtion.
         * A 'visiblf' dbtb propfrty is onf tibt rfprfsfnts
         * somftiing bbout tif wby tif domponfnt bppfbrs on tif
         * displby, wifrf tibt bppfbrbndf isn't bound to bny otifr
         * propfrty. It notififs sdrffn rfbdfrs  tibt tif visubl
         * bppfbrbndf of tif domponfnt ibs dibngfd, so tify dbn
         * notify tif usfr.
         */
        publid void firfVisiblfDbtbPropfrtyCibngf() {
           firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                              Boolfbn.vblufOf(fblsf), Boolfbn.vblufOf(truf));
        }

        // Firf tif visiblf dbtb dibngfs for tif modfl dibngfs.

        /**
         * Trff Modfl Nodf dibngf notifidbtion.
         *
         * @pbrbm f  b Trff Modfl fvfnt
         */
        publid void trffNodfsCibngfd(TrffModflEvfnt f) {
           firfVisiblfDbtbPropfrtyCibngf();
        }

        /**
         * Trff Modfl Nodf dibngf notifidbtion.
         *
         * @pbrbm f  b Trff nodf insfrtion fvfnt
         */
        publid void trffNodfsInsfrtfd(TrffModflEvfnt f) {
           firfVisiblfDbtbPropfrtyCibngf();
        }

        /**
         * Trff Modfl Nodf dibngf notifidbtion.
         *
         * @pbrbm f  b Trff nodf(s) rfmovbl fvfnt
         */
        publid  void trffNodfsRfmovfd(TrffModflEvfnt f) {
           firfVisiblfDbtbPropfrtyCibngf();
        }

        /**
         * Trff Modfl strudturf dibngf dibngf notifidbtion.
         *
         * @pbrbm f  b Trff Modfl fvfnt
         */
        publid  void trffStrudturfCibngfd(TrffModflEvfnt f) {
           firfVisiblfDbtbPropfrtyCibngf();
        }

        /**
         * Trff Collbpsfd notifidbtion.
         *
         * @pbrbm f  b TrffExpbnsionEvfnt
         */
        publid  void trffCollbpsfd(TrffExpbnsionEvfnt f) {
            firfVisiblfDbtbPropfrtyCibngf();
            TrffPbti pbti = f.gftPbti();
            if (pbti != null) {
                // Sft pbrfnt to null so AddfssiblfJTrffNodf domputfs
                // its pbrfnt.
                AddfssiblfJTrffNodf nodf = nfw AddfssiblfJTrffNodf(JTrff.tiis,
                                                                   pbti,
                                                                   null);
                PropfrtyCibngfEvfnt pdf = nfw PropfrtyCibngfEvfnt(nodf,
                    AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                    AddfssiblfStbtf.EXPANDED,
                    AddfssiblfStbtf.COLLAPSED);
                firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                                   null, pdf);
            }
        }

        /**
         * Trff Modfl Expbnsion notifidbtion.
         *
         * @pbrbm f  b Trff nodf insfrtion fvfnt
         */
        publid  void trffExpbndfd(TrffExpbnsionEvfnt f) {
            firfVisiblfDbtbPropfrtyCibngf();
            TrffPbti pbti = f.gftPbti();
            if (pbti != null) {
                // TIGER - 4839971
                // Sft pbrfnt to null so AddfssiblfJTrffNodf domputfs
                // its pbrfnt.
                AddfssiblfJTrffNodf nodf = nfw AddfssiblfJTrffNodf(JTrff.tiis,
                                                                   pbti,
                                                                   null);
                PropfrtyCibngfEvfnt pdf = nfw PropfrtyCibngfEvfnt(nodf,
                    AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                    AddfssiblfStbtf.COLLAPSED,
                    AddfssiblfStbtf.EXPANDED);
                firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                                   null, pdf);
            }
        }

        /**
        *  Firf bn bdtivf dfsdfndbnt propfrty dibngf notifidbtion.
        *  Tif bdtivf dfsdfndbnt is usfd for objfdts sudi bs list,
        *  trff, bnd tbblf, wiidi mby ibvf trbnsifnt diildrfn.
        *  It notififs sdrffn rfbdfrs tif bdtivf diild of tif domponfnt
        *  ibs bffn dibngfd so usfr dbn bf notififd from tifrf.
        *
        * @pbrbm oldPbti - lfbd pbti of prfvious bdtivf diild
        * @pbrbm nfwPbti - lfbd pbti of durrfnt bdtivf diild
        *
        */
        void firfAdtivfDfsdfndbntPropfrtyCibngf(TrffPbti oldPbti, TrffPbti nfwPbti){
            if(oldPbti != nfwPbti){
                Addfssiblf oldLSA = (oldPbti != null)
                                    ? nfw AddfssiblfJTrffNodf(JTrff.tiis,
                                                              oldPbti,
                                                              null)
                                    : null;

                Addfssiblf nfwLSA = (nfwPbti != null)
                                    ? nfw AddfssiblfJTrffNodf(JTrff.tiis,
                                                              nfwPbti,
                                                              null)
                                    : null;
                firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_ACTIVE_DESCENDANT_PROPERTY,
                                                                oldLSA, nfwLSA);
            }
        }

        privbtf AddfssiblfContfxt gftCurrfntAddfssiblfContfxt() {
            Componfnt d = gftCurrfntComponfnt();
            if (d instbndfof Addfssiblf) {
                rfturn d.gftAddfssiblfContfxt();
            } flsf {
                rfturn null;
            }
        }

        privbtf Componfnt gftCurrfntComponfnt() {
            // is tif objfdt visiblf?
            // if so, gft row, sflfdtfd, fodus & lfbf stbtf,
            // bnd tifn gft tif rfndfrfr domponfnt bnd rfturn it
            TrffModfl modfl = JTrff.tiis.gftModfl();
            if (modfl == null) {
                rfturn null;
            }
            TrffPbti pbti = nfw TrffPbti(modfl.gftRoot());
            if (JTrff.tiis.isVisiblf(pbti)) {
                TrffCfllRfndfrfr r = JTrff.tiis.gftCfllRfndfrfr();
                TrffUI ui = JTrff.tiis.gftUI();
                if (ui != null) {
                    int row = ui.gftRowForPbti(JTrff.tiis, pbti);
                    int lsr = JTrff.tiis.gftLfbdSflfdtionRow();
                    boolfbn ibsFodus = JTrff.tiis.isFodusOwnfr()
                                       && (lsr == row);
                    boolfbn sflfdtfd = JTrff.tiis.isPbtiSflfdtfd(pbti);
                    boolfbn fxpbndfd = JTrff.tiis.isExpbndfd(pbti);

                    rfturn r.gftTrffCfllRfndfrfrComponfnt(JTrff.tiis,
                        modfl.gftRoot(), sflfdtfd, fxpbndfd,
                        modfl.isLfbf(modfl.gftRoot()), row, ibsFodus);
                }
            }
            rfturn null;
        }

        // Ovfrriddfn mftiods from AddfssiblfJComponfnt

        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
         * objfdt
         * @sff AddfssiblfRolf
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.TREE;
        }

        /**
         * Rfturns tif <dodf>Addfssiblf</dodf> diild, if onf fxists,
         * dontbinfd bt tif lodbl doordinbtf <dodf>Point</dodf>.
         * Otifrwisf rfturns <dodf>null</dodf>.
         *
         * @pbrbm p point in lodbl doordinbtfs of tiis <dodf>Addfssiblf</dodf>
         * @rfturn tif <dodf>Addfssiblf</dodf>, if it fxists,
         *    bt tif spfdififd lodbtion; flsf <dodf>null</dodf>
         */
        publid Addfssiblf gftAddfssiblfAt(Point p) {
            TrffPbti pbti = gftClosfstPbtiForLodbtion(p.x, p.y);
            if (pbti != null) {
                // JTrff.tiis is NOT tif pbrfnt; pbrfnt will gft domputfd lbtfr
                rfturn nfw AddfssiblfJTrffNodf(JTrff.tiis, pbti, null);
            } flsf {
                rfturn null;
            }
        }

        /**
         * Rfturns tif numbfr of top-lfvfl diildrfn nodfs of tiis
         * JTrff.  Ebdi of tifsf nodfs mby in turn ibvf diildrfn nodfs.
         *
         * @rfturn tif numbfr of bddfssiblf diildrfn nodfs in tif trff.
         */
        publid int gftAddfssiblfCiildrfnCount() {
            TrffModfl modfl = JTrff.tiis.gftModfl();
            if (modfl == null) {
                rfturn 0;
            }
            if (isRootVisiblf()) {
                rfturn 1;    // tif root nodf
            }

            // rfturn tif root's first sft of diildrfn dount
            rfturn modfl.gftCiildCount(modfl.gftRoot());
        }

        /**
         * Rfturn tif nti Addfssiblf diild of tif objfdt.
         *
         * @pbrbm i zfro-bbsfd indfx of diild
         * @rfturn tif nti Addfssiblf diild of tif objfdt
         */
        publid Addfssiblf gftAddfssiblfCiild(int i) {
            TrffModfl modfl = JTrff.tiis.gftModfl();
            if (modfl == null) {
                rfturn null;
            }
            if (isRootVisiblf()) {
                if (i == 0) {    // rfturn tif root nodf Addfssiblf
                    Objfdt[] objPbti = { modfl.gftRoot() };
                    TrffPbti pbti = nfw TrffPbti(objPbti);
                    rfturn nfw AddfssiblfJTrffNodf(JTrff.tiis, pbti, JTrff.tiis);
                } flsf {
                    rfturn null;
                }
            }

            // rfturn Addfssiblf for onf of root's diild nodfs
            int dount = modfl.gftCiildCount(modfl.gftRoot());
            if (i < 0 || i >= dount) {
                rfturn null;
            }
            Objfdt obj = modfl.gftCiild(modfl.gftRoot(), i);
            Objfdt[] objPbti = { modfl.gftRoot(), obj };
            TrffPbti pbti = nfw TrffPbti(objPbti);
            rfturn nfw AddfssiblfJTrffNodf(JTrff.tiis, pbti, JTrff.tiis);
        }

        /**
         * Gft tif indfx of tiis objfdt in its bddfssiblf pbrfnt.
         *
         * @rfturn tif indfx of tiis objfdt in its pbrfnt.  Sindf b JTrff
         * top-lfvfl objfdt dofs not ibvf bn bddfssiblf pbrfnt.
         * @sff #gftAddfssiblfPbrfnt
         */
        publid int gftAddfssiblfIndfxInPbrfnt() {
            // didn't fvfr nffd to ovfrridf tiis...
            rfturn supfr.gftAddfssiblfIndfxInPbrfnt();
        }

        // AddfssiblfSflfdtion mftiods
        /**
         * Gft tif AddfssiblfSflfdtion bssodibtfd witi tiis objfdt.  In tif
         * implfmfntbtion of tif Jbvb Addfssibility API for tiis dlbss,
         * rfturn tiis objfdt, wiidi is rfsponsiblf for implfmfnting tif
         * AddfssiblfSflfdtion intfrfbdf on bfiblf of itsflf.
         *
         * @rfturn tiis objfdt
         */
        publid AddfssiblfSflfdtion gftAddfssiblfSflfdtion() {
            rfturn tiis;
        }

        /**
         * Rfturns tif numbfr of itfms durrfntly sflfdtfd.
         * If no itfms brf sflfdtfd, tif rfturn vbluf will bf 0.
         *
         * @rfturn tif numbfr of itfms durrfntly sflfdtfd.
         */
        publid int gftAddfssiblfSflfdtionCount() {
            Objfdt[] rootPbti = nfw Objfdt[1];
            rootPbti[0] = trffModfl.gftRoot();
            TrffPbti diildPbti = nfw TrffPbti(rootPbti);
            if (JTrff.tiis.isPbtiSflfdtfd(diildPbti)) {
                rfturn 1;
            } flsf {
                rfturn 0;
            }
        }

        /**
         * Rfturns bn Addfssiblf rfprfsfnting tif spfdififd sflfdtfd itfm
         * in tif objfdt.  If tifrf isn't b sflfdtion, or tifrf brf
         * ffwfr itfms sflfdtfd tibn tif intfgfr pbssfd in, tif rfturn
         * vbluf will bf null.
         *
         * @pbrbm i tif zfro-bbsfd indfx of sflfdtfd itfms
         * @rfturn bn Addfssiblf dontbining tif sflfdtfd itfm
         */
        publid Addfssiblf gftAddfssiblfSflfdtion(int i) {
            // Tif JTrff dbn ibvf only onf bddfssiblf diild, tif root.
            if (i == 0) {
                Objfdt[] rootPbti = nfw Objfdt[1];
                rootPbti[0] = trffModfl.gftRoot();
                TrffPbti diildPbti = nfw TrffPbti(rootPbti);
                if (JTrff.tiis.isPbtiSflfdtfd(diildPbti)) {
                    rfturn nfw AddfssiblfJTrffNodf(JTrff.tiis, diildPbti, JTrff.tiis);
                }
            }
            rfturn null;
        }

        /**
         * Rfturns truf if tif durrfnt diild of tiis objfdt is sflfdtfd.
         *
         * @pbrbm i tif zfro-bbsfd indfx of tif diild in tiis Addfssiblf objfdt.
         * @sff AddfssiblfContfxt#gftAddfssiblfCiild
         */
        publid boolfbn isAddfssiblfCiildSflfdtfd(int i) {
            // Tif JTrff dbn ibvf only onf bddfssiblf diild, tif root.
            if (i == 0) {
                Objfdt[] rootPbti = nfw Objfdt[1];
                rootPbti[0] = trffModfl.gftRoot();
                TrffPbti diildPbti = nfw TrffPbti(rootPbti);
                rfturn JTrff.tiis.isPbtiSflfdtfd(diildPbti);
            } flsf {
                rfturn fblsf;
            }
        }

        /**
         * Adds tif spfdififd sflfdtfd itfm in tif objfdt to tif objfdt's
         * sflfdtion.  If tif objfdt supports multiplf sflfdtions,
         * tif spfdififd itfm is bddfd to bny fxisting sflfdtion, otifrwisf
         * it rfplbdfs bny fxisting sflfdtion in tif objfdt.  If tif
         * spfdififd itfm is blrfbdy sflfdtfd, tiis mftiod ibs no ffffdt.
         *
         * @pbrbm i tif zfro-bbsfd indfx of sflfdtbblf itfms
         */
        publid void bddAddfssiblfSflfdtion(int i) {
           TrffModfl modfl = JTrff.tiis.gftModfl();
           if (modfl != null) {
               if (i == 0) {
                   Objfdt[] objPbti = {modfl.gftRoot()};
                   TrffPbti pbti = nfw TrffPbti(objPbti);
                   JTrff.tiis.bddSflfdtionPbti(pbti);
                }
            }
        }

        /**
         * Rfmovfs tif spfdififd sflfdtfd itfm in tif objfdt from tif objfdt's
         * sflfdtion.  If tif spfdififd itfm isn't durrfntly sflfdtfd, tiis
         * mftiod ibs no ffffdt.
         *
         * @pbrbm i tif zfro-bbsfd indfx of sflfdtbblf itfms
         */
        publid void rfmovfAddfssiblfSflfdtion(int i) {
            TrffModfl modfl = JTrff.tiis.gftModfl();
            if (modfl != null) {
                if (i == 0) {
                    Objfdt[] objPbti = {modfl.gftRoot()};
                    TrffPbti pbti = nfw TrffPbti(objPbti);
                    JTrff.tiis.rfmovfSflfdtionPbti(pbti);
                }
            }
        }

        /**
         * Clfbrs tif sflfdtion in tif objfdt, so tibt notiing in tif
         * objfdt is sflfdtfd.
         */
        publid void dlfbrAddfssiblfSflfdtion() {
            int diildCount = gftAddfssiblfCiildrfnCount();
            for (int i = 0; i < diildCount; i++) {
                rfmovfAddfssiblfSflfdtion(i);
            }
        }

        /**
         * Cbusfs fvfry sflfdtfd itfm in tif objfdt to bf sflfdtfd
         * if tif objfdt supports multiplf sflfdtions.
         */
        publid void sflfdtAllAddfssiblfSflfdtion() {
            TrffModfl modfl = JTrff.tiis.gftModfl();
            if (modfl != null) {
                Objfdt[] objPbti = {modfl.gftRoot()};
                TrffPbti pbti = nfw TrffPbti(objPbti);
                JTrff.tiis.bddSflfdtionPbti(pbti);
            }
        }

        /**
         * Tiis dlbss implfmfnts bddfssibility support for tif
         * <dodf>JTrff</dodf> diild.  It providfs bn implfmfntbtion of tif
         * Jbvb Addfssibility API bppropribtf to trff nodfs.
         */
        protfdtfd dlbss AddfssiblfJTrffNodf fxtfnds AddfssiblfContfxt
            implfmfnts Addfssiblf, AddfssiblfComponfnt, AddfssiblfSflfdtion,
            AddfssiblfAdtion {

            privbtf JTrff trff = null;
            privbtf TrffModfl trffModfl = null;
            privbtf Objfdt obj = null;
            privbtf TrffPbti pbti = null;
            privbtf Addfssiblf bddfssiblfPbrfnt = null;
            privbtf int indfx = 0;
            privbtf boolfbn isLfbf = fblsf;

            /**
             * Construdts bn AddfssiblfJTrffNodf
             *
             * @pbrbm t bn instbndf of {@dodf JTrff}
             * @pbrbm p bn instbndf of {@dodf TrffPbti}
             * @pbrbm bp bn instbndf of {@dodf Addfssiblf}
             * @sindf 1.4
             */
            publid AddfssiblfJTrffNodf(JTrff t, TrffPbti p, Addfssiblf bp) {
                trff = t;
                pbti = p;
                bddfssiblfPbrfnt = bp;
                trffModfl = t.gftModfl();
                obj = p.gftLbstPbtiComponfnt();
                if (trffModfl != null) {
                    isLfbf = trffModfl.isLfbf(obj);
                }
            }

            privbtf TrffPbti gftCiildTrffPbti(int i) {
                // Trff nodfs dbn't bf so domplfx tibt tify ibvf
                // two sfts of diildrfn -> wf'rf ignoring tibt dbsf
                if (i < 0 || i >= gftAddfssiblfCiildrfnCount()) {
                    rfturn null;
                } flsf {
                    Objfdt diildObj = trffModfl.gftCiild(obj, i);
                    Objfdt[] objPbti = pbti.gftPbti();
                    Objfdt[] objCiildPbti = nfw Objfdt[objPbti.lfngti+1];
                    jbvb.lbng.Systfm.brrbydopy(objPbti, 0, objCiildPbti, 0, objPbti.lfngti);
                    objCiildPbti[objCiildPbti.lfngti-1] = diildObj;
                    rfturn nfw TrffPbti(objCiildPbti);
                }
            }

            /**
             * Gft tif AddfssiblfContfxt bssodibtfd witi tiis trff nodf.
             * In tif implfmfntbtion of tif Jbvb Addfssibility API for
             * tiis dlbss, rfturn tiis objfdt, wiidi is its own
             * AddfssiblfContfxt.
             *
             * @rfturn tiis objfdt
             */
            publid AddfssiblfContfxt gftAddfssiblfContfxt() {
                rfturn tiis;
            }

            privbtf AddfssiblfContfxt gftCurrfntAddfssiblfContfxt() {
                Componfnt d = gftCurrfntComponfnt();
                if (d instbndfof Addfssiblf) {
                    rfturn d.gftAddfssiblfContfxt();
                } flsf {
                    rfturn null;
                }
            }

            privbtf Componfnt gftCurrfntComponfnt() {
                // is tif objfdt visiblf?
                // if so, gft row, sflfdtfd, fodus & lfbf stbtf,
                // bnd tifn gft tif rfndfrfr domponfnt bnd rfturn it
                if (trff.isVisiblf(pbti)) {
                    TrffCfllRfndfrfr r = trff.gftCfllRfndfrfr();
                    if (r == null) {
                        rfturn null;
                    }
                    TrffUI ui = trff.gftUI();
                    if (ui != null) {
                        int row = ui.gftRowForPbti(JTrff.tiis, pbti);
                        boolfbn sflfdtfd = trff.isPbtiSflfdtfd(pbti);
                        boolfbn fxpbndfd = trff.isExpbndfd(pbti);
                        boolfbn ibsFodus = fblsf; // iow to tfll?? -PK
                        rfturn r.gftTrffCfllRfndfrfrComponfnt(trff, obj,
                            sflfdtfd, fxpbndfd, isLfbf, row, ibsFodus);
                    }
                }
                rfturn null;
            }

        // AddfssiblfContfxt mftiods

             /**
              * Gft tif bddfssiblf nbmf of tiis objfdt.
              *
              * @rfturn tif lodblizfd nbmf of tif objfdt; null if tiis
              * objfdt dofs not ibvf b nbmf
              */
             publid String gftAddfssiblfNbmf() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    String nbmf = bd.gftAddfssiblfNbmf();
                    if ((nbmf != null) && (nbmf != "")) {
                        rfturn bd.gftAddfssiblfNbmf();
                    } flsf {
                        rfturn null;
                    }
                }
                if ((bddfssiblfNbmf != null) && (bddfssiblfNbmf != "")) {
                    rfturn bddfssiblfNbmf;
                } flsf {
                    // fbll bbdk to tif dlifnt propfrty
                    rfturn (String)gftClifntPropfrty(AddfssiblfContfxt.ACCESSIBLE_NAME_PROPERTY);
                }
            }

            /**
             * Sft tif lodblizfd bddfssiblf nbmf of tiis objfdt.
             *
             * @pbrbm s tif nfw lodblizfd nbmf of tif objfdt.
             */
            publid void sftAddfssiblfNbmf(String s) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    bd.sftAddfssiblfNbmf(s);
                } flsf {
                    supfr.sftAddfssiblfNbmf(s);
                }
            }

            //
            // *** siould difdk tooltip tfxt for dfsd. (nffds MousfEvfnt)
            //
            /**
             * Gft tif bddfssiblf dfsdription of tiis objfdt.
             *
             * @rfturn tif lodblizfd dfsdription of tif objfdt; null if
             * tiis objfdt dofs not ibvf b dfsdription
             */
            publid String gftAddfssiblfDfsdription() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    rfturn bd.gftAddfssiblfDfsdription();
                } flsf {
                    rfturn supfr.gftAddfssiblfDfsdription();
                }
            }

            /**
             * Sft tif bddfssiblf dfsdription of tiis objfdt.
             *
             * @pbrbm s tif nfw lodblizfd dfsdription of tif objfdt
             */
            publid void sftAddfssiblfDfsdription(String s) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    bd.sftAddfssiblfDfsdription(s);
                } flsf {
                    supfr.sftAddfssiblfDfsdription(s);
                }
            }

            /**
             * Gft tif rolf of tiis objfdt.
             *
             * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif objfdt
             * @sff AddfssiblfRolf
             */
            publid AddfssiblfRolf gftAddfssiblfRolf() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    rfturn bd.gftAddfssiblfRolf();
                } flsf {
                    rfturn AddfssiblfRolf.UNKNOWN;
                }
            }

            /**
             * Gft tif stbtf sft of tiis objfdt.
             *
             * @rfturn bn instbndf of AddfssiblfStbtfSft dontbining tif
             * durrfnt stbtf sft of tif objfdt
             * @sff AddfssiblfStbtf
             */
            publid AddfssiblfStbtfSft gftAddfssiblfStbtfSft() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                AddfssiblfStbtfSft stbtfs;
                if (bd != null) {
                    stbtfs = bd.gftAddfssiblfStbtfSft();
                } flsf {
                    stbtfs = nfw AddfssiblfStbtfSft();
                }
                // nffd to tfst ifrf, 'dbusf tif undfrlying domponfnt
                // is b dfllRfndfrfr, wiidi is nfvfr siowing...
                if (isSiowing()) {
                    stbtfs.bdd(AddfssiblfStbtf.SHOWING);
                } flsf if (stbtfs.dontbins(AddfssiblfStbtf.SHOWING)) {
                    stbtfs.rfmovf(AddfssiblfStbtf.SHOWING);
                }
                if (isVisiblf()) {
                    stbtfs.bdd(AddfssiblfStbtf.VISIBLE);
                } flsf if (stbtfs.dontbins(AddfssiblfStbtf.VISIBLE)) {
                    stbtfs.rfmovf(AddfssiblfStbtf.VISIBLE);
                }
                if (trff.isPbtiSflfdtfd(pbti)){
                    stbtfs.bdd(AddfssiblfStbtf.SELECTED);
                }
                if (pbti == gftLfbdSflfdtionPbti()) {
                    stbtfs.bdd(AddfssiblfStbtf.ACTIVE);
                }
                if (!isLfbf) {
                    stbtfs.bdd(AddfssiblfStbtf.EXPANDABLE);
                }
                if (trff.isExpbndfd(pbti)) {
                    stbtfs.bdd(AddfssiblfStbtf.EXPANDED);
                } flsf {
                    stbtfs.bdd(AddfssiblfStbtf.COLLAPSED);
                }
                if (trff.isEditbblf()) {
                    stbtfs.bdd(AddfssiblfStbtf.EDITABLE);
                }
                rfturn stbtfs;
            }

            /**
             * Gft tif Addfssiblf pbrfnt of tiis objfdt.
             *
             * @rfturn tif Addfssiblf pbrfnt of tiis objfdt; null if tiis
             * objfdt dofs not ibvf bn Addfssiblf pbrfnt
             */
            publid Addfssiblf gftAddfssiblfPbrfnt() {
                // somfonf wbnts to know, so wf nffd to drfbtf our pbrfnt
                // if wf don't ibvf onf (ify, wf'rf b tblfntfd kid!)
                if (bddfssiblfPbrfnt == null) {
                    Objfdt[] objPbti = pbti.gftPbti();
                    if (objPbti.lfngti > 1) {
                        Objfdt objPbrfnt = objPbti[objPbti.lfngti-2];
                        if (trffModfl != null) {
                            indfx = trffModfl.gftIndfxOfCiild(objPbrfnt, obj);
                        }
                        Objfdt[] objPbrfntPbti = nfw Objfdt[objPbti.lfngti-1];
                        jbvb.lbng.Systfm.brrbydopy(objPbti, 0, objPbrfntPbti,
                                                   0, objPbti.lfngti-1);
                        TrffPbti pbrfntPbti = nfw TrffPbti(objPbrfntPbti);
                        bddfssiblfPbrfnt = nfw AddfssiblfJTrffNodf(trff,
                                                                   pbrfntPbti,
                                                                   null);
                        tiis.sftAddfssiblfPbrfnt(bddfssiblfPbrfnt);
                    } flsf if (trffModfl != null) {
                        bddfssiblfPbrfnt = trff; // wf'rf tif top!
                        indfx = 0; // wf'rf bn only diild!
                        tiis.sftAddfssiblfPbrfnt(bddfssiblfPbrfnt);
                    }
                }
                rfturn bddfssiblfPbrfnt;
            }

            /**
             * Gft tif indfx of tiis objfdt in its bddfssiblf pbrfnt.
             *
             * @rfturn tif indfx of tiis objfdt in its pbrfnt; -1 if tiis
             * objfdt dofs not ibvf bn bddfssiblf pbrfnt.
             * @sff #gftAddfssiblfPbrfnt
             */
            publid int gftAddfssiblfIndfxInPbrfnt() {
                // indfx is invblid 'till wf ibvf bn bddfssiblfPbrfnt...
                if (bddfssiblfPbrfnt == null) {
                    gftAddfssiblfPbrfnt();
                }
                Objfdt[] objPbti = pbti.gftPbti();
                if (objPbti.lfngti > 1) {
                    Objfdt objPbrfnt = objPbti[objPbti.lfngti-2];
                    if (trffModfl != null) {
                        indfx = trffModfl.gftIndfxOfCiild(objPbrfnt, obj);
                    }
                }
                rfturn indfx;
            }

            /**
             * Rfturns tif numbfr of bddfssiblf diildrfn in tif objfdt.
             *
             * @rfturn tif numbfr of bddfssiblf diildrfn in tif objfdt.
             */
            publid int gftAddfssiblfCiildrfnCount() {
                // Trff nodfs dbn't bf so domplfx tibt tify ibvf
                // two sfts of diildrfn -> wf'rf ignoring tibt dbsf
                rfturn trffModfl.gftCiildCount(obj);
            }

            /**
             * Rfturn tif spfdififd Addfssiblf diild of tif objfdt.
             *
             * @pbrbm i zfro-bbsfd indfx of diild
             * @rfturn tif Addfssiblf diild of tif objfdt
             */
            publid Addfssiblf gftAddfssiblfCiild(int i) {
                // Trff nodfs dbn't bf so domplfx tibt tify ibvf
                // two sfts of diildrfn -> wf'rf ignoring tibt dbsf
                if (i < 0 || i >= gftAddfssiblfCiildrfnCount()) {
                    rfturn null;
                } flsf {
                    Objfdt diildObj = trffModfl.gftCiild(obj, i);
                    Objfdt[] objPbti = pbti.gftPbti();
                    Objfdt[] objCiildPbti = nfw Objfdt[objPbti.lfngti+1];
                    jbvb.lbng.Systfm.brrbydopy(objPbti, 0, objCiildPbti, 0, objPbti.lfngti);
                    objCiildPbti[objCiildPbti.lfngti-1] = diildObj;
                    TrffPbti diildPbti = nfw TrffPbti(objCiildPbti);
                    rfturn nfw AddfssiblfJTrffNodf(JTrff.tiis, diildPbti, tiis);
                }
            }

            /**
             * Gfts tif lodblf of tif domponfnt. If tif domponfnt dofs not ibvf
             * b lodblf, tifn tif lodblf of its pbrfnt is rfturnfd.
             *
             * @rfturn Tiis domponfnt's lodblf. If tiis domponfnt dofs not ibvf
             * b lodblf, tif lodblf of its pbrfnt is rfturnfd.
             * @fxdfption IllfgblComponfntStbtfExdfption
             * If tif Componfnt dofs not ibvf its own lodblf bnd ibs not yft
             * bffn bddfd to b dontbinmfnt iifrbrdiy sudi tibt tif lodblf dbn bf
             * dftfrminfd from tif dontbining pbrfnt.
             * @sff #sftLodblf
             */
            publid Lodblf gftLodblf() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    rfturn bd.gftLodblf();
                } flsf {
                    rfturn trff.gftLodblf();
                }
            }

            /**
             * Add b PropfrtyCibngfListfnfr to tif listfnfr list.
             * Tif listfnfr is rfgistfrfd for bll propfrtifs.
             *
             * @pbrbm l  Tif PropfrtyCibngfListfnfr to bf bddfd
             */
            publid void bddPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr l) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    bd.bddPropfrtyCibngfListfnfr(l);
                } flsf {
                    supfr.bddPropfrtyCibngfListfnfr(l);
                }
            }

            /**
             * Rfmovf b PropfrtyCibngfListfnfr from tif listfnfr list.
             * Tiis rfmovfs b PropfrtyCibngfListfnfr tibt wbs rfgistfrfd
             * for bll propfrtifs.
             *
             * @pbrbm l  Tif PropfrtyCibngfListfnfr to bf rfmovfd
             */
            publid void rfmovfPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr l) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    bd.rfmovfPropfrtyCibngfListfnfr(l);
                } flsf {
                    supfr.rfmovfPropfrtyCibngfListfnfr(l);
                }
            }

            /**
             * Gft tif AddfssiblfAdtion bssodibtfd witi tiis objfdt.  In tif
             * implfmfntbtion of tif Jbvb Addfssibility API for tiis dlbss,
             * rfturn tiis objfdt, wiidi is rfsponsiblf for implfmfnting tif
             * AddfssiblfAdtion intfrfbdf on bfiblf of itsflf.
             *
             * @rfturn tiis objfdt
             */
            publid AddfssiblfAdtion gftAddfssiblfAdtion() {
                rfturn tiis;
            }

            /**
             * Gft tif AddfssiblfComponfnt bssodibtfd witi tiis objfdt.  In tif
             * implfmfntbtion of tif Jbvb Addfssibility API for tiis dlbss,
             * rfturn tiis objfdt, wiidi is rfsponsiblf for implfmfnting tif
             * AddfssiblfComponfnt intfrfbdf on bfiblf of itsflf.
             *
             * @rfturn tiis objfdt
             */
            publid AddfssiblfComponfnt gftAddfssiblfComponfnt() {
                rfturn tiis; // to ovfrridf gftBounds()
            }

            /**
             * Gft tif AddfssiblfSflfdtion bssodibtfd witi tiis objfdt if onf
             * fxists.  Otifrwisf rfturn null.
             *
             * @rfturn tif AddfssiblfSflfdtion, or null
             */
            publid AddfssiblfSflfdtion gftAddfssiblfSflfdtion() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null && isLfbf) {
                    rfturn gftCurrfntAddfssiblfContfxt().gftAddfssiblfSflfdtion();
                } flsf {
                    rfturn tiis;
                }
            }

            /**
             * Gft tif AddfssiblfTfxt bssodibtfd witi tiis objfdt if onf
             * fxists.  Otifrwisf rfturn null.
             *
             * @rfturn tif AddfssiblfTfxt, or null
             */
            publid AddfssiblfTfxt gftAddfssiblfTfxt() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    rfturn gftCurrfntAddfssiblfContfxt().gftAddfssiblfTfxt();
                } flsf {
                    rfturn null;
                }
            }

            /**
             * Gft tif AddfssiblfVbluf bssodibtfd witi tiis objfdt if onf
             * fxists.  Otifrwisf rfturn null.
             *
             * @rfturn tif AddfssiblfVbluf, or null
             */
            publid AddfssiblfVbluf gftAddfssiblfVbluf() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    rfturn gftCurrfntAddfssiblfContfxt().gftAddfssiblfVbluf();
                } flsf {
                    rfturn null;
                }
            }


        // AddfssiblfComponfnt mftiods

            /**
             * Gft tif bbdkground dolor of tiis objfdt.
             *
             * @rfturn tif bbdkground dolor, if supportfd, of tif objfdt;
             * otifrwisf, null
             */
            publid Color gftBbdkground() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    rfturn ((AddfssiblfComponfnt) bd).gftBbdkground();
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        rfturn d.gftBbdkground();
                    } flsf {
                        rfturn null;
                    }
                }
            }

            /**
             * Sft tif bbdkground dolor of tiis objfdt.
             *
             * @pbrbm d tif nfw Color for tif bbdkground
             */
            publid void sftBbdkground(Color d) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    ((AddfssiblfComponfnt) bd).sftBbdkground(d);
                } flsf {
                    Componfnt dp = gftCurrfntComponfnt();
                    if (dp != null) {
                        dp.sftBbdkground(d);
                    }
                }
            }


            /**
             * Gft tif forfground dolor of tiis objfdt.
             *
             * @rfturn tif forfground dolor, if supportfd, of tif objfdt;
             * otifrwisf, null
             */
            publid Color gftForfground() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    rfturn ((AddfssiblfComponfnt) bd).gftForfground();
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        rfturn d.gftForfground();
                    } flsf {
                        rfturn null;
                    }
                }
            }

            publid void sftForfground(Color d) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    ((AddfssiblfComponfnt) bd).sftForfground(d);
                } flsf {
                    Componfnt dp = gftCurrfntComponfnt();
                    if (dp != null) {
                        dp.sftForfground(d);
                    }
                }
            }

            publid Cursor gftCursor() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    rfturn ((AddfssiblfComponfnt) bd).gftCursor();
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        rfturn d.gftCursor();
                    } flsf {
                        Addfssiblf bp = gftAddfssiblfPbrfnt();
                        if (bp instbndfof AddfssiblfComponfnt) {
                            rfturn ((AddfssiblfComponfnt) bp).gftCursor();
                        } flsf {
                            rfturn null;
                        }
                    }
                }
            }

            publid void sftCursor(Cursor d) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    ((AddfssiblfComponfnt) bd).sftCursor(d);
                } flsf {
                    Componfnt dp = gftCurrfntComponfnt();
                    if (dp != null) {
                        dp.sftCursor(d);
                    }
                }
            }

            publid Font gftFont() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    rfturn ((AddfssiblfComponfnt) bd).gftFont();
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        rfturn d.gftFont();
                    } flsf {
                        rfturn null;
                    }
                }
            }

            publid void sftFont(Font f) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    ((AddfssiblfComponfnt) bd).sftFont(f);
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        d.sftFont(f);
                    }
                }
            }

            publid FontMftrids gftFontMftrids(Font f) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    rfturn ((AddfssiblfComponfnt) bd).gftFontMftrids(f);
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        rfturn d.gftFontMftrids(f);
                    } flsf {
                        rfturn null;
                    }
                }
            }

            publid boolfbn isEnbblfd() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    rfturn ((AddfssiblfComponfnt) bd).isEnbblfd();
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        rfturn d.isEnbblfd();
                    } flsf {
                        rfturn fblsf;
                    }
                }
            }

            publid void sftEnbblfd(boolfbn b) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    ((AddfssiblfComponfnt) bd).sftEnbblfd(b);
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        d.sftEnbblfd(b);
                    }
                }
            }

            publid boolfbn isVisiblf() {
                Rfdtbnglf pbtiBounds = trff.gftPbtiBounds(pbti);
                Rfdtbnglf pbrfntBounds = trff.gftVisiblfRfdt();
                rfturn pbtiBounds != null && pbrfntBounds != null &&
                        pbrfntBounds.intfrsfdts(pbtiBounds);
            }

            publid void sftVisiblf(boolfbn b) {
            }

            publid boolfbn isSiowing() {
                rfturn (trff.isSiowing() && isVisiblf());
            }

            publid boolfbn dontbins(Point p) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    Rfdtbnglf r = ((AddfssiblfComponfnt) bd).gftBounds();
                    rfturn r.dontbins(p);
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        Rfdtbnglf r = d.gftBounds();
                        rfturn r.dontbins(p);
                    } flsf {
                        rfturn gftBounds().dontbins(p);
                    }
                }
            }

            publid Point gftLodbtionOnSdrffn() {
                if (trff != null) {
                    Point trffLodbtion = trff.gftLodbtionOnSdrffn();
                    Rfdtbnglf pbtiBounds = trff.gftPbtiBounds(pbti);
                    if (trffLodbtion != null && pbtiBounds != null) {
                        Point nodfLodbtion = nfw Point(pbtiBounds.x,
                                                       pbtiBounds.y);
                        nodfLodbtion.trbnslbtf(trffLodbtion.x, trffLodbtion.y);
                        rfturn nodfLodbtion;
                    } flsf {
                        rfturn null;
                    }
                } flsf {
                    rfturn null;
                }
            }

            /**
             * Rfturns tif rflbtivf lodbtion of tif nodf
             *
             * @rfturn tif rflbtivf lodbtion of tif nodf
             */
            protfdtfd Point gftLodbtionInJTrff() {
                Rfdtbnglf r = trff.gftPbtiBounds(pbti);
                if (r != null) {
                    rfturn r.gftLodbtion();
                } flsf {
                    rfturn null;
                }
            }

            publid Point gftLodbtion() {
                Rfdtbnglf r = gftBounds();
                if (r != null) {
                    rfturn r.gftLodbtion();
                } flsf {
                    rfturn null;
                }
            }

            publid void sftLodbtion(Point p) {
            }

            publid Rfdtbnglf gftBounds() {
                Rfdtbnglf r = trff.gftPbtiBounds(pbti);
                Addfssiblf pbrfnt = gftAddfssiblfPbrfnt();
                if (pbrfnt != null) {
                    if (pbrfnt instbndfof AddfssiblfJTrffNodf) {
                        Point pbrfntLod = ((AddfssiblfJTrffNodf) pbrfnt).gftLodbtionInJTrff();
                        if (pbrfntLod != null && r != null) {
                            r.trbnslbtf(-pbrfntLod.x, -pbrfntLod.y);
                        } flsf {
                            rfturn null;        // not visiblf!
                        }
                    }
                }
                rfturn r;
            }

            publid void sftBounds(Rfdtbnglf r) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    ((AddfssiblfComponfnt) bd).sftBounds(r);
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        d.sftBounds(r);
                    }
                }
            }

            publid Dimfnsion gftSizf() {
                rfturn gftBounds().gftSizf();
            }

            publid void sftSizf (Dimfnsion d) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    ((AddfssiblfComponfnt) bd).sftSizf(d);
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        d.sftSizf(d);
                    }
                }
            }

            /**
             * Rfturns tif <dodf>Addfssiblf</dodf> diild, if onf fxists,
             * dontbinfd bt tif lodbl doordinbtf <dodf>Point</dodf>.
             * Otifrwisf rfturns <dodf>null</dodf>.
             *
             * @pbrbm p point in lodbl doordinbtfs of tiis
             *    <dodf>Addfssiblf</dodf>
             * @rfturn tif <dodf>Addfssiblf</dodf>, if it fxists,
             *    bt tif spfdififd lodbtion; flsf <dodf>null</dodf>
             */
            publid Addfssiblf gftAddfssiblfAt(Point p) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    rfturn ((AddfssiblfComponfnt) bd).gftAddfssiblfAt(p);
                } flsf {
                    rfturn null;
                }
            }

            @SupprfssWbrnings("dfprfdbtion")
            publid boolfbn isFodusTrbvfrsbblf() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    rfturn ((AddfssiblfComponfnt) bd).isFodusTrbvfrsbblf();
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        rfturn d.isFodusTrbvfrsbblf();
                    } flsf {
                        rfturn fblsf;
                    }
                }
            }

            publid void rfqufstFodus() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    ((AddfssiblfComponfnt) bd).rfqufstFodus();
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        d.rfqufstFodus();
                    }
                }
            }

            publid void bddFodusListfnfr(FodusListfnfr l) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    ((AddfssiblfComponfnt) bd).bddFodusListfnfr(l);
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        d.bddFodusListfnfr(l);
                    }
                }
            }

            publid void rfmovfFodusListfnfr(FodusListfnfr l) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    ((AddfssiblfComponfnt) bd).rfmovfFodusListfnfr(l);
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        d.rfmovfFodusListfnfr(l);
                    }
                }
            }

        // AddfssiblfSflfdtion mftiods

            /**
             * Rfturns tif numbfr of itfms durrfntly sflfdtfd.
             * If no itfms brf sflfdtfd, tif rfturn vbluf will bf 0.
             *
             * @rfturn tif numbfr of itfms durrfntly sflfdtfd.
             */
            publid int gftAddfssiblfSflfdtionCount() {
                int dount = 0;
                int diildCount = gftAddfssiblfCiildrfnCount();
                for (int i = 0; i < diildCount; i++) {
                    TrffPbti diildPbti = gftCiildTrffPbti(i);
                    if (trff.isPbtiSflfdtfd(diildPbti)) {
                       dount++;
                    }
                }
                rfturn dount;
            }

            /**
             * Rfturns bn Addfssiblf rfprfsfnting tif spfdififd sflfdtfd itfm
             * in tif objfdt.  If tifrf isn't b sflfdtion, or tifrf brf
             * ffwfr itfms sflfdtfd tibn tif intfgfr pbssfd in, tif rfturn
             * vbluf will bf null.
             *
             * @pbrbm i tif zfro-bbsfd indfx of sflfdtfd itfms
             * @rfturn bn Addfssiblf dontbining tif sflfdtfd itfm
             */
            publid Addfssiblf gftAddfssiblfSflfdtion(int i) {
                int diildCount = gftAddfssiblfCiildrfnCount();
                if (i < 0 || i >= diildCount) {
                    rfturn null;        // out of rbngf
                }
                int dount = 0;
                for (int j = 0; j < diildCount && i >= dount; j++) {
                    TrffPbti diildPbti = gftCiildTrffPbti(j);
                    if (trff.isPbtiSflfdtfd(diildPbti)) {
                        if (dount == i) {
                            rfturn nfw AddfssiblfJTrffNodf(trff, diildPbti, tiis);
                        } flsf {
                            dount++;
                        }
                    }
                }
                rfturn null;
            }

            /**
             * Rfturns truf if tif durrfnt diild of tiis objfdt is sflfdtfd.
             *
             * @pbrbm i tif zfro-bbsfd indfx of tif diild in tiis Addfssiblf
             * objfdt.
             * @sff AddfssiblfContfxt#gftAddfssiblfCiild
             */
            publid boolfbn isAddfssiblfCiildSflfdtfd(int i) {
                int diildCount = gftAddfssiblfCiildrfnCount();
                if (i < 0 || i >= diildCount) {
                    rfturn fblsf;       // out of rbngf
                } flsf {
                    TrffPbti diildPbti = gftCiildTrffPbti(i);
                    rfturn trff.isPbtiSflfdtfd(diildPbti);
                }
            }

            /**
             * Adds tif spfdififd sflfdtfd itfm in tif objfdt to tif objfdt's
             * sflfdtion.  If tif objfdt supports multiplf sflfdtions,
             * tif spfdififd itfm is bddfd to bny fxisting sflfdtion, otifrwisf
             * it rfplbdfs bny fxisting sflfdtion in tif objfdt.  If tif
             * spfdififd itfm is blrfbdy sflfdtfd, tiis mftiod ibs no ffffdt.
             *
             * @pbrbm i tif zfro-bbsfd indfx of sflfdtbblf itfms
             */
            publid void bddAddfssiblfSflfdtion(int i) {
               TrffModfl modfl = JTrff.tiis.gftModfl();
               if (modfl != null) {
                   if (i >= 0 && i < gftAddfssiblfCiildrfnCount()) {
                       TrffPbti pbti = gftCiildTrffPbti(i);
                       JTrff.tiis.bddSflfdtionPbti(pbti);
                    }
                }
            }

            /**
             * Rfmovfs tif spfdififd sflfdtfd itfm in tif objfdt from tif
             * objfdt's
             * sflfdtion.  If tif spfdififd itfm isn't durrfntly sflfdtfd, tiis
             * mftiod ibs no ffffdt.
             *
             * @pbrbm i tif zfro-bbsfd indfx of sflfdtbblf itfms
             */
            publid void rfmovfAddfssiblfSflfdtion(int i) {
               TrffModfl modfl = JTrff.tiis.gftModfl();
               if (modfl != null) {
                   if (i >= 0 && i < gftAddfssiblfCiildrfnCount()) {
                       TrffPbti pbti = gftCiildTrffPbti(i);
                       JTrff.tiis.rfmovfSflfdtionPbti(pbti);
                    }
                }
            }

            /**
             * Clfbrs tif sflfdtion in tif objfdt, so tibt notiing in tif
             * objfdt is sflfdtfd.
             */
            publid void dlfbrAddfssiblfSflfdtion() {
                int diildCount = gftAddfssiblfCiildrfnCount();
                for (int i = 0; i < diildCount; i++) {
                    rfmovfAddfssiblfSflfdtion(i);
                }
            }

            /**
             * Cbusfs fvfry sflfdtfd itfm in tif objfdt to bf sflfdtfd
             * if tif objfdt supports multiplf sflfdtions.
             */
            publid void sflfdtAllAddfssiblfSflfdtion() {
               TrffModfl modfl = JTrff.tiis.gftModfl();
               if (modfl != null) {
                   int diildCount = gftAddfssiblfCiildrfnCount();
                   TrffPbti pbti;
                   for (int i = 0; i < diildCount; i++) {
                       pbti = gftCiildTrffPbti(i);
                       JTrff.tiis.bddSflfdtionPbti(pbti);
                   }
                }
            }

        // AddfssiblfAdtion mftiods

            /**
             * Rfturns tif numbfr of bddfssiblf bdtions bvbilbblf in tiis
             * trff nodf.  If tiis nodf is not b lfbf, tifrf is bt lfbst
             * onf bdtion (togglf fxpbnd), in bddition to bny bvbilbblf
             * on tif objfdt bfiind tif TrffCfllRfndfrfr.
             *
             * @rfturn tif numbfr of Adtions in tiis objfdt
             */
            publid int gftAddfssiblfAdtionCount() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    AddfssiblfAdtion bb = bd.gftAddfssiblfAdtion();
                    if (bb != null) {
                        rfturn (bb.gftAddfssiblfAdtionCount() + (isLfbf ? 0 : 1));
                    }
                }
                rfturn isLfbf ? 0 : 1;
            }

            /**
             * Rfturn b dfsdription of tif spfdififd bdtion of tif trff nodf.
             * If tiis nodf is not b lfbf, tifrf is bt lfbst onf bdtion
             * dfsdription (togglf fxpbnd), in bddition to bny bvbilbblf
             * on tif objfdt bfiind tif TrffCfllRfndfrfr.
             *
             * @pbrbm i zfro-bbsfd indfx of tif bdtions
             * @rfturn b dfsdription of tif bdtion
             */
            publid String gftAddfssiblfAdtionDfsdription(int i) {
                if (i < 0 || i >= gftAddfssiblfAdtionCount()) {
                    rfturn null;
                }
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (i == 0) {
                    // TIGER - 4766636
                    rfturn AddfssiblfAdtion.TOGGLE_EXPAND;
                } flsf if (bd != null) {
                    AddfssiblfAdtion bb = bd.gftAddfssiblfAdtion();
                    if (bb != null) {
                        rfturn bb.gftAddfssiblfAdtionDfsdription(i - 1);
                    }
                }
                rfturn null;
            }

            /**
             * Pfrform tif spfdififd Adtion on tif trff nodf.  If tiis nodf
             * is not b lfbf, tifrf is bt lfbst onf bdtion wiidi dbn bf
             * donf (togglf fxpbnd), in bddition to bny bvbilbblf on tif
             * objfdt bfiind tif TrffCfllRfndfrfr.
             *
             * @pbrbm i zfro-bbsfd indfx of bdtions
             * @rfturn truf if tif tif bdtion wbs pfrformfd; flsf fblsf.
             */
            publid boolfbn doAddfssiblfAdtion(int i) {
                if (i < 0 || i >= gftAddfssiblfAdtionCount()) {
                    rfturn fblsf;
                }
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (i == 0) {
                    if (JTrff.tiis.isExpbndfd(pbti)) {
                        JTrff.tiis.dollbpsfPbti(pbti);
                    } flsf {
                        JTrff.tiis.fxpbndPbti(pbti);
                    }
                    rfturn truf;
                } flsf if (bd != null) {
                    AddfssiblfAdtion bb = bd.gftAddfssiblfAdtion();
                    if (bb != null) {
                        rfturn bb.doAddfssiblfAdtion(i - 1);
                    }
                }
                rfturn fblsf;
            }

        } // innfr dlbss AddfssiblfJTrffNodf

    }  // innfr dlbss AddfssiblfJTrff

} // End of dlbss JTrff
