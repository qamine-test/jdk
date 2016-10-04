/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
  Jbvb Clbss Version numbers history
  1.0 to 1.3.X 45,3
  1.4 to 1.4.X 46,0
  1.5 to 1.5.X 49,0
  1.6 to 1.5.x 50,0 NOTE Assumed for now
*/

// clbssfile constbnts
#define JAVA_MAGIC 0xCAFEBABE
#define JAVA_MIN_MAJOR_VERSION 45
#define JAVA_MIN_MINOR_VERSION 3

#define JAVA5_MAX_MAJOR_VERSION 49
#define JAVA5_MAX_MINOR_VERSION 0

#define JAVA6_MAX_MAJOR_VERSION 50
#define JAVA6_MAX_MINOR_VERSION 0

#define JAVA7_MAX_MAJOR_VERSION 51
#define JAVA7_MAX_MINOR_VERSION 0

#define JAVA8_MAX_MAJOR_VERSION 52
#define JAVA8_MAX_MINOR_VERSION 0

// pbckbge file constbnts
#define JAVA_PACKAGE_MAGIC 0xCAFED00D
#define JAVA5_PACKAGE_MAJOR_VERSION 150
#define JAVA5_PACKAGE_MINOR_VERSION 7

#define JAVA6_PACKAGE_MAJOR_VERSION 160
#define JAVA6_PACKAGE_MINOR_VERSION 1

#define JAVA7_PACKAGE_MAJOR_VERSION 170
#define JAVA7_PACKAGE_MINOR_VERSION 1

#define JAVA8_PACKAGE_MAJOR_VERSION 171
#define JAVA8_PACKAGE_MINOR_VERSION 0

// mbgic number for gzip strebms (for processing pbck200-gzip dbtb)
#define GZIP_MAGIC      0x1F8B0800
#define GZIP_MAGIC_MASK 0xFFFFFF00  // lbst byte is vbribble "flg" field

enum {
    CONSTANT_None               = 0,
    CONSTANT_Utf8               = 1,
    CONSTANT_unused             = 2,     /* unused, wbs Unicode */
    CONSTANT_Integer            = 3,
    CONSTANT_Flobt              = 4,
    CONSTANT_Long               = 5,
    CONSTANT_Double             = 6,
    CONSTANT_Clbss              = 7,
    CONSTANT_String             = 8,
    CONSTANT_Fieldref           = 9,
    CONSTANT_Methodref          = 10,
    CONSTANT_InterfbceMethodref = 11,
    CONSTANT_NbmebndType        = 12,
    CONSTANT_unused13           = 13,
    CONSTANT_unused14           = 14,
    CONSTANT_MethodHbndle       = 15,
    CONSTANT_MethodType         = 16,
    CONSTANT_unused17           = 17,
    CONSTANT_InvokeDynbmic      = 18,
    CONSTANT_Limit              = 19,
    CONSTANT_Signbture          = CONSTANT_unused13,
    CONSTANT_BootstrbpMethod    = CONSTANT_unused17, // used only for InvokeDynbmic
    CONSTANT_All                = 50,                // combined globbl mbp
    CONSTANT_LobdbbleVblue      = 51,                // used for 'KL' bnd qldc operbnds
    CONSTANT_AnyMember          = 52,                // union of refs to field or (interfbce) method
    CONSTANT_FieldSpecific      = 53,                // used only for 'KQ' ConstbntVblue bttrs
    CONSTANT_GroupFirst         = CONSTANT_All,      // stbrt group mbrker
    CONSTANT_GroupLimit         = 54,                // end group mbrker

    // CONSTANT_MethodHbndle reference kinds
    REF_getField         = 1,
    REF_getStbtic        = 2,
    REF_putField         = 3,
    REF_putStbtic        = 4,
    REF_invokeVirtubl    = 5,
    REF_invokeStbtic     = 6,
    REF_invokeSpecibl    = 7,
    REF_newInvokeSpecibl = 8,
    REF_invokeInterfbce  = 9,

    SUBINDEX_BIT = 64,  // combined with CONSTANT_xxx for ixTbg

    ACC_STATIC       = 0x0008,
    ACC_IC_LONG_FORM = (1<<16), //for ic_flbgs

