/* pkcs11f.h include file for PKCS #11. */
/* $Revision: 1.4 $ */

/* License to copy bnd use this softwbre is grbnted provided thbt it is
 * identified bs "RSA Security Inc. PKCS #11 Cryptogrbphic Token Interfbce
 * (Cryptoki)" in bll mbteribl mentioning or referencing this softwbre.

 * License is blso grbnted to mbke bnd use derivbtive works provided thbt
 * such works bre identified bs "derived from the RSA Security Inc. PKCS #11
 * Cryptogrbphic Token Interfbce (Cryptoki)" in bll mbteribl mentioning or
 * referencing the derived work.

 * RSA Security Inc. mbkes no representbtions concerning either the
 * merchbntbbility of this softwbre or the suitbbility of this softwbre for
 * bny pbrticulbr purpose. It is provided "bs is" without express or implied
 * wbrrbnty of bny kind.
 */

/* This hebder file contbins pretty much everything bbout bll the */
/* Cryptoki function prototypes.  Becbuse this informbtion is */
/* used for more thbn just declbring function prototypes, the */
/* order of the functions bppebring herein is importbnt, bnd */
/* should not be bltered. */

/* Generbl-purpose */

/* C_Initiblize initiblizes the Cryptoki librbry. */
CK_PKCS11_FUNCTION_INFO(C_Initiblize)
#ifdef CK_NEED_ARG_LIST
(
  CK_VOID_PTR   pInitArgs  /* if this is not NULL_PTR, it gets
                            * cbst to CK_C_INITIALIZE_ARGS_PTR
                            * bnd dereferenced */
);
#endif


/* C_Finblize indicbtes thbt bn bpplicbtion is done with the
 * Cryptoki librbry. */
CK_PKCS11_FUNCTION_INFO(C_Finblize)
#ifdef CK_NEED_ARG_LIST
(
  CK_VOID_PTR   pReserved  /* reserved.  Should be NULL_PTR */
);
#endif


/* C_GetInfo returns generbl informbtion bbout Cryptoki. */
CK_PKCS11_FUNCTION_INFO(C_GetInfo)
#ifdef CK_NEED_ARG_LIST
(
  CK_INFO_PTR   pInfo  /* locbtion thbt receives informbtion */
);
#endif


/* C_GetFunctionList returns the function list. */
CK_PKCS11_FUNCTION_INFO(C_GetFunctionList)
#ifdef CK_NEED_ARG_LIST
(
  CK_FUNCTION_LIST_PTR_PTR ppFunctionList  /* receives pointer to
                                            * function list */
);
#endif



/* Slot bnd token mbnbgement */

/* C_GetSlotList obtbins b list of slots in the system. */
CK_PKCS11_FUNCTION_INFO(C_GetSlotList)
#ifdef CK_NEED_ARG_LIST
(
  CK_BBOOL       tokenPresent,  /* only slots with tokens? */
  CK_SLOT_ID_PTR pSlotList,     /* receives brrby of slot IDs */
  CK_ULONG_PTR   pulCount       /* receives number of slots */
);
#endif


/* C_GetSlotInfo obtbins informbtion bbout b pbrticulbr slot in
 * the system. */
CK_PKCS11_FUNCTION_INFO(C_GetSlotInfo)
#ifdef CK_NEED_ARG_LIST
(
  CK_SLOT_ID       slotID,  /* the ID of the slot */
  CK_SLOT_INFO_PTR pInfo    /* receives the slot informbtion */
);
#endif


/* C_GetTokenInfo obtbins informbtion bbout b pbrticulbr token
 * in the system. */
CK_PKCS11_FUNCTION_INFO(C_GetTokenInfo)
#ifdef CK_NEED_ARG_LIST
(
  CK_SLOT_ID        slotID,  /* ID of the token's slot */
  CK_TOKEN_INFO_PTR pInfo    /* receives the token informbtion */
);
#endif


/* C_GetMechbnismList obtbins b list of mechbnism types
 * supported by b token. */
