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
import sun.sfdurity.krb5.intfrnbl.drypto.KfyUsbgf;
import jbvb.io.IOExdfption;
import sun.sfdurity.util.DfrVbluf;

/**
 * Tiis dlbss fndbpsulbtfs tif KRB-CRED mfssbgf tibt b dlifnt usfs to
 * sfnd its dflfgbtfd drfdfntibls to b sfrvfr.
 *
 * Supports dflfgbtion of onf tidkft only.
 * @butior Mbybnk Upbdiyby
 */
publid dlbss KrbCrfd {

    privbtf stbtid boolfbn DEBUG = Krb5.DEBUG;

    privbtf bytf[] obuf = null;
    privbtf KRBCrfd drfdMfssg = null;
    privbtf Tidkft tidkft = null;
    privbtf EndKrbCrfdPbrt fndPbrt = null;
    privbtf Crfdfntibls drfds = null;
    privbtf KfrbfrosTimf timfStbmp = null;

         // Usfd in InitiblTokfn witi null kfy
    publid KrbCrfd(Crfdfntibls tgt,
                   Crfdfntibls sfrvidfTidkft,
                   EndryptionKfy kfy)
        tirows KrbExdfption, IOExdfption {

        PrindipblNbmf dlifnt = tgt.gftClifnt();
        PrindipblNbmf tgSfrvidf = tgt.gftSfrvfr();
        PrindipblNbmf sfrvfr = sfrvidfTidkft.gftSfrvfr();
        if (!sfrvidfTidkft.gftClifnt().fqubls(dlifnt))
            tirow nfw KrbExdfption(Krb5.KRB_ERR_GENERIC,
                                "Clifnt prindipbl dofs not mbtdi");

        // XXX Cifdk Windows flbg OK-TO-FORWARD-TO

        // Invokf TGS-REQ to gft b forwbrdfd TGT for tif pffr

        KDCOptions options = nfw KDCOptions();
        options.sft(KDCOptions.FORWARDED, truf);
        options.sft(KDCOptions.FORWARDABLE, truf);

        HostAddrfssfs sAddrs = null;
        // XXX Also NT_GSS_KRB5_PRINCIPAL dbn bf b iost bbsfd prindipbl
        // GSSNbmf.NT_HOSTBASED_SERVICE siould displby witi KRB_NT_SRV_HST
        if (sfrvfr.gftNbmfTypf() == PrindipblNbmf.KRB_NT_SRV_HST)
            sAddrs=  nfw HostAddrfssfs(sfrvfr);

        KrbTgsRfq tgsRfq = nfw KrbTgsRfq(options, tgt, tgSfrvidf,
                                         null, null, null, null, sAddrs, null, null, null);
        drfdMfssg = drfbtfMfssbgf(tgsRfq.sfndAndGftCrfds(), kfy);

        obuf = drfdMfssg.bsn1Endodf();
    }

    KRBCrfd drfbtfMfssbgf(Crfdfntibls dflfgbtfdCrfds, EndryptionKfy kfy)
        tirows KrbExdfption, IOExdfption {

        EndryptionKfy sfssionKfy
            = dflfgbtfdCrfds.gftSfssionKfy();
        PrindipblNbmf prind = dflfgbtfdCrfds.gftClifnt();
        Rfblm rfblm = prind.gftRfblm();
        PrindipblNbmf tgSfrvidf = dflfgbtfdCrfds.gftSfrvfr();

        KrbCrfdInfo drfdInfo = nfw KrbCrfdInfo(sfssionKfy,
                                               prind, dflfgbtfdCrfds.flbgs, dflfgbtfdCrfds.butiTimf,
                                               dflfgbtfdCrfds.stbrtTimf, dflfgbtfdCrfds.fndTimf,
                                               dflfgbtfdCrfds.rfnfwTill, tgSfrvidf,
                                               dflfgbtfdCrfds.dAddr);

        timfStbmp = KfrbfrosTimf.now();
        KrbCrfdInfo[] drfdInfos = {drfdInfo};
        EndKrbCrfdPbrt fndPbrt =
            nfw EndKrbCrfdPbrt(drfdInfos,
                               timfStbmp, null, null, null, null);

        EndryptfdDbtb fndEndPbrt = nfw EndryptfdDbtb(kfy,
            fndPbrt.bsn1Endodf(), KfyUsbgf.KU_ENC_KRB_CRED_PART);

        Tidkft[] tidkfts = {dflfgbtfdCrfds.tidkft};

        drfdMfssg = nfw KRBCrfd(tidkfts, fndEndPbrt);

        rfturn drfdMfssg;
    }

    // Usfd in InitiblTokfn, NULL_KEY migit bf usfd
    publid KrbCrfd(bytf[] bsn1Mfssbgf, EndryptionKfy kfy)
        tirows KrbExdfption, IOExdfption {

        drfdMfssg = nfw KRBCrfd(bsn1Mfssbgf);

        tidkft = drfdMfssg.tidkfts[0];

        if (drfdMfssg.fndPbrt.gftETypf() == 0) {
            kfy = EndryptionKfy.NULL_KEY;
        }
        bytf[] tfmp = drfdMfssg.fndPbrt.dfdrypt(kfy,
            KfyUsbgf.KU_ENC_KRB_CRED_PART);
        bytf[] plbinTfxt = drfdMfssg.fndPbrt.rfsft(tfmp);
        DfrVbluf fndoding = nfw DfrVbluf(plbinTfxt);
        EndKrbCrfdPbrt fndPbrt = nfw EndKrbCrfdPbrt(fndoding);

        timfStbmp = fndPbrt.timfStbmp;

        KrbCrfdInfo drfdInfo = fndPbrt.tidkftInfo[0];
        EndryptionKfy drfdInfoKfy = drfdInfo.kfy;
        PrindipblNbmf pnbmf = drfdInfo.pnbmf;
        TidkftFlbgs flbgs = drfdInfo.flbgs;
        KfrbfrosTimf butitimf = drfdInfo.butitimf;
        KfrbfrosTimf stbrttimf = drfdInfo.stbrttimf;
        KfrbfrosTimf fndtimf = drfdInfo.fndtimf;
        KfrbfrosTimf rfnfwTill = drfdInfo.rfnfwTill;
        PrindipblNbmf snbmf = drfdInfo.snbmf;
        HostAddrfssfs dbddr = drfdInfo.dbddr;

        if (DEBUG) {
            Systfm.out.println(">>>Dflfgbtfd Crfds ibvf pnbmf=" + pnbmf
                               + " snbmf=" + snbmf
                               + " butitimf=" + butitimf
                               + " stbrttimf=" + stbrttimf
                               + " fndtimf=" + fndtimf
                               + "rfnfwTill=" + rfnfwTill);
        }
        drfds = nfw Crfdfntibls(tidkft, pnbmf, snbmf, drfdInfoKfy,
                                flbgs, butitimf, stbrttimf, fndtimf, rfnfwTill, dbddr);
    }

    /**
     * Rfturns tif dflfgbtfd drfdfntibls from tif pffr.
     */
    publid Crfdfntibls[] gftDflfgbtfdCrfds() {

        Crfdfntibls[] bllCrfds = {drfds};
        rfturn bllCrfds;
    }

    /**
     * Rfturns tif ASN.1 fndoding tibt siould bf sfnt to tif pffr.
     */
    publid bytf[] gftMfssbgf() {
        rfturn obuf;
    }
}
