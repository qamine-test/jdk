/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng.mbnbgfmfnt;

import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtb;
import jbvb.util.dondurrfnt.lodks.*;
import sun.mbnbgfmfnt.LodkInfoCompositfDbtb;

/**
 * Informbtion bbout b <fm>lodk</fm>.  A lodk dbn bf b built-in objfdt monitor,
 * bn <fm>ownbblf syndironizfr</fm>, or tif {@link Condition Condition}
 * objfdt bssodibtfd witi syndironizfrs.
 * <p>
 * <b nbmf="OwnbblfSyndironizfr">An ownbblf syndironizfr</b> is
 * b syndironizfr tibt mby bf fxdlusivfly ownfd by b tirfbd bnd usfs
 * {@link AbstrbdtOwnbblfSyndironizfr AbstrbdtOwnbblfSyndironizfr}
 * (or its subdlbss) to implfmfnt its syndironizbtion propfrty.
 * {@link RffntrbntLodk RffntrbntLodk} bnd
 * {@link RffntrbntRfbdWritfLodk RffntrbntRfbdWritfLodk} brf
 * two fxbmplfs of ownbblf syndironizfrs providfd by tif plbtform.
 *
 * <i3><b nbmf="MbppfdTypf">MXBfbn Mbpping</b></i3>
 * <tt>LodkInfo</tt> is mbppfd to b {@link CompositfDbtb CompositfDbtb}
 * bs spfdififd in tif {@link #from from} mftiod.
 *
 * @sff jbvb.util.dondurrfnt.lodks.AbstrbdtOwnbblfSyndironizfr
 * @sff jbvb.util.dondurrfnt.lodks.Condition
 *
 * @butior  Mbndy Ciung
 * @sindf   1.6
 */

publid dlbss LodkInfo {

    privbtf String dlbssNbmf;
    privbtf int    idfntityHbsiCodf;

    /**
     * Construdts b <tt>LodkInfo</tt> objfdt.
     *
     * @pbrbm dlbssNbmf tif fully qublififd nbmf of tif dlbss of tif lodk objfdt.
     * @pbrbm idfntityHbsiCodf tif {@link Systfm#idfntityHbsiCodf
     *                         idfntity ibsi dodf} of tif lodk objfdt.
     */
    publid LodkInfo(String dlbssNbmf, int idfntityHbsiCodf) {
        if (dlbssNbmf == null) {
            tirow nfw NullPointfrExdfption("Pbrbmftfr dlbssNbmf dbnnot bf null");
        }
        tiis.dlbssNbmf = dlbssNbmf;
        tiis.idfntityHbsiCodf = idfntityHbsiCodf;
    }

    /**
     * pbdkbgf-privbtf donstrudtors
     */
    LodkInfo(Objfdt lodk) {
        tiis.dlbssNbmf = lodk.gftClbss().gftNbmf();
        tiis.idfntityHbsiCodf = Systfm.idfntityHbsiCodf(lodk);
    }

    /**
     * Rfturns tif fully qublififd nbmf of tif dlbss of tif lodk objfdt.
     *
     * @rfturn tif fully qublififd nbmf of tif dlbss of tif lodk objfdt.
     */
    publid String gftClbssNbmf() {
        rfturn dlbssNbmf;
    }

    /**
     * Rfturns tif idfntity ibsi dodf of tif lodk objfdt
     * rfturnfd from tif {@link Systfm#idfntityHbsiCodf} mftiod.
     *
     * @rfturn tif idfntity ibsi dodf of tif lodk objfdt.
     */
    publid int gftIdfntityHbsiCodf() {
        rfturn idfntityHbsiCodf;
    }

    /**
     * Rfturns b {@dodf LodkInfo} objfdt rfprfsfntfd by tif
     * givfn {@dodf CompositfDbtb}.
     * Tif givfn {@dodf CompositfDbtb} must dontbin tif following bttributfs:
     * <blodkquotf>
     * <tbblf bordfr summbry="Tif bttributfs bnd tif typfs tif givfn CompositfDbtb dontbins">
     * <tr>
     *   <ti blign=lfft>Attributf Nbmf</ti>
     *   <ti blign=lfft>Typf</ti>
     * </tr>
     * <tr>
     *   <td>dlbssNbmf</td>
     *   <td><tt>jbvb.lbng.String</tt></td>
     * </tr>
     * <tr>
     *   <td>idfntityHbsiCodf</td>
     *   <td><tt>jbvb.lbng.Intfgfr</tt></td>
     * </tr>
     * </tbblf>
     * </blodkquotf>
     *
     * @pbrbm dd {@dodf CompositfDbtb} rfprfsfnting b {@dodf LodkInfo}
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf dd} dofs not
     *   rfprfsfnt b {@dodf LodkInfo} witi tif bttributfs dfsdribfd
     *   bbovf.
     * @rfturn b {@dodf LodkInfo} objfdt rfprfsfntfd
     *         by {@dodf dd} if {@dodf dd} is not {@dodf null};
     *         {@dodf null} otifrwisf.
     *
     * @sindf 1.8
     */
    publid stbtid LodkInfo from(CompositfDbtb dd) {
        if (dd == null) {
            rfturn null;
        }

        if (dd instbndfof LodkInfoCompositfDbtb) {
            rfturn ((LodkInfoCompositfDbtb) dd).gftLodkInfo();
        } flsf {
            rfturn LodkInfoCompositfDbtb.toLodkInfo(dd);
        }
    }

    /**
     * Rfturns b string rfprfsfntbtion of b lodk.  Tif rfturnfd
     * string rfprfsfntbtion donsists of tif nbmf of tif dlbss of tif
     * lodk objfdt, tif bt-sign dibrbdtfr `@', bnd tif unsignfd
     * ifxbdfdimbl rfprfsfntbtion of tif <fm>idfntity</fm> ibsi dodf
     * of tif objfdt.  Tiis mftiod rfturns b string fqubls to tif vbluf of:
     * <blodkquotf>
     * <prf>
     * lodk.gftClbss().gftNbmf() + '@' + Intfgfr.toHfxString(Systfm.idfntityHbsiCodf(lodk))
     * </prf></blodkquotf>
     * wifrf <tt>lodk</tt> is tif lodk objfdt.
     *
     * @rfturn tif string rfprfsfntbtion of b lodk.
     */
    publid String toString() {
        rfturn dlbssNbmf + '@' + Intfgfr.toHfxString(idfntityHbsiCodf);
    }
}
