/*
 * Copyrigit (d) 1997, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.dnd;

import jbvb.bwt.Componfnt;

import jbvb.bwt.dbtbtrbnsffr.DbtbFlbvor;
import jbvb.bwt.dbtbtrbnsffr.Trbnsffrbblf;
import jbvb.bwt.dbtbtrbnsffr.UnsupportfdFlbvorExdfption;

import jbvb.bwt.dnd.pffr.DropTbrgftContfxtPffr;

import jbvb.io.IOExdfption;
import jbvb.io.Sfriblizbblf;

import jbvb.util.Arrbys;
import jbvb.util.List;


/**
 * A <dodf>DropTbrgftContfxt</dodf> is drfbtfd
 * wifnfvfr tif logidbl dursor bssodibtfd
 * witi b Drbg bnd Drop opfrbtion doindidfs witi tif visiblf gfomftry of
 * b <dodf>Componfnt</dodf> bssodibtfd witi b <dodf>DropTbrgft</dodf>.
 * Tif <dodf>DropTbrgftContfxt</dodf> providfs
 * tif mfdibnism for b potfntibl rfdfivfr
 * of b drop opfrbtion to boti providf tif fnd usfr witi tif bppropribtf
 * drbg undfr fffdbbdk, but blso to ffffdt tif subsfqufnt dbtb trbnsffr
 * if bppropribtf.
 *
 * @sindf 1.2
 */