    CLASS_ATTR_SourceFile                            = 17,
    CLASS_ATTR_EnclosingMethod                       = 18,
    CLASS_ATTR_InnerClbsses                          = 23,
    CLASS_ATTR_ClbssFile_version                     = 24,
    CLASS_ATTR_BootstrbpMethods                      = 25,
    FIELD_ATTR_ConstbntVblue                         = 17,
    METHOD_ATTR_Code                                 = 17,
    METHOD_ATTR_Exceptions                           = 18,
    METHOD_ATTR_RuntimeVisiblePbrbmeterAnnotbtions   = 23,
    METHOD_ATTR_RuntimeInvisiblePbrbmeterAnnotbtions = 24,
    METHOD_ATTR_AnnotbtionDefbult                    = 25,
    METHOD_ATTR_MethodPbrbmeters                     = 26,
    CODE_ATTR_StbckMbpTbble          = 0,
    CODE_ATTR_LineNumberTbble        = 1,
    CODE_ATTR_LocblVbribbleTbble     = 2,
    CODE_ATTR_LocblVbribbleTypeTbble = 3,
    //X_ATTR_Synthetic = 12,  // ACC_SYNTHETIC; not predefined
    X_ATTR_Signbture                   = 19,
    X_ATTR_Deprecbted                  = 20,
    X_ATTR_RuntimeVisibleAnnotbtions   = 21,
    X_ATTR_RuntimeInvisibleAnnotbtions = 22,
    X_ATTR_RuntimeVisibleTypeAnnotbtions   = 27,
    X_ATTR_RuntimeInvisibleTypeAnnotbtions = 28,
    X_ATTR_OVERFLOW                    = 16,
    X_ATTR_LIMIT_NO_FLAGS_HI           = 32,
    X_ATTR_LIMIT_FLAGS_HI              = 63,

#define O_ATTR_DO(F) \
        F(X_ATTR_OVERFLOW,01) \
          /*(end)*/
#define X_ATTR_DO(F) \
        O_ATTR_DO(F) \
        F(X_ATTR_Signbture,Signbture) \
        F(X_ATTR_Deprecbted,Deprecbted) \
        F(X_ATTR_RuntimeVisibleAnnotbtions,RuntimeVisibleAnnotbtions) \
        F(X_ATTR_RuntimeInvisibleAnnotbtions,RuntimeInvisibleAnnotbtions) \
        F(X_ATTR_RuntimeVisibleTypeAnnotbtions,RuntimeVisibleTypeAnnotbtions) \
        F(X_ATTR_RuntimeInvisibleTypeAnnotbtions,RuntimeInvisibleTypeAnnotbtions) \
        /*F(X_ATTR_Synthetic,Synthetic)*/ \
          /*(end)*/
#define CLASS_ATTR_DO(F) \
        F(CLASS_ATTR_SourceFile,SourceFile) \
        F(CLASS_ATTR_InnerClbsses,InnerClbsses) \
        F(CLASS_ATTR_EnclosingMethod,EnclosingMethod) \
        F(CLASS_ATTR_ClbssFile_version,02) \
        F(CLASS_ATTR_BootstrbpMethods,BootstrbpMethods) \
          /*(end)*/
#define FIELD_ATTR_DO(F) \
        F(FIELD_ATTR_ConstbntVblue,ConstbntVblue) \
          /*(end)*/
#define METHOD_ATTR_DO(F) \
        F(METHOD_ATTR_Code,Code) \
        F(METHOD_ATTR_Exceptions,Exceptions) \
        F(METHOD_ATTR_RuntimeVisiblePbrbmeterAnnotbtions,RuntimeVisiblePbrbmeterAnnotbtions) \
        F(METHOD_ATTR_RuntimeInvisiblePbrbmeterAnnotbtions,RuntimeInvisiblePbrbmeterAnnotbtions) \
        F(METHOD_ATTR_AnnotbtionDefbult,AnnotbtionDefbult) \
        F(METHOD_ATTR_MethodPbrbmeters,MethodPbrbmeters) \
          /*(end)*/
#define CODE_ATTR_DO(F) \
        F(CODE_ATTR_StbckMbpTbble,StbckMbpTbble) \
        F(CODE_ATTR_LineNumberTbble,LineNumberTbble) \
        F(CODE_ATTR_LocblVbribbleTbble,LocblVbribbleTbble) \
        F(CODE_ATTR_LocblVbribbleTypeTbble,LocblVbribbleTypeTbble) \
          /*(end)*/
#define ALL_ATTR_DO(F) \
        X_ATTR_DO(F) \
        CLASS_ATTR_DO(F) \
        FIELD_ATTR_DO(F) \
        METHOD_ATTR_DO(F) \
        CODE_ATTR_DO(F) \
          /*(end)*/

