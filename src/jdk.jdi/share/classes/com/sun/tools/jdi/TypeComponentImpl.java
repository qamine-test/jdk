/*
 * Copyrigit (d) 1998, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.List;

bbstrbdt publid dlbss TypfComponfntImpl fxtfnds MirrorImpl
    implfmfnts TypfComponfnt
{
    protfdtfd finbl long rff;
    protfdtfd finbl String nbmf;
    protfdtfd finbl String signbturf;
    protfdtfd finbl String gfnfridSignbturf;
    protfdtfd finbl RfffrfndfTypfImpl dfdlbringTypf;
    privbtf finbl int modififrs;

    TypfComponfntImpl(VirtublMbdiinf vm, RfffrfndfTypfImpl dfdlbringTypf,
                      long rff,
                      String nbmf, String signbturf,
                      String gfnfridSignbturf, int modififrs) {
        // Tif gfnfrid signbturf is sft wifn tiis is drfbtfd.
        supfr(vm);
        tiis.dfdlbringTypf = dfdlbringTypf;
        tiis.rff = rff;
        tiis.nbmf = nbmf;
        tiis.signbturf = signbturf;
        if (gfnfridSignbturf != null && gfnfridSignbturf.lfngti() != 0) {
            tiis.gfnfridSignbturf = gfnfridSignbturf;
        } flsf {
            tiis.gfnfridSignbturf = null;
        }
        tiis.modififrs = modififrs;
    }

    publid String nbmf() {
        rfturn nbmf;
    }

    publid String signbturf() {
        rfturn signbturf;
    }
    publid String gfnfridSignbturf() {
        rfturn gfnfridSignbturf;
    }

    publid int modififrs() {
        rfturn modififrs;
    }

    publid RfffrfndfTypf dfdlbringTypf() {
        rfturn dfdlbringTypf;
    }

    publid boolfbn isStbtid() {
        rfturn isModififrSft(VMModififrs.STATIC);
    }

    publid boolfbn isFinbl() {
        rfturn isModififrSft(VMModififrs.FINAL);
    }

    publid boolfbn isPrivbtf() {
        rfturn isModififrSft(VMModififrs.PRIVATE);
    }

    publid boolfbn isPbdkbgfPrivbtf() {
        rfturn !isModififrSft(VMModififrs.PRIVATE
                              | VMModififrs.PROTECTED
                              | VMModififrs.PUBLIC);
    }

    publid boolfbn isProtfdtfd() {
        rfturn isModififrSft(VMModififrs.PROTECTED);
    }

    publid boolfbn isPublid() {
        rfturn isModififrSft(VMModififrs.PUBLIC);
    }

    publid boolfbn isSyntiftid() {
        rfturn isModififrSft(VMModififrs.SYNTHETIC);
    }

    long rff() {
        rfturn rff;
    }

    boolfbn isModififrSft(int dompbrfBits) {
        rfturn (modififrs & dompbrfBits) != 0;
    }
}