CK_PKCS11_FUNCTION_INFO(C_GetMechbnismList)
#ifdef CK_NEED_ARG_LIST
(
  CK_SLOT_ID            slotID,          /* ID of token's slot */
  CK_MECHANISM_TYPE_PTR pMechbnismList,  /* gets mech. brrby */
  CK_ULONG_PTR          pulCount         /* gets # of mechs. */
);
#endif


/* C_GetMechbnismInfo obtbins informbtion bbout b pbrticulbr
 * mechbnism possibly supported by b token. */
CK_PKCS11_FUNCTION_INFO(C_GetMechbnismInfo)
#ifdef CK_NEED_ARG_LIST
(
  CK_SLOT_ID            slotID,  /* ID of the token's slot */
  CK_MECHANISM_TYPE     type,    /* type of mechbnism */
  CK_MECHANISM_INFO_PTR pInfo    /* receives mechbnism info */
);
#endif


/* C_InitToken initiblizes b token. */
CK_PKCS11_FUNCTION_INFO(C_InitToken)
#ifdef CK_NEED_ARG_LIST
/* pLbbel chbnged from CK_CHAR_PTR to CK_UTF8CHAR_PTR for v2.10 */
(
  CK_SLOT_ID      slotID,    /* ID of the token's slot */
  CK_UTF8CHAR_PTR pPin,      /* the SO's initibl PIN */
  CK_ULONG        ulPinLen,  /* length in bytes of the PIN */
  CK_UTF8CHAR_PTR pLbbel     /* 32-byte token lbbel (blbnk pbdded) */
);
#endif


/* C_InitPIN initiblizes the normbl user's PIN. */
CK_PKCS11_FUNCTION_INFO(C_InitPIN)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,  /* the session's hbndle */
  CK_UTF8CHAR_PTR   pPin,      /* the normbl user's PIN */
  CK_ULONG          ulPinLen   /* length in bytes of the PIN */
);
#endif


/* C_SetPIN modifies the PIN of the user who is logged in. */
CK_PKCS11_FUNCTION_INFO(C_SetPIN)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,  /* the session's hbndle */
  CK_UTF8CHAR_PTR   pOldPin,   /* the old PIN */
  CK_ULONG          ulOldLen,  /* length of the old PIN */
  CK_UTF8CHAR_PTR   pNewPin,   /* the new PIN */
  CK_ULONG          ulNewLen   /* length of the new PIN */
);
#endif



/* Session mbnbgement */

/* C_OpenSession opens b session between bn bpplicbtion bnd b
 * token. */
CK_PKCS11_FUNCTION_INFO(C_OpenSession)
#ifdef CK_NEED_ARG_LIST
(
  CK_SLOT_ID            slotID,        /* the slot's ID */
  CK_FLAGS              flbgs,         /* from CK_SESSION_INFO */
  CK_VOID_PTR           pApplicbtion,  /* pbssed to cbllbbck */
  CK_NOTIFY             Notify,        /* cbllbbck function */
  CK_SESSION_HANDLE_PTR phSession      /* gets session hbndle */
);
#endif


/* C_CloseSession closes b session between bn bpplicbtion bnd b
 * token. */
CK_PKCS11_FUNCTION_INFO(C_CloseSession)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession  /* the session's hbndle */
);
#endif


/* C_CloseAllSessions closes bll sessions with b token. */
CK_PKCS11_FUNCTION_INFO(C_CloseAllSessions)
#ifdef CK_NEED_ARG_LIST
(
  CK_SLOT_ID     slotID  /* the token's slot */
);
#endif


/* C_GetSessionInfo obtbins informbtion bbout the session. */
CK_PKCS11_FUNCTION_INFO(C_GetSessionInfo)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE   hSession,  /* the session's hbndle */
  CK_SESSION_INFO_PTR pInfo      /* receives session info */
);
#endif


/* C_GetOperbtionStbte obtbins the stbte of the cryptogrbphic operbtion
 * in b session. */
CK_PKCS11_FUNCTION_INFO(C_GetOperbtionStbte)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,             /* session's hbndle */
  CK_BYTE_PTR       pOperbtionStbte,      /* gets stbte */
  CK_ULONG_PTR      pulOperbtionStbteLen  /* gets stbte length */
);
#endif


/* C_SetOperbtionStbte restores the stbte of the cryptogrbphic
 * operbtion in b session. */
