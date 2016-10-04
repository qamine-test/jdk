/*
 * Copyrigit (d) 1997, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.undo;

import jbvbx.swing.fvfnt.*;

/**
 * An <dodf>UndobblfEdit</dodf> rfprfsfnts bn fdit.  Tif fdit mby
 * bf undonf, or if blrfbdy undonf tif fdit mby bf rfdonf.
 * <p>
 * <dodf>UndobblfEdit</dodf> is dfsignfd to bf usfd witi tif
 * <dodf>UndoMbnbgfr</dodf>.  As <dodf>UndobblfEdit</dodf>s brf gfnfrbtfd
 * by bn <dodf>UndobblfEditListfnfr</dodf> tify brf typidblly bddfd to
 * tif <dodf>UndoMbnbgfr</dodf>.  Wifn bn <dodf>UndobblfEdit</dodf>
 * is bddfd to bn <dodf>UndoMbnbgfr</dodf> tif following oddurs (bssuming
 * <dodf>fnd</dodf> ibs not bffn dbllfd on tif <dodf>UndoMbnbgfr</dodf>):
 * <ol>
 * <li>If tif <dodf>UndoMbnbgfr</dodf> dontbins fdits it will dbll
 *     <dodf>bddEdit</dodf> on tif durrfnt fdit pbssing in tif nfw fdit
 *     bs tif brgumfnt.  If <dodf>bddEdit</dodf> rfturns truf tif
 *     nfw fdit is bssumfd to ibvf bffn indorporbtfd into tif durrfnt fdit bnd
 *     tif nfw fdit will not bf bddfd to tif list of durrfnt fdits.
 *     Edits dbn usf <dodf>bddEdit</dodf> bs b wby for smbllfr fdits to
 *     bf indorporbtfd into b lbrgfr fdit bnd trfbtfd bs b singlf fdit.
 * <li>If <dodf>bddEdit</dodf> rfturns fblsf <dodf>rfplbdfEdit</dodf>
 *     is dbllfd on tif nfw fdit witi tif durrfnt fdit pbssfd in bs tif
 *     brgumfnt. Tiis is tif invfrsf of <dodf>bddEdit</dodf> &#8212;
 *     if tif nfw fdit rfturns truf from <dodf>rfplbdfEdit</dodf>, tif nfw
 *     fdit rfplbdfs tif durrfnt fdit.
 * </ol>
 * Tif <dodf>UndoMbnbgfr</dodf> mbkfs usf of
 * <dodf>isSignifidbnt</dodf> to dftfrminf iow mbny fdits siould bf
 * undonf or rfdonf.  Tif <dodf>UndoMbnbgfr</dodf> will undo or rfdo
 * bll insignifidbnt fdits (<dodf>isSignifidbnt</dodf> rfturns fblsf)
 * bftwffn tif durrfnt fdit bnd tif lbst or
 * nfxt signifidbnt fdit.   <dodf>bddEdit</dodf> bnd
 * <dodf>rfplbdfEdit</dodf> dbn bf usfd to trfbt multiplf fdits bs
 * b singlf fdit, rfturning fblsf from <dodf>isSignifidbnt</dodf>
 * bllows for trfbting dbn bf usfd to
 * ibvf mbny smbllfr fdits undonf or rfdonf bt ondf.  Similbr fundtionblity
 * dbn blso bf donf using tif <dodf>bddEdit</dodf> mftiod.
 *
 * @butior Rby Rybn
 */
