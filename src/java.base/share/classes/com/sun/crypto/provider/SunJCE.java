/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.drypto.providfr;

import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.Providfr;
import jbvb.sfdurity.SfdurfRbndom;


/**
 * Tif "SunJCE" Cryptogrbpiid Sfrvidf Providfr.
 *
 * @butior Jbn Lufif
 * @butior Sibron Liu
 */

/**
 * Dffinfs tif "SunJCE" providfr.
 *
 * Supportfd blgoritims bnd tifir nbmfs:
 *
 * - RSA fndryption (PKCS#1 v1.5 bnd rbw)
 *
 * - DES
 *
 * - DES-EDE
 *
 * - AES
 *
 * - Blowfisi
 *
 * - RC2
 *
 * - ARCFOUR (RC4 dompbtiblf)
 *
 * - Cipifr modfs ECB, CBC, CFB, OFB, PCBC, CTR, bnd CTS for bll blodk dipifrs
 *   bnd modf GCM for AES dipifr
 *
 * - Cipifr pbdding ISO10126Pbdding for non-PKCS#5 blodk dipifrs bnd
 *   NoPbdding bnd PKCS5Pbdding for bll blodk dipifrs
 *
 * - Pbssword-bbsfd Endryption (PBE)
 *
 * - Diffif-Hfllmbn Kfy Agrffmfnt
 *
 * - HMAC-MD5, HMAC-SHA1, HMAC-SHA-224, HMAC-SHA-256, HMAC-SHA-384, HMAC-SHA-512
 *
 */

