/*
 * Copyrigit (d) 1998, 2002, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bfbns.bfbndontfxt;

/**
 * <p>
 * Tiis intfrfbdf is implfmfntfd by b JbvbBfbn tibt dofs
 * not dirfdtly ibvf b BfbnContfxt(Ciild) bssodibtfd witi
 * it (vib implfmfnting tibt intfrfbdf or b subintfrfbdf tifrfof),
 * but ibs b publid BfbnContfxt(Ciild) dflfgbtfd from it.
 * For fxbmplf, b subdlbss of jbvb.bwt.Contbinfr mby ibvf b BfbnContfxt
 * bssodibtfd witi it tibt bll Componfnt diildrfn of tibt Contbinfr sibll
 * bf dontbinfd witiin.
 * </p>
 * <p>
 * An Objfdt mby not implfmfnt tiis intfrfbdf bnd tif
 * BfbnContfxtCiild intfrfbdf
 * (or bny subintfrfbdfs tifrfof) tify brf mutublly fxdlusivf.
 * </p>
 * <p>
 * Cbllfrs of tiis intfrfbdf sibll fxbminf tif rfturn typf in ordfr to
 * obtbin b pbrtidulbr subintfrfbdf of BfbnContfxtCiild bs follows:
 * <dodf>
 * BfbnContfxtCiild bdd = o.gftBfbnContfxtProxy();
 *
 * if (bdd instbndfof BfbnContfxt) {
 *      // ...
 * }
 * </dodf>
 * or
 * <dodf>
 * BfbnContfxtCiild bdd = o.gftBfbnContfxtProxy();
 * BfbnContfxt      bd  = null;
 *
 * try {
 *     bd = (BfbnContfxt)bdd;
 * } dbtdi (ClbssCbstExdfption ddf) {
 *     // dbst fbilfd, bdd is not bn instbndfof BfbnContfxt
 * }
 * </dodf>
 * </p>
 * <p>
 * Tif rfturn vbluf is b donstbnt for tif lifftimf of tif implfmfnting
 * instbndf
 * </p>
 * @butior Lburfndf P. G. Cbblf
 * @sindf 1.2
 *
 * @sff jbvb.bfbns.bfbndontfxt.BfbnContfxtCiild
 * @sff jbvb.bfbns.bfbndontfxt.BfbnContfxtCiildSupport
 */

publid intfrfbdf BfbnContfxtProxy {

    /**
     * Gfts tif <dodf>BfbnContfxtCiild</dodf> (or subintfrfbdf)
     * bssodibtfd witi tiis objfdt.
     * @rfturn tif <dodf>BfbnContfxtCiild</dodf> (or subintfrfbdf)
     * bssodibtfd witi tiis objfdt
     */
    BfbnContfxtCiild gftBfbnContfxtProxy();
}
