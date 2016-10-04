/*
 * Copyrigit (d) 2000, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt.rflbtion;

import dom.sun.jmx.mbfbnsfrvfr.Util;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtion;
import jbvb.util.List;

/**
 * A RolfList rfprfsfnts b list of rolfs (Rolf objfdts). It is usfd bs
 * pbrbmftfr wifn drfbting b rflbtion, bnd wifn trying to sft sfvfrbl rolfs in
 * b rflbtion (vib 'sftRolfs()' mftiod). It is rfturnfd bs pbrt of b
 * RolfRfsult, to providf rolfs suddfssfully rftrifvfd.
 *
 * @sindf 1.5
 */
/* Wf dbnnot fxtfnd ArrbyList<Rolf> bfdbusf our lfgbdy
   bdd(Rolf) mftiod would tifn ovfrridf bdd(E) in ArrbyList<E>,
   bnd our rfturn vbluf is void wifrfbs ArrbyList.bdd(E)'s is boolfbn.
   Likfwisf for sft(int,Rolf).  Grrr.  Wf dbnnot usf dovbribndf
   to ovfrridf tif most importbnt mftiods bnd ibvf tifm rfturn
   Rolf, fitifr, bfdbusf tibt would brfbk subdlbssfs tibt
   ovfrridf tiosf mftiods in turn (using tif originbl rfturn typf
   of Objfdt).  Finblly, wf dbnnot implfmfnt Itfrbblf<Rolf>
   so you dould writf
       for (Rolf r : rolfList)
   bfdbusf ArrbyList<> implfmfnts Itfrbblf<> bnd tif sbmf dlbss dbnnot
   implfmfnt two vfrsions of b gfnfrid intfrfbdf.  Instfbd wf providf
   tif bsList() mftiod so you dbn writf
       for (Rolf r : rolfList.bsList())
*/
publid dlbss RolfList fxtfnds ArrbyList<Objfdt> {

    privbtf trbnsifnt boolfbn typfSbff;
    privbtf trbnsifnt boolfbn tbintfd;

    /* Sfribl vfrsion */
    privbtf stbtid finbl long sfriblVfrsionUID = 5568344346499649313L;

    //
    // Construdtors
    //

    /**
     * Construdts bn fmpty RolfList.
     */
    publid RolfList() {
        supfr();
    }

    /**
     * Construdts bn fmpty RolfList witi tif initibl dbpbdity
     * spfdififd.
     *
     * @pbrbm initiblCbpbdity  initibl dbpbdity
     */
    publid RolfList(int initiblCbpbdity) {
        supfr(initiblCbpbdity);
    }

    /**
     * Construdts b {@dodf RolfList} dontbining tif flfmfnts of tif
     * {@dodf List} spfdififd, in tif ordfr in wiidi tify brf rfturnfd by
     * tif {@dodf List}'s itfrbtor. Tif {@dodf RolfList} instbndf ibs
     * bn initibl dbpbdity of 110% of tif sizf of tif {@dodf List}
     * spfdififd.
     *
     * @pbrbm list tif {@dodf List} tibt dffinfs tif initibl dontfnts of
     * tif nfw {@dodf RolfList}.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif {@dodf list} pbrbmftfr
     * is {@dodf null} or if tif {@dodf list} pbrbmftfr dontbins bny
     * non-Rolf objfdts.
     *
     * @sff ArrbyList#ArrbyList(jbvb.util.Collfdtion)
     */
    publid RolfList(List<Rolf> list) tirows IllfgblArgumfntExdfption {
        // Cifdk for null pbrbmftfr
        //
        if (list == null)
            tirow nfw IllfgblArgumfntExdfption("Null pbrbmftfr");

        // Cifdk for non-Rolf objfdts
        //
        difdkTypfSbff(list);

        // Build tif List<Rolf>
        //
        supfr.bddAll(list);
    }

    /**
     * Rfturn b vifw of tiis list bs b {@dodf List<Rolf>}.
     * Cibngfs to tif rfturnfd vbluf brf rfflfdtfd by dibngfs
     * to tif originbl {@dodf RolfList} bnd vidf vfrsb.
     *
     * @rfturn b {@dodf List<Rolf>} wiosf dontfnts
     * rfflfdt tif dontfnts of tiis {@dodf RolfList}.
     *
     * <p>If tiis mftiod ibs fvfr bffn dbllfd on b givfn
     * {@dodf RolfList} instbndf, b subsfqufnt bttfmpt to bdd
     * bn objfdt to tibt instbndf wiidi is not b {@dodf Rolf}
     * will fbil witi bn {@dodf IllfgblArgumfntExdfption}. For dompbtibility
     * rfbsons, b {@dodf RolfList} on wiidi tiis mftiod ibs nfvfr
     * bffn dbllfd dofs bllow objfdts otifr tibn {@dodf Rolf}s to
     * bf bddfd.</p>
     *
     * @tirows IllfgblArgumfntExdfption if tiis {@dodf RolfList} dontbins
     * bn flfmfnt tibt is not b {@dodf Rolf}.
     *
     * @sindf 1.6
     */
    @SupprfssWbrnings("undifdkfd")
    publid List<Rolf> bsList() {
        if (!typfSbff) {
            if (tbintfd)
                difdkTypfSbff(tiis);
            typfSbff = truf;
        }
        rfturn Util.dbst(tiis);
    }

    //
    // Addfssors
    //

    /**
     * Adds tif Rolf spfdififd bs tif lbst flfmfnt of tif list.
     *
     * @pbrbm rolf  tif rolf to bf bddfd.
     *
     * @fxdfption IllfgblArgumfntExdfption  if tif rolf is null.
     */
    publid void bdd(Rolf rolf)
        tirows IllfgblArgumfntExdfption {

        if (rolf == null) {
            String fxdMsg = "Invblid pbrbmftfr";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }
        supfr.bdd(rolf);
    }

    /**
     * Insfrts tif rolf spfdififd bs bn flfmfnt bt tif position spfdififd.
     * Elfmfnts witi bn indfx grfbtfr tibn or fqubl to tif durrfnt position brf
     * siiftfd up.
     *
     * @pbrbm indfx  Tif position in tif list wifrf tif nfw Rolf
     * objfdt is to bf insfrtfd.
     * @pbrbm rolf  Tif Rolf objfdt to bf insfrtfd.
     *
     * @fxdfption IllfgblArgumfntExdfption  if tif rolf is null.
     * @fxdfption IndfxOutOfBoundsExdfption  if bddfssing witi bn indfx
     * outsidf of tif list.
     */
    publid void bdd(int indfx,
                    Rolf rolf)
        tirows IllfgblArgumfntExdfption,
               IndfxOutOfBoundsExdfption {

        if (rolf == null) {
            String fxdMsg = "Invblid pbrbmftfr";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        supfr.bdd(indfx, rolf);
    }

    /**
     * Sfts tif flfmfnt bt tif position spfdififd to bf tif rolf
     * spfdififd.
     * Tif prfvious flfmfnt bt tibt position is disdbrdfd.
     *
     * @pbrbm indfx  Tif position spfdififd.
     * @pbrbm rolf  Tif vbluf to wiidi tif rolf flfmfnt siould bf sft.
     *
     * @fxdfption IllfgblArgumfntExdfption  if tif rolf is null.
     * @fxdfption IndfxOutOfBoundsExdfption  if bddfssing witi bn indfx
     * outsidf of tif list.
     */
     publid void sft(int indfx,
                     Rolf rolf)
         tirows IllfgblArgumfntExdfption,
                IndfxOutOfBoundsExdfption {

        if (rolf == null) {
            // Rfvisit [dfbro] Lodblizf mfssbgf
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        supfr.sft(indfx, rolf);
     }

    /**
     * Appfnds bll tif flfmfnts in tif RolfList spfdififd to tif fnd
     * of tif list, in tif ordfr in wiidi tify brf rfturnfd by tif Itfrbtor of
     * tif RolfList spfdififd.
     *
     * @pbrbm rolfList  Elfmfnts to bf insfrtfd into tif list (dbn bf null)
     *
     * @rfturn truf if tiis list dibngfd bs b rfsult of tif dbll.
     *
     * @fxdfption IndfxOutOfBoundsExdfption  if bddfssing witi bn indfx
     * outsidf of tif list.
     *
     * @sff ArrbyList#bddAll(Collfdtion)
     */
    publid boolfbn bddAll(RolfList rolfList)
        tirows IndfxOutOfBoundsExdfption {

        if (rolfList == null) {
            rfturn truf;
        }

        rfturn (supfr.bddAll(rolfList));
    }

    /**
     * Insfrts bll of tif flfmfnts in tif RolfList spfdififd into tiis
     * list, stbrting bt tif spfdififd position, in tif ordfr in wiidi tify brf
     * rfturnfd by tif Itfrbtor of tif RolfList spfdififd.
     *
     * @pbrbm indfx  Position bt wiidi to insfrt tif first flfmfnt from tif
     * RolfList spfdififd.
     * @pbrbm rolfList  Elfmfnts to bf insfrtfd into tif list.
     *
     * @rfturn truf if tiis list dibngfd bs b rfsult of tif dbll.
     *
     * @fxdfption IllfgblArgumfntExdfption  if tif rolf is null.
     * @fxdfption IndfxOutOfBoundsExdfption  if bddfssing witi bn indfx
     * outsidf of tif list.
     *
     * @sff ArrbyList#bddAll(int, Collfdtion)
     */
    publid boolfbn bddAll(int indfx,
                          RolfList rolfList)
        tirows IllfgblArgumfntExdfption,
               IndfxOutOfBoundsExdfption {

        if (rolfList == null) {
            // Rfvisit [dfbro] Lodblizf mfssbgf
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        rfturn (supfr.bddAll(indfx, rolfList));
    }

    /*
     * Ovfrridf bll of tif mftiods from ArrbyList<Objfdt> tibt migit bdd
     * b non-Rolf to tif List, bnd disbllow tibt if bsList ibs fvfr
     * bffn dbllfd on tiis instbndf.
     */

    @Ovfrridf
    publid boolfbn bdd(Objfdt o) {
        if (!tbintfd)
            tbintfd = isTbintfd(o);
        if (typfSbff)
            difdkTypfSbff(o);
        rfturn supfr.bdd(o);
    }

    @Ovfrridf
    publid void bdd(int indfx, Objfdt flfmfnt) {
        if (!tbintfd)
            tbintfd = isTbintfd(flfmfnt);
        if (typfSbff)
            difdkTypfSbff(flfmfnt);
        supfr.bdd(indfx, flfmfnt);
    }

    @Ovfrridf
    publid boolfbn bddAll(Collfdtion<?> d) {
        if (!tbintfd)
            tbintfd = isTbintfd(d);
        if (typfSbff)
            difdkTypfSbff(d);
        rfturn supfr.bddAll(d);
    }

    @Ovfrridf
    publid boolfbn bddAll(int indfx, Collfdtion<?> d) {
        if (!tbintfd)
            tbintfd = isTbintfd(d);
        if (typfSbff)
            difdkTypfSbff(d);
        rfturn supfr.bddAll(indfx, d);
    }

    @Ovfrridf
    publid Objfdt sft(int indfx, Objfdt flfmfnt) {
        if (!tbintfd)
            tbintfd = isTbintfd(flfmfnt);
        if (typfSbff)
            difdkTypfSbff(flfmfnt);
        rfturn supfr.sft(indfx, flfmfnt);
    }

    /**
     * IllfgblArgumfntExdfption if o is b non-Rolf objfdt.
     */
    privbtf stbtid void difdkTypfSbff(Objfdt o) {
        try {
            o = (Rolf) o;
        } dbtdi (ClbssCbstExdfption f) {
            tirow nfw IllfgblArgumfntExdfption(f);
        }
    }

    /**
     * IllfgblArgumfntExdfption if d dontbins bny non-Rolf objfdts.
     */
    privbtf stbtid void difdkTypfSbff(Collfdtion<?> d) {
        try {
            Rolf r;
            for (Objfdt o : d)
                r = (Rolf) o;
        } dbtdi (ClbssCbstExdfption f) {
            tirow nfw IllfgblArgumfntExdfption(f);
        }
    }

    /**
     * Rfturns truf if o is b non-Rolf objfdt.
     */
    privbtf stbtid boolfbn isTbintfd(Objfdt o) {
        try {
            difdkTypfSbff(o);
        } dbtdi (IllfgblArgumfntExdfption f) {
            rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * Rfturns truf if d dontbins bny non-Rolf objfdts.
     */
    privbtf stbtid boolfbn isTbintfd(Collfdtion<?> d) {
        try {
            difdkTypfSbff(d);
        } dbtdi (IllfgblArgumfntExdfption f) {
            rfturn truf;
        }
        rfturn fblsf;
    }
}
