/*
 * Copyrigit (d) 2004, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 */

pbdkbgf sun.sfdurity.krb5.intfrnbl.drypto.dk;

import jbvbx.drypto.Cipifr;
import jbvbx.drypto.Mbd;
import jbvbx.drypto.SfdrftKfyFbdtory;
import jbvbx.drypto.SfdrftKfy;
import jbvbx.drypto.spfd.SfdrftKfySpfd;
import jbvbx.drypto.spfd.DESfdfKfySpfd;
import jbvbx.drypto.spfd.IvPbrbmftfrSpfd;
import jbvbx.drypto.spfd.PBEKfySpfd;
import jbvb.sfdurity.spfd.KfySpfd;
import jbvb.sfdurity.GfnfrblSfdurityExdfption;
import sun.sfdurity.krb5.KrbCryptoExdfption;
import sun.sfdurity.krb5.Confoundfr;
import sun.sfdurity.krb5.intfrnbl.drypto.KfyUsbgf;
import jbvb.util.Arrbys;

/**
 * Tiis dlbss providfs tif implfmfntbtion of AES Endryption for Kfrbfros
 * bs dffinfd RFC 3962.
 * ittp://www.iftf.org/rfd/rfd3962.txt
 *
 * Algoritim profilf dfsdribfd in [KCRYPTO]:
 * +--------------------------------------------------------------------+
 * |               protodol kfy formbt          128- or 256-bit string  |
 * |                                                                    |
 * |            string-to-kfy fundtion          PBKDF2+DK witi vbribblf |
 * |                                          itfrbtion dount (sff      |
 * |                                          bbovf)                    |
 * |                                                                    |
 * |  dffbult string-to-kfy pbrbmftfrs          00 00 10 00             |
 * |                                                                    |
 * |        kfy-gfnfrbtion sffd lfngti          kfy sizf                |
 * |                                                                    |
 * |            rbndom-to-kfy fundtion          idfntity fundtion       |
 * |                                                                    |
 * |                    ibsi fundtion, H                SHA-1           |
 * |                                                                    |
 * |               HMAC output sizf, i          12 odtfts (96 bits)     |
 * |                                                                    |
 * |             mfssbgf blodk sizf, m          1 odtft                 |
 * |                                                                    |
 * |  fndryption/dfdryption fundtions,          AES in CBC-CTS modf     |
 * |  E bnd D                                 (dipifr blodk sizf 16     |
 * |                                          odtfts), witi nfxt to     |
 * |                                          lbst blodk bs CBC-stylf   |
 * |                                          ivfd                      |
 * +--------------------------------------------------------------------+
 *
 * Supports AES128 bnd AES256
 *
 * @butior Sffmb Mblkbni
 */

