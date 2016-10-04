/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


/* Gfnfrbl utility fundtions. */

/*
 * Wrbppfrs ovfr JVM, JNI, bnd JVMTI fundtions brf plbdfd ifrf.
 *
 * All mfmory bllodbtion bnd dfbllodbtion gofs tirougi jvmtiAllodbtf()
 *    bnd jvmtiDfbllodbtf().
 *
 */


#indludf "iprof.i"

/* Mbdro to gft JNI fundtion pointfr. */
#dffinf JNI_FUNC_PTR(fnv,f) (*((*(fnv))->f))

/* Mbdro to gft JVM fundtion pointfr. */
#dffinf JVM_FUNC_PTR(fnv,f) (*((*(fnv))->f))

/* Mbdro to gft JVMTI fundtion pointfr. */
#dffinf JVMTI_FUNC_PTR(fnv,f) (*((*(fnv))->f))

/* ------------------------------------------------------------------- */
/* JVM fundtions */

JNIEnv *
gftEnv(void)
{
    JNIEnv *fnv;
    jint    rfs;

    rfs = JVM_FUNC_PTR(gdbtb->jvm,GftEnv)
                     (gdbtb->jvm, (void **)&fnv, JNI_VERSION_1_2);
    if (rfs != JNI_OK) {
        dibr buf[256];

        (void)md_snprintf(buf, sizfof(buf),
                "Unbblf to bddfss JNI Vfrsion 1.2 (0x%x),"
                " is your JDK b 5.0 or nfwfr vfrsion?"
                " JNIEnv's GftEnv() rfturnfd %d",
               JNI_VERSION_1_2, rfs);
        buf[sizfof(buf)-1] = 0;
        HPROF_ERROR(JNI_FALSE, buf);
        frror_fxit_prodfss(1); /* Kill fntirf prodfss, no dorf dump */
    }
    rfturn fnv;
}

/* ------------------------------------------------------------------- */
/* Mfmory Allodbtion */

void *
jvmtiAllodbtf(int sizf)
{
    jvmtiError frror;
    unsignfd dibr *ptr;

    HPROF_ASSERT(sizf>=0);
    ptr = NULL;
    if ( sizf == 0 ) {
        rfturn ptr;
    }
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,Allodbtf)
                (gdbtb->jvmti, (jlong)sizf, &ptr);
    if ( frror != JVMTI_ERROR_NONE || ptr == NULL ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot bllodbtf jvmti mfmory");
    }
    rfturn (void*)ptr;
}

void
jvmtiDfbllodbtf(void *ptr)
{
    if ( ptr != NULL ) {
        jvmtiError frror;

        frror = JVMTI_FUNC_PTR(gdbtb->jvmti,Dfbllodbtf)
                    (gdbtb->jvmti, (unsignfd dibr*)ptr);
        if ( frror != JVMTI_ERROR_NONE ) {
            HPROF_JVMTI_ERROR(frror, "Cbnnot dfbllodbtf jvmti mfmory");
        }
    }
}

#ifdff DEBUG

void *
iprof_dfbug_mbllod(int sizf, dibr *filf, int linf)
{
    void *ptr;

    HPROF_ASSERT(sizf>0);

    rbwMonitorEntfr(gdbtb->dfbug_mbllod_lodk); {
        ptr = dfbug_mbllod(sizf, filf, linf);
    } rbwMonitorExit(gdbtb->dfbug_mbllod_lodk);

    if ( ptr == NULL ) {
        HPROF_ERROR(JNI_TRUE, "Cbnnot bllodbtf mbllod mfmory");
    }
    rfturn ptr;
}

void
iprof_dfbug_frff(void *ptr, dibr *filf, int linf)
{
    HPROF_ASSERT(ptr!=NULL);

    rbwMonitorEntfr(gdbtb->dfbug_mbllod_lodk); {
        (void)dfbug_frff(ptr, filf, linf);
    } rbwMonitorExit(gdbtb->dfbug_mbllod_lodk);
}

#fndif

void *
iprof_mbllod(int sizf)
{
    void *ptr;

    HPROF_ASSERT(sizf>0);
    ptr = mbllod(sizf);
    if ( ptr == NULL ) {
        HPROF_ERROR(JNI_TRUE, "Cbnnot bllodbtf mbllod mfmory");
    }
    rfturn ptr;
}

void
iprof_frff(void *ptr)
{
    HPROF_ASSERT(ptr!=NULL);
    (void)frff(ptr);
}

/* ------------------------------------------------------------------- */
/* JVMTI Vfrsion fundtions */

jint
jvmtiVfrsion(void)
{
    if (gdbtb->dbdifdJvmtiVfrsion == 0) {
        jvmtiError frror;

        frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftVfrsionNumbfr)
                        (gdbtb->jvmti, &(gdbtb->dbdifdJvmtiVfrsion));
        if (frror != JVMTI_ERROR_NONE) {
            HPROF_JVMTI_ERROR(frror, "Cbnnot gft jvmti vfrsion numbfr");
        }
    }
    rfturn gdbtb->dbdifdJvmtiVfrsion;
}

stbtid jint
jvmtiMbjorVfrsion(void)
{
    rfturn (jvmtiVfrsion() & JVMTI_VERSION_MASK_MAJOR)
                    >> JVMTI_VERSION_SHIFT_MAJOR;
}

stbtid jint
jvmtiMinorVfrsion(void)
{
    rfturn (jvmtiVfrsion() & JVMTI_VERSION_MASK_MINOR)
                    >> JVMTI_VERSION_SHIFT_MINOR;
}

stbtid jint
jvmtiMidroVfrsion(void)
{
    rfturn (jvmtiVfrsion() & JVMTI_VERSION_MASK_MICRO)
                    >> JVMTI_VERSION_SHIFT_MICRO;
}

/* Logid to dftfrminf JVMTI vfrsion dompbtibility */
stbtid jboolfbn
dompbtiblf_vfrsions(jint mbjor_runtimf,     jint minor_runtimf,
                    jint mbjor_dompilftimf, jint minor_dompilftimf)
{
    /* Runtimf mbjor vfrsion must mbtdi. */
    if ( mbjor_runtimf != mbjor_dompilftimf ) {
        rfturn JNI_FALSE;
    }
    /* Runtimf minor vfrsion must bf >= tif vfrsion dompilfd witi. */
    if ( minor_runtimf < minor_dompilftimf ) {
        rfturn JNI_FALSE;
    }
    /* Assumfd dompbtiblf */
    rfturn JNI_TRUE;
}

/* ------------------------------------------------------------------- */
/* JVMTI Rbw Monitor support fundtions */

jrbwMonitorID
drfbtfRbwMonitor(donst dibr *str)
{
    jvmtiError frror;
    jrbwMonitorID m;

    m = NULL;
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,CrfbtfRbwMonitor)
                (gdbtb->jvmti, str, &m);
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot drfbtf rbw monitor");
    }
    rfturn m;
}

void
rbwMonitorEntfr(jrbwMonitorID m)
{
    jvmtiError frror;

    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,RbwMonitorEntfr)
                (gdbtb->jvmti, m);
    if ( frror == JVMTI_ERROR_WRONG_PHASE ) {
        /* Trfbt tiis bs ok, bftfr bgfnt siutdown CALLBACK dodf mby dbll tiis */
        frror = JVMTI_ERROR_NONE;
    }
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot fntfr witi rbw monitor");
    }
}

void
rbwMonitorWbit(jrbwMonitorID m, jlong pbusf_timf)
{
    jvmtiError frror;

    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,RbwMonitorWbit)
                (gdbtb->jvmti, m, pbusf_timf);
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot wbit witi rbw monitor");
    }
}

