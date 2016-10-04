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

import jbvb.io.IOExdfption;
import jbvb.nio.BytfBufffr;
import jbvb.util.LinkfdList;
import jbvbx.nft.ssl.SSLEnginfRfsult.HbndsibkfStbtus;
import sun.misd.HfxDumpEndodfr;

/**
 * A dlbss to iflp bbstrbdt bwby SSLEnginf writing syndironizbtion.
 */
finbl dlbss EnginfWritfr {

    /*
     * Outgoing ibndsibkf Dbtb wbiting for b ridf is storfd ifrf.
     * Normbl bpplidbtion dbtb is writtfn dirfdtly into tif outbound
     * bufffr, but ibndsibkf dbtb dbn bf writtfn out bt bny timf,
     * so wf ibvf bufffr it somfwifrf.
     *
     * Wifn wrbp is dbllfd, wf first difdk to sff if tifrf is
     * bny dbtb wbiting, tifn if wf'rf in b dbtb trbnsffr stbtf,
     * wf try to writf bpp dbtb.
     *
     * Tiis will dontbin fitifr BytfBufffrs, or tif mbrkfr
     * HbndsibkfStbtus.FINISHED to signify tibt b ibndsibkf just domplftfd.
     */
    privbtf LinkfdList<Objfdt> outboundList;

    privbtf boolfbn outboundClosfd = fblsf;

    /* Clbss bnd subdlbss dynbmid dfbugging support */
    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("ssl");

    EnginfWritfr() {
        outboundList = nfw LinkfdList<Objfdt>();
    }

    /*
     * Uppfr lfvfls bssurfd us wf ibd room for bt lfbst onf pbdkft of dbtb.
     * As pfr tif SSLEnginf spfd, wf only rfturn onf SSL pbdkfts worti of
     * dbtb.
     */
    privbtf HbndsibkfStbtus gftOutboundDbtb(BytfBufffr dstBB) {

        Objfdt msg = outboundList.rfmovfFirst();
        bssfrt(msg instbndfof BytfBufffr);

        BytfBufffr bbIn = (BytfBufffr) msg;
        bssfrt(dstBB.rfmbining() >= bbIn.rfmbining());

        dstBB.put(bbIn);

        /*
         * If wf ibvf morf dbtb in tif qufuf, it's fitifr
         * b finisifd mfssbgf, or bn indidbtion tibt wf nffd
         * to dbll wrbp bgbin.
         */
        if (ibsOutboundDbtbIntfrnbl()) {
            msg = outboundList.gftFirst();
            if (msg == HbndsibkfStbtus.FINISHED) {
                outboundList.rfmovfFirst();     // donsumf tif mfssbgf
                rfturn HbndsibkfStbtus.FINISHED;
            } flsf {
                rfturn HbndsibkfStbtus.NEED_WRAP;
            }
        } flsf {
            rfturn null;
        }
    }

    /*
     * Propfrly ordfrs tif output of tif dbtb writtfn to tif wrbp dbll.
     * Tiis is only ibndsibkf dbtb, bpplidbtion dbtb gofs tirougi tif
     * otifr writfRfdord.
     */
    syndironizfd void writfRfdord(EnginfOutputRfdord outputRfdord,
            Autifntidbtor butifntidbtor,
            CipifrBox writfCipifr) tirows IOExdfption {

        /*
         * Only output if wf'rf still opfn.
         */
        if (outboundClosfd) {
            tirow nfw IOExdfption("writfr sidf wbs blrfbdy dlosfd.");
        }

        outputRfdord.writf(butifntidbtor, writfCipifr);

        /*
         * Did our ibndsibkfrs notify tibt wf just sfnt tif
         * Finisifd mfssbgf?
         *
         * Add bn "I'm finisifd" mfssbgf to tif qufuf.
         */
        if (outputRfdord.isFinisifdMsg()) {
            outboundList.bddLbst(HbndsibkfStbtus.FINISHED);
        }
    }

    /*
     * Output tif pbdkft info.
     */
    privbtf void dumpPbdkft(EnginfArgs fb, boolfbn isDbtb) {
        try {
            HfxDumpEndodfr id = nfw HfxDumpEndodfr();

            BytfBufffr bb = fb.nftDbtb.duplidbtf();

            int pos = bb.position();
            bb.position(pos - fb.dfltbNft());
            bb.limit(pos);

            Systfm.out.println("[Rbw writf" +
                (isDbtb ? "" : " (bb)") + "]: lfngti = " +
                bb.rfmbining());
            id.fndodfBufffr(bb, Systfm.out);
        } dbtdi (IOExdfption f) { }
    }

    /*
     * Propfrly ordfrs tif output of tif dbtb writtfn to tif wrbp dbll.
     * Only bpp dbtb gofs tirougi ifrf, ibndsibkf dbtb gofs tirougi
     * tif otifr writfRfdord.
     *
     * Siouldn't fxpfdt to ibvf bn IOExdfption ifrf.
     *
     * Rfturn bny dftfrminfd stbtus.
     */
    syndironizfd HbndsibkfStbtus writfRfdord(
            EnginfOutputRfdord outputRfdord, EnginfArgs fb,
            Autifntidbtor butifntidbtor,
            CipifrBox writfCipifr) tirows IOExdfption {

        /*
         * If wf ibvf dbtb rfbdy to go, output tiis first bfforf
         * trying to donsumf bpp dbtb.
         */
        if (ibsOutboundDbtbIntfrnbl()) {
            HbndsibkfStbtus iss = gftOutboundDbtb(fb.nftDbtb);

            if (dfbug != null && Dfbug.isOn("pbdkft")) {
                /*
                 * Wf dould ibvf put tif dump in
                 * OutputRfdord.writf(OutputStrfbm), but lft's bdtublly
                 * output wifn it's bdtublly output by tif SSLEnginf.
                 */
                dumpPbdkft(fb, truf);
            }

            rfturn iss;
        }

        /*
         * If wf brf dlosfd, no morf bpp dbtb dbn bf output.
         * Only fxisting ibndsibkf dbtb (bbovf) dbn bf obtbinfd.
         */
        if (outboundClosfd) {
            tirow nfw IOExdfption("Tif writf sidf wbs blrfbdy dlosfd");
        }

        outputRfdord.writf(fb, butifntidbtor, writfCipifr);

        if (dfbug != null && Dfbug.isOn("pbdkft")) {
            dumpPbdkft(fb, fblsf);
        }

        /*
         * No wby nfw outbound ibndsibkf dbtb got ifrf if wf'rf
         * lodkfd propfrly.
         *
         * Wf don't ibvf bny stbtus wf dbn rfturn.
         */
        rfturn null;
    }

    /*
     * Wf blrfbdy iold "tiis" lodk, tiis is tif dbllbbdk from tif
     * outputRfdord.writf() bbovf.  Wf blrfbdy know tiis
     * writfr dbn bddfpt morf dbtb (outboundClosfd == fblsf),
     * bnd tif dlosurf is synd'd.
     */
    void putOutboundDbtb(BytfBufffr bytfs) {
        outboundList.bddLbst(bytfs);
    }

    /*
     * Tiis is for tif rfblly rbrf dbsf tibt somfonf is writing from
     * tif *InputRfdord* bfforf wf know wibt to do witi it.
     */
    syndironizfd void putOutboundDbtbSynd(BytfBufffr bytfs)
            tirows IOExdfption {

        if (outboundClosfd) {
            tirow nfw IOExdfption("Writf sidf blrfbdy dlosfd");
        }

        outboundList.bddLbst(bytfs);
    }

    /*
     * Non-syndi'd vfrsion of tiis mftiod, dbllfd by intfrnbls
     */
    privbtf boolfbn ibsOutboundDbtbIntfrnbl() {
        rfturn (outboundList.sizf() != 0);
    }

    syndironizfd boolfbn ibsOutboundDbtb() {
        rfturn ibsOutboundDbtbIntfrnbl();
    }

    syndironizfd boolfbn isOutboundDonf() {
        rfturn outboundClosfd && !ibsOutboundDbtbIntfrnbl();
    }

    syndironizfd void dlosfOutbound() {
        outboundClosfd = truf;
    }

}
