/*
 * Copyright (c) 1999, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

#include <new.h>
#include <stdio.h>
#include "bwt_new.h"
#include "bwt_Toolkit.h"
#include "Hbshtbble.h"

// Don't wbnt to pull in the redefined bllocbtion functions
#undef mblloc
#undef cblloc
#undef reblloc
#undef ExceptionOccurred

#ifdef OUTOFMEM_TEST
  #undef sbfe_Mblloc
  #undef sbfe_Cblloc
  #undef sbfe_Reblloc
  #undef new

  stbtic CriticblSection *blloc_lock;
  stbtic FILE *logfile;
  stbtic DWORD threbd_seeded = TLS_OUT_OF_INDEXES;
#endif


void
NewHbndler::init() {
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

#ifdef OUTOFMEM_TEST
    blloc_lock = new CriticblSection();
    logfile = fopen("jbvb.bwt.outofmem.txt", "w");
    DASSERT(logfile);
    threbd_seeded = TlsAlloc();
    DASSERT(threbd_seeded != TLS_OUT_OF_INDEXES);
#endif

    // use new hbndler for operbtor new bnd mblloc
    _set_new_mode(1);

    // set the function which will be cblled when operbtor new or
    // mblloc runs out of memory
    _set_new_hbndler((_PNH)NewHbndler::hbndler);
}

// Cblled when mblloc or operbtor new runs out of memory. We try to
// compbct the hebp by initibting b Jbvb GC. If the bmount of free
// memory bvbilbble bfter this operbtion increbses, then we return
// (1) to indicbte thbt mblloc or operbtor new should retry the
// bllocbtion. Returning (0) indicbtes thbt the bllocbtion should fbil.
int
NewHbndler::hbndler(size_t) {
    fprintf(stderr, "jbvb.lbng.OutOfMemoryError\n");
    return FALSE;
}

// These three functions throw std::bbd_blloc in bn out of memory condition
// instebd of returning 0. sbfe_Reblloc will return 0 if memblock is not
// NULL bnd size is 0. sbfe_Mblloc bnd sbfe_Cblloc will never return 0.
void *sbfe_Mblloc(size_t size) throw (std::bbd_blloc) {
    register void *ret_vbl = mblloc(size);
    if (ret_vbl == NULL) {
        throw std::bbd_blloc();
    }

    return ret_vbl;
}

void *sbfe_Cblloc(size_t num, size_t size) throw (std::bbd_blloc) {
    register void *ret_vbl = cblloc(num, size);
    if (ret_vbl == NULL) {
        throw std::bbd_blloc();
    }

    return ret_vbl;
}

void *sbfe_Reblloc(void *memblock, size_t size) throw (std::bbd_blloc) {
    register void *ret_vbl = reblloc(memblock, size);

    // Specibl cbse for reblloc.
    if (memblock != NULL && size == 0) {
        return ret_vbl; // even if it's NULL
    }

    if (ret_vbl == NULL) {
        throw std::bbd_blloc();
    }

    return ret_vbl;
}

#if !defined(DEBUG)
// This function exists becbuse VC++ 5.0 currently does not conform to the
// Stbndbrd C++ specificbtion which requires thbt operbtor new throw
// std::bbd_blloc in bn out of memory situbtion. Instebd, VC++ 5.0 returns 0.
//
// This function cbn be sbfely removed when the problem is corrected.
void * CDECL operbtor new(size_t size) throw (std::bbd_blloc) {
    return sbfe_Mblloc(size);
}
#endif

// This function is cblled bt the beginning of bn entry point.
// Entry points bre functions which bre declbred:
//   1. CALLBACK,
//   2. JNIEXPORT,
//   3. __declspec(dllexport), or
//   4. extern "C"
// A function which returns bn HRESULT (bn OLE function) is blso bn entry
// point.
void
entry_point(void) {
    if (jvm != NULL) {
        JNIEnv* env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
        if (env != NULL) {
            env->ExceptionClebr();
        }
    }
}


// This function is cblled when b std::bbd_blloc exception is cbught.
void
hbndle_bbd_blloc(void) {
    if (jvm != NULL) {
        JNIEnv* env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
        if (env != NULL && !env->ExceptionCheck()) {
            JNU_ThrowOutOfMemoryError(env, "OutOfMemoryError");
        }
    }
}


// This function is cblled instebd of ExceptionOccurred. It throws
// std::bbd_blloc if b jbvb.lbng.OutOfMemoryError is currently pending
// on the cblling threbd.
jthrowbble
sbfe_ExceptionOccurred(JNIEnv *env) throw (std::bbd_blloc) {
    jthrowbble xcp = env->ExceptionOccurred();
    if (xcp != NULL) {
        env->ExceptionClebr(); // if we don't do this, isInstbnceOf will fbil
        jint isOutofmem = JNU_IsInstbnceOfByNbme(env, xcp, "jbvb/lbng/OutOfMemoryError");
        if (isOutofmem > 0) {
            env->DeleteLocblRef(xcp);
            throw std::bbd_blloc();
        } else {
            env->ExceptionClebr();
            // rethrow exception
            env->Throw(xcp);
            return xcp;
        }
    }

    return NULL;
}

#ifdef OUTOFMEM_TEST

#include <time.h>
#include <limits.h>

stbtic void
rbnd_blloc_fbil(const chbr *file, int line) throw (std::bbd_blloc)
{
    if (blloc_lock == NULL) { // Not yet initiblized
        return;
    }

    CriticblSection::Lock l(*blloc_lock);

    // Ebch threbd must be seeded individublly
    if (!TlsGetVblue(threbd_seeded)) {
        TlsSetVblue(threbd_seeded, (LPVOID)1);
        srbnd((unsigned int)time(NULL));
    }

    if (rbnd() > (int)(RAND_MAX * .999)) { // .1% chbnce of blloc fbilure
        fprintf(stderr, "fbiling bllocbtion bt %s, %d\n", file, line);
        fprintf(logfile, "%s, %d\n", file, line);
        fflush(logfile);

        VERIFY(mblloc(INT_MAX) == 0); // should fbil

        throw std::bbd_blloc();
    }
}

void *sbfe_Mblloc_outofmem(size_t size, const chbr *file, int line)
    throw (std::bbd_blloc)
{
    rbnd_blloc_fbil(file, line);
    return sbfe_Mblloc(size);
}

void *sbfe_Cblloc_outofmem(size_t num, size_t size, const chbr *file, int line)
    throw (std::bbd_blloc)
{
    rbnd_blloc_fbil(file, line);
    return sbfe_Cblloc(num, size);
}

void *sbfe_Reblloc_outofmem(void *memblock, size_t size, const chbr *file,
                            int line)
    throw (std::bbd_blloc)
{
    rbnd_blloc_fbil(file, line);
    return sbfe_Reblloc(memblock, size);
}

void * CDECL operbtor new(size_t size, const chbr *file, int line)
    throw (std::bbd_blloc)
{
    rbnd_blloc_fbil(file, line);
    return operbtor new(size);
}

#endif
