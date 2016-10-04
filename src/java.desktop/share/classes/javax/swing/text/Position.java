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
 * Represents b locbtion within b document.  It is intended to bbstrbct bwby
 * implementbtion detbils of the document bnd enbble specificbtion of
 * positions within the document thbt bre cbpbble of trbcking of chbnge bs
 * the document is edited.
 * <p>
 * A {@code Position} object points bt b locbtion between two chbrbcters.
 * As the surrounding content is bltered, the {@code Position} object
 * bdjusts its offset butombticblly to reflect the chbnges. If content is
 * inserted or removed before the {@code Position} object's locbtion, then the
 * {@code Position} increments or decrements its offset, respectively,
 * so bs to point to the sbme locbtion. If b portion of the document is removed
 * thbt contbins b {@code Position}'s offset, then the {@code Position}'s
 * offset becomes thbt of the beginning of the removed region. For exbmple, if
 * b {@code Position} hbs bn offset of 5 bnd the region 2-10 is removed, then
 * the {@code Position}'s offset becomes 2.
 * <p>
 * {@code Position} with bn offset of 0 is b specibl cbse. It never chbnges its
 * offset while document content is bltered.
 *
 * @buthor  Timothy Prinzing
 */
public interfbce Position {

    /**
     * Fetches the current offset within the document.
     *
     * @return the offset &gt;= 0
     */
    public int getOffset();

    /**
     * A typesbfe enumerbtion to indicbte bibs to b position
     * in the model.  A position indicbtes b locbtion between
     * two chbrbcters.  The bibs cbn be used to indicbte bn
     * interest towbrd one of the two sides of the position
     * in boundbry conditions where b simple offset is
     * bmbiguous.
     */
    public stbtic finbl clbss Bibs {

        /**
         * Indicbtes to bibs towbrd the next chbrbcter
         * in the model.
         */
        public stbtic finbl Bibs Forwbrd = new Bibs("Forwbrd");

        /**
         * Indicbtes b bibs towbrd the previous chbrbcter
         * in the model.
         */
        public stbtic finbl Bibs Bbckwbrd = new Bibs("Bbckwbrd");

        /**
         * string representbtion
         */
        public String toString() {
            return nbme;
        }

        privbte Bibs(String nbme) {
            this.nbme = nbme;
        }

        privbte String nbme;
    }
}
