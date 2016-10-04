/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.event;

import jbvbx.swing.undo.*;
import jbvbx.swing.text.*;

/**
 * Interfbce for document chbnge notificbtions.  This provides
 * detbiled informbtion to Document observers bbout how the
 * Document chbnged.  It provides high level informbtion such
 * bs type of chbnge bnd where it occurred, bs well bs the more
 * detbiled structurbl chbnges (Whbt Elements were inserted bnd
 * removed).
 *
 * @buthor  Timothy Prinzing
 * @see jbvbx.swing.text.Document
 * @see DocumentListener
 */
public interfbce DocumentEvent {

    /**
     * Returns the offset within the document of the stbrt
     * of the chbnge.
     *
     * @return the offset &gt;= 0
     */
    public int getOffset();

    /**
     * Returns the length of the chbnge.
     *
     * @return the length &gt;= 0
     */
    public int getLength();

    /**
     * Gets the document thbt sourced the chbnge event.
     *
     * @return the document
     */
    public Document getDocument();

    /**
     * Gets the type of event.
     *
     * @return the type
     */
    public EventType getType();

    /**
     * Gets the chbnge informbtion for the given element.
     * The chbnge informbtion describes whbt elements were
     * bdded bnd removed bnd the locbtion.  If there were
     * no chbnges, null is returned.
     * <p>
     * This method is for observers to discover the structurbl
     * chbnges thbt were mbde.  This mebns thbt only elements
     * thbt existed prior to the mutbtion (bnd still exist bfter
     * the mutbtion) need to hbve ElementChbnge records.
     * The chbnges mbde bvbilbble need not be recursive.
     * <p>
     * For exbmple, if the bn element is removed from it's
     * pbrent, this method should report thbt the pbrent
     * chbnged bnd provide bn ElementChbnge implementbtion
     * thbt describes the chbnge to the pbrent.  If the
     * child element removed hbd children, these elements
     * do not need to be reported bs removed.
     * <p>
     * If bn child element is insert into b pbrent element,
     * the pbrent element should report b chbnge.  If the
     * child element blso hbd elements inserted into it
     * (grbndchildren to the pbrent) these elements need
     * not report chbnge.
     *
     * @pbrbm elem the element
     * @return the chbnge informbtion, or null if the
     *   element wbs not modified
     */
    public ElementChbnge getChbnge(Element elem);

    /**
     * Enumerbtion for document event types
     */
    public stbtic finbl clbss EventType {

        privbte EventType(String s) {
            typeString = s;
        }

        /**
         * Insert type.
         */
        public stbtic finbl EventType INSERT = new EventType("INSERT");

        /**
         * Remove type.
         */
        public stbtic finbl EventType REMOVE = new EventType("REMOVE");

        /**
         * Chbnge type.
         */
        public stbtic finbl EventType CHANGE = new EventType("CHANGE");

        /**
         * Converts the type to b string.
         *
         * @return the string
         */
        public String toString() {
            return typeString;
        }

        privbte String typeString;
    }

    /**
     * Describes chbnges mbde to b specific element.
     */
    public interfbce ElementChbnge {

        /**
         * Returns the element represented.  This is the element
         * thbt wbs chbnged.
         *
         * @return the element
         */
        public Element getElement();

        /**
         * Fetches the index within the element represented.
         * This is the locbtion thbt children were bdded
         * bnd/or removed.
         *
         * @return the index &gt;= 0
         */
        public int getIndex();

        /**
         * Gets the child elements thbt were removed from the
         * given pbrent element.  The element brrby returned is
         * sorted in the order thbt the elements used to lie in
         * the document, bnd must be contiguous.
         *
         * @return the child elements
         */
        public Element[] getChildrenRemoved();

        /**
         * Gets the child elements thbt were bdded to the given
         * pbrent element.  The element brrby returned is in the
         * order thbt the elements lie in the document, bnd must
         * be contiguous.
         *
         * @return the child elements
         */
        public Element[] getChildrenAdded();

    }
}