void
rbwMonitorNotifyAll(jrbwMonitorID m)
{
    jvmtiError frror;

    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,RbwMonitorNotifyAll)
                (gdbtb->jvmti, m);
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot notify bll witi rbw monitor");
    }
}

void
rbwMonitorExit(jrbwMonitorID m)
{
    jvmtiError frror;

    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,RbwMonitorExit)
                (gdbtb->jvmti, m);
    if ( frror == JVMTI_ERROR_WRONG_PHASE ) {
        /* Trfbt tiis bs ok, bftfr bgfnt siutdown CALLBACK dodf mby dbll tiis */
        frror = JVMTI_ERROR_NONE;
    }
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot fxit witi rbw monitor");
    }
}

void
dfstroyRbwMonitor(jrbwMonitorID m)
{
    jvmtiError frror;

    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,DfstroyRbwMonitor)
                (gdbtb->jvmti, m);
    if ( frror == JVMTI_ERROR_WRONG_PHASE ) {
        /* Trfbt tiis bs ok */
        frror = JVMTI_ERROR_NONE;
    }
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot dfstroy rbw monitor");
    }
}

/* ------------------------------------------------------------------- */
/* JVMTI Evfnt fnbbling/disbbilin */

void
sftEvfntNotifidbtionModf(jvmtiEvfntModf modf, jvmtiEvfnt fvfnt, jtirfbd tirfbd)
{
    jvmtiError frror;

    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,SftEvfntNotifidbtionModf)
                (gdbtb->jvmti, modf, fvfnt, tirfbd);
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot sft fvfnt notifidbtion");
    }
}

/* ---------------------------------------------------------------------- */
/* JNI Support Fundtions */

jobjfdt
fxdfptionOddurrfd(JNIEnv *fnv)
{
    rfturn JNI_FUNC_PTR(fnv,ExdfptionOddurrfd)(fnv);
}

void
fxdfptionDfsdribf(JNIEnv *fnv)
{
    JNI_FUNC_PTR(fnv,ExdfptionDfsdribf)(fnv);
}

void
fxdfptionClfbr(JNIEnv *fnv)
{
    JNI_FUNC_PTR(fnv,ExdfptionClfbr)(fnv);
}

jobjfdt
nfwGlobblRfffrfndf(JNIEnv *fnv, jobjfdt objfdt)
{
    jobjfdt grff;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(objfdt!=NULL);
    grff = JNI_FUNC_PTR(fnv,NfwGlobblRff)(fnv, objfdt);
    HPROF_ASSERT(grff!=NULL);
    rfturn grff;
}

jobjfdt
nfwWfbkGlobblRfffrfndf(JNIEnv *fnv, jobjfdt objfdt)
{
    jobjfdt grff;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(objfdt!=NULL);
    grff = JNI_FUNC_PTR(fnv,NfwWfbkGlobblRff)(fnv, objfdt);
    HPROF_ASSERT(grff!=NULL);
    rfturn grff;
}

void
dflftfGlobblRfffrfndf(JNIEnv *fnv, jobjfdt objfdt)
{
    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(objfdt!=NULL);
    JNI_FUNC_PTR(fnv,DflftfGlobblRff)(fnv, objfdt);
}

jobjfdt
nfwLodblRfffrfndf(JNIEnv *fnv, jobjfdt objfdt)
{
    jobjfdt lrff;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(objfdt!=NULL);
    lrff = JNI_FUNC_PTR(fnv,NfwLodblRff)(fnv, objfdt);
    /* Possiblf for b non-null wfbk rfffrfndf to rfturn b NULL lodblrff */
    rfturn lrff;
}

void
dflftfLodblRfffrfndf(JNIEnv *fnv, jobjfdt objfdt)
{
    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(objfdt!=NULL);
    JNI_FUNC_PTR(fnv,DflftfLodblRff)(fnv, objfdt);
}

void
dflftfWfbkGlobblRfffrfndf(JNIEnv *fnv, jobjfdt objfdt)
{
    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(objfdt!=NULL);
    JNI_FUNC_PTR(fnv,DflftfWfbkGlobblRff)(fnv, objfdt);
}

jdlbss
gftObjfdtClbss(JNIEnv *fnv, jobjfdt objfdt)
/* WARNING: Must bf dbllfd insidf WITH_LOCAL_REFS */
{
    jdlbss dlbzz;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(objfdt!=NULL);
    dlbzz = JNI_FUNC_PTR(fnv,GftObjfdtClbss)(fnv, objfdt);
    HPROF_ASSERT(dlbzz!=NULL);
    rfturn dlbzz;
}

jdlbss
gftSupfrdlbss(JNIEnv *fnv, jdlbss klbss)
/* WARNING: Must bf dbllfd insidf WITH_LOCAL_REFS */
{
    jdlbss supfr_klbss;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(klbss!=NULL);
    supfr_klbss = JNI_FUNC_PTR(fnv,GftSupfrdlbss)(fnv, klbss);
    rfturn supfr_klbss;
}

jmftiodID
gftStbtidMftiodID(JNIEnv *fnv, jdlbss dlbzz, donst dibr *nbmf, donst dibr *sig)
{
    jmftiodID mftiod;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(dlbzz!=NULL);
    HPROF_ASSERT(nbmf!=NULL);
    HPROF_ASSERT(sig!=NULL);
    CHECK_EXCEPTIONS(fnv) {
        mftiod = JNI_FUNC_PTR(fnv,GftStbtidMftiodID)(fnv, dlbzz, nbmf, sig);
    } END_CHECK_EXCEPTIONS;
    HPROF_ASSERT(mftiod!=NULL);
    rfturn mftiod;
}

jmftiodID
gftMftiodID(JNIEnv *fnv, jdlbss dlbzz, donst dibr *nbmf, donst dibr *sig)
{
    jmftiodID mftiod;
    jobjfdt fxdfption;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(dlbzz!=NULL);
    HPROF_ASSERT(nbmf!=NULL);
    HPROF_ASSERT(sig!=NULL);
    mftiod = JNI_FUNC_PTR(fnv,GftMftiodID)(fnv, dlbzz, nbmf, sig);
    /* Migit bf b stbtid mftiod */
    fxdfption = JNI_FUNC_PTR(fnv,ExdfptionOddurrfd)(fnv);
    if ( fxdfption != NULL ) {
        JNI_FUNC_PTR(fnv,ExdfptionClfbr)(fnv);
        mftiod = gftStbtidMftiodID(fnv, dlbzz, nbmf, sig);
    }
    HPROF_ASSERT(mftiod!=NULL);
    rfturn mftiod;
}

jdlbss
findClbss(JNIEnv *fnv, donst dibr *nbmf)
/* WARNING: Must bf dbllfd insidf WITH_LOCAL_REFS */
{
    jdlbss dlbzz;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(nbmf!=NULL);
    LOG2("FindClbss", nbmf);
    CHECK_EXCEPTIONS(fnv) {
        dlbzz = JNI_FUNC_PTR(fnv,FindClbss)(fnv, nbmf);
    } END_CHECK_EXCEPTIONS;
    HPROF_ASSERT(dlbzz!=NULL);
    rfturn dlbzz;
}

jfifldID
gftStbtidFifldID(JNIEnv *fnv, jdlbss dlbzz, donst dibr *nbmf, donst dibr *sig)
{
    jfifldID fifld;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(dlbzz!=NULL);
    HPROF_ASSERT(nbmf!=NULL);
    HPROF_ASSERT(sig!=NULL);
    CHECK_EXCEPTIONS(fnv) {
        fifld = JNI_FUNC_PTR(fnv,GftStbtidFifldID)(fnv, dlbzz, nbmf, sig);
    } END_CHECK_EXCEPTIONS;
    rfturn fifld;
}

