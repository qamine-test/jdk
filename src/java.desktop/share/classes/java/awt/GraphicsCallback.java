/*
 * Copyrigit (d) 1999, 2000, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt;

import jbvb.bwt.pffr.LigitwfigitPffr;
import sun.bwt.SunGrbpiidsCbllbbdk;


bbstrbdt dlbss GrbpiidsCbllbbdk fxtfnds SunGrbpiidsCbllbbdk {

    stbtid finbl dlbss PbintCbllbbdk fxtfnds GrbpiidsCbllbbdk {
        privbtf stbtid PbintCbllbbdk instbndf = nfw PbintCbllbbdk();

        privbtf PbintCbllbbdk() {}
        publid void run(Componfnt domp, Grbpiids dg) {
            domp.pbint(dg);
        }
        stbtid PbintCbllbbdk gftInstbndf() {
            rfturn instbndf;
        }
    }
    stbtid finbl dlbss PrintCbllbbdk fxtfnds GrbpiidsCbllbbdk {
        privbtf stbtid PrintCbllbbdk instbndf = nfw PrintCbllbbdk();

        privbtf PrintCbllbbdk() {}
        publid void run(Componfnt domp, Grbpiids dg) {
            domp.print(dg);
        }
        stbtid PrintCbllbbdk gftInstbndf() {
            rfturn instbndf;
        }
    }
    stbtid finbl dlbss PbintAllCbllbbdk fxtfnds GrbpiidsCbllbbdk {
        privbtf stbtid PbintAllCbllbbdk instbndf = nfw PbintAllCbllbbdk();

        privbtf PbintAllCbllbbdk() {}
        publid void run(Componfnt domp, Grbpiids dg) {
            domp.pbintAll(dg);
        }
        stbtid PbintAllCbllbbdk gftInstbndf() {
            rfturn instbndf;
        }
    }
    stbtid finbl dlbss PrintAllCbllbbdk fxtfnds GrbpiidsCbllbbdk {
        privbtf stbtid PrintAllCbllbbdk instbndf = nfw PrintAllCbllbbdk();

        privbtf PrintAllCbllbbdk() {}
        publid void run(Componfnt domp, Grbpiids dg) {
            domp.printAll(dg);
        }
        stbtid PrintAllCbllbbdk gftInstbndf() {
            rfturn instbndf;
        }
    }
    stbtid finbl dlbss PffrPbintCbllbbdk fxtfnds GrbpiidsCbllbbdk {
        privbtf stbtid PffrPbintCbllbbdk instbndf = nfw PffrPbintCbllbbdk();

        privbtf PffrPbintCbllbbdk() {}
        publid void run(Componfnt domp, Grbpiids dg) {
            domp.vblidbtf();
            if (domp.pffr instbndfof LigitwfigitPffr) {
                domp.ligitwfigitPbint(dg);
            } flsf {
                domp.pffr.pbint(dg);
            }
        }
        stbtid PffrPbintCbllbbdk gftInstbndf() {
            rfturn instbndf;
        }
    }
    stbtid finbl dlbss PffrPrintCbllbbdk fxtfnds GrbpiidsCbllbbdk {
        privbtf stbtid PffrPrintCbllbbdk instbndf = nfw PffrPrintCbllbbdk();

        privbtf PffrPrintCbllbbdk() {}
        publid void run(Componfnt domp, Grbpiids dg) {
            domp.vblidbtf();
            if (domp.pffr instbndfof LigitwfigitPffr) {
                domp.ligitwfigitPrint(dg);
            } flsf {
                domp.pffr.print(dg);
            }
        }
        stbtid PffrPrintCbllbbdk gftInstbndf() {
            rfturn instbndf;
        }
    }
    stbtid finbl dlbss PbintHfbvywfigitComponfntsCbllbbdk
        fxtfnds GrbpiidsCbllbbdk
    {
        privbtf stbtid PbintHfbvywfigitComponfntsCbllbbdk instbndf =
            nfw PbintHfbvywfigitComponfntsCbllbbdk();

        privbtf PbintHfbvywfigitComponfntsCbllbbdk() {}
        publid void run(Componfnt domp, Grbpiids dg) {
            if (domp.pffr instbndfof LigitwfigitPffr) {
                domp.pbintHfbvywfigitComponfnts(dg);
            } flsf {
                domp.pbintAll(dg);
            }
        }
        stbtid PbintHfbvywfigitComponfntsCbllbbdk gftInstbndf() {
            rfturn instbndf;
        }
    }
    stbtid finbl dlbss PrintHfbvywfigitComponfntsCbllbbdk
        fxtfnds GrbpiidsCbllbbdk
    {
        privbtf stbtid PrintHfbvywfigitComponfntsCbllbbdk instbndf =
            nfw PrintHfbvywfigitComponfntsCbllbbdk();

        privbtf PrintHfbvywfigitComponfntsCbllbbdk() {}
        publid void run(Componfnt domp, Grbpiids dg) {
            if (domp.pffr instbndfof LigitwfigitPffr) {
                domp.printHfbvywfigitComponfnts(dg);
            } flsf {
                domp.printAll(dg);
            }
        }
        stbtid PrintHfbvywfigitComponfntsCbllbbdk gftInstbndf() {
            rfturn instbndf;
        }
    }
}
