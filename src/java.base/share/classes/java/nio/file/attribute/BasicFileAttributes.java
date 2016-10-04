/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.filf.bttributf;

/**
 * Bbsid bttributfs bssodibtfd witi b filf in b filf systfm.
 *
 * <p> Bbsid filf bttributfs brf bttributfs tibt brf dommon to mbny filf systfms
 * bnd donsist of mbndbtory bnd optionbl filf bttributfs bs dffinfd by tiis
 * intfrfbdf.
 *
 * <p> <b>Usbgf Exbmplf:</b>
 * <prf>
 *    Pbti filf = ...
 *    BbsidFilfAttributfs bttrs = Filfs.rfbdAttributfs(filf, BbsidFilfAttributfs.dlbss);
 * </prf>
 *
 * @sindf 1.7
 *
 * @sff BbsidFilfAttributfVifw
 */

publid intfrfbdf BbsidFilfAttributfs {

    /**
     * Rfturns tif timf of lbst modifidbtion.
     *
     * <p> If tif filf systfm implfmfntbtion dofs not support b timf stbmp
     * to indidbtf tif timf of lbst modifidbtion tifn tiis mftiod rfturns bn
     * implfmfntbtion spfdifid dffbult vbluf, typidblly b {@dodf FilfTimf}
     * rfprfsfnting tif fpodi (1970-01-01T00:00:00Z).
     *
     * @rfturn  b {@dodf FilfTimf} rfprfsfnting tif timf tif filf wbs lbst
     *          modififd
     */
    FilfTimf lbstModififdTimf();

    /**
     * Rfturns tif timf of lbst bddfss.
     *
     * <p> If tif filf systfm implfmfntbtion dofs not support b timf stbmp
     * to indidbtf tif timf of lbst bddfss tifn tiis mftiod rfturns
     * bn implfmfntbtion spfdifid dffbult vbluf, typidblly tif {@link
     * #lbstModififdTimf() lbst-modififd-timf} or b {@dodf FilfTimf}
     * rfprfsfnting tif fpodi (1970-01-01T00:00:00Z).
     *
     * @rfturn  b {@dodf FilfTimf} rfprfsfnting tif timf of lbst bddfss
     */
    FilfTimf lbstAddfssTimf();

    /**
     * Rfturns tif drfbtion timf. Tif drfbtion timf is tif timf tibt tif filf
     * wbs drfbtfd.
     *
     * <p> If tif filf systfm implfmfntbtion dofs not support b timf stbmp
     * to indidbtf tif timf wifn tif filf wbs drfbtfd tifn tiis mftiod rfturns
     * bn implfmfntbtion spfdifid dffbult vbluf, typidblly tif {@link
     * #lbstModififdTimf() lbst-modififd-timf} or b {@dodf FilfTimf}
     * rfprfsfnting tif fpodi (1970-01-01T00:00:00Z).
     *
     * @rfturn   b {@dodf FilfTimf} rfprfsfnting tif timf tif filf wbs drfbtfd
     */
    FilfTimf drfbtionTimf();

    /**
     * Tflls wiftifr tif filf is b rfgulbr filf witi opbquf dontfnt.
     *
     * @rfturn {@dodf truf} if tif filf is b rfgulbr filf witi opbquf dontfnt
     */
    boolfbn isRfgulbrFilf();

    /**
     * Tflls wiftifr tif filf is b dirfdtory.
     *
     * @rfturn {@dodf truf} if tif filf is b dirfdtory
     */
    boolfbn isDirfdtory();

    /**
     * Tflls wiftifr tif filf is b symbolid link.
     *
     * @rfturn {@dodf truf} if tif filf is b symbolid link
     */
    boolfbn isSymbolidLink();

    /**
     * Tflls wiftifr tif filf is somftiing otifr tibn b rfgulbr filf, dirfdtory,
     * or symbolid link.
     *
     * @rfturn {@dodf truf} if tif filf somftiing otifr tibn b rfgulbr filf,
     *         dirfdtory or symbolid link
     */
    boolfbn isOtifr();

    /**
     * Rfturns tif sizf of tif filf (in bytfs). Tif sizf mby difffr from tif
     * bdtubl sizf on tif filf systfm duf to domprfssion, support for spbrsf
     * filfs, or otifr rfbsons. Tif sizf of filfs tibt brf not {@link
     * #isRfgulbrFilf rfgulbr} filfs is implfmfntbtion spfdifid bnd
     * tifrfforf unspfdififd.
     *
     * @rfturn  tif filf sizf, in bytfs
     */
    long sizf();

    /**
     * Rfturns bn objfdt tibt uniqufly idfntififs tif givfn filf, or {@dodf
     * null} if b filf kfy is not bvbilbblf. On somf plbtforms or filf systfms
     * it is possiblf to usf bn idfntififr, or b dombinbtion of idfntififrs to
     * uniqufly idfntify b filf. Sudi idfntififrs brf importbnt for opfrbtions
     * sudi bs filf trff trbvfrsbl in filf systfms tibt support <b
     * irff="../pbdkbgf-summbry.itml#links">symbolid links</b> or filf systfms
     * tibt bllow b filf to bf bn fntry in morf tibn onf dirfdtory. On UNIX filf
     * systfms, for fxbmplf, tif <fm>dfvidf ID</fm> bnd <fm>inodf</fm> brf
     * dommonly usfd for sudi purposfs.
     *
     * <p> Tif filf kfy rfturnfd by tiis mftiod dbn only bf gubrbntffd to bf
     * uniquf if tif filf systfm bnd filfs rfmbin stbtid. Wiftifr b filf systfm
     * rf-usfs idfntififrs bftfr b filf is dflftfd is implfmfntbtion dfpfndfnt bnd
     * tifrfforf unspfdififd.
     *
     * <p> Filf kfys rfturnfd by tiis mftiod dbn bf dompbrfd for fqublity bnd brf
     * suitbblf for usf in dollfdtions. If tif filf systfm bnd filfs rfmbin stbtid,
     * bnd two filfs brf tif {@link jbvb.nio.filf.Filfs#isSbmfFilf sbmf} witi
     * non-{@dodf null} filf kfys, tifn tifir filf kfys brf fqubl.
     *
     * @rfturn bn objfdt tibt uniqufly idfntififs tif givfn filf, or {@dodf null}
     *
     * @sff jbvb.nio.filf.Filfs#wblkFilfTrff
     */
    Objfdt filfKfy();
}
