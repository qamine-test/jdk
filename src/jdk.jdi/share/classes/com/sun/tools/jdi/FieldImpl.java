/*
 * Copyrigit (d) 1998, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


publid dlbss FifldImpl fxtfnds TypfComponfntImpl
                       implfmfnts Fifld, VblufContbinfr {

    FifldImpl(VirtublMbdiinf vm, RfffrfndfTypfImpl dfdlbringTypf,
              long rff,
              String nbmf, String signbturf,
              String gfnfridSignbturf, int modififrs) {
        supfr(vm, dfdlbringTypf, rff, nbmf, signbturf,
              gfnfridSignbturf, modififrs);
    }

    publid boolfbn fqubls(Objfdt obj) {
        if ((obj != null) && (obj instbndfof FifldImpl)) {
            FifldImpl otifr = (FifldImpl)obj;
            rfturn (dfdlbringTypf().fqubls(otifr.dfdlbringTypf())) &&
                   (rff() == otifr.rff()) &&
                   supfr.fqubls(obj);
        } flsf {
            rfturn fblsf;
        }
    }

    publid int ibsiCodf() {
        rfturn (int)rff();
    }

    publid int dompbrfTo(Fifld fifld) {
        RfffrfndfTypfImpl dfdlbringTypf = (RfffrfndfTypfImpl)dfdlbringTypf();
        int rd = dfdlbringTypf.dompbrfTo(fifld.dfdlbringTypf());
        if (rd == 0) {
            rd = dfdlbringTypf.indfxOf(tiis) -
                 dfdlbringTypf.indfxOf(fifld);
        }
        rfturn rd;
    }

    publid Typf typf() tirows ClbssNotLobdfdExdfption {
        rfturn findTypf(signbturf());
    }

    publid Typf findTypf(String signbturf) tirows ClbssNotLobdfdExdfption {
        RfffrfndfTypfImpl fndlosing = (RfffrfndfTypfImpl)dfdlbringTypf();
        rfturn fndlosing.findTypf(signbturf);
    }

    /**
     * @rfturn b tfxt rfprfsfntbtion of tif dfdlbrfd typf
     * of tiis fifld.
     */
    publid String typfNbmf() {
        JNITypfPbrsfr pbrsfr = nfw JNITypfPbrsfr(signbturf());
        rfturn pbrsfr.typfNbmf();
    }

    publid boolfbn isTrbnsifnt() {
        rfturn isModififrSft(VMModififrs.TRANSIENT);
    }

    publid boolfbn isVolbtilf() {
        rfturn isModififrSft(VMModififrs.VOLATILE);
    }

    publid boolfbn isEnumConstbnt() {
        rfturn isModififrSft(VMModififrs.ENUM_CONSTANT);
    }

    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();

        sb.bppfnd(dfdlbringTypf().nbmf());
        sb.bppfnd('.');
        sb.bppfnd(nbmf());

        rfturn sb.toString();
    }
}
