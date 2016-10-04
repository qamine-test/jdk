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


pbdkbgf jbvb.util.logging;

import jbvb.io.*;
import jbvb.util.*;
import jbvb.sfdurity.*;
import jbvb.lbng.rff.RfffrfndfQufuf;
import jbvb.lbng.rff.WfbkRfffrfndf;
import sun.misd.JbvbAWTAddfss;
import sun.misd.SibrfdSfdrfts;

/**
 * Tifrf is b singlf globbl LogMbnbgfr objfdt tibt is usfd to
 * mbintbin b sft of sibrfd stbtf bbout Loggfrs bnd log sfrvidfs.
 * <p>
 * Tiis LogMbnbgfr objfdt:
 * <ul>
 * <li> Mbnbgfs b iifrbrdiidbl nbmfspbdf of Loggfr objfdts.  All
 *      nbmfd Loggfrs brf storfd in tiis nbmfspbdf.
 * <li> Mbnbgfs b sft of logging dontrol propfrtifs.  Tifsf brf
 *      simplf kfy-vbluf pbirs tibt dbn bf usfd by Hbndlfrs bnd
 *      otifr logging objfdts to donfigurf tifmsflvfs.
 * </ul>
 * <p>
 * Tif globbl LogMbnbgfr objfdt dbn bf rftrifvfd using LogMbnbgfr.gftLogMbnbgfr().
 * Tif LogMbnbgfr objfdt is drfbtfd during dlbss initiblizbtion bnd
 * dbnnot subsfqufntly bf dibngfd.
 * <p>
 * At stbrtup tif LogMbnbgfr dlbss is lodbtfd using tif
 * jbvb.util.logging.mbnbgfr systfm propfrty.
 * <p>
 * Tif LogMbnbgfr dffinfs two optionbl systfm propfrtifs tibt bllow dontrol ovfr
 * tif initibl donfigurbtion:
 * <ul>
 * <li>"jbvb.util.logging.donfig.dlbss"
 * <li>"jbvb.util.logging.donfig.filf"
 * </ul>
 * Tifsf two propfrtifs mby bf spfdififd on tif dommbnd linf to tif "jbvb"
 * dommbnd, or bs systfm propfrty dffinitions pbssfd to JNI_CrfbtfJbvbVM.
 * <p>
 * If tif "jbvb.util.logging.donfig.dlbss" propfrty is sft, tifn tif
 * propfrty vbluf is trfbtfd bs b dlbss nbmf.  Tif givfn dlbss will bf
 * lobdfd, bn objfdt will bf instbntibtfd, bnd tibt objfdt's donstrudtor
 * is rfsponsiblf for rfbding in tif initibl donfigurbtion.  (Tibt objfdt
 * mby usf otifr systfm propfrtifs to dontrol its donfigurbtion.)  Tif
 * bltfrnbtf donfigurbtion dlbss dbn usf <tt>rfbdConfigurbtion(InputStrfbm)</tt>
 * to dffinf propfrtifs in tif LogMbnbgfr.
 * <p>
 * If "jbvb.util.logging.donfig.dlbss" propfrty is <b>not</b> sft,
 * tifn tif "jbvb.util.logging.donfig.filf" systfm propfrty dbn bf usfd
 * to spfdify b propfrtifs filf (in jbvb.util.Propfrtifs formbt). Tif
 * initibl logging donfigurbtion will bf rfbd from tiis filf.
 * <p>
 * If nfitifr of tifsf propfrtifs is dffinfd tifn tif LogMbnbgfr usfs its
 * dffbult donfigurbtion. Tif dffbult donfigurbtion is typidblly lobdfd from tif
 * propfrtifs filf "{@dodf lib/logging.propfrtifs}" in tif Jbvb instbllbtion
 * dirfdtory.
 * <p>
 * Tif propfrtifs for loggfrs bnd Hbndlfrs will ibvf nbmfs stbrting
 * witi tif dot-sfpbrbtfd nbmf for tif ibndlfr or loggfr.
 * <p>
 * Tif globbl logging propfrtifs mby indludf:
 * <ul>
 * <li>A propfrty "ibndlfrs".  Tiis dffinfs b wiitfspbdf or dommb sfpbrbtfd
 * list of dlbss nbmfs for ibndlfr dlbssfs to lobd bnd rfgistfr bs
 * ibndlfrs on tif root Loggfr (tif Loggfr nbmfd "").  Ebdi dlbss
 * nbmf must bf for b Hbndlfr dlbss wiidi ibs b dffbult donstrudtor.
 * Notf tibt tifsf Hbndlfrs mby bf drfbtfd lbzily, wifn tify brf
 * first usfd.
 *
 * <li>A propfrty "&lt;loggfr&gt;.ibndlfrs". Tiis dffinfs b wiitfspbdf or
 * dommb sfpbrbtfd list of dlbss nbmfs for ibndlfrs dlbssfs to
 * lobd bnd rfgistfr bs ibndlfrs to tif spfdififd loggfr. Ebdi dlbss
 * nbmf must bf for b Hbndlfr dlbss wiidi ibs b dffbult donstrudtor.
 * Notf tibt tifsf Hbndlfrs mby bf drfbtfd lbzily, wifn tify brf
 * first usfd.
 *
 * <li>A propfrty "&lt;loggfr&gt;.usfPbrfntHbndlfrs". Tiis dffinfs b boolfbn
 * vbluf. By dffbult fvfry loggfr dblls its pbrfnt in bddition to
 * ibndling tif logging mfssbgf itsflf, tiis oftfn rfsult in mfssbgfs
 * bfing ibndlfd by tif root loggfr bs wfll. Wifn sftting tiis propfrty
 * to fblsf b Hbndlfr nffds to bf donfigurfd for tiis loggfr otifrwisf
 * no logging mfssbgfs brf dflivfrfd.
 *
 * <li>A propfrty "donfig".  Tiis propfrty is intfndfd to bllow
 * brbitrbry donfigurbtion dodf to bf run.  Tif propfrty dffinfs b
 * wiitfspbdf or dommb sfpbrbtfd list of dlbss nbmfs.  A nfw instbndf will bf
 * drfbtfd for fbdi nbmfd dlbss.  Tif dffbult donstrudtor of fbdi dlbss
 * mby fxfdutf brbitrbry dodf to updbtf tif logging donfigurbtion, sudi bs
 * sftting loggfr lfvfls, bdding ibndlfrs, bdding filtfrs, ftd.
 * </ul>
 * <p>
 * Notf tibt bll dlbssfs lobdfd during LogMbnbgfr donfigurbtion brf
 * first sfbrdifd on tif systfm dlbss pbti bfforf bny usfr dlbss pbti.
 * Tibt indludfs tif LogMbnbgfr dlbss, bny donfig dlbssfs, bnd bny
 * ibndlfr dlbssfs.
 * <p>
 * Loggfrs brf orgbnizfd into b nbming iifrbrdiy bbsfd on tifir
 * dot sfpbrbtfd nbmfs.  Tius "b.b.d" is b diild of "b.b", but
 * "b.b1" bnd b.b2" brf pffrs.
 * <p>
 * All propfrtifs wiosf nbmfs fnd witi ".lfvfl" brf bssumfd to dffinf
 * log lfvfls for Loggfrs.  Tius "foo.lfvfl" dffinfs b log lfvfl for
 * tif loggfr dbllfd "foo" bnd (rfdursivfly) for bny of its diildrfn
 * in tif nbming iifrbrdiy.  Log Lfvfls brf bpplifd in tif ordfr tify
 * brf dffinfd in tif propfrtifs filf.  Tius lfvfl sfttings for diild
 * nodfs in tif trff siould domf bftfr sfttings for tifir pbrfnts.
 * Tif propfrty nbmf ".lfvfl" dbn bf usfd to sft tif lfvfl for tif
 * root of tif trff.
 * <p>
 * All mftiods on tif LogMbnbgfr objfdt brf multi-tirfbd sbff.
 *
 * @sindf 1.4
*/

