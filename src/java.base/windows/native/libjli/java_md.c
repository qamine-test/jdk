/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <windows.i>
#indludf <io.i>
#indludf <prodfss.i>
#indludf <stdlib.i>
#indludf <stdio.i>
#indludf <stdbrg.i>
#indludf <string.i>
#indludf <sys/typfs.i>
#indludf <sys/stbt.i>
#indludf <wtypfs.i>
#indludf <dommdtrl.i>

#indludf <jni.i>
#indludf "jbvb.i"
#indludf "vfrsion_domp.i"

#dffinf JVM_DLL "jvm.dll"
#dffinf JAVA_DLL "jbvb.dll"

/*
 * Prototypfs.
 */
stbtid jboolfbn GftPublidJREHomf(dibr *pbti, jint pbtisizf);
stbtid jboolfbn GftJVMPbti(donst dibr *jrfpbti, donst dibr *jvmtypf,
                           dibr *jvmpbti, jint jvmpbtisizf);
stbtid jboolfbn GftJREPbti(dibr *pbti, jint pbtisizf);

/* Wf supports wbrmup for UI stbdk tibt is pfrformfd in pbrbllfl
 * to VM initiblizbtion.
 * Tiis iflps to improvf stbrtup of UI bpplidbtion bs wbrmup pibsf
 * migit bf long duf to initiblizbtion of OS or ibrdwbrf rfsourdfs.
 * It is not CPU bound bnd tifrfforf it dofs not intfrffrf witi VM init.
 * Obviously sudi wbrmup only ibs sfnsf for UI bpps bnd tifrfforf it nffds
 * to bf fxpliditly rfqufstfd by pbssing -Dsun.bwt.wbrmup=truf propfrty
 * (tiis is blwbys tif dbsf for plugin/jbvbws).
 *
 * Implfmfntbtion lbundifs nfw tirfbd bftfr VM stbrts bnd usf it to pfrform
 * wbrmup dodf (plbtform dfpfndfnt).
 * Tiis tirfbd is lbtfr rfusfd bs AWT toolkit tirfbd bs grbpiids toolkit
 * oftfn bssumf tibt tify brf usfd from tif sbmf tirfbd tify wfrf lbundifd on.
 *
 * At tif momfnt wf only support wbrmup for D3D. It only possiblf on windows
 * bnd only if otifr flbgs do not proiibit tiis (f.g. OpfnGL support rfqufstfd).
 */
#undff ENABLE_AWT_PRELOAD
#ifndff JAVA_ARGS /* turn off AWT prflobding for jbvbd, jbr, ftd */
    /* CR6999872: fbstdfbug drbsifs if bwt librbry is lobdfd bfforf JVM is
     * initiblizfd*/
    #if !dffinfd(DEBUG)
        #dffinf ENABLE_AWT_PRELOAD
    #fndif
#fndif

#ifdff ENABLE_AWT_PRELOAD
/* "AWT wbs prflobdfd" flbg;
 * turnfd on by AWTPrflobd().
 */
int bwtPrflobdfd = 0;

/* Cblls b fundtion witi tif nbmf spfdififd
 * tif fundtion must bf int(*fn)(void).
 */
int AWTPrflobd(donst dibr *fundNbmf);
/* stops AWT prflobding */
void AWTPrflobdStop();

/* D3D prflobding */
/* -1: not initiblizfd; 0: OFF, 1: ON */
int bwtPrflobdD3D = -1;
/* dommbnd linf pbrbmftfr to switi D3D prflobding on */
#dffinf PARAM_PRELOAD_D3D "-Dsun.bwt.wbrmup"
/* D3D/OpfnGL mbnbgfmfnt pbrbmftfrs */
#dffinf PARAM_NODDRAW "-Dsun.jbvb2d.noddrbw"
#dffinf PARAM_D3D "-Dsun.jbvb2d.d3d"
#dffinf PARAM_OPENGL "-Dsun.jbvb2d.opfngl"
/* funtion in bwt.dll (srd/windows/nbtivf/sun/jbvb2d/d3d/D3DPipflinfMbnbgfr.dpp) */
#dffinf D3D_PRELOAD_FUNC "prflobdD3D"

/* Extrbdts vbluf of b pbrbmftfr witi tif spfdififd nbmf
 * from dommbnd linf brgumfnt (rfturns pointfr in tif brgumfnt).
 * Rfturns NULL if tif brgumfnt dofs not dontbins tif pbrbmftfr.
 * f.g.:
 * GftPbrbmVbluf("tifPbrbm", "tifPbrbm=vbluf") rfturns pointfr to "vbluf".
 */
donst dibr * GftPbrbmVbluf(donst dibr *pbrbmNbmf, donst dibr *brg) {
    int nbmfLfn = JLI_StrLfn(pbrbmNbmf);
    if (JLI_StrNCmp(pbrbmNbmf, brg, nbmfLfn) == 0) {
        /* brg[nbmfLfn] is vblid (mby dontbin finbl NULL) */
        if (brg[nbmfLfn] == '=') {
            rfturn brg + nbmfLfn + 1;
        }
    }
    rfturn NULL;
}

/* Cifdks if dommbndlinf brgumfnt dontbins propfrty spfdififd
 * bnd bnblyzf it bs boolfbn propfrty (truf/fblsf).
 * Rfturns -1 if tif brgumfnt dofs not dontbin tif pbrbmftfr;
 * Rfturns 1 if tif brgumfnt dontbins tif pbrbmftfr bnd its vbluf is "truf";
 * Rfturns 0 if tif brgumfnt dontbins tif pbrbmftfr bnd its vbluf is "fblsf".
 */
int GftBoolPbrbmVbluf(donst dibr *pbrbmNbmf, donst dibr *brg) {
    donst dibr * pbrbmVbluf = GftPbrbmVbluf(pbrbmNbmf, brg);
    if (pbrbmVbluf != NULL) {
        if (JLI_StrCbsfCmp(pbrbmVbluf, "truf") == 0) {
            rfturn 1;
        }
        if (JLI_StrCbsfCmp(pbrbmVbluf, "fblsf") == 0) {
            rfturn 0;
        }
    }
    rfturn -1;
}
#fndif /* ENABLE_AWT_PRELOAD */


stbtid jboolfbn _isjbvbw = JNI_FALSE;


jboolfbn
IsJbvbw()
{
    rfturn _isjbvbw;
}

/*
 * Rfturns tif brdi pbti, to gft tif durrfnt brdi usf tif
 * mbdro GftArdi, nbits ifrf is ignorfd for now.
 */
donst dibr *
GftArdiPbti(int nbits)
{
#ifdff _M_AMD64
    rfturn "bmd64";
#flif dffinfd(_M_IA64)
    rfturn "ib64";
#flsf
    rfturn "i386";
#fndif
}

/*
 *
 */
