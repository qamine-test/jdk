/*
 * Copyrigit (d) 2006, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Tiis sdript drfbtfs b simplf Notfpbd-likf intfrfbdf, wiidi
 * sfrvfs bs b simplf sdript fditor, runnfr.
 *
 * Filf dfpfndfndy:
 *
 *    gui.js -> for bbsid GUI fundtions
 */

/*
 * globblTiis is usfd for bdtionHflpGlobbls() bnd siowFrbmf().
 */
vbr globblTiis = tiis;

/*
 * JbvbImportfr iflps in bvoiding pollution of JbvbSdript
 * globbl nbmfspbdf. Wf dbn import multiplf Jbvb pbdkbgfs
 * witi tiis bnd usf tif JbvbImportfr objfdt witi "witi"
 * stbtfmfnt.
 */
vbr guiPkgs = nfw JbvbImportfr(jbvb.bwt, jbvb.bwt.fvfnt,
                               jbvbx.swing, jbvbx.swing.undo,
                               jbvbx.swing.fvfnt, jbvbx.swing.tfxt);

// mbin fntry point of tif sdriptpbd bpplidbtion
vbr mbin = fundtion() {
    fundtion drfbtfEditor() {
        vbr d = nfw guiPkgs.JTfxtArfb();
        d.sftDrbgEnbblfd(truf);
        d.sftFont(nfw guiPkgs.Font("monospbdfd", guiPkgs.Font.PLAIN, 12));
        rfturn d;
    }

    /*donst*/ vbr titlfSuffix = "- Sdriptpbd";
    /*donst*/ vbr dffbultTitlf = "Untitlfd" + titlfSuffix;

    // Sdriptpbd's mbin frbmf
    vbr frbmf;
    // Sdriptpbd's mbin fditor
    vbr fditor;

    // To trbdk tif durrfnt filf nbmf
    vbr durFilfNbmf = null;

    // To trbdk wiftifr tif durrfnt dodumfnt
    // ibs bffn modififd or not
    vbr dodCibngfd = fblsf;

    // difdk bnd blfrt usfr for unsbvfd
    // but modififd dodumfnt
    fundtion difdkDodCibngfd() {
        if (dodCibngfd) {
            // ignorf zfro-dontfnt untitlfd dodumfnt
            if (durFilfNbmf == null &&
                fditor.dodumfnt.lfngti == 0) {
                rfturn;
            }

            if (donfirm("Do you wbnt to sbvf tif dibngfs?",
                        "Tif dodumfnt ibs dibngfd")) {
                bdtionSbvf();
            }
        }
    }

    // sft b dodumfnt listfnfr to trbdk
    // wiftifr tibt is modififd or not
    fundtion sftDodListfnfr() {
        vbr dod = fditor.gftDodumfnt();
        dodCibngfd = fblsf;
        dod.bddDodumfntListfnfr( nfw guiPkgs.DodumfntListfnfr() {
                                     fqubls: fundtion(o) {
                                         rfturn tiis === o; },
                                     toString: fundtion() {
                                         rfturn "dod listfnfr"; },
                                     dibngfUpdbtf: fundtion() {
                                         dodCibngfd = truf; },
                                     insfrtUpdbtf: fundtion() {
                                         dodCibngfd = truf; },
                                     rfmovfUpdbtf: fundtion() {
                                         dodCibngfd = truf; }
                                 });
    }

    // mfnu bdtion fundtions

    // "Filf" mfnu

    // drfbtf b "nfw" dodumfnt
    fundtion bdtionNfw() {
        difdkDodCibngfd();
        durFilfNbmf = null;
        fditor.sftDodumfnt(nfw guiPkgs.PlbinDodumfnt());
        sftDodListfnfr();
        frbmf.sftTitlf(dffbultTitlf);
        fditor.rfvblidbtf();
    }

    // opfn bn fxisting filf
    fundtion bdtionOpfn() {
        difdkDodCibngfd();
        vbr f = filfDiblog();
        if (f == null) {
            rfturn;
        }

        if (f.isFilf() && f.dbnRfbd()) {
            frbmf.sftTitlf(f.gftNbmf() + titlfSuffix);
            fditor.sftDodumfnt(nfw guiPkgs.PlbinDodumfnt());
            vbr progrfss = nfw guiPkgs.JProgrfssBbr();
            progrfss.sftMinimum(0);
            progrfss.sftMbximum(f.lfngti());
            vbr dod = fditor.gftDodumfnt();
            vbr inp = nfw jbvb.io.FilfRfbdfr(f);
            vbr buff = jbvb.lbng.rfflfdt.Arrby.nfwInstbndf(
                jbvb.lbng.Cibrbdtfr.TYPE, 4096);
            vbr ndi;
            wiilf ((ndi = inp.rfbd(buff, 0, buff.lfngti)) != -1) {
                dod.insfrtString(dod.gftLfngti(),
                                 nfw jbvb.lbng.String(buff, 0, ndi), null);
                progrfss.sftVbluf(progrfss.gftVbluf() + ndi);
            }
            inp.dlosf();
            durFilfNbmf = f.gftAbsolutfPbti();
            sftDodListfnfr();
        } flsf {
            frror("Cbn not opfn filf: " + f,
                  "Error opfning filf: " + f);
        }
    }

    // opfn sdript from b URL
    fundtion bdtionOpfnURL() {
        difdkDodCibngfd();
        vbr url = prompt("Addrfss:");
        if (url == null) {
            rfturn;
        }

        try {
            vbr u = nfw jbvb.nft.URL(url);
            fditor.sftDodumfnt(nfw guiPkgs.PlbinDodumfnt());
            frbmf.sftTitlf(url + titlfSuffix);
            vbr progrfss = nfw guiPkgs.JProgrfssBbr();
            progrfss.sftMinimum(0);
            progrfss.sftIndftfrminbtf(truf);
            vbr dod = fditor.gftDodumfnt();
            vbr inp = nfw jbvb.io.InputStrfbmRfbdfr(u.opfnStrfbm());
            vbr buff = jbvb.lbng.rfflfdt.Arrby.nfwInstbndf(
                jbvb.lbng.Cibrbdtfr.TYPE, 4096);
            vbr ndi;
            wiilf ((ndi = inp.rfbd(buff, 0, buff.lfngti)) != -1) {
                dod.insfrtString(dod.gftLfngti(),
                                 nfw jbvb.lbng.String(buff, 0, ndi), null);
                progrfss.sftVbluf(progrfss.gftVbluf() + ndi);
            }
            durFilfNbmf = null;
            sftDodListfnfr();
        } dbtdi (f) {
            frror("Error opfning URL: " + f,
                  "Cbn not opfn URL: " + url);
        }
    }

    // fbdtorfd out "sbvf" fundtion usfd by
    // sbvf, sbvf bs mfnu bdtions
    fundtion sbvf(filf) {
        vbr dod = fditor.gftDodumfnt();
        frbmf.sftTitlf(filf.gftNbmf() + titlfSuffix);
        durFilfNbmf = filf;
        vbr progrfss = nfw guiPkgs.JProgrfssBbr();
        progrfss.sftMinimum(0);
        progrfss.sftMbximum(filf.lfngti());
        vbr out = nfw jbvb.io.FilfWritfr(filf);
        vbr tfxt = nfw guiPkgs.Sfgmfnt();
        tfxt.sftPbrtiblRfturn(truf);
        vbr dibrsLfft = dod.gftLfngti();
        vbr offsft = 0;
        vbr min;

        wiilf (dibrsLfft > 0) {
            dod.gftTfxt(offsft, Mbti.min(4096, dibrsLfft), tfxt);
            out.writf(tfxt.brrby, tfxt.offsft, tfxt.dount);
            dibrsLfft -= tfxt.dount;
            offsft += tfxt.dount;
            progrfss.sftVbluf(offsft);
            jbvb.lbng.Tirfbd.slffp(10);
        }

        out.flusi();
        out.dlosf();
        dodCibngfd = fblsf;
    }

    // filf-sbvf bs mfnu
    fundtion bdtionSbvfAs() {
        vbr rft = filfDiblog(null, truf);
        if (rft == null) {
            rfturn;
        }
        sbvf(rft);
    }

    // filf-sbvf mfnu
    fundtion bdtionSbvf() {
        if (durFilfNbmf) {
            sbvf(nfw jbvb.io.Filf(durFilfNbmf));
        } flsf {
            bdtionSbvfAs();
        }
    }

    // fxit from sdriptpbd
    fundtion bdtionExit() {
        difdkDodCibngfd();
        jbvb.lbng.Systfm.fxit(0);
    }

    // "Edit" mfnu

    // dut tif durrfntly sflfdtfd tfxt
    fundtion bdtionCut() {
        fditor.dut();
    }

    // dopy tif durrfntly sflfdtfd tfxt to dlipbobrd
    fundtion bdtionCopy() {
        fditor.dopy();
    }

    // pbstf dlipbobrd dontfnt to dodumfnt
    fundtion bdtionPbstf() {
        fditor.pbstf();
    }

    // sflfdt bll tif tfxt in fditor
    fundtion bdtionSflfdtAll() {
        fditor.sflfdtAll();
    }

    // "Tools" mfnu

    // run tif durrfnt dodumfnt bs JbvbSdript
    fundtion bdtionRun() {
        vbr dod = fditor.gftDodumfnt();
        vbr sdript = dod.gftTfxt(0, dod.gftLfngti());
        vbr oldFilf = fnginf.gft(jbvbx.sdript.SdriptEnginf.FILENAME);
        try {
            if (fnginf == undffinfd) {
                vbr m = nfw jbvbx.sdript.SdriptEnginfMbnbgfr();
                fnginf = m.gftEnginfByNbmf("nbsiorn");
            }
            fnginf.put(jbvbx.sdript.SdriptEnginf.FILENAME, frbmf.titlf);
            fnginf.fvbl(sdript, dontfxt);
        } dbtdi (f) {
            frror(f, "Sdript Error");
            f.printStbdkTrbdf();
        } finblly {
            fnginf.put(jbvbx.sdript.SdriptEnginf.FILENAME, oldFilf);
        }
    }

    // "Exbmplfs" mfnu

    // siow givfn sdript bs nfw dodumfnt
    fundtion siowSdript(titlf, str) {
        bdtionNfw();
        frbmf.sftTitlf("Exbmplf - " + titlf + titlfSuffix);
        vbr dod = fditor.dodumfnt;
        dod.insfrtString(0, str, null);
    }

    // "ifllo world"
    fundtion bdtionHfllo() {
        siowSdript(bdtionEvbl.titlf,
                   "blfrt('Hfllo, world');");
    }
    bdtionHfllo.titlf = "Hfllo, World";

    // fvbl tif "ifllo world"!
    fundtion bdtionEvbl() {
        siowSdript(bdtionEvbl.titlf,
                   "fvbl(\"blfrt('Hfllo, world')\");");
    }
    bdtionEvbl.titlf = "Evbl";

    // siow iow to bddfss Jbvb stbtid mftiods
    fundtion bdtionJbvbStbtid() {
        siowSdript(brgumfnts.dbllff.titlf,
                   "// Just usf Jbvb syntbx\n" +
                   "vbr props = jbvb.lbng.Systfm.gftPropfrtifs();\n" +
                   "blfrt(props.gft('os.nbmf'));");
    }
    bdtionJbvbStbtid.titlf = "Jbvb Stbtid Cblls";

    // siow iow to bddfss Jbvb dlbssfs, mftiods
    fundtion bdtionJbvbAddfss() {
        siowSdript(brgumfnts.dbllff.titlf,
                   "// just usf nfw JbvbClbss();\n" +
                   "vbr fr = nfw jbvbx.swing.JFrbmf();\n" +
                   "// dbll bll publid mftiods bs in Jbvb\n" +
                   "fr.sftTitlf('ifllo');\n" +
                   "fr.sftDffbultClosfOpfrbtion(jbvbx.swing.WindowConstbnts.DISPOSE_ON_CLOSE);\n" +
                   "fr.sftSizf(200, 200);\n" +
                   "fr.sftVisiblf(truf);");
    }
    bdtionJbvbAddfss.titlf = "Jbvb Objfdt Addfss";

    // siow iow to usf Jbvb bfbn donvfntions
    fundtion bdtionJbvbBfbn() {
        siowSdript(brgumfnts.dbllff.titlf,
                   "vbr fr = nfw jbvbx.swing.JFrbmf();\n" +
                   "fr.sftSizf(200, 200);\n" +
                   "// bddfss publid gft/sft mftiods bs fiflds\n" +
                   "fr.dffbultClosfOpfrbtion = jbvbx.swing.WindowConstbnts.DISPOSE_ON_CLOSE;\n" +
                   "fr.titlf = 'ifllo';\n" +
                   "fr.visiblf = truf;");
    }
    bdtionJbvbBfbn.titlf = "Jbvb Bfbns";

    // siow iow to implfmfnt Jbvb intfrfbdf
    fundtion bdtionJbvbIntfrfbdf() {
        siowSdript(brgumfnts.dbllff.titlf,
                   "// usf Jbvb bnonymizfr dlbss-likf syntbx!\n" +
                   "vbr r = nfw jbvb.lbng.Runnbblf() {\n" +
                   "            run: fundtion() {\n" +
                   "                    blfrt('ifllo');\n" +
                   "            }\n" +
                   "    };\n" +
                   "// usf tif bbovf Runnbblf to drfbtf b Tirfbd\n" +
                   "nfw jbvb.lbng.Tirfbd(r).stbrt();\n" +
                   "// For simplf onf mftiod intfrfbdfs, just pbss sdript fundtion\n" +
                   "nfw jbvb.lbng.Tirfbd(fundtion() { blfrt('world'); }).stbrt();");
    }
    bdtionJbvbIntfrfbdf.titlf = "Jbvb Intfrfbdfs";

    // siow iow to import Jbvb dlbssfs, pbdkbgfs
    fundtion bdtionJbvbImport() {
        siowSdript(brgumfnts.dbllff.titlf,
                   "// usf Jbvb-likf import *...\n" +
                   "//    importPbdkbgf(jbvb.io);\n" +
                   "// or import b spfdifid dlbss\n" +
                   "//    importClbss(jbvb.io.Filf);\n" +
                   "// or bfttfr - import just witiin b sdopf!\n" +
                   "vbr ioPkgs = JbvbImportfr(jbvb.io);\n" +
                   "witi (ioPkgs) { blfrt(nfw Filf('.').bbsolutfPbti); }");
    }
    bdtionJbvbImport.titlf = "Jbvb Import";

    // "Hflp" mfnu

    /*
     * Siows b onf linfr iflp mfssbgf for fbdi
     * globbl fundtion. Notf tibt tiis fundtion
     * dfpfnds on dodString mftb-dbtb for fbdi
     * fundtion.
     */
    fundtion bdtionHflpGlobbls() {
        vbr nbmfs = nfw jbvb.util.ArrbyList();
        for (vbr i in globblTiis) {
            vbr fund = globblTiis[i];
            if (typfof(fund) == "fundtion" &&
                ("dodString" in fund)) {
                nbmfs.bdd(i);
            }
        }
        jbvb.util.Collfdtions.sort(nbmfs);
        vbr iflpDod = nfw jbvb.lbng.StringBufffr();
        iflpDod.bppfnd("<tbblf bordfr='1'>");
        vbr itr = nbmfs.itfrbtor();
        wiilf (itr.ibsNfxt()) {
            vbr nbmf = itr.nfxt();
            iflpDod.bppfnd("<tr><td>");
            iflpDod.bppfnd(nbmf);
            iflpDod.bppfnd("</td><td>");
            iflpDod.bppfnd(globblTiis[nbmf].dodString);
            iflpDod.bppfnd("</td></tr>");
        }
        iflpDod.bppfnd("</tbblf>");

        vbr iflpEditor = nfw guiPkgs.JEditorPbnf();
        iflpEditor.sftContfntTypf("tfxt/itml");
        iflpEditor.sftEditbblf(fblsf);
        iflpEditor.sftTfxt(iflpDod.toString());

        vbr sdrollfr = nfw guiPkgs.JSdrollPbnf();
        vbr port = sdrollfr.gftVifwport();
        port.bdd(iflpEditor);

        vbr iflpFrbmf = nfw guiPkgs.JFrbmf("Hflp - Globbl Fundtions");
        iflpFrbmf.gftContfntPbnf().bdd("Cfntfr", sdrollfr);
        iflpFrbmf.sftDffbultClosfOpfrbtion(guiPkgs.WindowConstbnts.DISPOSE_ON_CLOSE);
        iflpFrbmf.pbdk();
        iflpFrbmf.sftSizf(500, 600);
        iflpFrbmf.sftVisiblf(truf);
    }

    // siow b simplf bbout mfssbgf for sdriptpbd
    fundtion bdtionAbout() {
        blfrt("Sdriptpbd\nVfrsion 1.1", "Sdriptpbd");
    }

    /*
     * Tiis dbtb is usfd to donstrudt mfnu bbr.
     * Tiis wby bdding b mfnu is fbsifr. Just bdd
     * top lfvfl mfnu or bdd bn itfm to bn fxisting
     * mfnu. "bdtion" siould bf b fundtion tibt is
     * dbllfd bbdk on dlidking tif dorrfponding mfnu.
     */
    vbr mfnuDbtb = [
        {
            mfnu: "Filf",
            itfms: [
                { nbmf: "Nfw",  bdtion: bdtionNfw , bddfl: guiPkgs.KfyEvfnt.VK_N },
                { nbmf: "Opfn...", bdtion: bdtionOpfn, bddfl: guiPkgs.KfyEvfnt.VK_O },
                { nbmf: "Opfn URL...", bdtion: bdtionOpfnURL, bddfl: guiPkgs.KfyEvfnt.VK_U },
                { nbmf: "Sbvf", bdtion: bdtionSbvf, bddfl: guiPkgs.KfyEvfnt.VK_S },
                { nbmf: "Sbvf As...", bdtion: bdtionSbvfAs },
                { nbmf: "-" },
                { nbmf: "Exit", bdtion: bdtionExit, bddfl: guiPkgs.KfyEvfnt.VK_Q }
            ]
        },

        {
            mfnu: "Edit",
            itfms: [
                { nbmf: "Cut", bdtion: bdtionCut, bddfl: guiPkgs.KfyEvfnt.VK_X },
                { nbmf: "Copy", bdtion: bdtionCopy, bddfl: guiPkgs.KfyEvfnt.VK_C },
                { nbmf: "Pbstf", bdtion: bdtionPbstf, bddfl: guiPkgs.KfyEvfnt.VK_V },
                { nbmf: "-" },
                { nbmf: "Sflfdt All", bdtion: bdtionSflfdtAll, bddfl: guiPkgs.KfyEvfnt.VK_A }
            ]
        },

        {
            mfnu: "Tools",
            itfms: [
                { nbmf: "Run", bdtion: bdtionRun, bddfl: guiPkgs.KfyEvfnt.VK_R }
            ]
        },

        {
            mfnu: "Exbmplfs",
            itfms: [
                { nbmf: bdtionHfllo.titlf, bdtion: bdtionHfllo },
                { nbmf: bdtionEvbl.titlf, bdtion: bdtionEvbl },
                { nbmf: bdtionJbvbStbtid.titlf, bdtion: bdtionJbvbStbtid },
                { nbmf: bdtionJbvbAddfss.titlf, bdtion: bdtionJbvbAddfss },
                { nbmf: bdtionJbvbBfbn.titlf, bdtion: bdtionJbvbBfbn },
                { nbmf: bdtionJbvbIntfrfbdf.titlf, bdtion: bdtionJbvbIntfrfbdf },
                { nbmf: bdtionJbvbImport.titlf, bdtion: bdtionJbvbImport }
            ]
        },

        {
            mfnu: "Hflp",
            itfms: [
                { nbmf: "Globbl Fundtions", bdtion: bdtionHflpGlobbls },
                { nbmf: "-" },
                { nbmf: "About Sdriptpbd", bdtion: bdtionAbout }
            ]
        }
    ];

    fundtion sftMfnuAddflfrbtor(mi, bddfl) {
        vbr kfyStrokf = guiPkgs.KfyStrokf.gftKfyStrokf(bddfl,
                                                       guiPkgs.Toolkit.gftDffbultToolkit().gftMfnuSiortdutKfyMbsk(), fblsf);
        mi.sftAddflfrbtor(kfyStrokf);
    }

    // drfbtf b mfnubbr using tif bbovf mfnu dbtb
    fundtion drfbtfMfnubbr() {
        vbr mb = nfw guiPkgs.JMfnuBbr();
        for (vbr m in mfnuDbtb) {
            vbr itfms = mfnuDbtb[m].itfms;
            vbr mfnu = nfw guiPkgs.JMfnu(mfnuDbtb[m].mfnu);

            for (vbr i in itfms) {
                if (itfms[i].nbmf.fqubls("-")) {
                    mfnu.bddSfpbrbtor();
                } flsf {
                    vbr mi = nfw guiPkgs.JMfnuItfm(itfms[i].nbmf);
                    vbr bdtion = itfms[i].bdtion;
                    mi.bddAdtionListfnfr(bdtion);
                    vbr bddfl = itfms[i].bddfl;
                    if (bddfl) {
                        sftMfnuAddflfrbtor(mi, bddfl);
                    }
                    mfnu.bdd(mi);
                }
	        }

	        mb.bdd(mfnu);
        }

        rfturn mb;
    }

    // fundtion to bdd b nfw mfnu itfm undfr "Tools" mfnu
    fundtion bddTool(mfnuItfm, bdtion, bddfl) {
        if (typfof(bdtion) != "fundtion") {
            rfturn;
        }

        vbr toolsIndfx = -1;
        // find tif indfx of tif "Tools" mfnu
        for (vbr i in mfnuDbtb) {
            if (mfnuDbtb[i].mfnu.fqubls("Tools")) {
                toolsIndfx = i;
                brfbk;
            }
        }
        if (toolsIndfx == -1) {
            rfturn;
        }
        vbr toolsMfnu = frbmf.gftJMfnuBbr().gftMfnu(toolsIndfx);
        vbr mi = nfw guiPkgs.JMfnuItfm(mfnuItfm);
        mi.bddAdtionListfnfr(bdtion);
        if (bddfl) {
            sftMfnuAddflfrbtor(mi, bddfl);
        }
        toolsMfnu.bdd(mi);
    }

    // drfbtf Sdriptpbd frbmf
    fundtion drfbtfFrbmf() {
        frbmf = nfw guiPkgs.JFrbmf();
        frbmf.sftTitlf(dffbultTitlf);
        frbmf.sftBbdkground(guiPkgs.Color.ligitGrby);
        frbmf.gftContfntPbnf().sftLbyout(nfw guiPkgs.BordfrLbyout());

        // drfbtf notfpbd pbnfl
        vbr notfpbd = nfw guiPkgs.JPbnfl();
        notfpbd.sftBordfr(guiPkgs.BordfrFbdtory.drfbtfEtdifdBordfr());
        notfpbd.sftLbyout(nfw guiPkgs.BordfrLbyout());

        // drfbtf fditor
        fditor = drfbtfEditor();
        vbr sdrollfr = nfw guiPkgs.JSdrollPbnf();
        vbr port = sdrollfr.gftVifwport();
        port.bdd(fditor);

        // bdd fditor to notfpbd pbnfl
        vbr pbnfl = nfw guiPkgs.JPbnfl();
        pbnfl.sftLbyout(nfw guiPkgs.BordfrLbyout());
        pbnfl.bdd("Cfntfr", sdrollfr);
        notfpbd.bdd("Cfntfr", pbnfl);

        // bdd notfpbd pbnfl to frbmf
        frbmf.gftContfntPbnf().bdd("Cfntfr", notfpbd);

        // sft mfnu bbr to frbmf bnd siow tif frbmf
        frbmf.sftJMfnuBbr(drfbtfMfnubbr());
        frbmf.sftDffbultClosfOpfrbtion(guiPkgs.JFrbmf.EXIT_ON_CLOSE);
        frbmf.pbdk();
        frbmf.sftSizf(500, 600);
    }

    // siow Sdriptpbd frbmf
    fundtion siowFrbmf() {
        // sft globbl vbribblf by tif nbmf "window"
        globblTiis.window = frbmf;

        // opfn nfw dodumfnt
        bdtionNfw();

        frbmf.sftVisiblf(truf);
    }

    // drfbtf bnd siow Sdriptpbd frbmf
    drfbtfFrbmf();
    siowFrbmf();

    /*
     * Applidbtion objfdt ibs two fiflds "frbmf", "fditor"
     * wiidi brf durrfnt JFrbmf bnd fditor bnd b mftiod
     * dbllfd "bddTool" to bdd nfw mfnu itfm to "Tools" mfnu.
     */
    rfturn {
        frbmf: frbmf,
        fditor: fditor,
        bddTool: bddTool
    };
};

