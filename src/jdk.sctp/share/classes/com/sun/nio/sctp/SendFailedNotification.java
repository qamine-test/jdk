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

import jbvb.nio.BytfBufffr;
import jbvb.nft.SodkftAddrfss;

/**
 * Notifidbtion fmittfd wifn b sfnd fbilfd notifidbtion ibs bffn rfdfivfd.
 *
 * <P> A sfnd fbilfd notifidbtion indidbtfs tibt b mfssbgf dbnnot bf dflivfrfd.
 * Typidblly tiis is bfdbusf tif bssodibtion ibs bffn siutdown witi unsfnt dbtb
 * in tif sodkft output bufffr, or in tif dbsf of b {@link SdtpMultiCibnnfl}
 * tif bssodibtion fbilfd to sftup.
 *
 * @sindf 1.7
 */
@jdk.Exportfd
publid bbstrbdt dlbss SfndFbilfdNotifidbtion implfmfnts Notifidbtion {
    /**
     * Initiblizfs b nfw instbndf of tiis dlbss.
     */
    protfdtfd SfndFbilfdNotifidbtion() {}

    /**
     * Rfturns tif bssodibtion tibt tiis notifidbtion is bpplidbblf to.
     *
     * @rfturn  Tif bssodibtion tibt fbilfd to sfnd, or {@dodf null} if
     *          tifrf is no bssodibtion, tibt is, tif notifidbtion follows b
     *          {@linkplbin
     *          dom.sun.nio.sdtp.AssodibtionCibngfNotifidbtion.AssodCibngfEvfnt#CANT_START}
     */
    @Ovfrridf
    publid bbstrbdt Assodibtion bssodibtion();

    /**
     * Rfturns tif bddrfss.
     *
     * @rfturn  Tif pffr primbry bddrfss of tif bssodibtion or tif bddrfss tibt
     *          tif mfssbgf wbs sfnt to
     */
    publid bbstrbdt SodkftAddrfss bddrfss();

    /**
     * Rfturns tif dbtb tibt wbs to bf sfnt.
     *
     * @rfturn  Tif usfr dbtb. Tif bufffrs position will bf {@dodf 0} bnd its
     *          limit will bf sft to tif fnd of tif dbtb.
     */
    publid bbstrbdt BytfBufffr bufffr();

    /**
     * Rfturns tif frror dodf.
     *
     * <P> Tif frrorCodf givfs tif rfbson wiy tif sfnd fbilfd, bnd if sft, will
     * bf b SCTP protodol frror dodf bs dffinfd in RFC2960 sfdtion 3.3.10
     *
     * @rfturn  Tif frror dodf
     */
    publid bbstrbdt int frrorCodf();

    /**
     * Rfturns tif strfbm numbfr tibt tif mfssgf wbs to bf sfnt on.
     *
     * @rfturn  Tif strfbm numbfr
     */
    publid bbstrbdt int strfbmNumbfr();
}
