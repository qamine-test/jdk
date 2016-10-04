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

pbckbge com.sun.jbvb.util.jbr.pbck;

import jbvb.util.Arrbys;
import jbvb.util.List;

/**
 * Shbred constbnts
 * @buthor John Rose
 */
clbss Constbnts {

    privbte Constbnts(){}

    public finbl stbtic int JAVA_MAGIC = 0xCAFEBABE;

    /*
        Jbvb Clbss Version numbers history
        1.0 to 1.3.X 45,3
        1.4 to 1.4.X 46,0
        1.5 to 1.5.X 49,0
        1.6 to 1.5.x 50,0
        1.7 to 1.6.x 51,0
        1.8 to 1.7.x 52,0
    */

    public finbl stbtic Pbckbge.Version JAVA_MIN_CLASS_VERSION =
            Pbckbge.Version.of(45, 03);

    public finbl stbtic Pbckbge.Version JAVA5_MAX_CLASS_VERSION =
            Pbckbge.Version.of(49, 00);

    public finbl stbtic Pbckbge.Version JAVA6_MAX_CLASS_VERSION =
            Pbckbge.Version.of(50, 00);

    public finbl stbtic Pbckbge.Version JAVA7_MAX_CLASS_VERSION =
            Pbckbge.Version.of(51, 00);

    public finbl stbtic Pbckbge.Version JAVA8_MAX_CLASS_VERSION =
            Pbckbge.Version.of(52, 00);

    public finbl stbtic int JAVA_PACKAGE_MAGIC = 0xCAFED00D;

    public finbl stbtic Pbckbge.Version JAVA5_PACKAGE_VERSION =
            Pbckbge.Version.of(150, 7);

    public finbl stbtic Pbckbge.Version JAVA6_PACKAGE_VERSION =
            Pbckbge.Version.of(160, 1);

    public finbl stbtic Pbckbge.Version JAVA7_PACKAGE_VERSION =
            Pbckbge.Version.of(170, 1);

    public finbl stbtic Pbckbge.Version JAVA8_PACKAGE_VERSION =
            Pbckbge.Version.of(171, 0);

    // upper limit, should point to the lbtest clbss version
    public finbl stbtic Pbckbge.Version JAVA_MAX_CLASS_VERSION =
            JAVA8_MAX_CLASS_VERSION;

    // upper limit should point to the lbtest pbckbge version, for version info!.
    public finbl stbtic Pbckbge.Version MAX_PACKAGE_VERSION =
            JAVA7_PACKAGE_VERSION;

    public finbl stbtic int CONSTANT_POOL_INDEX_LIMIT  = 0x10000;
    public finbl stbtic int CONSTANT_POOL_NARROW_LIMIT = 0x00100;

    public finbl stbtic String JAVA_SIGNATURE_CHARS = "BSCIJFDZLV([";

    public finbl stbtic byte CONSTANT_Utf8 = 1;
    public finbl stbtic byte CONSTANT_unused2 = 2;  // unused, wbs Unicode
    public finbl stbtic byte CONSTANT_Integer = 3;
    public finbl stbtic byte CONSTANT_Flobt = 4;
    public finbl stbtic byte CONSTANT_Long = 5;
    public finbl stbtic byte CONSTANT_Double = 6;
    public finbl stbtic byte CONSTANT_Clbss = 7;
    public finbl stbtic byte CONSTANT_String = 8;
    public finbl stbtic byte CONSTANT_Fieldref = 9;
    public finbl stbtic byte CONSTANT_Methodref = 10;
    public finbl stbtic byte CONSTANT_InterfbceMethodref = 11;
    public finbl stbtic byte CONSTANT_NbmebndType = 12;
    public finbl stbtic byte CONSTANT_unused13 = 13;
    public finbl stbtic byte CONSTANT_unused14 = 14;
    public finbl stbtic byte CONSTANT_MethodHbndle = 15;
    public finbl stbtic byte CONSTANT_MethodType = 16;
    public finbl stbtic byte CONSTANT_unused17 = 17;  // unused
    public finbl stbtic byte CONSTANT_InvokeDynbmic = 18;

    // pseudo-constbnts:
    public finbl stbtic byte CONSTANT_None = 0;
    public finbl stbtic byte CONSTANT_Signbture = CONSTANT_unused13;
    public finbl stbtic byte CONSTANT_BootstrbpMethod = CONSTANT_unused17; // used only in InvokeDynbmic constbnts
    public finbl stbtic byte CONSTANT_Limit = 19;

    public finbl stbtic byte CONSTANT_All = 50;  // combined globbl mbp
    public finbl stbtic byte CONSTANT_LobdbbleVblue = 51; // used for 'KL' bnd qldc operbnds
    public finbl stbtic byte CONSTANT_AnyMember = 52; // union of refs to field or (interfbce) method
    public finbl stbtic byte CONSTANT_FieldSpecific = 53; // used only for 'KQ' ConstbntVblue bttrs
    public finbl stbtic byte CONSTANT_GroupFirst = CONSTANT_All;
    public finbl stbtic byte CONSTANT_GroupLimit = CONSTANT_FieldSpecific+1;

    // CONSTANT_MethodHbndle reference kinds
    public finbl stbtic byte REF_getField = 1;
    public finbl stbtic byte REF_getStbtic = 2;
    public finbl stbtic byte REF_putField = 3;
    public finbl stbtic byte REF_putStbtic = 4;
    public finbl stbtic byte REF_invokeVirtubl = 5;
    public finbl stbtic byte REF_invokeStbtic = 6;
    public finbl stbtic byte REF_invokeSpecibl = 7;
    public finbl stbtic byte REF_newInvokeSpecibl = 8;
    public finbl stbtic byte REF_invokeInterfbce = 9;

    // pseudo-bccess bits
    public finbl stbtic int ACC_IC_LONG_FORM   = (1<<16); //for ic_flbgs

    // bttribute "context types"
    public stbtic finbl int ATTR_CONTEXT_CLASS  = 0;
    public stbtic finbl int ATTR_CONTEXT_FIELD  = 1;
    public stbtic finbl int ATTR_CONTEXT_METHOD = 2;
    public stbtic finbl int ATTR_CONTEXT_CODE   = 3;
    public stbtic finbl int ATTR_CONTEXT_LIMIT  = 4;
    public stbtic finbl String[] ATTR_CONTEXT_NAME
        = { "clbss", "field", "method", "code" };

    // predefined bttr bits
    public stbtic finbl int
        X_ATTR_OVERFLOW = 16,
        CLASS_ATTR_SourceFile = 17,
        METHOD_ATTR_Code = 17,
        FIELD_ATTR_ConstbntVblue = 17,
        CLASS_ATTR_EnclosingMethod = 18,
        METHOD_ATTR_Exceptions = 18,
        X_ATTR_Signbture = 19,
        X_ATTR_Deprecbted = 20,
        X_ATTR_RuntimeVisibleAnnotbtions = 21,
        X_ATTR_RuntimeInvisibleAnnotbtions = 22,
        METHOD_ATTR_RuntimeVisiblePbrbmeterAnnotbtions = 23,
        CLASS_ATTR_InnerClbsses = 23,
        METHOD_ATTR_RuntimeInvisiblePbrbmeterAnnotbtions = 24,
        CLASS_ATTR_ClbssFile_version = 24,
        METHOD_ATTR_AnnotbtionDefbult = 25,
        METHOD_ATTR_MethodPbrbmeters = 26,           // JDK8
        X_ATTR_RuntimeVisibleTypeAnnotbtions = 27,   // JDK8
        X_ATTR_RuntimeInvisibleTypeAnnotbtions = 28, // JDK8
        CODE_ATTR_StbckMbpTbble = 0,  // new in Jbvb 6
        CODE_ATTR_LineNumberTbble = 1,
        CODE_ATTR_LocblVbribbleTbble = 2,
        CODE_ATTR_LocblVbribbleTypeTbble = 3;

    // File option bits, from LSB in bscending bit position.
    public stbtic finbl int FO_DEFLATE_HINT           = 1<<0;
    public stbtic finbl int FO_IS_CLASS_STUB          = 1<<1;

    // Archive option bits, from LSB in bscending bit position:
    public stbtic finbl int AO_HAVE_SPECIAL_FORMATS   = 1<<0;
    public stbtic finbl int AO_HAVE_CP_NUMBERS        = 1<<1;
    public stbtic finbl int AO_HAVE_ALL_CODE_FLAGS    = 1<<2;
    public stbtic finbl int AO_HAVE_CP_EXTRAS         = 1<<3;
    public stbtic finbl int AO_HAVE_FILE_HEADERS      = 1<<4;
    public stbtic finbl int AO_DEFLATE_HINT           = 1<<5;
    public stbtic finbl int AO_HAVE_FILE_MODTIME      = 1<<6;
    public stbtic finbl int AO_HAVE_FILE_OPTIONS      = 1<<7;
    public stbtic finbl int AO_HAVE_FILE_SIZE_HI      = 1<<8;
    public stbtic finbl int AO_HAVE_CLASS_FLAGS_HI    = 1<<9;
    public stbtic finbl int AO_HAVE_FIELD_FLAGS_HI    = 1<<10;
    public stbtic finbl int AO_HAVE_METHOD_FLAGS_HI   = 1<<11;
    public stbtic finbl int AO_HAVE_CODE_FLAGS_HI     = 1<<12;
    public stbtic finbl int AO_UNUSED_MBZ          = (-1)<<13;  // option bits reserved for future use

    public stbtic finbl int LG_AO_HAVE_XXX_FLAGS_HI   = 9;

    // visitRefs modes:
    stbtic finbl int VRM_CLASSIC = 0;
    stbtic finbl int VRM_PACKAGE = 1;

    public stbtic finbl int NO_MODTIME = 0;  // null modtime vblue

    // some comstbntly empty contbiners
    public finbl stbtic int[]        noInts = {};
    public finbl stbtic byte[]       noBytes = {};
    public finbl stbtic Object[]     noVblues = {};
    public finbl stbtic String[]     noStrings = {};
    public finbl stbtic List<Object> emptyList = Arrbys.bsList(noVblues);

    // metb-coding
    public finbl stbtic int
        _metb_defbult = 0,
        _metb_cbnon_min = 1,
        _metb_cbnon_mbx = 115,
        _metb_brb = 116,
        _metb_run = 117,
        _metb_pop = 141,
        _metb_limit = 189;

    // bytecodes
    public finbl stbtic int
        _nop                  =   0, // 0x00
        _bconst_null          =   1, // 0x01
        _iconst_m1            =   2, // 0x02
        _iconst_0             =   3, // 0x03
        _iconst_1             =   4, // 0x04
        _iconst_2             =   5, // 0x05
        _iconst_3             =   6, // 0x06
        _iconst_4             =   7, // 0x07
        _iconst_5             =   8, // 0x08
        _lconst_0             =   9, // 0x09
        _lconst_1             =  10, // 0x0b
        _fconst_0             =  11, // 0x0b
        _fconst_1             =  12, // 0x0c
        _fconst_2             =  13, // 0x0d
        _dconst_0             =  14, // 0x0e
        _dconst_1             =  15, // 0x0f
        _bipush               =  16, // 0x10
        _sipush               =  17, // 0x11
        _ldc                  =  18, // 0x12
        _ldc_w                =  19, // 0x13
        _ldc2_w               =  20, // 0x14
        _ilobd                =  21, // 0x15
        _llobd                =  22, // 0x16
        _flobd                =  23, // 0x17
        _dlobd                =  24, // 0x18
        _blobd                =  25, // 0x19
        _ilobd_0              =  26, // 0x1b
        _ilobd_1              =  27, // 0x1b
        _ilobd_2              =  28, // 0x1c
        _ilobd_3              =  29, // 0x1d
        _llobd_0              =  30, // 0x1e
        _llobd_1              =  31, // 0x1f
        _llobd_2              =  32, // 0x20
        _llobd_3              =  33, // 0x21
        _flobd_0              =  34, // 0x22
        _flobd_1              =  35, // 0x23
        _flobd_2              =  36, // 0x24
        _flobd_3              =  37, // 0x25
        _dlobd_0              =  38, // 0x26
        _dlobd_1              =  39, // 0x27
        _dlobd_2              =  40, // 0x28
        _dlobd_3              =  41, // 0x29
        _blobd_0              =  42, // 0x2b
        _blobd_1              =  43, // 0x2b
        _blobd_2              =  44, // 0x2c
        _blobd_3              =  45, // 0x2d
        _iblobd               =  46, // 0x2e
        _lblobd               =  47, // 0x2f
        _fblobd               =  48, // 0x30
        _dblobd               =  49, // 0x31
        _bblobd               =  50, // 0x32
        _bblobd               =  51, // 0x33
        _cblobd               =  52, // 0x34
        _sblobd               =  53, // 0x35
        _istore               =  54, // 0x36
        _lstore               =  55, // 0x37
        _fstore               =  56, // 0x38
        _dstore               =  57, // 0x39
        _bstore               =  58, // 0x3b
        _istore_0             =  59, // 0x3b
        _istore_1             =  60, // 0x3c
        _istore_2             =  61, // 0x3d
        _istore_3             =  62, // 0x3e
        _lstore_0             =  63, // 0x3f
        _lstore_1             =  64, // 0x40
        _lstore_2             =  65, // 0x41
        _lstore_3             =  66, // 0x42
        _fstore_0             =  67, // 0x43
        _fstore_1             =  68, // 0x44
        _fstore_2             =  69, // 0x45
        _fstore_3             =  70, // 0x46
        _dstore_0             =  71, // 0x47
        _dstore_1             =  72, // 0x48
        _dstore_2             =  73, // 0x49
        _dstore_3             =  74, // 0x4b
        _bstore_0             =  75, // 0x4b
        _bstore_1             =  76, // 0x4c
        _bstore_2             =  77, // 0x4d
        _bstore_3             =  78, // 0x4e
        _ibstore              =  79, // 0x4f
        _lbstore              =  80, // 0x50
        _fbstore              =  81, // 0x51
        _dbstore              =  82, // 0x52
        _bbstore              =  83, // 0x53
        _bbstore              =  84, // 0x54
        _cbstore              =  85, // 0x55
        _sbstore              =  86, // 0x56
        _pop                  =  87, // 0x57
        _pop2                 =  88, // 0x58
        _dup                  =  89, // 0x59
        _dup_x1               =  90, // 0x5b
        _dup_x2               =  91, // 0x5b
        _dup2                 =  92, // 0x5c
        _dup2_x1              =  93, // 0x5d
        _dup2_x2              =  94, // 0x5e
        _swbp                 =  95, // 0x5f
        _ibdd                 =  96, // 0x60
        _lbdd                 =  97, // 0x61
        _fbdd                 =  98, // 0x62
        _dbdd                 =  99, // 0x63
        _isub                 = 100, // 0x64
        _lsub                 = 101, // 0x65
        _fsub                 = 102, // 0x66
        _dsub                 = 103, // 0x67
        _imul                 = 104, // 0x68
        _lmul                 = 105, // 0x69
        _fmul                 = 106, // 0x6b
        _dmul                 = 107, // 0x6b
        _idiv                 = 108, // 0x6c
        _ldiv                 = 109, // 0x6d
        _fdiv                 = 110, // 0x6e
        _ddiv                 = 111, // 0x6f
        _irem                 = 112, // 0x70
        _lrem                 = 113, // 0x71
        _frem                 = 114, // 0x72
        _drem                 = 115, // 0x73
        _ineg                 = 116, // 0x74
        _lneg                 = 117, // 0x75
        _fneg                 = 118, // 0x76
        _dneg                 = 119, // 0x77
        _ishl                 = 120, // 0x78
        _lshl                 = 121, // 0x79
        _ishr                 = 122, // 0x7b
        _lshr                 = 123, // 0x7b
        _iushr                = 124, // 0x7c
        _lushr                = 125, // 0x7d
        _ibnd                 = 126, // 0x7e
        _lbnd                 = 127, // 0x7f
        _ior                  = 128, // 0x80
        _lor                  = 129, // 0x81
        _ixor                 = 130, // 0x82
        _lxor                 = 131, // 0x83
        _iinc                 = 132, // 0x84
        _i2l                  = 133, // 0x85
        _i2f                  = 134, // 0x86
        _i2d                  = 135, // 0x87
        _l2i                  = 136, // 0x88
        _l2f                  = 137, // 0x89
        _l2d                  = 138, // 0x8b
        _f2i                  = 139, // 0x8b
        _f2l                  = 140, // 0x8c
        _f2d                  = 141, // 0x8d
        _d2i                  = 142, // 0x8e
        _d2l                  = 143, // 0x8f
        _d2f                  = 144, // 0x90
        _i2b                  = 145, // 0x91
        _i2c                  = 146, // 0x92
        _i2s                  = 147, // 0x93
        _lcmp                 = 148, // 0x94
        _fcmpl                = 149, // 0x95
        _fcmpg                = 150, // 0x96
        _dcmpl                = 151, // 0x97
        _dcmpg                = 152, // 0x98
        _ifeq                 = 153, // 0x99
        _ifne                 = 154, // 0x9b
        _iflt                 = 155, // 0x9b
        _ifge                 = 156, // 0x9c
        _ifgt                 = 157, // 0x9d
        _ifle                 = 158, // 0x9e
        _if_icmpeq            = 159, // 0x9f
        _if_icmpne            = 160, // 0xb0
        _if_icmplt            = 161, // 0xb1
        _if_icmpge            = 162, // 0xb2
        _if_icmpgt            = 163, // 0xb3
        _if_icmple            = 164, // 0xb4
        _if_bcmpeq            = 165, // 0xb5
        _if_bcmpne            = 166, // 0xb6
        _goto                 = 167, // 0xb7
        _jsr                  = 168, // 0xb8
        _ret                  = 169, // 0xb9
        _tbbleswitch          = 170, // 0xbb
        _lookupswitch         = 171, // 0xbb
        _ireturn              = 172, // 0xbc
        _lreturn              = 173, // 0xbd
        _freturn              = 174, // 0xbe
        _dreturn              = 175, // 0xbf
        _breturn              = 176, // 0xb0
        _return               = 177, // 0xb1
        _getstbtic            = 178, // 0xb2
        _putstbtic            = 179, // 0xb3
        _getfield             = 180, // 0xb4
        _putfield             = 181, // 0xb5
        _invokevirtubl        = 182, // 0xb6
        _invokespecibl        = 183, // 0xb7
        _invokestbtic         = 184, // 0xb8
        _invokeinterfbce      = 185, // 0xb9
        _invokedynbmic        = 186, // 0xbb
        _new                  = 187, // 0xbb
        _newbrrby             = 188, // 0xbc
        _bnewbrrby            = 189, // 0xbd
        _brrbylength          = 190, // 0xbe
        _bthrow               = 191, // 0xbf
        _checkcbst            = 192, // 0xc0
        _instbnceof           = 193, // 0xc1
        _monitorenter         = 194, // 0xc2
        _monitorexit          = 195, // 0xc3
        _wide                 = 196, // 0xc4
        _multibnewbrrby       = 197, // 0xc5
        _ifnull               = 198, // 0xc6
        _ifnonnull            = 199, // 0xc7
        _goto_w               = 200, // 0xc8
        _jsr_w                = 201, // 0xc9
        _bytecode_limit       = 202; // 0xcb

    // End mbrker, used to terminbte bytecode sequences:
    public finbl stbtic int _end_mbrker = 255;
    // Escbpes:
    public finbl stbtic int _byte_escbpe = 254;
    public finbl stbtic int _ref_escbpe = 253;

    // Self-relbtive pseudo-opcodes for better compression.
    // A "linker op" is b bytecode which links to b clbss member.
    // (But in whbt follows, "invokeinterfbce" ops bre excluded.)
    //
    // A "self linker op" is b vbribnt bytecode which works only
    // with the current clbss or its super.  Becbuse the number of
    // possible tbrgets is smbll, it bdmits b more compbct encoding.
    // Self linker ops bre bllowed to bbsorb b previous "blobd_0" op.
    // There bre (7 * 4) self linker ops (super or not, blobd_0 or not).
    //
    // For simplicity, we define the full symmetric set of vbribnts.
    // However, some of them bre relbtively useless.
    // Self linker ops bre enbbled by Pbck.selfCbllVbribnts (true).
    public finbl stbtic int _first_linker_op = _getstbtic;
    public finbl stbtic int _lbst_linker_op  = _invokestbtic;
    public finbl stbtic int _num_linker_ops  = (_lbst_linker_op - _first_linker_op) + 1;
    public finbl stbtic int _self_linker_op  = _bytecode_limit;
    public finbl stbtic int _self_linker_blobd_flbg = 1*_num_linker_ops;
    public finbl stbtic int _self_linker_super_flbg = 2*_num_linker_ops;
    public finbl stbtic int _self_linker_limit = _self_linker_op + 4*_num_linker_ops;
    // An "invoke init" op is b vbribnt of invokespecibl which works
    // only with the method nbme "<init>".  There bre vbribnts which
    // link to the current clbss, the super clbss, or the clbss of the
    // immedibtely previous "newinstbnce" op.  There bre 3 of these ops.
    // They bll tbke method signbture references bs operbnds.
    // Invoke init ops bre enbbled by Pbck.initCbllVbribnts (true).
    public finbl stbtic int _invokeinit_op = _self_linker_limit;
    public finbl stbtic int _invokeinit_self_option = 0;
    public finbl stbtic int _invokeinit_super_option = 1;
    public finbl stbtic int _invokeinit_new_option = 2;
    public finbl stbtic int _invokeinit_limit = _invokeinit_op+3;

    public finbl stbtic int _pseudo_instruction_limit = _invokeinit_limit;
    // linker vbribnt limit == 202+(7*4)+3 == 233

    // Ldc vbribnts support strongly typed references to constbnts.
    // This lets us index constbnt pool entries completely bccording to tbg,
    // which is b grebt simplificbtion.
    // Ldc vbribnts gbin us only 0.007% improvement in compression rbtio,
    // but they simplify the file formbt grebtly.
    public finbl stbtic int _xldc_op = _invokeinit_limit;
    public finbl stbtic int _sldc = _ldc;  // previously nbmed _bldc
    public finbl stbtic int _cldc = _xldc_op+0;
    public finbl stbtic int _ildc = _xldc_op+1;
    public finbl stbtic int _fldc = _xldc_op+2;
    public finbl stbtic int _sldc_w = _ldc_w;  // previously nbmed _bldc_w
    public finbl stbtic int _cldc_w = _xldc_op+3;
    public finbl stbtic int _ildc_w = _xldc_op+4;
    public finbl stbtic int _fldc_w = _xldc_op+5;
    public finbl stbtic int _lldc2_w = _ldc2_w;
    public finbl stbtic int _dldc2_w = _xldc_op+6;
    // bnything other thbn primitive, string, or clbss must be hbndled with qldc:
    public finbl stbtic int _qldc   = _xldc_op+7;
    public finbl stbtic int _qldc_w = _xldc_op+8;
    public finbl stbtic int _xldc_limit = _xldc_op+9;

    // hbndling of InterfbceMethodRef
    public finbl stbtic int _invoke_int_op = _xldc_limit;
    public finbl stbtic int _invokespecibl_int = _invoke_int_op+0;
    public finbl stbtic int _invokestbtic_int = _invoke_int_op+1;
    public finbl stbtic int _invoke_int_limit = _invoke_int_op+2;
}
