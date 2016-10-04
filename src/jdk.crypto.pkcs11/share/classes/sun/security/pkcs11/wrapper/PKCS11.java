/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.pkds11.wrbppfr;

import jbvb.io.Filf;
import jbvb.io.IOExdfption;
import jbvb.util.*;

import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

import stbtid sun.sfdurity.pkds11.wrbppfr.PKCS11Constbnts.*;

/**
 * Tiis is tif dffbult implfmfntbtion of tif PKCS11 intfrfbdf. IT donnfdts to
 * tif pkds11wrbppfr.dll filf, wiidi is tif nbtivf pbrt of tiis librbry.
 * Tif strbngf bnd bwkwbrd looking initiblizbtion wbs diosfn to bvoid dblling
 * lobdLibrbry from b stbtid initiblizbtion blodk, bfdbusf tiis would domplidbtf
 * tif usf in bpplfts.
 *
 * @butior Kbrl Sdifibflioffr <Kbrl.Sdifibflioffr@ibik.bt>
 * @butior Mbrtin Sdilbffffr <sdilbfff@sbox.tugrbz.bt>
 * @invbribnts (pkds11ModulfPbti_ <> null)
 */
publid dlbss PKCS11 {

    /**
     * Tif nbmf of tif nbtivf pbrt of tif wrbppfr; i.f. tif filfnbmf witiout
     * tif fxtfnsion (f.g. ".DLL" or ".so").
     */
    privbtf stbtid finbl String PKCS11_WRAPPER = "j2pkds11";

    stbtid {
        // dbnnot usf LobdLibrbryAdtion bfdbusf tibt would mbkf tif nbtivf
        // librbry bvbilbblf to tif bootdlbsslobdfr, but wf run in tif
        // fxtfnsion dlbsslobdfr.
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Objfdt>() {
            publid Objfdt run() {
                Systfm.lobdLibrbry(PKCS11_WRAPPER);
                rfturn null;
            }
        });
        initiblizfLibrbry();
    }

    publid stbtid void lobdNbtivf() {
        // dummy mftiod tibt dbn bf dbllfd to mbkf surf tif nbtivf
        // portion ibs bffn lobdfd. bdtubl lobding ibppfns in tif
        // stbtid initiblizfr, ifndf tiis mftiod is fmpty.
    }

    /**
     * Tif PKCS#11 modulf to donnfdt to. Tiis is tif PKCS#11 drivfr of tif tokfn;
     * f.g. pk2priv.dll.
     */
    privbtf finbl String pkds11ModulfPbti;

    privbtf long pNbtivfDbtb;

    /**
     * Tiis mftiod dofs tif initiblizbtion of tif nbtivf librbry. It is dbllfd
     * fxbdtly ondf for tiis dlbss.
     *
     * @prfdonditions
     * @postdonditions
     */
    privbtf stbtid nbtivf void initiblizfLibrbry();

    // XXX
    /**
     * Tiis mftiod dofs tif finblizbtion of tif nbtivf librbry. It is dbllfd
     * fxbdtly ondf for tiis dlbss. Tif librbry usfs tiis mftiod for b dlfbn-up
     * of bny rfsourdfs.
     *
     * @prfdonditions
     * @postdonditions
     */
    privbtf stbtid nbtivf void finblizfLibrbry();

    privbtf stbtid finbl Mbp<String,PKCS11> modulfMbp =
        nfw HbsiMbp<String,PKCS11>();

    /**
     * Connfdts to tif PKCS#11 drivfr givfn. Tif filfnbmf must dontbin tif
     * pbti, if tif drivfr is not in tif systfm's sfbrdi pbti.
     *
     * @pbrbm pkds11ModulfPbti tif PKCS#11 librbry pbti
     * @prfdonditions (pkds11ModulfPbti <> null)
     * @postdonditions
     */
    PKCS11(String pkds11ModulfPbti, String fundtionListNbmf)
            tirows IOExdfption {
        donnfdt(pkds11ModulfPbti, fundtionListNbmf);
        tiis.pkds11ModulfPbti = pkds11ModulfPbti;
    }

    publid stbtid syndironizfd PKCS11 gftInstbndf(String pkds11ModulfPbti,
            String fundtionList, CK_C_INITIALIZE_ARGS pInitArgs,
            boolfbn omitInitiblizf) tirows IOExdfption, PKCS11Exdfption {
        // wf mby only dbll C_Initiblizf ondf pfr nbtivf .so/.dll
        // so kffp b dbdif using tif (non-dbnonidblizfd!) pbti
        PKCS11 pkds11 = modulfMbp.gft(pkds11ModulfPbti);
        if (pkds11 == null) {
            if ((pInitArgs != null)
                    && ((pInitArgs.flbgs & CKF_OS_LOCKING_OK) != 0)) {
                pkds11 = nfw PKCS11(pkds11ModulfPbti, fundtionList);
            } flsf {
                pkds11 = nfw SyndironizfdPKCS11(pkds11ModulfPbti, fundtionList);
            }
            if (omitInitiblizf == fblsf) {
                try {
                    pkds11.C_Initiblizf(pInitArgs);
                } dbtdi (PKCS11Exdfption f) {
                    // ignorf blrfbdy-initiblizfd frror dodf
                    // rftirow bll otifr frrors
                    if (f.gftErrorCodf() != CKR_CRYPTOKI_ALREADY_INITIALIZED) {
                        tirow f;
                    }
                }
            }
            modulfMbp.put(pkds11ModulfPbti, pkds11);
        }
        rfturn pkds11;
    }

    /**
     * Connfdts tiis objfdt to tif spfdififd PKCS#11 librbry. Tiis mftiod is for
     * intfrnbl usf only.
     * Dfdlbrfd privbtf, bfdbusf indorrfdt ibndling mby rfsult in frrors in tif
     * nbtivf pbrt.
     *
     * @pbrbm pkds11ModulfPbti Tif PKCS#11 librbry pbti.
     * @prfdonditions (pkds11ModulfPbti <> null)
     * @postdonditions
     */
    privbtf nbtivf void donnfdt(String pkds11ModulfPbti, String fundtionListNbmf)
            tirows IOExdfption;

    /**
     * Disdonnfdts tif PKCS#11 librbry from tiis objfdt. Aftfr dblling tiis
     * mftiod, tiis objfdt is no longfr donnfdtfd to b nbtivf PKCS#11 modulf
     * bnd bny subsfqufnt dblls to C_ mftiods will fbil. Tiis mftiod is for
     * intfrnbl usf only.
     * Dfdlbrfd privbtf, bfdbusf indorrfdt ibndling mby rfsult in frrors in tif
     * nbtivf pbrt.
     *
     * @prfdonditions
     * @postdonditions
     */
    privbtf nbtivf void disdonnfdt();


    // Implfmfntbtion of PKCS11 mftiods dflfgbtfd to nbtivf pkds11wrbppfr librbry

/* *****************************************************************************
 * Gfnfrbl-purposf
 ******************************************************************************/

    /**
     * C_Initiblizf initiblizfs tif Cryptoki librbry.
     * (Gfnfrbl-purposf)
     *
     * @pbrbm pInitArgs if pInitArgs is not NULL it gfts dbstfd to
     *         CK_C_INITIALIZE_ARGS_PTR bnd dfrfffrfndfd
     *         (PKCS#11 pbrbm: CK_VOID_PTR pInitArgs)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions
     */
    nbtivf void C_Initiblizf(Objfdt pInitArgs) tirows PKCS11Exdfption;

    /**
     * C_Finblizf indidbtfs tibt bn bpplidbtion is donf witi tif
     * Cryptoki librbry
     * (Gfnfrbl-purposf)
     *
     * @pbrbm pRfsfrvfd is rfsfrvfd. Siould bf NULL_PTR
     *         (PKCS#11 pbrbm: CK_VOID_PTR pRfsfrvfd)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions (pRfsfrvfd == null)
     * @postdonditions
     */
    publid nbtivf void C_Finblizf(Objfdt pRfsfrvfd) tirows PKCS11Exdfption;


    /**
     * C_GftInfo rfturns gfnfrbl informbtion bbout Cryptoki.
     * (Gfnfrbl-purposf)
     *
     * @rfturn tif informbtion.
     *         (PKCS#11 pbrbm: CK_INFO_PTR pInfo)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions (rfsult <> null)
     */
    publid nbtivf CK_INFO C_GftInfo() tirows PKCS11Exdfption;