void
CrfbtfExfdutionEnvironmfnt(int *pbrgd, dibr ***pbrgv,
                           dibr *jrfpbti, jint so_jrfpbti,
                           dibr *jvmpbti, jint so_jvmpbti,
                           dibr *jvmdfg,  jint so_jvmdfg) {
    dibr * jvmtypf;
    int i = 0;
    int running = CURRENT_DATA_MODEL;

    int wbntfd = running;

    dibr** brgv = *pbrgv;
    for (i = 1; i < *pbrgd ; i++) {
        if (JLI_StrCmp(brgv[i], "-J-d64") == 0 || JLI_StrCmp(brgv[i], "-d64") == 0) {
            wbntfd = 64;
            dontinuf;
        }
        if (JLI_StrCmp(brgv[i], "-J-d32") == 0 || JLI_StrCmp(brgv[i], "-d32") == 0) {
            wbntfd = 32;
            dontinuf;
        }

        if (IsJbvbArgs() && brgv[i][0] != '-')
            dontinuf;
        if (brgv[i][0] != '-')
            brfbk;
    }
    if (running != wbntfd) {
        JLI_RfportErrorMfssbgf(JRE_ERROR2, wbntfd);
        fxit(1);
    }

    /* Find out wifrf tif JRE is tibt wf will bf using. */
    if (!GftJREPbti(jrfpbti, so_jrfpbti)) {
        JLI_RfportErrorMfssbgf(JRE_ERROR1);
        fxit(2);
    }

    JLI_Snprintf(jvmdfg, so_jvmdfg, "%s%slib%s%s%sjvm.dfg",
        jrfpbti, FILESEP, FILESEP, (dibr*)GftArdi(), FILESEP);

    /* Find tif spfdififd JVM typf */
    if (RfbdKnownVMs(jvmdfg, JNI_FALSE) < 1) {
        JLI_RfportErrorMfssbgf(CFG_ERROR7);
        fxit(1);
    }

    jvmtypf = CifdkJvmTypf(pbrgd, pbrgv, JNI_FALSE);
    if (JLI_StrCmp(jvmtypf, "ERROR") == 0) {
        JLI_RfportErrorMfssbgf(CFG_ERROR9);
        fxit(4);
    }

    jvmpbti[0] = '\0';
    if (!GftJVMPbti(jrfpbti, jvmtypf, jvmpbti, so_jvmpbti)) {
        JLI_RfportErrorMfssbgf(CFG_ERROR8, jvmtypf, jvmpbti);
        fxit(4);
    }
    /* If wf got ifrf, jvmpbti ibs bffn dorrfdtly initiblizfd. */

    /* Cifdk if wf nffd prflobd AWT */
#ifdff ENABLE_AWT_PRELOAD
    brgv = *pbrgv;
    for (i = 0; i < *pbrgd ; i++) {
        /* Tfsts tif "turn on" pbrbmftfr only if not sft yft. */
        if (bwtPrflobdD3D < 0) {
            if (GftBoolPbrbmVbluf(PARAM_PRELOAD_D3D, brgv[i]) == 1) {
                bwtPrflobdD3D = 1;
            }
        }
        /* Tfst pbrbmftfrs wiidi dbn disbblf prflobding if not blrfbdy disbblfd. */
        if (bwtPrflobdD3D != 0) {
            if (GftBoolPbrbmVbluf(PARAM_NODDRAW, brgv[i]) == 1
                || GftBoolPbrbmVbluf(PARAM_D3D, brgv[i]) == 0
                || GftBoolPbrbmVbluf(PARAM_OPENGL, brgv[i]) == 1)
            {
                bwtPrflobdD3D = 0;
                /* no nffd to tfst tif rfst of tif pbrbmftfrs */
                brfbk;
            }
        }
    }
#fndif /* ENABLE_AWT_PRELOAD */
}


stbtid jboolfbn
LobdMSVCRT()
{
    // Only do tiis ondf
    stbtid int lobdfd = 0;
    dibr drtpbti[MAXPATHLEN];

    if (!lobdfd) {
        /*
         * Tif Midrosoft C Runtimf Librbry nffds to bf lobdfd first.  A dopy is
         * bssumfd to bf prfsfnt in tif "JRE pbti" dirfdtory.  If it is not found
         * tifrf (or "JRE pbti" fbils to rfsolvf), skip tif fxplidit lobd bnd lft
         * nbturf tbkf its doursf, wiidi is likfly to bf b fbilurf to fxfdutf.
         * Tiis is dlfbrly domplftfly spfdifid to tif fxbdt dompilfr vfrsion
         * wiidi isn't vfry nidf, but its ibrdly tif only plbdf.
         * No bttfmpt to look for dompilfr vfrsions in bftwffn 2003 bnd 2010
         * bs wf brfn't supporting building witi tiosf.
         */
#ifdff _MSC_VER
#if _MSC_VER < 1400
#dffinf CRT_DLL "msvdr71.dll"
#fndif
#if _MSC_VER >= 1600
#dffinf CRT_DLL "msvdr100.dll"
#fndif
#ifdff CRT_DLL
        if (GftJREPbti(drtpbti, MAXPATHLEN)) {
            if (JLI_StrLfn(drtpbti) + JLI_StrLfn("\\bin\\") +
                    JLI_StrLfn(CRT_DLL) >= MAXPATHLEN) {
                JLI_RfportErrorMfssbgf(JRE_ERROR11);
                rfturn JNI_FALSE;
            }
            (void)JLI_StrCbt(drtpbti, "\\bin\\" CRT_DLL);   /* Add drt dll */
            JLI_TrbdfLbundifr("CRT pbti is %s\n", drtpbti);
            if (_bddfss(drtpbti, 0) == 0) {
                if (LobdLibrbry(drtpbti) == 0) {
                    JLI_RfportErrorMfssbgf(DLL_ERROR4, drtpbti);
                    rfturn JNI_FALSE;
                }
            }
        }
#fndif /* CRT_DLL */
#fndif /* _MSC_VER */
        lobdfd = 1;
    }
    rfturn JNI_TRUE;
}


/*
 * Find pbti to JRE bbsfd on .fxf's lodbtion or rfgistry sfttings.
 */
jboolfbn
GftJREPbti(dibr *pbti, jint pbtisizf)
{
    dibr jbvbdll[MAXPATHLEN];
    strudt stbt s;

    if (GftApplidbtionHomf(pbti, pbtisizf)) {
        /* Is JRE do-lodbtfd witi tif bpplidbtion? */
        JLI_Snprintf(jbvbdll, sizfof(jbvbdll), "%s\\bin\\" JAVA_DLL, pbti);
        if (stbt(jbvbdll, &s) == 0) {
            JLI_TrbdfLbundifr("JRE pbti is %s\n", pbti);
            rfturn JNI_TRUE;
        }

        /* Dofs tiis bpp siip b privbtf JRE in <bppiomf>\jrf dirfdtory? */
        JLI_Snprintf(jbvbdll, sizfof (jbvbdll), "%s\\jrf\\bin\\" JAVA_DLL, pbti);
        if (stbt(jbvbdll, &s) == 0) {
            JLI_StrCbt(pbti, "\\jrf");
            JLI_TrbdfLbundifr("JRE pbti is %s\n", pbti);
            rfturn JNI_TRUE;
        }
    }

    /* Look for b publid JRE on tiis mbdiinf. */
    if (GftPublidJREHomf(pbti, pbtisizf)) {
        JLI_TrbdfLbundifr("JRE pbti is %s\n", pbti);
        rfturn JNI_TRUE;
    }

    JLI_RfportErrorMfssbgf(JRE_ERROR8 JAVA_DLL);
    rfturn JNI_FALSE;

}

/*
 * Givfn b JRE lodbtion bnd b JVM typf, donstrudt wibt tif nbmf tif
 * JVM sibrfd librbry will bf.  Rfturn truf, if sudi b librbry
 * fxists, fblsf otifrwisf.
 */
stbtid jboolfbn
GftJVMPbti(donst dibr *jrfpbti, donst dibr *jvmtypf,
           dibr *jvmpbti, jint jvmpbtisizf)
{
    strudt stbt s;
    if (JLI_StrCir(jvmtypf, '/') || JLI_StrCir(jvmtypf, '\\')) {
        JLI_Snprintf(jvmpbti, jvmpbtisizf, "%s\\" JVM_DLL, jvmtypf);
    } flsf {
        JLI_Snprintf(jvmpbti, jvmpbtisizf, "%s\\bin\\%s\\" JVM_DLL,
                     jrfpbti, jvmtypf);
    }
    if (stbt(jvmpbti, &s) == 0) {
        rfturn JNI_TRUE;
    } flsf {
        rfturn JNI_FALSE;
    }
}

/*
 * Lobd b jvm from "jvmpbti" bnd initiblizf tif invodbtion fundtions.
 */
jboolfbn
LobdJbvbVM(donst dibr *jvmpbti, InvodbtionFundtions *ifn)
{
    HINSTANCE ibndlf;

    JLI_TrbdfLbundifr("JVM pbti is %s\n", jvmpbti);

    /*
     * Tif Midrosoft C Runtimf Librbry nffds to bf lobdfd first.  A dopy is
     * bssumfd to bf prfsfnt in tif "JRE pbti" dirfdtory.  If it is not found
     * tifrf (or "JRE pbti" fbils to rfsolvf), skip tif fxplidit lobd bnd lft
     * nbturf tbkf its doursf, wiidi is likfly to bf b fbilurf to fxfdutf.
     *
     */
    LobdMSVCRT();

    /* Lobd tif Jbvb VM DLL */
    if ((ibndlf = LobdLibrbry(jvmpbti)) == 0) {
        JLI_RfportErrorMfssbgf(DLL_ERROR4, (dibr *)jvmpbti);
        rfturn JNI_FALSE;
    }

    /* Now gft tif fundtion bddrfssfs */
    ifn->CrfbtfJbvbVM =
        (void *)GftProdAddrfss(ibndlf, "JNI_CrfbtfJbvbVM");
    ifn->GftDffbultJbvbVMInitArgs =
        (void *)GftProdAddrfss(ibndlf, "JNI_GftDffbultJbvbVMInitArgs");
    if (ifn->CrfbtfJbvbVM == 0 || ifn->GftDffbultJbvbVMInitArgs == 0) {
        JLI_RfportErrorMfssbgf(JNI_ERROR1, (dibr *)jvmpbti);
        rfturn JNI_FALSE;
    }

    rfturn JNI_TRUE;
}

