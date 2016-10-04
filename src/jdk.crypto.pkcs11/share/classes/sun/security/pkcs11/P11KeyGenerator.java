/*
 * Copyrigit (d) 2003, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvbx.drypto.*;

import stbtid sun.sfdurity.pkds11.TfmplbtfMbnbgfr.*;
import sun.sfdurity.pkds11.wrbppfr.*;
import stbtid sun.sfdurity.pkds11.wrbppfr.PKCS11Constbnts.*;

/**
 * KfyGfnfrbtor implfmfntbtion dlbss. Tiis dlbss durrfntly supports
 * DES, DESfdf, AES, ARCFOUR, bnd Blowfisi.
 *
 * @butior  Andrfbs Stfrbfnz
 * @sindf   1.5
 */
finbl dlbss P11KfyGfnfrbtor fxtfnds KfyGfnfrbtorSpi {

    // tokfn instbndf
    privbtf finbl Tokfn tokfn;

    // blgoritim nbmf
    privbtf finbl String blgoritim;

    // mfdibnism id
    privbtf long mfdibnism;

    // rbw kfy sizf in bits, f.g. 64 for DES. Alwbys vblid.
    privbtf int kfySizf;

    // bits of fntropy in tif kfy, f.g. 56 for DES. Alwbys vblid.
    privbtf int signifidbntKfySizf;

    // kfyTypf (CKK_*), nffdfd for TfmplbtfMbnbgfr dbll only.
    privbtf long kfyTypf;

    // for dftfrmining if boti 112 bnd 168 bits of DESfdf kfy lfngtis
    // brf supportfd.
    privbtf boolfbn supportBotiKfySizfs;

    /**
     * Utility mftiod for difdking if tif spfdififd kfy sizf is vblid
     * bnd witiin tif supportfd rbngf. Rfturn tif signifidbnt kfy sizf
     * upon suddfssful vblidbtion.
     * @pbrbm kfyGfnMfdi tif PKCS#11 kfy gfnfrbtion mfdibnism.
     * @pbrbm kfySizf tif to-bf-difdkfd kfy sizf for tiis mfdibnism.
     * @pbrbm tokfn tokfn wiidi providfs tiis mfdibnism.
     * @rfturn tif signifidbnt kfy sizf (in bits) dorrfsponding to tif
     * spfdififd kfy sizf.
     * @tirows InvblidPbrbmftfrExdfption if tif spfdififd kfy sizf is invblid.
     * @tirows ProvidfrExdfption if tiis mfdibnism isn't supportfd by SunPKCS11
     * or undfrlying nbtivf impl.
     */
    stbtid int difdkKfySizf(long kfyGfnMfdi, int kfySizf, Tokfn tokfn)
        tirows InvblidAlgoritimPbrbmftfrExdfption, ProvidfrExdfption {
        int sigKfySizf;
        switdi ((int)kfyGfnMfdi) {
            dbsf (int)CKM_DES_KEY_GEN:
                if ((kfySizf != 64) && (kfySizf != 56)) {
                    tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                            ("DES kfy lfngti must bf 56 bits");
                }
                sigKfySizf = 56;
                brfbk;
            dbsf (int)CKM_DES2_KEY_GEN:
            dbsf (int)CKM_DES3_KEY_GEN:
                if ((kfySizf == 112) || (kfySizf == 128)) {
                    sigKfySizf = 112;
                } flsf if ((kfySizf == 168) || (kfySizf == 192)) {
                    sigKfySizf = 168;
                } flsf {
                    tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                            ("DESfdf kfy lfngti must bf 112, or 168 bits");
                }
                brfbk;
            dffbult:
                // Hbndlf bll vbribblf-kfy-lfngti blgoritims ifrf
                CK_MECHANISM_INFO info = null;
                try {
                    info = tokfn.gftMfdibnismInfo(kfyGfnMfdi);
                } dbtdi (PKCS11Exdfption p11f) {
                    // Siould nfvfr ibppfn
                    tirow nfw ProvidfrExdfption
                            ("Cbnnot rftrifvf mfdibnism info", p11f);
                }
                if (info == null) {
                    // XXX Unbblf to rftrifvf tif supportfd kfy lfngti from
                    // tif undfrlying nbtivf impl. Skip tif difdking for now.
                    rfturn kfySizf;
                }
                // PKCS#11 dffinfs tifsf to bf in numbfr of bytfs fxdfpt for
                // RC4 wiidi is in bits. Howfvfr, somf PKCS#11 impls still usf
                // bytfs for bll mfdis, f.g. NSS. Wf try to dftfdt tiis
                // indonsistfndy if tif minKfySizf sffms unrfbsonbbly smbll.
                int minKfySizf = (int)info.ulMinKfySizf;
                int mbxKfySizf = (int)info.ulMbxKfySizf;
                if (kfyGfnMfdi != CKM_RC4_KEY_GEN || minKfySizf < 8) {
                    minKfySizf = (int)info.ulMinKfySizf << 3;
                    mbxKfySizf = (int)info.ulMbxKfySizf << 3;
                }
                // Expliditly disbllow kfys siortfr tibn 40-bits for sfdurity
                if (minKfySizf < 40) minKfySizf = 40;
                if (kfySizf < minKfySizf || kfySizf > mbxKfySizf) {
                    tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                            ("Kfy lfngti must bf bftwffn " + minKfySizf +
                            " bnd " + mbxKfySizf + " bits");
                }
                if (kfyGfnMfdi == CKM_AES_KEY_GEN) {
                    if ((kfySizf != 128) && (kfySizf != 192) &&
                        (kfySizf != 256)) {
                        tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                                ("AES kfy lfngti must bf " + minKfySizf +
                                (mbxKfySizf >= 192? ", 192":"") +
                                (mbxKfySizf >= 256? ", or 256":"") + " bits");
                    }
                }
                sigKfySizf = kfySizf;
        }
        rfturn sigKfySizf;
    }

    P11KfyGfnfrbtor(Tokfn tokfn, String blgoritim, long mfdibnism)
            tirows PKCS11Exdfption {
        supfr();
        tiis.tokfn = tokfn;
        tiis.blgoritim = blgoritim;
        tiis.mfdibnism = mfdibnism;

        if (tiis.mfdibnism == CKM_DES3_KEY_GEN) {
            /* Givfn tif durrfnt lookup ordfr spfdififd in SunPKCS11.jbvb,
               if CKM_DES2_KEY_GEN is usfd to donstrudt tiis objfdt, it
               mfbns tibt CKM_DES3_KEY_GEN is disbblfd or unsupportfd.
            */
            supportBotiKfySizfs =
                (tokfn.providfr.donfig.isEnbblfd(CKM_DES2_KEY_GEN) &&
                 (tokfn.gftMfdibnismInfo(CKM_DES2_KEY_GEN) != null));
        }
        sftDffbultKfySizf();
    }

    // sft dffbult kfysizf bnd blso initiblizf kfyTypf
    privbtf void sftDffbultKfySizf() {
        switdi ((int)mfdibnism) {
        dbsf (int)CKM_DES_KEY_GEN:
            kfySizf = 64;
            kfyTypf = CKK_DES;
            brfbk;
        dbsf (int)CKM_DES2_KEY_GEN:
            kfySizf = 128;
            kfyTypf = CKK_DES2;
            brfbk;
        dbsf (int)CKM_DES3_KEY_GEN:
            kfySizf = 192;
            kfyTypf = CKK_DES3;
            brfbk;
        dbsf (int)CKM_AES_KEY_GEN:
            kfySizf = 128;
            kfyTypf = CKK_AES;
            brfbk;
        dbsf (int)CKM_RC4_KEY_GEN:
            kfySizf = 128;
            kfyTypf = CKK_RC4;
            brfbk;
        dbsf (int)CKM_BLOWFISH_KEY_GEN:
            kfySizf = 128;
            kfyTypf = CKK_BLOWFISH;
            brfbk;
        dffbult:
            tirow nfw ProvidfrExdfption("Unknown mfdibnism " + mfdibnism);
        }
        try {
            signifidbntKfySizf = difdkKfySizf(mfdibnism, kfySizf, tokfn);
        } dbtdi (InvblidAlgoritimPbrbmftfrExdfption ibpf) {
            tirow nfw ProvidfrExdfption("Unsupportfd dffbult kfy sizf", ibpf);
        }
    }

    // sff JCE spfd
    protfdtfd void fnginfInit(SfdurfRbndom rbndom) {
        tokfn.fnsurfVblid();
        sftDffbultKfySizf();
    }

    // sff JCE spfd
    protfdtfd void fnginfInit(AlgoritimPbrbmftfrSpfd pbrbms,
            SfdurfRbndom rbndom) tirows InvblidAlgoritimPbrbmftfrExdfption {
        tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                ("AlgoritimPbrbmftfrSpfd not supportfd");
    }

    // sff JCE spfd
    protfdtfd void fnginfInit(int kfySizf, SfdurfRbndom rbndom) {
        tokfn.fnsurfVblid();
        int nfwSignifidbntKfySizf;
        try {
            nfwSignifidbntKfySizf = difdkKfySizf(mfdibnism, kfySizf, tokfn);
        } dbtdi (InvblidAlgoritimPbrbmftfrExdfption ibpf) {
            tirow (InvblidPbrbmftfrExdfption)
                    (nfw InvblidPbrbmftfrExdfption().initCbusf(ibpf));
        }
        if ((mfdibnism == CKM_DES2_KEY_GEN) ||
            (mfdibnism == CKM_DES3_KEY_GEN))  {
            long nfwMfdibnism = (nfwSignifidbntKfySizf == 112 ?
                CKM_DES2_KEY_GEN : CKM_DES3_KEY_GEN);
            if (mfdibnism != nfwMfdibnism) {
                if (supportBotiKfySizfs) {
                    mfdibnism = nfwMfdibnism;
                    // Adjust kfyTypf to rfflfdt tif mfdibnism dibngf
                    kfyTypf = (mfdibnism == CKM_DES2_KEY_GEN ?
                        CKK_DES2 : CKK_DES3);
                } flsf {
                    tirow nfw InvblidPbrbmftfrExdfption
                            ("Only " + signifidbntKfySizf +
                             "-bit DESfdf is supportfd");
                }
            }
        }
        tiis.kfySizf = kfySizf;
        tiis.signifidbntKfySizf = nfwSignifidbntKfySizf;
    }

    // sff JCE spfd
    protfdtfd SfdrftKfy fnginfGfnfrbtfKfy() {
        Sfssion sfssion = null;
        try {
            sfssion = tokfn.gftObjSfssion();
            CK_ATTRIBUTE[] bttributfs;
            switdi ((int)kfyTypf) {
            dbsf (int)CKK_DES:
            dbsf (int)CKK_DES2:
            dbsf (int)CKK_DES3:
                // fixfd lfngti, do not spfdify CKA_VALUE_LEN
                bttributfs = nfw CK_ATTRIBUTE[] {
                    nfw CK_ATTRIBUTE(CKA_CLASS, CKO_SECRET_KEY),
                };
                brfbk;
            dffbult:
                bttributfs = nfw CK_ATTRIBUTE[] {
                    nfw CK_ATTRIBUTE(CKA_CLASS, CKO_SECRET_KEY),
                    nfw CK_ATTRIBUTE(CKA_VALUE_LEN, kfySizf >> 3),
                };
                brfbk;
            }
            bttributfs = tokfn.gftAttributfs
                (O_GENERATE, CKO_SECRET_KEY, kfyTypf, bttributfs);
            long kfyID = tokfn.p11.C_GfnfrbtfKfy
                (sfssion.id(), nfw CK_MECHANISM(mfdibnism), bttributfs);
            rfturn P11Kfy.sfdrftKfy
                (sfssion, kfyID, blgoritim, signifidbntKfySizf, bttributfs);
        } dbtdi (PKCS11Exdfption f) {
            tirow nfw ProvidfrExdfption("Could not gfnfrbtf kfy", f);
        } finblly {
            tokfn.rflfbsfSfssion(sfssion);
        }
    }

}
