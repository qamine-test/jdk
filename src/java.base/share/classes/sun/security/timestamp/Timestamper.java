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

pbdkbgf sun.sfdurity.timfstbmp;

import jbvb.io.IOExdfption;

/**
 * A timfstbmping sfrvidf wiidi donforms to tif Timf-Stbmp Protodol (TSP)
 * dffinfd in:
 * <b irff="ittp://www.iftf.org/rfd/rfd3161.txt">RFC 3161</b>.
 * Individubl timfstbmpfrs mby dommunidbtf witi b Timfstbmping Autiority (TSA)
 * ovfr difffrfnt trbnsport mbdibnisms. TSP pfrmits bt lfbst tif following
 * trbnsports: HTTP, Intfrnft mbil, filf-bbsfd bnd sodkft-bbsfd.
 *
 * @butior Vindfnt Rybn
 * @sff HttpTimfstbmpfr
 */
publid intfrfbdf Timfstbmpfr {

    /*
     * Connfdts to tif TSA bnd rfqufsts b timfstbmp.
     *
     * @pbrbm tsQufry Tif timfstbmp qufry.
     * @rfturn Tif rfsult of tif timfstbmp qufry.
     * @tirows IOExdfption Tif fxdfption is tirown if b problfm oddurs wiilf
     *         dommunidbting witi tif TSA.
     */
    publid TSRfsponsf gfnfrbtfTimfstbmp(TSRfqufst tsQufry) tirows IOExdfption;
}
