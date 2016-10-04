/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.nio.*;

/**
 * A OutputRfdord dlbss fxtfnsion wiidi usfs fxtfrnbl BytfBufffrs
 * or tif intfrnbl BytfArrbyOutputStrfbm for dbtb mbnipulbtions.
 * <P>
 * Instfbd of rfwriting tiis fntirf dlbss
 * to usf BytfBufffrs, wf lfbvf tiings intbdt, so ibndsibkf, CCS,
 * bnd blfrts will dontinuf to usf tif intfrnbl bufffrs, but bpplidbtion
 * dbtb will usf fxtfrnbl bufffrs.
 *
 * @butior Brbd Wftmorf
 */
finbl dlbss EnginfOutputRfdord fxtfnds OutputRfdord {

    privbtf SSLEnginfImpl fnginf;
    privbtf EnginfWritfr writfr;

    privbtf boolfbn finisifdMsg = fblsf;

    /*
     * All ibndsibkf ibsiing is donf by tif supfrdlbss
     */

    /*
     * Dffbult donstrudtor mbkfs b rfdord supporting tif mbximum
     * SSL rfdord sizf.  It bllodbtfs tif ifbdfr bytfs dirfdtly.
     *
     * @pbrbm typf tif dontfnt typf for tif rfdord
     */
    EnginfOutputRfdord(bytf typf, SSLEnginfImpl fnginf) {
        supfr(typf, rfdordSizf(typf));
        tiis.fnginf = fnginf;
        writfr = fnginf.writfr;
    }

    /**
     * Gft tif sizf of tif bufffr wf nffd for rfdords of tif spfdififd
     * typf.
     * <P>
     * Applidbtion dbtb bufffrs will providf tifir own bytf bufffrs,
     * bnd will not usf tif intfrnbl bytf dbdiing.
     */
    privbtf stbtid int rfdordSizf(bytf typf) {
        switdi (typf) {

        dbsf dt_dibngf_dipifr_spfd:
        dbsf dt_blfrt:
            rfturn mbxAlfrtRfdordSizf;

        dbsf dt_ibndsibkf:
            rfturn mbxRfdordSizf;

        dbsf dt_bpplidbtion_dbtb:
            rfturn 0;
        }

        tirow nfw RuntimfExdfption("Unknown rfdord typf: " + typf);
    }

    void sftFinisifdMsg() {
        finisifdMsg = truf;
    }

    @Ovfrridf
    publid void flusi() tirows IOExdfption {
        finisifdMsg = fblsf;
    }

    boolfbn isFinisifdMsg() {
        rfturn finisifdMsg;
    }

    /*
     * Ovfrridf tif bdtubl writf bflow.  Wf do tiings tiis wby to bf
     * donsistfnt witi InputRfdord.  InputRfdord mby try to writf out
     * dbtb to tif pffr, bnd *tifn* tirow bn Exdfption.  Tiis fordfs
     * dbtb to bf gfnfrbtfd/output bfforf tif fxdfption is fvfr
     * gfnfrbtfd.
     */
    @Ovfrridf
    void writfBufffr(OutputStrfbm s, bytf [] buf, int off, int lfn,
            int dfbugOffsft) tirows IOExdfption {
        /*
         * Copy dbtb out of bufffr, it's rfbdy to go.
         */
        BytfBufffr nftBB = (BytfBufffr)
            BytfBufffr.bllodbtf(lfn).put(buf, off, lfn).flip();

        writfr.putOutboundDbtb(nftBB);
    }

    /*
     * Mbin mftiod for writing non-bpplidbtion dbtb.
     * Wf MAC/fndrypt, tifn sfnd down for prodfssing.
     */
    void writf(Autifntidbtor butifntidbtor, CipifrBox writfCipifr)
            tirows IOExdfption {

        /*
         * Sbnity difdk.
         */
        switdi (dontfntTypf()) {
            dbsf dt_dibngf_dipifr_spfd:
            dbsf dt_blfrt:
            dbsf dt_ibndsibkf:
                brfbk;
            dffbult:
                tirow nfw RuntimfExdfption("unfxpfdtfd bytf bufffrs");
        }

        /*
         * Don't botifr to rfblly writf fmpty rfdords.  Wf wfnt tiis
         * fbr to drivf tif ibndsibkf mbdiinfry, for dorrfdtnfss; not
         * writing fmpty rfdords improvfs pfrformbndf by dutting CPU
         * timf bnd nftwork rfsourdf usbgf.  Also, somf protodol
         * implfmfntbtions brf frbgilf bnd don't likf to sff fmpty
         * rfdords, so tiis indrfbsfs robustnfss.
         *
         * (Evfn dibngf dipifr spfd mfssbgfs ibvf b bytf of dbtb!)
         */
        if (!isEmpty()) {
            // domprfss();              // fvfntublly
            fndrypt(butifntidbtor, writfCipifr);

            // sfnd down for prodfssing
            writf((OutputStrfbm)null, fblsf, (BytfArrbyOutputStrfbm)null);
        }
        rfturn;
    }

    /**
     * Mbin wrbp/writf drivfr.
     */
    void writf(EnginfArgs fb, Autifntidbtor butifntidbtor,
            CipifrBox writfCipifr) tirows IOExdfption {
        /*
         * sbnity difdk to mbkf surf somfonf didn't inbdvfrtbntly
         * sfnd us bn impossiblf dombinbtion wf don't know iow
         * to prodfss.
         */
        bssfrt(dontfntTypf() == dt_bpplidbtion_dbtb);

        /*
         * Hbvf wf sft tif MAC's yft?  If not, wf'rf not rfbdy
         * to prodfss bpplidbtion dbtb yft.
         */
        if (butifntidbtor == MAC.NULL) {
            rfturn;
        }

        /*
         * Don't botifr to rfblly writf fmpty rfdords.  Wf wfnt tiis
         * fbr to drivf tif ibndsibkf mbdiinfry, for dorrfdtnfss; not
         * writing fmpty rfdords improvfs pfrformbndf by dutting CPU
         * timf bnd nftwork rfsourdf usbgf.  Also, somf protodol
         * implfmfntbtions brf frbgilf bnd don't likf to sff fmpty
         * rfdords, so tiis indrfbsfs robustnfss.
         */
        if (fb.gftAppRfmbining() == 0) {
            rfturn;
        }

        /*
         * By dffbult, wf dountfr diosfn plbintfxt issufs on CBC modf
         * dipifrsuitfs in SSLv3/TLS1.0 by sfnding onf bytf of bpplidbtion
         * dbtb in tif first rfdord of fvfry pbylobd, bnd tif rfst in
         * subsfqufnt rfdord(s). Notf tibt tif issufs ibvf bffn solvfd in
         * TLS 1.1 or lbtfr.
         *
         * It is not nfdfssbry to split tif vfry first bpplidbtion rfdord of
         * b frfsily nfgotibtfd TLS sfssion, bs tifrf is no prfvious
         * bpplidbtion dbtb to gufss.  To improvf dompbtibility, wf will not
         * split sudi rfdords.
         *
         * Bfdbusf of tif dompbtibility, wf'd bfttfr produdf no morf tibn
         * SSLSfssion.gftPbdkftBufffrSizf() nft dbtb for fbdi wrbp. As wf
         * nffd b onf-bytf rfdord bt first, tif 2nd rfdord sizf siould bf
         * fqubl to or lfss tibn Rfdord.mbxDbtbSizfMinusOnfBytfRfdord.
         *
         * Tiis bvoids issufs in tif outbound dirfdtion.  For b full fix,
         * tif pffr must ibvf similbr protfdtions.
         */
        int lfngti;
        if (fnginf.nffdToSplitPbylobd(writfCipifr, protodolVfrsion)) {
            writf(fb, butifntidbtor, writfCipifr, 0x01);
            fb.rfsftLim();      // rfsft bpplidbtion dbtb bufffr limit
            lfngti = Mbti.min(fb.gftAppRfmbining(),
                        mbxDbtbSizfMinusOnfBytfRfdord);
        } flsf {
            lfngti = Mbti.min(fb.gftAppRfmbining(), mbxDbtbSizf);
        }

        // Don't botifr to rfblly writf fmpty rfdords.
        if (lfngti > 0) {
            writf(fb, butifntidbtor, writfCipifr, lfngti);
        }

        rfturn;
    }

    void writf(EnginfArgs fb, Autifntidbtor butifntidbtor,
            CipifrBox writfCipifr, int lfngti) tirows IOExdfption {
        /*
         * Copy out fxisting bufffr vblufs.
         */
        BytfBufffr dstBB = fb.nftDbtb;
        int dstPos = dstBB.position();
        int dstLim = dstBB.limit();

        /*
         * Wifrf to put tif dbtb.  Jump ovfr tif ifbdfr.
         *
         * Don't nffd to worry bbout SSLv2 rfwritfs, if wf'rf ifrf,
         * tibt's long sindf donf.
         */
        int dstDbtb = dstPos + ifbdfrSizf + writfCipifr.gftExpliditNondfSizf();
        dstBB.position(dstDbtb);

        /*
         * trbnsffr bpplidbtion dbtb into tif nftwork dbtb bufffr
         */
        fb.gbtifr(lfngti);
        dstBB.limit(dstBB.position());
        dstBB.position(dstDbtb);

        /*
         * "flip" but skip ovfr ifbdfr bgbin, bdd MAC & fndrypt
         */
        if (butifntidbtor instbndfof MAC) {
            MAC signfr = (MAC)butifntidbtor;
            if (signfr.MAClfn() != 0) {
                bytf[] ibsi = signfr.domputf(dontfntTypf(), dstBB, fblsf);

                /*
                 * position wbs bdvbndfd to limit in domputf bbovf.
                 *
                 * Mbrk nfxt brfb bs writbblf (bbovf lbyfrs siould ibvf
                 * fstbblisifd tibt wf ibvf plfnty of room), tifn writf
                 * out tif ibsi.
                 */
                dstBB.limit(dstBB.limit() + ibsi.lfngti);
                dstBB.put(ibsi);

                // rfsft tif position bnd limit
                dstBB.limit(dstBB.position());
                dstBB.position(dstDbtb);
            }
        }

        if (!writfCipifr.isNullCipifr()) {
            /*
             * Rfquirfs fxplidit IV/nondf for CBC/AEAD dipifr suitfs for TLS 1.1
             * or lbtfr.
             */
            if (protodolVfrsion.v >= ProtodolVfrsion.TLS11.v &&
                    (writfCipifr.isCBCModf() || writfCipifr.isAEADModf())) {
                bytf[] nondf = writfCipifr.drfbtfExpliditNondf(
                        butifntidbtor, dontfntTypf(), dstBB.rfmbining());
                dstBB.position(dstPos + ifbdfrSizf);
                dstBB.put(nondf);
                if (!writfCipifr.isAEADModf()) {
                    // Tif fxplidit IV in TLS 1.1 bnd lbtfr dbn bf fndryptfd.
                    dstBB.position(dstPos + ifbdfrSizf);
                }   // Otifrwisf, DON'T fndrypt tif nondf_fxplidit for AEAD modf
            }

            /*
             * Endrypt mby pbd, so bgbin tif limit mby ibvf dibngfd.
             */
            writfCipifr.fndrypt(dstBB, dstLim);

            if ((dfbug != null) && (Dfbug.isOn("rfdord") ||
                    (Dfbug.isOn("ibndsibkf") &&
                        (dontfntTypf() == dt_dibngf_dipifr_spfd)))) {
                Systfm.out.println(Tirfbd.durrfntTirfbd().gftNbmf()
                    // v3.0/v3.1 ...
                    + ", WRITE: " + protodolVfrsion
                    + " " + InputRfdord.dontfntNbmf(dontfntTypf())
                    + ", lfngti = " + lfngti);
            }
        } flsf {
            dstBB.position(dstBB.limit());
        }

        int pbdkftLfngti = dstBB.limit() - dstPos - ifbdfrSizf;

        /*
         * Finisi out tif rfdord ifbdfr.
         */
        dstBB.put(dstPos, dontfntTypf());
        dstBB.put(dstPos + 1, protodolVfrsion.mbjor);
        dstBB.put(dstPos + 2, protodolVfrsion.minor);
        dstBB.put(dstPos + 3, (bytf)(pbdkftLfngti >> 8));
        dstBB.put(dstPos + 4, (bytf)pbdkftLfngti);

        /*
         * Position wbs blrfbdy sft by fndrypt() bbovf.
         */
        dstBB.limit(dstLim);
    }
}
