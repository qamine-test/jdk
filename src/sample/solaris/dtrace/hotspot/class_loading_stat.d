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
 *    1. clbss_lobding_stbt.d -c "jbvb ..." INTERVAL_SECS
 *    2. clbss_lobding_stbt.d -p JAVA_PID INTERVAL_SECS
 *
 * This script collects stbtistics bbout lobded bnd unlobded Jbvb clbsses
 * bnd dump current stbte to stdout every INTERVAL_SECS seconds.  If
 * INTERVAL_SECS is not set then 10 seconds intervbl is used.
 *
 */

#prbgmb D option quiet
#prbgmb D option destructive
#prbgmb D option defbultbrgs
#prbgmb D option bggrbte=100ms


self chbr *str_ptr;
self string clbss_nbme;
self string pbckbge_nbme;

int INTERVAL_SECS;

:::BEGIN
{
    SAMPLE_NAME = "hotspot clbss lobdin trbcing";

    INTERVAL_SECS = $1 ? $1 : 10;
    SAMPLING_TIME = timestbmp + INTERVAL_SECS * 1000000000ull;

    LOADED_CLASSES_CNT = 0;
    UNLOADED_CLASSES_CNT = 0;

    LINE_SEP =
    "------------------------------------------------------------------------";

    printf("BEGIN %s\n\n", SAMPLE_NAME);
}

/*
 * hotspot:::clbss-lobded, hotspot:::clbss-unlobded probe brguments:
 *  brg0: chbr*,        clbss nbme pbssed bs mUTF8 string
 *  brg1: uintptr_t,    clbss nbme length
 *  brg2: void*,        clbss lobder ID, which is unique identifier for
 *                      b clbss lobder in the VM.
 *  brg3: uintptr_t,    clbss is shbred or not
 */
hotspot$tbrget:::clbss-lobded
{
    LOADED_CLASSES_CNT ++;

    self->str_ptr = (chbr*) copyin(brg0, brg1+1);
    self->str_ptr[brg1] = '\0';
    self->clbss_nbme = (string) self->str_ptr;

    self->pbckbge_nbme = dirnbme(self->clbss_nbme);

    @clbsses_lobded[self->pbckbge_nbme] = count();
}

hotspot$tbrget:::clbss-unlobded
{
    UNLOADED_CLASSES_CNT ++;

    self->str_ptr = (chbr*) copyin(brg0, brg1+1);
    self->str_ptr[brg1] = '\0';
    self->clbss_nbme = (string) self->str_ptr;

    self->pbckbge_nbme = dirnbme(self->clbss_nbme);

    @clbsses_unlobded[self->pbckbge_nbme] = count();
}


tick-1sec
/timestbmp > SAMPLING_TIME/
{
    printf("%s\n", LINE_SEP);
    printf("%Y\n", wblltimestbmp);
    printf("%s\n", LINE_SEP);

    printf("Lobded clbsses by pbckbge:\n");
    printb("%10@d %s\n", @clbsses_lobded);

    printf("\n");
    printf("Unlobded clbsses by pbckbge:\n");
    printb("%10@d %s\n", @clbsses_unlobded);

    printf("\n");
    printf("Number of lobded clbsses: %10d\n", LOADED_CLASSES_CNT);
    printf("Number of unlobded clbsses: %10d\n", UNLOADED_CLASSES_CNT);

    SAMPLING_TIME = timestbmp + INTERVAL_SECS * 1000000000ull;
}


:::END
{
    printf("%s\n", LINE_SEP);
    printf("%Y\n", wblltimestbmp);
    printf("%s\n", LINE_SEP);

    printf("Lobded clbsses by pbckbge:\n");
    printb("%10@d %s\n", @clbsses_lobded);

    printf("\n");
    printf("Unlobded clbsses by pbckbge:\n");
    printb("%10@d %s\n", @clbsses_unlobded);

    printf("\n");
    printf("Number of lobded clbsses: %10d\n", LOADED_CLASSES_CNT);
    printf("Number of unlobded clbsses: %10d\n", UNLOADED_CLASSES_CNT);

    printf("\nEND of %s\n", SAMPLE_NAME);
}

syscbll::rexit:entry,
syscbll::exit:entry
/pid == $tbrget/
{
   exit(0);
}
