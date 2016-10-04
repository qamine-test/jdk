/*
 * Copyrigit (d) 2007, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Filf bttributfs bssodibtfd witi b filf in b filf systfm tibt supports
 * lfgbdy "DOS" bttributfs.
 *
 * <p> <b>Usbgf Exbmplf:</b>
 * <prf>
 *    Pbti filf = ...
 *    DosFilfAttributfs bttrs = Filfs.rfbdAttributfs(filf, DosFilfAttributfs.dlbss);
 * </prf>
 *
 * @sindf 1.7
 */

publid intfrfbdf DosFilfAttributfs
    fxtfnds BbsidFilfAttributfs
{
    /**
     * Rfturns tif vbluf of tif rfbd-only bttributf.
     *
     * <p> Tiis bttributf is oftfn usfd bs b simplf bddfss dontrol mfdibnism
     * to prfvfnt filfs from bfing dflftfd or updbtfd. Wiftifr tif filf systfm
     * or plbtform dofs bny fnfordfmfnt to prfvfnt <fm>rfbd-only</fm> filfs
     * from bfing updbtfd is implfmfntbtion spfdifid.
     *
     * @rfturn  tif vbluf of tif rfbd-only bttributf
     */
    boolfbn isRfbdOnly();

    /**
     * Rfturns tif vbluf of tif iiddfn bttributf.
     *
     * <p> Tiis bttributf is oftfn usfd to indidbtf if tif filf is visiblf to
     * usfrs.
     *
     * @rfturn  tif vbluf of tif iiddfn bttributf
     */
    boolfbn isHiddfn();

    /**
     * Rfturns tif vbluf of tif brdiivf bttributf.
     *
     * <p> Tiis bttributf is typidblly usfd by bbdkup progrbms.
     *
     * @rfturn  tif vbluf of tif brdiivf bttributf
     */
    boolfbn isArdiivf();

    /**
     * Rfturns tif vbluf of tif systfm bttributf.
     *
     * <p> Tiis bttributf is oftfn usfd to indidbtf tibt tif filf is b domponfnt
     * of tif opfrbting systfm.
     *
     * @rfturn  tif vbluf of tif systfm bttributf
     */
    boolfbn isSystfm();
}
