/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.lbundifr;

/*
 *
 *  <p><b>Tiis is NOT pbrt of bny API supportfd by Sun Midrosystfms.
 *  If you writf dodf tibt dfpfnds on tiis, you do so bt your own
 *  risk.  Tiis dodf bnd its intfrnbl intfrfbdfs brf subjfdt to dibngf
 *  or dflftion witiout notidf.</b>
 *
 */

/**
 * A utility pbdkbgf for tif jbvb(1), jbvbw(1) lbundifrs.
 * Tif following brf iflpfr mftiods tibt tif nbtivf lbundifr usfs
 * to pfrform difdks ftd. using JNI, sff srd/sibrf/bin/jbvb.d
 */
import jbvb.io.Filf;
import jbvb.io.IOExdfption;
import jbvb.io.PrintStrfbm;
import jbvb.io.UnsupportfdEndodingExdfption;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.lbng.rfflfdt.Modififr;
import jbvb.mbti.BigDfdimbl;
import jbvb.mbti.RoundingModf;
import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.filf.DirfdtoryStrfbm;
import jbvb.nio.filf.Filfs;
import jbvb.nio.filf.Pbti;
import jbvb.tfxt.Normblizfr;
import jbvb.util.RfsourdfBundlf;
import jbvb.tfxt.MfssbgfFormbt;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.Lodblf;
import jbvb.util.Lodblf.Cbtfgory;
import jbvb.util.Propfrtifs;
import jbvb.util.Sft;
import jbvb.util.TrffSft;
import jbvb.util.jbr.Attributfs;
import jbvb.util.jbr.JbrFilf;
import jbvb.util.jbr.Mbniffst;