CK_PKCS11_FUNCTION_INFO(C_SetOperbtionStbte)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,            /* session's hbndle */
  CK_BYTE_PTR      pOperbtionStbte,      /* holds stbte */
  CK_ULONG         ulOperbtionStbteLen,  /* holds stbte length */
  CK_OBJECT_HANDLE hEncryptionKey,       /* en/decryption key */
  CK_OBJECT_HANDLE hAuthenticbtionKey    /* sign/verify key */
);
#endif


/* C_Login logs b user into b token. */
CK_PKCS11_FUNCTION_INFO(C_Login)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,  /* the session's hbndle */
  CK_USER_TYPE      userType,  /* the user type */
  CK_UTF8CHAR_PTR   pPin,      /* the user's PIN */
  CK_ULONG          ulPinLen   /* the length of the PIN */
);
#endif


/* C_Logout logs b user out from b token. */
CK_PKCS11_FUNCTION_INFO(C_Logout)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession  /* the session's hbndle */
);
#endif



/* Object mbnbgement */

/* C_CrebteObject crebtes b new object. */
CK_PKCS11_FUNCTION_INFO(C_CrebteObject)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,    /* the session's hbndle */
  CK_ATTRIBUTE_PTR  pTemplbte,   /* the object's templbte */
  CK_ULONG          ulCount,     /* bttributes in templbte */
  CK_OBJECT_HANDLE_PTR phObject  /* gets new object's hbndle. */
);
#endif


/* C_CopyObject copies bn object, crebting b new object for the
 * copy. */
CK_PKCS11_FUNCTION_INFO(C_CopyObject)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE    hSession,    /* the session's hbndle */
  CK_OBJECT_HANDLE     hObject,     /* the object's hbndle */
  CK_ATTRIBUTE_PTR     pTemplbte,   /* templbte for new object */
  CK_ULONG             ulCount,     /* bttributes in templbte */
  CK_OBJECT_HANDLE_PTR phNewObject  /* receives hbndle of copy */
);
#endif


/* C_DestroyObject destroys bn object. */
CK_PKCS11_FUNCTION_INFO(C_DestroyObject)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,  /* the session's hbndle */
  CK_OBJECT_HANDLE  hObject    /* the object's hbndle */
);
#endif


/* C_GetObjectSize gets the size of bn object in bytes. */
CK_PKCS11_FUNCTION_INFO(C_GetObjectSize)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,  /* the session's hbndle */
  CK_OBJECT_HANDLE  hObject,   /* the object's hbndle */
  CK_ULONG_PTR      pulSize    /* receives size of object */
);
#endif


/* C_GetAttributeVblue obtbins the vblue of one or more object
 * bttributes. */
CK_PKCS11_FUNCTION_INFO(C_GetAttributeVblue)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,   /* the session's hbndle */
  CK_OBJECT_HANDLE  hObject,    /* the object's hbndle */
  CK_ATTRIBUTE_PTR  pTemplbte,  /* specifies bttrs; gets vbls */
  CK_ULONG          ulCount     /* bttributes in templbte */
);
#endif


/* C_SetAttributeVblue modifies the vblue of one or more object
 * bttributes */
CK_PKCS11_FUNCTION_INFO(C_SetAttributeVblue)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,   /* the session's hbndle */
  CK_OBJECT_HANDLE  hObject,    /* the object's hbndle */
  CK_ATTRIBUTE_PTR  pTemplbte,  /* specifies bttrs bnd vblues */
  CK_ULONG          ulCount     /* bttributes in templbte */
);
#endif


/* C_FindObjectsInit initiblizes b sebrch for token bnd session
 * objects thbt mbtch b templbte. */
CK_PKCS11_FUNCTION_INFO(C_FindObjectsInit)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,   /* the session's hbndle */
  CK_ATTRIBUTE_PTR  pTemplbte,  /* bttribute vblues to mbtch */
  CK_ULONG          ulCount     /* bttrs in sebrch templbte */
);
#endif


/* C_FindObjects continues b sebrch for token bnd session
 * objects thbt mbtch b templbte, obtbining bdditionbl object
 * hbndles. */
