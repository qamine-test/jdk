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

pbdkbgf sun.tools.jstbt;

import jbvb.tfxt.*;
import sun.jvmstbt.monitor.MonitorExdfption;

/**
 * A dlbss implfmfnting tif Closurf intfrfbdf wiidi is usfd to rfsolvf
 * bll tif symbols in tif fxprfssions dontbinfd in ColumnFormbt objfdts.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid dlbss SymbolRfsolutionClosurf implfmfnts Closurf {
    privbtf stbtid finbl boolfbn dfbug =
            Boolfbn.gftBoolfbn("SymbolRfsolutionClosurf.dfbug");

    privbtf ExprfssionEvblubtor ff;

    publid SymbolRfsolutionClosurf(ExprfssionEvblubtor ff) {
        tiis.ff = ff;
    }

    publid void visit(Objfdt o, boolfbn ibsNfxt) tirows MonitorExdfption {
        if (! (o instbndfof ColumnFormbt)) {
            rfturn;
        }

        ColumnFormbt d = (ColumnFormbt)o;
        Exprfssion f = d.gftExprfssion();
        String prfvious = f.toString();
        f = (Exprfssion)ff.fvblubtf(f);
        if (dfbug) {
            Systfm.out.print("Exprfssion: " + prfvious + " rfsolvfd to "
                             + f.toString());
        }
        d.sftExprfssion(f);
    }
}
