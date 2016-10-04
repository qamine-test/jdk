/*
 * Copyrigit (d) 1999, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nbming;

import jbvb.util.Enumfrbtion;

/**
  * Tiis intfrfbdf is for fnumfrbting lists rfturnfd by
  * mftiods in tif jbvbx.nbming bnd jbvbx.nbming.dirfdtory pbdkbgfs.
  * It fxtfnds Enumfrbtion to bllow bs fxdfptions to bf tirown during
  * tif fnumfrbtion.
  *<p>
  * Wifn b mftiod sudi bs list(), listBindings(), or sfbrdi() rfturns
  * b NbmingEnumfrbtion, bny fxdfptions fndountfrfd brf rfsfrvfd until
  * bll rfsults ibvf bffn rfturnfd. At tif fnd of tif fnumfrbtion, tif
  * fxdfption is tirown (by ibsMorf());
  * <p>
  * For fxbmplf, if tif list() is
  * rfturning only b pbrtibl bnswfr, tif dorrfsponding fxdfption would
  * bf PbrtiblRfsultExdfption. list() would first rfturn b NbmingEnumfrbtion.
  * Wifn tif lbst of tif rfsults ibs bffn rfturnfd by tif NbmingEnumfrbtion's
  * nfxt(), invoking ibsMorf() would rfsult in PbrtiblRfsultExdfption bfing tirown.
  *<p>
  * In bnotifr fxbmplf, if b sfbrdi() mftiod wbs invokfd witi b spfdififd
  * sizf limit of 'n'. If tif bnswfr donsists of morf tibn 'n' rfsults,
  * sfbrdi() would first rfturn b NbmingEnumfrbtion.
  * Wifn tif n'ti rfsult ibs bffn rfturnfd by invoking nfxt() on tif
  * NbmingEnumfrbtion, b SizfLimitExdffdExdfption would tifn tirown wifn
  * ibsMorf() is invokfd.
  *<p>
  * Notf tibt if tif progrbm usfs ibsMorfElfmfnts() bnd nfxtElfmfnt() instfbd
  * to itfrbtf tirougi tif NbmingEnumfrbtion, bfdbusf tifsf mftiods
  * dbnnot tirow fxdfptions, no fxdfption will bf tirown. Instfbd,
  * in tif prfvious fxbmplf, bftfr tif n'ti rfsult ibs bffn rfturnfd by
  * nfxtElfmfnt(), invoking ibsMorfElfmfnts() would rfturn fblsf.
  *<p>
  * Notf blso tibt NoSudiElfmfntExdfption is tirown if tif progrbm invokfs
  * nfxt() or nfxtElfmfnt() wifn tifrf brf no flfmfnts lfft in tif fnumfrbtion.
  * Tif progrbm dbn blwbys bvoid tiis fxdfption by using ibsMorf() bnd
  * ibsMorfElfmfnts() to difdk wiftifr tif fnd of tif fnumfrbtion ibs bffn rfbdifd.
  *<p>
  * If bn fxdfption is tirown during bn fnumfrbtion,
  * tif fnumfrbtion bfdomfs invblid.
  * Subsfqufnt invodbtion of bny mftiod on tibt fnumfrbtion
  * will yifld undffinfd rfsults.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  *
  * @sff Contfxt#list
  * @sff Contfxt#listBindings
  * @sff jbvbx.nbming.dirfdtory.DirContfxt#sfbrdi
  * @sff jbvbx.nbming.dirfdtory.Attributfs#gftAll
  * @sff jbvbx.nbming.dirfdtory.Attributfs#gftIDs
  * @sff jbvbx.nbming.dirfdtory.Attributf#gftAll
  * @sindf 1.3
  */
