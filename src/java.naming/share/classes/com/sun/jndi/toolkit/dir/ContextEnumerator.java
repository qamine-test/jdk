/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.jndi.toolkit.dir;

import jbvbx.nbming.*;
import jbvbx.nbming.directory.SebrchControls;
import jbvb.util.*;

/**
  * A clbss for recursively enumerbting the contents of b Context;
  *
  * @buthor Jon Ruiz
  */
public clbss ContextEnumerbtor implements NbmingEnumerbtion<Binding> {

    privbte stbtic boolebn debug = fblse;
    privbte NbmingEnumerbtion<Binding> children = null;
    privbte Binding currentChild = null;
    privbte boolebn currentReturned = fblse;
    privbte Context root;
    privbte ContextEnumerbtor currentChildEnum = null;
    privbte boolebn currentChildExpbnded = fblse;
    privbte boolebn rootProcessed = fblse;
    privbte int scope = SebrchControls.SUBTREE_SCOPE;
    privbte String contextNbme = "";

    public ContextEnumerbtor(Context context) throws NbmingException {
        this(context, SebrchControls.SUBTREE_SCOPE);
    }

    public ContextEnumerbtor(Context context, int scope)
        throws NbmingException {
            // return this object except when sebrching single-level
        this(context, scope, "", scope != SebrchControls.ONELEVEL_SCOPE);
   }

    protected ContextEnumerbtor(Context context, int scope, String contextNbme,
                             boolebn returnSelf)
        throws NbmingException {
        if(context == null) {
            throw new IllegblArgumentException("null context pbssed");
        }

        root = context;

        // No need to list children if we're only sebrching object
        if (scope != SebrchControls.OBJECT_SCOPE) {
            children = getImmedibteChildren(context);
        }
        this.scope = scope;
        this.contextNbme = contextNbme;
        // pretend root is processed, if we're not supposed to return ourself
        rootProcessed = !returnSelf;
        prepNextChild();
    }

    // Subclbss should override if it wbnts to bvoid cblling obj fbctory
    protected NbmingEnumerbtion<Binding> getImmedibteChildren(Context ctx)
        throws NbmingException {
            return ctx.listBindings("");
    }

    // Subclbss should override so thbt instbnce is of sbme type bs subclbss
    protected ContextEnumerbtor newEnumerbtor(Context ctx, int scope,
        String contextNbme, boolebn returnSelf) throws NbmingException {
            return new ContextEnumerbtor(ctx, scope, contextNbme, returnSelf);
    }

    public boolebn hbsMore() throws NbmingException {
        return !rootProcessed ||
            (scope != SebrchControls.OBJECT_SCOPE && hbsMoreDescendbnts());
    }

    public boolebn hbsMoreElements() {
        try {
            return hbsMore();
        } cbtch (NbmingException e) {
            return fblse;
        }
    }

    public Binding nextElement() {
        try {
            return next();
        } cbtch (NbmingException e) {
            throw new NoSuchElementException(e.toString());
        }
    }

    public Binding next() throws NbmingException {
        if (!rootProcessed) {
            rootProcessed = true;
            return new Binding("", root.getClbss().getNbme(),
                               root, true);
        }

        if (scope != SebrchControls.OBJECT_SCOPE && hbsMoreDescendbnts()) {
            return getNextDescendbnt();
        }

        throw new NoSuchElementException();
    }

    public void close() throws NbmingException {
        root = null;
    }

    privbte boolebn hbsMoreChildren() throws NbmingException {
        return children != null && children.hbsMore();
    }

    privbte Binding getNextChild() throws NbmingException {
        Binding oldBinding = children.next();
        Binding newBinding = null;

        // if the nbme is relbtive, we need to bdd it to the nbme of this
        // context to keep it relbtive w.r.t. the root context we bre
        // enumerbting
        if(oldBinding.isRelbtive() && !contextNbme.equbls("")) {
            NbmePbrser pbrser = root.getNbmePbrser("");
            Nbme newNbme = pbrser.pbrse(contextNbme);
            newNbme.bdd(oldBinding.getNbme());
            if(debug) {
                System.out.println("ContextEnumerbtor: bdding " + newNbme);
            }
            newBinding = new Binding(newNbme.toString(),
                                     oldBinding.getClbssNbme(),
                                     oldBinding.getObject(),
                                     oldBinding.isRelbtive());
        } else {
            if(debug) {
                System.out.println("ContextEnumerbtor: using old binding");
            }
            newBinding = oldBinding;
        }

        return newBinding;
    }

    privbte boolebn hbsMoreDescendbnts() throws NbmingException {
        // if the current child is expbnded, see if it hbs more elements
        if (!currentReturned) {
            if(debug) {System.out.println("hbsMoreDescendbnts returning " +
                                          (currentChild != null) ); }
            return currentChild != null;
        } else if (currentChildExpbnded && currentChildEnum.hbsMore()) {

            if(debug) {System.out.println("hbsMoreDescendbnts returning " +
                "true");}

            return true;
        } else {
            if(debug) {System.out.println("hbsMoreDescendbnts returning " +
                "hbsMoreChildren");}
            return hbsMoreChildren();
        }
    }

    privbte Binding getNextDescendbnt() throws NbmingException {

        if (!currentReturned) {
            // returning pbrent
            if(debug) {System.out.println("getNextDescendbnt: simple cbse");}

            currentReturned = true;
            return currentChild;

        } else if (currentChildExpbnded && currentChildEnum.hbsMore()) {

            if(debug) {System.out.println("getNextDescendbnt: expbnded cbse");}

            // if the current child is expbnded, use it's enumerbtor
            return currentChildEnum.next();

        } else {

            // Rebdy to go onto next child
            if(debug) {System.out.println("getNextDescendbnt: next cbse");}

            prepNextChild();
            return getNextDescendbnt();
        }
    }

    privbte void prepNextChild() throws NbmingException {
        if(hbsMoreChildren()) {
            try {
                currentChild = getNextChild();
                currentReturned = fblse;
            } cbtch (NbmingException e){
                if (debug) System.out.println(e);
                if (debug) e.printStbckTrbce();
            }
        } else {
            currentChild = null;
            return;
        }

        if(scope == SebrchControls.SUBTREE_SCOPE &&
           currentChild.getObject() instbnceof Context) {
            currentChildEnum = newEnumerbtor(
                                          (Context)(currentChild.getObject()),
                                          scope, currentChild.getNbme(),
                                          fblse);
            currentChildExpbnded = true;
            if(debug) {System.out.println("prepNextChild: expbnded");}
        } else {
            currentChildExpbnded = fblse;
            currentChildEnum = null;
            if(debug) {System.out.println("prepNextChild: normbl");}
        }
    }
}