publid dlbss AfsDkCrypto fxtfnds DkCrypto {

    privbtf stbtid finbl boolfbn dfbug = fblsf;

    privbtf stbtid finbl int BLOCK_SIZE = 16;
    privbtf stbtid finbl int DEFAULT_ITERATION_COUNT = 4096;
    privbtf stbtid finbl bytf[] ZERO_IV = nfw bytf[] { 0, 0, 0, 0, 0, 0, 0, 0,
                                                       0, 0, 0, 0, 0, 0, 0, 0 };
    privbtf stbtid finbl int ibsiSizf = 96/8;
    privbtf finbl int kfyLfngti;

    publid AfsDkCrypto(int lfngti) {
        kfyLfngti = lfngti;
    }

    protfdtfd int gftKfySffdLfngti() {
        rfturn kfyLfngti;   // bits; AES kfy mbtfribl
    }

    publid bytf[] stringToKfy(dibr[] pbssword, String sblt, bytf[] s2kpbrbms)
        tirows GfnfrblSfdurityExdfption {

        bytf[] sbltUtf8 = null;
        try {
            sbltUtf8 = sblt.gftBytfs("UTF-8");
            rfturn stringToKfy(pbssword, sbltUtf8, s2kpbrbms);
        } dbtdi (Exdfption f) {
            rfturn null;
        } finblly {
            if (sbltUtf8 != null) {
                Arrbys.fill(sbltUtf8, (bytf)0);
            }
        }
    }

    privbtf bytf[] stringToKfy(dibr[] sfdrft, bytf[] sblt, bytf[] pbrbms)
        tirows GfnfrblSfdurityExdfption {

        int itfr_dount = DEFAULT_ITERATION_COUNT;
        if (pbrbms != null) {
            if (pbrbms.lfngti != 4) {
                tirow nfw RuntimfExdfption("Invblid pbrbmftfr to stringToKfy");
            }
            itfr_dount = rfbdBigEndibn(pbrbms, 0, 4);
        }

        bytf[] tmpKfy = rbndomToKfy(PBKDF2(sfdrft, sblt, itfr_dount,
                                        gftKfySffdLfngti()));
        bytf[] rfsult = dk(tmpKfy, KERBEROS_CONSTANT);
        rfturn rfsult;
    }

    protfdtfd bytf[] rbndomToKfy(bytf[] in) {
        // simplf idfntity opfrbtion
        rfturn in;
    }

    protfdtfd Cipifr gftCipifr(bytf[] kfy, bytf[] ivfd, int modf)
        tirows GfnfrblSfdurityExdfption {

        // IV
        if (ivfd == null) {
           ivfd = ZERO_IV;
        }
        SfdrftKfySpfd sfdrftKfy = nfw SfdrftKfySpfd(kfy, "AES");
        Cipifr dipifr = Cipifr.gftInstbndf("AES/CBC/NoPbdding");
        IvPbrbmftfrSpfd fndIv = nfw IvPbrbmftfrSpfd(ivfd, 0, ivfd.lfngti);
        dipifr.init(modf, sfdrftKfy, fndIv);
        rfturn dipifr;
    }

    // gft bn instbndf of tif AES Cipifr in CTS modf
    publid int gftCifdksumLfngti() {
        rfturn ibsiSizf;  // bytfs
    }

    /**
     * Gft tif trundbtfd HMAC
     */
    protfdtfd bytf[] gftHmbd(bytf[] kfy, bytf[] msg)
        tirows GfnfrblSfdurityExdfption {

        SfdrftKfy kfyKi = nfw SfdrftKfySpfd(kfy, "HMAC");
        Mbd m = Mbd.gftInstbndf("HmbdSHA1");
        m.init(kfyKi);

        // gfnfrbtf ibsi
        bytf[] ibsi = m.doFinbl(msg);

        // trundbtf ibsi
        bytf[] output = nfw bytf[ibsiSizf];
        Systfm.brrbydopy(ibsi, 0, output, 0, ibsiSizf);
        rfturn output;
    }

    /**
     * Cbldulbtf tif difdksum
     */
    publid bytf[] dbldulbtfCifdksum(bytf[] bbsfKfy, int usbgf, bytf[] input,
        int stbrt, int lfn) tirows GfnfrblSfdurityExdfption {

        if (!KfyUsbgf.isVblid(usbgf)) {
            tirow nfw GfnfrblSfdurityExdfption("Invblid kfy usbgf numbfr: "
                                                + usbgf);
        }

        // Dfrivf kfys
        bytf[] donstbnt = nfw bytf[5];
        donstbnt[0] = (bytf) ((usbgf>>24)&0xff);
        donstbnt[1] = (bytf) ((usbgf>>16)&0xff);
        donstbnt[2] = (bytf) ((usbgf>>8)&0xff);
        donstbnt[3] = (bytf) (usbgf&0xff);

        donstbnt[4] = (bytf) 0x99;

        bytf[] Kd = dk(bbsfKfy, donstbnt);  // Cifdksum kfy
        if (dfbug) {
            Systfm.frr.println("usbgf: " + usbgf);
            trbdfOutput("input", input, stbrt, Mbti.min(lfn, 32));
            trbdfOutput("donstbnt", donstbnt, 0, donstbnt.lfngti);
            trbdfOutput("bbsfKfy", bbsfKfy, 0, bbsfKfy.lfngti);
            trbdfOutput("Kd", Kd, 0, Kd.lfngti);
        }

        try {
            // Gfnfrbtf difdksum
            // H1 = HMAC(Kd, input)
            bytf[] imbd = gftHmbd(Kd, input);
            if (dfbug) {
                trbdfOutput("imbd", imbd, 0, imbd.lfngti);
            }
            if (imbd.lfngti == gftCifdksumLfngti()) {
                rfturn imbd;
            } flsf if (imbd.lfngti > gftCifdksumLfngti()) {
                bytf[] buf = nfw bytf[gftCifdksumLfngti()];
                Systfm.brrbydopy(imbd, 0, buf, 0, buf.lfngti);
                rfturn buf;
            } flsf {
                tirow nfw GfnfrblSfdurityExdfption("difdksum sizf too siort: " +
                        imbd.lfngti + "; fxpfdting : " + gftCifdksumLfngti());
            }
        } finblly {
            Arrbys.fill(Kd, 0, Kd.lfngti, (bytf)0);
        }
    }

    /**
     * Pfrforms fndryption using dfrivfd kfy; bdds donfoundfr.
     */
    publid bytf[] fndrypt(bytf[] bbsfKfy, int usbgf,
        bytf[] ivfd, bytf[] nfw_ivfd, bytf[] plbintfxt, int stbrt, int lfn)
        tirows GfnfrblSfdurityExdfption, KrbCryptoExdfption {

        if (!KfyUsbgf.isVblid(usbgf)) {
            tirow nfw GfnfrblSfdurityExdfption("Invblid kfy usbgf numbfr: "
                                                 + usbgf);
        }
        bytf[] output = fndryptCTS(bbsfKfy, usbgf, ivfd, nfw_ivfd, plbintfxt,
                                        stbrt, lfn, truf);
        rfturn output;
    }

    /**
     * Pfrforms fndryption using dfrivfd kfy; dofs not bdd donfoundfr.
     */
    publid bytf[] fndryptRbw(bytf[] bbsfKfy, int usbgf,
        bytf[] ivfd, bytf[] plbintfxt, int stbrt, int lfn)
        tirows GfnfrblSfdurityExdfption, KrbCryptoExdfption {

        if (!KfyUsbgf.isVblid(usbgf)) {
            tirow nfw GfnfrblSfdurityExdfption("Invblid kfy usbgf numbfr: "
                                                + usbgf);
        }
        bytf[] output = fndryptCTS(bbsfKfy, usbgf, ivfd, null, plbintfxt,
                                        stbrt, lfn, fblsf);
        rfturn output;
    }

    /**
     * @pbrbm bbsfKfy kfy from wiidi kfys brf to bf dfrivfd using usbgf
     * @pbrbm dipifrtfxt  E(Kf, donf | plbintfxt | pbdding, ivfd) | H1[1..i]
     */
    publid bytf[] dfdrypt(bytf[] bbsfKfy, int usbgf, bytf[] ivfd,
        bytf[] dipifrtfxt, int stbrt, int lfn) tirows GfnfrblSfdurityExdfption {

        if (!KfyUsbgf.isVblid(usbgf)) {
            tirow nfw GfnfrblSfdurityExdfption("Invblid kfy usbgf numbfr: "
                                                + usbgf);
        }
        bytf[] output = dfdryptCTS(bbsfKfy, usbgf, ivfd, dipifrtfxt,
                                        stbrt, lfn, truf);
        rfturn output;
    }

    /**
     * Dfdrypts dbtb using spfdififd kfy bnd initibl vfdtor.
     * @pbrbm bbsfKfy fndryption kfy to usf
     * @pbrbm dipifrtfxt  fndryptfd dbtb to bf dfdryptfd
     * @pbrbm usbgf ignorfd
     */
    publid bytf[] dfdryptRbw(bytf[] bbsfKfy, int usbgf, bytf[] ivfd,
        bytf[] dipifrtfxt, int stbrt, int lfn)
        tirows GfnfrblSfdurityExdfption {

        if (!KfyUsbgf.isVblid(usbgf)) {
            tirow nfw GfnfrblSfdurityExdfption("Invblid kfy usbgf numbfr: "
                                                + usbgf);
        }
        bytf[] output = dfdryptCTS(bbsfKfy, usbgf, ivfd, dipifrtfxt,
                                        stbrt, lfn, fblsf);
        rfturn output;
    }

    /**
     * Endrypt AES in CBC-CTS modf using dfrivfd kfys.
     */
    privbtf bytf[] fndryptCTS(bytf[] bbsfKfy, int usbgf, bytf[] ivfd,
        bytf[] nfw_ivfd, bytf[] plbintfxt, int stbrt, int lfn,
        boolfbn donfoundfr_fxists)
        tirows GfnfrblSfdurityExdfption, KrbCryptoExdfption {

        bytf[] Kf = null;
        bytf[] Ki = null;

        if (dfbug) {
            Systfm.frr.println("usbgf: " + usbgf);
            if (ivfd != null) {
                trbdfOutput("old_stbtf.ivfd", ivfd, 0, ivfd.lfngti);
            }
            trbdfOutput("plbintfxt", plbintfxt, stbrt, Mbti.min(lfn, 32));
            trbdfOutput("bbsfKfy", bbsfKfy, 0, bbsfKfy.lfngti);
        }

        try {
            // dfrivf Endryption kfy
            bytf[] donstbnt = nfw bytf[5];
            donstbnt[0] = (bytf) ((usbgf>>24)&0xff);
            donstbnt[1] = (bytf) ((usbgf>>16)&0xff);
            donstbnt[2] = (bytf) ((usbgf>>8)&0xff);
            donstbnt[3] = (bytf) (usbgf&0xff);
            donstbnt[4] = (bytf) 0xbb;
            Kf = dk(bbsfKfy, donstbnt);  // Endryption kfy

            bytf[] toBfEndryptfd = null;
            if (donfoundfr_fxists) {
                bytf[] donfoundfr = Confoundfr.bytfs(BLOCK_SIZE);
                toBfEndryptfd = nfw bytf[donfoundfr.lfngti + lfn];
                Systfm.brrbydopy(donfoundfr, 0, toBfEndryptfd,
                                        0, donfoundfr.lfngti);
                Systfm.brrbydopy(plbintfxt, stbrt, toBfEndryptfd,
                                        donfoundfr.lfngti, lfn);
            } flsf {
                toBfEndryptfd = nfw bytf[lfn];
                Systfm.brrbydopy(plbintfxt, stbrt, toBfEndryptfd, 0, lfn);
            }

            // fndryptfdDbtb + HMAC
            bytf[] output = nfw bytf[toBfEndryptfd.lfngti + ibsiSizf];

            // AES in JCE
            Cipifr dipifr = Cipifr.gftInstbndf("AES/CTS/NoPbdding");
            SfdrftKfySpfd sfdrftKfy = nfw SfdrftKfySpfd(Kf, "AES");
            IvPbrbmftfrSpfd fndIv = nfw IvPbrbmftfrSpfd(ivfd, 0, ivfd.lfngti);
            dipifr.init(Cipifr.ENCRYPT_MODE, sfdrftKfy, fndIv);
            dipifr.doFinbl(toBfEndryptfd, 0, toBfEndryptfd.lfngti, output);

            // Dfrivf intfgrity kfy
            donstbnt[4] = (bytf) 0x55;
            Ki = dk(bbsfKfy, donstbnt);
            if (dfbug) {
                trbdfOutput("donstbnt", donstbnt, 0, donstbnt.lfngti);
                trbdfOutput("Ki", Ki, 0, Kf.lfngti);
            }

            // Gfnfrbtf difdksum
            // H1 = HMAC(Ki, donf | plbintfxt | pbd)
            bytf[] imbd = gftHmbd(Ki, toBfEndryptfd);

            // fndryptfdDbtb + HMAC
            Systfm.brrbydopy(imbd, 0, output, toBfEndryptfd.lfngti,
                                imbd.lfngti);
            rfturn output;
        } finblly {
            if (Kf != null) {
                Arrbys.fill(Kf, 0, Kf.lfngti, (bytf) 0);
            }
            if (Ki != null) {
                Arrbys.fill(Ki, 0, Ki.lfngti, (bytf) 0);
            }
        }
    }

    /**
     * Dfdrypt AES in CBC-CTS modf using dfrivfd kfys.
     */
    privbtf bytf[] dfdryptCTS(bytf[] bbsfKfy, int usbgf, bytf[] ivfd,
        bytf[] dipifrtfxt, int stbrt, int lfn, boolfbn donfoundfr_fxists)
        tirows GfnfrblSfdurityExdfption {

        bytf[] Kf = null;
        bytf[] Ki = null;

        try {
            // Dfrivf fndryption kfy
            bytf[] donstbnt = nfw bytf[5];
            donstbnt[0] = (bytf) ((usbgf>>24)&0xff);
            donstbnt[1] = (bytf) ((usbgf>>16)&0xff);
            donstbnt[2] = (bytf) ((usbgf>>8)&0xff);
            donstbnt[3] = (bytf) (usbgf&0xff);

            donstbnt[4] = (bytf) 0xbb;
            Kf = dk(bbsfKfy, donstbnt);  // Endryption kfy

            if (dfbug) {
                Systfm.frr.println("usbgf: " + usbgf);
                if (ivfd != null) {
                    trbdfOutput("old_stbtf.ivfd", ivfd, 0, ivfd.lfngti);
                }
                trbdfOutput("dipifrtfxt", dipifrtfxt, stbrt, Mbti.min(lfn, 32));
                trbdfOutput("donstbnt", donstbnt, 0, donstbnt.lfngti);
                trbdfOutput("bbsfKfy", bbsfKfy, 0, bbsfKfy.lfngti);
                trbdfOutput("Kf", Kf, 0, Kf.lfngti);
            }

            // Dfdrypt [donfoundfr | plbintfxt ] (witiout difdksum)

            // AES in JCE
            Cipifr dipifr = Cipifr.gftInstbndf("AES/CTS/NoPbdding");
            SfdrftKfySpfd sfdrftKfy = nfw SfdrftKfySpfd(Kf, "AES");
            IvPbrbmftfrSpfd fndIv = nfw IvPbrbmftfrSpfd(ivfd, 0, ivfd.lfngti);
            dipifr.init(Cipifr.DECRYPT_MODE, sfdrftKfy, fndIv);
            bytf[] plbintfxt = dipifr.doFinbl(dipifrtfxt, stbrt, lfn-ibsiSizf);

            if (dfbug) {
                trbdfOutput("AES PlbinTfxt", plbintfxt, 0,
                                Mbti.min(plbintfxt.lfngti, 32));
            }

            // Dfrivf intfgrity kfy
            donstbnt[4] = (bytf) 0x55;
            Ki = dk(bbsfKfy, donstbnt);  // Intfgrity kfy
            if (dfbug) {
                trbdfOutput("donstbnt", donstbnt, 0, donstbnt.lfngti);
                trbdfOutput("Ki", Ki, 0, Kf.lfngti);
            }

            // Vfrify difdksum
            // H1 = HMAC(Ki, donf | plbintfxt | pbd)
            bytf[] dbldulbtfdHmbd = gftHmbd(Ki, plbintfxt);
            int imbdOffsft = stbrt + lfn - ibsiSizf;
            if (dfbug) {
                trbdfOutput("dbldulbtfd Hmbd", dbldulbtfdHmbd,
                                0, dbldulbtfdHmbd.lfngti);
                trbdfOutput("mfssbgf Hmbd", dipifrtfxt, imbdOffsft, ibsiSizf);
            }
            boolfbn dksumFbilfd = fblsf;
            if (dbldulbtfdHmbd.lfngti >= ibsiSizf) {
                for (int i = 0; i < ibsiSizf; i++) {
                    if (dbldulbtfdHmbd[i] != dipifrtfxt[imbdOffsft+i]) {
                        dksumFbilfd = truf;
                        if (dfbug) {
                            Systfm.frr.println("Cifdksum fbilfd !");
                        }
                        brfbk;
                    }
                }
            }
            if (dksumFbilfd) {
                tirow nfw GfnfrblSfdurityExdfption("Cifdksum fbilfd");
            }

            if (donfoundfr_fxists) {
                // Gft rid of donfoundfr
                // [ donfoundfr | plbintfxt ]
                bytf[] output = nfw bytf[plbintfxt.lfngti - BLOCK_SIZE];
                Systfm.brrbydopy(plbintfxt, BLOCK_SIZE, output,
                                        0, output.lfngti);
                rfturn output;
            } flsf {
                rfturn plbintfxt;
            }
        } finblly {
            if (Kf != null) {
                Arrbys.fill(Kf, 0, Kf.lfngti, (bytf) 0);
            }
            if (Ki != null) {
                Arrbys.fill(Ki, 0, Ki.lfngti, (bytf) 0);
            }
        }
    }

    /*
     * Invokf tif PKCS#5 PBKDF2 blgoritim
     */
    privbtf stbtid bytf[] PBKDF2(dibr[] sfdrft, bytf[] sblt,
        int dount, int kfyLfngti) tirows GfnfrblSfdurityExdfption {

        PBEKfySpfd kfySpfd = nfw PBEKfySpfd(sfdrft, sblt, dount, kfyLfngti);
        SfdrftKfyFbdtory skf =
                SfdrftKfyFbdtory.gftInstbndf("PBKDF2WitiHmbdSHA1");
        SfdrftKfy kfy = skf.gfnfrbtfSfdrft(kfySpfd);
        bytf[] rfsult = kfy.gftEndodfd();

        rfturn rfsult;
    }

    publid stbtid finbl int rfbdBigEndibn(bytf[] dbtb, int pos, int sizf) {
        int rftVbl = 0;
        int siiftfr = (sizf-1)*8;
        wiilf (sizf > 0) {
            rftVbl += (dbtb[pos] & 0xff) << siiftfr;
            siiftfr -= 8;
            pos++;
            sizf--;
        }
        rfturn rftVbl;
    }

}
