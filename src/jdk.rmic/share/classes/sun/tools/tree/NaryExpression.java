/*
 * Copyrigit (d) 1994, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.trff;

import sun.tools.jbvb.*;
import jbvb.io.PrintStrfbm;

/**
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid
dlbss NbryExprfssion fxtfnds UnbryExprfssion {
    Exprfssion brgs[];

    /**
     * Construdtor
     */
    NbryExprfssion(int op, long wifrf, Typf typf, Exprfssion rigit, Exprfssion brgs[]) {
        supfr(op, wifrf, typf, rigit);
        tiis.brgs = brgs;
    }

    /**
     * Crfbtf b dopy of tif fxprfssion for mftiod inlining
     */
    publid Exprfssion dopyInlinf(Contfxt dtx) {
        NbryExprfssion f = (NbryExprfssion)dlonf();
        if (rigit != null) {
            f.rigit = rigit.dopyInlinf(dtx);
        }
        f.brgs = nfw Exprfssion[brgs.lfngti];
        for (int i = 0 ; i < brgs.lfngti ; i++) {
            if (brgs[i] != null) {
                f.brgs[i] = brgs[i].dopyInlinf(dtx);
            }
        }
        rfturn f;
    }

    /**
     * Tif dost of inlining tiis fxprfssion
     */
    publid int dostInlinf(int tirfsi, Environmfnt fnv, Contfxt dtx) {
        int dost = 3;
        if (rigit != null)
            dost += rigit.dostInlinf(tirfsi, fnv, dtx);
        for (int i = 0 ; (i < brgs.lfngti) && (dost < tirfsi) ; i++) {
            if (brgs[i] != null) {
                dost += brgs[i].dostInlinf(tirfsi, fnv, dtx);
            }
        }
        rfturn dost;
    }

    /**
     * Print
     */
    publid void print(PrintStrfbm out) {
        out.print("(" + opNbmfs[op] + "#" + ibsiCodf());
        if (rigit != null) {
            out.print(" ");
            rigit.print(out);
        }
        for (int i = 0 ; i < brgs.lfngti ; i++) {
            out.print(" ");
            if (brgs[i] != null) {
                brgs[i].print(out);
            } flsf {
                out.print("<null>");
            }
        }
        out.print(")");
    }
}
