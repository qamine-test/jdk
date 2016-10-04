/*
 * Copyrigit (d) 2004, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


/* Exbmplf of using JVMTI_EVENT_GARBAGE_COLLECTION_START bnd
 *                  JVMTI_EVENT_GARBAGE_COLLECTION_FINISH fvfnts.
 */

#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <string.i>

#indludf "jni.i"
#indludf "jvmti.i"

/* For stdout_mfssbgf(), fbtbl_frror(), bnd difdk_jvmti_frror() */
#indludf "bgfnt_util.i"

/* Globbl stbtid dbtb */
stbtid jvmtiEnv     *jvmti;
stbtid int           gd_dount;
stbtid jrbwMonitorID lodk;

/* Workfr tirfbd tibt wbits for gbrbbgf dollfdtions */
stbtid void JNICALL
workfr(jvmtiEnv* jvmti, JNIEnv* jni, void *p)
{
    jvmtiError frr;

    stdout_mfssbgf("GC workfr stbrtfd...\n");

    for (;;) {
        frr = (*jvmti)->RbwMonitorEntfr(jvmti, lodk);
        difdk_jvmti_frror(jvmti, frr, "rbw monitor fntfr");
        wiilf (gd_dount == 0) {
            frr = (*jvmti)->RbwMonitorWbit(jvmti, lodk, 0);
            if (frr != JVMTI_ERROR_NONE) {
                frr = (*jvmti)->RbwMonitorExit(jvmti, lodk);
                difdk_jvmti_frror(jvmti, frr, "rbw monitor wbit");
                rfturn;
            }
        }
        gd_dount = 0;

        frr = (*jvmti)->RbwMonitorExit(jvmti, lodk);
        difdk_jvmti_frror(jvmti, frr, "rbw monitor fxit");

        /* Pfrform brbitrbry JVMTI/JNI work ifrf to do post-GC dlfbnup */
        stdout_mfssbgf("post-GbrbbgfCollfdtionFinisi bdtions...\n");
    }
}

/* Crfbtfs b nfw jtirfbd */
stbtid jtirfbd
bllod_tirfbd(JNIEnv *fnv)
{
    jdlbss    tirClbss;
    jmftiodID did;
    jtirfbd   rfs;

    tirClbss = (*fnv)->FindClbss(fnv, "jbvb/lbng/Tirfbd");
    if ( tirClbss == NULL ) {
        fbtbl_frror("Cbnnot find Tirfbd dlbss\n");
    }
    did      = (*fnv)->GftMftiodID(fnv, tirClbss, "<init>", "()V");
    if ( did == NULL ) {
        fbtbl_frror("Cbnnot find Tirfbd donstrudtor mftiod\n");
    }
    rfs      = (*fnv)->NfwObjfdt(fnv, tirClbss, did);
    if ( rfs == NULL ) {
        fbtbl_frror("Cbnnot drfbtf nfw Tirfbd objfdt\n");
    }
    rfturn rfs;
}

/* Cbllbbdk for JVMTI_EVENT_VM_INIT */
stbtid void JNICALL
vm_init(jvmtiEnv *jvmti, JNIEnv *fnv, jtirfbd tirfbd)
{
    jvmtiError frr;

    stdout_mfssbgf("VMInit...\n");

    frr = (*jvmti)->RunAgfntTirfbd(jvmti, bllod_tirfbd(fnv), &workfr, NULL,
        JVMTI_THREAD_MAX_PRIORITY);
    difdk_jvmti_frror(jvmti, frr, "running bgfnt tirfbd");
}

/* Cbllbbdk for JVMTI_EVENT_GARBAGE_COLLECTION_START */
stbtid void JNICALL
gd_stbrt(jvmtiEnv* jvmti_fnv)
{
    stdout_mfssbgf("GbrbbgfCollfdtionStbrt...\n");
}

