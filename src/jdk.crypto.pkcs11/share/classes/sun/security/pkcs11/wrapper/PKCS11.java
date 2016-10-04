/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.pkcs11.wrbpper;

import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.util.*;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

import stbtic sun.security.pkcs11.wrbpper.PKCS11Constbnts.*;

/**
 * This is the defbult implementbtion of the PKCS11 interfbce. IT connects to
 * the pkcs11wrbpper.dll file, which is the nbtive pbrt of this librbry.
 * The strbnge bnd bwkwbrd looking initiblizbtion wbs chosen to bvoid cblling
 * lobdLibrbry from b stbtic initiblizbtion block, becbuse this would complicbte
 * the use in bpplets.
 *
 * @buthor Kbrl Scheibelhofer <Kbrl.Scheibelhofer@ibik.bt>
 * @buthor Mbrtin Schlbeffer <schlbeff@sbox.tugrbz.bt>
 * @invbribnts (pkcs11ModulePbth_ <> null)
 */
public clbss PKCS11 {

    /**
     * The nbme of the nbtive pbrt of the wrbpper; i.e. the filenbme without
     * the extension (e.g. ".DLL" or ".so").
     */
    privbte stbtic finbl String PKCS11_WRAPPER = "j2pkcs11";

    stbtic {
        // cbnnot use LobdLibrbryAction becbuse thbt would mbke the nbtive
        // librbry bvbilbble to the bootclbsslobder, but we run in the
        // extension clbsslobder.
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                System.lobdLibrbry(PKCS11_WRAPPER);
                return null;
            }
        });
        initiblizeLibrbry();
    }

    public stbtic void lobdNbtive() {
        // dummy method thbt cbn be cblled to mbke sure the nbtive
        // portion hbs been lobded. bctubl lobding hbppens in the
        // stbtic initiblizer, hence this method is empty.
    }

    /**
     * The PKCS#11 module to connect to. This is the PKCS#11 driver of the token;
     * e.g. pk2priv.dll.
     */
    privbte finbl String pkcs11ModulePbth;

    privbte long pNbtiveDbtb;

    /**
     * This method does the initiblizbtion of the nbtive librbry. It is cblled
     * exbctly once for this clbss.
     *
     * @preconditions
     * @postconditions
     */
    privbte stbtic nbtive void initiblizeLibrbry();

    // XXX
    /**
     * This method does the finblizbtion of the nbtive librbry. It is cblled
     * exbctly once for this clbss. The librbry uses this method for b clebn-up
     * of bny resources.
     *
     * @preconditions
     * @postconditions
     */
    privbte stbtic nbtive void finblizeLibrbry();

    privbte stbtic finbl Mbp<String,PKCS11> moduleMbp =
        new HbshMbp<String,PKCS11>();

    /**
     * Connects to the PKCS#11 driver given. The filenbme must contbin the
     * pbth, if the driver is not in the system's sebrch pbth.
     *
     * @pbrbm pkcs11ModulePbth the PKCS#11 librbry pbth
     * @preconditions (pkcs11ModulePbth <> null)
     * @postconditions
     */
    PKCS11(String pkcs11ModulePbth, String functionListNbme)
            throws IOException {
        connect(pkcs11ModulePbth, functionListNbme);
        this.pkcs11ModulePbth = pkcs11ModulePbth;
    }

    public stbtic synchronized PKCS11 getInstbnce(String pkcs11ModulePbth,
            String functionList, CK_C_INITIALIZE_ARGS pInitArgs,
            boolebn omitInitiblize) throws IOException, PKCS11Exception {
        // we mby only cbll C_Initiblize once per nbtive .so/.dll
        // so keep b cbche using the (non-cbnonicblized!) pbth
        PKCS11 pkcs11 = moduleMbp.get(pkcs11ModulePbth);
        if (pkcs11 == null) {
            if ((pInitArgs != null)
                    && ((pInitArgs.flbgs & CKF_OS_LOCKING_OK) != 0)) {
                pkcs11 = new PKCS11(pkcs11ModulePbth, functionList);
            } else {
                pkcs11 = new SynchronizedPKCS11(pkcs11ModulePbth, functionList);
            }
            if (omitInitiblize == fblse) {
                try {
                    pkcs11.C_Initiblize(pInitArgs);
                } cbtch (PKCS11Exception e) {
                    // ignore blrebdy-initiblized error code
                    // rethrow bll other errors
                    if (e.getErrorCode() != CKR_CRYPTOKI_ALREADY_INITIALIZED) {
                        throw e;
                    }
                }
            }
            moduleMbp.put(pkcs11ModulePbth, pkcs11);
        }
        return pkcs11;
    }

    /**
     * Connects this object to the specified PKCS#11 librbry. This method is for
     * internbl use only.
     * Declbred privbte, becbuse incorrect hbndling mby result in errors in the
     * nbtive pbrt.
     *
     * @pbrbm pkcs11ModulePbth The PKCS#11 librbry pbth.
     * @preconditions (pkcs11ModulePbth <> null)
     * @postconditions
     */
    privbte nbtive void connect(String pkcs11ModulePbth, String functionListNbme)
            throws IOException;

    /**
     * Disconnects the PKCS#11 librbry from this object. After cblling this
     * method, this object is no longer connected to b nbtive PKCS#11 module
     * bnd bny subsequent cblls to C_ methods will fbil. This method is for
     * internbl use only.
     * Declbred privbte, becbuse incorrect hbndling mby result in errors in the
     * nbtive pbrt.
     *
     * @preconditions
     * @postconditions
     */
    privbte nbtive void disconnect();


    // Implementbtion of PKCS11 methods delegbted to nbtive pkcs11wrbpper librbry

/* *****************************************************************************
 * Generbl-purpose
 ******************************************************************************/

    /**
     * C_Initiblize initiblizes the Cryptoki librbry.
     * (Generbl-purpose)
     *
     * @pbrbm pInitArgs if pInitArgs is not NULL it gets cbsted to
     *         CK_C_INITIALIZE_ARGS_PTR bnd dereferenced
     *         (PKCS#11 pbrbm: CK_VOID_PTR pInitArgs)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions
     */
    nbtive void C_Initiblize(Object pInitArgs) throws PKCS11Exception;

    /**
     * C_Finblize indicbtes thbt bn bpplicbtion is done with the
     * Cryptoki librbry
     * (Generbl-purpose)
     *
     * @pbrbm pReserved is reserved. Should be NULL_PTR
     *         (PKCS#11 pbrbm: CK_VOID_PTR pReserved)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions (pReserved == null)
     * @postconditions
     */
    public nbtive void C_Finblize(Object pReserved) throws PKCS11Exception;


    /**
     * C_GetInfo returns generbl informbtion bbout Cryptoki.
     * (Generbl-purpose)
     *
     * @return the informbtion.
     *         (PKCS#11 pbrbm: CK_INFO_PTR pInfo)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions (result <> null)
     */
    public nbtive CK_INFO C_GetInfo() throws PKCS11Exception;


