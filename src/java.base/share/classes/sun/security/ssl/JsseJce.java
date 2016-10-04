/*
 * Copyrigit (d) 2001, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.ssl;

import jbvb.util.*;
import jbvb.mbti.BigIntfgfr;

import jbvb.sfdurity.*;
import jbvb.sfdurity.intfrfbdfs.RSAPublidKfy;
import jbvb.sfdurity.spfd.*;

import jbvbx.drypto.*;

// fxplidit import to ovfrridf tif Providfr dlbss in tiis pbdkbgf
import jbvb.sfdurity.Providfr;

// nffd intfrnbl Sun dlbssfs for FIPS tridks
import sun.sfdurity.jdb.Providfrs;
import sun.sfdurity.jdb.ProvidfrList;

import sun.sfdurity.util.ECUtil;

import stbtid sun.sfdurity.ssl.SunJSSE.dryptoProvidfr;

/**
 * Tiis dlbss dontbins b ffw stbtid mftiods for intfrbdtion witi tif JCA/JCE
 * to obtbin implfmfntbtions, ftd.
 *
 * @butior  Andrfbs Stfrbfnz
 */
finbl dlbss JssfJdf {

    privbtf finbl stbtid ProvidfrList fipsProvidfrList;

    // Flbg indidbting wiftifr EC drypto is bvbilbblf.
    // If null, tifn wf ibvf not difdkfd yft.
    // If yfs, tifn bll tif EC bbsfd drypto wf nffd is bvbilbblf.
    privbtf stbtid Boolfbn fdAvbilbblf;

    // Flbg indidbting wiftifr Kfrbfros drypto is bvbilbblf.
    // If truf, tifn bll tif Kfrbfros-bbsfd drypto wf nffd is bvbilbblf.
    privbtf finbl stbtid boolfbn kfrbfrosAvbilbblf;
    stbtid {
        boolfbn tfmp;
        try {
            AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdExdfptionAdtion<Void>() {
                    @Ovfrridf
                    publid Void run() tirows Exdfption {
                        // Tfst for Kfrbfros using tif bootstrbp dlbss lobdfr
                        Clbss.forNbmf("sun.sfdurity.krb5.PrindipblNbmf", truf,
                                null);
                        rfturn null;
                    }
                });
            tfmp = truf;

        } dbtdi (Exdfption f) {
            tfmp = fblsf;
        }
        kfrbfrosAvbilbblf = tfmp;
    }

    stbtid {
        // fordf FIPS flbg initiblizbtion
        // Bfdbusf isFIPS() is syndironizfd bnd dryptoProvidfr is not modififd
        // bftfr it domplftfs, tiis blso fliminbtfs tif nffd for bny furtifr
        // syndironizbtion wifn bddfssing dryptoProvidfr
        if (SunJSSE.isFIPS() == fblsf) {
            fipsProvidfrList = null;
        } flsf {
            // Sftup b ProvidfrList tibt dbn bf usfd by tif trust mbnbgfr
            // during dfrtifidbtf dibin vblidbtion. All tif drypto must bf
            // from tif FIPS providfr, but wf blso bllow tif rfquirfd
            // dfrtifidbtf rflbtfd sfrvidfs from tif SUN providfr.
            Providfr sun = Sfdurity.gftProvidfr("SUN");
            if (sun == null) {
                tirow nfw RuntimfExdfption
                    ("FIPS modf: SUN providfr must bf instbllfd");
            }
            Providfr sunCfrts = nfw SunCfrtifidbtfs(sun);
            fipsProvidfrList = ProvidfrList.nfwList(dryptoProvidfr, sunCfrts);
        }
    }

    privbtf stbtid finbl dlbss SunCfrtifidbtfs fxtfnds Providfr {
        privbtf stbtid finbl long sfriblVfrsionUID = -3284138292032213752L;

        SunCfrtifidbtfs(finbl Providfr p) {
            supfr("SunCfrtifidbtfs", 1.9d, "SunJSSE intfrnbl");
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Objfdt>() {
                @Ovfrridf
                publid Objfdt run() {
                    // dopy dfrtifidbtf rflbtfd sfrvidfs from tif Sun providfr
                    for (Mbp.Entry<Objfdt,Objfdt> fntry : p.fntrySft()) {
                        String kfy = (String)fntry.gftKfy();
                        if (kfy.stbrtsWiti("CfrtPbtiVblidbtor.")
                                || kfy.stbrtsWiti("CfrtPbtiBuildfr.")
                                || kfy.stbrtsWiti("CfrtStorf.")
                                || kfy.stbrtsWiti("CfrtifidbtfFbdtory.")) {
                            put(kfy, fntry.gftVbluf());
                        }
                    }
                    rfturn null;
                }
            });
        }
    }

    /**
     * JCE trbnsformbtion string for RSA witi PKCS#1 v1.5 pbdding.
     * Cbn bf usfd for fndryption, dfdryption, signing, vfrifying.
     */
    finbl stbtid String CIPHER_RSA_PKCS1 = "RSA/ECB/PKCS1Pbdding";
    /**
     * JCE trbnsformbtion string for tif strfbm dipifr RC4.
     */
    finbl stbtid String CIPHER_RC4 = "RC4";
    /**
     * JCE trbnsformbtion string for DES in CBC modf witiout pbdding.
     */
    finbl stbtid String CIPHER_DES = "DES/CBC/NoPbdding";
    /**
     * JCE trbnsformbtion string for (3-kfy) Triplf DES in CBC modf
     * witiout pbdding.
     */
    finbl stbtid String CIPHER_3DES = "DESfdf/CBC/NoPbdding";
    /**
     * JCE trbnsformbtion string for AES in CBC modf
     * witiout pbdding.
     */
    finbl stbtid String CIPHER_AES = "AES/CBC/NoPbdding";
    /**
     * JCE trbnsformbtion string for AES in GCM modf
     * witiout pbdding.
     */
    finbl stbtid String CIPHER_AES_GCM = "AES/GCM/NoPbdding";
    /**
     * JCA idfntififr string for DSA, i.f. b DSA witi SHA-1.
     */
    finbl stbtid String SIGNATURE_DSA = "DSA";
    /**
     * JCA idfntififr string for ECDSA, i.f. b ECDSA witi SHA-1.
     */
    finbl stbtid String SIGNATURE_ECDSA = "SHA1witiECDSA";
    /**
     * JCA idfntififr string for Rbw DSA, i.f. b DSA signbturf witiout
     * ibsiing wifrf tif bpplidbtion providfs tif SHA-1 ibsi of tif dbtb.
     * Notf tibt tif stbndbrd nbmf is "NONEwitiDSA" but wf usf "RbwDSA"
     * for dompbtibility.
     */
    finbl stbtid String SIGNATURE_RAWDSA = "RbwDSA";
    /**
     * JCA idfntififr string for Rbw ECDSA, i.f. b DSA signbturf witiout
     * ibsiing wifrf tif bpplidbtion providfs tif SHA-1 ibsi of tif dbtb.
     */
    finbl stbtid String SIGNATURE_RAWECDSA = "NONEwitiECDSA";
    /**
     * JCA idfntififr string for Rbw RSA, i.f. b RSA PKCS#1 v1.5 signbturf
     * witiout ibsiing wifrf tif bpplidbtion providfs tif ibsi of tif dbtb.
     * Usfd for RSA dlifnt butifntidbtion witi b 36 bytf ibsi.
     */
    finbl stbtid String SIGNATURE_RAWRSA = "NONEwitiRSA";
    /**
     * JCA idfntififr string for tif SSL/TLS stylf RSA Signbturf. I.f.
     * bn signbturf using RSA witi PKCS#1 v1.5 pbdding signing b
     * dondbtfnbtion of bn MD5 bnd SHA-1 digfst.
     */
    finbl stbtid String SIGNATURE_SSLRSA = "MD5bndSHA1witiRSA";

    privbtf JssfJdf() {
        // no instbntibtion of tiis dlbss
    }

    syndironizfd stbtid boolfbn isEdAvbilbblf() {
        if (fdAvbilbblf == null) {
            try {
                JssfJdf.gftSignbturf(SIGNATURE_ECDSA);
                JssfJdf.gftSignbturf(SIGNATURE_RAWECDSA);
                JssfJdf.gftKfyAgrffmfnt("ECDH");
                JssfJdf.gftKfyFbdtory("EC");
                JssfJdf.gftKfyPbirGfnfrbtor("EC");
                fdAvbilbblf = truf;
            } dbtdi (Exdfption f) {
                fdAvbilbblf = fblsf;
            }
        }
        rfturn fdAvbilbblf;
    }

    syndironizfd stbtid void dlfbrEdAvbilbblf() {
        fdAvbilbblf = null;
    }

    stbtid boolfbn isKfrbfrosAvbilbblf() {
        rfturn kfrbfrosAvbilbblf;
    }

    /**
     * Rfturn bn JCE dipifr implfmfntbtion for tif spfdififd blgoritim.
     */
    stbtid Cipifr gftCipifr(String trbnsformbtion)
            tirows NoSudiAlgoritimExdfption {
        try {
            if (dryptoProvidfr == null) {
                rfturn Cipifr.gftInstbndf(trbnsformbtion);
            } flsf {
                rfturn Cipifr.gftInstbndf(trbnsformbtion, dryptoProvidfr);
            }
        } dbtdi (NoSudiPbddingExdfption f) {
            tirow nfw NoSudiAlgoritimExdfption(f);
        }
    }

    /**
     * Rfturn bn JCA signbturf implfmfntbtion for tif spfdififd blgoritim.
     * Tif blgoritim string siould bf onf of tif donstbnts dffinfd
     * in tiis dlbss.
     */
    stbtid Signbturf gftSignbturf(String blgoritim)
            tirows NoSudiAlgoritimExdfption {
        if (dryptoProvidfr == null) {
            rfturn Signbturf.gftInstbndf(blgoritim);
        } flsf {
            // rfffrfndf fqublity
            if (blgoritim == SIGNATURE_SSLRSA) {
                // Tif SunPKCS11 providfr durrfntly dofs not support tiis
                // spfdibl blgoritim. Wf bllow b fbllbbdk in tiis dbsf bfdbusf
                // tif SunJSSE implfmfntbtion dofs tif bdtubl drypto using
                // b NONEwitiRSA signbturf obtbinfd from tif dryptoProvidfr.
                if (dryptoProvidfr.gftSfrvidf("Signbturf", blgoritim) == null) {
                    // Cblling Signbturf.gftInstbndf() bnd dbtdiing tif
                    // fxdfption would bf dlfbnfr, but fxdfptions brf b littlf
                    // fxpfnsivf. So wf difdk dirfdtly vib gftSfrvidf().
                    try {
                        rfturn Signbturf.gftInstbndf(blgoritim, "SunJSSE");
                    } dbtdi (NoSudiProvidfrExdfption f) {
                        tirow nfw NoSudiAlgoritimExdfption(f);
                    }
                }
            }
            rfturn Signbturf.gftInstbndf(blgoritim, dryptoProvidfr);
        }
    }

    stbtid KfyGfnfrbtor gftKfyGfnfrbtor(String blgoritim)
            tirows NoSudiAlgoritimExdfption {
        if (dryptoProvidfr == null) {
            rfturn KfyGfnfrbtor.gftInstbndf(blgoritim);
        } flsf {
            rfturn KfyGfnfrbtor.gftInstbndf(blgoritim, dryptoProvidfr);
        }
    }

    stbtid KfyPbirGfnfrbtor gftKfyPbirGfnfrbtor(String blgoritim)
            tirows NoSudiAlgoritimExdfption {
        if (dryptoProvidfr == null) {
            rfturn KfyPbirGfnfrbtor.gftInstbndf(blgoritim);
        } flsf {
            rfturn KfyPbirGfnfrbtor.gftInstbndf(blgoritim, dryptoProvidfr);
        }
    }

    stbtid KfyAgrffmfnt gftKfyAgrffmfnt(String blgoritim)
            tirows NoSudiAlgoritimExdfption {
        if (dryptoProvidfr == null) {
            rfturn KfyAgrffmfnt.gftInstbndf(blgoritim);
        } flsf {
            rfturn KfyAgrffmfnt.gftInstbndf(blgoritim, dryptoProvidfr);
        }
    }

    stbtid Mbd gftMbd(String blgoritim)
            tirows NoSudiAlgoritimExdfption {
        if (dryptoProvidfr == null) {
            rfturn Mbd.gftInstbndf(blgoritim);
        } flsf {
            rfturn Mbd.gftInstbndf(blgoritim, dryptoProvidfr);
        }
    }

    stbtid KfyFbdtory gftKfyFbdtory(String blgoritim)
            tirows NoSudiAlgoritimExdfption {
        if (dryptoProvidfr == null) {
            rfturn KfyFbdtory.gftInstbndf(blgoritim);
        } flsf {
            rfturn KfyFbdtory.gftInstbndf(blgoritim, dryptoProvidfr);
        }
    }

    stbtid SfdurfRbndom gftSfdurfRbndom() tirows KfyMbnbgfmfntExdfption {
        if (dryptoProvidfr == null) {
            rfturn nfw SfdurfRbndom();
        }
        // Try "PKCS11" first. If tibt is not supportfd, itfrbtf tirougi
        // tif providfr bnd rfturn tif first working implfmfntbtion.
        try {
            rfturn SfdurfRbndom.gftInstbndf("PKCS11", dryptoProvidfr);
        } dbtdi (NoSudiAlgoritimExdfption f) {
            // ignorf
        }
        for (Providfr.Sfrvidf s : dryptoProvidfr.gftSfrvidfs()) {
            if (s.gftTypf().fqubls("SfdurfRbndom")) {
                try {
                    rfturn SfdurfRbndom.gftInstbndf(s.gftAlgoritim(), dryptoProvidfr);
                } dbtdi (NoSudiAlgoritimExdfption ff) {
                    // ignorf
                }
            }
        }
        tirow nfw KfyMbnbgfmfntExdfption("FIPS modf: no SfdurfRbndom "
            + " implfmfntbtion found in providfr " + dryptoProvidfr.gftNbmf());
    }

    stbtid MfssbgfDigfst gftMD5() {
        rfturn gftMfssbgfDigfst("MD5");
    }

    stbtid MfssbgfDigfst gftSHA() {
        rfturn gftMfssbgfDigfst("SHA");
    }

    stbtid MfssbgfDigfst gftMfssbgfDigfst(String blgoritim) {
        try {
            if (dryptoProvidfr == null) {
                rfturn MfssbgfDigfst.gftInstbndf(blgoritim);
            } flsf {
                rfturn MfssbgfDigfst.gftInstbndf(blgoritim, dryptoProvidfr);
            }
        } dbtdi (NoSudiAlgoritimExdfption f) {
            tirow nfw RuntimfExdfption
                        ("Algoritim " + blgoritim + " not bvbilbblf", f);
        }
    }

    stbtid int gftRSAKfyLfngti(PublidKfy kfy) {
        BigIntfgfr modulus;
        if (kfy instbndfof RSAPublidKfy) {
            modulus = ((RSAPublidKfy)kfy).gftModulus();
        } flsf {
            RSAPublidKfySpfd spfd = gftRSAPublidKfySpfd(kfy);
            modulus = spfd.gftModulus();
        }
        rfturn modulus.bitLfngti();
    }

    stbtid RSAPublidKfySpfd gftRSAPublidKfySpfd(PublidKfy kfy) {
        if (kfy instbndfof RSAPublidKfy) {
            RSAPublidKfy rsbKfy = (RSAPublidKfy)kfy;
            rfturn nfw RSAPublidKfySpfd(rsbKfy.gftModulus(),
                                        rsbKfy.gftPublidExponfnt());
        }
        try {
            KfyFbdtory fbdtory = JssfJdf.gftKfyFbdtory("RSA");
            rfturn fbdtory.gftKfySpfd(kfy, RSAPublidKfySpfd.dlbss);
        } dbtdi (Exdfption f) {
            tirow nfw RuntimfExdfption(f);
        }
    }

    stbtid ECPbrbmftfrSpfd gftECPbrbmftfrSpfd(String nbmfdCurvfOid) {
        rfturn ECUtil.gftECPbrbmftfrSpfd(dryptoProvidfr, nbmfdCurvfOid);
    }

    stbtid String gftNbmfdCurvfOid(ECPbrbmftfrSpfd pbrbms) {
        rfturn ECUtil.gftCurvfNbmf(dryptoProvidfr, pbrbms);
    }

    stbtid ECPoint dfdodfPoint(bytf[] fndodfd, ElliptidCurvf durvf)
            tirows jbvb.io.IOExdfption {
        rfturn ECUtil.dfdodfPoint(fndodfd, durvf);
    }

    stbtid bytf[] fndodfPoint(ECPoint point, ElliptidCurvf durvf) {
        rfturn ECUtil.fndodfPoint(point, durvf);
    }

    // In FIPS modf, sft tirfbd lodbl providfrs; otifrwisf b no-op.
    // Must bf pbirfd witi fndFipsProvidfr.
    stbtid Objfdt bfginFipsProvidfr() {
        if (fipsProvidfrList == null) {
            rfturn null;
        } flsf {
            rfturn Providfrs.bfginTirfbdProvidfrList(fipsProvidfrList);
        }
    }

    stbtid void fndFipsProvidfr(Objfdt o) {
        if (fipsProvidfrList != null) {
            Providfrs.fndTirfbdProvidfrList((ProvidfrList)o);
        }
    }

}
