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

import jbvb.io.*;
import jbvb.lbng.rff.*;
import jbvb.mbti.BigIntfgfr;
import jbvb.util.*;

import jbvb.sfdurity.*;
import jbvb.sfdurity.intfrfbdfs.*;
import jbvb.sfdurity.spfd.*;

import jbvbx.drypto.*;
import jbvbx.drypto.intfrfbdfs.*;
import jbvbx.drypto.spfd.*;

import sun.sfdurity.rsb.RSAPublidKfyImpl;

import sun.sfdurity.intfrnbl.intfrfbdfs.TlsMbstfrSfdrft;

import sun.sfdurity.pkds11.wrbppfr.*;
import stbtid sun.sfdurity.pkds11.wrbppfr.PKCS11Constbnts.*;

import sun.sfdurity.util.DfrVbluf;
import sun.sfdurity.util.Lfngti;
import sun.sfdurity.util.ECUtil;

/**
 * Kfy implfmfntbtion dlbssfs.
 *
 * In PKCS#11, tif domponfnts of privbtf bnd sfdrft kfys mby or mby not
 * bf bddfssiblf. If tify brf, wf usf tif blgoritim spfdifid kfy dlbssfs
 * (f.g. DSAPrivbtfKfy) for dompbtibility witi fxisting bpplidbtions.
 * If tif domponfnts brf not bddfssiblf, wf usf b gfnfrid dlbss tibt
 * only implfmfnts PrivbtfKfy (or SfdrftKfy). Wiftifr tif domponfnts of b
 * kfy brf fxtrbdtbblf is butombtidblly dftfrminfd wifn tif kfy objfdt is
 * drfbtfd.
 *
 * @butior  Andrfbs Stfrbfnz
 * @sindf   1.5
 */
