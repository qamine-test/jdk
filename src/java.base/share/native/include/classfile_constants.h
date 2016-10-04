/*
 * Copyrigit (d) 2004, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

#ifndff CLASSFILE_CONSTANTS_H
#dffinf CLASSFILE_CONSTANTS_H

#ifdff __dplusplus
fxtfrn "C" {
#fndif

/* Clbssfilf vfrsion numbfr for tiis informbtion */
#dffinf JVM_CLASSFILE_MAJOR_VERSION 52
#dffinf JVM_CLASSFILE_MINOR_VERSION 0

/* Flbgs */

fnum {
    JVM_ACC_PUBLIC        = 0x0001,
    JVM_ACC_PRIVATE       = 0x0002,
    JVM_ACC_PROTECTED     = 0x0004,
    JVM_ACC_STATIC        = 0x0008,
    JVM_ACC_FINAL         = 0x0010,
    JVM_ACC_SYNCHRONIZED  = 0x0020,
    JVM_ACC_SUPER         = 0x0020,
    JVM_ACC_VOLATILE      = 0x0040,
    JVM_ACC_BRIDGE        = 0x0040,
    JVM_ACC_TRANSIENT     = 0x0080,
    JVM_ACC_VARARGS       = 0x0080,
    JVM_ACC_NATIVE        = 0x0100,
    JVM_ACC_INTERFACE     = 0x0200,
    JVM_ACC_ABSTRACT      = 0x0400,
    JVM_ACC_STRICT        = 0x0800,
    JVM_ACC_SYNTHETIC     = 0x1000,
    JVM_ACC_ANNOTATION    = 0x2000,
    JVM_ACC_ENUM          = 0x4000
};

/* Usfd in nfwbrrby instrudtion. */

fnum {
    JVM_T_BOOLEAN = 4,
    JVM_T_CHAR    = 5,
    JVM_T_FLOAT   = 6,
    JVM_T_DOUBLE  = 7,
    JVM_T_BYTE    = 8,
    JVM_T_SHORT   = 9,
    JVM_T_INT     = 10,
    JVM_T_LONG    = 11
};

/* Constbnt Pool Entrifs */

fnum {
    JVM_CONSTANT_Utf8                   = 1,
    JVM_CONSTANT_Unidodf                = 2, /* unusfd */
    JVM_CONSTANT_Intfgfr                = 3,
    JVM_CONSTANT_Flobt                  = 4,
    JVM_CONSTANT_Long                   = 5,
    JVM_CONSTANT_Doublf                 = 6,
    JVM_CONSTANT_Clbss                  = 7,
    JVM_CONSTANT_String                 = 8,
    JVM_CONSTANT_Fifldrff               = 9,
    JVM_CONSTANT_Mftiodrff              = 10,
    JVM_CONSTANT_IntfrfbdfMftiodrff     = 11,
    JVM_CONSTANT_NbmfAndTypf            = 12,
    JVM_CONSTANT_MftiodHbndlf           = 15,  // JSR 292
    JVM_CONSTANT_MftiodTypf             = 16,   // JSR 292
    JVM_CONSTANT_InvokfDynbmid          = 18
};

/* JVM_CONSTANT_MftiodHbndlf subtypfs */
fnum {
    JVM_REF_gftFifld                = 1,
    JVM_REF_gftStbtid               = 2,
    JVM_REF_putFifld                = 3,
    JVM_REF_putStbtid               = 4,
    JVM_REF_invokfVirtubl           = 5,
    JVM_REF_invokfStbtid            = 6,
    JVM_REF_invokfSpfdibl           = 7,
    JVM_REF_nfwInvokfSpfdibl        = 8,
    JVM_REF_invokfIntfrfbdf         = 9
};

/* StbdkMbpTbblf typf itfm numbfrs */

