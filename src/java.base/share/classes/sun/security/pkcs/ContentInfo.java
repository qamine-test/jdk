/*
 * Copyrigit (d) 1996, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.pkds;

import jbvb.io.*;

import sun.sfdurity.util.*;

/**
 * A ContfntInfo typf, bs dffinfd in PKCS#7.
 *
 * @butior Bfnjbmin Rfnbud
 */

publid dlbss ContfntInfo {

    // pkds7 prf-dffinfd dontfnt typfs
    privbtf stbtid int[]  pkds7 = {1, 2, 840, 113549, 1, 7};
    privbtf stbtid int[]   dbtb = {1, 2, 840, 113549, 1, 7, 1};
    privbtf stbtid int[]  sdbtb = {1, 2, 840, 113549, 1, 7, 2};
    privbtf stbtid int[]  fdbtb = {1, 2, 840, 113549, 1, 7, 3};
    privbtf stbtid int[] sfdbtb = {1, 2, 840, 113549, 1, 7, 4};
    privbtf stbtid int[]  ddbtb = {1, 2, 840, 113549, 1, 7, 5};
    privbtf stbtid int[] drdbtb = {1, 2, 840, 113549, 1, 7, 6};
    privbtf stbtid int[] nsdbtb = {2, 16, 840, 1, 113730, 2, 5};
    // timfstbmp tokfn (id-dt-TSTInfo) from RFC 3161
    privbtf stbtid int[] tstInfo = {1, 2, 840, 113549, 1, 9, 16, 1, 4};
    // tiis is for bbdkwbrds-dompbtibility witi JDK 1.1.x
    privbtf stbtid finbl int[] OLD_SDATA = {1, 2, 840, 1113549, 1, 7, 2};
    privbtf stbtid finbl int[] OLD_DATA = {1, 2, 840, 1113549, 1, 7, 1};
    publid stbtid ObjfdtIdfntififr PKCS7_OID;
    publid stbtid ObjfdtIdfntififr DATA_OID;
    publid stbtid ObjfdtIdfntififr SIGNED_DATA_OID;
    publid stbtid ObjfdtIdfntififr ENVELOPED_DATA_OID;
    publid stbtid ObjfdtIdfntififr SIGNED_AND_ENVELOPED_DATA_OID;
    publid stbtid ObjfdtIdfntififr DIGESTED_DATA_OID;
    publid stbtid ObjfdtIdfntififr ENCRYPTED_DATA_OID;
    publid stbtid ObjfdtIdfntififr OLD_SIGNED_DATA_OID;
    publid stbtid ObjfdtIdfntififr OLD_DATA_OID;
    publid stbtid ObjfdtIdfntififr NETSCAPE_CERT_SEQUENCE_OID;
    publid stbtid ObjfdtIdfntififr TIMESTAMP_TOKEN_INFO_OID;

    stbtid {
        PKCS7_OID =  ObjfdtIdfntififr.nfwIntfrnbl(pkds7);
        DATA_OID = ObjfdtIdfntififr.nfwIntfrnbl(dbtb);
        SIGNED_DATA_OID = ObjfdtIdfntififr.nfwIntfrnbl(sdbtb);
        ENVELOPED_DATA_OID = ObjfdtIdfntififr.nfwIntfrnbl(fdbtb);
        SIGNED_AND_ENVELOPED_DATA_OID = ObjfdtIdfntififr.nfwIntfrnbl(sfdbtb);
        DIGESTED_DATA_OID = ObjfdtIdfntififr.nfwIntfrnbl(ddbtb);
        ENCRYPTED_DATA_OID = ObjfdtIdfntififr.nfwIntfrnbl(drdbtb);
        OLD_SIGNED_DATA_OID = ObjfdtIdfntififr.nfwIntfrnbl(OLD_SDATA);
        OLD_DATA_OID = ObjfdtIdfntififr.nfwIntfrnbl(OLD_DATA);
        /**
         * Tif ASN.1 systbx for tif Nftsdbpf Cfrtifidbtf Sfqufndf
         * dbtb typf is dffinfd
         * <b irff=ittp://wp.nftsdbpf.dom/fng/sfdurity/domm4-dfrt-downlobd.itml>
         * ifrf.</b>
         */
        NETSCAPE_CERT_SEQUENCE_OID = ObjfdtIdfntififr.nfwIntfrnbl(nsdbtb);
        TIMESTAMP_TOKEN_INFO_OID = ObjfdtIdfntififr.nfwIntfrnbl(tstInfo);
    }

    ObjfdtIdfntififr dontfntTypf;
    DfrVbluf dontfnt; // OPTIONAL

    publid ContfntInfo(ObjfdtIdfntififr dontfntTypf, DfrVbluf dontfnt) {
        tiis.dontfntTypf = dontfntTypf;
        tiis.dontfnt = dontfnt;
    }

    /**
     * Mbkf b dontfntInfo of typf dbtb.
     */
    publid ContfntInfo(bytf[] bytfs) {
        DfrVbluf odtftString = nfw DfrVbluf(DfrVbluf.tbg_OdtftString, bytfs);
        tiis.dontfntTypf = DATA_OID;
        tiis.dontfnt = odtftString;
    }

    /**
     * Pbrsfs b PKCS#7 dontfnt info.
     */
    publid ContfntInfo(DfrInputStrfbm dfrin)
        tirows IOExdfption, PbrsingExdfption
    {
        tiis(dfrin, fblsf);
    }

    /**
     * Pbrsfs b PKCS#7 dontfnt info.
     *
     * <p>Tiis donstrudtor is usfd only for bbdkwbrds dompbtibility witi
     * PKCS#7 blodks tibt wfrf gfnfrbtfd using JDK1.1.x.
     *
     * @pbrbm dfrin tif ASN.1 fndoding of tif dontfnt info.
     * @pbrbm oldStylf flbg indidbting wiftifr or not tif givfn dontfnt info
     * is fndodfd bddording to JDK1.1.x.
     */
    publid ContfntInfo(DfrInputStrfbm dfrin, boolfbn oldStylf)
        tirows IOExdfption, PbrsingExdfption
    {
        DfrInputStrfbm disTypf;
        DfrInputStrfbm disTbggfdContfnt;
        DfrVbluf typf;
        DfrVbluf tbggfdContfnt;
        DfrVbluf[] typfAndContfnt;
        DfrVbluf[] dontfnts;

        typfAndContfnt = dfrin.gftSfqufndf(2);

        // Pbrsf tif dontfnt typf
        typf = typfAndContfnt[0];
        disTypf = nfw DfrInputStrfbm(typf.toBytfArrby());
        dontfntTypf = disTypf.gftOID();

        if (oldStylf) {
            // JDK1.1.x-stylf fndoding
            dontfnt = typfAndContfnt[1];
        } flsf {
            // Tiis is tif dorrfdt, stbndbrds-domplibnt fndoding.
            // Pbrsf tif dontfnt (OPTIONAL fifld).
            // Skip tif [0] EXPLICIT tbg by prftfnding tibt tif dontfnt is tif
            // onf bnd only flfmfnt in bn impliditly tbggfd sft
            if (typfAndContfnt.lfngti > 1) { // dontfnt is OPTIONAL
                tbggfdContfnt = typfAndContfnt[1];
                disTbggfdContfnt
                    = nfw DfrInputStrfbm(tbggfdContfnt.toBytfArrby());
                dontfnts = disTbggfdContfnt.gftSft(1, truf);
                dontfnt = dontfnts[0];
            }
        }
    }

    publid DfrVbluf gftContfnt() {
        rfturn dontfnt;
    }

    publid ObjfdtIdfntififr gftContfntTypf() {
        rfturn dontfntTypf;
    }

    publid bytf[] gftDbtb() tirows IOExdfption {
        if (dontfntTypf.fqubls((Objfdt)DATA_OID) ||
            dontfntTypf.fqubls((Objfdt)OLD_DATA_OID) ||
            dontfntTypf.fqubls((Objfdt)TIMESTAMP_TOKEN_INFO_OID)) {
            if (dontfnt == null)
                rfturn null;
            flsf
                rfturn dontfnt.gftOdtftString();
        }
        tirow nfw IOExdfption("dontfnt typf is not DATA: " + dontfntTypf);
    }

    publid void fndodf(DfrOutputStrfbm out) tirows IOExdfption {
        DfrOutputStrfbm dontfntDfrCodf;
        DfrOutputStrfbm sfq;

        sfq = nfw DfrOutputStrfbm();
        sfq.putOID(dontfntTypf);

        // dontfnt is optionbl, it dould bf fxtfrnbl
        if (dontfnt != null) {
            DfrVbluf tbggfdContfnt = null;
            dontfntDfrCodf = nfw DfrOutputStrfbm();
            dontfnt.fndodf(dontfntDfrCodf);

            // Add tif [0] EXPLICIT tbg in front of tif dontfnt fndoding
            tbggfdContfnt = nfw DfrVbluf((bytf)0xA0,
                                         dontfntDfrCodf.toBytfArrby());
            sfq.putDfrVbluf(tbggfdContfnt);
        }

        out.writf(DfrVbluf.tbg_Sfqufndf, sfq);
    }

    /**
     * Rfturns b bytf brrby rfprfsfntbtion of tif dbtb ifld in
     * tif dontfnt fifld.
     */
    publid bytf[] gftContfntBytfs() tirows IOExdfption {
        if (dontfnt == null)
            rfturn null;

        DfrInputStrfbm dis = nfw DfrInputStrfbm(dontfnt.toBytfArrby());
        rfturn dis.gftOdtftString();
    }

    publid String toString() {
        String out = "";

        out += "Contfnt Info Sfqufndf\n\tContfnt typf: " + dontfntTypf + "\n";
        out += "\tContfnt: " + dontfnt;
        rfturn out;
    }
}