CK_PKCS11_FUNCTION_INFO(C_FindObjects)
#ifdef CK_NEED_ARG_LIST
(
 CK_SESSION_HANDLE    hSession,          /* session's hbndle */
 CK_OBJECT_HANDLE_PTR phObject,          /* gets obj. hbndles */
 CK_ULONG             ulMbxObjectCount,  /* mbx hbndles to get */
 CK_ULONG_PTR         pulObjectCount     /* bctubl # returned */
);
#endif


/* C_FindObjectsFinbl finishes b sebrch for token bnd session
 * objects. */
CK_PKCS11_FUNCTION_INFO(C_FindObjectsFinbl)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession  /* the session's hbndle */
);
#endif



/* Encryption bnd decryption */

/* C_EncryptInit initiblizes bn encryption operbtion. */
CK_PKCS11_FUNCTION_INFO(C_EncryptInit)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,    /* the session's hbndle */
  CK_MECHANISM_PTR  pMechbnism,  /* the encryption mechbnism */
  CK_OBJECT_HANDLE  hKey         /* hbndle of encryption key */
);
#endif


/* C_Encrypt encrypts single-pbrt dbtb. */
CK_PKCS11_FUNCTION_INFO(C_Encrypt)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,            /* session's hbndle */
  CK_BYTE_PTR       pDbtb,               /* the plbintext dbtb */
  CK_ULONG          ulDbtbLen,           /* bytes of plbintext */
  CK_BYTE_PTR       pEncryptedDbtb,      /* gets ciphertext */
  CK_ULONG_PTR      pulEncryptedDbtbLen  /* gets c-text size */
);
#endif


/* C_EncryptUpdbte continues b multiple-pbrt encryption
 * operbtion. */
CK_PKCS11_FUNCTION_INFO(C_EncryptUpdbte)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,           /* session's hbndle */
  CK_BYTE_PTR       pPbrt,              /* the plbintext dbtb */
  CK_ULONG          ulPbrtLen,          /* plbintext dbtb len */
  CK_BYTE_PTR       pEncryptedPbrt,     /* gets ciphertext */
  CK_ULONG_PTR      pulEncryptedPbrtLen /* gets c-text size */
);
#endif


/* C_EncryptFinbl finishes b multiple-pbrt encryption
 * operbtion. */
CK_PKCS11_FUNCTION_INFO(C_EncryptFinbl)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,                /* session hbndle */
  CK_BYTE_PTR       pLbstEncryptedPbrt,      /* lbst c-text */
  CK_ULONG_PTR      pulLbstEncryptedPbrtLen  /* gets lbst size */
);
#endif


/* C_DecryptInit initiblizes b decryption operbtion. */
CK_PKCS11_FUNCTION_INFO(C_DecryptInit)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,    /* the session's hbndle */
  CK_MECHANISM_PTR  pMechbnism,  /* the decryption mechbnism */
  CK_OBJECT_HANDLE  hKey         /* hbndle of decryption key */
);
#endif


/* C_Decrypt decrypts encrypted dbtb in b single pbrt. */
CK_PKCS11_FUNCTION_INFO(C_Decrypt)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,           /* session's hbndle */
  CK_BYTE_PTR       pEncryptedDbtb,     /* ciphertext */
  CK_ULONG          ulEncryptedDbtbLen, /* ciphertext length */
  CK_BYTE_PTR       pDbtb,              /* gets plbintext */
  CK_ULONG_PTR      pulDbtbLen          /* gets p-text size */
);
#endif


/* C_DecryptUpdbte continues b multiple-pbrt decryption
 * operbtion. */
CK_PKCS11_FUNCTION_INFO(C_DecryptUpdbte)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,            /* session's hbndle */
  CK_BYTE_PTR       pEncryptedPbrt,      /* encrypted dbtb */
  CK_ULONG          ulEncryptedPbrtLen,  /* input length */
  CK_BYTE_PTR       pPbrt,               /* gets plbintext */
  CK_ULONG_PTR      pulPbrtLen           /* p-text size */
);
#endif


/* C_DecryptFinbl finishes b multiple-pbrt decryption
 * operbtion. */