/* *****************************************************************************
 * Slot bnd token mbnbgement
 ******************************************************************************/

    /**
     * C_GetSlotList obtbins b list of slots in the system.
     * (Slot bnd token mbnbgement)
     *
     * @pbrbm tokenPresent if true only Slot IDs with b token bre returned
     *         (PKCS#11 pbrbm: CK_BBOOL tokenPresent)
     * @return b long brrby of slot IDs bnd number of Slot IDs
     *         (PKCS#11 pbrbm: CK_SLOT_ID_PTR pSlotList, CK_ULONG_PTR pulCount)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions (result <> null)
     */
    public nbtive long[] C_GetSlotList(boolebn tokenPresent)
            throws PKCS11Exception;


    /**
     * C_GetSlotInfo obtbins informbtion bbout b pbrticulbr slot in
     * the system.
     * (Slot bnd token mbnbgement)
     *
     * @pbrbm slotID the ID of the slot
     *         (PKCS#11 pbrbm: CK_SLOT_ID slotID)
     * @return the slot informbtion
     *         (PKCS#11 pbrbm: CK_SLOT_INFO_PTR pInfo)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions (result <> null)
     */
    public nbtive CK_SLOT_INFO C_GetSlotInfo(long slotID) throws PKCS11Exception;


    /**
     * C_GetTokenInfo obtbins informbtion bbout b pbrticulbr token
     * in the system.
     * (Slot bnd token mbnbgement)
     *
     * @pbrbm slotID ID of the token's slot
     *         (PKCS#11 pbrbm: CK_SLOT_ID slotID)
     * @return the token informbtion
     *         (PKCS#11 pbrbm: CK_TOKEN_INFO_PTR pInfo)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions (result <> null)
     */
    public nbtive CK_TOKEN_INFO C_GetTokenInfo(long slotID)
            throws PKCS11Exception;


    /**
     * C_GetMechbnismList obtbins b list of mechbnism types
     * supported by b token.
     * (Slot bnd token mbnbgement)
     *
     * @pbrbm slotID ID of the token's slot
     *         (PKCS#11 pbrbm: CK_SLOT_ID slotID)
     * @return b long brrby of mechbnism types bnd number of mechbnism types
     *         (PKCS#11 pbrbm: CK_MECHANISM_TYPE_PTR pMechbnismList,
     *                         CK_ULONG_PTR pulCount)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions (result <> null)
     */
    public nbtive long[] C_GetMechbnismList(long slotID) throws PKCS11Exception;


    /**
     * C_GetMechbnismInfo obtbins informbtion bbout b pbrticulbr
     * mechbnism possibly supported by b token.
     * (Slot bnd token mbnbgement)
     *
     * @pbrbm slotID ID of the token's slot
     *         (PKCS#11 pbrbm: CK_SLOT_ID slotID)
     * @pbrbm type type of mechbnism
     *         (PKCS#11 pbrbm: CK_MECHANISM_TYPE type)
     * @return the mechbnism info
     *         (PKCS#11 pbrbm: CK_MECHANISM_INFO_PTR pInfo)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions (result <> null)
     */
    public nbtive CK_MECHANISM_INFO C_GetMechbnismInfo(long slotID, long type)
            throws PKCS11Exception;


    /**
     * C_InitToken initiblizes b token.
     * (Slot bnd token mbnbgement)
     *
     * @pbrbm slotID ID of the token's slot
     *         (PKCS#11 pbrbm: CK_SLOT_ID slotID)
     * @pbrbm pPin the SO's initibl PIN bnd the length in bytes of the PIN
     *         (PKCS#11 pbrbm: CK_CHAR_PTR pPin, CK_ULONG ulPinLen)
     * @pbrbm pLbbel 32-byte token lbbel (blbnk pbdded)
     *         (PKCS#11 pbrbm: CK_UTF8CHAR_PTR pLbbel)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions
     */
//    public nbtive void C_InitToken(long slotID, chbr[] pPin, chbr[] pLbbel)
//            throws PKCS11Exception;


    /**
     * C_InitPIN initiblizes the normbl user's PIN.
     * (Slot bnd token mbnbgement)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pPin the normbl user's PIN bnd the length in bytes of the PIN
     *         (PKCS#11 pbrbm: CK_CHAR_PTR pPin, CK_ULONG ulPinLen)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions
     */
//    public nbtive void C_InitPIN(long hSession, chbr[] pPin)
//            throws PKCS11Exception;


    /**
     * C_SetPIN modifies the PIN of the user who is logged in.
     * (Slot bnd token mbnbgement)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pOldPin the old PIN bnd the length of the old PIN
     *         (PKCS#11 pbrbm: CK_CHAR_PTR pOldPin, CK_ULONG ulOldLen)
     * @pbrbm pNewPin the new PIN bnd the length of the new PIN
     *         (PKCS#11 pbrbm: CK_CHAR_PTR pNewPin, CK_ULONG ulNewLen)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions
     */
//    public nbtive void C_SetPIN(long hSession, chbr[] pOldPin, chbr[] pNewPin)
//            throws PKCS11Exception;



/* *****************************************************************************
 * Session mbnbgement
 ******************************************************************************/

    /**
     * C_OpenSession opens b session between bn bpplicbtion bnd b
     * token.
     * (Session mbnbgement)
     *
     * @pbrbm slotID the slot's ID
     *         (PKCS#11 pbrbm: CK_SLOT_ID slotID)
     * @pbrbm flbgs of CK_SESSION_INFO
     *         (PKCS#11 pbrbm: CK_FLAGS flbgs)
     * @pbrbm pApplicbtion pbssed to cbllbbck
     *         (PKCS#11 pbrbm: CK_VOID_PTR pApplicbtion)
     * @pbrbm Notify the cbllbbck function
     *         (PKCS#11 pbrbm: CK_NOTIFY Notify)
     * @return the session hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE_PTR phSession)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions
     */
    public nbtive long C_OpenSession(long slotID, long flbgs,
            Object pApplicbtion, CK_NOTIFY Notify) throws PKCS11Exception;


    /**
     * C_CloseSession closes b session between bn bpplicbtion bnd b
     * token.
     * (Session mbnbgement)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions
     */
    public nbtive void C_CloseSession(long hSession) throws PKCS11Exception;


    /**
     * C_CloseAllSessions closes bll sessions with b token.
     * (Session mbnbgement)
     *
     * @pbrbm slotID the ID of the token's slot
     *         (PKCS#11 pbrbm: CK_SLOT_ID slotID)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions
     */
//    public nbtive void C_CloseAllSessions(long slotID) throws PKCS11Exception;


    /**
     * C_GetSessionInfo obtbins informbtion bbout the session.
     * (Session mbnbgement)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @return the session info
     *         (PKCS#11 pbrbm: CK_SESSION_INFO_PTR pInfo)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions (result <> null)
     */
    public nbtive CK_SESSION_INFO C_GetSessionInfo(long hSession)
            throws PKCS11Exception;


    /**
     * C_GetOperbtionStbte obtbins the stbte of the cryptogrbphic operbtion
     * in b session.
     * (Session mbnbgement)
     *
     * @pbrbm hSession session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @return the stbte bnd the stbte length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pOperbtionStbte,
     *                         CK_ULONG_PTR pulOperbtionStbteLen)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions (result <> null)
     */
    public nbtive byte[] C_GetOperbtionStbte(long hSession)
            throws PKCS11Exception;


    /**
     * C_SetOperbtionStbte restores the stbte of the cryptogrbphic
     * operbtion in b session.
     * (Session mbnbgement)
     *
     * @pbrbm hSession session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pOperbtionStbte the stbte bnd the stbte length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pOperbtionStbte,
     *                         CK_ULONG ulOperbtionStbteLen)
     * @pbrbm hEncryptionKey en/decryption key
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE hEncryptionKey)
     * @pbrbm hAuthenticbtionKey sign/verify key
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE hAuthenticbtionKey)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions
     */
    public nbtive void C_SetOperbtionStbte(long hSession, byte[] pOperbtionStbte,
            long hEncryptionKey, long hAuthenticbtionKey) throws PKCS11Exception;


    /**
     * C_Login logs b user into b token.
     * (Session mbnbgement)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm userType the user type
     *         (PKCS#11 pbrbm: CK_USER_TYPE userType)
     * @pbrbm pPin the user's PIN bnd the length of the PIN
     *         (PKCS#11 pbrbm: CK_CHAR_PTR pPin, CK_ULONG ulPinLen)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions
     */
    public nbtive void C_Login(long hSession, long userType, chbr[] pPin)
            throws PKCS11Exception;


    /**
     * C_Logout logs b user out from b token.
     * (Session mbnbgement)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions
     */
    public nbtive void C_Logout(long hSession) throws PKCS11Exception;



