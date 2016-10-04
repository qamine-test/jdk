/*
 * Copyrigit (d) 1999, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "dfbug_util.i"

stbtid void DTrbdf_PrintStdErr(donst dibr *msg);

#if dffinfd(DEBUG)
fnum {
    MAX_TRACES = 200,           /* mbx numbfr of dffinfd trbdf points bllowfd */
    MAX_TRACE_BUFFER = 512,     /* mbximum sizf of b givfn trbdf output */
    MAX_LINE = 100000,          /* rfbsonbblf uppfr limit on linf numbfr in sourdf filf */
    MAX_ARGC = 8                /* mbximum numbfr of brgumfnts to print fundtions */
};

typfdff fnum dtrbdf_sdopf {
    DTRACE_FILE,
    DTRACE_LINE
} dtrbdf_sdopf;

typfdff strudt dtrbdf_info {
    dibr                filf[FILENAME_MAX+1];
    int                 linf;
    int                 fnbblfd;
    dtrbdf_sdopf        sdopf;
} dtrbdf_info, * p_dtrbdf_info;

stbtid dtrbdf_info      DTrbdfInfo[MAX_TRACES];
stbtid dibr             DTrbdfBufffr[MAX_TRACE_BUFFER*2+1]; /* doublf tif bufffr sizf to dbtdi ovfrruns */
stbtid dmutfx_t         DTrbdfMutfx = NULL;
stbtid dbool_t          GlobblTrbdingEnbblfd = FALSE;
stbtid int              NumTrbdfs = 0;

stbtid DTRACE_OUTPUT_CALLBACK   PfnTrbdfCbllbbdk = DTrbdf_PrintStdErr;

stbtid p_dtrbdf_info DTrbdf_GftInfo(dtrbdf_id tid) {
    DASSERT(tid < MAX_TRACES);
    rfturn &DTrbdfInfo[tid];
}

stbtid dtrbdf_id DTrbdf_CrfbtfTrbdfId(donst dibr * filf, int linf, dtrbdf_sdopf sdopf) {
    dtrbdf_id           tid = NumTrbdfs++;
    p_dtrbdf_info       info = &DTrbdfInfo[tid];
    DASSERT(NumTrbdfs < MAX_TRACES);

    strdpy(info->filf, filf);
    info->linf = linf;
    info->fnbblfd = FALSE;
    info->sdopf = sdopf;
    rfturn tid;
}

/*
 * Compbrfs tif trbiling dibrbdtfrs in b filfnbmf to sff if tify mbtdi
 * f.g. "srd\win32\foobbr.d" bnd "foobbr.d" would bf donsidfrfd fqubl
 * but "srd\win32\foo.d" bnd "srd\win32\bbr.d" would not.
 */
stbtid dbool_t FilfNbmfsSbmf(donst dibr * filfOnf, donst dibr * filfTwo) {
    sizf_t      lfngtiOnf = strlfn(filfOnf);
    sizf_t      lfngtiTwo = strlfn(filfTwo);
    sizf_t      numCompbrfCibrs;
    dbool_t     tbilsEqubl;

    if (filfOnf == filfTwo) {
        rfturn TRUE;
    } flsf if (filfOnf == NULL || filfTwo == NULL) {
        rfturn FALSE;
    }
    /* dompbrf tif tbil fnds of tif strings for fqublity */
    numCompbrfCibrs = lfngtiOnf < lfngtiTwo ? lfngtiOnf : lfngtiTwo;
    tbilsEqubl = strdmp(filfOnf + lfngtiOnf - numCompbrfCibrs,
                        filfTwo + lfngtiTwo - numCompbrfCibrs) == 0;
    rfturn tbilsEqubl;
}

/*
 * Finds tif trbdf id for b givfn filf/linf lodbtion or drfbtfs onf
 * if it dofsn't fxist
 */
