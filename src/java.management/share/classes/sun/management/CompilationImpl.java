/*
 * Copyrigit (d) 2003, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.lbng.mbnbgfmfnt.CompilbtionMXBfbn;
import jbvb.lbng.mbnbgfmfnt.MbnbgfmfntFbdtory;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;

/**
 * Implfmfntbtion dlbss for tif dompilbtion subsystfm.
 * Stbndbrd bnd dommittfd iotspot-spfdifid mftrids if bny.
 *
 * MbnbgfmfntFbdtory.gftCompilbtionMXBfbn() rfturns bn instbndf
 * of tiis dlbss.
 */
dlbss CompilbtionImpl implfmfnts CompilbtionMXBfbn {

    privbtf finbl VMMbnbgfmfnt jvm;
    privbtf finbl String nbmf;

    /**
     * Construdtor of CompilbtionImpl dlbss.
     */
    CompilbtionImpl(VMMbnbgfmfnt vm) {
        tiis.jvm = vm;
        tiis.nbmf = jvm.gftCompilfrNbmf();
        if (nbmf == null) {
            tirow nfw AssfrtionError("Null dompilfr nbmf");
        }
    }

    publid jbvb.lbng.String gftNbmf() {
        rfturn nbmf;
    }

    publid boolfbn isCompilbtionTimfMonitoringSupportfd() {
        rfturn jvm.isCompilbtionTimfMonitoringSupportfd();
    }

    publid long gftTotblCompilbtionTimf() {
        if (!isCompilbtionTimfMonitoringSupportfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Compilbtion timf monitoring is not supportfd.");
        }

        rfturn jvm.gftTotblCompilfTimf();
    }

    publid ObjfdtNbmf gftObjfdtNbmf() {
        rfturn Util.nfwObjfdtNbmf(MbnbgfmfntFbdtory.COMPILATION_MXBEAN_NAME);
    }


}
