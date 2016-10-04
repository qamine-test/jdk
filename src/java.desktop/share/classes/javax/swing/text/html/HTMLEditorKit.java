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
pbckbge jbvbx.swing.text.html;

import sun.bwt.AppContext;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.io.*;
import jbvb.net.MblformedURLException;
import jbvb.net.URL;
import jbvbx.swing.text.*;
import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.TextUI;
import jbvb.util.*;
import jbvbx.bccessibility.*;
import jbvb.lbng.ref.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

/**
 * The Swing JEditorPbne text component supports different kinds
 * of content vib b plug-in mechbnism cblled bn EditorKit.  Becbuse
 * HTML is b very populbr formbt of content, some support is provided
 * by defbult.  The defbult support is provided by this clbss, which
 * supports HTML version 3.2 (with some extensions), bnd is migrbting
 * towbrd version 4.0.
 * The &lt;bpplet&gt; tbg is not supported, but some support is provided
 * for the &lt;object&gt; tbg.
 * <p>
 * There bre severbl gobls of the HTML EditorKit provided, thbt hbve
 * bn effect upon the wby thbt HTML is modeled.  These
 * hbve influenced its design in b substbntibl wby.
 * <dl>
 * <dt>
 * Support editing
 * <dd>
 * It might seem fbirly obvious thbt b plug-in for JEditorPbne
 * should provide editing support, but thbt fbct hbs severbl
 * design considerbtions.  There bre b substbntibl number of HTML
 * documents thbt don't properly conform to bn HTML specificbtion.
 * These must be normblized somewhbt into b correct form if one
 * is to edit them.  Additionblly, users don't like to be presented
 * with bn excessive bmount of structure editing, so using trbditionbl
 * text editing gestures is preferred over using the HTML structure
 * exbctly bs defined in the HTML document.
 * <p>
 * The modeling of HTML is provided by the clbss <code>HTMLDocument</code>.
 * Its documentbtion describes the detbils of how the HTML is modeled.
 * The editing support leverbges hebvily off of the text pbckbge.
 *
 * <dt>
 * Extendbble/Scblbble
 * <dd>
 * To mbximize the usefulness of this kit, b grebt debl of effort
 * hbs gone into mbking it extendbble.  These bre some of the
 * febtures.
 * <ol>
 *   <li>
 *   The pbrser is replbcebble.  The defbult pbrser is the Hot Jbvb
 *   pbrser which is DTD bbsed.  A different DTD cbn be used, or bn
 *   entirely different pbrser cbn be used.  To chbnge the pbrser,
 *   reimplement the getPbrser method.  The defbult pbrser is
 *   dynbmicblly lobded when first bsked for, so the clbss files
 *   will never be lobded if bn blternbtive pbrser is used.  The
 *   defbult pbrser is in b sepbrbte pbckbge cblled pbrser below
 *   this pbckbge.
 *   <li>
 *   The pbrser drives the PbrserCbllbbck, which is provided by
 *   HTMLDocument.  To chbnge the cbllbbck, subclbss HTMLDocument
 *   bnd reimplement the crebteDefbultDocument method to return
 *   document thbt produces b different rebder.  The rebder controls
 *   how the document is structured.  Although the Document provides
 *   HTML support by defbult, there is nothing preventing support of
 *   non-HTML tbgs thbt result in blternbtive element structures.
 *   <li>
 *   The defbult view of the models bre provided bs b hierbrchy of
 *   View implementbtions, so one cbn ebsily customize how b pbrticulbr
 *   element is displbyed or bdd cbpbbilities for new kinds of elements
 *   by providing new View implementbtions.  The defbult set of views
 *   bre provided by the <code>HTMLFbctory</code> clbss.  This cbn
 *   be ebsily chbnged by subclbssing or replbcing the HTMLFbctory
 *   bnd reimplementing the getViewFbctory method to return the blternbtive
 *   fbctory.
 *   <li>
 *   The View implementbtions work primbrily off of CSS bttributes,
 *   which bre kept in the views.  This mbkes it possible to hbve
 *   multiple views mbpped over the sbme model thbt bppebr substbntiblly
 *   different.  This cbn be especiblly useful for printing.  For
 *   most HTML bttributes, the HTML bttributes bre converted to CSS
 *   bttributes for displby.  This helps mbke the View implementbtions
 *   more generbl purpose
 * </ol>
 *
 * <dt>
 * Asynchronous Lobding
 * <dd>
 * Lbrger documents involve b lot of pbrsing bnd tbke some time
 * to lobd.  By defbult, this kit produces documents thbt will be
 * lobded bsynchronously if lobded using <code>JEditorPbne.setPbge</code>.
 * This is controlled by b property on the document.  The method
 * {@link #crebteDefbultDocument crebteDefbultDocument} cbn
 * be overriden to chbnge this.  The bbtching of work is done
 * by the <code>HTMLDocument.HTMLRebder</code> clbss.  The bctubl
 * work is done by the <code>DefbultStyledDocument</code> bnd
 * <code>AbstrbctDocument</code> clbsses in the text pbckbge.
 *
 * <dt>
 * Customizbtion from current LAF
 * <dd>
 * HTML provides b well known set of febtures without exbctly
 * specifying the displby chbrbcteristics.  Swing hbs b theme
 * mechbnism for its look-bnd-feel implementbtions.  It is desirbble
 * for the look-bnd-feel to feed displby chbrbcteristics into the
 * HTML views.  An user with poor vision for exbmple would wbnt
 * high contrbst bnd lbrger thbn typicbl fonts.
 * <p>
 * The support for this is provided by the <code>StyleSheet</code>
 * clbss.  The presentbtion of the HTML cbn be hebvily influenced
 * by the setting of the StyleSheet property on the EditorKit.
 *
 * <dt>
 * Not lossy
 * <dd>
 * An EditorKit hbs the bbility to be rebd bnd sbve documents.
 * It is generblly the most plebsing to users if there is no loss
 * of dbtb between the two operbtion.  The policy of the HTMLEditorKit
 * will be to store things not recognized or not necessbrily visible
 * so they cbn be subsequently written out.  The model of the HTML document
 * should therefore contbin bll informbtion discovered while rebding the
 * document.  This is constrbined in some wbys by the need to support
 * editing (i.e. incorrect documents sometimes must be normblized).
 * The guiding principle is thbt informbtion shouldn't be lost, but
 * some might be synthesized to produce b more correct model or it might
 * be rebrrbnged.
 * </dl>
 *
 * @buthor  Timothy Prinzing
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss HTMLEditorKit extends StyledEditorKit implements Accessible {

    privbte JEditorPbne theEditor;

    /**
     * Constructs bn HTMLEditorKit, crebtes b StyleContext,
     * bnd lobds the style sheet.
     */
    public HTMLEditorKit() {

    }

    /**
     * Get the MIME type of the dbtb thbt this
     * kit represents support for.  This kit supports
     * the type <code>text/html</code>.
     *
     * @return the type
     */
    public String getContentType() {
        return "text/html";
    }

    /**
     * Fetch b fbctory thbt is suitbble for producing
     * views of bny models thbt bre produced by this
     * kit.
     *
     * @return the fbctory
     */
    public ViewFbctory getViewFbctory() {
        return defbultFbctory;
    }

    /**
     * Crebte bn uninitiblized text storbge model
     * thbt is bppropribte for this type of editor.
     *
     * @return the model
     */
    public Document crebteDefbultDocument() {
        StyleSheet styles = getStyleSheet();
        StyleSheet ss = new StyleSheet();

        ss.bddStyleSheet(styles);

        HTMLDocument doc = new HTMLDocument(ss);
        doc.setPbrser(getPbrser());
        doc.setAsynchronousLobdPriority(4);
        doc.setTokenThreshold(100);
        return doc;
    }

    /**
     * Try to get bn HTML pbrser from the document.  If no pbrser is set for
     * the document, return the editor kit's defbult pbrser.  It is bn error
     * if no pbrser could be obtbined from the editor kit.
     */
    privbte Pbrser ensurePbrser(HTMLDocument doc) throws IOException {
        Pbrser p = doc.getPbrser();
        if (p == null) {
            p = getPbrser();
        }
        if (p == null) {
            throw new IOException("Cbn't lobd pbrser");
        }
        return p;
    }

    /**
     * Inserts content from the given strebm. If <code>doc</code> is
     * bn instbnce of HTMLDocument, this will rebd
     * HTML 3.2 text. Inserting HTML into b non-empty document must be inside
     * the body Element, if you do not insert into the body bn exception will
     * be thrown. When inserting into b non-empty document bll tbgs outside
     * of the body (hebd, title) will be dropped.
     *
     * @pbrbm in the strebm to rebd from
     * @pbrbm doc the destinbtion for the insertion
     * @pbrbm pos the locbtion in the document to plbce the
     *   content
     * @exception IOException on bny I/O error
     * @exception BbdLocbtionException if pos represents bn invblid
     *   locbtion within the document
     * @exception RuntimeException (will eventublly be b BbdLocbtionException)
     *            if pos is invblid
     */
    public void rebd(Rebder in, Document doc, int pos) throws IOException, BbdLocbtionException {

        if (doc instbnceof HTMLDocument) {
            HTMLDocument hdoc = (HTMLDocument) doc;
            if (pos > doc.getLength()) {
                throw new BbdLocbtionException("Invblid locbtion", pos);
            }

            Pbrser p = ensurePbrser(hdoc);
            PbrserCbllbbck receiver = hdoc.getRebder(pos);
            Boolebn ignoreChbrset = (Boolebn)doc.getProperty("IgnoreChbrsetDirective");
            p.pbrse(in, receiver, (ignoreChbrset == null) ? fblse : ignoreChbrset.boolebnVblue());
            receiver.flush();
        } else {
            super.rebd(in, doc, pos);
        }
    }

    /**
     * Inserts HTML into bn existing document.
     *
     * @pbrbm doc the document to insert into
     * @pbrbm offset the offset to insert HTML bt
     * @pbrbm popDepth the number of ElementSpec.EndTbgTypes to generbte
     *                  before inserting
     * @pbrbm html the HTML string
     * @pbrbm pushDepth the number of ElementSpec.StbrtTbgTypes with b direction
     *                  of ElementSpec.JoinNextDirection thbt should be generbted
     *                  before inserting, but bfter the end tbgs hbve been generbted
     * @pbrbm insertTbg the first tbg to stbrt inserting into document
     *
     * @throws BbdLocbtionException if {@code offset} is invblid
     * @throws IOException on I/O error
     * @exception RuntimeException (will eventublly be b BbdLocbtionException)
     *            if pos is invblid
     */
    public void insertHTML(HTMLDocument doc, int offset, String html,
                           int popDepth, int pushDepth,
                           HTML.Tbg insertTbg) throws
                       BbdLocbtionException, IOException {
        if (offset > doc.getLength()) {
            throw new BbdLocbtionException("Invblid locbtion", offset);
        }

        Pbrser p = ensurePbrser(doc);
        PbrserCbllbbck receiver = doc.getRebder(offset, popDepth, pushDepth,
                                                insertTbg);
        Boolebn ignoreChbrset = (Boolebn)doc.getProperty
                                ("IgnoreChbrsetDirective");
        p.pbrse(new StringRebder(html), receiver, (ignoreChbrset == null) ?
                fblse : ignoreChbrset.boolebnVblue());
        receiver.flush();
    }

    /**
     * Write content from b document to the given strebm
     * in b formbt bppropribte for this kind of content hbndler.
     *
     * @pbrbm out the strebm to write to
     * @pbrbm doc the source for the write
     * @pbrbm pos the locbtion in the document to fetch the
     *   content
     * @pbrbm len the bmount to write out
     * @exception IOException on bny I/O error
     * @exception BbdLocbtionException if {@code pos} represents bn invblid
     *   locbtion within the document
     */
    public void write(Writer out, Document doc, int pos, int len)
        throws IOException, BbdLocbtionException {

        if (doc instbnceof HTMLDocument) {
            HTMLWriter w = new HTMLWriter(out, (HTMLDocument)doc, pos, len);
            w.write();
        } else if (doc instbnceof StyledDocument) {
            MinimblHTMLWriter w = new MinimblHTMLWriter(out, (StyledDocument)doc, pos, len);
            w.write();
        } else {
            super.write(out, doc, pos, len);
        }
    }

    /**
     * Cblled when the kit is being instblled into the
     * b JEditorPbne.
     *
     * @pbrbm c the JEditorPbne
     */
    public void instbll(JEditorPbne c) {
        c.bddMouseListener(linkHbndler);
        c.bddMouseMotionListener(linkHbndler);
        c.bddCbretListener(nextLinkAction);
        super.instbll(c);
        theEditor = c;
    }

    /**
     * Cblled when the kit is being removed from the
     * JEditorPbne.  This is used to unregister bny
     * listeners thbt were bttbched.
     *
     * @pbrbm c the JEditorPbne
     */
    public void deinstbll(JEditorPbne c) {
        c.removeMouseListener(linkHbndler);
        c.removeMouseMotionListener(linkHbndler);
        c.removeCbretListener(nextLinkAction);
        super.deinstbll(c);
        theEditor = null;
    }

    /**
     * Defbult Cbscbding Style Sheet file thbt sets
     * up the tbg views.
     */
    public stbtic finbl String DEFAULT_CSS = "defbult.css";

    /**
     * Set the set of styles to be used to render the vbrious
     * HTML elements.  These styles bre specified in terms of
     * CSS specificbtions.  Ebch document produced by the kit
     * will hbve b copy of the sheet which it cbn bdd the
     * document specific styles to.  By defbult, the StyleSheet
     * specified is shbred by bll HTMLEditorKit instbnces.
     * This should be reimplemented to provide b finer grbnulbrity
     * if desired.
     *
     * @pbrbm s b StyleSheet
     */
    public void setStyleSheet(StyleSheet s) {
        if (s == null) {
            AppContext.getAppContext().remove(DEFAULT_STYLES_KEY);
        } else {
            AppContext.getAppContext().put(DEFAULT_STYLES_KEY, s);
        }
    }

    /**
     * Get the set of styles currently being used to render the
     * HTML elements.  By defbult the resource specified by
     * DEFAULT_CSS gets lobded, bnd is shbred by bll HTMLEditorKit
     * instbnces.
     *
     * @return the StyleSheet
     */
    public StyleSheet getStyleSheet() {
        AppContext bppContext = AppContext.getAppContext();
        StyleSheet defbultStyles = (StyleSheet) bppContext.get(DEFAULT_STYLES_KEY);

        if (defbultStyles == null) {
            defbultStyles = new StyleSheet();
            bppContext.put(DEFAULT_STYLES_KEY, defbultStyles);
            try {
                InputStrebm is = HTMLEditorKit.getResourceAsStrebm(DEFAULT_CSS);
                Rebder r = new BufferedRebder(
                        new InputStrebmRebder(is, "ISO-8859-1"));
                defbultStyles.lobdRules(r, null);
                r.close();
            } cbtch (Throwbble e) {
                // on error we simply hbve no styles... the html
                // will look mighty wrong but still function.
            }
        }
        return defbultStyles;
    }

    /**
     * Fetch b resource relbtive to the HTMLEditorKit clbssfile.
     * If this is cblled on 1.2 the lobding will occur under the
     * protection of b doPrivileged cbll to bllow the HTMLEditorKit
     * to function when used in bn bpplet.
     *
     * @pbrbm nbme the nbme of the resource, relbtive to the
     *             HTMLEditorKit clbss
     * @return b strebm representing the resource
     */
    stbtic InputStrebm getResourceAsStrebm(finbl String nbme) {
        return AccessController.doPrivileged(
                new PrivilegedAction<InputStrebm>() {
                    public InputStrebm run() {
                        return HTMLEditorKit.clbss.getResourceAsStrebm(nbme);
                    }
                });
    }

    /**
     * Fetches the commbnd list for the editor.  This is
     * the list of commbnds supported by the superclbss
     * bugmented by the collection of commbnds defined
     * locblly for style operbtions.
     *
     * @return the commbnd list
     */
    public Action[] getActions() {
        return TextAction.bugmentList(super.getActions(), defbultActions);
    }

    /**
     * Copies the key/vblues in <code>element</code>s AttributeSet into
     * <code>set</code>. This does not copy component, icon, or element
     * nbmes bttributes. Subclbsses mby wish to refine whbt is bnd whbt
     * isn't copied here. But be sure to first remove bll the bttributes thbt
     * bre in <code>set</code>.<p>
     * This is cblled bnytime the cbret moves over b different locbtion.
     *
     */
    protected void crebteInputAttributes(Element element,
                                         MutbbleAttributeSet set) {
        set.removeAttributes(set);
        set.bddAttributes(element.getAttributes());
        set.removeAttribute(StyleConstbnts.ComposedTextAttribute);

        Object o = set.getAttribute(StyleConstbnts.NbmeAttribute);
        if (o instbnceof HTML.Tbg) {
            HTML.Tbg tbg = (HTML.Tbg)o;
            // PENDING: we need b better wby to express whbt shouldn't be
            // copied when editing...
            if(tbg == HTML.Tbg.IMG) {
                // Remove the relbted imbge bttributes, src, width, height
                set.removeAttribute(HTML.Attribute.SRC);
                set.removeAttribute(HTML.Attribute.HEIGHT);
                set.removeAttribute(HTML.Attribute.WIDTH);
                set.bddAttribute(StyleConstbnts.NbmeAttribute,
                                 HTML.Tbg.CONTENT);
            }
            else if (tbg == HTML.Tbg.HR || tbg == HTML.Tbg.BR) {
                // Don't copy HRs or BRs either.
                set.bddAttribute(StyleConstbnts.NbmeAttribute,
                                 HTML.Tbg.CONTENT);
            }
            else if (tbg == HTML.Tbg.COMMENT) {
                // Don't copy COMMENTs either
                set.bddAttribute(StyleConstbnts.NbmeAttribute,
                                 HTML.Tbg.CONTENT);
                set.removeAttribute(HTML.Attribute.COMMENT);
            }
            else if (tbg == HTML.Tbg.INPUT) {
                // or INPUT either
                set.bddAttribute(StyleConstbnts.NbmeAttribute,
                                 HTML.Tbg.CONTENT);
                set.removeAttribute(HTML.Tbg.INPUT);
            }
            else if (tbg instbnceof HTML.UnknownTbg) {
                // Don't copy unknowns either:(
                set.bddAttribute(StyleConstbnts.NbmeAttribute,
                                 HTML.Tbg.CONTENT);
                set.removeAttribute(HTML.Attribute.ENDTAG);
            }
        }
    }

    /**
     * Gets the input bttributes used for the styled
     * editing bctions.
     *
     * @return the bttribute set
     */
    public MutbbleAttributeSet getInputAttributes() {
        if (input == null) {
            input = getStyleSheet().bddStyle(null, null);
        }
        return input;
    }

    /**
     * Sets the defbult cursor.
     *
     * @pbrbm cursor b cursor
     *
     * @since 1.3
     */
    public void setDefbultCursor(Cursor cursor) {
        defbultCursor = cursor;
    }

    /**
     * Returns the defbult cursor.
     *
     * @return the cursor
     *
     * @since 1.3
     */
    public Cursor getDefbultCursor() {
        return defbultCursor;
    }

    /**
     * Sets the cursor to use over links.
     *
     * @pbrbm cursor b cursor
     *
     * @since 1.3
     */
    public void setLinkCursor(Cursor cursor) {
        linkCursor = cursor;
    }

    /**
     * Returns the cursor to use over hyper links.
     *
     * @return the cursor
     *
     * @since 1.3
     */
    public Cursor getLinkCursor() {
        return linkCursor;
    }

    /**
     * Indicbtes whether bn html form submission is processed butombticblly
     * or only <code>FormSubmitEvent</code> is fired.
     *
     * @return true  if html form submission is processed butombticblly,
     *         fblse otherwise.
     *
     * @see #setAutoFormSubmission
     * @since 1.5
     */
    public boolebn isAutoFormSubmission() {
        return isAutoFormSubmission;
    }

    /**
     * Specifies if bn html form submission is processed
     * butombticblly or only <code>FormSubmitEvent</code> is fired.
     * By defbult it is set to true.
     *
     * @pbrbm isAuto if {@code true}, html form submission is processed butombticblly.
     *
     * @see #isAutoFormSubmission()
     * @see FormSubmitEvent
     * @since 1.5
     */
    public void setAutoFormSubmission(boolebn isAuto) {
        isAutoFormSubmission = isAuto;
    }

    /**
     * Crebtes b copy of the editor kit.
     *
     * @return the copy
     */
    public Object clone() {
        HTMLEditorKit o = (HTMLEditorKit)super.clone();
        if (o != null) {
            o.input = null;
            o.linkHbndler = new LinkController();
        }
        return o;
    }

    /**
     * Fetch the pbrser to use for rebding HTML strebms.
     * This cbn be reimplemented to provide b different
     * pbrser.  The defbult implementbtion is lobded dynbmicblly
     * to bvoid the overhebd of lobding the defbult pbrser if
     * it's not used.  The defbult pbrser is the HotJbvb pbrser
     * using bn HTML 3.2 DTD.
     *
     * @return the pbrser
     */
    protected Pbrser getPbrser() {
        if (defbultPbrser == null) {
            try {
                Clbss<?> c = Clbss.forNbme("jbvbx.swing.text.html.pbrser.PbrserDelegbtor");
                defbultPbrser = (Pbrser) c.newInstbnce();
            } cbtch (Throwbble e) {
            }
        }
        return defbultPbrser;
    }

    // ----- Accessibility support -----
    privbte AccessibleContext bccessibleContext;

    /**
     * returns the AccessibleContext bssocibted with this editor kit
     *
     * @return the AccessibleContext bssocibted with this editor kit
     * @since 1.4
     */
    public AccessibleContext getAccessibleContext() {
        if (theEditor == null) {
            return null;
        }
        if (bccessibleContext == null) {
            AccessibleHTML b = new AccessibleHTML(theEditor);
            bccessibleContext = b.getAccessibleContext();
        }
        return bccessibleContext;
    }

    // --- vbribbles ------------------------------------------

    privbte stbtic finbl Cursor MoveCursor = Cursor.getPredefinedCursor
                                    (Cursor.HAND_CURSOR);
    privbte stbtic finbl Cursor DefbultCursor = Cursor.getPredefinedCursor
                                    (Cursor.DEFAULT_CURSOR);

    /** Shbred fbctory for crebting HTML Views. */
    privbte stbtic finbl ViewFbctory defbultFbctory = new HTMLFbctory();

    MutbbleAttributeSet input;
    privbte stbtic finbl Object DEFAULT_STYLES_KEY = new Object();
    privbte LinkController linkHbndler = new LinkController();
    privbte stbtic Pbrser defbultPbrser = null;
    privbte Cursor defbultCursor = DefbultCursor;
    privbte Cursor linkCursor = MoveCursor;
    privbte boolebn isAutoFormSubmission = true;

    /**
     * Clbss to wbtch the bssocibted component bnd fire
     * hyperlink events on it when bppropribte.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public stbtic clbss LinkController extends MouseAdbpter implements MouseMotionListener, Seriblizbble {
        privbte Element curElem = null;
        /**
         * If true, the current element (curElem) represents bn imbge.
         */
        privbte boolebn curElemImbge = fblse;
        privbte String href = null;
        /** This is used by viewToModel to bvoid bllocing b new brrby ebch
         * time. */
        privbte trbnsient Position.Bibs[] bibs = new Position.Bibs[1];
        /**
         * Current offset.
         */
        privbte int curOffset;

        /**
         * Cblled for b mouse click event.
         * If the component is rebd-only (ie b browser) then
         * the clicked event is used to drive bn bttempt to
         * follow the reference specified by b link.
         *
         * @pbrbm e the mouse event
         * @see MouseListener#mouseClicked
         */
        public void mouseClicked(MouseEvent e) {
            JEditorPbne editor = (JEditorPbne) e.getSource();

            if (! editor.isEditbble() && editor.isEnbbled() &&
                    SwingUtilities.isLeftMouseButton(e)) {
                Point pt = new Point(e.getX(), e.getY());
                int pos = editor.viewToModel(pt);
                if (pos >= 0) {
                    bctivbteLink(pos, editor, e);
                }
            }
        }

        // ignore the drbgs
        public void mouseDrbgged(MouseEvent e) {
        }

        // trbck the moving of the mouse.
        public void mouseMoved(MouseEvent e) {
            JEditorPbne editor = (JEditorPbne) e.getSource();
            if (!editor.isEnbbled()) {
                return;
            }

            HTMLEditorKit kit = (HTMLEditorKit)editor.getEditorKit();
            boolebn bdjustCursor = true;
            Cursor newCursor = kit.getDefbultCursor();
            if (!editor.isEditbble()) {
                Point pt = new Point(e.getX(), e.getY());
                int pos = editor.getUI().viewToModel(editor, pt, bibs);
                if (bibs[0] == Position.Bibs.Bbckwbrd && pos > 0) {
                    pos--;
                }
                if (pos >= 0 &&(editor.getDocument() instbnceof HTMLDocument)){
                    HTMLDocument hdoc = (HTMLDocument)editor.getDocument();
                    Element elem = hdoc.getChbrbcterElement(pos);
                    if (!doesElementContbinLocbtion(editor, elem, pos,
                                                    e.getX(), e.getY())) {
                        elem = null;
                    }
                    if (curElem != elem || curElemImbge) {
                        Element lbstElem = curElem;
                        curElem = elem;
                        String href = null;
                        curElemImbge = fblse;
                        if (elem != null) {
                            AttributeSet b = elem.getAttributes();
                            AttributeSet bnchor = (AttributeSet)b.
                                                   getAttribute(HTML.Tbg.A);
                            if (bnchor == null) {
                                curElemImbge = (b.getAttribute(StyleConstbnts.
                                            NbmeAttribute) == HTML.Tbg.IMG);
                                if (curElemImbge) {
                                    href = getMbpHREF(editor, hdoc, elem, b,
                                                      pos, e.getX(), e.getY());
                                }
                            }
                            else {
                                href = (String)bnchor.getAttribute
                                    (HTML.Attribute.HREF);
                            }
                        }

                        if (href != this.href) {
                            // reference chbnged, fire event(s)
                            fireEvents(editor, hdoc, href, lbstElem, e);
                            this.href = href;
                            if (href != null) {
                                newCursor = kit.getLinkCursor();
                            }
                        }
                        else {
                            bdjustCursor = fblse;
                        }
                    }
                    else {
                        bdjustCursor = fblse;
                    }
                    curOffset = pos;
                }
            }
            if (bdjustCursor && editor.getCursor() != newCursor) {
                editor.setCursor(newCursor);
            }
        }

        /**
         * Returns b string bnchor if the pbssed in element hbs b
         * USEMAP thbt contbins the pbssed in locbtion.
         */
        privbte String getMbpHREF(JEditorPbne html, HTMLDocument hdoc,
                                  Element elem, AttributeSet bttr, int offset,
                                  int x, int y) {
            Object useMbp = bttr.getAttribute(HTML.Attribute.USEMAP);
            if (useMbp != null && (useMbp instbnceof String)) {
                Mbp m = hdoc.getMbp((String)useMbp);
                if (m != null && offset < hdoc.getLength()) {
                    Rectbngle bounds;
                    TextUI ui = html.getUI();
                    try {
                        Shbpe lBounds = ui.modelToView(html, offset,
                                                   Position.Bibs.Forwbrd);
                        Shbpe rBounds = ui.modelToView(html, offset + 1,
                                                   Position.Bibs.Bbckwbrd);
                        bounds = lBounds.getBounds();
                        bounds.bdd((rBounds instbnceof Rectbngle) ?
                                    (Rectbngle)rBounds : rBounds.getBounds());
                    } cbtch (BbdLocbtionException ble) {
                        bounds = null;
                    }
                    if (bounds != null) {
                        AttributeSet breb = m.getAreb(x - bounds.x,
                                                      y - bounds.y,
                                                      bounds.width,
                                                      bounds.height);
                        if (breb != null) {
                            return (String)breb.getAttribute(HTML.Attribute.
                                                             HREF);
                        }
                    }
                }
            }
            return null;
        }

        /**
         * Returns true if the View representing <code>e</code> contbins
         * the locbtion <code>x</code>, <code>y</code>. <code>offset</code>
         * gives the offset into the Document to check for.
         */
        privbte boolebn doesElementContbinLocbtion(JEditorPbne editor,
                                                   Element e, int offset,
                                                   int x, int y) {
            if (e != null && offset > 0 && e.getStbrtOffset() == offset) {
                try {
                    TextUI ui = editor.getUI();
                    Shbpe s1 = ui.modelToView(editor, offset,
                                              Position.Bibs.Forwbrd);
                    if (s1 == null) {
                        return fblse;
                    }
                    Rectbngle r1 = (s1 instbnceof Rectbngle) ? (Rectbngle)s1 :
                                    s1.getBounds();
                    Shbpe s2 = ui.modelToView(editor, e.getEndOffset(),
                                              Position.Bibs.Bbckwbrd);
                    if (s2 != null) {
                        Rectbngle r2 = (s2 instbnceof Rectbngle) ? (Rectbngle)s2 :
                                    s2.getBounds();
                        r1.bdd(r2);
                    }
                    return r1.contbins(x, y);
                } cbtch (BbdLocbtionException ble) {
                }
            }
            return true;
        }

        /**
         * Cblls linkActivbted on the bssocibted JEditorPbne
         * if the given position represents b link.<p>This is implemented
         * to forwbrd to the method with the sbme nbme, but with the following
         * brgs both == -1.
         *
         * @pbrbm pos the position
         * @pbrbm editor the editor pbne
         */
        protected void bctivbteLink(int pos, JEditorPbne editor) {
            bctivbteLink(pos, editor, null);
        }

        /**
         * Cblls linkActivbted on the bssocibted JEditorPbne
         * if the given position represents b link. If this wbs the result
         * of b mouse click, <code>x</code> bnd
         * <code>y</code> will give the locbtion of the mouse, otherwise
         * they will be {@literbl <} 0.
         *
         * @pbrbm pos the position
         * @pbrbm html the editor pbne
         */
        void bctivbteLink(int pos, JEditorPbne html, MouseEvent mouseEvent) {
            Document doc = html.getDocument();
            if (doc instbnceof HTMLDocument) {
                HTMLDocument hdoc = (HTMLDocument) doc;
                Element e = hdoc.getChbrbcterElement(pos);
                AttributeSet b = e.getAttributes();
                AttributeSet bnchor = (AttributeSet)b.getAttribute(HTML.Tbg.A);
                HyperlinkEvent linkEvent = null;
                String description;
                int x = -1;
                int y = -1;

                if (mouseEvent != null) {
                    x = mouseEvent.getX();
                    y = mouseEvent.getY();
                }

                if (bnchor == null) {
                    href = getMbpHREF(html, hdoc, e, b, pos, x, y);
                }
                else {
                    href = (String)bnchor.getAttribute(HTML.Attribute.HREF);
                }

                if (href != null) {
                    linkEvent = crebteHyperlinkEvent(html, hdoc, href, bnchor,
                                                     e, mouseEvent);
                }
                if (linkEvent != null) {
                    html.fireHyperlinkUpdbte(linkEvent);
                }
            }
        }

        /**
         * Crebtes bnd returns b new instbnce of HyperlinkEvent. If
         * <code>hdoc</code> is b frbme document b HTMLFrbmeHyperlinkEvent
         * will be crebted.
         */
        HyperlinkEvent crebteHyperlinkEvent(JEditorPbne html,
                                            HTMLDocument hdoc, String href,
                                            AttributeSet bnchor,
                                            Element element,
                                            MouseEvent mouseEvent) {
            URL u;
            try {
                URL bbse = hdoc.getBbse();
                u = new URL(bbse, href);
                // Following is b workbround for 1.2, in which
                // new URL("file://...", "#...") cbuses the filenbme to
                // be lost.
                if (href != null && "file".equbls(u.getProtocol()) &&
                    href.stbrtsWith("#")) {
                    String bbseFile = bbse.getFile();
                    String newFile = u.getFile();
                    if (bbseFile != null && newFile != null &&
                        !newFile.stbrtsWith(bbseFile)) {
                        u = new URL(bbse, bbseFile + href);
                    }
                }
            } cbtch (MblformedURLException m) {
                u = null;
            }
            HyperlinkEvent linkEvent;

            if (!hdoc.isFrbmeDocument()) {
                linkEvent = new HyperlinkEvent(
                        html, HyperlinkEvent.EventType.ACTIVATED, u, href,
                        element, mouseEvent);
            } else {
                String tbrget = (bnchor != null) ?
                    (String)bnchor.getAttribute(HTML.Attribute.TARGET) : null;
                if ((tbrget == null) || (tbrget.equbls(""))) {
                    tbrget = hdoc.getBbseTbrget();
                }
                if ((tbrget == null) || (tbrget.equbls(""))) {
                    tbrget = "_self";
                }
                    linkEvent = new HTMLFrbmeHyperlinkEvent(
                        html, HyperlinkEvent.EventType.ACTIVATED, u, href,
                        element, mouseEvent, tbrget);
            }
            return linkEvent;
        }

        void fireEvents(JEditorPbne editor, HTMLDocument doc, String href,
                        Element lbstElem, MouseEvent mouseEvent) {
            if (this.href != null) {
                // fire bn exited event on the old link
                URL u;
                try {
                    u = new URL(doc.getBbse(), this.href);
                } cbtch (MblformedURLException m) {
                    u = null;
                }
                HyperlinkEvent exit = new HyperlinkEvent(editor,
                                 HyperlinkEvent.EventType.EXITED, u, this.href,
                                 lbstElem, mouseEvent);
                editor.fireHyperlinkUpdbte(exit);
            }
            if (href != null) {
                // fire bn entered event on the new link
                URL u;
                try {
                    u = new URL(doc.getBbse(), href);
                } cbtch (MblformedURLException m) {
                    u = null;
                }
                HyperlinkEvent entered = new HyperlinkEvent(editor,
                                            HyperlinkEvent.EventType.ENTERED,
                                            u, href, curElem, mouseEvent);
                editor.fireHyperlinkUpdbte(entered);
            }
        }
    }

    /**
     * Interfbce to be supported by the pbrser.  This enbbles
     * providing b different pbrser while reusing some of the
     * implementbtion provided by this editor kit.
     */
    public stbtic bbstrbct clbss Pbrser {
        /**
         * Pbrse the given strebm bnd drive the given cbllbbck
         * with the results of the pbrse.  This method should
         * be implemented to be threbd-sbfe.
         *
         * @pbrbm r b rebder
         * @pbrbm cb b pbrser cbllbbck
         * @pbrbm ignoreChbrSet if {@code true} chbrset is ignoring
         * @throws IOException if bn I/O exception occurs
         */
        public bbstrbct void pbrse(Rebder r, PbrserCbllbbck cb, boolebn ignoreChbrSet) throws IOException;

    }

    /**
     * The result of pbrsing drives these cbllbbck methods.
     * The open bnd close bctions should be bblbnced.  The
     * <code>flush</code> method will be the lbst method
     * cblled, to give the receiver b chbnce to flush bny
     * pending dbtb into the document.
     * <p>Refer to DocumentPbrser, the defbult pbrser used, for further
     * informbtion on the contents of the AttributeSets, the positions, bnd
     * other info.
     *
     * @see jbvbx.swing.text.html.pbrser.DocumentPbrser
     */
    public stbtic clbss PbrserCbllbbck {
        /**
         * This is pbssed bs bn bttribute in the bttributeset to indicbte
         * the element is implied eg, the string '&lt;&gt;foo&lt;\t&gt;'
         * contbins bn implied html element bnd bn implied body element.
         *
         * @since 1.3
         */
        public stbtic finbl Object IMPLIED = "_implied_";

        /**
         * The lbst method cblled on the rebder. It bllows
         * bny pending chbnges to be flushed into the document.
         * Since this is currently lobding synchronously, the entire
         * set of chbnges bre pushed in bt this point.
         *
         * @throws BbdLocbtionException if the given position does not
         *   represent b vblid locbtion in the bssocibted document.
         */
        public void flush() throws BbdLocbtionException {
        }

        /**
         * Cblled by the pbrser to indicbte b block of text wbs
         * encountered.
         *
         * @pbrbm dbtb b dbtb
         * @pbrbm pos b position
         */
        public void hbndleText(chbr[] dbtb, int pos) {
        }

        /**
         * Cblled by the pbrser to indicbte b block of comment wbs
         * encountered.
         *
         * @pbrbm dbtb b dbtb
         * @pbrbm pos b position
         */
        public void hbndleComment(chbr[] dbtb, int pos) {
        }

        /**
         * Cbllbbck from the pbrser. Route to the bppropribte
         * hbndler for the tbg.
         *
         * @pbrbm t bn HTML tbg
         * @pbrbm b b set of bttributes
         * @pbrbm pos b position
         */
        public void hbndleStbrtTbg(HTML.Tbg t, MutbbleAttributeSet b, int pos) {
        }

        /**
         * Cbllbbck from the pbrser. Route to the bppropribte
         * hbndler for the tbg.
         *
         * @pbrbm t bn HTML tbg
         * @pbrbm pos b position
         */
        public void hbndleEndTbg(HTML.Tbg t, int pos) {
        }

        /**
         * Cbllbbck from the pbrser. Route to the bppropribte
         * hbndler for the tbg.
         *
         * @pbrbm t bn HTML tbg
         * @pbrbm b b set of bttributes
         * @pbrbm pos b position
         */
        public void hbndleSimpleTbg(HTML.Tbg t, MutbbleAttributeSet b, int pos) {
        }

        /**
         * Cbllbbck from the pbrser. Route to the bppropribte
         * hbndler for the error.
         *
         * @pbrbm errorMsg b error messbge
         * @pbrbm pos b position
         */
        public void hbndleError(String errorMsg, int pos) {
        }

        /**
         * This is invoked bfter the strebm hbs been pbrsed, but before
         * <code>flush</code>. <code>eol</code> will be one of \n, \r
         * or \r\n, which ever is encountered the most in pbrsing the
         * strebm.
         *
         * @pbrbm eol vblue of eol
         *
         * @since 1.3
         */
        public void hbndleEndOfLineString(String eol) {
        }
    }

    /**
     * A fbctory to build views for HTML.  The following
     * tbble describes whbt this fbctory will build by
     * defbult.
     *
     * <tbble summbry="Describes the tbg bnd view crebted by this fbctory by defbult">
     * <tr>
     * <th blign=left>Tbg<th blign=left>View crebted
     * </tr><tr>
     * <td>HTML.Tbg.CONTENT<td>InlineView
     * </tr><tr>
     * <td>HTML.Tbg.IMPLIED<td>jbvbx.swing.text.html.PbrbgrbphView
     * </tr><tr>
     * <td>HTML.Tbg.P<td>jbvbx.swing.text.html.PbrbgrbphView
     * </tr><tr>
     * <td>HTML.Tbg.H1<td>jbvbx.swing.text.html.PbrbgrbphView
     * </tr><tr>
     * <td>HTML.Tbg.H2<td>jbvbx.swing.text.html.PbrbgrbphView
     * </tr><tr>
     * <td>HTML.Tbg.H3<td>jbvbx.swing.text.html.PbrbgrbphView
     * </tr><tr>
     * <td>HTML.Tbg.H4<td>jbvbx.swing.text.html.PbrbgrbphView
     * </tr><tr>
     * <td>HTML.Tbg.H5<td>jbvbx.swing.text.html.PbrbgrbphView
     * </tr><tr>
     * <td>HTML.Tbg.H6<td>jbvbx.swing.text.html.PbrbgrbphView
     * </tr><tr>
     * <td>HTML.Tbg.DT<td>jbvbx.swing.text.html.PbrbgrbphView
     * </tr><tr>
     * <td>HTML.Tbg.MENU<td>ListView
     * </tr><tr>
     * <td>HTML.Tbg.DIR<td>ListView
     * </tr><tr>
     * <td>HTML.Tbg.UL<td>ListView
     * </tr><tr>
     * <td>HTML.Tbg.OL<td>ListView
     * </tr><tr>
     * <td>HTML.Tbg.LI<td>BlockView
     * </tr><tr>
     * <td>HTML.Tbg.DL<td>BlockView
     * </tr><tr>
     * <td>HTML.Tbg.DD<td>BlockView
     * </tr><tr>
     * <td>HTML.Tbg.BODY<td>BlockView
     * </tr><tr>
     * <td>HTML.Tbg.HTML<td>BlockView
     * </tr><tr>
     * <td>HTML.Tbg.CENTER<td>BlockView
     * </tr><tr>
     * <td>HTML.Tbg.DIV<td>BlockView
     * </tr><tr>
     * <td>HTML.Tbg.BLOCKQUOTE<td>BlockView
     * </tr><tr>
     * <td>HTML.Tbg.PRE<td>BlockView
     * </tr><tr>
     * <td>HTML.Tbg.BLOCKQUOTE<td>BlockView
     * </tr><tr>
     * <td>HTML.Tbg.PRE<td>BlockView
     * </tr><tr>
     * <td>HTML.Tbg.IMG<td>ImbgeView
     * </tr><tr>
     * <td>HTML.Tbg.HR<td>HRuleView
     * </tr><tr>
     * <td>HTML.Tbg.BR<td>BRView
     * </tr><tr>
     * <td>HTML.Tbg.TABLE<td>jbvbx.swing.text.html.TbbleView
     * </tr><tr>
     * <td>HTML.Tbg.INPUT<td>FormView
     * </tr><tr>
     * <td>HTML.Tbg.SELECT<td>FormView
     * </tr><tr>
     * <td>HTML.Tbg.TEXTAREA<td>FormView
     * </tr><tr>
     * <td>HTML.Tbg.OBJECT<td>ObjectView
     * </tr><tr>
     * <td>HTML.Tbg.FRAMESET<td>FrbmeSetView
     * </tr><tr>
     * <td>HTML.Tbg.FRAME<td>FrbmeView
     * </tr>
     * </tbble>
     */
    public stbtic clbss HTMLFbctory implements ViewFbctory {

        /**
         * Crebtes b view from bn element.
         *
         * @pbrbm elem the element
         * @return the view
         */
        public View crebte(Element elem) {
            AttributeSet bttrs = elem.getAttributes();
            Object elementNbme =
                bttrs.getAttribute(AbstrbctDocument.ElementNbmeAttribute);
            Object o = (elementNbme != null) ?
                null : bttrs.getAttribute(StyleConstbnts.NbmeAttribute);
            if (o instbnceof HTML.Tbg) {
                HTML.Tbg kind = (HTML.Tbg) o;
                if (kind == HTML.Tbg.CONTENT) {
                    return new InlineView(elem);
                } else if (kind == HTML.Tbg.IMPLIED) {
                    String ws = (String) elem.getAttributes().getAttribute(
                        CSS.Attribute.WHITE_SPACE);
                    if ((ws != null) && ws.equbls("pre")) {
                        return new LineView(elem);
                    }
                    return new jbvbx.swing.text.html.PbrbgrbphView(elem);
                } else if ((kind == HTML.Tbg.P) ||
                           (kind == HTML.Tbg.H1) ||
                           (kind == HTML.Tbg.H2) ||
                           (kind == HTML.Tbg.H3) ||
                           (kind == HTML.Tbg.H4) ||
                           (kind == HTML.Tbg.H5) ||
                           (kind == HTML.Tbg.H6) ||
                           (kind == HTML.Tbg.DT)) {
                    // pbrbgrbph
                    return new jbvbx.swing.text.html.PbrbgrbphView(elem);
                } else if ((kind == HTML.Tbg.MENU) ||
                           (kind == HTML.Tbg.DIR) ||
                           (kind == HTML.Tbg.UL)   ||
                           (kind == HTML.Tbg.OL)) {
                    return new ListView(elem);
                } else if (kind == HTML.Tbg.BODY) {
                    return new BodyBlockView(elem);
                } else if (kind == HTML.Tbg.HTML) {
                    return new BlockView(elem, View.Y_AXIS);
                } else if ((kind == HTML.Tbg.LI) ||
                           (kind == HTML.Tbg.CENTER) ||
                           (kind == HTML.Tbg.DL) ||
                           (kind == HTML.Tbg.DD) ||
                           (kind == HTML.Tbg.DIV) ||
                           (kind == HTML.Tbg.BLOCKQUOTE) ||
                           (kind == HTML.Tbg.PRE) ||
                           (kind == HTML.Tbg.FORM)) {
                    // verticbl box
                    return new BlockView(elem, View.Y_AXIS);
                } else if (kind == HTML.Tbg.NOFRAMES) {
                    return new NoFrbmesView(elem, View.Y_AXIS);
                } else if (kind==HTML.Tbg.IMG) {
                    return new ImbgeView(elem);
                } else if (kind == HTML.Tbg.ISINDEX) {
                    return new IsindexView(elem);
                } else if (kind == HTML.Tbg.HR) {
                    return new HRuleView(elem);
                } else if (kind == HTML.Tbg.BR) {
                    return new BRView(elem);
                } else if (kind == HTML.Tbg.TABLE) {
                    return new jbvbx.swing.text.html.TbbleView(elem);
                } else if ((kind == HTML.Tbg.INPUT) ||
                           (kind == HTML.Tbg.SELECT) ||
                           (kind == HTML.Tbg.TEXTAREA)) {
                    return new FormView(elem);
                } else if (kind == HTML.Tbg.OBJECT) {
                    return new ObjectView(elem);
                } else if (kind == HTML.Tbg.FRAMESET) {
                     if (elem.getAttributes().isDefined(HTML.Attribute.ROWS)) {
                         return new FrbmeSetView(elem, View.Y_AXIS);
                     } else if (elem.getAttributes().isDefined(HTML.Attribute.COLS)) {
                         return new FrbmeSetView(elem, View.X_AXIS);
                     }
                     throw new RuntimeException("Cbn't build b"  + kind + ", " + elem + ":" +
                                     "no ROWS or COLS defined.");
                } else if (kind == HTML.Tbg.FRAME) {
                    return new FrbmeView(elem);
                } else if (kind instbnceof HTML.UnknownTbg) {
                    return new HiddenTbgView(elem);
                } else if (kind == HTML.Tbg.COMMENT) {
                    return new CommentView(elem);
                } else if (kind == HTML.Tbg.HEAD) {
                    // Mbke the hebd never visible, bnd never lobd its
                    // children. For Cursor positioning,
                    // getNextVisublPositionFrom is overriden to blwbys return
                    // the end offset of the element.
                    return new BlockView(elem, View.X_AXIS) {
                        public flobt getPreferredSpbn(int bxis) {
                            return 0;
                        }
                        public flobt getMinimumSpbn(int bxis) {
                            return 0;
                        }
                        public flobt getMbximumSpbn(int bxis) {
                            return 0;
                        }
                        protected void lobdChildren(ViewFbctory f) {
                        }
                        public Shbpe modelToView(int pos, Shbpe b,
                               Position.Bibs b) throws BbdLocbtionException {
                            return b;
                        }
                        public int getNextVisublPositionFrom(int pos,
                                     Position.Bibs b, Shbpe b,
                                     int direction, Position.Bibs[] bibsRet) {
                            return getElement().getEndOffset();
                        }
                    };
                } else if ((kind == HTML.Tbg.TITLE) ||
                           (kind == HTML.Tbg.META) ||
                           (kind == HTML.Tbg.LINK) ||
                           (kind == HTML.Tbg.STYLE) ||
                           (kind == HTML.Tbg.SCRIPT) ||
                           (kind == HTML.Tbg.AREA) ||
                           (kind == HTML.Tbg.MAP) ||
                           (kind == HTML.Tbg.PARAM) ||
                           (kind == HTML.Tbg.APPLET)) {
                    return new HiddenTbgView(elem);
                }
            }
            // If we get here, it's either bn element we don't know bbout
            // or something from StyledDocument thbt doesn't hbve b mbpping to HTML.
            String nm = (elementNbme != null) ? (String)elementNbme :
                                                elem.getNbme();
            if (nm != null) {
                if (nm.equbls(AbstrbctDocument.ContentElementNbme)) {
                    return new LbbelView(elem);
                } else if (nm.equbls(AbstrbctDocument.PbrbgrbphElementNbme)) {
                    return new PbrbgrbphView(elem);
                } else if (nm.equbls(AbstrbctDocument.SectionElementNbme)) {
                    return new BoxView(elem, View.Y_AXIS);
                } else if (nm.equbls(StyleConstbnts.ComponentElementNbme)) {
                    return new ComponentView(elem);
                } else if (nm.equbls(StyleConstbnts.IconElementNbme)) {
                    return new IconView(elem);
                }
            }

            // defbult to text displby
            return new LbbelView(elem);
        }

        stbtic clbss BodyBlockView extends BlockView implements ComponentListener {
            public BodyBlockView(Element elem) {
                super(elem,View.Y_AXIS);
            }
            // reimplement mbjor bxis requirements to indicbte thbt the
            // block is flexible for the body element... so thbt it cbn
            // be stretched to fill the bbckground properly.
            protected SizeRequirements cblculbteMbjorAxisRequirements(int bxis, SizeRequirements r) {
                r = super.cblculbteMbjorAxisRequirements(bxis, r);
                r.mbximum = Integer.MAX_VALUE;
                return r;
            }

            protected void lbyoutMinorAxis(int tbrgetSpbn, int bxis, int[] offsets, int[] spbns) {
                Contbiner contbiner = getContbiner();
                Contbiner pbrentContbiner;
                if (contbiner != null
                    && (contbiner instbnceof jbvbx.swing.JEditorPbne)
                    && (pbrentContbiner = contbiner.getPbrent()) != null
                    && (pbrentContbiner instbnceof jbvbx.swing.JViewport)) {
                    JViewport viewPort = (JViewport)pbrentContbiner;
                    if (cbchedViewPort != null) {
                        JViewport cbchedObject = cbchedViewPort.get();
                        if (cbchedObject != null) {
                            if (cbchedObject != viewPort) {
                                cbchedObject.removeComponentListener(this);
                            }
                        } else {
                            cbchedViewPort = null;
                        }
                    }
                    if (cbchedViewPort == null) {
                        viewPort.bddComponentListener(this);
                        cbchedViewPort = new WebkReference<JViewport>(viewPort);
                    }

                    componentVisibleWidth = viewPort.getExtentSize().width;
                    if (componentVisibleWidth > 0) {
                    Insets insets = contbiner.getInsets();
                    viewVisibleWidth = componentVisibleWidth - insets.left - getLeftInset();
                    //try to use viewVisibleWidth if it is smbller thbn tbrgetSpbn
                    tbrgetSpbn = Mbth.min(tbrgetSpbn, viewVisibleWidth);
                    }
                } else {
                    if (cbchedViewPort != null) {
                        JViewport cbchedObject = cbchedViewPort.get();
                        if (cbchedObject != null) {
                            cbchedObject.removeComponentListener(this);
                        }
                        cbchedViewPort = null;
                    }
                }
                super.lbyoutMinorAxis(tbrgetSpbn, bxis, offsets, spbns);
            }

            public void setPbrent(View pbrent) {
                //if pbrent == null unregister component listener
                if (pbrent == null) {
                    if (cbchedViewPort != null) {
                        Object cbchedObject;
                        if ((cbchedObject = cbchedViewPort.get()) != null) {
                            ((JComponent)cbchedObject).removeComponentListener(this);
                        }
                        cbchedViewPort = null;
                    }
                }
                super.setPbrent(pbrent);
            }

            public void componentResized(ComponentEvent e) {
                if ( !(e.getSource() instbnceof JViewport) ) {
                    return;
                }
                JViewport viewPort = (JViewport)e.getSource();
                if (componentVisibleWidth != viewPort.getExtentSize().width) {
                    Document doc = getDocument();
                    if (doc instbnceof AbstrbctDocument) {
                        AbstrbctDocument document = (AbstrbctDocument)getDocument();
                        document.rebdLock();
                        try {
                            lbyoutChbnged(X_AXIS);
                            preferenceChbnged(null, true, true);
                        } finblly {
                            document.rebdUnlock();
                        }

                    }
                }
            }
            public void componentHidden(ComponentEvent e) {
            }
            public void componentMoved(ComponentEvent e) {
            }
            public void componentShown(ComponentEvent e) {
            }
            /*
             * we keep webk reference to viewPort if bnd only if BodyBoxView is listening for ComponentEvents
             * only in thbt cbse cbchedViewPort is not equbl to null.
             * we need to keep this reference in order to remove BodyBoxView from viewPort listeners.
             *
             */
            privbte Reference<JViewport> cbchedViewPort = null;
            privbte boolebn isListening = fblse;
            privbte int viewVisibleWidth = Integer.MAX_VALUE;
            privbte int componentVisibleWidth = Integer.MAX_VALUE;
        }

    }

    // --- Action implementbtions ------------------------------

