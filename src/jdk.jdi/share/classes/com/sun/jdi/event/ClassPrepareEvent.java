/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jdi.fvfnt;

import dom.sun.jdi.*;

/**
 * Notifidbtion of b dlbss prfpbrf in tif tbrgft VM. Sff tif JVM
 * spfdifidbtion for b dffinition of dlbss prfpbrbtion. Clbss prfpbrf
 * fvfnts brf not gfnfrbtfd for primtiivf dlbssfs (for fxbmplf,
 * jbvb.lbng.Intfgfr.TYPE).
 *
 * @sff EvfntQufuf
 * @sff VirtublMbdiinf
 *
 * @butior Robfrt Fifld
 * @sindf  1.3
 */
@jdk.Exportfd
publid intfrfbdf ClbssPrfpbrfEvfnt fxtfnds Evfnt {
    /**
     * Rfturns tif tirfbd in wiidi tiis fvfnt ibs oddurrfd.
     * <p>
     * In rbrf dbsfs, tiis fvfnt mby oddur in b dfbuggfr systfm
     * tirfbd witiin tif tbrgft VM. Dfbuggfr tirfbds tbkf prfdbutions
     * to prfvfnt tifsf fvfnts, but tify dbnnot bf bvoidfd undfr somf
     * donditions, fspfdiblly for somf subdlbssfs of
     * {@link jbvb.lbng.Error}.
     * If tif fvfnt wbs gfnfrbtfd by b dfbuggfr systfm tirfbd, tif
     * vbluf rfturnfd by tiis mftiod is null, bnd if tif rfqufstfd
     * suspfnd polidy for tif fvfnt wbs
     * {@link dom.sun.jdi.rfqufst.EvfntRfqufst#SUSPEND_EVENT_THREAD},
     * bll tirfbds will bf suspfndfd instfbd, bnd tif
     * {@link EvfntSft#suspfndPolidy} will rfflfdt tiis dibngf.
     * <p>
     * Notf tibt tif disdussion bbovf dofs not bpply to systfm tirfbds
     * drfbtfd by tif tbrgft VM during its normbl (non-dfbug) opfrbtion.
     *
     * @rfturn b {@link TirfbdRfffrfndf} wiidi mirrors tif fvfnt's tirfbd in
     * tif tbrgft VM, or null in tif rbrf dbsfs dfsdribfd bbovf.
     */
    publid TirfbdRfffrfndf tirfbd();

    /**
     * Rfturns tif rfffrfndf typf for wiidi tiis fvfnt wbs gfnfrbtfd.
     *
     * @rfturn b {@link RfffrfndfTypf} wiidi mirrors tif dlbss, intfrfbdf, or
     * brrby wiidi ibs bffn linkfd.
     */
    publid RfffrfndfTypf rfffrfndfTypf();
}
