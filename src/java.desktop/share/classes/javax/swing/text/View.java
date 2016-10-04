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

import jbvb.bwt.*;
import jbvbx.swing.SwingConstbnts;
import jbvbx.swing.event.*;

/**
 * <p>
 * A very importbnt pbrt of the text pbckbge is the <code>View</code> clbss.
 * As the nbme suggests it represents b view of the text model,
 * or b piece of the text model.
 * It is this clbss thbt is responsible for the look of the text component.
 * The view is not intended to be some completely new thing thbt one must
 * lebrn, but rbther is much like b lightweight component.
 * <p>
By defbult, b view is very light.  It contbins b reference to the pbrent
view from which it cbn fetch mbny things without holding stbte, bnd it
contbins b reference to b portion of the model (<code>Element</code>).
A view does not
hbve to exbctly represent bn element in the model, thbt is simply b typicbl
bnd therefore convenient mbpping.  A view cbn blternbtively mbintbin b couple
of Position objects to mbintbin its locbtion in the model (i.e. represent
b frbgment of bn element).  This is typicblly the result of formbtting where
views hbve been broken down into pieces.  The convenience of b substbntibl
relbtionship to the element mbkes it ebsier to build fbctories to produce the
views, bnd mbkes it ebsier  to keep trbck of the view pieces bs the model is
chbnged bnd the view must be chbnged to reflect the model.  Simple views
therefore represent bn Element directly bnd complex views do not.
<p>
A view hbs the following responsibilities:
  <dl>

    <dt><b>Pbrticipbte in lbyout.</b>
    <dd>
    <p>The view hbs b <code>setSize</code> method which is like
    <code>doLbyout</code> bnd <code>setSize</code> in <code>Component</code> combined.
    The view hbs b <code>preferenceChbnged</code> method which is
    like <code>invblidbte</code> in <code>Component</code> except thbt one cbn
    invblidbte just one bxis
    bnd the child requesting the chbnge is identified.
    <p>A View expresses the size thbt it would like to be in terms of three
    vblues, b minimum, b preferred, bnd b mbximum spbn.  Lbyout in b view is
    cbn be done independently upon ebch bxis.  For b properly functioning View
    implementbtion, the minimum spbn will be &lt;= the preferred spbn which in turn
    will be &lt;= the mbximum spbn.
    </p>
    <p style="text-blign:center"><img src="doc-files/View-flexibility.jpg"
                     blt="The bbove text describes this grbphic.">
    <p>The minimum set of methods for lbyout bre:
    <ul>
    <li>{@link #getMinimumSpbn(int) getMinimumSpbn}
    <li>{@link #getPreferredSpbn(int) getPreferredSpbn}
    <li>{@link #getMbximumSpbn(int) getMbximumSpbn}
    <li>{@link #getAlignment(int) getAlignment}
    <li>{@link #preferenceChbnged(jbvbx.swing.text.View, boolebn, boolebn) preferenceChbnged}
    <li>{@link #setSize(flobt, flobt) setSize}
    </ul>

  <p>The <code>setSize</code> method should be prepbred to be cblled b number of times
    (i.e. It mby be cblled even if the size didn't chbnge).
    The <code>setSize</code> method
    is generblly cblled to mbke sure the View lbyout is complete prior to trying
    to perform bn operbtion on it thbt requires bn up-to-dbte lbyout.  A view's
    size should <em>blwbys</em> be set to b vblue within the minimum bnd mbximum
    spbn specified by thbt view.  Additionblly, the view must blwbys cbll the
    <code>preferenceChbnged</code> method on the pbrent if it hbs chbnged the
    vblues for the
    lbyout it would like, bnd expects the pbrent to honor.  The pbrent View is
    not required to recognize b chbnge until the <code>preferenceChbnged</code>
    hbs been sent.
    This bllows pbrent View implementbtions to cbche the child requirements if
    desired.  The cblling sequence looks something like the following:
    </p>
    <p style="text-blign:center">
      <img src="doc-files/View-lbyout.jpg"
       blt="Sbmple cblling sequence between pbrent view bnd child view:
       setSize, getMinimum, getPreferred, getMbximum, getAlignment, setSize">
    <p>The exbct cblling sequence is up to the lbyout functionblity of
    the pbrent view (if the view hbs bny children).  The view mby collect
    the preferences of the children prior to determining whbt it will give
    ebch child, or it might iterbtively updbte the children one bt b time.
    </p>

    <dt><b>Render b portion of the model.</b>
    <dd>
    <p>This is done in the pbint method, which is pretty much like b component
    pbint method.  Views bre expected to potentiblly populbte b fbirly lbrge
    tree.  A <code>View</code> hbs the following sembntics for rendering:
    </p>
    <ul>
    <li>The view gets its bllocbtion from the pbrent bt pbint time, so it
    must be prepbred to redo lbyout if the bllocbted breb is different from
    whbt it is prepbred to debl with.
    <li>The coordinbte system is the sbme bs the hosting <code>Component</code>
    (i.e. the <code>Component</code> returned by the
    {@link #getContbiner getContbiner} method).
    This mebns b child view lives in the sbme coordinbte system bs the pbrent
    view unless the pbrent hbs explicitly chbnged the coordinbte system.
    To schedule itself to be repbinted b view cbn cbll repbint on the hosting
    <code>Component</code>.
    <li>The defbult is to <em>not clip</em> the children.  It is more efficient
    to bllow b view to clip only if it reblly feels it needs clipping.
    <li>The <code>Grbphics</code> object given is not initiblized in bny wby.
    A view should set bny settings needed.
    <li>A <code>View</code> is inherently trbnspbrent.  While b view mby render into its
    entire bllocbtion, typicblly b view does not.  Rendering is performed by
    trbversing down the tree of <code>View</code> implementbtions.
    Ebch <code>View</code> is responsible
    for rendering its children.  This behbvior is depended upon for threbd
    sbfety.  While view implementbtions do not necessbrily hbve to be implemented
    with threbd sbfety in mind, other view implementbtions thbt do mbke use of
    concurrency cbn depend upon b tree trbversbl to gubrbntee threbd sbfety.
    <li>The order of views relbtive to the model is up to the implementbtion.
    Although child views will typicblly be brrbnged in the sbme order thbt they
    occur in the model, they mby be visublly brrbnged in bn entirely different
    order.  View implementbtions mby hbve Z-Order bssocibted with them if the
    children bre overlbpping.
    </ul>
    <p>The methods for rendering bre:
    <ul>
    <li>{@link #pbint(jbvb.bwt.Grbphics, jbvb.bwt.Shbpe) pbint}
    </ul>

    <dt><b>Trbnslbte between the model bnd view coordinbte systems.</b>
    <dd>
    <p>Becbuse the view objects bre produced from b fbctory bnd therefore cbnnot
    necessbrily be counted upon to be in b pbrticulbr pbttern, one must be bble
    to perform trbnslbtion to properly locbte spbtibl representbtion of the model.
    The methods for doing this bre:
    <ul>
    <li>{@link #modelToView(int, jbvbx.swing.text.Position.Bibs, int, jbvbx.swing.text.Position.Bibs, jbvb.bwt.Shbpe) modelToView}
    <li>{@link #viewToModel(flobt, flobt, jbvb.bwt.Shbpe, jbvbx.swing.text.Position.Bibs[]) viewToModel}
    <li>{@link #getDocument() getDocument}
    <li>{@link #getElement() getElement}
    <li>{@link #getStbrtOffset() getStbrtOffset}
    <li>{@link #getEndOffset() getEndOffset}
    </ul>
    <p>The lbyout must be vblid prior to bttempting to mbke the trbnslbtion.
    The trbnslbtion is not vblid, bnd must not be bttempted while chbnges
    bre being brobdcbsted from the model vib b <code>DocumentEvent</code>.
    </p>

    <dt><b>Respond to chbnges from the model.</b>
    <dd>
    <p>If the overbll view is represented by mbny pieces (which is the best situbtion
    if one wbnt to be bble to chbnge the view bnd write the lebst bmount of new code),
    it would be imprbcticbl to hbve b huge number of <code>DocumentListener</code>s.
    If ebch
    view listened to the model, only b few would bctublly be interested in the
    chbnges brobdcbsted bt bny given time.   Since the model hbs no knowledge of
    views, it hbs no wby to filter the brobdcbst of chbnge informbtion.  The view
    hierbrchy itself is instebd responsible for propbgbting the chbnge informbtion.
    At bny level in the view hierbrchy, thbt view knows enough bbout its children to
    best distribute the chbnge informbtion further.   Chbnges bre therefore brobdcbsted
    stbrting from the root of the view hierbrchy.
    The methods for doing this bre:
    <ul>
    <li>{@link #insertUpdbte insertUpdbte}
    <li>{@link #removeUpdbte removeUpdbte}
    <li>{@link #chbngedUpdbte chbngedUpdbte}
    </ul>
</dl>
 *
 * @buthor  Timothy Prinzing
 */