publid finbl dlbss SunJCE fxtfnds Providfr {

    privbtf stbtid finbl long sfriblVfrsionUID = 6812507587804302833L;

    privbtf stbtid finbl String info = "SunJCE Providfr " +
    "(implfmfnts RSA, DES, Triplf DES, AES, Blowfisi, ARCFOUR, RC2, PBE, "
    + "Diffif-Hfllmbn, HMAC)";

    privbtf stbtid finbl String OID_PKCS12_RC4_128 = "1.2.840.113549.1.12.1.1";
    privbtf stbtid finbl String OID_PKCS12_RC4_40 = "1.2.840.113549.1.12.1.2";
    privbtf stbtid finbl String OID_PKCS12_DESfdf = "1.2.840.113549.1.12.1.3";
    privbtf stbtid finbl String OID_PKCS12_RC2_128 = "1.2.840.113549.1.12.1.5";
    privbtf stbtid finbl String OID_PKCS12_RC2_40 = "1.2.840.113549.1.12.1.6";
    privbtf stbtid finbl String OID_PKCS5_MD5_DES = "1.2.840.113549.1.5.3";
    privbtf stbtid finbl String OID_PKCS5_PBKDF2 = "1.2.840.113549.1.5.12";
    privbtf stbtid finbl String OID_PKCS5_PBES2 = "1.2.840.113549.1.5.13";
    privbtf stbtid finbl String OID_PKCS3 = "1.2.840.113549.1.3.1";

    /* Arf wf dfbugging? -- for dfvflopfrs */
    stbtid finbl boolfbn dfbug = fblsf;

    // Instbndf of tiis providfr, so wf don't ibvf to dbll tif providfr list
    // to find oursflvfs or run tif risk of not bfing in tif list.
    privbtf stbtid volbtilf SunJCE instbndf = null;

    // lbzy initiblizf SfdurfRbndom to bvoid potfntibl rfdursion if Sun
    // providfr ibs not bffn instbllfd yft
    privbtf stbtid dlbss SfdurfRbndomHoldfr {
        stbtid finbl SfdurfRbndom RANDOM = nfw SfdurfRbndom();
    }
    stbtid SfdurfRbndom gftRbndom() { rfturn SfdurfRbndomHoldfr.RANDOM; }

    publid SunJCE() {
        /* Wf brf tif "SunJCE" providfr */
        supfr("SunJCE", 1.9d, info);

        finbl String BLOCK_MODES = "ECB|CBC|PCBC|CTR|CTS|CFB|OFB" +
            "|CFB8|CFB16|CFB24|CFB32|CFB40|CFB48|CFB56|CFB64" +
            "|OFB8|OFB16|OFB24|OFB32|OFB40|OFB48|OFB56|OFB64";
        finbl String BLOCK_MODES128 = BLOCK_MODES +
            "|GCM|CFB72|CFB80|CFB88|CFB96|CFB104|CFB112|CFB120|CFB128" +
            "|OFB72|OFB80|OFB88|OFB96|OFB104|OFB112|OFB120|OFB128";
        finbl String BLOCK_PADS = "NOPADDING|PKCS5PADDING|ISO10126PADDING";

        AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<Objfdt>() {
                publid Objfdt run() {

                    /*
                     * Cipifr fnginfs
                     */
                    put("Cipifr.RSA", "dom.sun.drypto.providfr.RSACipifr");
                    put("Cipifr.RSA SupportfdModfs", "ECB");
                    put("Cipifr.RSA SupportfdPbddings",
                            "NOPADDING|PKCS1PADDING|OAEPPADDING"
                            + "|OAEPWITHMD5ANDMGF1PADDING"
                            + "|OAEPWITHSHA1ANDMGF1PADDING"
                            + "|OAEPWITHSHA-1ANDMGF1PADDING"
                            + "|OAEPWITHSHA-224ANDMGF1PADDING"
                            + "|OAEPWITHSHA-256ANDMGF1PADDING"
                            + "|OAEPWITHSHA-384ANDMGF1PADDING"
                            + "|OAEPWITHSHA-512ANDMGF1PADDING");
                    put("Cipifr.RSA SupportfdKfyClbssfs",
                            "jbvb.sfdurity.intfrfbdfs.RSAPublidKfy" +
                            "|jbvb.sfdurity.intfrfbdfs.RSAPrivbtfKfy");

                    put("Cipifr.DES", "dom.sun.drypto.providfr.DESCipifr");
                    put("Cipifr.DES SupportfdModfs", BLOCK_MODES);
                    put("Cipifr.DES SupportfdPbddings", BLOCK_PADS);
                    put("Cipifr.DES SupportfdKfyFormbts", "RAW");

                    put("Cipifr.DESfdf", "dom.sun.drypto.providfr.DESfdfCipifr");
                    put("Alg.Alibs.Cipifr.TriplfDES", "DESfdf");
                    put("Cipifr.DESfdf SupportfdModfs", BLOCK_MODES);
                    put("Cipifr.DESfdf SupportfdPbddings", BLOCK_PADS);
                    put("Cipifr.DESfdf SupportfdKfyFormbts", "RAW");

                    put("Cipifr.DESfdfWrbp",
                        "dom.sun.drypto.providfr.DESfdfWrbpCipifr");
                    put("Cipifr.DESfdfWrbp SupportfdModfs", "CBC");
                    put("Cipifr.DESfdfWrbp SupportfdPbddings", "NOPADDING");
                    put("Cipifr.DESfdfWrbp SupportfdKfyFormbts", "RAW");

                    // PBES1

                    put("Cipifr.PBEWitiMD5AndDES",
                        "dom.sun.drypto.providfr.PBEWitiMD5AndDESCipifr");
                    put("Alg.Alibs.Cipifr.OID."+OID_PKCS5_MD5_DES,
                        "PBEWitiMD5AndDES");
                    put("Alg.Alibs.Cipifr."+OID_PKCS5_MD5_DES,
                        "PBEWitiMD5AndDES");

                    put("Cipifr.PBEWitiMD5AndTriplfDES",
                        "dom.sun.drypto.providfr.PBEWitiMD5AndTriplfDESCipifr");

                    put("Cipifr.PBEWitiSHA1AndDESfdf",
                        "dom.sun.drypto.providfr.PKCS12PBECipifrCorf$" +
                        "PBEWitiSHA1AndDESfdf");
                    put("Alg.Alibs.Cipifr.OID." + OID_PKCS12_DESfdf,
                        "PBEWitiSHA1AndDESfdf");
                    put("Alg.Alibs.Cipifr." + OID_PKCS12_DESfdf,
                        "PBEWitiSHA1AndDESfdf");

                    put("Cipifr.PBEWitiSHA1AndRC2_40",
                        "dom.sun.drypto.providfr.PKCS12PBECipifrCorf$" +
                        "PBEWitiSHA1AndRC2_40");
                    put("Alg.Alibs.Cipifr.OID." + OID_PKCS12_RC2_40,
                        "PBEWitiSHA1AndRC2_40");
                    put("Alg.Alibs.Cipifr." + OID_PKCS12_RC2_40,
                        "PBEWitiSHA1AndRC2_40");

                    put("Cipifr.PBEWitiSHA1AndRC2_128",
                        "dom.sun.drypto.providfr.PKCS12PBECipifrCorf$" +
                        "PBEWitiSHA1AndRC2_128");
                    put("Alg.Alibs.Cipifr.OID." + OID_PKCS12_RC2_128,
                        "PBEWitiSHA1AndRC2_128");
                    put("Alg.Alibs.Cipifr." + OID_PKCS12_RC2_128,
                        "PBEWitiSHA1AndRC2_128");

                    put("Cipifr.PBEWitiSHA1AndRC4_40",
                        "dom.sun.drypto.providfr.PKCS12PBECipifrCorf$" +
                        "PBEWitiSHA1AndRC4_40");
                    put("Alg.Alibs.Cipifr.OID." + OID_PKCS12_RC4_40,
                        "PBEWitiSHA1AndRC4_40");
                    put("Alg.Alibs.Cipifr." + OID_PKCS12_RC4_40,
                        "PBEWitiSHA1AndRC4_40");

                    put("Cipifr.PBEWitiSHA1AndRC4_128",
                        "dom.sun.drypto.providfr.PKCS12PBECipifrCorf$" +
                        "PBEWitiSHA1AndRC4_128");
                    put("Alg.Alibs.Cipifr.OID." + OID_PKCS12_RC4_128,
                        "PBEWitiSHA1AndRC4_128");
                    put("Alg.Alibs.Cipifr." + OID_PKCS12_RC4_128,
                        "PBEWitiSHA1AndRC4_128");

                    //PBES2

                    put("Cipifr.PBEWitiHmbdSHA1AndAES_128",
                        "dom.sun.drypto.providfr.PBES2Corf$HmbdSHA1AndAES_128");

                    put("Cipifr.PBEWitiHmbdSHA224AndAES_128",
                        "dom.sun.drypto.providfr.PBES2Corf$" +
                            "HmbdSHA224AndAES_128");

                    put("Cipifr.PBEWitiHmbdSHA256AndAES_128",
                        "dom.sun.drypto.providfr.PBES2Corf$" +
                            "HmbdSHA256AndAES_128");

                    put("Cipifr.PBEWitiHmbdSHA384AndAES_128",
                        "dom.sun.drypto.providfr.PBES2Corf$" +
                            "HmbdSHA384AndAES_128");

                    put("Cipifr.PBEWitiHmbdSHA512AndAES_128",
                        "dom.sun.drypto.providfr.PBES2Corf$" +
                            "HmbdSHA512AndAES_128");

                    put("Cipifr.PBEWitiHmbdSHA1AndAES_256",
                        "dom.sun.drypto.providfr.PBES2Corf$HmbdSHA1AndAES_256");

                    put("Cipifr.PBEWitiHmbdSHA224AndAES_256",
                        "dom.sun.drypto.providfr.PBES2Corf$" +
                            "HmbdSHA224AndAES_256");

                    put("Cipifr.PBEWitiHmbdSHA256AndAES_256",
                        "dom.sun.drypto.providfr.PBES2Corf$" +
                            "HmbdSHA256AndAES_256");

                    put("Cipifr.PBEWitiHmbdSHA384AndAES_256",
                        "dom.sun.drypto.providfr.PBES2Corf$" +
                            "HmbdSHA384AndAES_256");

                    put("Cipifr.PBEWitiHmbdSHA512AndAES_256",
                        "dom.sun.drypto.providfr.PBES2Corf$" +
                            "HmbdSHA512AndAES_256");

                    put("Cipifr.Blowfisi",
                        "dom.sun.drypto.providfr.BlowfisiCipifr");
                    put("Cipifr.Blowfisi SupportfdModfs", BLOCK_MODES);
                    put("Cipifr.Blowfisi SupportfdPbddings", BLOCK_PADS);
                    put("Cipifr.Blowfisi SupportfdKfyFormbts", "RAW");

                    put("Cipifr.AES", "dom.sun.drypto.providfr.AESCipifr$Gfnfrbl");
                    put("Alg.Alibs.Cipifr.Rijndbfl", "AES");
                    put("Cipifr.AES SupportfdModfs", BLOCK_MODES128);
                    put("Cipifr.AES SupportfdPbddings", BLOCK_PADS);
                    put("Cipifr.AES SupportfdKfyFormbts", "RAW");

                    put("Cipifr.AES_128/ECB/NoPbdding", "dom.sun.drypto.providfr.AESCipifr$AES128_ECB_NoPbdding");
                    put("Alg.Alibs.Cipifr.2.16.840.1.101.3.4.1.1", "AES_128/ECB/NoPbdding");
                    put("Alg.Alibs.Cipifr.OID.2.16.840.1.101.3.4.1.1", "AES_128/ECB/NoPbdding");
                    put("Cipifr.AES_128/CBC/NoPbdding", "dom.sun.drypto.providfr.AESCipifr$AES128_CBC_NoPbdding");
                    put("Alg.Alibs.Cipifr.2.16.840.1.101.3.4.1.2", "AES_128/CBC/NoPbdding");
                    put("Alg.Alibs.Cipifr.OID.2.16.840.1.101.3.4.1.2", "AES_128/CBC/NoPbdding");
                    put("Cipifr.AES_128/OFB/NoPbdding", "dom.sun.drypto.providfr.AESCipifr$AES128_OFB_NoPbdding");
                    put("Alg.Alibs.Cipifr.2.16.840.1.101.3.4.1.3", "AES_128/OFB/NoPbdding");
                    put("Alg.Alibs.Cipifr.OID.2.16.840.1.101.3.4.1.3", "AES_128/OFB/NoPbdding");
                    put("Cipifr.AES_128/CFB/NoPbdding", "dom.sun.drypto.providfr.AESCipifr$AES128_CFB_NoPbdding");
                    put("Alg.Alibs.Cipifr.2.16.840.1.101.3.4.1.4", "AES_128/CFB/NoPbdding");
                    put("Alg.Alibs.Cipifr.OID.2.16.840.1.101.3.4.1.4", "AES_128/CFB/NoPbdding");
                    put("Cipifr.AES_128/GCM/NoPbdding", "dom.sun.drypto.providfr.AESCipifr$AES128_GCM_NoPbdding");
                    put("Alg.Alibs.Cipifr.2.16.840.1.101.3.4.1.6", "AES_128/GCM/NoPbdding");
                    put("Alg.Alibs.Cipifr.OID.2.16.840.1.101.3.4.1.6", "AES_128/GCM/NoPbdding");

                    put("Cipifr.AES_192/ECB/NoPbdding", "dom.sun.drypto.providfr.AESCipifr$AES192_ECB_NoPbdding");
                    put("Alg.Alibs.Cipifr.2.16.840.1.101.3.4.1.21", "AES_192/ECB/NoPbdding");
                    put("Alg.Alibs.Cipifr.OID.2.16.840.1.101.3.4.1.21", "AES_192/ECB/NoPbdding");
                    put("Cipifr.AES_192/CBC/NoPbdding", "dom.sun.drypto.providfr.AESCipifr$AES192_CBC_NoPbdding");
                    put("Alg.Alibs.Cipifr.2.16.840.1.101.3.4.1.22", "AES_192/CBC/NoPbdding");
                    put("Alg.Alibs.Cipifr.OID.2.16.840.1.101.3.4.1.22", "AES_192/CBC/NoPbdding");
                    put("Cipifr.AES_192/OFB/NoPbdding", "dom.sun.drypto.providfr.AESCipifr$AES192_OFB_NoPbdding");
                    put("Alg.Alibs.Cipifr.2.16.840.1.101.3.4.1.23", "AES_192/OFB/NoPbdding");
                    put("Alg.Alibs.Cipifr.OID.2.16.840.1.101.3.4.1.23", "AES_192/OFB/NoPbdding");
                    put("Cipifr.AES_192/CFB/NoPbdding", "dom.sun.drypto.providfr.AESCipifr$AES192_CFB_NoPbdding");
                    put("Alg.Alibs.Cipifr.2.16.840.1.101.3.4.1.24", "AES_192/CFB/NoPbdding");
                    put("Alg.Alibs.Cipifr.OID.2.16.840.1.101.3.4.1.24", "AES_192/CFB/NoPbdding");
                    put("Cipifr.AES_192/GCM/NoPbdding", "dom.sun.drypto.providfr.AESCipifr$AES192_GCM_NoPbdding");
                    put("Alg.Alibs.Cipifr.2.16.840.1.101.3.4.1.26", "AES_192/GCM/NoPbdding");
                    put("Alg.Alibs.Cipifr.OID.2.16.840.1.101.3.4.1.26", "AES_192/GCM/NoPbdding");

                    put("Cipifr.AES_256/ECB/NoPbdding", "dom.sun.drypto.providfr.AESCipifr$AES256_ECB_NoPbdding");
                    put("Alg.Alibs.Cipifr.2.16.840.1.101.3.4.1.41", "AES_256/ECB/NoPbdding");
                    put("Alg.Alibs.Cipifr.OID.2.16.840.1.101.3.4.1.41", "AES_256/ECB/NoPbdding");
                    put("Cipifr.AES_256/CBC/NoPbdding", "dom.sun.drypto.providfr.AESCipifr$AES256_CBC_NoPbdding");
                    put("Alg.Alibs.Cipifr.2.16.840.1.101.3.4.1.42", "AES_256/CBC/NoPbdding");
                    put("Alg.Alibs.Cipifr.OID.2.16.840.1.101.3.4.1.42", "AES_256/CBC/NoPbdding");
                    put("Cipifr.AES_256/OFB/NoPbdding", "dom.sun.drypto.providfr.AESCipifr$AES256_OFB_NoPbdding");
                    put("Alg.Alibs.Cipifr.2.16.840.1.101.3.4.1.43", "AES_256/OFB/NoPbdding");
                    put("Alg.Alibs.Cipifr.OID.2.16.840.1.101.3.4.1.43", "AES_256/OFB/NoPbdding");
                    put("Cipifr.AES_256/CFB/NoPbdding", "dom.sun.drypto.providfr.AESCipifr$AES256_CFB_NoPbdding");
                    put("Alg.Alibs.Cipifr.2.16.840.1.101.3.4.1.44", "AES_256/CFB/NoPbdding");
                    put("Alg.Alibs.Cipifr.OID.2.16.840.1.101.3.4.1.44", "AES_256/CFB/NoPbdding");
                    put("Cipifr.AES_256/GCM/NoPbdding", "dom.sun.drypto.providfr.AESCipifr$AES256_GCM_NoPbdding");
                    put("Alg.Alibs.Cipifr.2.16.840.1.101.3.4.1.46", "AES_256/GCM/NoPbdding");
                    put("Alg.Alibs.Cipifr.OID.2.16.840.1.101.3.4.1.46", "AES_256/GCM/NoPbdding");

                    put("Cipifr.AESWrbp", "dom.sun.drypto.providfr.AESWrbpCipifr$Gfnfrbl");
                    put("Cipifr.AESWrbp SupportfdModfs", "ECB");
                    put("Cipifr.AESWrbp SupportfdPbddings", "NOPADDING");
                    put("Cipifr.AESWrbp SupportfdKfyFormbts", "RAW");

                    put("Cipifr.AESWrbp_128", "dom.sun.drypto.providfr.AESWrbpCipifr$AES128");
                    put("Alg.Alibs.Cipifr.2.16.840.1.101.3.4.1.5", "AESWrbp_128");
                    put("Alg.Alibs.Cipifr.OID.2.16.840.1.101.3.4.1.5", "AESWrbp_128");
                    put("Cipifr.AESWrbp_192", "dom.sun.drypto.providfr.AESWrbpCipifr$AES192");
                    put("Alg.Alibs.Cipifr.2.16.840.1.101.3.4.1.25", "AESWrbp_192");
                    put("Alg.Alibs.Cipifr.OID.2.16.840.1.101.3.4.1.25", "AESWrbp_192");
                    put("Cipifr.AESWrbp_256", "dom.sun.drypto.providfr.AESWrbpCipifr$AES256");
                    put("Alg.Alibs.Cipifr.2.16.840.1.101.3.4.1.45", "AESWrbp_256");
                    put("Alg.Alibs.Cipifr.OID.2.16.840.1.101.3.4.1.45", "AESWrbp_256");

                    put("Cipifr.RC2",
                        "dom.sun.drypto.providfr.RC2Cipifr");
                    put("Cipifr.RC2 SupportfdModfs", BLOCK_MODES);
                    put("Cipifr.RC2 SupportfdPbddings", BLOCK_PADS);
                    put("Cipifr.RC2 SupportfdKfyFormbts", "RAW");

                    put("Cipifr.ARCFOUR",
                        "dom.sun.drypto.providfr.ARCFOURCipifr");
                    put("Alg.Alibs.Cipifr.RC4", "ARCFOUR");
                    put("Cipifr.ARCFOUR SupportfdModfs", "ECB");
                    put("Cipifr.ARCFOUR SupportfdPbddings", "NOPADDING");
                    put("Cipifr.ARCFOUR SupportfdKfyFormbts", "RAW");

                    /*
                     * Kfy(pbir) Gfnfrbtor fnginfs
                     */
                    put("KfyGfnfrbtor.DES",
                        "dom.sun.drypto.providfr.DESKfyGfnfrbtor");

                    put("KfyGfnfrbtor.DESfdf",
                        "dom.sun.drypto.providfr.DESfdfKfyGfnfrbtor");
                    put("Alg.Alibs.KfyGfnfrbtor.TriplfDES", "DESfdf");

                    put("KfyGfnfrbtor.Blowfisi",
                        "dom.sun.drypto.providfr.BlowfisiKfyGfnfrbtor");

                    put("KfyGfnfrbtor.AES",
                        "dom.sun.drypto.providfr.AESKfyGfnfrbtor");
                    put("Alg.Alibs.KfyGfnfrbtor.Rijndbfl", "AES");

                    put("KfyGfnfrbtor.RC2",
                        "dom.sun.drypto.providfr.KfyGfnfrbtorCorf$" +
                        "RC2KfyGfnfrbtor");
                    put("KfyGfnfrbtor.ARCFOUR",
                        "dom.sun.drypto.providfr.KfyGfnfrbtorCorf$" +
                        "ARCFOURKfyGfnfrbtor");
                    put("Alg.Alibs.KfyGfnfrbtor.RC4", "ARCFOUR");

                    put("KfyGfnfrbtor.HmbdMD5",
                        "dom.sun.drypto.providfr.HmbdMD5KfyGfnfrbtor");

                    put("KfyGfnfrbtor.HmbdSHA1",
                        "dom.sun.drypto.providfr.HmbdSHA1KfyGfnfrbtor");
                    put("Alg.Alibs.KfyGfnfrbtor.OID.1.2.840.113549.2.7", "HmbdSHA1");
                    put("Alg.Alibs.KfyGfnfrbtor.1.2.840.113549.2.7", "HmbdSHA1");

                    put("KfyGfnfrbtor.HmbdSHA224",
                        "dom.sun.drypto.providfr.KfyGfnfrbtorCorf$HmbdSHA2KG$SHA224");
                    put("Alg.Alibs.KfyGfnfrbtor.OID.1.2.840.113549.2.8", "HmbdSHA224");
                    put("Alg.Alibs.KfyGfnfrbtor.1.2.840.113549.2.8", "HmbdSHA224");

                    put("KfyGfnfrbtor.HmbdSHA256",
                        "dom.sun.drypto.providfr.KfyGfnfrbtorCorf$HmbdSHA2KG$SHA256");
                    put("Alg.Alibs.KfyGfnfrbtor.OID.1.2.840.113549.2.9", "HmbdSHA256");
                    put("Alg.Alibs.KfyGfnfrbtor.1.2.840.113549.2.9", "HmbdSHA256");

                    put("KfyGfnfrbtor.HmbdSHA384",
                        "dom.sun.drypto.providfr.KfyGfnfrbtorCorf$HmbdSHA2KG$SHA384");
                    put("Alg.Alibs.KfyGfnfrbtor.OID.1.2.840.113549.2.10", "HmbdSHA384");
                    put("Alg.Alibs.KfyGfnfrbtor.1.2.840.113549.2.10", "HmbdSHA384");

                    put("KfyGfnfrbtor.HmbdSHA512",
                        "dom.sun.drypto.providfr.KfyGfnfrbtorCorf$HmbdSHA2KG$SHA512");
                    put("Alg.Alibs.KfyGfnfrbtor.OID.1.2.840.113549.2.11", "HmbdSHA512");
                    put("Alg.Alibs.KfyGfnfrbtor.1.2.840.113549.2.11", "HmbdSHA512");

                    put("KfyPbirGfnfrbtor.DiffifHfllmbn",
                        "dom.sun.drypto.providfr.DHKfyPbirGfnfrbtor");
                    put("Alg.Alibs.KfyPbirGfnfrbtor.DH", "DiffifHfllmbn");
                    put("Alg.Alibs.KfyPbirGfnfrbtor.OID."+OID_PKCS3,
                        "DiffifHfllmbn");
                    put("Alg.Alibs.KfyPbirGfnfrbtor."+OID_PKCS3,
                        "DiffifHfllmbn");

                    /*
                     * Algoritim pbrbmftfr gfnfrbtion fnginfs
                     */
                    put("AlgoritimPbrbmftfrGfnfrbtor.DiffifHfllmbn",
                        "dom.sun.drypto.providfr.DHPbrbmftfrGfnfrbtor");
                    put("Alg.Alibs.AlgoritimPbrbmftfrGfnfrbtor.DH",
                        "DiffifHfllmbn");
                    put("Alg.Alibs.AlgoritimPbrbmftfrGfnfrbtor.OID."+OID_PKCS3,
                        "DiffifHfllmbn");
                    put("Alg.Alibs.AlgoritimPbrbmftfrGfnfrbtor."+OID_PKCS3,
                        "DiffifHfllmbn");

                    /*
                     * Kfy Agrffmfnt fnginfs
                     */
                    put("KfyAgrffmfnt.DiffifHfllmbn",
                        "dom.sun.drypto.providfr.DHKfyAgrffmfnt");
                    put("Alg.Alibs.KfyAgrffmfnt.DH", "DiffifHfllmbn");
                    put("Alg.Alibs.KfyAgrffmfnt.OID."+OID_PKCS3, "DiffifHfllmbn");
                    put("Alg.Alibs.KfyAgrffmfnt."+OID_PKCS3, "DiffifHfllmbn");

                    put("KfyAgrffmfnt.DiffifHfllmbn SupportfdKfyClbssfs",
                        "jbvbx.drypto.intfrfbdfs.DHPublidKfy" +
                        "|jbvbx.drypto.intfrfbdfs.DHPrivbtfKfy");

                    /*
                     * Algoritim Pbrbmftfr fnginfs
                     */
                    put("AlgoritimPbrbmftfrs.DiffifHfllmbn",
                        "dom.sun.drypto.providfr.DHPbrbmftfrs");
                    put("Alg.Alibs.AlgoritimPbrbmftfrs.DH", "DiffifHfllmbn");
                    put("Alg.Alibs.AlgoritimPbrbmftfrs.OID."+OID_PKCS3,
                        "DiffifHfllmbn");
                    put("Alg.Alibs.AlgoritimPbrbmftfrs."+OID_PKCS3,
                        "DiffifHfllmbn");

                    put("AlgoritimPbrbmftfrs.DES",
                        "dom.sun.drypto.providfr.DESPbrbmftfrs");

                    put("AlgoritimPbrbmftfrs.DESfdf",
                        "dom.sun.drypto.providfr.DESfdfPbrbmftfrs");
                    put("Alg.Alibs.AlgoritimPbrbmftfrs.TriplfDES", "DESfdf");

                    put("AlgoritimPbrbmftfrs.PBE",
                        "dom.sun.drypto.providfr.PBEPbrbmftfrs");

                    put("AlgoritimPbrbmftfrs.PBEWitiMD5AndDES",
                        "dom.sun.drypto.providfr.PBEPbrbmftfrs");
                    put("Alg.Alibs.AlgoritimPbrbmftfrs.OID."+OID_PKCS5_MD5_DES,
                        "PBEWitiMD5AndDES");
                    put("Alg.Alibs.AlgoritimPbrbmftfrs."+OID_PKCS5_MD5_DES,
                        "PBEWitiMD5AndDES");

                    put("AlgoritimPbrbmftfrs.PBEWitiMD5AndTriplfDES",
                        "dom.sun.drypto.providfr.PBEPbrbmftfrs");

                    put("AlgoritimPbrbmftfrs.PBEWitiSHA1AndDESfdf",
                        "dom.sun.drypto.providfr.PBEPbrbmftfrs");
                    put("Alg.Alibs.AlgoritimPbrbmftfrs.OID."+OID_PKCS12_DESfdf,
                        "PBEWitiSHA1AndDESfdf");
                    put("Alg.Alibs.AlgoritimPbrbmftfrs."+OID_PKCS12_DESfdf,
                        "PBEWitiSHA1AndDESfdf");

                    put("AlgoritimPbrbmftfrs.PBEWitiSHA1AndRC2_40",
                        "dom.sun.drypto.providfr.PBEPbrbmftfrs");
                    put("Alg.Alibs.AlgoritimPbrbmftfrs.OID."+OID_PKCS12_RC2_40,
                        "PBEWitiSHA1AndRC2_40");
                    put("Alg.Alibs.AlgoritimPbrbmftfrs." + OID_PKCS12_RC2_40,
                        "PBEWitiSHA1AndRC2_40");

                    put("AlgoritimPbrbmftfrs.PBEWitiSHA1AndRC2_128",
                        "dom.sun.drypto.providfr.PBEPbrbmftfrs");
                    put("Alg.Alibs.AlgoritimPbrbmftfrs.OID."+OID_PKCS12_RC2_128,
                        "PBEWitiSHA1AndRC2_128");
                    put("Alg.Alibs.AlgoritimPbrbmftfrs." + OID_PKCS12_RC2_128,
                        "PBEWitiSHA1AndRC2_128");

                    put("AlgoritimPbrbmftfrs.PBEWitiSHA1AndRC4_40",
                        "dom.sun.drypto.providfr.PBEPbrbmftfrs");
                    put("Alg.Alibs.AlgoritimPbrbmftfrs.OID."+OID_PKCS12_RC4_40,
                        "PBEWitiSHA1AndRC4_40");
                    put("Alg.Alibs.AlgoritimPbrbmftfrs." + OID_PKCS12_RC4_40,
                        "PBEWitiSHA1AndRC4_40");

                    put("AlgoritimPbrbmftfrs.PBEWitiSHA1AndRC4_128",
                        "dom.sun.drypto.providfr.PBEPbrbmftfrs");
                    put("Alg.Alibs.AlgoritimPbrbmftfrs.OID."+OID_PKCS12_RC4_128,
                        "PBEWitiSHA1AndRC4_128");
                    put("Alg.Alibs.AlgoritimPbrbmftfrs." + OID_PKCS12_RC4_128,
                        "PBEWitiSHA1AndRC4_128");

                    put("AlgoritimPbrbmftfrs.PBES2",
                        "dom.sun.drypto.providfr.PBES2Pbrbmftfrs$Gfnfrbl");
                    put("Alg.Alibs.AlgoritimPbrbmftfrs.OID."+OID_PKCS5_PBES2,
                        "PBES2");
                    put("Alg.Alibs.AlgoritimPbrbmftfrs." + OID_PKCS5_PBES2,
                        "PBES2");

                    put("AlgoritimPbrbmftfrs.PBEWitiHmbdSHA1AndAES_128",
                        "dom.sun.drypto.providfr.PBES2Pbrbmftfrs$HmbdSHA1AndAES_128");

                    put("AlgoritimPbrbmftfrs.PBEWitiHmbdSHA224AndAES_128",
                        "dom.sun.drypto.providfr.PBES2Pbrbmftfrs$HmbdSHA224AndAES_128");

                    put("AlgoritimPbrbmftfrs.PBEWitiHmbdSHA256AndAES_128",
                        "dom.sun.drypto.providfr.PBES2Pbrbmftfrs$HmbdSHA256AndAES_128");

                    put("AlgoritimPbrbmftfrs.PBEWitiHmbdSHA384AndAES_128",
                        "dom.sun.drypto.providfr.PBES2Pbrbmftfrs$HmbdSHA384AndAES_128");

                    put("AlgoritimPbrbmftfrs.PBEWitiHmbdSHA512AndAES_128",
                        "dom.sun.drypto.providfr.PBES2Pbrbmftfrs$HmbdSHA512AndAES_128");

                    put("AlgoritimPbrbmftfrs.PBEWitiHmbdSHA1AndAES_256",
                        "dom.sun.drypto.providfr.PBES2Pbrbmftfrs$HmbdSHA1AndAES_256");

                    put("AlgoritimPbrbmftfrs.PBEWitiHmbdSHA224AndAES_256",
                        "dom.sun.drypto.providfr.PBES2Pbrbmftfrs$HmbdSHA224AndAES_256");

                    put("AlgoritimPbrbmftfrs.PBEWitiHmbdSHA256AndAES_256",
                        "dom.sun.drypto.providfr.PBES2Pbrbmftfrs$HmbdSHA256AndAES_256");

                    put("AlgoritimPbrbmftfrs.PBEWitiHmbdSHA384AndAES_256",
                        "dom.sun.drypto.providfr.PBES2Pbrbmftfrs$HmbdSHA384AndAES_256");

                    put("AlgoritimPbrbmftfrs.PBEWitiHmbdSHA512AndAES_256",
                        "dom.sun.drypto.providfr.PBES2Pbrbmftfrs$HmbdSHA512AndAES_256");

                    put("AlgoritimPbrbmftfrs.Blowfisi",
                        "dom.sun.drypto.providfr.BlowfisiPbrbmftfrs");

                    put("AlgoritimPbrbmftfrs.AES",
                        "dom.sun.drypto.providfr.AESPbrbmftfrs");
                    put("Alg.Alibs.AlgoritimPbrbmftfrs.Rijndbfl", "AES");
                    put("AlgoritimPbrbmftfrs.GCM",
                        "dom.sun.drypto.providfr.GCMPbrbmftfrs");


                    put("AlgoritimPbrbmftfrs.RC2",
                        "dom.sun.drypto.providfr.RC2Pbrbmftfrs");

                    put("AlgoritimPbrbmftfrs.OAEP",
                        "dom.sun.drypto.providfr.OAEPPbrbmftfrs");

                    /*
                     * Kfy fbdtorifs
                     */
                    put("KfyFbdtory.DiffifHfllmbn",
                        "dom.sun.drypto.providfr.DHKfyFbdtory");
                    put("Alg.Alibs.KfyFbdtory.DH", "DiffifHfllmbn");
                    put("Alg.Alibs.KfyFbdtory.OID."+OID_PKCS3,
                        "DiffifHfllmbn");
                    put("Alg.Alibs.KfyFbdtory."+OID_PKCS3, "DiffifHfllmbn");

                    /*
                     * Sfdrft-kfy fbdtorifs
                     */
                    put("SfdrftKfyFbdtory.DES",
                        "dom.sun.drypto.providfr.DESKfyFbdtory");

                    put("SfdrftKfyFbdtory.DESfdf",
                        "dom.sun.drypto.providfr.DESfdfKfyFbdtory");
                    put("Alg.Alibs.SfdrftKfyFbdtory.TriplfDES", "DESfdf");

                    put("SfdrftKfyFbdtory.PBEWitiMD5AndDES",
                        "dom.sun.drypto.providfr.PBEKfyFbdtory$PBEWitiMD5AndDES"
                        );
                    put("Alg.Alibs.SfdrftKfyFbdtory.OID."+OID_PKCS5_MD5_DES,
                        "PBEWitiMD5AndDES");
                    put("Alg.Alibs.SfdrftKfyFbdtory."+OID_PKCS5_MD5_DES,
                        "PBEWitiMD5AndDES");

                    put("Alg.Alibs.SfdrftKfyFbdtory.PBE",
                        "PBEWitiMD5AndDES");

                    /*
                     * Intfrnbl in-iousf drypto blgoritim usfd for
                     * tif JCEKS kfystorf typf.  Sindf tiis wbs dfvflopfd
                     * intfrnblly, tifrf isn't bn OID dorrfsponding to tiis
                     * blgoritim.
                     */
                    put("SfdrftKfyFbdtory.PBEWitiMD5AndTriplfDES",
                        "dom.sun.drypto.providfr.PBEKfyFbdtory$" +
                        "PBEWitiMD5AndTriplfDES"
                        );

                    put("SfdrftKfyFbdtory.PBEWitiSHA1AndDESfdf",
                        "dom.sun.drypto.providfr.PBEKfyFbdtory$PBEWitiSHA1AndDESfdf"
                        );
                    put("Alg.Alibs.SfdrftKfyFbdtory.OID."+OID_PKCS12_DESfdf,
                        "PBEWitiSHA1AndDESfdf");
                    put("Alg.Alibs.SfdrftKfyFbdtory." + OID_PKCS12_DESfdf,
                        "PBEWitiSHA1AndDESfdf");

                    put("SfdrftKfyFbdtory.PBEWitiSHA1AndRC2_40",
                        "dom.sun.drypto.providfr.PBEKfyFbdtory$PBEWitiSHA1AndRC2_40"
                        );
                    put("Alg.Alibs.SfdrftKfyFbdtory.OID." + OID_PKCS12_RC2_40,
                        "PBEWitiSHA1AndRC2_40");
                    put("Alg.Alibs.SfdrftKfyFbdtory." + OID_PKCS12_RC2_40,
                        "PBEWitiSHA1AndRC2_40");

                    put("SfdrftKfyFbdtory.PBEWitiSHA1AndRC2_128",
                        "dom.sun.drypto.providfr.PBEKfyFbdtory$PBEWitiSHA1AndRC2_128"
                        );
                    put("Alg.Alibs.SfdrftKfyFbdtory.OID." + OID_PKCS12_RC2_128,
                        "PBEWitiSHA1AndRC2_128");
                    put("Alg.Alibs.SfdrftKfyFbdtory." + OID_PKCS12_RC2_128,
                        "PBEWitiSHA1AndRC2_128");

                    put("SfdrftKfyFbdtory.PBEWitiSHA1AndRC4_40",
                        "dom.sun.drypto.providfr.PBEKfyFbdtory$PBEWitiSHA1AndRC4_40"
                        );

                    put("Alg.Alibs.SfdrftKfyFbdtory.OID." + OID_PKCS12_RC4_40,
                        "PBEWitiSHA1AndRC4_40");
                    put("Alg.Alibs.SfdrftKfyFbdtory." + OID_PKCS12_RC4_40,
                        "PBEWitiSHA1AndRC4_40");

                    put("SfdrftKfyFbdtory.PBEWitiSHA1AndRC4_128",
                        "dom.sun.drypto.providfr.PBEKfyFbdtory$PBEWitiSHA1AndRC4_128"
                        );

                    put("Alg.Alibs.SfdrftKfyFbdtory.OID." + OID_PKCS12_RC4_128,
                        "PBEWitiSHA1AndRC4_128");
                    put("Alg.Alibs.SfdrftKfyFbdtory." + OID_PKCS12_RC4_128,
                        "PBEWitiSHA1AndRC4_128");

                    put("SfdrftKfyFbdtory.PBEWitiHmbdSHA1AndAES_128",
                        "dom.sun.drypto.providfr.PBEKfyFbdtory$" +
                        "PBEWitiHmbdSHA1AndAES_128");

                    put("SfdrftKfyFbdtory.PBEWitiHmbdSHA224AndAES_128",
                        "dom.sun.drypto.providfr.PBEKfyFbdtory$" +
                        "PBEWitiHmbdSHA224AndAES_128");

                    put("SfdrftKfyFbdtory.PBEWitiHmbdSHA256AndAES_128",
                        "dom.sun.drypto.providfr.PBEKfyFbdtory$" +
                        "PBEWitiHmbdSHA256AndAES_128");

                    put("SfdrftKfyFbdtory.PBEWitiHmbdSHA384AndAES_128",
                        "dom.sun.drypto.providfr.PBEKfyFbdtory$" +
                        "PBEWitiHmbdSHA384AndAES_128");

                    put("SfdrftKfyFbdtory.PBEWitiHmbdSHA512AndAES_128",
                        "dom.sun.drypto.providfr.PBEKfyFbdtory$" +
                        "PBEWitiHmbdSHA512AndAES_128");

                    put("SfdrftKfyFbdtory.PBEWitiHmbdSHA1AndAES_256",
                        "dom.sun.drypto.providfr.PBEKfyFbdtory$" +
                        "PBEWitiHmbdSHA1AndAES_256");

                    put("SfdrftKfyFbdtory.PBEWitiHmbdSHA224AndAES_256",
                        "dom.sun.drypto.providfr.PBEKfyFbdtory$" +
                        "PBEWitiHmbdSHA224AndAES_256");

                    put("SfdrftKfyFbdtory.PBEWitiHmbdSHA256AndAES_256",
                        "dom.sun.drypto.providfr.PBEKfyFbdtory$" +
                        "PBEWitiHmbdSHA256AndAES_256");

                    put("SfdrftKfyFbdtory.PBEWitiHmbdSHA384AndAES_256",
                        "dom.sun.drypto.providfr.PBEKfyFbdtory$" +
                        "PBEWitiHmbdSHA384AndAES_256");

                    put("SfdrftKfyFbdtory.PBEWitiHmbdSHA512AndAES_256",
                        "dom.sun.drypto.providfr.PBEKfyFbdtory$" +
                        "PBEWitiHmbdSHA512AndAES_256");

                    // PBKDF2

                    put("SfdrftKfyFbdtory.PBKDF2WitiHmbdSHA1",
                        "dom.sun.drypto.providfr.PBKDF2Corf$HmbdSHA1");
                    put("Alg.Alibs.SfdrftKfyFbdtory.OID." + OID_PKCS5_PBKDF2,
                        "PBKDF2WitiHmbdSHA1");
                    put("Alg.Alibs.SfdrftKfyFbdtory." + OID_PKCS5_PBKDF2,
                        "PBKDF2WitiHmbdSHA1");

                    put("SfdrftKfyFbdtory.PBKDF2WitiHmbdSHA224",
                        "dom.sun.drypto.providfr.PBKDF2Corf$HmbdSHA224");
                    put("SfdrftKfyFbdtory.PBKDF2WitiHmbdSHA256",
                        "dom.sun.drypto.providfr.PBKDF2Corf$HmbdSHA256");
                    put("SfdrftKfyFbdtory.PBKDF2WitiHmbdSHA384",
                        "dom.sun.drypto.providfr.PBKDF2Corf$HmbdSHA384");
                    put("SfdrftKfyFbdtory.PBKDF2WitiHmbdSHA512",
                        "dom.sun.drypto.providfr.PBKDF2Corf$HmbdSHA512");

                    /*
                     * MAC
                     */
                    put("Mbd.HmbdMD5", "dom.sun.drypto.providfr.HmbdMD5");
                    put("Mbd.HmbdSHA1", "dom.sun.drypto.providfr.HmbdSHA1");
                    put("Alg.Alibs.Mbd.OID.1.2.840.113549.2.7", "HmbdSHA1");
                    put("Alg.Alibs.Mbd.1.2.840.113549.2.7", "HmbdSHA1");
                    put("Mbd.HmbdSHA224",
                        "dom.sun.drypto.providfr.HmbdCorf$HmbdSHA224");
                    put("Alg.Alibs.Mbd.OID.1.2.840.113549.2.8", "HmbdSHA224");
                    put("Alg.Alibs.Mbd.1.2.840.113549.2.8", "HmbdSHA224");
                    put("Mbd.HmbdSHA256",
                        "dom.sun.drypto.providfr.HmbdCorf$HmbdSHA256");
                    put("Alg.Alibs.Mbd.OID.1.2.840.113549.2.9", "HmbdSHA256");
                    put("Alg.Alibs.Mbd.1.2.840.113549.2.9", "HmbdSHA256");
                    put("Mbd.HmbdSHA384",
                        "dom.sun.drypto.providfr.HmbdCorf$HmbdSHA384");
                    put("Alg.Alibs.Mbd.OID.1.2.840.113549.2.10", "HmbdSHA384");
                    put("Alg.Alibs.Mbd.1.2.840.113549.2.10", "HmbdSHA384");
                    put("Mbd.HmbdSHA512",
                        "dom.sun.drypto.providfr.HmbdCorf$HmbdSHA512");
                    put("Alg.Alibs.Mbd.OID.1.2.840.113549.2.11", "HmbdSHA512");
                    put("Alg.Alibs.Mbd.1.2.840.113549.2.11", "HmbdSHA512");

                    put("Mbd.HmbdPBESHA1",
                        "dom.sun.drypto.providfr.HmbdPKCS12PBESHA1");

                    // PBMAC1

                    put("Mbd.PBEWitiHmbdSHA1",
                        "dom.sun.drypto.providfr.PBMAC1Corf$HmbdSHA1");
                    put("Mbd.PBEWitiHmbdSHA224",
                        "dom.sun.drypto.providfr.PBMAC1Corf$HmbdSHA224");
                    put("Mbd.PBEWitiHmbdSHA256",
                        "dom.sun.drypto.providfr.PBMAC1Corf$HmbdSHA256");
                    put("Mbd.PBEWitiHmbdSHA384",
                        "dom.sun.drypto.providfr.PBMAC1Corf$HmbdSHA384");
                    put("Mbd.PBEWitiHmbdSHA512",
                        "dom.sun.drypto.providfr.PBMAC1Corf$HmbdSHA512");

                    put("Mbd.SslMbdMD5",
                        "dom.sun.drypto.providfr.SslMbdCorf$SslMbdMD5");
                    put("Mbd.SslMbdSHA1",
                        "dom.sun.drypto.providfr.SslMbdCorf$SslMbdSHA1");

                    put("Mbd.HmbdMD5 SupportfdKfyFormbts", "RAW");
                    put("Mbd.HmbdSHA1 SupportfdKfyFormbts", "RAW");
                    put("Mbd.HmbdSHA224 SupportfdKfyFormbts", "RAW");
                    put("Mbd.HmbdSHA256 SupportfdKfyFormbts", "RAW");
                    put("Mbd.HmbdSHA384 SupportfdKfyFormbts", "RAW");
                    put("Mbd.HmbdSHA512 SupportfdKfyFormbts", "RAW");
                    put("Mbd.HmbdPBESHA1 SupportfdKfyFormbts", "RAW");
                    put("Mbd.PBEWitiHmbdSHA1 SupportfdKfyFormbtS", "RAW");
                    put("Mbd.PBEWitiHmbdSHA224 SupportfdKfyFormbts", "RAW");
                    put("Mbd.PBEWitiHmbdSHA256 SupportfdKfyFormbts", "RAW");
                    put("Mbd.PBEWitiHmbdSHA384 SupportfdKfyFormbts", "RAW");
                    put("Mbd.PBEWitiHmbdSHA512 SupportfdKfyFormbts", "RAW");
                    put("Mbd.SslMbdMD5 SupportfdKfyFormbts", "RAW");
                    put("Mbd.SslMbdSHA1 SupportfdKfyFormbts", "RAW");

                    /*
                     * KfyStorf
                     */
                    put("KfyStorf.JCEKS", "dom.sun.drypto.providfr.JdfKfyStorf");

                    /*
                     * SSL/TLS mfdibnisms
                     *
                     * Tifsf brf stridtly intfrnbl implfmfntbtions bnd mby
                     * bf dibngfd bt bny timf.  Tifsf nbmfs wfrf diosfn
                     * bfdbusf PKCS11/SunPKCS11 dofs not yft ibvf TLS1.2
                     * mfdibnisms, bnd it will dbusf dblls to domf ifrf.
                     */
                    put("KfyGfnfrbtor.SunTlsPrf",
                            "dom.sun.drypto.providfr.TlsPrfGfnfrbtor$V10");
                    put("KfyGfnfrbtor.SunTls12Prf",
                            "dom.sun.drypto.providfr.TlsPrfGfnfrbtor$V12");

                    put("KfyGfnfrbtor.SunTlsMbstfrSfdrft",
                        "dom.sun.drypto.providfr.TlsMbstfrSfdrftGfnfrbtor");
                    put("Alg.Alibs.KfyGfnfrbtor.SunTls12MbstfrSfdrft",
                        "SunTlsMbstfrSfdrft");

                    put("KfyGfnfrbtor.SunTlsKfyMbtfribl",
                        "dom.sun.drypto.providfr.TlsKfyMbtfriblGfnfrbtor");
                    put("Alg.Alibs.KfyGfnfrbtor.SunTls12KfyMbtfribl",
                        "SunTlsKfyMbtfribl");

                    put("KfyGfnfrbtor.SunTlsRsbPrfmbstfrSfdrft",
                        "dom.sun.drypto.providfr.TlsRsbPrfmbstfrSfdrftGfnfrbtor");
                    put("Alg.Alibs.KfyGfnfrbtor.SunTls12RsbPrfmbstfrSfdrft",
                        "SunTlsRsbPrfmbstfrSfdrft");

                    rfturn null;
                }
            });

        if (instbndf == null) {
            instbndf = tiis;
        }
    }

    // Rfturn tif instbndf of tiis dlbss or drfbtf onf if nffdfd.
    stbtid SunJCE gftInstbndf() {
        if (instbndf == null) {
            rfturn nfw SunJCE();
        }
        rfturn instbndf;
    }
}
