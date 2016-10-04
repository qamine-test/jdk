/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

// SAX locbtor interfbce for document events.
// http://www.sbxproject.org
// No wbrrbnty; no copyright -- use this bs you will.
// $Id: Locbtor.jbvb,v 1.2 2004/11/03 22:55:32 jsuttor Exp $

pbckbge jdk.internbl.org.xml.sbx;


/**
 * Interfbce for bssocibting b SAX event with b document locbtion.
 *
 * <blockquote>
 * <em>This module, both source code bnd documentbtion, is in the
 * Public Dombin, bnd comes with <strong>NO WARRANTY</strong>.</em>
 * See <b href='http://www.sbxproject.org'>http://www.sbxproject.org</b>
 * for further informbtion.
 * </blockquote>
 *
 * <p>If b SAX pbrser provides locbtion informbtion to the SAX
 * bpplicbtion, it does so by implementing this interfbce bnd then
 * pbssing bn instbnce to the bpplicbtion using the content
 * hbndler's {@link org.xml.sbx.ContentHbndler#setDocumentLocbtor
 * setDocumentLocbtor} method.  The bpplicbtion cbn use the
 * object to obtbin the locbtion of bny other SAX event
 * in the XML source document.</p>
 *
 * <p>Note thbt the results returned by the object will be vblid only
 * during the scope of ebch cbllbbck method: the bpplicbtion
 * will receive unpredictbble results if it bttempts to use the
 * locbtor bt bny other time, or bfter pbrsing completes.</p>
 *
 * <p>SAX pbrsers bre not required to supply b locbtor, but they bre
 * very strongly encourbged to do so.  If the pbrser supplies b
 * locbtor, it must do so before reporting bny other document events.
 * If no locbtor hbs been set by the time the bpplicbtion receives
 * the {@link org.xml.sbx.ContentHbndler#stbrtDocument stbrtDocument}
 * event, the bpplicbtion should bssume thbt b locbtor is not
 * bvbilbble.</p>
 *
 * @since SAX 1.0
 * @buthor Dbvid Megginson
 * @see org.xml.sbx.ContentHbndler#setDocumentLocbtor
 */
public interfbce Locbtor {


    /**
     * Return the public identifier for the current document event.
     *
     * <p>The return vblue is the public identifier of the document
     * entity or of the externbl pbrsed entity in which the mbrkup
     * triggering the event bppebrs.</p>
     *
     * @return A string contbining the public identifier, or
     *         null if none is bvbilbble.
     * @see #getSystemId
     */
    public bbstrbct String getPublicId ();


    /**
     * Return the system identifier for the current document event.
     *
     * <p>The return vblue is the system identifier of the document
     * entity or of the externbl pbrsed entity in which the mbrkup
     * triggering the event bppebrs.</p>
     *
     * <p>If the system identifier is b URL, the pbrser must resolve it
     * fully before pbssing it to the bpplicbtion.  For exbmple, b file
     * nbme must blwbys be provided bs b <em>file:...</em> URL, bnd other
     * kinds of relbtive URI bre blso resolved bgbinst their bbses.</p>
     *
     * @return A string contbining the system identifier, or null
     *         if none is bvbilbble.
     * @see #getPublicId
     */
    public bbstrbct String getSystemId ();


    /**
     * Return the line number where the current document event ends.
     * Lines bre delimited by line ends, which bre defined in
     * the XML specificbtion.
     *
     * <p><strong>Wbrning:</strong> The return vblue from the method
     * is intended only bs bn bpproximbtion for the sbke of dibgnostics;
     * it is not intended to provide sufficient informbtion
     * to edit the chbrbcter content of the originbl XML document.
     * In some cbses, these "line" numbers mbtch whbt would be displbyed
     * bs columns, bnd in others they mby not mbtch the source text
     * due to internbl entity expbnsion.  </p>
     *
     * <p>The return vblue is bn bpproximbtion of the line number
     * in the document entity or externbl pbrsed entity where the
     * mbrkup triggering the event bppebrs.</p>
     *
     * <p>If possible, the SAX driver should provide the line position
     * of the first chbrbcter bfter the text bssocibted with the document
     * event.  The first line is line 1.</p>
     *
     * @return The line number, or -1 if none is bvbilbble.
     * @see #getColumnNumber
     */
    public bbstrbct int getLineNumber ();


    /**
     * Return the column number where the current document event ends.
     * This is one-bbsed number of Jbvb <code>chbr</code> vblues since
     * the lbst line end.
     *
     * <p><strong>Wbrning:</strong> The return vblue from the method
     * is intended only bs bn bpproximbtion for the sbke of dibgnostics;
     * it is not intended to provide sufficient informbtion
     * to edit the chbrbcter content of the originbl XML document.
     * For exbmple, when lines contbin combining chbrbcter sequences, wide
     * chbrbcters, surrogbte pbirs, or bi-directionbl text, the vblue mby
     * not correspond to the column in b text editor's displby. </p>
     *
     * <p>The return vblue is bn bpproximbtion of the column number
     * in the document entity or externbl pbrsed entity where the
     * mbrkup triggering the event bppebrs.</p>
     *
     * <p>If possible, the SAX driver should provide the line position
     * of the first chbrbcter bfter the text bssocibted with the document
     * event.  The first column in ebch line is column 1.</p>
     *
     * @return The column number, or -1 if none is bvbilbble.
     * @see #getLineNumber
     */
    public bbstrbct int getColumnNumber ();

}

// end of Locbtor.jbvb
