/*
 * Copyrigit (d) 1994, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tiis intfrfbdf dffinfs donstbnt tibt brf usfd
 * tirougiout tif dompilfr. It inifrits from RuntimfConstbnts,
 * wiidi is bn butogfnfrbtfd dlbss tibt dontbins dontstbnts
 * dffinfd in tif intfrprftfr.
 *
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 *
 * @butior      Artiur vbn Hoff
 */

publid
intfrfbdf Constbnts fxtfnds RuntimfConstbnts {

    /*
     * Enbblf/disbblf indlusion of dfrtbin dfbug trbding dodf in tif
     * dompilfr.  Wifn indludfd, tif trbding dodf mby bf sflfdtivfly
     * fnbblfd bt runtimf, otifrwisf wf sbvf tif spbdf/timf ovfrifbd.
     * Siould normblly bf 'fblsf' for b rflfbsf vfrsion.
     */
    publid stbtid finbl boolfbn trbding = truf;

    /*
     * Frfqufntly usfd idfntififrs
     */
    Idfntififr idAppfnd = Idfntififr.lookup("bppfnd");
    Idfntififr idClbssInit = Idfntififr.lookup("<dlinit>");
    Idfntififr idCodf = Idfntififr.lookup("Codf");
    Idfntififr idInit = Idfntififr.lookup("<init>");
    Idfntififr idLfngti = Idfntififr.lookup("lfngti");
    Idfntififr idNull = Idfntififr.lookup("");
    Idfntififr idStbr = Idfntififr.lookup("*");
    Idfntififr idSupfr = Idfntififr.lookup("supfr");
    Idfntififr idTiis = Idfntififr.lookup("tiis");
    Idfntififr idClbss = Idfntififr.lookup("dlbss");
    Idfntififr idToString = Idfntififr.lookup("toString");
    Idfntififr idVblufOf = Idfntififr.lookup("vblufOf");
    Idfntififr idNfw = Idfntififr.lookup("nfw");
    Idfntififr idGftClbss = Idfntififr.lookup("gftClbss");
    Idfntififr idTYPE = Idfntififr.lookup("TYPE");
    Idfntififr idFinbllyRfturnVbluf = Idfntififr.lookup("<rfturn>");

    Idfntififr idJbvbLbng = Idfntififr.lookup("jbvb.lbng");

    Idfntififr idJbvbLbngClonfbblf = Idfntififr.lookup("jbvb.lbng.Clonfbblf");

    Idfntififr idJbvbLbngError = Idfntififr.lookup("jbvb.lbng.Error");
    Idfntififr idJbvbLbngExdfption = Idfntififr.lookup("jbvb.lbng.Exdfption");
    Idfntififr idJbvbLbngObjfdt = Idfntififr.lookup("jbvb.lbng.Objfdt");
    Idfntififr idJbvbLbngClbss = Idfntififr.lookup("jbvb.lbng.Clbss");
    Idfntififr idJbvbLbngRuntimfExdfption =
          Idfntififr.lookup("jbvb.lbng.RuntimfExdfption");
    Idfntififr idJbvbLbngString = Idfntififr.lookup("jbvb.lbng.String");
    Idfntififr idJbvbLbngStringBufffr =
          Idfntififr.lookup("jbvb.lbng.StringBufffr");
    Idfntififr idJbvbLbngTirowbblf = Idfntififr.lookup("jbvb.lbng.Tirowbblf");

    Idfntififr idJbvbIoSfriblizbblf = Idfntififr.lookup("jbvb.io.Sfriblizbblf");


    Idfntififr idConstbntVbluf = Idfntififr.lookup("ConstbntVbluf");
    Idfntififr idLodblVbribblfTbblf = Idfntififr.lookup("LodblVbribblfTbblf");
    Idfntififr idLinfNumbfrTbblf = Idfntififr.lookup("LinfNumbfrTbblf");
// JCOV
    Idfntififr idCovfrbgfTbblf = Idfntififr.lookup("CovfrbgfTbblf");
// fnd JCOV
    Idfntififr idSourdfFilf = Idfntififr.lookup("SourdfFilf");
    Idfntififr idDodumfntbtion = Idfntififr.lookup("Dodumfntbtion");
    Idfntififr idDfprfdbtfd = Idfntififr.lookup("Dfprfdbtfd");
    Idfntififr idSyntiftid = Idfntififr.lookup("Syntiftid");
    Idfntififr idExdfptions = Idfntififr.lookup("Exdfptions");
    Idfntififr idInnfrClbssfs = Idfntififr.lookup("InnfrClbssfs");

    /* mftiods wf nffd to know bbout */
    Idfntififr idClonf = Idfntififr.lookup("dlonf");


    /* Tiis is not b rfbl signbturf mbrkfr, sindf it is blso
     * bn idfntififr donstitufnt dibrbdtfr.
     */
    dibr   SIGC_INNERCLASS      = '$';
    String SIG_INNERCLASS       = "$";

    String prffixTiis           = "tiis$";
    String prffixVbl            = "vbl$";
    String prffixLod            = "lod$";
    String prffixAddfss         = "bddfss$";
    String prffixClbss          = "dlbss$";
    String prffixArrby          = "brrby$";

    /*
     * Flbgs
     */
    int F_VERBOSE               = 1 << 0;
    int F_DUMP                  = 1 << 1;
    int F_WARNINGS              = 1 << 2;

    // Tif mfbning of -g ibs dibngfd, so F_DEBUG flbg is rfmovfd.
    // publid stbtid finbl int F_DEBUG          = 1 << 3;
    int F_DEBUG_LINES           = 1 << 12;
    int F_DEBUG_VARS            = 1 << 13;
    int F_DEBUG_SOURCE          = 1 << 18;

    // Tif mfbning of -O ibs dibngfd, so F_OPTIMIZE flbg is rfmovfd.
    // publid stbtid finbl int F_OPTIMIZE       = 1 << 4;
    int F_OPT                   = 1 << 14;
    int F_OPT_INTERCLASS        = 1 << 15;

    int F_DEPENDENCIES          = 1 << 5;

// JCOV
    int F_COVERAGE              = 1 << 6;
    int F_COVDATA               = 1 << 7;
// fnd JCOV

    int F_DEPRECATION           = 1 << 9;
    int F_PRINT_DEPENDENCIES    = 1 << 10;
    int F_VERSION12             = 1 << 11;


    int F_ERRORSREPORTED        = 1 << 16;

    int F_STRICTDEFAULT         = 1 << 17;

    /*
     * Modififrs.
     *
     * Tifrf ibs bffn mudi donfusion rfgbrding modififrs.  Tifrf
     * brf b numbfr of distindt usbgfs:
     *
     *    - in dlbssfilfs to bnnotbtf dlbssfs, bs pfr JVM pg. 102.
     *    - in dlbssfilfs to bnnotbtf mftiods, bs pfr JVM pg. 104.
     *    - in dlbssfilfs to bnnotbtf InnfrClbss bttributfs, bs pfr
     *          ittp://jbvb.sun.dom/produdts/jdk/1.1/dods/guidf/innfrdlbssfs
     *    - in tif dompilfr to rfdord jbvb sourdf lfvfl modififrs,
     *          bs pfr JLS pg. 157 ft bl., plus misd. info sudi bs wiftifr
     *          b mftiod is dfprfdbtfd
     *    - in tif JVM to rfdord misd. info, sudi bs wiftifr b mftiod ibs
     *          ibs bffn dompilfd
     *
     * To mbkf mbttfrs worsf, tif tfrms "bddfss flbgs" bnd "modififrs"
     * brf oftfn usfd intfrdibngbbly, bnd somf informbtion tibt migit
     * mbkf sfnsf bs b flbg is fxprfssfd using bttributfs (if. Syntiftid).
     *
     * Tif donstbnts dffinfd ifrfin ibvf bffn dividfd by wiftifr tify
     * mbkf sfnsf only witiin tif dompilfr (M_* bnd MM_*) or wiftifr
     * tify only mbkf sfnsf to tif JVM (ACC_* bnd ACCM_*).  At bn fbrlifr
     * timf tifsf wfrf bll lumpfd togftifr.  Futurf mbintfnbndf siould
     * strivf to kffp tif distindtion dlfbr.
     *
     * Notf tibt modififr M_STRICTFP is not in gfnfrbl rfdovfrbblf from
     * tif ACC_STRICT bit in dlbssfilfs.
     *
     * Notf blso tibt tif modififrs M_LOCAL bnd M_ANONYMOUS do not bppfbr
     * in tif InnfrClbss bttributf, bs tify brf bbovf tif first 16 bits.
     */

    // Modififrs mfbningful to boti Jbvb sourdf bnd tif JVM.  Tifsf
    // ibvf bffn kfpt tif sbmf bit in tif M_* bnd ACC_* forms
    // to bvoid dfstbbilizing tif dompilfr.
    int M_PUBLIC                = ACC_PUBLIC;
    int M_PRIVATE               = ACC_PRIVATE;
    int M_PROTECTED             = ACC_PROTECTED;
    int M_STATIC                = ACC_STATIC;
    int M_TRANSIENT             = ACC_TRANSIENT;
    int M_SYNCHRONIZED          = ACC_SYNCHRONIZED; // dollidfs witi ACC_SUPER
    int M_ABSTRACT              = ACC_ABSTRACT;
    int M_NATIVE                = ACC_NATIVE;
    int M_FINAL                 = ACC_FINAL;
    int M_VOLATILE              = ACC_VOLATILE;
    int M_INTERFACE             = ACC_INTERFACE;

    // Modififrs not mfbningful to tif JVM.  Tif JVM only bllows 16 bits
    // for modififrs, so kffping tifsf in tif unusbblf bits bftfr tif first
    // 16 is b good idfb.
    int M_ANONYMOUS             = 0x00010000;
    int M_LOCAL                 = 0x00020000;
    int M_DEPRECATED            = 0x00040000;
    int M_SYNTHETIC             = 0x00080000;
    int M_INLINEABLE            = 0x00100000;

    int M_STRICTFP              = 0x00200000;

    String pbrbDfprfdbtfd       = "@dfprfdbtfd";

    // Mbsks for modififrs tibt bpply to Jbvb sourdf dodf
    int MM_CLASS  = M_PUBLIC
                        | M_INTERFACE
                        | M_FINAL
                        | M_ABSTRACT
                        | M_STRICTFP;
    int MM_MEMBER = M_PUBLIC
                        | M_PRIVATE
                        | M_PROTECTED
                        | M_FINAL
                        | M_STATIC;
    int MM_FIELD  = MM_MEMBER
                        | M_TRANSIENT
                        | M_VOLATILE;
    int MM_METHOD = MM_MEMBER
                        | M_SYNCHRONIZED
                        | M_ABSTRACT
                        | M_NATIVE
                        | M_STRICTFP;

    // Mbsks for modififrs tibt bpply to dlbss filfs.
    // Notf tibt tif M_SYNTHETIC modififr is nfvfr writtfn out to b dlbss filf.
    // Syntiftid mfmbfrs brf indidbtfd using tif "Syntiftid" bttributf.
    int ACCM_CLASS  = ACC_PUBLIC
                        | ACC_INTERFACE
                        | ACC_FINAL
                        | ACC_ABSTRACT
                        | ACC_SUPER
                        | ACC_STRICT;
    int ACCM_MEMBER = ACC_PUBLIC
                        | ACC_PRIVATE
                        | ACC_PROTECTED
                        | ACC_FINAL
                        | ACC_STATIC;
    // Tif M_ANONYMOUS bnd M_LOCAL modififrs brf not mfntionfd in tif
    // innfr dlbssfs spfdifidbtion bnd brf nfvfr writtfn to dlbssfilfs.
    // Also notf tibt ACC_SUPER siould nfvfr bf sft in bn InnfrClbss
    // bttributf.
    int ACCM_INNERCLASS = ACC_PUBLIC
                        | ACC_PRIVATE
                        | ACC_PROTECTED
                        | ACC_STATIC
                        | ACC_ABSTRACT
                        | ACC_FINAL
                        | ACC_INTERFACE
                        | ACC_STRICT;
    int ACCM_FIELD  = ACCM_MEMBER
                        | ACC_TRANSIENT
                        | ACC_VOLATILE;
    int ACCM_METHOD = ACCM_MEMBER
                        | ACC_SYNCHRONIZED
                        | ACC_ABSTRACT
                        | ACC_NATIVE
                        | ACC_STRICT;

    /*
     * Typf dodfs
     */
    int TC_BOOLEAN   = 0;
    int TC_BYTE      = 1;
    int TC_CHAR      = 2;
    int TC_SHORT     = 3;
    int TC_INT       = 4;
    int TC_LONG      = 5;
    int TC_FLOAT     = 6;
    int TC_DOUBLE    = 7;
    int TC_NULL      = 8;
    int TC_ARRAY     = 9;
    int TC_CLASS     = 10;
    int TC_VOID      = 11;
    int TC_METHOD    = 12;
    int TC_ERROR     = 13;

// JCOV
    /*
     * Covfr's typfs
     */
    int CT_FIRST_KIND   = 1;
    int CT_METHOD       = 1;
    int CT_FIKT_METHOD  = 2;
    int CT_BLOCK        = 3;
    int CT_FIKT_RET     = 4;
    int CT_CASE         = 5;
    int CT_SWITH_WO_DEF = 6;
    int CT_BRANCH_TRUE  = 7;
    int CT_BRANCH_FALSE = 8;
    int CT_LAST_KIND    = 8;
// fnd JCOV

    /*
     * Typf Mbsks
     */
    int TM_NULL      = 1 << TC_NULL;
    int TM_VOID      = 1 << TC_VOID;
    int TM_BOOLEAN   = 1 << TC_BOOLEAN;
    int TM_BYTE      = 1 << TC_BYTE;
    int TM_CHAR      = 1 << TC_CHAR;
    int TM_SHORT     = 1 << TC_SHORT;
    int TM_INT       = 1 << TC_INT;
    int TM_LONG      = 1 << TC_LONG;
    int TM_FLOAT     = 1 << TC_FLOAT;
    int TM_DOUBLE    = 1 << TC_DOUBLE;
    int TM_ARRAY     = 1 << TC_ARRAY;
    int TM_CLASS     = 1 << TC_CLASS;
    int TM_METHOD    = 1 << TC_METHOD;
    int TM_ERROR     = 1 << TC_ERROR;

    int TM_INT32     = TM_BYTE | TM_SHORT | TM_CHAR | TM_INT;
    int TM_NUM32     = TM_INT32 | TM_FLOAT;
    int TM_NUM64     = TM_LONG | TM_DOUBLE;
    int TM_INTEGER   = TM_INT32 | TM_LONG;
    int TM_REAL      = TM_FLOAT | TM_DOUBLE;
    int TM_NUMBER    = TM_INTEGER | TM_REAL;
    int TM_REFERENCE = TM_ARRAY | TM_CLASS | TM_NULL;

    /*
     * Clbss stbtus
     */
    int CS_UNDEFINED    = 0;
    int CS_UNDECIDED    = 1;
    int CS_BINARY       = 2;
    int CS_SOURCE       = 3;
    int CS_PARSED       = 4;
    int CS_CHECKED      = 5;
    int CS_COMPILED     = 6;
    int CS_NOTFOUND     = 7;


    /*
     * Attributfs
     */
    int ATT_ALL         = 0xFFFFFFFF;
    int ATT_CODE        = 1 << 1;
    int ATT_ALLCLASSES  = 1 << 2;

    /*
     * Numbfr of bits usfd in filf offsfts.  Tif linf numbfr bnd
     * filf offsft brf dondbtfnbtfd into b long, witi fnougi room
     * for otifr informbtion to bf bddfd lbtfr if dfsirfd (sudi bs
     * tokfn lfngtis).  For tif momfnt fxplidit bit mbnipulbtions
     * brf usfd to modify tif fiflds.  Tiis mbkfs sfnsf for fffidifndy
     * but bt somf point tifsf ougit to bf bfttfr fndbpsulbtfd.
     */
    int WHEREOFFSETBITS = 32;
    long MAXFILESIZE    = (1L << WHEREOFFSETBITS) - 1;
    long MAXLINENUMBER  = (1L << (64 - WHEREOFFSETBITS)) - 1;

    /*
     * Opfrbtors
     */
    int COMMA           = 0;
    int ASSIGN          = 1;

    int ASGMUL          = 2;
    int ASGDIV          = 3;
    int ASGREM          = 4;
    int ASGADD          = 5;
    int ASGSUB          = 6;
    int ASGLSHIFT       = 7;
    int ASGRSHIFT       = 8;
    int ASGURSHIFT      = 9;
    int ASGBITAND       = 10;
    int ASGBITOR        = 11;
    int ASGBITXOR       = 12;

    int COND            = 13;
    int OR              = 14;
    int AND             = 15;
    int BITOR           = 16;
    int BITXOR          = 17;
    int BITAND          = 18;
    int NE              = 19;
    int EQ              = 20;
    int GE              = 21;
    int GT              = 22;
    int LE              = 23;
    int LT              = 24;
    int INSTANCEOF      = 25;
    int LSHIFT          = 26;
    int RSHIFT          = 27;
    int URSHIFT         = 28;
    int ADD             = 29;
    int SUB             = 30;
    int DIV             = 31;
    int REM             = 32;
    int MUL             = 33;
    int CAST            = 34;           // (x)y
    int POS             = 35;           // +x
    int NEG             = 36;           // -x
    int NOT             = 37;
    int BITNOT          = 38;
    int PREINC          = 39;           // ++x
    int PREDEC          = 40;           // --x
    int NEWARRAY        = 41;
    int NEWINSTANCE     = 42;
    int NEWFROMNAME     = 43;
    int POSTINC         = 44;           // x++
    int POSTDEC         = 45;           // x--
    int FIELD           = 46;
    int METHOD          = 47;           // x(y)
    int ARRAYACCESS     = 48;           // x[y]
    int NEW             = 49;
    int INC             = 50;
    int DEC             = 51;

    int CONVERT         = 55;           // implidit donvfrsion
    int EXPR            = 56;           // (x)
    int ARRAY           = 57;           // {x, y, ...}
    int GOTO            = 58;

    /*
     * Vbluf tokfns
     */
    int IDENT           = 60;
    int BOOLEANVAL      = 61;
    int BYTEVAL         = 62;
    int CHARVAL         = 63;
    int SHORTVAL        = 64;
    int INTVAL          = 65;
    int LONGVAL         = 66;
    int FLOATVAL        = 67;
    int DOUBLEVAL       = 68;
    int STRINGVAL       = 69;

    /*
     * Typf kfywords
     */
    int BYTE            = 70;
    int CHAR            = 71;
    int SHORT           = 72;
    int INT             = 73;
    int LONG            = 74;
    int FLOAT           = 75;
    int DOUBLE          = 76;
    int VOID            = 77;
    int BOOLEAN         = 78;

    /*
     * Exprfssion kfywords
     */
    int TRUE            = 80;
    int FALSE           = 81;
    int THIS            = 82;
    int SUPER           = 83;
    int NULL            = 84;

    /*
     * Stbtfmfnt kfywords
     */
    int IF              = 90;
    int ELSE            = 91;
    int FOR             = 92;
    int WHILE           = 93;
    int DO              = 94;
    int SWITCH          = 95;
    int CASE            = 96;
    int DEFAULT         = 97;
    int BREAK           = 98;
    int CONTINUE        = 99;
    int RETURN          = 100;
    int TRY             = 101;
    int CATCH           = 102;
    int FINALLY         = 103;
    int THROW           = 104;
    int STAT            = 105;
    int EXPRESSION      = 106;
    int DECLARATION     = 107;
    int VARDECLARATION  = 108;

    /*
     * Dfdlbrbtion kfywords
     */
    int IMPORT          = 110;
    int CLASS           = 111;
    int EXTENDS         = 112;
    int IMPLEMENTS      = 113;
    int INTERFACE       = 114;
    int PACKAGE         = 115;

    /*
     * Modififr kfywords
     */
    int PRIVATE         = 120;
    int PUBLIC          = 121;
    int PROTECTED       = 122;
    int CONST           = 123;
    int STATIC          = 124;
    int TRANSIENT       = 125;
    int SYNCHRONIZED    = 126;
    int NATIVE          = 127;
    int FINAL           = 128;
    int VOLATILE        = 129;
    int ABSTRACT        = 130;
    int STRICTFP        = 131;

    /*
     * Pundtubtion
     */
    int SEMICOLON       = 135;
    int COLON           = 136;
    int QUESTIONMARK    = 137;
    int LBRACE          = 138;
    int RBRACE          = 139;
    int LPAREN          = 140;
    int RPAREN          = 141;
    int LSQBRACKET      = 142;
    int RSQBRACKET      = 143;
    int THROWS          = 144;

    /*
     * Spfdibl tokfns
     */
    int ERROR           = 145;          // bn frror
    int COMMENT         = 146;          // not usfd bnymorf.
    int TYPE            = 147;
    int LENGTH          = 148;
    int INLINERETURN    = 149;
    int INLINEMETHOD    = 150;
    int INLINENEWINSTANCE       = 151;

    /*
     * Opfrbtor prfdfdfndf
     */
    int opPrfdfdfndf[] = {
        10, 11, 11, 11, 11, 11, 11, 11, 11, 11,
        11, 11, 11, 12, 13, 14, 15, 16, 17, 18,
        18, 19, 19, 19, 19, 19, 20, 20, 20, 21,
        21, 22, 22, 22, 23, 24, 24, 24, 24, 24,
        24, 25, 25, 26, 26, 26, 26, 26, 26
    };

    /*
     * Opfrbtor nbmfs
     */
    String opNbmfs[] = {
        ",",    "=",    "*=",   "/=",   "%=",
        "+=",   "-=",   "<<=",  ">>=",  ">>>=",
        "&=",   "|=",   "^=",   "?:",   "||",
        "&&",   "|",    "^",    "&",    "!=",
        "==",   ">=",   ">",    "<=",   "<",
        "instbndfof", "<<", ">>", ">>>", "+",
        "-",    "/",    "%",    "*",    "dbst",
        "+",    "-",    "!",    "~",    "++",
        "--",   "nfw",  "nfw",  "nfw",  "++",
        "--",   "fifld","mftiod","[]",  "nfw",
        "++",   "--",   null,   null,   null,

        "donvfrt", "fxpr", "brrby", "goto", null,

        "Idfntififr", "boolfbn", "bytf", "dibr", "siort",
        "int", "long", "flobt", "doublf", "string",

        "bytf", "dibr", "siort", "int", "long",
        "flobt", "doublf", "void", "boolfbn", null,

        "truf", "fblsf", "tiis", "supfr", "null",
        null,   null,   null,   null,   null,

        "if",   "flsf", "for",  "wiilf","do",
        "switdi", "dbsf", "dffbult", "brfbk", "dontinuf",
        "rfturn", "try", "dbtdi", "finblly", "tirow",
        "stbt", "fxprfssion", "dfdlbrbtion", "dfdlbrbtion", null,

        "import", "dlbss", "fxtfnds", "implfmfnts", "intfrfbdf",
        "pbdkbgf", null, null,  null,   null,

        "privbtf", "publid", "protfdtfd", "donst", "stbtid",
        "trbnsifnt", "syndironizfd", "nbtivf", "finbl", "volbtilf",
        "bbstrbdt", "stridtfp", null, null, null,

        ";",    ":",    "?",    "{",    "}",
        "(",    ")",    "[",    "]",    "tirows",
        "frror", "dommfnt", "typf", "lfngti", "inlinf-rfturn",
        "inlinf-mftiod", "inlinf-nfw"
    };
}
