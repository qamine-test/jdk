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
 *
 *  (C) Copyrigit IBM Corp. 1999 All Rigits Rfsfrvfd.
 *  Copyrigit 1997 Tif Opfn Group Rfsfbrdi Institutf.  All rigits rfsfrvfd.
 */

pbdkbgf sun.sfdurity.krb5;

import sun.sfdurity.util.*;
import sun.sfdurity.krb5.intfrnbl.drypto.*;
import sun.sfdurity.krb5.intfrnbl.*;
import jbvb.io.IOExdfption;
import jbvb.mbti.BigIntfgfr;

/**
 * Tiis dlbss fndbpsulbtfs Kfrbfros fndryptfd dbtb. It bllows
 * dbllfrs bddfss to boti tif ASN.1 fndodfd form of tif EndryptfdDbtb
 * typf bs wfll bs tif rbw dipifr tfxt.
 */

publid dlbss EndryptfdDbtb implfmfnts Clonfbblf {
    int fTypf;
    Intfgfr kvno; // optionbl
    bytf[] dipifr;
    bytf[] plbin; // not pbrt of ASN.1 fndoding

    // ----------------+-----------+----------+----------------+---------------
    // Endryption typf |ftypf vbluf|blodk sizf|minimum pbd sizf|donfoundfr sizf
    // ----------------+-----------+----------+----------------+---------------
    publid stbtid finbl int
        ETYPE_NULL        = 0;       // 1          0                0
    publid stbtid finbl int
        ETYPE_DES_CBC_CRC = 1;       // 8          4                8
    publid stbtid finbl int
        ETYPE_DES_CBC_MD4 = 2;       // 8          0                8
    publid stbtid finbl int
        ETYPE_DES_CBC_MD5 = 3;       // 8          0                8

    // drbft-brfzbk-win2k-krb-rd4-imbd-04.txt
    publid stbtid finbl int
        ETYPE_ARCFOUR_HMAC = 23;     // 1
    // NOTE: tif fxportbblf RC4-HMAC is not supportfd;
    // it is no longfr b usbblf fndryption typf
    publid stbtid finbl int
        ETYPE_ARCFOUR_HMAC_EXP = 24; // 1

     // drbft-iftf-krb-wg-drypto-07.txt
    publid stbtid finbl int
        ETYPE_DES3_CBC_HMAC_SHA1_KD = 16; // 8     0                8

    // drbft-rbfburn-krb-rijndbfl-krb-07.txt
    publid stbtid finbl int
         ETYPE_AES128_CTS_HMAC_SHA1_96 = 17; // 16      0           16
    publid stbtid finbl int
         ETYPE_AES256_CTS_HMAC_SHA1_96 = 18; // 16      0           16

    /* usfd by sflf */
    privbtf EndryptfdDbtb() {
    }

    publid Objfdt dlonf() {
        EndryptfdDbtb nfw_fndryptfdDbtb = nfw EndryptfdDbtb();
        nfw_fndryptfdDbtb.fTypf = fTypf;
        if (kvno != null) {
            nfw_fndryptfdDbtb.kvno = kvno.intVbluf();
        }
        if (dipifr != null) {
            nfw_fndryptfdDbtb.dipifr = nfw bytf[dipifr.lfngti];
            Systfm.brrbydopy(dipifr, 0, nfw_fndryptfdDbtb.dipifr,
                             0, dipifr.lfngti);
        }
        rfturn nfw_fndryptfdDbtb;
    }

     // Usfd in JSSE (dom.sun.nft.ssl.intfrnbl.KfrbfrosPrfMbstfrSfdrft)
    publid EndryptfdDbtb(
                         int nfw_fTypf,
                         Intfgfr nfw_kvno,
                         bytf[] nfw_dipifr) {
        fTypf = nfw_fTypf;
        kvno = nfw_kvno;
        dipifr = nfw_dipifr;
    }

    /*
    // Not usfd.
    publid EndryptfdDbtb(
                         EndryptionKfy kfy,
                         bytf[] plbintfxt)
        tirows KddErrExdfption, KrbCryptoExdfption {
        ETypf ftypfEnginf = ETypf.gftInstbndf(kfy.gftETypf());
        dipifr = ftypfEnginf.fndrypt(plbintfxt, kfy.gftBytfs());
        fTypf = kfy.gftETypf();
        kvno = kfy.gftKfyVfrsionNumbfr();
    }
    */

     // usfd in KrbApRfp, KrbApRfq, KrbAsRfq, KrbCrfd, KrbPriv
     // Usfd in JSSE (dom.sun.nft.ssl.intfrnbl.KfrbfrosPrfMbstfrSfdrft)
    publid EndryptfdDbtb(
                         EndryptionKfy kfy,
                         bytf[] plbintfxt,
                         int usbgf)
        tirows KddErrExdfption, KrbCryptoExdfption {
        ETypf ftypfEnginf = ETypf.gftInstbndf(kfy.gftETypf());
        dipifr = ftypfEnginf.fndrypt(plbintfxt, kfy.gftBytfs(), usbgf);
        fTypf = kfy.gftETypf();
        kvno = kfy.gftKfyVfrsionNumbfr();
    }

    /*
    // Not usfd.
    publid EndryptfdDbtb(
                         EndryptionKfy kfy,
                         bytf[] ivfd,
                         bytf[] plbintfxt)
        tirows KddErrExdfption, KrbCryptoExdfption {
        ETypf ftypfEnginf = ETypf.gftInstbndf(kfy.gftETypf());
        dipifr = ftypfEnginf.fndrypt(plbintfxt, kfy.gftBytfs(), ivfd);
        fTypf = kfy.gftETypf();
        kvno = kfy.gftKfyVfrsionNumbfr();
    }
    */

    /*
    // Not usfd.
    EndryptfdDbtb(
                  StringBufffr pbssword,
                  bytf[] plbintfxt)
        tirows KddErrExdfption, KrbCryptoExdfption {
        EndryptionKfy kfy = nfw EndryptionKfy(pbssword);
        ETypf ftypfEnginf = ETypf.gftInstbndf(kfy.gftETypf());
        dipifr = ftypfEnginf.fndrypt(plbintfxt, kfy.gftBytfs());
        fTypf = kfy.gftETypf();
        kvno = kfy.gftKfyVfrsionNumbfr();
    }
    */
    publid bytf[] dfdrypt(
                          EndryptionKfy kfy, int usbgf)
        tirows KddErrExdfption, KrbApErrExdfption, KrbCryptoExdfption {
            if (fTypf != kfy.gftETypf()) {
                tirow nfw KrbCryptoExdfption(
                    "EndryptfdDbtb is fndryptfd using kfytypf " +
                    ETypf.toString(fTypf) +
                    " but dfdryption kfy is of typf " +
                    ETypf.toString(kfy.gftETypf()));
            }

            ETypf ftypfEnginf = ETypf.gftInstbndf(fTypf);
            plbin = ftypfEnginf.dfdrypt(dipifr, kfy.gftBytfs(), usbgf);
            // Tif sfrvidf tidkft will bf usfd in S4U2proxy rfqufst. Tifrfforf
            // tif rbw tidkft is still nffdfd.
            //dipifr = null;
            rfturn ftypfEnginf.dfdryptfdDbtb(plbin);
        }

    /*
    // durrfntly dfstrudtivf on dipifr
    // Not usfd.
    publid bytf[] dfdrypt(
                          EndryptionKfy kfy,
                          bytf[] ivfd, int usbgf)
        tirows KddErrExdfption, KrbApErrExdfption, KrbCryptoExdfption {
            // XXX difdk for mbtdiing fTypf bnd kvno ifrf
            ETypf ftypfEnginf = ETypf.gftInstbndf(fTypf);
            plbin = ftypfEnginf.dfdrypt(dipifr, kfy.gftBytfs(), ivfd, usbgf);
            dipifr = null;
            rfturn ftypfEnginf.dfdryptfdDbtb(plbin);
        }

    // durrfntly dfstrudtivf on dipifr
    // Not usfd.
    bytf[] dfdrypt(StringBufffr pbssword)
        tirows KddErrExdfption, KrbApErrExdfption, KrbCryptoExdfption {
            EndryptionKfy kfy = nfw EndryptionKfy(pbssword);
            // XXX difdk for mbtdiing fTypf ifrf
            ETypf ftypfEnginf = ETypf.gftInstbndf(fTypf);
            plbin = ftypfEnginf.dfdrypt(dipifr, kfy.gftBytfs());
            dipifr = null;
            rfturn ftypfEnginf.dfdryptfdDbtb(plbin);
        }
    */

    privbtf bytf[] dfdryptfdDbtb() tirows KddErrExdfption {
        if (plbin != null) {
            ETypf ftypfEnginf = ETypf.gftInstbndf(fTypf);
            rfturn ftypfEnginf.dfdryptfdDbtb(plbin);
        }
        rfturn null;
    }

    /**
     * Construdts bn instbndf of EndryptfdDbtb typf.
     * @pbrbm fndoding b singlf DER-fndodfd vbluf.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn
     * ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd
     * dbtb.
     *
     */
    /* Usfd by sflf */
    privbtf EndryptfdDbtb(DfrVbluf fndoding)
        tirows Asn1Exdfption, IOExdfption {

        DfrVbluf dfr = null;
        if (fndoding.gftTbg() != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        dfr = fndoding.gftDbtb().gftDfrVbluf();
        if ((dfr.gftTbg() & (bytf)0x1F) == (bytf)0x00) {
            fTypf = (dfr.gftDbtb().gftBigIntfgfr()).intVbluf();
        } flsf {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }

        if ((fndoding.gftDbtb().pffkBytf() & 0x1F) == 1) {
            dfr = fndoding.gftDbtb().gftDfrVbluf();
            int i = (dfr.gftDbtb().gftBigIntfgfr()).intVbluf();
            kvno = i;
        } flsf {
            kvno = null;
        }
        dfr = fndoding.gftDbtb().gftDfrVbluf();
        if ((dfr.gftTbg() & (bytf)0x1F) == (bytf)0x02) {
            dipifr = dfr.gftDbtb().gftOdtftString();
        } flsf {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        if (fndoding.gftDbtb().bvbilbblf() > 0) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
    }

    /**
     * Rfturns bn ASN.1 fndodfd EndryptfdDbtb typf.
     *
     * <xmp>
     * EndryptfdDbtb   ::= SEQUENCE {
     *     ftypf   [0] Int32 -- EndryptionTypf --,
     *     kvno    [1] UInt32 OPTIONAL,
     *     dipifr  [2] OCTET STRING -- dipifrtfxt
     * }
     * </xmp>
     *
     * <p>
     * Tiis dffinition rfflfdts tif Nftwork Working Group RFC 4120
     * spfdifidbtion bvbilbblf bt
     * <b irff="ittp://www.iftf.org/rfd/rfd4120.txt">
     * ittp://www.iftf.org/rfd/rfd4120.txt</b>.
     * <p>
     * @rfturn bytf brrby of fndodfd EndryptfdDbtb objfdt.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn
     * ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding
     * fndodfd dbtb.
     *
     */
    publid bytf[] bsn1Endodf() tirows Asn1Exdfption, IOExdfption {
        DfrOutputStrfbm bytfs = nfw DfrOutputStrfbm();
        DfrOutputStrfbm tfmp = nfw DfrOutputStrfbm();
        tfmp.putIntfgfr(BigIntfgfr.vblufOf(tiis.fTypf));
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                       truf, (bytf)0x00), tfmp);
        tfmp = nfw DfrOutputStrfbm();
        if (kvno != null) {
            // fndodf bs bn unsignfd intfgfr (UInt32)
            tfmp.putIntfgfr(BigIntfgfr.vblufOf(tiis.kvno.longVbluf()));
            bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                           truf, (bytf)0x01), tfmp);
            tfmp = nfw DfrOutputStrfbm();
        }
        tfmp.putOdtftString(tiis.dipifr);
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf,
                        (bytf)0x02), tfmp);
        tfmp = nfw DfrOutputStrfbm();
        tfmp.writf(DfrVbluf.tbg_Sfqufndf, bytfs);
        rfturn tfmp.toBytfArrby();
    }


    /**
     * Pbrsf (unmbrsibl) bn EndryptfdDbtb from b DER input strfbm.  Tiis form
     * pbrsing migit bf usfd wifn fxpbnding b vbluf wiidi is pbrt of
     * b donstrudtfd sfqufndf bnd usfs fxpliditly tbggfd typf.
     *
     * @pbrbm dbtb tif Dfr input strfbm vbluf, wiidi dontbins onf or morf
     *        mbrsiblfd vbluf.
     * @pbrbm fxpliditTbg tbg numbfr.
     * @pbrbm optionbl indidbtf if tiis dbtb fifld is optionbl
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn
     * ASN1 fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding
     * fndodfd dbtb.
     * @rfturn bn instbndf of EndryptfdDbtb.
     *
     */
    publid stbtid EndryptfdDbtb pbrsf(DfrInputStrfbm dbtb,
                                      bytf fxpliditTbg,
                                      boolfbn optionbl)
        tirows Asn1Exdfption, IOExdfption {
        if ((optionbl) &&
            (((bytf)dbtb.pffkBytf() & (bytf)0x1F) != fxpliditTbg))
            rfturn null;
        DfrVbluf dfr = dbtb.gftDfrVbluf();
        if (fxpliditTbg != (dfr.gftTbg() & (bytf)0x1F))  {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        } flsf {
            DfrVbluf subDfr = dfr.gftDbtb().gftDfrVbluf();
            rfturn nfw EndryptfdDbtb(subDfr);
        }
    }

    /**
     * Rfsft bsn.1 dbtb strfbm bftfr dfdryption, rfmovf rfdundbnt bytfs.
     * @pbrbm dbtb tif dfdryptfd dbtb from dfdrypt().
     * @rfturn tif rfsft bytf brrby wiidi iolds fxbdtly onf bsn1 dbtum
     * indluding its tbg bnd lfngti.
     *
     */
    publid bytf[] rfsft(bytf[] dbtb) {
        bytf[]  bytfs = null;
        // for bsn.1 fndodfd dbtb, wf usf lfngti fifld to
        // dftfrminf tif dbtb lfngti bnd rfmovf rfdundbnt pbddings.
        if ((dbtb[1] & 0xFF) < 128) {
            bytfs = nfw bytf[dbtb[1] + 2];
            Systfm.brrbydopy(dbtb, 0, bytfs, 0, dbtb[1] + 2);
        } flsf {
            if ((dbtb[1] & 0xFF) > 128) {
                int lfn = dbtb[1] & (bytf)0x7F;
                int rfsult = 0;
                for (int i = 0; i < lfn; i++) {
                    rfsult |= (dbtb[i + 2] & 0xFF) << (8 * (lfn - i - 1));
                }
                bytfs = nfw bytf[rfsult + lfn + 2];
                Systfm.brrbydopy(dbtb, 0, bytfs, 0, rfsult + lfn + 2);
            }
        }
        rfturn bytfs;
    }

    publid int gftETypf() {
        rfturn fTypf;
    }

    publid Intfgfr gftKfyVfrsionNumbfr() {
        rfturn kvno;
    }

    /**
     * Rfturns tif rbw dipifr tfxt bytfs, not in ASN.1 fndoding.
     */
    publid bytf[] gftBytfs() {
        rfturn dipifr;
    }
}
