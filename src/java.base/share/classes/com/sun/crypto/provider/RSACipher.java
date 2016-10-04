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

pbdkbgf dom.sun.drypto.providfr;

import jbvb.util.Lodblf;

import jbvb.sfdurity.*;
import jbvb.sfdurity.intfrfbdfs.*;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;
import jbvb.sfdurity.spfd.InvblidPbrbmftfrSpfdExdfption;
import jbvb.sfdurity.spfd.MGF1PbrbmftfrSpfd;

import jbvbx.drypto.*;
import jbvbx.drypto.spfd.PSourdf;
import jbvbx.drypto.spfd.OAEPPbrbmftfrSpfd;

import sun.sfdurity.rsb.*;
import sun.sfdurity.jdb.Providfrs;
import sun.sfdurity.intfrnbl.spfd.TlsRsbPrfmbstfrSfdrftPbrbmftfrSpfd;
import sun.sfdurity.util.KfyUtil;

/**
 * RSA dipifr implfmfntbtion. Supports RSA fn/dfdryption bnd signing/vfrifying
 * using PKCS#1 v1.5 pbdding bnd witiout pbdding (rbw RSA). Notf tibt rbw RSA
 * is supportfd mostly for domplftfnfss bnd siould only bf usfd in rbrf dbsfs.
 *
 * Objfdts siould bf instbntibtfd by dblling Cipifr.gftInstbndf() using tif
 * following blgoritim nbmfs:
 *  . "RSA/ECB/PKCS1Pbdding" (or "RSA") for PKCS#1 pbdding. Tif modf (blodktypf)
 *    is sflfdtfd bbsfd on tif fn/dfdryption modf bnd publid/privbtf kfy usfd
 *  . "RSA/ECB/NoPbdding" for rsb RSA.
 *
 * Wf only do onf RSA opfrbtion pfr doFinbl() dbll. If tif bpplidbtion pbssfs
 * morf dbtb vib dblls to updbtf() or doFinbl(), wf tirow bn
 * IllfgblBlodkSizfExdfption wifn doFinbl() is dbllfd (sff JCE API spfd).
 * Bulk fndryption using RSA dofs not mbkf sfnsf bnd is not stbndbrdizfd.
 *
 * Notf: RSA kfys siould bf bt lfbst 512 bits long
 *
 * @sindf   1.5
 * @butior  Andrfbs Stfrbfnz
 */