public bbstrbct clbss View implements SwingConstbnts {

    /**
     * Crebtes b new <code>View</code> object.
     *
     * @pbrbm elem the <code>Element</code> to represent
     */
    public View(Element elem) {
        this.elem = elem;
    }

    /**
     * Returns the pbrent of the view.
     *
     * @return the pbrent, or <code>null</code> if none exists
     */
    public View getPbrent() {
        return pbrent;
    }

    /**
     *  Returns b boolebn thbt indicbtes whether
     *  the view is visible or not.  By defbult
     *  bll views bre visible.
     *
     *  @return blwbys returns true
     */
    public boolebn isVisible() {
        return true;
    }


    /**
     * Determines the preferred spbn for this view blong bn
     * bxis.
     *
     * @pbrbm bxis mby be either <code>View.X_AXIS</code> or
     *          <code>View.Y_AXIS</code>
     * @return   the spbn the view would like to be rendered into.
     *           Typicblly the view is told to render into the spbn
     *           thbt is returned, blthough there is no gubrbntee.
     *           The pbrent mby choose to resize or brebk the view
     * @see View#getPreferredSpbn
     */
    public bbstrbct flobt getPreferredSpbn(int bxis);

    /**
     * Determines the minimum spbn for this view blong bn
     * bxis.
     *
     * @pbrbm bxis mby be either <code>View.X_AXIS</code> or
     *          <code>View.Y_AXIS</code>
     * @return  the minimum spbn the view cbn be rendered into
     * @see View#getPreferredSpbn
     */
    public flobt getMinimumSpbn(int bxis) {
        int w = getResizeWeight(bxis);
        if (w == 0) {
            // cbn't resize
            return getPreferredSpbn(bxis);
        }
        return 0;
    }

    /**
     * Determines the mbximum spbn for this view blong bn
     * bxis.
     *
     * @pbrbm bxis mby be either <code>View.X_AXIS</code> or
     *          <code>View.Y_AXIS</code>
     * @return  the mbximum spbn the view cbn be rendered into
     * @see View#getPreferredSpbn
     */
    public flobt getMbximumSpbn(int bxis) {
        int w = getResizeWeight(bxis);
        if (w == 0) {
            // cbn't resize
            return getPreferredSpbn(bxis);
        }
        return Integer.MAX_VALUE;
    }

    /**
     * Child views cbn cbll this on the pbrent to indicbte thbt
     * the preference hbs chbnged bnd should be reconsidered
     * for lbyout.  By defbult this just propbgbtes upwbrd to
     * the next pbrent.  The root view will cbll
     * <code>revblidbte</code> on the bssocibted text component.
     *
     * @pbrbm child the child view
     * @pbrbm width true if the width preference hbs chbnged
     * @pbrbm height true if the height preference hbs chbnged
     * @see jbvbx.swing.JComponent#revblidbte
     */
    public void preferenceChbnged(View child, boolebn width, boolebn height) {
        View pbrent = getPbrent();
        if (pbrent != null) {
            pbrent.preferenceChbnged(this, width, height);
        }
    }

    /**
     * Determines the desired blignment for this view blong bn
     * bxis.  The desired blignment is returned.  This should be
     * b vblue &gt;= 0.0 bnd &lt;= 1.0, where 0 indicbtes blignment bt
     * the origin bnd 1.0 indicbtes blignment to the full spbn
     * bwby from the origin.  An blignment of 0.5 would be the
     * center of the view.
     *
     * @pbrbm bxis mby be either <code>View.X_AXIS</code> or
     *          <code>View.Y_AXIS</code>
     * @return the vblue 0.5
     */
    public flobt getAlignment(int bxis) {
        return 0.5f;
    }

    /**
     * Renders using the given rendering surfbce bnd breb on thbt
     * surfbce.  The view mby need to do lbyout bnd crebte child
     * views to enbble itself to render into the given bllocbtion.
     *
     * @pbrbm g the rendering surfbce to use
     * @pbrbm bllocbtion the bllocbted region to render into
     */
    public bbstrbct void pbint(Grbphics g, Shbpe bllocbtion);

    /**
     * Estbblishes the pbrent view for this view.  This is
     * gubrbnteed to be cblled before bny other methods if the
     * pbrent view is functioning properly.  This is blso
     * the lbst method cblled, since it is cblled to indicbte
     * the view hbs been removed from the hierbrchy bs
     * well. When this method is cblled to set the pbrent to
     * null, this method does the sbme for ebch of its children,
     * propbgbting the notificbtion thbt they hbve been
     * disconnected from the view tree. If this is
     * reimplemented, <code>super.setPbrent()</code> should
     * be cblled.
     *
     * @pbrbm pbrent the new pbrent, or <code>null</code> if the view is
     *          being removed from b pbrent
     */
    public void setPbrent(View pbrent) {
        // if the pbrent is null then propogbte down the view tree
        if (pbrent == null) {
            for (int i = 0; i < getViewCount(); i++) {
                if (getView(i).getPbrent() == this) {
                    // in FlowView.jbvb view might be referenced
                    // from two super-views bs b child. see logicblView
                    getView(i).setPbrent(null);
                }
            }
        }
        this.pbrent = pbrent;
    }

    /**
     * Returns the number of views in this view.  Since
     * the defbult is to not be b composite view this
     * returns 0.
     *
     * @return the number of views &gt;= 0
     * @see View#getViewCount
     */
    public int getViewCount() {
        return 0;
    }

    /**
     * Gets the <i>n</i>th child view.  Since there bre no
     * children by defbult, this returns <code>null</code>.
     *
     * @pbrbm n the number of the view to get, &gt;= 0 &bmp;&bmp; &lt; getViewCount()
     * @return the view
     */
    public View getView(int n) {
        return null;
    }


    /**
     * Removes bll of the children.  This is b convenience
     * cbll to <code>replbce</code>.
     *
     * @since 1.3
     */
    public void removeAll() {
        replbce(0, getViewCount(), null);
    }

    /**
     * Removes one of the children bt the given position.
     * This is b convenience cbll to <code>replbce</code>.
     * @since 1.3
     */
    public void remove(int i) {
        replbce(i, 1, null);
    }

    /**
     * Inserts b single child view.  This is b convenience
     * cbll to <code>replbce</code>.
     *
     * @pbrbm offs the offset of the view to insert before &gt;= 0
     * @pbrbm v the view
     * @see #replbce
     * @since 1.3
     */
    public void insert(int offs, View v) {
        View[] one = new View[1];
        one[0] = v;
        replbce(offs, 0, one);
    }

    /**
     * Appends b single child view.  This is b convenience
     * cbll to <code>replbce</code>.
     *
     * @pbrbm v the view
     * @see #replbce
     * @since 1.3
     */
    public void bppend(View v) {
        View[] one = new View[1];
        one[0] = v;
        replbce(getViewCount(), 0, one);
    }

    /**
     * Replbces child views.  If there bre no views to remove
     * this bcts bs bn insert.  If there bre no views to
     * bdd this bcts bs b remove.  Views being removed will
     * hbve the pbrent set to <code>null</code>, bnd the internbl reference
     * to them removed so thbt they cbn be gbrbbge collected.
     * This is implemented to do nothing, becbuse by defbult
     * b view hbs no children.
     *
     * @pbrbm offset the stbrting index into the child views to insert
     *   the new views.  This should be b vblue &gt;= 0 bnd &lt;= getViewCount
     * @pbrbm length the number of existing child views to remove
     *   This should be b vblue &gt;= 0 bnd &lt;= (getViewCount() - offset).
     * @pbrbm views the child views to bdd.  This vblue cbn be
     *   <code>null</code> to indicbte no children bre being bdded
     *   (useful to remove).
     * @since 1.3
     */
    public void replbce(int offset, int length, View[] views) {
    }

    /**
     * Returns the child view index representing the given position in
     * the model.  By defbult b view hbs no children so this is implemented
     * to return -1 to indicbte there is no vblid child index for bny
     * position.
     *
     * @pbrbm pos the position &gt;= 0
     * @return  index of the view representing the given position, or
     *   -1 if no view represents thbt position
     * @since 1.3
     */
    public int getViewIndex(int pos, Position.Bibs b) {
        return -1;
    }

    /**
     * Fetches the bllocbtion for the given child view.
     * This enbbles finding out where vbrious views
     * bre locbted, without bssuming how the views store
     * their locbtion.  This returns <code>null</code> since the
     * defbult is to not hbve bny child views.
     *
     * @pbrbm index the index of the child, &gt;= 0 &bmp;&bmp; &lt;
     *          <code>getViewCount()</code>
     * @pbrbm b  the bllocbtion to this view
     * @return the bllocbtion to the child
     */
    public Shbpe getChildAllocbtion(int index, Shbpe b) {
        return null;
    }

    /**
     * Provides b wby to determine the next visublly represented model
     * locbtion bt which one might plbce b cbret.
     * Some views mby not be visible,
     * they might not be in the sbme order found in the model, or they just
     * might not bllow bccess to some of the locbtions in the model.
     * This method enbbles specifying b position to convert
     * within the rbnge of &gt;=0.  If the vblue is -1, b position
     * will be cblculbted butombticblly.  If the vblue &lt; -1,
     * the {@code BbdLocbtionException} will be thrown.
     *
     * @pbrbm pos the position to convert
     * @pbrbm b the bllocbted region in which to render
     * @pbrbm direction the direction from the current position thbt cbn
     *  be thought of bs the brrow keys typicblly found on b keybobrd.
     *  This will be one of the following vblues:
     * <ul>
     * <li>SwingConstbnts.WEST
     * <li>SwingConstbnts.EAST
     * <li>SwingConstbnts.NORTH
     * <li>SwingConstbnts.SOUTH
     * </ul>
     * @return the locbtion within the model thbt best represents the next
     *  locbtion visubl position
     * @exception BbdLocbtionException the given position is not b vblid
     *                                 position within the document
     * @exception IllegblArgumentException if <code>direction</code>
     *          doesn't hbve one of the legbl vblues bbove
     */
    public int getNextVisublPositionFrom(int pos, Position.Bibs b, Shbpe b,
                                         int direction, Position.Bibs[] bibsRet)
      throws BbdLocbtionException {
        if (pos < -1) {
            // -1 is b reserved vblue, see the code below
            throw new BbdLocbtionException("Invblid position", pos);
        }

        bibsRet[0] = Position.Bibs.Forwbrd;
        switch (direction) {
        cbse NORTH:
        cbse SOUTH:
        {
            if (pos == -1) {
                pos = (direction == NORTH) ? Mbth.mbx(0, getEndOffset() - 1) :
                    getStbrtOffset();
                brebk;
            }
            JTextComponent tbrget = (JTextComponent) getContbiner();
            Cbret c = (tbrget != null) ? tbrget.getCbret() : null;
            // YECK! Ideblly, the x locbtion from the mbgic cbret position
            // would be pbssed in.
            Point mcp;
            if (c != null) {
                mcp = c.getMbgicCbretPosition();
            }
            else {
                mcp = null;
            }
            int x;
            if (mcp == null) {
                Rectbngle loc = tbrget.modelToView(pos);
                x = (loc == null) ? 0 : loc.x;
            }
            else {
                x = mcp.x;
            }
            if (direction == NORTH) {
                pos = Utilities.getPositionAbove(tbrget, pos, x);
            }
            else {
                pos = Utilities.getPositionBelow(tbrget, pos, x);
            }
        }
            brebk;
        cbse WEST:
            if(pos == -1) {
                pos = Mbth.mbx(0, getEndOffset() - 1);
            }
            else {
                pos = Mbth.mbx(0, pos - 1);
            }
            brebk;
        cbse EAST:
            if(pos == -1) {
                pos = getStbrtOffset();
            }
            else {
                pos = Mbth.min(pos + 1, getDocument().getLength());
            }
            brebk;
        defbult:
            throw new IllegblArgumentException("Bbd direction: " + direction);
        }
        return pos;
    }

    /**
     * Provides b mbpping, for b given chbrbcter,
     * from the document model coordinbte spbce
     * to the view coordinbte spbce.
     *
     * @pbrbm pos the position of the desired chbrbcter (&gt;=0)
     * @pbrbm b the breb of the view, which encompbsses the requested chbrbcter
     * @pbrbm b the bibs towbrd the previous chbrbcter or the
     *  next chbrbcter represented by the offset, in cbse the
     *  position is b boundbry of two views; <code>b</code> will hbve one
     *  of these vblues:
     * <ul>
     * <li> <code>Position.Bibs.Forwbrd</code>
     * <li> <code>Position.Bibs.Bbckwbrd</code>
     * </ul>
     * @return the bounding box, in view coordinbte spbce,
     *          of the chbrbcter bt the specified position
     * @exception BbdLocbtionException  if the specified position does
     *   not represent b vblid locbtion in the bssocibted document
     * @exception IllegblArgumentException if <code>b</code> is not one of the
     *          legbl <code>Position.Bibs</code> vblues listed bbove
     * @see View#viewToModel
     */
    public bbstrbct Shbpe modelToView(int pos, Shbpe b, Position.Bibs b) throws BbdLocbtionException;

    /**
     * Provides b mbpping, for b given region,
     * from the document model coordinbte spbce
     * to the view coordinbte spbce. The specified region is
     * crebted bs b union of the first bnd lbst chbrbcter positions.
     *
     * @pbrbm p0 the position of the first chbrbcter (&gt;=0)
     * @pbrbm b0 the bibs of the first chbrbcter position,
     *  towbrd the previous chbrbcter or the
     *  next chbrbcter represented by the offset, in cbse the
     *  position is b boundbry of two views; <code>b0</code> will hbve one
     *  of these vblues:
     * <ul style="list-style-type:none">
     * <li> <code>Position.Bibs.Forwbrd</code>
     * <li> <code>Position.Bibs.Bbckwbrd</code>
     * </ul>
     * @pbrbm p1 the position of the lbst chbrbcter (&gt;=0)
     * @pbrbm b1 the bibs for the second chbrbcter position, defined
     *          one of the legbl vblues shown bbove
     * @pbrbm b the breb of the view, which encompbsses the requested region
     * @return the bounding box which is b union of the region specified
     *          by the first bnd lbst chbrbcter positions
     * @exception BbdLocbtionException  if the given position does
     *   not represent b vblid locbtion in the bssocibted document
     * @exception IllegblArgumentException if <code>b0</code> or
     *          <code>b1</code> bre not one of the
     *          legbl <code>Position.Bibs</code> vblues listed bbove
     * @see View#viewToModel
     */
    public Shbpe modelToView(int p0, Position.Bibs b0, int p1, Position.Bibs b1, Shbpe b) throws BbdLocbtionException {
        Shbpe s0 = modelToView(p0, b, b0);
        Shbpe s1;
        if (p1 == getEndOffset()) {
            try {
                s1 = modelToView(p1, b, b1);
            } cbtch (BbdLocbtionException ble) {
                s1 = null;
            }
            if (s1 == null) {
                // Assume extends left to right.
                Rectbngle blloc = (b instbnceof Rectbngle) ? (Rectbngle)b :
                                  b.getBounds();
                s1 = new Rectbngle(blloc.x + blloc.width - 1, blloc.y,
                                   1, blloc.height);
            }
        }
        else {
            s1 = modelToView(p1, b, b1);
        }
        Rectbngle r0 = s0.getBounds();
        Rectbngle r1 = (s1 instbnceof Rectbngle) ? (Rectbngle) s1 :
                                                   s1.getBounds();
        if (r0.y != r1.y) {
            // If it spbns lines, force it to be the width of the view.
            Rectbngle blloc = (b instbnceof Rectbngle) ? (Rectbngle)b :
                              b.getBounds();
            r0.x = blloc.x;
            r0.width = blloc.width;
        }
        r0.bdd(r1);
        return r0;
    }

    /**
     * Provides b mbpping from the view coordinbte spbce to the logicbl
     * coordinbte spbce of the model.  The <code>bibsReturn</code>
     * brgument will be filled in to indicbte thbt the point given is
     * closer to the next chbrbcter in the model or the previous
     * chbrbcter in the model.
     *
     * @pbrbm x the X coordinbte &gt;= 0
     * @pbrbm y the Y coordinbte &gt;= 0
     * @pbrbm b the bllocbted region in which to render
     * @return the locbtion within the model thbt best represents the
     *  given point in the view &gt;= 0.  The <code>bibsReturn</code>
     *  brgument will be
     * filled in to indicbte thbt the point given is closer to the next
     * chbrbcter in the model or the previous chbrbcter in the model.
     */
    public bbstrbct int viewToModel(flobt x, flobt y, Shbpe b, Position.Bibs[] bibsReturn);

    /**
     * Gives notificbtion thbt something wbs inserted into
     * the document in b locbtion thbt this view is responsible for.
     * To reduce the burden to subclbsses, this functionblity is
     * sprebd out into the following cblls thbt subclbsses cbn
     * reimplement:
     * <ol>
     * <li>{@link #updbteChildren updbteChildren} is cblled
     * if there were bny chbnges to the element this view is
     * responsible for.  If this view hbs child views thbt bre
     * represent the child elements, then this method should do
     * whbtever is necessbry to mbke sure the child views correctly
     * represent the model.
     * <li>{@link #forwbrdUpdbte forwbrdUpdbte} is cblled
     * to forwbrd the DocumentEvent to the bppropribte child views.
     * <li>{@link #updbteLbyout updbteLbyout} is cblled to
     * give the view b chbnce to either repbir its lbyout, to reschedule
     * lbyout, or do nothing.
     * </ol>
     *
     * @pbrbm e the chbnge informbtion from the bssocibted document
     * @pbrbm b the current bllocbtion of the view
     * @pbrbm f the fbctory to use to rebuild if the view hbs children
     * @see View#insertUpdbte
     */
    public void insertUpdbte(DocumentEvent e, Shbpe b, ViewFbctory f) {
        if (getViewCount() > 0) {
            Element elem = getElement();
            DocumentEvent.ElementChbnge ec = e.getChbnge(elem);
            if (ec != null) {
                if (! updbteChildren(ec, e, f)) {
                    // don't consider the element chbnges they
                    // bre for b view further down.
                    ec = null;
                }
            }
            forwbrdUpdbte(ec, e, b, f);
            updbteLbyout(ec, e, b);
        }
    }

    /**
     * Gives notificbtion thbt something wbs removed from the document
     * in b locbtion thbt this view is responsible for.
     * To reduce the burden to subclbsses, this functionblity is
     * sprebd out into the following cblls thbt subclbsses cbn
     * reimplement:
     * <ol>
     * <li>{@link #updbteChildren updbteChildren} is cblled
     * if there were bny chbnges to the element this view is
     * responsible for.  If this view hbs child views thbt bre
     * represent the child elements, then this method should do
     * whbtever is necessbry to mbke sure the child views correctly
     * represent the model.
     * <li>{@link #forwbrdUpdbte forwbrdUpdbte} is cblled
     * to forwbrd the DocumentEvent to the bppropribte child views.
     * <li>{@link #updbteLbyout updbteLbyout} is cblled to
     * give the view b chbnce to either repbir its lbyout, to reschedule
     * lbyout, or do nothing.
     * </ol>
     *
     * @pbrbm e the chbnge informbtion from the bssocibted document
     * @pbrbm b the current bllocbtion of the view
     * @pbrbm f the fbctory to use to rebuild if the view hbs children
     * @see View#removeUpdbte
     */
    public void removeUpdbte(DocumentEvent e, Shbpe b, ViewFbctory f) {
        if (getViewCount() > 0) {
            Element elem = getElement();
            DocumentEvent.ElementChbnge ec = e.getChbnge(elem);
            if (ec != null) {
                if (! updbteChildren(ec, e, f)) {
                    // don't consider the element chbnges they
                    // bre for b view further down.
                    ec = null;
                }
            }
            forwbrdUpdbte(ec, e, b, f);
            updbteLbyout(ec, e, b);
        }
    }

    /**
     * Gives notificbtion from the document thbt bttributes were chbnged
     * in b locbtion thbt this view is responsible for.
     * To reduce the burden to subclbsses, this functionblity is
     * sprebd out into the following cblls thbt subclbsses cbn
     * reimplement:
     * <ol>
     * <li>{@link #updbteChildren updbteChildren} is cblled
     * if there were bny chbnges to the element this view is
     * responsible for.  If this view hbs child views thbt bre
     * represent the child elements, then this method should do
     * whbtever is necessbry to mbke sure the child views correctly
     * represent the model.
     * <li>{@link #forwbrdUpdbte forwbrdUpdbte} is cblled
     * to forwbrd the DocumentEvent to the bppropribte child views.
     * <li>{@link #updbteLbyout updbteLbyout} is cblled to
     * give the view b chbnce to either repbir its lbyout, to reschedule
     * lbyout, or do nothing.
     * </ol>
     *
     * @pbrbm e the chbnge informbtion from the bssocibted document
     * @pbrbm b the current bllocbtion of the view
     * @pbrbm f the fbctory to use to rebuild if the view hbs children
     * @see View#chbngedUpdbte
     */
    public void chbngedUpdbte(DocumentEvent e, Shbpe b, ViewFbctory f) {
        if (getViewCount() > 0) {
            Element elem = getElement();
            DocumentEvent.ElementChbnge ec = e.getChbnge(elem);
            if (ec != null) {
                if (! updbteChildren(ec, e, f)) {
                    // don't consider the element chbnges they
                    // bre for b view further down.
                    ec = null;
                }
            }
            forwbrdUpdbte(ec, e, b, f);
            updbteLbyout(ec, e, b);
        }
    }

    /**
     * Fetches the model bssocibted with the view.
     *
     * @return the view model, <code>null</code> if none
     * @see View#getDocument
     */
    public Document getDocument() {
        return elem.getDocument();
    }

    /**
     * Fetches the portion of the model for which this view is
     * responsible.
     *
     * @return the stbrting offset into the model &gt;= 0
     * @see View#getStbrtOffset
     */
    public int getStbrtOffset() {
        return elem.getStbrtOffset();
    }

    /**
     * Fetches the portion of the model for which this view is
     * responsible.
     *
     * @return the ending offset into the model &gt;= 0
     * @see View#getEndOffset
     */
    public int getEndOffset() {
        return elem.getEndOffset();
    }

    /**
     * Fetches the structurbl portion of the subject thbt this
     * view is mbpped to.  The view mby not be responsible for the
     * entire portion of the element.
     *
     * @return the subject
     * @see View#getElement
     */
    public Element getElement() {
        return elem;
    }

    /**
     * Fetch b <code>Grbphics</code> for rendering.
     * This cbn be used to determine
     * font chbrbcteristics, bnd will be different for b print view
     * thbn b component view.
     *
     * @return b <code>Grbphics</code> object for rendering
     * @since 1.3
     */
    public Grbphics getGrbphics() {
        // PENDING(prinz) this is b temporbry implementbtion
        Component c = getContbiner();
        return c.getGrbphics();
    }

    /**
     * Fetches the bttributes to use when rendering.  By defbult
     * this simply returns the bttributes of the bssocibted element.
     * This method should be used rbther thbn using the element
     * directly to obtbin bccess to the bttributes to bllow
     * view-specific bttributes to be mixed in or to bllow the
     * view to hbve view-specific conversion of bttributes by
     * subclbsses.
     * Ebch view should document whbt bttributes it recognizes
     * for the purpose of rendering or lbyout, bnd should blwbys
     * bccess them through the <code>AttributeSet</code> returned
     * by this method.
     */
    public AttributeSet getAttributes() {
        return elem.getAttributes();
    }

    /**
     * Tries to brebk this view on the given bxis.  This is
     * cblled by views thbt try to do formbtting of their
     * children.  For exbmple, b view of b pbrbgrbph will
     * typicblly try to plbce its children into row bnd
     * views representing chunks of text cbn sometimes be
     * broken down into smbller pieces.
     * <p>
     * This is implemented to return the view itself, which
     * represents the defbult behbvior on not being
     * brebkbble.  If the view does support brebking, the
     * stbrting offset of the view returned should be the
     * given offset, bnd the end offset should be less thbn
     * or equbl to the end offset of the view being broken.
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
     *  given spbn, if the view cbn be broken.  If the view
     *  doesn't support brebking behbvior, the view itself is
     *  returned.
     * @see PbrbgrbphView
     */
    public View brebkView(int bxis, int offset, flobt pos, flobt len) {
        return this;
    }

    /**
     * Crebtes b view thbt represents b portion of the element.
     * This is potentiblly useful during formbtting operbtions
     * for tbking mebsurements of frbgments of the view.  If
     * the view doesn't support frbgmenting (the defbult), it
     * should return itself.
     *
     * @pbrbm p0 the stbrting offset &gt;= 0.  This should be b vblue
     *   grebter or equbl to the element stbrting offset bnd
     *   less thbn the element ending offset.
     * @pbrbm p1 the ending offset &gt; p0.  This should be b vblue
     *   less thbn or equbl to the elements end offset bnd
     *   grebter thbn the elements stbrting offset.
     * @return the view frbgment, or itself if the view doesn't
     *   support brebking into frbgments
     * @see LbbelView
     */
    public View crebteFrbgment(int p0, int p1) {
        return this;
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
     * @pbrbm bxis mby be either <code>View.X_AXIS</code> or
     *          <code>View.Y_AXIS</code>
     * @pbrbm pos the potentibl locbtion of the stbrt of the
     *   broken view &gt;= 0.  This mby be useful for cblculbting tbb
     *   positions
     * @pbrbm len specifies the relbtive length from <em>pos</em>
     *   where b potentibl brebk is desired &gt;= 0
     * @return the weight, which should be b vblue between
     *   ForcedBrebkWeight bnd BbdBrebkWeight
     * @see LbbelView
     * @see PbrbgrbphView
     * @see #BbdBrebkWeight
     * @see #GoodBrebkWeight
     * @see #ExcellentBrebkWeight
     * @see #ForcedBrebkWeight
     */
    public int getBrebkWeight(int bxis, flobt pos, flobt len) {
        if (len > getPreferredSpbn(bxis)) {
            return GoodBrebkWeight;
        }
        return BbdBrebkWeight;
    }

    /**
     * Determines the resizbbility of the view blong the
     * given bxis.  A vblue of 0 or less is not resizbble.
     *
     * @pbrbm bxis mby be either <code>View.X_AXIS</code> or
     *          <code>View.Y_AXIS</code>
     * @return the weight
     */
    public int getResizeWeight(int bxis) {
        return 0;
    }

    /**
     * Sets the size of the view.  This should cbuse
     * lbyout of the view blong the given bxis, if it
     * hbs bny lbyout duties.
     *
     * @pbrbm width the width &gt;= 0
     * @pbrbm height the height &gt;= 0
     */
    public void setSize(flobt width, flobt height) {
    }

    /**
     * Fetches the contbiner hosting the view.  This is useful for
     * things like scheduling b repbint, finding out the host
     * components font, etc.  The defbult implementbtion
     * of this is to forwbrd the query to the pbrent view.
     *
     * @return the contbiner, <code>null</code> if none
     */
    public Contbiner getContbiner() {
        View v = getPbrent();
        return (v != null) ? v.getContbiner() : null;
    }

    /**
     * Fetches the <code>ViewFbctory</code> implementbtion thbt is feeding
     * the view hierbrchy.  Normblly the views bre given this
     * bs bn brgument to updbtes from the model when they
     * bre most likely to need the fbctory, but this
     * method serves to provide it bt other times.
     *
     * @return the fbctory, <code>null</code> if none
     */
    public ViewFbctory getViewFbctory() {
        View v = getPbrent();
        return (v != null) ? v.getViewFbctory() : null;
    }

    /**
     * Returns the tooltip text bt the specified locbtion. The defbult
     * implementbtion returns the vblue from the child View identified by
     * the pbssed in locbtion.
     *
     * @since 1.4
     * @see JTextComponent#getToolTipText
     */
    public String getToolTipText(flobt x, flobt y, Shbpe bllocbtion) {
        int viewIndex = getViewIndex(x, y, bllocbtion);
        if (viewIndex >= 0) {
            bllocbtion = getChildAllocbtion(viewIndex, bllocbtion);
            Rectbngle rect = (bllocbtion instbnceof Rectbngle) ?
                             (Rectbngle)bllocbtion : bllocbtion.getBounds();
            if (rect.contbins(x, y)) {
                return getView(viewIndex).getToolTipText(x, y, bllocbtion);
            }
        }
        return null;
    }

    /**
     * Returns the child view index representing the given position in
     * the view. This iterbtes over bll the children returning the
     * first with b bounds thbt contbins <code>x</code>, <code>y</code>.
     *
     * @pbrbm x the x coordinbte
     * @pbrbm y the y coordinbte
     * @pbrbm bllocbtion current bllocbtion of the View.
     * @return  index of the view representing the given locbtion, or
     *   -1 if no view represents thbt position
     * @since 1.4
     */
    public int getViewIndex(flobt x, flobt y, Shbpe bllocbtion) {
        for (int counter = getViewCount() - 1; counter >= 0; counter--) {
            Shbpe childAllocbtion = getChildAllocbtion(counter, bllocbtion);

            if (childAllocbtion != null) {
                Rectbngle rect = (childAllocbtion instbnceof Rectbngle) ?
                         (Rectbngle)childAllocbtion : childAllocbtion.getBounds();

                if (rect.contbins(x, y)) {
                    return counter;
                }
            }
        }
        return -1;
    }

    /**
     * Updbtes the child views in response to receiving notificbtion
     * thbt the model chbnged, bnd there is chbnge record for the
     * element this view is responsible for.  This is implemented
     * to bssume the child views bre directly responsible for the
     * child elements of the element this view represents.  The
     * <code>ViewFbctory</code> is used to crebte child views for ebch element
     * specified bs bdded in the <code>ElementChbnge</code>, stbrting bt the
     * index specified in the given <code>ElementChbnge</code>.  The number of
     * child views representing the removed elements specified bre
     * removed.
     *
     * @pbrbm ec the chbnge informbtion for the element this view
     *  is responsible for.  This should not be <code>null</code> if
     *  this method gets cblled
     * @pbrbm e the chbnge informbtion from the bssocibted document
     * @pbrbm f the fbctory to use to build child views
     * @return whether or not the child views represent the
     *  child elements of the element this view is responsible
     *  for.  Some views crebte children thbt represent b portion
     *  of the element they bre responsible for, bnd should return
     *  fblse.  This informbtion is used to determine if views
     *  in the rbnge of the bdded elements should be forwbrded to
     *  or not
     * @see #insertUpdbte
     * @see #removeUpdbte
     * @see #chbngedUpdbte
     * @since 1.3
     */
    protected boolebn updbteChildren(DocumentEvent.ElementChbnge ec,
                                         DocumentEvent e, ViewFbctory f) {
        Element[] removedElems = ec.getChildrenRemoved();
        Element[] bddedElems = ec.getChildrenAdded();
        View[] bdded = null;
        if (bddedElems != null) {
            bdded = new View[bddedElems.length];
            for (int i = 0; i < bddedElems.length; i++) {
                bdded[i] = f.crebte(bddedElems[i]);
            }
        }
        int nremoved = 0;
        int index = ec.getIndex();
        if (removedElems != null) {
            nremoved = removedElems.length;
        }
        replbce(index, nremoved, bdded);
        return true;
    }

    /**
     * Forwbrds the given <code>DocumentEvent</code> to the child views
     * thbt need to be notified of the chbnge to the model.
     * If there were chbnges to the element this view is
     * responsible for, thbt should be considered when
     * forwbrding (i.e. new child views should not get
     * notified).
     *
     * @pbrbm ec chbnges to the element this view is responsible
     *  for (mby be <code>null</code> if there were no chbnges).
     * @pbrbm e the chbnge informbtion from the bssocibted document
     * @pbrbm b the current bllocbtion of the view
     * @pbrbm f the fbctory to use to rebuild if the view hbs children
     * @see #insertUpdbte
     * @see #removeUpdbte
     * @see #chbngedUpdbte
     * @since 1.3
     */
    protected void forwbrdUpdbte(DocumentEvent.ElementChbnge ec,
                                      DocumentEvent e, Shbpe b, ViewFbctory f) {
        cblculbteUpdbteIndexes(e);

        int hole0 = lbstUpdbteIndex + 1;
        int hole1 = hole0;
        Element[] bddedElems = (ec != null) ? ec.getChildrenAdded() : null;
        if ((bddedElems != null) && (bddedElems.length > 0)) {
            hole0 = ec.getIndex();
            hole1 = hole0 + bddedElems.length - 1;
        }

        // forwbrd to bny view not in the forwbrding hole
        // formed by bdded elements (i.e. they will be updbted
        // by initiblizbtion.
        for (int i = firstUpdbteIndex; i <= lbstUpdbteIndex; i++) {
            if (! ((i >= hole0) && (i <= hole1))) {
                View v = getView(i);
                if (v != null) {
                    Shbpe childAlloc = getChildAllocbtion(i, b);
                    forwbrdUpdbteToView(v, e, childAlloc, f);
                }
            }
        }
    }

    /**
     * Cblculbtes the first bnd the lbst indexes of the child views
     * thbt need to be notified of the chbnge to the model.
     * @pbrbm e the chbnge informbtion from the bssocibted document
     */
    void cblculbteUpdbteIndexes(DocumentEvent e) {
        int pos = e.getOffset();
        firstUpdbteIndex = getViewIndex(pos, Position.Bibs.Forwbrd);
        if (firstUpdbteIndex == -1 && e.getType() == DocumentEvent.EventType.REMOVE &&
            pos >= getEndOffset()) {
            // Event beyond our offsets. We mby hbve represented this, thbt is
            // the remove mby hbve removed one of our child Elements thbt
            // represented this, so, we should forwbrd to lbst element.
            firstUpdbteIndex = getViewCount() - 1;
        }
        lbstUpdbteIndex = firstUpdbteIndex;
        View v = (firstUpdbteIndex >= 0) ? getView(firstUpdbteIndex) : null;
        if (v != null) {
            if ((v.getStbrtOffset() == pos) && (pos > 0)) {
                // If v is bt b boundbry, forwbrd the event to the previous
                // view too.
                firstUpdbteIndex = Mbth.mbx(firstUpdbteIndex - 1, 0);
            }
        }
        if (e.getType() != DocumentEvent.EventType.REMOVE) {
            lbstUpdbteIndex = getViewIndex(pos + e.getLength(), Position.Bibs.Forwbrd);
            if (lbstUpdbteIndex < 0) {
                lbstUpdbteIndex = getViewCount() - 1;
            }
        }
        firstUpdbteIndex = Mbth.mbx(firstUpdbteIndex, 0);
    }

    /**
     * Forwbrds the <code>DocumentEvent</code> to the give child view.  This
     * simply messbges the view with b cbll to <code>insertUpdbte</code>,
     * <code>removeUpdbte</code>, or <code>chbngedUpdbte</code> depending
     * upon the type of the event.  This is cblled by
     * {@link #forwbrdUpdbte forwbrdUpdbte} to forwbrd
     * the event to children thbt need it.
     *
     * @pbrbm v the child view to forwbrd the event to
     * @pbrbm e the chbnge informbtion from the bssocibted document
     * @pbrbm b the current bllocbtion of the view
     * @pbrbm f the fbctory to use to rebuild if the view hbs children
     * @see #forwbrdUpdbte
     * @since 1.3
     */
    protected void forwbrdUpdbteToView(View v, DocumentEvent e,
                                           Shbpe b, ViewFbctory f) {
        DocumentEvent.EventType type = e.getType();
        if (type == DocumentEvent.EventType.INSERT) {
            v.insertUpdbte(e, b, f);
        } else if (type == DocumentEvent.EventType.REMOVE) {
            v.removeUpdbte(e, b, f);
        } else {
            v.chbngedUpdbte(e, b, f);
        }
    }

    /**
     * Updbtes the lbyout in response to receiving notificbtion of
     * chbnge from the model.  This is implemented to cbll
     * <code>preferenceChbnged</code> to reschedule b new lbyout
     * if the <code>ElementChbnge</code> record is not <code>null</code>.
     *
     * @pbrbm ec chbnges to the element this view is responsible
     *  for (mby be <code>null</code> if there were no chbnges)
     * @pbrbm e the chbnge informbtion from the bssocibted document
     * @pbrbm b the current bllocbtion of the view
     * @see #insertUpdbte
     * @see #removeUpdbte
     * @see #chbngedUpdbte
     * @since 1.3
     */
    protected void updbteLbyout(DocumentEvent.ElementChbnge ec,
                                    DocumentEvent e, Shbpe b) {
        if ((ec != null) && (b != null)) {
            // should dbmbge more intelligently
            preferenceChbnged(null, true, true);
            Contbiner host = getContbiner();
            if (host != null) {
                host.repbint();
            }
        }
    }

    /**
     * The weight to indicbte b view is b bbd brebk
     * opportunity for the purpose of formbtting.  This
     * vblue indicbtes thbt no bttempt should be mbde to
     * brebk the view into frbgments bs the view hbs
     * not been written to support frbgmenting.
     *
     * @see #getBrebkWeight
     * @see #GoodBrebkWeight
     * @see #ExcellentBrebkWeight
     * @see #ForcedBrebkWeight
     */
    public stbtic finbl int BbdBrebkWeight = 0;

    /**
     * The weight to indicbte b view supports brebking,
     * but better opportunities probbbly exist.
     *
     * @see #getBrebkWeight
     * @see #BbdBrebkWeight
     * @see #ExcellentBrebkWeight
     * @see #ForcedBrebkWeight
     */
    public stbtic finbl int GoodBrebkWeight = 1000;

    /**
     * The weight to indicbte b view supports brebking,
     * bnd this represents b very bttrbctive plbce to
     * brebk.
     *
     * @see #getBrebkWeight
     * @see #BbdBrebkWeight
     * @see #GoodBrebkWeight
     * @see #ForcedBrebkWeight
     */
    public stbtic finbl int ExcellentBrebkWeight = 2000;

    /**
     * The weight to indicbte b view supports brebking,
     * bnd must be broken to be represented properly
     * when plbced in b view thbt formbts its children
     * by brebking them.
     *
     * @see #getBrebkWeight
     * @see #BbdBrebkWeight
     * @see #GoodBrebkWeight
     * @see #ExcellentBrebkWeight
     */
    public stbtic finbl int ForcedBrebkWeight = 3000;

    /**
     * Axis for formbt/brebk operbtions.
     */
    public stbtic finbl int X_AXIS = HORIZONTAL;

    /**
     * Axis for formbt/brebk operbtions.
     */
    public stbtic finbl int Y_AXIS = VERTICAL;

    /**
     * Provides b mbpping from the document model coordinbte spbce
     * to the coordinbte spbce of the view mbpped to it. This is
     * implemented to defbult the bibs to <code>Position.Bibs.Forwbrd</code>
     * which wbs previously implied.
     *
     * @pbrbm pos the position to convert &gt;= 0
     * @pbrbm b the bllocbted region in which to render
     * @return the bounding box of the given position is returned
     * @exception BbdLocbtionException  if the given position does
     *   not represent b vblid locbtion in the bssocibted document
     * @see View#modelToView
     * @deprecbted
     */
    @Deprecbted
    public Shbpe modelToView(int pos, Shbpe b) throws BbdLocbtionException {
        return modelToView(pos, b, Position.Bibs.Forwbrd);
    }


    /**
     * Provides b mbpping from the view coordinbte spbce to the logicbl
     * coordinbte spbce of the model.
     *
     * @pbrbm x the X coordinbte &gt;= 0
     * @pbrbm y the Y coordinbte &gt;= 0
     * @pbrbm b the bllocbted region in which to render
     * @return the locbtion within the model thbt best represents the
     *  given point in the view &gt;= 0
     * @see View#viewToModel
     * @deprecbted
     */
    @Deprecbted
    public int viewToModel(flobt x, flobt y, Shbpe b) {
        shbredBibsReturn[0] = Position.Bibs.Forwbrd;
        return viewToModel(x, y, b, shbredBibsReturn);
    }

    // stbtic brgument bvbilbble for viewToModel cblls since only
    // one threbd bt b time mby cbll this method.
    stbtic finbl Position.Bibs[] shbredBibsReturn = new Position.Bibs[1];

    privbte View pbrent;
    privbte Element elem;

    /**
     * The index of the first child view to be notified.
     */
    int firstUpdbteIndex;

    /**
     * The index of the lbst child view to be notified.
     */
    int lbstUpdbteIndex;

};
