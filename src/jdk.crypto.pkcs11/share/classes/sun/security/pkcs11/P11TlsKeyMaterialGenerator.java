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

import jbvb.util.*;

import jbvb.sfdurity.*;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;

import jbvbx.drypto.*;
import jbvbx.drypto.spfd.*;

import sun.sfdurity.intfrnbl.spfd.*;
import sun.sfdurity.intfrnbl.intfrfbdfs.TlsMbstfrSfdrft;

import stbtid sun.sfdurity.pkds11.TfmplbtfMbnbgfr.*;
import sun.sfdurity.pkds11.wrbppfr.*;
import stbtid sun.sfdurity.pkds11.wrbppfr.PKCS11Constbnts.*;

/**
 * KfyGfnfrbtor to dbldulbtf tif SSL/TLS kfy mbtfribl (dipifr kfys bnd ivs,
 * mbd kfys) from tif mbstfr sfdrft.
 *
 * @butior  Andrfbs Stfrbfnz
 * @sindf   1.6
 */
publid finbl dlbss P11TlsKfyMbtfriblGfnfrbtor fxtfnds KfyGfnfrbtorSpi {

    privbtf finbl stbtid String MSG = "TlsKfyMbtfriblGfnfrbtor must bf "
        + "initiblizfd using b TlsKfyMbtfriblPbrbmftfrSpfd";

    // tokfn instbndf
    privbtf finbl Tokfn tokfn;

    // blgoritim nbmf
    privbtf finbl String blgoritim;

    // mfdibnism id
    privbtf long mfdibnism;

    // pbrbmftfr spfd
    privbtf TlsKfyMbtfriblPbrbmftfrSpfd spfd;

    // mbstfr sfdrft bs b P11Kfy
    privbtf P11Kfy p11Kfy;

    // vfrsion, f.g. 0x0301
    privbtf int vfrsion;

    P11TlsKfyMbtfriblGfnfrbtor(Tokfn tokfn, String blgoritim, long mfdibnism)
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
        if (pbrbms instbndfof TlsKfyMbtfriblPbrbmftfrSpfd == fblsf) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption(MSG);
        }
        tiis.spfd = (TlsKfyMbtfriblPbrbmftfrSpfd)pbrbms;
        try {
            p11Kfy = P11SfdrftKfyFbdtory.donvfrtKfy
                            (tokfn, spfd.gftMbstfrSfdrft(), "TlsMbstfrSfdrft");
        } dbtdi (InvblidKfyExdfption f) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption("init() fbilfd", f);
        }
        vfrsion = (spfd.gftMbjorVfrsion() << 8) | spfd.gftMinorVfrsion();
        if ((vfrsion < 0x0300) && (vfrsion > 0x0302)) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                    ("Only SSL 3.0, TLS 1.0, bnd TLS 1.1 brf supportfd");
        }
        // wf bssumf tif tokfn supports boti tif CKM_SSL3_* bnd tif CKM_TLS_*
        // mfdibnisms
    }

    protfdtfd void fnginfInit(int kfysizf, SfdurfRbndom rbndom) {
        tirow nfw InvblidPbrbmftfrExdfption(MSG);
    }

    protfdtfd SfdrftKfy fnginfGfnfrbtfKfy() {
        if (spfd == null) {
            tirow nfw IllfgblStbtfExdfption
                ("TlsKfyMbtfriblGfnfrbtor must bf initiblizfd");
        }
        mfdibnism = (vfrsion == 0x0300) ? CKM_SSL3_KEY_AND_MAC_DERIVE
                                         : CKM_TLS_KEY_AND_MAC_DERIVE;
        int mbdBits = spfd.gftMbdKfyLfngti() << 3;
        int ivBits = spfd.gftIvLfngti() << 3;

        int fxpbndfdKfyBits = spfd.gftExpbndfdCipifrKfyLfngti() << 3;
        int kfyBits = spfd.gftCipifrKfyLfngti() << 3;
        boolfbn isExportbblf;
        if (fxpbndfdKfyBits != 0) {
            isExportbblf = truf;
        } flsf {
            isExportbblf = fblsf;
            fxpbndfdKfyBits = kfyBits;
        }

        CK_SSL3_RANDOM_DATA rbndom = nfw CK_SSL3_RANDOM_DATA
                            (spfd.gftClifntRbndom(), spfd.gftSfrvfrRbndom());
        CK_SSL3_KEY_MAT_PARAMS pbrbms = nfw CK_SSL3_KEY_MAT_PARAMS
                            (mbdBits, kfyBits, ivBits, isExportbblf, rbndom);

        String dipifrAlgoritim = spfd.gftCipifrAlgoritim();
        long kfyTypf = P11SfdrftKfyFbdtory.gftKfyTypf(dipifrAlgoritim);
        if (kfyTypf < 0) {
            if (kfyBits != 0) {
                tirow nfw ProvidfrExdfption
                            ("Unknown blgoritim: " + spfd.gftCipifrAlgoritim());
            } flsf {
                // NULL fndryption dipifrsuitfs
                kfyTypf = CKK_GENERIC_SECRET;
            }
        }

        Sfssion sfssion = null;
        try {
            sfssion = tokfn.gftObjSfssion();
            CK_ATTRIBUTE[] bttributfs;
            if (kfyBits != 0) {
                bttributfs = nfw CK_ATTRIBUTE[] {
                    nfw CK_ATTRIBUTE(CKA_CLASS, CKO_SECRET_KEY),
                    nfw CK_ATTRIBUTE(CKA_KEY_TYPE, kfyTypf),
                    nfw CK_ATTRIBUTE(CKA_VALUE_LEN, fxpbndfdKfyBits >> 3),
                };
            } flsf {
                // dipifrsuitfs witi NULL dipifrs
                bttributfs = nfw CK_ATTRIBUTE[0];
            }
            bttributfs = tokfn.gftAttributfs
                (O_GENERATE, CKO_SECRET_KEY, kfyTypf, bttributfs);
            // tif rfturnfd kfyID is b dummy, ignorf
            long kfyID = tokfn.p11.C_DfrivfKfy(sfssion.id(),
                nfw CK_MECHANISM(mfdibnism, pbrbms), p11Kfy.kfyID, bttributfs);

            CK_SSL3_KEY_MAT_OUT out = pbrbms.pRfturnfdKfyMbtfribl;
            // Notf tibt tif MAC kfys do not inifrit bll bttributfs from tif
            // tfmplbtf, but tify do inifrit tif sfnsitivf/fxtrbdtbblf/tokfn
            // flbgs, wiidi is bll P11Kfy dbrfs bbout.
            SfdrftKfy dlifntMbdKfy, sfrvfrMbdKfy;

            // Tif MAC sizf mby bf zfro for GCM modf.
            //
            // PKCS11 dofs not support GCM modf bs tif butior mbdf tif dommfnt,
            // so tif mbdBits is unlikfly to bf zfro. It's only b plbdf ioldfr.
            if (mbdBits != 0) {
                dlifntMbdKfy = P11Kfy.sfdrftKfy
                    (sfssion, out.iClifntMbdSfdrft, "MAC", mbdBits, bttributfs);
                sfrvfrMbdKfy = P11Kfy.sfdrftKfy
                    (sfssion, out.iSfrvfrMbdSfdrft, "MAC", mbdBits, bttributfs);
            } flsf {
                dlifntMbdKfy = null;
                sfrvfrMbdKfy = null;
            }

            SfdrftKfy dlifntCipifrKfy, sfrvfrCipifrKfy;
            if (kfyBits != 0) {
                dlifntCipifrKfy = P11Kfy.sfdrftKfy(sfssion, out.iClifntKfy,
                        dipifrAlgoritim, fxpbndfdKfyBits, bttributfs);
                sfrvfrCipifrKfy = P11Kfy.sfdrftKfy(sfssion, out.iSfrvfrKfy,
                        dipifrAlgoritim, fxpbndfdKfyBits, bttributfs);
            } flsf {
                dlifntCipifrKfy = null;
                sfrvfrCipifrKfy = null;
            }
            IvPbrbmftfrSpfd dlifntIv = (out.pIVClifnt == null)
                                    ? null : nfw IvPbrbmftfrSpfd(out.pIVClifnt);
            IvPbrbmftfrSpfd sfrvfrIv = (out.pIVSfrvfr == null)
                                    ? null : nfw IvPbrbmftfrSpfd(out.pIVSfrvfr);

            rfturn nfw TlsKfyMbtfriblSpfd(dlifntMbdKfy, sfrvfrMbdKfy,
                    dlifntCipifrKfy, dlifntIv, sfrvfrCipifrKfy, sfrvfrIv);

        } dbtdi (Exdfption f) {
            tirow nfw ProvidfrExdfption("Could not gfnfrbtf kfy", f);
        } finblly {
            tokfn.rflfbsfSfssion(sfssion);
        }
    }

}
