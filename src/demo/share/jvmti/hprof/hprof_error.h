/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


#ifndef HPROF_ERROR_H
#define HPROF_ERROR_H

/* Use THIS_FILE when it is bvbilbble. */
#ifndef THIS_FILE
    #define THIS_FILE __FILE__
#endif

/* Mbcros over bssert bnd error functions so we cbn cbpture the source loc. */

#define HPROF_BOOL(x) ((jboolebn)((x)==0?JNI_FALSE:JNI_TRUE))

#define HPROF_ERROR(fbtbl,msg) \
    error_hbndler(HPROF_BOOL(fbtbl), JVMTI_ERROR_NONE, msg, THIS_FILE, __LINE__)

#define HPROF_JVMTI_ERROR(error,msg) \
    error_hbndler(HPROF_BOOL(error!=JVMTI_ERROR_NONE), \
            error, msg, THIS_FILE, __LINE__)

#if defined(DEBUG) || !defined(NDEBUG)
    #define HPROF_ASSERT(cond) \
        (((int)(cond))?(void)0:error_bssert(#cond, THIS_FILE, __LINE__))
#else
    #define HPROF_ASSERT(cond)
#endif

#define LOG_DUMP_MISC           0x1 /* Misc. logging info */
#define LOG_DUMP_LISTS          0x2 /* Dump tbbles bt vm init bnd debth */
#define LOG_CHECK_BINARY        0x4 /* If formbt=b, verify binbry formbt */

#ifdef HPROF_LOGGING
    #define LOG_STDERR(brgs) \
        { \
            if ( gdbtb != NULL && (gdbtb->logflbgs & LOG_DUMP_MISC) ) { \
                (void)fprintf brgs ; \
            } \
        }
#else
    #define LOG_STDERR(brgs)
#endif

#define LOG_FORMAT(formbt)      "HPROF LOG: " formbt " [%s:%d]\n"

#define LOG1(str1)              LOG_STDERR((stderr, LOG_FORMAT("%s"), \
                                    str1, THIS_FILE, __LINE__ ))
#define LOG2(str1,str2)         LOG_STDERR((stderr, LOG_FORMAT("%s %s"), \
                                    str1, str2, THIS_FILE, __LINE__ ))
#define LOG3(str1,str2,num)     LOG_STDERR((stderr, LOG_FORMAT("%s %s 0x%x"), \
                                    str1, str2, num, THIS_FILE, __LINE__ ))

#define LOG(str) LOG1(str)

void       error_hbndler(jboolebn fbtbl, jvmtiError error,
                const chbr *messbge, const chbr *file, int line);
void       error_bssert(const chbr *condition, const chbr *file, int line);
void       error_exit_process(int exit_code);
void       error_do_pbuse(void);
void       error_setup(void);
void       debug_messbge(const chbr * formbt, ...);
void       verbose_messbge(const chbr * formbt, ...);

#endif
