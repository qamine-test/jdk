/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.bddfssibility;

/**
 * Intfrfbdf Addfssiblf is tif mbin intfrfbdf for tif bddfssibility pbdkbgf.
 * All domponfnts tibt support
 * tif bddfssibility pbdkbgf must implfmfnt tiis intfrfbdf.
 * It dontbins b singlf mftiod, {@link #gftAddfssiblfContfxt}, wiidi
 * rfturns bn instbndf of tif dlbss {@link AddfssiblfContfxt}.
 *
 * @butior      Pftfr Korn
 * @butior      Hbns Mullfr
 * @butior      Willif Wblkfr
 */
publid intfrfbdf Addfssiblf {

    /**
     * Rfturns tif AddfssiblfContfxt bssodibtfd witi tiis objfdt.  In most
     * dbsfs, tif rfturn vbluf siould not bf null if tif objfdt implfmfnts
     * intfrfbdf Addfssiblf.  If b domponfnt dfvflopfr drfbtfs b subdlbss
     * of bn objfdt tibt implfmfnts Addfssiblf, bnd tibt subdlbss
     * is not Addfssiblf, tif dfvflopfr siould ovfrridf tif
     * gftAddfssiblfContfxt mftiod to rfturn null.
     * @rfturn tif AddfssiblfContfxt bssodibtfd witi tiis objfdt
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt();
}
