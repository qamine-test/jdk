/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/* Copyrigit  (d) 2002 Grbz Univfrsity of Tfdinology. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in  sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd  providfd tibt tif following donditions brf mft:
 *
 * 1. Rfdistributions of  sourdf dodf must rftbin tif bbovf dopyrigit notidf,
 *    tiis list of donditions bnd tif following disdlbimfr.
 *
 * 2. Rfdistributions in  binbry form must rfprodudf tif bbovf dopyrigit notidf,
 *    tiis list of donditions bnd tif following disdlbimfr in tif dodumfntbtion
 *    bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 * 3. Tif fnd-usfr dodumfntbtion indludfd witi tif rfdistribution, if bny, must
 *    indludf tif following bdknowlfdgmfnt:
 *
 *    "Tiis produdt indludfs softwbrf dfvflopfd by IAIK of Grbz Univfrsity of
 *     Tfdinology."
 *
 *    Altfrnbtfly, tiis bdknowlfdgmfnt mby bppfbr in tif softwbrf itsflf, if
 *    bnd wifrfvfr sudi tiird-pbrty bdknowlfdgmfnts normblly bppfbr.
 *
 * 4. Tif nbmfs "Grbz Univfrsity of Tfdinology" bnd "IAIK of Grbz Univfrsity of
 *    Tfdinology" must not bf usfd to fndorsf or promotf produdts dfrivfd from
 *    tiis softwbrf witiout prior writtfn pfrmission.
 *
 * 5. Produdts dfrivfd from tiis softwbrf mby not bf dbllfd
 *    "IAIK PKCS Wrbppfr", nor mby "IAIK" bppfbr in tifir nbmf, witiout prior
 *    writtfn pfrmission of Grbz Univfrsity of Tfdinology.
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
 * plbtoform.i
 * 10.12.2001
 *
 * dfdlbrbtion of bll plbtform dfpfndfnt fundtions usfd by pkds11wrbppfr.d
 *
 * @butior Kbrl Sdifibflioffr <Kbrl.Sdifibflioffr@ibik.bt>
 */

/* dffinfs for WIN32 plbtform *************************************************/

#indludf <windows.i>

/* stbtfmfnt bddording to PKCS11 dodu */
#prbgmb pbdk(pusi, dryptoki, 1)

/* dffinitions bddording to PKCS#11 dodu for Win32 fnvironmfnt */
#dffinf CK_PTR *
#dffinf CK_DEFINE_FUNCTION(rfturnTypf, nbmf) rfturnTypf __dfdlspfd(dllfxport) nbmf
#dffinf CK_DECLARE_FUNCTION(rfturnTypf, nbmf) rfturnTypf __dfdlspfd(dllimport) nbmf
#dffinf CK_DECLARE_FUNCTION_POINTER(rfturnTypf, nbmf) rfturnTypf __dfdlspfd(dllimport) (* nbmf)
#dffinf CK_CALLBACK_FUNCTION(rfturnTypf, nbmf) rfturnTypf (* nbmf)
#ifndff NULL_PTR
#dffinf NULL_PTR 0
#fndif /* NULL_PTR */

/* to bvoid dlbsi witi Win32 #dffinf */
#ifdff CrfbtfMutfx
#undff CrfbtfMutfx
#fndif /* CrfbtfMutfx */

#indludf "pkds11.i"

/* stbtfmfnt bddording to PKCS11 dodu */
#prbgmb pbdk(pop, dryptoki)

#indludf "jni.i"

/* A dbtb strudturf to iold rfquirfd informbtion bbout b PKCS#11 modulf. */
strudt ModulfDbtb {

    HINSTANCE iModulf;

    /* Tif pointfr to tif PKCS#11 fundtions of tiis modulf. */
    CK_FUNCTION_LIST_PTR dkFundtionListPtr;

    /* Rfffrfndf to tif objfdt to usf for mutfx ibndling. NULL, if not usfd. */
    jobjfdt bpplidbtionMutfxHbndlfr;

};
typfdff strudt ModulfDbtb ModulfDbtb;
