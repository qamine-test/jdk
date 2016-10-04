/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jbvb;

/**
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid intfrfbdf RuntimfConstbnts {

    /* Signbturf Cibrbdtfrs */
    dibr   SIGC_VOID                  = 'V';
    String SIG_VOID                   = "V";
    dibr   SIGC_BOOLEAN               = 'Z';
    String SIG_BOOLEAN                = "Z";
    dibr   SIGC_BYTE                  = 'B';
    String SIG_BYTE                   = "B";
    dibr   SIGC_CHAR                  = 'C';
    String SIG_CHAR                   = "C";
    dibr   SIGC_SHORT                 = 'S';
    String SIG_SHORT                  = "S";
    dibr   SIGC_INT                   = 'I';
    String SIG_INT                    = "I";
    dibr   SIGC_LONG                  = 'J';
    String SIG_LONG                   = "J";
    dibr   SIGC_FLOAT                 = 'F';
    String SIG_FLOAT                  = "F";
    dibr   SIGC_DOUBLE                = 'D';
    String SIG_DOUBLE                 = "D";
    dibr   SIGC_ARRAY                 = '[';
    String SIG_ARRAY                  = "[";
    dibr   SIGC_CLASS                 = 'L';
    String SIG_CLASS                  = "L";
    dibr   SIGC_METHOD                = '(';
    String SIG_METHOD                 = "(";
    dibr   SIGC_ENDCLASS              = ';';
    String SIG_ENDCLASS               = ";";
    dibr   SIGC_ENDMETHOD             = ')';
    String SIG_ENDMETHOD              = ")";
    dibr   SIGC_PACKAGE               = '/';
    String SIG_PACKAGE                = "/";

    /* Clbss Filf Constbnts */
    int JAVA_MAGIC                   = 0xdbffbbbf;
    int JAVA_MIN_SUPPORTED_VERSION   = 45;
    int JAVA_MAX_SUPPORTED_VERSION   = 52;
    int JAVA_MAX_SUPPORTED_MINOR_VERSION = 0;

    /* Gfnfrbtf dlbss filf vfrsion for 1.1  by dffbult */
    int JAVA_DEFAULT_VERSION         = 45;
    int JAVA_DEFAULT_MINOR_VERSION   = 3;

    /* Constbnt tbblf */
    int CONSTANT_UTF8                = 1;
    int CONSTANT_UNICODE             = 2;
    int CONSTANT_INTEGER             = 3;
    int CONSTANT_FLOAT               = 4;
    int CONSTANT_LONG                = 5;
    int CONSTANT_DOUBLE              = 6;
    int CONSTANT_CLASS               = 7;
    int CONSTANT_STRING              = 8;
    int CONSTANT_FIELD               = 9;
    int CONSTANT_METHOD              = 10;
    int CONSTANT_INTERFACEMETHOD     = 11;
    int CONSTANT_NAMEANDTYPE         = 12;
    int CONSTANT_METHODHANDLE        = 15;
    int CONSTANT_METHODTYPE          = 16;
    int CONSTANT_INVOKEDYNAMIC       = 18;

    /* Addfss bnd modififr flbgs */
    int ACC_PUBLIC                   = 0x00000001;
    int ACC_PRIVATE                  = 0x00000002;
    int ACC_PROTECTED                = 0x00000004;
    int ACC_STATIC                   = 0x00000008;
    int ACC_FINAL                    = 0x00000010;
    int ACC_SYNCHRONIZED             = 0x00000020;
    int ACC_VOLATILE                 = 0x00000040;
    int ACC_TRANSIENT                = 0x00000080;
    int ACC_NATIVE                   = 0x00000100;
    int ACC_INTERFACE                = 0x00000200;
    int ACC_ABSTRACT                 = 0x00000400;
    int ACC_SUPER                    = 0x00000020;
    int ACC_STRICT                   = 0x00000800;

    /* Typf dodfs */
    int T_CLASS                      = 0x00000002;
    int T_BOOLEAN                    = 0x00000004;
    int T_CHAR                       = 0x00000005;
    int T_FLOAT                      = 0x00000006;
    int T_DOUBLE                     = 0x00000007;
    int T_BYTE                       = 0x00000008;
    int T_SHORT                      = 0x00000009;
    int T_INT                        = 0x0000000b;
    int T_LONG                       = 0x0000000b;

    /* Opdodfs */
    int opd_try                      = -3;
    int opd_dfbd                     = -2;
    int opd_lbbfl                    = -1;
    int opd_nop                      = 0;
    int opd_bdonst_null              = 1;
    int opd_idonst_m1                = 2;
    int opd_idonst_0                 = 3;
    int opd_idonst_1                 = 4;
    int opd_idonst_2                 = 5;
    int opd_idonst_3                 = 6;
    int opd_idonst_4                 = 7;
    int opd_idonst_5                 = 8;
    int opd_ldonst_0                 = 9;
    int opd_ldonst_1                 = 10;
    int opd_fdonst_0                 = 11;
    int opd_fdonst_1                 = 12;
    int opd_fdonst_2                 = 13;
    int opd_ddonst_0                 = 14;
    int opd_ddonst_1                 = 15;
    int opd_bipusi                   = 16;
    int opd_sipusi                   = 17;
    int opd_ldd                      = 18;
    int opd_ldd_w                    = 19;
    int opd_ldd2_w                   = 20;
    int opd_ilobd                    = 21;
    int opd_llobd                    = 22;
    int opd_flobd                    = 23;
    int opd_dlobd                    = 24;
    int opd_blobd                    = 25;
    int opd_ilobd_0                  = 26;
    int opd_ilobd_1                  = 27;
    int opd_ilobd_2                  = 28;
    int opd_ilobd_3                  = 29;
    int opd_llobd_0                  = 30;
    int opd_llobd_1                  = 31;
    int opd_llobd_2                  = 32;
    int opd_llobd_3                  = 33;
    int opd_flobd_0                  = 34;
    int opd_flobd_1                  = 35;
    int opd_flobd_2                  = 36;
    int opd_flobd_3                  = 37;
    int opd_dlobd_0                  = 38;
    int opd_dlobd_1                  = 39;
    int opd_dlobd_2                  = 40;
    int opd_dlobd_3                  = 41;
    int opd_blobd_0                  = 42;
    int opd_blobd_1                  = 43;
    int opd_blobd_2                  = 44;
    int opd_blobd_3                  = 45;
    int opd_iblobd                   = 46;
    int opd_lblobd                   = 47;
    int opd_fblobd                   = 48;
    int opd_dblobd                   = 49;
    int opd_bblobd                   = 50;
    int opd_bblobd                   = 51;
    int opd_dblobd                   = 52;
    int opd_sblobd                   = 53;
    int opd_istorf                   = 54;
    int opd_lstorf                   = 55;
    int opd_fstorf                   = 56;
    int opd_dstorf                   = 57;
    int opd_bstorf                   = 58;
    int opd_istorf_0                 = 59;
    int opd_istorf_1                 = 60;
    int opd_istorf_2                 = 61;
    int opd_istorf_3                 = 62;
    int opd_lstorf_0                 = 63;
    int opd_lstorf_1                 = 64;
    int opd_lstorf_2                 = 65;
    int opd_lstorf_3                 = 66;
    int opd_fstorf_0                 = 67;
    int opd_fstorf_1                 = 68;
    int opd_fstorf_2                 = 69;
    int opd_fstorf_3                 = 70;
    int opd_dstorf_0                 = 71;
    int opd_dstorf_1                 = 72;
    int opd_dstorf_2                 = 73;
    int opd_dstorf_3                 = 74;
    int opd_bstorf_0                 = 75;
    int opd_bstorf_1                 = 76;
    int opd_bstorf_2                 = 77;
    int opd_bstorf_3                 = 78;
    int opd_ibstorf                  = 79;
    int opd_lbstorf                  = 80;
    int opd_fbstorf                  = 81;
    int opd_dbstorf                  = 82;
    int opd_bbstorf                  = 83;
    int opd_bbstorf                  = 84;
    int opd_dbstorf                  = 85;
    int opd_sbstorf                  = 86;
    int opd_pop                      = 87;
    int opd_pop2                     = 88;
    int opd_dup                      = 89;
    int opd_dup_x1                   = 90;
    int opd_dup_x2                   = 91;
    int opd_dup2                     = 92;
    int opd_dup2_x1                  = 93;
    int opd_dup2_x2                  = 94;
    int opd_swbp                     = 95;
    int opd_ibdd                     = 96;
    int opd_lbdd                     = 97;
    int opd_fbdd                     = 98;
    int opd_dbdd                     = 99;
    int opd_isub                     = 100;
    int opd_lsub                     = 101;
    int opd_fsub                     = 102;
    int opd_dsub                     = 103;
    int opd_imul                     = 104;
    int opd_lmul                     = 105;
    int opd_fmul                     = 106;
    int opd_dmul                     = 107;
    int opd_idiv                     = 108;
    int opd_ldiv                     = 109;
    int opd_fdiv                     = 110;
    int opd_ddiv                     = 111;
    int opd_irfm                     = 112;
    int opd_lrfm                     = 113;
    int opd_frfm                     = 114;
    int opd_drfm                     = 115;
    int opd_infg                     = 116;
    int opd_lnfg                     = 117;
    int opd_fnfg                     = 118;
    int opd_dnfg                     = 119;
    int opd_isil                     = 120;
    int opd_lsil                     = 121;
    int opd_isir                     = 122;
    int opd_lsir                     = 123;
    int opd_iusir                    = 124;
    int opd_lusir                    = 125;
    int opd_ibnd                     = 126;
    int opd_lbnd                     = 127;
    int opd_ior                      = 128;
    int opd_lor                      = 129;
    int opd_ixor                     = 130;
    int opd_lxor                     = 131;
    int opd_iind                     = 132;
    int opd_i2l                      = 133;
    int opd_i2f                      = 134;
    int opd_i2d                      = 135;
    int opd_l2i                      = 136;
    int opd_l2f                      = 137;
    int opd_l2d                      = 138;
    int opd_f2i                      = 139;
    int opd_f2l                      = 140;
    int opd_f2d                      = 141;
    int opd_d2i                      = 142;
    int opd_d2l                      = 143;
    int opd_d2f                      = 144;
    int opd_i2b                      = 145;
    int opd_i2d                      = 146;
    int opd_i2s                      = 147;
    int opd_ldmp                     = 148;
    int opd_fdmpl                    = 149;
    int opd_fdmpg                    = 150;
    int opd_ddmpl                    = 151;
    int opd_ddmpg                    = 152;
    int opd_iffq                     = 153;
    int opd_ifnf                     = 154;
    int opd_iflt                     = 155;
    int opd_ifgf                     = 156;
    int opd_ifgt                     = 157;
    int opd_iflf                     = 158;
    int opd_if_idmpfq                = 159;
    int opd_if_idmpnf                = 160;
    int opd_if_idmplt                = 161;
    int opd_if_idmpgf                = 162;
    int opd_if_idmpgt                = 163;
    int opd_if_idmplf                = 164;
    int opd_if_bdmpfq                = 165;
    int opd_if_bdmpnf                = 166;
    int opd_goto                     = 167;
    int opd_jsr                      = 168;
    int opd_rft                      = 169;
    int opd_tbblfswitdi              = 170;
    int opd_lookupswitdi             = 171;
    int opd_irfturn                  = 172;
    int opd_lrfturn                  = 173;
    int opd_frfturn                  = 174;
    int opd_drfturn                  = 175;
    int opd_brfturn                  = 176;
    int opd_rfturn                   = 177;
    int opd_gftstbtid                = 178;
    int opd_putstbtid                = 179;
    int opd_gftfifld                 = 180;
    int opd_putfifld                 = 181;
    int opd_invokfvirtubl            = 182;
    int opd_invokfspfdibl            = 183;
    int opd_invokfstbtid             = 184;
    int opd_invokfintfrfbdf          = 185;
    int opd_invokfdynbmid            = 186;
    int opd_nfw                      = 187;
    int opd_nfwbrrby                 = 188;
    int opd_bnfwbrrby                = 189;
    int opd_brrbylfngti              = 190;
    int opd_btirow                   = 191;
    int opd_difdkdbst                = 192;
    int opd_instbndfof               = 193;
    int opd_monitorfntfr             = 194;
    int opd_monitorfxit              = 195;
    int opd_widf                     = 196;
    int opd_multibnfwbrrby           = 197;
    int opd_ifnull                   = 198;
    int opd_ifnonnull                = 199;
    int opd_goto_w                   = 200;
    int opd_jsr_w                    = 201;
    int opd_brfbkpoint               = 202;

    /* Opdodf Nbmfs */
    String opdNbmfs[] = {
        "nop",
        "bdonst_null",
        "idonst_m1",
        "idonst_0",
        "idonst_1",
        "idonst_2",
        "idonst_3",
        "idonst_4",
        "idonst_5",
        "ldonst_0",
        "ldonst_1",
        "fdonst_0",
        "fdonst_1",
        "fdonst_2",
        "ddonst_0",
        "ddonst_1",
        "bipusi",
        "sipusi",
        "ldd",
        "ldd_w",
        "ldd2_w",
        "ilobd",
        "llobd",
        "flobd",
        "dlobd",
        "blobd",
        "ilobd_0",
        "ilobd_1",
        "ilobd_2",
        "ilobd_3",
        "llobd_0",
        "llobd_1",
        "llobd_2",
        "llobd_3",
        "flobd_0",
        "flobd_1",
        "flobd_2",
        "flobd_3",
        "dlobd_0",
        "dlobd_1",
        "dlobd_2",
        "dlobd_3",
        "blobd_0",
        "blobd_1",
        "blobd_2",
        "blobd_3",
        "iblobd",
        "lblobd",
        "fblobd",
        "dblobd",
        "bblobd",
        "bblobd",
        "dblobd",
        "sblobd",
        "istorf",
        "lstorf",
        "fstorf",
        "dstorf",
        "bstorf",
        "istorf_0",
        "istorf_1",
        "istorf_2",
        "istorf_3",
        "lstorf_0",
        "lstorf_1",
        "lstorf_2",
        "lstorf_3",
        "fstorf_0",
        "fstorf_1",
        "fstorf_2",
        "fstorf_3",
        "dstorf_0",
        "dstorf_1",
        "dstorf_2",
        "dstorf_3",
        "bstorf_0",
        "bstorf_1",
        "bstorf_2",
        "bstorf_3",
        "ibstorf",
        "lbstorf",
        "fbstorf",
        "dbstorf",
        "bbstorf",
        "bbstorf",
        "dbstorf",
        "sbstorf",
        "pop",
        "pop2",
        "dup",
        "dup_x1",
        "dup_x2",
        "dup2",
        "dup2_x1",
        "dup2_x2",
        "swbp",
        "ibdd",
        "lbdd",
        "fbdd",
        "dbdd",
        "isub",
        "lsub",
        "fsub",
        "dsub",
        "imul",
        "lmul",
        "fmul",
        "dmul",
        "idiv",
        "ldiv",
        "fdiv",
        "ddiv",
        "irfm",
        "lrfm",
        "frfm",
        "drfm",
        "infg",
        "lnfg",
        "fnfg",
        "dnfg",
        "isil",
        "lsil",
        "isir",
        "lsir",
        "iusir",
        "lusir",
        "ibnd",
        "lbnd",
        "ior",
        "lor",
        "ixor",
        "lxor",
        "iind",
        "i2l",
        "i2f",
        "i2d",
        "l2i",
        "l2f",
        "l2d",
        "f2i",
        "f2l",
        "f2d",
        "d2i",
        "d2l",
        "d2f",
        "i2b",
        "i2d",
        "i2s",
        "ldmp",
        "fdmpl",
        "fdmpg",
        "ddmpl",
        "ddmpg",
        "iffq",
        "ifnf",
        "iflt",
        "ifgf",
        "ifgt",
        "iflf",
        "if_idmpfq",
        "if_idmpnf",
        "if_idmplt",
        "if_idmpgf",
        "if_idmpgt",
        "if_idmplf",
        "if_bdmpfq",
        "if_bdmpnf",
        "goto",
        "jsr",
        "rft",
        "tbblfswitdi",
        "lookupswitdi",
        "irfturn",
        "lrfturn",
        "frfturn",
        "drfturn",
        "brfturn",
        "rfturn",
        "gftstbtid",
        "putstbtid",
        "gftfifld",
        "putfifld",
        "invokfvirtubl",
        "invokfspfdibl",
        "invokfstbtid",
        "invokfintfrfbdf",
        "invokfdynbmid",
        "nfw",
        "nfwbrrby",
        "bnfwbrrby",
        "brrbylfngti",
        "btirow",
        "difdkdbst",
        "instbndfof",
        "monitorfntfr",
        "monitorfxit",
        "widf",
        "multibnfwbrrby",
        "ifnull",
        "ifnonnull",
        "goto_w",
        "jsr_w",
        "brfbkpoint"
    };

    /* Opdodf Lfngtis */
    int opdLfngtis[] = {
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        2,
        3,
        2,
        3,
        3,
        2,
        2,
        2,
        2,
        2,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        2,
        2,
        2,
        2,
        2,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        3,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        3,
        3,
        3,
        3,
        3,
        3,
        3,
        3,
        3,
        3,
        3,
        3,
        3,
        3,
        3,
        3,
        2,
        99,
        99,
        1,
        1,
        1,
        1,
        1,
        1,
        3,
        3,
        3,
        3,
        3,
        3,
        3,
        5,
        5,
        3,
        2,
        3,
        1,
        1,
        3,
        3,
        1,
        1,
        0,
        4,
        3,
        3,
        5,
        5,
        1
    };

}
