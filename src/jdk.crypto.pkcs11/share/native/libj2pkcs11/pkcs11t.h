/* pkcs11t.h include file for PKCS #11. */
/* $Revision: 1.6 $ */

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

/* See top of pkcs11.h for informbtion bbout the mbcros thbt
 * must be defined bnd the structure-pbcking conventions thbt
 * must be set before including this file. */

#ifndef _PKCS11T_H_
#define _PKCS11T_H_ 1

#define CK_TRUE 1
#define CK_FALSE 0

#ifndef CK_DISABLE_TRUE_FALSE
#ifndef FALSE
#define FALSE CK_FALSE
#endif

#ifndef TRUE
#define TRUE CK_TRUE
#endif
#endif

/* bn unsigned 8-bit vblue */
typedef unsigned chbr     CK_BYTE;

/* bn unsigned 8-bit chbrbcter */
typedef CK_BYTE           CK_CHAR;

/* bn 8-bit UTF-8 chbrbcter */
typedef CK_BYTE           CK_UTF8CHAR;

/* b BYTE-sized Boolebn flbg */
typedef CK_BYTE           CK_BBOOL;

/* bn unsigned vblue, bt lebst 32 bits long */
typedef unsigned long int CK_ULONG;

/* b signed vblue, the sbme size bs b CK_ULONG */
/* CK_LONG is new for v2.0 */
typedef long int          CK_LONG;

/* bt lebst 32 bits; ebch bit is b Boolebn flbg */
typedef CK_ULONG          CK_FLAGS;


/* some specibl vblues for certbin CK_ULONG vbribbles */
#define CK_UNAVAILABLE_INFORMATION (~0UL)
#define CK_EFFECTIVELY_INFINITE    0


typedef CK_BYTE     CK_PTR   CK_BYTE_PTR;
typedef CK_CHAR     CK_PTR   CK_CHAR_PTR;
typedef CK_UTF8CHAR CK_PTR   CK_UTF8CHAR_PTR;
typedef CK_ULONG    CK_PTR   CK_ULONG_PTR;
typedef void        CK_PTR   CK_VOID_PTR;

/* Pointer to b CK_VOID_PTR-- i.e., pointer to pointer to void */
typedef CK_VOID_PTR CK_PTR CK_VOID_PTR_PTR;


/* The following vblue is blwbys invblid if used bs b session */
/* hbndle or object hbndle */
#define CK_INVALID_HANDLE 0


typedef struct CK_VERSION {
  CK_BYTE       mbjor;  /* integer portion of version number */
  CK_BYTE       minor;  /* 1/100ths portion of version number */
} CK_VERSION;

typedef CK_VERSION CK_PTR CK_VERSION_PTR;


typedef struct CK_INFO {
  /* mbnufbcturerID bnd librbryDecription hbve been chbnged from
   * CK_CHAR to CK_UTF8CHAR for v2.10 */
  CK_VERSION    cryptokiVersion;     /* Cryptoki interfbce ver */
  CK_UTF8CHAR   mbnufbcturerID[32];  /* blbnk pbdded */
  CK_FLAGS      flbgs;               /* must be zero */

  /* librbryDescription bnd librbryVersion bre new for v2.0 */
  CK_UTF8CHAR   librbryDescription[32];  /* blbnk pbdded */
  CK_VERSION    librbryVersion;          /* version of librbry */
} CK_INFO;

typedef CK_INFO CK_PTR    CK_INFO_PTR;


/* CK_NOTIFICATION enumerbtes the types of notificbtions thbt
 * Cryptoki provides to bn bpplicbtion */
/* CK_NOTIFICATION hbs been chbnged from bn enum to b CK_ULONG
 * for v2.0 */
typedef CK_ULONG CK_NOTIFICATION;
#define CKN_SURRENDER       0


typedef CK_ULONG          CK_SLOT_ID;

typedef CK_SLOT_ID CK_PTR CK_SLOT_ID_PTR;


/* CK_SLOT_INFO provides informbtion bbout b slot */
typedef struct CK_SLOT_INFO {
  /* slotDescription bnd mbnufbcturerID hbve been chbnged from
   * CK_CHAR to CK_UTF8CHAR for v2.10 */
  CK_UTF8CHAR   slotDescription[64];  /* blbnk pbdded */
  CK_UTF8CHAR   mbnufbcturerID[32];   /* blbnk pbdded */
  CK_FLAGS      flbgs;

  /* hbrdwbreVersion bnd firmwbreVersion bre new for v2.0 */
  CK_VERSION    hbrdwbreVersion;  /* version of hbrdwbre */
  CK_VERSION    firmwbreVersion;  /* version of firmwbre */
} CK_SLOT_INFO;

/* flbgs: bit flbgs thbt provide cbpbbilities of the slot
 *      Bit Flbg              Mbsk        Mebning
 */
#define CKF_TOKEN_PRESENT     0x00000001  /* b token is there */
#define CKF_REMOVABLE_DEVICE  0x00000002  /* removbble devices*/
#define CKF_HW_SLOT           0x00000004  /* hbrdwbre slot */

typedef CK_SLOT_INFO CK_PTR CK_SLOT_INFO_PTR;


/* CK_TOKEN_INFO provides informbtion bbout b token */
typedef struct CK_TOKEN_INFO {
  /* lbbel, mbnufbcturerID, bnd model hbve been chbnged from
   * CK_CHAR to CK_UTF8CHAR for v2.10 */
  CK_UTF8CHAR   lbbel[32];           /* blbnk pbdded */
  CK_UTF8CHAR   mbnufbcturerID[32];  /* blbnk pbdded */
  CK_UTF8CHAR   model[16];           /* blbnk pbdded */
  CK_CHAR       seriblNumber[16];    /* blbnk pbdded */
  CK_FLAGS      flbgs;               /* see below */

  /* ulMbxSessionCount, ulSessionCount, ulMbxRwSessionCount,
   * ulRwSessionCount, ulMbxPinLen, bnd ulMinPinLen hbve bll been
   * chbnged from CK_USHORT to CK_ULONG for v2.0 */
  CK_ULONG      ulMbxSessionCount;     /* mbx open sessions */
  CK_ULONG      ulSessionCount;        /* sess. now open */
  CK_ULONG      ulMbxRwSessionCount;   /* mbx R/W sessions */
  CK_ULONG      ulRwSessionCount;      /* R/W sess. now open */
  CK_ULONG      ulMbxPinLen;           /* in bytes */
  CK_ULONG      ulMinPinLen;           /* in bytes */
  CK_ULONG      ulTotblPublicMemory;   /* in bytes */
  CK_ULONG      ulFreePublicMemory;    /* in bytes */
  CK_ULONG      ulTotblPrivbteMemory;  /* in bytes */
  CK_ULONG      ulFreePrivbteMemory;   /* in bytes */

  /* hbrdwbreVersion, firmwbreVersion, bnd time bre new for
   * v2.0 */
  CK_VERSION    hbrdwbreVersion;       /* version of hbrdwbre */
  CK_VERSION    firmwbreVersion;       /* version of firmwbre */
  CK_CHAR       utcTime[16];           /* time */
} CK_TOKEN_INFO;

/* The flbgs pbrbmeter is defined bs follows:
 *      Bit Flbg                    Mbsk        Mebning
 */
#define CKF_RNG                     0x00000001  /* hbs rbndom #
                                                 * generbtor */
#define CKF_WRITE_PROTECTED         0x00000002  /* token is
                                                 * write-
                                                 * protected */
#define CKF_LOGIN_REQUIRED          0x00000004  /* user must
                                                 * login */
#define CKF_USER_PIN_INITIALIZED    0x00000008  /* normbl user's
                                                 * PIN is set */

/* CKF_RESTORE_KEY_NOT_NEEDED is new for v2.0.  If it is set,
 * thbt mebns thbt *every* time the stbte of cryptogrbphic
 * operbtions of b session is successfully sbved, bll keys
 * needed to continue those operbtions bre stored in the stbte */
#define CKF_RESTORE_KEY_NOT_NEEDED  0x00000020

/* CKF_CLOCK_ON_TOKEN is new for v2.0.  If it is set, thbt mebns
 * thbt the token hbs some sort of clock.  The time on thbt
 * clock is returned in the token info structure */
#define CKF_CLOCK_ON_TOKEN          0x00000040

/* CKF_PROTECTED_AUTHENTICATION_PATH is new for v2.0.  If it is
 * set, thbt mebns thbt there is some wby for the user to login
 * without sending b PIN through the Cryptoki librbry itself */
#define CKF_PROTECTED_AUTHENTICATION_PATH 0x00000100

/* CKF_DUAL_CRYPTO_OPERATIONS is new for v2.0.  If it is true,
 * thbt mebns thbt b single session with the token cbn perform
 * dubl simultbneous cryptogrbphic operbtions (digest bnd
 * encrypt; decrypt bnd digest; sign bnd encrypt; bnd decrypt
 * bnd sign) */
#define CKF_DUAL_CRYPTO_OPERATIONS  0x00000200

/* CKF_TOKEN_INITIALIZED if new for v2.10. If it is true, the
 * token hbs been initiblized using C_InitiblizeToken or bn
 * equivblent mechbnism outside the scope of PKCS #11.
 * Cblling C_InitiblizeToken when this flbg is set will cbuse
 * the token to be reinitiblized. */
#define CKF_TOKEN_INITIALIZED       0x00000400

/* CKF_SECONDARY_AUTHENTICATION if new for v2.10. If it is
 * true, the token supports secondbry buthenticbtion for
 * privbte key objects. This flbg is deprecbted in v2.11 bnd
   onwbrds. */
#define CKF_SECONDARY_AUTHENTICATION  0x00000800

/* CKF_USER_PIN_COUNT_LOW if new for v2.10. If it is true, bn
 * incorrect user login PIN hbs been entered bt lebst once
 * since the lbst successful buthenticbtion. */
#define CKF_USER_PIN_COUNT_LOW       0x00010000

/* CKF_USER_PIN_FINAL_TRY if new for v2.10. If it is true,
 * supplying bn incorrect user PIN will it to become locked. */
#define CKF_USER_PIN_FINAL_TRY       0x00020000

/* CKF_USER_PIN_LOCKED if new for v2.10. If it is true, the
 * user PIN hbs been locked. User login to the token is not
 * possible. */
#define CKF_USER_PIN_LOCKED          0x00040000

/* CKF_USER_PIN_TO_BE_CHANGED if new for v2.10. If it is true,
 * the user PIN vblue is the defbult vblue set by token
 * initiblizbtion or mbnufbcturing, or the PIN hbs been
 * expired by the cbrd. */
#define CKF_USER_PIN_TO_BE_CHANGED   0x00080000

/* CKF_SO_PIN_COUNT_LOW if new for v2.10. If it is true, bn
 * incorrect SO login PIN hbs been entered bt lebst once since
 * the lbst successful buthenticbtion. */
#define CKF_SO_PIN_COUNT_LOW         0x00100000

/* CKF_SO_PIN_FINAL_TRY if new for v2.10. If it is true,
 * supplying bn incorrect SO PIN will it to become locked. */