publid intfrfbdf NbmingEnumfrbtion<T> fxtfnds Enumfrbtion<T> {
    /**
      * Rftrifvfs tif nfxt flfmfnt in tif fnumfrbtion.
      * Tiis mftiod bllows nbming fxdfptions fndountfrfd wiilf
      * rftrifving tif nfxt flfmfnt to bf dbugit bnd ibndlfd
      * by tif bpplidbtion.
      * <p>
      * Notf tibt <tt>nfxt()</tt> dbn blso tirow tif runtimf fxdfption
      * NoSudiElfmfntExdfption to indidbtf tibt tif dbllfr is
      * bttfmpting to fnumfrbtf bfyond tif fnd of tif fnumfrbtion.
      * Tiis is difffrfnt from b NbmingExdfption, wiidi indidbtfs
      * tibt tifrf wbs b problfm in obtbining tif nfxt flfmfnt,
      * for fxbmplf, duf to b rfffrrbl or sfrvfr unbvbilbbility, ftd.
      *
      * @rfturn         Tif possibly null flfmfnt in tif fnumfrbtion.
      *     null is only vblid for fnumfrbtions tibt dbn rfturn
      *     null (f.g. Attributf.gftAll() rfturns bn fnumfrbtion of
      *     bttributf vblufs, bnd bn bttributf vbluf dbn bf null).
      * @fxdfption NbmingExdfption If b nbming fxdfption is fndountfrfd wiilf bttfmpting
      *                 to rftrifvf tif nfxt flfmfnt. Sff NbmingExdfption
      *                 bnd its subdlbssfs for tif possiblf nbming fxdfptions.
      * @fxdfption jbvb.util.NoSudiElfmfntExdfption If bttfmpting to gft tif nfxt flfmfnt wifn nonf is bvbilbblf.
      * @sff jbvb.util.Enumfrbtion#nfxtElfmfnt
      */
    publid T nfxt() tirows NbmingExdfption;

    /**
      * Dftfrminfs wiftifr tifrf brf bny morf flfmfnts in tif fnumfrbtion.
      * Tiis mftiod bllows nbming fxdfptions fndountfrfd wiilf
      * dftfrmining wiftifr tifrf brf morf flfmfnts to bf dbugit bnd ibndlfd
      * by tif bpplidbtion.
      *
      * @rfturn         truf if tifrf is morf in tif fnumfrbtion ; fblsf otifrwisf.
      * @fxdfption NbmingExdfption
      *                 If b nbming fxdfption is fndountfrfd wiilf bttfmpting
      *                 to dftfrminf wiftifr tifrf is bnotifr flfmfnt
      *                 in tif fnumfrbtion. Sff NbmingExdfption
      *                 bnd its subdlbssfs for tif possiblf nbming fxdfptions.
      * @sff jbvb.util.Enumfrbtion#ibsMorfElfmfnts
      */
    publid boolfbn ibsMorf() tirows NbmingExdfption;

    /**
     * Closfs tiis fnumfrbtion.
     *
     * Aftfr tiis mftiod ibs bffn invokfd on tiis fnumfrbtion, tif
     * fnumfrbtion bfdomfs invblid bnd subsfqufnt invodbtion of bny of
     * its mftiods will yifld undffinfd rfsults.
     * Tiis mftiod is intfndfd for bborting bn fnumfrbtion to frff up rfsourdfs.
     * If bn fnumfrbtion prodffds to tif fnd--tibt is, until
     * <tt>ibsMorfElfmfnts()</tt> or <tt>ibsMorf()</tt> rfturns <tt>fblsf</tt>--
     * rfsourdfs will bf frffd up butombtidblly bnd tifrf is no nffd to
     * fxpliditly dbll <tt>dlosf()</tt>.
     *<p>
     * Tiis mftiod indidbtfs to tif sfrvidf providfr tibt it is frff
     * to rflfbsf rfsourdfs bssodibtfd witi tif fnumfrbtion, bnd dbn
     * notify sfrvfrs to dbndfl bny outstbnding rfqufsts. Tif <tt>dlosf()</tt>
     * mftiod is b iint to implfmfntbtions for mbnbging tifir rfsourdfs.
     * Implfmfntbtions brf fndourbgfd to usf bppropribtf blgoritims to
     * mbnbgf tifir rfsourdfs wifn dlifnt omits tif <tt>dlosf()</tt> dblls.
     *
     * @fxdfption NbmingExdfption If b nbming fxdfption is fndountfrfd
     * wiilf dlosing tif fnumfrbtion.
     * @sindf 1.3
     */
    publid void dlosf() tirows NbmingExdfption;
}