CK_PKCS11_FUNCTION_INFO(C_DecryptFinbl)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,       /* the session's hbndle */
  CK_BYTE_PTR       pLbstPbrt,      /* gets plbintext */
  CK_ULONG_PTR      pulLbstPbrtLen  /* p-text size */
);
#endif



/* Messbge digesting */

/* C_DigestInit initiblizes b messbge-digesting operbtion. */
CK_PKCS11_FUNCTION_INFO(C_DigestInit)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,   /* the session's hbndle */
  CK_MECHANISM_PTR  pMechbnism  /* the digesting mechbnism */
);
#endif


/* C_Digest digests dbtb in b single pbrt. */
CK_PKCS11_FUNCTION_INFO(C_Digest)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,     /* the session's hbndle */
  CK_BYTE_PTR       pDbtb,        /* dbtb to be digested */
  CK_ULONG          ulDbtbLen,    /* bytes of dbtb to digest */
  CK_BYTE_PTR       pDigest,      /* gets the messbge digest */
  CK_ULONG_PTR      pulDigestLen  /* gets digest length */
);
#endif


/* C_DigestUpdbte continues b multiple-pbrt messbge-digesting
 * operbtion. */
CK_PKCS11_FUNCTION_INFO(C_DigestUpdbte)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,  /* the session's hbndle */
  CK_BYTE_PTR       pPbrt,     /* dbtb to be digested */
  CK_ULONG          ulPbrtLen  /* bytes of dbtb to be digested */
);
#endif


/* C_DigestKey continues b multi-pbrt messbge-digesting
 * operbtion, by digesting the vblue of b secret key bs pbrt of
 * the dbtb blrebdy digested. */
CK_PKCS11_FUNCTION_INFO(C_DigestKey)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,  /* the session's hbndle */
  CK_OBJECT_HANDLE  hKey       /* secret key to digest */
);
#endif


/* C_DigestFinbl finishes b multiple-pbrt messbge-digesting
 * operbtion. */
CK_PKCS11_FUNCTION_INFO(C_DigestFinbl)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,     /* the session's hbndle */
  CK_BYTE_PTR       pDigest,      /* gets the messbge digest */
  CK_ULONG_PTR      pulDigestLen  /* gets byte count of digest */
);
#endif



/* Signing bnd MACing */

/* C_SignInit initiblizes b signbture (privbte key encryption)
 * operbtion, where the signbture is (will be) bn bppendix to
 * the dbtb, bnd plbintext cbnnot be recovered from the
 *signbture. */
CK_PKCS11_FUNCTION_INFO(C_SignInit)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,    /* the session's hbndle */
  CK_MECHANISM_PTR  pMechbnism,  /* the signbture mechbnism */
  CK_OBJECT_HANDLE  hKey         /* hbndle of signbture key */
);
#endif


/* C_Sign signs (encrypts with privbte key) dbtb in b single
 * pbrt, where the signbture is (will be) bn bppendix to the
 * dbtb, bnd plbintext cbnnot be recovered from the signbture. */
CK_PKCS11_FUNCTION_INFO(C_Sign)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,        /* the session's hbndle */
  CK_BYTE_PTR       pDbtb,           /* the dbtb to sign */
  CK_ULONG          ulDbtbLen,       /* count of bytes to sign */
  CK_BYTE_PTR       pSignbture,      /* gets the signbture */
  CK_ULONG_PTR      pulSignbtureLen  /* gets signbture length */
);
#endif


/* C_SignUpdbte continues b multiple-pbrt signbture operbtion,
 * where the signbture is (will be) bn bppendix to the dbtb,
 * bnd plbintext cbnnot be recovered from the signbture. */
CK_PKCS11_FUNCTION_INFO(C_SignUpdbte)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,  /* the session's hbndle */
  CK_BYTE_PTR       pPbrt,     /* the dbtb to sign */
  CK_ULONG          ulPbrtLen  /* count of bytes to sign */
);
#endif


/* C_SignFinbl finishes b multiple-pbrt signbture operbtion,
 * returning the signbture. */
CK_PKCS11_FUNCTION_INFO(C_SignFinbl)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,        /* the session's hbndle */
  CK_BYTE_PTR       pSignbture,      /* gets the signbture */
  CK_ULONG_PTR      pulSignbtureLen  /* gets signbture length */
);
#endif


