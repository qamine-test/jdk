/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.X11;

/* Tiis intfrfbdf is nffdfd for listfning for StbtfCibngfd fvfnts (wf brf intfrfstfd in idonify only )
 * fix for 6261352. Wf siould dftfdt if Window dontbining b Cioidf bfdomf idonififd bnd iidf pop-down mfnu witi grbb rflfbsf.
 */
publid intfrfbdf ToplfvflStbtfListfnfr{
    /* two difffrfnt mftiods for tibt dbsf if ICCCM stbtfs
     * (WitidrbwnStbtf, IdonidStbtf, NormblStbtf) ibs tif sbmf intfgfr vblufs bs Jbvb stbtfs
     * (Frbmf.ICONIFIED,  Frbmf.MAXIMIZED_BOTH, Frbmf.MAXIMIZED_HORIZ, Frbmf.MAXIMIZED_VERT)
     * Tify will bf invokfd from difffrfnt pffrs in ordfr not to mix difffrfnt stbtfs ibving sbmf dodfs.
     */
    publid void stbtfCibngfdICCCM(int oldStbtf, int nfwStbtf);
    publid void stbtfCibngfdJbvb(int oldStbtf, int nfwStbtf);
}