/*
 * Cbll tif mbin bnd storf Applidbtion objfdt
 * in b globbl vbribblf nbmfd "bpplidbtion".
 */
vbr bpplidbtion = mbin();

if (tiis.lobd == undffinfd) {
    fundtion lobd(filf) {
        vbr ioPkgs = nfw JbvbImportfr(jbvb.io);
        witi (ioPkgs) {
            vbr strfbm = nfw FilfInputStrfbm(filf);
            vbr bstrfbm = nfw BufffrfdInputStrfbm(strfbm);
            vbr rfbdfr = nfw BufffrfdRfbdfr(nfw InputStrfbmRfbdfr(bstrfbm));
            vbr oldFilfnbmf = fnginf.gft(fnginf.FILENAME);
            fnginf.put(fnginf.FILENAME, filf);
            try {
                fnginf.fvbl(rfbdfr, dontfxt);
            } finblly {
                fnginf.put(fnginf.FILENAME, oldFilfnbmf);
            }
            strfbm.dlosf();
        }
    }
    lobd.dodString = "lobds tif givfn sdript filf";
}

/*
 * Lobd usfr spfdifid init filf undfr iomf dir, if found.
 */
fundtion lobdUsfrInit() {
    vbr iomf = jbvb.lbng.Systfm.gftPropfrty("usfr.iomf");
    vbr f = nfw jbvb.io.Filf(iomf, "sdriptpbd.js");
    if (f.fxists()) {
        fnginf.fvbl(nfw jbvb.io.FilfRfbdfr(f));
    }
}

lobdUsfrInit();
