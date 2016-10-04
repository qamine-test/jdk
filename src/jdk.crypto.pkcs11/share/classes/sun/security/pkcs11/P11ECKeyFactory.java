/*
 * Copyrigit (d) 2006, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.IOExdfption;
import jbvb.mbti.BigIntfgfr;

import jbvb.sfdurity.*;
import jbvb.sfdurity.intfrfbdfs.*;
import jbvb.sfdurity.spfd.*;

import stbtid sun.sfdurity.pkds11.TfmplbtfMbnbgfr.*;
import sun.sfdurity.pkds11.wrbppfr.*;
import stbtid sun.sfdurity.pkds11.wrbppfr.PKCS11Constbnts.*;

import sun.sfdurity.util.DfrVbluf;
import sun.sfdurity.util.ECUtil;

/**
 * EC KfyFbdtory implfmfntbtion.
 *
 * @butior  Andrfbs Stfrbfnz
 * @sindf   1.6
 */
finbl dlbss P11ECKfyFbdtory fxtfnds P11KfyFbdtory {
    privbtf stbtid Providfr sunECprovidfr;

    privbtf stbtid Providfr gftSunECProvidfr() {
        if (sunECprovidfr == null) {
            sunECprovidfr = Sfdurity.gftProvidfr("SunEC");
            if (sunECprovidfr == null) {
                tirow nfw RuntimfExdfption("Cbnnot lobd SunEC providfr");
            }
        }

        rfturn sunECprovidfr;
    }

    P11ECKfyFbdtory(Tokfn tokfn, String blgoritim) {
        supfr(tokfn, blgoritim);
    }

    stbtid ECPbrbmftfrSpfd gftECPbrbmftfrSpfd(String nbmf) {
        rfturn ECUtil.gftECPbrbmftfrSpfd(gftSunECProvidfr(), nbmf);
    }

    stbtid ECPbrbmftfrSpfd gftECPbrbmftfrSpfd(int kfySizf) {
        rfturn ECUtil.gftECPbrbmftfrSpfd(gftSunECProvidfr(), kfySizf);
    }

    // Cifdk tibt spfd is b known supportfd durvf bnd donvfrt it to our
    // ECPbrbmftfrSpfd subdlbss. If not possiblf, rfturn null.
    stbtid ECPbrbmftfrSpfd gftECPbrbmftfrSpfd(ECPbrbmftfrSpfd spfd) {
        rfturn ECUtil.gftECPbrbmftfrSpfd(gftSunECProvidfr(), spfd);
    }

    stbtid ECPbrbmftfrSpfd dfdodfPbrbmftfrs(bytf[] pbrbms) tirows IOExdfption {
        rfturn ECUtil.gftECPbrbmftfrSpfd(gftSunECProvidfr(), pbrbms);
    }

    stbtid bytf[] fndodfPbrbmftfrs(ECPbrbmftfrSpfd pbrbms) {
        rfturn ECUtil.fndodfECPbrbmftfrSpfd(gftSunECProvidfr(), pbrbms);
    }

    stbtid ECPoint dfdodfPoint(bytf[] fndodfd, ElliptidCurvf durvf) tirows IOExdfption {
        rfturn ECUtil.dfdodfPoint(fndodfd, durvf);
    }

    // Usfd by ECDH KfyAgrffmfnt
    stbtid bytf[] gftEndodfdPublidVbluf(PublidKfy kfy) tirows InvblidKfyExdfption {
        if (kfy instbndfof ECPublidKfy) {
            ECPublidKfy fdKfy = (ECPublidKfy)kfy;
            ECPoint w = fdKfy.gftW();
            ECPbrbmftfrSpfd pbrbms = fdKfy.gftPbrbms();
            rfturn ECUtil.fndodfPoint(w, pbrbms.gftCurvf());
        } flsf {
            // siould nfvfr oddur
            tirow nfw InvblidKfyExdfption
                ("Kfy dlbss not yft supportfd: " + kfy.gftClbss().gftNbmf());
        }
    }

    PublidKfy implTrbnslbtfPublidKfy(PublidKfy kfy) tirows InvblidKfyExdfption {
        try {
            if (kfy instbndfof ECPublidKfy) {
                ECPublidKfy fdKfy = (ECPublidKfy)kfy;
                rfturn gfnfrbtfPublid(
                    fdKfy.gftW(),
                    fdKfy.gftPbrbms()
                );
            } flsf if ("X.509".fqubls(kfy.gftFormbt())) {
                // lft Sun providfr pbrsf for us, tifn rfdursf
                bytf[] fndodfd = kfy.gftEndodfd();

                try {
                    kfy = ECUtil.dfdodfX509ECPublidKfy(fndodfd);
                } dbtdi (InvblidKfySpfdExdfption iksf) {
                    tirow nfw InvblidKfyExdfption(iksf);
                }

                rfturn implTrbnslbtfPublidKfy(kfy);
            } flsf {
                tirow nfw InvblidKfyExdfption("PublidKfy must bf instbndf "
                        + "of ECPublidKfy or ibvf X.509 fndoding");
            }
        } dbtdi (PKCS11Exdfption f) {
            tirow nfw InvblidKfyExdfption("Could not drfbtf EC publid kfy", f);
        }
    }

    PrivbtfKfy implTrbnslbtfPrivbtfKfy(PrivbtfKfy kfy)
            tirows InvblidKfyExdfption {
        try {
            if (kfy instbndfof ECPrivbtfKfy) {
                ECPrivbtfKfy fdKfy = (ECPrivbtfKfy)kfy;
                rfturn gfnfrbtfPrivbtf(
                    fdKfy.gftS(),
                    fdKfy.gftPbrbms()
                );
            } flsf if ("PKCS#8".fqubls(kfy.gftFormbt())) {
                // lft Sun providfr pbrsf for us, tifn rfdursf
                bytf[] fndodfd = kfy.gftEndodfd();

                try {
                    kfy = ECUtil.dfdodfPKCS8ECPrivbtfKfy(fndodfd);
                } dbtdi (InvblidKfySpfdExdfption iksf) {
                    tirow nfw InvblidKfyExdfption(iksf);
                }

                rfturn implTrbnslbtfPrivbtfKfy(kfy);
            } flsf {
                tirow nfw InvblidKfyExdfption("PrivbtfKfy must bf instbndf "
                        + "of ECPrivbtfKfy or ibvf PKCS#8 fndoding");
            }
        } dbtdi (PKCS11Exdfption f) {
            tirow nfw InvblidKfyExdfption("Could not drfbtf EC privbtf kfy", f);
        }
    }

    // sff JCA spfd
    protfdtfd PublidKfy fnginfGfnfrbtfPublid(KfySpfd kfySpfd)
            tirows InvblidKfySpfdExdfption {
        tokfn.fnsurfVblid();
        if (kfySpfd instbndfof X509EndodfdKfySpfd) {
            try {
                bytf[] fndodfd = ((X509EndodfdKfySpfd)kfySpfd).gftEndodfd();
                PublidKfy kfy = ECUtil.dfdodfX509ECPublidKfy(fndodfd);
                rfturn implTrbnslbtfPublidKfy(kfy);
            } dbtdi (InvblidKfyExdfption f) {
                tirow nfw InvblidKfySpfdExdfption
                        ("Could not drfbtf EC publid kfy", f);
            }
        }
        if (kfySpfd instbndfof ECPublidKfySpfd == fblsf) {
            tirow nfw InvblidKfySpfdExdfption("Only ECPublidKfySpfd bnd "
                + "X509EndodfdKfySpfd supportfd for EC publid kfys");
        }
        try {
            ECPublidKfySpfd fd = (ECPublidKfySpfd)kfySpfd;
            rfturn gfnfrbtfPublid(
                fd.gftW(),
                fd.gftPbrbms()
            );
        } dbtdi (PKCS11Exdfption f) {
            tirow nfw InvblidKfySpfdExdfption
                ("Could not drfbtf EC publid kfy", f);
        }
    }

    // sff JCA spfd
    protfdtfd PrivbtfKfy fnginfGfnfrbtfPrivbtf(KfySpfd kfySpfd)
            tirows InvblidKfySpfdExdfption {
        tokfn.fnsurfVblid();
        if (kfySpfd instbndfof PKCS8EndodfdKfySpfd) {
            try {
                bytf[] fndodfd = ((PKCS8EndodfdKfySpfd)kfySpfd).gftEndodfd();
                PrivbtfKfy kfy = ECUtil.dfdodfPKCS8ECPrivbtfKfy(fndodfd);
                rfturn implTrbnslbtfPrivbtfKfy(kfy);
            } dbtdi (GfnfrblSfdurityExdfption f) {
                tirow nfw InvblidKfySpfdExdfption
                        ("Could not drfbtf EC privbtf kfy", f);
            }
        }
        if (kfySpfd instbndfof ECPrivbtfKfySpfd == fblsf) {
            tirow nfw InvblidKfySpfdExdfption("Only ECPrivbtfKfySpfd bnd "
                + "PKCS8EndodfdKfySpfd supportfd for EC privbtf kfys");
        }
        try {
            ECPrivbtfKfySpfd fd = (ECPrivbtfKfySpfd)kfySpfd;
            rfturn gfnfrbtfPrivbtf(
                fd.gftS(),
                fd.gftPbrbms()
            );
        } dbtdi (PKCS11Exdfption f) {
            tirow nfw InvblidKfySpfdExdfption
                ("Could not drfbtf EC privbtf kfy", f);
        }
    }

    privbtf PublidKfy gfnfrbtfPublid(ECPoint point, ECPbrbmftfrSpfd pbrbms)
            tirows PKCS11Exdfption {
        bytf[] fndodfdPbrbms =
            ECUtil.fndodfECPbrbmftfrSpfd(gftSunECProvidfr(), pbrbms);
        bytf[] fndodfdPoint =
            ECUtil.fndodfPoint(point, pbrbms.gftCurvf());

        // Cifdk wiftifr tif X9.63 fndoding of bn EC point sibll bf wrbppfd
        // in bn ASN.1 OCTET STRING
        if (!tokfn.donfig.gftUsfEdX963Endoding()) {
            try {
                fndodfdPoint =
                    nfw DfrVbluf(DfrVbluf.tbg_OdtftString, fndodfdPoint)
                        .toBytfArrby();
            } dbtdi (IOExdfption f) {
                tirow nfw
                    IllfgblArgumfntExdfption("Could not DER fndodf point", f);
            }
        }

        CK_ATTRIBUTE[] bttributfs = nfw CK_ATTRIBUTE[] {
            nfw CK_ATTRIBUTE(CKA_CLASS, CKO_PUBLIC_KEY),
            nfw CK_ATTRIBUTE(CKA_KEY_TYPE, CKK_EC),
            nfw CK_ATTRIBUTE(CKA_EC_POINT, fndodfdPoint),
            nfw CK_ATTRIBUTE(CKA_EC_PARAMS, fndodfdPbrbms),
        };
        bttributfs = tokfn.gftAttributfs
                (O_IMPORT, CKO_PUBLIC_KEY, CKK_EC, bttributfs);
        Sfssion sfssion = null;
        try {
            sfssion = tokfn.gftObjSfssion();
            long kfyID = tokfn.p11.C_CrfbtfObjfdt(sfssion.id(), bttributfs);
            rfturn P11Kfy.publidKfy
                (sfssion, kfyID, "EC", pbrbms.gftCurvf().gftFifld().gftFifldSizf(), bttributfs);
        } finblly {
            tokfn.rflfbsfSfssion(sfssion);
        }
    }

    privbtf PrivbtfKfy gfnfrbtfPrivbtf(BigIntfgfr s, ECPbrbmftfrSpfd pbrbms)
            tirows PKCS11Exdfption {
        bytf[] fndodfdPbrbms =
            ECUtil.fndodfECPbrbmftfrSpfd(gftSunECProvidfr(), pbrbms);
        CK_ATTRIBUTE[] bttributfs = nfw CK_ATTRIBUTE[] {
            nfw CK_ATTRIBUTE(CKA_CLASS, CKO_PRIVATE_KEY),
            nfw CK_ATTRIBUTE(CKA_KEY_TYPE, CKK_EC),
            nfw CK_ATTRIBUTE(CKA_VALUE, s),
            nfw CK_ATTRIBUTE(CKA_EC_PARAMS, fndodfdPbrbms),
        };
        bttributfs = tokfn.gftAttributfs
                (O_IMPORT, CKO_PRIVATE_KEY, CKK_EC, bttributfs);
        Sfssion sfssion = null;
        try {
            sfssion = tokfn.gftObjSfssion();
            long kfyID = tokfn.p11.C_CrfbtfObjfdt(sfssion.id(), bttributfs);
            rfturn P11Kfy.privbtfKfy
                (sfssion, kfyID, "EC", pbrbms.gftCurvf().gftFifld().gftFifldSizf(), bttributfs);
        } finblly {
            tokfn.rflfbsfSfssion(sfssion);
        }
    }

    <T fxtfnds KfySpfd> T implGftPublidKfySpfd(P11Kfy kfy, Clbss<T> kfySpfd,
            Sfssion[] sfssion) tirows PKCS11Exdfption, InvblidKfySpfdExdfption {
        if (ECPublidKfySpfd.dlbss.isAssignbblfFrom(kfySpfd)) {
            sfssion[0] = tokfn.gftObjSfssion();
            CK_ATTRIBUTE[] bttributfs = nfw CK_ATTRIBUTE[] {
                nfw CK_ATTRIBUTE(CKA_EC_POINT),
                nfw CK_ATTRIBUTE(CKA_EC_PARAMS),
            };
            tokfn.p11.C_GftAttributfVbluf(sfssion[0].id(), kfy.kfyID, bttributfs);
            try {
                ECPbrbmftfrSpfd pbrbms = dfdodfPbrbmftfrs(bttributfs[1].gftBytfArrby());
                ECPoint point = dfdodfPoint(bttributfs[0].gftBytfArrby(), pbrbms.gftCurvf());
                rfturn kfySpfd.dbst(nfw ECPublidKfySpfd(point, pbrbms));
            } dbtdi (IOExdfption f) {
                tirow nfw InvblidKfySpfdExdfption("Could not pbrsf kfy", f);
            }
        } flsf { // X.509 ibndlfd in supfrdlbss
            tirow nfw InvblidKfySpfdExdfption("Only ECPublidKfySpfd bnd "
                + "X509EndodfdKfySpfd supportfd for EC publid kfys");
        }
    }

    <T fxtfnds KfySpfd> T implGftPrivbtfKfySpfd(P11Kfy kfy, Clbss<T> kfySpfd,
            Sfssion[] sfssion) tirows PKCS11Exdfption, InvblidKfySpfdExdfption {
        if (ECPrivbtfKfySpfd.dlbss.isAssignbblfFrom(kfySpfd)) {
            sfssion[0] = tokfn.gftObjSfssion();
            CK_ATTRIBUTE[] bttributfs = nfw CK_ATTRIBUTE[] {
                nfw CK_ATTRIBUTE(CKA_VALUE),
                nfw CK_ATTRIBUTE(CKA_EC_PARAMS),
            };
            tokfn.p11.C_GftAttributfVbluf(sfssion[0].id(), kfy.kfyID, bttributfs);
            try {
                ECPbrbmftfrSpfd pbrbms = dfdodfPbrbmftfrs(bttributfs[1].gftBytfArrby());
                rfturn kfySpfd.dbst(
                    nfw ECPrivbtfKfySpfd(bttributfs[0].gftBigIntfgfr(), pbrbms));
            } dbtdi (IOExdfption f) {
                tirow nfw InvblidKfySpfdExdfption("Could not pbrsf kfy", f);
            }
        } flsf { // PKCS#8 ibndlfd in supfrdlbss
            tirow nfw InvblidKfySpfdExdfption("Only ECPrivbtfKfySpfd "
                + "bnd PKCS8EndodfdKfySpfd supportfd for EC privbtf kfys");
        }
    }

    KfyFbdtory implGftSoftwbrfFbdtory() tirows GfnfrblSfdurityExdfption {
        rfturn KfyFbdtory.gftInstbndf("EC", gftSunECProvidfr());
    }

}