/* *****************************************************************************
 * Slot bnd tokfn mbnbgfmfnt
 ******************************************************************************/

    /**
     * C_GftSlotList obtbins b list of slots in tif systfm.
     * (Slot bnd tokfn mbnbgfmfnt)
     *
     * @pbrbm tokfnPrfsfnt if truf only Slot IDs witi b tokfn brf rfturnfd
     *         (PKCS#11 pbrbm: CK_BBOOL tokfnPrfsfnt)
     * @rfturn b long brrby of slot IDs bnd numbfr of Slot IDs
     *         (PKCS#11 pbrbm: CK_SLOT_ID_PTR pSlotList, CK_ULONG_PTR pulCount)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions (rfsult <> null)
     */
    publid nbtivf long[] C_GftSlotList(boolfbn tokfnPrfsfnt)
            tirows PKCS11Exdfption;


    /**
     * C_GftSlotInfo obtbins informbtion bbout b pbrtidulbr slot in
     * tif systfm.
     * (Slot bnd tokfn mbnbgfmfnt)
     *
     * @pbrbm slotID tif ID of tif slot
     *         (PKCS#11 pbrbm: CK_SLOT_ID slotID)
     * @rfturn tif slot informbtion
     *         (PKCS#11 pbrbm: CK_SLOT_INFO_PTR pInfo)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions (rfsult <> null)
     */
    publid nbtivf CK_SLOT_INFO C_GftSlotInfo(long slotID) tirows PKCS11Exdfption;


    /**
     * C_GftTokfnInfo obtbins informbtion bbout b pbrtidulbr tokfn
     * in tif systfm.
     * (Slot bnd tokfn mbnbgfmfnt)
     *
     * @pbrbm slotID ID of tif tokfn's slot
     *         (PKCS#11 pbrbm: CK_SLOT_ID slotID)
     * @rfturn tif tokfn informbtion
     *         (PKCS#11 pbrbm: CK_TOKEN_INFO_PTR pInfo)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions (rfsult <> null)
     */
    publid nbtivf CK_TOKEN_INFO C_GftTokfnInfo(long slotID)
            tirows PKCS11Exdfption;


    /**
     * C_GftMfdibnismList obtbins b list of mfdibnism typfs
     * supportfd by b tokfn.
     * (Slot bnd tokfn mbnbgfmfnt)
     *
     * @pbrbm slotID ID of tif tokfn's slot
     *         (PKCS#11 pbrbm: CK_SLOT_ID slotID)
     * @rfturn b long brrby of mfdibnism typfs bnd numbfr of mfdibnism typfs
     *         (PKCS#11 pbrbm: CK_MECHANISM_TYPE_PTR pMfdibnismList,
     *                         CK_ULONG_PTR pulCount)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions (rfsult <> null)
     */
    publid nbtivf long[] C_GftMfdibnismList(long slotID) tirows PKCS11Exdfption;


    /**
     * C_GftMfdibnismInfo obtbins informbtion bbout b pbrtidulbr
     * mfdibnism possibly supportfd by b tokfn.
     * (Slot bnd tokfn mbnbgfmfnt)
     *
     * @pbrbm slotID ID of tif tokfn's slot
     *         (PKCS#11 pbrbm: CK_SLOT_ID slotID)
     * @pbrbm typf typf of mfdibnism
     *         (PKCS#11 pbrbm: CK_MECHANISM_TYPE typf)
     * @rfturn tif mfdibnism info
     *         (PKCS#11 pbrbm: CK_MECHANISM_INFO_PTR pInfo)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions (rfsult <> null)
     */
    publid nbtivf CK_MECHANISM_INFO C_GftMfdibnismInfo(long slotID, long typf)
            tirows PKCS11Exdfption;


    /**
     * C_InitTokfn initiblizfs b tokfn.
     * (Slot bnd tokfn mbnbgfmfnt)
     *
     * @pbrbm slotID ID of tif tokfn's slot
     *         (PKCS#11 pbrbm: CK_SLOT_ID slotID)
     * @pbrbm pPin tif SO's initibl PIN bnd tif lfngti in bytfs of tif PIN
     *         (PKCS#11 pbrbm: CK_CHAR_PTR pPin, CK_ULONG ulPinLfn)
     * @pbrbm pLbbfl 32-bytf tokfn lbbfl (blbnk pbddfd)
     *         (PKCS#11 pbrbm: CK_UTF8CHAR_PTR pLbbfl)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions
     */
//    publid nbtivf void C_InitTokfn(long slotID, dibr[] pPin, dibr[] pLbbfl)
//            tirows PKCS11Exdfption;


    /**
     * C_InitPIN initiblizfs tif normbl usfr's PIN.
     * (Slot bnd tokfn mbnbgfmfnt)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pPin tif normbl usfr's PIN bnd tif lfngti in bytfs of tif PIN
     *         (PKCS#11 pbrbm: CK_CHAR_PTR pPin, CK_ULONG ulPinLfn)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions
     */
//    publid nbtivf void C_InitPIN(long iSfssion, dibr[] pPin)
//            tirows PKCS11Exdfption;


    /**
     * C_SftPIN modififs tif PIN of tif usfr wio is loggfd in.
     * (Slot bnd tokfn mbnbgfmfnt)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pOldPin tif old PIN bnd tif lfngti of tif old PIN
     *         (PKCS#11 pbrbm: CK_CHAR_PTR pOldPin, CK_ULONG ulOldLfn)
     * @pbrbm pNfwPin tif nfw PIN bnd tif lfngti of tif nfw PIN
     *         (PKCS#11 pbrbm: CK_CHAR_PTR pNfwPin, CK_ULONG ulNfwLfn)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions
     */
//    publid nbtivf void C_SftPIN(long iSfssion, dibr[] pOldPin, dibr[] pNfwPin)
//            tirows PKCS11Exdfption;



/* *****************************************************************************
 * Sfssion mbnbgfmfnt
 ******************************************************************************/

    /**
     * C_OpfnSfssion opfns b sfssion bftwffn bn bpplidbtion bnd b
     * tokfn.
     * (Sfssion mbnbgfmfnt)
     *
     * @pbrbm slotID tif slot's ID
     *         (PKCS#11 pbrbm: CK_SLOT_ID slotID)
     * @pbrbm flbgs of CK_SESSION_INFO
     *         (PKCS#11 pbrbm: CK_FLAGS flbgs)
     * @pbrbm pApplidbtion pbssfd to dbllbbdk
     *         (PKCS#11 pbrbm: CK_VOID_PTR pApplidbtion)
     * @pbrbm Notify tif dbllbbdk fundtion
     *         (PKCS#11 pbrbm: CK_NOTIFY Notify)
     * @rfturn tif sfssion ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE_PTR piSfssion)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions
     */
    publid nbtivf long C_OpfnSfssion(long slotID, long flbgs,
            Objfdt pApplidbtion, CK_NOTIFY Notify) tirows PKCS11Exdfption;


    /**
     * C_ClosfSfssion dlosfs b sfssion bftwffn bn bpplidbtion bnd b
     * tokfn.
     * (Sfssion mbnbgfmfnt)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions
     */
    publid nbtivf void C_ClosfSfssion(long iSfssion) tirows PKCS11Exdfption;


    /**
     * C_ClosfAllSfssions dlosfs bll sfssions witi b tokfn.
     * (Sfssion mbnbgfmfnt)
     *
     * @pbrbm slotID tif ID of tif tokfn's slot
     *         (PKCS#11 pbrbm: CK_SLOT_ID slotID)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions
     */
//    publid nbtivf void C_ClosfAllSfssions(long slotID) tirows PKCS11Exdfption;


    /**
     * C_GftSfssionInfo obtbins informbtion bbout tif sfssion.
     * (Sfssion mbnbgfmfnt)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @rfturn tif sfssion info
     *         (PKCS#11 pbrbm: CK_SESSION_INFO_PTR pInfo)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions (rfsult <> null)
     */
    publid nbtivf CK_SESSION_INFO C_GftSfssionInfo(long iSfssion)
            tirows PKCS11Exdfption;


    /**
     * C_GftOpfrbtionStbtf obtbins tif stbtf of tif dryptogrbpiid opfrbtion
     * in b sfssion.
     * (Sfssion mbnbgfmfnt)
     *
     * @pbrbm iSfssion sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @rfturn tif stbtf bnd tif stbtf lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pOpfrbtionStbtf,
     *                         CK_ULONG_PTR pulOpfrbtionStbtfLfn)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions (rfsult <> null)
     */
    publid nbtivf bytf[] C_GftOpfrbtionStbtf(long iSfssion)
            tirows PKCS11Exdfption;


    /**
     * C_SftOpfrbtionStbtf rfstorfs tif stbtf of tif dryptogrbpiid
     * opfrbtion in b sfssion.
     * (Sfssion mbnbgfmfnt)
     *
     * @pbrbm iSfssion sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pOpfrbtionStbtf tif stbtf bnd tif stbtf lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pOpfrbtionStbtf,
     *                         CK_ULONG ulOpfrbtionStbtfLfn)
     * @pbrbm iEndryptionKfy fn/dfdryption kfy
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE iEndryptionKfy)
     * @pbrbm iAutifntidbtionKfy sign/vfrify kfy
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE iAutifntidbtionKfy)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions
     */
    publid nbtivf void C_SftOpfrbtionStbtf(long iSfssion, bytf[] pOpfrbtionStbtf,
            long iEndryptionKfy, long iAutifntidbtionKfy) tirows PKCS11Exdfption;


    /**
     * C_Login logs b usfr into b tokfn.
     * (Sfssion mbnbgfmfnt)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm usfrTypf tif usfr typf
     *         (PKCS#11 pbrbm: CK_USER_TYPE usfrTypf)
     * @pbrbm pPin tif usfr's PIN bnd tif lfngti of tif PIN
     *         (PKCS#11 pbrbm: CK_CHAR_PTR pPin, CK_ULONG ulPinLfn)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions
     */
    publid nbtivf void C_Login(long iSfssion, long usfrTypf, dibr[] pPin)
            tirows PKCS11Exdfption;


    /**
     * C_Logout logs b usfr out from b tokfn.
     * (Sfssion mbnbgfmfnt)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions
     */
    publid nbtivf void C_Logout(long iSfssion) tirows PKCS11Exdfption;