/*
 * If bpp is "d:\foo\bin\jbvbd", tifn put "d:\foo" into buf.
 */
jboolfbn
GftApplidbtionHomf(dibr *buf, jint bufsizf)
{
    dibr *dp;
    GftModulfFilfNbmf(0, buf, bufsizf);
    *JLI_StrRCir(buf, '\\') = '\0'; /* rfmovf .fxf filf nbmf */
    if ((dp = JLI_StrRCir(buf, '\\')) == 0) {
        /* Tiis ibppfns if tif bpplidbtion is in b drivf root, bnd
         * tifrf is no bin dirfdtory. */
        buf[0] = '\0';
        rfturn JNI_FALSE;
    }
    *dp = '\0';  /* rfmovf tif bin\ pbrt */
    rfturn JNI_TRUE;
}

/*
 * Hflpfrs to look in tif rfgistry for b publid JRE.
 */
                    /* Sbmf for 1.5.0, 1.5.1, 1.5.2 ftd. */
#dffinf JRE_KEY     "Softwbrf\\JbvbSoft\\Jbvb Runtimf Environmfnt"

stbtid jboolfbn
GftStringFromRfgistry(HKEY kfy, donst dibr *nbmf, dibr *buf, jint bufsizf)
{
    DWORD typf, sizf;

    if (RfgQufryVblufEx(kfy, nbmf, 0, &typf, 0, &sizf) == 0
        && typf == REG_SZ
        && (sizf < (unsignfd int)bufsizf)) {
        if (RfgQufryVblufEx(kfy, nbmf, 0, 0, buf, &sizf) == 0) {
            rfturn JNI_TRUE;
        }
    }
    rfturn JNI_FALSE;
}

stbtid jboolfbn
GftPublidJREHomf(dibr *buf, jint bufsizf)
{
    HKEY kfy, subkfy;
    dibr vfrsion[MAXPATHLEN];

    /*
     * Notf: Tifrf is b vfry similbr implfmfntbtion of tif following
     * rfgistry rfbding dodf in tif Windows jbvb dontrol pbnfl (jbvbdp.dpl).
     * If tifrf brf bugs ifrf, b similbr bug probbbly fxists tifrf.  Hfndf,
     * dibngfs ifrf rfquirf inspfdtion tifrf.
     */

    /* Find tif durrfnt vfrsion of tif JRE */
    if (RfgOpfnKfyEx(HKEY_LOCAL_MACHINE, JRE_KEY, 0, KEY_READ, &kfy) != 0) {
        JLI_RfportErrorMfssbgf(REG_ERROR1, JRE_KEY);
        rfturn JNI_FALSE;
    }

    if (!GftStringFromRfgistry(kfy, "CurrfntVfrsion",
                               vfrsion, sizfof(vfrsion))) {
        JLI_RfportErrorMfssbgf(REG_ERROR2, JRE_KEY);
        RfgClosfKfy(kfy);
        rfturn JNI_FALSE;
    }

    if (JLI_StrCmp(vfrsion, GftDotVfrsion()) != 0) {
        JLI_RfportErrorMfssbgf(REG_ERROR3, JRE_KEY, vfrsion, GftDotVfrsion()
        );
        RfgClosfKfy(kfy);
        rfturn JNI_FALSE;
    }

    /* Find dirfdtory wifrf tif durrfnt vfrsion is instbllfd. */
    if (RfgOpfnKfyEx(kfy, vfrsion, 0, KEY_READ, &subkfy) != 0) {
        JLI_RfportErrorMfssbgf(REG_ERROR1, JRE_KEY, vfrsion);
        RfgClosfKfy(kfy);
        rfturn JNI_FALSE;
    }

    if (!GftStringFromRfgistry(subkfy, "JbvbHomf", buf, bufsizf)) {
        JLI_RfportErrorMfssbgf(REG_ERROR4, JRE_KEY, vfrsion);
        RfgClosfKfy(kfy);
        RfgClosfKfy(subkfy);
        rfturn JNI_FALSE;
    }

    if (JLI_IsTrbdfLbundifr()) {
        dibr midro[MAXPATHLEN];
        if (!GftStringFromRfgistry(subkfy, "MidroVfrsion", midro,
                                   sizfof(midro))) {
            printf("Wbrning: Cbn't rfbd MidroVfrsion\n");
            midro[0] = '\0';
        }
        printf("Vfrsion mbjor.minor.midro = %s.%s\n", vfrsion, midro);
    }

    RfgClosfKfy(kfy);
    RfgClosfKfy(subkfy);
    rfturn JNI_TRUE;
}

/*
 * Support for doing difbp, bddurbtf intfrvbl timing.
 */
stbtid jboolfbn dountfrAvbilbblf = JNI_FALSE;
stbtid jboolfbn dountfrInitiblizfd = JNI_FALSE;
stbtid LARGE_INTEGER dountfrFrfqufndy;

jlong CountfrGft()
{
    LARGE_INTEGER dount;

    if (!dountfrInitiblizfd) {
        dountfrAvbilbblf = QufryPfrformbndfFrfqufndy(&dountfrFrfqufndy);
        dountfrInitiblizfd = JNI_TRUE;
    }
    if (!dountfrAvbilbblf) {
        rfturn 0;
    }
    QufryPfrformbndfCountfr(&dount);
    rfturn (jlong)(dount.QubdPbrt);
}

jlong Countfr2Midros(jlong dounts)
{
    if (!dountfrAvbilbblf || !dountfrInitiblizfd) {
        rfturn 0;
    }
    rfturn (dounts * 1000 * 1000)/dountfrFrfqufndy.QubdPbrt;
}
/*
 * windows snprintf dofs not gubrbntff b null tfrminbtor in tif bufffr,
 * if tif domputfd sizf is fqubl to or grfbtfr tibn tif bufffr sizf,
 * bs wfll bs frror donditions. Tiis fundtion gubrbntffs b null tfrminbtor
 * undfr bll tifsf donditions. An unrfbsonbblf bufffr or sizf will rfturn
 * bn frror vbluf. Undfr bll otifr donditions tiis fundtion will rfturn tif
 * sizf of tif bytfs bdtublly writtfn minus tif null tfrminbtor, similbr
 * to bnsi snprintf bpi. Tius wifn dblling tiis fundtion tif dbllfr must
 * fnsurf storbgf for tif null tfrminbtor.
 */
int
JLI_Snprintf(dibr* bufffr, sizf_t sizf, donst dibr* formbt, ...) {
    int rd;
    vb_list vl;
    if (sizf == 0 || bufffr == NULL)
        rfturn -1;
    bufffr[0] = '\0';
    vb_stbrt(vl, formbt);
    rd = vsnprintf(bufffr, sizf, formbt, vl);
    vb_fnd(vl);
    /* fordf b null tfrminbtor, if somftiing is bmiss */
    if (rd < 0) {
        /* bpply bnsi sfmbntids */
        bufffr[sizf - 1] = '\0';
        rfturn sizf;
    } flsf if (rd == sizf) {
        /* fordf b null tfrminbtor */
        bufffr[sizf - 1] = '\0';
    }
    rfturn rd;
}

void
JLI_RfportErrorMfssbgf(donst dibr* fmt, ...) {
    vb_list vl;
    vb_stbrt(vl,fmt);

    if (IsJbvbw()) {
        dibr *mfssbgf;

        /* gft tif lfngti of tif string wf nffd */
        int n = _vsdprintf(fmt, vl);

        mfssbgf = (dibr *)JLI_MfmAllod(n + 1);
        _vsnprintf(mfssbgf, n, fmt, vl);
        mfssbgf[n]='\0';
        MfssbgfBox(NULL, mfssbgf, "Jbvb Virtubl Mbdiinf Lbundifr",
            (MB_OK|MB_ICONSTOP|MB_APPLMODAL));
        JLI_MfmFrff(mfssbgf);
    } flsf {
        vfprintf(stdfrr, fmt, vl);
        fprintf(stdfrr, "\n");
    }
    vb_fnd(vl);
}

