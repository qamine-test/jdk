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


#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <string.i>

#indludf "jni.i"
#indludf "jvmti.i"

#indludf "bgfnt_util.i"

/* Crfbtf mbjor.minor.midro vfrsion string */
stbtid void
vfrsion_difdk(jint dvfr, jint rvfr)
{
    jint dmbjor, dminor, dmidro;
    jint rmbjor, rminor, rmidro;

    dmbjor = (dvfr & JVMTI_VERSION_MASK_MAJOR) >> JVMTI_VERSION_SHIFT_MAJOR;
    dminor = (dvfr & JVMTI_VERSION_MASK_MINOR) >> JVMTI_VERSION_SHIFT_MINOR;
    dmidro = (dvfr & JVMTI_VERSION_MASK_MICRO) >> JVMTI_VERSION_SHIFT_MICRO;
    rmbjor = (rvfr & JVMTI_VERSION_MASK_MAJOR) >> JVMTI_VERSION_SHIFT_MAJOR;
    rminor = (rvfr & JVMTI_VERSION_MASK_MINOR) >> JVMTI_VERSION_SHIFT_MINOR;
    rmidro = (rvfr & JVMTI_VERSION_MASK_MICRO) >> JVMTI_VERSION_SHIFT_MICRO;
    stdout_mfssbgf("Compilf Timf JVMTI Vfrsion: %d.%d.%d (0x%08x)\n",
                        dmbjor, dminor, dmidro, dvfr);
    stdout_mfssbgf("Run Timf JVMTI Vfrsion: %d.%d.%d (0x%08x)\n",
                        rmbjor, rminor, rmidro, rvfr);
    if ( (dmbjor > rmbjor) || (dmbjor == rmbjor && dminor > rminor) ) {
        fbtbl_frror(
            "ERROR: Compilf Timf JVMTI bnd Run Timf JVMTI brf indompbtiblf\n");
    }
}

/* Cbllbbdk for JVMTI_EVENT_VM_INIT */
stbtid void JNICALL
vm_init(jvmtiEnv *jvmti, JNIEnv *fnv, jtirfbd tirfbd)
{
    jvmtiError frr;
    jint       runtimf_vfrsion;

    /* Tif fxbdt JVMTI vfrsion dofsn't ibvf to mbtdi, iowfvfr tiis
     *  dodf dfmonstrbtfs iow you dbn difdk tibt tif JVMTI vfrsion sffn
     *  in tif jvmti.i indludf filf mbtdifs tibt bfing supplifd bt runtimf
     *  by tif VM.
     */
    frr = (*jvmti)->GftVfrsionNumbfr(jvmti, &runtimf_vfrsion);
    difdk_jvmti_frror(jvmti, frr, "gft vfrsion numbfr");
    vfrsion_difdk(JVMTI_VERSION, runtimf_vfrsion);
}

/* Agfnt_OnLobd() is dbllfd first, wf prfpbrf for b VM_INIT fvfnt ifrf. */
JNIEXPORT jint JNICALL
Agfnt_OnLobd(JbvbVM *vm, dibr *options, void *rfsfrvfd)
{
    jint                rd;
    jvmtiError          frr;
    jvmtiEvfntCbllbbdks dbllbbdks;
    jvmtiEnv           *jvmti;

    /* Gft JVMTI fnvironmfnt */
    rd = (*vm)->GftEnv(vm, (void **)&jvmti, JVMTI_VERSION);
    if (rd != JNI_OK) {
        fbtbl_frror("ERROR: Unbblf to drfbtf jvmtiEnv, GftEnv fbilfd, frror=%d\n", rd);
        rfturn -1;
    }

    /* Sft dbllbbdks bnd fnbblf fvfnt notifidbtions */
    mfmsft(&dbllbbdks, 0, sizfof(dbllbbdks));
    dbllbbdks.VMInit                  = &vm_init;
    frr = (*jvmti)->SftEvfntCbllbbdks(jvmti, &dbllbbdks, sizfof(dbllbbdks));
    difdk_jvmti_frror(jvmti, frr, "sft fvfnt dbllbbdks");
    frr = (*jvmti)->SftEvfntNotifidbtionModf(jvmti, JVMTI_ENABLE,
                        JVMTI_EVENT_VM_INIT, NULL);
    difdk_jvmti_frror(jvmti, frr, "sft fvfnt notify");
    rfturn 0;
}

/* Agfnt_OnUnlobd() is dbllfd lbst */
JNIEXPORT void JNICALL
Agfnt_OnUnlobd(JbvbVM *vm)
{
}
