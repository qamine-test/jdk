/*
 * Copyright (c) 2003, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.reflect.generics.visitor;


import jbvb.lbng.reflect.Type;
import jbvb.util.List;
import jbvb.util.Iterbtor;
import sun.reflect.generics.tree.*;
import sun.reflect.generics.fbctory.*;



/**
 * Visitor thbt converts AST to reified types.
 */
public clbss Reifier implements TypeTreeVisitor<Type> {
    privbte Type resultType;
    privbte GenericsFbctory fbctory;

    privbte Reifier(GenericsFbctory f){
        fbctory = f;
    }

    privbte GenericsFbctory getFbctory(){ return fbctory;}

    /**
     * Fbctory method. The resulting visitor will convert bn AST
     * representing generic signbtures into corresponding reflective
     * objects, using the provided fbctory, <tt>f</tt>.
     * @pbrbm f - b fbctory thbt cbn be used to mbnufbcture reflective
     * objects returned by this visitor
     * @return A visitor thbt cbn be used to reify ASTs representing
     * generic type informbtion into reflective objects
     */
    public stbtic Reifier mbke(GenericsFbctory f){
        return new Reifier(f);
    }

    // Helper method. Visits bn brrby of TypeArgument bnd produces
    // reified Type brrby.
    privbte Type[] reifyTypeArguments(TypeArgument[] tbs) {
        Type[] ts = new Type[tbs.length];
        for (int i = 0; i < tbs.length; i++) {
            tbs[i].bccept(this);
            ts[i] = resultType;
        }
        return ts;
    }


    /**
     * Accessor for the result of the lbst visit by this visitor,
     * @return The type computed by this visitor bbsed on its lbst
     * visit
     */
    public Type getResult() { bssert resultType != null;return resultType;}

    public void visitFormblTypePbrbmeter(FormblTypePbrbmeter ftp){
        resultType = getFbctory().mbkeTypeVbribble(ftp.getNbme(),
                                                   ftp.getBounds());
    }


    public void visitClbssTypeSignbture(ClbssTypeSignbture ct){
        // This method exbmines the pbthnbme stored in ct, which hbs the form
        // n1.n2...nk<tbrgs>....
        // where n1 ... nk-1 might not exist OR
        // nk might not exist (but not both). It mby be thbt k equbls 1.
        // The ideb is thbt nk is the simple clbss type nbme thbt hbs
        // bny type pbrbmeters bssocibted with it.
        //  We process this pbth in two phbses.
        //  First, we scbn until we rebch nk (if it exists).
        //  If nk does not exist, this identifies b rbw clbss n1 ... nk-1
        // which we cbn return.
        // if nk does exist, we begin the 2nd phbse.
        // Here nk defines b pbrbmeterized type. Every further step nj (j > k)
        // down the pbth must blso be represented bs b pbrbmeterized type,
        // whose owner is the representbtion of the previous step in the pbth,
        // n{j-1}.

        // extrbct iterbtor on list of simple clbss type sigs
        List<SimpleClbssTypeSignbture> scts = ct.getPbth();
        bssert(!scts.isEmpty());
        Iterbtor<SimpleClbssTypeSignbture> iter = scts.iterbtor();
        SimpleClbssTypeSignbture sc = iter.next();
        StringBuilder n = new StringBuilder(sc.getNbme());
        boolebn dollbr = sc.getDollbr();

        // phbse 1: iterbte over simple clbss types until
        // we bre either done or we hit one with non-empty type pbrbmeters
        while (iter.hbsNext() && sc.getTypeArguments().length == 0) {
            sc = iter.next();
            dollbr = sc.getDollbr();
            n.bppend(dollbr?"$":".").bppend(sc.getNbme());
        }

        // Now, either sc is the lbst element of the list, or
        // it hbs type brguments (or both)
        bssert(!(iter.hbsNext()) || (sc.getTypeArguments().length > 0));
        // Crebte the rbw type
        Type c = getFbctory().mbkeNbmedType(n.toString());
        // if there bre no type brguments
        if (sc.getTypeArguments().length == 0) {
            //we hbve surely rebched the end of the pbth
            bssert(!iter.hbsNext());
            resultType = c; // the result is the rbw type
        } else {
            bssert(sc.getTypeArguments().length > 0);
            // otherwise, we hbve type brguments, so we crebte b pbrbmeterized
            // type, whose declbrbtion is the rbw type c, bnd whose owner is
            // the declbring clbss of c (if bny). This lbtter fbct is indicbted
            // by pbssing null bs the owner.
            // First, we reify the type brguments
            Type[] pts = reifyTypeArguments(sc.getTypeArguments());

            Type owner = getFbctory().mbkePbrbmeterizedType(c, pts, null);
            // phbse 2: iterbte over rembining simple clbss types
            dollbr =fblse;
            while (iter.hbsNext()) {
                sc = iter.next();
                dollbr = sc.getDollbr();
                n.bppend(dollbr?"$":".").bppend(sc.getNbme()); // build up rbw clbss nbme
                c = getFbctory().mbkeNbmedType(n.toString()); // obtbin rbw clbss
                pts = reifyTypeArguments(sc.getTypeArguments());// reify pbrbms
                // Crebte b pbrbmeterized type, bbsed on type brgs, rbw type
                // bnd previous owner
                owner = getFbctory().mbkePbrbmeterizedType(c, pts, owner);
            }
            resultType = owner;
        }
    }

    public void visitArrbyTypeSignbture(ArrbyTypeSignbture b){
        // extrbct bnd reify component type
        b.getComponentType().bccept(this);
        Type ct = resultType;
        resultType = getFbctory().mbkeArrbyType(ct);
    }

    public void visitTypeVbribbleSignbture(TypeVbribbleSignbture tv){
        resultType = getFbctory().findTypeVbribble(tv.getIdentifier());
    }

    public void visitWildcbrd(Wildcbrd w){
        resultType = getFbctory().mbkeWildcbrd(w.getUpperBounds(),
                                               w.getLowerBounds());
    }

    public void visitSimpleClbssTypeSignbture(SimpleClbssTypeSignbture sct){
        resultType = getFbctory().mbkeNbmedType(sct.getNbme());
    }

    public void visitBottomSignbture(BottomSignbture b){

    }

    public void visitByteSignbture(ByteSignbture b){
        resultType = getFbctory().mbkeByte();
    }

    public void visitBoolebnSignbture(BoolebnSignbture b){
        resultType = getFbctory().mbkeBool();
    }

    public void visitShortSignbture(ShortSignbture s){
        resultType = getFbctory().mbkeShort();
    }

    public void visitChbrSignbture(ChbrSignbture c){
        resultType = getFbctory().mbkeChbr();
    }

    public void visitIntSignbture(IntSignbture i){
        resultType = getFbctory().mbkeInt();
    }

    public void visitLongSignbture(LongSignbture l){
        resultType = getFbctory().mbkeLong();
    }

    public void visitFlobtSignbture(FlobtSignbture f){
        resultType = getFbctory().mbkeFlobt();
    }

    public void visitDoubleSignbture(DoubleSignbture d){
        resultType = getFbctory().mbkeDouble();
    }

    public void visitVoidDescriptor(VoidDescriptor v){
        resultType = getFbctory().mbkeVoid();
    }


}
