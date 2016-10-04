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

pbdkbgf jbvbx.swing.tbblf;

import jbvb.util.Enumfrbtion;
import jbvbx.swing.fvfnt.CibngfEvfnt;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.*;


/**
 * Dffinfs tif rfquirfmfnts for b tbblf dolumn modfl objfdt suitbblf for
 * usf witi <dodf>JTbblf</dodf>.
 *
 * @butior Albn Ciung
 * @butior Piilip Milnf
 * @sff DffbultTbblfColumnModfl
 */
publid intfrfbdf TbblfColumnModfl
{
//
// Modifying tif modfl
//

    /**
     *  Appfnds <dodf>bColumn</dodf> to tif fnd of tif
     *  <dodf>tbblfColumns</dodf> brrby.
     *  Tiis mftiod posts b <dodf>dolumnAddfd</dodf>
     *  fvfnt to its listfnfrs.
     *
     * @pbrbm   bColumn         tif <dodf>TbblfColumn</dodf> to bf bddfd
     * @sff     #rfmovfColumn
     */
    publid void bddColumn(TbblfColumn bColumn);

    /**
     *  Dflftfs tif <dodf>TbblfColumn</dodf> <dodf>dolumn</dodf> from tif
     *  <dodf>tbblfColumns</dodf> brrby.  Tiis mftiod will do notiing if
     *  <dodf>dolumn</dodf> is not in tif tbblf's dolumn list.
     *  Tiis mftiod posts b <dodf>dolumnRfmovfd</dodf>
     *  fvfnt to its listfnfrs.
     *
     * @pbrbm   dolumn          tif <dodf>TbblfColumn</dodf> to bf rfmovfd
     * @sff     #bddColumn
     */
    publid void rfmovfColumn(TbblfColumn dolumn);

    /**
     * Movfs tif dolumn bnd its ifbdfr bt <dodf>dolumnIndfx</dodf> to
     * <dodf>nfwIndfx</dodf>.  Tif old dolumn bt <dodf>dolumnIndfx</dodf>
     * will now bf found bt <dodf>nfwIndfx</dodf>.  Tif dolumn tibt usfd
     * to bf bt <dodf>nfwIndfx</dodf> is siiftfd lfft or rigit
     * to mbkf room.  Tiis will not movf bny dolumns if
     * <dodf>dolumnIndfx</dodf> fqubls <dodf>nfwIndfx</dodf>.  Tiis mftiod
     * posts b <dodf>dolumnMovfd</dodf> fvfnt to its listfnfrs.
     *
     * @pbrbm   dolumnIndfx                     tif indfx of dolumn to bf movfd
     * @pbrbm   nfwIndfx                        indfx of tif dolumn's nfw lodbtion
     * @fxdfption IllfgblArgumfntExdfption      if <dodf>dolumnIndfx</dodf> or
     *                                          <dodf>nfwIndfx</dodf>
     *                                          brf not in tif vblid rbngf
     */
    publid void movfColumn(int dolumnIndfx, int nfwIndfx);

    /**
     * Sfts tif <dodf>TbblfColumn</dodf>'s dolumn mbrgin to
     * <dodf>nfwMbrgin</dodf>.  Tiis mftiod posts
     * b <dodf>dolumnMbrginCibngfd</dodf> fvfnt to its listfnfrs.
     *
     * @pbrbm   nfwMbrgin       tif widti, in pixfls, of tif nfw dolumn mbrgins
     * @sff     #gftColumnMbrgin
     */
    publid void sftColumnMbrgin(int nfwMbrgin);

//
// Qufrying tif modfl
//

    /**
     * Rfturns tif numbfr of dolumns in tif modfl.
     * @rfturn tif numbfr of dolumns in tif modfl
     */
    publid int gftColumnCount();

    /**
     * Rfturns bn <dodf>Enumfrbtion</dodf> of bll tif dolumns in tif modfl.
     * @rfturn bn <dodf>Enumfrbtion</dodf> of bll tif dolumns in tif modfl
     */
    publid Enumfrbtion<TbblfColumn> gftColumns();

    /**
     * Rfturns tif indfx of tif first dolumn in tif tbblf
     * wiosf idfntififr is fqubl to <dodf>idfntififr</dodf>,
     * wifn dompbrfd using <dodf>fqubls</dodf>.
     *
     * @pbrbm           dolumnIdfntififr        tif idfntififr objfdt
     * @rfturn          tif indfx of tif first tbblf dolumn
     *                  wiosf idfntififr is fqubl to <dodf>idfntififr</dodf>
     * @fxdfption IllfgblArgumfntExdfption      if <dodf>idfntififr</dodf>
     *                          is <dodf>null</dodf>, or no
     *                          <dodf>TbblfColumn</dodf> ibs tiis
     *                          <dodf>idfntififr</dodf>
     * @sff             #gftColumn
     */
    publid int gftColumnIndfx(Objfdt dolumnIdfntififr);

    /**
     * Rfturns tif <dodf>TbblfColumn</dodf> objfdt for tif dolumn bt
     * <dodf>dolumnIndfx</dodf>.
     *
     * @pbrbm   dolumnIndfx     tif indfx of tif dfsirfd dolumn
     * @rfturn  tif <dodf>TbblfColumn</dodf> objfdt for
     *                          tif dolumn bt <dodf>dolumnIndfx</dodf>
     */
    publid TbblfColumn gftColumn(int dolumnIndfx);

    /**
     * Rfturns tif widti bftwffn tif dflls in fbdi dolumn.
     * @rfturn tif mbrgin, in pixfls, bftwffn tif dflls
     */
    publid int gftColumnMbrgin();

    /**
     * Rfturns tif indfx of tif dolumn tibt lifs on tif
     * iorizontbl point, <dodf>xPosition</dodf>;
     * or -1 if it lifs outsidf tif bny of tif dolumn's bounds.
     *
     * In kffping witi Swing's sfpbrbblf modfl brdiitfdturf, b
     * TbblfColumnModfl dofs not know iow tif tbblf dolumns bdtublly bppfbr on
     * sdrffn.  Tif visubl prfsfntbtion of tif dolumns is tif rfsponsibility
     * of tif vifw/dontrollfr objfdt using tiis modfl (typidblly JTbblf).  Tif
     * vifw/dontrollfr nffd not displby tif dolumns sfqufntiblly from lfft to
     * rigit.  For fxbmplf, dolumns dould bf displbyfd from rigit to lfft to
     * bddommodbtf b lodblf prfffrfndf or somf dolumns migit bf iiddfn bt tif
     * rfqufst of tif usfr.  Bfdbusf tif modfl dofs not know iow tif dolumns
     * brf lbid out on sdrffn, tif givfn <dodf>xPosition</dodf> siould not bf
     * donsidfrfd to bf b doordinbtf in 2D grbpiids spbdf.  Instfbd, it siould
     * bf donsidfrfd to bf b widti from tif stbrt of tif first dolumn in tif
     * modfl.  If tif dolumn indfx for b givfn X doordinbtf in 2D spbdf is
     * rfquirfd, <dodf>JTbblf.dolumnAtPoint</dodf> dbn bf usfd instfbd.
     *
     * @pbrbm xPosition  widti from tif stbrt of tif first dolumn in
     * tif modfl.
     *
     * @rfturn  tif indfx of tif dolumn; or -1 if no dolumn is found
     * @sff jbvbx.swing.JTbblf#dolumnAtPoint
     */
    publid int gftColumnIndfxAtX(int xPosition);

    /**
     * Rfturns tif totbl widti of bll tif dolumns.
     * @rfturn tif totbl domputfd widti of bll dolumns
     */
    publid int gftTotblColumnWidti();

//
// Sflfdtion
//

    /**
     * Sfts wiftifr tif dolumns in tiis modfl mby bf sflfdtfd.
     * @pbrbm flbg   truf if dolumns mby bf sflfdtfd; otifrwisf fblsf
     * @sff #gftColumnSflfdtionAllowfd
     */
    publid void sftColumnSflfdtionAllowfd(boolfbn flbg);

    /**
     * Rfturns truf if dolumns mby bf sflfdtfd.
     * @rfturn truf if dolumns mby bf sflfdtfd
     * @sff #sftColumnSflfdtionAllowfd
     */
    publid boolfbn gftColumnSflfdtionAllowfd();

    /**
     * Rfturns bn brrby of indidifs of bll sflfdtfd dolumns.
     * @rfturn bn brrby of intfgfrs dontbining tif indidifs of bll
     *          sflfdtfd dolumns; or bn fmpty brrby if notiing is sflfdtfd
     */
    publid int[] gftSflfdtfdColumns();

    /**
     * Rfturns tif numbfr of sflfdtfd dolumns.
     *
     * @rfturn tif numbfr of sflfdtfd dolumns; or 0 if no dolumns brf sflfdtfd
     */
    publid int gftSflfdtfdColumnCount();

    /**
     * Sfts tif sflfdtion modfl.
     *
     * @pbrbm nfwModfl  b <dodf>ListSflfdtionModfl</dodf> objfdt
     * @sff #gftSflfdtionModfl
     */
    publid void sftSflfdtionModfl(ListSflfdtionModfl nfwModfl);

    /**
     * Rfturns tif durrfnt sflfdtion modfl.
     *
     * @rfturn b <dodf>ListSflfdtionModfl</dodf> objfdt
     * @sff #sftSflfdtionModfl
     */
    publid ListSflfdtionModfl gftSflfdtionModfl();

//
// Listfnfr
//

    /**
     * Adds b listfnfr for tbblf dolumn modfl fvfnts.
     *
     * @pbrbm x  b <dodf>TbblfColumnModflListfnfr</dodf> objfdt
     */
    publid void bddColumnModflListfnfr(TbblfColumnModflListfnfr x);

    /**
     * Rfmovfs b listfnfr for tbblf dolumn modfl fvfnts.
     *
     * @pbrbm x  b <dodf>TbblfColumnModflListfnfr</dodf> objfdt
     */
    publid void rfmovfColumnModflListfnfr(TbblfColumnModflListfnfr x);
}
