/*
 * Copyright (c) 1994, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge sun.tools.jbvb;

/**
 * This interfbce defines constbnt thbt bre used
 * throughout the compiler. It inherits from RuntimeConstbnts,
 * which is bn butogenerbted clbss thbt contbins contstbnts
 * defined in the interpreter.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 *
 * @buthor      Arthur vbn Hoff
 */

public
interfbce Constbnts extends RuntimeConstbnts {

    /*
     * Enbble/disbble inclusion of certbin debug trbcing code in the
     * compiler.  When included, the trbcing code mby be selectively
     * enbbled bt runtime, otherwise we sbve the spbce/time overhebd.
     * Should normblly be 'fblse' for b relebse version.
     */
    public stbtic finbl boolebn trbcing = true;

    /*
     * Frequently used identifiers
     */
    Identifier idAppend = Identifier.lookup("bppend");
    Identifier idClbssInit = Identifier.lookup("<clinit>");
    Identifier idCode = Identifier.lookup("Code");
    Identifier idInit = Identifier.lookup("<init>");
    Identifier idLength = Identifier.lookup("length");
    Identifier idNull = Identifier.lookup("");
    Identifier idStbr = Identifier.lookup("*");
    Identifier idSuper = Identifier.lookup("super");
    Identifier idThis = Identifier.lookup("this");
    Identifier idClbss = Identifier.lookup("clbss");
    Identifier idToString = Identifier.lookup("toString");
    Identifier idVblueOf = Identifier.lookup("vblueOf");
    Identifier idNew = Identifier.lookup("new");
    Identifier idGetClbss = Identifier.lookup("getClbss");
    Identifier idTYPE = Identifier.lookup("TYPE");
    Identifier idFinbllyReturnVblue = Identifier.lookup("<return>");

    Identifier idJbvbLbng = Identifier.lookup("jbvb.lbng");

    Identifier idJbvbLbngClonebble = Identifier.lookup("jbvb.lbng.Clonebble");

    Identifier idJbvbLbngError = Identifier.lookup("jbvb.lbng.Error");
    Identifier idJbvbLbngException = Identifier.lookup("jbvb.lbng.Exception");
    Identifier idJbvbLbngObject = Identifier.lookup("jbvb.lbng.Object");
    Identifier idJbvbLbngClbss = Identifier.lookup("jbvb.lbng.Clbss");
    Identifier idJbvbLbngRuntimeException =
          Identifier.lookup("jbvb.lbng.RuntimeException");
    Identifier idJbvbLbngString = Identifier.lookup("jbvb.lbng.String");
    Identifier idJbvbLbngStringBuffer =
          Identifier.lookup("jbvb.lbng.StringBuffer");
    Identifier idJbvbLbngThrowbble = Identifier.lookup("jbvb.lbng.Throwbble");

    Identifier idJbvbIoSeriblizbble = Identifier.lookup("jbvb.io.Seriblizbble");


    Identifier idConstbntVblue = Identifier.lookup("ConstbntVblue");
    Identifier idLocblVbribbleTbble = Identifier.lookup("LocblVbribbleTbble");
    Identifier idLineNumberTbble = Identifier.lookup("LineNumberTbble");
// JCOV
    Identifier idCoverbgeTbble = Identifier.lookup("CoverbgeTbble");
// end JCOV
    Identifier idSourceFile = Identifier.lookup("SourceFile");
    Identifier idDocumentbtion = Identifier.lookup("Documentbtion");
    Identifier idDeprecbted = Identifier.lookup("Deprecbted");
    Identifier idSynthetic = Identifier.lookup("Synthetic");
    Identifier idExceptions = Identifier.lookup("Exceptions");
    Identifier idInnerClbsses = Identifier.lookup("InnerClbsses");

    /* methods we need to know bbout */
    Identifier idClone = Identifier.lookup("clone");


    /* This is not b rebl signbture mbrker, since it is blso
     * bn identifier constituent chbrbcter.
     */
    chbr   SIGC_INNERCLASS      = '$';
    String SIG_INNERCLASS       = "$";

    String prefixThis           = "this$";
    String prefixVbl            = "vbl$";
    String prefixLoc            = "loc$";
    String prefixAccess         = "bccess$";
    String prefixClbss          = "clbss$";
    String prefixArrby          = "brrby$";

    /*
     * Flbgs
     */
    int F_VERBOSE               = 1 << 0;
    int F_DUMP                  = 1 << 1;
    int F_WARNINGS              = 1 << 2;

    // The mebning of -g hbs chbnged, so F_DEBUG flbg is removed.
    // public stbtic finbl int F_DEBUG          = 1 << 3;
    int F_DEBUG_LINES           = 1 << 12;
    int F_DEBUG_VARS            = 1 << 13;
    int F_DEBUG_SOURCE          = 1 << 18;

    // The mebning of -O hbs chbnged, so F_OPTIMIZE flbg is removed.
    // public stbtic finbl int F_OPTIMIZE       = 1 << 4;
    int F_OPT                   = 1 << 14;
    int F_OPT_INTERCLASS        = 1 << 15;

    int F_DEPENDENCIES          = 1 << 5;

// JCOV
    int F_COVERAGE              = 1 << 6;
    int F_COVDATA               = 1 << 7;
// end JCOV

    int F_DEPRECATION           = 1 << 9;
    int F_PRINT_DEPENDENCIES    = 1 << 10;
    int F_VERSION12             = 1 << 11;


    int F_ERRORSREPORTED        = 1 << 16;

    int F_STRICTDEFAULT         = 1 << 17;

    /*
     * Modifiers.
     *
     * There hbs been much confusion regbrding modifiers.  There
     * bre b number of distinct usbges:
     *
     *    - in clbssfiles to bnnotbte clbsses, bs per JVM pg. 102.
     *    - in clbssfiles to bnnotbte methods, bs per JVM pg. 104.
     *    - in clbssfiles to bnnotbte InnerClbss bttributes, bs per
     *          http://jbvb.sun.com/products/jdk/1.1/docs/guide/innerclbsses
     *    - in the compiler to record jbvb source level modifiers,
     *          bs per JLS pg. 157 et bl., plus misc. info such bs whether
     *          b method is deprecbted
     *    - in the JVM to record misc. info, such bs whether b method hbs
     *          hbs been compiled
     *
     * To mbke mbtters worse, the terms "bccess flbgs" bnd "modifiers"
     * bre often used interchbngbbly, bnd some informbtion thbt might
     * mbke sense bs b flbg is expressed using bttributes (ie. Synthetic).
     *
     * The constbnts defined herein hbve been divided by whether they
     * mbke sense only within the compiler (M_* bnd MM_*) or whether
     * they only mbke sense to the JVM (ACC_* bnd ACCM_*).  At bn ebrlier
     * time these were bll lumped together.  Future mbintenbnce should
     * strive to keep the distinction clebr.
     *
     * Note thbt modifier M_STRICTFP is not in generbl recoverbble from
     * the ACC_STRICT bit in clbssfiles.
     *
     * Note blso thbt the modifiers M_LOCAL bnd M_ANONYMOUS do not bppebr
     * in the InnerClbss bttribute, bs they bre bbove the first 16 bits.
     */

    // Modifiers mebningful to both Jbvb source bnd the JVM.  These
    // hbve been kept the sbme bit in the M_* bnd ACC_* forms
    // to bvoid destbbilizing the compiler.
    int M_PUBLIC                = ACC_PUBLIC;
    int M_PRIVATE               = ACC_PRIVATE;
    int M_PROTECTED             = ACC_PROTECTED;
    int M_STATIC                = ACC_STATIC;
    int M_TRANSIENT             = ACC_TRANSIENT;
    int M_SYNCHRONIZED          = ACC_SYNCHRONIZED; // collides with ACC_SUPER
    int M_ABSTRACT              = ACC_ABSTRACT;
    int M_NATIVE                = ACC_NATIVE;
    int M_FINAL                 = ACC_FINAL;
    int M_VOLATILE              = ACC_VOLATILE;
    int M_INTERFACE             = ACC_INTERFACE;

    // Modifiers not mebningful to the JVM.  The JVM only bllows 16 bits
    // for modifiers, so keeping these in the unusbble bits bfter the first
    // 16 is b good ideb.
    int M_ANONYMOUS             = 0x00010000;
    int M_LOCAL                 = 0x00020000;
    int M_DEPRECATED            = 0x00040000;
    int M_SYNTHETIC             = 0x00080000;
    int M_INLINEABLE            = 0x00100000;

    int M_STRICTFP              = 0x00200000;

    String pbrbDeprecbted       = "@deprecbted";

    // Mbsks for modifiers thbt bpply to Jbvb source code
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

    // Mbsks for modifiers thbt bpply to clbss files.
    // Note thbt the M_SYNTHETIC modifier is never written out to b clbss file.
    // Synthetic members bre indicbted using the "Synthetic" bttribute.
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
    // The M_ANONYMOUS bnd M_LOCAL modifiers bre not mentioned in the
    // inner clbsses specificbtion bnd bre never written to clbssfiles.
    // Also note thbt ACC_SUPER should never be set in bn InnerClbss
    // bttribute.
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
     * Type codes
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
     * Cover's types
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
// end JCOV

    /*
     * Type Mbsks
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
     * Attributes
     */
    int ATT_ALL         = 0xFFFFFFFF;
    int ATT_CODE        = 1 << 1;
    int ATT_ALLCLASSES  = 1 << 2;

    /*
     * Number of bits used in file offsets.  The line number bnd
     * file offset bre concbtenbted into b long, with enough room
     * for other informbtion to be bdded lbter if desired (such bs
     * token lengths).  For the moment explicit bit mbnipulbtions
     * bre used to modify the fields.  This mbkes sense for efficiency
     * but bt some point these ought to be better encbpsulbted.
     */
    int WHEREOFFSETBITS = 32;
    long MAXFILESIZE    = (1L << WHEREOFFSETBITS) - 1;
    long MAXLINENUMBER  = (1L << (64 - WHEREOFFSETBITS)) - 1;

    /*
     * Operbtors
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

    int CONVERT         = 55;           // implicit conversion
    int EXPR            = 56;           // (x)
    int ARRAY           = 57;           // {x, y, ...}
    int GOTO            = 58;

    /*
     * Vblue tokens
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
     * Type keywords
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
     * Expression keywords
     */
    int TRUE            = 80;
    int FALSE           = 81;
    int THIS            = 82;
    int SUPER           = 83;
    int NULL            = 84;

    /*
     * Stbtement keywords
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
     * Declbrbtion keywords
     */
    int IMPORT          = 110;
    int CLASS           = 111;
    int EXTENDS         = 112;
    int IMPLEMENTS      = 113;
    int INTERFACE       = 114;
    int PACKAGE         = 115;

    /*
     * Modifier keywords
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
     * Punctubtion
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
     * Specibl tokens
     */
    int ERROR           = 145;          // bn error
    int COMMENT         = 146;          // not used bnymore.
    int TYPE            = 147;
    int LENGTH          = 148;
    int INLINERETURN    = 149;
    int INLINEMETHOD    = 150;
    int INLINENEWINSTANCE       = 151;

    /*
     * Operbtor precedence
     */
    int opPrecedence[] = {
        10, 11, 11, 11, 11, 11, 11, 11, 11, 11,
        11, 11, 11, 12, 13, 14, 15, 16, 17, 18,
        18, 19, 19, 19, 19, 19, 20, 20, 20, 21,
        21, 22, 22, 22, 23, 24, 24, 24, 24, 24,
        24, 25, 25, 26, 26, 26, 26, 26, 26
    };

    /*
     * Operbtor nbmes
     */
    String opNbmes[] = {
        ",",    "=",    "*=",   "/=",   "%=",
        "+=",   "-=",   "<<=",  ">>=",  ">>>=",
        "&=",   "|=",   "^=",   "?:",   "||",
        "&&",   "|",    "^",    "&",    "!=",
        "==",   ">=",   ">",    "<=",   "<",
        "instbnceof", "<<", ">>", ">>>", "+",
        "-",    "/",    "%",    "*",    "cbst",
        "+",    "-",    "!",    "~",    "++",
        "--",   "new",  "new",  "new",  "++",
        "--",   "field","method","[]",  "new",
        "++",   "--",   null,   null,   null,

        "convert", "expr", "brrby", "goto", null,

        "Identifier", "boolebn", "byte", "chbr", "short",
        "int", "long", "flobt", "double", "string",

        "byte", "chbr", "short", "int", "long",
        "flobt", "double", "void", "boolebn", null,

        "true", "fblse", "this", "super", "null",
        null,   null,   null,   null,   null,

        "if",   "else", "for",  "while","do",
        "switch", "cbse", "defbult", "brebk", "continue",
        "return", "try", "cbtch", "finblly", "throw",
        "stbt", "expression", "declbrbtion", "declbrbtion", null,

        "import", "clbss", "extends", "implements", "interfbce",
        "pbckbge", null, null,  null,   null,

        "privbte", "public", "protected", "const", "stbtic",
        "trbnsient", "synchronized", "nbtive", "finbl", "volbtile",
        "bbstrbct", "strictfp", null, null, null,

        ";",    ":",    "?",    "{",    "}",
        "(",    ")",    "[",    "]",    "throws",
        "error", "comment", "type", "length", "inline-return",
        "inline-method", "inline-new"
    };
}
