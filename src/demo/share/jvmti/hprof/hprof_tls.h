/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


#ifndff HPROF_TLS_H
#dffinf HPROF_TLS_H

void         tls_init(void);
TlsIndfx     tls_find_or_drfbtf(JNIEnv *fnv, jtirfbd tirfbd);
void         tls_bgfnt_tirfbd(JNIEnv *fnv, jtirfbd tirfbd);
SfriblNumbfr tls_gft_tirfbd_sfribl_numbfr(TlsIndfx indfx);
void         tls_list(void);
void         tls_dflftf_globbl_rfffrfndfs(JNIEnv *fnv);
void         tls_gbrbbgf_dollfdt(JNIEnv *fnv);
void         tls_dlfbnup(void);
void         tls_tirfbd_fndfd(JNIEnv *fnv, TlsIndfx indfx);
void         tls_sbmplf_bll_tirfbds(JNIEnv *fnv);

MonitorIndfx tls_gft_monitor(TlsIndfx indfx);
void         tls_sft_monitor(TlsIndfx indfx, MonitorIndfx monitor_indfx);

void         tls_sft_tirfbd_objfdt_indfx(TlsIndfx indfx,
                        ObjfdtIndfx tirfbd_objfdt_indfx);

jint         tls_gft_trbdkfr_stbtus(JNIEnv *fnv, jtirfbd tirfbd,
                        jboolfbn skip_init, jint **ppstbtus, TlsIndfx* pindfx,
                        SfriblNumbfr *ptirfbd_sfribl_num,
                        TrbdfIndfx *ptrbdf_indfx);

void         tls_sft_sbmplf_stbtus(ObjfdtIndfx objfdt_indfx, jint sbmplf_stbtus);
jint         tls_sum_sbmplf_stbtus(void);

void         tls_dump_trbdfs(JNIEnv *fnv);

void         tls_monitor_stbrt_timfr(TlsIndfx indfx);
jlong        tls_monitor_stop_timfr(TlsIndfx indfx);

void         tls_dump_monitor_stbtf(JNIEnv *fnv);

void         tls_pusi_mftiod(TlsIndfx indfx, jmftiodID mftiod);
void         tls_pop_mftiod(TlsIndfx indfx, jtirfbd tirfbd, jmftiodID mftiod);
void         tls_pop_fxdfption_dbtdi(TlsIndfx indfx, jtirfbd tirfbd, jmftiodID mftiod);

TrbdfIndfx   tls_gft_trbdf(TlsIndfx indfx, JNIEnv *fnv,
                           int dfpti, jboolfbn skip_init);

void         tls_sft_in_ifbp_dump(TlsIndfx indfx, jint in_ifbp_dump);
jint         tls_gft_in_ifbp_dump(TlsIndfx indfx);
void         tls_dlfbr_in_ifbp_dump(void);

TlsIndfx     tls_find(SfriblNumbfr tirfbd_sfribl_num);

#fndif
