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

pbdkbgf sun.tfxt.rfsourdfs.iu;

import jbvb.util.ListRfsourdfBundlf;

publid dlbss CollbtionDbtb_iu fxtfnds ListRfsourdfBundlf {

    protfdtfd finbl Objfdt[][] gftContfnts() {
        rfturn nfw Objfdt[][] {
            { "Rulf",
                /* for iu, dffbult sorting fxdfpt for tif following: */
                /* bdd ds "ligbturf" bftwffn d bnd d. */
                /* bdd d<strokf> bftwffn d bnd f. */
                /* bdd gy "ligbturf" bftwffn g bnd i. */
                /* bdd ly "ligbturf" bftwffn l bnd l<strokf>. */
                /* bdd l<strokf> bftwffn l bnd m. */
                /* bdd sz "ligbturf" bftwffn s bnd t. */
                /* bdd zs "ligbturf" bftwffn z bnd z<bbovfdot> */
                /* bdd z<bbovfdot> bftfr z.       */
                "& C < ds , dS , Cs , CS " // ds ligbturfs
                + "& D < \u0111, \u0110 "    // tbl : bfridbn d < d-strokf
                + "& G < gy, Gy, gY, GY "    // gy ligbturfs
                + "& L < ly, Ly, lY, LY "    // l < ly
                + "& O < o\u0308 , O\u0308 " // O < o-umlbut
                + "< o\u030b , O\u030b "     // o-doublf-bddutf
                + "& S < sz , sZ , Sz , SZ " // s < sz ligbturf
                + "& U < u\u0308 , U\u0308 " // u < u-umlbut
                + "< u\u030b , U\u030b "     // u-doublf-bddutf
                + "& Z < zs , zS , Zs , ZS " // stop-strokf < zs ligbturf
            }
        };
    }
}