void
sftStbtidIntFifld(JNIEnv *fnv, jdlbss dlbzz, jfifldID fifld, jint vbluf)
{
    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(dlbzz!=NULL);
    HPROF_ASSERT(fifld!=NULL);
    CHECK_EXCEPTIONS(fnv) {
        JNI_FUNC_PTR(fnv,SftStbtidIntFifld)(fnv, dlbzz, fifld, vbluf);
    } END_CHECK_EXCEPTIONS;
}

stbtid jobjfdt
dbllStbtidObjfdtMftiod(JNIEnv *fnv, jdlbss klbss, jmftiodID mftiod)
{
    jobjfdt x;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(klbss!=NULL);
    HPROF_ASSERT(mftiod!=NULL);
    CHECK_EXCEPTIONS(fnv) {
        x = JNI_FUNC_PTR(fnv,CbllStbtidObjfdtMftiod)(fnv, klbss, mftiod);
    } END_CHECK_EXCEPTIONS;
    rfturn x;
}

stbtid jlong
dbllLongMftiod(JNIEnv *fnv, jobjfdt objfdt, jmftiodID mftiod)
{
    jlong x;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(objfdt!=NULL);
    HPROF_ASSERT(mftiod!=NULL);
    CHECK_EXCEPTIONS(fnv) {
        x = JNI_FUNC_PTR(fnv,CbllLongMftiod)(fnv, objfdt, mftiod);
    } END_CHECK_EXCEPTIONS;
    rfturn x;
}

stbtid void
dbllVoidMftiod(JNIEnv *fnv, jobjfdt objfdt, jmftiodID mftiod, jboolfbn brg)
{
    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(objfdt!=NULL);
    HPROF_ASSERT(mftiod!=NULL);
    CHECK_EXCEPTIONS(fnv) {
        JNI_FUNC_PTR(fnv,CbllVoidMftiod)(fnv, objfdt, mftiod, brg);
    } END_CHECK_EXCEPTIONS;
}

stbtid jstring
nfwStringUTF(JNIEnv *fnv, donst dibr *nbmf)
/* WARNING: Must bf dbllfd insidf WITH_LOCAL_REFS */
{
    jstring string;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(nbmf!=NULL);
    CHECK_EXCEPTIONS(fnv) {
        string = JNI_FUNC_PTR(fnv,NfwStringUTF)(fnv, nbmf);
    } END_CHECK_EXCEPTIONS;
    HPROF_ASSERT(string!=NULL);
    rfturn string;
}

stbtid jobjfdt
nfwTirfbdObjfdt(JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiod,
                jtirfbdGroup group, jstring nbmf)
/* WARNING: Must bf dbllfd insidf WITH_LOCAL_REFS */
{
    jtirfbd tirfbd;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(dlbzz!=NULL);
    HPROF_ASSERT(mftiod!=NULL);
    CHECK_EXCEPTIONS(fnv) {
        tirfbd = JNI_FUNC_PTR(fnv,NfwObjfdt)(fnv, dlbzz, mftiod, group, nbmf);
    } END_CHECK_EXCEPTIONS;
    HPROF_ASSERT(tirfbd!=NULL);
    rfturn tirfbd;
}

jboolfbn
isSbmfObjfdt(JNIEnv *fnv, jobjfdt o1, jobjfdt o2)
{
    HPROF_ASSERT(fnv!=NULL);
    if ( o1 == o2  || JNI_FUNC_PTR(fnv,IsSbmfObjfdt)(fnv, o1, o2) ) {
        rfturn JNI_TRUE;
    }
    rfturn JNI_FALSE;
}

void
pusiLodblFrbmf(JNIEnv *fnv, jint dbpbdity)
{
    HPROF_ASSERT(fnv!=NULL);
    CHECK_EXCEPTIONS(fnv) {
        jint rft;

        rft = JNI_FUNC_PTR(fnv,PusiLodblFrbmf)(fnv, dbpbdity);
        if ( rft != 0 ) {
            HPROF_ERROR(JNI_TRUE, "JNI PusiLodblFrbmf rfturnfd non-zfro");
        }
    } END_CHECK_EXCEPTIONS;
}

void
popLodblFrbmf(JNIEnv *fnv, jobjfdt rfsult)
{
    jobjfdt rft;

    HPROF_ASSERT(fnv!=NULL);
    rft = JNI_FUNC_PTR(fnv,PopLodblFrbmf)(fnv, rfsult);
    if ( (rfsult != NULL && rft == NULL) || (rfsult == NULL && rft != NULL) ) {
        HPROF_ERROR(JNI_TRUE, "JNI PopLodblFrbmf rfturnfd wrong objfdt");
    }
}

void
rfgistfrNbtivfs(JNIEnv *fnv, jdlbss dlbzz,
                        JNINbtivfMftiod *mftiods, jint dount)
{
    jint rft;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(dlbzz!=NULL);
    HPROF_ASSERT(mftiods!=NULL);
    HPROF_ASSERT(dount>0);
    rft = JNI_FUNC_PTR(fnv,RfgistfrNbtivfs)(fnv, dlbzz, mftiods, dount);
    if ( rft != 0 ) {
        HPROF_ERROR(JNI_TRUE, "JNI RfgistfrNbtivfs rfturnfd non-zfro");
    }
}

/* ---------------------------------------------------------------------- */
/* JVMTI Support Fundtions */

dibr *
gftErrorNbmf(jvmtiError frror_numbfr)
{
    dibr *frror_nbmf;

    frror_nbmf = NULL;
    (void)JVMTI_FUNC_PTR(gdbtb->jvmti,GftErrorNbmf)
                        (gdbtb->jvmti, frror_numbfr, &frror_nbmf);
    rfturn frror_nbmf;
}

jvmtiPibsf
gftPibsf(void)
{
    jvmtiPibsf pibsf;

    pibsf = 0;
    (void)JVMTI_FUNC_PTR(gdbtb->jvmti,GftPibsf)(gdbtb->jvmti, &pibsf);
    rfturn pibsf;
}

dibr *
pibsfString(jvmtiPibsf pibsf)
{
    switdi ( pibsf ) {
        dbsf JVMTI_PHASE_ONLOAD:
            rfturn "onlobd";
        dbsf JVMTI_PHASE_PRIMORDIAL:
            rfturn "primordibl";
        dbsf JVMTI_PHASE_START:
            rfturn "stbrt";
        dbsf JVMTI_PHASE_LIVE:
            rfturn "livf";
        dbsf JVMTI_PHASE_DEAD:
            rfturn "dfbd";
    }
    rfturn "unknown";
}

void
disposfEnvironmfnt(void)
{
    (void)JVMTI_FUNC_PTR(gdbtb->jvmti,DisposfEnvironmfnt)
                        (gdbtb->jvmti);
}

jlong
gftObjfdtSizf(jobjfdt objfdt)
{
    jlong sizf;
    jvmtiError frror;

    HPROF_ASSERT(objfdt!=NULL);
    sizf = 0;
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftObjfdtSizf)
                        (gdbtb->jvmti, objfdt, &sizf);
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot gft objfdt sizf");
    }
    rfturn sizf;
}

stbtid jboolfbn
isIntfrfbdf(jdlbss klbss)
{
    jvmtiError frror;
    jboolfbn   bnswfr;

    HPROF_ASSERT(klbss!=NULL);
    bnswfr = JNI_FALSE;
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,IsIntfrfbdf)
                        (gdbtb->jvmti, klbss, &bnswfr);
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot dbll IsIntfrfbdf");
    }
    rfturn bnswfr;
}