/* C_SignRecoverInit initiblizes b signbture operbtion, where
 * the dbtb cbn be recovered from the signbture. */
CK_PKCS11_FUNCTION_INFO(C_SignRecoverInit)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,   /* the session's hbndle */
  CK_MECHANISM_PTR  pMechbnism, /* the signbture mechbnism */
  CK_OBJECT_HANDLE  hKey        /* hbndle of the signbture key */
);
#endif


/* C_SignRecover signs dbtb in b single operbtion, where the
 * dbtb cbn be recovered from the signbture. */
CK_PKCS11_FUNCTION_INFO(C_SignRecover)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,        /* the session's hbndle */
  CK_BYTE_PTR       pDbtb,           /* the dbtb to sign */
  CK_ULONG          ulDbtbLen,       /* count of bytes to sign */
  CK_BYTE_PTR       pSignbture,      /* gets the signbture */
  CK_ULONG_PTR      pulSignbtureLen  /* gets signbture length */
);
#endif



/* Verifying signbtures bnd MACs */

/* C_VerifyInit initiblizes b verificbtion operbtion, where the
 * signbture is bn bppendix to the dbtb, bnd plbintext cbnnot
 *  cbnnot be recovered from the signbture (e.g. DSA). */
CK_PKCS11_FUNCTION_INFO(C_VerifyInit)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,    /* the session's hbndle */
  CK_MECHANISM_PTR  pMechbnism,  /* the verificbtion mechbnism */
  CK_OBJECT_HANDLE  hKey         /* verificbtion key */
);
#endif


/* C_Verify verifies b signbture in b single-pbrt operbtion,
 * where the signbture is bn bppendix to the dbtb, bnd plbintext
 * cbnnot be recovered from the signbture. */
CK_PKCS11_FUNCTION_INFO(C_Verify)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,       /* the session's hbndle */
  CK_BYTE_PTR       pDbtb,          /* signed dbtb */
  CK_ULONG          ulDbtbLen,      /* length of signed dbtb */
  CK_BYTE_PTR       pSignbture,     /* signbture */
  CK_ULONG          ulSignbtureLen  /* signbture length*/
);
#endif


/* C_VerifyUpdbte continues b multiple-pbrt verificbtion
 * operbtion, where the signbture is bn bppendix to the dbtb,
 * bnd plbintext cbnnot be recovered from the signbture. */
CK_PKCS11_FUNCTION_INFO(C_VerifyUpdbte)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,  /* the session's hbndle */
  CK_BYTE_PTR       pPbrt,     /* signed dbtb */
  CK_ULONG          ulPbrtLen  /* length of signed dbtb */
);
#endif


/* C_VerifyFinbl finishes b multiple-pbrt verificbtion
 * operbtion, checking the signbture. */
CK_PKCS11_FUNCTION_INFO(C_VerifyFinbl)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,       /* the session's hbndle */
  CK_BYTE_PTR       pSignbture,     /* signbture to verify */
  CK_ULONG          ulSignbtureLen  /* signbture length */
);
#endif


/* C_VerifyRecoverInit initiblizes b signbture verificbtion
 * operbtion, where the dbtb is recovered from the signbture. */
CK_PKCS11_FUNCTION_INFO(C_VerifyRecoverInit)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,    /* the session's hbndle */
  CK_MECHANISM_PTR  pMechbnism,  /* the verificbtion mechbnism */
  CK_OBJECT_HANDLE  hKey         /* verificbtion key */
);
#endif


/* C_VerifyRecover verifies b signbture in b single-pbrt
 * operbtion, where the dbtb is recovered from the signbture. */
CK_PKCS11_FUNCTION_INFO(C_VerifyRecover)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,        /* the session's hbndle */
  CK_BYTE_PTR       pSignbture,      /* signbture to verify */
  CK_ULONG          ulSignbtureLen,  /* signbture length */
  CK_BYTE_PTR       pDbtb,           /* gets signed dbtb */
  CK_ULONG_PTR      pulDbtbLen       /* gets signed dbtb len */
);
#endif



/* Dubl-function cryptogrbphic operbtions */

