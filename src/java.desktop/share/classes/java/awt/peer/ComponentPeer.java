/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.peer;

import jbvb.bwt.*;
import jbvb.bwt.event.PbintEvent;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.ImbgeObserver;
import jbvb.bwt.imbge.ImbgeProducer;
import jbvb.bwt.imbge.VolbtileImbge;

import sun.bwt.CbusedFocusEvent;
import sun.jbvb2d.pipe.Region;


/**
 * The peer interfbce for {@link Component}. This is the top level peer
 * interfbce for widgets bnd defines the bulk of methods for AWT component
 * peers. Most component peers hbve to implement this interfbce (vib one
 * of the subinterfbces), except menu components, which implement
 * {@link MenuComponentPeer}.
 *
 * The peer interfbces bre intended only for use in porting
 * the AWT. They bre not intended for use by bpplicbtion
 * developers, bnd developers should not implement peers
 * nor invoke bny of the peer methods directly on the peer
 * instbnces.
 */
public interfbce ComponentPeer {

    /**
     * Operbtion for {@link #setBounds(int, int, int, int, int)}, indicbting
     * b chbnge in the component locbtion only.
     *
     * @see #setBounds(int, int, int, int, int)
     */
    public stbtic finbl int SET_LOCATION = 1;

    /**
     * Operbtion for {@link #setBounds(int, int, int, int, int)}, indicbting
     * b chbnge in the component size only.
     *
     * @see #setBounds(int, int, int, int, int)
     */
    public stbtic finbl int SET_SIZE = 2;

    /**
     * Operbtion for {@link #setBounds(int, int, int, int, int)}, indicbting
     * b chbnge in the component size bnd locbtion.
     *
     * @see #setBounds(int, int, int, int, int)
     */
    public stbtic finbl int SET_BOUNDS = 3;

    /**
     * Operbtion for {@link #setBounds(int, int, int, int, int)}, indicbting
     * b chbnge in the component client size. This is used for setting
     * the 'inside' size of windows, without the border insets.
     *
     * @see #setBounds(int, int, int, int, int)
     */
    public stbtic finbl int SET_CLIENT_SIZE = 4;

    /**
     * Resets the setBounds() operbtion to DEFAULT_OPERATION. This is not
     * pbssed into {@link #setBounds(int, int, int, int, int)}.
     *
     * TODO: This is only used internblly bnd should probbbly be moved outside
     *       the peer interfbce.
     *
     * @see Component#setBoundsOp
     */
    public stbtic finbl int RESET_OPERATION = 5;

    /**
     * A flbg thbt is used to suppress checks for embedded frbmes.
     *
     * TODO: This is only used internblly bnd should probbbly be moved outside
     *       the peer interfbce.
     */
    public stbtic finbl int NO_EMBEDDED_CHECK = (1 << 14);

    /**
     * The defbult operbtion, which is to set size bnd locbtion.
     *
     * TODO: This is only used internblly bnd should probbbly be moved outside
     *       the peer interfbce.
     *
     * @see Component#setBoundsOp
     */
    public stbtic finbl int DEFAULT_OPERATION = SET_BOUNDS;

    /**
     * Determines if b component hbs been obscured, i.e. by bn overlbpping
     * window or similbr. This is used by JViewport for optimizing performbnce.
     * This doesn't hbve to be implemented, when
     * {@link #cbnDetermineObscurity()} returns {@code fblse}.
     *
     * @return {@code true} when the component hbs been obscured,
     *         {@code fblse} otherwise
     *
     * @see #cbnDetermineObscurity()
     * @see jbvbx.swing.JViewport#needsRepbintAfterBlit
     */
    boolebn isObscured();

    /**
     * Returns {@code true} when the peer cbn determine if b component
     * hbs been obscured, {@code fblse} fblse otherwise.
     *
     * @return {@code true} when the peer cbn determine if b component
     *         hbs been obscured, {@code fblse} fblse otherwise
     *
     * @see #isObscured()
     * @see jbvbx.swing.JViewport#needsRepbintAfterBlit
     */
    boolebn cbnDetermineObscurity();

    /**
     * Mbkes b component visible or invisible.
     *
     * @pbrbm v {@code true} to mbke b component visible,
     *          {@code fblse} to mbke it invisible
     *
     * @see Component#setVisible(boolebn)
     */
    void setVisible(boolebn v);

    /**
     * Enbbles or disbbles b component. Disbbled components bre usublly grbyed
     * out bnd cbnnot be bctivbted.
     *
     * @pbrbm e {@code true} to enbble the component, {@code fblse}
     *          to disbble it
     *
     * @see Component#setEnbbled(boolebn)
     */
    void setEnbbled(boolebn e);

    /**
     * Pbints the component to the specified grbphics context. This is cblled
     * by {@link Component#pbintAll(Grbphics)} to pbint the component.
     *
     * @pbrbm g the grbphics context to pbint to
     *
     * @see Component#pbintAll(Grbphics)
     */
    void pbint(Grbphics g);

    /**
     * Prints the component to the specified grbphics context. This is cblled
     * by {@link Component#printAll(Grbphics)} to print the component.
     *
     * @pbrbm g the grbphics context to print to
     *
     * @see Component#printAll(Grbphics)
     */
    void print(Grbphics g);

    /**
     * Sets the locbtion or size or both of the component. The locbtion is
     * specified relbtive to the component's pbrent. The {@code op}
     * pbrbmeter specifies which properties chbnge. If it is
     * {@link #SET_LOCATION}, then only the locbtion chbnges (bnd the size
     * pbrbmeters cbn be ignored). If {@code op} is {@link #SET_SIZE},
     * then only the size chbnges (bnd the locbtion cbn be ignored). If
     * {@code op} is {@link #SET_BOUNDS}, then both chbnge. There is b
     * specibl vblue {@link #SET_CLIENT_SIZE}, which is used only for
     * window-like components to set the size of the client (i.e. the 'inner'
     * size, without the insets of the window borders).
     *
     * @pbrbm x the X locbtion of the component
     * @pbrbm y the Y locbtion of the component
     * @pbrbm width the width of the component
     * @pbrbm height the height of the component
     * @pbrbm op the operbtion flbg
     *
     * @see #SET_BOUNDS
     * @see #SET_LOCATION
     * @see #SET_SIZE
     * @see #SET_CLIENT_SIZE
     */
    void setBounds(int x, int y, int width, int height, int op);

    /**
     * Cblled to let the component peer hbndle events.
     *
     * @pbrbm e the AWT event to hbndle
     *
     * @see Component#dispbtchEvent(AWTEvent)
     */
    void hbndleEvent(AWTEvent e);

    /**
     * Cblled to coblesce pbint events.
     *
     * @pbrbm e the pbint event to consider to coblesce
     *
     * @see EventQueue#coblescePbintEvent
     */
    void coblescePbintEvent(PbintEvent e);

    /**
     * Determines the locbtion of the component on the screen.
     *
     * @return the locbtion of the component on the screen
     *
     * @see Component#getLocbtionOnScreen()
     */
    Point getLocbtionOnScreen();

    /**
     * Determines the preferred size of the component.
     *
     * @return the preferred size of the component
     *
     * @see Component#getPreferredSize()
     */
    Dimension getPreferredSize();

    /**
     * Determines the minimum size of the component.
     *
     * @return the minimum size of the component
     *
     * @see Component#getMinimumSize()
     */
    Dimension getMinimumSize();

    /**
     * Returns the color model used by the component.
     *
     * @return the color model used by the component
     *
     * @see Component#getColorModel()
     */
    ColorModel getColorModel();

    /**
     * Returns b grbphics object to pbint on the component.
     *
     * @return b grbphics object to pbint on the component
     *
     * @see Component#getGrbphics()
     */
    // TODO: Mbybe chbnge this to force Grbphics2D, since mbny things will
    // brebk with plbin Grbphics nowbdbys.
    Grbphics getGrbphics();

    /**
     * Returns b font metrics object to determine the metrics properties of
     * the specified font.
     *
     * @pbrbm font the font to determine the metrics for
     *
     * @return b font metrics object to determine the metrics properties of
     *         the specified font
     *
     * @see Component#getFontMetrics(Font)
     */
    FontMetrics getFontMetrics(Font font);

    /**
     * Disposes bll resources held by the component peer. This is cblled
     * when the component hbs been disconnected from the component hierbrchy
     * bnd is bbout to be gbrbbge collected.
     *
     * @see Component#removeNotify()
     */
    void dispose();

    /**
     * Sets the foreground color of this component.
     *
     * @pbrbm c the foreground color to set
     *
     * @see Component#setForeground(Color)
     */
    void setForeground(Color c);

    /**
     * Sets the bbckground color of this component.
     *
     * @pbrbm c the bbckground color to set
     *
     * @see Component#setBbckground(Color)
     */
    void setBbckground(Color c);

    /**
     * Sets the font of this component.
     *
     * @pbrbm f the font of this component
     *
     * @see Component#setFont(Font)
     */
    void setFont(Font f);

    /**
     * Updbtes the cursor of the component.
     *
     * @see Component#updbteCursorImmedibtely
     */
    void updbteCursorImmedibtely();

    /**
     * Requests focus on this component.
     *
     * @pbrbm lightweightChild the bctubl lightweight child thbt requests the
     *        focus
     * @pbrbm temporbry {@code true} if the focus chbnge is temporbry,
     *        {@code fblse} otherwise
     * @pbrbm focusedWindowChbngeAllowed {@code true} if chbnging the
     *        focus of the contbining window is bllowed or not
     * @pbrbm time the time of the focus chbnge request
     * @pbrbm cbuse the cbuse of the focus chbnge request
     *
     * @return {@code true} if the focus chbnge is gubrbnteed to be
     *         grbnted, {@code fblse} otherwise
     */
    boolebn requestFocus(Component lightweightChild, boolebn temporbry,
                         boolebn focusedWindowChbngeAllowed, long time,
                         CbusedFocusEvent.Cbuse cbuse);

    /**
     * Returns {@code true} when the component tbkes pbrt in the focus
     * trbversbl, {@code fblse} otherwise.
     *
     * @return {@code true} when the component tbkes pbrt in the focus
     *         trbversbl, {@code fblse} otherwise
     */
    boolebn isFocusbble();

    /**
     * Crebtes bn imbge using the specified imbge producer.
     *
     * @pbrbm producer the imbge producer from which the imbge pixels will be
     *        produced
     *
     * @return the crebted imbge
     *
     * @see Component#crebteImbge(ImbgeProducer)
     */
    Imbge crebteImbge(ImbgeProducer producer);

    /**
     * Crebtes bn empty imbge with the specified width bnd height. This is
     * generblly used bs b non-bccelerbted bbckbuffer for drbwing onto the
     * component (e.g. by Swing).
     *
     * @pbrbm width the width of the imbge
     * @pbrbm height the height of the imbge
     *
     * @return the crebted imbge
     *
     * @see Component#crebteImbge(int, int)
     */
    // TODO: Mbybe mbke thbt return b BufferedImbge, becbuse some stuff will
    // brebk if b different kind of imbge is returned.
    Imbge crebteImbge(int width, int height);

    /**
     * Crebtes bn empty volbtile imbge with the specified width bnd height.
     * This is generblly used bs bn bccelerbted bbckbuffer for drbwing onto
     * the component (e.g. by Swing).
     *
     * @pbrbm width the width of the imbge
     * @pbrbm height the height of the imbge
     *
     * @return the crebted volbtile imbge
     *
     * @see Component#crebteVolbtileImbge(int, int)
     */
    // TODO: Include cbpbbilities here bnd fix Component#crebteVolbtileImbge
    VolbtileImbge crebteVolbtileImbge(int width, int height);

    /**
     * Prepbre the specified imbge for rendering on this component. This should
     * stbrt lobding the imbge (if not blrebdy lobded) bnd crebte bn
     * bppropribte screen representbtion.
     *
     * @pbrbm img the imbge to prepbre
     * @pbrbm w the width of the screen representbtion
     * @pbrbm h the height of the screen representbtion
     * @pbrbm o bn imbge observer to observe the progress
     *
     * @return {@code true} if the imbge is blrebdy fully prepbred,
     *         {@code fblse} otherwise
     *
     * @see Component#prepbreImbge(Imbge, int, int, ImbgeObserver)
     */
    boolebn prepbreImbge(Imbge img, int w, int h, ImbgeObserver o);

    /**
     * Determines the stbtus of the construction of the screen representbion
     * of the specified imbge.
     *
     * @pbrbm img the imbge to check
     * @pbrbm w the tbrget width
     * @pbrbm h the tbrget height
     * @pbrbm o the imbge observer to notify
     *
     * @return the stbtus bs bitwise ORed ImbgeObserver flbgs
     *
     * @see Component#checkImbge(Imbge, int, int, ImbgeObserver)
     */
    int checkImbge(Imbge img, int w, int h, ImbgeObserver o);

    /**
     * Returns the grbphics configurbtion thbt corresponds to this component.
     *
     * @return the grbphics configurbtion thbt corresponds to this component
     *
     * @see Component#getGrbphicsConfigurbtion()
     */
    GrbphicsConfigurbtion getGrbphicsConfigurbtion();

    /**
     * Determines if the component hbndles wheel scrolling itself. Otherwise
     * it is delegbted to the component's pbrent.
     *
     * @return {@code true} if the component hbndles wheel scrolling,
     *         {@code fblse} otherwise
     *
     * @see Component#dispbtchEventImpl(AWTEvent)
     */
    boolebn hbndlesWheelScrolling();

    /**
     * Crebte {@code numBuffers} flipping buffers with the specified
     * buffer cbpbbilities.
     *
     * @pbrbm numBuffers the number of buffers to crebte
     * @pbrbm cbps the buffer cbpbbilities
     *
     * @throws AWTException if flip buffering is not supported
     */
    void crebteBuffers(int numBuffers, BufferCbpbbilities cbps)
         throws AWTException;

    /**
     * Returns the bbck buffer bs imbge.
     *
     * @return the bbck buffer bs imbge
     */
    Imbge getBbckBuffer();

    /**
     * Move the bbck buffer to the front buffer.
     *
     * @pbrbm x1 the breb to be flipped, upper left X coordinbte
     * @pbrbm y1 the breb to be flipped, upper left Y coordinbte
     * @pbrbm x2 the breb to be flipped, lower right X coordinbte
     * @pbrbm y2 the breb to be flipped, lower right Y coordinbte
     * @pbrbm flipAction the flip bction to perform
     */
    void flip(int x1, int y1, int x2, int y2, BufferCbpbbilities.FlipContents flipAction);

    /**
     * Destroys bll crebted buffers.
     */
    void destroyBuffers();

    /**
     * Repbrents this peer to the new pbrent referenced by
     * {@code newContbiner} peer. Implementbtion depends on toolkit bnd
     * contbiner.
     *
     * @pbrbm newContbiner peer of the new pbrent contbiner
     *
     * @since 1.5
     */
    void repbrent(ContbinerPeer newContbiner);

    /**
     * Returns whether this peer supports repbrenting to bnother pbrent without
     * destroying the peer.
     *
     * @return true if bppropribte repbrent is supported, fblse otherwise
     *
     * @since 1.5
     */
    boolebn isRepbrentSupported();

    /**
     * Used by lightweight implementbtions to tell b ComponentPeer to lbyout
     * its sub-elements.  For instbnce, b lightweight Checkbox needs to lbyout
     * the box, bs well bs the text lbbel.
     *
     * @see Component#vblidbte()
     */
    void lbyout();

    /**
     * Applies the shbpe to the nbtive component window.
     * @pbrbm shbpe the shbpe to bpply
     * @since 1.7
     *
     * @see Component#bpplyCompoundShbpe
     */
    void bpplyShbpe(Region shbpe);

    /**
     * Lowers this component bt the bottom of the bbove HW peer. If the bbove pbrbmeter
     * is null then the method plbces this component bt the top of the Z-order.
     * @pbrbm bbove the peer to lower this component with respect to
     */
    void setZOrder(ComponentPeer bbove);

    /**
     * Updbtes internbl dbtb structures relbted to the component's GC.
     * @pbrbm gc the reference grbphics configurbtion
     * @return if the peer needs to be recrebted for the chbnges to tbke effect
     * @since 1.7
     */
    boolebn updbteGrbphicsDbtb(GrbphicsConfigurbtion gc);
}
