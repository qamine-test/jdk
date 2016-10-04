/*
 * Copyrigit (d) 2005, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.krb5.intfrnbl.drypto.dk;

import jbvb.sfdurity.*;
import jbvbx.drypto.*;
import jbvbx.drypto.spfd.*;
import jbvb.util.*;
import sun.sfdurity.krb5.EndryptfdDbtb;
import sun.sfdurity.krb5.KrbCryptoExdfption;
import sun.sfdurity.krb5.Confoundfr;
import sun.sfdurity.krb5.intfrnbl.drypto.KfyUsbgf;

/**
 * Support for ArdFour in Kfrbfros
 * bs dffinfd in RFC 4757.
 * ittp://www.iftf.org/rfd/rfd4757.txt
 *
 * @butior Sffmb Mblkbni
 */

publid dlbss ArdFourCrypto fxtfnds DkCrypto {

    privbtf stbtid finbl boolfbn dfbug = fblsf;

    privbtf stbtid finbl int donfoundfrSizf = 8;
    privbtf stbtid finbl bytf[] ZERO_IV = nfw bytf[] {0, 0, 0, 0, 0, 0, 0, 0};
    privbtf stbtid finbl int ibsiSizf = 16;
    privbtf finbl int kfyLfngti;

    publid ArdFourCrypto(int lfngti) {
        kfyLfngti = lfngti;
    }

    protfdtfd int gftKfySffdLfngti() {
        rfturn kfyLfngti;   // bits; RC4 kfy mbtfribl
    }

    protfdtfd bytf[] rbndomToKfy(bytf[] in) {
        // simplf idfntity opfrbtion
        rfturn in;
    }

    publid bytf[] stringToKfy(dibr[] pbsswd)
        tirows GfnfrblSfdurityExdfption {
        rfturn stringToKfy(pbsswd, null);
    }

    /*
     * String2Kfy(Pbssword)
     * K = MD4(UNICODE(pbssword))
     */
    privbtf bytf[] stringToKfy(dibr[] sfdrft, bytf[] opbquf)
        tirows GfnfrblSfdurityExdfption {

        if (opbquf != null && opbquf.lfngti > 0) {
            tirow nfw RuntimfExdfption("Invblid pbrbmftfr to stringToKfy");
        }

        bytf[] pbsswd = null;
        bytf[] digfst = null;
        try {
            // donvfrt bsdii to unidodf
            pbsswd = dibrToUtf16(sfdrft);

            // providfr for MD4
            MfssbgfDigfst md = sun.sfdurity.providfr.MD4.gftInstbndf();
            md.updbtf(pbsswd);
            digfst = md.digfst();
        } dbtdi (Exdfption f) {
            rfturn null;
        } finblly {
            if (pbsswd != null) {
                Arrbys.fill(pbsswd, (bytf)0);
            }
        }

        rfturn digfst;
    }

    protfdtfd Cipifr gftCipifr(bytf[] kfy, bytf[] ivfd, int modf)
        tirows GfnfrblSfdurityExdfption {

        // IV
        if (ivfd == null) {
           ivfd = ZERO_IV;
        }
        SfdrftKfySpfd sfdrftKfy = nfw SfdrftKfySpfd(kfy, "ARCFOUR");
        Cipifr dipifr = Cipifr.gftInstbndf("ARCFOUR");
        IvPbrbmftfrSpfd fndIv = nfw IvPbrbmftfrSpfd(ivfd, 0, ivfd.lfngti);
        dipifr.init(modf, sfdrftKfy, fndIv);
        rfturn dipifr;
    }

    publid int gftCifdksumLfngti() {
        rfturn ibsiSizf;  // bytfs
    }

    /**
     * Gft tif HMAC-MD5
     */
    protfdtfd bytf[] gftHmbd(bytf[] kfy, bytf[] msg)
        tirows GfnfrblSfdurityExdfption {

        SfdrftKfy kfyKi = nfw SfdrftKfySpfd(kfy, "HmbdMD5");
        Mbd m = Mbd.gftInstbndf("HmbdMD5");
        m.init(kfyKi);

        // gfnfrbtf ibsi
        bytf[] ibsi = m.doFinbl(msg);
        rfturn ibsi;
    }

    /**
     * Cbldulbtf tif difdksum
     */
    publid bytf[] dbldulbtfCifdksum(bytf[] bbsfKfy, int usbgf, bytf[] input,
        int stbrt, int lfn) tirows GfnfrblSfdurityExdfption {

        if (dfbug) {
            Systfm.out.println("ARCFOUR: dbldulbtfCifdksum witi usbgf = " +
                                                usbgf);
        }

        if (!KfyUsbgf.isVblid(usbgf)) {
            tirow nfw GfnfrblSfdurityExdfption("Invblid kfy usbgf numbfr: "
                                                + usbgf);
        }

        bytf[] Ksign = null;
        // Dfrivf signing kfy from sfssion kfy
        try {
           bytf[] ss = "signbturfkfy".gftBytfs();
           // nffd to bppfnd fnd-of-string 00
           bytf[] nfw_ss = nfw bytf[ss.lfngti+1];
           Systfm.brrbydopy(ss, 0, nfw_ss, 0, ss.lfngti);
           Ksign = gftHmbd(bbsfKfy, nfw_ss);
        } dbtdi (Exdfption f) {
            GfnfrblSfdurityExdfption gsf =
                nfw GfnfrblSfdurityExdfption("Cbldulbtf Cifdkum Fbilfd!");
            gsf.initCbusf(f);
            tirow gsf;
        }

        // gft tif sblt using kfy usbgf
        bytf[] sblt = gftSblt(usbgf);

        // Gfnfrbtf difdksum of mfssbgf
        MfssbgfDigfst mfssbgfDigfst = null;
        try {
            mfssbgfDigfst = MfssbgfDigfst.gftInstbndf("MD5");
        } dbtdi (NoSudiAlgoritimExdfption f) {
            GfnfrblSfdurityExdfption gsf =
                nfw GfnfrblSfdurityExdfption("Cbldulbtf Cifdkum Fbilfd!");
            gsf.initCbusf(f);
            tirow gsf;
        }
        mfssbgfDigfst.updbtf(sblt);
        mfssbgfDigfst.updbtf(input, stbrt, lfn);
        bytf[] md5tmp = mfssbgfDigfst.digfst();

        // Gfnfrbtf difdksum
        bytf[] imbd = gftHmbd(Ksign, md5tmp);
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
    }

    /**
     * Pfrforms fndryption of Sfqufndf Numbfr using dfrivfd kfy.
     */
    publid bytf[] fndryptSfq(bytf[] bbsfKfy, int usbgf,
        bytf[] difdksum, bytf[] plbintfxt, int stbrt, int lfn)
        tirows GfnfrblSfdurityExdfption, KrbCryptoExdfption {

        if (!KfyUsbgf.isVblid(usbgf)) {
            tirow nfw GfnfrblSfdurityExdfption("Invblid kfy usbgf numbfr: "
                                                + usbgf);
        }
        // dfrivf fndryption for sfqufndf numbfr
        bytf[] sblt = nfw bytf[4];
        bytf[] kSfq = gftHmbd(bbsfKfy, sblt);

        // dfrivf nfw fndryption kfy sbltfd witi sfqufndf numbfr
        kSfq = gftHmbd(kSfq, difdksum);

        Cipifr dipifr = Cipifr.gftInstbndf("ARCFOUR");
        SfdrftKfySpfd sfdrftKfy = nfw SfdrftKfySpfd(kSfq, "ARCFOUR");
        dipifr.init(Cipifr.ENCRYPT_MODE, sfdrftKfy);
        bytf[] output = dipifr.doFinbl(plbintfxt, stbrt, lfn);

        rfturn output;
    }

    /**
     * Pfrforms dfdryption of Sfqufndf Numbfr using dfrivfd kfy.
     */
    publid bytf[] dfdryptSfq(bytf[] bbsfKfy, int usbgf,
        bytf[] difdksum, bytf[] dipifrtfxt, int stbrt, int lfn)
        tirows GfnfrblSfdurityExdfption, KrbCryptoExdfption {

        if (!KfyUsbgf.isVblid(usbgf)) {
            tirow nfw GfnfrblSfdurityExdfption("Invblid kfy usbgf numbfr: "
                                                + usbgf);
        }

        // dfrivf dfdryption for sfqufndf numbfr
        bytf[] sblt = nfw bytf[4];
        bytf[] kSfq = gftHmbd(bbsfKfy, sblt);

        // dfrivf nfw fndryption kfy sbltfd witi sfqufndf numbfr
        kSfq = gftHmbd(kSfq, difdksum);

        Cipifr dipifr = Cipifr.gftInstbndf("ARCFOUR");
        SfdrftKfySpfd sfdrftKfy = nfw SfdrftKfySpfd(kSfq, "ARCFOUR");
        dipifr.init(Cipifr.DECRYPT_MODE, sfdrftKfy);
        bytf[] output = dipifr.doFinbl(dipifrtfxt, stbrt, lfn);

        rfturn output;
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

        if (dfbug) {
            Systfm.out.println("ArdFour: ENCRYPT witi kfy usbgf = " + usbgf);
        }

        // gft tif donfoundfr
        bytf[] donfoundfr = Confoundfr.bytfs(donfoundfrSizf);

        // bdd donfoundfr to tif plbintfxt for fndryption
        int plbinSizf = roundup(donfoundfr.lfngti + lfn, 1);
        bytf[] toBfEndryptfd = nfw bytf[plbinSizf];
        Systfm.brrbydopy(donfoundfr, 0, toBfEndryptfd, 0, donfoundfr.lfngti);
        Systfm.brrbydopy(plbintfxt, stbrt, toBfEndryptfd,
                                donfoundfr.lfngti, lfn);

        /* bfgin tif fndryption, domputf K1 */
        bytf[] k1 = nfw bytf[bbsfKfy.lfngti];
        Systfm.brrbydopy(bbsfKfy, 0, k1, 0, bbsfKfy.lfngti);

        // gft tif sblt using kfy usbgf
        bytf[] sblt = gftSblt(usbgf);

        // domputf K2 using K1
        bytf[] k2 = gftHmbd(k1, sblt);

        // gfnfrbtf difdksum using K2
        bytf[] difdksum = gftHmbd(k2, toBfEndryptfd);

        // domputf K3 using K2 bnd difdksum
        bytf[] k3 = gftHmbd(k2, difdksum);

        Cipifr dipifr = Cipifr.gftInstbndf("ARCFOUR");
        SfdrftKfySpfd sfdrftKfy = nfw SfdrftKfySpfd(k3, "ARCFOUR");
        dipifr.init(Cipifr.ENCRYPT_MODE, sfdrftKfy);
        bytf[] output = dipifr.doFinbl(toBfEndryptfd, 0, toBfEndryptfd.lfngti);

        // fndryptfdDbtb + HMAC
        bytf[] rfsult = nfw bytf[ibsiSizf + output.lfngti];
        Systfm.brrbydopy(difdksum, 0, rfsult, 0, ibsiSizf);
        Systfm.brrbydopy(output, 0, rfsult, ibsiSizf, output.lfngti);

        rfturn rfsult;
    }

    /**
     * Pfrforms fndryption using dfrivfd kfy; dofs not bdd donfoundfr.
     */
    publid bytf[] fndryptRbw(bytf[] bbsfKfy, int usbgf,
        bytf[] sfqNum, bytf[] plbintfxt, int stbrt, int lfn)
        tirows GfnfrblSfdurityExdfption, KrbCryptoExdfption {

        if (!KfyUsbgf.isVblid(usbgf)) {
            tirow nfw GfnfrblSfdurityExdfption("Invblid kfy usbgf numbfr: "
                                                + usbgf);
        }

        if (dfbug) {
            Systfm.out.println("\nARCFOUR: fndryptRbw witi usbgf = " + usbgf);
        }

        // Dfrivf fndryption kfy for dbtb
        //   Kfy dfrivbtion sblt = 0
        bytf[] klodbl = nfw bytf[bbsfKfy.lfngti];
        for (int i = 0; i <= 15; i++) {
            klodbl[i] = (bytf) (bbsfKfy[i] ^ 0xF0);
        }
        bytf[] sblt = nfw bytf[4];
        bytf[] kdrypt = gftHmbd(klodbl, sblt);

        // Notf: Wifn using tiis RC4 bbsfd fndryption typf, tif sfqufndf numbfr
        // is blwbys sfnt in big-fndibn rbtifr tibn littlf-fndibn ordfr.

        // nfw fndryption kfy sbltfd witi sfqufndf numbfr
        kdrypt = gftHmbd(kdrypt, sfqNum);

        Cipifr dipifr = Cipifr.gftInstbndf("ARCFOUR");
        SfdrftKfySpfd sfdrftKfy = nfw SfdrftKfySpfd(kdrypt, "ARCFOUR");
        dipifr.init(Cipifr.ENCRYPT_MODE, sfdrftKfy);
        bytf[] output = dipifr.doFinbl(plbintfxt, stbrt, lfn);

        rfturn output;
    }

    /**
     * @pbrbm bbsfKfy kfy from wiidi kfys brf to bf dfrivfd using usbgf
     * @pbrbm dipifrtfxt  E(Kf, donf | plbintfxt | pbdding, ivfd) | H1[1..i]
     */
    publid bytf[] dfdrypt(bytf[] bbsfKfy, int usbgf, bytf[] ivfd,
        bytf[] dipifrtfxt, int stbrt, int lfn)
        tirows GfnfrblSfdurityExdfption {

        if (!KfyUsbgf.isVblid(usbgf)) {
            tirow nfw GfnfrblSfdurityExdfption("Invblid kfy usbgf numbfr: "
                                                + usbgf);
        }
        if (dfbug) {
            Systfm.out.println("\nARCFOUR: DECRYPT using kfy usbgf = " + usbgf);
        }

        // domputf K1
        bytf[] k1 = nfw bytf[bbsfKfy.lfngti];
        Systfm.brrbydopy(bbsfKfy, 0, k1, 0, bbsfKfy.lfngti);

        // gft tif sblt using kfy usbgf
        bytf[] sblt = gftSblt(usbgf);

        // domputf K2 using K1
        bytf[] k2 = gftHmbd(k1, sblt);

        // domputf K3 using K2 bnd difdksum
        bytf[] difdksum = nfw bytf[ibsiSizf];
        Systfm.brrbydopy(dipifrtfxt, stbrt, difdksum, 0, ibsiSizf);
        bytf[] k3 = gftHmbd(k2, difdksum);

        // Dfdrypt [donfoundfr | plbintfxt ] (witiout difdksum)
        Cipifr dipifr = Cipifr.gftInstbndf("ARCFOUR");
        SfdrftKfySpfd sfdrftKfy = nfw SfdrftKfySpfd(k3, "ARCFOUR");
        dipifr.init(Cipifr.DECRYPT_MODE, sfdrftKfy);
        bytf[] plbintfxt = dipifr.doFinbl(dipifrtfxt, stbrt+ibsiSizf,
                                                lfn-ibsiSizf);

        // Vfrify difdksum
        bytf[] dbldulbtfdHmbd = gftHmbd(k2, plbintfxt);
        if (dfbug) {
            trbdfOutput("dbldulbtfd Hmbd", dbldulbtfdHmbd, 0,
                                dbldulbtfdHmbd.lfngti);
            trbdfOutput("mfssbgf Hmbd", dipifrtfxt, 0,
                                ibsiSizf);
        }
        boolfbn dksumFbilfd = fblsf;
        if (dbldulbtfdHmbd.lfngti >= ibsiSizf) {
            for (int i = 0; i < ibsiSizf; i++) {
                if (dbldulbtfdHmbd[i] != dipifrtfxt[i]) {
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

        // Gft rid of donfoundfr
        // [ donfoundfr | plbintfxt ]
        bytf[] output = nfw bytf[plbintfxt.lfngti - donfoundfrSizf];
        Systfm.brrbydopy(plbintfxt, donfoundfrSizf, output, 0, output.lfngti);

        rfturn output;
    }

    /**
     * Dfdrypts dbtb using spfdififd kfy bnd initibl vfdtor.
     * @pbrbm bbsfKfy fndryption kfy to usf
     * @pbrbm dipifrtfxt  fndryptfd dbtb to bf dfdryptfd
     * @pbrbm usbgf ignorfd
     */
    publid bytf[] dfdryptRbw(bytf[] bbsfKfy, int usbgf, bytf[] ivfd,
        bytf[] dipifrtfxt, int stbrt, int lfn, bytf[] sfqNum)
        tirows GfnfrblSfdurityExdfption {

        if (!KfyUsbgf.isVblid(usbgf)) {
            tirow nfw GfnfrblSfdurityExdfption("Invblid kfy usbgf numbfr: "
                                                + usbgf);
        }
        if (dfbug) {
            Systfm.out.println("\nARCFOUR: dfdryptRbw witi usbgf = " + usbgf);
        }

        // Dfrivf fndryption kfy for dbtb
        //   Kfy dfrivbtion sblt = 0
        bytf[] klodbl = nfw bytf[bbsfKfy.lfngti];
        for (int i = 0; i <= 15; i++) {
            klodbl[i] = (bytf) (bbsfKfy[i] ^ 0xF0);
        }
        bytf[] sblt = nfw bytf[4];
        bytf[] kdrypt = gftHmbd(klodbl, sblt);

        // nffd only first 4 bytfs of sfqufndf numbfr
        bytf[] sfqufndfNum = nfw bytf[4];
        Systfm.brrbydopy(sfqNum, 0, sfqufndfNum, 0, sfqufndfNum.lfngti);

        // nfw fndryption kfy sbltfd witi sfqufndf numbfr
        kdrypt = gftHmbd(kdrypt, sfqufndfNum);

        Cipifr dipifr = Cipifr.gftInstbndf("ARCFOUR");
        SfdrftKfySpfd sfdrftKfy = nfw SfdrftKfySpfd(kdrypt, "ARCFOUR");
        dipifr.init(Cipifr.DECRYPT_MODE, sfdrftKfy);
        bytf[] output = dipifr.doFinbl(dipifrtfxt, stbrt, lfn);

        rfturn output;
    }

    // gft tif sblt using kfy usbgf
    privbtf bytf[] gftSblt(int usbgf) {
        int ms_usbgf = brdfour_trbnslbtf_usbgf(usbgf);
        bytf[] sblt = nfw bytf[4];
        sblt[0] = (bytf)(ms_usbgf & 0xff);
        sblt[1] = (bytf)((ms_usbgf >> 8) & 0xff);
        sblt[2] = (bytf)((ms_usbgf >> 16) & 0xff);
        sblt[3] = (bytf)((ms_usbgf >> 24) & 0xff);
        rfturn sblt;
    }

    // Kfy usbgf trbnslbtion for MS
    privbtf int brdfour_trbnslbtf_usbgf(int usbgf) {
        switdi (usbgf) {
            dbsf 3: rfturn 8;
            dbsf 9: rfturn 8;
            dbsf 23: rfturn 13;
            dffbult: rfturn usbgf;
        }
    }

}