/* *****************************************************************************
 * Object mbnbgement
 ******************************************************************************/

    /**
     * C_CrebteObject crebtes b new object.
     * (Object mbnbgement)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pTemplbte the object's templbte bnd number of bttributes in
     *         templbte
     *         (PKCS#11 pbrbm: CK_ATTRIBUTE_PTR pTemplbte, CK_ULONG ulCount)
     * @return the object's hbndle
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE_PTR phObject)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions
     */
    public nbtive long C_CrebteObject(long hSession, CK_ATTRIBUTE[] pTemplbte)
            throws PKCS11Exception;


    /**
     * C_CopyObject copies bn object, crebting b new object for the
     * copy.
     * (Object mbnbgement)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm hObject the object's hbndle
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE hObject)
     * @pbrbm pTemplbte the templbte for the new object bnd number of bttributes
     *         in templbte
     *         (PKCS#11 pbrbm: CK_ATTRIBUTE_PTR pTemplbte, CK_ULONG ulCount)
     * @return the hbndle of the copy
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE_PTR phNewObject)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions
     */
    public nbtive long C_CopyObject(long hSession, long hObject,
            CK_ATTRIBUTE[] pTemplbte) throws PKCS11Exception;


    /**
     * C_DestroyObject destroys bn object.
     * (Object mbnbgement)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm hObject the object's hbndle
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE hObject)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions
     */
    public nbtive void C_DestroyObject(long hSession, long hObject)
            throws PKCS11Exception;


    /**
     * C_GetObjectSize gets the size of bn object in bytes.
     * (Object mbnbgement)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm hObject the object's hbndle
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE hObject)
     * @return the size of the object
     *         (PKCS#11 pbrbm: CK_ULONG_PTR pulSize)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions
     */
//    public nbtive long C_GetObjectSize(long hSession, long hObject)
//            throws PKCS11Exception;


    /**
     * C_GetAttributeVblue obtbins the vblue of one or more object
     * bttributes. The templbte bttributes blso receive the vblues.
     * (Object mbnbgement)
     * note: in PKCS#11 pTemplbte bnd the result templbte bre the sbme
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm hObject the object's hbndle
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE hObject)
     * @pbrbm pTemplbte specifies the bttributes bnd number of bttributes to get
     *                  The templbte bttributes blso receive the vblues.
     *         (PKCS#11 pbrbm: CK_ATTRIBUTE_PTR pTemplbte, CK_ULONG ulCount)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions (pTemplbte <> null)
     * @postconditions (result <> null)
     */
    public nbtive void C_GetAttributeVblue(long hSession, long hObject,
            CK_ATTRIBUTE[] pTemplbte) throws PKCS11Exception;


    /**
     * C_SetAttributeVblue modifies the vblue of one or more object
     * bttributes
     * (Object mbnbgement)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm hObject the object's hbndle
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE hObject)
     * @pbrbm pTemplbte specifies the bttributes bnd vblues to get; number of
     *         bttributes in the templbte
     *         (PKCS#11 pbrbm: CK_ATTRIBUTE_PTR pTemplbte, CK_ULONG ulCount)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions (pTemplbte <> null)
     * @postconditions
     */
    public nbtive void C_SetAttributeVblue(long hSession, long hObject,
            CK_ATTRIBUTE[] pTemplbte) throws PKCS11Exception;


    /**
     * C_FindObjectsInit initiblizes b sebrch for token bnd session
     * objects thbt mbtch b templbte.
     * (Object mbnbgement)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pTemplbte the object's bttribute vblues to mbtch bnd the number of
     *         bttributes in sebrch templbte
     *         (PKCS#11 pbrbm: CK_ATTRIBUTE_PTR pTemplbte, CK_ULONG ulCount)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions
     */
    public nbtive void C_FindObjectsInit(long hSession, CK_ATTRIBUTE[] pTemplbte)
            throws PKCS11Exception;


    /**
     * C_FindObjects continues b sebrch for token bnd session
     * objects thbt mbtch b templbte, obtbining bdditionbl object
     * hbndles.
     * (Object mbnbgement)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm ulMbxObjectCount the mbx. object hbndles to get
     *         (PKCS#11 pbrbm: CK_ULONG ulMbxObjectCount)
     * @return the object's hbndles bnd the bctubl number of objects returned
     *         (PKCS#11 pbrbm: CK_ULONG_PTR pulObjectCount)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions (result <> null)
     */
    public nbtive long[] C_FindObjects(long hSession, long ulMbxObjectCount)
            throws PKCS11Exception;


    /**
     * C_FindObjectsFinbl finishes b sebrch for token bnd session
     * objects.
     * (Object mbnbgement)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions
     */
    public nbtive void C_FindObjectsFinbl(long hSession) throws PKCS11Exception;