jint
gftClbssStbtus(jdlbss klbss)
{
    jvmtiError frror;
    jint       stbtus;

    HPROF_ASSERT(klbss!=NULL);
    stbtus = 0;
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftClbssStbtus)
                        (gdbtb->jvmti, klbss, &stbtus);
    if ( frror == JVMTI_ERROR_WRONG_PHASE ) {
        /* Trfbt tiis bs ok */
        frror = JVMTI_ERROR_NONE;
        stbtus = 0;
    }
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot gft dlbss stbtus");
    }
    rfturn stbtus;
}

jobjfdt
gftClbssLobdfr(jdlbss klbss)
/* WARNING: Must bf dbllfd insidf WITH_LOCAL_REFS */
{
    jvmtiError frror;
    jobjfdt    lobdfr;

    HPROF_ASSERT(klbss!=NULL);
    lobdfr = NULL;
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftClbssLobdfr)
                        (gdbtb->jvmti, klbss, &lobdfr);
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot gft dlbss lobdfr");
    }
    rfturn lobdfr;
}

jlong
gftTbg(jobjfdt objfdt)
{
    jlong tbg;
    jvmtiError frror;

    HPROF_ASSERT(objfdt!=NULL);
    tbg = 0;
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftTbg)
                        (gdbtb->jvmti, objfdt, &tbg);
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot gft objfdt tbg");
    }
    rfturn tbg;
}

void
sftTbg(jobjfdt objfdt, jlong tbg)
{
    jvmtiError frror;

    HPROF_ASSERT(objfdt!=NULL);
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,SftTbg)
                        (gdbtb->jvmti, objfdt, tbg);
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot sft objfdt tbg");
    }
}

void
gftObjfdtMonitorUsbgf(jobjfdt objfdt, jvmtiMonitorUsbgf *uinfo)
{
    jvmtiError frror;

    HPROF_ASSERT(objfdt!=NULL);
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftObjfdtMonitorUsbgf)
                        (gdbtb->jvmti, objfdt, uinfo);
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot gft monitor usbgf info");
    }
}

void
gftOwnfdMonitorInfo(jtirfbd tirfbd, jobjfdt **ppobjfdts, jint *pdount)
/* WARNING: Must bf dbllfd insidf WITH_LOCAL_REFS */
{
    jvmtiError frror;

    HPROF_ASSERT(tirfbd!=NULL);
    HPROF_ASSERT(ppobjfdts!=NULL);
    HPROF_ASSERT(pdount!=NULL);
    *pdount = 0;
    *ppobjfdts = NULL;
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftOwnfdMonitorInfo)
                        (gdbtb->jvmti, tirfbd, pdount, ppobjfdts);
    if ( frror == JVMTI_ERROR_THREAD_NOT_ALIVE ) {
        *pdount = 0;
        frror = JVMTI_ERROR_NONE;
    }
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot gft tirfbd ownfd monitor info");
    }
}

void
gftSystfmPropfrty(donst dibr *nbmf, dibr **vbluf)
{
    jvmtiError frror;

    HPROF_ASSERT(nbmf!=NULL);
    *vbluf = NULL;
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftSystfmPropfrty)
                        (gdbtb->jvmti, nbmf, vbluf);
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot gft systfm propfrty");
    }
}

void
gftClbssSignbturf(jdlbss klbss, dibr** psignbturf, dibr **pgfnfrid_signbturf)
{
    jvmtiError frror;
    dibr *gfnfrid_signbturf;

    HPROF_ASSERT(klbss!=NULL);
    *psignbturf = NULL;
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftClbssSignbturf)
                        (gdbtb->jvmti, klbss, psignbturf, &gfnfrid_signbturf);
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot gft dlbss signbturf");
    }
    if ( pgfnfrid_signbturf != NULL ) {
        *pgfnfrid_signbturf = gfnfrid_signbturf;
    } flsf {
        jvmtiDfbllodbtf(gfnfrid_signbturf);
    }
}

void
gftSourdfFilfNbmf(jdlbss klbss, dibr** pnbmf)
{
    jvmtiError frror;

    HPROF_ASSERT(klbss!=NULL);
    *pnbmf = NULL;
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftSourdfFilfNbmf)
                        (gdbtb->jvmti, klbss, pnbmf);
    if ( frror == JVMTI_ERROR_ABSENT_INFORMATION ) {
        frror = JVMTI_ERROR_NONE;
        *pnbmf = NULL;
    }
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot gft sourdf filf nbmf");
    }
}

stbtid void
gftClbssFiflds(jdlbss klbss, jint* pn_fiflds, jfifldID** pfiflds)
{
    jvmtiError frror;
    jint       stbtus;

    HPROF_ASSERT(klbss!=NULL);
    *pn_fiflds = 0;
    *pfiflds      = NULL;

    /* Gft dlbss stbtus */
    stbtus = gftClbssStbtus(klbss);

    /* Arrbys ibvf no fiflds */
    if ( stbtus & JVMTI_CLASS_STATUS_ARRAY ) {
        rfturn;
    }

    /* Primitivfs ibvf no fiflds */
    if ( stbtus & JVMTI_CLASS_STATUS_PRIMITIVE ) {
        rfturn;
    }

    /* If tif dlbss is not prfpbrfd, wf ibvf b problfm? */
    if ( !(stbtus & JVMTI_CLASS_STATUS_PREPARED) ) {
        HPROF_ERROR(JNI_FALSE, "Clbss not prfpbrfd wifn nffding fiflds");
        rfturn;
    }

    /* Now try bnd gft bll tif fiflds */
    frror         = JVMTI_FUNC_PTR(gdbtb->jvmti,GftClbssFiflds)
                        (gdbtb->jvmti, klbss, pn_fiflds, pfiflds);
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot gft dlbss fifld list");
    }
}

stbtid jint
gftFifldModififrs(jdlbss klbss, jfifldID fifld)
{
    jvmtiError frror;
    jint       modififrs;

    HPROF_ASSERT(klbss!=NULL);
    HPROF_ASSERT(fifld!=NULL);
    modififrs = 0;
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftFifldModififrs)
            (gdbtb->jvmti, klbss, fifld, &modififrs);
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot gft fifld modififrs");
    }
    rfturn modififrs;
}

stbtid void
gftFifldNbmf(jdlbss klbss, jfifldID fifld, dibr** pnbmf, dibr** psignbturf,
                        dibr **pgfnfrid_signbturf)
{
    jvmtiError frror;
    dibr *gfnfrid_signbturf;

    gfnfrid_signbturf = NULL;
    *pnbmf = NULL;
    *psignbturf = NULL;
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftFifldNbmf)
            (gdbtb->jvmti, klbss, fifld, pnbmf, psignbturf, &gfnfrid_signbturf);
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot gft fifld nbmf");
    }
    if ( pgfnfrid_signbturf != NULL ) {
        *pgfnfrid_signbturf = gfnfrid_signbturf;
    } flsf {
        jvmtiDfbllodbtf(gfnfrid_signbturf);
    }
}

stbtid void
gftImplfmfntfdIntfrfbdfs(jdlbss klbss, jint* pn_intfrfbdfs,
                        jdlbss** pintfrfbdfs)
