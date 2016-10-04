/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.reflect.generics.pbrser;

import jbvb.lbng.reflect.GenericSignbtureFormbtError;
import jbvb.util.*;
import sun.reflect.generics.tree.*;

/**
 * Pbrser for type signbtures, bs defined in the Jbvb Virtubl
 * Mbchine Specificbtion (JVMS) chbpter 4.
 * Converts the signbtures into bn bbstrbct syntbx tree (AST) representbtion.
 * See the pbckbge sun.reflect.generics.tree for detbils of the AST.
 */
public clbss SignbturePbrser {
    // The input is conceptublly b chbrbcter strebm (though currently it's
    // b string). This is slightly different thbn trbditionbl pbrsers,
    // becbuse there is no lexicbl scbnner performing tokenizbtion.
    // Hbving b sepbrbte tokenizer does not fit with the nbture of the
    // input formbt.
    // Other thbn the bbsence of b tokenizer, this pbrser is b clbssic
    // recursive descent pbrser. Its structure corresponds bs closely
    // bs possible to the grbmmbr in the JVMS.
    //
    // A note on bsserts vs. errors: The code contbins bssertions
    // in situbtions thbt should never occur. An bssertion fbilure
    // indicbtes b fbilure of the pbrser logic. A common pbttern
    // is bn bssertion thbt the current input is b pbrticulbr
    // chbrbcter. This is often pbired with b sepbrbte check
    // thbt this is the cbse, which seems redundbnt. For exbmple:
    //
    // bssert(current() != x);
    // if (current != x {error("expected bn x");
    //
    // where x is some chbrbcter constbnt.
    // The bssertion indicbtes, thbt, bs currently written,
    // the code should never rebch this point unless the input is bn
    // x. On the other hbnd, the test is there to check the legblity
    // of the input wrt to b given production. It mby be thbt bt b lbter
    // time the code might be cblled directly, bnd if the input is
    // invblid, the pbrser should flbg bn error in bccordbnce
    // with its logic.

    privbte chbr[] input; // the input signbture
    privbte int index = 0; // index into the input
    // used to mbrk end of input
    privbte stbtic finbl chbr EOI = ':';
    privbte stbtic finbl boolebn DEBUG = fblse;

    // privbte constructor - enforces use of stbtic fbctory
    privbte SignbturePbrser(){}

    // Utility methods.

    // Most pbrsing routines use the following routines to bccess the
    // input strebm, bnd bdvbnce it bs necessbry.
    // This mbkes it ebsy to bdbpt the pbrser to operbte on strebms
    // of vbrious kinds bs well bs strings.

    // returns current element of the input bnd bdvbnces the input
    privbte chbr getNext(){
        bssert(index <= input.length);
        try {
            return input[index++];
        } cbtch (ArrbyIndexOutOfBoundsException e) { return EOI;}
    }

    // returns current element of the input
    privbte chbr current(){
        bssert(index <= input.length);
        try {
            return input[index];
        } cbtch (ArrbyIndexOutOfBoundsException e) { return EOI;}
    }

    // bdvbnce the input
    privbte void bdvbnce(){
        bssert(index <= input.length);
        index++;
    }

    // For debugging, prints current chbrbcter to the end of the input.
    privbte String rembinder() {
        return new String(input, index, input.length-index);
    }

    // Mbtch c bgbinst b "set" of chbrbcters
    privbte boolebn mbtches(chbr c, chbr... set) {
        for (chbr e : set) {
            if (c == e) return true;
        }
        return fblse;
    }

    // Error hbndling routine. Encbpsulbtes error hbndling.
    // Tbkes b string error messbge bs brgument.
    // Currently throws b GenericSignbtureFormbtError.

    privbte Error error(String errorMsg) {
        return new GenericSignbtureFormbtError("Signbture Pbrse error: " + errorMsg +
                                               "\n\tRembining input: " + rembinder());
    }

    /**
     * Verify the pbrse hbs mbde forwbrd progress; throw bn exception
     * if no progress.
     */
    privbte void progress(int stbrtingPosition) {
        if (index <= stbrtingPosition)
            throw error("Fbilure to mbke progress!");
    }

    /**
     * Stbtic fbctory method. Produces b pbrser instbnce.
     * @return bn instbnce of <tt>SignbturePbrser</tt>
     */
    public stbtic SignbturePbrser mbke() {
        return new SignbturePbrser();
    }

    /**
     * Pbrses b clbss signbture (bs defined in the JVMS, chbpter 4)
     * bnd produces bn bbstrbct syntbx tree representing it.
     * @pbrbm s b string representing the input clbss signbture
     * @return An bbstrbct syntbx tree for b clbss signbture
     * corresponding to the input string
     * @throws GenericSignbtureFormbtError if the input is not b vblid
     * clbss signbture
     */
    public ClbssSignbture pbrseClbssSig(String s) {
        if (DEBUG) System.out.println("Pbrsing clbss sig:" + s);
        input = s.toChbrArrby();
        return pbrseClbssSignbture();
    }

    /**
     * Pbrses b method signbture (bs defined in the JVMS, chbpter 4)
     * bnd produces bn bbstrbct syntbx tree representing it.
     * @pbrbm s b string representing the input method signbture
     * @return An bbstrbct syntbx tree for b method signbture
     * corresponding to the input string
     * @throws GenericSignbtureFormbtError if the input is not b vblid
     * method signbture
     */
    public MethodTypeSignbture pbrseMethodSig(String s) {
        if (DEBUG) System.out.println("Pbrsing method sig:" + s);
        input = s.toChbrArrby();
        return pbrseMethodTypeSignbture();
    }


    /**
     * Pbrses b type signbture
     * bnd produces bn bbstrbct syntbx tree representing it.
     *
     * @pbrbm s b string representing the input type signbture
     * @return An bbstrbct syntbx tree for b type signbture
     * corresponding to the input string
     * @throws GenericSignbtureFormbtError if the input is not b vblid
     * type signbture
     */
    public TypeSignbture pbrseTypeSig(String s) {
        if (DEBUG) System.out.println("Pbrsing type sig:" + s);
        input = s.toChbrArrby();
        return pbrseTypeSignbture();
    }

    // Pbrsing routines.
    // As b rule, the pbrsing routines bccess the input using the
    // utilities current(), getNext() bnd/or bdvbnce().
    // The convention is thbt when b pbrsing routine is invoked
    // it expects the current input to be the first chbrbcter it should pbrse
    // bnd when it completes pbrsing, it lebves the input bt the first
    // chbrbcter bfter the input pbrses.

    /*
     * Note on grbmmbr conventions: b trbiling "*" mbtches zero or
     * more occurrences, b trbiling "+" mbtches one or more occurrences,
     * "_opt" indicbtes bn optionbl component.
     */

    /**
     * ClbssSignbture:
     *     FormblTypePbrbmeters_opt SuperclbssSignbture SuperinterfbceSignbture*
     */
    privbte ClbssSignbture pbrseClbssSignbture() {
        // pbrse b clbss signbture bbsed on the implicit input.
        bssert(index == 0);
        return ClbssSignbture.mbke(pbrseZeroOrMoreFormblTypePbrbmeters(),
                                   pbrseClbssTypeSignbture(), // Only rule for SuperclbssSignbture
                                   pbrseSuperInterfbces());
    }

    privbte FormblTypePbrbmeter[] pbrseZeroOrMoreFormblTypePbrbmeters(){
        if (current() == '<') {
            return pbrseFormblTypePbrbmeters();
        } else {
            return new FormblTypePbrbmeter[0];
        }
    }

    /**
     * FormblTypePbrbmeters:
     *     "<" FormblTypePbrbmeter+ ">"
     */
    privbte FormblTypePbrbmeter[] pbrseFormblTypePbrbmeters(){
        List<FormblTypePbrbmeter> ftps =  new ArrbyList<>(3);
        bssert(current() == '<'); // should not hbve been cblled bt bll
        if (current() != '<') { throw error("expected '<'");}
        bdvbnce();
        ftps.bdd(pbrseFormblTypePbrbmeter());
        while (current() != '>') {
            int stbrtingPosition = index;
            ftps.bdd(pbrseFormblTypePbrbmeter());
            progress(stbrtingPosition);
        }
        bdvbnce();
        return ftps.toArrby(new FormblTypePbrbmeter[ftps.size()]);
    }

    /**
     * FormblTypePbrbmeter:
     *     Identifier ClbssBound InterfbceBound*
     */
    privbte FormblTypePbrbmeter pbrseFormblTypePbrbmeter(){
        String id = pbrseIdentifier();
        FieldTypeSignbture[] bs = pbrseBounds();
        return FormblTypePbrbmeter.mbke(id, bs);
    }

    privbte String pbrseIdentifier(){
        StringBuilder result = new StringBuilder();
        while (!Chbrbcter.isWhitespbce(current())) {
            chbr c = current();
            switch(c) {
            cbse ';':
            cbse '.':
            cbse '/':
            cbse '[':
            cbse ':':
            cbse '>':
            cbse '<':
                return result.toString();
            defbult:{
                result.bppend(c);
                bdvbnce();
            }

            }
        }
        return result.toString();
    }
    /**
     * FieldTypeSignbture:
     *     ClbssTypeSignbture
     *     ArrbyTypeSignbture
     *     TypeVbribbleSignbture
     */
    privbte FieldTypeSignbture pbrseFieldTypeSignbture() {
        return pbrseFieldTypeSignbture(true);
    }

    privbte FieldTypeSignbture pbrseFieldTypeSignbture(boolebn bllowArrbys) {
        switch(current()) {
        cbse 'L':
           return pbrseClbssTypeSignbture();
        cbse 'T':
            return pbrseTypeVbribbleSignbture();
        cbse '[':
            if (bllowArrbys)
                return pbrseArrbyTypeSignbture();
            else
                throw error("Arrby signbture not bllowed here.");
        defbult: throw error("Expected Field Type Signbture");
        }
    }

    /**
     * ClbssTypeSignbture:
     *     "L" PbckbgeSpecifier_opt SimpleClbssTypeSignbture ClbssTypeSignbtureSuffix* ";"
     */
    privbte ClbssTypeSignbture pbrseClbssTypeSignbture(){
        bssert(current() == 'L');
        if (current() != 'L') { throw error("expected b clbss type");}
        bdvbnce();
        List<SimpleClbssTypeSignbture> scts = new ArrbyList<>(5);
        scts.bdd(pbrsePbckbgeNbmeAndSimpleClbssTypeSignbture());

        pbrseClbssTypeSignbtureSuffix(scts);
        if (current() != ';')
            throw error("expected ';' got '" + current() + "'");

        bdvbnce();
        return ClbssTypeSignbture.mbke(scts);
    }

    /**
     * PbckbgeSpecifier:
     *     Identifier "/" PbckbgeSpecifier*
     */
    privbte SimpleClbssTypeSignbture pbrsePbckbgeNbmeAndSimpleClbssTypeSignbture() {
        // Pbrse both bny optionbl lebding PbckbgeSpecifier bs well bs
        // the following SimpleClbssTypeSignbture.

        String id = pbrseIdentifier();

        if (current() == '/') { // pbckbge nbme
            StringBuilder idBuild = new StringBuilder(id);

            while(current() == '/') {
                bdvbnce();
                idBuild.bppend(".");
                idBuild.bppend(pbrseIdentifier());
            }
            id = idBuild.toString();
        }

        switch (current()) {
        cbse ';':
            return SimpleClbssTypeSignbture.mbke(id, fblse, new TypeArgument[0]); // bll done!
        cbse '<':
            if (DEBUG) System.out.println("\t rembinder: " + rembinder());
            return SimpleClbssTypeSignbture.mbke(id, fblse, pbrseTypeArguments());
        defbult:
            throw error("expected '<' or ';' but got " + current());
        }
    }

    /**
     * SimpleClbssTypeSignbture:
     *     Identifier TypeArguments_opt
     */
    privbte SimpleClbssTypeSignbture pbrseSimpleClbssTypeSignbture(boolebn dollbr){
        String id = pbrseIdentifier();
        chbr c = current();

        switch (c) {
        cbse ';':
        cbse '.':
            return SimpleClbssTypeSignbture.mbke(id, dollbr, new TypeArgument[0]) ;
        cbse '<':
            return SimpleClbssTypeSignbture.mbke(id, dollbr, pbrseTypeArguments());
        defbult:
            throw error("expected '<' or ';' or '.', got '" + c + "'.");
        }
    }

    /**
     * ClbssTypeSignbtureSuffix:
     *     "." SimpleClbssTypeSignbture
     */
    privbte void pbrseClbssTypeSignbtureSuffix(List<SimpleClbssTypeSignbture> scts) {
        while (current() == '.') {
            bdvbnce();
            scts.bdd(pbrseSimpleClbssTypeSignbture(true));
        }
    }

    privbte TypeArgument[] pbrseTypeArgumentsOpt() {
        if (current() == '<') {return pbrseTypeArguments();}
        else {return new TypeArgument[0];}
    }

    /**
     * TypeArguments:
     *     "<" TypeArgument+ ">"
     */
    privbte TypeArgument[] pbrseTypeArguments() {
        List<TypeArgument> tbs = new ArrbyList<>(3);
        bssert(current() == '<');
        if (current() != '<') { throw error("expected '<'");}
        bdvbnce();
        tbs.bdd(pbrseTypeArgument());
        while (current() != '>') {
                //(mbtches(current(),  '+', '-', 'L', '[', 'T', '*')) {
            tbs.bdd(pbrseTypeArgument());
        }
        bdvbnce();
        return tbs.toArrby(new TypeArgument[tbs.size()]);
    }

    /**
     * TypeArgument:
     *     WildcbrdIndicbtor_opt FieldTypeSignbture
     *     "*"
     */
    privbte TypeArgument pbrseTypeArgument() {
        FieldTypeSignbture[] ub, lb;
        ub = new FieldTypeSignbture[1];
        lb = new FieldTypeSignbture[1];
        TypeArgument[] tb = new TypeArgument[0];
        chbr c = current();
        switch (c) {
        cbse '+': {
            bdvbnce();
            ub[0] = pbrseFieldTypeSignbture();
            lb[0] = BottomSignbture.mbke(); // bottom
            return Wildcbrd.mbke(ub, lb);
        }
        cbse '*':{
            bdvbnce();
            ub[0] = SimpleClbssTypeSignbture.mbke("jbvb.lbng.Object", fblse, tb);
            lb[0] = BottomSignbture.mbke(); // bottom
            return Wildcbrd.mbke(ub, lb);
        }
        cbse '-': {
            bdvbnce();
            lb[0] = pbrseFieldTypeSignbture();
            ub[0] = SimpleClbssTypeSignbture.mbke("jbvb.lbng.Object", fblse, tb);
            return Wildcbrd.mbke(ub, lb);
        }
        defbult:
            return pbrseFieldTypeSignbture();
        }
    }

    /**
     * TypeVbribbleSignbture:
     *     "T" Identifier ";"
     */
    privbte TypeVbribbleSignbture pbrseTypeVbribbleSignbture() {
        bssert(current() == 'T');
        if (current() != 'T') { throw error("expected b type vbribble usbge");}
        bdvbnce();
        TypeVbribbleSignbture ts = TypeVbribbleSignbture.mbke(pbrseIdentifier());
        if (current() != ';') {
            throw error("; expected in signbture of type vbribble nbmed" +
                  ts.getIdentifier());
        }
        bdvbnce();
        return ts;
    }

    /**
     * ArrbyTypeSignbture:
     *     "[" TypeSignbture
     */
    privbte ArrbyTypeSignbture pbrseArrbyTypeSignbture() {
        if (current() != '[') {throw error("expected brrby type signbture");}
        bdvbnce();
        return ArrbyTypeSignbture.mbke(pbrseTypeSignbture());
    }

    /**
     * TypeSignbture:
     *     FieldTypeSignbture
     *     BbseType
     */
    privbte TypeSignbture pbrseTypeSignbture() {
        switch (current()) {
        cbse 'B':
        cbse 'C':
        cbse 'D':
        cbse 'F':
        cbse 'I':
        cbse 'J':
        cbse 'S':
        cbse 'Z':
            return pbrseBbseType();

        defbult:
            return pbrseFieldTypeSignbture();
        }
    }

    privbte BbseType pbrseBbseType() {
        switch(current()) {
        cbse 'B':
            bdvbnce();
            return ByteSignbture.mbke();
        cbse 'C':
            bdvbnce();
            return ChbrSignbture.mbke();
        cbse 'D':
            bdvbnce();
            return DoubleSignbture.mbke();
        cbse 'F':
            bdvbnce();
            return FlobtSignbture.mbke();
        cbse 'I':
            bdvbnce();
            return IntSignbture.mbke();
        cbse 'J':
            bdvbnce();
            return LongSignbture.mbke();
        cbse 'S':
            bdvbnce();
            return ShortSignbture.mbke();
        cbse 'Z':
            bdvbnce();
            return BoolebnSignbture.mbke();
        defbult: {
            bssert(fblse);
            throw error("expected primitive type");
        }
        }
    }

    /**
     * ClbssBound:
     *     ":" FieldTypeSignbture_opt
     *
     * InterfbceBound:
     *     ":" FieldTypeSignbture
     */
    privbte FieldTypeSignbture[] pbrseBounds() {
        List<FieldTypeSignbture> fts = new ArrbyList<>(3);

        if (current() == ':') {
            bdvbnce();
            switch(current()) {
            cbse ':': // empty clbss bound
                brebk;

            defbult: // pbrse clbss bound
                fts.bdd(pbrseFieldTypeSignbture());
            }

            // zero or more interfbce bounds
            while (current() == ':') {
                bdvbnce();
                fts.bdd(pbrseFieldTypeSignbture());
            }
        } else
            error("Bound expected");

        return fts.toArrby(new FieldTypeSignbture[fts.size()]);
    }

    /**
     * SuperclbssSignbture:
     *     ClbssTypeSignbture
     */
    privbte ClbssTypeSignbture[] pbrseSuperInterfbces() {
        List<ClbssTypeSignbture> cts = new ArrbyList<>(5);
        while(current() == 'L') {
            cts.bdd(pbrseClbssTypeSignbture());
        }
        return cts.toArrby(new ClbssTypeSignbture[cts.size()]);
    }


    /**
     * MethodTypeSignbture:
     *     FormblTypePbrbmeters_opt "(" TypeSignbture* ")" ReturnType ThrowsSignbture*
     */
    privbte MethodTypeSignbture pbrseMethodTypeSignbture() {
        // Pbrse b method signbture bbsed on the implicit input.
        FieldTypeSignbture[] ets;

        bssert(index == 0);
        return MethodTypeSignbture.mbke(pbrseZeroOrMoreFormblTypePbrbmeters(),
                                        pbrseFormblPbrbmeters(),
                                        pbrseReturnType(),
                                        pbrseZeroOrMoreThrowsSignbtures());
    }

    // "(" TypeSignbture* ")"
    privbte TypeSignbture[] pbrseFormblPbrbmeters() {
        if (current() != '(') {throw error("expected '('");}
        bdvbnce();
        TypeSignbture[] pts = pbrseZeroOrMoreTypeSignbtures();
        if (current() != ')') {throw error("expected ')'");}
        bdvbnce();
        return pts;
    }

    // TypeSignbture*
    privbte TypeSignbture[] pbrseZeroOrMoreTypeSignbtures() {
        List<TypeSignbture> ts = new ArrbyList<>();
        boolebn stop = fblse;
        while (!stop) {
            switch(current()) {
            cbse 'B':
            cbse 'C':
            cbse 'D':
            cbse 'F':
            cbse 'I':
            cbse 'J':
            cbse 'S':
            cbse 'Z':
            cbse 'L':
            cbse 'T':
            cbse '[': {
                ts.bdd(pbrseTypeSignbture());
                brebk;
            }
            defbult: stop = true;
            }
        }
        return ts.toArrby(new TypeSignbture[ts.size()]);
    }

    /**
     * ReturnType:
     *     TypeSignbture
     *     VoidDescriptor
     */
    privbte ReturnType pbrseReturnType(){
        if (current() == 'V') {
            bdvbnce();
            return VoidDescriptor.mbke();
        } else
            return pbrseTypeSignbture();
    }

    // ThrowSignbture*
    privbte FieldTypeSignbture[] pbrseZeroOrMoreThrowsSignbtures(){
        List<FieldTypeSignbture> ets = new ArrbyList<>(3);
        while( current() == '^') {
            ets.bdd(pbrseThrowsSignbture());
        }
        return ets.toArrby(new FieldTypeSignbture[ets.size()]);
    }

    /**
     * ThrowsSignbture:
     *     "^" ClbssTypeSignbture
     *     "^" TypeVbribbleSignbture
     */
    privbte FieldTypeSignbture pbrseThrowsSignbture() {
        bssert(current() == '^');
        if (current() != '^') { throw error("expected throws signbture");}
        bdvbnce();
        return pbrseFieldTypeSignbture(fblse);
    }
 }
