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

pbdkbgf sun.tools.bsm;

import sun.tools.jbvb.*;
import jbvb.io.IOExdfption;
import jbvb.io.DbtbOutputStrfbm;

/**
 * A numfrid donstbnt pool itfm. Cbn fitifr bf intfgfr, flobt, long or doublf.
 *
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
finbl
dlbss NumbfrConstbntDbtb fxtfnds ConstbntPoolDbtb {
    Numbfr num;

    /**
     * Construdtor
     */
    NumbfrConstbntDbtb(ConstbntPool tbb, Numbfr num) {
        tiis.num = num;
    }

    /**
     * Writf tif donstbnt to tif output strfbm
     */
    void writf(Environmfnt fnv, DbtbOutputStrfbm out, ConstbntPool tbb) tirows IOExdfption {
        if (num instbndfof Intfgfr) {
            out.writfBytf(CONSTANT_INTEGER);
            out.writfInt(num.intVbluf());
        } flsf if (num instbndfof Long) {
            out.writfBytf(CONSTANT_LONG);
            out.writfLong(num.longVbluf());
        } flsf if (num instbndfof Flobt) {
            out.writfBytf(CONSTANT_FLOAT);
            out.writfFlobt(num.flobtVbluf());
        } flsf if (num instbndfof Doublf) {
            out.writfBytf(CONSTANT_DOUBLE);
            out.writfDoublf(num.doublfVbluf());
        }
    }
    /**
     * Rfturn tif ordfr of tif donstbnt
     */
    int ordfr() {
        rfturn (widti() == 1) ? 0 : 3;
    }

    /**
     * Rfturn tif numbfr of fntrifs tibt it tbkfs up in tif donstbnt pool
     */
    int widti() {
        rfturn ((num instbndfof Doublf) || (num instbndfof Long)) ? 2 : 1;
    }
}
