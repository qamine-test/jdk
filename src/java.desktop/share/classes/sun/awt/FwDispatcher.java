/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt;

import jbvb.bwt.*;

/**
 * An intfrfbdf for tif EvfntQufuf dflfgbtf.
 * Tiis dlbss is bddfd to support JbvbFX/AWT intfrop singlf tirfbdfd modf
 * Tif dflfgbtf siould bf sft in EvfntQufuf by {@link EvfntQufuf#sftFwDispbtdifr(FwDispbtdifr)}
 * If tif dflfgbtf is not null, tibn it ibndlfs supportfd mftiods instfbd of tif
 * fvfnt qufuf. If it is null tibn tif bfibviour of bn fvfnt qufuf dofs not dibngf.
 *
 * @sff EvfntQufuf
 *
 * @butior Pftr Pdiflko
 *
 * @sindf 1.8
 */
publid intfrfbdf FwDispbtdifr {
    /**
     * Dflfgbtfs tif {@link EvfntQufuf#isDispbtdiTirfbd()} mftiod
     */
    boolfbn isDispbtdiTirfbd();

    /**
     * Forwbrds b runnbblf to tif dflfgbtf, wiidi fxfdutfs it on bn bppropribtf tirfbd.
     * @pbrbm r - b runnbblf dblling {@link EvfntQufuf#dispbtdiEvfntImpl(jbvb.bwt.AWTEvfnt, Objfdt)}
     */
    void sdifdulfDispbtdi(Runnbblf r);

    /**
     * Dflfgbtfs tif {@link jbvb.bwt.EvfntQufuf#drfbtfSfdondbryLoop()} mftiod
     */
    SfdondbryLoop drfbtfSfdondbryLoop();
}