/* *****************************************************************************
 * Encryption bnd decryption
 ******************************************************************************/

    /**
     * C_EncryptInit initiblizes bn encryption operbtion.
     * (Encryption bnd decryption)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pMechbnism the encryption mechbnism
     *         (PKCS#11 pbrbm: CK_MECHANISM_PTR pMechbnism)
     * @pbrbm hKey the hbndle of the encryption key
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE hKey)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions
     */
    public nbtive void C_EncryptInit(long hSession, CK_MECHANISM pMechbnism,
            long hKey) throws PKCS11Exception;


    /**
     * C_Encrypt encrypts single-pbrt dbtb.
     * (Encryption bnd decryption)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pDbtb the dbtb to get encrypted bnd the dbtb's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pDbtb, CK_ULONG ulDbtbLen)
     * @return the encrypted dbtb bnd the encrypted dbtb's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pEncryptedDbtb,
     *                         CK_ULONG_PTR pulEncryptedDbtbLen)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions (pDbtb <> null)
     * @postconditions (result <> null)
     */
    public nbtive int C_Encrypt(long hSession, byte[] in, int inOfs, int inLen,
            byte[] out, int outOfs, int outLen) throws PKCS11Exception;


    /**
     * C_EncryptUpdbte continues b multiple-pbrt encryption
     * operbtion.
     * (Encryption bnd decryption)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pPbrt the dbtb pbrt to get encrypted bnd the dbtb pbrt's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pPbrt, CK_ULONG ulPbrtLen)
     * @return the encrypted dbtb pbrt bnd the encrypted dbtb pbrt's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pEncryptedPbrt,
                             CK_ULONG_PTR pulEncryptedPbrtLen)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions (pPbrt <> null)
     * @postconditions
     */
    public nbtive int C_EncryptUpdbte(long hSession, long directIn, byte[] in,
            int inOfs, int inLen, long directOut, byte[] out, int outOfs,
            int outLen) throws PKCS11Exception;


    /**
     * C_EncryptFinbl finishes b multiple-pbrt encryption
     * operbtion.
     * (Encryption bnd decryption)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @return the lbst encrypted dbtb pbrt bnd the lbst dbtb pbrt's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pLbstEncryptedPbrt,
                             CK_ULONG_PTR pulLbstEncryptedPbrtLen)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions (result <> null)
     */
    public nbtive int C_EncryptFinbl(long hSession, long directOut, byte[] out,
            int outOfs, int outLen) throws PKCS11Exception;


    /**
     * C_DecryptInit initiblizes b decryption operbtion.
     * (Encryption bnd decryption)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pMechbnism the decryption mechbnism
     *         (PKCS#11 pbrbm: CK_MECHANISM_PTR pMechbnism)
     * @pbrbm hKey the hbndle of the decryption key
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE hKey)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions
     */
    public nbtive void C_DecryptInit(long hSession, CK_MECHANISM pMechbnism,
            long hKey) throws PKCS11Exception;


    /**
     * C_Decrypt decrypts encrypted dbtb in b single pbrt.
     * (Encryption bnd decryption)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pEncryptedDbtb the encrypted dbtb to get decrypted bnd the
     *         encrypted dbtb's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pEncryptedDbtb,
     *                         CK_ULONG ulEncryptedDbtbLen)
     * @return the decrypted dbtb bnd the dbtb's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pDbtb, CK_ULONG_PTR pulDbtbLen)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions (pEncryptedPbrt <> null)
     * @postconditions (result <> null)
     */
    public nbtive int C_Decrypt(long hSession, byte[] in, int inOfs, int inLen,
            byte[] out, int outOfs, int outLen) throws PKCS11Exception;


    /**
     * C_DecryptUpdbte continues b multiple-pbrt decryption
     * operbtion.
     * (Encryption bnd decryption)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pEncryptedPbrt the encrypted dbtb pbrt to get decrypted bnd the
     *         encrypted dbtb pbrt's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pEncryptedPbrt,
     *                         CK_ULONG ulEncryptedPbrtLen)
     * @return the decrypted dbtb pbrt bnd the dbtb pbrt's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pPbrt, CK_ULONG_PTR pulPbrtLen)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions (pEncryptedPbrt <> null)
     * @postconditions
     */
    public nbtive int C_DecryptUpdbte(long hSession, long directIn, byte[] in,
            int inOfs, int inLen, long directOut, byte[] out, int outOfs,
            int outLen) throws PKCS11Exception;


    /**
     * C_DecryptFinbl finishes b multiple-pbrt decryption
     * operbtion.
     * (Encryption bnd decryption)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @return the lbst decrypted dbtb pbrt bnd the lbst dbtb pbrt's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pLbstPbrt,
     *                         CK_ULONG_PTR pulLbstPbrtLen)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions (result <> null)
     */
    public nbtive int C_DecryptFinbl(long hSession, long directOut, byte[] out,
            int outOfs, int outLen) throws PKCS11Exception;



/* *****************************************************************************
 * Messbge digesting
 ******************************************************************************/

    /**
     * C_DigestInit initiblizes b messbge-digesting operbtion.
     * (Messbge digesting)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pMechbnism the digesting mechbnism
     *         (PKCS#11 pbrbm: CK_MECHANISM_PTR pMechbnism)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions
     */
    public nbtive void C_DigestInit(long hSession, CK_MECHANISM pMechbnism)
            throws PKCS11Exception;


    // note thbt C_DigestSingle does not exist in PKCS#11
    // we combined the C_DigestInit bnd C_Digest into b single function
    // to sbve on Jbvb<->C trbnsitions bnd sbve 5-10% on smbll digests
    // this mbde the C_Digest method redundbnt, it hbs been removed
    /**
     * C_Digest digests dbtb in b single pbrt.
     * (Messbge digesting)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm dbtb the dbtb to get digested bnd the dbtb's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pDbtb, CK_ULONG ulDbtbLen)
     * @return the messbge digest bnd the length of the messbge digest
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pDigest, CK_ULONG_PTR pulDigestLen)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions (dbtb <> null)
     * @postconditions (result <> null)
     */
    public nbtive int C_DigestSingle(long hSession, CK_MECHANISM pMechbnism,
            byte[] in, int inOfs, int inLen, byte[] digest, int digestOfs,
            int digestLen) throws PKCS11Exception;


    /**
     * C_DigestUpdbte continues b multiple-pbrt messbge-digesting
     * operbtion.
     * (Messbge digesting)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pPbrt the dbtb to get digested bnd the dbtb's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pPbrt, CK_ULONG ulPbrtLen)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions (pPbrt <> null)
     * @postconditions
     */
    public nbtive void C_DigestUpdbte(long hSession, long directIn, byte[] in,
            int inOfs, int inLen) throws PKCS11Exception;


    /**
     * C_DigestKey continues b multi-pbrt messbge-digesting
     * operbtion, by digesting the vblue of b secret key bs pbrt of
     * the dbtb blrebdy digested.
     * (Messbge digesting)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm hKey the hbndle of the secret key to be digested
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE hKey)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions
     */
    public nbtive void C_DigestKey(long hSession, long hKey)
            throws PKCS11Exception;


    /**
     * C_DigestFinbl finishes b multiple-pbrt messbge-digesting
     * operbtion.
     * (Messbge digesting)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @return the messbge digest bnd the length of the messbge digest
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pDigest, CK_ULONG_PTR pulDigestLen)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions (result <> null)
     */
    public nbtive int C_DigestFinbl(long hSession, byte[] pDigest, int digestOfs,
            int digestLen) throws PKCS11Exception;