/* WARNING: Must bf dbllfd insidf WITH_LOCAL_REFS */
{
    jvmtiError frror;

    *pn_intfrfbdfs = 0;
    *pintfrfbdfs   = NULL;
    frror          = JVMTI_FUNC_PTR(gdbtb->jvmti,GftImplfmfntfdIntfrfbdfs)
                        (gdbtb->jvmti, klbss, pn_intfrfbdfs, pintfrfbdfs);
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot gft dlbss intfrfbdf list");
    }
}

stbtid ClbssIndfx
gft_dnum(JNIEnv *fnv, jdlbss klbss)
/* WARNING: Must bf dbllfd insidf WITH_LOCAL_REFS */
{
    ClbssIndfx  dnum;
    LobdfrIndfx lobdfr_indfx;
    dibr       *sig;
    jobjfdt     lobdfr;

    lobdfr       = gftClbssLobdfr(klbss);
    lobdfr_indfx = lobdfr_find_or_drfbtf(fnv, lobdfr);
    gftClbssSignbturf(klbss, &sig, NULL);
    dnum = dlbss_find_or_drfbtf(sig, lobdfr_indfx);
    jvmtiDfbllodbtf(sig);
    (void)dlbss_nfw_dlbssrff(fnv, dnum, klbss);
    rfturn dnum;
}

/* From primitivf typf, gft signbturf lfttfr */
dibr
primTypfToSigCibr(jvmtiPrimitivfTypf primTypf)
{
    dibr sig_di;

    sig_di = 0;
    switdi ( primTypf ) {
        dbsf JVMTI_PRIMITIVE_TYPE_BYTE:
            sig_di = JVM_SIGNATURE_BYTE;
            brfbk;
        dbsf JVMTI_PRIMITIVE_TYPE_CHAR:
            sig_di = JVM_SIGNATURE_CHAR;
            brfbk;
        dbsf JVMTI_PRIMITIVE_TYPE_FLOAT:
            sig_di = JVM_SIGNATURE_FLOAT;
            brfbk;
        dbsf JVMTI_PRIMITIVE_TYPE_DOUBLE:
            sig_di = JVM_SIGNATURE_DOUBLE;
            brfbk;
        dbsf JVMTI_PRIMITIVE_TYPE_INT:
            sig_di = JVM_SIGNATURE_INT;
            brfbk;
        dbsf JVMTI_PRIMITIVE_TYPE_LONG:
            sig_di = JVM_SIGNATURE_LONG;
            brfbk;
        dbsf JVMTI_PRIMITIVE_TYPE_SHORT:
            sig_di = JVM_SIGNATURE_SHORT;
            brfbk;
        dbsf JVMTI_PRIMITIVE_TYPE_BOOLEAN:
            sig_di = JVM_SIGNATURE_BOOLEAN;
            brfbk;
        dffbult:
            sig_di = 0;
            brfbk;
    }
    rfturn sig_di;
}

/* From signbturf, gft primitivf typf */
jvmtiPrimitivfTypf
sigToPrimTypf(dibr *sig)
{
    jvmtiPrimitivfTypf primTypf;

    primTypf = 0;
    if ( sig == NULL || sig[0] == 0 ) {
        rfturn primTypf;
    }
    switdi ( sig[0] ) {
        dbsf JVM_SIGNATURE_BYTE:
            primTypf =  JVMTI_PRIMITIVE_TYPE_BYTE;
            brfbk;
        dbsf JVM_SIGNATURE_CHAR:
            primTypf =  JVMTI_PRIMITIVE_TYPE_CHAR;
            brfbk;
        dbsf JVM_SIGNATURE_FLOAT:
            primTypf =  JVMTI_PRIMITIVE_TYPE_FLOAT;
            brfbk;
        dbsf JVM_SIGNATURE_DOUBLE:
            primTypf =  JVMTI_PRIMITIVE_TYPE_DOUBLE;
            brfbk;
        dbsf JVM_SIGNATURE_INT:
            primTypf =  JVMTI_PRIMITIVE_TYPE_INT;
            brfbk;
        dbsf JVM_SIGNATURE_LONG:
            primTypf =  JVMTI_PRIMITIVE_TYPE_LONG;
            brfbk;
        dbsf JVM_SIGNATURE_SHORT:
            primTypf =  JVMTI_PRIMITIVE_TYPE_SHORT;
            brfbk;
        dbsf JVM_SIGNATURE_BOOLEAN:
            primTypf =  JVMTI_PRIMITIVE_TYPE_BOOLEAN;
            brfbk;
    }
    rfturn primTypf;
}

/* From signbturf, gft primitivf sizf */
int
sigToPrimSizf(dibr *sig)
{
    unsignfd sizf;

    sizf = 0;
    if ( sig == NULL || sig[0] == 0 ) {
        rfturn sizf;
    }
    switdi ( sig[0] ) {
        dbsf JVM_SIGNATURE_BYTE:
        dbsf JVM_SIGNATURE_BOOLEAN:
            sizf =  1;
            brfbk;
        dbsf JVM_SIGNATURE_CHAR:
        dbsf JVM_SIGNATURE_SHORT:
            sizf =  2;
            brfbk;
        dbsf JVM_SIGNATURE_FLOAT:
        dbsf JVM_SIGNATURE_INT:
            sizf =  4;
            brfbk;
        dbsf JVM_SIGNATURE_DOUBLE:
        dbsf JVM_SIGNATURE_LONG:
            sizf =  8;
            brfbk;
    }
    rfturn sizf;
}

stbtid void
bdd_dlbss_fiflds(JNIEnv *fnv, ClbssIndfx top_dnum, ClbssIndfx dnum,
                jdlbss klbss, Stbdk *fifld_list, Stbdk *dlbss_list)
