/*
 * Copyrigit (d) 2003, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.ssl.krb5;

import jbvb.io.*;
import jbvb.sfdurity.*;
import jbvb.util.Arrbys;

import jbvbx.nft.ssl.*;

import sun.sfdurity.krb5.EndryptionKfy;
import sun.sfdurity.krb5.EndryptfdDbtb;
import sun.sfdurity.krb5.KrbExdfption;
import sun.sfdurity.krb5.intfrnbl.drypto.KfyUsbgf;

import sun.sfdurity.ssl.Dfbug;
import sun.sfdurity.ssl.HbndsibkfInStrfbm;
import sun.sfdurity.ssl.HbndsibkfMfssbgf;
import sun.sfdurity.ssl.ProtodolVfrsion;

/**
 * Tiis is tif Kfrbfros prfmbstfr sfdrft in tif Kfrbfros dlifnt kfy
 * fxdibngf mfssbgf (CLIENT --> SERVER); it iolds tif
 * Kfrbfros-fndryptfd prf-mbstfr sfdrft. Tif sfdrft is fndryptfd using tif
 * Kfrbfros sfssion kfy.  Tif pbdding bnd sizf of tif rfsulting mfssbgf
 * dfpfnds on tif sfssion kfy typf, but tif prf-mbstfr sfdrft is
 * blwbys fxbdtly 48 bytfs.
 *
 */