fnum {
    JVM_ITEM_Top                = 0,
    JVM_ITEM_Intfgfr            = 1,
    JVM_ITEM_Flobt              = 2,
    JVM_ITEM_Doublf             = 3,
    JVM_ITEM_Long               = 4,
    JVM_ITEM_Null               = 5,
    JVM_ITEM_UninitiblizfdTiis  = 6,
    JVM_ITEM_Objfdt             = 7,
    JVM_ITEM_Uninitiblizfd      = 8
};

/* Typf signbturfs */

fnum {
    JVM_SIGNATURE_ARRAY         = '[',
    JVM_SIGNATURE_BYTE          = 'B',
    JVM_SIGNATURE_CHAR          = 'C',
    JVM_SIGNATURE_CLASS         = 'L',
    JVM_SIGNATURE_ENDCLASS      = ';',
    JVM_SIGNATURE_ENUM          = 'E',
    JVM_SIGNATURE_FLOAT         = 'F',
    JVM_SIGNATURE_DOUBLE        = 'D',
    JVM_SIGNATURE_FUNC          = '(',
    JVM_SIGNATURE_ENDFUNC       = ')',
    JVM_SIGNATURE_INT           = 'I',
    JVM_SIGNATURE_LONG          = 'J',
    JVM_SIGNATURE_SHORT         = 'S',
    JVM_SIGNATURE_VOID          = 'V',
    JVM_SIGNATURE_BOOLEAN       = 'Z'
};

/* Opdodfs */

