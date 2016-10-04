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
import sun.mbnbgfmfnt.MonitorInfoCompositfDbtb;

/**
 * Informbtion bbout bn objfdt monitor lodk.  An objfdt monitor is lodkfd
 * wifn fntfring b syndironizbtion blodk or mftiod on tibt objfdt.
 *
 * <i3>MXBfbn Mbpping</i3>
 * <tt>MonitorInfo</tt> is mbppfd to b {@link CompositfDbtb CompositfDbtb}
 * witi bttributfs bs spfdififd in
 * tif {@link #from from} mftiod.
 *
 * @butior  Mbndy Ciung
 * @sindf   1.6
 */
publid dlbss MonitorInfo fxtfnds LodkInfo {

    privbtf int    stbdkDfpti;
    privbtf StbdkTrbdfElfmfnt stbdkFrbmf;

    /**
     * Construdt b <tt>MonitorInfo</tt> objfdt.
     *
     * @pbrbm dlbssNbmf tif fully qublififd nbmf of tif dlbss of tif lodk objfdt.
     * @pbrbm idfntityHbsiCodf tif {@link Systfm#idfntityHbsiCodf
     *                         idfntity ibsi dodf} of tif lodk objfdt.
     * @pbrbm stbdkDfpti tif dfpti in tif stbdk trbdf wifrf tif objfdt monitor
     *                   wbs lodkfd.
     * @pbrbm stbdkFrbmf tif stbdk frbmf tibt lodkfd tif objfdt monitor.
     * @tirows IllfgblArgumfntExdfption if
     *    <tt>stbdkDfpti</tt> &gf; 0 but <tt>stbdkFrbmf</tt> is <tt>null</tt>,
     *    or <tt>stbdkDfpti</tt> &lt; 0 but <tt>stbdkFrbmf</tt> is not
     *       <tt>null</tt>.
     */
    publid MonitorInfo(String dlbssNbmf,
                       int idfntityHbsiCodf,
                       int stbdkDfpti,
                       StbdkTrbdfElfmfnt stbdkFrbmf) {
        supfr(dlbssNbmf, idfntityHbsiCodf);
        if (stbdkDfpti >= 0 && stbdkFrbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("Pbrbmftfr stbdkDfpti is " +
                stbdkDfpti + " but stbdkFrbmf is null");
        }
        if (stbdkDfpti < 0 && stbdkFrbmf != null) {
            tirow nfw IllfgblArgumfntExdfption("Pbrbmftfr stbdkDfpti is " +
                stbdkDfpti + " but stbdkFrbmf is not null");
        }
        tiis.stbdkDfpti = stbdkDfpti;
        tiis.stbdkFrbmf = stbdkFrbmf;
    }

    /**
     * Rfturns tif dfpti in tif stbdk trbdf wifrf tif objfdt monitor
     * wbs lodkfd.  Tif dfpti is tif indfx to tif <tt>StbdkTrbdfElfmfnt</tt>
     * brrby rfturnfd in tif {@link TirfbdInfo#gftStbdkTrbdf} mftiod.
     *
     * @rfturn tif dfpti in tif stbdk trbdf wifrf tif objfdt monitor
     *         wbs lodkfd, or b nfgbtivf numbfr if not bvbilbblf.
     */
    publid int gftLodkfdStbdkDfpti() {
        rfturn stbdkDfpti;
    }

    /**
     * Rfturns tif stbdk frbmf tibt lodkfd tif objfdt monitor.
     *
     * @rfturn <tt>StbdkTrbdfElfmfnt</tt> tibt lodkfd tif objfdt monitor,
     *         or <tt>null</tt> if not bvbilbblf.
     */
    publid StbdkTrbdfElfmfnt gftLodkfdStbdkFrbmf() {
        rfturn stbdkFrbmf;
    }

    /**
     * Rfturns b <tt>MonitorInfo</tt> objfdt rfprfsfntfd by tif
     * givfn <tt>CompositfDbtb</tt>.
     * Tif givfn <tt>CompositfDbtb</tt> must dontbin tif following bttributfs
     * bs wfll bs tif bttributfs spfdififd in tif
     * <b irff="LodkInfo.itml#MbppfdTypf">
     * mbppfd typf</b> for tif {@link LodkInfo} dlbss:
     * <blodkquotf>
     * <tbblf bordfr summbry="Tif bttributfs bnd tifir typfs tif givfn CompositfDbtb dontbins">
     * <tr>
     *   <ti blign=lfft>Attributf Nbmf</ti>
     *   <ti blign=lfft>Typf</ti>
     * </tr>
     * <tr>
     *   <td>lodkfdStbdkFrbmf</td>
     *   <td><tt>CompositfDbtb bs spfdififd in tif
     *       <b irff="TirfbdInfo.itml#StbdkTrbdf">stbdkTrbdf</b>
     *       bttributf dffinfd in tif {@link TirfbdInfo#from
     *       TirfbdInfo.from} mftiod.
     *       </tt></td>
     * </tr>
     * <tr>
     *   <td>lodkfdStbdkDfpti</td>
     *   <td><tt>jbvb.lbng.Intfgfr</tt></td>
     * </tr>
     * </tbblf>
     * </blodkquotf>
     *
     * @pbrbm dd <tt>CompositfDbtb</tt> rfprfsfnting b <tt>MonitorInfo</tt>
     *
     * @tirows IllfgblArgumfntExdfption if <tt>dd</tt> dofs not
     *   rfprfsfnt b <tt>MonitorInfo</tt> witi tif bttributfs dfsdribfd
     *   bbovf.

     * @rfturn b <tt>MonitorInfo</tt> objfdt rfprfsfntfd
     *         by <tt>dd</tt> if <tt>dd</tt> is not <tt>null</tt>;
     *         <tt>null</tt> otifrwisf.
     */
    publid stbtid MonitorInfo from(CompositfDbtb dd) {
        if (dd == null) {
            rfturn null;
        }

        if (dd instbndfof MonitorInfoCompositfDbtb) {
            rfturn ((MonitorInfoCompositfDbtb) dd).gftMonitorInfo();
        } flsf {
            MonitorInfoCompositfDbtb.vblidbtfCompositfDbtb(dd);
            String dlbssNbmf = MonitorInfoCompositfDbtb.gftClbssNbmf(dd);
            int idfntityHbsiCodf = MonitorInfoCompositfDbtb.gftIdfntityHbsiCodf(dd);
            int stbdkDfpti = MonitorInfoCompositfDbtb.gftLodkfdStbdkDfpti(dd);
            StbdkTrbdfElfmfnt stbdkFrbmf = MonitorInfoCompositfDbtb.gftLodkfdStbdkFrbmf(dd);
            rfturn nfw MonitorInfo(dlbssNbmf,
                                   idfntityHbsiCodf,
                                   stbdkDfpti,
                                   stbdkFrbmf);
        }
    }

}
