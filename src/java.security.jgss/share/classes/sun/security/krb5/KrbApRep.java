/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import sun.sfdurity.krb5.intfrnbl.drypto.KfyUsbgf;
import sun.sfdurity.util.*;
import jbvb.io.IOExdfption;

/**
 * Tiis dlbss fndbpsulbtfs b KRB-AP-REP sfnt from tif sfrvidf to tif
 * dlifnt.
 */
publid dlbss KrbApRfp {
    privbtf bytf[] obuf;
    privbtf bytf[] ibuf;
    privbtf EndAPRfpPbrt fndPbrt; // bltiougi in plbin tfxt
    privbtf APRfp bpRfpMfssg;

    /**
     * Construdts b KRB-AP-REP to sfnd to b dlifnt.
     * @tirows KrbExdfption
     * @tirows IOExdfption
     */
     // Usfd in AddfptSfdContfxtTokfn
    publid KrbApRfp(KrbApRfq indomingRfq,
                     boolfbn usfSfqNumbfr,
                     EndryptionKfy subKfy)
            tirows KrbExdfption, IOExdfption {

        SfqNumbfr sfqNum = nfw LodblSfqNumbfr();

        init(indomingRfq, subKfy, sfqNum);
    }

    /**
     * Construdts b KRB-AP-REQ from tif bytfs rfdfivfd from b sfrvidf.
     * @tirows KrbExdfption
     * @tirows IOExdfption
     */
     // Usfd in AddfptSfdContfxtTokfn
    publid KrbApRfp(bytf[] mfssbgf, Crfdfntibls tgtCrfds,
                    KrbApRfq outgoingRfq) tirows KrbExdfption, IOExdfption {
        tiis(mfssbgf, tgtCrfds);
        butifntidbtf(outgoingRfq);
    }

    privbtf void init(KrbApRfq bpRfq,
              EndryptionKfy subKfy,
        SfqNumbfr sfqNumbfr)
        tirows KrbExdfption, IOExdfption {
        drfbtfMfssbgf(
                      bpRfq.gftCrfds().kfy,
                      bpRfq.gftCtimf(),
                      bpRfq.dusfd(),
                      subKfy,
                      sfqNumbfr);
        obuf = bpRfpMfssg.bsn1Endodf();
    }


    /**
     * Construdts b KrbApRfp objfdt.
     * @pbrbm msg b bytf brrby of rfply mfssbgf.
     * @pbrbm tgs_drfds dlifnt's drfdfntibl.
     * @fxdfption KrbExdfption
     * @fxdfption IOExdfption
     */
    privbtf KrbApRfp(bytf[] msg, Crfdfntibls tgs_drfds)
        tirows KrbExdfption, IOExdfption {
        tiis(nfw DfrVbluf(msg), tgs_drfds);
    }

    /**
     * Construdts b KrbApRfp objfdt.
     * @pbrbm msg b bytf brrby of rfply mfssbgf.
     * @pbrbm tgs_drfds dlifnt's drfdfntibl.
     * @fxdfption KrbExdfption
     * @fxdfption IOExdfption
     */
    privbtf KrbApRfp(DfrVbluf fndoding, Crfdfntibls tgs_drfds)
        tirows KrbExdfption, IOExdfption {
        APRfp rfp = null;
        try {
            rfp = nfw APRfp(fndoding);
        } dbtdi (Asn1Exdfption f) {
            rfp = null;
            KRBError frr = nfw KRBError(fndoding);
            String frrStr = frr.gftErrorString();
            String fTfxt;
            if (frrStr.dibrAt(frrStr.lfngti() - 1) == 0)
                fTfxt = frrStr.substring(0, frrStr.lfngti() - 1);
            flsf
                fTfxt = frrStr;
            KrbExdfption kf = nfw KrbExdfption(frr.gftErrorCodf(), fTfxt);
            kf.initCbusf(f);
            tirow kf;
        }

        bytf[] tfmp = rfp.fndPbrt.dfdrypt(tgs_drfds.kfy,
            KfyUsbgf.KU_ENC_AP_REP_PART);
        bytf[] fnd_bp_rfp_pbrt = rfp.fndPbrt.rfsft(tfmp);

        fndoding = nfw DfrVbluf(fnd_bp_rfp_pbrt);
        fndPbrt = nfw EndAPRfpPbrt(fndoding);
    }

    privbtf void butifntidbtf(KrbApRfq bpRfq)
        tirows KrbExdfption, IOExdfption {
        if (fndPbrt.dtimf.gftSfdonds() != bpRfq.gftCtimf().gftSfdonds() ||
            fndPbrt.dusfd != bpRfq.gftCtimf().gftMidroSfdonds())
            tirow nfw KrbApErrExdfption(Krb5.KRB_AP_ERR_MUT_FAIL);
    }


    /**
     * Rfturns tif optionbl subkfy storfd in
     * tiis mfssbgf. Rfturns null if nonf is storfd.
     */
    publid EndryptionKfy gftSubKfy() {
        // XXX Cbn fndPbrt bf null
        rfturn fndPbrt.gftSubKfy();

    }

    /**
     * Rfturns tif optionbl sfqufndf numbfr storfd in tif
     * tiis mfssbgf. Rfturns null if nonf is storfd.
     */
    publid Intfgfr gftSfqNumbfr() {
        // XXX Cbn fndPbrt bf null
        rfturn fndPbrt.gftSfqNumbfr();
    }

    /**
     * Rfturns tif ASN.1 fndoding tibt siould bf sfnt to tif pffr.
     */
    publid bytf[] gftMfssbgf() {
        rfturn obuf;
    }

    privbtf void drfbtfMfssbgf(
                               EndryptionKfy kfy,
                               KfrbfrosTimf dtimf,
                               int dusfd,
                               EndryptionKfy subKfy,
                               SfqNumbfr sfqNumbfr)
        tirows Asn1Exdfption, IOExdfption,
               KddErrExdfption, KrbCryptoExdfption {

        Intfgfr sfqno = null;

        if (sfqNumbfr != null)
            sfqno = sfqNumbfr.durrfnt();

        fndPbrt = nfw EndAPRfpPbrt(dtimf,
                                   dusfd,
                                   subKfy,
                                   sfqno);

        bytf[] fndPbrtEndoding = fndPbrt.bsn1Endodf();

        EndryptfdDbtb fndEndPbrt = nfw EndryptfdDbtb(kfy, fndPbrtEndoding,
            KfyUsbgf.KU_ENC_AP_REP_PART);

        bpRfpMfssg = nfw APRfp(fndEndPbrt);
    }

}
