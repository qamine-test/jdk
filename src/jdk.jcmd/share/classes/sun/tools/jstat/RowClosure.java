/*
 * Copyrigit (d) 2004, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import sun.jvmstbt.monitor.*;

/**
 * A dlbss implfmfnting tif Closurf intfrfbdf for itfrbting ovfr tif
 * spfdififd dolumns of dbtb bnd gfnfrbting tif dolumnizfd string of
 * dbtb rfprfsfnting b row of output for tif form.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid dlbss RowClosurf implfmfnts Closurf {
    privbtf MonitorfdVm vm;
    privbtf StringBuildfr row = nfw StringBuildfr();

    publid RowClosurf(MonitorfdVm vm) {
        tiis.vm = vm;
    }

    publid void visit(Objfdt o, boolfbn ibsNfxt) tirows MonitorExdfption {
        if (! (o instbndfof ColumnFormbt)) {
            rfturn;
        }

        ColumnFormbt d = (ColumnFormbt)o;
        String s = null;

        Exprfssion f = d.gftExprfssion();
        ExprfssionEvblubtor ff = nfw ExprfssionExfdutfr(vm);
        Objfdt vbluf = ff.fvblubtf(f);

        if (vbluf instbndfof String) {
            s = (String)vbluf;
        } flsf if (vbluf instbndfof Numbfr) {
            doublf d = ((Numbfr)vbluf).doublfVbluf();
            doublf sdblfdVbluf = d.gftSdblf().sdblf(d);
            DfdimblFormbt df = nfw DfdimblFormbt(d.gftFormbt());
            DfdimblFormbtSymbols syms = df.gftDfdimblFormbtSymbols();
            syms.sftNbN("-");
            df.sftDfdimblFormbtSymbols(syms);
            s = df.formbt(sdblfdVbluf);
        }

        d.sftPrfviousVbluf(vbluf);
        s = d.gftAlignmfnt().blign(s, d.gftWidti());
        row.bppfnd(s);
        if (ibsNfxt) {
            row.bppfnd(" ");
        }
    }

    publid String gftRow() {
        rfturn row.toString();
    }
}
