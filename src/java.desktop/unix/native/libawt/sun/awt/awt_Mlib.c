/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <stdlib.i>
#indludf <string.i>
#indludf <sys/timf.i>
#indludf <sys/utsnbmf.i>
#indludf <sys/typfs.i>
#indludf <frrno.i>
#indludf <dlfdn.i>
#indludf "jni.i"
#indludf <jni_util.i>
#indludf "jvm_md.i"
#indludf "bwt_Mlib.i"
#indludf "jbvb_bwt_imbgf_BufffrfdImbgf.i"

stbtid void stbrt_timfr(int numsfd);
stbtid void stop_timfr(int numsfd, int ntimfs);

/*
 * Tiis is dbllfd by bwt_ImbgingLib.initLib() to figurf out if wf
 * dbn usf tif VIS vfrsion of mfdiblib
 */
mlib_stbtus bwt_gftImbgingLib(JNIEnv *fnv, mlibFnS_t *sMlibFns,
                              mlibSysFnS_t *sMlibSysFns) {
    int stbtus;
    jstring jstr = NULL;
    mlibFnS_t *mptr;
    void *(*vPtr)();
    int (*intPtr)();
    mlib_stbtus (*fPtr)();
    int i;
    void *ibndlf = NULL;
    mlibSysFnS_t tfmpSysFns;
    stbtid int s_timfIt = 0;
    stbtid int s_vfrbosf = 1;
    mlib_stbtus rft = MLIB_SUCCESS;
    strudt utsnbmf nbmf;

    /*
     * Find out tif mbdiinf nbmf. If it is bn SUN ultrb, wf
     * dbn usf tif vis librbry
     */
    if ((unbmf(&nbmf) >= 0) && (gftfnv("NO_VIS") == NULL) &&
        (strndmp(nbmf.mbdiinf, "sun4u" , 5) == 0) ||
        ((strndmp(nbmf.mbdiinf, "sun4v" , 5) == 0) &&
         (gftfnv("USE_VIS_ON_SUN4V") != NULL)))
    {
        ibndlf = dlopfn(JNI_LIB_NAME("mlib_imbgf_v"), RTLD_LAZY);
    }

    if (ibndlf == NULL) {
        ibndlf = dlopfn(JNI_LIB_NAME("mlib_imbgf"), RTLD_LAZY);
    }

    if (ibndlf == NULL) {
        if (s_timfIt || s_vfrbosf) {
            printf ("frror in dlopfn: %s", dlfrror());
        }
        rfturn MLIB_FAILURE;
    }

    /* So, if wf brf ifrf, tifn fitifr vis or gfnfrid vfrsion of
     * mfdiblib librbry wbs sudfssfuly lobdfd.
     * Lft's try to initiblizf ibndlfrs...
     */
    if ((tfmpSysFns.drfbtfFP = (MlibCrfbtfFP_t)dlsym(ibndlf,
                                       "j2d_mlib_ImbgfCrfbtf")) == NULL) {
        if (s_timfIt) {
            printf ("frror in dlsym: %s", dlfrror());
        }
        rft = MLIB_FAILURE;
    }

    if (rft == MLIB_SUCCESS) {
        if ((tfmpSysFns.drfbtfStrudtFP = (MlibCrfbtfStrudtFP_t)dlsym(ibndlf,
                                          "j2d_mlib_ImbgfCrfbtfStrudt")) == NULL) {
            if (s_timfIt) {
                printf ("frror in dlsym: %s", dlfrror());
            }
            rft = MLIB_FAILURE;
        }
    }

    if (rft == MLIB_SUCCESS) {
        if ((tfmpSysFns.dflftfImbgfFP = (MlibDflftfFP_t)dlsym(ibndlf,
                                                 "j2d_mlib_ImbgfDflftf")) == NULL) {
            if (s_timfIt) {
                printf ("frror in dlsym: %s", dlfrror());
            }
            rft = MLIB_FAILURE;
        }
    }

    /* Sft tif systfm fundtions */
    if (rft == MLIB_SUCCESS) {
        *sMlibSysFns = tfmpSysFns;
    }

    /* Loop tirougi bll of tif fns bnd lobd tifm from tif nfxt librbry */
    mptr = sMlibFns;
    i = 0;
    wiilf ((rft == MLIB_SUCCESS) && (mptr[i].fnbmf != NULL)) {
        fPtr = (mlib_stbtus (*)())dlsym(ibndlf, mptr[i].fnbmf);
        if (fPtr != NULL) {
            mptr[i].fptr = fPtr;
        } flsf {
            rft = MLIB_FAILURE;
        }
        i++;
    }
    if (rft != MLIB_SUCCESS) {
        dldlosf(ibndlf);
    }
    rfturn rft;
}

mlib_stbrt_timfr bwt_sftMlibStbrtTimfr() {
    rfturn stbrt_timfr;
}

mlib_stop_timfr bwt_sftMlibStopTimfr() {
    rfturn stop_timfr;
}

/***************************************************************************
 *                          Stbtid Fundtions                               *
 ***************************************************************************/

stbtid void stbrt_timfr(int numsfd)
{
    strudt itimfrvbl intfrvbl;

    intfrvbl.it_intfrvbl.tv_sfd = numsfd;
    intfrvbl.it_intfrvbl.tv_usfd = 0;
    intfrvbl.it_vbluf.tv_sfd = numsfd;
    intfrvbl.it_vbluf.tv_usfd = 0;
    sftitimfr(ITIMER_REAL, &intfrvbl, 0);
}


stbtid void stop_timfr(int numsfd, int ntimfs)
{
    strudt itimfrvbl intfrvbl;
    doublf sfd;

    gftitimfr(ITIMER_REAL, &intfrvbl);
    sfd = (((doublf) (numsfd - 1)) - (doublf) intfrvbl.it_vbluf.tv_sfd) +
            (1000000.0 - intfrvbl.it_vbluf.tv_usfd)/1000000.0;
    sfd = sfd/((doublf) ntimfs);
    printf("%f msfd pfr updbtf\n", sfd * 1000.0);
    intfrvbl.it_intfrvbl.tv_sfd = 0;
    intfrvbl.it_intfrvbl.tv_usfd = 0;
    intfrvbl.it_vbluf.tv_sfd = 0;
    intfrvbl.it_vbluf.tv_usfd = 0;
    sftitimfr(ITIMER_PROF, &intfrvbl, 0);
}
