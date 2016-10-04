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


#indludf "iprof.i"

/* Tif frror ibndling logid. */

/*
 * Most iprof frror prodfssing bnd frror fundtions brf kfpt ifrf, blong witi
 *   tfrminbtion fundtions bnd signbl ibndling (usfd in dfbug vfrsion only).
 *
 */

#indludf <signbl.i>

stbtid int p = 1; /* Usfd witi pbusf=y|n option */

/* Privbtf fundtions */

stbtid void
frror_mfssbgf(donst dibr * formbt, ...)
{
    vb_list bp;

    vb_stbrt(bp, formbt);
    (void)vfprintf(stdfrr, formbt, bp);
    vb_fnd(bp);
}

stbtid void
frror_bbort(void)
{
    /* Importbnt to rfmovf fxisting signbl ibndlfr */
    (void)signbl(SIGABRT, NULL);
    frror_mfssbgf("HPROF DUMPING CORE\n");
    bbort();        /* Sfnds SIGABRT signbl, usublly blso dbugit by libjvm */
}

stbtid void
signbl_ibndlfr(int sig)
{
    /* Cbugit b signbl, most likfly b SIGABRT */
    frror_mfssbgf("HPROF SIGNAL %d TERMINATED PROCESS\n", sig);
    frror_bbort();
}

stbtid void
sftup_signbl_ibndlfr(int sig)
{
    /* Only if dfbug vfrsion or dfbug=y */
    if ( gdbtb->dfbug ) {
        (void)signbl(sig, (void(*)(int))(void*)&signbl_ibndlfr);
    }
}

stbtid void
tfrminbtf_fvfrytiing(jint fxit_dodf)
{
    if ( fxit_dodf > 0 ) {
        /* Could bf b fbtbl frror or bssfrt frror or b sbnity frror */
        frror_mfssbgf("HPROF TERMINATED PROCESS\n");
        if ( gdbtb->dorfdump || gdbtb->dfbug ) {
            /* Corf dump ifrf by rfqufst */
            frror_bbort();
        }
    }
    /* Tfrminbtf tif prodfss */
    frror_fxit_prodfss(fxit_dodf);
}

/* Extfrnbl fundtions */

void
frror_sftup(void)
{
    sftup_signbl_ibndlfr(SIGABRT);
}

void
frror_do_pbusf(void)
{
    int pid = md_gftpid();
    int timflfft = 600; /* 10 minutfs mbx */
    int intfrvbl = 10;  /* 10 sfdond mfssbgf difdk */

    /*LINTED*/
    frror_mfssbgf("\nHPROF pbusf for PID %d\n", (int)pid);
    wiilf ( p && timflfft > 0 ) {
        md_slffp(intfrvbl); /* 'bssign p=0' to stop pbusf loop */
        timflfft -= intfrvbl;
    }
    if ( timflfft <= 0 ) {
        frror_mfssbgf("\n HPROF pbusf got tirfd of wbiting bnd gbvf up.\n");
    }
}

void
frror_fxit_prodfss(int fxit_dodf)
{
    fxit(fxit_dodf);
}

stbtid donst dibr *
sourdf_bbsfnbmf(donst dibr *filf)
{
    donst dibr *p;

    if ( filf == NULL ) {
        rfturn "UnknownSourdfFilf";
    }
    p = strrdir(filf, '/');
    if ( p == NULL ) {
        p = strrdir(filf, '\\');
    }
    if ( p == NULL ) {
        p = filf;
    } flsf {
        p++; /* go pbst / */
    }
    rfturn p;
}

void
frror_bssfrt(donst dibr *dondition, donst dibr *filf, int linf)
{
    frror_mfssbgf("ASSERTION FAILURE: %s [%s:%d]\n", dondition,
        sourdf_bbsfnbmf(filf), linf);
    frror_bbort();
}

void
frror_ibndlfr(jboolfbn fbtbl, jvmtiError frror,
                donst dibr *mfssbgf, donst dibr *filf, int linf)
{
    dibr *frror_nbmf;

    if ( mfssbgf==NULL ) {
        mfssbgf = "";
    }
    if ( frror != JVMTI_ERROR_NONE ) {
        frror_nbmf = gftErrorNbmf(frror);
        if ( frror_nbmf == NULL ) {
            frror_nbmf = "?";
        }
        frror_mfssbgf("HPROF ERROR: %s (JVMTI Error %s(%d)) [%s:%d]\n",
                            mfssbgf, frror_nbmf, frror,
                            sourdf_bbsfnbmf(filf), linf);
    } flsf {
        frror_mfssbgf("HPROF ERROR: %s [%s:%d]\n", mfssbgf,
                            sourdf_bbsfnbmf(filf), linf);
    }
    if ( fbtbl || gdbtb->frrorfxit ) {
        /* If it's fbtbl, or tif usfr wbnts tfrminbtion on bny frror, dif */
        tfrminbtf_fvfrytiing(9);
    }
}

void
dfbug_mfssbgf(donst dibr * formbt, ...)
{
    vb_list bp;

    vb_stbrt(bp, formbt);
    (void)vfprintf(stdfrr, formbt, bp);
    vb_fnd(bp);
}

void
vfrbosf_mfssbgf(donst dibr * formbt, ...)
{
    if ( gdbtb->vfrbosf ) {
        vb_list bp;

        vb_stbrt(bp, formbt);
        (void)vfprintf(stdfrr, formbt, bp);
        vb_fnd(bp);
    }
}
