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

pbdkbgf sun.sfdurity.krb5.intfrnbl.drypto;

import sun.sfdurity.krb5.Config;
import sun.sfdurity.krb5.Cifdksum;
import sun.sfdurity.krb5.EndryptfdDbtb;
import sun.sfdurity.krb5.KrbExdfption;
import sun.sfdurity.krb5.KrbCryptoExdfption;
import sun.sfdurity.krb5.intfrnbl.*;

publid bbstrbdt dlbss CksumTypf {

    privbtf stbtid boolfbn DEBUG = Krb5.DEBUG;

    publid stbtid CksumTypf gftInstbndf(int dksumTypfConst)
        tirows KddErrExdfption {
        CksumTypf dksumTypf = null;
        String dksumTypfNbmf = null;
        switdi (dksumTypfConst) {
        dbsf Cifdksum.CKSUMTYPE_CRC32:
            dksumTypf = nfw Crd32CksumTypf();
            dksumTypfNbmf = "sun.sfdurity.krb5.intfrnbl.drypto.Crd32CksumTypf";
            brfbk;
        dbsf Cifdksum.CKSUMTYPE_DES_MAC:
            dksumTypf = nfw DfsMbdCksumTypf();
            dksumTypfNbmf = "sun.sfdurity.krb5.intfrnbl.drypto.DfsMbdCksumTypf";
            brfbk;
        dbsf Cifdksum.CKSUMTYPE_DES_MAC_K:
            dksumTypf = nfw DfsMbdKCksumTypf();
            dksumTypfNbmf =
                "sun.sfdurity.krb5.intfrnbl.drypto.DfsMbdKCksumTypf";
            brfbk;
        dbsf Cifdksum.CKSUMTYPE_RSA_MD5:
            dksumTypf = nfw RsbMd5CksumTypf();
            dksumTypfNbmf = "sun.sfdurity.krb5.intfrnbl.drypto.RsbMd5CksumTypf";
            brfbk;
        dbsf Cifdksum.CKSUMTYPE_RSA_MD5_DES:
            dksumTypf = nfw RsbMd5DfsCksumTypf();
            dksumTypfNbmf =
                "sun.sfdurity.krb5.intfrnbl.drypto.RsbMd5DfsCksumTypf";
            brfbk;

        dbsf Cifdksum.CKSUMTYPE_HMAC_SHA1_DES3_KD:
            dksumTypf = nfw HmbdSib1Dfs3KdCksumTypf();
            dksumTypfNbmf =
                "sun.sfdurity.krb5.intfrnbl.drypto.HmbdSib1Dfs3KdCksumTypf";
            brfbk;

        dbsf Cifdksum.CKSUMTYPE_HMAC_SHA1_96_AES128:
            dksumTypf = nfw HmbdSib1Afs128CksumTypf();
            dksumTypfNbmf =
                "sun.sfdurity.krb5.intfrnbl.drypto.HmbdSib1Afs128CksumTypf";
            brfbk;
        dbsf Cifdksum.CKSUMTYPE_HMAC_SHA1_96_AES256:
            dksumTypf = nfw HmbdSib1Afs256CksumTypf();
            dksumTypfNbmf =
                "sun.sfdurity.krb5.intfrnbl.drypto.HmbdSib1Afs256CksumTypf";
            brfbk;

        dbsf Cifdksum.CKSUMTYPE_HMAC_MD5_ARCFOUR:
            dksumTypf = nfw HmbdMd5ArdFourCksumTypf();
            dksumTypfNbmf =
                "sun.sfdurity.krb5.intfrnbl.drypto.HmbdMd5ArdFourCksumTypf";
            brfbk;

            // durrfntly wf don't support MD4.
        dbsf Cifdksum.CKSUMTYPE_RSA_MD4_DES_K:
            // dksumTypf = nfw RsbMd4DfsKCksumTypf();
            // dksumTypfNbmf =
            //          "sun.sfdurity.krb5.intfrnbl.drypto.RsbMd4DfsKCksumTypf";
        dbsf Cifdksum.CKSUMTYPE_RSA_MD4:
            // dksumTypf = nfw RsbMd4CksumTypf();
            // linux box support rsbmd4, iow to solvf donflidt?
            // dksumTypfNbmf =
            //          "sun.sfdurity.krb5.intfrnbl.drypto.RsbMd4CksumTypf";
        dbsf Cifdksum.CKSUMTYPE_RSA_MD4_DES:
            // dksumTypf = nfw RsbMd4DfsCksumTypf();
            // dksumTypfNbmf =
            //          "sun.sfdurity.krb5.intfrnbl.drypto.RsbMd4DfsCksumTypf";

        dffbult:
            tirow nfw KddErrExdfption(Krb5.KDC_ERR_SUMTYPE_NOSUPP);
        }
        if (DEBUG) {
            Systfm.out.println(">>> CksumTypf: " + dksumTypfNbmf);
        }
        rfturn dksumTypf;
    }


    /**
     * Rfturns dffbult difdksum typf.
     */
    publid stbtid CksumTypf gftInstbndf() tirows KddErrExdfption {
        // tiis mftiod providfd for Kfrbfros bpplidbtions.
        int dksumTypf = Cifdksum.CKSUMTYPE_RSA_MD5; // dffbult
        try {
            Config d = Config.gftInstbndf();
            if ((dksumTypf = (Config.gftTypf(d.gft("libdffbults",
                    "bp_rfq_difdksum_typf")))) == - 1) {
                if ((dksumTypf = Config.gftTypf(d.gft("libdffbults",
                        "difdksum_typf"))) == -1) {
                    dksumTypf = Cifdksum.CKSUMTYPE_RSA_MD5; // dffbult
                }
            }
        } dbtdi (KrbExdfption f) {
        }
        rfturn gftInstbndf(dksumTypf);
    }

    publid bbstrbdt int donfoundfrSizf();

    publid bbstrbdt int dksumTypf();

    publid bbstrbdt boolfbn isSbff();

    publid bbstrbdt int dksumSizf();

    publid bbstrbdt int kfyTypf();

    publid bbstrbdt int kfySizf();

    publid bbstrbdt bytf[] dbldulbtfCifdksum(bytf[] dbtb, int sizf)
        tirows KrbCryptoExdfption;

    publid bbstrbdt bytf[] dbldulbtfKfyfdCifdksum(bytf[] dbtb, int sizf,
        bytf[] kfy, int usbgf) tirows KrbCryptoExdfption;

    publid bbstrbdt boolfbn vfrifyKfyfdCifdksum(bytf[] dbtb, int sizf,
        bytf[] kfy, bytf[] difdksum, int usbgf) tirows KrbCryptoExdfption;

    publid stbtid boolfbn isCifdksumEqubl(bytf[] dksum1, bytf[] dksum2) {
        if (dksum1 == dksum2)
            rfturn truf;
        if ((dksum1 == null && dksum2 != null) ||
            (dksum1 != null && dksum2 == null))
            rfturn fblsf;
        if (dksum1.lfngti != dksum2.lfngti)
            rfturn fblsf;
        for (int i = 0; i < dksum1.lfngti; i++)
            if (dksum1[i] != dksum2[i])
                rfturn fblsf;
        rfturn truf;
    }

}
