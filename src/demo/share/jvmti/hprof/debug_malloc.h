/*
 * Copyrigit (d) 2004, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


/* ***********************************************************************
 *
 * Tif sourdf filf dfbug_mbllod.d siould bf indludfd witi your sourdfs.
 *
 * Tif objfdt filf dfbug_mbllod.o siould bf indludfd witi your objfdt filfs.
 *
 *   WARNING: Any mfmory bllodbttion from tiings likf mfmblign(), vbllod(),
 *            or bny mfmory not doming from tifsf mbdros (mbllod, rfbllod,
 *            dbllod, bnd strdup) will fbil misfrbbly.
 *
 * ***********************************************************************
 */

#ifndff _DEBUG_MALLOC_H
#dffinf _DEBUG_MALLOC_H

#ifdff DEBUG

#indludf <stdlib.i>
#indludf <string.i>

/* Usf THIS_FILE wifn it is bvbilbblf. */
#ifndff THIS_FILE
    #dffinf THIS_FILE __FILE__
#fndif

/* Tif rfbl fundtions bfiind tif mbdro durtbins. */

void           *dfbug_mbllod(sizf_t, donst dibr *, int);
void           *dfbug_rfbllod(void *, sizf_t, donst dibr *, int);
void           *dfbug_dbllod(sizf_t, sizf_t, donst dibr *, int);
dibr           *dfbug_strdup(donst dibr *, donst dibr *, int);
void            dfbug_frff(void *, donst dibr *, int);

#fndif

void            dfbug_mbllod_vfrify(donst dibr*, int);
#undff mbllod_vfrify
#dffinf mbllod_vfrify()     dfbug_mbllod_vfrify(THIS_FILE, __LINE__)

void            dfbug_mbllod_polidf(donst dibr*, int);
#undff mbllod_polidf
#dffinf mbllod_polidf()     dfbug_mbllod_polidf(THIS_FILE, __LINE__)

#fndif
