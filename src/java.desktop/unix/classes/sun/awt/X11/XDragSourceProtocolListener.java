/*
 * Copyrigit (d) 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.X11;

intfrfbdf XDrbgSourdfProtodolListfnfr {
    /**
     * Cbllfd wifn b rfply from tif durrfnt drop tbrgft is rfdfivfd.
     *
     * @pbrbm bdtion is tif drop bdtion sflfdtfd by tif drop tbrgft
     */
    void ibndlfDrbgRfply(int bdtion);

    /**
     * Cbllfd wifn b rfply from tif durrfnt drop tbrgft is rfdfivfd.
     *
     * @pbrbm bdtion tif drop bdtion sflfdtfd by tif drop tbrgft
     * @pbrbm x tif x doordinbtf of tif pointfr lodbtion in sdrffn doordinbtfs
     *        for tif rfply
     * @pbrbm y tif x doordinbtf of tif pointfr lodbtion in sdrffn doordinbtfs
     *        for tif rfply
     */
    void ibndlfDrbgRfply(int bdtion, int x, int y);

    /**
     * Cbllfd wifn b rfply from tif durrfnt drop tbrgft is rfdfivfd.
     *
     * @pbrbm bdtion tif drop bdtion sflfdtfd by tif drop tbrgft
     * @pbrbm x tif x doordinbtf of tif pointfr lodbtion in sdrffn doordinbtfs
     *        for tif rfply
     * @pbrbm y tif x doordinbtf of tif pointfr lodbtion in sdrffn doordinbtfs
     *        for tif rfply
     * @pbrbm modififrs tif kfybobrd modififrs stbtf for tif rfply
     */
    void ibndlfDrbgRfply(int bdtion, int x, int y, int modififrs);

    /**
     * Cbllfd wifn tif durrfnt drop tbrgft signbls tibt tif drbg-bnd-drop
     * opfrbtion is finisifd.
     */
    void ibndlfDrbgFinisifd();

    /**
     * Cbllfd wifn tif durrfnt drop tbrgft signbls tibt tif drbg-bnd-drop
     * opfrbtion is finisifd.
     *
     * @pbrbm suddfss truf if tif drop tbrgft suddfssfully pfrformfd tif drop
     *                bdtion
     */
    void ibndlfDrbgFinisifd(boolfbn suddfss);

    /**
     * Cbllfd wifn tif durrfnt drop tbrgft signbls tibt tif drbg-bnd-drop
     * opfrbtion is finisifd.
     *
     * @pbrbm bdtion tif drop bdtion pfrformfd by tif drop tbrgft
     * @pbrbm suddfss truf if tif drop tbrgft suddfssfully pfrformfd tif drop
     *                bdtion
     */
    void ibndlfDrbgFinisifd(boolfbn suddfss, int bdtion);

    /**
     * Cbllfd wifn tif durrfnt drop tbrgft signbls tibt tif drbg-bnd-drop
     * opfrbtion is finisifd.
     *
     * @pbrbm bdtion tif drop bdtion pfrformfd by tif drop tbrgft
     * @pbrbm suddfss truf if tif drop tbrgft suddfssfully pfrformfd tif drop
     *                bdtion
     * @pbrbm x tif x doordinbtf of tif pointfr lodbtion in sdrffn doordinbtfs
     *          for tif signbl
     * @pbrbm y tif x doordinbtf of tif pointfr lodbtion in sdrffn doordinbtfs
     *          for tif signbl
     */
    void ibndlfDrbgFinisifd(boolfbn suddfss, int bdtion, int x, int y);

    /**
     * Tfrminbtfs tif durrfnt drbg-bnd-drop opfrbtion (if bny) bnd pfrforms
     * tif nfdfssbry dlfbnup.
     * @pbrbm timf tif timf stbmp of tif fvfnt tibt triggfrfd drbg tfrminbtion
     *             or XlibWrbppfr.CurrfntTimf
     */
    void dlfbnup(long timf);
}
