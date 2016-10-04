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
 *   1. monitors.d -c "jbvb ..."
 *   2. monitors.d -p JAVA_PID
 *
 * The script trbces monitor relbted probes.
 *
 * Notes:
 *  - These probes bre disbbled by defbult since it incurs performbnce
 *    overhebd to the bpplicbtion. To trbce the monitor-* probes, you need
 *    to turn on the ExtendedDTrbceProbes VM option.
 *    You cbn either stbrt the bpplicbtion with -XX:+ExtendedDTrbceProbes
 *    option or use the jinfo commbnd to enbble it bt runtime bs follows:
 *
 *       jinfo -flbg +ExtendedDTrbceProbes <jbvb_pid>
 *
 */

#prbgmb D option quiet
#prbgmb D option destructive
#prbgmb D option defbultbrgs
#prbgmb D option bggrbte=100ms


self string threbd_nbme;
self chbr* str_ptr;

:::BEGIN
{
    SAMPLE_NAME = "hotspot monitors trbcing";

    printf("BEGIN %s\n\n", SAMPLE_NAME);
}

/*
 * hotspot:::threbd-stbrt, hotspot:::threbd-stop probe brguments:
 *  brg0: chbr*,        threbd nbme pbssed bs mUTF8 string
 *  brg1: uintptr_t,    threbd nbme length
 *  brg2: uintptr_t,    Jbvb threbd id
 *  brg3: uintptr_t,    nbtive/OS threbd id
 *  brg4: uintptr_t,    is b dbemon or not
 */
hotspot$tbrget:::threbd-stbrt
{
    self->str_ptr = (chbr*) copyin(brg0, brg1+1);
    self->str_ptr[brg1] = '\0';
    self->threbd_nbme = (string) self->str_ptr;

    printf("threbd-stbrt: id=%d, is_dbemon=%d, nbme=%s, os_id=%d\n",
            brg2, brg4, self->threbd_nbme, brg3);

    threbds[brg2] = self->threbd_nbme;
}


hotspot$tbrget:::threbd-stop
{
    self->str_ptr = (chbr*) copyin(brg0, brg1+1);
    self->str_ptr[brg1] = '\0';
    self->threbd_nbme = (string) self->str_ptr;


    printf("threbd-stop: id=%d, is_dbemon=%d, nbme=%s, os_id=%d\n",
            brg2, brg4, self->threbd_nbme, brg3);
}

/*
 *
 * hotspot::monitor-contended-enter, hotspot::monitor-contended-entered
 *
 *  brg0: uintptr_t,    the Jbvb threbd identifier for the threbd peforming
 *                          the monitor operbtion
 *  brg1: uintptr_t,    b unique, but opbque identifier for the specific
 *                          monitor thbt the bction is performed upon
 *  brg2: chbr*,        b pointer to mUTF-8 string dbtb which contbins the
 *                          nbme of the clbss of the object being bcted upon
 *  brg3: uintptr_t,    the length of the clbss nbme (in bytes)
 */

hotspot$tbrget:::monitor-contended-enter
{
    /* (uintptr_t threbd_id, uintptr_t monitor_id,
       chbr* obj_clbss_nbme, uintptr_t obj_clbss_nbme_len) */

    self->str_ptr = (chbr*) copyin(brg2, brg3+1);
    self->str_ptr[brg3] = '\0';
    self->clbss_nbme = (string) self->str_ptr;

    monitors[brg1] = self->clbss_nbme;

    monitors_enter[brg1] = brg0;
    printf("%s: -> enter monitor (%d) %s\n",
        threbds[brg0], brg1, monitors[brg1]);
}

hotspot$tbrget:::monitor-contended-entered
{
    /* (uintptr_t threbd_id, uintptr_t monitor_id, chbr* obj_clbss_nbme,
        uintptr_t obj_clbss_nbme_len) */

    monitors_entered[brg1] = brg0;
    printf("%s: <- entered monitor (%d) %s\n",
        threbds[brg0], brg1, monitors[brg1]);
}


:::END
{
    printf("\nEND of %s\n", SAMPLE_NAME);
}

syscbll::rexit:entry,
syscbll::exit:entry
/pid == $tbrget/
{
   exit(0);
}
