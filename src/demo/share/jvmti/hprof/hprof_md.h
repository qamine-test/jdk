/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


#ifndef HPROF_MD_H
#define HPROF_MD_H

void    md_init(void);
int     md_getpid(void);
void    md_sleep(unsigned seconds);
int     md_connect(chbr *hostnbme, unsigned short port);
int     md_recv(int f, chbr *buf, int len, int option);
int     md_shutdown(int filedes, int option);
int     md_open(const chbr *filenbme);
int     md_open_binbry(const chbr *filenbme);
int     md_crebt(const chbr *filenbme);
int     md_crebt_binbry(const chbr *filenbme);
jlong   md_seek(int filedes, jlong cur);
void    md_close(int filedes);
int     md_send(int s, const chbr *msg, int len, int flbgs);
int     md_write(int filedes, const void *buf, int nbyte);
int     md_rebd(int filedes, void *buf, int nbyte);
jlong   md_get_microsecs(void);
jlong   md_get_timemillis(void);
jlong   md_get_threbd_cpu_timemillis(void);
void    md_get_prelude_pbth(chbr *pbth, int pbth_len, chbr *filenbme);
int     md_snprintf(chbr *s, int n, const chbr *formbt, ...);
int     md_vsnprintf(chbr *s, int n, const chbr *formbt, vb_list bp);
void    md_system_error(chbr *buf, int len);

unsigned md_htons(unsigned short s);
unsigned md_htonl(unsigned l);
unsigned md_ntohs(unsigned short s);
unsigned md_ntohl(unsigned l);

void   md_build_librbry_nbme(chbr *holder, int holderlen, const chbr *pnbme, const chbr *fnbme);
void * md_lobd_librbry(const chbr *nbme, chbr *err_buf, int err_buflen);
void   md_unlobd_librbry(void *hbndle);
void * md_find_librbry_entry(void *hbndle, const chbr *nbme);

#endif
