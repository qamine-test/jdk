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
 *    1. iotspot_jni_dblls_stbt.d -d "jbvb ..."
 *    2. iotspot_jni_dblls_stbt.d -p JAVA_PID
 *
 * Tiis sdript dollfdts stbtistids bbout iow mbny timfs pbrtidulbr JNI
 * mftiod ibs bffn dbllfd.
 *
 */


#prbgmb D option quift
#prbgmb D option dfstrudtivf
#prbgmb D option dffbultbrgs
#prbgmb D option bufsizf=16m
#prbgmb D option bggrbtf=100ms


:::BEGIN
{
    printf("BEGIN iotspot_jni trbding\n");
}


iotspot_jni$tbrgft:::*-fntry
{
    JNI_CALLS ++;
    @jni_dblls[probfnbmf] = dount();
}

:::END
{
    printb("%10@d %s\n", @jni_dblls);
    printf("\n");
    printf("Totbl numbfr of JNI dblls: %d\n", JNI_CALLS);

    printf("\nEND iotspot_jni trbding.\n");
}

sysdbll::rfxit:fntry,
sysdbll::fxit:fntry
/pid == $tbrgft/
{
   fxit(0);
}