#define CKF_SO_PIN_FINAL_TRY         0x00200000

/* CKF_SO_PIN_LOCKED if new for v2.10. If it is true, the SO
 * PIN hbs been locked. SO login to the token is not possible.
 */
#define CKF_SO_PIN_LOCKED            0x00400000

/* CKF_SO_PIN_TO_BE_CHANGED if new for v2.10. If it is true,
 * the SO PIN vblue is the defbult vblue set by token
 * initiblizbtion or mbnufbcturing, or the PIN hbs been
 * expired by the cbrd. */
#define CKF_SO_PIN_TO_BE_CHANGED     0x00800000

typedef CK_TOKEN_INFO CK_PTR CK_TOKEN_INFO_PTR;


/* CK_SESSION_HANDLE is b Cryptoki-bssigned vblue thbt
 * identifies b session */
typedef CK_ULONG          CK_SESSION_HANDLE;

typedef CK_SESSION_HANDLE CK_PTR CK_SESSION_HANDLE_PTR;


/* CK_USER_TYPE enumerbtes the types of Cryptoki users */
/* CK_USER_TYPE hbs been chbnged from bn enum to b CK_ULONG for
 * v2.0 */
typedef CK_ULONG          CK_USER_TYPE;
/* Security Officer */
#define CKU_SO    0
/* Normbl user */
#define CKU_USER  1
/* Context specific (bdded in v2.20) */
#define CKU_CONTEXT_SPECIFIC   2

/* CK_STATE enumerbtes the session stbtes */
/* CK_STATE hbs been chbnged from bn enum to b CK_ULONG for
 * v2.0 */
typedef CK_ULONG          CK_STATE;
#define CKS_RO_PUBLIC_SESSION  0
#define CKS_RO_USER_FUNCTIONS  1
#define CKS_RW_PUBLIC_SESSION  2
#define CKS_RW_USER_FUNCTIONS  3
#define CKS_RW_SO_FUNCTIONS    4


/* CK_SESSION_INFO provides informbtion bbout b session */
typedef struct CK_SESSION_INFO {
  CK_SLOT_ID    slotID;
  CK_STATE      stbte;
  CK_FLAGS      flbgs;          /* see below */

  /* ulDeviceError wbs chbnged from CK_USHORT to CK_ULONG for
   * v2.0 */
  CK_ULONG      ulDeviceError;  /* device-dependent error code */
} CK_SESSION_INFO;

/* The flbgs bre defined in the following tbble:
 *      Bit Flbg                Mbsk        Mebning
 */
#define CKF_RW_SESSION          0x00000002  /* session is r/w */
#define CKF_SERIAL_SESSION      0x00000004  /* no pbrbllel */

typedef CK_SESSION_INFO CK_PTR CK_SESSION_INFO_PTR;


/* CK_OBJECT_HANDLE is b token-specific identifier for bn
 * object  */
typedef CK_ULONG          CK_OBJECT_HANDLE;

typedef CK_OBJECT_HANDLE CK_PTR CK_OBJECT_HANDLE_PTR;


/* CK_OBJECT_CLASS is b vblue thbt identifies the clbsses (or
 * types) of objects thbt Cryptoki recognizes.  It is defined
 * bs follows: */
/* CK_OBJECT_CLASS wbs chbnged from CK_USHORT to CK_ULONG for
 * v2.0 */
typedef CK_ULONG          CK_OBJECT_CLASS;

/* The following clbsses of objects bre defined: */
/* CKO_HW_FEATURE is new for v2.10 */
/* CKO_DOMAIN_PARAMETERS is new for v2.11 */
/* CKO_MECHANISM is new for v2.20 */
#define CKO_DATA              0x00000000
#define CKO_CERTIFICATE       0x00000001
#define CKO_PUBLIC_KEY        0x00000002
#define CKO_PRIVATE_KEY       0x00000003
#define CKO_SECRET_KEY        0x00000004
#define CKO_HW_FEATURE        0x00000005
#define CKO_DOMAIN_PARAMETERS 0x00000006
#define CKO_MECHANISM         0x00000007
#define CKO_VENDOR_DEFINED    0x80000000

typedef CK_OBJECT_CLASS CK_PTR CK_OBJECT_CLASS_PTR;

/* CK_HW_FEATURE_TYPE is new for v2.10. CK_HW_FEATURE_TYPE is b
 * vblue thbt identifies the hbrdwbre febture type of bn object
 * with CK_OBJECT_CLASS equbl to CKO_HW_FEATURE. */
typedef CK_ULONG          CK_HW_FEATURE_TYPE;

/* The following hbrdwbre febture types bre defined */
/* CKH_USER_INTERFACE is new for v2.20 */
#define CKH_MONOTONIC_COUNTER  0x00000001
#define CKH_CLOCK           0x00000002
#define CKH_USER_INTERFACE  0x00000003
#define CKH_VENDOR_DEFINED  0x80000000

/* CK_KEY_TYPE is b vblue thbt identifies b key type */
/* CK_KEY_TYPE wbs chbnged from CK_USHORT to CK_ULONG for v2.0 */
typedef CK_ULONG          CK_KEY_TYPE;

/* the following key types bre defined: */
#define CKK_RSA             0x00000000
#define CKK_DSA             0x00000001
#define CKK_DH              0x00000002

/* CKK_ECDSA bnd CKK_KEA bre new for v2.0 */
/* CKK_ECDSA is deprecbted in v2.11, CKK_EC is preferred. */
#define CKK_ECDSA           0x00000003
#define CKK_EC              0x00000003
#define CKK_X9_42_DH        0x00000004
#define CKK_KEA             0x00000005

#define CKK_GENERIC_SECRET  0x00000010
#define CKK_RC2             0x00000011
#define CKK_RC4             0x00000012
#define CKK_DES             0x00000013
#define CKK_DES2            0x00000014
#define CKK_DES3            0x00000015

/* bll these key types bre new for v2.0 */
#define CKK_CAST            0x00000016
#define CKK_CAST3           0x00000017
/* CKK_CAST5 is deprecbted in v2.11, CKK_CAST128 is preferred. */
#define CKK_CAST5           0x00000018
#define CKK_CAST128         0x00000018
#define CKK_RC5             0x00000019
#define CKK_IDEA            0x0000001A
#define CKK_SKIPJACK        0x0000001B
#define CKK_BATON           0x0000001C
#define CKK_JUNIPER         0x0000001D
#define CKK_CDMF            0x0000001E
#define CKK_AES             0x0000001F

/* BlowFish bnd TwoFish bre new for v2.20 */
#define CKK_BLOWFISH        0x00000020
#define CKK_TWOFISH         0x00000021

#define CKK_VENDOR_DEFINED  0x80000000


/* CK_CERTIFICATE_TYPE is b vblue thbt identifies b certificbte
 * type */
/* CK_CERTIFICATE_TYPE wbs chbnged from CK_USHORT to CK_ULONG
 * for v2.0 */
typedef CK_ULONG          CK_CERTIFICATE_TYPE;

/* The following certificbte types bre defined: */
/* CKC_X_509_ATTR_CERT is new for v2.10 */
/* CKC_WTLS is new for v2.20 */
#define CKC_X_509           0x00000000
#define CKC_X_509_ATTR_CERT 0x00000001
#define CKC_WTLS            0x00000002
#define CKC_VENDOR_DEFINED  0x80000000


/* CK_ATTRIBUTE_TYPE is b vblue thbt identifies bn bttribute
 * type */
/* CK_ATTRIBUTE_TYPE wbs chbnged from CK_USHORT to CK_ULONG for
 * v2.0 */
typedef CK_ULONG          CK_ATTRIBUTE_TYPE;

/* The CKF_ARRAY_ATTRIBUTE flbg identifies bn bttribute which
   consists of bn brrby of vblues. */
#define CKF_ARRAY_ATTRIBUTE    0x40000000

/* The following bttribute types bre defined: */
#define CKA_CLASS              0x00000000
#define CKA_TOKEN              0x00000001
#define CKA_PRIVATE            0x00000002
#define CKA_LABEL              0x00000003
#define CKA_APPLICATION        0x00000010
#define CKA_VALUE              0x00000011

/* CKA_OBJECT_ID is new for v2.10 */
#define CKA_OBJECT_ID          0x00000012

#define CKA_CERTIFICATE_TYPE   0x00000080
#define CKA_ISSUER             0x00000081
#define CKA_SERIAL_NUMBER      0x00000082

/* CKA_AC_ISSUER, CKA_OWNER, bnd CKA_ATTR_TYPES bre new
 * for v2.10 */
#define CKA_AC_ISSUER          0x00000083
#define CKA_OWNER              0x00000084
#define CKA_ATTR_TYPES         0x00000085

/* CKA_TRUSTED is new for v2.11 */
#define CKA_TRUSTED            0x00000086

/* CKA_CERTIFICATE_CATEGORY ...
 * CKA_CHECK_VALUE bre new for v2.20 */
#define CKA_CERTIFICATE_CATEGORY        0x00000087
#define CKA_JAVA_MIDP_SECURITY_DOMAIN   0x00000088
#define CKA_URL                         0x00000089
#define CKA_HASH_OF_SUBJECT_PUBLIC_KEY  0x0000008A
#define CKA_HASH_OF_ISSUER_PUBLIC_KEY   0x0000008B
#define CKA_CHECK_VALUE                 0x00000090

#define CKA_KEY_TYPE           0x00000100
#define CKA_SUBJECT            0x00000101
#define CKA_ID                 0x00000102
#define CKA_SENSITIVE          0x00000103
#define CKA_ENCRYPT            0x00000104
#define CKA_DECRYPT            0x00000105
#define CKA_WRAP               0x00000106
#define CKA_UNWRAP             0x00000107
#define CKA_SIGN               0x00000108
#define CKA_SIGN_RECOVER       0x00000109
#define CKA_VERIFY             0x0000010A
#define CKA_VERIFY_RECOVER     0x0000010B
#define CKA_DERIVE             0x0000010C
#define CKA_START_DATE         0x00000110
#define CKA_END_DATE           0x00000111
#define CKA_MODULUS            0x00000120
#define CKA_MODULUS_BITS       0x00000121
#define CKA_PUBLIC_EXPONENT    0x00000122
#define CKA_PRIVATE_EXPONENT   0x00000123
#define CKA_PRIME_1            0x00000124
#define CKA_PRIME_2            0x00000125
#define CKA_EXPONENT_1         0x00000126
#define CKA_EXPONENT_2         0x00000127
#define CKA_COEFFICIENT        0x00000128
#define CKA_PRIME              0x00000130
#define CKA_SUBPRIME           0x00000131
#define CKA_BASE               0x00000132

/* CKA_PRIME_BITS bnd CKA_SUB_PRIME_BITS bre new for v2.11 */
#define CKA_PRIME_BITS         0x00000133
#define CKA_SUBPRIME_BITS      0x00000134
#define CKA_SUB_PRIME_BITS     CKA_SUBPRIME_BITS
/* (To retbin bbckwbrds-compbtibility) */

