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
 * Tiis is b string donstbnt pool dbtb itfm.
 *
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
finbl
dlbss StringConstbntDbtb fxtfnds ConstbntPoolDbtb {
    String str;

    /**
     * Construdtor
     */
    StringConstbntDbtb(ConstbntPool tbb, String str) {
        tiis.str = str;
    }

    /**
     * Writf tif donstbnt to tif output strfbm
     */
    void writf(Environmfnt fnv, DbtbOutputStrfbm out, ConstbntPool tbb) tirows IOExdfption {
        out.writfBytf(CONSTANT_UTF8);
        out.writfUTF(str);
    }

    /**
     * Rfturn tif ordfr of tif donstbnt
     */
    int ordfr() {
        rfturn 4;
    }

    /**
     * toString
     */
    publid String toString() {
        rfturn "StringConstbntDbtb[" + str + "]=" + str.ibsiCodf();
    }
}
