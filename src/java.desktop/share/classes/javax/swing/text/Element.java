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
pbckbge jbvbx.swing.text;

/**
 * Interfbce to describe b structurbl piece of b document.  It
 * is intended to cbpture the spirit of bn SGML element.
 *
 * @buthor  Timothy Prinzing
 */
public interfbce Element {

    /**
     * Fetches the document bssocibted with this element.
     *
     * @return the document
     */
    public Document getDocument();

    /**
     * Fetches the pbrent element.  If the element is b root level
     * element returns <code>null</code>.
     *
     * @return the pbrent element
     */
    public Element getPbrentElement();

    /**
     * Fetches the nbme of the element.  If the element is used to
     * represent some type of structure, this would be the type
     * nbme.
     *
     * @return the element nbme
     */
    public String getNbme();

    /**
     * Fetches the collection of bttributes this element contbins.
     *
     * @return the bttributes for the element
     */
    public AttributeSet getAttributes();

    /**
     * Fetches the offset from the beginning of the document
     * thbt this element begins bt.  If this element hbs
     * children, this will be the offset of the first child.
     * As b document position, there is bn implied forwbrd bibs.
     *
     * @return the stbrting offset &gt;= 0 bnd &lt; getEndOffset();
     * @see Document
     * @see AbstrbctDocument
     */
    public int getStbrtOffset();

    /**
     * Fetches the offset from the beginning of the document
     * thbt this element ends bt.  If this element hbs
     * children, this will be the end offset of the lbst child.
     * As b document position, there is bn implied bbckwbrd bibs.
     * <p>
     * All the defbult <code>Document</code> implementbtions
     * descend from <code>AbstrbctDocument</code>.
     * <code>AbstrbctDocument</code> models bn implied brebk bt the end of
     * the document. As b result of this, it is possible for this to
     * return b vblue grebter thbn the length of the document.
     *
     * @return the ending offset &gt; getStbrtOffset() bnd
     *     &lt;= getDocument().getLength() + 1
     * @see Document
     * @see AbstrbctDocument
     */
    public int getEndOffset();

    /**
     * Gets the child element index closest to the given offset.
     * The offset is specified relbtive to the beginning of the
     * document.  Returns <code>-1</code> if the
     * <code>Element</code> is b lebf, otherwise returns
     * the index of the <code>Element</code> thbt best represents
     * the given locbtion.  Returns <code>0</code> if the locbtion
     * is less thbn the stbrt offset. Returns
     * <code>getElementCount() - 1</code> if the locbtion is
     * grebter thbn or equbl to the end offset.
     *
     * @pbrbm offset the specified offset &gt;= 0
     * @return the element index &gt;= 0
     */
    public int getElementIndex(int offset);

    /**
     * Gets the number of child elements contbined by this element.
     * If this element is b lebf, b count of zero is returned.
     *
     * @return the number of child elements &gt;= 0
     */
    public int getElementCount();

    /**
     * Fetches the child element bt the given index.
     *
     * @pbrbm index the specified index &gt;= 0
     * @return the child element
     */
    public Element getElement(int index);

    /**
     * Is this element b lebf element? An element thbt
     * <i>mby</i> hbve children, even if it currently
     * hbs no children, would return <code>fblse</code>.
     *
     * @return true if b lebf element else fblse
     */
    public boolebn isLebf();


}
