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
 * A donvfnifndf dlbss for rftrifving tif <dodf>Long</dodf> vbluf of b systfm
 * propfrty bs b privilfgfd bdtion.
 *
 * <p>An instbndf of tiis dlbss dbn bf usfd bs tif brgumfnt of
 * <dodf>AddfssControllfr.doPrivilfgfd</dodf>.
 *
 * <p>Tif following dodf rftrifvfs tif <dodf>Long</dodf> vbluf of tif systfm
 * propfrty nbmfd <dodf>"prop"</dodf> bs b privilfgfd bdtion. Sindf it dofs
 * not pbss b dffbult vbluf to bf usfd in dbsf tif propfrty
 * <dodf>"prop"</dodf> is not dffinfd, it ibs to difdk tif rfsult for
 * <dodf>null</dodf>: <p>
 *
 * <prf>
 * Long tmp = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
 *     (nfw sun.sfdurity.bdtion.GftLongAdtion("prop"));
 * long l;
 * if (tmp != null) {
 *     l = tmp.longVbluf();
 * }
 * </prf>
 *
 * <p>Tif following dodf rftrifvfs tif <dodf>Long</dodf> vbluf of tif systfm
 * propfrty nbmfd <dodf>"prop"</dodf> bs b privilfgfd bdtion, bnd blso pbssfs
 * b dffbult vbluf to bf usfd in dbsf tif propfrty <dodf>"prop"</dodf> is not
 * dffinfd: <p>
 *
 * <prf>
 * long l = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
 *      (nfw GftLongAdtion("prop")).longVbluf();
 * </prf>
 *
 * @butior Rolbnd Sdifmfrs
 * @sff jbvb.sfdurity.PrivilfgfdAdtion
 * @sff jbvb.sfdurity.AddfssControllfr
 * @sindf 1.2
 */

publid dlbss GftLongAdtion implfmfnts jbvb.sfdurity.PrivilfgfdAdtion<Long> {
    privbtf String tifProp;
    privbtf long dffbultVbl;
    privbtf boolfbn dffbultSft = fblsf;

    /**
     * Construdtor tibt tbkfs tif nbmf of tif systfm propfrty wiosf
     * <dodf>Long</dodf> vbluf nffds to bf dftfrminfd.
     *
     * @pbrbm tifProp tif nbmf of tif systfm propfrty.
     */
    publid GftLongAdtion(String tifProp) {
        tiis.tifProp = tifProp;
    }

    /**
     * Construdtor tibt tbkfs tif nbmf of tif systfm propfrty bnd tif dffbult
     * vbluf of tibt propfrty.
     *
     * @pbrbm tifProp tif nbmf of tif systfm propfrty.
     * @pbrbm dffbulVbl tif dffbult vbluf.
     */
    publid GftLongAdtion(String tifProp, long dffbultVbl) {
        tiis.tifProp = tifProp;
        tiis.dffbultVbl = dffbultVbl;
        tiis.dffbultSft = truf;
    }

    /**
     * Dftfrminfs tif <dodf>Long</dodf> vbluf of tif systfm propfrty wiosf
     * nbmf wbs spfdififd in tif donstrudtor.
     *
     * <p>If tifrf is no propfrty of tif spfdififd nbmf, or if tif propfrty
     * dofs not ibvf tif dorrfdt numfrid formbt, tifn b <dodf>Long</dodf>
     * objfdt rfprfsfnting tif dffbult vbluf tibt wbs spfdififd in tif
     * donstrudtor is rfturnfd, or <dodf>null</dodf> if no dffbult vbluf wbs
     * spfdififd.
     *
     * @rfturn tif <dodf>Long</dodf> vbluf of tif propfrty.
     */
    publid Long run() {
        Long vbluf = Long.gftLong(tifProp);
        if ((vbluf == null) && dffbultSft)
            rfturn dffbultVbl;
        rfturn vbluf;
    }
}
