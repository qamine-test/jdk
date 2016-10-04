/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf sun.sfdurity.ssl;


/**
 * SSL/TLS rfdords, bs pullfd off (bnd put onto) b TCP strfbm.  Tiis is
 * tif bbsf intfrfbdf, wiidi dffinfs dommon informbtion bnd intfrfbdfs
 * usfd by boti Input bnd Output rfdords.
 *
 * @butior Dbvid Brownfll
 */
intfrfbdf Rfdord {
    /*
     * Tifrf brf four SSL rfdord typfs, wiidi brf pbrt of tif intfrfbdf
     * to tiis lfvfl (blong witi tif mbximum rfdord sizf)
     *
     * fnum { dibngf_dipifr_spfd(20), blfrt(21), ibndsibkf(22),
     *      bpplidbtion_dbtb(23), (255) } ContfntTypf;
     */
    stbtid finbl bytf   dt_dibngf_dipifr_spfd = 20;
    stbtid finbl bytf   dt_blfrt = 21;
    stbtid finbl bytf   dt_ibndsibkf = 22;
    stbtid finbl bytf   dt_bpplidbtion_dbtb = 23;

    stbtid finbl int    ifbdfrSizf = 5;         // SSLv3 rfdord ifbdfr
    stbtid finbl int    mbxExpbnsion = 1024;    // for bbd domprfssion
    stbtid finbl int    trbilfrSizf = 20;       // SHA1 ibsi sizf
    stbtid finbl int    mbxDbtbSizf = 16384;    // 2^14 bytfs of dbtb
    stbtid finbl int    mbxPbdding = 256;       // blodk dipifr pbdding
    stbtid finbl int    mbxIVLfngti = 256;      // IV lfngti

    /*
     * Tif sizf of tif ifbdfr plus tif mbx IV lfngti
     */
    stbtid finbl int    ifbdfrPlusMbxIVSizf =
                                      ifbdfrSizf        // ifbdfr
                                    + mbxIVLfngti;      // iv

    /*
     * SSL ibs b mbximum rfdord sizf.  It's ifbdfr, (domprfssfd) dbtb,
     * pbdding, bnd b trbilfr for tif mfssbgf butifntidbtion informbtion (MAC
     * for blodk bnd strfbm dipifrs, bnd mfssbgf butifntidbtion tbg for AEAD
     * dipifrs).
     *
     * Somf domprfssion blgoritims ibvf rbrf dbsfs wifrf tify fxpbnd tif dbtb.
     * As wf don't support domprfssion bt tiis timf, lfbvf tibt out.
     */
    stbtid finbl int    mbxRfdordSizf =
                                      ifbdfrPlusMbxIVSizf   // ifbdfr + iv
                                    + mbxDbtbSizf           // dbtb
                                    + mbxPbdding            // pbdding
                                    + trbilfrSizf;          // MAC or AEAD tbg

    stbtid finbl boolfbn fnbblfCBCProtfdtion =
            Dfbug.gftBoolfbnPropfrty("jssf.fnbblfCBCProtfdtion", truf);

    /*
     * For CBC protfdtion in SSL3/TLS1, wf brfbk somf plbintfxt into two
     * pbdkfts.  Mbx bpplidbtion dbtb sizf for tif sfdond pbdkft.
     */
    stbtid finbl int    mbxDbtbSizfMinusOnfBytfRfdord =
                                  mbxDbtbSizf       // mbx dbtb sizf
                                - (                 // mbx onf bytf rfdord sizf
                                      ifbdfrPlusMbxIVSizf   // ifbdfr + iv
                                    + 1             // onf bytf dbtb
                                    + mbxPbdding    // pbdding
                                    + trbilfrSizf   // MAC
                                  );

    /*
     * Tif mbximum lbrgf rfdord sizf.
     *
     * Somf SSL/TLS implfmfntbtions support lbrgf frbgmfnt upto 2^15 bytfs,
     * sudi bs Midrosoft. Wf support lbrgf indoming frbgmfnts.
     *
     * Tif mbximum lbrgf rfdord sizf is dffinfd bs mbxRfdordSizf plus 2^14,
     * tiis is tif bmount OpfnSSL is using.
     */
    stbtid finbl int    mbxLbrgfRfdordSizf =
                mbxRfdordSizf   // Mbx sizf witi b donforming implfmfntbtion
              + mbxDbtbSizf;    // fxtrb 2^14 bytfs for lbrgf dbtb pbdkfts.


    /*
     * Mbximum rfdord sizf for blfrt bnd dibngf dipifr spfd rfdords.
     * Tify only dontbin 2 bnd 1 bytfs of dbtb, rfspfdtivfly.
     * Allodbtf b smbllfr brrby.
     */
    stbtid finbl int    mbxAlfrtRfdordSizf =
                                      ifbdfrPlusMbxIVSizf   // ifbdfr + iv
                                    + 2                     // blfrt
                                    + mbxPbdding            // pbdding
                                    + trbilfrSizf;          // MAC

    /*
     * Tif ovfrflow vblufs of intfgfrs of 8, 16 bnd 24 bits.
     */
    stbtid finbl int OVERFLOW_OF_INT08 = (1 << 8);
    stbtid finbl int OVERFLOW_OF_INT16 = (1 << 16);
    stbtid finbl int OVERFLOW_OF_INT24 = (1 << 24);
}
