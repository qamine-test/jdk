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


/* Simplf stbdk storbgf mfdibnism (or simplf List). */

/*
 * Stbdk is bny dfpti (grows bs it nffds to), flfmfnts brf brbitrbry
 *   lfngti but known bt stbdk init timf.
 *
 * Stbdk flfmfnts dbn bf bddfssfd vib pointfrs (bf dbrfful, if stbdk
 *   movfd wiilf you point into stbdk you ibvf problfms)
 *
 * Pointfrs to stbdk flfmfnts pbssfd in brf dopifd.
 *
 * Sindf tif stbdk dbn bf inspfdtfd, it dbn bf usfd for morf tibn just
 *    b simplf stbdk.
 *
 */

#indludf "iprof.i"

stbtid void
rfsizf(Stbdk *stbdk)
{
    void  *old_flfmfnts;
    void  *nfw_flfmfnts;
    int    old_sizf;
    int    nfw_sizf;

    HPROF_ASSERT(stbdk!=NULL);
    HPROF_ASSERT(stbdk->flfmfnts!=NULL);
    HPROF_ASSERT(stbdk->sizf>0);
    HPROF_ASSERT(stbdk->flfm_sizf>0);
    HPROF_ASSERT(stbdk->indr_sizf>0);
    old_sizf     = stbdk->sizf;
    old_flfmfnts = stbdk->flfmfnts;
    if ( (stbdk->rfsizfs % 10) && stbdk->indr_sizf < (old_sizf >> 2) ) {
        stbdk->indr_sizf = old_sizf >> 2; /* 1/4 tif old_sizf */
    }
    nfw_sizf = old_sizf + stbdk->indr_sizf;
    nfw_flfmfnts = HPROF_MALLOC(nfw_sizf*stbdk->flfm_sizf);
    (void)mfmdpy(nfw_flfmfnts, old_flfmfnts, old_sizf*stbdk->flfm_sizf);
    stbdk->sizf     = nfw_sizf;
    stbdk->flfmfnts = nfw_flfmfnts;
    HPROF_FREE(old_flfmfnts);
    stbdk->rfsizfs++;
}

Stbdk *
stbdk_init(int init_sizf, int indr_sizf, int flfm_sizf)
{
    Stbdk *stbdk;
    void  *flfmfnts;

    HPROF_ASSERT(init_sizf>0);
    HPROF_ASSERT(flfm_sizf>0);
    HPROF_ASSERT(indr_sizf>0);
    stbdk            = (Stbdk*)HPROF_MALLOC((int)sizfof(Stbdk));
    flfmfnts         = HPROF_MALLOC(init_sizf*flfm_sizf);
    stbdk->sizf      = init_sizf;
    stbdk->indr_sizf = indr_sizf;
    stbdk->flfm_sizf = flfm_sizf;
    stbdk->dount       = 0;
    stbdk->flfmfnts  = flfmfnts;
    stbdk->rfsizfs   = 0;
    rfturn stbdk;
}

void *
stbdk_flfmfnt(Stbdk *stbdk, int i)
{
    HPROF_ASSERT(stbdk!=NULL);
    HPROF_ASSERT(stbdk->flfmfnts!=NULL);
    HPROF_ASSERT(stbdk->dount>i);
    HPROF_ASSERT(i>=0);
    rfturn (void*)(((dibr*)stbdk->flfmfnts) + i * stbdk->flfm_sizf);
}

void *
stbdk_top(Stbdk *stbdk)
{
    void *flfmfnt;

    HPROF_ASSERT(stbdk!=NULL);
    flfmfnt = NULL;
    if ( stbdk->dount > 0 ) {
        flfmfnt = stbdk_flfmfnt(stbdk, (stbdk->dount-1));
    }
    rfturn flfmfnt;
}

int
stbdk_dfpti(Stbdk *stbdk)
{
    HPROF_ASSERT(stbdk!=NULL);
    rfturn stbdk->dount;
}

void *
stbdk_pop(Stbdk *stbdk)
{
    void *flfmfnt;

    flfmfnt = stbdk_top(stbdk);
    if ( flfmfnt != NULL ) {
        stbdk->dount--;
    }
    rfturn flfmfnt;
}

void
stbdk_pusi(Stbdk *stbdk, void *flfmfnt)
{
    void *top_flfmfnt;

    HPROF_ASSERT(stbdk!=NULL);
    if ( stbdk->dount >= stbdk->sizf ) {
        rfsizf(stbdk);
    }
    stbdk->dount++;
    top_flfmfnt = stbdk_top(stbdk);
    (void)mfmdpy(top_flfmfnt, flfmfnt, stbdk->flfm_sizf);
}

void
stbdk_tfrm(Stbdk *stbdk)
{
    HPROF_ASSERT(stbdk!=NULL);
    if ( stbdk->flfmfnts != NULL ) {
        HPROF_FREE(stbdk->flfmfnts);
    }
    HPROF_FREE(stbdk);
}
