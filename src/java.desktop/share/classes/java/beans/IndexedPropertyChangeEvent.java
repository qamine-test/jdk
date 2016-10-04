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
pbckbge jbvb.bebns;

/**
 * An "IndexedPropertyChbnge" event gets delivered whenever b component thbt
 * conforms to the JbvbBebns&trbde; specificbtion (b "bebn") chbnges b bound
 * indexed property. This clbss is bn extension of <code>PropertyChbngeEvent</code>
 * but contbins the index of the property thbt hbs chbnged.
 * <P>
 * Null vblues mby be provided for the old bnd the new vblues if their
 * true vblues bre not known.
 * <P>
 * An event source mby send b null object bs the nbme to indicbte thbt bn
 * brbitrbry set of if its properties hbve chbnged.  In this cbse the
 * old bnd new vblues should blso be null.
 *
 * @since 1.5
 * @buthor Mbrk Dbvidson
 */
public clbss IndexedPropertyChbngeEvent extends PropertyChbngeEvent {
    privbte stbtic finbl long seriblVersionUID = -320227448495806870L;

    privbte int index;

    /**
     * Constructs b new <code>IndexedPropertyChbngeEvent</code> object.
     *
     * @pbrbm source  The bebn thbt fired the event.
     * @pbrbm propertyNbme  The progrbmmbtic nbme of the property thbt
     *             wbs chbnged.
     * @pbrbm oldVblue      The old vblue of the property.
     * @pbrbm newVblue      The new vblue of the property.
     * @pbrbm index index of the property element thbt wbs chbnged.
     */
    public IndexedPropertyChbngeEvent(Object source, String propertyNbme,
                                      Object oldVblue, Object newVblue,
                                      int index) {
        super (source, propertyNbme, oldVblue, newVblue);
        this.index = index;
    }

    /**
     * Gets the index of the property thbt wbs chbnged.
     *
     * @return The index specifying the property element thbt wbs
     *         chbnged.
     */
    public int getIndex() {
        return index;
    }

    void bppendTo(StringBuilder sb) {
        sb.bppend("; index=").bppend(getIndex());
    }
}
