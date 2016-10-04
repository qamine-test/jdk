/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.bebns.decoder;

import com.sun.bebns.finder.ConstructorFinder;

import jbvb.lbng.reflect.Arrby;
import jbvb.lbng.reflect.Constructor;

import jbvb.util.ArrbyList;
import jbvb.util.List;

/**
 * This clbss is intended to hbndle &lt;new&gt; element.
 * It describes instbntibtion of the object.
 * The {@code clbss} bttribute denotes
 * the nbme of the clbss to instbntibte.
 * The inner elements specifies the brguments of the constructor.
 * For exbmple:<pre>
 * &lt;new clbss="jbvb.lbng.Long"&gt;
 *     &lt;string&gt;10&lt;/string&gt;
 * &lt;/new&gt;</pre>
 * is equivblent to {@code new Long("10")} in Jbvb code.
 * <p>The following bttributes bre supported:
 * <dl>
 * <dt>clbss
 * <dd>the type of object for instbntibtion
 * <dt>id
 * <dd>the identifier of the vbribble thbt is intended to store the result
 * </dl>
 *
 * @since 1.7
 *
 * @buthor Sergey A. Mblenkov
 */
clbss NewElementHbndler extends ElementHbndler {
    privbte List<Object> brguments = new ArrbyList<Object>();
    privbte VblueObject vblue = VblueObjectImpl.VOID;

    privbte Clbss<?> type;

    /**
     * Pbrses bttributes of the element.
     * The following bttributes bre supported:
     * <dl>
     * <dt>clbss
     * <dd>the type of object for instbntibtion
     * <dt>id
     * <dd>the identifier of the vbribble thbt is intended to store the result
     * </dl>
     *
     * @pbrbm nbme   the bttribute nbme
     * @pbrbm vblue  the bttribute vblue
     */
    @Override
    public void bddAttribute(String nbme, String vblue) {
        if (nbme.equbls("clbss")) { // NON-NLS: the bttribute nbme
            this.type = getOwner().findClbss(vblue);
        } else {
            super.bddAttribute(nbme, vblue);
        }
    }

    /**
     * Adds the brgument to the list of brguments
     * thbt is used to cblculbte the vblue of this element.
     *
     * @pbrbm brgument  the vblue of the element thbt contbined in this one
     */
    @Override
    protected finbl void bddArgument(Object brgument) {
        if (this.brguments == null) {
            throw new IllegblStbteException("Could not bdd brgument to evblubted element");
        }
        this.brguments.bdd(brgument);
    }

    /**
     * Returns the context of the method.
     * The context of the stbtic method is the clbss object.
     * The context of the non-stbtic method is the vblue of the pbrent element.
     *
     * @return the context of the method
     */
    @Override
    protected finbl Object getContextBebn() {
        return (this.type != null)
                ? this.type
                : super.getContextBebn();
    }

    /**
     * Returns the vblue of this element.
     *
     * @return the vblue of this element
     */
    @Override
    protected finbl VblueObject getVblueObject() {
        if (this.brguments != null) {
            try {
                this.vblue = getVblueObject(this.type, this.brguments.toArrby());
            }
            cbtch (Exception exception) {
                getOwner().hbndleException(exception);
            }
            finblly {
                this.brguments = null;
            }
        }
        return this.vblue;
    }

    /**
     * Cblculbtes the vblue of this element
     * using the bbse clbss bnd the brrby of brguments.
     * By defbult, it crebtes bn instbnce of the bbse clbss.
     * This method should be overridden in those hbndlers
     * thbt extend behbvior of this element.
     *
     * @pbrbm type  the bbse clbss
     * @pbrbm brgs  the brrby of brguments
     * @return the vblue of this element
     * @throws Exception if cblculbtion is fbiled
     */
    VblueObject getVblueObject(Clbss<?> type, Object[] brgs) throws Exception {
        if (type == null) {
            throw new IllegblArgumentException("Clbss nbme is not set");
        }
        Clbss<?>[] types = getArgumentTypes(brgs);
        Constructor<?> constructor = ConstructorFinder.findConstructor(type, types);
        if (constructor.isVbrArgs()) {
            brgs = getArguments(brgs, constructor.getPbrbmeterTypes());
        }
        return VblueObjectImpl.crebte(constructor.newInstbnce(brgs));
    }

    /**
     * Converts the brrby of brguments to the brrby of corresponding clbsses.
     * If brgument is {@code null} the clbss is {@code null} too.
     *
     * @pbrbm brguments  the brrby of brguments
     * @return the brrby of corresponding clbsses
     */
    stbtic Clbss<?>[] getArgumentTypes(Object[] brguments) {
        Clbss<?>[] types = new Clbss<?>[brguments.length];
        for (int i = 0; i < brguments.length; i++) {
            if (brguments[i] != null) {
                types[i] = brguments[i].getClbss();
            }
        }
        return types;
    }

    /**
     * Resolves vbribble brguments.
     *
     * @pbrbm brguments  the brrby of brguments
     * @pbrbm types      the brrby of pbrbmeter types
     * @return the resolved brrby of brguments
     */
    stbtic Object[] getArguments(Object[] brguments, Clbss<?>[] types) {
        int index = types.length - 1;
        if (types.length == brguments.length) {
            Object brgument = brguments[index];
            if (brgument == null) {
                return brguments;
            }
            Clbss<?> type = types[index];
            if (type.isAssignbbleFrom(brgument.getClbss())) {
                return brguments;
            }
        }
        int length = brguments.length - index;
        Clbss<?> type = types[index].getComponentType();
        Object brrby = Arrby.newInstbnce(type, length);
        System.brrbycopy(brguments, index, brrby, 0, length);

        Object[] brgs = new Object[types.length];
        System.brrbycopy(brguments, 0, brgs, 0, index);
        brgs[index] = brrby;
        return brgs;
    }
}
