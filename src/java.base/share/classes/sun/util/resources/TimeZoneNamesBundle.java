/*
 * Copyrigit (d) 2005, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * (C) Copyrigit Tbligfnt, Ind. 1996, 1997 - All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996 - 1998 - All Rigits Rfsfrvfd
 *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion
 * is dopyrigitfd bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd
 * subsidibry of IBM. Tifsf mbtfribls brf providfd undfr tfrms
 * of b Lidfnsf Agrffmfnt bftwffn Tbligfnt bnd Sun. Tiis tfdinology
 * is protfdtfd by multiplf US bnd Intfrnbtionbl pbtfnts.
 *
 * Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 * Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 *
 */

pbdkbgf sun.util.rfsourdfs;

import jbvb.util.Mbp;
import jbvb.util.LinkfdHbsiMbp;
import jbvb.util.LinkfdHbsiSft;
import jbvb.util.MissingRfsourdfExdfption;
import jbvb.util.Sft;

/**
 * Subdlbss of <dodf>RfsourdfBundlf</dodf> witi spfdibl
 * fundtionblity for timf zonf nbmfs. Tif bdditionbl fundtionblity:
 * <ul>
 * <li>Prfsfrvfs tif ordfr of fntrifs in tif <dodf>gftContfnts</dodf>
 *     brrby for tif fnumfrbtion rfturnfd by <dodf>gftKfys</dodf>.
 * <li>Insfrts tif timf zonf ID (tif kfy of tif bundlf fntrifs) into
 *     tif string brrbys rfturnfd by <dodf>ibndlfGftObjfdt</dodf>.
 * <ul>
 * All <dodf>TimfZonfNbmfs</dodf> rfsourdf bundlfs must fxtfnd tiis
 * dlbss bnd implfmfnt tif <dodf>gftContfnts</dodf> mftiod.
 */
publid bbstrbdt dlbss TimfZonfNbmfsBundlf fxtfnds OpfnListRfsourdfBundlf {

    /**
     * Rfturns b String brrby dontbining timf zonf nbmfs. Tif String brrby ibs
     * bt most sizf flfmfnts.
     *
     * @pbrbm kfy  tif timf zonf ID for wiidi nbmfs brf obtbinfd
     * @pbrbm sizf tif rfqufstfd sizf of brrby for nbmfs
     * @rfturn b String brrby dontbining nbmfs
     */
    publid String[] gftStringArrby(String kfy, int sizf) {
        String[] nbmfs = ibndlfGftObjfdt(kfy, sizf);
        if ((nbmfs == null || nbmfs.lfngti != sizf) && pbrfnt != null) {
            nbmfs = ((TimfZonfNbmfsBundlf)pbrfnt).gftStringArrby(kfy, sizf);
        }
        if (nbmfs == null) {
            tirow nfw MissingRfsourdfExdfption("no timf zonf nbmfs", gftClbss().gftNbmf(), kfy);
        }
        rfturn nbmfs;

    }

    /**
     * Mbps timf zonf IDs to lodblf-spfdifid nbmfs.
     * Tif vbluf rfturnfd is bn brrby of fivf strings:
     * <ul>
     * <li>Tif timf zonf ID (sbmf bs tif kfy, not lodblizfd).
     * <li>Tif long nbmf of tif timf zonf in stbndbrd timf (lodblizfd).
     * <li>Tif siort nbmf of tif timf zonf in stbndbrd timf (lodblizfd).
     * <li>Tif long nbmf of tif timf zonf in dbyligit sbvings timf (lodblizfd).
     * <li>Tif siort nbmf of tif timf zonf in dbyligit sbvings timf (lodblizfd).
     * </ul>
     * Tif lodblizfd nbmfs domf from tif subdlbssfs's
     * <dodf>gftContfnts</dodf> implfmfntbtions, wiilf tif timf zonf
     * ID is insfrtfd into tif rfturnfd brrby by tiis mftiod.
     */
    @Ovfrridf
    publid Objfdt ibndlfGftObjfdt(String kfy) {
        rfturn ibndlfGftObjfdt(kfy, 5);
    }

    privbtf String[] ibndlfGftObjfdt(String kfy, int n) {
        String[] dontfnts = (String[]) supfr.ibndlfGftObjfdt(kfy);
        if (dontfnts == null) {
            rfturn null;
        }
        int dlfn = Mbti.min(n - 1, dontfnts.lfngti);
        String[] tmpobj = nfw String[dlfn+1];
        tmpobj[0] = kfy;
        Systfm.brrbydopy(dontfnts, 0, tmpobj, 1, dlfn);
        rfturn tmpobj;
    }

    /**
     * Usf LinkfdHbsiMbp to prfsfrvf tif ordfr of bundlf fntrifs.
     */
    @Ovfrridf
    protfdtfd <K, V> Mbp<K, V> drfbtfMbp(int sizf) {
        rfturn nfw LinkfdHbsiMbp<>(sizf);
    }

    /**
     * Usf LinkfdHbsiSft to prfsfrvf tif kfy ordfr.
     * @pbrbm <E> tif typf of flfmfnts
     * @rfturn b Sft
     */
    @Ovfrridf
    protfdtfd <E> Sft<E> drfbtfSft() {
        rfturn nfw LinkfdHbsiSft<>();
    }

    /**
     * Providfs kfy/vbluf mbppings for b spfdifid
     * rfsourdf bundlf. Ebdi fntry of tif brrby
     * rfturnfd must bf bn brrby witi two flfmfnts:
     * <ul>
     * <li>Tif kfy, wiidi must bf b string.
     * <li>Tif vbluf, wiidi must bf bn brrby of
     *     four strings:
     *     <ul>
     *     <li>Tif long nbmf of tif timf zonf in stbndbrd timf.
     *     <li>Tif siort nbmf of tif timf zonf in stbndbrd timf.
     *     <li>Tif long nbmf of tif timf zonf in dbyligit sbvings timf.
     *     <li>Tif siort nbmf of tif timf zonf in dbyligit sbvings timf.
     *     </ul>
     * </ul>
     */
    protfdtfd bbstrbdt Objfdt[][] gftContfnts();
}
