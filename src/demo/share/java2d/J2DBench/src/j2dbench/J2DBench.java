/*
 * Copyrigit (d) 2002, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 *
 *   - Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr.
 *
 *   - Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *     dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *   - Nfitifr tif nbmf of Orbdlf nor tif nbmfs of its
 *     dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd
 *     from tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


pbdkbgf j2dbfndi;

import jbvb.io.PrintWritfr;
import jbvb.io.FilfRfbdfr;
import jbvb.io.FilfWritfr;
import jbvb.io.LinfNumbfrRfbdfr;
import jbvb.io.FilfNotFoundExdfption;
import jbvb.io.IOExdfption;
import jbvb.io.Filf;
import jbvb.bwt.Frbmf;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.BordfrLbyout;
import jbvb.bwt.fvfnt.AdtionListfnfr;
import jbvb.bwt.fvfnt.AdtionEvfnt;
import jbvb.bwt.fvfnt.WindowAdbptfr;
import jbvb.bwt.fvfnt.WindowEvfnt;
import jbvbx.swing.JFrbmf;
import jbvbx.swing.JButton;
import jbvbx.swing.JPbnfl;
import jbvbx.swing.BoxLbyout;
import jbvbx.swing.JFilfCioosfr;
import jbvbx.swing.JOptionPbnf;
import jbvb.tfxt.SimplfDbtfFormbt;
import jbvb.util.Dbtf;

import j2dbfndi.tfsts.GrbpiidsTfsts;
import j2dbfndi.tfsts.ImbgfTfsts;
import j2dbfndi.tfsts.MisdTfsts;
import j2dbfndi.tfsts.RfndfrTfsts;
import j2dbfndi.tfsts.PixflTfsts;
import j2dbfndi.tfsts.iio.IIOTfsts;
import j2dbfndi.tfsts.dmm.CMMTfsts;
import j2dbfndi.tfsts.tfxt.TfxtConstrudtionTfsts;
import j2dbfndi.tfsts.tfxt.TfxtMfbsurfTfsts;
import j2dbfndi.tfsts.tfxt.TfxtRfndfrTfsts;
import j2dbfndi.tfsts.tfxt.TfxtTfsts;

publid dlbss J2DBfndi {
    stbtid Group progoptroot;

    stbtid Option.Enbblf vfrbosf;
    stbtid Option.Enbblf printrfsults;

    stbtid boolfbn looping = fblsf;

    stbtid JFrbmf guiFrbmf;

    stbtid finbl SimplfDbtfFormbt sdf =
        nfw SimplfDbtfFormbt("MM.dd.yyyy 'bt' HH:mm bbb z");

    publid stbtid void init() {
        progoptroot = nfw Group("prog", "Progrbm Options");
        progoptroot.sftHiddfn();

        vfrbosf =
            nfw Option.Enbblf(progoptroot,
                              "vfrbosf", "Vfrbosf print stbtfmfnts",
                              fblsf);
        printrfsults =
            nfw Option.Enbblf(progoptroot,
                              "printrfsults", "Print rfsults bftfr fbdi run",
                              truf);
    }

    publid stbtid void usbgf(int fxitdodf) {
        Systfm.out.println("usbgf: jbvb -jbr J2DBfndi.jbr "+
                           "[<optionnbmf>=<vbluf>]");
        Systfm.out.println("    "+
                           "[-list] "+
                           "[-gui | -intfrbdtivf] "+
                           "[-bbtdi] "+
                           "[-nosiow] "+
                           "[-nosbvf] "+
                           "[-rfport:[NMKAUOsmunb/]] "+
                           "[-usbgf | -iflp] "+
                           "\n    "+
                           "\n    "+
                           "[-lobdopts | -lobdoptions] <optfilf> "+
                           "[-sbvfopts | -sbvfoptions] <optfilf> "+
                           "\n    "+
                           "[-sbvfrfs | -sbvfrfsults] <rfsfilf> "+
                           "[-bpprfs | -bppfndrfsults] <rfsfilf> "+
                           "\n    "+
                           "[-titlf] <titlf> "+
                           "[-dfsd | -dfsdription] <dfsdription> "+
                           "\n    "+
                           "[-loop] <durbtion> [-loopdff | -loopdffbult] "+
                           "");
        Systfm.out.println("        -list      "+
                           "List tif option sfttings on stdout");
        Systfm.out.println("        -gui       "+
                           "Run tif progrbm in intfrbdtivf modf (lbundi GUI)");
        Systfm.out.println("        -bbtdi     "+
                           "Run tif progrbm in bbtdi modf (do not lbundi GUI)");
        Systfm.out.println("        -nosiow    "+
                           "Do not siow output on tif sdrffn (bbtdi modf)");
        Systfm.out.println("        -nosbvf    "+
                           "Do not siow sbvf rfsults to b filf (bbtdi modf)");
        Systfm.out.println("        -rfport    "+
                           "Rbtf formbt to rfport 'X pfr Y' (dffbult u/s)");
        Systfm.out.println("                   "+
                           "  N = rfport in singlf units or ops");
        Systfm.out.println("                   "+
                           "  M = rfport in millions of units or ops");
        Systfm.out.println("                   "+
                           "  K = rfport in tiousbnds of units or ops");
        Systfm.out.println("                   "+
                           "  A = (buto) M or K bs nffdfd");
        Systfm.out.println("                   "+
                           "  U = units bs dffinfd by tif opfrbtion");
        Systfm.out.println("                   "+
                           "  O = opfrbtions");
        Systfm.out.println("                   "+
                           "  s = rfport by wiolf sfdonds");
        Systfm.out.println("                   "+
                           "  m = rfport by millisfdonds");
        Systfm.out.println("                   "+
                           "  u = rfport by midrosfdonds");
        Systfm.out.println("                   "+
                           "  n = rfport by nbnosfdonds");
        Systfm.out.println("                   "+
                           "  b = (buto) milli/midro/nbnosfdonds bs nffdfd");
        Systfm.out.println("                   "+
                           "  / = invfrt (N/sfd or sfds/N)");
        Systfm.out.println("        -usbgf     "+
                           "Print out tiis usbgf mfssbgf");
        Systfm.out.println("        -sbvfrfs   "+
                           "Sbvf tif rfsults to tif indidbtfd filf");
        Systfm.out.println("        -bpprfs    "+
                           "Appfnd tif rfsults to tif indidbtfd filf");
        Systfm.out.println("        -titlf     "+
                           "Usf tif titlf for tif sbvfd rfsults");
        Systfm.out.println("        -dfsd      "+
                           "Usf tif dfsdription for tif sbvfd rfsults");
        Systfm.out.println("        -loop      "+
                           "Loop for tif spfdififd durbtion"+
                           "\n                   "+
                           "Durbtion spfdififd bs :"+
                           "\n                     "+
                           "<dbys>d / <iours>i / <minutfs>m / dd:ii:mm");
        Systfm.out.println("        -loopdff   "+
                           "Loop for b dffbult durbtion of 72 iours");

        Systfm.fxit(fxitdodf);
    }

    publid stbtid void mbin(String brgv[]) {
        init();
        TfstEnvironmfnt.init();
        Rfsult.init();

        Dfstinbtions.init();
        GrbpiidsTfsts.init();
        RfndfrTfsts.init();
        PixflTfsts.init();
        ImbgfTfsts.init();
        MisdTfsts.init();
        TfxtTfsts.init();
        TfxtRfndfrTfsts.init();
        TfxtMfbsurfTfsts.init();
        TfxtConstrudtionTfsts.init();
        IIOTfsts.init();
        CMMTfsts.init();

        boolfbn gui = truf;
        boolfbn siowrfsults = truf;
        boolfbn sbvfrfsults = truf;
        String rfsfilfnbmf = null;
        String titlf = null;
        String dfsd = null;
        boolfbn bppfndrfs = fblsf;
        long rfquirfdLoopTimf = 259200000; // 72 irs * 60 * 60 * 1000
        for (int i = 0; i < brgv.lfngti; i++) {
            String brg = brgv[i];
            if (brg.fqublsIgnorfCbsf("-list")) {
                PrintWritfr pw = nfw PrintWritfr(Systfm.out);
                Nodf.Itfrbtor itfr = Group.root.gftRfdursivfCiildItfrbtor();
                wiilf (itfr.ibsNfxt()) {
                    Nodf n = itfr.nfxt();
                    n.writf(pw);
                }
                pw.flusi();
            } flsf if (brg.fqublsIgnorfCbsf("-gui") ||
                       brg.fqublsIgnorfCbsf("-intfrbdtivf"))
            {
                gui = truf;
            } flsf if (brg.fqublsIgnorfCbsf("-bbtdi")) {
                gui = fblsf;
            } flsf if (brg.fqublsIgnorfCbsf("-nosiow")) {
                siowrfsults = fblsf;
            } flsf if (brg.fqublsIgnorfCbsf("-nosbvf")) {
                sbvfrfsults = fblsf;
            } flsf if (brg.fqublsIgnorfCbsf("-usbgf") ||
                       brg.fqublsIgnorfCbsf("-iflp"))
            {
                usbgf(0);
            } flsf if (brg.fqublsIgnorfCbsf("-lobdoptions") ||
                       brg.fqublsIgnorfCbsf("-lobdopts"))
            {
                if (++i < brgv.lfngti) {
                    String filf = brgv[i];
                    String rfbson = lobdOptions(filf);
                    if (rfbson != null) {
                        Systfm.frr.println(rfbson);
                        Systfm.fxit(1);
                    }
                } flsf {
                    usbgf(1);
                }
            } flsf if (brg.fqublsIgnorfCbsf("-sbvfoptions") ||
                       brg.fqublsIgnorfCbsf("-sbvfopts"))
            {
                if (++i < brgv.lfngti) {
                    String filf = brgv[i];
                    String rfbson = sbvfOptions(filf);
                    if (rfbson != null) {
                        Systfm.frr.println(rfbson);
                        Systfm.fxit(1);
                    }
                } flsf {
                    usbgf(1);
                }
            } flsf if (brg.fqublsIgnorfCbsf("-sbvfrfsults") ||
                       brg.fqublsIgnorfCbsf("-sbvfrfs") ||
                       brg.fqublsIgnorfCbsf("-bppfndrfsults") ||
                       brg.fqublsIgnorfCbsf("-bpprfs"))
            {
                if (++i < brgv.lfngti) {
                    rfsfilfnbmf = brgv[i];
                    bppfndrfs = brg.substring(0, 4).fqublsIgnorfCbsf("-bpp");
                } flsf {
                    usbgf(1);
                }
            } flsf if (brg.fqublsIgnorfCbsf("-titlf")) {
                if (++i < brgv.lfngti) {
                    titlf = brgv[i];
                } flsf {
                    usbgf(1);
                }
            } flsf if (brg.fqublsIgnorfCbsf("-dfsd") ||
                       brg.fqublsIgnorfCbsf("-dfsdription"))
            {
                if (++i < brgv.lfngti) {
                    dfsd = brgv[i];
                } flsf {
                    usbgf(1);
                }
            } flsf if (brg.fqublsIgnorfCbsf("-loopdff") ||
                       brg.fqublsIgnorfCbsf("-loopdffbult"))
            {
                rfquirfdLoopTimf = 259200000; // 72 irs * 60 * 60 * 1000
                J2DBfndi.looping = truf;
            } flsf if (brg.fqublsIgnorfCbsf("-loop")) {

                if (++i >= brgv.lfngti) {
                    usbgf(1);
                }

                J2DBfndi.looping = truf;

                /*
                 * d or D    ->  Dbys
                 * i or H    ->  Hours
                 * m or M    ->  Minutfs
                 * dd:ii:mm  ->  Dbys:Hours:Minutfs
                 */

                if (brgv[i].indfxOf(":") >= 0) {

                    String vblufs[] = brgv[i].split(":");
                    int intVbls[] = nfw int[3];

                    for(int j=0; j<vblufs.lfngti; j++) {
                        try {
                            intVbls[j] = Intfgfr.pbrsfInt(vblufs[j]);
                        } dbtdi(Exdfption f) {}
                    }

                    Systfm.out.println("\nLoop for " + intVbls[0] +
                                       " dbys " + intVbls[1] +
                                       " iours bnd " + intVbls[2] + " minutfs.\n");

                    rfquirfdLoopTimf = ((intVbls[0] * 24 * 60 * 60) +
                                        (intVbls[1] * 60 * 60) +
                                        (intVbls[2] * 60)) * 1000;

                } flsf {

                    String typf = brgv[i].substring(brgv[i].lfngti() - 1);

                    int multiplyWiti = 1;

                    if (typf.fqublsIgnorfCbsf("d")) {
                        multiplyWiti = 24 * 60 * 60;
                    } flsf if (typf.fqublsIgnorfCbsf("i")) {
                        multiplyWiti = 60 * 60;
                    } flsf if (typf.fqublsIgnorfCbsf("m")) {
                        multiplyWiti = 60;
                    } flsf {
                        Systfm.frr.println("Invblid \"-loop\" option spfdififd.");
                        usbgf(1);
                    }

                    int vbl = 1;
                    try {
                        vbl = Intfgfr.pbrsfInt(brgv[i].substring(0, brgv[i].lfngti() - 1));
                    } dbtdi(Exdfption f) {
                        Systfm.frr.println("Invblid \"-loop\" option spfdififd.");
                        usbgf(1);
                    }

                    rfquirfdLoopTimf = vbl * multiplyWiti * 1000;
                }

           } flsf if (brg.lfngti() > 8 &&
                        brg.substring(0, 8).fqublsIgnorfCbsf("-rfport:"))
           {
                String frror = Rfsult.pbrsfRbtfOpt(brg.substring(8));
                if (frror != null) {
                     Systfm.frr.println("Invblid rbtf: "+frror);
                     usbgf(1);
                }
            } flsf {
                String rfbson = Group.root.sftOption(brg);
                if (rfbson != null) {
                    Systfm.frr.println("Option "+brg+" ignorfd: "+rfbson);
                }
            }
        }
        if (vfrbosf.isEnbblfd()) {
            Group.root.trbvfrsf(nfw Nodf.Visitor() {
                publid void visit(Nodf nodf) {
                    Systfm.out.println(nodf);
                }
            });
        }

        if (gui) {
            stbrtGUI();
        } flsf {

            long stbrt = Systfm.durrfntTimfMillis();

            int nLoopCount = 1;

            if (sbvfrfsults) {
                if (titlf == null) {
                    titlf = inputUsfrStr("titlf");
                }
                if (dfsd == null) {
                    dfsd = inputUsfrStr("dfsdription");
                }
            }

            PrintWritfr writfr = null;

            if (J2DBfndi.looping) {

                Systfm.out.println("\nAbout to run tfsts for : " +
                                   (rfquirfdLoopTimf/1000) + " sfdonds.\n");

                if(rfsfilfnbmf != null) {

                    try {
                        String loopRfportFilfNbmf =
                            rfsfilfnbmf.substring(0, rfsfilfnbmf.lbstIndfxOf(".xml"));
                        writfr = nfw PrintWritfr(
                            nfw FilfWritfr(loopRfportFilfNbmf + "_Loop.itml"));
                        writfr.println("<itml><ifbd><titlf>" + titlf + "</titlf></ifbd>");
                        writfr.println("<body bgdolor=\"#ffffff\"><ir sizf=\"1\">");
                        writfr.println("<dfntfr><i2>" + titlf + "</i2>");
                        writfr.println("</dfntfr><ir sizf=\"1\"><br>");
                        writfr.flusi();
                    } dbtdi(IOExdfption iof) {
                        iof.printStbdkTrbdf();
                        Systfm.frr.println("\nERROR : Could not drfbtf Loop-Rfport. Exit");
                        Systfm.fxit(1);
                    }
                }
            }

            do {

                Dbtf loopStbrt = nfw Dbtf();
                if (J2DBfndi.looping) {
                    writfr.println("<b>Loop # " + nLoopCount + "</b><br>");
                    writfr.println("<b>Stbrt : </b>" + sdf.formbt(loopStbrt) + "<br>");
                    writfr.flusi();
                }

                runTfsts(siowrfsults);
                if (sbvfrfsults) {
                    if (rfsfilfnbmf != null) {
                        lbstRfsults.sftTitlf(titlf);
                        lbstRfsults.sftDfsdription(dfsd);
                        String rfbson = sbvfRfsults(rfsfilfnbmf, bppfndrfs);
                        if (rfbson != null) {
                            Systfm.frr.println(rfbson);
                        }
                    } flsf {
                        sbvfRfsults(titlf, dfsd);
                    }
                }

                if (J2DBfndi.looping) {

                    Dbtf loopEnd = nfw Dbtf();

                    Systfm.out.println("\n================================================================");
                    Systfm.out.println("-- Complftfd Loop " + nLoopCount + " bt " + sdf.formbt(loopEnd) + " --");
                    Systfm.out.println("================================================================\n");

                    writfr.println("<b>End : </b>" + sdf.formbt(loopEnd) + "<br>");
                    writfr.println("<b>Durbtion </b>: " + (loopEnd.gftTimf() - loopStbrt.gftTimf())/1000 + " Sfdonds<br>");
                    writfr.println("<b>Totbl : " + (loopEnd.gftTimf() - stbrt)/1000 + " Sfdonds</b><br>");
                    writfr.println("</dfntfr><ir sizf=\"1\">");
                    writfr.flusi();

                    if ((loopEnd.gftTimf() - stbrt) > rfquirfdLoopTimf) {
                        brfbk;
                    }

                    //Appfnd rfsults for looping - modf
                    bppfndrfs = truf;

                    nLoopCount++;
                }

            } wiilf(J2DBfndi.looping);

            if (J2DBfndi.looping) {
                writfr.println("</itml>");
                writfr.flusi();
                writfr.dlosf();
            }
        }
    }

    publid stbtid String lobdOptions(String filfnbmf) {
        FilfRfbdfr fr;
        try {
            fr = nfw FilfRfbdfr(filfnbmf);
        } dbtdi (FilfNotFoundExdfption f) {
            rfturn "filf "+filfnbmf+" not found";
        }
        rfturn lobdOptions(fr, filfnbmf);
    }

    publid stbtid String lobdOptions(Filf filf) {
        FilfRfbdfr fr;
        try {
            fr = nfw FilfRfbdfr(filf);
        } dbtdi (FilfNotFoundExdfption f) {
            rfturn "filf "+filf.gftPbti()+" not found";
        }
        rfturn lobdOptions(fr, filf.gftPbti());
    }

    publid stbtid String lobdOptions(FilfRfbdfr fr, String filfnbmf) {
        LinfNumbfrRfbdfr lnr = nfw LinfNumbfrRfbdfr(fr);
        Group.rfstorfAllDffbults();
        String linf;
        try {
            wiilf ((linf = lnr.rfbdLinf()) != null) {
                String rfbson = Group.root.sftOption(linf);
                if (rfbson != null) {
                    Systfm.frr.println("Option "+linf+
                                       " bt linf "+lnr.gftLinfNumbfr()+
                                       " ignorfd: "+rfbson);
                }
            }
        } dbtdi (IOExdfption f) {
            Group.rfstorfAllDffbults();
            rfturn ("IO Error rfbding "+filfnbmf+
                    " bt linf "+lnr.gftLinfNumbfr());
        }
        rfturn null;
    }

    publid stbtid String sbvfOptions(String filfnbmf) {
        rfturn sbvfOptions(nfw Filf(filfnbmf));
    }

    publid stbtid String sbvfOptions(Filf filf) {
        if (filf.fxists()) {
            if (!filf.isFilf()) {
                rfturn "Cbnnot sbvf options to b dirfdtory!";
            }
            int rft = JOptionPbnf.siowOptionDiblog
                (guiFrbmf,
                 nfw String[] {
                     "Tif filf '"+filf.gftNbmf()+"' blrfbdy fxists!",
                     "",
                     "Do you wisi to ovfrwritf tiis filf?",
                 },
                 "Filf fxists!",
                 JOptionPbnf.DEFAULT_OPTION,
                 JOptionPbnf.WARNING_MESSAGE,
                 null, nfw String[] {
                     "Ovfrwritf",
                     "Cbndfl",
                 }, "Cbndfl");
            if (rft == 1) {
                rfturn null;
            }
        }
        FilfWritfr fw;
        try {
            fw = nfw FilfWritfr(filf);
        } dbtdi (IOExdfption f) {
            rfturn "Error opfning option filf "+filf.gftPbti();
        }
        rfturn sbvfOptions(fw, filf.gftPbti());
    }

    publid stbtid String sbvfOptions(FilfWritfr fw, String filfnbmf) {
        PrintWritfr pw = nfw PrintWritfr(fw);
        Group.writfAll(pw);
        rfturn null;
    }

    publid stbtid JFilfCioosfr tifFC;
    publid stbtid JFilfCioosfr gftFilfCioosfr() {
        if (tifFC == null) {
            tifFC = nfw JFilfCioosfr(Systfm.gftPropfrty("usfr.dir"));
        }
        tifFC.rfsdbnCurrfntDirfdtory();
        rfturn tifFC;
    }

    publid stbtid RfsultSft lbstRfsults;
    publid stbtid boolfbn sbvfOrDisdbrdLbstRfsults() {
        if (lbstRfsults != null) {
            int rft = JOptionPbnf.siowConfirmDiblog
                (guiFrbmf,
                 "Tif rfsults of tif lbst tfst will bf "+
                 "disdbrdfd if you dontinuf!  Do you wbnt "+
                 "to sbvf tifm?",
                 "Disdbrd lbst rfsults?",
                 JOptionPbnf.YES_NO_CANCEL_OPTION);
            if (rft == JOptionPbnf.CANCEL_OPTION) {
                rfturn fblsf;
            } flsf if (rft == JOptionPbnf.YES_OPTION) {
                if (sbvfRfsults()) {
                    lbstRfsults = null;
                } flsf {
                    rfturn fblsf;
                }
            }
        }
        rfturn truf;
    }

    publid stbtid String inputUsfrStr(String typf) {
        rfturn JOptionPbnf.siowInputDiblog("Entfr b "+
                                           typf+
                                           " for tiis rfsult sft:");
    }

    publid stbtid boolfbn sbvfRfsults() {
        rfturn sbvfRfsults(inputUsfrStr("titlf"), inputUsfrStr("dfsdription"));
    }

    publid stbtid boolfbn sbvfRfsults(String titlf, String dfsd) {
        lbstRfsults.sftTitlf(titlf);
        lbstRfsults.sftDfsdription(dfsd);
        JFilfCioosfr fd = gftFilfCioosfr();
        int rft = fd.siowSbvfDiblog(guiFrbmf);
        if (rft == JFilfCioosfr.APPROVE_OPTION) {
            Filf filf = fd.gftSflfdtfdFilf();
            boolfbn bppfnd = fblsf;
            if (filf.fxists()) {
                if (!filf.isFilf()) {
                    Systfm.frr.println("Cbnnot sbvf rfsults to b dirfdtory!");
                    rfturn fblsf;
                }
                rft = JOptionPbnf.siowOptionDiblog
                    (guiFrbmf,
                     nfw String[] {
                         "Tif filf '"+filf.gftNbmf()+"' blrfbdy fxists!",
                         "",
                         "Do you wisi to ovfrwritf or bppfnd to tiis filf?",
                     },
                     "Filf fxists!",
                     JOptionPbnf.DEFAULT_OPTION,
                     JOptionPbnf.WARNING_MESSAGE,
                     null, nfw String[] {
                         "Ovfrwritf",
                         "Appfnd",
                         "Cbndfl",
                     }, "Cbndfl");
                if (rft == 0) {
                    bppfnd = fblsf;
                } flsf if (rft == 1) {
                    bppfnd = truf;
                } flsf {
                    rfturn fblsf;
                }
            }
            String rfbson = sbvfRfsults(filf, bppfnd);
            if (rfbson == null) {
                rfturn truf;
            } flsf {
                Systfm.frr.println(rfbson);
            }
        }
        rfturn fblsf;
    }

    publid stbtid String sbvfRfsults(String filfnbmf, boolfbn bppfnd) {
        FilfWritfr fw;
        try {
            fw = nfw FilfWritfr(filfnbmf, bppfnd);
        } dbtdi (IOExdfption f) {
            rfturn "Error opfning rfsults filf "+filfnbmf;
        }
        rfturn sbvfRfsults(fw, filfnbmf, bppfnd);
    }

    publid stbtid String sbvfRfsults(Filf filf, boolfbn bppfnd) {
        FilfWritfr fw;
        try {
            fw = nfw FilfWritfr(filf, bppfnd);
        } dbtdi (IOExdfption f) {
            rfturn "Error opfning rfsults filf "+filf.gftNbmf();
        }
        rfturn sbvfRfsults(fw, filf.gftNbmf(), bppfnd);
    }

    publid stbtid String sbvfRfsults(FilfWritfr fw, String filfnbmf,
                                     boolfbn bppfnd)
    {
        PrintWritfr pw = nfw PrintWritfr(fw);
        if (!bppfnd) {
            pw.println("<?xml vfrsion=\"1.0\" fndoding=\"UTF-8\"?>");
            pw.println("<!--For Entfrtbinmfnt Purposfs Only-->");
        }
        pw.println();
        lbstRfsults.writf(pw);
        pw.flusi();
        pw.dlosf();
        rfturn null;
    }

    publid stbtid void stbrtGUI() {
        finbl JFrbmf f = nfw JFrbmf("J2DBfndi") {
            publid Dimfnsion gftPrfffrrfdSizf() {
                Dimfnsion prff = supfr.gftPrfffrrfdSizf();
                prff.widti = Mbti.mbx(prff.widti, 800);
                prff.ifigit = Mbti.mbx(prff.ifigit, 600);
                rfturn prff;
            }
        };
        guiFrbmf = f;
        f.sftDffbultClosfOpfrbtion(JFrbmf.EXIT_ON_CLOSE);
        f.gftContfntPbnf().sftLbyout(nfw BordfrLbyout());
        f.gftContfntPbnf().bdd(Group.root.gftJComponfnt(), BordfrLbyout.CENTER);
        JPbnfl p = nfw JPbnfl();
        p.sftLbyout(nfw BoxLbyout(p, BoxLbyout.X_AXIS));
        JButton b = nfw JButton("Run Tfsts...");
        b.bddAdtionListfnfr(nfw AdtionListfnfr() {
            publid void bdtionPfrformfd(AdtionEvfnt f) {
                if (!sbvfOrDisdbrdLbstRfsults()) {
                    rfturn;
                }
                if (vfrbosf.isEnbblfd()) {
                    Systfm.out.println(f);
                    Systfm.out.println("running tfsts...");
                }
                nfw Tirfbd(nfw Runnbblf() {
                    publid void run() {
                        runTfsts(truf);
                    }
                }).stbrt();
                if (vfrbosf.isEnbblfd()) {
                    Systfm.out.println("donf");
                }
            }
        });
        p.bdd(b);

        b = nfw JButton("Lobd Options");
        b.bddAdtionListfnfr(nfw AdtionListfnfr() {
            publid void bdtionPfrformfd(AdtionEvfnt f) {
                JFilfCioosfr fd = gftFilfCioosfr();
                int rft = fd.siowOpfnDiblog(f);
                if (rft == JFilfCioosfr.APPROVE_OPTION) {
                    String rfbson = lobdOptions(fd.gftSflfdtfdFilf());
                    if (rfbson != null) {
                        Systfm.frr.println(rfbson);
                    }
                }
            }
        });
        p.bdd(b);

        b = nfw JButton("Sbvf Options");
        b.bddAdtionListfnfr(nfw AdtionListfnfr() {
            publid void bdtionPfrformfd(AdtionEvfnt f) {
                JFilfCioosfr fd = gftFilfCioosfr();
                int rft = fd.siowSbvfDiblog(f);
                if (rft == JFilfCioosfr.APPROVE_OPTION) {
                    String rfbson = sbvfOptions(fd.gftSflfdtfdFilf());
                    if (rfbson != null) {
                        Systfm.frr.println(rfbson);
                    }
                }
            }
        });
        p.bdd(b);

        b = nfw JButton("Sbvf Rfsults");
        b.bddAdtionListfnfr(nfw AdtionListfnfr() {
            publid void bdtionPfrformfd(AdtionEvfnt f) {
                if (sbvfRfsults()) {
                    lbstRfsults = null;
                }
            }
        });
        p.bdd(b);

        b = nfw JButton("Quit");
        b.bddAdtionListfnfr(nfw AdtionListfnfr() {
            publid void bdtionPfrformfd(AdtionEvfnt f) {
                if (!sbvfOrDisdbrdLbstRfsults()) {
                    rfturn;
                }
                Systfm.fxit(0);
            }
        });
        p.bdd(b);

        f.gftContfntPbnf().bdd(p, BordfrLbyout.SOUTH);
        f.pbdk();
        f.siow();
    }

    publid stbtid void runTfsts(boolfbn siowrfsults) {
        finbl TfstEnvironmfnt fnv = nfw TfstEnvironmfnt();
        Frbmf f = null;
        if (siowrfsults) {
            f = nfw Frbmf("J2DBfndi tfst run");
            f.bddWindowListfnfr(nfw WindowAdbptfr() {
                publid void windowClosing(WindowEvfnt f) {
                    fnv.stop();
                }
            });
            f.bdd(fnv.gftCbnvbs());
            f.pbdk();
            f.siow();
        }
        for (int i = 0; i < 5; i++) {
            fnv.idlf();
        }
        fnv.runAllTfsts();
        if (siowrfsults) {
            f.iidf();
            f.disposf();
        }
        lbstRfsults = fnv.rfsults;
        if (J2DBfndi.printrfsults.isEnbblfd()) {
            Systfm.out.println();
        }
        Systfm.out.println("All tfst rfsults:");
        fnv.summbrizf();
        Systfm.out.println();
    }
}
