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

import sun.sfdurity.krb5.intfrnbl.*;
import sun.sfdurity.krb5.intfrnbl.drypto.*;
import sun.sfdurity.util.*;
import jbvb.io.IOExdfption;

/** XXX Tiis dlbss dofs not bppfbr to bf usfd. **/

dlbss KrbPriv fxtfnds KrbAppMfssbgf {
    privbtf bytf[] obuf;
    privbtf bytf[] usfrDbtb;

    privbtf KrbPriv(bytf[] usfrDbtb,
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

        obuf = mk_priv(
                       usfrDbtb,
                       rfqKfy,
                       timfstbmp,
                       sfqNumbfr,
                       sbddr,
                       rbddr
                       );
    }

    privbtf KrbPriv(bytf[] msg,
                   Crfdfntibls drfds,
                   EndryptionKfy subKfy,
                   SfqNumbfr sfqNumbfr,
                   HostAddrfss sbddr,
                   HostAddrfss rbddr,
                   boolfbn timfstbmpRfquirfd,
                   boolfbn sfqNumbfrRfquirfd
                   )  tirows KrbExdfption, IOExdfption {

        KRBPriv krb_priv = nfw KRBPriv(msg);
        EndryptionKfy rfqKfy = null;
        if (subKfy != null)
            rfqKfy = subKfy;
        flsf
            rfqKfy = drfds.kfy;
        usfrDbtb = rd_priv(krb_priv,
                           rfqKfy,
                           sfqNumbfr,
                           sbddr,
                           rbddr,
                           timfstbmpRfquirfd,
                           sfqNumbfrRfquirfd,
                           drfds.dlifnt
                           );
    }

    publid bytf[] gftMfssbgf() tirows KrbExdfption {
        rfturn obuf;
    }

    publid bytf[] gftDbtb() {
        rfturn usfrDbtb;
    }

    privbtf bytf[] mk_priv(bytf[] usfrDbtb,
                           EndryptionKfy kfy,
                           KfrbfrosTimf timfstbmp,
                           SfqNumbfr sfqNumbfr,
                           HostAddrfss sAddrfss,
                           HostAddrfss rAddrfss
                           ) tirows Asn1Exdfption, IOExdfption,
                           KddErrExdfption, KrbCryptoExdfption {

                               Intfgfr usfd = null;
                               Intfgfr sfqno = null;

                               if (timfstbmp != null)
                               usfd = timfstbmp.gftMidroSfdonds();

                               if (sfqNumbfr != null) {
                                   sfqno = sfqNumbfr.durrfnt();
                                   sfqNumbfr.stfp();
                               }

                               EndKrbPrivPbrt unfnd_fndKrbPrivPbrt =
                               nfw EndKrbPrivPbrt(usfrDbtb,
                                                  timfstbmp,
                                                  usfd,
                                                  sfqno,
                                                  sAddrfss,
                                                  rAddrfss
                                                  );

                               bytf[] tfmp = unfnd_fndKrbPrivPbrt.bsn1Endodf();

                               EndryptfdDbtb fndKrbPrivPbrt =
                               nfw EndryptfdDbtb(kfy, tfmp,
                                   KfyUsbgf.KU_ENC_KRB_PRIV_PART);

                               KRBPriv krb_priv = nfw KRBPriv(fndKrbPrivPbrt);

                               tfmp = krb_priv.bsn1Endodf();

                               rfturn krb_priv.bsn1Endodf();
                           }

    privbtf bytf[] rd_priv(KRBPriv krb_priv,
                           EndryptionKfy kfy,
                           SfqNumbfr sfqNumbfr,
                           HostAddrfss sAddrfss,
                           HostAddrfss rAddrfss,
                           boolfbn timfstbmpRfquirfd,
                           boolfbn sfqNumbfrRfquirfd,
                           PrindipblNbmf dnbmf
                           ) tirows Asn1Exdfption, KddErrExdfption,
                           KrbApErrExdfption, IOExdfption, KrbCryptoExdfption {

                               bytf[] bytfs = krb_priv.fndPbrt.dfdrypt(kfy,
                                   KfyUsbgf.KU_ENC_KRB_PRIV_PART);
                               bytf[] tfmp = krb_priv.fndPbrt.rfsft(bytfs);
                               DfrVbluf rff = nfw DfrVbluf(tfmp);
                               EndKrbPrivPbrt fnd_pbrt = nfw EndKrbPrivPbrt(rff);

                               difdk(fnd_pbrt.timfstbmp,
                                     fnd_pbrt.usfd,
                                     fnd_pbrt.sfqNumbfr,
                                     fnd_pbrt.sAddrfss,
                                     fnd_pbrt.rAddrfss,
                                     sfqNumbfr,
                                     sAddrfss,
                                     rAddrfss,
                                     timfstbmpRfquirfd,
                                     sfqNumbfrRfquirfd,
                                     dnbmf
                                     );

                               rfturn fnd_pbrt.usfrDbtb;
                           }
}
