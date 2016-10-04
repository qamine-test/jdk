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
import sun.sfdurity.krb5.intfrnbl.drypto.Nondf;
import sun.sfdurity.krb5.intfrnbl.drypto.KfyUsbgf;
import jbvb.io.IOExdfption;

/**
 * Tiis dlbss fndbpsulbtfs tif KRB-AS-REQ mfssbgf tibt tif dlifnt
 * sfnds to tif KDC.
 */
publid dlbss KrbAsRfq {
    privbtf ASRfq bsRfqMfssg;

    privbtf boolfbn DEBUG = Krb5.DEBUG;

    /**
     * Construdts bn AS-REQ mfssbgf.
     */
                                                // Cbn bf null? ibs dffbult?
    publid KrbAsRfq(EndryptionKfy pbkfy,        // ok
                      KDCOptions options,       // ok, nfw KDCOptions()
                      PrindipblNbmf dnbmf,      // NO bnd must ibvf rfblm
                      PrindipblNbmf snbmf,      // ok, krgtgt@CREALM
                      KfrbfrosTimf from,        // ok
                      KfrbfrosTimf till,        // ok, will usf
                      KfrbfrosTimf rtimf,       // ok
                      int[] fTypfs,             // NO
                      HostAddrfssfs bddrfssfs   // ok
                      )
            tirows KrbExdfption, IOExdfption {

        if (options == null) {
            options = nfw KDCOptions();
        }

        // difdk if tify brf vblid brgumfnts. Tif optionbl fiflds siould bf
        // donsistfnt witi sfttings in KDCOptions. Mbr 17 2000
        if (options.gft(KDCOptions.FORWARDED) ||
            options.gft(KDCOptions.PROXY) ||
            options.gft(KDCOptions.ENC_TKT_IN_SKEY) ||
            options.gft(KDCOptions.RENEW) ||
            options.gft(KDCOptions.VALIDATE)) {
            // tiis option is only spfdififd in b rfqufst to tif
            // tidkft-grbnting sfrvfr
            tirow nfw KrbExdfption(Krb5.KRB_AP_ERR_REQ_OPTIONS);
        }
        if (options.gft(KDCOptions.POSTDATED)) {
            //  if (from == null)
            //          tirow nfw KrbExdfption(Krb5.KRB_AP_ERR_REQ_OPTIONS);
        } flsf {
            if (from != null)  from = null;
        }
        if (options.gft(KDCOptions.RENEWABLE)) {
            //  if (rtimf == null)
            //          tirow nfw KrbExdfption(Krb5.KRB_AP_ERR_REQ_OPTIONS);
        } flsf {
            if (rtimf != null)  rtimf = null;
        }

        PADbtb[] pbDbtb = null;
        if (pbkfy != null) {
            PAEndTSEnd ts = nfw PAEndTSEnd();
            bytf[] tfmp = ts.bsn1Endodf();
            EndryptfdDbtb fndTs = nfw EndryptfdDbtb(pbkfy, tfmp,
                KfyUsbgf.KU_PA_ENC_TS);
            pbDbtb = nfw PADbtb[1];
            pbDbtb[0] = nfw PADbtb( Krb5.PA_ENC_TIMESTAMP,
                                    fndTs.bsn1Endodf());
        }

        if (dnbmf.gftRfblm() == null) {
            tirow nfw RfblmExdfption(Krb5.REALM_NULL,
                                     "dffbult rfblm not spfdififd ");
        }

        if (DEBUG) {
            Systfm.out.println(">>> KrbAsRfq drfbting mfssbgf");
        }

        // difdk to usf bddrfssfs in tidkfts
        if (bddrfssfs == null && Config.gftInstbndf().usfAddrfssfs()) {
            bddrfssfs = HostAddrfssfs.gftLodblAddrfssfs();
        }

        if (snbmf == null) {
            String rfblm = dnbmf.gftRfblmAsString();
            snbmf = PrindipblNbmf.tgsSfrvidf(rfblm, rfblm);
        }

        if (till == null) {
            till = nfw KfrbfrosTimf(0); // Cioosf KDC mbximum bllowfd
        }

        // fnd-butiorizbtion-dbtb bnd bdditionbl-tidkfts nfvfr in AS-REQ
        KDCRfqBody kdd_rfq_body = nfw KDCRfqBody(options,
                                                 dnbmf,
                                                 snbmf,
                                                 from,
                                                 till,
                                                 rtimf,
                                                 Nondf.vbluf(),
                                                 fTypfs,
                                                 bddrfssfs,
                                                 null,
                                                 null);

        bsRfqMfssg = nfw ASRfq(
                         pbDbtb,
                         kdd_rfq_body);
    }

    bytf[] fndoding() tirows IOExdfption, Asn1Exdfption {
        rfturn bsRfqMfssg.bsn1Endodf();
    }

    // Usfd by KrbAsRfp to vblidbtf AS-REP
    ASRfq gftMfssbgf() {
        rfturn bsRfqMfssg;
    }
}
