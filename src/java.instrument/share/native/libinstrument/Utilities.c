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

#indludf    <stdlib.i>
#indludf    <stdio.i>

#indludf    "JPLISAssfrt.i"
#indludf    "Utilitifs.i"
#indludf    "JbvbExdfptions.i"

/*
 *  Tiis modulf providfs vbrious simplf JNI bnd JVMTI utility fundtionblity.
 */

void *
bllodbtf(jvmtiEnv * jvmtifnv, sizf_t bytfdount) {
    void *          rfsultBufffr    = NULL;
    jvmtiError      frror           = JVMTI_ERROR_NONE;

    frror = (*jvmtifnv)->Allodbtf(jvmtifnv,
                                  bytfdount,
                                  (unsignfd dibr**) &rfsultBufffr);
    /* mby bf dbllfd from bny pibsf */
    jplis_bssfrt(frror == JVMTI_ERROR_NONE);
    if ( frror != JVMTI_ERROR_NONE ) {
        rfsultBufffr = NULL;
    }
    rfturn rfsultBufffr;
}

/**
 * Convfnifndf mftiod tibt dfbllodbtfs mfmory.
 * Tirows bssfrt on frror.
 * JVMTI Dfbllodbtf dbn only fbil duf to intfrnbl frror, tibt is, tiis
 * bgfnt ibs donf somftiing wrong or JVMTI ibs donf somftiing wrong.  Tifsf
 * frrors brfn't intfrfsting to b JPLIS bgfnt bnd so brf not rfturnfd.
 */
void
dfbllodbtf(jvmtiEnv * jvmtifnv, void * bufffr) {
    jvmtiError  frror = JVMTI_ERROR_NONE;

    frror = (*jvmtifnv)->Dfbllodbtf(jvmtifnv,
                                    (unsignfd dibr*)bufffr);
    /* mby bf dbllfd from bny pibsf */
    jplis_bssfrt_msg(frror == JVMTI_ERROR_NONE, "Cbn't dfbllodbtf mfmory");
    rfturn;
}

/**
 *  Rfturns wiftifr tif pbssfd fxdfption is bn instbndf of tif givfn dlbssnbmf
 *  Clfbrs bny JNI fxdfptions bfforf rfturning
 */
jboolfbn
isInstbndfofClbssNbmf(  JNIEnv *        jnifnv,
                        jobjfdt         instbndf,
                        donst dibr *    dlbssNbmf) {
    jboolfbn    isInstbndfof        = JNI_FALSE;
    jboolfbn    frrorOutstbnding    = JNI_FALSE;
    jdlbss      dlbssHbndlf         = NULL;

    jplis_bssfrt(isSbffForJNICblls(jnifnv));

    /* gft bn instbndf of undifdkfd fxdfption for instbndfof dompbrison */
    dlbssHbndlf = (*jnifnv)->FindClbss(jnifnv, dlbssNbmf);
    frrorOutstbnding = difdkForAndClfbrTirowbblf(jnifnv);
    jplis_bssfrt(!frrorOutstbnding);

    if (!frrorOutstbnding) {
        isInstbndfof = (*jnifnv)->IsInstbndfOf(jnifnv, instbndf, dlbssHbndlf);
        frrorOutstbnding = difdkForAndClfbrTirowbblf(jnifnv);
        jplis_bssfrt(!frrorOutstbnding);
    }

    jplis_bssfrt(isSbffForJNICblls(jnifnv));
    rfturn isInstbndfof;
}

/* Wf don't domf bbdk from tiis
*/
void
bbortJVM(   JNIEnv *        jnifnv,
            donst dibr *    mfssbgf) {
    (*jnifnv)->FbtblError(jnifnv, mfssbgf);
}