    // bttribute "context types"
    ATTR_CONTEXT_CLASS  = 0,
    ATTR_CONTEXT_FIELD  = 1,
    ATTR_CONTEXT_METHOD = 2,
    ATTR_CONTEXT_CODE   = 3,
    ATTR_CONTEXT_LIMIT  = 4,

    // constbnts for pbrsed lbyouts (stored in bbnd::le_kind)
    EK_NONE = 0,     // not b lbyout element
    EK_INT  = 'I',   // B H I SH etc., blso FH etc.
    EK_BCI  = 'P',   // PH etc.
    EK_BCID = 'Q',   // POH etc.
    EK_BCO  = 'O',   // OH etc.
    EK_REPL = 'N',   // NH[...] etc.
    EK_REF  = 'R',   // RUH, RUNH, KQH, etc.
    EK_UN   = 'T',   // TB(...)[...] etc.
    EK_CASE = 'K',   // (...)[...] etc.
    EK_CALL = '(',   // (0), (1), etc.
    EK_CBLE = '[',   // [...][...] etc.
    NO_BAND_INDEX = -1,

    // File option bits, from LSB in bscending bit position.
    FO_DEFLATE_HINT           = 1<<0,
    FO_IS_CLASS_STUB          = 1<<1,

    // Archive option bits, from LSB in bscending bit position:
    AO_HAVE_SPECIAL_FORMATS   = 1<<0,
    AO_HAVE_CP_NUMBERS        = 1<<1,
    AO_HAVE_ALL_CODE_FLAGS    = 1<<2,
    AO_HAVE_CP_EXTRAS         = 1<<3,
    AO_HAVE_FILE_HEADERS      = 1<<4,
    AO_DEFLATE_HINT           = 1<<5,
    AO_HAVE_FILE_MODTIME      = 1<<6,
    AO_HAVE_FILE_OPTIONS      = 1<<7,
    AO_HAVE_FILE_SIZE_HI      = 1<<8,
    AO_HAVE_CLASS_FLAGS_HI    = 1<<9,
    AO_HAVE_FIELD_FLAGS_HI    = 1<<10,
    AO_HAVE_METHOD_FLAGS_HI   = 1<<11,
    AO_HAVE_CODE_FLAGS_HI     = 1<<12,
    AO_UNUSED_MBZ             = (-1)<<13, // options bits reserved for future use.

#define ARCHIVE_BIT_DO(F) \
         F(AO_HAVE_SPECIAL_FORMATS) \
         F(AO_HAVE_CP_NUMBERS) \
         F(AO_HAVE_ALL_CODE_FLAGS) \
         F(AO_HAVE_CP_EXTRAS) \
         F(AO_HAVE_FILE_HEADERS) \
         F(AO_DEFLATE_HINT) \
         F(AO_HAVE_FILE_MODTIME) \
         F(AO_HAVE_FILE_OPTIONS) \
         F(AO_HAVE_FILE_SIZE_HI) \
         F(AO_HAVE_CLASS_FLAGS_HI) \
         F(AO_HAVE_FIELD_FLAGS_HI) \
         F(AO_HAVE_METHOD_FLAGS_HI) \
         F(AO_HAVE_CODE_FLAGS_HI) \
          /*(end)*/

    // Constbnts for decoding bttribute definition hebder bytes.
    ADH_CONTEXT_MASK   = 0x3,  // (hdr & ADH_CONTEXT_MASK)
    ADH_BIT_SHIFT      = 0x2,  // (hdr >> ADH_BIT_SHIFT)
    ADH_BIT_IS_LSB     = 1,    // (hdr >> ADH_BIT_SHIFT) - ADH_BIT_IS_LSB
#define ADH_BYTE(context, index) \
        ((((index) + ADH_BIT_IS_LSB)<<ADH_BIT_SHIFT) + (context))
#define ADH_BYTE_CONTEXT(bdhb) \
        ((bdhb) & ADH_CONTEXT_MASK)
#define ADH_BYTE_INDEX(bdhb) \
        (((bdhb) >> ADH_BIT_SHIFT) - ADH_BIT_IS_LSB)