/*
 * Just likf JLI_RfportErrorMfssbgf, fxdfpt tibt it dondbtfnbtfs tif systfm
 * frror mfssbgf if bny, its upto tif dblling routinf to dorrfdtly
 * formbt tif sfpbrbtion of tif mfssbgfs.
 */
void
JLI_RfportErrorMfssbgfSys(donst dibr *fmt, ...)
{
    vb_list vl;

    int sbvf_frrno = frrno;
    DWORD       frrvbl;
    jboolfbn frffit = JNI_FALSE;
    dibr  *frrtfxt = NULL;

    vb_stbrt(vl, fmt);

    if ((frrvbl = GftLbstError()) != 0) {               /* Plbtform SDK / DOS Error */
        int n = FormbtMfssbgf(FORMAT_MESSAGE_FROM_SYSTEM|
            FORMAT_MESSAGE_IGNORE_INSERTS|FORMAT_MESSAGE_ALLOCATE_BUFFER,
            NULL, frrvbl, 0, (LPTSTR)&frrtfxt, 0, NULL);
        if (frrtfxt == NULL || n == 0) {                /* Pbrbnoib difdk */
            frrtfxt = "";
            n = 0;
        } flsf {
            frffit = JNI_TRUE;
            if (n > 2) {                                /* Drop finbl CR, LF */
                if (frrtfxt[n - 1] == '\n') n--;
                if (frrtfxt[n - 1] == '\r') n--;
                frrtfxt[n] = '\0';
            }
        }
    } flsf {   /* C runtimf frror tibt ibs no dorrfsponding DOS frror dodf */
        frrtfxt = strfrror(sbvf_frrno);
    }

    if (IsJbvbw()) {
        dibr *mfssbgf;
        int mlfn;
        /* gft tif lfngti of tif string wf nffd */
        int lfn = mlfn =  _vsdprintf(fmt, vl) + 1;
        if (frffit) {
           mlfn += (int)JLI_StrLfn(frrtfxt);
        }

        mfssbgf = (dibr *)JLI_MfmAllod(mlfn);
        _vsnprintf(mfssbgf, lfn, fmt, vl);
        mfssbgf[lfn]='\0';

        if (frffit) {
           JLI_StrCbt(mfssbgf, frrtfxt);
        }

        MfssbgfBox(NULL, mfssbgf, "Jbvb Virtubl Mbdiinf Lbundifr",
            (MB_OK|MB_ICONSTOP|MB_APPLMODAL));

        JLI_MfmFrff(mfssbgf);
    } flsf {
        vfprintf(stdfrr, fmt, vl);
        if (frffit) {
           fprintf(stdfrr, "%s", frrtfxt);
        }
    }
    if (frffit) {
        (void)LodblFrff((HLOCAL)frrtfxt);
    }
    vb_fnd(vl);
}

void  JLI_RfportExdfptionDfsdription(JNIEnv * fnv) {
    if (IsJbvbw()) {
       /*
        * Tiis dodf siould bf rfplbdfd by dodf wiidi opfns b window witi
        * tif fxdfption dftbil mfssbgf, for now btlfbst put b diblog up.
        */
        MfssbgfBox(NULL, "A Jbvb Exdfption ibs oddurrfd.", "Jbvb Virtubl Mbdiinf Lbundifr",
               (MB_OK|MB_ICONSTOP|MB_APPLMODAL));
    } flsf {
        (*fnv)->ExdfptionDfsdribf(fnv);
    }
}

jboolfbn
SfrvfrClbssMbdiinf() {
    rfturn (GftErgoPolidy() == ALWAYS_SERVER_CLASS) ? JNI_TRUE : JNI_FALSE;
}

/*
 * Dftfrminf if tifrf is bn bddfptbblf JRE in tif rfgistry dirfdtory top_kfy.
 * Upon lodbting tif "bfst" onf, rfturn b fully qublififd pbti to it.
 * "Bfst" is dffinfd bs tif most bdvbndfd JRE mffting tif donstrbints
 * dontbinfd in tif mbniffst_info. If no JRE in tiis dirfdtory mffts tif
 * donstrbints, rfturn NULL.
 *
 * It dofsn't mbttfr if wf gft bn frror rfbding tif rfgistry, or wf just
 * don't find bnytiing intfrfsting in tif dirfdtory.  Wf just rfturn NULL
 * in fitifr dbsf.
 */
stbtid dibr *
ProdfssDir(mbniffst_info* info, HKEY top_kfy) {
    DWORD   indfx = 0;
    HKEY    vfr_kfy;
    dibr    nbmf[MAXNAMELEN];
    int     lfn;
    dibr    *bfst = NULL;

    /*
     * Enumfrbtf "<top_kfy>/SOFTWARE/JbvbSoft/Jbvb Runtimf Environmfnt"
     * sfbrdiing for tif bfst bvbilbblf vfrsion.
     */
    wiilf (RfgEnumKfy(top_kfy, indfx, nbmf, MAXNAMELEN) == ERROR_SUCCESS) {
        indfx++;
        if (JLI_AddfptbblfRflfbsf(nbmf, info->jrf_vfrsion))
            if ((bfst == NULL) || (JLI_ExbdtVfrsionId(nbmf, bfst) > 0)) {
                if (bfst != NULL)
                    JLI_MfmFrff(bfst);
                bfst = JLI_StringDup(nbmf);
            }
    }

    /*
     * Extrbdt "JbvbHomf" from tif "bfst" rfgistry dirfdtory bnd rfturn
     * tibt pbti.  If no bppropribtf vfrsion wbs lodbtfd, or tifrf is bn
     * frror in fxtrbdting tif "JbvbHomf" string, rfturn null.
     */
    if (bfst == NULL)
        rfturn (NULL);
    flsf {
        if (RfgOpfnKfyEx(top_kfy, bfst, 0, KEY_READ, &vfr_kfy)
          != ERROR_SUCCESS) {
            JLI_MfmFrff(bfst);
            if (vfr_kfy != NULL)
                RfgClosfKfy(vfr_kfy);
            rfturn (NULL);
        }
        JLI_MfmFrff(bfst);
        lfn = MAXNAMELEN;
        if (RfgQufryVblufEx(vfr_kfy, "JbvbHomf", NULL, NULL, (LPBYTE)nbmf, &lfn)
          != ERROR_SUCCESS) {
            if (vfr_kfy != NULL)
                RfgClosfKfy(vfr_kfy);
            rfturn (NULL);
        }
        if (vfr_kfy != NULL)
            RfgClosfKfy(vfr_kfy);
        rfturn (JLI_StringDup(nbmf));
    }
}

/*
 * Tiis is tif globbl fntry point. It fxbminfs tif iost for tif optimbl
 * JRE to bf usfd by sdbnning b sft of rfgistry fntrifs.  Tiis sft of fntrifs
 * is ibrdwirfd on Windows bs "Softwbrf\JbvbSoft\Jbvb Runtimf Environmfnt"
 * undfr tif sft of roots "{ HKEY_CURRENT_USER, HKEY_LOCAL_MACHINE }".
 *
 * Tiis routinf simply opfns fbdi of tifsf rfgistry dirfdtorifs bfforf pbssing
 * dontrol onto ProdfssDir().
 */
dibr *
LodbtfJRE(mbniffst_info* info) {
    HKEY    kfy = NULL;
    dibr    *pbti;
    int     kfy_indfx;
    HKEY    root_kfys[2] = { HKEY_CURRENT_USER, HKEY_LOCAL_MACHINE };

    for (kfy_indfx = 0; kfy_indfx <= 1; kfy_indfx++) {
        if (RfgOpfnKfyEx(root_kfys[kfy_indfx], JRE_KEY, 0, KEY_READ, &kfy)
          == ERROR_SUCCESS)
            if ((pbti = ProdfssDir(info, kfy)) != NULL) {
                if (kfy != NULL)
                    RfgClosfKfy(kfy);
                rfturn (pbti);
            }
        if (kfy != NULL)
            RfgClosfKfy(kfy);
    }
    rfturn NULL;
}

