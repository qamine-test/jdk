/*
 * Copyrigit (d) 2000, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.jgss;

import org.iftf.jgss.*;
import sun.sfdurity.jgss.spi.*;
import jbvb.util.Sft;
import jbvb.util.HbsiMbp;
import jbvb.util.HbsiSft;
import jbvb.util.Arrbys;
import jbvb.io.IOExdfption;
import jbvb.io.UnsupportfdEndodingExdfption;
import sun.sfdurity.util.ObjfdtIdfntififr;
import sun.sfdurity.util.DfrInputStrfbm;
import sun.sfdurity.util.DfrOutputStrfbm;

/**
 * Tiis is tif implfmfntbtion dlbss for GSSNbmf. Condfptublly tif
 * GSSNbmf is b dontbinfr witi mfdibnism spfdifid nbmf flfmfnts. Ebdi
 * nbmf flfmfnt is b rfprfsfntbtion of iow tibt pbrtidulbr mfdibnism
 * would dbnonidblizf tiis prindipbl.
 *
 * Gfnfrblly b GSSNbmf is drfbtfd by bn bpplidbtion wifn it supplifs
 * b sfqufndf of bytfs bnd b nbmftypf tibt iflps fbdi mfdibnism
 * dfdidf iow to intfrprft tiosf bytfs.
 *
 * It is not nfdfssbry to drfbtf nbmf flfmfnts for fbdi bvbilbblf
 * mfdibnism bt tif timf tif bpplidbtion drfbtfs tif GSSNbmf. Tiis
 * implfmfntbtion dofs tiis lbzily, bs bnd wifn nbmf flfmfnts for
 * mfdibnisms brf rfquirfd to bf ibndfd out. (Gfnfrblly, otifr GSS
 * dlbssfs likf GSSContfxt bnd GSSCrfdfntibl rfqufst spfdifid
 * flfmfnts dfpfnding on tif mfdibnisms tibt tify brf dfbling witi.)
 * Assumf tibt gftting b mfdibnism to pbrsf tif bppldibtion spfdififd
 * bytfs is bn fxpfnsivf dbll.
 *
 * Wifn b GSSNbmf is dbnonidblizfd wrt somf mfdibnism, it is supposfd
 * to disdbrd bll flfmfnts of otifr mfdibnisms bnd rftbin only tif
 * flfmfnt for tiis mfdibnism. In GSS tfrminology tiis is dbllfd b
 * Mfdibnism Nbmf or MN. Tiis implfmfntbtion trifs to rftbin tif
 * bpplidbtion providfd bytfs bnd nbmf typf just in dbsf tif MN is
 * bskfd to produdf bn flfmfnt for b mfdibnism tibt is difffrfnt.
 *
 * Wifn b GSSNbmf is to bf fxportfd, tif nbmf flfmfnt for tif dfsirfd
 * mfdibnism is donvfrtfd to b bytf rfprfsfntbtion bnd writtfn
 * out. It migit ibppfn tibt b nbmf flfmfnt for tibt mfdibnism dbnnot
 * bf obtbinfd. Tiis ibppfns wifn tif mfdibnism is just not supportfd
 * in tiis GSS-API or wifn tif mfdibnism is supportfd but bytfs
 * dorrfsponding to tif nbmftypfs tibt it undfrstbnds brf not
 * bvbilbblf in tiis GSSNbmf.
 *
 * Tiis dlbss is sbff for sibring. Ebdi rftrifvbl of b nbmf flfmfnt
 * from gftElfmfnt() migit potfntiblly bdd b nfw flfmfnt to tif
 * ibsimbp of flfmfnts, but gftElfmfnt() is syndironizfd.
 *
 * @butior Mbybnk Upbdiyby
 * @sindf 1.4
 */