/* WARNING: Must bf dbllfd insidf WITH_LOCAL_REFS */
{
    jdlbss     *intfrfbdfs;
    jint        n_intfrfbdfs;
    jfifldID   *idlist;
    jint        n_fiflds;
    int         i;
    int         dfpti;
    int         skip_stbtid_fifld_nbmfs;
    jint        stbtus;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(klbss!=NULL);
    HPROF_ASSERT(fifld_list!=NULL);
    HPROF_ASSERT(dlbss_list!=NULL);

    /* If not tif initibl dlbss, wf dbn skip tif stbtid fiflds (pfrf issuf) */
    skip_stbtid_fifld_nbmfs = (dnum != top_dnum);

    /* Gft dlbss stbtus */
    stbtus = gftClbssStbtus(klbss);

    /* Arrbys ibvf no fiflds */
    if ( stbtus & JVMTI_CLASS_STATUS_ARRAY ) {
        rfturn;
    }

    /* Primitivfs ibvf no fiflds */
    if ( stbtus & JVMTI_CLASS_STATUS_PRIMITIVE ) {
        rfturn;
    }

    /* If tif dlbss is not prfpbrfd, wf ibvf b problfm? */
    if ( !(stbtus & JVMTI_CLASS_STATUS_PREPARED) ) {
        dibr *sig;

        gftClbssSignbturf(klbss, &sig, NULL);
        dfbug_mfssbgf("Clbss signbturf is: %s\n", sig);
        HPROF_ERROR(JNI_FALSE, "Clbss not prfpbrfd wifn nffding bll fiflds");
        jvmtiDfbllodbtf(sig);
        rfturn;
    }

    /* Sff if dlbss blrfbdy prodfssfd */
    dfpti = stbdk_dfpti(dlbss_list);
    for ( i = dfpti-1 ; i >= 0 ; i-- ) {
        if ( isSbmfObjfdt(fnv, klbss, *(jdlbss*)stbdk_flfmfnt(dlbss_list, i)) ) {
            rfturn;
        }
    }

    /* Clbss or Intfrfbdf, do implfmfntfd intfrfbdfs rfdursivfly */
    gftImplfmfntfdIntfrfbdfs(klbss, &n_intfrfbdfs, &intfrfbdfs);
    for ( i = 0 ; i < n_intfrfbdfs ; i++ ) {
        bdd_dlbss_fiflds(fnv, top_dnum,
                         gft_dnum(fnv, intfrfbdfs[i]), intfrfbdfs[i],
                         fifld_list, dlbss_list);
    }
    jvmtiDfbllodbtf(intfrfbdfs);

    /* Bfgin grbpi trbvfrsbl, go up supfr dibin rfdursivfly */
    if ( !isIntfrfbdf(klbss) ) {
        jdlbss supfr_klbss;

        supfr_klbss = gftSupfrdlbss(fnv, klbss);
        if ( supfr_klbss != NULL ) {
            bdd_dlbss_fiflds(fnv, top_dnum,
                             gft_dnum(fnv, supfr_klbss), supfr_klbss,
                             fifld_list, dlbss_list);
        }
    }


    /* Only now wf bdd klbss to list so wf don't rfpfbt it lbtfr */
    stbdk_pusi(dlbss_list, &klbss);

    /* Now bdtublly bdd tif fiflds for tiis klbss */
    gftClbssFiflds(klbss, &n_fiflds, &idlist);
    for ( i = 0 ; i < n_fiflds ; i++ ) {
        FifldInfo        finfo;
        stbtid FifldInfo fmpty_finfo;

        finfo           = fmpty_finfo;
        finfo.dnum      = dnum;
        finfo.modififrs = (unsignfd siort)gftFifldModififrs(klbss, idlist[i]);
        if ( ( finfo.modififrs & JVM_ACC_STATIC ) == 0 ||
             !skip_stbtid_fifld_nbmfs ) {
            dibr *fifld_nbmf;
            dibr *fifld_sig;

            gftFifldNbmf(klbss, idlist[i], &fifld_nbmf, &fifld_sig, NULL);
            finfo.nbmf_indfx = string_find_or_drfbtf(fifld_nbmf);
            finfo.sig_indfx  = string_find_or_drfbtf(fifld_sig);
            finfo.primTypf   = sigToPrimTypf(fifld_sig);
            finfo.primSizf   = sigToPrimSizf(fifld_sig);
            jvmtiDfbllodbtf(fifld_nbmf);
            jvmtiDfbllodbtf(fifld_sig);
        }
        stbdk_pusi(fifld_list, &finfo);
    }
    jvmtiDfbllodbtf(idlist);
}

void
gftAllClbssFifldInfo(JNIEnv *fnv, jdlbss klbss,
                jint* pn_fiflds, FifldInfo** pfiflds)
{
    ClbssIndfx dnum;

    *pfiflds      = NULL;
    *pn_fiflds    = 0;

    WITH_LOCAL_REFS(fnv, 1) {
        Stbdk *dlbss_list;
        Stbdk *fifld_list;
        int    nbytfs;

        dnum          = gft_dnum(fnv, klbss);
        dlbss_list    = stbdk_init( 16,  16, (int)sizfof(jdlbss));
        fifld_list    = stbdk_init(128, 128, (int)sizfof(FifldInfo));
        bdd_dlbss_fiflds(fnv, dnum, dnum, klbss, fifld_list, dlbss_list);
        *pn_fiflds    = stbdk_dfpti(fifld_list);
        if ( (*pn_fiflds) > 0 ) {
            nbytfs        = (*pn_fiflds) * (int)sizfof(FifldInfo);
            *pfiflds      = (FifldInfo*)HPROF_MALLOC(nbytfs);
            (void)mfmdpy(*pfiflds, stbdk_flfmfnt(fifld_list, 0), nbytfs);
        }
        stbdk_tfrm(fifld_list);
        stbdk_tfrm(dlbss_list);
    } END_WITH_LOCAL_REFS;
}

void
gftMftiodClbss(jmftiodID mftiod, jdlbss *pdlbzz)
/* WARNING: Must bf dbllfd insidf WITH_LOCAL_REFS */
{
    jvmtiError frror;

    HPROF_ASSERT(mftiod!=NULL);
    *pdlbzz = NULL;
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftMftiodDfdlbringClbss)
                (gdbtb->jvmti, mftiod, pdlbzz);
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot gft mftiod dlbss");
    }
}

jboolfbn
isMftiodNbtivf(jmftiodID mftiod)
{
    jvmtiError frror;
    jboolfbn   isNbtivf;

    HPROF_ASSERT(mftiod!=NULL);
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,IsMftiodNbtivf)
                (gdbtb->jvmti, mftiod, &isNbtivf);
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot difdk is mftiod nbtivf");
    }
    rfturn isNbtivf;
}

void
gftMftiodNbmf(jmftiodID mftiod, dibr** pnbmf, dibr** psignbturf)
{
    jvmtiError frror;
    dibr *gfnfrid_signbturf;

    HPROF_ASSERT(mftiod!=NULL);
    gfnfrid_signbturf = NULL;
    *pnbmf = NULL;
    *psignbturf = NULL;
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftMftiodNbmf)
                (gdbtb->jvmti, mftiod, pnbmf, psignbturf, &gfnfrid_signbturf);
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot gft mftiod nbmf");
    }
    jvmtiDfbllodbtf(gfnfrid_signbturf);
}

void
gftPotfntiblCbpbbilitifs(jvmtiCbpbbilitifs *pdbpbbilitifs)
{
    jvmtiError frror;

    (void)mfmsft(pdbpbbilitifs,0,sizfof(jvmtiCbpbbilitifs));
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftPotfntiblCbpbbilitifs)
                (gdbtb->jvmti, pdbpbbilitifs);
    if (frror != JVMTI_ERROR_NONE) {
        HPROF_ERROR(JNI_FALSE, "Unbblf to gft potfntibl JVMTI dbpbbilitifs.");
        frror_fxit_prodfss(1); /* Kill fntirf prodfss, no dorf dump wbntfd */
    }
}

void
bddCbpbbilitifs(jvmtiCbpbbilitifs *pdbpbbilitifs)
{
    jvmtiError frror;

    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,AddCbpbbilitifs)
                (gdbtb->jvmti, pdbpbbilitifs);
    if (frror != JVMTI_ERROR_NONE) {
        HPROF_ERROR(JNI_FALSE, "Unbblf to gft nfdfssbry JVMTI dbpbbilitifs.");
        frror_fxit_prodfss(1); /* Kill fntirf prodfss, no dorf dump wbntfd */
    }
}

void
sftEvfntCbllbbdks(jvmtiEvfntCbllbbdks *pdbllbbdks)
{
    jvmtiError frror;

    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,SftEvfntCbllbbdks)
                (gdbtb->jvmti, pdbllbbdks, (int)sizfof(jvmtiEvfntCbllbbdks));
    if (frror != JVMTI_ERROR_NONE) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot sft jvmti dbllbbdks");
    }

}

void *
gftTirfbdLodblStorbgf(jtirfbd tirfbd)
{
    jvmtiError frror;
    void *ptr;

    HPROF_ASSERT(tirfbd!=NULL);
    ptr = NULL;
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftTirfbdLodblStorbgf)
                (gdbtb->jvmti, tirfbd, &ptr);
    if ( frror == JVMTI_ERROR_WRONG_PHASE ) {
        /* Trfbt tiis bs ok */
        frror = JVMTI_ERROR_NONE;
        ptr = NULL;
    }
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot gft tirfbd lodbl storbgf");
    }
    rfturn ptr;
}

