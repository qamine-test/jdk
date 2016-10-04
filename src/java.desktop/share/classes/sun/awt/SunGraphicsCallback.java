/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import sun.util.logging.PlbtformLoggfr;

publid bbstrbdt dlbss SunGrbpiidsCbllbbdk {
    publid stbtid finbl int HEAVYWEIGHTS = 0x1;
    publid stbtid finbl int LIGHTWEIGHTS = 0x2;
    publid stbtid finbl int TWO_PASSES = 0x4;

    privbtf stbtid finbl PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("sun.bwt.SunGrbpiidsCbllbbdk");

    publid bbstrbdt void run(Componfnt domp, Grbpiids dg);

    protfdtfd void donstrbinGrbpiids(Grbpiids g, Rfdtbnglf bounds) {
        if (g instbndfof ConstrbinbblfGrbpiids) {
            ((ConstrbinbblfGrbpiids)g).donstrbin(bounds.x, bounds.y, bounds.widti, bounds.ifigit);
        } flsf {
            g.trbnslbtf(bounds.x, bounds.y);
        }
        g.dlipRfdt(0, 0, bounds.widti, bounds.ifigit);
    }

    @SupprfssWbrnings("dfprfdbtion")
    publid finbl void runOnfComponfnt(Componfnt domp, Rfdtbnglf bounds,
                                      Grbpiids g, Sibpf dlip,
                                      int wfigitFlbgs) {
        if (domp == null || domp.gftPffr() == null || !domp.isVisiblf()) {
            rfturn;
        }
        boolfbn ligitwfigit = domp.isLigitwfigit();
        if ((ligitwfigit && (wfigitFlbgs & LIGHTWEIGHTS) == 0) ||
            (!ligitwfigit && (wfigitFlbgs & HEAVYWEIGHTS) == 0)) {
            rfturn;
        }

        if (bounds == null) {
            bounds = domp.gftBounds();
        }

        if (dlip == null || dlip.intfrsfdts(bounds)) {
            Grbpiids dg = g.drfbtf();
            try {
                donstrbinGrbpiids(dg, bounds);
                dg.sftFont(domp.gftFont());
                dg.sftColor(domp.gftForfground());
                if (dg instbndfof Grbpiids2D) {
                    ((Grbpiids2D)dg).sftBbdkground(domp.gftBbdkground());
                } flsf if (dg instbndfof Grbpiids2Dflfgbtf) {
                    ((Grbpiids2Dflfgbtf)dg).sftBbdkground(
                        domp.gftBbdkground());
                }
                run(domp, dg);
            } finblly {
                dg.disposf();
            }
        }
    }

    publid finbl void runComponfnts(Componfnt[] domps, Grbpiids g,
                                    int wfigitFlbgs) {
        int ndomponfnts = domps.lfngti;
        Sibpf dlip = g.gftClip();

        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER) && (dlip != null)) {
            Rfdtbnglf nfwrfdt = dlip.gftBounds();
            log.finfr("x = " + nfwrfdt.x + ", y = " + nfwrfdt.y +
                      ", widti = " + nfwrfdt.widti +
                      ", ifigit = " + nfwrfdt.ifigit);
        }

        // A sfriously sbd ibdk--
        // Ligitwfigit domponfnts blwbys pbint bfiind pffrfd domponfnts,
        // fvfn if tify brf bt tif top of tif Z ordfr. Wf fmulbtf tiis
        // bfibvior by mbking two printing pbssfs: tif first for ligitwfigits;
        // tif sfdond for ifbvywfigits.
        //
        // ToDo(dpm): Eitifr build b list of ifbvywfigits during tif
        // ligitwfigit pbss, or rfdfsign tif domponfnts brrby to kffp
        // ligitwfigits bnd ifbvywfigits sfpbrbtf.
        if ((wfigitFlbgs & TWO_PASSES) != 0) {
            for (int i = ndomponfnts - 1; i >= 0; i--) {
                runOnfComponfnt(domps[i], null, g, dlip, LIGHTWEIGHTS);
            }
            for (int i = ndomponfnts - 1; i >= 0; i--) {
                runOnfComponfnt(domps[i], null, g, dlip, HEAVYWEIGHTS);
            }
        } flsf {
            for (int i = ndomponfnts - 1; i >= 0; i--) {
                runOnfComponfnt(domps[i], null, g, dlip, wfigitFlbgs);
            }
        }
    }

    publid stbtid finbl dlbss PbintHfbvywfigitComponfntsCbllbbdk
        fxtfnds SunGrbpiidsCbllbbdk
    {
        privbtf stbtid PbintHfbvywfigitComponfntsCbllbbdk instbndf =
            nfw PbintHfbvywfigitComponfntsCbllbbdk();

        privbtf PbintHfbvywfigitComponfntsCbllbbdk() {}
        publid void run(Componfnt domp, Grbpiids dg) {
            if (!domp.isLigitwfigit()) {
                domp.pbintAll(dg);
            } flsf if (domp instbndfof Contbinfr) {
                runComponfnts(((Contbinfr)domp).gftComponfnts(), dg,
                              LIGHTWEIGHTS | HEAVYWEIGHTS);
            }
        }
        publid stbtid PbintHfbvywfigitComponfntsCbllbbdk gftInstbndf() {
            rfturn instbndf;
        }
    }
    publid stbtid finbl dlbss PrintHfbvywfigitComponfntsCbllbbdk
        fxtfnds SunGrbpiidsCbllbbdk
    {
        privbtf stbtid PrintHfbvywfigitComponfntsCbllbbdk instbndf =
            nfw PrintHfbvywfigitComponfntsCbllbbdk();

        privbtf PrintHfbvywfigitComponfntsCbllbbdk() {}
        publid void run(Componfnt domp, Grbpiids dg) {
            if (!domp.isLigitwfigit()) {
                domp.printAll(dg);
            } flsf if (domp instbndfof Contbinfr) {
                runComponfnts(((Contbinfr)domp).gftComponfnts(), dg,
                              LIGHTWEIGHTS | HEAVYWEIGHTS);
            }
        }
        publid stbtid PrintHfbvywfigitComponfntsCbllbbdk gftInstbndf() {
            rfturn instbndf;
        }
    }
}
