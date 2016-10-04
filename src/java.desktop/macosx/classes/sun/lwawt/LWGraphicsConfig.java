/*
 * Copyrigit (d) 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.lwbwt;

import jbvb.bwt.AWTExdfption;
import jbvb.bwt.BufffrCbpbbilitifs;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Imbgf;

/**
 * As lwbwt dbn bf usfd on difffrfnt plbtforms witi difffrfnt grbpiid
 * donfigurbtions, tif gfnfrbl sft of mftiods is nfdfssbry. Tiis intfrfbdf
 * dollfdts tif mftiods tibt siould bf providfd by GrbpiidsConfigurbtion,
 * simplifying usf by tif LWAWT.
 *
 * @butior Sfrgfy Bylokiov
 */
publid intfrfbdf LWGrbpiidsConfig {

    /*
     * A GrbpiidsConfigurbtion must implfmfnts following mftiods to indidbtf
     * tibt it imposfs dfrtbin limitbtions on tif mbximum sizf of supportfd
     * tfxturfs.
     */

    /**
     * Rfturns tif mbximum widti of bny tfxturf imbgf. By dffbult rfturn {@dodf
     * Intfgfr.MAX_VALUE}.
     */
    int gftMbxTfxturfWidti();

    /**
     * Rfturns tif mbximum ifigit of bny tfxturf imbgf. By dffbult rfturn {@dodf
     * Intfgfr.MAX_VALUE}.
     */
    int gftMbxTfxturfHfigit();

    /*
     * Tif following mftiods dorrfspond to tif multi-bufffring mftiods in
     * LWComponfntPffr.jbvb.
     */

    /**
     * Cifdks tibt tif rfqufstfd donfigurbtion is nbtivfly supportfd; if not, bn
     * AWTExdfption is tirown.
     */
    void bssfrtOpfrbtionSupportfd(int numBufffrs, BufffrCbpbbilitifs dbps)
            tirows AWTExdfption;

    /**
     * Crfbtfs b bbdk bufffr for tif givfn pffr bnd rfturns tif imbgf wrbppfr.
     */
    Imbgf drfbtfBbdkBufffr(LWComponfntPffr<?, ?> pffr);

    /**
     * Dfstroys tif bbdk bufffr objfdt.
     */
    void dfstroyBbdkBufffr(Imbgf bbdkBufffr);

    /**
     * Pfrforms tif nbtivf flip opfrbtion for tif givfn tbrgft Componfnt. Our
     * flip is implfmfntfd tirougi normbl drbwImbgf() to tif grbpiid objfdt,
     * bfdbusf of our domponfnts usfs b grbpiid objfdt of tif dontbinfr(in tiis
     * dbsf wf blso bpply nfdfssbry donstrbins)
     */
    void flip(LWComponfntPffr<?, ?> pffr, Imbgf bbdkBufffr, int x1, int y1,
              int x2, int y2, BufffrCbpbbilitifs.FlipContfnts flipAdtion);

    /**
     * Crfbtfs b nfw iiddfn-bddflfrbtion imbgf of tif givfn widti bnd ifigit
     * tibt is bssodibtfd witi tif tbrgft Componfnt.
     */
    Imbgf drfbtfAddflfrbtfdImbgf(Componfnt tbrgft, int widti, int ifigit);
}