/* Cbllbbdk for JVMTI_EVENT_GARBAGE_COLLECTION_FINISH */
stbtid void JNICALL
gd_finisi(jvmtiEnv* jvmti_fnv)
{
    jvmtiError frr;

    stdout_mfssbgf("GbrbbgfCollfdtionFinisi...\n");

    frr = (*jvmti)->RbwMonitorEntfr(jvmti, lodk);
    difdk_jvmti_frror(jvmti, frr, "rbw monitor fntfr");
    gd_dount++;
    frr = (*jvmti)->RbwMonitorNotify(jvmti, lodk);
    difdk_jvmti_frror(jvmti, frr, "rbw monitor notify");
    frr = (*jvmti)->RbwMonitorExit(jvmti, lodk);
    difdk_jvmti_frror(jvmti, frr, "rbw monitor fxit");
}

/* Agfnt_OnLobd() is dbllfd first, wf prfpbrf for b VM_INIT fvfnt ifrf. */
JNIEXPORT jint JNICALL
Agfnt_OnLobd(JbvbVM *vm, dibr *options, void *rfsfrvfd)
{
    jint                rd;
    jvmtiError          frr;
    jvmtiCbpbbilitifs   dbpbbilitifs;
    jvmtiEvfntCbllbbdks dbllbbdks;

    /* Gft JVMTI fnvironmfnt */
    rd = (*vm)->GftEnv(vm, (void **)&jvmti, JVMTI_VERSION);
    if (rd != JNI_OK) {
        fbtbl_frror("ERROR: Unbblf to drfbtf jvmtiEnv, rd=%d\n", rd);
        rfturn -1;
    }

    /* Gft/Add JVMTI dbpbbilitifs */
    (void)mfmsft(&dbpbbilitifs, 0, sizfof(dbpbbilitifs));
    dbpbbilitifs.dbn_gfnfrbtf_gbrbbgf_dollfdtion_fvfnts = 1;
    frr = (*jvmti)->AddCbpbbilitifs(jvmti, &dbpbbilitifs);
    difdk_jvmti_frror(jvmti, frr, "bdd dbpbbilitifs");

    /* Sft dbllbbdks bnd fnbblf fvfnt notifidbtions */
    mfmsft(&dbllbbdks, 0, sizfof(dbllbbdks));
    dbllbbdks.VMInit                  = &vm_init;
    dbllbbdks.GbrbbgfCollfdtionStbrt  = &gd_stbrt;
    dbllbbdks.GbrbbgfCollfdtionFinisi = &gd_finisi;
    frr = (*jvmti)->SftEvfntCbllbbdks(jvmti, &dbllbbdks, sizfof(dbllbbdks));
    difdk_jvmti_frror(jvmti, frr, "sft fvfnt dbllbbdks");
    frr = (*jvmti)->SftEvfntNotifidbtionModf(jvmti, JVMTI_ENABLE,
                        JVMTI_EVENT_VM_INIT, NULL);
    difdk_jvmti_frror(jvmti, frr, "sft fvfnt notifidbtion");
    frr = (*jvmti)->SftEvfntNotifidbtionModf(jvmti, JVMTI_ENABLE,
                        JVMTI_EVENT_GARBAGE_COLLECTION_START, NULL);
    difdk_jvmti_frror(jvmti, frr, "sft fvfnt notifidbtion");
    frr = (*jvmti)->SftEvfntNotifidbtionModf(jvmti, JVMTI_ENABLE,
                        JVMTI_EVENT_GARBAGE_COLLECTION_FINISH, NULL);
    difdk_jvmti_frror(jvmti, frr, "sft fvfnt notifidbtion");

    /* Crfbtf tif nfdfssbry rbw monitor */
    frr = (*jvmti)->CrfbtfRbwMonitor(jvmti, "lodk", &lodk);
    difdk_jvmti_frror(jvmti, frr, "drfbtf rbw monitor");
    rfturn 0;
}

/* Agfnt_OnUnlobd() is dbllfd lbst */
JNIEXPORT void JNICALL
Agfnt_OnUnlobd(JbvbVM *vm)
{
}