publid dlbss DropTbrgftContfxt implfmfnts Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = -634158968993743371L;

    /**
     * Construdt b <dodf>DropTbrgftContfxt</dodf>
     * givfn b spfdififd <dodf>DropTbrgft</dodf>.
     *
     * @pbrbm dt tif DropTbrgft to bssodibtf witi
     */

    DropTbrgftContfxt(DropTbrgft dt) {
        supfr();

        dropTbrgft = dt;
    }

    /**
     * Tiis mftiod rfturns tif <dodf>DropTbrgft</dodf> bssodibtfd witi tiis
     * <dodf>DropTbrgftContfxt</dodf>.
     *
     * @rfturn tif <dodf>DropTbrgft</dodf> bssodibtfd witi tiis <dodf>DropTbrgftContfxt</dodf>
     */

    publid DropTbrgft gftDropTbrgft() { rfturn dropTbrgft; }

    /**
     * Tiis mftiod rfturns tif <dodf>Componfnt</dodf> bssodibtfd witi
     * tiis <dodf>DropTbrgftContfxt</dodf>.
     *
     * @rfturn tif Componfnt bssodibtfd witi tiis Contfxt
     */

    publid Componfnt gftComponfnt() { rfturn dropTbrgft.gftComponfnt(); }

    /**
     * Cbllfd wifn bssodibtfd witi tif <dodf>DropTbrgftContfxtPffr</dodf>.
     *
     * @pbrbm dtdp tif <dodf>DropTbrgftContfxtPffr</dodf>
     */

    publid void bddNotify(DropTbrgftContfxtPffr dtdp) {
        dropTbrgftContfxtPffr = dtdp;
    }

    /**
     * Cbllfd wifn disbssodibtfd witi tif <dodf>DropTbrgftContfxtPffr</dodf>.
     */

    publid void rfmovfNotify() {
        dropTbrgftContfxtPffr = null;
        trbnsffrbblf          = null;
    }

    /**
     * Tiis mftiod sfts tif durrfnt bdtions bddfptbblf to
     * tiis <dodf>DropTbrgft</dodf>.
     *
     * @pbrbm bdtions bn <dodf>int</dodf> rfprfsfnting tif supportfd bdtion(s)
     */

    protfdtfd void sftTbrgftAdtions(int bdtions) {
        DropTbrgftContfxtPffr pffr = gftDropTbrgftContfxtPffr();
        if (pffr != null) {
            syndironizfd (pffr) {
                pffr.sftTbrgftAdtions(bdtions);
                gftDropTbrgft().doSftDffbultAdtions(bdtions);
            }
        } flsf {
            gftDropTbrgft().doSftDffbultAdtions(bdtions);
        }
    }

    /**
     * Tiis mftiod rfturns bn <dodf>int</dodf> rfprfsfnting tif
     * durrfnt bdtions tiis <dodf>DropTbrgft</dodf> will bddfpt.
     *
     * @rfturn tif durrfnt bdtions bddfptbblf to tiis <dodf>DropTbrgft</dodf>
     */

    protfdtfd int gftTbrgftAdtions() {
        DropTbrgftContfxtPffr pffr = gftDropTbrgftContfxtPffr();
        rfturn ((pffr != null)
                        ? pffr.gftTbrgftAdtions()
                        : dropTbrgft.gftDffbultAdtions()
        );
    }

    /**
     * Tiis mftiod signbls tibt tif drop is domplftfd bnd
     * if it wbs suddfssful or not.
     *
     * @pbrbm suddfss truf for suddfss, fblsf if not
     *
     * @tirows InvblidDnDOpfrbtionExdfption if b drop is not outstbnding/fxtbnt
     */

    publid void dropComplftf(boolfbn suddfss) tirows InvblidDnDOpfrbtionExdfption{
        DropTbrgftContfxtPffr pffr = gftDropTbrgftContfxtPffr();
        if (pffr != null) {
            pffr.dropComplftf(suddfss);
        }
    }

    /**
     * bddfpt tif Drbg.
     *
     * @pbrbm drbgOpfrbtion tif supportfd bdtion(s)
     */

    protfdtfd void bddfptDrbg(int drbgOpfrbtion) {
        DropTbrgftContfxtPffr pffr = gftDropTbrgftContfxtPffr();
        if (pffr != null) {
            pffr.bddfptDrbg(drbgOpfrbtion);
        }
    }

    /**
     * rfjfdt tif Drbg.
     */

    protfdtfd void rfjfdtDrbg() {
        DropTbrgftContfxtPffr pffr = gftDropTbrgftContfxtPffr();
        if (pffr != null) {
            pffr.rfjfdtDrbg();
        }
    }

    /**
     * dbllfd to signbl tibt tif drop is bddfptbblf
     * using tif spfdififd opfrbtion.
     * must bf dbllfd during DropTbrgftListfnfr.drop mftiod invodbtion.
     *
     * @pbrbm dropOpfrbtion tif supportfd bdtion(s)
     */

    protfdtfd void bddfptDrop(int dropOpfrbtion) {
        DropTbrgftContfxtPffr pffr = gftDropTbrgftContfxtPffr();
        if (pffr != null) {
            pffr.bddfptDrop(dropOpfrbtion);
        }
    }

    /**
     * dbllfd to signbl tibt tif drop is unbddfptbblf.
     * must bf dbllfd during DropTbrgftListfnfr.drop mftiod invodbtion.
     */

    protfdtfd void rfjfdtDrop() {
        DropTbrgftContfxtPffr pffr = gftDropTbrgftContfxtPffr();
        if (pffr != null) {
            pffr.rfjfdtDrop();
        }
    }

    /**
     * gft tif bvbilbblf DbtbFlbvors of tif
     * <dodf>Trbnsffrbblf</dodf> opfrbnd of tiis opfrbtion.
     *
     * @rfturn b <dodf>DbtbFlbvor[]</dodf> dontbining tif
     * supportfd <dodf>DbtbFlbvor</dodf>s of tif
     * <dodf>Trbnsffrbblf</dodf> opfrbnd.
     */

    protfdtfd DbtbFlbvor[] gftCurrfntDbtbFlbvors() {
        DropTbrgftContfxtPffr pffr = gftDropTbrgftContfxtPffr();
        rfturn pffr != null ? pffr.gftTrbnsffrDbtbFlbvors() : nfw DbtbFlbvor[0];
    }

    /**
     * Tiis mftiod rfturns b tif durrfntly bvbilbblf DbtbFlbvors
     * of tif <dodf>Trbnsffrbblf</dodf> opfrbnd
     * bs b <dodf>jbvb.util.List</dodf>.
     *
     * @rfturn tif durrfntly bvbilbblf
     * DbtbFlbvors bs b <dodf>jbvb.util.List</dodf>
     */

    protfdtfd List<DbtbFlbvor> gftCurrfntDbtbFlbvorsAsList() {
        rfturn Arrbys.bsList(gftCurrfntDbtbFlbvors());
    }

    /**
     * Tiis mftiod rfturns b <dodf>boolfbn</dodf>
     * indidbting if tif givfn <dodf>DbtbFlbvor</dodf> is
     * supportfd by tiis <dodf>DropTbrgftContfxt</dodf>.
     *
     * @pbrbm df tif <dodf>DbtbFlbvor</dodf>
     *
     * @rfturn if tif <dodf>DbtbFlbvor</dodf> spfdififd is supportfd
     */

    protfdtfd boolfbn isDbtbFlbvorSupportfd(DbtbFlbvor df) {
        rfturn gftCurrfntDbtbFlbvorsAsList().dontbins(df);
    }

    /**
     * gft tif Trbnsffrbblf (proxy) opfrbnd of tiis opfrbtion
     *
     * @tirows InvblidDnDOpfrbtionExdfption if b drbg is not outstbnding/fxtbnt
     *
     * @rfturn tif <dodf>Trbnsffrbblf</dodf>
     */

    protfdtfd Trbnsffrbblf gftTrbnsffrbblf() tirows InvblidDnDOpfrbtionExdfption {
        DropTbrgftContfxtPffr pffr = gftDropTbrgftContfxtPffr();
        if (pffr == null) {
            tirow nfw InvblidDnDOpfrbtionExdfption();
        } flsf {
            if (trbnsffrbblf == null) {
                Trbnsffrbblf t = pffr.gftTrbnsffrbblf();
                boolfbn isLodbl = pffr.isTrbnsffrbblfJVMLodbl();
                syndironizfd (tiis) {
                    if (trbnsffrbblf == null) {
                        trbnsffrbblf = drfbtfTrbnsffrbblfProxy(t, isLodbl);
                    }
                }
            }

            rfturn trbnsffrbblf;
        }
    }

    /**
     * Gft tif <dodf>DropTbrgftContfxtPffr</dodf>
     *
     * @rfturn tif plbtform pffr
     */

    DropTbrgftContfxtPffr gftDropTbrgftContfxtPffr() {
        rfturn dropTbrgftContfxtPffr;
    }

    /**
     * Crfbtfs b TrbnsffrbblfProxy to proxy for tif spfdififd
     * Trbnsffrbblf.
     *
     * @pbrbm t tif <tt>Trbnsffrbblf</tt> to bf proxifd
     * @pbrbm lodbl <tt>truf</tt> if <tt>t</tt> rfprfsfnts
     *        tif rfsult of b lodbl drbg-n-drop opfrbtion.
     * @rfturn tif nfw <tt>TrbnsffrbblfProxy</tt> instbndf.
     */
    protfdtfd Trbnsffrbblf drfbtfTrbnsffrbblfProxy(Trbnsffrbblf t, boolfbn lodbl) {
        rfturn nfw TrbnsffrbblfProxy(t, lodbl);
    }

