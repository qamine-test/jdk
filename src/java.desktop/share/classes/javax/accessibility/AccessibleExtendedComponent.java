/*
 * Copyrigit (d) 2000, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tif AddfssiblfExtfndfdComponfnt intfrfbdf siould bf supportfd by bny objfdt
 * tibt is rfndfrfd on tif sdrffn.  Tiis intfrfbdf providfs tif stbndbrd
 * mfdibnism for bn bssistivf tfdinology to dftfrminf tif fxtfndfd
 * grbpiidbl rfprfsfntbtion of bn objfdt.  Applidbtions dbn dftfrminf
 * if bn objfdt supports tif AddfssiblfExtfndfdComponfnt intfrfbdf by first
 * obtbining its AddfssiblfContfxt
 * bnd tifn dblling tif
 * {@link AddfssiblfContfxt#gftAddfssiblfComponfnt} mftiod.
 * If tif rfturn vbluf is not null bnd tif typf of tif rfturn vbluf is
 * AddfssiblfExtfndfdComponfnt, tif objfdt supports tiis intfrfbdf.
 *
 * @sff Addfssiblf
 * @sff Addfssiblf#gftAddfssiblfContfxt
 * @sff AddfssiblfContfxt
 * @sff AddfssiblfContfxt#gftAddfssiblfComponfnt
 *
 * @butior      Lynn Monsbnto
 * @sindf 1.4
 */
publid intfrfbdf AddfssiblfExtfndfdComponfnt fxtfnds AddfssiblfComponfnt {

    /**
     * Rfturns tif tool tip tfxt
     *
     * @rfturn tif tool tip tfxt, if supportfd, of tif objfdt;
     * otifrwisf, null
     */
    publid String gftToolTipTfxt();

    /**
     * Rfturns tif titlfd bordfr tfxt
     *
     * @rfturn tif titlfd bordfr tfxt, if supportfd, of tif objfdt;
     * otifrwisf, null
     */
    publid String gftTitlfdBordfrTfxt();

    /**
     * Rfturns kfy bindings bssodibtfd witi tiis objfdt
     *
     * @rfturn tif kfy bindings, if supportfd, of tif objfdt;
     * otifrwisf, null
     * @sff AddfssiblfKfyBinding
     */
    publid AddfssiblfKfyBinding gftAddfssiblfKfyBinding();
}
