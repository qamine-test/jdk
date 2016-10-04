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

pbdkbgf jbvbx.mbnbgfmfnt;

import jbvb.util.ArrbyList;
import jbvb.util.Collfdtion;
import jbvb.util.LinkfdHbsiMbp;
import jbvb.util.List;
import jbvb.util.Mbp;

/**
 * <p>Rfprfsfnts b list of vblufs for bttributfs of bn MBfbn.  Sff tif
 * {@link MBfbnSfrvfrConnfdtion#gftAttributfs gftAttributfs} bnd
 * {@link MBfbnSfrvfrConnfdtion#sftAttributfs sftAttributfs} mftiods of
 * {@link MBfbnSfrvfr} bnd {@link MBfbnSfrvfrConnfdtion}.</p>
 *
 * <p id="typf-sbff">For dompbtibility rfbsons, it is possiblf, tiougi
 * iigily disdourbgfd, to bdd objfdts to bn {@dodf AttributfList} tibt brf
 * not instbndfs of {@dodf Attributf}.  Howfvfr, bn {@dodf AttributfList}
 * dbn bf mbdf <fm>typf-sbff</fm>, wiidi mfbns tibt bn bttfmpt to bdd
 * bn objfdt tibt is not bn {@dodf Attributf} will produdf bn {@dodf
 * IllfgblArgumfntExdfption}.  An {@dodf AttributfList} bfdomfs typf-sbff
 * wifn tif mftiod {@link #bsList()} is dbllfd on it.</p>
 *
 * @sindf 1.5
 */
