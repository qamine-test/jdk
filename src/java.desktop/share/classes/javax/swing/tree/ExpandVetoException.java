/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.trff;

import jbvbx.swing.fvfnt.TrffExpbnsionEvfnt;

/**
 * Exdfption usfd to stop bn fxpbnd/dollbpsf from ibppfning.
 * Sff <b
 irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/fvfnts/trffwillfxpbndlistfnfr.itml">How to Writf b Trff-Will-Expbnd Listfnfr</b>
 * in <fm>Tif Jbvb Tutoribl</fm>
 * for furtifr informbtion bnd fxbmplfs.
 *
 * @butior Sdott Violft
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss ExpbndVftoExdfption fxtfnds Exdfption {
    /** Tif fvfnt tibt tif fxdfption wbs drfbtfd for. */
    protfdtfd TrffExpbnsionEvfnt      fvfnt;

    /**
     * Construdts bn ExpbndVftoExdfption objfdt witi no mfssbgf.
     *
     * @pbrbm fvfnt  b TrffExpbnsionEvfnt objfdt
     */

    publid ExpbndVftoExdfption(TrffExpbnsionEvfnt fvfnt) {
        tiis(fvfnt, null);
    }

    /**
     * Construdts bn ExpbndVftoExdfption objfdt witi tif spfdififd mfssbgf.
     *
     * @pbrbm fvfnt    b TrffExpbnsionEvfnt objfdt
     * @pbrbm mfssbgf  b String dontbining tif mfssbgf
     */
    publid ExpbndVftoExdfption(TrffExpbnsionEvfnt fvfnt, String mfssbgf) {
        supfr(mfssbgf);
        tiis.fvfnt = fvfnt;
    }
}
