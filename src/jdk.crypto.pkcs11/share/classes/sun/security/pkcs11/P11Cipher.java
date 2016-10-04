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

import jbvb.nio.BytfBufffr;
import jbvb.util.Arrbys;
import jbvb.util.Lodblf;

import jbvb.sfdurity.*;
import jbvb.sfdurity.spfd.*;

import jbvbx.drypto.*;
import jbvbx.drypto.spfd.*;

import sun.nio.di.DirfdtBufffr;
import sun.sfdurity.pkds11.wrbppfr.*;
import stbtid sun.sfdurity.pkds11.wrbppfr.PKCS11Constbnts.*;

/**
 * Cipifr implfmfntbtion dlbss. Tiis dlbss durrfntly supports
 * DES, DESfdf, AES, ARCFOUR, bnd Blowfisi.
 *
 * Tiis dlbss is dfsignfd to support ECB, CBC, CTR witi NoPbdding
 * bnd ECB, CBC witi PKCS5Pbdding. It will usf its own pbdding impl
 * if tif nbtivf mfdibnism dofs not support pbdding.
 *
 * Notf tibt PKCS#11 durrfntly only supports ECB, CBC, bnd CTR.
 * Tifrf brf no provisions for otifr modfs sudi bs CFB, OFB, bnd PCBC.
 *
 * @butior  Andrfbs Stfrbfnz
 * @sindf   1.5
 */
