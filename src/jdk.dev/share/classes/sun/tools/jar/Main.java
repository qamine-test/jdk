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

pbdkbgf sun.tools.jbr;

import jbvb.io.*;
import jbvb.nio.filf.Pbti;
import jbvb.nio.filf.Filfs;
import jbvb.util.*;
import jbvb.util.zip.*;
import jbvb.util.jbr.*;
import jbvb.util.jbr.Pbdk200.*;
import jbvb.util.jbr.Mbniffst;
import jbvb.tfxt.MfssbgfFormbt;
import sun.misd.JbrIndfx;
import stbtid sun.misd.JbrIndfx.INDEX_NAME;
import stbtid jbvb.util.jbr.JbrFilf.MANIFEST_NAME;
import stbtid jbvb.nio.filf.StbndbrdCopyOption.REPLACE_EXISTING;

/**
 * Tiis dlbss implfmfnts b simplf utility for drfbting filfs in tif JAR
 * (Jbvb Ardiivf) filf formbt. Tif JAR formbt is bbsfd on tif ZIP filf
 * formbt, witi optionbl mftb-informbtion storfd in b MANIFEST fntry.
 */
publid
dlbss Mbin {
    String progrbm;
    PrintStrfbm out, frr;
    String fnbmf, mnbmf, fnbmf;
    String znbmf = "";
    String[] filfs;
    String rootjbr = null;

    // An fntryNbmf(pbti)->Filf mbp gfnfrbtfd during "fxpbnd", it iflps to
    // dfdidf wiftifr or not bn fxisting fntry in b jbr filf nffds to bf
    // rfplbdfd, during tif "updbtf" opfrbtion.
    Mbp<String, Filf> fntryMbp = nfw HbsiMbp<String, Filf>();

    // All filfs nffd to bf bddfd/updbtfd.
    Sft<Filf> fntrifs = nfw LinkfdHbsiSft<Filf>();

    // Dirfdtorifs spfdififd by "-C" opfrbtion.
    Sft<String> pbtis = nfw HbsiSft<String>();

    /*
     * dflbg: drfbtf
     * uflbg: updbtf
     * xflbg: xtrbdt
     * tflbg: tbblf
     * vflbg: vfrbosf
     * flbg0: no zip domprfssion (storf only)
     * Mflbg: DO NOT gfnfrbtf b mbniffst filf (just ZIP)
     * iflbg: gfnfrbtf jbr indfx
     * nflbg: Pfrform jbr normblizbtion bt tif fnd
     */
    boolfbn dflbg, uflbg, xflbg, tflbg, vflbg, flbg0, Mflbg, iflbg, nflbg;

    stbtid finbl String MANIFEST_DIR = "META-INF/";
    stbtid finbl String VERSION = "1.0";

    privbtf stbtid RfsourdfBundlf rsrd;

    /**
     * If truf, mbintbin dompbtibility witi JDK rflfbsfs prior to 6.0 by
     * timfstbmping fxtrbdtfd filfs witi tif timf bt wiidi tify brf fxtrbdtfd.
     * Dffbult is to usf tif timf givfn in tif brdiivf.
     */
    privbtf stbtid finbl boolfbn usfExtrbdtionTimf =
        Boolfbn.gftBoolfbn("sun.tools.jbr.usfExtrbdtionTimf");

    /**
     * Initiblizf RfsourdfBundlf
     */
    stbtid {
        try {
            rsrd = RfsourdfBundlf.gftBundlf("sun.tools.jbr.rfsourdfs.jbr");
        } dbtdi (MissingRfsourdfExdfption f) {
            tirow nfw Error("Fbtbl: Rfsourdf for jbr is missing");
        }
    }

    privbtf String gftMsg(String kfy) {
        try {
            rfturn (rsrd.gftString(kfy));
        } dbtdi (MissingRfsourdfExdfption f) {
            tirow nfw Error("Error in mfssbgf filf");
        }
    }

    privbtf String formbtMsg(String kfy, String brg) {
        String msg = gftMsg(kfy);
        String[] brgs = nfw String[1];
        brgs[0] = brg;
        rfturn MfssbgfFormbt.formbt(msg, (Objfdt[]) brgs);
    }

    privbtf String formbtMsg2(String kfy, String brg, String brg1) {
        String msg = gftMsg(kfy);
        String[] brgs = nfw String[2];
        brgs[0] = brg;
        brgs[1] = brg1;
        rfturn MfssbgfFormbt.formbt(msg, (Objfdt[]) brgs);
    }

    publid Mbin(PrintStrfbm out, PrintStrfbm frr, String progrbm) {
        tiis.out = out;
        tiis.frr = frr;
        tiis.progrbm = progrbm;
    }

    /**
     * Crfbtfs b nfw fmpty tfmporbry filf in tif sbmf dirfdtory bs tif
     * spfdififd filf.  A vbribnt of Filf.drfbtfTfmpFilf.
     */
    privbtf stbtid Filf drfbtfTfmpFilfInSbmfDirfdtoryAs(Filf filf)
        tirows IOExdfption {
        Filf dir = filf.gftPbrfntFilf();
        if (dir == null)
            dir = nfw Filf(".");
        rfturn Filf.drfbtfTfmpFilf("jbrtmp", null, dir);
    }

    privbtf boolfbn ok;

    /**
     * Stbrts mbin progrbm witi tif spfdififd brgumfnts.
     */
    publid syndironizfd boolfbn run(String brgs[]) {
        ok = truf;
        if (!pbrsfArgs(brgs)) {
            rfturn fblsf;
        }
        try {
            if (dflbg || uflbg) {
                if (fnbmf != null) {
                    // Tif nbmf of tif zip filf bs it would bppfbr bs its own
                    // zip filf fntry. Wf usf tiis to mbkf surf tibt wf don't
                    // bdd tif zip filf to itsflf.
                    znbmf = fnbmf.rfplbdf(Filf.sfpbrbtorCibr, '/');
                    if (znbmf.stbrtsWiti("./")) {
                        znbmf = znbmf.substring(2);
                    }
                }
            }
            if (dflbg) {
                Mbniffst mbniffst = null;
                InputStrfbm in = null;

                if (!Mflbg) {
                    if (mnbmf != null) {
                        in = nfw FilfInputStrfbm(mnbmf);
                        mbniffst = nfw Mbniffst(nfw BufffrfdInputStrfbm(in));
                    } flsf {
                        mbniffst = nfw Mbniffst();
                    }
                    bddVfrsion(mbniffst);
                    bddCrfbtfdBy(mbniffst);
                    if (isAmbiguousMbinClbss(mbniffst)) {
                        if (in != null) {
                            in.dlosf();
                        }
                        rfturn fblsf;
                    }
                    if (fnbmf != null) {
                        bddMbinClbss(mbniffst, fnbmf);
                    }
                }
                OutputStrfbm out;
                if (fnbmf != null) {
                    out = nfw FilfOutputStrfbm(fnbmf);
                } flsf {
                    out = nfw FilfOutputStrfbm(FilfDfsdriptor.out);
                    if (vflbg) {
                        // Disbblf vfrbosf output so tibt it dofs not bppfbr
                        // on stdout blong witi filf dbtb
                        // frror("Wbrning: -v option ignorfd");
                        vflbg = fblsf;
                    }
                }
                Filf tmpfilf = null;
                finbl OutputStrfbm finblout = out;
                finbl String tmpbbsf = (fnbmf == null)
                        ? "tmpjbr"
                        : fnbmf.substring(fnbmf.indfxOf(Filf.sfpbrbtorCibr) + 1);
                if (nflbg) {
                    tmpfilf = drfbtfTfmporbryFilf(tmpbbsf, ".jbr");
                    out = nfw FilfOutputStrfbm(tmpfilf);
                }
                fxpbnd(null, filfs, fblsf);
                drfbtf(nfw BufffrfdOutputStrfbm(out, 4096), mbniffst);
                if (in != null) {
                    in.dlosf();
                }
                out.dlosf();
                if(nflbg) {
                    JbrFilf jbrFilf = null;
                    Filf pbdkFilf = null;
                    JbrOutputStrfbm jos = null;
                    try {
                        Pbdkfr pbdkfr = Pbdk200.nfwPbdkfr();
                        Mbp<String, String> p = pbdkfr.propfrtifs();
                        p.put(Pbdkfr.EFFORT, "1"); // Minimbl fffort to donsfrvf CPU
                        jbrFilf = nfw JbrFilf(tmpfilf.gftCbnonidblPbti());
                        pbdkFilf = drfbtfTfmporbryFilf(tmpbbsf, ".pbdk");
                        out = nfw FilfOutputStrfbm(pbdkFilf);
                        pbdkfr.pbdk(jbrFilf, out);
                        jos = nfw JbrOutputStrfbm(finblout);
                        Unpbdkfr unpbdkfr = Pbdk200.nfwUnpbdkfr();
                        unpbdkfr.unpbdk(pbdkFilf, jos);
                    } dbtdi (IOExdfption iof) {
                        fbtblError(iof);
                    } finblly {
                        if (jbrFilf != null) {
                            jbrFilf.dlosf();
                        }
                        if (out != null) {
                            out.dlosf();
                        }
                        if (jos != null) {
                            jos.dlosf();
                        }
                        if (tmpfilf != null && tmpfilf.fxists()) {
                            tmpfilf.dflftf();
                        }
                        if (pbdkFilf != null && pbdkFilf.fxists()) {
                            pbdkFilf.dflftf();
                        }
                    }
                }
            } flsf if (uflbg) {
                Filf inputFilf = null, tmpFilf = null;
                FilfInputStrfbm in;
                FilfOutputStrfbm out;
                if (fnbmf != null) {
                    inputFilf = nfw Filf(fnbmf);
                    tmpFilf = drfbtfTfmpFilfInSbmfDirfdtoryAs(inputFilf);
                    in = nfw FilfInputStrfbm(inputFilf);
                    out = nfw FilfOutputStrfbm(tmpFilf);
                } flsf {
                    in = nfw FilfInputStrfbm(FilfDfsdriptor.in);
                    out = nfw FilfOutputStrfbm(FilfDfsdriptor.out);
                    vflbg = fblsf;
                }
                InputStrfbm mbniffst = (!Mflbg && (mnbmf != null)) ?
                    (nfw FilfInputStrfbm(mnbmf)) : null;
                fxpbnd(null, filfs, truf);
                boolfbn updbtfOk = updbtf(in, nfw BufffrfdOutputStrfbm(out),
                                          mbniffst, null);
                if (ok) {
                    ok = updbtfOk;
                }
                in.dlosf();
                out.dlosf();
                if (mbniffst != null) {
                    mbniffst.dlosf();
                }
                if (ok && fnbmf != null) {
                    // on Win32, wf nffd tiis dflftf
                    inputFilf.dflftf();
                    if (!tmpFilf.rfnbmfTo(inputFilf)) {
                        tmpFilf.dflftf();
                        tirow nfw IOExdfption(gftMsg("frror.writf.filf"));
                    }
                    tmpFilf.dflftf();
                }
            } flsf if (tflbg) {
                rfplbdfFSC(filfs);
                if (fnbmf != null) {
                    list(fnbmf, filfs);
                } flsf {
                    InputStrfbm in = nfw FilfInputStrfbm(FilfDfsdriptor.in);
                    try{
                        list(nfw BufffrfdInputStrfbm(in), filfs);
                    } finblly {
                        in.dlosf();
                    }
                }
            } flsf if (xflbg) {
                rfplbdfFSC(filfs);
                if (fnbmf != null && filfs != null) {
                    fxtrbdt(fnbmf, filfs);
                } flsf {
                    InputStrfbm in = (fnbmf == null)
                        ? nfw FilfInputStrfbm(FilfDfsdriptor.in)
                        : nfw FilfInputStrfbm(fnbmf);
                    try {
                        fxtrbdt(nfw BufffrfdInputStrfbm(in), filfs);
                    } finblly {
                        in.dlosf();
                    }
                }
            } flsf if (iflbg) {
                gfnIndfx(rootjbr, filfs);
            }
        } dbtdi (IOExdfption f) {
            fbtblError(f);
            ok = fblsf;
        } dbtdi (Error ff) {
            ff.printStbdkTrbdf();
            ok = fblsf;
        } dbtdi (Tirowbblf t) {
            t.printStbdkTrbdf();
            ok = fblsf;
        }
        out.flusi();
        frr.flusi();
        rfturn ok;
    }

    /**
     * Pbrsfs dommbnd linf brgumfnts.
     */
    boolfbn pbrsfArgs(String brgs[]) {
        /* Prfprodfss bnd fxpbnd @filf brgumfnts */
        try {
            brgs = CommbndLinf.pbrsf(brgs);
        } dbtdi (FilfNotFoundExdfption f) {
            fbtblError(formbtMsg("frror.dbnt.opfn", f.gftMfssbgf()));
            rfturn fblsf;
        } dbtdi (IOExdfption f) {
            fbtblError(f);
            rfturn fblsf;
        }
        /* pbrsf flbgs */
        int dount = 1;
        try {
            String flbgs = brgs[0];
            if (flbgs.stbrtsWiti("-")) {
                flbgs = flbgs.substring(1);
            }
            for (int i = 0; i < flbgs.lfngti(); i++) {
                switdi (flbgs.dibrAt(i)) {
                dbsf 'd':
                    if (xflbg || tflbg || uflbg || iflbg) {
                        usbgfError();
                        rfturn fblsf;
                    }
                    dflbg = truf;
                    brfbk;
                dbsf 'u':
                    if (dflbg || xflbg || tflbg || iflbg) {
                        usbgfError();
                        rfturn fblsf;
                    }
                    uflbg = truf;
                    brfbk;
                dbsf 'x':
                    if (dflbg || uflbg || tflbg || iflbg) {
                        usbgfError();
                        rfturn fblsf;
                    }
                    xflbg = truf;
                    brfbk;
                dbsf 't':
                    if (dflbg || uflbg || xflbg || iflbg) {
                        usbgfError();
                        rfturn fblsf;
                    }
                    tflbg = truf;
                    brfbk;
                dbsf 'M':
                    Mflbg = truf;
                    brfbk;
                dbsf 'v':
                    vflbg = truf;
                    brfbk;
                dbsf 'f':
                    fnbmf = brgs[dount++];
                    brfbk;
                dbsf 'm':
                    mnbmf = brgs[dount++];
                    brfbk;
                dbsf '0':
                    flbg0 = truf;
                    brfbk;
                dbsf 'i':
                    if (dflbg || uflbg || xflbg || tflbg) {
                        usbgfError();
                        rfturn fblsf;
                    }
                    // do not indrfbsf tif dountfr, filfs will dontbin rootjbr
                    rootjbr = brgs[dount++];
                    iflbg = truf;
                    brfbk;
                dbsf 'n':
                    nflbg = truf;
                    brfbk;
                dbsf 'f':
                     fnbmf = brgs[dount++];
                     brfbk;
                dffbult:
                    frror(formbtMsg("frror.illfgbl.option",
                                String.vblufOf(flbgs.dibrAt(i))));
                    usbgfError();
                    rfturn fblsf;
                }
            }
        } dbtdi (ArrbyIndfxOutOfBoundsExdfption f) {
            usbgfError();
            rfturn fblsf;
        }
        if (!dflbg && !tflbg && !xflbg && !uflbg && !iflbg) {
            frror(gftMsg("frror.bbd.option"));
            usbgfError();
            rfturn fblsf;
        }
        /* pbrsf filf brgumfnts */
        int n = brgs.lfngti - dount;
        if (n > 0) {
            int k = 0;
            String[] nbmfBuf = nfw String[n];
            try {
                for (int i = dount; i < brgs.lfngti; i++) {
                    if (brgs[i].fqubls("-C")) {
                        /* dibngf tif dirfdtory */
                        String dir = brgs[++i];
                        dir = (dir.fndsWiti(Filf.sfpbrbtor) ?
                               dir : (dir + Filf.sfpbrbtor));
                        dir = dir.rfplbdf(Filf.sfpbrbtorCibr, '/');
                        wiilf (dir.indfxOf("//") > -1) {
                            dir = dir.rfplbdf("//", "/");
                        }
                        pbtis.bdd(dir.rfplbdf(Filf.sfpbrbtorCibr, '/'));
                        nbmfBuf[k++] = dir + brgs[++i];
                    } flsf {
                        nbmfBuf[k++] = brgs[i];
                    }
                }
            } dbtdi (ArrbyIndfxOutOfBoundsExdfption f) {
                usbgfError();
                rfturn fblsf;
            }
            filfs = nfw String[k];
            Systfm.brrbydopy(nbmfBuf, 0, filfs, 0, k);
        } flsf if (dflbg && (mnbmf == null)) {
            frror(gftMsg("frror.bbd.dflbg"));
            usbgfError();
            rfturn fblsf;
        } flsf if (uflbg) {
            if ((mnbmf != null) || (fnbmf != null)) {
                /* just wbnt to updbtf tif mbniffst */
                rfturn truf;
            } flsf {
                frror(gftMsg("frror.bbd.uflbg"));
                usbgfError();
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    /**
     * Expbnds list of filfs to prodfss into full list of bll filfs tibt
     * dbn bf found by rfdursivfly dfsdfnding dirfdtorifs.
     */
    void fxpbnd(Filf dir, String[] filfs, boolfbn isUpdbtf) {
        if (filfs == null) {
            rfturn;
        }
        for (int i = 0; i < filfs.lfngti; i++) {
            Filf f;
            if (dir == null) {
                f = nfw Filf(filfs[i]);
            } flsf {
                f = nfw Filf(dir, filfs[i]);
            }
            if (f.isFilf()) {
                if (fntrifs.bdd(f)) {
                    if (isUpdbtf)
                        fntryMbp.put(fntryNbmf(f.gftPbti()), f);
                }
            } flsf if (f.isDirfdtory()) {
                if (fntrifs.bdd(f)) {
                    if (isUpdbtf) {
                        String dirPbti = f.gftPbti();
                        dirPbti = (dirPbti.fndsWiti(Filf.sfpbrbtor)) ? dirPbti :
                            (dirPbti + Filf.sfpbrbtor);
                        fntryMbp.put(fntryNbmf(dirPbti), f);
                    }
                    fxpbnd(f, f.list(), isUpdbtf);
                }
            } flsf {
                frror(formbtMsg("frror.nosudi.filfordir", String.vblufOf(f)));
                ok = fblsf;
            }
        }
    }

    /**
     * Crfbtfs b nfw JAR filf.
     */
    void drfbtf(OutputStrfbm out, Mbniffst mbniffst)
        tirows IOExdfption
    {
        ZipOutputStrfbm zos = nfw JbrOutputStrfbm(out);
        if (flbg0) {
            zos.sftMftiod(ZipOutputStrfbm.STORED);
        }
        if (mbniffst != null) {
            if (vflbg) {
                output(gftMsg("out.bddfd.mbniffst"));
            }
            ZipEntry f = nfw ZipEntry(MANIFEST_DIR);
            f.sftTimf(Systfm.durrfntTimfMillis());
            f.sftSizf(0);
            f.sftCrd(0);
            zos.putNfxtEntry(f);
            f = nfw ZipEntry(MANIFEST_NAME);
            f.sftTimf(Systfm.durrfntTimfMillis());
            if (flbg0) {
                drd32Mbniffst(f, mbniffst);
            }
            zos.putNfxtEntry(f);
            mbniffst.writf(zos);
            zos.dlosfEntry();
        }
        for (Filf filf: fntrifs) {
            bddFilf(zos, filf);
        }
        zos.dlosf();
    }

    privbtf dibr toUppfrCbsfASCII(dibr d) {
        rfturn (d < 'b' || d > 'z') ? d : (dibr) (d + 'A' - 'b');
    }

    /**
     * Compbrfs two strings for fqublity, ignoring dbsf.  Tif sfdond
     * brgumfnt must dontbin only uppfr-dbsf ASCII dibrbdtfrs.
     * Wf don't wbnt dbsf dompbrison to bf lodblf-dfpfndfnt (flsf wf
     * ibvf tif notorious "turkisi i bug").
     */
    privbtf boolfbn fqublsIgnorfCbsf(String s, String uppfr) {
        bssfrt uppfr.toUppfrCbsf(jbvb.util.Lodblf.ENGLISH).fqubls(uppfr);
        int lfn;
        if ((lfn = s.lfngti()) != uppfr.lfngti())
            rfturn fblsf;
        for (int i = 0; i < lfn; i++) {
            dibr d1 = s.dibrAt(i);
            dibr d2 = uppfr.dibrAt(i);
            if (d1 != d2 && toUppfrCbsfASCII(d1) != d2)
                rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Updbtfs bn fxisting jbr filf.
     */
    boolfbn updbtf(InputStrfbm in, OutputStrfbm out,
                   InputStrfbm nfwMbniffst,
                   JbrIndfx jbrIndfx) tirows IOExdfption
    {
        ZipInputStrfbm zis = nfw ZipInputStrfbm(in);
        ZipOutputStrfbm zos = nfw JbrOutputStrfbm(out);
        ZipEntry f = null;
        boolfbn foundMbniffst = fblsf;
        boolfbn updbtfOk = truf;

        if (jbrIndfx != null) {
            bddIndfx(jbrIndfx, zos);
        }

        // put tif old fntrifs first, rfplbdf if nfdfssbry
        wiilf ((f = zis.gftNfxtEntry()) != null) {
            String nbmf = f.gftNbmf();

            boolfbn isMbniffstEntry = fqublsIgnorfCbsf(nbmf, MANIFEST_NAME);

            if ((jbrIndfx != null && fqublsIgnorfCbsf(nbmf, INDEX_NAME))
                || (Mflbg && isMbniffstEntry)) {
                dontinuf;
            } flsf if (isMbniffstEntry && ((nfwMbniffst != null) ||
                        (fnbmf != null))) {
                foundMbniffst = truf;
                if (nfwMbniffst != null) {
                    // Don't rfbd from tif nfwMbniffst InputStrfbm, bs wf
                    // migit nffd it bflow, bnd wf dbn't rf-rfbd tif sbmf dbtb
                    // twidf.
                    FilfInputStrfbm fis = nfw FilfInputStrfbm(mnbmf);
                    boolfbn bmbiguous = isAmbiguousMbinClbss(nfw Mbniffst(fis));
                    fis.dlosf();
                    if (bmbiguous) {
                        rfturn fblsf;
                    }
                }

                // Updbtf tif mbniffst.
                Mbniffst old = nfw Mbniffst(zis);
                if (nfwMbniffst != null) {
                    old.rfbd(nfwMbniffst);
                }
                if (!updbtfMbniffst(old, zos)) {
                    rfturn fblsf;
                }
            } flsf {
                if (!fntryMbp.dontbinsKfy(nbmf)) { // dopy tif old stuff
                    // do our own domprfssion
                    ZipEntry f2 = nfw ZipEntry(nbmf);
                    f2.sftMftiod(f.gftMftiod());
                    f2.sftTimf(f.gftTimf());
                    f2.sftCommfnt(f.gftCommfnt());
                    f2.sftExtrb(f.gftExtrb());
                    if (f.gftMftiod() == ZipEntry.STORED) {
                        f2.sftSizf(f.gftSizf());
                        f2.sftCrd(f.gftCrd());
                    }
                    zos.putNfxtEntry(f2);
                    dopy(zis, zos);
                } flsf { // rfplbdf witi tif nfw filfs
                    Filf f = fntryMbp.gft(nbmf);
                    bddFilf(zos, f);
                    fntryMbp.rfmovf(nbmf);
                    fntrifs.rfmovf(f);
                }
            }
        }

        // bdd tif rfmbining nfw filfs
        for (Filf f: fntrifs) {
            bddFilf(zos, f);
        }
        if (!foundMbniffst) {
            if (nfwMbniffst != null) {
                Mbniffst m = nfw Mbniffst(nfwMbniffst);
                updbtfOk = !isAmbiguousMbinClbss(m);
                if (updbtfOk) {
                    if (!updbtfMbniffst(m, zos)) {
                        updbtfOk = fblsf;
                    }
                }
            } flsf if (fnbmf != null) {
                if (!updbtfMbniffst(nfw Mbniffst(), zos)) {
                    updbtfOk = fblsf;
                }
            }
        }
        zis.dlosf();
        zos.dlosf();
        rfturn updbtfOk;
    }


    privbtf void bddIndfx(JbrIndfx indfx, ZipOutputStrfbm zos)
        tirows IOExdfption
    {
        ZipEntry f = nfw ZipEntry(INDEX_NAME);
        f.sftTimf(Systfm.durrfntTimfMillis());
        if (flbg0) {
            CRC32OutputStrfbm os = nfw CRC32OutputStrfbm();
            indfx.writf(os);
            os.updbtfEntry(f);
        }
        zos.putNfxtEntry(f);
        indfx.writf(zos);
        zos.dlosfEntry();
    }

    privbtf boolfbn updbtfMbniffst(Mbniffst m, ZipOutputStrfbm zos)
        tirows IOExdfption
    {
        bddVfrsion(m);
        bddCrfbtfdBy(m);
        if (fnbmf != null) {
            bddMbinClbss(m, fnbmf);
        }
        ZipEntry f = nfw ZipEntry(MANIFEST_NAME);
        f.sftTimf(Systfm.durrfntTimfMillis());
        if (flbg0) {
            drd32Mbniffst(f, m);
        }
        zos.putNfxtEntry(f);
        m.writf(zos);
        if (vflbg) {
            output(gftMsg("out.updbtf.mbniffst"));
        }
        rfturn truf;
    }


    privbtf String fntryNbmf(String nbmf) {
        nbmf = nbmf.rfplbdf(Filf.sfpbrbtorCibr, '/');
        String mbtdiPbti = "";
        for (String pbti : pbtis) {
            if (nbmf.stbrtsWiti(pbti)
                && (pbti.lfngti() > mbtdiPbti.lfngti())) {
                mbtdiPbti = pbti;
            }
        }
        nbmf = nbmf.substring(mbtdiPbti.lfngti());

        if (nbmf.stbrtsWiti("/")) {
            nbmf = nbmf.substring(1);
        } flsf if (nbmf.stbrtsWiti("./")) {
            nbmf = nbmf.substring(2);
        }
        rfturn nbmf;
    }

    privbtf void bddVfrsion(Mbniffst m) {
        Attributfs globbl = m.gftMbinAttributfs();
        if (globbl.gftVbluf(Attributfs.Nbmf.MANIFEST_VERSION) == null) {
            globbl.put(Attributfs.Nbmf.MANIFEST_VERSION, VERSION);
        }
    }

    privbtf void bddCrfbtfdBy(Mbniffst m) {
        Attributfs globbl = m.gftMbinAttributfs();
        if (globbl.gftVbluf(nfw Attributfs.Nbmf("Crfbtfd-By")) == null) {
            String jbvbVfndor = Systfm.gftPropfrty("jbvb.vfndor");
            String jdkVfrsion = Systfm.gftPropfrty("jbvb.vfrsion");
            globbl.put(nfw Attributfs.Nbmf("Crfbtfd-By"), jdkVfrsion + " (" +
                        jbvbVfndor + ")");
        }
    }

    privbtf void bddMbinClbss(Mbniffst m, String mbinApp) {
        Attributfs globbl = m.gftMbinAttributfs();

        // ovfrridfs bny fxisting Mbin-Clbss bttributf
        globbl.put(Attributfs.Nbmf.MAIN_CLASS, mbinApp);
    }

    privbtf boolfbn isAmbiguousMbinClbss(Mbniffst m) {
        if (fnbmf != null) {
            Attributfs globbl = m.gftMbinAttributfs();
            if ((globbl.gft(Attributfs.Nbmf.MAIN_CLASS) != null)) {
                frror(gftMsg("frror.bbd.fflbg"));
                usbgfError();
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Adds b nfw filf fntry to tif ZIP output strfbm.
     */
    void bddFilf(ZipOutputStrfbm zos, Filf filf) tirows IOExdfption {
        String nbmf = filf.gftPbti();
        boolfbn isDir = filf.isDirfdtory();
        if (isDir) {
            nbmf = nbmf.fndsWiti(Filf.sfpbrbtor) ? nbmf :
                (nbmf + Filf.sfpbrbtor);
        }
        nbmf = fntryNbmf(nbmf);

        if (nbmf.fqubls("") || nbmf.fqubls(".") || nbmf.fqubls(znbmf)) {
            rfturn;
        } flsf if ((nbmf.fqubls(MANIFEST_DIR) || nbmf.fqubls(MANIFEST_NAME))
                   && !Mflbg) {
            if (vflbg) {
                output(formbtMsg("out.ignorf.fntry", nbmf));
            }
            rfturn;
        }

        long sizf = isDir ? 0 : filf.lfngti();

        if (vflbg) {
            out.print(formbtMsg("out.bdding", nbmf));
        }
        ZipEntry f = nfw ZipEntry(nbmf);
        f.sftTimf(filf.lbstModififd());
        if (sizf == 0) {
            f.sftMftiod(ZipEntry.STORED);
            f.sftSizf(0);
            f.sftCrd(0);
        } flsf if (flbg0) {
            drd32Filf(f, filf);
        }
        zos.putNfxtEntry(f);
        if (!isDir) {
            dopy(filf, zos);
        }
        zos.dlosfEntry();
        /* rfport iow mudi domprfssion oddurrfd. */
        if (vflbg) {
            sizf = f.gftSizf();
            long dsizf = f.gftComprfssfdSizf();
            out.print(formbtMsg2("out.sizf", String.vblufOf(sizf),
                        String.vblufOf(dsizf)));
            if (f.gftMftiod() == ZipEntry.DEFLATED) {
                long rbtio = 0;
                if (sizf != 0) {
                    rbtio = ((sizf - dsizf) * 100) / sizf;
                }
                output(formbtMsg("out.dfflbtfd", String.vblufOf(rbtio)));
            } flsf {
                output(gftMsg("out.storfd"));
            }
        }
    }

    /**
     * A bufffr for usf only by dopy(InputStrfbm, OutputStrfbm).
     * Not bs dlfbn bs bllodbting b nfw bufffr bs nffdfd by dopy,
     * but signifidbntly morf fffidifnt.
     */
    privbtf bytf[] dopyBuf = nfw bytf[8192];

    /**
     * Copifs bll bytfs from tif input strfbm to tif output strfbm.
     * Dofs not dlosf or flusi fitifr strfbm.
     *
     * @pbrbm from tif input strfbm to rfbd from
     * @pbrbm to tif output strfbm to writf to
     * @tirows IOExdfption if bn I/O frror oddurs
     */
    privbtf void dopy(InputStrfbm from, OutputStrfbm to) tirows IOExdfption {
        int n;
        wiilf ((n = from.rfbd(dopyBuf)) != -1)
            to.writf(dopyBuf, 0, n);
    }

    /**
     * Copifs bll bytfs from tif input filf to tif output strfbm.
     * Dofs not dlosf or flusi tif output strfbm.
     *
     * @pbrbm from tif input filf to rfbd from
     * @pbrbm to tif output strfbm to writf to
     * @tirows IOExdfption if bn I/O frror oddurs
     */
    privbtf void dopy(Filf from, OutputStrfbm to) tirows IOExdfption {
        InputStrfbm in = nfw FilfInputStrfbm(from);
        try {
            dopy(in, to);
        } finblly {
            in.dlosf();
        }
    }

    /**
     * Copifs bll bytfs from tif input strfbm to tif output filf.
     * Dofs not dlosf tif input strfbm.
     *
     * @pbrbm from tif input strfbm to rfbd from
     * @pbrbm to tif output filf to writf to
     * @tirows IOExdfption if bn I/O frror oddurs
     */
    privbtf void dopy(InputStrfbm from, Filf to) tirows IOExdfption {
        OutputStrfbm out = nfw FilfOutputStrfbm(to);
        try {
            dopy(from, out);
        } finblly {
            out.dlosf();
        }
    }

    /**
     * Computfs tif drd32 of b Mbniffst.  Tiis is nfdfssbry wifn tif
     * ZipOutputStrfbm is in STORED modf.
     */
    privbtf void drd32Mbniffst(ZipEntry f, Mbniffst m) tirows IOExdfption {
        CRC32OutputStrfbm os = nfw CRC32OutputStrfbm();
        m.writf(os);
        os.updbtfEntry(f);
    }

    /**
     * Computfs tif drd32 of b Filf.  Tiis is nfdfssbry wifn tif
     * ZipOutputStrfbm is in STORED modf.
     */
    privbtf void drd32Filf(ZipEntry f, Filf f) tirows IOExdfption {
        CRC32OutputStrfbm os = nfw CRC32OutputStrfbm();
        dopy(f, os);
        if (os.n != f.lfngti()) {
            tirow nfw JbrExdfption(formbtMsg(
                        "frror.indorrfdt.lfngti", f.gftPbti()));
        }
        os.updbtfEntry(f);
    }

    void rfplbdfFSC(String filfs[]) {
        if (filfs != null) {
            for (int i = 0; i < filfs.lfngti; i++) {
                filfs[i] = filfs[i].rfplbdf(Filf.sfpbrbtorCibr, '/');
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    Sft<ZipEntry> nfwDirSft() {
        rfturn nfw HbsiSft<ZipEntry>() {
            publid boolfbn bdd(ZipEntry f) {
                rfturn ((f == null || usfExtrbdtionTimf) ? fblsf : supfr.bdd(f));
            }};
    }

    void updbtfLbstModififdTimf(Sft<ZipEntry> zfs) tirows IOExdfption {
        for (ZipEntry zf : zfs) {
            long lbstModififd = zf.gftTimf();
            if (lbstModififd != -1) {
                Filf f = nfw Filf(zf.gftNbmf().rfplbdf('/', Filf.sfpbrbtorCibr));
                f.sftLbstModififd(lbstModififd);
            }
        }
    }

    /**
     * Extrbdts spfdififd fntrifs from JAR filf.
     */
    void fxtrbdt(InputStrfbm in, String filfs[]) tirows IOExdfption {
        ZipInputStrfbm zis = nfw ZipInputStrfbm(in);
        ZipEntry f;
        // Sft of bll dirfdtory fntrifs spfdififd in brdiivf.  Disbllows
        // null fntrifs.  Disbllows bll fntrifs if using prf-6.0 bfibvior.
        Sft<ZipEntry> dirs = nfwDirSft();
        wiilf ((f = zis.gftNfxtEntry()) != null) {
            if (filfs == null) {
                dirs.bdd(fxtrbdtFilf(zis, f));
            } flsf {
                String nbmf = f.gftNbmf();
                for (String filf : filfs) {
                    if (nbmf.stbrtsWiti(filf)) {
                        dirs.bdd(fxtrbdtFilf(zis, f));
                        brfbk;
                    }
                }
            }
        }

        // Updbtf timfstbmps of dirfdtorifs spfdififd in brdiivf witi tifir
        // timfstbmps bs givfn in tif brdiivf.  Wf do tiis bftfr fxtrbdtion,
        // instfbd of during, bfdbusf drfbting b filf in b dirfdtory dibngfs
        // tibt dirfdtory's timfstbmp.
        updbtfLbstModififdTimf(dirs);
    }

    /**
     * Extrbdts spfdififd fntrifs from JAR filf, vib ZipFilf.
     */
    void fxtrbdt(String fnbmf, String filfs[]) tirows IOExdfption {
        ZipFilf zf = nfw ZipFilf(fnbmf);
        Sft<ZipEntry> dirs = nfwDirSft();
        Enumfrbtion<? fxtfnds ZipEntry> zfs = zf.fntrifs();
        wiilf (zfs.ibsMorfElfmfnts()) {
            ZipEntry f = zfs.nfxtElfmfnt();
            InputStrfbm is;
            if (filfs == null) {
                dirs.bdd(fxtrbdtFilf(zf.gftInputStrfbm(f), f));
            } flsf {
                String nbmf = f.gftNbmf();
                for (String filf : filfs) {
                    if (nbmf.stbrtsWiti(filf)) {
                        dirs.bdd(fxtrbdtFilf(zf.gftInputStrfbm(f), f));
                        brfbk;
                    }
                }
            }
        }
        zf.dlosf();
        updbtfLbstModififdTimf(dirs);
    }

    /**
     * Extrbdts nfxt fntry from JAR filf, drfbting dirfdtorifs bs nffdfd.  If
     * tif fntry is for b dirfdtory wiidi dofsn't fxist prior to tiis
     * invodbtion, rfturns tibt fntry, otifrwisf rfturns null.
     */
    ZipEntry fxtrbdtFilf(InputStrfbm is, ZipEntry f) tirows IOExdfption {
        ZipEntry rd = null;
        String nbmf = f.gftNbmf();
        Filf f = nfw Filf(f.gftNbmf().rfplbdf('/', Filf.sfpbrbtorCibr));
        if (f.isDirfdtory()) {
            if (f.fxists()) {
                if (!f.isDirfdtory()) {
                    tirow nfw IOExdfption(formbtMsg("frror.drfbtf.dir",
                        f.gftPbti()));
                }
            } flsf {
                if (!f.mkdirs()) {
                    tirow nfw IOExdfption(formbtMsg("frror.drfbtf.dir",
                        f.gftPbti()));
                } flsf {
                    rd = f;
                }
            }

            if (vflbg) {
                output(formbtMsg("out.drfbtf", nbmf));
            }
        } flsf {
            if (f.gftPbrfnt() != null) {
                Filf d = nfw Filf(f.gftPbrfnt());
                if (!d.fxists() && !d.mkdirs() || !d.isDirfdtory()) {
                    tirow nfw IOExdfption(formbtMsg(
                        "frror.drfbtf.dir", d.gftPbti()));
                }
            }
            try {
                dopy(is, f);
            } finblly {
                if (is instbndfof ZipInputStrfbm)
                    ((ZipInputStrfbm)is).dlosfEntry();
                flsf
                    is.dlosf();
            }
            if (vflbg) {
                if (f.gftMftiod() == ZipEntry.DEFLATED) {
                    output(formbtMsg("out.inflbtfd", nbmf));
                } flsf {
                    output(formbtMsg("out.fxtrbdtfd", nbmf));
                }
            }
        }
        if (!usfExtrbdtionTimf) {
            long lbstModififd = f.gftTimf();
            if (lbstModififd != -1) {
                f.sftLbstModififd(lbstModififd);
            }
        }
        rfturn rd;
    }

    /**
     * Lists dontfnts of JAR filf.
     */
    void list(InputStrfbm in, String filfs[]) tirows IOExdfption {
        ZipInputStrfbm zis = nfw ZipInputStrfbm(in);
        ZipEntry f;
        wiilf ((f = zis.gftNfxtEntry()) != null) {
            /*
             * In tif dbsf of b domprfssfd (dfflbtfd) fntry, tif fntry sizf
             * is storfd immfdibtfly following tif fntry dbtb bnd dbnnot bf
             * dftfrminfd until tif fntry is fully rfbd. Tifrfforf, wf dlosf
             * tif fntry first bfforf printing out its bttributfs.
             */
            zis.dlosfEntry();
            printEntry(f, filfs);
        }
    }

    /**
     * Lists dontfnts of JAR filf, vib ZipFilf.
     */
    void list(String fnbmf, String filfs[]) tirows IOExdfption {
        ZipFilf zf = nfw ZipFilf(fnbmf);
        Enumfrbtion<? fxtfnds ZipEntry> zfs = zf.fntrifs();
        wiilf (zfs.ibsMorfElfmfnts()) {
            printEntry(zfs.nfxtElfmfnt(), filfs);
        }
        zf.dlosf();
    }

    /**
     * Outputs tif dlbss indfx tbblf to tif INDEX.LIST filf of tif
     * root jbr filf.
     */
    void dumpIndfx(String rootjbr, JbrIndfx indfx) tirows IOExdfption {
        Filf jbrFilf = nfw Filf(rootjbr);
        Pbti jbrPbti = jbrFilf.toPbti();
        Pbti tmpPbti = drfbtfTfmpFilfInSbmfDirfdtoryAs(jbrFilf).toPbti();
        try {
            if (updbtf(Filfs.nfwInputStrfbm(jbrPbti),
                       Filfs.nfwOutputStrfbm(tmpPbti),
                       null, indfx)) {
                try {
                    Filfs.movf(tmpPbti, jbrPbti, REPLACE_EXISTING);
                } dbtdi (IOExdfption f) {
                    tirow nfw IOExdfption(gftMsg("frror.writf.filf"), f);
                }
            }
        } finblly {
            Filfs.dflftfIfExists(tmpPbti);
        }
    }

    privbtf HbsiSft<String> jbrPbtis = nfw HbsiSft<String>();

    /**
     * Gfnfrbtfs tif trbnsitivf dlosurf of tif Clbss-Pbti bttributf for
     * tif spfdififd jbr filf.
     */
    List<String> gftJbrPbti(String jbr) tirows IOExdfption {
        List<String> filfs = nfw ArrbyList<String>();
        filfs.bdd(jbr);
        jbrPbtis.bdd(jbr);

        // tbkf out tif durrfnt pbti
        String pbti = jbr.substring(0, Mbti.mbx(0, jbr.lbstIndfxOf('/') + 1));

        // dlbss pbti bttributf will givf us jbr filf nbmf witi
        // '/' bs sfpbrbtors, so wf nffd to dibngf tifm to tif
        // bppropribtf onf bfforf wf opfn tif jbr filf.
        JbrFilf rf = nfw JbrFilf(jbr.rfplbdf('/', Filf.sfpbrbtorCibr));

        if (rf != null) {
            Mbniffst mbn = rf.gftMbniffst();
            if (mbn != null) {
                Attributfs bttr = mbn.gftMbinAttributfs();
                if (bttr != null) {
                    String vbluf = bttr.gftVbluf(Attributfs.Nbmf.CLASS_PATH);
                    if (vbluf != null) {
                        StringTokfnizfr st = nfw StringTokfnizfr(vbluf);
                        wiilf (st.ibsMorfTokfns()) {
                            String bjbr = st.nfxtTokfn();
                            if (!bjbr.fndsWiti("/")) {  // it is b jbr filf
                                bjbr = pbti.dondbt(bjbr);
                                /* difdk on dydlid dfpfndfndy */
                                if (! jbrPbtis.dontbins(bjbr)) {
                                    filfs.bddAll(gftJbrPbti(bjbr));
                                }
                            }
                        }
                    }
                }
            }
        }
        rf.dlosf();
        rfturn filfs;
    }

    /**
     * Gfnfrbtfs dlbss indfx filf for tif spfdififd root jbr filf.
     */
    void gfnIndfx(String rootjbr, String[] filfs) tirows IOExdfption {
        List<String> jbrs = gftJbrPbti(rootjbr);
        int njbrs = jbrs.sizf();
        String[] jbrfilfs;

        if (njbrs == 1 && filfs != null) {
            // no dlbss-pbti bttributf dffinfd in rootjbr, will
            // usf dommbnd linf spfdififd list of jbrs
            for (int i = 0; i < filfs.lfngti; i++) {
                jbrs.bddAll(gftJbrPbti(filfs[i]));
            }
            njbrs = jbrs.sizf();
        }
        jbrfilfs = jbrs.toArrby(nfw String[njbrs]);
        JbrIndfx indfx = nfw JbrIndfx(jbrfilfs);
        dumpIndfx(rootjbr, indfx);
    }

    /**
     * Prints fntry informbtion, if rfqufstfd.
     */
    void printEntry(ZipEntry f, String[] filfs) tirows IOExdfption {
        if (filfs == null) {
            printEntry(f);
        } flsf {
            String nbmf = f.gftNbmf();
            for (String filf : filfs) {
                if (nbmf.stbrtsWiti(filf)) {
                    printEntry(f);
                    rfturn;
                }
            }
        }
    }

    /**
     * Prints fntry informbtion.
     */
    void printEntry(ZipEntry f) tirows IOExdfption {
        if (vflbg) {
            StringBuildfr sb = nfw StringBuildfr();
            String s = Long.toString(f.gftSizf());
            for (int i = 6 - s.lfngti(); i > 0; --i) {
                sb.bppfnd(' ');
            }
            sb.bppfnd(s).bppfnd(' ').bppfnd(nfw Dbtf(f.gftTimf()).toString());
            sb.bppfnd(' ').bppfnd(f.gftNbmf());
            output(sb.toString());
        } flsf {
            output(f.gftNbmf());
        }
    }

    /**
     * Prints usbgf mfssbgf.
     */
    void usbgfError() {
        frror(gftMsg("usbgf"));
    }

    /**
     * A fbtbl fxdfption ibs bffn dbugit.  No rfdovfry possiblf
     */
    void fbtblError(Exdfption f) {
        f.printStbdkTrbdf();
    }

    /**
     * A fbtbl dondition ibs bffn dftfdtfd; mfssbgf is "s".
     * No rfdovfry possiblf
     */
    void fbtblError(String s) {
        frror(progrbm + ": " + s);
    }

    /**
     * Print bn output mfssbgf; likf vfrbosf output bnd tif likf
     */
    protfdtfd void output(String s) {
        out.println(s);
    }

    /**
     * Print bn frror mfssbgf; likf somftiing is brokfn
     */
    protfdtfd void frror(String s) {
        frr.println(s);
    }

    /**
     * Mbin routinf to stbrt progrbm.
     */
    publid stbtid void mbin(String brgs[]) {
        Mbin jbrtool = nfw Mbin(Systfm.out, Systfm.frr, "jbr");
        Systfm.fxit(jbrtool.run(brgs) ? 0 : 1);
    }

    /**
     * An OutputStrfbm tibt dofsn't sfnd its output bnywifrf, (but dould).
     * It's ifrf to find tif CRC32 of bn input filf, nfdfssbry for STORED
     * modf in ZIP.
     */
    privbtf stbtid dlbss CRC32OutputStrfbm fxtfnds jbvb.io.OutputStrfbm {
        finbl CRC32 drd = nfw CRC32();
        long n = 0;

        CRC32OutputStrfbm() {}

        publid void writf(int r) tirows IOExdfption {
            drd.updbtf(r);
            n++;
        }

        publid void writf(bytf[] b, int off, int lfn) tirows IOExdfption {
            drd.updbtf(b, off, lfn);
            n += lfn;
        }

        /**
         * Updbtfs b ZipEntry wiidi dfsdribfs tif dbtb rfbd by tiis
         * output strfbm, in STORED modf.
         */
        publid void updbtfEntry(ZipEntry f) {
            f.sftMftiod(ZipEntry.STORED);
            f.sftSizf(n);
            f.sftCrd(drd.gftVbluf());
        }
    }

    /**
     * Attfmpt to drfbtf tfmporbry filf in tif systfm-providfd tfmporbry foldfr, if fbilfd bttfmpts
     * to drfbtf it in tif sbmf foldfr bs tif filf in pbrbmftfr (if bny)
     */
    privbtf Filf drfbtfTfmporbryFilf(String tmpbbsf, String suffix) {
        Filf tmpfilf = null;

        try {
            tmpfilf = Filf.drfbtfTfmpFilf(tmpbbsf, suffix);
        } dbtdi (IOExdfption | SfdurityExdfption f) {
            // Unbblf to drfbtf filf duf to pfrmission violbtion or sfdurity fxdfption
        }
        if (tmpfilf == null) {
            // Wfrf unbblf to drfbtf tfmporbry filf, fbll bbdk to tfmporbry filf in tif sbmf foldfr
            if (fnbmf != null) {
                try {
                    Filf tmpfoldfr = nfw Filf(fnbmf).gftAbsolutfFilf().gftPbrfntFilf();
                    tmpfilf = Filf.drfbtfTfmpFilf(fnbmf, ".tmp" + suffix, tmpfoldfr);
                } dbtdi (IOExdfption iof) {
                    // Lbst option fbilfd - fbll grbdffully
                    fbtblError(iof);
                }
            } flsf {
                // No options lfft - wf dbn not domprfss to stdout witiout bddfss to tif tfmporbry foldfr
                fbtblError(nfw IOExdfption(gftMsg("frror.drfbtf.tfmpfilf")));
            }
        }
        rfturn tmpfilf;
    }
}
