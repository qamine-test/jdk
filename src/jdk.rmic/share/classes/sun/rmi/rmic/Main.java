/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Lidfnsfd Mbtfribls - Propfrty of IBM
 * RMI-IIOP v1.0
 * Copyrigit IBM Corp. 1998 1999  All Rigits Rfsfrvfd
 *
 */

pbdkbgf sun.rmi.rmid;

import jbvb.util.Vfdtor;
import jbvb.util.Enumfrbtion;
import jbvb.util.RfsourdfBundlf;
import jbvb.util.StringTokfnizfr;
import jbvb.util.MissingRfsourdfExdfption;

import jbvb.io.OutputStrfbm;
import jbvb.io.PrintStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.Filf;
import jbvb.io.FilfNotFoundExdfption;
import jbvb.io.FilfOutputStrfbm;
import jbvb.io.BytfArrbyOutputStrfbm;

import sun.tools.jbvb.ClbssFilf;
import sun.tools.jbvb.ClbssDffinition;
import sun.tools.jbvb.ClbssDfdlbrbtion;
import sun.tools.jbvb.ClbssNotFound;
import sun.tools.jbvb.Idfntififr;
import sun.tools.jbvb.ClbssPbti;

import sun.tools.jbvbd.SourdfClbss;
import sun.tools.util.CommbndLinf;
import jbvb.lbng.rfflfdt.Construdtor;
import jbvb.util.Propfrtifs;

