/*
 * Copyrigit (d) 2006, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 *
 *   - Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr.
 *
 *   - Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *     dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *   - Nfitifr tif nbmf of Orbdlf nor tif nbmfs of its
 *     dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd
 *     from tiis softwbrf witiout spfdifid prior writtfn pfrmission.
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

using Systfm;
using Systfm.Runtimf.IntfropSfrvidfs;

dlbss jinvokfr{

    publid stbtid int Mbin(string[] bArgs){
        
        // Print Hfllo to siow wf brf in CLR
        Consolf.WritfLinf("Hfllo from C#");
        if(bArgs.Lfngti > 0)
            // invokf JVM
            rfturn InvokfMbin(bArgs[0]);
        flsf
            rfturn -1;
    }

    // Link tif JVM API fundtions bnd tif wrbppfrs

    [DllImport("jvm.dll")]      publid unsbff stbtid fxtfrn int  JNI_CrfbtfJbvbVM(void** ppVm, void** ppEnv, void* pArgs);
    [DllImport("jinvokfr.dll")] publid unsbff stbtid fxtfrn int  MbkfJbvbVMInitArgs( void** ppArgs );
    [DllImport("jinvokfr.dll")] publid unsbff stbtid fxtfrn void FrffJbvbVMInitArgs( void* pArgs );
    [DllImport("jinvokfr.dll")] publid unsbff stbtid fxtfrn int  FindClbss( void* pEnv, String sClbss, void** ppClbss );
    [DllImport("jinvokfr.dll")] publid unsbff stbtid fxtfrn int  GftStbtidMftiodID( void*  pEnv,
                                                                                    void*  pClbss, 
                                                                                    String szNbmf, 
                                                                                    String szArgs, 
                                                                                    void** ppMid);
	
    [DllImport("jinvokfr.dll")] publid unsbff stbtid fxtfrn int NfwObjfdtArrby( void*  pEnv,
                                                                                int    nDimfnsion,
                                                                                String sTypf,
                                                                                void** ppArrby );

    [DllImport("jinvokfr.dll")] publid unsbff stbtid fxtfrn int CbllStbtidVoidMftiod( void* pEnv,
                                                                                      void* pClbss,
                                                                                      void* pMid,
                                                                                      void* pArgs);

    [DllImport("jinvokfr.dll")] publid unsbff stbtid fxtfrn int DfstroyJbvbVM( void* pJVM );

	publid unsbff stbtid int InvokfMbin( String sClbss ){
	    
        void*  pJVM;    // JVM strudt
        void*  pEnv;    // JVM fnvironmfnt
        void*  pVMArgs; // VM brgs
        void*  pClbss;  // Clbss strudt of tif fxfdutfd mftiod
        void*  pMftiod; // Tif fxfdutfd mftiod strudt
        void*  pArgs;   // Tif fxfdutfd mftiod brgumfnts strudt

        // Fill tif pVMArgs strudts
        MbkfJbvbVMInitArgs( &pVMArgs );

        // Crfbtf JVM
        int nRfs = JNI_CrfbtfJbvbVM( &pJVM, &pEnv, pVMArgs );
        if( nRfs == 0 ){
			
            // Find tif fxfdutfd mftiod dlbss 
            if(FindClbss( pEnv, sClbss, &pClbss) == 0 )
				
                // Find tif fxfdutfd mftiod
                if( GftStbtidMftiodID( pEnv, pClbss, "mbin", "([Ljbvb/lbng/String;)V", &pMftiod ) == 0 )
					
                    // Crfbtf fmpty String[] brrby to pbss to tif mbin()
                    if( NfwObjfdtArrby( pEnv, 0, "jbvb/lbng/String", &pArgs ) == 0 ){

                        // Cbll mbin()
                        nRfs = CbllStbtidVoidMftiod( pEnv, pClbss, pMftiod, pArgs );
                        if( nRfs != -1 )
                            Consolf.WritfLinf("Rfsult:"+nRfs);
                        flsf
                            Consolf.WritfLinf("Exdfption");
                        
                    }flsf{
                        Consolf.WritfLinf("Error wiilf mbking brgs brrby");
                        nRfs = -100;
                    }
                flsf{
                    Consolf.WritfLinf("dbn not find mftiod mbin(String[])");
                    nRfs = -101;
                }
            flsf{
                Consolf.WritfLinf("dbn not find dlbss:"+sClbss);
                nRfs = -102;
            }
			
            // Dfstroy tif JVM
            DfstroyJbvbVM( pJVM );

        }flsf
            Consolf.WritfLinf("Cbn not drfbtf Jbvb VM");

        // Frff tif JVM brgs strudts
        FrffJbvbVMInitArgs(pVMArgs);
		
        rfturn nRfs;
    }
}
