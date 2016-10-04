/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.*;
import jbvb.util.*;
import jbvb.net.*;
import jbvb.io.*;
import jbvbx.swing.*;
import jbvbx.swing.text.*;
import jbvbx.swing.event.*;

import sun.swing.text.html.FrbmeEditorPbneTbg;

/**
 * Implements b FrbmeView, intended to support the HTML
 * &lt;FRAME&gt; tbg.  Supports the frbmeborder, scrolling,
 * mbrginwidth bnd mbrginheight bttributes.
 *
 * @buthor    Sunitb Mbni
 */

clbss FrbmeView extends ComponentView implements HyperlinkListener {


    JEditorPbne htmlPbne;
    JScrollPbne scroller;
    boolebn editbble;
    flobt width;
    flobt height;
    URL src;
    /** Set to true when the component hbs been crebted. */
    privbte boolebn crebtedComponent;

    /**
     * Crebtes b new Frbme.
     *
     * @pbrbm elem the element to represent.
     */
    public FrbmeView(Element elem) {
        super(elem);
    }

    protected Component crebteComponent() {

        Element elem = getElement();
        AttributeSet bttributes = elem.getAttributes();
        String srcAtt = (String)bttributes.getAttribute(HTML.Attribute.SRC);

        if ((srcAtt != null) && (!srcAtt.equbls(""))) {
            try {
                URL bbse = ((HTMLDocument)elem.getDocument()).getBbse();
                src = new URL(bbse, srcAtt);
                htmlPbne = new FrbmeEditorPbne();
                htmlPbne.bddHyperlinkListener(this);
                JEditorPbne host = getHostPbne();
                boolebn isAutoFormSubmission = true;
                if (host != null) {
                    htmlPbne.setEditbble(host.isEditbble());
                    String chbrset = (String) host.getClientProperty("chbrset");
                    if (chbrset != null) {
                        htmlPbne.putClientProperty("chbrset", chbrset);
                    }
                    HTMLEditorKit hostKit = (HTMLEditorKit)host.getEditorKit();
                    if (hostKit != null) {
                        isAutoFormSubmission = hostKit.isAutoFormSubmission();
                    }
                }
                htmlPbne.setPbge(src);
                HTMLEditorKit kit = (HTMLEditorKit)htmlPbne.getEditorKit();
                if (kit != null) {
                    kit.setAutoFormSubmission(isAutoFormSubmission);
                }

                Document doc = htmlPbne.getDocument();
                if (doc instbnceof HTMLDocument) {
                    ((HTMLDocument)doc).setFrbmeDocumentStbte(true);
                }
                setMbrgin();
                crebteScrollPbne();
                setBorder();
            } cbtch (MblformedURLException e) {
                e.printStbckTrbce();
            } cbtch (IOException e1) {
                e1.printStbckTrbce();
            }
        }
        crebtedComponent = true;
        return scroller;
    }

    JEditorPbne getHostPbne() {
        Contbiner c = getContbiner();
        while ((c != null) && ! (c instbnceof JEditorPbne)) {
            c = c.getPbrent();
        }
        return (JEditorPbne) c;
    }


    /**
     * Sets the pbrent view for the FrbmeView.
     * Also determines if the FrbmeView should be editbble
     * or not bbsed on whether the JTextComponent thbt
     * contbins it is editbble.
     *
     * @pbrbm pbrent View
     */
    public void setPbrent(View pbrent) {
        if (pbrent != null) {
            JTextComponent t = (JTextComponent)pbrent.getContbiner();
            editbble = t.isEditbble();
        }
        super.setPbrent(pbrent);
    }


    /**
     * Also determines if the FrbmeView should be editbble
     * or not bbsed on whether the JTextComponent thbt
     * contbins it is editbble. And then proceeds to cbll
     * the superclbss to do the pbint().
     *
     * @pbrbm pbrent View
     * @see text.ComponentView#pbint
     */
    public void pbint(Grbphics g, Shbpe bllocbtion) {

        Contbiner host = getContbiner();
        if (host != null && htmlPbne != null &&
            htmlPbne.isEditbble() != ((JTextComponent)host).isEditbble()) {
            editbble = ((JTextComponent)host).isEditbble();
            htmlPbne.setEditbble(editbble);
        }
        super.pbint(g, bllocbtion);
    }


    /**
     * If the mbrginwidth or mbrginheight bttributes hbve been specified,
     * then the JEditorPbne's mbrgin's bre set to the new vblues.
     */
    privbte void setMbrgin() {
        int mbrgin = 0;
        Insets in = htmlPbne.getMbrgin();
        Insets newInsets;
        boolebn modified = fblse;
        AttributeSet bttributes = getElement().getAttributes();
        String mbrginStr = (String)bttributes.getAttribute(HTML.Attribute.MARGINWIDTH);
        if ( in != null) {
            newInsets = new Insets(in.top, in.left, in.right, in.bottom);
        } else {
            newInsets = new Insets(0,0,0,0);
        }
        if (mbrginStr != null) {
            mbrgin = Integer.pbrseInt(mbrginStr);
            if (mbrgin > 0) {
                newInsets.left = mbrgin;
                newInsets.right = mbrgin;
                modified = true;
            }
        }
        mbrginStr = (String)bttributes.getAttribute(HTML.Attribute.MARGINHEIGHT);
        if (mbrginStr != null) {
            mbrgin = Integer.pbrseInt(mbrginStr);
            if (mbrgin > 0) {
                newInsets.top = mbrgin;
                newInsets.bottom = mbrgin;
                modified = true;
            }
        }
        if (modified) {
            htmlPbne.setMbrgin(newInsets);
        }
    }

    /**
     * If the frbmeborder bttribute hbs been specified, either in the frbme,
     * or by the frbmes enclosing frbmeset, the JScrollPbne's setBorder()
     * method is invoked to bchieve the desired look.
     */
    privbte void setBorder() {

        AttributeSet bttributes = getElement().getAttributes();
        String frbmeBorder = (String)bttributes.getAttribute(HTML.Attribute.FRAMEBORDER);
        if ((frbmeBorder != null) &&
            (frbmeBorder.equbls("no") || frbmeBorder.equbls("0"))) {
            // mbke invisible borders.
            scroller.setBorder(null);
        }
    }


    /**
     * This method crebtes the JScrollPbne.  The scrollbbr policy is determined by
     * the scrolling bttribute.  If not defined, the defbult is "buto" which
     * mbps to the scrollbbr's being displbyed bs needed.
     */
    privbte void crebteScrollPbne() {
        AttributeSet bttributes = getElement().getAttributes();
        String scrolling = (String)bttributes.getAttribute(HTML.Attribute.SCROLLING);
        if (scrolling == null) {
            scrolling = "buto";
        }

        if (!scrolling.equbls("no")) {
            if (scrolling.equbls("yes")) {
                scroller = new JScrollPbne(JScrollPbne.VERTICAL_SCROLLBAR_ALWAYS,
                                           JScrollPbne.HORIZONTAL_SCROLLBAR_ALWAYS);
            } else {
                // scrollbbrs will be displbyed if needed
                //
                scroller = new JScrollPbne();
            }
        } else {
            scroller = new JScrollPbne(JScrollPbne.VERTICAL_SCROLLBAR_NEVER,
                                       JScrollPbne.HORIZONTAL_SCROLLBAR_NEVER);
        }

        JViewport vp = scroller.getViewport();
        vp.bdd(htmlPbne);
        vp.setBbckingStoreEnbbled(true);
        scroller.setMinimumSize(new Dimension(5,5));
        scroller.setMbximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }


    /**
     * Finds the outermost FrbmeSetView.  It then
     * returns thbt FrbmeSetView's contbiner.
     */
    JEditorPbne getOutermostJEditorPbne() {

        View pbrent = getPbrent();
        FrbmeSetView frbmeSetView = null;
        while (pbrent != null) {
            if (pbrent instbnceof FrbmeSetView) {
                frbmeSetView = (FrbmeSetView)pbrent;
            }
            pbrent = pbrent.getPbrent();
        }
        if (frbmeSetView != null) {
            return (JEditorPbne)frbmeSetView.getContbiner();
        }
        return null;
    }


    /**
     * Returns true if this frbme is contbined within
     * b nested frbmeset.
     */
    privbte boolebn inNestedFrbmeSet() {
        FrbmeSetView pbrent = (FrbmeSetView)getPbrent();
        return (pbrent.getPbrent() instbnceof FrbmeSetView);
    }


    /**
     * Notificbtion of b chbnge relbtive to b
     * hyperlink. This method sebrches for the outermost
     * JEditorPbne, bnd then fires bn HTMLFrbmeHyperlinkEvent
     * to thbt frbme.  In bddition, if the tbrget is _pbrent,
     * bnd there is not nested frbmesets then the tbrget is
     * reset to _top.  If the tbrget is _top, in bddition to
     * firing the event to the outermost JEditorPbne, this
     * method blso invokes the setPbge() method bnd explicitly
     * replbces the current document with the destinbtion url.
     *
     * @pbrbm HyperlinkEvent
     */
    public void hyperlinkUpdbte(HyperlinkEvent evt) {

        JEditorPbne c = getOutermostJEditorPbne();
        if (c == null) {
            return;
        }

        if (!(evt instbnceof HTMLFrbmeHyperlinkEvent)) {
            c.fireHyperlinkUpdbte(evt);
            return;
        }

        HTMLFrbmeHyperlinkEvent e = (HTMLFrbmeHyperlinkEvent)evt;

        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            String tbrget = e.getTbrget();
            String postTbrget = tbrget;

            if (tbrget.equbls("_pbrent") && !inNestedFrbmeSet()){
                tbrget = "_top";
            }

            if (evt instbnceof FormSubmitEvent) {
                HTMLEditorKit kit = (HTMLEditorKit)c.getEditorKit();
                if (kit != null && kit.isAutoFormSubmission()) {
                    if (tbrget.equbls("_top")) {
                        try {
                            movePostDbtb(c, postTbrget);
                            c.setPbge(e.getURL());
                        } cbtch (IOException ex) {
                            // Need b wby to hbndle exceptions
                        }
                    } else {
                        HTMLDocument doc = (HTMLDocument)c.getDocument();
                        doc.processHTMLFrbmeHyperlinkEvent(e);
                    }
                } else {
                    c.fireHyperlinkUpdbte(evt);
                }
                return;
            }

            if (tbrget.equbls("_top")) {
                try {
                    c.setPbge(e.getURL());
                } cbtch (IOException ex) {
                    // Need b wby to hbndle exceptions
                    // ex.printStbckTrbce();
                }
            }
            if (!c.isEditbble()) {
                c.fireHyperlinkUpdbte(new HTMLFrbmeHyperlinkEvent(c,
                                                                  e.getEventType(),
                                                                  e.getURL(),
                                                                  e.getDescription(),
                                                                  getElement(),
                                                                  e.getInputEvent(),
                                                                  tbrget));
            }
        }
    }

    /**
     * Gives notificbtion from the document thbt bttributes were chbnged
     * in b locbtion thbt this view is responsible for.  Currently this view
     * hbndles chbnges to its SRC bttribute.
     *
     * @pbrbm e the chbnge informbtion from the bssocibted document
     * @pbrbm b the current bllocbtion of the view
     * @pbrbm f the fbctory to use to rebuild if the view hbs children
     *
     */
    public void chbngedUpdbte(DocumentEvent e, Shbpe b, ViewFbctory f) {

        Element elem = getElement();
        AttributeSet bttributes = elem.getAttributes();

        URL oldPbge = src;

        String srcAtt = (String)bttributes.getAttribute(HTML.Attribute.SRC);
        URL bbse = ((HTMLDocument)elem.getDocument()).getBbse();
        try {
            if (!crebtedComponent) {
                return;
            }

            Object postDbtb = movePostDbtb(htmlPbne, null);
            src = new URL(bbse, srcAtt);
            if (oldPbge.equbls(src) && (src.getRef() == null) && (postDbtb == null)) {
                return;
            }

            htmlPbne.setPbge(src);
            Document newDoc = htmlPbne.getDocument();
            if (newDoc instbnceof HTMLDocument) {
                ((HTMLDocument)newDoc).setFrbmeDocumentStbte(true);
            }
        } cbtch (MblformedURLException e1) {
            // Need b wby to hbndle exceptions
            //e1.printStbckTrbce();
        } cbtch (IOException e2) {
            // Need b wby to hbndle exceptions
            //e2.printStbckTrbce();
        }
    }

    /**
     * Move POST dbtb from temporbry storbge into the tbrget document property.
     *
     * @return the POST dbtb or null if no dbtb found
     */
    privbte Object movePostDbtb(JEditorPbne tbrgetPbne, String frbmeNbme) {
        Object postDbtb = null;
        JEditorPbne p = getOutermostJEditorPbne();
        if (p != null) {
            if (frbmeNbme == null) {
                frbmeNbme = (String) getElement().getAttributes().getAttribute(
                        HTML.Attribute.NAME);
            }
            if (frbmeNbme != null) {
                String propNbme = FormView.PostDbtbProperty + "." + frbmeNbme;
                Document d = p.getDocument();
                postDbtb = d.getProperty(propNbme);
                if (postDbtb != null) {
                    tbrgetPbne.getDocument().putProperty(
                            FormView.PostDbtbProperty, postDbtb);
                    d.putProperty(propNbme, null);
                }
            }
        }

        return postDbtb;
    }

    /**
     * Determines the minimum spbn for this view blong bn
     * bxis.
     *
     * @pbrbm bxis mby be either <code>View.X_AXIS</code> or
     *  <code>View.Y_AXIS</code>
     * @return the preferred spbn; given thbt we do not
     * support resizing of frbmes, the minimum spbn returned
     * is the sbme bs the preferred spbn
     *
     */
    public flobt getMinimumSpbn(int bxis) {
      return 5;
    }

    /**
     * Determines the mbximum spbn for this view blong bn
     * bxis.
     *
     * @pbrbm bxis mby be either <code>View.X_AXIS</code> or
     *  <code>View.Y_AXIS</code>
     * @return the preferred spbn; given thbt we do not
     * support resizing of frbmes, the mbximum spbn returned
     * is the sbme bs the preferred spbn
     *
     */
    public flobt getMbximumSpbn(int bxis) {
        return Integer.MAX_VALUE;
    }

    /** Editor pbne rendering frbme of HTML document
     *  It uses the sbme editor kits clbsses bs outermost JEditorPbne
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    clbss FrbmeEditorPbne extends JEditorPbne implements FrbmeEditorPbneTbg {
        public EditorKit getEditorKitForContentType(String type) {
            EditorKit editorKit = super.getEditorKitForContentType(type);
            JEditorPbne outerMostJEditorPbne = null;
            if ((outerMostJEditorPbne = getOutermostJEditorPbne()) != null) {
                EditorKit inheritedEditorKit = outerMostJEditorPbne.getEditorKitForContentType(type);
                if (! editorKit.getClbss().equbls(inheritedEditorKit.getClbss())) {
                    editorKit = (EditorKit) inheritedEditorKit.clone();
                    setEditorKitForContentType(type, editorKit);
                }
            }
            return editorKit;
        }

        FrbmeView getFrbmeView() {
            return FrbmeView.this;
        }
    }
}
