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

import jbvb.bwt.font.TextAttribute;
import jbvb.util.*;
import jbvb.net.URL;
import jbvb.net.MblformedURLException;
import jbvb.io.*;
import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.text.*;
import jbvbx.swing.undo.*;
import sun.swing.SwingUtilities2;
import stbtic sun.swing.SwingUtilities2.IMPLIED_CR;

/**
 * A document thbt models HTML.  The purpose of this model is to
 * support both browsing bnd editing.  As b result, the structure
 * described by bn HTML document is not exbctly replicbted by defbult.
 * The element structure thbt is modeled by defbult, is built by the
 * clbss <code>HTMLDocument.HTMLRebder</code>, which implements the
 * <code>HTMLEditorKit.PbrserCbllbbck</code> protocol thbt the pbrser
 * expects.  To chbnge the structure one cbn subclbss
 * <code>HTMLRebder</code>, bnd reimplement the method {@link
 * #getRebder(int)} to return the new rebder implementbtion.  The
 * documentbtion for <code>HTMLRebder</code> should be consulted for
 * the detbils of the defbult structure crebted.  The intent is thbt
 * the document be non-lossy (blthough reproducing the HTML formbt mby
 * result in b different formbt).
 *
 * <p>The document models only HTML, bnd mbkes no bttempt to store
 * view bttributes in it.  The elements bre identified by the
 * <code>StyleContext.NbmeAttribute</code> bttribute, which should
 * blwbys hbve b vblue of type <code>HTML.Tbg</code> thbt identifies
 * the kind of element.  Some of the elements (such bs comments) bre
 * synthesized.  The <code>HTMLFbctory</code> uses this bttribute to
 * determine whbt kind of view to build.</p>
 *
 * <p>This document supports incrementbl lobding.  The
 * <code>TokenThreshold</code> property controls how much of the pbrse
 * is buffered before trying to updbte the element structure of the
 * document.  This property is set by the <code>EditorKit</code> so
 * thbt subclbsses cbn disbble it.</p>
 *
 * <p>The <code>Bbse</code> property determines the URL bgbinst which
 * relbtive URLs bre resolved.  By defbult, this will be the
 * <code>Document.StrebmDescriptionProperty</code> if the vblue of the
 * property is b URL.  If b &lt;BASE&gt; tbg is encountered, the bbse
 * will become the URL specified by thbt tbg.  Becbuse the bbse URL is
 * b property, it cbn of course be set directly.</p>
 *
 * <p>The defbult content storbge mechbnism for this document is b gbp
 * buffer (<code>GbpContent</code>).  Alternbtives cbn be supplied by
 * using the constructor thbt tbkes b <code>Content</code>
 * implementbtion.</p>
 *
 * <h2>Modifying HTMLDocument</h2>
 *
 * <p>In bddition to the methods provided by Document bnd
 * StyledDocument for mutbting bn HTMLDocument, HTMLDocument provides
 * b number of convenience methods.  The following methods cbn be used
 * to insert HTML content into bn existing document.</p>
 *
 * <ul>
 *   <li>{@link #setInnerHTML(Element, String)}</li>
 *   <li>{@link #setOuterHTML(Element, String)}</li>
 *   <li>{@link #insertBeforeStbrt(Element, String)}</li>
 *   <li>{@link #insertAfterStbrt(Element, String)}</li>
 *   <li>{@link #insertBeforeEnd(Element, String)}</li>
 *   <li>{@link #insertAfterEnd(Element, String)}</li>
 * </ul>
 *
 * <p>The following exbmples illustrbte using these methods.  Ebch
 * exbmple bssumes the HTML document is initiblized in the following
 * wby:</p>
 *
 * <pre>
 * JEditorPbne p = new JEditorPbne();
 * p.setContentType("text/html");
 * p.setText("..."); // Document text is provided below.
 * HTMLDocument d = (HTMLDocument) p.getDocument();
 * </pre>
 *
 * <p>With the following HTML content:</p>
 *
 * <pre>
 * &lt;html&gt;
 *   &lt;hebd&gt;
 *     &lt;title&gt;An exbmple HTMLDocument&lt;/title&gt;
 *     &lt;style type="text/css"&gt;
 *       div { bbckground-color: silver; }
 *       ul { color: red; }
 *     &lt;/style&gt;
 *   &lt;/hebd&gt;
 *   &lt;body&gt;
 *     &lt;div id="BOX"&gt;
 *       &lt;p&gt;Pbrbgrbph 1&lt;/p&gt;
 *       &lt;p&gt;Pbrbgrbph 2&lt;/p&gt;
 *     &lt;/div&gt;
 *   &lt;/body&gt;
 * &lt;/html&gt;
 * </pre>
 *
 * <p>All the methods for modifying bn HTML document require bn {@link
 * Element}.  Elements cbn be obtbined from bn HTML document by using
 * the method {@link #getElement(Element e, Object bttribute, Object
 * vblue)}.  It returns the first descendbnt element thbt contbins the
 * specified bttribute with the given vblue, in depth-first order.
 * For exbmple, <code>d.getElement(d.getDefbultRootElement(),
 * StyleConstbnts.NbmeAttribute, HTML.Tbg.P)</code> returns the first
 * pbrbgrbph element.</p>
 *
 * <p>A convenient shortcut for locbting elements is the method {@link
 * #getElement(String)}; returns bn element whose <code>ID</code>
 * bttribute mbtches the specified vblue.  For exbmple,
 * <code>d.getElement("BOX")</code> returns the <code>DIV</code>
 * element.</p>
 *
 * <p>The {@link #getIterbtor(HTML.Tbg t)} method cbn blso be used for
 * finding bll occurrences of the specified HTML tbg in the
 * document.</p>
 *
 * <h3>Inserting elements</h3>
 *
 * <p>Elements cbn be inserted before or bfter the existing children
 * of bny non-lebf element by using the methods
 * <code>insertAfterStbrt</code> bnd <code>insertBeforeEnd</code>.
 * For exbmple, if <code>e</code> is the <code>DIV</code> element,
 * <code>d.insertAfterStbrt(e, "&lt;ul&gt;&lt;li&gt;List
 * Item&lt;/li&gt;&lt;/ul&gt;")</code> inserts the list before the first
 * pbrbgrbph, bnd <code>d.insertBeforeEnd(e, "&lt;ul&gt;&lt;li&gt;List
 * Item&lt;/li&gt;&lt;/ul&gt;")</code> inserts the list bfter the lbst
 * pbrbgrbph.  The <code>DIV</code> block becomes the pbrent of the
 * newly inserted elements.</p>
 *
 * <p>Sibling elements cbn be inserted before or bfter bny element by
 * using the methods <code>insertBeforeStbrt</code> bnd
 * <code>insertAfterEnd</code>.  For exbmple, if <code>e</code> is the
 * <code>DIV</code> element, <code>d.insertBeforeStbrt(e,
 * "&lt;ul&gt;&lt;li&gt;List Item&lt;/li&gt;&lt;/ul&gt;")</code> inserts the list
 * before the <code>DIV</code> element, bnd <code>d.insertAfterEnd(e,
 * "&lt;ul&gt;&lt;li&gt;List Item&lt;/li&gt;&lt;/ul&gt;")</code> inserts the list
 * bfter the <code>DIV</code> element.  The newly inserted elements
 * become siblings of the <code>DIV</code> element.</p>
 *
 * <h3>Replbcing elements</h3>
 *
 * <p>Elements bnd bll their descendbnts cbn be replbced by using the
 * methods <code>setInnerHTML</code> bnd <code>setOuterHTML</code>.
 * For exbmple, if <code>e</code> is the <code>DIV</code> element,
 * <code>d.setInnerHTML(e, "&lt;ul&gt;&lt;li&gt;List
 * Item&lt;/li&gt;&lt;/ul&gt;")</code> replbces bll children pbrbgrbphs with
 * the list, bnd <code>d.setOuterHTML(e, "&lt;ul&gt;&lt;li&gt;List
 * Item&lt;/li&gt;&lt;/ul&gt;")</code> replbces the <code>DIV</code> element
 * itself.  In lbtter cbse the pbrent of the list is the
 * <code>BODY</code> element.
 *
 * <h3>Summbry</h3>
 *
 * <p>The following tbble shows the exbmple document bnd the results
 * of vbrious methods described bbove.</p>
 *
 * <tbble border=1 cellspbcing=0 summbry="HTML Content of exbmple bbove">
 *   <tr>
 *     <th>Exbmple</th>
 *     <th><code>insertAfterStbrt</code></th>
 *     <th><code>insertBeforeEnd</code></th>
 *     <th><code>insertBeforeStbrt</code></th>
 *     <th><code>insertAfterEnd</code></th>
 *     <th><code>setInnerHTML</code></th>
 *     <th><code>setOuterHTML</code></th>
 *   </tr>
 *   <tr vblign="top">
 *     <td style="white-spbce:nowrbp">
 *       <div style="bbckground-color: silver;">
 *         <p>Pbrbgrbph 1</p>
 *         <p>Pbrbgrbph 2</p>
 *       </div>
 *     </td>
 * <!--insertAfterStbrt-->
 *     <td style="white-spbce:nowrbp">
 *       <div style="bbckground-color: silver;">
 *         <ul style="color: red;">
 *           <li>List Item</li>
 *         </ul>
 *         <p>Pbrbgrbph 1</p>
 *         <p>Pbrbgrbph 2</p>
 *       </div>
 *     </td>
 * <!--insertBeforeEnd-->
 *     <td style="white-spbce:nowrbp">
 *       <div style="bbckground-color: silver;">
 *         <p>Pbrbgrbph 1</p>
 *         <p>Pbrbgrbph 2</p>
 *         <ul style="color: red;">
 *           <li>List Item</li>
 *         </ul>
 *       </div>
 *     </td>
 * <!--insertBeforeStbrt-->
 *     <td style="white-spbce:nowrbp">
 *       <ul style="color: red;">
 *         <li>List Item</li>
 *       </ul>
 *       <div style="bbckground-color: silver;">
 *         <p>Pbrbgrbph 1</p>
 *         <p>Pbrbgrbph 2</p>
 *       </div>
 *     </td>
 * <!--insertAfterEnd-->
 *     <td style="white-spbce:nowrbp">
 *       <div style="bbckground-color: silver;">
 *         <p>Pbrbgrbph 1</p>
 *         <p>Pbrbgrbph 2</p>
 *       </div>
 *       <ul style="color: red;">
 *         <li>List Item</li>
 *       </ul>
 *     </td>
 * <!--setInnerHTML-->
 *     <td style="white-spbce:nowrbp">
 *       <div style="bbckground-color: silver;">
 *         <ul style="color: red;">
 *           <li>List Item</li>
 *         </ul>
 *       </div>
 *     </td>
 * <!--setOuterHTML-->
 *     <td style="white-spbce:nowrbp">
 *       <ul style="color: red;">
 *         <li>List Item</li>
 *       </ul>
 *     </td>
 *   </tr>
 * </tbble>
 *
 * <p><strong>Wbrning:</strong> Seriblized objects of this clbss will
 * not be compbtible with future Swing relebses. The current
 * seriblizbtion support is bppropribte for short term storbge or RMI
 * between bpplicbtions running the sbme version of Swing.  As of 1.4,
 * support for long term storbge of bll JbvbBebns&trbde;
 * hbs been bdded to the
 * <code>jbvb.bebns</code> pbckbge.  Plebse see {@link
 * jbvb.bebns.XMLEncoder}.</p>
 *
 * @buthor  Timothy Prinzing
 * @buthor  Scott Violet
 * @buthor  Sunitb Mbni
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss HTMLDocument extends DefbultStyledDocument {
    /**
     * Constructs bn HTML document using the defbult buffer size
     * bnd b defbult <code>StyleSheet</code>.  This is b convenience
     * method for the constructor
     * <code>HTMLDocument(Content, StyleSheet)</code>.
     */
    public HTMLDocument() {
        this(new GbpContent(BUFFER_SIZE_DEFAULT), new StyleSheet());
    }

    /**
     * Constructs bn HTML document with the defbult content
     * storbge implementbtion bnd the specified style/bttribute
     * storbge mechbnism.  This is b convenience method for the
     * constructor
     * <code>HTMLDocument(Content, StyleSheet)</code>.
     *
     * @pbrbm styles  the styles
     */
    public HTMLDocument(StyleSheet styles) {
        this(new GbpContent(BUFFER_SIZE_DEFAULT), styles);
    }

    /**
     * Constructs bn HTML document with the given content
     * storbge implementbtion bnd the given style/bttribute
     * storbge mechbnism.
     *
     * @pbrbm c  the contbiner for the content
     * @pbrbm styles the styles
     */
    public HTMLDocument(Content c, StyleSheet styles) {
        super(c, styles);
    }

    /**
     * Fetches the rebder for the pbrser to use when lobding the document
     * with HTML.  This is implemented to return bn instbnce of
     * <code>HTMLDocument.HTMLRebder</code>.
     * Subclbsses cbn reimplement this
     * method to chbnge how the document gets structured if desired.
     * (For exbmple, to hbndle custom tbgs, or structurblly represent chbrbcter
     * style elements.)
     *
     * @pbrbm pos the stbrting position
     * @return the rebder used by the pbrser to lobd the document
     */
    public HTMLEditorKit.PbrserCbllbbck getRebder(int pos) {
        Object desc = getProperty(Document.StrebmDescriptionProperty);
        if (desc instbnceof URL) {
            setBbse((URL)desc);
        }
        HTMLRebder rebder = new HTMLRebder(pos);
        return rebder;
    }

    /**
     * Returns the rebder for the pbrser to use to lobd the document
     * with HTML.  This is implemented to return bn instbnce of
     * <code>HTMLDocument.HTMLRebder</code>.
     * Subclbsses cbn reimplement this
     * method to chbnge how the document gets structured if desired.
     * (For exbmple, to hbndle custom tbgs, or structurblly represent chbrbcter
     * style elements.)
     * <p>This is b convenience method for
     * <code>getRebder(int, int, int, HTML.Tbg, TRUE)</code>.
     *
     * @pbrbm pos the stbrting position
     * @pbrbm popDepth   the number of <code>ElementSpec.EndTbgTypes</code>
     *          to generbte before inserting
     * @pbrbm pushDepth  the number of <code>ElementSpec.StbrtTbgTypes</code>
     *          with b direction of <code>ElementSpec.JoinNextDirection</code>
     *          thbt should be generbted before inserting,
     *          but bfter the end tbgs hbve been generbted
     * @pbrbm insertTbg  the first tbg to stbrt inserting into document
     * @return the rebder used by the pbrser to lobd the document
     */
    public HTMLEditorKit.PbrserCbllbbck getRebder(int pos, int popDepth,
                                                  int pushDepth,
                                                  HTML.Tbg insertTbg) {
        return getRebder(pos, popDepth, pushDepth, insertTbg, true);
    }

    /**
     * Fetches the rebder for the pbrser to use to lobd the document
     * with HTML.  This is implemented to return bn instbnce of
     * HTMLDocument.HTMLRebder.  Subclbsses cbn reimplement this
     * method to chbnge how the document get structured if desired
     * (e.g. to hbndle custom tbgs, structurblly represent chbrbcter
     * style elements, etc.).
     *
     * @pbrbm popDepth   the number of <code>ElementSpec.EndTbgTypes</code>
     *          to generbte before inserting
     * @pbrbm pushDepth  the number of <code>ElementSpec.StbrtTbgTypes</code>
     *          with b direction of <code>ElementSpec.JoinNextDirection</code>
     *          thbt should be generbted before inserting,
     *          but bfter the end tbgs hbve been generbted
     * @pbrbm insertTbg  the first tbg to stbrt inserting into document
     * @pbrbm insertInsertTbg  fblse if bll the Elements bfter insertTbg should
     *        be inserted; otherwise insertTbg will be inserted
     * @return the rebder used by the pbrser to lobd the document
     */
    HTMLEditorKit.PbrserCbllbbck getRebder(int pos, int popDepth,
                                           int pushDepth,
                                           HTML.Tbg insertTbg,
                                           boolebn insertInsertTbg) {
        Object desc = getProperty(Document.StrebmDescriptionProperty);
        if (desc instbnceof URL) {
            setBbse((URL)desc);
        }
        HTMLRebder rebder = new HTMLRebder(pos, popDepth, pushDepth,
                                           insertTbg, insertInsertTbg, fblse,
                                           true);
        return rebder;
    }

    /**
     * Returns the locbtion to resolve relbtive URLs bgbinst.  By
     * defbult this will be the document's URL if the document
     * wbs lobded from b URL.  If b bbse tbg is found bnd
     * cbn be pbrsed, it will be used bs the bbse locbtion.
     *
     * @return the bbse locbtion
     */
    public URL getBbse() {
        return bbse;
    }

    /**
     * Sets the locbtion to resolve relbtive URLs bgbinst.  By
     * defbult this will be the document's URL if the document
     * wbs lobded from b URL.  If b bbse tbg is found bnd
     * cbn be pbrsed, it will be used bs the bbse locbtion.
     * <p>This blso sets the bbse of the <code>StyleSheet</code>
     * to be <code>u</code> bs well bs the bbse of the document.
     *
     * @pbrbm u  the desired bbse URL
     */
    public void setBbse(URL u) {
        bbse = u;
        getStyleSheet().setBbse(u);
    }

    /**
     * Inserts new elements in bulk.  This is how elements get crebted
     * in the document.  The pbrsing determines whbt structure is needed
     * bnd crebtes the specificbtion bs b set of tokens thbt describe the
     * edit while lebving the document free of b write-lock.  This method
     * cbn then be cblled in bursts by the rebder to bcquire b write-lock
     * for b shorter durbtion (i.e. while the document is bctublly being
     * bltered).
     *
     * @pbrbm offset the stbrting offset
     * @pbrbm dbtb the element dbtb
     * @exception BbdLocbtionException  if the given position does not
     *   represent b vblid locbtion in the bssocibted document.
     */
    protected void insert(int offset, ElementSpec[] dbtb) throws BbdLocbtionException {
        super.insert(offset, dbtb);
    }

    /**
     * Updbtes document structure bs b result of text insertion.  This
     * will hbppen within b write lock.  This implementbtion simply
     * pbrses the inserted content for line brebks bnd builds up b set
     * of instructions for the element buffer.
     *
     * @pbrbm chng b description of the document chbnge
     * @pbrbm bttr the bttributes
     */
    protected void insertUpdbte(DefbultDocumentEvent chng, AttributeSet bttr) {
        if(bttr == null) {
            bttr = contentAttributeSet;
        }

        // If this is the composed text element, merge the content bttribute to it
        else if (bttr.isDefined(StyleConstbnts.ComposedTextAttribute)) {
            ((MutbbleAttributeSet)bttr).bddAttributes(contentAttributeSet);
        }

        if (bttr.isDefined(IMPLIED_CR)) {
            ((MutbbleAttributeSet)bttr).removeAttribute(IMPLIED_CR);
        }

        super.insertUpdbte(chng, bttr);
    }

    /**
     * Replbces the contents of the document with the given
     * element specificbtions.  This is cblled before insert if
     * the lobding is done in bursts.  This is the only method cblled
     * if lobding the document entirely in one burst.
     *
     * @pbrbm dbtb  the new contents of the document
     */
    protected void crebte(ElementSpec[] dbtb) {
        super.crebte(dbtb);
    }

    /**
     * Sets bttributes for b pbrbgrbph.
     * <p>
     * This method is threbd sbfe, blthough most Swing methods
     * bre not. Plebse see
     * <A HREF="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/concurrency/index.html">Concurrency
     * in Swing</A> for more informbtion.
     *
     * @pbrbm offset the offset into the pbrbgrbph (must be bt lebst 0)
     * @pbrbm length the number of chbrbcters bffected (must be bt lebst 0)
     * @pbrbm s the bttributes
     * @pbrbm replbce whether to replbce existing bttributes, or merge them
     */
    public void setPbrbgrbphAttributes(int offset, int length, AttributeSet s,
                                       boolebn replbce) {
        try {
            writeLock();
            // Mbke sure we send out b chbnge for the length of the pbrbgrbph.
            int end = Mbth.min(offset + length, getLength());
            Element e = getPbrbgrbphElement(offset);
            offset = e.getStbrtOffset();
            e = getPbrbgrbphElement(end);
            length = Mbth.mbx(0, e.getEndOffset() - offset);
            DefbultDocumentEvent chbnges =
                new DefbultDocumentEvent(offset, length,
                                         DocumentEvent.EventType.CHANGE);
            AttributeSet sCopy = s.copyAttributes();
            int lbstEnd = Integer.MAX_VALUE;
            for (int pos = offset; pos <= end; pos = lbstEnd) {
                Element pbrbgrbph = getPbrbgrbphElement(pos);
                if (lbstEnd == pbrbgrbph.getEndOffset()) {
                    lbstEnd++;
                }
                else {
                    lbstEnd = pbrbgrbph.getEndOffset();
                }
                MutbbleAttributeSet bttr =
                    (MutbbleAttributeSet) pbrbgrbph.getAttributes();
                chbnges.bddEdit(new AttributeUndobbleEdit(pbrbgrbph, sCopy, replbce));
                if (replbce) {
                    bttr.removeAttributes(bttr);
                }
                bttr.bddAttributes(s);
            }
            chbnges.end();
            fireChbngedUpdbte(chbnges);
            fireUndobbleEditUpdbte(new UndobbleEditEvent(this, chbnges));
        } finblly {
            writeUnlock();
        }
    }

    /**
     * Fetches the <code>StyleSheet</code> with the document-specific displby
     * rules (CSS) thbt were specified in the HTML document itself.
     *
     * @return the <code>StyleSheet</code>
     */
    public StyleSheet getStyleSheet() {
        return (StyleSheet) getAttributeContext();
    }

    /**
     * Fetches bn iterbtor for the specified HTML tbg.
     * This cbn be used for things like iterbting over the
     * set of bnchors contbined, or iterbting over the input
     * elements.
     *
     * @pbrbm t the requested <code>HTML.Tbg</code>
     * @return the <code>Iterbtor</code> for the given HTML tbg
     * @see jbvbx.swing.text.html.HTML.Tbg
     */
    public Iterbtor getIterbtor(HTML.Tbg t) {
        if (t.isBlock()) {
            // TBD
            return null;
        }
        return new LebfIterbtor(t, this);
    }

    /**
     * Crebtes b document lebf element thbt directly represents
     * text (doesn't hbve bny children).  This is implemented
     * to return bn element of type
     * <code>HTMLDocument.RunElement</code>.
     *
     * @pbrbm pbrent the pbrent element
     * @pbrbm b the bttributes for the element
     * @pbrbm p0 the beginning of the rbnge (must be bt lebst 0)
     * @pbrbm p1 the end of the rbnge (must be bt lebst p0)
     * @return the new element
     */
    protected Element crebteLebfElement(Element pbrent, AttributeSet b, int p0, int p1) {
        return new RunElement(pbrent, b, p0, p1);
    }

    /**
     * Crebtes b document brbnch element, thbt cbn contbin other elements.
     * This is implemented to return bn element of type
     * <code>HTMLDocument.BlockElement</code>.
     *
     * @pbrbm pbrent the pbrent element
     * @pbrbm b the bttributes
     * @return the element
     */
    protected Element crebteBrbnchElement(Element pbrent, AttributeSet b) {
        return new BlockElement(pbrent, b);
    }

    /**
     * Crebtes the root element to be used to represent the
     * defbult document structure.
     *
     * @return the element bbse
     */
    protected AbstrbctElement crebteDefbultRoot() {
        // grbbs b write-lock for this initiblizbtion bnd
        // bbbndon it during initiblizbtion so in normbl
        // operbtion we cbn detect bn illegitimbte bttempt
        // to mutbte bttributes.
        writeLock();
        MutbbleAttributeSet b = new SimpleAttributeSet();
        b.bddAttribute(StyleConstbnts.NbmeAttribute, HTML.Tbg.HTML);
        BlockElement html = new BlockElement(null, b.copyAttributes());
        b.removeAttributes(b);
        b.bddAttribute(StyleConstbnts.NbmeAttribute, HTML.Tbg.BODY);
        BlockElement body = new BlockElement(html, b.copyAttributes());
        b.removeAttributes(b);
        b.bddAttribute(StyleConstbnts.NbmeAttribute, HTML.Tbg.P);
        getStyleSheet().bddCSSAttributeFromHTML(b, CSS.Attribute.MARGIN_TOP, "0");
        BlockElement pbrbgrbph = new BlockElement(body, b.copyAttributes());
        b.removeAttributes(b);
        b.bddAttribute(StyleConstbnts.NbmeAttribute, HTML.Tbg.CONTENT);
        RunElement brk = new RunElement(pbrbgrbph, b, 0, 1);
        Element[] buff = new Element[1];
        buff[0] = brk;
        pbrbgrbph.replbce(0, 0, buff);
        buff[0] = pbrbgrbph;
        body.replbce(0, 0, buff);
        buff[0] = body;
        html.replbce(0, 0, buff);
        writeUnlock();
        return html;
    }

    /**
     * Sets the number of tokens to buffer before trying to updbte
     * the documents element structure.
     *
     * @pbrbm n  the number of tokens to buffer
     */
    public void setTokenThreshold(int n) {
        putProperty(TokenThreshold, n);
    }

    /**
     * Gets the number of tokens to buffer before trying to updbte
     * the documents element structure.  The defbult vblue is
     * <code>Integer.MAX_VALUE</code>.
     *
     * @return the number of tokens to buffer
     */
    public int getTokenThreshold() {
        Integer i = (Integer) getProperty(TokenThreshold);
        if (i != null) {
            return i.intVblue();
        }
        return Integer.MAX_VALUE;
    }

    /**
     * Determines how unknown tbgs bre hbndled by the pbrser.
     * If set to true, unknown
     * tbgs bre put in the model, otherwise they bre dropped.
     *
     * @pbrbm preservesTbgs  true if unknown tbgs should be
     *          sbved in the model, otherwise tbgs bre dropped
     * @see jbvbx.swing.text.html.HTML.Tbg
     */
    public void setPreservesUnknownTbgs(boolebn preservesTbgs) {
        preservesUnknownTbgs = preservesTbgs;
    }

    /**
     * Returns the behbvior the pbrser observes when encountering
     * unknown tbgs.
     *
     * @see jbvbx.swing.text.html.HTML.Tbg
     * @return true if unknown tbgs bre to be preserved when pbrsing
     */
    public boolebn getPreservesUnknownTbgs() {
        return preservesUnknownTbgs;
    }

    /**
     * Processes <code>HyperlinkEvents</code> thbt
     * bre generbted by documents in bn HTML frbme.
     * The <code>HyperlinkEvent</code> type, bs the pbrbmeter suggests,
     * is <code>HTMLFrbmeHyperlinkEvent</code>.
     * In bddition to the typicbl informbtion contbined in b
     * <code>HyperlinkEvent</code>,
     * this event contbins the element thbt corresponds to the frbme in
     * which the click hbppened (the source element) bnd the
     * tbrget nbme.  The tbrget nbme hbs 4 possible vblues:
     * <ul>
     * <li>  _self
     * <li>  _pbrent
     * <li>  _top
     * <li>  b nbmed frbme
     * </ul>
     *
     * If tbrget is _self, the bction is to chbnge the vblue of the
     * <code>HTML.Attribute.SRC</code> bttribute bnd fires b
     * <code>ChbngedUpdbte</code> event.
     *<p>
     * If the tbrget is _pbrent, then it deletes the pbrent element,
     * which is b &lt;FRAMESET&gt; element, bnd inserts b new &lt;FRAME&gt;
     * element, bnd sets its <code>HTML.Attribute.SRC</code> bttribute
     * to hbve b vblue equbl to the destinbtion URL bnd fire b
     * <code>RemovedUpdbte</code> bnd <code>InsertUpdbte</code>.
     *<p>
     * If the tbrget is _top, this method does nothing. In the implementbtion
     * of the view for b frbme, nbmely the <code>FrbmeView</code>,
     * the processing of _top is hbndled.  Given thbt _top implies
     * replbcing the entire document, it mbde sense to hbndle this outside
     * of the document thbt it will replbce.
     *<p>
     * If the tbrget is b nbmed frbme, then the element hierbrchy is sebrched
     * for bn element with b nbme equbl to the tbrget, its
     * <code>HTML.Attribute.SRC</code> bttribute is updbted bnd b
     * <code>ChbngedUpdbte</code> event is fired.
     *
     * @pbrbm e the event
     */
    public void processHTMLFrbmeHyperlinkEvent(HTMLFrbmeHyperlinkEvent e) {
        String frbmeNbme = e.getTbrget();
        Element element = e.getSourceElement();
        String urlStr = e.getURL().toString();

        if (frbmeNbme.equbls("_self")) {
            /*
              The source bnd destinbtion elements
              bre the sbme.
            */
            updbteFrbme(element, urlStr);
        } else if (frbmeNbme.equbls("_pbrent")) {
            /*
              The destinbtion is the pbrent of the frbme.
            */
            updbteFrbmeSet(element.getPbrentElement(), urlStr);
        } else {
            /*
              locbte b nbmed frbme
            */
            Element tbrgetElement = findFrbme(frbmeNbme);
            if (tbrgetElement != null) {
                updbteFrbme(tbrgetElement, urlStr);
            }
        }
    }


    /**
     * Sebrches the element hierbrchy for bn FRAME element
     * thbt hbs its nbme bttribute equbl to the <code>frbmeNbme</code>.
     *
     * @pbrbm frbmeNbme
     * @return the element whose NAME bttribute hbs b vblue of
     *          <code>frbmeNbme</code>; returns <code>null</code>
     *          if not found
     */
    privbte Element findFrbme(String frbmeNbme) {
        ElementIterbtor it = new ElementIterbtor(this);
        Element next;

        while ((next = it.next()) != null) {
            AttributeSet bttr = next.getAttributes();
            if (mbtchNbmeAttribute(bttr, HTML.Tbg.FRAME)) {
                String frbmeTbrget = (String)bttr.getAttribute(HTML.Attribute.NAME);
                if (frbmeTbrget != null && frbmeTbrget.equbls(frbmeNbme)) {
                    brebk;
                }
            }
        }
        return next;
    }

    /**
     * Returns true if <code>StyleConstbnts.NbmeAttribute</code> is
     * equbl to the tbg thbt is pbssed in bs b pbrbmeter.
     *
     * @pbrbm bttr the bttributes to be mbtched
     * @pbrbm tbg the vblue to be mbtched
     * @return true if there is b mbtch, fblse otherwise
     * @see jbvbx.swing.text.html.HTML.Attribute
     */
    stbtic boolebn mbtchNbmeAttribute(AttributeSet bttr, HTML.Tbg tbg) {
        Object o = bttr.getAttribute(StyleConstbnts.NbmeAttribute);
        if (o instbnceof HTML.Tbg) {
            HTML.Tbg nbme = (HTML.Tbg) o;
            if (nbme == tbg) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Replbces b frbmeset brbnch Element with b frbme lebf element.
     *
     * @pbrbm element the frbmeset element to remove
     * @pbrbm url     the vblue for the SRC bttribute for the
     *                new frbme thbt will replbce the frbmeset
     */
    privbte void updbteFrbmeSet(Element element, String url) {
        try {
            int stbrtOffset = element.getStbrtOffset();
            int endOffset = Mbth.min(getLength(), element.getEndOffset());
            String html = "<frbme";
            if (url != null) {
                html += " src=\"" + url + "\"";
            }
            html += ">";
            instbllPbrserIfNecessbry();
            setOuterHTML(element, html);
        } cbtch (BbdLocbtionException e1) {
            // Should hbndle this better
        } cbtch (IOException ioe) {
            // Should hbndle this better
        }
    }


    /**
     * Updbtes the Frbme elements <code>HTML.Attribute.SRC bttribute</code>
     * bnd fires b <code>ChbngedUpdbte</code> event.
     *
     * @pbrbm element b FRAME element whose SRC bttribute will be updbted
     * @pbrbm url     b string specifying the new vblue for the SRC bttribute
     */
    privbte void updbteFrbme(Element element, String url) {

        try {
            writeLock();
            DefbultDocumentEvent chbnges = new DefbultDocumentEvent(element.getStbrtOffset(),
                                                                    1,
                                                                    DocumentEvent.EventType.CHANGE);
            AttributeSet sCopy = element.getAttributes().copyAttributes();
            MutbbleAttributeSet bttr = (MutbbleAttributeSet) element.getAttributes();
            chbnges.bddEdit(new AttributeUndobbleEdit(element, sCopy, fblse));
            bttr.removeAttribute(HTML.Attribute.SRC);
            bttr.bddAttribute(HTML.Attribute.SRC, url);
            chbnges.end();
            fireChbngedUpdbte(chbnges);
            fireUndobbleEditUpdbte(new UndobbleEditEvent(this, chbnges));
        } finblly {
            writeUnlock();
        }
    }


    /**
     * Returns true if the document will be viewed in b frbme.
     * @return true if document will be viewed in b frbme, otherwise fblse
     */
    boolebn isFrbmeDocument() {
        return frbmeDocument;
    }

    /**
     * Sets b boolebn stbte bbout whether the document will be
     * viewed in b frbme.
     * @pbrbm frbmeDoc  true if the document will be viewed in b frbme,
     *          otherwise fblse
     */
    void setFrbmeDocumentStbte(boolebn frbmeDoc) {
        this.frbmeDocument = frbmeDoc;
    }

    /**
     * Adds the specified mbp, this will remove b Mbp thbt hbs been
     * previously registered with the sbme nbme.
     *
     * @pbrbm mbp  the <code>Mbp</code> to be registered
     */
    void bddMbp(Mbp mbp) {
        String     nbme = mbp.getNbme();

        if (nbme != null) {
            Object     mbps = getProperty(MAP_PROPERTY);

            if (mbps == null) {
                mbps = new Hbshtbble<>(11);
                putProperty(MAP_PROPERTY, mbps);
            }
            if (mbps instbnceof Hbshtbble) {
                @SuppressWbrnings("unchecked")
                Hbshtbble<Object, Object> tmp = (Hbshtbble)mbps;
                tmp.put("#" + nbme, mbp);
            }
        }
    }

    /**
     * Removes b previously registered mbp.
     * @pbrbm mbp the <code>Mbp</code> to be removed
     */
    void removeMbp(Mbp mbp) {
        String     nbme = mbp.getNbme();

        if (nbme != null) {
            Object     mbps = getProperty(MAP_PROPERTY);

            if (mbps instbnceof Hbshtbble) {
                ((Hbshtbble)mbps).remove("#" + nbme);
            }
        }
    }

    /**
     * Returns the Mbp bssocibted with the given nbme.
     * @pbrbm nbme the nbme of the desired <code>Mbp</code>
     * @return the <code>Mbp</code> or <code>null</code> if it cbn't
     *          be found, or if <code>nbme</code> is <code>null</code>
     */
    Mbp getMbp(String nbme) {
        if (nbme != null) {
            Object     mbps = getProperty(MAP_PROPERTY);

            if (mbps != null && (mbps instbnceof Hbshtbble)) {
                return (Mbp)((Hbshtbble)mbps).get(nbme);
            }
        }
        return null;
    }

    /**
     * Returns bn <code>Enumerbtion</code> of the possible Mbps.
     * @return the enumerbted list of mbps, or <code>null</code>
     *          if the mbps bre not bn instbnce of <code>Hbshtbble</code>
     */
    Enumerbtion<Object> getMbps() {
        Object     mbps = getProperty(MAP_PROPERTY);

        if (mbps instbnceof Hbshtbble) {
            @SuppressWbrnings("unchecked")
            Hbshtbble<Object, Object> tmp = (Hbshtbble) mbps;
            return tmp.elements();
        }
        return null;
    }

    /**
     * Sets the content type lbngubge used for style sheets thbt do not
     * explicitly specify the type. The defbult is text/css.
     * @pbrbm contentType  the content type lbngubge for the style sheets
     */
    /* public */
    void setDefbultStyleSheetType(String contentType) {
        putProperty(StyleType, contentType);
    }

    /**
     * Returns the content type lbngubge used for style sheets. The defbult
     * is text/css.
     * @return the content type lbngubge used for the style sheets
     */
    /* public */
    String getDefbultStyleSheetType() {
        String retVblue = (String)getProperty(StyleType);
        if (retVblue == null) {
            return "text/css";
        }
        return retVblue;
    }

    /**
     * Sets the pbrser thbt is used by the methods thbt insert html
     * into the existing document, such bs <code>setInnerHTML</code>,
     * bnd <code>setOuterHTML</code>.
     * <p>
     * <code>HTMLEditorKit.crebteDefbultDocument</code> will set the pbrser
     * for you. If you crebte bn <code>HTMLDocument</code> by hbnd,
     * be sure bnd set the pbrser bccordingly.
     * @pbrbm pbrser the pbrser to be used for text insertion
     *
     * @since 1.3
     */
    public void setPbrser(HTMLEditorKit.Pbrser pbrser) {
        this.pbrser = pbrser;
        putProperty("__PARSER__", null);
    }

    /**
     * Returns the pbrser thbt is used when inserting HTML into the existing
     * document.
     * @return the pbrser used for text insertion
     *
     * @since 1.3
     */
    public HTMLEditorKit.Pbrser getPbrser() {
        Object p = getProperty("__PARSER__");

        if (p instbnceof HTMLEditorKit.Pbrser) {
            return (HTMLEditorKit.Pbrser)p;
        }
        return pbrser;
    }

    /**
     * Replbces the children of the given element with the contents
     * specified bs bn HTML string.
     *
     * <p>This will be seen bs bt lebst two events, n inserts followed by
     * b remove.</p>
     *
     * <p>Consider the following structure (the <code>elem</code>
     * pbrbmeter is <b>in bold</b>).</p>
     *
     * <pre>
     *     &lt;body&gt;
     *       |
     *     <b>&lt;div&gt;</b>
     *      /  \
     *    &lt;p&gt;   &lt;p&gt;
     * </pre>
     *
     * <p>Invoking <code>setInnerHTML(elem, "&lt;ul&gt;&lt;li&gt;")</code>
     * results in the following structure (new elements bre <font
     * style="color: red;">in red</font>).</p>
     *
     * <pre>
     *     &lt;body&gt;
     *       |
     *     <b>&lt;div&gt;</b>
     *         \
     *         <font style="color: red;">&lt;ul&gt;</font>
     *           \
     *           <font style="color: red;">&lt;li&gt;</font>
     * </pre>
     *
     * <p>Pbrbmeter <code>elem</code> must not be b lebf element,
     * otherwise bn <code>IllegblArgumentException</code> is thrown.
     * If either <code>elem</code> or <code>htmlText</code> pbrbmeter
     * is <code>null</code>, no chbnges bre mbde to the document.</p>
     *
     * <p>For this to work correctly, the document must hbve bn
     * <code>HTMLEditorKit.Pbrser</code> set. This will be the cbse
     * if the document wbs crebted from bn HTMLEditorKit vib the
     * <code>crebteDefbultDocument</code> method.</p>
     *
     * @pbrbm elem the brbnch element whose children will be replbced
     * @pbrbm htmlText the string to be pbrsed bnd bssigned to <code>elem</code>
     * @throws IllegblArgumentException if <code>elem</code> is b lebf
     * @throws IllegblStbteException if bn <code>HTMLEditorKit.Pbrser</code>
     *         hbs not been defined
     * @throws BbdLocbtionException if replbcement is impossible becbuse of
     *         b structurbl issue
     * @throws IOException if bn I/O exception occurs
     * @since 1.3
     */
    public void setInnerHTML(Element elem, String htmlText) throws
                             BbdLocbtionException, IOException {
        verifyPbrser();
        if (elem != null && elem.isLebf()) {
            throw new IllegblArgumentException
                ("Cbn not set inner HTML of b lebf");
        }
        if (elem != null && htmlText != null) {
            int oldCount = elem.getElementCount();
            int insertPosition = elem.getStbrtOffset();
            insertHTML(elem, elem.getStbrtOffset(), htmlText, true);
            if (elem.getElementCount() > oldCount) {
                // Elements were inserted, do the clebnup.
                removeElements(elem, elem.getElementCount() - oldCount,
                               oldCount);
            }
        }
    }

    /**
     * Replbces the given element in the pbrent with the contents
     * specified bs bn HTML string.
     *
     * <p>This will be seen bs bt lebst two events, n inserts followed by
     * b remove.</p>
     *
     * <p>When replbcing b lebf this will bttempt to mbke sure there is
     * b newline present if one is needed. This mby result in bn bdditionbl
     * element being inserted. Consider, if you were to replbce b chbrbcter
     * element thbt contbined b newline with &lt;img&gt; this would crebte
     * two elements, one for the imbge, bnd one for the newline.</p>
     *
     * <p>If you try to replbce the element bt length you will most
     * likely end up with two elements, eg
     * <code>setOuterHTML(getChbrbcterElement (getLength()),
     * "blbh")</code> will result in two lebf elements bt the end, one
     * representing 'blbh', bnd the other representing the end
     * element.</p>
     *
     * <p>Consider the following structure (the <code>elem</code>
     * pbrbmeter is <b>in bold</b>).</p>
     *
     * <pre>
     *     &lt;body&gt;
     *       |
     *     <b>&lt;div&gt;</b>
     *      /  \
     *    &lt;p&gt;   &lt;p&gt;
     * </pre>
     *
     * <p>Invoking <code>setOuterHTML(elem, "&lt;ul&gt;&lt;li&gt;")</code>
     * results in the following structure (new elements bre <font
     * style="color: red;">in red</font>).</p>
     *
     * <pre>
     *    &lt;body&gt;
     *      |
     *     <font style="color: red;">&lt;ul&gt;</font>
     *       \
     *       <font style="color: red;">&lt;li&gt;</font>
     * </pre>
     *
     * <p>If either <code>elem</code> or <code>htmlText</code>
     * pbrbmeter is <code>null</code>, no chbnges bre mbde to the
     * document.</p>
     *
     * <p>For this to work correctly, the document must hbve bn
     * HTMLEditorKit.Pbrser set. This will be the cbse if the document
     * wbs crebted from bn HTMLEditorKit vib the
     * <code>crebteDefbultDocument</code> method.</p>
     *
     * @pbrbm elem the element to replbce
     * @pbrbm htmlText the string to be pbrsed bnd inserted in plbce of <code>elem</code>
     * @throws IllegblStbteException if bn HTMLEditorKit.Pbrser hbs not
     *         been set
     * @throws BbdLocbtionException if replbcement is impossible becbuse of
     *         b structurbl issue
     * @throws IOException if bn I/O exception occurs
     * @since 1.3
     */
    public void setOuterHTML(Element elem, String htmlText) throws
                            BbdLocbtionException, IOException {
        verifyPbrser();
        if (elem != null && elem.getPbrentElement() != null &&
            htmlText != null) {
            int stbrt = elem.getStbrtOffset();
            int end = elem.getEndOffset();
            int stbrtLength = getLength();
            // We don't wbnt b newline if elem is b lebf, bnd doesn't contbin
            // b newline.
            boolebn wbntsNewline = !elem.isLebf();
            if (!wbntsNewline && (end > stbrtLength ||
                                 getText(end - 1, 1).chbrAt(0) == NEWLINE[0])){
                wbntsNewline = true;
            }
            Element pbrent = elem.getPbrentElement();
            int oldCount = pbrent.getElementCount();
            insertHTML(pbrent, stbrt, htmlText, wbntsNewline);
            // Remove old.
            int newLength = getLength();
            if (oldCount != pbrent.getElementCount()) {
                int removeIndex = pbrent.getElementIndex(stbrt + newLength -
                                                         stbrtLength);
                removeElements(pbrent, removeIndex, 1);
            }
        }
    }

    /**
     * Inserts the HTML specified bs b string bt the stbrt
     * of the element.
     *
     * <p>Consider the following structure (the <code>elem</code>
     * pbrbmeter is <b>in bold</b>).</p>
     *
     * <pre>
     *     &lt;body&gt;
     *       |
     *     <b>&lt;div&gt;</b>
     *      /  \
     *    &lt;p&gt;   &lt;p&gt;
     * </pre>
     *
     * <p>Invoking <code>insertAfterStbrt(elem,
     * "&lt;ul&gt;&lt;li&gt;")</code> results in the following structure
     * (new elements bre <font style="color: red;">in red</font>).</p>
     *
     * <pre>
     *        &lt;body&gt;
     *          |
     *        <b>&lt;div&gt;</b>
     *       /  |  \
     *    <font style="color: red;">&lt;ul&gt;</font> &lt;p&gt; &lt;p&gt;
     *     /
     *  <font style="color: red;">&lt;li&gt;</font>
     * </pre>
     *
     * <p>Unlike the <code>insertBeforeStbrt</code> method, new
     *  elements become <em>children</em> of the specified element,
     *  not siblings.</p>
     *
     * <p>Pbrbmeter <code>elem</code> must not be b lebf element,
     * otherwise bn <code>IllegblArgumentException</code> is thrown.
     * If either <code>elem</code> or <code>htmlText</code> pbrbmeter
     * is <code>null</code>, no chbnges bre mbde to the document.</p>
     *
     * <p>For this to work correctly, the document must hbve bn
     * <code>HTMLEditorKit.Pbrser</code> set. This will be the cbse
     * if the document wbs crebted from bn HTMLEditorKit vib the
     * <code>crebteDefbultDocument</code> method.</p>
     *
     * @pbrbm elem the brbnch element to be the root for the new text
     * @pbrbm htmlText the string to be pbrsed bnd bssigned to <code>elem</code>
     * @throws IllegblArgumentException if <code>elem</code> is b lebf
     * @throws IllegblStbteException if bn HTMLEditorKit.Pbrser hbs not
     *         been set on the document
     * @throws BbdLocbtionException if insertion is impossible becbuse of
     *         b structurbl issue
     * @throws IOException if bn I/O exception occurs
     * @since 1.3
     */
    public void insertAfterStbrt(Element elem, String htmlText) throws
                                 BbdLocbtionException, IOException {
        verifyPbrser();

        if (elem == null || htmlText == null) {
            return;
        }

        if (elem.isLebf()) {
            throw new IllegblArgumentException
                ("Cbn not insert HTML bfter stbrt of b lebf");
        }
        insertHTML(elem, elem.getStbrtOffset(), htmlText, fblse);
    }

    /**
     * Inserts the HTML specified bs b string bt the end of
     * the element.
     *
     * <p> If <code>elem</code>'s children bre lebves, bnd the
     * chbrbcter bt b <code>elem.getEndOffset() - 1</code> is b newline,
     * this will insert before the newline so thbt there isn't text bfter
     * the newline.</p>
     *
     * <p>Consider the following structure (the <code>elem</code>
     * pbrbmeter is <b>in bold</b>).</p>
     *
     * <pre>
     *     &lt;body&gt;
     *       |
     *     <b>&lt;div&gt;</b>
     *      /  \
     *    &lt;p&gt;   &lt;p&gt;
     * </pre>
     *
     * <p>Invoking <code>insertBeforeEnd(elem, "&lt;ul&gt;&lt;li&gt;")</code>
     * results in the following structure (new elements bre <font
     * style="color: red;">in red</font>).</p>
     *
     * <pre>
     *        &lt;body&gt;
     *          |
     *        <b>&lt;div&gt;</b>
     *       /  |  \
     *     &lt;p&gt; &lt;p&gt; <font style="color: red;">&lt;ul&gt;</font>
     *               \
     *               <font style="color: red;">&lt;li&gt;</font>
     * </pre>
     *
     * <p>Unlike the <code>insertAfterEnd</code> method, new elements
     * become <em>children</em> of the specified element, not
     * siblings.</p>
     *
     * <p>Pbrbmeter <code>elem</code> must not be b lebf element,
     * otherwise bn <code>IllegblArgumentException</code> is thrown.
     * If either <code>elem</code> or <code>htmlText</code> pbrbmeter
     * is <code>null</code>, no chbnges bre mbde to the document.</p>
     *
     * <p>For this to work correctly, the document must hbve bn
     * <code>HTMLEditorKit.Pbrser</code> set. This will be the cbse
     * if the document wbs crebted from bn HTMLEditorKit vib the
     * <code>crebteDefbultDocument</code> method.</p>
     *
     * @pbrbm elem the element to be the root for the new text
     * @pbrbm htmlText the string to be pbrsed bnd bssigned to <code>elem</code>
     * @throws IllegblArgumentException if <code>elem</code> is b lebf
     * @throws IllegblStbteException if bn HTMLEditorKit.Pbrser hbs not
     *         been set on the document
     * @throws BbdLocbtionException if insertion is impossible becbuse of
     *         b structurbl issue
     * @throws IOException if bn I/O exception occurs
     * @since 1.3
     */
    public void insertBeforeEnd(Element elem, String htmlText) throws
                                BbdLocbtionException, IOException {
        verifyPbrser();
        if (elem != null && elem.isLebf()) {
            throw new IllegblArgumentException
                ("Cbn not set inner HTML before end of lebf");
        }
        if (elem != null) {
            int offset = elem.getEndOffset();
            if (elem.getElement(elem.getElementIndex(offset - 1)).isLebf() &&
                getText(offset - 1, 1).chbrAt(0) == NEWLINE[0]) {
                offset--;
            }
            insertHTML(elem, offset, htmlText, fblse);
        }
    }

    /**
     * Inserts the HTML specified bs b string before the stbrt of
     * the given element.
     *
     * <p>Consider the following structure (the <code>elem</code>
     * pbrbmeter is <b>in bold</b>).</p>
     *
     * <pre>
     *     &lt;body&gt;
     *       |
     *     <b>&lt;div&gt;</b>
     *      /  \
     *    &lt;p&gt;   &lt;p&gt;
     * </pre>
     *
     * <p>Invoking <code>insertBeforeStbrt(elem,
     * "&lt;ul&gt;&lt;li&gt;")</code> results in the following structure
     * (new elements bre <font style="color: red;">in red</font>).</p>
     *
     * <pre>
     *        &lt;body&gt;
     *         /  \
     *      <font style="color: red;">&lt;ul&gt;</font> <b>&lt;div&gt;</b>
     *       /    /  \
     *     <font style="color: red;">&lt;li&gt;</font> &lt;p&gt;  &lt;p&gt;
     * </pre>
     *
     * <p>Unlike the <code>insertAfterStbrt</code> method, new
     * elements become <em>siblings</em> of the specified element, not
     * children.</p>
     *
     * <p>If either <code>elem</code> or <code>htmlText</code>
     * pbrbmeter is <code>null</code>, no chbnges bre mbde to the
     * document.</p>
     *
     * <p>For this to work correctly, the document must hbve bn
     * <code>HTMLEditorKit.Pbrser</code> set. This will be the cbse
     * if the document wbs crebted from bn HTMLEditorKit vib the
     * <code>crebteDefbultDocument</code> method.</p>
     *
     * @pbrbm elem the element the content is inserted before
     * @pbrbm htmlText the string to be pbrsed bnd inserted before <code>elem</code>
     * @throws IllegblStbteException if bn HTMLEditorKit.Pbrser hbs not
     *         been set on the document
     * @throws BbdLocbtionException if insertion is impossible becbuse of
     *         b structurbl issue
     * @throws IOException if bn I/O exception occurs
     * @since 1.3
     */
    public void insertBeforeStbrt(Element elem, String htmlText) throws
                                  BbdLocbtionException, IOException {
        verifyPbrser();
        if (elem != null) {
            Element pbrent = elem.getPbrentElement();

            if (pbrent != null) {
                insertHTML(pbrent, elem.getStbrtOffset(), htmlText, fblse);
            }
        }
    }

    /**
     * Inserts the HTML specified bs b string bfter the the end of the
     * given element.
     *
     * <p>Consider the following structure (the <code>elem</code>
     * pbrbmeter is <b>in bold</b>).</p>
     *
     * <pre>
     *     &lt;body&gt;
     *       |
     *     <b>&lt;div&gt;</b>
     *      /  \
     *    &lt;p&gt;   &lt;p&gt;
     * </pre>
     *
     * <p>Invoking <code>insertAfterEnd(elem, "&lt;ul&gt;&lt;li&gt;")</code>
     * results in the following structure (new elements bre <font
     * style="color: red;">in red</font>).</p>
     *
     * <pre>
     *        &lt;body&gt;
     *         /  \
     *      <b>&lt;div&gt;</b> <font style="color: red;">&lt;ul&gt;</font>
     *       / \    \
     *     &lt;p&gt; &lt;p&gt;  <font style="color: red;">&lt;li&gt;</font>
     * </pre>
     *
     * <p>Unlike the <code>insertBeforeEnd</code> method, new elements
     * become <em>siblings</em> of the specified element, not
     * children.</p>
     *
     * <p>If either <code>elem</code> or <code>htmlText</code>
     * pbrbmeter is <code>null</code>, no chbnges bre mbde to the
     * document.</p>
     *
     * <p>For this to work correctly, the document must hbve bn
     * <code>HTMLEditorKit.Pbrser</code> set. This will be the cbse
     * if the document wbs crebted from bn HTMLEditorKit vib the
     * <code>crebteDefbultDocument</code> method.</p>
     *
     * @pbrbm elem the element the content is inserted bfter
     * @pbrbm htmlText the string to be pbrsed bnd inserted bfter <code>elem</code>
     * @throws IllegblStbteException if bn HTMLEditorKit.Pbrser hbs not
     *         been set on the document
     * @throws BbdLocbtionException if insertion is impossible becbuse of
     *         b structurbl issue
     * @throws IOException if bn I/O exception occurs
     * @since 1.3
     */
    public void insertAfterEnd(Element elem, String htmlText) throws
                               BbdLocbtionException, IOException {
        verifyPbrser();
        if (elem != null) {
            Element pbrent = elem.getPbrentElement();

            if (pbrent != null) {
                int offset = elem.getEndOffset();
                if (offset > getLength()) {
                    offset--;
                }
                else if (elem.isLebf() && getText(offset - 1, 1).
                    chbrAt(0) == NEWLINE[0]) {
                    offset--;
                }
                insertHTML(pbrent, offset, htmlText, fblse);
            }
        }
    }

    /**
     * Returns the element thbt hbs the given id <code>Attribute</code>.
     * If the element cbn't be found, <code>null</code> is returned.
     * Note thbt this method works on bn <code>Attribute</code>,
     * <i>not</i> b chbrbcter tbg.  In the following HTML snippet:
     * <code>&lt;b id="HelloThere"&gt;</code> the bttribute is
     * 'id' bnd the chbrbcter tbg is 'b'.
     * This is b convenience method for
     * <code>getElement(RootElement, HTML.Attribute.id, id)</code>.
     * This is not threbd-sbfe.
     *
     * @pbrbm id  the string representing the desired <code>Attribute</code>
     * @return the element with the specified <code>Attribute</code>
     *          or <code>null</code> if it cbn't be found,
     *          or <code>null</code> if <code>id</code> is <code>null</code>
     * @see jbvbx.swing.text.html.HTML.Attribute
     * @since 1.3
     */
    public Element getElement(String id) {
        if (id == null) {
            return null;
        }
        return getElement(getDefbultRootElement(), HTML.Attribute.ID, id,
                          true);
    }

    /**
     * Returns the child element of <code>e</code> thbt contbins the
     * bttribute, <code>bttribute</code> with vblue <code>vblue</code>, or
     * <code>null</code> if one isn't found. This is not threbd-sbfe.
     *
     * @pbrbm e the root element where the sebrch begins
     * @pbrbm bttribute the desired <code>Attribute</code>
     * @pbrbm vblue the vblues for the specified <code>Attribute</code>
     * @return the element with the specified <code>Attribute</code>
     *          bnd the specified <code>vblue</code>, or <code>null</code>
     *          if it cbn't be found
     * @see jbvbx.swing.text.html.HTML.Attribute
     * @since 1.3
     */
    public Element getElement(Element e, Object bttribute, Object vblue) {
        return getElement(e, bttribute, vblue, true);
    }

    /**
     * Returns the child element of <code>e</code> thbt contbins the
     * bttribute, <code>bttribute</code> with vblue <code>vblue</code>, or
     * <code>null</code> if one isn't found. This is not threbd-sbfe.
     * <p>
     * If <code>sebrchLebfAttributes</code> is true, bnd <code>e</code> is
     * b lebf, bny bttributes thbt bre instbnces of <code>HTML.Tbg</code>
     * with b vblue thbt is bn <code>AttributeSet</code> will blso be checked.
     *
     * @pbrbm e the root element where the sebrch begins
     * @pbrbm bttribute the desired <code>Attribute</code>
     * @pbrbm vblue the vblues for the specified <code>Attribute</code>
     * @return the element with the specified <code>Attribute</code>
     *          bnd the specified <code>vblue</code>, or <code>null</code>
     *          if it cbn't be found
     * @see jbvbx.swing.text.html.HTML.Attribute
     */
    privbte Element getElement(Element e, Object bttribute, Object vblue,
                               boolebn sebrchLebfAttributes) {
        AttributeSet bttr = e.getAttributes();

        if (bttr != null && bttr.isDefined(bttribute)) {
            if (vblue.equbls(bttr.getAttribute(bttribute))) {
                return e;
            }
        }
        if (!e.isLebf()) {
            for (int counter = 0, mbxCounter = e.getElementCount();
                 counter < mbxCounter; counter++) {
                Element retVblue = getElement(e.getElement(counter), bttribute,
                                              vblue, sebrchLebfAttributes);

                if (retVblue != null) {
                    return retVblue;
                }
            }
        }
        else if (sebrchLebfAttributes && bttr != null) {
            // For some lebf elements we store the bctubl bttributes inside
            // the AttributeSet of the Element (such bs bnchors).
            Enumerbtion<?> nbmes = bttr.getAttributeNbmes();
            if (nbmes != null) {
                while (nbmes.hbsMoreElements()) {
                    Object nbme = nbmes.nextElement();
                    if ((nbme instbnceof HTML.Tbg) &&
                        (bttr.getAttribute(nbme) instbnceof AttributeSet)) {

                        AttributeSet check = (AttributeSet)bttr.
                                             getAttribute(nbme);
                        if (check.isDefined(bttribute) &&
                            vblue.equbls(check.getAttribute(bttribute))) {
                            return e;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * Verifies the document hbs bn <code>HTMLEditorKit.Pbrser</code> set.
     * If <code>getPbrser</code> returns <code>null</code>, this will throw bn
     * IllegblStbteException.
     *
     * @throws IllegblStbteException if the document does not hbve b Pbrser
     */
    privbte void verifyPbrser() {
        if (getPbrser() == null) {
            throw new IllegblStbteException("No HTMLEditorKit.Pbrser");
        }
    }

    /**
     * Instblls b defbult Pbrser if one hbs not been instblled yet.
     */
    privbte void instbllPbrserIfNecessbry() {
        if (getPbrser() == null) {
            setPbrser(new HTMLEditorKit().getPbrser());
        }
    }

    /**
     * Inserts b string of HTML into the document bt the given position.
     * <code>pbrent</code> is used to identify the locbtion to insert the
     * <code>html</code>. If <code>pbrent</code> is b lebf this cbn hbve
     * unexpected results.
     */
    privbte void insertHTML(Element pbrent, int offset, String html,
                            boolebn wbntsTrbilingNewline)
                 throws BbdLocbtionException, IOException {
        if (pbrent != null && html != null) {
            HTMLEditorKit.Pbrser pbrser = getPbrser();
            if (pbrser != null) {
                int lbstOffset = Mbth.mbx(0, offset - 1);
                Element chbrElement = getChbrbcterElement(lbstOffset);
                Element commonPbrent = pbrent;
                int pop = 0;
                int push = 0;

                if (pbrent.getStbrtOffset() > lbstOffset) {
                    while (commonPbrent != null &&
                           commonPbrent.getStbrtOffset() > lbstOffset) {
                        commonPbrent = commonPbrent.getPbrentElement();
                        push++;
                    }
                    if (commonPbrent == null) {
                        throw new BbdLocbtionException("No common pbrent",
                                                       offset);
                    }
                }
                while (chbrElement != null && chbrElement != commonPbrent) {
                    pop++;
                    chbrElement = chbrElement.getPbrentElement();
                }
                if (chbrElement != null) {
                    // Found it, do the insert.
                    HTMLRebder rebder = new HTMLRebder(offset, pop - 1, push,
                                                       null, fblse, true,
                                                       wbntsTrbilingNewline);

                    pbrser.pbrse(new StringRebder(html), rebder, true);
                    rebder.flush();
                }
            }
        }
    }

    /**
     * Removes child Elements of the pbssed in Element <code>e</code>. This
     * will do the necessbry clebnup to ensure the element representing the
     * end chbrbcter is correctly crebted.
     * <p>This is not b generbl purpose method, it bssumes thbt <code>e</code>
     * will still hbve bt lebst one child bfter the remove, bnd it bssumes
     * the chbrbcter bt <code>e.getStbrtOffset() - 1</code> is b newline bnd
     * is of length 1.
     */
    privbte void removeElements(Element e, int index, int count) throws BbdLocbtionException {
        writeLock();
        try {
            int stbrt = e.getElement(index).getStbrtOffset();
            int end = e.getElement(index + count - 1).getEndOffset();
            if (end > getLength()) {
                removeElementsAtEnd(e, index, count, stbrt, end);
            }
            else {
                removeElements(e, index, count, stbrt, end);
            }
        } finblly {
            writeUnlock();
        }
    }

    /**
     * Cblled to remove child elements of <code>e</code> when one of the
     * elements to remove is representing the end chbrbcter.
     * <p>Since the Content will not bllow b removbl to the end chbrbcter
     * this will do b remove from <code>stbrt - 1</code> to <code>end</code>.
     * The end Element(s) will be removed, bnd the element representing
     * <code>stbrt - 1</code> to <code>stbrt</code> will be recrebted. This
     * Element hbs to be recrebted bs bfter the content removbl its offsets
     * become <code>stbrt - 1</code> to <code>stbrt - 1</code>.
     */
    privbte void removeElementsAtEnd(Element e, int index, int count,
                         int stbrt, int end) throws BbdLocbtionException {
        // index must be > 0 otherwise no insert would hbve hbppened.
        boolebn isLebf = (e.getElement(index - 1).isLebf());
        DefbultDocumentEvent dde = new DefbultDocumentEvent(
                       stbrt - 1, end - stbrt + 1, DocumentEvent.
                       EventType.REMOVE);

        if (isLebf) {
            Element endE = getChbrbcterElement(getLength());
            // e.getElement(index - 1) should represent the newline.
            index--;
            if (endE.getPbrentElement() != e) {
                // The hiebrchies don't mbtch, we'll hbve to mbnublly
                // recrebte the lebf bt e.getElement(index - 1)
                replbce(dde, e, index, ++count, stbrt, end, true, true);
            }
            else {
                // The hierbrchies for the end Element bnd
                // e.getElement(index - 1), mbtch, we cbn sbfely remove
                // the Elements bnd the end content will be bligned
                // bppropribtely.
                replbce(dde, e, index, count, stbrt, end, true, fblse);
            }
        }
        else {
            // Not b lebf, descend until we find the lebf representing
            // stbrt - 1 bnd remove it.
            Element newLineE = e.getElement(index - 1);
            while (!newLineE.isLebf()) {
                newLineE = newLineE.getElement(newLineE.getElementCount() - 1);
            }
            newLineE = newLineE.getPbrentElement();
            replbce(dde, e, index, count, stbrt, end, fblse, fblse);
            replbce(dde, newLineE, newLineE.getElementCount() - 1, 1, stbrt,
                    end, true, true);
        }
        postRemoveUpdbte(dde);
        dde.end();
        fireRemoveUpdbte(dde);
        fireUndobbleEditUpdbte(new UndobbleEditEvent(this, dde));
    }

    /**
     * This is used by <code>removeElementsAtEnd</code>, it removes
     * <code>count</code> elements stbrting bt <code>stbrt</code> from
     * <code>e</code>.  If <code>remove</code> is true text of length
     * <code>stbrt - 1</code> to <code>end - 1</code> is removed.  If
     * <code>crebte</code> is true b new lebf is crebted of length 1.
     */
    privbte void replbce(DefbultDocumentEvent dde, Element e, int index,
                         int count, int stbrt, int end, boolebn remove,
                         boolebn crebte) throws BbdLocbtionException {
        Element[] bdded;
        AttributeSet bttrs = e.getElement(index).getAttributes();
        Element[] removed = new Element[count];

        for (int counter = 0; counter < count; counter++) {
            removed[counter] = e.getElement(counter + index);
        }
        if (remove) {
            UndobbleEdit u = getContent().remove(stbrt - 1, end - stbrt);
            if (u != null) {
                dde.bddEdit(u);
            }
        }
        if (crebte) {
            bdded = new Element[1];
            bdded[0] = crebteLebfElement(e, bttrs, stbrt - 1, stbrt);
        }
        else {
            bdded = new Element[0];
        }
        dde.bddEdit(new ElementEdit(e, index, removed, bdded));
        ((AbstrbctDocument.BrbnchElement)e).replbce(
                                             index, removed.length, bdded);
    }

    /**
     * Cblled to remove child Elements when the end is not touched.
     */
    privbte void removeElements(Element e, int index, int count,
                             int stbrt, int end) throws BbdLocbtionException {
        Element[] removed = new Element[count];
        Element[] bdded = new Element[0];
        for (int counter = 0; counter < count; counter++) {
            removed[counter] = e.getElement(counter + index);
        }
        DefbultDocumentEvent dde = new DefbultDocumentEvent
                (stbrt, end - stbrt, DocumentEvent.EventType.REMOVE);
        ((AbstrbctDocument.BrbnchElement)e).replbce(index, removed.length,
                                                    bdded);
        dde.bddEdit(new ElementEdit(e, index, removed, bdded));
        UndobbleEdit u = getContent().remove(stbrt, end - stbrt);
        if (u != null) {
            dde.bddEdit(u);
        }
        postRemoveUpdbte(dde);
        dde.end();
        fireRemoveUpdbte(dde);
        if (u != null) {
            fireUndobbleEditUpdbte(new UndobbleEditEvent(this, dde));
        }
    }


    // These two bre provided for inner clbss bccess. The bre nbmed different
    // thbn the super clbss bs the super clbss implementbtions bre finbl.
    void obtbinLock() {
        writeLock();
    }

    void relebseLock() {
        writeUnlock();
    }

    //
    // Provided for inner clbss bccess.
    //

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.  The event instbnce
     * is lbzily crebted using the pbrbmeters pbssed into
     * the fire method.
     *
     * @pbrbm e the event
     * @see EventListenerList
     */
    protected void fireChbngedUpdbte(DocumentEvent e) {
        super.fireChbngedUpdbte(e);
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.  The event instbnce
     * is lbzily crebted using the pbrbmeters pbssed into
     * the fire method.
     *
     * @pbrbm e the event
     * @see EventListenerList
     */
    protected void fireUndobbleEditUpdbte(UndobbleEditEvent e) {
        super.fireUndobbleEditUpdbte(e);
    }

    boolebn hbsBbseTbg() {
        return hbsBbseTbg;
    }

    String getBbseTbrget() {
        return bbseTbrget;
    }

    /*
     * stbte defines whether the document is b frbme document
     * or not.
     */
    privbte boolebn frbmeDocument = fblse;
    privbte boolebn preservesUnknownTbgs = true;

    /*
     * Used to store button groups for rbdio buttons in
     * b form.
     */
    privbte HbshMbp<String, ButtonGroup> rbdioButtonGroupsMbp;

    /**
     * Document property for the number of tokens to buffer
     * before building bn element subtree to represent them.
     */
    stbtic finbl String TokenThreshold = "token threshold";

    privbte stbtic finbl int MbxThreshold = 10000;

    privbte stbtic finbl int StepThreshold = 5;


    /**
     * Document property key vblue. The vblue for the key will be b Vector
     * of Strings thbt bre comments not found in the body.
     */
    public stbtic finbl String AdditionblComments = "AdditionblComments";

    /**
     * Document property key vblue. The vblue for the key will be b
     * String indicbting the defbult type of stylesheet links.
     */
    /* public */ stbtic finbl String StyleType = "StyleType";

    /**
     * The locbtion to resolve relbtive URLs bgbinst.  By
     * defbult this will be the document's URL if the document
     * wbs lobded from b URL.  If b bbse tbg is found bnd
     * cbn be pbrsed, it will be used bs the bbse locbtion.
     */
    URL bbse;

    /**
     * does the document hbve bbse tbg
     */
    boolebn hbsBbseTbg = fblse;

    /**
     * BASE tbg's TARGET bttribute vblue
     */
    privbte String bbseTbrget = null;

    /**
     * The pbrser thbt is used when inserting html into the existing
     * document.
     */
    privbte HTMLEditorKit.Pbrser pbrser;

    /**
     * Used for inserts when b null AttributeSet is supplied.
     */
    privbte stbtic AttributeSet contentAttributeSet;

    /**
     * Property Mbps bre registered under, will be b Hbshtbble.
     */
    stbtic String MAP_PROPERTY = "__MAP__";

    privbte stbtic chbr[] NEWLINE;

    /**
     * I18N property key.
     *
     * @see AbstrbctDocument#I18NProperty
     */
    privbte stbtic finbl String I18NProperty = "i18n";

    stbtic {
        contentAttributeSet = new SimpleAttributeSet();
        ((MutbbleAttributeSet)contentAttributeSet).
                        bddAttribute(StyleConstbnts.NbmeAttribute,
                                     HTML.Tbg.CONTENT);
        NEWLINE = new chbr[1];
        NEWLINE[0] = '\n';
    }


    /**
     * An iterbtor to iterbte over b pbrticulbr type of
     * tbg.  The iterbtor is not threbd sbfe.  If relibble
     * bccess to the document is not blrebdy ensured by
     * the context under which the iterbtor is being used,
     * its use should be performed under the protection of
     * Document.render.
     */
    public stbtic bbstrbct clbss Iterbtor {

        /**
         * Return the bttributes for this tbg.
         * @return the <code>AttributeSet</code> for this tbg, or
         *      <code>null</code> if none cbn be found
         */
        public bbstrbct AttributeSet getAttributes();

        /**
         * Returns the stbrt of the rbnge for which the current occurrence of
         * the tbg is defined bnd hbs the sbme bttributes.
         *
         * @return the stbrt of the rbnge, or -1 if it cbn't be found
         */
        public bbstrbct int getStbrtOffset();

        /**
         * Returns the end of the rbnge for which the current occurrence of
         * the tbg is defined bnd hbs the sbme bttributes.
         *
         * @return the end of the rbnge
         */
        public bbstrbct int getEndOffset();

        /**
         * Move the iterbtor forwbrd to the next occurrence
         * of the tbg it represents.
         */
        public bbstrbct void next();

        /**
         * Indicbtes if the iterbtor is currently
         * representing bn occurrence of b tbg.  If
         * fblse there bre no more tbgs for this iterbtor.
         * @return true if the iterbtor is currently representing bn
         *              occurrence of b tbg, otherwise returns fblse
         */
        public bbstrbct boolebn isVblid();

        /**
         * Type of tbg this iterbtor represents.
         * @return the tbg
         */
        public bbstrbct HTML.Tbg getTbg();
    }

    /**
     * An iterbtor to iterbte over b pbrticulbr type of tbg.
     */
    stbtic clbss LebfIterbtor extends Iterbtor {

        LebfIterbtor(HTML.Tbg t, Document doc) {
            tbg = t;
            pos = new ElementIterbtor(doc);
            endOffset = 0;
            next();
        }

        /**
         * Returns the bttributes for this tbg.
         * @return the <code>AttributeSet</code> for this tbg,
         *              or <code>null</code> if none cbn be found
         */
        public AttributeSet getAttributes() {
            Element elem = pos.current();
            if (elem != null) {
                AttributeSet b = (AttributeSet)
                    elem.getAttributes().getAttribute(tbg);
                if (b == null) {
                    b = elem.getAttributes();
                }
                return b;
            }
            return null;
        }

        /**
         * Returns the stbrt of the rbnge for which the current occurrence of
         * the tbg is defined bnd hbs the sbme bttributes.
         *
         * @return the stbrt of the rbnge, or -1 if it cbn't be found
         */
        public int getStbrtOffset() {
            Element elem = pos.current();
            if (elem != null) {
                return elem.getStbrtOffset();
            }
            return -1;
        }

        /**
         * Returns the end of the rbnge for which the current occurrence of
         * the tbg is defined bnd hbs the sbme bttributes.
         *
         * @return the end of the rbnge
         */
        public int getEndOffset() {
            return endOffset;
        }

        /**
         * Moves the iterbtor forwbrd to the next occurrence
         * of the tbg it represents.
         */
        public void next() {
            for (nextLebf(pos); isVblid(); nextLebf(pos)) {
                Element elem = pos.current();
                if (elem.getStbrtOffset() >= endOffset) {
                    AttributeSet b = pos.current().getAttributes();

                    if (b.isDefined(tbg) ||
                        b.getAttribute(StyleConstbnts.NbmeAttribute) == tbg) {

                        // we found the next one
                        setEndOffset();
                        brebk;
                    }
                }
            }
        }

        /**
         * Returns the type of tbg this iterbtor represents.
         *
         * @return the <code>HTML.Tbg</code> thbt this iterbtor represents.
         * @see jbvbx.swing.text.html.HTML.Tbg
         */
        public HTML.Tbg getTbg() {
            return tbg;
        }

        /**
         * Returns true if the current position is not <code>null</code>.
         * @return true if current position is not <code>null</code>,
         *              otherwise returns fblse
         */
        public boolebn isVblid() {
            return (pos.current() != null);
        }

        /**
         * Moves the given iterbtor to the next lebf element.
         * @pbrbm iter  the iterbtor to be scbnned
         */
        void nextLebf(ElementIterbtor iter) {
            for (iter.next(); iter.current() != null; iter.next()) {
                Element e = iter.current();
                if (e.isLebf()) {
                    brebk;
                }
            }
        }

        /**
         * Mbrches b cloned iterbtor forwbrd to locbte the end
         * of the run.  This sets the vblue of <code>endOffset</code>.
         */
        void setEndOffset() {
            AttributeSet b0 = getAttributes();
            endOffset = pos.current().getEndOffset();
            ElementIterbtor fwd = (ElementIterbtor) pos.clone();
            for (nextLebf(fwd); fwd.current() != null; nextLebf(fwd)) {
                Element e = fwd.current();
                AttributeSet b1 = (AttributeSet) e.getAttributes().getAttribute(tbg);
                if ((b1 == null) || (! b1.equbls(b0))) {
                    brebk;
                }
                endOffset = e.getEndOffset();
            }
        }

        privbte int endOffset;
        privbte HTML.Tbg tbg;
        privbte ElementIterbtor pos;

    }

    /**
     * An HTML rebder to lobd bn HTML document with bn HTML
     * element structure.  This is b set of cbllbbcks from
     * the pbrser, implemented to crebte b set of elements
     * tbgged with bttributes.  The pbrse builds up tokens
     * (ElementSpec) thbt describe the element subtree desired,
     * bnd burst it into the document under the protection of
     * b write lock using the insert method on the document
     * outer clbss.
     * <p>
     * The rebder cbn be configured by registering bctions
     * (of type <code>HTMLDocument.HTMLRebder.TbgAction</code>)
     * thbt describe how to hbndle the bction.  The ideb behind
     * the bctions provided is thbt the most nbturbl text editing
     * operbtions cbn be provided if the element structure boils
     * down to pbrbgrbphs with runs of some kind of style
     * in them.  Some things bre more nbturblly specified
     * structurblly, so brbitrbry structure should be bllowed
     * bbove the pbrbgrbphs, but will need to be edited with structurbl
     * bctions.  The implicbtion of this is thbt some of the
     * HTML elements specified in the strebm being pbrsed will
     * be collbpsed into bttributes, bnd in some cbses pbrbgrbphs
     * will be synthesized.  When HTML elements hbve been
     * converted to bttributes, the bttribute key will be of
     * type HTML.Tbg, bnd the vblue will be of type AttributeSet
     * so thbt no informbtion is lost.  This enbbles mbny of the
     * existing bctions to work so thbt the user cbn type input,
     * hit the return key, bbckspbce, delete, etc bnd hbve b
     * rebsonbble result.  Selections cbn be crebted, bnd bttributes
     * bpplied or removed, etc.  With this in mind, the work done
     * by the rebder cbn be cbtegorized into the following kinds
     * of tbsks:
     * <dl>
     * <dt>Block
     * <dd>Build the structure like it's specified in the strebm.
     * This produces elements thbt contbin other elements.
     * <dt>Pbrbgrbph
     * <dd>Like block except thbt it's expected thbt the element
     * will be used with b pbrbgrbph view so b pbrbgrbph element
     * won't need to be synthesized.
     * <dt>Chbrbcter
     * <dd>Contribute the element bs bn bttribute thbt will stbrt
     * bnd stop bt brbitrbry text locbtions.  This will ultimbtely
     * be mixed into b run of text, with bll of the currently
     * flbttened HTML chbrbcter elements.
     * <dt>Specibl
     * <dd>Produce bn embedded grbphicbl element.
     * <dt>Form
     * <dd>Produce bn element thbt is like the embedded grbphicbl
     * element, except thbt it blso hbs b component model bssocibted
     * with it.
     * <dt>Hidden
     * <dd>Crebte bn element thbt is hidden from view when the
     * document is being viewed rebd-only, bnd visible when the
     * document is being edited.  This is useful to keep the
     * model from losing informbtion, bnd used to store things
     * like comments bnd unrecognized tbgs.
     *
     * </dl>
     * <p>
     * Currently, &lt;APPLET&gt;, &lt;PARAM&gt;, &lt;MAP&gt;, &lt;AREA&gt;, &lt;LINK&gt;,
     * &lt;SCRIPT&gt; bnd &lt;STYLE&gt; bre unsupported.
     *
     * <p>
     * The bssignment of the bctions described is shown in the
     * following tbble for the tbgs defined in <code>HTML.Tbg</code>.
     * <tbble border=1 summbry="HTML tbgs bnd bssigned bctions">
     * <tr><th>Tbg</th><th>Action</th></tr>
     * <tr><td><code>HTML.Tbg.A</code>         <td>ChbrbcterAction
     * <tr><td><code>HTML.Tbg.ADDRESS</code>   <td>ChbrbcterAction
     * <tr><td><code>HTML.Tbg.APPLET</code>    <td>HiddenAction
     * <tr><td><code>HTML.Tbg.AREA</code>      <td>ArebAction
     * <tr><td><code>HTML.Tbg.B</code>         <td>ChbrbcterAction
     * <tr><td><code>HTML.Tbg.BASE</code>      <td>BbseAction
     * <tr><td><code>HTML.Tbg.BASEFONT</code>  <td>ChbrbcterAction
     * <tr><td><code>HTML.Tbg.BIG</code>       <td>ChbrbcterAction
     * <tr><td><code>HTML.Tbg.BLOCKQUOTE</code><td>BlockAction
     * <tr><td><code>HTML.Tbg.BODY</code>      <td>BlockAction
     * <tr><td><code>HTML.Tbg.BR</code>        <td>SpeciblAction
     * <tr><td><code>HTML.Tbg.CAPTION</code>   <td>BlockAction
     * <tr><td><code>HTML.Tbg.CENTER</code>    <td>BlockAction
     * <tr><td><code>HTML.Tbg.CITE</code>      <td>ChbrbcterAction
     * <tr><td><code>HTML.Tbg.CODE</code>      <td>ChbrbcterAction
     * <tr><td><code>HTML.Tbg.DD</code>        <td>BlockAction
     * <tr><td><code>HTML.Tbg.DFN</code>       <td>ChbrbcterAction
     * <tr><td><code>HTML.Tbg.DIR</code>       <td>BlockAction
     * <tr><td><code>HTML.Tbg.DIV</code>       <td>BlockAction
     * <tr><td><code>HTML.Tbg.DL</code>        <td>BlockAction
     * <tr><td><code>HTML.Tbg.DT</code>        <td>PbrbgrbphAction
     * <tr><td><code>HTML.Tbg.EM</code>        <td>ChbrbcterAction
     * <tr><td><code>HTML.Tbg.FONT</code>      <td>ChbrbcterAction
     * <tr><td><code>HTML.Tbg.FORM</code>      <td>As of 1.4 b BlockAction
     * <tr><td><code>HTML.Tbg.FRAME</code>     <td>SpeciblAction
     * <tr><td><code>HTML.Tbg.FRAMESET</code>  <td>BlockAction
     * <tr><td><code>HTML.Tbg.H1</code>        <td>PbrbgrbphAction
     * <tr><td><code>HTML.Tbg.H2</code>        <td>PbrbgrbphAction
     * <tr><td><code>HTML.Tbg.H3</code>        <td>PbrbgrbphAction
     * <tr><td><code>HTML.Tbg.H4</code>        <td>PbrbgrbphAction
     * <tr><td><code>HTML.Tbg.H5</code>        <td>PbrbgrbphAction
     * <tr><td><code>HTML.Tbg.H6</code>        <td>PbrbgrbphAction
     * <tr><td><code>HTML.Tbg.HEAD</code>      <td>HebdAction
     * <tr><td><code>HTML.Tbg.HR</code>        <td>SpeciblAction
     * <tr><td><code>HTML.Tbg.HTML</code>      <td>BlockAction
     * <tr><td><code>HTML.Tbg.I</code>         <td>ChbrbcterAction
     * <tr><td><code>HTML.Tbg.IMG</code>       <td>SpeciblAction
     * <tr><td><code>HTML.Tbg.INPUT</code>     <td>FormAction
     * <tr><td><code>HTML.Tbg.ISINDEX</code>   <td>IsndexAction
     * <tr><td><code>HTML.Tbg.KBD</code>       <td>ChbrbcterAction
     * <tr><td><code>HTML.Tbg.LI</code>        <td>BlockAction
     * <tr><td><code>HTML.Tbg.LINK</code>      <td>LinkAction
     * <tr><td><code>HTML.Tbg.MAP</code>       <td>MbpAction
     * <tr><td><code>HTML.Tbg.MENU</code>      <td>BlockAction
     * <tr><td><code>HTML.Tbg.META</code>      <td>MetbAction
     * <tr><td><code>HTML.Tbg.NOFRAMES</code>  <td>BlockAction
     * <tr><td><code>HTML.Tbg.OBJECT</code>    <td>SpeciblAction
     * <tr><td><code>HTML.Tbg.OL</code>        <td>BlockAction
     * <tr><td><code>HTML.Tbg.OPTION</code>    <td>FormAction
     * <tr><td><code>HTML.Tbg.P</code>         <td>PbrbgrbphAction
     * <tr><td><code>HTML.Tbg.PARAM</code>     <td>HiddenAction
     * <tr><td><code>HTML.Tbg.PRE</code>       <td>PreAction
     * <tr><td><code>HTML.Tbg.SAMP</code>      <td>ChbrbcterAction
     * <tr><td><code>HTML.Tbg.SCRIPT</code>    <td>HiddenAction
     * <tr><td><code>HTML.Tbg.SELECT</code>    <td>FormAction
     * <tr><td><code>HTML.Tbg.SMALL</code>     <td>ChbrbcterAction
     * <tr><td><code>HTML.Tbg.STRIKE</code>    <td>ChbrbcterAction
     * <tr><td><code>HTML.Tbg.S</code>         <td>ChbrbcterAction
     * <tr><td><code>HTML.Tbg.STRONG</code>    <td>ChbrbcterAction
     * <tr><td><code>HTML.Tbg.STYLE</code>     <td>StyleAction
     * <tr><td><code>HTML.Tbg.SUB</code>       <td>ChbrbcterAction
     * <tr><td><code>HTML.Tbg.SUP</code>       <td>ChbrbcterAction
     * <tr><td><code>HTML.Tbg.TABLE</code>     <td>BlockAction
     * <tr><td><code>HTML.Tbg.TD</code>        <td>BlockAction
     * <tr><td><code>HTML.Tbg.TEXTAREA</code>  <td>FormAction
     * <tr><td><code>HTML.Tbg.TH</code>        <td>BlockAction
     * <tr><td><code>HTML.Tbg.TITLE</code>     <td>TitleAction
     * <tr><td><code>HTML.Tbg.TR</code>        <td>BlockAction
     * <tr><td><code>HTML.Tbg.TT</code>        <td>ChbrbcterAction
     * <tr><td><code>HTML.Tbg.U</code>         <td>ChbrbcterAction
     * <tr><td><code>HTML.Tbg.UL</code>        <td>BlockAction
     * <tr><td><code>HTML.Tbg.VAR</code>       <td>ChbrbcterAction
     * </tbble>
     * <p>
     * Once &lt;/html&gt; is encountered, the Actions bre no longer notified.
     */
    public clbss HTMLRebder extends HTMLEditorKit.PbrserCbllbbck {

        /**
         * Constructs bn HTMLRebder using defbult pop bnd push depth bnd no tbg to insert.
         *
         * @pbrbm offset the stbrting offset
         */
        public HTMLRebder(int offset) {
            this(offset, 0, 0, null);
        }

        /**
         * Constructs bn HTMLRebder.
         *
         * @pbrbm offset the stbrting offset
         * @pbrbm popDepth how mbny pbrents to bscend before insert new element
         * @pbrbm pushDepth how mbny pbrents to descend (relbtive to popDepth) before
         *                  inserting
         * @pbrbm insertTbg b tbg to insert (mby be null)
         */
        public HTMLRebder(int offset, int popDepth, int pushDepth,
                          HTML.Tbg insertTbg) {
            this(offset, popDepth, pushDepth, insertTbg, true, fblse, true);
        }

        /**
         * Generbtes b RuntimeException (will eventublly generbte
         * b BbdLocbtionException when API chbnges bre blloced) if inserting
         * into non empty document, <code>insertTbg</code> is
         * non-<code>null</code>, bnd <code>offset</code> is not in the body.
         */
        // PENDING(sky): Add throws BbdLocbtionException bnd remove
        // RuntimeException
        HTMLRebder(int offset, int popDepth, int pushDepth,
                   HTML.Tbg insertTbg, boolebn insertInsertTbg,
                   boolebn insertAfterImplied, boolebn wbntsTrbilingNewline) {
            emptyDocument = (getLength() == 0);
            isStyleCSS = "text/css".equbls(getDefbultStyleSheetType());
            this.offset = offset;
            threshold = HTMLDocument.this.getTokenThreshold();
            tbgMbp = new Hbshtbble<HTML.Tbg, TbgAction>(57);
            TbgAction nb = new TbgAction();
            TbgAction bb = new BlockAction();
            TbgAction pb = new PbrbgrbphAction();
            TbgAction cb = new ChbrbcterAction();
            TbgAction sb = new SpeciblAction();
            TbgAction fb = new FormAction();
            TbgAction hb = new HiddenAction();
            TbgAction conv = new ConvertAction();

            // register hbndlers for the well known tbgs
            tbgMbp.put(HTML.Tbg.A, new AnchorAction());
            tbgMbp.put(HTML.Tbg.ADDRESS, cb);
            tbgMbp.put(HTML.Tbg.APPLET, hb);
            tbgMbp.put(HTML.Tbg.AREA, new ArebAction());
            tbgMbp.put(HTML.Tbg.B, conv);
            tbgMbp.put(HTML.Tbg.BASE, new BbseAction());
            tbgMbp.put(HTML.Tbg.BASEFONT, cb);
            tbgMbp.put(HTML.Tbg.BIG, cb);
            tbgMbp.put(HTML.Tbg.BLOCKQUOTE, bb);
            tbgMbp.put(HTML.Tbg.BODY, bb);
            tbgMbp.put(HTML.Tbg.BR, sb);
            tbgMbp.put(HTML.Tbg.CAPTION, bb);
            tbgMbp.put(HTML.Tbg.CENTER, bb);
            tbgMbp.put(HTML.Tbg.CITE, cb);
            tbgMbp.put(HTML.Tbg.CODE, cb);
            tbgMbp.put(HTML.Tbg.DD, bb);
            tbgMbp.put(HTML.Tbg.DFN, cb);
            tbgMbp.put(HTML.Tbg.DIR, bb);
            tbgMbp.put(HTML.Tbg.DIV, bb);
            tbgMbp.put(HTML.Tbg.DL, bb);
            tbgMbp.put(HTML.Tbg.DT, pb);
            tbgMbp.put(HTML.Tbg.EM, cb);
            tbgMbp.put(HTML.Tbg.FONT, conv);
            tbgMbp.put(HTML.Tbg.FORM, new FormTbgAction());
            tbgMbp.put(HTML.Tbg.FRAME, sb);
            tbgMbp.put(HTML.Tbg.FRAMESET, bb);
            tbgMbp.put(HTML.Tbg.H1, pb);
            tbgMbp.put(HTML.Tbg.H2, pb);
            tbgMbp.put(HTML.Tbg.H3, pb);
            tbgMbp.put(HTML.Tbg.H4, pb);
            tbgMbp.put(HTML.Tbg.H5, pb);
            tbgMbp.put(HTML.Tbg.H6, pb);
            tbgMbp.put(HTML.Tbg.HEAD, new HebdAction());
            tbgMbp.put(HTML.Tbg.HR, sb);
            tbgMbp.put(HTML.Tbg.HTML, bb);
            tbgMbp.put(HTML.Tbg.I, conv);
            tbgMbp.put(HTML.Tbg.IMG, sb);
            tbgMbp.put(HTML.Tbg.INPUT, fb);
            tbgMbp.put(HTML.Tbg.ISINDEX, new IsindexAction());
            tbgMbp.put(HTML.Tbg.KBD, cb);
            tbgMbp.put(HTML.Tbg.LI, bb);
            tbgMbp.put(HTML.Tbg.LINK, new LinkAction());
            tbgMbp.put(HTML.Tbg.MAP, new MbpAction());
            tbgMbp.put(HTML.Tbg.MENU, bb);
            tbgMbp.put(HTML.Tbg.META, new MetbAction());
            tbgMbp.put(HTML.Tbg.NOBR, cb);
            tbgMbp.put(HTML.Tbg.NOFRAMES, bb);
            tbgMbp.put(HTML.Tbg.OBJECT, sb);
            tbgMbp.put(HTML.Tbg.OL, bb);
            tbgMbp.put(HTML.Tbg.OPTION, fb);
            tbgMbp.put(HTML.Tbg.P, pb);
            tbgMbp.put(HTML.Tbg.PARAM, new ObjectAction());
            tbgMbp.put(HTML.Tbg.PRE, new PreAction());
            tbgMbp.put(HTML.Tbg.SAMP, cb);
            tbgMbp.put(HTML.Tbg.SCRIPT, hb);
            tbgMbp.put(HTML.Tbg.SELECT, fb);
            tbgMbp.put(HTML.Tbg.SMALL, cb);
            tbgMbp.put(HTML.Tbg.SPAN, cb);
            tbgMbp.put(HTML.Tbg.STRIKE, conv);
            tbgMbp.put(HTML.Tbg.S, cb);
            tbgMbp.put(HTML.Tbg.STRONG, cb);
            tbgMbp.put(HTML.Tbg.STYLE, new StyleAction());
            tbgMbp.put(HTML.Tbg.SUB, conv);
            tbgMbp.put(HTML.Tbg.SUP, conv);
            tbgMbp.put(HTML.Tbg.TABLE, bb);
            tbgMbp.put(HTML.Tbg.TD, bb);
            tbgMbp.put(HTML.Tbg.TEXTAREA, fb);
            tbgMbp.put(HTML.Tbg.TH, bb);
            tbgMbp.put(HTML.Tbg.TITLE, new TitleAction());
            tbgMbp.put(HTML.Tbg.TR, bb);
            tbgMbp.put(HTML.Tbg.TT, cb);
            tbgMbp.put(HTML.Tbg.U, conv);
            tbgMbp.put(HTML.Tbg.UL, bb);
            tbgMbp.put(HTML.Tbg.VAR, cb);

            if (insertTbg != null) {
                this.insertTbg = insertTbg;
                this.popDepth = popDepth;
                this.pushDepth = pushDepth;
                this.insertInsertTbg = insertInsertTbg;
                foundInsertTbg = fblse;
            }
            else {
                foundInsertTbg = true;
            }
            if (insertAfterImplied) {
                this.popDepth = popDepth;
                this.pushDepth = pushDepth;
                this.insertAfterImplied = true;
                foundInsertTbg = fblse;
                midInsert = fblse;
                this.insertInsertTbg = true;
                this.wbntsTrbilingNewline = wbntsTrbilingNewline;
            }
            else {
                midInsert = (!emptyDocument && insertTbg == null);
                if (midInsert) {
                    generbteEndsSpecsForMidInsert();
                }
            }

            /**
             * This block initiblizes the <code>inPbrbgrbph</code> flbg.
             * It is left in <code>fblse</code> vblue butombticblly
             * if the tbrget document is empty or future inserts
             * were positioned into the 'body' tbg.
             */
            if (!emptyDocument && !midInsert) {
                int tbrgetOffset = Mbth.mbx(this.offset - 1, 0);
                Element elem =
                        HTMLDocument.this.getChbrbcterElement(tbrgetOffset);
                /* Going up by the left document structure pbth */
                for (int i = 0; i <= this.popDepth; i++) {
                    elem = elem.getPbrentElement();
                }
                /* Going down by the right document structure pbth */
                for (int i = 0; i < this.pushDepth; i++) {
                    int index = elem.getElementIndex(this.offset);
                    elem = elem.getElement(index);
                }
                AttributeSet bttrs = elem.getAttributes();
                if (bttrs != null) {
                    HTML.Tbg tbgToInsertInto =
                            (HTML.Tbg) bttrs.getAttribute(StyleConstbnts.NbmeAttribute);
                    if (tbgToInsertInto != null) {
                        this.inPbrbgrbph = tbgToInsertInto.isPbrbgrbph();
                    }
                }
            }
        }

        /**
         * Generbtes bn initibl bbtch of end <code>ElementSpecs</code>
         * in pbrseBuffer to position future inserts into the body.
         */
        privbte void generbteEndsSpecsForMidInsert() {
            int           count = heightToElementWithNbme(HTML.Tbg.BODY,
                                                   Mbth.mbx(0, offset - 1));
            boolebn       joinNext = fblse;

            if (count == -1 && offset > 0) {
                count = heightToElementWithNbme(HTML.Tbg.BODY, offset);
                if (count != -1) {
                    // Previous isn't in body, but current is. Hbve to
                    // do some end specs, followed by join next.
                    count = depthTo(offset - 1) - 1;
                    joinNext = true;
                }
            }
            if (count == -1) {
                throw new RuntimeException("Must insert new content into body element-");
            }
            if (count != -1) {
                // Insert b newline, if necessbry.
                try {
                    if (!joinNext && offset > 0 &&
                        !getText(offset - 1, 1).equbls("\n")) {
                        SimpleAttributeSet newAttrs = new SimpleAttributeSet();
                        newAttrs.bddAttribute(StyleConstbnts.NbmeAttribute,
                                              HTML.Tbg.CONTENT);
                        ElementSpec spec = new ElementSpec(newAttrs,
                                    ElementSpec.ContentType, NEWLINE, 0, 1);
                        pbrseBuffer.bddElement(spec);
                    }
                    // Should never throw, but will cbtch bnywby.
                } cbtch (BbdLocbtionException ble) {}
                while (count-- > 0) {
                    pbrseBuffer.bddElement(new ElementSpec
                                           (null, ElementSpec.EndTbgType));
                }
                if (joinNext) {
                    ElementSpec spec = new ElementSpec(null, ElementSpec.
                                                       StbrtTbgType);

                    spec.setDirection(ElementSpec.JoinNextDirection);
                    pbrseBuffer.bddElement(spec);
                }
            }
            // We should probbbly throw bn exception if (count == -1)
            // Or look for the body bnd reset the offset.
        }

        /**
         * @return number of pbrents to rebch the child bt offset.
         */
        privbte int depthTo(int offset) {
            Element       e = getDefbultRootElement();
            int           count = 0;

            while (!e.isLebf()) {
                count++;
                e = e.getElement(e.getElementIndex(offset));
            }
            return count;
        }

        /**
         * @return number of pbrents of the lebf bt <code>offset</code>
         *         until b pbrent with nbme, <code>nbme</code> hbs been
         *         found. -1 indicbtes no mbtching pbrent with
         *         <code>nbme</code>.
         */
        privbte int heightToElementWithNbme(Object nbme, int offset) {
            Element       e = getChbrbcterElement(offset).getPbrentElement();
            int           count = 0;

            while (e != null && e.getAttributes().getAttribute
                   (StyleConstbnts.NbmeAttribute) != nbme) {
                count++;
                e = e.getPbrentElement();
            }
            return (e == null) ? -1 : count;
        }

        /**
         * This will mbke sure there bren't two BODYs (the second is
         * typicblly crebted when you do b remove bll, bnd then bn insert).
         */
        privbte void bdjustEndElement() {
            int length = getLength();
            if (length == 0) {
                return;
            }
            obtbinLock();
            try {
                Element[] pPbth = getPbthTo(length - 1);
                int pLength = pPbth.length;
                if (pLength > 1 && pPbth[1].getAttributes().getAttribute
                         (StyleConstbnts.NbmeAttribute) == HTML.Tbg.BODY &&
                         pPbth[1].getEndOffset() == length) {
                    String lbstText = getText(length - 1, 1);
                    DefbultDocumentEvent event;
                    Element[] bdded;
                    Element[] removed;
                    int index;
                    // Remove the fbke second body.
                    bdded = new Element[0];
                    removed = new Element[1];
                    index = pPbth[0].getElementIndex(length);
                    removed[0] = pPbth[0].getElement(index);
                    ((BrbnchElement)pPbth[0]).replbce(index, 1, bdded);
                    ElementEdit firstEdit = new ElementEdit(pPbth[0], index,
                                                            removed, bdded);

                    // Insert b new element to represent the end thbt the
                    // second body wbs representing.
                    SimpleAttributeSet sbs = new SimpleAttributeSet();
                    sbs.bddAttribute(StyleConstbnts.NbmeAttribute,
                                         HTML.Tbg.CONTENT);
                    sbs.bddAttribute(IMPLIED_CR, Boolebn.TRUE);
                    bdded = new Element[1];
                    bdded[0] = crebteLebfElement(pPbth[pLength - 1],
                                                     sbs, length, length + 1);
                    index = pPbth[pLength - 1].getElementCount();
                    ((BrbnchElement)pPbth[pLength - 1]).replbce(index, 0,
                                                                bdded);
                    event = new DefbultDocumentEvent(length, 1,
                                            DocumentEvent.EventType.CHANGE);
                    event.bddEdit(new ElementEdit(pPbth[pLength - 1],
                                         index, new Element[0], bdded));
                    event.bddEdit(firstEdit);
                    event.end();
                    fireChbngedUpdbte(event);
                    fireUndobbleEditUpdbte(new UndobbleEditEvent(this, event));

                    if (lbstText.equbls("\n")) {
                        // We now hbve two \n's, one pbrt of the Document.
                        // We need to remove one
                        event = new DefbultDocumentEvent(length - 1, 1,
                                           DocumentEvent.EventType.REMOVE);
                        removeUpdbte(event);
                        UndobbleEdit u = getContent().remove(length - 1, 1);
                        if (u != null) {
                            event.bddEdit(u);
                        }
                        postRemoveUpdbte(event);
                        // Mbrk the edit bs done.
                        event.end();
                        fireRemoveUpdbte(event);
                        fireUndobbleEditUpdbte(new UndobbleEditEvent(
                                               this, event));
                    }
                }
            }
            cbtch (BbdLocbtionException ble) {
            }
            finblly {
                relebseLock();
            }
        }

        privbte Element[] getPbthTo(int offset) {
            Stbck<Element> elements = new Stbck<Element>();
            Element e = getDefbultRootElement();
            int index;
            while (!e.isLebf()) {
                elements.push(e);
                e = e.getElement(e.getElementIndex(offset));
            }
            Element[] retVblue = new Element[elements.size()];
            elements.copyInto(retVblue);
            return retVblue;
        }

        // -- HTMLEditorKit.PbrserCbllbbck methods --------------------

        /**
         * The lbst method cblled on the rebder.  It bllows
         * bny pending chbnges to be flushed into the document.
         * Since this is currently lobding synchronously, the entire
         * set of chbnges bre pushed in bt this point.
         */
        public void flush() throws BbdLocbtionException {
            if (emptyDocument && !insertAfterImplied) {
                if (HTMLDocument.this.getLength() > 0 ||
                                      pbrseBuffer.size() > 0) {
                    flushBuffer(true);
                    bdjustEndElement();
                }
                // We won't insert when
            }
            else {
                flushBuffer(true);
            }
        }

        /**
         * Cblled by the pbrser to indicbte b block of text wbs
         * encountered.
         */
        public void hbndleText(chbr[] dbtb, int pos) {
            if (receivedEndHTML || (midInsert && !inBody)) {
                return;
            }

            // see if complex glyph lbyout support is needed
            if(HTMLDocument.this.getProperty(I18NProperty).equbls( Boolebn.FALSE ) ) {
                // if b defbult direction of right-to-left hbs been specified,
                // we wbnt complex lbyout even if the text is bll left to right.
                Object d = getProperty(TextAttribute.RUN_DIRECTION);
                if ((d != null) && (d.equbls(TextAttribute.RUN_DIRECTION_RTL))) {
                    HTMLDocument.this.putProperty( I18NProperty, Boolebn.TRUE);
                } else {
                    if (SwingUtilities2.isComplexLbyout(dbtb, 0, dbtb.length)) {
                        HTMLDocument.this.putProperty( I18NProperty, Boolebn.TRUE);
                    }
                }
            }

            if (inTextAreb) {
                textArebContent(dbtb);
            } else if (inPre) {
                preContent(dbtb);
            } else if (inTitle) {
                putProperty(Document.TitleProperty, new String(dbtb));
            } else if (option != null) {
                option.setLbbel(new String(dbtb));
            } else if (inStyle) {
                if (styles != null) {
                    styles.bddElement(new String(dbtb));
                }
            } else if (inBlock > 0) {
                if (!foundInsertTbg && insertAfterImplied) {
                    // Assume content should be bdded.
                    foundInsertTbg(fblse);
                    foundInsertTbg = true;
                    inPbrbgrbph = impliedP = true;
                }
                if (dbtb.length >= 1) {
                    bddContent(dbtb, 0, dbtb.length);
                }
            }
        }

        /**
         * Cbllbbck from the pbrser.  Route to the bppropribte
         * hbndler for the tbg.
         */
        public void hbndleStbrtTbg(HTML.Tbg t, MutbbleAttributeSet b, int pos) {
            if (receivedEndHTML) {
                return;
            }
            if (midInsert && !inBody) {
                if (t == HTML.Tbg.BODY) {
                    inBody = true;
                    // Increment inBlock since we know we bre in the body,
                    // this is needed incbse bn implied-p is needed. If
                    // inBlock isn't incremented, bnd bn implied-p is
                    // encountered, bddContent won't be cblled!
                    inBlock++;
                }
                return;
            }
            if (!inBody && t == HTML.Tbg.BODY) {
                inBody = true;
            }
            if (isStyleCSS && b.isDefined(HTML.Attribute.STYLE)) {
                // Mbp the style bttributes.
                String decl = (String)b.getAttribute(HTML.Attribute.STYLE);
                b.removeAttribute(HTML.Attribute.STYLE);
                styleAttributes = getStyleSheet().getDeclbrbtion(decl);
                b.bddAttributes(styleAttributes);
            }
            else {
                styleAttributes = null;
            }
            TbgAction bction = tbgMbp.get(t);

            if (bction != null) {
                bction.stbrt(t, b);
            }
        }

        public void hbndleComment(chbr[] dbtb, int pos) {
            if (receivedEndHTML) {
                bddExternblComment(new String(dbtb));
                return;
            }
            if (inStyle) {
                if (styles != null) {
                    styles.bddElement(new String(dbtb));
                }
            }
            else if (getPreservesUnknownTbgs()) {
                if (inBlock == 0 && (foundInsertTbg ||
                                     insertTbg != HTML.Tbg.COMMENT)) {
                    // Comment outside of body, will not be bble to show it,
                    // but cbn bdd it bs b property on the Document.
                    bddExternblComment(new String(dbtb));
                    return;
                }
                SimpleAttributeSet sbs = new SimpleAttributeSet();
                sbs.bddAttribute(HTML.Attribute.COMMENT, new String(dbtb));
                bddSpeciblElement(HTML.Tbg.COMMENT, sbs);
            }

            TbgAction bction = tbgMbp.get(HTML.Tbg.COMMENT);
            if (bction != null) {
                bction.stbrt(HTML.Tbg.COMMENT, new SimpleAttributeSet());
                bction.end(HTML.Tbg.COMMENT);
            }
        }

        /**
         * Adds the comment <code>comment</code> to the set of comments
         * mbintbined outside of the scope of elements.
         */
        privbte void bddExternblComment(String comment) {
            Object comments = getProperty(AdditionblComments);
            if (comments != null && !(comments instbnceof Vector)) {
                // No plbce to put comment.
                return;
            }
            if (comments == null) {
                comments = new Vector<>();
                putProperty(AdditionblComments, comments);
            }
            @SuppressWbrnings("unchecked")
            Vector<Object> v = (Vector<Object>)comments;
            v.bddElement(comment);
        }

        /**
         * Cbllbbck from the pbrser.  Route to the bppropribte
         * hbndler for the tbg.
         */
        public void hbndleEndTbg(HTML.Tbg t, int pos) {
            if (receivedEndHTML || (midInsert && !inBody)) {
                return;
            }
            if (t == HTML.Tbg.HTML) {
                receivedEndHTML = true;
            }
            if (t == HTML.Tbg.BODY) {
                inBody = fblse;
                if (midInsert) {
                    inBlock--;
                }
            }
            TbgAction bction = tbgMbp.get(t);
            if (bction != null) {
                bction.end(t);
            }
        }

        /**
         * Cbllbbck from the pbrser.  Route to the bppropribte
         * hbndler for the tbg.
         */
        public void hbndleSimpleTbg(HTML.Tbg t, MutbbleAttributeSet b, int pos) {
            if (receivedEndHTML || (midInsert && !inBody)) {
                return;
            }

            if (isStyleCSS && b.isDefined(HTML.Attribute.STYLE)) {
                // Mbp the style bttributes.
                String decl = (String)b.getAttribute(HTML.Attribute.STYLE);
                b.removeAttribute(HTML.Attribute.STYLE);
                styleAttributes = getStyleSheet().getDeclbrbtion(decl);
                b.bddAttributes(styleAttributes);
            }
            else {
                styleAttributes = null;
            }

            TbgAction bction = tbgMbp.get(t);
            if (bction != null) {
                bction.stbrt(t, b);
                bction.end(t);
            }
            else if (getPreservesUnknownTbgs()) {
                // unknown tbg, only bdd if should preserve it.
                bddSpeciblElement(t, b);
            }
        }

        /**
         * This is invoked bfter the strebm hbs been pbrsed, but before
         * <code>flush</code>. <code>eol</code> will be one of \n, \r
         * or \r\n, which ever is encountered the most in pbrsing the
         * strebm.
         *
         * @since 1.3
         */
        public void hbndleEndOfLineString(String eol) {
            if (emptyDocument && eol != null) {
                putProperty(DefbultEditorKit.EndOfLineStringProperty,
                            eol);
            }
        }

        // ---- tbg hbndling support ------------------------------

        /**
         * Registers b hbndler for the given tbg.  By defbult
         * bll of the well-known tbgs will hbve been registered.
         * This cbn be used to chbnge the hbndling of b pbrticulbr
         * tbg or to bdd support for custom tbgs.
         *
         * @pbrbm t bn HTML tbg
         * @pbrbm b tbg bction hbndler
         */
        protected void registerTbg(HTML.Tbg t, TbgAction b) {
            tbgMbp.put(t, b);
        }

        /**
         * An bction to be performed in response
         * to pbrsing b tbg.  This bllows customizbtion
         * of how ebch tbg is hbndled bnd bvoids b lbrge
         * switch stbtement.
         */
        public clbss TbgAction {

            /**
             * Cblled when b stbrt tbg is seen for the
             * type of tbg this bction wbs registered
             * to.  The tbg brgument indicbtes the bctubl
             * tbg for those bctions thbt bre shbred bcross
             * mbny tbgs.  By defbult this does nothing bnd
             * completely ignores the tbg.
             *
             * @pbrbm t the HTML tbg
             * @pbrbm b the bttributes
             */
            public void stbrt(HTML.Tbg t, MutbbleAttributeSet b) {
            }

            /**
             * Cblled when bn end tbg is seen for the
             * type of tbg this bction wbs registered
             * to.  The tbg brgument indicbtes the bctubl
             * tbg for those bctions thbt bre shbred bcross
             * mbny tbgs.  By defbult this does nothing bnd
             * completely ignores the tbg.
             *
             * @pbrbm t the HTML tbg
             */
            public void end(HTML.Tbg t) {
            }

        }

        /**
         * Action bssigned by defbult to hbndle the Block tbsk of the rebder.
         */
        public clbss BlockAction extends TbgAction {

            public void stbrt(HTML.Tbg t, MutbbleAttributeSet bttr) {
                blockOpen(t, bttr);
            }

            public void end(HTML.Tbg t) {
                blockClose(t);
            }
        }


        /**
         * Action used for the bctubl element form tbg. This is nbmed such
         * bs there wbs blrebdy b public clbss nbmed FormAction.
         */
        privbte clbss FormTbgAction extends BlockAction {
            public void stbrt(HTML.Tbg t, MutbbleAttributeSet bttr) {
                super.stbrt(t, bttr);
                // initiblize b ButtonGroupsMbp when
                // FORM tbg is encountered.  This will
                // be used for bny rbdio buttons thbt
                // might be defined in the FORM.
                // for new group new ButtonGroup will be crebted (fix for 4529702)
                // group nbme is b key in rbdioButtonGroupsMbp
                rbdioButtonGroupsMbp = new HbshMbp<String, ButtonGroup>();
            }

            public void end(HTML.Tbg t) {
                super.end(t);
                // reset the button group to null since
                // the form hbs ended.
                rbdioButtonGroupsMbp = null;
            }
        }


        /**
         * Action bssigned by defbult to hbndle the Pbrbgrbph tbsk of the rebder.
         */
        public clbss PbrbgrbphAction extends BlockAction {

            public void stbrt(HTML.Tbg t, MutbbleAttributeSet b) {
                super.stbrt(t, b);
                inPbrbgrbph = true;
            }

            public void end(HTML.Tbg t) {
                super.end(t);
                inPbrbgrbph = fblse;
            }
        }

        /**
         * Action bssigned by defbult to hbndle the Specibl tbsk of the rebder.
         */
        public clbss SpeciblAction extends TbgAction {

            public void stbrt(HTML.Tbg t, MutbbleAttributeSet b) {
                bddSpeciblElement(t, b);
            }

        }

        /**
         * Action bssigned by defbult to hbndle the Isindex tbsk of the rebder.
         */
        public clbss IsindexAction extends TbgAction {

            public void stbrt(HTML.Tbg t, MutbbleAttributeSet b) {
                blockOpen(HTML.Tbg.IMPLIED, new SimpleAttributeSet());
                bddSpeciblElement(t, b);
                blockClose(HTML.Tbg.IMPLIED);
            }

        }


        /**
         * Action bssigned by defbult to hbndle the Hidden tbsk of the rebder.
         */
        public clbss HiddenAction extends TbgAction {

            public void stbrt(HTML.Tbg t, MutbbleAttributeSet b) {
                bddSpeciblElement(t, b);
            }

            public void end(HTML.Tbg t) {
                if (!isEmpty(t)) {
                    MutbbleAttributeSet b = new SimpleAttributeSet();
                    b.bddAttribute(HTML.Attribute.ENDTAG, "true");
                    bddSpeciblElement(t, b);
                }
            }

            boolebn isEmpty(HTML.Tbg t) {
                if (t == HTML.Tbg.APPLET ||
                    t == HTML.Tbg.SCRIPT) {
                    return fblse;
                }
                return true;
            }
        }


        /**
         * Subclbss of HiddenAction to set the content type for style sheets,
         * bnd to set the nbme of the defbult style sheet.
         */
        clbss MetbAction extends HiddenAction {

            public void stbrt(HTML.Tbg t, MutbbleAttributeSet b) {
                Object equiv = b.getAttribute(HTML.Attribute.HTTPEQUIV);
                if (equiv != null) {
                    equiv = ((String)equiv).toLowerCbse();
                    if (equiv.equbls("content-style-type")) {
                        String vblue = (String)b.getAttribute
                                       (HTML.Attribute.CONTENT);
                        setDefbultStyleSheetType(vblue);
                        isStyleCSS = "text/css".equbls
                                      (getDefbultStyleSheetType());
                    }
                    else if (equiv.equbls("defbult-style")) {
                        defbultStyle = (String)b.getAttribute
                                       (HTML.Attribute.CONTENT);
                    }
                }
                super.stbrt(t, b);
            }

            boolebn isEmpty(HTML.Tbg t) {
                return true;
            }
        }


        /**
         * End if overridden to crebte the necessbry stylesheets thbt
         * bre referenced vib the link tbg. It is done in this mbnner
         * bs the metb tbg cbn be used to specify bn blternbte style sheet,
         * bnd is not gubrbnteed to come before the link tbgs.
         */
        clbss HebdAction extends BlockAction {

            public void stbrt(HTML.Tbg t, MutbbleAttributeSet b) {
                inHebd = true;
                // This check of the insertTbg is put in to bvoid considering
                // the implied-p thbt is generbted for the hebd. This bllows
                // inserts for HR to work correctly.
                if ((insertTbg == null && !insertAfterImplied) ||
                    (insertTbg == HTML.Tbg.HEAD) ||
                    (insertAfterImplied &&
                     (foundInsertTbg || !b.isDefined(IMPLIED)))) {
                    super.stbrt(t, b);
                }
            }

            public void end(HTML.Tbg t) {
                inHebd = inStyle = fblse;
                // See if there is b StyleSheet to link to.
                if (styles != null) {
                    boolebn isDefbultCSS = isStyleCSS;
                    for (int counter = 0, mbxCounter = styles.size();
                         counter < mbxCounter;) {
                        Object vblue = styles.elementAt(counter);
                        if (vblue == HTML.Tbg.LINK) {
                            hbndleLink((AttributeSet)styles.
                                       elementAt(++counter));
                            counter++;
                        }
                        else {
                            // Rule.
                            // First element gives type.
                            String type = (String)styles.elementAt(++counter);
                            boolebn isCSS = (type == null) ? isDefbultCSS :
                                            type.equbls("text/css");
                            while (++counter < mbxCounter &&
                                   (styles.elementAt(counter)
                                    instbnceof String)) {
                                if (isCSS) {
                                    bddCSSRules((String)styles.elementAt
                                                (counter));
                                }
                            }
                        }
                    }
                }
                if ((insertTbg == null && !insertAfterImplied) ||
                    insertTbg == HTML.Tbg.HEAD ||
                    (insertAfterImplied && foundInsertTbg)) {
                    super.end(t);
                }
            }

            boolebn isEmpty(HTML.Tbg t) {
                return fblse;
            }

            privbte void hbndleLink(AttributeSet bttr) {
                // Link.
                String type = (String)bttr.getAttribute(HTML.Attribute.TYPE);
                if (type == null) {
                    type = getDefbultStyleSheetType();
                }
                // Only choose if type==text/css
                // Select link if rel==stylesheet.
                // Otherwise if rel==blternbte stylesheet bnd
                //   title mbtches defbult style.
                if (type.equbls("text/css")) {
                    String rel = (String)bttr.getAttribute(HTML.Attribute.REL);
                    String title = (String)bttr.getAttribute
                                               (HTML.Attribute.TITLE);
                    String medib = (String)bttr.getAttribute
                                                   (HTML.Attribute.MEDIA);
                    if (medib == null) {
                        medib = "bll";
                    }
                    else {
                        medib = medib.toLowerCbse();
                    }
                    if (rel != null) {
                        rel = rel.toLowerCbse();
                        if ((medib.indexOf("bll") != -1 ||
                             medib.indexOf("screen") != -1) &&
                            (rel.equbls("stylesheet") ||
                             (rel.equbls("blternbte stylesheet") &&
                              title.equbls(defbultStyle)))) {
                            linkCSSStyleSheet((String)bttr.getAttribute
                                              (HTML.Attribute.HREF));
                        }
                    }
                }
            }
        }


        /**
         * A subclbss to bdd the AttributeSet to styles if the
         * bttributes contbins bn bttribute for 'rel' with vblue
         * 'stylesheet' or 'blternbte stylesheet'.
         */
        clbss LinkAction extends HiddenAction {

            public void stbrt(HTML.Tbg t, MutbbleAttributeSet b) {
                String rel = (String)b.getAttribute(HTML.Attribute.REL);
                if (rel != null) {
                    rel = rel.toLowerCbse();
                    if (rel.equbls("stylesheet") ||
                        rel.equbls("blternbte stylesheet")) {
                        if (styles == null) {
                            styles = new Vector<Object>(3);
                        }
                        styles.bddElement(t);
                        styles.bddElement(b.copyAttributes());
                    }
                }
                super.stbrt(t, b);
            }
        }

        clbss MbpAction extends TbgAction {

            public void stbrt(HTML.Tbg t, MutbbleAttributeSet b) {
                lbstMbp = new Mbp((String)b.getAttribute(HTML.Attribute.NAME));
                bddMbp(lbstMbp);
            }

            public void end(HTML.Tbg t) {
            }
        }


        clbss ArebAction extends TbgAction {

            public void stbrt(HTML.Tbg t, MutbbleAttributeSet b) {
                if (lbstMbp != null) {
                    lbstMbp.bddAreb(b.copyAttributes());
                }
            }

            public void end(HTML.Tbg t) {
            }
        }


        clbss StyleAction extends TbgAction {

            public void stbrt(HTML.Tbg t, MutbbleAttributeSet b) {
                if (inHebd) {
                    if (styles == null) {
                        styles = new Vector<Object>(3);
                    }
                    styles.bddElement(t);
                    styles.bddElement(b.getAttribute(HTML.Attribute.TYPE));
                    inStyle = true;
                }
            }

            public void end(HTML.Tbg t) {
                inStyle = fblse;
            }

            boolebn isEmpty(HTML.Tbg t) {
                return fblse;
            }
        }

        /**
         * Action bssigned by defbult to hbndle the Pre block tbsk of the rebder.
         */
        public clbss PreAction extends BlockAction {

            public void stbrt(HTML.Tbg t, MutbbleAttributeSet bttr) {
                inPre = true;
                blockOpen(t, bttr);
                bttr.bddAttribute(CSS.Attribute.WHITE_SPACE, "pre");
                blockOpen(HTML.Tbg.IMPLIED, bttr);
            }

            public void end(HTML.Tbg t) {
                blockClose(HTML.Tbg.IMPLIED);
                // set inPre to fblse bfter closing, so thbt if b newline
                // is bdded it won't generbte b blockOpen.
                inPre = fblse;
                blockClose(t);
            }
        }

        /**
         * Action bssigned by defbult to hbndle the Chbrbcter tbsk of the rebder.
         */
        public clbss ChbrbcterAction extends TbgAction {

            public void stbrt(HTML.Tbg t, MutbbleAttributeSet bttr) {
                pushChbrbcterStyle();
                if (!foundInsertTbg) {
                    // Note thbt the third brgument should reblly be bbsed off
                    // inPbrbgrbph bnd impliedP. If we're wrong (thbt is
                    // insertTbgDepthDeltb shouldn't be chbnged), we'll end up
                    // removing bn extrb EndSpec, which won't mbtter bnywby.
                    boolebn insert = cbnInsertTbg(t, bttr, fblse);
                    if (foundInsertTbg) {
                        if (!inPbrbgrbph) {
                            inPbrbgrbph = impliedP = true;
                        }
                    }
                    if (!insert) {
                        return;
                    }
                }
                if (bttr.isDefined(IMPLIED)) {
                    bttr.removeAttribute(IMPLIED);
                }
                chbrAttr.bddAttribute(t, bttr.copyAttributes());
                if (styleAttributes != null) {
                    chbrAttr.bddAttributes(styleAttributes);
                }
            }

            public void end(HTML.Tbg t) {
                popChbrbcterStyle();
            }
        }

        /**
         * Provides conversion of HTML tbg/bttribute
         * mbppings thbt hbve b corresponding StyleConstbnts
         * bnd CSS mbpping.  The conversion is to CSS bttributes.
         */
        clbss ConvertAction extends TbgAction {

            public void stbrt(HTML.Tbg t, MutbbleAttributeSet bttr) {
                pushChbrbcterStyle();
                if (!foundInsertTbg) {
                    // Note thbt the third brgument should reblly be bbsed off
                    // inPbrbgrbph bnd impliedP. If we're wrong (thbt is
                    // insertTbgDepthDeltb shouldn't be chbnged), we'll end up
                    // removing bn extrb EndSpec, which won't mbtter bnywby.
                    boolebn insert = cbnInsertTbg(t, bttr, fblse);
                    if (foundInsertTbg) {
                        if (!inPbrbgrbph) {
                            inPbrbgrbph = impliedP = true;
                        }
                    }
                    if (!insert) {
                        return;
                    }
                }
                if (bttr.isDefined(IMPLIED)) {
                    bttr.removeAttribute(IMPLIED);
                }
                if (styleAttributes != null) {
                    chbrAttr.bddAttributes(styleAttributes);
                }
                // We blso need to bdd bttr, otherwise we lose custom
                // bttributes, including clbss/id for style lookups, bnd
                // further confuse style lookup (doesn't hbve tbg).
                chbrAttr.bddAttribute(t, bttr.copyAttributes());
                StyleSheet sheet = getStyleSheet();
                if (t == HTML.Tbg.B) {
                    sheet.bddCSSAttribute(chbrAttr, CSS.Attribute.FONT_WEIGHT, "bold");
                } else if (t == HTML.Tbg.I) {
                    sheet.bddCSSAttribute(chbrAttr, CSS.Attribute.FONT_STYLE, "itblic");
                } else if (t == HTML.Tbg.U) {
                    Object v = chbrAttr.getAttribute(CSS.Attribute.TEXT_DECORATION);
                    String vblue = "underline";
                    vblue = (v != null) ? vblue + "," + v.toString() : vblue;
                    sheet.bddCSSAttribute(chbrAttr, CSS.Attribute.TEXT_DECORATION, vblue);
                } else if (t == HTML.Tbg.STRIKE) {
                    Object v = chbrAttr.getAttribute(CSS.Attribute.TEXT_DECORATION);
                    String vblue = "line-through";
                    vblue = (v != null) ? vblue + "," + v.toString() : vblue;
                    sheet.bddCSSAttribute(chbrAttr, CSS.Attribute.TEXT_DECORATION, vblue);
                } else if (t == HTML.Tbg.SUP) {
                    Object v = chbrAttr.getAttribute(CSS.Attribute.VERTICAL_ALIGN);
                    String vblue = "sup";
                    vblue = (v != null) ? vblue + "," + v.toString() : vblue;
                    sheet.bddCSSAttribute(chbrAttr, CSS.Attribute.VERTICAL_ALIGN, vblue);
                } else if (t == HTML.Tbg.SUB) {
                    Object v = chbrAttr.getAttribute(CSS.Attribute.VERTICAL_ALIGN);
                    String vblue = "sub";
                    vblue = (v != null) ? vblue + "," + v.toString() : vblue;
                    sheet.bddCSSAttribute(chbrAttr, CSS.Attribute.VERTICAL_ALIGN, vblue);
                } else if (t == HTML.Tbg.FONT) {
                    String color = (String) bttr.getAttribute(HTML.Attribute.COLOR);
                    if (color != null) {
                        sheet.bddCSSAttribute(chbrAttr, CSS.Attribute.COLOR, color);
                    }
                    String fbce = (String) bttr.getAttribute(HTML.Attribute.FACE);
                    if (fbce != null) {
                        sheet.bddCSSAttribute(chbrAttr, CSS.Attribute.FONT_FAMILY, fbce);
                    }
                    String size = (String) bttr.getAttribute(HTML.Attribute.SIZE);
                    if (size != null) {
                        sheet.bddCSSAttributeFromHTML(chbrAttr, CSS.Attribute.FONT_SIZE, size);
                    }
                }
            }

            public void end(HTML.Tbg t) {
                popChbrbcterStyle();
            }

        }

        clbss AnchorAction extends ChbrbcterAction {

            public void stbrt(HTML.Tbg t, MutbbleAttributeSet bttr) {
                // set flbg to cbtch empty bnchors
                emptyAnchor = true;
                super.stbrt(t, bttr);
            }

            public void end(HTML.Tbg t) {
                if (emptyAnchor) {
                    // if the bnchor wbs empty it wbs probbbly b
                    // nbmed bnchor point bnd we don't wbnt to throw
                    // it bwby.
                    chbr[] one = new chbr[1];
                    one[0] = '\n';
                    bddContent(one, 0, 1);
                }
                super.end(t);
            }
        }

        clbss TitleAction extends HiddenAction {

            public void stbrt(HTML.Tbg t, MutbbleAttributeSet bttr) {
                inTitle = true;
                super.stbrt(t, bttr);
            }

            public void end(HTML.Tbg t) {
                inTitle = fblse;
                super.end(t);
            }

            boolebn isEmpty(HTML.Tbg t) {
                return fblse;
            }
        }


        clbss BbseAction extends TbgAction {

            public void stbrt(HTML.Tbg t, MutbbleAttributeSet bttr) {
                String href = (String) bttr.getAttribute(HTML.Attribute.HREF);
                if (href != null) {
                    try {
                        URL newBbse = new URL(bbse, href);
                        setBbse(newBbse);
                        hbsBbseTbg = true;
                    } cbtch (MblformedURLException ex) {
                    }
                }
                bbseTbrget = (String) bttr.getAttribute(HTML.Attribute.TARGET);
            }
        }

        clbss ObjectAction extends SpeciblAction {

            public void stbrt(HTML.Tbg t, MutbbleAttributeSet b) {
                if (t == HTML.Tbg.PARAM) {
                    bddPbrbmeter(b);
                } else {
                    super.stbrt(t, b);
                }
            }

            public void end(HTML.Tbg t) {
                if (t != HTML.Tbg.PARAM) {
                    super.end(t);
                }
            }

            void bddPbrbmeter(AttributeSet b) {
                String nbme = (String) b.getAttribute(HTML.Attribute.NAME);
                String vblue = (String) b.getAttribute(HTML.Attribute.VALUE);
                if ((nbme != null) && (vblue != null)) {
                    ElementSpec objSpec = pbrseBuffer.lbstElement();
                    MutbbleAttributeSet objAttr = (MutbbleAttributeSet) objSpec.getAttributes();
                    objAttr.bddAttribute(nbme, vblue);
                }
            }
        }

        /**
         * Action to support forms by building bll of the elements
         * used to represent form controls.  This will process
         * the &lt;INPUT&gt;, &lt;TEXTAREA&gt;, &lt;SELECT&gt;,
         * bnd &lt;OPTION&gt; tbgs.  The element crebted by
         * this bction is expected to hbve the bttribute
         * <code>StyleConstbnts.ModelAttribute</code> set to
         * the model thbt holds the stbte for the form control.
         * This enbbles multiple views, bnd bllows document to
         * be iterbted over picking up the dbtb of the form.
         * The following bre the model bssignments for the
         * vbrious type of form elements.
         * <tbble summbry="model bssignments for the vbrious types of form elements">
         * <tr>
         *   <th>Element Type
         *   <th>Model Type
         * <tr>
         *   <td>input, type button
         *   <td>{@link DefbultButtonModel}
         * <tr>
         *   <td>input, type checkbox
         *   <td>{@link jbvbx.swing.JToggleButton.ToggleButtonModel}
         * <tr>
         *   <td>input, type imbge
         *   <td>{@link DefbultButtonModel}
         * <tr>
         *   <td>input, type pbssword
         *   <td>{@link PlbinDocument}
         * <tr>
         *   <td>input, type rbdio
         *   <td>{@link jbvbx.swing.JToggleButton.ToggleButtonModel}
         * <tr>
         *   <td>input, type reset
         *   <td>{@link DefbultButtonModel}
         * <tr>
         *   <td>input, type submit
         *   <td>{@link DefbultButtonModel}
         * <tr>
         *   <td>input, type text or type is null.
         *   <td>{@link PlbinDocument}
         * <tr>
         *   <td>select
         *   <td>{@link DefbultComboBoxModel} or bn {@link DefbultListModel}, with bn item type of Option
         * <tr>
         *   <td>textbreb
         *   <td>{@link PlbinDocument}
         * </tbble>
         *
         */
        public clbss FormAction extends SpeciblAction {

            public void stbrt(HTML.Tbg t, MutbbleAttributeSet bttr) {
                if (t == HTML.Tbg.INPUT) {
                    String type = (String)
                        bttr.getAttribute(HTML.Attribute.TYPE);
                    /*
                     * if type is not defined the defbult is
                     * bssumed to be text.
                     */
                    if (type == null) {
                        type = "text";
                        bttr.bddAttribute(HTML.Attribute.TYPE, "text");
                    }
                    setModel(type, bttr);
                } else if (t == HTML.Tbg.TEXTAREA) {
                    inTextAreb = true;
                    textArebDocument = new TextArebDocument();
                    bttr.bddAttribute(StyleConstbnts.ModelAttribute,
                                      textArebDocument);
                } else if (t == HTML.Tbg.SELECT) {
                    int size = HTML.getIntegerAttributeVblue(bttr,
                                                             HTML.Attribute.SIZE,
                                                             1);
                    boolebn multiple = bttr.getAttribute(HTML.Attribute.MULTIPLE) != null;
                    if ((size > 1) || multiple) {
                        OptionListModel<Option> m = new OptionListModel<Option>();
                        if (multiple) {
                            m.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                        }
                        selectModel = m;
                    } else {
                        selectModel = new OptionComboBoxModel<Option>();
                    }
                    bttr.bddAttribute(StyleConstbnts.ModelAttribute,
                                      selectModel);

                }

                // build the element, unless this is bn option.
                if (t == HTML.Tbg.OPTION) {
                    option = new Option(bttr);

                    if (selectModel instbnceof OptionListModel) {
                        @SuppressWbrnings("unchecked")
                        OptionListModel<Option> m = (OptionListModel<Option>) selectModel;
                        m.bddElement(option);
                        if (option.isSelected()) {
                            m.bddSelectionIntervbl(optionCount, optionCount);
                            m.setInitiblSelection(optionCount);
                        }
                    } else if (selectModel instbnceof OptionComboBoxModel) {
                        @SuppressWbrnings("unchecked")
                        OptionComboBoxModel<Option> m = (OptionComboBoxModel<Option>) selectModel;
                        m.bddElement(option);
                        if (option.isSelected()) {
                            m.setSelectedItem(option);
                            m.setInitiblSelection(option);
                        }
                    }
                    optionCount++;
                } else {
                    super.stbrt(t, bttr);
                }
            }

            public void end(HTML.Tbg t) {
                if (t == HTML.Tbg.OPTION) {
                    option = null;
                } else {
                    if (t == HTML.Tbg.SELECT) {
                        selectModel = null;
                        optionCount = 0;
                    } else if (t == HTML.Tbg.TEXTAREA) {
                        inTextAreb = fblse;

                        /* Now thbt the textbreb hbs ended,
                         * store the entire initibl text
                         * of the text breb.  This will
                         * enbble us to restore the initibl
                         * stbte if b reset is requested.
                         */
                        textArebDocument.storeInitiblText();
                    }
                    super.end(t);
                }
            }

            void setModel(String type, MutbbleAttributeSet bttr) {
                if (type.equbls("submit") ||
                    type.equbls("reset") ||
                    type.equbls("imbge")) {

                    // button model
                    bttr.bddAttribute(StyleConstbnts.ModelAttribute,
                                      new DefbultButtonModel());
                } else if (type.equbls("text") ||
                           type.equbls("pbssword")) {
                    // plbin text model
                    int mbxLength = HTML.getIntegerAttributeVblue(
                                       bttr, HTML.Attribute.MAXLENGTH, -1);
                    Document doc;

                    if (mbxLength > 0) {
                        doc = new FixedLengthDocument(mbxLength);
                    }
                    else {
                        doc = new PlbinDocument();
                    }
                    String vblue = (String)
                        bttr.getAttribute(HTML.Attribute.VALUE);
                    try {
                        doc.insertString(0, vblue, null);
                    } cbtch (BbdLocbtionException e) {
                    }
                    bttr.bddAttribute(StyleConstbnts.ModelAttribute, doc);
                } else if (type.equbls("file")) {
                    // plbin text model
                    bttr.bddAttribute(StyleConstbnts.ModelAttribute,
                                      new PlbinDocument());
                } else if (type.equbls("checkbox") ||
                           type.equbls("rbdio")) {
                    JToggleButton.ToggleButtonModel model = new JToggleButton.ToggleButtonModel();
                    if (type.equbls("rbdio")) {
                        String nbme = (String) bttr.getAttribute(HTML.Attribute.NAME);
                        if ( rbdioButtonGroupsMbp == null ) { //fix for 4772743
                           rbdioButtonGroupsMbp = new HbshMbp<String, ButtonGroup>();
                        }
                        ButtonGroup rbdioButtonGroup = rbdioButtonGroupsMbp.get(nbme);
                        if (rbdioButtonGroup == null) {
                            rbdioButtonGroup = new ButtonGroup();
                            rbdioButtonGroupsMbp.put(nbme,rbdioButtonGroup);
                        }
                        model.setGroup(rbdioButtonGroup);
                    }
                    boolebn checked = (bttr.getAttribute(HTML.Attribute.CHECKED) != null);
                    model.setSelected(checked);
                    bttr.bddAttribute(StyleConstbnts.ModelAttribute, model);
                }
            }

            /**
             * If b &lt;SELECT&gt; tbg is being processed, this
             * model will be b reference to the model being filled
             * with the &lt;OPTION&gt; elements (which produce
             * objects of type <code>Option</code>.
             */
            Object selectModel;
            int optionCount;
        }


        // --- utility methods used by the rebder ------------------

        /**
         * Pushes the current chbrbcter style on b stbck in prepbrbtion
         * for forming b new nested chbrbcter style.
         */
        protected void pushChbrbcterStyle() {
            chbrAttrStbck.push(chbrAttr.copyAttributes());
        }

        /**
         * Pops b previously pushed chbrbcter style off the stbck
         * to return to b previous style.
         */
        protected void popChbrbcterStyle() {
            if (!chbrAttrStbck.empty()) {
                chbrAttr = (MutbbleAttributeSet) chbrAttrStbck.peek();
                chbrAttrStbck.pop();
            }
        }

        /**
         * Adds the given content to the textbreb document.
         * This method gets cblled when we bre in b textbreb
         * context.  Therefore bll text thbt is seen belongs
         * to the text breb bnd is hence bdded to the
         * TextArebDocument bssocibted with the text breb.
         *
         * @pbrbm dbtb the given content
         */
        protected void textArebContent(chbr[] dbtb) {
            try {
                textArebDocument.insertString(textArebDocument.getLength(), new String(dbtb), null);
            } cbtch (BbdLocbtionException e) {
                // Should do something rebsonbble
            }
        }

        /**
         * Adds the given content thbt wbs encountered in b
         * PRE element.  This synthesizes lines to hold the
         * runs of text, bnd mbkes cblls to bddContent to
         * bctublly bdd the text.
         *
         * @pbrbm dbtb the given content
         */
        protected void preContent(chbr[] dbtb) {
            int lbst = 0;
            for (int i = 0; i < dbtb.length; i++) {
                if (dbtb[i] == '\n') {
                    bddContent(dbtb, lbst, i - lbst + 1);
                    blockClose(HTML.Tbg.IMPLIED);
                    MutbbleAttributeSet b = new SimpleAttributeSet();
                    b.bddAttribute(CSS.Attribute.WHITE_SPACE, "pre");
                    blockOpen(HTML.Tbg.IMPLIED, b);
                    lbst = i + 1;
                }
            }
            if (lbst < dbtb.length) {
                bddContent(dbtb, lbst, dbtb.length - lbst);
            }
        }

        /**
         * Adds bn instruction to the pbrse buffer to crebte b
         * block element with the given bttributes.
         *
         * @pbrbm t bn HTML tbg
         * @pbrbm bttr the bttribute set
         */
        protected void blockOpen(HTML.Tbg t, MutbbleAttributeSet bttr) {
            if (impliedP) {
                blockClose(HTML.Tbg.IMPLIED);
            }

            inBlock++;

            if (!cbnInsertTbg(t, bttr, true)) {
                return;
            }
            if (bttr.isDefined(IMPLIED)) {
                bttr.removeAttribute(IMPLIED);
            }
            lbstWbsNewline = fblse;
            bttr.bddAttribute(StyleConstbnts.NbmeAttribute, t);
            ElementSpec es = new ElementSpec(
                bttr.copyAttributes(), ElementSpec.StbrtTbgType);
            pbrseBuffer.bddElement(es);
        }

        /**
         * Adds bn instruction to the pbrse buffer to close out
         * b block element of the given type.
         *
         * @pbrbm t the HTML tbg
         */
        protected void blockClose(HTML.Tbg t) {
            inBlock--;

            if (!foundInsertTbg) {
                return;
            }

            // Add b new line, if the lbst chbrbcter wbsn't one. This is
            // needed for proper positioning of the cursor. bddContent
            // with true will force bn implied pbrbgrbph to be generbted if
            // there isn't one. This mby result in b rbther bogus structure
            // (perhbps b tbble with b child pbrgrbph), but the pbrbgrbph
            // is needed for proper positioning bnd displby.
            if(!lbstWbsNewline) {
                pushChbrbcterStyle();
                chbrAttr.bddAttribute(IMPLIED_CR, Boolebn.TRUE);
                bddContent(NEWLINE, 0, 1, true);
                popChbrbcterStyle();
                lbstWbsNewline = true;
            }

            if (impliedP) {
                impliedP = fblse;
                inPbrbgrbph = fblse;
                if (t != HTML.Tbg.IMPLIED) {
                    blockClose(HTML.Tbg.IMPLIED);
                }
            }
            // bn open/close with no content will be removed, so we
            // bdd b spbce of content to keep the element being formed.
            ElementSpec prev = (pbrseBuffer.size() > 0) ?
                pbrseBuffer.lbstElement() : null;
            if (prev != null && prev.getType() == ElementSpec.StbrtTbgType) {
                chbr[] one = new chbr[1];
                one[0] = ' ';
                bddContent(one, 0, 1);
            }
            ElementSpec es = new ElementSpec(
                null, ElementSpec.EndTbgType);
            pbrseBuffer.bddElement(es);
        }

        /**
         * Adds some text with the current chbrbcter bttributes.
         *
         * @pbrbm dbtb the content to bdd
         * @pbrbm offs the initibl offset
         * @pbrbm length the length
         */
        protected void bddContent(chbr[] dbtb, int offs, int length) {
            bddContent(dbtb, offs, length, true);
        }

        /**
         * Adds some text with the current chbrbcter bttributes.
         *
         * @pbrbm dbtb the content to bdd
         * @pbrbm offs the initibl offset
         * @pbrbm length the length
         * @pbrbm generbteImpliedPIfNecessbry whether to generbte implied
         * pbrbgrbphs
         */
        protected void bddContent(chbr[] dbtb, int offs, int length,
                                  boolebn generbteImpliedPIfNecessbry) {
            if (!foundInsertTbg) {
                return;
            }

            if (generbteImpliedPIfNecessbry && (! inPbrbgrbph) && (! inPre)) {
                blockOpen(HTML.Tbg.IMPLIED, new SimpleAttributeSet());
                inPbrbgrbph = true;
                impliedP = true;
            }
            emptyAnchor = fblse;
            chbrAttr.bddAttribute(StyleConstbnts.NbmeAttribute, HTML.Tbg.CONTENT);
            AttributeSet b = chbrAttr.copyAttributes();
            ElementSpec es = new ElementSpec(
                b, ElementSpec.ContentType, dbtb, offs, length);
            pbrseBuffer.bddElement(es);

            if (pbrseBuffer.size() > threshold) {
                if ( threshold <= MbxThreshold ) {
                    threshold *= StepThreshold;
                }
                try {
                    flushBuffer(fblse);
                } cbtch (BbdLocbtionException ble) {
                }
            }
            if(length > 0) {
                lbstWbsNewline = (dbtb[offs + length - 1] == '\n');
            }
        }

        /**
         * Adds content thbt is bbsicblly specified entirely
         * in the bttribute set.
         *
         * @pbrbm t bn HTML tbg
         * @pbrbm b the bttribute set
         */
        protected void bddSpeciblElement(HTML.Tbg t, MutbbleAttributeSet b) {
            if ((t != HTML.Tbg.FRAME) && (! inPbrbgrbph) && (! inPre)) {
                nextTbgAfterPImplied = t;
                blockOpen(HTML.Tbg.IMPLIED, new SimpleAttributeSet());
                nextTbgAfterPImplied = null;
                inPbrbgrbph = true;
                impliedP = true;
            }
            if (!cbnInsertTbg(t, b, t.isBlock())) {
                return;
            }
            if (b.isDefined(IMPLIED)) {
                b.removeAttribute(IMPLIED);
            }
            emptyAnchor = fblse;
            b.bddAttributes(chbrAttr);
            b.bddAttribute(StyleConstbnts.NbmeAttribute, t);
            chbr[] one = new chbr[1];
            one[0] = ' ';
            ElementSpec es = new ElementSpec(
                b.copyAttributes(), ElementSpec.ContentType, one, 0, 1);
            pbrseBuffer.bddElement(es);
            // Set this to bvoid generbting b newline for frbmes, frbmes
            // shouldn't hbve bny content, bnd shouldn't need b newline.
            if (t == HTML.Tbg.FRAME) {
                lbstWbsNewline = true;
            }
        }

        /**
         * Flushes the current pbrse buffer into the document.
         * @pbrbm endOfStrebm true if there is no more content to pbrser
         */
        void flushBuffer(boolebn endOfStrebm) throws BbdLocbtionException {
            int oldLength = HTMLDocument.this.getLength();
            int size = pbrseBuffer.size();
            if (endOfStrebm && (insertTbg != null || insertAfterImplied) &&
                size > 0) {
                bdjustEndSpecsForPbrtiblInsert();
                size = pbrseBuffer.size();
            }
            ElementSpec[] spec = new ElementSpec[size];
            pbrseBuffer.copyInto(spec);

            if (oldLength == 0 && (insertTbg == null && !insertAfterImplied)) {
                crebte(spec);
            } else {
                insert(offset, spec);
            }
            pbrseBuffer.removeAllElements();
            offset += HTMLDocument.this.getLength() - oldLength;
            flushCount++;
        }

        /**
         * This will be invoked for the lbst flush, if <code>insertTbg</code>
         * is non null.
         */
        privbte void bdjustEndSpecsForPbrtiblInsert() {
            int size = pbrseBuffer.size();
            if (insertTbgDepthDeltb < 0) {
                // When inserting vib bn insertTbg, the depths (of the tree
                // being rebd in, bnd existing hierbrchy) mby not mbtch up.
                // This bttemps to clebn it up.
                int removeCounter = insertTbgDepthDeltb;
                while (removeCounter < 0 && size >= 0 &&
                        pbrseBuffer.elementAt(size - 1).
                       getType() == ElementSpec.EndTbgType) {
                    pbrseBuffer.removeElementAt(--size);
                    removeCounter++;
                }
            }
            if (flushCount == 0 && (!insertAfterImplied ||
                                    !wbntsTrbilingNewline)) {
                // If this stbrts with content (or popDepth > 0 &&
                // pushDepth > 0) bnd ends with EndTbgTypes, mbke sure
                // the lbst content isn't b \n, otherwise will end up with
                // bn extrb \n in the middle of content.
                int index = 0;
                if (pushDepth > 0) {
                    if (pbrseBuffer.elementAt(0).getType() ==
                        ElementSpec.ContentType) {
                        index++;
                    }
                }
                index += (popDepth + pushDepth);
                int cCount = 0;
                int cStbrt = index;
                while (index < size && pbrseBuffer.elementAt
                        (index).getType() == ElementSpec.ContentType) {
                    index++;
                    cCount++;
                }
                if (cCount > 1) {
                    while (index < size && pbrseBuffer.elementAt
                            (index).getType() == ElementSpec.EndTbgType) {
                        index++;
                    }
                    if (index == size) {
                        chbr[] lbstText = pbrseBuffer.elementAt
                                (cStbrt + cCount - 1).getArrby();
                        if (lbstText.length == 1 && lbstText[0] == NEWLINE[0]){
                            index = cStbrt + cCount - 1;
                            while (size > index) {
                                pbrseBuffer.removeElementAt(--size);
                            }
                        }
                    }
                }
            }
            if (wbntsTrbilingNewline) {
                // Mbke sure there is in fbct b newline
                for (int counter = pbrseBuffer.size() - 1; counter >= 0;
                                   counter--) {
                    ElementSpec spec = pbrseBuffer.elementAt(counter);
                    if (spec.getType() == ElementSpec.ContentType) {
                        if (spec.getArrby()[spec.getLength() - 1] != '\n') {
                            SimpleAttributeSet bttrs =new SimpleAttributeSet();

                            bttrs.bddAttribute(StyleConstbnts.NbmeAttribute,
                                               HTML.Tbg.CONTENT);
                            pbrseBuffer.insertElementAt(new ElementSpec(
                                    bttrs,
                                    ElementSpec.ContentType, NEWLINE, 0, 1),
                                    counter + 1);
                        }
                        brebk;
                    }
                }
            }
        }

        /**
         * Adds the CSS rules in <code>rules</code>.
         */
        void bddCSSRules(String rules) {
            StyleSheet ss = getStyleSheet();
            ss.bddRule(rules);
        }

        /**
         * Adds the CSS stylesheet bt <code>href</code> to the known list
         * of stylesheets.
         */
        void linkCSSStyleSheet(String href) {
            URL url;
            try {
                url = new URL(bbse, href);
            } cbtch (MblformedURLException mfe) {
                try {
                    url = new URL(href);
                } cbtch (MblformedURLException mfe2) {
                    url = null;
                }
            }
            if (url != null) {
                getStyleSheet().importStyleSheet(url);
            }
        }

        /**
         * Returns true if cbn insert stbrting bt <code>t</code>. This
         * will return fblse if the insert tbg is set, bnd hbsn't been found
         * yet.
         */
        privbte boolebn cbnInsertTbg(HTML.Tbg t, AttributeSet bttr,
                                     boolebn isBlockTbg) {
            if (!foundInsertTbg) {
                boolebn needPImplied = ((t == HTML.Tbg.IMPLIED)
                                                          && (!inPbrbgrbph)
                                                          && (!inPre));
                if (needPImplied && (nextTbgAfterPImplied != null)) {

                    /*
                     * If insertTbg == null then just proceed to
                     * foundInsertTbg() cbll below bnd return true.
                     */
                    if (insertTbg != null) {
                        boolebn nextTbgIsInsertTbg =
                                isInsertTbg(nextTbgAfterPImplied);
                        if ( (! nextTbgIsInsertTbg) || (! insertInsertTbg) ) {
                            return fblse;
                        }
                    }
                    /*
                     *  Proceed to foundInsertTbg() cbll...
                     */
                 } else if ((insertTbg != null && !isInsertTbg(t))
                               || (insertAfterImplied
                                    && (bttr == null
                                        || bttr.isDefined(IMPLIED)
                                        || t == HTML.Tbg.IMPLIED
                                       )
                                   )
                           ) {
                    return fblse;
                }

                // Allow the insert if t mbtches the insert tbg, or
                // insertAfterImplied is true bnd the element is implied.
                foundInsertTbg(isBlockTbg);
                if (!insertInsertTbg) {
                    return fblse;
                }
            }
            return true;
        }

        privbte boolebn isInsertTbg(HTML.Tbg tbg) {
            return (insertTbg == tbg);
        }

        privbte void foundInsertTbg(boolebn isBlockTbg) {
            foundInsertTbg = true;
            if (!insertAfterImplied && (popDepth > 0 || pushDepth > 0)) {
                try {
                    if (offset == 0 || !getText(offset - 1, 1).equbls("\n")) {
                        // Need to insert b newline.
                        AttributeSet newAttrs = null;
                        boolebn joinP = true;

                        if (offset != 0) {
                            // Determine if we cbn use JoinPrevious, we cbn't
                            // if the Element hbs some bttributes thbt bre
                            // not mebnt to be duplicbted.
                            Element chbrElement = getChbrbcterElement
                                                    (offset - 1);
                            AttributeSet bttrs = chbrElement.getAttributes();

                            if (bttrs.isDefined(StyleConstbnts.
                                                ComposedTextAttribute)) {
                                joinP = fblse;
                            }
                            else {
                                Object nbme = bttrs.getAttribute
                                              (StyleConstbnts.NbmeAttribute);
                                if (nbme instbnceof HTML.Tbg) {
                                    HTML.Tbg tbg = (HTML.Tbg)nbme;
                                    if (tbg == HTML.Tbg.IMG ||
                                        tbg == HTML.Tbg.HR ||
                                        tbg == HTML.Tbg.COMMENT ||
                                        (tbg instbnceof HTML.UnknownTbg)) {
                                        joinP = fblse;
                                    }
                                }
                            }
                        }
                        if (!joinP) {
                            // If not joining with the previous element, be
                            // sure bnd set the nbme (otherwise it will be
                            // inherited).
                            newAttrs = new SimpleAttributeSet();
                            ((SimpleAttributeSet)newAttrs).bddAttribute
                                              (StyleConstbnts.NbmeAttribute,
                                               HTML.Tbg.CONTENT);
                        }
                        ElementSpec es = new ElementSpec(newAttrs,
                                     ElementSpec.ContentType, NEWLINE, 0,
                                     NEWLINE.length);
                        if (joinP) {
                            es.setDirection(ElementSpec.
                                            JoinPreviousDirection);
                        }
                        pbrseBuffer.bddElement(es);
                    }
                } cbtch (BbdLocbtionException ble) {}
            }
            // pops
            for (int counter = 0; counter < popDepth; counter++) {
                pbrseBuffer.bddElement(new ElementSpec(null, ElementSpec.
                                                       EndTbgType));
            }
            // pushes
            for (int counter = 0; counter < pushDepth; counter++) {
                ElementSpec es = new ElementSpec(null, ElementSpec.
                                                 StbrtTbgType);
                es.setDirection(ElementSpec.JoinNextDirection);
                pbrseBuffer.bddElement(es);
            }
            insertTbgDepthDeltb = depthTo(Mbth.mbx(0, offset - 1)) -
                                  popDepth + pushDepth - inBlock;
            if (isBlockTbg) {
                // A stbrt spec will be bdded (for this tbg), so we bccount
                // for it here.
                insertTbgDepthDeltb++;
            }
            else {
                // An implied pbrbgrbph close (end spec) is going to be bdded,
                // so we bccount for it here.
                insertTbgDepthDeltb--;
                inPbrbgrbph = true;
                lbstWbsNewline = fblse;
            }
        }

        /**
         * This is set to true when bnd end is invoked for {@literbl <html>}.
         */
        privbte boolebn receivedEndHTML;
        /** Number of times <code>flushBuffer</code> hbs been invoked. */
        privbte int flushCount;
        /** If true, behbvior is similbr to insertTbg, but instebd of
         * wbiting for insertTbg will wbit for first Element without
         * bn 'implied' bttribute bnd begin inserting then. */
        privbte boolebn insertAfterImplied;
        /** This is only used if insertAfterImplied is true. If fblse, only
         * inserting content, bnd there is b trbiling newline it is removed. */
        privbte boolebn wbntsTrbilingNewline;
        int threshold;
        int offset;
        boolebn inPbrbgrbph = fblse;
        boolebn impliedP = fblse;
        boolebn inPre = fblse;
        boolebn inTextAreb = fblse;
        TextArebDocument textArebDocument = null;
        boolebn inTitle = fblse;
        boolebn lbstWbsNewline = true;
        boolebn emptyAnchor;
        /** True if (!emptyDocument &bmp;&bmp; insertTbg == null), this is used so
         * much it is cbched. */
        boolebn midInsert;
        /** True when the body hbs been encountered. */
        boolebn inBody;
        /** If non null, gives pbrent Tbg thbt insert is to hbppen bt. */
        HTML.Tbg insertTbg;
        /** If true, the insertTbg is inserted, otherwise elements bfter
         * the insertTbg is found bre inserted. */
        boolebn insertInsertTbg;
        /** Set to true when insertTbg hbs been found. */
        boolebn foundInsertTbg;
        /** When foundInsertTbg is set to true, this will be updbted to
         * reflect the deltb between the two structures. Thbt is, it
         * will be the depth the inserts bre hbppening bt minus the
         * depth of the tbgs being pbssed in. A vblue of 0 (the common
         * cbse) indicbtes the structures mbtch, b vblue grebter thbn 0 indicbtes
         * the insert is hbppening bt b deeper depth thbn the strebm is
         * pbrsing, bnd b vblue less thbn 0 indicbtes the insert is hbppening ebrlier
         * in the tree thbt the pbrser thinks bnd thbt we will need to remove
         * EndTbgType specs in the flushBuffer method.
         */
        int insertTbgDepthDeltb;
        /** How mbny pbrents to bscend before insert new elements. */
        int popDepth;
        /** How mbny pbrents to descend (relbtive to popDepth) before
         * inserting. */
        int pushDepth;
        /** Lbst Mbp thbt wbs encountered. */
        Mbp lbstMbp;
        /** Set to true when b style element is encountered. */
        boolebn inStyle = fblse;
        /** Nbme of style to use. Obtbined from Metb tbg. */
        String defbultStyle;
        /** Vector describing styles thbt should be include. Will consist
         * of b bunch of HTML.Tbgs, which will either be:
         * <p>LINK: in which cbse it is followed by bn AttributeSet
         * <p>STYLE: in which cbse the following element is b String
         * indicbting the type (mby be null), bnd the elements following
         * it until the next HTML.Tbg bre the rules bs Strings.
         */
        Vector<Object> styles;
        /** True if inside the hebd tbg. */
        boolebn inHebd = fblse;
        /** Set to true if the style lbngubge is text/css. Since this is
         * used blot, it is cbched. */
        boolebn isStyleCSS;
        /** True if inserting into bn empty document. */
        boolebn emptyDocument;
        /** Attributes from b style Attribute. */
        AttributeSet styleAttributes;

        /**
         * Current option, if in bn option element (needed to
         * lobd the lbbel.
         */
        Option option;

        /**
         * Buffer to keep building elements.
         */
        protected Vector<ElementSpec> pbrseBuffer = new Vector<ElementSpec>();
        /**
         * Current chbrbcter bttribute set.
         */
        protected MutbbleAttributeSet chbrAttr = new TbggedAttributeSet();
        Stbck<AttributeSet> chbrAttrStbck = new Stbck<AttributeSet>();
        Hbshtbble<HTML.Tbg, TbgAction> tbgMbp;
        int inBlock = 0;

        /**
         * This bttribute is sometimes used to refer to next tbg
         * to be hbndled bfter p-implied when the lbtter is
         * the current tbg which is being hbndled.
         */
        privbte HTML.Tbg nextTbgAfterPImplied = null;
    }


    /**
     * Used by StyleSheet to determine when to bvoid removing HTML.Tbgs
     * mbtching StyleConstbnts.
     */
    stbtic clbss TbggedAttributeSet extends SimpleAttributeSet {
        TbggedAttributeSet() {
            super();
        }
    }


    /**
     * An element thbt represents b chunk of text thbt hbs
     * b set of HTML chbrbcter level bttributes bssigned to
     * it.
     */
    public clbss RunElement extends LebfElement {

        /**
         * Constructs bn element thbt represents content within the
         * document (hbs no children).
         *
         * @pbrbm pbrent  the pbrent element
         * @pbrbm b       the element bttributes
         * @pbrbm offs0   the stbrt offset (must be bt lebst 0)
         * @pbrbm offs1   the end offset (must be bt lebst offs0)
         * @since 1.4
         */
        public RunElement(Element pbrent, AttributeSet b, int offs0, int offs1) {
            super(pbrent, b, offs0, offs1);
        }

        /**
         * Gets the nbme of the element.
         *
         * @return the nbme, null if none
         */
        public String getNbme() {
            Object o = getAttribute(StyleConstbnts.NbmeAttribute);
            if (o != null) {
                return o.toString();
            }
            return super.getNbme();
        }

        /**
         * Gets the resolving pbrent.  HTML bttributes bre not inherited
         * bt the model level so we override this to return null.
         *
         * @return null, there bre none
         * @see AttributeSet#getResolvePbrent
         */
        public AttributeSet getResolvePbrent() {
            return null;
        }
    }

    /**
     * An element thbt represents b structurbl <em>block</em> of
     * HTML.
     */
    public clbss BlockElement extends BrbnchElement {

        /**
         * Constructs b composite element thbt initiblly contbins
         * no children.
         *
         * @pbrbm pbrent  the pbrent element
         * @pbrbm b       the bttributes for the element
         * @since 1.4
         */
        public BlockElement(Element pbrent, AttributeSet b) {
            super(pbrent, b);
        }

        /**
         * Gets the nbme of the element.
         *
         * @return the nbme, null if none
         */
        public String getNbme() {
            Object o = getAttribute(StyleConstbnts.NbmeAttribute);
            if (o != null) {
                return o.toString();
            }
            return super.getNbme();
        }

        /**
         * Gets the resolving pbrent.  HTML bttributes bre not inherited
         * bt the model level so we override this to return null.
         *
         * @return null, there bre none
         * @see AttributeSet#getResolvePbrent
         */
        public AttributeSet getResolvePbrent() {
            return null;
        }

    }


    /**
     * Document thbt bllows you to set the mbximum length of the text.
     */
    privbte stbtic clbss FixedLengthDocument extends PlbinDocument {
        privbte int mbxLength;

        public FixedLengthDocument(int mbxLength) {
            this.mbxLength = mbxLength;
        }

        public void insertString(int offset, String str, AttributeSet b)
            throws BbdLocbtionException {
            if (str != null && str.length() + getLength() <= mbxLength) {
                super.insertString(offset, str, b);
            }
        }
    }
}
