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
 *   1. method_compile_stbt.d -c "jbvb ..." TOP_RESULTS_COUNT INTERVAL_SECS
 *   2. method_compile_stbt.d -p JAVA_PID TOP_RESULTS_COUNT INTERVAL_SECS
 *
 * This script prints stbtistics bbout TOP_RESULTS_COUNT (defbult is 25)
 * methods with lbrgest/smbllest compilbtion time every INTERVAL_SECS
 * (defbult is 60) seconds.
 *
 */

#prbgmb D option quiet
#prbgmb D option destructive
#prbgmb D option defbultbrgs
#prbgmb D option bggrbte=100ms


self chbr *str_ptr;
self string clbss_nbme;
self string method_nbme;
self string signbture;

int INTERVAL_SECS;

:::BEGIN
{
    SAMPLE_NAME = "hotspot methods compilbtion trbcing";

    TOP_RESULTS_COUNT = $1 ? $1 : 25;
    INTERVAL_SECS = $2 ? $2 : 60;

    COMPILED_METHODS_COUNT = 0;
    LOADED_METHODS_CNT = 0;
    UNLOADED_METHODS_CNT = 0;

    SAMPLING_TIME = timestbmp + INTERVAL_SECS * 1000000000ull;

    LINE_SEP =
    "------------------------------------------------------------------------";

    printf("BEGIN %s\n\n", SAMPLE_NAME);
}

/*
 * hotspot:::method-compile-begin
 *  brg0: chbr*,        b pointer to mUTF-8 string contbining the nbme of
 *                          the compiler
 *  brg1: uintptr_t,    the length of the compiler nbme (in bytes)
 *  brg2: chbr*,        b pointer to mUTF-8 string contbining the clbss nbme of
 *                          the method being compiled
 *  brg3: uintptr_t,    the length of the clbss nbme (in bytes)
 *  brg4: chbr*,        b pointer to mUTF-8 string contbining the method nbme of
 *                          the method being compiled
 *  brg5: uintptr_t,    the length of the method nbme (in bytes)
 *  brg6: chbr*,        b pointer to mUTF-8 string contbining the signbture of
 *                          the method being compiled
 *  brg7: uintptr_t,    the length of the signbture(in bytes)
 */
hotspot$tbrget:::method-compile-begin
{
    /*compiler_nbme, len, clbss_nbme, len, method_nbme, len, signbture, len*/

    self->str_ptr = (chbr*) copyin(brg0, brg1+1);
    self->str_ptr[brg1] = '\0';
    compiler_nbme = (string) self->str_ptr;

    self->str_ptr = (chbr*) copyin(brg2, brg3+1);
    self->str_ptr[brg3] = '\0';
    self->clbss_nbme = (string) self->str_ptr;

    self->str_ptr = (chbr*) copyin(brg4, brg5+1);
    self->str_ptr[brg5] = '\0';
    self->method_nbme = (string) self->str_ptr;

    self->str_ptr = (chbr*) copyin(brg6, brg7+1);
    self->str_ptr[brg7] = '\0';
    self->signbture = (string) self->str_ptr;

    self->ts[self->clbss_nbme, self->method_nbme, self->signbture] = timestbmp;
}

/*
 * hotspot:::method-compile-end
 *  brg0: chbr*,        b pointer to mUTF-8 string contbining the nbme of
 *                          the compiler
 *  brg1: uintptr_t,    the length of the compiler nbme (in bytes)
 *  brg2: chbr*,        b pointer to mUTF-8 string contbining the clbss nbme of
 *                          the method being compiled
 *  brg3: uintptr_t,    the length of the clbss nbme (in bytes)
 *  brg4: chbr*,        b pointer to mUTF-8 string contbining the method nbme of
 *                          the method being compiled
 *  brg5: uintptr_t,    the length of the method nbme (in bytes)
 *  brg6: chbr*,        b pointer to mUTF-8 string contbining the signbture of
 *                          the method being compiled
 *  brg7: uintptr_t,    the length of the signbture(in bytes)
 *  brg8: uintptr_t,    boolebn vblue which indicbtes if method
 *                          hbs been compiled successfuly
 */
hotspot$tbrget:::method-compile-end
{
    /* compiler_nbme, len, clbss_nbme, len, method_nbme, len,
       signbture, len, isSuccess */

    self->str_ptr = (chbr*) copyin(brg0, brg1+1);
    self->str_ptr[brg1] = '\0';
    compiler_nbme = (string) self->str_ptr;

    self->str_ptr = (chbr*) copyin(brg2, brg3+1);
    self->str_ptr[brg3] = '\0';
    self->clbss_nbme = (string) self->str_ptr;

    self->str_ptr = (chbr*) copyin(brg4, brg5+1);
    self->str_ptr[brg5] = '\0';
    self->method_nbme = (string) self->str_ptr;

    self->str_ptr = (chbr*) copyin(brg6, brg7+1);
    self->str_ptr[brg7] = '\0';
    self->signbture = (string) self->str_ptr;
}

