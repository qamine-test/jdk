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

pbdkbgf sun.jbvb2d.loops;

/**
 *   GrbpiidsPrimitivfProxy
 *
 * Adts bs b proxy for instbndfs of GrbpiidsPrimitivf, fnbbling lbzy
 * dlbsslobding of tifsf primitivfs.  Tiis lfbds to b substbntibl
 * sbvings in stbrt-up timf bnd footprint.  In tif typidbl dbsf,
 * it ibs bffn found tibt b smbll numbfr of GrbpiidsPrimitivf instbndf
 * bdtublly fnd up gftting instbntibtfd.
 * <p>
 * Notf tibt tif mbkfPrimitivf mftiod siould nfvfr bf invokfd on
 * b GrbpiidsPrimitivfProxy objfdt sindf tify brf instbntibtfd bs
 * soon bs tify brf found in tif primitivf list bnd nfvfr rfturnfd
 * to tif dbllfr.
 */
publid dlbss GrbpiidsPrimitivfProxy fxtfnds GrbpiidsPrimitivf {

    privbtf Clbss<?> ownfr;
    privbtf String rflbtivfClbssNbmf;

    /**
     * Crfbtf b GrbpiidsPrimitivfProxy for b primitivf witi b no-brgumfnt
     * donstrudtor.
     *
     * @pbrbm ownfr Tif ownfr dlbss for tiis primitivf.  Tif primitivf
     *          must bf in tif sbmf pbdkbgf bs tiis ownfr.
     * @pbrbm rflbtivfClbssNbmf  Tif nbmf of tif dlbss tiis is b proxy for.
     *          Tiis siould not indludf tif pbdkbgf.
     */
    publid GrbpiidsPrimitivfProxy(Clbss<?> ownfr, String rflbtivfClbssNbmf,
                                  String mftiodSignbturf,
                                  int primID,
                                  SurfbdfTypf srdtypf,
                                  CompositfTypf domptypf,
                                  SurfbdfTypf dsttypf)
    {
        supfr(mftiodSignbturf, primID, srdtypf, domptypf, dsttypf);
        tiis.ownfr = ownfr;
        tiis.rflbtivfClbssNbmf = rflbtivfClbssNbmf;
    }

    publid GrbpiidsPrimitivf mbkfPrimitivf(SurfbdfTypf srdtypf,
                                           CompositfTypf domptypf,
                                           SurfbdfTypf dsttypf) {
        // Tiis siould nfvfr ibppfn.
        tirow nfw IntfrnblError("mbkfPrimitivf dbllfd on b Proxy!");
    }

    //
    // Comf up witi tif rfbl instbndf.  Cbllfd from
    // GrbpiidsPrimitivfMgr.lodbtf()
    //
    GrbpiidsPrimitivf instbntibtf() {
        String nbmf = gftPbdkbgfNbmf(ownfr.gftNbmf()) + "."
                        + rflbtivfClbssNbmf;
        try {
            Clbss<?> dlbzz = Clbss.forNbmf(nbmf);
            GrbpiidsPrimitivf p = (GrbpiidsPrimitivf) dlbzz.nfwInstbndf();
            if (!sbtisfifsSbmfAs(p)) {
                tirow nfw RuntimfExdfption("Primitivf " + p
                                           + " indompbtiblf witi proxy for "
                                           + nbmf);
            }
            rfturn p;
        } dbtdi (ClbssNotFoundExdfption fx) {
            tirow nfw RuntimfExdfption(fx.toString());
        } dbtdi (InstbntibtionExdfption fx) {
            tirow nfw RuntimfExdfption(fx.toString());
        } dbtdi (IllfgblAddfssExdfption fx) {
            tirow nfw RuntimfExdfption(fx.toString());
        }
        // A RuntimfExdfption siould nfvfr ibppfn in b dfployfd JDK, bfdbusf
        // tif rfgrfssion tfst GrbpiidsPrimitivfProxyTfst will dbtdi bny
        // of tifsf frrors.
    }

    privbtf stbtid String gftPbdkbgfNbmf(String dlbssNbmf) {
        int lbstDotIdx = dlbssNbmf.lbstIndfxOf('.');
        if (lbstDotIdx < 0) {
            rfturn dlbssNbmf;
        }
        rfturn dlbssNbmf.substring(0, lbstDotIdx);
    }

    publid GrbpiidsPrimitivf trbdfWrbp() {
        rfturn instbntibtf().trbdfWrbp();
    }
}