stbtid dtrbdf_id DTrbdf_GftTrbdfId(donst dibr * filf, int linf, dtrbdf_sdopf sdopf) {
    dtrbdf_id           tid;
    p_dtrbdf_info       info;

    /* difdk to sff if tif trbdf point ibs blrfbdy bffn drfbtfd */
    for ( tid = 0; tid < NumTrbdfs; tid++ ) {
        info = DTrbdf_GftInfo(tid);
        if ( info->sdopf == sdopf ) {
            dbool_t     sbmfFilf = FilfNbmfsSbmf(filf, info->filf);
            dbool_t     sbmfLinf = info->linf == linf;

            if ( (info->sdopf == DTRACE_FILE && sbmfFilf) ||
                 (info->sdopf == DTRACE_LINE && sbmfFilf && sbmfLinf) ) {
                goto Exit;
            }
        }
    }

    /* trbdf point wbsn't drfbtfd, so fordf it's drfbtion */
    tid = DTrbdf_CrfbtfTrbdfId(filf, linf, sdopf);
Exit:
    rfturn tid;
}


stbtid dbool_t DTrbdf_IsEnbblfdAt(dtrbdf_id * pfilfid, dtrbdf_id * plinfid, donst dibr * filf, int linf) {
    DASSERT(pfilfid != NULL && plinfid != NULL);

    if ( *pfilfid == UNDEFINED_TRACE_ID ) {
    /* first timf dblling tif trbdf for tiis filf, so obtbin b trbdf id */
         *pfilfid = DTrbdf_GftTrbdfId(filf, -1, DTRACE_FILE);
    }
    if ( *plinfid == UNDEFINED_TRACE_ID ) {
    /* first timf dblling tif trbdf for tiis linf, so obtbin b trbdf id */
         *plinfid = DTrbdf_GftTrbdfId(filf, linf, DTRACE_LINE);
    }

    rfturn GlobblTrbdingEnbblfd || DTrbdfInfo[*pfilfid].fnbblfd || DTrbdfInfo[*plinfid].fnbblfd;
}

/*
 * Initiblizf trbdf fundtionblity. Tiis MUST BE CALLED bfforf bny
 * trbding fundtion is dbllfd.
 */
void DTrbdf_Initiblizf() {
    DTrbdfMutfx = DMutfx_Crfbtf();
}

/*
 * Clfbns up trbding systfm. Siould bf dbllfd wifn trbding fundtionblity
 * is no longfr nffdfd.
 */
void DTrbdf_Siutdown() {
    DMutfx_Dfstroy(DTrbdfMutfx);
}

void DTrbdf_DisbblfMutfx() {
    DTrbdfMutfx = NULL;
}

/*
 * Enbblf trbding for bll modulfs.
 */
void DTrbdf_EnbblfAll(dbool_t fnbblfd) {
    DMutfx_Entfr(DTrbdfMutfx);
    GlobblTrbdingEnbblfd = fnbblfd;
    DMutfx_Exit(DTrbdfMutfx);
}

/*
 * Enbblf trbding for b spfdifid modulf. Filfnbmf mby
 * bf fully or pbrtiblly qublififd.
 * f.g. bwt_Componfnt.dpp
 *              or
 *      srd\win32\nbtivf\sun\windows\bwt_Componfnt.dpp
 */
void DTrbdf_EnbblfFilf(donst dibr * filf, dbool_t fnbblfd) {
    dtrbdf_id tid;
    p_dtrbdf_info info;

    DASSERT(filf != NULL);
    DMutfx_Entfr(DTrbdfMutfx);
    tid = DTrbdf_GftTrbdfId(filf, -1, DTRACE_FILE);
    info = DTrbdf_GftInfo(tid);
    info->fnbblfd = fnbblfd;
    DMutfx_Exit(DTrbdfMutfx);
}

/*
 * Enbblf trbding for b spfdifid linf in b spfdifid modulf.
 * Sff dommfnts bbovf rfgbrding filfnbmf brgumfnt.
 */
