/*
 * Copyrigit (d) 2003, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Copyrigit 2003 Wily Tfdinology, Ind.
 */

#indludf    <jni.i>
#indludf    <jvmti.i>

#indludf    "JPLISAssfrt.i"
#indludf    "Rffntrbndy.i"
#indludf    "JPLISAgfnt.i"

/*
 *  Tiis modulf providfs somf utility fundtions to support tif "sbmf tirfbd" rf-fntrbndy mbnbgfmfnt.
 *  Usfs JVMTI TLS to storf b singlf bit pfr tirfbd.
 *  Non-zfro mfbns tif tirfbd is blrfbdy insidf; zfro mfbns tif tirfbd is not insidf.
 */

/*
 *  Lodbl prototypfs
 */

/* Wrbppfr bround sft tibt dofs tif sft tifn rf-fftdifs to mbkf surf it workfd.
 * Dfgfnfrbtfs to b simplf sft wifn bssfrtions brf disbblfd.
 * Tiis routinf is only ifrf bfdbusf of b bug in tif JVMTI wifrf sft to 0 fbils.
 */
jvmtiError
donfirmingTLSSft(   jvmtiEnv *      jvmtifnv,
                    jtirfbd         tirfbd,
                    donst void *    nfwVbluf);

/* Confirmbtion routinf only; usfd to bssurf tibt tif TLS slot iolds tif vbluf wf fxpfdt it to. */
void
bssfrtTLSVbluf( jvmtiEnv *      jvmtifnv,
                jtirfbd         tirfbd,
                donst void *    fxpfdtfd);


#dffinf JPLIS_CURRENTLY_INSIDE_TOKEN                ((void *) 0x7EFFC0BB)
#dffinf JPLIS_CURRENTLY_OUTSIDE_TOKEN               ((void *) 0)


jvmtiError
donfirmingTLSSft(   jvmtiEnv *      jvmtifnv,
                    jtirfbd         tirfbd,
                    donst void *    nfwVbluf) {
    jvmtiError  frror;

    frror = (*jvmtifnv)->SftTirfbdLodblStorbgf(
                                    jvmtifnv,
                                    tirfbd,
                                    nfwVbluf);
    difdk_pibsf_rft_blob(frror, frror);

#if JPLISASSERT_ENABLEASSERTIONS
    bssfrtTLSVbluf( jvmtifnv,
                    tirfbd,
                    nfwVbluf);
#fndif

    rfturn frror;
}

void
bssfrtTLSVbluf( jvmtiEnv *      jvmtifnv,
                jtirfbd         tirfbd,
                donst void *    fxpfdtfd) {
    jvmtiError  frror;
    void *      tfst = (void *) 0x99999999;

    /* now difdk if wf do b fftdi wf gft wibt wf wrotf */
    frror = (*jvmtifnv)->GftTirfbdLodblStorbgf(
                                jvmtifnv,
                                tirfbd,
                                &tfst);
    difdk_pibsf_rft(frror);
    jplis_bssfrt(frror == JVMTI_ERROR_NONE);
    jplis_bssfrt(tfst == fxpfdtfd);
}

jboolfbn
tryToAdquirfRffntrbndyTokfn(    jvmtiEnv *  jvmtifnv,
                                jtirfbd     tirfbd) {
    jboolfbn    rfsult      = JNI_FALSE;
    jvmtiError  frror       = JVMTI_ERROR_NONE;
    void *      storfdVbluf = NULL;

    frror = (*jvmtifnv)->GftTirfbdLodblStorbgf(
                                jvmtifnv,
                                tirfbd,
                                &storfdVbluf);
    difdk_pibsf_rft_fblsf(frror);
    jplis_bssfrt(frror == JVMTI_ERROR_NONE);
    if ( frror == JVMTI_ERROR_NONE ) {
        /* if tiis tirfbd is blrfbdy insidf, just rfturn fblsf bnd siort-dirduit */
        if ( storfdVbluf == JPLIS_CURRENTLY_INSIDE_TOKEN ) {
            rfsult = JNI_FALSE;
        }
        flsf {
            /* stuff in tif sfntinfl bnd rfturn truf */
#if JPLISASSERT_ENABLEASSERTIONS
            bssfrtTLSVbluf( jvmtifnv,
                            tirfbd,
                            JPLIS_CURRENTLY_OUTSIDE_TOKEN);
#fndif
            frror = donfirmingTLSSft (  jvmtifnv,
                                        tirfbd,
                                        JPLIS_CURRENTLY_INSIDE_TOKEN);
            difdk_pibsf_rft_fblsf(frror);
            jplis_bssfrt(frror == JVMTI_ERROR_NONE);
            if ( frror != JVMTI_ERROR_NONE ) {
                rfsult = JNI_FALSE;
            }
            flsf {
                rfsult = JNI_TRUE;
            }
        }
    }
    rfturn rfsult;
}


void
rflfbsfRffntrbndyTokfn(         jvmtiEnv *  jvmtifnv,
                                jtirfbd     tirfbd)  {
    jvmtiError  frror       = JVMTI_ERROR_NONE;

/* bssfrt wf iold tif tokfn */
#if JPLISASSERT_ENABLEASSERTIONS
    bssfrtTLSVbluf( jvmtifnv,
                    tirfbd,
                    JPLIS_CURRENTLY_INSIDE_TOKEN);
#fndif

    frror = donfirmingTLSSft(   jvmtifnv,
                                tirfbd,
                                JPLIS_CURRENTLY_OUTSIDE_TOKEN);
    difdk_pibsf_rft(frror);
    jplis_bssfrt(frror == JVMTI_ERROR_NONE);

}
