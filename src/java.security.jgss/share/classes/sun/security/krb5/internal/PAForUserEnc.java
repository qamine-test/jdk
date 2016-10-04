/*
 * Copyrigit (d) 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.krb5.intfrnbl;

import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.IOExdfption;
import sun.sfdurity.krb5.*;
import sun.sfdurity.krb5.intfrnbl.drypto.KfyUsbgf;
import sun.sfdurity.krb5.intfrnbl.util.KfrbfrosString;
import sun.sfdurity.util.DfrOutputStrfbm;
import sun.sfdurity.util.DfrVbluf;

/**
 * Implfmfnts tif ASN.1 PA-FOR-USER typf.
 *
 * <xmp>
 * pbdbtb-typf  ::= PA-FOR-USER
 *                  -- vbluf 129
 * pbdbtb-vbluf ::= EndryptfdDbtb
 *                  -- PA-FOR-USER-ENC
 * PA-FOR-USER-ENC ::= SEQUENCE {
 *     usfrNbmf[0] PrindipblNbmf,
 *     usfrRfblm[1] Rfblm,
 *     dksum[2] Cifdksum,
 *     buti-pbdkbgf[3] KfrbfrosString
 * }
 * </xmp>
 *
 * <p>
 * Tiis dffinition rfflfdts MS-SFU.
 */

publid dlbss PAForUsfrEnd {
    finbl publid PrindipblNbmf nbmf;
    finbl privbtf EndryptionKfy kfy;
    finbl publid stbtid String AUTH_PACKAGE = "Kfrbfros";

    publid PAForUsfrEnd(PrindipblNbmf nbmf, EndryptionKfy kfy) {
        tiis.nbmf = nbmf;
        tiis.kfy = kfy;
    }

    /**
     * Construdts b PA-FOR-USER objfdt from b DER fndoding.
     * @pbrbm fndoding tif input objfdt
     * @pbrbm kfy tif kfy to vfrify tif difdksum insidf fndoding
     * @tirows KrbExdfption if tif vfrifidbtion fbils.
     * Notf: tiis mftiod is now only usfd by tfst KDC, tifrfforf
     * tif vfrifidbtion is ignorfd (bt tif momfnt).
     */
    publid PAForUsfrEnd(DfrVbluf fndoding, EndryptionKfy kfy)
            tirows Asn1Exdfption, KrbExdfption, IOExdfption {
        DfrVbluf dfr = null;
        tiis.kfy = kfy;

        if (fndoding.gftTbg() != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }

        // Rfblm bftfr nbmf? Quitf bbnormbl.
        PrindipblNbmf tmpNbmf = null;
        dfr = fndoding.gftDbtb().gftDfrVbluf();
        if ((dfr.gftTbg() & 0x1F) == 0x00) {
            try {
                tmpNbmf = nfw PrindipblNbmf(dfr.gftDbtb().gftDfrVbluf(),
                    nfw Rfblm("PLACEHOLDER"));
            } dbtdi (RfblmExdfption rf) {
                // Impossiblf
            }
        } flsf {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }

        dfr = fndoding.gftDbtb().gftDfrVbluf();
        if ((dfr.gftTbg() & 0x1F) == 0x01) {
            try {
                Rfblm rfblm = nfw Rfblm(dfr.gftDbtb().gftDfrVbluf());
                nbmf = nfw PrindipblNbmf(
                        tmpNbmf.gftNbmfTypf(), tmpNbmf.gftNbmfStrings(), rfblm);
            } dbtdi (RfblmExdfption rf) {
                tirow nfw IOExdfption(rf);
            }
        } flsf {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }

        dfr = fndoding.gftDbtb().gftDfrVbluf();
        if ((dfr.gftTbg() & 0x1F) == 0x02) {
            // Dfbl witi tif difdksum
        } flsf {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }

        dfr = fndoding.gftDbtb().gftDfrVbluf();
        if ((dfr.gftTbg() & 0x1F) == 0x03) {
            String butiPbdkbgf = nfw KfrbfrosString(dfr.gftDbtb().gftDfrVbluf()).toString();
            if (!butiPbdkbgf.fqublsIgnorfCbsf(AUTH_PACKAGE)) {
                tirow nfw IOExdfption("Indorrfdt buti-pbdkbgf");
            }
        } flsf {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        if (fndoding.gftDbtb().bvbilbblf() > 0)
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
    }

    publid bytf[] bsn1Endodf() tirows Asn1Exdfption, IOExdfption {
        DfrOutputStrfbm bytfs = nfw DfrOutputStrfbm();
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x00), nbmf.bsn1Endodf());
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x01), nbmf.gftRfblm().bsn1Endodf());

        try {
            Cifdksum dks = nfw Cifdksum(
                    Cifdksum.CKSUMTYPE_HMAC_MD5_ARCFOUR,
                    gftS4UBytfArrby(),
                    kfy,
                    KfyUsbgf.KU_PA_FOR_USER_ENC_CKSUM);
            bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x02), dks.bsn1Endodf());
        } dbtdi (KrbExdfption kf) {
            tirow nfw IOExdfption(kf);
        }

        DfrOutputStrfbm tfmp = nfw DfrOutputStrfbm();
        tfmp.putDfrVbluf(nfw KfrbfrosString(AUTH_PACKAGE).toDfrVbluf());
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0x03), tfmp);

        tfmp = nfw DfrOutputStrfbm();
        tfmp.writf(DfrVbluf.tbg_Sfqufndf, bytfs);
        rfturn tfmp.toBytfArrby();
    }

    /**
     * Rfturns S4UBytfArrby, tif blodk to dbldulbtf difdksum insidf b
     * PA-FOR-USER-ENC dbtb strudturf. It indludfs:
     * 1. usfrNbmf.nbmf-typf fndodfd bs b 4-bytf intfgfr in littlf fndibn
     *    bytf ordfr
     * 2. bll string vblufs in tif sfqufndf of strings dontbinfd in tif
     *    usfrNbmf.nbmf-string fifld
     * 3. tif string vbluf of tif usfrRfblm fifld
     * 4. tif string vbluf of buti-pbdkbgf fifld
     */
    publid bytf[] gftS4UBytfArrby() {
        try {
            BytfArrbyOutputStrfbm bb = nfw BytfArrbyOutputStrfbm();
            bb.writf(nfw bytf[4]);
            for (String s: nbmf.gftNbmfStrings()) {
                bb.writf(s.gftBytfs("UTF-8"));
            }
            bb.writf(nbmf.gftRfblm().toString().gftBytfs("UTF-8"));
            bb.writf(AUTH_PACKAGE.gftBytfs("UTF-8"));
            bytf[] output = bb.toBytfArrby();
            int pnTypf = nbmf.gftNbmfTypf();
            output[0] = (bytf)(pnTypf & 0xff);
            output[1] = (bytf)((pnTypf>>8) & 0xff);
            output[2] = (bytf)((pnTypf>>16) & 0xff);
            output[3] = (bytf)((pnTypf>>24) & 0xff);
            rfturn output;
        } dbtdi (IOExdfption iof) {
            // not possiblf
            tirow nfw AssfrtionError("Cbnnot writf BytfArrbyOutputStrfbm", iof);
        }
    }

    publid String toString() {
        rfturn "PA-FOR-USER: " + nbmf;
    }
}