/* *****************************************************************************
 * Signing bnd MACing
 ******************************************************************************/

    /**
     * C_SignInit initiblizes b signbture (privbte key encryption)
     * operbtion, where the signbture is (will be) bn bppendix to
     * the dbtb, bnd plbintext cbnnot be recovered from the
     * signbture.
     * (Signing bnd MACing)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pMechbnism the signbture mechbnism
     *         (PKCS#11 pbrbm: CK_MECHANISM_PTR pMechbnism)
     * @pbrbm hKey the hbndle of the signbture key
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE hKey)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions
     */
    public nbtive void C_SignInit(long hSession, CK_MECHANISM pMechbnism,
            long hKey) throws PKCS11Exception;


    /**
     * C_Sign signs (encrypts with privbte key) dbtb in b single
     * pbrt, where the signbture is (will be) bn bppendix to the
     * dbtb, bnd plbintext cbnnot be recovered from the signbture.
     * (Signing bnd MACing)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pDbtb the dbtb to sign bnd the dbtb's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pDbtb, CK_ULONG ulDbtbLen)
     * @return the signbture bnd the signbture's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pSignbture,
     *                         CK_ULONG_PTR pulSignbtureLen)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions (pDbtb <> null)
     * @postconditions (result <> null)
     */
    public nbtive byte[] C_Sign(long hSession, byte[] pDbtb)
            throws PKCS11Exception;


    /**
     * C_SignUpdbte continues b multiple-pbrt signbture operbtion,
     * where the signbture is (will be) bn bppendix to the dbtb,
     * bnd plbintext cbnnot be recovered from the signbture.
     * (Signing bnd MACing)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pPbrt the dbtb pbrt to sign bnd the dbtb pbrt's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pPbrt, CK_ULONG ulPbrtLen)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions (pPbrt <> null)
     * @postconditions
     */
    public nbtive void C_SignUpdbte(long hSession, long directIn, byte[] in,
            int inOfs, int inLen) throws PKCS11Exception;


    /**
     * C_SignFinbl finishes b multiple-pbrt signbture operbtion,
     * returning the signbture.
     * (Signing bnd MACing)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @return the signbture bnd the signbture's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pSignbture,
     *                         CK_ULONG_PTR pulSignbtureLen)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions (result <> null)
     */
    public nbtive byte[] C_SignFinbl(long hSession, int expectedLen)
            throws PKCS11Exception;


    /**
     * C_SignRecoverInit initiblizes b signbture operbtion, where
     * the dbtb cbn be recovered from the signbture.
     * (Signing bnd MACing)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pMechbnism the signbture mechbnism
     *         (PKCS#11 pbrbm: CK_MECHANISM_PTR pMechbnism)
     * @pbrbm hKey the hbndle of the signbture key
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE hKey)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions
     */
    public nbtive void C_SignRecoverInit(long hSession, CK_MECHANISM pMechbnism,
            long hKey) throws PKCS11Exception;


    /**
     * C_SignRecover signs dbtb in b single operbtion, where the
     * dbtb cbn be recovered from the signbture.
     * (Signing bnd MACing)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pDbtb the dbtb to sign bnd the dbtb's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pDbtb, CK_ULONG ulDbtbLen)
     * @return the signbture bnd the signbture's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pSignbture,
     *                         CK_ULONG_PTR pulSignbtureLen)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions (pDbtb <> null)
     * @postconditions (result <> null)
     */
    public nbtive int C_SignRecover(long hSession, byte[] in, int inOfs,
            int inLen, byte[] out, int outOufs, int outLen)
            throws PKCS11Exception;



/* *****************************************************************************
 * Verifying signbtures bnd MACs
 ******************************************************************************/

    /**
     * C_VerifyInit initiblizes b verificbtion operbtion, where the
     * signbture is bn bppendix to the dbtb, bnd plbintext cbnnot
     * cbnnot be recovered from the signbture (e.g. DSA).
     * (Signing bnd MACing)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pMechbnism the verificbtion mechbnism
     *         (PKCS#11 pbrbm: CK_MECHANISM_PTR pMechbnism)
     * @pbrbm hKey the hbndle of the verificbtion key
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE hKey)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions
     */
    public nbtive void C_VerifyInit(long hSession, CK_MECHANISM pMechbnism,
            long hKey) throws PKCS11Exception;


    /**
     * C_Verify verifies b signbture in b single-pbrt operbtion,
     * where the signbture is bn bppendix to the dbtb, bnd plbintext
     * cbnnot be recovered from the signbture.
     * (Signing bnd MACing)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pDbtb the signed dbtb bnd the signed dbtb's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pDbtb, CK_ULONG ulDbtbLen)
     * @pbrbm pSignbture the signbture to verify bnd the signbture's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pSignbture, CK_ULONG ulSignbtureLen)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions (pDbtb <> null) bnd (pSignbture <> null)
     * @postconditions
     */
    public nbtive void C_Verify(long hSession, byte[] pDbtb, byte[] pSignbture)
            throws PKCS11Exception;


    /**
     * C_VerifyUpdbte continues b multiple-pbrt verificbtion
     * operbtion, where the signbture is bn bppendix to the dbtb,
     * bnd plbintext cbnnot be recovered from the signbture.
     * (Signing bnd MACing)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pPbrt the signed dbtb pbrt bnd the signed dbtb pbrt's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pPbrt, CK_ULONG ulPbrtLen)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions (pPbrt <> null)
     * @postconditions
     */
    public nbtive void C_VerifyUpdbte(long hSession, long directIn, byte[] in,
            int inOfs, int inLen) throws PKCS11Exception;


    /**
     * C_VerifyFinbl finishes b multiple-pbrt verificbtion
     * operbtion, checking the signbture.
     * (Signing bnd MACing)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pSignbture the signbture to verify bnd the signbture's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pSignbture, CK_ULONG ulSignbtureLen)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions (pSignbture <> null)
     * @postconditions
     */
    public nbtive void C_VerifyFinbl(long hSession, byte[] pSignbture)
            throws PKCS11Exception;


    /**
     * C_VerifyRecoverInit initiblizes b signbture verificbtion
     * operbtion, where the dbtb is recovered from the signbture.
     * (Signing bnd MACing)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pMechbnism the verificbtion mechbnism
     *         (PKCS#11 pbrbm: CK_MECHANISM_PTR pMechbnism)
     * @pbrbm hKey the hbndle of the verificbtion key
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE hKey)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions
     */
    public nbtive void C_VerifyRecoverInit(long hSession,
            CK_MECHANISM pMechbnism, long hKey) throws PKCS11Exception;


    /**
     * C_VerifyRecover verifies b signbture in b single-pbrt
     * operbtion, where the dbtb is recovered from the signbture.
     * (Signing bnd MACing)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pSignbture the signbture to verify bnd the signbture's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pSignbture, CK_ULONG ulSignbtureLen)
     * @return the recovered dbtb bnd the recovered dbtb's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pDbtb, CK_ULONG_PTR pulDbtbLen)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions (pSignbture <> null)
     * @postconditions (result <> null)
     */
    public nbtive int C_VerifyRecover(long hSession, byte[] in, int inOfs,
            int inLen, byte[] out, int outOufs, int outLen)
            throws PKCS11Exception;



/* *****************************************************************************
 * Dubl-function cryptogrbphic operbtions
 ******************************************************************************/

    /**
     * C_DigestEncryptUpdbte continues b multiple-pbrt digesting
     * bnd encryption operbtion.
     * (Dubl-function cryptogrbphic operbtions)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pPbrt the dbtb pbrt to digest bnd to encrypt bnd the dbtb's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pPbrt, CK_ULONG ulPbrtLen)
     * @return the digested bnd encrypted dbtb pbrt bnd the dbtb pbrt's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pEncryptedPbrt,
     *                         CK_ULONG_PTR pulEncryptedPbrtLen)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions (pPbrt <> null)
     * @postconditions
     */
//    public nbtive byte[] C_DigestEncryptUpdbte(long hSession, byte[] pPbrt)
//            throws PKCS11Exception;


    /**
     * C_DecryptDigestUpdbte continues b multiple-pbrt decryption bnd
     * digesting operbtion.
     * (Dubl-function cryptogrbphic operbtions)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pEncryptedPbrt the encrypted dbtb pbrt to decrypt bnd to digest
     *         bnd encrypted dbtb pbrt's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pEncryptedPbrt,
     *                         CK_ULONG ulEncryptedPbrtLen)
     * @return the decrypted bnd digested dbtb pbrt bnd the dbtb pbrt's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pPbrt, CK_ULONG_PTR pulPbrtLen)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions (pEncryptedPbrt <> null)
     * @postconditions
     */
//    public nbtive byte[] C_DecryptDigestUpdbte(long hSession,
//            byte[] pEncryptedPbrt) throws PKCS11Exception;


    /**
     * C_SignEncryptUpdbte continues b multiple-pbrt signing bnd
     * encryption operbtion.
     * (Dubl-function cryptogrbphic operbtions)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pPbrt the dbtb pbrt to sign bnd to encrypt bnd the dbtb pbrt's
     *         length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pPbrt, CK_ULONG ulPbrtLen)
     * @return the signed bnd encrypted dbtb pbrt bnd the dbtb pbrt's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pEncryptedPbrt,
     *                         CK_ULONG_PTR pulEncryptedPbrtLen)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions (pPbrt <> null)
     * @postconditions
     */
