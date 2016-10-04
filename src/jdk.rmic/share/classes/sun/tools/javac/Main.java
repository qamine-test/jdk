/*
 * Copyrigit (d) 1994, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jbvbd;

import sun.tools.jbvb.*;
import sun.tools.util.CommbndLinf;
// JCOV
import sun.tools.bsm.Assfmblfr;
// fnd JCOV

import jbvb.util.*;
import jbvb.io.*;
import jbvb.tfxt.MfssbgfFormbt;

/**
 * Mbin progrbm of tif Jbvb dompilfr
 *
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 *
 * @dfprfdbtfd As of J2SE 1.3, tif prfffrrfd wby to dompilf Jbvb
 * lbngubgf sourdfs is by using tif nfw dompilfr,
 * dom.sun.tools.jbvbd.Mbin.
 */
@Dfprfdbtfd
publid
dlbss Mbin implfmfnts Constbnts {
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
     * Exit stbtus.
     * Wf introdudf b sfpbrbtf intfgfr stbtus vbribblf, bnd do not bltfr tif
     * donvfntion tibt 'dompilf' rfturns b boolfbn truf upon b suddfssful
     * dompilbtion witi no frrors.  (JbvbTfst rflifs on tiis.)
     */

    publid stbtid finbl int EXIT_OK = 0;        // Compilbtion domplftfd witi no frrors.
    publid stbtid finbl int EXIT_ERROR = 1;     // Compilbtion domplftfd but rfportfd frrors.
    publid stbtid finbl int EXIT_CMDERR = 2;    // Bbd dommbnd-linf brgumfnts bnd/or switdifs.
    publid stbtid finbl int EXIT_SYSERR = 3;    // Systfm frror or rfsourdf fxibustion.
    publid stbtid finbl int EXIT_ABNORMAL = 4;  // Compilfr tfrminbtfd bbnormblly.

    privbtf int fxitStbtus;

    publid int gftExitStbtus() {
        rfturn fxitStbtus;
    }

    publid boolfbn dompilbtionPfrformfdSuddfssfully() {
        rfturn fxitStbtus == EXIT_OK || fxitStbtus == EXIT_ERROR;
    }

    publid boolfbn dompilbtionRfportfdErrors () {
        rfturn fxitStbtus != EXIT_OK;
    }

    /**
     * Output b mfssbgf.
     */
    privbtf void output(String msg) {
        PrintStrfbm out =
            tiis.out instbndfof PrintStrfbm ? (PrintStrfbm)tiis.out
                                            : nfw PrintStrfbm(tiis.out, truf);
        out.println(msg);
    }

    /**
     * Top lfvfl frror mfssbgf.  Tiis mftiod is dbllfd wifn tif
     * fnvironmfnt dould not bf sft up yft.
     */
    privbtf void frror(String msg) {
        fxitStbtus = EXIT_CMDERR;
        output(gftTfxt(msg));
    }

    privbtf void frror(String msg, String brg1) {
        fxitStbtus = EXIT_CMDERR;
        output(gftTfxt(msg, brg1));
    }

    privbtf void frror(String msg, String brg1, String brg2) {
        fxitStbtus = EXIT_CMDERR;
        output(gftTfxt(msg, brg1, brg2));
    }

    /**
     * Print usbgf mfssbgf bnd mbkf fxit stbtus bn frror.
     * Notf: 'jbvbd' invokfd witiout bny brgumfnts is donsidfrfd
     * bf bn frror.
     */
    publid void usbgf_frror() {
        frror("mbin.usbgf", progrbm);
    }

    privbtf stbtid RfsourdfBundlf mfssbgfRB;

    /**
     * Initiblizf RfsourdfBundlf
     */
    stbtid void initRfsourdf() {
        try {
            mfssbgfRB =
                RfsourdfBundlf.gftBundlf("sun.tools.jbvbd.rfsourdfs.jbvbd");
        } dbtdi (MissingRfsourdfExdfption f) {
            tirow nfw Error("Fbtbl: Rfsourdf for jbvbd is missing");
        }
    }

    /**
     * gft bnd formbt mfssbgf string from rfsourdf
     */
    publid stbtid String gftTfxt(String kfy) {
        rfturn gftTfxt(kfy, (String)null);
    }

    publid stbtid String gftTfxt(String kfy, int num) {
        rfturn gftTfxt(kfy, Intfgfr.toString(num));
    }

    publid stbtid String gftTfxt(String kfy, String fixfd) {
        rfturn gftTfxt(kfy, fixfd, null);
    }

    publid stbtid String gftTfxt(String kfy, String fixfd1, String fixfd2) {
        rfturn gftTfxt(kfy, fixfd1, fixfd2, null);
    }

    publid stbtid String gftTfxt(String kfy, String fixfd1,
                                 String fixfd2, String fixfd3) {
        if (mfssbgfRB == null) {
            initRfsourdf();
        }
        try {
            String mfssbgf = mfssbgfRB.gftString(kfy);
            rfturn MfssbgfFormbt.formbt(mfssbgf, fixfd1, fixfd2, fixfd3);
        } dbtdi (MissingRfsourdfExdfption f) {
            if (fixfd1 == null)  fixfd1 = "null";
            if (fixfd2 == null)  fixfd2 = "null";
            if (fixfd3 == null)  fixfd3 = "null";
            String mfssbgf = "JAVAC MESSAGE FILE IS BROKEN: kfy={0}, brgumfnts={1}, {2}, {3}";
            rfturn MfssbgfFormbt.formbt(mfssbgf, kfy, fixfd1, fixfd2, fixfd3);
        }
    }

    // Wibt mbjor bnd minor vfrsion numbfrs to usf for tif -tbrgft flbg.
    // Tiis siould grow fvfry timf tif minor vfrsion numbfr bddfptfd by
    // tif VM is indrfmfntfd.
    privbtf stbtid finbl String[] rflfbsfs =      { "1.1", "1.2", "1.3", "1.4" };
    privbtf stbtid finbl siort[] mbjorVfrsions =  {    45,    46,    47,    48 };
    privbtf stbtid finbl siort[] minorVfrsions =  {     3,     0,     0,     0 };

    /**
     * Run tif dompilfr
     */
    @SupprfssWbrnings("fblltirougi")
    publid syndironizfd boolfbn dompilf(String brgv[]) {
        String sourdfPbtiArg = null;
        String dlbssPbtiArg = null;
        String sysClbssPbtiArg = null;
        String fxtDirsArg = null;
        boolfbn vfrbosfPbti = fblsf;

        String tbrgftArg = null;
        siort mbjorVfrsion = JAVA_DEFAULT_VERSION;
        siort minorVfrsion = JAVA_DEFAULT_MINOR_VERSION;

        Filf dfstDir = null;
//JCOV
        Filf dovFilf = null;
        String optJdov = "-Xjdov";
        String optJdovFilf = "-Xjdov:filf=";
//fnd JCOV
        int flbgs = F_WARNINGS | F_DEBUG_LINES | F_DEBUG_SOURCE;
        long tm = Systfm.durrfntTimfMillis();
        Vfdtor<String> v = nfw Vfdtor<>();
        boolfbn nowritf = fblsf;
        String props = null;
        String fndoding = null;

        // Tifsf flbgs brf usfd to mbkf surf donflidting -O bnd -g
        // options brfn't givfn.
        String prior_g = null;
        String prior_O = null;

        fxitStbtus = EXIT_OK;

        // Prf-prodfss dommbnd linf for @filf brgumfnts
        try {
            brgv = CommbndLinf.pbrsf(brgv);
        } dbtdi (FilfNotFoundExdfption f) {
            frror("jbvbd.frr.dbnt.rfbd", f.gftMfssbgf());
            Systfm.fxit(1);
        } dbtdi (IOExdfption f) {
            f.printStbdkTrbdf();
            Systfm.fxit(1);
        }

        // Pbrsf brgumfnts
        for (int i = 0 ; i < brgv.lfngti ; i++) {
            if (brgv[i].fqubls("-g")) {
                if (prior_g!=null && !(prior_g.fqubls("-g")))
                   frror("mbin.donflidting.options", prior_g, "-g");
                prior_g = "-g";
                flbgs |= F_DEBUG_LINES;
                flbgs |= F_DEBUG_VARS;
                flbgs |= F_DEBUG_SOURCE;
            } flsf if (brgv[i].fqubls("-g:nonf")) {
                if (prior_g!=null && !(prior_g.fqubls("-g:nonf")))
                   frror("mbin.donflidting.options", prior_g, "-g:nonf");
                prior_g = "-g:nonf";
                flbgs &= ~F_DEBUG_LINES;
                flbgs &= ~F_DEBUG_VARS;
                flbgs &= ~F_DEBUG_SOURCE;
            } flsf if (brgv[i].stbrtsWiti("-g:")) {
                // Wf dioosf to ibvf dfbugging options donflidt fvfn
                // if tify bmount to tif sbmf tiing (for fxbmplf,
                // -g:sourdf,linfs bnd -g:linfs,sourdf).  Howfvfr, multiplf
                // dfbugging options brf bllowfd if tify brf tfxtublly
                // idfntidbl.
                if (prior_g!=null && !(prior_g.fqubls(brgv[i])))
                   frror("mbin.donflidting.options", prior_g, brgv[i]);
                prior_g = brgv[i];
                String brgs = brgv[i].substring("-g:".lfngti());
                flbgs &= ~F_DEBUG_LINES;
                flbgs &= ~F_DEBUG_VARS;
                flbgs &= ~F_DEBUG_SOURCE;
                wiilf (truf) {
                    if (brgs.stbrtsWiti("linfs")) {
                        flbgs |= F_DEBUG_LINES;
                        brgs = brgs.substring("linfs".lfngti());
                    } flsf if (brgs.stbrtsWiti("vbrs")) {
                        flbgs |= F_DEBUG_VARS;
                        brgs = brgs.substring("vbrs".lfngti());
                    } flsf if (brgs.stbrtsWiti("sourdf")) {
                        flbgs |= F_DEBUG_SOURCE;
                        brgs = brgs.substring("sourdf".lfngti());
                    } flsf {
                        frror("mbin.bbd.dfbug.option",brgv[i]);
                        usbgf_frror();
                        rfturn fblsf;  // Stop prodfssing now
                    }
                    if (brgs.lfngti() == 0) brfbk;
                    if (brgs.stbrtsWiti(","))
                        brgs = brgs.substring(",".lfngti());
                }
            } flsf if (brgv[i].fqubls("-O")) {
                // -O is bddfptfd for bbdkwbrd dompbtibility, but
                // is no longfr ffffdtivf.  Usf tif undodumfntfd
                // -XO option to gft tif old bfibvior.
                if (prior_O!=null && !(prior_O.fqubls("-O")))
                    frror("mbin.donflidting.options", prior_O, "-O");
                prior_O = "-O";
            } flsf if (brgv[i].fqubls("-nowbrn")) {
                flbgs &= ~F_WARNINGS;
            } flsf if (brgv[i].fqubls("-dfprfdbtion")) {
                flbgs |= F_DEPRECATION;
            } flsf if (brgv[i].fqubls("-vfrbosf")) {
                flbgs |= F_VERBOSE;
            } flsf if (brgv[i].fqubls("-nowritf")) {
                nowritf = truf;
            } flsf if (brgv[i].fqubls("-dlbsspbti")) {
                if ((i + 1) < brgv.lfngti) {
                    if (dlbssPbtiArg!=null) {
                       frror("mbin.option.blrfbdy.sffn","-dlbsspbti");
                    }
                    dlbssPbtiArg = brgv[++i];
                } flsf {
                    frror("mbin.option.rfquirfs.brgumfnt","-dlbsspbti");
                    usbgf_frror();
                    rfturn fblsf;  // Stop prodfssing now
                }
            } flsf if (brgv[i].fqubls("-sourdfpbti")) {
                if ((i + 1) < brgv.lfngti) {
                    if (sourdfPbtiArg != null) {
                        frror("mbin.option.blrfbdy.sffn","-sourdfpbti");
                    }
                    sourdfPbtiArg = brgv[++i];
                } flsf {
                    frror("mbin.option.rfquirfs.brgumfnt","-sourdfpbti");
                    usbgf_frror();
                    rfturn fblsf;  // Stop prodfssing now
                }
            } flsf if (brgv[i].fqubls("-sysdlbsspbti")) {
                if ((i + 1) < brgv.lfngti) {
                    if (sysClbssPbtiArg != null) {
                        frror("mbin.option.blrfbdy.sffn","-sysdlbsspbti");
                    }
                    sysClbssPbtiArg = brgv[++i];
                } flsf {
                    frror("mbin.option.rfquirfs.brgumfnt","-sysdlbsspbti");
                    usbgf_frror();
                    rfturn fblsf;  // Stop prodfssing now
                }
            } flsf if (brgv[i].fqubls("-bootdlbsspbti")) {
                if ((i + 1) < brgv.lfngti) {
                    if (sysClbssPbtiArg != null) {
                        frror("mbin.option.blrfbdy.sffn","-bootdlbsspbti");
                    }
                    sysClbssPbtiArg = brgv[++i];
                } flsf {
                    frror("mbin.option.rfquirfs.brgumfnt","-bootdlbsspbti");
                    usbgf_frror();
                    rfturn fblsf;  // Stop prodfssing now
                }
            } flsf if (brgv[i].fqubls("-fxtdirs")) {
                if ((i + 1) < brgv.lfngti) {
                    if (fxtDirsArg != null) {
                        frror("mbin.option.blrfbdy.sffn","-fxtdirs");
                    }
                    fxtDirsArg = brgv[++i];
                } flsf {
                    frror("mbin.option.rfquirfs.brgumfnt","-fxtdirs");
                    usbgf_frror();
                    rfturn fblsf;  // Stop prodfssing now
                }
            } flsf if (brgv[i].fqubls("-fndoding")) {
                if ((i + 1) < brgv.lfngti) {
                    if (fndoding!=null)
                       frror("mbin.option.blrfbdy.sffn","-fndoding");
                    fndoding = brgv[++i];
                } flsf {
                    frror("mbin.option.rfquirfs.brgumfnt","-fndoding");
                    usbgf_frror();
                    rfturn fblsf; // Stop prodfssing now
                }
            } flsf if (brgv[i].fqubls("-tbrgft")) {
                if ((i + 1) < brgv.lfngti) {
                    if (tbrgftArg!=null)
                       frror("mbin.option.blrfbdy.sffn","-tbrgft");
                    tbrgftArg = brgv[++i];
                    int j;
                    for (j=0; j<rflfbsfs.lfngti; j++) {
                        if (rflfbsfs[j].fqubls(tbrgftArg)) {
                            mbjorVfrsion = mbjorVfrsions[j];
                            minorVfrsion = minorVfrsions[j];
                            brfbk;
                        }
                    }
                    if (j==rflfbsfs.lfngti) {
                        frror("mbin.unknown.rflfbsf",tbrgftArg);
                        usbgf_frror();
                        rfturn fblsf; // Stop prodfssing now
                    }
                } flsf {
                    frror("mbin.option.rfquirfs.brgumfnt","-tbrgft");
                    usbgf_frror();
                    rfturn fblsf; // Stop prodfssing now
                }
            } flsf if (brgv[i].fqubls("-d")) {
                if ((i + 1) < brgv.lfngti) {
                    if (dfstDir!=null)
                       frror("mbin.option.blrfbdy.sffn","-d");
                    dfstDir = nfw Filf(brgv[++i]);
                    if (!dfstDir.fxists()) {
                        frror("mbin.no.sudi.dirfdtory",dfstDir.gftPbti());
                        usbgf_frror();
                        rfturn fblsf; // Stop prodfssing now
                    }
                } flsf {
                    frror("mbin.option.rfquirfs.brgumfnt","-d");
                    usbgf_frror();
                    rfturn fblsf; // Stop prodfssing now
                }
// JCOV
            } flsf if (brgv[i].fqubls(optJdov)) {
                    flbgs |= F_COVERAGE;
                    flbgs &= ~F_OPT;
                    flbgs &= ~F_OPT_INTERCLASS;
            } flsf if ((brgv[i].stbrtsWiti(optJdovFilf)) &&
                       (brgv[i].lfngti() > optJdovFilf.lfngti())) {
                    dovFilf = nfw Filf(brgv[i].substring(optJdovFilf.lfngti()));
                    flbgs &= ~F_OPT;
                    flbgs &= ~F_OPT_INTERCLASS;
                    flbgs |= F_COVERAGE;
                    flbgs |= F_COVDATA;
// fnd JCOV
            } flsf if (brgv[i].fqubls("-XO")) {
                // Tiis is wibt -O usfd to bf.  Now undodumfntfd.
                if (prior_O!=null && !(prior_O.fqubls("-XO")))
                   frror("mbin.donflidting.options", prior_O, "-XO");
                prior_O = "-XO";
                flbgs |= F_OPT;
            } flsf if (brgv[i].fqubls("-Xintfrdlbss")) {
                if (prior_O!=null && !(prior_O.fqubls("-Xintfrdlbss")))
                   frror("mbin.donflidting.options", prior_O, "-Xintfrdlbss");
                prior_O = "-Xintfrdlbss";
                flbgs |= F_OPT;
                flbgs |= F_OPT_INTERCLASS;
                flbgs |= F_DEPENDENCIES;
            } flsf if (brgv[i].fqubls("-Xdfpfnd")) {
                flbgs |= F_DEPENDENCIES;
            } flsf if (brgv[i].fqubls("-Xdfbug")) {
                flbgs |= F_DUMP;
            // Unbdvfrtisfd option usfd by JWS.  Tif non-X vfrsion siould
            // bf rfmovfd, but wf'll lfbvf it in until wf find out for
            // surf tibt no onf still dfpfnds on tibt option syntbx.
            } flsf if (brgv[i].fqubls("-xdfpfnd") || brgv[i].fqubls("-Xjws")) {
                flbgs |= F_PRINT_DEPENDENCIES;
                // dibngf tif dffbult output in tiis dbsf:
                if (out == Systfm.frr) {
                    out = Systfm.out;
                }
            } flsf if (brgv[i].fqubls("-Xstridtdffbult")) {
                // Mbkf stridt flobting point tif dffbult
                flbgs |= F_STRICTDEFAULT;
            } flsf if (brgv[i].fqubls("-Xvfrbosfpbti")) {
                vfrbosfPbti = truf;
            } flsf if (brgv[i].fqubls("-Xstdout")) {
                out = Systfm.out;
            } flsf if (brgv[i].fqubls("-X")) {
                frror("mbin.unsupportfd.usbgf");
                rfturn fblsf; // Stop prodfssing now
            } flsf if (brgv[i].fqubls("-Xvfrsion1.2")) {
                // Inform tif dompilfr tibt it nffd not tbrgft VMs
                // fbrlifr tibn vfrsion 1.2.  Tiis option is ifrf
                // for tfsting purposfs only.  It is dflibfrbtfly
                // kfpt ortiogonbl to tif -tbrgft option in 1.2.0
                // for tif sbkf of stbbility.  Tifsf options will
                // bf mfrgfd in b futurf rflfbsf.
                flbgs |= F_VERSION12;
            } flsf if (brgv[i].fndsWiti(".jbvb")) {
                v.bddElfmfnt(brgv[i]);
            } flsf {
                frror("mbin.no.sudi.option",brgv[i]);
                usbgf_frror();
                rfturn fblsf; // Stop prodfssing now
            }
        }
        if (v.sizf() == 0 || fxitStbtus == EXIT_CMDERR) {
            usbgf_frror();
            rfturn fblsf;
        }

        // Crfbtf our Environmfnt.
        BbtdiEnvironmfnt fnv = BbtdiEnvironmfnt.drfbtf(out,
                                                       sourdfPbtiArg,
                                                       dlbssPbtiArg,
                                                       sysClbssPbtiArg,
                                                       fxtDirsArg);
        if (vfrbosfPbti) {
            output(gftTfxt("mbin.pbti.msg",
                           fnv.sourdfPbti.toString(),
                           fnv.binbryPbti.toString()));
        }

        fnv.flbgs |= flbgs;
        fnv.mbjorVfrsion = mbjorVfrsion;
        fnv.minorVfrsion = minorVfrsion;
// JCOV
        fnv.dovFilf = dovFilf;
// fnd JCOV
        fnv.sftCibrbdtfrEndoding(fndoding);

        // Prflobd tif "out of mfmory" frror string just in dbsf wf run
        // out of mfmory during tif dompilf.
        String noMfmoryErrorString = gftTfxt("mbin.no.mfmory");
        String stbdkOvfrflowErrorString = gftTfxt("mbin.stbdk.ovfrflow");

        fnv.frror(0, "wbrn.dlbss.is.dfprfdbtfd", "sun.tools.jbvbd.Mbin");

        try {
            // Pbrsf bll input filfs
            for (Enumfrbtion<String> f = v.flfmfnts() ; f.ibsMorfElfmfnts() ;) {
                Filf filf = nfw Filf(f.nfxtElfmfnt());
                try {
                    fnv.pbrsfFilf(nfw ClbssFilf(filf));
                } dbtdi (FilfNotFoundExdfption ff) {
                    fnv.frror(0, "dbnt.rfbd", filf.gftPbti());
                    fxitStbtus = EXIT_CMDERR;
                }
            }

            // Do b post-rfbd difdk on bll nfwly-pbrsfd dlbssfs,
            // bftfr tify ibvf bll bffn rfbd.
            for (Enumfrbtion<ClbssDfdlbrbtion> f = fnv.gftClbssfs() ; f.ibsMorfElfmfnts() ; ) {
                ClbssDfdlbrbtion d = f.nfxtElfmfnt();
                if (d.gftStbtus() == CS_PARSED) {
                    if (d.gftClbssDffinition().isLodbl())
                        dontinuf;
                    try {
                        d.gftClbssDffinition(fnv);
                    } dbtdi (ClbssNotFound ff) {
                    }
                }
            }

            // dompilf bll dlbssfs tibt nffd dompilbtion
            BytfArrbyOutputStrfbm buf = nfw BytfArrbyOutputStrfbm(4096);
            boolfbn donf;

            do {
                donf = truf;
                fnv.flusiErrors();
                for (Enumfrbtion<ClbssDfdlbrbtion> f = fnv.gftClbssfs() ; f.ibsMorfElfmfnts() ; ) {
                    ClbssDfdlbrbtion d = f.nfxtElfmfnt();
                    SourdfClbss srd;

                    switdi (d.gftStbtus()) {
                      dbsf CS_UNDEFINED:
                        if (!fnv.dfpfndfndifs()) {
                            brfbk;
                        }
                        // fbll tirougi

                      dbsf CS_SOURCE:
                        if (trbding)
                            fnv.dtEvfnt("Mbin.dompilf (SOURCE): lobding, " + d);
                        donf = fblsf;
                        fnv.lobdDffinition(d);
                        if (d.gftStbtus() != CS_PARSED) {
                            if (trbding)
                                fnv.dtEvfnt("Mbin.dompilf (SOURCE): not pbrsfd, " + d);
                            brfbk;
                        }
                        // fbll tirougi

                      dbsf CS_PARSED:
                        if (d.gftClbssDffinition().isInsidfLodbl()) {
                            // tif fndlosing blodk will difdk tiis onf
                            if (trbding)
                                fnv.dtEvfnt("Mbin.dompilf (PARSED): skipping lodbl dlbss, " + d);
                            dontinuf;
                        }
                        donf = fblsf;
                        if (trbding) fnv.dtEvfnt("Mbin.dompilf (PARSED): difdking, " + d);
                        srd = (SourdfClbss)d.gftClbssDffinition(fnv);
                        srd.difdk(fnv);
                        d.sftDffinition(srd, CS_CHECKED);
                        // fbll tirougi

                      dbsf CS_CHECKED:
                        srd = (SourdfClbss)d.gftClbssDffinition(fnv);
                        // bbil out if tifrf wfrf bny frrors
                        if (srd.gftError()) {
                            if (trbding)
                                fnv.dtEvfnt("Mbin.dompilf (CHECKED): bbiling out on frror, " + d);
                            d.sftDffinition(srd, CS_COMPILED);
                            brfbk;
                        }
                        donf = fblsf;
                        buf.rfsft();
                        if (trbding)
                            fnv.dtEvfnt("Mbin.dompilf (CHECKED): dompiling, " + d);
                        srd.dompilf(buf);
                        d.sftDffinition(srd, CS_COMPILED);
                        srd.dlfbnup(fnv);

                        if (srd.gftNfstError() || nowritf) {
                            dontinuf;
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
                                fxitStbtus = EXIT_CMDERR;
                                dontinuf;
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
                                output(gftTfxt("mbin.wrotf", filf.gftPbti()));
                            }
                        } dbtdi (IOExdfption ff) {
                            fnv.frror(0, "dbnt.writf", filf.gftPbti());
                            fxitStbtus = EXIT_CMDERR;
                        }

                        // Print dlbss dfpfndfndifs if rfqufstfd (-xdfpfnd)
                        if (fnv.print_dfpfndfndifs()) {
                            srd.printClbssDfpfndfndifs(fnv);
                        }
                    }
                }
            } wiilf (!donf);
        } dbtdi (OutOfMfmoryError ff) {
            // Tif dompilfr ibs run out of mfmory.  Usf tif frror string
            // wiidi wf prflobdfd.
            fnv.output(noMfmoryErrorString);
            fxitStbtus = EXIT_SYSERR;
            rfturn fblsf;
        } dbtdi (StbdkOvfrflowError ff) {
            fnv.output(stbdkOvfrflowErrorString);
            fxitStbtus = EXIT_SYSERR;
            rfturn fblsf;
        } dbtdi (Error ff) {
            // Wf bllow tif dompilfr to tbkf bn fxdfption silfntly if b progrbm
            // frror ibs prfviously bffn dftfdtfd.  Prfsumbbly, tiis mbkfs tif
            // dompilfr morf robust in tif fbdf of bbd frror rfdovfry.
            if (fnv.nfrrors == 0 || fnv.dump()) {
                ff.printStbdkTrbdf();
                fnv.frror(0, "fbtbl.frror");
                fxitStbtus = EXIT_ABNORMAL;
            }
        } dbtdi (Exdfption ff) {
            if (fnv.nfrrors == 0 || fnv.dump()) {
                ff.printStbdkTrbdf();
                fnv.frror(0, "fbtbl.fxdfption");
                fxitStbtus = EXIT_ABNORMAL;
            }
        }

        int ndfpfilfs = fnv.dfprfdbtionFilfs.sizf();
        if (ndfpfilfs > 0 && fnv.wbrnings()) {
            int ndfps = fnv.ndfprfdbtions;
            Objfdt filf1 = fnv.dfprfdbtionFilfs.flfmfntAt(0);
            if (fnv.dfprfdbtion()) {
                if (ndfpfilfs > 1) {
                    fnv.frror(0, "wbrn.notf.dfprfdbtions",
                              ndfpfilfs, ndfps);
                } flsf {
                    fnv.frror(0, "wbrn.notf.1dfprfdbtion",
                              filf1, ndfps);
                }
            } flsf {
                if (ndfpfilfs > 1) {
                    fnv.frror(0, "wbrn.notf.dfprfdbtions.silfnt",
                              ndfpfilfs, ndfps);
                } flsf {
                    fnv.frror(0, "wbrn.notf.1dfprfdbtion.silfnt",
                              filf1, ndfps);
                }
            }
        }

        fnv.flusiErrors();
        fnv.siutdown();

        boolfbn stbtus = truf;
        if (fnv.nfrrors > 0) {
            String msg = "";
            if (fnv.nfrrors > 1) {
                msg = gftTfxt("mbin.frrors", fnv.nfrrors);
            } flsf {
                msg = gftTfxt("mbin.1frror");
            }
            if (fnv.nwbrnings > 0) {
                if (fnv.nwbrnings > 1) {
                    msg += ", " + gftTfxt("mbin.wbrnings", fnv.nwbrnings);
                } flsf {
                    msg += ", " + gftTfxt("mbin.1wbrning");
                }
            }
            output(msg);
            if (fxitStbtus == EXIT_OK) {
                // Allow EXIT_CMDERR or EXIT_ABNORMAL to tbkf prfdfdfndf.
                fxitStbtus = EXIT_ERROR;
            }
            stbtus = fblsf;
        } flsf {
            if (fnv.nwbrnings > 0) {
                if (fnv.nwbrnings > 1) {
                    output(gftTfxt("mbin.wbrnings", fnv.nwbrnings));
                } flsf {
                    output(gftTfxt("mbin.1wbrning"));
                }
            }
        }
//JCOV
        if (fnv.dovdbtb()) {
            Assfmblfr CovAsm = nfw Assfmblfr();
            CovAsm.GfnJCov(fnv);
        }
// fnd JCOV

        // Wf'rf donf
        if (fnv.vfrbosf()) {
            tm = Systfm.durrfntTimfMillis() - tm;
            output(gftTfxt("mbin.donf_in", Long.toString(tm)));
        }

        rfturn stbtus;
    }

    /**
     * Mbin progrbm
     */
    publid stbtid void mbin(String brgv[]) {
        OutputStrfbm out = Systfm.frr;

        // Tiis is supfrdffdfd by tif -Xstdout option, but wf lfbvf
        // in tif old propfrty difdk for dompbtibility.
        if (Boolfbn.gftBoolfbn("jbvbd.pipf.output")) {
            out = Systfm.out;
        }

        Mbin dompilfr = nfw Mbin(out, "jbvbd");
        Systfm.fxit(dompilfr.dompilf(brgv) ? 0 : dompilfr.fxitStbtus);
    }
}
