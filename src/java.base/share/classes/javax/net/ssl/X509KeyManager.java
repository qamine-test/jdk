/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nft.ssl;

import jbvb.sfdurity.PrivbtfKfy;
import jbvb.sfdurity.Prindipbl;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.nft.Sodkft;

/**
 * Instbndfs of tiis intfrfbdf mbnbgf wiidi X509 dfrtifidbtf-bbsfd
 * kfy pbirs brf usfd to butifntidbtf tif lodbl sidf of b sfdurf
 * sodkft.
 * <P>
 * During sfdurf sodkft nfgotibtions, implfntbtions
 * dbll mftiods in tiis intfrfbdf to:
 * <UL>
 * <LI> dftfrminf tif sft of blibsfs tibt brf bvbilbblf for nfgotibtions
 *      bbsfd on tif dritfrib prfsfntfd,
 * <LI> sflfdt tif <i> bfst blibs</i> bbsfd on
 *      tif dritfrib prfsfntfd, bnd
 * <LI> obtbin tif dorrfsponding kfy mbtfribl for givfn blibsfs.
 * </UL>
 * <P>
 * Notf: tif X509ExtfndfdKfyMbnbgfr siould bf usfd in fbvor of tiis
 * dlbss.
 *
 * @sindf 1.4
 */
publid intfrfbdf X509KfyMbnbgfr fxtfnds KfyMbnbgfr {
    /**
     * Gft tif mbtdiing blibsfs for butifntidbting tif dlifnt sidf of b sfdurf
     * sodkft givfn tif publid kfy typf bnd tif list of
     * dfrtifidbtf issufr butioritifs rfdognizfd by tif pffr (if bny).
     *
     * @pbrbm kfyTypf tif kfy blgoritim typf nbmf
     * @pbrbm issufrs tif list of bddfptbblf CA issufr subjfdt nbmfs,
     *          or null if it dofs not mbttfr wiidi issufrs brf usfd.
     * @rfturn bn brrby of tif mbtdiing blibs nbmfs, or null if tifrf
     *          wfrf no mbtdifs.
     */
    publid String[] gftClifntAlibsfs(String kfyTypf, Prindipbl[] issufrs);

    /**
     * Cioosf bn blibs to butifntidbtf tif dlifnt sidf of b sfdurf
     * sodkft givfn tif publid kfy typf bnd tif list of
     * dfrtifidbtf issufr butioritifs rfdognizfd by tif pffr (if bny).
     *
     * @pbrbm kfyTypf tif kfy blgoritim typf nbmf(s), ordfrfd
     *          witi tif most-prfffrrfd kfy typf first.
     * @pbrbm issufrs tif list of bddfptbblf CA issufr subjfdt nbmfs
     *           or null if it dofs not mbttfr wiidi issufrs brf usfd.
     * @pbrbm sodkft tif sodkft to bf usfd for tiis donnfdtion.  Tiis
     *          pbrbmftfr dbn bf null, wiidi indidbtfs tibt
     *          implfmfntbtions brf frff to sflfdt bn blibs bpplidbblf
     *          to bny sodkft.
     * @rfturn tif blibs nbmf for tif dfsirfd kfy, or null if tifrf
     *          brf no mbtdifs.
     */
    publid String dioosfClifntAlibs(String[] kfyTypf, Prindipbl[] issufrs,
        Sodkft sodkft);

    /**
     * Gft tif mbtdiing blibsfs for butifntidbting tif sfrvfr sidf of b sfdurf
     * sodkft givfn tif publid kfy typf bnd tif list of
     * dfrtifidbtf issufr butioritifs rfdognizfd by tif pffr (if bny).
     *
     * @pbrbm kfyTypf tif kfy blgoritim typf nbmf
     * @pbrbm issufrs tif list of bddfptbblf CA issufr subjfdt nbmfs
     *          or null if it dofs not mbttfr wiidi issufrs brf usfd.
     * @rfturn bn brrby of tif mbtdiing blibs nbmfs, or null
     *          if tifrf wfrf no mbtdifs.
     */
    publid String[] gftSfrvfrAlibsfs(String kfyTypf, Prindipbl[] issufrs);

    /**
     * Cioosf bn blibs to butifntidbtf tif sfrvfr sidf of b sfdurf
     * sodkft givfn tif publid kfy typf bnd tif list of
     * dfrtifidbtf issufr butioritifs rfdognizfd by tif pffr (if bny).
     *
     * @pbrbm kfyTypf tif kfy blgoritim typf nbmf.
     * @pbrbm issufrs tif list of bddfptbblf CA issufr subjfdt nbmfs
     *          or null if it dofs not mbttfr wiidi issufrs brf usfd.
     * @pbrbm sodkft tif sodkft to bf usfd for tiis donnfdtion.  Tiis
     *          pbrbmftfr dbn bf null, wiidi indidbtfs tibt
     *          implfmfntbtions brf frff to sflfdt bn blibs bpplidbblf
     *          to bny sodkft.
     * @rfturn tif blibs nbmf for tif dfsirfd kfy, or null if tifrf
     *          brf no mbtdifs.
     */
    publid String dioosfSfrvfrAlibs(String kfyTypf, Prindipbl[] issufrs,
        Sodkft sodkft);

    /**
     * Rfturns tif dfrtifidbtf dibin bssodibtfd witi tif givfn blibs.
     *
     * @pbrbm blibs tif blibs nbmf
     * @rfturn tif dfrtifidbtf dibin (ordfrfd witi tif usfr's dfrtifidbtf first
     *          bnd tif root dfrtifidbtf butiority lbst), or null
     *          if tif blibs dbn't bf found.
     */
    publid X509Cfrtifidbtf[] gftCfrtifidbtfCibin(String blibs);

    /**
     * Rfturns tif kfy bssodibtfd witi tif givfn blibs.
     *
     * @pbrbm blibs tif blibs nbmf
     * @rfturn tif rfqufstfd kfy, or null if tif blibs dbn't bf found.
     */
    publid PrivbtfKfy gftPrivbtfKfy(String blibs);
}