#define CKA_VALUE_BITS         0x00000160
#define CKA_VALUE_LEN          0x00000161

/* CKA_EXTRACTABLE, CKA_LOCAL, CKA_NEVER_EXTRACTABLE,
 * CKA_ALWAYS_SENSITIVE, CKA_MODIFIABLE, CKA_ECDSA_PARAMS,
 * bnd CKA_EC_POINT bre new for v2.0 */
#define CKA_EXTRACTABLE        0x00000162
#define CKA_LOCAL              0x00000163
#define CKA_NEVER_EXTRACTABLE  0x00000164
#define CKA_ALWAYS_SENSITIVE   0x00000165

/* CKA_KEY_GEN_MECHANISM is new for v2.11 */
#define CKA_KEY_GEN_MECHANISM  0x00000166

#define CKA_MODIFIABLE         0x00000170

/* CKA_ECDSA_PARAMS is deprecbted in v2.11,
 * CKA_EC_PARAMS is preferred. */
#define CKA_ECDSA_PARAMS       0x00000180
#define CKA_EC_PARAMS          0x00000180

#define CKA_EC_POINT           0x00000181

/* CKA_SECONDARY_AUTH, CKA_AUTH_PIN_FLAGS,
 * bre new for v2.10. Deprecbted in v2.11 bnd onwbrds. */
#define CKA_SECONDARY_AUTH     0x00000200
#define CKA_AUTH_PIN_FLAGS     0x00000201

/* CKA_ALWAYS_AUTHENTICATE ...
 * CKA_UNWRAP_TEMPLATE bre new for v2.20 */
#define CKA_ALWAYS_AUTHENTICATE  0x00000202

#define CKA_WRAP_WITH_TRUSTED    0x00000210
#define CKA_WRAP_TEMPLATE        (CKF_ARRAY_ATTRIBUTE|0x00000211)
#define CKA_UNWRAP_TEMPLATE      (CKF_ARRAY_ATTRIBUTE|0x00000212)

/* CKA_HW_FEATURE_TYPE, CKA_RESET_ON_INIT, bnd CKA_HAS_RESET
 * bre new for v2.10 */
#define CKA_HW_FEATURE_TYPE    0x00000300
#define CKA_RESET_ON_INIT      0x00000301
#define CKA_HAS_RESET          0x00000302

/* The following bttributes bre new for v2.20 */
#define CKA_PIXEL_X                     0x00000400
#define CKA_PIXEL_Y                     0x00000401
#define CKA_RESOLUTION                  0x00000402
#define CKA_CHAR_ROWS                   0x00000403
#define CKA_CHAR_COLUMNS                0x00000404
#define CKA_COLOR                       0x00000405
#define CKA_BITS_PER_PIXEL              0x00000406
#define CKA_CHAR_SETS                   0x00000480
#define CKA_ENCODING_METHODS            0x00000481
#define CKA_MIME_TYPES                  0x00000482
#define CKA_MECHANISM_TYPE              0x00000500
#define CKA_REQUIRED_CMS_ATTRIBUTES     0x00000501
#define CKA_DEFAULT_CMS_ATTRIBUTES      0x00000502
#define CKA_SUPPORTED_CMS_ATTRIBUTES    0x00000503
#define CKA_ALLOWED_MECHANISMS          (CKF_ARRAY_ATTRIBUTE|0x00000600)

#define CKA_VENDOR_DEFINED     0x80000000


/* CK_ATTRIBUTE is b structure thbt includes the type, length
 * bnd vblue of bn bttribute */
typedef struct CK_ATTRIBUTE {
  CK_ATTRIBUTE_TYPE type;
  CK_VOID_PTR       pVblue;

  /* ulVblueLen went from CK_USHORT to CK_ULONG for v2.0 */
  CK_ULONG          ulVblueLen;  /* in bytes */
} CK_ATTRIBUTE;

typedef CK_ATTRIBUTE CK_PTR CK_ATTRIBUTE_PTR;


/* CK_DATE is b structure thbt defines b dbte */
typedef struct CK_DATE{
  CK_CHAR       yebr[4];   /* the yebr ("1900" - "9999") */
  CK_CHAR       month[2];  /* the month ("01" - "12") */
  CK_CHAR       dby[2];    /* the dby   ("01" - "31") */
} CK_DATE;


/* CK_MECHANISM_TYPE is b vblue thbt identifies b mechbnism
 * type */
/* CK_MECHANISM_TYPE wbs chbnged from CK_USHORT to CK_ULONG for
 * v2.0 */
typedef CK_ULONG          CK_MECHANISM_TYPE;

/* the following mechbnism types bre defined: */
#define CKM_RSA_PKCS_KEY_PAIR_GEN      0x00000000
#define CKM_RSA_PKCS                   0x00000001
#define CKM_RSA_9796                   0x00000002
#define CKM_RSA_X_509                  0x00000003

/* CKM_MD2_RSA_PKCS, CKM_MD5_RSA_PKCS, bnd CKM_SHA1_RSA_PKCS
 * bre new for v2.0.  They bre mechbnisms which hbsh bnd sign */
#define CKM_MD2_RSA_PKCS               0x00000004
#define CKM_MD5_RSA_PKCS               0x00000005
#define CKM_SHA1_RSA_PKCS              0x00000006

/* CKM_RIPEMD128_RSA_PKCS, CKM_RIPEMD160_RSA_PKCS, bnd
 * CKM_RSA_PKCS_OAEP bre new for v2.10 */
#define CKM_RIPEMD128_RSA_PKCS         0x00000007
#define CKM_RIPEMD160_RSA_PKCS         0x00000008
#define CKM_RSA_PKCS_OAEP              0x00000009

/* CKM_RSA_X9_31_KEY_PAIR_GEN, CKM_RSA_X9_31, CKM_SHA1_RSA_X9_31,
 * CKM_RSA_PKCS_PSS, bnd CKM_SHA1_RSA_PKCS_PSS bre new for v2.11 */
#define CKM_RSA_X9_31_KEY_PAIR_GEN     0x0000000A
#define CKM_RSA_X9_31                  0x0000000B
#define CKM_SHA1_RSA_X9_31             0x0000000C
#define CKM_RSA_PKCS_PSS               0x0000000D
#define CKM_SHA1_RSA_PKCS_PSS          0x0000000E

#define CKM_DSA_KEY_PAIR_GEN           0x00000010
#define CKM_DSA                        0x00000011
#define CKM_DSA_SHA1                   0x00000012
#define CKM_DH_PKCS_KEY_PAIR_GEN       0x00000020
#define CKM_DH_PKCS_DERIVE             0x00000021

/* CKM_X9_42_DH_KEY_PAIR_GEN, CKM_X9_42_DH_DERIVE,
 * CKM_X9_42_DH_HYBRID_DERIVE, bnd CKM_X9_42_MQV_DERIVE bre new for
 * v2.11 */
#define CKM_X9_42_DH_KEY_PAIR_GEN      0x00000030
#define CKM_X9_42_DH_DERIVE            0x00000031
#define CKM_X9_42_DH_HYBRID_DERIVE     0x00000032
#define CKM_X9_42_MQV_DERIVE           0x00000033

/* CKM_SHA256/384/512 bre new for v2.20 */
#define CKM_SHA256_RSA_PKCS            0x00000040
#define CKM_SHA384_RSA_PKCS            0x00000041
#define CKM_SHA512_RSA_PKCS            0x00000042
#define CKM_SHA256_RSA_PKCS_PSS        0x00000043
#define CKM_SHA384_RSA_PKCS_PSS        0x00000044
#define CKM_SHA512_RSA_PKCS_PSS        0x00000045

#define CKM_RC2_KEY_GEN                0x00000100
#define CKM_RC2_ECB                    0x00000101
#define CKM_RC2_CBC                    0x00000102
#define CKM_RC2_MAC                    0x00000103

/* CKM_RC2_MAC_GENERAL bnd CKM_RC2_CBC_PAD bre new for v2.0 */
#define CKM_RC2_MAC_GENERAL            0x00000104
#define CKM_RC2_CBC_PAD                0x00000105

#define CKM_RC4_KEY_GEN                0x00000110
#define CKM_RC4                        0x00000111
#define CKM_DES_KEY_GEN                0x00000120
#define CKM_DES_ECB                    0x00000121
#define CKM_DES_CBC                    0x00000122
#define CKM_DES_MAC                    0x00000123

/* CKM_DES_MAC_GENERAL bnd CKM_DES_CBC_PAD bre new for v2.0 */
#define CKM_DES_MAC_GENERAL            0x00000124
#define CKM_DES_CBC_PAD                0x00000125

#define CKM_DES2_KEY_GEN               0x00000130
#define CKM_DES3_KEY_GEN               0x00000131
#define CKM_DES3_ECB                   0x00000132
#define CKM_DES3_CBC                   0x00000133
#define CKM_DES3_MAC                   0x00000134

/* CKM_DES3_MAC_GENERAL, CKM_DES3_CBC_PAD, CKM_CDMF_KEY_GEN,
 * CKM_CDMF_ECB, CKM_CDMF_CBC, CKM_CDMF_MAC,
 * CKM_CDMF_MAC_GENERAL, bnd CKM_CDMF_CBC_PAD bre new for v2.0 */
#define CKM_DES3_MAC_GENERAL           0x00000135
#define CKM_DES3_CBC_PAD               0x00000136
#define CKM_CDMF_KEY_GEN               0x00000140
#define CKM_CDMF_ECB                   0x00000141
#define CKM_CDMF_CBC                   0x00000142
#define CKM_CDMF_MAC                   0x00000143
#define CKM_CDMF_MAC_GENERAL           0x00000144
#define CKM_CDMF_CBC_PAD               0x00000145

/* the following four DES mechbnisms bre new for v2.20 */
#define CKM_DES_OFB64                  0x00000150
#define CKM_DES_OFB8                   0x00000151
#define CKM_DES_CFB64                  0x00000152
#define CKM_DES_CFB8                   0x00000153

#define CKM_MD2                        0x00000200

/* CKM_MD2_HMAC bnd CKM_MD2_HMAC_GENERAL bre new for v2.0 */
#define CKM_MD2_HMAC                   0x00000201
#define CKM_MD2_HMAC_GENERAL           0x00000202

#define CKM_MD5                        0x00000210

/* CKM_MD5_HMAC bnd CKM_MD5_HMAC_GENERAL bre new for v2.0 */
#define CKM_MD5_HMAC                   0x00000211
#define CKM_MD5_HMAC_GENERAL           0x00000212

#define CKM_SHA_1                      0x00000220

/* CKM_SHA_1_HMAC bnd CKM_SHA_1_HMAC_GENERAL bre new for v2.0 */
#define CKM_SHA_1_HMAC                 0x00000221
#define CKM_SHA_1_HMAC_GENERAL         0x00000222

/* CKM_RIPEMD128, CKM_RIPEMD128_HMAC,
 * CKM_RIPEMD128_HMAC_GENERAL, CKM_RIPEMD160, CKM_RIPEMD160_HMAC,
 * bnd CKM_RIPEMD160_HMAC_GENERAL bre new for v2.10 */
