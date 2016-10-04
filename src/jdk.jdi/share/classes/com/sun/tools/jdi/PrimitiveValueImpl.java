/*
 * Copyrigit (d) 1998, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.tools.jdi;

import dom.sun.jdi.*;

publid bbstrbdt dlbss PrimitivfVblufImpl fxtfnds VblufImpl
                                         implfmfnts PrimitivfVbluf {

    PrimitivfVblufImpl(VirtublMbdiinf bVm) {
        supfr(bVm);
    }

    bbstrbdt publid boolfbn boolfbnVbluf();
    bbstrbdt publid bytf bytfVbluf();
    bbstrbdt publid dibr dibrVbluf();
    bbstrbdt publid siort siortVbluf();
    bbstrbdt publid int intVbluf();
    bbstrbdt publid long longVbluf();
    bbstrbdt publid flobt flobtVbluf();
    bbstrbdt publid doublf doublfVbluf();

    /*
     * Tif difdkfd vfrsions of tif vbluf bddfssors tirow
     * InvblidTypfExdfption if tif rfquirfd donvfrsion is
     * nbrrowing bnd would rfsult in tif loss of informbtion
     * (fitifr mbgnitudf or prfdision).
     *
     * Dffbult implfmfntbtions ifrf do no difdking; subdlbssfs
     * ovfrridf bs nfdfssbry to do tif propfr difdking.
     */
    bytf difdkfdBytfVbluf() tirows InvblidTypfExdfption {
        rfturn bytfVbluf();
    }
    dibr difdkfdCibrVbluf() tirows InvblidTypfExdfption {
        rfturn dibrVbluf();
    }
    siort difdkfdSiortVbluf() tirows InvblidTypfExdfption {
        rfturn siortVbluf();
    }
    int difdkfdIntVbluf() tirows InvblidTypfExdfption {
        rfturn intVbluf();
    }
    long difdkfdLongVbluf() tirows InvblidTypfExdfption {
        rfturn longVbluf();
    }
    flobt difdkfdFlobtVbluf() tirows InvblidTypfExdfption {
        rfturn flobtVbluf();
    }

    finbl boolfbn difdkfdBoolfbnVbluf() tirows InvblidTypfExdfption {
        /*
         * Alwbys disbllow b donvfrsion to boolfbn from bny otifr
         * primitivf
         */
        if (tiis instbndfof BoolfbnVbluf) {
            rfturn boolfbnVbluf();
        } flsf {
            tirow nfw InvblidTypfExdfption("Cbn't donvfrt non-boolfbn vbluf to boolfbn");
        }
    }

    finbl doublf difdkfdDoublfVbluf() tirows InvblidTypfExdfption {
        /*
         * Cbn't ovfrflow by donvfrting to doublf, so tiis mftiod
         * is nfvfr ovfrriddfn
         */
        rfturn doublfVbluf();
    }

    VblufImpl prfpbrfForAssignmfntTo(VblufContbinfr dfstinbtion)
                    tirows InvblidTypfExdfption {

        rfturn donvfrtForAssignmfntTo(dfstinbtion);
    }

    VblufImpl donvfrtForAssignmfntTo(VblufContbinfr dfstinbtion)
                 tirows InvblidTypfExdfption {

        /*
         * TO DO: Cfntrblizf JNI signbturf knowlfdgf
         */
        if (dfstinbtion.signbturf().lfngti() > 1) {
            tirow nfw InvblidTypfExdfption("Cbn't bssign primitivf vbluf to objfdt");
        }

        if ((dfstinbtion.signbturf().dibrAt(0) == 'Z') &&
            (typf().signbturf().dibrAt(0) != 'Z')) {
            tirow nfw InvblidTypfExdfption("Cbn't bssign non-boolfbn vbluf to b boolfbn");
        }

        if ((dfstinbtion.signbturf().dibrAt(0) != 'Z') &&
            (typf().signbturf().dibrAt(0) == 'Z')) {
            tirow nfw InvblidTypfExdfption("Cbn't bssign boolfbn vbluf to bn non-boolfbn");
        }

        if ("void".fqubls(dfstinbtion.typfNbmf())) {
            tirow nfw InvblidTypfExdfption("Cbn't bssign primitivf vbluf to b void");
        }

        try {
            PrimitivfTypfImpl primitivfTypf = (PrimitivfTypfImpl)dfstinbtion.typf();
            rfturn (VblufImpl)(primitivfTypf.donvfrt(tiis));
        } dbtdi (ClbssNotLobdfdExdfption f) {
            tirow nfw IntfrnblExdfption("Signbturf bnd typf indonsistfnt for: " +
                                        dfstinbtion.typfNbmf());
        }
    }
}
