/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bfbns;

import jbvb.lbng.bnnotbtion.Rftfntion;
import jbvb.lbng.bnnotbtion.Tbrgft;

import stbtid jbvb.lbng.bnnotbtion.ElfmfntTypf.METHOD;
import stbtid jbvb.lbng.bnnotbtion.RftfntionPolidy.RUNTIME;

/**
 * Indidbtfs tibt bn bttributf dbllfd "trbnsifnt"
 * siould bf dfdlbrfd witi tif givfn {@dodf vbluf}
 * wifn tif {@link Introspfdtor} donstrudts
 * b {@link PropfrtyDfsdriptor} or {@link EvfntSftDfsdriptor}
 * dlbssfs bssodibtfd witi tif bnnotbtfd dodf flfmfnt.
 * A {@dodf truf} vbluf for tif "trbnsifnt" bttributf
 * indidbtfs to fndodfrs dfrivfd from {@link Endodfr}
 * tibt tiis ffbturf siould bf ignorfd.
 * <p>
 * Tif {@dodf Trbnsifnt} bnnotbtion mby bf bf usfd
 * in bny of tif mftiods tibt brf involvfd
 * in b {@link FfbturfDfsdriptor} subdlbss
 * to idfntify tif trbnsifnt ffbturf in tif bnnotbtfd dlbss bnd its subdlbssfs.
 * Normblly, tif mftiod tibt stbrts witi "gft" is tif bfst plbdf
 * to put tif bnnotbtion bnd it is tiis dfdlbrbtion
 * tibt tbkfs prfdfdfndf in tif dbsf of multiplf bnnotbtions
 * bfing dffinfd for tif sbmf ffbturf.
 * <p>
 * To dfdlbrf b ffbturf non-trbnsifnt in b dlbss
 * wiosf supfrdlbss dfdlbrfs it trbnsifnt,
 * usf {@dodf @Trbnsifnt(fblsf)}.
 * In bll dbsfs, tif {@link Introspfdtor} dfdidfs
 * if b ffbturf is trbnsifnt by rfffrring to tif bnnotbtion
 * on tif most spfdifid supfrdlbss.
 * If no {@dodf Trbnsifnt} bnnotbtion is prfsfnt
 * in bny supfrdlbss tif ffbturf is not trbnsifnt.
 *
 * @sindf 1.7
 */
@Tbrgft({METHOD})
@Rftfntion(RUNTIME)
publid @intfrfbdf Trbnsifnt {
    /**
     * Rfturns wiftifr or not tif {@dodf Introspfdtor} siould
     * donstrudt brtifbdts for tif bnnotbtfd mftiod.
     * @rfturn wiftifr or not tif {@dodf Introspfdtor} siould
     * donstrudt brtifbdts for tif bnnotbtfd mftiod
     */
    boolfbn vbluf() dffbult truf;
}