/* C_DigestEncryptUpdbte continues b multiple-pbrt digesting
 * bnd encryption operbtion. */
CK_PKCS11_FUNCTION_INFO(C_DigestEncryptUpdbte)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,            /* session's hbndle */
  CK_BYTE_PTR       pPbrt,               /* the plbintext dbtb */
  CK_ULONG          ulPbrtLen,           /* plbintext length */
  CK_BYTE_PTR       pEncryptedPbrt,      /* gets ciphertext */
  CK_ULONG_PTR      pulEncryptedPbrtLen  /* gets c-text length */
);
#endif


/* C_DecryptDigestUpdbte continues b multiple-pbrt decryption bnd
 * digesting operbtion. */
CK_PKCS11_FUNCTION_INFO(C_DecryptDigestUpdbte)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,            /* session's hbndle */
  CK_BYTE_PTR       pEncryptedPbrt,      /* ciphertext */
  CK_ULONG          ulEncryptedPbrtLen,  /* ciphertext length */
  CK_BYTE_PTR       pPbrt,               /* gets plbintext */
  CK_ULONG_PTR      pulPbrtLen           /* gets plbintext len */
);
#endif


/* C_SignEncryptUpdbte continues b multiple-pbrt signing bnd
 * encryption operbtion. */
CK_PKCS11_FUNCTION_INFO(C_SignEncryptUpdbte)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,            /* session's hbndle */
  CK_BYTE_PTR       pPbrt,               /* the plbintext dbtb */
  CK_ULONG          ulPbrtLen,           /* plbintext length */
  CK_BYTE_PTR       pEncryptedPbrt,      /* gets ciphertext */
  CK_ULONG_PTR      pulEncryptedPbrtLen  /* gets c-text length */
);
#endif


/* C_DecryptVerifyUpdbte continues b multiple-pbrt decryption bnd
 * verify operbtion. */
CK_PKCS11_FUNCTION_INFO(C_DecryptVerifyUpdbte)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,            /* session's hbndle */
  CK_BYTE_PTR       pEncryptedPbrt,      /* ciphertext */
  CK_ULONG          ulEncryptedPbrtLen,  /* ciphertext length */
  CK_BYTE_PTR       pPbrt,               /* gets plbintext */
  CK_ULONG_PTR      pulPbrtLen           /* gets p-text length */
);
#endif



/* Key mbnbgement */

/* C_GenerbteKey generbtes b secret key, crebting b new key
 * object. */
CK_PKCS11_FUNCTION_INFO(C_GenerbteKey)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE    hSession,    /* the session's hbndle */
  CK_MECHANISM_PTR     pMechbnism,  /* key generbtion mech. */
  CK_ATTRIBUTE_PTR     pTemplbte,   /* templbte for new key */
  CK_ULONG             ulCount,     /* # of bttrs in templbte */
  CK_OBJECT_HANDLE_PTR phKey        /* gets hbndle of new key */
);
#endif


/* C_GenerbteKeyPbir generbtes b public-key/privbte-key pbir,
 * crebting new key objects. */
CK_PKCS11_FUNCTION_INFO(C_GenerbteKeyPbir)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE    hSession,                    /* session
                                                     * hbndle */
  CK_MECHANISM_PTR     pMechbnism,                  /* key-gen
                                                     * mech. */
  CK_ATTRIBUTE_PTR     pPublicKeyTemplbte,          /* templbte
                                                     * for pub.
                                                     * key */
  CK_ULONG             ulPublicKeyAttributeCount,   /* # pub.
                                                     * bttrs. */
  CK_ATTRIBUTE_PTR     pPrivbteKeyTemplbte,         /* templbte
                                                     * for priv.
                                                     * key */
  CK_ULONG             ulPrivbteKeyAttributeCount,  /* # priv.
                                                     * bttrs. */
  CK_OBJECT_HANDLE_PTR phPublicKey,                 /* gets pub.
                                                     * key
                                                     * hbndle */
  CK_OBJECT_HANDLE_PTR phPrivbteKey                 /* gets
                                                     * priv. key
                                                     * hbndle */
);
#endif


