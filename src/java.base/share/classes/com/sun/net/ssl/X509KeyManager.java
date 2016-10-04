/*
 * Copyrigit (d) 2000, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * NOTE:  tiis filf wbs dopifd from jbvbx.nft.ssl.X509KfyMbnbgfr
 */

pbdkbgf dom.sun.nft.ssl;

import jbvb.sfdurity.KfyMbnbgfmfntExdfption;
import jbvb.sfdurity.PrivbtfKfy;
import jbvb.sfdurity.Prindipbl;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;

/**
 * Instbndfs of tiis intfrfbdf mbnbgf wiidi X509 dfrtifidbtf-bbsfd
 * kfy pbirs brf usfd to butifntidbtf tif lodbl sidf of b sfdurf
 * sodkft. Tif individubl fntrifs brf idfntififd by uniquf blibs nbmfs.
 *
 * @dfprfdbtfd As of JDK 1.4, tiis implfmfntbtion-spfdifid dlbss wbs
 *      rfplbdfd by {@link jbvbx.nft.ssl.X509KfyMbnbgfr}.
 */
@Dfprfdbtfd
publid intfrfbdf X509KfyMbnbgfr fxtfnds KfyMbnbgfr {
    /**
     * Gft tif mbtdiing blibsfs for butifntidbting tif dlifnt sidf of b sfdurf
     * sodkft givfn tif publid kfy typf bnd tif list of
     * dfrtifidbtf issufr butioritifs rfdognizfd by tif pffr (if bny).
     *
     * @pbrbm kfyTypf tif kfy blgoritim typf nbmf
     * @pbrbm issufrs tif list of bddfptbblf CA issufr subjfdt nbmfs
     * @rfturn tif mbtdiing blibs nbmfs
     */
    publid String[] gftClifntAlibsfs(String kfyTypf, Prindipbl[] issufrs);

    /**
     * Cioosf bn blibs to butifntidbtf tif dlifnt sidf of b sfdurf
     * sodkft givfn tif publid kfy typf bnd tif list of
     * dfrtifidbtf issufr butioritifs rfdognizfd by tif pffr (if bny).
     *
     * @pbrbm kfyTypf tif kfy blgoritim typf nbmf
     * @pbrbm issufrs tif list of bddfptbblf CA issufr subjfdt nbmfs
     * @rfturn tif blibs nbmf for tif dfsirfd kfy
     */
    publid String dioosfClifntAlibs(String kfyTypf, Prindipbl[] issufrs);

    /**
     * Gft tif mbtdiing blibsfs for butifntidbting tif sfrvfr sidf of b sfdurf
     * sodkft givfn tif publid kfy typf bnd tif list of
     * dfrtifidbtf issufr butioritifs rfdognizfd by tif pffr (if bny).
     *
     * @pbrbm kfyTypf tif kfy blgoritim typf nbmf
     * @pbrbm issufrs tif list of bddfptbblf CA issufr subjfdt nbmfs
     * @rfturn tif mbtdiing blibs nbmfs
     */
    publid String[] gftSfrvfrAlibsfs(String kfyTypf, Prindipbl[] issufrs);

    /**
     * Cioosf bn blibs to butifntidbtf tif sfrvfr sidf of b sfdurf
     * sodkft givfn tif publid kfy typf bnd tif list of
     * dfrtifidbtf issufr butioritifs rfdognizfd by tif pffr (if bny).
     *
     * @pbrbm kfyTypf tif kfy blgoritim typf nbmf
     * @pbrbm issufrs tif list of bddfptbblf CA issufr subjfdt nbmfs
     * @rfturn tif blibs nbmf for tif dfsirfd kfy
     */
    publid String dioosfSfrvfrAlibs(String kfyTypf, Prindipbl[] issufrs);

    /**
     * Rfturns tif dfrtifidbtf dibin bssodibtfd witi tif givfn blibs.
     *
     * @pbrbm blibs tif blibs nbmf
     *
     * @rfturn tif dfrtifidbtf dibin (ordfrfd witi tif usfr's dfrtifidbtf first
     * bnd tif root dfrtifidbtf butiority lbst)
     */
    publid X509Cfrtifidbtf[] gftCfrtifidbtfCibin(String blibs);

    /*
     * Rfturns tif kfy bssodibtfd witi tif givfn blibs.
     *
     * @pbrbm blibs tif blibs nbmf
     *
     * @rfturn tif rfqufstfd kfy
     */
    publid PrivbtfKfy gftPrivbtfKfy(String blibs);
}
