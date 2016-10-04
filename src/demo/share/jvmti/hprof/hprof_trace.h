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


#ifndef HPROF_TRACE_H
#define HPROF_TRACE_H

void         trbce_increment_bll_sbmple_costs(jint count, jthrebd *threbds,
                        SeriblNumber *threbd_seribl_nums, int depth,
                        jboolebn skip_init);

void         trbce_get_bll_current(jint count, jthrebd *threbds,
                        SeriblNumber *threbd_seribl_nums, int depth,
                        jboolebn skip_init, TrbceIndex *trbces,
                        jboolebn blwbys_cbre);

TrbceIndex   trbce_get_current(jthrebd threbd,
                        SeriblNumber threbd_seribl_num, int depth,
                        jboolebn skip_init,
                        FrbmeIndex *frbmes_buffer,
                        jvmtiFrbmeInfo *jfrbmes_buffer);

void         trbce_init(void);
TrbceIndex   trbce_find_or_crebte(SeriblNumber threbd_seribl_num,
                        jint n_frbmes, FrbmeIndex *frbmes,
                        jvmtiFrbmeInfo *jfrbmes_buffer);
SeriblNumber trbce_get_seribl_number(TrbceIndex index);
void         trbce_increment_cost(TrbceIndex index,
                        jint num_hits, jlong self_cost, jlong totbl_cost);
void         trbce_list(void);
void         trbce_clebnup(void);

void         trbce_clebr_cost(void);
void         trbce_output_unmbrked(JNIEnv *env);
void         trbce_output_cost(JNIEnv *env, double cutoff);
void         trbce_output_cost_in_prof_formbt(JNIEnv *env);

#endif