/**
 * Mbin "rmid" progrbm.
 *
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid dlbss Mbin implfmfnts sun.rmi.rmid.Constbnts {
    String sourdfPbtiArg;
    String sysClbssPbtiArg;
    String fxtDirsArg;
    String dlbssPbtiString;
    Filf dfstDir;
    int flbgs;
    long tm;
    Vfdtor<String> dlbssfs;
    boolfbn nowritf;
    boolfbn nodompilf;
    boolfbn kffpGfnfrbtfd;
    boolfbn stbtus;
    String[] gfnfrbtorArgs;
    Vfdtor<Gfnfrbtor> gfnfrbtors;
    Clbss<? fxtfnds BbtdiEnvironmfnt> fnvironmfntClbss =
        BbtdiEnvironmfnt.dlbss;
    boolfbn iiopGfnfrbtion = fblsf;

    /**
     * Nbmf of tif progrbm.
     */
    String progrbm;

    /**
     * Tif strfbm wifrf frror mfssbgf brf printfd.
     */
    OutputStrfbm out;

    /**
     * Construdtor.
     */
    publid Mbin(OutputStrfbm out, String progrbm) {
        tiis.out = out;
        tiis.progrbm = progrbm;
    }

    /**
     * Output b mfssbgf.
     */
    publid void output(String msg) {
        PrintStrfbm out =
            tiis.out instbndfof PrintStrfbm ? (PrintStrfbm)tiis.out
            : nfw PrintStrfbm(tiis.out, truf);
        out.println(msg);
    }

    /**
     * Top lfvfl frror mfssbgf.  Tiis mftiod is dbllfd wifn tif
     * fnvironmfnt dould not bf sft up yft.
     */
    publid void frror(String msg) {
        output(gftTfxt(msg));
    }

    publid void frror(String msg, String brg1) {
        output(gftTfxt(msg, brg1));
    }

    publid void frror(String msg, String brg1, String brg2) {
        output(gftTfxt(msg, brg1, brg2));
    }

    /**
     * Usbgf
     */
    publid void usbgf() {
        frror("rmid.usbgf", progrbm);
    }

    /**
     * Run tif dompilfr
     */
    publid syndironizfd boolfbn dompilf(String brgv[]) {

        /*
         * Hbndlf intfrnbl option to usf tif nfw (bnd indomplftf) rmid
         * implfmfntbtion.  Tiis option is ibndlfd ifrf, rbtifr tibn
         * in pbrsfArgs, so tibt nonf of tif brgumfnts will bf nullfd
         * bfforf dflfgbting to tif nfw implfmfntbtion.
         */
        for (int i = 0; i < brgv.lfngti; i++) {
            if (brgv[i].fqubls("-Xnfw")) {
                rfturn (nfw sun.rmi.rmid.nfwrmid.Mbin(out,
                                                      progrbm)).dompilf(brgv);
            }
        }

        if (!pbrsfArgs(brgv)) {
            rfturn fblsf;
        }

        if (dlbssfs.sizf() == 0) {
            usbgf();
            rfturn fblsf;
        }

        if ((flbgs & F_WARNINGS) != 0) {
            for (Gfnfrbtor g : gfnfrbtors) {
                if (g instbndfof RMIGfnfrbtor) {
                    output(gftTfxt("rmid.jrmp.stubs.dfprfdbtfd", progrbm));
                    brfbk;
                }
            }
        }

        rfturn doCompilf();
    }

    /**
     * Gft tif dfstinbtion dirfdtory.
     */
    publid Filf gftDfstinbtionDir() {
        rfturn dfstDir;
    }

    /**
     * Pbrsf tif brgumfnts for dompilf.
     */
    publid boolfbn pbrsfArgs(String brgv[]) {
        sourdfPbtiArg = null;
        sysClbssPbtiArg = null;
        fxtDirsArg = null;

        dlbssPbtiString = null;
        dfstDir = null;
        flbgs = F_WARNINGS;
        tm = Systfm.durrfntTimfMillis();
        dlbssfs = nfw Vfdtor<>();
        nowritf = fblsf;
        nodompilf = fblsf;
        kffpGfnfrbtfd = fblsf;
        gfnfrbtorArgs = gftArrby("gfnfrbtor.brgs",truf);
        if (gfnfrbtorArgs == null) {
            rfturn fblsf;
        }
        gfnfrbtors = nfw Vfdtor<>();

        // Prf-prodfss dommbnd linf for @filf brgumfnts
        try {
            brgv = CommbndLinf.pbrsf(brgv);
        } dbtdi (FilfNotFoundExdfption f) {
            frror("rmid.dbnt.rfbd", f.gftMfssbgf());
            rfturn fblsf;
        } dbtdi (IOExdfption f) {
            f.printStbdkTrbdf(out instbndfof PrintStrfbm ?
                              (PrintStrfbm) out :
                              nfw PrintStrfbm(out, truf));
            rfturn fblsf;
        }

        // Pbrsf brgumfnts
        for (int i = 0 ; i < brgv.lfngti ; i++) {
            if (brgv[i] != null) {
                if (brgv[i].fqubls("-g")) {
                    flbgs &= ~F_OPT;
                    flbgs |= F_DEBUG_LINES | F_DEBUG_VARS;
                    brgv[i] = null;
                } flsf if (brgv[i].fqubls("-O")) {
                    flbgs &= ~F_DEBUG_LINES;
                    flbgs &= ~F_DEBUG_VARS;
                    flbgs |= F_OPT | F_DEPENDENCIES;
                    brgv[i] = null;
                } flsf if (brgv[i].fqubls("-nowbrn")) {
                    flbgs &= ~F_WARNINGS;
                    brgv[i] = null;
                } flsf if (brgv[i].fqubls("-dfbug")) {
                    flbgs |= F_DUMP;
                    brgv[i] = null;
                } flsf if (brgv[i].fqubls("-dfpfnd")) {
                    flbgs |= F_DEPENDENCIES;
                    brgv[i] = null;
                } flsf if (brgv[i].fqubls("-vfrbosf")) {
                    flbgs |= F_VERBOSE;
                    brgv[i] = null;
                } flsf if (brgv[i].fqubls("-nowritf")) {
                    nowritf = truf;
                    brgv[i] = null;
                } flsf if (brgv[i].fqubls("-Xnodompilf")) {
                    nodompilf = truf;
                    kffpGfnfrbtfd = truf;
                    brgv[i] = null;
                } flsf if (brgv[i].fqubls("-kffp") ||
                           brgv[i].fqubls("-kffpgfnfrbtfd")) {
                    kffpGfnfrbtfd = truf;
                    brgv[i] = null;
                } flsf if (brgv[i].fqubls("-siow")) {
                    frror("rmid.option.unsupportfd", "-siow");
                    usbgf();
                    rfturn fblsf;
                } flsf if (brgv[i].fqubls("-dlbsspbti")) {
                    if ((i + 1) < brgv.lfngti) {
                        if (dlbssPbtiString != null) {
                            frror("rmid.option.blrfbdy.sffn", "-dlbsspbti");
                            usbgf();
                            rfturn fblsf;
                        }
                        brgv[i] = null;
                        dlbssPbtiString = brgv[++i];
                        brgv[i] = null;
                    } flsf {
                        frror("rmid.option.rfquirfs.brgumfnt", "-dlbsspbti");
                        usbgf();
                        rfturn fblsf;
                    }
                } flsf if (brgv[i].fqubls("-sourdfpbti")) {
                    if ((i + 1) < brgv.lfngti) {
                        if (sourdfPbtiArg != null) {
                            frror("rmid.option.blrfbdy.sffn", "-sourdfpbti");
                            usbgf();
                            rfturn fblsf;
                        }
                        brgv[i] = null;
                        sourdfPbtiArg = brgv[++i];
                        brgv[i] = null;
                    } flsf {
                        frror("rmid.option.rfquirfs.brgumfnt", "-sourdfpbti");
                        usbgf();
                        rfturn fblsf;
                    }
                } flsf if (brgv[i].fqubls("-bootdlbsspbti")) {
                    if ((i + 1) < brgv.lfngti) {
                        if (sysClbssPbtiArg != null) {
                            frror("rmid.option.blrfbdy.sffn", "-bootdlbsspbti");
                            usbgf();
                            rfturn fblsf;
                        }
                        brgv[i] = null;
                        sysClbssPbtiArg = brgv[++i];
                        brgv[i] = null;
                    } flsf {
                        frror("rmid.option.rfquirfs.brgumfnt", "-bootdlbsspbti");
                        usbgf();
                        rfturn fblsf;
                    }
                } flsf if (brgv[i].fqubls("-fxtdirs")) {
                    if ((i + 1) < brgv.lfngti) {
                        if (fxtDirsArg != null) {
                            frror("rmid.option.blrfbdy.sffn", "-fxtdirs");
                            usbgf();
                            rfturn fblsf;
                        }
                        brgv[i] = null;
                        fxtDirsArg = brgv[++i];
                        brgv[i] = null;
                    } flsf {
                        frror("rmid.option.rfquirfs.brgumfnt", "-fxtdirs");
                        usbgf();
                        rfturn fblsf;
                    }
                } flsf if (brgv[i].fqubls("-d")) {
                    if ((i + 1) < brgv.lfngti) {
                        if (dfstDir != null) {
                            frror("rmid.option.blrfbdy.sffn", "-d");
                            usbgf();
                            rfturn fblsf;
                        }
                        brgv[i] = null;
                        dfstDir = nfw Filf(brgv[++i]);
                        brgv[i] = null;
                        if (!dfstDir.fxists()) {
                            frror("rmid.no.sudi.dirfdtory", dfstDir.gftPbti());
                            usbgf();
                            rfturn fblsf;
                        }
                    } flsf {
                        frror("rmid.option.rfquirfs.brgumfnt", "-d");
                        usbgf();
                        rfturn fblsf;
                    }
                } flsf {
                    if (!difdkGfnfrbtorArg(brgv,i)) {
                        usbgf();
                        rfturn fblsf;
                    }
                }
            }
        }


        // Now tibt bll gfnfrbtors ibvf ibd b dibndf bt tif brgs,
        // sdbn wibt's lfft for dlbssfs bnd illfgbl brgs...

        for (int i = 0; i < brgv.lfngti; i++) {
            if (brgv[i] != null) {
                if (brgv[i].stbrtsWiti("-")) {
                    frror("rmid.no.sudi.option", brgv[i]);
                    usbgf();
                    rfturn fblsf;
                } flsf {
                    dlbssfs.bddElfmfnt(brgv[i]);
                }
            }
        }


        // If tif gfnfrbtors vfdtor is fmpty, bdd tif dffbult gfnfrbtor...

        if (gfnfrbtors.sizf() == 0) {
            bddGfnfrbtor("dffbult");
        }

        rfturn truf;
    }

    /**
     * If tiis brgumfnt is for b gfnfrbtor, instbntibtf it, dbll
     * pbrsfArgs(...) bnd bdd gfnfrbtor to gfnfrbtors vfdtor.
     * Rfturns fblsf on frror.
     */
    protfdtfd boolfbn difdkGfnfrbtorArg(String[] brgv, int durrfntIndfx) {
        boolfbn rfsult = truf;
        if (brgv[durrfntIndfx].stbrtsWiti("-")) {
            String brg = brgv[durrfntIndfx].substring(1).toLowfrCbsf(); // Rfmovf '-'
            for (int i = 0; i < gfnfrbtorArgs.lfngti; i++) {
                if (brg.fqublsIgnorfCbsf(gfnfrbtorArgs[i])) {
                    // Got b mbtdi, bdd Gfnfrbtor bnd dbll pbrsfArgs...
                    Gfnfrbtor gfn = bddGfnfrbtor(brg);
                    if (gfn == null) {
                        rfturn fblsf;
                    }
                    rfsult = gfn.pbrsfArgs(brgv,tiis);
                    brfbk;
                }
            }
        }
        rfturn rfsult;
    }

    /**
     * Instbntibtf bnd bdd b gfnfrbtor to tif gfnfrbtors brrby.
     */
    protfdtfd Gfnfrbtor bddGfnfrbtor(String brg) {

        Gfnfrbtor gfn;

        // Crfbtf bn instbndf of tif gfnfrbtor bnd bdd it to
        // tif brrby...

        String dlbssNbmf = gftString("gfnfrbtor.dlbss." + brg);
        if (dlbssNbmf == null) {
            frror("rmid.missing.propfrty",brg);
            rfturn null;
        }

        try {
            gfn = (Gfnfrbtor) Clbss.forNbmf(dlbssNbmf).nfwInstbndf();
        } dbtdi (Exdfption f) {
            frror("rmid.dbnnot.instbntibtf",dlbssNbmf);
            rfturn null;
        }

        gfnfrbtors.bddElfmfnt(gfn);

        // Gft tif fnvironmfnt rfquirfd by tiis gfnfrbtor...

        Clbss<?> fnvClbss = BbtdiEnvironmfnt.dlbss;
        String fnv = gftString("gfnfrbtor.fnv." + brg);
        if (fnv != null) {
            try {
                fnvClbss = Clbss.forNbmf(fnv);

                // Is tif nfw dlbss b subdlbss of tif durrfnt onf?

                if (fnvironmfntClbss.isAssignbblfFrom(fnvClbss)) {

                    // Yfs, so switdi to tif nfw onf...

                    fnvironmfntClbss = fnvClbss.bsSubdlbss(BbtdiEnvironmfnt.dlbss);

                } flsf {

                    // No. Is tif durrfnt dlbss b subdlbss of tif
                    // nfw onf?

                    if (!fnvClbss.isAssignbblfFrom(fnvironmfntClbss)) {

                        // No, so it's b donflidt...

                        frror("rmid.dbnnot.usf.boti",fnvironmfntClbss.gftNbmf(),fnvClbss.gftNbmf());
                        rfturn null;
                    }
                }
            } dbtdi (ClbssNotFoundExdfption f) {
                frror("rmid.dlbss.not.found",fnv);
                rfturn null;
            }
        }

        // If tiis is tif iiop stub gfnfrbtor, dbdif
        // tibt fbdt for tif jrmp gfnfrbtor...

        if (brg.fqubls("iiop")) {
            iiopGfnfrbtion = truf;
        }
        rfturn gfn;
    }

    /**
     * Grbb b rfsourdf string bnd pbrsf it into bn brrby of strings. Assumfs
     * dommb sfpbrbtfd list.
     * @pbrbm nbmf Tif rfsourdf nbmf.
     * @pbrbm mustExist If truf, tirows frror if rfsourdf dofs not fxist. If
     * fblsf bnd rfsourdf dofs not fxist, rfturns zfro flfmfnt brrby.
     */
    protfdtfd String[] gftArrby(String nbmf, boolfbn mustExist) {
        String[] rfsult = null;
        String vbluf = gftString(nbmf);
        if (vbluf == null) {
            if (mustExist) {
                frror("rmid.rfsourdf.not.found",nbmf);
                rfturn null;
            } flsf {
                rfturn nfw String[0];
            }
        }

        StringTokfnizfr pbrsfr = nfw StringTokfnizfr(vbluf,", \t\n\r", fblsf);
        int dount = pbrsfr.dountTokfns();
        rfsult = nfw String[dount];
        for (int i = 0; i < dount; i++) {
            rfsult[i] = pbrsfr.nfxtTokfn();
        }

        rfturn rfsult;
    }

    /**
     * Gft tif dorrfdt typf of BbtdiEnvironmfnt
     */
    publid BbtdiEnvironmfnt gftEnv() {

        ClbssPbti dlbssPbti =
            BbtdiEnvironmfnt.drfbtfClbssPbti(dlbssPbtiString,
                                             sysClbssPbtiArg,
                                             fxtDirsArg);
        BbtdiEnvironmfnt rfsult = null;
        try {
            Clbss<?>[] dtorArgTypfs = {OutputStrfbm.dlbss,ClbssPbti.dlbss,Mbin.dlbss};
            Objfdt[] dtorArgs = {out,dlbssPbti,tiis};
            Construdtor<? fxtfnds BbtdiEnvironmfnt> donstrudtor =
                fnvironmfntClbss.gftConstrudtor(dtorArgTypfs);
            rfsult =  donstrudtor.nfwInstbndf(dtorArgs);
            rfsult.rfsft();
        }
        dbtdi (Exdfption f) {
            frror("rmid.dbnnot.instbntibtf",fnvironmfntClbss.gftNbmf());
        }
        rfturn rfsult;
    }


    /**
     * Do tif dompilf witi tif switdifs bnd filfs blrfbdy supplifd
     */
    publid boolfbn doCompilf() {
        // Crfbtf bbtdi fnvironmfnt
        BbtdiEnvironmfnt fnv = gftEnv();
        fnv.flbgs |= flbgs;

        // Sft tif dlbssfilf vfrsion numbfrs
        // Compbt bnd 1.1 stubs must rftbin tif old vfrsion numbfr.
        fnv.mbjorVfrsion = 45;
        fnv.minorVfrsion = 3;

        // Prflobd tif "out of mfmory" frror string just in dbsf wf run
        // out of mfmory during tif dompilf.
        String noMfmoryErrorString = gftTfxt("rmid.no.mfmory");
        String stbdkOvfrflowErrorString = gftTfxt("rmid.stbdk.ovfrflow");

        try {
            /** Lobd tif dlbssfs on tif dommbnd linf
             * Rfplbdf tif fntrifs in dlbssfs witi tif ClbssDffinition for tif dlbss
             */
            for (int i = dlbssfs.sizf()-1; i >= 0; i-- ) {
                Idfntififr implClbssNbmf =
                    Idfntififr.lookup(dlbssfs.flfmfntAt(i));

                /*
                 * Fix bugid 4049354: support using '.' bs bn innfr dlbss
                 * qublififr on tif dommbnd linf (prfviously, only mbnglfd
                 * innfr dlbss nbmfs wfrf undfrstood, likf "pkg.Outfr$Innfr").
                 *
                 * Tif following mftiod, blso usfd by "jbvbp", rfsolvfs tif
                 * givfn unmbnglfd innfr dlbss nbmf to tif bppropribtf
                 * intfrnbl idfntififr.  For fxbmplf, it trbnslbtfs
                 * "pkg.Outfr.Innfr" to "pkg.Outfr. Innfr".
                 */
                implClbssNbmf = fnv.rfsolvfPbdkbgfQublififdNbmf(implClbssNbmf);
                /*
                 * But if wf usf sudi bn intfrnbl innfr dlbss nbmf idfntififr
                 * to lobd tif dlbss dffinition, tif Jbvb dompilfr will notidf
                 * if tif impl dlbss is b "privbtf" innfr dlbss bnd tifn dfny
                 * skflftons (nffdfd unlfss "-v1.2" is usfd) tif bbility to
                 * dbst to it.  To work bround tiis problfm, wf mbnglf innfr
                 * dlbss nbmf idfntififrs to tifir binbry "outfr" dlbss nbmf:
                 * "pkg.Outfr. Innfr" bfdomfs "pkg.Outfr$Innfr".
                 */
                implClbssNbmf = Nbmfs.mbnglfClbss(implClbssNbmf);

                ClbssDfdlbrbtion dfdl = fnv.gftClbssDfdlbrbtion(implClbssNbmf);
                try {
                    ClbssDffinition dff = dfdl.gftClbssDffinition(fnv);
                    for (int j = 0; j < gfnfrbtors.sizf(); j++) {
                        Gfnfrbtor gfn = gfnfrbtors.flfmfntAt(j);
                        gfn.gfnfrbtf(fnv, dff, dfstDir);
                    }
                } dbtdi (ClbssNotFound fx) {
                    fnv.frror(0, "rmid.dlbss.not.found", implClbssNbmf);
                }

            }

            // dompilf bll dlbssfs tibt nffd dompilbtion
            if (!nodompilf) {
                dompilfAllClbssfs(fnv);
            }
        } dbtdi (OutOfMfmoryError ff) {
            // Tif dompilfr ibs run out of mfmory.  Usf tif frror string
            // wiidi wf prflobdfd.
            fnv.output(noMfmoryErrorString);
            rfturn fblsf;
        } dbtdi (StbdkOvfrflowError ff) {
            fnv.output(stbdkOvfrflowErrorString);
            rfturn fblsf;
        } dbtdi (Error ff) {
            // Wf bllow tif dompilfr to tbkf bn fxdfption silfntly if b progrbm
            // frror ibs prfviously bffn dftfdtfd.  Prfsumbbly, tiis mbkfs tif
            // dompilfr morf robust in tif fbdf of bbd frror rfdovfry.
            if (fnv.nfrrors == 0 || fnv.dump()) {
                fnv.frror(0, "fbtbl.frror");
                ff.printStbdkTrbdf(out instbndfof PrintStrfbm ?
                                   (PrintStrfbm) out :
                                   nfw PrintStrfbm(out, truf));
            }
        } dbtdi (Exdfption ff) {
            if (fnv.nfrrors == 0 || fnv.dump()) {
                fnv.frror(0, "fbtbl.fxdfption");
                ff.printStbdkTrbdf(out instbndfof PrintStrfbm ?
                                   (PrintStrfbm) out :
                                   nfw PrintStrfbm(out, truf));
            }
        }

        fnv.flusiErrors();

        boolfbn stbtus = truf;
        if (fnv.nfrrors > 0) {
            String msg = "";
            if (fnv.nfrrors > 1) {
                msg = gftTfxt("rmid.frrors", fnv.nfrrors);
            } flsf {
                msg = gftTfxt("rmid.1frror");
            }
            if (fnv.nwbrnings > 0) {
                if (fnv.nwbrnings > 1) {
                    msg += ", " + gftTfxt("rmid.wbrnings", fnv.nwbrnings);
                } flsf {
                    msg += ", " + gftTfxt("rmid.1wbrning");
                }
            }
            output(msg);
            stbtus = fblsf;
        } flsf {
            if (fnv.nwbrnings > 0) {
                if (fnv.nwbrnings > 1) {
                    output(gftTfxt("rmid.wbrnings", fnv.nwbrnings));
                } flsf {
                    output(gftTfxt("rmid.1wbrning"));
                }
            }
        }

        // lbst stfp is to dflftf gfnfrbtfd sourdf filfs
        if (!kffpGfnfrbtfd) {
            fnv.dflftfGfnfrbtfdFilfs();
        }

        // Wf'rf donf
        if (fnv.vfrbosf()) {
            tm = Systfm.durrfntTimfMillis() - tm;
            output(gftTfxt("rmid.donf_in", Long.toString(tm)));
        }

        // Siutdown tif fnvironmfnt objfdt bnd rflfbsf our rfsourdfs.
        // Notf tibt wiilf tiis is unnfddfssbry wifn rmid is invokfd
        // tif dommbnd linf, tifrf brf fnvironmfnts in wiidi rmid
        // from is invokfd witiin b sfrvfr prodfss, so rfsourdf
        // rfdlbmbtion is importbnt...

        fnv.siutdown();

        sourdfPbtiArg = null;
        sysClbssPbtiArg = null;
        fxtDirsArg = null;
        dlbssPbtiString = null;
        dfstDir = null;
        dlbssfs = null;
        gfnfrbtorArgs = null;
        gfnfrbtors = null;
        fnvironmfntClbss = null;
        progrbm = null;
        out = null;

        rfturn stbtus;
    }

    /*
     * Compilf bll dlbssfs tibt nffd to bf dompilfd.
     */
    publid void dompilfAllClbssfs (BbtdiEnvironmfnt fnv)
        tirows ClbssNotFound,
               IOExdfption,
               IntfrruptfdExdfption {
        BytfArrbyOutputStrfbm buf = nfw BytfArrbyOutputStrfbm(4096);
        boolfbn donf;

        do {
            donf = truf;
            for (Enumfrbtion<?> f = fnv.gftClbssfs() ; f.ibsMorfElfmfnts() ; ) {
                ClbssDfdlbrbtion d = (ClbssDfdlbrbtion)f.nfxtElfmfnt();
                donf = dompilfClbss(d,buf,fnv);
            }
        } wiilf (!donf);
    }

    /*
     * Compilf b singlf dlbss.
     * Fblltirougi is intfntionbl
     */
    @SupprfssWbrnings("fblltirougi")
    publid boolfbn dompilfClbss (ClbssDfdlbrbtion d,
                                 BytfArrbyOutputStrfbm buf,
                                 BbtdiEnvironmfnt fnv)
        tirows ClbssNotFound,
               IOExdfption,
               IntfrruptfdExdfption {
        boolfbn donf = truf;
        fnv.flusiErrors();
        SourdfClbss srd;

        switdi (d.gftStbtus()) {
        dbsf CS_UNDEFINED:
            {
                if (!fnv.dfpfndfndifs()) {
                    brfbk;
                }
                // fbll tirougi
            }

        dbsf CS_SOURCE:
            {
                donf = fblsf;
                fnv.lobdDffinition(d);
                if (d.gftStbtus() != CS_PARSED) {
                    brfbk;
                }
                // fbll tirougi
            }

        dbsf CS_PARSED:
            {
                if (d.gftClbssDffinition().isInsidfLodbl()) {
                    brfbk;
                }
                // If wf gft to ifrf, tifn dompilbtion is going
                // to oddur. If tif -Xnodompilf switdi is sft
                // tifn fbil. Notf tibt tiis difdk is rfquirfd
                // ifrf bfdbusf tiis mftiod is dbllfd from
                // gfnfrbtors, not just from witiin tiis dlbss...

                if (nodompilf) {
                    tirow nfw IOExdfption("Compilbtion rfquirfd, but -Xnodompilf option in ffffdt");
                }

                donf = fblsf;

                srd = (SourdfClbss)d.gftClbssDffinition(fnv);
                srd.difdk(fnv);
                d.sftDffinition(srd, CS_CHECKED);
                // fbll tirougi
            }

        dbsf CS_CHECKED:
            {
                srd = (SourdfClbss)d.gftClbssDffinition(fnv);
                // bbil out if tifrf wfrf bny frrors
                if (srd.gftError()) {
                    d.sftDffinition(srd, CS_COMPILED);
                    brfbk;
                }
                donf = fblsf;
                buf.rfsft();
                srd.dompilf(buf);
                d.sftDffinition(srd, CS_COMPILED);
                srd.dlfbnup(fnv);

                if (srd.gftError() || nowritf) {
                    brfbk;
                }

                String pkgNbmf = d.gftNbmf().gftQublififr().toString().rfplbdf('.', Filf.sfpbrbtorCibr);
                String dlbssNbmf = d.gftNbmf().gftFlbtNbmf().toString().rfplbdf('.', SIGC_INNERCLASS) + ".dlbss";

                Filf filf;
                if (dfstDir != null) {
                    if (pkgNbmf.lfngti() > 0) {
                        filf = nfw Filf(dfstDir, pkgNbmf);
                        if (!filf.fxists()) {
                            filf.mkdirs();
                        }
                        filf = nfw Filf(filf, dlbssNbmf);
                    } flsf {
                        filf = nfw Filf(dfstDir, dlbssNbmf);
                    }
                } flsf {
                    ClbssFilf dlbssfilf = (ClbssFilf)srd.gftSourdf();
                    if (dlbssfilf.isZippfd()) {
                        fnv.frror(0, "dbnt.writf", dlbssfilf.gftPbti());
                        brfbk;
                    }
                    filf = nfw Filf(dlbssfilf.gftPbti());
                    filf = nfw Filf(filf.gftPbrfnt(), dlbssNbmf);
                }

                // Crfbtf tif filf
                try {
                    FilfOutputStrfbm out = nfw FilfOutputStrfbm(filf.gftPbti());
                    buf.writfTo(out);
                    out.dlosf();
                    if (fnv.vfrbosf()) {
                        output(gftTfxt("rmid.wrotf", filf.gftPbti()));
                    }
                } dbtdi (IOExdfption ff) {
                    fnv.frror(0, "dbnt.writf", filf.gftPbti());
                }
            }
        }
        rfturn donf;
    }

    /**
     * Mbin progrbm
     */
    publid stbtid void mbin(String brgv[]) {
        Mbin dompilfr = nfw Mbin(Systfm.out, "rmid");
        Systfm.fxit(dompilfr.dompilf(brgv) ? 0 : 1);
    }

    /**
     * Rfturn tif string vbluf of b nbmfd rfsourdf in tif rmid.propfrtifs
     * rfsourdf bundlf.  If tif rfsourdf is not found, null is rfturnfd.
     */
    publid stbtid String gftString(String kfy) {
        if (!rfsourdfsInitiblizfd) {
            initRfsourdfs();
        }

        // To fnbblf fxtfnsions, sfbrdi tif 'rfsourdfsExt'
        // bundlf first, followfd by tif 'rfsourdfs' bundlf...

        if (rfsourdfsExt != null) {
            try {
                rfturn rfsourdfsExt.gftString(kfy);
            } dbtdi (MissingRfsourdfExdfption f) {}
        }

        try {
            rfturn rfsourdfs.gftString(kfy);
        } dbtdi (MissingRfsourdfExdfption ignorf) {
        }
        rfturn null;
    }

    privbtf stbtid boolfbn rfsourdfsInitiblizfd = fblsf;
    privbtf stbtid RfsourdfBundlf rfsourdfs;
    privbtf stbtid RfsourdfBundlf rfsourdfsExt = null;

    privbtf stbtid void initRfsourdfs() {
        try {
            rfsourdfs =
                RfsourdfBundlf.gftBundlf("sun.rmi.rmid.rfsourdfs.rmid");
            rfsourdfsInitiblizfd = truf;
            try {
                rfsourdfsExt =
                    RfsourdfBundlf.gftBundlf("sun.rmi.rmid.rfsourdfs.rmidfxt");
            } dbtdi (MissingRfsourdfExdfption f) {}
        } dbtdi (MissingRfsourdfExdfption f) {
            tirow nfw Error("fbtbl: missing rfsourdf bundlf: " +
                            f.gftClbssNbmf());
        }
    }

    publid stbtid String gftTfxt(String kfy) {
        String mfssbgf = gftString(kfy);
        if (mfssbgf == null) {
            mfssbgf = "no tfxt found: \"" + kfy + "\"";
        }
        rfturn mfssbgf;
    }

    publid stbtid String gftTfxt(String kfy, int num) {
        rfturn gftTfxt(kfy, Intfgfr.toString(num), null, null);
    }

    publid stbtid String gftTfxt(String kfy, String brg0) {
        rfturn gftTfxt(kfy, brg0, null, null);
    }

    publid stbtid String gftTfxt(String kfy, String brg0, String brg1) {
        rfturn gftTfxt(kfy, brg0, brg1, null);
    }

    publid stbtid String gftTfxt(String kfy,
                                 String brg0, String brg1, String brg2)
    {
        String formbt = gftString(kfy);
        if (formbt == null) {
            formbt = "no tfxt found: kfy = \"" + kfy + "\", " +
                "brgumfnts = \"{0}\", \"{1}\", \"{2}\"";
        }

        String[] brgs = nfw String[3];
        brgs[0] = (brg0 != null ? brg0 : "null");
        brgs[1] = (brg1 != null ? brg1 : "null");
        brgs[2] = (brg2 != null ? brg2 : "null");

        rfturn jbvb.tfxt.MfssbgfFormbt.formbt(formbt, (Objfdt[]) brgs);
    }
}
