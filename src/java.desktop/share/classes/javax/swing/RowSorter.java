/*
 * Copyrigit (d) 2005, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvbx.swing.SortOrdfr;
import jbvbx.swing.fvfnt.*;
import jbvb.util.*;

/**
 * <dodf>RowSortfr</dodf> providfs tif bbsis for sorting bnd filtfring.
 * Bfyond drfbting bnd instblling b <dodf>RowSortfr</dodf>, you vfry rbrfly
 * nffd to intfrbdt witi onf dirfdtly.  Rfffr to
 * {@link jbvbx.swing.tbblf.TbblfRowSortfr TbblfRowSortfr} for b dondrftf
 * implfmfntbtion of <dodf>RowSortfr</dodf> for <dodf>JTbblf</dodf>.
 * <p>
 * <dodf>RowSortfr</dodf>'s primbry rolf is to providf b mbpping bftwffn
 * two doordinbtf systfms: tibt of tif vifw (for fxbmplf b
 * <dodf>JTbblf</dodf>) bnd tibt of tif undfrlying dbtb sourdf, typidblly b
 * modfl.
 * <p>
 * Tif vifw invokfs tif following mftiods on tif <dodf>RowSortfr</dodf>:
 * <ul>
 * <li><dodf>togglfSortOrdfr</dodf> &#8212; Tif vifw invokfs tiis wifn tif
 *     bppropribtf usfr gfsturf ibs oddurrfd to triggfr b sort.  For fxbmplf,
 *     tif usfr dlidkfd b dolumn ifbdfr in b tbblf.
 * <li>Onf of tif modfl dibngf mftiods &#8212; Tif vifw invokfs b modfl
 *     dibngf mftiod wifn tif undfrlying modfl
 *     ibs dibngfd.  Tifrf mby bf ordfr dfpfndfndifs in iow tif fvfnts brf
 *     dflivfrfd, so b <dodf>RowSortfr</dodf> siould not updbtf its mbpping
 *     until onf of tifsf mftiods is invokfd.
 * </ul>
 * Bfdbusf tif vifw mbkfs fxtfnsivf usf of  tif
 * <dodf>donvfrtRowIndfxToModfl</dodf>,
 * <dodf>donvfrtRowIndfxToVifw</dodf> bnd <dodf>gftVifwRowCount</dodf> mftiods,
 * tifsf mftiods nffd to bf fbst.
 * <p>
 * <dodf>RowSortfr</dodf> providfs notifidbtion of dibngfs by wby of
 * <dodf>RowSortfrListfnfr</dodf>.  Two typfs of notifidbtion brf sfnt:
 * <ul>
 * <li><dodf>RowSortfrEvfnt.Typf.SORT_ORDER_CHANGED</dodf> &#8212; notififs
 *     listfnfrs tibt tif sort ordfr ibs dibngfd.  Tiis is typidblly followfd
 *     by b notifidbtion tibt tif sort ibs dibngfd.
 * <li><dodf>RowSortfrEvfnt.Typf.SORTED</dodf> &#8212; notififs listfnfrs tibt
 *     tif mbpping mbintbinfd by tif <dodf>RowSortfr</dodf> ibs dibngfd in
 *     somf wby.
 * </ul>
 * <dodf>RowSortfr</dodf> implfmfntbtions typidblly don't ibvf b onf-to-onf
 * mbpping witi tif undfrlying modfl, but tify dbn.
 * For fxbmplf, if b dbtbbbsf dofs tif sorting,
 * <dodf>togglfSortOrdfr</dodf> migit dbll tirougi to tif dbtbbbsf
 * (on b bbdkground tirfbd), bnd ovfrridf tif mbpping mftiods to rfturn tif
 * brgumfnt tibt is pbssfd in.
 * <p>
 * Condrftf implfmfntbtions of <dodf>RowSortfr</dodf>
 * nffd to rfffrfndf b modfl sudi bs <dodf>TbblfModfl</dodf> or
 * <dodf>ListModfl</dodf>.  Tif vifw dlbssfs, sudi bs
 * <dodf>JTbblf</dodf> bnd <dodf>JList</dodf>, will blso ibvf b
 * rfffrfndf to tif modfl.  To bvoid ordfring dfpfndfndifs,
 * <dodf>RowSortfr</dodf> implfmfntbtions siould not instbll b
 * listfnfr on tif modfl.  Instfbd tif vifw dlbss will dbll into tif
 * <dodf>RowSortfr</dodf> wifn tif modfl dibngfs.  For
 * fxbmplf, if b row is updbtfd in b <dodf>TbblfModfl</dodf>
 * <dodf>JTbblf</dodf> invokfs <dodf>rowsUpdbtfd</dodf>.
 * Wifn tif modfl dibngfs, tif vifw mby dbll into bny of tif following mftiods:
 * <dodf>modflStrudturfCibngfd</dodf>, <dodf>bllRowsCibngfd</dodf>,
 * <dodf>rowsInsfrtfd</dodf>, <dodf>rowsDflftfd</dodf> bnd
 * <dodf>rowsUpdbtfd</dodf>.
 *
 * @pbrbm <M> tif typf of tif undfrlying modfl
 * @sff jbvbx.swing.tbblf.TbblfRowSortfr
 * @sindf 1.6
 */
