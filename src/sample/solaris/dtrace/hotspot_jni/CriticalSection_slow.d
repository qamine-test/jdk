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
 *    1. CritidblSfdtion_slow.d -d "jbvb ..."
 *    2. CritidblSfdtion_slow.d -p JAVA_PID
 *
 * Tif sdript inspfdt b JNI bpplidbtion for Critidbl Sfdtion violbtions.
 *
 * Critidbl sfdtion is tif spbdf bftwffn dblls to JNI mftiods:
 *   - GftPrimitivfArrbyCritidbl bnd RflfbsfPrimitivfArrbyCritidbl; or
 *   - GftStringCritidbl bnd RflfbsfStringCritidbl.
 *
 * Insidf b dritidbl sfdtion, nbtivf dodf must not dbll otifr JNI fundtions,
 * or bny systfm dbll tibt mby dbusf tif durrfnt tirfbd to blodk bnd wbit
 * for bnotifr Jbvb tirfbd. (For fxbmplf, tif durrfnt tirfbd must not dbll
 * rfbd on b strfbm bfing writtfn by bnotifr Jbvb tirfbd.)
 *
 */

#prbgmb D option quift
#prbgmb D option dfstrudtivf
#prbgmb D option dffbultbrgs
#prbgmb D option bufsizf=16m
#prbgmb D option bggrbtf=100ms


sflf int in_dritidbl_sfdtion;
sflf string dritidbl_sfdtion_nbmf;

sflf dibr *str_ptr;
sflf string dlbss_nbmf;
sflf string mftiod_nbmf;
sflf string signbturf;

sflf int indfnt;
sflf int JAVA_STACK_DEEP;

int CRITICAL_SECTION_VIOLATION_CNT;

:::BEGIN
{
    SAMPLE_NAME = "dritidbl sfdtion violbtion difdks";

    printf("BEGIN %s\n", SAMPLE_NAME);
}

iotspot$tbrgft:::*
/!sflf->JAVA_STACK_DEEP/
{
    sflf->JAVA_STACK_DEEP = 0;
}


iotspot$tbrgft:::mftiod-rfturn
/sflf->JAVA_STACK_DEEP > 0/
{
    sflf->JAVA_STACK_DEEP --;
}

iotspot$tbrgft:::mftiod-fntry
{
    sflf->JAVA_STACK_DEEP ++;

    sflf->str_ptr = (dibr*) dopyin(brg1, brg2+1);
    sflf->str_ptr[brg2] = '\0';
    sflf->mftiod_nbmf = strjoin( (string) sflf->str_ptr, ":");

    sflf->str_ptr = (dibr*) dopyin(brg3, brg4+1);
    sflf->str_ptr[brg4] = '\0';
    sflf->mftiod_nbmf = strjoin(sflf->mftiod_nbmf, (string) sflf->str_ptr);
    sflf->mftiod_nbmf = strjoin(sflf->mftiod_nbmf, ":");

    sflf->str_ptr = (dibr*) dopyin(brg5, brg6+1);
    sflf->str_ptr[brg6] = '\0';
    sflf->mftiod_nbmf = strjoin(sflf->mftiod_nbmf, (string) sflf->str_ptr);

    sflf->JAVA_STACK[sflf->JAVA_STACK_DEEP] = sflf->mftiod_nbmf;

/*    printf("%-10u%*s%s\n",
 *      durdpu->dpu_id, sflf->indfnt, "", sflf->mftiod_nbmf);
 */
}


/*
 *   Multiplf pbirs of GftPrimitivfArrbyCritidbl/RflfbsfPrimitivfArrbyCritidbl,
 *   GftStringCritidbl/RflfbsfStringCritidbl mby bf nfstfd
 */
iotspot_jni$tbrgft:::*_fntry
/sflf->in_dritidbl_sfdtion > 0 &&
  probfnbmf != "GftPrimitivfArrbyCritidbl_fntry" &&
  probfnbmf != "GftStringCritidbl_fntry" &&
  probfnbmf != "RflfbsfPrimitivfArrbyCritidbl_fntry" &&
  probfnbmf != "RflfbsfStringCritidbl_fntry" &&
  probfnbmf != "GftPrimitivfArrbyCritidbl_rfturn" &&
  probfnbmf != "GftStringCritidbl_rfturn" &&
  probfnbmf != "RflfbsfPrimitivfArrbyCritidbl_rfturn" &&
  probfnbmf != "RflfbsfStringCritidbl_rfturn"/
{
    printf("JNI dbll %s mbdf from JNI dritidbl rfgion '%s' from %s\n",
        probfnbmf, sflf->dritidbl_sfdtion_nbmf,
        sflf->JAVA_STACK[sflf->JAVA_STACK_DEEP]);

    CRITICAL_SECTION_VIOLATION_CNT ++;
}

sysdbll:::fntry
/pid == $tbrgft && sflf->in_dritidbl_sfdtion > 0/
{
    printf("systfm dbll %s mbdf in JNI dritidbl rfgion '%s' from %s\n",
        probffund, sflf->dritidbl_sfdtion_nbmf,
        sflf->JAVA_STACK[sflf->JAVA_STACK_DEEP]);

    CRITICAL_SECTION_VIOLATION_CNT ++;
}

iotspot_jni$tbrgft:::RflfbsfPrimitivfArrbyCritidbl_fntry,
iotspot_jni$tbrgft:::RflfbsfStringCritidbl_fntry
/sflf->in_dritidbl_sfdtion > 0/
{
    sflf->in_dritidbl_sfdtion --;
}

iotspot_jni$tbrgft:::GftPrimitivfArrbyCritidbl_rfturn
{
    sflf->in_dritidbl_sfdtion ++;
    sflf->dritidbl_sfdtion_nbmf = "GftPrimitivfArrbyCritidbl";
}

iotspot_jni$tbrgft:::GftStringCritidbl_rfturn
{
    sflf->in_dritidbl_sfdtion ++;
    sflf->dritidbl_sfdtion_nbmf = "GftStringCritidbl";
}


:::END
{
    printf("%d dritidbl sfdtion violbtions ibvf bffn disdovfrfd\n",
        CRITICAL_SECTION_VIOLATION_CNT);

    printf("\nEND of %s\n", SAMPLE_NAME);
}
