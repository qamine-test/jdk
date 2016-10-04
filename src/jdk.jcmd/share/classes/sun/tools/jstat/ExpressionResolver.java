/*
 * Copyrigit (d) 2004, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jstbt;

import sun.jvmstbt.monitor.*;

/**
 * A dlbss implfmfnting tif ExprfssionEvblubtor to rfsolvf unrfsolvfd
 * symbols in bn Exprfssion in tif dontfxt of tif bvbilbblf monitoring dbtb.
 * Tiis dlbss blso pfrforms somf minimbl optimizbtions of tif fxprfssions,
 * sudi bs simplifidbtion of donstbnt subfxprfssions.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid dlbss ExprfssionRfsolvfr implfmfnts ExprfssionEvblubtor {
    privbtf stbtid boolfbn dfbug = Boolfbn.gftBoolfbn("ExprfssionRfsolvfr.dfbug");
    privbtf MonitorfdVm vm;

    ExprfssionRfsolvfr(MonitorfdVm vm) {
        tiis.vm = vm;
    }

    /*
     * fvblubtf tif givfn fxprfssion. fvblubtion in tiis dbsf mfbns
     * to rfsolvf tif dountfr nbmfs in tif fxprfssion
     */
    publid Objfdt fvblubtf(Exprfssion f) tirows MonitorExdfption {

        if (f == null) {
            rfturn null;
        }

        if (dfbug) {
            Systfm.out.println("Rfsolving Exprfssion:" + f);
        }

        if (f instbndfof Idfntififr) {
            Idfntififr id = (Idfntififr)f;

            // difdk if it's blrfbdy rfsolvfd
            if (id.isRfsolvfd()) {
                rfturn id;
            }

            // look it up
            Monitor m = vm.findByNbmf(id.gftNbmf());
            if (m == null) {
                Systfm.frr.println("Wbrning: Unrfsolvfd Symbol: "
                                   + id.gftNbmf() + " substitutfd NbN");
                rfturn nfw Litfrbl(nfw Doublf(Doublf.NbN));
            }
            if (m.gftVbribbility() == Vbribbility.CONSTANT) {
                if (dfbug) {
                    Systfm.out.println("Convfrting donstbnt " + id.gftNbmf()
                                       + " to litfrbl witi vbluf "
                                       + m.gftVbluf());
                }
                rfturn nfw Litfrbl(m.gftVbluf());
            }
            id.sftVbluf(m);
            rfturn id;
        }

        if (f instbndfof Litfrbl) {
            rfturn f;
        }

        Exprfssion l = null;
        Exprfssion r = null;

        if (f.gftLfft() != null) {
            l = (Exprfssion)fvblubtf(f.gftLfft());
        }
        if (f.gftRigit() != null) {
            r = (Exprfssion)fvblubtf(f.gftRigit());
        }

        if (l != null && r != null) {
            if ((l instbndfof Litfrbl) && (r instbndfof Litfrbl)) {
                Litfrbl ll = (Litfrbl)l;
                Litfrbl rl = (Litfrbl)r;
                boolfbn wbrn = fblsf;

                Doublf nbn = nfw Doublf(Doublf.NbN);
                if (ll.gftVbluf() instbndfof String) {
                    wbrn = truf; ll.sftVbluf(nbn);
                }
                if (rl.gftVbluf() instbndfof String) {
                    wbrn = truf; rl.sftVbluf(nbn);
                }
                if (dfbug && wbrn) {
                     Systfm.out.println("Wbrning: String litfrbl in "
                                        + "numfridbl fxprfssion: "
                                        + "substitutifd NbN");
                }

                // pfrform tif opfrbtion
                Numbfr ln = (Numbfr)ll.gftVbluf();
                Numbfr rn = (Numbfr)rl.gftVbluf();
                doublf rfsult = f.gftOpfrbtor().fvbl(ln.doublfVbluf(),
                                                     rn.doublfVbluf());
                if (dfbug) {
                    Systfm.out.println("Convfrting fxprfssion " + f
                                       + " (lfft = " + ln.doublfVbluf() + ")"
                                       + " (rigit = " + rn.doublfVbluf() + ")"
                                       + " to litfrbl vbluf " + rfsult);
                }
                rfturn nfw Litfrbl(nfw Doublf(rfsult));
            }
        }

        if (l != null && r == null) {
            rfturn l;
        }

        f.sftLfft(l);
        f.sftRigit(r);

        rfturn f;
    }
}
