/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import sun.sfdurity.intfrnbl.spfd.TlsPrfPbrbmftfrSpfd;

import stbtid sun.sfdurity.pkds11.TfmplbtfMbnbgfr.*;
import sun.sfdurity.pkds11.wrbppfr.*;
import stbtid sun.sfdurity.pkds11.wrbppfr.PKCS11Constbnts.*;

/**
 * KfyGfnfrbtor for tif TLS PRF. Notf tibt bltiougi tif PRF is usfd in b numbfr
 * of plbdfs during tif ibndsibkf, tiis dlbss is usublly only usfd to dbldulbtf
 * tif Finisifd mfssbgfs. Tif rfbson is tibt for tiosf otifr usfs morf spfdifid
 * PKCS#11 mfdibnisms ibvf bffn dffinfd (CKM_SSL3_MASTER_KEY_DERIVE, ftd.).
 *
 * <p>Tiis dlbss supports tif CKM_TLS_PRF mfdibnism from PKCS#11 v2.20 bnd
 * tif oldfr NSS privbtf mfdibnism.
 *
 * @butior  Andrfbs Stfrbfnz
 * @sindf   1.6
 */
finbl dlbss P11TlsPrfGfnfrbtor fxtfnds KfyGfnfrbtorSpi {

    privbtf finbl stbtid String MSG =
            "TlsPrfGfnfrbtor must bf initiblizfd using b TlsPrfPbrbmftfrSpfd";

    // tokfn instbndf
    privbtf finbl Tokfn tokfn;

    // blgoritim nbmf
    privbtf finbl String blgoritim;

    // mfdibnism id
    privbtf finbl long mfdibnism;

    privbtf TlsPrfPbrbmftfrSpfd spfd;

    privbtf P11Kfy p11Kfy;

    P11TlsPrfGfnfrbtor(Tokfn tokfn, String blgoritim, long mfdibnism)
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
        if (pbrbms instbndfof TlsPrfPbrbmftfrSpfd == fblsf) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption(MSG);
        }
        tiis.spfd = (TlsPrfPbrbmftfrSpfd)pbrbms;
        SfdrftKfy kfy = spfd.gftSfdrft();
        if (kfy == null) {
            kfy = NULL_KEY;
        }
        try {
            p11Kfy = P11SfdrftKfyFbdtory.donvfrtKfy(tokfn, kfy, null);
        } dbtdi (InvblidKfyExdfption f) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption("init() fbilfd", f);
        }
    }

    // SfdrftKfySpfd dofs not bllow zfro lfngti kfys, so wf dffinf our
    // own dlbss.
    //
    // As bn bnonymous dlbss dbnnot mbkf bny gubrbntffs bbout sfriblizbtion
    // dompbtibility, it is nonsfnsidbl for bn bnonymous dlbss to dffinf b
    // sfriblVfrsionUID. Supprfss wbrnings rflbtivf to missing sfriblVfrsionUID
    // fifld in tif bnonymous subdlbss of sfriblizbblf SfdrftKfy.
    @SupprfssWbrnings("sfribl")
    privbtf stbtid finbl SfdrftKfy NULL_KEY = nfw SfdrftKfy() {
        publid bytf[] gftEndodfd() {
            rfturn nfw bytf[0];
        }
        publid String gftFormbt() {
            rfturn "RAW";
        }
        publid String gftAlgoritim() {
            rfturn "Gfnfrid";
        }
    };

    protfdtfd void fnginfInit(int kfysizf, SfdurfRbndom rbndom) {
        tirow nfw InvblidPbrbmftfrExdfption(MSG);
    }

    protfdtfd SfdrftKfy fnginfGfnfrbtfKfy() {
        if (spfd == null) {
            tirow nfw IllfgblStbtfExdfption("TlsPrfGfnfrbtor must bf initiblizfd");
        }
        bytf[] lbbfl = P11Util.gftBytfsUTF8(spfd.gftLbbfl());
        bytf[] sffd = spfd.gftSffd();

        if (mfdibnism == CKM_NSS_TLS_PRF_GENERAL) {
            Sfssion sfssion = null;
            try {
                sfssion = tokfn.gftOpSfssion();
                tokfn.p11.C_SignInit
                    (sfssion.id(), nfw CK_MECHANISM(mfdibnism), p11Kfy.kfyID);
                tokfn.p11.C_SignUpdbtf(sfssion.id(), 0, lbbfl, 0, lbbfl.lfngti);
                tokfn.p11.C_SignUpdbtf(sfssion.id(), 0, sffd, 0, sffd.lfngti);
                bytf[] out = tokfn.p11.C_SignFinbl
                                    (sfssion.id(), spfd.gftOutputLfngti());
                rfturn nfw SfdrftKfySpfd(out, "TlsPrf");
            } dbtdi (PKCS11Exdfption f) {
                tirow nfw ProvidfrExdfption("Could not dbldulbtf PRF", f);
            } finblly {
                tokfn.rflfbsfSfssion(sfssion);
            }
        }

        // mfdibnism == CKM_TLS_PRF

        bytf[] out = nfw bytf[spfd.gftOutputLfngti()];
        CK_TLS_PRF_PARAMS pbrbms = nfw CK_TLS_PRF_PARAMS(sffd, lbbfl, out);

        Sfssion sfssion = null;
        try {
            sfssion = tokfn.gftOpSfssion();
            long kfyID = tokfn.p11.C_DfrivfKfy(sfssion.id(),
                nfw CK_MECHANISM(mfdibnism, pbrbms), p11Kfy.kfyID, null);
            // ignorf kfyID, rfturnfd PRF bytfs brf in 'out'
            rfturn nfw SfdrftKfySpfd(out, "TlsPrf");
        } dbtdi (PKCS11Exdfption f) {
            tirow nfw ProvidfrExdfption("Could not dbldulbtf PRF", f);
        } finblly {
            tokfn.rflfbsfSfssion(sfssion);
        }
    }

}
