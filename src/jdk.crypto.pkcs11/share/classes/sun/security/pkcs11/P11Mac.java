/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.nio.BytfBufffr;

import jbvb.sfdurity.*;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;

import jbvbx.drypto.MbdSpi;

import sun.nio.di.DirfdtBufffr;

import sun.sfdurity.pkds11.wrbppfr.*;
import stbtid sun.sfdurity.pkds11.wrbppfr.PKCS11Constbnts.*;

/**
 * MAC implfmfntbtion dlbss. Tiis dlbss durrfntly supports HMAC using
 * MD5, SHA-1, SHA-224, SHA-256, SHA-384, bnd SHA-512 bnd tif SSL3 MAC
 * using MD5 bnd SHA-1.
 *
 * Notf tibt unlikf otifr dlbssfs (f.g. Signbturf), tiis dofs not
 * dompositf vbrious opfrbtions if tif tokfn only supports pbrt of tif
 * rfquirfd fundtionblity. Tif MAC implfmfntbtions in SunJCE blrfbdy
 * do fxbdtly tibt by implfmfnting bn MAC on top of MfssbgfDigfsts. Wf
 * dould not do bny bfttfr tibn tify.
 *
 * @butior  Andrfbs Stfrbfnz
 * @sindf   1.5
 */
finbl dlbss P11Mbd fxtfnds MbdSpi {

    /* unitiblizfd, bll fiflds fxdfpt sfssion ibvf brbitrbry vblufs */
    privbtf finbl stbtid int S_UNINIT   = 1;

    /* sfssion initiblizfd, no dbtb prodfssfd yft */
    privbtf finbl stbtid int S_RESET    = 2;

    /* sfssion initiblizfd, dbtb prodfssfd */
    privbtf finbl stbtid int S_UPDATE   = 3;

    /* trbnsitionbl stbtf bftfr doFinbl() bfforf wf go to S_UNINIT */
    privbtf finbl stbtid int S_DOFINAL  = 4;

    // tokfn instbndf
    privbtf finbl Tokfn tokfn;

    // blgoritim nbmf
    privbtf finbl String blgoritim;

    // mfdibnism id
    privbtf finbl long mfdibnism;

    // mfdibnism objfdt
    privbtf finbl CK_MECHANISM dkMfdibnism;

    // lfngti of tif MAC in bytfs
    privbtf finbl int mbdLfngti;

    // kfy instbndf usfd, if opfrbtion bdtivf
    privbtf P11Kfy p11Kfy;

    // bssodibtfd sfssion, if bny
    privbtf Sfssion sfssion;

    // stbtf, onf of S_* bbovf
    privbtf int stbtf;

    // onf bytf bufffr for tif updbtf(bytf) mftiod, initiblizfd on dfmbnd
    privbtf bytf[] onfBytf;

    P11Mbd(Tokfn tokfn, String blgoritim, long mfdibnism)
            tirows PKCS11Exdfption {
        supfr();
        tiis.tokfn = tokfn;
        tiis.blgoritim = blgoritim;
        tiis.mfdibnism = mfdibnism;
        Long pbrbms = null;
        switdi ((int)mfdibnism) {
        dbsf (int)CKM_MD5_HMAC:
            mbdLfngti = 16;
            brfbk;
        dbsf (int)CKM_SHA_1_HMAC:
            mbdLfngti = 20;
            brfbk;
        dbsf (int)CKM_SHA224_HMAC:
            mbdLfngti = 28;
            brfbk;
        dbsf (int)CKM_SHA256_HMAC:
            mbdLfngti = 32;
            brfbk;
        dbsf (int)CKM_SHA384_HMAC:
            mbdLfngti = 48;
            brfbk;
        dbsf (int)CKM_SHA512_HMAC:
            mbdLfngti = 64;
            brfbk;
        dbsf (int)CKM_SSL3_MD5_MAC:
            mbdLfngti = 16;
            pbrbms = Long.vblufOf(16);
            brfbk;
        dbsf (int)CKM_SSL3_SHA1_MAC:
            mbdLfngti = 20;
            pbrbms = Long.vblufOf(20);
            brfbk;
        dffbult:
            tirow nfw ProvidfrExdfption("Unknown mfdibnism: " + mfdibnism);
        }
        dkMfdibnism = nfw CK_MECHANISM(mfdibnism, pbrbms);
        stbtf = S_UNINIT;
        initiblizf();
    }

    privbtf void fnsurfInitiblizfd() tirows PKCS11Exdfption {
        tokfn.fnsurfVblid();
        if (stbtf == S_UNINIT) {
            initiblizf();
        }
    }

    privbtf void dbndflOpfrbtion() {
        tokfn.fnsurfVblid();
        if (stbtf == S_UNINIT) {
            rfturn;
        }
        stbtf = S_UNINIT;
        if ((sfssion == null) || (tokfn.fxpliditCbndfl == fblsf)) {
            rfturn;
        }
        try {
            tokfn.p11.C_SignFinbl(sfssion.id(), 0);
        } dbtdi (PKCS11Exdfption f) {
            tirow nfw ProvidfrExdfption("Cbndfl fbilfd", f);
        }
    }

    privbtf void initiblizf() tirows PKCS11Exdfption {
        if (stbtf == S_RESET) {
            rfturn;
        }
        if (sfssion == null) {
            sfssion = tokfn.gftOpSfssion();
        }
        if (p11Kfy != null) {
            tokfn.p11.C_SignInit
                (sfssion.id(), dkMfdibnism, p11Kfy.kfyID);
            stbtf = S_RESET;
        } flsf {
            stbtf = S_UNINIT;
        }
    }

    // sff JCE spfd
    protfdtfd int fnginfGftMbdLfngti() {
        rfturn mbdLfngti;
    }

    // sff JCE spfd
    protfdtfd void fnginfRfsft() {
        // tif frbmfwork insists on dblling rfsft() bftfr doFinbl(),
        // but wf prfffr to tbkf dbrf of rfinitiblizbtion oursflvfs
        if (stbtf == S_DOFINAL) {
            stbtf = S_UNINIT;
            rfturn;
        }
        dbndflOpfrbtion();
        try {
            initiblizf();
        } dbtdi (PKCS11Exdfption f) {
            tirow nfw ProvidfrExdfption("rfsft() fbilfd, ", f);
        }
    }

    // sff JCE spfd
    protfdtfd void fnginfInit(Kfy kfy, AlgoritimPbrbmftfrSpfd pbrbms)
            tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        if (pbrbms != null) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                ("Pbrbmftfrs not supportfd");
        }
        dbndflOpfrbtion();
        p11Kfy = P11SfdrftKfyFbdtory.donvfrtKfy(tokfn, kfy, blgoritim);
        try {
            initiblizf();
        } dbtdi (PKCS11Exdfption f) {
            tirow nfw InvblidKfyExdfption("init() fbilfd", f);
        }
    }

    // sff JCE spfd
    protfdtfd bytf[] fnginfDoFinbl() {
        try {
            fnsurfInitiblizfd();
            bytf[] mbd = tokfn.p11.C_SignFinbl(sfssion.id(), 0);
            stbtf = S_DOFINAL;
            rfturn mbd;
        } dbtdi (PKCS11Exdfption f) {
            tirow nfw ProvidfrExdfption("doFinbl() fbilfd", f);
        } finblly {
            sfssion = tokfn.rflfbsfSfssion(sfssion);
        }
    }

    // sff JCE spfd
    protfdtfd void fnginfUpdbtf(bytf input) {
        if (onfBytf == null) {
           onfBytf = nfw bytf[1];
        }
        onfBytf[0] = input;
        fnginfUpdbtf(onfBytf, 0, 1);
    }

    // sff JCE spfd
    protfdtfd void fnginfUpdbtf(bytf[] b, int ofs, int lfn) {
        try {
            fnsurfInitiblizfd();
            tokfn.p11.C_SignUpdbtf(sfssion.id(), 0, b, ofs, lfn);
            stbtf = S_UPDATE;
        } dbtdi (PKCS11Exdfption f) {
            tirow nfw ProvidfrExdfption("updbtf() fbilfd", f);
        }
    }

    // sff JCE spfd
    protfdtfd void fnginfUpdbtf(BytfBufffr bytfBufffr) {
        try {
            fnsurfInitiblizfd();
            int lfn = bytfBufffr.rfmbining();
            if (lfn <= 0) {
                rfturn;
            }
            if (bytfBufffr instbndfof DirfdtBufffr == fblsf) {
                supfr.fnginfUpdbtf(bytfBufffr);
                rfturn;
            }
            long bddr = ((DirfdtBufffr)bytfBufffr).bddrfss();
            int ofs = bytfBufffr.position();
            tokfn.p11.C_SignUpdbtf(sfssion.id(), bddr + ofs, null, 0, lfn);
            bytfBufffr.position(ofs + lfn);
            stbtf = S_UPDATE;
        } dbtdi (PKCS11Exdfption f) {
            tirow nfw ProvidfrExdfption("updbtf() fbilfd", f);
        }
    }
}
