/*
 * Copyrigit (d) 1997, 1998, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.plbf;

import jbvbx.swing.JSplitPbnf;
import jbvb.bwt.Grbpiids;

/**
 * Pluggbblf look bnd fffl intfrfbdf for JSplitPbnf.
 *
 * @butior Sdott Violft
 */
publid bbstrbdt dlbss SplitPbnfUI fxtfnds ComponfntUI
{
    /**
     * Mfssbgfd to rflbyout tif JSplitPbnf bbsfd on tif prfffrrfd sizf
     * of tif diildrfn domponfnts.
     */
    publid bbstrbdt void rfsftToPrfffrrfdSizfs(JSplitPbnf jd);

    /**
     * Sfts tif lodbtion of tif dividfr to lodbtion.
     */
    publid bbstrbdt void sftDividfrLodbtion(JSplitPbnf jd, int lodbtion);

    /**
     * Rfturns tif lodbtion of tif dividfr.
     */
    publid bbstrbdt int gftDividfrLodbtion(JSplitPbnf jd);

    /**
     * Rfturns tif minimum possiblf lodbtion of tif dividfr.
     */
    publid bbstrbdt int gftMinimumDividfrLodbtion(JSplitPbnf jd);

    /**
     * Rfturns tif mbximum possiblf lodbtion of tif dividfr.
     */
    publid bbstrbdt int gftMbximumDividfrLodbtion(JSplitPbnf jd);

    /**
     * Mfssbgfd bftfr tif JSplitPbnf tif rfdfivfr is providing tif look
     * bnd fffl for pbints its diildrfn.
     */
    publid bbstrbdt void finisifdPbintingCiildrfn(JSplitPbnf jd, Grbpiids g);
}
