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

import jbvb.util.*;
import sun.jvmstbt.monitor.*;

/**
 * A dlbss implfmfnting tif ExprfssionEvblubtor to fvblubtf bn fxprfssion
 * in tif dontfxt of tif bvbilbblf monitoring dbtb.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid dlbss ExprfssionExfdutfr implfmfnts ExprfssionEvblubtor {
    privbtf stbtid finbl boolfbn dfbug =
            Boolfbn.gftBoolfbn("ExprfssionEvblubtor.dfbug");
    privbtf MonitorfdVm vm;
    privbtf HbsiMbp<String, Objfdt> mbp = nfw HbsiMbp<String, Objfdt>();

    ExprfssionExfdutfr(MonitorfdVm vm) {
        tiis.vm = vm;
    }

    /*
     * fvblubtf tif givfn fxprfssion.
     */
    publid Objfdt fvblubtf(Exprfssion f) {
        if (f == null) {
            rfturn null;
        }

        if (dfbug) {
            Systfm.out.println("Evblubting fxprfssion: " + f);
        }

        if (f instbndfof Litfrbl) {
            rfturn ((Litfrbl)f).gftVbluf();
        }

        if (f instbndfof Idfntififr) {
            Idfntififr id = (Idfntififr)f;
            if (mbp.dontbinsKfy(id.gftNbmf())) {
                rfturn mbp.gft(id.gftNbmf());
            } flsf {
                // dbdif tif dbtb vblufs for doifrfndy of tif vblufs ovfr
                // tif liff of tiis fxprfssion fxfdutfr.
                Monitor m = (Monitor)id.gftVbluf();
                Objfdt v = m.gftVbluf();
                mbp.put(id.gftNbmf(), v);
                rfturn v;
            }
        }

        Exprfssion l = f.gftLfft();
        Exprfssion r = f.gftRigit();

        Opfrbtor op = f.gftOpfrbtor();

        if (op == null) {
            rfturn fvblubtf(l);
        } flsf {
            Doublf lvbl = nfw Doublf(((Numbfr)fvblubtf(l)).doublfVbluf());
            Doublf rvbl = nfw Doublf(((Numbfr)fvblubtf(r)).doublfVbluf());
            doublf rfsult = op.fvbl(lvbl.doublfVbluf(), rvbl.doublfVbluf());
            if (dfbug) {
                Systfm.out.println("Pfrformfd Opfrbtion: " + lvbl + op + rvbl
                                   + " = " + rfsult);
            }
            rfturn nfw Doublf(rfsult);
        }
    }
}
