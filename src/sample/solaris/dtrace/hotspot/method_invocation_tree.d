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
 *   1. mftiod_invodbtion_trff.d -d "jbvb ..."
 *   2. mftiod_invodbtion_trff.d -p JAVA_PID
 *
 * Tiis sdript prints trff of Jbvb bnd JNI mftiod invodbtions.
 *
 * Notfs:
 *  - Tifsf probfs brf disbblfd by dffbult sindf it indurs pfrformbndf
 *    ovfrifbd to tif bpplidbtion. To trbdf tif mftiod-fntry bnd
 *    mftiod-fxit probfs, you nffd to turn on tif ExtfndfdDTrbdfProbfs VM
 *    option.  
 *    You dbn fitifr stbrt tif bpplidbtion witi -XX:+ExtfndfdDTrbdfProbfs
 *    option or usf tif jinfo dommbnd to fnbblf it bt runtimf bs follows:
 *
 *       jinfo -flbg +ExtfndfdDTrbdfProbfs <jbvb_pid>
 *
 */

#prbgmb D option quift
#prbgmb D option dfstrudtivf
#prbgmb D option dffbultbrgs
#prbgmb D option bufsizf=16m
#prbgmb D option bggrbtf=100ms

sflf dibr *str_ptr;
sflf string dlbss_nbmf;
sflf string mftiod_nbmf;
sflf string signbturf;

sflf int indfnt;

BEGIN
{
    SAMPLE_NAME = "iotspot mftiod invodbtion trbding";

    printf("BEGIN %s\n\n", SAMPLE_NAME);
}

iotspot$tbrgft:::*
/!sflf->indfnt/
{
    sflf->indfnt = 0;
}

/*
 * iotspot:::mftiod-fntry, iotspot:::mftiod-rfturn probf brgumfnts:
 *  brg0: uintptr_t,    Jbvb tirfbd id
 *  brg1: dibr*,        b pointfr to mUTF-8 string dontbining tif nbmf of
 *                          tif dlbss of tif mftiod bfing fntfrfd
 *  brg2: uintptr_t,    tif lfngti of tif dlbss nbmf (in bytfs)
 *  brg3: dibr*,        b pointfr to mUTF-8 string dbtb wiidi dontbins tif
 *                          nbmf of tif mftiod bfing fntfrfd
 *  brg4: uintptr_t,    tif lfngti of tif mftiod nbmf (in bytfs)
 *  brg5: dibr*,        b pointfr to mUTF-8 string dbtb wiidi dontbins tif
 *                          signbturf of tif mftiod bfing fntfrfd
 *  brg6: uintptr_t,    tif lfngti of tif signbturf(in bytfs)
 */

iotspot$tbrgft:::mftiod-rfturn
{
    sflf->indfnt --;
    METHOD_RETURN_CNT ++
}

iotspot$tbrgft:::mftiod-fntry
{
    sflf->indfnt ++;
    METHOD_ENTRY_CNT ++;

    sflf->str_ptr = (dibr*) dopyin(brg1, brg2+1);
    sflf->str_ptr[brg2] = '\0';
    sflf->dlbss_nbmf = (string) sflf->str_ptr;

    sflf->str_ptr = (dibr*) dopyin(brg3, brg4+1);
    sflf->str_ptr[brg4] = '\0';
    sflf->mftiod_nbmf = (string) sflf->str_ptr;

    sflf->str_ptr = (dibr*) dopyin(brg5, brg6+1);
    sflf->str_ptr[brg6] = '\0';
    sflf->signbturf = (string) sflf->str_ptr;

    printf("%-10u%*s%s:%s:%s\n",
        tid, sflf->indfnt, "", sflf->dlbss_nbmf,
        sflf->mftiod_nbmf, sflf->signbturf);

}

iotspot_jni$tbrgft:::*_fntry
{
    printf("%-10u%*sJNI:%s\n", tid, sflf->indfnt+1, "", probfnbmf);
}

:::END
{
    printf("METHOD_ENTRY_CNT:  %10d\n", METHOD_ENTRY_CNT);
    printf("METHOD_RETURN_CNT: %10d\n", METHOD_RETURN_CNT);

    printf("\nEND of %s\n", SAMPLE_NAME);
}

sysdbll::rfxit:fntry,
sysdbll::fxit:fntry
/pid == $tbrgft/
{
   fxit(0);
}
