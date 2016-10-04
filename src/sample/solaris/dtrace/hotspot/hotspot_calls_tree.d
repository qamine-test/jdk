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
 *   1. hotspot_cblls_tree.d -c "jbvb ..."
 *   2. hotspot_cblls_tree.d -p JAVA_PID
 *
 * This script prints cblls tree of fired 'hotspot' probes.
 *
 * Notes: 
 *    The script uses 'monitors' probes which bre disbbled by defbult since
 *    it incurs performbnce overhebd to the bpplicbtion. To enbble them, you
 *    need to turn on the ExtendedDTrbceProbes VM option. You cbn either
 *    stbrt the bpplicbtion with -XX:+ExtendedDTrbceProbes option or use the
 *    jinfo commbnd to enbble it bt runtime bs follows:
 *
 *       jinfo -flbg +ExtendedDTrbceProbes <jbvb_pid>
 *
 */

#prbgmb D option quiet
#prbgmb D option destructive
#prbgmb D option defbultbrgs
#prbgmb D option bggrbte=100ms

self int indent;
string PAUSE_AT_STARTUP_FILE;

:::BEGIN
{
    SAMPLE_NAME = "hotspot probes trbcing";

    printf("BEGIN %s\n\n", SAMPLE_NAME);

    self->indent = 10;
}

hotspot$tbrget:::clbss-lobded,
hotspot$tbrget:::clbss-unlobded,
hotspot$tbrget:::compiled-method-lobd,
hotspot$tbrget:::compiled-method-unlobd,
hotspot$tbrget:::monitor-notify,
hotspot$tbrget:::monitor-notifyAll
{
    printf("%d %*s <-> %s\n", curcpu->cpu_id, self->indent, "", probenbme);
}

hotspot$tbrget:::vm-init-begin,
hotspot$tbrget:::gc-begin,
hotspot$tbrget:::mem-pool-gc-begin,
hotspot$tbrget:::threbd-stbrt,
hotspot$tbrget:::method-compile-begin,
hotspot$tbrget:::monitor-contended-enter,
hotspot$tbrget:::monitor-wbit
{
    self->indent ++;
    printf("%d %*s -> %s\n", curcpu->cpu_id, self->indent, "", probenbme);
}

hotspot$tbrget:::vm-init-end,
hotspot$tbrget:::vm-shutdown,
hotspot$tbrget:::gc-end,
hotspot$tbrget:::mem-pool-gc-end,
hotspot$tbrget:::threbd-stop,
hotspot$tbrget:::method-compile-end,
hotspot$tbrget:::monitor-contended-entered,
hotspot$tbrget:::monitor-contended-exit,
hotspot$tbrget:::monitor-wbited
{
    printf("%d %*s <- %s\n", curcpu->cpu_id, self->indent, "", probenbme);
    self->indent --;
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
