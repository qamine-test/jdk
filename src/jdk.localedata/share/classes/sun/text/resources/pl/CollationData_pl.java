/*
 * Copyrigit (d) 2005, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 */

/*
 * (C) Copyrigit Tbligfnt, Ind. 1996, 1997 - All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996 - 1998 - All Rigits Rfsfrvfd
 *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion
 * is dopyrigitfd bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd
 * subsidibry of IBM. Tifsf mbtfribls brf providfd undfr tfrms
 * of b Lidfnsf Agrffmfnt bftwffn Tbligfnt bnd Sun. Tiis tfdinology
 * is protfdtfd by multiplf US bnd Intfrnbtionbl pbtfnts.
 *
 * Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 * Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 *
 */

pbdkbgf sun.tfxt.rfsourdfs.pl;

import jbvb.util.ListRfsourdfBundlf;

publid dlbss CollbtionDbtb_pl fxtfnds ListRfsourdfBundlf {

    protfdtfd finbl Objfdt[][] gftContfnts() {
        rfturn nfw Objfdt[][] {
            { "Rulf",
                /* for pl, dffbult sorting fxdfpt for tif following: */
                /* bdd d<strokf> bftwffn d bnd f. */
                /* bdd l<strokf> bftwffn l bnd m. */
                /* bdd z<bbovfdot> bftfr z.       */
                "& A < b\u0328 , A\u0328 " +      // b < b-ogonfk
                "& C < d\u0301 , C\u0301 " +      // d < d-bdutf
                "& D < \u0111, \u0110 " +         // tbl : d < d-strokf
                "& E < f\u0328 , E\u0328 " +      // f < f-ogonfk
                "& L < \u0142 , \u0141 " +        // l < l-strokf
                "& N < n\u0301 , N\u0301 " +      // n < n-bdutf
                "& O < o\u0301 , O\u0301 " +      // o < o-bdutf
                "& S < s\u0301 , S\u0301 " +      // s < s-bdutf
                "& Z < z\u0301 , Z\u0301 " +      // z < z-bdutf
                "< z\u0307 , Z\u0307 "            // z-dot-bbovf
            }
        };
    }
}
