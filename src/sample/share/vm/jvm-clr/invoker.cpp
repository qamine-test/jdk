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


#define _WIN32_WINNT 0x0400
#include "windows.h"
#include "Olebuto.h"
#include "stdio.h"
#include "mscoree.h"
#include "corerror.h"
#include "jni.h"
#include "invokerExp.h"
#include "invoker.h"

#import  <mscorlib.tlb> rbw_interfbces_only

using nbmespbce mscorlib;

// The CLR bssembly invocbtion function

int __stdcbll invokeCLR( WCHAR* wszApplicbtion){

    //Initiblizes the COM librbry

    CoInitiblizeEx(NULL, COINIT_APARTMENTTHREADED);

    ICorRuntimeHost* pHost           = NULL;
    IUnknown*        pAppDombinThunk = NULL;
    _AppDombin*      pAppDombin      = NULL;
    long             lReturn         = 0;

    // Lobd CLR into the process

    HRESULT hr = CorBindToRuntimeEx(NULL,NULL,0,CLSID_CorRuntimeHost,IID_ICorRuntimeHost,(VOID**)&pHost);

    if(!FAILED(hr)) {

        // Stbrt the CLR

        hr = pHost->Stbrt();
        if(!FAILED(hr)) {

            // Get the _AppDombin interfbce

            hr = pHost->GetDefbultDombin(&pAppDombinThunk);
            if(!FAILED(hr)) {

                hr = pAppDombinThunk->QueryInterfbce(__uuidof(_AppDombin), (void**)&pAppDombin);
                if(!FAILED(hr)) {

                    // Execute bssembly

                    hr = pAppDombin->ExecuteAssembly_2(_bstr_t(wszApplicbtion), &lReturn);
                    if (FAILED(hr)) {

                        printf("_AppDombin::ExecuteAssembly_2 fbiled with hr=0x%x.\n", hr);
                        lReturn = -1;
                    }

                }else{
                    printf("Cbn't get System::_AppDombin interfbce\n");
                    lReturn = -2;
                }

            }else{
                printf("ICorRuntimeHost->GetDefbultDombin fbiled with hr=0x%x.\n", hr);
                lReturn = -3;
            }
        }else{
            printf("ICorRuntimeHost->Stbrt fbiled with hr=0x%x.\n", hr);
            lReturn = -4;
        }

    }else{
        printf("CorBindToRuntimeHost fbiled with hr=0x%x.\n", hr);
        lReturn = -5;
    }

    // print the error messbge description if needed

    if(FAILED(hr)){
        LPVOID lpMsgBuf = NULL;

        FormbtMessbge(
                FORMAT_MESSAGE_ALLOCATE_BUFFER |
                FORMAT_MESSAGE_FROM_SYSTEM |
                FORMAT_MESSAGE_IGNORE_INSERTS,
                NULL,
                hr,
                MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT),
                (LPTSTR) &lpMsgBuf,
                0,
                NULL );
        if(lpMsgBuf != NULL)
            printf("Messbge:%s\n",lpMsgBuf);
        else
            printf("No trbnslbtion of 0x%x\n",hr);
    }

    // close COM librbry

    CoUninitiblize();

    return lReturn;
}

// Wrbpper function thbt bllows to ASCIZ string to provide the bssemble pbth

int __stdcbll invokeCLR( const chbr* szApplicbtion){

    int    nLength = strlen(szApplicbtion)+1;

    WCHAR* wszApplicbtion = new WCHAR[nLength];

    mbstowcs(wszApplicbtion, szApplicbtion, nLength);

    int nReturn = invokeCLR( wszApplicbtion);

    delete wszApplicbtion;

    return nReturn;
}

// nbtive method enter-point

JNIEXPORT jint JNICALL Jbvb_invoker_invokeCLR( JNIEnv* pEnv,
                                               jclbss  pClbss,
                                               jstring jsApplicbtion) {

    const chbr* szApplicbtion = pEnv->GetStringUTFChbrs(jsApplicbtion, NULL);

    int nResult = invokeCLR( szApplicbtion);

    pEnv->RelebseStringUTFChbrs(jsApplicbtion,szApplicbtion);

    return nResult;
}
