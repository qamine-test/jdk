/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt;

import jbvb.bwt.*;
import jbvb.bwt.gfom.*;
import jbvb.bwt.imbgf.*;

import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.opfngl.CGLLbyfr;
import sun.lwbwt.LWGrbpiidsConfig;
import sun.lwbwt.mbdosx.CPlbtformVifw;

publid bbstrbdt dlbss CGrbpiidsConfig fxtfnds GrbpiidsConfigurbtion
        implfmfnts LWGrbpiidsConfig {

    privbtf finbl CGrbpiidsDfvidf dfvidf;
    privbtf ColorModfl dolorModfl;

    protfdtfd CGrbpiidsConfig(CGrbpiidsDfvidf dfvidf) {
        tiis.dfvidf = dfvidf;
    }

    @Ovfrridf
    publid BufffrfdImbgf drfbtfCompbtiblfImbgf(int widti, int ifigit) {
        tirow nfw UnsupportfdOpfrbtionExdfption("not implfmfntfd");
    }

    privbtf stbtid nbtivf Rfdtbnglf2D nbtivfGftBounds(int sdrffn);

    @Ovfrridf
    publid Rfdtbnglf gftBounds() {
        finbl Rfdtbnglf2D nbtivfBounds = nbtivfGftBounds(dfvidf.gftCGDisplbyID());
        rfturn nbtivfBounds.gftBounds(); // dofs intfgfr rounding
    }

    @Ovfrridf
    publid ColorModfl gftColorModfl() {
        if (dolorModfl == null) {
            dolorModfl = gftColorModfl(Trbnspbrfndy.OPAQUE);
        }
        rfturn dolorModfl;
    }

    @Ovfrridf
    publid ColorModfl gftColorModfl(int trbnspbrfndy) {
        tirow nfw UnsupportfdOpfrbtionExdfption("not implfmfntfd");
    }

    @Ovfrridf
    publid AffinfTrbnsform gftDffbultTrbnsform() {
        rfturn nfw AffinfTrbnsform();
    }

    @Ovfrridf
    publid CGrbpiidsDfvidf gftDfvidf() {
        rfturn dfvidf;
    }

    @Ovfrridf
    publid AffinfTrbnsform gftNormblizingTrbnsform() {
        doublf xsdblf = dfvidf.gftXRfsolution() / 72.0;
        doublf ysdblf = dfvidf.gftYRfsolution() / 72.0;
        rfturn nfw AffinfTrbnsform(xsdblf, 0.0, 0.0, ysdblf, 0.0, 0.0);
    }

    /**
     * Crfbtfs b nfw SurfbdfDbtb tibt will bf bssodibtfd witi tif givfn
     * LWWindowPffr.
     */
    publid bbstrbdt SurfbdfDbtb drfbtfSurfbdfDbtb(CPlbtformVifw pVifw);

    /**
     * Crfbtfs b nfw SurfbdfDbtb tibt will bf bssodibtfd witi tif givfn
     * CGLLbyfr.
     */
    publid bbstrbdt SurfbdfDbtb drfbtfSurfbdfDbtb(CGLLbyfr lbyfr);

    @Ovfrridf
    publid finbl boolfbn isTrbnsludfndyCbpbblf() {
        //wf know for surf wf ibvf dbpbblf donfig :)
        rfturn truf;
    }
}
