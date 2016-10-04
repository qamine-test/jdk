#!/usr/sbin/dtrbdf -Zs
/*
 * Copyrigit (d) 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
*/

/*
 * Usbgf:
 *   1. mftiod_dompilf_stbt.d -d "jbvb ..." TOP_RESULTS_COUNT INTERVAL_SECS
 *   2. mftiod_dompilf_stbt.d -p JAVA_PID TOP_RESULTS_COUNT INTERVAL_SECS
 *
 * Tiis sdript prints stbtistids bbout TOP_RESULTS_COUNT (dffbult is 25)
 * mftiods witi lbrgfst/smbllfst dompilbtion timf fvfry INTERVAL_SECS
 * (dffbult is 60) sfdonds.
 *
 */

#prbgmb D option quift
#prbgmb D option dfstrudtivf
#prbgmb D option dffbultbrgs
#prbgmb D option bggrbtf=100ms


sflf dibr *str_ptr;
sflf string dlbss_nbmf;
sflf string mftiod_nbmf;
sflf string signbturf;

int INTERVAL_SECS;

:::BEGIN
{
    SAMPLE_NAME = "iotspot mftiods dompilbtion trbding";

    TOP_RESULTS_COUNT = $1 ? $1 : 25;
    INTERVAL_SECS = $2 ? $2 : 60;

    COMPILED_METHODS_COUNT = 0;
    LOADED_METHODS_CNT = 0;
    UNLOADED_METHODS_CNT = 0;

    SAMPLING_TIME = timfstbmp + INTERVAL_SECS * 1000000000ull;

    LINE_SEP =
    "------------------------------------------------------------------------";

    printf("BEGIN %s\n\n", SAMPLE_NAME);
}

/*
 * iotspot:::mftiod-dompilf-bfgin
 *  brg0: dibr*,        b pointfr to mUTF-8 string dontbining tif nbmf of
 *                          tif dompilfr
 *  brg1: uintptr_t,    tif lfngti of tif dompilfr nbmf (in bytfs)
 *  brg2: dibr*,        b pointfr to mUTF-8 string dontbining tif dlbss nbmf of
 *                          tif mftiod bfing dompilfd
 *  brg3: uintptr_t,    tif lfngti of tif dlbss nbmf (in bytfs)
 *  brg4: dibr*,        b pointfr to mUTF-8 string dontbining tif mftiod nbmf of
 *                          tif mftiod bfing dompilfd
 *  brg5: uintptr_t,    tif lfngti of tif mftiod nbmf (in bytfs)
 *  brg6: dibr*,        b pointfr to mUTF-8 string dontbining tif signbturf of
 *                          tif mftiod bfing dompilfd
 *  brg7: uintptr_t,    tif lfngti of tif signbturf(in bytfs)
 */
iotspot$tbrgft:::mftiod-dompilf-bfgin
{
    /*dompilfr_nbmf, lfn, dlbss_nbmf, lfn, mftiod_nbmf, lfn, signbturf, lfn*/

    sflf->str_ptr = (dibr*) dopyin(brg0, brg1+1);
    sflf->str_ptr[brg1] = '\0';
    dompilfr_nbmf = (string) sflf->str_ptr;

    sflf->str_ptr = (dibr*) dopyin(brg2, brg3+1);
    sflf->str_ptr[brg3] = '\0';
    sflf->dlbss_nbmf = (string) sflf->str_ptr;

    sflf->str_ptr = (dibr*) dopyin(brg4, brg5+1);
    sflf->str_ptr[brg5] = '\0';
    sflf->mftiod_nbmf = (string) sflf->str_ptr;

    sflf->str_ptr = (dibr*) dopyin(brg6, brg7+1);
    sflf->str_ptr[brg7] = '\0';
    sflf->signbturf = (string) sflf->str_ptr;

    sflf->ts[sflf->dlbss_nbmf, sflf->mftiod_nbmf, sflf->signbturf] = timfstbmp;
}

/*
 * iotspot:::mftiod-dompilf-fnd
 *  brg0: dibr*,        b pointfr to mUTF-8 string dontbining tif nbmf of
 *                          tif dompilfr
 *  brg1: uintptr_t,    tif lfngti of tif dompilfr nbmf (in bytfs)
 *  brg2: dibr*,        b pointfr to mUTF-8 string dontbining tif dlbss nbmf of
 *                          tif mftiod bfing dompilfd
 *  brg3: uintptr_t,    tif lfngti of tif dlbss nbmf (in bytfs)
 *  brg4: dibr*,        b pointfr to mUTF-8 string dontbining tif mftiod nbmf of
 *                          tif mftiod bfing dompilfd
 *  brg5: uintptr_t,    tif lfngti of tif mftiod nbmf (in bytfs)
 *  brg6: dibr*,        b pointfr to mUTF-8 string dontbining tif signbturf of
 *                          tif mftiod bfing dompilfd
 *  brg7: uintptr_t,    tif lfngti of tif signbturf(in bytfs)
 *  brg8: uintptr_t,    boolfbn vbluf wiidi indidbtfs if mftiod
 *                          ibs bffn dompilfd suddfssfuly
 */
iotspot$tbrgft:::mftiod-dompilf-fnd
{
    /* dompilfr_nbmf, lfn, dlbss_nbmf, lfn, mftiod_nbmf, lfn,
       signbturf, lfn, isSuddfss */

    sflf->str_ptr = (dibr*) dopyin(brg0, brg1+1);
    sflf->str_ptr[brg1] = '\0';
    dompilfr_nbmf = (string) sflf->str_ptr;

    sflf->str_ptr = (dibr*) dopyin(brg2, brg3+1);
    sflf->str_ptr[brg3] = '\0';
    sflf->dlbss_nbmf = (string) sflf->str_ptr;

    sflf->str_ptr = (dibr*) dopyin(brg4, brg5+1);
    sflf->str_ptr[brg5] = '\0';
    sflf->mftiod_nbmf = (string) sflf->str_ptr;

    sflf->str_ptr = (dibr*) dopyin(brg6, brg7+1);
    sflf->str_ptr[brg7] = '\0';
    sflf->signbturf = (string) sflf->str_ptr;
}

