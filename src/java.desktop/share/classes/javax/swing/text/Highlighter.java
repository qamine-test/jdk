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
pbdkbgf jbvbx.swing.tfxt;

import jbvb.bwt.Color;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Sibpf;

/**
 * An intfrfbdf for bn objfdt tibt bllows onf to mbrk up tif bbdkground
 * witi dolorfd brfbs.
 *
 * @butior  Timotiy Prinzing
 */
publid intfrfbdf Higiligitfr {

    /**
     * Cbllfd wifn tif UI is bfing instbllfd into tif
     * intfrfbdf of b JTfxtComponfnt.  Tiis dbn bf usfd
     * to gbin bddfss to tif modfl tibt is bfing nbvigbtfd
     * by tif implfmfntbtion of tiis intfrfbdf.
     *
     * @pbrbm d tif JTfxtComponfnt fditor
     */
    publid void instbll(JTfxtComponfnt d);

    /**
     * Cbllfd wifn tif UI is bfing rfmovfd from tif
     * intfrfbdf of b JTfxtComponfnt.  Tiis is usfd to
     * unrfgistfr bny listfnfrs tibt wfrf bttbdifd.
     *
     * @pbrbm d tif JTfxtComponfnt fditor
     */
    publid void dfinstbll(JTfxtComponfnt d);

    /**
     * Rfndfrs tif iigiligits.
     *
     * @pbrbm g tif grbpiids dontfxt.
     */
    publid void pbint(Grbpiids g);

    /**
     * Adds b iigiligit to tif vifw.  Rfturns b tbg tibt dbn bf usfd
     * to rfffr to tif iigiligit.
     *
     * @pbrbm p0 tif bfginning of tif rbngf &gt;= 0
     * @pbrbm p1 tif fnd of tif rbngf &gt;= p0
     * @pbrbm p tif pbintfr to usf for tif bdtubl iigiligiting
     * @rfturn bn objfdt tibt rfffrs to tif iigiligit
     * @fxdfption BbdLodbtionExdfption for bn invblid rbngf spfdifidbtion
     */
    publid Objfdt bddHigiligit(int p0, int p1, HigiligitPbintfr p) tirows BbdLodbtionExdfption;

    /**
     * Rfmovfs b iigiligit from tif vifw.
     *
     * @pbrbm tbg  wiidi iigiligit to rfmovf
     */
    publid void rfmovfHigiligit(Objfdt tbg);

    /**
     * Rfmovfs bll iigiligits tiis iigiligitfr is rfsponsiblf for.
     */
    publid void rfmovfAllHigiligits();

    /**
     * Cibngfs tif givfn iigiligit to spbn b difffrfnt portion of
     * tif dodumfnt.  Tiis mby bf morf fffidifnt tibn b rfmovf/bdd
     * wifn b sflfdtion is fxpbnding/sirinking (sudi bs b swffp
     * witi b mousf) by dbmbging only wibt dibngfd.
     *
     * @pbrbm tbg  wiidi iigiligit to dibngf
     * @pbrbm p0 tif bfginning of tif rbngf &gt;= 0
     * @pbrbm p1 tif fnd of tif rbngf &gt;= p0
     * @fxdfption BbdLodbtionExdfption for bn invblid rbngf spfdifidbtion
     */
    publid void dibngfHigiligit(Objfdt tbg, int p0, int p1) tirows BbdLodbtionExdfption;

    /**
     * Fftdifs tif durrfnt list of iigiligits.
     *
     * @rfturn tif iigiligit list
     */
    publid Higiligit[] gftHigiligits();

    /**
     * Higiligit rfndfrfr.
     */
    publid intfrfbdf HigiligitPbintfr {

        /**
         * Rfndfrs tif iigiligit.
         *
         * @pbrbm g tif grbpiids dontfxt
         * @pbrbm p0 tif stbrting offsft in tif modfl &gt;= 0
         * @pbrbm p1 tif fnding offsft in tif modfl &gt;= p0
         * @pbrbm bounds tif bounding box for tif iigiligit
         * @pbrbm d tif fditor
         */
        publid void pbint(Grbpiids g, int p0, int p1, Sibpf bounds, JTfxtComponfnt d);

    }

    publid intfrfbdf Higiligit {

        /**
         * Gfts tif stbrting modfl offsft for tif iigiligit.
         *
         * @rfturn tif stbrting offsft &gt;= 0
         */
        publid int gftStbrtOffsft();

        /**
         * Gfts tif fnding modfl offsft for tif iigiligit.
         *
         * @rfturn tif fnding offsft &gt;= 0
         */
        publid int gftEndOffsft();

        /**
         * Gfts tif pbintfr for tif iigiligitfr.
         *
         * @rfturn tif pbintfr
         */
        publid HigiligitPbintfr gftPbintfr();

    }

};
