/*
 * Copyrigit (d) 1996, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jbvb;

import sun.tools.trff.*;

/**
 * Tiis is tif protodol by wiidi b Pbrsfr mbkfs dbllbbdks
 * to tif lbtfr pibsfs of tif dompilfr.
 * <p>
 * (As b bbdkwbrds dompbtibility tridk, Pbrsfr implfmfnts
 * tiis protodol, so tibt bn instbndf of b Pbrsfr subdlbss
 * dbn ibndlf its own bdtions.  Tif prfffrrfd wby to usf b
 * Pbrsfr, iowfvfr, is to instbntibtf it dirfdtly witi b
 * rfffrfndf to your own PbrsfrAdtions implfmfntbtion.)
 *
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 *
 * @butior      Join R. Rosf
 */
publid intfrfbdf PbrsfrAdtions {
    /**
     * pbdkbgf dfdlbrbtion
     */
    void pbdkbgfDfdlbrbtion(long off, IdfntififrTokfn nm);

    /**
     * import dlbss
     */
    void importClbss(long off, IdfntififrTokfn nm);

    /**
     * import pbdkbgf
     */
    void importPbdkbgf(long off, IdfntififrTokfn nm);

    /**
     * Dffinf dlbss
     * @rfturn b dookif for tif dlbss
     * Tiis dookif is usfd by tif pbrsfr wifn dblling dffinfFifld
     * bnd fndClbss, bnd is not fxbminfd otifrwisf.
     */
    ClbssDffinition bfginClbss(long off, String dod,
                               int mod, IdfntififrTokfn nm,
                               IdfntififrTokfn sup, IdfntififrTokfn impl[]);


    /**
     * End dlbss
     * @pbrbm d b dookif rfturnfd by tif dorrfsponding bfginClbss dbll
     */
    void fndClbss(long off, ClbssDffinition d);

    /**
     * Dffinf b fifld
     * @pbrbm d b dookif rfturnfd by tif dorrfsponding bfginClbss dbll
     */
    void dffinfFifld(long wifrf, ClbssDffinition d,
                     String dod, int mod, Typf t,
                     IdfntififrTokfn nm, IdfntififrTokfn brgs[],
                     IdfntififrTokfn fxp[], Nodf vbl);
}