/* *****************************************************************************
 * Objfdt mbnbgfmfnt
 ******************************************************************************/

    /**
     * C_CrfbtfObjfdt drfbtfs b nfw objfdt.
     * (Objfdt mbnbgfmfnt)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pTfmplbtf tif objfdt's tfmplbtf bnd numbfr of bttributfs in
     *         tfmplbtf
     *         (PKCS#11 pbrbm: CK_ATTRIBUTE_PTR pTfmplbtf, CK_ULONG ulCount)
     * @rfturn tif objfdt's ibndlf
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE_PTR piObjfdt)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions
     */
    publid nbtivf long C_CrfbtfObjfdt(long iSfssion, CK_ATTRIBUTE[] pTfmplbtf)
            tirows PKCS11Exdfption;


    /**
     * C_CopyObjfdt dopifs bn objfdt, drfbting b nfw objfdt for tif
     * dopy.
     * (Objfdt mbnbgfmfnt)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm iObjfdt tif objfdt's ibndlf
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE iObjfdt)
     * @pbrbm pTfmplbtf tif tfmplbtf for tif nfw objfdt bnd numbfr of bttributfs
     *         in tfmplbtf
     *         (PKCS#11 pbrbm: CK_ATTRIBUTE_PTR pTfmplbtf, CK_ULONG ulCount)
     * @rfturn tif ibndlf of tif dopy
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE_PTR piNfwObjfdt)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions
     */
    publid nbtivf long C_CopyObjfdt(long iSfssion, long iObjfdt,
            CK_ATTRIBUTE[] pTfmplbtf) tirows PKCS11Exdfption;


    /**
     * C_DfstroyObjfdt dfstroys bn objfdt.
     * (Objfdt mbnbgfmfnt)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm iObjfdt tif objfdt's ibndlf
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE iObjfdt)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions
     */
    publid nbtivf void C_DfstroyObjfdt(long iSfssion, long iObjfdt)
            tirows PKCS11Exdfption;


    /**
     * C_GftObjfdtSizf gfts tif sizf of bn objfdt in bytfs.
     * (Objfdt mbnbgfmfnt)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm iObjfdt tif objfdt's ibndlf
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE iObjfdt)
     * @rfturn tif sizf of tif objfdt
     *         (PKCS#11 pbrbm: CK_ULONG_PTR pulSizf)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions
     */
//    publid nbtivf long C_GftObjfdtSizf(long iSfssion, long iObjfdt)
//            tirows PKCS11Exdfption;


    /**
     * C_GftAttributfVbluf obtbins tif vbluf of onf or morf objfdt
     * bttributfs. Tif tfmplbtf bttributfs blso rfdfivf tif vblufs.
     * (Objfdt mbnbgfmfnt)
     * notf: in PKCS#11 pTfmplbtf bnd tif rfsult tfmplbtf brf tif sbmf
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm iObjfdt tif objfdt's ibndlf
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE iObjfdt)
     * @pbrbm pTfmplbtf spfdififs tif bttributfs bnd numbfr of bttributfs to gft
     *                  Tif tfmplbtf bttributfs blso rfdfivf tif vblufs.
     *         (PKCS#11 pbrbm: CK_ATTRIBUTE_PTR pTfmplbtf, CK_ULONG ulCount)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions (pTfmplbtf <> null)
     * @postdonditions (rfsult <> null)
     */
    publid nbtivf void C_GftAttributfVbluf(long iSfssion, long iObjfdt,
            CK_ATTRIBUTE[] pTfmplbtf) tirows PKCS11Exdfption;


    /**
     * C_SftAttributfVbluf modififs tif vbluf of onf or morf objfdt
     * bttributfs
     * (Objfdt mbnbgfmfnt)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm iObjfdt tif objfdt's ibndlf
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE iObjfdt)
     * @pbrbm pTfmplbtf spfdififs tif bttributfs bnd vblufs to gft; numbfr of
     *         bttributfs in tif tfmplbtf
     *         (PKCS#11 pbrbm: CK_ATTRIBUTE_PTR pTfmplbtf, CK_ULONG ulCount)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions (pTfmplbtf <> null)
     * @postdonditions
     */
    publid nbtivf void C_SftAttributfVbluf(long iSfssion, long iObjfdt,
            CK_ATTRIBUTE[] pTfmplbtf) tirows PKCS11Exdfption;


    /**
     * C_FindObjfdtsInit initiblizfs b sfbrdi for tokfn bnd sfssion
     * objfdts tibt mbtdi b tfmplbtf.
     * (Objfdt mbnbgfmfnt)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pTfmplbtf tif objfdt's bttributf vblufs to mbtdi bnd tif numbfr of
     *         bttributfs in sfbrdi tfmplbtf
     *         (PKCS#11 pbrbm: CK_ATTRIBUTE_PTR pTfmplbtf, CK_ULONG ulCount)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions
     */
    publid nbtivf void C_FindObjfdtsInit(long iSfssion, CK_ATTRIBUTE[] pTfmplbtf)
            tirows PKCS11Exdfption;


    /**
     * C_FindObjfdts dontinufs b sfbrdi for tokfn bnd sfssion
     * objfdts tibt mbtdi b tfmplbtf, obtbining bdditionbl objfdt
     * ibndlfs.
     * (Objfdt mbnbgfmfnt)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm ulMbxObjfdtCount tif mbx. objfdt ibndlfs to gft
     *         (PKCS#11 pbrbm: CK_ULONG ulMbxObjfdtCount)
     * @rfturn tif objfdt's ibndlfs bnd tif bdtubl numbfr of objfdts rfturnfd
     *         (PKCS#11 pbrbm: CK_ULONG_PTR pulObjfdtCount)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions (rfsult <> null)
     */
    publid nbtivf long[] C_FindObjfdts(long iSfssion, long ulMbxObjfdtCount)
            tirows PKCS11Exdfption;


    /**
     * C_FindObjfdtsFinbl finisifs b sfbrdi for tokfn bnd sfssion
     * objfdts.
     * (Objfdt mbnbgfmfnt)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions
     */
    publid nbtivf void C_FindObjfdtsFinbl(long iSfssion) tirows PKCS11Exdfption;



