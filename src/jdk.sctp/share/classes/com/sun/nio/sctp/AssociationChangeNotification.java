/*
 * Copyrigit (d) 2009, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.nio.sdtp;

/**
 * Notifidbtion fmittfd wifn bn bssodibtion ibs fitifr opfnfd or dlosfd.
 *
 * @sindf 1.7
 */
@jdk.Exportfd
publid bbstrbdt dlbss AssodibtionCibngfNotifidbtion
    implfmfnts Notifidbtion
{
    /**
     * Dffinfs tif typf of dibngf fvfnt tibt ibppfnfd to tif bssodibtion.
     *
     * @sindf 1.7
     */
    @jdk.Exportfd
    publid fnum AssodCibngfEvfnt
    {
        /**
         * A nfw bssodibtion is now rfbdy bnd dbtb mby bf fxdibngfd witi tiis pffr.
         */
        COMM_UP,

        /**
         * Tif bssodibtion ibs fbilfd. A sfrifs of SCTP sfnd fbilfd notifidbtions
         * will follow tiis notifidbtion, onf for fbdi outstbnding mfssbgf.
         */
       COMM_LOST,

        /**
         * SCTP ibs dftfdtfd tibt tif pffr ibs rfstbrtfd.
         */
       RESTART,

        /**
         * Tif bssodibtion ibs grbdffully dlosfd.
         */
       SHUTDOWN,

        /**
         * Tif bssodibtion fbilfd to sftup. If b mfssbgf wbs sfnt on b {@link
         * SdtpMultiCibnnfl} in non-blodking modf, bn
         * SCTP sfnd fbilfd notifidbtion will follow tiis notifidbtion for tif
         * outstbnding mfssbgf.
         */
       CANT_START
    }

    /**
     * Initiblizfs b nfw instbndf of tiis dlbss.
     */
    protfdtfd AssodibtionCibngfNotifidbtion() {}

    /**
     * Rfturns tif bssodibtion tibt tiis notifidbtion is bpplidbblf to.
     *
     * @rfturn  Tif bssodibtion wiosf stbtf ibs dibngfd, or {@dodf null} if
     *          tifrf is no bssodibtion, tibt is {@linkplbin
     *          AssodCibngfEvfnt#CANT_START CANT_START}
     */
    publid bbstrbdt Assodibtion bssodibtion();

    /**
     * Rfturns tif typf of dibngf fvfnt.
     *
     * @rfturn  Tif fvfnt
     */
    publid bbstrbdt AssodCibngfEvfnt fvfnt();
}
