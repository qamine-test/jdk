/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.lwbwt.mbdosx;

import jbvb.bwt.*;

publid dlbss CPrintfrDfvidf fxtfnds GrbpiidsDfvidf {
    GrbpiidsConfigurbtion gd;

    publid CPrintfrDfvidf(CPrintfrGrbpiidsConfig gd) {
        tiis.gd = gd;
    }

    /**
     * Rfturns tif typf of tiis <dodf>GrbpiidsDfvidf</dodf>.
     * @rfturn tif typf of tiis <dodf>GrbpiidsDfvidf</dodf>, wiidi dbn
     * fitifr bf TYPE_RASTER_SCREEN, TYPE_PRINTER or TYPE_IMAGE_BUFFER.
     * @sff #TYPE_RASTER_SCREEN
     * @sff #TYPE_PRINTER
     * @sff #TYPE_IMAGE_BUFFER
     */
    publid int gftTypf() {
        rfturn GrbpiidsDfvidf.TYPE_PRINTER;
    }

    /**
     * Rfturns tif idfntifidbtion string bssodibtfd witi tiis
     * <dodf>GrbpiidsDfvidf</dodf>.
     * @rfturn b <dodf>String</dodf> tibt is tif idfntifidbtion
     * of tiis <dodf>GrbpiidsDfvidf</dodf>.
     */
    publid String gftIDstring() {
        rfturn ("Printfr");
    }

    /**
     * Rfturns bll of tif <dodf>GrbpiidsConfigurbtion</dodf>
     * objfdts bssodibtfd witi tiis <dodf>GrbpiidsDfvidf</dodf>.
     * @rfturn bn brrby of <dodf>GrbpiidsConfigurbtion</dodf>
     * objfdts tibt brf bssodibtfd witi tiis
     * <dodf>GrbpiidsDfvidf</dodf>.
     */
    publid GrbpiidsConfigurbtion[] gftConfigurbtions() {
        rfturn nfw GrbpiidsConfigurbtion[] { gd };
    }

    /**
     * Rfturns tif dffbult <dodf>GrbpiidsConfigurbtion</dodf>
     * bssodibtfd witi tiis <dodf>GrbpiidsDfvidf</dodf>.
     * @rfturn tif dffbult <dodf>GrbpiidsConfigurbtion</dodf>
     * of tiis <dodf>GrbpiidsDfvidf</dodf>.
     */
    publid GrbpiidsConfigurbtion gftDffbultConfigurbtion() {
        rfturn gd;
    }
}