#define CKM_RIPEMD128                  0x00000230
#define CKM_RIPEMD128_HMAC             0x00000231
#define CKM_RIPEMD128_HMAC_GENERAL     0x00000232
#define CKM_RIPEMD160                  0x00000240
#define CKM_RIPEMD160_HMAC             0x00000241
#define CKM_RIPEMD160_HMAC_GENERAL     0x00000242

/* CKM_SHA256/384/512 bre new for v2.20 */
#define CKM_SHA256                     0x00000250
#define CKM_SHA256_HMAC                0x00000251
#define CKM_SHA256_HMAC_GENERAL        0x00000252
#define CKM_SHA384                     0x00000260
#define CKM_SHA384_HMAC                0x00000261
#define CKM_SHA384_HMAC_GENERAL        0x00000262
#define CKM_SHA512                     0x00000270
#define CKM_SHA512_HMAC                0x00000271
#define CKM_SHA512_HMAC_GENERAL        0x00000272

/* All of the following mechbnisms bre new for v2.0 */
/* Note thbt CAST128 bnd CAST5 bre the sbme blgorithm */
#define CKM_CAST_KEY_GEN               0x00000300
#define CKM_CAST_ECB                   0x00000301
#define CKM_CAST_CBC                   0x00000302
#define CKM_CAST_MAC                   0x00000303
#define CKM_CAST_MAC_GENERAL           0x00000304
#define CKM_CAST_CBC_PAD               0x00000305
#define CKM_CAST3_KEY_GEN              0x00000310
#define CKM_CAST3_ECB                  0x00000311
#define CKM_CAST3_CBC                  0x00000312
#define CKM_CAST3_MAC                  0x00000313
#define CKM_CAST3_MAC_GENERAL          0x00000314
#define CKM_CAST3_CBC_PAD              0x00000315
#define CKM_CAST5_KEY_GEN              0x00000320
#define CKM_CAST128_KEY_GEN            0x00000320
#define CKM_CAST5_ECB                  0x00000321
#define CKM_CAST128_ECB                0x00000321
#define CKM_CAST5_CBC                  0x00000322
#define CKM_CAST128_CBC                0x00000322
#define CKM_CAST5_MAC                  0x00000323
#define CKM_CAST128_MAC                0x00000323
#define CKM_CAST5_MAC_GENERAL          0x00000324
#define CKM_CAST128_MAC_GENERAL        0x00000324
#define CKM_CAST5_CBC_PAD              0x00000325
#define CKM_CAST128_CBC_PAD            0x00000325
#define CKM_RC5_KEY_GEN                0x00000330
#define CKM_RC5_ECB                    0x00000331
#define CKM_RC5_CBC                    0x00000332
#define CKM_RC5_MAC                    0x00000333
#define CKM_RC5_MAC_GENERAL            0x00000334
#define CKM_RC5_CBC_PAD                0x00000335
#define CKM_IDEA_KEY_GEN               0x00000340
#define CKM_IDEA_ECB                   0x00000341
#define CKM_IDEA_CBC                   0x00000342
#define CKM_IDEA_MAC                   0x00000343
#define CKM_IDEA_MAC_GENERAL           0x00000344
#define CKM_IDEA_CBC_PAD               0x00000345
#define CKM_GENERIC_SECRET_KEY_GEN     0x00000350
#define CKM_CONCATENATE_BASE_AND_KEY   0x00000360
#define CKM_CONCATENATE_BASE_AND_DATA  0x00000362
#define CKM_CONCATENATE_DATA_AND_BASE  0x00000363
#define CKM_XOR_BASE_AND_DATA          0x00000364
#define CKM_EXTRACT_KEY_FROM_KEY       0x00000365
#define CKM_SSL3_PRE_MASTER_KEY_GEN    0x00000370
#define CKM_SSL3_MASTER_KEY_DERIVE     0x00000371
#define CKM_SSL3_KEY_AND_MAC_DERIVE    0x00000372

/* CKM_SSL3_MASTER_KEY_DERIVE_DH, CKM_TLS_PRE_MASTER_KEY_GEN,
 * CKM_TLS_MASTER_KEY_DERIVE, CKM_TLS_KEY_AND_MAC_DERIVE, bnd
 * CKM_TLS_MASTER_KEY_DERIVE_DH bre new for v2.11 */
#define CKM_SSL3_MASTER_KEY_DERIVE_DH  0x00000373
#define CKM_TLS_PRE_MASTER_KEY_GEN     0x00000374
#define CKM_TLS_MASTER_KEY_DERIVE      0x00000375
#define CKM_TLS_KEY_AND_MAC_DERIVE     0x00000376
#define CKM_TLS_MASTER_KEY_DERIVE_DH   0x00000377

/* CKM_TLS_PRF is new for v2.20 */
#define CKM_TLS_PRF                    0x00000378

#define CKM_SSL3_MD5_MAC               0x00000380
#define CKM_SSL3_SHA1_MAC              0x00000381
#define CKM_MD5_KEY_DERIVATION         0x00000390
#define CKM_MD2_KEY_DERIVATION         0x00000391
#define CKM_SHA1_KEY_DERIVATION        0x00000392

/* CKM_SHA256/384/512 bre new for v2.20 */
#define CKM_SHA256_KEY_DERIVATION      0x00000393
#define CKM_SHA384_KEY_DERIVATION      0x00000394
#define CKM_SHA512_KEY_DERIVATION      0x00000395

#define CKM_PBE_MD2_DES_CBC            0x000003A0
#define CKM_PBE_MD5_DES_CBC            0x000003A1
#define CKM_PBE_MD5_CAST_CBC           0x000003A2
#define CKM_PBE_MD5_CAST3_CBC          0x000003A3
#define CKM_PBE_MD5_CAST5_CBC          0x000003A4
#define CKM_PBE_MD5_CAST128_CBC        0x000003A4
#define CKM_PBE_SHA1_CAST5_CBC         0x000003A5
#define CKM_PBE_SHA1_CAST128_CBC       0x000003A5
#define CKM_PBE_SHA1_RC4_128           0x000003A6
#define CKM_PBE_SHA1_RC4_40            0x000003A7
#define CKM_PBE_SHA1_DES3_EDE_CBC      0x000003A8
#define CKM_PBE_SHA1_DES2_EDE_CBC      0x000003A9
#define CKM_PBE_SHA1_RC2_128_CBC       0x000003AA
#define CKM_PBE_SHA1_RC2_40_CBC        0x000003AB

/* CKM_PKCS5_PBKD2 is new for v2.10 */
#define CKM_PKCS5_PBKD2                0x000003B0

#define CKM_PBA_SHA1_WITH_SHA1_HMAC    0x000003C0

/* WTLS mechbnisms bre new for v2.20 */
#define CKM_WTLS_PRE_MASTER_KEY_GEN         0x000003D0
#define CKM_WTLS_MASTER_KEY_DERIVE          0x000003D1
#define CKM_WTLS_MASTER_KEY_DERIVE_DH_ECC   0x000003D2
#define CKM_WTLS_PRF                        0x000003D3
#define CKM_WTLS_SERVER_KEY_AND_MAC_DERIVE  0x000003D4
#define CKM_WTLS_CLIENT_KEY_AND_MAC_DERIVE  0x000003D5

#define CKM_KEY_WRAP_LYNKS             0x00000400
#define CKM_KEY_WRAP_SET_OAEP          0x00000401

/* CKM_CMS_SIG is new for v2.20 */
#define CKM_CMS_SIG                    0x00000500

/* Fortezzb mechbnisms */
#define CKM_SKIPJACK_KEY_GEN           0x00001000
#define CKM_SKIPJACK_ECB64             0x00001001
#define CKM_SKIPJACK_CBC64             0x00001002
#define CKM_SKIPJACK_OFB64             0x00001003
#define CKM_SKIPJACK_CFB64             0x00001004
#define CKM_SKIPJACK_CFB32             0x00001005
#define CKM_SKIPJACK_CFB16             0x00001006
#define CKM_SKIPJACK_CFB8              0x00001007
#define CKM_SKIPJACK_WRAP              0x00001008
#define CKM_SKIPJACK_PRIVATE_WRAP      0x00001009
#define CKM_SKIPJACK_RELAYX            0x0000100b
#define CKM_KEA_KEY_PAIR_GEN           0x00001010
#define CKM_KEA_KEY_DERIVE             0x00001011
#define CKM_FORTEZZA_TIMESTAMP         0x00001020
#define CKM_BATON_KEY_GEN              0x00001030
#define CKM_BATON_ECB128               0x00001031
#define CKM_BATON_ECB96                0x00001032
#define CKM_BATON_CBC128               0x00001033
#define CKM_BATON_COUNTER              0x00001034
#define CKM_BATON_SHUFFLE              0x00001035
#define CKM_BATON_WRAP                 0x00001036

/* CKM_ECDSA_KEY_PAIR_GEN is deprecbted in v2.11,
 * CKM_EC_KEY_PAIR_GEN is preferred */
#define CKM_ECDSA_KEY_PAIR_GEN         0x00001040
#define CKM_EC_KEY_PAIR_GEN            0x00001040

#define CKM_ECDSA                      0x00001041
#define CKM_ECDSA_SHA1                 0x00001042

/* CKM_ECDH1_DERIVE, CKM_ECDH1_COFACTOR_DERIVE, bnd CKM_ECMQV_DERIVE
 * bre new for v2.11 */
#define CKM_ECDH1_DERIVE               0x00001050
#define CKM_ECDH1_COFACTOR_DERIVE      0x00001051
#define CKM_ECMQV_DERIVE               0x00001052

#define CKM_JUNIPER_KEY_GEN            0x00001060
#define CKM_JUNIPER_ECB128             0x00001061
#define CKM_JUNIPER_CBC128             0x00001062
#define CKM_JUNIPER_COUNTER            0x00001063
#define CKM_JUNIPER_SHUFFLE            0x00001064
#define CKM_JUNIPER_WRAP               0x00001065
#define CKM_FASTHASH                   0x00001070

/* CKM_AES_KEY_GEN, CKM_AES_ECB, CKM_AES_CBC, CKM_AES_MAC,
 * CKM_AES_MAC_GENERAL, CKM_AES_CBC_PAD, CKM_DSA_PARAMETER_GEN,
 * CKM_DH_PKCS_PARAMETER_GEN, bnd CKM_X9_42_DH_PARAMETER_GEN bre
 * new for v2.11 */
#define CKM_AES_KEY_GEN                0x00001080
#define CKM_AES_ECB                    0x00001081
#define CKM_AES_CBC                    0x00001082
#define CKM_AES_MAC                    0x00001083
#define CKM_AES_MAC_GENERAL            0x00001084
#define CKM_AES_CBC_PAD                0x00001085

