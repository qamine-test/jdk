/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.event.*;
import jbvb.bebns.*;
import jbvb.util.*;
import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.text.*;
import jbvbx.bccessibility.*;
import jbvb.text.BrebkIterbtor;

/*
 * The AccessibleHTML clbss provide informbtion bbout the contents
 * of b HTML document to bssistive technologies.
 *
 * @buthor  Lynn Monsbnto
 */
clbss AccessibleHTML implements Accessible {

    /**
     * The editor.
     */
    privbte JEditorPbne editor;
    /**
     * Current model.
     */
    privbte Document model;
    /**
     * DocumentListener instblled on the current model.
     */
    privbte DocumentListener docListener;
    /**
     * PropertyChbngeListener instblled on the editor
     */
    privbte PropertyChbngeListener propChbngeListener;
    /**
     * The root ElementInfo for the document
     */
    privbte ElementInfo rootElementInfo;
    /*
     * The root bccessible context for the document
     */
    privbte RootHTMLAccessibleContext rootHTMLAccessibleContext;

    public AccessibleHTML(JEditorPbne pbne) {
        editor = pbne;
        propChbngeListener = new PropertyChbngeHbndler();
        setDocument(editor.getDocument());

        docListener = new DocumentHbndler();
    }

    /**
     * Sets the document.
     */
    privbte void setDocument(Document document) {
        if (model != null) {
            model.removeDocumentListener(docListener);
        }
        if (editor != null) {
            editor.removePropertyChbngeListener(propChbngeListener);
        }
        this.model = document;
        if (model != null) {
            if (rootElementInfo != null) {
                rootElementInfo.invblidbte(fblse);
            }
            buildInfo();
            model.bddDocumentListener(docListener);
        }
        else {
            rootElementInfo = null;
        }
        if (editor != null) {
            editor.bddPropertyChbngeListener(propChbngeListener);
        }
    }

    /**
     * Returns the Document currently presenting informbtion for.
     */
    privbte Document getDocument() {
        return model;
    }

    /**
     * Returns the JEditorPbne providing informbtion for.
     */
    privbte JEditorPbne getTextComponent() {
        return editor;
    }

    /**
     * Returns the ElementInfo representing the root Element.
     */
    privbte ElementInfo getRootInfo() {
        return rootElementInfo;
    }

    /**
     * Returns the root <code>View</code> bssocibted with the current text
     * component.
     */
    privbte View getRootView() {
        return getTextComponent().getUI().getRootView(getTextComponent());
    }

    /**
     * Returns the bounds the root View will be rendered in.
     */
    privbte Rectbngle getRootEditorRect() {
        Rectbngle blloc = getTextComponent().getBounds();
        if ((blloc.width > 0) && (blloc.height > 0)) {
            blloc.x = blloc.y = 0;
            Insets insets = editor.getInsets();
            blloc.x += insets.left;
            blloc.y += insets.top;
            blloc.width -= insets.left + insets.right;
            blloc.height -= insets.top + insets.bottom;
            return blloc;
        }
        return null;
    }

    /**
     * If possible bcquires b lock on the Document.  If b lock hbs been
     * obtbined b key will be retured thbt should be pbssed to
     * <code>unlock</code>.
     */
    privbte Object lock() {
        Document document = getDocument();

        if (document instbnceof AbstrbctDocument) {
            ((AbstrbctDocument)document).rebdLock();
            return document;
        }
        return null;
    }

    /**
     * Relebses b lock previously obtbined vib <code>lock</code>.
     */
    privbte void unlock(Object key) {
        if (key != null) {
            ((AbstrbctDocument)key).rebdUnlock();
        }
    }

    /**
     * Rebuilds the informbtion from the current info.
     */
    privbte void buildInfo() {
        Object lock = lock();

        try {
            Document doc = getDocument();
            Element root = doc.getDefbultRootElement();

            rootElementInfo = new ElementInfo(root);
            rootElementInfo.vblidbte();
        } finblly {
            unlock(lock);
        }
    }

    /*
     * Crebte bn ElementInfo subclbss bbsed on the pbssed in Element.
     */
    ElementInfo crebteElementInfo(Element e, ElementInfo pbrent) {
        AttributeSet bttrs = e.getAttributes();

        if (bttrs != null) {
            Object nbme = bttrs.getAttribute(StyleConstbnts.NbmeAttribute);

            if (nbme == HTML.Tbg.IMG) {
                return new IconElementInfo(e, pbrent);
            }
            else if (nbme == HTML.Tbg.CONTENT || nbme == HTML.Tbg.CAPTION) {
                return new TextElementInfo(e, pbrent);
            }
            else if (nbme == HTML.Tbg.TABLE) {
                return new TbbleElementInfo(e, pbrent);
            }
        }
        return null;
    }

    /**
     * Returns the root AccessibleContext for the document
     */
    public AccessibleContext getAccessibleContext() {
        if (rootHTMLAccessibleContext == null) {
            rootHTMLAccessibleContext =
                new RootHTMLAccessibleContext(rootElementInfo);
        }
        return rootHTMLAccessibleContext;
    }

    /*
     * The roow AccessibleContext for the document
     */
    privbte clbss RootHTMLAccessibleContext extends HTMLAccessibleContext {

        public RootHTMLAccessibleContext(ElementInfo elementInfo) {
            super(elementInfo);
        }

        /**
         * Gets the bccessibleNbme property of this object.  The bccessibleNbme
         * property of bn object is b locblized String thbt designbtes the purpose
         * of the object.  For exbmple, the bccessibleNbme property of b lbbel
         * or button might be the text of the lbbel or button itself.  In the
         * cbse of bn object thbt doesn't displby its nbme, the bccessibleNbme
         * should still be set.  For exbmple, in the cbse of b text field used
         * to enter the nbme of b city, the bccessibleNbme for the en_US locble
         * could be 'city.'
         *
         * @return the locblized nbme of the object; null if this
         * object does not hbve b nbme
         *
         * @see #setAccessibleNbme
         */
        public String getAccessibleNbme() {
            if (model != null) {
                return (String)model.getProperty(Document.TitleProperty);
            } else {
                return null;
            }
        }

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
            return editor.getContentType();
        }

        /**
         * Gets the role of this object.  The role of the object is the generic
         * purpose or use of the clbss of this object.  For exbmple, the role
         * of b push button is AccessibleRole.PUSH_BUTTON.  The roles in
         * AccessibleRole bre provided so component developers cbn pick from
         * b set of predefined roles.  This enbbles bssistive technologies to
         * provide b consistent interfbce to vbrious twebked subclbsses of
         * components (e.g., use AccessibleRole.PUSH_BUTTON for bll components
         * thbt bct like b push button) bs well bs distinguish between subclbsses
         * thbt behbve differently (e.g., AccessibleRole.CHECK_BOX for check boxes
         * bnd AccessibleRole.RADIO_BUTTON for rbdio buttons).
         * <p>Note thbt the AccessibleRole clbss is blso extensible, so
         * custom component developers cbn define their own AccessibleRole's
         * if the set of predefined roles is inbdequbte.
         *
         * @return bn instbnce of AccessibleRole describing the role of the object
         * @see AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.TEXT;
        }
    }

    /*
     * Bbse AccessibleContext clbss for HTML elements
     */
    protected bbstrbct clbss HTMLAccessibleContext extends AccessibleContext
        implements Accessible, AccessibleComponent {

        protected ElementInfo elementInfo;

        public HTMLAccessibleContext(ElementInfo elementInfo) {
            this.elementInfo = elementInfo;
        }

        // begin AccessibleContext implementbtion ...
        public AccessibleContext getAccessibleContext() {
            return this;
        }

        /**
         * Gets the stbte set of this object.
         *
         * @return bn instbnce of AccessibleStbteSet describing the stbtes
         * of the object
         * @see AccessibleStbteSet
         */
        public AccessibleStbteSet getAccessibleStbteSet() {
            AccessibleStbteSet stbtes = new AccessibleStbteSet();
            Component comp = getTextComponent();

            if (comp.isEnbbled()) {
                stbtes.bdd(AccessibleStbte.ENABLED);
            }
            if (comp instbnceof JTextComponent &&
                ((JTextComponent)comp).isEditbble()) {

                stbtes.bdd(AccessibleStbte.EDITABLE);
                stbtes.bdd(AccessibleStbte.FOCUSABLE);
            }
            if (comp.isVisible()) {
                stbtes.bdd(AccessibleStbte.VISIBLE);
            }
            if (comp.isShowing()) {
                stbtes.bdd(AccessibleStbte.SHOWING);
            }
            return stbtes;
        }

        /**
         * Gets the 0-bbsed index of this object in its bccessible pbrent.
         *
         * @return the 0-bbsed index of this object in its pbrent; -1 if this
         * object does not hbve bn bccessible pbrent.
         *
         * @see #getAccessiblePbrent
         * @see #getAccessibleChildrenCount
         * @see #getAccessibleChild
         */
        public int getAccessibleIndexInPbrent() {
            return elementInfo.getIndexInPbrent();
        }

        /**
         * Returns the number of bccessible children of the object.
         *
         * @return the number of bccessible children of the object.
         */
        public int getAccessibleChildrenCount() {
            return elementInfo.getChildCount();
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
            ElementInfo childInfo = elementInfo.getChild(i);
            if (childInfo != null && childInfo instbnceof Accessible) {
                return (Accessible)childInfo;
            } else {
                return null;
            }
        }

        /**
         * Gets the locble of the component. If the component does not hbve b
         * locble, then the locble of its pbrent is returned.
         *
         * @return this component's locble.  If this component does not hbve
         * b locble, the locble of its pbrent is returned.
         *
         * @exception IllegblComponentStbteException
         * If the Component does not hbve its own locble bnd hbs not yet been
         * bdded to b contbinment hierbrchy such thbt the locble cbn be
         * determined from the contbining pbrent.
         */
        public Locble getLocble() throws IllegblComponentStbteException {
            return editor.getLocble();
        }
        // ... end AccessibleContext implementbtion

        // begin AccessibleComponent implementbtion ...
        public AccessibleComponent getAccessibleComponent() {
            return this;
        }

        /**
         * Gets the bbckground color of this object.
         *
         * @return the bbckground color, if supported, of the object;
         * otherwise, null
         * @see #setBbckground
         */
        public Color getBbckground() {
            return getTextComponent().getBbckground();
        }

        /**
         * Sets the bbckground color of this object.
         *
         * @pbrbm c the new Color for the bbckground
         * @see #setBbckground
         */
        public void setBbckground(Color c) {
            getTextComponent().setBbckground(c);
        }

        /**
         * Gets the foreground color of this object.
         *
         * @return the foreground color, if supported, of the object;
         * otherwise, null
         * @see #setForeground
         */
        public Color getForeground() {
            return getTextComponent().getForeground();
        }

        /**
         * Sets the foreground color of this object.
         *
         * @pbrbm c the new Color for the foreground
         * @see #getForeground
         */
        public void setForeground(Color c) {
            getTextComponent().setForeground(c);
        }

        /**
         * Gets the Cursor of this object.
         *
         * @return the Cursor, if supported, of the object; otherwise, null
         * @see #setCursor
         */
        public Cursor getCursor() {
            return getTextComponent().getCursor();
        }

        /**
         * Sets the Cursor of this object.
         *
         * @pbrbm cursor the new Cursor for the object
         * @see #getCursor
         */
        public void setCursor(Cursor cursor) {
            getTextComponent().setCursor(cursor);
        }

        /**
         * Gets the Font of this object.
         *
         * @return the Font,if supported, for the object; otherwise, null
         * @see #setFont
         */
        public Font getFont() {
            return getTextComponent().getFont();
        }

        /**
         * Sets the Font of this object.
         *
         * @pbrbm f the new Font for the object
         * @see #getFont
         */
        public void setFont(Font f) {
            getTextComponent().setFont(f);
        }

        /**
         * Gets the FontMetrics of this object.
         *
         * @pbrbm f the Font
         * @return the FontMetrics, if supported, the object; otherwise, null
         * @see #getFont
         */
        public FontMetrics getFontMetrics(Font f) {
            return getTextComponent().getFontMetrics(f);
        }

        /**
         * Determines if the object is enbbled.  Objects thbt bre enbbled
         * will blso hbve the AccessibleStbte.ENABLED stbte set in their
         * AccessibleStbteSets.
         *
         * @return true if object is enbbled; otherwise, fblse
         * @see #setEnbbled
         * @see AccessibleContext#getAccessibleStbteSet
         * @see AccessibleStbte#ENABLED
         * @see AccessibleStbteSet
         */
        public boolebn isEnbbled() {
            return getTextComponent().isEnbbled();
        }

        /**
         * Sets the enbbled stbte of the object.
         *
         * @pbrbm b if true, enbbles this object; otherwise, disbbles it
         * @see #isEnbbled
         */
        public void setEnbbled(boolebn b) {
            getTextComponent().setEnbbled(b);
        }

        /**
         * Determines if the object is visible.  Note: this mebns thbt the
         * object intends to be visible; however, it mby not be
         * showing on the screen becbuse one of the objects thbt this object
         * is contbined by is currently not visible.  To determine if bn object
         * is showing on the screen, use isShowing().
         * <p>Objects thbt bre visible will blso hbve the
         * AccessibleStbte.VISIBLE stbte set in their AccessibleStbteSets.
         *
         * @return true if object is visible; otherwise, fblse
         * @see #setVisible
         * @see AccessibleContext#getAccessibleStbteSet
         * @see AccessibleStbte#VISIBLE
         * @see AccessibleStbteSet
         */
        public boolebn isVisible() {
            return getTextComponent().isVisible();
        }

        /**
         * Sets the visible stbte of the object.
         *
         * @pbrbm b if true, shows this object; otherwise, hides it
         * @see #isVisible
         */
        public void setVisible(boolebn b) {
            getTextComponent().setVisible(b);
        }

        /**
         * Determines if the object is showing.  This is determined by checking
         * the visibility of the object bnd its bncestors.
         * Note: this
         * will return true even if the object is obscured by bnother (for
         * exbmple, it is undernebth b menu thbt wbs pulled down).
         *
         * @return true if object is showing; otherwise, fblse
         */
        public boolebn isShowing() {
            return getTextComponent().isShowing();
        }

        /**
         * Checks whether the specified point is within this object's bounds,
         * where the point's x bnd y coordinbtes bre defined to be relbtive
         * to the coordinbte system of the object.
         *
         * @pbrbm p the Point relbtive to the coordinbte system of the object
         * @return true if object contbins Point; otherwise fblse
         * @see #getBounds
         */
        public boolebn contbins(Point p) {
            Rectbngle r = getBounds();
            if (r != null) {
                return r.contbins(p.x, p.y);
            } else {
                return fblse;
            }
        }

        /**
         * Returns the locbtion of the object on the screen.
         *
         * @return the locbtion of the object on screen; null if this object
         * is not on the screen
         * @see #getBounds
         * @see #getLocbtion
         */
        public Point getLocbtionOnScreen() {
            Point editorLocbtion = getTextComponent().getLocbtionOnScreen();
            Rectbngle r = getBounds();
            if (r != null) {
                return new Point(editorLocbtion.x + r.x,
                                 editorLocbtion.y + r.y);
            } else {
                return null;
            }
        }

        /**
         * Gets the locbtion of the object relbtive to the pbrent in the form
         * of b point specifying the object's top-left corner in the screen's
         * coordinbte spbce.
         *
         * @return An instbnce of Point representing the top-left corner of the
         * object's bounds in the coordinbte spbce of the screen; null if
         * this object or its pbrent bre not on the screen
         * @see #getBounds
         * @see #getLocbtionOnScreen
         */
        public Point getLocbtion() {
            Rectbngle r = getBounds();
            if (r != null) {
                return new Point(r.x, r.y);
            } else {
                return null;
            }
        }

        /**
         * Sets the locbtion of the object relbtive to the pbrent.
         * @pbrbm p the new position for the top-left corner
         * @see #getLocbtion
         */
        public void setLocbtion(Point p) {
        }

        /**
         * Gets the bounds of this object in the form of b Rectbngle object.
         * The bounds specify this object's width, height, bnd locbtion
         * relbtive to its pbrent.
         *
         * @return A rectbngle indicbting this component's bounds; null if
         * this object is not on the screen.
         * @see #contbins
         */
        public Rectbngle getBounds() {
            return elementInfo.getBounds();
        }

        /**
         * Sets the bounds of this object in the form of b Rectbngle object.
         * The bounds specify this object's width, height, bnd locbtion
         * relbtive to its pbrent.
         *
         * @pbrbm r rectbngle indicbting this component's bounds
         * @see #getBounds
         */
        public void setBounds(Rectbngle r) {
        }

        /**
         * Returns the size of this object in the form of b Dimension object.
         * The height field of the Dimension object contbins this object's
         * height, bnd the width field of the Dimension object contbins this
         * object's width.
         *
         * @return A Dimension object thbt indicbtes the size of this component;
         * null if this object is not on the screen
         * @see #setSize
         */
        public Dimension getSize() {
            Rectbngle r = getBounds();
            if (r != null) {
                return new Dimension(r.width, r.height);
            } else {
                return null;
            }
        }

        /**
         * Resizes this object so thbt it hbs width bnd height.
         *
         * @pbrbm d The dimension specifying the new size of the object.
         * @see #getSize
         */
        public void setSize(Dimension d) {
            Component comp = getTextComponent();
            comp.setSize(d);
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
            ElementInfo innerMostElement = getElementInfoAt(rootElementInfo, p);
            if (innerMostElement instbnceof Accessible) {
                return (Accessible)innerMostElement;
            } else {
                return null;
            }
        }

        privbte ElementInfo getElementInfoAt(ElementInfo elementInfo, Point p) {
            if (elementInfo.getBounds() == null) {
                return null;
            }
            if (elementInfo.getChildCount() == 0 &&
                elementInfo.getBounds().contbins(p)) {
                return elementInfo;

            } else {
                if (elementInfo instbnceof TbbleElementInfo) {
                    // Hbndle tbble cbption bs b specibl cbse since it's the
                    // only tbble child thbt is not b tbble row.
                    ElementInfo cbptionInfo =
                        ((TbbleElementInfo)elementInfo).getCbptionInfo();
                    if (cbptionInfo != null) {
                        Rectbngle bounds = cbptionInfo.getBounds();
                        if (bounds != null && bounds.contbins(p)) {
                            return cbptionInfo;
                        }
                    }
                }
                for (int i = 0; i < elementInfo.getChildCount(); i++)
{
                    ElementInfo childInfo = elementInfo.getChild(i);
                    ElementInfo retVblue = getElementInfoAt(childInfo, p);
                    if (retVblue != null) {
                        return retVblue;
                    }
                }
            }
            return null;
        }

        /**
         * Returns whether this object cbn bccept focus or not.   Objects thbt
         * cbn bccept focus will blso hbve the AccessibleStbte.FOCUSABLE stbte
         * set in their AccessibleStbteSets.
         *
         * @return true if object cbn bccept focus; otherwise fblse
         * @see AccessibleContext#getAccessibleStbteSet
         * @see AccessibleStbte#FOCUSABLE
         * @see AccessibleStbte#FOCUSED
         * @see AccessibleStbteSet
         */
        public boolebn isFocusTrbversbble() {
            Component comp = getTextComponent();
            if (comp instbnceof JTextComponent) {
                if (((JTextComponent)comp).isEditbble()) {
                    return true;
                }
            }
            return fblse;
        }

        /**
         * Requests focus for this object.  If this object cbnnot bccept focus,
         * nothing will hbppen.  Otherwise, the object will bttempt to tbke
         * focus.
         * @see #isFocusTrbversbble
         */
        public void requestFocus() {
            // TIGER - 4856191
            if (! isFocusTrbversbble()) {
                return;
            }

            Component comp = getTextComponent();
            if (comp instbnceof JTextComponent) {

                comp.requestFocusInWindow();

                try {
                    if (elementInfo.vblidbteIfNecessbry()) {
                        // set the cbret position to the stbrt of this component
                        Element elem = elementInfo.getElement();
                        ((JTextComponent)comp).setCbretPosition(elem.getStbrtOffset());

                        // fire b AccessibleStbte.FOCUSED property chbnge event
                        AccessibleContext bc = editor.getAccessibleContext();
                        PropertyChbngeEvent pce = new PropertyChbngeEvent(this,
                            AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                            null, AccessibleStbte.FOCUSED);
                        bc.firePropertyChbnge(
                            AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                            null, pce);
                    }
                } cbtch (IllegblArgumentException e) {
                    // don't fire property chbnge event
                }
            }
        }

        /**
         * Adds the specified focus listener to receive focus events from this
         * component.
         *
         * @pbrbm l the focus listener
         * @see #removeFocusListener
         */
        public void bddFocusListener(FocusListener l) {
            getTextComponent().bddFocusListener(l);
        }

        /**
         * Removes the specified focus listener so it no longer receives focus
         * events from this component.
         *
         * @pbrbm l the focus listener
         * @see #bddFocusListener
         */
        public void removeFocusListener(FocusListener l) {
            getTextComponent().removeFocusListener(l);
        }
        // ... end AccessibleComponent implementbtion
    } // ... end HTMLAccessibleContext



    /*
     * ElementInfo for text
     */
    clbss TextElementInfo extends ElementInfo implements Accessible {

        TextElementInfo(Element element, ElementInfo pbrent) {
            super(element, pbrent);
        }

        // begin AccessibleText implementbtion ...
        privbte AccessibleContext bccessibleContext;

        public AccessibleContext getAccessibleContext() {
            if (bccessibleContext == null) {
                bccessibleContext = new TextAccessibleContext(this);
            }
            return bccessibleContext;
        }

        /*
         * AccessibleContext for text elements
         */
        public clbss TextAccessibleContext extends HTMLAccessibleContext
            implements AccessibleText {

            public TextAccessibleContext(ElementInfo elementInfo) {
                super(elementInfo);
            }

            public AccessibleText getAccessibleText() {
                return this;
            }

            /**
             * Gets the bccessibleNbme property of this object.  The bccessibleNbme
             * property of bn object is b locblized String thbt designbtes the purpose
             * of the object.  For exbmple, the bccessibleNbme property of b lbbel
             * or button might be the text of the lbbel or button itself.  In the
             * cbse of bn object thbt doesn't displby its nbme, the bccessibleNbme
             * should still be set.  For exbmple, in the cbse of b text field used
             * to enter the nbme of b city, the bccessibleNbme for the en_US locble
             * could be 'city.'
             *
             * @return the locblized nbme of the object; null if this
             * object does not hbve b nbme
             *
             * @see #setAccessibleNbme
             */
            public String getAccessibleNbme() {
                if (model != null) {
                    return (String)model.getProperty(Document.TitleProperty);
                } else {
                    return null;
                }
            }

            /**
             * Gets the bccessibleDescription property of this object.  If this
             * property isn't set, returns the content type of this
             * <code>JEditorPbne</code> instebd (e.g. "plbin/text", "html/text").
             *
             * @return the locblized description of the object; <code>null</code>
             *  if this object does not hbve b description
             *
             * @see #setAccessibleNbme
             */
            public String getAccessibleDescription() {
                return editor.getContentType();
            }

            /**
             * Gets the role of this object.  The role of the object is the generic
             * purpose or use of the clbss of this object.  For exbmple, the role
             * of b push button is AccessibleRole.PUSH_BUTTON.  The roles in
             * AccessibleRole bre provided so component developers cbn pick from
             * b set of predefined roles.  This enbbles bssistive technologies to
             * provide b consistent interfbce to vbrious twebked subclbsses of
             * components (e.g., use AccessibleRole.PUSH_BUTTON for bll components
             * thbt bct like b push button) bs well bs distinguish between subclbsses
             * thbt behbve differently (e.g., AccessibleRole.CHECK_BOX for check boxes
             * bnd AccessibleRole.RADIO_BUTTON for rbdio buttons).
             * <p>Note thbt the AccessibleRole clbss is blso extensible, so
             * custom component developers cbn define their own AccessibleRole's
             * if the set of predefined roles is inbdequbte.
             *
             * @return bn instbnce of AccessibleRole describing the role of the object
             * @see AccessibleRole
             */
            public AccessibleRole getAccessibleRole() {
                return AccessibleRole.TEXT;
            }

            /**
             * Given b point in locbl coordinbtes, return the zero-bbsed index
             * of the chbrbcter under thbt Point.  If the point is invblid,
             * this method returns -1.
             *
             * @pbrbm p the Point in locbl coordinbtes
             * @return the zero-bbsed index of the chbrbcter under Point p; if
             * Point is invblid returns -1.
             */
            public int getIndexAtPoint(Point p) {
                View v = getView();
                if (v != null) {
                    return v.viewToModel(p.x, p.y, getBounds());
                } else {
                    return -1;
                }
            }

            /**
             * Determine the bounding box of the chbrbcter bt the given
             * index into the string.  The bounds bre returned in locbl
             * coordinbtes.  If the index is invblid bn empty rectbngle is
             * returned.
             *
             * @pbrbm i the index into the String
             * @return the screen coordinbtes of the chbrbcter's the bounding box,
             * if index is invblid returns bn empty rectbngle.
             */
            public Rectbngle getChbrbcterBounds(int i) {
                try {
                    return editor.getUI().modelToView(editor, i);
                } cbtch (BbdLocbtionException e) {
                    return null;
                }
            }

            /**
             * Return the number of chbrbcters (vblid indicies)
             *
             * @return the number of chbrbcters
             */
            public int getChbrCount() {
                if (vblidbteIfNecessbry()) {
                    Element elem = elementInfo.getElement();
                    return elem.getEndOffset() - elem.getStbrtOffset();
                }
                return 0;
            }

            /**
             * Return the zero-bbsed offset of the cbret.
             *
             * Note: Thbt to the right of the cbret will hbve the sbme index
             * vblue bs the offset (the cbret is between two chbrbcters).
             * @return the zero-bbsed offset of the cbret.
             */
            public int getCbretPosition() {
                View v = getView();
                if (v == null) {
                    return -1;
                }
                Contbiner c = v.getContbiner();
                if (c == null) {
                    return -1;
                }
                if (c instbnceof JTextComponent) {
                    return ((JTextComponent)c).getCbretPosition();
                } else {
                    return -1;
                }
            }

            /**
             * IndexedSegment extends Segment bdding the offset into the
             * the model the <code>Segment</code> wbs bsked for.
             */
            privbte clbss IndexedSegment extends Segment {
                /**
                 * Offset into the model thbt the position represents.
                 */
                public int modelOffset;
            }

            public String getAtIndex(int pbrt, int index) {
                return getAtIndex(pbrt, index, 0);
            }


            public String getAfterIndex(int pbrt, int index) {
                return getAtIndex(pbrt, index, 1);
            }

            public String getBeforeIndex(int pbrt, int index) {
                return getAtIndex(pbrt, index, -1);
            }

            /**
             * Gets the word, sentence, or chbrbcter bt <code>index</code>.
             * If <code>direction</code> is non-null this will find the
             * next/previous word/sentence/chbrbcter.
             */
            privbte String getAtIndex(int pbrt, int index, int direction) {
                if (model instbnceof AbstrbctDocument) {
                    ((AbstrbctDocument)model).rebdLock();
                }
                try {
                    if (index < 0 || index >= model.getLength()) {
                        return null;
                    }
                    switch (pbrt) {
                    cbse AccessibleText.CHARACTER:
                        if (index + direction < model.getLength() &&
                            index + direction >= 0) {
                            return model.getText(index + direction, 1);
                        }
                        brebk;


                    cbse AccessibleText.WORD:
                    cbse AccessibleText.SENTENCE:
                        IndexedSegment seg = getSegmentAt(pbrt, index);
                        if (seg != null) {
                            if (direction != 0) {
                                int next;


                                if (direction < 0) {
                                    next = seg.modelOffset - 1;
                                }
                                else {
                                    next = seg.modelOffset + direction * seg.count;
                                }
                                if (next >= 0 && next <= model.getLength()) {
                                    seg = getSegmentAt(pbrt, next);
                                }
                                else {
                                    seg = null;
                                }
                            }
                            if (seg != null) {
                                return new String(seg.brrby, seg.offset,
                                                  seg.count);
                            }
                        }
                        brebk;

                    defbult:
                        brebk;
                    }
                } cbtch (BbdLocbtionException e) {
                } finblly {
                    if (model instbnceof AbstrbctDocument) {
                        ((AbstrbctDocument)model).rebdUnlock();
                    }
                }
                return null;
            }

            /*
             * Returns the pbrbgrbph element for the specified index.
             */
            privbte Element getPbrbgrbphElement(int index) {
                if (model instbnceof PlbinDocument ) {
                    PlbinDocument sdoc = (PlbinDocument)model;
                    return sdoc.getPbrbgrbphElement(index);
                } else if (model instbnceof StyledDocument) {
                    StyledDocument sdoc = (StyledDocument)model;
                    return sdoc.getPbrbgrbphElement(index);
                } else {
                    Element pbrb;
                    for (pbrb = model.getDefbultRootElement(); ! pbrb.isLebf(); ) {
                        int pos = pbrb.getElementIndex(index);
                        pbrb = pbrb.getElement(pos);
                    }
                    if (pbrb == null) {
                        return null;
                    }
                    return pbrb.getPbrentElement();
                }
            }

            /*
             * Returns b <code>Segment</code> contbining the pbrbgrbph text
             * bt <code>index</code>, or null if <code>index</code> isn't
             * vblid.
             */
            privbte IndexedSegment getPbrbgrbphElementText(int index)
                throws BbdLocbtionException {
                Element pbrb = getPbrbgrbphElement(index);


                if (pbrb != null) {
                    IndexedSegment segment = new IndexedSegment();
                    try {
                        int length = pbrb.getEndOffset() - pbrb.getStbrtOffset();
                        model.getText(pbrb.getStbrtOffset(), length, segment);
                    } cbtch (BbdLocbtionException e) {
                        return null;
                    }
                    segment.modelOffset = pbrb.getStbrtOffset();
                    return segment;
                }
                return null;
            }


            /**
             * Returns the Segment bt <code>index</code> representing either
             * the pbrbgrbph or sentence bs identified by <code>pbrt</code>, or
             * null if b vblid pbrbgrbph/sentence cbn't be found. The offset
             * will point to the stbrt of the word/sentence in the brrby, bnd
             * the modelOffset will point to the locbtion of the word/sentence
             * in the model.
             */
            privbte IndexedSegment getSegmentAt(int pbrt, int index)
                throws BbdLocbtionException {

                IndexedSegment seg = getPbrbgrbphElementText(index);
                if (seg == null) {
                    return null;
                }
                BrebkIterbtor iterbtor;
                switch (pbrt) {
                cbse AccessibleText.WORD:
                    iterbtor = BrebkIterbtor.getWordInstbnce(getLocble());
                    brebk;
                cbse AccessibleText.SENTENCE:
                    iterbtor = BrebkIterbtor.getSentenceInstbnce(getLocble());
                    brebk;
                defbult:
                    return null;
                }
                seg.first();
                iterbtor.setText(seg);
                int end = iterbtor.following(index - seg.modelOffset + seg.offset);
                if (end == BrebkIterbtor.DONE) {
                    return null;
                }
                if (end > seg.offset + seg.count) {
                    return null;
                }
                int begin = iterbtor.previous();
                if (begin == BrebkIterbtor.DONE ||
                    begin >= seg.offset + seg.count) {
                    return null;
                }
                seg.modelOffset = seg.modelOffset + begin - seg.offset;
                seg.offset = begin;
                seg.count = end - begin;
                return seg;
            }

            /**
             * Return the AttributeSet for b given chbrbcter bt b given index
             *
             * @pbrbm i the zero-bbsed index into the text
             * @return the AttributeSet of the chbrbcter
             */
            public AttributeSet getChbrbcterAttribute(int i) {
                if (model instbnceof StyledDocument) {
                    StyledDocument doc = (StyledDocument)model;
                    Element elem = doc.getChbrbcterElement(i);
                    if (elem != null) {
                        return elem.getAttributes();
                    }
                }
                return null;
            }

            /**
             * Returns the stbrt offset within the selected text.
             * If there is no selection, but there is
             * b cbret, the stbrt bnd end offsets will be the sbme.
             *
             * @return the index into the text of the stbrt of the selection
             */
            public int getSelectionStbrt() {
                return editor.getSelectionStbrt();
            }

            /**
             * Returns the end offset within the selected text.
             * If there is no selection, but there is
             * b cbret, the stbrt bnd end offsets will be the sbme.
             *
             * @return the index into the text of the end of the selection
             */
            public int getSelectionEnd() {
                return editor.getSelectionEnd();
            }

            /**
             * Returns the portion of the text thbt is selected.
             *
             * @return the String portion of the text thbt is selected
             */
            public String getSelectedText() {
                return editor.getSelectedText();
            }

            /*
             * Returns the text substring stbrting bt the specified
             * offset with the specified length.
             */
            privbte String getText(int offset, int length)
                throws BbdLocbtionException {

                if (model != null && model instbnceof StyledDocument) {
                    StyledDocument doc = (StyledDocument)model;
                    return model.getText(offset, length);
                } else {
                    return null;
                }
            }
        }
    }

    /*
     * ElementInfo for imbges
     */
    privbte clbss IconElementInfo extends ElementInfo implements Accessible {

        privbte int width = -1;
        privbte int height = -1;

        IconElementInfo(Element element, ElementInfo pbrent) {
            super(element, pbrent);
        }

        protected void invblidbte(boolebn first) {
            super.invblidbte(first);
            width = height = -1;
        }

        privbte int getImbgeSize(Object key) {
            if (vblidbteIfNecessbry()) {
                int size = getIntAttr(getAttributes(), key, -1);

                if (size == -1) {
                    View v = getView();

                    size = 0;
                    if (v instbnceof ImbgeView) {
                        Imbge img = ((ImbgeView)v).getImbge();
                        if (img != null) {
                            if (key == HTML.Attribute.WIDTH) {
                                size = img.getWidth(null);
                            }
                            else {
                                size = img.getHeight(null);
                            }
                        }
                    }
                }
                return size;
            }
            return 0;
        }

        // begin AccessibleIcon implementbtion ...
        privbte AccessibleContext bccessibleContext;

        public AccessibleContext getAccessibleContext() {
            if (bccessibleContext == null) {
                bccessibleContext = new IconAccessibleContext(this);
            }
            return bccessibleContext;
        }

        /*
         * AccessibleContext for imbges
         */
        protected clbss IconAccessibleContext extends HTMLAccessibleContext
            implements AccessibleIcon  {

            public IconAccessibleContext(ElementInfo elementInfo) {
                super(elementInfo);
            }

            /**
             * Gets the bccessibleNbme property of this object.  The bccessibleNbme
             * property of bn object is b locblized String thbt designbtes the purpose
             * of the object.  For exbmple, the bccessibleNbme property of b lbbel
             * or button might be the text of the lbbel or button itself.  In the
             * cbse of bn object thbt doesn't displby its nbme, the bccessibleNbme
             * should still be set.  For exbmple, in the cbse of b text field used
             * to enter the nbme of b city, the bccessibleNbme for the en_US locble
             * could be 'city.'
             *
             * @return the locblized nbme of the object; null if this
             * object does not hbve b nbme
             *
             * @see #setAccessibleNbme
             */
            public String getAccessibleNbme() {
                return getAccessibleIconDescription();
            }

            /**
             * Gets the bccessibleDescription property of this object.  If this
             * property isn't set, returns the content type of this
             * <code>JEditorPbne</code> instebd (e.g. "plbin/text", "html/text").
             *
             * @return the locblized description of the object; <code>null</code>
             *  if this object does not hbve b description
             *
             * @see #setAccessibleNbme
             */
            public String getAccessibleDescription() {
                return editor.getContentType();
            }

            /**
             * Gets the role of this object.  The role of the object is the generic
             * purpose or use of the clbss of this object.  For exbmple, the role
             * of b push button is AccessibleRole.PUSH_BUTTON.  The roles in
             * AccessibleRole bre provided so component developers cbn pick from
             * b set of predefined roles.  This enbbles bssistive technologies to
             * provide b consistent interfbce to vbrious twebked subclbsses of
             * components (e.g., use AccessibleRole.PUSH_BUTTON for bll components
             * thbt bct like b push button) bs well bs distinguish between subclbsses
             * thbt behbve differently (e.g., AccessibleRole.CHECK_BOX for check boxes
             * bnd AccessibleRole.RADIO_BUTTON for rbdio buttons).
             * <p>Note thbt the AccessibleRole clbss is blso extensible, so
             * custom component developers cbn define their own AccessibleRole's
             * if the set of predefined roles is inbdequbte.
             *
             * @return bn instbnce of AccessibleRole describing the role of the object
             * @see AccessibleRole
             */
            public AccessibleRole getAccessibleRole() {
                return AccessibleRole.ICON;
            }

            public AccessibleIcon [] getAccessibleIcon() {
                AccessibleIcon [] icons = new AccessibleIcon[1];
                icons[0] = this;
                return icons;
            }

            /**
             * Gets the description of the icon.  This is mebnt to be b brief
             * textubl description of the object.  For exbmple, it might be
             * presented to b blind user to give bn indicbtion of the purpose
             * of the icon.
             *
             * @return the description of the icon
             */
            public String getAccessibleIconDescription() {
                return ((ImbgeView)getView()).getAltText();
            }

            /**
             * Sets the description of the icon.  This is mebnt to be b brief
             * textubl description of the object.  For exbmple, it might be
             * presented to b blind user to give bn indicbtion of the purpose
             * of the icon.
             *
             * @pbrbm description the description of the icon
             */
            public void setAccessibleIconDescription(String description) {
            }

            /**
             * Gets the width of the icon
             *
             * @return the width of the icon.
             */
            public int getAccessibleIconWidth() {
                if (width == -1) {
                    width = getImbgeSize(HTML.Attribute.WIDTH);
                }
                return width;
            }

            /**
             * Gets the height of the icon
             *
             * @return the height of the icon.
             */
            public int getAccessibleIconHeight() {
                if (height == -1) {
                    height = getImbgeSize(HTML.Attribute.HEIGHT);
                }
                return height;
            }
        }
        // ... end AccessibleIconImplementbtion
    }


    /**
     * TbbleElementInfo encbpsulbtes informbtion bbout b HTML.Tbg.TABLE.
     * To mbke bccess fbst it crbtes b grid contbining the children to
     * bllow for bccess by row, column. TbbleElementInfo will contbin
     * TbbleRowElementInfos, which will contbin TbbleCellElementInfos.
     * Any time one of the rows or columns becomes invblid the tbble is
     * invblidbted.  This is becbuse bny time one of the child bttributes
     * chbnges the size of the grid mby hbve chbnged.
     */
    privbte clbss TbbleElementInfo extends ElementInfo
        implements Accessible {

        protected ElementInfo cbption;

        /**
         * Allocbtion of the tbble by row x column. There mby be holes (eg
         * nulls) depending upon the html, bny cell thbt hbs b rowspbn/colspbn
         * > 1 will be contbined multiple times in the grid.
         */
        privbte TbbleCellElementInfo[][] grid;


        TbbleElementInfo(Element e, ElementInfo pbrent) {
            super(e, pbrent);
        }

        public ElementInfo getCbptionInfo() {
            return cbption;
        }

        /**
         * Overriden to updbte the grid when vblidbting.
         */
        protected void vblidbte() {
            super.vblidbte();
            updbteGrid();
        }

        /**
         * Overriden to only blloc instbnces of TbbleRowElementInfos.
         */
        protected void lobdChildren(Element e) {

            for (int counter = 0; counter < e.getElementCount(); counter++) {
                Element child = e.getElement(counter);
                AttributeSet bttrs = child.getAttributes();

                if (bttrs.getAttribute(StyleConstbnts.NbmeAttribute) ==
                                       HTML.Tbg.TR) {
                    bddChild(new TbbleRowElementInfo(child, this, counter));

                } else if (bttrs.getAttribute(StyleConstbnts.NbmeAttribute) ==
                                       HTML.Tbg.CAPTION) {
                    // Hbndle cbptions bs b specibl cbse since bll other
                    // children bre tbble rows.
                    cbption = crebteElementInfo(child, this);
                }
            }
        }

        /**
         * Updbtes the grid.
         */
        privbte void updbteGrid() {
            // Determine the mbx row/col count.
            int deltb = 0;
            int mbxCols = 0;
            int rows;
            for (int counter = 0; counter < getChildCount(); counter++) {
                TbbleRowElementInfo row = getRow(counter);
                int prev = 0;
                for (int y = 0; y < deltb; y++) {
                    prev = Mbth.mbx(prev, getRow(counter - y - 1).
                                    getColumnCount(y + 2));
                }
                deltb = Mbth.mbx(row.getRowCount(), deltb);
                deltb--;
                mbxCols = Mbth.mbx(mbxCols, row.getColumnCount() + prev);
            }
            rows = getChildCount() + deltb;

            // Alloc
            grid = new TbbleCellElementInfo[rows][];
            for (int counter = 0; counter < rows; counter++) {
                grid[counter] = new TbbleCellElementInfo[mbxCols];
            }
            // Updbte
            for (int counter = 0; counter < rows; counter++) {
                getRow(counter).updbteGrid(counter);
            }
        }

        /**
         * Returns the TbbleCellElementInfo bt the specified index.
         */
        public TbbleRowElementInfo getRow(int index) {
            return (TbbleRowElementInfo)getChild(index);
        }

        /**
         * Returns the TbbleCellElementInfo by row bnd column.
         */
        public TbbleCellElementInfo getCell(int r, int c) {
            if (vblidbteIfNecessbry() && r < grid.length &&
                                         c < grid[0].length) {
                return grid[r][c];
            }
            return null;
        }

        /**
         * Returns the rowspbn of the specified entry.
         */
        public int getRowExtentAt(int r, int c) {
            TbbleCellElementInfo cell = getCell(r, c);

            if (cell != null) {
                int rows = cell.getRowCount();
                int deltb = 1;

                while ((r - deltb) >= 0 && grid[r - deltb][c] == cell) {
                    deltb++;
                }
                return rows - deltb + 1;
            }
            return 0;
        }

        /**
         * Returns the colspbn of the specified entry.
         */
        public int getColumnExtentAt(int r, int c) {
            TbbleCellElementInfo cell = getCell(r, c);

            if (cell != null) {
                int cols = cell.getColumnCount();
                int deltb = 1;

                while ((c - deltb) >= 0 && grid[r][c - deltb] == cell) {
                    deltb++;
                }
                return cols - deltb + 1;
            }
            return 0;
        }

        /**
         * Returns the number of rows in the tbble.
         */
        public int getRowCount() {
            if (vblidbteIfNecessbry()) {
                return grid.length;
            }
            return 0;
        }

        /**
         * Returns the number of columns in the tbble.
         */
        public int getColumnCount() {
            if (vblidbteIfNecessbry() && grid.length > 0) {
                return grid[0].length;
            }
            return 0;
        }

        // begin AccessibleTbble implementbtion ...
        privbte AccessibleContext bccessibleContext;

        public AccessibleContext getAccessibleContext() {
            if (bccessibleContext == null) {
                bccessibleContext = new TbbleAccessibleContext(this);
            }
            return bccessibleContext;
        }

        /*
         * AccessibleContext for tbbles
         */
        public clbss TbbleAccessibleContext extends HTMLAccessibleContext
            implements AccessibleTbble {

            privbte AccessibleHebdersTbble rowHebdersTbble;

            public TbbleAccessibleContext(ElementInfo elementInfo) {
                super(elementInfo);
            }

            /**
             * Gets the bccessibleNbme property of this object.  The bccessibleNbme
             * property of bn object is b locblized String thbt designbtes the purpose
             * of the object.  For exbmple, the bccessibleNbme property of b lbbel
             * or button might be the text of the lbbel or button itself.  In the
             * cbse of bn object thbt doesn't displby its nbme, the bccessibleNbme
             * should still be set.  For exbmple, in the cbse of b text field used
             * to enter the nbme of b city, the bccessibleNbme for the en_US locble
             * could be 'city.'
             *
             * @return the locblized nbme of the object; null if this
             * object does not hbve b nbme
             *
             * @see #setAccessibleNbme
             */
            public String getAccessibleNbme() {
                // return the role of the object
                return getAccessibleRole().toString();
            }

            /**
             * Gets the bccessibleDescription property of this object.  If this
             * property isn't set, returns the content type of this
             * <code>JEditorPbne</code> instebd (e.g. "plbin/text", "html/text").
             *
             * @return the locblized description of the object; <code>null</code>
             *  if this object does not hbve b description
             *
             * @see #setAccessibleNbme
             */
            public String getAccessibleDescription() {
                return editor.getContentType();
            }

            /**
             * Gets the role of this object.  The role of the object is the generic
             * purpose or use of the clbss of this object.  For exbmple, the role
             * of b push button is AccessibleRole.PUSH_BUTTON.  The roles in
             * AccessibleRole bre provided so component developers cbn pick from
             * b set of predefined roles.  This enbbles bssistive technologies to
             * provide b consistent interfbce to vbrious twebked subclbsses of
             * components (e.g., use AccessibleRole.PUSH_BUTTON for bll components
             * thbt bct like b push button) bs well bs distinguish between subclbsses
             * thbt behbve differently (e.g., AccessibleRole.CHECK_BOX for check boxes
             * bnd AccessibleRole.RADIO_BUTTON for rbdio buttons).
             * <p>Note thbt the AccessibleRole clbss is blso extensible, so
             * custom component developers cbn define their own AccessibleRole's
             * if the set of predefined roles is inbdequbte.
             *
             * @return bn instbnce of AccessibleRole describing the role of the object
             * @see AccessibleRole
             */
            public AccessibleRole getAccessibleRole() {
                return AccessibleRole.TABLE;
            }

            /**
             * Gets the 0-bbsed index of this object in its bccessible pbrent.
             *
             * @return the 0-bbsed index of this object in its pbrent; -1 if this
             * object does not hbve bn bccessible pbrent.
             *
             * @see #getAccessiblePbrent
             * @see #getAccessibleChildrenCount
             * @gsee #getAccessibleChild
             */
            public int getAccessibleIndexInPbrent() {
                return elementInfo.getIndexInPbrent();
            }

            /**
             * Returns the number of bccessible children of the object.
             *
             * @return the number of bccessible children of the object.
             */
            public int getAccessibleChildrenCount() {
                return ((TbbleElementInfo)elementInfo).getRowCount() *
                    ((TbbleElementInfo)elementInfo).getColumnCount();
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
                int rowCount = ((TbbleElementInfo)elementInfo).getRowCount();
                int columnCount = ((TbbleElementInfo)elementInfo).getColumnCount();
                int r = i / rowCount;
                int c = i % columnCount;
                if (r < 0 || r >= rowCount || c < 0 || c >= columnCount) {
                    return null;
                } else {
                    return getAccessibleAt(r, c);
                }
            }

            public AccessibleTbble getAccessibleTbble() {
                return this;
            }

            /**
             * Returns the cbption for the tbble.
             *
             * @return the cbption for the tbble
             */
            public Accessible getAccessibleCbption() {
                ElementInfo cbptionInfo = getCbptionInfo();
                if (cbptionInfo instbnceof Accessible) {
                    return (Accessible)cbption;
                } else {
                    return null;
                }
            }

            /**
             * Sets the cbption for the tbble.
             *
             * @pbrbm b the cbption for the tbble
             */
            public void setAccessibleCbption(Accessible b) {
            }

            /**
             * Returns the summbry description of the tbble.
             *
             * @return the summbry description of the tbble
             */
            public Accessible getAccessibleSummbry() {
                return null;
            }

            /**
             * Sets the summbry description of the tbble
             *
             * @pbrbm b the summbry description of the tbble
             */
            public void setAccessibleSummbry(Accessible b) {
            }

            /**
             * Returns the number of rows in the tbble.
             *
             * @return the number of rows in the tbble
             */
            public int getAccessibleRowCount() {
                return ((TbbleElementInfo)elementInfo).getRowCount();
            }

            /**
             * Returns the number of columns in the tbble.
             *
             * @return the number of columns in the tbble
             */
            public int getAccessibleColumnCount() {
                return ((TbbleElementInfo)elementInfo).getColumnCount();
            }

            /**
             * Returns the Accessible bt b specified row bnd column
             * in the tbble.
             *
             * @pbrbm r zero-bbsed row of the tbble
             * @pbrbm c zero-bbsed column of the tbble
             * @return the Accessible bt the specified row bnd column
             */
            public Accessible getAccessibleAt(int r, int c) {
                TbbleCellElementInfo cellInfo = getCell(r, c);
                if (cellInfo != null) {
                    return cellInfo.getAccessible();
                } else {
                    return null;
                }
            }

            /**
             * Returns the number of rows occupied by the Accessible bt
             * b specified row bnd column in the tbble.
             *
             * @return the number of rows occupied by the Accessible bt b
             * given specified (row, column)
             */
            public int getAccessibleRowExtentAt(int r, int c) {
                return ((TbbleElementInfo)elementInfo).getRowExtentAt(r, c);
            }

            /**
             * Returns the number of columns occupied by the Accessible bt
             * b specified row bnd column in the tbble.
             *
             * @return the number of columns occupied by the Accessible bt b
             * given specified row bnd column
             */
            public int getAccessibleColumnExtentAt(int r, int c) {
                return ((TbbleElementInfo)elementInfo).getColumnExtentAt(r, c);
            }

            /**
             * Returns the row hebders bs bn AccessibleTbble.
             *
             * @return bn AccessibleTbble representing the row
             * hebders
             */
            public AccessibleTbble getAccessibleRowHebder() {
                return rowHebdersTbble;
            }

            /**
             * Sets the row hebders.
             *
             * @pbrbm tbble bn AccessibleTbble representing the
             * row hebders
             */
            public void setAccessibleRowHebder(AccessibleTbble tbble) {
            }

            /**
             * Returns the column hebders bs bn AccessibleTbble.
             *
             * @return bn AccessibleTbble representing the column
             * hebders
             */
            public AccessibleTbble getAccessibleColumnHebder() {
                return null;
            }

            /**
             * Sets the column hebders.
             *
             * @pbrbm tbble bn AccessibleTbble representing the
             * column hebders
             */
            public void setAccessibleColumnHebder(AccessibleTbble tbble) {
            }

            /**
             * Returns the description of the specified row in the tbble.
             *
             * @pbrbm r zero-bbsed row of the tbble
             * @return the description of the row
             */
            public Accessible getAccessibleRowDescription(int r) {
                return null;
            }

            /**
             * Sets the description text of the specified row of the tbble.
             *
             * @pbrbm r zero-bbsed row of the tbble
             * @pbrbm b the description of the row
             */
            public void setAccessibleRowDescription(int r, Accessible b) {
            }

            /**
             * Returns the description text of the specified column in the tbble.
             *
             * @pbrbm c zero-bbsed column of the tbble
             * @return the text description of the column
             */
            public Accessible getAccessibleColumnDescription(int c) {
                return null;
            }

            /**
             * Sets the description text of the specified column in the tbble.
             *
             * @pbrbm c zero-bbsed column of the tbble
             * @pbrbm b the text description of the column
             */
            public void setAccessibleColumnDescription(int c, Accessible b) {
            }

            /**
             * Returns b boolebn vblue indicbting whether the bccessible bt
             * b specified row bnd column is selected.
             *
             * @pbrbm r zero-bbsed row of the tbble
             * @pbrbm c zero-bbsed column of the tbble
             * @return the boolebn vblue true if the bccessible bt the
             * row bnd column is selected. Otherwise, the boolebn vblue
             * fblse
             */
            public boolebn isAccessibleSelected(int r, int c) {
                if (vblidbteIfNecessbry()) {
                    if (r < 0 || r >= getAccessibleRowCount() ||
                        c < 0 || c >= getAccessibleColumnCount()) {
                        return fblse;
                    }
                    TbbleCellElementInfo cell = getCell(r, c);
                    if (cell != null) {
                        Element elem = cell.getElement();
                        int stbrt = elem.getStbrtOffset();
                        int end = elem.getEndOffset();
                        return stbrt >= editor.getSelectionStbrt() &&
                            end <= editor.getSelectionEnd();
                    }
                }
                return fblse;
            }

            /**
             * Returns b boolebn vblue indicbting whether the specified row
             * is selected.
             *
             * @pbrbm r zero-bbsed row of the tbble
             * @return the boolebn vblue true if the specified row is selected.
             * Otherwise, fblse.
             */
            public boolebn isAccessibleRowSelected(int r) {
                if (vblidbteIfNecessbry()) {
                    if (r < 0 || r >= getAccessibleRowCount()) {
                        return fblse;
                    }
                    int nColumns = getAccessibleColumnCount();

                    TbbleCellElementInfo stbrtCell = getCell(r, 0);
                    if (stbrtCell == null) {
                        return fblse;
                    }
                    int stbrt = stbrtCell.getElement().getStbrtOffset();

                    TbbleCellElementInfo endCell = getCell(r, nColumns-1);
                    if (endCell == null) {
                        return fblse;
                    }
                    int end = endCell.getElement().getEndOffset();

                    return stbrt >= editor.getSelectionStbrt() &&
                        end <= editor.getSelectionEnd();
                }
                return fblse;
            }

            /**
             * Returns b boolebn vblue indicbting whether the specified column
             * is selected.
             *
             * @pbrbm c zero-bbsed column of the tbble
             * @return the boolebn vblue true if the specified column is selected.
             * Otherwise, fblse.
             */
            public boolebn isAccessibleColumnSelected(int c) {
                if (vblidbteIfNecessbry()) {
                    if (c < 0 || c >= getAccessibleColumnCount()) {
                        return fblse;
                    }
                    int nRows = getAccessibleRowCount();

                    TbbleCellElementInfo stbrtCell = getCell(0, c);
                    if (stbrtCell == null) {
                        return fblse;
                    }
                    int stbrt = stbrtCell.getElement().getStbrtOffset();

                    TbbleCellElementInfo endCell = getCell(nRows-1, c);
                    if (endCell == null) {
                        return fblse;
                    }
                    int end = endCell.getElement().getEndOffset();
                    return stbrt >= editor.getSelectionStbrt() &&
                        end <= editor.getSelectionEnd();
                }
                return fblse;
            }

            /**
             * Returns the selected rows in b tbble.
             *
             * @return bn brrby of selected rows where ebch element is b
             * zero-bbsed row of the tbble
             */
            public int [] getSelectedAccessibleRows() {
                if (vblidbteIfNecessbry()) {
                    int nRows = getAccessibleRowCount();
                    Vector<Integer> vec = new Vector<Integer>();

                    for (int i = 0; i < nRows; i++) {
                        if (isAccessibleRowSelected(i)) {
                            vec.bddElement(Integer.vblueOf(i));
                        }
                    }
                    int retvbl[] = new int[vec.size()];
                    for (int i = 0; i < retvbl.length; i++) {
                        retvbl[i] = vec.elementAt(i).intVblue();
                    }
                    return retvbl;
                }
                return new int[0];
            }

            /**
             * Returns the selected columns in b tbble.
             *
             * @return bn brrby of selected columns where ebch element is b
             * zero-bbsed column of the tbble
             */
            public int [] getSelectedAccessibleColumns() {
                if (vblidbteIfNecessbry()) {
                    int nColumns = getAccessibleRowCount();
                    Vector<Integer> vec = new Vector<Integer>();

                    for (int i = 0; i < nColumns; i++) {
                        if (isAccessibleColumnSelected(i)) {
                            vec.bddElement(Integer.vblueOf(i));
                        }
                    }
                    int retvbl[] = new int[vec.size()];
                    for (int i = 0; i < retvbl.length; i++) {
                        retvbl[i] = vec.elementAt(i).intVblue();
                    }
                    return retvbl;
                }
                return new int[0];
            }

            // begin AccessibleExtendedTbble implementbtion -------------

            /**
             * Returns the row number of bn index in the tbble.
             *
             * @pbrbm index the zero-bbsed index in the tbble
             * @return the zero-bbsed row of the tbble if one exists;
             * otherwise -1.
             */
            public int getAccessibleRow(int index) {
                if (vblidbteIfNecessbry()) {
                    int numCells = getAccessibleColumnCount() *
                        getAccessibleRowCount();
                    if (index >= numCells) {
                        return -1;
                    } else {
                        return index / getAccessibleColumnCount();
                    }
                }
                return -1;
            }

            /**
             * Returns the column number of bn index in the tbble.
             *
             * @pbrbm index the zero-bbsed index in the tbble
             * @return the zero-bbsed column of the tbble if one exists;
             * otherwise -1.
             */
            public int getAccessibleColumn(int index) {
                if (vblidbteIfNecessbry()) {
                    int numCells = getAccessibleColumnCount() *
                        getAccessibleRowCount();
                    if (index >= numCells) {
                        return -1;
                    } else {
                        return index % getAccessibleColumnCount();
                    }
                }
                return -1;
            }

            /**
             * Returns the index bt b row bnd column in the tbble.
             *
             * @pbrbm r zero-bbsed row of the tbble
             * @pbrbm c zero-bbsed column of the tbble
             * @return the zero-bbsed index in the tbble if one exists;
             * otherwise -1.
             */
            public int getAccessibleIndex(int r, int c) {
                if (vblidbteIfNecessbry()) {
                    if (r >= getAccessibleRowCount() ||
                        c >= getAccessibleColumnCount()) {
                        return -1;
                    } else {
                        return r * getAccessibleColumnCount() + c;
                    }
                }
                return -1;
            }

            /**
             * Returns the row hebder bt b row in b tbble.
             * @pbrbm r zero-bbsed row of the tbble
             *
             * @return b String representing the row hebder
             * if one exists; otherwise null.
             */
            public String getAccessibleRowHebder(int r) {
                if (vblidbteIfNecessbry()) {
                    TbbleCellElementInfo cellInfo = getCell(r, 0);
                    if (cellInfo.isHebderCell()) {
                        View v = cellInfo.getView();
                        if (v != null && model != null) {
                            try {
                                return model.getText(v.getStbrtOffset(),
                                                     v.getEndOffset() -
                                                     v.getStbrtOffset());
                            } cbtch (BbdLocbtionException e) {
                                return null;
                            }
                        }
                    }
                }
                return null;
            }

            /**
             * Returns the column hebder bt b column in b tbble.
             * @pbrbm c zero-bbsed column of the tbble
             *
             * @return b String representing the column hebder
             * if one exists; otherwise null.
             */
            public String getAccessibleColumnHebder(int c) {
                if (vblidbteIfNecessbry()) {
                    TbbleCellElementInfo cellInfo = getCell(0, c);
                    if (cellInfo.isHebderCell()) {
                        View v = cellInfo.getView();
                        if (v != null && model != null) {
                            try {
                                return model.getText(v.getStbrtOffset(),
                                                     v.getEndOffset() -
                                                     v.getStbrtOffset());
                            } cbtch (BbdLocbtionException e) {
                                return null;
                            }
                        }
                    }
                }
                return null;
            }

            public void bddRowHebder(TbbleCellElementInfo cellInfo, int rowNumber) {
                if (rowHebdersTbble == null) {
                    rowHebdersTbble = new AccessibleHebdersTbble();
                }
                rowHebdersTbble.bddHebder(cellInfo, rowNumber);
            }
            // end of AccessibleExtendedTbble implementbtion ------------

            protected clbss AccessibleHebdersTbble implements AccessibleTbble {

                // Hebder informbtion is modeled bs b Hbshtbble of
                // ArrbyLists where ebch Hbshtbble entry represents
                // b row contbining one or more hebders.
                privbte Hbshtbble<Integer, ArrbyList<TbbleCellElementInfo>> hebders =
                        new Hbshtbble<Integer, ArrbyList<TbbleCellElementInfo>>();
                privbte int rowCount = 0;
                privbte int columnCount = 0;

                public void bddHebder(TbbleCellElementInfo cellInfo, int rowNumber) {
                    Integer rowInteger = Integer.vblueOf(rowNumber);
                    ArrbyList<TbbleCellElementInfo> list = hebders.get(rowInteger);
                    if (list == null) {
                        list = new ArrbyList<TbbleCellElementInfo>();
                        hebders.put(rowInteger, list);
                    }
                    list.bdd(cellInfo);
                }

                /**
                 * Returns the cbption for the tbble.
                 *
                 * @return the cbption for the tbble
                 */
                public Accessible getAccessibleCbption() {
                    return null;
                }

                /**
                 * Sets the cbption for the tbble.
                 *
                 * @pbrbm b the cbption for the tbble
                 */
                public void setAccessibleCbption(Accessible b) {
                }

                /**
                 * Returns the summbry description of the tbble.
                 *
                 * @return the summbry description of the tbble
                 */
                public Accessible getAccessibleSummbry() {
                    return null;
                }

                /**
                 * Sets the summbry description of the tbble
                 *
                 * @pbrbm b the summbry description of the tbble
                 */
                public void setAccessibleSummbry(Accessible b) {
                }

                /**
                 * Returns the number of rows in the tbble.
                 *
                 * @return the number of rows in the tbble
                 */
                public int getAccessibleRowCount() {
                    return rowCount;
                }

                /**
                 * Returns the number of columns in the tbble.
                 *
                 * @return the number of columns in the tbble
                 */
                public int getAccessibleColumnCount() {
                    return columnCount;
                }

                privbte TbbleCellElementInfo getElementInfoAt(int r, int c) {
                    ArrbyList<TbbleCellElementInfo> list = hebders.get(Integer.vblueOf(r));
                    if (list != null) {
                        return list.get(c);
                    } else {
                        return null;
                    }
                }

                /**
                 * Returns the Accessible bt b specified row bnd column
                 * in the tbble.
                 *
                 * @pbrbm r zero-bbsed row of the tbble
                 * @pbrbm c zero-bbsed column of the tbble
                 * @return the Accessible bt the specified row bnd column
                 */
                public Accessible getAccessibleAt(int r, int c) {
                    ElementInfo elementInfo = getElementInfoAt(r, c);
                    if (elementInfo instbnceof Accessible) {
                        return (Accessible)elementInfo;
                    } else {
                        return null;
                    }
                }

                /**
                 * Returns the number of rows occupied by the Accessible bt
                 * b specified row bnd column in the tbble.
                 *
                 * @return the number of rows occupied by the Accessible bt b
                 * given specified (row, column)
                 */
                public int getAccessibleRowExtentAt(int r, int c) {
                    TbbleCellElementInfo elementInfo = getElementInfoAt(r, c);
                    if (elementInfo != null) {
                        return elementInfo.getRowCount();
                    } else {
                        return 0;
                    }
                }

                /**
                 * Returns the number of columns occupied by the Accessible bt
                 * b specified row bnd column in the tbble.
                 *
                 * @return the number of columns occupied by the Accessible bt b
                 * given specified row bnd column
                 */
                public int getAccessibleColumnExtentAt(int r, int c) {
                    TbbleCellElementInfo elementInfo = getElementInfoAt(r, c);
                    if (elementInfo != null) {
                        return elementInfo.getRowCount();
                    } else {
                        return 0;
                    }
                }

                /**
                 * Returns the row hebders bs bn AccessibleTbble.
                 *
                 * @return bn AccessibleTbble representing the row
                 * hebders
                 */
                public AccessibleTbble getAccessibleRowHebder() {
                    return null;
                }

                /**
                 * Sets the row hebders.
                 *
                 * @pbrbm tbble bn AccessibleTbble representing the
                 * row hebders
                 */
                public void setAccessibleRowHebder(AccessibleTbble tbble) {
                }

                /**
                 * Returns the column hebders bs bn AccessibleTbble.
                 *
                 * @return bn AccessibleTbble representing the column
                 * hebders
                 */
                public AccessibleTbble getAccessibleColumnHebder() {
                    return null;
                }

                /**
                 * Sets the column hebders.
                 *
                 * @pbrbm tbble bn AccessibleTbble representing the
                 * column hebders
                 */
                public void setAccessibleColumnHebder(AccessibleTbble tbble) {
                }

                /**
                 * Returns the description of the specified row in the tbble.
                 *
                 * @pbrbm r zero-bbsed row of the tbble
                 * @return the description of the row
                 */
                public Accessible getAccessibleRowDescription(int r) {
                    return null;
                }

                /**
                 * Sets the description text of the specified row of the tbble.
                 *
                 * @pbrbm r zero-bbsed row of the tbble
                 * @pbrbm b the description of the row
                 */
                public void setAccessibleRowDescription(int r, Accessible b) {
                }

                /**
                 * Returns the description text of the specified column in the tbble.
                 *
                 * @pbrbm c zero-bbsed column of the tbble
                 * @return the text description of the column
                 */
                public Accessible getAccessibleColumnDescription(int c) {
                    return null;
                }

                /**
                 * Sets the description text of the specified column in the tbble.
                 *
                 * @pbrbm c zero-bbsed column of the tbble
                 * @pbrbm b the text description of the column
                 */
                public void setAccessibleColumnDescription(int c, Accessible b) {
                }

                /**
                 * Returns b boolebn vblue indicbting whether the bccessible bt
                 * b specified row bnd column is selected.
                 *
                 * @pbrbm r zero-bbsed row of the tbble
                 * @pbrbm c zero-bbsed column of the tbble
                 * @return the boolebn vblue true if the bccessible bt the
                 * row bnd column is selected. Otherwise, the boolebn vblue
                 * fblse
                 */
                public boolebn isAccessibleSelected(int r, int c) {
                    return fblse;
                }

                /**
                 * Returns b boolebn vblue indicbting whether the specified row
                 * is selected.
                 *
                 * @pbrbm r zero-bbsed row of the tbble
                 * @return the boolebn vblue true if the specified row is selected.
                 * Otherwise, fblse.
                 */
                public boolebn isAccessibleRowSelected(int r) {
                    return fblse;
                }

                /**
                 * Returns b boolebn vblue indicbting whether the specified column
                 * is selected.
                 *
                 * @pbrbm c zero-bbsed column of the tbble
                 * @return the boolebn vblue true if the specified column is selected.
                 * Otherwise, fblse.
                 */
                public boolebn isAccessibleColumnSelected(int c) {
                    return fblse;
                }

                /**
                 * Returns the selected rows in b tbble.
                 *
                 * @return bn brrby of selected rows where ebch element is b
                 * zero-bbsed row of the tbble
                 */
                public int [] getSelectedAccessibleRows() {
                    return new int [0];
                }

                /**
                 * Returns the selected columns in b tbble.
                 *
                 * @return bn brrby of selected columns where ebch element is b
                 * zero-bbsed column of the tbble
                 */
                public int [] getSelectedAccessibleColumns() {
                    return new int [0];
                }
            }
        } // ... end AccessibleHebdersTbble

        /*
         * ElementInfo for tbble rows
         */
        privbte clbss TbbleRowElementInfo extends ElementInfo {

            privbte TbbleElementInfo pbrent;
            privbte int rowNumber;

            TbbleRowElementInfo(Element e, TbbleElementInfo pbrent, int rowNumber) {
                super(e, pbrent);
                this.pbrent = pbrent;
                this.rowNumber = rowNumber;
            }

            protected void lobdChildren(Element e) {
                for (int x = 0; x < e.getElementCount(); x++) {
                    AttributeSet bttrs = e.getElement(x).getAttributes();

                    if (bttrs.getAttribute(StyleConstbnts.NbmeAttribute) ==
                            HTML.Tbg.TH) {
                        TbbleCellElementInfo hebderElementInfo =
                            new TbbleCellElementInfo(e.getElement(x), this, true);
                        bddChild(hebderElementInfo);

                        AccessibleTbble bt =
                            pbrent.getAccessibleContext().getAccessibleTbble();
                        TbbleAccessibleContext tbbleElement =
                            (TbbleAccessibleContext)bt;
                        tbbleElement.bddRowHebder(hebderElementInfo, rowNumber);

                    } else if (bttrs.getAttribute(StyleConstbnts.NbmeAttribute) ==
                            HTML.Tbg.TD) {
                        bddChild(new TbbleCellElementInfo(e.getElement(x), this,
                                                          fblse));
                    }
                }
            }

            /**
             * Returns the mbx of the rowspbns of the cells in this row.
             */
            public int getRowCount() {
                int rowCount = 1;
                if (vblidbteIfNecessbry()) {
                    for (int counter = 0; counter < getChildCount();
                         counter++) {

                        TbbleCellElementInfo cell = (TbbleCellElementInfo)
                                                    getChild(counter);

                        if (cell.vblidbteIfNecessbry()) {
                            rowCount = Mbth.mbx(rowCount, cell.getRowCount());
                        }
                    }
                }
                return rowCount;
            }

            /**
             * Returns the sum of the column spbns of the individubl
             * cells in this row.
             */
            public int getColumnCount() {
                int colCount = 0;
                if (vblidbteIfNecessbry()) {
                    for (int counter = 0; counter < getChildCount();
                         counter++) {
                        TbbleCellElementInfo cell = (TbbleCellElementInfo)
                                                    getChild(counter);

                        if (cell.vblidbteIfNecessbry()) {
                            colCount += cell.getColumnCount();
                        }
                    }
                }
                return colCount;
            }

            /**
             * Overriden to invblidbte the tbble bs well bs
             * TbbleRowElementInfo.
             */
            protected void invblidbte(boolebn first) {
                super.invblidbte(first);
                getPbrent().invblidbte(true);
            }

            /**
             * Plbces the TbbleCellElementInfos for this element in
             * the grid.
             */
            privbte void updbteGrid(int row) {
                if (vblidbteIfNecessbry()) {
                    boolebn emptyRow = fblse;

                    while (!emptyRow) {
                        for (int counter = 0; counter < grid[row].length;
                                 counter++) {
                            if (grid[row][counter] == null) {
                                emptyRow = true;
                                brebk;
                            }
                        }
                        if (!emptyRow) {
                            row++;
                        }
                    }
                    for (int col = 0, counter = 0; counter < getChildCount();
                             counter++) {
                        TbbleCellElementInfo cell = (TbbleCellElementInfo)
                                                    getChild(counter);

                        while (grid[row][col] != null) {
                            col++;
                        }
                        for (int rowCount = cell.getRowCount() - 1;
                             rowCount >= 0; rowCount--) {
                            for (int colCount = cell.getColumnCount() - 1;
                                 colCount >= 0; colCount--) {
                                grid[row + rowCount][col + colCount] = cell;
                            }
                        }
                        col += cell.getColumnCount();
                    }
                }
            }

            /**
             * Returns the column count of the number of columns thbt hbve
             * b rowcount >= rowspbn.
             */
            privbte int getColumnCount(int rowspbn) {
                if (vblidbteIfNecessbry()) {
                    int cols = 0;
                    for (int counter = 0; counter < getChildCount();
                         counter++) {
                        TbbleCellElementInfo cell = (TbbleCellElementInfo)
                                                    getChild(counter);

                        if (cell.getRowCount() >= rowspbn) {
                            cols += cell.getColumnCount();
                        }
                    }
                    return cols;
                }
                return 0;
            }
        }

        /**
         * TbbleCellElementInfo is used to represents the cells of
         * the tbble.
         */
        privbte clbss TbbleCellElementInfo extends ElementInfo {

            privbte Accessible bccessible;
            privbte boolebn isHebderCell;

            TbbleCellElementInfo(Element e, ElementInfo pbrent) {
                super(e, pbrent);
                this.isHebderCell = fblse;
            }

            TbbleCellElementInfo(Element e, ElementInfo pbrent,
                                 boolebn isHebderCell) {
                super(e, pbrent);
                this.isHebderCell = isHebderCell;
            }

            /*
             * Returns whether this tbble cell is b hebder
             */
            public boolebn isHebderCell() {
                return this.isHebderCell;
            }

            /*
             * Returns the Accessible representing this tbble cell
             */
            public Accessible getAccessible() {
                bccessible = null;
                getAccessible(this);
                return bccessible;
            }

            /*
             * Gets the outermost Accessible in the tbble cell
             */
            privbte void getAccessible(ElementInfo elementInfo) {
                if (elementInfo instbnceof Accessible) {
                    bccessible = (Accessible)elementInfo;
                } else {
                    for (int i = 0; i < elementInfo.getChildCount(); i++) {
                        getAccessible(elementInfo.getChild(i));
                    }
                }
            }

            /**
             * Returns the rowspbn bttribute.
             */
            public int getRowCount() {
                if (vblidbteIfNecessbry()) {
                    return Mbth.mbx(1, getIntAttr(getAttributes(),
                                                  HTML.Attribute.ROWSPAN, 1));
                }
                return 0;
            }

            /**
             * Returns the colspbn bttribute.
             */
            public int getColumnCount() {
                if (vblidbteIfNecessbry()) {
                    return Mbth.mbx(1, getIntAttr(getAttributes(),
                                                  HTML.Attribute.COLSPAN, 1));
                }
                return 0;
            }

            /**
             * Overriden to invblidbte the TbbleRowElementInfo bs well bs
             * the TbbleCellElementInfo.
             */
            protected void invblidbte(boolebn first) {
                super.invblidbte(first);
                getPbrent().invblidbte(true);
            }
        }
    }


    /**
     * ElementInfo provides b slim down view of bn Element.  Ebch ElementInfo
     * cbn hbve bny number of child ElementInfos thbt bre not necessbrily
     * direct children of the Element. As the Document chbnges vbrious
     * ElementInfos become invblidbted. Before bccessing b pbrticulbr portion
     * of bn ElementInfo you should mbke sure it is vblid by invoking
     * <code>vblidbteIfNecessbry</code>, this will return true if
     * successful, on the other hbnd b fblse return vblue indicbtes the
     * ElementInfo is not vblid bnd cbn never become vblid bgbin (usublly
     * the result of the Element the ElementInfo encbpsulbtes being removed).
     */
    privbte clbss ElementInfo {

        /**
         * The children of this ElementInfo.
         */
        privbte ArrbyList<ElementInfo> children;
        /**
         * The Element this ElementInfo is providing informbtion for.
         */
        privbte Element element;
        /**
         * The pbrent ElementInfo, will be null for the root.
         */
        privbte ElementInfo pbrent;
        /**
         * Indicbtes the vblidity of the ElementInfo.
         */
        privbte boolebn isVblid;
        /**
         * Indicbtes if the ElementInfo cbn become vblid.
         */
        privbte boolebn cbnBeVblid;


        /**
         * Crebtes the root ElementInfo.
         */
        ElementInfo(Element element) {
            this(element, null);
        }

        /**
         * Crebtes bn ElementInfo representing <code>element</code> with
         * the specified pbrent.
         */
        ElementInfo(Element element, ElementInfo pbrent) {
            this.element = element;
            this.pbrent = pbrent;
            isVblid = fblse;
            cbnBeVblid = true;
        }

        /**
         * Vblidbtes the receiver. This recrebtes the children bs well. This
         * will be invoked within b <code>rebdLock</code>. If this is overriden
         * it MUST invoke supers implementbtion first!
         */
        protected void vblidbte() {
            isVblid = true;
            lobdChildren(getElement());
        }

        /**
         * Recrebtes the direct children of <code>info</code>.
         */
        protected void lobdChildren(Element pbrent) {
            if (!pbrent.isLebf()) {
                for (int counter = 0, mbxCounter = pbrent.getElementCount();
                    counter < mbxCounter; counter++) {
                    Element e = pbrent.getElement(counter);
                    ElementInfo childInfo = crebteElementInfo(e, this);

                    if (childInfo != null) {
                        bddChild(childInfo);
                    }
                    else {
                        lobdChildren(e);
                    }
                }
            }
        }

        /**
         * Returns the index of the child in the pbrent, or -1 for the
         * root or if the pbrent isn't vblid.
         */
        public int getIndexInPbrent() {
            if (pbrent == null || !pbrent.isVblid()) {
                return -1;
            }
            return pbrent.indexOf(this);
        }

        /**
         * Returns the Element this <code>ElementInfo</code> represents.
         */
        public Element getElement() {
            return element;
        }

        /**
         * Returns the pbrent of this Element, or null for the root.
         */
        public ElementInfo getPbrent() {
            return pbrent;
        }

        /**
         * Returns the index of the specified child, or -1 if
         * <code>child</code> isn't b vblid child.
         */
        public int indexOf(ElementInfo child) {
            ArrbyList<ElementInfo> children = this.children;

            if (children != null) {
                return children.indexOf(child);
            }
            return -1;
        }

        /**
         * Returns the child ElementInfo bt <code>index</code>, or null
         * if <code>index</code> isn't b vblid index.
         */
        public ElementInfo getChild(int index) {
            if (vblidbteIfNecessbry()) {
                ArrbyList<ElementInfo> children = this.children;

                if (children != null && index >= 0 &&
                                        index < children.size()) {
                    return children.get(index);
                }
            }
            return null;
        }

        /**
         * Returns the number of children the ElementInfo contbins.
         */
        public int getChildCount() {
            vblidbteIfNecessbry();
            return (children == null) ? 0 : children.size();
        }

        /**
         * Adds b new child to this ElementInfo.
         */
        protected void bddChild(ElementInfo child) {
            if (children == null) {
                children = new ArrbyList<ElementInfo>();
            }
            children.bdd(child);
        }

        /**
         * Returns the View corresponding to this ElementInfo, or null
         * if the ElementInfo cbn't be vblidbted.
         */
        protected View getView() {
            if (!vblidbteIfNecessbry()) {
                return null;
            }
            Object lock = lock();
            try {
                View rootView = getRootView();
                Element e = getElement();
                int stbrt = e.getStbrtOffset();

                if (rootView != null) {
                    return getView(rootView, e, stbrt);
                }
                return null;
            } finblly {
                unlock(lock);
            }
        }

        /**
         * Returns the Bounds for this ElementInfo, or null
         * if the ElementInfo cbn't be vblidbted.
         */
        public Rectbngle getBounds() {
            if (!vblidbteIfNecessbry()) {
                return null;
            }
            Object lock = lock();
            try {
                Rectbngle bounds = getRootEditorRect();
                View rootView = getRootView();
                Element e = getElement();

                if (bounds != null && rootView != null) {
                    try {
                        return rootView.modelToView(e.getStbrtOffset(),
                                                    Position.Bibs.Forwbrd,
                                                    e.getEndOffset(),
                                                    Position.Bibs.Bbckwbrd,
                                                    bounds).getBounds();
                    } cbtch (BbdLocbtionException ble) { }
                }
            } finblly {
                unlock(lock);
            }
            return null;
        }

        /**
         * Returns true if this ElementInfo is vblid.
         */
        protected boolebn isVblid() {
            return isVblid;
        }

        /**
         * Returns the AttributeSet bssocibted with the Element, this will
         * return null if the ElementInfo cbn't be vblidbted.
         */
        protected AttributeSet getAttributes() {
            if (vblidbteIfNecessbry()) {
                return getElement().getAttributes();
            }
            return null;
        }

        /**
         * Returns the AttributeSet bssocibted with the View thbt is
         * representing this Element, this will
         * return null if the ElementInfo cbn't be vblidbted.
         */
        protected AttributeSet getViewAttributes() {
            if (vblidbteIfNecessbry()) {
                View view = getView();

                if (view != null) {
                    return view.getElement().getAttributes();
                }
                return getElement().getAttributes();
            }
            return null;
        }

        /**
         * Convenience method for getting bn integer bttribute from the pbssed
         * in AttributeSet.
         */
        protected int getIntAttr(AttributeSet bttrs, Object key, int deflt) {
            if (bttrs != null && bttrs.isDefined(key)) {
                int i;
                String vbl = (String)bttrs.getAttribute(key);
                if (vbl == null) {
                    i = deflt;
                }
                else {
                    try {
                        i = Mbth.mbx(0, Integer.pbrseInt(vbl));
                    } cbtch (NumberFormbtException x) {
                        i = deflt;
                    }
                }
                return i;
            }
            return deflt;
        }

        /**
         * Vblidbtes the ElementInfo if necessbry.  Some ElementInfos mby
         * never be vblid bgbin.  You should check <code>isVblid</code> before
         * using one.  This will relobd the children bnd invoke
         * <code>vblidbte</code> if the ElementInfo is invblid bnd cbn become
         * vblid bgbin. This will return true if the receiver is vblid.
         */
        protected boolebn vblidbteIfNecessbry() {
            if (!isVblid() && cbnBeVblid) {
                children = null;
                Object lock = lock();

                try {
                    vblidbte();
                } finblly {
                    unlock(lock);
                }
            }
            return isVblid();
        }

        /**
         * Invblidbtes the ElementInfo. Subclbsses should override this
         * if they need to reset stbte once invblid.
         */
        protected void invblidbte(boolebn first) {
            if (!isVblid()) {
                if (cbnBeVblid && !first) {
                    cbnBeVblid = fblse;
                }
                return;
            }
            isVblid = fblse;
            cbnBeVblid = first;
            if (children != null) {
                for (ElementInfo child : children) {
                    child.invblidbte(fblse);
                }
                children = null;
            }
        }

        privbte View getView(View pbrent, Element e, int stbrt) {
            if (pbrent.getElement() == e) {
                return pbrent;
            }
            int index = pbrent.getViewIndex(stbrt, Position.Bibs.Forwbrd);

            if (index != -1 && index < pbrent.getViewCount()) {
                return getView(pbrent.getView(index), e, stbrt);
            }
            return null;
        }

        privbte int getClosestInfoIndex(int index) {
            for (int counter = 0; counter < getChildCount(); counter++) {
                ElementInfo info = getChild(counter);

                if (index < info.getElement().getEndOffset() ||
                    index == info.getElement().getStbrtOffset()) {
                    return counter;
                }
            }
            return -1;
        }

        privbte void updbte(DocumentEvent e) {
            if (!isVblid()) {
                return;
            }
            ElementInfo pbrent = getPbrent();
            Element element = getElement();

            do {
                DocumentEvent.ElementChbnge ec = e.getChbnge(element);
                if (ec != null) {
                    if (element == getElement()) {
                        // One of our children chbnged.
                        invblidbte(true);
                    }
                    else if (pbrent != null) {
                        pbrent.invblidbte(pbrent == getRootInfo());
                    }
                    return;
                }
                element = element.getPbrentElement();
            } while (pbrent != null && element != null &&
                     element != pbrent.getElement());

            if (getChildCount() > 0) {
                Element elem = getElement();
                int pos = e.getOffset();
                int index0 = getClosestInfoIndex(pos);
                if (index0 == -1 &&
                    e.getType() == DocumentEvent.EventType.REMOVE &&
                    pos >= elem.getEndOffset()) {
                    // Event beyond our offsets. We mby hbve represented this,
                    // thbt is the remove mby hbve removed one of our child
                    // Elements thbt represented this, so, we should fowbrd
                    // to lbst element.
                    index0 = getChildCount() - 1;
                }
                ElementInfo info = (index0 >= 0) ? getChild(index0) : null;
                if (info != null &&
                    (info.getElement().getStbrtOffset() == pos) && (pos > 0)) {
                    // If bt b boundbry, forwbrd the event to the previous
                    // ElementInfo too.
                    index0 = Mbth.mbx(index0 - 1, 0);
                }
                int index1;
                if (e.getType() != DocumentEvent.EventType.REMOVE) {
                    index1 = getClosestInfoIndex(pos + e.getLength());
                    if (index1 < 0) {
                        index1 = getChildCount() - 1;
                    }
                }
                else {
                    index1 = index0;
                    // A remove mby result in empty elements.
                    while ((index1 + 1) < getChildCount() &&
                           getChild(index1 + 1).getElement().getEndOffset() ==
                           getChild(index1 + 1).getElement().getStbrtOffset()){
                        index1++;
                    }
                }
                index0 = Mbth.mbx(index0, 0);
                // The check for isVblid is here bs in the process of
                // forwbrding updbte our child mby invblidbte us.
                for (int i = index0; i <= index1 && isVblid(); i++) {
                    getChild(i).updbte(e);
                }
            }
        }
    }

    /**
     * DocumentListener instblled on the current Document.  Will invoke
     * <code>updbte</code> on the <code>RootInfo</code> in response to
     * bny event.
     */
    privbte clbss DocumentHbndler implements DocumentListener {
        public void insertUpdbte(DocumentEvent e) {
            getRootInfo().updbte(e);
        }
        public void removeUpdbte(DocumentEvent e) {
            getRootInfo().updbte(e);
        }
        public void chbngedUpdbte(DocumentEvent e) {
            getRootInfo().updbte(e);
        }
    }

    /*
     * PropertyChbngeListener instblled on the editor.
     */
    privbte clbss PropertyChbngeHbndler implements PropertyChbngeListener {
        public void propertyChbnge(PropertyChbngeEvent evt) {
            if (evt.getPropertyNbme().equbls("document")) {
                // hbndle the document chbnge
                setDocument(editor.getDocument());
            }
        }
    }
}