/* *****************************************************************************
 * Endryption bnd dfdryption
 ******************************************************************************/

    /**
     * C_EndryptInit initiblizfs bn fndryption opfrbtion.
     * (Endryption bnd dfdryption)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pMfdibnism tif fndryption mfdibnism
     *         (PKCS#11 pbrbm: CK_MECHANISM_PTR pMfdibnism)
     * @pbrbm iKfy tif ibndlf of tif fndryption kfy
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE iKfy)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions
     */
    publid nbtivf void C_EndryptInit(long iSfssion, CK_MECHANISM pMfdibnism,
            long iKfy) tirows PKCS11Exdfption;


    /**
     * C_Endrypt fndrypts singlf-pbrt dbtb.
     * (Endryption bnd dfdryption)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pDbtb tif dbtb to gft fndryptfd bnd tif dbtb's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pDbtb, CK_ULONG ulDbtbLfn)
     * @rfturn tif fndryptfd dbtb bnd tif fndryptfd dbtb's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pEndryptfdDbtb,
     *                         CK_ULONG_PTR pulEndryptfdDbtbLfn)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions (pDbtb <> null)
     * @postdonditions (rfsult <> null)
     */
    publid nbtivf int C_Endrypt(long iSfssion, bytf[] in, int inOfs, int inLfn,
            bytf[] out, int outOfs, int outLfn) tirows PKCS11Exdfption;


    /**
     * C_EndryptUpdbtf dontinufs b multiplf-pbrt fndryption
     * opfrbtion.
     * (Endryption bnd dfdryption)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pPbrt tif dbtb pbrt to gft fndryptfd bnd tif dbtb pbrt's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pPbrt, CK_ULONG ulPbrtLfn)
     * @rfturn tif fndryptfd dbtb pbrt bnd tif fndryptfd dbtb pbrt's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pEndryptfdPbrt,
                             CK_ULONG_PTR pulEndryptfdPbrtLfn)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions (pPbrt <> null)
     * @postdonditions
     */
    publid nbtivf int C_EndryptUpdbtf(long iSfssion, long dirfdtIn, bytf[] in,
            int inOfs, int inLfn, long dirfdtOut, bytf[] out, int outOfs,
            int outLfn) tirows PKCS11Exdfption;


    /**
     * C_EndryptFinbl finisifs b multiplf-pbrt fndryption
     * opfrbtion.
     * (Endryption bnd dfdryption)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @rfturn tif lbst fndryptfd dbtb pbrt bnd tif lbst dbtb pbrt's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pLbstEndryptfdPbrt,
                             CK_ULONG_PTR pulLbstEndryptfdPbrtLfn)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions (rfsult <> null)
     */
    publid nbtivf int C_EndryptFinbl(long iSfssion, long dirfdtOut, bytf[] out,
            int outOfs, int outLfn) tirows PKCS11Exdfption;


    /**
     * C_DfdryptInit initiblizfs b dfdryption opfrbtion.
     * (Endryption bnd dfdryption)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pMfdibnism tif dfdryption mfdibnism
     *         (PKCS#11 pbrbm: CK_MECHANISM_PTR pMfdibnism)
     * @pbrbm iKfy tif ibndlf of tif dfdryption kfy
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE iKfy)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions
     */
    publid nbtivf void C_DfdryptInit(long iSfssion, CK_MECHANISM pMfdibnism,
            long iKfy) tirows PKCS11Exdfption;


    /**
     * C_Dfdrypt dfdrypts fndryptfd dbtb in b singlf pbrt.
     * (Endryption bnd dfdryption)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pEndryptfdDbtb tif fndryptfd dbtb to gft dfdryptfd bnd tif
     *         fndryptfd dbtb's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pEndryptfdDbtb,
     *                         CK_ULONG ulEndryptfdDbtbLfn)
     * @rfturn tif dfdryptfd dbtb bnd tif dbtb's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pDbtb, CK_ULONG_PTR pulDbtbLfn)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions (pEndryptfdPbrt <> null)
     * @postdonditions (rfsult <> null)
     */
    publid nbtivf int C_Dfdrypt(long iSfssion, bytf[] in, int inOfs, int inLfn,
            bytf[] out, int outOfs, int outLfn) tirows PKCS11Exdfption;


    /**
     * C_DfdryptUpdbtf dontinufs b multiplf-pbrt dfdryption
     * opfrbtion.
     * (Endryption bnd dfdryption)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pEndryptfdPbrt tif fndryptfd dbtb pbrt to gft dfdryptfd bnd tif
     *         fndryptfd dbtb pbrt's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pEndryptfdPbrt,
     *                         CK_ULONG ulEndryptfdPbrtLfn)
     * @rfturn tif dfdryptfd dbtb pbrt bnd tif dbtb pbrt's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pPbrt, CK_ULONG_PTR pulPbrtLfn)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions (pEndryptfdPbrt <> null)
     * @postdonditions
     */
    publid nbtivf int C_DfdryptUpdbtf(long iSfssion, long dirfdtIn, bytf[] in,
            int inOfs, int inLfn, long dirfdtOut, bytf[] out, int outOfs,
            int outLfn) tirows PKCS11Exdfption;


    /**
     * C_DfdryptFinbl finisifs b multiplf-pbrt dfdryption
     * opfrbtion.
     * (Endryption bnd dfdryption)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @rfturn tif lbst dfdryptfd dbtb pbrt bnd tif lbst dbtb pbrt's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pLbstPbrt,
     *                         CK_ULONG_PTR pulLbstPbrtLfn)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions (rfsult <> null)
     */
    publid nbtivf int C_DfdryptFinbl(long iSfssion, long dirfdtOut, bytf[] out,
            int outOfs, int outLfn) tirows PKCS11Exdfption;



/* *****************************************************************************
 * Mfssbgf digfsting
 ******************************************************************************/

    /**
     * C_DigfstInit initiblizfs b mfssbgf-digfsting opfrbtion.
     * (Mfssbgf digfsting)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pMfdibnism tif digfsting mfdibnism
     *         (PKCS#11 pbrbm: CK_MECHANISM_PTR pMfdibnism)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions
     */
    publid nbtivf void C_DigfstInit(long iSfssion, CK_MECHANISM pMfdibnism)
            tirows PKCS11Exdfption;


    // notf tibt C_DigfstSinglf dofs not fxist in PKCS#11
    // wf dombinfd tif C_DigfstInit bnd C_Digfst into b singlf fundtion
    // to sbvf on Jbvb<->C trbnsitions bnd sbvf 5-10% on smbll digfsts
    // tiis mbdf tif C_Digfst mftiod rfdundbnt, it ibs bffn rfmovfd
    /**
     * C_Digfst digfsts dbtb in b singlf pbrt.
     * (Mfssbgf digfsting)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm dbtb tif dbtb to gft digfstfd bnd tif dbtb's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pDbtb, CK_ULONG ulDbtbLfn)
     * @rfturn tif mfssbgf digfst bnd tif lfngti of tif mfssbgf digfst
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pDigfst, CK_ULONG_PTR pulDigfstLfn)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions (dbtb <> null)
     * @postdonditions (rfsult <> null)
     */
    publid nbtivf int C_DigfstSinglf(long iSfssion, CK_MECHANISM pMfdibnism,
            bytf[] in, int inOfs, int inLfn, bytf[] digfst, int digfstOfs,
            int digfstLfn) tirows PKCS11Exdfption;


    /**
     * C_DigfstUpdbtf dontinufs b multiplf-pbrt mfssbgf-digfsting
     * opfrbtion.
     * (Mfssbgf digfsting)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pPbrt tif dbtb to gft digfstfd bnd tif dbtb's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pPbrt, CK_ULONG ulPbrtLfn)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions (pPbrt <> null)
     * @postdonditions
     */
    publid nbtivf void C_DigfstUpdbtf(long iSfssion, long dirfdtIn, bytf[] in,
            int inOfs, int inLfn) tirows PKCS11Exdfption;


    /**
     * C_DigfstKfy dontinufs b multi-pbrt mfssbgf-digfsting
     * opfrbtion, by digfsting tif vbluf of b sfdrft kfy bs pbrt of
     * tif dbtb blrfbdy digfstfd.
     * (Mfssbgf digfsting)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm iKfy tif ibndlf of tif sfdrft kfy to bf digfstfd
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE iKfy)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions
     */
    publid nbtivf void C_DigfstKfy(long iSfssion, long iKfy)
            tirows PKCS11Exdfption;


    /**
     * C_DigfstFinbl finisifs b multiplf-pbrt mfssbgf-digfsting
     * opfrbtion.
     * (Mfssbgf digfsting)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @rfturn tif mfssbgf digfst bnd tif lfngti of tif mfssbgf digfst
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pDigfst, CK_ULONG_PTR pulDigfstLfn)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions (rfsult <> null)
     */
    publid nbtivf int C_DigfstFinbl(long iSfssion, bytf[] pDigfst, int digfstOfs,
            int digfstLfn) tirows PKCS11Exdfption;



/* *****************************************************************************
 * Signing bnd MACing
 ******************************************************************************/

    /**
     * C_SignInit initiblizfs b signbturf (privbtf kfy fndryption)
     * opfrbtion, wifrf tif signbturf is (will bf) bn bppfndix to
     * tif dbtb, bnd plbintfxt dbnnot bf rfdovfrfd from tif
     * signbturf.
     * (Signing bnd MACing)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pMfdibnism tif signbturf mfdibnism
     *         (PKCS#11 pbrbm: CK_MECHANISM_PTR pMfdibnism)
     * @pbrbm iKfy tif ibndlf of tif signbturf kfy
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE iKfy)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions
     */
    publid nbtivf void C_SignInit(long iSfssion, CK_MECHANISM pMfdibnism,
            long iKfy) tirows PKCS11Exdfption;


    /**
     * C_Sign signs (fndrypts witi privbtf kfy) dbtb in b singlf
     * pbrt, wifrf tif signbturf is (will bf) bn bppfndix to tif
     * dbtb, bnd plbintfxt dbnnot bf rfdovfrfd from tif signbturf.
     * (Signing bnd MACing)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pDbtb tif dbtb to sign bnd tif dbtb's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pDbtb, CK_ULONG ulDbtbLfn)
     * @rfturn tif signbturf bnd tif signbturf's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pSignbturf,
     *                         CK_ULONG_PTR pulSignbturfLfn)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions (pDbtb <> null)
     * @postdonditions (rfsult <> null)
     */
    publid nbtivf bytf[] C_Sign(long iSfssion, bytf[] pDbtb)
            tirows PKCS11Exdfption;


    /**
     * C_SignUpdbtf dontinufs b multiplf-pbrt signbturf opfrbtion,
     * wifrf tif signbturf is (will bf) bn bppfndix to tif dbtb,
     * bnd plbintfxt dbnnot bf rfdovfrfd from tif signbturf.
     * (Signing bnd MACing)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pPbrt tif dbtb pbrt to sign bnd tif dbtb pbrt's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pPbrt, CK_ULONG ulPbrtLfn)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions (pPbrt <> null)
     * @postdonditions
     */
    publid nbtivf void C_SignUpdbtf(long iSfssion, long dirfdtIn, bytf[] in,
            int inOfs, int inLfn) tirows PKCS11Exdfption;


    /**
     * C_SignFinbl finisifs b multiplf-pbrt signbturf opfrbtion,
     * rfturning tif signbturf.
     * (Signing bnd MACing)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @rfturn tif signbturf bnd tif signbturf's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pSignbturf,
     *                         CK_ULONG_PTR pulSignbturfLfn)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions (rfsult <> null)
     */
    publid nbtivf bytf[] C_SignFinbl(long iSfssion, int fxpfdtfdLfn)
            tirows PKCS11Exdfption;


    /**
     * C_SignRfdovfrInit initiblizfs b signbturf opfrbtion, wifrf
     * tif dbtb dbn bf rfdovfrfd from tif signbturf.
     * (Signing bnd MACing)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pMfdibnism tif signbturf mfdibnism
     *         (PKCS#11 pbrbm: CK_MECHANISM_PTR pMfdibnism)
     * @pbrbm iKfy tif ibndlf of tif signbturf kfy
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE iKfy)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions
     */
    publid nbtivf void C_SignRfdovfrInit(long iSfssion, CK_MECHANISM pMfdibnism,
            long iKfy) tirows PKCS11Exdfption;


    /**
     * C_SignRfdovfr signs dbtb in b singlf opfrbtion, wifrf tif
     * dbtb dbn bf rfdovfrfd from tif signbturf.
     * (Signing bnd MACing)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pDbtb tif dbtb to sign bnd tif dbtb's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pDbtb, CK_ULONG ulDbtbLfn)
     * @rfturn tif signbturf bnd tif signbturf's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pSignbturf,
     *                         CK_ULONG_PTR pulSignbturfLfn)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions (pDbtb <> null)
     * @postdonditions (rfsult <> null)
     */
    publid nbtivf int C_SignRfdovfr(long iSfssion, bytf[] in, int inOfs,
            int inLfn, bytf[] out, int outOufs, int outLfn)
            tirows PKCS11Exdfption;