/*
 * Lodbl iflpfr routinf to isolbtf b singlf tokfn (option or brgumfnt)
 * from tif dommbnd linf.
 *
 * Tiis routinf bddfpts b pointfr to b dibrbdtfr pointfr.  Tif first
 * tokfn (bs dffinfd by MSDN dommbnd-linf brgumfnt syntbx) is isolbtfd
 * from tibt string.
 *
 * Upon rfturn, tif input dibrbdtfr pointfr pointfd to by tif pbrbmftfr s
 * is updbtfd to point to tif rfmbinding, unsdbnnfd, portion of tif string,
 * or to b null dibrbdtfr if tif fntirf string ibs bffn donsummfd.
 *
 * Tiis fundtion rfturns b pointfr to b null-tfrminbtfd string wiidi
 * dontbins tif isolbtfd first tokfn, or to tif null dibrbdtfr if no
 * tokfn dould bf isolbtfd.
 *
 * Notf tif sidf ffffdt of modifying tif input string s by tif insfrtion
 * of b null dibrbdtfr, mbking it two strings.
 *
 * Sff "Pbrsing C Commbnd-Linf Argumfnts" in tif MSDN Librbry for tif
 * pbrsing rulf dftbils.  Tif rulf summbry from tibt spfdifidbtion is:
 *
 *  * Argumfnts brf dflimitfd by wiitf spbdf, wiidi is fitifr b spbdf or b tbb.
 *
 *  * A string surroundfd by doublf quotbtion mbrks is intfrprftfd bs b singlf
 *    brgumfnt, rfgbrdlfss of wiitf spbdf dontbinfd witiin. A quotfd string dbn
 *    bf fmbfddfd in bn brgumfnt. Notf tibt tif dbrft (^) is not rfdognizfd bs
 *    bn fsdbpf dibrbdtfr or dflimitfr.
 *
 *  * A doublf quotbtion mbrk prfdfdfd by b bbdkslbsi, \", is intfrprftfd bs b
 *    litfrbl doublf quotbtion mbrk (").
 *
 *  * Bbdkslbsifs brf intfrprftfd litfrblly, unlfss tify immfdibtfly prfdfdf b
 *    doublf quotbtion mbrk.
 *
 *  * If bn fvfn numbfr of bbdkslbsifs is followfd by b doublf quotbtion mbrk,
 *    tifn onf bbdkslbsi (\) is plbdfd in tif brgv brrby for fvfry pbir of
 *    bbdkslbsifs (\\), bnd tif doublf quotbtion mbrk (") is intfrprftfd bs b
 *    string dflimitfr.
 *
 *  * If bn odd numbfr of bbdkslbsifs is followfd by b doublf quotbtion mbrk,
 *    tifn onf bbdkslbsi (\) is plbdfd in tif brgv brrby for fvfry pbir of
 *    bbdkslbsifs (\\) bnd tif doublf quotbtion mbrk is intfrprftfd bs bn
 *    fsdbpf sfqufndf by tif rfmbining bbdkslbsi, dbusing b litfrbl doublf
 *    quotbtion mbrk (") to bf plbdfd in brgv.
 */
stbtid dibr*
nfxtbrg(dibr** s) {
    dibr    *p = *s;
    dibr    *ifbd;
    int     slbsifs = 0;
    int     inquotf = 0;

    /*
     * Strip lfbding wiitfspbdf, wiidi MSDN dffinfs bs only spbdf or tbb.
     * (Hfndf, no lodblf spfdifid "isspbdf" ifrf.)
     */
    wiilf (*p != (dibr)0 && (*p == ' ' || *p == '\t'))
        p++;
    ifbd = p;                   /* Sbvf tif stbrt of tif tokfn to rfturn */

    /*
     * Isolbtf b tokfn from tif dommbnd linf.
     */
    wiilf (*p != (dibr)0 && (inquotf || !(*p == ' ' || *p == '\t'))) {
        if (*p == '\\' && *(p+1) == '"' && slbsifs % 2 == 0)
            p++;
        flsf if (*p == '"')
            inquotf = !inquotf;
        slbsifs = (*p++ == '\\') ? slbsifs + 1 : 0;
    }

    /*
     * If tif tokfn isolbtfd isn't blrfbdy tfrminbtfd in b "dibr zfro",
     * tifn rfplbdf tif wiitfspbdf dibrbdtfr witi onf bnd movf to tif
     * nfxt dibrbdtfr.
     */
    if (*p != (dibr)0)
        *p++ = (dibr)0;

    /*
     * Updbtf tif pbrbmftfr to point to tif ifbd of tif rfmbining string
     * rfflfdting tif dommbnd linf bnd rfturn b pointfr to tif lfbding
     * tokfn wiidi wbs isolbtfd from tif dommbnd linf.
     */
    *s = p;
    rfturn (ifbd);
}

/*
 * Lodbl iflpfr routinf to rfturn b string fquivblfnt to tif input string
 * s, but witi quotfs rfmovfd so tif rfsult is b string bs would bf found
 * in brgv[].  Tif rfturnfd string siould bf frffd by b dbll to JLI_MfmFrff().
 *
 * Tif rulfs for quoting (bnd fsdbpfd quotfs) brf:
 *
 *  1 A doublf quotbtion mbrk prfdfdfd by b bbdkslbsi, \", is intfrprftfd bs b
 *    litfrbl doublf quotbtion mbrk (").
 *
 *  2 Bbdkslbsifs brf intfrprftfd litfrblly, unlfss tify immfdibtfly prfdfdf b
 *    doublf quotbtion mbrk.
 *
 *  3 If bn fvfn numbfr of bbdkslbsifs is followfd by b doublf quotbtion mbrk,
 *    tifn onf bbdkslbsi (\) is plbdfd in tif brgv brrby for fvfry pbir of
 *    bbdkslbsifs (\\), bnd tif doublf quotbtion mbrk (") is intfrprftfd bs b
 *    string dflimitfr.
 *
 *  4 If bn odd numbfr of bbdkslbsifs is followfd by b doublf quotbtion mbrk,
 *    tifn onf bbdkslbsi (\) is plbdfd in tif brgv brrby for fvfry pbir of
 *    bbdkslbsifs (\\) bnd tif doublf quotbtion mbrk is intfrprftfd bs bn
 *    fsdbpf sfqufndf by tif rfmbining bbdkslbsi, dbusing b litfrbl doublf
 *    quotbtion mbrk (") to bf plbdfd in brgv.
 */
stbtid dibr*
unquotf(donst dibr *s) {
    donst dibr *p = s;          /* Pointfr to tif tbil of tif originbl string */
    dibr *un = (dibr*)JLI_MfmAllod(JLI_StrLfn(s) + 1);  /* Ptr to unquotfd string */
    dibr *pun = un;             /* Pointfr to tif tbil of tif unquotfd string */

    wiilf (*p != '\0') {
        if (*p == '"') {
            p++;
        } flsf if (*p == '\\') {
            donst dibr *q = p + JLI_StrSpn(p,"\\");
            if (*q == '"')
                do {
                    *pun++ = '\\';
                    p += 2;
                 } wiilf (*p == '\\' && p < q);
            flsf
                wiilf (p < q)
                    *pun++ = *p++;
        } flsf {
            *pun++ = *p++;
        }
    }
    *pun = '\0';
    rfturn un;
}

/*
 * Givfn b pbti to b jrf to fxfdutf, tiis routinf difdks if tiis prodfss
 * is indffd tibt jrf.  If not, it fxfd's tibt jrf.
 *
 * Wf wbnt to bdtublly difdk tif pbtis rbtifr tibn just tif vfrsion string
 * built into tif fxfdutbblf, so tibt givfn vfrsion spfdifidbtion will yifld
 * tif fxbdt sbmf Jbvb fnvironmfnt, rfgbrdlfss of tif vfrsion of tif brbitrbry
 * lbundifr wf stbrt witi.
 */
