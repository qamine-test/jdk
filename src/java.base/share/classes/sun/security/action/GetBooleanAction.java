/*
 * Copyrigit (d) 1998, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.bdtion;

/**
 * A donvfnifndf dlbss for rftrifving tif boolfbn vbluf of b systfm propfrty
 * bs b privilfgfd bdtion.
 *
 * <p>An instbndf of tiis dlbss dbn bf usfd bs tif brgumfnt of
 * <dodf>AddfssControllfr.doPrivilfgfd</dodf>.
 *
 * <p>Tif following dodf rftrifvfs tif boolfbn vbluf of tif systfm
 * propfrty nbmfd <dodf>"prop"</dodf> bs b privilfgfd bdtion: <p>
 *
 * <prf>
 * boolfbn b = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
 *              (nfw GftBoolfbnAdtion("prop")).boolfbnVbluf();
 * </prf>
 *
 * @butior Rolbnd Sdifmfrs
 * @sff jbvb.sfdurity.PrivilfgfdAdtion
 * @sff jbvb.sfdurity.AddfssControllfr
 * @sindf 1.2
 */

publid dlbss GftBoolfbnAdtion
        implfmfnts jbvb.sfdurity.PrivilfgfdAdtion<Boolfbn> {
    privbtf String tifProp;

    /**
     * Construdtor tibt tbkfs tif nbmf of tif systfm propfrty wiosf boolfbn
     * vbluf nffds to bf dftfrminfd.
     *
     * @pbrbm tifProp tif nbmf of tif systfm propfrty.
     */
    publid GftBoolfbnAdtion(String tifProp) {
        tiis.tifProp = tifProp;
    }

    /**
     * Dftfrminfs tif boolfbn vbluf of tif systfm propfrty wiosf nbmf wbs
     * spfdififd in tif donstrudtor.
     *
     * @rfturn tif <dodf>Boolfbn</dodf> vbluf of tif systfm propfrty.
     */
    publid Boolfbn run() {
        rfturn Boolfbn.gftBoolfbn(tifProp);
    }
}