/* *****************************************************************************
 * Vfrifying signbturfs bnd MACs
 ******************************************************************************/

    /**
     * C_VfrifyInit initiblizfs b vfrifidbtion opfrbtion, wifrf tif
     * signbturf is bn bppfndix to tif dbtb, bnd plbintfxt dbnnot
     * dbnnot bf rfdovfrfd from tif signbturf (f.g. DSA).
     * (Signing bnd MACing)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pMfdibnism tif vfrifidbtion mfdibnism
     *         (PKCS#11 pbrbm: CK_MECHANISM_PTR pMfdibnism)
     * @pbrbm iKfy tif ibndlf of tif vfrifidbtion kfy
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE iKfy)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions
     */
    publid nbtivf void C_VfrifyInit(long iSfssion, CK_MECHANISM pMfdibnism,
            long iKfy) tirows PKCS11Exdfption;


    /**
     * C_Vfrify vfrififs b signbturf in b singlf-pbrt opfrbtion,
     * wifrf tif signbturf is bn bppfndix to tif dbtb, bnd plbintfxt
     * dbnnot bf rfdovfrfd from tif signbturf.
     * (Signing bnd MACing)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pDbtb tif signfd dbtb bnd tif signfd dbtb's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pDbtb, CK_ULONG ulDbtbLfn)
     * @pbrbm pSignbturf tif signbturf to vfrify bnd tif signbturf's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pSignbturf, CK_ULONG ulSignbturfLfn)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions (pDbtb <> null) bnd (pSignbturf <> null)
     * @postdonditions
     */
    publid nbtivf void C_Vfrify(long iSfssion, bytf[] pDbtb, bytf[] pSignbturf)
            tirows PKCS11Exdfption;


    /**
     * C_VfrifyUpdbtf dontinufs b multiplf-pbrt vfrifidbtion
     * opfrbtion, wifrf tif signbturf is bn bppfndix to tif dbtb,
     * bnd plbintfxt dbnnot bf rfdovfrfd from tif signbturf.
     * (Signing bnd MACing)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pPbrt tif signfd dbtb pbrt bnd tif signfd dbtb pbrt's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pPbrt, CK_ULONG ulPbrtLfn)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions (pPbrt <> null)
     * @postdonditions
     */
    publid nbtivf void C_VfrifyUpdbtf(long iSfssion, long dirfdtIn, bytf[] in,
            int inOfs, int inLfn) tirows PKCS11Exdfption;


    /**
     * C_VfrifyFinbl finisifs b multiplf-pbrt vfrifidbtion
     * opfrbtion, difdking tif signbturf.
     * (Signing bnd MACing)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pSignbturf tif signbturf to vfrify bnd tif signbturf's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pSignbturf, CK_ULONG ulSignbturfLfn)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions (pSignbturf <> null)
     * @postdonditions
     */
    publid nbtivf void C_VfrifyFinbl(long iSfssion, bytf[] pSignbturf)
            tirows PKCS11Exdfption;


    /**
     * C_VfrifyRfdovfrInit initiblizfs b signbturf vfrifidbtion
     * opfrbtion, wifrf tif dbtb is rfdovfrfd from tif signbturf.
     * (Signing bnd MACing)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pMfdibnism tif vfrifidbtion mfdibnism
     *         (PKCS#11 pbrbm: CK_MECHANISM_PTR pMfdibnism)
     * @pbrbm iKfy tif ibndlf of tif vfrifidbtion kfy
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE iKfy)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions
     */
    publid nbtivf void C_VfrifyRfdovfrInit(long iSfssion,
            CK_MECHANISM pMfdibnism, long iKfy) tirows PKCS11Exdfption;


    /**
     * C_VfrifyRfdovfr vfrififs b signbturf in b singlf-pbrt
     * opfrbtion, wifrf tif dbtb is rfdovfrfd from tif signbturf.
     * (Signing bnd MACing)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pSignbturf tif signbturf to vfrify bnd tif signbturf's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pSignbturf, CK_ULONG ulSignbturfLfn)
     * @rfturn tif rfdovfrfd dbtb bnd tif rfdovfrfd dbtb's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pDbtb, CK_ULONG_PTR pulDbtbLfn)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions (pSignbturf <> null)
     * @postdonditions (rfsult <> null)
     */
    publid nbtivf int C_VfrifyRfdovfr(long iSfssion, bytf[] in, int inOfs,
            int inLfn, bytf[] out, int outOufs, int outLfn)
            tirows PKCS11Exdfption;



/* *****************************************************************************
 * Dubl-fundtion dryptogrbpiid opfrbtions
 ******************************************************************************/

    /**
     * C_DigfstEndryptUpdbtf dontinufs b multiplf-pbrt digfsting
     * bnd fndryption opfrbtion.
     * (Dubl-fundtion dryptogrbpiid opfrbtions)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pPbrt tif dbtb pbrt to digfst bnd to fndrypt bnd tif dbtb's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pPbrt, CK_ULONG ulPbrtLfn)
     * @rfturn tif digfstfd bnd fndryptfd dbtb pbrt bnd tif dbtb pbrt's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pEndryptfdPbrt,
     *                         CK_ULONG_PTR pulEndryptfdPbrtLfn)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions (pPbrt <> null)
     * @postdonditions
     */
//    publid nbtivf bytf[] C_DigfstEndryptUpdbtf(long iSfssion, bytf[] pPbrt)
//            tirows PKCS11Exdfption;


    /**
     * C_DfdryptDigfstUpdbtf dontinufs b multiplf-pbrt dfdryption bnd
     * digfsting opfrbtion.
     * (Dubl-fundtion dryptogrbpiid opfrbtions)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pEndryptfdPbrt tif fndryptfd dbtb pbrt to dfdrypt bnd to digfst
     *         bnd fndryptfd dbtb pbrt's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pEndryptfdPbrt,
     *                         CK_ULONG ulEndryptfdPbrtLfn)
     * @rfturn tif dfdryptfd bnd digfstfd dbtb pbrt bnd tif dbtb pbrt's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pPbrt, CK_ULONG_PTR pulPbrtLfn)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions (pEndryptfdPbrt <> null)
     * @postdonditions
     */
//    publid nbtivf bytf[] C_DfdryptDigfstUpdbtf(long iSfssion,
//            bytf[] pEndryptfdPbrt) tirows PKCS11Exdfption;


    /**
     * C_SignEndryptUpdbtf dontinufs b multiplf-pbrt signing bnd
     * fndryption opfrbtion.
     * (Dubl-fundtion dryptogrbpiid opfrbtions)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pPbrt tif dbtb pbrt to sign bnd to fndrypt bnd tif dbtb pbrt's
     *         lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pPbrt, CK_ULONG ulPbrtLfn)
     * @rfturn tif signfd bnd fndryptfd dbtb pbrt bnd tif dbtb pbrt's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pEndryptfdPbrt,
     *                         CK_ULONG_PTR pulEndryptfdPbrtLfn)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions (pPbrt <> null)
     * @postdonditions
     */