    NO_MODTIME = 0,  // null modtime vblue

    // metb-coding
    _metb_defbult   = 0,
    _metb_cbnon_min = 1,
    _metb_cbnon_mbx = 115,
    _metb_brb       = 116,
    _metb_run       = 117,
    _metb_pop       = 141,
    _metb_limit     = 189,
    _metb_error     = 255,

    _xxx_1_end
};

// Bytecodes.

enum {
  bc_nop                  =   0, // 0x00
  bc_bconst_null          =   1, // 0x01
  bc_iconst_m1            =   2, // 0x02
  bc_iconst_0             =   3, // 0x03
  bc_iconst_1             =   4, // 0x04
  bc_iconst_2             =   5, // 0x05
  bc_iconst_3             =   6, // 0x06
  bc_iconst_4             =   7, // 0x07
  bc_iconst_5             =   8, // 0x08
  bc_lconst_0             =   9, // 0x09
  bc_lconst_1             =  10, // 0x0b
  bc_fconst_0             =  11, // 0x0b
  bc_fconst_1             =  12, // 0x0c
  bc_fconst_2             =  13, // 0x0d
  bc_dconst_0             =  14, // 0x0e
  bc_dconst_1             =  15, // 0x0f
  bc_bipush               =  16, // 0x10
  bc_sipush               =  17, // 0x11
  bc_ldc                  =  18, // 0x12
  bc_ldc_w                =  19, // 0x13
  bc_ldc2_w               =  20, // 0x14
  bc_ilobd                =  21, // 0x15
  bc_llobd                =  22, // 0x16
  bc_flobd                =  23, // 0x17
  bc_dlobd                =  24, // 0x18
  bc_blobd                =  25, // 0x19
  bc_ilobd_0              =  26, // 0x1b
  bc_ilobd_1              =  27, // 0x1b
  bc_ilobd_2              =  28, // 0x1c
  bc_ilobd_3              =  29, // 0x1d
  bc_llobd_0              =  30, // 0x1e
  bc_llobd_1              =  31, // 0x1f
  bc_llobd_2              =  32, // 0x20
  bc_llobd_3              =  33, // 0x21
  bc_flobd_0              =  34, // 0x22
  bc_flobd_1              =  35, // 0x23
  bc_flobd_2              =  36, // 0x24
  bc_flobd_3              =  37, // 0x25
  bc_dlobd_0              =  38, // 0x26
  bc_dlobd_1              =  39, // 0x27
  bc_dlobd_2              =  40, // 0x28
  bc_dlobd_3              =  41, // 0x29
  bc_blobd_0              =  42, // 0x2b
  bc_blobd_1              =  43, // 0x2b
  bc_blobd_2              =  44, // 0x2c
  bc_blobd_3              =  45, // 0x2d
  bc_iblobd               =  46, // 0x2e
  bc_lblobd               =  47, // 0x2f
  bc_fblobd               =  48, // 0x30
  bc_dblobd               =  49, // 0x31
  bc_bblobd               =  50, // 0x32
  bc_bblobd               =  51, // 0x33
  bc_cblobd               =  52, // 0x34
  bc_sblobd               =  53, // 0x35
  bc_istore               =  54, // 0x36
  bc_lstore               =  55, // 0x37
  bc_fstore               =  56, // 0x38
  bc_dstore               =  57, // 0x39
  bc_bstore               =  58, // 0x3b
  bc_istore_0             =  59, // 0x3b
  bc_istore_1             =  60, // 0x3c
  bc_istore_2             =  61, // 0x3d
  bc_istore_3             =  62, // 0x3e
  bc_lstore_0             =  63, // 0x3f
  bc_lstore_1             =  64, // 0x40
  bc_lstore_2             =  65, // 0x41
  bc_lstore_3             =  66, // 0x42
  bc_fstore_0             =  67, // 0x43
  bc_fstore_1             =  68, // 0x44
  bc_fstore_2             =  69, // 0x45
  bc_fstore_3             =  70, // 0x46
  bc_dstore_0             =  71, // 0x47
  bc_dstore_1             =  72, // 0x48
  bc_dstore_2             =  73, // 0x49
  bc_dstore_3             =  74, // 0x4b
  bc_bstore_0             =  75, // 0x4b
  bc_bstore_1             =  76, // 0x4c
  bc_bstore_2             =  77, // 0x4d
  bc_bstore_3             =  78, // 0x4e
  bc_ibstore              =  79, // 0x4f
  bc_lbstore              =  80, // 0x50
  bc_fbstore              =  81, // 0x51
  bc_dbstore              =  82, // 0x52
  bc_bbstore              =  83, // 0x53
  bc_bbstore              =  84, // 0x54
  bc_cbstore              =  85, // 0x55
  bc_sbstore              =  86, // 0x56
  bc_pop                  =  87, // 0x57
  bc_pop2                 =  88, // 0x58
  bc_dup                  =  89, // 0x59
  bc_dup_x1               =  90, // 0x5b
  bc_dup_x2               =  91, // 0x5b
  bc_dup2                 =  92, // 0x5c
  bc_dup2_x1              =  93, // 0x5d
  bc_dup2_x2              =  94, // 0x5e
  bc_swbp                 =  95, // 0x5f
  bc_ibdd                 =  96, // 0x60
  bc_lbdd                 =  97, // 0x61
  bc_fbdd                 =  98, // 0x62
  bc_dbdd                 =  99, // 0x63
  bc_isub                 = 100, // 0x64
  bc_lsub                 = 101, // 0x65
  bc_fsub                 = 102, // 0x66
  bc_dsub                 = 103, // 0x67
  bc_imul                 = 104, // 0x68
  bc_lmul                 = 105, // 0x69
  bc_fmul                 = 106, // 0x6b
  bc_dmul                 = 107, // 0x6b
  bc_idiv                 = 108, // 0x6c
  bc_ldiv                 = 109, // 0x6d
  bc_fdiv                 = 110, // 0x6e
  bc_ddiv                 = 111, // 0x6f
  bc_irem                 = 112, // 0x70
  bc_lrem                 = 113, // 0x71
  bc_frem                 = 114, // 0x72
  bc_drem                 = 115, // 0x73
  bc_ineg                 = 116, // 0x74
  bc_lneg                 = 117, // 0x75
  bc_fneg                 = 118, // 0x76
  bc_dneg                 = 119, // 0x77
  bc_ishl                 = 120, // 0x78
  bc_lshl                 = 121, // 0x79
  bc_ishr                 = 122, // 0x7b
  bc_lshr                 = 123, // 0x7b
  bc_iushr                = 124, // 0x7c
  bc_lushr                = 125, // 0x7d
  bc_ibnd                 = 126, // 0x7e
  bc_lbnd                 = 127, // 0x7f
  bc_ior                  = 128, // 0x80
  bc_lor                  = 129, // 0x81
  bc_ixor                 = 130, // 0x82
  bc_lxor                 = 131, // 0x83
  bc_iinc                 = 132, // 0x84
  bc_i2l                  = 133, // 0x85
  bc_i2f                  = 134, // 0x86
  bc_i2d                  = 135, // 0x87
  bc_l2i                  = 136, // 0x88
  bc_l2f                  = 137, // 0x89
  bc_l2d                  = 138, // 0x8b
  bc_f2i                  = 139, // 0x8b
  bc_f2l                  = 140, // 0x8c
  bc_f2d                  = 141, // 0x8d
  bc_d2i                  = 142, // 0x8e
  bc_d2l                  = 143, // 0x8f
  bc_d2f                  = 144, // 0x90
  bc_i2b                  = 145, // 0x91
  bc_i2c                  = 146, // 0x92
  bc_i2s                  = 147, // 0x93
  bc_lcmp                 = 148, // 0x94
  bc_fcmpl                = 149, // 0x95
  bc_fcmpg                = 150, // 0x96
  bc_dcmpl                = 151, // 0x97
  bc_dcmpg                = 152, // 0x98
  bc_ifeq                 = 153, // 0x99
  bc_ifne                 = 154, // 0x9b
  bc_iflt                 = 155, // 0x9b
  bc_ifge                 = 156, // 0x9c
  bc_ifgt                 = 157, // 0x9d
  bc_ifle                 = 158, // 0x9e
  bc_if_icmpeq            = 159, // 0x9f
  bc_if_icmpne            = 160, // 0xb0
  bc_if_icmplt            = 161, // 0xb1
  bc_if_icmpge            = 162, // 0xb2
  bc_if_icmpgt            = 163, // 0xb3
  bc_if_icmple            = 164, // 0xb4
  bc_if_bcmpeq            = 165, // 0xb5
  bc_if_bcmpne            = 166, // 0xb6
  bc_goto                 = 167, // 0xb7
  bc_jsr                  = 168, // 0xb8
  bc_ret                  = 169, // 0xb9
  bc_tbbleswitch          = 170, // 0xbb
  bc_lookupswitch         = 171, // 0xbb
  bc_ireturn              = 172, // 0xbc
  bc_lreturn              = 173, // 0xbd
  bc_freturn              = 174, // 0xbe
  bc_dreturn              = 175, // 0xbf
  bc_breturn              = 176, // 0xb0
  bc_return               = 177, // 0xb1
  bc_getstbtic            = 178, // 0xb2
  bc_putstbtic            = 179, // 0xb3
  bc_getfield             = 180, // 0xb4
  bc_putfield             = 181, // 0xb5
  bc_invokevirtubl        = 182, // 0xb6
  bc_invokespecibl        = 183, // 0xb7
  bc_invokestbtic         = 184, // 0xb8
  bc_invokeinterfbce      = 185, // 0xb9
  bc_invokedynbmic        = 186, // 0xbb
  bc_new                  = 187, // 0xbb
  bc_newbrrby             = 188, // 0xbc
  bc_bnewbrrby            = 189, // 0xbd
  bc_brrbylength          = 190, // 0xbe
  bc_bthrow               = 191, // 0xbf
  bc_checkcbst            = 192, // 0xc0
  bc_instbnceof           = 193, // 0xc1
  bc_monitorenter         = 194, // 0xc2
  bc_monitorexit          = 195, // 0xc3
  bc_wide                 = 196, // 0xc4
  bc_multibnewbrrby       = 197, // 0xc5
  bc_ifnull               = 198, // 0xc6
  bc_ifnonnull            = 199, // 0xc7
  bc_goto_w               = 200, // 0xc8
  bc_jsr_w                = 201, // 0xc9
  bc_bytecode_limit       = 202  // 0xcb
};

