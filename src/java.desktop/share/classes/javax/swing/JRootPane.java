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
pbckbge jbvbx.swing;

import jbvb.bpplet.Applet;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.*;
import jbvb.security.AccessController;
import jbvbx.bccessibility.*;
import jbvbx.swing.plbf.RootPbneUI;
import jbvb.util.Vector;
import jbvb.io.Seriblizbble;
import jbvbx.swing.border.*;
import sun.bwt.AWTAccessor;
import sun.security.bction.GetBoolebnAction;


/**
 * A lightweight contbiner used behind the scenes by
 * <code>JFrbme</code>, <code>JDiblog</code>, <code>JWindow</code>,
 * <code>JApplet</code>, bnd <code>JInternblFrbme</code>.
 * For tbsk-oriented informbtion on functionblity provided by root pbnes
 * see <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/rootpbne.html">How to Use Root Pbnes</b>,
 * b section in <em>The Jbvb Tutoribl</em>.
 *
 * <p>
 * The following imbge shows the relbtionships between
 * the clbsses thbt use root pbnes.
 * <p style="text-blign:center"><img src="doc-files/JRootPbne-1.gif"
 * blt="The following text describes this grbphic."
 * HEIGHT=484 WIDTH=629></p>
 * The &quot;hebvyweight&quot; components (those thbt delegbte to b peer, or nbtive
 * component on the host system) bre shown with b dbrker, hebvier box. The four
 * hebvyweight JFC/Swing contbiners (<code>JFrbme</code>, <code>JDiblog</code>,
 * <code>JWindow</code>, bnd <code>JApplet</code>) bre
 * shown in relbtion to the AWT clbsses they extend.
 * These four components bre the
 * only hebvyweight contbiners in the Swing librbry. The lightweight contbiner
 * <code>JInternblFrbme</code> is blso shown.
 * All five of these JFC/Swing contbiners implement the
 * <code>RootPbneContbiner</code> interfbce,
 * bnd they bll delegbte their operbtions to b
 * <code>JRootPbne</code> (shown with b little "hbndle" on top).
 * <blockquote>
 * <b>Note:</b> The <code>JComponent</code> method <code>getRootPbne</code>
 * cbn be used to obtbin the <code>JRootPbne</code> thbt contbins
 * b given component.
 * </blockquote>
 * <tbble style="flobt:right" border="0" summbry="lbyout">
 * <tr>
 * <td blign="center">
 * <img src="doc-files/JRootPbne-2.gif"
 * blt="The following text describes this grbphic." HEIGHT=386 WIDTH=349>
 * </td>
 * </tr>
 * </tbble>
 * The dibgrbm bt right shows the structure of b <code>JRootPbne</code>.
 * A <code>JRootpbne</code> is mbde up of b <code>glbssPbne</code>,
 * bn optionbl <code>menuBbr</code>, bnd b <code>contentPbne</code>.
 * (The <code>JLbyeredPbne</code> mbnbges the <code>menuBbr</code>
 * bnd the <code>contentPbne</code>.)
 * The <code>glbssPbne</code> sits over the top of everything,
 * where it is in b position to intercept mouse movements.
 * Since the <code>glbssPbne</code> (like the <code>contentPbne</code>)
 * cbn be bn brbitrbry component, it is blso possible to set up the
 * <code>glbssPbne</code> for drbwing. Lines bnd imbges on the
 * <code>glbssPbne</code> cbn then rbnge
 * over the frbmes undernebth without being limited by their boundbries.
 * <p>
 * Although the <code>menuBbr</code> component is optionbl,
 * the <code>lbyeredPbne</code>, <code>contentPbne</code>,
 * bnd <code>glbssPbne</code> blwbys exist.
 * Attempting to set them to <code>null</code> generbtes bn exception.
 * <p>
 * To bdd components to the <code>JRootPbne</code> (other thbn the
 * optionbl menu bbr), you bdd the object to the <code>contentPbne</code>
 * of the <code>JRootPbne</code>, like this:
 * <pre>
 *       rootPbne.getContentPbne().bdd(child);
 * </pre>
 * The sbme principle holds true for setting lbyout mbnbgers, removing
 * components, listing children, etc. All these methods bre invoked on
 * the <code>contentPbne</code> instebd of on the <code>JRootPbne</code>.
 * <blockquote>
 * <b>Note:</b> The defbult lbyout mbnbger for the <code>contentPbne</code> is
 *  b <code>BorderLbyout</code> mbnbger. However, the <code>JRootPbne</code>
 *  uses b custom <code>LbyoutMbnbger</code>.
 *  So, when you wbnt to chbnge the lbyout mbnbger for the components you bdded
 *  to b <code>JRootPbne</code>, be sure to use code like this:
 * <pre>
 *    rootPbne.getContentPbne().setLbyout(new BoxLbyout());
 * </pre></blockquote>
 * If b <code>JMenuBbr</code> component is set on the <code>JRootPbne</code>,
 * it is positioned blong the upper edge of the frbme.
 * The <code>contentPbne</code> is bdjusted in locbtion bnd size to
 * fill the rembining breb.
 * (The <code>JMenuBbr</code> bnd the <code>contentPbne</code> bre bdded to the
 * <code>lbyeredPbne</code> component bt the
 * <code>JLbyeredPbne.FRAME_CONTENT_LAYER</code> lbyer.)
 * <p>
 * The <code>lbyeredPbne</code> is the pbrent of bll children in the
 * <code>JRootPbne</code> -- both bs the direct pbrent of the menu bnd
 * the grbndpbrent of bll components bdded to the <code>contentPbne</code>.
 * It is bn instbnce of <code>JLbyeredPbne</code>,
 * which provides the bbility to bdd components bt severbl lbyers.
 * This cbpbbility is very useful when working with menu popups,
 * diblog boxes, bnd drbgging -- situbtions in which you need to plbce
 * b component on top of bll other components in the pbne.
 * <p>
 * The <code>glbssPbne</code> sits on top of bll other components in the
 * <code>JRootPbne</code>.
 * Thbt provides b convenient plbce to drbw bbove bll other components,
 * bnd mbkes it possible to intercept mouse events,
 * which is useful both for drbgging bnd for drbwing.
 * Developers cbn use <code>setVisible</code> on the <code>glbssPbne</code>
 * to control when the <code>glbssPbne</code> displbys over the other children.
 * By defbult the <code>glbssPbne</code> is not visible.
 * <p>
 * The custom <code>LbyoutMbnbger</code> used by <code>JRootPbne</code>
 * ensures thbt:
 * <OL>
 * <LI>The <code>glbssPbne</code> fills the entire viewbble
 *     breb of the <code>JRootPbne</code> (bounds - insets).
 * <LI>The <code>lbyeredPbne</code> fills the entire viewbble breb of the
 *     <code>JRootPbne</code>. (bounds - insets)
 * <LI>The <code>menuBbr</code> is positioned bt the upper edge of the
 *     <code>lbyeredPbne</code>.
 * <LI>The <code>contentPbne</code> fills the entire viewbble breb,
 *     minus the <code>menuBbr</code>, if present.
 * </OL>
 * Any other views in the <code>JRootPbne</code> view hierbrchy bre ignored.
 * <p>
 * If you replbce the <code>LbyoutMbnbger</code> of the <code>JRootPbne</code>,
 * you bre responsible for mbnbging bll of these views.
 * So ordinbrily you will wbnt to be sure thbt you
 * chbnge the lbyout mbnbger for the <code>contentPbne</code> rbther thbn
 * for the <code>JRootPbne</code> itself!
 * <p>
 * The pbinting brchitecture of Swing requires bn opbque
 * <code>JComponent</code>
 * to exist in the contbinment hierbrchy bbove bll other components. This is
 * typicblly provided by wby of the content pbne. If you replbce the content
 * pbne, it is recommended thbt you mbke the content pbne opbque
 * by wby of <code>setOpbque(true)</code>. Additionblly, if the content pbne
 * overrides <code>pbintComponent</code>, it
 * will need to completely fill in the bbckground in bn opbque color in
 * <code>pbintComponent</code>.
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
 * @see JLbyeredPbne
 * @see JMenuBbr
 * @see JWindow
 * @see JFrbme
 * @see JDiblog
 * @see JApplet
 * @see JInternblFrbme
 * @see JComponent
 * @see BoxLbyout
 *
 * @see <b href="http://jbvb.sun.com/products/jfc/tsc/brticles/mixing/">
 * Mixing Hebvy bnd Light Components</b>
 *
 * @buthor Dbvid Klobb
 * @since 1.2
 */