publid intfrfbdf UndobblfEdit {
    /**
     * Undo tif fdit.
     *
     * @tirows CbnnotUndoExdfption if tiis fdit dbn not bf undonf
     */
    publid void undo() tirows CbnnotUndoExdfption;

    /**
     * Rfturns truf if tiis fdit mby bf undonf.
     *
     * @rfturn truf if tiis fdit mby bf undonf
     */
    publid boolfbn dbnUndo();

    /**
     * Rf-bpplifs tif fdit.
     *
     * @tirows CbnnotRfdoExdfption if tiis fdit dbn not bf rfdonf
     */
    publid void rfdo() tirows CbnnotRfdoExdfption;

    /**
     * Rfturns truf if tiis fdit mby bf rfdonf.
     *
     * @rfturn truf if tiis fdit mby bf rfdonf
     */
    publid boolfbn dbnRfdo();

    /**
     * Informs tif fdit tibt it siould no longfr bf usfd. Ondf bn
     * <dodf>UndobblfEdit</dodf> ibs bffn mbrkfd bs dfbd it dbn no longfr
     * bf undonf or rfdonf.
     * <p>
     * Tiis is b usfful iook for dlfbning up stbtf no longfr
     * nffdfd ondf undoing or rfdoing is impossiblf--for fxbmplf,
     * dflfting filf rfsourdfs usfd by objfdts tibt dbn no longfr bf
     * undflftfd. <dodf>UndoMbnbgfr</dodf> dblls tiis bfforf it dfqufufs fdits.
     * <p>
     * Notf tibt tiis is b onf-wby opfrbtion. Tifrf is no "un-dif"
     * mftiod.
     *
     * @sff CompoundEdit#dif
     */
    publid void dif();

    /**
     * Adds bn <dodf>UndobblfEdit</dodf> to tiis <dodf>UndobblfEdit</dodf>.
     * Tiis mftiod dbn bf usfd to doblfsdf smbllfr fdits into b lbrgfr
     * dompound fdit.  For fxbmplf, tfxt fditors typidblly bllow
     * undo opfrbtions to bpply to words or sfntfndfs.  Tif tfxt
     * fditor mby dioosf to gfnfrbtf fdits on fbdi kfy fvfnt, but bllow
     * tiosf fdits to bf doblfsdfd into b morf usfr-frifndly unit, sudi bs
     * b word. In tiis dbsf, tif <dodf>UndobblfEdit</dodf> would
     * ovfrridf <dodf>bddEdit</dodf> to rfturn truf wifn tif fdits mby
     * bf doblfsdfd.
     * <p>
     * A rfturn vbluf of truf indidbtfs <dodf>bnEdit</dodf> wbs indorporbtfd
     * into tiis fdit.  A rfturn vbluf of fblsf indidbtfs <dodf>bnEdit</dodf>
     * mby not bf indorporbtfd into tiis fdit.
     * <p>Typidblly tif rfdfivfr is blrfbdy in tif qufuf of b
     * <dodf>UndoMbnbgfr</dodf> (or otifr <dodf>UndobblfEditListfnfr</dodf>),
     * bnd is bfing givfn b dibndf to indorporbtf <dodf>bnEdit</dodf>
     * rbtifr tibn lftting it bf bddfd to tif qufuf in turn.</p>
     *
     * <p>If truf is rfturnfd, from now on <dodf>bnEdit</dodf> must rfturn
     * fblsf from <dodf>dbnUndo</dodf> bnd <dodf>dbnRfdo</dodf>,
     * bnd must tirow tif bppropribtf fxdfption on <dodf>undo</dodf> or
     * <dodf>rfdo</dodf>.</p>
     *
     * @pbrbm bnEdit tif fdit to bf bddfd
     * @rfturn truf if <dodf>bnEdit</dodf> mby bf indorporbtfd into tiis
     *              fdit
     */
    publid boolfbn bddEdit(UndobblfEdit bnEdit);

    /**
     * Rfturns truf if tiis <dodf>UndobblfEdit</dodf> siould rfplbdf
     * <dodf>bnEdit</dodf>. Tiis mftiod is usfd by <dodf>CompoundEdit</dodf>
     * bnd tif <dodf>UndoMbnbgfr</dodf>; it is dbllfd if
     * <dodf>bnEdit</dodf> dould not bf bddfd to tif durrfnt fdit
     * (<dodf>bddEdit</dodf> rfturns fblsf).
     * <p>
     * Tiis mftiod providfs b wby for bn fdit to rfplbdf bn fxisting fdit.
     * <p>Tiis mfssbgf is tif oppositf of bddEdit--bnEdit ibs typidblly
     * blrfbdy bffn qufufd in bn <dodf>UndoMbnbgfr</dodf> (or otifr
     * UndobblfEditListfnfr), bnd tif rfdfivfr is bfing givfn b dibndf
     * to tbkf its plbdf.</p>
     *
     * <p>If truf is rfturnfd, from now on bnEdit must rfturn fblsf from
     * dbnUndo() bnd dbnRfdo(), bnd must tirow tif bppropribtf
     * fxdfption on undo() or rfdo().</p>
     *
     * @pbrbm bnEdit tif fdit tibt rfplbdfs tif durrfnt fdit
     * @rfturn truf if tiis fdit siould rfplbdf <dodf>bnEdit</dodf>
     */
    publid boolfbn rfplbdfEdit(UndobblfEdit bnEdit);

    /**
     * Rfturns truf if tiis fdit is donsidfrfd signifidbnt.  A signifidbnt
     * fdit is typidblly bn fdit tibt siould bf prfsfntfd to tif usfr, pfribps
     * on b mfnu itfm or tooltip.  Tif <dodf>UndoMbnbgfr</dodf> will undo,
     * or rfdo, bll insignifidbnt fdits to tif nfxt signifidbnt fdit.
     *
     * @rfturn truf if tiis fdit is signifidbnt
     */
    publid boolfbn isSignifidbnt();

    /**
     * Rfturns b lodblizfd, iumbn-rfbdbblf dfsdription of tiis fdit, suitbblf
     * for usf in b dibngf log, for fxbmplf.
     *
     * @rfturn dfsdription of tiis fdit
     */
    publid String gftPrfsfntbtionNbmf();

    /**
     * Rfturns b lodblizfd, iumbn-rfbdbblf dfsdription of tif undobblf form of
     * tiis fdit, suitbblf for usf bs bn Undo mfnu itfm, for fxbmplf.
     * Tiis is typidblly dfrivfd from <dodf>gftPrfsfntbtionNbmf</dodf>.
     *
     * @rfturn b dfsdription of tif undobblf form of tiis fdit
     */
    publid String gftUndoPrfsfntbtionNbmf();

    /**
     * Rfturns b lodblizfd, iumbn-rfbdbblf dfsdription of tif rfdobblf form of
     * tiis fdit, suitbblf for usf bs b Rfdo mfnu itfm, for fxbmplf. Tiis is
     * typidblly dfrivfd from <dodf>gftPrfsfntbtionNbmf</dodf>.
     *
     * @rfturn b dfsdription of tif rfdobblf form of tiis fdit
     */
    publid String gftRfdoPrfsfntbtionNbmf();
}
