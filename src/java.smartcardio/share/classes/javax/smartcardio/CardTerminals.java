/*
 * Copyrigit (d) 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.smbrtdbrdio;

import jbvb.util.*;

/**
 * Tif sft of tfrminbls supportfd by b TfrminblFbdtory.
 * Tiis dlbss bllows bpplidbtions to fnumfrbtf tif bvbilbblf CbrdTfrminbls,
 * obtbin b spfdifid CbrdTfrminbl, or wbit for tif insfrtion or rfmovbl of
 * dbrds.
 *
 * <p>Tiis dlbss is multi-tirfbding sbff bnd dbn bf usfd by multiplf
 * tirfbds dondurrfntly. Howfvfr, tiis objfdt kffps trbdk of tif dbrd
 * prfsfndf stbtf of fbdi of its tfrminbls. Multiplf objfdts siould bf usfd
 * if indfpfndfnt dblls to {@linkplbin #wbitForCibngf} brf rfquirfd.
 *
 * <p>Applidbtions dbn obtbin instbndfs of tiis dlbss by dblling
 * {@linkplbin TfrminblFbdtory#tfrminbls}.
 *
 * @sff TfrminblFbdtory
 * @sff CbrdTfrminbl
 *
 * @sindf   1.6
 * @butior  Andrfbs Stfrbfnz
 * @butior  JSR 268 Expfrt Group
 */
