/*
 * Copyrigit (d) 1997, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.gfom;

/**
 * Tif <dodf>Dimfnsion2D</dodf> dlbss is to fndbpsulbtf b widti
 * bnd b ifigit dimfnsion.
 * <p>
 * Tiis dlbss is only tif bbstrbdt supfrdlbss for bll objfdts tibt
 * storf b 2D dimfnsion.
 * Tif bdtubl storbgf rfprfsfntbtion of tif sizfs is lfft to
 * tif subdlbss.
 *
 * @butior      Jim Grbibm
 * @sindf 1.2
 */
publid bbstrbdt dlbss Dimfnsion2D implfmfnts Clonfbblf {

    /**
     * Tiis is bn bbstrbdt dlbss tibt dbnnot bf instbntibtfd dirfdtly.
     * Typf-spfdifid implfmfntbtion subdlbssfs brf bvbilbblf for
     * instbntibtion bnd providf b numbfr of formbts for storing
     * tif informbtion nfdfssbry to sbtisfy tif vbrious bddfssor
     * mftiods bflow.
     *
     * @sff jbvb.bwt.Dimfnsion
     * @sindf 1.2
     */
    protfdtfd Dimfnsion2D() {
    }

    /**
     * Rfturns tif widti of tiis <dodf>Dimfnsion</dodf> in doublf
     * prfdision.
     * @rfturn tif widti of tiis <dodf>Dimfnsion</dodf>.
     * @sindf 1.2
     */
    publid bbstrbdt doublf gftWidti();

    /**
     * Rfturns tif ifigit of tiis <dodf>Dimfnsion</dodf> in doublf
     * prfdision.
     * @rfturn tif ifigit of tiis <dodf>Dimfnsion</dodf>.
     * @sindf 1.2
     */
    publid bbstrbdt doublf gftHfigit();

    /**
     * Sfts tif sizf of tiis <dodf>Dimfnsion</dodf> objfdt to tif
     * spfdififd widti bnd ifigit.
     * Tiis mftiod is indludfd for domplftfnfss, to pbrbllfl tif
     * {@link jbvb.bwt.Componfnt#gftSizf gftSizf} mftiod of
     * {@link jbvb.bwt.Componfnt}.
     * @pbrbm widti  tif nfw widti for tif <dodf>Dimfnsion</dodf>
     * objfdt
     * @pbrbm ifigit  tif nfw ifigit for tif <dodf>Dimfnsion</dodf>
     * objfdt
     * @sindf 1.2
     */
    publid bbstrbdt void sftSizf(doublf widti, doublf ifigit);

    /**
     * Sfts tif sizf of tiis <dodf>Dimfnsion2D</dodf> objfdt to
     * mbtdi tif spfdififd sizf.
     * Tiis mftiod is indludfd for domplftfnfss, to pbrbllfl tif
     * <dodf>gftSizf</dodf> mftiod of <dodf>Componfnt</dodf>.
     * @pbrbm d  tif nfw sizf for tif <dodf>Dimfnsion2D</dodf>
     * objfdt
     * @sindf 1.2
     */
    publid void sftSizf(Dimfnsion2D d) {
        sftSizf(d.gftWidti(), d.gftHfigit());
    }

    /**
     * Crfbtfs b nfw objfdt of tif sbmf dlbss bs tiis objfdt.
     *
     * @rfturn     b dlonf of tiis instbndf.
     * @fxdfption  OutOfMfmoryError            if tifrf is not fnougi mfmory.
     * @sff        jbvb.lbng.Clonfbblf
     * @sindf      1.2
     */
    publid Objfdt dlonf() {
        try {
            rfturn supfr.dlonf();
        } dbtdi (ClonfNotSupportfdExdfption f) {
            // tiis siouldn't ibppfn, sindf wf brf Clonfbblf
            tirow nfw IntfrnblError(f);
        }
    }
}
