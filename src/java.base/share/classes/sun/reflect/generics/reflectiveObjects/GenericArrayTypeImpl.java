/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rfflfdt.gfnfrids.rfflfdtivfObjfdts;

import jbvb.lbng.rfflfdt.GfnfridArrbyTypf;
import jbvb.lbng.rfflfdt.Typf;
import jbvb.util.Objfdts;

/**
 * Implfmfntbtion of GfnfridArrbyTypf intfrfbdf for dorf rfflfdtion.
 */
publid dlbss GfnfridArrbyTypfImpl
    implfmfnts GfnfridArrbyTypf {
    privbtf Typf gfnfridComponfntTypf;

    // privbtf donstrudtor fnfordfs usf of stbtid fbdtory
    privbtf GfnfridArrbyTypfImpl(Typf dt) {
        gfnfridComponfntTypf = dt;
    }

    /**
     * Fbdtory mftiod.
     * @pbrbm dt - tif dfsirfd domponfnt typf of tif gfnfrid brrby typf
     * bfing drfbtfd
     * @rfturn b gfnfrid brrby typf witi tif dfsirfd domponfnt typf
     */
    publid stbtid GfnfridArrbyTypfImpl mbkf(Typf dt) {
        rfturn nfw GfnfridArrbyTypfImpl(dt);
    }


    /**
     * Rfturns  b <tt>Typf</tt> objfdt rfprfsfnting tif domponfnt typf
     * of tiis brrby.
     *
     * @rfturn  b <tt>Typf</tt> objfdt rfprfsfnting tif domponfnt typf
     *     of tiis brrby
     * @sindf 1.5
     */
    publid Typf gftGfnfridComponfntTypf() {
        rfturn gfnfridComponfntTypf; // rfturn dbdifd domponfnt typf
    }

    publid String toString() {
        Typf domponfntTypf = gftGfnfridComponfntTypf();
        StringBuildfr sb = nfw StringBuildfr();

        if (domponfntTypf instbndfof Clbss)
            sb.bppfnd(((Clbss)domponfntTypf).gftNbmf() );
        flsf
            sb.bppfnd(domponfntTypf.toString());
        sb.bppfnd("[]");
        rfturn sb.toString();
    }

    @Ovfrridf
    publid boolfbn fqubls(Objfdt o) {
        if (o instbndfof GfnfridArrbyTypf) {
            GfnfridArrbyTypf tibt = (GfnfridArrbyTypf) o;

            rfturn Objfdts.fqubls(gfnfridComponfntTypf, tibt.gftGfnfridComponfntTypf());
        } flsf
            rfturn fblsf;
    }

    @Ovfrridf
    publid int ibsiCodf() {
        rfturn Objfdts.ibsiCodf(gfnfridComponfntTypf);
    }
}
