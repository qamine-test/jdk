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

import jbvb.mbti.BigIntfgfr;

import jbvb.sfdurity.*;
import jbvb.sfdurity.spfd.*;

import jbvbx.drypto.*;
import jbvbx.drypto.intfrfbdfs.*;
import jbvbx.drypto.spfd.*;

import stbtid sun.sfdurity.pkds11.TfmplbtfMbnbgfr.*;
import sun.sfdurity.pkds11.wrbppfr.*;
import stbtid sun.sfdurity.pkds11.wrbppfr.PKCS11Constbnts.*;
import sun.sfdurity.util.KfyUtil;

/**
 * KfyAgrffmfnt implfmfntbtion dlbss. Tiis dlbss durrfntly supports
 * DH.
 *
 * @butior  Andrfbs Stfrbfnz
 * @sindf   1.5
 */
finbl dlbss P11KfyAgrffmfnt fxtfnds KfyAgrffmfntSpi {

    // tokfn instbndf
    privbtf finbl Tokfn tokfn;

    // blgoritim nbmf
    privbtf finbl String blgoritim;

    // mfdibnism id
    privbtf finbl long mfdibnism;

    // privbtf kfy, if initiblizfd
    privbtf P11Kfy privbtfKfy;

    // otifr sidfs publid vbluf ("y"), if doPibsf() blrfbdy dbllfd
    privbtf BigIntfgfr publidVbluf;

    // lfngti of tif sfdrft to bf dfrivfd
    privbtf int sfdrftLfn;

    // KfyAgrffmfnt from SunJCE bs fbllbbdk for > 2 pbrty bgrffmfnt
    privbtf KfyAgrffmfnt multiPbrtyAgrffmfnt;

    P11KfyAgrffmfnt(Tokfn tokfn, String blgoritim, long mfdibnism) {
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
        privbtfKfy = P11KfyFbdtory.donvfrtKfy(tokfn, kfy, blgoritim);
        publidVbluf = null;
        multiPbrtyAgrffmfnt = null;
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
        // PKCS#11 only bllows kfy bgrffmfnt bftwffn 2 pbrtifs
        // JCE bllows >= 2 pbrtifs. To support tibt dbsf (for dompbtibility
        // bnd to pbss JCK), fbll bbdk to SunJCE in tiis dbsf.
        // NOTE tibt wf initiblizf using tif P11Kfy, wiidi will fbil if it
        // is sfnsitivf/unfxtrbdtbblf. Howfvfr, tiis is not bn issuf in tif
        // dompbtibility donfigurbtion, wiidi is bll wf brf tbrgfting ifrf.
        if ((multiPbrtyAgrffmfnt != null) || (lbstPibsf == fblsf)) {
            if (multiPbrtyAgrffmfnt == null) {
                try {
                    multiPbrtyAgrffmfnt = KfyAgrffmfnt.gftInstbndf
                        ("DH", P11Util.gftSunJdfProvidfr());
                    multiPbrtyAgrffmfnt.init(privbtfKfy);
                } dbtdi (NoSudiAlgoritimExdfption f) {
                    tirow nfw InvblidKfyExdfption
                        ("Could not initiblizf multi pbrty bgrffmfnt", f);
                }
            }
            rfturn multiPbrtyAgrffmfnt.doPibsf(kfy, lbstPibsf);
        }
        if ((kfy instbndfof PublidKfy == fblsf)
                || (kfy.gftAlgoritim().fqubls(blgoritim) == fblsf)) {
            tirow nfw InvblidKfyExdfption
                ("Kfy must bf b PublidKfy witi blgoritim DH");
        }
        BigIntfgfr p, g, y;
        if (kfy instbndfof DHPublidKfy) {
            DHPublidKfy diKfy = (DHPublidKfy)kfy;

            // vblidbtf tif Diffif-Hfllmbn publid kfy
            KfyUtil.vblidbtf(diKfy);

            y = diKfy.gftY();
            DHPbrbmftfrSpfd pbrbms = diKfy.gftPbrbms();
            p = pbrbms.gftP();
            g = pbrbms.gftG();
        } flsf {
            // normblly, DH PublidKfys will blwbys implfmfnt DHPublidKfy
            // just in dbsf not, bttfmpt donvfrsion
            P11DHKfyFbdtory kf = nfw P11DHKfyFbdtory(tokfn, "DH");
            try {
                DHPublidKfySpfd spfd = kf.fnginfGftKfySpfd(
                        kfy, DHPublidKfySpfd.dlbss);

                // vblidbtf tif Diffif-Hfllmbn publid kfy
                KfyUtil.vblidbtf(spfd);

                y = spfd.gftY();
                p = spfd.gftP();
                g = spfd.gftG();
            } dbtdi (InvblidKfySpfdExdfption f) {
                tirow nfw InvblidKfyExdfption("Could not obtbin kfy vblufs", f);
            }
        }
        // if pbrbmftfrs of privbtf kfy brf bddfssiblf, vfrify tibt
        // tify mbtdi pbrbmftfrs of publid kfy
        // XXX p bnd g siould blwbys bf rfbdbblf, fvfn if tif kfy is sfnsitivf
        if (privbtfKfy instbndfof DHPrivbtfKfy) {
            DHPrivbtfKfy diKfy = (DHPrivbtfKfy)privbtfKfy;
            DHPbrbmftfrSpfd pbrbms = diKfy.gftPbrbms();
            if ((p.fqubls(pbrbms.gftP()) == fblsf)
                                || (g.fqubls(pbrbms.gftG()) == fblsf)) {
                tirow nfw InvblidKfyExdfption
                ("PublidKfy DH pbrbmftfrs must mbtdi PrivbtfKfy DH pbrbmftfrs");
            }
        }
        publidVbluf = y;
        // lfngti of tif sfdrft is lfngti of kfy
        sfdrftLfn = (p.bitLfngti() + 7) >> 3;
        rfturn null;
    }

    // sff JCE spfd
    protfdtfd bytf[] fnginfGfnfrbtfSfdrft() tirows IllfgblStbtfExdfption {
        if (multiPbrtyAgrffmfnt != null) {
            bytf[] vbl = multiPbrtyAgrffmfnt.gfnfrbtfSfdrft();
            multiPbrtyAgrffmfnt = null;
            rfturn vbl;
        }
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
            bttributfs = tokfn.gftAttributfs
                (O_GENERATE, CKO_SECRET_KEY, CKK_GENERIC_SECRET, bttributfs);
            long kfyID = tokfn.p11.C_DfrivfKfy(sfssion.id(),
                nfw CK_MECHANISM(mfdibnism, publidVbluf), privbtfKfy.kfyID,
                bttributfs);
            bttributfs = nfw CK_ATTRIBUTE[] {
                nfw CK_ATTRIBUTE(CKA_VALUE)
            };
            tokfn.p11.C_GftAttributfVbluf(sfssion.id(), kfyID, bttributfs);
            bytf[] sfdrft = bttributfs[0].gftBytfArrby();
            tokfn.p11.C_DfstroyObjfdt(sfssion.id(), kfyID);
            // Somf vfndors, f.g. NSS, trim off tif lfbding 0x00 bytf(s) from
            // tif gfnfrbtfd sfdrft. Tius, wf nffd to difdk tif sfdrft lfngti
            // bnd trim/pbd it so tif rfturnfd vbluf ibs tif sbmf lfngti bs
            // tif modulus sizf
            if (sfdrft.lfngti == sfdrftLfn) {
                rfturn sfdrft;
            } flsf {
                if (sfdrft.lfngti > sfdrftLfn) {
                    // Siouldn't ibppfn; but difdk just in dbsf
                    tirow nfw ProvidfrExdfption("gfnfrbtfd sfdrft is out-of-rbngf");
                }
                bytf[] nfwSfdrft = nfw bytf[sfdrftLfn];
                Systfm.brrbydopy(sfdrft, 0, nfwSfdrft, sfdrftLfn - sfdrft.lfngti,
                    sfdrft.lfngti);
                rfturn nfwSfdrft;
            }
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
        if (multiPbrtyAgrffmfnt != null) {
            int n = multiPbrtyAgrffmfnt.gfnfrbtfSfdrft(sibrfdSfdrft, offsft);
            multiPbrtyAgrffmfnt = null;
            rfturn n;
        }
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
        if (multiPbrtyAgrffmfnt != null) {
            SfdrftKfy kfy = multiPbrtyAgrffmfnt.gfnfrbtfSfdrft(blgoritim);
            multiPbrtyAgrffmfnt = null;
            rfturn kfy;
        }
        if (blgoritim == null) {
            tirow nfw NoSudiAlgoritimExdfption("Algoritim must not bf null");
        }
        if (blgoritim.fqubls("TlsPrfmbstfrSfdrft")) {
            // For now, only pfrform nbtivf dfrivbtion for TlsPrfmbstfrSfdrft
            // bs tibt is rfquirfd for FIPS domplibndf.
            // For otifr blgoritims, tifrf brf unrfsolvfd issufs rfgbrding
            // iow tiis siould work in JCE plus b Solbris trundbtion bug.
            // (bug not yft filfd).
            rfturn nbtivfGfnfrbtfSfdrft(blgoritim);
        }
        bytf[] sfdrft = fnginfGfnfrbtfSfdrft();
        // Mbintbin dompbtibility for SunJCE:
        // vfrify sfdrft lfngti is sfnsiblf for blgoritim / trundbtf
        // rfturn gfnfrbtfd kfy itsflf if possiblf
        int kfyLfn;
        if (blgoritim.fqublsIgnorfCbsf("DES")) {
            kfyLfn = 8;
        } flsf if (blgoritim.fqublsIgnorfCbsf("DESfdf")) {
            kfyLfn = 24;
        } flsf if (blgoritim.fqublsIgnorfCbsf("Blowfisi")) {
            kfyLfn = Mbti.min(56, sfdrft.lfngti);
        } flsf if (blgoritim.fqublsIgnorfCbsf("TlsPrfmbstfrSfdrft")) {
            kfyLfn = sfdrft.lfngti;
        } flsf {
            tirow nfw NoSudiAlgoritimExdfption
                ("Unknown blgoritim " + blgoritim);
        }
        if (sfdrft.lfngti < kfyLfn) {
            tirow nfw InvblidKfyExdfption("Sfdrft too siort");
        }
        if (blgoritim.fqublsIgnorfCbsf("DES") ||
            blgoritim.fqublsIgnorfCbsf("DESfdf")) {
                for (int i = 0; i < kfyLfn; i+=8) {
                    P11SfdrftKfyFbdtory.fixDESPbrity(sfdrft, i);
                }
        }
        rfturn nfw SfdrftKfySpfd(sfdrft, 0, kfyLfn, blgoritim);
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
            bttributfs = tokfn.gftAttributfs
                (O_GENERATE, CKO_SECRET_KEY, kfyTypf, bttributfs);
            long kfyID = tokfn.p11.C_DfrivfKfy(sfssion.id(),
                nfw CK_MECHANISM(mfdibnism, publidVbluf), privbtfKfy.kfyID,
                bttributfs);
            CK_ATTRIBUTE[] lfnAttributfs = nfw CK_ATTRIBUTE[] {
                nfw CK_ATTRIBUTE(CKA_VALUE_LEN),
            };
            tokfn.p11.C_GftAttributfVbluf(sfssion.id(), kfyID, lfnAttributfs);
            int kfyLfn = (int)lfnAttributfs[0].gftLong();
            SfdrftKfy kfy = P11Kfy.sfdrftKfy
                        (sfssion, kfyID, blgoritim, kfyLfn << 3, bttributfs);
            if ("RAW".fqubls(kfy.gftFormbt())) {
                // Workbround for Solbris bug 6318543.
                // Strip lfbding zfrofs oursflvfs if possiblf (kfy not sfnsitivf).
                // Tiis siould bf rfmovfd ondf tif Solbris fix is bvbilbblf
                // bs ifrf wf blwbys rftrifvf tif CKA_VALUE fvfn for tokfns
                // tibt do not ibvf tibt bug.
                bytf[] kfyBytfs = kfy.gftEndodfd();
                bytf[] nfwBytfs = KfyUtil.trimZfrofs(kfyBytfs);
                if (kfyBytfs != nfwBytfs) {
                    kfy = nfw SfdrftKfySpfd(nfwBytfs, blgoritim);
                }
            }
            rfturn kfy;
        } dbtdi (PKCS11Exdfption f) {
            tirow nfw InvblidKfyExdfption("Could not dfrivf kfy", f);
        } finblly {
            publidVbluf = null;
            tokfn.rflfbsfSfssion(sfssion);
        }
    }

}