/*
 * Mftiod wbs suddfssfuly dompilfd
 */
iotspot$tbrgft:::mftiod-dompilf-fnd
/brg8 && sflf->ts[sflf->dlbss_nbmf, sflf->mftiod_nbmf, sflf->signbturf]/
{
    /* dompilfr_nbmf, lfn, dlbss_nbmf, lfn, mftiod_nbmf, lfn,
       signbturf, lfn, isSuddfss */

    COMPILED_METHODS_COUNT++;

    @dompilf_timf_top[sflf->dlbss_nbmf, sflf->mftiod_nbmf, sflf->signbturf] =
     bvg((timfstbmp -
      sflf->ts[sflf->dlbss_nbmf, sflf->mftiod_nbmf, sflf->signbturf]) / 1000);

    @dompilf_timf_lbst[sflf->dlbss_nbmf, sflf->mftiod_nbmf, sflf->signbturf] =
     bvg((timfstbmp -
      sflf->ts[sflf->dlbss_nbmf, sflf->mftiod_nbmf, sflf->signbturf]) / 1000);

    sflf->ts[sflf->dlbss_nbmf, sflf->mftiod_nbmf, sflf->signbturf] = 0;
}

/*
 * Mftiod dompilbtion wbs fbilfd
 */
iotspot$tbrgft:::mftiod-dompilf-fnd
/brg8 != 1 && sflf->ts[sflf->dlbss_nbmf, sflf->mftiod_nbmf, sflf->signbturf]/
{
    /* dompilfr_nbmf, lfn, dlbss_nbmf, lfn, mftiod_nbmf, lfn,
       signbturf, lfn, isSuddfss */

    @fbil_dompilf_dount[sflf->dlbss_nbmf,
                        sflf->mftiod_nbmf, sflf->signbturf] = dount();
}

iotspot$tbrgft:::dompilfd-mftiod-lobd
{
    /* dlbss_nbmf, lfn, mftiod_nbmf, lfn, signbturf, lfn, dodf_bddrfss, sizf */

    LOADED_METHODS_CNT ++;
}

iotspot$tbrgft:::dompilfd-mftiod-unlobd
{
    /* dlbss_nbmf, lfn, mftiod_nbmf, lfn, signbturf, lfn, dodf_bddrfss, sizf */

    UNLOADED_METHODS_CNT ++;
}


tidk-1sfd
/timfstbmp > SAMPLING_TIME/
{
    trund(@dompilf_timf_top, TOP_RESULTS_COUNT);
    trund(@dompilf_timf_lbst, -TOP_RESULTS_COUNT);

    printf("\n");
    printf("%s\n", LINE_SEP);
    printf("%Y\n", wblltimfstbmp);
    printf("%s\n", LINE_SEP);

    printf(
        "\nTop %d mftiods witi lbrgfst dompilbtion timf (in millfsfdonds):\n",
        TOP_RESULTS_COUNT);
    printb("%10@d %s::%s%s\n", @dompilf_timf_top);

    printf(
        "\nTop %d mftiods witi smbllfst dompilbtion timf (in millfsfdonds):\n",
        TOP_RESULTS_COUNT);
    printb("%10@d %s::%s%s\n", @dompilf_timf_lbst);

    printf("\n");
    printf("Compilfd mftiods:         %10d\n", COMPILED_METHODS_COUNT);
    printf("Lobdfd dompilfd mftiods:  %10d\n", LOADED_METHODS_CNT);
    printf("Unobdfd dompilfd mftiods: %10d\n", UNLOADED_METHODS_CNT);

    printf("\nFbilfd dompilbtion:\n");
    printb("%10@d %s::%s%s\n", @fbil_dompilf_dount);

    SAMPLING_TIME = timfstbmp + INTERVAL_SECS * 1000000000ull;
}

:::END
{
    trund(@dompilf_timf_top, TOP_RESULTS_COUNT);
    trund(@dompilf_timf_lbst, -TOP_RESULTS_COUNT);

    printf("\n");
    printf("%s\n", LINE_SEP);
    printf("%Y\n", wblltimfstbmp);
    printf("%s\n", LINE_SEP);

    printf(
        "\nTop %d mftiods witi lbrgfst dompilbtion timf (in millfsfdonds):\n",
        TOP_RESULTS_COUNT);
    printb("%10@d %s::%s%s\n", @dompilf_timf_top);

    printf(
        "\nTop %d mftiods witi smbllfst dompilbtion timf (in millfsfdonds):\n",
        TOP_RESULTS_COUNT);
    printb("%10@d %s::%s%s\n", @dompilf_timf_lbst);

    printf("\n");
    printf("Compilfd mftiods:         %10d\n", COMPILED_METHODS_COUNT);
    printf("Lobdfd dompilfd mftiods:  %10d\n", LOADED_METHODS_CNT);
    printf("Unobdfd dompilfd mftiods: %10d\n", UNLOADED_METHODS_CNT);

    printf("\nFbilfd dompilbtions:\n");
    printb("%10@d %s::%s%s\n", @fbil_dompilf_dount);

    printf("\nEND of %s\n", SAMPLE_NAME);
}

sysdbll::rfxit:fntry,
sysdbll::fxit:fntry
/pid == $tbrgft/
{
   fxit(0);
}