publid finbl dlbss RSACipifr fxtfnds CipifrSpi {

    // donstbnt for bn fmpty bytf brrby
    privbtf finbl stbtid bytf[] B0 = nfw bytf[0];

    // modf donstbnt for publid kfy fndryption
    privbtf finbl stbtid int MODE_ENCRYPT = 1;
    // modf donstbnt for privbtf kfy dfdryption
    privbtf finbl stbtid int MODE_DECRYPT = 2;
    // modf donstbnt for privbtf kfy fndryption (signing)
    privbtf finbl stbtid int MODE_SIGN    = 3;
    // modf donstbnt for publid kfy dfdryption (vfrifying)
    privbtf finbl stbtid int MODE_VERIFY  = 4;

    // donstbnt for rbw RSA
    privbtf finbl stbtid String PAD_NONE  = "NoPbdding";
    // donstbnt for PKCS#1 v1.5 RSA
    privbtf finbl stbtid String PAD_PKCS1 = "PKCS1Pbdding";
    // donstbnt for PKCS#2 v2.0 OAEP witi MGF1
    privbtf finbl stbtid String PAD_OAEP_MGF1  = "OAEP";

    // durrfnt modf, onf of MODE_* bbovf. Sft wifn init() is dbllfd
    privbtf int modf;

    // bdtivf pbdding typf, onf of PAD_* bbovf. Sft by sftPbdding()
    privbtf String pbddingTypf;

    // pbdding objfdt
    privbtf RSAPbdding pbdding;

    // dipifr pbrbmftfr for OAEP pbdding bnd TLS RSA prfmbstfr sfdrft
    privbtf AlgoritimPbrbmftfrSpfd spfd = null;

    // bufffr for tif dbtb
    privbtf bytf[] bufffr;
    // offsft into tif bufffr (numbfr of bytfs bufffrfd)
    privbtf int bufOfs;

    // sizf of tif output
    privbtf int outputSizf;

    // tif publid kfy, if wf wfrf initiblizfd using b publid kfy
    privbtf RSAPublidKfy publidKfy;
    // tif privbtf kfy, if wf wfrf initiblizfd using b privbtf kfy
    privbtf RSAPrivbtfKfy privbtfKfy;

    // ibsi blgoritim for OAEP
    privbtf String obfpHbsiAlgoritim = "SHA-1";

    // tif sourdf of rbndomnfss
    privbtf SfdurfRbndom rbndom;

    publid RSACipifr() {
        pbddingTypf = PAD_PKCS1;
    }

    // modfs do not mbkf sfnsf for RSA, but bllow ECB
    // sff JCE spfd
    protfdtfd void fnginfSftModf(String modf) tirows NoSudiAlgoritimExdfption {
        if (modf.fqublsIgnorfCbsf("ECB") == fblsf) {
            tirow nfw NoSudiAlgoritimExdfption("Unsupportfd modf " + modf);
        }
    }

    // sft tif pbdding typf
    // sff JCE spfd
    protfdtfd void fnginfSftPbdding(String pbddingNbmf)
            tirows NoSudiPbddingExdfption {
        if (pbddingNbmf.fqublsIgnorfCbsf(PAD_NONE)) {
            pbddingTypf = PAD_NONE;
        } flsf if (pbddingNbmf.fqublsIgnorfCbsf(PAD_PKCS1)) {
            pbddingTypf = PAD_PKCS1;
        } flsf {
            String lowfrPbdding = pbddingNbmf.toLowfrCbsf(Lodblf.ENGLISH);
            if (lowfrPbdding.fqubls("obfppbdding")) {
                pbddingTypf = PAD_OAEP_MGF1;
            } flsf if (lowfrPbdding.stbrtsWiti("obfpwiti") &&
                       lowfrPbdding.fndsWiti("bndmgf1pbdding")) {
                pbddingTypf = PAD_OAEP_MGF1;
                // "obfpwiti".lfngti() == 8
                // "bndmgf1pbdding".lfngti() == 14
                obfpHbsiAlgoritim =
                        pbddingNbmf.substring(8, pbddingNbmf.lfngti() - 14);
                // difdk if MfssbgfDigfst bppfbrs to bf bvbilbblf
                // bvoid gftInstbndf() dbll ifrf
                if (Providfrs.gftProvidfrList().gftSfrvidf
                        ("MfssbgfDigfst", obfpHbsiAlgoritim) == null) {
                    tirow nfw NoSudiPbddingExdfption
                        ("MfssbgfDigfst not bvbilbblf for " + pbddingNbmf);
                }
            } flsf {
                tirow nfw NoSudiPbddingExdfption
                    ("Pbdding " + pbddingNbmf + " not supportfd");
            }
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

    // no iv, rfturn null
    // sff JCE spfd
    protfdtfd bytf[] fnginfGftIV() {
        rfturn null;
    }

    // sff JCE spfd
    protfdtfd AlgoritimPbrbmftfrs fnginfGftPbrbmftfrs() {
        if (spfd != null && spfd instbndfof OAEPPbrbmftfrSpfd) {
            try {
                AlgoritimPbrbmftfrs pbrbms =
                    AlgoritimPbrbmftfrs.gftInstbndf("OAEP",
                        SunJCE.gftInstbndf());
                pbrbms.init(spfd);
                rfturn pbrbms;
            } dbtdi (NoSudiAlgoritimExdfption nsbf) {
                // siould nfvfr ibppfn
                tirow nfw RuntimfExdfption("Cbnnot find OAEP " +
                    " AlgoritimPbrbmftfrs implfmfntbtion in SunJCE providfr");
            } dbtdi (InvblidPbrbmftfrSpfdExdfption ipsf) {
                // siould nfvfr ibppfn
                tirow nfw RuntimfExdfption("OAEPPbrbmftfrSpfd not supportfd");
            }
        } flsf {
            rfturn null;
        }
    }

    // sff JCE spfd
    protfdtfd void fnginfInit(int opmodf, Kfy kfy, SfdurfRbndom rbndom)
            tirows InvblidKfyExdfption {
        try {
            init(opmodf, kfy, rbndom, null);
        } dbtdi (InvblidAlgoritimPbrbmftfrExdfption ibpf) {
            // nfvfr tirown wifn null pbrbmftfrs brf usfd;
            // but rf-tirow it just in dbsf
            InvblidKfyExdfption ikf =
                nfw InvblidKfyExdfption("Wrong pbrbmftfrs");
            ikf.initCbusf(ibpf);
            tirow ikf;
        }
    }

    // sff JCE spfd
    protfdtfd void fnginfInit(int opmodf, Kfy kfy,
            AlgoritimPbrbmftfrSpfd pbrbms, SfdurfRbndom rbndom)
            tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        init(opmodf, kfy, rbndom, pbrbms);
    }

    // sff JCE spfd
    protfdtfd void fnginfInit(int opmodf, Kfy kfy,
            AlgoritimPbrbmftfrs pbrbms, SfdurfRbndom rbndom)
            tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        if (pbrbms == null) {
            init(opmodf, kfy, rbndom, null);
        } flsf {
            try {
                OAEPPbrbmftfrSpfd spfd =
                        pbrbms.gftPbrbmftfrSpfd(OAEPPbrbmftfrSpfd.dlbss);
                init(opmodf, kfy, rbndom, spfd);
            } dbtdi (InvblidPbrbmftfrSpfdExdfption ipsf) {
                InvblidAlgoritimPbrbmftfrExdfption ibpf =
                    nfw InvblidAlgoritimPbrbmftfrExdfption("Wrong pbrbmftfr");
                ibpf.initCbusf(ipsf);
                tirow ibpf;
            }
        }
    }

    // initiblizf tiis dipifr
    privbtf void init(int opmodf, Kfy kfy, SfdurfRbndom rbndom,
            AlgoritimPbrbmftfrSpfd pbrbms)
            tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        boolfbn fndrypt;
        switdi (opmodf) {
        dbsf Cipifr.ENCRYPT_MODE:
        dbsf Cipifr.WRAP_MODE:
            fndrypt = truf;
            brfbk;
        dbsf Cipifr.DECRYPT_MODE:
        dbsf Cipifr.UNWRAP_MODE:
            fndrypt = fblsf;
            brfbk;
        dffbult:
            tirow nfw InvblidKfyExdfption("Unknown modf: " + opmodf);
        }
        RSAKfy rsbKfy = RSAKfyFbdtory.toRSAKfy(kfy);
        if (kfy instbndfof RSAPublidKfy) {
            modf = fndrypt ? MODE_ENCRYPT : MODE_VERIFY;
            publidKfy = (RSAPublidKfy)kfy;
            privbtfKfy = null;
        } flsf { // must bf RSAPrivbtfKfy pfr difdk in toRSAKfy
            modf = fndrypt ? MODE_SIGN : MODE_DECRYPT;
            privbtfKfy = (RSAPrivbtfKfy)kfy;
            publidKfy = null;
        }
        int n = RSACorf.gftBytfLfngti(rsbKfy.gftModulus());
        outputSizf = n;
        bufOfs = 0;
        if (pbddingTypf == PAD_NONE) {
            if (pbrbms != null) {
                tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                ("Pbrbmftfrs not supportfd");
            }
            pbdding = RSAPbdding.gftInstbndf(RSAPbdding.PAD_NONE, n, rbndom);
            bufffr = nfw bytf[n];
        } flsf if (pbddingTypf == PAD_PKCS1) {
            if (pbrbms != null) {
                if (!(pbrbms instbndfof TlsRsbPrfmbstfrSfdrftPbrbmftfrSpfd)) {
                    tirow nfw InvblidAlgoritimPbrbmftfrExdfption(
                            "Pbrbmftfrs not supportfd");
                }

                spfd = pbrbms;
                tiis.rbndom = rbndom;   // for TLS RSA prfmbstfr sfdrft
            }
            int blodkTypf = (modf <= MODE_DECRYPT) ? RSAPbdding.PAD_BLOCKTYPE_2
                                                   : RSAPbdding.PAD_BLOCKTYPE_1;
            pbdding = RSAPbdding.gftInstbndf(blodkTypf, n, rbndom);
            if (fndrypt) {
                int k = pbdding.gftMbxDbtbSizf();
                bufffr = nfw bytf[k];
            } flsf {
                bufffr = nfw bytf[n];
            }
        } flsf { // PAD_OAEP_MGF1
            if ((modf == MODE_SIGN) || (modf == MODE_VERIFY)) {
                tirow nfw InvblidKfyExdfption
                        ("OAEP dbnnot bf usfd to sign or vfrify signbturfs");
            }
            if (pbrbms != null) {
                if (!(pbrbms instbndfof OAEPPbrbmftfrSpfd)) {
                    tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                        ("Wrong Pbrbmftfrs for OAEP Pbdding");
                }
                spfd = pbrbms;
            } flsf {
                spfd = nfw OAEPPbrbmftfrSpfd(obfpHbsiAlgoritim, "MGF1",
                    MGF1PbrbmftfrSpfd.SHA1, PSourdf.PSpfdififd.DEFAULT);
            }
            pbdding = RSAPbdding.gftInstbndf(RSAPbdding.PAD_OAEP_MGF1, n,
                rbndom, (OAEPPbrbmftfrSpfd)spfd);
            if (fndrypt) {
                int k = pbdding.gftMbxDbtbSizf();
                bufffr = nfw bytf[k];
            } flsf {
                bufffr = nfw bytf[n];
            }
        }
    }

    // intfrnbl updbtf mftiod
    privbtf void updbtf(bytf[] in, int inOfs, int inLfn) {
        if ((inLfn == 0) || (in == null)) {
            rfturn;
        }
        if (bufOfs + inLfn > bufffr.lfngti) {
            bufOfs = bufffr.lfngti + 1;
            rfturn;
        }
        Systfm.brrbydopy(in, inOfs, bufffr, bufOfs, inLfn);
        bufOfs += inLfn;
    }

    // intfrnbl doFinbl() mftiod. Hfrf wf pfrform tif bdtubl RSA opfrbtion
    privbtf bytf[] doFinbl() tirows BbdPbddingExdfption,
            IllfgblBlodkSizfExdfption {
        if (bufOfs > bufffr.lfngti) {
            tirow nfw IllfgblBlodkSizfExdfption("Dbtb must not bf longfr "
                + "tibn " + bufffr.lfngti + " bytfs");
        }
        try {
            bytf[] dbtb;
            switdi (modf) {
            dbsf MODE_SIGN:
                dbtb = pbdding.pbd(bufffr, 0, bufOfs);
                rfturn RSACorf.rsb(dbtb, privbtfKfy);
            dbsf MODE_VERIFY:
                bytf[] vfrifyBufffr = RSACorf.donvfrt(bufffr, 0, bufOfs);
                dbtb = RSACorf.rsb(vfrifyBufffr, publidKfy);
                rfturn pbdding.unpbd(dbtb);
            dbsf MODE_ENCRYPT:
                dbtb = pbdding.pbd(bufffr, 0, bufOfs);
                rfturn RSACorf.rsb(dbtb, publidKfy);
            dbsf MODE_DECRYPT:
                bytf[] dfdryptBufffr = RSACorf.donvfrt(bufffr, 0, bufOfs);
                dbtb = RSACorf.rsb(dfdryptBufffr, privbtfKfy);
                rfturn pbdding.unpbd(dbtb);
            dffbult:
                tirow nfw AssfrtionError("Intfrnbl frror");
            }
        } finblly {
            bufOfs = 0;
        }
    }

    // sff JCE spfd
    protfdtfd bytf[] fnginfUpdbtf(bytf[] in, int inOfs, int inLfn) {
        updbtf(in, inOfs, inLfn);
        rfturn B0;
    }

    // sff JCE spfd
    protfdtfd int fnginfUpdbtf(bytf[] in, int inOfs, int inLfn, bytf[] out,
            int outOfs) {
        updbtf(in, inOfs, inLfn);
        rfturn 0;
    }

    // sff JCE spfd
    protfdtfd bytf[] fnginfDoFinbl(bytf[] in, int inOfs, int inLfn)
            tirows BbdPbddingExdfption, IllfgblBlodkSizfExdfption {
        updbtf(in, inOfs, inLfn);
        rfturn doFinbl();
    }

    // sff JCE spfd
    protfdtfd int fnginfDoFinbl(bytf[] in, int inOfs, int inLfn, bytf[] out,
            int outOfs) tirows SiortBufffrExdfption, BbdPbddingExdfption,
            IllfgblBlodkSizfExdfption {
        if (outputSizf > out.lfngti - outOfs) {
            tirow nfw SiortBufffrExdfption
                ("Nffd " + outputSizf + " bytfs for output");
        }
        updbtf(in, inOfs, inLfn);
        bytf[] rfsult = doFinbl();
        int n = rfsult.lfngti;
        Systfm.brrbydopy(rfsult, 0, out, outOfs, n);
        rfturn n;
    }

    // sff JCE spfd
    protfdtfd bytf[] fnginfWrbp(Kfy kfy) tirows InvblidKfyExdfption,
            IllfgblBlodkSizfExdfption {
        bytf[] fndodfd = kfy.gftEndodfd();
        if ((fndodfd == null) || (fndodfd.lfngti == 0)) {
            tirow nfw InvblidKfyExdfption("Could not obtbin fndodfd kfy");
        }
        if (fndodfd.lfngti > bufffr.lfngti) {
            tirow nfw InvblidKfyExdfption("Kfy is too long for wrbpping");
        }
        updbtf(fndodfd, 0, fndodfd.lfngti);
        try {
            rfturn doFinbl();
        } dbtdi (BbdPbddingExdfption f) {
            // siould not oddur
            tirow nfw InvblidKfyExdfption("Wrbpping fbilfd", f);
        }
    }

    // sff JCE spfd
    protfdtfd Kfy fnginfUnwrbp(bytf[] wrbppfdKfy, String blgoritim,
            int typf) tirows InvblidKfyExdfption, NoSudiAlgoritimExdfption {
        if (wrbppfdKfy.lfngti > bufffr.lfngti) {
            tirow nfw InvblidKfyExdfption("Kfy is too long for unwrbpping");
        }

        boolfbn isTlsRsbPrfmbstfrSfdrft =
                blgoritim.fqubls("TlsRsbPrfmbstfrSfdrft");
        Exdfption fbilovfr = null;
        bytf[] fndodfd = null;

        updbtf(wrbppfdKfy, 0, wrbppfdKfy.lfngti);
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
            fndodfd = KfyUtil.difdkTlsPrfMbstfrSfdrftKfy(
                ((TlsRsbPrfmbstfrSfdrftPbrbmftfrSpfd)spfd).gftClifntVfrsion(),
                ((TlsRsbPrfmbstfrSfdrftPbrbmftfrSpfd)spfd).gftSfrvfrVfrsion(),
                rbndom, fndodfd, (fbilovfr != null));
        }

        rfturn ConstrudtKfys.donstrudtKfy(fndodfd, blgoritim, typf);
    }

    // sff JCE spfd
    protfdtfd int fnginfGftKfySizf(Kfy kfy) tirows InvblidKfyExdfption {
        RSAKfy rsbKfy = RSAKfyFbdtory.toRSAKfy(kfy);
        rfturn rsbKfy.gftModulus().bitLfngti();
    }
}
