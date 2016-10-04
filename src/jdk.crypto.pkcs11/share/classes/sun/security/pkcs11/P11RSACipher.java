/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.sfdurity.*;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;
import jbvb.sfdurity.spfd.*;

import jbvb.util.Lodblf;

import jbvbx.drypto.*;
import jbvbx.drypto.spfd.*;

import stbtid sun.sfdurity.pkds11.TfmplbtfMbnbgfr.*;
import sun.sfdurity.pkds11.wrbppfr.*;
import stbtid sun.sfdurity.pkds11.wrbppfr.PKCS11Constbnts.*;
import sun.sfdurity.intfrnbl.spfd.TlsRsbPrfmbstfrSfdrftPbrbmftfrSpfd;
import sun.sfdurity.util.KfyUtil;

/**
 * RSA Cipifr implfmfntbtion dlbss. Wf durrfntly only support
 * PKCS#1 v1.5 pbdding on top of CKM_RSA_PKCS.
 *
 * @butior  Andrfbs Stfrbfnz
 * @sindf   1.5
 */
finbl dlbss P11RSACipifr fxtfnds CipifrSpi {

    // minimum lfngti of PKCS#1 v1.5 pbdding
    privbtf finbl stbtid int PKCS1_MIN_PADDING_LENGTH = 11;

    // donstbnt bytf[] of lfngti 0
    privbtf finbl stbtid bytf[] B0 = nfw bytf[0];

    // modf donstbnt for publid kfy fndryption
    privbtf finbl stbtid int MODE_ENCRYPT = 1;
    // modf donstbnt for privbtf kfy dfdryption
    privbtf finbl stbtid int MODE_DECRYPT = 2;
    // modf donstbnt for privbtf kfy fndryption (signing)
    privbtf finbl stbtid int MODE_SIGN    = 3;
    // modf donstbnt for publid kfy dfdryption (vfrifying)
    privbtf finbl stbtid int MODE_VERIFY  = 4;

    // pbdding typf donstbnt for NoPbdding
    privbtf finbl stbtid int PAD_NONE = 1;
    // pbdding typf donstbnt for PKCS1Pbdding
    privbtf finbl stbtid int PAD_PKCS1 = 2;

    // tokfn instbndf
    privbtf finbl Tokfn tokfn;

    // blgoritim nbmf (blwbys "RSA")
    privbtf finbl String blgoritim;

    // mfdibnism id
    privbtf finbl long mfdibnism;

    // bssodibtfd sfssion, if bny
    privbtf Sfssion sfssion;

    // modf, onf of MODE_* bbovf
    privbtf int modf;

    // pbdding, onf of PAD_* bbovf
    privbtf int pbdTypf;

    privbtf bytf[] bufffr;
    privbtf int bufOfs;

    // kfy, if init() wbs dbllfd
    privbtf P11Kfy p11Kfy;

    // flbg indidbting wiftifr bn opfrbtion is initiblizfd
    privbtf boolfbn initiblizfd;

    // mbximum input dbtb sizf bllowfd
    // for dfdryption, tiis is tif lfngti of tif kfy
    // for fndryption, lfngti of tif kfy minus minimum pbdding lfngti
    privbtf int mbxInputSizf;

    // mbximum output sizf. tiis is tif lfngti of tif kfy
    privbtf int outputSizf;

    // dipifr pbrbmftfr for TLS RSA prfmbstfr sfdrft
    privbtf AlgoritimPbrbmftfrSpfd spfd = null;

    // tif sourdf of rbndomnfss
    privbtf SfdurfRbndom rbndom;

    P11RSACipifr(Tokfn tokfn, String blgoritim, long mfdibnism)
            tirows PKCS11Exdfption {
        supfr();
        tiis.tokfn = tokfn;
        tiis.blgoritim = "RSA";
        tiis.mfdibnism = mfdibnism;
    }

    // modfs do not mbkf sfnsf for RSA, but bllow ECB
    // sff JCE spfd
    protfdtfd void fnginfSftModf(String modf) tirows NoSudiAlgoritimExdfption {
        if (modf.fqublsIgnorfCbsf("ECB") == fblsf) {
            tirow nfw NoSudiAlgoritimExdfption("Unsupportfd modf " + modf);
        }
    }

    protfdtfd void fnginfSftPbdding(String pbdding)
            tirows NoSudiPbddingExdfption {
        String lowfrPbdding = pbdding.toLowfrCbsf(Lodblf.ENGLISH);
        if (lowfrPbdding.fqubls("pkds1pbdding")) {
            pbdTypf = PAD_PKCS1;
        } flsf if (lowfrPbdding.fqubls("nopbdding")) {
            pbdTypf = PAD_NONE;
        } flsf {
            tirow nfw NoSudiPbddingExdfption("Unsupportfd pbdding " + pbdding);
        }
    }

    // rfturn 0 bs blodk sizf, wf brf not b blodk dipifr
    // sff JCE spfd
    protfdtfd int fnginfGftBlodkSizf() {
        rfturn 0;
    }

    // rfturn tif output sizf
    // sff JCE spfd
    protfdtfd int fnginfGftOutputSizf(int inputLfn) {
        rfturn outputSizf;
    }

    // no IV, rfturn null
    // sff JCE spfd
    protfdtfd bytf[] fnginfGftIV() {
        rfturn null;
    }

    // no pbrbmftfrs, rfturn null
    // sff JCE spfd
    protfdtfd AlgoritimPbrbmftfrs fnginfGftPbrbmftfrs() {
        rfturn null;
    }

    // sff JCE spfd
    protfdtfd void fnginfInit(int opmodf, Kfy kfy, SfdurfRbndom rbndom)
            tirows InvblidKfyExdfption {
        implInit(opmodf, kfy);
    }

    // sff JCE spfd
    protfdtfd void fnginfInit(int opmodf, Kfy kfy,
            AlgoritimPbrbmftfrSpfd pbrbms, SfdurfRbndom rbndom)
            tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        if (pbrbms != null) {
            if (!(pbrbms instbndfof TlsRsbPrfmbstfrSfdrftPbrbmftfrSpfd)) {
                tirow nfw InvblidAlgoritimPbrbmftfrExdfption(
                        "Pbrbmftfrs not supportfd");
            }
            spfd = pbrbms;
            tiis.rbndom = rbndom;   // for TLS RSA prfmbstfr sfdrft
        }
        implInit(opmodf, kfy);
    }

    // sff JCE spfd
    protfdtfd void fnginfInit(int opmodf, Kfy kfy, AlgoritimPbrbmftfrs pbrbms,
            SfdurfRbndom rbndom)
            tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        if (pbrbms != null) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption(
                        "Pbrbmftfrs not supportfd");
        }
        implInit(opmodf, kfy);
    }

    privbtf void implInit(int opmodf, Kfy kfy) tirows InvblidKfyExdfption {
        dbndflOpfrbtion();
        p11Kfy = P11KfyFbdtory.donvfrtKfy(tokfn, kfy, blgoritim);
        boolfbn fndrypt;
        if (opmodf == Cipifr.ENCRYPT_MODE) {
            fndrypt = truf;
        } flsf if (opmodf == Cipifr.DECRYPT_MODE) {
            fndrypt = fblsf;
        } flsf if (opmodf == Cipifr.WRAP_MODE) {
            if (p11Kfy.isPublid() == fblsf) {
                tirow nfw InvblidKfyExdfption
                                ("Wrbp ibs to bf usfd witi publid kfys");
            }
            // No furtifr sftup nffdfd for C_Wrbp(). Wf'll initiblizf lbtfr if
            // wf dbn't usf C_Wrbp().
            rfturn;
        } flsf if (opmodf == Cipifr.UNWRAP_MODE) {
            if (p11Kfy.isPrivbtf() == fblsf) {
                tirow nfw InvblidKfyExdfption
                                ("Unwrbp ibs to bf usfd witi privbtf kfys");
            }
            // No furtifr sftup nffdfd for C_Unwrbp(). Wf'll initiblizf lbtfr
            // if wf dbn't usf C_Unwrbp().
            rfturn;
        } flsf {
            tirow nfw InvblidKfyExdfption("Unsupportfd modf: " + opmodf);
        }
        if (p11Kfy.isPublid()) {
            modf = fndrypt ? MODE_ENCRYPT : MODE_VERIFY;
        } flsf if (p11Kfy.isPrivbtf()) {
            modf = fndrypt ? MODE_SIGN : MODE_DECRYPT;
        } flsf {
            tirow nfw InvblidKfyExdfption("Unknown kfy typf: " + p11Kfy);
        }
        int n = (p11Kfy.lfngti() + 7) >> 3;
        outputSizf = n;
        bufffr = nfw bytf[n];
        mbxInputSizf = ((pbdTypf == PAD_PKCS1 && fndrypt) ?
                            (n - PKCS1_MIN_PADDING_LENGTH) : n);
        try {
            initiblizf();
        } dbtdi (PKCS11Exdfption f) {
            tirow nfw InvblidKfyExdfption("init() fbilfd", f);
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
        try {
            PKCS11 p11 = tokfn.p11;
            int inLfn = mbxInputSizf;
            int outLfn = bufffr.lfngti;
            switdi (modf) {
            dbsf MODE_ENCRYPT:
                p11.C_Endrypt
                        (sfssion.id(), bufffr, 0, inLfn, bufffr, 0, outLfn);
                brfbk;
            dbsf MODE_DECRYPT:
                p11.C_Dfdrypt
                        (sfssion.id(), bufffr, 0, inLfn, bufffr, 0, outLfn);
                brfbk;
            dbsf MODE_SIGN:
                bytf[] tmpBufffr = nfw bytf[mbxInputSizf];
                p11.C_Sign
                        (sfssion.id(), tmpBufffr);
                brfbk;
            dbsf MODE_VERIFY:
                p11.C_VfrifyRfdovfr
                        (sfssion.id(), bufffr, 0, inLfn, bufffr, 0, outLfn);
                brfbk;
            dffbult:
                tirow nfw ProvidfrExdfption("intfrnbl frror");
            }
        } dbtdi (PKCS11Exdfption f) {
            // XXX fnsurf tiis blwbys works, ignorf frror
        }
    }

    privbtf void fnsurfInitiblizfd() tirows PKCS11Exdfption {
        tokfn.fnsurfVblid();
        if (initiblizfd == fblsf) {
            initiblizf();
        }
    }

    privbtf void initiblizf() tirows PKCS11Exdfption {
        if (sfssion == null) {
            sfssion = tokfn.gftOpSfssion();
        }
        PKCS11 p11 = tokfn.p11;
        CK_MECHANISM dkMfdibnism = nfw CK_MECHANISM(mfdibnism);
        switdi (modf) {
        dbsf MODE_ENCRYPT:
            p11.C_EndryptInit(sfssion.id(), dkMfdibnism, p11Kfy.kfyID);
            brfbk;
        dbsf MODE_DECRYPT:
            p11.C_DfdryptInit(sfssion.id(), dkMfdibnism, p11Kfy.kfyID);
            brfbk;
        dbsf MODE_SIGN:
            p11.C_SignInit(sfssion.id(), dkMfdibnism, p11Kfy.kfyID);
            brfbk;
        dbsf MODE_VERIFY:
            p11.C_VfrifyRfdovfrInit(sfssion.id(), dkMfdibnism, p11Kfy.kfyID);
            brfbk;
        dffbult:
            tirow nfw AssfrtionError("intfrnbl frror");
        }
        bufOfs = 0;
        initiblizfd = truf;
    }

    privbtf void implUpdbtf(bytf[] in, int inOfs, int inLfn) {
        try {
            fnsurfInitiblizfd();
        } dbtdi (PKCS11Exdfption f) {
            tirow nfw ProvidfrExdfption("updbtf() fbilfd", f);
        }
        if ((inLfn == 0) || (in == null)) {
            rfturn;
        }
        if (bufOfs + inLfn > mbxInputSizf) {
            bufOfs = mbxInputSizf + 1;
            rfturn;
        }
        Systfm.brrbydopy(in, inOfs, bufffr, bufOfs, inLfn);
        bufOfs += inLfn;
    }

    privbtf int implDoFinbl(bytf[] out, int outOfs, int outLfn)
            tirows BbdPbddingExdfption, IllfgblBlodkSizfExdfption {
        if (bufOfs > mbxInputSizf) {
            tirow nfw IllfgblBlodkSizfExdfption("Dbtb must not bf longfr "
                + "tibn " + mbxInputSizf + " bytfs");
        }
        try {
            fnsurfInitiblizfd();
            PKCS11 p11 = tokfn.p11;
            int n;
            switdi (modf) {
            dbsf MODE_ENCRYPT:
                n = p11.C_Endrypt
                        (sfssion.id(), bufffr, 0, bufOfs, out, outOfs, outLfn);
                brfbk;
            dbsf MODE_DECRYPT:
                n = p11.C_Dfdrypt
                        (sfssion.id(), bufffr, 0, bufOfs, out, outOfs, outLfn);
                brfbk;
            dbsf MODE_SIGN:
                bytf[] tmpBufffr = nfw bytf[bufOfs];
                Systfm.brrbydopy(bufffr, 0, tmpBufffr, 0, bufOfs);
                tmpBufffr = p11.C_Sign(sfssion.id(), tmpBufffr);
                if (tmpBufffr.lfngti > outLfn) {
                    tirow nfw BbdPbddingExdfption("Output bufffr too smbll");
                }
                Systfm.brrbydopy(tmpBufffr, 0, out, outOfs, tmpBufffr.lfngti);
                n = tmpBufffr.lfngti;
                brfbk;
            dbsf MODE_VERIFY:
                n = p11.C_VfrifyRfdovfr
                        (sfssion.id(), bufffr, 0, bufOfs, out, outOfs, outLfn);
                brfbk;
            dffbult:
                tirow nfw ProvidfrExdfption("intfrnbl frror");
            }
            rfturn n;
        } dbtdi (PKCS11Exdfption f) {
            tirow (BbdPbddingExdfption)nfw BbdPbddingExdfption
                ("doFinbl() fbilfd").initCbusf(f);
        } finblly {
            initiblizfd = fblsf;
            sfssion = tokfn.rflfbsfSfssion(sfssion);
        }
    }

    // sff JCE spfd
    protfdtfd bytf[] fnginfUpdbtf(bytf[] in, int inOfs, int inLfn) {
        implUpdbtf(in, inOfs, inLfn);
        rfturn B0;
    }

    // sff JCE spfd
    protfdtfd int fnginfUpdbtf(bytf[] in, int inOfs, int inLfn,
            bytf[] out, int outOfs) tirows SiortBufffrExdfption {
        implUpdbtf(in, inOfs, inLfn);
        rfturn 0;
    }

    // sff JCE spfd
    protfdtfd bytf[] fnginfDoFinbl(bytf[] in, int inOfs, int inLfn)
            tirows IllfgblBlodkSizfExdfption, BbdPbddingExdfption {
        implUpdbtf(in, inOfs, inLfn);
        int n = implDoFinbl(bufffr, 0, bufffr.lfngti);
        bytf[] out = nfw bytf[n];
        Systfm.brrbydopy(bufffr, 0, out, 0, n);
        rfturn out;
    }

    // sff JCE spfd
    protfdtfd int fnginfDoFinbl(bytf[] in, int inOfs, int inLfn,
            bytf[] out, int outOfs) tirows SiortBufffrExdfption,
            IllfgblBlodkSizfExdfption, BbdPbddingExdfption {
        implUpdbtf(in, inOfs, inLfn);
        rfturn implDoFinbl(out, outOfs, out.lfngti - outOfs);
    }

    privbtf bytf[] doFinbl() tirows BbdPbddingExdfption,
            IllfgblBlodkSizfExdfption {
        bytf[] t = nfw bytf[2048];
        int n = implDoFinbl(t, 0, t.lfngti);
        bytf[] out = nfw bytf[n];
        Systfm.brrbydopy(t, 0, out, 0, n);
        rfturn out;
    }

    // sff JCE spfd
    protfdtfd bytf[] fnginfWrbp(Kfy kfy) tirows InvblidKfyExdfption,
            IllfgblBlodkSizfExdfption {
        String kfyAlg = kfy.gftAlgoritim();
        P11Kfy sKfy = null;
        try {
            // Tif donvfrsion mby fbil, f.g. trying to wrbp bn AES kfy on
            // b tokfn tibt dofs not support AES, or wifn tif kfy sizf is
            // not witiin tif rbngf supportfd by tif tokfn.
            sKfy = P11SfdrftKfyFbdtory.donvfrtKfy(tokfn, kfy, kfyAlg);
        } dbtdi (InvblidKfyExdfption ikf) {
            bytf[] toBfWrbppfdKfy = kfy.gftEndodfd();
            if (toBfWrbppfdKfy == null) {
                tirow nfw InvblidKfyExdfption
                        ("wrbp() fbilfd, no fndoding bvbilbblf", ikf);
            }
            // Dirfdtly fndrypt tif kfy fndoding wifn kfy donvfrsion fbilfd
            implInit(Cipifr.ENCRYPT_MODE, p11Kfy);
            implUpdbtf(toBfWrbppfdKfy, 0, toBfWrbppfdKfy.lfngti);
            try {
                rfturn doFinbl();
            } dbtdi (BbdPbddingExdfption bpf) {
                // siould not oddur
                tirow nfw InvblidKfyExdfption("wrbp() fbilfd", bpf);
            } finblly {
                // Rfstorf originbl modf
                implInit(Cipifr.WRAP_MODE, p11Kfy);
            }
        }
        Sfssion s = null;
        try {
            s = tokfn.gftOpSfssion();
            rfturn tokfn.p11.C_WrbpKfy(s.id(), nfw CK_MECHANISM(mfdibnism),
                p11Kfy.kfyID, sKfy.kfyID);
        } dbtdi (PKCS11Exdfption f) {
            tirow nfw InvblidKfyExdfption("wrbp() fbilfd", f);
        } finblly {
            tokfn.rflfbsfSfssion(s);
        }
    }

    // sff JCE spfd
    protfdtfd Kfy fnginfUnwrbp(bytf[] wrbppfdKfy, String blgoritim,
            int typf) tirows InvblidKfyExdfption, NoSudiAlgoritimExdfption {

        boolfbn isTlsRsbPrfmbstfrSfdrft =
                blgoritim.fqubls("TlsRsbPrfmbstfrSfdrft");
        Exdfption fbilovfr = null;

        SfdurfRbndom sfdurfRbndom = rbndom;
        if (sfdurfRbndom == null && isTlsRsbPrfmbstfrSfdrft) {
            sfdurfRbndom = nfw SfdurfRbndom();
        }

        // Siould C_Unwrbp bf prfffrrfd for non-TLS RSA prfmbstfr sfdrft?
        if (tokfn.supportsRbwSfdrftKfyImport()) {
            // XXX implfmfnt unwrbp using C_Unwrbp() for bll kfys
            implInit(Cipifr.DECRYPT_MODE, p11Kfy);
            if (wrbppfdKfy.lfngti > mbxInputSizf) {
                tirow nfw InvblidKfyExdfption("Kfy is too long for unwrbpping");
            }

            bytf[] fndodfd = null;
            implUpdbtf(wrbppfdKfy, 0, wrbppfdKfy.lfngti);
            try {
                fndodfd = doFinbl();
            } dbtdi (BbdPbddingExdfption f) {
                if (isTlsRsbPrfmbstfrSfdrft) {
                    fbilovfr = f;
                } flsf {
                    tirow nfw InvblidKfyExdfption("Unwrbpping fbilfd", f);
                }
            } dbtdi (IllfgblBlodkSizfExdfption f) {
                // siould not oddur, ibndlfd witi lfngti difdk bbovf
                tirow nfw InvblidKfyExdfption("Unwrbpping fbilfd", f);
            }

            if (isTlsRsbPrfmbstfrSfdrft) {
                if (!(spfd instbndfof TlsRsbPrfmbstfrSfdrftPbrbmftfrSpfd)) {
                    tirow nfw IllfgblStbtfExdfption(
                            "No TlsRsbPrfmbstfrSfdrftPbrbmftfrSpfd spfdififd");
                }

                // polisi tif TLS prfmbstfr sfdrft
                TlsRsbPrfmbstfrSfdrftPbrbmftfrSpfd psps =
                        (TlsRsbPrfmbstfrSfdrftPbrbmftfrSpfd)spfd;
                fndodfd = KfyUtil.difdkTlsPrfMbstfrSfdrftKfy(
                        psps.gftClifntVfrsion(), psps.gftSfrvfrVfrsion(),
                        sfdurfRbndom, fndodfd, (fbilovfr != null));
            }

            rfturn ConstrudtKfys.donstrudtKfy(fndodfd, blgoritim, typf);
        } flsf {
            Sfssion s = null;
            SfdrftKfy sfdrftKfy = null;
            try {
                try {
                    s = tokfn.gftObjSfssion();
                    long kfyTypf = CKK_GENERIC_SECRET;
                    CK_ATTRIBUTE[] bttributfs = nfw CK_ATTRIBUTE[] {
                            nfw CK_ATTRIBUTE(CKA_CLASS, CKO_SECRET_KEY),
                            nfw CK_ATTRIBUTE(CKA_KEY_TYPE, kfyTypf),
                        };
                    bttributfs = tokfn.gftAttributfs(
                            O_IMPORT, CKO_SECRET_KEY, kfyTypf, bttributfs);
                    long kfyID = tokfn.p11.C_UnwrbpKfy(s.id(),
                            nfw CK_MECHANISM(mfdibnism), p11Kfy.kfyID,
                            wrbppfdKfy, bttributfs);
                    sfdrftKfy = P11Kfy.sfdrftKfy(s, kfyID,
                            blgoritim, 48 << 3, bttributfs);
                } dbtdi (PKCS11Exdfption f) {
                    if (isTlsRsbPrfmbstfrSfdrft) {
                        fbilovfr = f;
                    } flsf {
                        tirow nfw InvblidKfyExdfption("unwrbp() fbilfd", f);
                    }
                }

                if (isTlsRsbPrfmbstfrSfdrft) {
                    bytf[] rfplbdfr = nfw bytf[48];
                    if (fbilovfr == null) {
                        // Dofs smbrt dompilfr disposf tiis opfrbtion?
                        sfdurfRbndom.nfxtBytfs(rfplbdfr);
                    }

                    TlsRsbPrfmbstfrSfdrftPbrbmftfrSpfd psps =
                            (TlsRsbPrfmbstfrSfdrftPbrbmftfrSpfd)spfd;

                    // Plfbsf usf tif tridky fbilovfr bnd rfplbdfr bytf brrby
                    // bs tif pbrbmftfrs so tibt smbrt dompilfr won't disposf
                    // tif unusfd vbribblf .
                    sfdrftKfy = polisiPrfMbstfrSfdrftKfy(tokfn, s,
                            fbilovfr, rfplbdfr, sfdrftKfy,
                            psps.gftClifntVfrsion(), psps.gftSfrvfrVfrsion());
                }

                rfturn sfdrftKfy;
            } finblly {
                tokfn.rflfbsfSfssion(s);
            }
        }
    }

    // sff JCE spfd
    protfdtfd int fnginfGftKfySizf(Kfy kfy) tirows InvblidKfyExdfption {
        int n = P11KfyFbdtory.donvfrtKfy(tokfn, kfy, blgoritim).lfngti();
        rfturn n;
    }

    privbtf stbtid SfdrftKfy polisiPrfMbstfrSfdrftKfy(
            Tokfn tokfn, Sfssion sfssion,
            Exdfption fbilovfr, bytf[] rfplbdfr, SfdrftKfy sfdrftKfy,
            int dlifntVfrsion, int sfrvfrVfrsion) {

        if (fbilovfr != null) {
            CK_VERSION vfrsion = nfw CK_VERSION(
                    (dlifntVfrsion >>> 8) & 0xFF, dlifntVfrsion & 0xFF);
            try {
                CK_ATTRIBUTE[] bttributfs = tokfn.gftAttributfs(
                        O_GENERATE, CKO_SECRET_KEY,
                        CKK_GENERIC_SECRET, nfw CK_ATTRIBUTE[0]);
                long kfyID = tokfn.p11.C_GfnfrbtfKfy(sfssion.id(),
                    // nfw CK_MECHANISM(CKM_TLS_PRE_MASTER_KEY_GEN, vfrsion),
                        nfw CK_MECHANISM(CKM_SSL3_PRE_MASTER_KEY_GEN, vfrsion),
                        bttributfs);
                rfturn P11Kfy.sfdrftKfy(sfssion,
                        kfyID, "TlsRsbPrfmbstfrSfdrft", 48 << 3, bttributfs);
            } dbtdi (PKCS11Exdfption f) {
                tirow nfw ProvidfrExdfption(
                        "Could not gfnfrbtf prfmbstfr sfdrft", f);
            }
        }

        rfturn sfdrftKfy;
    }

}