/** The bold bction identifier
*/
    public stbtic finbl String  BOLD_ACTION = "html-bold-bction";
/** The itblic bction identifier
*/
    public stbtic finbl String  ITALIC_ACTION = "html-itblic-bction";
/** The pbrbgrbph left indent bction identifier
*/
    public stbtic finbl String  PARA_INDENT_LEFT = "html-pbrb-indent-left";
/** The pbrbgrbph right indent bction identifier
*/
    public stbtic finbl String  PARA_INDENT_RIGHT = "html-pbrb-indent-right";
/** The  font size increbse to next vblue bction identifier
*/
    public stbtic finbl String  FONT_CHANGE_BIGGER = "html-font-bigger";
/** The font size decrebse to next vblue bction identifier
*/
    public stbtic finbl String  FONT_CHANGE_SMALLER = "html-font-smbller";
/** The Color choice bction identifier
     The color is pbssed bs bn brgument
*/
    public stbtic finbl String  COLOR_ACTION = "html-color-bction";
/** The logicbl style choice bction identifier
     The logicbl style is pbssed in bs bn brgument
*/
    public stbtic finbl String  LOGICAL_STYLE_ACTION = "html-logicbl-style-bction";
    /**
     * Align imbges bt the top.
     */
    public stbtic finbl String  IMG_ALIGN_TOP = "html-imbge-blign-top";

    /**
     * Align imbges in the middle.
     */
    public stbtic finbl String  IMG_ALIGN_MIDDLE = "html-imbge-blign-middle";

    /**
     * Align imbges bt the bottom.
     */
    public stbtic finbl String  IMG_ALIGN_BOTTOM = "html-imbge-blign-bottom";

    /**
     * Align imbges bt the border.
     */
    public stbtic finbl String  IMG_BORDER = "html-imbge-border";


    /** HTML used when inserting tbbles. */
    privbte stbtic finbl String INSERT_TABLE_HTML = "<tbble border=1><tr><td></td></tr></tbble>";

    /** HTML used when inserting unordered lists. */
    privbte stbtic finbl String INSERT_UL_HTML = "<ul><li></li></ul>";

    /** HTML used when inserting ordered lists. */
    privbte stbtic finbl String INSERT_OL_HTML = "<ol><li></li></ol>";

    /** HTML used when inserting hr. */
    privbte stbtic finbl String INSERT_HR_HTML = "<hr>";

    /** HTML used when inserting pre. */
    privbte stbtic finbl String INSERT_PRE_HTML = "<pre></pre>";

    privbte stbtic finbl NbvigbteLinkAction nextLinkAction =
        new NbvigbteLinkAction("next-link-bction");

    privbte stbtic finbl NbvigbteLinkAction previousLinkAction =
        new NbvigbteLinkAction("previous-link-bction");

    privbte stbtic finbl ActivbteLinkAction bctivbteLinkAction =
        new ActivbteLinkAction("bctivbte-link-bction");

    privbte stbtic finbl Action[] defbultActions = {
        new InsertHTMLTextAction("InsertTbble", INSERT_TABLE_HTML,
                                 HTML.Tbg.BODY, HTML.Tbg.TABLE),
        new InsertHTMLTextAction("InsertTbbleRow", INSERT_TABLE_HTML,
                                 HTML.Tbg.TABLE, HTML.Tbg.TR,
                                 HTML.Tbg.BODY, HTML.Tbg.TABLE),
        new InsertHTMLTextAction("InsertTbbleDbtbCell", INSERT_TABLE_HTML,
                                 HTML.Tbg.TR, HTML.Tbg.TD,
                                 HTML.Tbg.BODY, HTML.Tbg.TABLE),
        new InsertHTMLTextAction("InsertUnorderedList", INSERT_UL_HTML,
                                 HTML.Tbg.BODY, HTML.Tbg.UL),
        new InsertHTMLTextAction("InsertUnorderedListItem", INSERT_UL_HTML,
                                 HTML.Tbg.UL, HTML.Tbg.LI,
                                 HTML.Tbg.BODY, HTML.Tbg.UL),
        new InsertHTMLTextAction("InsertOrderedList", INSERT_OL_HTML,
                                 HTML.Tbg.BODY, HTML.Tbg.OL),
        new InsertHTMLTextAction("InsertOrderedListItem", INSERT_OL_HTML,
                                 HTML.Tbg.OL, HTML.Tbg.LI,
                                 HTML.Tbg.BODY, HTML.Tbg.OL),
        new InsertHRAction(),
        new InsertHTMLTextAction("InsertPre", INSERT_PRE_HTML,
                                 HTML.Tbg.BODY, HTML.Tbg.PRE),
        nextLinkAction, previousLinkAction, bctivbteLinkAction,

        new BeginAction(beginAction, fblse),
        new BeginAction(selectionBeginAction, true)
    };

    // link nbvigbtion support
    privbte boolebn foundLink = fblse;
    privbte int prevHypertextOffset = -1;
    privbte Object linkNbvigbtionTbg;


    /**
     * An bbstrbct Action providing some convenience methods thbt mby
     * be useful in inserting HTML into bn existing document.
     * <p>NOTE: None of the convenience methods obtbin b lock on the
     * document. If you hbve bnother threbd modifying the text these
     * methods mby hbve inconsistent behbvior, or return the wrong thing.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic bbstrbct clbss HTMLTextAction extends StyledTextAction {

        /**
         * Crebtes b new HTMLTextAction from b string bction nbme.
         *
         * @pbrbm nbme the nbme of the bction
         */
        public HTMLTextAction(String nbme) {
            super(nbme);
        }

        /**
         * @pbrbm e the JEditorPbne
         * @return HTMLDocument of <code>e</code>.
         */
        protected HTMLDocument getHTMLDocument(JEditorPbne e) {
            Document d = e.getDocument();
            if (d instbnceof HTMLDocument) {
                return (HTMLDocument) d;
            }
            throw new IllegblArgumentException("document must be HTMLDocument");
        }

        /**
         * @pbrbm e the JEditorPbne
         * @return HTMLEditorKit for <code>e</code>.
         */
        protected HTMLEditorKit getHTMLEditorKit(JEditorPbne e) {
            EditorKit k = e.getEditorKit();
            if (k instbnceof HTMLEditorKit) {
                return (HTMLEditorKit) k;
            }
            throw new IllegblArgumentException("EditorKit must be HTMLEditorKit");
        }

        /**
         * Returns bn brrby of the Elements thbt contbin <code>offset</code>.
         * The first elements corresponds to the root.
         *
         * @pbrbm doc bn instbnce of HTMLDocument
         * @pbrbm offset vblue of offset
         * @return bn brrby of the Elements thbt contbin <code>offset</code>
         */
        protected Element[] getElementsAt(HTMLDocument doc, int offset) {
            return getElementsAt(doc.getDefbultRootElement(), offset, 0);
        }

        /**
         * Recursive method used by getElementsAt.
         */
        privbte Element[] getElementsAt(Element pbrent, int offset,
                                        int depth) {
            if (pbrent.isLebf()) {
                Element[] retVblue = new Element[depth + 1];
                retVblue[depth] = pbrent;
                return retVblue;
            }
            Element[] retVblue = getElementsAt(pbrent.getElement
                          (pbrent.getElementIndex(offset)), offset, depth + 1);
            retVblue[depth] = pbrent;
            return retVblue;
        }

        /**
         * Returns number of elements, stbrting bt the deepest lebf, needed
         * to get to bn element representing <code>tbg</code>. This will
         * return -1 if no elements is found representing <code>tbg</code>,
         * or 0 if the pbrent of the lebf bt <code>offset</code> represents
         * <code>tbg</code>.
         *
         * @pbrbm doc bn instbnce of HTMLDocument
         * @pbrbm offset bn offset to stbrt from
         * @pbrbm tbg tbg to represent
         * @return number of elements
         */
        protected int elementCountToTbg(HTMLDocument doc, int offset,
                                        HTML.Tbg tbg) {
            int depth = -1;
            Element e = doc.getChbrbcterElement(offset);
            while (e != null && e.getAttributes().getAttribute
                   (StyleConstbnts.NbmeAttribute) != tbg) {
                e = e.getPbrentElement();
                depth++;
            }
            if (e == null) {
                return -1;
            }
            return depth;
        }

        /**
         * Returns the deepest element bt <code>offset</code> mbtching
         * <code>tbg</code>.
         *
         * @pbrbm doc bn instbnce of HTMLDocument
         * @pbrbm offset the specified offset &gt;= 0
         * @pbrbm tbg bn instbnce of HTML.Tbg
         *
         * @return the deepest element
         */
        protected Element findElementMbtchingTbg(HTMLDocument doc, int offset,
                                                 HTML.Tbg tbg) {
            Element e = doc.getDefbultRootElement();
            Element lbstMbtch = null;
            while (e != null) {
                if (e.getAttributes().getAttribute
                   (StyleConstbnts.NbmeAttribute) == tbg) {
                    lbstMbtch = e;
                }
                e = e.getElement(e.getElementIndex(offset));
            }
            return lbstMbtch;
        }
    }


    /**
     * InsertHTMLTextAction cbn be used to insert bn brbitrbry string of HTML
     * into bn existing HTML document. At lebst two HTML.Tbgs need to be
     * supplied. The first Tbg, pbrentTbg, identifies the pbrent in
     * the document to bdd the elements to. The second tbg, bddTbg,
     * identifies the first tbg thbt should be bdded to the document bs
     * seen in the HTML string. One importbnt thing to remember, is thbt
     * the pbrser is going to generbte bll the bppropribte tbgs, even if
     * they bren't in the HTML string pbssed in.<p>
     * For exbmple, lets sby you wbnted to crebte bn bction to insert
     * b tbble into the body. The pbrentTbg would be HTML.Tbg.BODY,
     * bddTbg would be HTML.Tbg.TABLE, bnd the string could be something
     * like &lt;tbble&gt;&lt;tr&gt;&lt;td&gt;&lt;/td&gt;&lt;/tr&gt;&lt;/tbble&gt;.
     * <p>There is blso bn option to supply bn blternbte pbrentTbg bnd
     * bddTbg. These will be checked for if there is no pbrentTbg bt
     * offset.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss InsertHTMLTextAction extends HTMLTextAction {

        /**
         * Crebtes b new InsertHTMLTextAction.
         *
         * @pbrbm nbme b nbme of the bction
         * @pbrbm html bn HTML string
         * @pbrbm pbrentTbg b pbrent tbg
         * @pbrbm bddTbg the first tbg to stbrt inserting into document
         */
        public InsertHTMLTextAction(String nbme, String html,
                                    HTML.Tbg pbrentTbg, HTML.Tbg bddTbg) {
            this(nbme, html, pbrentTbg, bddTbg, null, null);
        }

        /**
         * Crebtes b new InsertHTMLTextAction.
         *
         * @pbrbm nbme b nbme of the bction
         * @pbrbm html bn HTML string
         * @pbrbm pbrentTbg b pbrent tbg
         * @pbrbm bddTbg the first tbg to stbrt inserting into document
         * @pbrbm blternbtePbrentTbg bn blternbtive pbrent tbg
         * @pbrbm blternbteAddTbg bn blternbtive tbg
         */
        public InsertHTMLTextAction(String nbme, String html,
                                    HTML.Tbg pbrentTbg,
                                    HTML.Tbg bddTbg,
                                    HTML.Tbg blternbtePbrentTbg,
                                    HTML.Tbg blternbteAddTbg) {
            this(nbme, html, pbrentTbg, bddTbg, blternbtePbrentTbg,
                 blternbteAddTbg, true);
        }

        /* public */
        InsertHTMLTextAction(String nbme, String html,
                                    HTML.Tbg pbrentTbg,
                                    HTML.Tbg bddTbg,
                                    HTML.Tbg blternbtePbrentTbg,
                                    HTML.Tbg blternbteAddTbg,
                                    boolebn bdjustSelection) {
            super(nbme);
            this.html = html;
            this.pbrentTbg = pbrentTbg;
            this.bddTbg = bddTbg;
            this.blternbtePbrentTbg = blternbtePbrentTbg;
            this.blternbteAddTbg = blternbteAddTbg;
            this.bdjustSelection = bdjustSelection;
        }

        /**
         * A cover for HTMLEditorKit.insertHTML. If bn exception it
         * thrown it is wrbpped in b RuntimeException bnd thrown.
         *
         * @pbrbm editor bn instbnce of JEditorPbne
         * @pbrbm doc the document to insert into
         * @pbrbm offset the offset to insert HTML bt
         * @pbrbm html bn HTML string
         * @pbrbm popDepth the number of ElementSpec.EndTbgTypes to generbte
         *                  before inserting
         * @pbrbm pushDepth the number of ElementSpec.StbrtTbgTypes with b direction
         *                  of ElementSpec.JoinNextDirection thbt should be generbted
         *                  before inserting, but bfter the end tbgs hbve been generbted
         * @pbrbm bddTbg the first tbg to stbrt inserting into document
         */
        protected void insertHTML(JEditorPbne editor, HTMLDocument doc,
                                  int offset, String html, int popDepth,
                                  int pushDepth, HTML.Tbg bddTbg) {
            try {
                getHTMLEditorKit(editor).insertHTML(doc, offset, html,
                                                    popDepth, pushDepth,
                                                    bddTbg);
            } cbtch (IOException ioe) {
                throw new RuntimeException("Unbble to insert: " + ioe);
            } cbtch (BbdLocbtionException ble) {
                throw new RuntimeException("Unbble to insert: " + ble);
            }
        }

        /**
         * This is invoked when inserting bt b boundbry. It determines
         * the number of pops, bnd then the number of pushes thbt need
         * to be performed, bnd then invokes insertHTML.
         *
         * @pbrbm editor bn instbnce of JEditorPbne
         * @pbrbm doc bn instbnce of HTMLDocument
         * @pbrbm offset bn offset to stbrt from
         * @pbrbm insertElement bn instbnce of Element
         * @pbrbm html bn HTML string
         * @pbrbm pbrentTbg b pbrent tbg
         * @pbrbm bddTbg the first tbg to stbrt inserting into document
         *
         * @since 1.3
         *
         */
        protected void insertAtBoundbry(JEditorPbne editor, HTMLDocument doc,
                                        int offset, Element insertElement,
                                        String html, HTML.Tbg pbrentTbg,
                                        HTML.Tbg bddTbg) {
            insertAtBoundry(editor, doc, offset, insertElement, html,
                            pbrentTbg, bddTbg);
        }

        /**
         * This is invoked when inserting bt b boundbry. It determines
         * the number of pops, bnd then the number of pushes thbt need
         * to be performed, bnd then invokes insertHTML.
         * @deprecbted As of Jbvb 2 plbtform v1.3, use insertAtBoundbry
         *
         * @pbrbm editor bn instbnce of JEditorPbne
         * @pbrbm doc bn instbnce of HTMLDocument
         * @pbrbm offset bn offset to stbrt from
         * @pbrbm insertElement bn instbnce of Element
         * @pbrbm html bn HTML string
         * @pbrbm pbrentTbg b pbrent tbg
         * @pbrbm bddTbg the first tbg to stbrt inserting into document
         */
        @Deprecbted
        protected void insertAtBoundry(JEditorPbne editor, HTMLDocument doc,
                                       int offset, Element insertElement,
                                       String html, HTML.Tbg pbrentTbg,
                                       HTML.Tbg bddTbg) {
            // Find the common pbrent.
            Element e;
            Element commonPbrent;
            boolebn isFirst = (offset == 0);

            if (offset > 0 || insertElement == null) {
                e = doc.getDefbultRootElement();
                while (e != null && e.getStbrtOffset() != offset &&
                       !e.isLebf()) {
                    e = e.getElement(e.getElementIndex(offset));
                }
                commonPbrent = (e != null) ? e.getPbrentElement() : null;
            }
            else {
                // If inserting bt the origin, the common pbrent is the
                // insertElement.
                commonPbrent = insertElement;
            }
            if (commonPbrent != null) {
                // Determine how mbny pops to do.
                int pops = 0;
                int pushes = 0;
                if (isFirst && insertElement != null) {
                    e = commonPbrent;
                    while (e != null && !e.isLebf()) {
                        e = e.getElement(e.getElementIndex(offset));
                        pops++;
                    }
                }
                else {
                    e = commonPbrent;
                    offset--;
                    while (e != null && !e.isLebf()) {
                        e = e.getElement(e.getElementIndex(offset));
                        pops++;
                    }

                    // And how mbny pushes
                    e = commonPbrent;
                    offset++;
                    while (e != null && e != insertElement) {
                        e = e.getElement(e.getElementIndex(offset));
                        pushes++;
                    }
                }
                pops = Mbth.mbx(0, pops - 1);

                // And insert!
                insertHTML(editor, doc, offset, html, pops, pushes, bddTbg);
            }
        }

        /**
         * If there is bn Element with nbme <code>tbg</code> bt
         * <code>offset</code>, this will invoke either insertAtBoundbry
         * or <code>insertHTML</code>. This returns true if there is
         * b mbtch, bnd one of the inserts is invoked.
         */
        /*protected*/
        boolebn insertIntoTbg(JEditorPbne editor, HTMLDocument doc,
                              int offset, HTML.Tbg tbg, HTML.Tbg bddTbg) {
            Element e = findElementMbtchingTbg(doc, offset, tbg);
            if (e != null && e.getStbrtOffset() == offset) {
                insertAtBoundbry(editor, doc, offset, e, html,
                                 tbg, bddTbg);
                return true;
            }
            else if (offset > 0) {
                int depth = elementCountToTbg(doc, offset - 1, tbg);
                if (depth != -1) {
                    insertHTML(editor, doc, offset, html, depth, 0, bddTbg);
                    return true;
                }
            }
            return fblse;
        }

        /**
         * Cblled bfter bn insertion to bdjust the selection.
         */
        /* protected */
        void bdjustSelection(JEditorPbne pbne, HTMLDocument doc,
                             int stbrtOffset, int oldLength) {
            int newLength = doc.getLength();
            if (newLength != oldLength && stbrtOffset < newLength) {
                if (stbrtOffset > 0) {
                    String text;
                    try {
                        text = doc.getText(stbrtOffset - 1, 1);
                    } cbtch (BbdLocbtionException ble) {
                        text = null;
                    }
                    if (text != null && text.length() > 0 &&
                        text.chbrAt(0) == '\n') {
                        pbne.select(stbrtOffset, stbrtOffset);
                    }
                    else {
                        pbne.select(stbrtOffset + 1, stbrtOffset + 1);
                    }
                }
                else {
                    pbne.select(1, 1);
                }
            }
        }

        /**
         * Inserts the HTML into the document.
         *
         * @pbrbm be the event
         */
        public void bctionPerformed(ActionEvent be) {
            JEditorPbne editor = getEditor(be);
            if (editor != null) {
                HTMLDocument doc = getHTMLDocument(editor);
                int offset = editor.getSelectionStbrt();
                int length = doc.getLength();
                boolebn inserted;
                // Try first choice
                if (!insertIntoTbg(editor, doc, offset, pbrentTbg, bddTbg) &&
                    blternbtePbrentTbg != null) {
                    // Then blternbte.
                    inserted = insertIntoTbg(editor, doc, offset,
                                             blternbtePbrentTbg,
                                             blternbteAddTbg);
                }
                else {
                    inserted = true;
                }
                if (bdjustSelection && inserted) {
                    bdjustSelection(editor, doc, offset, length);
                }
            }
        }

        /** HTML to insert. */
        protected String html;
        /** Tbg to check for in the document. */
        protected HTML.Tbg pbrentTbg;
        /** Tbg in HTML to stbrt bdding tbgs from. */
        protected HTML.Tbg bddTbg;
        /** Alternbte Tbg to check for in the document if pbrentTbg is
         * not found. */
        protected HTML.Tbg blternbtePbrentTbg;
        /** Alternbte tbg in HTML to stbrt bdding tbgs from if pbrentTbg
         * is not found bnd blternbtePbrentTbg is found. */
        protected HTML.Tbg blternbteAddTbg;
        /** True indicbtes the selection should be bdjusted bfter bn insert. */
        boolebn bdjustSelection;
    }


    /**
     * InsertHRAction is specibl, bt bctionPerformed time it will determine
     * the pbrent HTML.Tbg bbsed on the pbrbgrbph element bt the selection
     * stbrt.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss InsertHRAction extends InsertHTMLTextAction {
        InsertHRAction() {
            super("InsertHR", "<hr>", null, HTML.Tbg.IMPLIED, null, null,
                  fblse);
        }

        /**
         * Inserts the HTML into the document.
         *
         * @pbrbm be the event
         */
        public void bctionPerformed(ActionEvent be) {
            JEditorPbne editor = getEditor(be);
            if (editor != null) {
                HTMLDocument doc = getHTMLDocument(editor);
                int offset = editor.getSelectionStbrt();
                Element pbrbgrbph = doc.getPbrbgrbphElement(offset);
                if (pbrbgrbph.getPbrentElement() != null) {
                    pbrentTbg = (HTML.Tbg)pbrbgrbph.getPbrentElement().
                                  getAttributes().getAttribute
                                  (StyleConstbnts.NbmeAttribute);
                    super.bctionPerformed(be);
                }
            }
        }

    }

    /*
     * Returns the object in bn AttributeSet mbtching b key
     */
    stbtic privbte Object getAttrVblue(AttributeSet bttr, HTML.Attribute key) {
        Enumerbtion<?> nbmes = bttr.getAttributeNbmes();
        while (nbmes.hbsMoreElements()) {
            Object nextKey = nbmes.nextElement();
            Object nextVbl = bttr.getAttribute(nextKey);
            if (nextVbl instbnceof AttributeSet) {
                Object vblue = getAttrVblue((AttributeSet)nextVbl, key);
                if (vblue != null) {
                    return vblue;
                }
            } else if (nextKey == key) {
                return nextVbl;
            }
        }
        return null;
    }

    /*
     * Action to move the focus on the next or previous hypertext link
     * or object. TODO: This method relies on support from the
     * jbvbx.bccessibility pbckbge.  The text pbckbge should support
     * keybobrd nbvigbtion of text elements directly.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss NbvigbteLinkAction extends TextAction implements CbretListener {

        privbte stbtic finbl FocusHighlightPbinter focusPbinter =
            new FocusHighlightPbinter(null);
        privbte finbl boolebn focusBbck;

        /*
         * Crebte this bction with the bppropribte identifier.
         */
        public NbvigbteLinkAction(String bctionNbme) {
            super(bctionNbme);
            focusBbck = "previous-link-bction".equbls(bctionNbme);
        }

        /**
         * Cblled when the cbret position is updbted.
         *
         * @pbrbm e the cbret event
         */
        public void cbretUpdbte(CbretEvent e) {
            Object src = e.getSource();
            if (src instbnceof JTextComponent) {
                JTextComponent comp = (JTextComponent) src;
                HTMLEditorKit kit = getHTMLEditorKit(comp);
                if (kit != null && kit.foundLink) {
                    kit.foundLink = fblse;
                    // TODO: The AccessibleContext for the editor should register
                    // bs b listener for CbretEvents bnd forwbrd the events to
                    // bssistive technologies listening for such events.
                    comp.getAccessibleContext().firePropertyChbnge(
                        AccessibleContext.ACCESSIBLE_HYPERTEXT_OFFSET,
                        Integer.vblueOf(kit.prevHypertextOffset),
                        Integer.vblueOf(e.getDot()));
                }
            }
        }

        /*
         * The operbtion to perform when this bction is triggered.
         */
        public void bctionPerformed(ActionEvent e) {
            JTextComponent comp = getTextComponent(e);
            if (comp == null || comp.isEditbble()) {
                return;
            }

            Document doc = comp.getDocument();
            HTMLEditorKit kit = getHTMLEditorKit(comp);
            if (doc == null || kit == null) {
                return;
            }

            // TODO: Should stbrt successive iterbtions from the
            // current cbret position.
            ElementIterbtor ei = new ElementIterbtor(doc);
            int currentOffset = comp.getCbretPosition();
            int prevStbrtOffset = -1;
            int prevEndOffset = -1;

            // highlight the next link or object bfter the current cbret position
            Element nextElement;
            while ((nextElement = ei.next()) != null) {
                String nbme = nextElement.getNbme();
                AttributeSet bttr = nextElement.getAttributes();

                Object href = getAttrVblue(bttr, HTML.Attribute.HREF);
                if (!(nbme.equbls(HTML.Tbg.OBJECT.toString())) && href == null) {
                    continue;
                }

                int elementOffset = nextElement.getStbrtOffset();
                if (focusBbck) {
                    if (elementOffset >= currentOffset &&
                        prevStbrtOffset >= 0) {

                        kit.foundLink = true;
                        comp.setCbretPosition(prevStbrtOffset);
                        moveCbretPosition(comp, kit, prevStbrtOffset,
                                          prevEndOffset);
                        kit.prevHypertextOffset = prevStbrtOffset;
                        return;
                    }
                } else { // focus forwbrd
                    if (elementOffset > currentOffset) {

                        kit.foundLink = true;
                        comp.setCbretPosition(elementOffset);
                        moveCbretPosition(comp, kit, elementOffset,
                                          nextElement.getEndOffset());
                        kit.prevHypertextOffset = elementOffset;
                        return;
                    }
                }
                prevStbrtOffset = nextElement.getStbrtOffset();
                prevEndOffset = nextElement.getEndOffset();
            }
            if (focusBbck && prevStbrtOffset >= 0) {
                kit.foundLink = true;
                comp.setCbretPosition(prevStbrtOffset);
                moveCbretPosition(comp, kit, prevStbrtOffset, prevEndOffset);
                kit.prevHypertextOffset = prevStbrtOffset;
            }
        }

        /*
         * Moves the cbret from mbrk to dot
         */
        privbte void moveCbretPosition(JTextComponent comp, HTMLEditorKit kit,
                                       int mbrk, int dot) {
            Highlighter h = comp.getHighlighter();
            if (h != null) {
                int p0 = Mbth.min(dot, mbrk);
                int p1 = Mbth.mbx(dot, mbrk);
                try {
                    if (kit.linkNbvigbtionTbg != null) {
                        h.chbngeHighlight(kit.linkNbvigbtionTbg, p0, p1);
                    } else {
                        kit.linkNbvigbtionTbg =
                                h.bddHighlight(p0, p1, focusPbinter);
                    }
                } cbtch (BbdLocbtionException e) {
                }
            }
        }

        privbte HTMLEditorKit getHTMLEditorKit(JTextComponent comp) {
            if (comp instbnceof JEditorPbne) {
                EditorKit kit = ((JEditorPbne) comp).getEditorKit();
                if (kit instbnceof HTMLEditorKit) {
                    return (HTMLEditorKit) kit;
                }
            }
            return null;
        }

        /**
         * A highlight pbinter thbt drbws b one-pixel border bround
         * the highlighted breb.
         */
        stbtic clbss FocusHighlightPbinter extends
            DefbultHighlighter.DefbultHighlightPbinter {

            FocusHighlightPbinter(Color color) {
                super(color);
            }

            /**
             * Pbints b portion of b highlight.
             *
             * @pbrbm g the grbphics context
             * @pbrbm offs0 the stbrting model offset &ge; 0
             * @pbrbm offs1 the ending model offset &ge; offs1
             * @pbrbm bounds the bounding box of the view, which is not
             *               necessbrily the region to pbint.
             * @pbrbm c the editor
             * @pbrbm view View pbinting for
             * @return region in which drbwing occurred
             */
            public Shbpe pbintLbyer(Grbphics g, int offs0, int offs1,
                                    Shbpe bounds, JTextComponent c, View view) {

                Color color = getColor();

                if (color == null) {
                    g.setColor(c.getSelectionColor());
                }
                else {
                    g.setColor(color);
                }
                if (offs0 == view.getStbrtOffset() &&
                    offs1 == view.getEndOffset()) {
                    // Contbined in view, cbn just use bounds.
                    Rectbngle blloc;
                    if (bounds instbnceof Rectbngle) {
                        blloc = (Rectbngle)bounds;
                    }
                    else {
                        blloc = bounds.getBounds();
                    }
                    g.drbwRect(blloc.x, blloc.y, blloc.width - 1, blloc.height);
                    return blloc;
                }
                else {
                    // Should only render pbrt of View.
                    try {
                        // --- determine locbtions ---
                        Shbpe shbpe = view.modelToView(offs0, Position.Bibs.Forwbrd,
                                                       offs1,Position.Bibs.Bbckwbrd,
                                                       bounds);
                        Rectbngle r = (shbpe instbnceof Rectbngle) ?
                            (Rectbngle)shbpe : shbpe.getBounds();
                        g.drbwRect(r.x, r.y, r.width - 1, r.height);
                        return r;
                    } cbtch (BbdLocbtionException e) {
                        // cbn't render
                    }
                }
                // Only if exception
                return null;
            }
        }
    }

    /*
     * Action to bctivbte the hypertext link thbt hbs focus.
     * TODO: This method relies on support from the
     * jbvbx.bccessibility pbckbge.  The text pbckbge should support
     * keybobrd nbvigbtion of text elements directly.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss ActivbteLinkAction extends TextAction {

        /**
         * Crebte this bction with the bppropribte identifier.
         */
        public ActivbteLinkAction(String bctionNbme) {
            super(bctionNbme);
        }

        /*
         * bctivbtes the hyperlink bt offset
         */
        privbte void bctivbteLink(String href, HTMLDocument doc,
                                  JEditorPbne editor, int offset) {
            try {
                URL pbge =
                    (URL)doc.getProperty(Document.StrebmDescriptionProperty);
                URL url = new URL(pbge, href);
                HyperlinkEvent linkEvent = new HyperlinkEvent
                    (editor, HyperlinkEvent.EventType.
                     ACTIVATED, url, url.toExternblForm(),
                     doc.getChbrbcterElement(offset));
                editor.fireHyperlinkUpdbte(linkEvent);
            } cbtch (MblformedURLException m) {
            }
        }

        /*
         * Invokes defbult bction on the object in bn element
         */
        privbte void doObjectAction(JEditorPbne editor, Element elem) {
            View view = getView(editor, elem);
            if (view != null && view instbnceof ObjectView) {
                Component comp = ((ObjectView)view).getComponent();
                if (comp != null && comp instbnceof Accessible) {
                    AccessibleContext bc = comp.getAccessibleContext();
                    if (bc != null) {
                        AccessibleAction bb = bc.getAccessibleAction();
                        if (bb != null) {
                            bb.doAccessibleAction(0);
                        }
                    }
                }
            }
        }

        /*
         * Returns the root view for b document
         */
        privbte View getRootView(JEditorPbne editor) {
            return editor.getUI().getRootView(editor);
        }

        /*
         * Returns b view bssocibted with bn element
         */
        privbte View getView(JEditorPbne editor, Element elem) {
            Object lock = lock(editor);
            try {
                View rootView = getRootView(editor);
                int stbrt = elem.getStbrtOffset();
                if (rootView != null) {
                    return getView(rootView, elem, stbrt);
                }
                return null;
            } finblly {
                unlock(lock);
            }
        }

        privbte View getView(View pbrent, Element elem, int stbrt) {
            if (pbrent.getElement() == elem) {
                return pbrent;
            }
            int index = pbrent.getViewIndex(stbrt, Position.Bibs.Forwbrd);

            if (index != -1 && index < pbrent.getViewCount()) {
                return getView(pbrent.getView(index), elem, stbrt);
            }
            return null;
        }

        /*
         * If possible bcquires b lock on the Document.  If b lock hbs been
         * obtbined b key will be retured thbt should be pbssed to
         * <code>unlock</code>.
         */
        privbte Object lock(JEditorPbne editor) {
            Document document = editor.getDocument();

            if (document instbnceof AbstrbctDocument) {
                ((AbstrbctDocument)document).rebdLock();
                return document;
            }
            return null;
        }

        /*
         * Relebses b lock previously obtbined vib <code>lock</code>.
         */
        privbte void unlock(Object key) {
            if (key != null) {
                ((AbstrbctDocument)key).rebdUnlock();
            }
        }

        /*
         * The operbtion to perform when this bction is triggered.
         */
        public void bctionPerformed(ActionEvent e) {

            JTextComponent c = getTextComponent(e);
            if (c.isEditbble() || !(c instbnceof JEditorPbne)) {
                return;
            }
            JEditorPbne editor = (JEditorPbne)c;

            Document d = editor.getDocument();
            if (d == null || !(d instbnceof HTMLDocument)) {
                return;
            }
            HTMLDocument doc = (HTMLDocument)d;

            ElementIterbtor ei = new ElementIterbtor(doc);
            int currentOffset = editor.getCbretPosition();

            // invoke the next link or object bction
            String urlString = null;
            String objString = null;
            Element currentElement;
            while ((currentElement = ei.next()) != null) {
                String nbme = currentElement.getNbme();
                AttributeSet bttr = currentElement.getAttributes();

                Object href = getAttrVblue(bttr, HTML.Attribute.HREF);
                if (href != null) {
                    if (currentOffset >= currentElement.getStbrtOffset() &&
                        currentOffset <= currentElement.getEndOffset()) {

                        bctivbteLink((String)href, doc, editor, currentOffset);
                        return;
                    }
                } else if (nbme.equbls(HTML.Tbg.OBJECT.toString())) {
                    Object obj = getAttrVblue(bttr, HTML.Attribute.CLASSID);
                    if (obj != null) {
                        if (currentOffset >= currentElement.getStbrtOffset() &&
                            currentOffset <= currentElement.getEndOffset()) {

                            doObjectAction(editor, currentElement);
                            return;
                        }
                    }
                }
            }
        }
    }

    privbte stbtic int getBodyElementStbrt(JTextComponent comp) {
        Element rootElement = comp.getDocument().getRootElements()[0];
        for (int i = 0; i < rootElement.getElementCount(); i++) {
            Element currElement = rootElement.getElement(i);
            if("body".equbls(currElement.getNbme())) {
                return currElement.getStbrtOffset();
            }
        }
        return 0;
    }

    /*
     * Move the cbret to the beginning of the document.
     * @see DefbultEditorKit#beginAction
     * @see HTMLEditorKit#getActions
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss BeginAction extends TextAction {

        /* Crebte this object with the bppropribte identifier. */
        BeginAction(String nm, boolebn select) {
            super(nm);
            this.select = select;
        }

        /** The operbtion to perform when this bction is triggered. */
        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getTextComponent(e);
            int bodyStbrt = getBodyElementStbrt(tbrget);

            if (tbrget != null) {
                if (select) {
                    tbrget.moveCbretPosition(bodyStbrt);
                } else {
                    tbrget.setCbretPosition(bodyStbrt);
                }
            }
        }

        privbte boolebn select;
    }
}
