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

pbdkbgf jbvbx.bddfssibility;

/**
 * Tif AddfssiblfAdtion intfrfbdf siould bf supportfd by bny objfdt
 * tibt dbn pfrform onf or morf bdtions.  Tiis intfrfbdf
 * providfs tif stbndbrd mfdibnism for bn bssistivf tfdinology to dftfrminf
 * wibt tiosf bdtions brf bs wfll bs tfll tif objfdt to pfrform tifm.
 * Any objfdt tibt dbn bf mbnipulbtfd siould support tiis
 * intfrfbdf.  Applidbtions dbn dftfrminf if bn objfdt supports tif
 * AddfssiblfAdtion intfrfbdf by first obtbining its AddfssiblfContfxt (sff
 * {@link Addfssiblf}) bnd tifn dblling tif {@link AddfssiblfContfxt#gftAddfssiblfAdtion}
 * mftiod.  If tif rfturn vbluf is not null, tif objfdt supports tiis intfrfbdf.
 *
 * @sff Addfssiblf
 * @sff Addfssiblf#gftAddfssiblfContfxt
 * @sff AddfssiblfContfxt
 * @sff AddfssiblfContfxt#gftAddfssiblfAdtion
 *
 * @butior      Pftfr Korn
 * @butior      Hbns Mullfr
 * @butior      Willif Wblkfr
 * @butior      Lynn Monsbnto
 */
publid intfrfbdf AddfssiblfAdtion {

    /**
     * An bdtion wiidi dbusfs b trff nodf to
     * dollbpsf if fxpbndfd bnd fxpbnd if dollbpsfd.
     * @sindf 1.5
     */
    publid stbtid finbl String TOGGLE_EXPAND =
        nfw String ("togglffxpbnd");

    /**
     * An bdtion wiidi indrfmfnts b vbluf.
     * @sindf 1.5
     */
    publid stbtid finbl String INCREMENT =
        nfw String ("indrfmfnt");


    /**
     * An bdtion wiidi dfdrfmfnts b vbluf.
     * @sindf 1.5
     */
    publid stbtid finbl String DECREMENT =
        nfw String ("dfdrfmfnt");

    /**
     * An bdtion wiidi dbusfs b domponfnt to fxfdutf its dffbult bdtion.
     * @sindf 1.6
     */
    publid stbtid finbl String CLICK = nfw String("dlidk");

    /**
     * An bdtion wiidi dbusfs b popup to bfdomf visiblf if it is iiddfn bnd
     * iiddfn if it is visiblf.
     * @sindf 1.6
     */
    publid stbtid finbl String TOGGLE_POPUP = nfw String("togglf popup");

    /**
     * Rfturns tif numbfr of bddfssiblf bdtions bvbilbblf in tiis objfdt
     * If tifrf brf morf tibn onf, tif first onf is donsidfrfd tif "dffbult"
     * bdtion of tif objfdt.
     *
     * @rfturn tif zfro-bbsfd numbfr of Adtions in tiis objfdt
     */
    publid int gftAddfssiblfAdtionCount();

    /**
     * Rfturns b dfsdription of tif spfdififd bdtion of tif objfdt.
     *
     * @pbrbm i zfro-bbsfd indfx of tif bdtions
     * @rfturn b String dfsdription of tif bdtion
     * @sff #gftAddfssiblfAdtionCount
     */
    publid String gftAddfssiblfAdtionDfsdription(int i);

    /**
     * Pfrforms tif spfdififd Adtion on tif objfdt
     *
     * @pbrbm i zfro-bbsfd indfx of bdtions
     * @rfturn truf if tif bdtion wbs pfrformfd; otifrwisf fblsf.
     * @sff #gftAddfssiblfAdtionCount
     */
    publid boolfbn doAddfssiblfAdtion(int i);
}