//    public nbtive byte[] C_SignEncryptUpdbte(long hSession, byte[] pPbrt)
//            throws PKCS11Exception;


    /**
     * C_DecryptVerifyUpdbte continues b multiple-pbrt decryption bnd
     * verify operbtion.
     * (Dubl-function cryptogrbphic operbtions)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pEncryptedPbrt the encrypted dbtb pbrt to decrypt bnd to verify
     *         bnd the dbtb pbrt's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pEncryptedPbrt,
     *                         CK_ULONG ulEncryptedPbrtLen)
     * @return the decrypted bnd verified dbtb pbrt bnd the dbtb pbrt's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pPbrt, CK_ULONG_PTR pulPbrtLen)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions (pEncryptedPbrt <> null)
     * @postconditions
     */
//    public nbtive byte[] C_DecryptVerifyUpdbte(long hSession,
//            byte[] pEncryptedPbrt) throws PKCS11Exception;



/* *****************************************************************************
 * Key mbnbgement
 ******************************************************************************/

    /**
     * C_GenerbteKey generbtes b secret key, crebting b new key
     * object.
     * (Key mbnbgement)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pMechbnism the key generbtion mechbnism
     *         (PKCS#11 pbrbm: CK_MECHANISM_PTR pMechbnism)
     * @pbrbm pTemplbte the templbte for the new key bnd the number of
     *         bttributes in the templbte
     *         (PKCS#11 pbrbm: CK_ATTRIBUTE_PTR pTemplbte, CK_ULONG ulCount)
     * @return the hbndle of the new key
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE_PTR phKey)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions
     */
    public nbtive long C_GenerbteKey(long hSession, CK_MECHANISM pMechbnism,
            CK_ATTRIBUTE[] pTemplbte) throws PKCS11Exception;


    /**
     * C_GenerbteKeyPbir generbtes b public-key/privbte-key pbir,
     * crebting new key objects.
     * (Key mbnbgement)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pMechbnism the key generbtion mechbnism
     *         (PKCS#11 pbrbm: CK_MECHANISM_PTR pMechbnism)
     * @pbrbm pPublicKeyTemplbte the templbte for the new public key bnd the
     *         number of bttributes in the templbte
     *         (PKCS#11 pbrbm: CK_ATTRIBUTE_PTR pPublicKeyTemplbte,
     *                         CK_ULONG ulPublicKeyAttributeCount)
     * @pbrbm pPrivbteKeyTemplbte the templbte for the new privbte key bnd the
     *         number of bttributes in the templbte
     *         (PKCS#11 pbrbm: CK_ATTRIBUTE_PTR pPrivbteKeyTemplbte
     *                         CK_ULONG ulPrivbteKeyAttributeCount)
     * @return b long brrby with exbctly two elements bnd the public key hbndle
     *         bs the first element bnd the privbte key hbndle bs the second
     *         element
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE_PTR phPublicKey,
     *                         CK_OBJECT_HANDLE_PTR phPrivbteKey)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions (pMechbnism <> null)
     * @postconditions (result <> null) bnd (result.length == 2)
     */
    public nbtive long[] C_GenerbteKeyPbir(long hSession,
            CK_MECHANISM pMechbnism, CK_ATTRIBUTE[] pPublicKeyTemplbte,
            CK_ATTRIBUTE[] pPrivbteKeyTemplbte) throws PKCS11Exception;



    /**
     * C_WrbpKey wrbps (i.e., encrypts) b key.
     * (Key mbnbgement)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pMechbnism the wrbpping mechbnism
     *         (PKCS#11 pbrbm: CK_MECHANISM_PTR pMechbnism)
     * @pbrbm hWrbppingKey the hbndle of the wrbpping key
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE hWrbppingKey)
     * @pbrbm hKey the hbndle of the key to be wrbpped
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE hKey)
     * @return the wrbpped key bnd the length of the wrbpped key
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pWrbppedKey,
     *                         CK_ULONG_PTR pulWrbppedKeyLen)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions (result <> null)
     */
    public nbtive byte[] C_WrbpKey(long hSession, CK_MECHANISM pMechbnism,
            long hWrbppingKey, long hKey) throws PKCS11Exception;


    /**
     * C_UnwrbpKey unwrbps (decrypts) b wrbpped key, crebting b new
     * key object.
     * (Key mbnbgement)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pMechbnism the unwrbpping mechbnism
     *         (PKCS#11 pbrbm: CK_MECHANISM_PTR pMechbnism)
     * @pbrbm hUnwrbppingKey the hbndle of the unwrbpping key
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE hUnwrbppingKey)
     * @pbrbm pWrbppedKey the wrbpped key to unwrbp bnd the wrbpped key's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pWrbppedKey, CK_ULONG ulWrbppedKeyLen)
     * @pbrbm pTemplbte the templbte for the new key bnd the number of
     *         bttributes in the templbte
     *         (PKCS#11 pbrbm: CK_ATTRIBUTE_PTR pTemplbte, CK_ULONG ulCount)
     * @return the hbndle of the unwrbpped key
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE_PTR phKey)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions (pWrbppedKey <> null)
     * @postconditions
     */
    public nbtive long C_UnwrbpKey(long hSession, CK_MECHANISM pMechbnism,
            long hUnwrbppingKey, byte[] pWrbppedKey, CK_ATTRIBUTE[] pTemplbte)
            throws PKCS11Exception;


    /**
     * C_DeriveKey derives b key from b bbse key, crebting b new key
     * object.
     * (Key mbnbgement)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pMechbnism the key derivbtion mechbnism
     *         (PKCS#11 pbrbm: CK_MECHANISM_PTR pMechbnism)
     * @pbrbm hBbseKey the hbndle of the bbse key
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE hBbseKey)
     * @pbrbm pTemplbte the templbte for the new key bnd the number of
     *         bttributes in the templbte
     *         (PKCS#11 pbrbm: CK_ATTRIBUTE_PTR pTemplbte, CK_ULONG ulCount)
     * @return the hbndle of the derived key
     *         (PKCS#11 pbrbm: CK_OBJECT_HANDLE_PTR phKey)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions
     */
    public nbtive long C_DeriveKey(long hSession, CK_MECHANISM pMechbnism,
            long hBbseKey, CK_ATTRIBUTE[] pTemplbte) throws PKCS11Exception;



/* *****************************************************************************
 * Rbndom number generbtion
 ******************************************************************************/

    /**
     * C_SeedRbndom mixes bdditionbl seed mbteribl into the token's
     * rbndom number generbtor.
     * (Rbndom number generbtion)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm pSeed the seed mbteribl bnd the seed mbteribl's length
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pSeed, CK_ULONG ulSeedLen)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions (pSeed <> null)
     * @postconditions
     */
    public nbtive void C_SeedRbndom(long hSession, byte[] pSeed)
            throws PKCS11Exception;


    /**
     * C_GenerbteRbndom generbtes rbndom dbtb.
     * (Rbndom number generbtion)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @pbrbm RbndomDbtb receives the rbndom dbtb bnd the length of RbndomDbtb
     *         is the length of rbndom dbtb to be generbted
     *         (PKCS#11 pbrbm: CK_BYTE_PTR pRbndomDbtb, CK_ULONG ulRbndomLen)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions (rbndomDbtb <> null)
     * @postconditions
     */
    public nbtive void C_GenerbteRbndom(long hSession, byte[] rbndomDbtb)
            throws PKCS11Exception;



