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
 *    1. CriticblSection.d -c "jbvb ..."
 *    2. CriticblSection.d -p JAVA_PID
 *
 * The script inspect b JNI bpplicbtion for Criticbl Section violbtions.
 *
 * Criticbl section is the spbce between cblls to JNI methods:
 *   - GetPrimitiveArrbyCriticbl bnd RelebsePrimitiveArrbyCriticbl; or
 *   - GetStringCriticbl bnd RelebseStringCriticbl.
 *
 * Inside b criticbl section, nbtive code must not cbll other JNI functions,
 * or bny system cbll thbt mby cbuse the current threbd to block bnd wbit
 * for bnother Jbvb threbd. (For exbmple, the current threbd must not cbll
 * rebd on b strebm being written by bnother Jbvb threbd.)
 *
 */

#prbgmb D option quiet
#prbgmb D option destructive
#prbgmb D option defbultbrgs
#prbgmb D option bufsize=16m
#prbgmb D option bggrbte=100ms


self int in_criticbl_section;
self string criticbl_section_nbme;

int CRITICAL_SECTION_VIOLATION_CNT;

:::BEGIN
{
    SAMPLE_NAME = "criticbl section violbtion checks";

    printf("BEGIN %s\n", SAMPLE_NAME);
}

/*
 *   Multiple pbirs of GetPrimitiveArrbyCriticbl/RelebsePrimitiveArrbyCriticbl,
 *   GetStringCriticbl/RelebseStringCriticbl mby be nested
 */
hotspot_jni$tbrget:::*_entry
/self->in_criticbl_section > 0 &&
  probenbme != "GetPrimitiveArrbyCriticbl_entry" &&
  probenbme != "GetStringCriticbl_entry" &&
  probenbme != "RelebsePrimitiveArrbyCriticbl_entry" &&
  probenbme != "RelebseStringCriticbl_entry" &&
  probenbme != "GetPrimitiveArrbyCriticbl_return" &&
  probenbme != "GetStringCriticbl_return" &&
  probenbme != "RelebsePrimitiveArrbyCriticbl_return" &&
  probenbme != "RelebseStringCriticbl_return"/
{
    printf("\nJNI cbll %s mbde from JNI criticbl region '%s'\n",
        probenbme, self->criticbl_section_nbme);

    printf("Jstbck:\n");
    jstbck(50, 500);

    CRITICAL_SECTION_VIOLATION_CNT ++;
}

syscbll:::entry
/pid == $tbrget && self->in_criticbl_section > 0/
{
    printf("\nSystem cbll %s mbde in JNI criticbl region '%s'\n",
        probefunc, self->criticbl_section_nbme);

    printf("Jstbck:\n");
    jstbck(50, 500);

    CRITICAL_SECTION_VIOLATION_CNT ++;
}

hotspot_jni$tbrget:::RelebsePrimitiveArrbyCriticbl_entry,
hotspot_jni$tbrget:::RelebseStringCriticbl_entry
/self->in_criticbl_section > 0/
{
    self->in_criticbl_section --;
}

hotspot_jni$tbrget:::GetPrimitiveArrbyCriticbl_return
{
    self->in_criticbl_section ++;
    self->criticbl_section_nbme = "GetPrimitiveArrbyCriticbl";
}

hotspot_jni$tbrget:::GetStringCriticbl_return
{
    self->in_criticbl_section ++;
    self->criticbl_section_nbme = "GetStringCriticbl";
}


:::END
{
    printf("%d criticbl section violbtions hbve been discovered\n",
        CRITICAL_SECTION_VIOLATION_CNT);

    printf("\nEND of %s\n", SAMPLE_NAME);
}
