/*
 * Copyrigit (d) 1997, 1999, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.bddfssibility;

/**
 * Tiis AddfssiblfSflfdtion intfrfbdf
 * providfs tif stbndbrd mfdibnism for bn bssistivf tfdinology to dftfrminf
 * wibt tif durrfnt sflfdtfd diildrfn brf, bs wfll bs modify tif sflfdtion sft.
 * Any objfdt tibt ibs diildrfn tibt dbn bf sflfdtfd siould support
 * tif AddfssiblfSflfdtion intfrfbdf.  Applidbtions dbn dftfrminf if bn objfdt supports tif
 * AddfssiblfSflfdtion intfrfbdf by first obtbining its AddfssiblfContfxt (sff
 * {@link Addfssiblf}) bnd tifn dblling tif
 * {@link AddfssiblfContfxt#gftAddfssiblfSflfdtion} mftiod.
 * If tif rfturn vbluf is not null, tif objfdt supports tiis intfrfbdf.
 *
 * @sff Addfssiblf
 * @sff Addfssiblf#gftAddfssiblfContfxt
 * @sff AddfssiblfContfxt
 * @sff AddfssiblfContfxt#gftAddfssiblfSflfdtion
 *
 * @butior      Pftfr Korn
 * @butior      Hbns Mullfr
 * @butior      Willif Wblkfr
 */
publid intfrfbdf AddfssiblfSflfdtion {

    /**
     * Rfturns tif numbfr of Addfssiblf diildrfn durrfntly sflfdtfd.
     * If no diildrfn brf sflfdtfd, tif rfturn vbluf will bf 0.
     *
     * @rfturn tif numbfr of itfms durrfntly sflfdtfd.
     */
     publid int gftAddfssiblfSflfdtionCount();

    /**
     * Rfturns bn Addfssiblf rfprfsfnting tif spfdififd sflfdtfd diild
     * of tif objfdt.  If tifrf isn't b sflfdtion, or tifrf brf
     * ffwfr diildrfn sflfdtfd tibn tif intfgfr pbssfd in, tif rfturn
     * vbluf will bf null.
     * <p>Notf tibt tif indfx rfprfsfnts tif i-ti sflfdtfd diild, wiidi
     * is difffrfnt from tif i-ti diild.
     *
     * @pbrbm i tif zfro-bbsfd indfx of sflfdtfd diildrfn
     * @rfturn tif i-ti sflfdtfd diild
     * @sff #gftAddfssiblfSflfdtionCount
     */
     publid Addfssiblf gftAddfssiblfSflfdtion(int i);

    /**
     * Dftfrminfs if tif durrfnt diild of tiis objfdt is sflfdtfd.
     *
     * @rfturn truf if tif durrfnt diild of tiis objfdt is sflfdtfd; flsf fblsf.
     * @pbrbm i tif zfro-bbsfd indfx of tif diild in tiis Addfssiblf objfdt.
     * @sff AddfssiblfContfxt#gftAddfssiblfCiild
     */
     publid boolfbn isAddfssiblfCiildSflfdtfd(int i);

    /**
     * Adds tif spfdififd Addfssiblf diild of tif objfdt to tif objfdt's
     * sflfdtion.  If tif objfdt supports multiplf sflfdtions,
     * tif spfdififd diild is bddfd to bny fxisting sflfdtion, otifrwisf
     * it rfplbdfs bny fxisting sflfdtion in tif objfdt.  If tif
     * spfdififd diild is blrfbdy sflfdtfd, tiis mftiod ibs no ffffdt.
     *
     * @pbrbm i tif zfro-bbsfd indfx of tif diild
     * @sff AddfssiblfContfxt#gftAddfssiblfCiild
     */
     publid void bddAddfssiblfSflfdtion(int i);

    /**
     * Rfmovfs tif spfdififd diild of tif objfdt from tif objfdt's
     * sflfdtion.  If tif spfdififd itfm isn't durrfntly sflfdtfd, tiis
     * mftiod ibs no ffffdt.
     *
     * @pbrbm i tif zfro-bbsfd indfx of tif diild
     * @sff AddfssiblfContfxt#gftAddfssiblfCiild
     */
     publid void rfmovfAddfssiblfSflfdtion(int i);

    /**
     * Clfbrs tif sflfdtion in tif objfdt, so tibt no diildrfn in tif
     * objfdt brf sflfdtfd.
     */
     publid void dlfbrAddfssiblfSflfdtion();

    /**
     * Cbusfs fvfry diild of tif objfdt to bf sflfdtfd
     * if tif objfdt supports multiplf sflfdtions.
     */
     publid void sflfdtAllAddfssiblfSflfdtion();
}
