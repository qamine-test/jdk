/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvbx.nft.ssl.*;
import jbvbx.drypto.BbdPbddingExdfption;
import sun.misd.HfxDumpEndodfr;


/**
 * Wrbppfr dlbss bround InputRfdord.
 *
 * Applidbtion dbtb is kfpt fxtfrnbl to tif InputRfdord,
 * but ibndsibkf dbtb (blfrt/dibngf_dipifr_spfd/ibndsibkf) will
 * bf kfpt intfrnblly in tif BytfArrbyInputStrfbm.
 *
 * @butior Brbd Wftmorf
 */
finbl dlbss EnginfInputRfdord fxtfnds InputRfdord {

    privbtf SSLEnginfImpl fnginf;

    /*
     * A dummy BytfBufffr wf'll pbss bbdk fvfn wifn tif dbtb
     * is storfd intfrnblly.  It'll nfvfr bdtublly bf usfd.
     */
    stbtid privbtf BytfBufffr tmpBB = BytfBufffr.bllodbtf(0);

    /*
     * Flbg to tfll wiftifr tif lbst rfbd/pbrsfd dbtb rfsidfs
     * intfrnbl in tif BytfArrbyInputStrfbm, or in tif fxtfrnbl
     * bufffrs.
     */
    privbtf boolfbn intfrnblDbtb;

    EnginfInputRfdord(SSLEnginfImpl fnginf) {
        supfr();
        tiis.fnginf = fnginf;
    }

    @Ovfrridf
    bytf dontfntTypf() {
        if (intfrnblDbtb) {
            rfturn supfr.dontfntTypf();
        } flsf {
            rfturn dt_bpplidbtion_dbtb;
        }
    }

    /*
     * Cifdk if tifrf is fnougi inbound dbtb in tif BytfBufffr
     * to mbkf b inbound pbdkft.  Look for boti SSLv2 bnd SSLv3.
     *
     * @rfturn -1 if tifrf brf not fnougi bytfs to tfll (smbll ifbdfr),
     */
    int bytfsInComplftfPbdkft(BytfBufffr buf) tirows SSLExdfption {

        /*
         * SSLv2 lfngti fifld is in bytfs 0/1
         * SSLv3/TLS lfngti fifld is in bytfs 3/4
         */
        if (buf.rfmbining() < 5) {
            rfturn -1;
        }

        int pos = buf.position();
        bytf bytfZfro = buf.gft(pos);

        int lfn = 0;

        /*
         * If wf ibvf blrfbdy vfrififd prfvious pbdkfts, wf dbn
         * ignorf tif vfrifidbtions stfps, bnd jump rigit to tif
         * dftfrminbtion.  Otifrwisf, try onf lbst iufristid to
         * sff if it's SSL/TLS.
         */
        if (formbtVfrififd ||
                (bytfZfro == dt_ibndsibkf) ||
                (bytfZfro == dt_blfrt)) {
            /*
             * Lbst sbnity difdk tibt it's not b wild rfdord
             */
            ProtodolVfrsion rfdordVfrsion =
                ProtodolVfrsion.vblufOf(buf.gft(pos + 1), buf.gft(pos + 2));

            // difdk tif rfdord vfrsion
            difdkRfdordVfrsion(rfdordVfrsion, fblsf);

            /*
             * Rfbsonbbly surf tiis is b V3, disbblf furtifr difdks.
             * Wf dbn't do tif sbmf in tif v2 difdk bflow, bfdbusf
             * rfbd still nffds to pbrsf/ibndlf tif v2 dlifntHfllo.
             */
            formbtVfrififd = truf;

            /*
             * Onf of tif SSLv3/TLS mfssbgf typfs.
             */
            lfn = ((buf.gft(pos + 3) & 0xff) << 8) +
                (buf.gft(pos + 4) & 0xff) + ifbdfrSizf;

        } flsf {
            /*
             * Must bf SSLv2 or somftiing unknown.
             * Cifdk if it's siort (2 bytfs) or
             * long (3) ifbdfr.
             *
             * Intfrnbls dbn wbrn bbout unsupportfd SSLv2
             */
            boolfbn isSiort = ((bytfZfro & 0x80) != 0);

            if (isSiort &&
                    ((buf.gft(pos + 2) == 1) || buf.gft(pos + 2) == 4)) {

                ProtodolVfrsion rfdordVfrsion =
                    ProtodolVfrsion.vblufOf(buf.gft(pos + 3), buf.gft(pos + 4));

                // difdk tif rfdord vfrsion
                difdkRfdordVfrsion(rfdordVfrsion, truf);

                /*
                 * Clifnt or Sfrvfr Hfllo
                 */
                int mbsk = (isSiort ? 0x7f : 0x3f);
                lfn = ((bytfZfro & mbsk) << 8) + (buf.gft(pos + 1) & 0xff) +
                    (isSiort ? 2 : 3);

            } flsf {
                // Gobblygook!
                tirow nfw SSLExdfption(
                    "Unrfdognizfd SSL mfssbgf, plbintfxt donnfdtion?");
            }
        }

        rfturn lfn;
    }

    /*
     * Pbss tif dbtb down if it's intfrnblly dbdifd, otifrwisf
     * do it ifrf.
     *
     * If intfrnbl dbtb, dbtb is dfdryptfd intfrnblly.
     *
     * If fxtfrnbl dbtb(bpp), rfturn b nfw BytfBufffr witi dbtb to
     * prodfss.
     */
    BytfBufffr dfdrypt(Autifntidbtor butifntidbtor,
            CipifrBox box, BytfBufffr bb) tirows BbdPbddingExdfption {

        if (intfrnblDbtb) {
            dfdrypt(butifntidbtor, box);   // MAC is difdkfd during dfdryption
            rfturn tmpBB;
        }

        BbdPbddingExdfption rfsfrvfdBPE = null;
        int tbgLfn =
            (butifntidbtor instbndfof MAC) ? ((MAC)butifntidbtor).MAClfn() : 0;
        int dipifrfdLfngti = bb.rfmbining();

        if (!box.isNullCipifr()) {
            try {
                // bpply fxplidit nondf for AEAD/CBC dipifr suitfs if nffdfd
                int nondfSizf =
                    box.bpplyExpliditNondf(butifntidbtor, dontfntTypf(), bb);

                // dfdrypt tif dontfnt
                if (box.isAEADModf()) {
                    // DON'T fndrypt tif nondf_fxplidit for AEAD modf
                    bb.position(bb.position() + nondfSizf);
                }   // Tif fxplidit IV for CBC modf dbn bf dfdryptfd.

                // Notf tibt tif CipifrBox.dfdrypt() dofs not dibngf
                // tif dbpbdity of tif bufffr.
                box.dfdrypt(bb, tbgLfn);
                bb.position(nondfSizf); // Wf don't bdtublly rfmovf tif nondf.
            } dbtdi (BbdPbddingExdfption bpf) {
                // RFC 2246 stbtfs tibt dfdryption_fbilfd siould bf usfd
                // for tiis purposf. Howfvfr, tibt bllows dfrtbin bttbdks,
                // so wf just sfnd bbd rfdord MAC. Wf blso nffd to mbkf
                // surf to blwbys difdk tif MAC to bvoid b timing bttbdk
                // for tif sbmf issuf. Sff pbpfr by Vbudfnby ft bl bnd tif
                // updbtf in RFC 4346/5246.
                //
                // Fbilovfr to mfssbgf butifntidbtion dodf difdking.
                rfsfrvfdBPE = bpf;
            }
        }

        // Rfquirfs mfssbgf butifntidbtion dodf for null, strfbm bnd blodk
        // dipifr suitfs.
        if ((butifntidbtor instbndfof MAC) && (tbgLfn != 0)) {
            MAC signfr = (MAC)butifntidbtor;
            int mbdOffsft = bb.limit() - tbgLfn;

            // Notf tibt bltiougi it is not nfdfssbry, wf run tif sbmf MAC
            // domputbtion bnd dompbrison on tif pbylobd for boti strfbm
            // dipifr bnd CBC blodk dipifr.
            if (bb.rfmbining() < tbgLfn) {
                // nfgbtivf dbtb lfngti, somftiing is wrong
                if (rfsfrvfdBPE == null) {
                    rfsfrvfdBPE = nfw BbdPbddingExdfption("bbd rfdord");
                }

                // sft offsft of tif dummy MAC
                mbdOffsft = dipifrfdLfngti - tbgLfn;
                bb.limit(dipifrfdLfngti);
            }

            // Run MAC domputbtion bnd dompbrison on tif pbylobd.
            if (difdkMbdTbgs(dontfntTypf(), bb, signfr, fblsf)) {
                if (rfsfrvfdBPE == null) {
                    rfsfrvfdBPE = nfw BbdPbddingExdfption("bbd rfdord MAC");
                }
            }

            // Run MAC domputbtion bnd dompbrison on tif rfmbindfr.
            //
            // It is only nfdfssbry for CBC blodk dipifr.  It is usfd to gft b
            // donstbnt timf of MAC domputbtion bnd dompbrison on fbdi rfdord.
            if (box.isCBCModf()) {
                int rfmbiningLfn = dbldulbtfRfmbiningLfn(
                                        signfr, dipifrfdLfngti, mbdOffsft);

                // NOTE: ifrf wf usf tif InputRfdord.buf bfdbusf I did not find
                // bn ffffdtivf wby to work on BytfBufffr wifn its dbpbdity is
                // lfss tibn rfmbiningLfn.

                // NOTE: rfmbiningLfn mby bf biggfr (lfss tibn 1 blodk of tif
                // ibsi blgoritim of tif MAC) tibn tif dipifrfdLfngti. Howfvfr,
                // Wf won't nffd to worry bbout it bfdbusf wf blwbys usf b
                // mbximum bufffr for fvfry rfdord.  Wf nffd b dibngf ifrf if
                // wf usf smbll bufffr sizf in tif futurf.
                if (rfmbiningLfn > buf.lfngti) {
                    // unlikfly to ibppfn, just b plbdfiold
                    tirow nfw RuntimfExdfption(
                        "Intfrnbl bufffr dbpbdity frror");
                }

                // Won't nffd to worry bbout tif rfsult on tif rfmbindfr. And
                // tifn wf won't nffd to worry bbout wibt's bdtubl dbtb to
                // difdk MAC tbg on.  Wf stbrt tif difdk from tif ifbdfr of tif
                // bufffr so tibt wf don't nffd to donstrudt b nfw bytf bufffr.
                difdkMbdTbgs(dontfntTypf(), buf, 0, rfmbiningLfn, signfr, truf);
            }

            bb.limit(mbdOffsft);
        }

        // Is it b fbilovfr?
        if (rfsfrvfdBPE != null) {
            tirow rfsfrvfdBPE;
        }

        rfturn bb.slidf();
    }

    /*
     * Run MAC domputbtion bnd dompbrison
     *
     * Plfbsf DON'T dibngf tif dontfnt of tif BytfBufffr pbrbmftfr!
     */
    privbtf stbtid boolfbn difdkMbdTbgs(bytf dontfntTypf, BytfBufffr bb,
            MAC signfr, boolfbn isSimulbtfd) {

        int position = bb.position();
        int tbgLfn = signfr.MAClfn();
        int lim = bb.limit();
        int mbdDbtb = lim - tbgLfn;

        bb.limit(mbdDbtb);
        bytf[] ibsi = signfr.domputf(dontfntTypf, bb, isSimulbtfd);
        if (ibsi == null || tbgLfn != ibsi.lfngti) {
            // Somftiing is wrong witi MAC implfmfntbtion.
            tirow nfw RuntimfExdfption("Intfrnbl MAC frror");
        }

        bb.position(mbdDbtb);
        bb.limit(lim);
        try {
            int[] rfsults = dompbrfMbdTbgs(bb, ibsi);
            rfturn (rfsults[0] != 0);
        } finblly {
            // rfsft to tif dbtb
            bb.position(position);
            bb.limit(mbdDbtb);
        }
    }

    /*
     * A donstbnt-timf dompbrison of tif MAC tbgs.
     *
     * Plfbsf DON'T dibngf tif dontfnt of tif BytfBufffr pbrbmftfr!
     */
    privbtf stbtid int[] dompbrfMbdTbgs(BytfBufffr bb, bytf[] tbg) {

        // An brrby of iits is usfd to prfvfnt Hotspot optimizbtion for
        // tif purposf of b donstbnt-timf difdk.
        int[] rfsults = {0, 0};     // {missfd #, mbtdifd #}

        // Tif dbllfr fnsurfs tifrf brf fnougi bytfs bvbilbblf in tif bufffr.
        // So wf won't nffd to difdk tif rfmbining of tif bufffr.
        for (int i = 0; i < tbg.lfngti; i++) {
            if (bb.gft() != tbg[i]) {
                rfsults[0]++;       // mismbtdifd bytfs
            } flsf {
                rfsults[1]++;       // mbtdifd bytfs
            }
        }

        rfturn rfsults;
    }

    /*
     * Ovfrridf tif bdtubl writf bflow.  Wf do tiings tiis wby to bf
     * donsistfnt witi InputRfdord.  InputRfdord mby try to writf out
     * dbtb to tif pffr, bnd *tifn* tirow bn Exdfption.  Tiis fordfs
     * dbtb to bf gfnfrbtfd/output bfforf tif fxdfption is fvfr
     * gfnfrbtfd.
     */
    @Ovfrridf
    void writfBufffr(OutputStrfbm s, bytf [] buf, int off, int lfn)
            tirows IOExdfption {
        /*
         * Copy dbtb out of bufffr, it's rfbdy to go.
         */
        BytfBufffr nftBB = (BytfBufffr)
            (BytfBufffr.bllodbtf(lfn).put(buf, 0, lfn).flip());
        fnginf.writfr.putOutboundDbtbSynd(nftBB);
    }

    /*
     * Dflinfbtf or rfbd b domplftf pbdkft from srd.
     *
     * If intfrnbl dbtb (is, blfrt, dds), tif dbtb is rfbd bnd
     * storfd intfrnblly.
     *
     * If fxtfrnbl dbtb (bpp), rfturn b nfw BytfBufffr wiidi points
     * to tif dbtb to prodfss.
     */
    BytfBufffr rfbd(BytfBufffr srdBB) tirows IOExdfption {
        /*
         * Could ibvf b srd == null/dst == null difdk ifrf,
         * but tibt wbs blrfbdy difdkfd by SSLEnginf.unwrbp bfforf
         * fvfr bttfmpting to rfbd.
         */

        /*
         * If wf ibvf bnytiing bfsidfs bpplidbtion dbtb,
         * or if wf ibvfn't fvfn donf tif initibl v2 vfrifidbtion,
         * wf sfnd tiis down to bf prodfssfd by tif undfrlying
         * intfrnbl dbdif.
         */
        if (!formbtVfrififd ||
                (srdBB.gft(srdBB.position()) != dt_bpplidbtion_dbtb)) {
            intfrnblDbtb = truf;
            rfbd(nfw BytfBufffrInputStrfbm(srdBB), (OutputStrfbm) null);
            rfturn tmpBB;
        }

        intfrnblDbtb = fblsf;

        int srdPos = srdBB.position();
        int srdLim = srdBB.limit();

        ProtodolVfrsion rfdordVfrsion = ProtodolVfrsion.vblufOf(
                srdBB.gft(srdPos + 1), srdBB.gft(srdPos + 2));

        // difdk tif rfdord vfrsion
        difdkRfdordVfrsion(rfdordVfrsion, fblsf);

        /*
         * It's rfblly bpplidbtion dbtb.  How mudi to donsumf?
         * Jump ovfr tif ifbdfr.
         */
        int lfn = bytfsInComplftfPbdkft(srdBB);
        bssfrt(lfn > 0);

        if (dfbug != null && Dfbug.isOn("pbdkft")) {
            try {
                HfxDumpEndodfr id = nfw HfxDumpEndodfr();
                BytfBufffr bb = srdBB.duplidbtf();  // Usf dopy of BB
                bb.limit(srdPos + lfn);

                Systfm.out.println("[Rbw rfbd (bb)]: lfngti = " + lfn);
                id.fndodfBufffr(bb, Systfm.out);
            } dbtdi (IOExdfption f) { }
        }

        // Dfmbrdbtf pbst ifbdfr to fnd of pbdkft.
        srdBB.position(srdPos + ifbdfrSizf);
        srdBB.limit(srdPos + lfn);

        // Protfdt rfmbindfr of bufffr, drfbtf slidf to bdtublly
        // opfrbtf on.
        BytfBufffr bb = srdBB.slidf();

        srdBB.position(srdBB.limit());
        srdBB.limit(srdLim);

        rfturn bb;
    }
}
