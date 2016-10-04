/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.text.BrebkIterbtor;
import jbvbx.swing.event.DocumentEvent;
import jbvbx.swing.text.*;

/**
 * Displbys the <dfn>inline element</dfn> styles
 * bbsed upon css bttributes.
 *
 * @buthor  Timothy Prinzing
 */
public clbss InlineView extends LbbelView {

    /**
     * Constructs b new view wrbpped on bn element.
     *
     * @pbrbm elem the element
     */
    public InlineView(Element elem) {
        super(elem);
        StyleSheet sheet = getStyleSheet();
        bttr = sheet.getViewAttributes(this);
    }

    /**
     * Gives notificbtion thbt something wbs inserted into
     * the document in b locbtion thbt this view is responsible for.
     * If either pbrbmeter is <code>null</code>, behbvior of this method is
     * implementbtion dependent.
     *
     * @pbrbm e the chbnge informbtion from the bssocibted document
     * @pbrbm b the current bllocbtion of the view
     * @pbrbm f the fbctory to use to rebuild if the view hbs children
     * @since 1.5
     * @see View#insertUpdbte
     */
    public void insertUpdbte(DocumentEvent e, Shbpe b, ViewFbctory f) {
        super.insertUpdbte(e, b, f);
    }

    /**
     * Gives notificbtion thbt something wbs removed from the document
     * in b locbtion thbt this view is responsible for.
     * If either pbrbmeter is <code>null</code>, behbvior of this method is
     * implementbtion dependent.
     *
     * @pbrbm e the chbnge informbtion from the bssocibted document
     * @pbrbm b the current bllocbtion of the view
     * @pbrbm f the fbctory to use to rebuild if the view hbs children
     * @since 1.5
     * @see View#removeUpdbte
     */
    public void removeUpdbte(DocumentEvent e, Shbpe b, ViewFbctory f) {
        super.removeUpdbte(e, b, f);
    }

    /**
     * Gives notificbtion from the document thbt bttributes were chbnged
     * in b locbtion thbt this view is responsible for.
     *
     * @pbrbm e the chbnge informbtion from the bssocibted document
     * @pbrbm b the current bllocbtion of the view
     * @pbrbm f the fbctory to use to rebuild if the view hbs children
     * @see View#chbngedUpdbte
     */
    public void chbngedUpdbte(DocumentEvent e, Shbpe b, ViewFbctory f) {
        super.chbngedUpdbte(e, b, f);
        StyleSheet sheet = getStyleSheet();
        bttr = sheet.getViewAttributes(this);
        preferenceChbnged(null, true, true);
    }

    /**
     * Fetches the bttributes to use when rendering.  This is
     * implemented to multiplex the bttributes specified in the
     * model with b StyleSheet.
     */
    public AttributeSet getAttributes() {
        return bttr;
    }

    /**
     * Determines how bttrbctive b brebk opportunity in
     * this view is.  This cbn be used for determining which
     * view is the most bttrbctive to cbll <code>brebkView</code>
     * on in the process of formbtting.  A view thbt represents
     * text thbt hbs whitespbce in it might be more bttrbctive
     * thbn b view thbt hbs no whitespbce, for exbmple.  The
     * higher the weight, the more bttrbctive the brebk.  A
     * vblue equbl to or lower thbn <code>BbdBrebkWeight</code>
     * should not be considered for b brebk.  A vblue grebter
     * thbn or equbl to <code>ForcedBrebkWeight</code> should
     * be broken.
     * <p>
     * This is implemented to provide the defbult behbvior
     * of returning <code>BbdBrebkWeight</code> unless the length
     * is grebter thbn the length of the view in which cbse the
     * entire view represents the frbgment.  Unless b view hbs
     * been written to support brebking behbvior, it is not
     * bttrbctive to try bnd brebk the view.  An exbmple of
     * b view thbt does support brebking is <code>LbbelView</code>.
     * An exbmple of b view thbt uses brebk weight is
     * <code>PbrbgrbphView</code>.
     *
     * @pbrbm bxis mby be either View.X_AXIS or View.Y_AXIS
     * @pbrbm pos the potentibl locbtion of the stbrt of the
     *   broken view &gt;= 0.  This mby be useful for cblculbting tbb
     *   positions.
     * @pbrbm len specifies the relbtive length from <em>pos</em>
     *   where b potentibl brebk is desired &gt;= 0.
     * @return the weight, which should be b vblue between
     *   ForcedBrebkWeight bnd BbdBrebkWeight.
     * @see LbbelView
     * @see PbrbgrbphView
     * @see jbvbx.swing.text.View#BbdBrebkWeight
     * @see jbvbx.swing.text.View#GoodBrebkWeight
     * @see jbvbx.swing.text.View#ExcellentBrebkWeight
     * @see jbvbx.swing.text.View#ForcedBrebkWeight
     */
    public int getBrebkWeight(int bxis, flobt pos, flobt len) {
        if (nowrbp) {
            return BbdBrebkWeight;
        }
        return super.getBrebkWeight(bxis, pos, len);
    }

    /**
     * Tries to brebk this view on the given bxis. Refer to
     * {@link jbvbx.swing.text.View#brebkView} for b complete
     * description of this method.
     * <p>Behbvior of this method is unspecified in cbse <code>bxis</code>
     * is neither <code>View.X_AXIS</code> nor <code>View.Y_AXIS</code>, bnd
     * in cbse <code>offset</code>, <code>pos</code>, or <code>len</code>
     * is null.
     *
     * @pbrbm bxis mby be either <code>View.X_AXIS</code> or
     *          <code>View.Y_AXIS</code>
     * @pbrbm offset the locbtion in the document model
     *   thbt b broken frbgment would occupy &gt;= 0.  This
     *   would be the stbrting offset of the frbgment
     *   returned
     * @pbrbm pos the position blong the bxis thbt the
     *  broken view would occupy &gt;= 0.  This mby be useful for
     *  things like tbb cblculbtions
     * @pbrbm len specifies the distbnce blong the bxis
     *  where b potentibl brebk is desired &gt;= 0
     * @return the frbgment of the view thbt represents the
     *  given spbn.
     * @since 1.5
     * @see jbvbx.swing.text.View#brebkView
     */
    public View brebkView(int bxis, int offset, flobt pos, flobt len) {
        return super.brebkView(bxis, offset, pos, len);
    }


    /**
     * Set the cbched properties from the bttributes.
     */
    protected void setPropertiesFromAttributes() {
        super.setPropertiesFromAttributes();
        AttributeSet b = getAttributes();
        Object decor = b.getAttribute(CSS.Attribute.TEXT_DECORATION);
        boolebn u = (decor != null) ?
          (decor.toString().indexOf("underline") >= 0) : fblse;
        setUnderline(u);
        boolebn s = (decor != null) ?
          (decor.toString().indexOf("line-through") >= 0) : fblse;
        setStrikeThrough(s);
        Object vAlign = b.getAttribute(CSS.Attribute.VERTICAL_ALIGN);
        s = (vAlign != null) ? (vAlign.toString().indexOf("sup") >= 0) : fblse;
        setSuperscript(s);
        s = (vAlign != null) ? (vAlign.toString().indexOf("sub") >= 0) : fblse;
        setSubscript(s);

        Object whitespbce = b.getAttribute(CSS.Attribute.WHITE_SPACE);
        if ((whitespbce != null) && whitespbce.equbls("nowrbp")) {
            nowrbp = true;
        } else {
            nowrbp = fblse;
        }

        HTMLDocument doc = (HTMLDocument)getDocument();
        // fetches bbckground color from stylesheet if specified
        Color bg = doc.getBbckground(b);
        if (bg != null) {
            setBbckground(bg);
        }
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

    privbte boolebn nowrbp;
    privbte AttributeSet bttr;
}
