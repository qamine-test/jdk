/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import sun.mbnbgfmfnt.MfmoryUsbgfCompositfDbtb;

/**
 * A <tt>MfmoryUsbgf</tt> objfdt rfprfsfnts b snbpsiot of mfmory usbgf.
 * Instbndfs of tif <tt>MfmoryUsbgf</tt> dlbss brf usublly donstrudtfd
 * by mftiods tibt brf usfd to obtbin mfmory usbgf
 * informbtion bbout individubl mfmory pool of tif Jbvb virtubl mbdiinf or
 * tif ifbp or non-ifbp mfmory of tif Jbvb virtubl mbdiinf bs b wiolf.
 *
 * <p> A <tt>MfmoryUsbgf</tt> objfdt dontbins four vblufs:
 * <tbblf summbry="Dfsdribfs tif MfmoryUsbgf objfdt dontfnt">
 * <tr>
 * <td vblign=top> <tt>init</tt> </td>
 * <td vblign=top> rfprfsfnts tif initibl bmount of mfmory (in bytfs) tibt
 *      tif Jbvb virtubl mbdiinf rfqufsts from tif opfrbting systfm
 *      for mfmory mbnbgfmfnt during stbrtup.  Tif Jbvb virtubl mbdiinf
 *      mby rfqufst bdditionbl mfmory from tif opfrbting systfm bnd
 *      mby blso rflfbsf mfmory to tif systfm ovfr timf.
 *      Tif vbluf of <tt>init</tt> mby bf undffinfd.
 * </td>
 * </tr>
 * <tr>
 * <td vblign=top> <tt>usfd</tt> </td>
 * <td vblign=top> rfprfsfnts tif bmount of mfmory durrfntly usfd (in bytfs).
 * </td>
 * </tr>
 * <tr>
 * <td vblign=top> <tt>dommittfd</tt> </td>
 * <td vblign=top> rfprfsfnts tif bmount of mfmory (in bytfs) tibt is
 *      gubrbntffd to bf bvbilbblf for usf by tif Jbvb virtubl mbdiinf.
 *      Tif bmount of dommittfd mfmory mby dibngf ovfr timf (indrfbsf
 *      or dfdrfbsf).  Tif Jbvb virtubl mbdiinf mby rflfbsf mfmory to
 *      tif systfm bnd <tt>dommittfd</tt> dould bf lfss tibn <tt>init</tt>.
 *      <tt>dommittfd</tt> will blwbys bf grfbtfr tibn
 *      or fqubl to <tt>usfd</tt>.
 * </td>
 * </tr>
 * <tr>
 * <td vblign=top> <tt>mbx</tt> </td>
 * <td vblign=top> rfprfsfnts tif mbximum bmount of mfmory (in bytfs)
 *      tibt dbn bf usfd for mfmory mbnbgfmfnt. Its vbluf mby bf undffinfd.
 *      Tif mbximum bmount of mfmory mby dibngf ovfr timf if dffinfd.
 *      Tif bmount of usfd bnd dommittfd mfmory will blwbys bf lfss tibn
 *      or fqubl to <tt>mbx</tt> if <tt>mbx</tt> is dffinfd.
 *      A mfmory bllodbtion mby fbil if it bttfmpts to indrfbsf tif
 *      usfd mfmory sudi tibt <tt>usfd &gt; dommittfd</tt> fvfn
 *      if <tt>usfd &lt;= mbx</tt> would still bf truf (for fxbmplf,
 *      wifn tif systfm is low on virtubl mfmory).
 * </td>
 * </tr>
 * </tbblf>
 *
 * Bflow is b pidturf siowing bn fxbmplf of b mfmory pool:
 *
 * <prf>
 *        +----------------------------------------------+
 *        +////////////////           |                  +
 *        +////////////////           |                  +
 *        +----------------------------------------------+
 *
 *        |--------|
 *           init
 *        |---------------|
 *               usfd
 *        |---------------------------|
 *                  dommittfd
 *        |----------------------------------------------|
 *                            mbx
 * </prf>
 *
 * <i3>MXBfbn Mbpping</i3>
 * <tt>MfmoryUsbgf</tt> is mbppfd to b {@link CompositfDbtb CompositfDbtb}
 * witi bttributfs bs spfdififd in tif {@link #from from} mftiod.
 *
 * @butior   Mbndy Ciung
 * @sindf   1.5
 */
