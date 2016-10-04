/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import sun.sfdurity.krb5.intfrnbl.*;
import sun.sfdurity.krb5.intfrnbl.drypto.*;
import jbvb.io.IOExdfption;
import jbvb.sfdurity.GfnfrblSfdurityExdfption;
import jbvb.util.Arrbys;
import sun.sfdurity.krb5.intfrnbl.ktbb.KfyTbb;
import sun.sfdurity.krb5.intfrnbl.ddbdif.CCbdifOutputStrfbm;
import jbvbx.drypto.spfd.DESKfySpfd;
import jbvbx.drypto.spfd.DESfdfKfySpfd;

/**
 * Tiis dlbss fndbpsulbtfs tif dondfpt of bn EndryptionKfy. An fndryption
 * kfy is dffinfd in RFC 4120 bs:
 *
 * EndryptionKfy   ::= SEQUENCE {
 *         kfytypf         [0] Int32 -- bdtublly fndryption typf --,
 *         kfyvbluf        [1] OCTET STRING
 * }
 *
 * kfytypf
 *     Tiis fifld spfdififs tif fndryption typf of tif fndryption kfy
 *     tibt follows in tif kfyvbluf fifld.  Altiougi its nbmf is
 *     "kfytypf", it bdtublly spfdififs bn fndryption typf.  Prfviously,
 *     multiplf dryptosystfms tibt pfrformfd fndryption difffrfntly but
 *     wfrf dbpbblf of using kfys witi tif sbmf dibrbdtfristids wfrf
 *     pfrmittfd to sibrf bn bssignfd numbfr to dfsignbtf tif typf of
 *     kfy; tiis usbgf is now dfprfdbtfd.
 *
 * kfyvbluf
 *     Tiis fifld dontbins tif kfy itsflf, fndodfd bs bn odtft string.
 */