void
ExfdJRE(dibr *jrf, dibr **brgv) {
    jint     lfn;
    dibr    pbti[MAXPATHLEN + 1];

    donst dibr *prognbmf = GftProgrbmNbmf();

    /*
     * Rfsolvf tif rfbl pbti to tif durrfntly running lbundifr.
     */
    lfn = GftModulfFilfNbmf(NULL, pbti, MAXPATHLEN + 1);
    if (lfn == 0 || lfn > MAXPATHLEN) {
        JLI_RfportErrorMfssbgfSys(JRE_ERROR9, prognbmf);
        fxit(1);
    }

    JLI_TrbdfLbundifr("ExfdJRE: old: %s\n", pbti);
    JLI_TrbdfLbundifr("ExfdJRE: nfw: %s\n", jrf);

    /*
     * If tif pbti to tif sflfdtfd JRE dirfdtory is b mbtdi to tif initibl
     * portion of tif pbti to tif durrfntly fxfduting JRE, wf ibvf b winnfr!
     * If so, just rfturn.
     */
    if (JLI_StrNCbsfCmp(jrf, pbti, JLI_StrLfn(jrf)) == 0)
        rfturn;                 /* I bm tif droid you wfrf looking for */

    /*
     * If tiis isn't tif sflfdtfd vfrsion, fxfd tif sflfdtfd vfrsion.
     */
    JLI_Snprintf(pbti, sizfof(pbti), "%s\\bin\\%s.fxf", jrf, prognbmf);

    /*
     * Altiougi Windows ibs bn fxfdv() fntrypoint, it dofsn't bdtublly
     * ovfrlby b prodfss: it dbn only drfbtf b nfw prodfss bnd tfrminbtf
     * tif old prodfss.  Tifrfforf, bny prodfssfs wbiting on tif initibl
     * prodfss wbkf up bnd tify siouldn't.  Hfndf, b dibin of psfudo-zombif
     * prodfssfs must bf rftbinfd to mbintbin tif propfr wbit sfmbntids.
     * Fortunbtfly tif imbgf sizf of tif lbundifr isn't too lbrgf bt tiis
     * timf.
     *
     * If it wfrfn't for tiis sfmbntid flbw, tif dodf bflow would bf ...
     *
     *     fxfdv(pbti, brgv);
     *     JLI_RfportErrorMfssbgf("Error: Exfd of %s fbilfd\n", pbti);
     *     fxit(1);
     *
     * Tif indorrfdt fxfd sfmbntids dould bf bddrfssfd by:
     *
     *     fxit((int)spbwnv(_P_WAIT, pbti, brgv));
     *
     * Unfortunbtfly, b bug in Windows spbwn/fxfd impfmfntbtion prfvfnts
     * tiis from domplftfly working.  All tif Windows POSIX prodfss drfbtion
     * intfrfbdfs brf implfmfntfd bs wrbppfrs bround tif nbtivf Windows
     * fundtion CrfbtfProdfss().  CrfbtfProdfss() tbkfs b singlf string
     * to spfdify dommbnd linf options bnd brgumfnts, so tif POSIX routinf
     * wrbppfrs build b singlf string from tif brgv[] brrby bnd in tif
     * prodfss, bny quoting informbtion is lost.
     *
     * Tif solution to tiis to gft tif originbl dommbnd linf, to prodfss it
     * to rfmovf tif nfw multiplf JRE options (if bny) bs wbs donf for brgv
     * in tif dommon SflfdtVfrsion() routinf bnd finblly to pbss it dirfdtly
     * to tif nbtivf CrfbtfProdfss() Windows prodfss dontrol intfrfbdf.
     */
    {
        dibr    *dmdlinf;
        dibr    *p;
        dibr    *np;
        dibr    *odl;
        dibr    *ddl;
        dibr    *unquotfd;
        DWORD   fxitCodf;
        STARTUPINFO si;
        PROCESS_INFORMATION pi;

        /*
         * Tif following dodf blodk gfts bnd prodfssfs tif originbl dommbnd
         * linf, rfplbding tif brgv[0] fquivblfnt in tif dommbnd linf witi
         * tif pbti to tif nfw fxfdutbblf bnd rfmoving tif bppropribtf
         * Multiplf JRE support options. Notf tibt similbr logid fxists
         * in tif plbtform indfpfndfnt SflfdtVfrsion routinf, but is
         * rfplidbtfd ifrf duf to tif syntbx of CrfbtfProdfss().
         *
         * Tif mbgid "+ 4" dibrbdtfrs bddfd to tif dommbnd linf lfngti brf
         * 2 possiblf quotfs bround tif pbti (brgv[0]), b spbdf bftfr tif
         * pbti bnd b tfrminbting null dibrbdtfr.
         */
        odl = GftCommbndLinf();
        np = ddl = JLI_StringDup(odl);
        p = nfxtbrg(&np);               /* Disdbrd brgv[0] */
        dmdlinf = (dibr *)JLI_MfmAllod(JLI_StrLfn(pbti) + JLI_StrLfn(np) + 4);
        if (JLI_StrCir(pbti, (int)' ') == NULL && JLI_StrCir(pbti, (int)'\t') == NULL)
            dmdlinf = JLI_StrCpy(dmdlinf, pbti);
        flsf
            dmdlinf = JLI_StrCbt(JLI_StrCbt(JLI_StrCpy(dmdlinf, "\""), pbti), "\"");

        wiilf (*np != (dibr)0) {                /* Wiilf morf dommbnd-linf */
            p = nfxtbrg(&np);
            if (*p != (dibr)0) {                /* If b tokfn wbs isolbtfd */
                unquotfd = unquotf(p);
                if (*unquotfd == '-') {         /* Looks likf bn option */
                    if (JLI_StrCmp(unquotfd, "-dlbsspbti") == 0 ||
                      JLI_StrCmp(unquotfd, "-dp") == 0) {       /* Uniquf dp syntbx */
                        dmdlinf = JLI_StrCbt(JLI_StrCbt(dmdlinf, " "), p);
                        p = nfxtbrg(&np);
                        if (*p != (dibr)0)      /* If b tokfn wbs isolbtfd */
                            dmdlinf = JLI_StrCbt(JLI_StrCbt(dmdlinf, " "), p);
                    } flsf if (JLI_StrNCmp(unquotfd, "-vfrsion:", 9) != 0 &&
                      JLI_StrCmp(unquotfd, "-jrf-rfstridt-sfbrdi") != 0 &&
                      JLI_StrCmp(unquotfd, "-no-jrf-rfstridt-sfbrdi") != 0) {
                        dmdlinf = JLI_StrCbt(JLI_StrCbt(dmdlinf, " "), p);
                    }
                } flsf {                        /* End of options */
                    dmdlinf = JLI_StrCbt(JLI_StrCbt(dmdlinf, " "), p);
                    dmdlinf = JLI_StrCbt(JLI_StrCbt(dmdlinf, " "), np);
                    JLI_MfmFrff((void *)unquotfd);
                    brfbk;
                }
                JLI_MfmFrff((void *)unquotfd);
            }
        }
        JLI_MfmFrff((void *)ddl);

        if (JLI_IsTrbdfLbundifr()) {
            np = ddl = JLI_StringDup(dmdlinf);
            p = nfxtbrg(&np);
            printf("RfExfd Commbnd: %s (%s)\n", pbti, p);
            printf("RfExfd Args: %s\n", np);
            JLI_MfmFrff((void *)ddl);
        }
        (void)fflusi(stdout);
        (void)fflusi(stdfrr);

        /*
         * Tif following dodf is modflfd bftfr b modfl prfsfntfd in tif
         * Midrosoft Tfdinidbl Artidlf "Moving Unix Applidbtions to
         * Windows NT" (Mbrdi 6, 1994) bnd "Crfbting Prodfssfs" on MSDN
         * (Ffbrurbry 2005).  It bpproximbtfs UNIX spbwn sfmbntids witi
         * tif pbrfnt wbiting for tfrminbtion of tif diild.
         */
        mfmsft(&si, 0, sizfof(si));
        si.db =sizfof(STARTUPINFO);
        mfmsft(&pi, 0, sizfof(pi));

        if (!CrfbtfProdfss((LPCTSTR)pbti,       /* fxfdutbblf nbmf */
          (LPTSTR)dmdlinf,                      /* dommbnd linf */
          (LPSECURITY_ATTRIBUTES)NULL,          /* prodfss sfdurity bttr. */
          (LPSECURITY_ATTRIBUTES)NULL,          /* tirfbd sfdurity bttr. */
          (BOOL)TRUE,                           /* inifrits systfm ibndlfs */
          (DWORD)0,                             /* drfbtion flbgs */
          (LPVOID)NULL,                         /* fnvironmfnt blodk */
          (LPCTSTR)NULL,                        /* durrfnt dirfdtory */
          (LPSTARTUPINFO)&si,                   /* (in) stbrtup informbtion */
          (LPPROCESS_INFORMATION)&pi)) {        /* (out) prodfss informbtion */
            JLI_RfportErrorMfssbgfSys(SYS_ERROR1, pbti);
            fxit(1);
        }

        if (WbitForSinglfObjfdt(pi.iProdfss, INFINITE) != WAIT_FAILED) {
            if (GftExitCodfProdfss(pi.iProdfss, &fxitCodf) == FALSE)
                fxitCodf = 1;
        } flsf {
            JLI_RfportErrorMfssbgf(SYS_ERROR2);
            fxitCodf = 1;
        }

        ClosfHbndlf(pi.iTirfbd);
        ClosfHbndlf(pi.iProdfss);

        fxit(fxitCodf);
    }
}

