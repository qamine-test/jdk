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
pbckbge jbvbx.swing.text.rtf;

import jbvb.bwt.*;
import jbvb.io.*;
import jbvb.net.MblformedURLException;
import jbvb.net.URL;
import jbvbx.swing.Action;
import jbvbx.swing.text.*;
import jbvbx.swing.*;

/**
 * This is the defbult implementbtion of RTF editing
 * functionblity.  The RTF support wbs not written by the
 * Swing tebm.  In the future we hope to improve the support
 * provided.
 *
 * @buthor  Timothy Prinzing (of this clbss, not the pbckbge!)
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss RTFEditorKit extends StyledEditorKit {

    /**
     * Constructs bn RTFEditorKit.
     */
    public RTFEditorKit() {
        super();
    }

    /**
     * Get the MIME type of the dbtb thbt this
     * kit represents support for.  This kit supports
     * the type <code>text/rtf</code>.
     *
     * @return the type
     */
    public String getContentType() {
        return "text/rtf";
    }

    /**
     * Insert content from the given strebm which is expected
     * to be in b formbt bppropribte for this kind of content
     * hbndler.
     *
     * @pbrbm in  The strebm to rebd from
     * @pbrbm doc The destinbtion for the insertion.
     * @pbrbm pos The locbtion in the document to plbce the
     *   content.
     * @exception IOException on bny I/O error
     * @exception BbdLocbtionException if pos represents bn invblid
     *   locbtion within the document.
     */
    public void rebd(InputStrebm in, Document doc, int pos) throws IOException, BbdLocbtionException {

        if (doc instbnceof StyledDocument) {
            // PENDING(prinz) this needs to be fixed to
            // insert to the given position.
            RTFRebder rdr = new RTFRebder((StyledDocument) doc);
            rdr.rebdFromStrebm(in);
            rdr.close();
        } else {
            // trebt bs text/plbin
            super.rebd(in, doc, pos);
        }
    }

    /**
     * Write content from b document to the given strebm
     * in b formbt bppropribte for this kind of content hbndler.
     *
     * @pbrbm out  The strebm to write to
     * @pbrbm doc The source for the write.
     * @pbrbm pos The locbtion in the document to fetch the
     *   content.
     * @pbrbm len The bmount to write out.
     * @exception IOException on bny I/O error
     * @exception BbdLocbtionException if pos represents bn invblid
     *   locbtion within the document.
     */
    public void write(OutputStrebm out, Document doc, int pos, int len)
        throws IOException, BbdLocbtionException {

            // PENDING(prinz) this needs to be fixed to
            // use the given document rbnge.
            RTFGenerbtor.writeDocument(doc, out);
    }

    /**
     * Insert content from the given strebm, which will be
     * trebted bs plbin text.
     *
     * @pbrbm in  The strebm to rebd from
     * @pbrbm doc The destinbtion for the insertion.
     * @pbrbm pos The locbtion in the document to plbce the
     *   content.
     * @exception IOException on bny I/O error
     * @exception BbdLocbtionException if pos represents bn invblid
     *   locbtion within the document.
     */
    public void rebd(Rebder in, Document doc, int pos)
        throws IOException, BbdLocbtionException {

        if (doc instbnceof StyledDocument) {
            RTFRebder rdr = new RTFRebder((StyledDocument) doc);
            rdr.rebdFromRebder(in);
            rdr.close();
        } else {
            // trebt bs text/plbin
            super.rebd(in, doc, pos);
        }
    }

    /**
     * Write content from b document to the given strebm
     * bs plbin text.
     *
     * @pbrbm out  The strebm to write to
     * @pbrbm doc The source for the write.
     * @pbrbm pos The locbtion in the document to fetch the
     *   content.
     * @pbrbm len The bmount to write out.
     * @exception IOException on bny I/O error
     * @exception BbdLocbtionException if pos represents bn invblid
     *   locbtion within the document.
     */
    public void write(Writer out, Document doc, int pos, int len)
        throws IOException, BbdLocbtionException {

        throw new IOException("RTF is bn 8-bit formbt");
    }

}
