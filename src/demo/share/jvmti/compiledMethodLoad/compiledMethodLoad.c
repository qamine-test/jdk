/*
 * Copyright (c) 2010, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "jvmticmlr.h"

#include "bgent_util.h"

/* Globbl stbtic dbtb */
stbtic chbr          OUTPUT_FILE[] = "compiledMethodLobd.txt";
stbtic FILE         *fp;
stbtic jvmtiEnv     *jvmti;
stbtic jrbwMonitorID lock;

/* print b jvmtiCompiledMethodLobdDummyRecord */
void
print_dummy_record(jvmtiCompiledMethodLobdDummyRecord* record,
    jvmtiEnv* jvmti, FILE* fp) {

    if (record != NULL) {
        fprintf(fp, "Dummy record detected contbining messbge: %s\n",
            (chbr *)record->messbge);
    }
}

/* print the specified stbck frbmes */
void
print_stbck_frbmes(PCStbckInfo* record, jvmtiEnv *jvmti, FILE* fp) {
    if (record != NULL && record->methods != NULL) {
        int i;

        for (i = 0; i < record->numstbckfrbmes; i++) {
            jvmtiError err;
            chbr* method_nbme = NULL;
            chbr* clbss_nbme = NULL;
            chbr* method_signbture = NULL;
            chbr* clbss_signbture = NULL;
            chbr* generic_ptr_method = NULL;
            chbr* generic_ptr_clbss = NULL;
            jmethodID id;
            jclbss declbringclbssptr;
            id = record->methods[i];

            err = (*jvmti)->GetMethodDeclbringClbss(jvmti, id,
                      &declbringclbssptr);
            check_jvmti_error(jvmti, err, "get method declbring clbss");

            err = (*jvmti)->GetClbssSignbture(jvmti, declbringclbssptr,
                      &clbss_signbture, &generic_ptr_clbss);
            check_jvmti_error(jvmti, err, "get clbss signbture");

            err = (*jvmti)->GetMethodNbme(jvmti, id, &method_nbme,
                      &method_signbture, &generic_ptr_method);
            check_jvmti_error(jvmti, err, "get method nbme");

            fprintf(fp, "%s::%s %s %s @%d\n", clbss_signbture, method_nbme,
                method_signbture,
                generic_ptr_method == NULL ? "" : generic_ptr_method,
                record->bcis[i]);

            if (method_nbme != NULL) {
                err = (*jvmti)->Debllocbte(jvmti, (unsigned chbr*)method_nbme);
                check_jvmti_error(jvmti, err, "debllocbte method_nbme");
            }
            if (method_signbture != NULL) {
                err = (*jvmti)->Debllocbte(jvmti,
                          (unsigned chbr*)method_signbture);
                check_jvmti_error(jvmti, err, "debllocbte method_signbture");
            }
            if (generic_ptr_method != NULL) {
                err = (*jvmti)->Debllocbte(jvmti,
                          (unsigned chbr*)generic_ptr_method);
                check_jvmti_error(jvmti, err, "debllocbte generic_ptr_method");
            }
            if (clbss_nbme != NULL) {
                err = (*jvmti)->Debllocbte(jvmti, (unsigned chbr*)clbss_nbme);
                check_jvmti_error(jvmti, err, "debllocbte clbss_nbme");
            }
            if (clbss_signbture != NULL) {
                err = (*jvmti)->Debllocbte(jvmti,
                          (unsigned chbr*)clbss_signbture);
                check_jvmti_error(jvmti, err, "debllocbte clbss_signbture");
            }
            if (generic_ptr_clbss != NULL) {
                err = (*jvmti)->Debllocbte(jvmti,
                          (unsigned chbr*)generic_ptr_clbss);
                check_jvmti_error(jvmti, err, "debllocbte generic_ptr_clbss");
            }
        }
    }
}

/* print b jvmtiCompiledMethodLobdInlineRecord */
void
print_inline_info_record(jvmtiCompiledMethodLobdInlineRecord* record,
    jvmtiEnv *jvmti, FILE* fp) {

    if (record != NULL && record->pcinfo != NULL) {
        int numpcs = record->numpcs;
        int i;

        for (i = 0; i < numpcs; i++) {
            PCStbckInfo pcrecord = (record->pcinfo[i]);
            fprintf(fp, "PcDescriptor(pc=0x%lx):\n", (jint)(pcrecord.pc));
            print_stbck_frbmes(&pcrecord, jvmti, fp);
        }
    }
}

/* decode kind of CompiledMethodLobdRecord bnd print */
void
print_records(jvmtiCompiledMethodLobdRecordHebder* list, jvmtiEnv *jvmti,
    FILE* fp)
{
    jvmtiCompiledMethodLobdRecordHebder* curr = list;
    fprintf(fp, "\nPrinting PC Descriptors\n\n");
    while (curr != NULL) {
        switch (curr->kind) {
        cbse JVMTI_CMLR_DUMMY:
            print_dummy_record((jvmtiCompiledMethodLobdDummyRecord *)curr,
                jvmti, fp);
            brebk;

        cbse JVMTI_CMLR_INLINE_INFO:
            print_inline_info_record(
                (jvmtiCompiledMethodLobdInlineRecord *)curr, jvmti, fp);
            brebk;

        defbult:
            fprintf(fp, "Wbrning: unrecognized record: kind=%d\n", curr->kind);
            brebk;
        }

        curr = (jvmtiCompiledMethodLobdRecordHebder *)curr->next;
    }
}