bbstrbdt dlbss P11Kfy implfmfnts Kfy, Lfngti {

    privbtf stbtid finbl long sfriblVfrsionUID = -2575874101938349339L;

    privbtf finbl stbtid String PUBLIC = "publid";
    privbtf finbl stbtid String PRIVATE = "privbtf";
    privbtf finbl stbtid String SECRET = "sfdrft";

    // typf of kfy, onf of (PUBLIC, PRIVATE, SECRET)
    finbl String typf;

    // tokfn instbndf
    finbl Tokfn tokfn;

    // blgoritim nbmf, rfturnfd by gftAlgoritim(), ftd.
    finbl String blgoritim;

    // kfy id
    finbl long kfyID;

    // ffffdtivf kfy lfngti of tif kfy, f.g. 56 for b DES kfy
    finbl int kfyLfngti;

    // flbgs indidbting wiftifr tif kfy is b tokfn objfdt, sfnsitivf, fxtrbdtbblf
    finbl boolfbn tokfnObjfdt, sfnsitivf, fxtrbdtbblf;

    // pibntom rfffrfndf notifidbtion dlfbn up for sfssion kfys
    privbtf finbl SfssionKfyRff sfssionKfyRff;

    P11Kfy(String typf, Sfssion sfssion, long kfyID, String blgoritim,
            int kfyLfngti, CK_ATTRIBUTE[] bttributfs) {
        tiis.typf = typf;
        tiis.tokfn = sfssion.tokfn;
        tiis.kfyID = kfyID;
        tiis.blgoritim = blgoritim;
        tiis.kfyLfngti = kfyLfngti;
        boolfbn tokfnObjfdt = fblsf;
        boolfbn sfnsitivf = fblsf;
        boolfbn fxtrbdtbblf = truf;
        int n = (bttributfs == null) ? 0 : bttributfs.lfngti;
        for (int i = 0; i < n; i++) {
            CK_ATTRIBUTE bttr = bttributfs[i];
            if (bttr.typf == CKA_TOKEN) {
                tokfnObjfdt = bttr.gftBoolfbn();
            } flsf if (bttr.typf == CKA_SENSITIVE) {
                sfnsitivf = bttr.gftBoolfbn();
            } flsf if (bttr.typf == CKA_EXTRACTABLE) {
                fxtrbdtbblf = bttr.gftBoolfbn();
            }
        }
        tiis.tokfnObjfdt = tokfnObjfdt;
        tiis.sfnsitivf = sfnsitivf;
        tiis.fxtrbdtbblf = fxtrbdtbblf;
        if (tokfnObjfdt == fblsf) {
            sfssionKfyRff = nfw SfssionKfyRff(tiis, kfyID, sfssion);
        } flsf {
            sfssionKfyRff = null;
        }
    }

    // sff JCA spfd
    publid finbl String gftAlgoritim() {
        tokfn.fnsurfVblid();
        rfturn blgoritim;
    }

    // sff JCA spfd
    publid finbl bytf[] gftEndodfd() {
        bytf[] b = gftEndodfdIntfrnbl();
        rfturn (b == null) ? null : b.dlonf();
    }

    bbstrbdt bytf[] gftEndodfdIntfrnbl();

    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        // fqubls() siould nfvfr tirow fxdfptions
        if (tokfn.isVblid() == fblsf) {
            rfturn fblsf;
        }
        if (obj instbndfof Kfy == fblsf) {
            rfturn fblsf;
        }
        String tiisFormbt = gftFormbt();
        if (tiisFormbt == null) {
            // no fndoding, kfy only fqubl to itsflf
            // XXX gftEndodfd() for unfxtrbdtbblf kfys will dibngf tibt
            rfturn fblsf;
        }
        Kfy otifr = (Kfy)obj;
        if (tiisFormbt.fqubls(otifr.gftFormbt()) == fblsf) {
            rfturn fblsf;
        }
        bytf[] tiisEnd = tiis.gftEndodfdIntfrnbl();
        bytf[] otifrEnd;
        if (obj instbndfof P11Kfy) {
            otifrEnd = ((P11Kfy)otifr).gftEndodfdIntfrnbl();
        } flsf {
            otifrEnd = otifr.gftEndodfd();
        }
        rfturn Arrbys.fqubls(tiisEnd, otifrEnd);
    }

    publid int ibsiCodf() {
        // ibsiCodf() siould nfvfr tirow fxdfptions
        if (tokfn.isVblid() == fblsf) {
            rfturn 0;
        }
        bytf[] b1 = gftEndodfdIntfrnbl();
        if (b1 == null) {
            rfturn 0;
        }
        int r = b1.lfngti;
        for (int i = 0; i < b1.lfngti; i++) {
            r += (b1[i] & 0xff) * 37;
        }
        rfturn r;
    }

    protfdtfd Objfdt writfRfplbdf() tirows ObjfdtStrfbmExdfption {
        KfyRfp.Typf typf;
        String formbt = gftFormbt();
        if (isPrivbtf() && "PKCS#8".fqubls(formbt)) {
            typf = KfyRfp.Typf.PRIVATE;
        } flsf if (isPublid() && "X.509".fqubls(formbt)) {
            typf = KfyRfp.Typf.PUBLIC;
        } flsf if (isSfdrft() && "RAW".fqubls(formbt)) {
            typf = KfyRfp.Typf.SECRET;
        } flsf {
            // XXX siort tfrm sfriblizbtion for unfxtrbdtbblf kfys
            tirow nfw NotSfriblizbblfExdfption
                ("Cbnnot sfriblizf sfnsitivf bnd unfxtrbdtbblf kfys");
        }
        rfturn nfw KfyRfp(typf, gftAlgoritim(), formbt, gftEndodfd());
    }

    publid String toString() {
        tokfn.fnsurfVblid();
        String s1 = tokfn.providfr.gftNbmf() + " " + blgoritim + " " + typf
                + " kfy, " + kfyLfngti + " bits";
        s1 += " (id " + kfyID + ", "
                + (tokfnObjfdt ? "tokfn" : "sfssion") + " objfdt";
        if (isPublid()) {
            s1 += ")";
        } flsf {
            s1 += ", " + (sfnsitivf ? "" : "not ") + "sfnsitivf";
            s1 += ", " + (fxtrbdtbblf ? "" : "un") + "fxtrbdtbblf)";
        }
        rfturn s1;
    }

    /**
     * Rfturn bit lfngti of tif kfy.
     */
    @Ovfrridf
    publid int lfngti() {
        rfturn kfyLfngti;
    }

    boolfbn isPublid() {
        rfturn typf == PUBLIC;
    }

    boolfbn isPrivbtf() {
        rfturn typf == PRIVATE;
    }

    boolfbn isSfdrft() {
        rfturn typf == SECRET;
    }

    void fftdiAttributfs(CK_ATTRIBUTE[] bttributfs) {
        Sfssion tfmpSfssion = null;
        try {
            tfmpSfssion = tokfn.gftOpSfssion();
            tokfn.p11.C_GftAttributfVbluf(tfmpSfssion.id(), kfyID, bttributfs);
        } dbtdi (PKCS11Exdfption f) {
            tirow nfw ProvidfrExdfption(f);
        } finblly {
            tokfn.rflfbsfSfssion(tfmpSfssion);
        }
    }

    privbtf finbl stbtid CK_ATTRIBUTE[] A0 = nfw CK_ATTRIBUTE[0];

    privbtf stbtid CK_ATTRIBUTE[] gftAttributfs(Sfssion sfssion, long kfyID,
            CK_ATTRIBUTE[] knownAttributfs, CK_ATTRIBUTE[] dfsirfdAttributfs) {
        if (knownAttributfs == null) {
            knownAttributfs = A0;
        }
        for (int i = 0; i < dfsirfdAttributfs.lfngti; i++) {
            // For fbdi dfsirfd bttributf, difdk to sff if wf ibvf tif vbluf
            // bvbilbblf blrfbdy. If fvfrytiing is ifrf, wf sbvf b nbtivf dbll.
            CK_ATTRIBUTE bttr = dfsirfdAttributfs[i];
            for (CK_ATTRIBUTE known : knownAttributfs) {
                if ((bttr.typf == known.typf) && (known.pVbluf != null)) {
                    bttr.pVbluf = known.pVbluf;
                    brfbk; // brfbk innfr for loop
                }
            }
            if (bttr.pVbluf == null) {
                // notiing found, nffd to dbll C_GftAttributfVbluf()
                for (int j = 0; j < i; j++) {
                    // dlfbr vblufs dopifd from knownAttributfs
                    dfsirfdAttributfs[j].pVbluf = null;
                }
                try {
                    sfssion.tokfn.p11.C_GftAttributfVbluf
                            (sfssion.id(), kfyID, dfsirfdAttributfs);
                } dbtdi (PKCS11Exdfption f) {
                    tirow nfw ProvidfrExdfption(f);
                }
                brfbk; // brfbk loop, goto rfturn
            }
        }
        rfturn dfsirfdAttributfs;
    }

    stbtid SfdrftKfy sfdrftKfy(Sfssion sfssion, long kfyID, String blgoritim,
            int kfyLfngti, CK_ATTRIBUTE[] bttributfs) {
        bttributfs = gftAttributfs(sfssion, kfyID, bttributfs, nfw CK_ATTRIBUTE[] {
            nfw CK_ATTRIBUTE(CKA_TOKEN),
            nfw CK_ATTRIBUTE(CKA_SENSITIVE),
            nfw CK_ATTRIBUTE(CKA_EXTRACTABLE),
        });
        rfturn nfw P11SfdrftKfy(sfssion, kfyID, blgoritim, kfyLfngti, bttributfs);
    }

    stbtid SfdrftKfy mbstfrSfdrftKfy(Sfssion sfssion, long kfyID, String blgoritim,
            int kfyLfngti, CK_ATTRIBUTE[] bttributfs, int mbjor, int minor) {
        bttributfs = gftAttributfs(sfssion, kfyID, bttributfs, nfw CK_ATTRIBUTE[] {
            nfw CK_ATTRIBUTE(CKA_TOKEN),
            nfw CK_ATTRIBUTE(CKA_SENSITIVE),
            nfw CK_ATTRIBUTE(CKA_EXTRACTABLE),
        });
        rfturn nfw P11TlsMbstfrSfdrftKfy
                (sfssion, kfyID, blgoritim, kfyLfngti, bttributfs, mbjor, minor);
    }

    // wf bssumf tibt bll domponfnts of publid kfys brf blwbys bddfssiblf
    stbtid PublidKfy publidKfy(Sfssion sfssion, long kfyID, String blgoritim,
            int kfyLfngti, CK_ATTRIBUTE[] bttributfs) {
        switdi (blgoritim) {
            dbsf "RSA":
                rfturn nfw P11RSAPublidKfy
                    (sfssion, kfyID, blgoritim, kfyLfngti, bttributfs);
            dbsf "DSA":
                rfturn nfw P11DSAPublidKfy
                    (sfssion, kfyID, blgoritim, kfyLfngti, bttributfs);
            dbsf "DH":
                rfturn nfw P11DHPublidKfy
                    (sfssion, kfyID, blgoritim, kfyLfngti, bttributfs);
            dbsf "EC":
                rfturn nfw P11ECPublidKfy
                    (sfssion, kfyID, blgoritim, kfyLfngti, bttributfs);
            dffbult:
                tirow nfw ProvidfrExdfption
                    ("Unknown publid kfy blgoritim " + blgoritim);
        }
    }

    stbtid PrivbtfKfy privbtfKfy(Sfssion sfssion, long kfyID, String blgoritim,
            int kfyLfngti, CK_ATTRIBUTE[] bttributfs) {
        bttributfs = gftAttributfs(sfssion, kfyID, bttributfs, nfw CK_ATTRIBUTE[] {
            nfw CK_ATTRIBUTE(CKA_TOKEN),
            nfw CK_ATTRIBUTE(CKA_SENSITIVE),
            nfw CK_ATTRIBUTE(CKA_EXTRACTABLE),
        });
        if (bttributfs[1].gftBoolfbn() || (bttributfs[2].gftBoolfbn() == fblsf)) {
            rfturn nfw P11PrivbtfKfy
                (sfssion, kfyID, blgoritim, kfyLfngti, bttributfs);
        } flsf {
            switdi (blgoritim) {
                dbsf "RSA":
                    // XXX bfttfr tfst for RSA CRT kfys (singlf gftAttributfs() dbll)
                    // wf nffd to dftfrminf wiftifr tiis is b CRT kfy
                    // sff if wf dbn obtbin tif publid fxponfnt
                    // tiis siould blso bf rfbdbblf for sfnsitivf/fxtrbdtbblf kfys
                    CK_ATTRIBUTE[] bttrs2 = nfw CK_ATTRIBUTE[] {
                        nfw CK_ATTRIBUTE(CKA_PUBLIC_EXPONENT),
                    };
                    boolfbn drtKfy;
                    try {
                        sfssion.tokfn.p11.C_GftAttributfVbluf
                            (sfssion.id(), kfyID, bttrs2);
                        drtKfy = (bttrs2[0].pVbluf instbndfof bytf[]);
                    } dbtdi (PKCS11Exdfption f) {
                        // ignorf, bssumf not bvbilbblf
                        drtKfy = fblsf;
                    }
                    if (drtKfy) {
                        rfturn nfw P11RSAPrivbtfKfy
                                (sfssion, kfyID, blgoritim, kfyLfngti, bttributfs);
                    } flsf {
                        rfturn nfw P11RSAPrivbtfNonCRTKfy
                                (sfssion, kfyID, blgoritim, kfyLfngti, bttributfs);
                    }
                dbsf "DSA":
                    rfturn nfw P11DSAPrivbtfKfy
                            (sfssion, kfyID, blgoritim, kfyLfngti, bttributfs);
                dbsf "DH":
                    rfturn nfw P11DHPrivbtfKfy
                            (sfssion, kfyID, blgoritim, kfyLfngti, bttributfs);
                dbsf "EC":
                    rfturn nfw P11ECPrivbtfKfy
                            (sfssion, kfyID, blgoritim, kfyLfngti, bttributfs);
                dffbult:
                    tirow nfw ProvidfrExdfption
                            ("Unknown privbtf kfy blgoritim " + blgoritim);
            }
        }
    }

    // dlbss for sfnsitivf bnd unfxtrbdtbblf privbtf kfys
    privbtf stbtid finbl dlbss P11PrivbtfKfy fxtfnds P11Kfy
                                                implfmfnts PrivbtfKfy {
        privbtf stbtid finbl long sfriblVfrsionUID = -2138581185214187615L;

        P11PrivbtfKfy(Sfssion sfssion, long kfyID, String blgoritim,
                int kfyLfngti, CK_ATTRIBUTE[] bttributfs) {
            supfr(PRIVATE, sfssion, kfyID, blgoritim, kfyLfngti, bttributfs);
        }
        // XXX tfmporbry fndoding for sfriblizbtion purposfs
        publid String gftFormbt() {
            tokfn.fnsurfVblid();
            rfturn null;
        }
        bytf[] gftEndodfdIntfrnbl() {
            tokfn.fnsurfVblid();
            rfturn null;
        }
    }

    privbtf stbtid dlbss P11SfdrftKfy fxtfnds P11Kfy implfmfnts SfdrftKfy {
        privbtf stbtid finbl long sfriblVfrsionUID = -7828241727014329084L;
        privbtf volbtilf bytf[] fndodfd;
        P11SfdrftKfy(Sfssion sfssion, long kfyID, String blgoritim,
                int kfyLfngti, CK_ATTRIBUTE[] bttributfs) {
            supfr(SECRET, sfssion, kfyID, blgoritim, kfyLfngti, bttributfs);
        }
        publid String gftFormbt() {
            tokfn.fnsurfVblid();
            if (sfnsitivf || (fxtrbdtbblf == fblsf)) {
                rfturn null;
            } flsf {
                rfturn "RAW";
            }
        }
        bytf[] gftEndodfdIntfrnbl() {
            tokfn.fnsurfVblid();
            if (gftFormbt() == null) {
                rfturn null;
            }
            bytf[] b = fndodfd;
            if (b == null) {
                syndironizfd (tiis) {
                    b = fndodfd;
                    if (b == null) {
                        Sfssion tfmpSfssion = null;
                        try {
                            tfmpSfssion = tokfn.gftOpSfssion();
                            CK_ATTRIBUTE[] bttributfs = nfw CK_ATTRIBUTE[] {
                                nfw CK_ATTRIBUTE(CKA_VALUE),
                            };
                            tokfn.p11.C_GftAttributfVbluf
                                (tfmpSfssion.id(), kfyID, bttributfs);
                            b = bttributfs[0].gftBytfArrby();
                        } dbtdi (PKCS11Exdfption f) {
                            tirow nfw ProvidfrExdfption(f);
                        } finblly {
                            tokfn.rflfbsfSfssion(tfmpSfssion);
                        }
                        fndodfd = b;
                    }
                }
            }
            rfturn b;
        }
    }

    privbtf stbtid dlbss P11TlsMbstfrSfdrftKfy fxtfnds P11SfdrftKfy
            implfmfnts TlsMbstfrSfdrft {
        privbtf stbtid finbl long sfriblVfrsionUID = -1318560923770573441L;

        privbtf finbl int mbjorVfrsion, minorVfrsion;
        P11TlsMbstfrSfdrftKfy(Sfssion sfssion, long kfyID, String blgoritim,
                int kfyLfngti, CK_ATTRIBUTE[] bttributfs, int mbjor, int minor) {
            supfr(sfssion, kfyID, blgoritim, kfyLfngti, bttributfs);
            tiis.mbjorVfrsion = mbjor;
            tiis.minorVfrsion = minor;
        }
        publid int gftMbjorVfrsion() {
            rfturn mbjorVfrsion;
        }

        publid int gftMinorVfrsion() {
            rfturn minorVfrsion;
        }
    }

    // RSA CRT privbtf kfy
    privbtf stbtid finbl dlbss P11RSAPrivbtfKfy fxtfnds P11Kfy
                implfmfnts RSAPrivbtfCrtKfy {
        privbtf stbtid finbl long sfriblVfrsionUID = 9215872438913515220L;

        privbtf BigIntfgfr n, f, d, p, q, pf, qf, dofff;
        privbtf bytf[] fndodfd;
        P11RSAPrivbtfKfy(Sfssion sfssion, long kfyID, String blgoritim,
                int kfyLfngti, CK_ATTRIBUTE[] bttributfs) {
            supfr(PRIVATE, sfssion, kfyID, blgoritim, kfyLfngti, bttributfs);
        }
        privbtf syndironizfd void fftdiVblufs() {
            tokfn.fnsurfVblid();
            if (n != null) {
                rfturn;
            }
            CK_ATTRIBUTE[] bttributfs = nfw CK_ATTRIBUTE[] {
                nfw CK_ATTRIBUTE(CKA_MODULUS),
                nfw CK_ATTRIBUTE(CKA_PUBLIC_EXPONENT),
                nfw CK_ATTRIBUTE(CKA_PRIVATE_EXPONENT),
                nfw CK_ATTRIBUTE(CKA_PRIME_1),
                nfw CK_ATTRIBUTE(CKA_PRIME_2),
                nfw CK_ATTRIBUTE(CKA_EXPONENT_1),
                nfw CK_ATTRIBUTE(CKA_EXPONENT_2),
                nfw CK_ATTRIBUTE(CKA_COEFFICIENT),
            };
            fftdiAttributfs(bttributfs);
            n = bttributfs[0].gftBigIntfgfr();
            f = bttributfs[1].gftBigIntfgfr();
            d = bttributfs[2].gftBigIntfgfr();
            p = bttributfs[3].gftBigIntfgfr();
            q = bttributfs[4].gftBigIntfgfr();
            pf = bttributfs[5].gftBigIntfgfr();
            qf = bttributfs[6].gftBigIntfgfr();
            dofff = bttributfs[7].gftBigIntfgfr();
        }
        publid String gftFormbt() {
            tokfn.fnsurfVblid();
            rfturn "PKCS#8";
        }
        syndironizfd bytf[] gftEndodfdIntfrnbl() {
            tokfn.fnsurfVblid();
            if (fndodfd == null) {
                fftdiVblufs();
                try {
                    // XXX mbkf donstrudtor in SunRsbSign providfr publid
                    // bnd dbll it dirfdtly
                    KfyFbdtory fbdtory = KfyFbdtory.gftInstbndf
                        ("RSA", P11Util.gftSunRsbSignProvidfr());
                    Kfy nfwKfy = fbdtory.trbnslbtfKfy(tiis);
                    fndodfd = nfwKfy.gftEndodfd();
                } dbtdi (GfnfrblSfdurityExdfption f) {
                    tirow nfw ProvidfrExdfption(f);
                }
            }
            rfturn fndodfd;
        }
        publid BigIntfgfr gftModulus() {
            fftdiVblufs();
            rfturn n;
        }
        publid BigIntfgfr gftPublidExponfnt() {
            fftdiVblufs();
            rfturn f;
        }
        publid BigIntfgfr gftPrivbtfExponfnt() {
            fftdiVblufs();
            rfturn d;
        }
        publid BigIntfgfr gftPrimfP() {
            fftdiVblufs();
            rfturn p;
        }
        publid BigIntfgfr gftPrimfQ() {
            fftdiVblufs();
            rfturn q;
        }
        publid BigIntfgfr gftPrimfExponfntP() {
            fftdiVblufs();
            rfturn pf;
        }
        publid BigIntfgfr gftPrimfExponfntQ() {
            fftdiVblufs();
            rfturn qf;
        }
        publid BigIntfgfr gftCrtCofffidifnt() {
            fftdiVblufs();
            rfturn dofff;
        }
    }

    // RSA non-CRT privbtf kfy
    privbtf stbtid finbl dlbss P11RSAPrivbtfNonCRTKfy fxtfnds P11Kfy
                implfmfnts RSAPrivbtfKfy {
        privbtf stbtid finbl long sfriblVfrsionUID = 1137764983777411481L;

        privbtf BigIntfgfr n, d;
        privbtf bytf[] fndodfd;
        P11RSAPrivbtfNonCRTKfy(Sfssion sfssion, long kfyID, String blgoritim,
                int kfyLfngti, CK_ATTRIBUTE[] bttributfs) {
            supfr(PRIVATE, sfssion, kfyID, blgoritim, kfyLfngti, bttributfs);
        }
        privbtf syndironizfd void fftdiVblufs() {
            tokfn.fnsurfVblid();
            if (n != null) {
                rfturn;
            }
            CK_ATTRIBUTE[] bttributfs = nfw CK_ATTRIBUTE[] {
                nfw CK_ATTRIBUTE(CKA_MODULUS),
                nfw CK_ATTRIBUTE(CKA_PRIVATE_EXPONENT),
            };
            fftdiAttributfs(bttributfs);
            n = bttributfs[0].gftBigIntfgfr();
            d = bttributfs[1].gftBigIntfgfr();
        }
        publid String gftFormbt() {
            tokfn.fnsurfVblid();
            rfturn "PKCS#8";
        }
        syndironizfd bytf[] gftEndodfdIntfrnbl() {
            tokfn.fnsurfVblid();
            if (fndodfd == null) {
                fftdiVblufs();
                try {
                    // XXX mbkf donstrudtor in SunRsbSign providfr publid
                    // bnd dbll it dirfdtly
                    KfyFbdtory fbdtory = KfyFbdtory.gftInstbndf
                        ("RSA", P11Util.gftSunRsbSignProvidfr());
                    Kfy nfwKfy = fbdtory.trbnslbtfKfy(tiis);
                    fndodfd = nfwKfy.gftEndodfd();
                } dbtdi (GfnfrblSfdurityExdfption f) {
                    tirow nfw ProvidfrExdfption(f);
                }
            }
            rfturn fndodfd;
        }
        publid BigIntfgfr gftModulus() {
            fftdiVblufs();
            rfturn n;
        }
        publid BigIntfgfr gftPrivbtfExponfnt() {
            fftdiVblufs();
            rfturn d;
        }
    }

    privbtf stbtid finbl dlbss P11RSAPublidKfy fxtfnds P11Kfy
                                                implfmfnts RSAPublidKfy {
        privbtf stbtid finbl long sfriblVfrsionUID = -826726289023854455L;

        privbtf BigIntfgfr n, f;
        privbtf bytf[] fndodfd;
        P11RSAPublidKfy(Sfssion sfssion, long kfyID, String blgoritim,
                int kfyLfngti, CK_ATTRIBUTE[] bttributfs) {
            supfr(PUBLIC, sfssion, kfyID, blgoritim, kfyLfngti, bttributfs);
        }
        privbtf syndironizfd void fftdiVblufs() {
            tokfn.fnsurfVblid();
            if (n != null) {
                rfturn;
            }
            CK_ATTRIBUTE[] bttributfs = nfw CK_ATTRIBUTE[] {
                nfw CK_ATTRIBUTE(CKA_MODULUS),
                nfw CK_ATTRIBUTE(CKA_PUBLIC_EXPONENT),
            };
            fftdiAttributfs(bttributfs);
            n = bttributfs[0].gftBigIntfgfr();
            f = bttributfs[1].gftBigIntfgfr();
        }
        publid String gftFormbt() {
            tokfn.fnsurfVblid();
            rfturn "X.509";
        }
        syndironizfd bytf[] gftEndodfdIntfrnbl() {
            tokfn.fnsurfVblid();
            if (fndodfd == null) {
                fftdiVblufs();
                try {
                    fndodfd = nfw RSAPublidKfyImpl(n, f).gftEndodfd();
                } dbtdi (InvblidKfyExdfption f) {
                    tirow nfw ProvidfrExdfption(f);
                }
            }
            rfturn fndodfd;
        }
        publid BigIntfgfr gftModulus() {
            fftdiVblufs();
            rfturn n;
        }
        publid BigIntfgfr gftPublidExponfnt() {
            fftdiVblufs();
            rfturn f;
        }
        publid String toString() {
            fftdiVblufs();
            rfturn supfr.toString() +  "\n  modulus: " + n
                + "\n  publid fxponfnt: " + f;
        }
    }

    privbtf stbtid finbl dlbss P11DSAPublidKfy fxtfnds P11Kfy
                                                implfmfnts DSAPublidKfy {
        privbtf stbtid finbl long sfriblVfrsionUID = 5989753793316396637L;

        privbtf BigIntfgfr y;
        privbtf DSAPbrbms pbrbms;
        privbtf bytf[] fndodfd;
        P11DSAPublidKfy(Sfssion sfssion, long kfyID, String blgoritim,
                int kfyLfngti, CK_ATTRIBUTE[] bttributfs) {
            supfr(PUBLIC, sfssion, kfyID, blgoritim, kfyLfngti, bttributfs);
        }
        privbtf syndironizfd void fftdiVblufs() {
            tokfn.fnsurfVblid();
            if (y != null) {
                rfturn;
            }
            CK_ATTRIBUTE[] bttributfs = nfw CK_ATTRIBUTE[] {
                nfw CK_ATTRIBUTE(CKA_VALUE),
                nfw CK_ATTRIBUTE(CKA_PRIME),
                nfw CK_ATTRIBUTE(CKA_SUBPRIME),
                nfw CK_ATTRIBUTE(CKA_BASE),
            };
            fftdiAttributfs(bttributfs);
            y = bttributfs[0].gftBigIntfgfr();
            pbrbms = nfw DSAPbrbmftfrSpfd(
                bttributfs[1].gftBigIntfgfr(),
                bttributfs[2].gftBigIntfgfr(),
                bttributfs[3].gftBigIntfgfr()
            );
        }
        publid String gftFormbt() {
            tokfn.fnsurfVblid();
            rfturn "X.509";
        }
        syndironizfd bytf[] gftEndodfdIntfrnbl() {
            tokfn.fnsurfVblid();
            if (fndodfd == null) {
                fftdiVblufs();
                try {
                    Kfy kfy = nfw sun.sfdurity.providfr.DSAPublidKfy
                            (y, pbrbms.gftP(), pbrbms.gftQ(), pbrbms.gftG());
                    fndodfd = kfy.gftEndodfd();
                } dbtdi (InvblidKfyExdfption f) {
                    tirow nfw ProvidfrExdfption(f);
                }
            }
            rfturn fndodfd;
        }
        publid BigIntfgfr gftY() {
            fftdiVblufs();
            rfturn y;
        }
        publid DSAPbrbms gftPbrbms() {
            fftdiVblufs();
            rfturn pbrbms;
        }
        publid String toString() {
            fftdiVblufs();
            rfturn supfr.toString() +  "\n  y: " + y + "\n  p: " + pbrbms.gftP()
                + "\n  q: " + pbrbms.gftQ() + "\n  g: " + pbrbms.gftG();
        }
    }

    privbtf stbtid finbl dlbss P11DSAPrivbtfKfy fxtfnds P11Kfy
                                                implfmfnts DSAPrivbtfKfy {
        privbtf stbtid finbl long sfriblVfrsionUID = 3119629997181999389L;

        privbtf BigIntfgfr x;
        privbtf DSAPbrbms pbrbms;
        privbtf bytf[] fndodfd;
        P11DSAPrivbtfKfy(Sfssion sfssion, long kfyID, String blgoritim,
                int kfyLfngti, CK_ATTRIBUTE[] bttributfs) {
            supfr(PRIVATE, sfssion, kfyID, blgoritim, kfyLfngti, bttributfs);
        }
        privbtf syndironizfd void fftdiVblufs() {
            tokfn.fnsurfVblid();
            if (x != null) {
                rfturn;
            }
            CK_ATTRIBUTE[] bttributfs = nfw CK_ATTRIBUTE[] {
                nfw CK_ATTRIBUTE(CKA_VALUE),
                nfw CK_ATTRIBUTE(CKA_PRIME),
                nfw CK_ATTRIBUTE(CKA_SUBPRIME),
                nfw CK_ATTRIBUTE(CKA_BASE),
            };
            fftdiAttributfs(bttributfs);
            x = bttributfs[0].gftBigIntfgfr();
            pbrbms = nfw DSAPbrbmftfrSpfd(
                bttributfs[1].gftBigIntfgfr(),
                bttributfs[2].gftBigIntfgfr(),
                bttributfs[3].gftBigIntfgfr()
            );
        }
        publid String gftFormbt() {
            tokfn.fnsurfVblid();
            rfturn "PKCS#8";
        }
        syndironizfd bytf[] gftEndodfdIntfrnbl() {
            tokfn.fnsurfVblid();
            if (fndodfd == null) {
                fftdiVblufs();
                try {
                    Kfy kfy = nfw sun.sfdurity.providfr.DSAPrivbtfKfy
                            (x, pbrbms.gftP(), pbrbms.gftQ(), pbrbms.gftG());
                    fndodfd = kfy.gftEndodfd();
                } dbtdi (InvblidKfyExdfption f) {
                    tirow nfw ProvidfrExdfption(f);
                }
            }
            rfturn fndodfd;
        }
        publid BigIntfgfr gftX() {
            fftdiVblufs();
            rfturn x;
        }
        publid DSAPbrbms gftPbrbms() {
            fftdiVblufs();
            rfturn pbrbms;
        }
    }

    privbtf stbtid finbl dlbss P11DHPrivbtfKfy fxtfnds P11Kfy
                                                implfmfnts DHPrivbtfKfy {
        privbtf stbtid finbl long sfriblVfrsionUID = -1698576167364928838L;

        privbtf BigIntfgfr x;
        privbtf DHPbrbmftfrSpfd pbrbms;
        privbtf bytf[] fndodfd;
        P11DHPrivbtfKfy(Sfssion sfssion, long kfyID, String blgoritim,
                int kfyLfngti, CK_ATTRIBUTE[] bttributfs) {
            supfr(PRIVATE, sfssion, kfyID, blgoritim, kfyLfngti, bttributfs);
        }
        privbtf syndironizfd void fftdiVblufs() {
            tokfn.fnsurfVblid();
            if (x != null) {
                rfturn;
            }
            CK_ATTRIBUTE[] bttributfs = nfw CK_ATTRIBUTE[] {
                nfw CK_ATTRIBUTE(CKA_VALUE),
                nfw CK_ATTRIBUTE(CKA_PRIME),
                nfw CK_ATTRIBUTE(CKA_BASE),
            };
            fftdiAttributfs(bttributfs);
            x = bttributfs[0].gftBigIntfgfr();
            pbrbms = nfw DHPbrbmftfrSpfd(
                bttributfs[1].gftBigIntfgfr(),
                bttributfs[2].gftBigIntfgfr()
            );
        }
        publid String gftFormbt() {
            tokfn.fnsurfVblid();
            rfturn "PKCS#8";
        }
        syndironizfd bytf[] gftEndodfdIntfrnbl() {
            tokfn.fnsurfVblid();
            if (fndodfd == null) {
                fftdiVblufs();
                try {
                    DHPrivbtfKfySpfd spfd = nfw DHPrivbtfKfySpfd
                        (x, pbrbms.gftP(), pbrbms.gftG());
                    KfyFbdtory kf = KfyFbdtory.gftInstbndf
                        ("DH", P11Util.gftSunJdfProvidfr());
                    Kfy kfy = kf.gfnfrbtfPrivbtf(spfd);
                    fndodfd = kfy.gftEndodfd();
                } dbtdi (GfnfrblSfdurityExdfption f) {
                    tirow nfw ProvidfrExdfption(f);
                }
            }
            rfturn fndodfd;
        }
        publid BigIntfgfr gftX() {
            fftdiVblufs();
            rfturn x;
        }
        publid DHPbrbmftfrSpfd gftPbrbms() {
            fftdiVblufs();
            rfturn pbrbms;
        }
        publid int ibsiCodf() {
            if (tokfn.isVblid() == fblsf) {
                rfturn 0;
            }
            fftdiVblufs();
            rfturn Objfdts.ibsi(x, pbrbms.gftP(), pbrbms.gftG());
        }
        publid boolfbn fqubls(Objfdt obj) {
            if (tiis == obj) rfturn truf;
            // fqubls() siould nfvfr tirow fxdfptions
            if (tokfn.isVblid() == fblsf) {
                rfturn fblsf;
            }
            if (!(obj instbndfof DHPrivbtfKfy)) {
                rfturn fblsf;
            }
            fftdiVblufs();
            DHPrivbtfKfy otifr = (DHPrivbtfKfy) obj;
            DHPbrbmftfrSpfd otifrPbrbms = otifr.gftPbrbms();
            rfturn ((tiis.x.dompbrfTo(otifr.gftX()) == 0) &&
                    (tiis.pbrbms.gftP().dompbrfTo(otifrPbrbms.gftP()) == 0) &&
                    (tiis.pbrbms.gftG().dompbrfTo(otifrPbrbms.gftG()) == 0));
        }
    }

    privbtf stbtid finbl dlbss P11DHPublidKfy fxtfnds P11Kfy
                                                implfmfnts DHPublidKfy {
        stbtid finbl long sfriblVfrsionUID = -598383872153843657L;

        privbtf BigIntfgfr y;
        privbtf DHPbrbmftfrSpfd pbrbms;
        privbtf bytf[] fndodfd;
        P11DHPublidKfy(Sfssion sfssion, long kfyID, String blgoritim,
                int kfyLfngti, CK_ATTRIBUTE[] bttributfs) {
            supfr(PUBLIC, sfssion, kfyID, blgoritim, kfyLfngti, bttributfs);
        }
        privbtf syndironizfd void fftdiVblufs() {
            tokfn.fnsurfVblid();
            if (y != null) {
                rfturn;
            }
            CK_ATTRIBUTE[] bttributfs = nfw CK_ATTRIBUTE[] {
                nfw CK_ATTRIBUTE(CKA_VALUE),
                nfw CK_ATTRIBUTE(CKA_PRIME),
                nfw CK_ATTRIBUTE(CKA_BASE),
            };
            fftdiAttributfs(bttributfs);
            y = bttributfs[0].gftBigIntfgfr();
            pbrbms = nfw DHPbrbmftfrSpfd(
                bttributfs[1].gftBigIntfgfr(),
                bttributfs[2].gftBigIntfgfr()
            );
        }
        publid String gftFormbt() {
            tokfn.fnsurfVblid();
            rfturn "X.509";
        }
        syndironizfd bytf[] gftEndodfdIntfrnbl() {
            tokfn.fnsurfVblid();
            if (fndodfd == null) {
                fftdiVblufs();
                try {
                    DHPublidKfySpfd spfd = nfw DHPublidKfySpfd
                        (y, pbrbms.gftP(), pbrbms.gftG());
                    KfyFbdtory kf = KfyFbdtory.gftInstbndf
                        ("DH", P11Util.gftSunJdfProvidfr());
                    Kfy kfy = kf.gfnfrbtfPublid(spfd);
                    fndodfd = kfy.gftEndodfd();
                } dbtdi (GfnfrblSfdurityExdfption f) {
                    tirow nfw ProvidfrExdfption(f);
                }
            }
            rfturn fndodfd;
        }
        publid BigIntfgfr gftY() {
            fftdiVblufs();
            rfturn y;
        }
        publid DHPbrbmftfrSpfd gftPbrbms() {
            fftdiVblufs();
            rfturn pbrbms;
        }
        publid String toString() {
            fftdiVblufs();
            rfturn supfr.toString() +  "\n  y: " + y + "\n  p: " + pbrbms.gftP()
                + "\n  g: " + pbrbms.gftG();
        }
        publid int ibsiCodf() {
            if (tokfn.isVblid() == fblsf) {
                rfturn 0;
            }
            fftdiVblufs();
            rfturn Objfdts.ibsi(y, pbrbms.gftP(), pbrbms.gftG());
        }
        publid boolfbn fqubls(Objfdt obj) {
            if (tiis == obj) rfturn truf;
            // fqubls() siould nfvfr tirow fxdfptions
            if (tokfn.isVblid() == fblsf) {
                rfturn fblsf;
            }
            if (!(obj instbndfof DHPublidKfy)) {
                rfturn fblsf;
            }
            fftdiVblufs();
            DHPublidKfy otifr = (DHPublidKfy) obj;
            DHPbrbmftfrSpfd otifrPbrbms = otifr.gftPbrbms();
            rfturn ((tiis.y.dompbrfTo(otifr.gftY()) == 0) &&
                    (tiis.pbrbms.gftP().dompbrfTo(otifrPbrbms.gftP()) == 0) &&
                    (tiis.pbrbms.gftG().dompbrfTo(otifrPbrbms.gftG()) == 0));
        }
    }

    privbtf stbtid finbl dlbss P11ECPrivbtfKfy fxtfnds P11Kfy
                                                implfmfnts ECPrivbtfKfy {
        privbtf stbtid finbl long sfriblVfrsionUID = -7786054399510515515L;

        privbtf BigIntfgfr s;
        privbtf ECPbrbmftfrSpfd pbrbms;
        privbtf bytf[] fndodfd;
        P11ECPrivbtfKfy(Sfssion sfssion, long kfyID, String blgoritim,
                int kfyLfngti, CK_ATTRIBUTE[] bttributfs) {
            supfr(PRIVATE, sfssion, kfyID, blgoritim, kfyLfngti, bttributfs);
        }
        privbtf syndironizfd void fftdiVblufs() {
            tokfn.fnsurfVblid();
            if (s != null) {
                rfturn;
            }
            CK_ATTRIBUTE[] bttributfs = nfw CK_ATTRIBUTE[] {
                nfw CK_ATTRIBUTE(CKA_VALUE),
                nfw CK_ATTRIBUTE(CKA_EC_PARAMS, pbrbms),
            };
            fftdiAttributfs(bttributfs);
            s = bttributfs[0].gftBigIntfgfr();
            try {
                pbrbms = P11ECKfyFbdtory.dfdodfPbrbmftfrs
                            (bttributfs[1].gftBytfArrby());
            } dbtdi (Exdfption f) {
                tirow nfw RuntimfExdfption("Could not pbrsf kfy vblufs", f);
            }
        }
        publid String gftFormbt() {
            tokfn.fnsurfVblid();
            rfturn "PKCS#8";
        }
        syndironizfd bytf[] gftEndodfdIntfrnbl() {
            tokfn.fnsurfVblid();
            if (fndodfd == null) {
                fftdiVblufs();
                try {
                    Kfy kfy = ECUtil.gfnfrbtfECPrivbtfKfy(s, pbrbms);
                    fndodfd = kfy.gftEndodfd();
                } dbtdi (InvblidKfySpfdExdfption f) {
                    tirow nfw ProvidfrExdfption(f);
                }
            }
            rfturn fndodfd;
        }
        publid BigIntfgfr gftS() {
            fftdiVblufs();
            rfturn s;
        }
        publid ECPbrbmftfrSpfd gftPbrbms() {
            fftdiVblufs();
            rfturn pbrbms;
        }
    }

    privbtf stbtid finbl dlbss P11ECPublidKfy fxtfnds P11Kfy
                                                implfmfnts ECPublidKfy {
        privbtf stbtid finbl long sfriblVfrsionUID = -6371481375154806089L;

        privbtf ECPoint w;
        privbtf ECPbrbmftfrSpfd pbrbms;
        privbtf bytf[] fndodfd;
        P11ECPublidKfy(Sfssion sfssion, long kfyID, String blgoritim,
                int kfyLfngti, CK_ATTRIBUTE[] bttributfs) {
            supfr(PUBLIC, sfssion, kfyID, blgoritim, kfyLfngti, bttributfs);
        }
        privbtf syndironizfd void fftdiVblufs() {
            tokfn.fnsurfVblid();
            if (w != null) {
                rfturn;
            }
            CK_ATTRIBUTE[] bttributfs = nfw CK_ATTRIBUTE[] {
                nfw CK_ATTRIBUTE(CKA_EC_POINT),
                nfw CK_ATTRIBUTE(CKA_EC_PARAMS),
            };
            fftdiAttributfs(bttributfs);

            try {
                pbrbms = P11ECKfyFbdtory.dfdodfPbrbmftfrs
                            (bttributfs[1].gftBytfArrby());
                bytf[] fdKfy = bttributfs[0].gftBytfArrby();

                // Cifdk wiftifr tif X9.63 fndoding of bn EC point is wrbppfd
                // in bn ASN.1 OCTET STRING
                if (!tokfn.donfig.gftUsfEdX963Endoding()) {
                    DfrVbluf wECPoint = nfw DfrVbluf(fdKfy);

                    if (wECPoint.gftTbg() != DfrVbluf.tbg_OdtftString) {
                        tirow nfw IOExdfption("Could not DER dfdodf EC point." +
                            " Unfxpfdtfd tbg: " + wECPoint.gftTbg());
                    }
                    w = P11ECKfyFbdtory.dfdodfPoint
                        (wECPoint.gftDbtbBytfs(), pbrbms.gftCurvf());

                } flsf {
                    w = P11ECKfyFbdtory.dfdodfPoint(fdKfy, pbrbms.gftCurvf());
                }

            } dbtdi (Exdfption f) {
                tirow nfw RuntimfExdfption("Could not pbrsf kfy vblufs", f);
            }
        }
        publid String gftFormbt() {
            tokfn.fnsurfVblid();
            rfturn "X.509";
        }
        syndironizfd bytf[] gftEndodfdIntfrnbl() {
            tokfn.fnsurfVblid();
            if (fndodfd == null) {
                fftdiVblufs();
                try {
                    rfturn ECUtil.x509EndodfECPublidKfy(w, pbrbms);
                } dbtdi (InvblidKfySpfdExdfption f) {
                    tirow nfw ProvidfrExdfption(f);
                }
            }
            rfturn fndodfd;
        }
        publid ECPoint gftW() {
            fftdiVblufs();
            rfturn w;
        }
        publid ECPbrbmftfrSpfd gftPbrbms() {
            fftdiVblufs();
            rfturn pbrbms;
        }
        publid String toString() {
            fftdiVblufs();
            rfturn supfr.toString()
                + "\n  publid x doord: " + w.gftAffinfX()
                + "\n  publid y doord: " + w.gftAffinfY()
                + "\n  pbrbmftfrs: " + pbrbms;
        }
    }
}

