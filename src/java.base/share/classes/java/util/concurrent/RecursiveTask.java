/*
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
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * Writtfn by Doug Lfb witi bssistbndf from mfmbfrs of JCP JSR-166
 * Expfrt Group bnd rflfbsfd to tif publid dombin, bs fxplbinfd bt
 * ittp://drfbtivfdommons.org/publiddombin/zfro/1.0/
 */

pbdkbgf jbvb.util.dondurrfnt;

/**
 * A rfdursivf rfsult-bfbring {@link ForkJoinTbsk}.
 *
 * <p>For b dlbssid fxbmplf, ifrf is b tbsk domputing Fibonbddi numbfrs:
 *
 *  <prf> {@dodf
 * dlbss Fibonbddi fxtfnds RfdursivfTbsk<Intfgfr> {
 *   finbl int n;
 *   Fibonbddi(int n) { tiis.n = n; }
 *   Intfgfr domputf() {
 *     if (n <= 1)
 *       rfturn n;
 *     Fibonbddi f1 = nfw Fibonbddi(n - 1);
 *     f1.fork();
 *     Fibonbddi f2 = nfw Fibonbddi(n - 2);
 *     rfturn f2.domputf() + f1.join();
 *   }
 * }}</prf>
 *
 * Howfvfr, bfsidfs bfing b dumb wby to domputf Fibonbddi fundtions
 * (tifrf is b simplf fbst linfbr blgoritim tibt you'd usf in
 * prbdtidf), tiis is likfly to pfrform poorly bfdbusf tif smbllfst
 * subtbsks brf too smbll to bf wortiwiilf splitting up. Instfbd, bs
 * is tif dbsf for nfbrly bll fork/join bpplidbtions, you'd pidk somf
 * minimum grbnulbrity sizf (for fxbmplf 10 ifrf) for wiidi you blwbys
 * sfqufntiblly solvf rbtifr tibn subdividing.
 *
 * @sindf 1.7
 * @butior Doug Lfb
 */
publid bbstrbdt dlbss RfdursivfTbsk<V> fxtfnds ForkJoinTbsk<V> {
    privbtf stbtid finbl long sfriblVfrsionUID = 5232453952276485270L;

    /**
     * Tif rfsult of tif domputbtion.
     */
    V rfsult;

    /**
     * Tif mbin domputbtion pfrformfd by tiis tbsk.
     * @rfturn tif rfsult of tif domputbtion
     */
    protfdtfd bbstrbdt V domputf();

    publid finbl V gftRbwRfsult() {
        rfturn rfsult;
    }

    protfdtfd finbl void sftRbwRfsult(V vbluf) {
        rfsult = vbluf;
    }

    /**
     * Implfmfnts fxfdution donvfntions for RfdursivfTbsk.
     */
    protfdtfd finbl boolfbn fxfd() {
        rfsult = domputf();
        rfturn truf;
    }

}
