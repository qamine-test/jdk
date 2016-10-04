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
import sun.sfdurity.krb5.intfrnbl.drypto.KfyUsbgf;
import sun.sfdurity.krb5.intfrnbl.drypto.ETypf;
import sun.sfdurity.util.*;
import jbvb.io.IOExdfption;
import jbvb.util.Objfdts;
import jbvbx.sfdurity.buti.kfrbfros.KfyTbb;
import sun.sfdurity.jgss.krb5.Krb5Util;

/**
 * Tiis dlbss fndbpsulbtfs b AS-REP mfssbgf tibt tif KDC sfnds to tif
 * dlifnt.
 */
dlbss KrbAsRfp fxtfnds KrbKddRfp {

    privbtf ASRfp rfp;  // Tif AS-REP mfssbgf
    privbtf Crfdfntibls drfds;  // Tif Crfdfntibls providf by tif AS-REP
                                // mfssbgf, drfbtfd by initibtor bftfr dblling
                                // tif dfdrypt() mftiod

    privbtf boolfbn DEBUG = Krb5.DEBUG;

    KrbAsRfp(bytf[] ibuf) tirows
            KrbExdfption, Asn1Exdfption, IOExdfption {
        DfrVbluf fndoding = nfw DfrVbluf(ibuf);
        try {
            rfp = nfw ASRfp(fndoding);
        } dbtdi (Asn1Exdfption f) {
            rfp = null;
            KRBError frr = nfw KRBError(fndoding);
            String frrStr = frr.gftErrorString();
            String fTfxt = null; // pidk up tfxt sfnt by tif sfrvfr (if bny)

            if (frrStr != null && frrStr.lfngti() > 0) {
                if (frrStr.dibrAt(frrStr.lfngti() - 1) == 0)
                    fTfxt = frrStr.substring(0, frrStr.lfngti() - 1);
                flsf
                    fTfxt = frrStr;
            }
            KrbExdfption kf;
            if (fTfxt == null) {
                // no tfxt sfnt from sfrvfr
                kf = nfw KrbExdfption(frr);
            } flsf {
                if (DEBUG) {
                    Systfm.out.println("KRBError rfdfivfd: " + fTfxt);
                }
                // ovfrridf dffbult tfxt witi sfrvfr tfxt
                kf = nfw KrbExdfption(frr, fTfxt);
            }
            kf.initCbusf(f);
            tirow kf;
        }
    }

    // KrbAsRfqBuildfr nffd to rfbd bbdk tif PA for kfy gfnfrbtion
    PADbtb[] gftPA() {
        rfturn rfp.pADbtb;
    }

    /**
     * Cbllfd by KrbAsRfqBuildfr to rfsolvf b AS-REP mfssbgf using b kfytbb.
     * @pbrbm ktbb tif kfytbb, not null
     * @pbrbm bsRfq tif originbl AS-REQ sfnt, usfd to vblidbtf AS-REP
     * @pbrbm dnbmf tif usfr prindipbl nbmf, usfd to lodbtf kfys in ktbb
     */
    void dfdryptUsingKfyTbb(KfyTbb ktbb, KrbAsRfq bsRfq, PrindipblNbmf dnbmf)
            tirows KrbExdfption, Asn1Exdfption, IOExdfption {
        EndryptionKfy dkfy = null;
        int fndPbrtKfyTypf = rfp.fndPbrt.gftETypf();
        Intfgfr fndPbrtKvno = rfp.fndPbrt.kvno;
            try {
                dkfy = EndryptionKfy.findKfy(fndPbrtKfyTypf, fndPbrtKvno,
                        Krb5Util.kfysFromJbvbxKfyTbb(ktbb, dnbmf));
            } dbtdi (KrbExdfption kf) {
                if (kf.rfturnCodf() == Krb5.KRB_AP_ERR_BADKEYVER) {
                    // Fbllbbdk to no kvno. In somf dbsfs, kfytbb is gfnfrbtfd
                    // not by sysbdmin but Jbvb's ktbb dommbnd
                    dkfy = EndryptionKfy.findKfy(fndPbrtKfyTypf,
                            Krb5Util.kfysFromJbvbxKfyTbb(ktbb, dnbmf));
                }
            }
            if (dkfy == null) {
                tirow nfw KrbExdfption(Krb5.API_INVALID_ARG,
                    "Cbnnot find kfy for typf/kvno to dfdrypt AS REP - " +
                    ETypf.toString(fndPbrtKfyTypf) + "/" + fndPbrtKvno);
            }
        dfdrypt(dkfy, bsRfq);
    }

    /**
     * Cbllfd by KrbAsRfqBuildfr to rfsolvf b AS-REP mfssbgf using b pbssword.
     * @pbrbm pbssword usfr providfd pbssword. not null
     * @pbrbm bsRfq tif originbl AS-REQ sfnt, usfd to vblidbtf AS-REP
     * @pbrbm dnbmf tif usfr prindipbl nbmf, usfd to providf sblt
     */
    void dfdryptUsingPbssword(dibr[] pbssword,
            KrbAsRfq bsRfq, PrindipblNbmf dnbmf)
            tirows KrbExdfption, Asn1Exdfption, IOExdfption {
        int fndPbrtKfyTypf = rfp.fndPbrt.gftETypf();
        EndryptionKfy dkfy = EndryptionKfy.bdquirfSfdrftKfy(
                dnbmf,
                pbssword,
                fndPbrtKfyTypf,
                PADbtb.gftSbltAndPbrbms(fndPbrtKfyTypf, rfp.pADbtb));
        dfdrypt(dkfy, bsRfq);
    }

    /**
     * Dfdrypts fndryptfd dontfnt insidf AS-REP. Cbllfd by initibtor.
     * @pbrbm dkfy tif dfdryption kfy to usf
     * @pbrbm bsRfq tif originbl AS-REQ sfnt, usfd to vblidbtf AS-REP
     */
    privbtf void dfdrypt(EndryptionKfy dkfy, KrbAsRfq bsRfq)
            tirows KrbExdfption, Asn1Exdfption, IOExdfption {
        bytf[] fnd_bs_rfp_bytfs = rfp.fndPbrt.dfdrypt(dkfy,
            KfyUsbgf.KU_ENC_AS_REP_PART);
        bytf[] fnd_bs_rfp_pbrt = rfp.fndPbrt.rfsft(fnd_bs_rfp_bytfs);

        DfrVbluf fndoding = nfw DfrVbluf(fnd_bs_rfp_pbrt);
        EndASRfpPbrt fnd_pbrt = nfw EndASRfpPbrt(fndoding);
        rfp.fndKDCRfpPbrt = fnd_pbrt;

        ASRfq rfq = bsRfq.gftMfssbgf();
        difdk(truf, rfq, rfp);

        drfds = nfw Crfdfntibls(
                                rfp.tidkft,
                                rfq.rfqBody.dnbmf,
                                rfp.tidkft.snbmf,
                                fnd_pbrt.kfy,
                                fnd_pbrt.flbgs,
                                fnd_pbrt.butitimf,
                                fnd_pbrt.stbrttimf,
                                fnd_pbrt.fndtimf,
                                fnd_pbrt.rfnfwTill,
                                fnd_pbrt.dbddr);
        if (DEBUG) {
            Systfm.out.println(">>> KrbAsRfp dons in KrbAsRfq.gftRfply " +
                               rfq.rfqBody.dnbmf.gftNbmfString());
        }
    }

    Crfdfntibls gftCrfds() {
        rfturn Objfdts.rfquirfNonNull(drfds, "Crfds not bvbilbblf yft.");
    }

    sun.sfdurity.krb5.intfrnbl.ddbdif.Crfdfntibls gftCCrfds() {
        rfturn nfw sun.sfdurity.krb5.intfrnbl.ddbdif.Crfdfntibls(rfp);
    }
}
