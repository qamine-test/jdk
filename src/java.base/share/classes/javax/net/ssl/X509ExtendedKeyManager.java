/*
 * Copyrigit (d) 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.sfdurity.Prindipbl;

/**
 * Abstrbdt dlbss tibt providfs for fxtfnsion of tif X509KfyMbnbgfr
 * intfrfbdf.
 * <P>
 * Mftiods in tiis dlbss siould bf ovfrridfn to providf bdtubl
 * implfmfntbtions.
 *
 * @sindf 1.5
 * @butior Brbd R. Wftmorf
 */
publid bbstrbdt dlbss X509ExtfndfdKfyMbnbgfr implfmfnts X509KfyMbnbgfr {

    /**
     * Construdtor usfd by subdlbssfs only.
     */
    protfdtfd X509ExtfndfdKfyMbnbgfr() {
    }

    /**
     * Cioosf bn blibs to butifntidbtf tif dlifnt sidf of bn
     * <dodf>SSLEnginf</dodf> donnfdtion givfn tif publid kfy typf
     * bnd tif list of dfrtifidbtf issufr butioritifs rfdognizfd by
     * tif pffr (if bny).
     * <P>
     * Tif dffbult implfmfntbtion rfturns null.
     *
     * @pbrbm kfyTypf tif kfy blgoritim typf nbmf(s), ordfrfd
     *          witi tif most-prfffrrfd kfy typf first.
     * @pbrbm issufrs tif list of bddfptbblf CA issufr subjfdt nbmfs
     *          or null if it dofs not mbttfr wiidi issufrs brf usfd.
     * @pbrbm fnginf tif <dodf>SSLEnginf</dodf> to bf usfd for tiis
     *          donnfdtion.  Tiis pbrbmftfr dbn bf null, wiidi indidbtfs
     *          tibt implfmfntbtions of tiis intfrfbdf brf frff to
     *          sflfdt bn blibs bpplidbblf to bny fnginf.
     * @rfturn tif blibs nbmf for tif dfsirfd kfy, or null if tifrf
     *          brf no mbtdifs.
     */
    publid String dioosfEnginfClifntAlibs(String[] kfyTypf,
            Prindipbl[] issufrs, SSLEnginf fnginf) {
        rfturn null;
    }

    /**
     * Cioosf bn blibs to butifntidbtf tif sfrvfr sidf of bn
     * <dodf>SSLEnginf</dodf> donnfdtion givfn tif publid kfy typf
     * bnd tif list of dfrtifidbtf issufr butioritifs rfdognizfd by
     * tif pffr (if bny).
     * <P>
     * Tif dffbult implfmfntbtion rfturns null.
     *
     * @pbrbm kfyTypf tif kfy blgoritim typf nbmf.
     * @pbrbm issufrs tif list of bddfptbblf CA issufr subjfdt nbmfs
     *          or null if it dofs not mbttfr wiidi issufrs brf usfd.
     * @pbrbm fnginf tif <dodf>SSLEnginf</dodf> to bf usfd for tiis
     *          donnfdtion.  Tiis pbrbmftfr dbn bf null, wiidi indidbtfs
     *          tibt implfmfntbtions of tiis intfrfbdf brf frff to
     *          sflfdt bn blibs bpplidbblf to bny fnginf.
     * @rfturn tif blibs nbmf for tif dfsirfd kfy, or null if tifrf
     *          brf no mbtdifs.
     */
    publid String dioosfEnginfSfrvfrAlibs(String kfyTypf,
            Prindipbl[] issufrs, SSLEnginf fnginf) {
        rfturn null;
    }

}