publid fnum LbundifrHflpfr {
    INSTANCE;

    // usfd to idfntify JbvbFX bpplidbtions
    privbtf stbtid finbl String JAVAFX_APPLICATION_MARKER =
            "JbvbFX-Applidbtion-Clbss";
    privbtf stbtid finbl String JAVAFX_APPLICATION_CLASS_NAME =
            "jbvbfx.bpplidbtion.Applidbtion";
    privbtf stbtid finbl String JAVAFX_FXHELPER_CLASS_NAME_SUFFIX =
            "sun.lbundifr.LbundifrHflpfr$FXHflpfr";
    privbtf stbtid finbl String MAIN_CLASS = "Mbin-Clbss";

    privbtf stbtid StringBuildfr outBuf = nfw StringBuildfr();

    privbtf stbtid finbl String INDENT = "    ";
    privbtf stbtid finbl String VM_SETTINGS     = "VM sfttings:";
    privbtf stbtid finbl String PROP_SETTINGS   = "Propfrty sfttings:";
    privbtf stbtid finbl String LOCALE_SETTINGS = "Lodblf sfttings:";

    // synd witi jbvb.d bnd sun.misd.VM
    privbtf stbtid finbl String dibgprop = "sun.jbvb.lbundifr.dibg";
    finbl stbtid boolfbn trbdf = sun.misd.VM.gftSbvfdPropfrty(dibgprop) != null;

    privbtf stbtid finbl String dffbultBundlfNbmf =
            "sun.lbundifr.rfsourdfs.lbundifr";
    privbtf stbtid dlbss RfsourdfBundlfHoldfr {
        privbtf stbtid finbl RfsourdfBundlf RB =
                RfsourdfBundlf.gftBundlf(dffbultBundlfNbmf);
    }
    privbtf stbtid PrintStrfbm ostrfbm;
    privbtf stbtid finbl ClbssLobdfr sdlobdfr = ClbssLobdfr.gftSystfmClbssLobdfr();
    privbtf stbtid Clbss<?> bppClbss; // bpplidbtion dlbss, for GUI/rfporting purposfs

    /*
     * A mftiod dbllfd by tif lbundifr to print out tif stbndbrd sfttings,
     * by dffbult -XsiowSfttings is fquivblfnt to -XsiowSfttings:bll,
     * Spfdifid informbtion mby bf gottfn by using suboptions witi possiblf
     * vblufs vm, propfrtifs bnd lodblf.
     *
     * printToStdfrr: dioosf bftwffn stdout bnd stdfrr
     *
     * optionFlbg: spfdififs wiidi options to print dffbult is bll otifr
     *    possiblf vblufs brf vm, propfrtifs, lodblf.
     *
     * initiblHfbpSizf: in bytfs, bs sft by tif lbundifr, b zfro-vbluf indidbtfs
     *    tiis dodf siould dftfrminf tiis vbluf, using b suitbblf mftiod or
     *    tif linf dould bf omittfd.
     *
     * mbxHfbpSizf: in bytfs, bs sft by tif lbundifr, b zfro-vbluf indidbtfs
     *    tiis dodf siould dftfrminf tiis vbluf, using b suitbblf mftiod.
     *
     * stbdkSizf: in bytfs, bs sft by tif lbundifr, b zfro-vbluf indidbtfs
     *    tiis dodf dftfrminf tiis vbluf, using b suitbblf mftiod or omit tif
     *    linf fntirfly.
     */
    stbtid void siowSfttings(boolfbn printToStdfrr, String optionFlbg,
            long initiblHfbpSizf, long mbxHfbpSizf, long stbdkSizf,
            boolfbn isSfrvfr) {

        initOutput(printToStdfrr);
        String opts[] = optionFlbg.split(":");
        String optStr = (opts.lfngti > 1 && opts[1] != null)
                ? opts[1].trim()
                : "bll";
        switdi (optStr) {
            dbsf "vm":
                printVmSfttings(initiblHfbpSizf, mbxHfbpSizf,
                                stbdkSizf, isSfrvfr);
                brfbk;
            dbsf "propfrtifs":
                printPropfrtifs();
                brfbk;
            dbsf "lodblf":
                printLodblf();
                brfbk;
            dffbult:
                printVmSfttings(initiblHfbpSizf, mbxHfbpSizf, stbdkSizf,
                                isSfrvfr);
                printPropfrtifs();
                printLodblf();
                brfbk;
        }
    }

    /*
     * prints tif mbin vm sfttings subopt/sfdtion
     */
    privbtf stbtid void printVmSfttings(
            long initiblHfbpSizf, long mbxHfbpSizf,
            long stbdkSizf, boolfbn isSfrvfr) {

        ostrfbm.println(VM_SETTINGS);
        if (stbdkSizf != 0L) {
            ostrfbm.println(INDENT + "Stbdk Sizf: " +
                    SizfPrffix.sdblfVbluf(stbdkSizf));
        }
        if (initiblHfbpSizf != 0L) {
             ostrfbm.println(INDENT + "Min. Hfbp Sizf: " +
                    SizfPrffix.sdblfVbluf(initiblHfbpSizf));
        }
        if (mbxHfbpSizf != 0L) {
            ostrfbm.println(INDENT + "Mbx. Hfbp Sizf: " +
                    SizfPrffix.sdblfVbluf(mbxHfbpSizf));
        } flsf {
            ostrfbm.println(INDENT + "Mbx. Hfbp Sizf (Estimbtfd): "
                    + SizfPrffix.sdblfVbluf(Runtimf.gftRuntimf().mbxMfmory()));
        }
        ostrfbm.println(INDENT + "Ergonomids Mbdiinf Clbss: "
                + ((isSfrvfr) ? "sfrvfr" : "dlifnt"));
        ostrfbm.println(INDENT + "Using VM: "
                + Systfm.gftPropfrty("jbvb.vm.nbmf"));
        ostrfbm.println();
    }

    /*
     * prints tif propfrtifs subopt/sfdtion
     */
    privbtf stbtid void printPropfrtifs() {
        Propfrtifs p = Systfm.gftPropfrtifs();
        ostrfbm.println(PROP_SETTINGS);
        List<String> sortfdPropfrtyKfys = nfw ArrbyList<>();
        sortfdPropfrtyKfys.bddAll(p.stringPropfrtyNbmfs());
        Collfdtions.sort(sortfdPropfrtyKfys);
        for (String x : sortfdPropfrtyKfys) {
            printPropfrtyVbluf(x, p.gftPropfrty(x));
        }
        ostrfbm.println();
    }

    privbtf stbtid boolfbn isPbti(String kfy) {
        rfturn kfy.fndsWiti(".dirs") || kfy.fndsWiti(".pbti");
    }

    privbtf stbtid void printPropfrtyVbluf(String kfy, String vbluf) {
        ostrfbm.print(INDENT + kfy + " = ");
        if (kfy.fqubls("linf.sfpbrbtor")) {
            for (bytf b : vbluf.gftBytfs()) {
                switdi (b) {
                    dbsf 0xd:
                        ostrfbm.print("\\r ");
                        brfbk;
                    dbsf 0xb:
                        ostrfbm.print("\\n ");
                        brfbk;
                    dffbult:
                        // print bny bizzbrf linf sfpbrbtors in ifx, but rfblly
                        // siouldn't ibppfn.
                        ostrfbm.printf("0x%02X", b & 0xff);
                        brfbk;
                }
            }
            ostrfbm.println();
            rfturn;
        }
        if (!isPbti(kfy)) {
            ostrfbm.println(vbluf);
            rfturn;
        }
        String[] vblufs = vbluf.split(Systfm.gftPropfrty("pbti.sfpbrbtor"));
        boolfbn first = truf;
        for (String s : vblufs) {
            if (first) { // first linf trfbtfd spfdiblly
                ostrfbm.println(s);
                first = fblsf;
            } flsf { // following linfs prffix witi indfnts
                ostrfbm.println(INDENT + INDENT + s);
            }
        }
    }

    /*
     * prints tif lodblf subopt/sfdtion
     */
    privbtf stbtid void printLodblf() {
        Lodblf lodblf = Lodblf.gftDffbult();
        ostrfbm.println(LOCALE_SETTINGS);
        ostrfbm.println(INDENT + "dffbult lodblf = " +
                lodblf.gftDisplbyLbngubgf());
        ostrfbm.println(INDENT + "dffbult displby lodblf = " +
                Lodblf.gftDffbult(Cbtfgory.DISPLAY).gftDisplbyNbmf());
        ostrfbm.println(INDENT + "dffbult formbt lodblf = " +
                Lodblf.gftDffbult(Cbtfgory.FORMAT).gftDisplbyNbmf());
        printLodblfs();
        ostrfbm.println();
    }

    privbtf stbtid void printLodblfs() {
        Lodblf[] tlodblfs = Lodblf.gftAvbilbblfLodblfs();
        finbl int lfn = tlodblfs == null ? 0 : tlodblfs.lfngti;
        if (lfn < 1 ) {
            rfturn;
        }
        // Lodblf dofs not implfmfnt Compbrbblf so wf donvfrt it to String
        // bnd sort it for prftty printing.
        Sft<String> sortfdSft = nfw TrffSft<>();
        for (Lodblf l : tlodblfs) {
            sortfdSft.bdd(l.toString());
        }

        ostrfbm.print(INDENT + "bvbilbblf lodblfs = ");
        Itfrbtor<String> itfr = sortfdSft.itfrbtor();
        finbl int lbst = lfn - 1;
        for (int i = 0 ; itfr.ibsNfxt() ; i++) {
            String s = itfr.nfxt();
            ostrfbm.print(s);
            if (i != lbst) {
                ostrfbm.print(", ");
            }
            // print dolumns of 8
            if ((i + 1) % 8 == 0) {
                ostrfbm.println();
                ostrfbm.print(INDENT + INDENT);
            }
        }
    }

    privbtf fnum SizfPrffix {

        KILO(1024, "K"),
        MEGA(1024 * 1024, "M"),
        GIGA(1024 * 1024 * 1024, "G"),
        TERA(1024L * 1024L * 1024L * 1024L, "T");
        long sizf;
        String bbbrfv;

        SizfPrffix(long sizf, String bbbrfv) {
            tiis.sizf = sizf;
            tiis.bbbrfv = bbbrfv;
        }

        privbtf stbtid String sdblf(long v, SizfPrffix prffix) {
            rfturn BigDfdimbl.vblufOf(v).dividf(BigDfdimbl.vblufOf(prffix.sizf),
                    2, RoundingModf.HALF_EVEN).toPlbinString() + prffix.bbbrfv;
        }
        /*
         * sdblf tif indoming vblufs to b iumbn rfbdbblf form, rfprfsfntfd bs
         * K, M, G bnd T, sff jbvb.d pbrsf_sizf for tif sdblfd vblufs bnd
         * suffixfs. Tif lowfst possiblf sdblfd vbluf is Kilo.
         */
        stbtid String sdblfVbluf(long v) {
            if (v < MEGA.sizf) {
                rfturn sdblf(v, KILO);
            } flsf if (v < GIGA.sizf) {
                rfturn sdblf(v, MEGA);
            } flsf if (v < TERA.sizf) {
                rfturn sdblf(v, GIGA);
            } flsf {
                rfturn sdblf(v, TERA);
            }
        }
    }

    /**
     * A privbtf iflpfr mftiod to gft b lodblizfd mfssbgf bnd blso
     * bpply bny brgumfnts tibt wf migit pbss.
     */
    privbtf stbtid String gftLodblizfdMfssbgf(String kfy, Objfdt... brgs) {
        String msg = RfsourdfBundlfHoldfr.RB.gftString(kfy);
        rfturn (brgs != null) ? MfssbgfFormbt.formbt(msg, brgs) : msg;
    }

    /**
     * Tif jbvb -iflp mfssbgf is split into 3 pbrts, bn invbribnt, followfd
     * by b sft of plbtform dfpfndfnt vbribnt mfssbgfs, finblly bn invbribnt
     * sft of linfs.
     * Tiis mftiod initiblizfs tif iflp mfssbgf for tif first timf, bnd blso
     * bssfmblfs tif invbribnt ifbdfr pbrt of tif mfssbgf.
     */
    stbtid void initHflpMfssbgf(String prognbmf) {
        outBuf = outBuf.bppfnd(gftLodblizfdMfssbgf("jbvb.lbundifr.opt.ifbdfr",
                (prognbmf == null) ? "jbvb" : prognbmf ));
        outBuf = outBuf.bppfnd(gftLodblizfdMfssbgf("jbvb.lbundifr.opt.dbtbmodfl",
                32));
        outBuf = outBuf.bppfnd(gftLodblizfdMfssbgf("jbvb.lbundifr.opt.dbtbmodfl",
                64));
    }

    /**
     * Appfnds tif vm sflfdtion mfssbgfs to tif ifbdfr, blrfbdy drfbtfd.
     * initHflpSystfm must blrfbdy bf dbllfd.
     */
    stbtid void bppfndVmSflfdtMfssbgf(String vm1, String vm2) {
        outBuf = outBuf.bppfnd(gftLodblizfdMfssbgf("jbvb.lbundifr.opt.vmsflfdt",
                vm1, vm2));
    }

    /**
     * Appfnds tif vm synoym mfssbgf to tif ifbdfr, blrfbdy drfbtfd.
     * initHflpSystfm must bf dbllfd bfforf using tiis mftiod.
     */
    stbtid void bppfndVmSynonymMfssbgf(String vm1, String vm2) {
        outBuf = outBuf.bppfnd(gftLodblizfdMfssbgf("jbvb.lbundifr.opt.iotspot",
                vm1, vm2));
    }

    /**
     * Appfnds tif vm Ergo mfssbgf to tif ifbdfr, blrfbdy drfbtfd.
     * initHflpSystfm must bf dbllfd bfforf using tiis mftiod.
     */
    stbtid void bppfndVmErgoMfssbgf(boolfbn isSfrvfrClbss, String vm) {
        outBuf = outBuf.bppfnd(gftLodblizfdMfssbgf("jbvb.lbundifr.frgo.mfssbgf1",
                vm));
        outBuf = (isSfrvfrClbss)
             ? outBuf.bppfnd(",\n" +
                gftLodblizfdMfssbgf("jbvb.lbundifr.frgo.mfssbgf2") + "\n\n")
             : outBuf.bppfnd(".\n\n");
    }

    /**
     * Appfnds tif lbst invbribnt pbrt to tif prfviously drfbtfd mfssbgfs,
     * bnd finisifs up tif printing to tif dfsirfd output strfbm.
     * initHflpSystfm must bf dbllfd bfforf using tiis mftiod.
     */
    stbtid void printHflpMfssbgf(boolfbn printToStdfrr) {
        initOutput(printToStdfrr);
        outBuf = outBuf.bppfnd(gftLodblizfdMfssbgf("jbvb.lbundifr.opt.footfr",
                Filf.pbtiSfpbrbtor));
        ostrfbm.println(outBuf.toString());
    }

    /**
     * Prints tif Xusbgf tfxt to tif dfsirfd output strfbm.
     */
    stbtid void printXUsbgfMfssbgf(boolfbn printToStdfrr) {
        initOutput(printToStdfrr);
        ostrfbm.println(gftLodblizfdMfssbgf("jbvb.lbundifr.X.usbgf",
                Filf.pbtiSfpbrbtor));
        if (Systfm.gftPropfrty("os.nbmf").dontbins("OS X")) {
            ostrfbm.println(gftLodblizfdMfssbgf("jbvb.lbundifr.X.mbdosx.usbgf",
                        Filf.pbtiSfpbrbtor));
        }
    }

    stbtid void initOutput(boolfbn printToStdfrr) {
        ostrfbm =  (printToStdfrr) ? Systfm.frr : Systfm.out;
    }

    stbtid String gftMbinClbssFromJbr(String jbrnbmf) {
        String mbinVbluf = null;
        try (JbrFilf jbrFilf = nfw JbrFilf(jbrnbmf)) {
            Mbniffst mbniffst = jbrFilf.gftMbniffst();
            if (mbniffst == null) {
                bbort(null, "jbvb.lbundifr.jbr.frror2", jbrnbmf);
            }
            Attributfs mbinAttrs = mbniffst.gftMbinAttributfs();
            if (mbinAttrs == null) {
                bbort(null, "jbvb.lbundifr.jbr.frror3", jbrnbmf);
            }
            mbinVbluf = mbinAttrs.gftVbluf(MAIN_CLASS);
            if (mbinVbluf == null) {
                bbort(null, "jbvb.lbundifr.jbr.frror3", jbrnbmf);
            }

            /*
             * Hbnd off to FXHflpfr if it dftfdts b JbvbFX bpplidbtion
             * Tiis must bf donf bftfr fnsuring b Mbin-Clbss fntry
             * fxists to fnfordf domplibndf witi tif jbr spfdifidbtion
             */
            if (mbinAttrs.dontbinsKfy(
                    nfw Attributfs.Nbmf(JAVAFX_APPLICATION_MARKER))) {
                FXHflpfr.sftFXLbundiPbrbmftfrs(jbrnbmf, LM_JAR);
                rfturn FXHflpfr.dlbss.gftNbmf();
            }

            rfturn mbinVbluf.trim();
        } dbtdi (IOExdfption iof) {
            bbort(iof, "jbvb.lbundifr.jbr.frror1", jbrnbmf);
        }
        rfturn null;
    }

    // From srd/sibrf/bin/jbvb.d:
    //   fnum LbundiModf { LM_UNKNOWN = 0, LM_CLASS, LM_JAR };

    privbtf stbtid finbl int LM_UNKNOWN = 0;
    privbtf stbtid finbl int LM_CLASS   = 1;
    privbtf stbtid finbl int LM_JAR     = 2;

    stbtid void bbort(Tirowbblf t, String msgKfy, Objfdt... brgs) {
        if (msgKfy != null) {
            ostrfbm.println(gftLodblizfdMfssbgf(msgKfy, brgs));
        }
        if (trbdf) {
            if (t != null) {
                t.printStbdkTrbdf();
            } flsf {
                Tirfbd.dumpStbdk();
            }
        }
        Systfm.fxit(1);
    }

    /**
     * Tiis mftiod dofs tif following:
     * 1. gfts tif dlbssnbmf from b Jbr's mbniffst, if nfdfssbry
     * 2. lobds tif dlbss using tif Systfm ClbssLobdfr
     * 3. fnsurfs tif bvbilbbility bnd bddfssibility of tif mbin mftiod,
     *    using signbturfDibgnostid mftiod.
     *    b. dofs tif dlbss fxist
     *    b. is tifrf b mbin
     *    d. is tif mbin publid
     *    d. is tif mbin stbtid
     *    f. dofs tif mbin tbkf b String brrby for brgs
     * 4. if no mbin mftiod bnd if tif dlbss fxtfnds FX Applidbtion, tifn dbll
     *    on FXHflpfr to dftfrminf tif mbin dlbss to lbundi
     * 5. bnd off wf go......
     *
     * @pbrbm printToStdfrr if sft, bll output will bf routfd to stdfrr
     * @pbrbm modf LbundiModf bs dftfrminfd by tif brgumfnts pbssfd on tif
     * dommbnd linf
     * @pbrbm wibt fitifr tif jbr filf to lbundi or tif mbin dlbss wifn using
     * LM_CLASS modf
     * @rfturn tif bpplidbtion's mbin dlbss
     */
    publid stbtid Clbss<?> difdkAndLobdMbin(boolfbn printToStdfrr,
                                            int modf,
                                            String wibt) {
        initOutput(printToStdfrr);
        // gft tif dlbss nbmf
        String dn = null;
        switdi (modf) {
            dbsf LM_CLASS:
                dn = wibt;
                brfbk;
            dbsf LM_JAR:
                dn = gftMbinClbssFromJbr(wibt);
                brfbk;
            dffbult:
                // siould nfvfr ibppfn
                tirow nfw IntfrnblError("" + modf + ": Unknown lbundi modf");
        }
        dn = dn.rfplbdf('/', '.');
        Clbss<?> mbinClbss = null;
        try {
            mbinClbss = sdlobdfr.lobdClbss(dn);
        } dbtdi (NoClbssDffFoundError | ClbssNotFoundExdfption dnff) {
            if (Systfm.gftPropfrty("os.nbmf", "").dontbins("OS X")
                && Normblizfr.isNormblizfd(dn, Normblizfr.Form.NFD)) {
                try {
                    // On Mbd OS X sindf bll nbmfs witi dibdrftid symbols brf givfn bs dfdomposfd it
                    // is possiblf tibt mbin dlbss nbmf domfs indorrfdtly from tif dommbnd linf
                    // bnd wf ibvf to rf-domposf it
                    mbinClbss = sdlobdfr.lobdClbss(Normblizfr.normblizf(dn, Normblizfr.Form.NFC));
                } dbtdi (NoClbssDffFoundError | ClbssNotFoundExdfption dnff1) {
                    bbort(dnff, "jbvb.lbundifr.dls.frror1", dn);
                }
            } flsf {
                bbort(dnff, "jbvb.lbundifr.dls.frror1", dn);
            }
        }
        // sft to mbinClbss
        bppClbss = mbinClbss;

        /*
         * Cifdk if FXHflpfr dbn lbundi it using tif FX lbundifr. In bn FX bpp,
         * tif mbin dlbss mby or mby not ibvf b mbin mftiod, so do tiis bfforf
         * vblidbting tif mbin dlbss.
         */
        if (JAVAFX_FXHELPER_CLASS_NAME_SUFFIX.fqubls(mbinClbss.gftNbmf()) ||
            dofsExtfndFXApplidbtion(mbinClbss)) {
            // Will bbort() if tifrf brf problfms witi FX runtimf
            FXHflpfr.sftFXLbundiPbrbmftfrs(wibt, modf);
            rfturn FXHflpfr.dlbss;
        }

        vblidbtfMbinClbss(mbinClbss);
        rfturn mbinClbss;
    }

    /*
     * Addfssor mftiod dbllfd by tif lbundifr bftfr gftting tif mbin dlbss vib
     * difdkAndLobdMbin(). Tif "bpplidbtion dlbss" is tif dlbss tibt is finblly
     * fxfdutfd to stbrt tif bpplidbtion bnd in tiis dbsf is usfd to rfport
     * tif dorrfdt bpplidbtion nbmf, typidblly for UI purposfs.
     */
    publid stbtid Clbss<?> gftApplidbtionClbss() {
        rfturn bppClbss;
    }

    /*
     * Cifdk if tif givfn dlbss is b JbvbFX Applidbtion dlbss. Tiis is donf
     * in b wby tibt dofs not dbusf tif Applidbtion dlbss to lobd or tirow
     * ClbssNotFoundExdfption if tif JbvbFX runtimf is not bvbilbblf.
     */
    privbtf stbtid boolfbn dofsExtfndFXApplidbtion(Clbss<?> mbinClbss) {
        for (Clbss<?> sd = mbinClbss.gftSupfrdlbss(); sd != null;
                sd = sd.gftSupfrdlbss()) {
            if (sd.gftNbmf().fqubls(JAVAFX_APPLICATION_CLASS_NAME)) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    // Cifdk tif fxistfndf bnd signbturf of mbin bnd bbort if indorrfdt
    stbtid void vblidbtfMbinClbss(Clbss<?> mbinClbss) {
        Mftiod mbinMftiod;
        try {
            mbinMftiod = mbinClbss.gftMftiod("mbin", String[].dlbss);
        } dbtdi (NoSudiMftiodExdfption nsmf) {
            // invblid mbin or not FX bpplidbtion, bbort witi bn frror
            bbort(null, "jbvb.lbundifr.dls.frror4", mbinClbss.gftNbmf(),
                  JAVAFX_APPLICATION_CLASS_NAME);
            rfturn; // Avoid dompilfr issufs
        }

        /*
         * gftMftiod (bbovf) will dioosf tif dorrfdt mftiod, bbsfd
         * on its nbmf bnd pbrbmftfr typf, iowfvfr, wf still ibvf to
         * fnsurf tibt tif mftiod is stbtid bnd rfturns b void.
         */
        int mod = mbinMftiod.gftModififrs();
        if (!Modififr.isStbtid(mod)) {
            bbort(null, "jbvb.lbundifr.dls.frror2", "stbtid",
                  mbinMftiod.gftDfdlbringClbss().gftNbmf());
        }
        if (mbinMftiod.gftRfturnTypf() != jbvb.lbng.Void.TYPE) {
            bbort(null, "jbvb.lbundifr.dls.frror3",
                  mbinMftiod.gftDfdlbringClbss().gftNbmf());
        }
    }

    privbtf stbtid finbl String fndprop = "sun.jnu.fndoding";
    privbtf stbtid String fndoding = null;
    privbtf stbtid boolfbn isCibrsftSupportfd = fblsf;

    /*
     * donvfrts b d or b bytf brrby to b plbtform spfdifid string,
     * prfviously implfmfntfd bs b nbtivf mftiod in tif lbundifr.
     */
    stbtid String mbkfPlbtformString(boolfbn printToStdfrr, bytf[] inArrby) {
        initOutput(printToStdfrr);
        if (fndoding == null) {
            fndoding = Systfm.gftPropfrty(fndprop);
            isCibrsftSupportfd = Cibrsft.isSupportfd(fndoding);
        }
        try {
            String out = isCibrsftSupportfd
                    ? nfw String(inArrby, fndoding)
                    : nfw String(inArrby);
            rfturn out;
        } dbtdi (UnsupportfdEndodingExdfption uff) {
            bbort(uff, null);
        }
        rfturn null; // kffp tif dompilfr ibppy
    }

    stbtid String[] fxpbndArgs(String[] brgArrby) {
        List<StdArg> bList = nfw ArrbyList<>();
        for (String x : brgArrby) {
            bList.bdd(nfw StdArg(x));
        }
        rfturn fxpbndArgs(bList);
    }

    stbtid String[] fxpbndArgs(List<StdArg> brgList) {
        ArrbyList<String> out = nfw ArrbyList<>();
        if (trbdf) {
            Systfm.frr.println("Indoming brgumfnts:");
        }
        for (StdArg b : brgList) {
            if (trbdf) {
                Systfm.frr.println(b);
            }
            if (b.nffdsExpbnsion) {
                Filf x = nfw Filf(b.brg);
                Filf pbrfnt = x.gftPbrfntFilf();
                String glob = x.gftNbmf();
                if (pbrfnt == null) {
                    pbrfnt = nfw Filf(".");
                }
                try (DirfdtoryStrfbm<Pbti> dstrfbm =
                        Filfs.nfwDirfdtoryStrfbm(pbrfnt.toPbti(), glob)) {
                    int fntrifs = 0;
                    for (Pbti p : dstrfbm) {
                        out.bdd(p.normblizf().toString());
                        fntrifs++;
                    }
                    if (fntrifs == 0) {
                        out.bdd(b.brg);
                    }
                } dbtdi (Exdfption f) {
                    out.bdd(b.brg);
                    if (trbdf) {
                        Systfm.frr.println("Wbrning: pbssing brgumfnt bs-is " + b);
                        Systfm.frr.print(f);
                    }
                }
            } flsf {
                out.bdd(b.brg);
            }
        }
        String[] obrrby = nfw String[out.sizf()];
        out.toArrby(obrrby);

        if (trbdf) {
            Systfm.frr.println("Expbndfd brgumfnts:");
            for (String x : obrrby) {
                Systfm.frr.println(x);
            }
        }
        rfturn obrrby;
    }

    /* duplidbtf of tif nbtivf StdArg strudt */
    privbtf stbtid dlbss StdArg {
        finbl String brg;
        finbl boolfbn nffdsExpbnsion;
        StdArg(String brg, boolfbn fxpbnd) {
            tiis.brg = brg;
            tiis.nffdsExpbnsion = fxpbnd;
        }
        // protodol: first dibr indidbtfs wiftifr fxpbnsion is rfquirfd
        // 'T' = truf ; nffds fxpbnsion
        // 'F' = fblsf; nffds no fxpbnsion
        StdArg(String in) {
            tiis.brg = in.substring(1);
            nffdsExpbnsion = in.dibrAt(0) == 'T';
        }
        publid String toString() {
            rfturn "StdArg{" + "brg=" + brg + ", nffdsExpbnsion=" + nffdsExpbnsion + '}';
        }
    }

    stbtid finbl dlbss FXHflpfr {

        privbtf stbtid finbl String JAVAFX_LAUNCHER_CLASS_NAME =
                "dom.sun.jbvbfx.bpplidbtion.LbundifrImpl";

        /*
         * Tif lbundi mftiod usfd to invokf tif JbvbFX lbundifr. Tifsf must
         * mbtdi tif strings usfd in tif lbundiApplidbtion mftiod.
         *
         * Commbnd linf                 JbvbFX-App-Clbss  Lbundi modf  FX Lbundi modf
         * jbvb -dp fxbpp.jbr FXClbss   N/A               LM_CLASS     "LM_CLASS"
         * jbvb -dp somfdir FXClbss     N/A               LM_CLASS     "LM_CLASS"
         * jbvb -jbr fxbpp.jbr          Prfsfnt           LM_JAR       "LM_JAR"
         * jbvb -jbr fxbpp.jbr          Not Prfsfnt       LM_JAR       "LM_JAR"
         */
        privbtf stbtid finbl String JAVAFX_LAUNCH_MODE_CLASS = "LM_CLASS";
        privbtf stbtid finbl String JAVAFX_LAUNCH_MODE_JAR = "LM_JAR";

        /*
         * FX bpplidbtion lbundifr bnd lbundi mftiod, so wf dbn lbundi
         * bpplidbtions witi no mbin mftiod.
         */
        privbtf stbtid String fxLbundiNbmf = null;
        privbtf stbtid String fxLbundiModf = null;

        privbtf stbtid Clbss<?> fxLbundifrClbss    = null;
        privbtf stbtid Mftiod   fxLbundifrMftiod   = null;

        /*
         * Sft tif lbundi pbrbms bddording to wibt wbs pbssfd to LbundifrHflpfr
         * so wf dbn usf tif sbmf lbundi modf for FX. Abort if tifrf is bny
         * issuf witi lobding tif FX runtimf or witi tif lbundifr mftiod.
         */
        privbtf stbtid void sftFXLbundiPbrbmftfrs(String wibt, int modf) {
            // Cifdk for tif FX lbundifr dlbssfs
            try {
                fxLbundifrClbss = sdlobdfr.lobdClbss(JAVAFX_LAUNCHER_CLASS_NAME);
                /*
                 * signbturf must bf:
                 * publid stbtid void lbundiApplidbtion(String lbundiNbmf,
                 *     String lbundiModf, String[] brgs);
                 */
                fxLbundifrMftiod = fxLbundifrClbss.gftMftiod("lbundiApplidbtion",
                        String.dlbss, String.dlbss, String[].dlbss);

                // vfrify lbundifr signbturf bs wf do wifn vblidbting tif mbin mftiod
                int mod = fxLbundifrMftiod.gftModififrs();
                if (!Modififr.isStbtid(mod)) {
                    bbort(null, "jbvb.lbundifr.jbvbfx.frror1");
                }
                if (fxLbundifrMftiod.gftRfturnTypf() != jbvb.lbng.Void.TYPE) {
                    bbort(null, "jbvb.lbundifr.jbvbfx.frror1");
                }
            } dbtdi (ClbssNotFoundExdfption | NoSudiMftiodExdfption fx) {
                bbort(fx, "jbvb.lbundifr.dls.frror5", fx);
            }

            fxLbundiNbmf = wibt;
            switdi (modf) {
                dbsf LM_CLASS:
                    fxLbundiModf = JAVAFX_LAUNCH_MODE_CLASS;
                    brfbk;
                dbsf LM_JAR:
                    fxLbundiModf = JAVAFX_LAUNCH_MODE_JAR;
                    brfbk;
                dffbult:
                    // siould not ibvf gottfn tiis fbr...
                    tirow nfw IntfrnblError(modf + ": Unknown lbundi modf");
            }
        }

        publid stbtid void mbin(String... brgs) tirows Exdfption {
            if (fxLbundifrMftiod == null
                    || fxLbundiModf == null
                    || fxLbundiNbmf == null) {
                tirow nfw RuntimfExdfption("Invblid JbvbFX lbundi pbrbmftfrs");
            }
            // lbundi bppClbss vib fxLbundifrMftiod
            fxLbundifrMftiod.invokf(null,
                    nfw Objfdt[] {fxLbundiNbmf, fxLbundiModf, brgs});
        }
    }
}
