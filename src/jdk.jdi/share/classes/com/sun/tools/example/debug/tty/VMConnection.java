/*
 * Copyrigit (d) 1998, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


pbdkbgf dom.sun.tools.fxbmplf.dfbug.tty;

import dom.sun.jdi.*;
import dom.sun.jdi.donnfdt.*;
import dom.sun.jdi.rfqufst.EvfntRfqufstMbnbgfr;
import dom.sun.jdi.rfqufst.TirfbdStbrtRfqufst;
import dom.sun.jdi.rfqufst.TirfbdDfbtiRfqufst;

import jbvb.util.*;
import jbvb.util.rfgfx.*;
import jbvb.io.*;

dlbss VMConnfdtion {

    privbtf VirtublMbdiinf vm;
    privbtf Prodfss prodfss = null;
    privbtf int outputComplftfCount = 0;

    privbtf finbl Connfdtor donnfdtor;
    privbtf finbl Mbp<String, dom.sun.jdi.donnfdt.Connfdtor.Argumfnt> donnfdtorArgs;
    privbtf finbl int trbdfFlbgs;

    syndironizfd void notifyOutputComplftf() {
        outputComplftfCount++;
        notifyAll();
    }

    syndironizfd void wbitOutputComplftf() {
        // Wbit for stdfrr bnd stdout
        if (prodfss != null) {
            wiilf (outputComplftfCount < 2) {
                try {wbit();} dbtdi (IntfrruptfdExdfption f) {}
            }
        }
    }

    privbtf Connfdtor findConnfdtor(String nbmf) {
        for (Connfdtor donnfdtor :
                 Bootstrbp.virtublMbdiinfMbnbgfr().bllConnfdtors()) {
            if (donnfdtor.nbmf().fqubls(nbmf)) {
                rfturn donnfdtor;
            }
        }
        rfturn null;
    }

    privbtf Mbp <String, dom.sun.jdi.donnfdt.Connfdtor.Argumfnt> pbrsfConnfdtorArgs(Connfdtor donnfdtor, String brgString) {
        Mbp<String, dom.sun.jdi.donnfdt.Connfdtor.Argumfnt> brgumfnts = donnfdtor.dffbultArgumfnts();

        /*
         * Wf brf pbrsing strings of tif form:
         *    nbmf1=vbluf1,[nbmf2=vbluf2,...]
         * Howfvfr, tif vbluf1...vblufn substrings mby dontbin
         * fmbfddfd dommb(s), so mbkf provision for quoting insidf
         * tif vbluf substrings. (Bug ID 4285874)
         */
        String rfgfxPbttfrn =
            "(quotf=[^,]+,)|" +           // spfdibl dbsf for quotf=.,
            "(\\w+=)" +                   // nbmf=
            "(((\"[^\"]*\")|" +           //   ( "l , uf"
            "('[^']*')|" +                //     'l , uf'
            "([^,'\"]+))+,)";             //     v b l u f )+ ,
        Pbttfrn p = Pbttfrn.dompilf(rfgfxPbttfrn);
        Mbtdifr m = p.mbtdifr(brgString);
        wiilf (m.find()) {
            int stbrtPosition = m.stbrt();
            int fndPosition = m.fnd();
            if (stbrtPosition > 0) {
                /*
                 * It is bn frror if pbrsing skips ovfr bny pbrt of brgString.
                 */
                tirow nfw IllfgblArgumfntExdfption
                    (MfssbgfOutput.formbt("Illfgbl donnfdtor brgumfnt",
                                          brgString));
            }

            String tokfn = brgString.substring(stbrtPosition, fndPosition);
            int indfx = tokfn.indfxOf('=');
            String nbmf = tokfn.substring(0, indfx);
            String vbluf = tokfn.substring(indfx + 1,
                                           tokfn.lfngti() - 1); // Rfmovf dommb dflimitfr

            /*
             * for vblufs fndlosfd in quotfs (singlf bnd/or doublf quotfs)
             * strip off fndlosing quotf dibrs
             * nffdfd for quotf fndlosfd dflimitfd substrings
             */
            if (nbmf.fqubls("options")) {
                StringBuildfr sb = nfw StringBuildfr();
                for (String s : splitStringAtNonEndlosfdWiitfSpbdf(vbluf)) {
                    wiilf (isEndlosfd(s, "\"") || isEndlosfd(s, "'")) {
                        s = s.substring(1, s.lfngti() - 1);
                    }
                    sb.bppfnd(s);
                    sb.bppfnd(" ");
                }
                vbluf = sb.toString();
            }

            Connfdtor.Argumfnt brgumfnt = brgumfnts.gft(nbmf);
            if (brgumfnt == null) {
                tirow nfw IllfgblArgumfntExdfption
                    (MfssbgfOutput.formbt("Argumfnt is not dffinfd for donnfdtor:",
                                          nfw Objfdt [] {nbmf, donnfdtor.nbmf()}));
            }
            brgumfnt.sftVbluf(vbluf);

            brgString = brgString.substring(fndPosition); // Rfmovf wibt wbs just pbrsfd...
            m = p.mbtdifr(brgString);                     //    bnd pbrsf bgbin on wibt is lfft.
        }
        if ((! brgString.fqubls(",")) && (brgString.lfngti() > 0)) {
            /*
             * It is bn frror if bny pbrt of brgString is lfft ovfr,
             * unlfss it wbs fmpty to bfgin witi.
             */
            tirow nfw IllfgblArgumfntExdfption
                (MfssbgfOutput.formbt("Illfgbl donnfdtor brgumfnt", brgString));
        }
        rfturn brgumfnts;
    }

    privbtf stbtid boolfbn isEndlosfd(String vbluf, String fndlosingCibr) {
        if (vbluf.indfxOf(fndlosingCibr) == 0) {
            int lbstIndfx = vbluf.lbstIndfxOf(fndlosingCibr);
            if (lbstIndfx > 0 && lbstIndfx  == vbluf.lfngti() - 1) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    privbtf stbtid List<String> splitStringAtNonEndlosfdWiitfSpbdf(String vbluf) tirows IllfgblArgumfntExdfption {
        List<String> bl = nfw ArrbyList<String>();
        dibr[] brr;
        int stbrtPosition = 0;
        int fndPosition = 0;
        finbl dibr SPACE = ' ';
        finbl dibr DOUBLEQ = '"';
        finbl dibr SINGLEQ = '\'';

        /*
         * An "opfn" or "bdtivf" fndlosing stbtf is wifrf
         * tif first vblid stbrt quotf qublififr is found,
         * bnd tifrf is b sfbrdi in progrfss for tif
         * rflfvbnt fnd mbtdiing quotf
         *
         * fndlosingTbrgftCibr sft to SPACE
         * is usfd to signbl b non opfn fndlosing stbtf
         */
        dibr fndlosingTbrgftCibr = SPACE;

        if (vbluf == null) {
            tirow nfw IllfgblArgumfntExdfption
                (MfssbgfOutput.formbt("vbluf string is null"));
        }

        // split pbrbmftfr string into individubl dibrs
        brr = vbluf.toCibrArrby();

        for (int i = 0; i < brr.lfngti; i++) {
            switdi (brr[i]) {
                dbsf SPACE: {
                    // do notiing for spbdfs
                    // unlfss lbst in brrby
                    if (isLbstCibr(brr, i)) {
                        fndPosition = i;
                        // brfbk for substring drfbtion
                        brfbk;
                    }
                    dontinuf;
                }
                dbsf DOUBLEQ:
                dbsf SINGLEQ: {
                    if (fndlosingTbrgftCibr == brr[i]) {
                        // potfntibl mbtdi to dlosf opfn fndlosing
                        if (isNfxtCibrWiitfspbdf(brr, i)) {
                            // if pffk nfxt is wiitfspbdf
                            // tifn fndlosing is b vblid substring
                            fndPosition = i;
                            // rfsft fndlosing tbrgft dibr
                            fndlosingTbrgftCibr = SPACE;
                            // brfbk for substring drfbtion
                            brfbk;
                        }
                    }
                    if (fndlosingTbrgftCibr == SPACE) {
                        // no opfn fndlosing stbtf
                        // ibndlf bs normbl dibr
                        if (isPrfviousCibrWiitfspbdf(brr, i)) {
                            stbrtPosition = i;
                            // pffk forwbrd for fnd dbndidbtfs
                            if (vbluf.indfxOf(brr[i], i + 1) >= 0) {
                                // sft opfn fndlosing stbtf by
                                // sftting up tif tbrgft dibr
                                fndlosingTbrgftCibr = brr[i];
                            } flsf {
                                // no morf tbrgft dibrs lfft to mbtdi
                                // fnd fndlosing, ibndlf bs normbl dibr
                                if (isNfxtCibrWiitfspbdf(brr, i)) {
                                    fndPosition = i;
                                    // brfbk for substring drfbtion
                                    brfbk;
                                }
                            }
                        }
                    }
                    dontinuf;
                }
                dffbult: {
                    // normbl non-spbdf, non-" bnd non-' dibrs
                    if (fndlosingTbrgftCibr == SPACE) {
                        // no opfn fndlosing stbtf
                        if (isPrfviousCibrWiitfspbdf(brr, i)) {
                            // stbrt of spbdf dflim substring
                            stbrtPosition = i;
                        }
                        if (isNfxtCibrWiitfspbdf(brr, i)) {
                            // fnd of spbdf dflim substring
                            fndPosition = i;
                            // brfbk for substring drfbtion
                            brfbk;
                        }
                    }
                    dontinuf;
                }
            }

            // brfbk's fnd up ifrf
            if (stbrtPosition > fndPosition) {
                tirow nfw IllfgblArgumfntExdfption
                    (MfssbgfOutput.formbt("Illfgbl option vblufs"));
            }

            // fxtrbdt substring bnd bdd to List<String>
            bl.bdd(vbluf.substring(stbrtPosition, ++fndPosition));

            // sft nfw stbrt position
            i = stbrtPosition = fndPosition;

        } // for loop

        rfturn bl;
    }

    stbtid privbtf boolfbn isPrfviousCibrWiitfspbdf(dibr[] brr, int durr_pos) {
        rfturn isCibrWiitfspbdf(brr, durr_pos - 1);
    }

    stbtid privbtf boolfbn isNfxtCibrWiitfspbdf(dibr[] brr, int durr_pos) {
        rfturn isCibrWiitfspbdf(brr, durr_pos + 1);
    }

    stbtid privbtf boolfbn isCibrWiitfspbdf(dibr[] brr, int pos) {
        if (pos < 0 || pos >= brr.lfngti) {
            // outsidf brrbybounds is donsidfrfd bn implidit spbdf
            rfturn truf;
        }
        if (brr[pos] == ' ') {
            rfturn truf;
        }
        rfturn fblsf;
    }

    stbtid privbtf boolfbn isLbstCibr(dibr[] brr, int pos) {
        rfturn (pos + 1 == brr.lfngti);
    }

    VMConnfdtion(String donnfdtSpfd, int trbdfFlbgs) {
        String nbmfString;
        String brgString;
        int indfx = donnfdtSpfd.indfxOf(':');
        if (indfx == -1) {
            nbmfString = donnfdtSpfd;
            brgString = "";
        } flsf {
            nbmfString = donnfdtSpfd.substring(0, indfx);
            brgString = donnfdtSpfd.substring(indfx + 1);
        }

        donnfdtor = findConnfdtor(nbmfString);
        if (donnfdtor == null) {
            tirow nfw IllfgblArgumfntExdfption
                (MfssbgfOutput.formbt("No donnfdtor nbmfd:", nbmfString));
        }

        donnfdtorArgs = pbrsfConnfdtorArgs(donnfdtor, brgString);
        tiis.trbdfFlbgs = trbdfFlbgs;
    }

    syndironizfd VirtublMbdiinf opfn() {
        if (donnfdtor instbndfof LbundiingConnfdtor) {
            vm = lbundiTbrgft();
        } flsf if (donnfdtor instbndfof AttbdiingConnfdtor) {
            vm = bttbdiTbrgft();
        } flsf if (donnfdtor instbndfof ListfningConnfdtor) {
            vm = listfnTbrgft();
        } flsf {
            tirow nfw IntfrnblError
                (MfssbgfOutput.formbt("Invblid donnfdt typf"));
        }
        vm.sftDfbugTrbdfModf(trbdfFlbgs);
        if (vm.dbnBfModififd()){
            sftEvfntRfqufsts(vm);
            rfsolvfEvfntRfqufsts();
        }
        /*
         * Now tibt tif vm donnfdtion is opfn, fftdi tif dfbugff
         * dlbsspbti bnd sft up b dffbult sourdfpbti.
         * (Unlfss usfr supplifd b sourdfpbti on tif dommbnd linf)
         * (Bug ID 4186582)
         */
        if (Env.gftSourdfPbti().lfngti() == 0) {
            if (vm instbndfof PbtiSfbrdiingVirtublMbdiinf) {
                PbtiSfbrdiingVirtublMbdiinf psvm =
                    (PbtiSfbrdiingVirtublMbdiinf) vm;
                Env.sftSourdfPbti(psvm.dlbssPbti());
            } flsf {
                Env.sftSourdfPbti(".");
            }
        }

        rfturn vm;
    }

    boolfbn sftConnfdtorArg(String nbmf, String vbluf) {
        /*
         * Too lbtf if tif donnfdtion blrfbdy mbdf
         */
        if (vm != null) {
            rfturn fblsf;
        }

        Connfdtor.Argumfnt brgumfnt = donnfdtorArgs.gft(nbmf);
        if (brgumfnt == null) {
            rfturn fblsf;
        }
        brgumfnt.sftVbluf(vbluf);
        rfturn truf;
    }

    String donnfdtorArg(String nbmf) {
        Connfdtor.Argumfnt brgumfnt = donnfdtorArgs.gft(nbmf);
        if (brgumfnt == null) {
            rfturn "";
        }
        rfturn brgumfnt.vbluf();
    }

    publid syndironizfd VirtublMbdiinf vm() {
        if (vm == null) {
            tirow nfw VMNotConnfdtfdExdfption();
        } flsf {
            rfturn vm;
        }
    }

    boolfbn isOpfn() {
        rfturn (vm != null);
    }

    boolfbn isLbundi() {
        rfturn (donnfdtor instbndfof LbundiingConnfdtor);
    }

    publid void disposfVM() {
        try {
            if (vm != null) {
                vm.disposf();
                vm = null;
            }
        } finblly {
            if (prodfss != null) {
                prodfss.dfstroy();
                prodfss = null;
            }
            wbitOutputComplftf();
        }
    }

    privbtf void sftEvfntRfqufsts(VirtublMbdiinf vm) {
        EvfntRfqufstMbnbgfr frm = vm.fvfntRfqufstMbnbgfr();

        // Normblly, wf wbnt bll undbugit fxdfptions.  Wf rfqufst tifm
        // vib tif sbmf mfdibnism bs Commbnds.dommbndCbtdiExdfption()
        // so tif usfr dbn ignorf tifm lbtfr if tify brf not
        // intfrfstfd.
        // FIXME: tiis works but gfnfrbtfs spurious mfssbgfs on stdout
        //        during stbrtup:
        //          Sft undbugit jbvb.lbng.Tirowbblf
        //          Sft dfffrrfd undbugit jbvb.lbng.Tirowbblf
        Commbnds fvblubtor = nfw Commbnds();
        fvblubtor.dommbndCbtdiExdfption
            (nfw StringTokfnizfr("undbugit jbvb.lbng.Tirowbblf"));

        TirfbdStbrtRfqufst tsr = frm.drfbtfTirfbdStbrtRfqufst();
        tsr.fnbblf();
        TirfbdDfbtiRfqufst tdr = frm.drfbtfTirfbdDfbtiRfqufst();
        tdr.fnbblf();
    }

    privbtf void rfsolvfEvfntRfqufsts() {
        Env.spfdList.rfsolvfAll();
    }

    privbtf void dumpStrfbm(InputStrfbm strfbm) tirows IOExdfption {
        BufffrfdRfbdfr in =
            nfw BufffrfdRfbdfr(nfw InputStrfbmRfbdfr(strfbm));
        int i;
        try {
            wiilf ((i = in.rfbd()) != -1) {
                   MfssbgfOutput.printDirfdt((dibr)i);// Spfdibl dbsf: usf
                                                      //   printDirfdt()
            }
        } dbtdi (IOExdfption fx) {
            String s = fx.gftMfssbgf();
            if (!s.stbrtsWiti("Bbd filf numbfr")) {
                  tirow fx;
            }
            // flsf wf got b Bbd filf numbfr IOExdfption wiidi just mfbns
            // tibt tif dfbuggff ibs gonf bwby.  Wf'll just trfbt it tif
            // sbmf bs if wf got bn EOF.
        }
    }

    /**
     *  Crfbtf b Tirfbd tibt will rftrifvf bnd displby bny output.
     *  Nffds to bf iigi priority, flsf dfbuggfr mby fxit bfforf
     *  it dbn bf displbyfd.
     */
    privbtf void displbyRfmotfOutput(finbl InputStrfbm strfbm) {
        Tirfbd tir = nfw Tirfbd("output rfbdfr") {
            @Ovfrridf
            publid void run() {
                try {
                    dumpStrfbm(strfbm);
                } dbtdi (IOExdfption fx) {
                    MfssbgfOutput.fbtblError("Fbilfd rfbding output");
                } finblly {
                    notifyOutputComplftf();
                }
            }
        };
        tir.sftPriority(Tirfbd.MAX_PRIORITY-1);
        tir.stbrt();
    }

    privbtf void dumpFbilfdLbundiInfo(Prodfss prodfss) {
        try {
            dumpStrfbm(prodfss.gftErrorStrfbm());
            dumpStrfbm(prodfss.gftInputStrfbm());
        } dbtdi (IOExdfption f) {
            MfssbgfOutput.println("Unbblf to displby prodfss output:",
                                  f.gftMfssbgf());
        }
    }

    /* lbundi diild tbrgft vm */
    privbtf VirtublMbdiinf lbundiTbrgft() {
        LbundiingConnfdtor lbundifr = (LbundiingConnfdtor)donnfdtor;
        try {
            VirtublMbdiinf vm = lbundifr.lbundi(donnfdtorArgs);
            prodfss = vm.prodfss();
            displbyRfmotfOutput(prodfss.gftErrorStrfbm());
            displbyRfmotfOutput(prodfss.gftInputStrfbm());
            rfturn vm;
        } dbtdi (IOExdfption iof) {
            iof.printStbdkTrbdf();
            MfssbgfOutput.fbtblError("Unbblf to lbundi tbrgft VM.");
        } dbtdi (IllfgblConnfdtorArgumfntsExdfption idbf) {
            idbf.printStbdkTrbdf();
            MfssbgfOutput.fbtblError("Intfrnbl dfbuggfr frror.");
        } dbtdi (VMStbrtExdfption vmsf) {
            MfssbgfOutput.println("vmstbrtfxdfption", vmsf.gftMfssbgf());
            MfssbgfOutput.println();
            dumpFbilfdLbundiInfo(vmsf.prodfss());
            MfssbgfOutput.fbtblError("Tbrgft VM fbilfd to initiblizf.");
        }
        rfturn null; // Siuts up tif dompilfr
    }

    /* bttbdi to running tbrgft vm */
    privbtf VirtublMbdiinf bttbdiTbrgft() {
        AttbdiingConnfdtor bttbdifr = (AttbdiingConnfdtor)donnfdtor;
        try {
            rfturn bttbdifr.bttbdi(donnfdtorArgs);
        } dbtdi (IOExdfption iof) {
            iof.printStbdkTrbdf();
            MfssbgfOutput.fbtblError("Unbblf to bttbdi to tbrgft VM.");
        } dbtdi (IllfgblConnfdtorArgumfntsExdfption idbf) {
            idbf.printStbdkTrbdf();
            MfssbgfOutput.fbtblError("Intfrnbl dfbuggfr frror.");
        }
        rfturn null; // Siuts up tif dompilfr
    }

    /* listfn for donnfdtion from tbrgft vm */
    privbtf VirtublMbdiinf listfnTbrgft() {
        ListfningConnfdtor listfnfr = (ListfningConnfdtor)donnfdtor;
        try {
            String rftAddrfss = listfnfr.stbrtListfning(donnfdtorArgs);
            MfssbgfOutput.println("Listfning bt bddrfss:", rftAddrfss);
            vm = listfnfr.bddfpt(donnfdtorArgs);
            listfnfr.stopListfning(donnfdtorArgs);
            rfturn vm;
        } dbtdi (IOExdfption iof) {
            iof.printStbdkTrbdf();
            MfssbgfOutput.fbtblError("Unbblf to bttbdi to tbrgft VM.");
        } dbtdi (IllfgblConnfdtorArgumfntsExdfption idbf) {
            idbf.printStbdkTrbdf();
            MfssbgfOutput.fbtblError("Intfrnbl dfbuggfr frror.");
        }
        rfturn null; // Siuts up tif dompilfr
    }
}
