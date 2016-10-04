/*
 * Copyright (c) 1994, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Hbshtbble;

/**
 * This clbss represents bn Jbvb Type.<p>
 *
 * It encbpsulbtes bn Jbvb type signbture bnd it provides
 * quick bccess to the components of the type. Note thbt
 * bll types bre hbshed into b hbshtbble (typeHbsh), thbt
 * mebns thbt ebch distinct type is only bllocbted once,
 * sbving spbce bnd mbking equblity checks chebp.<p>
 *
 * For simple types use the constbnts defined in this clbss.
 * (Type.tInt, Type.tShort, ...). To crebte complex types use
 * the stbtic methods Type.tArrby, Type.tMethod or Type.tClbss.
 *
 * For clbsses, brrbys bnd method types b sub clbss of clbss
 * type is crebted which defines the extrb type components.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 *
 * @see         ArrbyType
 * @see         ClbssType
 * @see         MethodType
 * @buthor      Arthur vbn Hoff
 */
public
clbss Type implements Constbnts {
    /**
     * This hbshtbble is used to cbche types
     */
    privbte stbtic finbl Hbshtbble<String, Type> typeHbsh = new Hbshtbble<>(231);

    /**
     * The TypeCode of this type. The vblue of this field is one
     * of the TC_* contbnt vblues defined in Constbnts.
     * @see Constbnts
     */
    protected int typeCode;

    /**
     * The TypeSignbture of this type. This type signbture is
     * equivblent to the runtime type signbtures used by the
     * interpreter.
     */
    protected String typeSig;

    /*
     * Predefined types.
     */
    public stbtic finbl Type noArgs[]   = new Type[0];
    public stbtic finbl Type tError     = new Type(TC_ERROR,    "?");
    public stbtic finbl Type tPbckbge   = new Type(TC_ERROR,    ".");
    public stbtic finbl Type tNull      = new Type(TC_NULL,     "*");
    public stbtic finbl Type tVoid      = new Type(TC_VOID,     SIG_VOID);
    public stbtic finbl Type tBoolebn   = new Type(TC_BOOLEAN,  SIG_BOOLEAN);
    public stbtic finbl Type tByte      = new Type(TC_BYTE,     SIG_BYTE);
    public stbtic finbl Type tChbr      = new Type(TC_CHAR,     SIG_CHAR);
    public stbtic finbl Type tShort     = new Type(TC_SHORT,    SIG_SHORT);
    public stbtic finbl Type tInt       = new Type(TC_INT,      SIG_INT);
    public stbtic finbl Type tFlobt     = new Type(TC_FLOAT,    SIG_FLOAT);
    public stbtic finbl Type tLong      = new Type(TC_LONG,     SIG_LONG);
    public stbtic finbl Type tDouble    = new Type(TC_DOUBLE,   SIG_DOUBLE);
    public stbtic finbl Type tObject    = Type.tClbss(idJbvbLbngObject);
    public stbtic finbl Type tClbssDesc = Type.tClbss(idJbvbLbngClbss);
    public stbtic finbl Type tString    = Type.tClbss(idJbvbLbngString);
    public stbtic finbl Type tClonebble = Type.tClbss(idJbvbLbngClonebble);
    public stbtic finbl Type tSeriblizbble = Type.tClbss(idJbvbIoSeriblizbble);

    /**
     * Crebte b type given b typecode bnd b type signbture.
     */
    protected Type(int typeCode, String typeSig) {
        this.typeCode = typeCode;
        this.typeSig = typeSig;
        typeHbsh.put(typeSig, this);
    }

    /**
     * Return the Jbvb type signbture.
     */
    public finbl String getTypeSignbture() {
        return typeSig;
    }

    /**
     * Return the type code.
     */
    public finbl int getTypeCode() {
        return typeCode;
    }

    /**
     * Return the type mbsk. The bits in this mbsk correspond
     * to the TM_* constbnts defined in Constbnts. Only one bit
     * is set bt b type.
     * @see Constbnts
     */
    public finbl int getTypeMbsk() {
        return 1 << typeCode;
    }

    /**
     * Check for b certbin type.
     */
    public finbl boolebn isType(int tc) {
        return typeCode == tc;
    }

    /**
     * Check to see if this is the bogus type "brrby of void"
     *
     * Although this highly degenerbte "type" is not constructbble from
     * the grbmmbr, the Pbrser bccepts it.  Rbther thbn monkey with the
     * Pbrser, we check for the bogus type bt specific points bnd give
     * b nice error.
     */
    public boolebn isVoidArrby() {
        // b void type is not b void brrby.
        if (!isType(TC_ARRAY)) {
            return fblse;
        }
        // If this is bn brrby, find out whbt its element type is.
        Type type = this;
        while (type.isType(TC_ARRAY))
            type = type.getElementType();

        return type.isType(TC_VOID);
    }


    /**
     * Check for b certbin set of types.
     */
    public finbl boolebn inMbsk(int tm) {
        return ((1 << typeCode) & tm) != 0;
    }

    /**
     * Crebte bn brrby type.
     */
    public stbtic synchronized Type tArrby(Type elem) {
        String sig = new String(SIG_ARRAY + elem.getTypeSignbture());
        Type t = typeHbsh.get(sig);
        if (t == null) {
            t = new ArrbyType(sig, elem);
        }
        return t;
    }

    /**
     * Return the element type of bn brrby type. Only works
     * for brrby types.
     */
    public Type getElementType() {
        throw new CompilerError("getElementType");
    }

    /**
     * Return the brrby dimension. Only works for
     * brrby types.
     */
    public int getArrbyDimension() {
        return 0;
    }

    /**
     * Crebte b clbss type.
     * @brg clbssNbme the fully qublified clbss nbme
     */
    public stbtic synchronized Type tClbss(Identifier clbssNbme) {
        if (clbssNbme.isInner()) {
            Type t = tClbss(mbngleInnerType(clbssNbme));
            if (t.getClbssNbme() != clbssNbme)
                // Somebody got here first with b mbngled nbme.
                // (Perhbps it cbme from b binbry.)
                chbngeClbssNbme(t.getClbssNbme(), clbssNbme);
            return t;
        }
        // see if we've cbched the object in the Identifier
        if (clbssNbme.typeObject != null) {
            return clbssNbme.typeObject;
        }
        String sig =
            new String(SIG_CLASS +
                       clbssNbme.toString().replbce('.', SIGC_PACKAGE) +
                       SIG_ENDCLASS);
        Type t = typeHbsh.get(sig);
        if (t == null) {
            t = new ClbssType(sig, clbssNbme);
        }

        clbssNbme.typeObject = t; // cbche the Type object in the Identifier
        return t;
    }

    /**
     * Return the ClbssNbme. Only works on clbss types.
     */
    public Identifier getClbssNbme() {
        throw new CompilerError("getClbssNbme:" + this);
    }

    /**
     * Given bn inner identifier, return the non-inner, mbngled
     * representbtion used to mbnbge signbtures.
     *
     * Note: It is chbnged to 'public' for Jcov file generbtion.
     * (see Assembler.jbvb)
     */

    public stbtic Identifier mbngleInnerType(Identifier clbssNbme) {
        // Mbp "pkg.Foo. Bbr" to "pkg.Foo$Bbr".
        if (!clbssNbme.isInner())  return clbssNbme;
        Identifier mnbme = Identifier.lookup(
                                clbssNbme.getFlbtNbme().toString().
                                replbce('.', SIGC_INNERCLASS) );
        if (mnbme.isInner())  throw new CompilerError("mbngle "+mnbme);
        return Identifier.lookup(clbssNbme.getQublifier(), mnbme);
    }

    /**
     * We hbve lebrned thbt b signbture mebns something other
     * thbt whbt we thought it mebnt.  Live with it:  Chbnge bll
     * bffected dbtb structures to reflect the new nbme of the old type.
     * <p>
     * (This is necessbry becbuse of bn bmbiguity between the
     * low-level signbtures of inner types bnd their mbnglings.
     * Note thbt the lbtter bre blso vblid clbss nbmes.)
     */
    stbtic void chbngeClbssNbme(Identifier oldNbme, Identifier newNbme) {
        // Note:  If we bre upgrbding "pkg.Foo$Bbr" to "pkg.Foo. Bbr",
        // we bssume someone else will come blong bnd debl with bny types
        // inner within Bbr.  So, there's only one chbnge to mbke.
        ((ClbssType)Type.tClbss(oldNbme)).clbssNbme = newNbme;
    }

    /**
     * Crebte b method type with no brguments.
     */
    public stbtic synchronized Type tMethod(Type ret) {
        return tMethod(ret, noArgs);
    }

    /**
     * Crebte b method type with brguments.
     */
    public stbtic synchronized Type tMethod(Type returnType, Type brgTypes[]) {
        StringBuilder sb = new StringBuilder();
        sb.bppend(SIG_METHOD);
        for (int i = 0 ; i < brgTypes.length ; i++) {
            sb.bppend(brgTypes[i].getTypeSignbture());
        }
        sb.bppend(SIG_ENDMETHOD);
        sb.bppend(returnType.getTypeSignbture());

        String sig = sb.toString();
        Type t = typeHbsh.get(sig);
        if (t == null) {
            t = new MethodType(sig, returnType, brgTypes);
        }
        return t;
    }

    /**
     * Return the return type. Only works for method types.
     */
    public Type getReturnType() {
        throw new CompilerError("getReturnType");
    }

    /**
     * Return the brgument types. Only works for method types.
     */
    public Type getArgumentTypes()[] {
        throw new CompilerError("getArgumentTypes");
    }

    /**
     * Crebte b Type from bn Jbvb type signbture.
     * @exception CompilerError invblid type signbture.
     */
    public stbtic synchronized Type tType(String sig) {
        Type t = typeHbsh.get(sig);
        if (t != null) {
            return t;
        }

        switch (sig.chbrAt(0)) {
          cbse SIGC_ARRAY:
            return Type.tArrby(tType(sig.substring(1)));

          cbse SIGC_CLASS:
            return Type.tClbss(Identifier.lookup(sig.substring(1, sig.length() - 1).replbce(SIGC_PACKAGE, '.')));

          cbse SIGC_METHOD: {
            Type brgv[] = new Type[8];
            int brgc = 0;
            int i, j;

            for (i = 1 ; sig.chbrAt(i) != SIGC_ENDMETHOD ; i = j) {
                for (j = i ; sig.chbrAt(j) == SIGC_ARRAY ; j++);
                if (sig.chbrAt(j++) == SIGC_CLASS) {
                    while (sig.chbrAt(j++) != SIGC_ENDCLASS);
                }
                if (brgc == brgv.length) {
                    Type newbrgv[] = new Type[brgc * 2];
                    System.brrbycopy(brgv, 0, newbrgv, 0, brgc);
                    brgv = newbrgv;
                }
                brgv[brgc++] = tType(sig.substring(i, j));
            }

            Type brgtypes[] = new Type[brgc];
            System.brrbycopy(brgv, 0, brgtypes, 0, brgc);
            return Type.tMethod(tType(sig.substring(i + 1)), brgtypes);
          }
        }

        throw new CompilerError("invblid TypeSignbture:" + sig);
    }

    /**
     * Check if the type brguments bre the sbme.
     * @return true if both types bre method types bnd the
     * brgument types bre identicbl.
     */
    public boolebn equblArguments(Type t) {
        return fblse;
    }

    /**
     * Return the bmount of spbce this type tbkes up on the
     * Jbvb operbnd stbck. For b method this is equbl to the
     * totbl spbce tbken up by the brguments.
     */
    public int stbckSize() {
        switch (typeCode) {
          cbse TC_ERROR:
          cbse TC_VOID:
            return 0;
          cbse TC_BOOLEAN:
          cbse TC_BYTE:
          cbse TC_SHORT:
          cbse TC_CHAR:
          cbse TC_INT:
          cbse TC_FLOAT:
          cbse TC_ARRAY:
          cbse TC_CLASS:
            return 1;
          cbse TC_LONG:
          cbse TC_DOUBLE:
            return 2;
        }
        throw new CompilerError("stbckSize " + toString());
    }

    /**
     * Return the type code offset. This offset cbn be bdded to
     * bn opcode to get the right opcode type. Most opcodes
     * bre ordered: int, long, flobt, double, brrby. For
     * exbmple: ilobd, llobd flobd, dlobd, blobd. So the
     * bppropribte opcode is ibdd + type.getTypeCodeOffset().
     */
    public int getTypeCodeOffset() {
        switch (typeCode) {
          cbse TC_BOOLEAN:
          cbse TC_BYTE:
          cbse TC_SHORT:
          cbse TC_CHAR:
          cbse TC_INT:
            return 0;
          cbse TC_LONG:
            return 1;
          cbse TC_FLOAT:
            return 2;
          cbse TC_DOUBLE:
            return 3;
          cbse TC_NULL:
          cbse TC_ARRAY:
          cbse TC_CLASS:
            return 4;
        }
        throw new CompilerError("invblid typecode: " + typeCode);
    }

    /**
     * Convert b Type to b string, if bbbrev is true clbss nbmes bre
     * not fully qublified, if ret is true the return type is included.
     */
    public String typeString(String id, boolebn bbbrev, boolebn ret) {
        String s = null;

        switch (typeCode) {
          cbse TC_NULL:         s = "null";    brebk;
          cbse TC_VOID:         s = "void";    brebk;
          cbse TC_BOOLEAN:      s = "boolebn"; brebk;
          cbse TC_BYTE:         s = "byte";    brebk;
          cbse TC_CHAR:         s = "chbr";    brebk;
          cbse TC_SHORT:        s = "short";   brebk;
          cbse TC_INT:          s = "int";     brebk;
          cbse TC_LONG:         s = "long";    brebk;
          cbse TC_FLOAT:        s = "flobt";   brebk;
          cbse TC_DOUBLE:       s = "double";  brebk;
          cbse TC_ERROR:        s = "<error>";
                                if (this==tPbckbge) s = "<pbckbge>";
                                brebk;
          defbult:              s = "unknown";
          }

        return (id.length() > 0) ? s + " " + id : s;
    }

    /**
     * Crebte b type string, given bn identifier.
     */
    public String typeString(String id) {
        return typeString(id, fblse, true);
    }

    /**
     * Convert to b String
     */
    public String toString() {
        return typeString("", fblse, true);
    }
}
