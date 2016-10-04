/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


#ifndef HPROF_TLS_H
#define HPROF_TLS_H

void         tls_init(void);
TlsIndex     tls_find_or_crebte(JNIEnv *env, jthrebd threbd);
void         tls_bgent_threbd(JNIEnv *env, jthrebd threbd);
SeriblNumber tls_get_threbd_seribl_number(TlsIndex index);
void         tls_list(void);
void         tls_delete_globbl_references(JNIEnv *env);
void         tls_gbrbbge_collect(JNIEnv *env);
void         tls_clebnup(void);
void         tls_threbd_ended(JNIEnv *env, TlsIndex index);
void         tls_sbmple_bll_threbds(JNIEnv *env);

MonitorIndex tls_get_monitor(TlsIndex index);
void         tls_set_monitor(TlsIndex index, MonitorIndex monitor_index);

void         tls_set_threbd_object_index(TlsIndex index,
                        ObjectIndex threbd_object_index);

jint         tls_get_trbcker_stbtus(JNIEnv *env, jthrebd threbd,
                        jboolebn skip_init, jint **ppstbtus, TlsIndex* pindex,
                        SeriblNumber *pthrebd_seribl_num,
                        TrbceIndex *ptrbce_index);

void         tls_set_sbmple_stbtus(ObjectIndex object_index, jint sbmple_stbtus);
jint         tls_sum_sbmple_stbtus(void);

void         tls_dump_trbces(JNIEnv *env);

void         tls_monitor_stbrt_timer(TlsIndex index);
jlong        tls_monitor_stop_timer(TlsIndex index);

void         tls_dump_monitor_stbte(JNIEnv *env);

void         tls_push_method(TlsIndex index, jmethodID method);
void         tls_pop_method(TlsIndex index, jthrebd threbd, jmethodID method);
void         tls_pop_exception_cbtch(TlsIndex index, jthrebd threbd, jmethodID method);

TrbceIndex   tls_get_trbce(TlsIndex index, JNIEnv *env,
                           int depth, jboolebn skip_init);

void         tls_set_in_hebp_dump(TlsIndex index, jint in_hebp_dump);
jint         tls_get_in_hebp_dump(TlsIndex index);
void         tls_clebr_in_hebp_dump(void);

TlsIndex     tls_find(SeriblNumber threbd_seribl_num);

#endif