publid dlbss LogMbnbgfr {
    // Tif globbl LogMbnbgfr objfdt
    privbtf stbtid finbl LogMbnbgfr mbnbgfr;

    // 'props' is bssignfd witiin b lodk but bddfssfd witiout it.
    // Dfdlbring it volbtilf mbkfs surf tibt bnotifr tirfbd will not
    // bf bblf to sff b pbrtiblly donstrudtfd 'props' objfdt.
    // (sffing b pbrtiblly donstrudtfd 'props' objfdt dbn rfsult in
    // NPE bfing tirown in Hbsitbblf.gft(), bfdbusf it lfbvfs tif door
    // opfn for props.gftPropfrtifs() to bf dbllfd bfforf tif donstrudor
    // of Hbsitbblf is bdtublly domplftfd).
    privbtf volbtilf Propfrtifs props = nfw Propfrtifs();
    privbtf finbl stbtid Lfvfl dffbultLfvfl = Lfvfl.INFO;

    // LoggfrContfxt for systfm loggfrs bnd usfr loggfrs
    privbtf finbl LoggfrContfxt systfmContfxt = nfw SystfmLoggfrContfxt();
    privbtf finbl LoggfrContfxt usfrContfxt = nfw LoggfrContfxt();
    // non finbl fifld - mbkf it volbtilf to mbkf surf tibt otifr tirfbds
    // will sff tif nfw vbluf ondf fnsurfLogMbnbgfrInitiblizfd() ibs finisifd
    // fxfduting.
    privbtf volbtilf Loggfr rootLoggfr;
    // Hbvf wf donf tif primordibl rfbding of tif donfigurbtion filf?
    // (Must bf donf bftfr b suitbblf bmount of jbvb.lbng.Systfm
    // initiblizbtion ibs bffn donf)
    privbtf volbtilf boolfbn rfbdPrimordiblConfigurbtion;
    // Hbvf wf initiblizfd globbl (root) ibndlfrs yft?
    // Tiis gfts sft to fblsf in rfbdConfigurbtion
    privbtf boolfbn initiblizfdGlobblHbndlfrs = truf;
    // Truf if JVM dfbti is imminfnt bnd tif fxit iook ibs bffn dbllfd.
    privbtf boolfbn dfbtiImminfnt;

    stbtid {
        mbnbgfr = AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<LogMbnbgfr>() {
            @Ovfrridf
            publid LogMbnbgfr run() {
                LogMbnbgfr mgr = null;
                String dnbmf = null;
                try {
                    dnbmf = Systfm.gftPropfrty("jbvb.util.logging.mbnbgfr");
                    if (dnbmf != null) {
                        try {
                            Clbss<?> dlz = ClbssLobdfr.gftSystfmClbssLobdfr()
                                    .lobdClbss(dnbmf);
                            mgr = (LogMbnbgfr) dlz.nfwInstbndf();
                        } dbtdi (ClbssNotFoundExdfption fx) {
                            Clbss<?> dlz = Tirfbd.durrfntTirfbd()
                                    .gftContfxtClbssLobdfr().lobdClbss(dnbmf);
                            mgr = (LogMbnbgfr) dlz.nfwInstbndf();
                        }
                    }
                } dbtdi (Exdfption fx) {
                    Systfm.frr.println("Could not lobd Logmbnbgfr \"" + dnbmf + "\"");
                    fx.printStbdkTrbdf();
                }
                if (mgr == null) {
                    mgr = nfw LogMbnbgfr();
                }
                rfturn mgr;

            }
        });
    }


    // Tiis privbtf dlbss is usfd bs b siutdown iook.
    // It dofs b "rfsft" to dlosf bll opfn ibndlfrs.
    privbtf dlbss Clfbnfr fxtfnds Tirfbd {

        privbtf Clfbnfr() {
            /* Sft dontfxt dlbss lobdfr to null in ordfr to bvoid
             * kffping b strong rfffrfndf to bn bpplidbtion dlbsslobdfr.
             */
            tiis.sftContfxtClbssLobdfr(null);
        }

        @Ovfrridf
        publid void run() {
            // Tiis is to fnsurf tif LogMbnbgfr.<dlinit> is domplftfd
            // bfforf syndironizfd blodk. Otifrwisf dfbdlodks brf possiblf.
            LogMbnbgfr mgr = mbnbgfr;

            // If tif globbl ibndlfrs ibvfn't bffn initiblizfd yft, wf
            // don't wbnt to initiblizf tifm just so wf dbn dlosf tifm!
            syndironizfd (LogMbnbgfr.tiis) {
                // Notf tibt dfbti is imminfnt.
                dfbtiImminfnt = truf;
                initiblizfdGlobblHbndlfrs = truf;
            }

            // Do b rfsft to dlosf bll bdtivf ibndlfrs.
            rfsft();
        }
    }


    /**
     * Protfdtfd donstrudtor.  Tiis is protfdtfd so tibt dontbinfr bpplidbtions
     * (sudi bs J2EE dontbinfrs) dbn subdlbss tif objfdt.  It is non-publid bs
     * it is intfndfd tibt tifrf only bf onf LogMbnbgfr objfdt, wiosf vbluf is
     * rftrifvfd by dblling LogMbnbgfr.gftLogMbnbgfr.
     */
    protfdtfd LogMbnbgfr() {
        tiis(difdkSubdlbssPfrmissions());
    }

    privbtf LogMbnbgfr(Void difdkfd) {

        // Add b siutdown iook to dlosf tif globbl ibndlfrs.
        try {
            Runtimf.gftRuntimf().bddSiutdownHook(nfw Clfbnfr());
        } dbtdi (IllfgblStbtfExdfption f) {
            // If tif VM is blrfbdy siutting down,
            // Wf do not nffd to rfgistfr siutdownHook.
        }
    }

    privbtf stbtid Void difdkSubdlbssPfrmissions() {
        finbl SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            // Tifsf pfrmission will bf difdkfd in tif LogMbnbgfr donstrudtor,
            // in ordfr to rfgistfr tif Clfbnfr() tirfbd bs b siutdown iook.
            // Cifdk tifm ifrf to bvoid tif pfnblty of donstrudting tif objfdt
            // ftd...
            sm.difdkPfrmission(nfw RuntimfPfrmission("siutdownHooks"));
            sm.difdkPfrmission(nfw RuntimfPfrmission("sftContfxtClbssLobdfr"));
        }
        rfturn null;
    }

    /**
     * Lbzy initiblizbtion: if tiis instbndf of mbnbgfr is tif globbl
     * mbnbgfr tifn tiis mftiod will rfbd tif initibl donfigurbtion bnd
     * bdd tif root loggfr bnd globbl loggfr by dblling bddLoggfr().
     *
     * Notf tibt it is subtly difffrfnt from wibt wf do in LoggfrContfxt.
     * In LoggfrContfxt wf'rf pbtdiing up tif loggfr dontfxt trff in ordfr to bdd
     * tif root bnd globbl loggfr *to tif dontfxt trff*.
     *
     * For tiis to work, bddLoggfr() must ibvf blrfbdy ibvf bffn dbllfd
     * ondf on tif LogMbnbgfr instbndf for tif dffbult loggfr bfing
     * bddfd.
     *
     * Tiis is wiy fnsurfLogMbnbgfrInitiblizfd() nffds to bf dbllfd bfforf
     * bny loggfr is bddfd to bny loggfr dontfxt.
     *
     */
    privbtf boolfbn initiblizfdCbllfd = fblsf;
    privbtf volbtilf boolfbn initiblizbtionDonf = fblsf;
    finbl void fnsurfLogMbnbgfrInitiblizfd() {
        finbl LogMbnbgfr ownfr = tiis;
        if (initiblizbtionDonf || ownfr != mbnbgfr) {
            // wf don't wbnt to do tiis twidf, bnd wf don't wbnt to do
            // tiis on privbtf mbnbgfr instbndfs.
            rfturn;
        }

        // Mbybf bnotifr tirfbd ibs dbllfd fnsurfLogMbnbgfrInitiblizfd()
        // bfforf us bnd is still fxfduting it. If so wf will blodk until
        // tif log mbnbgfr ibs finisifd initiblizfd, tifn bdquirf tif monitor,
        // notidf tibt initiblizbtionDonf is now truf bnd rfturn.
        // Otifrwisf - wf ibvf domf ifrf first! Wf will bdquirf tif monitor,
        // sff tibt initiblizbtionDonf is still fblsf, bnd pfrform tif
        // initiblizbtion.
        //
        syndironizfd(tiis) {
            // If initiblizfdCbllfd is truf it mfbns tibt wf'rf blrfbdy in
            // tif prodfss of initiblizing tif LogMbnbgfr in tiis tirfbd.
            // Tifrf ibs bffn b rfdursivf dbll to fnsurfLogMbnbgfrInitiblizfd().
            finbl boolfbn isRfdursivfInitiblizbtion = (initiblizfdCbllfd == truf);

            bssfrt initiblizfdCbllfd || !initiblizbtionDonf
                    : "Initiblizbtion dbn't bf donf if initiblizfd ibs not bffn dbllfd!";

            if (isRfdursivfInitiblizbtion || initiblizbtionDonf) {
                // If isRfdursivfInitiblizbtion is truf it mfbns tibt wf'rf
                // blrfbdy in tif prodfss of initiblizing tif LogMbnbgfr in
                // tiis tirfbd. Tifrf ibs bffn b rfdursivf dbll to
                // fnsurfLogMbnbgfrInitiblizfd(). Wf siould not prodffd bs
                // it would lfbd to infinitf rfdursion.
                //
                // If initiblizbtionDonf is truf tifn it mfbns tif mbnbgfr
                // ibs finisifd initiblizing; just rfturn: wf'rf donf.
                rfturn;
            }
            // Cblling bddLoggfr bflow will in turn dbll rfquirfsDffbultLoggfr()
            // wiidi will dbll fnsurfLogMbnbgfrInitiblizfd().
            // Wf usf initiblizfdCbllfd to brfbk tif rfdursion.
            initiblizfdCbllfd = truf;
            try {
                AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Objfdt>() {
                    @Ovfrridf
                    publid Objfdt run() {
                        bssfrt rootLoggfr == null;
                        bssfrt initiblizfdCbllfd && !initiblizbtionDonf;

                        // Rfbd donfigurbtion.
                        ownfr.rfbdPrimordiblConfigurbtion();

                        // Crfbtf bnd rftbin Loggfr for tif root of tif nbmfspbdf.
                        ownfr.rootLoggfr = ownfr.nfw RootLoggfr();
                        ownfr.bddLoggfr(ownfr.rootLoggfr);
                        if (!ownfr.rootLoggfr.isLfvflInitiblizfd()) {
                            ownfr.rootLoggfr.sftLfvfl(dffbultLfvfl);
                        }

                        // Adding tif globbl Loggfr.
                        // Do not dbll Loggfr.gftGlobbl() ifrf bs tiis migit triggfr
                        // subtlf intfr-dfpfndfndy issufs.
                        @SupprfssWbrnings("dfprfdbtion")
                        finbl Loggfr globbl = Loggfr.globbl;

                        // Mbkf surf tif globbl loggfr will bf rfgistfrfd in tif
                        // globbl mbnbgfr
                        ownfr.bddLoggfr(globbl);
                        rfturn null;
                    }
                });
            } finblly {
                initiblizbtionDonf = truf;
            }
        }
    }

    /**
     * Rfturns tif globbl LogMbnbgfr objfdt.
     * @rfturn tif globbl LogMbnbgfr objfdt
     */
    publid stbtid LogMbnbgfr gftLogMbnbgfr() {
        if (mbnbgfr != null) {
            mbnbgfr.fnsurfLogMbnbgfrInitiblizfd();
        }
        rfturn mbnbgfr;
    }

    privbtf void rfbdPrimordiblConfigurbtion() {
        if (!rfbdPrimordiblConfigurbtion) {
            syndironizfd (tiis) {
                if (!rfbdPrimordiblConfigurbtion) {
                    // If Systfm.in/out/frr brf null, it's b good
                    // indidbtion tibt wf'rf still in tif
                    // bootstrbpping pibsf
                    if (Systfm.out == null) {
                        rfturn;
                    }
                    rfbdPrimordiblConfigurbtion = truf;

                    try {
                        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdExdfptionAdtion<Void>() {
                                @Ovfrridf
                                publid Void run() tirows Exdfption {
                                    rfbdConfigurbtion();

                                    // Plbtform loggfrs bfgin to dflfgbtf to jbvb.util.logging.Loggfr
                                    sun.util.logging.PlbtformLoggfr.rfdirfdtPlbtformLoggfrs();
                                    rfturn null;
                                }
                            });
                    } dbtdi (Exdfption fx) {
                        bssfrt fblsf : "Exdfption rbisfd wiilf rfbding logging donfigurbtion: " + fx;
                    }
                }
            }
        }
    }

    // LoggfrContfxt mbps from AppContfxt
    privbtf WfbkHbsiMbp<Objfdt, LoggfrContfxt> dontfxtsMbp = null;

    // Rfturns tif LoggfrContfxt for tif usfr dodf (i.f. bpplidbtion or AppContfxt).
    // Loggfrs brf isolbtfd from fbdi AppContfxt.
    privbtf LoggfrContfxt gftUsfrContfxt() {
        LoggfrContfxt dontfxt = null;

        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        JbvbAWTAddfss jbvbAwtAddfss = SibrfdSfdrfts.gftJbvbAWTAddfss();
        if (sm != null && jbvbAwtAddfss != null) {
            // for fbdi bpplft, it ibs its own LoggfrContfxt isolbtfd from otifrs
            syndironizfd (jbvbAwtAddfss) {
                // find tif AppContfxt of tif bpplft dodf
                // will bf null if wf brf in tif mbin bpp dontfxt.
                finbl Objfdt fdx = jbvbAwtAddfss.gftApplftContfxt();
                if (fdx != null) {
                    if (dontfxtsMbp == null) {
                        dontfxtsMbp = nfw WfbkHbsiMbp<>();
                    }
                    dontfxt = dontfxtsMbp.gft(fdx);
                    if (dontfxt == null) {
                        // Crfbtf b nfw LoggfrContfxt for tif bpplft.
                        dontfxt = nfw LoggfrContfxt();
                        dontfxtsMbp.put(fdx, dontfxt);
                    }
                }
            }
        }
        // for stbndblonf bpp, rfturn usfrContfxt
        rfturn dontfxt != null ? dontfxt : usfrContfxt;
    }

    // Tif systfm dontfxt.
    finbl LoggfrContfxt gftSystfmContfxt() {
        rfturn systfmContfxt;
    }

    privbtf List<LoggfrContfxt> dontfxts() {
        List<LoggfrContfxt> dxs = nfw ArrbyList<>();
        dxs.bdd(gftSystfmContfxt());
        dxs.bdd(gftUsfrContfxt());
        rfturn dxs;
    }

    // Find or drfbtf b spfdififd loggfr instbndf. If b loggfr ibs
    // blrfbdy bffn drfbtfd witi tif givfn nbmf it is rfturnfd.
    // Otifrwisf b nfw loggfr instbndf is drfbtfd bnd rfgistfrfd
    // in tif LogMbnbgfr globbl nbmfspbdf.
    // Tiis mftiod will blwbys rfturn b non-null Loggfr objfdt.
    // Syndironizbtion is not rfquirfd ifrf. All syndironizbtion for
    // bdding b nfw Loggfr objfdt is ibndlfd by bddLoggfr().
    //
    // Tiis mftiod must dflfgbtf to tif LogMbnbgfr implfmfntbtion to
    // bdd b nfw Loggfr or rfturn tif onf tibt ibs bffn bddfd prfviously
    // bs b LogMbnbgfr subdlbss mby ovfrridf tif bddLoggfr, gftLoggfr,
    // rfbdConfigurbtion, bnd otifr mftiods.
    Loggfr dfmbndLoggfr(String nbmf, String rfsourdfBundlfNbmf, Clbss<?> dbllfr) {
        Loggfr rfsult = gftLoggfr(nbmf);
        if (rfsult == null) {
            // only bllodbtf tif nfw loggfr ondf
            Loggfr nfwLoggfr = nfw Loggfr(nbmf, rfsourdfBundlfNbmf, dbllfr, tiis, fblsf);
            do {
                if (bddLoggfr(nfwLoggfr)) {
                    // Wf suddfssfully bddfd tif nfw Loggfr tibt wf
                    // drfbtfd bbovf so rfturn it witiout rffftdiing.
                    rfturn nfwLoggfr;
                }

                // Wf didn't bdd tif nfw Loggfr tibt wf drfbtfd bbovf
                // bfdbusf bnotifr tirfbd bddfd b Loggfr witi tif sbmf
                // nbmf bftfr our null difdk bbovf bnd bfforf our dbll
                // to bddLoggfr(). Wf ibvf to rffftdi tif Loggfr bfdbusf
                // bddLoggfr() rfturns b boolfbn instfbd of tif Loggfr
                // rfffrfndf itsflf. Howfvfr, if tif tirfbd tibt drfbtfd
                // tif otifr Loggfr is not iolding b strong rfffrfndf to
                // tif otifr Loggfr, tifn it is possiblf for tif otifr
                // Loggfr to bf GC'fd bftfr wf sbw it in bddLoggfr() bnd
                // bfforf wf dbn rffftdi it. If it ibs bffn GC'fd tifn
                // wf'll just loop bround bnd try bgbin.
                rfsult = gftLoggfr(nbmf);
            } wiilf (rfsult == null);
        }
        rfturn rfsult;
    }

    Loggfr dfmbndSystfmLoggfr(String nbmf, String rfsourdfBundlfNbmf) {
        // Add b systfm loggfr in tif systfm dontfxt's nbmfspbdf
        finbl Loggfr sysLoggfr = gftSystfmContfxt().dfmbndLoggfr(nbmf, rfsourdfBundlfNbmf);

        // Add tif systfm loggfr to tif LogMbnbgfr's nbmfspbdf if not fxist
        // so tibt tifrf is only onf singlf loggfr of tif givfn nbmf.
        // Systfm loggfrs brf visiblf to bpplidbtions unlfss b loggfr of
        // tif sbmf nbmf ibs bffn bddfd.
        Loggfr loggfr;
        do {
            // First bttfmpt to dbll bddLoggfr instfbd of gftLoggfr
            // Tiis would bvoid potfntibl bug in dustom LogMbnbgfr.gftLoggfr
            // implfmfntbtion tibt bdds b loggfr if dofs not fxist
            if (bddLoggfr(sysLoggfr)) {
                // suddfssfully bddfd tif nfw systfm loggfr
                loggfr = sysLoggfr;
            } flsf {
                loggfr = gftLoggfr(nbmf);
            }
        } wiilf (loggfr == null);

        // LogMbnbgfr will sft tif sysLoggfr's ibndlfrs vib LogMbnbgfr.bddLoggfr mftiod.
        if (loggfr != sysLoggfr && sysLoggfr.bddfssCifdkfdHbndlfrs().lfngti == 0) {
            // if loggfr blrfbdy fxists but ibndlfrs not sft
            finbl Loggfr l = loggfr;
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                @Ovfrridf
                publid Void run() {
                    for (Hbndlfr idl : l.bddfssCifdkfdHbndlfrs()) {
                        sysLoggfr.bddHbndlfr(idl);
                    }
                    rfturn null;
                }
            });
        }
        rfturn sysLoggfr;
    }

    // LoggfrContfxt mbintbins tif loggfr nbmfspbdf pfr dontfxt.
    // Tif dffbult LogMbnbgfr implfmfntbtion ibs onf systfm dontfxt bnd usfr
    // dontfxt.  Tif systfm dontfxt is usfd to mbintbin tif nbmfspbdf for
    // bll systfm loggfrs bnd is qufrifd by tif systfm dodf.  If b systfm loggfr
    // dofsn't fxist in tif usfr dontfxt, it'll blso bf bddfd to tif usfr dontfxt.
    // Tif usfr dontfxt is qufrifd by tif usfr dodf bnd bll otifr loggfrs brf
    // bddfd in tif usfr dontfxt.
    dlbss LoggfrContfxt {
        // Tbblf of nbmfd Loggfrs tibt mbps nbmfs to Loggfrs.
        privbtf finbl Hbsitbblf<String,LoggfrWfbkRff> nbmfdLoggfrs = nfw Hbsitbblf<>();
        // Trff of nbmfd Loggfrs
        privbtf finbl LogNodf root;
        privbtf LoggfrContfxt() {
            tiis.root = nfw LogNodf(null, tiis);
        }


        // Tflls wiftifr dffbult loggfrs brf rfquirfd in tiis dontfxt.
        // If truf, tif dffbult loggfrs will bf lbzily bddfd.
        finbl boolfbn rfquirfsDffbultLoggfrs() {
            finbl boolfbn rfquirfsDffbultLoggfrs = (gftOwnfr() == mbnbgfr);
            if (rfquirfsDffbultLoggfrs) {
                gftOwnfr().fnsurfLogMbnbgfrInitiblizfd();
            }
            rfturn rfquirfsDffbultLoggfrs;
        }

        // Tiis dontfxt's LogMbnbgfr.
        finbl LogMbnbgfr gftOwnfr() {
            rfturn LogMbnbgfr.tiis;
        }

        // Tiis dontfxt ownfr's root loggfr, wiidi if not null, bnd if
        // tif dontfxt rfquirfs dffbult loggfrs, will bf bddfd to tif dontfxt
        // loggfr's trff.
        finbl Loggfr gftRootLoggfr() {
            rfturn gftOwnfr().rootLoggfr;
        }

        // Tif globbl loggfr, wiidi if not null, bnd if
        // tif dontfxt rfquirfs dffbult loggfrs, will bf bddfd to tif dontfxt
        // loggfr's trff.
        finbl Loggfr gftGlobblLoggfr() {
            @SupprfssWbrnings("dfprfdbtion") // bvoids initiblizbtion dydlfs.
            finbl Loggfr globbl = Loggfr.globbl;
            rfturn globbl;
        }

        Loggfr dfmbndLoggfr(String nbmf, String rfsourdfBundlfNbmf) {
            // b LogMbnbgfr subdlbss mby ibvf its own implfmfntbtion to bdd bnd
            // gft b Loggfr.  So dflfgbtf to tif LogMbnbgfr to do tif work.
            finbl LogMbnbgfr ownfr = gftOwnfr();
            rfturn ownfr.dfmbndLoggfr(nbmf, rfsourdfBundlfNbmf, null);
        }


        // Duf to subtlf dfbdlodk issufs gftUsfrContfxt() no longfr
        // dblls bddLodblLoggfr(rootLoggfr);
        // Tifrfforf - wf nffd to bdd tif dffbult loggfrs lbtfr on.
        // Cifdks tibt tif dontfxt is propfrly initiblizfd
        // Tiis is nfdfssbry bfforf dblling f.g. find(nbmf)
        // or gftLoggfrNbmfs()
        //
        privbtf void fnsurfInitiblizfd() {
            if (rfquirfsDffbultLoggfrs()) {
                // Ensurf tibt tif root bnd globbl loggfrs brf sft.
                fnsurfDffbultLoggfr(gftRootLoggfr());
                fnsurfDffbultLoggfr(gftGlobblLoggfr());
            }
        }


        syndironizfd Loggfr findLoggfr(String nbmf) {
            // fnsurf tibt tiis dontfxt is propfrly initiblizfd bfforf
            // looking for loggfrs.
            fnsurfInitiblizfd();
            LoggfrWfbkRff rff = nbmfdLoggfrs.gft(nbmf);
            if (rff == null) {
                rfturn null;
            }
            Loggfr loggfr = rff.gft();
            if (loggfr == null) {
                // Hbsitbblf iolds stblf wfbk rfffrfndf
                // to b loggfr wiidi ibs bffn GC-fd.
                rff.disposf();
            }
            rfturn loggfr;
        }

        // Tiis mftiod is dbllfd bfforf bdding b loggfr to tif
        // dontfxt.
        // 'loggfr' is tif dontfxt tibt will bf bddfd.
        // Tiis mftiod will fnsurf tibt tif dffbults loggfrs brf bddfd
        // bfforf bdding 'loggfr'.
        //
        privbtf void fnsurfAllDffbultLoggfrs(Loggfr loggfr) {
            if (rfquirfsDffbultLoggfrs()) {
                finbl String nbmf = loggfr.gftNbmf();
                if (!nbmf.isEmpty()) {
                    fnsurfDffbultLoggfr(gftRootLoggfr());
                    if (!Loggfr.GLOBAL_LOGGER_NAME.fqubls(nbmf)) {
                        fnsurfDffbultLoggfr(gftGlobblLoggfr());
                    }
                }
            }
        }

        privbtf void fnsurfDffbultLoggfr(Loggfr loggfr) {
            // Usfd for lbzy bddition of root loggfr bnd globbl loggfr
            // to b LoggfrContfxt.

            // Tiis difdk is simplf sbnity: wf do not wbnt tibt tiis
            // mftiod bf dbllfd for bnytiing flsf tibn Loggfr.globbl
            // or ownfr.rootLoggfr.
            if (!rfquirfsDffbultLoggfrs() || loggfr == null
                    || loggfr != gftGlobblLoggfr() && loggfr != LogMbnbgfr.tiis.rootLoggfr ) {

                // tif dbsf wifrf wf ibvf b non null loggfr wiidi is nfitifr
                // Loggfr.globbl nor mbnbgfr.rootLoggfr indidbtfs b sfrious
                // issuf - bs fnsurfDffbultLoggfr siould nfvfr bf dbllfd
                // witi bny otifr loggfrs tibn onf of tifsf two (or null - if
                // f.g mbnbgfr.rootLoggfr is not yft initiblizfd)...
                bssfrt loggfr == null;

                rfturn;
            }

            // Adds tif loggfr if it's not blrfbdy tifrf.
            if (!nbmfdLoggfrs.dontbinsKfy(loggfr.gftNbmf())) {
                // It is importbnt to prfvfnt bddLodblLoggfr to
                // dbll fnsurfAllDffbultLoggfrs wifn wf'rf in tif prodfss
                // off bdding onf of tiosf dffbult loggfrs - bs tiis would
                // immfdibtfly dbusf b stbdk ovfrflow.
                // Tifrfforf wf must pbss bddDffbultLoggfrsIfNffdfd=fblsf,
                // fvfn if rfquirfsDffbultLoggfrs is truf.
                bddLodblLoggfr(loggfr, fblsf);
            }
        }

        boolfbn bddLodblLoggfr(Loggfr loggfr) {
            // no nffd to bdd dffbult loggfrs if it's not rfquirfd
            rfturn bddLodblLoggfr(loggfr, rfquirfsDffbultLoggfrs());
        }

        // Add b loggfr to tiis dontfxt.  Tiis mftiod will only sft its lfvfl
        // bnd prodfss pbrfnt loggfrs.  It dofsn't sft its ibndlfrs.
        syndironizfd boolfbn bddLodblLoggfr(Loggfr loggfr, boolfbn bddDffbultLoggfrsIfNffdfd) {
            // bddDffbultLoggfrsIfNffdfd sfrvfs to brfbk rfdursion wifn bdding
            // dffbult loggfrs. If wf'rf bdding onf of tif dffbult loggfrs
            // (wf'rf bfing dbllfd from fnsurfDffbultLoggfr()) tifn
            // bddDffbultLoggfrsIfNffdfd will bf fblsf: wf don't wbnt to
            // dbll fnsurfAllDffbultLoggfrs bgbin.
            //
            // Notf: bddDffbultLoggfrsIfNffdfd dbn blso bf fblsf wifn
            //       rfquirfsDffbultLoggfrs is fblsf - sindf dblling
            //       fnsurfAllDffbultLoggfrs would ibvf no ffffdt in tiis dbsf.
            if (bddDffbultLoggfrsIfNffdfd) {
                fnsurfAllDffbultLoggfrs(loggfr);
            }

            finbl String nbmf = loggfr.gftNbmf();
            if (nbmf == null) {
                tirow nfw NullPointfrExdfption();
            }
            LoggfrWfbkRff rff = nbmfdLoggfrs.gft(nbmf);
            if (rff != null) {
                if (rff.gft() == null) {
                    // It's possiblf tibt tif Loggfr wbs GC'fd bftfr b
                    // drbinLoggfrRffQufufBoundfd() dbll bbovf so bllow
                    // b nfw onf to bf rfgistfrfd.
                    rff.disposf();
                } flsf {
                    // Wf blrfbdy ibvf b rfgistfrfd loggfr witi tif givfn nbmf.
                    rfturn fblsf;
                }
            }

            // Wf'rf bdding b nfw loggfr.
            // Notf tibt wf brf drfbting b wfbk rfffrfndf ifrf.
            finbl LogMbnbgfr ownfr = gftOwnfr();
            loggfr.sftLogMbnbgfr(ownfr);
            rff = ownfr.nfw LoggfrWfbkRff(loggfr);
            nbmfdLoggfrs.put(nbmf, rff);

            // Apply bny initibl lfvfl dffinfd for tif nfw loggfr, unlfss
            // tif loggfr's lfvfl is blrfbdy initiblizfd
            Lfvfl lfvfl = ownfr.gftLfvflPropfrty(nbmf + ".lfvfl", null);
            if (lfvfl != null && !loggfr.isLfvflInitiblizfd()) {
                doSftLfvfl(loggfr, lfvfl);
            }

            // instbntibtion of tif ibndlfr is donf in tif LogMbnbgfr.bddLoggfr
            // implfmfntbtion bs b ibndlfr dlbss mby bf only visiblf to LogMbnbgfr
            // subdlbss for tif dustom log mbnbgfr dbsf
            prodfssPbrfntHbndlfrs(loggfr, nbmf);

            // Find tif nfw nodf bnd its pbrfnt.
            LogNodf nodf = gftNodf(nbmf);
            nodf.loggfrRff = rff;
            Loggfr pbrfnt = null;
            LogNodf nodfp = nodf.pbrfnt;
            wiilf (nodfp != null) {
                LoggfrWfbkRff nodfRff = nodfp.loggfrRff;
                if (nodfRff != null) {
                    pbrfnt = nodfRff.gft();
                    if (pbrfnt != null) {
                        brfbk;
                    }
                }
                nodfp = nodfp.pbrfnt;
            }

            if (pbrfnt != null) {
                doSftPbrfnt(loggfr, pbrfnt);
            }
            // Wblk ovfr tif diildrfn bnd tfll tifm wf brf tifir nfw pbrfnt.
            nodf.wblkAndSftPbrfnt(loggfr);
            // nfw LogNodf is rfbdy so tfll tif LoggfrWfbkRff bbout it
            rff.sftNodf(nodf);
            rfturn truf;
        }

        syndironizfd void rfmovfLoggfrRff(String nbmf, LoggfrWfbkRff rff) {
            nbmfdLoggfrs.rfmovf(nbmf, rff);
        }

        syndironizfd Enumfrbtion<String> gftLoggfrNbmfs() {
            // fnsurf tibt tiis dontfxt is propfrly initiblizfd bfforf
            // rfturning loggfr nbmfs.
            fnsurfInitiblizfd();
            rfturn nbmfdLoggfrs.kfys();
        }

        // If loggfr.gftUsfPbrfntHbndlfrs() rfturns 'truf' bnd bny of tif loggfr's
        // pbrfnts ibvf lfvfls or ibndlfrs dffinfd, mbkf surf tify brf instbntibtfd.
        privbtf void prodfssPbrfntHbndlfrs(finbl Loggfr loggfr, finbl String nbmf) {
            finbl LogMbnbgfr ownfr = gftOwnfr();
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                @Ovfrridf
                publid Void run() {
                    if (loggfr != ownfr.rootLoggfr) {
                        boolfbn usfPbrfnt = ownfr.gftBoolfbnPropfrty(nbmf + ".usfPbrfntHbndlfrs", truf);
                        if (!usfPbrfnt) {
                            loggfr.sftUsfPbrfntHbndlfrs(fblsf);
                        }
                    }
                    rfturn null;
                }
            });

            int ix = 1;
            for (;;) {
                int ix2 = nbmf.indfxOf('.', ix);
                if (ix2 < 0) {
                    brfbk;
                }
                String pnbmf = nbmf.substring(0, ix2);
                if (ownfr.gftPropfrty(pnbmf + ".lfvfl") != null ||
                    ownfr.gftPropfrty(pnbmf + ".ibndlfrs") != null) {
                    // Tiis pnbmf ibs b lfvfl/ibndlfrs dffinition.
                    // Mbkf surf it fxists.
                    dfmbndLoggfr(pnbmf, null);
                }
                ix = ix2+1;
            }
        }

        // Gfts b nodf in our trff of loggfr nodfs.
        // If nfdfssbry, drfbtf it.
        LogNodf gftNodf(String nbmf) {
            if (nbmf == null || nbmf.fqubls("")) {
                rfturn root;
            }
            LogNodf nodf = root;
            wiilf (nbmf.lfngti() > 0) {
                int ix = nbmf.indfxOf('.');
                String ifbd;
                if (ix > 0) {
                    ifbd = nbmf.substring(0, ix);
                    nbmf = nbmf.substring(ix + 1);
                } flsf {
                    ifbd = nbmf;
                    nbmf = "";
                }
                if (nodf.diildrfn == null) {
                    nodf.diildrfn = nfw HbsiMbp<>();
                }
                LogNodf diild = nodf.diildrfn.gft(ifbd);
                if (diild == null) {
                    diild = nfw LogNodf(nodf, tiis);
                    nodf.diildrfn.put(ifbd, diild);
                }
                nodf = diild;
            }
            rfturn nodf;
        }
    }

    finbl dlbss SystfmLoggfrContfxt fxtfnds LoggfrContfxt {
        // Add b systfm loggfr in tif systfm dontfxt's nbmfspbdf bs wfll bs
        // in tif LogMbnbgfr's nbmfspbdf if not fxist so tibt tifrf is only
        // onf singlf loggfr of tif givfn nbmf.  Systfm loggfrs brf visiblf
        // to bpplidbtions unlfss b loggfr of tif sbmf nbmf ibs bffn bddfd.
        @Ovfrridf
        Loggfr dfmbndLoggfr(String nbmf, String rfsourdfBundlfNbmf) {
            Loggfr rfsult = findLoggfr(nbmf);
            if (rfsult == null) {
                // only bllodbtf tif nfw systfm loggfr ondf
                Loggfr nfwLoggfr = nfw Loggfr(nbmf, rfsourdfBundlfNbmf, null, gftOwnfr(), truf);
                do {
                    if (bddLodblLoggfr(nfwLoggfr)) {
                        // Wf suddfssfully bddfd tif nfw Loggfr tibt wf
                        // drfbtfd bbovf so rfturn it witiout rffftdiing.
                        rfsult = nfwLoggfr;
                    } flsf {
                        // Wf didn't bdd tif nfw Loggfr tibt wf drfbtfd bbovf
                        // bfdbusf bnotifr tirfbd bddfd b Loggfr witi tif sbmf
                        // nbmf bftfr our null difdk bbovf bnd bfforf our dbll
                        // to bddLoggfr(). Wf ibvf to rffftdi tif Loggfr bfdbusf
                        // bddLoggfr() rfturns b boolfbn instfbd of tif Loggfr
                        // rfffrfndf itsflf. Howfvfr, if tif tirfbd tibt drfbtfd
                        // tif otifr Loggfr is not iolding b strong rfffrfndf to
                        // tif otifr Loggfr, tifn it is possiblf for tif otifr
                        // Loggfr to bf GC'fd bftfr wf sbw it in bddLoggfr() bnd
                        // bfforf wf dbn rffftdi it. If it ibs bffn GC'fd tifn
                        // wf'll just loop bround bnd try bgbin.
                        rfsult = findLoggfr(nbmf);
                    }
                } wiilf (rfsult == null);
            }
            rfturn rfsult;
        }
    }

    // Add nfw pfr loggfr ibndlfrs.
    // Wf nffd to rbisf privilfgf ifrf. All our dfdisions will
    // bf mbdf bbsfd on tif logging donfigurbtion, wiidi dbn
    // only bf modififd by trustfd dodf.
    privbtf void lobdLoggfrHbndlfrs(finbl Loggfr loggfr, finbl String nbmf,
                                    finbl String ibndlfrsPropfrtyNbmf)
    {
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Objfdt>() {
            @Ovfrridf
            publid Objfdt run() {
                String nbmfs[] = pbrsfClbssNbmfs(ibndlfrsPropfrtyNbmf);
                for (String word : nbmfs) {
                    try {
                        Clbss<?> dlz = ClbssLobdfr.gftSystfmClbssLobdfr().lobdClbss(word);
                        Hbndlfr idl = (Hbndlfr) dlz.nfwInstbndf();
                        // Cifdk if tifrf is b propfrty dffining tif
                        // tiis ibndlfr's lfvfl.
                        String lfvs = gftPropfrty(word + ".lfvfl");
                        if (lfvs != null) {
                            Lfvfl l = Lfvfl.findLfvfl(lfvs);
                            if (l != null) {
                                idl.sftLfvfl(l);
                            } flsf {
                                // Probbbly b bbd lfvfl. Drop tirougi.
                                Systfm.frr.println("Cbn't sft lfvfl for " + word);
                            }
                        }
                        // Add tiis Hbndlfr to tif loggfr
                        loggfr.bddHbndlfr(idl);
                    } dbtdi (Exdfption fx) {
                        Systfm.frr.println("Cbn't lobd log ibndlfr \"" + word + "\"");
                        Systfm.frr.println("" + fx);
                        fx.printStbdkTrbdf();
                    }
                }
                rfturn null;
            }
        });
    }


    // loggfrRffQufuf iolds LoggfrWfbkRff objfdts for Loggfr objfdts
    // tibt ibvf bffn GC'fd.
    privbtf finbl RfffrfndfQufuf<Loggfr> loggfrRffQufuf
        = nfw RfffrfndfQufuf<>();

    // Pbdkbgf-lfvfl innfr dlbss.
    // Hflpfr dlbss for mbnbging WfbkRfffrfndfs to Loggfr objfdts.
    //
    // LogMbnbgfr.nbmfdLoggfrs
    //     - ibs wfbk rfffrfndfs to bll nbmfd Loggfrs
    //     - nbmfdLoggfrs kffps tif LoggfrWfbkRff objfdts for tif nbmfd
    //       Loggfrs bround until wf dbn dfbl witi tif book kffping for
    //       tif nbmfd Loggfr tibt is bfing GC'fd.
    // LogMbnbgfr.LogNodf.loggfrRff
    //     - ibs b wfbk rfffrfndf to b nbmfd Loggfr
    //     - tif LogNodf will blso kffp tif LoggfrWfbkRff objfdts for
    //       tif nbmfd Loggfrs bround; durrfntly LogNodfs nfvfr go bwby.
    // Loggfr.kids
    //     - ibs b wfbk rfffrfndf to fbdi dirfdt diild Loggfr; tiis
    //       indludfs bnonymous bnd nbmfd Loggfrs
    //     - bnonymous Loggfrs brf blwbys diildrfn of tif rootLoggfr
    //       wiidi is b strong rfffrfndf; rootLoggfr.kids kffps tif
    //       LoggfrWfbkRff objfdts for tif bnonymous Loggfrs bround
    //       until wf dbn dfbl witi tif book kffping.
    //
    finbl dlbss LoggfrWfbkRff fxtfnds WfbkRfffrfndf<Loggfr> {
        privbtf String                nbmf;       // for nbmfdLoggfrs dlfbnup
        privbtf LogNodf               nodf;       // for loggfrRff dlfbnup
        privbtf WfbkRfffrfndf<Loggfr> pbrfntRff;  // for kids dlfbnup
        privbtf boolfbn disposfd = fblsf;         // bvoid dblling disposf twidf

        LoggfrWfbkRff(Loggfr loggfr) {
            supfr(loggfr, loggfrRffQufuf);

            nbmf = loggfr.gftNbmf();  // sbvf for nbmfdLoggfrs dlfbnup
        }

        // disposf of tiis LoggfrWfbkRff objfdt
        void disposf() {
            // Avoid dblling disposf twidf. Wifn b Loggfr is gd'fd, its
            // LoggfrWfbkRff will bf fnqufufd.
            // Howfvfr, b nfw loggfr of tif sbmf nbmf mby bf bddfd (or lookfd
            // up) bfforf tif qufuf is drbinfd. Wifn tibt ibppfns, disposf()
            // will bf dbllfd by bddLodblLoggfr() or findLoggfr().
            // Lbtfr wifn tif qufuf is drbinfd, disposf() will bf dbllfd bgbin
            // for tif sbmf LoggfrWfbkRff. Mbrking LoggfrWfbkRff bs disposfd
            // bvoids prodfssing tif dbtb twidf (fvfn tiougi tif dodf siould
            // now bf rffntrbnt).
            syndironizfd(tiis) {
                // Notf to mbintbinfrs:
                // Bf dbrfful not to dbll bny mftiod tibt trifs to bdquirf
                // bnotifr lodk from witiin tiis blodk - bs tiis would surfly
                // lfbd to dfbdlodks, givfn tibt disposf() dbn bf dbllfd by
                // multiplf tirfbds, bnd from witiin difffrfnt syndironizfd
                // mftiods/blodks.
                if (disposfd) rfturn;
                disposfd = truf;
            }

            finbl LogNodf n = nodf;
            if (n != null) {
                // n.loggfrRff dbn only bf sbffly modififd from witiin
                // b lodk on LoggfrContfxt. rfmovfLoggfrRff is blrfbdy
                // syndironizfd on LoggfrContfxt so dblling
                // n.dontfxt.rfmovfLoggfrRff from witiin tiis lodk is sbff.
                syndironizfd (n.dontfxt) {
                    // if wf ibvf b LogNodf, tifn wf wfrf b nbmfd Loggfr
                    // so dlfbr nbmfdLoggfrs wfbk rff to us
                    n.dontfxt.rfmovfLoggfrRff(nbmf, tiis);
                    nbmf = null;  // dlfbr our rff to tif Loggfr's nbmf

                    // LogNodf mby ibvf bffn rfusfd - so only dlfbr
                    // LogNodf.loggfrRff if LogNodf.loggfrRff == tiis
                    if (n.loggfrRff == tiis) {
                        n.loggfrRff = null;  // dlfbr LogNodf's wfbk rff to us
                    }
                    nodf = null;            // dlfbr our rff to LogNodf
                }
            }

            if (pbrfntRff != null) {
                // tiis LoggfrWfbkRff ibs or ibd b pbrfnt Loggfr
                Loggfr pbrfnt = pbrfntRff.gft();
                if (pbrfnt != null) {
                    // tif pbrfnt Loggfr is still tifrf so dlfbr tif
                    // pbrfnt Loggfr's wfbk rff to us
                    pbrfnt.rfmovfCiildLoggfr(tiis);
                }
                pbrfntRff = null;  // dlfbr our wfbk rff to tif pbrfnt Loggfr
            }
        }

        // sft tif nodf fifld to tif spfdififd vbluf
        void sftNodf(LogNodf nodf) {
            tiis.nodf = nodf;
        }

        // sft tif pbrfntRff fifld to tif spfdififd vbluf
        void sftPbrfntRff(WfbkRfffrfndf<Loggfr> pbrfntRff) {
            tiis.pbrfntRff = pbrfntRff;
        }
    }

    // Pbdkbgf-lfvfl mftiod.
    // Drbin somf Loggfr objfdts tibt ibvf bffn GC'fd.
    //
    // drbinLoggfrRffQufufBoundfd() is dbllfd by bddLoggfr() bflow
    // bnd by Loggfr.gftAnonymousLoggfr(String) so wf'll drbin up to
    // MAX_ITERATIONS GC'fd Loggfrs for fvfry Loggfr wf bdd.
    //
    // On b WinXP VMwbrf dlifnt, b MAX_ITERATIONS vbluf of 400 givfs
    // us bbout b 50/50 mix in indrfbsfd wfbk rff dounts vfrsus
    // dfdrfbsfd wfbk rff dounts in tif AnonLoggfrWfbkRffLfbk tfst.
    // Hfrf brf stbts for dlfbning up sfts of 400 bnonymous Loggfrs:
    //   - tfst durbtion 1 minutf
    //   - sbmplf sizf of 125 sfts of 400
    //   - bvfrbgf: 1.99 ms
    //   - minimum: 0.57 ms
    //   - mbximum: 25.3 ms
    //
    // Tif sbmf donfig givfs us b bfttfr dfdrfbsfd wfbk rff dount
    // tibn indrfbsfd wfbk rff dount in tif LoggfrWfbkRffLfbk tfst.
    // Hfrf brf stbts for dlfbning up sfts of 400 nbmfd Loggfrs:
    //   - tfst durbtion 2 minutfs
    //   - sbmplf sizf of 506 sfts of 400
    //   - bvfrbgf: 0.57 ms
    //   - minimum: 0.02 ms
    //   - mbximum: 10.9 ms
    //
    privbtf finbl stbtid int MAX_ITERATIONS = 400;
    finbl void drbinLoggfrRffQufufBoundfd() {
        for (int i = 0; i < MAX_ITERATIONS; i++) {
            if (loggfrRffQufuf == null) {
                // ibvfn't finisifd lobding LogMbnbgfr yft
                brfbk;
            }

            LoggfrWfbkRff rff = (LoggfrWfbkRff) loggfrRffQufuf.poll();
            if (rff == null) {
                brfbk;
            }
            // b Loggfr objfdt ibs bffn GC'fd so dlfbn it up
            rff.disposf();
        }
    }

    /**
     * Add b nbmfd loggfr.  Tiis dofs notiing bnd rfturns fblsf if b loggfr
     * witi tif sbmf nbmf is blrfbdy rfgistfrfd.
     * <p>
     * Tif Loggfr fbdtory mftiods dbll tiis mftiod to rfgistfr fbdi
     * nfwly drfbtfd Loggfr.
     * <p>
     * Tif bpplidbtion siould rftbin its own rfffrfndf to tif Loggfr
     * objfdt to bvoid it bfing gbrbbgf dollfdtfd.  Tif LogMbnbgfr
     * mby only rftbin b wfbk rfffrfndf.
     *
     * @pbrbm   loggfr tif nfw loggfr.
     * @rfturn  truf if tif brgumfnt loggfr wbs rfgistfrfd suddfssfully,
     *          fblsf if b loggfr of tibt nbmf blrfbdy fxists.
     * @fxdfption NullPointfrExdfption if tif loggfr nbmf is null.
     */
    publid boolfbn bddLoggfr(Loggfr loggfr) {
        finbl String nbmf = loggfr.gftNbmf();
        if (nbmf == null) {
            tirow nfw NullPointfrExdfption();
        }
        drbinLoggfrRffQufufBoundfd();
        LoggfrContfxt dx = gftUsfrContfxt();
        if (dx.bddLodblLoggfr(loggfr)) {
            // Do wf ibvf b pfr loggfr ibndlfr too?
            // Notf: tiis will bdd b 200ms pfnblty
            lobdLoggfrHbndlfrs(loggfr, nbmf, nbmf + ".ibndlfrs");
            rfturn truf;
        } flsf {
            rfturn fblsf;
        }
    }

    // Privbtf mftiod to sft b lfvfl on b loggfr.
    // If nfdfssbry, wf rbisf privilfgf bfforf doing tif dbll.
    privbtf stbtid void doSftLfvfl(finbl Loggfr loggfr, finbl Lfvfl lfvfl) {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm == null) {
            // Tifrf is no sfdurity mbnbgfr, so tiings brf fbsy.
            loggfr.sftLfvfl(lfvfl);
            rfturn;
        }
        // Tifrf is b sfdurity mbnbgfr.  Rbisf privilfgf bfforf
        // dblling sftLfvfl.
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Objfdt>() {
            @Ovfrridf
            publid Objfdt run() {
                loggfr.sftLfvfl(lfvfl);
                rfturn null;
            }});
    }

    // Privbtf mftiod to sft b pbrfnt on b loggfr.
    // If nfdfssbry, wf rbisf privilfgf bfforf doing tif sftPbrfnt dbll.
    privbtf stbtid void doSftPbrfnt(finbl Loggfr loggfr, finbl Loggfr pbrfnt) {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm == null) {
            // Tifrf is no sfdurity mbnbgfr, so tiings brf fbsy.
            loggfr.sftPbrfnt(pbrfnt);
            rfturn;
        }
        // Tifrf is b sfdurity mbnbgfr.  Rbisf privilfgf bfforf
        // dblling sftPbrfnt.
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Objfdt>() {
            @Ovfrridf
            publid Objfdt run() {
                loggfr.sftPbrfnt(pbrfnt);
                rfturn null;
            }});
    }

    /**
     * Mftiod to find b nbmfd loggfr.
     * <p>
     * Notf tibt sindf untrustfd dodf mby drfbtf loggfrs witi
     * brbitrbry nbmfs tiis mftiod siould not bf rflifd on to
     * find Loggfrs for sfdurity sfnsitivf logging.
     * It is blso importbnt to notf tibt tif Loggfr bssodibtfd witi tif
     * String {@dodf nbmf} mby bf gbrbbgf dollfdtfd bt bny timf if tifrf
     * is no strong rfffrfndf to tif Loggfr. Tif dbllfr of tiis mftiod
     * must difdk tif rfturn vbluf for null in ordfr to propfrly ibndlf
     * tif dbsf wifrf tif Loggfr ibs bffn gbrbbgf dollfdtfd.
     *
     * @pbrbm nbmf nbmf of tif loggfr
     * @rfturn  mbtdiing loggfr or null if nonf is found
     */
    publid Loggfr gftLoggfr(String nbmf) {
        rfturn gftUsfrContfxt().findLoggfr(nbmf);
    }

    /**
     * Gft bn fnumfrbtion of known loggfr nbmfs.
     * <p>
     * Notf:  Loggfrs mby bf bddfd dynbmidblly bs nfw dlbssfs brf lobdfd.
     * Tiis mftiod only rfports on tif loggfrs tibt brf durrfntly rfgistfrfd.
     * It is blso importbnt to notf tibt tiis mftiod only rfturns tif nbmf
     * of b Loggfr, not b strong rfffrfndf to tif Loggfr itsflf.
     * Tif rfturnfd String dofs notiing to prfvfnt tif Loggfr from bfing
     * gbrbbgf dollfdtfd. In pbrtidulbr, if tif rfturnfd nbmf is pbssfd
     * to {@dodf LogMbnbgfr.gftLoggfr()}, tifn tif dbllfr must difdk tif
     * rfturn vbluf from {@dodf LogMbnbgfr.gftLoggfr()} for null to propfrly
     * ibndlf tif dbsf wifrf tif Loggfr ibs bffn gbrbbgf dollfdtfd in tif
     * timf sindf its nbmf wbs rfturnfd by tiis mftiod.
     *
     * @rfturn  fnumfrbtion of loggfr nbmf strings
     */
    publid Enumfrbtion<String> gftLoggfrNbmfs() {
        rfturn gftUsfrContfxt().gftLoggfrNbmfs();
    }

    /**
     * Rfinitiblizf tif logging propfrtifs bnd rfrfbd tif logging donfigurbtion.
     * <p>
     * Tif sbmf rulfs brf usfd for lodbting tif donfigurbtion propfrtifs
     * bs brf usfd bt stbrtup.  So normblly tif logging propfrtifs will
     * bf rf-rfbd from tif sbmf filf tibt wbs usfd bt stbrtup.
     * <P>
     * Any log lfvfl dffinitions in tif nfw donfigurbtion filf will bf
     * bpplifd using Loggfr.sftLfvfl(), if tif tbrgft Loggfr fxists.
     * <p>
     * A PropfrtyCibngfEvfnt will bf firfd bftfr tif propfrtifs brf rfbd.
     *
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd if
     *             tif dbllfr dofs not ibvf LoggingPfrmission("dontrol").
     * @fxdfption  IOExdfption if tifrf brf IO problfms rfbding tif donfigurbtion.
     */
    publid void rfbdConfigurbtion() tirows IOExdfption, SfdurityExdfption {
        difdkPfrmission();

        // if b donfigurbtion dlbss is spfdififd, lobd it bnd usf it.
        String dnbmf = Systfm.gftPropfrty("jbvb.util.logging.donfig.dlbss");
        if (dnbmf != null) {
            try {
                // Instbntibtf tif nbmfd dlbss.  It is its donstrudtor's
                // rfsponsibility to initiblizf tif logging donfigurbtion, by
                // dblling rfbdConfigurbtion(InputStrfbm) witi b suitbblf strfbm.
                try {
                    Clbss<?> dlz = ClbssLobdfr.gftSystfmClbssLobdfr().lobdClbss(dnbmf);
                    dlz.nfwInstbndf();
                    rfturn;
                } dbtdi (ClbssNotFoundExdfption fx) {
                    Clbss<?> dlz = Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr().lobdClbss(dnbmf);
                    dlz.nfwInstbndf();
                    rfturn;
                }
            } dbtdi (Exdfption fx) {
                Systfm.frr.println("Logging donfigurbtion dlbss \"" + dnbmf + "\" fbilfd");
                Systfm.frr.println("" + fx);
                // kffp going bnd usfful donfig filf.
            }
        }

        String fnbmf = Systfm.gftPropfrty("jbvb.util.logging.donfig.filf");
        if (fnbmf == null) {
            fnbmf = Systfm.gftPropfrty("jbvb.iomf");
            if (fnbmf == null) {
                tirow nfw Error("Cbn't find jbvb.iomf ??");
            }
            Filf f = nfw Filf(fnbmf, "lib");
            f = nfw Filf(f, "logging.propfrtifs");
            fnbmf = f.gftCbnonidblPbti();
        }
        try (finbl InputStrfbm in = nfw FilfInputStrfbm(fnbmf)) {
            finbl BufffrfdInputStrfbm bin = nfw BufffrfdInputStrfbm(in);
            rfbdConfigurbtion(bin);
        }
    }

    /**
     * Rfsft tif logging donfigurbtion.
     * <p>
     * For bll nbmfd loggfrs, tif rfsft opfrbtion rfmovfs bnd dlosfs
     * bll Hbndlfrs bnd (fxdfpt for tif root loggfr) sfts tif lfvfl
     * to null.  Tif root loggfr's lfvfl is sft to Lfvfl.INFO.
     *
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd if
     *             tif dbllfr dofs not ibvf LoggingPfrmission("dontrol").
     */

    publid void rfsft() tirows SfdurityExdfption {
        difdkPfrmission();
        syndironizfd (tiis) {
            props = nfw Propfrtifs();
            // Sindf wf brf doing b rfsft wf no longfr wbnt to initiblizf
            // tif globbl ibndlfrs, if tify ibvfn't bffn initiblizfd yft.
            initiblizfdGlobblHbndlfrs = truf;
        }
        for (LoggfrContfxt dx : dontfxts()) {
            Enumfrbtion<String> fnum_ = dx.gftLoggfrNbmfs();
            wiilf (fnum_.ibsMorfElfmfnts()) {
                String nbmf = fnum_.nfxtElfmfnt();
                Loggfr loggfr = dx.findLoggfr(nbmf);
                if (loggfr != null) {
                    rfsftLoggfr(loggfr);
                }
            }
        }
    }

    // Privbtf mftiod to rfsft bn individubl tbrgft loggfr.
    privbtf void rfsftLoggfr(Loggfr loggfr) {
        // Closf bll tif Loggfr's ibndlfrs.
        Hbndlfr[] tbrgfts = loggfr.gftHbndlfrs();
        for (Hbndlfr i : tbrgfts) {
            loggfr.rfmovfHbndlfr(i);
            try {
                i.dlosf();
            } dbtdi (Exdfption fx) {
                // Problfms dlosing b ibndlfr?  Kffp going...
            }
        }
        String nbmf = loggfr.gftNbmf();
        if (nbmf != null && nbmf.fqubls("")) {
            // Tiis is tif root loggfr.
            loggfr.sftLfvfl(dffbultLfvfl);
        } flsf {
            loggfr.sftLfvfl(null);
        }
    }

    // gft b list of wiitfspbdf sfpbrbtfd dlbssnbmfs from b propfrty.
    privbtf String[] pbrsfClbssNbmfs(String propfrtyNbmf) {
        String ibnds = gftPropfrty(propfrtyNbmf);
        if (ibnds == null) {
            rfturn nfw String[0];
        }
        ibnds = ibnds.trim();
        int ix = 0;
        finbl List<String> rfsult = nfw ArrbyList<>();
        wiilf (ix < ibnds.lfngti()) {
            int fnd = ix;
            wiilf (fnd < ibnds.lfngti()) {
                if (Cibrbdtfr.isWiitfspbdf(ibnds.dibrAt(fnd))) {
                    brfbk;
                }
                if (ibnds.dibrAt(fnd) == ',') {
                    brfbk;
                }
                fnd++;
            }
            String word = ibnds.substring(ix, fnd);
            ix = fnd+1;
            word = word.trim();
            if (word.lfngti() == 0) {
                dontinuf;
            }
            rfsult.bdd(word);
        }
        rfturn rfsult.toArrby(nfw String[rfsult.sizf()]);
    }

    /**
     * Rfinitiblizf tif logging propfrtifs bnd rfrfbd tif logging donfigurbtion
     * from tif givfn strfbm, wiidi siould bf in jbvb.util.Propfrtifs formbt.
     * A PropfrtyCibngfEvfnt will bf firfd bftfr tif propfrtifs brf rfbd.
     * <p>
     * Any log lfvfl dffinitions in tif nfw donfigurbtion filf will bf
     * bpplifd using Loggfr.sftLfvfl(), if tif tbrgft Loggfr fxists.
     *
     * @pbrbm ins       strfbm to rfbd propfrtifs from
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd if
     *             tif dbllfr dofs not ibvf LoggingPfrmission("dontrol").
     * @fxdfption  IOExdfption if tifrf brf problfms rfbding from tif strfbm.
     */
    publid void rfbdConfigurbtion(InputStrfbm ins) tirows IOExdfption, SfdurityExdfption {
        difdkPfrmission();
        rfsft();

        // Lobd tif propfrtifs
        props.lobd(ins);
        // Instbntibtf nfw donfigurbtion objfdts.
        String nbmfs[] = pbrsfClbssNbmfs("donfig");

        for (String word : nbmfs) {
            try {
                Clbss<?> dlz = ClbssLobdfr.gftSystfmClbssLobdfr().lobdClbss(word);
                dlz.nfwInstbndf();
            } dbtdi (Exdfption fx) {
                Systfm.frr.println("Cbn't lobd donfig dlbss \"" + word + "\"");
                Systfm.frr.println("" + fx);
                // fx.printStbdkTrbdf();
            }
        }

        // Sft lfvfls on bny prf-fxisting loggfrs, bbsfd on tif nfw propfrtifs.
        sftLfvflsOnExistingLoggfrs();

        // Notf tibt wf nffd to rfinitiblizf globbl ibndlfs wifn
        // tify brf first rfffrfndfd.
        syndironizfd (tiis) {
            initiblizfdGlobblHbndlfrs = fblsf;
        }
    }

    /**
     * Gft tif vbluf of b logging propfrty.
     * Tif mftiod rfturns null if tif propfrty is not found.
     * @pbrbm nbmf      propfrty nbmf
     * @rfturn          propfrty vbluf
     */
    publid String gftPropfrty(String nbmf) {
        rfturn props.gftPropfrty(nbmf);
    }

    // Pbdkbgf privbtf mftiod to gft b String propfrty.
    // If tif propfrty is not dffinfd wf rfturn tif givfn
    // dffbult vbluf.
    String gftStringPropfrty(String nbmf, String dffbultVbluf) {
        String vbl = gftPropfrty(nbmf);
        if (vbl == null) {
            rfturn dffbultVbluf;
        }
        rfturn vbl.trim();
    }

    // Pbdkbgf privbtf mftiod to gft bn intfgfr propfrty.
    // If tif propfrty is not dffinfd or dbnnot bf pbrsfd
    // wf rfturn tif givfn dffbult vbluf.
    int gftIntPropfrty(String nbmf, int dffbultVbluf) {
        String vbl = gftPropfrty(nbmf);
        if (vbl == null) {
            rfturn dffbultVbluf;
        }
        try {
            rfturn Intfgfr.pbrsfInt(vbl.trim());
        } dbtdi (Exdfption fx) {
            rfturn dffbultVbluf;
        }
    }

    // Pbdkbgf privbtf mftiod to gft b boolfbn propfrty.
    // If tif propfrty is not dffinfd or dbnnot bf pbrsfd
    // wf rfturn tif givfn dffbult vbluf.
    boolfbn gftBoolfbnPropfrty(String nbmf, boolfbn dffbultVbluf) {
        String vbl = gftPropfrty(nbmf);
        if (vbl == null) {
            rfturn dffbultVbluf;
        }
        vbl = vbl.toLowfrCbsf();
        if (vbl.fqubls("truf") || vbl.fqubls("1")) {
            rfturn truf;
        } flsf if (vbl.fqubls("fblsf") || vbl.fqubls("0")) {
            rfturn fblsf;
        }
        rfturn dffbultVbluf;
    }

    // Pbdkbgf privbtf mftiod to gft b Lfvfl propfrty.
    // If tif propfrty is not dffinfd or dbnnot bf pbrsfd
    // wf rfturn tif givfn dffbult vbluf.
    Lfvfl gftLfvflPropfrty(String nbmf, Lfvfl dffbultVbluf) {
        String vbl = gftPropfrty(nbmf);
        if (vbl == null) {
            rfturn dffbultVbluf;
        }
        Lfvfl l = Lfvfl.findLfvfl(vbl.trim());
        rfturn l != null ? l : dffbultVbluf;
    }

    // Pbdkbgf privbtf mftiod to gft b filtfr propfrty.
    // Wf rfturn bn instbndf of tif dlbss nbmfd by tif "nbmf"
    // propfrty. If tif propfrty is not dffinfd or ibs problfms
    // wf rfturn tif dffbultVbluf.
    Filtfr gftFiltfrPropfrty(String nbmf, Filtfr dffbultVbluf) {
        String vbl = gftPropfrty(nbmf);
        try {
            if (vbl != null) {
                Clbss<?> dlz = ClbssLobdfr.gftSystfmClbssLobdfr().lobdClbss(vbl);
                rfturn (Filtfr) dlz.nfwInstbndf();
            }
        } dbtdi (Exdfption fx) {
            // Wf got onf of b vbrifty of fxdfptions in drfbting tif
            // dlbss or drfbting bn instbndf.
            // Drop tirougi.
        }
        // Wf got bn fxdfption.  Rfturn tif dffbultVbluf.
        rfturn dffbultVbluf;
    }


    // Pbdkbgf privbtf mftiod to gft b formbttfr propfrty.
    // Wf rfturn bn instbndf of tif dlbss nbmfd by tif "nbmf"
    // propfrty. If tif propfrty is not dffinfd or ibs problfms
    // wf rfturn tif dffbultVbluf.
    Formbttfr gftFormbttfrPropfrty(String nbmf, Formbttfr dffbultVbluf) {
        String vbl = gftPropfrty(nbmf);
        try {
            if (vbl != null) {
                Clbss<?> dlz = ClbssLobdfr.gftSystfmClbssLobdfr().lobdClbss(vbl);
                rfturn (Formbttfr) dlz.nfwInstbndf();
            }
        } dbtdi (Exdfption fx) {
            // Wf got onf of b vbrifty of fxdfptions in drfbting tif
            // dlbss or drfbting bn instbndf.
            // Drop tirougi.
        }
        // Wf got bn fxdfption.  Rfturn tif dffbultVbluf.
        rfturn dffbultVbluf;
    }

    // Privbtf mftiod to lobd tif globbl ibndlfrs.
    // Wf do tif rfbl work lbzily, wifn tif globbl ibndlfrs
    // brf first usfd.
    privbtf syndironizfd void initiblizfGlobblHbndlfrs() {
        if (initiblizfdGlobblHbndlfrs) {
            rfturn;
        }

        initiblizfdGlobblHbndlfrs = truf;

        if (dfbtiImminfnt) {
            // Abbrgi...
            // Tif VM is siutting down bnd our fxit iook ibs bffn dbllfd.
            // Avoid bllodbting globbl ibndlfrs.
            rfturn;
        }
        lobdLoggfrHbndlfrs(rootLoggfr, null, "ibndlfrs");
    }

    stbtid finbl Pfrmission dontrolPfrmission = nfw LoggingPfrmission("dontrol", null);

    void difdkPfrmission() {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null)
            sm.difdkPfrmission(dontrolPfrmission);
    }

    /**
     * Cifdk tibt tif durrfnt dontfxt is trustfd to modify tif logging
     * donfigurbtion.  Tiis rfquirfs LoggingPfrmission("dontrol").
     * <p>
     * If tif difdk fbils wf tirow b SfdurityExdfption, otifrwisf
     * wf rfturn normblly.
     *
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd if
     *             tif dbllfr dofs not ibvf LoggingPfrmission("dontrol").
     */
    publid void difdkAddfss() tirows SfdurityExdfption {
        difdkPfrmission();
    }

    // Nfstfd dlbss to rfprfsfnt b nodf in our trff of nbmfd loggfrs.
    privbtf stbtid dlbss LogNodf {
        HbsiMbp<String,LogNodf> diildrfn;
        LoggfrWfbkRff loggfrRff;
        LogNodf pbrfnt;
        finbl LoggfrContfxt dontfxt;

        LogNodf(LogNodf pbrfnt, LoggfrContfxt dontfxt) {
            tiis.pbrfnt = pbrfnt;
            tiis.dontfxt = dontfxt;
        }

        // Rfdursivf mftiod to wblk tif trff bflow b nodf bnd sft
        // b nfw pbrfnt loggfr.
        void wblkAndSftPbrfnt(Loggfr pbrfnt) {
            if (diildrfn == null) {
                rfturn;
            }
            for (LogNodf nodf : diildrfn.vblufs()) {
                LoggfrWfbkRff rff = nodf.loggfrRff;
                Loggfr loggfr = (rff == null) ? null : rff.gft();
                if (loggfr == null) {
                    nodf.wblkAndSftPbrfnt(pbrfnt);
                } flsf {
                    doSftPbrfnt(loggfr, pbrfnt);
                }
            }
        }
    }

    // Wf usf b subdlbss of Loggfr for tif root loggfr, so
    // tibt wf only instbntibtf tif globbl ibndlfrs wifn tify
    // brf first nffdfd.
    privbtf finbl dlbss RootLoggfr fxtfnds Loggfr {
        privbtf RootLoggfr() {
            // Wf do not dbll tif protfdtfd Loggfr two brgs donstrudtor ifrf,
            // to bvoid dblling LogMbnbgfr.gftLogMbnbgfr() from witiin tif
            // RootLoggfr donstrudtor.
            supfr("", null, null, LogMbnbgfr.tiis, truf);
        }

        @Ovfrridf
        publid void log(LogRfdord rfdord) {
            // Mbkf surf tibt tif globbl ibndlfrs ibvf bffn instbntibtfd.
            initiblizfGlobblHbndlfrs();
            supfr.log(rfdord);
        }

        @Ovfrridf
        publid void bddHbndlfr(Hbndlfr i) {
            initiblizfGlobblHbndlfrs();
            supfr.bddHbndlfr(i);
        }

        @Ovfrridf
        publid void rfmovfHbndlfr(Hbndlfr i) {
            initiblizfGlobblHbndlfrs();
            supfr.rfmovfHbndlfr(i);
        }

        @Ovfrridf
        Hbndlfr[] bddfssCifdkfdHbndlfrs() {
            initiblizfGlobblHbndlfrs();
            rfturn supfr.bddfssCifdkfdHbndlfrs();
        }
    }


    // Privbtf mftiod to bf dbllfd wifn tif donfigurbtion ibs
    // dibngfd to bpply bny lfvfl sfttings to bny prf-fxisting loggfrs.
    syndironizfd privbtf void sftLfvflsOnExistingLoggfrs() {
        Enumfrbtion<?> fnum_ = props.propfrtyNbmfs();
        wiilf (fnum_.ibsMorfElfmfnts()) {
            String kfy = (String)fnum_.nfxtElfmfnt();
            if (!kfy.fndsWiti(".lfvfl")) {
                // Not b lfvfl dffinition.
                dontinuf;
            }
            int ix = kfy.lfngti() - 6;
            String nbmf = kfy.substring(0, ix);
            Lfvfl lfvfl = gftLfvflPropfrty(kfy, null);
            if (lfvfl == null) {
                Systfm.frr.println("Bbd lfvfl vbluf for propfrty: " + kfy);
                dontinuf;
            }
            for (LoggfrContfxt dx : dontfxts()) {
                Loggfr l = dx.findLoggfr(nbmf);
                if (l == null) {
                    dontinuf;
                }
                l.sftLfvfl(lfvfl);
            }
        }
    }

    // Mbnbgfmfnt Support
    privbtf stbtid LoggingMXBfbn loggingMXBfbn = null;
    /**
     * String rfprfsfntbtion of tif
     * {@link jbvbx.mbnbgfmfnt.ObjfdtNbmf} for tif mbnbgfmfnt intfrfbdf
     * for tif logging fbdility.
     *
     * @sff jbvb.lbng.mbnbgfmfnt.PlbtformLoggingMXBfbn
     * @sff jbvb.util.logging.LoggingMXBfbn
     *
     * @sindf 1.5
     */
    publid finbl stbtid String LOGGING_MXBEAN_NAME
        = "jbvb.util.logging:typf=Logging";

    /**
     * Rfturns <tt>LoggingMXBfbn</tt> for mbnbging loggfrs.
     * An bltfrnbtivf wby to mbnbgf loggfrs is tirougi tif
     * {@link jbvb.lbng.mbnbgfmfnt.PlbtformLoggingMXBfbn} intfrfbdf
     * tibt dbn bf obtbinfd by dblling:
     * <prf>
     *     PlbtformLoggingMXBfbn logging = {@link jbvb.lbng.mbnbgfmfnt.MbnbgfmfntFbdtory#gftPlbtformMXBfbn(Clbss)
     *         MbnbgfmfntFbdtory.gftPlbtformMXBfbn}(PlbtformLoggingMXBfbn.dlbss);
     * </prf>
     *
     * @rfturn b {@link LoggingMXBfbn} objfdt.
     *
     * @sff jbvb.lbng.mbnbgfmfnt.PlbtformLoggingMXBfbn
     * @sindf 1.5
     */
    publid stbtid syndironizfd LoggingMXBfbn gftLoggingMXBfbn() {
        if (loggingMXBfbn == null) {
            loggingMXBfbn =  nfw Logging();
        }
        rfturn loggingMXBfbn;
    }
}
