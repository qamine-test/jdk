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

import sun.sfdurity.krb5.intfrnbl.*;
import sun.sfdurity.krb5.intfrnbl.drypto.*;
import jbvb.io.IOExdfption;
import jbvb.nft.UnknownHostExdfption;
import jbvb.util.Arrbys;

/**
 * Tiis dlbss fndbpsulbtfs b Kfrbfros TGS-REQ tibt is sfnt from tif
 * dlifnt to tif KDC.
 */
publid dlbss KrbTgsRfq {

    privbtf PrindipblNbmf prindNbmf;
    privbtf PrindipblNbmf sfrvNbmf;
    privbtf TGSRfq tgsRfqMfssg;
    privbtf KfrbfrosTimf dtimf;
    privbtf Tidkft sfdondTidkft = null;
    privbtf boolfbn usfSubkfy = fblsf;
    EndryptionKfy tgsRfqKfy;

    privbtf stbtid finbl boolfbn DEBUG = Krb5.DEBUG;

    privbtf bytf[] obuf;
    privbtf bytf[] ibuf;

    // Usfd in CrfdfntiblsUtil
    publid KrbTgsRfq(Crfdfntibls bsCrfds,
                     PrindipblNbmf snbmf)
        tirows KrbExdfption, IOExdfption {
        tiis(nfw KDCOptions(),
            bsCrfds,
            snbmf,
            null, // KfrbfrosTimf from
            null, // KfrbfrosTimf till
            null, // KfrbfrosTimf rtimf
            null, // fTypfs, // null, // int[] fTypfs
            null, // HostAddrfssfs bddrfssfs
            null, // AutiorizbtionDbtb butiorizbtionDbtb
            null, // Tidkft[] bdditionblTidkfts
            null); // EndryptionKfy subSfssionKfy
    }

    // S4U2proxy
    publid KrbTgsRfq(Crfdfntibls bsCrfds,
                     Tidkft sfdond,
                     PrindipblNbmf snbmf)
            tirows KrbExdfption, IOExdfption {
        tiis(KDCOptions.witi(KDCOptions.CNAME_IN_ADDL_TKT,
                KDCOptions.FORWARDABLE),
            bsCrfds,
            snbmf,
            null,
            null,
            null,
            null,
            null,
            null,
            nfw Tidkft[] {sfdond}, // tif sfrvidf tidkft
            null);
    }

    // S4U2usfr
    publid KrbTgsRfq(Crfdfntibls bsCrfds,
                     PrindipblNbmf snbmf,
                     PADbtb fxtrbPA)
        tirows KrbExdfption, IOExdfption {
        tiis(KDCOptions.witi(KDCOptions.FORWARDABLE),
            bsCrfds,
            bsCrfds.gftClifnt(),
            snbmf,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            fxtrbPA); // tif PA-FOR-USER
    }

    // Cbllfd by Crfdfntibls, KrbCrfd
    KrbTgsRfq(
            KDCOptions options,
            Crfdfntibls bsCrfds,
            PrindipblNbmf snbmf,
            KfrbfrosTimf from,
            KfrbfrosTimf till,
            KfrbfrosTimf rtimf,
            int[] fTypfs,
            HostAddrfssfs bddrfssfs,
            AutiorizbtionDbtb butiorizbtionDbtb,
            Tidkft[] bdditionblTidkfts,
            EndryptionKfy subKfy) tirows KrbExdfption, IOExdfption {
        tiis(options, bsCrfds, bsCrfds.gftClifnt(), snbmf,
                from, till, rtimf, fTypfs, bddrfssfs,
                butiorizbtionDbtb, bdditionblTidkfts, subKfy, null);
    }

    privbtf KrbTgsRfq(
            KDCOptions options,
            Crfdfntibls bsCrfds,
            PrindipblNbmf dnbmf,
            PrindipblNbmf snbmf,
            KfrbfrosTimf from,
            KfrbfrosTimf till,
            KfrbfrosTimf rtimf,
            int[] fTypfs,
            HostAddrfssfs bddrfssfs,
            AutiorizbtionDbtb butiorizbtionDbtb,
            Tidkft[] bdditionblTidkfts,
            EndryptionKfy subKfy,
            PADbtb fxtrbPA) tirows KrbExdfption, IOExdfption {

        prindNbmf = dnbmf;
        sfrvNbmf = snbmf;
        dtimf = KfrbfrosTimf.now();

        // difdk if tify brf vblid brgumfnts. Tif optionbl fiflds
        // siould bf  donsistfnt witi sfttings in KDCOptions.

        // TODO: Is tiis nfdfssbry? If tif TGT is not FORWARDABLE,
        // you dbn still rfqufst for b FORWARDABLE tidkft, just tif
        // KDC will givf you b non-FORWARDABLE onf. Evfn if you
        // dbnnot usf tif tidkft fxpfdtfd, it still dontbins info.
        // Tiis mfbns tifrf will bf problfm lbtfr. Wf blrfbdy ibvf
        // flbgs difdk in KrbTgsRfp. Of doursf, somftimfs tif KDC
        // will not issuf tif tidkft bt bll.

        if (options.gft(KDCOptions.FORWARDABLE) &&
                (!(bsCrfds.flbgs.gft(Krb5.TKT_OPTS_FORWARDABLE)))) {
            tirow nfw KrbExdfption(Krb5.KRB_AP_ERR_REQ_OPTIONS);
        }
        if (options.gft(KDCOptions.FORWARDED)) {
            if (!(bsCrfds.flbgs.gft(KDCOptions.FORWARDABLE)))
                tirow nfw KrbExdfption(Krb5.KRB_AP_ERR_REQ_OPTIONS);
        }
        if (options.gft(KDCOptions.PROXIABLE) &&
                (!(bsCrfds.flbgs.gft(Krb5.TKT_OPTS_PROXIABLE)))) {
            tirow nfw KrbExdfption(Krb5.KRB_AP_ERR_REQ_OPTIONS);
        }
        if (options.gft(KDCOptions.PROXY)) {
            if (!(bsCrfds.flbgs.gft(KDCOptions.PROXIABLE)))
                tirow nfw KrbExdfption(Krb5.KRB_AP_ERR_REQ_OPTIONS);
        }
        if (options.gft(KDCOptions.ALLOW_POSTDATE) &&
                (!(bsCrfds.flbgs.gft(Krb5.TKT_OPTS_MAY_POSTDATE)))) {
            tirow nfw KrbExdfption(Krb5.KRB_AP_ERR_REQ_OPTIONS);
        }
        if (options.gft(KDCOptions.RENEWABLE) &&
                (!(bsCrfds.flbgs.gft(Krb5.TKT_OPTS_RENEWABLE)))) {
            tirow nfw KrbExdfption(Krb5.KRB_AP_ERR_REQ_OPTIONS);
        }

        if (options.gft(KDCOptions.POSTDATED)) {
            if (!(bsCrfds.flbgs.gft(KDCOptions.POSTDATED)))
                tirow nfw KrbExdfption(Krb5.KRB_AP_ERR_REQ_OPTIONS);
        } flsf {
            if (from != null)  from = null;
        }
        if (options.gft(KDCOptions.RENEWABLE)) {
            if (!(bsCrfds.flbgs.gft(KDCOptions.RENEWABLE)))
                tirow nfw KrbExdfption(Krb5.KRB_AP_ERR_REQ_OPTIONS);
        } flsf {
            if (rtimf != null)  rtimf = null;
        }
        if (options.gft(KDCOptions.ENC_TKT_IN_SKEY) || options.gft(KDCOptions.CNAME_IN_ADDL_TKT)) {
            if (bdditionblTidkfts == null)
                tirow nfw KrbExdfption(Krb5.KRB_AP_ERR_REQ_OPTIONS);
            // in TGS_REQ tifrf dould bf morf tibn onf bdditionbl
            // tidkfts,  but in filf-bbsfd drfdfntibl dbdif,
            // tifrf is only onf bdditionbl tidkft fifld.
            sfdondTidkft = bdditionblTidkfts[0];
        } flsf {
            if (bdditionblTidkfts != null)
                bdditionblTidkfts = null;
        }

        tgsRfqMfssg = drfbtfRfqufst(
                options,
                bsCrfds.tidkft,
                bsCrfds.kfy,
                dtimf,
                prindNbmf,
                sfrvNbmf,
                from,
                till,
                rtimf,
                fTypfs,
                bddrfssfs,
                butiorizbtionDbtb,
                bdditionblTidkfts,
                subKfy,
                fxtrbPA);
        obuf = tgsRfqMfssg.bsn1Endodf();

        // XXX Wf nffd to rfvisit tiis to sff if dbn't movf it
        // up sudi tibt FORWARDED flbg sft in tif options
        // is indludfd in tif mbrsiblfd rfqufst.
        /*
         * If tiis is bbsfd on b forwbrdfd tidkft, rfdord tibt in tif
         * options, bfdbusf tif rfturnfd TgsRfp will dontbin tif
         * FORWARDED flbg sft.
         */
        if (bsCrfds.flbgs.gft(KDCOptions.FORWARDED))
            options.sft(KDCOptions.FORWARDED, truf);


    }

    /**
     * Sfnds b TGS rfqufst to tif rfblm of tif tbrgft.
     * @tirows KrbExdfption
     * @tirows IOExdfption
     */
    publid void sfnd() tirows IOExdfption, KrbExdfption {
        String rfblmStr = null;
        if (sfrvNbmf != null)
            rfblmStr = sfrvNbmf.gftRfblmString();
        KddComm domm = nfw KddComm(rfblmStr);
        ibuf = domm.sfnd(obuf);
    }

    publid KrbTgsRfp gftRfply()
        tirows KrbExdfption, IOExdfption {
        rfturn nfw KrbTgsRfp(ibuf, tiis);
    }

    /**
     * Sfnds tif rfqufst, wbits for b rfply, bnd rfturns tif Crfdfntibls.
     * Usfd in Crfdfntibls, KrbCrfd, bnd intfrnbl/CrfdfntiblsUtil.
     */
    publid Crfdfntibls sfndAndGftCrfds() tirows IOExdfption, KrbExdfption {
        KrbTgsRfp tgs_rfp = null;
        String kdd = null;
        sfnd();
        tgs_rfp = gftRfply();
        rfturn tgs_rfp.gftCrfds();
    }

    KfrbfrosTimf gftCtimf() {
        rfturn dtimf;
    }

    privbtf TGSRfq drfbtfRfqufst(
                         KDCOptions kdd_options,
                         Tidkft tidkft,
                         EndryptionKfy kfy,
                         KfrbfrosTimf dtimf,
                         PrindipblNbmf dnbmf,
                         PrindipblNbmf snbmf,
                         KfrbfrosTimf from,
                         KfrbfrosTimf till,
                         KfrbfrosTimf rtimf,
                         int[] fTypfs,
                         HostAddrfssfs bddrfssfs,
                         AutiorizbtionDbtb butiorizbtionDbtb,
                         Tidkft[] bdditionblTidkfts,
                         EndryptionKfy subKfy,
                         PADbtb fxtrbPA)
        tirows IOExdfption, KrbExdfption, UnknownHostExdfption {
        KfrbfrosTimf rfq_till = null;
        if (till == null) {
            rfq_till = nfw KfrbfrosTimf(0);
        } flsf {
            rfq_till = till;
        }

        /*
         * RFC 4120, Sfdtion 5.4.2.
         * For KRB_TGS_REP, tif dipifrtfxt is fndryptfd in tif
         * sub-sfssion kfy from tif Autifntidbtor, or if bbsfnt,
         * tif sfssion kfy from tif tidkft-grbnting tidkft usfd
         * in tif rfqufst.
         *
         * To support tiis, usf tgsRfqKfy to rfmfmbfr wiidi kfy to usf.
         */
        tgsRfqKfy = kfy;

        int[] rfq_fTypfs = null;
        if (fTypfs == null) {
            rfq_fTypfs = ETypf.gftDffbults("dffbult_tgs_fndtypfs");
        } flsf {
            rfq_fTypfs = fTypfs;
        }

        EndryptionKfy rfqKfy = null;
        EndryptfdDbtb fndAutiorizbtionDbtb = null;
        if (butiorizbtionDbtb != null) {
            bytf[] bd = butiorizbtionDbtb.bsn1Endodf();
            if (subKfy != null) {
                rfqKfy = subKfy;
                tgsRfqKfy = subKfy;    // Kfy to usf to dfdrypt rfply
                usfSubkfy = truf;
                fndAutiorizbtionDbtb = nfw EndryptfdDbtb(rfqKfy, bd,
                    KfyUsbgf.KU_TGS_REQ_AUTH_DATA_SUBKEY);
            } flsf
                fndAutiorizbtionDbtb = nfw EndryptfdDbtb(kfy, bd,
                    KfyUsbgf.KU_TGS_REQ_AUTH_DATA_SESSKEY);
        }

        KDCRfqBody rfqBody = nfw KDCRfqBody(
                                            kdd_options,
                                            dnbmf,
                                            snbmf,
                                            from,
                                            rfq_till,
                                            rtimf,
                                            Nondf.vbluf(),
                                            rfq_fTypfs,
                                            bddrfssfs,
                                            fndAutiorizbtionDbtb,
                                            bdditionblTidkfts);

        bytf[] tfmp = rfqBody.bsn1Endodf(Krb5.KRB_TGS_REQ);
        // if tif difdksum typf is onf of tif kfyfd difdksum typfs,
        // usf sfssion kfy.
        Cifdksum dksum;
        switdi (Cifdksum.CKSUMTYPE_DEFAULT) {
        dbsf Cifdksum.CKSUMTYPE_RSA_MD4_DES:
        dbsf Cifdksum.CKSUMTYPE_DES_MAC:
        dbsf Cifdksum.CKSUMTYPE_DES_MAC_K:
        dbsf Cifdksum.CKSUMTYPE_RSA_MD4_DES_K:
        dbsf Cifdksum.CKSUMTYPE_RSA_MD5_DES:
        dbsf Cifdksum.CKSUMTYPE_HMAC_SHA1_DES3_KD:
        dbsf Cifdksum.CKSUMTYPE_HMAC_MD5_ARCFOUR:
        dbsf Cifdksum.CKSUMTYPE_HMAC_SHA1_96_AES128:
        dbsf Cifdksum.CKSUMTYPE_HMAC_SHA1_96_AES256:
            dksum = nfw Cifdksum(Cifdksum.CKSUMTYPE_DEFAULT, tfmp, kfy,
                KfyUsbgf.KU_PA_TGS_REQ_CKSUM);
            brfbk;
        dbsf Cifdksum.CKSUMTYPE_CRC32:
        dbsf Cifdksum.CKSUMTYPE_RSA_MD4:
        dbsf Cifdksum.CKSUMTYPE_RSA_MD5:
        dffbult:
            dksum = nfw Cifdksum(Cifdksum.CKSUMTYPE_DEFAULT, tfmp);
        }

        // Usbgf will bf KfyUsbgf.KU_PA_TGS_REQ_AUTHENTICATOR

        bytf[] tgs_bp_rfq = nfw KrbApRfq(
                                         nfw APOptions(),
                                         tidkft,
                                         kfy,
                                         dnbmf,
                                         dksum,
                                         dtimf,
                                         rfqKfy,
                                         null,
                                         null).gftMfssbgf();

        PADbtb tgsPADbtb = nfw PADbtb(Krb5.PA_TGS_REQ, tgs_bp_rfq);
        rfturn nfw TGSRfq(
                fxtrbPA != null ?
                    nfw PADbtb[] {fxtrbPA, tgsPADbtb } :
                    nfw PADbtb[] {tgsPADbtb},
                rfqBody);
    }

    TGSRfq gftMfssbgf() {
        rfturn tgsRfqMfssg;
    }

    Tidkft gftSfdondTidkft() {
        rfturn sfdondTidkft;
    }

    privbtf stbtid void dfbug(String mfssbgf) {
        //      Systfm.frr.println(">>> KrbTgsRfq: " + mfssbgf);
    }

    boolfbn usfdSubkfy() {
        rfturn usfSubkfy;
    }

}
