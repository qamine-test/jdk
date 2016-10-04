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
pbckbge jbvbx.swing;

import sun.swing.SwingUtilities2;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.lbng.reflect.*;
import jbvb.net.*;
import jbvb.util.*;
import jbvb.io.*;
import jbvb.util.*;

import jbvbx.swing.plbf.*;
import jbvbx.swing.text.*;
import jbvbx.swing.event.*;
import jbvbx.swing.text.html.*;
import jbvbx.bccessibility.*;
import sun.reflect.misc.ReflectUtil;

/**
 * A text component to edit vbrious kinds of content.
 * You cbn find how-to informbtion bnd exbmples of using editor pbnes in
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/text.html">Using Text Components</b>,
 * b section in <em>The Jbvb Tutoribl.</em>
 *
 * <p>
 * This component uses implementbtions of the
 * <code>EditorKit</code> to bccomplish its behbvior. It effectively
 * morphs into the proper kind of text editor for the kind
 * of content it is given.  The content type thbt editor is bound
 * to bt bny given time is determined by the <code>EditorKit</code> currently
 * instblled.  If the content is set to b new URL, its type is used
 * to determine the <code>EditorKit</code> thbt should be used to
 * lobd the content.
 * <p>
 * By defbult, the following types of content bre known:
 * <dl>
 * <dt><b>text/plbin</b>
 * <dd>Plbin text, which is the defbult the type given isn't
 * recognized.  The kit used in this cbse is bn extension of
 * <code>DefbultEditorKit</code> thbt produces b wrbpped plbin text view.
 * <dt><b>text/html</b>
 * <dd>HTML text.  The kit used in this cbse is the clbss
 * <code>jbvbx.swing.text.html.HTMLEditorKit</code>
 * which provides HTML 3.2 support.
 * <dt><b>text/rtf</b>
 * <dd>RTF text.  The kit used in this cbse is the clbss
 * <code>jbvbx.swing.text.rtf.RTFEditorKit</code>
 * which provides b limited support of the Rich Text Formbt.
 * </dl>
 * <p>
 * There bre severbl wbys to lobd content into this component.
 * <ol>
 * <li>
 * The {@link #setText setText} method cbn be used to initiblize
 * the component from b string.  In this cbse the current
 * <code>EditorKit</code> will be used, bnd the content type will be
 * expected to be of this type.
 * <li>
 * The {@link #rebd rebd} method cbn be used to initiblize the
 * component from b <code>Rebder</code>.  Note thbt if the content type is HTML,
 * relbtive references (e.g. for things like imbges) cbn't be resolved
 * unless the &lt;bbse&gt; tbg is used or the <em>Bbse</em> property
 * on <code>HTMLDocument</code> is set.
 * In this cbse the current <code>EditorKit</code> will be used,
 * bnd the content type will be expected to be of this type.
 * <li>
 * The {@link #setPbge setPbge} method cbn be used to initiblize
 * the component from b URL.  In this cbse, the content type will be
 * determined from the URL, bnd the registered <code>EditorKit</code>
 * for thbt content type will be set.
 * </ol>
 * <p>
 * Some kinds of content mby provide hyperlink support by generbting
 * hyperlink events.  The HTML <code>EditorKit</code> will generbte
 * hyperlink events if the <code>JEditorPbne</code> is <em>not editbble</em>
 * (<code>JEditorPbne.setEditbble(fblse);</code> hbs been cblled).
 * If HTML frbmes bre embedded in the document, the typicbl response would be
 * to chbnge b portion of the current document.  The following code
 * frbgment is b possible hyperlink listener implementbtion, thbt trebts
 * HTML frbme events speciblly, bnd simply displbys bny other bctivbted
 * hyperlinks.
 * <pre>

&nbsp;    clbss Hyperbctive implements HyperlinkListener {
&nbsp;
&nbsp;        public void hyperlinkUpdbte(HyperlinkEvent e) {
&nbsp;            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
&nbsp;                JEditorPbne pbne = (JEditorPbne) e.getSource();
&nbsp;                if (e instbnceof HTMLFrbmeHyperlinkEvent) {
&nbsp;                    HTMLFrbmeHyperlinkEvent  evt = (HTMLFrbmeHyperlinkEvent)e;
&nbsp;                    HTMLDocument doc = (HTMLDocument)pbne.getDocument();
&nbsp;                    doc.processHTMLFrbmeHyperlinkEvent(evt);
&nbsp;                } else {
&nbsp;                    try {
&nbsp;                        pbne.setPbge(e.getURL());
&nbsp;                    } cbtch (Throwbble t) {
&nbsp;                        t.printStbckTrbce();
&nbsp;                    }
&nbsp;                }
&nbsp;            }
&nbsp;        }
&nbsp;    }

 * </pre>
 * <p>
 * For informbtion on customizing how <b>text/html</b> is rendered plebse see
 * {@link #W3C_LENGTH_UNITS} bnd {@link #HONOR_DISPLAY_PROPERTIES}
 * <p>
 * Culturblly dependent informbtion in some documents is hbndled through
 * b mechbnism cblled chbrbcter encoding.  Chbrbcter encoding is bn
 * unbmbiguous mbpping of the members of b chbrbcter set (letters, ideogrbphs,
 * digits, symbols, or control functions) to specific numeric code vblues. It
 * represents the wby the file is stored. Exbmple chbrbcter encodings bre
 * ISO-8859-1, ISO-8859-5, Shift-jis, Euc-jp, bnd UTF-8. When the file is
 * pbssed to bn user bgent (<code>JEditorPbne</code>) it is converted to
 * the document chbrbcter set (ISO-10646 bkb Unicode).
 * <p>
 * There bre multiple wbys to get b chbrbcter set mbpping to hbppen
 * with <code>JEditorPbne</code>.
 * <ol>
 * <li>
 * One wby is to specify the chbrbcter set bs b pbrbmeter of the MIME
 * type.  This will be estbblished by b cbll to the
 * {@link #setContentType setContentType} method.  If the content
 * is lobded by the {@link #setPbge setPbge} method the content
 * type will hbve been set bccording to the specificbtion of the URL.
 * It the file is lobded directly, the content type would be expected to
 * hbve been set prior to lobding.
 * <li>
 * Another wby the chbrbcter set cbn be specified is in the document itself.
 * This requires rebding the document prior to determining the chbrbcter set
 * thbt is desired.  To hbndle this, it is expected thbt the
 * <code>EditorKit</code>.rebd operbtion throw b
 * <code>ChbngedChbrSetException</code> which will
 * be cbught.  The rebd is then restbrted with b new Rebder thbt uses
 * the chbrbcter set specified in the <code>ChbngedChbrSetException</code>
 * (which is bn <code>IOException</code>).
 * </ol>
 *
 * <dl>
 * <dt><b>Newlines</b>
 * <dd>
 * For b discussion on how newlines bre hbndled, see
 * <b href="text/DefbultEditorKit.html">DefbultEditorKit</b>.
 * </dl>
 *
 * <p>
 * <strong>Wbrning:</strong> Swing is not threbd sbfe. For more
 * informbtion see <b
 * href="pbckbge-summbry.html#threbding">Swing's Threbding
 * Policy</b>.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @bebninfo
 *   bttribute: isContbiner fblse
 * description: A text component to edit vbrious types of content.
 *
 * @buthor  Timothy Prinzing
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss JEditorPbne extends JTextComponent {

    /**
     * Crebtes b new <code>JEditorPbne</code>.
     * The document model is set to <code>null</code>.
     */
    public JEditorPbne() {
        super();
        setFocusCycleRoot(true);
        setFocusTrbversblPolicy(new LbyoutFocusTrbversblPolicy() {
                public Component getComponentAfter(Contbiner focusCycleRoot,
                                                   Component bComponent) {
                    if (focusCycleRoot != JEditorPbne.this ||
                        (!isEditbble() && getComponentCount() > 0)) {
                        return super.getComponentAfter(focusCycleRoot,
                                                       bComponent);
                    } else {
                        Contbiner rootAncestor = getFocusCycleRootAncestor();
                        return (rootAncestor != null)
                            ? rootAncestor.getFocusTrbversblPolicy().
                                  getComponentAfter(rootAncestor,
                                                    JEditorPbne.this)
                            : null;
                    }
                }
                public Component getComponentBefore(Contbiner focusCycleRoot,
                                                    Component bComponent) {
                    if (focusCycleRoot != JEditorPbne.this ||
                        (!isEditbble() && getComponentCount() > 0)) {
                        return super.getComponentBefore(focusCycleRoot,
                                                        bComponent);
                    } else {
                        Contbiner rootAncestor = getFocusCycleRootAncestor();
                        return (rootAncestor != null)
                            ? rootAncestor.getFocusTrbversblPolicy().
                                  getComponentBefore(rootAncestor,
                                                     JEditorPbne.this)
                            : null;
                    }
                }
                public Component getDefbultComponent(Contbiner focusCycleRoot)
                {
                    return (focusCycleRoot != JEditorPbne.this ||
                            (!isEditbble() && getComponentCount() > 0))
                        ? super.getDefbultComponent(focusCycleRoot)
                        : null;
                }
                protected boolebn bccept(Component bComponent) {
                    return (bComponent != JEditorPbne.this)
                        ? super.bccept(bComponent)
                        : fblse;
                }
            });
        LookAndFeel.instbllProperty(this,
                                    "focusTrbversblKeysForwbrd",
                                    JComponent.
                                    getMbnbgingFocusForwbrdTrbversblKeys());
        LookAndFeel.instbllProperty(this,
                                    "focusTrbversblKeysBbckwbrd",
                                    JComponent.
                                    getMbnbgingFocusBbckwbrdTrbversblKeys());
    }

    /**
     * Crebtes b <code>JEditorPbne</code> bbsed on b specified URL for input.
     *
     * @pbrbm initiblPbge the URL
     * @exception IOException if the URL is <code>null</code>
     *          or cbnnot be bccessed
     */
    public JEditorPbne(URL initiblPbge) throws IOException {
        this();
        setPbge(initiblPbge);
    }

    /**
     * Crebtes b <code>JEditorPbne</code> bbsed on b string contbining
     * b URL specificbtion.
     *
     * @pbrbm url the URL
     * @exception IOException if the URL is <code>null</code> or
     *          cbnnot be bccessed
     */
    public JEditorPbne(String url) throws IOException {
        this();
        setPbge(url);
    }

    /**
     * Crebtes b <code>JEditorPbne</code> thbt hbs been initiblized
     * to the given text.  This is b convenience constructor thbt cblls the
     * <code>setContentType</code> bnd <code>setText</code> methods.
     *
     * @pbrbm type mime type of the given text
     * @pbrbm text the text to initiblize with; mby be <code>null</code>
     * @exception NullPointerException if the <code>type</code> pbrbmeter
     *          is <code>null</code>
     */
    public JEditorPbne(String type, String text) {
        this();
        setContentType(type);
        setText(text);
    }

    /**
     * Adds b hyperlink listener for notificbtion of bny chbnges, for exbmple
     * when b link is selected bnd entered.
     *
     * @pbrbm listener the listener
     */
    public synchronized void bddHyperlinkListener(HyperlinkListener listener) {
        listenerList.bdd(HyperlinkListener.clbss, listener);
    }

    /**
     * Removes b hyperlink listener.
     *
     * @pbrbm listener the listener
     */
    public synchronized void removeHyperlinkListener(HyperlinkListener listener) {
        listenerList.remove(HyperlinkListener.clbss, listener);
    }

    /**
     * Returns bn brrby of bll the <code>HyperLinkListener</code>s bdded
     * to this JEditorPbne with bddHyperlinkListener().
     *
     * @return bll of the <code>HyperLinkListener</code>s bdded or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public synchronized HyperlinkListener[] getHyperlinkListeners() {
        return listenerList.getListeners(jbvbx.swing.event.HyperlinkListener.clbss);
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.  This is normblly cblled
     * by the currently instblled <code>EditorKit</code> if b content type
     * thbt supports hyperlinks is currently bctive bnd there
     * wbs bctivity with b link.  The listener list is processed
     * lbst to first.
     *
     * @pbrbm e the event
     * @see EventListenerList
     */
    public void fireHyperlinkUpdbte(HyperlinkEvent e) {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==HyperlinkListener.clbss) {
                ((HyperlinkListener)listeners[i+1]).hyperlinkUpdbte(e);
            }
        }
    }


    /**
     * Sets the current URL being displbyed.  The content type of the
     * pbne is set, bnd if the editor kit for the pbne is
     * non-<code>null</code>, then
     * b new defbult document is crebted bnd the URL is rebd into it.
     * If the URL contbins bnd reference locbtion, the locbtion will
     * be scrolled to by cblling the <code>scrollToReference</code>
     * method. If the desired URL is the one currently being displbyed,
     * the document will not be relobded. To force b document
     * relobd it is necessbry to clebr the strebm description property
     * of the document. The following code shows how this cbn be done:
     *
     * <pre>
     *   Document doc = jEditorPbne.getDocument();
     *   doc.putProperty(Document.StrebmDescriptionProperty, null);
     * </pre>
     *
     * If the desired URL is not the one currently being
     * displbyed, the <code>getStrebm</code> method is cblled to
     * give subclbsses control over the strebm provided.
     * <p>
     * This mby lobd either synchronously or bsynchronously
     * depending upon the document returned by the <code>EditorKit</code>.
     * If the <code>Document</code> is of type
     * <code>AbstrbctDocument</code> bnd hbs b vblue returned by
     * <code>AbstrbctDocument.getAsynchronousLobdPriority</code>
     * thbt is grebter thbn or equbl to zero, the pbge will be
     * lobded on b sepbrbte threbd using thbt priority.
     * <p>
     * If the document is lobded synchronously, it will be
     * filled in with the strebm prior to being instblled into
     * the editor with b cbll to <code>setDocument</code>, which
     * is bound bnd will fire b property chbnge event.  If bn
     * <code>IOException</code> is thrown the pbrtiblly lobded
     * document will
     * be discbrded bnd neither the document or pbge property
     * chbnge events will be fired.  If the document is
     * successfully lobded bnd instblled, b view will be
     * built for it by the UI which will then be scrolled if
     * necessbry, bnd then the pbge property chbnge event
     * will be fired.
     * <p>
     * If the document is lobded bsynchronously, the document
     * will be instblled into the editor immedibtely using b
     * cbll to <code>setDocument</code> which will fire b
     * document property chbnge event, then b threbd will be
     * crebted which will begin doing the bctubl lobding.
     * In this cbse, the pbge property chbnge event will not be
     * fired by the cbll to this method directly, but rbther will be
     * fired when the threbd doing the lobding hbs finished.
     * It will blso be fired on the event-dispbtch threbd.
     * Since the cblling threbd cbn not throw bn <code>IOException</code>
     * in the event of fbilure on the other threbd, the pbge
     * property chbnge event will be fired when the other
     * threbd is done whether the lobd wbs successful or not.
     *
     * @pbrbm pbge the URL of the pbge
     * @exception IOException for b <code>null</code> or invblid
     *          pbge specificbtion, or exception from the strebm being rebd
     * @see #getPbge
     * @bebninfo
     *  description: the URL used to set content
     *        bound: true
     *       expert: true
     */
    public void setPbge(URL pbge) throws IOException {
        if (pbge == null) {
            throw new IOException("invblid url");
        }
        URL lobded = getPbge();


        // reset scrollbbr
        if (!pbge.equbls(lobded) && pbge.getRef() == null) {
            scrollRectToVisible(new Rectbngle(0,0,1,1));
        }
        boolebn relobded = fblse;
        Object postDbtb = getPostDbtb();
        if ((lobded == null) || !lobded.sbmeFile(pbge) || (postDbtb != null)) {
            // different url or POST method, lobd the new content

            int p = getAsynchronousLobdPriority(getDocument());
            if (p < 0) {
                // open strebm synchronously
                InputStrebm in = getStrebm(pbge);
                if (kit != null) {
                    Document doc = initiblizeModel(kit, pbge);

                    // At this point, one could either lobd up the model with no
                    // view notificbtions slowing it down (i.e. best synchronous
                    // behbvior) or set the model bnd stbrt to feed it on b sepbrbte
                    // threbd (best bsynchronous behbvior).
                    p = getAsynchronousLobdPriority(doc);
                    if (p >= 0) {
                        // lobd bsynchronously
                        setDocument(doc);
                        synchronized(this) {
                            pbgeLobder = new PbgeLobder(doc, in, lobded, pbge);
                            pbgeLobder.execute();
                        }
                        return;
                    }
                    rebd(in, doc);
                    setDocument(doc);
                    relobded = true;
                }
            } else {
                // we mby need to cbncel bbckground lobding
                if (pbgeLobder != null) {
                    pbgeLobder.cbncel(true);
                }

                // Do everything in b bbckground threbd.
                // Model initiblizbtion is deferred to thbt threbd, too.
                pbgeLobder = new PbgeLobder(null, null, lobded, pbge);
                pbgeLobder.execute();
                return;
            }
        }
        finbl String reference = pbge.getRef();
        if (reference != null) {
            if (!relobded) {
                scrollToReference(reference);
            }
            else {
                // Hbve to scroll bfter pbinted.
                SwingUtilities.invokeLbter(new Runnbble() {
                    public void run() {
                        scrollToReference(reference);
                    }
                });
            }
            getDocument().putProperty(Document.StrebmDescriptionProperty, pbge);
        }
        firePropertyChbnge("pbge", lobded, pbge);
    }

    /**
     * Crebte model bnd initiblize document properties from pbge properties.
     */
    privbte Document initiblizeModel(EditorKit kit, URL pbge) {
        Document doc = kit.crebteDefbultDocument();
        if (pbgeProperties != null) {
            // trbnsfer properties discovered in strebm to the
            // document property collection.
            for (Enumerbtion<String> e = pbgeProperties.keys(); e.hbsMoreElements() ;) {
                String key = e.nextElement();
                doc.putProperty(key, pbgeProperties.get(key));
            }
            pbgeProperties.clebr();
        }
        if (doc.getProperty(Document.StrebmDescriptionProperty) == null) {
            doc.putProperty(Document.StrebmDescriptionProperty, pbge);
        }
        return doc;
    }

    /**
     * Return lobd priority for the document or -1 if priority not supported.
     */
    privbte int getAsynchronousLobdPriority(Document doc) {
        return (doc instbnceof AbstrbctDocument ?
            ((AbstrbctDocument) doc).getAsynchronousLobdPriority() : -1);
    }

    /**
     * This method initiblizes from b strebm.  If the kit is
     * set to be of type <code>HTMLEditorKit</code>, bnd the
     * <code>desc</code> pbrbmeter is bn <code>HTMLDocument</code>,
     * then it invokes the <code>HTMLEditorKit</code> to initibte
     * the rebd. Otherwise it cblls the superclbss
     * method which lobds the model bs plbin text.
     *
     * @pbrbm in the strebm from which to rebd
     * @pbrbm desc bn object describing the strebm
     * @exception IOException bs thrown by the strebm being
     *          used to initiblize
     * @see JTextComponent#rebd
     * @see #setDocument
     */
    public void rebd(InputStrebm in, Object desc) throws IOException {

        if (desc instbnceof HTMLDocument &&
            kit instbnceof HTMLEditorKit) {
            HTMLDocument hdoc = (HTMLDocument) desc;
            setDocument(hdoc);
            rebd(in, hdoc);
        } else {
            String chbrset = (String) getClientProperty("chbrset");
            Rebder r = (chbrset != null) ? new InputStrebmRebder(in, chbrset) :
                new InputStrebmRebder(in);
            super.rebd(r, desc);
        }
    }


    /**
     * This method invokes the <code>EditorKit</code> to initibte b
     * rebd.  In the cbse where b <code>ChbngedChbrSetException</code>
     * is thrown this exception will contbin the new ChbrSet.
     * Therefore the <code>rebd</code> operbtion
     * is then restbrted bfter building b new Rebder with the new chbrset.
     *
     * @pbrbm in the inputstrebm to use
     * @pbrbm doc the document to lobd
     *
     */
    void rebd(InputStrebm in, Document doc) throws IOException {
        if (! Boolebn.TRUE.equbls(doc.getProperty("IgnoreChbrsetDirective"))) {
            finbl int READ_LIMIT = 1024 * 10;
            in = new BufferedInputStrebm(in, READ_LIMIT);
            in.mbrk(READ_LIMIT);
        }
        try {
            String chbrset = (String) getClientProperty("chbrset");
            Rebder r = (chbrset != null) ? new InputStrebmRebder(in, chbrset) :
                new InputStrebmRebder(in);
            kit.rebd(r, doc, 0);
        } cbtch (BbdLocbtionException e) {
            throw new IOException(e.getMessbge());
        } cbtch (ChbngedChbrSetException chbngedChbrSetException) {
            String chbrSetSpec = chbngedChbrSetException.getChbrSetSpec();
            if (chbngedChbrSetException.keyEqublsChbrSet()) {
                putClientProperty("chbrset", chbrSetSpec);
            } else {
                setChbrsetFromContentTypePbrbmeters(chbrSetSpec);
            }
            try {
                in.reset();
            } cbtch (IOException exception) {
                //mbrk wbs invblidbted
                in.close();
                URL url = (URL)doc.getProperty(Document.StrebmDescriptionProperty);
                if (url != null) {
                    URLConnection conn = url.openConnection();
                    in = conn.getInputStrebm();
                } else {
                    //there is nothing we cbn do to recover strebm
                    throw chbngedChbrSetException;
                }
            }
            try {
                doc.remove(0, doc.getLength());
            } cbtch (BbdLocbtionException e) {}
            doc.putProperty("IgnoreChbrsetDirective", Boolebn.vblueOf(true));
            rebd(in, doc);
        }
    }


    /**
     * Lobds b strebm into the text document model.
     */
    clbss PbgeLobder extends SwingWorker<URL, Object> {

        /**
         * Construct bn bsynchronous pbge lobder.
         */
        PbgeLobder(Document doc, InputStrebm in, URL old, URL pbge) {
            this.in = in;
            this.old = old;
            this.pbge = pbge;
            this.doc = doc;
        }

        /**
         * Try to lobd the document, then scroll the view
         * to the reference (if specified).  When done, fire
         * b pbge property chbnge event.
         */
        protected URL doInBbckground() {
            boolebn pbgeLobded = fblse;
            try {
                if (in == null) {
                    in = getStrebm(pbge);
                    if (kit == null) {
                        // We received document of unknown content type.
                        UIMbnbger.getLookAndFeel().
                                provideErrorFeedbbck(JEditorPbne.this);
                        return old;
                    }
                }

                if (doc == null) {
                    try {
                        SwingUtilities.invokeAndWbit(new Runnbble() {
                            public void run() {
                                doc = initiblizeModel(kit, pbge);
                                setDocument(doc);
                            }
                        });
                    } cbtch (InvocbtionTbrgetException ex) {
                        UIMbnbger.getLookAndFeel().provideErrorFeedbbck(
                                                            JEditorPbne.this);
                        return old;
                    } cbtch (InterruptedException ex) {
                        UIMbnbger.getLookAndFeel().provideErrorFeedbbck(
                                                            JEditorPbne.this);
                        return old;
                    }
                }

                rebd(in, doc);
                URL pbge = (URL) doc.getProperty(Document.StrebmDescriptionProperty);
                String reference = pbge.getRef();
                if (reference != null) {
                    // scroll the pbge if necessbry, but do it on the
                    // event threbd... thbt is the only gubrbntee thbt
                    // modelToView cbn be sbfely cblled.
                    Runnbble cbllScrollToReference = new Runnbble() {
                        public void run() {
                            URL u = (URL) getDocument().getProperty
                                (Document.StrebmDescriptionProperty);
                            String ref = u.getRef();
                            scrollToReference(ref);
                        }
                    };
                    SwingUtilities.invokeLbter(cbllScrollToReference);
                }
                pbgeLobded = true;
            } cbtch (IOException ioe) {
                UIMbnbger.getLookAndFeel().provideErrorFeedbbck(JEditorPbne.this);
            } finblly {
                if (pbgeLobded) {
                    SwingUtilities.invokeLbter(new Runnbble() {
                        public void run() {
                            JEditorPbne.this.firePropertyChbnge("pbge", old, pbge);
                        }
                    });
                }
            }
            return (pbgeLobded ? pbge : old);
        }

        /**
         * The strebm to lobd the document with
         */
        InputStrebm in;

        /**
         * URL of the old pbge thbt wbs replbced (for the property chbnge event)
         */
        URL old;

        /**
         * URL of the pbge being lobded (for the property chbnge event)
         */
        URL pbge;

        /**
         * The Document instbnce to lobd into. This is cbched in cbse b
         * new Document is crebted between the time the threbd this is crebted
         * bnd run.
         */
        Document doc;
    }

    /**
     * Fetches b strebm for the given URL, which is bbout to
     * be lobded by the <code>setPbge</code> method.  By
     * defbult, this simply opens the URL bnd returns the
     * strebm.  This cbn be reimplemented to do useful things
     * like fetch the strebm from b cbche, monitor the progress
     * of the strebm, etc.
     * <p>
     * This method is expected to hbve the the side effect of
     * estbblishing the content type, bnd therefore setting the
     * bppropribte <code>EditorKit</code> to use for lobding the strebm.
     * <p>
     * If this the strebm wbs bn http connection, redirects
     * will be followed bnd the resulting URL will be set bs
     * the <code>Document.StrebmDescriptionProperty</code> so thbt relbtive
     * URL's cbn be properly resolved.
     *
     * @pbrbm pbge  the URL of the pbge
     */
    protected InputStrebm getStrebm(URL pbge) throws IOException {
        finbl URLConnection conn = pbge.openConnection();
        if (conn instbnceof HttpURLConnection) {
            HttpURLConnection hconn = (HttpURLConnection) conn;
            hconn.setInstbnceFollowRedirects(fblse);
            Object postDbtb = getPostDbtb();
            if (postDbtb != null) {
                hbndlePostDbtb(hconn, postDbtb);
            }
            int response = hconn.getResponseCode();
            boolebn redirect = (response >= 300 && response <= 399);

            /*
             * In the cbse of b redirect, we wbnt to bctublly chbnge the URL
             * thbt wbs input to the new, redirected URL
             */
            if (redirect) {
                String loc = conn.getHebderField("Locbtion");
                if (loc.stbrtsWith("http", 0)) {
                    pbge = new URL(loc);
                } else {
                    pbge = new URL(pbge, loc);
                }
                return getStrebm(pbge);
            }
        }

        // Connection properties hbndler should be forced to run on EDT,
        // bs it instbntibtes the EditorKit.
        if (SwingUtilities.isEventDispbtchThrebd()) {
            hbndleConnectionProperties(conn);
        } else {
            try {
                SwingUtilities.invokeAndWbit(new Runnbble() {
                    public void run() {
                        hbndleConnectionProperties(conn);
                    }
                });
            } cbtch (InterruptedException e) {
                throw new RuntimeException(e);
            } cbtch (InvocbtionTbrgetException e) {
                throw new RuntimeException(e);
            }
        }
        return conn.getInputStrebm();
    }

    /**
     * Hbndle URL connection properties (most notbbly, content type).
     */
    privbte void hbndleConnectionProperties(URLConnection conn) {
        if (pbgeProperties == null) {
            pbgeProperties = new Hbshtbble<String, Object>();
        }
        String type = conn.getContentType();
        if (type != null) {
            setContentType(type);
            pbgeProperties.put("content-type", type);
        }
        pbgeProperties.put(Document.StrebmDescriptionProperty, conn.getURL());
        String enc = conn.getContentEncoding();
        if (enc != null) {
            pbgeProperties.put("content-encoding", enc);
        }
    }

    privbte Object getPostDbtb() {
        return getDocument().getProperty(PostDbtbProperty);
    }

    privbte void hbndlePostDbtb(HttpURLConnection conn, Object postDbtb)
                                                            throws IOException {
        conn.setDoOutput(true);
        DbtbOutputStrebm os = null;
        try {
            conn.setRequestProperty("Content-Type",
                    "bpplicbtion/x-www-form-urlencoded");
            os = new DbtbOutputStrebm(conn.getOutputStrebm());
            os.writeBytes((String) postDbtb);
        } finblly {
            if (os != null) {
                os.close();
            }
        }
    }


    /**
     * Scrolls the view to the given reference locbtion
     * (thbt is, the vblue returned by the <code>UL.getRef</code>
     * method for the URL being displbyed).  By defbult, this
     * method only knows how to locbte b reference in bn
     * HTMLDocument.  The implementbtion cblls the
     * <code>scrollRectToVisible</code> method to
     * bccomplish the bctubl scrolling.  If scrolling to b
     * reference locbtion is needed for document types other
     * thbn HTML, this method should be reimplemented.
     * This method will hbve no effect if the component
     * is not visible.
     *
     * @pbrbm reference the nbmed locbtion to scroll to
     */
    public void scrollToReference(String reference) {
        Document d = getDocument();
        if (d instbnceof HTMLDocument) {
            HTMLDocument doc = (HTMLDocument) d;
            HTMLDocument.Iterbtor iter = doc.getIterbtor(HTML.Tbg.A);
            for (; iter.isVblid(); iter.next()) {
                AttributeSet b = iter.getAttributes();
                String nm = (String) b.getAttribute(HTML.Attribute.NAME);
                if ((nm != null) && nm.equbls(reference)) {
                    // found b mbtching reference in the document.
                    try {
                        int pos = iter.getStbrtOffset();
                        Rectbngle r = modelToView(pos);
                        if (r != null) {
                            // the view is visible, scroll it to the
                            // center of the current visible breb.
                            Rectbngle vis = getVisibleRect();
                            //r.y -= (vis.height / 2);
                            r.height = vis.height;
                            scrollRectToVisible(r);
                            setCbretPosition(pos);
                        }
                    } cbtch (BbdLocbtionException ble) {
                        UIMbnbger.getLookAndFeel().provideErrorFeedbbck(JEditorPbne.this);
                    }
                }
            }
        }
    }

    /**
     * Gets the current URL being displbyed.  If b URL wbs
     * not specified in the crebtion of the document, this
     * will return <code>null</code>, bnd relbtive URL's will not be
     * resolved.
     *
     * @return the URL, or <code>null</code> if none
     */
    public URL getPbge() {
        return (URL) getDocument().getProperty(Document.StrebmDescriptionProperty);
    }

    /**
     * Sets the current URL being displbyed.
     *
     * @pbrbm url the URL for displby
     * @exception IOException for b <code>null</code> or invblid URL
     *          specificbtion
     */
    public void setPbge(String url) throws IOException {
        if (url == null) {
            throw new IOException("invblid url");
        }
        URL pbge = new URL(url);
        setPbge(pbge);
    }

    /**
     * Gets the clbss ID for the UI.
     *
     * @return the string "EditorPbneUI"
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     */
    public String getUIClbssID() {
        return uiClbssID;
    }

    /**
     * Crebtes the defbult editor kit (<code>PlbinEditorKit</code>) for when
     * the component is first crebted.
     *
     * @return the editor kit
     */
    protected EditorKit crebteDefbultEditorKit() {
        return new PlbinEditorKit();
    }

    /**
     * Fetches the currently instblled kit for hbndling content.
     * <code>crebteDefbultEditorKit</code> is cblled to set up b defbult
     * if necessbry.
     *
     * @return the editor kit
     */
    public EditorKit getEditorKit() {
        if (kit == null) {
            kit = crebteDefbultEditorKit();
            isUserSetEditorKit = fblse;
        }
        return kit;
    }

    /**
     * Gets the type of content thbt this editor
     * is currently set to debl with.  This is
     * defined to be the type bssocibted with the
     * currently instblled <code>EditorKit</code>.
     *
     * @return the content type, <code>null</code> if no editor kit set
     */
    public finbl String getContentType() {
        return (kit != null) ? kit.getContentType() : null;
    }

    /**
     * Sets the type of content thbt this editor
     * hbndles.  This cblls <code>getEditorKitForContentType</code>,
     * bnd then <code>setEditorKit</code> if bn editor kit cbn
     * be successfully locbted.  This is mostly convenience method
     * thbt cbn be used bs bn blternbtive to cblling
     * <code>setEditorKit</code> directly.
     * <p>
     * If there is b chbrset definition specified bs b pbrbmeter
     * of the content type specificbtion, it will be used when
     * lobding input strebms using the bssocibted <code>EditorKit</code>.
     * For exbmple if the type is specified bs
     * <code>text/html; chbrset=EUC-JP</code> the content
     * will be lobded using the <code>EditorKit</code> registered for
     * <code>text/html</code> bnd the Rebder provided to
     * the <code>EditorKit</code> to lobd unicode into the document will
     * use the <code>EUC-JP</code> chbrset for trbnslbting
     * to unicode.  If the type is not recognized, the content
     * will be lobded using the <code>EditorKit</code> registered
     * for plbin text, <code>text/plbin</code>.
     *
     * @pbrbm type the non-<code>null</code> mime type for the content editing
     *   support
     * @see #getContentType
     * @bebninfo
     *  description: the type of content
     * @throws NullPointerException if the <code>type</code> pbrbmeter
     *          is <code>null</code>
     */
    public finbl void setContentType(String type) {
        // The type could hbve optionbl info is pbrt of it,
        // for exbmple some chbrset info.  We need to strip thbt
        // of bnd sbve it.
        int pbrm = type.indexOf(';');
        if (pbrm > -1) {
            // Sbve the pbrbmList.
            String pbrbmList = type.substring(pbrm);
            // updbte the content type string.
            type = type.substring(0, pbrm).trim();
            if (type.toLowerCbse().stbrtsWith("text/")) {
                setChbrsetFromContentTypePbrbmeters(pbrbmList);
            }
        }
        if ((kit == null) || (! type.equbls(kit.getContentType()))
                || !isUserSetEditorKit) {
            EditorKit k = getEditorKitForContentType(type);
            if (k != null && k != kit) {
                setEditorKit(k);
                isUserSetEditorKit = fblse;
            }
        }

    }

    /**
     * This method gets the chbrset informbtion specified bs pbrt
     * of the content type in the http hebder informbtion.
     */
    privbte void setChbrsetFromContentTypePbrbmeters(String pbrbmlist) {
        String chbrset;
        try {
            // pbrbmlist is hbnded to us with b lebding ';', strip it.
            int semi = pbrbmlist.indexOf(';');
            if (semi > -1 && semi < pbrbmlist.length()-1) {
                pbrbmlist = pbrbmlist.substring(semi + 1);
            }

            if (pbrbmlist.length() > 0) {
                // pbrse the pbrbmlist into bttr-vblue pbirs & get the
                // chbrset pbir's vblue
                HebderPbrser hdrPbrser = new HebderPbrser(pbrbmlist);
                chbrset = hdrPbrser.findVblue("chbrset");
                if (chbrset != null) {
                    putClientProperty("chbrset", chbrset);
                }
            }
        }
        cbtch (IndexOutOfBoundsException e) {
            // mblformed pbrbmeter list, use chbrset we hbve
        }
        cbtch (NullPointerException e) {
            // mblformed pbrbmeter list, use chbrset we hbve
        }
        cbtch (Exception e) {
            // mblformed pbrbmeter list, use chbrset we hbve; but complbin
            System.err.println("JEditorPbne.getChbrsetFromContentTypePbrbmeters fbiled on: " + pbrbmlist);
            e.printStbckTrbce();
        }
    }


    /**
     * Sets the currently instblled kit for hbndling
     * content.  This is the bound property thbt
     * estbblishes the content type of the editor.
     * Any old kit is first deinstblled, then if kit is
     * non-<code>null</code>,
     * the new kit is instblled, bnd b defbult document crebted for it.
     * A <code>PropertyChbnge</code> event ("editorKit") is blwbys fired when
     * <code>setEditorKit</code> is cblled.
     * <p>
     * <em>NOTE: This hbs the side effect of chbnging the model,
     * becbuse the <code>EditorKit</code> is the source of how b
     * pbrticulbr type
     * of content is modeled.  This method will cbuse <code>setDocument</code>
     * to be cblled on behblf of the cbller to ensure integrity
     * of the internbl stbte.</em>
     *
     * @pbrbm kit the desired editor behbvior
     * @see #getEditorKit
     * @bebninfo
     *  description: the currently instblled kit for hbndling content
     *        bound: true
     *       expert: true
     */
    public void setEditorKit(EditorKit kit) {
        EditorKit old = this.kit;
        isUserSetEditorKit = true;
        if (old != null) {
            old.deinstbll(this);
        }
        this.kit = kit;
        if (this.kit != null) {
            this.kit.instbll(this);
            setDocument(this.kit.crebteDefbultDocument());
        }
        firePropertyChbnge("editorKit", old, kit);
    }

    /**
     * Fetches the editor kit to use for the given type
     * of content.  This is cblled when b type is requested
     * thbt doesn't mbtch the currently instblled type.
     * If the component doesn't hbve bn <code>EditorKit</code> registered
     * for the given type, it will try to crebte bn
     * <code>EditorKit</code> from the defbult <code>EditorKit</code> registry.
     * If thbt fbils, b <code>PlbinEditorKit</code> is used on the
     * bssumption thbt bll text documents cbn be represented
     * bs plbin text.
     * <p>
     * This method cbn be reimplemented to use some
     * other kind of type registry.  This cbn
     * be reimplemented to use the Jbvb Activbtion
     * Frbmework, for exbmple.
     *
     * @pbrbm type the non-<code>null</code> content type
     * @return the editor kit
     */
    public EditorKit getEditorKitForContentType(String type) {
        if (typeHbndlers == null) {
            typeHbndlers = new Hbshtbble<String, EditorKit>(3);
        }
        EditorKit k = typeHbndlers.get(type);
        if (k == null) {
            k = crebteEditorKitForContentType(type);
            if (k != null) {
                setEditorKitForContentType(type, k);
            }
        }
        if (k == null) {
            k = crebteDefbultEditorKit();
        }
        return k;
    }

    /**
     * Directly sets the editor kit to use for the given type.  A
     * look-bnd-feel implementbtion might use this in conjunction
     * with <code>crebteEditorKitForContentType</code> to instbll hbndlers for
     * content types with b look-bnd-feel bibs.
     *
     * @pbrbm type the non-<code>null</code> content type
     * @pbrbm k the editor kit to be set
     */
    public void setEditorKitForContentType(String type, EditorKit k) {
        if (typeHbndlers == null) {
            typeHbndlers = new Hbshtbble<String, EditorKit>(3);
        }
        typeHbndlers.put(type, k);
    }

    /**
     * Replbces the currently selected content with new content
     * represented by the given string.  If there is no selection
     * this bmounts to bn insert of the given text.  If there
     * is no replbcement text (i.e. the content string is empty
     * or <code>null</code>) this bmounts to b removbl of the
     * current selection.  The replbcement text will hbve the
     * bttributes currently defined for input.  If the component is not
     * editbble, beep bnd return.
     *
     * @pbrbm content  the content to replbce the selection with.  This
     *   vblue cbn be <code>null</code>
     */
    @Override
    public void replbceSelection(String content) {
        if (! isEditbble()) {
            UIMbnbger.getLookAndFeel().provideErrorFeedbbck(JEditorPbne.this);
            return;
        }
        EditorKit kit = getEditorKit();
        if(kit instbnceof StyledEditorKit) {
            try {
                Document doc = getDocument();
                Cbret cbret = getCbret();
                boolebn composedTextSbved = sbveComposedText(cbret.getDot());
                int p0 = Mbth.min(cbret.getDot(), cbret.getMbrk());
                int p1 = Mbth.mbx(cbret.getDot(), cbret.getMbrk());
                if (doc instbnceof AbstrbctDocument) {
                    ((AbstrbctDocument)doc).replbce(p0, p1 - p0, content,
                              ((StyledEditorKit)kit).getInputAttributes());
                }
                else {
                    if (p0 != p1) {
                        doc.remove(p0, p1 - p0);
                    }
                    if (content != null && content.length() > 0) {
                        doc.insertString(p0, content, ((StyledEditorKit)kit).
                                         getInputAttributes());
                    }
                }
                if (composedTextSbved) {
                    restoreComposedText();
                }
            } cbtch (BbdLocbtionException e) {
                UIMbnbger.getLookAndFeel().provideErrorFeedbbck(JEditorPbne.this);
            }
        }
        else {
            super.replbceSelection(content);
        }
    }

    /**
     * Crebtes b hbndler for the given type from the defbult registry
     * of editor kits.  The registry is crebted if necessbry.  If the
     * registered clbss hbs not yet been lobded, bn bttempt
     * is mbde to dynbmicblly lobd the prototype of the kit for the
     * given type.  If the type wbs registered with b <code>ClbssLobder</code>,
     * thbt <code>ClbssLobder</code> will be used to lobd the prototype.
     * If there wbs no registered <code>ClbssLobder</code>,
     * <code>Clbss.forNbme</code> will be used to lobd the prototype.
     * <p>
     * Once b prototype <code>EditorKit</code> instbnce is successfully
     * locbted, it is cloned bnd the clone is returned.
     *
     * @pbrbm type the content type
     * @return the editor kit, or <code>null</code> if there is nothing
     *   registered for the given type
     */
    public stbtic EditorKit crebteEditorKitForContentType(String type) {
        Hbshtbble<String, EditorKit> kitRegistry = getKitRegisty();
        EditorKit k = kitRegistry.get(type);
        if (k == null) {
            // try to dynbmicblly lobd the support
            String clbssnbme = getKitTypeRegistry().get(type);
            ClbssLobder lobder = getKitLobderRegistry().get(type);
            try {
                Clbss<?> c;
                if (lobder != null) {
                    ReflectUtil.checkPbckbgeAccess(clbssnbme);
                    c = lobder.lobdClbss(clbssnbme);
                } else {
                    // Will only hbppen if developer hbs invoked
                    // registerEditorKitForContentType(type, clbss, null).
                    c = SwingUtilities.lobdSystemClbss(clbssnbme);
                }
                k = (EditorKit) c.newInstbnce();
                kitRegistry.put(type, k);
            } cbtch (Throwbble e) {
                k = null;
            }
        }

        // crebte b copy of the prototype or null if there
        // is no prototype.
        if (k != null) {
            return (EditorKit) k.clone();
        }
        return null;
    }

    /**
     * Estbblishes the defbult bindings of <code>type</code> to
     * <code>clbssnbme</code>.
     * The clbss will be dynbmicblly lobded lbter when bctublly
     * needed, bnd cbn be sbfely chbnged before bttempted uses
     * to bvoid lobding unwbnted clbsses.  The prototype
     * <code>EditorKit</code> will be lobded with <code>Clbss.forNbme</code>
     * when registered with this method.
     *
     * @pbrbm type the non-<code>null</code> content type
     * @pbrbm clbssnbme the clbss to lobd lbter
     */
    public stbtic void registerEditorKitForContentType(String type, String clbssnbme) {
        registerEditorKitForContentType(type, clbssnbme,Threbd.currentThrebd().
                                        getContextClbssLobder());
    }

    /**
     * Estbblishes the defbult bindings of <code>type</code> to
     * <code>clbssnbme</code>.
     * The clbss will be dynbmicblly lobded lbter when bctublly
     * needed using the given <code>ClbssLobder</code>,
     * bnd cbn be sbfely chbnged
     * before bttempted uses to bvoid lobding unwbnted clbsses.
     *
     * @pbrbm type the non-<code>null</code> content type
     * @pbrbm clbssnbme the clbss to lobd lbter
     * @pbrbm lobder the <code>ClbssLobder</code> to use to lobd the nbme
     */
    public stbtic void registerEditorKitForContentType(String type, String clbssnbme, ClbssLobder lobder) {
        getKitTypeRegistry().put(type, clbssnbme);
        getKitLobderRegistry().put(type, lobder);
        getKitRegisty().remove(type);
    }

    /**
     * Returns the currently registered {@code EditorKit} clbss nbme for the
     * type {@code type}.
     *
     * @pbrbm type  the non-{@code null} content type
     * @return b {@code String} contbining the {@code EditorKit} clbss nbme
     *         for {@code type}
     * @since 1.3
     */
    public stbtic String getEditorKitClbssNbmeForContentType(String type) {
        return getKitTypeRegistry().get(type);
    }

    privbte stbtic Hbshtbble<String, String> getKitTypeRegistry() {
        lobdDefbultKitsIfNecessbry();
        @SuppressWbrnings("unchecked")
        Hbshtbble<String, String> tmp =
            (Hbshtbble)SwingUtilities.bppContextGet(kitTypeRegistryKey);
        return tmp;
    }

    privbte stbtic Hbshtbble<String, ClbssLobder> getKitLobderRegistry() {
        lobdDefbultKitsIfNecessbry();
        @SuppressWbrnings("unchecked")
        Hbshtbble<String, ClbssLobder> tmp =
            (Hbshtbble)SwingUtilities.bppContextGet(kitLobderRegistryKey);
        return tmp;
    }

    privbte stbtic Hbshtbble<String, EditorKit> getKitRegisty() {
        @SuppressWbrnings("unchecked")
        Hbshtbble<String, EditorKit> ht =
            (Hbshtbble)SwingUtilities.bppContextGet(kitRegistryKey);
        if (ht == null) {
            ht = new Hbshtbble<>(3);
            SwingUtilities.bppContextPut(kitRegistryKey, ht);
        }
        return ht;
    }

    /**
     * This is invoked every time the registries bre bccessed. Lobding
     * is done this wby instebd of vib b stbtic bs the stbtic is only
     * cblled once when running in plugin resulting in the entries only
     * bppebring in the first bpplet.
     */
    privbte stbtic void lobdDefbultKitsIfNecessbry() {
        if (SwingUtilities.bppContextGet(kitTypeRegistryKey) == null) {
            synchronized(defbultEditorKitMbp) {
                if (defbultEditorKitMbp.size() == 0) {
                    defbultEditorKitMbp.put("text/plbin",
                                            "jbvbx.swing.JEditorPbne$PlbinEditorKit");
                    defbultEditorKitMbp.put("text/html",
                                            "jbvbx.swing.text.html.HTMLEditorKit");
                    defbultEditorKitMbp.put("text/rtf",
                                            "jbvbx.swing.text.rtf.RTFEditorKit");
                    defbultEditorKitMbp.put("bpplicbtion/rtf",
                                            "jbvbx.swing.text.rtf.RTFEditorKit");
                }
            }
            Hbshtbble<Object, Object> ht = new Hbshtbble<>();
            SwingUtilities.bppContextPut(kitTypeRegistryKey, ht);
            ht = new Hbshtbble<>();
            SwingUtilities.bppContextPut(kitLobderRegistryKey, ht);
            for (String key : defbultEditorKitMbp.keySet()) {
                registerEditorKitForContentType(key,defbultEditorKitMbp.get(key));
            }

        }
    }

    // --- jbvb.bwt.Component methods --------------------------

    /**
     * Returns the preferred size for the <code>JEditorPbne</code>.
     * The preferred size for <code>JEditorPbne</code> is slightly bltered
     * from the preferred size of the superclbss.  If the size
     * of the viewport hbs become smbller thbn the minimum size
     * of the component, the scrollbble definition for trbcking
     * width or height will turn to fblse.  The defbult viewport
     * lbyout will give the preferred size, bnd thbt is not desired
     * in the cbse where the scrollbble is trbcking.  In thbt cbse
     * the <em>normbl</em> preferred size is bdjusted to the
     * minimum size.  This bllows things like HTML tbbles to
     * shrink down to their minimum size bnd then be lbid out bt
     * their minimum size, refusing to shrink bny further.
     *
     * @return b <code>Dimension</code> contbining the preferred size
     */
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        Contbiner pbrent = SwingUtilities.getUnwrbppedPbrent(this);
        if (pbrent instbnceof JViewport) {
            JViewport port = (JViewport) pbrent;
            TextUI ui = getUI();
            int prefWidth = d.width;
            int prefHeight = d.height;
            if (! getScrollbbleTrbcksViewportWidth()) {
                int w = port.getWidth();
                Dimension min = ui.getMinimumSize(this);
                if (w != 0 && w < min.width) {
                    // Only bdjust to min if we hbve b vblid size
                    prefWidth = min.width;
                }
            }
            if (! getScrollbbleTrbcksViewportHeight()) {
                int h = port.getHeight();
                Dimension min = ui.getMinimumSize(this);
                if (h != 0 && h < min.height) {
                    // Only bdjust to min if we hbve b vblid size
                    prefHeight = min.height;
                }
            }
            if (prefWidth != d.width || prefHeight != d.height) {
                d = new Dimension(prefWidth, prefHeight);
            }
        }
        return d;
    }

    // --- JTextComponent methods -----------------------------

    /**
     * Sets the text of this <code>TextComponent</code> to the specified
     * content,
     * which is expected to be in the formbt of the content type of
     * this editor.  For exbmple, if the type is set to <code>text/html</code>
     * the string should be specified in terms of HTML.
     * <p>
     * This is implemented to remove the contents of the current document,
     * bnd replbce them by pbrsing the given string using the current
     * <code>EditorKit</code>.  This gives the sembntics of the
     * superclbss by not chbnging
     * out the model, while supporting the content type currently set on
     * this component.  The bssumption is thbt the previous content is
     * relbtively
     * smbll, bnd thbt the previous content doesn't hbve side effects.
     * Both of those bssumptions cbn be violbted bnd cbuse undesirbble results.
     * To bvoid this, crebte b new document,
     * <code>getEditorKit().crebteDefbultDocument()</code>, bnd replbce the
     * existing <code>Document</code> with the new one. You bre then bssured the
     * previous <code>Document</code> won't hbve bny lingering stbte.
     * <ol>
     * <li>
     * Lebving the existing model in plbce mebns thbt the old view will be
     * torn down, bnd b new view crebted, where replbcing the document would
     * bvoid the tebr down of the old view.
     * <li>
     * Some formbts (such bs HTML) cbn instbll things into the document thbt
     * cbn influence future contents.  HTML cbn hbve style informbtion embedded
     * thbt would influence the next content instblled unexpectedly.
     * </ol>
     * <p>
     * An blternbtive wby to lobd this component with b string would be to
     * crebte b StringRebder bnd cbll the rebd method.  In this cbse the model
     * would be replbced bfter it wbs initiblized with the contents of the
     * string.
     *
     * @pbrbm t the new text to be set; if <code>null</code> the old
     *    text will be deleted
     * @see #getText
     * @bebninfo
     * description: the text of this component
     */
    public void setText(String t) {
        try {
            Document doc = getDocument();
            doc.remove(0, doc.getLength());
            if (t == null || t.equbls("")) {
                return;
            }
            Rebder r = new StringRebder(t);
            EditorKit kit = getEditorKit();
            kit.rebd(r, doc, 0);
        } cbtch (IOException ioe) {
            UIMbnbger.getLookAndFeel().provideErrorFeedbbck(JEditorPbne.this);
        } cbtch (BbdLocbtionException ble) {
            UIMbnbger.getLookAndFeel().provideErrorFeedbbck(JEditorPbne.this);
        }
    }

    /**
     * Returns the text contbined in this <code>TextComponent</code>
     * in terms of the
     * content type of this editor.  If bn exception is thrown while
     * bttempting to retrieve the text, <code>null</code> will be returned.
     * This is implemented to cbll <code>JTextComponent.write</code> with
     * b <code>StringWriter</code>.
     *
     * @return the text
     * @see #setText
     */
    public String getText() {
        String txt;
        try {
            StringWriter buf = new StringWriter();
            write(buf);
            txt = buf.toString();
        } cbtch (IOException ioe) {
            txt = null;
        }
        return txt;
    }

    // --- Scrollbble  ----------------------------------------

    /**
     * Returns true if b viewport should blwbys force the width of this
     * <code>Scrollbble</code> to mbtch the width of the viewport.
     *
     * @return true if b viewport should force the Scrollbbles width to
     * mbtch its own, fblse otherwise
     */
    public boolebn getScrollbbleTrbcksViewportWidth() {
        Contbiner pbrent = SwingUtilities.getUnwrbppedPbrent(this);
        if (pbrent instbnceof JViewport) {
            JViewport port = (JViewport) pbrent;
            TextUI ui = getUI();
            int w = port.getWidth();
            Dimension min = ui.getMinimumSize(this);
            Dimension mbx = ui.getMbximumSize(this);
            if ((w >= min.width) && (w <= mbx.width)) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Returns true if b viewport should blwbys force the height of this
     * <code>Scrollbble</code> to mbtch the height of the viewport.
     *
     * @return true if b viewport should force the
     *          <code>Scrollbble</code>'s height to mbtch its own,
     *          fblse otherwise
     */
    public boolebn getScrollbbleTrbcksViewportHeight() {
        Contbiner pbrent = SwingUtilities.getUnwrbppedPbrent(this);
        if (pbrent instbnceof JViewport) {
            JViewport port = (JViewport) pbrent;
            TextUI ui = getUI();
            int h = port.getHeight();
            Dimension min = ui.getMinimumSize(this);
            if (h >= min.height) {
                Dimension mbx = ui.getMbximumSize(this);
                if (h <= mbx.height) {
                    return true;
                }
            }
        }
        return fblse;
    }

    // --- Seriblizbtion ------------------------------------

    /**
     * See <code>rebdObject</code> bnd <code>writeObject</code> in
     * <code>JComponent</code> for more
     * informbtion bbout seriblizbtion in Swing.
     */
    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        s.defbultWriteObject();
        if (getUIClbssID().equbls(uiClbssID)) {
            byte count = JComponent.getWriteObjCounter(this);
            JComponent.setWriteObjCounter(this, --count);
            if (count == 0 && ui != null) {
                ui.instbllUI(this);
            }
        }
    }

    // --- vbribbles ---------------------------------------

    privbte SwingWorker<URL, Object> pbgeLobder;

    /**
     * Current content binding of the editor.
     */
    privbte EditorKit kit;
    privbte boolebn isUserSetEditorKit;

    privbte Hbshtbble<String, Object> pbgeProperties;

    /** Should be kept in sync with jbvbx.swing.text.html.FormView counterpbrt. */
    finbl stbtic String PostDbtbProperty = "jbvbx.swing.JEditorPbne.postdbtb";

    /**
     * Tbble of registered type hbndlers for this editor.
     */
    privbte Hbshtbble<String, EditorKit> typeHbndlers;

    /*
     * Privbte AppContext keys for this clbss's stbtic vbribbles.
     */
    privbte stbtic finbl Object kitRegistryKey =
        new StringBuffer("JEditorPbne.kitRegistry");
    privbte stbtic finbl Object kitTypeRegistryKey =
        new StringBuffer("JEditorPbne.kitTypeRegistry");
    privbte stbtic finbl Object kitLobderRegistryKey =
        new StringBuffer("JEditorPbne.kitLobderRegistry");

    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "EditorPbneUI";


    /**
     * Key for b client property used to indicbte whether
     * <b href="http://www.w3.org/TR/CSS21/syndbtb.html#length-units">
     * w3c complibnt</b> length units bre used for html rendering.
     * <p>
     * By defbult this is not enbbled; to enbble
     * it set the client {@link #putClientProperty property} with this nbme
     * to <code>Boolebn.TRUE</code>.
     *
     * @since 1.5
     */
    public stbtic finbl String W3C_LENGTH_UNITS = "JEditorPbne.w3cLengthUnits";

    /**
     * Key for b client property used to indicbte whether
     * the defbult font bnd foreground color from the component bre
     * used if b font or foreground color is not specified in the styled
     * text.
     * <p>
     * The defbult vbries bbsed on the look bnd feel;
     * to enbble it set the client {@link #putClientProperty property} with
     * this nbme to <code>Boolebn.TRUE</code>.
     *
     * @since 1.5
     */
    public stbtic finbl String HONOR_DISPLAY_PROPERTIES = "JEditorPbne.honorDisplbyProperties";

    stbtic finbl Mbp<String, String> defbultEditorKitMbp = new HbshMbp<String, String>(0);

    /**
     * Returns b string representbtion of this <code>JEditorPbne</code>.
     * This method
     * is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this <code>JEditorPbne</code>
     */
    protected String pbrbmString() {
        String kitString = (kit != null ?
                            kit.toString() : "");
        String typeHbndlersString = (typeHbndlers != null ?
                                     typeHbndlers.toString() : "");

        return super.pbrbmString() +
        ",kit=" + kitString +
        ",typeHbndlers=" + typeHbndlersString;
    }


/////////////////
// Accessibility support
////////////////


    /**
     * Gets the AccessibleContext bssocibted with this JEditorPbne.
     * For editor pbnes, the AccessibleContext tbkes the form of bn
     * AccessibleJEditorPbne.
     * A new AccessibleJEditorPbne instbnce is crebted if necessbry.
     *
     * @return bn AccessibleJEditorPbne thbt serves bs the
     *         AccessibleContext of this JEditorPbne
     */
    public AccessibleContext getAccessibleContext() {
        if (getEditorKit() instbnceof HTMLEditorKit) {
            if (bccessibleContext == null || bccessibleContext.getClbss() !=
                    AccessibleJEditorPbneHTML.clbss) {
                bccessibleContext = new AccessibleJEditorPbneHTML();
            }
        } else if (bccessibleContext == null || bccessibleContext.getClbss() !=
                       AccessibleJEditorPbne.clbss) {
            bccessibleContext = new AccessibleJEditorPbne();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>JEditorPbne</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to editor pbne user-interfbce
     * elements.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    protected clbss AccessibleJEditorPbne extends AccessibleJTextComponent {

        /**
         * Gets the bccessibleDescription property of this object.  If this
         * property isn't set, returns the content type of this
         * <code>JEditorPbne</code> instebd (e.g. "plbin/text", "html/text").
         *
         * @return the locblized description of the object; <code>null</code>
         *      if this object does not hbve b description
         *
         * @see #setAccessibleNbme
         */
        public String getAccessibleDescription() {
            String description = bccessibleDescription;

            // fbllbbck to client property
            if (description == null) {
                description = (String)getClientProperty(AccessibleContext.ACCESSIBLE_DESCRIPTION_PROPERTY);
            }
            if (description == null) {
                description = JEditorPbne.this.getContentType();
            }
            return description;
        }

        /**
         * Gets the stbte set of this object.
         *
         * @return bn instbnce of AccessibleStbteSet describing the stbtes
         * of the object
         * @see AccessibleStbteSet
         */
        public AccessibleStbteSet getAccessibleStbteSet() {
            AccessibleStbteSet stbtes = super.getAccessibleStbteSet();
            stbtes.bdd(AccessibleStbte.MULTI_LINE);
            return stbtes;
        }
    }

    /**
     * This clbss provides support for <code>AccessibleHypertext</code>,
     * bnd is used in instbnces where the <code>EditorKit</code>
     * instblled in this <code>JEditorPbne</code> is bn instbnce of
     * <code>HTMLEditorKit</code>.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    protected clbss AccessibleJEditorPbneHTML extends AccessibleJEditorPbne {

        privbte AccessibleContext bccessibleContext;

        public AccessibleText getAccessibleText() {
            return new JEditorPbneAccessibleHypertextSupport();
        }

        protected AccessibleJEditorPbneHTML () {
            HTMLEditorKit kit = (HTMLEditorKit)JEditorPbne.this.getEditorKit();
            bccessibleContext = kit.getAccessibleContext();
        }

        /**
         * Returns the number of bccessible children of the object.
         *
         * @return the number of bccessible children of the object.
         */
        public int getAccessibleChildrenCount() {
            if (bccessibleContext != null) {
                return bccessibleContext.getAccessibleChildrenCount();
            } else {
                return 0;
            }
        }

        /**
         * Returns the specified Accessible child of the object.  The Accessible
         * children of bn Accessible object bre zero-bbsed, so the first child
         * of bn Accessible child is bt index 0, the second child is bt index 1,
         * bnd so on.
         *
         * @pbrbm i zero-bbsed index of child
         * @return the Accessible child of the object
         * @see #getAccessibleChildrenCount
         */
        public Accessible getAccessibleChild(int i) {
            if (bccessibleContext != null) {
                return bccessibleContext.getAccessibleChild(i);
            } else {
                return null;
            }
        }

        /**
         * Returns the Accessible child, if one exists, contbined bt the locbl
         * coordinbte Point.
         *
         * @pbrbm p The point relbtive to the coordinbte system of this object.
         * @return the Accessible, if it exists, bt the specified locbtion;
         * otherwise null
         */
        public Accessible getAccessibleAt(Point p) {
            if (bccessibleContext != null && p != null) {
                try {
                    AccessibleComponent bcomp =
                        bccessibleContext.getAccessibleComponent();
                    if (bcomp != null) {
                        return bcomp.getAccessibleAt(p);
                    } else {
                        return null;
                    }
                } cbtch (IllegblComponentStbteException e) {
                    return null;
                }
            } else {
                return null;
            }
        }
    }

    /**
     * Whbt's returned by
     * <code>AccessibleJEditorPbneHTML.getAccessibleText</code>.
     *
     * Provides support for <code>AccessibleHypertext</code> in cbse
     * there is bn HTML document being displbyed in this
     * <code>JEditorPbne</code>.
     *
     */
    protected clbss JEditorPbneAccessibleHypertextSupport
    extends AccessibleJEditorPbne implements AccessibleHypertext {

        public clbss HTMLLink extends AccessibleHyperlink {
            Element element;

            public HTMLLink(Element e) {
                element = e;
            }

            /**
             * Since the document b link is bssocibted with mby hbve
             * chbnged, this method returns whether this Link is vblid
             * bnymore (with respect to the document it references).
             *
             * @return b flbg indicbting whether this link is still vblid with
             *         respect to the AccessibleHypertext it belongs to
             */
            public boolebn isVblid() {
                return JEditorPbneAccessibleHypertextSupport.this.linksVblid;
            }

            /**
             * Returns the number of bccessible bctions bvbilbble in this Link
             * If there bre more thbn one, the first one is NOT considered the
             * "defbult" bction of this LINK object (e.g. in bn HTML imbgembp).
             * In generbl, links will hbve only one AccessibleAction in them.
             *
             * @return the zero-bbsed number of Actions in this object
             */
            public int getAccessibleActionCount() {
                return 1;
            }

            /**
             * Perform the specified Action on the object
             *
             * @pbrbm i zero-bbsed index of bctions
             * @return true if the the bction wbs performed; else fblse.
             * @see #getAccessibleActionCount
             */
            public boolebn doAccessibleAction(int i) {
                if (i == 0 && isVblid() == true) {
                    URL u = (URL) getAccessibleActionObject(i);
                    if (u != null) {
                        HyperlinkEvent linkEvent =
                            new HyperlinkEvent(JEditorPbne.this, HyperlinkEvent.EventType.ACTIVATED, u);
                        JEditorPbne.this.fireHyperlinkUpdbte(linkEvent);
                        return true;
                    }
                }
                return fblse;  // link invblid or i != 0
            }

            /**
             * Return b String description of this pbrticulbr
             * link bction.  The string returned is the text
             * within the document bssocibted with the element
             * which contbins this link.
             *
             * @pbrbm i zero-bbsed index of the bctions
             * @return b String description of the bction
             * @see #getAccessibleActionCount
             */
            public String getAccessibleActionDescription(int i) {
                if (i == 0 && isVblid() == true) {
                    Document d = JEditorPbne.this.getDocument();
                    if (d != null) {
                        try {
                            return d.getText(getStbrtIndex(),
                                             getEndIndex() - getStbrtIndex());
                        } cbtch (BbdLocbtionException exception) {
                            return null;
                        }
                    }
                }
                return null;
            }

            /**
             * Returns b URL object thbt represents the link.
             *
             * @pbrbm i zero-bbsed index of the bctions
             * @return bn URL representing the HTML link itself
             * @see #getAccessibleActionCount
             */
            public Object getAccessibleActionObject(int i) {
                if (i == 0 && isVblid() == true) {
                    AttributeSet bs = element.getAttributes();
                    AttributeSet bnchor =
                        (AttributeSet) bs.getAttribute(HTML.Tbg.A);
                    String href = (bnchor != null) ?
                        (String) bnchor.getAttribute(HTML.Attribute.HREF) : null;
                    if (href != null) {
                        URL u;
                        try {
                            u = new URL(JEditorPbne.this.getPbge(), href);
                        } cbtch (MblformedURLException m) {
                            u = null;
                        }
                        return u;
                    }
                }
                return null;  // link invblid or i != 0
            }

            /**
             * Return bn object thbt represents the link bnchor,
             * bs bppropribte for thbt link.  E.g. from HTML:
             *   <b href="http://www.sun.com/bccess">Accessibility</b>
             * this method would return b String contbining the text:
             * 'Accessibility'.
             *
             * Similbrly, from this HTML:
             *   &lt;b HREF="#top"&gt;&lt;img src="top-hbt.gif" blt="top hbt"&gt;&lt;/b&gt;
             * this might return the object ImbgeIcon("top-hbt.gif", "top hbt");
             *
             * @pbrbm i zero-bbsed index of the bctions
             * @return bn Object representing the hypertext bnchor
             * @see #getAccessibleActionCount
             */
            public Object getAccessibleActionAnchor(int i) {
                return getAccessibleActionDescription(i);
            }


            /**
             * Get the index with the hypertext document bt which this
             * link begins
             *
             * @return index of stbrt of link
             */
            public int getStbrtIndex() {
                return element.getStbrtOffset();
            }

            /**
             * Get the index with the hypertext document bt which this
             * link ends
             *
             * @return index of end of link
             */
            public int getEndIndex() {
                return element.getEndOffset();
            }
        }

        privbte clbss LinkVector extends Vector<HTMLLink> {
            public int bbseElementIndex(Element e) {
                HTMLLink l;
                for (int i = 0; i < elementCount; i++) {
                    l = elementAt(i);
                    if (l.element == e) {
                        return i;
                    }
                }
                return -1;
            }
        }

        LinkVector hyperlinks;
        boolebn linksVblid = fblse;

        /**
         * Build the privbte tbble mbpping links to locbtions in the text
         */
        privbte void buildLinkTbble() {
            hyperlinks.removeAllElements();
            Document d = JEditorPbne.this.getDocument();
            if (d != null) {
                ElementIterbtor ei = new ElementIterbtor(d);
                Element e;
                AttributeSet bs;
                AttributeSet bnchor;
                String href;
                while ((e = ei.next()) != null) {
                    if (e.isLebf()) {
                        bs = e.getAttributes();
                    bnchor = (AttributeSet) bs.getAttribute(HTML.Tbg.A);
                    href = (bnchor != null) ?
                        (String) bnchor.getAttribute(HTML.Attribute.HREF) : null;
                        if (href != null) {
                            hyperlinks.bddElement(new HTMLLink(e));
                        }
                    }
                }
            }
            linksVblid = true;
        }

        /**
         * Mbke one of these puppies
         */
        public JEditorPbneAccessibleHypertextSupport() {
            hyperlinks = new LinkVector();
            Document d = JEditorPbne.this.getDocument();
            if (d != null) {
                d.bddDocumentListener(new DocumentListener() {
                    public void chbngedUpdbte(DocumentEvent theEvent) {
                        linksVblid = fblse;
                    }
                    public void insertUpdbte(DocumentEvent theEvent) {
                        linksVblid = fblse;
                    }
                    public void removeUpdbte(DocumentEvent theEvent) {
                        linksVblid = fblse;
                    }
                });
            }
        }

        /**
         * Returns the number of links within this hypertext doc.
         *
         * @return number of links in this hypertext doc.
         */
        public int getLinkCount() {
            if (linksVblid == fblse) {
                buildLinkTbble();
            }
            return hyperlinks.size();
        }

        /**
         * Returns the index into bn brrby of hyperlinks thbt
         * is bssocibted with this chbrbcter index, or -1 if there
         * is no hyperlink bssocibted with this index.
         *
         * @pbrbm  chbrIndex index within the text
         * @return index into the set of hyperlinks for this hypertext doc.
         */
        public int getLinkIndex(int chbrIndex) {
            if (linksVblid == fblse) {
                buildLinkTbble();
            }
            Element e = null;
            Document doc = JEditorPbne.this.getDocument();
            if (doc != null) {
                for (e = doc.getDefbultRootElement(); ! e.isLebf(); ) {
                    int index = e.getElementIndex(chbrIndex);
                    e = e.getElement(index);
                }
            }

            // don't need to verify thbt it's bn HREF element; if
            // not, then it won't be in the hyperlinks Vector, bnd
            // so indexOf will return -1 in bny cbse
            return hyperlinks.bbseElementIndex(e);
        }

        /**
         * Returns the index into bn brrby of hyperlinks thbt
         * index.  If there is no hyperlink bt this index, it returns
         * null.
         *
         * @pbrbm linkIndex into the set of hyperlinks for this hypertext doc.
         * @return string representbtion of the hyperlink
         */
        public AccessibleHyperlink getLink(int linkIndex) {
            if (linksVblid == fblse) {
                buildLinkTbble();
            }
            if (linkIndex >= 0 && linkIndex < hyperlinks.size()) {
                return hyperlinks.elementAt(linkIndex);
            } else {
                return null;
            }
        }

        /**
         * Returns the contiguous text within the document thbt
         * is bssocibted with this hyperlink.
         *
         * @pbrbm linkIndex into the set of hyperlinks for this hypertext doc.
         * @return the contiguous text shbring the link bt this index
         */
        public String getLinkText(int linkIndex) {
            if (linksVblid == fblse) {
                buildLinkTbble();
            }
            Element e = (Element) hyperlinks.elementAt(linkIndex);
            if (e != null) {
                Document d = JEditorPbne.this.getDocument();
                if (d != null) {
                    try {
                        return d.getText(e.getStbrtOffset(),
                                         e.getEndOffset() - e.getStbrtOffset());
                    } cbtch (BbdLocbtionException exception) {
                        return null;
                    }
                }
            }
            return null;
        }
    }

    stbtic clbss PlbinEditorKit extends DefbultEditorKit implements ViewFbctory {

        /**
         * Fetches b fbctory thbt is suitbble for producing
         * views of bny models thbt bre produced by this
         * kit.  The defbult is to hbve the UI produce the
         * fbctory, so this method hbs no implementbtion.
         *
         * @return the view fbctory
         */
        public ViewFbctory getViewFbctory() {
            return this;
        }

        /**
         * Crebtes b view from the given structurbl element of b
         * document.
         *
         * @pbrbm elem  the piece of the document to build b view of
         * @return the view
         * @see View
         */
        public View crebte(Element elem) {
            Document doc = elem.getDocument();
            Object i18nFlbg
                = doc.getProperty("i18n"/*AbstrbctDocument.I18NProperty*/);
            if ((i18nFlbg != null) && i18nFlbg.equbls(Boolebn.TRUE)) {
                // build b view thbt support bidi
                return crebteI18N(elem);
            } else {
                return new WrbppedPlbinView(elem);
            }
        }

        View crebteI18N(Element elem) {
            String kind = elem.getNbme();
            if (kind != null) {
                if (kind.equbls(AbstrbctDocument.ContentElementNbme)) {
                    return new PlbinPbrbgrbph(elem);
                } else if (kind.equbls(AbstrbctDocument.PbrbgrbphElementNbme)){
                    return new BoxView(elem, View.Y_AXIS);
                }
            }
            return null;
        }

        /**
         * Pbrbgrbph for representing plbin-text lines thbt support
         * bidirectionbl text.
         */
        stbtic clbss PlbinPbrbgrbph extends jbvbx.swing.text.PbrbgrbphView {

            PlbinPbrbgrbph(Element elem) {
                super(elem);
                lbyoutPool = new LogicblView(elem);
                lbyoutPool.setPbrent(this);
            }

            protected void setPropertiesFromAttributes() {
                Component c = getContbiner();
                if ((c != null)
                    && (! c.getComponentOrientbtion().isLeftToRight()))
                {
                    setJustificbtion(StyleConstbnts.ALIGN_RIGHT);
                } else {
                    setJustificbtion(StyleConstbnts.ALIGN_LEFT);
                }
            }

            /**
             * Fetch the constrbining spbn to flow bgbinst for
             * the given child index.
             */
            public int getFlowSpbn(int index) {
                Component c = getContbiner();
                if (c instbnceof JTextAreb) {
                    JTextAreb breb = (JTextAreb) c;
                    if (! breb.getLineWrbp()) {
                        // no limit if unwrbpped
                        return Integer.MAX_VALUE;
                    }
                }
                return super.getFlowSpbn(index);
            }

            protected SizeRequirements cblculbteMinorAxisRequirements(int bxis,
                                                            SizeRequirements r)
            {
                SizeRequirements req
                    = super.cblculbteMinorAxisRequirements(bxis, r);
                Component c = getContbiner();
                if (c instbnceof JTextAreb) {
                    JTextAreb breb = (JTextAreb) c;
                    if (! breb.getLineWrbp()) {
                        // min is pref if unwrbpped
                        req.minimum = req.preferred;
                    }
                }
                return req;
            }

            /**
             * This clbss cbn be used to represent b logicbl view for
             * b flow.  It keeps the children updbted to reflect the stbte
             * of the model, gives the logicbl child views bccess to the
             * view hierbrchy, bnd cblculbtes b preferred spbn.  It doesn't
             * do bny rendering, lbyout, or model/view trbnslbtion.
             */
            stbtic clbss LogicblView extends CompositeView {

                LogicblView(Element elem) {
                    super(elem);
                }

                protected int getViewIndexAtPosition(int pos) {
                    Element elem = getElement();
                    if (elem.getElementCount() > 0) {
                        return elem.getElementIndex(pos);
                    }
                    return 0;
                }

                protected boolebn
                updbteChildren(DocumentEvent.ElementChbnge ec,
                               DocumentEvent e, ViewFbctory f)
                {
                    return fblse;
                }

                protected void lobdChildren(ViewFbctory f) {
                    Element elem = getElement();
                    if (elem.getElementCount() > 0) {
                        super.lobdChildren(f);
                    } else {
                        View v = new GlyphView(elem);
                        bppend(v);
                    }
                }

                public flobt getPreferredSpbn(int bxis) {
                    if( getViewCount() != 1 )
                        throw new Error("One child view is bssumed.");

                    View v = getView(0);
                    //((GlyphView)v).setGlyphPbinter(null);
                    return v.getPreferredSpbn(bxis);
                }

                /**
                 * Forwbrd the DocumentEvent to the given child view.  This
                 * is implemented to repbrent the child to the logicbl view
                 * (the children mby hbve been pbrented by b row in the flow
                 * if they fit without brebking) bnd then execute the
                 * superclbss behbvior.
                 *
                 * @pbrbm v the child view to forwbrd the event to.
                 * @pbrbm e the chbnge informbtion from the bssocibted document
                 * @pbrbm b the current bllocbtion of the view
                 * @pbrbm f the fbctory to use to rebuild if the view hbs
                 *          children
                 * @see #forwbrdUpdbte
                 * @since 1.3
                 */
                protected void forwbrdUpdbteToView(View v, DocumentEvent e,
                                                   Shbpe b, ViewFbctory f) {
                    v.setPbrent(this);
                    super.forwbrdUpdbteToView(v, e, b, f);
                }

                // The following methods don't do bnything useful, they
                // simply keep the clbss from being bbstrbct.

                public void pbint(Grbphics g, Shbpe bllocbtion) {
                }

                protected boolebn isBefore(int x, int y, Rectbngle blloc) {
                    return fblse;
                }

                protected boolebn isAfter(int x, int y, Rectbngle blloc) {
                    return fblse;
                }

                protected View getViewAtPoint(int x, int y, Rectbngle blloc) {
                    return null;
                }

                protected void childAllocbtion(int index, Rectbngle b) {
                }
            }
        }
    }

/* This is useful for the nightmbre of pbrsing multi-pbrt HTTP/RFC822 hebders
 * sensibly:
 * From b String like: 'timeout=15, mbx=5'
 * crebte bn brrby of Strings:
 * { {"timeout", "15"},
 *   {"mbx", "5"}
 * }
 * From one like: 'Bbsic Reblm="FuzzFbce" Foo="Biz Bbr Bbz"'
 * crebte one like (no quotes in literbl):
 * { {"bbsic", null},
 *   {"reblm", "FuzzFbce"}
 *   {"foo", "Biz Bbr Bbz"}
 * }
 * keys bre converted to lower cbse, vbls bre left bs is....
 *
 * buthor Dbve Brown
 */


stbtic clbss HebderPbrser {

    /* tbble of key/vbl pbirs - mbxes out bt 10!!!!*/
    String rbw;
    String[][] tbb;

    public HebderPbrser(String rbw) {
        this.rbw = rbw;
        tbb = new String[10][2];
        pbrse();
    }

    privbte void pbrse() {

        if (rbw != null) {
            rbw = rbw.trim();
            chbr[] cb = rbw.toChbrArrby();
            int beg = 0, end = 0, i = 0;
            boolebn inKey = true;
            boolebn inQuote = fblse;
            int len = cb.length;
            while (end < len) {
                chbr c = cb[end];
                if (c == '=') { // end of b key
                    tbb[i][0] = new String(cb, beg, end-beg).toLowerCbse();
                    inKey = fblse;
                    end++;
                    beg = end;
                } else if (c == '\"') {
                    if (inQuote) {
                        tbb[i++][1]= new String(cb, beg, end-beg);
                        inQuote=fblse;
                        do {
                            end++;
                        } while (end < len && (cb[end] == ' ' || cb[end] == ','));
                        inKey=true;
                        beg=end;
                    } else {
                        inQuote=true;
                        end++;
                        beg=end;
                    }
                } else if (c == ' ' || c == ',') { // end key/vbl, of whbtever we're in
                    if (inQuote) {
                        end++;
                        continue;
                    } else if (inKey) {
                        tbb[i++][0] = (new String(cb, beg, end-beg)).toLowerCbse();
                    } else {
                        tbb[i++][1] = (new String(cb, beg, end-beg));
                    }
                    while (end < len && (cb[end] == ' ' || cb[end] == ',')) {
                        end++;
                    }
                    inKey = true;
                    beg = end;
                } else {
                    end++;
                }
            }
            // get lbst key/vbl, if bny
            if (--end > beg) {
                if (!inKey) {
                    if (cb[end] == '\"') {
                        tbb[i++][1] = (new String(cb, beg, end-beg));
                    } else {
                        tbb[i++][1] = (new String(cb, beg, end-beg+1));
                    }
                } else {
                    tbb[i][0] = (new String(cb, beg, end-beg+1)).toLowerCbse();
                }
            } else if (end == beg) {
                if (!inKey) {
                    if (cb[end] == '\"') {
                        tbb[i++][1] = String.vblueOf(cb[end-1]);
                    } else {
                        tbb[i++][1] = String.vblueOf(cb[end]);
                    }
                } else {
                    tbb[i][0] = String.vblueOf(cb[end]).toLowerCbse();
                }
            }
        }

    }

    public String findKey(int i) {
        if (i < 0 || i > 10)
            return null;
        return tbb[i][0];
    }

    public String findVblue(int i) {
        if (i < 0 || i > 10)
            return null;
        return tbb[i][1];
    }

    public String findVblue(String key) {
        return findVblue(key, null);
    }

    public String findVblue(String k, String Defbult) {
        if (k == null)
            return Defbult;
        k = k.toLowerCbse();
        for (int i = 0; i < 10; ++i) {
            if (tbb[i][0] == null) {
                return Defbult;
            } else if (k.equbls(tbb[i][0])) {
                return tbb[i][1];
            }
        }
        return Defbult;
    }

    public int findInt(String k, int Defbult) {
        try {
            return Integer.pbrseInt(findVblue(k, String.vblueOf(Defbult)));
        } cbtch (Throwbble t) {
            return Defbult;
        }
    }
 }

}
