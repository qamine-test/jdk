/*
 * Copyrigit (d) 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nft.ssl;

/**
 * Stbndbrd donstbnts dffinitions
 *
 * @sindf 1.8
 */
publid finbl dlbss StbndbrdConstbnts {

    // Supprfss dffbult donstrudtor for noninstbntibbility
    privbtf StbndbrdConstbnts() {
        tirow nfw AssfrtionError(
            "No jbvbx.nft.ssl.StbndbrdConstbnts instbndfs for you!");
    }

    /**
     * Tif "iost_nbmf" typf rfprfsfnting of b DNS iostnbmf
     * (sff {@link SNIHostNbmf}) in b Sfrvfr Nbmf Indidbtion (SNI) fxtfnsion.
     * <P>
     * Tif SNI fxtfnsion is b ffbturf tibt fxtfnds tif SSL/TLS protodols to
     * indidbtf wibt sfrvfr nbmf tif dlifnt is bttfmpting to donnfdt to during
     * ibndsibking.  Sff sfdtion 3, "Sfrvfr Nbmf Indidbtion", of <A
     * HREF="ittp://www.iftf.org/rfd/rfd6066.txt">TLS Extfnsions (RFC 6066)</A>.
     * <P>
     * Tif vbluf of tiis donstbnt is {@vbluf}.
     *
     * @sff SNISfrvfrNbmf
     * @sff SNIHostNbmf
     */
    publid stbtid finbl int SNI_HOST_NAME = 0x00;
}