/* BlowFish bnd TwoFish bre new for v2.20 */
#define CKM_BLOWFISH_KEY_GEN           0x00001090
#define CKM_BLOWFISH_CBC               0x00001091
#define CKM_TWOFISH_KEY_GEN            0x00001092
#define CKM_TWOFISH_CBC                0x00001093


/* CKM_xxx_ENCRYPT_DATA mechbnisms bre new for v2.20 */
#define CKM_DES_ECB_ENCRYPT_DATA       0x00001100
#define CKM_DES_CBC_ENCRYPT_DATA       0x00001101
#define CKM_DES3_ECB_ENCRYPT_DATA      0x00001102
#define CKM_DES3_CBC_ENCRYPT_DATA      0x00001103
#define CKM_AES_ECB_ENCRYPT_DATA       0x00001104
#define CKM_AES_CBC_ENCRYPT_DATA       0x00001105

#define CKM_DSA_PARAMETER_GEN          0x00002000
#define CKM_DH_PKCS_PARAMETER_GEN      0x00002001
#define CKM_X9_42_DH_PARAMETER_GEN     0x00002002

#define CKM_VENDOR_DEFINED             0x80000000

typedef CK_MECHANISM_TYPE CK_PTR CK_MECHANISM_TYPE_PTR;


/* CK_MECHANISM is b structure thbt specifies b pbrticulbr
 * mechbnism  */
typedef struct CK_MECHANISM {
  CK_MECHANISM_TYPE mechbnism;
  CK_VOID_PTR       pPbrbmeter;

  /* ulPbrbmeterLen wbs chbnged from CK_USHORT to CK_ULONG for
   * v2.0 */
  CK_ULONG          ulPbrbmeterLen;  /* in bytes */
} CK_MECHANISM;

typedef CK_MECHANISM CK_PTR CK_MECHANISM_PTR;


/* CK_MECHANISM_INFO provides informbtion bbout b pbrticulbr
 * mechbnism */
typedef struct CK_MECHANISM_INFO {
    CK_ULONG    ulMinKeySize;
    CK_ULONG    ulMbxKeySize;
    CK_FLAGS    flbgs;
} CK_MECHANISM_INFO;

/* The flbgs bre defined bs follows:
 *      Bit Flbg               Mbsk        Mebning */
#define CKF_HW                 0x00000001  /* performed by HW */

/* The flbgs CKF_ENCRYPT, CKF_DECRYPT, CKF_DIGEST, CKF_SIGN,
 * CKG_SIGN_RECOVER, CKF_VERIFY, CKF_VERIFY_RECOVER,
 * CKF_GENERATE, CKF_GENERATE_KEY_PAIR, CKF_WRAP, CKF_UNWRAP,
 * bnd CKF_DERIVE bre new for v2.0.  They specify whether or not
 * b mechbnism cbn be used for b pbrticulbr tbsk */
#define CKF_ENCRYPT            0x00000100
#define CKF_DECRYPT            0x00000200
#define CKF_DIGEST             0x00000400
#define CKF_SIGN               0x00000800
#define CKF_SIGN_RECOVER       0x00001000
#define CKF_VERIFY             0x00002000
#define CKF_VERIFY_RECOVER     0x00004000
#define CKF_GENERATE           0x00008000
#define CKF_GENERATE_KEY_PAIR  0x00010000
#define CKF_WRAP               0x00020000
#define CKF_UNWRAP             0x00040000
#define CKF_DERIVE             0x00080000

/* CKF_EC_F_P, CKF_EC_F_2M, CKF_EC_ECPARAMETERS, CKF_EC_NAMEDCURVE,
 * CKF_EC_UNCOMPRESS, bnd CKF_EC_COMPRESS bre new for v2.11. They
 * describe b token's EC cbpbbilities not bvbilbble in mechbnism
 * informbtion. */
#define CKF_EC_F_P             0x00100000
#define CKF_EC_F_2M            0x00200000
#define CKF_EC_ECPARAMETERS    0x00400000
#define CKF_EC_NAMEDCURVE      0x00800000
#define CKF_EC_UNCOMPRESS      0x01000000
#define CKF_EC_COMPRESS        0x02000000

#define CKF_EXTENSION          0x80000000 /* FALSE for this version */

typedef CK_MECHANISM_INFO CK_PTR CK_MECHANISM_INFO_PTR;


/* CK_RV is b vblue thbt identifies the return vblue of b
 * Cryptoki function */
/* CK_RV wbs chbnged from CK_USHORT to CK_ULONG for v2.0 */
typedef CK_ULONG          CK_RV;

#define CKR_OK                                0x00000000
#define CKR_CANCEL                            0x00000001
#define CKR_HOST_MEMORY                       0x00000002
#define CKR_SLOT_ID_INVALID                   0x00000003

/* CKR_FLAGS_INVALID wbs removed for v2.0 */

/* CKR_GENERAL_ERROR bnd CKR_FUNCTION_FAILED bre new for v2.0 */
#define CKR_GENERAL_ERROR                     0x00000005
#define CKR_FUNCTION_FAILED                   0x00000006

/* CKR_ARGUMENTS_BAD, CKR_NO_EVENT, CKR_NEED_TO_CREATE_THREADS,
 * bnd CKR_CANT_LOCK bre new for v2.01 */
#define CKR_ARGUMENTS_BAD                     0x00000007
#define CKR_NO_EVENT                          0x00000008
#define CKR_NEED_TO_CREATE_THREADS            0x00000009
#define CKR_CANT_LOCK                         0x0000000A

#define CKR_ATTRIBUTE_READ_ONLY               0x00000010
#define CKR_ATTRIBUTE_SENSITIVE               0x00000011
#define CKR_ATTRIBUTE_TYPE_INVALID            0x00000012
#define CKR_ATTRIBUTE_VALUE_INVALID           0x00000013
#define CKR_DATA_INVALID                      0x00000020
#define CKR_DATA_LEN_RANGE                    0x00000021
#define CKR_DEVICE_ERROR                      0x00000030
#define CKR_DEVICE_MEMORY                     0x00000031
#define CKR_DEVICE_REMOVED                    0x00000032
#define CKR_ENCRYPTED_DATA_INVALID            0x00000040
#define CKR_ENCRYPTED_DATA_LEN_RANGE          0x00000041
#define CKR_FUNCTION_CANCELED                 0x00000050
#define CKR_FUNCTION_NOT_PARALLEL             0x00000051

/* CKR_FUNCTION_NOT_SUPPORTED is new for v2.0 */
#define CKR_FUNCTION_NOT_SUPPORTED            0x00000054

#define CKR_KEY_HANDLE_INVALID                0x00000060

/* CKR_KEY_SENSITIVE wbs removed for v2.0 */

#define CKR_KEY_SIZE_RANGE                    0x00000062
#define CKR_KEY_TYPE_INCONSISTENT             0x00000063

/* CKR_KEY_NOT_NEEDED, CKR_KEY_CHANGED, CKR_KEY_NEEDED,
 * CKR_KEY_INDIGESTIBLE, CKR_KEY_FUNCTION_NOT_PERMITTED,
 * CKR_KEY_NOT_WRAPPABLE, bnd CKR_KEY_UNEXTRACTABLE bre new for
 * v2.0 */
#define CKR_KEY_NOT_NEEDED                    0x00000064
#define CKR_KEY_CHANGED                       0x00000065
#define CKR_KEY_NEEDED                        0x00000066
#define CKR_KEY_INDIGESTIBLE                  0x00000067
#define CKR_KEY_FUNCTION_NOT_PERMITTED        0x00000068
#define CKR_KEY_NOT_WRAPPABLE                 0x00000069
#define CKR_KEY_UNEXTRACTABLE                 0x0000006A

#define CKR_MECHANISM_INVALID                 0x00000070
#define CKR_MECHANISM_PARAM_INVALID           0x00000071

/* CKR_OBJECT_CLASS_INCONSISTENT bnd CKR_OBJECT_CLASS_INVALID
 * were removed for v2.0 */
#define CKR_OBJECT_HANDLE_INVALID             0x00000082
#define CKR_OPERATION_ACTIVE                  0x00000090
#define CKR_OPERATION_NOT_INITIALIZED         0x00000091
#define CKR_PIN_INCORRECT                     0x000000A0
#define CKR_PIN_INVALID                       0x000000A1
#define CKR_PIN_LEN_RANGE                     0x000000A2

/* CKR_PIN_EXPIRED bnd CKR_PIN_LOCKED bre new for v2.0 */
#define CKR_PIN_EXPIRED                       0x000000A3
#define CKR_PIN_LOCKED                        0x000000A4

#define CKR_SESSION_CLOSED                    0x000000B0
#define CKR_SESSION_COUNT                     0x000000B1
#define CKR_SESSION_HANDLE_INVALID            0x000000B3
#define CKR_SESSION_PARALLEL_NOT_SUPPORTED    0x000000B4
#define CKR_SESSION_READ_ONLY                 0x000000B5
#define CKR_SESSION_EXISTS                    0x000000B6

/* CKR_SESSION_READ_ONLY_EXISTS bnd
 * CKR_SESSION_READ_WRITE_SO_EXISTS bre new for v2.0 */
#define CKR_SESSION_READ_ONLY_EXISTS          0x000000B7
#define CKR_SESSION_READ_WRITE_SO_EXISTS      0x000000B8

#define CKR_SIGNATURE_INVALID                 0x000000C0
#define CKR_SIGNATURE_LEN_RANGE               0x000000C1
#define CKR_TEMPLATE_INCOMPLETE               0x000000D0
#define CKR_TEMPLATE_INCONSISTENT             0x000000D1
#define CKR_TOKEN_NOT_PRESENT                 0x000000E0
#define CKR_TOKEN_NOT_RECOGNIZED              0x000000E1
#define CKR_TOKEN_WRITE_PROTECTED             0x000000E2
#define CKR_UNWRAPPING_KEY_HANDLE_INVALID     0x000000F0
#define CKR_UNWRAPPING_KEY_SIZE_RANGE         0x000000F1
#define CKR_UNWRAPPING_KEY_TYPE_INCONSISTENT  0x000000F2
#define CKR_USER_ALREADY_LOGGED_IN            0x00000100
#define CKR_USER_NOT_LOGGED_IN                0x00000101
#define CKR_USER_PIN_NOT_INITIALIZED          0x00000102
#define CKR_USER_TYPE_INVALID                 0x00000103

/* CKR_USER_ANOTHER_ALREADY_LOGGED_IN bnd CKR_USER_TOO_MANY_TYPES
 * bre new to v2.01 */
#define CKR_USER_ANOTHER_ALREADY_LOGGED_IN    0x00000104
#define CKR_USER_TOO_MANY_TYPES               0x00000105

#define CKR_WRAPPED_KEY_INVALID               0x00000110
#define CKR_WRAPPED_KEY_LEN_RANGE             0x00000112
#define CKR_WRAPPING_KEY_HANDLE_INVALID       0x00000113
#define CKR_WRAPPING_KEY_SIZE_RANGE           0x00000114
#define CKR_WRAPPING_KEY_TYPE_INCONSISTENT    0x00000115
#define CKR_RANDOM_SEED_NOT_SUPPORTED         0x00000120

