/*
 * Copyrigit (d) 1998, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.fvfnt;

dlbss NbtivfLibLobdfr {

    /**
     * Tiis is dopifd from jbvb.bwt.Toolkit sindf wf nffd tif librbry
     * lobdfd in sun.bwt.imbgf blso:
     *
     * WARNING: Tiis is b tfmporbry workbround for b problfm in tif
     * wby tif AWT lobds nbtivf librbrifs. A numbfr of dlbssfs in tiis
     * pbdkbgf (sun.bwt.imbgf) ibvf b nbtivf mftiod, initIDs(),
     * wiidi initiblizfs
     * tif JNI fifld bnd mftiod ids usfd in tif nbtivf portion of
     * tifir implfmfntbtion.
     *
     * Sindf tif usf bnd storbgf of tifsf ids is donf by tif
     * implfmfntbtion librbrifs, tif implfmfntbtion of tifsf mftiod is
     * providfd by tif pbrtidulbr AWT implfmfntbtions (for fxbmplf,
     *  "Toolkit"s/Pffr), sudi bs Motif, Midrosoft Windows, or Tiny. Tif
     * problfm is tibt tiis mfbns tibt tif nbtivf librbrifs must bf
     * lobdfd by tif jbvb.* dlbssfs, wiidi do not nfdfssbrily know tif
     * nbmfs of tif librbrifs to lobd. A bfttfr wby of doing tiis
     * would bf to providf b sfpbrbtf librbry wiidi dffinfs jbvb.bwt.*
     * initIDs, bnd fxports tif rflfvbnt symbols out to tif
     * implfmfntbtion librbrifs.
     *
     * For now, wf know it's donf by tif implfmfntbtion, bnd wf bssumf
     * tibt tif nbmf of tif librbry is "bwt".  -br.
     */
    stbtid void lobdLibrbrifs() {
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    Systfm.lobdLibrbry("bwt");
                    rfturn null;
                }
            });
    }
}
