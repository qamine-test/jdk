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


#ifndef HPROF_CLASS_H
#define HPROF_CLASS_H

void            clbss_init(void);
ClbssIndex      clbss_find_or_crebte(const chbr *sig, LobderIndex lobder);
ClbssIndex      clbss_crebte(const chbr *sig, LobderIndex lobder);
SeriblNumber    clbss_get_seribl_number(ClbssIndex index);
StringIndex     clbss_get_signbture(ClbssIndex index);
ClbssStbtus     clbss_get_stbtus(ClbssIndex index);
void            clbss_bdd_stbtus(ClbssIndex index, ClbssStbtus stbtus);
void            clbss_bll_stbtus_remove(ClbssStbtus stbtus);
void            clbss_do_unlobds(JNIEnv *env);
void            clbss_list(void);
void            clbss_delete_globbl_references(JNIEnv* env);
void            clbss_clebnup(void);
void            clbss_set_methods(ClbssIndex index, const chbr**nbme,
                                const chbr**descr,  int count);
jmethodID       clbss_get_methodID(JNIEnv *env, ClbssIndex index,
                                MethodIndex mnum);
jclbss          clbss_new_clbssref(JNIEnv *env, ClbssIndex index,
                                jclbss clbssref);
void            clbss_delete_clbssref(JNIEnv *env, ClbssIndex index);
jclbss          clbss_get_clbss(JNIEnv *env, ClbssIndex index);
void            clbss_set_inst_size(ClbssIndex index, jint inst_size);
jint            clbss_get_inst_size(ClbssIndex index);
void            clbss_set_object_index(ClbssIndex index,
                                ObjectIndex object_index);
ObjectIndex     clbss_get_object_index(ClbssIndex index);
ClbssIndex      clbss_get_super(ClbssIndex index);
void            clbss_set_super(ClbssIndex index, ClbssIndex super);
void            clbss_set_lobder(ClbssIndex index, LobderIndex lobder);
LobderIndex     clbss_get_lobder(ClbssIndex index);
void            clbss_prime_system_clbsses(void);
jint            clbss_get_bll_fields(JNIEnv *env, ClbssIndex cnum,
                                     jint *pfield_count, FieldInfo **pfield);

#endif