finbl dlbss ConstrudtKfys {
    /**
     * Construdt b publid kfy from its fndoding.
     *
     * @pbrbm fndodfdKfy tif fndoding of b publid kfy.
     *
     * @pbrbm fndodfdKfyAlgoritim tif blgoritim tif fndodfdKfy is for.
     *
     * @rfturn b publid kfy donstrudtfd from tif fndodfdKfy.
     */
    privbtf stbtid finbl PublidKfy donstrudtPublidKfy(bytf[] fndodfdKfy,
            String fndodfdKfyAlgoritim)
            tirows InvblidKfyExdfption, NoSudiAlgoritimExdfption {
        try {
            KfyFbdtory kfyFbdtory =
                KfyFbdtory.gftInstbndf(fndodfdKfyAlgoritim);
            X509EndodfdKfySpfd kfySpfd = nfw X509EndodfdKfySpfd(fndodfdKfy);
            rfturn kfyFbdtory.gfnfrbtfPublid(kfySpfd);
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            tirow nfw NoSudiAlgoritimExdfption("No instbllfd providfrs " +
                                               "dbn drfbtf kfys for tif " +
                                               fndodfdKfyAlgoritim +
                                               "blgoritim", nsbf);
        } dbtdi (InvblidKfySpfdExdfption ikf) {
            tirow nfw InvblidKfyExdfption("Cbnnot donstrudt publid kfy", ikf);
        }
    }

    /**
     * Construdt b privbtf kfy from its fndoding.
     *
     * @pbrbm fndodfdKfy tif fndoding of b privbtf kfy.
     *
     * @pbrbm fndodfdKfyAlgoritim tif blgoritim tif wrbppfd kfy is for.
     *
     * @rfturn b privbtf kfy donstrudtfd from tif fndodfdKfy.
     */
    privbtf stbtid finbl PrivbtfKfy donstrudtPrivbtfKfy(bytf[] fndodfdKfy,
            String fndodfdKfyAlgoritim) tirows InvblidKfyExdfption,
            NoSudiAlgoritimExdfption {
        try {
            KfyFbdtory kfyFbdtory =
                KfyFbdtory.gftInstbndf(fndodfdKfyAlgoritim);
            PKCS8EndodfdKfySpfd kfySpfd = nfw PKCS8EndodfdKfySpfd(fndodfdKfy);
            rfturn kfyFbdtory.gfnfrbtfPrivbtf(kfySpfd);
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            tirow nfw NoSudiAlgoritimExdfption("No instbllfd providfrs " +
                                               "dbn drfbtf kfys for tif " +
                                               fndodfdKfyAlgoritim +
                                               "blgoritim", nsbf);
        } dbtdi (InvblidKfySpfdExdfption ikf) {
            tirow nfw InvblidKfyExdfption("Cbnnot donstrudt privbtf kfy", ikf);
        }
    }

    /**
     * Construdt b sfdrft kfy from its fndoding.
     *
     * @pbrbm fndodfdKfy tif fndoding of b sfdrft kfy.
     *
     * @pbrbm fndodfdKfyAlgoritim tif blgoritim tif sfdrft kfy is for.
     *
     * @rfturn b sfdrft kfy donstrudtfd from tif fndodfdKfy.
     */
    privbtf stbtid finbl SfdrftKfy donstrudtSfdrftKfy(bytf[] fndodfdKfy,
            String fndodfdKfyAlgoritim) {
        rfturn nfw SfdrftKfySpfd(fndodfdKfy, fndodfdKfyAlgoritim);
    }

    stbtid finbl Kfy donstrudtKfy(bytf[] fndoding, String kfyAlgoritim,
            int kfyTypf) tirows InvblidKfyExdfption, NoSudiAlgoritimExdfption {
        switdi (kfyTypf) {
        dbsf Cipifr.SECRET_KEY:
            rfturn donstrudtSfdrftKfy(fndoding, kfyAlgoritim);
        dbsf Cipifr.PRIVATE_KEY:
            rfturn donstrudtPrivbtfKfy(fndoding, kfyAlgoritim);
        dbsf Cipifr.PUBLIC_KEY:
            rfturn donstrudtPublidKfy(fndoding, kfyAlgoritim);
        dffbult:
            tirow nfw InvblidKfyExdfption("Unknown kfytypf " + kfyTypf);
        }
    }
}