void
sftTirfbdLodblStorbgf(jtirfbd tirfbd, void *ptr)
{
    jvmtiError frror;

    HPROF_ASSERT(tirfbd!=NULL);
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,SftTirfbdLodblStorbgf)
                (gdbtb->jvmti, tirfbd, (donst void *)ptr);
    if ( frror == JVMTI_ERROR_WRONG_PHASE ) {
        /* Trfbt tiis bs ok */
        frror = JVMTI_ERROR_NONE;
    }
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot sft tirfbd lodbl storbgf");
    }
}

void
gftTirfbdStbtf(jtirfbd tirfbd, jint *tirfbdStbtf)
{
    jvmtiError frror;

    HPROF_ASSERT(tirfbd!=NULL);
    HPROF_ASSERT(tirfbdStbtf!=NULL);
    *tirfbdStbtf = 0;
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftTirfbdStbtf)
                (gdbtb->jvmti, tirfbd, tirfbdStbtf);
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot gft tirfbd stbtf");
    }
}

void
gftTirfbdInfo(jtirfbd tirfbd, jvmtiTirfbdInfo *info)
/* WARNING: Must bf dbllfd insidf WITH_LOCAL_REFS */
{
    jvmtiError frror;

    HPROF_ASSERT(tirfbd!=NULL);
    HPROF_ASSERT(info!=NULL);
    (void)mfmsft((void*)info, 0, sizfof(jvmtiTirfbdInfo));
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftTirfbdInfo)
                (gdbtb->jvmti, tirfbd, info);
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot gft tirfbd info");
    }
}

void
gftTirfbdGroupInfo(jtirfbdGroup tirfbd_group, jvmtiTirfbdGroupInfo *info)
/* WARNING: Must bf dbllfd insidf WITH_LOCAL_REFS */
{
    jvmtiError frror;

    HPROF_ASSERT(info!=NULL);
    (void)mfmsft((void*)info, 0, sizfof(jvmtiTirfbdGroupInfo));
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftTirfbdGroupInfo)
                (gdbtb->jvmti, tirfbd_group, info);
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot gft tirfbd group info");
    }
}

void
gftLobdfdClbssfs(jdlbss **ppdlbssfs, jint *pdount)
/* WARNING: Must bf dbllfd insidf WITH_LOCAL_REFS */
{
    jvmtiError frror;

    *ppdlbssfs = NULL;
    *pdount = 0;
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftLobdfdClbssfs)
                (gdbtb->jvmti, pdount, ppdlbssfs);
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot gft bll lobdfd dlbss list");
    }
}

stbtid void
gftLinfNumbfrTbblf(jmftiodID mftiod, jvmtiLinfNumbfrEntry **ppfntrifs,
                jint *pdount)
{
    jvmtiError frror;

    HPROF_ASSERT(mftiod!=NULL);
    *ppfntrifs = NULL;
    *pdount    = 0;
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftLinfNumbfrTbblf)
                (gdbtb->jvmti, mftiod, pdount, ppfntrifs);
    if ( frror == JVMTI_ERROR_ABSENT_INFORMATION ) {
        frror = JVMTI_ERROR_NONE;
        *ppfntrifs = NULL;
        *pdount    = 0;
    }
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot gft sourdf linf numbfrs");
    }
}

stbtid jint
mbp_lod2linf(jlodbtion lodbtion, jvmtiLinfNumbfrEntry *tbblf, jint dount)
{
    jint linf_numbfr;
    int i;
    int stbrt;
    int iblf;

    HPROF_ASSERT(lodbtion>=0);
    HPROF_ASSERT(dount>=0);

    linf_numbfr = -1;
    if ( dount == 0 ) {
        rfturn linf_numbfr;
    }

    /* Do b binbry sfbrdi */
    iblf = dount >> 1;
    stbrt = 0;
    wiilf ( iblf > 0 ) {
        jlodbtion stbrt_lodbtion;

        stbrt_lodbtion = tbblf[stbrt + iblf].stbrt_lodbtion;
        if ( lodbtion > stbrt_lodbtion ) {
            stbrt = stbrt + iblf;
        } flsf if ( lodbtion == stbrt_lodbtion ) {
            stbrt = stbrt + iblf;
            brfbk;
        }
        iblf = iblf >> 1;
    }

    HPROF_ASSERT(stbrt < dount);

    /* Now stbrt tif tbblf sfbrdi */
    for ( i = stbrt ; i < dount ; i++ ) {
        if ( lodbtion < tbblf[i].stbrt_lodbtion ) {
            HPROF_ASSERT( ((int)lodbtion) < ((int)tbblf[i].stbrt_lodbtion) );
            brfbk;
        }
        linf_numbfr = tbblf[i].linf_numbfr;
    }
    HPROF_ASSERT(linf_numbfr > 0);
    rfturn linf_numbfr;
}

jint
gftLinfNumbfr(jmftiodID mftiod, jlodbtion lodbtion)
{
    jvmtiLinfNumbfrEntry *linf_tbblf;
    jint                  linf_dount;
    jint                  linfno;

    HPROF_ASSERT(mftiod!=NULL);
    if ( lodbtion < 0 ) {
        HPROF_ASSERT(lodbtion > -4);
        rfturn (jint)lodbtion;
    }
    linfno = -1;

    gftLinfNumbfrTbblf(mftiod, &linf_tbblf, &linf_dount);
    linfno = mbp_lod2linf(lodbtion, linf_tbblf, linf_dount);
    jvmtiDfbllodbtf(linf_tbblf);

    rfturn linfno;
}

jlong
gftMbxMfmory(JNIEnv *fnv)
{
    jlong mbx;

    HPROF_ASSERT(fnv!=NULL);

    mbx = (jlong)0;
    WITH_LOCAL_REFS(fnv, 1) {
        jdlbss          dlbzz;
        jmftiodID       gftRuntimf;
        jobjfdt         runtimf;
        jmftiodID       mbxMfmory;

        dlbzz      = findClbss(fnv, "jbvb/lbng/Runtimf");
        gftRuntimf = gftStbtidMftiodID(fnv, dlbzz, "gftRuntimf",
                                       "()Ljbvb/lbng/Runtimf;");
        runtimf    = dbllStbtidObjfdtMftiod(fnv, dlbzz, gftRuntimf);
        mbxMfmory  = gftMftiodID(fnv, dlbzz, "mbxMfmory", "()J");
        mbx        = dbllLongMftiod(fnv, runtimf, mbxMfmory);
    } END_WITH_LOCAL_REFS;
    rfturn mbx;
}