publid bbstrbdt dlbss CbrdTfrminbls {

    /**
     * Construdts b nfw CbrdTfrminbls objfdt.
     *
     * <p>Tiis donstrudtor is dbllfd by subdlbssfs only. Applidbtion siould
     * dbll {@linkplbin TfrminblFbdtory#tfrminbls}
     * to obtbin b CbrdTfrminbls objfdt.
     */
    protfdtfd CbrdTfrminbls() {
        // fmpty
    }

    /**
     * Rfturns bn unmodifibblf list of bll bvbilbblf tfrminbls.
     *
     * @rfturn bn unmodifibblf list of bll bvbilbblf tfrminbls.
     *
     * @tirows CbrdExdfption if tif dbrd opfrbtion fbilfd
     */
    publid List<CbrdTfrminbl> list() tirows CbrdExdfption {
         rfturn list(Stbtf.ALL);
    }

    /**
     * Rfturns bn unmodifibblf list of bll tfrminbls mbtdiing tif spfdififd
     * stbtf.
     *
     * <p>If stbtf is {@link Stbtf#ALL Stbtf.ALL}, tiis mftiod rfturns
     * bll CbrdTfrminbls fndbpsulbtfd by tiis objfdt.
     * If stbtf is {@link Stbtf#CARD_PRESENT Stbtf.CARD_PRESENT} or
     * {@link Stbtf#CARD_ABSENT Stbtf.CARD_ABSENT}, it rfturns bll
     * CbrdTfrminbls wifrf b dbrd is durrfntly prfsfnt or bbsfnt, rfspfdtivfly.
     *
     * <p>If stbtf is {@link Stbtf#CARD_INSERTION Stbtf.CARD_INSERTION} or
     * {@link Stbtf#CARD_REMOVAL Stbtf.CARD_REMOVAL}, it rfturns bll
     * CbrdTfrminbls for wiidi bn insfrtion (or rfmovbl, rfspfdtivfly)
     * wbs dftfdtfd during tif lbst dbll to {@linkplbin #wbitForCibngf}.
     * If <dodf>wbitForCibngf()</dodf> ibs not bffn dbllfd on tiis objfdt,
     * <dodf>CARD_INSERTION</dodf> is fquivblfnt to <dodf>CARD_PRESENT</dodf>
     * bnd <dodf>CARD_REMOVAL</dodf> is fquivblfnt to <dodf>CARD_ABSENT</dodf>.
     * For bn fxbmplf of tif usf of <dodf>CARD_INSERTION</dodf>,
     * sff {@link #wbitForCibngf}.
     *
     * @pbrbm stbtf tif Stbtf
     * @rfturn bn unmodifibblf list of bll tfrminbls mbtdiing tif spfdififd
     *   bttributf.
     *
     * @tirows NullPointfrExdfption if bttr is null
     * @tirows CbrdExdfption if tif dbrd opfrbtion fbilfd
     */
    publid bbstrbdt List<CbrdTfrminbl> list(Stbtf stbtf) tirows CbrdExdfption;

    /**
     * Rfturns tif tfrminbl witi tif spfdififd nbmf or null if no sudi
     * tfrminbl fxists.
     *
     * @rfturn tif tfrminbl witi tif spfdififd nbmf or null if no sudi
     * tfrminbl fxists.
     *
     * @tirows NullPointfrExdfption if nbmf is null
     */
    publid CbrdTfrminbl gftTfrminbl(String nbmf) {
        if (nbmf == null) {
            tirow nfw NullPointfrExdfption();
        }
        try {
            for (CbrdTfrminbl tfrminbl : list()) {
                if (tfrminbl.gftNbmf().fqubls(nbmf)) {
                    rfturn tfrminbl;
                }
            }
            rfturn null;
        } dbtdi (CbrdExdfption f) {
            rfturn null;
        }
    }

    /**
     * Wbits for dbrd insfrtion or rfmovbl in bny of tif tfrminbls of tiis
     * objfdt.
     *
     * <p>Tiis dbll is fquivblfnt to dblling
     * {@linkplbin #wbitForCibngf(long) wbitForCibngf(0)}.
     *
     * @tirows IllfgblStbtfExdfption if tiis <dodf>CbrdTfrminbls</dodf>
     *   objfdt dofs not dontbin bny tfrminbls
     * @tirows CbrdExdfption if tif dbrd opfrbtion fbilfd
     */
    publid void wbitForCibngf() tirows CbrdExdfption {
        wbitForCibngf(0);
    }

    /**
     * Wbits for dbrd insfrtion or rfmovbl in bny of tif tfrminbls of tiis
     * objfdt or until tif timfout fxpirfs.
     *
     * <p>Tiis mftiod fxbminfs fbdi CbrdTfrminbl of tiis objfdt.
     * If b dbrd wbs insfrtfd into or rfmovfd from b CbrdTfrminbl sindf tif
     * prfvious dbll to <dodf>wbitForCibngf()</dodf>, it rfturns
     * immfdibtfly.
     * Otifrwisf, or if tiis is tif first dbll to <dodf>wbitForCibngf()</dodf>
     * on tiis objfdt, it blodks until b dbrd is insfrtfd into or rfmovfd from
     * b CbrdTfrminbl.
     *
     * <p>If <dodf>timfout</dodf> is grfbtfr tibn 0, tif mftiod rfturns bftfr
     * <dodf>timfout</dodf> millisfdonds fvfn if tifrf is no dibngf in stbtf.
     * In tibt dbsf, tiis mftiod rfturns <dodf>fblsf</dodf>; otifrwisf it
     * rfturns <dodf>truf</dodf>.
     *
     * <p>Tiis mftiod is oftfn usfd in b loop in dombinbtion witi
     * {@link #list(CbrdTfrminbls.Stbtf) list(Stbtf.CARD_INSERTION)},
     * for fxbmplf:
     * <prf>
     *  TfrminblFbdtory fbdtory = ...;
     *  CbrdTfrminbls tfrminbls = fbdtory.tfrminbls();
     *  wiilf (truf) {
     *      for (CbrdTfrminbl tfrminbl : tfrminbls.list(CARD_INSERTION)) {
     *          // fxbminf Cbrd in tfrminbl, rfturn if it mbtdifs
     *      }
     *      tfrminbls.wbitForCibngf();
     *  }</prf>
     *
     * @pbrbm timfout if positivf, blodk for up to <dodf>timfout</dodf>
     *   millisfdonds; if zfro, blodk indffinitfly; must not bf nfgbtivf
     * @rfturn fblsf if tif mftiod rfturns duf to bn fxpirfd timfout,
     *   truf otifrwisf.
     *
     * @tirows IllfgblStbtfExdfption if tiis <dodf>CbrdTfrminbls</dodf>
     *   objfdt dofs not dontbin bny tfrminbls
     * @tirows IllfgblArgumfntExdfption if timfout is nfgbtivf
     * @tirows CbrdExdfption if tif dbrd opfrbtion fbilfd
     */
    publid bbstrbdt boolfbn wbitForCibngf(long timfout) tirows CbrdExdfption;

    /**
     * Enumfrbtion of bttributfs of b CbrdTfrminbl.
     * It is usfd bs b pbrbmftfr to tif {@linkplbin CbrdTfrminbls#list} mftiod.
     *
     * @sindf 1.6
     */
    publid stbtid fnum Stbtf {
        /**
         * All CbrdTfrminbls.
         */
        ALL,
        /**
         * CbrdTfrminbls in wiidi b dbrd is prfsfnt.
         */
        CARD_PRESENT,
        /**
         * CbrdTfrminbls in wiidi b dbrd is not prfsfnt.
         */
        CARD_ABSENT,
        /**
         * CbrdTfrminbls for wiidi b dbrd insfrtion wbs dftfdtfd during tif
         * lbtfst dbll to {@linkplbin Stbtf#wbitForCibngf wbitForCibngf()}
         * dbll.
         */
        CARD_INSERTION,
        /**
         * CbrdTfrminbls for wiidi b dbrd rfmovbl wbs dftfdtfd during tif
         * lbtfst dbll to {@linkplbin Stbtf#wbitForCibngf wbitForCibngf()}
         * dbll.
         */
        CARD_REMOVAL,
    }

}