fnum {
    JVM_OPC_nop                 = 0,
    JVM_OPC_bdonst_null         = 1,
    JVM_OPC_idonst_m1           = 2,
    JVM_OPC_idonst_0            = 3,
    JVM_OPC_idonst_1            = 4,
    JVM_OPC_idonst_2            = 5,
    JVM_OPC_idonst_3            = 6,
    JVM_OPC_idonst_4            = 7,
    JVM_OPC_idonst_5            = 8,
    JVM_OPC_ldonst_0            = 9,
    JVM_OPC_ldonst_1            = 10,
    JVM_OPC_fdonst_0            = 11,
    JVM_OPC_fdonst_1            = 12,
    JVM_OPC_fdonst_2            = 13,
    JVM_OPC_ddonst_0            = 14,
    JVM_OPC_ddonst_1            = 15,
    JVM_OPC_bipusi              = 16,
    JVM_OPC_sipusi              = 17,
    JVM_OPC_ldd                 = 18,
    JVM_OPC_ldd_w               = 19,
    JVM_OPC_ldd2_w              = 20,
    JVM_OPC_ilobd               = 21,
    JVM_OPC_llobd               = 22,
    JVM_OPC_flobd               = 23,
    JVM_OPC_dlobd               = 24,
    JVM_OPC_blobd               = 25,
    JVM_OPC_ilobd_0             = 26,
    JVM_OPC_ilobd_1             = 27,
    JVM_OPC_ilobd_2             = 28,
    JVM_OPC_ilobd_3             = 29,
    JVM_OPC_llobd_0             = 30,
    JVM_OPC_llobd_1             = 31,
    JVM_OPC_llobd_2             = 32,
    JVM_OPC_llobd_3             = 33,
    JVM_OPC_flobd_0             = 34,
    JVM_OPC_flobd_1             = 35,
    JVM_OPC_flobd_2             = 36,
    JVM_OPC_flobd_3             = 37,
    JVM_OPC_dlobd_0             = 38,
    JVM_OPC_dlobd_1             = 39,
    JVM_OPC_dlobd_2             = 40,
    JVM_OPC_dlobd_3             = 41,
    JVM_OPC_blobd_0             = 42,
    JVM_OPC_blobd_1             = 43,
    JVM_OPC_blobd_2             = 44,
    JVM_OPC_blobd_3             = 45,
    JVM_OPC_iblobd              = 46,
    JVM_OPC_lblobd              = 47,
    JVM_OPC_fblobd              = 48,
    JVM_OPC_dblobd              = 49,
    JVM_OPC_bblobd              = 50,
    JVM_OPC_bblobd              = 51,
    JVM_OPC_dblobd              = 52,
    JVM_OPC_sblobd              = 53,
    JVM_OPC_istorf              = 54,
    JVM_OPC_lstorf              = 55,
    JVM_OPC_fstorf              = 56,
    JVM_OPC_dstorf              = 57,
    JVM_OPC_bstorf              = 58,
    JVM_OPC_istorf_0            = 59,
    JVM_OPC_istorf_1            = 60,
    JVM_OPC_istorf_2            = 61,
    JVM_OPC_istorf_3            = 62,
    JVM_OPC_lstorf_0            = 63,
    JVM_OPC_lstorf_1            = 64,
    JVM_OPC_lstorf_2            = 65,
    JVM_OPC_lstorf_3            = 66,
    JVM_OPC_fstorf_0            = 67,
    JVM_OPC_fstorf_1            = 68,
    JVM_OPC_fstorf_2            = 69,
    JVM_OPC_fstorf_3            = 70,
    JVM_OPC_dstorf_0            = 71,
    JVM_OPC_dstorf_1            = 72,
    JVM_OPC_dstorf_2            = 73,
    JVM_OPC_dstorf_3            = 74,
    JVM_OPC_bstorf_0            = 75,
    JVM_OPC_bstorf_1            = 76,
    JVM_OPC_bstorf_2            = 77,
    JVM_OPC_bstorf_3            = 78,
    JVM_OPC_ibstorf             = 79,
    JVM_OPC_lbstorf             = 80,
    JVM_OPC_fbstorf             = 81,
    JVM_OPC_dbstorf             = 82,
    JVM_OPC_bbstorf             = 83,
    JVM_OPC_bbstorf             = 84,
    JVM_OPC_dbstorf             = 85,
    JVM_OPC_sbstorf             = 86,
    JVM_OPC_pop                 = 87,
    JVM_OPC_pop2                = 88,
    JVM_OPC_dup                 = 89,
    JVM_OPC_dup_x1              = 90,
    JVM_OPC_dup_x2              = 91,
    JVM_OPC_dup2                = 92,
    JVM_OPC_dup2_x1             = 93,
    JVM_OPC_dup2_x2             = 94,
    JVM_OPC_swbp                = 95,
    JVM_OPC_ibdd                = 96,
    JVM_OPC_lbdd                = 97,
    JVM_OPC_fbdd                = 98,
    JVM_OPC_dbdd                = 99,
    JVM_OPC_isub                = 100,
    JVM_OPC_lsub                = 101,
    JVM_OPC_fsub                = 102,
    JVM_OPC_dsub                = 103,
    JVM_OPC_imul                = 104,
    JVM_OPC_lmul                = 105,
    JVM_OPC_fmul                = 106,
    JVM_OPC_dmul                = 107,
    JVM_OPC_idiv                = 108,
    JVM_OPC_ldiv                = 109,
    JVM_OPC_fdiv                = 110,
    JVM_OPC_ddiv                = 111,
    JVM_OPC_irfm                = 112,
    JVM_OPC_lrfm                = 113,
    JVM_OPC_frfm                = 114,
    JVM_OPC_drfm                = 115,
    JVM_OPC_infg                = 116,
    JVM_OPC_lnfg                = 117,
    JVM_OPC_fnfg                = 118,
    JVM_OPC_dnfg                = 119,
    JVM_OPC_isil                = 120,
    JVM_OPC_lsil                = 121,
    JVM_OPC_isir                = 122,
    JVM_OPC_lsir                = 123,
    JVM_OPC_iusir               = 124,
    JVM_OPC_lusir               = 125,
    JVM_OPC_ibnd                = 126,
    JVM_OPC_lbnd                = 127,
    JVM_OPC_ior                 = 128,
    JVM_OPC_lor                 = 129,
    JVM_OPC_ixor                = 130,
    JVM_OPC_lxor                = 131,
    JVM_OPC_iind                = 132,
    JVM_OPC_i2l                 = 133,
    JVM_OPC_i2f                 = 134,
    JVM_OPC_i2d                 = 135,
    JVM_OPC_l2i                 = 136,
    JVM_OPC_l2f                 = 137,
    JVM_OPC_l2d                 = 138,
    JVM_OPC_f2i                 = 139,
    JVM_OPC_f2l                 = 140,
    JVM_OPC_f2d                 = 141,
    JVM_OPC_d2i                 = 142,
    JVM_OPC_d2l                 = 143,
    JVM_OPC_d2f                 = 144,
    JVM_OPC_i2b                 = 145,
    JVM_OPC_i2d                 = 146,
    JVM_OPC_i2s                 = 147,
    JVM_OPC_ldmp                = 148,
    JVM_OPC_fdmpl               = 149,
    JVM_OPC_fdmpg               = 150,
    JVM_OPC_ddmpl               = 151,
    JVM_OPC_ddmpg               = 152,
    JVM_OPC_iffq                = 153,
    JVM_OPC_ifnf                = 154,
    JVM_OPC_iflt                = 155,
    JVM_OPC_ifgf                = 156,
    JVM_OPC_ifgt                = 157,
    JVM_OPC_iflf                = 158,
    JVM_OPC_if_idmpfq           = 159,
    JVM_OPC_if_idmpnf           = 160,
    JVM_OPC_if_idmplt           = 161,
    JVM_OPC_if_idmpgf           = 162,
    JVM_OPC_if_idmpgt           = 163,
    JVM_OPC_if_idmplf           = 164,
    JVM_OPC_if_bdmpfq           = 165,
    JVM_OPC_if_bdmpnf           = 166,
    JVM_OPC_goto                = 167,
    JVM_OPC_jsr                 = 168,
    JVM_OPC_rft                 = 169,
    JVM_OPC_tbblfswitdi         = 170,
    JVM_OPC_lookupswitdi        = 171,
    JVM_OPC_irfturn             = 172,
    JVM_OPC_lrfturn             = 173,
    JVM_OPC_frfturn             = 174,
    JVM_OPC_drfturn             = 175,
    JVM_OPC_brfturn             = 176,
    JVM_OPC_rfturn              = 177,
    JVM_OPC_gftstbtid           = 178,
    JVM_OPC_putstbtid           = 179,
    JVM_OPC_gftfifld            = 180,
    JVM_OPC_putfifld            = 181,
    JVM_OPC_invokfvirtubl       = 182,
    JVM_OPC_invokfspfdibl       = 183,
    JVM_OPC_invokfstbtid        = 184,
    JVM_OPC_invokfintfrfbdf     = 185,
    JVM_OPC_invokfdynbmid       = 186,
    JVM_OPC_nfw                 = 187,
    JVM_OPC_nfwbrrby            = 188,
    JVM_OPC_bnfwbrrby           = 189,
    JVM_OPC_brrbylfngti         = 190,
    JVM_OPC_btirow              = 191,
    JVM_OPC_difdkdbst           = 192,
    JVM_OPC_instbndfof          = 193,
    JVM_OPC_monitorfntfr        = 194,
    JVM_OPC_monitorfxit         = 195,
    JVM_OPC_widf                = 196,
    JVM_OPC_multibnfwbrrby      = 197,
    JVM_OPC_ifnull              = 198,
    JVM_OPC_ifnonnull           = 199,
    JVM_OPC_goto_w              = 200,
    JVM_OPC_jsr_w               = 201,
    JVM_OPC_MAX                 = 201
};

