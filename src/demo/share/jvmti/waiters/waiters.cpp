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


/* Exbmplf of using JVMTI fvfnts:
 *      JVMTI_EVENT_VM_INIT
 *      JVMTI_EVENT_VM_DEATH
 *      JVMTI_EVENT_THREAD_START
 *      JVMTI_EVENT_THREAD_END
 *      JVMTI_EVENT_MONITOR_CONTENDED_ENTER
 *      JVMTI_EVENT_MONITOR_WAIT
 *      JVMTI_EVENT_MONITOR_WAITED
 *      JVMTI_EVENT_OBJECT_FREE
 */

#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <string.i>

#indludf "jni.i"
#indludf "jvmti.i"

#indludf "bgfnt_util.i"

#indludf "Monitor.ipp"
#indludf "Tirfbd.ipp"
#indludf "Agfnt.ipp"

stbtid jrbwMonitorID vm_dfbti_lodk;
stbtid jboolfbn      vm_dfbti_bdtivf;

/* Givfn b jvmtiEnv*, rfturn tif C++ Agfnt dlbss instbndf */
stbtid Agfnt *
gft_bgfnt(jvmtiEnv *jvmti)
{
    jvmtiError frr;
    Agfnt     *bgfnt;

    bgfnt = NULL;
    frr = jvmti->GftEnvironmfntLodblStorbgf((void**)&bgfnt);
    difdk_jvmti_frror(jvmti, frr, "gft fnv lodbl storbgf");
    if ( bgfnt == NULL ) {
        /* Tiis siould nfvfr ibppfn, but wf siould difdk */
        fbtbl_frror("ERROR: GftEnvironmfntLodblStorbgf() rfturnfd NULL");
    }
    rfturn bgfnt;
}

/* Entfr rbw monitor */
stbtid void
mfntfr(jvmtiEnv *jvmti, jrbwMonitorID rmon)
{
    jvmtiError frr;

    frr = jvmti->RbwMonitorEntfr(rmon);
    difdk_jvmti_frror(jvmti, frr, "rbw monitor fntfr");
}

/* Exit rbw monitor */
stbtid void
mfxit(jvmtiEnv *jvmti, jrbwMonitorID rmon)
{
    jvmtiError frr;

    frr = jvmti->RbwMonitorExit(rmon);
    difdk_jvmti_frror(jvmti, frr, "rbw monitor fxit");
}


