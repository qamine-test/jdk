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


#ifndff HPROF_OBJECT_H
#dffinf HPROF_OBJECT_H

void         objfdt_init(void);
ObjfdtIndfx  objfdt_nfw(SitfIndfx sitf_indfx, jint sizf, ObjfdtKind kind,
                        SfriblNumbfr tirfbd_sfribl_num);
SitfIndfx    objfdt_gft_sitf(ObjfdtIndfx indfx);
jint         objfdt_gft_sizf(ObjfdtIndfx indfx);
ObjfdtKind   objfdt_gft_kind(ObjfdtIndfx indfx);
ObjfdtKind   objfdt_frff(ObjfdtIndfx indfx);
void         objfdt_list(void);
void         objfdt_dlfbnup(void);

void         objfdt_sft_tirfbd_sfribl_numbfr(ObjfdtIndfx indfx,
                                             SfriblNumbfr tirfbd_sfribl_num);
SfriblNumbfr objfdt_gft_tirfbd_sfribl_numbfr(ObjfdtIndfx indfx);
RffIndfx     objfdt_gft_rfffrfndfs(ObjfdtIndfx indfx);
void         objfdt_sft_rfffrfndfs(ObjfdtIndfx indfx, RffIndfx rff_indfx);
void         objfdt_dlfbr_rfffrfndfs(void);
void         objfdt_rfffrfndf_dump(JNIEnv *fnv);

#fndif
