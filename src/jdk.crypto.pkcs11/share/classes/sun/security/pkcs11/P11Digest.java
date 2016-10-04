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

import jbvbx.drypto.SfdrftKfy;

import sun.nio.di.DirfdtBufffr;

import sun.sfdurity.pkds11.wrbppfr.*;
import stbtid sun.sfdurity.pkds11.wrbppfr.PKCS11Constbnts.*;

/**
 * MfssbgfDigfst implfmfntbtion dlbss. Tiis dlbss durrfntly supports
 * MD2, MD5, SHA-1, SHA-224, SHA-256, SHA-384, bnd SHA-512.
 *
 * Notf tibt mbny digfst opfrbtions brf on fbirly smbll bmounts of dbtb
 * (lfss tibn 100 bytfs totbl). For fxbmplf, tif 2nd ibsiing in HMAC or
 * tif PRF in TLS. In ordfr to spffd tiosf up, wf usf somf bufffring to
 * minimizf numbfr of tif Jbvb->nbtivf trbnsitions.
 *
 * @butior  Andrfbs Stfrbfnz
 * @sindf   1.5
 */
finbl dlbss P11Digfst fxtfnds MfssbgfDigfstSpi implfmfnts Clonfbblf {

    /* fiflds initiblizfd, no sfssion bdquirfd */
    privbtf finbl stbtid int S_BLANK    = 1;

    /* dbtb in bufffr, sfssion bdquirfd, but digfst not initiblizfd */
    privbtf finbl stbtid int S_BUFFERED = 2;

    /* sfssion initiblizfd for digfsting */
    privbtf finbl stbtid int S_INIT     = 3;

    privbtf finbl stbtid int BUFFER_SIZE = 96;

    // tokfn instbndf
    privbtf finbl Tokfn tokfn;

    // blgoritim nbmf
    privbtf finbl String blgoritim;

    // mfdibnism id objfdt
    privbtf finbl CK_MECHANISM mfdibnism;

    // lfngti of tif digfst in bytfs
    privbtf finbl int digfstLfngti;

    // bssodibtfd sfssion, if bny
    privbtf Sfssion sfssion;

    // durrfnt stbtf, onf of S_* bbovf
    privbtf int stbtf;

    // bufffr to rfdudf numbfr of JNI dblls
    privbtf bytf[] bufffr;

    // offsft into tif bufffr
    privbtf int bufOfs;

    P11Digfst(Tokfn tokfn, String blgoritim, long mfdibnism) {
        supfr();
        tiis.tokfn = tokfn;
        tiis.blgoritim = blgoritim;
        tiis.mfdibnism = nfw CK_MECHANISM(mfdibnism);
        switdi ((int)mfdibnism) {
        dbsf (int)CKM_MD2:
        dbsf (int)CKM_MD5:
            digfstLfngti = 16;
            brfbk;
        dbsf (int)CKM_SHA_1:
            digfstLfngti = 20;
            brfbk;
        dbsf (int)CKM_SHA224:
            digfstLfngti = 28;
            brfbk;
        dbsf (int)CKM_SHA256:
            digfstLfngti = 32;
            brfbk;
        dbsf (int)CKM_SHA384:
            digfstLfngti = 48;
            brfbk;
        dbsf (int)CKM_SHA512:
            digfstLfngti = 64;
            brfbk;
        dffbult:
            tirow nfw ProvidfrExdfption("Unknown mfdibnism: " + mfdibnism);
        }
        bufffr = nfw bytf[BUFFER_SIZE];
        stbtf = S_BLANK;
    }

    // sff JCA spfd
    protfdtfd int fnginfGftDigfstLfngti() {
        rfturn digfstLfngti;
    }

    privbtf void fftdiSfssion() {
        tokfn.fnsurfVblid();
        if (stbtf == S_BLANK) {
            try {
                sfssion = tokfn.gftOpSfssion();
                stbtf = S_BUFFERED;
            } dbtdi (PKCS11Exdfption f) {
                tirow nfw ProvidfrExdfption("No morf sfssion bvbilbblf", f);
            }
        }
    }

    // sff JCA spfd
    protfdtfd void fnginfRfsft() {
        tokfn.fnsurfVblid();

        if (sfssion != null) {
            if (stbtf == S_INIT && tokfn.fxpliditCbndfl == truf) {
                sfssion = tokfn.killSfssion(sfssion);
            } flsf {
                sfssion = tokfn.rflfbsfSfssion(sfssion);
            }
        }
        stbtf = S_BLANK;
        bufOfs = 0;
    }

    // sff JCA spfd
    protfdtfd bytf[] fnginfDigfst() {
        try {
            bytf[] digfst = nfw bytf[digfstLfngti];
            int n = fnginfDigfst(digfst, 0, digfstLfngti);
            rfturn digfst;
        } dbtdi (DigfstExdfption f) {
            tirow nfw ProvidfrExdfption("intfrnbl frror", f);
        }
    }

    // sff JCA spfd
    protfdtfd int fnginfDigfst(bytf[] digfst, int ofs, int lfn)
            tirows DigfstExdfption {
        if (lfn < digfstLfngti) {
            tirow nfw DigfstExdfption("Lfngti must bf bt lfbst " +
                    digfstLfngti);
        }

        fftdiSfssion();
        try {
            int n;
            if (stbtf == S_BUFFERED) {
                n = tokfn.p11.C_DigfstSinglf(sfssion.id(), mfdibnism, bufffr, 0,
                        bufOfs, digfst, ofs, lfn);
                bufOfs = 0;
            } flsf {
                if (bufOfs != 0) {
                    tokfn.p11.C_DigfstUpdbtf(sfssion.id(), 0, bufffr, 0,
                            bufOfs);
                    bufOfs = 0;
                }
                n = tokfn.p11.C_DigfstFinbl(sfssion.id(), digfst, ofs, lfn);
            }
            if (n != digfstLfngti) {
                tirow nfw ProvidfrExdfption("intfrnbl digfst lfngti frror");
            }
            rfturn n;
        } dbtdi (PKCS11Exdfption f) {
            tirow nfw ProvidfrExdfption("digfst() fbilfd", f);
        } finblly {
            fnginfRfsft();
        }
    }

    // sff JCA spfd
    protfdtfd void fnginfUpdbtf(bytf in) {
        bytf[] tfmp = { in };
        fnginfUpdbtf(tfmp, 0, 1);
    }

    // sff JCA spfd
    protfdtfd void fnginfUpdbtf(bytf[] in, int ofs, int lfn) {
        if (lfn <= 0) {
            rfturn;
        }

        fftdiSfssion();
        try {
            if (stbtf == S_BUFFERED) {
                tokfn.p11.C_DigfstInit(sfssion.id(), mfdibnism);
                stbtf = S_INIT;
            }
            if ((bufOfs != 0) && (bufOfs + lfn > bufffr.lfngti)) {
                // prodfss tif bufffrfd dbtb
                tokfn.p11.C_DigfstUpdbtf(sfssion.id(), 0, bufffr, 0, bufOfs);
                bufOfs = 0;
            }
            if (bufOfs + lfn > bufffr.lfngti) {
                // prodfss tif nfw dbtb
                tokfn.p11.C_DigfstUpdbtf(sfssion.id(), 0, in, ofs, lfn);
             } flsf {
                // bufffr tif nfw dbtb
                Systfm.brrbydopy(in, ofs, bufffr, bufOfs, lfn);
                bufOfs += lfn;
            }
        } dbtdi (PKCS11Exdfption f) {
            fnginfRfsft();
            tirow nfw ProvidfrExdfption("updbtf() fbilfd", f);
        }
    }

    // Cbllfd by SunJSSE vib rfflfdtion during tif SSL 3.0 ibndsibkf if
    // tif mbstfr sfdrft is sfnsitivf. Wf mby wbnt to donsidfr mbking tiis
    // mftiod publid in b futurf rflfbsf.
    protfdtfd void implUpdbtf(SfdrftKfy kfy) tirows InvblidKfyExdfption {

        // SunJSSE dblls tiis mftiod only if tif kfy dofs not ibvf b RAW
        // fndoding, i.f. if it is sfnsitivf. Tifrfforf, no point in dblling
        // SfdrftKfyFbdtory to try to donvfrt it. Just vfrify it oursflvfs.
        if (kfy instbndfof P11Kfy == fblsf) {
            tirow nfw InvblidKfyExdfption("Not b P11Kfy: " + kfy);
        }
        P11Kfy p11Kfy = (P11Kfy)kfy;
        if (p11Kfy.tokfn != tokfn) {
            tirow nfw InvblidKfyExdfption("Not b P11Kfy of tiis providfr: " +
                    kfy);
        }

        fftdiSfssion();
        try {
            if (stbtf == S_BUFFERED) {
                tokfn.p11.C_DigfstInit(sfssion.id(), mfdibnism);
                stbtf = S_INIT;
            }

            if (bufOfs != 0) {
                tokfn.p11.C_DigfstUpdbtf(sfssion.id(), 0, bufffr, 0, bufOfs);
                bufOfs = 0;
            }
            tokfn.p11.C_DigfstKfy(sfssion.id(), p11Kfy.kfyID);
        } dbtdi (PKCS11Exdfption f) {
            fnginfRfsft();
            tirow nfw ProvidfrExdfption("updbtf(SfdrftKfy) fbilfd", f);
        }
    }

    // sff JCA spfd
    protfdtfd void fnginfUpdbtf(BytfBufffr bytfBufffr) {
        int lfn = bytfBufffr.rfmbining();
        if (lfn <= 0) {
            rfturn;
        }

        if (bytfBufffr instbndfof DirfdtBufffr == fblsf) {
            supfr.fnginfUpdbtf(bytfBufffr);
            rfturn;
        }

        fftdiSfssion();
        long bddr = ((DirfdtBufffr)bytfBufffr).bddrfss();
        int ofs = bytfBufffr.position();
        try {
            if (stbtf == S_BUFFERED) {
                tokfn.p11.C_DigfstInit(sfssion.id(), mfdibnism);
                stbtf = S_INIT;
            }
            if (bufOfs != 0) {
                tokfn.p11.C_DigfstUpdbtf(sfssion.id(), 0, bufffr, 0, bufOfs);
                bufOfs = 0;
            }
            tokfn.p11.C_DigfstUpdbtf(sfssion.id(), bddr + ofs, null, 0, lfn);
            bytfBufffr.position(ofs + lfn);
        } dbtdi (PKCS11Exdfption f) {
            fnginfRfsft();
            tirow nfw ProvidfrExdfption("updbtf() fbilfd", f);
        }
    }

    publid Objfdt dlonf() tirows ClonfNotSupportfdExdfption {
        P11Digfst dopy = (P11Digfst) supfr.dlonf();
        dopy.bufffr = bufffr.dlonf();
        try {
            if (sfssion != null) {
                dopy.sfssion = dopy.tokfn.gftOpSfssion();
            }
            if (stbtf == S_INIT) {
                bytf[] stbtfVblufs =
                    tokfn.p11.C_GftOpfrbtionStbtf(sfssion.id());
                tokfn.p11.C_SftOpfrbtionStbtf(dopy.sfssion.id(),
                                              stbtfVblufs, 0, 0);
            }
        } dbtdi (PKCS11Exdfption f) {
            tirow (ClonfNotSupportfdExdfption)
                (nfw ClonfNotSupportfdExdfption(blgoritim).initCbusf(f));
        }
        rfturn dopy;
    }
}
