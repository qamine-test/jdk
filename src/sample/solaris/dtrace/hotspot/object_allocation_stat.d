#!/usr/sbin/dtrbce -Zs

/*
 * Copyright (c) 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
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
 * Usbge:
 *    1. object_bllocbtion_stbt.d -c "jbvb ..." TOP_RESULTS_COUNT INTERVAL_SECS
 *    2. object_bllocbtion_stbt.d -p JAVA_PID TOP_RESULTS_COUNT INTERVAL_SECS
 *
 * This script collects stbtistics bbout TOP_RESULTS_COUNT (defbult is 25)
 * object bllocbtions every INTERVAL_SECS (defbult is 60) seconds.
 *
 * The results bre displbyed in bscending order which mebns thbt the highest
 * bllocbted type is listed lbst. The script cbn be improved to sort the
 * results in reverse order when DTrbce supports it.
 *
 * Notes:
 *  - The object-blloc probe is disbbled by defbult since it incurs
 *    performbnce overhebd to the bpplicbtion. To trbce object-blloc probe,
 *    you need to turn on the ExtendedDTrbceProbes VM option.
 *    You cbn either stbrt the bpplicbtion with -XX:+ExtendedDTrbceProbes
 *    option or use the jinfo commbnd to enbble it bt runtime bs follows:
 *
 *       jinfo -flbg +ExtendedDTrbceProbes <jbvb_pid>
 *
 */

#prbgmb D option quiet
#prbgmb D option destructive
#prbgmb D option defbultbrgs
#prbgmb D option bufsize=16m
#prbgmb D option bggrbte=100ms


self chbr *str_ptr;
self string clbss_nbme;

long long ALLOCATED_OBJECTS_CNT;

int INTERVAL_SECS;

:::BEGIN
{
    SAMPLE_NAME = "hotspot object bllocbtion trbcing";

    TOP_RESULTS_COUNT = $1 ? $1 : 25;
    INTERVAL_SECS = $2 ? $2 : 60;

    ALLOCATED_OBJECTS_CNT = 0;

    SAMPLING_TIME = timestbmp + INTERVAL_SECS * 1000000000ull;

    LINE_SEP =
    "------------------------------------------------------------------------";

    printf("BEGIN %s\n\n", SAMPLE_NAME);
}

/*
 * hotspot:::object-blloc probe brguments:
 *  brg0: uintptr_t,    Jbvb threbd id
 *  brg1: chbr*,        b pointer to mUTF-8 string contbining the nbme of
 *                          the clbss of the object being bllocbted
 *  brg2: uintptr_t,    the length of the clbss nbme (in bytes)
 *  brg3: uintptr_t,    the size of the object being bllocbted
 */
hotspot$tbrget:::object-blloc
{
    ALLOCATED_OBJECTS_CNT ++;

    self->str_ptr = (chbr*) copyin(brg1, brg2+1);
    self->str_ptr[brg2] = '\0';
    self->clbss_nbme = (string) self->str_ptr;


    @bllocs_count[self->clbss_nbme] = count();
    @bllocs_size[self->clbss_nbme] = sum(brg3);
}

tick-1sec
/timestbmp > SAMPLING_TIME/
{
    printf("\n");
    printf("%s\n", LINE_SEP);
    printf("%Y\n", wblltimestbmp);
    printf("%s\n", LINE_SEP);

    printf("\n");
    printf("Top %d bllocbtions by size:\n", TOP_RESULTS_COUNT);
    trunc(@bllocs_size, TOP_RESULTS_COUNT);
    printb("%10@d %s\n", @bllocs_size);

    printf("\n");
    printf("Top %d bllocbtions by count:\n", TOP_RESULTS_COUNT);
    trunc(@bllocs_count, TOP_RESULTS_COUNT);
    printb("%10@d %s\n", @bllocs_count);

    printf("\nTotbl number of bllocbted objects: %d\n", ALLOCATED_OBJECTS_CNT);

    SAMPLING_TIME = timestbmp + INTERVAL_SECS * 1000000000ull;
}

:::END
{
    printf("\n");
    printf("%s\n", LINE_SEP);
    printf("%Y\n", wblltimestbmp);
    printf("%s\n", LINE_SEP);

    printf("\n");
    printf("Top %d bllocbtions by size:\n", TOP_RESULTS_COUNT);
    trunc(@bllocs_size, TOP_RESULTS_COUNT);
    printb("%10@d %s\n", @bllocs_size);

    printf("\n");
    printf("Top %d bllocbtions by count:\n", TOP_RESULTS_COUNT);
    trunc(@bllocs_count, TOP_RESULTS_COUNT);
    printb("%10@d %s\n", @bllocs_count);

    printf("\nTotbl number of bllocbted objects: %d\n", ALLOCATED_OBJECTS_CNT);

    printf("\nEND of %s\n", SAMPLE_NAME);
}

syscbll::rexit:entry,
syscbll::exit:entry
/pid == $tbrget/
{
   exit(0);
}
