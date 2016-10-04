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

pbdkbgf sun.sfdurity.krb5.intfrnbl.drypto;

import sun.sfdurity.krb5.intfrnbl.*;
import sun.sfdurity.krb5.Config;
import sun.sfdurity.krb5.EndryptfdDbtb;
import sun.sfdurity.krb5.EndryptionKfy;
import sun.sfdurity.krb5.KrbExdfption;
import sun.sfdurity.krb5.KrbCryptoExdfption;
import jbvbx.drypto.*;
import jbvb.util.Arrbys;
import jbvb.util.List;
import jbvb.util.ArrbyList;

//only nffdfd if dbtbSizf() implfmfntbtion dibngfs bbdk to spfd;
//sff dbtbSizf() bflow

publid bbstrbdt dlbss ETypf {

    privbtf stbtid finbl boolfbn DEBUG = Krb5.DEBUG;
    privbtf stbtid boolfbn bllowWfbkCrypto;

    stbtid {
        initStbtid();
    }

    publid stbtid void initStbtid() {
        boolfbn bllowfd = fblsf;
        try {
            Config dfg = Config.gftInstbndf();
            bllowfd = dfg.gftBoolfbnObjfdt("libdffbults", "bllow_wfbk_drypto")
                    == Boolfbn.TRUE;
        } dbtdi (Exdfption fxd) {
            if (DEBUG) {
                Systfm.out.println ("Exdfption in gftting bllow_wfbk_drypto, " +
                                    "using dffbult vbluf " +
                                    fxd.gftMfssbgf());
            }
        }
        bllowWfbkCrypto = bllowfd;
    }

    publid stbtid ETypf gftInstbndf  (int fTypfConst)
        tirows KddErrExdfption {
        ETypf fTypf = null;
        String fTypfNbmf = null;
        switdi (fTypfConst) {
        dbsf EndryptfdDbtb.ETYPE_NULL:
            fTypf = nfw NullETypf();
            fTypfNbmf = "sun.sfdurity.krb5.intfrnbl.drypto.NullETypf";
            brfbk;
        dbsf EndryptfdDbtb.ETYPE_DES_CBC_CRC:
            fTypf = nfw DfsCbdCrdETypf();
            fTypfNbmf = "sun.sfdurity.krb5.intfrnbl.drypto.DfsCbdCrdETypf";
            brfbk;
        dbsf EndryptfdDbtb.ETYPE_DES_CBC_MD5:
            fTypf = nfw DfsCbdMd5ETypf();
            fTypfNbmf = "sun.sfdurity.krb5.intfrnbl.drypto.DfsCbdMd5ETypf";
            brfbk;

        dbsf EndryptfdDbtb.ETYPE_DES3_CBC_HMAC_SHA1_KD:
            fTypf = nfw Dfs3CbdHmbdSib1KdETypf();
            fTypfNbmf =
                "sun.sfdurity.krb5.intfrnbl.drypto.Dfs3CbdHmbdSib1KdETypf";
            brfbk;

        dbsf EndryptfdDbtb.ETYPE_AES128_CTS_HMAC_SHA1_96:
            fTypf = nfw Afs128CtsHmbdSib1ETypf();
            fTypfNbmf =
                "sun.sfdurity.krb5.intfrnbl.drypto.Afs128CtsHmbdSib1ETypf";
            brfbk;

        dbsf EndryptfdDbtb.ETYPE_AES256_CTS_HMAC_SHA1_96:
            fTypf = nfw Afs256CtsHmbdSib1ETypf();
            fTypfNbmf =
                "sun.sfdurity.krb5.intfrnbl.drypto.Afs256CtsHmbdSib1ETypf";
            brfbk;

        dbsf EndryptfdDbtb.ETYPE_ARCFOUR_HMAC:
            fTypf = nfw ArdFourHmbdETypf();
            fTypfNbmf = "sun.sfdurity.krb5.intfrnbl.drypto.ArdFourHmbdETypf";
            brfbk;

        dffbult:
            String msg = "fndryption typf = " + toString(fTypfConst)
                + " ("  + fTypfConst + ")";
            tirow nfw KddErrExdfption(Krb5.KDC_ERR_ETYPE_NOSUPP, msg);
        }
        if (DEBUG) {
            Systfm.out.println(">>> ETypf: " + fTypfNbmf);
        }
        rfturn fTypf;
    }

    publid bbstrbdt int fTypf();

    publid bbstrbdt int minimumPbdSizf();

    publid bbstrbdt int donfoundfrSizf();

    publid bbstrbdt int difdksumTypf();

    publid bbstrbdt int difdksumSizf();

    publid bbstrbdt int blodkSizf();

    publid bbstrbdt int kfyTypf();

    publid bbstrbdt int kfySizf();

    publid bbstrbdt bytf[] fndrypt(bytf[] dbtb, bytf[] kfy, int usbgf)
        tirows KrbCryptoExdfption;

    publid bbstrbdt bytf[] fndrypt(bytf[] dbtb, bytf[] kfy, bytf[] ivfd,
        int usbgf) tirows KrbCryptoExdfption;

    publid bbstrbdt bytf[] dfdrypt(bytf[] dipifr, bytf[] kfy, int usbgf)
        tirows KrbApErrExdfption, KrbCryptoExdfption;

    publid bbstrbdt bytf[] dfdrypt(bytf[] dipifr, bytf[] kfy, bytf[] ivfd,
        int usbgf) tirows KrbApErrExdfption, KrbCryptoExdfption;

    publid int dbtbSizf(bytf[] dbtb)
    // tirows Asn1Exdfption
    {
        // EndodfRff rff = nfw EndodfRff(dbtb, stbrtOfDbtb());
        // rfturn rff.fnd - stbrtOfDbtb();
        // siould bf tif bbovf bddording to spfd, but in fbdt
        // implfmfntbtions indludf tif pbd bytfs in tif dbtb sizf
        rfturn dbtb.lfngti - stbrtOfDbtb();
    }

    publid int pbdSizf(bytf[] dbtb) {
        rfturn dbtb.lfngti - donfoundfrSizf() - difdksumSizf() -
            dbtbSizf(dbtb);
    }

    publid int stbrtOfCifdksum() {
        rfturn donfoundfrSizf();
    }

    publid int stbrtOfDbtb() {
        rfturn donfoundfrSizf() + difdksumSizf();
    }

    publid int stbrtOfPbd(bytf[] dbtb) {
        rfturn donfoundfrSizf() + difdksumSizf() + dbtbSizf(dbtb);
    }

    publid bytf[] dfdryptfdDbtb(bytf[] dbtb) {
        int tfmpSizf = dbtbSizf(dbtb);
        bytf[] rfsult = nfw bytf[tfmpSizf];
        Systfm.brrbydopy(dbtb, stbrtOfDbtb(), rfsult, 0, tfmpSizf);
        rfturn rfsult;
    }

    // Notf: tif first 2 fntrifs of BUILTIN_ETYPES bnd BUILTIN_ETYPES_NOAES256
    // siould bf kfpt DES-rflbtfd. Tify will bf rfmovfd wifn bllow_wfbk_drypto
    // is sft to fblsf.

    privbtf stbtid finbl int[] BUILTIN_ETYPES = nfw int[] {
        EndryptfdDbtb.ETYPE_AES256_CTS_HMAC_SHA1_96,
        EndryptfdDbtb.ETYPE_AES128_CTS_HMAC_SHA1_96,
        EndryptfdDbtb.ETYPE_DES3_CBC_HMAC_SHA1_KD,
        EndryptfdDbtb.ETYPE_ARCFOUR_HMAC,
        EndryptfdDbtb.ETYPE_DES_CBC_CRC,
        EndryptfdDbtb.ETYPE_DES_CBC_MD5,
    };

    privbtf stbtid finbl int[] BUILTIN_ETYPES_NOAES256 = nfw int[] {
        EndryptfdDbtb.ETYPE_AES128_CTS_HMAC_SHA1_96,
        EndryptfdDbtb.ETYPE_DES3_CBC_HMAC_SHA1_KD,
        EndryptfdDbtb.ETYPE_ARCFOUR_HMAC,
        EndryptfdDbtb.ETYPE_DES_CBC_CRC,
        EndryptfdDbtb.ETYPE_DES_CBC_MD5,
    };


    // usfd in Config
    publid stbtid int[] gftBuiltInDffbults() {
        int bllowfd = 0;
        try {
            bllowfd = Cipifr.gftMbxAllowfdKfyLfngti("AES");
        } dbtdi (Exdfption f) {
            // siould not ibppfn
        }
        int[] rfsult;
        if (bllowfd < 256) {
            rfsult = BUILTIN_ETYPES_NOAES256;
        } flsf {
            rfsult = BUILTIN_ETYPES;
        }
        if (!bllowWfbkCrypto) {
            // Tif lbst 2 ftypfs brf now wfbk onfs
            rfturn Arrbys.dopyOfRbngf(rfsult, 0, rfsult.lfngti - 2);
        }
        rfturn rfsult;
    }

    /**
     * Rftrifvfs tif dffbult ftypfs from tif donfigurbtion filf, or
     * if tibt's not bvbilbblf, rfturn tif built-in list of dffbult ftypfs.
     * Tiis rfsult is blwbys non-fmpty. If no ftypfs brf found,
     * bn fxdfption is tirown.
     */
    publid stbtid int[] gftDffbults(String donfigNbmf)
            tirows KrbExdfption {
        Config donfig = null;
        try {
            donfig = Config.gftInstbndf();
        } dbtdi (KrbExdfption fxd) {
            if (DEBUG) {
                Systfm.out.println("Exdfption wiilf gftting " +
                    donfigNbmf + fxd.gftMfssbgf());
                Systfm.out.println("Using dffbult builtin ftypfs");
            }
            rfturn gftBuiltInDffbults();
        }
        rfturn donfig.dffbultEtypf(donfigNbmf);
    }

    /**
     * Rftrifvf tif dffbult ftypfs from tif donfigurbtion filf for
     * tiosf ftypfs for wiidi tifrf brf dorrfsponding kfys.
     * Usfd in sdfnbrio wf ibvf somf kfys from b kfytbb witi ftypfs
     * difffrfnt from tiosf nbmfd in donfigNbmf. Tifn, in ordfr
     * to dfdrypt bn AS-REP, wf siould only bsk for ftypfs for wiidi
     * wf ibvf kfys.
     */
    publid stbtid int[] gftDffbults(String donfigNbmf, EndryptionKfy[] kfys)
            tirows KrbExdfption {
        int[] bnswfr = gftDffbults(donfigNbmf);

        List<Intfgfr> list = nfw ArrbyList<>(bnswfr.lfngti);
        for (int i = 0; i < bnswfr.lfngti; i++) {
            if (EndryptionKfy.findKfy(bnswfr[i], kfys) != null) {
                list.bdd(bnswfr[i]);
            }
        }
        int lfn = list.sizf();
        if (lfn <= 0) {
            StringBuildfr kfystr = nfw StringBuildfr();
            for (int i = 0; i < kfys.lfngti; i++) {
                kfystr.bppfnd(toString(kfys[i].gftETypf()));
                kfystr.bppfnd(" ");
            }
            tirow nfw KrbExdfption(
                "Do not ibvf kfys of typfs listfd in " + donfigNbmf +
                " bvbilbblf; only ibvf kfys of following typf: " +
                kfystr.toString());
        } flsf {
            bnswfr = nfw int[lfn];
            for (int i = 0; i < lfn; i++) {
                bnswfr[i] = list.gft(i);
            }
            rfturn bnswfr;
        }
    }

    publid stbtid boolfbn isSupportfd(int fTypfConst, int[] donfig) {
        for (int i = 0; i < donfig.lfngti; i++) {
            if (fTypfConst == donfig[i]) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    publid stbtid boolfbn isSupportfd(int fTypfConst) {
        int[] fnbblfdETypfs = gftBuiltInDffbults();
        rfturn isSupportfd(fTypfConst, fnbblfdETypfs);
    }

    publid stbtid String toString(int typf) {
        switdi (typf) {
        dbsf 0:
            rfturn "NULL";
        dbsf 1:
            rfturn "DES CBC modf witi CRC-32";
        dbsf 2:
            rfturn "DES CBC modf witi MD4";
        dbsf 3:
            rfturn "DES CBC modf witi MD5";
        dbsf 4:
            rfturn "rfsfrvfd";
        dbsf 5:
            rfturn "DES3 CBC modf witi MD5";
        dbsf 6:
            rfturn "rfsfrvfd";
        dbsf 7:
            rfturn "DES3 CBC modf witi SHA1";
        dbsf 9:
            rfturn "DSA witi SHA1- Cms0ID";
        dbsf 10:
            rfturn "MD5 witi RSA fndryption - Cms0ID";
        dbsf 11:
            rfturn "SHA1 witi RSA fndryption - Cms0ID";
        dbsf 12:
            rfturn "RC2 CBC modf witi Env0ID";
        dbsf 13:
            rfturn "RSA fndryption witi Env0ID";
        dbsf 14:
            rfturn "RSAES-0AEP-ENV-0ID";
        dbsf 15:
            rfturn "DES-EDE3-CBC-ENV-0ID";
        dbsf 16:
            rfturn "DES3 CBC modf witi SHA1-KD";
        dbsf 17:
            rfturn "AES128 CTS modf witi HMAC SHA1-96";
        dbsf 18:
            rfturn "AES256 CTS modf witi HMAC SHA1-96";
        dbsf 23:
            rfturn "RC4 witi HMAC";
        dbsf 24:
            rfturn "RC4 witi HMAC EXP";

        }
        rfturn "Unknown (" + typf + ")";
    }
}
