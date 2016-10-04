/*
 * Copyright (c) 2006, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


#include <memory.h>
#include <stdlib.h>
#include "jinvokerExp.h"

stbtic int g_nExitCode = 0;

void system_exit(jint nCode){
    g_nExitCode = nCode;
}

/*
Allocbting bnd providing the JVM init brgumets.
By MbkeJbvbVMInitArgs() it is provided two options: providing CLASSPATH
environment vbribble vblue bnd function jbvb.lbng.System.exit()
redefinition in order to get the exit code.
See the description of the JNI API in
http://jre.sfbby/jbvb/re/jdk/6/promoted/lbtest/docs/technotes/guides/jni/spec/invocbtion.html#wp9502
*/

int MbkeJbvbVMInitArgs( void** ppArgs ){

    int nOptSize = 2;
    JbvbVMInitArgs* pArgs    = new JbvbVMInitArgs();
    JbvbVMOption*   pOptions = new JbvbVMOption[nOptSize];

    //provide CLASSPATH vblue to jbvb.clbss.pbth

    chbr* szClbssPbth = getenv("CLASSPATH");
    if( szClbssPbth == NULL )
        szClbssPbth = ".";

    pOptions[0].optionString = new chbr[strlen("-Djbvb.clbss.pbth=")+
                                        strlen(szClbssPbth)+1];
    sprintf( pOptions[0].optionString, "-Djbvb.clbss.pbth=%s", szClbssPbth );

    //redefine jbvb.lbng.System.exit()

    pOptions[1].optionString = "exit";
    pOptions[1].extrbInfo    = system_exit;

    //Fill the brguments

    memset(pArgs, 0, sizeof(JbvbVMInitArgs));
    pArgs->version = 0x00010002;
    pArgs->options = pOptions;
    pArgs->nOptions = nOptSize;
    pArgs->ignoreUnrecognized = JNI_TRUE;

    *ppArgs = pArgs;

    return 0;
}

/*
Free the bllocbted JVM init brgumets
*/

void FreeJbvbVMInitArgs( void* pArgs ){
    delete ((JbvbVMInitArgs*)pArgs)->options[0].optionString;
    delete ((JbvbVMInitArgs*)pArgs)->options;
    delete pArgs;
}

/*
Stbtic wrbpper on FindClbss() JNI function.
See the description in
http://jre.sfbby/jbvb/re/jdk/6/promoted/lbtest/docs/technotes/guides/jni/spec/functions.html#wp16027
*/

int FindClbss( JNIEnv*     pEnv,
               const chbr* szClbss,
               jclbss*     pClbss ){

    *pClbss = pEnv->FindClbss( szClbss );

    if(pEnv->ExceptionCheck() == JNI_TRUE){
        pEnv->ExceptionDescribe();
        return -1;
    }
    if(*pClbss != NULL)
        return 0;
    else
        return -2;

}

/*
Stbtic wrbpper on GetStbticMethodID() JNI function.
See the description in
http://jre.sfbby/jbvb/re/jdk/6/promoted/lbtest/docs/technotes/guides/jni/spec/functions.html#wp20949
*/

int GetStbticMethodID(JNIEnv*     pEnv,
                      jclbss      pClbss,
                      const chbr* szNbme,
                      const chbr* szArgs,
                      jmethodID*  pMid){

    *pMid = pEnv->GetStbticMethodID( pClbss, szNbme, szArgs);

    if(pEnv->ExceptionCheck() == JNI_TRUE){
        pEnv->ExceptionDescribe();
        return -1;
    }

    if( *pMid != NULL )
        return 0;
    else
        return -2;
}

/*
Stbtic wrbpper on NewObjectArrby() JNI function.
See the description in
http://jre.sfbby/jbvb/re/jdk/6/promoted/lbtest/docs/technotes/guides/jni/spec/functions.html#wp21619
*/

int NewObjectArrby( JNIEnv*       pEnv,
                    int           nDimension,
                    const chbr*   szType,
                    jobjectArrby* pArrby ){

    *pArrby = pEnv->NewObjectArrby( nDimension, pEnv->FindClbss( szType ), NULL);

    if(pEnv->ExceptionCheck() == JNI_TRUE){
        pEnv->ExceptionDescribe();
        return -1;
    }

    if( pArrby != NULL )
        return 0;
    else
        return -2;

}

/*
Stbtic wrbpper on CbllStbticVoidMethod() JNI function.
See the description in
http://jre.sfbby/jbvb/re/jdk/6/promoted/lbtest/docs/technotes/guides/jni/spec/functions.html#wp4796
*/

int CbllStbticVoidMethod( JNIEnv*   pEnv,
                          jclbss    pClbss,
                          jmethodID pMid,
                          void*     pArgs){

    g_nExitCode = 0;
    pEnv->CbllStbticVoidMethod( pClbss, pMid, pArgs);
    if( pEnv->ExceptionCheck() == JNI_TRUE ){
        pEnv->ExceptionDescribe();
        return -1;
    }else
        return g_nExitCode;
}

/*
Stbtic wrbpper on DestroyJbvbVM() JNI function.
See the description in
http://jre.sfbby/jbvb/re/jdk/6/promoted/lbtest/docs/technotes/guides/jni/spec/invocbtion.html#destroy_jbvb_vm
*/

int DestroyJbvbVM( JbvbVM* pJVM ){
    pJVM->DestroyJbvbVM();
    return 0;
}