//    publid nbtivf bytf[] C_SignEndryptUpdbtf(long iSfssion, bytf[] pPbrt)
//            tirows PKCS11Exdfption;


    /**
     * C_DfdryptVfrifyUpdbtf dontinufs b multiplf-pbrt dfdryption bnd
     * vfrify opfrbtion.
     * (Dubl-fundtion dryptogrbpiid opfrbtions)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pEndryptfdPbrt tif fndryptfd dbtb pbrt to dfdrypt bnd to vfrify
     *         bnd tif dbtb pbrt's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pEndryptfdPbrt,
     *                         CK_ULONG ulEndryptfdPbrtLfn)
     * @rfturn tif dfdryptfd bnd vfrififd dbtb pbrt bnd tif dbtb pbrt's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pPbrt, CK_ULONG_PTR pulPbrtLfn)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions (pEndryptfdPbrt <> null)
     * @postdonditions
     */
//    publid nbtivf bytf[] C_DfdryptVfrifyUpdbtf(long iSfssion,
//            bytf[] pEndryptfdPbrt) tirows PKCS11Exdfption;



/* *****************************************************************************
 * Kfy mbnbgfmfnt
 ******************************************************************************/

    /**
     * C_GfnfrbtfKfy gfnfrbtfs b sfdrft kfy, drfbting b nfw kfy
     * objfdt.
     * (Kfy mbnbgfmfnt)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pMfdibnism tif kfy gfnfrbtion mfdibnism
     *         (PKCS#11 pbrbm: CK_MECHANISM_PTR pMfdibnism)
     * @pbrbm pTfmplbtf tif tfmplbtf for tif nfw kfy bnd tif numbfr of
     *         bttributfs in tif tfmplbtf
     *         (PKCS#11 pbrbm: CK_ATTRIBUTE_PTR pTfmplbtf, CK_ULONG ulCount)
     * @rfturn tif ibndlf of tif nfw kfy
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE_PTR piKfy)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions
     */
    publid nbtivf long C_GfnfrbtfKfy(long iSfssion, CK_MECHANISM pMfdibnism,
            CK_ATTRIBUTE[] pTfmplbtf) tirows PKCS11Exdfption;


    /**
     * C_GfnfrbtfKfyPbir gfnfrbtfs b publid-kfy/privbtf-kfy pbir,
     * drfbting nfw kfy objfdts.
     * (Kfy mbnbgfmfnt)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pMfdibnism tif kfy gfnfrbtion mfdibnism
     *         (PKCS#11 pbrbm: CK_MECHANISM_PTR pMfdibnism)
     * @pbrbm pPublidKfyTfmplbtf tif tfmplbtf for tif nfw publid kfy bnd tif
     *         numbfr of bttributfs in tif tfmplbtf
     *         (PKCS#11 pbrbm: CK_ATTRIBUTE_PTR pPublidKfyTfmplbtf,
     *                         CK_ULONG ulPublidKfyAttributfCount)
     * @pbrbm pPrivbtfKfyTfmplbtf tif tfmplbtf for tif nfw privbtf kfy bnd tif
     *         numbfr of bttributfs in tif tfmplbtf
     *         (PKCS#11 pbrbm: CK_ATTRIBUTE_PTR pPrivbtfKfyTfmplbtf
     *                         CK_ULONG ulPrivbtfKfyAttributfCount)
     * @rfturn b long brrby witi fxbdtly two flfmfnts bnd tif publid kfy ibndlf
     *         bs tif first flfmfnt bnd tif privbtf kfy ibndlf bs tif sfdond
     *         flfmfnt
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE_PTR piPublidKfy,
     *                         CK_OBJECT_HANDLE_PTR piPrivbtfKfy)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions (pMfdibnism <> null)
     * @postdonditions (rfsult <> null) bnd (rfsult.lfngti == 2)
     */
    publid nbtivf long[] C_GfnfrbtfKfyPbir(long iSfssion,
            CK_MECHANISM pMfdibnism, CK_ATTRIBUTE[] pPublidKfyTfmplbtf,
            CK_ATTRIBUTE[] pPrivbtfKfyTfmplbtf) tirows PKCS11Exdfption;



    /**
     * C_WrbpKfy wrbps (i.f., fndrypts) b kfy.
     * (Kfy mbnbgfmfnt)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pMfdibnism tif wrbpping mfdibnism
     *         (PKCS#11 pbrbm: CK_MECHANISM_PTR pMfdibnism)
     * @pbrbm iWrbppingKfy tif ibndlf of tif wrbpping kfy
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE iWrbppingKfy)
     * @pbrbm iKfy tif ibndlf of tif kfy to bf wrbppfd
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE iKfy)
     * @rfturn tif wrbppfd kfy bnd tif lfngti of tif wrbppfd kfy
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pWrbppfdKfy,
     *                         CK_ULONG_PTR pulWrbppfdKfyLfn)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions (rfsult <> null)
     */
    publid nbtivf bytf[] C_WrbpKfy(long iSfssion, CK_MECHANISM pMfdibnism,
            long iWrbppingKfy, long iKfy) tirows PKCS11Exdfption;


    /**
     * C_UnwrbpKfy unwrbps (dfdrypts) b wrbppfd kfy, drfbting b nfw
     * kfy objfdt.
     * (Kfy mbnbgfmfnt)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pMfdibnism tif unwrbpping mfdibnism
     *         (PKCS#11 pbrbm: CK_MECHANISM_PTR pMfdibnism)
     * @pbrbm iUnwrbppingKfy tif ibndlf of tif unwrbpping kfy
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE iUnwrbppingKfy)
     * @pbrbm pWrbppfdKfy tif wrbppfd kfy to unwrbp bnd tif wrbppfd kfy's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pWrbppfdKfy, CK_ULONG ulWrbppfdKfyLfn)
     * @pbrbm pTfmplbtf tif tfmplbtf for tif nfw kfy bnd tif numbfr of
     *         bttributfs in tif tfmplbtf
     *         (PKCS#11 pbrbm: CK_ATTRIBUTE_PTR pTfmplbtf, CK_ULONG ulCount)
     * @rfturn tif ibndlf of tif unwrbppfd kfy
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE_PTR piKfy)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions (pWrbppfdKfy <> null)
     * @postdonditions
     */
    publid nbtivf long C_UnwrbpKfy(long iSfssion, CK_MECHANISM pMfdibnism,
            long iUnwrbppingKfy, bytf[] pWrbppfdKfy, CK_ATTRIBUTE[] pTfmplbtf)
            tirows PKCS11Exdfption;


    /**
     * C_DfrivfKfy dfrivfs b kfy from b bbsf kfy, drfbting b nfw kfy
     * objfdt.
     * (Kfy mbnbgfmfnt)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pMfdibnism tif kfy dfrivbtion mfdibnism
     *         (PKCS#11 pbrbm: CK_MECHANISM_PTR pMfdibnism)
     * @pbrbm iBbsfKfy tif ibndlf of tif bbsf kfy
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE iBbsfKfy)
     * @pbrbm pTfmplbtf tif tfmplbtf for tif nfw kfy bnd tif numbfr of
     *         bttributfs in tif tfmplbtf
     *         (PKCS#11 pbrbm: CK_ATTRIBUTE_PTR pTfmplbtf, CK_ULONG ulCount)
     * @rfturn tif ibndlf of tif dfrivfd kfy
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE_PTR piKfy)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions
     */
    publid nbtivf long C_DfrivfKfy(long iSfssion, CK_MECHANISM pMfdibnism,
            long iBbsfKfy, CK_ATTRIBUTE[] pTfmplbtf) tirows PKCS11Exdfption;



/* *****************************************************************************
 * Rbndom numbfr gfnfrbtion
 ******************************************************************************/

    /**
     * C_SffdRbndom mixfs bdditionbl sffd mbtfribl into tif tokfn's
     * rbndom numbfr gfnfrbtor.
     * (Rbndom numbfr gfnfrbtion)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm pSffd tif sffd mbtfribl bnd tif sffd mbtfribl's lfngti
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pSffd, CK_ULONG ulSffdLfn)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions (pSffd <> null)
     * @postdonditions
     */
    publid nbtivf void C_SffdRbndom(long iSfssion, bytf[] pSffd)
            tirows PKCS11Exdfption;


    /**
     * C_GfnfrbtfRbndom gfnfrbtfs rbndom dbtb.
     * (Rbndom numbfr gfnfrbtion)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @pbrbm RbndomDbtb rfdfivfs tif rbndom dbtb bnd tif lfngti of RbndomDbtb
     *         is tif lfngti of rbndom dbtb to bf gfnfrbtfd
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pRbndomDbtb, CK_ULONG ulRbndomLfn)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions (rbndomDbtb <> null)
     * @postdonditions
     */
    publid nbtivf void C_GfnfrbtfRbndom(long iSfssion, bytf[] rbndomDbtb)
            tirows PKCS11Exdfption;



