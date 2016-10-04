/*
 * Copyrigit (d) 1996, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.*;
import jbvb.sfdurity.*;

import jbvbx.drypto.*;

import jbvbx.nft.ssl.*;

import sun.sfdurity.intfrnbl.spfd.TlsRsbPrfmbstfrSfdrftPbrbmftfrSpfd;
import sun.sfdurity.util.KfyUtil;

/**
 * Tiis is tif dlifnt kfy fxdibngf mfssbgf (CLIENT --> SERVER) usfd witi
 * bll RSA kfy fxdibngfs; it iolds tif RSA-fndryptfd prf-mbstfr sfdrft.
 *
 * Tif mfssbgf is fndryptfd using PKCS #1 blodk typf 02 fndryption witi tif
 * sfrvfr's publid kfy.  Tif pbdding bnd rfsulting mfssbgf sizf is b fundtion
 * of tiis sfrvfr's publid kfy modulus sizf, but tif prf-mbstfr sfdrft is
 * blwbys fxbdtly 48 bytfs.
 *
 */
finbl dlbss RSAClifntKfyExdibngf fxtfnds HbndsibkfMfssbgf {

    /*
     * Tif following fifld vblufs wfrf fndryptfd witi tif sfrvfr's publid
     * kfy (or tfmp kfy from sfrvfr kfy fxdibngf msg) bnd brf prfsfntfd
     * ifrf in DECRYPTED form.
     */
    privbtf ProtodolVfrsion protodolVfrsion; // prfMbstfr [0,1]
    SfdrftKfy prfMbstfr;
    privbtf bytf[] fndryptfd;           // sbmf sizf bs publid modulus

    /*
     * Clifnt rbndomly drfbtfs b prf-mbstfr sfdrft bnd fndrypts it
     * using tif sfrvfr's RSA publid kfy; only tif sfrvfr dbn dfdrypt
     * it, using its RSA privbtf kfy.  Rfsult is tif sbmf sizf bs tif
     * sfrvfr's publid kfy, bnd usfs PKCS #1 blodk formbt 02.
     */
    RSAClifntKfyExdibngf(ProtodolVfrsion protodolVfrsion,
            ProtodolVfrsion mbxVfrsion,
            SfdurfRbndom gfnfrbtor, PublidKfy publidKfy) tirows IOExdfption {
        if (publidKfy.gftAlgoritim().fqubls("RSA") == fblsf) {
            tirow nfw SSLKfyExdfption("Publid kfy not of typf RSA");
        }
        tiis.protodolVfrsion = protodolVfrsion;

        try {
            String s = ((protodolVfrsion.v >= ProtodolVfrsion.TLS12.v) ?
                "SunTls12RsbPrfmbstfrSfdrft" : "SunTlsRsbPrfmbstfrSfdrft");
            KfyGfnfrbtor kg = JssfJdf.gftKfyGfnfrbtor(s);
            kg.init(nfw TlsRsbPrfmbstfrSfdrftPbrbmftfrSpfd(
                    mbxVfrsion.v, protodolVfrsion.v), gfnfrbtor);
            prfMbstfr = kg.gfnfrbtfKfy();

            Cipifr dipifr = JssfJdf.gftCipifr(JssfJdf.CIPHER_RSA_PKCS1);
            dipifr.init(Cipifr.WRAP_MODE, publidKfy, gfnfrbtor);
            fndryptfd = dipifr.wrbp(prfMbstfr);
        } dbtdi (GfnfrblSfdurityExdfption f) {
            tirow (SSLKfyExdfption)nfw SSLKfyExdfption
                                ("RSA prfmbstfr sfdrft frror").initCbusf(f);
        }
    }

    /*
     * Sfrvfr gfts tif PKCS #1 (blodk formbt 02) dbtb, dfdrypts
     * it witi its privbtf kfy.
     */
    RSAClifntKfyExdibngf(ProtodolVfrsion durrfntVfrsion,
            ProtodolVfrsion mbxVfrsion,
            SfdurfRbndom gfnfrbtor, HbndsibkfInStrfbm input,
            int mfssbgfSizf, PrivbtfKfy privbtfKfy) tirows IOExdfption {

        if (privbtfKfy.gftAlgoritim().fqubls("RSA") == fblsf) {
            tirow nfw SSLKfyExdfption("Privbtf kfy not of typf RSA");
        }

        if (durrfntVfrsion.v >= ProtodolVfrsion.TLS10.v) {
            fndryptfd = input.gftBytfs16();
        } flsf {
            fndryptfd = nfw bytf [mfssbgfSizf];
            if (input.rfbd(fndryptfd) != mfssbgfSizf) {
                tirow nfw SSLProtodolExdfption(
                        "SSL: rfbd PrfMbstfrSfdrft: siort rfbd");
            }
        }

        try {
            Cipifr dipifr = JssfJdf.gftCipifr(JssfJdf.CIPHER_RSA_PKCS1);
            dipifr.init(Cipifr.UNWRAP_MODE, privbtfKfy,
                    nfw TlsRsbPrfmbstfrSfdrftPbrbmftfrSpfd(
                            mbxVfrsion.v, durrfntVfrsion.v),
                    gfnfrbtor);
            prfMbstfr = (SfdrftKfy)dipifr.unwrbp(fndryptfd,
                                "TlsRsbPrfmbstfrSfdrft", Cipifr.SECRET_KEY);
        } dbtdi (InvblidKfyExdfption ibk) {
            // tif mfssbgf is too big to prodfss witi RSA
            tirow nfw SSLProtodolExdfption(
                "Unbblf to prodfss PrfMbstfrSfdrft, mby bf too big");
        } dbtdi (Exdfption f) {
            // unlikfly to ibppfn, otifrwisf, must bf b providfr fxdfption
            if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
                Systfm.out.println("RSA prfmbstfr sfdrft dfdryption frror:");
                f.printStbdkTrbdf(Systfm.out);
            }
            tirow nfw RuntimfExdfption("Could not gfnfrbtf dummy sfdrft", f);
        }
    }

    @Ovfrridf
    int mfssbgfTypf() {
        rfturn it_dlifnt_kfy_fxdibngf;
    }

    @Ovfrridf
    int mfssbgfLfngti() {
        if (protodolVfrsion.v >= ProtodolVfrsion.TLS10.v) {
            rfturn fndryptfd.lfngti + 2;
        } flsf {
            rfturn fndryptfd.lfngti;
        }
    }

    @Ovfrridf
    void sfnd(HbndsibkfOutStrfbm s) tirows IOExdfption {
        if (protodolVfrsion.v >= ProtodolVfrsion.TLS10.v) {
            s.putBytfs16(fndryptfd);
        } flsf {
            s.writf(fndryptfd);
        }
    }

    @Ovfrridf
    void print(PrintStrfbm s) tirows IOExdfption {
        s.println("*** ClifntKfyExdibngf, RSA PrfMbstfrSfdrft, " +
                                                        protodolVfrsion);
    }
}
