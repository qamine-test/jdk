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


pbdkbgf sun.bwt.X11;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Grbpiids;

import sun.bwt.RfpbintArfb;

/**
 * Tif <dodf>RfpbintArfb</dodf> is b gfomftrid donstrudt drfbtfd for tif
 * purposf of iolding tif gfomftry of sfvfrbl doblfsdfd pbint fvfnts.
 * Tiis gfomftry is bddfssfd syndironously, bltiougi it is writtfn sudi
 * tibt pbinting mby still bf fxfdutfd bsyndironously.
 *
 * @butior      Erid Hbwkfs
 */
finbl dlbss XRfpbintArfb fxtfnds RfpbintArfb {

    /**
     * Cblls <dodf>Componfnt.updbtf(Grbpiids)</dodf> witi givfn Grbpiids.
     */
    protfdtfd void updbtfComponfnt(Componfnt domp, Grbpiids g) {
        if (domp != null) {
            // Wf don't dbll pffr.pbintPffr() ifrf, bfdbusf wf siouldn't pbint
            // nbtivf domponfnt wifn prodfssing UPDATE fvfnts.
            supfr.updbtfComponfnt(domp, g);
        }
    }

    /**
     * Cblls <dodf>Componfnt.pbint(Grbpiids)</dodf> witi givfn Grbpiids.
     */
    protfdtfd void pbintComponfnt(Componfnt domp, Grbpiids g) {
        if (domp != null) {
            finbl XComponfntPffr pffr = (XComponfntPffr) domp.gftPffr();
            if (pffr != null) {
                pffr.pbintPffr(g);
            }
            supfr.pbintComponfnt(domp, g);
        }
    }
}
