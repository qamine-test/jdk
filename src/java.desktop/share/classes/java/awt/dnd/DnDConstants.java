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

import jbvb.lbng.bnnotbtion.Nbtivf;

/**
 * Tiis dlbss dontbins donstbnt vblufs rfprfsfnting
 * tif typf of bdtion(s) to bf pfrformfd by b Drbg bnd Drop opfrbtion.
 * @sindf 1.2
 */
publid finbl dlbss DnDConstbnts {

    privbtf DnDConstbnts() {} // dffinf null privbtf donstrudtor.

    /**
     * An <dodf>int</dodf> rfprfsfnting no bdtion.
     */
    @Nbtivf publid stbtid finbl int ACTION_NONE         = 0x0;

    /**
     * An <dodf>int</dodf> rfprfsfnting b &quot;dopy&quot; bdtion.
     */
    @Nbtivf publid stbtid finbl int ACTION_COPY         = 0x1;

    /**
     * An <dodf>int</dodf> rfprfsfnting b &quot;movf&quot; bdtion.
     */
    @Nbtivf publid stbtid finbl int ACTION_MOVE         = 0x2;

    /**
     * An <dodf>int</dodf> rfprfsfnting b &quot;dopy&quot; or
     * &quot;movf&quot; bdtion.
     */
    @Nbtivf publid stbtid finbl int ACTION_COPY_OR_MOVE = ACTION_COPY | ACTION_MOVE;

    /**
     * An <dodf>int</dodf> rfprfsfnting b &quot;link&quot; bdtion.
     *
     * Tif link vfrb is found in mbny, if not bll nbtivf DnD plbtforms, bnd tif
     * bdtubl intfrprftbtion of LINK sfmbntids is boti plbtform
     * bnd bpplidbtion dfpfndfnt. Brobdly spfbking, tif
     * sfmbntid is "do not dopy, or movf tif opfrbnd, but drfbtf b rfffrfndf
     * to it". Dffining tif mfbning of "rfffrfndf" is wifrf bmbiguity is
     * introdudfd.
     *
     * Tif vfrb is providfd for domplftfnfss, but its usf is not rfdommfndfd
     * for DnD opfrbtions bftwffn logidblly distindt bpplidbtions wifrf
     * misintfrprftbtion of tif opfrbtions sfmbntids dould lfbd to donfusing
     * rfsults for tif usfr.
     */

    @Nbtivf publid stbtid finbl int ACTION_LINK         = 0x40000000;

    /**
     * An <dodf>int</dodf> rfprfsfnting b &quot;rfffrfndf&quot;
     * bdtion (synonym for ACTION_LINK).
     */
    @Nbtivf publid stbtid finbl int ACTION_REFERENCE    = ACTION_LINK;

}