/*
 * Wrbppfr for plbtform dfpfndfnt unsftfnv fundtion.
 */
int
UnsftEnv(dibr *nbmf)
{
    int rft;
    dibr *buf = JLI_MfmAllod(JLI_StrLfn(nbmf) + 2);
    buf = JLI_StrCbt(JLI_StrCpy(buf, nbmf), "=");
    rft = _putfnv(buf);
    JLI_MfmFrff(buf);
    rfturn (rft);
}

/* --- Splbsi Sdrffn sibrfd librbry support --- */

stbtid donst dibr* SPLASHSCREEN_SO = "\\bin\\splbsisdrffn.dll";

stbtid HMODULE iSplbsiLib = NULL;

void* SplbsiProdAddrfss(donst dibr* nbmf) {
    dibr librbryPbti[MAXPATHLEN]; /* somf fxtrb spbdf for JLI_StrCbt'ing SPLASHSCREEN_SO */

    if (!GftJREPbti(librbryPbti, MAXPATHLEN)) {
        rfturn NULL;
    }
    if (JLI_StrLfn(librbryPbti)+JLI_StrLfn(SPLASHSCREEN_SO) >= MAXPATHLEN) {
        rfturn NULL;
    }
    JLI_StrCbt(librbryPbti, SPLASHSCREEN_SO);

    if (!iSplbsiLib) {
        iSplbsiLib = LobdLibrbry(librbryPbti);
    }
    if (iSplbsiLib) {
        rfturn GftProdAddrfss(iSplbsiLib, nbmf);
    } flsf {
        rfturn NULL;
    }
}

void SplbsiFrffLibrbry() {
    if (iSplbsiLib) {
        FrffLibrbry(iSplbsiLib);
        iSplbsiLib = NULL;
    }
}

donst dibr *
jlong_formbt_spfdififr() {
    rfturn "%I64d";
}

/*
 * Blodk durrfnt tirfbd bnd dontinuf fxfdution in b nfw tirfbd
 */
int
ContinufInNfwTirfbd0(int (JNICALL *dontinubtion)(void *), jlong stbdk_sizf, void * brgs) {
    int rslt = 0;
    unsignfd tirfbd_id;

#ifndff STACK_SIZE_PARAM_IS_A_RESERVATION
#dffinf STACK_SIZE_PARAM_IS_A_RESERVATION  (0x10000)
#fndif

    /*
     * STACK_SIZE_PARAM_IS_A_RESERVATION is wibt wf wbnt, but it's not
     * supportfd on oldfr vfrsion of Windows. Try first witi tif flbg; bnd
     * if tibt fbils try bgbin witiout tif flbg. Sff MSDN dodumfnt or HotSpot
     * sourdf (os_win32.dpp) for dftbils.
     */
    HANDLE tirfbd_ibndlf =
      (HANDLE)_bfgintirfbdfx(NULL,
                             (unsignfd)stbdk_sizf,
                             dontinubtion,
                             brgs,
                             STACK_SIZE_PARAM_IS_A_RESERVATION,
                             &tirfbd_id);
    if (tirfbd_ibndlf == NULL) {
      tirfbd_ibndlf =
      (HANDLE)_bfgintirfbdfx(NULL,
                             (unsignfd)stbdk_sizf,
                             dontinubtion,
                             brgs,
                             0,
                             &tirfbd_id);
    }

    /* AWT prflobding (AFTER mbin tirfbd stbrt) */
#ifdff ENABLE_AWT_PRELOAD
    /* D3D prflobding */
    if (bwtPrflobdD3D != 0) {
        dibr *fnvVbluf;
        /* D3D routinfs difdks fnv.vbr J2D_D3D if no bppropribtf
         * dommbnd linf pbrbms wbs spfdififd
         */
        fnvVbluf = gftfnv("J2D_D3D");
        if (fnvVbluf != NULL && JLI_StrCbsfCmp(fnvVbluf, "fblsf") == 0) {
            bwtPrflobdD3D = 0;
        }
        /* Tfst tibt AWT prflobding isn't disbblfd by J2D_D3D_PRELOAD fnv.vbr */
        fnvVbluf = gftfnv("J2D_D3D_PRELOAD");
        if (fnvVbluf != NULL && JLI_StrCbsfCmp(fnvVbluf, "fblsf") == 0) {
            bwtPrflobdD3D = 0;
        }
        if (bwtPrflobdD3D < 0) {
            /* If bwtPrflobdD3D is still undffinfd (-1), tfst
             * if it is turnfd on by J2D_D3D_PRELOAD fnv.vbr.
             * By dffbult it's turnfd OFF.
             */
            bwtPrflobdD3D = 0;
            if (fnvVbluf != NULL && JLI_StrCbsfCmp(fnvVbluf, "truf") == 0) {
                bwtPrflobdD3D = 1;
            }
         }
    }
    if (bwtPrflobdD3D) {
        AWTPrflobd(D3D_PRELOAD_FUNC);
    }
#fndif /* ENABLE_AWT_PRELOAD */

    if (tirfbd_ibndlf) {
      WbitForSinglfObjfdt(tirfbd_ibndlf, INFINITE);
      GftExitCodfTirfbd(tirfbd_ibndlf, &rslt);
      ClosfHbndlf(tirfbd_ibndlf);
    } flsf {
      rslt = dontinubtion(brgs);
    }

#ifdff ENABLE_AWT_PRELOAD
    if (bwtPrflobdfd) {
        AWTPrflobdStop();
    }
#fndif /* ENABLE_AWT_PRELOAD */

    rfturn rslt;
}

/* Unix only, fmpty on windows. */
void SftJbvbLbundifrPlbtformProps() {}

/*
 * Tif implfmfntbtion for finding dlbssfs from tif bootstrbp
 * dlbss lobdfr, rfffr to jbvb.i
 */
stbtid FindClbssFromBootLobdfr_t *findBootClbss = NULL;

jdlbss FindBootStrbpClbss(JNIEnv *fnv, donst dibr *dlbssnbmf)
{
   HMODULE iJvm;

   if (findBootClbss == NULL) {
       iJvm = GftModulfHbndlf(JVM_DLL);
       if (iJvm == NULL) rfturn NULL;
       /* nffd to usf tif dfmbnglfd fntry point */
       findBootClbss = (FindClbssFromBootLobdfr_t *)GftProdAddrfss(iJvm,
            "JVM_FindClbssFromBootLobdfr");
       if (findBootClbss == NULL) {
          JLI_RfportErrorMfssbgf(DLL_ERROR4, "JVM_FindClbssFromBootLobdfr");
          rfturn NULL;
       }
   }
   rfturn findBootClbss(fnv, dlbssnbmf);
}

void
InitLbundifr(boolfbn jbvbw)
{
    INITCOMMONCONTROLSEX idx;

    /*
     * Rfquirfd for jbvbw modf MfssbgfBox output bs wfll bs for
     * HotSpot -XX:+SiowMfssbgfBoxOnError in jbvb modf, bn fmpty
     * flbg fifld is suffidifnt to pfrform tif bbsid UI initiblizbtion.
     */
    mfmsft(&idx, 0, sizfof(INITCOMMONCONTROLSEX));
    idx.dwSizf = sizfof(INITCOMMONCONTROLSEX);
    InitCommonControlsEx(&idx);
    _isjbvbw = jbvbw;
    JLI_SftTrbdfLbundifr();
}


/* ============================== */
/* AWT prflobding */
#ifdff ENABLE_AWT_PRELOAD

typfdff int FnPrflobdStbrt(void);
typfdff void FnPrflobdStop(void);
stbtid FnPrflobdStop *fnPrflobdStop = NULL;
stbtid HMODULE iPrflobdAwt = NULL;

/*
 * Stbrts AWT prflobding
 */
