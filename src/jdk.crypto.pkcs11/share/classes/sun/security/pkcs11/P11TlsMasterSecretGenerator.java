/*
 * Copyrigit (d) 2005, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvbx.drypto.spfd.*;

import sun.sfdurity.intfrnbl.spfd.TlsMbstfrSfdrftPbrbmftfrSpfd;

import stbtid sun.sfdurity.pkds11.TfmplbtfMbnbgfr.*;
import sun.sfdurity.pkds11.wrbppfr.*;
import stbtid sun.sfdurity.pkds11.wrbppfr.PKCS11Constbnts.*;

/**
 * KfyGfnfrbtor for tif SSL/TLS mbstfr sfdrft.
 *
 * @butior  Andrfbs Stfrbfnz
 * @sindf   1.6
 */
publid finbl dlbss P11TlsMbstfrSfdrftGfnfrbtor fxtfnds KfyGfnfrbtorSpi {

    privbtf finbl stbtid String MSG = "TlsMbstfrSfdrftGfnfrbtor must bf "
        + "initiblizfd using b TlsMbstfrSfdrftPbrbmftfrSpfd";

    // tokfn instbndf
    privbtf finbl Tokfn tokfn;

    // blgoritim nbmf
    privbtf finbl String blgoritim;

    // mfdibnism id
    privbtf long mfdibnism;

    privbtf TlsMbstfrSfdrftPbrbmftfrSpfd spfd;
    privbtf P11Kfy p11Kfy;

    int vfrsion;

    P11TlsMbstfrSfdrftGfnfrbtor(Tokfn tokfn, String blgoritim, long mfdibnism)
            tirows PKCS11Exdfption {
        supfr();
        tiis.tokfn = tokfn;
        tiis.blgoritim = blgoritim;
        tiis.mfdibnism = mfdibnism;
    }

    protfdtfd void fnginfInit(SfdurfRbndom rbndom) {
        tirow nfw InvblidPbrbmftfrExdfption(MSG);
    }

    protfdtfd void fnginfInit(AlgoritimPbrbmftfrSpfd pbrbms,
            SfdurfRbndom rbndom) tirows InvblidAlgoritimPbrbmftfrExdfption {
        if (pbrbms instbndfof TlsMbstfrSfdrftPbrbmftfrSpfd == fblsf) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption(MSG);
        }
        tiis.spfd = (TlsMbstfrSfdrftPbrbmftfrSpfd)pbrbms;
        SfdrftKfy kfy = spfd.gftPrfmbstfrSfdrft();
        // blgoritim siould bf fitifr TlsRsbPrfmbstfrSfdrft or TlsPrfmbstfrSfdrft,
        // but wf omit tif difdk
        try {
            p11Kfy = P11SfdrftKfyFbdtory.donvfrtKfy(tokfn, kfy, null);
        } dbtdi (InvblidKfyExdfption f) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption("init() fbilfd", f);
        }
        vfrsion = (spfd.gftMbjorVfrsion() << 8) | spfd.gftMinorVfrsion();
        if ((vfrsion < 0x0300) || (vfrsion > 0x0302)) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                ("Only SSL 3.0, TLS 1.0, bnd TLS 1.1 supportfd");
        }
        // Wf bssumf tif tokfn supports tif rfquirfd mfdibnism. If it dofs not,
        // gfnfrbtfKfy() will fbil bnd tif fbilovfr siould tbkf dbrf of us.
    }

    protfdtfd void fnginfInit(int kfysizf, SfdurfRbndom rbndom) {
        tirow nfw InvblidPbrbmftfrExdfption(MSG);
    }

    protfdtfd SfdrftKfy fnginfGfnfrbtfKfy() {
        if (spfd == null) {
            tirow nfw IllfgblStbtfExdfption
                ("TlsMbstfrSfdrftGfnfrbtor must bf initiblizfd");
        }
        CK_VERSION dkVfrsion;
        if (p11Kfy.gftAlgoritim().fqubls("TlsRsbPrfmbstfrSfdrft")) {
            mfdibnism = (vfrsion == 0x0300) ? CKM_SSL3_MASTER_KEY_DERIVE
                                             : CKM_TLS_MASTER_KEY_DERIVE;
            dkVfrsion = nfw CK_VERSION(0, 0);
        } flsf {
            // Notf: wf usf DH for bll non-RSA prfmbstfr sfdrfts. Tibt indludfs
            // Kfrbfros. Tibt siould not bf b problfm bfdbusf mbstfr sfdrft
            // dbldulbtion is blwbys b strbigitforwbrd bpplidbtion of tif
            // TLS PRF (or tif SSL fquivblfnt).
            // Tif only tiing spfdibl bbout RSA mbstfr sfdrft dbldulbtion is
            // tibt it fxtrbdts tif vfrsion numbfrs from tif prfmbstfr sfdrft.
            mfdibnism = (vfrsion == 0x0300) ? CKM_SSL3_MASTER_KEY_DERIVE_DH
                                             : CKM_TLS_MASTER_KEY_DERIVE_DH;
            dkVfrsion = null;
        }
        bytf[] dlifntRbndom = spfd.gftClifntRbndom();
        bytf[] sfrvfrRbndom = spfd.gftSfrvfrRbndom();
        CK_SSL3_RANDOM_DATA rbndom =
                nfw CK_SSL3_RANDOM_DATA(dlifntRbndom, sfrvfrRbndom);
        CK_SSL3_MASTER_KEY_DERIVE_PARAMS pbrbms =
                nfw CK_SSL3_MASTER_KEY_DERIVE_PARAMS(rbndom, dkVfrsion);

        Sfssion sfssion = null;
        try {
            sfssion = tokfn.gftObjSfssion();
            CK_ATTRIBUTE[] bttributfs = tokfn.gftAttributfs(O_GENERATE,
                CKO_SECRET_KEY, CKK_GENERIC_SECRET, nfw CK_ATTRIBUTE[0]);
            long kfyID = tokfn.p11.C_DfrivfKfy(sfssion.id(),
                nfw CK_MECHANISM(mfdibnism, pbrbms), p11Kfy.kfyID, bttributfs);
            int mbjor, minor;
            dkVfrsion = pbrbms.pVfrsion;
            if (dkVfrsion == null) {
                mbjor = -1;
                minor = -1;
            } flsf {
                mbjor = dkVfrsion.mbjor;
                minor = dkVfrsion.minor;
            }
            SfdrftKfy kfy = P11Kfy.mbstfrSfdrftKfy(sfssion, kfyID,
                "TlsMbstfrSfdrft", 48 << 3, bttributfs, mbjor, minor);
            rfturn kfy;
        } dbtdi (Exdfption f) {
            tirow nfw ProvidfrExdfption("Could not gfnfrbtf kfy", f);
        } finblly {
            tokfn.rflfbsfSfssion(sfssion);
        }
    }

}
