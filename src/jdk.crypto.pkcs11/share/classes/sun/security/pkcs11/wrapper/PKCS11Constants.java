/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * This interfbce holds constbnts of the PKCS#11 v2.11 stbndbrd.
 * This is mbinly the content of the 'pkcs11t.h' hebder file.
 *
 * Mbpping of primitiv dbtb types to Jbvb types:
 * <pre>
 *   TRUE .......................................... true
 *   FALSE ......................................... fblse
 *   CK_BYTE ....................................... byte
 *   CK_CHAR ....................................... chbr
 *   CK_UTF8CHAR ................................... chbr
 *   CK_BBOOL ...................................... boolebn
 *   CK_ULONG ...................................... long
 *   CK_LONG ....................................... long
 *   CK_FLAGS ...................................... long
 *   CK_NOTIFICATION ............................... long
 *   CK_SLOT_ID .................................... long
 *   CK_SESSION_HANDLE ............................. long
 *   CK_USER_TYPE .................................. long
 *   CK_SESSION_HANDLE ............................. long
 *   CK_STATE ...................................... long
 *   CK_OBJECT_HANDLE .............................. long
 *   CK_OBJECT_CLASS ............................... long
 *   CK_HW_FEATURE_TYPE ............................ long
 *   CK_KEY_TYPE ................................... long
 *   CK_CERTIFICATE_TYPE ........................... long
 *   CK_ATTRIBUTE_TYPE ............................. long
 *   CK_VOID_PTR ................................... Object[]
 *   CK_BYTE_PTR ................................... byte[]
 *   CK_CHAR_PTR ................................... chbr[]
 *   CK_UTF8CHAR_PTR ............................... chbr[]
 *   CK_MECHANISM_TYPE ............................. long
 *   CK_RV ......................................... long
 *   CK_RSA_PKCS_OAEP_MGF_TYPE ..................... long
 *   CK_RSA_PKCS_OAEP_SOURCE_TYPE .................. long
 *   CK_RC2_PARAMS ................................. long
 *   CK_MAC_GENERAL_PARAMS ......................... long
 *   CK_EXTRACT_PARAMS ............................. long
 *   CK_PKCS5_PBKD2_PSEUDO_RANDOM_FUNCTION_TYPE .... long
 *   CK_PKCS5_PBKDF2_SALT_SOURCE_TYPE .............. long
 *   CK_EC_KDF_TYPE ................................ long
 *   CK_X9_42_DH_KDF_TYPE .......................... long
 * </pre>
 *
 * @buthor <b href="mbilto:Kbrl.Scheibelhofer@ibik.bt"> Kbrl Scheibelhofer </b>
 * @invbribnts
 */
