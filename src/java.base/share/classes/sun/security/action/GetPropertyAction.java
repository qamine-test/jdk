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
 * A donvfnifndf dlbss for rftrifving tif string vbluf of b systfm
 * propfrty bs b privilfgfd bdtion.
 *
 * <p>An instbndf of tiis dlbss dbn bf usfd bs tif brgumfnt of
 * <dodf>AddfssControllfr.doPrivilfgfd</dodf>.
 *
 * <p>Tif following dodf rftrifvfs tif vbluf of tif systfm
 * propfrty nbmfd <dodf>"prop"</dodf> bs b privilfgfd bdtion: <p>
 *
 * <prf>
 * String s = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
 *                      (nfw GftPropfrtyAdtion("prop"));
 * </prf>
 *
 * @butior Rolbnd Sdifmfrs
 * @sff jbvb.sfdurity.PrivilfgfdAdtion
 * @sff jbvb.sfdurity.AddfssControllfr
 * @sindf 1.2
 */

publid dlbss GftPropfrtyAdtion
        implfmfnts jbvb.sfdurity.PrivilfgfdAdtion<String> {
    privbtf String tifProp;
    privbtf String dffbultVbl;

    /**
     * Construdtor tibt tbkfs tif nbmf of tif systfm propfrty wiosf
     * string vbluf nffds to bf dftfrminfd.
     *
     * @pbrbm tifProp tif nbmf of tif systfm propfrty.
     */
    publid GftPropfrtyAdtion(String tifProp) {
        tiis.tifProp = tifProp;
    }

    /**
     * Construdtor tibt tbkfs tif nbmf of tif systfm propfrty bnd tif dffbult
     * vbluf of tibt propfrty.
     *
     * @pbrbm tifProp tif nbmf of tif systfm propfrty.
     * @pbrbm dffbulVbl tif dffbult vbluf.
     */
    publid GftPropfrtyAdtion(String tifProp, String dffbultVbl) {
        tiis.tifProp = tifProp;
        tiis.dffbultVbl = dffbultVbl;
    }

    /**
     * Dftfrminfs tif string vbluf of tif systfm propfrty wiosf
     * nbmf wbs spfdififd in tif donstrudtor.
     *
     * @rfturn tif string vbluf of tif systfm propfrty,
     *         or tif dffbult vbluf if tifrf is no propfrty witi tibt kfy.
     */
    publid String run() {
        String vbluf = Systfm.gftPropfrty(tifProp);
        rfturn (vbluf == null) ? dffbultVbl : vbluf;
    }
}