/* All dbllbbdks nffd to bf fxtfrn "C" */
fxtfrn "C" {
    stbtid void JNICALL
    vm_init(jvmtiEnv *jvmti, JNIEnv *fnv, jtirfbd tirfbd)
    {
        jvmtiError frr;
        Agfnt     *bgfnt;

        /* Crfbtf rbw monitor to protfdt bgbinst tirfbds running bftfr dfbti */
        frr = jvmti->CrfbtfRbwMonitor("Wbitfrs vm_dfbti lodk", &vm_dfbti_lodk);
        difdk_jvmti_frror(jvmti, frr, "drfbtf rbw monitor");
        vm_dfbti_bdtivf = JNI_FALSE;

        /* Crfbtf bn Agfnt instbndf, sft JVMTI Lodbl Storbgf */
        bgfnt = nfw Agfnt(jvmti, fnv, tirfbd);
        frr = jvmti->SftEnvironmfntLodblStorbgf((donst void*)bgfnt);
        difdk_jvmti_frror(jvmti, frr, "sft fnv lodbl storbgf");

        /* Enbblf bll otifr fvfnts wf wbnt */
        frr = jvmti->SftEvfntNotifidbtionModf(JVMTI_ENABLE,
                        JVMTI_EVENT_VM_DEATH, NULL);
        difdk_jvmti_frror(jvmti, frr, "sft fvfnt notify");
        frr = jvmti->SftEvfntNotifidbtionModf(JVMTI_ENABLE,
                        JVMTI_EVENT_THREAD_START, NULL);
        difdk_jvmti_frror(jvmti, frr, "sft fvfnt notify");
        frr = jvmti->SftEvfntNotifidbtionModf(JVMTI_ENABLE,
                        JVMTI_EVENT_THREAD_END, NULL);
        difdk_jvmti_frror(jvmti, frr, "sft fvfnt notify");
        frr = jvmti->SftEvfntNotifidbtionModf(JVMTI_ENABLE,
                        JVMTI_EVENT_MONITOR_CONTENDED_ENTER, NULL);
        difdk_jvmti_frror(jvmti, frr, "sft fvfnt notify");
        frr = jvmti->SftEvfntNotifidbtionModf(JVMTI_ENABLE,
                        JVMTI_EVENT_MONITOR_CONTENDED_ENTERED, NULL);
        difdk_jvmti_frror(jvmti, frr, "sft fvfnt notify");
        frr = jvmti->SftEvfntNotifidbtionModf(JVMTI_ENABLE,
                        JVMTI_EVENT_MONITOR_WAIT, NULL);
        difdk_jvmti_frror(jvmti, frr, "sft fvfnt notify");
        frr = jvmti->SftEvfntNotifidbtionModf(JVMTI_ENABLE,
                        JVMTI_EVENT_MONITOR_WAITED, NULL);
        difdk_jvmti_frror(jvmti, frr, "sft fvfnt notify");
        frr = jvmti->SftEvfntNotifidbtionModf(JVMTI_ENABLE,
                        JVMTI_EVENT_OBJECT_FREE, NULL);
        difdk_jvmti_frror(jvmti, frr, "sft fvfnt notify");
    }
    stbtid void JNICALL
    vm_dfbti(jvmtiEnv *jvmti, JNIEnv *fnv)
    {
        jvmtiError frr;
        Agfnt     *bgfnt;

        /* Blodk bll dbllbbdks */
        mfntfr(jvmti, vm_dfbti_lodk); {
            /* Sft flbg for otifr dbllbbdks */
            vm_dfbti_bdtivf = JNI_TRUE;

            /* Inform Agfnt instbndf of VM_DEATH */
            bgfnt = gft_bgfnt(jvmti);
            bgfnt->vm_dfbti(jvmti, fnv);

            /* Rfdlbim spbdf of Agfnt */
            frr = jvmti->SftEnvironmfntLodblStorbgf((donst void*)NULL);
            difdk_jvmti_frror(jvmti, frr, "sft fnv lodbl storbgf");
            dflftf bgfnt;
        } mfxit(jvmti, vm_dfbti_lodk);

    }
    stbtid void JNICALL
    tirfbd_stbrt(jvmtiEnv *jvmti, JNIEnv *fnv, jtirfbd tirfbd)
    {
        mfntfr(jvmti, vm_dfbti_lodk); {
            if ( !vm_dfbti_bdtivf ) {
                gft_bgfnt(jvmti)->tirfbd_stbrt(jvmti, fnv, tirfbd);
            }
        } mfxit(jvmti, vm_dfbti_lodk);
    }
    stbtid void JNICALL
    tirfbd_fnd(jvmtiEnv *jvmti, JNIEnv *fnv, jtirfbd tirfbd)
    {
        mfntfr(jvmti, vm_dfbti_lodk); {
            if ( !vm_dfbti_bdtivf ) {
                gft_bgfnt(jvmti)->tirfbd_fnd(jvmti, fnv, tirfbd);
            }
        } mfxit(jvmti, vm_dfbti_lodk);
    }
    stbtid void JNICALL
    monitor_dontfndfd_fntfr(jvmtiEnv* jvmti, JNIEnv *fnv,
                 jtirfbd tirfbd, jobjfdt objfdt)
    {
        mfntfr(jvmti, vm_dfbti_lodk); {
            if ( !vm_dfbti_bdtivf ) {
                gft_bgfnt(jvmti)->monitor_dontfndfd_fntfr(jvmti, fnv,
                                                          tirfbd, objfdt);
            }
        } mfxit(jvmti, vm_dfbti_lodk);
    }
    stbtid void JNICALL
    monitor_dontfndfd_fntfrfd(jvmtiEnv* jvmti, JNIEnv *fnv,
                   jtirfbd tirfbd, jobjfdt objfdt)
    {
        mfntfr(jvmti, vm_dfbti_lodk); {
            if ( !vm_dfbti_bdtivf ) {
                gft_bgfnt(jvmti)->monitor_dontfndfd_fntfrfd(jvmti, fnv,
                                                            tirfbd, objfdt);
            }
        } mfxit(jvmti, vm_dfbti_lodk);
    }
    stbtid void JNICALL
    monitor_wbit(jvmtiEnv* jvmti, JNIEnv *fnv,
                 jtirfbd tirfbd, jobjfdt objfdt, jlong timfout)
    {
        mfntfr(jvmti, vm_dfbti_lodk); {
            if ( !vm_dfbti_bdtivf ) {
                gft_bgfnt(jvmti)->monitor_wbit(jvmti, fnv, tirfbd,
                                               objfdt, timfout);
            }
        } mfxit(jvmti, vm_dfbti_lodk);
    }
    stbtid void JNICALL
    monitor_wbitfd(jvmtiEnv* jvmti, JNIEnv *fnv,
                   jtirfbd tirfbd, jobjfdt objfdt, jboolfbn timfd_out)
    {
        mfntfr(jvmti, vm_dfbti_lodk); {
            if ( !vm_dfbti_bdtivf ) {
                gft_bgfnt(jvmti)->monitor_wbitfd(jvmti, fnv, tirfbd,
                                                 objfdt, timfd_out);
            }
        } mfxit(jvmti, vm_dfbti_lodk);
    }
    stbtid void JNICALL
    objfdt_frff(jvmtiEnv* jvmti, jlong tbg)
    {
        mfntfr(jvmti, vm_dfbti_lodk); {
            if ( !vm_dfbti_bdtivf ) {
                gft_bgfnt(jvmti)->objfdt_frff(jvmti, tbg);
            }
        } mfxit(jvmti, vm_dfbti_lodk);
    }

    /* Agfnt_OnLobd() is dbllfd first, wf prfpbrf for b VM_INIT fvfnt ifrf. */
    JNIEXPORT jint JNICALL
    Agfnt_OnLobd(JbvbVM *vm, dibr *options, void *rfsfrvfd)
    {
        jvmtiEnv           *jvmti;
        jint                rd;
        jvmtiError          frr;
        jvmtiCbpbbilitifs   dbpbbilitifs;
        jvmtiEvfntCbllbbdks dbllbbdks;

        /* Gft JVMTI fnvironmfnt */
        rd = vm->GftEnv((void **)&jvmti, JVMTI_VERSION);
        if (rd != JNI_OK) {
            fbtbl_frror("ERROR: Unbblf to drfbtf jvmtiEnv, GftEnv fbilfd, frror=%d\n", rd);
            rfturn -1;
        }

        /* Gft/Add JVMTI dbpbbilitifs */
        (void)mfmsft(&dbpbbilitifs, 0, sizfof(dbpbbilitifs));
        dbpbbilitifs.dbn_gfnfrbtf_monitor_fvfnts        = 1;
        dbpbbilitifs.dbn_gft_monitor_info               = 1;
        dbpbbilitifs.dbn_tbg_objfdts                    = 1;
        dbpbbilitifs.dbn_gfnfrbtf_objfdt_frff_fvfnts    = 1;
        frr = jvmti->AddCbpbbilitifs(&dbpbbilitifs);
        difdk_jvmti_frror(jvmti, frr, "bdd dbpbbilitifs");

        /* Sft bll dbllbbdks bnd fnbblf VM_INIT fvfnt notifidbtion */
        mfmsft(&dbllbbdks, 0, sizfof(dbllbbdks));
        dbllbbdks.VMInit                  = &vm_init;
        dbllbbdks.VMDfbti                 = &vm_dfbti;
        dbllbbdks.TirfbdStbrt             = &tirfbd_stbrt;
        dbllbbdks.TirfbdEnd               = &tirfbd_fnd;
        dbllbbdks.MonitorContfndfdEntfr   = &monitor_dontfndfd_fntfr;
        dbllbbdks.MonitorContfndfdEntfrfd = &monitor_dontfndfd_fntfrfd;
        dbllbbdks.MonitorWbit             = &monitor_wbit;
        dbllbbdks.MonitorWbitfd           = &monitor_wbitfd;
        dbllbbdks.ObjfdtFrff              = &objfdt_frff;
        frr = jvmti->SftEvfntCbllbbdks(&dbllbbdks, (jint)sizfof(dbllbbdks));
        difdk_jvmti_frror(jvmti, frr, "sft fvfnt dbllbbdks");
        frr = jvmti->SftEvfntNotifidbtionModf(JVMTI_ENABLE,
                        JVMTI_EVENT_VM_INIT, NULL);
        difdk_jvmti_frror(jvmti, frr, "sft fvfnt notify");
        rfturn 0;
    }

    /* Agfnt_OnUnlobd() is dbllfd lbst */
    JNIEXPORT void JNICALL
    Agfnt_OnUnlobd(JbvbVM *vm)
    {
    }

} /* of fxtfrn "C" */