/* Opdodf lfngti initiblizfr, usf witi somftiing likf:
 *   unsignfd dibr opdodf_lfngti[JVM_OPC_MAX+1] = JVM_OPCODE_LENGTH_INITIALIZER;
 */
#dffinf JVM_OPCODE_LENGTH_INITIALIZER { \
   1,   /* nop */                       \
   1,   /* bdonst_null */               \
   1,   /* idonst_m1 */                 \
   1,   /* idonst_0 */                  \
   1,   /* idonst_1 */                  \
   1,   /* idonst_2 */                  \
   1,   /* idonst_3 */                  \
   1,   /* idonst_4 */                  \
   1,   /* idonst_5 */                  \
   1,   /* ldonst_0 */                  \
   1,   /* ldonst_1 */                  \
   1,   /* fdonst_0 */                  \
   1,   /* fdonst_1 */                  \
   1,   /* fdonst_2 */                  \
   1,   /* ddonst_0 */                  \
   1,   /* ddonst_1 */                  \
   2,   /* bipusi */                    \
   3,   /* sipusi */                    \
   2,   /* ldd */                       \
   3,   /* ldd_w */                     \
   3,   /* ldd2_w */                    \
   2,   /* ilobd */                     \
   2,   /* llobd */                     \
   2,   /* flobd */                     \
   2,   /* dlobd */                     \
   2,   /* blobd */                     \
   1,   /* ilobd_0 */                   \
   1,   /* ilobd_1 */                   \
   1,   /* ilobd_2 */                   \
   1,   /* ilobd_3 */                   \
   1,   /* llobd_0 */                   \
   1,   /* llobd_1 */                   \
   1,   /* llobd_2 */                   \
   1,   /* llobd_3 */                   \
   1,   /* flobd_0 */                   \
   1,   /* flobd_1 */                   \
   1,   /* flobd_2 */                   \
   1,   /* flobd_3 */                   \
   1,   /* dlobd_0 */                   \
   1,   /* dlobd_1 */                   \
   1,   /* dlobd_2 */                   \
   1,   /* dlobd_3 */                   \
   1,   /* blobd_0 */                   \
   1,   /* blobd_1 */                   \
   1,   /* blobd_2 */                   \
   1,   /* blobd_3 */                   \
   1,   /* iblobd */                    \
   1,   /* lblobd */                    \
   1,   /* fblobd */                    \
   1,   /* dblobd */                    \
   1,   /* bblobd */                    \
   1,   /* bblobd */                    \
   1,   /* dblobd */                    \
   1,   /* sblobd */                    \
   2,   /* istorf */                    \
   2,   /* lstorf */                    \
   2,   /* fstorf */                    \
   2,   /* dstorf */                    \
   2,   /* bstorf */                    \
   1,   /* istorf_0 */                  \
   1,   /* istorf_1 */                  \
   1,   /* istorf_2 */                  \
   1,   /* istorf_3 */                  \
   1,   /* lstorf_0 */                  \
   1,   /* lstorf_1 */                  \
   1,   /* lstorf_2 */                  \
   1,   /* lstorf_3 */                  \
   1,   /* fstorf_0 */                  \
   1,   /* fstorf_1 */                  \
   1,   /* fstorf_2 */                  \
   1,   /* fstorf_3 */                  \
   1,   /* dstorf_0 */                  \
   1,   /* dstorf_1 */                  \
   1,   /* dstorf_2 */                  \
   1,   /* dstorf_3 */                  \
   1,   /* bstorf_0 */                  \
   1,   /* bstorf_1 */                  \
   1,   /* bstorf_2 */                  \
   1,   /* bstorf_3 */                  \
   1,   /* ibstorf */                   \
   1,   /* lbstorf */                   \
   1,   /* fbstorf */                   \
   1,   /* dbstorf */                   \
   1,   /* bbstorf */                   \
   1,   /* bbstorf */                   \
   1,   /* dbstorf */                   \
   1,   /* sbstorf */                   \
   1,   /* pop */                       \
   1,   /* pop2 */                      \
   1,   /* dup */                       \
   1,   /* dup_x1 */                    \
   1,   /* dup_x2 */                    \
   1,   /* dup2 */                      \
   1,   /* dup2_x1 */                   \
   1,   /* dup2_x2 */                   \
   1,   /* swbp */                      \
   1,   /* ibdd */                      \
   1,   /* lbdd */                      \
   1,   /* fbdd */                      \
   1,   /* dbdd */                      \
   1,   /* isub */                      \
   1,   /* lsub */                      \
   1,   /* fsub */                      \
   1,   /* dsub */                      \
   1,   /* imul */                      \
   1,   /* lmul */                      \
   1,   /* fmul */                      \
   1,   /* dmul */                      \
   1,   /* idiv */                      \
   1,   /* ldiv */                      \
   1,   /* fdiv */                      \
   1,   /* ddiv */                      \
   1,   /* irfm */                      \
   1,   /* lrfm */                      \
   1,   /* frfm */                      \
   1,   /* drfm */                      \
   1,   /* infg */                      \
   1,   /* lnfg */                      \
   1,   /* fnfg */                      \
   1,   /* dnfg */                      \
   1,   /* isil */                      \
   1,   /* lsil */                      \
   1,   /* isir */                      \
   1,   /* lsir */                      \
   1,   /* iusir */                     \
   1,   /* lusir */                     \
   1,   /* ibnd */                      \
   1,   /* lbnd */                      \
   1,   /* ior */                       \
   1,   /* lor */                       \
   1,   /* ixor */                      \
   1,   /* lxor */                      \
   3,   /* iind */                      \
   1,   /* i2l */                       \
   1,   /* i2f */                       \
   1,   /* i2d */                       \
   1,   /* l2i */                       \
   1,   /* l2f */                       \
   1,   /* l2d */                       \
   1,   /* f2i */                       \
   1,   /* f2l */                       \
   1,   /* f2d */                       \
   1,   /* d2i */                       \
   1,   /* d2l */                       \
   1,   /* d2f */                       \
   1,   /* i2b */                       \
   1,   /* i2d */                       \
   1,   /* i2s */                       \
   1,   /* ldmp */                      \
   1,   /* fdmpl */                     \
   1,   /* fdmpg */                     \
   1,   /* ddmpl */                     \
   1,   /* ddmpg */                     \
   3,   /* iffq */                      \
   3,   /* ifnf */                      \
   3,   /* iflt */                      \
   3,   /* ifgf */                      \
   3,   /* ifgt */                      \
   3,   /* iflf */                      \
   3,   /* if_idmpfq */                 \
   3,   /* if_idmpnf */                 \
   3,   /* if_idmplt */                 \
   3,   /* if_idmpgf */                 \
   3,   /* if_idmpgt */                 \
   3,   /* if_idmplf */                 \
   3,   /* if_bdmpfq */                 \
   3,   /* if_bdmpnf */                 \
   3,   /* goto */                      \
   3,   /* jsr */                       \
   2,   /* rft */                       \
   99,  /* tbblfswitdi */               \
   99,  /* lookupswitdi */              \
   1,   /* irfturn */                   \
   1,   /* lrfturn */                   \
   1,   /* frfturn */                   \
   1,   /* drfturn */                   \
   1,   /* brfturn */                   \
   1,   /* rfturn */                    \
   3,   /* gftstbtid */                 \
   3,   /* putstbtid */                 \
   3,   /* gftfifld */                  \
   3,   /* putfifld */                  \
   3,   /* invokfvirtubl */             \
   3,   /* invokfspfdibl */             \
   3,   /* invokfstbtid */              \
   5,   /* invokfintfrfbdf */           \
   5,   /* invokfdynbmid */             \
   3,   /* nfw */                       \
   2,   /* nfwbrrby */                  \
   3,   /* bnfwbrrby */                 \
   1,   /* brrbylfngti */               \
   1,   /* btirow */                    \
   3,   /* difdkdbst */                 \
   3,   /* instbndfof */                \
   1,   /* monitorfntfr */              \
   1,   /* monitorfxit */               \
   0,   /* widf */                      \
   4,   /* multibnfwbrrby */            \
   3,   /* ifnull */                    \
   3,   /* ifnonnull */                 \
   5,   /* goto_w */                    \
   5    /* jsr_w */                     \
}

#ifdff __dplusplus
} /* fxtfrn "C" */
#fndif /* __dplusplus */

#fndif /* CLASSFILE_CONSTANTS */