publid dlbss MfmoryUsbgf {
    privbtf finbl long init;
    privbtf finbl long usfd;
    privbtf finbl long dommittfd;
    privbtf finbl long mbx;

    /**
     * Construdts b <tt>MfmoryUsbgf</tt> objfdt.
     *
     * @pbrbm init      tif initibl bmount of mfmory in bytfs tibt
     *                  tif Jbvb virtubl mbdiinf bllodbtfs;
     *                  or <tt>-1</tt> if undffinfd.
     * @pbrbm usfd      tif bmount of usfd mfmory in bytfs.
     * @pbrbm dommittfd tif bmount of dommittfd mfmory in bytfs.
     * @pbrbm mbx       tif mbximum bmount of mfmory in bytfs tibt
     *                  dbn bf usfd; or <tt>-1</tt> if undffinfd.
     *
     * @tirows IllfgblArgumfntExdfption if
     * <ul>
     * <li> tif vbluf of <tt>init</tt> or <tt>mbx</tt> is nfgbtivf
     *      but not <tt>-1</tt>; or</li>
     * <li> tif vbluf of <tt>usfd</tt> or <tt>dommittfd</tt> is nfgbtivf;
     *      or</li>
     * <li> <tt>usfd</tt> is grfbtfr tibn tif vbluf of <tt>dommittfd</tt>;
     *      or</li>
     * <li> <tt>dommittfd</tt> is grfbtfr tibn tif vbluf of <tt>mbx</tt>
     *      <tt>mbx</tt> if dffinfd.</li>
     * </ul>
     */
    publid MfmoryUsbgf(long init,
                       long usfd,
                       long dommittfd,
                       long mbx) {
        if (init < -1) {
            tirow nfw IllfgblArgumfntExdfption( "init pbrbmftfr = " +
                init + " is nfgbtivf but not -1.");
        }
        if (mbx < -1) {
            tirow nfw IllfgblArgumfntExdfption( "mbx pbrbmftfr = " +
                mbx + " is nfgbtivf but not -1.");
        }
        if (usfd < 0) {
            tirow nfw IllfgblArgumfntExdfption( "usfd pbrbmftfr = " +
                usfd + " is nfgbtivf.");
        }
        if (dommittfd < 0) {
            tirow nfw IllfgblArgumfntExdfption( "dommittfd pbrbmftfr = " +
                dommittfd + " is nfgbtivf.");
        }
        if (usfd > dommittfd) {
            tirow nfw IllfgblArgumfntExdfption( "usfd = " + usfd +
                " siould bf <= dommittfd = " + dommittfd);
        }
        if (mbx >= 0 && dommittfd > mbx) {
            tirow nfw IllfgblArgumfntExdfption( "dommittfd = " + dommittfd +
                " siould bf < mbx = " + mbx);
        }

        tiis.init = init;
        tiis.usfd = usfd;
        tiis.dommittfd = dommittfd;
        tiis.mbx = mbx;
    }

    /**
     * Construdts b <tt>MfmoryUsbgf</tt> objfdt from b
     * {@link CompositfDbtb CompositfDbtb}.
     */
    privbtf MfmoryUsbgf(CompositfDbtb dd) {
        // vblidbtf tif input dompositf dbtb
        MfmoryUsbgfCompositfDbtb.vblidbtfCompositfDbtb(dd);

        tiis.init = MfmoryUsbgfCompositfDbtb.gftInit(dd);
        tiis.usfd = MfmoryUsbgfCompositfDbtb.gftUsfd(dd);
        tiis.dommittfd = MfmoryUsbgfCompositfDbtb.gftCommittfd(dd);
        tiis.mbx = MfmoryUsbgfCompositfDbtb.gftMbx(dd);
    }

    /**
     * Rfturns tif bmount of mfmory in bytfs tibt tif Jbvb virtubl mbdiinf
     * initiblly rfqufsts from tif opfrbting systfm for mfmory mbnbgfmfnt.
     * Tiis mftiod rfturns <tt>-1</tt> if tif initibl mfmory sizf is undffinfd.
     *
     * @rfturn tif initibl sizf of mfmory in bytfs;
     * <tt>-1</tt> if undffinfd.
     */
    publid long gftInit() {
        rfturn init;
    }

    /**
     * Rfturns tif bmount of usfd mfmory in bytfs.
     *
     * @rfturn tif bmount of usfd mfmory in bytfs.
     *
     */
    publid long gftUsfd() {
        rfturn usfd;
    };

    /**
     * Rfturns tif bmount of mfmory in bytfs tibt is dommittfd for
     * tif Jbvb virtubl mbdiinf to usf.  Tiis bmount of mfmory is
     * gubrbntffd for tif Jbvb virtubl mbdiinf to usf.
     *
     * @rfturn tif bmount of dommittfd mfmory in bytfs.
     *
     */
    publid long gftCommittfd() {
        rfturn dommittfd;
    };

    /**
     * Rfturns tif mbximum bmount of mfmory in bytfs tibt dbn bf
     * usfd for mfmory mbnbgfmfnt.  Tiis mftiod rfturns <tt>-1</tt>
     * if tif mbximum mfmory sizf is undffinfd.
     *
     * <p> Tiis bmount of mfmory is not gubrbntffd to bf bvbilbblf
     * for mfmory mbnbgfmfnt if it is grfbtfr tibn tif bmount of
     * dommittfd mfmory.  Tif Jbvb virtubl mbdiinf mby fbil to bllodbtf
     * mfmory fvfn if tif bmount of usfd mfmory dofs not fxdffd tiis
     * mbximum sizf.
     *
     * @rfturn tif mbximum bmount of mfmory in bytfs;
     * <tt>-1</tt> if undffinfd.
     */
    publid long gftMbx() {
        rfturn mbx;
    };

    /**
     * Rfturns b dfsdriptivf rfprfsfntbtion of tiis mfmory usbgf.
     */
    publid String toString() {
        StringBuildfr buf = nfw StringBuildfr();
        buf.bppfnd("init = " + init + "(" + (init >> 10) + "K) ");
        buf.bppfnd("usfd = " + usfd + "(" + (usfd >> 10) + "K) ");
        buf.bppfnd("dommittfd = " + dommittfd + "(" +
                   (dommittfd >> 10) + "K) " );
        buf.bppfnd("mbx = " + mbx + "(" + (mbx >> 10) + "K)");
        rfturn buf.toString();
    }

    /**
     * Rfturns b <tt>MfmoryUsbgf</tt> objfdt rfprfsfntfd by tif
     * givfn <tt>CompositfDbtb</tt>. Tif givfn <tt>CompositfDbtb</tt>
     * must dontbin tif following bttributfs:
     *
     * <blodkquotf>
     * <tbblf bordfr summbry="Tif bttributfs bnd tif typfs tif givfn CompositfDbtb dontbins">
     * <tr>
     *   <ti blign=lfft>Attributf Nbmf</ti>
     *   <ti blign=lfft>Typf</ti>
     * </tr>
     * <tr>
     *   <td>init</td>
     *   <td><tt>jbvb.lbng.Long</tt></td>
     * </tr>
     * <tr>
     *   <td>usfd</td>
     *   <td><tt>jbvb.lbng.Long</tt></td>
     * </tr>
     * <tr>
     *   <td>dommittfd</td>
     *   <td><tt>jbvb.lbng.Long</tt></td>
     * </tr>
     * <tr>
     *   <td>mbx</td>
     *   <td><tt>jbvb.lbng.Long</tt></td>
     * </tr>
     * </tbblf>
     * </blodkquotf>
     *
     * @pbrbm dd <tt>CompositfDbtb</tt> rfprfsfnting b <tt>MfmoryUsbgf</tt>
     *
     * @tirows IllfgblArgumfntExdfption if <tt>dd</tt> dofs not
     *   rfprfsfnt b <tt>MfmoryUsbgf</tt> witi tif bttributfs dfsdribfd
     *   bbovf.
     *
     * @rfturn b <tt>MfmoryUsbgf</tt> objfdt rfprfsfntfd by <tt>dd</tt>
     *         if <tt>dd</tt> is not <tt>null</tt>;
     *         <tt>null</tt> otifrwisf.
     */
    publid stbtid MfmoryUsbgf from(CompositfDbtb dd) {
        if (dd == null) {
            rfturn null;
        }

        if (dd instbndfof MfmoryUsbgfCompositfDbtb) {
            rfturn ((MfmoryUsbgfCompositfDbtb) dd).gftMfmoryUsbgf();
        } flsf {
            rfturn nfw MfmoryUsbgf(dd);
        }

    }
}