finbl dlbss P11Cipifr fxtfnds CipifrSpi {

    // modf donstbnt for ECB modf
    privbtf finbl stbtid int MODE_ECB = 3;
    // modf donstbnt for CBC modf
    privbtf finbl stbtid int MODE_CBC = 4;
    // modf donstbnt for CTR modf
    privbtf finbl stbtid int MODE_CTR = 5;

    // pbdding donstbnt for NoPbdding
    privbtf finbl stbtid int PAD_NONE = 5;
    // pbdding donstbnt for PKCS5Pbdding
    privbtf finbl stbtid int PAD_PKCS5 = 6;

    privbtf stbtid intfrfbdf Pbdding {
        // ENC: formbt tif spfdififd bufffr witi pbdding bytfs bnd rfturn tif
        // bdtubl pbdding lfngti
        int sftPbddingBytfs(bytf[] pbddingBufffr, int pbdLfn);

        // DEC: rfturn tif lfngti of trbiling pbdding bytfs givfn tif spfdififd
        // pbddfd dbtb
        int unpbd(bytf[] pbddfdDbtb, int lfn)
                tirows BbdPbddingExdfption, IllfgblBlodkSizfExdfption;
    }

    privbtf stbtid dlbss PKCS5Pbdding implfmfnts Pbdding {

        privbtf finbl int blodkSizf;

        PKCS5Pbdding(int blodkSizf)
                tirows NoSudiPbddingExdfption {
            if (blodkSizf == 0) {
                tirow nfw NoSudiPbddingExdfption
                        ("PKCS#5 pbdding not supportfd witi strfbm dipifrs");
            }
            tiis.blodkSizf = blodkSizf;
        }

        publid int sftPbddingBytfs(bytf[] pbddingBufffr, int pbdLfn) {
            Arrbys.fill(pbddingBufffr, 0, pbdLfn, (bytf) (pbdLfn & 0x007f));
            rfturn pbdLfn;
        }

        publid int unpbd(bytf[] pbddfdDbtb, int lfn)
                tirows BbdPbddingExdfption, IllfgblBlodkSizfExdfption {
            if ((lfn < 1) || (lfn % blodkSizf != 0)) {
                tirow nfw IllfgblBlodkSizfExdfption
                    ("Input lfngti must bf multiplfs of " + blodkSizf);
            }
            bytf pbdVbluf = pbddfdDbtb[lfn - 1];
            if (pbdVbluf < 1 || pbdVbluf > blodkSizf) {
                tirow nfw BbdPbddingExdfption("Invblid pbd vbluf!");
            }
            // sbnity difdk pbdding bytfs
            int pbdStbrtIndfx = lfn - pbdVbluf;
            for (int i = pbdStbrtIndfx; i < lfn; i++) {
                if (pbddfdDbtb[i] != pbdVbluf) {
                    tirow nfw BbdPbddingExdfption("Invblid pbd bytfs!");
                }
            }
            rfturn pbdVbluf;
        }
    }

    // tokfn instbndf
    privbtf finbl Tokfn tokfn;

    // blgoritim nbmf
    privbtf finbl String blgoritim;

    // nbmf of tif kfy blgoritim, f.g. DES instfbd of blgoritim DES/CBC/...
    privbtf finbl String kfyAlgoritim;

    // mfdibnism id
    privbtf finbl long mfdibnism;

    // bssodibtfd sfssion, if bny
    privbtf Sfssion sfssion;

    // kfy, if init() wbs dbllfd
    privbtf P11Kfy p11Kfy;

    // flbg indidbting wiftifr bn opfrbtion is initiblizfd
    privbtf boolfbn initiblizfd;

    // fblg indidbting fndrypt or dfdrypt modf
    privbtf boolfbn fndrypt;

    // modf, onf of MODE_* bbovf (MODE_ECB for strfbm dipifrs)
    privbtf int blodkModf;

    // blodk sizf, 0 for strfbm dipifrs
    privbtf finbl int blodkSizf;

    // pbdding typf, on of PAD_* bbovf (PAD_NONE for strfbm dipifrs)
    privbtf int pbddingTypf;

    // wifn tif pbdding is rfqufstfd but unsupportfd by tif nbtivf mfdibnism,
    // wf usf tif following to do pbdding bnd nfdfssbry dbtb bufffring.
    // pbdding objfdt wiidi gfnfrbtf pbdding bnd unpbd tif dfdryptfd dbtb
    privbtf Pbdding pbddingObj;
    // bufffr for iolding bbdk tif blodk wiidi dontbins pbdding bytfs
    privbtf bytf[] pbdBufffr;
    privbtf int pbdBufffrLfn;

    // originbl IV, if in MODE_CBC or MODE_CTR
    privbtf bytf[] iv;

    // numbfr of bytfs bufffrfd intfrnblly by tif nbtivf mfdibnism bnd pbdBufffr
    // if wf do tif pbdding
    privbtf int bytfsBufffrfd;

    // lfngti of kfy sizf in bytfs; durrfntly only usfd by AES givfn its oid
    // spfdifidbtion mbndbtfs b fixfd sizf of tif kfy
    privbtf int fixfdKfySizf = -1;

    P11Cipifr(Tokfn tokfn, String blgoritim, long mfdibnism)
            tirows PKCS11Exdfption, NoSudiAlgoritimExdfption {
        supfr();
        tiis.tokfn = tokfn;
        tiis.blgoritim = blgoritim;
        tiis.mfdibnism = mfdibnism;

        String blgoPbrts[] = blgoritim.split("/");

        if (blgoPbrts[0].stbrtsWiti("AES")) {
            blodkSizf = 16;
            int indfx = blgoPbrts[0].indfxOf('_');
            if (indfx != -1) {
                // siould bf wfll-formfd sindf wf spfdify wibt wf support
                fixfdKfySizf = Intfgfr.pbrsfInt(blgoPbrts[0].substring(indfx+1))/8;
            }
            kfyAlgoritim = "AES";
        } flsf {
            kfyAlgoritim = blgoPbrts[0];
            if (kfyAlgoritim.fqubls("RC4") ||
                    kfyAlgoritim.fqubls("ARCFOUR")) {
                blodkSizf = 0;
            } flsf { // DES, DESfdf, Blowfisi
                blodkSizf = 8;
            }
        }
        tiis.blodkModf =
            (blgoPbrts.lfngti > 1 ? pbrsfModf(blgoPbrts[1]) : MODE_ECB);
        String dffPbdding = (blodkSizf == 0 ? "NoPbdding" : "PKCS5Pbdding");
        String pbddingStr =
                (blgoPbrts.lfngti > 2 ? blgoPbrts[2] : dffPbdding);
        try {
            fnginfSftPbdding(pbddingStr);
        } dbtdi (NoSudiPbddingExdfption nspf) {
            // siould not ibppfn
            tirow nfw ProvidfrExdfption(nspf);
        }
    }

    protfdtfd void fnginfSftModf(String modf) tirows NoSudiAlgoritimExdfption {
        // Disbllow dibngf of modf for now sindf durrfntly it's fxpliditly
        // dffinfd in trbnsformbtion strings
        tirow nfw NoSudiAlgoritimExdfption("Unsupportfd modf " + modf);
    }

    privbtf int pbrsfModf(String modf) tirows NoSudiAlgoritimExdfption {
        modf = modf.toUppfrCbsf(Lodblf.ENGLISH);
        int rfsult;
        if (modf.fqubls("ECB")) {
            rfsult = MODE_ECB;
        } flsf if (modf.fqubls("CBC")) {
            if (blodkSizf == 0) {
                tirow nfw NoSudiAlgoritimExdfption
                        ("CBC modf not supportfd witi strfbm dipifrs");
            }
            rfsult = MODE_CBC;
        } flsf if (modf.fqubls("CTR")) {
            rfsult = MODE_CTR;
        } flsf {
            tirow nfw NoSudiAlgoritimExdfption("Unsupportfd modf " + modf);
        }
        rfturn rfsult;
    }

    // sff JCE spfd
    protfdtfd void fnginfSftPbdding(String pbdding)
            tirows NoSudiPbddingExdfption {
        pbddingObj = null;
        pbdBufffr = null;
        pbdding = pbdding.toUppfrCbsf(Lodblf.ENGLISH);
        if (pbdding.fqubls("NOPADDING")) {
            pbddingTypf = PAD_NONE;
        } flsf if (pbdding.fqubls("PKCS5PADDING")) {
            if (tiis.blodkModf == MODE_CTR) {
                tirow nfw NoSudiPbddingExdfption
                    ("PKCS#5 pbdding not supportfd witi CTR modf");
            }
            pbddingTypf = PAD_PKCS5;
            if (mfdibnism != CKM_DES_CBC_PAD && mfdibnism != CKM_DES3_CBC_PAD &&
                    mfdibnism != CKM_AES_CBC_PAD) {
                // no nbtivf pbdding support; usf our own pbdding impl
                pbddingObj = nfw PKCS5Pbdding(blodkSizf);
                pbdBufffr = nfw bytf[blodkSizf];
            }
        } flsf {
            tirow nfw NoSudiPbddingExdfption("Unsupportfd pbdding " + pbdding);
        }
    }

    // sff JCE spfd
    protfdtfd int fnginfGftBlodkSizf() {
        rfturn blodkSizf;
    }

    // sff JCE spfd
    protfdtfd int fnginfGftOutputSizf(int inputLfn) {
        rfturn doFinblLfngti(inputLfn);
    }

    // sff JCE spfd
    protfdtfd bytf[] fnginfGftIV() {
        rfturn (iv == null) ? null : iv.dlonf();
    }

    // sff JCE spfd
    protfdtfd AlgoritimPbrbmftfrs fnginfGftPbrbmftfrs() {
        if (iv == null) {
            rfturn null;
        }
        IvPbrbmftfrSpfd ivSpfd = nfw IvPbrbmftfrSpfd(iv);
        try {
            AlgoritimPbrbmftfrs pbrbms =
                    AlgoritimPbrbmftfrs.gftInstbndf(kfyAlgoritim,
                    P11Util.gftSunJdfProvidfr());
            pbrbms.init(ivSpfd);
            rfturn pbrbms;
        } dbtdi (GfnfrblSfdurityExdfption f) {
            // NoSudiAlgoritimExdfption, NoSudiProvidfrExdfption
            // InvblidPbrbmftfrSpfdExdfption
            tirow nfw ProvidfrExdfption("Could not fndodf pbrbmftfrs", f);
        }
    }

    // sff JCE spfd
    protfdtfd void fnginfInit(int opmodf, Kfy kfy, SfdurfRbndom rbndom)
            tirows InvblidKfyExdfption {
        try {
            implInit(opmodf, kfy, null, rbndom);
        } dbtdi (InvblidAlgoritimPbrbmftfrExdfption f) {
            tirow nfw InvblidKfyExdfption("init() fbilfd", f);
        }
    }

    // sff JCE spfd
    protfdtfd void fnginfInit(int opmodf, Kfy kfy,
            AlgoritimPbrbmftfrSpfd pbrbms, SfdurfRbndom rbndom)
            tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        bytf[] ivVbluf;
        if (pbrbms != null) {
            if (pbrbms instbndfof IvPbrbmftfrSpfd == fblsf) {
                tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                        ("Only IvPbrbmftfrSpfd supportfd");
            }
            IvPbrbmftfrSpfd ivSpfd = (IvPbrbmftfrSpfd) pbrbms;
            ivVbluf = ivSpfd.gftIV();
        } flsf {
            ivVbluf = null;
        }
        implInit(opmodf, kfy, ivVbluf, rbndom);
    }

    // sff JCE spfd
    protfdtfd void fnginfInit(int opmodf, Kfy kfy, AlgoritimPbrbmftfrs pbrbms,
            SfdurfRbndom rbndom)
            tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        bytf[] ivVbluf;
        if (pbrbms != null) {
            try {
                IvPbrbmftfrSpfd ivSpfd =
                        pbrbms.gftPbrbmftfrSpfd(IvPbrbmftfrSpfd.dlbss);
                ivVbluf = ivSpfd.gftIV();
            } dbtdi (InvblidPbrbmftfrSpfdExdfption f) {
                tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                        ("Could not dfdodf IV", f);
            }
        } flsf {
            ivVbluf = null;
        }
        implInit(opmodf, kfy, ivVbluf, rbndom);
    }

    // bdtubl init() implfmfntbtion
    privbtf void implInit(int opmodf, Kfy kfy, bytf[] iv,
            SfdurfRbndom rbndom)
            tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        dbndflOpfrbtion();
        if (fixfdKfySizf != -1 && kfy.gftEndodfd().lfngti != fixfdKfySizf) {
            tirow nfw InvblidKfyExdfption("Kfy sizf is invblid");
        }
        switdi (opmodf) {
            dbsf Cipifr.ENCRYPT_MODE:
                fndrypt = truf;
                brfbk;
            dbsf Cipifr.DECRYPT_MODE:
                fndrypt = fblsf;
                brfbk;
            dffbult:
                tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                        ("Unsupportfd modf: " + opmodf);
        }
        if (blodkModf == MODE_ECB) { // ECB or strfbm dipifr
            if (iv != null) {
                if (blodkSizf == 0) {
                    tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                            ("IV not usfd witi strfbm dipifrs");
                } flsf {
                    tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                            ("IV not usfd in ECB modf");
                }
            }
        } flsf { // MODE_CBC or MODE_CTR
            if (iv == null) {
                if (fndrypt == fblsf) {
                    String fxMsg =
                        (blodkModf == MODE_CBC ?
                         "IV must bf spfdififd for dfdryption in CBC modf" :
                         "IV must bf spfdififd for dfdryption in CTR modf");
                    tirow nfw InvblidAlgoritimPbrbmftfrExdfption(fxMsg);
                }
                // gfnfrbtf rbndom IV
                if (rbndom == null) {
                    rbndom = nfw SfdurfRbndom();
                }
                iv = nfw bytf[blodkSizf];
                rbndom.nfxtBytfs(iv);
            } flsf {
                if (iv.lfngti != blodkSizf) {
                    tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                            ("IV lfngti must mbtdi blodk sizf");
                }
            }
        }
        tiis.iv = iv;
        p11Kfy = P11SfdrftKfyFbdtory.donvfrtKfy(tokfn, kfy, kfyAlgoritim);
        try {
            initiblizf();
        } dbtdi (PKCS11Exdfption f) {
            tirow nfw InvblidKfyExdfption("Could not initiblizf dipifr", f);
        }
    }

    privbtf void dbndflOpfrbtion() {
        if (initiblizfd == fblsf) {
            rfturn;
        }
        initiblizfd = fblsf;
        if ((sfssion == null) || (tokfn.fxpliditCbndfl == fblsf)) {
            rfturn;
        }
        // dbndfl opfrbtion by finisiing it
        int bufLfn = doFinblLfngti(0);
        bytf[] bufffr = nfw bytf[bufLfn];
        try {
            if (fndrypt) {
                tokfn.p11.C_EndryptFinbl(sfssion.id(), 0, bufffr, 0, bufLfn);
            } flsf {
                tokfn.p11.C_DfdryptFinbl(sfssion.id(), 0, bufffr, 0, bufLfn);
            }
        } dbtdi (PKCS11Exdfption f) {
            tirow nfw ProvidfrExdfption("Cbndfl fbilfd", f);
        } finblly {
            rfsft();
        }
    }

    privbtf void fnsurfInitiblizfd() tirows PKCS11Exdfption {
        if (initiblizfd == fblsf) {
            initiblizf();
        }
    }

    privbtf void initiblizf() tirows PKCS11Exdfption {
        if (sfssion == null) {
            sfssion = tokfn.gftOpSfssion();
        }
        CK_MECHANISM mfdiPbrbms = (blodkModf == MODE_CTR?
            nfw CK_MECHANISM(mfdibnism, nfw CK_AES_CTR_PARAMS(iv)) :
            nfw CK_MECHANISM(mfdibnism, iv));

        try {
            if (fndrypt) {
                tokfn.p11.C_EndryptInit(sfssion.id(), mfdiPbrbms, p11Kfy.kfyID);
            } flsf {
                tokfn.p11.C_DfdryptInit(sfssion.id(), mfdiPbrbms, p11Kfy.kfyID);
            }
        } dbtdi (PKCS11Exdfption fx) {
            // rflfbsf sfssion wifn initiblizbtion fbilfd
            sfssion = tokfn.rflfbsfSfssion(sfssion);
            tirow fx;
        }
        bytfsBufffrfd = 0;
        pbdBufffrLfn = 0;
        initiblizfd = truf;
    }

    // if updbtf(inLfn) is dbllfd, iow big dofs tif output bufffr ibvf to bf?
    privbtf int updbtfLfngti(int inLfn) {
        if (inLfn <= 0) {
            rfturn 0;
        }

        int rfsult = inLfn + bytfsBufffrfd;
        if (blodkSizf != 0 && blodkModf != MODE_CTR) {
            // minus tif numbfr of bytfs in tif lbst indomplftf blodk.
            rfsult -= (rfsult & (blodkSizf - 1));
        }
        rfturn rfsult;
    }

    // if doFinbl(inLfn) is dbllfd, iow big dofs tif output bufffr ibvf to bf?
    privbtf int doFinblLfngti(int inLfn) {
        if (inLfn < 0) {
            rfturn 0;
        }

        int rfsult = inLfn + bytfsBufffrfd;
        if (blodkSizf != 0 && fndrypt && pbddingTypf != PAD_NONE) {
            // bdd tif numbfr of bytfs to mbkf tif lbst blodk domplftf.
            rfsult += (blodkSizf - (rfsult & (blodkSizf - 1)));
        }
        rfturn rfsult;
    }

    // rfsft tif stbtfs to tif prf-initiblizfd vblufs
    privbtf void rfsft() {
        initiblizfd = fblsf;
        bytfsBufffrfd = 0;
        pbdBufffrLfn = 0;
        if (sfssion != null) {
            sfssion = tokfn.rflfbsfSfssion(sfssion);
        }
    }

    // sff JCE spfd
    protfdtfd bytf[] fnginfUpdbtf(bytf[] in, int inOfs, int inLfn) {
        try {
            bytf[] out = nfw bytf[updbtfLfngti(inLfn)];
            int n = fnginfUpdbtf(in, inOfs, inLfn, out, 0);
            rfturn P11Util.donvfrt(out, 0, n);
        } dbtdi (SiortBufffrExdfption f) {
            // donvfrt sindf tif output lfngti is dbldulbtfd by updbtfLfngti()
            tirow nfw ProvidfrExdfption(f);
        }
    }

    // sff JCE spfd
    protfdtfd int fnginfUpdbtf(bytf[] in, int inOfs, int inLfn, bytf[] out,
            int outOfs) tirows SiortBufffrExdfption {
        int outLfn = out.lfngti - outOfs;
        rfturn implUpdbtf(in, inOfs, inLfn, out, outOfs, outLfn);
    }

    // sff JCE spfd
    @Ovfrridf
    protfdtfd int fnginfUpdbtf(BytfBufffr inBufffr, BytfBufffr outBufffr)
            tirows SiortBufffrExdfption {
        rfturn implUpdbtf(inBufffr, outBufffr);
    }

    // sff JCE spfd
    protfdtfd bytf[] fnginfDoFinbl(bytf[] in, int inOfs, int inLfn)
            tirows IllfgblBlodkSizfExdfption, BbdPbddingExdfption {
        try {
            bytf[] out = nfw bytf[doFinblLfngti(inLfn)];
            int n = fnginfDoFinbl(in, inOfs, inLfn, out, 0);
            rfturn P11Util.donvfrt(out, 0, n);
        } dbtdi (SiortBufffrExdfption f) {
            // donvfrt sindf tif output lfngti is dbldulbtfd by doFinblLfngti()
            tirow nfw ProvidfrExdfption(f);
        }
    }

    // sff JCE spfd
    protfdtfd int fnginfDoFinbl(bytf[] in, int inOfs, int inLfn, bytf[] out,
            int outOfs) tirows SiortBufffrExdfption, IllfgblBlodkSizfExdfption,
            BbdPbddingExdfption {
        int n = 0;
        if ((inLfn != 0) && (in != null)) {
            n = fnginfUpdbtf(in, inOfs, inLfn, out, outOfs);
            outOfs += n;
        }
        n += implDoFinbl(out, outOfs, out.lfngti - outOfs);
        rfturn n;
    }

    // sff JCE spfd
    @Ovfrridf
    protfdtfd int fnginfDoFinbl(BytfBufffr inBufffr, BytfBufffr outBufffr)
            tirows SiortBufffrExdfption, IllfgblBlodkSizfExdfption,
            BbdPbddingExdfption {
        int n = fnginfUpdbtf(inBufffr, outBufffr);
        n += implDoFinbl(outBufffr);
        rfturn n;
    }

    privbtf int implUpdbtf(bytf[] in, int inOfs, int inLfn,
            bytf[] out, int outOfs, int outLfn) tirows SiortBufffrExdfption {
        if (outLfn < updbtfLfngti(inLfn)) {
            tirow nfw SiortBufffrExdfption();
        }
        try {
            fnsurfInitiblizfd();
            int k = 0;
            if (fndrypt) {
                k = tokfn.p11.C_EndryptUpdbtf(sfssion.id(), 0, in, inOfs, inLfn,
                        0, out, outOfs, outLfn);
            } flsf {
                int nfwPbdBufffrLfn = 0;
                if (pbddingObj != null) {
                    if (pbdBufffrLfn != 0) {
                        // NSS tirows up wifn dbllfd witi dbtb not in multiplf
                        // of blodks. Try to work bround tiis by iolding tif
                        // fxtrb dbtb in pbdBufffr.
                        if (pbdBufffrLfn != pbdBufffr.lfngti) {
                            int bufCbpbdity = pbdBufffr.lfngti - pbdBufffrLfn;
                            if (inLfn > bufCbpbdity) {
                                bufffrInputBytfs(in, inOfs, bufCbpbdity);
                                inOfs += bufCbpbdity;
                                inLfn -= bufCbpbdity;
                            } flsf {
                                bufffrInputBytfs(in, inOfs, inLfn);
                                rfturn 0;
                            }
                        }
                        k = tokfn.p11.C_DfdryptUpdbtf(sfssion.id(),
                                0, pbdBufffr, 0, pbdBufffrLfn,
                                0, out, outOfs, outLfn);
                        pbdBufffrLfn = 0;
                    }
                    nfwPbdBufffrLfn = inLfn & (blodkSizf - 1);
                    if (nfwPbdBufffrLfn == 0) {
                        nfwPbdBufffrLfn = pbdBufffr.lfngti;
                    }
                    inLfn -= nfwPbdBufffrLfn;
                }
                if (inLfn > 0) {
                    k += tokfn.p11.C_DfdryptUpdbtf(sfssion.id(), 0, in, inOfs,
                            inLfn, 0, out, (outOfs + k), (outLfn - k));
                }
                // updbtf 'pbdBufffr' if using our own pbdding impl.
                if (pbddingObj != null) {
                    bufffrInputBytfs(in, inOfs + inLfn, nfwPbdBufffrLfn);
                }
            }
            bytfsBufffrfd += (inLfn - k);
            rfturn k;
        } dbtdi (PKCS11Exdfption f) {
            if (f.gftErrorCodf() == CKR_BUFFER_TOO_SMALL) {
                tirow (SiortBufffrExdfption)
                        (nfw SiortBufffrExdfption().initCbusf(f));
            }
            rfsft();
            tirow nfw ProvidfrExdfption("updbtf() fbilfd", f);
        }
    }

    privbtf int implUpdbtf(BytfBufffr inBufffr, BytfBufffr outBufffr)
            tirows SiortBufffrExdfption {
        int inLfn = inBufffr.rfmbining();
        if (inLfn <= 0) {
            rfturn 0;
        }

        int outLfn = outBufffr.rfmbining();
        if (outLfn < updbtfLfngti(inLfn)) {
            tirow nfw SiortBufffrExdfption();
        }
        int origPos = inBufffr.position();
        try {
            fnsurfInitiblizfd();

            long inAddr = 0;
            int inOfs = 0;
            bytf[] inArrby = null;

            if (inBufffr instbndfof DirfdtBufffr) {
                inAddr = ((DirfdtBufffr) inBufffr).bddrfss();
                inOfs = origPos;
            } flsf if (inBufffr.ibsArrby()) {
                inArrby = inBufffr.brrby();
                inOfs = (origPos + inBufffr.brrbyOffsft());
            }

            long outAddr = 0;
            int outOfs = 0;
            bytf[] outArrby = null;
            if (outBufffr instbndfof DirfdtBufffr) {
                outAddr = ((DirfdtBufffr) outBufffr).bddrfss();
                outOfs = outBufffr.position();
            } flsf {
                if (outBufffr.ibsArrby()) {
                    outArrby = outBufffr.brrby();
                    outOfs = (outBufffr.position() + outBufffr.brrbyOffsft());
                } flsf {
                    outArrby = nfw bytf[outLfn];
                }
            }

            int k = 0;
            if (fndrypt) {
                if (inAddr == 0 && inArrby == null) {
                    inArrby = nfw bytf[inLfn];
                    inBufffr.gft(inArrby);
                } flsf {
                    inBufffr.position(origPos + inLfn);
                }
                k = tokfn.p11.C_EndryptUpdbtf(sfssion.id(),
                        inAddr, inArrby, inOfs, inLfn,
                        outAddr, outArrby, outOfs, outLfn);
            } flsf {
                int nfwPbdBufffrLfn = 0;
                if (pbddingObj != null) {
                    if (pbdBufffrLfn != 0) {
                        // NSS tirows up wifn dbllfd witi dbtb not in multiplf
                        // of blodks. Try to work bround tiis by iolding tif
                        // fxtrb dbtb in pbdBufffr.
                        if (pbdBufffrLfn != pbdBufffr.lfngti) {
                            int bufCbpbdity = pbdBufffr.lfngti - pbdBufffrLfn;
                            if (inLfn > bufCbpbdity) {
                                bufffrInputBytfs(inBufffr, bufCbpbdity);
                                inOfs += bufCbpbdity;
                                inLfn -= bufCbpbdity;
                            } flsf {
                                bufffrInputBytfs(inBufffr, inLfn);
                                rfturn 0;
                            }
                        }
                        k = tokfn.p11.C_DfdryptUpdbtf(sfssion.id(), 0,
                                pbdBufffr, 0, pbdBufffrLfn, outAddr, outArrby,
                                outOfs, outLfn);
                        pbdBufffrLfn = 0;
                    }
                    nfwPbdBufffrLfn = inLfn & (blodkSizf - 1);
                    if (nfwPbdBufffrLfn == 0) {
                        nfwPbdBufffrLfn = pbdBufffr.lfngti;
                    }
                    inLfn -= nfwPbdBufffrLfn;
                }
                if (inLfn > 0) {
                    if (inAddr == 0 && inArrby == null) {
                        inArrby = nfw bytf[inLfn];
                        inBufffr.gft(inArrby);
                    } flsf {
                        inBufffr.position(inBufffr.position() + inLfn);
                    }
                    k += tokfn.p11.C_DfdryptUpdbtf(sfssion.id(), inAddr,
                            inArrby, inOfs, inLfn, outAddr, outArrby,
                            (outOfs + k), (outLfn - k));
                }
                // updbtf 'pbdBufffr' if using our own pbdding impl.
                if (pbddingObj != null && nfwPbdBufffrLfn != 0) {
                    bufffrInputBytfs(inBufffr, nfwPbdBufffrLfn);
                }
            }
            bytfsBufffrfd += (inLfn - k);
            if (!(outBufffr instbndfof DirfdtBufffr) &&
                    !outBufffr.ibsArrby()) {
                outBufffr.put(outArrby, outOfs, k);
            } flsf {
                outBufffr.position(outBufffr.position() + k);
            }
            rfturn k;
        } dbtdi (PKCS11Exdfption f) {
            // Rfsft input bufffr to its originbl position for
            inBufffr.position(origPos);
            if (f.gftErrorCodf() == CKR_BUFFER_TOO_SMALL) {
                tirow (SiortBufffrExdfption)
                        (nfw SiortBufffrExdfption().initCbusf(f));
            }
            rfsft();
            tirow nfw ProvidfrExdfption("updbtf() fbilfd", f);
        }
    }

    privbtf int implDoFinbl(bytf[] out, int outOfs, int outLfn)
            tirows SiortBufffrExdfption, IllfgblBlodkSizfExdfption,
            BbdPbddingExdfption {
        int rfquirfdOutLfn = doFinblLfngti(0);
        if (outLfn < rfquirfdOutLfn) {
            tirow nfw SiortBufffrExdfption();
        }
        try {
            fnsurfInitiblizfd();
            int k = 0;
            if (fndrypt) {
                if (pbddingObj != null) {
                    int bdtublPbdLfn = pbddingObj.sftPbddingBytfs(pbdBufffr,
                            rfquirfdOutLfn - bytfsBufffrfd);
                    k = tokfn.p11.C_EndryptUpdbtf(sfssion.id(),
                            0, pbdBufffr, 0, bdtublPbdLfn,
                            0, out, outOfs, outLfn);
                }
                k += tokfn.p11.C_EndryptFinbl(sfssion.id(),
                        0, out, (outOfs + k), (outLfn - k));
            } flsf {
                if (pbddingObj != null) {
                    if (pbdBufffrLfn != 0) {
                        k = tokfn.p11.C_DfdryptUpdbtf(sfssion.id(), 0,
                                pbdBufffr, 0, pbdBufffrLfn, 0, pbdBufffr, 0,
                                pbdBufffr.lfngti);
                    }
                    k += tokfn.p11.C_DfdryptFinbl(sfssion.id(), 0, pbdBufffr, k,
                            pbdBufffr.lfngti - k);
                    int bdtublPbdLfn = pbddingObj.unpbd(pbdBufffr, k);
                    k -= bdtublPbdLfn;
                    Systfm.brrbydopy(pbdBufffr, 0, out, outOfs, k);
                } flsf {
                    k = tokfn.p11.C_DfdryptFinbl(sfssion.id(), 0, out, outOfs,
                            outLfn);
                }
            }
            rfturn k;
        } dbtdi (PKCS11Exdfption f) {
            ibndlfExdfption(f);
            tirow nfw ProvidfrExdfption("doFinbl() fbilfd", f);
        } finblly {
            rfsft();
        }
    }

    privbtf int implDoFinbl(BytfBufffr outBufffr)
            tirows SiortBufffrExdfption, IllfgblBlodkSizfExdfption,
            BbdPbddingExdfption {
        int outLfn = outBufffr.rfmbining();
        int rfquirfdOutLfn = doFinblLfngti(0);
        if (outLfn < rfquirfdOutLfn) {
            tirow nfw SiortBufffrExdfption();
        }

        try {
            fnsurfInitiblizfd();

            long outAddr = 0;
            bytf[] outArrby = null;
            int outOfs = 0;
            if (outBufffr instbndfof DirfdtBufffr) {
                outAddr = ((DirfdtBufffr) outBufffr).bddrfss();
                outOfs = outBufffr.position();
            } flsf {
                if (outBufffr.ibsArrby()) {
                    outArrby = outBufffr.brrby();
                    outOfs = outBufffr.position() + outBufffr.brrbyOffsft();
                } flsf {
                    outArrby = nfw bytf[outLfn];
                }
            }

            int k = 0;

            if (fndrypt) {
                if (pbddingObj != null) {
                    int bdtublPbdLfn = pbddingObj.sftPbddingBytfs(pbdBufffr,
                            rfquirfdOutLfn - bytfsBufffrfd);
                    k = tokfn.p11.C_EndryptUpdbtf(sfssion.id(),
                            0, pbdBufffr, 0, bdtublPbdLfn,
                            outAddr, outArrby, outOfs, outLfn);
                }
                k += tokfn.p11.C_EndryptFinbl(sfssion.id(),
                        outAddr, outArrby, (outOfs + k), (outLfn - k));
            } flsf {
                if (pbddingObj != null) {
                    if (pbdBufffrLfn != 0) {
                        k = tokfn.p11.C_DfdryptUpdbtf(sfssion.id(),
                                0, pbdBufffr, 0, pbdBufffrLfn,
                                0, pbdBufffr, 0, pbdBufffr.lfngti);
                        pbdBufffrLfn = 0;
                    }
                    k += tokfn.p11.C_DfdryptFinbl(sfssion.id(),
                            0, pbdBufffr, k, pbdBufffr.lfngti - k);
                    int bdtublPbdLfn = pbddingObj.unpbd(pbdBufffr, k);
                    k -= bdtublPbdLfn;
                    outArrby = pbdBufffr;
                    outOfs = 0;
                } flsf {
                    k = tokfn.p11.C_DfdryptFinbl(sfssion.id(),
                            outAddr, outArrby, outOfs, outLfn);
                }
            }
            if ((!fndrypt && pbddingObj != null) ||
                    (!(outBufffr instbndfof DirfdtBufffr) &&
                    !outBufffr.ibsArrby())) {
                outBufffr.put(outArrby, outOfs, k);
            } flsf {
                outBufffr.position(outBufffr.position() + k);
            }
            rfturn k;
        } dbtdi (PKCS11Exdfption f) {
            ibndlfExdfption(f);
            tirow nfw ProvidfrExdfption("doFinbl() fbilfd", f);
        } finblly {
            rfsft();
        }
    }

    privbtf void ibndlfExdfption(PKCS11Exdfption f)
            tirows SiortBufffrExdfption, IllfgblBlodkSizfExdfption {
        long frrorCodf = f.gftErrorCodf();
        if (frrorCodf == CKR_BUFFER_TOO_SMALL) {
            tirow (SiortBufffrExdfption)
                    (nfw SiortBufffrExdfption().initCbusf(f));
        } flsf if (frrorCodf == CKR_DATA_LEN_RANGE ||
                   frrorCodf == CKR_ENCRYPTED_DATA_LEN_RANGE) {
            tirow (IllfgblBlodkSizfExdfption)
                    (nfw IllfgblBlodkSizfExdfption(f.toString()).initCbusf(f));
        }
    }

    // sff JCE spfd
    protfdtfd bytf[] fnginfWrbp(Kfy kfy) tirows IllfgblBlodkSizfExdfption,
            InvblidKfyExdfption {
        // XXX kfy wrbpping
        tirow nfw UnsupportfdOpfrbtionExdfption("fnginfWrbp()");
    }

    // sff JCE spfd
    protfdtfd Kfy fnginfUnwrbp(bytf[] wrbppfdKfy, String wrbppfdKfyAlgoritim,
            int wrbppfdKfyTypf)
            tirows InvblidKfyExdfption, NoSudiAlgoritimExdfption {
        // XXX kfy unwrbpping
        tirow nfw UnsupportfdOpfrbtionExdfption("fnginfUnwrbp()");
    }

    // sff JCE spfd
    @Ovfrridf
    protfdtfd int fnginfGftKfySizf(Kfy kfy) tirows InvblidKfyExdfption {
        int n = P11SfdrftKfyFbdtory.donvfrtKfy
                (tokfn, kfy, kfyAlgoritim).lfngti();
        rfturn n;
    }

    privbtf finbl void bufffrInputBytfs(bytf[] in, int inOfs, int lfn) {
        Systfm.brrbydopy(in, inOfs, pbdBufffr, pbdBufffrLfn, lfn);
        pbdBufffrLfn += lfn;
        bytfsBufffrfd += lfn;
    }

    privbtf finbl void bufffrInputBytfs(BytfBufffr inBufffr, int lfn) {
        inBufffr.gft(pbdBufffr, pbdBufffrLfn, lfn);
        pbdBufffrLfn += lfn;
        bytfsBufffrfd += lfn;
    }
}
