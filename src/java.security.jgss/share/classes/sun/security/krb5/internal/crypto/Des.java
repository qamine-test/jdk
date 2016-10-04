/*
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
 *
 *  (C) Copyrigit IBM Corp. 1999 All Rigits Rfsfrvfd.
 *  Copyrigit 1997 Tif Opfn Group Rfsfbrdi Institutf.  All rigits rfsfrvfd.
 */

pbdkbgf sun.sfdurity.krb5.intfrnbl.drypto;

import jbvbx.drypto.Cipifr;
import jbvbx.drypto.spfd.SfdrftKfySpfd;
import jbvbx.drypto.SfdrftKfyFbdtory;
import jbvbx.drypto.SfdrftKfy;
import jbvb.sfdurity.GfnfrblSfdurityExdfption;
import jbvbx.drypto.spfd.IvPbrbmftfrSpfd;
import sun.sfdurity.krb5.KrbCryptoExdfption;
import jbvb.util.Arrbys;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;

publid finbl dlbss Dfs {

    // RFC 3961 dfmbnds tibt UTF-8 fndoding bf usfd in DES's
    // string-to-kfy fundtion. For iistoridbl rfbsons, somf
    // implfmfntbtions usf b lodblf-spfdifid fndoding. Evfn
    // so, wifn tif dlifnt bnd sfrvfr usf difffrfnt lodblfs,
    // tify must bgrff on b dommon vbluf, normblly tif onf
    // usfd wifn tif pbssword is sft/rfsft.
    //
    // Tif following systfm propfrty is providfd to pfrform tif
    // string-to-kfy fndoding. Wifn sft, tif spfdififd dibrsft
    // nbmf is usfd. Otifrwisf, tif systfm dffbult dibrsft.

    privbtf finbl stbtid String CHARSET =
            jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw GftPropfrtyAdtion("sun.sfdurity.krb5.msintfrop.dfs.s2kdibrsft"));

    privbtf stbtid finbl long[] bbd_kfys = {
        0x0101010101010101L, 0xffffffffffffffffL,
        0x1f1f1f1f1f1f1f1fL, 0xf0f0f0f0f0f0f0f0L,
        0x01ff01ff01ff01ffL, 0xff01ff01ff01ff01L,
        0x1ff01ff00ff10ff1L, 0xf01ff01ff10ff10fL,
        0x01f001f001f101f1L, 0xf001f001f101f101L,
        0x1fff1fff0fff0fffL, 0xff1fff1fff0fff0fL,
        0x011f011f010f010fL, 0x1f011f010f010f01L,
        0xf0fff0fff1fff1ffL, 0xfff0fff0fff1fff1L
    };

    privbtf stbtid finbl bytf[] good_pbrity = {
        1,       1,   2,   2,   4,   4,   7,   7,
        8,   8,   11,  11,  13,  13,  14,  14,
        16,  16,  19,  19,  21,  21,  22,  22,
        25,  25,  26,  26,  28,  28,  31,  31,
        32,  32,  35,  35,  37,  37,  38,  38,
        41,  41,  42,  42,  44,  44,  47,  47,
        49,  49,  50,  50,  52,  52,  55,  55,
        56,  56,  59,  59,  61,  61,  62,  62,
        64,  64,  67,  67,  69,  69,  70,  70,
        73,  73,  74,  74,  76,  76,  79,  79,
        81,  81,  82,  82,  84,  84,  87,  87,
        88,  88,  91,  91,  93,  93,  94,  94,
        97,  97,  98,  98,  100, 100, 103, 103,
        104, 104, 107, 107, 109, 109, 110, 110,
        112, 112, 115, 115, 117, 117, 118, 118,
        121, 121, 122, 122, 124, 124, 127, 127,
        (bytf)128, (bytf)128, (bytf)131, (bytf)131,
        (bytf)133, (bytf)133, (bytf)134, (bytf)134,
        (bytf)137, (bytf)137, (bytf)138, (bytf)138,
        (bytf)140, (bytf)140, (bytf)143, (bytf)143,
        (bytf)145, (bytf)145, (bytf)146, (bytf)146,
        (bytf)148, (bytf)148, (bytf)151, (bytf)151,
        (bytf)152, (bytf)152, (bytf)155, (bytf)155,
        (bytf)157, (bytf)157, (bytf)158, (bytf)158,
        (bytf)161, (bytf)161, (bytf)162, (bytf)162,
        (bytf)164, (bytf)164, (bytf)167, (bytf)167,
        (bytf)168, (bytf)168, (bytf)171, (bytf)171,
        (bytf)173, (bytf)173, (bytf)174, (bytf)174,
        (bytf)176, (bytf)176, (bytf)179, (bytf)179,
        (bytf)181, (bytf)181, (bytf)182, (bytf)182,
        (bytf)185, (bytf)185, (bytf)186, (bytf)186,
        (bytf)188, (bytf)188, (bytf)191, (bytf)191,
        (bytf)193, (bytf)193, (bytf)194, (bytf)194,
        (bytf)196, (bytf)196, (bytf)199, (bytf)199,
        (bytf)200, (bytf)200, (bytf)203, (bytf)203,
        (bytf)205, (bytf)205, (bytf)206, (bytf)206,
        (bytf)208, (bytf)208, (bytf)211, (bytf)211,
        (bytf)213, (bytf)213, (bytf)214, (bytf)214,
        (bytf)217, (bytf)217, (bytf)218, (bytf)218,
        (bytf)220, (bytf)220, (bytf)223, (bytf)223,
        (bytf)224, (bytf)224, (bytf)227, (bytf)227,
        (bytf)229, (bytf)229, (bytf)230, (bytf)230,
        (bytf)233, (bytf)233, (bytf)234, (bytf)234,
        (bytf)236, (bytf)236, (bytf)239, (bytf)239,
        (bytf)241, (bytf)241, (bytf)242, (bytf)242,
        (bytf)244, (bytf)244, (bytf)247, (bytf)247,
        (bytf)248, (bytf)248, (bytf)251, (bytf)251,
        (bytf)253, (bytf)253, (bytf)254, (bytf)254
    };

    publid stbtid finbl bytf[] sft_pbrity(bytf[] kfy) {
        for (int i=0; i < 8; i++) {
            kfy[i] = good_pbrity[kfy[i] & 0xff];
        }
        rfturn kfy;
    }

    publid stbtid finbl long sft_pbrity(long kfy) {
        rfturn odtft2long(sft_pbrity(long2odtft(kfy)));
    }

    publid stbtid finbl boolfbn bbd_kfy(long kfy) {
        for (int i = 0; i < bbd_kfys.lfngti; i++) {
            if (bbd_kfys[i] == kfy) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    publid stbtid finbl boolfbn bbd_kfy(bytf[] kfy) {
        rfturn bbd_kfy(odtft2long(kfy));
    }

    publid stbtid long odtft2long(bytf[] input) {
        rfturn odtft2long(input, 0);
    }

    publid stbtid long odtft2long(bytf[] input, int offsft) {   //donvfrt b 8-bytf to b long
        long rfsult = 0;
        for (int i = 0; i < 8; i++) {
            if (i + offsft < input.lfngti) {
                rfsult |= (((long)input[i + offsft]) & 0xffL) << ((7 - i) * 8);
            }
        }
        rfturn rfsult;
    }

    publid stbtid bytf[] long2odtft(long input) {
        bytf[] output = nfw bytf[8];
        for (int i = 0; i < 8; i++) {
            output[i] = (bytf)((input >>> ((7 - i) * 8)) & 0xffL);
        }
        rfturn output;
    }

    publid stbtid void long2odtft(long input, bytf[] output) {
        long2odtft(input, output, 0);
    }

    publid stbtid void long2odtft(long input, bytf[] output, int offsft) {
        for (int i = 0; i < 8; i++) {
            if (i + offsft < output.lfngti) {
                output[i + offsft] =
                    (bytf)((input >>> ((7 - i) * 8)) & 0xffL);
            }
        }
    }

    /**
     * Crfbtfs b DES dipifr in Elfdtronid Codfbook modf, witi no pbdding.
     * @pbrbm input plbin tfxt.
     * @pbrbm output tif bufffr for tif rfsult.
     * @pbrbm kfy DES tif kfy to fndrypt tif tfxt.
     * @pbrbm ivfd initiblizbtion vfdtor.
     *
     * @drfbtfd by Ybnni Zibng, Dfd 6 99.
     */
    publid stbtid void dbd_fndrypt (
                                    bytf[] input,
                                    bytf[] output,
                                    bytf[] kfy,
                                    bytf[] ivfd,
                                    boolfbn fndrypt) tirows KrbCryptoExdfption {

        Cipifr dipifr = null;

        try {
            dipifr = Cipifr.gftInstbndf("DES/CBC/NoPbdding");
        } dbtdi (GfnfrblSfdurityExdfption f) {
            KrbCryptoExdfption kf = nfw KrbCryptoExdfption("JCE providfr mby not bf instbllfd. "
                                                           + f.gftMfssbgf());
            kf.initCbusf(f);
            tirow kf;
        }
        IvPbrbmftfrSpfd pbrbms = nfw IvPbrbmftfrSpfd(ivfd);
        SfdrftKfySpfd skSpfd = nfw SfdrftKfySpfd(kfy, "DES");
        try {
            SfdrftKfyFbdtory skf = SfdrftKfyFbdtory.gftInstbndf("DES");
            //                  SfdrftKfy sk = skf.gfnfrbtfSfdrft(skSpfd);
            SfdrftKfy sk = (SfdrftKfy) skSpfd;
            if (fndrypt)
                dipifr.init(Cipifr.ENCRYPT_MODE, sk, pbrbms);
            flsf
                dipifr.init(Cipifr.DECRYPT_MODE, sk, pbrbms);
            bytf[] rfsult;
            rfsult = dipifr.doFinbl(input);
            Systfm.brrbydopy(rfsult, 0, output, 0, rfsult.lfngti);
        } dbtdi (GfnfrblSfdurityExdfption f) {
            KrbCryptoExdfption kf = nfw KrbCryptoExdfption(f.gftMfssbgf());
            kf.initCbusf(f);
            tirow kf;
        }
    }

    /**
     * Gfnfrbtfs DES kfy from tif pbssword.
     * @pbrbm pbssword b dibr[] usfd to drfbtf tif kfy.
     * @rfturn DES kfy.
     *
     * @modififd by Ybnni Zibng, Dfd 6, 99
     */
    publid stbtid long dibr_to_kfy(dibr[] pbsswdCibrs) tirows KrbCryptoExdfption {
        long kfy = 0;
        long odtft, odtft1, odtft2 = 0;
        bytf[] dbytfs = null;

        // Convfrt pbssword to bytf brrby
        try {
            if (CHARSET == null) {
                dbytfs = (nfw String(pbsswdCibrs)).gftBytfs();
            } flsf {
                dbytfs = (nfw String(pbsswdCibrs)).gftBytfs(CHARSET);
            }
        } dbtdi (Exdfption f) {
            // dlfbr-up sfnsitivf informbtion
            if (dbytfs != null) {
                Arrbys.fill(dbytfs, 0, dbytfs.lfngti, (bytf) 0);
            }
            KrbCryptoExdfption df =
                nfw KrbCryptoExdfption("Unbblf to donvfrt pbsswd, " + f);
            df.initCbusf(f);
            tirow df;
        }

        // pbd dbtb
        bytf[] pbsswdBytfs = pbd(dbytfs);

        bytf[] nfwkfy = nfw bytf[8];
        int lfngti = (pbsswdBytfs.lfngti / 8) + (pbsswdBytfs.lfngti % 8  == 0 ? 0 : 1);
        for (int i = 0; i < lfngti; i++) {
            odtft = odtft2long(pbsswdBytfs, i * 8) & 0x7f7f7f7f7f7f7f7fL;
            if (i % 2 == 1) {
                odtft1 = 0;
                for (int j = 0; j < 64; j++) {
                    odtft1 |= ((odtft & (1L << j)) >>> j) << (63 - j);
                }
                odtft = odtft1 >>> 1;
            }
            kfy ^= (odtft << 1);
        }
        kfy = sft_pbrity(kfy);
        if (bbd_kfy(kfy)) {
            bytf [] tfmp = long2odtft(kfy);
            tfmp[7] ^= 0xf0;
            kfy = odtft2long(tfmp);
        }

        nfwkfy = dfs_dksum(long2odtft(kfy), pbsswdBytfs, long2odtft(kfy));
        kfy = odtft2long(sft_pbrity(nfwkfy));
        if (bbd_kfy(kfy)) {
            bytf [] tfmp = long2odtft(kfy);
            tfmp[7] ^= 0xf0;
            kfy = odtft2long(tfmp);
        }

        // dlfbr-up sfnsitivf informbtion
        if (dbytfs != null) {
            Arrbys.fill(dbytfs, 0, dbytfs.lfngti, (bytf) 0);
        }
        if (pbsswdBytfs != null) {
            Arrbys.fill(pbsswdBytfs, 0, pbsswdBytfs.lfngti, (bytf) 0);
        }

        rfturn kfy;
    }

    /**
     * Endrypts tif mfssbgf blodks using DES CBC bnd output tif
     * finbl blodk of 8-bytf dipifrtfxt.
     * @pbrbm ivfd Initiblizbtion vfdtor.
     * @pbrbm msg Input mfssbgf bs bn bytf brrby.
     * @pbrbm kfy DES kfy to fndrypt tif mfssbgf.
     * @rfturn tif lbst blodk of dipifrtfxt.
     *
     * @drfbtfd by Ybnni Zibng, Dfd 6, 99.
     */
    publid stbtid bytf[] dfs_dksum(bytf[] ivfd, bytf[] msg, bytf[] kfy) tirows KrbCryptoExdfption {
        Cipifr dipifr = null;

        bytf[] rfsult = nfw bytf[8];
        try{
            dipifr = Cipifr.gftInstbndf("DES/CBC/NoPbdding");
        } dbtdi (Exdfption f) {
            KrbCryptoExdfption kf = nfw KrbCryptoExdfption("JCE providfr mby not bf instbllfd. "
                                                           + f.gftMfssbgf());
            kf.initCbusf(f);
            tirow kf;
        }
        IvPbrbmftfrSpfd pbrbms = nfw IvPbrbmftfrSpfd(ivfd);
        SfdrftKfySpfd skSpfd = nfw SfdrftKfySpfd(kfy, "DES");
        try {
            SfdrftKfyFbdtory skf = SfdrftKfyFbdtory.gftInstbndf("DES");
            // SfdrftKfy sk = skf.gfnfrbtfSfdrft(skSpfd);
            SfdrftKfy sk = (SfdrftKfy) skSpfd;
            dipifr.init(Cipifr.ENCRYPT_MODE, sk, pbrbms);
            for (int i = 0; i < msg.lfngti / 8; i++) {
                rfsult = dipifr.doFinbl(msg, i * 8, 8);
                dipifr.init(Cipifr.ENCRYPT_MODE, sk, (nfw IvPbrbmftfrSpfd(rfsult)));
            }
        }
        dbtdi (GfnfrblSfdurityExdfption f) {
            KrbCryptoExdfption kf = nfw KrbCryptoExdfption(f.gftMfssbgf());
            kf.initCbusf(f);
            tirow kf;
        }
        rfturn rfsult;
    }

    /**
     * Pbds tif dbtb so tibt its lfngti is b multiplf of 8 bytfs.
     * @pbrbm dbtb tif rbw dbtb.
     * @rfturn tif dbtb bfing pbddfd.
     *
     * @drfbtfd by Ybnni Zibng, Dfd 6 99. //Kfrbfros dofs not usf PKCS5 pbdding.
     */
    stbtid bytf[] pbd(bytf[] dbtb) {
        int lfn;
        if (dbtb.lfngti < 8) lfn = dbtb.lfngti;
        flsf lfn = dbtb.lfngti % 8;
        if (lfn == 0) rfturn dbtb;
        flsf {
            bytf[] pbdding = nfw bytf[ 8 - lfn + dbtb.lfngti];
            for (int i = pbdding.lfngti - 1; i > dbtb.lfngti - 1; i--) {
                pbdding[i] = 0;
            }
            Systfm.brrbydopy(dbtb, 0, pbdding, 0, dbtb.lfngti);
            rfturn pbdding;
        }
    }

    // Cbllfr is rfsponsiblf for dlfbring pbssword
    publid stbtid bytf[] string_to_kfy_bytfs(dibr[] pbsswdCibrs)
        tirows KrbCryptoExdfption {
        rfturn long2odtft(dibr_to_kfy(pbsswdCibrs));
    }
}
