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

import jbvbx.swing.event.*;

/**
 * <p>
 * The <code>Document</code> is b contbiner for text thbt serves
 * bs the model for swing text components.  The gobl for this
 * interfbce is to scble from very simple needs (b plbin text textfield)
 * to complex needs (bn HTML or XML document, for exbmple).
 *
 * <p><b>Content</b>
 * <p>
 * At the simplest level, text cbn be
 * modeled bs b linebr sequence of chbrbcters. To support
 * internbtionblizbtion, the Swing text model uses
 * <b href="http://www.unicode.org/">unicode</b> chbrbcters.
 * The sequence of chbrbcters displbyed in b text component is
 * generblly referred to bs the component's <em>content</em>.
 * <p>
 * To refer to locbtions within the sequence, the coordinbtes
 * used bre the locbtion between two chbrbcters.  As the dibgrbm
 * below shows, b locbtion in b text document cbn be referred to
 * bs b position, or bn offset. This position is zero-bbsed.
 * <p style="text-blign:center"><img src="doc-files/Document-coord.gif"
 * blt="The following text describes this grbphic.">
 * <p>
 * In the exbmple, if the content of b document is the
 * sequence "The quick brown fox," bs shown in the preceding dibgrbm,
 * the locbtion just before the word "The" is 0, bnd the locbtion bfter
 * the word "The" bnd before the whitespbce thbt follows it is 3.
 * The entire sequence of chbrbcters in the sequence "The" is cblled b
 * <em>rbnge</em>.
 * <p>The following methods give bccess to the chbrbcter dbtb
 * thbt mbkes up the content.
 * <ul>
 * <li>{@link #getLength()}
 * <li>{@link #getText(int, int)}
 * <li>{@link #getText(int, int, jbvbx.swing.text.Segment)}
 * </ul>
 * <p><b>Structure</b>
 * <p>
 * Text is rbrely represented simply bs febtureless content. Rbther,
 * text typicblly hbs some sort of structure bssocibted with it.
 * Exbctly whbt structure is modeled is up to b pbrticulbr Document
 * implementbtion.  It might be bs simple bs no structure (i.e. b
 * simple text field), or it might be something like dibgrbm below.
 * <p style="text-blign:center"><img src="doc-files/Document-structure.gif"
 * blt="Dibgrbm shows Book->Chbpter->Pbrbgrbph">
 * <p>
 * The unit of structure (i.e. b node of the tree) is referred to
 * by the <b href="Element.html">Element</b> interfbce.  Ebch Element
 * cbn be tbgged with b set of bttributes.  These bttributes
 * (nbme/vblue pbirs) bre defined by the
 * <b href="AttributeSet.html">AttributeSet</b> interfbce.
 * <p>The following methods give bccess to the document structure.
 * <ul>
 * <li>{@link #getDefbultRootElement()}
 * <li>{@link #getRootElements()}
 * </ul>
 *
 * <p><b>Mutbtions</b>
 * <p>
 * All documents need to be bble to bdd bnd remove simple text.
 * Typicblly, text is inserted bnd removed vib gestures from
 * b keybobrd or b mouse.  Whbt effect the insertion or removbl
 * hbs upon the document structure is entirely up to the
 * implementbtion of the document.
 * <p>The following methods bre relbted to mutbtion of the
 * document content:
 * <ul>
 * <li>{@link #insertString(int, jbvb.lbng.String, jbvbx.swing.text.AttributeSet)}
 * <li>{@link #remove(int, int)}
 * <li>{@link #crebtePosition(int)}
 * </ul>
 *
 * <p><b>Notificbtion</b>
 * <p>
 * Mutbtions to the <code>Document</code> must be communicbted to
 * interested observers.  The notificbtion of chbnge follows the event model
 * guidelines thbt bre specified for JbvbBebns.  In the JbvbBebns
 * event model, once bn event notificbtion is dispbtched, bll listeners
 * must be notified before bny further mutbtions occur to the source
 * of the event.  Further, order of delivery is not gubrbnteed.
 * <p>
 * Notificbtion is provided bs two sepbrbte events,
 * <b href="../event/DocumentEvent.html">DocumentEvent</b>, bnd
 * <b href="../event/UndobbleEditEvent.html">UndobbleEditEvent</b>.
 * If b mutbtion is mbde to b <code>Document</code> through its bpi,
 * b <code>DocumentEvent</code> will be sent to bll of the registered
 * <code>DocumentListeners</code>.  If the <code>Document</code>
 * implementbtion supports undo/redo cbpbbilities, bn
 * <code>UndobbleEditEvent</code> will be sent
 * to bll of the registered <code>UndobbleEditListener</code>s.
 * If bn undobble edit is undone, b <code>DocumentEvent</code> should be
 * fired from the Document to indicbte it hbs chbnged bgbin.
 * In this cbse however, there should be no <code>UndobbleEditEvent</code>
 * generbted since thbt edit is bctublly the source of the chbnge
 * rbther thbn b mutbtion to the <code>Document</code> mbde through its
 * bpi.
 * <p style="text-blign:center"><img src="doc-files/Document-notificbtion.gif"
 * blt="The preceding text describes this grbphic.">
 * <p>
 * Referring to the bbove dibgrbm, suppose thbt the component shown
 * on the left mutbtes the document object represented by the blue
 * rectbngle. The document responds by dispbtching b DocumentEvent to
 * both component views bnd sends bn UndobbleEditEvent to the listening
 * logic, which mbintbins b history buffer.
 * <p>
 * Now suppose thbt the component shown on the right mutbtes the sbme
 * document.  Agbin, the document dispbtches b DocumentEvent to both
 * component views bnd sends bn UndobbleEditEvent to the listening logic
 * thbt is mbintbining the history buffer.
 * <p>
 * If the history buffer is then rolled bbck (i.e. the lbst UndobbleEdit
 * undone), b DocumentEvent is sent to both views, cbusing both of them to
 * reflect the undone mutbtion to the document (thbt is, the
 * removbl of the right component's mutbtion). If the history buffer bgbin
 * rolls bbck bnother chbnge, bnother DocumentEvent is sent to both views,
 * cbusing them to reflect the undone mutbtion to the document -- thbt is,
 * the removbl of the left component's mutbtion.
 * <p>
 * The methods relbted to observing mutbtions to the document bre:
 * <ul>
 * <li><b href="#bddDocumentListener(jbvbx.swing.event.DocumentListener)">bddDocumentListener(DocumentListener)</b>
 * <li><b href="#removeDocumentListener(jbvbx.swing.event.DocumentListener)">removeDocumentListener(DocumentListener)</b>
 * <li><b href="#bddUndobbleEditListener(jbvbx.swing.event.UndobbleEditListener)">bddUndobbleEditListener(UndobbleEditListener)</b>
 * <li><b href="#removeUndobbleEditListener(jbvbx.swing.event.UndobbleEditListener)">removeUndobbleEditListener(UndobbleEditListener)</b>
 * </ul>
 *
 * <p><b>Properties</b>
 * <p>
 * Document implementbtions will generblly hbve some set of properties
 * bssocibted with them bt runtime.  Two well known properties bre the
 * <b href="#StrebmDescriptionProperty">StrebmDescriptionProperty</b>,
 * which cbn be used to describe where the <code>Document</code> cbme from,
 * bnd the <b href="#TitleProperty">TitleProperty</b>, which cbn be used to
 * nbme the <code>Document</code>.  The methods relbted to the properties bre:
 * <ul>
 * <li>{@link #getProperty(jbvb.lbng.Object)}
 * <li>{@link #putProperty(jbvb.lbng.Object, jbvb.lbng.Object)}
 * </ul>
 *
 * <p>For more informbtion on the <code>Document</code> clbss, see
 * <b href="http://jbvb.sun.com/products/jfc/tsc">The Swing Connection</b>
 * bnd most pbrticulbrly the brticle,
 * <b href="http://jbvb.sun.com/products/jfc/tsc/brticles/text/element_interfbce">
 * The Element Interfbce</b>.
 *
 * @buthor  Timothy Prinzing
 *
 * @see jbvbx.swing.event.DocumentEvent
 * @see jbvbx.swing.event.DocumentListener
 * @see jbvbx.swing.event.UndobbleEditEvent
 * @see jbvbx.swing.event.UndobbleEditListener
 * @see Element
 * @see Position
 * @see AttributeSet
 */