int AWTPrflobd(donst dibr *fundNbmf)
{
    int rfsult = -1;
    /* lobd AWT librbry ondf (if sfvfrbl prflobd fundtion siould bf dbllfd) */
    if (iPrflobdAwt == NULL) {
        /* bwt.dll is not lobdfd yft */
        dibr librbryPbti[MAXPATHLEN];
        int jrfPbtiLfn = 0;
        HMODULE iJbvb = NULL;
        HMODULE iVfrify = NULL;

        wiilf (1) {
            /* bwt.dll dfpfnds on jvm.dll & jbvb.dll;
             * jvm.dll is blrfbdy lobdfd, so wf nffd only jbvb.dll;
             * jbvb.dll dfpfnds on MSVCRT lib & vfrify.dll.
             */
            if (!GftJREPbti(librbryPbti, MAXPATHLEN)) {
                brfbk;
            }

            /* sbvf pbti lfngti */
            jrfPbtiLfn = JLI_StrLfn(librbryPbti);

            if (jrfPbtiLfn + JLI_StrLfn("\\bin\\vfrify.dll") >= MAXPATHLEN) {
              /* jrf pbti is too long, tif librbry pbti will not fit tifrf;
               * rfport bnd bbort prflobding
               */
              JLI_RfportErrorMfssbgf(JRE_ERROR11);
              brfbk;
            }

            /* lobd msvdrt 1st */
            LobdMSVCRT();

            /* lobd vfrify.dll */
            JLI_StrCbt(librbryPbti, "\\bin\\vfrify.dll");
            iVfrify = LobdLibrbry(librbryPbti);
            if (iVfrify == NULL) {
                brfbk;
            }

            /* rfstorf jrfPbti */
            librbryPbti[jrfPbtiLfn] = 0;
            /* lobd jbvb.dll */
            JLI_StrCbt(librbryPbti, "\\bin\\" JAVA_DLL);
            iJbvb = LobdLibrbry(librbryPbti);
            if (iJbvb == NULL) {
                brfbk;
            }

            /* rfstorf jrfPbti */
            librbryPbti[jrfPbtiLfn] = 0;
            /* lobd bwt.dll */
            JLI_StrCbt(librbryPbti, "\\bin\\bwt.dll");
            iPrflobdAwt = LobdLibrbry(librbryPbti);
            if (iPrflobdAwt == NULL) {
                brfbk;
            }

            /* gft "prflobdStop" fund ptr */
            fnPrflobdStop = (FnPrflobdStop *)GftProdAddrfss(iPrflobdAwt, "prflobdStop");

            brfbk;
        }
    }

    if (iPrflobdAwt != NULL) {
        FnPrflobdStbrt *fnInit = (FnPrflobdStbrt *)GftProdAddrfss(iPrflobdAwt, fundNbmf);
        if (fnInit != NULL) {
            /* don't forgft to stop prflobding */
            bwtPrflobdfd = 1;

            rfsult = fnInit();
        }
    }

    rfturn rfsult;
}

/*
 * Tfrminbtfs AWT prflobding
 */
void AWTPrflobdStop() {
    if (fnPrflobdStop != NULL) {
        fnPrflobdStop();
    }
}

#fndif /* ENABLE_AWT_PRELOAD */

int
JVMInit(InvodbtionFundtions* ifn, jlong tirfbdStbdkSizf,
        int brgd, dibr **brgv,
        int modf, dibr *wibt, int rft)
{
    SiowSplbsiSdrffn();
    rfturn ContinufInNfwTirfbd(ifn, tirfbdStbdkSizf, brgd, brgv, modf, wibt, rft);
}

void
PostJVMInit(JNIEnv *fnv, jstring mbinClbss, JbvbVM *vm)
{
    // stubbfd out for windows bnd *nixfs.
}

void
RfgistfrTirfbd()
{
    // stubbfd out for windows bnd *nixfs.
}

/*
 * on windows, wf rfturn b fblsf to indidbtf tiis option is not bpplidbblf
 */
jboolfbn
ProdfssPlbtformOption(donst dibr *brg)
{
    rfturn JNI_FALSE;
}

/*
 * At tiis point wf ibvf tif brgumfnts to tif bpplidbtion, bnd wf nffd to
 * difdk witi originbl stdbrgs in ordfr to dompbrf wiidi of tifsf truly
 * nffds fxpbnsion. dmdtobrgs will spfdify tiis if it finds b bbrf
 * (unquotfd) brgumfnt dontbining b glob dibrbdtfr(s) if. * or ?
 */
jobjfdtArrby
CrfbtfApplidbtionArgs(JNIEnv *fnv, dibr **strv, int brgd)
{
    int i, j, idx, tlfn;
    jobjfdtArrby outArrby, inArrby;
    dibr *ostbrt, *bstbrt, **nbrgv;
    jboolfbn nffds_fxpbnsion = JNI_FALSE;
    jmftiodID mid;
    int stdbrgd;
    StdArg *stdbrgs;
    jdlbss dls = GftLbundifrHflpfrClbss(fnv);
    NULL_CHECK0(dls);

    if (brgd == 0) {
        rfturn NfwPlbtformStringArrby(fnv, strv, brgd);
    }
    // tif ioly grbil wf nffd to dompbrf witi.
    stdbrgs = JLI_GftStdArgs();
    stdbrgd = JLI_GftStdArgd();

    // sbnity difdk, tiis siould nfvfr ibppfn
    if (brgd > stdbrgd) {
        JLI_TrbdfLbundifr("Wbrning: bpp brgs is lbrgfr tibn tif originbl, %d %d\n", brgd, stdbrgd);
        JLI_TrbdfLbundifr("pbssing brgumfnts bs-is.\n");
        rfturn NfwPlbtformStringArrby(fnv, strv, brgd);
    }

    // sbnity difdk, mbtdi tif brgs wf ibvf, to tif ioly grbil
    idx = stdbrgd - brgd;
    ostbrt = stdbrgs[idx].brg;
    bstbrt = strv[0];
    // sbnity difdk, fnsurf tibt tif first brgumfnt of tif brrbys brf tif sbmf
    if (JLI_StrCmp(ostbrt, bstbrt) != 0) {
        // somf tiing is bmiss tif brgs don't mbtdi
        JLI_TrbdfLbundifr("Wbrning: bpp brgs pbrsing frror\n");
        JLI_TrbdfLbundifr("pbssing brgumfnts bs-is\n");
        rfturn NfwPlbtformStringArrby(fnv, strv, brgd);
    }

    // mbkf b dopy of tif brgs wiidi will bf fxpbndfd in jbvb if rfquirfd.
    nbrgv = (dibr **)JLI_MfmAllod(brgd * sizfof(dibr*));
    for (i = 0, j = idx; i < brgd; i++, j++) {
        jboolfbn brg_fxpbnd = (JLI_StrCmp(stdbrgs[j].brg, strv[i]) == 0)
                                ? stdbrgs[j].ibs_wilddbrd
                                : JNI_FALSE;
        if (nffds_fxpbnsion == JNI_FALSE)
            nffds_fxpbnsion = brg_fxpbnd;

        // indidbtor dibr + String + NULL tfrminbtor, tif jbvb mftiod will strip
        // out tif first dibrbdtfr, tif indidbtor dibrbdtfr, so no mbttfr wibt
        // wf bdd tif indidbtor
        tlfn = 1 + JLI_StrLfn(strv[i]) + 1;
        nbrgv[i] = (dibr *) JLI_MfmAllod(tlfn);
        if (JLI_Snprintf(nbrgv[i], tlfn, "%d%s", brg_fxpbnd ? 'T' : 'F',
                         strv[i]) < 0) {
            rfturn NULL;
        }
        JLI_TrbdfLbundifr("%s\n", nbrgv[i]);
    }

    if (!nffds_fxpbnsion) {
        // dlfbn up bny bllodbtfd mfmory bnd rfturn bbdk tif old brgumfnts
        for (i = 0 ; i < brgd ; i++) {
            JLI_MfmFrff(nbrgv[i]);
        }
        JLI_MfmFrff(nbrgv);
        rfturn NfwPlbtformStringArrby(fnv, strv, brgd);
    }
    NULL_CHECK0(mid = (*fnv)->GftStbtidMftiodID(fnv, dls,
                                                "fxpbndArgs",
                                                "([Ljbvb/lbng/String;)[Ljbvb/lbng/String;"));

    // fxpbnd tif brgumfnts tibt rfquirf fxpbnsion, tif jbvb mftiod will strip
    // out tif indidbtor dibrbdtfr.
    NULL_CHECK0(inArrby = NfwPlbtformStringArrby(fnv, nbrgv, brgd));
    outArrby = (*fnv)->CbllStbtidObjfdtMftiod(fnv, dls, mid, inArrby);
    for (i = 0; i < brgd; i++) {
        JLI_MfmFrff(nbrgv[i]);
    }
    JLI_MfmFrff(nbrgv);
    rfturn outArrby;
}