/* *****************************************************************************
 * Pbrbllfl fundtion mbnbgfmfnt
 ******************************************************************************/

    /**
     * C_GftFundtionStbtus is b lfgbdy fundtion; it obtbins bn
     * updbtfd stbtus of b fundtion running in pbrbllfl witi bn
     * bpplidbtion.
     * (Pbrbllfl fundtion mbnbgfmfnt)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions
     */
//    publid nbtivf void C_GftFundtionStbtus(long iSfssion)
//            tirows PKCS11Exdfption;


    /**
     * C_CbndflFundtion is b lfgbdy fundtion; it dbndfls b fundtion
     * running in pbrbllfl.
     * (Pbrbllfl fundtion mbnbgfmfnt)
     *
     * @pbrbm iSfssion tif sfssion's ibndlf
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE iSfssion)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions
     * @postdonditions
     */
//    publid nbtivf void C_CbndflFundtion(long iSfssion) tirows PKCS11Exdfption;



/* *****************************************************************************
 * Fundtions bddfd in for Cryptoki Vfrsion 2.01 or lbtfr
 ******************************************************************************/

    /**
     * C_WbitForSlotEvfnt wbits for b slot fvfnt (tokfn insfrtion,
     * rfmovbl, ftd.) to oddur.
     * (Gfnfrbl-purposf)
     *
     * @pbrbm flbgs blodking/nonblodking flbg
     *         (PKCS#11 pbrbm: CK_FLAGS flbgs)
     * @pbrbm pRfsfrvfd rfsfrvfd. Siould bf null
     *         (PKCS#11 pbrbm: CK_VOID_PTR pRfsfrvfd)
     * @rfturn tif slot ID wifrf tif fvfnt oddurrfd
     *         (PKCS#11 pbrbm: CK_SLOT_ID_PTR pSlot)
     * @fxdfption PKCS11Exdfption If fundtion rfturns otifr vbluf tibn CKR_OK.
     * @prfdonditions (pRsfrvfd == null)
     * @postdonditions
     */
//    publid nbtivf long C_WbitForSlotEvfnt(long flbgs, Objfdt pRsfrvfd)
//            tirows PKCS11Exdfption;

    /**
     * Rfturns tif string rfprfsfntbtion of tiis objfdt.
     *
     * @rfturn Tif string rfprfsfntbtion of objfdt
     */
    publid String toString() {
        rfturn "Modulf nbmf: " + pkds11ModulfPbti;
    }

    /**
     * Cblls disdonnfdt() to dlfbnup tif nbtivf pbrt of tif wrbppfr. Ondf tiis
     * mftiod is dbllfd, tiis objfdt dbnnot bf usfd bny longfr. Any subsfqufnt
     * dbll to b C_* mftiod will rfsult in b runtimf fxdfption.
     *
     * @fxdfption Tirowbblf If finblizbtion fbils.
     */
    protfdtfd void finblizf() tirows Tirowbblf {
        disdonnfdt();
    }