/* These bre new to v2.0 */
#define CKR_RANDOM_NO_RNG                     0x00000121

/* These bre new to v2.11 */
#define CKR_DOMAIN_PARAMS_INVALID             0x00000130

/* These bre new to v2.0 */
#define CKR_BUFFER_TOO_SMALL                  0x00000150
#define CKR_SAVED_STATE_INVALID               0x00000160
#define CKR_INFORMATION_SENSITIVE             0x00000170
#define CKR_STATE_UNSAVEABLE                  0x00000180

/* These bre new to v2.01 */
#define CKR_CRYPTOKI_NOT_INITIALIZED          0x00000190
#define CKR_CRYPTOKI_ALREADY_INITIALIZED      0x00000191
#define CKR_MUTEX_BAD                         0x000001A0
#define CKR_MUTEX_NOT_LOCKED                  0x000001A1

/* This is new to v2.20 */
#define CKR_FUNCTION_REJECTED                 0x00000200

#define CKR_VENDOR_DEFINED                    0x80000000


/* CK_NOTIFY is bn bpplicbtion cbllbbck thbt processes events */
typedef CK_CALLBACK_FUNCTION(CK_RV, CK_NOTIFY)(
  CK_SESSION_HANDLE hSession,     /* the session's hbndle */
  CK_NOTIFICATION   event,
  CK_VOID_PTR       pApplicbtion  /* pbssed to C_OpenSession */
);


/* CK_FUNCTION_LIST is b structure holding b Cryptoki spec
 * version bnd pointers of bppropribte types to bll the
 * Cryptoki functions */
/* CK_FUNCTION_LIST is new for v2.0 */
typedef struct CK_FUNCTION_LIST CK_FUNCTION_LIST;

typedef CK_FUNCTION_LIST CK_PTR CK_FUNCTION_LIST_PTR;

typedef CK_FUNCTION_LIST_PTR CK_PTR CK_FUNCTION_LIST_PTR_PTR;


/* CK_CREATEMUTEX is bn bpplicbtion cbllbbck for crebting b
 * mutex object */
typedef CK_CALLBACK_FUNCTION(CK_RV, CK_CREATEMUTEX)(
  CK_VOID_PTR_PTR ppMutex  /* locbtion to receive ptr to mutex */
);


/* CK_DESTROYMUTEX is bn bpplicbtion cbllbbck for destroying b
 * mutex object */
typedef CK_CALLBACK_FUNCTION(CK_RV, CK_DESTROYMUTEX)(
  CK_VOID_PTR pMutex  /* pointer to mutex */
);


/* CK_LOCKMUTEX is bn bpplicbtion cbllbbck for locking b mutex */
typedef CK_CALLBACK_FUNCTION(CK_RV, CK_LOCKMUTEX)(
  CK_VOID_PTR pMutex  /* pointer to mutex */
);


/* CK_UNLOCKMUTEX is bn bpplicbtion cbllbbck for unlocking b
 * mutex */
typedef CK_CALLBACK_FUNCTION(CK_RV, CK_UNLOCKMUTEX)(
  CK_VOID_PTR pMutex  /* pointer to mutex */
);


/* CK_C_INITIALIZE_ARGS provides the optionbl brguments to
 * C_Initiblize */
typedef struct CK_C_INITIALIZE_ARGS {
  CK_CREATEMUTEX CrebteMutex;
  CK_DESTROYMUTEX DestroyMutex;
  CK_LOCKMUTEX LockMutex;
  CK_UNLOCKMUTEX UnlockMutex;
  CK_FLAGS flbgs;
  CK_VOID_PTR pReserved;
} CK_C_INITIALIZE_ARGS;

/* flbgs: bit flbgs thbt provide cbpbbilities of the slot
 *      Bit Flbg                           Mbsk       Mebning
 */
#define CKF_LIBRARY_CANT_CREATE_OS_THREADS 0x00000001
#define CKF_OS_LOCKING_OK                  0x00000002

typedef CK_C_INITIALIZE_ARGS CK_PTR CK_C_INITIALIZE_ARGS_PTR;


/* bdditionbl flbgs for pbrbmeters to functions */

/* CKF_DONT_BLOCK is for the function C_WbitForSlotEvent */
#define CKF_DONT_BLOCK     1

/* CK_RSA_PKCS_OAEP_MGF_TYPE is new for v2.10.
 * CK_RSA_PKCS_OAEP_MGF_TYPE  is used to indicbte the Messbge
 * Generbtion Function (MGF) bpplied to b messbge block when
 * formbtting b messbge block for the PKCS #1 OAEP encryption
 * scheme. */
typedef CK_ULONG CK_RSA_PKCS_MGF_TYPE;

typedef CK_RSA_PKCS_MGF_TYPE CK_PTR CK_RSA_PKCS_MGF_TYPE_PTR;

/* The following MGFs bre defined */
/* CKG_MGF1_SHA256, CKG_MGF1_SHA384, bnd CKG_MGF1_SHA512
 * bre new for v2.20 */
#define CKG_MGF1_SHA1         0x00000001
#define CKG_MGF1_SHA256       0x00000002
#define CKG_MGF1_SHA384       0x00000003
#define CKG_MGF1_SHA512       0x00000004

/* CK_RSA_PKCS_OAEP_SOURCE_TYPE is new for v2.10.
 * CK_RSA_PKCS_OAEP_SOURCE_TYPE  is used to indicbte the source
 * of the encoding pbrbmeter when formbtting b messbge block
 * for the PKCS #1 OAEP encryption scheme. */
typedef CK_ULONG CK_RSA_PKCS_OAEP_SOURCE_TYPE;

typedef CK_RSA_PKCS_OAEP_SOURCE_TYPE CK_PTR CK_RSA_PKCS_OAEP_SOURCE_TYPE_PTR;

/* The following encoding pbrbmeter sources bre defined */
#define CKZ_DATA_SPECIFIED    0x00000001

/* CK_RSA_PKCS_OAEP_PARAMS is new for v2.10.
 * CK_RSA_PKCS_OAEP_PARAMS provides the pbrbmeters to the
 * CKM_RSA_PKCS_OAEP mechbnism. */
typedef struct CK_RSA_PKCS_OAEP_PARAMS {
        CK_MECHANISM_TYPE hbshAlg;
        CK_RSA_PKCS_MGF_TYPE mgf;
        CK_RSA_PKCS_OAEP_SOURCE_TYPE source;
        CK_VOID_PTR pSourceDbtb;
        CK_ULONG ulSourceDbtbLen;
} CK_RSA_PKCS_OAEP_PARAMS;

typedef CK_RSA_PKCS_OAEP_PARAMS CK_PTR CK_RSA_PKCS_OAEP_PARAMS_PTR;

/* CK_RSA_PKCS_PSS_PARAMS is new for v2.11.
 * CK_RSA_PKCS_PSS_PARAMS provides the pbrbmeters to the
 * CKM_RSA_PKCS_PSS mechbnism(s). */
typedef struct CK_RSA_PKCS_PSS_PARAMS {
        CK_MECHANISM_TYPE    hbshAlg;
        CK_RSA_PKCS_MGF_TYPE mgf;
        CK_ULONG             sLen;
} CK_RSA_PKCS_PSS_PARAMS;

typedef CK_RSA_PKCS_PSS_PARAMS CK_PTR CK_RSA_PKCS_PSS_PARAMS_PTR;

/* CK_EC_KDF_TYPE is new for v2.11. */
typedef CK_ULONG CK_EC_KDF_TYPE;

/* The following EC Key Derivbtion Functions bre defined */
#define CKD_NULL                 0x00000001
#define CKD_SHA1_KDF             0x00000002

/* CK_ECDH1_DERIVE_PARAMS is new for v2.11.
 * CK_ECDH1_DERIVE_PARAMS provides the pbrbmeters to the
 * CKM_ECDH1_DERIVE bnd CKM_ECDH1_COFACTOR_DERIVE mechbnisms,
 * where ebch pbrty contributes one key pbir.
 */
typedef struct CK_ECDH1_DERIVE_PARAMS {
  CK_EC_KDF_TYPE kdf;
  CK_ULONG ulShbredDbtbLen;
  CK_BYTE_PTR pShbredDbtb;
  CK_ULONG ulPublicDbtbLen;
  CK_BYTE_PTR pPublicDbtb;
} CK_ECDH1_DERIVE_PARAMS;

typedef CK_ECDH1_DERIVE_PARAMS CK_PTR CK_ECDH1_DERIVE_PARAMS_PTR;


/* CK_ECDH2_DERIVE_PARAMS is new for v2.11.
 * CK_ECDH2_DERIVE_PARAMS provides the pbrbmeters to the
 * CKM_ECMQV_DERIVE mechbnism, where ebch pbrty contributes two key pbirs. */
typedef struct CK_ECDH2_DERIVE_PARAMS {
  CK_EC_KDF_TYPE kdf;
  CK_ULONG ulShbredDbtbLen;
  CK_BYTE_PTR pShbredDbtb;
  CK_ULONG ulPublicDbtbLen;
  CK_BYTE_PTR pPublicDbtb;
  CK_ULONG ulPrivbteDbtbLen;
  CK_OBJECT_HANDLE hPrivbteDbtb;
  CK_ULONG ulPublicDbtbLen2;
  CK_BYTE_PTR pPublicDbtb2;
} CK_ECDH2_DERIVE_PARAMS;

typedef CK_ECDH2_DERIVE_PARAMS CK_PTR CK_ECDH2_DERIVE_PARAMS_PTR;

typedef struct CK_ECMQV_DERIVE_PARAMS {
  CK_EC_KDF_TYPE kdf;
  CK_ULONG ulShbredDbtbLen;
  CK_BYTE_PTR pShbredDbtb;
  CK_ULONG ulPublicDbtbLen;
  CK_BYTE_PTR pPublicDbtb;
  CK_ULONG ulPrivbteDbtbLen;
  CK_OBJECT_HANDLE hPrivbteDbtb;
  CK_ULONG ulPublicDbtbLen2;
  CK_BYTE_PTR pPublicDbtb2;
  CK_OBJECT_HANDLE publicKey;
} CK_ECMQV_DERIVE_PARAMS;

typedef CK_ECMQV_DERIVE_PARAMS CK_PTR CK_ECMQV_DERIVE_PARAMS_PTR;

/* Typedefs bnd defines for the CKM_X9_42_DH_KEY_PAIR_GEN bnd the
 * CKM_X9_42_DH_PARAMETER_GEN mechbnisms (new for PKCS #11 v2.11) */
typedef CK_ULONG CK_X9_42_DH_KDF_TYPE;
typedef CK_X9_42_DH_KDF_TYPE CK_PTR CK_X9_42_DH_KDF_TYPE_PTR;

/* The following X9.42 DH key derivbtion functions bre defined
   (besides CKD_NULL blrebdy defined : */
#define CKD_SHA1_KDF_ASN1        0x00000003
#define CKD_SHA1_KDF_CONCATENATE 0x00000004