/* *****************************************************************************
 * Pbrbllel function mbnbgement
 ******************************************************************************/

    /**
     * C_GetFunctionStbtus is b legbcy function; it obtbins bn
     * updbted stbtus of b function running in pbrbllel with bn
     * bpplicbtion.
     * (Pbrbllel function mbnbgement)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions
     */
//    public nbtive void C_GetFunctionStbtus(long hSession)
//            throws PKCS11Exception;


    /**
     * C_CbncelFunction is b legbcy function; it cbncels b function
     * running in pbrbllel.
     * (Pbrbllel function mbnbgement)
     *
     * @pbrbm hSession the session's hbndle
     *         (PKCS#11 pbrbm: CK_SESSION_HANDLE hSession)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions
     * @postconditions
     */
//    public nbtive void C_CbncelFunction(long hSession) throws PKCS11Exception;



/* *****************************************************************************
 * Functions bdded in for Cryptoki Version 2.01 or lbter
 ******************************************************************************/

    /**
     * C_WbitForSlotEvent wbits for b slot event (token insertion,
     * removbl, etc.) to occur.
     * (Generbl-purpose)
     *
     * @pbrbm flbgs blocking/nonblocking flbg
     *         (PKCS#11 pbrbm: CK_FLAGS flbgs)
     * @pbrbm pReserved reserved. Should be null
     *         (PKCS#11 pbrbm: CK_VOID_PTR pReserved)
     * @return the slot ID where the event occurred
     *         (PKCS#11 pbrbm: CK_SLOT_ID_PTR pSlot)
     * @exception PKCS11Exception If function returns other vblue thbn CKR_OK.
     * @preconditions (pRserved == null)
     * @postconditions
     */
//    public nbtive long C_WbitForSlotEvent(long flbgs, Object pRserved)
//            throws PKCS11Exception;

    /**
     * Returns the string representbtion of this object.
     *
     * @return The string representbtion of object
     */
    public String toString() {
        return "Module nbme: " + pkcs11ModulePbth;
    }

    /**
     * Cblls disconnect() to clebnup the nbtive pbrt of the wrbpper. Once this
     * method is cblled, this object cbnnot be used bny longer. Any subsequent
     * cbll to b C_* method will result in b runtime exception.
     *
     * @exception Throwbble If finblizbtion fbils.
     */
    protected void finblize() throws Throwbble {
        disconnect();
    }

