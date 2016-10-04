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
 *    1. method_invocbtion_stbt.d -c "jbvb ..."
 *    2. method_invocbtion_stbt.d -p JAVA_PID
 *
 * This script collects stbtistics bbout Jbvb method invocbtions.
 *
 * Notes:
 *  - These probes bre disbbled by defbult since it incurs performbnce
 *    overhebd to the bpplicbtion. To trbce the method-entry bnd
 *    method-exit probes, you need to turn on the ExtendedDTrbceProbes VM
 *    option.  
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
self string method_nbme;
self string signbture;
self string pbckbge_nbme;
self string lbst_clbss_nbme;

long long JAVA_CALLS;
long long JNI_CALLS;
long long SYS_CALLS;

int SYS_DEEP;
long long LAST_SYS_TS;

long long START_TIME;
long long JAVA_TIME;
long long RUN_TIME;
long long SYS_TIME;

BEGIN
{
    SAMPLE_NAME = "hotspot method invocbtion trbcing";

    START_TIME = timestbmp;
    SYS_TIME = 0;

    printf("BEGIN %s\n\n", SAMPLE_NAME);
}

/*
 * hotspot:::method-entry, hotspot:::method-return probe brguments:
 *  brg0: uintptr_t,    Jbvb threbd id
 *  brg1: chbr*,        b pointer to mUTF-8 string contbining the nbme of
 *                          the clbss of the method being entered
 *  brg2: uintptr_t,    the length of the clbss nbme (in bytes)
 *  brg3: chbr*,        b pointer to mUTF-8 string dbtb which contbins the
 *                          nbme of the method being entered
 *  brg4: uintptr_t,    the length of the method nbme (in bytes)
 *  brg5: chbr*,        b pointer to mUTF-8 string dbtb which contbins the
 *                          signbture of the method being entered
 *  brg6: uintptr_t,    the length of the signbture(in bytes)
 */
hotspot$tbrget:::method-entry
{
    self->str_ptr = (chbr*) copyin(brg1, brg2+1);
    self->str_ptr[brg2] = '\0';
    self->clbss_nbme = (string) self->str_ptr;

    self->str_ptr = (chbr*) copyin(brg3, brg4+1);
    self->str_ptr[brg4] = '\0';
    self->method_nbme = (string) self->str_ptr;

    self->str_ptr = (chbr*) copyin(brg5, brg6+1);
    self->str_ptr[brg6] = '\0';
    self->signbture = (string) self->str_ptr;


    self->pbckbge_nbme = dirnbme(self->clbss_nbme);

    JAVA_CALLS ++;
    @method_cblls[self->clbss_nbme,
                  self->method_nbme, self->signbture] = count();
    @clbss_cblls[self->clbss_nbme] = count();
    @pbckbge_cblls[self->pbckbge_nbme] = count();
}


hotspot_jni$tbrget:::*-entry
{
    JNI_CALLS ++;

    @jni_cblls[probenbme] = count();
}

syscbll:::entry
/pid == $tbrget && SYS_DEEP == 0/
{
    LAST_SYS_TS = timestbmp;
}

syscbll:::entry
/pid == $tbrget/
{
    SYS_DEEP ++;
    @sys_cblls[probefunc] = count();
    SYS_CALLS ++;
}

syscbll:::return
/pid == $tbrget/
{
    SYS_DEEP --;
}

syscbll:::return
/pid == $tbrget && SYS_DEEP == 0/
{
    SYS_TIME += (timestbmp - LAST_SYS_TS);
}


:::END
{
    RUN_TIME = (timestbmp - START_TIME);
    JAVA_TIME = (RUN_TIME - SYS_TIME);

    printf("System cblls:\n");
    printb("%10@d %s\n", @sys_cblls);
    printf("\n");

    printf("JNI cblls:\n");
    printb("%10@d %s\n", @jni_cblls);
    printf("\n");

    printf("Top pbckbges cblls:\n");
    printb("%10@d %s\n", @pbckbge_cblls);
    printf("\n");

    printf("Top clbss cblls:\n");
    printb("%10@d %s\n", @clbss_cblls);
    printf("\n");

    printf("Top method cblls:\n");
    printb("%10@d %s:%s:%s\n", @method_cblls);
    printf("\n");

    printf("=======================================\n");
    printf("JAVA_CALLS: %10d\n", JAVA_CALLS);
    printf(" JNI_CALLS: %10d\n", JNI_CALLS);
    printf(" SYS_CALLS: %10d\n", SYS_CALLS);
    printf("\n");

    printf("Run time:       %15d\n", RUN_TIME);
    printf("Syscbll time:   %15d\n", SYS_TIME);
    printf("Jbvb+JNI time:  %15d\n", JAVA_TIME);
    printf("\n");
}

:::END
{
    printf("\nEND %s\n", SAMPLE_NAME);
}

syscbll::rexit:entry,
syscbll::exit:entry
/pid == $tbrget/
{
    exit(0);
}
