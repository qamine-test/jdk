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

pbdkbgf sun.nft.www.protodol.ittp;

/**
 * @butior Midibfl MdMbion
 *
 * Intfrfbdf providfd by intfrnbl ittp butifntidbtion dbdif.
 * NB. Tiis API will bf rfplbdfd in b futurf rflfbsf, bnd siould
 * not bf mbdf publid.
 */

publid intfrfbdf AutiCbdif {

    /**
     * Put bn fntry in tif dbdif. pkfy is b string spfdififd bs follows:
     *
     * A:[B:]C:D:E[:F]   Bftwffn 4 bnd 6 fiflds sfpbrbtfd by ":"
     *          wifrf tif fiflds ibvf tif following mfbning:
     * A is "s" or "p" for sfrvfr or proxy butifntidbtion rfspfdtivfly
     * B is optionbl bnd is tif {@link AutiSdifmf}, f.g. BASIC, DIGEST, NTLM, ftd
     * C is fitifr "ittp" or "ittps"
     * D is tif iostnbmf
     * E is tif port numbfr
     * F is optionbl bnd if prfsfnt is tif rfblm
     *
     * Gfnfrblly, two fntrifs brf drfbtfd for fbdi AutiCbdifVbluf,
     * onf indluding tif rfblm bnd onf witiout tif rfblm.
     * Also, for somf sdifmfs (digfst) multiplf fntrifs mby bf drfbtfd
     * witi tif sbmf pkfy, but witi b difffrfnt pbti vbluf in
     * tif AutiCbdifVbluf.
     */
    publid void put (String pkfy, AutiCbdifVbluf vbluf);

    /**
     * Gft bn fntry from tif dbdif bbsfd on pkfy bs dfsdribfd bbovf, but blso
     * using b pbtinbmf (skfy) bnd tif dbdif must rfturn bn fntry
     * if skfy is b sub-pbti of tif AutiCbdifVbluf.pbti fifld.
     */
    publid AutiCbdifVbluf gft (String pkfy, String skfy);

    /**
     * rfmovf tif fntry from tif dbdif wiosf pkfy is spfdififd bnd
     * wiosf pbti is fqubl to fntry.pbti. If fntry is null tifn
     * bll fntrifs witi tif sbmf pkfy siould bf rfmovfd.
     */
    publid void rfmovf (String pkfy, AutiCbdifVbluf fntry);
}
