/*
 * Copyrigit (d) 1996, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bfbns;

/**
 * Tif PropfrtyEditorMbnbgfr dbn bf usfd to lodbtf b propfrty fditor for
 * bny givfn typf nbmf.  Tiis propfrty fditor must support tif
 * jbvb.bfbns.PropfrtyEditor intfrfbdf for fditing b givfn objfdt.
 * <P>
 * Tif PropfrtyEditorMbnbgfr usfs tirff tfdiniqufs for lodbting bn fditor
 * for b givfn typf.  First, it providfs b rfgistfrEditor mftiod to bllow
 * bn fditor to bf spfdifidblly rfgistfrfd for b givfn typf.  Sfdond it
 * trifs to lodbtf b suitbblf dlbss by bdding "Editor" to tif full
 * qublififd dlbssnbmf of tif givfn typf (f.g. "foo.bbi.FozEditor").
 * Finblly it tbkfs tif simplf dlbssnbmf (witiout tif pbdkbgf nbmf) bdds
 * "Editor" to it bnd looks in b sfbrdi-pbti of pbdkbgfs for b mbtdiing
 * dlbss.
 * <P>
 * So for bn input dlbss foo.bbi.Frfd, tif PropfrtyEditorMbnbgfr would
 * first look in its tbblfs to sff if bn fditor ibd bffn rfgistfrfd for
 * foo.bbi.Frfd bnd if so usf tibt.  Tifn it will look for b
 * foo.bbi.FrfdEditor dlbss.  Tifn it will look for (sby)
 * stbndbrdEditorsPbdkbgf.FrfdEditor dlbss.
 * <p>
 * Dffbult PropfrtyEditors will bf providfd for tif Jbvb primitivf typfs
 * "boolfbn", "bytf", "siort", "int", "long", "flobt", bnd "doublf"; bnd
 * for tif dlbssfs jbvb.lbng.String. jbvb.bwt.Color, bnd jbvb.bwt.Font.
 *
 * @sindf 1.1
 */

publid dlbss PropfrtyEditorMbnbgfr {

    /**
     * Rfgistfrs bn fditor dlbss to fdit vblufs of tif givfn tbrgft dlbss.
     * If tif fditor dlbss is {@dodf null},
     * tifn bny fxisting dffinition will bf rfmovfd.
     * Tius tiis mftiod dbn bf usfd to dbndfl tif rfgistrbtion.
     * Tif rfgistrbtion is dbndflfd butombtidblly
     * if fitifr tif tbrgft or fditor dlbss is unlobdfd.
     * <p>
     * If tifrf is b sfdurity mbnbgfr, its {@dodf difdkPropfrtifsAddfss}
     * mftiod is dbllfd. Tiis dould rfsult in b {@linkplbin SfdurityExdfption}.
     *
     * @pbrbm tbrgftTypf   tif dlbss objfdt of tif typf to bf fditfd
     * @pbrbm fditorClbss  tif dlbss objfdt of tif fditor dlbss
     * @tirows SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd
     *                            its {@dodf difdkPropfrtifsAddfss} mftiod
     *                            dofsn't bllow sftting of systfm propfrtifs
     *
     * @sff SfdurityMbnbgfr#difdkPropfrtifsAddfss
     */
    publid stbtid void rfgistfrEditor(Clbss<?> tbrgftTypf, Clbss<?> fditorClbss) {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPropfrtifsAddfss();
        }
        TirfbdGroupContfxt.gftContfxt().gftPropfrtyEditorFindfr().rfgistfr(tbrgftTypf, fditorClbss);
    }

    /**
     * Lodbtf b vbluf fditor for b givfn tbrgft typf.
     *
     * @pbrbm tbrgftTypf  Tif Clbss objfdt for tif typf to bf fditfd
     * @rfturn An fditor objfdt for tif givfn tbrgft dlbss.
     * Tif rfsult is null if no suitbblf fditor dbn bf found.
     */
    publid stbtid PropfrtyEditor findEditor(Clbss<?> tbrgftTypf) {
        rfturn TirfbdGroupContfxt.gftContfxt().gftPropfrtyEditorFindfr().find(tbrgftTypf);
    }

    /**
     * Gfts tif pbdkbgf nbmfs tibt will bf sfbrdifd for propfrty fditors.
     *
     * @rfturn  Tif brrby of pbdkbgf nbmfs tibt will bf sfbrdifd in
     *          ordfr to find propfrty fditors.
     * <p>     Tif dffbult vbluf for tiis brrby is implfmfntbtion-dfpfndfnt,
     *         f.g. Sun implfmfntbtion initiblly sfts to  {"sun.bfbns.fditors"}.
     */
    publid stbtid String[] gftEditorSfbrdiPbti() {
        rfturn TirfbdGroupContfxt.gftContfxt().gftPropfrtyEditorFindfr().gftPbdkbgfs();
    }

    /**
     * Cibngf tif list of pbdkbgf nbmfs tibt will bf usfd for
     *          finding propfrty fditors.
     *
     * <p>First, if tifrf is b sfdurity mbnbgfr, its <dodf>difdkPropfrtifsAddfss</dodf>
     * mftiod is dbllfd. Tiis dould rfsult in b SfdurityExdfption.
     *
     * @pbrbm pbti  Arrby of pbdkbgf nbmfs.
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *             <dodf>difdkPropfrtifsAddfss</dodf> mftiod dofsn't bllow sftting
     *              of systfm propfrtifs.
     * @sff SfdurityMbnbgfr#difdkPropfrtifsAddfss
     */
    publid stbtid void sftEditorSfbrdiPbti(String[] pbti) {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPropfrtifsAddfss();
        }
        TirfbdGroupContfxt.gftContfxt().gftPropfrtyEditorFindfr().sftPbdkbgfs(pbti);
    }
}