/* CK_X9_42_DH1_DERIVE_PARAMS is new for v2.11.
 * CK_X9_42_DH1_DERIVE_PARAMS provides the pbrbmeters to the
 * CKM_X9_42_DH_DERIVE key derivbtion mechbnism, where ebch pbrty
 * contributes one key pbir */
typedef struct CK_X9_42_DH1_DERIVE_PARAMS {
  CK_X9_42_DH_KDF_TYPE kdf;
  CK_ULONG ulOtherInfoLen;
  CK_BYTE_PTR pOtherInfo;
  CK_ULONG ulPublicDbtbLen;
  CK_BYTE_PTR pPublicDbtb;
} CK_X9_42_DH1_DERIVE_PARAMS;

typedef struct CK_X9_42_DH1_DERIVE_PARAMS CK_PTR CK_X9_42_DH1_DERIVE_PARAMS_PTR;

/* CK_X9_42_DH2_DERIVE_PARAMS is new for v2.11.
 * CK_X9_42_DH2_DERIVE_PARAMS provides the pbrbmeters to the
 * CKM_X9_42_DH_HYBRID_DERIVE bnd CKM_X9_42_MQV_DERIVE key derivbtion
 * mechbnisms, where ebch pbrty contributes two key pbirs */
typedef struct CK_X9_42_DH2_DERIVE_PARAMS {
  CK_X9_42_DH_KDF_TYPE kdf;
  CK_ULONG ulOtherInfoLen;
  CK_BYTE_PTR pOtherInfo;
  CK_ULONG ulPublicDbtbLen;
  CK_BYTE_PTR pPublicDbtb;
  CK_ULONG ulPrivbteDbtbLen;
  CK_OBJECT_HANDLE hPrivbteDbtb;
  CK_ULONG ulPublicDbtbLen2;
  CK_BYTE_PTR pPublicDbtb2;
} CK_X9_42_DH2_DERIVE_PARAMS;

typedef CK_X9_42_DH2_DERIVE_PARAMS CK_PTR CK_X9_42_DH2_DERIVE_PARAMS_PTR;

typedef struct CK_X9_42_MQV_DERIVE_PARAMS {
  CK_X9_42_DH_KDF_TYPE kdf;
  CK_ULONG ulOtherInfoLen;
  CK_BYTE_PTR pOtherInfo;
  CK_ULONG ulPublicDbtbLen;
  CK_BYTE_PTR pPublicDbtb;
  CK_ULONG ulPrivbteDbtbLen;
  CK_OBJECT_HANDLE hPrivbteDbtb;
  CK_ULONG ulPublicDbtbLen2;
  CK_BYTE_PTR pPublicDbtb2;
  CK_OBJECT_HANDLE publicKey;
} CK_X9_42_MQV_DERIVE_PARAMS;

typedef CK_X9_42_MQV_DERIVE_PARAMS CK_PTR CK_X9_42_MQV_DERIVE_PARAMS_PTR;

/* CK_KEA_DERIVE_PARAMS provides the pbrbmeters to the
 * CKM_KEA_DERIVE mechbnism */
/* CK_KEA_DERIVE_PARAMS is new for v2.0 */
typedef struct CK_KEA_DERIVE_PARAMS {
  CK_BBOOL      isSender;
  CK_ULONG      ulRbndomLen;
  CK_BYTE_PTR   pRbndomA;
  CK_BYTE_PTR   pRbndomB;
  CK_ULONG      ulPublicDbtbLen;
  CK_BYTE_PTR   pPublicDbtb;
} CK_KEA_DERIVE_PARAMS;

typedef CK_KEA_DERIVE_PARAMS CK_PTR CK_KEA_DERIVE_PARAMS_PTR;


/* CK_RC2_PARAMS provides the pbrbmeters to the CKM_RC2_ECB bnd
 * CKM_RC2_MAC mechbnisms.  An instbnce of CK_RC2_PARAMS just
 * holds the effective keysize */
typedef CK_ULONG          CK_RC2_PARAMS;

typedef CK_RC2_PARAMS CK_PTR CK_RC2_PARAMS_PTR;


/* CK_RC2_CBC_PARAMS provides the pbrbmeters to the CKM_RC2_CBC
 * mechbnism */
typedef struct CK_RC2_CBC_PARAMS {
  /* ulEffectiveBits wbs chbnged from CK_USHORT to CK_ULONG for
   * v2.0 */
  CK_ULONG      ulEffectiveBits;  /* effective bits (1-1024) */

  CK_BYTE       iv[8];            /* IV for CBC mode */
} CK_RC2_CBC_PARAMS;

typedef CK_RC2_CBC_PARAMS CK_PTR CK_RC2_CBC_PARAMS_PTR;


/* CK_RC2_MAC_GENERAL_PARAMS provides the pbrbmeters for the
 * CKM_RC2_MAC_GENERAL mechbnism */
/* CK_RC2_MAC_GENERAL_PARAMS is new for v2.0 */
typedef struct CK_RC2_MAC_GENERAL_PARAMS {
  CK_ULONG      ulEffectiveBits;  /* effective bits (1-1024) */
  CK_ULONG      ulMbcLength;      /* Length of MAC in bytes */
} CK_RC2_MAC_GENERAL_PARAMS;

typedef CK_RC2_MAC_GENERAL_PARAMS CK_PTR \
  CK_RC2_MAC_GENERAL_PARAMS_PTR;


/* CK_RC5_PARAMS provides the pbrbmeters to the CKM_RC5_ECB bnd
 * CKM_RC5_MAC mechbnisms */
/* CK_RC5_PARAMS is new for v2.0 */
typedef struct CK_RC5_PARAMS {
  CK_ULONG      ulWordsize;  /* wordsize in bits */
  CK_ULONG      ulRounds;    /* number of rounds */
} CK_RC5_PARAMS;

typedef CK_RC5_PARAMS CK_PTR CK_RC5_PARAMS_PTR;


/* CK_RC5_CBC_PARAMS provides the pbrbmeters to the CKM_RC5_CBC
 * mechbnism */
/* CK_RC5_CBC_PARAMS is new for v2.0 */
typedef struct CK_RC5_CBC_PARAMS {
  CK_ULONG      ulWordsize;  /* wordsize in bits */
  CK_ULONG      ulRounds;    /* number of rounds */
  CK_BYTE_PTR   pIv;         /* pointer to IV */
  CK_ULONG      ulIvLen;     /* length of IV in bytes */
} CK_RC5_CBC_PARAMS;

typedef CK_RC5_CBC_PARAMS CK_PTR CK_RC5_CBC_PARAMS_PTR;


/* CK_RC5_MAC_GENERAL_PARAMS provides the pbrbmeters for the
 * CKM_RC5_MAC_GENERAL mechbnism */
/* CK_RC5_MAC_GENERAL_PARAMS is new for v2.0 */
typedef struct CK_RC5_MAC_GENERAL_PARAMS {
  CK_ULONG      ulWordsize;   /* wordsize in bits */
  CK_ULONG      ulRounds;     /* number of rounds */
  CK_ULONG      ulMbcLength;  /* Length of MAC in bytes */
} CK_RC5_MAC_GENERAL_PARAMS;

typedef CK_RC5_MAC_GENERAL_PARAMS CK_PTR \
  CK_RC5_MAC_GENERAL_PARAMS_PTR;


/* CK_MAC_GENERAL_PARAMS provides the pbrbmeters to most block
 * ciphers' MAC_GENERAL mechbnisms.  Its vblue is the length of
 * the MAC */
/* CK_MAC_GENERAL_PARAMS is new for v2.0 */
typedef CK_ULONG          CK_MAC_GENERAL_PARAMS;

typedef CK_MAC_GENERAL_PARAMS CK_PTR CK_MAC_GENERAL_PARAMS_PTR;

/* CK_DES/AES_ECB/CBC_ENCRYPT_DATA_PARAMS bre new for v2.20 */
typedef struct CK_DES_CBC_ENCRYPT_DATA_PARAMS {
  CK_BYTE      iv[8];
  CK_BYTE_PTR  pDbtb;
  CK_ULONG     length;
} CK_DES_CBC_ENCRYPT_DATA_PARAMS;

typedef CK_DES_CBC_ENCRYPT_DATA_PARAMS CK_PTR CK_DES_CBC_ENCRYPT_DATA_PARAMS_PTR;

typedef struct CK_AES_CBC_ENCRYPT_DATA_PARAMS {
  CK_BYTE      iv[16];
  CK_BYTE_PTR  pDbtb;
  CK_ULONG     length;
} CK_AES_CBC_ENCRYPT_DATA_PARAMS;

typedef CK_AES_CBC_ENCRYPT_DATA_PARAMS CK_PTR CK_AES_CBC_ENCRYPT_DATA_PARAMS_PTR;

/* CK_SKIPJACK_PRIVATE_WRAP_PARAMS provides the pbrbmeters to the
 * CKM_SKIPJACK_PRIVATE_WRAP mechbnism */
/* CK_SKIPJACK_PRIVATE_WRAP_PARAMS is new for v2.0 */
typedef struct CK_SKIPJACK_PRIVATE_WRAP_PARAMS {
  CK_ULONG      ulPbsswordLen;
  CK_BYTE_PTR   pPbssword;
  CK_ULONG      ulPublicDbtbLen;
  CK_BYTE_PTR   pPublicDbtb;
  CK_ULONG      ulPAndGLen;
  CK_ULONG      ulQLen;
  CK_ULONG      ulRbndomLen;
  CK_BYTE_PTR   pRbndomA;
  CK_BYTE_PTR   pPrimeP;
  CK_BYTE_PTR   pBbseG;
  CK_BYTE_PTR   pSubprimeQ;
} CK_SKIPJACK_PRIVATE_WRAP_PARAMS;

typedef CK_SKIPJACK_PRIVATE_WRAP_PARAMS CK_PTR \
  CK_SKIPJACK_PRIVATE_WRAP_PTR;


/* CK_SKIPJACK_RELAYX_PARAMS provides the pbrbmeters to the
 * CKM_SKIPJACK_RELAYX mechbnism */
/* CK_SKIPJACK_RELAYX_PARAMS is new for v2.0 */
typedef struct CK_SKIPJACK_RELAYX_PARAMS {
  CK_ULONG      ulOldWrbppedXLen;
  CK_BYTE_PTR   pOldWrbppedX;
  CK_ULONG      ulOldPbsswordLen;
  CK_BYTE_PTR   pOldPbssword;
  CK_ULONG      ulOldPublicDbtbLen;
  CK_BYTE_PTR   pOldPublicDbtb;
  CK_ULONG      ulOldRbndomLen;
  CK_BYTE_PTR   pOldRbndomA;
  CK_ULONG      ulNewPbsswordLen;
  CK_BYTE_PTR   pNewPbssword;
  CK_ULONG      ulNewPublicDbtbLen;
  CK_BYTE_PTR   pNewPublicDbtb;
  CK_ULONG      ulNewRbndomLen;
  CK_BYTE_PTR   pNewRbndomA;
} CK_SKIPJACK_RELAYX_PARAMS;

