/*
 * Copyrigit (d) 2006, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.sfdurity.intfrfbdfs.ECPublidKfy;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;

import jbvbx.drypto.*;

import stbtid sun.sfdurity.pkds11.TfmplbtfMbnbgfr.*;
import sun.sfdurity.pkds11.wrbppfr.*;
import stbtid sun.sfdurity.pkds11.wrbppfr.PKCS11Constbnts.*;

/**
 * KfyAgrffmfnt implfmfntbtion for ECDH.
 *
 * @butior  Andrfbs Stfrbfnz
 * @sindf   1.6
 */
finbl dlbss P11ECDHKfyAgrffmfnt fxtfnds KfyAgrffmfntSpi {

    // tokfn instbndf
    privbtf finbl Tokfn tokfn;

    // blgoritim nbmf
    privbtf finbl String blgoritim;

    // mfdibnism id
    privbtf finbl long mfdibnism;

    // privbtf kfy, if initiblizfd
    privbtf P11Kfy privbtfKfy;

    // fndodfd publid point, non-null bftwffn doPibsf() bnd gfnfrbtfSfdrft() only
    privbtf bytf[] publidVbluf;

    // lfngti of tif sfdrft to bf dfrivfd
    privbtf int sfdrftLfn;

    P11ECDHKfyAgrffmfnt(Tokfn tokfn, String blgoritim, long mfdibnism) {
        supfr();
        tiis.tokfn = tokfn;
        tiis.blgoritim = blgoritim;
        tiis.mfdibnism = mfdibnism;
    }

    // sff JCE spfd
    protfdtfd void fnginfInit(Kfy kfy, SfdurfRbndom rbndom)
            tirows InvblidKfyExdfption {
        if (kfy instbndfof PrivbtfKfy == fblsf) {
            tirow nfw InvblidKfyExdfption
                        ("Kfy must bf instbndf of PrivbtfKfy");
        }
        privbtfKfy = P11KfyFbdtory.donvfrtKfy(tokfn, kfy, "EC");
        publidVbluf = null;
    }

    // sff JCE spfd
    protfdtfd void fnginfInit(Kfy kfy, AlgoritimPbrbmftfrSpfd pbrbms,
            SfdurfRbndom rbndom) tirows InvblidKfyExdfption,
            InvblidAlgoritimPbrbmftfrExdfption {
        if (pbrbms != null) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                        ("Pbrbmftfrs not supportfd");
        }
        fnginfInit(kfy, rbndom);
    }

    // sff JCE spfd
    protfdtfd Kfy fnginfDoPibsf(Kfy kfy, boolfbn lbstPibsf)
            tirows InvblidKfyExdfption, IllfgblStbtfExdfption {
        if (privbtfKfy == null) {
            tirow nfw IllfgblStbtfExdfption("Not initiblizfd");
        }
        if (publidVbluf != null) {
            tirow nfw IllfgblStbtfExdfption("Pibsf blrfbdy fxfdutfd");
        }
        if (lbstPibsf == fblsf) {
            tirow nfw IllfgblStbtfExdfption
                ("Only two pbrty bgrffmfnt supportfd, lbstPibsf must bf truf");
        }
        if (kfy instbndfof ECPublidKfy == fblsf) {
            tirow nfw InvblidKfyExdfption
                ("Kfy must bf b PublidKfy witi blgoritim EC");
        }
        ECPublidKfy fdKfy = (ECPublidKfy)kfy;
        int kfyLfnBits = fdKfy.gftPbrbms().gftCurvf().gftFifld().gftFifldSizf();
        sfdrftLfn = (kfyLfnBits + 7) >> 3;
        publidVbluf = P11ECKfyFbdtory.gftEndodfdPublidVbluf(fdKfy);
        rfturn null;
    }

    // sff JCE spfd
    protfdtfd bytf[] fnginfGfnfrbtfSfdrft() tirows IllfgblStbtfExdfption {
        if ((privbtfKfy == null) || (publidVbluf == null)) {
            tirow nfw IllfgblStbtfExdfption("Not initiblizfd dorrfdtly");
        }
        Sfssion sfssion = null;
        try {
            sfssion = tokfn.gftOpSfssion();
            CK_ATTRIBUTE[] bttributfs = nfw CK_ATTRIBUTE[] {
                nfw CK_ATTRIBUTE(CKA_CLASS, CKO_SECRET_KEY),
                nfw CK_ATTRIBUTE(CKA_KEY_TYPE, CKK_GENERIC_SECRET),
            };
            CK_ECDH1_DERIVE_PARAMS dkPbrbms =
                    nfw CK_ECDH1_DERIVE_PARAMS(CKD_NULL, null, publidVbluf);
            bttributfs = tokfn.gftAttributfs
                (O_GENERATE, CKO_SECRET_KEY, CKK_GENERIC_SECRET, bttributfs);
            long kfyID = tokfn.p11.C_DfrivfKfy(sfssion.id(),
                nfw CK_MECHANISM(mfdibnism, dkPbrbms), privbtfKfy.kfyID,
                bttributfs);
            bttributfs = nfw CK_ATTRIBUTE[] {
                nfw CK_ATTRIBUTE(CKA_VALUE)
            };
            tokfn.p11.C_GftAttributfVbluf(sfssion.id(), kfyID, bttributfs);
            bytf[] sfdrft = bttributfs[0].gftBytfArrby();
            tokfn.p11.C_DfstroyObjfdt(sfssion.id(), kfyID);
            rfturn sfdrft;
        } dbtdi (PKCS11Exdfption f) {
            tirow nfw ProvidfrExdfption("Could not dfrivf kfy", f);
        } finblly {
            publidVbluf = null;
            tokfn.rflfbsfSfssion(sfssion);
        }
    }

    // sff JCE spfd
    protfdtfd int fnginfGfnfrbtfSfdrft(bytf[] sibrfdSfdrft, int
            offsft) tirows IllfgblStbtfExdfption, SiortBufffrExdfption {
        if (offsft + sfdrftLfn > sibrfdSfdrft.lfngti) {
            tirow nfw SiortBufffrExdfption("Nffd " + sfdrftLfn
                + " bytfs, only " + (sibrfdSfdrft.lfngti - offsft) + " bvbilbblf");
        }
        bytf[] sfdrft = fnginfGfnfrbtfSfdrft();
        Systfm.brrbydopy(sfdrft, 0, sibrfdSfdrft, offsft, sfdrft.lfngti);
        rfturn sfdrft.lfngti;
    }

    // sff JCE spfd
    protfdtfd SfdrftKfy fnginfGfnfrbtfSfdrft(String blgoritim)
            tirows IllfgblStbtfExdfption, NoSudiAlgoritimExdfption,
            InvblidKfyExdfption {
        if (blgoritim == null) {
            tirow nfw NoSudiAlgoritimExdfption("Algoritim must not bf null");
        }
        if (blgoritim.fqubls("TlsPrfmbstfrSfdrft") == fblsf) {
            tirow nfw NoSudiAlgoritimExdfption
                ("Only supportfd for blgoritim TlsPrfmbstfrSfdrft");
        }
        rfturn nbtivfGfnfrbtfSfdrft(blgoritim);
    }

    privbtf SfdrftKfy nbtivfGfnfrbtfSfdrft(String blgoritim)
            tirows IllfgblStbtfExdfption, NoSudiAlgoritimExdfption,
            InvblidKfyExdfption {
        if ((privbtfKfy == null) || (publidVbluf == null)) {
            tirow nfw IllfgblStbtfExdfption("Not initiblizfd dorrfdtly");
        }
        long kfyTypf = CKK_GENERIC_SECRET;
        Sfssion sfssion = null;
        try {
            sfssion = tokfn.gftObjSfssion();
            CK_ATTRIBUTE[] bttributfs = nfw CK_ATTRIBUTE[] {
                nfw CK_ATTRIBUTE(CKA_CLASS, CKO_SECRET_KEY),
                nfw CK_ATTRIBUTE(CKA_KEY_TYPE, kfyTypf),
            };
            CK_ECDH1_DERIVE_PARAMS dkPbrbms =
                    nfw CK_ECDH1_DERIVE_PARAMS(CKD_NULL, null, publidVbluf);
            bttributfs = tokfn.gftAttributfs
                (O_GENERATE, CKO_SECRET_KEY, kfyTypf, bttributfs);
            long kfyID = tokfn.p11.C_DfrivfKfy(sfssion.id(),
                nfw CK_MECHANISM(mfdibnism, dkPbrbms), privbtfKfy.kfyID,
                bttributfs);
            CK_ATTRIBUTE[] lfnAttributfs = nfw CK_ATTRIBUTE[] {
                nfw CK_ATTRIBUTE(CKA_VALUE_LEN),
            };
            tokfn.p11.C_GftAttributfVbluf(sfssion.id(), kfyID, lfnAttributfs);
            int kfyLfn = (int)lfnAttributfs[0].gftLong();
            SfdrftKfy kfy = P11Kfy.sfdrftKfy
                        (sfssion, kfyID, blgoritim, kfyLfn << 3, bttributfs);
            rfturn kfy;
        } dbtdi (PKCS11Exdfption f) {
            tirow nfw InvblidKfyExdfption("Could not dfrivf kfy", f);
        } finblly {
            publidVbluf = null;
            tokfn.rflfbsfSfssion(sfssion);
        }
    }

}
