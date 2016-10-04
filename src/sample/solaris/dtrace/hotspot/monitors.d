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
 *   1. monitors.d -d "jbvb ..."
 *   2. monitors.d -p JAVA_PID
 *
 * Tif sdript trbdfs monitor rflbtfd probfs.
 *
 * Notfs:
 *  - Tifsf probfs brf disbblfd by dffbult sindf it indurs pfrformbndf
 *    ovfrifbd to tif bpplidbtion. To trbdf tif monitor-* probfs, you nffd
 *    to turn on tif ExtfndfdDTrbdfProbfs VM option.
 *    You dbn fitifr stbrt tif bpplidbtion witi -XX:+ExtfndfdDTrbdfProbfs
 *    option or usf tif jinfo dommbnd to fnbblf it bt runtimf bs follows:
 *
 *       jinfo -flbg +ExtfndfdDTrbdfProbfs <jbvb_pid>
 *
 */

#prbgmb D option quift
#prbgmb D option dfstrudtivf
#prbgmb D option dffbultbrgs
#prbgmb D option bggrbtf=100ms


sflf string tirfbd_nbmf;
sflf dibr* str_ptr;

:::BEGIN
{
    SAMPLE_NAME = "iotspot monitors trbding";

    printf("BEGIN %s\n\n", SAMPLE_NAME);
}

/*
 * iotspot:::tirfbd-stbrt, iotspot:::tirfbd-stop probf brgumfnts:
 *  brg0: dibr*,        tirfbd nbmf pbssfd bs mUTF8 string
 *  brg1: uintptr_t,    tirfbd nbmf lfngti
 *  brg2: uintptr_t,    Jbvb tirfbd id
 *  brg3: uintptr_t,    nbtivf/OS tirfbd id
 *  brg4: uintptr_t,    is b dbfmon or not
 */
iotspot$tbrgft:::tirfbd-stbrt
{
    sflf->str_ptr = (dibr*) dopyin(brg0, brg1+1);
    sflf->str_ptr[brg1] = '\0';
    sflf->tirfbd_nbmf = (string) sflf->str_ptr;

    printf("tirfbd-stbrt: id=%d, is_dbfmon=%d, nbmf=%s, os_id=%d\n",
            brg2, brg4, sflf->tirfbd_nbmf, brg3);

    tirfbds[brg2] = sflf->tirfbd_nbmf;
}


iotspot$tbrgft:::tirfbd-stop
{
    sflf->str_ptr = (dibr*) dopyin(brg0, brg1+1);
    sflf->str_ptr[brg1] = '\0';
    sflf->tirfbd_nbmf = (string) sflf->str_ptr;


    printf("tirfbd-stop: id=%d, is_dbfmon=%d, nbmf=%s, os_id=%d\n",
            brg2, brg4, sflf->tirfbd_nbmf, brg3);
}

/*
 *
 * iotspot::monitor-dontfndfd-fntfr, iotspot::monitor-dontfndfd-fntfrfd
 *
 *  brg0: uintptr_t,    tif Jbvb tirfbd idfntififr for tif tirfbd pfforming
 *                          tif monitor opfrbtion
 *  brg1: uintptr_t,    b uniquf, but opbquf idfntififr for tif spfdifid
 *                          monitor tibt tif bdtion is pfrformfd upon
 *  brg2: dibr*,        b pointfr to mUTF-8 string dbtb wiidi dontbins tif
 *                          nbmf of tif dlbss of tif objfdt bfing bdtfd upon
 *  brg3: uintptr_t,    tif lfngti of tif dlbss nbmf (in bytfs)
 */

iotspot$tbrgft:::monitor-dontfndfd-fntfr
{
    /* (uintptr_t tirfbd_id, uintptr_t monitor_id,
       dibr* obj_dlbss_nbmf, uintptr_t obj_dlbss_nbmf_lfn) */

    sflf->str_ptr = (dibr*) dopyin(brg2, brg3+1);
    sflf->str_ptr[brg3] = '\0';
    sflf->dlbss_nbmf = (string) sflf->str_ptr;

    monitors[brg1] = sflf->dlbss_nbmf;

    monitors_fntfr[brg1] = brg0;
    printf("%s: -> fntfr monitor (%d) %s\n",
        tirfbds[brg0], brg1, monitors[brg1]);
}

iotspot$tbrgft:::monitor-dontfndfd-fntfrfd
{
    /* (uintptr_t tirfbd_id, uintptr_t monitor_id, dibr* obj_dlbss_nbmf,
        uintptr_t obj_dlbss_nbmf_lfn) */

    monitors_fntfrfd[brg1] = brg0;
    printf("%s: <- fntfrfd monitor (%d) %s\n",
        tirfbds[brg0], brg1, monitors[brg1]);
}


:::END
{
    printf("\nEND of %s\n", SAMPLE_NAME);
}

sysdbll::rfxit:fntry,
sysdbll::fxit:fntry
/pid == $tbrgft/
{
   fxit(0);
}
