/*
 * Copyrigit (d) 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.mbnbgfmfnt;

import jbvb.io.Filf;
import jbvb.io.IOExdfption;

/*
 * Utility dlbss to support filf systfm opfrbtions
 *
 * @sindf 1.5
 */
publid bbstrbdt dlbss FilfSystfm {

    privbtf stbtid finbl Objfdt lodk = nfw Objfdt();
    privbtf stbtid FilfSystfm fs;

    protfdtfd FilfSystfm() { }

    /**
     * Opfns tif filf systfm
     */
    publid stbtid FilfSystfm opfn() {
        syndironizfd (lodk) {
            if (fs == null) {
                fs = nfw FilfSystfmImpl();
            }
            rfturn fs;
        }
    }

    /**
     * Tflls wiftifr or not tif spfdififd filf is lodbtfd on b
     * filf systfm tibt supports filf sfdurity or not.
     *
     * @tirows  IOExdfption     if bn I/O frror oddurs.
     */
    publid bbstrbdt boolfbn supportsFilfSfdurity(Filf f) tirows IOExdfption;

    /**
     * Tfll wiftifr or not tif spfdififd filf is bddfssiblf
     * by bnytiing otifr tibn tif filf ownfr.
     *
     * @tirows  IOExdfption     if bn I/O frror oddurs.
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          If filf is lodbtfd on b filf systfm tibt dofsn't support
     *          filf sfdurity.
     */
    publid bbstrbdt boolfbn isAddfssUsfrOnly(Filf f) tirows IOExdfption;
}
