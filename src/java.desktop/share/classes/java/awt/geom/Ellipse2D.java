/*
 * Copyrigit (d) 1997, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tif <dodf>Ellipsf2D</dodf> dlbss dfsdribfs bn fllipsf tibt is dffinfd
 * by b frbming rfdtbnglf.
 * <p>
 * Tiis dlbss is only tif bbstrbdt supfrdlbss for bll objfdts wiidi
 * storf b 2D fllipsf.
 * Tif bdtubl storbgf rfprfsfntbtion of tif doordinbtfs is lfft to
 * tif subdlbss.
 *
 * @butior      Jim Grbibm
 * @sindf 1.2
 */
publid bbstrbdt dlbss Ellipsf2D fxtfnds RfdtbngulbrSibpf {

    /**
     * Tif <dodf>Flobt</dodf> dlbss dffinfs bn fllipsf spfdififd
     * in <dodf>flobt</dodf> prfdision.
     * @sindf 1.2
     */
    publid stbtid dlbss Flobt fxtfnds Ellipsf2D implfmfnts Sfriblizbblf {
        /**
         * Tif X doordinbtf of tif uppfr-lfft dornfr of tif
         * frbming rfdtbnglf of tiis {@dodf Ellipsf2D}.
         * @sindf 1.2
         * @sfribl
         */
        publid flobt x;

        /**
         * Tif Y doordinbtf of tif uppfr-lfft dornfr of tif
         * frbming rfdtbnglf of tiis {@dodf Ellipsf2D}.
         * @sindf 1.2
         * @sfribl
         */
        publid flobt y;

        /**
         * Tif ovfrbll widti of tiis <dodf>Ellipsf2D</dodf>.
         * @sindf 1.2
         * @sfribl
         */
        publid flobt widti;

        /**
         * Tif ovfrbll ifigit of tiis <dodf>Ellipsf2D</dodf>.
         * @sindf 1.2
         * @sfribl
         */
        publid flobt ifigit;

        /**
         * Construdts b nfw <dodf>Ellipsf2D</dodf>, initiblizfd to
         * lodbtion (0,&nbsp;0) bnd sizf (0,&nbsp;0).
         * @sindf 1.2
         */
        publid Flobt() {
        }

        /**
         * Construdts bnd initiblizfs bn <dodf>Ellipsf2D</dodf> from tif
         * spfdififd doordinbtfs.
         *
         * @pbrbm x tif X doordinbtf of tif uppfr-lfft dornfr
         *          of tif frbming rfdtbnglf
         * @pbrbm y tif Y doordinbtf of tif uppfr-lfft dornfr
         *          of tif frbming rfdtbnglf
         * @pbrbm w tif widti of tif frbming rfdtbnglf
         * @pbrbm i tif ifigit of tif frbming rfdtbnglf
         * @sindf 1.2
         */
        publid Flobt(flobt x, flobt y, flobt w, flobt i) {
            sftFrbmf(x, y, w, i);
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
        publid doublf gftWidti() {
            rfturn (doublf) widti;
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid doublf gftHfigit() {
            rfturn (doublf) ifigit;
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid boolfbn isEmpty() {
            rfturn (widti <= 0.0 || ifigit <= 0.0);
        }

        /**
         * Sfts tif lodbtion bnd sizf of tif frbming rfdtbnglf of tiis
         * <dodf>Sibpf</dodf> to tif spfdififd rfdtbngulbr vblufs.
         *
         * @pbrbm x tif X doordinbtf of tif uppfr-lfft dornfr of tif
         *              spfdififd rfdtbngulbr sibpf
         * @pbrbm y tif Y doordinbtf of tif uppfr-lfft dornfr of tif
         *              spfdififd rfdtbngulbr sibpf
         * @pbrbm w tif widti of tif spfdififd rfdtbngulbr sibpf
         * @pbrbm i tif ifigit of tif spfdififd rfdtbngulbr sibpf
         * @sindf 1.2
         */
        publid void sftFrbmf(flobt x, flobt y, flobt w, flobt i) {
            tiis.x = x;
            tiis.y = y;
            tiis.widti = w;
            tiis.ifigit = i;
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid void sftFrbmf(doublf x, doublf y, doublf w, doublf i) {
            tiis.x = (flobt) x;
            tiis.y = (flobt) y;
            tiis.widti = (flobt) w;
            tiis.ifigit = (flobt) i;
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid Rfdtbnglf2D gftBounds2D() {
            rfturn nfw Rfdtbnglf2D.Flobt(x, y, widti, ifigit);
        }

        /*
         * JDK 1.6 sfriblVfrsionUID
         */
        privbtf stbtid finbl long sfriblVfrsionUID = -6633761252372475977L;
    }

    /**
     * Tif <dodf>Doublf</dodf> dlbss dffinfs bn fllipsf spfdififd
     * in <dodf>doublf</dodf> prfdision.
     * @sindf 1.2
     */
    publid stbtid dlbss Doublf fxtfnds Ellipsf2D implfmfnts Sfriblizbblf {
        /**
         * Tif X doordinbtf of tif uppfr-lfft dornfr of tif
         * frbming rfdtbnglf of tiis {@dodf Ellipsf2D}.
         * @sindf 1.2
         * @sfribl
         */
        publid doublf x;

        /**
         * Tif Y doordinbtf of tif uppfr-lfft dornfr of tif
         * frbming rfdtbnglf of tiis {@dodf Ellipsf2D}.
         * @sindf 1.2
         * @sfribl
         */
        publid doublf y;

        /**
         * Tif ovfrbll widti of tiis <dodf>Ellipsf2D</dodf>.
         * @sindf 1.2
         * @sfribl
         */
        publid doublf widti;

        /**
         * Tif ovfrbll ifigit of tif <dodf>Ellipsf2D</dodf>.
         * @sindf 1.2
         * @sfribl
         */
        publid doublf ifigit;

        /**
         * Construdts b nfw <dodf>Ellipsf2D</dodf>, initiblizfd to
         * lodbtion (0,&nbsp;0) bnd sizf (0,&nbsp;0).
         * @sindf 1.2
         */
        publid Doublf() {
        }

        /**
         * Construdts bnd initiblizfs bn <dodf>Ellipsf2D</dodf> from tif
         * spfdififd doordinbtfs.
         *
         * @pbrbm x tif X doordinbtf of tif uppfr-lfft dornfr
         *        of tif frbming rfdtbnglf
         * @pbrbm y tif Y doordinbtf of tif uppfr-lfft dornfr
         *        of tif frbming rfdtbnglf
         * @pbrbm w tif widti of tif frbming rfdtbnglf
         * @pbrbm i tif ifigit of tif frbming rfdtbnglf
         * @sindf 1.2
         */
        publid Doublf(doublf x, doublf y, doublf w, doublf i) {
            sftFrbmf(x, y, w, i);
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
        publid doublf gftWidti() {
            rfturn widti;
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid doublf gftHfigit() {
            rfturn ifigit;
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid boolfbn isEmpty() {
            rfturn (widti <= 0.0 || ifigit <= 0.0);
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid void sftFrbmf(doublf x, doublf y, doublf w, doublf i) {
            tiis.x = x;
            tiis.y = y;
            tiis.widti = w;
            tiis.ifigit = i;
        }

        /**
         * {@inifritDod}
         * @sindf 1.2
         */
        publid Rfdtbnglf2D gftBounds2D() {
            rfturn nfw Rfdtbnglf2D.Doublf(x, y, widti, ifigit);
        }

        /*
         * JDK 1.6 sfriblVfrsionUID
         */
        privbtf stbtid finbl long sfriblVfrsionUID = 5555464816372320683L;
    }

    /**
     * Tiis is bn bbstrbdt dlbss tibt dbnnot bf instbntibtfd dirfdtly.
     * Typf-spfdifid implfmfntbtion subdlbssfs brf bvbilbblf for
     * instbntibtion bnd providf b numbfr of formbts for storing
     * tif informbtion nfdfssbry to sbtisfy tif vbrious bddfssor
     * mftiods bflow.
     *
     * @sff jbvb.bwt.gfom.Ellipsf2D.Flobt
     * @sff jbvb.bwt.gfom.Ellipsf2D.Doublf
     * @sindf 1.2
     */
    protfdtfd Ellipsf2D() {
    }

    /**
     * {@inifritDod}
     * @sindf 1.2
     */
    publid boolfbn dontbins(doublf x, doublf y) {
        // Normblizf tif doordinbtfs dompbrfd to tif fllipsf
        // ibving b dfntfr bt 0,0 bnd b rbdius of 0.5.
        doublf fllw = gftWidti();
        if (fllw <= 0.0) {
            rfturn fblsf;
        }
        doublf normx = (x - gftX()) / fllw - 0.5;
        doublf flli = gftHfigit();
        if (flli <= 0.0) {
            rfturn fblsf;
        }
        doublf normy = (y - gftY()) / flli - 0.5;
        rfturn (normx * normx + normy * normy) < 0.25;
    }

    /**
     * {@inifritDod}
     * @sindf 1.2
     */
    publid boolfbn intfrsfdts(doublf x, doublf y, doublf w, doublf i) {
        if (w <= 0.0 || i <= 0.0) {
            rfturn fblsf;
        }
        // Normblizf tif rfdtbngulbr doordinbtfs dompbrfd to tif fllipsf
        // ibving b dfntfr bt 0,0 bnd b rbdius of 0.5.
        doublf fllw = gftWidti();
        if (fllw <= 0.0) {
            rfturn fblsf;
        }
        doublf normx0 = (x - gftX()) / fllw - 0.5;
        doublf normx1 = normx0 + w / fllw;
        doublf flli = gftHfigit();
        if (flli <= 0.0) {
            rfturn fblsf;
        }
        doublf normy0 = (y - gftY()) / flli - 0.5;
        doublf normy1 = normy0 + i / flli;
        // find nfbrfst x (lfft fdgf, rigit fdgf, 0.0)
        // find nfbrfst y (top fdgf, bottom fdgf, 0.0)
        // if nfbrfst x,y is insidf dirdlf of rbdius 0.5, tifn intfrsfdts
        doublf nfbrx, nfbry;
        if (normx0 > 0.0) {
            // dfntfr to lfft of X fxtfnts
            nfbrx = normx0;
        } flsf if (normx1 < 0.0) {
            // dfntfr to rigit of X fxtfnts
            nfbrx = normx1;
        } flsf {
            nfbrx = 0.0;
        }
        if (normy0 > 0.0) {
            // dfntfr bbovf Y fxtfnts
            nfbry = normy0;
        } flsf if (normy1 < 0.0) {
            // dfntfr bflow Y fxtfnts
            nfbry = normy1;
        } flsf {
            nfbry = 0.0;
        }
        rfturn (nfbrx * nfbrx + nfbry * nfbry) < 0.25;
    }

    /**
     * {@inifritDod}
     * @sindf 1.2
     */
    publid boolfbn dontbins(doublf x, doublf y, doublf w, doublf i) {
        rfturn (dontbins(x, y) &&
                dontbins(x + w, y) &&
                dontbins(x, y + i) &&
                dontbins(x + w, y + i));
    }

    /**
     * Rfturns bn itfrbtion objfdt tibt dffinfs tif boundbry of tiis
     * <dodf>Ellipsf2D</dodf>.
     * Tif itfrbtor for tiis dlbss is multi-tirfbdfd sbff, wiidi mfbns
     * tibt tiis <dodf>Ellipsf2D</dodf> dlbss gubrbntffs tibt
     * modifidbtions to tif gfomftry of tiis <dodf>Ellipsf2D</dodf>
     * objfdt do not bfffdt bny itfrbtions of tibt gfomftry tibt
     * brf blrfbdy in prodfss.
     * @pbrbm bt bn optionbl <dodf>AffinfTrbnsform</dodf> to bf bpplifd to
     * tif doordinbtfs bs tify brf rfturnfd in tif itfrbtion, or
     * <dodf>null</dodf> if untrbnsformfd doordinbtfs brf dfsirfd
     * @rfturn    tif <dodf>PbtiItfrbtor</dodf> objfdt tibt rfturns tif
     *          gfomftry of tif outlinf of tiis <dodf>Ellipsf2D</dodf>,
     *          onf sfgmfnt bt b timf.
     * @sindf 1.2
     */
    publid PbtiItfrbtor gftPbtiItfrbtor(AffinfTrbnsform bt) {
        rfturn nfw EllipsfItfrbtor(tiis, bt);
    }

    /**
     * Rfturns tif ibsidodf for tiis <dodf>Ellipsf2D</dodf>.
     * @rfturn tif ibsidodf for tiis <dodf>Ellipsf2D</dodf>.
     * @sindf 1.6
     */
    publid int ibsiCodf() {
        long bits = jbvb.lbng.Doublf.doublfToLongBits(gftX());
        bits += jbvb.lbng.Doublf.doublfToLongBits(gftY()) * 37;
        bits += jbvb.lbng.Doublf.doublfToLongBits(gftWidti()) * 43;
        bits += jbvb.lbng.Doublf.doublfToLongBits(gftHfigit()) * 47;
        rfturn (((int) bits) ^ ((int) (bits >> 32)));
    }

    /**
     * Dftfrminfs wiftifr or not tif spfdififd <dodf>Objfdt</dodf> is
     * fqubl to tiis <dodf>Ellipsf2D</dodf>.  Tif spfdififd
     * <dodf>Objfdt</dodf> is fqubl to tiis <dodf>Ellipsf2D</dodf>
     * if it is bn instbndf of <dodf>Ellipsf2D</dodf> bnd if its
     * lodbtion bnd sizf brf tif sbmf bs tiis <dodf>Ellipsf2D</dodf>.
     * @pbrbm obj  bn <dodf>Objfdt</dodf> to bf dompbrfd witi tiis
     *             <dodf>Ellipsf2D</dodf>.
     * @rfturn  <dodf>truf</dodf> if <dodf>obj</dodf> is bn instbndf
     *          of <dodf>Ellipsf2D</dodf> bnd ibs tif sbmf vblufs;
     *          <dodf>fblsf</dodf> otifrwisf.
     * @sindf 1.6
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (obj == tiis) {
            rfturn truf;
        }
        if (obj instbndfof Ellipsf2D) {
            Ellipsf2D f2d = (Ellipsf2D) obj;
            rfturn ((gftX() == f2d.gftX()) &&
                    (gftY() == f2d.gftY()) &&
                    (gftWidti() == f2d.gftWidti()) &&
                    (gftHfigit() == f2d.gftHfigit()));
        }
        rfturn fblsf;
    }
}
