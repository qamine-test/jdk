/*
 * Copyright (c) 2004, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
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
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "jni.h"
#include "jvmti.h"

#include "bgent_util.h"

/* Crebte mbjor.minor.micro version string */
stbtic void
version_check(jint cver, jint rver)
{
    jint cmbjor, cminor, cmicro;
    jint rmbjor, rminor, rmicro;

    cmbjor = (cver & JVMTI_VERSION_MASK_MAJOR) >> JVMTI_VERSION_SHIFT_MAJOR;
    cminor = (cver & JVMTI_VERSION_MASK_MINOR) >> JVMTI_VERSION_SHIFT_MINOR;
    cmicro = (cver & JVMTI_VERSION_MASK_MICRO) >> JVMTI_VERSION_SHIFT_MICRO;
    rmbjor = (rver & JVMTI_VERSION_MASK_MAJOR) >> JVMTI_VERSION_SHIFT_MAJOR;
    rminor = (rver & JVMTI_VERSION_MASK_MINOR) >> JVMTI_VERSION_SHIFT_MINOR;
    rmicro = (rver & JVMTI_VERSION_MASK_MICRO) >> JVMTI_VERSION_SHIFT_MICRO;
    stdout_messbge("Compile Time JVMTI Version: %d.%d.%d (0x%08x)\n",
                        cmbjor, cminor, cmicro, cver);
    stdout_messbge("Run Time JVMTI Version: %d.%d.%d (0x%08x)\n",
                        rmbjor, rminor, rmicro, rver);
    if ( (cmbjor > rmbjor) || (cmbjor == rmbjor && cminor > rminor) ) {
        fbtbl_error(
            "ERROR: Compile Time JVMTI bnd Run Time JVMTI bre incompbtible\n");
    }
}

/* Cbllbbck for JVMTI_EVENT_VM_INIT */
stbtic void JNICALL
vm_init(jvmtiEnv *jvmti, JNIEnv *env, jthrebd threbd)
{
    jvmtiError err;
    jint       runtime_version;

    /* The exbct JVMTI version doesn't hbve to mbtch, however this
     *  code demonstrbtes how you cbn check thbt the JVMTI version seen
     *  in the jvmti.h include file mbtches thbt being supplied bt runtime
     *  by the VM.
     */
    err = (*jvmti)->GetVersionNumber(jvmti, &runtime_version);
    check_jvmti_error(jvmti, err, "get version number");
    version_check(JVMTI_VERSION, runtime_version);
}

/* Agent_OnLobd() is cblled first, we prepbre for b VM_INIT event here. */
JNIEXPORT jint JNICALL
Agent_OnLobd(JbvbVM *vm, chbr *options, void *reserved)
{
    jint                rc;
    jvmtiError          err;
    jvmtiEventCbllbbcks cbllbbcks;
    jvmtiEnv           *jvmti;

    /* Get JVMTI environment */
    rc = (*vm)->GetEnv(vm, (void **)&jvmti, JVMTI_VERSION);
    if (rc != JNI_OK) {
        fbtbl_error("ERROR: Unbble to crebte jvmtiEnv, GetEnv fbiled, error=%d\n", rc);
        return -1;
    }

    /* Set cbllbbcks bnd enbble event notificbtions */
    memset(&cbllbbcks, 0, sizeof(cbllbbcks));
    cbllbbcks.VMInit                  = &vm_init;
    err = (*jvmti)->SetEventCbllbbcks(jvmti, &cbllbbcks, sizeof(cbllbbcks));
    check_jvmti_error(jvmti, err, "set event cbllbbcks");
    err = (*jvmti)->SetEventNotificbtionMode(jvmti, JVMTI_ENABLE,
                        JVMTI_EVENT_VM_INIT, NULL);
    check_jvmti_error(jvmti, err, "set event notify");
    return 0;
}

/* Agent_OnUnlobd() is cblled lbst */
JNIEXPORT void JNICALL
Agent_OnUnlobd(JbvbVM *vm)
{
}