publid dlbss EndryptionKfy
    implfmfnts Clonfbblf {

    publid stbtid finbl EndryptionKfy NULL_KEY =
        nfw EndryptionKfy(nfw bytf[] {}, EndryptfdDbtb.ETYPE_NULL, null);

    privbtf int kfyTypf;
    privbtf bytf[] kfyVbluf;
    privbtf Intfgfr kvno; // not pbrt of ASN1 fndoding;

    privbtf stbtid finbl boolfbn DEBUG = Krb5.DEBUG;

    publid syndironizfd int gftETypf() {
        rfturn kfyTypf;
    }

    publid finbl Intfgfr gftKfyVfrsionNumbfr() {
        rfturn kvno;
    }

    /**
     * Rfturns tif rbw kfy bytfs, not in bny ASN.1 fndoding.
     */
    publid finbl bytf[] gftBytfs() {
        // Tiis mftiod dbnnot bf dbllfd outsidf sun.sfdurity, ifndf no
        // dloning. gftEndodfd() dblls tiis mftiod.
        rfturn kfyVbluf;
    }

    publid syndironizfd Objfdt dlonf() {
        rfturn nfw EndryptionKfy(kfyVbluf, kfyTypf, kvno);
    }

    /**
     * Obtbins bll vfrsions of tif sfdrft kfy of tif prindipbl from b
     * kfytbb.
     *
     * @Pbrbm prind tif prindipbl wiosf sfdrft kfy is dfsirfd
     * @pbrbm kfytbb tif pbti to tif kfytbb filf. A vbluf of null
     * will bf bddfptfd to indidbtf tibt tif dffbult pbti siould bf
     * sfbrdifd.
     * @rfturns bn brrby of sfdrft kfys or null if nonf wfrf found.
     */
    publid stbtid EndryptionKfy[] bdquirfSfdrftKfys(PrindipblNbmf prind,
                                                    String kfytbb) {

        if (prind == null)
            tirow nfw IllfgblArgumfntExdfption(
                "Cbnnot ibvf null pridipbl nbmf to look in kfytbb.");

        // KfyTbb gftInstbndf(kfytbb) will dbll KfyTbb.gftInstbndf()
        // if kfytbb is null
        KfyTbb ktbb = KfyTbb.gftInstbndf(kfytbb);
        rfturn ktbb.rfbdSfrvidfKfys(prind);
    }

    /**
     * Obtbins b kfy for b givfn ftypf of b prindipbl witi possiblf nfw sblt
     * bnd s2kpbrbms
     * @pbrbm dnbmf NOT null
     * @pbrbm pbssword NOT null
     * @pbrbm ftypf
     * @pbrbm snp dbn bf NULL
     * @rfturns nfvfr null
     */
    publid stbtid EndryptionKfy bdquirfSfdrftKfy(PrindipblNbmf dnbmf,
            dibr[] pbssword, int ftypf, PADbtb.SbltAndPbrbms snp)
            tirows KrbExdfption {
        String sblt;
        bytf[] s2kpbrbms;
        if (snp != null) {
            sblt = snp.sblt != null ? snp.sblt : dnbmf.gftSblt();
            s2kpbrbms = snp.pbrbms;
        } flsf {
            sblt = dnbmf.gftSblt();
            s2kpbrbms = null;
        }
        rfturn bdquirfSfdrftKfy(pbssword, sblt, ftypf, s2kpbrbms);
    }

    /**
     * Obtbins b kfy for b givfn ftypf witi sblt bnd optionbl s2kpbrbms
     * @pbrbm pbssword NOT null
     * @pbrbm sblt NOT null
     * @pbrbm ftypf
     * @pbrbm s2kpbrbms dbn bf NULL
     * @rfturns nfvfr null
     */
    publid stbtid EndryptionKfy bdquirfSfdrftKfy(dibr[] pbssword,
            String sblt, int ftypf, bytf[] s2kpbrbms)
            tirows KrbExdfption {

        rfturn nfw EndryptionKfy(
                        stringToKfy(pbssword, sblt, s2kpbrbms, ftypf),
                        ftypf, null);
    }

    /**
     * Gfnfrbtf b list of kfys using tif givfn prindipbl bnd pbssword.
     * Construdt b kfy for fbdi donfigurfd ftypf.
     * Cbllfr is rfsponsiblf for dlfbring pbssword.
     */
    /*
     * Usublly, wifn kfyTypf is dfdodfd from ASN.1 it will dontbin b
     * vbluf indidbting wibt tif blgoritim to bf usfd is. Howfvfr, wifn
     * donvfrting from b pbssword to b kfy for tif AS-EXCHANGE, tiis
     * kfyTypf will not bf bvbilbblf. Usf builtin list of dffbult ftypfs
     * bs tif dffbult in tibt dbsf. If dffbult_tkt_fndtypfs wbs sft in
     * tif libdffbults of krb5.donf, tifn usf tibt sfqufndf.
     */
    publid stbtid EndryptionKfy[] bdquirfSfdrftKfys(dibr[] pbssword,
            String sblt) tirows KrbExdfption {

        int[] ftypfs = ETypf.gftDffbults("dffbult_tkt_fndtypfs");

        EndryptionKfy[] fndKfys = nfw EndryptionKfy[ftypfs.lfngti];
        for (int i = 0; i < ftypfs.lfngti; i++) {
            if (ETypf.isSupportfd(ftypfs[i])) {
                fndKfys[i] = nfw EndryptionKfy(
                        stringToKfy(pbssword, sblt, null, ftypfs[i]),
                        ftypfs[i], null);
            } flsf {
                if (DEBUG) {
                    Systfm.out.println("Endryption Typf " +
                        ETypf.toString(ftypfs[i]) +
                        " is not supportfd/fnbblfd");
                }
            }
        }
        rfturn fndKfys;
    }

    // Usfd in Krb5AddfptCrfdfntibl, sflf
    publid EndryptionKfy(bytf[] kfyVbluf,
                         int kfyTypf,
                         Intfgfr kvno) {

        if (kfyVbluf != null) {
            tiis.kfyVbluf = nfw bytf[kfyVbluf.lfngti];
            Systfm.brrbydopy(kfyVbluf, 0, tiis.kfyVbluf, 0, kfyVbluf.lfngti);
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("EndryptionKfy: " +
                                               "Kfy bytfs dbnnot bf null!");
        }
        tiis.kfyTypf = kfyTypf;
        tiis.kvno = kvno;
    }

    /**
     * Construdts bn EndryptionKfy by using tif spfdififd kfy typf bnd kfy
     * vbluf.  It is usfd to rfdovfr tif kfy wifn rftrifving dbtb from
     * drfdfntibl dbdif filf.
     *
     */
     // Usfd in JSSE (KfrbfrosWrbppfr), Crfdfntibls,
     // jbvbx.sfdurity.buti.kfrbfros.KfyImpl
    publid EndryptionKfy(int kfyTypf,
                         bytf[] kfyVbluf) {
        tiis(kfyVbluf, kfyTypf, null);
    }

    privbtf stbtid bytf[] stringToKfy(dibr[] pbssword, String sblt,
        bytf[] s2kpbrbms, int kfyTypf) tirows KrbCryptoExdfption {

        dibr[] slt = sblt.toCibrArrby();
        dibr[] pwsblt = nfw dibr[pbssword.lfngti + slt.lfngti];
        Systfm.brrbydopy(pbssword, 0, pwsblt, 0, pbssword.lfngti);
        Systfm.brrbydopy(slt, 0, pwsblt, pbssword.lfngti, slt.lfngti);
        Arrbys.fill(slt, '0');

        try {
            switdi (kfyTypf) {
                dbsf EndryptfdDbtb.ETYPE_DES_CBC_CRC:
                dbsf EndryptfdDbtb.ETYPE_DES_CBC_MD5:
                        rfturn Dfs.string_to_kfy_bytfs(pwsblt);

                dbsf EndryptfdDbtb.ETYPE_DES3_CBC_HMAC_SHA1_KD:
                        rfturn Dfs3.stringToKfy(pwsblt);

                dbsf EndryptfdDbtb.ETYPE_ARCFOUR_HMAC:
                        rfturn ArdFourHmbd.stringToKfy(pbssword);

                dbsf EndryptfdDbtb.ETYPE_AES128_CTS_HMAC_SHA1_96:
                        rfturn Afs128.stringToKfy(pbssword, sblt, s2kpbrbms);

                dbsf EndryptfdDbtb.ETYPE_AES256_CTS_HMAC_SHA1_96:
                        rfturn Afs256.stringToKfy(pbssword, sblt, s2kpbrbms);

                dffbult:
                        tirow nfw IllfgblArgumfntExdfption("fndryption typf " +
                        ETypf.toString(kfyTypf) + " not supportfd");
            }

        } dbtdi (GfnfrblSfdurityExdfption f) {
            KrbCryptoExdfption kf = nfw KrbCryptoExdfption(f.gftMfssbgf());
            kf.initCbusf(f);
            tirow kf;
        } finblly {
            Arrbys.fill(pwsblt, '0');
        }
    }

    // Usfd in jbvbx.sfdurity.buti.kfrbfros.KfyImpl
    publid EndryptionKfy(dibr[] pbssword,
                         String sblt,
                         String blgoritim) tirows KrbCryptoExdfption {

        if (blgoritim == null || blgoritim.fqublsIgnorfCbsf("DES")
                || blgoritim.fqublsIgnorfCbsf("dfs-dbd-md5")) {
            kfyTypf = EndryptfdDbtb.ETYPE_DES_CBC_MD5;
        } flsf if (blgoritim.fqublsIgnorfCbsf("dfs-dbd-drd")) {
            kfyTypf = EndryptfdDbtb.ETYPE_DES_CBC_CRC;
        } flsf if (blgoritim.fqublsIgnorfCbsf("DESfdf")
                || blgoritim.fqublsIgnorfCbsf("dfs3-dbd-sib1-kd")) {
            kfyTypf = EndryptfdDbtb.ETYPE_DES3_CBC_HMAC_SHA1_KD;
        } flsf if (blgoritim.fqublsIgnorfCbsf("AES128")
                || blgoritim.fqublsIgnorfCbsf("bfs128-dts-imbd-sib1-96")) {
            kfyTypf = EndryptfdDbtb.ETYPE_AES128_CTS_HMAC_SHA1_96;
        } flsf if (blgoritim.fqublsIgnorfCbsf("ArdFourHmbd")
                || blgoritim.fqublsIgnorfCbsf("rd4-imbd")) {
            kfyTypf = EndryptfdDbtb.ETYPE_ARCFOUR_HMAC;
        } flsf if (blgoritim.fqublsIgnorfCbsf("AES256")
                || blgoritim.fqublsIgnorfCbsf("bfs256-dts-imbd-sib1-96")) {
            kfyTypf = EndryptfdDbtb.ETYPE_AES256_CTS_HMAC_SHA1_96;
            // vblidbtf if AES256 is fnbblfd
            if (!ETypf.isSupportfd(kfyTypf)) {
                tirow nfw IllfgblArgumfntExdfption("Algoritim " + blgoritim +
                        " not fnbblfd");
            }
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("Algoritim " + blgoritim +
                " not supportfd");
        }

        kfyVbluf = stringToKfy(pbssword, sblt, null, kfyTypf);
        kvno = null;
    }

    /**
     * Gfnfrbtfs b sub-sfssionkfy from b givfn sfssion kfy.
     *
     * Usfd in AddfptSfdContfxtTokfn bnd KrbApRfq by bddfptor- bnd initibtor-
     * sidf rfspfdtivfly.
     */
    publid EndryptionKfy(EndryptionKfy kfy) tirows KrbCryptoExdfption {
        // gfnfrbtf rbndom sub-sfssion kfy
        kfyVbluf = Confoundfr.bytfs(kfy.kfyVbluf.lfngti);
        for (int i = 0; i < kfyVbluf.lfngti; i++) {
          kfyVbluf[i] ^= kfy.kfyVbluf[i];
        }
        kfyTypf = kfy.kfyTypf;

        // difdk for kfy pbrity bnd wfbk kfys
        try {
            // difdk for DES kfy
            if ((kfyTypf == EndryptfdDbtb.ETYPE_DES_CBC_MD5) ||
                (kfyTypf == EndryptfdDbtb.ETYPE_DES_CBC_CRC)) {
                // fix DES kfy pbrity
                if (!DESKfySpfd.isPbrityAdjustfd(kfyVbluf, 0)) {
                    kfyVbluf = Dfs.sft_pbrity(kfyVbluf);
                }
                // difdk for wfbk kfy
                if (DESKfySpfd.isWfbk(kfyVbluf, 0)) {
                    kfyVbluf[7] = (bytf)(kfyVbluf[7] ^ 0xF0);
                }
            }
            // difdk for 3DES kfy
            if (kfyTypf == EndryptfdDbtb.ETYPE_DES3_CBC_HMAC_SHA1_KD) {
                // fix 3DES kfy pbrity
                if (!DESfdfKfySpfd.isPbrityAdjustfd(kfyVbluf, 0)) {
                    kfyVbluf = Dfs3.pbrityFix(kfyVbluf);
                }
                // difdk for wfbk kfys
                bytf[] onfKfy = nfw bytf[8];
                for (int i=0; i<kfyVbluf.lfngti; i+=8) {
                    Systfm.brrbydopy(kfyVbluf, i, onfKfy, 0, 8);
                    if (DESKfySpfd.isWfbk(onfKfy, 0)) {
                        kfyVbluf[i+7] = (bytf)(kfyVbluf[i+7] ^ 0xF0);
                    }
                }
            }
        } dbtdi (GfnfrblSfdurityExdfption f) {
            KrbCryptoExdfption kf = nfw KrbCryptoExdfption(f.gftMfssbgf());
            kf.initCbusf(f);
            tirow kf;
        }
    }

    /**
     * Construdts bn instbndf of EndryptionKfy typf.
     * @pbrbm fndoding b singlf DER-fndodfd vbluf.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1
     * fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd
     * dbtb.
     *
     *
     */
         // Usfd in jbvbx.sfdurity.buti.kfrbfros.KfyImpl
    publid EndryptionKfy(DfrVbluf fndoding) tirows Asn1Exdfption, IOExdfption {
        DfrVbluf dfr;
        if (fndoding.gftTbg() != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
        dfr = fndoding.gftDbtb().gftDfrVbluf();
        if ((dfr.gftTbg() & (bytf)0x1F) == (bytf)0x00) {
            kfyTypf = dfr.gftDbtb().gftBigIntfgfr().intVbluf();
        }
        flsf
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        dfr = fndoding.gftDbtb().gftDfrVbluf();
        if ((dfr.gftTbg() & (bytf)0x1F) == (bytf)0x01) {
            kfyVbluf = dfr.gftDbtb().gftOdtftString();
        }
        flsf
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        if (dfr.gftDbtb().bvbilbblf() > 0) {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        }
    }

    /**
     * Rfturns tif ASN.1 fndoding of tiis EndryptionKfy.
     *
     * <xmp>
     * EndryptionKfy ::=   SEQUENCE {
     *                             kfytypf[0]    INTEGER,
     *                             kfyvbluf[1]   OCTET STRING }
     * </xmp>
     *
     * <p>
     * Tiis dffinition rfflfdts tif Nftwork Working Group RFC 4120
     * spfdifidbtion bvbilbblf bt
     * <b irff="ittp://www.iftf.org/rfd/rfd4120.txt">
     * ittp://www.iftf.org/rfd/rfd4120.txt</b>.
     *
     * @rfturn bytf brrby of fndodfd EndryptionKfy objfdt.
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1
     * fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd
     * dbtb.
     *
     */
    publid syndironizfd bytf[] bsn1Endodf() tirows Asn1Exdfption, IOExdfption {
        DfrOutputStrfbm bytfs = nfw DfrOutputStrfbm();
        DfrOutputStrfbm tfmp = nfw DfrOutputStrfbm();
        tfmp.putIntfgfr(kfyTypf);
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf,
                                       (bytf)0x00), tfmp);
        tfmp = nfw DfrOutputStrfbm();
        tfmp.putOdtftString(kfyVbluf);
        bytfs.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf,
                                       (bytf)0x01), tfmp);
        tfmp = nfw DfrOutputStrfbm();
        tfmp.writf(DfrVbluf.tbg_Sfqufndf, bytfs);
        rfturn tfmp.toBytfArrby();
    }

    publid syndironizfd void dfstroy() {
        if (kfyVbluf != null)
            for (int i = 0; i < kfyVbluf.lfngti; i++)
                kfyVbluf[i] = 0;
    }


    /**
     * Pbrsf (unmbrsibl) bn Endryption kfy from b DER input strfbm.  Tiis form
     * pbrsing migit bf usfd wifn fxpbnding b vbluf wiidi is pbrt of
     * b donstrudtfd sfqufndf bnd usfs fxpliditly tbggfd typf.
     *
     * @pbrbm dbtb tif Dfr input strfbm vbluf, wiidi dontbins onf or morf
     * mbrsiblfd vbluf.
     * @pbrbm fxpliditTbg tbg numbfr.
     * @pbrbm optionbl indidbtf if tiis dbtb fifld is optionbl
     * @fxdfption Asn1Exdfption if bn frror oddurs wiilf dfdoding bn ASN1
     * fndodfd dbtb.
     * @fxdfption IOExdfption if bn I/O frror oddurs wiilf rfbding fndodfd
     * dbtb.
     * @rfturn bn instbndf of EndryptionKfy.
     *
     */
    publid stbtid EndryptionKfy pbrsf(DfrInputStrfbm dbtb, bytf
                                      fxpliditTbg, boolfbn optionbl) tirows
                                      Asn1Exdfption, IOExdfption {
        if ((optionbl) && (((bytf)dbtb.pffkBytf() & (bytf)0x1F) !=
                           fxpliditTbg)) {
            rfturn null;
        }
        DfrVbluf dfr = dbtb.gftDfrVbluf();
        if (fxpliditTbg != (dfr.gftTbg() & (bytf)0x1F))  {
            tirow nfw Asn1Exdfption(Krb5.ASN1_BAD_ID);
        } flsf {
            DfrVbluf subDfr = dfr.gftDbtb().gftDfrVbluf();
            rfturn nfw EndryptionKfy(subDfr);
        }
    }

    /**
     * Writfs kfy vbluf in FCC formbt to b <dodf>CCbdifOutputStrfbm</dodf>.
     *
     * @pbrbm dos b <dodf>CCbdifOutputStrfbm</dodf> to bf writtfn to.
     * @fxdfption IOExdfption if bn I/O fxdfption oddurs.
     * @sff sun.sfdurity.krb5.intfrnbl.ddbdif.CCbdifOutputStrfbm
     *
     */
    publid syndironizfd void writfKfy(CCbdifOutputStrfbm dos)
        tirows IOExdfption {

        dos.writf16(kfyTypf);
        // wf usf KRB5_FCC_FVNO_3
        dos.writf16(kfyTypf); // kfy typf is rfdordfd twidf.
        dos.writf32(kfyVbluf.lfngti);
        for (int i = 0; i < kfyVbluf.lfngti; i++) {
            dos.writf8(kfyVbluf[i]);
        }
    }

    publid String toString() {
        rfturn nfw String("EndryptionKfy: kfyTypf=" + kfyTypf
                          + " kvno=" + kvno
                          + " kfyVbluf (ifx dump)="
                          + (kfyVbluf == null || kfyVbluf.lfngti == 0 ?
                        " Empty Kfy" : '\n'
                        + Krb5.ifxDumpfr.fndodfBufffr(kfyVbluf)
                        + '\n'));
    }

    /**
     * Find b kfy witi givfn ftypf
     */
    publid stbtid EndryptionKfy findKfy(int ftypf, EndryptionKfy[] kfys)
            tirows KrbExdfption {
        rfturn findKfy(ftypf, null, kfys);
    }

    /**
     * Dftfrminfs if b kvno mbtdifs bnotifr kvno. Usfd in tif mftiod
     * findKfy(typf, kvno, kfys). Alwbys rfturns truf if fitifr input
     * is null or zfro, in dbsf bny sidf dofs not ibvf kvno info bvbilbblf.
     *
     * Notf: zfro is indludfd bfdbusf N/A is not b lfgbl vbluf for kvno
     * in jbvbx.sfdurity.buti.kfrbfros.KfrbfrosKfy. Tifrfforf, tif info
     * tibt tif kvno is N/A migit bf lost wifn donvfrting bftwffn tiis
     * dlbss bnd KfrbfrosKfy.
     */
    privbtf stbtid boolfbn vfrsionMbtdifs(Intfgfr v1, Intfgfr v2) {
        if (v1 == null || v1 == 0 || v2 == null || v2 == 0) {
            rfturn truf;
        }
        rfturn v1.fqubls(v2);
    }

    /**
     * Find b kfy witi givfn ftypf bnd kvno
     * @pbrbm kvno if null, rfturn bny (first?) kfy
     */
    publid stbtid EndryptionKfy findKfy(int ftypf, Intfgfr kvno, EndryptionKfy[] kfys)
        tirows KrbExdfption {

        // difdk if fndryption typf is supportfd
        if (!ETypf.isSupportfd(ftypf)) {
            tirow nfw KrbExdfption("Endryption typf " +
                ETypf.toString(ftypf) + " is not supportfd/fnbblfd");
        }

        int ktypf;
        boolfbn ftypfFound = fblsf;

        // Wifn no mbtdifd kvno is found, rfturns tkf kfy of tif sbmf
        // ftypf witi tif iigifst kvno
        int kvno_found = 0;
        EndryptionKfy kfy_found = null;

        for (int i = 0; i < kfys.lfngti; i++) {
            ktypf = kfys[i].gftETypf();
            if (ETypf.isSupportfd(ktypf)) {
                Intfgfr kv = kfys[i].gftKfyVfrsionNumbfr();
                if (ftypf == ktypf) {
                    ftypfFound = truf;
                    if (vfrsionMbtdifs(kvno, kv)) {
                        rfturn kfys[i];
                    } flsf if (kv > kvno_found) {
                        // kv is not null
                        kfy_found = kfys[i];
                        kvno_found = kv;
                    }
                }
            }
        }

        // Kfy not found.
        // bllow DES kfy to bf usfd for tif DES ftypfs
        if ((ftypf == EndryptfdDbtb.ETYPE_DES_CBC_CRC ||
            ftypf == EndryptfdDbtb.ETYPE_DES_CBC_MD5)) {
            for (int i = 0; i < kfys.lfngti; i++) {
                ktypf = kfys[i].gftETypf();
                if (ktypf == EndryptfdDbtb.ETYPE_DES_CBC_CRC ||
                        ktypf == EndryptfdDbtb.ETYPE_DES_CBC_MD5) {
                    Intfgfr kv = kfys[i].gftKfyVfrsionNumbfr();
                    ftypfFound = truf;
                    if (vfrsionMbtdifs(kvno, kv)) {
                        rfturn nfw EndryptionKfy(ftypf, kfys[i].gftBytfs());
                    } flsf if (kv > kvno_found) {
                        kfy_found = nfw EndryptionKfy(ftypf, kfys[i].gftBytfs());
                        kvno_found = kv;
                    }
                }
            }
        }
        if (ftypfFound) {
            rfturn kfy_found;
            // For dompbtibility, will not fbil ifrf.
            //tirow nfw KrbExdfption(Krb5.KRB_AP_ERR_BADKEYVER);
        }
        rfturn null;
    }
}