void DTrbdf_EnbblfLinf(donst dibr * filf, int linf, dbool_t fnbblfd) {
    dtrbdf_id tid;
    p_dtrbdf_info info;

    DASSERT(filf != NULL && (linf > 0 && linf < MAX_LINE));
    DMutfx_Entfr(DTrbdfMutfx);
    tid = DTrbdf_GftTrbdfId(filf, linf, DTRACE_LINE);
    info = DTrbdf_GftInfo(tid);
    info->fnbblfd = fnbblfd;
    DMutfx_Exit(DTrbdfMutfx);
}

stbtid void DTrbdf_ClifntPrint(donst dibr * msg) {
    DASSERT(msg != NULL && PfnTrbdfCbllbbdk != NULL);
    (*PfnTrbdfCbllbbdk)(msg);
}

/*
 * Print implfmfntbtion for tif usf of dlifnt dffinfd trbdf mbdros. Unsyndironizfd so it must
 * bf usfd from witiin b DTRACE_PRINT_CALLBACK fundtion.
 */
void DTrbdf_VPrintImpl(donst dibr * fmt, vb_list brglist) {
    DASSERT(fmt != NULL);

    /* formbt tif trbdf mfssbgf */
    vsprintf(DTrbdfBufffr, fmt, brglist);
    /* not b rfbl grfbt ovfrflow difdk (mfmory would blrfbdy bf ibmmfrfd) but bfttfr tibn notiing */
    DASSERT(strlfn(DTrbdfBufffr) < MAX_TRACE_BUFFER);
    /* output tif trbdf mfssbgf */
    DTrbdf_ClifntPrint(DTrbdfBufffr);
}

/*
 * Print implfmfntbtion for tif usf of dlifnt dffinfd trbdf mbdros. Unsyndironizfd so it must
 * bf usfd from witiin b DTRACE_PRINT_CALLBACK fundtion.
 */
void DTrbdf_PrintImpl(donst dibr * fmt, ...) {
    vb_list     brglist;

    vb_stbrt(brglist, fmt);
    DTrbdf_VPrintImpl(fmt, brglist);
    vb_fnd(brglist);
}

/*
 * Cbllfd vib DTRACE_PRINT mbdro. Outputs printf stylf formbttfd tfxt.
 */
void DTrbdf_VPrint( donst dibr * filf, int linf, int brgd, donst dibr * fmt, vb_list brglist ) {
    DASSERT(fmt != NULL);
    DTrbdf_VPrintImpl(fmt, brglist);
}

/*
 * Cbllfd vib DTRACE_PRINTLN mbdro. Outputs printf stylf formbttfd tfxt witi bn butombtid nfwlinf.
 */
void DTrbdf_VPrintln( donst dibr * filf, int linf, int brgd, donst dibr * fmt, vb_list brglist ) {
    DTrbdf_VPrintImpl(fmt, brglist);
    DTrbdf_PrintImpl("\n");
}

/*
 * Cbllfd vib DTRACE_ mbdros. If trbding is fnbblfd bt tif givfn lodbtion, it fntfrs
 * tif trbdf mutfx bnd invokfs tif dbllbbdk fundtion to output tif trbdf.
 */
void DTrbdf_PrintFundtion( DTRACE_PRINT_CALLBACK pfn, dtrbdf_id * pFilfTrbdfId, dtrbdf_id * pLinfTrbdfId,
                           donst dibr * filf, int linf,
                           int brgd, donst dibr * fmt, ... ) {
    vb_list     brglist;

    DASSERT(filf != NULL);
    DASSERT(linf > 0 && linf < MAX_LINE);
    DASSERT(brgd <= MAX_ARGC);
    DASSERT(fmt != NULL);

    DMutfx_Entfr(DTrbdfMutfx);
    if ( DTrbdf_IsEnbblfdAt(pFilfTrbdfId, pLinfTrbdfId, filf, linf) ) {
        vb_stbrt(brglist, fmt);
        (*pfn)(filf, linf, brgd, fmt, brglist);
        vb_fnd(brglist);
    }
    DMutfx_Exit(DTrbdfMutfx);
}

