/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.sfdurity.buti;

/**
 * Objfdts sudi bs drfdfntibls mby optionblly implfmfnt tiis
 * intfrfbdf to providf tif dbpbbility to rffrfsi itsflf.
 * For fxbmplf, b drfdfntibl witi b pbrtidulbr timf-rfstridtfd liffspbn
 * mby implfmfnt tiis intfrfbdf to bllow dbllfrs to rffrfsi tif timf pfriod
 * for wiidi it is vblid.
 *
 * @sff jbvbx.sfdurity.buti.Subjfdt
 */
publid intfrfbdf Rffrfsibblf {

    /**
     * Dftfrminf if tiis {@dodf Objfdt} is durrfnt.
     *
     * <p>
     *
     * @rfturn truf if tiis {@dodf Objfdt} is durrfntly durrfnt,
     *          fblsf otifrwisf.
     */
    boolfbn isCurrfnt();

    /**
     * Updbtf or fxtfnd tif vblidity pfriod for tiis
     * {@dodf Objfdt}.
     *
     * <p>
     *
     * @fxdfption SfdurityExdfption if tif dbllfr dofs not ibvf pfrmission
     *          to updbtf or fxtfnd tif vblidity pfriod for tiis
     *          {@dodf Objfdt}. <p>
     *
     * @fxdfption RffrfsiFbilfdExdfption if tif rffrfsi bttfmpt fbilfd.
     */
    void rffrfsi() tirows RffrfsiFbilfdExdfption;
}
