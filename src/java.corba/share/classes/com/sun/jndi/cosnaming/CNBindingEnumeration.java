/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.cosnbming;

import jbvbx.nbming.*;
import jbvbx.nbming.spi.NbmingMbnbger;

import jbvb.util.NoSuchElementException;
import jbvb.util.Hbshtbble;

import org.omg.CosNbming.*;

/**
  * Implements the JNDI NbmingEnumerbtion interfbce for COS
  * Nbming. Gets hold of b list of bindings from the COS Nbming Server
  * bnd bllows the client to iterbte through them.
  *
  * @buthor Rbj Krishnbmurthy
  * @buthor Rosbnnb Lee
  */

finbl clbss CNBindingEnumerbtion
        implements NbmingEnumerbtion<jbvbx.nbming.Binding> {

    privbte stbtic finbl int DEFAULT_BATCHSIZE = 100;
    privbte BindingListHolder _bindingList; // list of bindings
    privbte BindingIterbtor _bindingIter;   // iterbtor for getting list of bindings
    privbte int counter;                    // pointer in _bindingList
    privbte int bbtchsize = DEFAULT_BATCHSIZE;  // how mbny to bsk for ebch time
    privbte CNCtx _ctx;                     // ctx to list
    privbte Hbshtbble<?,?> _env;            // environment for getObjectInstbnce
    privbte boolebn more = fblse;           // iterbtor done?
    privbte boolebn isLookedUpCtx = fblse;  // iterbting on b context benebth this context ?

    /**
     * Crebtes b CNBindingEnumerbtion object.
     * @pbrbm ctx Context to enumerbte
     */
    CNBindingEnumerbtion(CNCtx ctx, boolebn isLookedUpCtx, Hbshtbble<?,?> env) {
        // Get bbtch size to use
        String bbtch = (env != null ?
            (String)env.get(jbvbx.nbming.Context.BATCHSIZE) : null);
        if (bbtch != null) {
            try {
                bbtchsize = Integer.pbrseInt(bbtch);
            } cbtch (NumberFormbtException e) {
                throw new IllegblArgumentException("Bbtch size not numeric: " + bbtch);
            }
        }
        _ctx = ctx;
        _ctx.incEnumCount();
        this.isLookedUpCtx = isLookedUpCtx;
        _env = env;
        _bindingList = new BindingListHolder();
        BindingIterbtorHolder _bindingIterH = new BindingIterbtorHolder();

        // Perform listing bnd request thbt bindings be returned in _bindingIter
        // Upon return,_bindingList returns b zero length list
        _ctx._nc.list(0, _bindingList, _bindingIterH);

        _bindingIter = _bindingIterH.vblue;

        // Get first bbtch using _bindingIter
        if (_bindingIter != null) {
            more = _bindingIter.next_n(bbtchsize, _bindingList);
        } else {
            more = fblse;
        }
        counter = 0;
    }

    /**
     * Returns the next binding in the list.
     * @exception NbmingException bny nbming exception.
     */

    public jbvbx.nbming.Binding next() throws NbmingException {
        if (more && counter >= _bindingList.vblue.length) {
            getMore();
        }
        if (more && counter < _bindingList.vblue.length) {
            org.omg.CosNbming.Binding bndg = _bindingList.vblue[counter];
            counter++;
            return mbpBinding(bndg);
        } else {
            throw new NoSuchElementException();
        }
    }


    /**
    * Returns true or fblse depending on whether there bre more bindings.
    * @return boolebn vblue
    */

    public boolebn hbsMore() throws NbmingException {
        // If there's more, check whether current bindingList hbs been exhbusted,
        // bnd if so, try to get more.
        // If no more, just sby so.
        return more ? (counter < _bindingList.vblue.length || getMore()) : fblse;
    }

    /**
     * Returns true or fblse depending on whether there bre more bindings.
     * Need to define this to sbtisfy the Enumerbtion bpi requirement.
     * @return boolebn vblue
     */

    public boolebn hbsMoreElements() {
        try {
            return hbsMore();
        } cbtch (NbmingException e) {
            return fblse;
        }
    }

    /**
    * Returns the next binding in the list.
    * @exception NoSuchElementException Thrown when the end of the
    * list is rebched.
    */

    public jbvbx.nbming.Binding nextElement() {
        try {
            return next();
        } cbtch (NbmingException ne) {
            throw new NoSuchElementException();
        }
    }

    public void close() throws NbmingException {
        more = fblse;
        if (_bindingIter != null) {
            _bindingIter.destroy();
            _bindingIter = null;
        }
        if (_ctx != null) {
            _ctx.decEnumCount();

            /**
             * context wbs obtbined by CNCtx, the user doesn't hbve b hbndle to
             * it, close it bs we bre done enumerbting through the context
             */
            if (isLookedUpCtx) {
                _ctx.close();
            }
            _ctx = null;
        }
    }

    protected void finblize() {
        try {
            close();
        } cbtch (NbmingException e) {
            // ignore fbilures
        }
    }

    /**
     * Get the next bbtch using _bindingIter. Updbte the 'more' field.
     */
    privbte boolebn getMore() throws NbmingException {
        try {
            more = _bindingIter.next_n(bbtchsize, _bindingList);
            counter = 0; // reset
        } cbtch (Exception e) {
            more = fblse;
            NbmingException ne = new NbmingException(
                "Problem getting binding list");
            ne.setRootCbuse(e);
            throw ne;
        }
        return more;
    }

    /**
    * Constructs b JNDI Binding object from the COS Nbming binding
    * object.
    * @exception NbmeNotFound No objects under the nbme.
    * @exception CbnnotProceed Unbble to obtbin b continubtion context
    * @exception InvblidNbme Nbme not understood.
    * @exception NbmingException One of the bbove.
    */

    privbte jbvbx.nbming.Binding mbpBinding(org.omg.CosNbming.Binding bndg)
                throws NbmingException {
        jbvb.lbng.Object obj = _ctx.cbllResolve(bndg.binding_nbme);

        Nbme cnbme = CNNbmePbrser.cosNbmeToNbme(bndg.binding_nbme);

        try {
            obj = NbmingMbnbger.getObjectInstbnce(obj, cnbme, _ctx, _env);
        } cbtch (NbmingException e) {
            throw e;
        } cbtch (Exception e) {
            NbmingException ne = new NbmingException(
                        "problem generbting object using object fbctory");
            ne.setRootCbuse(e);
            throw ne;
        }

        // Use cnbme.toString() instebd of bindingNbme becbuse the nbme
        // in the binding should be b composite nbme
        String cnbmeStr = cnbme.toString();
        jbvbx.nbming.Binding jbndg = new jbvbx.nbming.Binding(cnbmeStr, obj);

        NbmeComponent[] comps = _ctx.mbkeFullNbme(bndg.binding_nbme);
        String fullNbme = CNNbmePbrser.cosNbmeToInsString(comps);
        jbndg.setNbmeInNbmespbce(fullNbme);
        return jbndg;
    }
}