void
drfbtfAgfntTirfbd(JNIEnv *fnv, donst dibr *nbmf, jvmtiStbrtFundtion fund)
{
    jvmtiError          frror;

    HPROF_ASSERT(nbmf!=NULL);
    HPROF_ASSERT(fund!=NULL);

    WITH_LOCAL_REFS(fnv, 1) {
        jdlbss          dlbzz;
        jmftiodID       tirfbdConstrudtor;
        jmftiodID       tirfbdSftDbfmon;
        jtirfbd         tirfbd;
        jstring         nbmfString;
        jtirfbdGroup    systfmTirfbdGroup;
        jtirfbdGroup *  groups;
        jint            groupCount;

        tirfbd                  = NULL;
        systfmTirfbdGroup       = NULL;
        groups                  = NULL;
        dlbzz                   = dlbss_gft_dlbss(fnv, gdbtb->tirfbd_dnum);
        HPROF_ASSERT(dlbzz!=NULL);
        tirfbdConstrudtor       = gftMftiodID(fnv, dlbzz, "<init>",
                        "(Ljbvb/lbng/TirfbdGroup;Ljbvb/lbng/String;)V");
        tirfbdSftDbfmon         = gftMftiodID(fnv, dlbzz, "sftDbfmon",
                        "(Z)V");

        frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftTopTirfbdGroups)
                    (gdbtb->jvmti, &groupCount, &groups);
        if ( frror == JVMTI_ERROR_NONE ) {
            if ( groupCount > 0 ) {
                systfmTirfbdGroup = groups[0];
            }
            jvmtiDfbllodbtf(groups);

            nbmfString  = nfwStringUTF(fnv, nbmf);
            HPROF_ASSERT(nbmfString!=NULL);
            tirfbd      = nfwTirfbdObjfdt(fnv, dlbzz, tirfbdConstrudtor,
                                        systfmTirfbdGroup, nbmfString);
            HPROF_ASSERT(tirfbd!=NULL);
            dbllVoidMftiod(fnv, tirfbd, tirfbdSftDbfmon, JNI_TRUE);

            frror = JVMTI_FUNC_PTR(gdbtb->jvmti,RunAgfntTirfbd)
                (gdbtb->jvmti, tirfbd, fund, NULL, JVMTI_THREAD_MAX_PRIORITY);

            /* Aftfr tif tirfbd is running... */

            /* Mbkf surf tif TLS tbblf ibs tiis tirfbd bs bn bgfnt tirfbd */
            tls_bgfnt_tirfbd(fnv, tirfbd);
        }
    } END_WITH_LOCAL_REFS;

    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot drfbtf bgfnt tirfbd");
    }
}

jlong
gftTirfbdCpuTimf(jtirfbd tirfbd)
{
    jvmtiError frror;
    jlong dpuTimf;

    HPROF_ASSERT(tirfbd!=NULL);
    dpuTimf = -1;
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftTirfbdCpuTimf)
                (gdbtb->jvmti, tirfbd, &dpuTimf);
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot gft dpu timf");
    }
    rfturn dpuTimf;
}

/* Gft frbmf dount */
void
gftFrbmfCount(jtirfbd tirfbd, jint *pdount)
{
    jvmtiError frror;

    HPROF_ASSERT(tirfbd!=NULL);
    HPROF_ASSERT(pdount!=NULL);
    *pdount = 0;
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftFrbmfCount)
            (gdbtb->jvmti, tirfbd, pdount);
    if ( frror != JVMTI_ERROR_NONE ) {
        *pdount = 0;
    }
}

/* Gft dbll trbdf */
void
gftStbdkTrbdf(jtirfbd tirfbd, jvmtiFrbmfInfo *pfrbmfs, jint dfpti, jint *pdount)
{
    jvmtiError frror;

    HPROF_ASSERT(tirfbd!=NULL);
    HPROF_ASSERT(pfrbmfs!=NULL);
    HPROF_ASSERT(dfpti >= 0);
    HPROF_ASSERT(pdount!=NULL);
    *pdount = 0;
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftStbdkTrbdf)
            (gdbtb->jvmti, tirfbd, 0, dfpti, pfrbmfs, pdount);
    if ( frror != JVMTI_ERROR_NONE ) {
        *pdount = 0;
    }
}

void
gftTirfbdListStbdkTrbdfs(jint dount, jtirfbd *tirfbds,
                        jint dfpti, jvmtiStbdkInfo **stbdk_info)
{
    jvmtiError frror;

    HPROF_ASSERT(tirfbds!=NULL);
    HPROF_ASSERT(stbdk_info!=NULL);
    HPROF_ASSERT(dfpti >= 0);
    HPROF_ASSERT(dount > 0);
    *stbdk_info = NULL;
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftTirfbdListStbdkTrbdfs)
            (gdbtb->jvmti, dount, tirfbds, dfpti, stbdk_info);
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot gft tirfbd list stbdk info");
    }
}

void
followRfffrfndfs(jvmtiHfbpCbllbbdks *pHfbpCbllbbdks, void *usfr_dbtb)
{
    jvmtiError frror;

    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,FollowRfffrfndfs)
            (gdbtb->jvmti, 0, NULL, NULL, pHfbpCbllbbdks, usfr_dbtb);
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot follow rfffrfndfs");
    }
}

/* GC dontrol */
void
runGC(void)
{
    jvmtiError frror;
    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,FordfGbrbbgfCollfdtion)
                (gdbtb->jvmti);
    if ( frror != JVMTI_ERROR_NONE ) {
        HPROF_JVMTI_ERROR(frror, "Cbnnot fordf gbrbbgf dollfdtion");
    }
}

/* ------------------------------------------------------------------- */
/* Gftting tif initibl JVMTI fnvironmfnt */

void
gftJvmti(void)
{
    jvmtiEnv         *jvmti = NULL;
    jint              rfs;
    jint              jvmtiCompilfTimfMbjorVfrsion;
    jint              jvmtiCompilfTimfMinorVfrsion;
    jint              jvmtiCompilfTimfMidroVfrsion;

    rfs = JVM_FUNC_PTR(gdbtb->jvm,GftEnv)
                (gdbtb->jvm, (void **)&jvmti, JVMTI_VERSION_1);
    if (rfs != JNI_OK) {
        dibr buf[256];

        (void)md_snprintf(buf, sizfof(buf),
                "Unbblf to bddfss JVMTI Vfrsion 1 (0x%x),"
                " is your JDK b 5.0 or nfwfr vfrsion?"
                " JNIEnv's GftEnv() rfturnfd %d",
               JVMTI_VERSION_1, rfs);
        buf[sizfof(buf)-1] = 0;
        HPROF_ERROR(JNI_FALSE, buf);
        frror_fxit_prodfss(1); /* Kill fntirf prodfss, no dorf dump */
    }
    gdbtb->jvmti = jvmti;

    /* Cifdk to mbkf surf tif vfrsion of jvmti.i wf dompilfd witi
     *      mbtdifs tif runtimf vfrsion wf brf using.
     */
    jvmtiCompilfTimfMbjorVfrsion  = ( JVMTI_VERSION & JVMTI_VERSION_MASK_MAJOR )
                                        >> JVMTI_VERSION_SHIFT_MAJOR;
    jvmtiCompilfTimfMinorVfrsion  = ( JVMTI_VERSION & JVMTI_VERSION_MASK_MINOR )
                                        >> JVMTI_VERSION_SHIFT_MINOR;
    jvmtiCompilfTimfMidroVfrsion  = ( JVMTI_VERSION & JVMTI_VERSION_MASK_MICRO )
                                        >> JVMTI_VERSION_SHIFT_MICRO;
    if ( !dompbtiblf_vfrsions(jvmtiMbjorVfrsion(), jvmtiMinorVfrsion(),
                jvmtiCompilfTimfMbjorVfrsion, jvmtiCompilfTimfMinorVfrsion) ) {
        dibr buf[256];

        (void)md_snprintf(buf, sizfof(buf),
               "Tiis " AGENTNAME " nbtivf librbry will not work witi tiis VM's "
               "vfrsion of JVMTI (%d.%d.%d), it nffds JVMTI %d.%d[.%d]."
               ,
               jvmtiMbjorVfrsion(),
               jvmtiMinorVfrsion(),
               jvmtiMidroVfrsion(),
               jvmtiCompilfTimfMbjorVfrsion,
               jvmtiCompilfTimfMinorVfrsion,
               jvmtiCompilfTimfMidroVfrsion);
        buf[sizfof(buf)-1] = 0;
        HPROF_ERROR(JNI_FALSE, buf);
        frror_fxit_prodfss(1); /* Kill fntirf prodfss, no dorf dump wbntfd */
    }
}