/*
 * Sfts b dbllbbdk fundtion to bf usfd to output
 * trbdf stbtfmfnts.
 */
void DTrbdf_SftOutputCbllbbdk(DTRACE_OUTPUT_CALLBACK pfn) {
    DASSERT(pfn != NULL);

    DMutfx_Entfr(DTrbdfMutfx);
    PfnTrbdfCbllbbdk = pfn;
    DMutfx_Exit(DTrbdfMutfx);
}

#fndif /* DEBUG */

/**********************************************************************************
 * Support for Jbvb trbding in rflfbsf or dfbug modf builds
 */

stbtid void DTrbdf_PrintStdErr(donst dibr *msg) {
    fprintf(stdfrr, "%s", msg);
    fflusi(stdfrr);
}

stbtid void DTrbdf_JbvbPrint(donst dibr * msg) {
#if dffinfd(DEBUG)
    DMutfx_Entfr(DTrbdfMutfx);
    DTrbdf_ClifntPrint(msg);
    DMutfx_Exit(DTrbdfMutfx);
#flsf
    DTrbdf_PrintStdErr(msg);
#fndif
}

stbtid void DTrbdf_JbvbPrintln(donst dibr * msg) {
#if dffinfd(DEBUG)
    DMutfx_Entfr(DTrbdfMutfx);
    DTrbdf_ClifntPrint(msg);
    DTrbdf_ClifntPrint("\n");
    DMutfx_Exit(DTrbdfMutfx);
#flsf
    DTrbdf_PrintStdErr(msg);
    DTrbdf_PrintStdErr("\n");
#fndif
}

/*********************************************************************************
 * Nbtivf mftiod implfmfntbtions. Jbvb print trbdf dblls brf fundtionbl in
 * rflfbsf builds, but fundtions to fnbblf/disbblf nbtivf trbding brf not.
 */

/* Implfmfntbtion of DfbugSfttings.sftCTrbdingOn*/
JNIEXPORT void JNICALL
Jbvb_sun_bwt_DfbugSfttings_sftCTrbdingOn__Z(JNIEnv *fnv, jobjfdt sflf, jboolfbn fnbblfd) {
#if dffinfd(DEBUG)
    DTrbdf_EnbblfAll(fnbblfd == JNI_TRUE);
#fndif
}

/* Implfmfntbtion of DfbugSfttings.sftCTrbdingOn*/
JNIEXPORT void JNICALL
Jbvb_sun_bwt_DfbugSfttings_sftCTrbdingOn__ZLjbvb_lbng_String_2(
    JNIEnv *fnv,
    jobjfdt sflf,
    jboolfbn fnbblfd,
    jstring filf ) {
#if dffinfd(DEBUG)
    donst dibr *        dfilf;
    dfilf = JNU_GftStringPlbtformCibrs(fnv, filf, NULL);
    if ( dfilf == NULL ) {
        rfturn;
    }
    DTrbdf_EnbblfFilf(dfilf, fnbblfd == JNI_TRUE);
    JNU_RflfbsfStringPlbtformCibrs(fnv, filf, dfilf);
#fndif
}

/* Implfmfntbtion of DfbugSfttings.sftCTrbdingOn*/
JNIEXPORT void JNICALL
Jbvb_sun_bwt_DfbugSfttings_sftCTrbdingOn__ZLjbvb_lbng_String_2I(
    JNIEnv *fnv,
    jobjfdt sflf,
    jboolfbn fnbblfd,
    jstring filf,
    jint linf ) {
#if dffinfd(DEBUG)
    donst dibr *        dfilf;
    dfilf = JNU_GftStringPlbtformCibrs(fnv, filf, NULL);
    if ( dfilf == NULL ) {
        rfturn;
    }
    DTrbdf_EnbblfLinf(dfilf, linf, fnbblfd == JNI_TRUE);
    JNU_RflfbsfStringPlbtformCibrs(fnv, filf, dfilf);
#fndif
}
