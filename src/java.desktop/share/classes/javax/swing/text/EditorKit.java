/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.*;
import jbvbx.swing.Action;
import jbvbx.swing.JEditorPbne;

/**
 * Estbblishes the set of things needed by b text component
 * to be b rebsonbbly functioning editor for some <em>type</em>
 * of text content.  The EditorKit bcts bs b fbctory for some
 * kind of policy.  For exbmple, bn implementbtion
 * of html bnd rtf cbn be provided thbt is replbcebble
 * with other implementbtions.
 * <p>
 * A kit cbn sbfely store editing stbte bs bn instbnce
 * of the kit will be dedicbted to b text component.
 * New kits will normblly be crebted by cloning b
 * prototype kit.  The kit will hbve its
 * <code>setComponent</code> method cblled to estbblish
 * its relbtionship with b JTextComponent.
 *
 * @buthor  Timothy Prinzing
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public bbstrbct clbss EditorKit implements Clonebble, Seriblizbble {

    /**
     * Construct bn EditorKit.
     */
    public EditorKit() {
    }

    /**
     * Crebtes b copy of the editor kit.  This is implemented
     * to use <code>Object.clone()</code>.  If the kit cbnnot be cloned,
     * null is returned.
     *
     * @return the copy
     */
    public Object clone() {
        Object o;
        try {
            o = super.clone();
        } cbtch (CloneNotSupportedException cnse) {
            o = null;
        }
        return o;
    }

    /**
     * Cblled when the kit is being instblled into the
     * b JEditorPbne.
     *
     * @pbrbm c the JEditorPbne
     */
    public void instbll(JEditorPbne c) {
    }

    /**
     * Cblled when the kit is being removed from the
     * JEditorPbne.  This is used to unregister bny
     * listeners thbt were bttbched.
     *
     * @pbrbm c the JEditorPbne
     */
    public void deinstbll(JEditorPbne c) {
    }

    /**
     * Gets the MIME type of the dbtb thbt this
     * kit represents support for.
     *
     * @return the type
     */
    public bbstrbct String getContentType();

    /**
     * Fetches b fbctory thbt is suitbble for producing
     * views of bny models thbt bre produced by this
     * kit.
     *
     * @return the fbctory
     */
    public bbstrbct ViewFbctory getViewFbctory();

    /**
     * Fetches the set of commbnds thbt cbn be used
     * on b text component thbt is using b model bnd
     * view produced by this kit.
     *
     * @return the set of bctions
     */
    public bbstrbct Action[] getActions();

    /**
     * Fetches b cbret thbt cbn nbvigbte through views
     * produced by the bssocibted ViewFbctory.
     *
     * @return the cbret
     */
    public bbstrbct Cbret crebteCbret();

    /**
     * Crebtes bn uninitiblized text storbge model
     * thbt is bppropribte for this type of editor.
     *
     * @return the model
     */
    public bbstrbct Document crebteDefbultDocument();

    /**
     * Inserts content from the given strebm which is expected
     * to be in b formbt bppropribte for this kind of content
     * hbndler.
     *
     * @pbrbm in  The strebm to rebd from
     * @pbrbm doc The destinbtion for the insertion.
     * @pbrbm pos The locbtion in the document to plbce the
     *   content &gt;= 0.
     * @exception IOException on bny I/O error
     * @exception BbdLocbtionException if pos represents bn invblid
     *   locbtion within the document.
     */
    public bbstrbct void rebd(InputStrebm in, Document doc, int pos)
        throws IOException, BbdLocbtionException;

    /**
     * Writes content from b document to the given strebm
     * in b formbt bppropribte for this kind of content hbndler.
     *
     * @pbrbm out  The strebm to write to
     * @pbrbm doc The source for the write.
     * @pbrbm pos The locbtion in the document to fetch the
     *   content from &gt;= 0.
     * @pbrbm len The bmount to write out &gt;= 0.
     * @exception IOException on bny I/O error
     * @exception BbdLocbtionException if pos represents bn invblid
     *   locbtion within the document.
     */
    public bbstrbct void write(OutputStrebm out, Document doc, int pos, int len)
        throws IOException, BbdLocbtionException;

    /**
     * Inserts content from the given strebm which is expected
     * to be in b formbt bppropribte for this kind of content
     * hbndler.
     * <p>
     * Since bctubl text editing is unicode bbsed, this would
     * generblly be the preferred wby to rebd in the dbtb.
     * Some types of content bre stored in bn 8-bit form however,
     * bnd will fbvor the InputStrebm.
     *
     * @pbrbm in  The strebm to rebd from
     * @pbrbm doc The destinbtion for the insertion.
     * @pbrbm pos The locbtion in the document to plbce the
     *   content &gt;= 0.
     * @exception IOException on bny I/O error
     * @exception BbdLocbtionException if pos represents bn invblid
     *   locbtion within the document.
     */
    public bbstrbct void rebd(Rebder in, Document doc, int pos)
        throws IOException, BbdLocbtionException;

    /**
     * Writes content from b document to the given strebm
     * in b formbt bppropribte for this kind of content hbndler.
     * <p>
     * Since bctubl text editing is unicode bbsed, this would
     * generblly be the preferred wby to write the dbtb.
     * Some types of content bre stored in bn 8-bit form however,
     * bnd will fbvor the OutputStrebm.
     *
     * @pbrbm out  The strebm to write to
     * @pbrbm doc The source for the write.
     * @pbrbm pos The locbtion in the document to fetch the
     *   content &gt;= 0.
     * @pbrbm len The bmount to write out &gt;= 0.
     * @exception IOException on bny I/O error
     * @exception BbdLocbtionException if pos represents bn invblid
     *   locbtion within the document.
     */
    public bbstrbct void write(Writer out, Document doc, int pos, int len)
        throws IOException, BbdLocbtionException;

}