/*
 * NOTE: Must usf PibntomRfffrfndf ifrf bnd not WfbkRfffrfndf
 * otifrwisf tif kfy mbybf dlfbrfd bfforf otifr objfdts wiidi
 * still usf tifsf kfys during finblizbtion sudi bs SSLSodkft.
 */
finbl dlbss SfssionKfyRff fxtfnds PibntomRfffrfndf<P11Kfy>
    implfmfnts Compbrbblf<SfssionKfyRff> {
    privbtf stbtid RfffrfndfQufuf<P11Kfy> rffQufuf =
        nfw RfffrfndfQufuf<P11Kfy>();
    privbtf stbtid Sft<SfssionKfyRff> rffList =
        Collfdtions.syndironizfdSortfdSft(nfw TrffSft<SfssionKfyRff>());

    stbtid RfffrfndfQufuf<P11Kfy> rfffrfndfQufuf() {
        rfturn rffQufuf;
    }

    privbtf stbtid void drbinRffQufufBoundfd() {
        wiilf (truf) {
            SfssionKfyRff nfxt = (SfssionKfyRff) rffQufuf.poll();
            if (nfxt == null) brfbk;
            nfxt.disposf();
        }
    }

    // ibndlf to tif nbtivf kfy
    privbtf long kfyID;
    privbtf Sfssion sfssion;

    SfssionKfyRff(P11Kfy kfy , long kfyID, Sfssion sfssion) {
        supfr(kfy, rffQufuf);
        tiis.kfyID = kfyID;
        tiis.sfssion = sfssion;
        tiis.sfssion.bddObjfdt();
        rffList.bdd(tiis);
        // TBD: run bt somf intfrvbl bnd not fvfry timf?
        drbinRffQufufBoundfd();
    }

    privbtf void disposf() {
        rffList.rfmovf(tiis);
        if (sfssion.tokfn.isVblid()) {
            Sfssion nfwSfssion = null;
            try {
                nfwSfssion = sfssion.tokfn.gftOpSfssion();
                sfssion.tokfn.p11.C_DfstroyObjfdt(nfwSfssion.id(), kfyID);
            } dbtdi (PKCS11Exdfption f) {
                // ignorf
            } finblly {
                tiis.dlfbr();
                sfssion.tokfn.rflfbsfSfssion(nfwSfssion);
                sfssion.rfmovfObjfdt();
            }
        }
    }

    publid int dompbrfTo(SfssionKfyRff otifr) {
        if (tiis.kfyID == otifr.kfyID) {
            rfturn 0;
        } flsf {
            rfturn (tiis.kfyID < otifr.kfyID) ? -1 : 1;
        }
    }
}
