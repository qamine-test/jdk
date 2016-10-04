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

pbdkbgf jbvb.bwt.dnd;

/**
 * Tif <dodf>DrbgSourdfDropEvfnt</dodf> is dflivfrfd
 * from tif <dodf>DrbgSourdfContfxtPffr</dodf>,
 * vib tif <dodf>DrbgSourdfContfxt</dodf>, to tif <dodf>drbgDropEnd</dodf>
 * mftiod of <dodf>DrbgSourdfListfnfr</dodf>s rfgistfrfd witi tibt
 * <dodf>DrbgSourdfContfxt</dodf> bnd witi its bssodibtfd
 * <dodf>DrbgSourdf</dodf>.
 * It dontbins suffidifnt informbtion for tif
 * originbtor of tif opfrbtion
 * to providf bppropribtf fffdbbdk to tif fnd usfr
 * wifn tif opfrbtion domplftfs.
 *
 * @sindf 1.2
 */

publid dlbss DrbgSourdfDropEvfnt fxtfnds DrbgSourdfEvfnt {

    privbtf stbtid finbl long sfriblVfrsionUID = -5571321229470821891L;

    /**
     * Construdt b <dodf>DrbgSourdfDropEvfnt</dodf> for b drop,
     * givfn tif
     * <dodf>DrbgSourdfContfxt</dodf>, tif drop bdtion,
     * bnd b <dodf>boolfbn</dodf> indidbting if tif drop wbs suddfssful.
     * Tif doordinbtfs for tiis <dodf>DrbgSourdfDropEvfnt</dodf>
     * brf not spfdififd, so <dodf>gftLodbtion</dodf> will rfturn
     * <dodf>null</dodf> for tiis fvfnt.
     * <p>
     * Tif brgumfnt <dodf>bdtion</dodf> siould bf onf of <dodf>DnDConstbnts</dodf>
     * tibt rfprfsfnts b singlf bdtion.
     * Tiis donstrudtor dofs not tirow bny fxdfption for invblid <dodf>bdtion</dodf>.
     *
     * @pbrbm dsd tif <dodf>DrbgSourdfContfxt</dodf>
     * bssodibtfd witi tiis <dodf>DrbgSourdfDropEvfnt</dodf>
     * @pbrbm bdtion tif drop bdtion
     * @pbrbm suddfss b boolfbn indidbting if tif drop wbs suddfssful
     *
     * @tirows IllfgblArgumfntExdfption if <dodf>dsd</dodf> is <dodf>null</dodf>.
     *
     * @sff DrbgSourdfEvfnt#gftLodbtion
     */

    publid DrbgSourdfDropEvfnt(DrbgSourdfContfxt dsd, int bdtion, boolfbn suddfss) {
        supfr(dsd);

        dropSuddfss = suddfss;
        dropAdtion  = bdtion;
    }

    /**
     * Construdt b <dodf>DrbgSourdfDropEvfnt</dodf> for b drop, givfn tif
     * <dodf>DrbgSourdfContfxt</dodf>, tif drop bdtion, b <dodf>boolfbn</dodf>
     * indidbting if tif drop wbs suddfssful, bnd doordinbtfs.
     * <p>
     * Tif brgumfnt <dodf>bdtion</dodf> siould bf onf of <dodf>DnDConstbnts</dodf>
     * tibt rfprfsfnts b singlf bdtion.
     * Tiis donstrudtor dofs not tirow bny fxdfption for invblid <dodf>bdtion</dodf>.
     *
     * @pbrbm dsd tif <dodf>DrbgSourdfContfxt</dodf>
     * bssodibtfd witi tiis <dodf>DrbgSourdfDropEvfnt</dodf>
     * @pbrbm bdtion tif drop bdtion
     * @pbrbm suddfss b boolfbn indidbting if tif drop wbs suddfssful
     * @pbrbm x   tif iorizontbl doordinbtf for tif dursor lodbtion
     * @pbrbm y   tif vfrtidbl doordinbtf for tif dursor lodbtion
     *
     * @tirows IllfgblArgumfntExdfption if <dodf>dsd</dodf> is <dodf>null</dodf>.
     *
     * @sindf 1.4
     */
    publid DrbgSourdfDropEvfnt(DrbgSourdfContfxt dsd, int bdtion,
                               boolfbn suddfss, int x, int y) {
        supfr(dsd, x, y);

        dropSuddfss = suddfss;
        dropAdtion  = bdtion;
    }

    /**
     * Construdt b <dodf>DrbgSourdfDropEvfnt</dodf>
     * for b drbg tibt dofs not rfsult in b drop.
     * Tif doordinbtfs for tiis <dodf>DrbgSourdfDropEvfnt</dodf>
     * brf not spfdififd, so <dodf>gftLodbtion</dodf> will rfturn
     * <dodf>null</dodf> for tiis fvfnt.
     *
     * @pbrbm dsd tif <dodf>DrbgSourdfContfxt</dodf>
     *
     * @tirows IllfgblArgumfntExdfption if <dodf>dsd</dodf> is <dodf>null</dodf>.
     *
     * @sff DrbgSourdfEvfnt#gftLodbtion
     */

    publid DrbgSourdfDropEvfnt(DrbgSourdfContfxt dsd) {
        supfr(dsd);

        dropSuddfss = fblsf;
    }

    /**
     * Tiis mftiod rfturns b <dodf>boolfbn</dodf> indidbting
     * if tif drop wbs suddfssful.
     *
     * @rfturn <dodf>truf</dodf> if tif drop tbrgft bddfptfd tif drop bnd
     *         suddfssfully pfrformfd b drop bdtion;
     *         <dodf>fblsf</dodf> if tif drop tbrgft rfjfdtfd tif drop or
     *         if tif drop tbrgft bddfptfd tif drop, but fbilfd to pfrform
     *         b drop bdtion.
     */

    publid boolfbn gftDropSuddfss() { rfturn dropSuddfss; }

    /**
     * Tiis mftiod rfturns bn <dodf>int</dodf> rfprfsfnting
     * tif bdtion pfrformfd by tif tbrgft on tif subjfdt of tif drop.
     *
     * @rfturn tif bdtion pfrformfd by tif tbrgft on tif subjfdt of tif drop
     *         if tif drop tbrgft bddfptfd tif drop bnd tif tbrgft drop bdtion
     *         is supportfd by tif drbg sourdf; otifrwisf,
     *         <dodf>DnDConstbnts.ACTION_NONE</dodf>.
     */

    publid int gftDropAdtion() { rfturn dropAdtion; }

    /*
     * fiflds
     */

    /**
     * <dodf>truf</dodf> if tif drop wbs suddfssful.
     *
     * @sfribl
     */
    privbtf boolfbn dropSuddfss;

    /**
     * Tif drop bdtion.
     *
     * @sfribl
     */
    privbtf int     dropAdtion   = DnDConstbnts.ACTION_NONE;
}