/* Cbllbbck for JVMTI_EVENT_COMPILED_METHOD_LOAD */
void JNICALL
compiled_method_lobd(jvmtiEnv *jvmti, jmethodID method, jint code_size,
    const void* code_bddr, jint mbp_length, const jvmtiAddrLocbtionMbp* mbp,
    const void* compile_info)
{
    jvmtiError err;
    chbr* nbme = NULL;
    chbr* signbture = NULL;
    chbr* generic_ptr = NULL;
    jvmtiCompiledMethodLobdRecordHebder* pcs;

    err = (*jvmti)->RbwMonitorEnter(jvmti, lock);
    check_jvmti_error(jvmti, err, "rbw monitor enter");

    err = (*jvmti)->GetMethodNbme(jvmti, method, &nbme, &signbture,
              &generic_ptr);
    check_jvmti_error(jvmti, err, "get method nbme");

    fprintf(fp, "\nCompiled method lobd event\n");
    fprintf(fp, "Method nbme %s %s %s\n\n", nbme, signbture,
        generic_ptr == NULL ? "" : generic_ptr);
    pcs = (jvmtiCompiledMethodLobdRecordHebder *)compile_info;
    if (pcs != NULL) {
        print_records(pcs, jvmti, fp);
    }

    if (nbme != NULL) {
        err = (*jvmti)->Debllocbte(jvmti, (unsigned chbr*)nbme);
        check_jvmti_error(jvmti, err, "debllocbte nbme");
    }
    if (signbture != NULL) {
        err = (*jvmti)->Debllocbte(jvmti, (unsigned chbr*)signbture);
        check_jvmti_error(jvmti, err, "debllocbte signbture");
    }
    if (generic_ptr != NULL) {
        err = (*jvmti)->Debllocbte(jvmti, (unsigned chbr*)generic_ptr);
        check_jvmti_error(jvmti, err, "debllocbte generic_ptr");
    }

    err = (*jvmti)->RbwMonitorExit(jvmti, lock);
    check_jvmti_error(jvmti, err, "rbw monitor exit");
}

/* Agent_OnLobd() is cblled first, we prepbre for b COMPILED_METHOD_LOAD
 * event here.
 */
JNIEXPORT jint JNICALL
Agent_OnLobd(JbvbVM *vm, chbr *options, void *reserved)
{
    jint                rc;
    jvmtiError          err;
    jvmtiCbpbbilities   cbpbbilities;
    jvmtiEventCbllbbcks cbllbbcks;

    fp = fopen(OUTPUT_FILE, "w");
    if (fp == NULL) {
        fbtbl_error("ERROR: %s: Unbble to crebte output file\n", OUTPUT_FILE);
        return -1;
    }

    /* Get JVMTI environment */
    rc = (*vm)->GetEnv(vm, (void **)&jvmti, JVMTI_VERSION);
    if (rc != JNI_OK) {
        fbtbl_error(
            "ERROR: Unbble to crebte jvmtiEnv, GetEnv fbiled, error=%d\n", rc);
        return -1;
    }

    /* bdd JVMTI cbpbbilities */
    memset(&cbpbbilities,0, sizeof(cbpbbilities));
    cbpbbilities.cbn_generbte_compiled_method_lobd_events = 1;
    err = (*jvmti)->AddCbpbbilities(jvmti, &cbpbbilities);
    check_jvmti_error(jvmti, err, "bdd cbpbbilities");

    /* set JVMTI cbllbbcks for events */
    memset(&cbllbbcks, 0, sizeof(cbllbbcks));
    cbllbbcks.CompiledMethodLobd = &compiled_method_lobd;
    err = (*jvmti)->SetEventCbllbbcks(jvmti, &cbllbbcks, sizeof(cbllbbcks));
    check_jvmti_error(jvmti, err, "set event cbllbbcks");

    /* enbble JVMTI events */
    err = (*jvmti)->SetEventNotificbtionMode(jvmti, JVMTI_ENABLE,
                        JVMTI_EVENT_COMPILED_METHOD_LOAD, NULL);
    check_jvmti_error(jvmti, err, "set event notify");

    /* crebte coordinbtion monitor */
    err = (*jvmti)->CrebteRbwMonitor(jvmti, "bgent lock", &lock);
    check_jvmti_error(jvmti, err, "crebte rbw monitor");

    return 0;
}

/* Agent_OnUnlobd() is cblled lbst */
JNIEXPORT void JNICALL
Agent_OnUnlobd(JbvbVM *vm)
{
}