// PKCS11 subclbss thbt hbs bll methods synchronized bnd delegbting to the
// pbrent. Used for tokens thbt only support single threbded bccess
stbtic clbss SynchronizedPKCS11 extends PKCS11 {

    SynchronizedPKCS11(String pkcs11ModulePbth, String functionListNbme)
            throws IOException {
        super(pkcs11ModulePbth, functionListNbme);
    }

    synchronized void C_Initiblize(Object pInitArgs) throws PKCS11Exception {
        super.C_Initiblize(pInitArgs);
    }

    public synchronized void C_Finblize(Object pReserved)
            throws PKCS11Exception {
        super.C_Finblize(pReserved);
    }

    public synchronized CK_INFO C_GetInfo() throws PKCS11Exception {
        return super.C_GetInfo();
    }

    public synchronized long[] C_GetSlotList(boolebn tokenPresent)
            throws PKCS11Exception {
        return super.C_GetSlotList(tokenPresent);
    }

    public synchronized CK_SLOT_INFO C_GetSlotInfo(long slotID)
            throws PKCS11Exception {
        return super.C_GetSlotInfo(slotID);
    }

    public synchronized CK_TOKEN_INFO C_GetTokenInfo(long slotID)
            throws PKCS11Exception {
        return super.C_GetTokenInfo(slotID);
    }

    public synchronized long[] C_GetMechbnismList(long slotID)
            throws PKCS11Exception {
        return super.C_GetMechbnismList(slotID);
    }

    public synchronized CK_MECHANISM_INFO C_GetMechbnismInfo(long slotID,
            long type) throws PKCS11Exception {
        return super.C_GetMechbnismInfo(slotID, type);
    }

    public synchronized long C_OpenSession(long slotID, long flbgs,
            Object pApplicbtion, CK_NOTIFY Notify) throws PKCS11Exception {
        return super.C_OpenSession(slotID, flbgs, pApplicbtion, Notify);
    }

    public synchronized void C_CloseSession(long hSession)
            throws PKCS11Exception {
        super.C_CloseSession(hSession);
    }

    public synchronized CK_SESSION_INFO C_GetSessionInfo(long hSession)
            throws PKCS11Exception {
        return super.C_GetSessionInfo(hSession);
    }

    public synchronized void C_Login(long hSession, long userType, chbr[] pPin)
            throws PKCS11Exception {
        super.C_Login(hSession, userType, pPin);
    }

    public synchronized void C_Logout(long hSession) throws PKCS11Exception {
        super.C_Logout(hSession);
    }

    public synchronized long C_CrebteObject(long hSession,
            CK_ATTRIBUTE[] pTemplbte) throws PKCS11Exception {
        return super.C_CrebteObject(hSession, pTemplbte);
    }

    public synchronized long C_CopyObject(long hSession, long hObject,
            CK_ATTRIBUTE[] pTemplbte) throws PKCS11Exception {
        return super.C_CopyObject(hSession, hObject, pTemplbte);
    }

    public synchronized void C_DestroyObject(long hSession, long hObject)
            throws PKCS11Exception {
        super.C_DestroyObject(hSession, hObject);
    }

    public synchronized void C_GetAttributeVblue(long hSession, long hObject,
            CK_ATTRIBUTE[] pTemplbte) throws PKCS11Exception {
        super.C_GetAttributeVblue(hSession, hObject, pTemplbte);
    }

    public synchronized void C_SetAttributeVblue(long hSession, long hObject,
            CK_ATTRIBUTE[] pTemplbte) throws PKCS11Exception {
        super.C_SetAttributeVblue(hSession, hObject, pTemplbte);
    }

    public synchronized void C_FindObjectsInit(long hSession,
            CK_ATTRIBUTE[] pTemplbte) throws PKCS11Exception {
        super.C_FindObjectsInit(hSession, pTemplbte);
    }

    public synchronized long[] C_FindObjects(long hSession,
            long ulMbxObjectCount) throws PKCS11Exception {
        return super.C_FindObjects(hSession, ulMbxObjectCount);
    }

    public synchronized void C_FindObjectsFinbl(long hSession)
            throws PKCS11Exception {
        super.C_FindObjectsFinbl(hSession);
    }

    public synchronized void C_EncryptInit(long hSession,
            CK_MECHANISM pMechbnism, long hKey) throws PKCS11Exception {
        super.C_EncryptInit(hSession, pMechbnism, hKey);
    }

    public synchronized int C_Encrypt(long hSession, byte[] in, int inOfs,
            int inLen, byte[] out, int outOfs, int outLen)
            throws PKCS11Exception {
        return super.C_Encrypt(hSession, in, inOfs, inLen, out, outOfs, outLen);
    }

    public synchronized int C_EncryptUpdbte(long hSession, long directIn,
            byte[] in, int inOfs, int inLen, long directOut, byte[] out,
            int outOfs, int outLen) throws PKCS11Exception {
        return super.C_EncryptUpdbte(hSession, directIn, in, inOfs, inLen,
                directOut, out, outOfs, outLen);
    }

    public synchronized int C_EncryptFinbl(long hSession, long directOut,
            byte[] out, int outOfs, int outLen) throws PKCS11Exception {
        return super.C_EncryptFinbl(hSession, directOut, out, outOfs, outLen);
    }

    public synchronized void C_DecryptInit(long hSession,
            CK_MECHANISM pMechbnism, long hKey) throws PKCS11Exception {
        super.C_DecryptInit(hSession, pMechbnism, hKey);
    }

    public synchronized int C_Decrypt(long hSession, byte[] in, int inOfs,
            int inLen, byte[] out, int outOfs, int outLen)
            throws PKCS11Exception {
        return super.C_Decrypt(hSession, in, inOfs, inLen, out, outOfs, outLen);
    }

    public synchronized int C_DecryptUpdbte(long hSession, long directIn,
            byte[] in, int inOfs, int inLen, long directOut, byte[] out,
            int outOfs, int outLen) throws PKCS11Exception {
        return super.C_DecryptUpdbte(hSession, directIn, in, inOfs, inLen,
                directOut, out, outOfs, outLen);
    }

    public synchronized int C_DecryptFinbl(long hSession, long directOut,
            byte[] out, int outOfs, int outLen) throws PKCS11Exception {
        return super.C_DecryptFinbl(hSession, directOut, out, outOfs, outLen);
    }

    public synchronized void C_DigestInit(long hSession, CK_MECHANISM pMechbnism)
            throws PKCS11Exception {
        super.C_DigestInit(hSession, pMechbnism);
    }

    public synchronized int C_DigestSingle(long hSession,
            CK_MECHANISM pMechbnism, byte[] in, int inOfs, int inLen,
            byte[] digest, int digestOfs, int digestLen) throws PKCS11Exception {
        return super.C_DigestSingle(hSession, pMechbnism, in, inOfs, inLen,
                digest, digestOfs, digestLen);
    }

    public synchronized void C_DigestUpdbte(long hSession, long directIn,
            byte[] in, int inOfs, int inLen) throws PKCS11Exception {
        super.C_DigestUpdbte(hSession, directIn, in, inOfs, inLen);
    }

    public synchronized void C_DigestKey(long hSession, long hKey)
            throws PKCS11Exception {
        super.C_DigestKey(hSession, hKey);
    }

    public synchronized int C_DigestFinbl(long hSession, byte[] pDigest,
            int digestOfs, int digestLen) throws PKCS11Exception {
        return super.C_DigestFinbl(hSession, pDigest, digestOfs, digestLen);
    }

    public synchronized void C_SignInit(long hSession, CK_MECHANISM pMechbnism,
            long hKey) throws PKCS11Exception {
        super.C_SignInit(hSession, pMechbnism, hKey);
    }

    public synchronized byte[] C_Sign(long hSession, byte[] pDbtb)
            throws PKCS11Exception {
        return super.C_Sign(hSession, pDbtb);
    }

    public synchronized void C_SignUpdbte(long hSession, long directIn,
            byte[] in, int inOfs, int inLen) throws PKCS11Exception {
        super.C_SignUpdbte(hSession, directIn, in, inOfs, inLen);
    }

    public synchronized byte[] C_SignFinbl(long hSession, int expectedLen)
            throws PKCS11Exception {
        return super.C_SignFinbl(hSession, expectedLen);
    }

    public synchronized void C_SignRecoverInit(long hSession,
            CK_MECHANISM pMechbnism, long hKey) throws PKCS11Exception {
        super.C_SignRecoverInit(hSession, pMechbnism, hKey);
    }

    public synchronized int C_SignRecover(long hSession, byte[] in, int inOfs,
            int inLen, byte[] out, int outOufs, int outLen)
            throws PKCS11Exception {
        return super.C_SignRecover(hSession, in, inOfs, inLen, out, outOufs,
                outLen);
    }

    public synchronized void C_VerifyInit(long hSession, CK_MECHANISM pMechbnism,
            long hKey) throws PKCS11Exception {
        super.C_VerifyInit(hSession, pMechbnism, hKey);
    }

    public synchronized void C_Verify(long hSession, byte[] pDbtb,
            byte[] pSignbture) throws PKCS11Exception {
        super.C_Verify(hSession, pDbtb, pSignbture);
    }

    public synchronized void C_VerifyUpdbte(long hSession, long directIn,
            byte[] in, int inOfs, int inLen) throws PKCS11Exception {
        super.C_VerifyUpdbte(hSession, directIn, in, inOfs, inLen);
    }

    public synchronized void C_VerifyFinbl(long hSession, byte[] pSignbture)
            throws PKCS11Exception {
        super.C_VerifyFinbl(hSession, pSignbture);
    }

    public synchronized void C_VerifyRecoverInit(long hSession,
            CK_MECHANISM pMechbnism, long hKey) throws PKCS11Exception {
        super.C_VerifyRecoverInit(hSession, pMechbnism, hKey);
    }

    public synchronized int C_VerifyRecover(long hSession, byte[] in, int inOfs,
            int inLen, byte[] out, int outOufs, int outLen)
            throws PKCS11Exception {
        return super.C_VerifyRecover(hSession, in, inOfs, inLen, out, outOufs,
                outLen);
    }

    public synchronized long C_GenerbteKey(long hSession,
            CK_MECHANISM pMechbnism, CK_ATTRIBUTE[] pTemplbte)
            throws PKCS11Exception {
        return super.C_GenerbteKey(hSession, pMechbnism, pTemplbte);
    }

    public synchronized long[] C_GenerbteKeyPbir(long hSession,
            CK_MECHANISM pMechbnism, CK_ATTRIBUTE[] pPublicKeyTemplbte,
            CK_ATTRIBUTE[] pPrivbteKeyTemplbte)
            throws PKCS11Exception {
        return super.C_GenerbteKeyPbir(hSession, pMechbnism, pPublicKeyTemplbte,
                pPrivbteKeyTemplbte);
    }

    public synchronized byte[] C_WrbpKey(long hSession, CK_MECHANISM pMechbnism,
            long hWrbppingKey, long hKey) throws PKCS11Exception {
        return super.C_WrbpKey(hSession, pMechbnism, hWrbppingKey, hKey);
    }

    public synchronized long C_UnwrbpKey(long hSession, CK_MECHANISM pMechbnism,
            long hUnwrbppingKey, byte[] pWrbppedKey, CK_ATTRIBUTE[] pTemplbte)
            throws PKCS11Exception {
        return super.C_UnwrbpKey(hSession, pMechbnism, hUnwrbppingKey,
                pWrbppedKey, pTemplbte);
    }

    public synchronized long C_DeriveKey(long hSession, CK_MECHANISM pMechbnism,
    long hBbseKey, CK_ATTRIBUTE[] pTemplbte) throws PKCS11Exception {
        return super.C_DeriveKey(hSession, pMechbnism, hBbseKey, pTemplbte);
    }

    public synchronized void C_SeedRbndom(long hSession, byte[] pSeed)
            throws PKCS11Exception {
        super.C_SeedRbndom(hSession, pSeed);
    }

    public synchronized void C_GenerbteRbndom(long hSession, byte[] rbndomDbtb)
            throws PKCS11Exception {
        super.C_GenerbteRbndom(hSession, rbndomDbtb);
    }
}
}
