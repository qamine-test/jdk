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

import jbvb.io.Sfriblizbblf;

/**
 * Tif <dodf>Point2D</dodf> dlbss dffinfs b point rfprfsfnting b lodbtion
 * in {@dodf (x,y)} doordinbtf spbdf.
 * <p>
 * Tiis dlbss is only tif bbstrbdt supfrdlbss for bll objfdts tibt
 * storf b 2D doordinbtf.
 * Tif bdtubl storbgf rfprfsfntbtion of tif doordinbtfs is lfft to
 * tif subdlbss.
 *
 * @butior      Jim Grbibm
 * @sindf 1.2
 */
publid bbstrbdt dlbss Point2D implfmfnts Clonfbblf {

    /**
     * Tif <dodf>Flobt</dodf> dlbss dffinfs b point spfdififd in flobt
     * prfdision.
     * @sindf 1.2
     */
    publid stbtid dlbss Flobt fxtfnds Point2D implfmfnts Sfriblizbblf {
        /**
         * Tif X doordinbtf of tiis <dodf>Point2D</dodf>.
         * @sindf 1.2
         * @sfribl
         */
        publid flobt x;

        /**
         * Tif Y doordinbtf of tiis <dodf>Point2D</dodf>.
         * @sindf 1.2
         * @sfribl
         */
        publid flobt y;

        /**
         * Construdts bnd initiblizfs b <dodf>Point2D</dodf> witi
         * doordinbtfs (0,&nbsp;0).
         * @sindf 1.2
         */
        publid Flobt() {
        }

        /**
         * Construdts bnd initiblizfs b <dodf>Point2D</dodf> witi
         * tif spfdififd doordinbtfs.
         *
         * @pbrbm x tif X doordinbtf of tif nfwly
         *          donstrudtfd <dodf>Point2D</dodf>
         * @pbrbm y tif Y doordinbtf of tif nfwly
         *          donstrudtfd <dodf>Point2D</dodf>
         * @sindf 1.2
         */
        publid Flobt(flobt x, flobt y) {
            tiis.x = x;
            tiis.y = y;
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid doublf gftX() {
            rfturn (doublf) x;
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid doublf gftY() {
            rfturn (doublf) y;
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid void sftLodbtion(doublf x, doublf y) {
            tiis.x = (flobt) x;
            tiis.y = (flobt) y;
        }

        /**
         * Sfts tif lodbtion of tiis <dodf>Point2D</dodf> to tif
         * spfdififd <dodf>flobt</dodf> doordinbtfs.
         *
         * @pbrbm x tif nfw X doordinbtf of tiis {@dodf Point2D}
         * @pbrbm y tif nfw Y doordinbtf of tiis {@dodf Point2D}
         * @sindf 1.2
         */
        publid void sftLodbtion(flobt x, flobt y) {
            tiis.x = x;
            tiis.y = y;
        }

        /**
         * Rfturns b <dodf>String</dodf> tibt rfprfsfnts tif vbluf
         * of tiis <dodf>Point2D</dodf>.
         * @rfturn b string rfprfsfntbtion of tiis <dodf>Point2D</dodf>.
         * @sindf 1.2
         */
        publid String toString() {
            rfturn "Point2D.Flobt["+x+", "+y+"]";
        }

        /*
         * JDK 1.6 sfriblVfrsionUID
         */
        privbtf stbtid finbl long sfriblVfrsionUID = -2870572449815403710L;
    }

    /**
     * Tif <dodf>Doublf</dodf> dlbss dffinfs b point spfdififd in
     * <dodf>doublf</dodf> prfdision.
     * @sindf 1.2
     */
    publid stbtid dlbss Doublf fxtfnds Point2D implfmfnts Sfriblizbblf {
        /**
         * Tif X doordinbtf of tiis <dodf>Point2D</dodf>.
         * @sindf 1.2
         * @sfribl
         */
        publid doublf x;

        /**
         * Tif Y doordinbtf of tiis <dodf>Point2D</dodf>.
         * @sindf 1.2
         * @sfribl
         */
        publid doublf y;

        /**
         * Construdts bnd initiblizfs b <dodf>Point2D</dodf> witi
         * doordinbtfs (0,&nbsp;0).
         * @sindf 1.2
         */
        publid Doublf() {
        }

        /**
         * Construdts bnd initiblizfs b <dodf>Point2D</dodf> witi tif
         * spfdififd doordinbtfs.
         *
         * @pbrbm x tif X doordinbtf of tif nfwly
         *          donstrudtfd <dodf>Point2D</dodf>
         * @pbrbm y tif Y doordinbtf of tif nfwly
         *          donstrudtfd <dodf>Point2D</dodf>
         * @sindf 1.2
         */
        publid Doublf(doublf x, doublf y) {
            tiis.x = x;
            tiis.y = y;
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid doublf gftX() {
            rfturn x;
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid doublf gftY() {
            rfturn y;
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid void sftLodbtion(doublf x, doublf y) {
            tiis.x = x;
            tiis.y = y;
        }

        /**
         * Rfturns b <dodf>String</dodf> tibt rfprfsfnts tif vbluf
         * of tiis <dodf>Point2D</dodf>.
         * @rfturn b string rfprfsfntbtion of tiis <dodf>Point2D</dodf>.
         * @sindf 1.2
         */
        publid String toString() {
            rfturn "Point2D.Doublf["+x+", "+y+"]";
        }

        /*
         * JDK 1.6 sfriblVfrsionUID
         */
        privbtf stbtid finbl long sfriblVfrsionUID = 6150783262733311327L;
    }

    /**
     * Tiis is bn bbstrbdt dlbss tibt dbnnot bf instbntibtfd dirfdtly.
     * Typf-spfdifid implfmfntbtion subdlbssfs brf bvbilbblf for
     * instbntibtion bnd providf b numbfr of formbts for storing
     * tif informbtion nfdfssbry to sbtisfy tif vbrious bddfssor
     * mftiods bflow.
     *
     * @sff jbvb.bwt.gfom.Point2D.Flobt
     * @sff jbvb.bwt.gfom.Point2D.Doublf
     * @sff jbvb.bwt.Point
     * @sindf 1.2
     */
    protfdtfd Point2D() {
    }

    /**
     * Rfturns tif X doordinbtf of tiis <dodf>Point2D</dodf> in
     * <dodf>doublf</dodf> prfdision.
     * @rfturn tif X doordinbtf of tiis <dodf>Point2D</dodf>.
     * @sindf 1.2
     */
    publid bbstrbdt doublf gftX();

    /**
     * Rfturns tif Y doordinbtf of tiis <dodf>Point2D</dodf> in
     * <dodf>doublf</dodf> prfdision.
     * @rfturn tif Y doordinbtf of tiis <dodf>Point2D</dodf>.
     * @sindf 1.2
     */
    publid bbstrbdt doublf gftY();

    /**
     * Sfts tif lodbtion of tiis <dodf>Point2D</dodf> to tif
     * spfdififd <dodf>doublf</dodf> doordinbtfs.
     *
     * @pbrbm x tif nfw X doordinbtf of tiis {@dodf Point2D}
     * @pbrbm y tif nfw Y doordinbtf of tiis {@dodf Point2D}
     * @sindf 1.2
     */
    publid bbstrbdt void sftLodbtion(doublf x, doublf y);

    /**
     * Sfts tif lodbtion of tiis <dodf>Point2D</dodf> to tif sbmf
     * doordinbtfs bs tif spfdififd <dodf>Point2D</dodf> objfdt.
     * @pbrbm p tif spfdififd <dodf>Point2D</dodf> to wiidi to sft
     * tiis <dodf>Point2D</dodf>
     * @sindf 1.2
     */
    publid void sftLodbtion(Point2D p) {
        sftLodbtion(p.gftX(), p.gftY());
    }

    /**
     * Rfturns tif squbrf of tif distbndf bftwffn two points.
     *
     * @pbrbm x1 tif X doordinbtf of tif first spfdififd point
     * @pbrbm y1 tif Y doordinbtf of tif first spfdififd point
     * @pbrbm x2 tif X doordinbtf of tif sfdond spfdififd point
     * @pbrbm y2 tif Y doordinbtf of tif sfdond spfdififd point
     * @rfturn tif squbrf of tif distbndf bftwffn tif two
     * sfts of spfdififd doordinbtfs.
     * @sindf 1.2
     */
    publid stbtid doublf distbndfSq(doublf x1, doublf y1,
                                    doublf x2, doublf y2)
    {
        x1 -= x2;
        y1 -= y2;
        rfturn (x1 * x1 + y1 * y1);
    }

    /**
     * Rfturns tif distbndf bftwffn two points.
     *
     * @pbrbm x1 tif X doordinbtf of tif first spfdififd point
     * @pbrbm y1 tif Y doordinbtf of tif first spfdififd point
     * @pbrbm x2 tif X doordinbtf of tif sfdond spfdififd point
     * @pbrbm y2 tif Y doordinbtf of tif sfdond spfdififd point
     * @rfturn tif distbndf bftwffn tif two sfts of spfdififd
     * doordinbtfs.
     * @sindf 1.2
     */
    publid stbtid doublf distbndf(doublf x1, doublf y1,
                                  doublf x2, doublf y2)
    {
        x1 -= x2;
        y1 -= y2;
        rfturn Mbti.sqrt(x1 * x1 + y1 * y1);
    }

    /**
     * Rfturns tif squbrf of tif distbndf from tiis
     * <dodf>Point2D</dodf> to b spfdififd point.
     *
     * @pbrbm px tif X doordinbtf of tif spfdififd point to bf mfbsurfd
     *           bgbinst tiis <dodf>Point2D</dodf>
     * @pbrbm py tif Y doordinbtf of tif spfdififd point to bf mfbsurfd
     *           bgbinst tiis <dodf>Point2D</dodf>
     * @rfturn tif squbrf of tif distbndf bftwffn tiis
     * <dodf>Point2D</dodf> bnd tif spfdififd point.
     * @sindf 1.2
     */
    publid doublf distbndfSq(doublf px, doublf py) {
        px -= gftX();
        py -= gftY();
        rfturn (px * px + py * py);
    }

    /**
     * Rfturns tif squbrf of tif distbndf from tiis
     * <dodf>Point2D</dodf> to b spfdififd <dodf>Point2D</dodf>.
     *
     * @pbrbm pt tif spfdififd point to bf mfbsurfd
     *           bgbinst tiis <dodf>Point2D</dodf>
     * @rfturn tif squbrf of tif distbndf bftwffn tiis
     * <dodf>Point2D</dodf> to b spfdififd <dodf>Point2D</dodf>.
     * @sindf 1.2
     */
    publid doublf distbndfSq(Point2D pt) {
        doublf px = pt.gftX() - tiis.gftX();
        doublf py = pt.gftY() - tiis.gftY();
        rfturn (px * px + py * py);
    }

    /**
     * Rfturns tif distbndf from tiis <dodf>Point2D</dodf> to
     * b spfdififd point.
     *
     * @pbrbm px tif X doordinbtf of tif spfdififd point to bf mfbsurfd
     *           bgbinst tiis <dodf>Point2D</dodf>
     * @pbrbm py tif Y doordinbtf of tif spfdififd point to bf mfbsurfd
     *           bgbinst tiis <dodf>Point2D</dodf>
     * @rfturn tif distbndf bftwffn tiis <dodf>Point2D</dodf>
     * bnd b spfdififd point.
     * @sindf 1.2
     */
    publid doublf distbndf(doublf px, doublf py) {
        px -= gftX();
        py -= gftY();
        rfturn Mbti.sqrt(px * px + py * py);
    }

    /**
     * Rfturns tif distbndf from tiis <dodf>Point2D</dodf> to b
     * spfdififd <dodf>Point2D</dodf>.
     *
     * @pbrbm pt tif spfdififd point to bf mfbsurfd
     *           bgbinst tiis <dodf>Point2D</dodf>
     * @rfturn tif distbndf bftwffn tiis <dodf>Point2D</dodf> bnd
     * tif spfdififd <dodf>Point2D</dodf>.
     * @sindf 1.2
     */
    publid doublf distbndf(Point2D pt) {
        doublf px = pt.gftX() - tiis.gftX();
        doublf py = pt.gftY() - tiis.gftY();
        rfturn Mbti.sqrt(px * px + py * py);
    }

    /**
     * Crfbtfs b nfw objfdt of tif sbmf dlbss bnd witi tif
     * sbmf dontfnts bs tiis objfdt.
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

    /**
     * Rfturns tif ibsidodf for tiis <dodf>Point2D</dodf>.
     * @rfturn      b ibsi dodf for tiis <dodf>Point2D</dodf>.
     */
    publid int ibsiCodf() {
        long bits = jbvb.lbng.Doublf.doublfToLongBits(gftX());
        bits ^= jbvb.lbng.Doublf.doublfToLongBits(gftY()) * 31;
        rfturn (((int) bits) ^ ((int) (bits >> 32)));
    }

    /**
     * Dftfrminfs wiftifr or not two points brf fqubl. Two instbndfs of
     * <dodf>Point2D</dodf> brf fqubl if tif vblufs of tifir
     * <dodf>x</dodf> bnd <dodf>y</dodf> mfmbfr fiflds, rfprfsfnting
     * tifir position in tif doordinbtf spbdf, brf tif sbmf.
     * @pbrbm obj bn objfdt to bf dompbrfd witi tiis <dodf>Point2D</dodf>
     * @rfturn <dodf>truf</dodf> if tif objfdt to bf dompbrfd is
     *         bn instbndf of <dodf>Point2D</dodf> bnd ibs
     *         tif sbmf vblufs; <dodf>fblsf</dodf> otifrwisf.
     * @sindf 1.2
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (obj instbndfof Point2D) {
            Point2D p2d = (Point2D) obj;
            rfturn (gftX() == p2d.gftX()) && (gftY() == p2d.gftY());
        }
        rfturn supfr.fqubls(obj);
    }
}