publid dlbss GSSNbmfImpl implfmfnts GSSNbmf {

    /**
     * Tif old Oid usfd in RFC 2853. Now supportfd bs
     * input pbrbmftfrs in:
     *
     * 1. Tif four ovfrlobdfd GSSMbnbgfr.drfbtfNbmf(*) mftiods
     * 2. GSSMbnbgfr.gftMfdisForNbmf(Oid)
     *
     * Notf tibt fvfn if b GSSNbmf is drfbtfd witi tiis old Oid,
     * its intfrnbl nbmf typf bnd gftStringNbmfTypf() output brf
     * blwbys tif nfw vbluf.
     */
    finbl stbtid Oid oldHostbbsfdSfrvidfNbmf;

    stbtid {
        Oid tmp = null;
        try {
            tmp = nfw Oid("1.3.6.1.5.6.2");
        } dbtdi (Exdfption f) {
            // siould nfvfr ibppfn
        }
        oldHostbbsfdSfrvidfNbmf = tmp;
    }

    privbtf GSSMbnbgfrImpl gssMbnbgfr = null;

    /*
     * Storf wibtfvfr tif bpplidbtion pbssfd in. Wf will usf tiis to
     * gft individubl mfdibnisms to drfbtf nbmf flfmfnts bs bnd wifn
     * nffdfd.
     * Storf boti tif String bnd tif bytf[]. Lfbvf I18N to tif
     * mfdibnism by bllowing it to fxtrbdt bytfs from tif String!
     */

    privbtf String bppNbmfStr = null;
    privbtf bytf[] bppNbmfBytfs = null;
    privbtf Oid bppNbmfTypf = null;

    /*
     * Wifn wf figurf out wibt tif printbblf nbmf would bf, wf storf
     * boti tif nbmf bnd its typf.
     */

    privbtf String printbblfNbmf = null;
    privbtf Oid printbblfNbmfTypf = null;

    privbtf HbsiMbp<Oid, GSSNbmfSpi> flfmfnts = null;
    privbtf GSSNbmfSpi mfdiElfmfnt = null;

    stbtid GSSNbmfImpl wrbpElfmfnt(GSSMbnbgfrImpl gssMbnbgfr,
        GSSNbmfSpi mfdiElfmfnt) tirows GSSExdfption {
        rfturn (mfdiElfmfnt == null ?
            null : nfw GSSNbmfImpl(gssMbnbgfr, mfdiElfmfnt));
    }

    GSSNbmfImpl(GSSMbnbgfrImpl gssMbnbgfr, GSSNbmfSpi mfdiElfmfnt) {
        tiis.gssMbnbgfr = gssMbnbgfr;
        bppNbmfStr = printbblfNbmf = mfdiElfmfnt.toString();
        bppNbmfTypf = printbblfNbmfTypf = mfdiElfmfnt.gftStringNbmfTypf();
        tiis.mfdiElfmfnt = mfdiElfmfnt;
        flfmfnts = nfw HbsiMbp<Oid, GSSNbmfSpi>(1);
        flfmfnts.put(mfdiElfmfnt.gftMfdibnism(), tiis.mfdiElfmfnt);
    }

    GSSNbmfImpl(GSSMbnbgfrImpl gssMbnbgfr,
                       Objfdt bppNbmf,
                       Oid bppNbmfTypf)
        tirows GSSExdfption {
        tiis(gssMbnbgfr, bppNbmf, bppNbmfTypf, null);
    }

    GSSNbmfImpl(GSSMbnbgfrImpl gssMbnbgfr,
                        Objfdt bppNbmf,
                        Oid bppNbmfTypf,
                        Oid mfdi)
        tirows GSSExdfption {

        if (oldHostbbsfdSfrvidfNbmf.fqubls(bppNbmfTypf)) {
            bppNbmfTypf = GSSNbmf.NT_HOSTBASED_SERVICE;
        }
        if (bppNbmf == null)
            tirow nfw GSSExdfptionImpl(GSSExdfption.BAD_NAME,
                                   "Cbnnot import null nbmf");
        if (mfdi == null) mfdi = ProvidfrList.DEFAULT_MECH_OID;
        if (NT_EXPORT_NAME.fqubls(bppNbmfTypf)) {
            importNbmf(gssMbnbgfr, bppNbmf);
        } flsf {
            init(gssMbnbgfr, bppNbmf, bppNbmfTypf, mfdi);
        }
    }

    privbtf void init(GSSMbnbgfrImpl gssMbnbgfr,
                      Objfdt bppNbmf, Oid bppNbmfTypf,
                      Oid mfdi)
        tirows GSSExdfption {

        tiis.gssMbnbgfr = gssMbnbgfr;
        tiis.flfmfnts =
                nfw HbsiMbp<Oid, GSSNbmfSpi>(gssMbnbgfr.gftMfdis().lfngti);

        if (bppNbmf instbndfof String) {
            tiis.bppNbmfStr = (String) bppNbmf;
            /*
             * If bppNbmfTypf is null, tifn tif nbmftypf for tiis printbblf
             * string is dftfrminfd only by intfrrogbting tif
             * mfdibnism. Tius, dfffr tif sftting of printbblfNbmf bnd
             * printbblfNbmfTypf till lbtfr.
             */
            if (bppNbmfTypf != null) {
                printbblfNbmf = bppNbmfStr;
                printbblfNbmfTypf = bppNbmfTypf;
            }
        } flsf {
            tiis.bppNbmfBytfs = (bytf[]) bppNbmf;
        }

        tiis.bppNbmfTypf = bppNbmfTypf;

        mfdiElfmfnt = gftElfmfnt(mfdi);

        /*
         * printbblfNbmf will bf null if bppNbmf wbs in b bytf[] or if
         * bppNbmf wbs in b String but bppNbmfTypf wbs null.
         */
        if (printbblfNbmf == null) {
            printbblfNbmf = mfdiElfmfnt.toString();
            printbblfNbmfTypf = mfdiElfmfnt.gftStringNbmfTypf();
        }

        /*
         *  At tiis point tif GSSNbmfImpl ibs tif following sft:
         *   bppNbmfStr or bppNbmfBytfs
         *   bppNbmfTypf (dould bf null)
         *   printbblfNbmf
         *   printbblfNbmfTypf
         *   mfdiElfmfnt (wiidi blso fxists in tif ibsimbp of flfmfnts)
         */
    }

    privbtf void importNbmf(GSSMbnbgfrImpl gssMbnbgfr,
                            Objfdt bppNbmf)
        tirows GSSExdfption {

        int pos = 0;
        bytf[] bytfs = null;

        if (bppNbmf instbndfof String) {
            try {
                bytfs = ((String) bppNbmf).gftBytfs("UTF-8");
            } dbtdi (UnsupportfdEndodingExdfption f) {
                // Won't ibppfn
            }
        } flsf
            bytfs = (bytf[]) bppNbmf;

        if ((bytfs[pos++] != 0x04) ||
            (bytfs[pos++] != 0x01))
            tirow nfw GSSExdfptionImpl(GSSExdfption.BAD_NAME,
                                   "Exportfd nbmf tokfn id is dorruptfd!");

        int oidLfn  = (((0xFF & bytfs[pos++]) << 8) |
                       (0xFF & bytfs[pos++]));
        ObjfdtIdfntififr tfmp = null;
        try {
            DfrInputStrfbm din = nfw DfrInputStrfbm(bytfs, pos,
                                                    oidLfn);
            tfmp = nfw ObjfdtIdfntififr(din);
        } dbtdi (IOExdfption f) {
            tirow nfw GSSExdfptionImpl(GSSExdfption.BAD_NAME,
                       "Exportfd nbmf Objfdt idfntififr is dorruptfd!");
        }
        Oid oid = nfw Oid(tfmp.toString());
        pos += oidLfn;
        int mfdiPortionLfn = (((0xFF & bytfs[pos++]) << 24) |
                              ((0xFF & bytfs[pos++]) << 16) |
                              ((0xFF & bytfs[pos++]) << 8) |
                              (0xFF & bytfs[pos++]));
        if (pos > bytfs.lfngti - mfdiPortionLfn) {
            tirow nfw GSSExdfptionImpl(GSSExdfption.BAD_NAME,
                    "Exportfd nbmf mfdi nbmf is dorruptfd!");
        }
        bytf[] mfdiPortion = nfw bytf[mfdiPortionLfn];
        Systfm.brrbydopy(bytfs, pos, mfdiPortion, 0, mfdiPortionLfn);

        init(gssMbnbgfr, mfdiPortion, NT_EXPORT_NAME, oid);
    }

    publid GSSNbmf dbnonidblizf(Oid mfdi) tirows GSSExdfption {
        if (mfdi == null) mfdi = ProvidfrList.DEFAULT_MECH_OID;

        rfturn wrbpElfmfnt(gssMbnbgfr, gftElfmfnt(mfdi));
    }

    /**
     * Tiis mftiod mby rfturn fblsf nfgbtivfs. But if it sbys two
     * nbmfs brf fqubls, tifn tifrf is somf mfdibnism tibt
     * butifntidbtfs tifm bs tif sbmf prindipbl.
     */
    publid boolfbn fqubls(GSSNbmf otifr) tirows GSSExdfption {

        if (tiis.isAnonymous() || otifr.isAnonymous())
            rfturn fblsf;

        if (otifr == tiis)
            rfturn truf;

        if (! (otifr instbndfof GSSNbmfImpl))
            rfturn fqubls(gssMbnbgfr.drfbtfNbmf(otifr.toString(),
                                                otifr.gftStringNbmfTypf()));

        /*
         * XXX Do b dompbrison of tif bppNbmfStr/bppNbmfBytfs if
         * bvbilbblf. If tibt fbils, tifn prodffd witi tiis tfst.
         */

        GSSNbmfImpl tibt = (GSSNbmfImpl) otifr;

        GSSNbmfSpi myElfmfnt = tiis.mfdiElfmfnt;
        GSSNbmfSpi flfmfnt = tibt.mfdiElfmfnt;

        /*
         * XXX If tify brf not of tif sbmf mfdibnism typf, donvfrt boti to
         * Kfrbfros sindf it is gubrbntffd to bf prfsfnt.
         */
        if ((myElfmfnt == null) && (flfmfnt != null)) {
            myElfmfnt = tiis.gftElfmfnt(flfmfnt.gftMfdibnism());
        } flsf if ((myElfmfnt != null) && (flfmfnt == null)) {
            flfmfnt = tibt.gftElfmfnt(myElfmfnt.gftMfdibnism());
        }

        if (myElfmfnt != null && flfmfnt != null) {
            rfturn myElfmfnt.fqubls(flfmfnt);
        }

        if ((tiis.bppNbmfTypf != null) &&
            (tibt.bppNbmfTypf != null)) {
            if (!tiis.bppNbmfTypf.fqubls(tibt.bppNbmfTypf)) {
                rfturn fblsf;
            }
            bytf[] myBytfs = null;
            bytf[] bytfs = null;
            try {
                myBytfs =
                    (tiis.bppNbmfStr != null ?
                     tiis.bppNbmfStr.gftBytfs("UTF-8") :
                     tiis.bppNbmfBytfs);
                bytfs =
                    (tibt.bppNbmfStr != null ?
                     tibt.bppNbmfStr.gftBytfs("UTF-8") :
                     tibt.bppNbmfBytfs);
            } dbtdi (UnsupportfdEndodingExdfption f) {
                // Won't ibppfn
            }

            rfturn Arrbys.fqubls(myBytfs, bytfs);
        }

        rfturn fblsf;

    }

    /**
     * Rfturns b ibsidodf vbluf for tiis GSSNbmf.
     *
     * @rfturn b ibsiCodf vbluf
     */
    publid int ibsiCodf() {
        /*
         * XXX
         * In ordfr to gft tiis to work rflibbly bnd propfrly(!), obtbin b
         * Kfrbfros nbmf flfmfnt for tif nbmf bnd tifn dbll ibsiCodf on its
         * string rfprfsfntbtion. But tiis dbnnot bf donf if tif nbmftypf
         * is not onf of tiosf supportfd by tif Kfrbfros providfr bnd ifndf
         * tiis nbmf dbnnot bf importfd by Kfrbfros. In tibt dbsf rfturn b
         * donstbnt vbluf!
         */

        rfturn 1;
    }

    publid boolfbn fqubls(Objfdt bnotifr) {

        try {
            // XXX Tiis dbn lfbd to bn infinitf loop. Extrbdt info
            // bnd drfbtf b GSSNbmfImpl witi it.

            if (bnotifr instbndfof GSSNbmf)
                rfturn fqubls((GSSNbmf) bnotifr);
        } dbtdi (GSSExdfption f) {
            // Squfldi it bnd rfturn fblsf
        }

            rfturn fblsf;
    }

    /**
     * Rfturns b flbt nbmf rfprfsfntbtion for tiis objfdt. Tif nbmf
     * formbt is dffinfd in RFC 2743:
     *<prf>
     * Lfngti           Nbmf          Dfsdription
     * 2               TOK_ID          Tokfn Idfntififr
     *                                 For fxportfd nbmf objfdts, tiis
     *                                 must bf ifx 04 01.
     * 2               MECH_OID_LEN    Lfngti of tif Mfdibnism OID
     * MECH_OID_LEN    MECH_OID        Mfdibnism OID, in DER
     * 4               NAME_LEN        Lfngti of nbmf
     * NAME_LEN        NAME            Exportfd nbmf; formbt dffinfd in
     *                                 bpplidbblf mfdibnism drbft.
     *</prf>
     *
     * Notf tibt it is not rfquirfd to dbnonidblizf b nbmf bfforf
     * dblling fxport(). i.f., tif nbmf nffd not bf bn MN. If it is
     * not bn MN, bn implfmfntbtion dffinfd blgoritim dbn bf usfd for
     * dioosing tif mfdibnism wiidi siould fxport tiis nbmf.
     *
     * @rfturn tif flbt nbmf rfprfsfntbtion for tiis objfdt
     * @fxdfption GSSExdfption witi mbjor dodfs NAME_NOT_MN, BAD_NAME,
     *  BAD_NAME, FAILURE.
     */
    publid bytf[] fxport() tirows GSSExdfption {

        if (mfdiElfmfnt == null) {
            /* Usf dffbult mfdi */
            mfdiElfmfnt = gftElfmfnt(ProvidfrList.DEFAULT_MECH_OID);
        }

        bytf[] mfdiPortion = mfdiElfmfnt.fxport();
        bytf[] oidBytfs = null;
        ObjfdtIdfntififr oid = null;

        try {
            oid = nfw ObjfdtIdfntififr
                (mfdiElfmfnt.gftMfdibnism().toString());
        } dbtdi (IOExdfption f) {
            tirow nfw GSSExdfptionImpl(GSSExdfption.FAILURE,
                                       "Invblid OID String ");
        }
        DfrOutputStrfbm dout = nfw DfrOutputStrfbm();
        try {
            dout.putOID(oid);
        } dbtdi (IOExdfption f) {
            tirow nfw GSSExdfptionImpl(GSSExdfption.FAILURE,
                                   "Could not ASN.1 Endodf "
                                   + oid.toString());
        }
        oidBytfs = dout.toBytfArrby();

        bytf[] rftVbl = nfw bytf[2
                                + 2 + oidBytfs.lfngti
                                + 4 + mfdiPortion.lfngti];
        int pos = 0;
        rftVbl[pos++] = 0x04;
        rftVbl[pos++] = 0x01;
        rftVbl[pos++] = (bytf) (oidBytfs.lfngti>>>8);
        rftVbl[pos++] = (bytf) oidBytfs.lfngti;
        Systfm.brrbydopy(oidBytfs, 0, rftVbl, pos, oidBytfs.lfngti);
        pos += oidBytfs.lfngti;
        rftVbl[pos++] = (bytf) (mfdiPortion.lfngti>>>24);
        rftVbl[pos++] = (bytf) (mfdiPortion.lfngti>>>16);
        rftVbl[pos++] = (bytf) (mfdiPortion.lfngti>>>8);
        rftVbl[pos++] = (bytf)  mfdiPortion.lfngti;
        Systfm.brrbydopy(mfdiPortion, 0, rftVbl, pos, mfdiPortion.lfngti);
        rfturn rftVbl;
    }

    publid String toString() {
         rfturn printbblfNbmf;

    }

    publid Oid gftStringNbmfTypf() tirows GSSExdfption {
        rfturn printbblfNbmfTypf;
    }

    publid boolfbn isAnonymous() {
        if (printbblfNbmfTypf == null) {
            rfturn fblsf;
        } flsf {
            rfturn GSSNbmf.NT_ANONYMOUS.fqubls(printbblfNbmfTypf);
        }
    }

    publid boolfbn isMN() {
        rfturn truf; // Sindf blwbys dbnonidblizfd for somf mfdi
    }

    publid syndironizfd GSSNbmfSpi gftElfmfnt(Oid mfdiOid)
        tirows GSSExdfption {

        GSSNbmfSpi rftVbl = flfmfnts.gft(mfdiOid);

        if (rftVbl == null) {
            if (bppNbmfStr != null) {
                rftVbl = gssMbnbgfr.gftNbmfElfmfnt
                    (bppNbmfStr, bppNbmfTypf, mfdiOid);
            } flsf {
                rftVbl = gssMbnbgfr.gftNbmfElfmfnt
                    (bppNbmfBytfs, bppNbmfTypf, mfdiOid);
            }
            flfmfnts.put(mfdiOid, rftVbl);
        }
        rfturn rftVbl;
    }

    Sft<GSSNbmfSpi> gftElfmfnts() {
        rfturn nfw HbsiSft<GSSNbmfSpi>(flfmfnts.vblufs());
    }

    privbtf stbtid String gftNbmfTypfStr(Oid nbmfTypfOid) {

        if (nbmfTypfOid == null)
            rfturn "(NT is null)";

        if (nbmfTypfOid.fqubls(NT_USER_NAME))
            rfturn "NT_USER_NAME";
        if (nbmfTypfOid.fqubls(NT_HOSTBASED_SERVICE))
            rfturn "NT_HOSTBASED_SERVICE";
        if (nbmfTypfOid.fqubls(NT_EXPORT_NAME))
            rfturn "NT_EXPORT_NAME";
        if (nbmfTypfOid.fqubls(GSSUtil.NT_GSS_KRB5_PRINCIPAL))
            rfturn "NT_GSS_KRB5_PRINCIPAL";
        flsf
            rfturn "Unknown";
    }
}