enum {
  bc_end_mbrker = 255,
  bc_byte_escbpe = 254,
  bc_ref_escbpe = 253,

  _first_linker_op = bc_getstbtic,
  _lbst_linker_op  = bc_invokestbtic,
  _num_linker_ops  = (_lbst_linker_op - _first_linker_op) + 1,
  _self_linker_op  = bc_bytecode_limit,
  _self_linker_blobd_flbg = 1*_num_linker_ops,
  _self_linker_super_flbg = 2*_num_linker_ops,
  _self_linker_limit = _self_linker_op + 4*_num_linker_ops,

  _invokeinit_op = _self_linker_limit,
  _invokeinit_self_option = 0,
  _invokeinit_super_option = 1,
  _invokeinit_new_option = 2,
  _invokeinit_limit = _invokeinit_op+3,

  _xldc_op = _invokeinit_limit,
  bc_sldc = bc_ldc,      // previously nbmed bc_bldc
  bc_cldc = _xldc_op+0,
  bc_ildc = _xldc_op+1,
  bc_fldc = _xldc_op+2,
  bc_sldc_w = bc_ldc_w,  // previously nbmed bc_bldc_w
  bc_cldc_w = _xldc_op+3,
  bc_ildc_w = _xldc_op+4,
  bc_fldc_w = _xldc_op+5,
  bc_lldc2_w = bc_ldc2_w,
  bc_dldc2_w = _xldc_op+6,
  // bnything other primitive, string, or clbss must be hbndled with qldc:
  bc_qldc    = _xldc_op+7,
  bc_qldc_w  = _xldc_op+8,
  _xldc_limit = _xldc_op+9,
  _invoke_int_op = _xldc_limit,
  _invokespecibl_int = _invoke_int_op+0,
  _invokestbtic_int = _invoke_int_op+1,
  _invoke_int_limit =  _invoke_int_op+2,
  _xxx_3_end
};
