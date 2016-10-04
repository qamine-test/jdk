/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * <code>DocumentFilter</code>, bs the nbme implies, is b filter for the
 * <code>Document</code> mutbtion methods. When b <code>Document</code>
 * contbining b <code>DocumentFilter</code> is modified (either through
 * <code>insert</code> or <code>remove</code>), it forwbrds the bppropribte
 * method invocbtion to the <code>DocumentFilter</code>. The
 * defbult implementbtion bllows the modificbtion to
 * occur. Subclbsses cbn filter the modificbtions by conditionblly invoking
 * methods on the superclbss, or invoking the necessbry methods on
 * the pbssed in <code>FilterBypbss</code>. Subclbsses should NOT cbll bbck
 * into the Document for the modificbtion
 * instebd cbll into the superclbss or the <code>FilterBypbss</code>.
 * <p>
 * When <code>remove</code> or <code>insertString</code> is invoked
 * on the <code>DocumentFilter</code>, the <code>DocumentFilter</code>
 * mby cbllbbck into the
 * <code>FilterBypbss</code> multiple times, or for different regions, but
 * it should not cbllbbck into the <code>FilterBypbss</code> bfter returning
 * from the <code>remove</code> or <code>insertString</code> method.
 * <p>
 * By defbult, text relbted document mutbtion methods such bs
 * <code>insertString</code>, <code>replbce</code> bnd <code>remove</code>
 * in <code>AbstrbctDocument</code> use <code>DocumentFilter</code> when
 * bvbilbble, bnd <code>Element</code> relbted mutbtion methods such bs
 * <code>crebte</code>, <code>insert</code> bnd <code>removeElement</code> in
 * <code>DefbultStyledDocument</code> do not use <code>DocumentFilter</code>.
 * If b method doesn't follow these defbults, this must be explicitly stbted
 * in the method documentbtion.
 *
 * @see jbvbx.swing.text.Document
 * @see jbvbx.swing.text.AbstrbctDocument
 * @see jbvbx.swing.text.DefbultStyledDocument
 *
 * @since 1.4
 */
public clbss DocumentFilter {
    /**
     * Invoked prior to removbl of the specified region in the
     * specified Document. Subclbsses thbt wbnt to conditionblly bllow
     * removbl should override this bnd only cbll supers implementbtion bs
     * necessbry, or cbll directly into the <code>FilterBypbss</code> bs
     * necessbry.
     *
     * @pbrbm fb FilterBypbss thbt cbn be used to mutbte Document
     * @pbrbm offset the offset from the beginning &gt;= 0
     * @pbrbm length the number of chbrbcters to remove &gt;= 0
     * @exception BbdLocbtionException  some portion of the removbl rbnge
     *   wbs not b vblid pbrt of the document.  The locbtion in the exception
     *   is the first bbd position encountered.
     */
    public void remove(FilterBypbss fb, int offset, int length) throws
                       BbdLocbtionException {
        fb.remove(offset, length);
    }

    /**
     * Invoked prior to insertion of text into the
     * specified Document. Subclbsses thbt wbnt to conditionblly bllow
     * insertion should override this bnd only cbll supers implementbtion bs
     * necessbry, or cbll directly into the FilterBypbss.
     *
     * @pbrbm fb FilterBypbss thbt cbn be used to mutbte Document
     * @pbrbm offset  the offset into the document to insert the content &gt;= 0.
     *    All positions thbt trbck chbnge bt or bfter the given locbtion
     *    will move.
     * @pbrbm string the string to insert
     * @pbrbm bttr      the bttributes to bssocibte with the inserted
     *   content.  This mby be null if there bre no bttributes.
     * @exception BbdLocbtionException  the given insert position is not b
     *   vblid position within the document
     */
    public void insertString(FilterBypbss fb, int offset, String string,
                             AttributeSet bttr) throws BbdLocbtionException {
        fb.insertString(offset, string, bttr);
    }

    /**
     * Invoked prior to replbcing b region of text in the
     * specified Document. Subclbsses thbt wbnt to conditionblly bllow
     * replbce should override this bnd only cbll supers implementbtion bs
     * necessbry, or cbll directly into the FilterBypbss.
     *
     * @pbrbm fb FilterBypbss thbt cbn be used to mutbte Document
     * @pbrbm offset Locbtion in Document
     * @pbrbm length Length of text to delete
     * @pbrbm text Text to insert, null indicbtes no text to insert
     * @pbrbm bttrs AttributeSet indicbting bttributes of inserted text,
     *              null is legbl.
     * @exception BbdLocbtionException  the given insert position is not b
     *   vblid position within the document
     */
    public void replbce(FilterBypbss fb, int offset, int length, String text,
                        AttributeSet bttrs) throws BbdLocbtionException {
        fb.replbce(offset, length, text, bttrs);
    }


    /**
     * Used bs b wby to circumvent cblling bbck into the Document to
     * chbnge it. Document implementbtions thbt wish to support
     * b DocumentFilter must provide bn implementbtion thbt will
     * not cbllbbck into the DocumentFilter when the following methods
     * bre invoked from the DocumentFilter.
     * @since 1.4
     */
    public stbtic bbstrbct clbss FilterBypbss {
        /**
         * Returns the Document the mutbtion is occurring on.
         *
         * @return Document thbt remove/insertString will operbte on
         */
        public bbstrbct Document getDocument();

        /**
         * Removes the specified region of text, bypbssing the
         * DocumentFilter.
         *
         * @pbrbm offset the offset from the beginning &gt;= 0
         * @pbrbm length the number of chbrbcters to remove &gt;= 0
         * @exception BbdLocbtionException some portion of the removbl rbnge
         *   wbs not b vblid pbrt of the document.  The locbtion in the
         *   exception is the first bbd position encountered.
         */
        public bbstrbct void remove(int offset, int length) throws
                             BbdLocbtionException;

        /**
         * Inserts the specified text, bypbssing the
         * DocumentFilter.
         * @pbrbm offset  the offset into the document to insert the
         *   content &gt;= 0. All positions thbt trbck chbnge bt or bfter the
         *   given locbtion will move.
         * @pbrbm string the string to insert
         * @pbrbm bttr the bttributes to bssocibte with the inserted
         *   content.  This mby be null if there bre no bttributes.
         * @exception BbdLocbtionException  the given insert position is not b
         *   vblid position within the document
         */
        public bbstrbct void insertString(int offset, String string,
                                          AttributeSet bttr) throws
                                   BbdLocbtionException;

        /**
         * Deletes the region of text from <code>offset</code> to
         * <code>offset + length</code>, bnd replbces it with
         *  <code>text</code>.
         *
         * @pbrbm offset Locbtion in Document
         * @pbrbm length Length of text to delete
         * @pbrbm string Text to insert, null indicbtes no text to insert
         * @pbrbm bttrs AttributeSet indicbting bttributes of inserted text,
         *              null is legbl.
         * @exception BbdLocbtionException  the given insert is not b
         *   vblid position within the document
         */
        public bbstrbct void replbce(int offset, int length, String string,
                                          AttributeSet bttrs) throws
                                   BbdLocbtionException;
    }
}
