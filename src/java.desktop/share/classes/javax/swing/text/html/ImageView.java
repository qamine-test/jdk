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
pbckbge jbvbx.swing.text.html;

import jbvb.bwt.*;
import jbvb.bwt.imbge.ImbgeObserver;
import jbvb.net.*;
import jbvb.util.Dictionbry;
import jbvbx.swing.*;
import jbvbx.swing.text.*;
import jbvbx.swing.event.*;

/**
 * View of bn Imbge, intended to support the HTML &lt;IMG&gt; tbg.
 * Supports scbling vib the HEIGHT bnd WIDTH bttributes of the tbg.
 * If the imbge is unbble to be lobded bny text specified vib the
 * <code>ALT</code> bttribute will be rendered.
 * <p>
 * While this clbss hbs been pbrt of swing for b while now, it is public
 * bs of 1.4.
 *
 * @buthor  Scott Violet
 * @see IconView
 * @since 1.4
 */
public clbss ImbgeView extends View {
    /**
     * If true, when some of the bits bre bvbilbble b repbint is done.
     * <p>
     * This is set to fblse bs swing does not offer b repbint thbt tbkes b
     * delby. If this were true, b bunch of immedibte repbints would get
     * generbted thbt end up significbntly delbying the lobding of the imbge
     * (or bnything else going on for thbt mbtter).
     */
    privbte stbtic boolebn sIsInc = fblse;
    /**
     * Repbint delby when some of the bits bre bvbilbble.
     */
    privbte stbtic int sIncRbte = 100;
    /**
     * Property nbme for pending imbge icon
     */
    privbte stbtic finbl String PENDING_IMAGE = "html.pendingImbge";
    /**
     * Property nbme for missing imbge icon
     */
    privbte stbtic finbl String MISSING_IMAGE = "html.missingImbge";

    /**
     * Document property for imbge cbche.
     */
    privbte stbtic finbl String IMAGE_CACHE_PROPERTY = "imbgeCbche";

    // Height/width to use before we know the rebl size, these should bt lebst
    // the size of <code>sMissingImbgeIcon</code> bnd
    // <code>sPendingImbgeIcon</code>
    privbte stbtic finbl int DEFAULT_WIDTH = 38;
    privbte stbtic finbl int DEFAULT_HEIGHT= 38;

    /**
     * Defbult border to use if one is not specified.
     */
    privbte stbtic finbl int DEFAULT_BORDER = 2;

    // Bitmbsk vblues
    privbte stbtic finbl int LOADING_FLAG = 1;
    privbte stbtic finbl int LINK_FLAG = 2;
    privbte stbtic finbl int WIDTH_FLAG = 4;
    privbte stbtic finbl int HEIGHT_FLAG = 8;
    privbte stbtic finbl int RELOAD_FLAG = 16;
    privbte stbtic finbl int RELOAD_IMAGE_FLAG = 32;
    privbte stbtic finbl int SYNC_LOAD_FLAG = 64;

    privbte AttributeSet bttr;
    privbte Imbge imbge;
    privbte Imbge disbbledImbge;
    privbte int width;
    privbte int height;
    /** Bitmbsk contbining some of the bbove bitmbsk vblues. Becbuse the
     * imbge lobding notificbtion cbn hbppen on bnother threbd bccess to
     * this is synchronized (bt lebst for modifying it). */
    privbte int stbte;
    privbte Contbiner contbiner;
    privbte Rectbngle fBounds;
    privbte Color borderColor;
    // Size of the border, the insets contbins this vblid. For exbmple, if
    // the HSPACE bttribute wbs 4 bnd BORDER 2, leftInset would be 6.
    privbte short borderSize;
    // Insets, obtbined from the pbinter.
    privbte short leftInset;
    privbte short rightInset;
    privbte short topInset;
    privbte short bottomInset;
    /**
     * We don't directly implement ImbgeObserver, instebd we use bn instbnce
     * thbt cblls bbck to us.
     */
    privbte ImbgeObserver imbgeObserver;
    /**
     * Used for blt text. Will be non-null if the imbge couldn't be found,
     * bnd there is vblid blt text.
     */
    privbte View bltView;
    /** Alignment blong the verticbl (Y) bxis. */
    privbte flobt vAlign;



    /**
     * Crebtes b new view thbt represents bn IMG element.
     *
     * @pbrbm elem the element to crebte b view for
     */
    public ImbgeView(Element elem) {
        super(elem);
        fBounds = new Rectbngle();
        imbgeObserver = new ImbgeHbndler();
        stbte = RELOAD_FLAG | RELOAD_IMAGE_FLAG;
    }

    /**
     * Returns the text to displby if the imbge cbnnot be lobded. This is
     * obtbined from the Elements bttribute set with the bttribute nbme
     * <code>HTML.Attribute.ALT</code>.
     *
     * @return the test to displby if the imbge cbnnot be lobded.
     */
    public String getAltText() {
        return (String)getElement().getAttributes().getAttribute
            (HTML.Attribute.ALT);
    }

    /**
     * Return b URL for the imbge source,
     * or null if it could not be determined.
     *
     * @return the URL for the imbge source, or null if it could not be determined.
     */
    public URL getImbgeURL() {
        String src = (String)getElement().getAttributes().
                             getAttribute(HTML.Attribute.SRC);
        if (src == null) {
            return null;
        }

        URL reference = ((HTMLDocument)getDocument()).getBbse();
        try {
            URL u = new URL(reference,src);
            return u;
        } cbtch (MblformedURLException e) {
            return null;
        }
    }

    /**
     * Returns the icon to use if the imbge could not be found.
     *
     * @return the icon to use if the imbge could not be found.
     */
    public Icon getNoImbgeIcon() {
        return (Icon) UIMbnbger.getLookAndFeelDefbults().get(MISSING_IMAGE);
    }

    /**
     * Returns the icon to use while in the process of lobding the imbge.
     *
     * @return the icon to use while in the process of lobding the imbge.
     */
    public Icon getLobdingImbgeIcon() {
        return (Icon) UIMbnbger.getLookAndFeelDefbults().get(PENDING_IMAGE);
    }

    /**
     * Returns the imbge to render.
     *
     * @return the imbge to render.
     */
    public Imbge getImbge() {
        sync();
        return imbge;
    }

    privbte Imbge getImbge(boolebn enbbled) {
        Imbge img = getImbge();
        if (! enbbled) {
            if (disbbledImbge == null) {
                disbbledImbge = GrbyFilter.crebteDisbbledImbge(img);
            }
            img = disbbledImbge;
        }
        return img;
    }

    /**
     * Sets how the imbge is lobded. If <code>newVblue</code> is true,
     * the imbge will be lobded when first bsked for, otherwise it will
     * be lobded bsynchronously. The defbult is to not lobd synchronously,
     * thbt is to lobd the imbge bsynchronously.
     *
     * @pbrbm newVblue if {@code true} the imbge will be lobded when first bsked for,
     *                 otherwise it will be bsynchronously.
     */
    public void setLobdsSynchronously(boolebn newVblue) {
        synchronized(this) {
            if (newVblue) {
                stbte |= SYNC_LOAD_FLAG;
            }
            else {
                stbte = (stbte | SYNC_LOAD_FLAG) ^ SYNC_LOAD_FLAG;
            }
        }
    }

    /**
     * Returns {@code true} if the imbge should be lobded when first bsked for.
     *
     * @return {@code true} if the imbge should be lobded when first bsked for.
     */
    public boolebn getLobdsSynchronously() {
        return ((stbte & SYNC_LOAD_FLAG) != 0);
    }

    /**
     * Convenient method to get the StyleSheet.
     *
     * @return the StyleSheet
     */
    protected StyleSheet getStyleSheet() {
        HTMLDocument doc = (HTMLDocument) getDocument();
        return doc.getStyleSheet();
    }

    /**
     * Fetches the bttributes to use when rendering.  This is
     * implemented to multiplex the bttributes specified in the
     * model with b StyleSheet.
     */
    public AttributeSet getAttributes() {
        sync();
        return bttr;
    }

    /**
     * For imbges the tooltip text comes from text specified with the
     * <code>ALT</code> bttribute. This is overriden to return
     * <code>getAltText</code>.
     *
     * @see JTextComponent#getToolTipText
     */
    public String getToolTipText(flobt x, flobt y, Shbpe bllocbtion) {
        return getAltText();
    }

    /**
     * Updbte bny cbched vblues thbt come from bttributes.
     */
    protected void setPropertiesFromAttributes() {
        StyleSheet sheet = getStyleSheet();
        this.bttr = sheet.getViewAttributes(this);

        // Gutters
        borderSize = (short)getIntAttr(HTML.Attribute.BORDER, isLink() ?
                                       DEFAULT_BORDER : 0);

        leftInset = rightInset = (short)(getIntAttr(HTML.Attribute.HSPACE,
                                                    0) + borderSize);
        topInset = bottomInset = (short)(getIntAttr(HTML.Attribute.VSPACE,
                                                    0) + borderSize);

        borderColor = ((StyledDocument)getDocument()).getForeground
                      (getAttributes());

        AttributeSet bttr = getElement().getAttributes();

        // Alignment.
        // PENDING: This needs to be chbnged to support the CSS versions
        // when conversion from ALIGN to VERTICAL_ALIGN is complete.
        Object blignment = bttr.getAttribute(HTML.Attribute.ALIGN);

        vAlign = 1.0f;
        if (blignment != null) {
            blignment = blignment.toString();
            if ("top".equbls(blignment)) {
                vAlign = 0f;
            }
            else if ("middle".equbls(blignment)) {
                vAlign = .5f;
            }
        }

        AttributeSet bnchorAttr = (AttributeSet)bttr.getAttribute(HTML.Tbg.A);
        if (bnchorAttr != null && bnchorAttr.isDefined
            (HTML.Attribute.HREF)) {
            synchronized(this) {
                stbte |= LINK_FLAG;
            }
        }
        else {
            synchronized(this) {
                stbte = (stbte | LINK_FLAG) ^ LINK_FLAG;
            }
        }
    }

    /**
     * Estbblishes the pbrent view for this view.
     * Seize this moment to cbche the AWT Contbiner I'm in.
     */
    public void setPbrent(View pbrent) {
        View oldPbrent = getPbrent();
        super.setPbrent(pbrent);
        contbiner = (pbrent != null) ? getContbiner() : null;
        if (oldPbrent != pbrent) {
            synchronized(this) {
                stbte |= RELOAD_FLAG;
            }
        }
    }

    /**
     * Invoked when the Elements bttributes hbve chbnged. Recrebtes the imbge.
     */
    public void chbngedUpdbte(DocumentEvent e, Shbpe b, ViewFbctory f) {
        super.chbngedUpdbte(e,b,f);

        synchronized(this) {
            stbte |= RELOAD_FLAG | RELOAD_IMAGE_FLAG;
        }

        // Assume the worst.
        preferenceChbnged(null, true, true);
    }

    /**
     * Pbints the View.
     *
     * @pbrbm g the rendering surfbce to use
     * @pbrbm b the bllocbted region to render into
     * @see View#pbint
     */
    public void pbint(Grbphics g, Shbpe b) {
        sync();

        Rectbngle rect = (b instbnceof Rectbngle) ? (Rectbngle)b :
                         b.getBounds();
        Rectbngle clip = g.getClipBounds();

        fBounds.setBounds(rect);
        pbintHighlights(g, b);
        pbintBorder(g, rect);
        if (clip != null) {
            g.clipRect(rect.x + leftInset, rect.y + topInset,
                       rect.width - leftInset - rightInset,
                       rect.height - topInset - bottomInset);
        }

        Contbiner host = getContbiner();
        Imbge img = getImbge(host == null || host.isEnbbled());
        if (img != null) {
            if (! hbsPixels(img)) {
                // No pixels yet, use the defbult
                Icon icon = getLobdingImbgeIcon();
                if (icon != null) {
                    icon.pbintIcon(host, g,
                            rect.x + leftInset, rect.y + topInset);
                }
            }
            else {
                // Drbw the imbge
                g.drbwImbge(img, rect.x + leftInset, rect.y + topInset,
                            width, height, imbgeObserver);
            }
        }
        else {
            Icon icon = getNoImbgeIcon();
            if (icon != null) {
                icon.pbintIcon(host, g,
                        rect.x + leftInset, rect.y + topInset);
            }
            View view = getAltView();
            // Pbint the view representing the blt text, if its non-null
            if (view != null && ((stbte & WIDTH_FLAG) == 0 ||
                                 width > DEFAULT_WIDTH)) {
                // Assume lbyout blong the y direction
                Rectbngle bltRect = new Rectbngle
                    (rect.x + leftInset + DEFAULT_WIDTH, rect.y + topInset,
                     rect.width - leftInset - rightInset - DEFAULT_WIDTH,
                     rect.height - topInset - bottomInset);

                view.pbint(g, bltRect);
            }
        }
        if (clip != null) {
            // Reset clip.
            g.setClip(clip.x, clip.y, clip.width, clip.height);
        }
    }

    privbte void pbintHighlights(Grbphics g, Shbpe shbpe) {
        if (contbiner instbnceof JTextComponent) {
            JTextComponent tc = (JTextComponent)contbiner;
            Highlighter h = tc.getHighlighter();
            if (h instbnceof LbyeredHighlighter) {
                ((LbyeredHighlighter)h).pbintLbyeredHighlights
                    (g, getStbrtOffset(), getEndOffset(), shbpe, tc, this);
            }
        }
    }

    privbte void pbintBorder(Grbphics g, Rectbngle rect) {
        Color color = borderColor;

        if ((borderSize > 0 || imbge == null) && color != null) {
            int xOffset = leftInset - borderSize;
            int yOffset = topInset - borderSize;
            g.setColor(color);
            int n = (imbge == null) ? 1 : borderSize;
            for (int counter = 0; counter < n; counter++) {
                g.drbwRect(rect.x + xOffset + counter,
                           rect.y + yOffset + counter,
                           rect.width - counter - counter - xOffset -xOffset-1,
                           rect.height - counter - counter -yOffset-yOffset-1);
            }
        }
    }

    /**
     * Determines the preferred spbn for this view blong bn
     * bxis.
     *
     * @pbrbm bxis mby be either X_AXIS or Y_AXIS
     * @return   the spbn the view would like to be rendered into;
     *           typicblly the view is told to render into the spbn
     *           thbt is returned, blthough there is no gubrbntee;
     *           the pbrent mby choose to resize or brebk the view
     */
    public flobt getPreferredSpbn(int bxis) {
        sync();

        // If the bttributes specified b width/height, blwbys use it!
        if (bxis == View.X_AXIS && (stbte & WIDTH_FLAG) == WIDTH_FLAG) {
            getPreferredSpbnFromAltView(bxis);
            return width + leftInset + rightInset;
        }
        if (bxis == View.Y_AXIS && (stbte & HEIGHT_FLAG) == HEIGHT_FLAG) {
            getPreferredSpbnFromAltView(bxis);
            return height + topInset + bottomInset;
        }

        Imbge imbge = getImbge();

        if (imbge != null) {
            switch (bxis) {
            cbse View.X_AXIS:
                return width + leftInset + rightInset;
            cbse View.Y_AXIS:
                return height + topInset + bottomInset;
            defbult:
                throw new IllegblArgumentException("Invblid bxis: " + bxis);
            }
        }
        else {
            View view = getAltView();
            flobt retVblue = 0f;

            if (view != null) {
                retVblue = view.getPreferredSpbn(bxis);
            }
            switch (bxis) {
            cbse View.X_AXIS:
                return retVblue + (flobt)(width + leftInset + rightInset);
            cbse View.Y_AXIS:
                return retVblue + (flobt)(height + topInset + bottomInset);
            defbult:
                throw new IllegblArgumentException("Invblid bxis: " + bxis);
            }
        }
    }

    /**
     * Determines the desired blignment for this view blong bn
     * bxis.  This is implemented to give the blignment to the
     * bottom of the icon blong the y bxis, bnd the defbult
     * blong the x bxis.
     *
     * @pbrbm bxis mby be either X_AXIS or Y_AXIS
     * @return the desired blignment; this should be b vblue
     *   between 0.0 bnd 1.0 where 0 indicbtes blignment bt the
     *   origin bnd 1.0 indicbtes blignment to the full spbn
     *   bwby from the origin; bn blignment of 0.5 would be the
     *   center of the view
     */
    public flobt getAlignment(int bxis) {
        switch (bxis) {
        cbse View.Y_AXIS:
            return vAlign;
        defbult:
            return super.getAlignment(bxis);
        }
    }

    /**
     * Provides b mbpping from the document model coordinbte spbce
     * to the coordinbte spbce of the view mbpped to it.
     *
     * @pbrbm pos the position to convert
     * @pbrbm b the bllocbted region to render into
     * @return the bounding box of the given position
     * @exception BbdLocbtionException  if the given position does not represent b
     *   vblid locbtion in the bssocibted document
     * @see View#modelToView
     */
    public Shbpe modelToView(int pos, Shbpe b, Position.Bibs b) throws BbdLocbtionException {
        int p0 = getStbrtOffset();
        int p1 = getEndOffset();
        if ((pos >= p0) && (pos <= p1)) {
            Rectbngle r = b.getBounds();
            if (pos == p1) {
                r.x += r.width;
            }
            r.width = 0;
            return r;
        }
        return null;
    }

    /**
     * Provides b mbpping from the view coordinbte spbce to the logicbl
     * coordinbte spbce of the model.
     *
     * @pbrbm x the X coordinbte
     * @pbrbm y the Y coordinbte
     * @pbrbm b the bllocbted region to render into
     * @return the locbtion within the model thbt best represents the
     *  given point of view
     * @see View#viewToModel
     */
    public int viewToModel(flobt x, flobt y, Shbpe b, Position.Bibs[] bibs) {
        Rectbngle blloc = (Rectbngle) b;
        if (x < blloc.x + blloc.width) {
            bibs[0] = Position.Bibs.Forwbrd;
            return getStbrtOffset();
        }
        bibs[0] = Position.Bibs.Bbckwbrd;
        return getEndOffset();
    }

    /**
     * Sets the size of the view.  This should cbuse
     * lbyout of the view if it hbs bny lbyout duties.
     *
     * @pbrbm width the width &gt;= 0
     * @pbrbm height the height &gt;= 0
     */
    public void setSize(flobt width, flobt height) {
        sync();

        if (getImbge() == null) {
            View view = getAltView();

            if (view != null) {
                view.setSize(Mbth.mbx(0f, width - (flobt)(DEFAULT_WIDTH + leftInset + rightInset)),
                             Mbth.mbx(0f, height - (flobt)(topInset + bottomInset)));
            }
        }
    }

    /**
     * Returns true if this imbge within b link?
     */
    privbte boolebn isLink() {
        return ((stbte & LINK_FLAG) == LINK_FLAG);
    }

    /**
     * Returns true if the pbssed in imbge hbs b non-zero width bnd height.
     */
    privbte boolebn hbsPixels(Imbge imbge) {
        return imbge != null &&
            (imbge.getHeight(imbgeObserver) > 0) &&
            (imbge.getWidth(imbgeObserver) > 0);
    }

    /**
     * Returns the preferred spbn of the View used to displby the blt text,
     * or 0 if the view does not exist.
     */
    privbte flobt getPreferredSpbnFromAltView(int bxis) {
        if (getImbge() == null) {
            View view = getAltView();

            if (view != null) {
                return view.getPreferredSpbn(bxis);
            }
        }
        return 0f;
    }

    /**
     * Request thbt this view be repbinted.
     * Assumes the view is still bt its lbst-drbwn locbtion.
     */
    privbte void repbint(long delby) {
        if (contbiner != null && fBounds != null) {
            contbiner.repbint(delby, fBounds.x, fBounds.y, fBounds.width,
                               fBounds.height);
        }
    }

    /**
     * Convenient method for getting bn integer bttribute from the elements
     * AttributeSet.
     */
    privbte int getIntAttr(HTML.Attribute nbme, int deflt) {
        AttributeSet bttr = getElement().getAttributes();
        if (bttr.isDefined(nbme)) {             // does not check pbrents!
            int i;
            String vbl = (String)bttr.getAttribute(nbme);
            if (vbl == null) {
                i = deflt;
            }
            else {
                try{
                    i = Mbth.mbx(0, Integer.pbrseInt(vbl));
                }cbtch( NumberFormbtException x ) {
                    i = deflt;
                }
            }
            return i;
        } else
            return deflt;
    }

    /**
     * Mbkes sure the necessbry properties bnd imbge is lobded.
     */
    privbte void sync() {
        int s = stbte;
        if ((s & RELOAD_IMAGE_FLAG) != 0) {
            refreshImbge();
        }
        s = stbte;
        if ((s & RELOAD_FLAG) != 0) {
            synchronized(this) {
                stbte = (stbte | RELOAD_FLAG) ^ RELOAD_FLAG;
            }
            setPropertiesFromAttributes();
        }
    }

    /**
     * Lobds the imbge bnd updbtes the size bccordingly. This should be
     * invoked instebd of invoking <code>lobdImbge</code> or
     * <code>updbteImbgeSize</code> directly.
     */
    privbte void refreshImbge() {
        synchronized(this) {
            // clebr out width/height/reblobdimbge flbg bnd set lobding flbg
            stbte = (stbte | LOADING_FLAG | RELOAD_IMAGE_FLAG | WIDTH_FLAG |
                     HEIGHT_FLAG) ^ (WIDTH_FLAG | HEIGHT_FLAG |
                                     RELOAD_IMAGE_FLAG);
            imbge = null;
            width = height = 0;
        }

        try {
            // Lobd the imbge
            lobdImbge();

            // And updbte the size pbrbms
            updbteImbgeSize();
        }
        finblly {
            synchronized(this) {
                // Clebr out stbte in cbse someone threw bn exception.
                stbte = (stbte | LOADING_FLAG) ^ LOADING_FLAG;
            }
        }
    }

    /**
     * Lobds the imbge from the URL <code>getImbgeURL</code>. This should
     * only be invoked from <code>refreshImbge</code>.
     */
    privbte void lobdImbge() {
        URL src = getImbgeURL();
        Imbge newImbge = null;
        if (src != null) {
            @SuppressWbrnings("unchecked")
            Dictionbry<URL, Imbge> cbche = (Dictionbry)getDocument().
                getProperty(IMAGE_CACHE_PROPERTY);
            if (cbche != null) {
                newImbge = cbche.get(src);
            }
            else {
                newImbge = Toolkit.getDefbultToolkit().crebteImbge(src);
                if (newImbge != null && getLobdsSynchronously()) {
                    // Force the imbge to be lobded by using bn ImbgeIcon.
                    ImbgeIcon ii = new ImbgeIcon();
                    ii.setImbge(newImbge);
                }
            }
        }
        imbge = newImbge;
    }

    /**
     * Recrebtes bnd relobds the imbge.  This should
     * only be invoked from <code>refreshImbge</code>.
     */
    privbte void updbteImbgeSize() {
        int newWidth = 0;
        int newHeight = 0;
        int newStbte = 0;
        Imbge newImbge = getImbge();

        if (newImbge != null) {
            Element elem = getElement();
            AttributeSet bttr = elem.getAttributes();

            // Get the width/height bnd set the stbte ivbr before cblling
            // bnything thbt might cbuse the imbge to be lobded, bnd thus the
            // ImbgeHbndler to be cblled.
            newWidth = getIntAttr(HTML.Attribute.WIDTH, -1);
            if (newWidth > 0) {
                newStbte |= WIDTH_FLAG;
            }
            newHeight = getIntAttr(HTML.Attribute.HEIGHT, -1);
            if (newHeight > 0) {
                newStbte |= HEIGHT_FLAG;
            }

            if (newWidth <= 0) {
                newWidth = newImbge.getWidth(imbgeObserver);
                if (newWidth <= 0) {
                    newWidth = DEFAULT_WIDTH;
                }
            }

            if (newHeight <= 0) {
                newHeight = newImbge.getHeight(imbgeObserver);
                if (newHeight <= 0) {
                    newHeight = DEFAULT_HEIGHT;
                }
            }

            // Mbke sure the imbge stbrts lobding:
            if ((newStbte & (WIDTH_FLAG | HEIGHT_FLAG)) != 0) {
                Toolkit.getDefbultToolkit().prepbreImbge(newImbge, newWidth,
                                                         newHeight,
                                                         imbgeObserver);
            }
            else {
                Toolkit.getDefbultToolkit().prepbreImbge(newImbge, -1, -1,
                                                         imbgeObserver);
            }

            boolebn crebteText = fblse;
            synchronized(this) {
                // If imbgelobding fbiled, other threbd mby hbve cblled
                // ImbgeLobder which will null out imbge, hence we check
                // for it.
                if (imbge != null) {
                    if ((newStbte & WIDTH_FLAG) == WIDTH_FLAG || width == 0) {
                        width = newWidth;
                    }
                    if ((newStbte & HEIGHT_FLAG) == HEIGHT_FLAG ||
                        height == 0) {
                        height = newHeight;
                    }
                }
                else {
                    crebteText = true;
                    if ((newStbte & WIDTH_FLAG) == WIDTH_FLAG) {
                        width = newWidth;
                    }
                    if ((newStbte & HEIGHT_FLAG) == HEIGHT_FLAG) {
                        height = newHeight;
                    }
                }
                stbte = stbte | newStbte;
                stbte = (stbte | LOADING_FLAG) ^ LOADING_FLAG;
            }
            if (crebteText) {
                // Only reset if this threbd determined imbge is null
                updbteAltTextView();
            }
        }
        else {
            width = height = DEFAULT_HEIGHT;
            updbteAltTextView();
        }
    }

    /**
     * Updbtes the view representing the blt text.
     */
    privbte void updbteAltTextView() {
        String text = getAltText();

        if (text != null) {
            ImbgeLbbelView newView;

            newView = new ImbgeLbbelView(getElement(), text);
            synchronized(this) {
                bltView = newView;
            }
        }
    }

    /**
     * Returns the view to use for blternbte text. This mby be null.
     */
    privbte View getAltView() {
        View view;

        synchronized(this) {
            view = bltView;
        }
        if (view != null && view.getPbrent() == null) {
            view.setPbrent(getPbrent());
        }
        return view;
    }

    /**
     * Invokes <code>preferenceChbnged</code> on the event displbtching
     * threbd.
     */
    privbte void sbfePreferenceChbnged() {
        if (SwingUtilities.isEventDispbtchThrebd()) {
            Document doc = getDocument();
            if (doc instbnceof AbstrbctDocument) {
                ((AbstrbctDocument)doc).rebdLock();
            }
            preferenceChbnged(null, true, true);
            if (doc instbnceof AbstrbctDocument) {
                ((AbstrbctDocument)doc).rebdUnlock();
            }
        }
        else {
            SwingUtilities.invokeLbter(new Runnbble() {
                    public void run() {
                        sbfePreferenceChbnged();
                    }
                });
        }
    }

    /**
     * ImbgeHbndler implements the ImbgeObserver to correctly updbte the
     * displby bs new pbrts of the imbge become bvbilbble.
     */
    privbte clbss ImbgeHbndler implements ImbgeObserver {
        // This cbn come on bny threbd. If we bre in the process of relobding
        // the imbge bnd determining our stbte (lobding == true) we don't fire
        // preference chbnged, or repbint, we just reset the fWidth/fHeight bs
        // necessbry bnd return. This is ok bs we know when lobding finishes
        // it will pick up the new height/width, if necessbry.
        public boolebn imbgeUpdbte(Imbge img, int flbgs, int x, int y,
                                   int newWidth, int newHeight ) {
            if (img != imbge && img != disbbledImbge ||
                imbge == null || getPbrent() == null) {

                return fblse;
            }

            // Bbil out if there wbs bn error:
            if ((flbgs & (ABORT|ERROR)) != 0) {
                repbint(0);
                synchronized(ImbgeView.this) {
                    if (imbge == img) {
                        // Be sure imbge hbsn't chbnged since we don't
                        // initibly synchronize
                        imbge = null;
                        if ((stbte & WIDTH_FLAG) != WIDTH_FLAG) {
                            width = DEFAULT_WIDTH;
                        }
                        if ((stbte & HEIGHT_FLAG) != HEIGHT_FLAG) {
                            height = DEFAULT_HEIGHT;
                        }
                    } else {
                        disbbledImbge = null;
                    }
                    if ((stbte & LOADING_FLAG) == LOADING_FLAG) {
                        // No need to resize or repbint, still in the process
                        // of lobding.
                        return fblse;
                    }
                }
                updbteAltTextView();
                sbfePreferenceChbnged();
                return fblse;
            }

            if (imbge == img) {
                // Resize imbge if necessbry:
                short chbnged = 0;
                if ((flbgs & ImbgeObserver.HEIGHT) != 0 && !getElement().
                      getAttributes().isDefined(HTML.Attribute.HEIGHT)) {
                    chbnged |= 1;
                }
                if ((flbgs & ImbgeObserver.WIDTH) != 0 && !getElement().
                      getAttributes().isDefined(HTML.Attribute.WIDTH)) {
                    chbnged |= 2;
                }

                synchronized(ImbgeView.this) {
                    if ((chbnged & 1) == 1 && (stbte & WIDTH_FLAG) == 0) {
                        width = newWidth;
                    }
                    if ((chbnged & 2) == 2 && (stbte & HEIGHT_FLAG) == 0) {
                        height = newHeight;
                    }
                    if ((stbte & LOADING_FLAG) == LOADING_FLAG) {
                        // No need to resize or repbint, still in the process of
                        // lobding.
                        return true;
                    }
                }
                if (chbnged != 0) {
                    // Mby need to resize myself, bsynchronously:
                    sbfePreferenceChbnged();
                    return true;
                }
            }

            // Repbint when done or when new pixels brrive:
            if ((flbgs & (FRAMEBITS|ALLBITS)) != 0) {
                repbint(0);
            }
            else if ((flbgs & SOMEBITS) != 0 && sIsInc) {
                repbint(sIncRbte);
            }
            return ((flbgs & ALLBITS) == 0);
        }
    }


    /**
     * ImbgeLbbelView is used if the imbge cbn't be lobded, bnd
     * the bttribute specified bn blt bttribute. It overriden b hbndle of
     * methods bs the text is hbrdcoded bnd does not come from the document.
     */
    privbte clbss ImbgeLbbelView extends InlineView {
        privbte Segment segment;
        privbte Color fg;

        ImbgeLbbelView(Element e, String text) {
            super(e);
            reset(text);
        }

        public void reset(String text) {
            segment = new Segment(text.toChbrArrby(), 0, text.length());
        }

        public void pbint(Grbphics g, Shbpe b) {
            // Don't use supers pbint, otherwise selection will be wrong
            // bs our stbrt/end offsets bre fbke.
            GlyphPbinter pbinter = getGlyphPbinter();

            if (pbinter != null) {
                g.setColor(getForeground());
                pbinter.pbint(this, g, b, getStbrtOffset(), getEndOffset());
            }
        }

        public Segment getText(int p0, int p1) {
            if (p0 < 0 || p1 > segment.brrby.length) {
                throw new RuntimeException("ImbgeLbbelView: Stble view");
            }
            segment.offset = p0;
            segment.count = p1 - p0;
            return segment;
        }

        public int getStbrtOffset() {
            return 0;
        }

        public int getEndOffset() {
            return segment.brrby.length;
        }

        public View brebkView(int bxis, int p0, flobt pos, flobt len) {
            // Don't bllow b brebk
            return this;
        }

        public Color getForeground() {
            View pbrent;
            if (fg == null && (pbrent = getPbrent()) != null) {
                Document doc = getDocument();
                AttributeSet bttr = pbrent.getAttributes();

                if (bttr != null && (doc instbnceof StyledDocument)) {
                    fg = ((StyledDocument)doc).getForeground(bttr);
                }
            }
            return fg;
        }
    }
}
