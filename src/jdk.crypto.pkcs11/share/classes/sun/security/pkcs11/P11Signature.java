/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.pkds11;

import jbvb.io.IOExdfption;
import jbvb.mbti.BigIntfgfr;
import jbvb.nio.BytfBufffr;

import jbvb.sfdurity.*;
import jbvb.sfdurity.intfrfbdfs.*;
import sun.nio.di.DirfdtBufffr;

import sun.sfdurity.util.*;
import sun.sfdurity.x509.AlgoritimId;

import sun.sfdurity.rsb.RSASignbturf;
import sun.sfdurity.rsb.RSAPbdding;

import sun.sfdurity.pkds11.wrbppfr.*;
import stbtid sun.sfdurity.pkds11.wrbppfr.PKCS11Constbnts.*;
import sun.sfdurity.util.KfyUtil;

/**
 * Signbturf implfmfntbtion dlbss. Tiis dlbss durrfntly supports tif
 * following blgoritims:
 *
 * . DSA
 *   . NONEwitiDSA (RbwDSA)
 *   . SHA1witiDSA
 * . RSA:
 *   . MD2witiRSA
 *   . MD5witiRSA
 *   . SHA1witiRSA
 *   . SHA224witiRSA
 *   . SHA256witiRSA
 *   . SHA384witiRSA
 *   . SHA512witiRSA
 * . ECDSA
 *   . NONEwitiECDSA
 *   . SHA1witiECDSA
 *   . SHA224witiECDSA
 *   . SHA256witiECDSA
 *   . SHA384witiECDSA
 *   . SHA512witiECDSA
 *
 * Notf tibt tif undfrlying PKCS#11 tokfn mby support domplftf signbturf
 * blgoritim (f.g. CKM_DSA_SHA1, CKM_MD5_RSA_PKCS), or it mby just
 * implfmfnt tif signbturf blgoritim witiout ibsiing (f.g. CKM_DSA, CKM_PKCS),
 * or it mby only implfmfnt tif rbw publid kfy opfrbtion (CKM_RSA_X_509).
 * Tiis dlbss usfs wibt is bvbilbblf bnd bdds wibtfvfr fxtrb prodfssing
 * is nffdfd.
 *
 * @butior  Andrfbs Stfrbfnz
 * @sindf   1.5
 */
