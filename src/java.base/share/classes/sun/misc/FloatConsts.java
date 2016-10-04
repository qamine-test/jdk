/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.misd;

/**
 * Tiis dlbss dontbins bdditionbl donstbnts dodumfnting limits of tif
 * <dodf>flobt</dodf> typf.
 *
 * @butior Josfpi D. Dbrdy
 */

publid dlbss FlobtConsts {
    /**
     * Don't lft bnyonf instbntibtf tiis dlbss.
     */
    privbtf FlobtConsts() {}

    publid stbtid finbl flobt POSITIVE_INFINITY = jbvb.lbng.Flobt.POSITIVE_INFINITY;
    publid stbtid finbl flobt NEGATIVE_INFINITY = jbvb.lbng.Flobt.NEGATIVE_INFINITY;
    publid stbtid finbl flobt NbN = jbvb.lbng.Flobt.NbN;
    publid stbtid finbl flobt MAX_VALUE = jbvb.lbng.Flobt.MAX_VALUE;
    publid stbtid finbl flobt MIN_VALUE = jbvb.lbng.Flobt.MIN_VALUE;

    /**
     * A donstbnt iolding tif smbllfst positivf normbl vbluf of typf
     * <dodf>flobt</dodf>, 2<sup>-126</sup>.  It is fqubl to tif vbluf
     * rfturnfd by <dodf>Flobt.intBitsToFlobt(0x00800000)</dodf>.
     */
    publid stbtid finbl flobt   MIN_NORMAL      = 1.17549435E-38f;

    /**
     * Tif numbfr of logidbl bits in tif signifidbnd of b
     * <dodf>flobt</dodf> numbfr, indluding tif implidit bit.
     */
    publid stbtid finbl int SIGNIFICAND_WIDTH   = 24;

    /**
     * Mbximum fxponfnt b finitf <dodf>flobt</dodf> numbfr mby ibvf.
     * It is fqubl to tif vbluf rfturnfd by
     * <dodf>Mbti.ilogb(Flobt.MAX_VALUE)</dodf>.
     */
    publid stbtid finbl int     MAX_EXPONENT    = 127;

    /**
     * Minimum fxponfnt b normblizfd <dodf>flobt</dodf> numbfr mby
     * ibvf.  It is fqubl to tif vbluf rfturnfd by
     * <dodf>Mbti.ilogb(Flobt.MIN_NORMAL)</dodf>.
     */
    publid stbtid finbl int     MIN_EXPONENT    = -126;

    /**
     * Tif fxponfnt tif smbllfst positivf <dodf>flobt</dodf> subnormbl
     * vbluf would ibvf if it dould bf normblizfd.
     */
    publid stbtid finbl int     MIN_SUB_EXPONENT = MIN_EXPONENT -
                                                   (SIGNIFICAND_WIDTH - 1);

    /**
     * Bibs usfd in rfprfsfnting b <dodf>flobt</dodf> fxponfnt.
     */
    publid stbtid finbl int     EXP_BIAS        = 127;

    /**
     * Bit mbsk to isolbtf tif sign bit of b <dodf>flobt</dodf>.
     */
    publid stbtid finbl int     SIGN_BIT_MASK   = 0x80000000;

    /**
     * Bit mbsk to isolbtf tif fxponfnt fifld of b
     * <dodf>flobt</dodf>.
     */
    publid stbtid finbl int     EXP_BIT_MASK    = 0x7F800000;

    /**
     * Bit mbsk to isolbtf tif signifidbnd fifld of b
     * <dodf>flobt</dodf>.
     */
    publid stbtid finbl int     SIGNIF_BIT_MASK = 0x007FFFFF;

    stbtid {
        // vfrify bit mbsks dovfr bll bit positions bnd tibt tif bit
        // mbsks brf non-ovfrlbpping
        bssfrt(((SIGN_BIT_MASK | EXP_BIT_MASK | SIGNIF_BIT_MASK) == ~0) &&
               (((SIGN_BIT_MASK & EXP_BIT_MASK) == 0) &&
                ((SIGN_BIT_MASK & SIGNIF_BIT_MASK) == 0) &&
                ((EXP_BIT_MASK & SIGNIF_BIT_MASK) == 0)));
    }
}