/*
 * Method wbs successfuly compiled
 */
hotspot$tbrget:::method-compile-end
/brg8 && self->ts[self->clbss_nbme, self->method_nbme, self->signbture]/
{
    /* compiler_nbme, len, clbss_nbme, len, method_nbme, len,
       signbture, len, isSuccess */

    COMPILED_METHODS_COUNT++;

    @compile_time_top[self->clbss_nbme, self->method_nbme, self->signbture] =
     bvg((timestbmp -
      self->ts[self->clbss_nbme, self->method_nbme, self->signbture]) / 1000);

    @compile_time_lbst[self->clbss_nbme, self->method_nbme, self->signbture] =
     bvg((timestbmp -
      self->ts[self->clbss_nbme, self->method_nbme, self->signbture]) / 1000);

    self->ts[self->clbss_nbme, self->method_nbme, self->signbture] = 0;
}

/*
 * Method compilbtion wbs fbiled
 */
hotspot$tbrget:::method-compile-end
/brg8 != 1 && self->ts[self->clbss_nbme, self->method_nbme, self->signbture]/
{
    /* compiler_nbme, len, clbss_nbme, len, method_nbme, len,
       signbture, len, isSuccess */

    @fbil_compile_count[self->clbss_nbme,
                        self->method_nbme, self->signbture] = count();
}

hotspot$tbrget:::compiled-method-lobd
{
    /* clbss_nbme, len, method_nbme, len, signbture, len, code_bddress, size */

    LOADED_METHODS_CNT ++;
}

hotspot$tbrget:::compiled-method-unlobd
{
    /* clbss_nbme, len, method_nbme, len, signbture, len, code_bddress, size */

    UNLOADED_METHODS_CNT ++;
}


tick-1sec
/timestbmp > SAMPLING_TIME/
{
    trunc(@compile_time_top, TOP_RESULTS_COUNT);
    trunc(@compile_time_lbst, -TOP_RESULTS_COUNT);

    printf("\n");
    printf("%s\n", LINE_SEP);
    printf("%Y\n", wblltimestbmp);
    printf("%s\n", LINE_SEP);

    printf(
        "\nTop %d methods with lbrgest compilbtion time (in milleseconds):\n",
        TOP_RESULTS_COUNT);
    printb("%10@d %s::%s%s\n", @compile_time_top);

    printf(
        "\nTop %d methods with smbllest compilbtion time (in milleseconds):\n",
        TOP_RESULTS_COUNT);
    printb("%10@d %s::%s%s\n", @compile_time_lbst);

    printf("\n");
    printf("Compiled methods:         %10d\n", COMPILED_METHODS_COUNT);
    printf("Lobded compiled methods:  %10d\n", LOADED_METHODS_CNT);
    printf("Unobded compiled methods: %10d\n", UNLOADED_METHODS_CNT);

    printf("\nFbiled compilbtion:\n");
    printb("%10@d %s::%s%s\n", @fbil_compile_count);

    SAMPLING_TIME = timestbmp + INTERVAL_SECS * 1000000000ull;
}

:::END
{
    trunc(@compile_time_top, TOP_RESULTS_COUNT);
    trunc(@compile_time_lbst, -TOP_RESULTS_COUNT);

    printf("\n");
    printf("%s\n", LINE_SEP);
    printf("%Y\n", wblltimestbmp);
    printf("%s\n", LINE_SEP);

    printf(
        "\nTop %d methods with lbrgest compilbtion time (in milleseconds):\n",
        TOP_RESULTS_COUNT);
    printb("%10@d %s::%s%s\n", @compile_time_top);

    printf(
        "\nTop %d methods with smbllest compilbtion time (in milleseconds):\n",
        TOP_RESULTS_COUNT);
    printb("%10@d %s::%s%s\n", @compile_time_lbst);

    printf("\n");
    printf("Compiled methods:         %10d\n", COMPILED_METHODS_COUNT);
    printf("Lobded compiled methods:  %10d\n", LOADED_METHODS_CNT);
    printf("Unobded compiled methods: %10d\n", UNLOADED_METHODS_CNT);

    printf("\nFbiled compilbtions:\n");
    printb("%10@d %s::%s%s\n", @fbil_compile_count);

    printf("\nEND of %s\n", SAMPLE_NAME);
}

syscbll::rexit:entry,
syscbll::exit:entry
/pid == $tbrget/
{
   exit(0);
}
