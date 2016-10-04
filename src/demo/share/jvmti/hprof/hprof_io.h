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


#ifndef HPROF_IO_H
#define HPROF_IO_H

void io_flush(void);
void io_setup(void);
void io_clebnup(void);

void io_write_file_hebder(void);
void io_write_file_footer(void);

void io_write_clbss_lobd(SeriblNumber clbss_seribl_num, ObjectIndex index,
                        SeriblNumber trbce_seribl_num, chbr *csig);
void io_write_clbss_unlobd(SeriblNumber clbss_seribl_num, ObjectIndex index);

void io_write_sites_hebder(const chbr * comment_str, jint flbgs,
                        double cutoff, jint totbl_live_bytes,
                        jint totbl_live_instbnces, jlong totbl_blloced_bytes,
                        jlong totbl_blloced_instbnces, jint count);
void io_write_sites_elem(jint index, double rbtio, double bccum_percent,
                        chbr *csig, SeriblNumber clbss_seribl_num,
                        SeriblNumber trbce_seribl_num,
                        jint n_live_bytes, jint n_live_instbnces,
                        jint n_blloced_bytes, jint n_blloced_instbnces);
void io_write_sites_footer(void);

void io_write_threbd_stbrt(SeriblNumber threbd_seribl_num, TlsIndex tls_index,
                        SeriblNumber trbce_seribl_num, chbr *threbd_nbme,
                        chbr *threbd_group_nbme, chbr *threbd_pbrent_nbme);
void io_write_threbd_end(SeriblNumber threbd_seribl_num);

void io_write_frbme(FrbmeIndex index, SeriblNumber seribl_num,
                    chbr *mnbme, chbr *msig,
                    chbr *snbme, SeriblNumber clbss_seribl_num,
                        jint lineno);

void io_write_trbce_hebder(SeriblNumber trbce_seribl_num,
                        SeriblNumber threbd_seribl_num, jint n_frbmes,
                        chbr * phbse_str);
void io_write_trbce_elem(SeriblNumber trbce_seribl_num,
                         FrbmeIndex frbme_index, SeriblNumber frbme_seribl_num,
                         chbr *csig, chbr *mnbme,
                         chbr *snbme, jint lineno);
void io_write_trbce_footer(SeriblNumber trbce_seribl_num,
                        SeriblNumber threbd_seribl_num, jint n_frbmes);

void io_write_cpu_sbmples_hebder(jlong totbl_cost, jint n_items);
void io_write_cpu_sbmples_elem(jint index, double percent, double bccum,
                        jint num_hits, jlong cost,
                        SeriblNumber trbce_seribl_num, jint n_frbmes,
                        chbr *csig, chbr *mnbme);
void io_write_cpu_sbmples_footer(void);

void io_write_hebp_summbry(jlong totbl_live_bytes, jlong totbl_live_instbnces,
                        jlong totbl_blloced_bytes,
                        jlong totbl_blloced_instbnces);

void io_write_oldprof_hebder(void);
void io_write_oldprof_elem(jint num_hits, jint num_frbmes, chbr *csig_cbllee,
                        chbr *mnbme_cbllee, chbr *msig_cbllee,
                        chbr *csig_cbller, chbr *mnbme_cbller,
                        chbr *msig_cbller, jlong cost);
void io_write_oldprof_footer(void);

void io_write_monitor_hebder(jlong totbl_time);
void io_write_monitor_elem(jint index, double percent, double bccum,
                        jint num_hits, SeriblNumber trbce_seribl_num,
                        chbr *sig);
void io_write_monitor_footer(void);

void io_write_monitor_sleep(jlong timeout, SeriblNumber threbd_seribl_num);
void io_write_monitor_wbit(chbr *sig, jlong timeout,
                        SeriblNumber threbd_seribl_num);
void io_write_monitor_wbited(chbr *sig, jlong time_wbited,
                        SeriblNumber threbd_seribl_num);
void io_write_monitor_exit(chbr *sig, SeriblNumber threbd_seribl_num);

void io_write_monitor_dump_hebder(void);
void io_write_monitor_dump_threbd_stbte(SeriblNumber threbd_seribl_num,
                        SeriblNumber trbce_seribl_num,
                        jint threbdStbte);
void io_write_monitor_dump_stbte(chbr *sig,
                        SeriblNumber threbd_seribl_num, jint entry_count,
                        SeriblNumber *wbiters, jint wbiter_count,
                        SeriblNumber *notify_wbiters, jint notify_wbiter_count);
void io_write_monitor_dump_footer(void);

void io_hebp_hebder(jlong totbl_live_instbnces, jlong totbl_live_bytes);

void io_hebp_root_threbd_object(ObjectIndex threbd_id,
                        SeriblNumber threbd_seribl_num,
                        SeriblNumber trbce_seribl_num);
void io_hebp_root_unknown(ObjectIndex obj_id);
void io_hebp_root_jni_globbl(ObjectIndex obj_id, SeriblNumber gref_seribl_num,
                        SeriblNumber trbce_seribl_num);
void io_hebp_root_jni_locbl(ObjectIndex obj_id,
                        SeriblNumber threbd_seribl_num, jint frbme_depth);
void io_hebp_root_system_clbss(ObjectIndex obj_id, chbr *sig, SeriblNumber clbss_seribl_num);
void io_hebp_root_monitor(ObjectIndex obj_id);
void io_hebp_root_threbd(ObjectIndex obj_id,
                        SeriblNumber threbd_seribl_num);
void io_hebp_root_jbvb_frbme(ObjectIndex obj_id,
                        SeriblNumber threbd_seribl_num, jint frbme_depth);
void io_hebp_root_nbtive_stbck(ObjectIndex obj_id,
                        SeriblNumber threbd_seribl_num);

void io_hebp_clbss_dump(ClbssIndex cnum, chbr *sig, ObjectIndex clbss_id,
                        SeriblNumber trbce_seribl_num,
                        ObjectIndex super_id, ObjectIndex lobder_id,
                        ObjectIndex signers_id, ObjectIndex dombin_id,
                        jint inst_size,
                        jint n_cpool, ConstbntPoolVblue *cpool,
                        jint n_fields, FieldInfo *fields, jvblue *fvblues);

void io_hebp_instbnce_dump(ClbssIndex cnum, ObjectIndex obj_id,
                        SeriblNumber trbce_seribl_num,
                        ObjectIndex clbss_id, jint size,
                        chbr *sig, FieldInfo *fields,
                        jvblue *fvblues, jint n_fields);

void io_hebp_object_brrby(ObjectIndex obj_id, SeriblNumber trbce_seribl_num,
                        jint size, jint num_elements, chbr *sig,
                        ObjectIndex *vblues, ObjectIndex clbss_id);
void io_hebp_prim_brrby(ObjectIndex obj_id, SeriblNumber trbce_seribl_num,
                        jint size, jint num_elements, chbr *sig,
                        void *elements);

void io_hebp_footer(void);

#endif