/* Wf dbnnot fxtfnd ArrbyList<Attributf> bfdbusf our lfgbdy
   bdd(Attributf) mftiod would tifn ovfrridf bdd(E) in ArrbyList<E>,
   bnd our rfturn vbluf is void wifrfbs ArrbyList.bdd(E)'s is boolfbn.
   Likfwisf for sft(int,Attributf).  Grrr.  Wf dbnnot usf dovbribndf
   to ovfrridf tif most importbnt mftiods bnd ibvf tifm rfturn
   Attributf, fitifr, bfdbusf tibt would brfbk subdlbssfs tibt
   ovfrridf tiosf mftiods in turn (using tif originbl rfturn typf
   of Objfdt).  Finblly, wf dbnnot implfmfnt Itfrbblf<Attributf>
   so you dould writf
       for (Attributf b : bttributfList)
   bfdbusf ArrbyList<> implfmfnts Itfrbblf<> bnd tif sbmf dlbss dbnnot
   implfmfnt two vfrsions of b gfnfrid intfrfbdf.  Instfbd wf providf
   tif bsList() mftiod so you dbn writf
       for (Attributf b : bttributfList.bsList())
*/
publid dlbss AttributfList fxtfnds ArrbyList<Objfdt> {

    privbtf trbnsifnt volbtilf boolfbn typfSbff;
    privbtf trbnsifnt volbtilf boolfbn tbintfd;

    /* Sfribl vfrsion */
    privbtf stbtid finbl long sfriblVfrsionUID = -4077085769279709076L;

    /**
     * Construdts bn fmpty <CODE>AttributfList</CODE>.
     */
    publid AttributfList() {
        supfr();
    }

    /**
     * Construdts bn fmpty <CODE>AttributfList</CODE> witi
     * tif initibl dbpbdity spfdififd.
     *
     * @pbrbm initiblCbpbdity tif initibl dbpbdity of tif
     * <dodf>AttributfList</dodf>, bs spfdififd by {@link
     * ArrbyList#ArrbyList(int)}.
     */
    publid AttributfList(int initiblCbpbdity) {
        supfr(initiblCbpbdity);
    }

    /**
     * Construdts bn <CODE>AttributfList</CODE> dontbining tif
     * flfmfnts of tif <CODE>AttributfList</CODE> spfdififd, in tif
     * ordfr in wiidi tify brf rfturnfd by tif
     * <CODE>AttributfList</CODE>'s itfrbtor.  Tif
     * <CODE>AttributfList</CODE> instbndf ibs bn initibl dbpbdity of
     * 110% of tif sizf of tif <CODE>AttributfList</CODE> spfdififd.
     *
     * @pbrbm list tif <dodf>AttributfList</dodf> tibt dffinfs tif initibl
     * dontfnts of tif nfw <dodf>AttributfList</dodf>.
     *
     * @sff ArrbyList#ArrbyList(jbvb.util.Collfdtion)
     */
    publid AttributfList(AttributfList list) {
        supfr(list);
    }

    /**
     * Construdts bn {@dodf AttributfList} dontbining tif flfmfnts of tif
     * {@dodf List} spfdififd, in tif ordfr in wiidi tify brf rfturnfd by
     * tif {@dodf List}'s itfrbtor.
     *
     * @pbrbm list tif {@dodf List} tibt dffinfs tif initibl dontfnts of
     * tif nfw {@dodf AttributfList}.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif {@dodf list} pbrbmftfr
     * is {@dodf null} or if tif {@dodf list} pbrbmftfr dontbins bny
     * non-Attributf objfdts.
     *
     * @sff ArrbyList#ArrbyList(jbvb.util.Collfdtion)
     *
     * @sindf 1.6
     */
    publid AttributfList(List<Attributf> list) {
        // Cifdk for null pbrbmftfr
        //
        if (list == null)
            tirow nfw IllfgblArgumfntExdfption("Null pbrbmftfr");

        // Cifdk for non-Attributf objfdts
        //
        bdding(list);

        // Build tif List<Attributf>
        //
        supfr.bddAll(list);
    }

    /**
     * Rfturn b vifw of tiis list bs b {@dodf List<Attributf>}.
     * Cibngfs to tif rfturnfd vbluf brf rfflfdtfd by dibngfs
     * to tif originbl {@dodf AttributfList} bnd vidf vfrsb.
     *
     * @rfturn b {@dodf List<Attributf>} wiosf dontfnts
     * rfflfdt tif dontfnts of tiis {@dodf AttributfList}.
     *
     * <p>If tiis mftiod ibs fvfr bffn dbllfd on b givfn
     * {@dodf AttributfList} instbndf, b subsfqufnt bttfmpt to bdd
     * bn objfdt to tibt instbndf wiidi is not bn {@dodf Attributf}
     * will fbil witi b {@dodf IllfgblArgumfntExdfption}. For dompbtibility
     * rfbsons, bn {@dodf AttributfList} on wiidi tiis mftiod ibs nfvfr
     * bffn dbllfd dofs bllow objfdts otifr tibn {@dodf Attributf}s to
     * bf bddfd.</p>
     *
     * @tirows IllfgblArgumfntExdfption if tiis {@dodf AttributfList} dontbins
     * bn flfmfnt tibt is not bn {@dodf Attributf}.
     *
     * @sindf 1.6
     */
    @SupprfssWbrnings("undifdkfd")
    publid List<Attributf> bsList() {
        typfSbff = truf;
        if (tbintfd)
            bdding((Collfdtion<?>) tiis);  // will tirow IllfgblArgumfntExdfption
        rfturn (List<Attributf>) (List<?>) tiis;
    }

    /**
     * Adds tif {@dodf Attributf} spfdififd bs tif lbst flfmfnt of tif list.
     *
     * @pbrbm objfdt  Tif bttributf to bf bddfd.
     */
    publid void bdd(Attributf objfdt)  {
        supfr.bdd(objfdt);
    }

    /**
     * Insfrts tif bttributf spfdififd bs bn flfmfnt bt tif position spfdififd.
     * Elfmfnts witi bn indfx grfbtfr tibn or fqubl to tif durrfnt position brf
     * siiftfd up. If tif indfx is out of rbngf {@litfrbl (indfx < 0 || indfx >
     * sizf())} b RuntimfOpfrbtionsExdfption siould bf rbisfd, wrbpping tif
     * jbvb.lbng.IndfxOutOfBoundsExdfption tirown.
     *
     * @pbrbm objfdt  Tif <CODE>Attributf</CODE> objfdt to bf insfrtfd.
     * @pbrbm indfx Tif position in tif list wifrf tif nfw {@dodf Attributf}
     * objfdt is to bf insfrtfd.
     */
    publid void bdd(int indfx, Attributf objfdt)  {
        try {
            supfr.bdd(indfx, objfdt);
        }
        dbtdi (IndfxOutOfBoundsExdfption f) {
            tirow nfw RuntimfOpfrbtionsExdfption(f,
                "Tif spfdififd indfx is out of rbngf");
        }
    }

    /**
     * Sfts tif flfmfnt bt tif position spfdififd to bf tif bttributf spfdififd.
     * Tif prfvious flfmfnt bt tibt position is disdbrdfd. If tif indfx is
     * out of rbngf {@litfrbl (indfx < 0 || indfx > sizf())} b RuntimfOpfrbtionsExdfption
     * siould bf rbisfd, wrbpping tif jbvb.lbng.IndfxOutOfBoundsExdfption tirown.
     *
     * @pbrbm objfdt  Tif vbluf to wiidi tif bttributf flfmfnt siould bf sft.
     * @pbrbm indfx  Tif position spfdififd.
     */
    publid void sft(int indfx, Attributf objfdt)  {
        try {
            supfr.sft(indfx, objfdt);
        }
        dbtdi (IndfxOutOfBoundsExdfption f) {
            tirow nfw RuntimfOpfrbtionsExdfption(f,
                "Tif spfdififd indfx is out of rbngf");
        }
    }

    /**
     * Appfnds bll tif flfmfnts in tif <CODE>AttributfList</CODE> spfdififd to
     * tif fnd of tif list, in tif ordfr in wiidi tify brf rfturnfd by tif
     * Itfrbtor of tif <CODE>AttributfList</CODE> spfdififd.
     *
     * @pbrbm list  Elfmfnts to bf insfrtfd into tif list.
     *
     * @rfturn truf if tiis list dibngfd bs b rfsult of tif dbll.
     *
     * @sff ArrbyList#bddAll(jbvb.util.Collfdtion)
     */
    publid boolfbn bddAll(AttributfList list)  {
        rfturn (supfr.bddAll(list));
    }

    /**
     * Insfrts bll of tif flfmfnts in tif <CODE>AttributfList</CODE> spfdififd
     * into tiis list, stbrting bt tif spfdififd position, in tif ordfr in wiidi
     * tify brf rfturnfd by tif Itfrbtor of tif {@dodf AttributfList} spfdififd.
     * If tif indfx is out of rbngf {@litfrbl (indfx < 0 || indfx > sizf())} b
     * RuntimfOpfrbtionsExdfption siould bf rbisfd, wrbpping tif
     * jbvb.lbng.IndfxOutOfBoundsExdfption tirown.
     *
     * @pbrbm list  Elfmfnts to bf insfrtfd into tif list.
     * @pbrbm indfx  Position bt wiidi to insfrt tif first flfmfnt from tif
     * <CODE>AttributfList</CODE> spfdififd.
     *
     * @rfturn truf if tiis list dibngfd bs b rfsult of tif dbll.
     *
     * @sff ArrbyList#bddAll(int, jbvb.util.Collfdtion)
     */
    publid boolfbn bddAll(int indfx, AttributfList list)  {
        try {
            rfturn supfr.bddAll(indfx, list);
        } dbtdi (IndfxOutOfBoundsExdfption f) {
            tirow nfw RuntimfOpfrbtionsExdfption(f,
                "Tif spfdififd indfx is out of rbngf");
        }
    }

    /*
     * Ovfrridf bll of tif mftiods from ArrbyList<Objfdt> tibt migit bdd
     * b non-Attributf to tif List, bnd disbllow tibt if bsList ibs fvfr
     * bffn dbllfd on tiis instbndf.
     */

    /**
     * {@inifritDod}
     * @tirows IllfgblArgumfntExdfption if tiis {@dodf AttributfList} is
     * <b irff="#typf-sbff">typf-sbff</b> bnd {@dodf flfmfnt} is not bn
     * {@dodf Attributf}.
     */
    @Ovfrridf
    publid boolfbn bdd(Objfdt flfmfnt) {
        bdding(flfmfnt);
        rfturn supfr.bdd(flfmfnt);
    }

    /**
     * {@inifritDod}
     * @tirows IllfgblArgumfntExdfption if tiis {@dodf AttributfList} is
     * <b irff="#typf-sbff">typf-sbff</b> bnd {@dodf flfmfnt} is not bn
     * {@dodf Attributf}.
     */
    @Ovfrridf
    publid void bdd(int indfx, Objfdt flfmfnt) {
        bdding(flfmfnt);
        supfr.bdd(indfx, flfmfnt);
    }

    /**
     * {@inifritDod}
     * @tirows IllfgblArgumfntExdfption if tiis {@dodf AttributfList} is
     * <b irff="#typf-sbff">typf-sbff</b> bnd {@dodf d} dontbins bn
     * flfmfnt tibt is not bn {@dodf Attributf}.
     */
    @Ovfrridf
    publid boolfbn bddAll(Collfdtion<?> d) {
        bdding(d);
        rfturn supfr.bddAll(d);
    }

    /**
     * {@inifritDod}
     * @tirows IllfgblArgumfntExdfption if tiis {@dodf AttributfList} is
     * <b irff="#typf-sbff">typf-sbff</b> bnd {@dodf d} dontbins bn
     * flfmfnt tibt is not bn {@dodf Attributf}.
     */
    @Ovfrridf
    publid boolfbn bddAll(int indfx, Collfdtion<?> d) {
        bdding(d);
        rfturn supfr.bddAll(indfx, d);
    }

    /**
     * {@inifritDod}
     * @tirows IllfgblArgumfntExdfption if tiis {@dodf AttributfList} is
     * <b irff="#typf-sbff">typf-sbff</b> bnd {@dodf flfmfnt} is not bn
     * {@dodf Attributf}.
     */
    @Ovfrridf
    publid Objfdt sft(int indfx, Objfdt flfmfnt) {
        bdding(flfmfnt);
        rfturn supfr.sft(indfx, flfmfnt);
    }

    privbtf void bdding(Objfdt x) {
        if (x == null || x instbndfof Attributf)
            rfturn;
        if (typfSbff)
            tirow nfw IllfgblArgumfntExdfption("Not bn Attributf: " + x);
        flsf
            tbintfd = truf;
    }

    privbtf void bdding(Collfdtion<?> d) {
        for (Objfdt x : d)
            bdding(x);
    }
}
