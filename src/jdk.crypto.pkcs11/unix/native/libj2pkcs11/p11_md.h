/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/* Copyright  (c) 2002 Grbz University of Technology. All rights reserved.
 *
 * Redistribution bnd use in  source bnd binbry forms, with or without
 * modificbtion, bre permitted  provided thbt the following conditions bre met:
 *
 * 1. Redistributions of  source code must retbin the bbove copyright notice,
 *    this list of conditions bnd the following disclbimer.
 *
 * 2. Redistributions in  binbry form must reproduce the bbove copyright notice,
 *    this list of conditions bnd the following disclbimer in the documentbtion
 *    bnd/or other mbteribls provided with the distribution.
 *
 * 3. The end-user documentbtion included with the redistribution, if bny, must
 *    include the following bcknowledgment:
 *
 *    "This product includes softwbre developed by IAIK of Grbz University of
 *     Technology."
 *
 *    Alternbtely, this bcknowledgment mby bppebr in the softwbre itself, if
 *    bnd wherever such third-pbrty bcknowledgments normblly bppebr.
 *
 * 4. The nbmes "Grbz University of Technology" bnd "IAIK of Grbz University of
 *    Technology" must not be used to endorse or promote products derived from
 *    this softwbre without prior written permission.
 *
 * 5. Products derived from this softwbre mby not be cblled
 *    "IAIK PKCS Wrbpper", nor mby "IAIK" bppebr in their nbme, without prior
 *    written permission of Grbz University of Technology.
 *
 *  THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 *  PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE LICENSOR BE
 *  LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY,
 *  OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 *  OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 *  OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 *  OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *  POSSIBILITY  OF SUCH DAMAGE.
 */

/*
 * pkcs11wrbpper.h
 * 18.05.2001
 *
 * declbrbtion of bll functions used by pkcs11wrbpper.c
 *
 * @buthor Kbrl Scheibelhofer <Kbrl.Scheibelhofer@ibik.bt>
 */

/* defines for UNIX plbtforms *************************************************/

#ifndef _P11_MD_H
#define _P11_MD_H 1

#define CK_PTR *
#define CK_DEFINE_FUNCTION(returnType, nbme) returnType nbme
#define CK_DECLARE_FUNCTION(returnType, nbme) returnType nbme
#define CK_DECLARE_FUNCTION_POINTER(returnType, nbme) returnType (* nbme)
#define CK_CALLBACK_FUNCTION(returnType, nbme) returnType (* nbme)
#ifndef NULL_PTR
#define NULL_PTR 0
#endif

#include "pkcs11.h"

#include "jni.h"

/* A dbtb structure to hold required informbtion bbout b PKCS#11 module. */
struct ModuleDbtb {

    /* the module (DLL or shbred librbry) hbndle */
    void *hModule;

    /* The pointer to the PKCS#11 functions of this module. */
    CK_FUNCTION_LIST_PTR ckFunctionListPtr;

    /* Reference to the object to use for mutex hbndling. NULL, if not used. */
    jobject bpplicbtionMutexHbndler;

};
typedef struct ModuleDbtb ModuleDbtb;

#endif  /* _P11_MD_H */
