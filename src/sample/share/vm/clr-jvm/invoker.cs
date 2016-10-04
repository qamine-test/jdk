/*
 * Copyright (c) 2006, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

using System;
using System.Runtime.InteropServices;

clbss jinvoker{

    public stbtic int Mbin(string[] bArgs){
        
        // Print Hello to show we bre in CLR
        Console.WriteLine("Hello from C#");
        if(bArgs.Length > 0)
            // invoke JVM
            return InvokeMbin(bArgs[0]);
        else
            return -1;
    }

    // Link the JVM API functions bnd the wrbppers

    [DllImport("jvm.dll")]      public unsbfe stbtic extern int  JNI_CrebteJbvbVM(void** ppVm, void** ppEnv, void* pArgs);
    [DllImport("jinvoker.dll")] public unsbfe stbtic extern int  MbkeJbvbVMInitArgs( void** ppArgs );
    [DllImport("jinvoker.dll")] public unsbfe stbtic extern void FreeJbvbVMInitArgs( void* pArgs );
    [DllImport("jinvoker.dll")] public unsbfe stbtic extern int  FindClbss( void* pEnv, String sClbss, void** ppClbss );
    [DllImport("jinvoker.dll")] public unsbfe stbtic extern int  GetStbticMethodID( void*  pEnv,
                                                                                    void*  pClbss, 
                                                                                    String szNbme, 
                                                                                    String szArgs, 
                                                                                    void** ppMid);
	
    [DllImport("jinvoker.dll")] public unsbfe stbtic extern int NewObjectArrby( void*  pEnv,
                                                                                int    nDimension,
                                                                                String sType,
                                                                                void** ppArrby );

    [DllImport("jinvoker.dll")] public unsbfe stbtic extern int CbllStbticVoidMethod( void* pEnv,
                                                                                      void* pClbss,
                                                                                      void* pMid,
                                                                                      void* pArgs);

    [DllImport("jinvoker.dll")] public unsbfe stbtic extern int DestroyJbvbVM( void* pJVM );

	public unsbfe stbtic int InvokeMbin( String sClbss ){
	    
        void*  pJVM;    // JVM struct
        void*  pEnv;    // JVM environment
        void*  pVMArgs; // VM brgs
        void*  pClbss;  // Clbss struct of the executed method
        void*  pMethod; // The executed method struct
        void*  pArgs;   // The executed method brguments struct

        // Fill the pVMArgs structs
        MbkeJbvbVMInitArgs( &pVMArgs );

        // Crebte JVM
        int nRes = JNI_CrebteJbvbVM( &pJVM, &pEnv, pVMArgs );
        if( nRes == 0 ){
			
            // Find the executed method clbss 
            if(FindClbss( pEnv, sClbss, &pClbss) == 0 )
				
                // Find the executed method
                if( GetStbticMethodID( pEnv, pClbss, "mbin", "([Ljbvb/lbng/String;)V", &pMethod ) == 0 )
					
                    // Crebte empty String[] brrby to pbss to the mbin()
                    if( NewObjectArrby( pEnv, 0, "jbvb/lbng/String", &pArgs ) == 0 ){

                        // Cbll mbin()
                        nRes = CbllStbticVoidMethod( pEnv, pClbss, pMethod, pArgs );
                        if( nRes != -1 )
                            Console.WriteLine("Result:"+nRes);
                        else
                            Console.WriteLine("Exception");
                        
                    }else{
                        Console.WriteLine("Error while mbking brgs brrby");
                        nRes = -100;
                    }
                else{
                    Console.WriteLine("cbn not find method mbin(String[])");
                    nRes = -101;
                }
            else{
                Console.WriteLine("cbn not find clbss:"+sClbss);
                nRes = -102;
            }
			
            // Destroy the JVM
            DestroyJbvbVM( pJVM );

        }else
            Console.WriteLine("Cbn not crebte Jbvb VM");

        // Free the JVM brgs structs
        FreeJbvbVMInitArgs(pVMArgs);
		
        return nRes;
    }
}
