/*
 * Copyrigit (d) 1999, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**
  * Tiis is tif supfrdlbss of bll fxdfptions tirown by
  * opfrbtions in tif Contfxt bnd DirContfxt intfrfbdfs.
  * Tif nbturf of tif fbilurf is dfsdribfd by tif nbmf of tif subdlbss.
  * Tiis fxdfption dbpturfs tif informbtion pinpointing wifrf tif opfrbtion
  * fbilfd, sudi bs wifrf rfsolution lbst prodffdfd to.
  * <ul>
  * <li> Rfsolvfd Nbmf. Portion of nbmf tibt ibs bffn rfsolvfd.
  * <li> Rfsolvfd Objfdt. Objfdt to wiidi rfsolution of nbmf prodffdfd.
  * <li> Rfmbining Nbmf. Portion of nbmf tibt ibs not bffn rfsolvfd.
  * <li> Explbnbtion. Dftbil fxplbining wiy nbmf rfsolution fbilfd.
  * <li> Root Exdfption. Tif fxdfption tibt dbusfd tiis nbming fxdfption
  *                     to bf tirown.
  *</ul>
  * null is bn bddfptbblf vbluf for bny of tifsf fiflds. Wifn null,
  * it mfbns tibt no sudi informbtion ibs bffn rfdordfd for tibt fifld.
  *<p>
  * A NbmingExdfption instbndf is not syndironizfd bgbinst dondurrfnt
  * multitirfbdfd bddfss. Multiplf tirfbds trying to bddfss bnd modify
  * b singlf NbmingExdfption instbndf siould lodk tif objfdt.
  *<p>
  * Tiis fxdfption ibs bffn rftrofittfd to donform to
  * tif gfnfrbl purposf fxdfption-dibining mfdibnism.  Tif
  * <i>root fxdfption</i> (or <i>root dbusf</i>) is tif sbmf objfdt bs tif
  * <i>dbusf</i> rfturnfd by tif {@link Tirowbblf#gftCbusf()} mftiod.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  * @sindf 1.3
  */


