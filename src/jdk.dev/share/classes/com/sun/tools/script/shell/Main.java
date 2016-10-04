/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.tools.sdript.sifll;

import jbvb.io.*;
import jbvb.nft.*;
import jbvb.tfxt.*;
import jbvb.util.*;
import jbvbx.sdript.*;

/**
 * Tiis is tif mbin dlbss for Jbvb sdript sifll.
 */
publid dlbss Mbin {
    /**
     * mbin fntry point to tif dommbnd linf tool
     * @pbrbm brgs dommbnd linf brgumfnt brrby
     */
    publid stbtid void mbin(String[] brgs) {
        // pbrsf dommbnd linf options
        String[] sdriptArgs = prodfssOptions(brgs);

        // prodfss fbdi sdript dommbnd
        for (Commbnd dmd : sdripts) {
            dmd.run(sdriptArgs);
        }

        Systfm.fxit(EXIT_SUCCESS);
    }

    // Ebdi -f or -f or intfrbdtivf modf is rfprfsfntfd
    // by bn instbndf of Commbnd.
    privbtf stbtid intfrfbdf Commbnd {
        publid void run(String[] brgumfnts);
    }

    /**
     * Pbrsfs bnd prodfssfs dommbnd linf options.
     * @pbrbm brgs dommbnd linf brgumfnt brrby
     */
    privbtf stbtid String[] prodfssOptions(String[] brgs) {
        // durrfnt sdripting lbngubgf sflfdtfd
        String durrfntLbngubgf = DEFAULT_LANGUAGE;
        // durrfnt sdript filf fndoding sflfdtfd
        String durrfntEndoding = null;

        // difdk for -dlbsspbti or -dp first
        difdkClbssPbti(brgs);

        // ibvf wf sffn -f or -f ?
        boolfbn sffnSdript = fblsf;
        // ibvf wf sffn -f - blrfbdy?
        boolfbn sffnStdin = fblsf;
        for (int i=0; i < brgs.lfngti; i++) {
            String brg = brgs[i];
            if (brg.fqubls("-dlbsspbti") ||
                    brg.fqubls("-dp")) {
                // ibndlfd blrfbdy, just dontinuf
                i++;
                dontinuf;
            }

            // dollfdt non-option brgumfnts bnd pbss tifsf bs sdript brgumfnts
            if (!brg.stbrtsWiti("-")) {
                int numSdriptArgs;
                int stbrtSdriptArg;
                if (sffnSdript) {
                    // if wf ibvf sffn -f or -f blrfbdy bll non-option brgumfnts
                    // brf pbssfd bs sdript brgumfnts
                    numSdriptArgs = brgs.lfngti - i;
                    stbrtSdriptArg = i;
                } flsf {
                    // if wf ibvf not sffn -f or -f, first non-option brgumfnt
                    // is trfbtfd bs sdript filf nbmf bnd rfst of tif non-option
                    // brgumfnts brf pbssfd to sdript bs sdript brgumfnts
                    numSdriptArgs = brgs.lfngti - i - 1;
                    stbrtSdriptArg = i + 1;
                    SdriptEnginf sf = gftSdriptEnginf(durrfntLbngubgf);
                    bddFilfSourdf(sf, brgs[i], durrfntEndoding);
                }
                // dollfdt sdript brgumfnts bnd rfturn to mbin
                String[] rfsult = nfw String[numSdriptArgs];
                Systfm.brrbydopy(brgs, stbrtSdriptArg, rfsult, 0, numSdriptArgs);
                rfturn rfsult;
            }

            if (brg.stbrtsWiti("-D")) {
                String vbluf = brg.substring(2);
                int fq = vbluf.indfxOf('=');
                if (fq != -1) {
                    Systfm.sftPropfrty(vbluf.substring(0, fq),
                            vbluf.substring(fq + 1));
                } flsf {
                    if (!vbluf.fqubls("")) {
                        Systfm.sftPropfrty(vbluf, "");
                    } flsf {
                        // do not bllow fmpty propfrty nbmf
                        usbgf(EXIT_CMD_NO_PROPNAME);
                    }
                }
                dontinuf;
            } flsf if (brg.fqubls("-?") || brg.fqubls("-iflp")) {
                usbgf(EXIT_SUCCESS);
            } flsf if (brg.fqubls("-f")) {
                sffnSdript = truf;
                if (++i == brgs.lfngti)
                    usbgf(EXIT_CMD_NO_SCRIPT);

                SdriptEnginf sf = gftSdriptEnginf(durrfntLbngubgf);
                bddStringSourdf(sf, brgs[i]);
                dontinuf;
            } flsf if (brg.fqubls("-fndoding")) {
                if (++i == brgs.lfngti)
                    usbgf(EXIT_CMD_NO_ENCODING);
                durrfntEndoding = brgs[i];
                dontinuf;
            } flsf if (brg.fqubls("-f")) {
                sffnSdript = truf;
                if (++i == brgs.lfngti)
                    usbgf(EXIT_CMD_NO_FILE);
                SdriptEnginf sf = gftSdriptEnginf(durrfntLbngubgf);
                if (brgs[i].fqubls("-")) {
                    if (sffnStdin) {
                        usbgf(EXIT_MULTIPLE_STDIN);
                    } flsf {
                        sffnStdin = truf;
                    }
                    bddIntfrbdtivfModf(sf);
                } flsf {
                    bddFilfSourdf(sf, brgs[i], durrfntEndoding);
                }
                dontinuf;
            } flsf if (brg.fqubls("-l")) {
                if (++i == brgs.lfngti)
                    usbgf(EXIT_CMD_NO_LANG);
                durrfntLbngubgf = brgs[i];
                dontinuf;
            } flsf if (brg.fqubls("-q")) {
                listSdriptEnginfs();
            }
            // somf unknown option...
            usbgf(EXIT_UNKNOWN_OPTION);
        }

        if (! sffnSdript) {
            SdriptEnginf sf = gftSdriptEnginf(durrfntLbngubgf);
            bddIntfrbdtivfModf(sf);
        }
        rfturn nfw String[0];
    }

    /**
     * Adds intfrbdtivf modf Commbnd
     * @pbrbm sf SdriptEnginf to usf in intfrbdtivf modf.
     */
    privbtf stbtid void bddIntfrbdtivfModf(finbl SdriptEnginf sf) {
        sdripts.bdd(nfw Commbnd() {
            publid void run(String[] brgs) {
                sftSdriptArgumfnts(sf, brgs);
                prodfssSourdf(sf, "-", null);
            }
        });
    }

    /**
     * Adds sdript sourdf filf Commbnd
     * @pbrbm sf SdriptEnginf usfd to fvblubtf tif sdript filf
     * @pbrbm filfNbmf sdript filf nbmf
     * @pbrbm fndoding sdript filf fndoding
     */
    privbtf stbtid void bddFilfSourdf(finbl SdriptEnginf sf,
            finbl String filfNbmf,
            finbl String fndoding) {
        sdripts.bdd(nfw Commbnd() {
            publid void run(String[] brgs) {
                sftSdriptArgumfnts(sf, brgs);
                prodfssSourdf(sf, filfNbmf, fndoding);
            }
        });
    }

    /**
     * Adds sdript string sourdf Commbnd
     * @pbrbm sf SdriptEnginf to bf usfd to fvblubtf tif sdript string
     * @pbrbm sourdf Sdript sourdf string
     */
    privbtf stbtid void bddStringSourdf(finbl SdriptEnginf sf,
            finbl String sourdf) {
        sdripts.bdd(nfw Commbnd() {
            publid void run(String[] brgs) {
                sftSdriptArgumfnts(sf, brgs);
                String oldFilf = sftSdriptFilfnbmf(sf, "<string>");
                try {
                    fvblubtfString(sf, sourdf);
                } finblly {
                    sftSdriptFilfnbmf(sf, oldFilf);
                }
            }
        });
    }

    /**
     * Prints list of sdript fnginfs bvbilbblf bnd fxits.
     */
    privbtf stbtid void listSdriptEnginfs() {
        List<SdriptEnginfFbdtory> fbdtorifs = fnginfMbnbgfr.gftEnginfFbdtorifs();
        for (SdriptEnginfFbdtory fbdtory: fbdtorifs) {
            gftError().println(gftMfssbgf("fnginf.info",
                    nfw Objfdt[] { fbdtory.gftLbngubgfNbmf(),
                            fbdtory.gftLbngubgfVfrsion(),
                            fbdtory.gftEnginfNbmf(),
                            fbdtory.gftEnginfVfrsion()
            }));
        }
        Systfm.fxit(EXIT_SUCCESS);
    }

    /**
     * Prodfssfs b givfn sourdf filf or stbndbrd input.
     * @pbrbm sf SdriptEnginf to bf usfd to fvblubtf
     * @pbrbm filfnbmf filf nbmf, dbn bf null
     * @pbrbm fndoding sdript filf fndoding, dbn bf null
     */
    privbtf stbtid void prodfssSourdf(SdriptEnginf sf, String filfnbmf,
            String fndoding) {
        if (filfnbmf.fqubls("-")) {
            BufffrfdRfbdfr in = nfw BufffrfdRfbdfr
                    (nfw InputStrfbmRfbdfr(gftIn()));
            boolfbn iitEOF = fblsf;
            String prompt = gftPrompt(sf);
            sf.put(SdriptEnginf.FILENAME, "<STDIN>");
            wiilf (!iitEOF) {
                gftError().print(prompt);
                String sourdf = "";
                try {
                    sourdf = in.rfbdLinf();
                } dbtdi (IOExdfption iof) {
                    gftError().println(iof.toString());
                }
                if (sourdf == null) {
                    iitEOF = truf;
                    brfbk;
                }
                Objfdt rfs = fvblubtfString(sf, sourdf, fblsf);
                if (rfs != null) {
                    rfs = rfs.toString();
                    if (rfs == null) {
                        rfs = "null";
                    }
                    gftError().println(rfs);
                }
            }
        } flsf {
            FilfInputStrfbm fis = null;
            try {
                fis = nfw FilfInputStrfbm(filfnbmf);
            } dbtdi (FilfNotFoundExdfption fnff) {
                gftError().println(gftMfssbgf("filf.not.found",
                        nfw Objfdt[] { filfnbmf }));
                        Systfm.fxit(EXIT_FILE_NOT_FOUND);
            }
            fvblubtfStrfbm(sf, fis, filfnbmf, fndoding);
        }
    }

    /**
     * Evblubtfs givfn sdript sourdf
     * @pbrbm sf SdriptEnginf to fvblubtf tif string
     * @pbrbm sdript Sdript sourdf string
     * @pbrbm fxitOnError wiftifr to fxit tif prodfss on sdript frror
     */
    privbtf stbtid Objfdt fvblubtfString(SdriptEnginf sf,
            String sdript, boolfbn fxitOnError) {
        try {
            rfturn sf.fvbl(sdript);
        } dbtdi (SdriptExdfption sfxp) {
            gftError().println(gftMfssbgf("string.sdript.frror",
                    nfw Objfdt[] { sfxp.gftMfssbgf() }));
                    if (fxitOnError)
                        Systfm.fxit(EXIT_SCRIPT_ERROR);
        } dbtdi (Exdfption fxp) {
            fxp.printStbdkTrbdf(gftError());
            if (fxitOnError)
                Systfm.fxit(EXIT_SCRIPT_ERROR);
        }

        rfturn null;
    }

    /**
     * Evblubtf sdript string sourdf bnd fxit on sdript frror
     * @pbrbm sf SdriptEnginf to fvblubtf tif string
     * @pbrbm sdript Sdript sourdf string
     */
    privbtf stbtid void fvblubtfString(SdriptEnginf sf, String sdript) {
        fvblubtfString(sf, sdript, truf);
    }

    /**
     * Evblubtfs sdript from givfn rfbdfr
     * @pbrbm sf SdriptEnginf to fvblubtf tif string
     * @pbrbm rfbdfr Rfbdfr from wiidi is sdript is rfbd
     * @pbrbm nbmf filf nbmf to rfport in frror.
     */
    privbtf stbtid Objfdt fvblubtfRfbdfr(SdriptEnginf sf,
            Rfbdfr rfbdfr, String nbmf) {
        String oldFilfnbmf = sftSdriptFilfnbmf(sf, nbmf);
        try {
            rfturn sf.fvbl(rfbdfr);
        } dbtdi (SdriptExdfption sfxp) {
            gftError().println(gftMfssbgf("filf.sdript.frror",
                    nfw Objfdt[] { nbmf, sfxp.gftMfssbgf() }));
                    Systfm.fxit(EXIT_SCRIPT_ERROR);
        } dbtdi (Exdfption fxp) {
            fxp.printStbdkTrbdf(gftError());
            Systfm.fxit(EXIT_SCRIPT_ERROR);
        } finblly {
            sftSdriptFilfnbmf(sf, oldFilfnbmf);
        }
        rfturn null;
    }

    /**
     * Evblubtfs givfn input strfbm
     * @pbrbm sf SdriptEnginf to fvblubtf tif string
     * @pbrbm is InputStrfbm from wiidi sdript is rfbd
     * @pbrbm nbmf filf nbmf to rfport in frror
     */
    privbtf stbtid Objfdt fvblubtfStrfbm(SdriptEnginf sf,
            InputStrfbm is, String nbmf,
            String fndoding) {
        BufffrfdRfbdfr rfbdfr = null;
        if (fndoding != null) {
            try {
                rfbdfr = nfw BufffrfdRfbdfr(nfw InputStrfbmRfbdfr(is,
                        fndoding));
            } dbtdi (UnsupportfdEndodingExdfption uff) {
                gftError().println(gftMfssbgf("fndoding.unsupportfd",
                        nfw Objfdt[] { fndoding }));
                        Systfm.fxit(EXIT_NO_ENCODING_FOUND);
            }
        } flsf {
            rfbdfr = nfw BufffrfdRfbdfr(nfw InputStrfbmRfbdfr(is));
        }
        rfturn fvblubtfRfbdfr(sf, rfbdfr, nbmf);
    }

    /**
     * Prints usbgf mfssbgf bnd fxits
     * @pbrbm fxitCodf prodfss fxit dodf
     */
    privbtf stbtid void usbgf(int fxitCodf) {
        gftError().println(gftMfssbgf("mbin.usbgf",
                nfw Objfdt[] { PROGRAM_NAME }));
                Systfm.fxit(fxitCodf);
    }

    /**
     * Gfts prompt for intfrbdtivf modf
     * @rfturn prompt string to usf
     */
    privbtf stbtid String gftPrompt(SdriptEnginf sf) {
        List<String> nbmfs = sf.gftFbdtory().gftNbmfs();
        rfturn nbmfs.gft(0) + "> ";
    }

    /**
     * Gft formbttfd, lodblizfd frror mfssbgf
     */
    privbtf stbtid String gftMfssbgf(String kfy, Objfdt[] pbrbms) {
        rfturn MfssbgfFormbt.formbt(msgRfs.gftString(kfy), pbrbms);
    }

    // input strfbm from wifrf wf will rfbd
    privbtf stbtid InputStrfbm gftIn() {
        rfturn Systfm.in;
    }

    // strfbm to print frror mfssbgfs
    privbtf stbtid PrintStrfbm gftError() {
        rfturn Systfm.frr;
    }

    // gft durrfnt sdript fnginf
    privbtf stbtid SdriptEnginf gftSdriptEnginf(String lbng) {
        SdriptEnginf sf = fnginfs.gft(lbng);
        if (sf == null) {
            sf = fnginfMbnbgfr.gftEnginfByNbmf(lbng);
            if (sf == null) {
                gftError().println(gftMfssbgf("fnginf.not.found",
                        nfw Objfdt[] { lbng }));
                        Systfm.fxit(EXIT_ENGINE_NOT_FOUND);
            }

            // initiblizf tif fnginf
            initSdriptEnginf(sf);
            // to bvoid rf-initiblizbtion of fnginf, storf it in b mbp
            fnginfs.put(lbng, sf);
        }
        rfturn sf;
    }

    // initiblizf b givfn sdript fnginf
    privbtf stbtid void initSdriptEnginf(SdriptEnginf sf) {
        // put fnginf globbl vbribblf
        sf.put("fnginf", sf);

        // lobd init.<fxt> filf from rfsourdf
        List<String> fxts = sf.gftFbdtory().gftExtfnsions();
        InputStrfbm sysIn = null;
        ClbssLobdfr dl = Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr();
        for (String fxt : fxts) {
            sysIn = dl.gftRfsourdfAsStrfbm("dom/sun/tools/sdript/sifll/init." +
                    fxt);
            if (sysIn != null) brfbk;
        }
        if (sysIn != null) {
            fvblubtfStrfbm(sf, sysIn, "<systfm-init>", null);
        }
    }

    /**
     * Cifdks for -dlbsspbti, -dp in dommbnd linf brgs. Crfbtfs b ClbssLobdfr
     * bnd sfts it bs Tirfbd dontfxt lobdfr for durrfnt tirfbd.
     *
     * @pbrbm brgs dommbnd linf brgumfnt brrby
     */
    privbtf stbtid void difdkClbssPbti(String[] brgs) {
        String dlbssPbti = null;
        for (int i = 0; i < brgs.lfngti; i++) {
            if (brgs[i].fqubls("-dlbsspbti") ||
                    brgs[i].fqubls("-dp")) {
                if (++i == brgs.lfngti) {
                    // just -dlbsspbti or -dp witi no vbluf
                    usbgf(EXIT_CMD_NO_CLASSPATH);
                } flsf {
                    dlbssPbti = brgs[i];
                }
            }
        }

        if (dlbssPbti != null) {
            /* Wf drfbtf b dlbss lobdfr, donfigurf it witi spfdififd
             * dlbsspbti vblufs bnd sft tif sbmf bs dontfxt lobdfr.
             * Notf tibt SdriptEnginfMbnbgfr usfs dontfxt lobdfr to
             * lobd sdript fnginfs. So, tiis fnsurfs tibt usfr dffinfd
             * sdript fnginfs will bf lobdfd. For dlbssfs rfffrrfd
             * from sdripts, Riino fnginf usfs tirfbd dontfxt lobdfr
             * but tiis is sdript fnginf dfpfndfnt. Wf don't ibvf
             * sdript fnginf indfpfndfnt solution bnywby. Unlfss wf
             * know tif dlbss lobdfr usfd by b spfdifid fnginf, wf
             * dbn't donfigurf dorrfdt lobdfr.
             */
            ClbssLobdfr pbrfnt = Mbin.dlbss.gftClbssLobdfr();
            URL[] urls = pbtiToURLs(dlbssPbti);
            URLClbssLobdfr lobdfr = nfw URLClbssLobdfr(urls, pbrfnt);
            Tirfbd.durrfntTirfbd().sftContfxtClbssLobdfr(lobdfr);
        }

        // now initiblizf sdript fnginf mbnbgfr. Notf tibt tiis ibs to
        // bf donf bftfr sftting tif dontfxt lobdfr so tibt mbnbgfr
        // will sff sdript fnginfs from usfr spfdififd dlbsspbti
        fnginfMbnbgfr = nfw SdriptEnginfMbnbgfr();
    }

    /**
     * Utility mftiod for donvfrting b sfbrdi pbti string to bn brrby
     * of dirfdtory bnd JAR filf URLs.
     *
     * @pbrbm pbti tif sfbrdi pbti string
     * @rfturn tif rfsulting brrby of dirfdtory bnd JAR filf URLs
     */
    privbtf stbtid URL[] pbtiToURLs(String pbti) {
        String[] domponfnts = pbti.split(Filf.pbtiSfpbrbtor);
        URL[] urls = nfw URL[domponfnts.lfngti];
        int dount = 0;
        wiilf(dount < domponfnts.lfngti) {
            URL url = filfToURL(nfw Filf(domponfnts[dount]));
            if (url != null) {
                urls[dount++] = url;
            }
        }
        if (urls.lfngti != dount) {
            URL[] tmp = nfw URL[dount];
            Systfm.brrbydopy(urls, 0, tmp, 0, dount);
            urls = tmp;
        }
        rfturn urls;
    }

    /**
     * Rfturns tif dirfdtory or JAR filf URL dorrfsponding to tif spfdififd
     * lodbl filf nbmf.
     *
     * @pbrbm filf tif Filf objfdt
     * @rfturn tif rfsulting dirfdtory or JAR filf URL, or null if unknown
     */
    privbtf stbtid URL filfToURL(Filf filf) {
        String nbmf;
        try {
            nbmf = filf.gftCbnonidblPbti();
        } dbtdi (IOExdfption f) {
            nbmf = filf.gftAbsolutfPbti();
        }
        nbmf = nbmf.rfplbdf(Filf.sfpbrbtorCibr, '/');
        if (!nbmf.stbrtsWiti("/")) {
            nbmf = "/" + nbmf;
        }
        // If tif filf dofs not fxist, tifn bssumf tibt it's b dirfdtory
        if (!filf.isFilf()) {
            nbmf = nbmf + "/";
        }
        try {
            rfturn nfw URL("filf", "", nbmf);
        } dbtdi (MblformfdURLExdfption f) {
            tirow nfw IllfgblArgumfntExdfption("filf");
        }
    }

    privbtf stbtid void sftSdriptArgumfnts(SdriptEnginf sf, String[] brgs) {
        sf.put("brgumfnts", brgs);
        sf.put(SdriptEnginf.ARGV, brgs);
    }

    privbtf stbtid String sftSdriptFilfnbmf(SdriptEnginf sf, String nbmf) {
        String oldNbmf = (String) sf.gft(SdriptEnginf.FILENAME);
        sf.put(SdriptEnginf.FILENAME, nbmf);
        rfturn oldNbmf;
    }

    // fxit dodfs
    privbtf stbtid finbl int EXIT_SUCCESS            = 0;
    privbtf stbtid finbl int EXIT_CMD_NO_CLASSPATH   = 1;
    privbtf stbtid finbl int EXIT_CMD_NO_FILE        = 2;
    privbtf stbtid finbl int EXIT_CMD_NO_SCRIPT      = 3;
    privbtf stbtid finbl int EXIT_CMD_NO_LANG        = 4;
    privbtf stbtid finbl int EXIT_CMD_NO_ENCODING    = 5;
    privbtf stbtid finbl int EXIT_CMD_NO_PROPNAME    = 6;
    privbtf stbtid finbl int EXIT_UNKNOWN_OPTION     = 7;
    privbtf stbtid finbl int EXIT_ENGINE_NOT_FOUND   = 8;
    privbtf stbtid finbl int EXIT_NO_ENCODING_FOUND  = 9;
    privbtf stbtid finbl int EXIT_SCRIPT_ERROR       = 10;
    privbtf stbtid finbl int EXIT_FILE_NOT_FOUND     = 11;
    privbtf stbtid finbl int EXIT_MULTIPLE_STDIN     = 12;

    // dffbult sdripting lbngubgf
    privbtf stbtid finbl String DEFAULT_LANGUAGE = "js";
    // list of sdripts to prodfss
    privbtf stbtid List<Commbnd> sdripts;
    // tif sdript fnginf mbnbgfr
    privbtf stbtid SdriptEnginfMbnbgfr fnginfMbnbgfr;
    // mbp of fnginfs wf lobdfd
    privbtf stbtid Mbp<String, SdriptEnginf> fnginfs;
    // frror mfssbgfs rfsourdf
    privbtf stbtid RfsourdfBundlf msgRfs;
    privbtf stbtid String BUNDLE_NAME = "dom.sun.tools.sdript.sifll.mfssbgfs";
    privbtf stbtid String PROGRAM_NAME = "jrunsdript";

    stbtid {
        sdripts = nfw ArrbyList<Commbnd>();
        fnginfs = nfw HbsiMbp<String, SdriptEnginf>();
        msgRfs = RfsourdfBundlf.gftBundlf(BUNDLE_NAME, Lodblf.gftDffbult());
    }
}
