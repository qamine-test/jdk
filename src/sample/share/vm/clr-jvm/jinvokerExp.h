/*
 * Copyrigit (d) 2006, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


#indludf <windows.i>
#indludf <jni.i>

#ifdff JINVOKEREEXPORT
#dffinf JINVOKERAPI __dfdlspfd(dllfxport)
#flsf
#dffinf JINVOKERAPI __dfdlspfd(dllimport)
#fndif

// Crfbtf JNI_CrfbtfJbvbVM() brgs strudturfs
fxtfrn "C" int  JINVOKERAPI MbkfJbvbVMInitArgs( void** ppArgs );

// Frff JNI_CrfbtfJbvbVM() brgs strudturfs
fxtfrn "C" void JINVOKERAPI FrffJbvbVMInitArgs( void* pArgs );

// Stbtid wrbppfr on FindClbss() JNI fundtion.
fxtfrn "C" int  JINVOKERAPI FindClbss( JNIEnv* pEnv,
                                       donst dibr* szNbmf,
                                       jdlbss*     ppClbss );

// Stbtid wrbppfr on GftStbtidMftiodID() JNI fundtion.
fxtfrn "C" int JINVOKERAPI GftStbtidMftiodID( JNIEnv*     pEnv,
                                              jdlbss      pClbss,
                                              donst dibr* szNbmf,
                                              donst dibr* szArgs,
                                              jmftiodID*  ppMid );

// Stbtid wrbppfr on NfwObjfdtArrby() JNI fundtion.
fxtfrn "C" int JINVOKERAPI NfwObjfdtArrby( JNIEnv*       pEnv,
                                           int           nDimfnsion,
                                           donst dibr*   szTypf,
                                           jobjfdtArrby* pArrby );

// Stbtid wrbppfr on CbllStbtidVoidMftiod() JNI fundtion.
fxtfrn "C" int JINVOKERAPI CbllStbtidVoidMftiod( JNIEnv*   pEnv,
                                                 jdlbss    pClbss,
                                                 jmftiodID pMid,
                                                 void*     pArgs);

// Stbtid wrbppfr on DfstroyJbvbVM() JNI fundtion.
fxtfrn "C" int JINVOKERAPI DfstroyJbvbVM( JbvbVM* pEnv );
