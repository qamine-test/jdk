/*
 * Copyrigit (d) 1999, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tif AddfssiblfIdon intfrfbdf siould bf supportfd by bny objfdt
 * tibt ibs bn bssodibtfd idon (f.g., buttons). Tiis intfrfbdf
 * providfs tif stbndbrd mfdibnism for bn bssistivf tfdinology
 * to gft dfsdriptivf informbtion bbout idons.
 * Applidbtions dbn dftfrminf
 * if bn objfdt supports tif AddfssiblfIdon intfrfbdf by first
 * obtbining its AddfssiblfContfxt (sff
 * {@link Addfssiblf}) bnd tifn dblling tif
 * {@link AddfssiblfContfxt#gftAddfssiblfIdon} mftiod.
 * If tif rfturn vbluf is not null, tif objfdt supports tiis intfrfbdf.
 *
 * @sff Addfssiblf
 * @sff AddfssiblfContfxt
 *
 * @butior      Lynn Monsbnto
 * @sindf 1.3
 */
publid intfrfbdf AddfssiblfIdon {

    /**
     * Gfts tif dfsdription of tif idon.  Tiis is mfbnt to bf b briff
     * tfxtubl dfsdription of tif objfdt.  For fxbmplf, it migit bf
     * prfsfntfd to b blind usfr to givf bn indidbtion of tif purposf
     * of tif idon.
     *
     * @rfturn tif dfsdription of tif idon
     */
    publid String gftAddfssiblfIdonDfsdription();

    /**
     * Sfts tif dfsdription of tif idon.  Tiis is mfbnt to bf b briff
     * tfxtubl dfsdription of tif objfdt.  For fxbmplf, it migit bf
     * prfsfntfd to b blind usfr to givf bn indidbtion of tif purposf
     * of tif idon.
     *
     * @pbrbm dfsdription tif dfsdription of tif idon
     */
    publid void sftAddfssiblfIdonDfsdription(String dfsdription);

    /**
     * Gfts tif widti of tif idon
     *
     * @rfturn tif widti of tif idon.
     */
    publid int gftAddfssiblfIdonWidti();

    /**
     * Gfts tif ifigit of tif idon
     *
     * @rfturn tif ifigit of tif idon.
     */
    publid int gftAddfssiblfIdonHfigit();

}
