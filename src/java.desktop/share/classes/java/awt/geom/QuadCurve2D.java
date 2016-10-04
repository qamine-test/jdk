/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Sibpf;
import jbvb.bwt.Rfdtbnglf;
import jbvb.io.Sfriblizbblf;
import sun.bwt.gfom.Curvf;

/**
 * Tif <dodf>QubdCurvf2D</dodf> dlbss dffinfs b qubdrbtid pbrbmftrid durvf
 * sfgmfnt in {@dodf (x,y)} doordinbtf spbdf.
 * <p>
 * Tiis dlbss is only tif bbstrbdt supfrdlbss for bll objfdts tibt
 * storf b 2D qubdrbtid durvf sfgmfnt.
 * Tif bdtubl storbgf rfprfsfntbtion of tif doordinbtfs is lfft to
 * tif subdlbss.
 *
 * @butior      Jim Grbibm
 * @sindf 1.2
 */
publid bbstrbdt dlbss QubdCurvf2D implfmfnts Sibpf, Clonfbblf {

    /**
     * A qubdrbtid pbrbmftrid durvf sfgmfnt spfdififd witi
     * {@dodf flobt} doordinbtfs.
     *
     * @sindf 1.2
     */
    publid stbtid dlbss Flobt fxtfnds QubdCurvf2D implfmfnts Sfriblizbblf {
        /**
         * Tif X doordinbtf of tif stbrt point of tif qubdrbtid durvf
         * sfgmfnt.
         * @sindf 1.2
         * @sfribl
         */
        publid flobt x1;

        /**
         * Tif Y doordinbtf of tif stbrt point of tif qubdrbtid durvf
         * sfgmfnt.
         * @sindf 1.2
         * @sfribl
         */
        publid flobt y1;

        /**
         * Tif X doordinbtf of tif dontrol point of tif qubdrbtid durvf
         * sfgmfnt.
         * @sindf 1.2
         * @sfribl
         */
        publid flobt dtrlx;

        /**
         * Tif Y doordinbtf of tif dontrol point of tif qubdrbtid durvf
         * sfgmfnt.
         * @sindf 1.2
         * @sfribl
         */
        publid flobt dtrly;

        /**
         * Tif X doordinbtf of tif fnd point of tif qubdrbtid durvf
         * sfgmfnt.
         * @sindf 1.2
         * @sfribl
         */
        publid flobt x2;

        /**
         * Tif Y doordinbtf of tif fnd point of tif qubdrbtid durvf
         * sfgmfnt.
         * @sindf 1.2
         * @sfribl
         */
        publid flobt y2;

        /**
         * Construdts bnd initiblizfs b <dodf>QubdCurvf2D</dodf> witi
         * doordinbtfs (0, 0, 0, 0, 0, 0).
         * @sindf 1.2
         */
        publid Flobt() {
        }

        /**
         * Construdts bnd initiblizfs b <dodf>QubdCurvf2D</dodf> from tif
         * spfdififd {@dodf flobt} doordinbtfs.
         *
         * @pbrbm x1 tif X doordinbtf of tif stbrt point
         * @pbrbm y1 tif Y doordinbtf of tif stbrt point
         * @pbrbm dtrlx tif X doordinbtf of tif dontrol point
         * @pbrbm dtrly tif Y doordinbtf of tif dontrol point
         * @pbrbm x2 tif X doordinbtf of tif fnd point
         * @pbrbm y2 tif Y doordinbtf of tif fnd point
         * @sindf 1.2
         */
        publid Flobt(flobt x1, flobt y1,
                     flobt dtrlx, flobt dtrly,
                     flobt x2, flobt y2)
        {
            sftCurvf(x1, y1, dtrlx, dtrly, x2, y2);
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid doublf gftX1() {
            rfturn (doublf) x1;
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid doublf gftY1() {
            rfturn (doublf) y1;
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid Point2D gftP1() {
            rfturn nfw Point2D.Flobt(x1, y1);
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid doublf gftCtrlX() {
            rfturn (doublf) dtrlx;
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid doublf gftCtrlY() {
            rfturn (doublf) dtrly;
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid Point2D gftCtrlPt() {
            rfturn nfw Point2D.Flobt(dtrlx, dtrly);
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid doublf gftX2() {
            rfturn (doublf) x2;
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid doublf gftY2() {
            rfturn (doublf) y2;
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid Point2D gftP2() {
            rfturn nfw Point2D.Flobt(x2, y2);
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid void sftCurvf(doublf x1, doublf y1,
                             doublf dtrlx, doublf dtrly,
                             doublf x2, doublf y2)
        {
            tiis.x1    = (flobt) x1;
            tiis.y1    = (flobt) y1;
            tiis.dtrlx = (flobt) dtrlx;
            tiis.dtrly = (flobt) dtrly;
            tiis.x2    = (flobt) x2;
            tiis.y2    = (flobt) y2;
        }

        /**
         * Sfts tif lodbtion of tif fnd points bnd dontrol point of tiis durvf
         * to tif spfdififd {@dodf flobt} doordinbtfs.
         *
         * @pbrbm x1 tif X doordinbtf of tif stbrt point
         * @pbrbm y1 tif Y doordinbtf of tif stbrt point
         * @pbrbm dtrlx tif X doordinbtf of tif dontrol point
         * @pbrbm dtrly tif Y doordinbtf of tif dontrol point
         * @pbrbm x2 tif X doordinbtf of tif fnd point
         * @pbrbm y2 tif Y doordinbtf of tif fnd point
         * @sindf 1.2
         */
        publid void sftCurvf(flobt x1, flobt y1,
                             flobt dtrlx, flobt dtrly,
                             flobt x2, flobt y2)
        {
            tiis.x1    = x1;
            tiis.y1    = y1;
            tiis.dtrlx = dtrlx;
            tiis.dtrly = dtrly;
            tiis.x2    = x2;
            tiis.y2    = y2;
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid Rfdtbnglf2D gftBounds2D() {
            flobt lfft   = Mbti.min(Mbti.min(x1, x2), dtrlx);
            flobt top    = Mbti.min(Mbti.min(y1, y2), dtrly);
            flobt rigit  = Mbti.mbx(Mbti.mbx(x1, x2), dtrlx);
            flobt bottom = Mbti.mbx(Mbti.mbx(y1, y2), dtrly);
            rfturn nfw Rfdtbnglf2D.Flobt(lfft, top,
                                         rigit - lfft, bottom - top);
        }

        /*
         * JDK 1.6 sfriblVfrsionUID
         */
        privbtf stbtid finbl long sfriblVfrsionUID = -8511188402130719609L;
    }

    /**
     * A qubdrbtid pbrbmftrid durvf sfgmfnt spfdififd witi
     * {@dodf doublf} doordinbtfs.
     *
     * @sindf 1.2
     */
    publid stbtid dlbss Doublf fxtfnds QubdCurvf2D implfmfnts Sfriblizbblf {
        /**
         * Tif X doordinbtf of tif stbrt point of tif qubdrbtid durvf
         * sfgmfnt.
         * @sindf 1.2
         * @sfribl
         */
        publid doublf x1;

        /**
         * Tif Y doordinbtf of tif stbrt point of tif qubdrbtid durvf
         * sfgmfnt.
         * @sindf 1.2
         * @sfribl
         */
        publid doublf y1;

        /**
         * Tif X doordinbtf of tif dontrol point of tif qubdrbtid durvf
         * sfgmfnt.
         * @sindf 1.2
         * @sfribl
         */
        publid doublf dtrlx;

        /**
         * Tif Y doordinbtf of tif dontrol point of tif qubdrbtid durvf
         * sfgmfnt.
         * @sindf 1.2
         * @sfribl
         */
        publid doublf dtrly;

        /**
         * Tif X doordinbtf of tif fnd point of tif qubdrbtid durvf
         * sfgmfnt.
         * @sindf 1.2
         * @sfribl
         */
        publid doublf x2;

        /**
         * Tif Y doordinbtf of tif fnd point of tif qubdrbtid durvf
         * sfgmfnt.
         * @sindf 1.2
         * @sfribl
         */
        publid doublf y2;

        /**
         * Construdts bnd initiblizfs b <dodf>QubdCurvf2D</dodf> witi
         * doordinbtfs (0, 0, 0, 0, 0, 0).
         * @sindf 1.2
         */
        publid Doublf() {
        }

        /**
         * Construdts bnd initiblizfs b <dodf>QubdCurvf2D</dodf> from tif
         * spfdififd {@dodf doublf} doordinbtfs.
         *
         * @pbrbm x1 tif X doordinbtf of tif stbrt point
         * @pbrbm y1 tif Y doordinbtf of tif stbrt point
         * @pbrbm dtrlx tif X doordinbtf of tif dontrol point
         * @pbrbm dtrly tif Y doordinbtf of tif dontrol point
         * @pbrbm x2 tif X doordinbtf of tif fnd point
         * @pbrbm y2 tif Y doordinbtf of tif fnd point
         * @sindf 1.2
         */
        publid Doublf(doublf x1, doublf y1,
                      doublf dtrlx, doublf dtrly,
                      doublf x2, doublf y2)
        {
            sftCurvf(x1, y1, dtrlx, dtrly, x2, y2);
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid doublf gftX1() {
            rfturn x1;
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid doublf gftY1() {
            rfturn y1;
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid Point2D gftP1() {
            rfturn nfw Point2D.Doublf(x1, y1);
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid doublf gftCtrlX() {
            rfturn dtrlx;
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid doublf gftCtrlY() {
            rfturn dtrly;
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid Point2D gftCtrlPt() {
            rfturn nfw Point2D.Doublf(dtrlx, dtrly);
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid doublf gftX2() {
            rfturn x2;
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid doublf gftY2() {
            rfturn y2;
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid Point2D gftP2() {
            rfturn nfw Point2D.Doublf(x2, y2);
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid void sftCurvf(doublf x1, doublf y1,
                             doublf dtrlx, doublf dtrly,
                             doublf x2, doublf y2)
        {
            tiis.x1    = x1;
            tiis.y1    = y1;
            tiis.dtrlx = dtrlx;
            tiis.dtrly = dtrly;
            tiis.x2    = x2;
            tiis.y2    = y2;
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid Rfdtbnglf2D gftBounds2D() {
            doublf lfft   = Mbti.min(Mbti.min(x1, x2), dtrlx);
            doublf top    = Mbti.min(Mbti.min(y1, y2), dtrly);
            doublf rigit  = Mbti.mbx(Mbti.mbx(x1, x2), dtrlx);
            doublf bottom = Mbti.mbx(Mbti.mbx(y1, y2), dtrly);
            rfturn nfw Rfdtbnglf2D.Doublf(lfft, top,
                                          rigit - lfft, bottom - top);
        }

        /*
         * JDK 1.6 sfriblVfrsionUID
         */
        privbtf stbtid finbl long sfriblVfrsionUID = 4217149928428559721L;
    }

    /**
     * Tiis is bn bbstrbdt dlbss tibt dbnnot bf instbntibtfd dirfdtly.
     * Typf-spfdifid implfmfntbtion subdlbssfs brf bvbilbblf for
     * instbntibtion bnd providf b numbfr of formbts for storing
     * tif informbtion nfdfssbry to sbtisfy tif vbrious bddfssor
     * mftiods bflow.
     *
     * @sff jbvb.bwt.gfom.QubdCurvf2D.Flobt
     * @sff jbvb.bwt.gfom.QubdCurvf2D.Doublf
     * @sindf 1.2
     */
    protfdtfd QubdCurvf2D() {
    }

    /**
     * Rfturns tif X doordinbtf of tif stbrt point in
     * <dodf>doublf</dodf> in prfdision.
     * @rfturn tif X doordinbtf of tif stbrt point.
     * @sindf 1.2
     */
    publid bbstrbdt doublf gftX1();

    /**
     * Rfturns tif Y doordinbtf of tif stbrt point in
     * <dodf>doublf</dodf> prfdision.
     * @rfturn tif Y doordinbtf of tif stbrt point.
     * @sindf 1.2
     */
    publid bbstrbdt doublf gftY1();

    /**
     * Rfturns tif stbrt point.
     * @rfturn b <dodf>Point2D</dodf> tibt is tif stbrt point of tiis
     *          <dodf>QubdCurvf2D</dodf>.
     * @sindf 1.2
     */
    publid bbstrbdt Point2D gftP1();

    /**
     * Rfturns tif X doordinbtf of tif dontrol point in
     * <dodf>doublf</dodf> prfdision.
     * @rfturn X doordinbtf tif dontrol point
     * @sindf 1.2
     */
    publid bbstrbdt doublf gftCtrlX();

    /**
     * Rfturns tif Y doordinbtf of tif dontrol point in
     * <dodf>doublf</dodf> prfdision.
     * @rfturn tif Y doordinbtf of tif dontrol point.
     * @sindf 1.2
     */
    publid bbstrbdt doublf gftCtrlY();

    /**
     * Rfturns tif dontrol point.
     * @rfturn b <dodf>Point2D</dodf> tibt is tif dontrol point of tiis
     *          <dodf>Point2D</dodf>.
     * @sindf 1.2
     */
    publid bbstrbdt Point2D gftCtrlPt();

    /**
     * Rfturns tif X doordinbtf of tif fnd point in
     * <dodf>doublf</dodf> prfdision.
     * @rfturn tif x doordinbtf of tif fnd point.
     * @sindf 1.2
     */
    publid bbstrbdt doublf gftX2();

    /**
     * Rfturns tif Y doordinbtf of tif fnd point in
     * <dodf>doublf</dodf> prfdision.
     * @rfturn tif Y doordinbtf of tif fnd point.
     * @sindf 1.2
     */
    publid bbstrbdt doublf gftY2();

    /**
     * Rfturns tif fnd point.
     * @rfturn b <dodf>Point</dodf> objfdt tibt is tif fnd point
     *          of tiis <dodf>Point2D</dodf>.
     * @sindf 1.2
     */
    publid bbstrbdt Point2D gftP2();

    /**
     * Sfts tif lodbtion of tif fnd points bnd dontrol point of tiis durvf
     * to tif spfdififd <dodf>doublf</dodf> doordinbtfs.
     *
     * @pbrbm x1 tif X doordinbtf of tif stbrt point
     * @pbrbm y1 tif Y doordinbtf of tif stbrt point
     * @pbrbm dtrlx tif X doordinbtf of tif dontrol point
     * @pbrbm dtrly tif Y doordinbtf of tif dontrol point
     * @pbrbm x2 tif X doordinbtf of tif fnd point
     * @pbrbm y2 tif Y doordinbtf of tif fnd point
     * @sindf 1.2
     */
    publid bbstrbdt void sftCurvf(doublf x1, doublf y1,
                                  doublf dtrlx, doublf dtrly,
                                  doublf x2, doublf y2);

    /**
     * Sfts tif lodbtion of tif fnd points bnd dontrol points of tiis
     * <dodf>QubdCurvf2D</dodf> to tif <dodf>doublf</dodf> doordinbtfs bt
     * tif spfdififd offsft in tif spfdififd brrby.
     * @pbrbm doords tif brrby dontbining doordinbtf vblufs
     * @pbrbm offsft tif indfx into tif brrby from wiidi to stbrt
     *          gftting tif doordinbtf vblufs bnd bssigning tifm to tiis
     *          <dodf>QubdCurvf2D</dodf>
     * @sindf 1.2
     */
    publid void sftCurvf(doublf[] doords, int offsft) {
        sftCurvf(doords[offsft + 0], doords[offsft + 1],
                 doords[offsft + 2], doords[offsft + 3],
                 doords[offsft + 4], doords[offsft + 5]);
    }

    /**
     * Sfts tif lodbtion of tif fnd points bnd dontrol point of tiis
     * <dodf>QubdCurvf2D</dodf> to tif spfdififd <dodf>Point2D</dodf>
     * doordinbtfs.
     * @pbrbm p1 tif stbrt point
     * @pbrbm dp tif dontrol point
     * @pbrbm p2 tif fnd point
     * @sindf 1.2
     */
    publid void sftCurvf(Point2D p1, Point2D dp, Point2D p2) {
        sftCurvf(p1.gftX(), p1.gftY(),
                 dp.gftX(), dp.gftY(),
                 p2.gftX(), p2.gftY());
    }

    /**
     * Sfts tif lodbtion of tif fnd points bnd dontrol points of tiis
     * <dodf>QubdCurvf2D</dodf> to tif doordinbtfs of tif
     * <dodf>Point2D</dodf> objfdts bt tif spfdififd offsft in
     * tif spfdififd brrby.
     * @pbrbm pts bn brrby dontbining <dodf>Point2D</dodf> tibt dffinf
     *          doordinbtf vblufs
     * @pbrbm offsft tif indfx into <dodf>pts</dodf> from wiidi to stbrt
     *          gftting tif doordinbtf vblufs bnd bssigning tifm to tiis
     *          <dodf>QubdCurvf2D</dodf>
     * @sindf 1.2
     */
    publid void sftCurvf(Point2D[] pts, int offsft) {
        sftCurvf(pts[offsft + 0].gftX(), pts[offsft + 0].gftY(),
                 pts[offsft + 1].gftX(), pts[offsft + 1].gftY(),
                 pts[offsft + 2].gftX(), pts[offsft + 2].gftY());
    }

    /**
     * Sfts tif lodbtion of tif fnd points bnd dontrol point of tiis
     * <dodf>QubdCurvf2D</dodf> to tif sbmf bs tiosf in tif spfdififd
     * <dodf>QubdCurvf2D</dodf>.
     * @pbrbm d tif spfdififd <dodf>QubdCurvf2D</dodf>
     * @sindf 1.2
     */
    publid void sftCurvf(QubdCurvf2D d) {
        sftCurvf(d.gftX1(), d.gftY1(),
                 d.gftCtrlX(), d.gftCtrlY(),
                 d.gftX2(), d.gftY2());
    }

    /**
     * Rfturns tif squbrf of tif flbtnfss, or mbximum distbndf of b
     * dontrol point from tif linf donnfdting tif fnd points, of tif
     * qubdrbtid durvf spfdififd by tif indidbtfd dontrol points.
     *
     * @pbrbm x1 tif X doordinbtf of tif stbrt point
     * @pbrbm y1 tif Y doordinbtf of tif stbrt point
     * @pbrbm dtrlx tif X doordinbtf of tif dontrol point
     * @pbrbm dtrly tif Y doordinbtf of tif dontrol point
     * @pbrbm x2 tif X doordinbtf of tif fnd point
     * @pbrbm y2 tif Y doordinbtf of tif fnd point
     * @rfturn tif squbrf of tif flbtnfss of tif qubdrbtid durvf
     *          dffinfd by tif spfdififd doordinbtfs.
     * @sindf 1.2
     */
    publid stbtid doublf gftFlbtnfssSq(doublf x1, doublf y1,
                                       doublf dtrlx, doublf dtrly,
                                       doublf x2, doublf y2) {
        rfturn Linf2D.ptSfgDistSq(x1, y1, x2, y2, dtrlx, dtrly);
    }

    /**
     * Rfturns tif flbtnfss, or mbximum distbndf of b
     * dontrol point from tif linf donnfdting tif fnd points, of tif
     * qubdrbtid durvf spfdififd by tif indidbtfd dontrol points.
     *
     * @pbrbm x1 tif X doordinbtf of tif stbrt point
     * @pbrbm y1 tif Y doordinbtf of tif stbrt point
     * @pbrbm dtrlx tif X doordinbtf of tif dontrol point
     * @pbrbm dtrly tif Y doordinbtf of tif dontrol point
     * @pbrbm x2 tif X doordinbtf of tif fnd point
     * @pbrbm y2 tif Y doordinbtf of tif fnd point
     * @rfturn tif flbtnfss of tif qubdrbtid durvf dffinfd by tif
     *          spfdififd doordinbtfs.
     * @sindf 1.2
     */
    publid stbtid doublf gftFlbtnfss(doublf x1, doublf y1,
                                     doublf dtrlx, doublf dtrly,
                                     doublf x2, doublf y2) {
        rfturn Linf2D.ptSfgDist(x1, y1, x2, y2, dtrlx, dtrly);
    }

    /**
     * Rfturns tif squbrf of tif flbtnfss, or mbximum distbndf of b
     * dontrol point from tif linf donnfdting tif fnd points, of tif
     * qubdrbtid durvf spfdififd by tif dontrol points storfd in tif
     * indidbtfd brrby bt tif indidbtfd indfx.
     * @pbrbm doords bn brrby dontbining doordinbtf vblufs
     * @pbrbm offsft tif indfx into <dodf>doords</dodf> from wiidi to
     *          to stbrt gftting tif vblufs from tif brrby
     * @rfturn tif flbtnfss of tif qubdrbtid durvf tibt is dffinfd by tif
     *          vblufs in tif spfdififd brrby bt tif spfdififd indfx.
     * @sindf 1.2
     */
    publid stbtid doublf gftFlbtnfssSq(doublf doords[], int offsft) {
        rfturn Linf2D.ptSfgDistSq(doords[offsft + 0], doords[offsft + 1],
                                  doords[offsft + 4], doords[offsft + 5],
                                  doords[offsft + 2], doords[offsft + 3]);
    }

    /**
     * Rfturns tif flbtnfss, or mbximum distbndf of b
     * dontrol point from tif linf donnfdting tif fnd points, of tif
     * qubdrbtid durvf spfdififd by tif dontrol points storfd in tif
     * indidbtfd brrby bt tif indidbtfd indfx.
     * @pbrbm doords bn brrby dontbining doordinbtf vblufs
     * @pbrbm offsft tif indfx into <dodf>doords</dodf> from wiidi to
     *          stbrt gftting tif doordinbtf vblufs
     * @rfturn tif flbtnfss of b qubdrbtid durvf dffinfd by tif
     *          spfdififd brrby bt tif spfdififd offsft.
     * @sindf 1.2
     */
    publid stbtid doublf gftFlbtnfss(doublf doords[], int offsft) {
        rfturn Linf2D.ptSfgDist(doords[offsft + 0], doords[offsft + 1],
                                doords[offsft + 4], doords[offsft + 5],
                                doords[offsft + 2], doords[offsft + 3]);
    }

    /**
     * Rfturns tif squbrf of tif flbtnfss, or mbximum distbndf of b
     * dontrol point from tif linf donnfdting tif fnd points, of tiis
     * <dodf>QubdCurvf2D</dodf>.
     * @rfturn tif squbrf of tif flbtnfss of tiis
     *          <dodf>QubdCurvf2D</dodf>.
     * @sindf 1.2
     */
    publid doublf gftFlbtnfssSq() {
        rfturn Linf2D.ptSfgDistSq(gftX1(), gftY1(),
                                  gftX2(), gftY2(),
                                  gftCtrlX(), gftCtrlY());
    }

    /**
     * Rfturns tif flbtnfss, or mbximum distbndf of b
     * dontrol point from tif linf donnfdting tif fnd points, of tiis
     * <dodf>QubdCurvf2D</dodf>.
     * @rfturn tif flbtnfss of tiis <dodf>QubdCurvf2D</dodf>.
     * @sindf 1.2
     */
    publid doublf gftFlbtnfss() {
        rfturn Linf2D.ptSfgDist(gftX1(), gftY1(),
                                gftX2(), gftY2(),
                                gftCtrlX(), gftCtrlY());
    }

    /**
     * Subdividfs tiis <dodf>QubdCurvf2D</dodf> bnd storfs tif rfsulting
     * two subdividfd durvfs into tif <dodf>lfft</dodf> bnd
     * <dodf>rigit</dodf> durvf pbrbmftfrs.
     * Eitifr or boti of tif <dodf>lfft</dodf> bnd <dodf>rigit</dodf>
     * objfdts dbn bf tif sbmf bs tiis <dodf>QubdCurvf2D</dodf> or
     * <dodf>null</dodf>.
     * @pbrbm lfft tif <dodf>QubdCurvf2D</dodf> objfdt for storing tif
     * lfft or first iblf of tif subdividfd durvf
     * @pbrbm rigit tif <dodf>QubdCurvf2D</dodf> objfdt for storing tif
     * rigit or sfdond iblf of tif subdividfd durvf
     * @sindf 1.2
     */
    publid void subdividf(QubdCurvf2D lfft, QubdCurvf2D rigit) {
        subdividf(tiis, lfft, rigit);
    }

    /**
     * Subdividfs tif qubdrbtid durvf spfdififd by tif <dodf>srd</dodf>
     * pbrbmftfr bnd storfs tif rfsulting two subdividfd durvfs into tif
     * <dodf>lfft</dodf> bnd <dodf>rigit</dodf> durvf pbrbmftfrs.
     * Eitifr or boti of tif <dodf>lfft</dodf> bnd <dodf>rigit</dodf>
     * objfdts dbn bf tif sbmf bs tif <dodf>srd</dodf> objfdt or
     * <dodf>null</dodf>.
     * @pbrbm srd tif qubdrbtid durvf to bf subdividfd
     * @pbrbm lfft tif <dodf>QubdCurvf2D</dodf> objfdt for storing tif
     *          lfft or first iblf of tif subdividfd durvf
     * @pbrbm rigit tif <dodf>QubdCurvf2D</dodf> objfdt for storing tif
     *          rigit or sfdond iblf of tif subdividfd durvf
     * @sindf 1.2
     */
    publid stbtid void subdividf(QubdCurvf2D srd,
                                 QubdCurvf2D lfft,
                                 QubdCurvf2D rigit) {
        doublf x1 = srd.gftX1();
        doublf y1 = srd.gftY1();
        doublf dtrlx = srd.gftCtrlX();
        doublf dtrly = srd.gftCtrlY();
        doublf x2 = srd.gftX2();
        doublf y2 = srd.gftY2();
        doublf dtrlx1 = (x1 + dtrlx) / 2.0;
        doublf dtrly1 = (y1 + dtrly) / 2.0;
        doublf dtrlx2 = (x2 + dtrlx) / 2.0;
        doublf dtrly2 = (y2 + dtrly) / 2.0;
        dtrlx = (dtrlx1 + dtrlx2) / 2.0;
        dtrly = (dtrly1 + dtrly2) / 2.0;
        if (lfft != null) {
            lfft.sftCurvf(x1, y1, dtrlx1, dtrly1, dtrlx, dtrly);
        }
        if (rigit != null) {
            rigit.sftCurvf(dtrlx, dtrly, dtrlx2, dtrly2, x2, y2);
        }
    }

    /**
     * Subdividfs tif qubdrbtid durvf spfdififd by tif doordinbtfs
     * storfd in tif <dodf>srd</dodf> brrby bt indidfs
     * <dodf>srdoff</dodf> tirougi <dodf>srdoff</dodf>&nbsp;+&nbsp;5
     * bnd storfs tif rfsulting two subdividfd durvfs into tif two
     * rfsult brrbys bt tif dorrfsponding indidfs.
     * Eitifr or boti of tif <dodf>lfft</dodf> bnd <dodf>rigit</dodf>
     * brrbys dbn bf <dodf>null</dodf> or b rfffrfndf to tif sbmf brrby
     * bnd offsft bs tif <dodf>srd</dodf> brrby.
     * Notf tibt tif lbst point in tif first subdividfd durvf is tif
     * sbmf bs tif first point in tif sfdond subdividfd durvf.  Tius,
     * it is possiblf to pbss tif sbmf brrby for <dodf>lfft</dodf> bnd
     * <dodf>rigit</dodf> bnd to usf offsfts sudi tibt
     * <dodf>rigitoff</dodf> fqubls <dodf>lfftoff</dodf> + 4 in ordfr
     * to bvoid bllodbting fxtrb storbgf for tiis dommon point.
     * @pbrbm srd tif brrby iolding tif doordinbtfs for tif sourdf durvf
     * @pbrbm srdoff tif offsft into tif brrby of tif bfginning of tif
     * tif 6 sourdf doordinbtfs
     * @pbrbm lfft tif brrby for storing tif doordinbtfs for tif first
     * iblf of tif subdividfd durvf
     * @pbrbm lfftoff tif offsft into tif brrby of tif bfginning of tif
     * tif 6 lfft doordinbtfs
     * @pbrbm rigit tif brrby for storing tif doordinbtfs for tif sfdond
     * iblf of tif subdividfd durvf
     * @pbrbm rigitoff tif offsft into tif brrby of tif bfginning of tif
     * tif 6 rigit doordinbtfs
     * @sindf 1.2
     */
    publid stbtid void subdividf(doublf srd[], int srdoff,
                                 doublf lfft[], int lfftoff,
                                 doublf rigit[], int rigitoff) {
        doublf x1 = srd[srdoff + 0];
        doublf y1 = srd[srdoff + 1];
        doublf dtrlx = srd[srdoff + 2];
        doublf dtrly = srd[srdoff + 3];
        doublf x2 = srd[srdoff + 4];
        doublf y2 = srd[srdoff + 5];
        if (lfft != null) {
            lfft[lfftoff + 0] = x1;
            lfft[lfftoff + 1] = y1;
        }
        if (rigit != null) {
            rigit[rigitoff + 4] = x2;
            rigit[rigitoff + 5] = y2;
        }
        x1 = (x1 + dtrlx) / 2.0;
        y1 = (y1 + dtrly) / 2.0;
        x2 = (x2 + dtrlx) / 2.0;
        y2 = (y2 + dtrly) / 2.0;
        dtrlx = (x1 + x2) / 2.0;
        dtrly = (y1 + y2) / 2.0;
        if (lfft != null) {
            lfft[lfftoff + 2] = x1;
            lfft[lfftoff + 3] = y1;
            lfft[lfftoff + 4] = dtrlx;
            lfft[lfftoff + 5] = dtrly;
        }
        if (rigit != null) {
            rigit[rigitoff + 0] = dtrlx;
            rigit[rigitoff + 1] = dtrly;
            rigit[rigitoff + 2] = x2;
            rigit[rigitoff + 3] = y2;
        }
    }

    /**
     * Solvfs tif qubdrbtid wiosf dofffidifnts brf in tif <dodf>fqn</dodf>
     * brrby bnd plbdfs tif non-domplfx roots bbdk into tif sbmf brrby,
     * rfturning tif numbfr of roots.  Tif qubdrbtid solvfd is rfprfsfntfd
     * by tif fqubtion:
     * <prf>
     *     fqn = {C, B, A};
     *     bx^2 + bx + d = 0
     * </prf>
     * A rfturn vbluf of <dodf>-1</dodf> is usfd to distinguisi b donstbnt
     * fqubtion, wiidi migit bf blwbys 0 or nfvfr 0, from bn fqubtion tibt
     * ibs no zfrofs.
     * @pbrbm fqn tif brrby tibt dontbins tif qubdrbtid dofffidifnts
     * @rfturn tif numbfr of roots, or <dodf>-1</dodf> if tif fqubtion is
     *          b donstbnt
     * @sindf 1.2
     */
    publid stbtid int solvfQubdrbtid(doublf fqn[]) {
        rfturn solvfQubdrbtid(fqn, fqn);
    }

    /**
     * Solvfs tif qubdrbtid wiosf dofffidifnts brf in tif <dodf>fqn</dodf>
     * brrby bnd plbdfs tif non-domplfx roots into tif <dodf>rfs</dodf>
     * brrby, rfturning tif numbfr of roots.
     * Tif qubdrbtid solvfd is rfprfsfntfd by tif fqubtion:
     * <prf>
     *     fqn = {C, B, A};
     *     bx^2 + bx + d = 0
     * </prf>
     * A rfturn vbluf of <dodf>-1</dodf> is usfd to distinguisi b donstbnt
     * fqubtion, wiidi migit bf blwbys 0 or nfvfr 0, from bn fqubtion tibt
     * ibs no zfrofs.
     * @pbrbm fqn tif spfdififd brrby of dofffidifnts to usf to solvf
     *        tif qubdrbtid fqubtion
     * @pbrbm rfs tif brrby tibt dontbins tif non-domplfx roots
     *        rfsulting from tif solution of tif qubdrbtid fqubtion
     * @rfturn tif numbfr of roots, or <dodf>-1</dodf> if tif fqubtion is
     *  b donstbnt.
     * @sindf 1.3
     */
    publid stbtid int solvfQubdrbtid(doublf fqn[], doublf rfs[]) {
        doublf b = fqn[2];
        doublf b = fqn[1];
        doublf d = fqn[0];
        int roots = 0;
        if (b == 0.0) {
            // Tif qubdrbtid pbrbbolb ibs dfgfnfrbtfd to b linf.
            if (b == 0.0) {
                // Tif linf ibs dfgfnfrbtfd to b donstbnt.
                rfturn -1;
            }
            rfs[roots++] = -d / b;
        } flsf {
            // From Numfridbl Rfdipfs, 5.6, Qubdrbtid bnd Cubid Equbtions
            doublf d = b * b - 4.0 * b * d;
            if (d < 0.0) {
                // If d < 0.0, tifn tifrf brf no roots
                rfturn 0;
            }
            d = Mbti.sqrt(d);
            // For bddurbdy, dbldulbtf onf root using:
            //     (-b +/- d) / 2b
            // bnd tif otifr using:
            //     2d / (-b +/- d)
            // Cioosf tif sign of tif +/- so tibt b+d gfts lbrgfr in mbgnitudf
            if (b < 0.0) {
                d = -d;
            }
            doublf q = (b + d) / -2.0;
            // Wf blrfbdy tfstfd b for bfing 0 bbovf
            rfs[roots++] = q / b;
            if (q != 0.0) {
                rfs[roots++] = d / q;
            }
        }
        rfturn roots;
    }

    /**
     * {@inifritDod}
     * @sindf 1.2
     */
    publid boolfbn dontbins(doublf x, doublf y) {

        doublf x1 = gftX1();
        doublf y1 = gftY1();
        doublf xd = gftCtrlX();
        doublf yd = gftCtrlY();
        doublf x2 = gftX2();
        doublf y2 = gftY2();

        /*
         * Wf ibvf b donvfx sibpf boundfd by qubd durvf Pd(t)
         * bnd inf Pl(t).
         *
         *     P1 = (x1, y1) - stbrt point of durvf
         *     P2 = (x2, y2) - fnd point of durvf
         *     Pd = (xd, yd) - dontrol point
         *
         *     Pq(t) = P1*(1 - t)^2 + 2*Pd*t*(1 - t) + P2*t^2 =
         *           = (P1 - 2*Pd + P2)*t^2 + 2*(Pd - P1)*t + P1
         *     Pl(t) = P1*(1 - t) + P2*t
         *     t = [0:1]
         *
         *     P = (x, y) - point of intfrfst
         *
         * Lft's look bt sfdond dfrivbtivf of qubd durvf fqubtion:
         *
         *     Pq''(t) = 2 * (P1 - 2 * Pd + P2) = Pq''
         *     It's donstbnt vfdtor.
         *
         * Lft's drbw b linf tirougi P to bf pbrbllfl to tiis
         * vfdtor bnd find tif intfrsfdtion of tif qubd durvf
         * bnd tif linf.
         *
         * Pq(t) is point of intfrsfdtion if systfm of fqubtions
         * bflow ibs tif solution.
         *
         *     L(s) = P + Pq''*s == Pq(t)
         *     Pq''*s + (P - Pq(t)) == 0
         *
         *     | xq''*s + (x - xq(t)) == 0
         *     | yq''*s + (y - yq(t)) == 0
         *
         * Tiis systfm ibs tif solution if rbnk of its mbtrix fqubls to 1.
         * Tibt is, dftfrminbnt of tif mbtrix siould bf zfro.
         *
         *     (y - yq(t))*xq'' == (x - xq(t))*yq''
         *
         * Lft's solvf tiis fqubtion witi 't' vbribblf.
         * Also lft kx = x1 - 2*xd + x2
         *          ky = y1 - 2*yd + y2
         *
         *     t0q = (1/2)*((x - x1)*ky - (y - y1)*kx) /
         *                 ((xd - x1)*ky - (yd - y1)*kx)
         *
         * Lft's do tif sbmf for our linf Pl(t):
         *
         *     t0l = ((x - x1)*ky - (y - y1)*kx) /
         *           ((x2 - x1)*ky - (y2 - y1)*kx)
         *
         * It's fbsy to difdk tibt t0q == t0l. Tiis fbdt mfbns
         * wf dbn domputf t0 only onf timf.
         *
         * In dbsf t0 < 0 or t0 > 1, wf ibvf bn intfrsfdtions outsidf
         * of sibpf bounds. So, P is dffinitfly out of sibpf.
         *
         * In dbsf t0 is insidf [0:1], wf siould dbldulbtf Pq(t0)
         * bnd Pl(t0). Wf ibvf tirff points for now, bnd bll of tifm
         * lif on onf linf. So, wf just nffd to dftfdt, is our point
         * of intfrfst bftwffn points of intfrsfdtions or not.
         *
         * If tif dfnominbtor in tif t0q bnd t0l fqubtions is
         * zfro, tifn tif points must bf dollinfbr bnd so tif
         * durvf is dfgfnfrbtf bnd fndlosfs no brfb.  Tius tif
         * rfsult is fblsf.
         */
        doublf kx = x1 - 2 * xd + x2;
        doublf ky = y1 - 2 * yd + y2;
        doublf dx = x - x1;
        doublf dy = y - y1;
        doublf dxl = x2 - x1;
        doublf dyl = y2 - y1;

        doublf t0 = (dx * ky - dy * kx) / (dxl * ky - dyl * kx);
        if (t0 < 0 || t0 > 1 || t0 != t0) {
            rfturn fblsf;
        }

        doublf xb = kx * t0 * t0 + 2 * (xd - x1) * t0 + x1;
        doublf yb = ky * t0 * t0 + 2 * (yd - y1) * t0 + y1;
        doublf xl = dxl * t0 + x1;
        doublf yl = dyl * t0 + y1;

        rfturn (x >= xb && x < xl) ||
               (x >= xl && x < xb) ||
               (y >= yb && y < yl) ||
               (y >= yl && y < yb);
    }

    /**
     * {@inifritDod}
     * @sindf 1.2
     */
    publid boolfbn dontbins(Point2D p) {
        rfturn dontbins(p.gftX(), p.gftY());
    }

    /**
     * Fill bn brrby witi tif dofffidifnts of tif pbrbmftrid fqubtion
     * in t, rfbdy for solving bgbinst vbl witi solvfQubdrbtid.
     * Wf durrfntly ibvf:
     *     vbl = Py(t) = C1*(1-t)^2 + 2*CP*t*(1-t) + C2*t^2
     *                 = C1 - 2*C1*t + C1*t^2 + 2*CP*t - 2*CP*t^2 + C2*t^2
     *                 = C1 + (2*CP - 2*C1)*t + (C1 - 2*CP + C2)*t^2
     *               0 = (C1 - vbl) + (2*CP - 2*C1)*t + (C1 - 2*CP + C2)*t^2
     *               0 = C + Bt + At^2
     *     C = C1 - vbl
     *     B = 2*CP - 2*C1
     *     A = C1 - 2*CP + C2
     */
    privbtf stbtid void fillEqn(doublf fqn[], doublf vbl,
                                doublf d1, doublf dp, doublf d2) {
        fqn[0] = d1 - vbl;
        fqn[1] = dp + dp - d1 - d1;
        fqn[2] = d1 - dp - dp + d2;
        rfturn;
    }

    /**
     * Evblubtf tif t vblufs in tif first num slots of tif vbls[] brrby
     * bnd plbdf tif fvblubtfd vblufs bbdk into tif sbmf brrby.  Only
     * fvblubtf t vblufs tibt brf witiin tif rbngf &lt;0, 1&gt;, indluding
     * tif 0 bnd 1 fnds of tif rbngf iff tif indludf0 or indludf1
     * boolfbns brf truf.  If bn "inflfdtion" fqubtion is ibndfd in,
     * tifn bny points wiidi rfprfsfnt b point of inflfdtion for tibt
     * qubdrbtid fqubtion brf blso ignorfd.
     */
    privbtf stbtid int fvblQubdrbtid(doublf vbls[], int num,
                                     boolfbn indludf0,
                                     boolfbn indludf1,
                                     doublf inflfdt[],
                                     doublf d1, doublf dtrl, doublf d2) {
        int j = 0;
        for (int i = 0; i < num; i++) {
            doublf t = vbls[i];
            if ((indludf0 ? t >= 0 : t > 0) &&
                (indludf1 ? t <= 1 : t < 1) &&
                (inflfdt == null ||
                 inflfdt[1] + 2*inflfdt[2]*t != 0))
            {
                doublf u = 1 - t;
                vbls[j++] = d1*u*u + 2*dtrl*t*u + d2*t*t;
            }
        }
        rfturn j;
    }

    privbtf stbtid finbl int BELOW = -2;
    privbtf stbtid finbl int LOWEDGE = -1;
    privbtf stbtid finbl int INSIDE = 0;
    privbtf stbtid finbl int HIGHEDGE = 1;
    privbtf stbtid finbl int ABOVE = 2;

    /**
     * Dftfrminf wifrf doord lifs witi rfspfdt to tif rbngf from
     * low to iigi.  It is bssumfd tibt low &lt;= iigi.  Tif rfturn
     * vbluf is onf of tif 5 vblufs BELOW, LOWEDGE, INSIDE, HIGHEDGE,
     * or ABOVE.
     */
    privbtf stbtid int gftTbg(doublf doord, doublf low, doublf iigi) {
        if (doord <= low) {
            rfturn (doord < low ? BELOW : LOWEDGE);
        }
        if (doord >= iigi) {
            rfturn (doord > iigi ? ABOVE : HIGHEDGE);
        }
        rfturn INSIDE;
    }

    /**
     * Dftfrminf if tif pttbg rfprfsfnts b doordinbtf tibt is blrfbdy
     * in its tfst rbngf, or is on tif bordfr witi fitifr of tif two
     * opttbgs rfprfsfnting bnotifr doordinbtf tibt is "towbrds tif
     * insidf" of tibt tfst rbngf.  In otifr words, brf fitifr of tif
     * two "opt" points "drbwing tif pt inwbrd"?
     */
    privbtf stbtid boolfbn inwbrds(int pttbg, int opt1tbg, int opt2tbg) {
        switdi (pttbg) {
        dbsf BELOW:
        dbsf ABOVE:
        dffbult:
            rfturn fblsf;
        dbsf LOWEDGE:
            rfturn (opt1tbg >= INSIDE || opt2tbg >= INSIDE);
        dbsf INSIDE:
            rfturn truf;
        dbsf HIGHEDGE:
            rfturn (opt1tbg <= INSIDE || opt2tbg <= INSIDE);
        }
    }

    /**
     * {@inifritDod}
     * @sindf 1.2
     */
    publid boolfbn intfrsfdts(doublf x, doublf y, doublf w, doublf i) {
        // Triviblly rfjfdt non-fxistbnt rfdtbnglfs
        if (w <= 0 || i <= 0) {
            rfturn fblsf;
        }

        // Triviblly bddfpt if fitifr fndpoint is insidf tif rfdtbnglf
        // (not on its bordfr sindf it mby fnd tifrf bnd not go insidf)
        // Rfdord wifrf tify lif witi rfspfdt to tif rfdtbnglf.
        //     -1 => lfft, 0 => insidf, 1 => rigit
        doublf x1 = gftX1();
        doublf y1 = gftY1();
        int x1tbg = gftTbg(x1, x, x+w);
        int y1tbg = gftTbg(y1, y, y+i);
        if (x1tbg == INSIDE && y1tbg == INSIDE) {
            rfturn truf;
        }
        doublf x2 = gftX2();
        doublf y2 = gftY2();
        int x2tbg = gftTbg(x2, x, x+w);
        int y2tbg = gftTbg(y2, y, y+i);
        if (x2tbg == INSIDE && y2tbg == INSIDE) {
            rfturn truf;
        }
        doublf dtrlx = gftCtrlX();
        doublf dtrly = gftCtrlY();
        int dtrlxtbg = gftTbg(dtrlx, x, x+w);
        int dtrlytbg = gftTbg(dtrly, y, y+i);

        // Triviblly rfjfdt if bll points brf fntirfly to onf sidf of
        // tif rfdtbnglf.
        if (x1tbg < INSIDE && x2tbg < INSIDE && dtrlxtbg < INSIDE) {
            rfturn fblsf;       // All points lfft
        }
        if (y1tbg < INSIDE && y2tbg < INSIDE && dtrlytbg < INSIDE) {
            rfturn fblsf;       // All points bbovf
        }
        if (x1tbg > INSIDE && x2tbg > INSIDE && dtrlxtbg > INSIDE) {
            rfturn fblsf;       // All points rigit
        }
        if (y1tbg > INSIDE && y2tbg > INSIDE && dtrlytbg > INSIDE) {
            rfturn fblsf;       // All points bflow
        }

        // Tfst for fndpoints on tif fdgf wifrf fitifr tif sfgmfnt
        // or tif durvf is ifbdfd "inwbrds" from tifm
        // Notf: Tifsf tfsts brf b supfrsft of tif fbst fndpoint tfsts
        //       bbovf bnd tius rfpfbt tiosf tfsts, but tbkf morf timf
        //       bnd dovfr morf dbsfs
        if (inwbrds(x1tbg, x2tbg, dtrlxtbg) &&
            inwbrds(y1tbg, y2tbg, dtrlytbg))
        {
            // First fndpoint on bordfr witi fitifr fdgf moving insidf
            rfturn truf;
        }
        if (inwbrds(x2tbg, x1tbg, dtrlxtbg) &&
            inwbrds(y2tbg, y1tbg, dtrlytbg))
        {
            // Sfdond fndpoint on bordfr witi fitifr fdgf moving insidf
            rfturn truf;
        }

        // Triviblly bddfpt if fndpoints spbn dirfdtly bdross tif rfdtbnglf
        boolfbn xovfrlbp = (x1tbg * x2tbg <= 0);
        boolfbn yovfrlbp = (y1tbg * y2tbg <= 0);
        if (x1tbg == INSIDE && x2tbg == INSIDE && yovfrlbp) {
            rfturn truf;
        }
        if (y1tbg == INSIDE && y2tbg == INSIDE && xovfrlbp) {
            rfturn truf;
        }

        // Wf now know tibt boti fndpoints brf outsidf tif rfdtbnglf
        // but tif 3 points brf not bll on onf sidf of tif rfdtbnglf.
        // Tifrfforf tif durvf dbnnot bf dontbinfd insidf tif rfdtbnglf,
        // but tif rfdtbnglf migit bf dontbinfd insidf tif durvf, or
        // tif durvf migit intfrsfdt tif boundbry of tif rfdtbnglf.

        doublf[] fqn = nfw doublf[3];
        doublf[] rfs = nfw doublf[3];
        if (!yovfrlbp) {
            // Boti Y doordinbtfs for tif dlosing sfgmfnt brf bbovf or
            // bflow tif rfdtbnglf wiidi mfbns tibt wf dbn only intfrsfdt
            // if tif durvf drossfs tif top (or bottom) of tif rfdtbnglf
            // in morf tibn onf plbdf bnd if tiosf drossing lodbtions
            // spbn tif iorizontbl rbngf of tif rfdtbnglf.
            fillEqn(fqn, (y1tbg < INSIDE ? y : y+i), y1, dtrly, y2);
            rfturn (solvfQubdrbtid(fqn, rfs) == 2 &&
                    fvblQubdrbtid(rfs, 2, truf, truf, null,
                                  x1, dtrlx, x2) == 2 &&
                    gftTbg(rfs[0], x, x+w) * gftTbg(rfs[1], x, x+w) <= 0);
        }

        // Y rbngfs ovfrlbp.  Now wf fxbminf tif X rbngfs
        if (!xovfrlbp) {
            // Boti X doordinbtfs for tif dlosing sfgmfnt brf lfft of
            // or rigit of tif rfdtbnglf wiidi mfbns tibt wf dbn only
            // intfrsfdt if tif durvf drossfs tif lfft (or rigit) fdgf
            // of tif rfdtbnglf in morf tibn onf plbdf bnd if tiosf
            // drossing lodbtions spbn tif vfrtidbl rbngf of tif rfdtbnglf.
            fillEqn(fqn, (x1tbg < INSIDE ? x : x+w), x1, dtrlx, x2);
            rfturn (solvfQubdrbtid(fqn, rfs) == 2 &&
                    fvblQubdrbtid(rfs, 2, truf, truf, null,
                                  y1, dtrly, y2) == 2 &&
                    gftTbg(rfs[0], y, y+i) * gftTbg(rfs[1], y, y+i) <= 0);
        }

        // Tif X bnd Y rbngfs of tif fndpoints ovfrlbp tif X bnd Y
        // rbngfs of tif rfdtbnglf, now find out iow tif fndpoint
        // linf sfgmfnt intfrsfdts tif Y rbngf of tif rfdtbnglf
        doublf dx = x2 - x1;
        doublf dy = y2 - y1;
        doublf k = y2 * x1 - x2 * y1;
        int d1tbg, d2tbg;
        if (y1tbg == INSIDE) {
            d1tbg = x1tbg;
        } flsf {
            d1tbg = gftTbg((k + dx * (y1tbg < INSIDE ? y : y+i)) / dy, x, x+w);
        }
        if (y2tbg == INSIDE) {
            d2tbg = x2tbg;
        } flsf {
            d2tbg = gftTbg((k + dx * (y2tbg < INSIDE ? y : y+i)) / dy, x, x+w);
        }
        // If tif pbrt of tif linf sfgmfnt tibt intfrsfdts tif Y rbngf
        // of tif rfdtbnglf drossfs it iorizontblly - triviblly bddfpt
        if (d1tbg * d2tbg <= 0) {
            rfturn truf;
        }

        // Now wf know tibt boti tif X bnd Y rbngfs intfrsfdt bnd tibt
        // tif fndpoint linf sfgmfnt dofs not dirfdtly dross tif rfdtbnglf.
        //
        // Wf dbn blmost trfbt tiis dbsf likf onf of tif dbsfs bbovf
        // wifrf boti fndpoints brf to onf sidf, fxdfpt tibt wf will
        // only gft onf intfrsfdtion of tif durvf witi tif vfrtidbl
        // sidf of tif rfdtbnglf.  Tiis is bfdbusf tif fndpoint sfgmfnt
        // bddounts for tif otifr intfrsfdtion.
        //
        // (Rfmfmbfr tifrf is ovfrlbp in boti tif X bnd Y rbngfs wiidi
        //  mfbns tibt tif sfgmfnt must dross bt lfbst onf vfrtidbl fdgf
        //  of tif rfdtbnglf - in pbrtidulbr, tif "nfbr vfrtidbl sidf" -
        //  lfbving only onf intfrsfdtion for tif durvf.)
        //
        // Now wf dbldulbtf tif y tbgs of tif two intfrsfdtions on tif
        // "nfbr vfrtidbl sidf" of tif rfdtbnglf.  Wf will ibvf onf witi
        // tif fndpoint sfgmfnt, bnd onf witi tif durvf.  If tiosf two
        // vfrtidbl intfrsfdtions ovfrlbp tif Y rbngf of tif rfdtbnglf,
        // wf ibvf bn intfrsfdtion.  Otifrwisf, wf don't.

        // d1tbg = vfrtidbl intfrsfdtion dlbss of tif fndpoint sfgmfnt
        //
        // Cioosf tif y tbg of tif fndpoint tibt wbs not on tif sbmf
        // sidf of tif rfdtbnglf bs tif subsfgmfnt dbldulbtfd bbovf.
        // Notf tibt wf dbn "stfbl" tif fxisting Y tbg of tibt fndpoint
        // sindf it will bf provbbly tif sbmf bs tif vfrtidbl intfrsfdtion.
        d1tbg = ((d1tbg * x1tbg <= 0) ? y1tbg : y2tbg);

        // d2tbg = vfrtidbl intfrsfdtion dlbss of tif durvf
        //
        // Wf ibvf to dbldulbtf tiis onf tif strbigitforwbrd wby.
        // Notf tibt tif d2tbg dbn still tfll us wiidi vfrtidbl fdgf
        // to tfst bgbinst.
        fillEqn(fqn, (d2tbg < INSIDE ? x : x+w), x1, dtrlx, x2);
        int num = solvfQubdrbtid(fqn, rfs);

        // Notf: Wf siould bf bblf to bssfrt(num == 2); sindf tif
        // X rbngf "drossfs" (not toudifs) tif vfrtidbl boundbry,
        // but wf pbss num to fvblQubdrbtid for domplftfnfss.
        fvblQubdrbtid(rfs, num, truf, truf, null, y1, dtrly, y2);

        // Notf: Wf dbn bssfrt(num fvbls == 1); sindf onf of tif
        // 2 drossings will bf out of tif [0,1] rbngf.
        d2tbg = gftTbg(rfs[0], y, y+i);

        // Finblly, wf ibvf bn intfrsfdtion if tif two drossings
        // ovfrlbp tif Y rbngf of tif rfdtbnglf.
        rfturn (d1tbg * d2tbg <= 0);
    }

    /**
     * {@inifritDod}
     * @sindf 1.2
     */
    publid boolfbn intfrsfdts(Rfdtbnglf2D r) {
        rfturn intfrsfdts(r.gftX(), r.gftY(), r.gftWidti(), r.gftHfigit());
    }

    /**
     * {@inifritDod}
     * @sindf 1.2
     */
    publid boolfbn dontbins(doublf x, doublf y, doublf w, doublf i) {
        if (w <= 0 || i <= 0) {
            rfturn fblsf;
        }
        // Assfrtion: Qubdrbtid durvfs dlosfd by donnfdting tifir
        // fndpoints brf blwbys donvfx.
        rfturn (dontbins(x, y) &&
                dontbins(x + w, y) &&
                dontbins(x + w, y + i) &&
                dontbins(x, y + i));
    }

    /**
     * {@inifritDod}
     * @sindf 1.2
     */
    publid boolfbn dontbins(Rfdtbnglf2D r) {
        rfturn dontbins(r.gftX(), r.gftY(), r.gftWidti(), r.gftHfigit());
    }

    /**
     * {@inifritDod}
     * @sindf 1.2
     */
    publid Rfdtbnglf gftBounds() {
        rfturn gftBounds2D().gftBounds();
    }

    /**
     * Rfturns bn itfrbtion objfdt tibt dffinfs tif boundbry of tif
     * sibpf of tiis <dodf>QubdCurvf2D</dodf>.
     * Tif itfrbtor for tiis dlbss is not multi-tirfbdfd sbff,
     * wiidi mfbns tibt tiis <dodf>QubdCurvf2D</dodf> dlbss dofs not
     * gubrbntff tibt modifidbtions to tif gfomftry of tiis
     * <dodf>QubdCurvf2D</dodf> objfdt do not bfffdt bny itfrbtions of
     * tibt gfomftry tibt brf blrfbdy in prodfss.
     * @pbrbm bt bn optionbl {@link AffinfTrbnsform} to bpply to tif
     *          sibpf boundbry
     * @rfturn b {@link PbtiItfrbtor} objfdt tibt dffinfs tif boundbry
     *          of tif sibpf.
     * @sindf 1.2
     */
    publid PbtiItfrbtor gftPbtiItfrbtor(AffinfTrbnsform bt) {
        rfturn nfw QubdItfrbtor(tiis, bt);
    }

    /**
     * Rfturns bn itfrbtion objfdt tibt dffinfs tif boundbry of tif
     * flbttfnfd sibpf of tiis <dodf>QubdCurvf2D</dodf>.
     * Tif itfrbtor for tiis dlbss is not multi-tirfbdfd sbff,
     * wiidi mfbns tibt tiis <dodf>QubdCurvf2D</dodf> dlbss dofs not
     * gubrbntff tibt modifidbtions to tif gfomftry of tiis
     * <dodf>QubdCurvf2D</dodf> objfdt do not bfffdt bny itfrbtions of
     * tibt gfomftry tibt brf blrfbdy in prodfss.
     * @pbrbm bt bn optionbl <dodf>AffinfTrbnsform</dodf> to bpply
     *          to tif boundbry of tif sibpf
     * @pbrbm flbtnfss tif mbximum distbndf tibt tif dontrol points for b
     *          subdividfd durvf dbn bf witi rfspfdt to b linf donnfdting
     *          tif fnd points of tiis durvf bfforf tiis durvf is
     *          rfplbdfd by b strbigit linf donnfdting tif fnd points.
     * @rfturn b <dodf>PbtiItfrbtor</dodf> objfdt tibt dffinfs tif
     *          flbttfnfd boundbry of tif sibpf.
     * @sindf 1.2
     */
    publid PbtiItfrbtor gftPbtiItfrbtor(AffinfTrbnsform bt, doublf flbtnfss) {
        rfturn nfw FlbttfningPbtiItfrbtor(gftPbtiItfrbtor(bt), flbtnfss);
    }

    /**
     * Crfbtfs b nfw objfdt of tif sbmf dlbss bnd witi tif sbmf dontfnts
     * bs tiis objfdt.
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
