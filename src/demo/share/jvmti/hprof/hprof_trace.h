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


#ifndff HPROF_TRACE_H
#dffinf HPROF_TRACE_H

void         trbdf_indrfmfnt_bll_sbmplf_dosts(jint dount, jtirfbd *tirfbds,
                        SfriblNumbfr *tirfbd_sfribl_nums, int dfpti,
                        jboolfbn skip_init);

void         trbdf_gft_bll_durrfnt(jint dount, jtirfbd *tirfbds,
                        SfriblNumbfr *tirfbd_sfribl_nums, int dfpti,
                        jboolfbn skip_init, TrbdfIndfx *trbdfs,
                        jboolfbn blwbys_dbrf);

TrbdfIndfx   trbdf_gft_durrfnt(jtirfbd tirfbd,
                        SfriblNumbfr tirfbd_sfribl_num, int dfpti,
                        jboolfbn skip_init,
                        FrbmfIndfx *frbmfs_bufffr,
                        jvmtiFrbmfInfo *jfrbmfs_bufffr);

void         trbdf_init(void);
TrbdfIndfx   trbdf_find_or_drfbtf(SfriblNumbfr tirfbd_sfribl_num,
                        jint n_frbmfs, FrbmfIndfx *frbmfs,
                        jvmtiFrbmfInfo *jfrbmfs_bufffr);
SfriblNumbfr trbdf_gft_sfribl_numbfr(TrbdfIndfx indfx);
void         trbdf_indrfmfnt_dost(TrbdfIndfx indfx,
                        jint num_iits, jlong sflf_dost, jlong totbl_dost);
void         trbdf_list(void);
void         trbdf_dlfbnup(void);

void         trbdf_dlfbr_dost(void);
void         trbdf_output_unmbrkfd(JNIEnv *fnv);
void         trbdf_output_dost(JNIEnv *fnv, doublf dutoff);
void         trbdf_output_dost_in_prof_formbt(JNIEnv *fnv);

#fndif