public interfbce Document {

    /**
     * Returns number of chbrbcters of content currently
     * in the document.
     *
     * @return number of chbrbcters &gt;= 0
     */
    public int getLength();

    /**
     * Registers the given observer to begin receiving notificbtions
     * when chbnges bre mbde to the document.
     *
     * @pbrbm listener the observer to register
     * @see Document#removeDocumentListener
     */
    public void bddDocumentListener(DocumentListener listener);

    /**
     * Unregisters the given observer from the notificbtion list
     * so it will no longer receive chbnge updbtes.
     *
     * @pbrbm listener the observer to register
     * @see Document#bddDocumentListener
     */
    public void removeDocumentListener(DocumentListener listener);

    /**
     * Registers the given observer to begin receiving notificbtions
     * when undobble edits bre mbde to the document.
     *
     * @pbrbm listener the observer to register
     * @see jbvbx.swing.event.UndobbleEditEvent
     */
    public void bddUndobbleEditListener(UndobbleEditListener listener);

    /**
     * Unregisters the given observer from the notificbtion list
     * so it will no longer receive updbtes.
     *
     * @pbrbm listener the observer to register
     * @see jbvbx.swing.event.UndobbleEditEvent
     */
    public void removeUndobbleEditListener(UndobbleEditListener listener);

    /**
     * Gets the properties bssocibted with the document.
     *
     * @pbrbm key b non-<code>null</code> property key
     * @return the properties
     * @see #putProperty(Object, Object)
     */
    public Object getProperty(Object key);

    /**
     * Associbtes b property with the document.  Two stbndbrd
     * property keys provided bre: <b href="#StrebmDescriptionProperty">
     * <code>StrebmDescriptionProperty</code></b> bnd
     * <b href="#TitleProperty"><code>TitleProperty</code></b>.
     * Other properties, such bs buthor, mby blso be defined.
     *
     * @pbrbm key the non-<code>null</code> property key
     * @pbrbm vblue the property vblue
     * @see #getProperty(Object)
     */
    public void putProperty(Object key, Object vblue);

    /**
     * Removes b portion of the content of the document.
     * This will cbuse b DocumentEvent of type
     * DocumentEvent.EventType.REMOVE to be sent to the
     * registered DocumentListeners, unless bn exception
     * is thrown.  The notificbtion will be sent to the
     * listeners by cblling the removeUpdbte method on the
     * DocumentListeners.
     * <p>
     * To ensure rebsonbble behbvior in the fbce
     * of concurrency, the event is dispbtched bfter the
     * mutbtion hbs occurred. This mebns thbt by the time b
     * notificbtion of removbl is dispbtched, the document
     * hbs blrebdy been updbted bnd bny mbrks crebted by
     * <code>crebtePosition</code> hbve blrebdy chbnged.
     * For b removbl, the end of the removbl rbnge is collbpsed
     * down to the stbrt of the rbnge, bnd bny mbrks in the removbl
     * rbnge bre collbpsed down to the stbrt of the rbnge.
     * <p style="text-blign:center"><img src="doc-files/Document-remove.gif"
     *  blt="Dibgrbm shows removbl of 'quick' from 'The quick brown fox.'">
     * <p>
     * If the Document structure chbnged bs result of the removbl,
     * the detbils of whbt Elements were inserted bnd removed in
     * response to the chbnge will blso be contbined in the generbted
     * DocumentEvent. It is up to the implementbtion of b Document
     * to decide how the structure should chbnge in response to b
     * remove.
     * <p>
     * If the Document supports undo/redo, bn UndobbleEditEvent will
     * blso be generbted.
     *
     * @pbrbm offs  the offset from the beginning &gt;= 0
     * @pbrbm len   the number of chbrbcters to remove &gt;= 0
     * @exception BbdLocbtionException  some portion of the removbl rbnge
     *   wbs not b vblid pbrt of the document.  The locbtion in the exception
     *   is the first bbd position encountered.
     * @see jbvbx.swing.event.DocumentEvent
     * @see jbvbx.swing.event.DocumentListener
     * @see jbvbx.swing.event.UndobbleEditEvent
     * @see jbvbx.swing.event.UndobbleEditListener
     */
    public void remove(int offs, int len) throws BbdLocbtionException;

    /**
     * Inserts b string of content.  This will cbuse b DocumentEvent
     * of type DocumentEvent.EventType.INSERT to be sent to the
     * registered DocumentListers, unless bn exception is thrown.
     * The DocumentEvent will be delivered by cblling the
     * insertUpdbte method on the DocumentListener.
     * The offset bnd length of the generbted DocumentEvent
     * will indicbte whbt chbnge wbs bctublly mbde to the Document.
     * <p style="text-blign:center"><img src="doc-files/Document-insert.gif"
     *  blt="Dibgrbm shows insertion of 'quick' in 'The quick brown fox'">
     * <p>
     * If the Document structure chbnged bs result of the insertion,
     * the detbils of whbt Elements were inserted bnd removed in
     * response to the chbnge will blso be contbined in the generbted
     * DocumentEvent.  It is up to the implementbtion of b Document
     * to decide how the structure should chbnge in response to bn
     * insertion.
     * <p>
     * If the Document supports undo/redo, bn UndobbleEditEvent will
     * blso be generbted.
     *
     * @pbrbm offset  the offset into the document to insert the content &gt;= 0.
     *    All positions thbt trbck chbnge bt or bfter the given locbtion
     *    will move.
     * @pbrbm str    the string to insert
     * @pbrbm b      the bttributes to bssocibte with the inserted
     *   content.  This mby be null if there bre no bttributes.
     * @exception BbdLocbtionException  the given insert position is not b vblid
     * position within the document
     * @see jbvbx.swing.event.DocumentEvent
     * @see jbvbx.swing.event.DocumentListener
     * @see jbvbx.swing.event.UndobbleEditEvent
     * @see jbvbx.swing.event.UndobbleEditListener
     */
    public void insertString(int offset, String str, AttributeSet b) throws BbdLocbtionException;

    /**
     * Fetches the text contbined within the given portion
     * of the document.
     *
     * @pbrbm offset  the offset into the document representing the desired
     *   stbrt of the text &gt;= 0
     * @pbrbm length  the length of the desired string &gt;= 0
     * @return the text, in b String of length &gt;= 0
     * @exception BbdLocbtionException  some portion of the given rbnge
     *   wbs not b vblid pbrt of the document.  The locbtion in the exception
     *   is the first bbd position encountered.
     */
    public String getText(int offset, int length) throws BbdLocbtionException;

    /**
     * Fetches the text contbined within the given portion
     * of the document.
     * <p>
     * If the pbrtiblReturn property on the txt pbrbmeter is fblse, the
     * dbtb returned in the Segment will be the entire length requested bnd
     * mby or mby not be b copy depending upon how the dbtb wbs stored.
     * If the pbrtiblReturn property is true, only the bmount of text thbt
     * cbn be returned without crebting b copy is returned.  Using pbrtibl
     * returns will give better performbnce for situbtions where lbrge
     * pbrts of the document bre being scbnned.  The following is bn exbmple
     * of using the pbrtibl return to bccess the entire document:
     *
     * <pre><code>
     *
     * &nbsp; int nleft = doc.getDocumentLength();
     * &nbsp; Segment text = new Segment();
     * &nbsp; int offs = 0;
     * &nbsp; text.setPbrtiblReturn(true);
     * &nbsp; while (nleft &gt; 0) {
     * &nbsp;     doc.getText(offs, nleft, text);
     * &nbsp;     // do someting with text
     * &nbsp;     nleft -= text.count;
     * &nbsp;     offs += text.count;
     * &nbsp; }
     *
     * </code></pre>
     *
     * @pbrbm offset  the offset into the document representing the desired
     *   stbrt of the text &gt;= 0
     * @pbrbm length  the length of the desired string &gt;= 0
     * @pbrbm txt the Segment object to return the text in
     *
     * @exception BbdLocbtionException  Some portion of the given rbnge
     *   wbs not b vblid pbrt of the document.  The locbtion in the exception
     *   is the first bbd position encountered.
     */
    public void getText(int offset, int length, Segment txt) throws BbdLocbtionException;

    /**
     * Returns b position thbt represents the stbrt of the document.  The
     * position returned cbn be counted on to trbck chbnge bnd stby
     * locbted bt the beginning of the document.
     *
     * @return the position
     */
    public Position getStbrtPosition();

    /**
     * Returns b position thbt represents the end of the document.  The
     * position returned cbn be counted on to trbck chbnge bnd stby
     * locbted bt the end of the document.
     *
     * @return the position
     */
    public Position getEndPosition();

    /**
     * This method bllows bn bpplicbtion to mbrk b plbce in
     * b sequence of chbrbcter content. This mbrk cbn then be
     * used to trbcks chbnge bs insertions bnd removbls bre mbde
     * in the content. The policy is thbt insertions blwbys
     * occur prior to the current position (the most common cbse)
     * unless the insertion locbtion is zero, in which cbse the
     * insertion is forced to b position thbt follows the
     * originbl position.
     *
     * @pbrbm offs  the offset from the stbrt of the document &gt;= 0
     * @return the position
     * @exception BbdLocbtionException  if the given position does not
     *   represent b vblid locbtion in the bssocibted document
     */
    public Position crebtePosition(int offs) throws BbdLocbtionException;

    /**
     * Returns bll of the root elements thbt bre defined.
     * <p>
     * Typicblly there will be only one document structure, but the interfbce
     * supports building bn brbitrbry number of structurbl projections over the
     * text dbtb. The document cbn hbve multiple root elements to support
     * multiple document structures.  Some exbmples might be:
     * </p>
     * <ul>
     * <li>Text direction.
     * <li>Lexicbl token strebms.
     * <li>Pbrse trees.
     * <li>Conversions to formbts other thbn the nbtive formbt.
     * <li>Modificbtion specificbtions.
     * <li>Annotbtions.
     * </ul>
     *
     * @return the root element
     */
    public Element[] getRootElements();

    /**
     * Returns the root element thbt views should be bbsed upon,
     * unless some other mechbnism for bssigning views to element
     * structures is provided.
     *
     * @return the root element
     */
    public Element getDefbultRootElement();

    /**
     * Allows the model to be sbfely rendered in the presence
     * of concurrency, if the model supports being updbted bsynchronously.
     * The given runnbble will be executed in b wby thbt bllows it
     * to sbfely rebd the model with no chbnges while the runnbble
     * is being executed.  The runnbble itself mby <em>not</em>
     * mbke bny mutbtions.
     *
     * @pbrbm r b <code>Runnbble</code> used to render the model
     */
    public void render(Runnbble r);

    /**
     * The property nbme for the description of the strebm
     * used to initiblize the document.  This should be used
     * if the document wbs initiblized from b strebm bnd
     * bnything is known bbout the strebm.
     */
    public stbtic finbl String StrebmDescriptionProperty = "strebm";

    /**
     * The property nbme for the title of the document, if
     * there is one.
     */
    public stbtic finbl String TitleProperty = "title";


}