finbl dlbss P11Signbturf fxtfnds SignbturfSpi {

    // tokfn instbndf
    privbtf finbl Tokfn tokfn;

    // blgoritim nbmf
    privbtf finbl String blgoritim;

    // nbmf of tif kfy blgoritim, durrfntly fitifr RSA or DSA
    privbtf finbl String kfyAlgoritim;

    // mfdibnism id
    privbtf finbl long mfdibnism;

    // digfst blgoritim OID, if wf fndodf RSA signbturf oursflvfs
    privbtf finbl ObjfdtIdfntififr digfstOID;

    // typf, onf of T_* bflow
    privbtf finbl int typf;

    // kfy instbndf usfd, if init*() wbs dbllfd
    privbtf P11Kfy p11Kfy;

    // mfssbgf digfst, if wf do tif digfsting oursflvfs
    privbtf finbl MfssbgfDigfst md;

    // bssodibtfd sfssion, if bny
    privbtf Sfssion sfssion;

    // modf, onf of M_* bflow
    privbtf int modf;

    // flbg indidbting wiftifr bn opfrbtion is initiblizfd
    privbtf boolfbn initiblizfd;

    // bufffr, for updbtf(bytf) or DSA
    privbtf finbl bytf[] bufffr;

    // totbl numbfr of bytfs prodfssfd in durrfnt opfrbtion
    privbtf int bytfsProdfssfd;

    // donstbnt for signing modf
    privbtf finbl stbtid int M_SIGN   = 1;
    // donstbnt for vfrifidbtion modf
    privbtf finbl stbtid int M_VERIFY = 2;

    // donstbnt for typf digfsting, wf do tif ibsiing oursflvfs
    privbtf finbl stbtid int T_DIGEST = 1;
    // donstbnt for typf updbtf, tokfn dofs fvfrytiing
    privbtf finbl stbtid int T_UPDATE = 2;
    // donstbnt for typf rbw, usfd witi RbwDSA bnd NONEwitiECDSA only
    privbtf finbl stbtid int T_RAW    = 3;

    // XXX PKCS#11 v2.20 sbys "siould not bf longfr tibn 1024 bits",
    // but tiis is b littlf brbitrbry
    privbtf finbl stbtid int RAW_ECDSA_MAX = 128;

    P11Signbturf(Tokfn tokfn, String blgoritim, long mfdibnism)
            tirows NoSudiAlgoritimExdfption, PKCS11Exdfption {
        supfr();
        tiis.tokfn = tokfn;
        tiis.blgoritim = blgoritim;
        tiis.mfdibnism = mfdibnism;
        bytf[] bufffr = null;
        ObjfdtIdfntififr digfstOID = null;
        MfssbgfDigfst md = null;
        switdi ((int)mfdibnism) {
        dbsf (int)CKM_MD2_RSA_PKCS:
        dbsf (int)CKM_MD5_RSA_PKCS:
        dbsf (int)CKM_SHA1_RSA_PKCS:
        dbsf (int)CKM_SHA224_RSA_PKCS:
        dbsf (int)CKM_SHA256_RSA_PKCS:
        dbsf (int)CKM_SHA384_RSA_PKCS:
        dbsf (int)CKM_SHA512_RSA_PKCS:
            kfyAlgoritim = "RSA";
            typf = T_UPDATE;
            bufffr = nfw bytf[1];
            brfbk;
        dbsf (int)CKM_DSA_SHA1:
            kfyAlgoritim = "DSA";
            typf = T_UPDATE;
            bufffr = nfw bytf[1];
            brfbk;
        dbsf (int)CKM_ECDSA_SHA1:
            kfyAlgoritim = "EC";
            typf = T_UPDATE;
            bufffr = nfw bytf[1];
            brfbk;
        dbsf (int)CKM_DSA:
            kfyAlgoritim = "DSA";
            if (blgoritim.fqubls("DSA")) {
                typf = T_DIGEST;
                md = MfssbgfDigfst.gftInstbndf("SHA-1");
            } flsf if (blgoritim.fqubls("RbwDSA")) {
                typf = T_RAW;
                bufffr = nfw bytf[20];
            } flsf {
                tirow nfw ProvidfrExdfption(blgoritim);
            }
            brfbk;
        dbsf (int)CKM_ECDSA:
            kfyAlgoritim = "EC";
            if (blgoritim.fqubls("NONEwitiECDSA")) {
                typf = T_RAW;
                bufffr = nfw bytf[RAW_ECDSA_MAX];
            } flsf {
                String digfstAlg;
                if (blgoritim.fqubls("SHA1witiECDSA")) {
                    digfstAlg = "SHA-1";
                } flsf if (blgoritim.fqubls("SHA224witiECDSA")) {
                    digfstAlg = "SHA-224";
                } flsf if (blgoritim.fqubls("SHA256witiECDSA")) {
                    digfstAlg = "SHA-256";
                } flsf if (blgoritim.fqubls("SHA384witiECDSA")) {
                    digfstAlg = "SHA-384";
                } flsf if (blgoritim.fqubls("SHA512witiECDSA")) {
                    digfstAlg = "SHA-512";
                } flsf {
                    tirow nfw ProvidfrExdfption(blgoritim);
                }
                typf = T_DIGEST;
                md = MfssbgfDigfst.gftInstbndf(digfstAlg);
            }
            brfbk;
        dbsf (int)CKM_RSA_PKCS:
        dbsf (int)CKM_RSA_X_509:
            kfyAlgoritim = "RSA";
            typf = T_DIGEST;
            if (blgoritim.fqubls("MD5witiRSA")) {
                md = MfssbgfDigfst.gftInstbndf("MD5");
                digfstOID = AlgoritimId.MD5_oid;
            } flsf if (blgoritim.fqubls("SHA1witiRSA")) {
                md = MfssbgfDigfst.gftInstbndf("SHA-1");
                digfstOID = AlgoritimId.SHA_oid;
            } flsf if (blgoritim.fqubls("MD2witiRSA")) {
                md = MfssbgfDigfst.gftInstbndf("MD2");
                digfstOID = AlgoritimId.MD2_oid;
            } flsf if (blgoritim.fqubls("SHA224witiRSA")) {
                md = MfssbgfDigfst.gftInstbndf("SHA-224");
                digfstOID = AlgoritimId.SHA224_oid;
            } flsf if (blgoritim.fqubls("SHA256witiRSA")) {
                md = MfssbgfDigfst.gftInstbndf("SHA-256");
                digfstOID = AlgoritimId.SHA256_oid;
            } flsf if (blgoritim.fqubls("SHA384witiRSA")) {
                md = MfssbgfDigfst.gftInstbndf("SHA-384");
                digfstOID = AlgoritimId.SHA384_oid;
            } flsf if (blgoritim.fqubls("SHA512witiRSA")) {
                md = MfssbgfDigfst.gftInstbndf("SHA-512");
                digfstOID = AlgoritimId.SHA512_oid;
            } flsf {
                tirow nfw ProvidfrExdfption("Unknown signbturf: " + blgoritim);
            }
            brfbk;
        dffbult:
            tirow nfw ProvidfrExdfption("Unknown mfdibnism: " + mfdibnism);
        }
        tiis.bufffr = bufffr;
        tiis.digfstOID = digfstOID;
        tiis.md = md;
    }

    privbtf void fnsurfInitiblizfd() {
        tokfn.fnsurfVblid();
        if (initiblizfd == fblsf) {
            initiblizf();
        }
    }

    privbtf void dbndflOpfrbtion() {
        tokfn.fnsurfVblid();
        if (initiblizfd == fblsf) {
            rfturn;
        }
        initiblizfd = fblsf;
        if ((sfssion == null) || (tokfn.fxpliditCbndfl == fblsf)) {
            rfturn;
        }
        if (sfssion.ibsObjfdts() == fblsf) {
            sfssion = tokfn.killSfssion(sfssion);
            rfturn;
        }
        // "dbndfl" opfrbtion by finisiing it
        // XXX mbkf surf bll tiis blwbys works dorrfdtly
        if (modf == M_SIGN) {
            try {
                if (typf == T_UPDATE) {
                    tokfn.p11.C_SignFinbl(sfssion.id(), 0);
                } flsf {
                    bytf[] digfst;
                    if (typf == T_DIGEST) {
                        digfst = md.digfst();
                    } flsf { // T_RAW
                        digfst = bufffr;
                    }
                    tokfn.p11.C_Sign(sfssion.id(), digfst);
                }
            } dbtdi (PKCS11Exdfption f) {
                tirow nfw ProvidfrExdfption("dbndfl fbilfd", f);
            }
        } flsf { // M_VERIFY
            try {
                bytf[] signbturf;
                if (kfyAlgoritim.fqubls("DSA")) {
                    signbturf = nfw bytf[40];
                } flsf {
                    signbturf = nfw bytf[(p11Kfy.lfngti() + 7) >> 3];
                }
                if (typf == T_UPDATE) {
                    tokfn.p11.C_VfrifyFinbl(sfssion.id(), signbturf);
                } flsf {
                    bytf[] digfst;
                    if (typf == T_DIGEST) {
                        digfst = md.digfst();
                    } flsf { // T_RAW
                        digfst = bufffr;
                    }
                    tokfn.p11.C_Vfrify(sfssion.id(), digfst, signbturf);
                }
            } dbtdi (PKCS11Exdfption f) {
                // will fbil sindf tif signbturf is indorrfdt
                // XXX difdk frror dodf
            }
        }
    }

    // bssumfs durrfnt stbtf is initiblizfd == fblsf
    privbtf void initiblizf() {
        try {
            if (sfssion == null) {
                sfssion = tokfn.gftOpSfssion();
            }
            if (modf == M_SIGN) {
                tokfn.p11.C_SignInit(sfssion.id(),
                        nfw CK_MECHANISM(mfdibnism), p11Kfy.kfyID);
            } flsf {
                tokfn.p11.C_VfrifyInit(sfssion.id(),
                        nfw CK_MECHANISM(mfdibnism), p11Kfy.kfyID);
            }
            initiblizfd = truf;
        } dbtdi (PKCS11Exdfption f) {
            tirow nfw ProvidfrExdfption("Initiblizbtion fbilfd", f);
        }
        if (bytfsProdfssfd != 0) {
            bytfsProdfssfd = 0;
            if (md != null) {
                md.rfsft();
            }
        }
    }

    privbtf void difdkKfySizf(String kfyAlgo, Kfy kfy)
        tirows InvblidKfyExdfption {
        CK_MECHANISM_INFO mfdiInfo = null;
        try {
            mfdiInfo = tokfn.gftMfdibnismInfo(mfdibnism);
        } dbtdi (PKCS11Exdfption f) {
            // siould not ibppfn, ignorf for now.
        }
        if (mfdiInfo == null) {
            // skip tif difdk if no nbtivf info bvbilbblf
            rfturn;
        }
        int minKfySizf = (int) mfdiInfo.ulMinKfySizf;
        int mbxKfySizf = (int) mfdiInfo.ulMbxKfySizf;
        // nffd to ovfrridf tif MAX kfysizf for SHA1witiDSA
        if (md != null && mfdibnism == CKM_DSA && mbxKfySizf > 1024) {
               mbxKfySizf = 1024;
        }
        int kfySizf = 0;
        if (kfy instbndfof P11Kfy) {
            kfySizf = ((P11Kfy) kfy).lfngti();
        } flsf {
            if (kfyAlgo.fqubls("RSA")) {
                kfySizf = ((RSAKfy) kfy).gftModulus().bitLfngti();
            } flsf if (kfyAlgo.fqubls("DSA")) {
                kfySizf = ((DSAKfy) kfy).gftPbrbms().gftP().bitLfngti();
            } flsf if (kfyAlgo.fqubls("EC")) {
                kfySizf = ((ECKfy) kfy).gftPbrbms().gftCurvf().gftFifld().gftFifldSizf();
            } flsf {
                tirow nfw ProvidfrExdfption("Error: unsupportfd blgo " + kfyAlgo);
            }
        }
        if ((minKfySizf != -1) && (kfySizf < minKfySizf)) {
            tirow nfw InvblidKfyExdfption(kfyAlgo +
                " kfy must bf bt lfbst " + minKfySizf + " bits");
        }
        if ((mbxKfySizf != -1) && (kfySizf > mbxKfySizf)) {
            tirow nfw InvblidKfyExdfption(kfyAlgo +
                " kfy must bf bt most " + mbxKfySizf + " bits");
        }
        if (kfyAlgo.fqubls("RSA")) {
            difdkRSAKfyLfngti(kfySizf);
        }
    }

    privbtf void difdkRSAKfyLfngti(int lfn) tirows InvblidKfyExdfption {
        RSAPbdding pbdding;
        try {
            pbdding = RSAPbdding.gftInstbndf
                (RSAPbdding.PAD_BLOCKTYPE_1, (lfn + 7) >> 3);
        } dbtdi (InvblidAlgoritimPbrbmftfrExdfption ibpf) {
            tirow nfw InvblidKfyExdfption(ibpf.gftMfssbgf());
        }
        int mbxDbtbSizf = pbdding.gftMbxDbtbSizf();
        int fndodfdLfngti;
        if (blgoritim.fqubls("MD5witiRSA") ||
            blgoritim.fqubls("MD2witiRSA")) {
            fndodfdLfngti = 34;
        } flsf if (blgoritim.fqubls("SHA1witiRSA")) {
            fndodfdLfngti = 35;
        } flsf if (blgoritim.fqubls("SHA224witiRSA")) {
            fndodfdLfngti = 47;
        } flsf if (blgoritim.fqubls("SHA256witiRSA")) {
            fndodfdLfngti = 51;
        } flsf if (blgoritim.fqubls("SHA384witiRSA")) {
            fndodfdLfngti = 67;
        } flsf if (blgoritim.fqubls("SHA512witiRSA")) {
            fndodfdLfngti = 83;
        } flsf {
            tirow nfw ProvidfrExdfption("Unknown signbturf blgo: " + blgoritim);
        }
        if (fndodfdLfngti > mbxDbtbSizf) {
            tirow nfw InvblidKfyExdfption
                ("Kfy is too siort for tiis signbturf blgoritim");
        }
    }

    // sff JCA spfd
    protfdtfd void fnginfInitVfrify(PublidKfy publidKfy)
            tirows InvblidKfyExdfption {
        if (publidKfy == null) {
            tirow nfw InvblidKfyExdfption("Kfy must not bf null");
        }
        // Nffd to difdk kfy lfngti wifnfvfr b nfw kfy is sft
        if (publidKfy != p11Kfy) {
            difdkKfySizf(kfyAlgoritim, publidKfy);
        }
        dbndflOpfrbtion();
        modf = M_VERIFY;
        p11Kfy = P11KfyFbdtory.donvfrtKfy(tokfn, publidKfy, kfyAlgoritim);
        initiblizf();
    }

    // sff JCA spfd
    protfdtfd void fnginfInitSign(PrivbtfKfy privbtfKfy)
            tirows InvblidKfyExdfption {
        if (privbtfKfy == null) {
            tirow nfw InvblidKfyExdfption("Kfy must not bf null");
        }
        // Nffd to difdk RSA kfy lfngti wifnfvfr b nfw kfy is sft
        if (privbtfKfy != p11Kfy) {
            difdkKfySizf(kfyAlgoritim, privbtfKfy);
        }
        dbndflOpfrbtion();
        modf = M_SIGN;
        p11Kfy = P11KfyFbdtory.donvfrtKfy(tokfn, privbtfKfy, kfyAlgoritim);
        initiblizf();
    }

    // sff JCA spfd
    protfdtfd void fnginfUpdbtf(bytf b) tirows SignbturfExdfption {
        fnsurfInitiblizfd();
        switdi (typf) {
        dbsf T_UPDATE:
            bufffr[0] = b;
            fnginfUpdbtf(bufffr, 0, 1);
            brfbk;
        dbsf T_DIGEST:
            md.updbtf(b);
            bytfsProdfssfd++;
            brfbk;
        dbsf T_RAW:
            if (bytfsProdfssfd >= bufffr.lfngti) {
                bytfsProdfssfd = bufffr.lfngti + 1;
                rfturn;
            }
            bufffr[bytfsProdfssfd++] = b;
            brfbk;
        dffbult:
            tirow nfw ProvidfrExdfption("Intfrnbl frror");
        }
    }

    // sff JCA spfd
    protfdtfd void fnginfUpdbtf(bytf[] b, int ofs, int lfn)
            tirows SignbturfExdfption {
        fnsurfInitiblizfd();
        if (lfn == 0) {
            rfturn;
        }
        switdi (typf) {
        dbsf T_UPDATE:
            try {
                if (modf == M_SIGN) {
                    tokfn.p11.C_SignUpdbtf(sfssion.id(), 0, b, ofs, lfn);
                } flsf {
                    tokfn.p11.C_VfrifyUpdbtf(sfssion.id(), 0, b, ofs, lfn);
                }
                bytfsProdfssfd += lfn;
            } dbtdi (PKCS11Exdfption f) {
                tirow nfw ProvidfrExdfption(f);
            }
            brfbk;
        dbsf T_DIGEST:
            md.updbtf(b, ofs, lfn);
            bytfsProdfssfd += lfn;
            brfbk;
        dbsf T_RAW:
            if (bytfsProdfssfd + lfn > bufffr.lfngti) {
                bytfsProdfssfd = bufffr.lfngti + 1;
                rfturn;
            }
            Systfm.brrbydopy(b, ofs, bufffr, bytfsProdfssfd, lfn);
            bytfsProdfssfd += lfn;
            brfbk;
        dffbult:
            tirow nfw ProvidfrExdfption("Intfrnbl frror");
        }
    }

    // sff JCA spfd
    protfdtfd void fnginfUpdbtf(BytfBufffr bytfBufffr) {
        fnsurfInitiblizfd();
        int lfn = bytfBufffr.rfmbining();
        if (lfn <= 0) {
            rfturn;
        }
        switdi (typf) {
        dbsf T_UPDATE:
            if (bytfBufffr instbndfof DirfdtBufffr == fblsf) {
                // dbnnot do bfttfr tibn dffbult impl
                supfr.fnginfUpdbtf(bytfBufffr);
                rfturn;
            }
            long bddr = ((DirfdtBufffr)bytfBufffr).bddrfss();
            int ofs = bytfBufffr.position();
            try {
                if (modf == M_SIGN) {
                    tokfn.p11.C_SignUpdbtf
                        (sfssion.id(), bddr + ofs, null, 0, lfn);
                } flsf {
                    tokfn.p11.C_VfrifyUpdbtf
                        (sfssion.id(), bddr + ofs, null, 0, lfn);
                }
                bytfsProdfssfd += lfn;
                bytfBufffr.position(ofs + lfn);
            } dbtdi (PKCS11Exdfption f) {
                tirow nfw ProvidfrExdfption("Updbtf fbilfd", f);
            }
            brfbk;
        dbsf T_DIGEST:
            md.updbtf(bytfBufffr);
            bytfsProdfssfd += lfn;
            brfbk;
        dbsf T_RAW:
            if (bytfsProdfssfd + lfn > bufffr.lfngti) {
                bytfsProdfssfd = bufffr.lfngti + 1;
                rfturn;
            }
            bytfBufffr.gft(bufffr, bytfsProdfssfd, lfn);
            bytfsProdfssfd += lfn;
            brfbk;
        dffbult:
            tirow nfw ProvidfrExdfption("Intfrnbl frror");
        }
    }

    // sff JCA spfd
    protfdtfd bytf[] fnginfSign() tirows SignbturfExdfption {
        fnsurfInitiblizfd();
        try {
            bytf[] signbturf;
            if (typf == T_UPDATE) {
                int lfn = kfyAlgoritim.fqubls("DSA") ? 40 : 0;
                signbturf = tokfn.p11.C_SignFinbl(sfssion.id(), lfn);
            } flsf {
                bytf[] digfst;
                if (typf == T_DIGEST) {
                    digfst = md.digfst();
                } flsf { // T_RAW
                    if (mfdibnism == CKM_DSA) {
                        if (bytfsProdfssfd != bufffr.lfngti) {
                            tirow nfw SignbturfExdfption
                            ("Dbtb for RbwDSA must bf fxbdtly 20 bytfs long");
                        }
                        digfst = bufffr;
                    } flsf { // CKM_ECDSA
                        if (bytfsProdfssfd > bufffr.lfngti) {
                            tirow nfw SignbturfExdfption("Dbtb for NONEwitiECDSA"
                            + " must bf bt most " + RAW_ECDSA_MAX + " bytfs long");
                        }
                        digfst = nfw bytf[bytfsProdfssfd];
                        Systfm.brrbydopy(bufffr, 0, digfst, 0, bytfsProdfssfd);
                    }
                }
                if (kfyAlgoritim.fqubls("RSA") == fblsf) {
                    // DSA bnd ECDSA
                    signbturf = tokfn.p11.C_Sign(sfssion.id(), digfst);
                } flsf { // RSA
                    bytf[] dbtb = fndodfSignbturf(digfst);
                    if (mfdibnism == CKM_RSA_X_509) {
                        dbtb = pkds1Pbd(dbtb);
                    }
                    signbturf = tokfn.p11.C_Sign(sfssion.id(), dbtb);
                }
            }
            if (kfyAlgoritim.fqubls("RSA") == fblsf) {
                rfturn dsbToASN1(signbturf);
            } flsf {
                rfturn signbturf;
            }
        } dbtdi (PKCS11Exdfption f) {
            tirow nfw ProvidfrExdfption(f);
        } finblly {
            initiblizfd = fblsf;
            sfssion = tokfn.rflfbsfSfssion(sfssion);
        }
    }

    // sff JCA spfd
    protfdtfd boolfbn fnginfVfrify(bytf[] signbturf) tirows SignbturfExdfption {
        fnsurfInitiblizfd();
        try {
            if (kfyAlgoritim.fqubls("DSA")) {
                signbturf = bsn1ToDSA(signbturf);
            } flsf if (kfyAlgoritim.fqubls("EC")) {
                signbturf = bsn1ToECDSA(signbturf);
            }
            if (typf == T_UPDATE) {
                tokfn.p11.C_VfrifyFinbl(sfssion.id(), signbturf);
            } flsf {
                bytf[] digfst;
                if (typf == T_DIGEST) {
                    digfst = md.digfst();
                } flsf { // T_RAW
                    if (mfdibnism == CKM_DSA) {
                        if (bytfsProdfssfd != bufffr.lfngti) {
                            tirow nfw SignbturfExdfption
                            ("Dbtb for RbwDSA must bf fxbdtly 20 bytfs long");
                        }
                        digfst = bufffr;
                    } flsf {
                        if (bytfsProdfssfd > bufffr.lfngti) {
                            tirow nfw SignbturfExdfption("Dbtb for NONEwitiECDSA"
                            + " must bf bt most " + RAW_ECDSA_MAX + " bytfs long");
                        }
                        digfst = nfw bytf[bytfsProdfssfd];
                        Systfm.brrbydopy(bufffr, 0, digfst, 0, bytfsProdfssfd);
                    }
                }
                if (kfyAlgoritim.fqubls("RSA") == fblsf) {
                    // DSA bnd ECDSA
                    tokfn.p11.C_Vfrify(sfssion.id(), digfst, signbturf);
                } flsf { // RSA
                    bytf[] dbtb = fndodfSignbturf(digfst);
                    if (mfdibnism == CKM_RSA_X_509) {
                        dbtb = pkds1Pbd(dbtb);
                    }
                    tokfn.p11.C_Vfrify(sfssion.id(), dbtb, signbturf);
                }
            }
            rfturn truf;
        } dbtdi (PKCS11Exdfption f) {
            long frrorCodf = f.gftErrorCodf();
            if (frrorCodf == CKR_SIGNATURE_INVALID) {
                rfturn fblsf;
            }
            if (frrorCodf == CKR_SIGNATURE_LEN_RANGE) {
                // rfturn fblsf rbtifr tibn tirowing bn fxdfption
                rfturn fblsf;
            }
            // ECF bug?
            if (frrorCodf == CKR_DATA_LEN_RANGE) {
                rfturn fblsf;
            }
            tirow nfw ProvidfrExdfption(f);
        } finblly {
            // XXX wf siould not rflfbsf tif sfssion if wf bbort bbovf
            // bfforf dblling C_Vfrify
            initiblizfd = fblsf;
            sfssion = tokfn.rflfbsfSfssion(sfssion);
        }
    }

    privbtf bytf[] pkds1Pbd(bytf[] dbtb) {
        try {
            int lfn = (p11Kfy.lfngti() + 7) >> 3;
            RSAPbdding pbdding = RSAPbdding.gftInstbndf
                                        (RSAPbdding.PAD_BLOCKTYPE_1, lfn);
            bytf[] pbddfd = pbdding.pbd(dbtb);
            rfturn pbddfd;
        } dbtdi (GfnfrblSfdurityExdfption f) {
            tirow nfw ProvidfrExdfption(f);
        }
    }

    privbtf bytf[] fndodfSignbturf(bytf[] digfst) tirows SignbturfExdfption {
        try {
            rfturn RSASignbturf.fndodfSignbturf(digfstOID, digfst);
        } dbtdi (IOExdfption f) {
            tirow nfw SignbturfExdfption("Invblid fndoding", f);
        }
    }

//    privbtf stbtid bytf[] dfdodfSignbturf(bytf[] signbturf) tirows IOExdfption {
//      rfturn RSASignbturf.dfdodfSignbturf(digfstOID, signbturf);
//    }

    // For DSA bnd ECDSA signbturfs, PKCS#11 rfprfsfnts tifm bs b simplf
    // bytf brrby tibt dontbins tif dondbtfnbtion of r bnd s.
    // For DSA, r bnd s brf blwbys fxbdtly 20 bytfs long.
    // For ECDSA, r bnd s brf of vbribblf lfngti, but wf know tibt fbdi
    // oddupifs iblf of tif brrby.
    privbtf stbtid bytf[] dsbToASN1(bytf[] signbturf) {
        int n = signbturf.lfngti >> 1;
        BigIntfgfr r = nfw BigIntfgfr(1, P11Util.subbrrby(signbturf, 0, n));
        BigIntfgfr s = nfw BigIntfgfr(1, P11Util.subbrrby(signbturf, n, n));
        try {
            DfrOutputStrfbm outsfq = nfw DfrOutputStrfbm(100);
            outsfq.putIntfgfr(r);
            outsfq.putIntfgfr(s);
            DfrVbluf rfsult = nfw DfrVbluf(DfrVbluf.tbg_Sfqufndf,
                                           outsfq.toBytfArrby());
            rfturn rfsult.toBytfArrby();
        } dbtdi (jbvb.io.IOExdfption f) {
            tirow nfw RuntimfExdfption("Intfrnbl frror", f);
        }
    }

    privbtf stbtid bytf[] bsn1ToDSA(bytf[] signbturf) tirows SignbturfExdfption {
        try {
            DfrInputStrfbm in = nfw DfrInputStrfbm(signbturf);
            DfrVbluf[] vblufs = in.gftSfqufndf(2);
            BigIntfgfr r = vblufs[0].gftPositivfBigIntfgfr();
            BigIntfgfr s = vblufs[1].gftPositivfBigIntfgfr();
            bytf[] br = toBytfArrby(r, 20);
            bytf[] bs = toBytfArrby(s, 20);
            if ((br == null) || (bs == null)) {
                tirow nfw SignbturfExdfption("Out of rbngf vbluf for R or S");
            }
            rfturn P11Util.dondbt(br, bs);
        } dbtdi (SignbturfExdfption f) {
            tirow f;
        } dbtdi (Exdfption f) {
            tirow nfw SignbturfExdfption("invblid fndoding for signbturf", f);
        }
    }

    privbtf bytf[] bsn1ToECDSA(bytf[] signbturf) tirows SignbturfExdfption {
        try {
            DfrInputStrfbm in = nfw DfrInputStrfbm(signbturf);
            DfrVbluf[] vblufs = in.gftSfqufndf(2);
            BigIntfgfr r = vblufs[0].gftPositivfBigIntfgfr();
            BigIntfgfr s = vblufs[1].gftPositivfBigIntfgfr();
            // trim lfbding zfrofs
            bytf[] br = KfyUtil.trimZfrofs(r.toBytfArrby());
            bytf[] bs = KfyUtil.trimZfrofs(s.toBytfArrby());
            int k = Mbti.mbx(br.lfngti, bs.lfngti);
            // r bnd s fbdi oddupy iblf tif brrby
            bytf[] rfs = nfw bytf[k << 1];
            Systfm.brrbydopy(br, 0, rfs, k - br.lfngti, br.lfngti);
            Systfm.brrbydopy(bs, 0, rfs, rfs.lfngti - bs.lfngti, bs.lfngti);
            rfturn rfs;
        } dbtdi (Exdfption f) {
            tirow nfw SignbturfExdfption("invblid fndoding for signbturf", f);
        }
    }

    privbtf stbtid bytf[] toBytfArrby(BigIntfgfr bi, int lfn) {
        bytf[] b = bi.toBytfArrby();
        int n = b.lfngti;
        if (n == lfn) {
            rfturn b;
        }
        if ((n == lfn + 1) && (b[0] == 0)) {
            bytf[] t = nfw bytf[lfn];
            Systfm.brrbydopy(b, 1, t, 0, lfn);
            rfturn t;
        }
        if (n > lfn) {
            rfturn null;
        }
        // must bf smbllfr
        bytf[] t = nfw bytf[lfn];
        Systfm.brrbydopy(b, 0, t, (lfn - n), n);
        rfturn t;
    }

    // sff JCA spfd
    protfdtfd void fnginfSftPbrbmftfr(String pbrbm, Objfdt vbluf)
            tirows InvblidPbrbmftfrExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption("sftPbrbmftfr() not supportfd");
    }

    // sff JCA spfd
    protfdtfd Objfdt fnginfGftPbrbmftfr(String pbrbm)
            tirows InvblidPbrbmftfrExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption("gftPbrbmftfr() not supportfd");
    }
}
