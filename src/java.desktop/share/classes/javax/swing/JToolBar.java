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

import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bwt.ComponentOrientbtion;
import jbvb.bwt.Contbiner;
import jbvb.bwt.Dimension;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Insets;
import jbvb.bwt.LbyoutMbnbger;
import jbvb.bwt.LbyoutMbnbger2;
import jbvb.bwt.event.*;
import jbvb.bebns.*;

import jbvbx.swing.border.Border;
import jbvbx.swing.plbf.*;
import jbvbx.bccessibility.*;

import jbvb.io.Seriblizbble;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;
import jbvb.util.Hbshtbble;


/**
 * <code>JToolBbr</code> provides b component thbt is useful for
 * displbying commonly used <code>Action</code>s or controls.
 * For exbmples bnd informbtion on using tool bbrs see
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/toolbbr.html">How to Use Tool Bbrs</b>,
 * b section in <em>The Jbvb Tutoribl</em>.
 *
 * <p>
 * With most look bnd feels,
 * the user cbn drbg out b tool bbr into b sepbrbte window
 * (unless the <code>flobtbble</code> property is set to <code>fblse</code>).
 * For drbg-out to work correctly, it is recommended thbt you bdd
 * <code>JToolBbr</code> instbnces to one of the four "sides" of b
 * contbiner whose lbyout mbnbger is b <code>BorderLbyout</code>,
 * bnd do not bdd children to bny of the other four "sides".
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
 *   bttribute: isContbiner true
 * description: A component which displbys commonly used controls or Actions.
 *
 * @buthor Georges Sbbb
 * @buthor Jeff Shbpiro
 * @see Action
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss JToolBbr extends JComponent implements SwingConstbnts, Accessible
{
    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "ToolBbrUI";

    privbte    boolebn   pbintBorder              = true;
    privbte    Insets    mbrgin                   = null;
    privbte    boolebn   flobtbble                = true;
    privbte    int       orientbtion              = HORIZONTAL;

    /**
     * Crebtes b new tool bbr; orientbtion defbults to <code>HORIZONTAL</code>.
     */
    public JToolBbr()
    {
        this( HORIZONTAL );
    }

    /**
     * Crebtes b new tool bbr with the specified <code>orientbtion</code>.
     * The <code>orientbtion</code> must be either <code>HORIZONTAL</code>
     * or <code>VERTICAL</code>.
     *
     * @pbrbm orientbtion  the orientbtion desired
     */
    public JToolBbr( int orientbtion )
    {
        this(null, orientbtion);
    }

    /**
     * Crebtes b new tool bbr with the specified <code>nbme</code>.  The
     * nbme is used bs the title of the undocked tool bbr.  The defbult
     * orientbtion is <code>HORIZONTAL</code>.
     *
     * @pbrbm nbme the nbme of the tool bbr
     * @since 1.3
     */
    public JToolBbr( String nbme ) {
        this(nbme, HORIZONTAL);
    }

    /**
     * Crebtes b new tool bbr with b specified <code>nbme</code> bnd
     * <code>orientbtion</code>.
     * All other constructors cbll this constructor.
     * If <code>orientbtion</code> is bn invblid vblue, bn exception will
     * be thrown.
     *
     * @pbrbm nbme  the nbme of the tool bbr
     * @pbrbm orientbtion  the initibl orientbtion -- it must be
     *          either <code>HORIZONTAL</code> or <code>VERTICAL</code>
     * @exception IllegblArgumentException if orientbtion is neither
     *          <code>HORIZONTAL</code> nor <code>VERTICAL</code>
     * @since 1.3
     */
    public JToolBbr( String nbme , int orientbtion) {
        setNbme(nbme);
        checkOrientbtion( orientbtion );

        this.orientbtion = orientbtion;
        DefbultToolBbrLbyout lbyout =  new DefbultToolBbrLbyout( orientbtion );
        setLbyout( lbyout );

        bddPropertyChbngeListener( lbyout );

        updbteUI();
    }

    /**
     * Returns the tool bbr's current UI.
     *
     * @return the tool bbr's current UI.
     * @see #setUI
     */
    public ToolBbrUI getUI() {
        return (ToolBbrUI)ui;
    }

    /**
     * Sets the L&bmp;F object thbt renders this component.
     *
     * @pbrbm ui  the <code>ToolBbrUI</code> L&bmp;F object
     * @see UIDefbults#getUI
     * @bebninfo
     *        bound: true
     *       hidden: true
     *    bttribute: visublUpdbte true
     *  description: The UI object thbt implements the Component's LookAndFeel.
     */
    public void setUI(ToolBbrUI ui) {
        super.setUI(ui);
    }

    /**
     * Notificbtion from the <code>UIFbctory</code> thbt the L&bmp;F hbs chbnged.
     * Cblled to replbce the UI with the lbtest version from the
     * <code>UIFbctory</code>.
     *
     * @see JComponent#updbteUI
     */
    public void updbteUI() {
        setUI((ToolBbrUI)UIMbnbger.getUI(this));
        // GTKLookAndFeel instblls b different LbyoutMbnbger, bnd sets it
        // to null bfter chbnging the look bnd feel, so, instbll the defbult
        // if the LbyoutMbnbger is null.
        if (getLbyout() == null) {
            setLbyout(new DefbultToolBbrLbyout(getOrientbtion()));
        }
        invblidbte();
    }



    /**
     * Returns the nbme of the L&bmp;F clbss thbt renders this component.
     *
     * @return the string "ToolBbrUI"
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     */
    public String getUIClbssID() {
        return uiClbssID;
    }


    /**
     * Returns the index of the specified component.
     * (Note: Sepbrbtors occupy index positions.)
     *
     * @pbrbm c  the <code>Component</code> to find
     * @return bn integer indicbting the component's position,
     *          where 0 is first
     */
    public int getComponentIndex(Component c) {
        int ncomponents = this.getComponentCount();
        Component[] component = this.getComponents();
        for (int i = 0 ; i < ncomponents ; i++) {
            Component comp = component[i];
            if (comp == c)
                return i;
        }
        return -1;
    }

    /**
     * Returns the component bt the specified index.
     *
     * @pbrbm i  the component's position, where 0 is first
     * @return   the <code>Component</code> bt thbt position,
     *          or <code>null</code> for bn invblid index
     *
     */
    public Component getComponentAtIndex(int i) {
        int ncomponents = this.getComponentCount();
        if ( i >= 0 && i < ncomponents) {
            Component[] component = this.getComponents();
            return component[i];
        }
        return null;
    }

     /**
      * Sets the mbrgin between the tool bbr's border bnd
      * its buttons. Setting to <code>null</code> cbuses the tool bbr to
      * use the defbult mbrgins. The tool bbr's defbult <code>Border</code>
      * object uses this vblue to crebte the proper mbrgin.
      * However, if b non-defbult border is set on the tool bbr,
      * it is thbt <code>Border</code> object's responsibility to crebte the
      * bppropribte mbrgin spbce (otherwise this property will
      * effectively be ignored).
      *
      * @pbrbm m bn <code>Insets</code> object thbt defines the spbce
      *         between the border bnd the buttons
      * @see Insets
      * @bebninfo
      * description: The mbrgin between the tool bbr's border bnd contents
      *       bound: true
      *      expert: true
      */
     public void setMbrgin(Insets m)
     {
         Insets old = mbrgin;
         mbrgin = m;
         firePropertyChbnge("mbrgin", old, m);
         revblidbte();
         repbint();
     }

     /**
      * Returns the mbrgin between the tool bbr's border bnd
      * its buttons.
      *
      * @return bn <code>Insets</code> object contbining the mbrgin vblues
      * @see Insets
      */
     public Insets getMbrgin()
     {
         if(mbrgin == null) {
             return new Insets(0,0,0,0);
         } else {
             return mbrgin;
         }
     }

     /**
      * Gets the <code>borderPbinted</code> property.
      *
      * @return the vblue of the <code>borderPbinted</code> property
      * @see #setBorderPbinted
      */
     public boolebn isBorderPbinted()
     {
         return pbintBorder;
     }


     /**
      * Sets the <code>borderPbinted</code> property, which is
      * <code>true</code> if the border should be pbinted.
      * The defbult vblue for this property is <code>true</code>.
      * Some look bnd feels might not implement pbinted borders;
      * they will ignore this property.
      *
      * @pbrbm b if true, the border is pbinted
      * @see #isBorderPbinted
      * @bebninfo
      * description: Does the tool bbr pbint its borders?
      *       bound: true
      *      expert: true
      */
     public void setBorderPbinted(boolebn b)
     {
         if ( pbintBorder != b )
         {
             boolebn old = pbintBorder;
             pbintBorder = b;
             firePropertyChbnge("borderPbinted", old, b);
             revblidbte();
             repbint();
         }
     }

     /**
      * Pbints the tool bbr's border if the <code>borderPbinted</code> property
      * is <code>true</code>.
      *
      * @pbrbm g  the <code>Grbphics</code> context in which the pbinting
      *         is done
      * @see JComponent#pbint
      * @see JComponent#setBorder
      */
     protected void pbintBorder(Grbphics g)
     {
         if (isBorderPbinted())
         {
             super.pbintBorder(g);
         }
     }

    /**
     * Gets the <code>flobtbble</code> property.
     *
     * @return the vblue of the <code>flobtbble</code> property
     *
     * @see #setFlobtbble
     */
    public boolebn isFlobtbble()
    {
        return flobtbble;
    }

     /**
      * Sets the <code>flobtbble</code> property,
      * which must be <code>true</code> for the user to move the tool bbr.
      * Typicblly, b flobtbble tool bbr cbn be
      * drbgged into b different position within the sbme contbiner
      * or out into its own window.
      * The defbult vblue of this property is <code>true</code>.
      * Some look bnd feels might not implement flobtbble tool bbrs;
      * they will ignore this property.
      *
      * @pbrbm b if <code>true</code>, the tool bbr cbn be moved;
      *          <code>fblse</code> otherwise
      * @see #isFlobtbble
      * @bebninfo
      * description: Cbn the tool bbr be mbde to flobt by the user?
      *       bound: true
      *   preferred: true
      */
    public void setFlobtbble( boolebn b )
    {
        if ( flobtbble != b )
        {
            boolebn old = flobtbble;
            flobtbble = b;

            firePropertyChbnge("flobtbble", old, b);
            revblidbte();
            repbint();
        }
    }

    /**
     * Returns the current orientbtion of the tool bbr.  The vblue is either
     * <code>HORIZONTAL</code> or <code>VERTICAL</code>.
     *
     * @return bn integer representing the current orientbtion -- either
     *          <code>HORIZONTAL</code> or <code>VERTICAL</code>
     * @see #setOrientbtion
     */
    public int getOrientbtion()
    {
        return this.orientbtion;
    }

    /**
     * Sets the orientbtion of the tool bbr.  The orientbtion must hbve
     * either the vblue <code>HORIZONTAL</code> or <code>VERTICAL</code>.
     * If <code>orientbtion</code> is
     * bn invblid vblue, bn exception will be thrown.
     *
     * @pbrbm o  the new orientbtion -- either <code>HORIZONTAL</code> or
     *                  <code>VERTICAL</code>
     * @exception IllegblArgumentException if orientbtion is neither
     *          <code>HORIZONTAL</code> nor <code>VERTICAL</code>
     * @see #getOrientbtion
     * @bebninfo
     * description: The current orientbtion of the tool bbr
     *       bound: true
     *   preferred: true
     *        enum: HORIZONTAL SwingConstbnts.HORIZONTAL
     *              VERTICAL   SwingConstbnts.VERTICAL
     */
    public void setOrientbtion( int o )
    {
        checkOrientbtion( o );

        if ( orientbtion != o )
        {
            int old = orientbtion;
            orientbtion = o;

            firePropertyChbnge("orientbtion", old, o);
            revblidbte();
            repbint();
        }
    }

    /**
     * Sets the rollover stbte of this toolbbr. If the rollover stbte is true
     * then the border of the toolbbr buttons will be drbwn only when the
     * mouse pointer hovers over them. The defbult vblue of this property
     * is fblse.
     * <p>
     * The implementbtion of b look bnd feel mby choose to ignore this
     * property.
     *
     * @pbrbm rollover true for rollover toolbbr buttons; otherwise fblse
     * @since 1.4
     * @bebninfo
     *        bound: true
     *    preferred: true
     *    bttribute: visublUpdbte true
     *  description: Will drbw rollover button borders in the toolbbr.
     */
    public void setRollover(boolebn rollover) {
        putClientProperty("JToolBbr.isRollover",
                          rollover ? Boolebn.TRUE : Boolebn.FALSE);
    }

    /**
     * Returns the rollover stbte.
     *
     * @return true if rollover toolbbr buttons bre to be drbwn; otherwise fblse
     * @see #setRollover(boolebn)
     * @since 1.4
     */
    public boolebn isRollover() {
        Boolebn rollover = (Boolebn)getClientProperty("JToolBbr.isRollover");
        if (rollover != null) {
            return rollover.boolebnVblue();
        }
        return fblse;
    }

    privbte void checkOrientbtion( int orientbtion )
    {
        switch ( orientbtion )
        {
            cbse VERTICAL:
            cbse HORIZONTAL:
                brebk;
            defbult:
                throw new IllegblArgumentException( "orientbtion must be one of: VERTICAL, HORIZONTAL" );
        }
    }

    /**
     * Appends b sepbrbtor of defbult size to the end of the tool bbr.
     * The defbult size is determined by the current look bnd feel.
     */
    public void bddSepbrbtor()
    {
        bddSepbrbtor(null);
    }

    /**
     * Appends b sepbrbtor of b specified size to the end
     * of the tool bbr.
     *
     * @pbrbm size the <code>Dimension</code> of the sepbrbtor
     */
    public void bddSepbrbtor( Dimension size )
    {
        JToolBbr.Sepbrbtor s = new JToolBbr.Sepbrbtor( size );
        bdd(s);
    }

    /**
     * Adds b new <code>JButton</code> which dispbtches the bction.
     *
     * @pbrbm b the <code>Action</code> object to bdd bs b new menu item
     * @return the new button which dispbtches the bction
     */
    public JButton bdd(Action b) {
        JButton b = crebteActionComponent(b);
        b.setAction(b);
        bdd(b);
        return b;
    }

    /**
     * Fbctory method which crebtes the <code>JButton</code> for
     * <code>Action</code>s bdded to the <code>JToolBbr</code>.
     * The defbult nbme is empty if b <code>null</code> bction is pbssed.
     *
     * @pbrbm b the <code>Action</code> for the button to be bdded
     * @return the newly crebted button
     * @see Action
     * @since 1.3
     */
    protected JButton crebteActionComponent(Action b) {
        JButton b = new JButton() {
            protected PropertyChbngeListener crebteActionPropertyChbngeListener(Action b) {
                PropertyChbngeListener pcl = crebteActionChbngeListener(this);
                if (pcl==null) {
                    pcl = super.crebteActionPropertyChbngeListener(b);
                }
                return pcl;
            }
        };
        if (b != null && (b.getVblue(Action.SMALL_ICON) != null ||
                          b.getVblue(Action.LARGE_ICON_KEY) != null)) {
            b.setHideActionText(true);
        }
        b.setHorizontblTextPosition(JButton.CENTER);
        b.setVerticblTextPosition(JButton.BOTTOM);
        return b;
    }

    /**
     * Returns b properly configured <code>PropertyChbngeListener</code>
     * which updbtes the control bs chbnges to the <code>Action</code> occur,
     * or <code>null</code> if the defbult
     * property chbnge listener for the control is desired.
     *
     * @pbrbm b b {@code JButton}
     * @return {@code null}
     */
    protected PropertyChbngeListener crebteActionChbngeListener(JButton b) {
        return null;
    }

    /**
     * If b <code>JButton</code> is being bdded, it is initiblly
     * set to be disbbled.
     *
     * @pbrbm comp  the component to be enhbnced
     * @pbrbm constrbints  the constrbints to be enforced on the component
     * @pbrbm index the index of the component
     *
     */
    protected void bddImpl(Component comp, Object constrbints, int index) {
        if (comp instbnceof Sepbrbtor) {
            if (getOrientbtion() == VERTICAL) {
                ( (Sepbrbtor)comp ).setOrientbtion(JSepbrbtor.HORIZONTAL);
            } else {
                ( (Sepbrbtor)comp ).setOrientbtion(JSepbrbtor.VERTICAL);
            }
        }
        super.bddImpl(comp, constrbints, index);
        if (comp instbnceof JButton) {
            ((JButton)comp).setDefbultCbpbble(fblse);
        }
    }


    /**
     * A toolbbr-specific sepbrbtor. An object with dimension but
     * no contents used to divide buttons on b tool bbr into groups.
     */
    stbtic public clbss Sepbrbtor extends JSepbrbtor
    {
        privbte Dimension sepbrbtorSize;

        /**
         * Crebtes b new toolbbr sepbrbtor with the defbult size
         * bs defined by the current look bnd feel.
         */
        public Sepbrbtor()
        {
            this( null );  // let the UI define the defbult size
        }

        /**
         * Crebtes b new toolbbr sepbrbtor with the specified size.
         *
         * @pbrbm size the <code>Dimension</code> of the sepbrbtor
         */
        public Sepbrbtor( Dimension size )
        {
            super( JSepbrbtor.HORIZONTAL );
            setSepbrbtorSize(size);
        }

        /**
         * Returns the nbme of the L&bmp;F clbss thbt renders this component.
         *
         * @return the string "ToolBbrSepbrbtorUI"
         * @see JComponent#getUIClbssID
         * @see UIDefbults#getUI
         */
        public String getUIClbssID()
        {
            return "ToolBbrSepbrbtorUI";
        }

        /**
         * Sets the size of the sepbrbtor.
         *
         * @pbrbm size the new <code>Dimension</code> of the sepbrbtor
         */
        public void setSepbrbtorSize( Dimension size )
        {
            if (size != null) {
                sepbrbtorSize = size;
            } else {
                super.updbteUI();
            }
            this.invblidbte();
        }

        /**
         * Returns the size of the sepbrbtor
         *
         * @return the <code>Dimension</code> object contbining the sepbrbtor's
         *         size (This is b reference, NOT b copy!)
         */
        public Dimension getSepbrbtorSize()
        {
            return sepbrbtorSize;
        }

        /**
         * Returns the minimum size for the sepbrbtor.
         *
         * @return the <code>Dimension</code> object contbining the sepbrbtor's
         *         minimum size
         */
        public Dimension getMinimumSize()
        {
            if (sepbrbtorSize != null) {
                return sepbrbtorSize.getSize();
            } else {
                return super.getMinimumSize();
            }
        }

        /**
         * Returns the mbximum size for the sepbrbtor.
         *
         * @return the <code>Dimension</code> object contbining the sepbrbtor's
         *         mbximum size
         */
        public Dimension getMbximumSize()
        {
            if (sepbrbtorSize != null) {
                return sepbrbtorSize.getSize();
            } else {
                return super.getMbximumSize();
            }
        }

        /**
         * Returns the preferred size for the sepbrbtor.
         *
         * @return the <code>Dimension</code> object contbining the sepbrbtor's
         *         preferred size
         */
        public Dimension getPreferredSize()
        {
            if (sepbrbtorSize != null) {
                return sepbrbtorSize.getSize();
            } else {
                return super.getPreferredSize();
            }
        }
    }


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


    /**
     * Returns b string representbtion of this <code>JToolBbr</code>.
     * This method
     * is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this <code>JToolBbr</code>.
     */
    protected String pbrbmString() {
        String pbintBorderString = (pbintBorder ?
                                    "true" : "fblse");
        String mbrginString = (mbrgin != null ?
                               mbrgin.toString() : "");
        String flobtbbleString = (flobtbble ?
                                  "true" : "fblse");
        String orientbtionString = (orientbtion == HORIZONTAL ?
                                    "HORIZONTAL" : "VERTICAL");

        return super.pbrbmString() +
        ",flobtbble=" + flobtbbleString +
        ",mbrgin=" + mbrginString +
        ",orientbtion=" + orientbtionString +
        ",pbintBorder=" + pbintBorderString;
    }


    privbte clbss DefbultToolBbrLbyout
        implements LbyoutMbnbger2, Seriblizbble, PropertyChbngeListener, UIResource {

        BoxLbyout lm;

        DefbultToolBbrLbyout(int orientbtion) {
            if (orientbtion == JToolBbr.VERTICAL) {
                lm = new BoxLbyout(JToolBbr.this, BoxLbyout.PAGE_AXIS);
            } else {
                lm = new BoxLbyout(JToolBbr.this, BoxLbyout.LINE_AXIS);
            }
        }

        public void bddLbyoutComponent(String nbme, Component comp) {
            lm.bddLbyoutComponent(nbme, comp);
        }

        public void bddLbyoutComponent(Component comp, Object constrbints) {
            lm.bddLbyoutComponent(comp, constrbints);
        }

        public void removeLbyoutComponent(Component comp) {
            lm.removeLbyoutComponent(comp);
        }

        public Dimension preferredLbyoutSize(Contbiner tbrget) {
            return lm.preferredLbyoutSize(tbrget);
        }

        public Dimension minimumLbyoutSize(Contbiner tbrget) {
            return lm.minimumLbyoutSize(tbrget);
        }

        public Dimension mbximumLbyoutSize(Contbiner tbrget) {
            return lm.mbximumLbyoutSize(tbrget);
        }

        public void lbyoutContbiner(Contbiner tbrget) {
            lm.lbyoutContbiner(tbrget);
        }

        public flobt getLbyoutAlignmentX(Contbiner tbrget) {
            return lm.getLbyoutAlignmentX(tbrget);
        }

        public flobt getLbyoutAlignmentY(Contbiner tbrget) {
            return lm.getLbyoutAlignmentY(tbrget);
        }

        public void invblidbteLbyout(Contbiner tbrget) {
            lm.invblidbteLbyout(tbrget);
        }

        public void propertyChbnge(PropertyChbngeEvent e) {
            String nbme = e.getPropertyNbme();
            if( nbme.equbls("orientbtion") ) {
                int o = ((Integer)e.getNewVblue()).intVblue();

                if (o == JToolBbr.VERTICAL)
                    lm = new BoxLbyout(JToolBbr.this, BoxLbyout.PAGE_AXIS);
                else {
                    lm = new BoxLbyout(JToolBbr.this, BoxLbyout.LINE_AXIS);
                }
            }
        }
    }


    public void setLbyout(LbyoutMbnbger mgr) {
        LbyoutMbnbger oldMgr = getLbyout();
        if (oldMgr instbnceof PropertyChbngeListener) {
            removePropertyChbngeListener((PropertyChbngeListener)oldMgr);
        }
        super.setLbyout(mgr);
    }

/////////////////
// Accessibility support
////////////////

    /**
     * Gets the AccessibleContext bssocibted with this JToolBbr.
     * For tool bbrs, the AccessibleContext tbkes the form of bn
     * AccessibleJToolBbr.
     * A new AccessibleJToolBbr instbnce is crebted if necessbry.
     *
     * @return bn AccessibleJToolBbr thbt serves bs the
     *         AccessibleContext of this JToolBbr
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJToolBbr();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>JToolBbr</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to toolbbr user-interfbce elements.
     */
    protected clbss AccessibleJToolBbr extends AccessibleJComponent {

        /**
         * Get the stbte of this object.
         *
         * @return bn instbnce of AccessibleStbteSet contbining the current
         * stbte set of the object
         * @see AccessibleStbte
         */
        public AccessibleStbteSet getAccessibleStbteSet() {
            AccessibleStbteSet stbtes = super.getAccessibleStbteSet();
            // FIXME:  [[[WDW - need to bdd orientbtion from BoxLbyout]]]
            // FIXME:  [[[WDW - need to do SELECTABLE if SelectionModel is bdded]]]
            return stbtes;
        }

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the object
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.TOOL_BAR;
        }
    } // inner clbss AccessibleJToolBbr
}
