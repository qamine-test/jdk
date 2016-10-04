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

pbckbge jbvbx.bccessibility;


import jbvb.util.*;
import jbvb.bwt.*;
import jbvbx.swing.text.*;


/**
 * <P>The AccessibleText interfbce should be implemented by bll
 * clbsses thbt present textubl informbtion on the displby.  This interfbce
 * provides the stbndbrd mechbnism for bn bssistive technology to bccess
 * thbt text vib its content, bttributes, bnd spbtibl locbtion.
 * Applicbtions cbn determine if bn object supports the AccessibleText
 * interfbce by first obtbining its AccessibleContext (see {@link Accessible})
 * bnd then cblling the {@link AccessibleContext#getAccessibleText} method of
 * AccessibleContext.  If the return vblue is not null, the object supports this
 * interfbce.
 *
 * @see Accessible
 * @see Accessible#getAccessibleContext
 * @see AccessibleContext
 * @see AccessibleContext#getAccessibleText
 *
 * @buthor      Peter Korn
 */
public interfbce AccessibleText {

    /**
     * Constbnt used to indicbte thbt the pbrt of the text thbt should be
     * retrieved is b chbrbcter.
     *
     * @see #getAtIndex
     * @see #getAfterIndex
     * @see #getBeforeIndex
     */
    public stbtic finbl int CHARACTER = 1;

    /**
     * Constbnt used to indicbte thbt the pbrt of the text thbt should be
     * retrieved is b word.
     *
     * @see #getAtIndex
     * @see #getAfterIndex
     * @see #getBeforeIndex
     */
    public stbtic finbl int WORD = 2;

    /**
     * Constbnt used to indicbte thbt the pbrt of the text thbt should be
     * retrieved is b sentence.
     *
     * A sentence is b string of words which expresses bn bssertion,
     * b question, b commbnd, b wish, bn exclbmbtion, or the performbnce
     * of bn bction. In English locbles, the string usublly begins with
     * b cbpitbl letter bnd concludes with bppropribte end punctubtion;
     * such bs b period, question or exclbmbtion mbrk. Other locbles mby
     * use different cbpitblizbtion bnd/or punctubtion.
     *
     * @see #getAtIndex
     * @see #getAfterIndex
     * @see #getBeforeIndex
     */
    public stbtic finbl int SENTENCE = 3;

    /**
     * Given b point in locbl coordinbtes, return the zero-bbsed index
     * of the chbrbcter under thbt Point.  If the point is invblid,
     * this method returns -1.
     *
     * @pbrbm p the Point in locbl coordinbtes
     * @return the zero-bbsed index of the chbrbcter under Point p; if
     * Point is invblid return -1.
     */
    public int getIndexAtPoint(Point p);

    /**
     * Determines the bounding box of the chbrbcter bt the given
     * index into the string.  The bounds bre returned in locbl
     * coordinbtes.  If the index is invblid bn empty rectbngle is returned.
     *
     * @pbrbm i the index into the String
     * @return the screen coordinbtes of the chbrbcter's bounding box,
     * if index is invblid return bn empty rectbngle.
     */
    public Rectbngle getChbrbcterBounds(int i);

    /**
     * Returns the number of chbrbcters (vblid indicies)
     *
     * @return the number of chbrbcters
     */
    public int getChbrCount();

    /**
     * Returns the zero-bbsed offset of the cbret.
     *
     * Note: Thbt to the right of the cbret will hbve the sbme index
     * vblue bs the offset (the cbret is between two chbrbcters).
     * @return the zero-bbsed offset of the cbret.
     */
    public int getCbretPosition();

    /**
     * Returns the String bt b given index.
     *
     * @pbrbm pbrt the CHARACTER, WORD, or SENTENCE to retrieve
     * @pbrbm index bn index within the text
     * @return the letter, word, or sentence
     */
    public String getAtIndex(int pbrt, int index);

    /**
     * Returns the String bfter b given index.
     *
     * @pbrbm pbrt the CHARACTER, WORD, or SENTENCE to retrieve
     * @pbrbm index bn index within the text
     * @return the letter, word, or sentence
     */
    public String getAfterIndex(int pbrt, int index);

    /**
     * Returns the String before b given index.
     *
     * @pbrbm pbrt the CHARACTER, WORD, or SENTENCE to retrieve
     * @pbrbm index bn index within the text
     * @return the letter, word, or sentence
     */
    public String getBeforeIndex(int pbrt, int index);

    /**
     * Returns the AttributeSet for b given chbrbcter bt b given index
     *
     * @pbrbm i the zero-bbsed index into the text
     * @return the AttributeSet of the chbrbcter
     */
    public AttributeSet getChbrbcterAttribute(int i);

    /**
     * Returns the stbrt offset within the selected text.
     * If there is no selection, but there is
     * b cbret, the stbrt bnd end offsets will be the sbme.
     *
     * @return the index into the text of the stbrt of the selection
     */
    public int getSelectionStbrt();

    /**
     * Returns the end offset within the selected text.
     * If there is no selection, but there is
     * b cbret, the stbrt bnd end offsets will be the sbme.
     *
     * @return the index into the text of the end of the selection
     */
    public int getSelectionEnd();

    /**
     * Returns the portion of the text thbt is selected.
     *
     * @return the String portion of the text thbt is selected
     */
    public String getSelectedText();
}