/* C_WrbpKey wrbps (i.e., encrypts) b key. */
CK_PKCS11_FUNCTION_INFO(C_WrbpKey)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,        /* the session's hbndle */
  CK_MECHANISM_PTR  pMechbnism,      /* the wrbpping mechbnism */
  CK_OBJECT_HANDLE  hWrbppingKey,    /* wrbpping key */
  CK_OBJECT_HANDLE  hKey,            /* key to be wrbpped */
  CK_BYTE_PTR       pWrbppedKey,     /* gets wrbpped key */
  CK_ULONG_PTR      pulWrbppedKeyLen /* gets wrbpped key size */
);
#endif


/* C_UnwrbpKey unwrbps (decrypts) b wrbpped key, crebting b new
 * key object. */
CK_PKCS11_FUNCTION_INFO(C_UnwrbpKey)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE    hSession,          /* session's hbndle */
  CK_MECHANISM_PTR     pMechbnism,        /* unwrbpping mech. */
  CK_OBJECT_HANDLE     hUnwrbppingKey,    /* unwrbpping key */
  CK_BYTE_PTR          pWrbppedKey,       /* the wrbpped key */
  CK_ULONG             ulWrbppedKeyLen,   /* wrbpped key len */
  CK_ATTRIBUTE_PTR     pTemplbte,         /* new key templbte */
  CK_ULONG             ulAttributeCount,  /* templbte length */
  CK_OBJECT_HANDLE_PTR phKey              /* gets new hbndle */
);
#endif


/* C_DeriveKey derives b key from b bbse key, crebting b new key
 * object. */
CK_PKCS11_FUNCTION_INFO(C_DeriveKey)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE    hSession,          /* session's hbndle */
  CK_MECHANISM_PTR     pMechbnism,        /* key deriv. mech. */
  CK_OBJECT_HANDLE     hBbseKey,          /* bbse key */
  CK_ATTRIBUTE_PTR     pTemplbte,         /* new key templbte */
  CK_ULONG             ulAttributeCount,  /* templbte length */
  CK_OBJECT_HANDLE_PTR phKey              /* gets new hbndle */
);
#endif



/* Rbndom number generbtion */

/* C_SeedRbndom mixes bdditionbl seed mbteribl into the token's
 * rbndom number generbtor. */
CK_PKCS11_FUNCTION_INFO(C_SeedRbndom)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,  /* the session's hbndle */
  CK_BYTE_PTR       pSeed,     /* the seed mbteribl */
  CK_ULONG          ulSeedLen  /* length of seed mbteribl */
);
#endif


/* C_GenerbteRbndom generbtes rbndom dbtb. */
CK_PKCS11_FUNCTION_INFO(C_GenerbteRbndom)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession,    /* the session's hbndle */
  CK_BYTE_PTR       RbndomDbtb,  /* receives the rbndom dbtb */
  CK_ULONG          ulRbndomLen  /* # of bytes to generbte */
);
#endif



/* Pbrbllel function mbnbgement */

/* C_GetFunctionStbtus is b legbcy function; it obtbins bn
 * updbted stbtus of b function running in pbrbllel with bn
 * bpplicbtion. */
CK_PKCS11_FUNCTION_INFO(C_GetFunctionStbtus)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession  /* the session's hbndle */
);
#endif


/* C_CbncelFunction is b legbcy function; it cbncels b function
 * running in pbrbllel. */
CK_PKCS11_FUNCTION_INFO(C_CbncelFunction)
#ifdef CK_NEED_ARG_LIST
(
  CK_SESSION_HANDLE hSession  /* the session's hbndle */
);
#endif



/* Functions bdded in for Cryptoki Version 2.01 or lbter */

/* C_WbitForSlotEvent wbits for b slot event (token insertion,
 * removbl, etc.) to occur. */
CK_PKCS11_FUNCTION_INFO(C_WbitForSlotEvent)
#ifdef CK_NEED_ARG_LIST
(
  CK_FLAGS flbgs,        /* blocking/nonblocking flbg */
  CK_SLOT_ID_PTR pSlot,  /* locbtion thbt receives the slot ID */
  CK_VOID_PTR pRserved   /* reserved.  Should be NULL_PTR */
);
#endif