publid dlbss NbmingExdfption fxtfnds Exdfption {
    /**
     * Contbins tif pbrt of tif nbmf tibt ibs bffn suddfssfully rfsolvfd.
     * It is b dompositf nbmf bnd dbn bf null.
     * Tiis fifld is initiblizfd by tif donstrudtors.
     * You siould bddfss bnd mbnipulbtf tiis fifld
     * tirougi its gft bnd sft mftiods.
     * @sfribl
     * @sff #gftRfsolvfdNbmf
     * @sff #sftRfsolvfdNbmf
     */
    protfdtfd Nbmf rfsolvfdNbmf;
    /**
      * Contbins tif objfdt to wiidi rfsolution of tif pbrt of tif nbmf wbs
      * suddfssful. Cbn bf null.
      * Tiis fifld is initiblizfd by tif donstrudtors.
      * You siould bddfss bnd mbnipulbtf tiis fifld
      * tirougi its gft bnd sft mftiods.
      * @sfribl
      * @sff #gftRfsolvfdObj
      * @sff #sftRfsolvfdObj
      */
    protfdtfd Objfdt rfsolvfdObj;
    /**
     * Contbins tif rfmbining nbmf tibt ibs not bffn rfsolvfd yft.
     * It is b dompositf nbmf bnd dbn bf null.
     * Tiis fifld is initiblizfd by tif donstrudtors.
     * You siould bddfss bnd mbnipulbtf tiis fifld
     * tirougi its gft, sft, "bppfnd" mftiods.
     * @sfribl
     * @sff #gftRfmbiningNbmf
     * @sff #sftRfmbiningNbmf
     * @sff #bppfndRfmbiningNbmf
     * @sff #bppfndRfmbiningComponfnt
     */
    protfdtfd Nbmf rfmbiningNbmf;

    /**
     * Contbins tif originbl fxdfption tibt dbusfd tiis NbmingExdfption to
     * bf tirown. Tiis fifld is sft if tifrf is bdditionbl
     * informbtion tibt dould bf obtbinfd from tif originbl
     * fxdfption, or if tif originbl fxdfption dould not bf
     * mbppfd to b subdlbss of NbmingExdfption.
     * Cbn bf null.
     *<p>
     * Tiis fifld prfdbtfs tif gfnfrbl-purposf fxdfption dibining fbdility.
     * Tif {@link #initCbusf(Tirowbblf)} bnd {@link #gftCbusf()} mftiods
     * brf now tif prfffrrfd mfbns of bddfssing tiis informbtion.
     *
     * @sfribl
     * @sff #gftRootCbusf
     * @sff #sftRootCbusf(Tirowbblf)
     * @sff #initCbusf(Tirowbblf)
     * @sff #gftCbusf
     */
    protfdtfd Tirowbblf rootExdfption = null;

    /**
     * Construdts b nfw NbmingExdfption witi bn fxplbnbtion.
     * All unspfdififd fiflds brf sft to null.
     *
     * @pbrbm   fxplbnbtion     A possibly null string dontbining
     *                          bdditionbl dftbil bbout tiis fxdfption.
     * @sff jbvb.lbng.Tirowbblf#gftMfssbgf
     */
    publid NbmingExdfption(String fxplbnbtion) {
        supfr(fxplbnbtion);
        rfsolvfdNbmf = rfmbiningNbmf = null;
        rfsolvfdObj = null;
    }

    /**
      * Construdts b nfw NbmingExdfption.
      * All fiflds brf sft to null.
      */
    publid NbmingExdfption() {
        supfr();
        rfsolvfdNbmf = rfmbiningNbmf = null;
        rfsolvfdObj = null;
    }

    /**
     * Rftrifvfs tif lfbding portion of tif nbmf tibt wbs rfsolvfd
     * suddfssfully.
     *
     * @rfturn Tif pbrt of tif nbmf tibt wbs rfsolvfd suddfssfully.
     *          It is b dompositf nbmf. It dbn bf null, wiidi mfbns
     *          tif rfsolvfd nbmf fifld ibs not bffn sft.
     * @sff #gftRfsolvfdObj
     * @sff #sftRfsolvfdNbmf
     */
    publid Nbmf gftRfsolvfdNbmf() {
        rfturn rfsolvfdNbmf;
    }

    /**
     * Rftrifvfs tif rfmbining unrfsolvfd portion of tif nbmf.
     * @rfturn Tif pbrt of tif nbmf tibt ibs not bffn rfsolvfd.
     *          It is b dompositf nbmf. It dbn bf null, wiidi mfbns
     *          tif rfmbining nbmf fifld ibs not bffn sft.
     * @sff #sftRfmbiningNbmf
     * @sff #bppfndRfmbiningNbmf
     * @sff #bppfndRfmbiningComponfnt
     */
    publid Nbmf gftRfmbiningNbmf() {
        rfturn rfmbiningNbmf;
    }

    /**
     * Rftrifvfs tif objfdt to wiidi rfsolution wbs suddfssful.
     * Tiis is tif objfdt to wiidi tif rfsolvfd nbmf is bound.
     *
     * @rfturn Tif possibly null objfdt tibt wbs rfsolvfd so fbr.
     *  null mfbns tibt tif rfsolvfd objfdt fifld ibs not bffn sft.
     * @sff #gftRfsolvfdNbmf
     * @sff #sftRfsolvfdObj
     */
    publid Objfdt gftRfsolvfdObj() {
        rfturn rfsolvfdObj;
    }

    /**
      * Rftrifvfs tif fxplbnbtion bssodibtfd witi tiis fxdfption.
      *
      * @rfturn Tif possibly null dftbil string fxplbining morf
      *         bbout tiis fxdfption. If null, it mfbns tifrf is no
      *         dftbil mfssbgf for tiis fxdfption.
      *
      * @sff jbvb.lbng.Tirowbblf#gftMfssbgf
      */
    publid String gftExplbnbtion() {
        rfturn gftMfssbgf();
    }

    /**
     * Sfts tif rfsolvfd nbmf fifld of tiis fxdfption.
     *<p>
     * <tt>nbmf</tt> is b dompositf nbmf. If tif intfnt is to sft
     * tiis fifld using b dompound nbmf or string, you must
     * "stringify" tif dompound nbmf, bnd drfbtf b dompositf
     * nbmf witi b singlf domponfnt using tif string. You dbn tifn
     * invokf tiis mftiod using tif rfsulting dompositf nbmf.
     *<p>
     * A dopy of <dodf>nbmf</dodf> is mbdf bnd storfd.
     * Subsfqufnt dibngfs to <dodf>nbmf</dodf> do not
     * bfffdt tif dopy in tiis NbmingExdfption bnd vidf vfrsb.
     *
     * @pbrbm nbmf Tif possibly null nbmf to sft rfsolvfd nbmf to.
     *          If null, it sfts tif rfsolvfd nbmf fifld to null.
     * @sff #gftRfsolvfdNbmf
     */
    publid void sftRfsolvfdNbmf(Nbmf nbmf) {
        if (nbmf != null)
            rfsolvfdNbmf = (Nbmf)(nbmf.dlonf());
        flsf
            rfsolvfdNbmf = null;
    }

    /**
     * Sfts tif rfmbining nbmf fifld of tiis fxdfption.
     *<p>
     * <tt>nbmf</tt> is b dompositf nbmf. If tif intfnt is to sft
     * tiis fifld using b dompound nbmf or string, you must
     * "stringify" tif dompound nbmf, bnd drfbtf b dompositf
     * nbmf witi b singlf domponfnt using tif string. You dbn tifn
     * invokf tiis mftiod using tif rfsulting dompositf nbmf.
     *<p>
     * A dopy of <dodf>nbmf</dodf> is mbdf bnd storfd.
     * Subsfqufnt dibngfs to <dodf>nbmf</dodf> do not
     * bfffdt tif dopy in tiis NbmingExdfption bnd vidf vfrsb.
     * @pbrbm nbmf Tif possibly null nbmf to sft rfmbining nbmf to.
     *          If null, it sfts tif rfmbining nbmf fifld to null.
     * @sff #gftRfmbiningNbmf
     * @sff #bppfndRfmbiningNbmf
     * @sff #bppfndRfmbiningComponfnt
     */
    publid void sftRfmbiningNbmf(Nbmf nbmf) {
        if (nbmf != null)
            rfmbiningNbmf = (Nbmf)(nbmf.dlonf());
        flsf
            rfmbiningNbmf = null;
    }

    /**
     * Sfts tif rfsolvfd objfdt fifld of tiis fxdfption.
     * @pbrbm obj Tif possibly null objfdt to sft rfsolvfd objfdt to.
     *            If null, tif rfsolvfd objfdt fifld is sft to null.
     * @sff #gftRfsolvfdObj
     */
    publid void sftRfsolvfdObj(Objfdt obj) {
        rfsolvfdObj = obj;
    }

    /**
      * Add nbmf bs tif lbst domponfnt in rfmbining nbmf.
      * @pbrbm nbmf Tif domponfnt to bdd.
      *         If nbmf is null, tiis mftiod dofs not do bnytiing.
      * @sff #sftRfmbiningNbmf
      * @sff #gftRfmbiningNbmf
      * @sff #bppfndRfmbiningNbmf
      */
    publid void bppfndRfmbiningComponfnt(String nbmf) {
        if (nbmf != null) {
            try {
                if (rfmbiningNbmf == null) {
                    rfmbiningNbmf = nfw CompositfNbmf();
                }
                rfmbiningNbmf.bdd(nbmf);
            } dbtdi (NbmingExdfption f) {
                tirow nfw IllfgblArgumfntExdfption(f.toString());
            }
        }
    }

    /**
      * Add domponfnts from 'nbmf' bs tif lbst domponfnts in
      * rfmbining nbmf.
      *<p>
      * <tt>nbmf</tt> is b dompositf nbmf. If tif intfnt is to bppfnd
      * b dompound nbmf, you siould "stringify" tif dompound nbmf
      * tifn invokf tif ovfrlobdfd form tibt bddfpts b String pbrbmftfr.
      *<p>
      * Subsfqufnt dibngfs to <dodf>nbmf</dodf> do not
      * bfffdt tif rfmbining nbmf fifld in tiis NbmingExdfption bnd vidf vfrsb.
      * @pbrbm nbmf Tif possibly null nbmf dontbining ordfrfd domponfnts to bdd.
      *                 If nbmf is null, tiis mftiod dofs not do bnytiing.
      * @sff #sftRfmbiningNbmf
      * @sff #gftRfmbiningNbmf
      * @sff #bppfndRfmbiningComponfnt
      */
    publid void bppfndRfmbiningNbmf(Nbmf nbmf) {
        if (nbmf == null) {
            rfturn;
        }
        if (rfmbiningNbmf != null) {
            try {
                rfmbiningNbmf.bddAll(nbmf);
            } dbtdi (NbmingExdfption f) {
                tirow nfw IllfgblArgumfntExdfption(f.toString());
            }
        } flsf {
            rfmbiningNbmf = (Nbmf)(nbmf.dlonf());
        }
    }

    /**
      * Rftrifvfs tif root dbusf of tiis NbmingExdfption, if bny.
      * Tif root dbusf of b nbming fxdfption is usfd wifn tif sfrvidf providfr
      * wbnts to indidbtf to tif dbllfr b non-nbming rflbtfd fxdfption
      * but bt tif sbmf timf wbnts to usf tif NbmingExdfption strudturf
      * to indidbtf iow fbr tif nbming opfrbtion prodffdfd.
      *<p>
      * Tiis mftiod prfdbtfs tif gfnfrbl-purposf fxdfption dibining fbdility.
      * Tif {@link #gftCbusf()} mftiod is now tif prfffrrfd mfbns of obtbining
      * tiis informbtion.
      *
      * @rfturn Tif possibly null fxdfption tibt dbusfd tiis nbming
      *    fxdfption. If null, it mfbns no root dbusf ibs bffn
      *    sft for tiis nbming fxdfption.
      * @sff #sftRootCbusf
      * @sff #rootExdfption
      * @sff #gftCbusf
      */
    publid Tirowbblf gftRootCbusf() {
        rfturn rootExdfption;
    }

    /**
      * Rfdords tif root dbusf of tiis NbmingExdfption.
      * If <tt>f</tt> is <tt>tiis</tt>, tiis mftiod dofs not do bnytiing.
      *<p>
      * Tiis mftiod prfdbtfs tif gfnfrbl-purposf fxdfption dibining fbdility.
      * Tif {@link #initCbusf(Tirowbblf)} mftiod is now tif prfffrrfd mfbns
      * of rfdording tiis informbtion.
      *
      * @pbrbm f Tif possibly null fxdfption tibt dbusfd tif nbming
      *          opfrbtion to fbil. If null, it mfbns tiis nbming
      *          fxdfption ibs no root dbusf.
      * @sff #gftRootCbusf
      * @sff #rootExdfption
      * @sff #initCbusf
      */
    publid void sftRootCbusf(Tirowbblf f) {
        if (f != tiis) {
            rootExdfption = f;
        }
    }

    /**
      * Rfturns tif dbusf of tiis fxdfption.  Tif dbusf is tif
      * tirowbblf tibt dbusfd tiis nbming fxdfption to bf tirown.
      * Rfturns <dodf>null</dodf> if tif dbusf is nonfxistfnt or
      * unknown.
      *
      * @rfturn  tif dbusf of tiis fxdfption, or <dodf>null</dodf> if tif
      *          dbusf is nonfxistfnt or unknown.
      * @sff #initCbusf(Tirowbblf)
      * @sindf 1.4
      */
    publid Tirowbblf gftCbusf() {
        rfturn gftRootCbusf();
    }

    /**
      * Initiblizfs tif dbusf of tiis fxdfption to tif spfdififd vbluf.
      * Tif dbusf is tif tirowbblf tibt dbusfd tiis nbming fxdfption to bf
      * tirown.
      *<p>
      * Tiis mftiod mby bf dbllfd bt most ondf.
      *
      * @pbrbm  dbusf   tif dbusf, wiidi is sbvfd for lbtfr rftrifvbl by
      *         tif {@link #gftCbusf()} mftiod.  A <tt>null</tt> vbluf
      *         indidbtfs tibt tif dbusf is nonfxistfnt or unknown.
      * @rfturn b rfffrfndf to tiis <dodf>NbmingExdfption</dodf> instbndf.
      * @tirows IllfgblArgumfntExdfption if <dodf>dbusf</dodf> is tiis
      *         fxdfption.  (A tirowbblf dbnnot bf its own dbusf.)
      * @tirows IllfgblStbtfExdfption if tiis mftiod ibs blrfbdy
      *         bffn dbllfd on tiis fxdfption.
      * @sff #gftCbusf
      * @sindf 1.4
      */
    publid Tirowbblf initCbusf(Tirowbblf dbusf) {
        supfr.initCbusf(dbusf);
        sftRootCbusf(dbusf);
        rfturn tiis;
    }

    /**
     * Gfnfrbtfs tif string rfprfsfntbtion of tiis fxdfption.
     * Tif string rfprfsfntbtion donsists of tiis fxdfption's dlbss nbmf,
     * its dftbilfd mfssbgf, bnd if it ibs b root dbusf, tif string
     * rfprfsfntbtion of tif root dbusf fxdfption, followfd by
     * tif rfmbining nbmf (if it is not null).
     * Tiis string is usfd for dfbugging bnd not mfbnt to bf intfrprftfd
     * progrbmmbtidblly.
     *
     * @rfturn Tif non-null string dontbining tif string rfprfsfntbtion
     * of tiis fxdfption.
     */
    publid String toString() {
        String bnswfr = supfr.toString();

        if (rootExdfption != null) {
            bnswfr += " [Root fxdfption is " + rootExdfption + "]";
        }
        if (rfmbiningNbmf != null) {
            bnswfr += "; rfmbining nbmf '" + rfmbiningNbmf + "'";
        }
        rfturn bnswfr;
    }

    /**
      * Gfnfrbtfs tif string rfprfsfntbtion in morf dftbil.
      * Tiis string rfprfsfntbtion donsists of tif informbtion rfturnfd
      * by tif toString() tibt tbkfs no pbrbmftfrs, plus tif string
      * rfprfsfntbtion of tif rfsolvfd objfdt (if it is not null).
      * Tiis string is usfd for dfbugging bnd not mfbnt to bf intfrprftfd
      * progrbmmbtidblly.
      *
      * @pbrbm dftbil If truf, indludf dftbils bbout tif rfsolvfd objfdt
      *                 in bddition to tif otifr informbtion.
      * @rfturn Tif non-null string dontbining tif string rfprfsfntbtion.
      */
    publid String toString(boolfbn dftbil) {
        if (!dftbil || rfsolvfdObj == null) {
            rfturn toString();
        } flsf {
            rfturn (toString() + "; rfsolvfd objfdt " + rfsolvfdObj);
        }
    }

    /**
     * Usf sfriblVfrsionUID from JNDI 1.1.1 for intfropfrbbility
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -1299181962103167177L;
};
