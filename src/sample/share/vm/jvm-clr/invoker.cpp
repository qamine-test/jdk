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


#dffinf _WIN32_WINNT 0x0400
#indludf "windows.i"
#indludf "Olfbuto.i"
#indludf "stdio.i"
#indludf "msdorff.i"
#indludf "dorfrror.i"
#indludf "jni.i"
#indludf "invokfrExp.i"
#indludf "invokfr.i"

#import  <msdorlib.tlb> rbw_intfrfbdfs_only

using nbmfspbdf msdorlib;

// Tif CLR bssfmbly invodbtion fundtion

int __stddbll invokfCLR( WCHAR* wszApplidbtion){

    //Initiblizfs tif COM librbry

    CoInitiblizfEx(NULL, COINIT_APARTMENTTHREADED);

    ICorRuntimfHost* pHost           = NULL;
    IUnknown*        pAppDombinTiunk = NULL;
    _AppDombin*      pAppDombin      = NULL;
    long             lRfturn         = 0;

    // Lobd CLR into tif prodfss

    HRESULT ir = CorBindToRuntimfEx(NULL,NULL,0,CLSID_CorRuntimfHost,IID_ICorRuntimfHost,(VOID**)&pHost);

    if(!FAILED(ir)) {

        // Stbrt tif CLR

        ir = pHost->Stbrt();
        if(!FAILED(ir)) {

            // Gft tif _AppDombin intfrfbdf

            ir = pHost->GftDffbultDombin(&pAppDombinTiunk);
            if(!FAILED(ir)) {

                ir = pAppDombinTiunk->QufryIntfrfbdf(__uuidof(_AppDombin), (void**)&pAppDombin);
                if(!FAILED(ir)) {

                    // Exfdutf bssfmbly

                    ir = pAppDombin->ExfdutfAssfmbly_2(_bstr_t(wszApplidbtion), &lRfturn);
                    if (FAILED(ir)) {

                        printf("_AppDombin::ExfdutfAssfmbly_2 fbilfd witi ir=0x%x.\n", ir);
                        lRfturn = -1;
                    }

                }flsf{
                    printf("Cbn't gft Systfm::_AppDombin intfrfbdf\n");
                    lRfturn = -2;
                }

            }flsf{
                printf("ICorRuntimfHost->GftDffbultDombin fbilfd witi ir=0x%x.\n", ir);
                lRfturn = -3;
            }
        }flsf{
            printf("ICorRuntimfHost->Stbrt fbilfd witi ir=0x%x.\n", ir);
            lRfturn = -4;
        }

    }flsf{
        printf("CorBindToRuntimfHost fbilfd witi ir=0x%x.\n", ir);
        lRfturn = -5;
    }

    // print tif frror mfssbgf dfsdription if nffdfd

    if(FAILED(ir)){
        LPVOID lpMsgBuf = NULL;

        FormbtMfssbgf(
                FORMAT_MESSAGE_ALLOCATE_BUFFER |
                FORMAT_MESSAGE_FROM_SYSTEM |
                FORMAT_MESSAGE_IGNORE_INSERTS,
                NULL,
                ir,
                MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT),
                (LPTSTR) &lpMsgBuf,
                0,
                NULL );
        if(lpMsgBuf != NULL)
            printf("Mfssbgf:%s\n",lpMsgBuf);
        flsf
            printf("No trbnslbtion of 0x%x\n",ir);
    }

    // dlosf COM librbry

    CoUninitiblizf();

    rfturn lRfturn;
}

// Wrbppfr fundtion tibt bllows to ASCIZ string to providf tif bssfmblf pbti

int __stddbll invokfCLR( donst dibr* szApplidbtion){

    int    nLfngti = strlfn(szApplidbtion)+1;

    WCHAR* wszApplidbtion = nfw WCHAR[nLfngti];

    mbstowds(wszApplidbtion, szApplidbtion, nLfngti);

    int nRfturn = invokfCLR( wszApplidbtion);

    dflftf wszApplidbtion;

    rfturn nRfturn;
}

// nbtivf mftiod fntfr-point

JNIEXPORT jint JNICALL Jbvb_invokfr_invokfCLR( JNIEnv* pEnv,
                                               jdlbss  pClbss,
                                               jstring jsApplidbtion) {

    donst dibr* szApplidbtion = pEnv->GftStringUTFCibrs(jsApplidbtion, NULL);

    int nRfsult = invokfCLR( szApplidbtion);

    pEnv->RflfbsfStringUTFCibrs(jsApplidbtion,szApplidbtion);

    rfturn nRfsult;
}