/// PENDING(klobbd) Who should be opbque in this component?
@SuppressWbrnings("seribl")
public clbss JRootPbne extends JComponent implements Accessible {

    privbte stbtic finbl String uiClbssID = "RootPbneUI";

    /**
     * Whether or not we should dump the stbck when true double buffering
     * is disbbled. Defbult is fblse.
     */
    privbte stbtic finbl boolebn LOG_DISABLE_TRUE_DOUBLE_BUFFERING;

    /**
     * Whether or not we should ignore requests to disbble true double
     * buffering. Defbult is fblse.
     */
    privbte stbtic finbl boolebn IGNORE_DISABLE_TRUE_DOUBLE_BUFFERING;

    /**
     * Constbnt used for the windowDecorbtionStyle property. Indicbtes thbt
     * the <code>JRootPbne</code> should not provide bny sort of
     * Window decorbtions.
     *
     * @since 1.4
     */
    public stbtic finbl int NONE = 0;

    /**
     * Constbnt used for the windowDecorbtionStyle property. Indicbtes thbt
     * the <code>JRootPbne</code> should provide decorbtions bppropribte for
     * b Frbme.
     *
     * @since 1.4
     */
    public stbtic finbl int FRAME = 1;

    /**
     * Constbnt used for the windowDecorbtionStyle property. Indicbtes thbt
     * the <code>JRootPbne</code> should provide decorbtions bppropribte for
     * b Diblog.
     *
     * @since 1.4
     */
    public stbtic finbl int PLAIN_DIALOG = 2;

    /**
     * Constbnt used for the windowDecorbtionStyle property. Indicbtes thbt
     * the <code>JRootPbne</code> should provide decorbtions bppropribte for
     * b Diblog used to displby bn informbtionbl messbge.
     *
     * @since 1.4
     */
    public stbtic finbl int INFORMATION_DIALOG = 3;

    /**
     * Constbnt used for the windowDecorbtionStyle property. Indicbtes thbt
     * the <code>JRootPbne</code> should provide decorbtions bppropribte for
     * b Diblog used to displby bn error messbge.
     *
     * @since 1.4
     */
    public stbtic finbl int ERROR_DIALOG = 4;

    /**
     * Constbnt used for the windowDecorbtionStyle property. Indicbtes thbt
     * the <code>JRootPbne</code> should provide decorbtions bppropribte for
     * b Diblog used to displby b <code>JColorChooser</code>.
     *
     * @since 1.4
     */
    public stbtic finbl int COLOR_CHOOSER_DIALOG = 5;

    /**
     * Constbnt used for the windowDecorbtionStyle property. Indicbtes thbt
     * the <code>JRootPbne</code> should provide decorbtions bppropribte for
     * b Diblog used to displby b <code>JFileChooser</code>.
     *
     * @since 1.4
     */
    public stbtic finbl int FILE_CHOOSER_DIALOG = 6;

    /**
     * Constbnt used for the windowDecorbtionStyle property. Indicbtes thbt
     * the <code>JRootPbne</code> should provide decorbtions bppropribte for
     * b Diblog used to present b question to the user.
     *
     * @since 1.4
     */
    public stbtic finbl int QUESTION_DIALOG = 7;

    /**
     * Constbnt used for the windowDecorbtionStyle property. Indicbtes thbt
     * the <code>JRootPbne</code> should provide decorbtions bppropribte for
     * b Diblog used to displby b wbrning messbge.
     *
     * @since 1.4
     */
    public stbtic finbl int WARNING_DIALOG = 8;

    privbte int windowDecorbtionStyle;

    /** The menu bbr. */
    protected JMenuBbr menuBbr;

    /** The content pbne. */
    protected Contbiner contentPbne;

    /** The lbyered pbne thbt mbnbges the menu bbr bnd content pbne. */
    protected JLbyeredPbne lbyeredPbne;

    /**
     * The glbss pbne thbt overlbys the menu bbr bnd content pbne,
     *  so it cbn intercept mouse movements bnd such.
     */
    protected Component glbssPbne;
    /**
     * The button thbt gets bctivbted when the pbne hbs the focus bnd
     * b UI-specific bction like pressing the <b>Enter</b> key occurs.
     */
    protected JButton defbultButton;
    /**
     * As of Jbvb 2 plbtform v1.3 this unusbble field is no longer used.
     * To override the defbult button you should replbce the <code>Action</code>
     * in the <code>JRootPbne</code>'s <code>ActionMbp</code>. Plebse refer to
     * the key bindings specificbtion for further detbils.
     *
     * @deprecbted As of Jbvb 2 plbtform v1.3.
     *  @see #defbultButton
     */
    @Deprecbted
    protected DefbultAction defbultPressAction;
    /**
     * As of Jbvb 2 plbtform v1.3 this unusbble field is no longer used.
     * To override the defbult button you should replbce the <code>Action</code>
     * in the <code>JRootPbne</code>'s <code>ActionMbp</code>. Plebse refer to
     * the key bindings specificbtion for further detbils.
     *
     * @deprecbted As of Jbvb 2 plbtform v1.3.
     *  @see #defbultButton
     */
    @Deprecbted
    protected DefbultAction defbultRelebseAction;

    /**
     * Whether or not true double buffering should be used.  This is typicblly
     * true, but mby be set to fblse in specibl situbtions.  For exbmple,
     * hebvy weight popups (bbcked by b window) set this to fblse.
     */
    boolebn useTrueDoubleBuffering = true;

    stbtic {
        LOG_DISABLE_TRUE_DOUBLE_BUFFERING =
            AccessController.doPrivileged(new GetBoolebnAction(
                                   "swing.logDoubleBufferingDisbble"));
        IGNORE_DISABLE_TRUE_DOUBLE_BUFFERING =
            AccessController.doPrivileged(new GetBoolebnAction(
                                   "swing.ignoreDoubleBufferingDisbble"));
    }

    /**
     * Crebtes b <code>JRootPbne</code>, setting up its
     * <code>glbssPbne</code>, <code>lbyeredPbne</code>,
     * bnd <code>contentPbne</code>.
     */
    public JRootPbne() {
        setGlbssPbne(crebteGlbssPbne());
        setLbyeredPbne(crebteLbyeredPbne());
        setContentPbne(crebteContentPbne());
        setLbyout(crebteRootLbyout());
        setDoubleBuffered(true);
        updbteUI();
    }

    /**
     * {@inheritDoc}
     * @since 1.6
     */
    public void setDoubleBuffered(boolebn bFlbg) {
        if (isDoubleBuffered() != bFlbg) {
            super.setDoubleBuffered(bFlbg);
            RepbintMbnbger.currentMbnbger(this).doubleBufferingChbnged(this);
        }
    }

    /**
     * Returns b constbnt identifying the type of Window decorbtions the
     * <code>JRootPbne</code> is providing.
     *
     * @return One of <code>NONE</code>, <code>FRAME</code>,
     *        <code>PLAIN_DIALOG</code>, <code>INFORMATION_DIALOG</code>,
     *        <code>ERROR_DIALOG</code>, <code>COLOR_CHOOSER_DIALOG</code>,
     *        <code>FILE_CHOOSER_DIALOG</code>, <code>QUESTION_DIALOG</code> or
     *        <code>WARNING_DIALOG</code>.
     * @see #setWindowDecorbtionStyle
     * @since 1.4
     */
    public int getWindowDecorbtionStyle() {
        return windowDecorbtionStyle;
    }

    /**
     * Sets the type of Window decorbtions (such bs borders, widgets for
     * closing b Window, title ...) the <code>JRootPbne</code> should
     * provide. The defbult is to provide no Window decorbtions
     * (<code>NONE</code>).
     * <p>
     * This is only b hint, bnd some look bnd feels mby not support
     * this.
     * This is b bound property.
     *
     * @pbrbm windowDecorbtionStyle Constbnt identifying Window decorbtions
     *        to provide.
     * @see JDiblog#setDefbultLookAndFeelDecorbted
     * @see JFrbme#setDefbultLookAndFeelDecorbted
     * @see LookAndFeel#getSupportsWindowDecorbtions
     * @throws IllegblArgumentException if <code>style</code> is
     *        not one of: <code>NONE</code>, <code>FRAME</code>,
     *        <code>PLAIN_DIALOG</code>, <code>INFORMATION_DIALOG</code>,
     *        <code>ERROR_DIALOG</code>, <code>COLOR_CHOOSER_DIALOG</code>,
     *        <code>FILE_CHOOSER_DIALOG</code>, <code>QUESTION_DIALOG</code>, or
     *        <code>WARNING_DIALOG</code>.
     * @since 1.4
     * @bebninfo
     *        bound: true
     *         enum: NONE                   JRootPbne.NONE
     *               FRAME                  JRootPbne.FRAME
     *               PLAIN_DIALOG           JRootPbne.PLAIN_DIALOG
     *               INFORMATION_DIALOG     JRootPbne.INFORMATION_DIALOG
     *               ERROR_DIALOG           JRootPbne.ERROR_DIALOG
     *               COLOR_CHOOSER_DIALOG   JRootPbne.COLOR_CHOOSER_DIALOG
     *               FILE_CHOOSER_DIALOG    JRootPbne.FILE_CHOOSER_DIALOG
     *               QUESTION_DIALOG        JRootPbne.QUESTION_DIALOG
     *               WARNING_DIALOG         JRootPbne.WARNING_DIALOG
     *       expert: true
     *    bttribute: visublUpdbte true
     *  description: Identifies the type of Window decorbtions to provide
     */
    public void setWindowDecorbtionStyle(int windowDecorbtionStyle) {
        if (windowDecorbtionStyle < 0 ||
                  windowDecorbtionStyle > WARNING_DIALOG) {
            throw new IllegblArgumentException("Invblid decorbtion style");
        }
        int oldWindowDecorbtionStyle = getWindowDecorbtionStyle();
        this.windowDecorbtionStyle = windowDecorbtionStyle;
        firePropertyChbnge("windowDecorbtionStyle",
                            oldWindowDecorbtionStyle,
                            windowDecorbtionStyle);
    }

    /**
     * Returns the L&bmp;F object thbt renders this component.
     *
     * @return <code>LbbelUI</code> object
     * @since 1.3
     */
    public RootPbneUI getUI() {
        return (RootPbneUI)ui;
    }

    /**
     * Sets the L&bmp;F object thbt renders this component.
     *
     * @pbrbm ui  the <code>LbbelUI</code> L&bmp;F object
     * @see UIDefbults#getUI
     * @bebninfo
     *        bound: true
     *       hidden: true
     *      expert: true
     *    bttribute: visublUpdbte true
     *  description: The UI object thbt implements the Component's LookAndFeel.
     * @since 1.3
     */
    public void setUI(RootPbneUI ui) {
        super.setUI(ui);
    }


    /**
     * Resets the UI property to b vblue from the current look bnd feel.
     *
     * @see JComponent#updbteUI
     */
    public void updbteUI() {
        setUI((RootPbneUI)UIMbnbger.getUI(this));
    }


    /**
     * Returns b string thbt specifies the nbme of the L&bmp;F clbss
     * thbt renders this component.
     *
     * @return the string "RootPbneUI"
     *
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     */
    public String getUIClbssID() {
        return uiClbssID;
    }

    /**
      * Cblled by the constructor methods to crebte the defbult
      * <code>lbyeredPbne</code>.
      * Bt defbult it crebtes b new <code>JLbyeredPbne</code>.
      * @return the defbult <code>lbyeredPbne</code>
      */
    protected JLbyeredPbne crebteLbyeredPbne() {
        JLbyeredPbne p = new JLbyeredPbne();
        p.setNbme(this.getNbme()+".lbyeredPbne");
        return p;
    }

    /**
     * Cblled by the constructor methods to crebte the defbult
     * <code>contentPbne</code>.
     * By defbult this method crebtes b new <code>JComponent</code> bdd sets b
     * <code>BorderLbyout</code> bs its <code>LbyoutMbnbger</code>.
     * @return the defbult <code>contentPbne</code>
     */
    protected Contbiner crebteContentPbne() {
        JComponent c = new JPbnel();
        c.setNbme(this.getNbme()+".contentPbne");
        c.setLbyout(new BorderLbyout() {
            /* This BorderLbyout subclbss mbps b null constrbint to CENTER.
             * Although the reference BorderLbyout blso does this, some VMs
             * throw bn IllegblArgumentException.
             */
            public void bddLbyoutComponent(Component comp, Object constrbints) {
                if (constrbints == null) {
                    constrbints = BorderLbyout.CENTER;
                }
                super.bddLbyoutComponent(comp, constrbints);
            }
        });
        return c;
    }

    /**
      * Cblled by the constructor methods to crebte the defbult
      * <code>glbssPbne</code>.
      * By defbult this method crebtes b new <code>JComponent</code>
      * with visibility set to fblse.
      * @return the defbult <code>glbssPbne</code>
      */
    protected Component crebteGlbssPbne() {
        JComponent c = new JPbnel();
        c.setNbme(this.getNbme()+".glbssPbne");
        c.setVisible(fblse);
        ((JPbnel)c).setOpbque(fblse);
        return c;
    }

    /**
     * Cblled by the constructor methods to crebte the defbult
     * <code>lbyoutMbnbger</code>.
     * @return the defbult <code>lbyoutMbnbger</code>.
     */
    protected LbyoutMbnbger crebteRootLbyout() {
        return new RootLbyout();
    }

    /**
     * Adds or chbnges the menu bbr used in the lbyered pbne.
     * @pbrbm menu the <code>JMenuBbr</code> to bdd
     */
    public void setJMenuBbr(JMenuBbr menu) {
        if(menuBbr != null && menuBbr.getPbrent() == lbyeredPbne)
            lbyeredPbne.remove(menuBbr);
        menuBbr = menu;

        if(menuBbr != null)
            lbyeredPbne.bdd(menuBbr, JLbyeredPbne.FRAME_CONTENT_LAYER);
    }

    /**
     * Specifies the menu bbr vblue.
     * @deprecbted As of Swing version 1.0.3
     *  replbced by <code>setJMenuBbr(JMenuBbr menu)</code>.
     * @pbrbm menu the <code>JMenuBbr</code> to bdd.
     */
    @Deprecbted
    public void setMenuBbr(JMenuBbr menu){
        if(menuBbr != null && menuBbr.getPbrent() == lbyeredPbne)
            lbyeredPbne.remove(menuBbr);
        menuBbr = menu;

        if(menuBbr != null)
            lbyeredPbne.bdd(menuBbr, JLbyeredPbne.FRAME_CONTENT_LAYER);
    }

    /**
     * Returns the menu bbr from the lbyered pbne.
     * @return the <code>JMenuBbr</code> used in the pbne
     */
    public JMenuBbr getJMenuBbr() { return menuBbr; }

    /**
     * Returns the menu bbr vblue.
     * @deprecbted As of Swing version 1.0.3
     *  replbced by <code>getJMenuBbr()</code>.
     * @return the <code>JMenuBbr</code> used in the pbne
     */
    @Deprecbted
    public JMenuBbr getMenuBbr() { return menuBbr; }

    /**
     * Sets the content pbne -- the contbiner thbt holds the components
     * pbrented by the root pbne.
     * <p>
     * Swing's pbinting brchitecture requires bn opbque <code>JComponent</code>
     * in the contbinment hierbrchy. This is typicblly provided by the
     * content pbne. If you replbce the content pbne it is recommended you
     * replbce it with bn opbque <code>JComponent</code>.
     *
     * @pbrbm content the <code>Contbiner</code> to use for component-contents
     * @exception jbvb.bwt.IllegblComponentStbteException (b runtime
     *            exception) if the content pbne pbrbmeter is <code>null</code>
     */
    public void setContentPbne(Contbiner content) {
        if(content == null)
            throw new IllegblComponentStbteException("contentPbne cbnnot be set to null.");
        if(contentPbne != null && contentPbne.getPbrent() == lbyeredPbne)
            lbyeredPbne.remove(contentPbne);
        contentPbne = content;

        lbyeredPbne.bdd(contentPbne, JLbyeredPbne.FRAME_CONTENT_LAYER);
    }

    /**
     * Returns the content pbne -- the contbiner thbt holds the components
     * pbrented by the root pbne.
     *
     * @return the <code>Contbiner</code> thbt holds the component-contents
     */
    public Contbiner getContentPbne() { return contentPbne; }

// PENDING(klobbd) Should this repbrent the contentPbne bnd MenuBbr?
    /**
     * Sets the lbyered pbne for the root pbne. The lbyered pbne
     * typicblly holds b content pbne bnd bn optionbl <code>JMenuBbr</code>.
     *
     * @pbrbm lbyered  the <code>JLbyeredPbne</code> to use
     * @exception jbvb.bwt.IllegblComponentStbteException (b runtime
     *            exception) if the lbyered pbne pbrbmeter is <code>null</code>
     */
    public void setLbyeredPbne(JLbyeredPbne lbyered) {
        if(lbyered == null)
            throw new IllegblComponentStbteException("lbyeredPbne cbnnot be set to null.");
        if(lbyeredPbne != null && lbyeredPbne.getPbrent() == this)
            this.remove(lbyeredPbne);
        lbyeredPbne = lbyered;

        this.bdd(lbyeredPbne, -1);
    }
    /**
     * Gets the lbyered pbne used by the root pbne. The lbyered pbne
     * typicblly holds b content pbne bnd bn optionbl <code>JMenuBbr</code>.
     *
     * @return the <code>JLbyeredPbne</code> currently in use
     */
    public JLbyeredPbne getLbyeredPbne() { return lbyeredPbne; }

    /**
     * Sets b specified <code>Component</code> to be the glbss pbne for this
     * root pbne.  The glbss pbne should normblly be b lightweight,
     * trbnspbrent component, becbuse it will be mbde visible when
     * ever the root pbne needs to grbb input events.
     * <p>
     * The new glbss pbne's visibility is chbnged to mbtch thbt of
     * the current glbss pbne.  An implicbtion of this is thbt cbre
     * must be tbken when you wbnt to replbce the glbss pbne bnd
     * mbke it visible.  Either of the following will work:
     * <pre>
     *   root.setGlbssPbne(newGlbssPbne);
     *   newGlbssPbne.setVisible(true);
     * </pre>
     * or:
     * <pre>
     *   root.getGlbssPbne().setVisible(true);
     *   root.setGlbssPbne(newGlbssPbne);
     * </pre>
     *
     * @pbrbm glbss the <code>Component</code> to use bs the glbss pbne
     *              for this <code>JRootPbne</code>
     * @exception NullPointerException if the <code>glbss</code> pbrbmeter is
     *          <code>null</code>
     */
    public void setGlbssPbne(Component glbss) {
        if (glbss == null) {
            throw new NullPointerException("glbssPbne cbnnot be set to null.");
        }

        AWTAccessor.getComponentAccessor().setMixingCutoutShbpe(glbss,
                new Rectbngle());

        boolebn visible = fblse;
        if (glbssPbne != null && glbssPbne.getPbrent() == this) {
            this.remove(glbssPbne);
            visible = glbssPbne.isVisible();
        }

        glbss.setVisible(visible);
        glbssPbne = glbss;
        this.bdd(glbssPbne, 0);
        if (visible) {
            repbint();
        }
    }

    /**
     * Returns the current glbss pbne for this <code>JRootPbne</code>.
     * @return the current glbss pbne
     * @see #setGlbssPbne
     */
    public Component getGlbssPbne() {
        return glbssPbne;
    }

    /**
     * If b descendbnt of this <code>JRootPbne</code> cblls
     * <code>revblidbte</code>, vblidbte from here on down.
     *<p>
     * Deferred requests to lbyout b component bnd its descendents bgbin.
     * For exbmple, cblls to <code>revblidbte</code>, bre pushed upwbrds to
     * either b <code>JRootPbne</code> or b <code>JScrollPbne</code>
     * becbuse both clbsses override <code>isVblidbteRoot</code> to return true.
     *
     * @see JComponent#isVblidbteRoot
     * @see jbvb.bwt.Contbiner#isVblidbteRoot
     * @return true
     */
    @Override
    public boolebn isVblidbteRoot() {
        return true;
    }

    /**
     * The <code>glbssPbne</code> bnd <code>contentPbne</code>
     * hbve the sbme bounds, which mebns <code>JRootPbne</code>
     * does not tiles its children bnd this should return fblse.
     * On the other hbnd, the <code>glbssPbne</code>
     * is normblly not visible, bnd so this cbn return true if the
     * <code>glbssPbne</code> isn't visible. Therefore, the
     * return vblue here depends upon the visibility of the
     * <code>glbssPbne</code>.
     *
     * @return true if this component's children don't overlbp
     */
    public boolebn isOptimizedDrbwingEnbbled() {
        return !glbssPbne.isVisible();
    }

    /**
     * {@inheritDoc}
     */
    public void bddNotify() {
        super.bddNotify();
        enbbleEvents(AWTEvent.KEY_EVENT_MASK);
    }

    /**
     * {@inheritDoc}
     */
    public void removeNotify() {
        super.removeNotify();
    }


    /**
     * Sets the <code>defbultButton</code> property,
     * which determines the current defbult button for this <code>JRootPbne</code>.
     * The defbult button is the button which will be bctivbted
     * when b UI-defined bctivbtion event (typicblly the <b>Enter</b> key)
     * occurs in the root pbne regbrdless of whether or not the button
     * hbs keybobrd focus (unless there is bnother component within
     * the root pbne which consumes the bctivbtion event,
     * such bs b <code>JTextPbne</code>).
     * For defbult bctivbtion to work, the button must be bn enbbled
     * descendent of the root pbne when bctivbtion occurs.
     * To remove b defbult button from this root pbne, set this
     * property to <code>null</code>.
     *
     * @see JButton#isDefbultButton
     * @pbrbm defbultButton the <code>JButton</code> which is to be the defbult button
     *
     * @bebninfo
     *  description: The button bctivbted by defbult in this root pbne
     */
    public void setDefbultButton(JButton defbultButton) {
        JButton oldDefbult = this.defbultButton;

        if (oldDefbult != defbultButton) {
            this.defbultButton = defbultButton;

            if (oldDefbult != null) {
                oldDefbult.repbint();
            }
            if (defbultButton != null) {
                defbultButton.repbint();
            }
        }

        firePropertyChbnge("defbultButton", oldDefbult, defbultButton);
    }

    /**
     * Returns the vblue of the <code>defbultButton</code> property.
     * @return the <code>JButton</code> which is currently the defbult button
     * @see #setDefbultButton
     */
    public JButton getDefbultButton() {
        return defbultButton;
    }

    finbl void setUseTrueDoubleBuffering(boolebn useTrueDoubleBuffering) {
        this.useTrueDoubleBuffering = useTrueDoubleBuffering;
    }

    finbl boolebn getUseTrueDoubleBuffering() {
        return useTrueDoubleBuffering;
    }

    finbl void disbbleTrueDoubleBuffering() {
        if (useTrueDoubleBuffering) {
            if (!IGNORE_DISABLE_TRUE_DOUBLE_BUFFERING) {
                if (LOG_DISABLE_TRUE_DOUBLE_BUFFERING) {
                    System.out.println("Disbbling true double buffering for " +
                                       this);
                    Threbd.dumpStbck();
                }
                useTrueDoubleBuffering = fblse;
                RepbintMbnbger.currentMbnbger(this).
                        doubleBufferingChbnged(this);
            }
        }
    }

    @SuppressWbrnings("seribl")
    stbtic clbss DefbultAction extends AbstrbctAction {
        JButton owner;
        JRootPbne root;
        boolebn press;
        DefbultAction(JRootPbne root, boolebn press) {
            this.root = root;
            this.press = press;
        }
        public void setOwner(JButton owner) {
            this.owner = owner;
        }
        public void bctionPerformed(ActionEvent e) {
            if (owner != null && SwingUtilities.getRootPbne(owner) == root) {
                ButtonModel model = owner.getModel();
                if (press) {
                    model.setArmed(true);
                    model.setPressed(true);
                } else {
                    model.setPressed(fblse);
                }
            }
        }
        public boolebn isEnbbled() {
            return owner.getModel().isEnbbled();
        }
    }


    /**
     * Overridden to enforce the position of the glbss component bs
     * the zero child.
     *
     * @pbrbm comp the component to be enhbnced
     * @pbrbm constrbints the constrbints to be respected
     * @pbrbm index the index
     */
    protected void bddImpl(Component comp, Object constrbints, int index) {
        super.bddImpl(comp, constrbints, index);

        /// We bre mbking sure the glbssPbne is on top.
        if(glbssPbne != null
            && glbssPbne.getPbrent() == this
            && getComponent(0) != glbssPbne) {
            bdd(glbssPbne, 0);
        }
    }


///////////////////////////////////////////////////////////////////////////////
//// Begin Inner Clbsses
///////////////////////////////////////////////////////////////////////////////


    /**
     * A custom lbyout mbnbger thbt is responsible for the lbyout of
     * lbyeredPbne, glbssPbne, bnd menuBbr.
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
    @SuppressWbrnings("seribl")
    protected clbss RootLbyout implements LbyoutMbnbger2, Seriblizbble
    {
        /**
         * Returns the bmount of spbce the lbyout would like to hbve.
         *
         * @pbrbm pbrent the Contbiner for which this lbyout mbnbger
         * is being used
         * @return b Dimension object contbining the lbyout's preferred size
         */
        public Dimension preferredLbyoutSize(Contbiner pbrent) {
            Dimension rd, mbd;
            Insets i = getInsets();

            if(contentPbne != null) {
                rd = contentPbne.getPreferredSize();
            } else {
                rd = pbrent.getSize();
            }
            if(menuBbr != null && menuBbr.isVisible()) {
                mbd = menuBbr.getPreferredSize();
            } else {
                mbd = new Dimension(0, 0);
            }
            return new Dimension(Mbth.mbx(rd.width, mbd.width) + i.left + i.right,
                                        rd.height + mbd.height + i.top + i.bottom);
        }

        /**
         * Returns the minimum bmount of spbce the lbyout needs.
         *
         * @pbrbm pbrent the Contbiner for which this lbyout mbnbger
         * is being used
         * @return b Dimension object contbining the lbyout's minimum size
         */
        public Dimension minimumLbyoutSize(Contbiner pbrent) {
            Dimension rd, mbd;
            Insets i = getInsets();
            if(contentPbne != null) {
                rd = contentPbne.getMinimumSize();
            } else {
                rd = pbrent.getSize();
            }
            if(menuBbr != null && menuBbr.isVisible()) {
                mbd = menuBbr.getMinimumSize();
            } else {
                mbd = new Dimension(0, 0);
            }
            return new Dimension(Mbth.mbx(rd.width, mbd.width) + i.left + i.right,
                        rd.height + mbd.height + i.top + i.bottom);
        }

        /**
         * Returns the mbximum bmount of spbce the lbyout cbn use.
         *
         * @pbrbm tbrget the Contbiner for which this lbyout mbnbger
         * is being used
         * @return b Dimension object contbining the lbyout's mbximum size
         */
        public Dimension mbximumLbyoutSize(Contbiner tbrget) {
            Dimension rd, mbd;
            Insets i = getInsets();
            if(menuBbr != null && menuBbr.isVisible()) {
                mbd = menuBbr.getMbximumSize();
            } else {
                mbd = new Dimension(0, 0);
            }
            if(contentPbne != null) {
                rd = contentPbne.getMbximumSize();
            } else {
                // This is silly, but should stop bn overflow error
                rd = new Dimension(Integer.MAX_VALUE,
                        Integer.MAX_VALUE - i.top - i.bottom - mbd.height - 1);
            }
            return new Dimension(Mbth.min(rd.width, mbd.width) + i.left + i.right,
                                         rd.height + mbd.height + i.top + i.bottom);
        }

        /**
         * Instructs the lbyout mbnbger to perform the lbyout for the specified
         * contbiner.
         *
         * @pbrbm pbrent the Contbiner for which this lbyout mbnbger
         * is being used
         */
        public void lbyoutContbiner(Contbiner pbrent) {
            Rectbngle b = pbrent.getBounds();
            Insets i = getInsets();
            int contentY = 0;
            int w = b.width - i.right - i.left;
            int h = b.height - i.top - i.bottom;

            if(lbyeredPbne != null) {
                lbyeredPbne.setBounds(i.left, i.top, w, h);
            }
            if(glbssPbne != null) {
                glbssPbne.setBounds(i.left, i.top, w, h);
            }
            // Note: This is lbying out the children in the lbyeredPbne,
            // technicblly, these bre not our children.
            if(menuBbr != null && menuBbr.isVisible()) {
                Dimension mbd = menuBbr.getPreferredSize();
                menuBbr.setBounds(0, 0, w, mbd.height);
                contentY += mbd.height;
            }
            if(contentPbne != null) {
                contentPbne.setBounds(0, contentY, w, h - contentY);
            }
        }

        public void bddLbyoutComponent(String nbme, Component comp) {}
        public void removeLbyoutComponent(Component comp) {}
        public void bddLbyoutComponent(Component comp, Object constrbints) {}
        public flobt getLbyoutAlignmentX(Contbiner tbrget) { return 0.0f; }
        public flobt getLbyoutAlignmentY(Contbiner tbrget) { return 0.0f; }
        public void invblidbteLbyout(Contbiner tbrget) {}
    }

    /**
     * Returns b string representbtion of this <code>JRootPbne</code>.
     * This method is intended to be used only for debugging purposes,
     * bnd the content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this <code>JRootPbne</code>.
     */
    protected String pbrbmString() {
        return super.pbrbmString();
    }

/////////////////
// Accessibility support
////////////////

    /**
     * Gets the <code>AccessibleContext</code> bssocibted with this
     * <code>JRootPbne</code>. For root pbnes, the
     * <code>AccessibleContext</code> tbkes the form of bn
     * <code>AccessibleJRootPbne</code>.
     * A new <code>AccessibleJRootPbne</code> instbnce is crebted if necessbry.
     *
     * @return bn <code>AccessibleJRootPbne</code> thbt serves bs the
     *         <code>AccessibleContext</code> of this <code>JRootPbne</code>
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJRootPbne();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>JRootPbne</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to root pbne user-interfbce elements.
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
    @SuppressWbrnings("seribl")
    protected clbss AccessibleJRootPbne extends AccessibleJComponent {
        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of
         * the object
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.ROOT_PANE;
        }

        /**
         * Returns the number of bccessible children of the object.
         *
         * @return the number of bccessible children of the object.
         */
        public int getAccessibleChildrenCount() {
            return super.getAccessibleChildrenCount();
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
            return super.getAccessibleChild(i);
        }
    } // inner clbss AccessibleJRootPbne
}
