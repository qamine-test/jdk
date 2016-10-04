/*
 * Copyright (c) 2004, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

#ifndef CLASSFILE_CONSTANTS_H
#define CLASSFILE_CONSTANTS_H

#ifdef __cplusplus
extern "C" {
#endif

/* Clbssfile version number for this informbtion */
#define JVM_CLASSFILE_MAJOR_VERSION 52
#define JVM_CLASSFILE_MINOR_VERSION 0

/* Flbgs */

enum {
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

/* Used in newbrrby instruction. */

enum {
    JVM_T_BOOLEAN = 4,
    JVM_T_CHAR    = 5,
    JVM_T_FLOAT   = 6,
    JVM_T_DOUBLE  = 7,
    JVM_T_BYTE    = 8,
    JVM_T_SHORT   = 9,
    JVM_T_INT     = 10,
    JVM_T_LONG    = 11
};

/* Constbnt Pool Entries */

enum {
    JVM_CONSTANT_Utf8                   = 1,
    JVM_CONSTANT_Unicode                = 2, /* unused */
    JVM_CONSTANT_Integer                = 3,
    JVM_CONSTANT_Flobt                  = 4,
    JVM_CONSTANT_Long                   = 5,
    JVM_CONSTANT_Double                 = 6,
    JVM_CONSTANT_Clbss                  = 7,
    JVM_CONSTANT_String                 = 8,
    JVM_CONSTANT_Fieldref               = 9,
    JVM_CONSTANT_Methodref              = 10,
    JVM_CONSTANT_InterfbceMethodref     = 11,
    JVM_CONSTANT_NbmeAndType            = 12,
    JVM_CONSTANT_MethodHbndle           = 15,  // JSR 292
    JVM_CONSTANT_MethodType             = 16,   // JSR 292
    JVM_CONSTANT_InvokeDynbmic          = 18
};

/* JVM_CONSTANT_MethodHbndle subtypes */
enum {
    JVM_REF_getField                = 1,
    JVM_REF_getStbtic               = 2,
    JVM_REF_putField                = 3,
    JVM_REF_putStbtic               = 4,
    JVM_REF_invokeVirtubl           = 5,
    JVM_REF_invokeStbtic            = 6,
    JVM_REF_invokeSpecibl           = 7,
    JVM_REF_newInvokeSpecibl        = 8,
    JVM_REF_invokeInterfbce         = 9
};

/* StbckMbpTbble type item numbers */

enum {
    JVM_ITEM_Top                = 0,
    JVM_ITEM_Integer            = 1,
    JVM_ITEM_Flobt              = 2,
    JVM_ITEM_Double             = 3,
    JVM_ITEM_Long               = 4,
    JVM_ITEM_Null               = 5,
    JVM_ITEM_UninitiblizedThis  = 6,
    JVM_ITEM_Object             = 7,
    JVM_ITEM_Uninitiblized      = 8
};

/* Type signbtures */

enum {
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

/* Opcodes */

enum {
    JVM_OPC_nop                 = 0,
    JVM_OPC_bconst_null         = 1,
    JVM_OPC_iconst_m1           = 2,
    JVM_OPC_iconst_0            = 3,
    JVM_OPC_iconst_1            = 4,
    JVM_OPC_iconst_2            = 5,
    JVM_OPC_iconst_3            = 6,
    JVM_OPC_iconst_4            = 7,
    JVM_OPC_iconst_5            = 8,
    JVM_OPC_lconst_0            = 9,
    JVM_OPC_lconst_1            = 10,
    JVM_OPC_fconst_0            = 11,
    JVM_OPC_fconst_1            = 12,
    JVM_OPC_fconst_2            = 13,
    JVM_OPC_dconst_0            = 14,
    JVM_OPC_dconst_1            = 15,
    JVM_OPC_bipush              = 16,
    JVM_OPC_sipush              = 17,
    JVM_OPC_ldc                 = 18,
    JVM_OPC_ldc_w               = 19,
    JVM_OPC_ldc2_w              = 20,
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
    JVM_OPC_cblobd              = 52,
    JVM_OPC_sblobd              = 53,
    JVM_OPC_istore              = 54,
    JVM_OPC_lstore              = 55,
    JVM_OPC_fstore              = 56,
    JVM_OPC_dstore              = 57,
    JVM_OPC_bstore              = 58,
    JVM_OPC_istore_0            = 59,
    JVM_OPC_istore_1            = 60,
    JVM_OPC_istore_2            = 61,
    JVM_OPC_istore_3            = 62,
    JVM_OPC_lstore_0            = 63,
    JVM_OPC_lstore_1            = 64,
    JVM_OPC_lstore_2            = 65,
    JVM_OPC_lstore_3            = 66,
    JVM_OPC_fstore_0            = 67,
    JVM_OPC_fstore_1            = 68,
    JVM_OPC_fstore_2            = 69,
    JVM_OPC_fstore_3            = 70,
    JVM_OPC_dstore_0            = 71,
    JVM_OPC_dstore_1            = 72,
    JVM_OPC_dstore_2            = 73,
    JVM_OPC_dstore_3            = 74,
    JVM_OPC_bstore_0            = 75,
    JVM_OPC_bstore_1            = 76,
    JVM_OPC_bstore_2            = 77,
    JVM_OPC_bstore_3            = 78,
    JVM_OPC_ibstore             = 79,
    JVM_OPC_lbstore             = 80,
    JVM_OPC_fbstore             = 81,
    JVM_OPC_dbstore             = 82,
    JVM_OPC_bbstore             = 83,
    JVM_OPC_bbstore             = 84,
    JVM_OPC_cbstore             = 85,
    JVM_OPC_sbstore             = 86,
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
    JVM_OPC_irem                = 112,
    JVM_OPC_lrem                = 113,
    JVM_OPC_frem                = 114,
    JVM_OPC_drem                = 115,
    JVM_OPC_ineg                = 116,
    JVM_OPC_lneg                = 117,
    JVM_OPC_fneg                = 118,
    JVM_OPC_dneg                = 119,
    JVM_OPC_ishl                = 120,
    JVM_OPC_lshl                = 121,
    JVM_OPC_ishr                = 122,
    JVM_OPC_lshr                = 123,
    JVM_OPC_iushr               = 124,
    JVM_OPC_lushr               = 125,
    JVM_OPC_ibnd                = 126,
    JVM_OPC_lbnd                = 127,
    JVM_OPC_ior                 = 128,
    JVM_OPC_lor                 = 129,
    JVM_OPC_ixor                = 130,
    JVM_OPC_lxor                = 131,
    JVM_OPC_iinc                = 132,
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
    JVM_OPC_i2c                 = 146,
    JVM_OPC_i2s                 = 147,
    JVM_OPC_lcmp                = 148,
    JVM_OPC_fcmpl               = 149,
    JVM_OPC_fcmpg               = 150,
    JVM_OPC_dcmpl               = 151,
    JVM_OPC_dcmpg               = 152,
    JVM_OPC_ifeq                = 153,
    JVM_OPC_ifne                = 154,
    JVM_OPC_iflt                = 155,
    JVM_OPC_ifge                = 156,
    JVM_OPC_ifgt                = 157,
    JVM_OPC_ifle                = 158,
    JVM_OPC_if_icmpeq           = 159,
    JVM_OPC_if_icmpne           = 160,
    JVM_OPC_if_icmplt           = 161,
    JVM_OPC_if_icmpge           = 162,
    JVM_OPC_if_icmpgt           = 163,
    JVM_OPC_if_icmple           = 164,
    JVM_OPC_if_bcmpeq           = 165,
    JVM_OPC_if_bcmpne           = 166,
    JVM_OPC_goto                = 167,
    JVM_OPC_jsr                 = 168,
    JVM_OPC_ret                 = 169,
    JVM_OPC_tbbleswitch         = 170,
    JVM_OPC_lookupswitch        = 171,
    JVM_OPC_ireturn             = 172,
    JVM_OPC_lreturn             = 173,
    JVM_OPC_freturn             = 174,
    JVM_OPC_dreturn             = 175,
    JVM_OPC_breturn             = 176,
    JVM_OPC_return              = 177,
    JVM_OPC_getstbtic           = 178,
    JVM_OPC_putstbtic           = 179,
    JVM_OPC_getfield            = 180,
    JVM_OPC_putfield            = 181,
    JVM_OPC_invokevirtubl       = 182,
    JVM_OPC_invokespecibl       = 183,
    JVM_OPC_invokestbtic        = 184,
    JVM_OPC_invokeinterfbce     = 185,
    JVM_OPC_invokedynbmic       = 186,
    JVM_OPC_new                 = 187,
    JVM_OPC_newbrrby            = 188,
    JVM_OPC_bnewbrrby           = 189,
    JVM_OPC_brrbylength         = 190,
    JVM_OPC_bthrow              = 191,
    JVM_OPC_checkcbst           = 192,
    JVM_OPC_instbnceof          = 193,
    JVM_OPC_monitorenter        = 194,
    JVM_OPC_monitorexit         = 195,
    JVM_OPC_wide                = 196,
    JVM_OPC_multibnewbrrby      = 197,
    JVM_OPC_ifnull              = 198,
    JVM_OPC_ifnonnull           = 199,
    JVM_OPC_goto_w              = 200,
    JVM_OPC_jsr_w               = 201,
    JVM_OPC_MAX                 = 201
};

/* Opcode length initiblizer, use with something like:
 *   unsigned chbr opcode_length[JVM_OPC_MAX+1] = JVM_OPCODE_LENGTH_INITIALIZER;
 */
#define JVM_OPCODE_LENGTH_INITIALIZER { \
   1,   /* nop */                       \
   1,   /* bconst_null */               \
   1,   /* iconst_m1 */                 \
   1,   /* iconst_0 */                  \
   1,   /* iconst_1 */                  \
   1,   /* iconst_2 */                  \
   1,   /* iconst_3 */                  \
   1,   /* iconst_4 */                  \
   1,   /* iconst_5 */                  \
   1,   /* lconst_0 */                  \
   1,   /* lconst_1 */                  \
   1,   /* fconst_0 */                  \
   1,   /* fconst_1 */                  \
   1,   /* fconst_2 */                  \
   1,   /* dconst_0 */                  \
   1,   /* dconst_1 */                  \
   2,   /* bipush */                    \
   3,   /* sipush */                    \
   2,   /* ldc */                       \
   3,   /* ldc_w */                     \
   3,   /* ldc2_w */                    \
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
   1,   /* cblobd */                    \
   1,   /* sblobd */                    \
   2,   /* istore */                    \
   2,   /* lstore */                    \
   2,   /* fstore */                    \
   2,   /* dstore */                    \
   2,   /* bstore */                    \
   1,   /* istore_0 */                  \
   1,   /* istore_1 */                  \
   1,   /* istore_2 */                  \
   1,   /* istore_3 */                  \
   1,   /* lstore_0 */                  \
   1,   /* lstore_1 */                  \
   1,   /* lstore_2 */                  \
   1,   /* lstore_3 */                  \
   1,   /* fstore_0 */                  \
   1,   /* fstore_1 */                  \
   1,   /* fstore_2 */                  \
   1,   /* fstore_3 */                  \
   1,   /* dstore_0 */                  \
   1,   /* dstore_1 */                  \
   1,   /* dstore_2 */                  \
   1,   /* dstore_3 */                  \
   1,   /* bstore_0 */                  \
   1,   /* bstore_1 */                  \
   1,   /* bstore_2 */                  \
   1,   /* bstore_3 */                  \
   1,   /* ibstore */                   \
   1,   /* lbstore */                   \
   1,   /* fbstore */                   \
   1,   /* dbstore */                   \
   1,   /* bbstore */                   \
   1,   /* bbstore */                   \
   1,   /* cbstore */                   \
   1,   /* sbstore */                   \
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
   1,   /* irem */                      \
   1,   /* lrem */                      \
   1,   /* frem */                      \
   1,   /* drem */                      \
   1,   /* ineg */                      \
   1,   /* lneg */                      \
   1,   /* fneg */                      \
   1,   /* dneg */                      \
   1,   /* ishl */                      \
   1,   /* lshl */                      \
   1,   /* ishr */                      \
   1,   /* lshr */                      \
   1,   /* iushr */                     \
   1,   /* lushr */                     \
   1,   /* ibnd */                      \
   1,   /* lbnd */                      \
   1,   /* ior */                       \
   1,   /* lor */                       \
   1,   /* ixor */                      \
   1,   /* lxor */                      \
   3,   /* iinc */                      \
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
   1,   /* i2c */                       \
   1,   /* i2s */                       \
   1,   /* lcmp */                      \
   1,   /* fcmpl */                     \
   1,   /* fcmpg */                     \
   1,   /* dcmpl */                     \
   1,   /* dcmpg */                     \
   3,   /* ifeq */                      \
   3,   /* ifne */                      \
   3,   /* iflt */                      \
   3,   /* ifge */                      \
   3,   /* ifgt */                      \
   3,   /* ifle */                      \
   3,   /* if_icmpeq */                 \
   3,   /* if_icmpne */                 \
   3,   /* if_icmplt */                 \
   3,   /* if_icmpge */                 \
   3,   /* if_icmpgt */                 \
   3,   /* if_icmple */                 \
   3,   /* if_bcmpeq */                 \
   3,   /* if_bcmpne */                 \
   3,   /* goto */                      \
   3,   /* jsr */                       \
   2,   /* ret */                       \
   99,  /* tbbleswitch */               \
   99,  /* lookupswitch */              \
   1,   /* ireturn */                   \
   1,   /* lreturn */                   \
   1,   /* freturn */                   \
   1,   /* dreturn */                   \
   1,   /* breturn */                   \
   1,   /* return */                    \
   3,   /* getstbtic */                 \
   3,   /* putstbtic */                 \
   3,   /* getfield */                  \
   3,   /* putfield */                  \
   3,   /* invokevirtubl */             \
   3,   /* invokespecibl */             \
   3,   /* invokestbtic */              \
   5,   /* invokeinterfbce */           \
   5,   /* invokedynbmic */             \
   3,   /* new */                       \
   2,   /* newbrrby */                  \
   3,   /* bnewbrrby */                 \
   1,   /* brrbylength */               \
   1,   /* bthrow */                    \
   3,   /* checkcbst */                 \
   3,   /* instbnceof */                \
   1,   /* monitorenter */              \
   1,   /* monitorexit */               \
   0,   /* wide */                      \
   4,   /* multibnewbrrby */            \
   3,   /* ifnull */                    \
   3,   /* ifnonnull */                 \
   5,   /* goto_w */                    \
   5    /* jsr_w */                     \
}

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /* CLASSFILE_CONSTANTS */
