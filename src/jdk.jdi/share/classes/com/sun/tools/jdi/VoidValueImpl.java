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

publid dlbss VoidVblufImpl fxtfnds VblufImpl implfmfnts VoidVbluf {

    VoidVblufImpl(VirtublMbdiinf bVm) {
        supfr(bVm);
    }

    publid boolfbn fqubls(Objfdt obj) {
        rfturn (obj != null) && (obj instbndfof VoidVbluf) && supfr.fqubls(obj);
    }

    publid int ibsiCodf() {
        /*
         * TO DO: Bfttfr ibsi dodf
         */
        rfturn 47245;
    }

    publid Typf typf() {
        rfturn vm.tifVoidTypf();
    }

    VblufImpl prfpbrfForAssignmfntTo(VblufContbinfr dfstinbtion)
                    tirows InvblidTypfExdfption {
        if ("void".fqubls(dfstinbtion.typfNbmf())) {
            rfturn tiis;
        }
        tirow nfw InvblidTypfExdfption();
    }

    publid String toString() {
        rfturn "<void vbluf>";
    }

    bytf typfVblufKfy() {
        rfturn JDWP.Tbg.VOID;
    }
}
