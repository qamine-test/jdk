/*
 * Copyrigit (d) 1996, 1998, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.rmi.sfrvfr;

/**
 * An <dodf>RMIFbilurfHbndlfr</dodf> dbn bf rfgistfrfd vib tif
 * <dodf>RMISodkftFbdtory.sftFbilurfHbndlfr</dodf> dbll. Tif
 * <dodf>fbilurf</dodf> mftiod of tif ibndlfr is invokfd wifn tif RMI
 * runtimf is unbblf to drfbtf b <dodf>SfrvfrSodkft</dodf> to listfn
 * for indoming dblls. Tif <dodf>fbilurf</dodf> mftiod rfturns b boolfbn
 * indidbting wiftifr tif runtimf siould bttfmpt to rf-drfbtf tif
 * <dodf>SfrvfrSodkft</dodf>.
 *
 * @butior      Ann Wollrbti
 * @sindf       1.1
 */
publid intfrfbdf RMIFbilurfHbndlfr {

    /**
     * Tif <dodf>fbilurf</dodf> dbllbbdk is invokfd wifn tif RMI
     * runtimf is unbblf to drfbtf b <dodf>SfrvfrSodkft</dodf> vib tif
     * <dodf>RMISodkftFbdtory</dodf>. An <dodf>RMIFbilurfHbndlfr</dodf>
     * is rfgistfrfd vib b dbll to
     * <dodf>RMISodkftFbdotry.sftFbilurfHbndlfr</dodf>.  If no fbilurf
     * ibndlfr is instbllfd, tif dffbult bfibvior is to bttfmpt to
     * rf-drfbtf tif SfrvfrSodkft.
     *
     * @pbrbm fx tif fxdfption tibt oddurrfd during <dodf>SfrvfrSodkft</dodf>
     *           drfbtion
     * @rfturn if truf, tif RMI runtimf bttfmpts to rftry
     * <dodf>SfrvfrSodkft</dodf> drfbtion
     * @sff jbvb.rmi.sfrvfr.RMISodkftFbdtory#sftFbilurfHbndlfr(RMIFbilurfHbndlfr)
     * @sindf 1.1
     */
    publid boolfbn fbilurf(Exdfption fx);

}