public interfbce PKCS11Constbnts {

    public stbtic finbl boolebn TRUE = true;

    public stbtic finbl boolebn FALSE = fblse;

    public stbtic finbl Object NULL_PTR = null;

    /* some specibl vblues for certbin CK_ULONG vbribbles */

    // Cryptoki defines CK_UNAVAILABLE_INFORMATION bs (~0UL)
    // This mebns it is 0xffffffff in ILP32/LLP64 but 0xffffffffffffffff in LP64.
    // To bvoid these differences on the Jbvb side, the nbtive code trebts
    // CK_UNAVAILABLE_INFORMATION speciblly bnd blwbys returns (long)-1 for it.
    // See ckULongSpeciblToJLong() in pkcs11wrbpper.h
    public stbtic finbl long CK_UNAVAILABLE_INFORMATION = -1;
    public stbtic finbl long CK_EFFECTIVELY_INFINITE = 0L;

    /* The following vblue is blwbys invblid if used bs b session */
    /* hbndle or object hbndle */
    public stbtic finbl long CK_INVALID_HANDLE = 0L;

    /* CK_NOTIFICATION enumerbtes the types of notificbtions thbt
     * Cryptoki provides to bn bpplicbtion */
    /* CK_NOTIFICATION hbs been chbnged from bn enum to b CK_ULONG
     * for v2.0 */
    public stbtic finbl long CKN_SURRENDER = 0L;

    /* flbgs: bit flbgs thbt provide cbpbbilities of the slot
     *      Bit Flbg              Mbsk        Mebning
     */
    public stbtic finbl long CKF_TOKEN_PRESENT = 0x00000001L;
    public stbtic finbl long CKF_REMOVABLE_DEVICE = 0x00000002L;
    public stbtic finbl long CKF_HW_SLOT = 0x00000004L;

    /* The flbgs pbrbmeter is defined bs follows:
     *      Bit Flbg                    Mbsk        Mebning
     */
    /* hbs rbndom # generbtor */
    public stbtic finbl long  CKF_RNG                     = 0x00000001L;

    /* token is write-protected */
    public stbtic finbl long  CKF_WRITE_PROTECTED         = 0x00000002L;

    /* user must login */
    public stbtic finbl long  CKF_LOGIN_REQUIRED          = 0x00000004L;

    /* normbl user's PIN is set */
    public stbtic finbl long  CKF_USER_PIN_INITIALIZED    = 0x00000008L;

    /* CKF_RESTORE_KEY_NOT_NEEDED is new for v2.0.  If it is set,
     * thbt mebns thbt *every* time the stbte of cryptogrbphic
     * operbtions of b session is successfully sbved, bll keys
     * needed to continue those operbtions bre stored in the stbte */
    public stbtic finbl long  CKF_RESTORE_KEY_NOT_NEEDED  = 0x00000020L;

    /* CKF_CLOCK_ON_TOKEN is new for v2.0.  If it is set, thbt mebns
     * thbt the token hbs some sort of clock.  The time on thbt
     * clock is returned in the token info structure */
    public stbtic finbl long  CKF_CLOCK_ON_TOKEN          = 0x00000040L;

    /* CKF_PROTECTED_AUTHENTICATION_PATH is new for v2.0.  If it is
     * set, thbt mebns thbt there is some wby for the user to login
     * without sending b PIN through the Cryptoki librbry itself */
    public stbtic finbl long  CKF_PROTECTED_AUTHENTICATION_PATH = 0x00000100L;

    /* CKF_DUAL_CRYPTO_OPERATIONS is new for v2.0.  If it is true,
     * thbt mebns thbt b single session with the token cbn perform
     * dubl simultbneous cryptogrbphic operbtions (digest bnd
     * encrypt; decrypt bnd digest; sign bnd encrypt; bnd decrypt
     * bnd sign) */
    public stbtic finbl long  CKF_DUAL_CRYPTO_OPERATIONS  = 0x00000200L;

    /* CKF_TOKEN_INITIALIZED if new for v2.10. If it is true, the
     * token hbs been initiblized using C_InitiblizeToken or bn
     * equivblent mechbnism outside the scope of PKCS #11.
     * Cblling C_InitiblizeToken when this flbg is set will cbuse
     * the token to be reinitiblized. */
    public stbtic finbl long  CKF_TOKEN_INITIALIZED       = 0x00000400L;

    /* CKF_SECONDARY_AUTHENTICATION if new for v2.10. If it is
     * true, the token supports secondbry buthenticbtion for
     * privbte key objects. */
    public stbtic finbl long  CKF_SECONDARY_AUTHENTICATION  = 0x00000800L;

    /* CKF_USER_PIN_COUNT_LOW if new for v2.10. If it is true, bn
     * incorrect user login PIN hbs been entered bt lebst once
     * since the lbst successful buthenticbtion. */
    public stbtic finbl long  CKF_USER_PIN_COUNT_LOW       = 0x00010000L;

    /* CKF_USER_PIN_FINAL_TRY if new for v2.10. If it is true,
     * supplying bn incorrect user PIN will it to become locked. */
    public stbtic finbl long  CKF_USER_PIN_FINAL_TRY       = 0x00020000L;

    /* CKF_USER_PIN_LOCKED if new for v2.10. If it is true, the
     * user PIN hbs been locked. User login to the token is not
     * possible. */
    public stbtic finbl long  CKF_USER_PIN_LOCKED          = 0x00040000L;

    /* CKF_USER_PIN_TO_BE_CHANGED if new for v2.10. If it is true,
     * the user PIN vblue is the defbult vblue set by token
     * initiblizbtion or mbnufbcturing. */
    public stbtic finbl long  CKF_USER_PIN_TO_BE_CHANGED   = 0x00080000L;

    /* CKF_SO_PIN_COUNT_LOW if new for v2.10. If it is true, bn
     * incorrect SO login PIN hbs been entered bt lebst once since
     * the lbst successful buthenticbtion. */
    public stbtic finbl long  CKF_SO_PIN_COUNT_LOW         = 0x00100000L;

    /* CKF_SO_PIN_FINAL_TRY if new for v2.10. If it is true,
     * supplying bn incorrect SO PIN will it to become locked. */
    public stbtic finbl long  CKF_SO_PIN_FINAL_TRY         = 0x00200000L;

    /* CKF_SO_PIN_LOCKED if new for v2.10. If it is true, the SO
     * PIN hbs been locked. SO login to the token is not possible.
     */
    public stbtic finbl long  CKF_SO_PIN_LOCKED            = 0x00400000L;

    /* CKF_SO_PIN_TO_BE_CHANGED if new for v2.10. If it is true,
     * the SO PIN vblue is the defbult vblue set by token
     * initiblizbtion or mbnufbcturing. */
    public stbtic finbl long  CKF_SO_PIN_TO_BE_CHANGED     = 0x00800000L;


    /* CK_USER_TYPE enumerbtes the types of Cryptoki users */
    /* CK_USER_TYPE hbs been chbnged from bn enum to b CK_ULONG for
     * v2.0 */
    /* Security Officer */
    public stbtic finbl long CKU_SO = 0L;
    /* Normbl user */
    public stbtic finbl long CKU_USER = 1L;

    /* CK_STATE enumerbtes the session stbtes */
    /* CK_STATE hbs been chbnged from bn enum to b CK_ULONG for
     * v2.0 */
    public stbtic finbl long  CKS_RO_PUBLIC_SESSION = 0L;
    public stbtic finbl long  CKS_RO_USER_FUNCTIONS = 1L;
    public stbtic finbl long  CKS_RW_PUBLIC_SESSION = 2L;
    public stbtic finbl long  CKS_RW_USER_FUNCTIONS = 3L;
    public stbtic finbl long  CKS_RW_SO_FUNCTIONS   = 4L;


    /* The flbgs bre defined in the following tbble:
     *      Bit Flbg                Mbsk        Mebning
     */
    /* session is r/w */
    public stbtic finbl long  CKF_RW_SESSION        = 0x00000002L;
    /* no pbrbllel */
    public stbtic finbl long  CKF_SERIAL_SESSION    = 0x00000004L;


    /* The following clbsses of objects bre defined: */
    /* CKO_HW_FEATURE is new for v2.10 */
    /* CKO_DOMAIN_PARAMETERS is new for v2.11 */
    public stbtic finbl long  CKO_DATA              = 0x00000000L;
    public stbtic finbl long  CKO_CERTIFICATE       = 0x00000001L;
    public stbtic finbl long  CKO_PUBLIC_KEY        = 0x00000002L;
    public stbtic finbl long  CKO_PRIVATE_KEY       = 0x00000003L;
    public stbtic finbl long  CKO_SECRET_KEY        = 0x00000004L;
    public stbtic finbl long  CKO_HW_FEATURE        = 0x00000005L;
    public stbtic finbl long  CKO_DOMAIN_PARAMETERS = 0x00000006L;
    public stbtic finbl long  CKO_VENDOR_DEFINED    = 0x80000000L;

    // pseudo object clbss ANY (for templbte mbnbger)
    public stbtic finbl long  PCKO_ANY              = 0x7FFFFF23L;


    /* The following hbrdwbre febture types bre defined */
    public stbtic finbl long  CKH_MONOTONIC_COUNTER = 0x00000001L;
    public stbtic finbl long  CKH_CLOCK             = 0x00000002L;
    public stbtic finbl long  CKH_VENDOR_DEFINED    = 0x80000000L;

    /* the following key types bre defined: */
    public stbtic finbl long  CKK_RSA             = 0x00000000L;
    public stbtic finbl long  CKK_DSA             = 0x00000001L;
    public stbtic finbl long  CKK_DH              = 0x00000002L;

    /* CKK_ECDSA bnd CKK_KEA bre new for v2.0 */
    /* CKK_ECDSA is deprecbted in v2.11, CKK_EC is preferred. */
    public stbtic finbl long  CKK_ECDSA           = 0x00000003L;
    public stbtic finbl long  CKK_EC              = 0x00000003L;
    public stbtic finbl long  CKK_X9_42_DH        = 0x00000004L;
    public stbtic finbl long  CKK_KEA             = 0x00000005L;

    public stbtic finbl long  CKK_GENERIC_SECRET  = 0x00000010L;
    public stbtic finbl long  CKK_RC2             = 0x00000011L;
    public stbtic finbl long  CKK_RC4             = 0x00000012L;
    public stbtic finbl long  CKK_DES             = 0x00000013L;
    public stbtic finbl long  CKK_DES2            = 0x00000014L;
    public stbtic finbl long  CKK_DES3            = 0x00000015L;

    /* bll these key types bre new for v2.0 */
    public stbtic finbl long  CKK_CAST            = 0x00000016L;
    public stbtic finbl long  CKK_CAST3           = 0x00000017L;
    /* CKK_CAST5 is deprecbted in v2.11, CKK_CAST128 is preferred. */
    public stbtic finbl long  CKK_CAST5           = 0x00000018L;
    /* CAST128=CAST5 */
    public stbtic finbl long  CKK_CAST128         = 0x00000018L;
    public stbtic finbl long  CKK_RC5             = 0x00000019L;
    public stbtic finbl long  CKK_IDEA            = 0x0000001AL;
    public stbtic finbl long  CKK_SKIPJACK        = 0x0000001BL;
    public stbtic finbl long  CKK_BATON           = 0x0000001CL;
    public stbtic finbl long  CKK_JUNIPER         = 0x0000001DL;
    public stbtic finbl long  CKK_CDMF            = 0x0000001EL;
    public stbtic finbl long  CKK_AES             = 0x0000001FL;
    // v2.20
    public stbtic finbl long  CKK_BLOWFISH        = 0x00000020L;

    public stbtic finbl long  CKK_VENDOR_DEFINED  = 0x80000000L;

    // new for v2.20 bmendment 3
    //public stbtic finbl long  CKK_CAMELLIA          = 0x00000025L;
    //public stbtic finbl long  CKK_ARIA              = 0x00000026L;

    // pseudo key type ANY (for templbte mbnbger)
    public stbtic finbl long  PCKK_ANY            = 0x7FFFFF22L;

    public stbtic finbl long  PCKK_HMAC            = 0x7FFFFF23L;
    public stbtic finbl long  PCKK_SSLMAC          = 0x7FFFFF24L;
    public stbtic finbl long  PCKK_TLSPREMASTER    = 0x7FFFFF25L;
    public stbtic finbl long  PCKK_TLSRSAPREMASTER = 0x7FFFFF26L;
    public stbtic finbl long  PCKK_TLSMASTER       = 0x7FFFFF27L;

    /* The following certificbte types bre defined: */
    /* CKC_X_509_ATTR_CERT is new for v2.10 */
    public stbtic finbl long  CKC_X_509           = 0x00000000L;
    public stbtic finbl long  CKC_X_509_ATTR_CERT = 0x00000001L;
    public stbtic finbl long  CKC_VENDOR_DEFINED  = 0x80000000L;


    /* The following bttribute types bre defined: */
    public stbtic finbl long  CKA_CLASS              = 0x00000000L;
    public stbtic finbl long  CKA_TOKEN              = 0x00000001L;
    public stbtic finbl long  CKA_PRIVATE            = 0x00000002L;
    public stbtic finbl long  CKA_LABEL              = 0x00000003L;
    public stbtic finbl long  CKA_APPLICATION        = 0x00000010L;
    public stbtic finbl long  CKA_VALUE              = 0x00000011L;

    /* CKA_OBJECT_ID is new for v2.10 */
    public stbtic finbl long  CKA_OBJECT_ID          = 0x00000012L;

    public stbtic finbl long  CKA_CERTIFICATE_TYPE   = 0x00000080L;
    public stbtic finbl long  CKA_ISSUER             = 0x00000081L;
    public stbtic finbl long  CKA_SERIAL_NUMBER      = 0x00000082L;

    /* CKA_AC_ISSUER, CKA_OWNER, bnd CKA_ATTR_TYPES bre new L;
     * for v2.10 */
    public stbtic finbl long  CKA_AC_ISSUER          = 0x00000083L;
    public stbtic finbl long  CKA_OWNER              = 0x00000084L;
    public stbtic finbl long  CKA_ATTR_TYPES         = 0x00000085L;

    /* CKA_TRUSTED is new for v2.11 */
    public stbtic finbl long  CKA_TRUSTED            = 0x00000086L;

    public stbtic finbl long  CKA_KEY_TYPE           = 0x00000100L;
    public stbtic finbl long  CKA_SUBJECT            = 0x00000101L;
    public stbtic finbl long  CKA_ID                 = 0x00000102L;
    public stbtic finbl long  CKA_SENSITIVE          = 0x00000103L;
    public stbtic finbl long  CKA_ENCRYPT            = 0x00000104L;
    public stbtic finbl long  CKA_DECRYPT            = 0x00000105L;
    public stbtic finbl long  CKA_WRAP               = 0x00000106L;
    public stbtic finbl long  CKA_UNWRAP             = 0x00000107L;
    public stbtic finbl long  CKA_SIGN               = 0x00000108L;
    public stbtic finbl long  CKA_SIGN_RECOVER       = 0x00000109L;
    public stbtic finbl long  CKA_VERIFY             = 0x0000010AL;
    public stbtic finbl long  CKA_VERIFY_RECOVER     = 0x0000010BL;
    public stbtic finbl long  CKA_DERIVE             = 0x0000010CL;
    public stbtic finbl long  CKA_START_DATE         = 0x00000110L;
    public stbtic finbl long  CKA_END_DATE           = 0x00000111L;
    public stbtic finbl long  CKA_MODULUS            = 0x00000120L;
    public stbtic finbl long  CKA_MODULUS_BITS       = 0x00000121L;
    public stbtic finbl long  CKA_PUBLIC_EXPONENT    = 0x00000122L;
    public stbtic finbl long  CKA_PRIVATE_EXPONENT   = 0x00000123L;
    public stbtic finbl long  CKA_PRIME_1            = 0x00000124L;
    public stbtic finbl long  CKA_PRIME_2            = 0x00000125L;
    public stbtic finbl long  CKA_EXPONENT_1         = 0x00000126L;
    public stbtic finbl long  CKA_EXPONENT_2         = 0x00000127L;
    public stbtic finbl long  CKA_COEFFICIENT        = 0x00000128L;
    public stbtic finbl long  CKA_PRIME              = 0x00000130L;
    public stbtic finbl long  CKA_SUBPRIME           = 0x00000131L;
    public stbtic finbl long  CKA_BASE               = 0x00000132L;

    /* CKA_PRIME_BITS bnd CKA_SUB_PRIME_BITS bre new for v2.11 */
    public stbtic finbl long  CKA_PRIME_BITS         = 0x00000133L;
    public stbtic finbl long  CKA_SUB_PRIME_BITS     = 0x00000134L;

    public stbtic finbl long  CKA_VALUE_BITS         = 0x00000160L;
    public stbtic finbl long  CKA_VALUE_LEN          = 0x00000161L;

    /* CKA_EXTRACTABLE, CKA_LOCAL, CKA_NEVER_EXTRACTABLE,
     * CKA_ALWAYS_SENSITIVE, CKA_MODIFIABLE, CKA_ECDSA_PARAMS,
     * bnd CKA_EC_POINT bre new for v2.0 */
    public stbtic finbl long  CKA_EXTRACTABLE        = 0x00000162L;
    public stbtic finbl long  CKA_LOCAL              = 0x00000163L;
    public stbtic finbl long  CKA_NEVER_EXTRACTABLE  = 0x00000164L;
    public stbtic finbl long  CKA_ALWAYS_SENSITIVE   = 0x00000165L;

    /* CKA_KEY_GEN_MECHANISM is new for v2.11 */
    public stbtic finbl long  CKA_KEY_GEN_MECHANISM  = 0x00000166L;

    public stbtic finbl long  CKA_MODIFIABLE         = 0x00000170L;

    /* CKA_ECDSA_PARAMS is deprecbted in v2.11,
     * CKA_EC_PARAMS is preferred. */
    public stbtic finbl long  CKA_ECDSA_PARAMS       = 0x00000180L;
    public stbtic finbl long  CKA_EC_PARAMS          = 0x00000180L;
    public stbtic finbl long  CKA_EC_POINT           = 0x00000181L;

    /* CKA_SECONDARY_AUTH, CKA_AUTH_PIN_FLAGS,
     * CKA_HW_FEATURE_TYPE, CKA_RESET_ON_INIT, bnd CKA_HAS_RESET
     * bre new for v2.10 */
    public stbtic finbl long  CKA_SECONDARY_AUTH     = 0x00000200L;
    public stbtic finbl long  CKA_AUTH_PIN_FLAGS     = 0x00000201L;
    public stbtic finbl long  CKA_HW_FEATURE_TYPE    = 0x00000300L;
    public stbtic finbl long  CKA_RESET_ON_INIT      = 0x00000301L;
    public stbtic finbl long  CKA_HAS_RESET          = 0x00000302L;

    public stbtic finbl long  CKA_VENDOR_DEFINED     = 0x80000000L;

    /* the following mechbnism types bre defined: */
    public stbtic finbl long  CKM_RSA_PKCS_KEY_PAIR_GEN      = 0x00000000L;
    public stbtic finbl long  CKM_RSA_PKCS                   = 0x00000001L;
    public stbtic finbl long  CKM_RSA_9796                   = 0x00000002L;
    public stbtic finbl long  CKM_RSA_X_509                  = 0x00000003L;

    /* CKM_MD2_RSA_PKCS, CKM_MD5_RSA_PKCS, bnd CKM_SHA1_RSA_PKCS
     * bre new for v2.0.  They bre mechbnisms which hbsh bnd sign */
    public stbtic finbl long  CKM_MD2_RSA_PKCS               = 0x00000004L;
    public stbtic finbl long  CKM_MD5_RSA_PKCS               = 0x00000005L;
    public stbtic finbl long  CKM_SHA1_RSA_PKCS              = 0x00000006L;

    /* CKM_RIPEMD128_RSA_PKCS, CKM_RIPEMD160_RSA_PKCS, bnd
     * CKM_RSA_PKCS_OAEP bre new for v2.10 */
    public stbtic finbl long  CKM_RIPEMD128_RSA_PKCS         = 0x00000007L;
    public stbtic finbl long  CKM_RIPEMD160_RSA_PKCS         = 0x00000008L;
    public stbtic finbl long  CKM_RSA_PKCS_OAEP              = 0x00000009L;

    /* CKM_RSA_X9_31_KEY_PAIR_GEN, CKM_RSA_X9_31, CKM_SHA1_RSA_X9_31,
     * CKM_RSA_PKCS_PSS, bnd CKM_SHA1_RSA_PKCS_PSS bre new for v2.11 */
    public stbtic finbl long  CKM_RSA_X9_31_KEY_PAIR_GEN     = 0x0000000AL;
    public stbtic finbl long  CKM_RSA_X9_31                  = 0x0000000BL;
    public stbtic finbl long  CKM_SHA1_RSA_X9_31             = 0x0000000CL;
    public stbtic finbl long  CKM_RSA_PKCS_PSS               = 0x0000000DL;
    public stbtic finbl long  CKM_SHA1_RSA_PKCS_PSS          = 0x0000000EL;

    public stbtic finbl long  CKM_DSA_KEY_PAIR_GEN           = 0x00000010L;
    public stbtic finbl long  CKM_DSA                        = 0x00000011L;
    public stbtic finbl long  CKM_DSA_SHA1                   = 0x00000012L;
    public stbtic finbl long  CKM_DH_PKCS_KEY_PAIR_GEN       = 0x00000020L;
    public stbtic finbl long  CKM_DH_PKCS_DERIVE             = 0x00000021L;

    /* CKM_X9_42_DH_KEY_PAIR_GEN, CKM_X9_42_DH_DERIVE,
     * CKM_X9_42_DH_HYBRID_DERIVE, bnd CKM_X9_42_MQV_DERIVE bre new for
     * v2.11 */
    public stbtic finbl long  CKM_X9_42_DH_KEY_PAIR_GEN      = 0x00000030L;
    public stbtic finbl long  CKM_X9_42_DH_DERIVE            = 0x00000031L;
    public stbtic finbl long  CKM_X9_42_DH_HYBRID_DERIVE     = 0x00000032L;
    public stbtic finbl long  CKM_X9_42_MQV_DERIVE           = 0x00000033L;

    // v2.20
    public stbtic finbl long  CKM_SHA256_RSA_PKCS            = 0x00000040L;
    public stbtic finbl long  CKM_SHA384_RSA_PKCS            = 0x00000041L;
    public stbtic finbl long  CKM_SHA512_RSA_PKCS            = 0x00000042L;

    public stbtic finbl long  CKM_RC2_KEY_GEN                = 0x00000100L;
    public stbtic finbl long  CKM_RC2_ECB                    = 0x00000101L;
    public stbtic finbl long  CKM_RC2_CBC                    = 0x00000102L;
    public stbtic finbl long  CKM_RC2_MAC                    = 0x00000103L;

    /* CKM_RC2_MAC_GENERAL bnd CKM_RC2_CBC_PAD bre new for v2.0 */
    public stbtic finbl long  CKM_RC2_MAC_GENERAL            = 0x00000104L;
    public stbtic finbl long  CKM_RC2_CBC_PAD                = 0x00000105L;

    public stbtic finbl long  CKM_RC4_KEY_GEN                = 0x00000110L;
    public stbtic finbl long  CKM_RC4                        = 0x00000111L;
    public stbtic finbl long  CKM_DES_KEY_GEN                = 0x00000120L;
    public stbtic finbl long  CKM_DES_ECB                    = 0x00000121L;
    public stbtic finbl long  CKM_DES_CBC                    = 0x00000122L;
    public stbtic finbl long  CKM_DES_MAC                    = 0x00000123L;

    /* CKM_DES_MAC_GENERAL bnd CKM_DES_CBC_PAD bre new for v2.0 */
    public stbtic finbl long  CKM_DES_MAC_GENERAL            = 0x00000124L;
    public stbtic finbl long  CKM_DES_CBC_PAD                = 0x00000125L;

    public stbtic finbl long  CKM_DES2_KEY_GEN               = 0x00000130L;
    public stbtic finbl long  CKM_DES3_KEY_GEN               = 0x00000131L;
    public stbtic finbl long  CKM_DES3_ECB                   = 0x00000132L;
    public stbtic finbl long  CKM_DES3_CBC                   = 0x00000133L;
    public stbtic finbl long  CKM_DES3_MAC                   = 0x00000134L;

    /* CKM_DES3_MAC_GENERAL, CKM_DES3_CBC_PAD, CKM_CDMF_KEY_GEN,
     * CKM_CDMF_ECB, CKM_CDMF_CBC, CKM_CDMF_MAC,
     * CKM_CDMF_MAC_GENERAL, bnd CKM_CDMF_CBC_PAD bre new for v2.0 */
    public stbtic finbl long  CKM_DES3_MAC_GENERAL           = 0x00000135L;
    public stbtic finbl long  CKM_DES3_CBC_PAD               = 0x00000136L;
    public stbtic finbl long  CKM_CDMF_KEY_GEN               = 0x00000140L;
    public stbtic finbl long  CKM_CDMF_ECB                   = 0x00000141L;
    public stbtic finbl long  CKM_CDMF_CBC                   = 0x00000142L;
    public stbtic finbl long  CKM_CDMF_MAC                   = 0x00000143L;
    public stbtic finbl long  CKM_CDMF_MAC_GENERAL           = 0x00000144L;
    public stbtic finbl long  CKM_CDMF_CBC_PAD               = 0x00000145L;

    public stbtic finbl long  CKM_MD2                        = 0x00000200L;

    /* CKM_MD2_HMAC bnd CKM_MD2_HMAC_GENERAL bre new for v2.0 */
    public stbtic finbl long  CKM_MD2_HMAC                   = 0x00000201L;
    public stbtic finbl long  CKM_MD2_HMAC_GENERAL           = 0x00000202L;

    public stbtic finbl long  CKM_MD5                        = 0x00000210L;

    /* CKM_MD5_HMAC bnd CKM_MD5_HMAC_GENERAL bre new for v2.0 */
    public stbtic finbl long  CKM_MD5_HMAC                   = 0x00000211L;
    public stbtic finbl long  CKM_MD5_HMAC_GENERAL           = 0x00000212L;

    public stbtic finbl long  CKM_SHA_1                      = 0x00000220L;

    /* CKM_SHA_1_HMAC bnd CKM_SHA_1_HMAC_GENERAL bre new for v2.0 */
    public stbtic finbl long  CKM_SHA_1_HMAC                 = 0x00000221L;
    public stbtic finbl long  CKM_SHA_1_HMAC_GENERAL         = 0x00000222L;

    /* CKM_RIPEMD128, CKM_RIPEMD128_HMAC,
     * CKM_RIPEMD128_HMAC_GENERAL, CKM_RIPEMD160, CKM_RIPEMD160_HMAC,
     * bnd CKM_RIPEMD160_HMAC_GENERAL bre new for v2.10 */
    public stbtic finbl long  CKM_RIPEMD128                  = 0x00000230L;
    public stbtic finbl long  CKM_RIPEMD128_HMAC             = 0x00000231L;
    public stbtic finbl long  CKM_RIPEMD128_HMAC_GENERAL     = 0x00000232L;
    public stbtic finbl long  CKM_RIPEMD160                  = 0x00000240L;
    public stbtic finbl long  CKM_RIPEMD160_HMAC             = 0x00000241L;
    public stbtic finbl long  CKM_RIPEMD160_HMAC_GENERAL     = 0x00000242L;

    // v2.20
    public stbtic finbl long  CKM_SHA256                     = 0x00000250L;
    public stbtic finbl long  CKM_SHA256_HMAC                = 0x00000251L;
    public stbtic finbl long  CKM_SHA256_HMAC_GENERAL        = 0x00000252L;

    public stbtic finbl long  CKM_SHA384                     = 0x00000260L;
    public stbtic finbl long  CKM_SHA384_HMAC                = 0x00000261L;
    public stbtic finbl long  CKM_SHA384_HMAC_GENERAL        = 0x00000262L;

    public stbtic finbl long  CKM_SHA512                     = 0x00000270L;
    public stbtic finbl long  CKM_SHA512_HMAC                = 0x00000271L;
    public stbtic finbl long  CKM_SHA512_HMAC_GENERAL        = 0x00000272L;

    /* All of the following mechbnisms bre new for v2.0 */
    /* Note thbt CAST128 bnd CAST5 bre the sbme blgorithm */
    public stbtic finbl long  CKM_CAST_KEY_GEN               = 0x00000300L;
    public stbtic finbl long  CKM_CAST_ECB                   = 0x00000301L;
    public stbtic finbl long  CKM_CAST_CBC                   = 0x00000302L;
    public stbtic finbl long  CKM_CAST_MAC                   = 0x00000303L;
    public stbtic finbl long  CKM_CAST_MAC_GENERAL           = 0x00000304L;
    public stbtic finbl long  CKM_CAST_CBC_PAD               = 0x00000305L;
    public stbtic finbl long  CKM_CAST3_KEY_GEN              = 0x00000310L;
    public stbtic finbl long  CKM_CAST3_ECB                  = 0x00000311L;
    public stbtic finbl long  CKM_CAST3_CBC                  = 0x00000312L;
    public stbtic finbl long  CKM_CAST3_MAC                  = 0x00000313L;
    public stbtic finbl long  CKM_CAST3_MAC_GENERAL          = 0x00000314L;
    public stbtic finbl long  CKM_CAST3_CBC_PAD              = 0x00000315L;
    public stbtic finbl long  CKM_CAST5_KEY_GEN              = 0x00000320L;
    public stbtic finbl long  CKM_CAST128_KEY_GEN            = 0x00000320L;
    public stbtic finbl long  CKM_CAST5_ECB                  = 0x00000321L;
    public stbtic finbl long  CKM_CAST128_ECB                = 0x00000321L;
    public stbtic finbl long  CKM_CAST5_CBC                  = 0x00000322L;
    public stbtic finbl long  CKM_CAST128_CBC                = 0x00000322L;
    public stbtic finbl long  CKM_CAST5_MAC                  = 0x00000323L;
    public stbtic finbl long  CKM_CAST128_MAC                = 0x00000323L;
    public stbtic finbl long  CKM_CAST5_MAC_GENERAL          = 0x00000324L;
    public stbtic finbl long  CKM_CAST128_MAC_GENERAL        = 0x00000324L;
    public stbtic finbl long  CKM_CAST5_CBC_PAD              = 0x00000325L;
    public stbtic finbl long  CKM_CAST128_CBC_PAD            = 0x00000325L;
    public stbtic finbl long  CKM_RC5_KEY_GEN                = 0x00000330L;
    public stbtic finbl long  CKM_RC5_ECB                    = 0x00000331L;
    public stbtic finbl long  CKM_RC5_CBC                    = 0x00000332L;
    public stbtic finbl long  CKM_RC5_MAC                    = 0x00000333L;
    public stbtic finbl long  CKM_RC5_MAC_GENERAL            = 0x00000334L;
    public stbtic finbl long  CKM_RC5_CBC_PAD                = 0x00000335L;
    public stbtic finbl long  CKM_IDEA_KEY_GEN               = 0x00000340L;
    public stbtic finbl long  CKM_IDEA_ECB                   = 0x00000341L;
    public stbtic finbl long  CKM_IDEA_CBC                   = 0x00000342L;
    public stbtic finbl long  CKM_IDEA_MAC                   = 0x00000343L;
    public stbtic finbl long  CKM_IDEA_MAC_GENERAL           = 0x00000344L;
    public stbtic finbl long  CKM_IDEA_CBC_PAD               = 0x00000345L;
    public stbtic finbl long  CKM_GENERIC_SECRET_KEY_GEN     = 0x00000350L;
    public stbtic finbl long  CKM_CONCATENATE_BASE_AND_KEY   = 0x00000360L;
    public stbtic finbl long  CKM_CONCATENATE_BASE_AND_DATA  = 0x00000362L;
    public stbtic finbl long  CKM_CONCATENATE_DATA_AND_BASE  = 0x00000363L;
    public stbtic finbl long  CKM_XOR_BASE_AND_DATA          = 0x00000364L;
    public stbtic finbl long  CKM_EXTRACT_KEY_FROM_KEY       = 0x00000365L;
    public stbtic finbl long  CKM_SSL3_PRE_MASTER_KEY_GEN    = 0x00000370L;
    public stbtic finbl long  CKM_SSL3_MASTER_KEY_DERIVE     = 0x00000371L;
    public stbtic finbl long  CKM_SSL3_KEY_AND_MAC_DERIVE    = 0x00000372L;

    /* CKM_SSL3_MASTER_KEY_DERIVE_DH, CKM_TLS_PRE_MASTER_KEY_GEN,
     * CKM_TLS_MASTER_KEY_DERIVE, CKM_TLS_KEY_AND_MAC_DERIVE, bnd
     * CKM_TLS_MASTER_KEY_DERIVE_DH bre new for v2.11 */
    public stbtic finbl long  CKM_SSL3_MASTER_KEY_DERIVE_DH  = 0x00000373L;
    public stbtic finbl long  CKM_TLS_PRE_MASTER_KEY_GEN     = 0x00000374L;
    public stbtic finbl long  CKM_TLS_MASTER_KEY_DERIVE      = 0x00000375L;
    public stbtic finbl long  CKM_TLS_KEY_AND_MAC_DERIVE     = 0x00000376L;
    public stbtic finbl long  CKM_TLS_MASTER_KEY_DERIVE_DH   = 0x00000377L;
    public stbtic finbl long  CKM_TLS_PRF                    = 0x00000378L;

    public stbtic finbl long  CKM_SSL3_MD5_MAC               = 0x00000380L;
    public stbtic finbl long  CKM_SSL3_SHA1_MAC              = 0x00000381L;
    public stbtic finbl long  CKM_MD5_KEY_DERIVATION         = 0x00000390L;
    public stbtic finbl long  CKM_MD2_KEY_DERIVATION         = 0x00000391L;
    public stbtic finbl long  CKM_SHA1_KEY_DERIVATION        = 0x00000392L;

    // v2.20
    public stbtic finbl long  CKM_SHA256_KEY_DERIVATION      = 0x00000393L;
    public stbtic finbl long  CKM_SHA384_KEY_DERIVATION      = 0x00000394L;
    public stbtic finbl long  CKM_SHA512_KEY_DERIVATION      = 0x00000395L;

    public stbtic finbl long  CKM_PBE_MD2_DES_CBC            = 0x000003A0L;
    public stbtic finbl long  CKM_PBE_MD5_DES_CBC            = 0x000003A1L;
    public stbtic finbl long  CKM_PBE_MD5_CAST_CBC           = 0x000003A2L;
    public stbtic finbl long  CKM_PBE_MD5_CAST3_CBC          = 0x000003A3L;
    public stbtic finbl long  CKM_PBE_MD5_CAST5_CBC          = 0x000003A4L;
    public stbtic finbl long  CKM_PBE_MD5_CAST128_CBC        = 0x000003A4L;
    public stbtic finbl long  CKM_PBE_SHA1_CAST5_CBC         = 0x000003A5L;
    public stbtic finbl long  CKM_PBE_SHA1_CAST128_CBC       = 0x000003A5L;
    public stbtic finbl long  CKM_PBE_SHA1_RC4_128           = 0x000003A6L;
    public stbtic finbl long  CKM_PBE_SHA1_RC4_40            = 0x000003A7L;
    public stbtic finbl long  CKM_PBE_SHA1_DES3_EDE_CBC      = 0x000003A8L;
    public stbtic finbl long  CKM_PBE_SHA1_DES2_EDE_CBC      = 0x000003A9L;
    public stbtic finbl long  CKM_PBE_SHA1_RC2_128_CBC       = 0x000003AAL;
    public stbtic finbl long  CKM_PBE_SHA1_RC2_40_CBC        = 0x000003ABL;

    /* CKM_PKCS5_PBKD2 is new for v2.10 */
    public stbtic finbl long  CKM_PKCS5_PBKD2                = 0x000003B0L;

    public stbtic finbl long  CKM_PBA_SHA1_WITH_SHA1_HMAC    = 0x000003C0L;
    public stbtic finbl long  CKM_KEY_WRAP_LYNKS             = 0x00000400L;
    public stbtic finbl long  CKM_KEY_WRAP_SET_OAEP          = 0x00000401L;

    /* Fortezzb mechbnisms */
    public stbtic finbl long  CKM_SKIPJACK_KEY_GEN           = 0x00001000L;
    public stbtic finbl long  CKM_SKIPJACK_ECB64             = 0x00001001L;
    public stbtic finbl long  CKM_SKIPJACK_CBC64             = 0x00001002L;
    public stbtic finbl long  CKM_SKIPJACK_OFB64             = 0x00001003L;
    public stbtic finbl long  CKM_SKIPJACK_CFB64             = 0x00001004L;
    public stbtic finbl long  CKM_SKIPJACK_CFB32             = 0x00001005L;
    public stbtic finbl long  CKM_SKIPJACK_CFB16             = 0x00001006L;
    public stbtic finbl long  CKM_SKIPJACK_CFB8              = 0x00001007L;
    public stbtic finbl long  CKM_SKIPJACK_WRAP              = 0x00001008L;
    public stbtic finbl long  CKM_SKIPJACK_PRIVATE_WRAP      = 0x00001009L;
    public stbtic finbl long  CKM_SKIPJACK_RELAYX            = 0x0000100AL;
    public stbtic finbl long  CKM_KEA_KEY_PAIR_GEN           = 0x00001010L;
    public stbtic finbl long  CKM_KEA_KEY_DERIVE             = 0x00001011L;
    public stbtic finbl long  CKM_FORTEZZA_TIMESTAMP         = 0x00001020L;
    public stbtic finbl long  CKM_BATON_KEY_GEN              = 0x00001030L;
    public stbtic finbl long  CKM_BATON_ECB128               = 0x00001031L;
    public stbtic finbl long  CKM_BATON_ECB96                = 0x00001032L;
    public stbtic finbl long  CKM_BATON_CBC128               = 0x00001033L;
    public stbtic finbl long  CKM_BATON_COUNTER              = 0x00001034L;
    public stbtic finbl long  CKM_BATON_SHUFFLE              = 0x00001035L;
    public stbtic finbl long  CKM_BATON_WRAP                 = 0x00001036L;

    /* CKM_ECDSA_KEY_PAIR_GEN is deprecbted in v2.11,
     * CKM_EC_KEY_PAIR_GEN is preferred */
    public stbtic finbl long  CKM_ECDSA_KEY_PAIR_GEN         = 0x00001040L;
    public stbtic finbl long  CKM_EC_KEY_PAIR_GEN            = 0x00001040L;

    public stbtic finbl long  CKM_ECDSA                      = 0x00001041L;
    public stbtic finbl long  CKM_ECDSA_SHA1                 = 0x00001042L;

    /* CKM_ECDH1_DERIVE, CKM_ECDH1_COFACTOR_DERIVE, bnd CKM_ECMQV_DERIVE
     * bre new for v2.11 */
    public stbtic finbl long  CKM_ECDH1_DERIVE               = 0x00001050L;
    public stbtic finbl long  CKM_ECDH1_COFACTOR_DERIVE      = 0x00001051L;
    public stbtic finbl long  CKM_ECMQV_DERIVE               = 0x00001052L;

    public stbtic finbl long  CKM_JUNIPER_KEY_GEN            = 0x00001060L;
    public stbtic finbl long  CKM_JUNIPER_ECB128             = 0x00001061L;
    public stbtic finbl long  CKM_JUNIPER_CBC128             = 0x00001062L;
    public stbtic finbl long  CKM_JUNIPER_COUNTER            = 0x00001063L;
    public stbtic finbl long  CKM_JUNIPER_SHUFFLE            = 0x00001064L;
    public stbtic finbl long  CKM_JUNIPER_WRAP               = 0x00001065L;
    public stbtic finbl long  CKM_FASTHASH                   = 0x00001070L;

    /* CKM_AES_KEY_GEN, CKM_AES_ECB, CKM_AES_CBC, CKM_AES_MAC,
     * CKM_AES_MAC_GENERAL, CKM_AES_CBC_PAD, CKM_DSA_PARAMETER_GEN,
     * CKM_DH_PKCS_PARAMETER_GEN, bnd CKM_X9_42_DH_PARAMETER_GEN bre
     * new for v2.11 */
    public stbtic finbl long  CKM_AES_KEY_GEN                = 0x00001080L;
    public stbtic finbl long  CKM_AES_ECB                    = 0x00001081L;
    public stbtic finbl long  CKM_AES_CBC                    = 0x00001082L;
    public stbtic finbl long  CKM_AES_MAC                    = 0x00001083L;
    public stbtic finbl long  CKM_AES_MAC_GENERAL            = 0x00001084L;
    public stbtic finbl long  CKM_AES_CBC_PAD                = 0x00001085L;
    // v2.20
    public stbtic finbl long  CKM_BLOWFISH_KEY_GEN           = 0x00001090L;
    public stbtic finbl long  CKM_BLOWFISH_CBC               = 0x00001091L;
    public stbtic finbl long  CKM_DSA_PARAMETER_GEN          = 0x00002000L;
    public stbtic finbl long  CKM_DH_PKCS_PARAMETER_GEN      = 0x00002001L;
    public stbtic finbl long  CKM_X9_42_DH_PARAMETER_GEN     = 0x00002002L;

    public stbtic finbl long  CKM_VENDOR_DEFINED             = 0x80000000L;

    // new for v2.20 bmendment 3
    public stbtic finbl long  CKM_SHA224                     = 0x00000255L;
    public stbtic finbl long  CKM_SHA224_HMAC                = 0x00000256L;
    public stbtic finbl long  CKM_SHA224_HMAC_GENERAL        = 0x00000257L;
    public stbtic finbl long  CKM_SHA224_KEY_DERIVATION      = 0x00000396L;
    public stbtic finbl long  CKM_SHA224_RSA_PKCS            = 0x00000046L;
    public stbtic finbl long  CKM_SHA224_RSA_PKCS_PSS        = 0x00000047L;
    public stbtic finbl long  CKM_AES_CTR                    = 0x00001086L;
    /*
    public stbtic finbl long  CKM_CAMELLIA_KEY_GEN           = 0x00000550L;
    public stbtic finbl long  CKM_CAMELLIA_ECB               = 0x00000551L;
    public stbtic finbl long  CKM_CAMELLIA_CBC               = 0x00000552L;
    public stbtic finbl long  CKM_CAMELLIA_MAC               = 0x00000553L;
    public stbtic finbl long  CKM_CAMELLIA_MAC_GENERAL       = 0x00000554L;
    public stbtic finbl long  CKM_CAMELLIA_CBC_PAD           = 0x00000555L;
    public stbtic finbl long  CKM_CAMELLIA_ECB_ENCRYPT_DATA  = 0x00000556L;
    public stbtic finbl long  CKM_CAMELLIA_CBC_ENCRYPT_DATA  = 0x00000557L;
    public stbtic finbl long  CKM_CAMELLIA_CTR               = 0x00000558L;
    public stbtic finbl long  CKM_ARIA_KEY_GEN               = 0x00000560L;
    public stbtic finbl long  CKM_ARIA_ECB                   = 0x00000561L;
    public stbtic finbl long  CKM_ARIA_CBC                   = 0x00000562L;
    public stbtic finbl long  CKM_ARIA_MAC                   = 0x00000563L;
    public stbtic finbl long  CKM_ARIA_MAC_GENERAL           = 0x00000564L;
    public stbtic finbl long  CKM_ARIA_CBC_PAD               = 0x00000565L;
    public stbtic finbl long  CKM_ARIA_ECB_ENCRYPT_DATA      = 0x00000566L;
    public stbtic finbl long  CKM_ARIA_CBC_ENCRYPT_DATA      = 0x00000567L;
    */

    // NSS privbte
    public stbtic finbl long  CKM_NSS_TLS_PRF_GENERAL        = 0x80000373L;

    // ids for our pseudo mechbnisms SecureRbndom bnd KeyStore
    public stbtic finbl long  PCKM_SECURERANDOM              = 0x7FFFFF20L;
    public stbtic finbl long  PCKM_KEYSTORE                  = 0x7FFFFF21L;

    /* The flbgs bre defined bs follows:
     *      Bit Flbg               Mbsk        Mebning */
    /* performed by HW */
    public stbtic finbl long  CKF_HW                 = 0x00000001L;

    /* The flbgs CKF_ENCRYPT, CKF_DECRYPT, CKF_DIGEST, CKF_SIGN,
     * CKG_SIGN_RECOVER, CKF_VERIFY, CKF_VERIFY_RECOVER,
     * CKF_GENERATE, CKF_GENERATE_KEY_PAIR, CKF_WRAP, CKF_UNWRAP,
     * bnd CKF_DERIVE bre new for v2.0.  They specify whether or not
     * b mechbnism cbn be used for b pbrticulbr tbsk */
    public stbtic finbl long  CKF_ENCRYPT            = 0x00000100L;
    public stbtic finbl long  CKF_DECRYPT            = 0x00000200L;
    public stbtic finbl long  CKF_DIGEST             = 0x00000400L;
    public stbtic finbl long  CKF_SIGN               = 0x00000800L;
    public stbtic finbl long  CKF_SIGN_RECOVER       = 0x00001000L;
    public stbtic finbl long  CKF_VERIFY             = 0x00002000L;
    public stbtic finbl long  CKF_VERIFY_RECOVER     = 0x00004000L;
    public stbtic finbl long  CKF_GENERATE           = 0x00008000L;
    public stbtic finbl long  CKF_GENERATE_KEY_PAIR  = 0x00010000L;
    public stbtic finbl long  CKF_WRAP               = 0x00020000L;
    public stbtic finbl long  CKF_UNWRAP             = 0x00040000L;
    public stbtic finbl long  CKF_DERIVE             = 0x00080000L;

    /* CKF_EC_F_P, CKF_EC_F_2M, CKF_EC_ECPARAMETERS, CKF_EC_NAMEDCURVE,
     * CKF_EC_UNCOMPRESS, bnd CKF_EC_COMPRESS bre new for v2.11. They
     * describe b token's EC cbpbbilities not bvbilbble in mechbnism
     * informbtion. */
    public stbtic finbl long  CKF_EC_F_P              = 0x00100000L;
    public stbtic finbl long  CKF_EC_F_2M           = 0x00200000L;
    public stbtic finbl long  CKF_EC_ECPARAMETERS   = 0x00400000L;
    public stbtic finbl long  CKF_EC_NAMEDCURVE     = 0x00800000L;
    public stbtic finbl long  CKF_EC_UNCOMPRESS     = 0x01000000L;
    public stbtic finbl long  CKF_EC_COMPRESS       = 0x02000000L;

    /* FALSE for 2.01 */
    public stbtic finbl long  CKF_EXTENSION          = 0x80000000L;


    /* CK_RV is b vblue thbt identifies the return vblue of b
     * Cryptoki function */
    /* CK_RV wbs chbnged from CK_USHORT to CK_ULONG for v2.0 */
    public stbtic finbl long  CKR_OK                                = 0x00000000L;
    public stbtic finbl long  CKR_CANCEL                            = 0x00000001L;
    public stbtic finbl long  CKR_HOST_MEMORY                       = 0x00000002L;
    public stbtic finbl long  CKR_SLOT_ID_INVALID                   = 0x00000003L;

    /* CKR_FLAGS_INVALID wbs removed for v2.0 */

    /* CKR_GENERAL_ERROR bnd CKR_FUNCTION_FAILED bre new for v2.0 */
    public stbtic finbl long  CKR_GENERAL_ERROR                     = 0x00000005L;
    public stbtic finbl long  CKR_FUNCTION_FAILED                   = 0x00000006L;

    /* CKR_ARGUMENTS_BAD, CKR_NO_EVENT, CKR_NEED_TO_CREATE_THREADS,
     * bnd CKR_CANT_LOCK bre new for v2.01 */
    public stbtic finbl long  CKR_ARGUMENTS_BAD                     = 0x00000007L;
    public stbtic finbl long  CKR_NO_EVENT                          = 0x00000008L;
    public stbtic finbl long  CKR_NEED_TO_CREATE_THREADS            = 0x00000009L;
    public stbtic finbl long  CKR_CANT_LOCK                         = 0x0000000AL;

    public stbtic finbl long  CKR_ATTRIBUTE_READ_ONLY               = 0x00000010L;
    public stbtic finbl long  CKR_ATTRIBUTE_SENSITIVE               = 0x00000011L;
    public stbtic finbl long  CKR_ATTRIBUTE_TYPE_INVALID            = 0x00000012L;
    public stbtic finbl long  CKR_ATTRIBUTE_VALUE_INVALID           = 0x00000013L;
    public stbtic finbl long  CKR_DATA_INVALID                      = 0x00000020L;
    public stbtic finbl long  CKR_DATA_LEN_RANGE                    = 0x00000021L;
    public stbtic finbl long  CKR_DEVICE_ERROR                      = 0x00000030L;
    public stbtic finbl long  CKR_DEVICE_MEMORY                     = 0x00000031L;
    public stbtic finbl long  CKR_DEVICE_REMOVED                    = 0x00000032L;
    public stbtic finbl long  CKR_ENCRYPTED_DATA_INVALID            = 0x00000040L;
    public stbtic finbl long  CKR_ENCRYPTED_DATA_LEN_RANGE          = 0x00000041L;
    public stbtic finbl long  CKR_FUNCTION_CANCELED                 = 0x00000050L;
    public stbtic finbl long  CKR_FUNCTION_NOT_PARALLEL             = 0x00000051L;

    /* CKR_FUNCTION_NOT_SUPPORTED is new for v2.0 */
    public stbtic finbl long  CKR_FUNCTION_NOT_SUPPORTED            = 0x00000054L;

    public stbtic finbl long  CKR_KEY_HANDLE_INVALID                = 0x00000060L;

    /* CKR_KEY_SENSITIVE wbs removed for v2.0 */

    public stbtic finbl long  CKR_KEY_SIZE_RANGE                    = 0x00000062L;
    public stbtic finbl long  CKR_KEY_TYPE_INCONSISTENT             = 0x00000063L;

    /* CKR_KEY_NOT_NEEDED, CKR_KEY_CHANGED, CKR_KEY_NEEDED,
     * CKR_KEY_INDIGESTIBLE, CKR_KEY_FUNCTION_NOT_PERMITTED,
     * CKR_KEY_NOT_WRAPPABLE, bnd CKR_KEY_UNEXTRACTABLE bre new for
     * v2.0 */
    public stbtic finbl long  CKR_KEY_NOT_NEEDED                    = 0x00000064L;
    public stbtic finbl long  CKR_KEY_CHANGED                       = 0x00000065L;
    public stbtic finbl long  CKR_KEY_NEEDED                        = 0x00000066L;
    public stbtic finbl long  CKR_KEY_INDIGESTIBLE                  = 0x00000067L;
    public stbtic finbl long  CKR_KEY_FUNCTION_NOT_PERMITTED        = 0x00000068L;
    public stbtic finbl long  CKR_KEY_NOT_WRAPPABLE                 = 0x00000069L;
    public stbtic finbl long  CKR_KEY_UNEXTRACTABLE                 = 0x0000006AL;

    public stbtic finbl long  CKR_MECHANISM_INVALID                 = 0x00000070L;
    public stbtic finbl long  CKR_MECHANISM_PARAM_INVALID           = 0x00000071L;

    /* CKR_OBJECT_CLASS_INCONSISTENT bnd CKR_OBJECT_CLASS_INVALID
     * were removed for v2.0 */
    public stbtic finbl long  CKR_OBJECT_HANDLE_INVALID             = 0x00000082L;
    public stbtic finbl long  CKR_OPERATION_ACTIVE                  = 0x00000090L;
    public stbtic finbl long  CKR_OPERATION_NOT_INITIALIZED         = 0x00000091L;
    public stbtic finbl long  CKR_PIN_INCORRECT                     = 0x000000A0L;
    public stbtic finbl long  CKR_PIN_INVALID                       = 0x000000A1L;
    public stbtic finbl long  CKR_PIN_LEN_RANGE                     = 0x000000A2L;

    /* CKR_PIN_EXPIRED bnd CKR_PIN_LOCKED bre new for v2.0 */
    public stbtic finbl long  CKR_PIN_EXPIRED                       = 0x000000A3L;
    public stbtic finbl long  CKR_PIN_LOCKED                        = 0x000000A4L;

    public stbtic finbl long  CKR_SESSION_CLOSED                    = 0x000000B0L;
    public stbtic finbl long  CKR_SESSION_COUNT                     = 0x000000B1L;
    public stbtic finbl long  CKR_SESSION_HANDLE_INVALID            = 0x000000B3L;
    public stbtic finbl long  CKR_SESSION_PARALLEL_NOT_SUPPORTED    = 0x000000B4L;
    public stbtic finbl long  CKR_SESSION_READ_ONLY                 = 0x000000B5L;
    public stbtic finbl long  CKR_SESSION_EXISTS                    = 0x000000B6L;

    /* CKR_SESSION_READ_ONLY_EXISTS bnd
     * CKR_SESSION_READ_WRITE_SO_EXISTS bre new for v2.0 */
    public stbtic finbl long  CKR_SESSION_READ_ONLY_EXISTS          = 0x000000B7L;
    public stbtic finbl long  CKR_SESSION_READ_WRITE_SO_EXISTS      = 0x000000B8L;

    public stbtic finbl long  CKR_SIGNATURE_INVALID                 = 0x000000C0L;
    public stbtic finbl long  CKR_SIGNATURE_LEN_RANGE               = 0x000000C1L;
    public stbtic finbl long  CKR_TEMPLATE_INCOMPLETE               = 0x000000D0L;
    public stbtic finbl long  CKR_TEMPLATE_INCONSISTENT             = 0x000000D1L;
    public stbtic finbl long  CKR_TOKEN_NOT_PRESENT                 = 0x000000E0L;
    public stbtic finbl long  CKR_TOKEN_NOT_RECOGNIZED              = 0x000000E1L;
    public stbtic finbl long  CKR_TOKEN_WRITE_PROTECTED             = 0x000000E2L;
    public stbtic finbl long  CKR_UNWRAPPING_KEY_HANDLE_INVALID     = 0x000000F0L;
    public stbtic finbl long  CKR_UNWRAPPING_KEY_SIZE_RANGE         = 0x000000F1L;
    public stbtic finbl long  CKR_UNWRAPPING_KEY_TYPE_INCONSISTENT  = 0x000000F2L;
    public stbtic finbl long  CKR_USER_ALREADY_LOGGED_IN            = 0x00000100L;
    public stbtic finbl long  CKR_USER_NOT_LOGGED_IN                = 0x00000101L;
    public stbtic finbl long  CKR_USER_PIN_NOT_INITIALIZED          = 0x00000102L;
    public stbtic finbl long  CKR_USER_TYPE_INVALID                 = 0x00000103L;

    /* CKR_USER_ANOTHER_ALREADY_LOGGED_IN bnd CKR_USER_TOO_MANY_TYPES
     * bre new to v2.01 */
    public stbtic finbl long  CKR_USER_ANOTHER_ALREADY_LOGGED_IN    = 0x00000104L;
    public stbtic finbl long  CKR_USER_TOO_MANY_TYPES               = 0x00000105L;

    public stbtic finbl long  CKR_WRAPPED_KEY_INVALID               = 0x00000110L;
    public stbtic finbl long  CKR_WRAPPED_KEY_LEN_RANGE             = 0x00000112L;
    public stbtic finbl long  CKR_WRAPPING_KEY_HANDLE_INVALID       = 0x00000113L;
    public stbtic finbl long  CKR_WRAPPING_KEY_SIZE_RANGE           = 0x00000114L;
    public stbtic finbl long  CKR_WRAPPING_KEY_TYPE_INCONSISTENT    = 0x00000115L;
    public stbtic finbl long  CKR_RANDOM_SEED_NOT_SUPPORTED         = 0x00000120L;

    /* These bre new to v2.0 */
    public stbtic finbl long  CKR_RANDOM_NO_RNG                     = 0x00000121L;

    /* These bre new to v2.11 */
    public stbtic finbl long  CKR_DOMAIN_PARAMS_INVALID             = 0x00000130L;

    /* These bre new to v2.0 */
    public stbtic finbl long  CKR_BUFFER_TOO_SMALL                  = 0x00000150L;
    public stbtic finbl long  CKR_SAVED_STATE_INVALID               = 0x00000160L;
    public stbtic finbl long  CKR_INFORMATION_SENSITIVE             = 0x00000170L;
    public stbtic finbl long  CKR_STATE_UNSAVEABLE                  = 0x00000180L;

    /* These bre new to v2.01 */
    public stbtic finbl long  CKR_CRYPTOKI_NOT_INITIALIZED          = 0x00000190L;
    public stbtic finbl long  CKR_CRYPTOKI_ALREADY_INITIALIZED      = 0x00000191L;
    public stbtic finbl long  CKR_MUTEX_BAD                         = 0x000001A0L;
    public stbtic finbl long  CKR_MUTEX_NOT_LOCKED                  = 0x000001A1L;

    public stbtic finbl long  CKR_VENDOR_DEFINED                    = 0x80000000L;


    /* flbgs: bit flbgs thbt provide cbpbbilities of the slot
     *        Bit Flbg = Mbsk
     */
    public stbtic finbl long  CKF_LIBRARY_CANT_CREATE_OS_THREADS = 0x00000001L;
    public stbtic finbl long  CKF_OS_LOCKING_OK                  = 0x00000002L;


    /* CKF_DONT_BLOCK is for the function C_WbitForSlotEvent */
    public stbtic finbl long  CKF_DONT_BLOCK =    1L;


    /* The following MGFs bre defined */
    public stbtic finbl long  CKG_MGF1_SHA1       =  0x00000001L;
    // new for v2.20 bmendment 3
    public stbtic finbl long  CKG_MGF1_SHA224     = 0x00000005L;

    /* The following encoding pbrbmeter sources bre defined */
    public stbtic finbl long  CKZ_DATA_SPECIFIED   = 0x00000001L;


    /* The following PRFs bre defined in PKCS #5 v2.0. */
    public stbtic finbl long  CKP_PKCS5_PBKD2_HMAC_SHA1 = 0x00000001L;


    /* The following sblt vblue sources bre defined in PKCS #5 v2.0. */
    public stbtic finbl long CKZ_SALT_SPECIFIED        = 0x00000001L;

    /* the following EC Key Derivbtion Functions bre defined */
    public stbtic finbl long CKD_NULL                 = 0x00000001L;
    public stbtic finbl long CKD_SHA1_KDF             = 0x00000002L;

    /* the following X9.42 Diffie-Hellmbn Key Derivbtion Functions bre defined */
    public stbtic finbl long CKD_SHA1_KDF_ASN1        = 0x00000003L;
    public stbtic finbl long CKD_SHA1_KDF_CONCATENATE = 0x00000004L;


    // privbte NSS bttribute (for DSA bnd DH privbte keys)
    public stbtic finbl long  CKA_NETSCAPE_DB         = 0xD5A0DB00L;

    // bbse number of NSS privbte bttributes
    public stbtic finbl long  CKA_NETSCAPE_BASE       = 0x80000000L + 0x4E534350L;

    // object type for NSS trust
    public stbtic finbl long  CKO_NETSCAPE_TRUST      = CKA_NETSCAPE_BASE + 3;

    // bbse number for NSS trust bttributes
    public stbtic finbl long  CKA_NETSCAPE_TRUST_BASE = CKA_NETSCAPE_BASE + 0x2000;

    // bttributes for NSS trust
    public stbtic finbl long  CKA_NETSCAPE_TRUST_SERVER_AUTH      = CKA_NETSCAPE_TRUST_BASE +   8;
    public stbtic finbl long  CKA_NETSCAPE_TRUST_CLIENT_AUTH      = CKA_NETSCAPE_TRUST_BASE +   9;
    public stbtic finbl long  CKA_NETSCAPE_TRUST_CODE_SIGNING     = CKA_NETSCAPE_TRUST_BASE +  10;
    public stbtic finbl long  CKA_NETSCAPE_TRUST_EMAIL_PROTECTION = CKA_NETSCAPE_TRUST_BASE +  11;
    public stbtic finbl long  CKA_NETSCAPE_CERT_SHA1_HASH         = CKA_NETSCAPE_TRUST_BASE + 100;
    public stbtic finbl long  CKA_NETSCAPE_CERT_MD5_HASH          = CKA_NETSCAPE_TRUST_BASE + 101;

    // trust vblues for ebch of the NSS trust bttributes
    public stbtic finbl long  CKT_NETSCAPE_TRUSTED           = CKA_NETSCAPE_BASE + 1;
    public stbtic finbl long  CKT_NETSCAPE_TRUSTED_DELEGATOR = CKA_NETSCAPE_BASE + 2;
    public stbtic finbl long  CKT_NETSCAPE_UNTRUSTED         = CKA_NETSCAPE_BASE + 3;
    public stbtic finbl long  CKT_NETSCAPE_MUST_VERIFY       = CKA_NETSCAPE_BASE + 4;
    public stbtic finbl long  CKT_NETSCAPE_TRUST_UNKNOWN     = CKA_NETSCAPE_BASE + 5; /* defbult */
    public stbtic finbl long  CKT_NETSCAPE_VALID             = CKA_NETSCAPE_BASE + 10;
    public stbtic finbl long  CKT_NETSCAPE_VALID_DELEGATOR   = CKA_NETSCAPE_BASE + 11;

}
