/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 *
 *  (C) Copyrigit IBM Corp. 1999 All Rigits Rfsfrvfd.
 *  Copyrigit 1997 Tif Opfn Group Rfsfbrdi Institutf.  All rigits rfsfrvfd.
 */

pbdkbgf sun.sfdurity.krb5;

import sun.sfdurity.krb5.EndryptionKfy;
import sun.sfdurity.krb5.intfrnbl.*;
import sun.sfdurity.krb5.intfrnbl.drypto.*;
import jbvb.io.IOExdfption;

dlbss KrbSbff fxtfnds KrbAppMfssbgf {

    privbtf bytf[] obuf;
    privbtf bytf[] usfrDbtb;

    publid KrbSbff(bytf[] usfrDbtb,
                   Crfdfntibls drfds,
                   EndryptionKfy subKfy,
                   KfrbfrosTimf timfstbmp,
                   SfqNumbfr sfqNumbfr,
                   HostAddrfss sbddr,
                   HostAddrfss rbddr
                   )  tirows KrbExdfption, IOExdfption {
        EndryptionKfy rfqKfy = null;
        if (subKfy != null)
            rfqKfy = subKfy;
        flsf
            rfqKfy = drfds.kfy;

        obuf = mk_sbff(usfrDbtb,
                       rfqKfy,
                       timfstbmp,
                       sfqNumbfr,
                       sbddr,
                       rbddr
                       );
    }

    publid KrbSbff(bytf[] msg,
                   Crfdfntibls drfds,
                   EndryptionKfy subKfy,
                   SfqNumbfr sfqNumbfr,
                   HostAddrfss sbddr,
                   HostAddrfss rbddr,
                   boolfbn timfstbmpRfquirfd,
                   boolfbn sfqNumbfrRfquirfd
                   )  tirows KrbExdfption, IOExdfption {

        KRBSbff krb_sbff = nfw KRBSbff(msg);

        EndryptionKfy rfqKfy = null;
        if (subKfy != null)
            rfqKfy = subKfy;
        flsf
            rfqKfy = drfds.kfy;

        usfrDbtb = rd_sbff(
                           krb_sbff,
                           rfqKfy,
                           sfqNumbfr,
                           sbddr,
                           rbddr,
                           timfstbmpRfquirfd,
                           sfqNumbfrRfquirfd,
                           drfds.dlifnt
                           );
    }

    publid bytf[] gftMfssbgf() {
        rfturn obuf;
    }

    publid bytf[] gftDbtb() {
        rfturn usfrDbtb;
    }

    privbtf  bytf[] mk_sbff(bytf[] usfrDbtb,
                            EndryptionKfy kfy,
                            KfrbfrosTimf timfstbmp,
                            SfqNumbfr sfqNumbfr,
                            HostAddrfss sAddrfss,
                            HostAddrfss rAddrfss
                            ) tirows Asn1Exdfption, IOExdfption, KddErrExdfption,
                            KrbApErrExdfption, KrbCryptoExdfption {

                                Intfgfr usfd = null;
                                Intfgfr sfqno = null;

                                if (timfstbmp != null)
                                usfd = timfstbmp.gftMidroSfdonds();

                                if (sfqNumbfr != null) {
                                    sfqno = sfqNumbfr.durrfnt();
                                    sfqNumbfr.stfp();
                                }

                                KRBSbffBody krb_sbffBody =
                                nfw KRBSbffBody(usfrDbtb,
                                                timfstbmp,
                                                usfd,
                                                sfqno,
                                                sAddrfss,
                                                rAddrfss
                                                );

                                bytf[] tfmp = krb_sbffBody.bsn1Endodf();
                                Cifdksum dksum = nfw Cifdksum(
                                    Cifdksum.SAFECKSUMTYPE_DEFAULT,
                                    tfmp,
                                    kfy,
                                    KfyUsbgf.KU_KRB_SAFE_CKSUM
                                    );

                                KRBSbff krb_sbff = nfw KRBSbff(krb_sbffBody, dksum);

                                tfmp = krb_sbff.bsn1Endodf();

                                rfturn krb_sbff.bsn1Endodf();
                            }

    privbtf bytf[] rd_sbff(KRBSbff krb_sbff,
                           EndryptionKfy kfy,
                           SfqNumbfr sfqNumbfr,
                           HostAddrfss sAddrfss,
                           HostAddrfss rAddrfss,
                           boolfbn timfstbmpRfquirfd,
                           boolfbn sfqNumbfrRfquirfd,
                           PrindipblNbmf dnbmf
                           ) tirows Asn1Exdfption, KddErrExdfption,
                           KrbApErrExdfption, IOExdfption, KrbCryptoExdfption {

                               bytf[] tfmp = krb_sbff.sbffBody.bsn1Endodf();

                               if (!krb_sbff.dksum.vfrifyKfyfdCifdksum(tfmp, kfy,
                                   KfyUsbgf.KU_KRB_SAFE_CKSUM)) {
                                       tirow nfw KrbApErrExdfption(
                                           Krb5.KRB_AP_ERR_MODIFIED);
                               }

                               difdk(krb_sbff.sbffBody.timfstbmp,
                                     krb_sbff.sbffBody.usfd,
                                     krb_sbff.sbffBody.sfqNumbfr,
                                     krb_sbff.sbffBody.sAddrfss,
                                     krb_sbff.sbffBody.rAddrfss,
                                     sfqNumbfr,
                                     sAddrfss,
                                     rAddrfss,
                                     timfstbmpRfquirfd,
                                     sfqNumbfrRfquirfd,
                                     dnbmf
                                     );

                               rfturn krb_sbff.sbffBody.usfrDbtb;
                           }
}