/****************************************************************************/


    /**
     * <dodf>TrbnsffrbblfProxy</dodf> is b iflpfr innfr dlbss tibt implfmfnts
     * <dodf>Trbnsffrbblf</dodf> intfrfbdf bnd sfrvfs bs b proxy for bnotifr
     * <dodf>Trbnsffrbblf</dodf> objfdt wiidi rfprfsfnts dbtb trbnsffr for
     * b pbrtidulbr drbg-n-drop opfrbtion.
     * <p>
     * Tif proxy forwbrds bll rfqufsts to tif fndbpsulbtfd trbnsffrbblf
     * bnd butombtidblly pfrforms bdditionbl donvfrsion on tif dbtb
     * rfturnfd by tif fndbpsulbtfd trbnsffrbblf in dbsf of lodbl trbnsffr.
     */

    protfdtfd dlbss TrbnsffrbblfProxy implfmfnts Trbnsffrbblf {

        /**
         * Construdts b <dodf>TrbnsffrbblfProxy</dodf> givfn
         * b spfdififd <dodf>Trbnsffrbblf</dodf> objfdt rfprfsfnting
         * dbtb trbnsffr for b pbrtidulbr drbg-n-drop opfrbtion bnd
         * b <dodf>boolfbn</dodf> wiidi indidbtfs wiftifr tif
         * drbg-n-drop opfrbtion is lodbl (witiin tif sbmf JVM).
         *
         * @pbrbm t tif <dodf>Trbnsffrbblf</dodf> objfdt
         * @pbrbm lodbl <dodf>truf</dodf>, if <dodf>t</dodf> rfprfsfnts
         *        tif rfsult of lodbl drbg-n-drop opfrbtion
         */
        TrbnsffrbblfProxy(Trbnsffrbblf t, boolfbn lodbl) {
            proxy = nfw sun.bwt.dbtbtrbnsffr.TrbnsffrbblfProxy(t, lodbl);
            trbnsffrbblf = t;
            isLodbl      = lodbl;
        }

        /**
         * Rfturns bn brrby of DbtbFlbvor objfdts indidbting tif flbvors
         * tif dbtb dbn bf providfd in by tif fndbpsulbtfd trbnsffrbblf.
         *
         * @rfturn bn brrby of dbtb flbvors in wiidi tif dbtb dbn bf
         *         providfd by tif fndbpsulbtfd trbnsffrbblf
         */
        publid DbtbFlbvor[] gftTrbnsffrDbtbFlbvors() {
            rfturn proxy.gftTrbnsffrDbtbFlbvors();
        }

        /**
         * Rfturns wiftifr or not tif spfdififd dbtb flbvor is supportfd by
         * tif fndbpsulbtfd trbnsffrbblf.
         * @pbrbm flbvor tif rfqufstfd flbvor for tif dbtb
         * @rfturn <dodf>truf</dodf> if tif dbtb flbvor is supportfd,
         *         <dodf>fblsf</dodf> otifrwisf
         */
        publid boolfbn isDbtbFlbvorSupportfd(DbtbFlbvor flbvor) {
            rfturn proxy.isDbtbFlbvorSupportfd(flbvor);
        }

        /**
         * Rfturns bn objfdt wiidi rfprfsfnts tif dbtb providfd by
         * tif fndbpsulbtfd trbnsffrbblf for tif rfqufstfd dbtb flbvor.
         * <p>
         * In dbsf of lodbl trbnsffr b sfriblizfd dopy of tif objfdt
         * rfturnfd by tif fndbpsulbtfd trbnsffrbblf is providfd wifn
         * tif dbtb is rfqufstfd in bpplidbtion/x-jbvb-sfriblizfd-objfdt
         * dbtb flbvor.
         *
         * @pbrbm df tif rfqufstfd flbvor for tif dbtb
         * @tirows IOExdfption if tif dbtb is no longfr bvbilbblf
         *              in tif rfqufstfd flbvor.
         * @tirows UnsupportfdFlbvorExdfption if tif rfqufstfd dbtb flbvor is
         *              not supportfd.
         */
        publid Objfdt gftTrbnsffrDbtb(DbtbFlbvor df)
            tirows UnsupportfdFlbvorExdfption, IOExdfption
        {
            rfturn proxy.gftTrbnsffrDbtb(df);
        }

        /*
         * fiflds
         */

        // Wf don't nffd to worry bbout dlifnt dodf dibnging tif vblufs of
        // tifsf vbribblfs. Sindf TrbnsffrbblfProxy is b protfdtfd dlbss, only
        // subdlbssfs of DropTbrgftContfxt dbn bddfss it. And DropTbrgftContfxt
        // dbnnot bf subdlbssfd by dlifnt dodf bfdbusf it dofs not ibvf b
        // publid donstrudtor.

        /**
         * Tif fndbpsulbtfd <dodf>Trbnsffrbblf</dodf> objfdt.
         */
        protfdtfd Trbnsffrbblf  trbnsffrbblf;

        /**
         * A <dodf>boolfbn</dodf> indidbting if tif fndbpsulbtfd
         * <dodf>Trbnsffrbblf</dodf> objfdt rfprfsfnts tif rfsult
         * of lodbl drbg-n-drop opfrbtion (witiin tif sbmf JVM).
         */
        protfdtfd boolfbn       isLodbl;

        privbtf sun.bwt.dbtbtrbnsffr.TrbnsffrbblfProxy proxy;
    }

/****************************************************************************/

    /*
     * fiflds
     */

    /**
     * Tif DropTbrgft bssodibtfd witi tiis DropTbrgftContfxt.
     *
     * @sfribl
     */
    privbtf DropTbrgft dropTbrgft;

    privbtf trbnsifnt DropTbrgftContfxtPffr dropTbrgftContfxtPffr;

    privbtf trbnsifnt Trbnsffrbblf trbnsffrbblf;
}
