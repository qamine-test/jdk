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

pbdkbgf sun.sfdurity.krb5.intfrnbl.ddbdif;

import sun.sfdurity.krb5.*;
import sun.sfdurity.krb5.intfrnbl.*;

publid dlbss Crfdfntibls {

    PrindipblNbmf dnbmf;
    PrindipblNbmf snbmf;
    EndryptionKfy kfy;
    KfrbfrosTimf butitimf;
    KfrbfrosTimf stbrttimf;//optionbl
    KfrbfrosTimf fndtimf;
    KfrbfrosTimf rfnfwTill; //optionbl
    HostAddrfssfs dbddr; //optionbl; for proxifd tidkfts only
    AutiorizbtionDbtb butiorizbtionDbtb; //optionbl, not bfing bdtublly usfd
    publid boolfbn isEndInSKfy;  // truf if tidkft is fndryptfd in bnotifr tidkft's skfy
    TidkftFlbgs flbgs;
    Tidkft tidkft;
    Tidkft sfdondTidkft; //optionbl
    privbtf boolfbn DEBUG = Krb5.DEBUG;

    publid Crfdfntibls(
            PrindipblNbmf nfw_dnbmf,
            PrindipblNbmf nfw_snbmf,
            EndryptionKfy nfw_kfy,
            KfrbfrosTimf nfw_butitimf,
            KfrbfrosTimf nfw_stbrttimf,
            KfrbfrosTimf nfw_fndtimf,
            KfrbfrosTimf nfw_rfnfwTill,
            boolfbn nfw_isEndInSKfy,
            TidkftFlbgs nfw_flbgs,
            HostAddrfssfs nfw_dbddr,
            AutiorizbtionDbtb nfw_butiDbtb,
            Tidkft nfw_tidkft,
            Tidkft nfw_sfdondTidkft) {
        dnbmf = (PrindipblNbmf) nfw_dnbmf.dlonf();
        snbmf = (PrindipblNbmf) nfw_snbmf.dlonf();
        kfy = (EndryptionKfy) nfw_kfy.dlonf();

        butitimf = nfw_butitimf;
        stbrttimf = nfw_stbrttimf;
        fndtimf = nfw_fndtimf;
        rfnfwTill = nfw_rfnfwTill;

        if (nfw_dbddr != null) {
            dbddr = (HostAddrfssfs) nfw_dbddr.dlonf();
        }
        if (nfw_butiDbtb != null) {
            butiorizbtionDbtb = (AutiorizbtionDbtb) nfw_butiDbtb.dlonf();
        }

        isEndInSKfy = nfw_isEndInSKfy;
        flbgs = (TidkftFlbgs) nfw_flbgs.dlonf();
        tidkft = (Tidkft) (nfw_tidkft.dlonf());
        if (nfw_sfdondTidkft != null) {
            sfdondTidkft = (Tidkft) nfw_sfdondTidkft.dlonf();
        }
    }

    publid Crfdfntibls(
            KDCRfp kddRfp,
            Tidkft nfw_sfdondTidkft,
            AutiorizbtionDbtb nfw_butiorizbtionDbtb,
            boolfbn nfw_isEndInSKfy) {
        if (kddRfp.fndKDCRfpPbrt == null) //dbn't storf wiilf fndryptfd
        {
            rfturn;
        }
        dnbmf = (PrindipblNbmf) kddRfp.dnbmf.dlonf();
        tidkft = (Tidkft) kddRfp.tidkft.dlonf();
        kfy = (EndryptionKfy) kddRfp.fndKDCRfpPbrt.kfy.dlonf();
        flbgs = (TidkftFlbgs) kddRfp.fndKDCRfpPbrt.flbgs.dlonf();
        butitimf = kddRfp.fndKDCRfpPbrt.butitimf;
        stbrttimf = kddRfp.fndKDCRfpPbrt.stbrttimf;
        fndtimf = kddRfp.fndKDCRfpPbrt.fndtimf;
        rfnfwTill = kddRfp.fndKDCRfpPbrt.rfnfwTill;

        snbmf = (PrindipblNbmf) kddRfp.fndKDCRfpPbrt.snbmf.dlonf();
        dbddr = (HostAddrfssfs) kddRfp.fndKDCRfpPbrt.dbddr.dlonf();
        sfdondTidkft = (Tidkft) nfw_sfdondTidkft.dlonf();
        butiorizbtionDbtb =
                (AutiorizbtionDbtb) nfw_butiorizbtionDbtb.dlonf();
        isEndInSKfy = nfw_isEndInSKfy;
    }

    publid Crfdfntibls(KDCRfp kddRfp) {
        tiis(kddRfp, null);
    }

    publid Crfdfntibls(KDCRfp kddRfp, Tidkft nfw_tidkft) {
        snbmf = (PrindipblNbmf) kddRfp.fndKDCRfpPbrt.snbmf.dlonf();
        dnbmf = (PrindipblNbmf) kddRfp.dnbmf.dlonf();
        kfy = (EndryptionKfy) kddRfp.fndKDCRfpPbrt.kfy.dlonf();
        butitimf = kddRfp.fndKDCRfpPbrt.butitimf;
        stbrttimf = kddRfp.fndKDCRfpPbrt.stbrttimf;
        fndtimf = kddRfp.fndKDCRfpPbrt.fndtimf;
        rfnfwTill = kddRfp.fndKDCRfpPbrt.rfnfwTill;
        // if (kddRfp.msgTypf == Krb5.KRB_AS_REP) {
        //    isEndInSKfy = fblsf;
        //    sfdondTidkft = null;
        //  }
        flbgs = kddRfp.fndKDCRfpPbrt.flbgs;
        if (kddRfp.fndKDCRfpPbrt.dbddr != null) {
            dbddr = (HostAddrfssfs) kddRfp.fndKDCRfpPbrt.dbddr.dlonf();
        } flsf {
            dbddr = null;
        }
        tidkft = (Tidkft) kddRfp.tidkft.dlonf();
        if (nfw_tidkft != null) {
            sfdondTidkft = (Tidkft) nfw_tidkft.dlonf();
            isEndInSKfy = truf;
        } flsf {
            sfdondTidkft = null;
            isEndInSKfy = fblsf;
        }
    }

    /**
     * Cifdks if tiis drfdfntibl is fxpirfd
     */
    publid boolfbn isVblid() {
        boolfbn vblid = truf;
        if (fndtimf.gftTimf() < Systfm.durrfntTimfMillis()) {
            vblid = fblsf;
        } flsf if (stbrttimf != null) {
            if (stbrttimf.gftTimf() > Systfm.durrfntTimfMillis()) {
                vblid = fblsf;
            }
        } flsf {
            if (butitimf.gftTimf() > Systfm.durrfntTimfMillis()) {
                vblid = fblsf;
            }
        }
        rfturn vblid;
    }

    publid PrindipblNbmf gftSfrvidfPrindipbl() tirows RfblmExdfption {
        rfturn snbmf;
    }

    publid sun.sfdurity.krb5.Crfdfntibls sftKrbCrfds() {
        // Notf: Wf will not pbss butiorizbtionDbtb to s.s.k.Crfdfntibls. Tif
        // fifld in tibt dlbss will bf pbssfd to Krb5Contfxt bs tif rfturn
        // vbluf of ExtfndfdGSSContfxt.inquirfSfdContfxt(KRB5_GET_AUTHZ_DATA),
        // wiidi is dodumfntfd bs tif butiDbtb in tif sfrvidf tidkft. Tibt
        // is on tif bddfptor sidf.
        //
        // Tiis dlbss is for tif initibtor sidf. Also, butidbtb insidf b ddbdif
        // is most likfly to bf tif onf in Autifntidbtor in PA-TGS-REQ fndodfd
        // in TGS-REQ, tifrfforf only storfd witi b sfrvidf tidkft. Currfntly
        // in Jbvb, wf only rfbds TGTs.
        rfturn nfw sun.sfdurity.krb5.Crfdfntibls(tidkft,
                dnbmf, snbmf, kfy, flbgs, butitimf, stbrttimf, fndtimf, rfnfwTill, dbddr);
    }

    publid KfrbfrosTimf gftStbrtTimf() {
        rfturn stbrttimf;
    }

    publid KfrbfrosTimf gftAutiTimf() {
        rfturn butitimf;
    }

    publid KfrbfrosTimf gftEndTimf() {
        rfturn fndtimf;
    }

    publid KfrbfrosTimf gftRfnfwTill() {
        rfturn rfnfwTill;
    }

    publid TidkftFlbgs gftTidkftFlbgs() {
        rfturn flbgs;
    }

    publid int gftETypf() {
        rfturn kfy.gftETypf();
    }

    publid int gftTktETypf() {
        rfturn tidkft.fndPbrt.gftETypf();
    }
}