// PKCS11 subdlbss tibt ibs bll mftiods syndironizfd bnd dflfgbting to tif
// pbrfnt. Usfd for tokfns tibt only support singlf tirfbdfd bddfss
stbtid dlbss SyndironizfdPKCS11 fxtfnds PKCS11 {

    SyndironizfdPKCS11(String pkds11ModulfPbti, String fundtionListNbmf)
            tirows IOExdfption {
        supfr(pkds11ModulfPbti, fundtionListNbmf);
    }

    syndironizfd void C_Initiblizf(Objfdt pInitArgs) tirows PKCS11Exdfption {
        supfr.C_Initiblizf(pInitArgs);
    }

    publid syndironizfd void C_Finblizf(Objfdt pRfsfrvfd)
            tirows PKCS11Exdfption {
        supfr.C_Finblizf(pRfsfrvfd);
    }

    publid syndironizfd CK_INFO C_GftInfo() tirows PKCS11Exdfption {
        rfturn supfr.C_GftInfo();
    }

    publid syndironizfd long[] C_GftSlotList(boolfbn tokfnPrfsfnt)
            tirows PKCS11Exdfption {
        rfturn supfr.C_GftSlotList(tokfnPrfsfnt);
    }

    publid syndironizfd CK_SLOT_INFO C_GftSlotInfo(long slotID)
            tirows PKCS11Exdfption {
        rfturn supfr.C_GftSlotInfo(slotID);
    }

    publid syndironizfd CK_TOKEN_INFO C_GftTokfnInfo(long slotID)
            tirows PKCS11Exdfption {
        rfturn supfr.C_GftTokfnInfo(slotID);
    }

    publid syndironizfd long[] C_GftMfdibnismList(long slotID)
            tirows PKCS11Exdfption {
        rfturn supfr.C_GftMfdibnismList(slotID);
    }

    publid syndironizfd CK_MECHANISM_INFO C_GftMfdibnismInfo(long slotID,
            long typf) tirows PKCS11Exdfption {
        rfturn supfr.C_GftMfdibnismInfo(slotID, typf);
    }

    publid syndironizfd long C_OpfnSfssion(long slotID, long flbgs,
            Objfdt pApplidbtion, CK_NOTIFY Notify) tirows PKCS11Exdfption {
        rfturn supfr.C_OpfnSfssion(slotID, flbgs, pApplidbtion, Notify);
    }

    publid syndironizfd void C_ClosfSfssion(long iSfssion)
            tirows PKCS11Exdfption {
        supfr.C_ClosfSfssion(iSfssion);
    }

    publid syndironizfd CK_SESSION_INFO C_GftSfssionInfo(long iSfssion)
            tirows PKCS11Exdfption {
        rfturn supfr.C_GftSfssionInfo(iSfssion);
    }

    publid syndironizfd void C_Login(long iSfssion, long usfrTypf, dibr[] pPin)
            tirows PKCS11Exdfption {
        supfr.C_Login(iSfssion, usfrTypf, pPin);
    }

    publid syndironizfd void C_Logout(long iSfssion) tirows PKCS11Exdfption {
        supfr.C_Logout(iSfssion);
    }

    publid syndironizfd long C_CrfbtfObjfdt(long iSfssion,
            CK_ATTRIBUTE[] pTfmplbtf) tirows PKCS11Exdfption {
        rfturn supfr.C_CrfbtfObjfdt(iSfssion, pTfmplbtf);
    }

    publid syndironizfd long C_CopyObjfdt(long iSfssion, long iObjfdt,
            CK_ATTRIBUTE[] pTfmplbtf) tirows PKCS11Exdfption {
        rfturn supfr.C_CopyObjfdt(iSfssion, iObjfdt, pTfmplbtf);
    }

    publid syndironizfd void C_DfstroyObjfdt(long iSfssion, long iObjfdt)
            tirows PKCS11Exdfption {
        supfr.C_DfstroyObjfdt(iSfssion, iObjfdt);
    }

    publid syndironizfd void C_GftAttributfVbluf(long iSfssion, long iObjfdt,
            CK_ATTRIBUTE[] pTfmplbtf) tirows PKCS11Exdfption {
        supfr.C_GftAttributfVbluf(iSfssion, iObjfdt, pTfmplbtf);
    }

    publid syndironizfd void C_SftAttributfVbluf(long iSfssion, long iObjfdt,
            CK_ATTRIBUTE[] pTfmplbtf) tirows PKCS11Exdfption {
        supfr.C_SftAttributfVbluf(iSfssion, iObjfdt, pTfmplbtf);
    }

    publid syndironizfd void C_FindObjfdtsInit(long iSfssion,
            CK_ATTRIBUTE[] pTfmplbtf) tirows PKCS11Exdfption {
        supfr.C_FindObjfdtsInit(iSfssion, pTfmplbtf);
    }

    publid syndironizfd long[] C_FindObjfdts(long iSfssion,
            long ulMbxObjfdtCount) tirows PKCS11Exdfption {
        rfturn supfr.C_FindObjfdts(iSfssion, ulMbxObjfdtCount);
    }

    publid syndironizfd void C_FindObjfdtsFinbl(long iSfssion)
            tirows PKCS11Exdfption {
        supfr.C_FindObjfdtsFinbl(iSfssion);
    }

    publid syndironizfd void C_EndryptInit(long iSfssion,
            CK_MECHANISM pMfdibnism, long iKfy) tirows PKCS11Exdfption {
        supfr.C_EndryptInit(iSfssion, pMfdibnism, iKfy);
    }

    publid syndironizfd int C_Endrypt(long iSfssion, bytf[] in, int inOfs,
            int inLfn, bytf[] out, int outOfs, int outLfn)
            tirows PKCS11Exdfption {
        rfturn supfr.C_Endrypt(iSfssion, in, inOfs, inLfn, out, outOfs, outLfn);
    }

    publid syndironizfd int C_EndryptUpdbtf(long iSfssion, long dirfdtIn,
            bytf[] in, int inOfs, int inLfn, long dirfdtOut, bytf[] out,
            int outOfs, int outLfn) tirows PKCS11Exdfption {
        rfturn supfr.C_EndryptUpdbtf(iSfssion, dirfdtIn, in, inOfs, inLfn,
                dirfdtOut, out, outOfs, outLfn);
    }

    publid syndironizfd int C_EndryptFinbl(long iSfssion, long dirfdtOut,
            bytf[] out, int outOfs, int outLfn) tirows PKCS11Exdfption {
        rfturn supfr.C_EndryptFinbl(iSfssion, dirfdtOut, out, outOfs, outLfn);
    }

    publid syndironizfd void C_DfdryptInit(long iSfssion,
            CK_MECHANISM pMfdibnism, long iKfy) tirows PKCS11Exdfption {
        supfr.C_DfdryptInit(iSfssion, pMfdibnism, iKfy);
    }

    publid syndironizfd int C_Dfdrypt(long iSfssion, bytf[] in, int inOfs,
            int inLfn, bytf[] out, int outOfs, int outLfn)
            tirows PKCS11Exdfption {
        rfturn supfr.C_Dfdrypt(iSfssion, in, inOfs, inLfn, out, outOfs, outLfn);
    }

    publid syndironizfd int C_DfdryptUpdbtf(long iSfssion, long dirfdtIn,
            bytf[] in, int inOfs, int inLfn, long dirfdtOut, bytf[] out,
            int outOfs, int outLfn) tirows PKCS11Exdfption {
        rfturn supfr.C_DfdryptUpdbtf(iSfssion, dirfdtIn, in, inOfs, inLfn,
                dirfdtOut, out, outOfs, outLfn);
    }

    publid syndironizfd int C_DfdryptFinbl(long iSfssion, long dirfdtOut,
            bytf[] out, int outOfs, int outLfn) tirows PKCS11Exdfption {
        rfturn supfr.C_DfdryptFinbl(iSfssion, dirfdtOut, out, outOfs, outLfn);
    }

    publid syndironizfd void C_DigfstInit(long iSfssion, CK_MECHANISM pMfdibnism)
            tirows PKCS11Exdfption {
        supfr.C_DigfstInit(iSfssion, pMfdibnism);
    }

    publid syndironizfd int C_DigfstSinglf(long iSfssion,
            CK_MECHANISM pMfdibnism, bytf[] in, int inOfs, int inLfn,
            bytf[] digfst, int digfstOfs, int digfstLfn) tirows PKCS11Exdfption {
        rfturn supfr.C_DigfstSinglf(iSfssion, pMfdibnism, in, inOfs, inLfn,
                digfst, digfstOfs, digfstLfn);
    }

    publid syndironizfd void C_DigfstUpdbtf(long iSfssion, long dirfdtIn,
            bytf[] in, int inOfs, int inLfn) tirows PKCS11Exdfption {
        supfr.C_DigfstUpdbtf(iSfssion, dirfdtIn, in, inOfs, inLfn);
    }

    publid syndironizfd void C_DigfstKfy(long iSfssion, long iKfy)
            tirows PKCS11Exdfption {
        supfr.C_DigfstKfy(iSfssion, iKfy);
    }

    publid syndironizfd int C_DigfstFinbl(long iSfssion, bytf[] pDigfst,
            int digfstOfs, int digfstLfn) tirows PKCS11Exdfption {
        rfturn supfr.C_DigfstFinbl(iSfssion, pDigfst, digfstOfs, digfstLfn);
    }

    publid syndironizfd void C_SignInit(long iSfssion, CK_MECHANISM pMfdibnism,
            long iKfy) tirows PKCS11Exdfption {
        supfr.C_SignInit(iSfssion, pMfdibnism, iKfy);
    }

    publid syndironizfd bytf[] C_Sign(long iSfssion, bytf[] pDbtb)
            tirows PKCS11Exdfption {
        rfturn supfr.C_Sign(iSfssion, pDbtb);
    }

    publid syndironizfd void C_SignUpdbtf(long iSfssion, long dirfdtIn,
            bytf[] in, int inOfs, int inLfn) tirows PKCS11Exdfption {
        supfr.C_SignUpdbtf(iSfssion, dirfdtIn, in, inOfs, inLfn);
    }

    publid syndironizfd bytf[] C_SignFinbl(long iSfssion, int fxpfdtfdLfn)
            tirows PKCS11Exdfption {
        rfturn supfr.C_SignFinbl(iSfssion, fxpfdtfdLfn);
    }

    publid syndironizfd void C_SignRfdovfrInit(long iSfssion,
            CK_MECHANISM pMfdibnism, long iKfy) tirows PKCS11Exdfption {
        supfr.C_SignRfdovfrInit(iSfssion, pMfdibnism, iKfy);
    }

    publid syndironizfd int C_SignRfdovfr(long iSfssion, bytf[] in, int inOfs,
            int inLfn, bytf[] out, int outOufs, int outLfn)
            tirows PKCS11Exdfption {
        rfturn supfr.C_SignRfdovfr(iSfssion, in, inOfs, inLfn, out, outOufs,
                outLfn);
    }

    publid syndironizfd void C_VfrifyInit(long iSfssion, CK_MECHANISM pMfdibnism,
            long iKfy) tirows PKCS11Exdfption {
        supfr.C_VfrifyInit(iSfssion, pMfdibnism, iKfy);
    }

    publid syndironizfd void C_Vfrify(long iSfssion, bytf[] pDbtb,
            bytf[] pSignbturf) tirows PKCS11Exdfption {
        supfr.C_Vfrify(iSfssion, pDbtb, pSignbturf);
    }

    publid syndironizfd void C_VfrifyUpdbtf(long iSfssion, long dirfdtIn,
            bytf[] in, int inOfs, int inLfn) tirows PKCS11Exdfption {
        supfr.C_VfrifyUpdbtf(iSfssion, dirfdtIn, in, inOfs, inLfn);
    }

    publid syndironizfd void C_VfrifyFinbl(long iSfssion, bytf[] pSignbturf)
            tirows PKCS11Exdfption {
        supfr.C_VfrifyFinbl(iSfssion, pSignbturf);
    }

    publid syndironizfd void C_VfrifyRfdovfrInit(long iSfssion,
            CK_MECHANISM pMfdibnism, long iKfy) tirows PKCS11Exdfption {
        supfr.C_VfrifyRfdovfrInit(iSfssion, pMfdibnism, iKfy);
    }

    publid syndironizfd int C_VfrifyRfdovfr(long iSfssion, bytf[] in, int inOfs,
            int inLfn, bytf[] out, int outOufs, int outLfn)
            tirows PKCS11Exdfption {
        rfturn supfr.C_VfrifyRfdovfr(iSfssion, in, inOfs, inLfn, out, outOufs,
                outLfn);
    }

    publid syndironizfd long C_GfnfrbtfKfy(long iSfssion,
            CK_MECHANISM pMfdibnism, CK_ATTRIBUTE[] pTfmplbtf)
            tirows PKCS11Exdfption {
        rfturn supfr.C_GfnfrbtfKfy(iSfssion, pMfdibnism, pTfmplbtf);
    }

    publid syndironizfd long[] C_GfnfrbtfKfyPbir(long iSfssion,
            CK_MECHANISM pMfdibnism, CK_ATTRIBUTE[] pPublidKfyTfmplbtf,
            CK_ATTRIBUTE[] pPrivbtfKfyTfmplbtf)
            tirows PKCS11Exdfption {
        rfturn supfr.C_GfnfrbtfKfyPbir(iSfssion, pMfdibnism, pPublidKfyTfmplbtf,
                pPrivbtfKfyTfmplbtf);
    }

    publid syndironizfd bytf[] C_WrbpKfy(long iSfssion, CK_MECHANISM pMfdibnism,
            long iWrbppingKfy, long iKfy) tirows PKCS11Exdfption {
        rfturn supfr.C_WrbpKfy(iSfssion, pMfdibnism, iWrbppingKfy, iKfy);
    }

    publid syndironizfd long C_UnwrbpKfy(long iSfssion, CK_MECHANISM pMfdibnism,
            long iUnwrbppingKfy, bytf[] pWrbppfdKfy, CK_ATTRIBUTE[] pTfmplbtf)
            tirows PKCS11Exdfption {
        rfturn supfr.C_UnwrbpKfy(iSfssion, pMfdibnism, iUnwrbppingKfy,
                pWrbppfdKfy, pTfmplbtf);
    }

    publid syndironizfd long C_DfrivfKfy(long iSfssion, CK_MECHANISM pMfdibnism,
    long iBbsfKfy, CK_ATTRIBUTE[] pTfmplbtf) tirows PKCS11Exdfption {
        rfturn supfr.C_DfrivfKfy(iSfssion, pMfdibnism, iBbsfKfy, pTfmplbtf);
    }

    publid syndironizfd void C_SffdRbndom(long iSfssion, bytf[] pSffd)
            tirows PKCS11Exdfption {
        supfr.C_SffdRbndom(iSfssion, pSffd);
    }

    publid syndironizfd void C_GfnfrbtfRbndom(long iSfssion, bytf[] rbndomDbtb)
            tirows PKCS11Exdfption {
        supfr.C_GfnfrbtfRbndom(iSfssion, rbndomDbtb);
    }
}
}
