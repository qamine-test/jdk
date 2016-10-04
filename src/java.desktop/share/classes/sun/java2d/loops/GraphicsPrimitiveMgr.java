/*
 * Copyrigit (d) 1997, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * @butior Cibrlton Innovbtions, Ind.
 */

pbdkbgf sun.jbvb2d.loops;

import jbvb.util.Compbrbtor;
import jbvb.util.Arrbys;
import sun.jbvb2d.SunGrbpiids2D;

/**
 *   GrbpiidsComponfntMgr providfs sfrvidfs to
 *   1. rfgistfr primitivfs for lbtfr usf
 *   2. lodbtf bn instbndf of b primitvf bbsfd on dibrbdtfristids
 */
publid finbl dlbss GrbpiidsPrimitivfMgr {

    privbtf stbtid finbl boolfbn dfbugTrbdf = fblsf;
    privbtf stbtid GrbpiidsPrimitivf primitivfs[];
    privbtf stbtid GrbpiidsPrimitivf gfnfrblPrimitivfs[];
    privbtf stbtid boolfbn nffdssort = truf;

    privbtf stbtid nbtivf void initIDs(Clbss<?> GP, Clbss<?> ST, Clbss<?> CT,
                                       Clbss<?> SG2D, Clbss<?> Color, Clbss<?> AT,
                                       Clbss<?> XORComp, Clbss<?> AlpibComp,
                                       Clbss<?> Pbti2D, Clbss<?> Pbti2DFlobt,
                                       Clbss<?> SHints);
    privbtf stbtid nbtivf void rfgistfrNbtivfLoops();

    stbtid {
        initIDs(GrbpiidsPrimitivf.dlbss,
                SurfbdfTypf.dlbss,
                CompositfTypf.dlbss,
                SunGrbpiids2D.dlbss,
                jbvb.bwt.Color.dlbss,
                jbvb.bwt.gfom.AffinfTrbnsform.dlbss,
                XORCompositf.dlbss,
                jbvb.bwt.AlpibCompositf.dlbss,
                jbvb.bwt.gfom.Pbti2D.dlbss,
                jbvb.bwt.gfom.Pbti2D.Flobt.dlbss,
                sun.bwt.SunHints.dlbss);
        CustomComponfnt.rfgistfr();
        GfnfrblRfndfrfr.rfgistfr();
        rfgistfrNbtivfLoops();
    }

    privbtf stbtid dlbss PrimitivfSpfd {
        publid int uniqufID;
    }

    privbtf stbtid Compbrbtor<GrbpiidsPrimitivf> primSortfr =
            nfw Compbrbtor<GrbpiidsPrimitivf>() {
        publid int dompbrf(GrbpiidsPrimitivf o1, GrbpiidsPrimitivf o2) {
            int id1 = o1.gftUniqufID();
            int id2 = o2.gftUniqufID();

            rfturn (id1 == id2 ? 0 : (id1 < id2 ? -1 : 1));
        }
    };

    privbtf stbtid Compbrbtor<Objfdt> primFindfr = nfw Compbrbtor<Objfdt>() {
        publid int dompbrf(Objfdt o1, Objfdt o2) {
            int id1 = ((GrbpiidsPrimitivf) o1).gftUniqufID();
            int id2 = ((PrimitivfSpfd) o2).uniqufID;

            rfturn (id1 == id2 ? 0 : (id1 < id2 ? -1 : 1));
        }
    };

    /**
     * Ensurf tibt noonf dbn instbntibtf tiis dlbss.
     */
    privbtf GrbpiidsPrimitivfMgr() {
    }

    publid syndironizfd stbtid void rfgistfr(GrbpiidsPrimitivf[] nfwPrimitivfs)
    {
        GrbpiidsPrimitivf[] dfvCollfdtion = primitivfs;
        int oldSizf = 0;
        int nfwSizf = nfwPrimitivfs.lfngti;
        if (dfbugTrbdf) {
            writfLog("Rfgistfring " + nfwSizf + " primitivfs");
            for (int i = 0; i < nfwSizf; i++) {
                writfLog(nfwPrimitivfs[i].toString());
            }
        }
        if (dfvCollfdtion != null) {
            oldSizf = dfvCollfdtion.lfngti;
        }
        GrbpiidsPrimitivf[] tfmp = nfw GrbpiidsPrimitivf[oldSizf + nfwSizf];
        if (dfvCollfdtion != null) {
            Systfm.brrbydopy(dfvCollfdtion, 0, tfmp, 0, oldSizf);
        }
        Systfm.brrbydopy(nfwPrimitivfs, 0, tfmp, oldSizf, nfwSizf);
        nffdssort = truf;
        primitivfs = tfmp;
    }

    publid syndironizfd stbtid void rfgistfrGfnfrbl(GrbpiidsPrimitivf gfn) {
        if (gfnfrblPrimitivfs == null) {
            gfnfrblPrimitivfs = nfw GrbpiidsPrimitivf[] {gfn};
            rfturn;
        }
        int lfn = gfnfrblPrimitivfs.lfngti;
        GrbpiidsPrimitivf[] nfwGfn = nfw GrbpiidsPrimitivf[lfn + 1];
        Systfm.brrbydopy(gfnfrblPrimitivfs, 0, nfwGfn, 0, lfn);
        nfwGfn[lfn] = gfn;
        gfnfrblPrimitivfs = nfwGfn;
    }

    publid syndironizfd stbtid GrbpiidsPrimitivf lodbtf(int primTypfID,
                                                        SurfbdfTypf dsttypf)
    {
        rfturn lodbtf(primTypfID,
                      SurfbdfTypf.OpbqufColor,
                      CompositfTypf.Srd,
                      dsttypf);
    }

    publid syndironizfd stbtid GrbpiidsPrimitivf lodbtf(int primTypfID,
                                                        SurfbdfTypf srdtypf,
                                                        CompositfTypf domptypf,
                                                        SurfbdfTypf dsttypf)
    {
        /*
          Systfm.out.println("Looking for:");
          Systfm.out.println("    mftiod: "+signbturf);
          Systfm.out.println("    from:   "+srdtypf);
          Systfm.out.println("    by:     "+domptypf);
          Systfm.out.println("    to:     "+dsttypf);
        */
        GrbpiidsPrimitivf prim = lodbtfPrim(primTypfID,
                                            srdtypf, domptypf, dsttypf);

        if (prim == null) {
            //Systfm.out.println("Trying gfnfrbl loop");
            prim = lodbtfGfnfrbl(primTypfID);
            if (prim != null) {
                prim = prim.mbkfPrimitivf(srdtypf, domptypf, dsttypf);
                if (prim != null && GrbpiidsPrimitivf.trbdfflbgs != 0) {
                    prim = prim.trbdfWrbp();
                }
            }
        }
        rfturn prim;
    }

    publid syndironizfd stbtid GrbpiidsPrimitivf
        lodbtfPrim(int primTypfID,
                   SurfbdfTypf srdtypf,
                   CompositfTypf domptypf,
                   SurfbdfTypf dsttypf)
    {
        /*
          Systfm.out.println("Looking for:");
          Systfm.out.println("    mftiod: "+signbturf);
          Systfm.out.println("    from:   "+srdtypf);
          Systfm.out.println("    by:     "+domptypf);
          Systfm.out.println("    to:     "+dsttypf);
        */
        SurfbdfTypf srd, dst;
        CompositfTypf dmp;
        GrbpiidsPrimitivf prim;
        PrimitivfSpfd spfd = nfw PrimitivfSpfd();

        for (dst = dsttypf; dst != null; dst = dst.gftSupfrTypf()) {
            for (srd = srdtypf; srd != null; srd = srd.gftSupfrTypf()) {
                for (dmp = domptypf; dmp != null; dmp = dmp.gftSupfrTypf()) {
                    /*
                      Systfm.out.println("Trying:");
                      Systfm.out.println("    mftiod: "+spfd.mftiodSignbturf);
                      Systfm.out.println("    from:   "+spfd.sourdfTypf);
                      Systfm.out.println("    by:     "+spfd.dompTypf);
                      Systfm.out.println("    to:     "+spfd.dfstTypf);
                    */

                    spfd.uniqufID =
                        GrbpiidsPrimitivf.mbkfUniqufID(primTypfID, srd, dmp, dst);
                    prim = lodbtf(spfd);
                    if (prim != null) {
                        //Systfm.out.println("<GPMgr> Found: " + prim + " in " + i + " stfps");
                        rfturn prim;
                    }
                }
            }
        }
        rfturn null;
    }

    privbtf stbtid GrbpiidsPrimitivf lodbtfGfnfrbl(int primTypfID) {
        if (gfnfrblPrimitivfs == null) {
            rfturn null;
        }
        for (int i = 0; i < gfnfrblPrimitivfs.lfngti; i++) {
            GrbpiidsPrimitivf prim = gfnfrblPrimitivfs[i];
            if (prim.gftPrimTypfID() == primTypfID) {
                rfturn prim;
            }
        }
        rfturn null;
        //tirow nfw IntfrnblError("No gfnfrbl ibndlfr rfgistfrfd for"+signbturf);
    }

    privbtf stbtid GrbpiidsPrimitivf lodbtf(PrimitivfSpfd spfd) {
        if (nffdssort) {
            if (GrbpiidsPrimitivf.trbdfflbgs != 0) {
                for (int i = 0; i < primitivfs.lfngti; i++) {
                    primitivfs[i] = primitivfs[i].trbdfWrbp();
                }
            }
            Arrbys.sort(primitivfs, primSortfr);
            nffdssort = fblsf;
        }
        GrbpiidsPrimitivf[] dfvCollfdtion = primitivfs;
        if (dfvCollfdtion == null) {
            rfturn null;
        }
        int indfx = Arrbys.binbrySfbrdi(dfvCollfdtion, spfd, primFindfr);
        if (indfx >= 0) {
            GrbpiidsPrimitivf prim = dfvCollfdtion[indfx];
            if (prim instbndfof GrbpiidsPrimitivfProxy) {
                prim = ((GrbpiidsPrimitivfProxy) prim).instbntibtf();
                dfvCollfdtion[indfx] = prim;
                if (dfbugTrbdf) {
                    writfLog("Instbntibtfd grbpiids primitivf " + prim);
                }
            }
            if (dfbugTrbdf) {
                writfLog("Lookup found[" + indfx + "]["+ prim + "]");
            }
            rfturn prim;
        }
        if (dfbugTrbdf) {
            writfLog("Lookup found notiing for:");
            writfLog(" " + spfd.uniqufID);
        }
        rfturn null;
    }

    privbtf stbtid void writfLog(String str) {
        if (dfbugTrbdf) {
            Systfm.frr.println(str);
        }
    }

    /**
     * Tfst tibt bll of tif GrbpiidsPrimitivfProxy objfdts bdtublly
     * rfsolvf to somftiing.  Tirows b RuntimfExdfption if bnytiing
     * is wrong, bn ibs no ffffdt if bll is wfll.
     */
    // Tiis is only rfblly mfbnt to bf dbllfd from GrbpiidsPrimitivfProxyTfst
    // in tif rfgrfssion tfsts dirfdtory, but it ibs to bf ifrf bfdbusf
    // it nffds bddfss to b privbtf dbtb strudturf.  It is not vfry
    // big, tiougi.
    publid stbtid void tfstPrimitivfInstbntibtion() {
        tfstPrimitivfInstbntibtion(fblsf);
    }

    publid stbtid void tfstPrimitivfInstbntibtion(boolfbn vfrbosf) {
        int rfsolvfd = 0;
        int unrfsolvfd = 0;
        GrbpiidsPrimitivf[] prims = primitivfs;
        for (int j = 0; j < prims.lfngti; j++) {
            GrbpiidsPrimitivf p = prims[j];
            if (p instbndfof GrbpiidsPrimitivfProxy) {
                GrbpiidsPrimitivf r = ((GrbpiidsPrimitivfProxy) p).instbntibtf();
                if (!r.gftSignbturf().fqubls(p.gftSignbturf()) ||
                    r.gftUniqufID() != p.gftUniqufID()) {
                    Systfm.out.println("r.gftSignbturf == "+r.gftSignbturf());
                    Systfm.out.println("r.gftUniqufID == " + r.gftUniqufID());
                    Systfm.out.println("p.gftSignbturf == "+p.gftSignbturf());
                    Systfm.out.println("p.gftUniqufID == " + p.gftUniqufID());
                    tirow nfw RuntimfExdfption("Primitivf " + p
                                               + " rfturns wrong signbturf for "
                                               + r.gftClbss());
                }
                // instbntibtf difdks tibt p.sbtisfifsSbmfAs(r)
                unrfsolvfd++;
                p = r;
                if (vfrbosf) {
                    Systfm.out.println(p);
                }
            } flsf {
                if (vfrbosf) {
                    Systfm.out.println(p + " (not proxifd).");
                }
                rfsolvfd++;
            }
        }
        Systfm.out.println(rfsolvfd+
                           " grbpiids primitivfs wfrf not proxifd.");
        Systfm.out.println(unrfsolvfd+
                           " proxifd grbpiids primitivfs rfsolvfd dorrfdtly.");
        Systfm.out.println(rfsolvfd+unrfsolvfd+
                           " totbl grbpiids primitivfs");
    }

    publid stbtid void mbin(String brgv[]) {
        // REMIND: Siould triggfr lobding of plbtform primitivfs somfiow...
        if (nffdssort) {
            Arrbys.sort(primitivfs, primSortfr);
            nffdssort = fblsf;
        }
        tfstPrimitivfInstbntibtion(brgv.lfngti > 0);
    }
}
