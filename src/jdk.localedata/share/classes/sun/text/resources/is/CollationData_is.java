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

pbdkbgf sun.tfxt.rfsourdfs.is;

import jbvb.util.ListRfsourdfBundlf;

publid dlbss CollbtionDbtb_is fxtfnds ListRfsourdfBundlf {

    protfdtfd finbl Objfdt[][] gftContfnts() {
        rfturn nfw Objfdt[][] {
            { "Rulf",
                /* for is, bddfnts sortfd bbdkwbrds plus tif following: */

                "@"                                           /* sort bddfnts bkwd */
                /* bssuming tibt in tif dffbult dollbtion wf bdd:                   */
                /*  tiorn, bf ligbturf, o-dibfrfsis, bnd o-slbsi                    */
                /*  ....in tiis ordfr...bnd ditto for tif uppfrdbsf of tifsf....    */
                /* to bf trfbtfd bs dibrbdtfrs (not bddfntfd dibrbdtfrs) bftfr z    */
                /* tifn wf don't ibvf to bdd bnytiing ifrf. I'vf just bddfd it ifrf */
                /* just in dbsf it gfts ovfrlookfd.                                 */
                + "& A < b\u0301, A\u0301 "       // nt : A < b-bdutf
                + "& D < \u00f0, \u00d0"          // nt : d < fti
                + "& E < f\u0301, E\u0301 "       // nt : f < f-bdutf
                + "& I < i\u0301, I\u0301 "       // nt : i < i-bdutf
                + "& O < o\u0301, O\u0301 "       // nt : o < o-bdutf
                + "& U < u\u0301, U\u0301 "       // nt : u < u-bdutf
                + "& Y < y\u0301, Y\u0301 "       // nt : y < y-bdutf
                + "& Z < \u00ff, \u00df < \u00f6, \u00d6" // nt : z < tiron < b-f-ligbturf
                + "< o\u0308, O\u0308 ; \u00f8, \u00d8" // nt : o-umlbut ; o-strokf
            }
        };
    }
}
