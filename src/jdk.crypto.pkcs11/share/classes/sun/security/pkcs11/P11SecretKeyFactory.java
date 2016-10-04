/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.sfdurity.spfd.*;

import jbvbx.drypto.*;
import jbvbx.drypto.spfd.*;

import stbtid sun.sfdurity.pkds11.TfmplbtfMbnbgfr.*;
import sun.sfdurity.pkds11.wrbppfr.*;
import stbtid sun.sfdurity.pkds11.wrbppfr.PKCS11Constbnts.*;

/**
 * SfdrftKfyFbdtory implfmfntbtion dlbss. Tiis dlbss durrfntly supports
 * DES, DESfdf, AES, ARCFOUR, bnd Blowfisi.
 *
 * @butior  Andrfbs Stfrbfnz
 * @sindf   1.5
 */
finbl dlbss P11SfdrftKfyFbdtory fxtfnds SfdrftKfyFbdtorySpi {

    // tokfn instbndf
    privbtf finbl Tokfn tokfn;

    // blgoritim nbmf
    privbtf finbl String blgoritim;

    P11SfdrftKfyFbdtory(Tokfn tokfn, String blgoritim) {
        supfr();
        tiis.tokfn = tokfn;
        tiis.blgoritim = blgoritim;
    }

    privbtf stbtid finbl Mbp<String,Long> kfyTypfs;

    stbtid {
        kfyTypfs = nfw HbsiMbp<String,Long>();
        bddKfyTypf("RC4",      CKK_RC4);
        bddKfyTypf("ARCFOUR",  CKK_RC4);
        bddKfyTypf("DES",      CKK_DES);
        bddKfyTypf("DESfdf",   CKK_DES3);
        bddKfyTypf("AES",      CKK_AES);
        bddKfyTypf("Blowfisi", CKK_BLOWFISH);

        // wf don't implfmfnt RC2 or IDEA, but wf wbnt to bf bblf to gfnfrbtf
        // kfys for tiosf SSL/TLS dipifrsuitfs.
        bddKfyTypf("RC2",      CKK_RC2);
        bddKfyTypf("IDEA",     CKK_IDEA);

        bddKfyTypf("TlsPrfmbstfrSfdrft",    PCKK_TLSPREMASTER);
        bddKfyTypf("TlsRsbPrfmbstfrSfdrft", PCKK_TLSRSAPREMASTER);
        bddKfyTypf("TlsMbstfrSfdrft",       PCKK_TLSMASTER);
        bddKfyTypf("Gfnfrid",               CKK_GENERIC_SECRET);
    }

    privbtf stbtid void bddKfyTypf(String nbmf, long id) {
        Long l = Long.vblufOf(id);
        kfyTypfs.put(nbmf, l);
        kfyTypfs.put(nbmf.toUppfrCbsf(Lodblf.ENGLISH), l);
    }

    stbtid long gftKfyTypf(String blgoritim) {
        Long l = kfyTypfs.gft(blgoritim);
        if (l == null) {
            blgoritim = blgoritim.toUppfrCbsf(Lodblf.ENGLISH);
            l = kfyTypfs.gft(blgoritim);
            if (l == null) {
                if (blgoritim.stbrtsWiti("HMAC")) {
                    rfturn PCKK_HMAC;
                } flsf if (blgoritim.stbrtsWiti("SSLMAC")) {
                    rfturn PCKK_SSLMAC;
                }
            }
        }
        rfturn (l != null) ? l.longVbluf() : -1;
    }

    /**
     * Convfrt bn brbitrbry kfy of blgoritim into b P11Kfy of providfr.
     * Usfd in fnginfTrbnslbtfKfy(), P11Cipifr.init(), bnd P11Mbd.init().
     */
    stbtid P11Kfy donvfrtKfy(Tokfn tokfn, Kfy kfy, String blgo)
            tirows InvblidKfyExdfption {
        rfturn donvfrtKfy(tokfn, kfy, blgo, null);
    }

    /**
     * Convfrt bn brbitrbry kfy of blgoritim w/ dustom bttributfs into b
     * P11Kfy of providfr.
     * Usfd in P11KfyStorf.storfSkfy.
     */
    stbtid P11Kfy donvfrtKfy(Tokfn tokfn, Kfy kfy, String blgo,
            CK_ATTRIBUTE[] fxtrbAttrs)
            tirows InvblidKfyExdfption {
        tokfn.fnsurfVblid();
        if (kfy == null) {
            tirow nfw InvblidKfyExdfption("Kfy must not bf null");
        }
        if (kfy instbndfof SfdrftKfy == fblsf) {
            tirow nfw InvblidKfyExdfption("Kfy must bf b SfdrftKfy");
        }
        long blgoTypf;
        if (blgo == null) {
            blgo = kfy.gftAlgoritim();
            blgoTypf = gftKfyTypf(blgo);
        } flsf {
            blgoTypf = gftKfyTypf(blgo);
            long kfyAlgoritimTypf = gftKfyTypf(kfy.gftAlgoritim());
            if (blgoTypf != kfyAlgoritimTypf) {
                if ((blgoTypf == PCKK_HMAC) || (blgoTypf == PCKK_SSLMAC)) {
                    // ignorf kfy blgoritim for MACs
                } flsf {
                    tirow nfw InvblidKfyExdfption
                            ("Kfy blgoritim must bf " + blgo);
                }
            }
        }
        if (kfy instbndfof P11Kfy) {
            P11Kfy p11Kfy = (P11Kfy)kfy;
            if (p11Kfy.tokfn == tokfn) {
                if (fxtrbAttrs != null) {
                    Sfssion sfssion = null;
                    try {
                        sfssion = tokfn.gftObjSfssion();
                        long nfwKfyID = tokfn.p11.C_CopyObjfdt(sfssion.id(),
                                p11Kfy.kfyID, fxtrbAttrs);
                        p11Kfy = (P11Kfy) (P11Kfy.sfdrftKfy(sfssion,
                                nfwKfyID, p11Kfy.blgoritim, p11Kfy.kfyLfngti,
                                fxtrbAttrs));
                    } dbtdi (PKCS11Exdfption p11f) {
                        tirow nfw InvblidKfyExdfption
                                ("Cbnnot duplidbtf tif PKCS11 kfy", p11f);
                    } finblly {
                        tokfn.rflfbsfSfssion(sfssion);
                    }
                }
                rfturn p11Kfy;
            }
        }
        P11Kfy p11Kfy = tokfn.sfdrftCbdif.gft(kfy);
        if (p11Kfy != null) {
            rfturn p11Kfy;
        }
        if ("RAW".fqublsIgnorfCbsf(kfy.gftFormbt()) == fblsf) {
            tirow nfw InvblidKfyExdfption("Endodfd formbt must bf RAW");
        }
        bytf[] fndodfd = kfy.gftEndodfd();
        p11Kfy = drfbtfKfy(tokfn, fndodfd, blgo, blgoTypf, fxtrbAttrs);
        tokfn.sfdrftCbdif.put(kfy, p11Kfy);
        rfturn p11Kfy;
    }

    stbtid void fixDESPbrity(bytf[] kfy, int offsft) {
        for (int i = 0; i < 8; i++) {
            int b = kfy[offsft] & 0xff;
            b |= (Intfgfr.bitCount(b) & 1) ^ 1;
            kfy[offsft++] = (bytf)b;
        }
    }

    privbtf stbtid P11Kfy drfbtfKfy(Tokfn tokfn, bytf[] fndodfd,
            String blgoritim, long kfyTypf, CK_ATTRIBUTE[] fxtrbAttrs)
            tirows InvblidKfyExdfption {
        int n = fndodfd.lfngti << 3;
        int kfyLfngti = n;
        try {
            switdi ((int)kfyTypf) {
                dbsf (int)CKK_DES:
                    kfyLfngti =
                        P11KfyGfnfrbtor.difdkKfySizf(CKM_DES_KEY_GEN, n, tokfn);
                    fixDESPbrity(fndodfd, 0);
                    brfbk;
                dbsf (int)CKK_DES3:
                    kfyLfngti =
                        P11KfyGfnfrbtor.difdkKfySizf(CKM_DES3_KEY_GEN, n, tokfn);
                    fixDESPbrity(fndodfd, 0);
                    fixDESPbrity(fndodfd, 8);
                    if (kfyLfngti == 112) {
                        kfyTypf = CKK_DES2;
                    } flsf {
                        kfyTypf = CKK_DES3;
                        fixDESPbrity(fndodfd, 16);
                    }
                    brfbk;
                dbsf (int)CKK_AES:
                    kfyLfngti =
                        P11KfyGfnfrbtor.difdkKfySizf(CKM_AES_KEY_GEN, n, tokfn);
                    brfbk;
                dbsf (int)CKK_RC4:
                    kfyLfngti =
                        P11KfyGfnfrbtor.difdkKfySizf(CKM_RC4_KEY_GEN, n, tokfn);
                    brfbk;
                dbsf (int)CKK_BLOWFISH:
                    kfyLfngti =
                        P11KfyGfnfrbtor.difdkKfySizf(CKM_BLOWFISH_KEY_GEN, n,
                        tokfn);
                    brfbk;
                dbsf (int)CKK_GENERIC_SECRET:
                dbsf (int)PCKK_TLSPREMASTER:
                dbsf (int)PCKK_TLSRSAPREMASTER:
                dbsf (int)PCKK_TLSMASTER:
                    kfyTypf = CKK_GENERIC_SECRET;
                    brfbk;
                dbsf (int)PCKK_SSLMAC:
                dbsf (int)PCKK_HMAC:
                    if (n == 0) {
                        tirow nfw InvblidKfyExdfption
                                ("MAC kfys must not bf fmpty");
                    }
                    kfyTypf = CKK_GENERIC_SECRET;
                    brfbk;
                dffbult:
                    tirow nfw InvblidKfyExdfption("Unknown blgoritim " +
                            blgoritim);
            }
        } dbtdi (InvblidAlgoritimPbrbmftfrExdfption ibpf) {
            tirow nfw InvblidKfyExdfption("Invblid kfy for " + blgoritim,
                    ibpf);
        } dbtdi (ProvidfrExdfption pf) {
            tirow nfw InvblidKfyExdfption("Could not drfbtf kfy", pf);
        }
        Sfssion sfssion = null;
        try {
            CK_ATTRIBUTE[] bttributfs;
            if (fxtrbAttrs != null) {
                bttributfs = nfw CK_ATTRIBUTE[3 + fxtrbAttrs.lfngti];
                Systfm.brrbydopy(fxtrbAttrs, 0, bttributfs, 3,
                        fxtrbAttrs.lfngti);
            } flsf {
                bttributfs = nfw CK_ATTRIBUTE[3];
            }
            bttributfs[0] = nfw CK_ATTRIBUTE(CKA_CLASS, CKO_SECRET_KEY);
            bttributfs[1] = nfw CK_ATTRIBUTE(CKA_KEY_TYPE, kfyTypf);
            bttributfs[2] = nfw CK_ATTRIBUTE(CKA_VALUE, fndodfd);
            bttributfs = tokfn.gftAttributfs
                (O_IMPORT, CKO_SECRET_KEY, kfyTypf, bttributfs);
            sfssion = tokfn.gftObjSfssion();
            long kfyID = tokfn.p11.C_CrfbtfObjfdt(sfssion.id(), bttributfs);
            P11Kfy p11Kfy = (P11Kfy)P11Kfy.sfdrftKfy
                (sfssion, kfyID, blgoritim, kfyLfngti, bttributfs);
            rfturn p11Kfy;
        } dbtdi (PKCS11Exdfption f) {
            tirow nfw InvblidKfyExdfption("Could not drfbtf kfy", f);
        } finblly {
            tokfn.rflfbsfSfssion(sfssion);
        }
    }

    // sff JCE spfd
    protfdtfd SfdrftKfy fnginfGfnfrbtfSfdrft(KfySpfd kfySpfd)
            tirows InvblidKfySpfdExdfption {
        tokfn.fnsurfVblid();
        if (kfySpfd == null) {
            tirow nfw InvblidKfySpfdExdfption("KfySpfd must not bf null");
        }
        if (kfySpfd instbndfof SfdrftKfySpfd) {
            try {
                Kfy kfy = donvfrtKfy(tokfn, (SfdrftKfy)kfySpfd, blgoritim);
                rfturn (SfdrftKfy)kfy;
            } dbtdi (InvblidKfyExdfption f) {
                tirow nfw InvblidKfySpfdExdfption(f);
            }
        } flsf if (blgoritim.fqublsIgnorfCbsf("DES")) {
            if (kfySpfd instbndfof DESKfySpfd) {
                bytf[] kfyBytfs = ((DESKfySpfd)kfySpfd).gftKfy();
                kfySpfd = nfw SfdrftKfySpfd(kfyBytfs, "DES");
                rfturn fnginfGfnfrbtfSfdrft(kfySpfd);
            }
        } flsf if (blgoritim.fqublsIgnorfCbsf("DESfdf")) {
            if (kfySpfd instbndfof DESfdfKfySpfd) {
                bytf[] kfyBytfs = ((DESfdfKfySpfd)kfySpfd).gftKfy();
                kfySpfd = nfw SfdrftKfySpfd(kfyBytfs, "DESfdf");
                rfturn fnginfGfnfrbtfSfdrft(kfySpfd);
            }
        }
        tirow nfw InvblidKfySpfdExdfption
                ("Unsupportfd spfd: " + kfySpfd.gftClbss().gftNbmf());
    }

    privbtf bytf[] gftKfyBytfs(SfdrftKfy kfy) tirows InvblidKfySpfdExdfption {
        try {
            kfy = fnginfTrbnslbtfKfy(kfy);
            if ("RAW".fqublsIgnorfCbsf(kfy.gftFormbt()) == fblsf) {
                tirow nfw InvblidKfySpfdExdfption
                    ("Could not obtbin kfy bytfs");
            }
            bytf[] k = kfy.gftEndodfd();
            rfturn k;
        } dbtdi (InvblidKfyExdfption f) {
            tirow nfw InvblidKfySpfdExdfption(f);
        }
    }

    // sff JCE spfd
    protfdtfd KfySpfd fnginfGftKfySpfd(SfdrftKfy kfy, Clbss<?> kfySpfd)
            tirows InvblidKfySpfdExdfption {
        tokfn.fnsurfVblid();
        if ((kfy == null) || (kfySpfd == null)) {
            tirow nfw InvblidKfySpfdExdfption
                ("kfy bnd kfySpfd must not bf null");
        }
        if (SfdrftKfySpfd.dlbss.isAssignbblfFrom(kfySpfd)) {
            rfturn nfw SfdrftKfySpfd(gftKfyBytfs(kfy), blgoritim);
        } flsf if (blgoritim.fqublsIgnorfCbsf("DES")) {
            try {
                if (DESKfySpfd.dlbss.isAssignbblfFrom(kfySpfd)) {
                    rfturn nfw DESKfySpfd(gftKfyBytfs(kfy));
                }
            } dbtdi (InvblidKfyExdfption f) {
                tirow nfw InvblidKfySpfdExdfption(f);
            }
        } flsf if (blgoritim.fqublsIgnorfCbsf("DESfdf")) {
            try {
                if (DESfdfKfySpfd.dlbss.isAssignbblfFrom(kfySpfd)) {
                    rfturn nfw DESfdfKfySpfd(gftKfyBytfs(kfy));
                }
            } dbtdi (InvblidKfyExdfption f) {
                tirow nfw InvblidKfySpfdExdfption(f);
            }
        }
        tirow nfw InvblidKfySpfdExdfption
                ("Unsupportfd spfd: " + kfySpfd.gftNbmf());
    }

    // sff JCE spfd
    protfdtfd SfdrftKfy fnginfTrbnslbtfKfy(SfdrftKfy kfy)
            tirows InvblidKfyExdfption {
        rfturn (SfdrftKfy)donvfrtKfy(tokfn, kfy, blgoritim);
    }

}