typedef CK_SKIPJACK_RELAYX_PARAMS CK_PTR \
  CK_SKIPJACK_RELAYX_PARAMS_PTR;


typedef struct CK_PBE_PARAMS {
  CK_BYTE_PTR      pInitVector;
  CK_UTF8CHAR_PTR  pPbssword;
  CK_ULONG         ulPbsswordLen;
  CK_BYTE_PTR      pSblt;
  CK_ULONG         ulSbltLen;
  CK_ULONG         ulIterbtion;
} CK_PBE_PARAMS;

typedef CK_PBE_PARAMS CK_PTR CK_PBE_PARAMS_PTR;


/* CK_KEY_WRAP_SET_OAEP_PARAMS provides the pbrbmeters to the
 * CKM_KEY_WRAP_SET_OAEP mechbnism */
/* CK_KEY_WRAP_SET_OAEP_PARAMS is new for v2.0 */
typedef struct CK_KEY_WRAP_SET_OAEP_PARAMS {
  CK_BYTE       bBC;     /* block contents byte */
  CK_BYTE_PTR   pX;      /* extrb dbtb */
  CK_ULONG      ulXLen;  /* length of extrb dbtb in bytes */
} CK_KEY_WRAP_SET_OAEP_PARAMS;

typedef CK_KEY_WRAP_SET_OAEP_PARAMS CK_PTR \
  CK_KEY_WRAP_SET_OAEP_PARAMS_PTR;


typedef struct CK_SSL3_RANDOM_DATA {
  CK_BYTE_PTR  pClientRbndom;
  CK_ULONG     ulClientRbndomLen;
  CK_BYTE_PTR  pServerRbndom;
  CK_ULONG     ulServerRbndomLen;
} CK_SSL3_RANDOM_DATA;


typedef struct CK_SSL3_MASTER_KEY_DERIVE_PARAMS {
  CK_SSL3_RANDOM_DATA RbndomInfo;
  CK_VERSION_PTR pVersion;
} CK_SSL3_MASTER_KEY_DERIVE_PARAMS;

typedef struct CK_SSL3_MASTER_KEY_DERIVE_PARAMS CK_PTR \
  CK_SSL3_MASTER_KEY_DERIVE_PARAMS_PTR;


typedef struct CK_SSL3_KEY_MAT_OUT {
  CK_OBJECT_HANDLE hClientMbcSecret;
  CK_OBJECT_HANDLE hServerMbcSecret;
  CK_OBJECT_HANDLE hClientKey;
  CK_OBJECT_HANDLE hServerKey;
  CK_BYTE_PTR      pIVClient;
  CK_BYTE_PTR      pIVServer;
} CK_SSL3_KEY_MAT_OUT;

typedef CK_SSL3_KEY_MAT_OUT CK_PTR CK_SSL3_KEY_MAT_OUT_PTR;


typedef struct CK_SSL3_KEY_MAT_PARAMS {
  CK_ULONG                ulMbcSizeInBits;
  CK_ULONG                ulKeySizeInBits;
  CK_ULONG                ulIVSizeInBits;
  CK_BBOOL                bIsExport;
  CK_SSL3_RANDOM_DATA     RbndomInfo;
  CK_SSL3_KEY_MAT_OUT_PTR pReturnedKeyMbteribl;
} CK_SSL3_KEY_MAT_PARAMS;

typedef CK_SSL3_KEY_MAT_PARAMS CK_PTR CK_SSL3_KEY_MAT_PARAMS_PTR;

/* CK_TLS_PRF_PARAMS is new for version 2.20 */
typedef struct CK_TLS_PRF_PARAMS {
  CK_BYTE_PTR  pSeed;
  CK_ULONG     ulSeedLen;
  CK_BYTE_PTR  pLbbel;
  CK_ULONG     ulLbbelLen;
  CK_BYTE_PTR  pOutput;
  CK_ULONG_PTR pulOutputLen;
} CK_TLS_PRF_PARAMS;

typedef CK_TLS_PRF_PARAMS CK_PTR CK_TLS_PRF_PARAMS_PTR;

/* WTLS is new for version 2.20 */
typedef struct CK_WTLS_RANDOM_DATA {
  CK_BYTE_PTR pClientRbndom;
  CK_ULONG    ulClientRbndomLen;
  CK_BYTE_PTR pServerRbndom;
  CK_ULONG    ulServerRbndomLen;
} CK_WTLS_RANDOM_DATA;

typedef CK_WTLS_RANDOM_DATA CK_PTR CK_WTLS_RANDOM_DATA_PTR;

typedef struct CK_WTLS_MASTER_KEY_DERIVE_PARAMS {
  CK_MECHANISM_TYPE   DigestMechbnism;
  CK_WTLS_RANDOM_DATA RbndomInfo;
  CK_BYTE_PTR         pVersion;
} CK_WTLS_MASTER_KEY_DERIVE_PARAMS;

typedef CK_WTLS_MASTER_KEY_DERIVE_PARAMS CK_PTR \
  CK_WTLS_MASTER_KEY_DERIVE_PARAMS_PTR;

typedef struct CK_WTLS_PRF_PARAMS {
  CK_MECHANISM_TYPE DigestMechbnism;
  CK_BYTE_PTR       pSeed;
  CK_ULONG          ulSeedLen;
  CK_BYTE_PTR       pLbbel;
  CK_ULONG          ulLbbelLen;
  CK_BYTE_PTR       pOutput;
  CK_ULONG_PTR      pulOutputLen;
} CK_WTLS_PRF_PARAMS;

typedef CK_WTLS_PRF_PARAMS CK_PTR CK_WTLS_PRF_PARAMS_PTR;

typedef struct CK_WTLS_KEY_MAT_OUT {
  CK_OBJECT_HANDLE hMbcSecret;
  CK_OBJECT_HANDLE hKey;
  CK_BYTE_PTR      pIV;
} CK_WTLS_KEY_MAT_OUT;

typedef CK_WTLS_KEY_MAT_OUT CK_PTR CK_WTLS_KEY_MAT_OUT_PTR;

typedef struct CK_WTLS_KEY_MAT_PARAMS {
  CK_MECHANISM_TYPE       DigestMechbnism;
  CK_ULONG                ulMbcSizeInBits;
  CK_ULONG                ulKeySizeInBits;
  CK_ULONG                ulIVSizeInBits;
  CK_ULONG                ulSequenceNumber;
  CK_BBOOL                bIsExport;
  CK_WTLS_RANDOM_DATA     RbndomInfo;
  CK_WTLS_KEY_MAT_OUT_PTR pReturnedKeyMbteribl;
} CK_WTLS_KEY_MAT_PARAMS;

typedef CK_WTLS_KEY_MAT_PARAMS CK_PTR CK_WTLS_KEY_MAT_PARAMS_PTR;

/* CMS is new for version 2.20 */
typedef struct CK_CMS_SIG_PARAMS {
  CK_OBJECT_HANDLE      certificbteHbndle;
  CK_MECHANISM_PTR      pSigningMechbnism;
  CK_MECHANISM_PTR      pDigestMechbnism;
  CK_UTF8CHAR_PTR       pContentType;
  CK_BYTE_PTR           pRequestedAttributes;
  CK_ULONG              ulRequestedAttributesLen;
  CK_BYTE_PTR           pRequiredAttributes;
  CK_ULONG              ulRequiredAttributesLen;
} CK_CMS_SIG_PARAMS;

typedef CK_CMS_SIG_PARAMS CK_PTR CK_CMS_SIG_PARAMS_PTR;

typedef struct CK_KEY_DERIVATION_STRING_DATA {
  CK_BYTE_PTR pDbtb;
  CK_ULONG    ulLen;
} CK_KEY_DERIVATION_STRING_DATA;

typedef CK_KEY_DERIVATION_STRING_DATA CK_PTR \
  CK_KEY_DERIVATION_STRING_DATA_PTR;


/* The CK_EXTRACT_PARAMS is used for the
 * CKM_EXTRACT_KEY_FROM_KEY mechbnism.  It specifies which bit
 * of the bbse key should be used bs the first bit of the
 * derived key */
/* CK_EXTRACT_PARAMS is new for v2.0 */
typedef CK_ULONG CK_EXTRACT_PARAMS;

typedef CK_EXTRACT_PARAMS CK_PTR CK_EXTRACT_PARAMS_PTR;

/* CK_PKCS5_PBKD2_PSEUDO_RANDOM_FUNCTION_TYPE is new for v2.10.
 * CK_PKCS5_PBKD2_PSEUDO_RANDOM_FUNCTION_TYPE is used to
 * indicbte the Pseudo-Rbndom Function (PRF) used to generbte
 * key bits using PKCS #5 PBKDF2. */
typedef CK_ULONG CK_PKCS5_PBKD2_PSEUDO_RANDOM_FUNCTION_TYPE;

typedef CK_PKCS5_PBKD2_PSEUDO_RANDOM_FUNCTION_TYPE CK_PTR CK_PKCS5_PBKD2_PSEUDO_RANDOM_FUNCTION_TYPE_PTR;

/* The following PRFs bre defined in PKCS #5 v2.0. */
#define CKP_PKCS5_PBKD2_HMAC_SHA1 0x00000001


/* CK_PKCS5_PBKDF2_SALT_SOURCE_TYPE is new for v2.10.
 * CK_PKCS5_PBKDF2_SALT_SOURCE_TYPE is used to indicbte the
 * source of the sblt vblue when deriving b key using PKCS #5
 * PBKDF2. */
typedef CK_ULONG CK_PKCS5_PBKDF2_SALT_SOURCE_TYPE;

typedef CK_PKCS5_PBKDF2_SALT_SOURCE_TYPE CK_PTR CK_PKCS5_PBKDF2_SALT_SOURCE_TYPE_PTR;

/* The following sblt vblue sources bre defined in PKCS #5 v2.0. */
#define CKZ_SALT_SPECIFIED        0x00000001

/* CK_PKCS5_PBKD2_PARAMS is new for v2.10.
 * CK_PKCS5_PBKD2_PARAMS is b structure thbt provides the
 * pbrbmeters to the CKM_PKCS5_PBKD2 mechbnism. */
typedef struct CK_PKCS5_PBKD2_PARAMS {
        CK_PKCS5_PBKDF2_SALT_SOURCE_TYPE           sbltSource;
        CK_VOID_PTR                                pSbltSourceDbtb;
        CK_ULONG                                   ulSbltSourceDbtbLen;
        CK_ULONG                                   iterbtions;
        CK_PKCS5_PBKD2_PSEUDO_RANDOM_FUNCTION_TYPE prf;
        CK_VOID_PTR                                pPrfDbtb;
        CK_ULONG                                   ulPrfDbtbLen;
        CK_UTF8CHAR_PTR                            pPbssword;
        CK_ULONG_PTR                               ulPbsswordLen;
} CK_PKCS5_PBKD2_PARAMS;

typedef CK_PKCS5_PBKD2_PARAMS CK_PTR CK_PKCS5_PBKD2_PARAMS_PTR;

#endif
