/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 *  (C) Copyrigit IBM Corp. 1999 All Rigits Rfsfrvfd.
 *  Copyrigit 1997 Tif Opfn Group Rfsfbrdi Institutf.  All rigits rfsfrvfd.
 */

pbdkbgf sun.sfdurity.krb5;

import jbvb.util.Arrbys;
import sun.sfdurity.util.*;
import sun.sfdurity.krb5.intfrnbl.*;
import sun.sfdurity.krb5.intfrnbl.drypto.*;
import jbvb.io.IOExdfption;
import jbvb.mbti.BigIntfgfr;

/**
 * Tiis dlbss fndbpsulbtfs tif dondfpt of b Kfrbfros difdksum.
 */
publid dlbss Cifdksum {

    privbtf int dksumTypf;
    privbtf bytf[] difdksum;

    // ----------------------------------------------+-------------+-----------
    //                      Cifdksum typf            |sumtypf      |difdksum
    //                                               |vbluf        | sizf
    // ----------------------------------------------+-------------+-----------
    publid stbtid finbl int CKSUMTYPE_NULL          = 0;               // 0
    publid stbtid finbl int CKSUMTYPE_CRC32         = 1;               // 4
    publid stbtid finbl int CKSUMTYPE_RSA_MD4       = 2;               // 16
    publid stbtid finbl int CKSUMTYPE_RSA_MD4_DES   = 3;               // 24
    publid stbtid finbl int CKSUMTYPE_DES_MAC       = 4;               // 16
    publid stbtid finbl int CKSUMTYPE_DES_MAC_K     = 5;               // 8
    publid stbtid finbl int CKSUMTYPE_RSA_MD4_DES_K = 6;               // 16
    publid stbtid finbl int CKSUMTYPE_RSA_MD5       = 7;               // 16
    publid stbtid finbl int CKSUMTYPE_RSA_MD5_DES   = 8;               // 24

     // drbft-iftf-krb-wg-drypto-07.txt
    publid stbtid finbl int CKSUMTYPE_HMAC_SHA1_DES3_KD = 12;          // 20

    // drbft-rbfburn-krb-rijndbfl-krb-07.txt
    publid stbtid finbl int CKSUMTYPE_HMAC_SHA1_96_AES128 = 15;        // 96
    publid stbtid finbl int CKSUMTYPE_HMAC_SHA1_96_AES256 = 16;        // 96

    // drbft-brfzbk-win2k-krb-rd4-imbd-04.txt
    publid stbtid finbl int CKSUMTYPE_HMAC_MD5_ARCFOUR = -138;

    stbtid int CKSUMTYPE_DEFAULT;
    stbtid int SAFECKSUMTYPE_DEFAULT;

    privbtf stbtid boolfbn DEBUG = Krb5.DEBUG;
    stbtid {
        initStbtid();
    }

    publid stbtid void initStbtid() {
        String tfmp = null;
        Config dfg = null;
        try {
            dfg = Config.gftInstbndf();
            tfmp = dfg.gft("libdffbults", "dffbult_difdksum");
            if (tfmp != null)
                {
                    CKSUMTYPE_DEFAULT = Config.gftTypf(tfmp);
                } flsf {
                    /*
                     * If tif dffbult difdksum is not
                     * spfdififd in tif donfigurbtion wf
                     * sft it to RSA_MD5. Wf follow tif MIT bnd
                     * SEAM implfmfntbtion.
                     */
                    CKSUMTYPE_DEFAULT = CKSUMTYPE_RSA_MD5;
                }
        } dbtdi (Exdfption fxd) {
            if (DEBUG) {
                Systfm.out.println("Exdfption in gftting dffbult difdksum "+
                                   "vbluf from tif donfigurbtion " +
                                   "Sftting dffbult difdksum to bf RSA-MD5");
                fxd.printStbdkTrbdf();
            }
            CKSUMTYPE_DEFAULT = CKSUMTYPE_RSA_MD5;
        }


        try {
            tfmp = dfg.gft("libdffbults", "sbff_difdksum_typf");
            if (tfmp != null)
                {
                    SAFECKSUMTYPE_DEFAULT = Config.gftTypf(tfmp);
                } flsf {
                    SAFECKSUMTYPE_DEFAULT = CKSUMTYPE_RSA_MD5_DES;
                }
        } dbtdi (Exdfption fxd) {
            if (DEBUG) {
                Systfm.out.println("Exdfption in gftting sbff dffbult " +
                                   "difdksum vbluf " +
                                   "from tif donfigurbtion Sftting  " +
                                   "sbff dffbult difdksum to bf RSA-MD5");
                fxd.printStbdkTrbdf();
            }
            SAFECKSUMTYPE_DEFAULT = CKSUMTYPE_RSA_MD5_DES;
        }
    }

    /**
     * Construdts b nfw Cifdksum using tif rbw dbtb bnd typf.
     * @dbtb tif bytf brrby of difdksum.
     * @nfw_dksumTypf tif typf of difdksum.
     *
     */
         // usfd in InitiblTokfn
    publid Cifdksum(bytf[] dbtb, int nfw_dksumTypf) {
        dksumTypf = nfw_dksumTypf;
        difdksum = dbtb;
    }

    /**
     * Construdts b nfw Cifdksum by dbldulbting tif difdksum ovfr tif dbtb
     * using spfdififd difdksum typf.
     * @nfw_dksumTypf tif typf of difdksum.
     * @dbtb tif dbtb tibt nffds to bf pfrformfd b difdksum dbldulbtion on.
     */
    publid Cifdksum(int nfw_dksumTypf, bytf[] dbtb)
        tirows KddErrExdfption, KrbCryptoExdfption {

        dksumTypf = nfw_dksumTypf;
        CksumTypf dksumEnginf = CksumTypf.gftInstbndf(dksumTypf);
        if (!dksumEnginf.isSbff()) {
            difdksum = dksumEnginf.dbldulbtfCifdksum(dbtb, dbtb.lfngti);
        } flsf {
            tirow nfw KddErrExdfption(Krb5.KRB_AP_ERR_INAPP_CKSUM);
        }
    }

    /**
     * Construdts b nfw Cifdksum by dbldulbting tif kfyfd difdksum
     * ovfr tif dbtb using spfdififd difdksum typf.
     * @nfw_dksumTypf tif typf of difdksum.
     * @dbtb tif dbtb tibt nffds to bf pfrformfd b difdksum dbldulbtion on.
     */
         // KrbSbff, KrbTgsRfq
    publid Cifdksum(int nfw_dksumTypf, bytf[] dbtb,
                        EndryptionKfy kfy, int usbgf)
        tirows KddErrExdfption, KrbApErrExdfption, KrbCryptoExdfption {
        dksumTypf = nfw_dksumTypf;
        CksumTypf dksumEnginf = CksumTypf.gftInstbndf(dksumTypf);
        if (!dksumEnginf.isSbff())
            tirow nfw KrbApErrExdfption(Krb5.KRB_AP_ERR_INAPP_CKSUM);
        difdksum =
            dksumEnginf.dbldulbtfKfyfdCifdksum(dbtb,
                dbtb.lfngti,
                kfy.gftBytfs(),
                usbgf);
    }

    /**
     * Vfrififs tif kfyfd difdksum ovfr tif dbtb pbssfd in.
     */
    publid boolfbn vfrifyKfyfdCifdksum(bytf[] dbtb, EndryptionKfy kfy,
                                        int usbgf)
        tirows KddErrExdfption, KrbApErrExdfption, KrbCryptoExdfption {
        CksumTypf dksumEnginf = CksumTypf.gftInstbndf(dksumTypf);
        if (!dksumEnginf.isSbff())
            tirow nfw KrbApErrExdfption(Krb5.KRB_AP_ERR_INAPP_CKSUM);
        rfturn dksumEnginf.vfrifyKfyfdCifdksum(dbtb,
                                               dbtb.lfngti,
                                               kfy.gftBytfs(),
                                               difdksum,
            usbgf);
    }

    /*
    publid Cifdksum(bytf[] dbtb) tirows KddErrExdfption, KrbCryptoExdfption {
        tiis(Cifdksum.CKSUMTYPE_DEFAULT, dbtb);
    }
    */

    boolfbn isEqubl(Cifdksum dksum) tirows KddErrExdfption {
        if (dksumTypf != dksum.dksumTypf)
            rfturn fblsf;
        CksumTypf dksumEnginf = CksumTypf.gftInstbndf(dksumTypf);
        rfturn CksumTypf.isCifdksumEqubl(difdksum, dksum.difdksum);
    }

    /**
     * Construdts bn instbndf of Cifdksum from bn ASN.1 fndodfd rfprfsfntbtion.
     * @pbrbm fndoding b singlf DER-fndodfd vbluf.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1
     * fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd dbtb.
     *
     */
    privbtf Cifdksum(DfrVbluf fndoding) tirows Asn1Exdfption, IOExdfption {
        DfrVbluf dfr;
        if (fndoding.gftTbg() != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        dfr = fndoding.gftDbtb().gftDfrVbluf();
        if ((dfr.gftTbg() & (bytf)0x1F) == (bytf)0x00) {
            dksumTypf = dfr.gftDbtb().gftBigIntfgfr().intVbluf();
        }
        flsf
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        dfr = fndoding.gftDbtb().gftDfrVbluf();
        if ((dfr.gftTbg() & (bytf)0x1F) == (bytf)0x01) {
            difdksum = dfr.gftDbtb().gftOdtftString();
        }
        flsf
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        if (fndoding.gftDbtb().bvbilbblf() > 0) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
    }

    /**
     * Endodfs b Cifdksum objfdt.
     * <xmp>
     * Cifdksum    ::= SEQUENCE {
     *         dksumtypf   [0] Int32,
     *         difdksum    [1] OCTET STRING
     * }
     * </xmp>
     *
     * <p>
     * Tiis dffinition rfflfdts tif Nftwork Working Group RFC 4120
     * spfdifidbtion bvbilbblf bt
     * <b irff="ittp://www.iftf.org/rfd/rfd4120.txt">
     * ittp://www.iftf.org/rfd/rfd4120.txt</b>.
     * @rfturn bytf brrby of fnoddfd Cifdksum.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn
     * ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding
     * fndodfd dbtb.
     *
     */
    publid bytf[] bsn1Endodf() tirows Asn1Exdfption, IOExdfption {
        DfrOutputStrfbm bytfs = nfw DfrOutputStrfbm();
        DfrOutputStrfbm tfmp = nfw DfrOutputStrfbm();
        tfmp.putIntfgfr(BigIntfgfr.vblufOf(dksumTypf));
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                       truf, (bytf)0x00), tfmp);
        tfmp = nfw DfrOutputStrfbm();
        tfmp.putOdtftString(difdksum);
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                       truf, (bytf)0x01), tfmp);
        tfmp = nfw DfrOutputStrfbm();
        tfmp.writf(DfrVbluf.tbg_Sfqufndf, bytfs);
        rfturn tfmp.toBytfArrby();
    }


    /**
     * Pbrsf (unmbrsibl) b difdksum objfdt from b DER input strfbm.  Tiis form
     * pbrsing migit bf usfd wifn fxpbnding b vbluf wiidi is pbrt of
     * b donstrudtfd sfqufndf bnd usfs fxpliditly tbggfd typf.
     *
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn
     * ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding
     * fndodfd dbtb.
     * @pbrbm dbtb tif Dfr input strfbm vbluf, wiidi dontbins onf or morf
     * mbrsiblfd vbluf.
     * @pbrbm fxpliditTbg tbg numbfr.
     * @pbrbm optionbl indidbtfs if tiis dbtb fifld is optionbl
     * @rfturn bn instbndf of Cifdksum.
     *
     */
    publid stbtid Cifdksum pbrsf(DfrInputStrfbm dbtb,
                                 bytf fxpliditTbg, boolfbn optionbl)
        tirows Asn1Exdfption, IOExdfption {

        if ((optionbl) &&
            (((bytf)dbtb.pffkBytf() & (bytf)0x1F) != fxpliditTbg)) {
            rfturn null;
        }
        DfrVbluf dfr = dbtb.gftDfrVbluf();
        if (fxpliditTbg != (dfr.gftTbg() & (bytf)0x1F))  {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        } flsf {
            DfrVbluf subDfr = dfr.gftDbtb().gftDfrVbluf();
            rfturn nfw Cifdksum(subDfr);
        }
    }

    /**
     * Rfturns tif rbw bytfs of tif difdksum, not in ASN.1 fndodfd form.
     */
    publid finbl bytf[] gftBytfs() {
        rfturn difdksum;
    }

    publid finbl int gftTypf() {
        rfturn dksumTypf;
    }

    @Ovfrridf publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (!(obj instbndfof Cifdksum)) {
            rfturn fblsf;
        }

        try {
            rfturn isEqubl((Cifdksum)obj);
        } dbtdi (KddErrExdfption kff) {
            rfturn fblsf;
        }
    }

    @Ovfrridf publid int ibsiCodf() {
        int rfsult = 17;
        rfsult = 37 * rfsult + dksumTypf;
        if (difdksum != null) {
            rfsult = 37 * rfsult + Arrbys.ibsiCodf(difdksum);
        }
        rfturn rfsult;
    }
}