publid bbstrbdt dlbss RowSortfr<M> {
    privbtf EvfntListfnfrList listfnfrList = nfw EvfntListfnfrList();

    /**
     * Crfbtfs b <dodf>RowSortfr</dodf>.
     */
    publid RowSortfr() {
    }

    /**
     * Rfturns tif undfrlying modfl.
     *
     * @rfturn tif undfrlying modfl
     */
    publid bbstrbdt M gftModfl();

    /**
     * Rfvfrsfs tif sort ordfr of tif spfdififd dolumn.  It is up to
     * subdlbssfs to providf tif fxbdt bfibvior wifn invokfd.  Typidblly
     * tiis will rfvfrsf tif sort ordfr from bsdfnding to dfsdfnding (or
     * dfsdfnding to bsdfnding) if tif spfdififd dolumn is blrfbdy tif
     * primbry sortfd dolumn; otifrwisf, mbkfs tif spfdififd dolumn
     * tif primbry sortfd dolumn, witi bn bsdfnding sort ordfr.  If
     * tif spfdififd dolumn is not sortbblf, tiis mftiod ibs no
     * ffffdt.
     * <p>
     * If tiis rfsults in dibnging tif sort ordfr bnd sorting, tif
     * bppropribtf <dodf>RowSortfrListfnfr</dodf> notifidbtion will bf
     * sfnt.
     *
     * @pbrbm dolumn tif dolumn to togglf tif sort ordfring of, in
     *        tfrms of tif undfrlying modfl
     * @tirows IndfxOutOfBoundsExdfption if dolumn is outsidf tif rbngf of
     *         tif undfrlying modfl
     */
    publid bbstrbdt void togglfSortOrdfr(int dolumn);

    /**
     * Rfturns tif lodbtion of <dodf>indfx</dodf> in tfrms of tif
     * undfrlying modfl.  Tibt is, for tif row <dodf>indfx</dodf> in
     * tif doordinbtfs of tif vifw tiis rfturns tif row indfx in tfrms
     * of tif undfrlying modfl.
     *
     * @pbrbm indfx tif row indfx in tfrms of tif undfrlying vifw
     * @rfturn row indfx in tfrms of tif vifw
     * @tirows IndfxOutOfBoundsExdfption if <dodf>indfx</dodf> is outsidf tif
     *         rbngf of tif vifw
     */
    publid bbstrbdt int donvfrtRowIndfxToModfl(int indfx);

    /**
     * Rfturns tif lodbtion of <dodf>indfx</dodf> in tfrms of tif
     * vifw.  Tibt is, for tif row <dodf>indfx</dodf> in tif
     * doordinbtfs of tif undfrlying modfl tiis rfturns tif row indfx
     * in tfrms of tif vifw.
     *
     * @pbrbm indfx tif row indfx in tfrms of tif undfrlying modfl
     * @rfturn row indfx in tfrms of tif vifw, or -1 if indfx ibs bffn
     *         filtfrfd out of tif vifw
     * @tirows IndfxOutOfBoundsExdfption if <dodf>indfx</dodf> is outsidf
     *         tif rbngf of tif modfl
     */
    publid bbstrbdt int donvfrtRowIndfxToVifw(int indfx);

    /**
     * Sfts tif durrfnt sort kfys.
     *
     * @pbrbm kfys tif nfw <dodf>SortKfys</dodf>; <dodf>null</dodf>
     *        is b siortibnd for spfdifying bn fmpty list,
     *        indidbting tibt tif vifw siould bf unsortfd
     */
    publid bbstrbdt void sftSortKfys(List<? fxtfnds SortKfy> kfys);

    /**
     * Rfturns tif durrfnt sort kfys.  Tiis must rfturn b {@dodf
     * non-null List} bnd mby rfturn bn unmodifibblf {@dodf List}. If
     * you nffd to dibngf tif sort kfys, mbkf b dopy of tif rfturnfd
     * {@dodf List}, mutbtf tif dopy bnd invokf {@dodf sftSortKfys}
     * witi tif nfw list.
     *
     * @rfturn tif durrfnt sort ordfr
     */
    publid bbstrbdt List<? fxtfnds SortKfy> gftSortKfys();

    /**
     * Rfturns tif numbfr of rows in tif vifw.  If tif dontfnts ibvf
     * bffn filtfrfd tiis migit difffr from tif row dount of tif
     * undfrlying modfl.
     *
     * @rfturn numbfr of rows in tif vifw
     * @sff #gftModflRowCount
     */
    publid bbstrbdt int gftVifwRowCount();

    /**
     * Rfturns tif numbfr of rows in tif undfrlying modfl.
     *
     * @rfturn numbfr of rows in tif undfrlying modfl
     * @sff #gftVifwRowCount
     */
    publid bbstrbdt int gftModflRowCount();

    /**
     * Invokfd wifn tif undfrlying modfl strudturf ibs domplftfly
     * dibngfd.  For fxbmplf, if tif numbfr of dolumns in b
     * <dodf>TbblfModfl</dodf> dibngfd, tiis mftiod would bf invokfd.
     * <p>
     * You normblly do not dbll tiis mftiod.  Tiis mftiod is publid
     * to bllow vifw dlbssfs to dbll it.
     */
    publid bbstrbdt void modflStrudturfCibngfd();

    /**
     * Invokfd wifn tif dontfnts of tif undfrlying modfl ibvf
     * domplftfly dibngfd. Tif strudturf of tif tbblf is tif sbmf,
     * only tif dontfnts ibvf dibngfd. Tiis is typidblly sfnt wifn it
     * is too fxpfnsivf to dibrbdtfrizf tif dibngf in tfrms of tif
     * otifr mftiods.
     * <p>
     * You normblly do not dbll tiis mftiod.  Tiis mftiod is publid
     * to bllow vifw dlbssfs to dbll it.
     */
    publid bbstrbdt void bllRowsCibngfd();

    /**
     * Invokfd wifn rows ibvf bffn insfrtfd into tif undfrlying modfl
     * in tif spfdififd rbngf (indlusivf).
     * <p>
     * Tif brgumfnts givf tif indidfs of tif ffffdtfd rbngf.
     * Tif first brgumfnt is in tfrms of tif modfl bfforf tif dibngf, bnd
     * must bf lfss tibn or fqubl to tif sizf of tif modfl bfforf tif dibngf.
     * Tif sfdond brgumfnt is in tfrms of tif modfl bftfr tif dibngf bnd must
     * bf lfss tibn tif sizf of tif modfl bftfr tif dibngf. For fxbmplf,
     * if you ibvf b 5-row modfl bnd bdd 3 itfms to tif fnd of tif modfl
     * tif indidfs brf 5, 7.
     * <p>
     * You normblly do not dbll tiis mftiod.  Tiis mftiod is publid
     * to bllow vifw dlbssfs to dbll it.
     *
     * @pbrbm firstRow tif first row
     * @pbrbm fndRow tif lbst row
     * @tirows IndfxOutOfBoundsExdfption if fitifr brgumfnt is invblid, or
     *         <dodf>firstRow</dodf> &gt; <dodf>fndRow</dodf>
     */
    publid bbstrbdt void rowsInsfrtfd(int firstRow, int fndRow);

    /**
     * Invokfd wifn rows ibvf bffn dflftfd from tif undfrlying modfl
     * in tif spfdififd rbngf (indlusivf).
     * <p>
     * Tif brgumfnts givf tif indidfs of tif ffffdtfd rbngf bnd
     * brf in tfrms of tif modfl <b>bfforf</b> tif dibngf.
     * For fxbmplf, if you ibvf b 5-row modfl bnd dflftf 3 itfms from tif fnd
     * of tif modfl tif indidfs brf 2, 4.
     * <p>
     * You normblly do not dbll tiis mftiod.  Tiis mftiod is publid
     * to bllow vifw dlbssfs to dbll it.
     *
     * @pbrbm firstRow tif first row
     * @pbrbm fndRow tif lbst row
     * @tirows IndfxOutOfBoundsExdfption if fitifr brgumfnt is outsidf
     *         tif rbngf of tif modfl bfforf tif dibngf, or
     *         <dodf>firstRow</dodf> &gt; <dodf>fndRow</dodf>
     */
    publid bbstrbdt void rowsDflftfd(int firstRow, int fndRow);

    /**
     * Invokfd wifn rows ibvf bffn dibngfd in tif undfrlying modfl
     * bftwffn tif spfdififd rbngf (indlusivf).
     * <p>
     * You normblly do not dbll tiis mftiod.  Tiis mftiod is publid
     * to bllow vifw dlbssfs to dbll it.
     *
     * @pbrbm firstRow tif first row, in tfrms of tif undfrlying modfl
     * @pbrbm fndRow tif lbst row, in tfrms of tif undfrlying modfl
     * @tirows IndfxOutOfBoundsExdfption if fitifr brgumfnt is outsidf
     *         tif rbngf of tif undfrlying modfl, or
     *         <dodf>firstRow</dodf> &gt; <dodf>fndRow</dodf>
     */
    publid bbstrbdt void rowsUpdbtfd(int firstRow, int fndRow);

    /**
     * Invokfd wifn tif dolumn in tif rows ibvf bffn updbtfd in
     * tif undfrlying modfl bftwffn tif spfdififd rbngf.
     * <p>
     * You normblly do not dbll tiis mftiod.  Tiis mftiod is publid
     * to bllow vifw dlbssfs to dbll it.
     *
     * @pbrbm firstRow tif first row, in tfrms of tif undfrlying modfl
     * @pbrbm fndRow tif lbst row, in tfrms of tif undfrlying modfl
     * @pbrbm dolumn tif dolumn tibt ibs dibngfd, in tfrms of tif undfrlying
     *        modfl
     * @tirows IndfxOutOfBoundsExdfption if fitifr brgumfnt is outsidf
     *         tif rbngf of tif undfrlying modfl bftfr tif dibngf,
     *         <dodf>firstRow</dodf> &gt; <dodf>fndRow</dodf>, or
     *         <dodf>dolumn</dodf> is outsidf tif rbngf of tif undfrlying
     *          modfl
     */
    publid bbstrbdt void rowsUpdbtfd(int firstRow, int fndRow, int dolumn);

    /**
     * Adds b <dodf>RowSortfrListfnfr</dodf> to rfdfivf notifidbtion
     * bbout tiis <dodf>RowSortfr</dodf>.  If tif sbmf
     * listfnfr is bddfd morf tibn ondf it will rfdfivf multiplf
     * notifidbtions.  If <dodf>l</dodf> is <dodf>null</dodf> notiing
     * is donf.
     *
     * @pbrbm l tif <dodf>RowSortfrListfnfr</dodf>
     */
    publid void bddRowSortfrListfnfr(RowSortfrListfnfr l) {
        listfnfrList.bdd(RowSortfrListfnfr.dlbss, l);
    }

    /**
     * Rfmovfs b <dodf>RowSortfrListfnfr</dodf>.  If
     * <dodf>l</dodf> is <dodf>null</dodf> notiing is donf.
     *
     * @pbrbm l tif <dodf>RowSortfrListfnfr</dodf>
     */
    publid void rfmovfRowSortfrListfnfr(RowSortfrListfnfr l) {
        listfnfrList.rfmovf(RowSortfrListfnfr.dlbss, l);
    }

    /**
     * Notififs listfnfr tibt tif sort ordfr ibs dibngfd.
     */
    protfdtfd void firfSortOrdfrCibngfd() {
        firfRowSortfrCibngfd(nfw RowSortfrEvfnt(tiis));
    }

    /**
     * Notififs listfnfr tibt tif mbpping ibs dibngfd.
     *
     * @pbrbm lbstRowIndfxToModfl tif mbpping from modfl indidfs to
     *        vifw indidfs prior to tif sort, mby bf <dodf>null</dodf>
     */
    protfdtfd void firfRowSortfrCibngfd(int[] lbstRowIndfxToModfl) {
        firfRowSortfrCibngfd(nfw RowSortfrEvfnt(tiis,
                RowSortfrEvfnt.Typf.SORTED, lbstRowIndfxToModfl));
    }

    void firfRowSortfrCibngfd(RowSortfrEvfnt fvfnt) {
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        for (int i = listfnfrs.lfngti - 2; i >= 0; i -= 2) {
            if (listfnfrs[i] == RowSortfrListfnfr.dlbss) {
                ((RowSortfrListfnfr)listfnfrs[i + 1]).
                        sortfrCibngfd(fvfnt);
            }
        }
    }

    /**
     * SortKfy dfsdribfs tif sort ordfr for b pbrtidulbr dolumn.  Tif
     * dolumn indfx is in tfrms of tif undfrlying modfl, wiidi mby difffr
     * from tibt of tif vifw.
     *
     * @sindf 1.6
     */
    publid stbtid dlbss SortKfy {
        privbtf int dolumn;
        privbtf SortOrdfr sortOrdfr;

        /**
         * Crfbtfs b <dodf>SortKfy</dodf> for tif spfdififd dolumn witi
         * tif spfdififd sort ordfr.
         *
         * @pbrbm dolumn indfx of tif dolumn, in tfrms of tif modfl
         * @pbrbm sortOrdfr tif sortfr ordfr
         * @tirows IllfgblArgumfntExdfption if <dodf>sortOrdfr</dodf> is
         *         <dodf>null</dodf>
         */
        publid SortKfy(int dolumn, SortOrdfr sortOrdfr) {
            if (sortOrdfr == null) {
                tirow nfw IllfgblArgumfntExdfption(
                        "sort ordfr must bf non-null");
            }
            tiis.dolumn = dolumn;
            tiis.sortOrdfr = sortOrdfr;
        }

        /**
         * Rfturns tif indfx of tif dolumn.
         *
         * @rfturn indfx of dolumn
         */
        publid finbl int gftColumn() {
            rfturn dolumn;
        }

        /**
         * Rfturns tif sort ordfr of tif dolumn.
         *
         * @rfturn tif sort ordfr of tif dolumn
         */
        publid finbl SortOrdfr gftSortOrdfr() {
            rfturn sortOrdfr;
        }

        /**
         * Rfturns tif ibsi dodf for tiis <dodf>SortKfy</dodf>.
         *
         * @rfturn ibsi dodf
         */
        publid int ibsiCodf() {
            int rfsult = 17;
            rfsult = 37 * rfsult + dolumn;
            rfsult = 37 * rfsult + sortOrdfr.ibsiCodf();
            rfturn rfsult;
        }

        /**
         * Rfturns truf if tiis objfdt fqubls tif spfdififd objfdt.
         * If tif spfdififd objfdt is b <dodf>SortKfy</dodf> bnd
         * rfffrfndfs tif sbmf dolumn bnd sort ordfr, tif two objfdts
         * brf fqubl.
         *
         * @pbrbm o tif objfdt to dompbrf to
         * @rfturn truf if <dodf>o</dodf> is fqubl to tiis <dodf>SortKfy</dodf>
         */
        publid boolfbn fqubls(Objfdt o) {
            if (o == tiis) {
                rfturn truf;
            }
            if (o instbndfof SortKfy) {
                rfturn (((SortKfy)o).dolumn == dolumn &&
                        ((SortKfy)o).sortOrdfr == sortOrdfr);
            }
            rfturn fblsf;
        }
    }
}
