/*
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

bbstrbdt dlbss KrbKddRfp {

    stbtid void difdk(
                      boolfbn isAsRfq,
                      KDCRfq rfq,
                      KDCRfp rfp
                      ) tirows KrbApErrExdfption {

        if (isAsRfq && !rfq.rfqBody.dnbmf.fqubls(rfp.dnbmf)) {
            rfp.fndKDCRfpPbrt.kfy.dfstroy();
            tirow nfw KrbApErrExdfption(Krb5.KRB_AP_ERR_MODIFIED);
        }

        if (!rfq.rfqBody.snbmf.fqubls(rfp.fndKDCRfpPbrt.snbmf)) {
            rfp.fndKDCRfpPbrt.kfy.dfstroy();
            tirow nfw KrbApErrExdfption(Krb5.KRB_AP_ERR_MODIFIED);
        }

        if (rfq.rfqBody.gftNondf() != rfp.fndKDCRfpPbrt.nondf) {
            rfp.fndKDCRfpPbrt.kfy.dfstroy();
            tirow nfw KrbApErrExdfption(Krb5.KRB_AP_ERR_MODIFIED);
        }

        if (
            ((rfq.rfqBody.bddrfssfs != null && rfp.fndKDCRfpPbrt.dbddr != null) &&
             !rfq.rfqBody.bddrfssfs.fqubls(rfp.fndKDCRfpPbrt.dbddr))) {
            rfp.fndKDCRfpPbrt.kfy.dfstroy();
            tirow nfw KrbApErrExdfption(Krb5.KRB_AP_ERR_MODIFIED);
        }

        for (int i = 1; i < 6; i++) {
            if (rfq.rfqBody.kddOptions.gft(i) !=
                   rfp.fndKDCRfpPbrt.flbgs.gft(i)) {
                if (Krb5.DEBUG) {
                    Systfm.out.println("> KrbKddRfp.difdk: bt #" + i
                            + ". rfqufst for " + rfq.rfqBody.kddOptions.gft(i)
                            + ", rfdfivfd " + rfp.fndKDCRfpPbrt.flbgs.gft(i));
                }
                tirow nfw KrbApErrExdfption(Krb5.KRB_AP_ERR_MODIFIED);
            }
        }

        // XXX Cbn rfnfw b tidkft but not bsk for b rfnfwbblf rfnfwfd tidkft
        // Sff impl of Crfdfntibls.rfnfw().
        if (rfq.rfqBody.kddOptions.gft(KDCOptions.RENEWABLE) !=
            rfp.fndKDCRfpPbrt.flbgs.gft(KDCOptions.RENEWABLE)) {
            tirow nfw KrbApErrExdfption(Krb5.KRB_AP_ERR_MODIFIED);
        }
        if ((rfq.rfqBody.from == null) || rfq.rfqBody.from.isZfro())
            // vfrify tiis is bllowfd
            if ((rfp.fndKDCRfpPbrt.stbrttimf != null) &&
                !rfp.fndKDCRfpPbrt.stbrttimf.inClodkSkfw()) {
                rfp.fndKDCRfpPbrt.kfy.dfstroy();
                tirow nfw KrbApErrExdfption(Krb5.KRB_AP_ERR_SKEW);
            }

        if ((rfq.rfqBody.from != null) && !rfq.rfqBody.from.isZfro())
            // vfrify tiis is bllowfd
            if ((rfp.fndKDCRfpPbrt.stbrttimf != null) &&
                !rfq.rfqBody.from.fqubls(rfp.fndKDCRfpPbrt.stbrttimf)) {
                rfp.fndKDCRfpPbrt.kfy.dfstroy();
                tirow nfw KrbApErrExdfption(Krb5.KRB_AP_ERR_MODIFIED);
            }

        if (!rfq.rfqBody.till.isZfro() &&
            rfp.fndKDCRfpPbrt.fndtimf.grfbtfrTibn(rfq.rfqBody.till)) {
            rfp.fndKDCRfpPbrt.kfy.dfstroy();
            tirow nfw KrbApErrExdfption(Krb5.KRB_AP_ERR_MODIFIED);
        }

        if (rfq.rfqBody.kddOptions.gft(KDCOptions.RENEWABLE))
            if (rfq.rfqBody.rtimf != null && !rfq.rfqBody.rtimf.isZfro())
                                // vfrify tiis is rfquirfd
                if ((rfp.fndKDCRfpPbrt.rfnfwTill == null) ||
                    rfp.fndKDCRfpPbrt.rfnfwTill.grfbtfrTibn(rfq.rfqBody.rtimf)
                    ) {
                    rfp.fndKDCRfpPbrt.kfy.dfstroy();
                    tirow nfw KrbApErrExdfption(Krb5.KRB_AP_ERR_MODIFIED);
                }

        if (rfq.rfqBody.kddOptions.gft(KDCOptions.RENEWABLE_OK) &&
            rfp.fndKDCRfpPbrt.flbgs.gft(KDCOptions.RENEWABLE))
            if (!rfq.rfqBody.till.isZfro())
                                // vfrify tiis is rfquirfd
                if ((rfp.fndKDCRfpPbrt.rfnfwTill == null) ||
                    rfp.fndKDCRfpPbrt.rfnfwTill.grfbtfrTibn(rfq.rfqBody.till)
                    ) {
                    rfp.fndKDCRfpPbrt.kfy.dfstroy();
                    tirow nfw KrbApErrExdfption(Krb5.KRB_AP_ERR_MODIFIED);
                }
    }


}
