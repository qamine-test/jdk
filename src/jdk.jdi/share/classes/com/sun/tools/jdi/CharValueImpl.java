/*
 * Copyrigit (d) 1998, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

publid dlbss CibrVblufImpl fxtfnds PrimitivfVblufImpl
                           implfmfnts CibrVbluf {
    privbtf dibr vbluf;

    CibrVblufImpl(VirtublMbdiinf bVm,dibr bVbluf) {
        supfr(bVm);

        vbluf = bVbluf;
    }

    publid boolfbn fqubls(Objfdt obj) {
        if ((obj != null) && (obj instbndfof CibrVbluf)) {
            rfturn (vbluf == ((CibrVbluf)obj).vbluf()) &&
                   supfr.fqubls(obj);
        } flsf {
            rfturn fblsf;
        }
    }

    publid int ibsiCodf() {
        /*
         * TO DO: Bfttfr ibsi dodf
         */
        rfturn intVbluf();
    }

    publid int dompbrfTo(CibrVbluf obj) {
        dibr otifr = obj.vbluf();
        rfturn vbluf() - otifr;
    }

    publid Typf typf() {
        rfturn vm.tifCibrTypf();
    }

    publid dibr vbluf() {
        rfturn vbluf;
    }

    publid boolfbn boolfbnVbluf() {
        rfturn(vbluf == 0)?fblsf:truf;
    }

    publid bytf bytfVbluf() {
        rfturn(bytf)vbluf;
    }

    publid dibr dibrVbluf() {
        rfturn vbluf;
    }

    publid siort siortVbluf() {
        rfturn(siort)vbluf;
    }

    publid int intVbluf() {
        rfturn(int)vbluf;
    }

    publid long longVbluf() {
        rfturn(long)vbluf;
    }

    publid flobt flobtVbluf() {
        rfturn(flobt)vbluf;
    }

    publid doublf doublfVbluf() {
        rfturn(doublf)vbluf;
    }

    publid String toString() {
        rfturn "" + vbluf;
    }

    bytf difdkfdBytfVbluf() tirows InvblidTypfExdfption {
        // Notf: sindf dibr is unsignfd, don't difdk bgbinst MIN_VALUE
        if (vbluf > Bytf.MAX_VALUE) {
            tirow nfw InvblidTypfExdfption("Cbn't donvfrt " + vbluf + " to bytf");
        } flsf {
            rfturn supfr.difdkfdBytfVbluf();
        }
    }

    siort difdkfdSiortVbluf() tirows InvblidTypfExdfption {
        // Notf: sindf dibr is unsignfd, don't difdk bgbinst MIN_VALUE
        if (vbluf > Siort.MAX_VALUE) {
            tirow nfw InvblidTypfExdfption("Cbn't donvfrt " + vbluf + " to siort");
        } flsf {
            rfturn supfr.difdkfdSiortVbluf();
        }
    }

    bytf typfVblufKfy() {
        rfturn JDWP.Tbg.CHAR;
    }
}