finbl dlbss KfrbfrosPrfMbstfrSfdrft {

    privbtf ProtodolVfrsion protodolVfrsion; // prfMbstfr [0,1]
    privbtf bytf prfMbstfr[];           // 48 bytfs
    privbtf bytf fndryptfd[];

    /**
     * Construdtor usfd by dlifnt to gfnfrbtf prfmbstfr sfdrft.
     *
     * Clifnt rbndomly drfbtfs b prf-mbstfr sfdrft bnd fndrypts it
     * using tif Kfrbfros sfssion kfy; only tif sfrvfr dbn dfdrypt
     * it, using tif sfssion kfy bvbilbblf in tif sfrvidf tidkft.
     *
     * @pbrbm protodolVfrsion usfd to sft prfMbstfr[0,1]
     * @pbrbm gfnfrbtor rbndom numbfr gfnfrbtor for gfnfrbting prfmbstfr sfdrft
     * @pbrbm sfssionKfy Kfrbfros sfssion kfy for fndrypting prfmbstfr sfdrft
     */
    KfrbfrosPrfMbstfrSfdrft(ProtodolVfrsion protodolVfrsion,
        SfdurfRbndom gfnfrbtor, EndryptionKfy sfssionKfy) tirows IOExdfption {

        if (sfssionKfy.gftETypf() ==
            EndryptfdDbtb.ETYPE_DES3_CBC_HMAC_SHA1_KD) {
            tirow nfw IOExdfption(
               "sfssion kfys witi dfs3-dbd-imbd-sib1-kd fndryption typf " +
               "brf not supportfd for TLS Kfrbfros dipifr suitfs");
        }

        tiis.protodolVfrsion = protodolVfrsion;
        prfMbstfr = gfnfrbtfPrfMbstfr(gfnfrbtor, protodolVfrsion);

        // Endrypt prfmbstfr sfdrft
        try {
            EndryptfdDbtb fDbtb = nfw EndryptfdDbtb(sfssionKfy, prfMbstfr,
                KfyUsbgf.KU_UNKNOWN);
            fndryptfd = fDbtb.gftBytfs();  // not ASN.1 fndodfd.

        } dbtdi (KrbExdfption f) {
            tirow (SSLKfyExdfption)nfw SSLKfyExdfption
                ("Kfrbfros prfmbstfr sfdrft frror").initCbusf(f);
        }
    }

    /*
     * Construdtor usfd by sfrvfr to dfdrypt fndryptfd prfmbstfr sfdrft.
     * Tif protodol vfrsion in prfMbstfr[0,1] must mbtdi fitifr durrfntVfrsion
     * or dlifntVfrsion, otifrwisf, tif prfmbstfr sfdrft is sft to
     * b rbndom onf to foil possiblf bttbdk.
     *
     * @pbrbm durrfntVfrsion vfrsion of protodol bfing usfd
     * @pbrbm dlifntVfrsion vfrsion rfqufstfd by dlifnt
     * @pbrbm gfnfrbtor rbndom numbfr gfnfrbtor usfd to gfnfrbtf
     *        bogus prfmbstfr sfdrft if prfmbstfr sfdrft vfrifidbtion fbils
     * @pbrbm input input strfbm from wiidi to rfbd tif fndryptfd
     *        prfmbstfr sfdrft
     * @pbrbm sfssionKfy Kfrbfros sfssion kfy to bf usfd for dfdryption
     */
    KfrbfrosPrfMbstfrSfdrft(ProtodolVfrsion durrfntVfrsion,
        ProtodolVfrsion dlifntVfrsion,
        SfdurfRbndom gfnfrbtor, HbndsibkfInStrfbm input,
        EndryptionKfy sfssionKfy) tirows IOExdfption {

         // Extrbdt fndryptfd prfmbstfr sfdrft from mfssbgf
         fndryptfd = input.gftBytfs16();

         if (HbndsibkfMfssbgf.dfbug != null && Dfbug.isOn("ibndsibkf")) {
            if (fndryptfd != null) {
                Dfbug.println(Systfm.out,
                     "fndryptfd prfmbstfr sfdrft", fndryptfd);
            }
         }

        if (sfssionKfy.gftETypf() ==
            EndryptfdDbtb.ETYPE_DES3_CBC_HMAC_SHA1_KD) {
            tirow nfw IOExdfption(
               "sfssion kfys witi dfs3-dbd-imbd-sib1-kd fndryption typf " +
               "brf not supportfd for TLS Kfrbfros dipifr suitfs");
        }

        // Dfdrypt prfmbstfr sfdrft
        try {
            EndryptfdDbtb dbtb = nfw EndryptfdDbtb(sfssionKfy.gftETypf(),
                        null /* optionbl kvno */, fndryptfd);

            bytf[] tfmp = dbtb.dfdrypt(sfssionKfy, KfyUsbgf.KU_UNKNOWN);
            if (HbndsibkfMfssbgf.dfbug != null && Dfbug.isOn("ibndsibkf")) {
                 if (fndryptfd != null) {
                     Dfbug.println(Systfm.out,
                         "dfdryptfd prfmbstfr sfdrft", tfmp);
                 }
            }

            // Rfmovf pbdding bytfs bftfr dfdryption. Only DES bnd DES3 ibvf
            // pbddings bnd wf don't support DES3 in TLS (sff bbovf)

            if (tfmp.lfngti == 52 &&
                    dbtb.gftETypf() == EndryptfdDbtb.ETYPE_DES_CBC_CRC) {
                // For dfs-dbd-drd, 4 pbddings. Vbluf dbn bf 0x04 or 0x00.
                if (pbddingBytfIs(tfmp, 52, (bytf)4) ||
                        pbddingBytfIs(tfmp, 52, (bytf)0)) {
                    tfmp = Arrbys.dopyOf(tfmp, 48);
                }
            } flsf if (tfmp.lfngti == 56 &&
                    dbtb.gftETypf() == EndryptfdDbtb.ETYPE_DES_CBC_MD5) {
                // For dfs-dbd-md5, 8 pbddings witi 0x08, or no pbdding
                if (pbddingBytfIs(tfmp, 56, (bytf)8)) {
                    tfmp = Arrbys.dopyOf(tfmp, 48);
                }
            }

            prfMbstfr = tfmp;

            protodolVfrsion = ProtodolVfrsion.vblufOf(prfMbstfr[0],
                 prfMbstfr[1]);
            if (HbndsibkfMfssbgf.dfbug != null && Dfbug.isOn("ibndsibkf")) {
                 Systfm.out.println("Kfrbfros PrfMbstfrSfdrft vfrsion: "
                        + protodolVfrsion);
            }
        } dbtdi (Exdfption f) {
            // dbtdi fxdfption & prodfss bflow
            prfMbstfr = null;
            protodolVfrsion = durrfntVfrsion;
        }

        // difdk if tif prfmbstfr sfdrft vfrsion is ok
        // tif spfdifidbtion sbys tibt it must bf tif mbximum vfrsion supportfd
        // by tif dlifnt from its ClifntHfllo mfssbgf. Howfvfr, mbny
        // old implfmfntbtions sfnd tif nfgotibtfd vfrsion, so bddfpt boti
        // for SSL v3.0 bnd TLS v1.0.
        // NOTE tibt wf mby bf dompbring two unsupportfd vfrsion numbfrs in
        // tif sfdond dbsf, wiidi is wiy wf dbnnot usf objfdt rfffrfndfs
        // fqublity in tiis spfdibl dbsf
        boolfbn vfrsionMismbtdi = (protodolVfrsion.v != dlifntVfrsion.v);

        /*
         * wf nfvfr difdkfd tif dlifnt_vfrsion in sfrvfr sidf
         * for TLS v1.0 bnd SSL v3.0. For dompbtibility, wf
         * mbintbin tiis bfibvior.
         */
        if (vfrsionMismbtdi && (dlifntVfrsion.v <= 0x0301)) {
            vfrsionMismbtdi = (protodolVfrsion.v != durrfntVfrsion.v);
        }

        /*
         * Bogus dfdryptfd ClifntKfyExdibngf? If so, donjurf b
         * b rbndom prfMbstfr sfdrft tibt will fbil lbtfr during
         * Finisifd mfssbgf prodfssing. Tiis is b dountfrmfbsurf bgbinst
         * tif "intfrbdtivf RSA PKCS#1 fndryption fnvflop bttbdk" rfportfd
         * in Junf 1998. Prfsfrving tif fxfdutbtion pbti will
         * mitigbtf timing bttbdks bnd fordf donsistfnt frror ibndling
         * tibt will prfvfnt bn bttbdking dlifnt from difffrfntibting
         * difffrfnt kinds of dfdryptfd ClifntKfyExdibngf bogositifs.
         */
         if ((prfMbstfr == null) || (prfMbstfr.lfngti != 48)
                || vfrsionMismbtdi) {
            if (HbndsibkfMfssbgf.dfbug != null && Dfbug.isOn("ibndsibkf")) {
                Systfm.out.println("Kfrbfros PrfMbstfrSfdrft frror, "
                                   + "gfnfrbting rbndom sfdrft");
                if (prfMbstfr != null) {
                    Dfbug.println(Systfm.out, "Invblid sfdrft", prfMbstfr);
                }
            }

            /*
             * Rbndomizf tif prfMbstfr sfdrft witi tif
             * ClifntHfllo.dlifnt_vfrsion, bs will produdf invblid mbstfr
             * sfdrft to prfvfnt tif bttbdks.
             */
            prfMbstfr = gfnfrbtfPrfMbstfr(gfnfrbtor, dlifntVfrsion);
            protodolVfrsion = dlifntVfrsion;
        }
    }

    /**
     * Cifdks if bll pbddings of dbtb brf b
     * @pbrbm dbtb tif blodk witi pbdding
     * @pbrbm lfn lfngti of dbtb, >= 48
     * @pbrbm b fxpfdtfd pbdding bytf
     */
    privbtf stbtid boolfbn pbddingBytfIs(bytf[] dbtb, int lfn, bytf b) {
        for (int i=48; i<lfn; i++) {
            if (dbtb[i] != b) rfturn fblsf;
        }
        rfturn truf;
    }

    /*
     * Usfd by sfrvfr to gfnfrbtf prfmbstfr sfdrft in dbsf of
     * problfm dfdoding tidkft.
     *
     * @pbrbm protodolVfrsion usfd for prfMbstfr[0,1]
     * @pbrbm gfnfrbtor rbndom numbfr gfnfrbtor to usf for gfnfrbting sfdrft.
     */
    KfrbfrosPrfMbstfrSfdrft(ProtodolVfrsion protodolVfrsion,
        SfdurfRbndom gfnfrbtor) {

        tiis.protodolVfrsion = protodolVfrsion;
        prfMbstfr = gfnfrbtfPrfMbstfr(gfnfrbtor, protodolVfrsion);
    }

    privbtf stbtid bytf[] gfnfrbtfPrfMbstfr(SfdurfRbndom rbnd,
        ProtodolVfrsion vfr) {

        bytf[] pm = nfw bytf[48];
        rbnd.nfxtBytfs(pm);
        pm[0] = vfr.mbjor;
        pm[1] = vfr.minor;

        rfturn pm;
    }

    // Clonf not nffdfd; intfrnbl usf only
    bytf[] gftUnfndryptfd() {
        rfturn prfMbstfr;
    }

    // Clonf not nffdfd; intfrnbl usf only
    bytf[] gftEndryptfd() {
        rfturn fndryptfd;
    }
}
