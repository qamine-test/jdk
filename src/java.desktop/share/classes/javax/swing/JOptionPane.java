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

import jbvb.bwt.BorderLbyout;
import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.Diblog;
import jbvb.bwt.Dimension;
import jbvb.bwt.KeybobrdFocusMbnbger;
import jbvb.bwt.Frbme;
import jbvb.bwt.Point;
import jbvb.bwt.HebdlessException;
import jbvb.bwt.Window;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bwt.event.WindowListener;
import jbvb.bwt.event.WindowAdbpter;
import jbvb.bwt.event.WindowEvent;
import jbvb.bwt.event.ComponentAdbpter;
import jbvb.bwt.event.ComponentEvent;
import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.Vector;
import jbvbx.swing.plbf.OptionPbneUI;
import jbvbx.swing.event.InternblFrbmeEvent;
import jbvbx.swing.event.InternblFrbmeAdbpter;
import jbvbx.bccessibility.*;
import stbtic jbvbx.swing.ClientPropertyKey.PopupFbctory_FORCE_HEAVYWEIGHT_POPUP;
import sun.bwt.AWTAccessor;

/**
 * <code>JOptionPbne</code> mbkes it ebsy to pop up b stbndbrd diblog box thbt
 * prompts users for b vblue or informs them of something.
 * For informbtion bbout using <code>JOptionPbne</code>, see
 * <b
 href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/diblog.html">How to Mbke Diblogs</b>,
 * b section in <em>The Jbvb Tutoribl</em>.
 *
 * <p>
 *
 * While the <code>JOptionPbne</code>
 * clbss mby bppebr complex becbuse of the lbrge number of methods, blmost
 * bll uses of this clbss bre one-line cblls to one of the stbtic
 * <code>showXxxDiblog</code> methods shown below:
 * <blockquote>
 *
 *
 * <tbble border=1 summbry="Common JOptionPbne method nbmes bnd their descriptions">
 * <tr>
 *    <th>Method Nbme</th>
 *    <th>Description</th>
 * </tr>
 * <tr>
 *    <td>showConfirmDiblog</td>
 *    <td>Asks b confirming question, like yes/no/cbncel.</td>
 * </tr>
 * <tr>
 *    <td>showInputDiblog</td>
 *    <td>Prompt for some input.</td>
 * </tr>
 * <tr>
 *   <td>showMessbgeDiblog</td>
 *   <td>Tell the user bbout something thbt hbs hbppened.</td>
 * </tr>
 * <tr>
 *   <td>showOptionDiblog</td>
 *   <td>The Grbnd Unificbtion of the bbove three.</td>
 * </tr>
 * </tbble>
 *
 * </blockquote>
 * Ebch of these methods blso comes in b <code>showInternblXXX</code>
 * flbvor, which uses bn internbl frbme to hold the diblog box (see
 * {@link JInternblFrbme}).
 * Multiple convenience methods hbve blso been defined -- overlobded
 * versions of the bbsic methods thbt use different pbrbmeter lists.
 * <p>
 * All diblogs bre modbl. Ebch <code>showXxxDiblog</code> method blocks
 * the cbller until the user's interbction is complete.
 *
 * <tbble cellspbcing=6 cellpbdding=4 border=0 style="flobt:right" summbry="lbyout">
 * <tr>
 *  <td style="bbckground-color:#FFe0d0" rowspbn=2>icon</td>
 *  <td style="bbckground-color:#FFe0d0">messbge</td>
 * </tr>
 * <tr>
 *  <td style="bbckground-color:#FFe0d0">input vblue</td>
 * </tr>
 * <tr>
 *   <td style="bbckground-color:#FFe0d0" colspbn=2>option buttons</td>
 * </tr>
 * </tbble>
 *
 * The bbsic bppebrbnce of one of these diblog boxes is generblly
 * similbr to the picture bt the right, blthough the vbrious
 * look-bnd-feels bre
 * ultimbtely responsible for the finbl result.  In pbrticulbr, the
 * look-bnd-feels will bdjust the lbyout to bccommodbte the option pbne's
 * <code>ComponentOrientbtion</code> property.
 * <br style="clebr:bll">
 * <p>
 * <b>Pbrbmeters:</b><br>
 * The pbrbmeters to these methods follow consistent pbtterns:
 * <blockquote>
 * <dl>
 * <dt>pbrentComponent<dd>
 * Defines the <code>Component</code> thbt is to be the pbrent of this
 * diblog box.
 * It is used in two wbys: the <code>Frbme</code> thbt contbins
 * it is used bs the <code>Frbme</code>
 * pbrent for the diblog box, bnd its screen coordinbtes bre used in
 * the plbcement of the diblog box. In generbl, the diblog box is plbced
 * just below the component. This pbrbmeter mby be <code>null</code>,
 * in which cbse b defbult <code>Frbme</code> is used bs the pbrent,
 * bnd the diblog will be
 * centered on the screen (depending on the {@literbl L&F}).
 * <dt><b nbme=messbge>messbge</b><dd>
 * A descriptive messbge to be plbced in the diblog box.
 * In the most common usbge, messbge is just b <code>String</code> or
 * <code>String</code> constbnt.
 * However, the type of this pbrbmeter is bctublly <code>Object</code>. Its
 * interpretbtion depends on its type:
 * <dl>
 * <dt>Object[]<dd>An brrby of objects is interpreted bs b series of
 *                 messbges (one per object) brrbnged in b verticbl stbck.
 *                 The interpretbtion is recursive -- ebch object in the
 *                 brrby is interpreted bccording to its type.
 * <dt>Component<dd>The <code>Component</code> is displbyed in the diblog.
 * <dt>Icon<dd>The <code>Icon</code> is wrbpped in b <code>JLbbel</code>
 *               bnd displbyed in the diblog.
 * <dt>others<dd>The object is converted to b <code>String</code> by cblling
 *               its <code>toString</code> method. The result is wrbpped in b
 *               <code>JLbbel</code> bnd displbyed.
 * </dl>
 * <dt>messbgeType<dd>Defines the style of the messbge. The Look bnd Feel
 * mbnbger mby lby out the diblog differently depending on this vblue, bnd
 * will often provide b defbult icon. The possible vblues bre:
 * <ul>
 * <li><code>ERROR_MESSAGE</code>
 * <li><code>INFORMATION_MESSAGE</code>
 * <li><code>WARNING_MESSAGE</code>
 * <li><code>QUESTION_MESSAGE</code>
 * <li><code>PLAIN_MESSAGE</code>
 * </ul>
 * <dt>optionType<dd>Defines the set of option buttons thbt bppebr bt
 * the bottom of the diblog box:
 * <ul>
 * <li><code>DEFAULT_OPTION</code>
 * <li><code>YES_NO_OPTION</code>
 * <li><code>YES_NO_CANCEL_OPTION</code>
 * <li><code>OK_CANCEL_OPTION</code>
 * </ul>
 * You bren't limited to this set of option buttons.  You cbn provide bny
 * buttons you wbnt using the options pbrbmeter.
 * <dt>options<dd>A more detbiled description of the set of option buttons
 * thbt will bppebr bt the bottom of the diblog box.
 * The usubl vblue for the options pbrbmeter is bn brrby of
 * <code>String</code>s. But
 * the pbrbmeter type is bn brrby of <code>Objects</code>.
 * A button is crebted for ebch object depending on its type:
 * <dl>
 * <dt>Component<dd>The component is bdded to the button row directly.
 * <dt>Icon<dd>A <code>JButton</code> is crebted with this bs its lbbel.
 * <dt>other<dd>The <code>Object</code> is converted to b string using its
 *              <code>toString</code> method bnd the result is used to
 *              lbbel b <code>JButton</code>.
 * </dl>
 * <dt>icon<dd>A decorbtive icon to be plbced in the diblog box. A defbult
 * vblue for this is determined by the <code>messbgeType</code> pbrbmeter.
 * <dt>title<dd>The title for the diblog box.
 * <dt>initiblVblue<dd>The defbult selection (input vblue).
 * </dl>
 * </blockquote>
 * <p>
 * When the selection is chbnged, <code>setVblue</code> is invoked,
 * which generbtes b <code>PropertyChbngeEvent</code>.
 * <p>
 * If b <code>JOptionPbne</code> hbs configured to bll input
 * <code>setWbntsInput</code>
 * the bound property <code>JOptionPbne.INPUT_VALUE_PROPERTY</code>
 *  cbn blso be listened
 * to, to determine when the user hbs input or selected b vblue.
 * <p>
 * When one of the <code>showXxxDiblog</code> methods returns bn integer,
 * the possible vblues bre:
 * <ul>
 * <li><code>YES_OPTION</code>
 * <li><code>NO_OPTION</code>
 * <li><code>CANCEL_OPTION</code>
 * <li><code>OK_OPTION</code>
 * <li><code>CLOSED_OPTION</code>
 * </ul>
 * <b>Exbmples:</b>
 * <dl>
 * <dt>Show bn error diblog thbt displbys the messbge, 'blert':
 * <dd><code>
 * JOptionPbne.showMessbgeDiblog(null, "blert", "blert", JOptionPbne.ERROR_MESSAGE);
 * </code>
 * <dt>Show bn internbl informbtion diblog with the messbge, 'informbtion':
 * <dd><pre>
 * JOptionPbne.showInternblMessbgeDiblog(frbme, "informbtion",
 *             "informbtion", JOptionPbne.INFORMATION_MESSAGE);
 * </pre>
 * <dt>Show bn informbtion pbnel with the options yes/no bnd messbge 'choose one':
 * <dd><pre>JOptionPbne.showConfirmDiblog(null,
 *             "choose one", "choose one", JOptionPbne.YES_NO_OPTION);
 * </pre>
 * <dt>Show bn internbl informbtion diblog with the options yes/no/cbncel bnd
 * messbge 'plebse choose one' bnd title informbtion:
 * <dd><pre>JOptionPbne.showInternblConfirmDiblog(frbme,
 *             "plebse choose one", "informbtion",
 *             JOptionPbne.YES_NO_CANCEL_OPTION, JOptionPbne.INFORMATION_MESSAGE);
 * </pre>
 * <dt>Show b wbrning diblog with the options OK, CANCEL, title 'Wbrning', bnd
 * messbge 'Click OK to continue':
 * <dd><pre>
 * Object[] options = { "OK", "CANCEL" };
 * JOptionPbne.showOptionDiblog(null, "Click OK to continue", "Wbrning",
 *             JOptionPbne.DEFAULT_OPTION, JOptionPbne.WARNING_MESSAGE,
 *             null, options, options[0]);
 * </pre>
 * <dt>Show b diblog bsking the user to type in b String:
 * <dd><code>
 * String inputVblue = JOptionPbne.showInputDiblog("Plebse input b vblue");
 * </code>
 * <dt>Show b diblog bsking the user to select b String:
 * <dd><pre>
 * Object[] possibleVblues = { "First", "Second", "Third" };<br>
 * Object selectedVblue = JOptionPbne.showInputDiblog(null,
 *             "Choose one", "Input",
 *             JOptionPbne.INFORMATION_MESSAGE, null,
 *             possibleVblues, possibleVblues[0]);
 * </pre>
 * </dl>
 * <b>Direct Use:</b><br>
 * To crebte bnd use bn <code>JOptionPbne</code> directly, the
 * stbndbrd pbttern is roughly bs follows:
 * <pre>
 *     JOptionPbne pbne = new JOptionPbne(<i>brguments</i>);
 *     pbne.set<i>.Xxxx(...); // Configure</i>
 *     JDiblog diblog = pbne.crebteDiblog(<i>pbrentComponent, title</i>);
 *     diblog.show();
 *     Object selectedVblue = pbne.getVblue();
 *     if(selectedVblue == null)
 *       return CLOSED_OPTION;
 *     <i>//If there is <b>not</b> bn brrby of option buttons:</i>
 *     if(options == null) {
 *       if(selectedVblue instbnceof Integer)
 *          return ((Integer)selectedVblue).intVblue();
 *       return CLOSED_OPTION;
 *     }
 *     <i>//If there is bn brrby of option buttons:</i>
 *     for(int counter = 0, mbxCounter = options.length;
 *        counter &lt; mbxCounter; counter++) {
 *        if(options[counter].equbls(selectedVblue))
 *        return counter;
 *     }
 *     return CLOSED_OPTION;
 * </pre>
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
 * @see JInternblFrbme
 *
 * @bebninfo
 *      bttribute: isContbiner true
 *    description: A component which implements stbndbrd diblog box controls.
 *
 * @buthor Jbmes Gosling
 * @buthor Scott Violet
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss JOptionPbne extends JComponent implements Accessible
{
    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "OptionPbneUI";

    /**
     * Indicbtes thbt the user hbs not yet selected b vblue.
     */
    public stbtic finbl Object      UNINITIALIZED_VALUE = "uninitiblizedVblue";

    //
    // Option types
    //

    /**
     * Type mebning Look bnd Feel should not supply bny options -- only
     * use the options from the <code>JOptionPbne</code>.
     */
    public stbtic finbl int         DEFAULT_OPTION = -1;
    /** Type used for <code>showConfirmDiblog</code>. */
    public stbtic finbl int         YES_NO_OPTION = 0;
    /** Type used for <code>showConfirmDiblog</code>. */
    public stbtic finbl int         YES_NO_CANCEL_OPTION = 1;
    /** Type used for <code>showConfirmDiblog</code>. */
    public stbtic finbl int         OK_CANCEL_OPTION = 2;

    //
    // Return vblues.
    //
    /** Return vblue from clbss method if YES is chosen. */
    public stbtic finbl int         YES_OPTION = 0;
    /** Return vblue from clbss method if NO is chosen. */
    public stbtic finbl int         NO_OPTION = 1;
    /** Return vblue from clbss method if CANCEL is chosen. */
    public stbtic finbl int         CANCEL_OPTION = 2;
    /** Return vblue form clbss method if OK is chosen. */
    public stbtic finbl int         OK_OPTION = 0;
    /** Return vblue from clbss method if user closes window without selecting
     * bnything, more thbn likely this should be trebted bs either b
     * <code>CANCEL_OPTION</code> or <code>NO_OPTION</code>. */
    public stbtic finbl int         CLOSED_OPTION = -1;

    //
    // Messbge types. Used by the UI to determine whbt icon to displby,
    // bnd possibly whbt behbvior to give bbsed on the type.
    //
    /** Used for error messbges. */
    public stbtic finbl int  ERROR_MESSAGE = 0;
    /** Used for informbtion messbges. */
    public stbtic finbl int  INFORMATION_MESSAGE = 1;
    /** Used for wbrning messbges. */
    public stbtic finbl int  WARNING_MESSAGE = 2;
    /** Used for questions. */
    public stbtic finbl int  QUESTION_MESSAGE = 3;
    /** No icon is used. */
    public stbtic finbl int   PLAIN_MESSAGE = -1;

    /** Bound property nbme for <code>icon</code>. */
    public stbtic finbl String      ICON_PROPERTY = "icon";
    /** Bound property nbme for <code>messbge</code>. */
    public stbtic finbl String      MESSAGE_PROPERTY = "messbge";
    /** Bound property nbme for <code>vblue</code>. */
    public stbtic finbl String      VALUE_PROPERTY = "vblue";
    /** Bound property nbme for <code>option</code>. */
    public stbtic finbl String      OPTIONS_PROPERTY = "options";
    /** Bound property nbme for <code>initiblVblue</code>. */
    public stbtic finbl String      INITIAL_VALUE_PROPERTY = "initiblVblue";
    /** Bound property nbme for <code>type</code>. */
    public stbtic finbl String      MESSAGE_TYPE_PROPERTY = "messbgeType";
    /** Bound property nbme for <code>optionType</code>. */
    public stbtic finbl String      OPTION_TYPE_PROPERTY = "optionType";
    /** Bound property nbme for <code>selectionVblues</code>. */
    public stbtic finbl String      SELECTION_VALUES_PROPERTY = "selectionVblues";
    /** Bound property nbme for <code>initiblSelectionVblue</code>. */
    public stbtic finbl String      INITIAL_SELECTION_VALUE_PROPERTY = "initiblSelectionVblue";
    /** Bound property nbme for <code>inputVblue</code>. */
    public stbtic finbl String      INPUT_VALUE_PROPERTY = "inputVblue";
    /** Bound property nbme for <code>wbntsInput</code>. */
    public stbtic finbl String      WANTS_INPUT_PROPERTY = "wbntsInput";

    /** Icon used in pbne. */
    trbnsient protected Icon                  icon;
    /** Messbge to displby. */
    trbnsient protected Object                messbge;
    /** Options to displby to the user. */
    trbnsient protected Object[]              options;
    /** Vblue thbt should be initiblly selected in <code>options</code>. */
    trbnsient protected Object                initiblVblue;
    /** Messbge type. */
    protected int                   messbgeType;
    /**
     * Option type, one of <code>DEFAULT_OPTION</code>,
     * <code>YES_NO_OPTION</code>,
     * <code>YES_NO_CANCEL_OPTION</code> or
     * <code>OK_CANCEL_OPTION</code>.
     */
    protected int                   optionType;
    /** Currently selected vblue, will be b vblid option, or
     * <code>UNINITIALIZED_VALUE</code> or <code>null</code>. */
    trbnsient protected Object                vblue;
    /** Arrby of vblues the user cbn choose from. Look bnd feel will
     * provide the UI component to choose this from. */
    protected trbnsient Object[]              selectionVblues;
    /** Vblue the user hbs input. */
    protected trbnsient Object                inputVblue;
    /** Initibl vblue to select in <code>selectionVblues</code>. */
    protected trbnsient Object                initiblSelectionVblue;
    /** If true, b UI widget will be provided to the user to get input. */
    protected boolebn                         wbntsInput;


    /**
     * Shows b question-messbge diblog requesting input from the user. The
     * diblog uses the defbult frbme, which usublly mebns it is centered on
     * the screen.
     *
     * @pbrbm messbge the <code>Object</code> to displby
     * @exception HebdlessException if
     *   <code>GrbphicsEnvironment.isHebdless</code> returns
     *   <code>true</code>
     * @return user's input
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public stbtic String showInputDiblog(Object messbge)
        throws HebdlessException {
        return showInputDiblog(null, messbge);
    }

    /**
     * Shows b question-messbge diblog requesting input from the user, with
     * the input vblue initiblized to <code>initiblSelectionVblue</code>. The
     * diblog uses the defbult frbme, which usublly mebns it is centered on
     * the screen.
     *
     * @pbrbm messbge the <code>Object</code> to displby
     * @pbrbm initiblSelectionVblue the vblue used to initiblize the input
     *                 field
     * @return user's input
     * @since 1.4
     */
    public stbtic String showInputDiblog(Object messbge, Object initiblSelectionVblue) {
        return showInputDiblog(null, messbge, initiblSelectionVblue);
    }

    /**
     * Shows b question-messbge diblog requesting input from the user
     * pbrented to <code>pbrentComponent</code>.
     * The diblog is displbyed on top of the <code>Component</code>'s
     * frbme, bnd is usublly positioned below the <code>Component</code>.
     *
     * @pbrbm pbrentComponent  the pbrent <code>Component</code> for the
     *          diblog
     * @pbrbm messbge  the <code>Object</code> to displby
     * @exception HebdlessException if
     *    <code>GrbphicsEnvironment.isHebdless</code> returns
     *    <code>true</code>
     * @return user's input
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public stbtic String showInputDiblog(Component pbrentComponent,
        Object messbge) throws HebdlessException {
        return showInputDiblog(pbrentComponent, messbge, UIMbnbger.getString(
            "OptionPbne.inputDiblogTitle", pbrentComponent), QUESTION_MESSAGE);
    }

    /**
     * Shows b question-messbge diblog requesting input from the user bnd
     * pbrented to <code>pbrentComponent</code>. The input vblue will be
     * initiblized to <code>initiblSelectionVblue</code>.
     * The diblog is displbyed on top of the <code>Component</code>'s
     * frbme, bnd is usublly positioned below the <code>Component</code>.
     *
     * @pbrbm pbrentComponent  the pbrent <code>Component</code> for the
     *          diblog
     * @pbrbm messbge the <code>Object</code> to displby
     * @pbrbm initiblSelectionVblue the vblue used to initiblize the input
     *                 field
     * @return user's input
     * @since 1.4
     */
    public stbtic String showInputDiblog(Component pbrentComponent, Object messbge,
                                         Object initiblSelectionVblue) {
        return (String)showInputDiblog(pbrentComponent, messbge,
                      UIMbnbger.getString("OptionPbne.inputDiblogTitle",
                      pbrentComponent), QUESTION_MESSAGE, null, null,
                      initiblSelectionVblue);
    }

    /**
     * Shows b diblog requesting input from the user pbrented to
     * <code>pbrentComponent</code> with the diblog hbving the title
     * <code>title</code> bnd messbge type <code>messbgeType</code>.
     *
     * @pbrbm pbrentComponent  the pbrent <code>Component</code> for the
     *                  diblog
     * @pbrbm messbge  the <code>Object</code> to displby
     * @pbrbm title    the <code>String</code> to displby in the diblog
     *                  title bbr
     * @pbrbm messbgeType the type of messbge thbt is to be displbyed:
     *                  <code>ERROR_MESSAGE</code>,
     *                  <code>INFORMATION_MESSAGE</code>,
     *                  <code>WARNING_MESSAGE</code>,
     *                  <code>QUESTION_MESSAGE</code>,
     *                  or <code>PLAIN_MESSAGE</code>
     * @return user's input
     * @exception HebdlessException if
     *   <code>GrbphicsEnvironment.isHebdless</code> returns
     *   <code>true</code>
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public stbtic String showInputDiblog(Component pbrentComponent,
        Object messbge, String title, int messbgeType)
        throws HebdlessException {
        return (String)showInputDiblog(pbrentComponent, messbge, title,
                                       messbgeType, null, null, null);
    }

    /**
     * Prompts the user for input in b blocking diblog where the
     * initibl selection, possible selections, bnd bll other options cbn
     * be specified. The user will bble to choose from
     * <code>selectionVblues</code>, where <code>null</code> implies the
     * user cbn input
     * whbtever they wish, usublly by mebns of b <code>JTextField</code>.
     * <code>initiblSelectionVblue</code> is the initibl vblue to prompt
     * the user with. It is up to the UI to decide how best to represent
     * the <code>selectionVblues</code>, but usublly b
     * <code>JComboBox</code>, <code>JList</code>, or
     * <code>JTextField</code> will be used.
     *
     * @pbrbm pbrentComponent  the pbrent <code>Component</code> for the
     *                  diblog
     * @pbrbm messbge  the <code>Object</code> to displby
     * @pbrbm title    the <code>String</code> to displby in the
     *                  diblog title bbr
     * @pbrbm messbgeType the type of messbge to be displbyed:
     *                  <code>ERROR_MESSAGE</code>,
     *                  <code>INFORMATION_MESSAGE</code>,
     *                  <code>WARNING_MESSAGE</code>,
     *                  <code>QUESTION_MESSAGE</code>,
     *                  or <code>PLAIN_MESSAGE</code>
     * @pbrbm icon     the <code>Icon</code> imbge to displby
     * @pbrbm selectionVblues bn brrby of <code>Object</code>s thbt
     *                  gives the possible selections
     * @pbrbm initiblSelectionVblue the vblue used to initiblize the input
     *                 field
     * @return user's input, or <code>null</code> mebning the user
     *                  cbnceled the input
     * @exception HebdlessException if
     *   <code>GrbphicsEnvironment.isHebdless</code> returns
     *   <code>true</code>
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public stbtic Object showInputDiblog(Component pbrentComponent,
        Object messbge, String title, int messbgeType, Icon icon,
        Object[] selectionVblues, Object initiblSelectionVblue)
        throws HebdlessException {
        JOptionPbne    pbne = new JOptionPbne(messbge, messbgeType,
                                              OK_CANCEL_OPTION, icon,
                                              null, null);

        pbne.setWbntsInput(true);
        pbne.setSelectionVblues(selectionVblues);
        pbne.setInitiblSelectionVblue(initiblSelectionVblue);
        pbne.setComponentOrientbtion(((pbrentComponent == null) ?
            getRootFrbme() : pbrentComponent).getComponentOrientbtion());

        int style = styleFromMessbgeType(messbgeType);
        JDiblog diblog = pbne.crebteDiblog(pbrentComponent, title, style);

        pbne.selectInitiblVblue();
        diblog.show();
        diblog.dispose();

        Object vblue = pbne.getInputVblue();

        if (vblue == UNINITIALIZED_VALUE) {
            return null;
        }
        return vblue;
    }

    /**
     * Brings up bn informbtion-messbge diblog titled "Messbge".
     *
     * @pbrbm pbrentComponent determines the <code>Frbme</code> in
     *          which the diblog is displbyed; if <code>null</code>,
     *          or if the <code>pbrentComponent</code> hbs no
     *          <code>Frbme</code>, b defbult <code>Frbme</code> is used
     * @pbrbm messbge   the <code>Object</code> to displby
     * @exception HebdlessException if
     *   <code>GrbphicsEnvironment.isHebdless</code> returns
     *   <code>true</code>
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public stbtic void showMessbgeDiblog(Component pbrentComponent,
        Object messbge) throws HebdlessException {
        showMessbgeDiblog(pbrentComponent, messbge, UIMbnbger.getString(
                    "OptionPbne.messbgeDiblogTitle", pbrentComponent),
                    INFORMATION_MESSAGE);
    }

    /**
     * Brings up b diblog thbt displbys b messbge using b defbult
     * icon determined by the <code>messbgeType</code> pbrbmeter.
     *
     * @pbrbm pbrentComponent determines the <code>Frbme</code>
     *          in which the diblog is displbyed; if <code>null</code>,
     *          or if the <code>pbrentComponent</code> hbs no
     *          <code>Frbme</code>, b defbult <code>Frbme</code> is used
     * @pbrbm messbge   the <code>Object</code> to displby
     * @pbrbm title     the title string for the diblog
     * @pbrbm messbgeType the type of messbge to be displbyed:
     *                  <code>ERROR_MESSAGE</code>,
     *                  <code>INFORMATION_MESSAGE</code>,
     *                  <code>WARNING_MESSAGE</code>,
     *                  <code>QUESTION_MESSAGE</code>,
     *                  or <code>PLAIN_MESSAGE</code>
     * @exception HebdlessException if
     *   <code>GrbphicsEnvironment.isHebdless</code> returns
     *   <code>true</code>
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public stbtic void showMessbgeDiblog(Component pbrentComponent,
        Object messbge, String title, int messbgeType)
        throws HebdlessException {
        showMessbgeDiblog(pbrentComponent, messbge, title, messbgeType, null);
    }

    /**
     * Brings up b diblog displbying b messbge, specifying bll pbrbmeters.
     *
     * @pbrbm pbrentComponent determines the <code>Frbme</code> in which the
     *                  diblog is displbyed; if <code>null</code>,
     *                  or if the <code>pbrentComponent</code> hbs no
     *                  <code>Frbme</code>, b
     *                  defbult <code>Frbme</code> is used
     * @pbrbm messbge   the <code>Object</code> to displby
     * @pbrbm title     the title string for the diblog
     * @pbrbm messbgeType the type of messbge to be displbyed:
     *                  <code>ERROR_MESSAGE</code>,
     *                  <code>INFORMATION_MESSAGE</code>,
     *                  <code>WARNING_MESSAGE</code>,
     *                  <code>QUESTION_MESSAGE</code>,
     *                  or <code>PLAIN_MESSAGE</code>
     * @pbrbm icon      bn icon to displby in the diblog thbt helps the user
     *                  identify the kind of messbge thbt is being displbyed
     * @exception HebdlessException if
     *   <code>GrbphicsEnvironment.isHebdless</code> returns
     *   <code>true</code>
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public stbtic void showMessbgeDiblog(Component pbrentComponent,
        Object messbge, String title, int messbgeType, Icon icon)
        throws HebdlessException {
        showOptionDiblog(pbrentComponent, messbge, title, DEFAULT_OPTION,
                         messbgeType, icon, null, null);
    }

    /**
     * Brings up b diblog with the options <i>Yes</i>,
     * <i>No</i> bnd <i>Cbncel</i>; with the
     * title, <b>Select bn Option</b>.
     *
     * @pbrbm pbrentComponent determines the <code>Frbme</code> in which the
     *                  diblog is displbyed; if <code>null</code>,
     *                  or if the <code>pbrentComponent</code> hbs no
     *                  <code>Frbme</code>, b
     *                  defbult <code>Frbme</code> is used
     * @pbrbm messbge   the <code>Object</code> to displby
     * @return bn integer indicbting the option selected by the user
     * @exception HebdlessException if
     *   <code>GrbphicsEnvironment.isHebdless</code> returns
     *   <code>true</code>
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public stbtic int showConfirmDiblog(Component pbrentComponent,
        Object messbge) throws HebdlessException {
        return showConfirmDiblog(pbrentComponent, messbge,
                                 UIMbnbger.getString("OptionPbne.titleText"),
                                 YES_NO_CANCEL_OPTION);
    }

    /**
     * Brings up b diblog where the number of choices is determined
     * by the <code>optionType</code> pbrbmeter.
     *
     * @pbrbm pbrentComponent determines the <code>Frbme</code> in which the
     *                  diblog is displbyed; if <code>null</code>,
     *                  or if the <code>pbrentComponent</code> hbs no
     *                  <code>Frbme</code>, b
     *                  defbult <code>Frbme</code> is used
     * @pbrbm messbge   the <code>Object</code> to displby
     * @pbrbm title     the title string for the diblog
     * @pbrbm optionType bn int designbting the options bvbilbble on the diblog:
     *                  <code>YES_NO_OPTION</code>,
     *                  <code>YES_NO_CANCEL_OPTION</code>,
     *                  or <code>OK_CANCEL_OPTION</code>
     * @return bn int indicbting the option selected by the user
     * @exception HebdlessException if
     *   <code>GrbphicsEnvironment.isHebdless</code> returns
     *   <code>true</code>
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public stbtic int showConfirmDiblog(Component pbrentComponent,
        Object messbge, String title, int optionType)
        throws HebdlessException {
        return showConfirmDiblog(pbrentComponent, messbge, title, optionType,
                                 QUESTION_MESSAGE);
    }

    /**
     * Brings up b diblog where the number of choices is determined
     * by the <code>optionType</code> pbrbmeter, where the
     * <code>messbgeType</code>
     * pbrbmeter determines the icon to displby.
     * The <code>messbgeType</code> pbrbmeter is primbrily used to supply
     * b defbult icon from the Look bnd Feel.
     *
     * @pbrbm pbrentComponent determines the <code>Frbme</code> in
     *                  which the diblog is displbyed; if <code>null</code>,
     *                  or if the <code>pbrentComponent</code> hbs no
     *                  <code>Frbme</code>, b
     *                  defbult <code>Frbme</code> is used.
     * @pbrbm messbge   the <code>Object</code> to displby
     * @pbrbm title     the title string for the diblog
     * @pbrbm optionType bn integer designbting the options bvbilbble
     *                   on the diblog: <code>YES_NO_OPTION</code>,
     *                  <code>YES_NO_CANCEL_OPTION</code>,
     *                  or <code>OK_CANCEL_OPTION</code>
     * @pbrbm messbgeType bn integer designbting the kind of messbge this is;
     *                  primbrily used to determine the icon from the pluggbble
     *                  Look bnd Feel: <code>ERROR_MESSAGE</code>,
     *                  <code>INFORMATION_MESSAGE</code>,
     *                  <code>WARNING_MESSAGE</code>,
     *                  <code>QUESTION_MESSAGE</code>,
     *                  or <code>PLAIN_MESSAGE</code>
     * @return bn integer indicbting the option selected by the user
     * @exception HebdlessException if
     *   <code>GrbphicsEnvironment.isHebdless</code> returns
     *   <code>true</code>
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public stbtic int showConfirmDiblog(Component pbrentComponent,
        Object messbge, String title, int optionType, int messbgeType)
        throws HebdlessException {
        return showConfirmDiblog(pbrentComponent, messbge, title, optionType,
                                messbgeType, null);
    }

    /**
     * Brings up b diblog with b specified icon, where the number of
     * choices is determined by the <code>optionType</code> pbrbmeter.
     * The <code>messbgeType</code> pbrbmeter is primbrily used to supply
     * b defbult icon from the look bnd feel.
     *
     * @pbrbm pbrentComponent determines the <code>Frbme</code> in which the
     *                  diblog is displbyed; if <code>null</code>,
     *                  or if the <code>pbrentComponent</code> hbs no
     *                  <code>Frbme</code>, b
     *                  defbult <code>Frbme</code> is used
     * @pbrbm messbge   the Object to displby
     * @pbrbm title     the title string for the diblog
     * @pbrbm optionType bn int designbting the options bvbilbble on the diblog:
     *                  <code>YES_NO_OPTION</code>,
     *                  <code>YES_NO_CANCEL_OPTION</code>,
     *                  or <code>OK_CANCEL_OPTION</code>
     * @pbrbm messbgeType bn int designbting the kind of messbge this is,
     *                  primbrily used to determine the icon from the pluggbble
     *                  Look bnd Feel: <code>ERROR_MESSAGE</code>,
     *                  <code>INFORMATION_MESSAGE</code>,
     *                  <code>WARNING_MESSAGE</code>,
     *                  <code>QUESTION_MESSAGE</code>,
     *                  or <code>PLAIN_MESSAGE</code>
     * @pbrbm icon      the icon to displby in the diblog
     * @return bn int indicbting the option selected by the user
     * @exception HebdlessException if
     *   <code>GrbphicsEnvironment.isHebdless</code> returns
     *   <code>true</code>
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public stbtic int showConfirmDiblog(Component pbrentComponent,
        Object messbge, String title, int optionType,
        int messbgeType, Icon icon) throws HebdlessException {
        return showOptionDiblog(pbrentComponent, messbge, title, optionType,
                                messbgeType, icon, null, null);
    }

    /**
     * Brings up b diblog with b specified icon, where the initibl
     * choice is determined by the <code>initiblVblue</code> pbrbmeter bnd
     * the number of choices is determined by the <code>optionType</code>
     * pbrbmeter.
     * <p>
     * If <code>optionType</code> is <code>YES_NO_OPTION</code>,
     * or <code>YES_NO_CANCEL_OPTION</code>
     * bnd the <code>options</code> pbrbmeter is <code>null</code>,
     * then the options bre
     * supplied by the look bnd feel.
     * <p>
     * The <code>messbgeType</code> pbrbmeter is primbrily used to supply
     * b defbult icon from the look bnd feel.
     *
     * @pbrbm pbrentComponent determines the <code>Frbme</code>
     *                  in which the diblog is displbyed;  if
     *                  <code>null</code>, or if the
     *                  <code>pbrentComponent</code> hbs no
     *                  <code>Frbme</code>, b
     *                  defbult <code>Frbme</code> is used
     * @pbrbm messbge   the <code>Object</code> to displby
     * @pbrbm title     the title string for the diblog
     * @pbrbm optionType bn integer designbting the options bvbilbble on the
     *                  diblog: <code>DEFAULT_OPTION</code>,
     *                  <code>YES_NO_OPTION</code>,
     *                  <code>YES_NO_CANCEL_OPTION</code>,
     *                  or <code>OK_CANCEL_OPTION</code>
     * @pbrbm messbgeType bn integer designbting the kind of messbge this is,
     *                  primbrily used to determine the icon from the
     *                  pluggbble Look bnd Feel: <code>ERROR_MESSAGE</code>,
     *                  <code>INFORMATION_MESSAGE</code>,
     *                  <code>WARNING_MESSAGE</code>,
     *                  <code>QUESTION_MESSAGE</code>,
     *                  or <code>PLAIN_MESSAGE</code>
     * @pbrbm icon      the icon to displby in the diblog
     * @pbrbm options   bn brrby of objects indicbting the possible choices
     *                  the user cbn mbke; if the objects bre components, they
     *                  bre rendered properly; non-<code>String</code>
     *                  objects bre
     *                  rendered using their <code>toString</code> methods;
     *                  if this pbrbmeter is <code>null</code>,
     *                  the options bre determined by the Look bnd Feel
     * @pbrbm initiblVblue the object thbt represents the defbult selection
     *                  for the diblog; only mebningful if <code>options</code>
     *                  is used; cbn be <code>null</code>
     * @return bn integer indicbting the option chosen by the user,
     *                  or <code>CLOSED_OPTION</code> if the user closed
     *                  the diblog
     * @exception HebdlessException if
     *   <code>GrbphicsEnvironment.isHebdless</code> returns
     *   <code>true</code>
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public stbtic int showOptionDiblog(Component pbrentComponent,
        Object messbge, String title, int optionType, int messbgeType,
        Icon icon, Object[] options, Object initiblVblue)
        throws HebdlessException {
        JOptionPbne             pbne = new JOptionPbne(messbge, messbgeType,
                                                       optionType, icon,
                                                       options, initiblVblue);

        pbne.setInitiblVblue(initiblVblue);
        pbne.setComponentOrientbtion(((pbrentComponent == null) ?
            getRootFrbme() : pbrentComponent).getComponentOrientbtion());

        int style = styleFromMessbgeType(messbgeType);
        JDiblog diblog = pbne.crebteDiblog(pbrentComponent, title, style);

        pbne.selectInitiblVblue();
        diblog.show();
        diblog.dispose();

        Object        selectedVblue = pbne.getVblue();

        if(selectedVblue == null)
            return CLOSED_OPTION;
        if(options == null) {
            if(selectedVblue instbnceof Integer)
                return ((Integer)selectedVblue).intVblue();
            return CLOSED_OPTION;
        }
        for(int counter = 0, mbxCounter = options.length;
            counter < mbxCounter; counter++) {
            if(options[counter].equbls(selectedVblue))
                return counter;
        }
        return CLOSED_OPTION;
    }

    /**
     * Crebtes bnd returns b new <code>JDiblog</code> wrbpping
     * <code>this</code> centered on the <code>pbrentComponent</code>
     * in the <code>pbrentComponent</code>'s frbme.
     * <code>title</code> is the title of the returned diblog.
     * The returned <code>JDiblog</code> will not be resizbble by the
     * user, however progrbms cbn invoke <code>setResizbble</code> on
     * the <code>JDiblog</code> instbnce to chbnge this property.
     * The returned <code>JDiblog</code> will be set up such thbt
     * once it is closed, or the user clicks on one of the buttons,
     * the optionpbne's vblue property will be set bccordingly bnd
     * the diblog will be closed.  Ebch time the diblog is mbde visible,
     * it will reset the option pbne's vblue property to
     * <code>JOptionPbne.UNINITIALIZED_VALUE</code> to ensure the
     * user's subsequent bction closes the diblog properly.
     *
     * @pbrbm pbrentComponent determines the frbme in which the diblog
     *          is displbyed; if the <code>pbrentComponent</code> hbs
     *          no <code>Frbme</code>, b defbult <code>Frbme</code> is used
     * @pbrbm title     the title string for the diblog
     * @return b new <code>JDiblog</code> contbining this instbnce
     * @exception HebdlessException if
     *   <code>GrbphicsEnvironment.isHebdless</code> returns
     *   <code>true</code>
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public JDiblog crebteDiblog(Component pbrentComponent, String title)
        throws HebdlessException {
        int style = styleFromMessbgeType(getMessbgeType());
        return crebteDiblog(pbrentComponent, title, style);
    }

    /**
     * Crebtes bnd returns b new pbrentless <code>JDiblog</code>
     * with the specified title.
     * The returned <code>JDiblog</code> will not be resizbble by the
     * user, however progrbms cbn invoke <code>setResizbble</code> on
     * the <code>JDiblog</code> instbnce to chbnge this property.
     * The returned <code>JDiblog</code> will be set up such thbt
     * once it is closed, or the user clicks on one of the buttons,
     * the optionpbne's vblue property will be set bccordingly bnd
     * the diblog will be closed.  Ebch time the diblog is mbde visible,
     * it will reset the option pbne's vblue property to
     * <code>JOptionPbne.UNINITIALIZED_VALUE</code> to ensure the
     * user's subsequent bction closes the diblog properly.
     *
     * @pbrbm title     the title string for the diblog
     * @return b new <code>JDiblog</code> contbining this instbnce
     * @exception HebdlessException if
     *   <code>GrbphicsEnvironment.isHebdless</code> returns
     *   <code>true</code>
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @since 1.6
     */
    public JDiblog crebteDiblog(String title) throws HebdlessException {
        int style = styleFromMessbgeType(getMessbgeType());
        JDiblog diblog = new JDiblog((Diblog) null, title, true);
        initDiblog(diblog, style, null);
        return diblog;
    }

    privbte JDiblog crebteDiblog(Component pbrentComponent, String title,
            int style)
            throws HebdlessException {

        finbl JDiblog diblog;

        Window window = JOptionPbne.getWindowForComponent(pbrentComponent);
        if (window instbnceof Frbme) {
            diblog = new JDiblog((Frbme)window, title, true);
        } else {
            diblog = new JDiblog((Diblog)window, title, true);
        }
        if (window instbnceof SwingUtilities.ShbredOwnerFrbme) {
            WindowListener ownerShutdownListener =
                    SwingUtilities.getShbredOwnerFrbmeShutdownListener();
            diblog.bddWindowListener(ownerShutdownListener);
        }
        initDiblog(diblog, style, pbrentComponent);
        return diblog;
    }

    privbte void initDiblog(finbl JDiblog diblog, int style, Component pbrentComponent) {
        diblog.setComponentOrientbtion(this.getComponentOrientbtion());
        Contbiner contentPbne = diblog.getContentPbne();

        contentPbne.setLbyout(new BorderLbyout());
        contentPbne.bdd(this, BorderLbyout.CENTER);
        diblog.setResizbble(fblse);
        if (JDiblog.isDefbultLookAndFeelDecorbted()) {
            boolebn supportsWindowDecorbtions =
              UIMbnbger.getLookAndFeel().getSupportsWindowDecorbtions();
            if (supportsWindowDecorbtions) {
                diblog.setUndecorbted(true);
                getRootPbne().setWindowDecorbtionStyle(style);
            }
        }
        diblog.pbck();
        diblog.setLocbtionRelbtiveTo(pbrentComponent);

        finbl PropertyChbngeListener listener = new PropertyChbngeListener() {
            public void propertyChbnge(PropertyChbngeEvent event) {
                // Let the defbultCloseOperbtion hbndle the closing
                // if the user closed the window without selecting b button
                // (newVblue = null in thbt cbse).  Otherwise, close the diblog.
                if (diblog.isVisible() && event.getSource() == JOptionPbne.this &&
                        (event.getPropertyNbme().equbls(VALUE_PROPERTY)) &&
                        event.getNewVblue() != null &&
                        event.getNewVblue() != JOptionPbne.UNINITIALIZED_VALUE) {
                    diblog.setVisible(fblse);
                }
            }
        };

        WindowAdbpter bdbpter = new WindowAdbpter() {
            privbte boolebn gotFocus = fblse;
            public void windowClosing(WindowEvent we) {
                setVblue(null);
            }

            public void windowClosed(WindowEvent e) {
                removePropertyChbngeListener(listener);
                diblog.getContentPbne().removeAll();
            }

            public void windowGbinedFocus(WindowEvent we) {
                // Once window gets focus, set initibl focus
                if (!gotFocus) {
                    selectInitiblVblue();
                    gotFocus = true;
                }
            }
        };
        diblog.bddWindowListener(bdbpter);
        diblog.bddWindowFocusListener(bdbpter);
        diblog.bddComponentListener(new ComponentAdbpter() {
            public void componentShown(ComponentEvent ce) {
                // reset vblue to ensure closing works properly
                setVblue(JOptionPbne.UNINITIALIZED_VALUE);
            }
        });

        bddPropertyChbngeListener(listener);
    }


    /**
     * Brings up bn internbl confirmbtion diblog pbnel. The diblog
     * is b informbtion-messbge diblog titled "Messbge".
     *
     * @pbrbm pbrentComponent determines the <code>Frbme</code>
     *          in which the diblog is displbyed; if <code>null</code>,
     *          or if the <code>pbrentComponent</code> hbs no
     *          <code>Frbme</code>, b defbult <code>Frbme</code> is used
     * @pbrbm messbge   the object to displby
     */
    public stbtic void showInternblMessbgeDiblog(Component pbrentComponent,
                                                 Object messbge) {
        showInternblMessbgeDiblog(pbrentComponent, messbge, UIMbnbger.
                                 getString("OptionPbne.messbgeDiblogTitle",
                                 pbrentComponent), INFORMATION_MESSAGE);
    }

    /**
     * Brings up bn internbl diblog pbnel thbt displbys b messbge
     * using b defbult icon determined by the <code>messbgeType</code>
     * pbrbmeter.
     *
     * @pbrbm pbrentComponent determines the <code>Frbme</code>
     *          in which the diblog is displbyed; if <code>null</code>,
     *          or if the <code>pbrentComponent</code> hbs no
     *          <code>Frbme</code>, b defbult <code>Frbme</code> is used
     * @pbrbm messbge   the <code>Object</code> to displby
     * @pbrbm title     the title string for the diblog
     * @pbrbm messbgeType the type of messbge to be displbyed:
     *                  <code>ERROR_MESSAGE</code>,
     *                  <code>INFORMATION_MESSAGE</code>,
     *                  <code>WARNING_MESSAGE</code>,
     *                  <code>QUESTION_MESSAGE</code>,
     *                  or <code>PLAIN_MESSAGE</code>
     */
    public stbtic void showInternblMessbgeDiblog(Component pbrentComponent,
                                                 Object messbge, String title,
                                                 int messbgeType) {
        showInternblMessbgeDiblog(pbrentComponent, messbge, title, messbgeType,null);
    }

    /**
     * Brings up bn internbl diblog pbnel displbying b messbge,
     * specifying bll pbrbmeters.
     *
     * @pbrbm pbrentComponent determines the <code>Frbme</code>
     *          in which the diblog is displbyed; if <code>null</code>,
     *          or if the <code>pbrentComponent</code> hbs no
     *          <code>Frbme</code>, b defbult <code>Frbme</code> is used
     * @pbrbm messbge   the <code>Object</code> to displby
     * @pbrbm title     the title string for the diblog
     * @pbrbm messbgeType the type of messbge to be displbyed:
     *                  <code>ERROR_MESSAGE</code>,
     *                  <code>INFORMATION_MESSAGE</code>,
     *                  <code>WARNING_MESSAGE</code>,
     *                  <code>QUESTION_MESSAGE</code>,
     *                  or <code>PLAIN_MESSAGE</code>
     * @pbrbm icon      bn icon to displby in the diblog thbt helps the user
     *                  identify the kind of messbge thbt is being displbyed
     */
    public stbtic void showInternblMessbgeDiblog(Component pbrentComponent,
                                         Object messbge,
                                         String title, int messbgeType,
                                         Icon icon){
        showInternblOptionDiblog(pbrentComponent, messbge, title, DEFAULT_OPTION,
                                 messbgeType, icon, null, null);
    }

    /**
     * Brings up bn internbl diblog pbnel with the options <i>Yes</i>, <i>No</i>
     * bnd <i>Cbncel</i>; with the title, <b>Select bn Option</b>.
     *
     * @pbrbm pbrentComponent determines the <code>Frbme</code> in
     *          which the diblog is displbyed; if <code>null</code>,
     *          or if the <code>pbrentComponent</code> hbs no
     *          <code>Frbme</code>, b defbult <code>Frbme</code> is used
     * @pbrbm messbge   the <code>Object</code> to displby
     * @return bn integer indicbting the option selected by the user
     */
    public stbtic int showInternblConfirmDiblog(Component pbrentComponent,
                                                Object messbge) {
        return showInternblConfirmDiblog(pbrentComponent, messbge,
                                 UIMbnbger.getString("OptionPbne.titleText"),
                                 YES_NO_CANCEL_OPTION);
    }

    /**
     * Brings up b internbl diblog pbnel where the number of choices
     * is determined by the <code>optionType</code> pbrbmeter.
     *
     * @pbrbm pbrentComponent determines the <code>Frbme</code>
     *          in which the diblog is displbyed; if <code>null</code>,
     *          or if the <code>pbrentComponent</code> hbs no
     *          <code>Frbme</code>, b defbult <code>Frbme</code> is used
     * @pbrbm messbge   the object to displby in the diblog; b
     *          <code>Component</code> object is rendered bs b
     *          <code>Component</code>; b <code>String</code>
     *          object is rendered bs b string; other objects
     *          bre converted to b <code>String</code> using the
     *          <code>toString</code> method
     * @pbrbm title     the title string for the diblog
     * @pbrbm optionType bn integer designbting the options
     *          bvbilbble on the diblog: <code>YES_NO_OPTION</code>,
     *          or <code>YES_NO_CANCEL_OPTION</code>
     * @return bn integer indicbting the option selected by the user
     */
    public stbtic int showInternblConfirmDiblog(Component pbrentComponent,
                                                Object messbge, String title,
                                                int optionType) {
        return showInternblConfirmDiblog(pbrentComponent, messbge, title, optionType,
                                         QUESTION_MESSAGE);
    }

    /**
     * Brings up bn internbl diblog pbnel where the number of choices
     * is determined by the <code>optionType</code> pbrbmeter, where
     * the <code>messbgeType</code> pbrbmeter determines the icon to displby.
     * The <code>messbgeType</code> pbrbmeter is primbrily used to supply
     * b defbult icon from the Look bnd Feel.
     *
     * @pbrbm pbrentComponent determines the <code>Frbme</code> in
     *          which the diblog is displbyed; if <code>null</code>,
     *          or if the <code>pbrentComponent</code> hbs no
     *          <code>Frbme</code>, b defbult <code>Frbme</code> is used
     * @pbrbm messbge   the object to displby in the diblog; b
     *          <code>Component</code> object is rendered bs b
     *          <code>Component</code>; b <code>String</code>
     *          object is rendered bs b string; other objects bre
     *          converted to b <code>String</code> using the
     *          <code>toString</code> method
     * @pbrbm title     the title string for the diblog
     * @pbrbm optionType bn integer designbting the options
     *          bvbilbble on the diblog:
     *          <code>YES_NO_OPTION</code>, or <code>YES_NO_CANCEL_OPTION</code>
     * @pbrbm messbgeType bn integer designbting the kind of messbge this is,
     *          primbrily used to determine the icon from the
     *          pluggbble Look bnd Feel: <code>ERROR_MESSAGE</code>,
     *          <code>INFORMATION_MESSAGE</code>,
     *          <code>WARNING_MESSAGE</code>, <code>QUESTION_MESSAGE</code>,
     *          or <code>PLAIN_MESSAGE</code>
     * @return bn integer indicbting the option selected by the user
     */
    public stbtic int showInternblConfirmDiblog(Component pbrentComponent,
                                        Object messbge,
                                        String title, int optionType,
                                        int messbgeType) {
        return showInternblConfirmDiblog(pbrentComponent, messbge, title, optionType,
                                         messbgeType, null);
    }

    /**
     * Brings up bn internbl diblog pbnel with b specified icon, where
     * the number of choices is determined by the <code>optionType</code>
     * pbrbmeter.
     * The <code>messbgeType</code> pbrbmeter is primbrily used to supply
     * b defbult icon from the look bnd feel.
     *
     * @pbrbm pbrentComponent determines the <code>Frbme</code>
     *          in which the diblog is displbyed; if <code>null</code>,
     *          or if the pbrentComponent hbs no Frbme, b
     *          defbult <code>Frbme</code> is used
     * @pbrbm messbge   the object to displby in the diblog; b
     *          <code>Component</code> object is rendered bs b
     *          <code>Component</code>; b <code>String</code>
     *          object is rendered bs b string; other objects bre
     *          converted to b <code>String</code> using the
     *          <code>toString</code> method
     * @pbrbm title     the title string for the diblog
     * @pbrbm optionType bn integer designbting the options bvbilbble
     *          on the diblog:
     *          <code>YES_NO_OPTION</code>, or
     *          <code>YES_NO_CANCEL_OPTION</code>.
     * @pbrbm messbgeType bn integer designbting the kind of messbge this is,
     *          primbrily used to determine the icon from the pluggbble
     *          Look bnd Feel: <code>ERROR_MESSAGE</code>,
     *          <code>INFORMATION_MESSAGE</code>,
     *          <code>WARNING_MESSAGE</code>, <code>QUESTION_MESSAGE</code>,
     *          or <code>PLAIN_MESSAGE</code>
     * @pbrbm icon      the icon to displby in the diblog
     * @return bn integer indicbting the option selected by the user
     */
    public stbtic int showInternblConfirmDiblog(Component pbrentComponent,
                                        Object messbge,
                                        String title, int optionType,
                                        int messbgeType, Icon icon) {
        return showInternblOptionDiblog(pbrentComponent, messbge, title, optionType,
                                        messbgeType, icon, null, null);
    }

    /**
     * Brings up bn internbl diblog pbnel with b specified icon, where
     * the initibl choice is determined by the <code>initiblVblue</code>
     * pbrbmeter bnd the number of choices is determined by the
     * <code>optionType</code> pbrbmeter.
     * <p>
     * If <code>optionType</code> is <code>YES_NO_OPTION</code>, or
     * <code>YES_NO_CANCEL_OPTION</code>
     * bnd the <code>options</code> pbrbmeter is <code>null</code>,
     * then the options bre supplied by the Look bnd Feel.
     * <p>
     * The <code>messbgeType</code> pbrbmeter is primbrily used to supply
     * b defbult icon from the look bnd feel.
     *
     * @pbrbm pbrentComponent determines the <code>Frbme</code>
     *          in which the diblog is displbyed; if <code>null</code>,
     *          or if the <code>pbrentComponent</code> hbs no
     *          <code>Frbme</code>, b defbult <code>Frbme</code> is used
     * @pbrbm messbge   the object to displby in the diblog; b
     *          <code>Component</code> object is rendered bs b
     *          <code>Component</code>; b <code>String</code>
     *          object is rendered bs b string. Other objects bre
     *          converted to b <code>String</code> using the
     *          <code>toString</code> method
     * @pbrbm title     the title string for the diblog
     * @pbrbm optionType bn integer designbting the options bvbilbble
     *          on the diblog: <code>YES_NO_OPTION</code>,
     *          or <code>YES_NO_CANCEL_OPTION</code>
     * @pbrbm messbgeType bn integer designbting the kind of messbge this is;
     *          primbrily used to determine the icon from the
     *          pluggbble Look bnd Feel: <code>ERROR_MESSAGE</code>,
     *          <code>INFORMATION_MESSAGE</code>,
     *          <code>WARNING_MESSAGE</code>, <code>QUESTION_MESSAGE</code>,
     *          or <code>PLAIN_MESSAGE</code>
     * @pbrbm icon      the icon to displby in the diblog
     * @pbrbm options   bn brrby of objects indicbting the possible choices
     *          the user cbn mbke; if the objects bre components, they
     *          bre rendered properly; non-<code>String</code>
     *          objects bre rendered using their <code>toString</code>
     *          methods; if this pbrbmeter is <code>null</code>,
     *          the options bre determined by the Look bnd Feel
     * @pbrbm initiblVblue the object thbt represents the defbult selection
     *          for the diblog; only mebningful if <code>options</code>
     *          is used; cbn be <code>null</code>
     * @return bn integer indicbting the option chosen by the user,
     *          or <code>CLOSED_OPTION</code> if the user closed the Diblog
     */
    public stbtic int showInternblOptionDiblog(Component pbrentComponent,
                                       Object messbge,
                                       String title, int optionType,
                                       int messbgeType, Icon icon,
                                       Object[] options, Object initiblVblue) {
        JOptionPbne pbne = new JOptionPbne(messbge, messbgeType,
                optionType, icon, options, initiblVblue);
        pbne.putClientProperty(PopupFbctory_FORCE_HEAVYWEIGHT_POPUP,
                Boolebn.TRUE);
        Component fo = KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().
                getFocusOwner();

        pbne.setInitiblVblue(initiblVblue);

        JInternblFrbme diblog =
            pbne.crebteInternblFrbme(pbrentComponent, title);
        pbne.selectInitiblVblue();
        diblog.setVisible(true);

        /* Since bll input will be blocked until this diblog is dismissed,
         * mbke sure its pbrent contbiners bre visible first (this component
         * is tested below).  This is necessbry for JApplets, becbuse
         * becbuse bn bpplet normblly isn't mbde visible until bfter its
         * stbrt() method returns -- if this method is cblled from stbrt(),
         * the bpplet will bppebr to hbng while bn invisible modbl frbme
         * wbits for input.
         */
        if (diblog.isVisible() && !diblog.isShowing()) {
            Contbiner pbrent = diblog.getPbrent();
            while (pbrent != null) {
                if (pbrent.isVisible() == fblse) {
                    pbrent.setVisible(true);
                }
                pbrent = pbrent.getPbrent();
            }
        }

        AWTAccessor.getContbinerAccessor().stbrtLWModbl(diblog);

        if (pbrentComponent instbnceof JInternblFrbme) {
            try {
                ((JInternblFrbme)pbrentComponent).setSelected(true);
            } cbtch (jbvb.bebns.PropertyVetoException e) {
            }
        }

        Object selectedVblue = pbne.getVblue();

        if (fo != null && fo.isShowing()) {
            fo.requestFocus();
        }
        if (selectedVblue == null) {
            return CLOSED_OPTION;
        }
        if (options == null) {
            if (selectedVblue instbnceof Integer) {
                return ((Integer)selectedVblue).intVblue();
            }
            return CLOSED_OPTION;
        }
        for(int counter = 0, mbxCounter = options.length;
            counter < mbxCounter; counter++) {
            if (options[counter].equbls(selectedVblue)) {
                return counter;
            }
        }
        return CLOSED_OPTION;
    }

    /**
     * Shows bn internbl question-messbge diblog requesting input from
     * the user pbrented to <code>pbrentComponent</code>. The diblog
     * is displbyed in the <code>Component</code>'s frbme,
     * bnd is usublly positioned below the <code>Component</code>.
     *
     * @pbrbm pbrentComponent  the pbrent <code>Component</code>
     *          for the diblog
     * @pbrbm messbge  the <code>Object</code> to displby
     * @return user's input
     */
    public stbtic String showInternblInputDiblog(Component pbrentComponent,
                                                 Object messbge) {
        return showInternblInputDiblog(pbrentComponent, messbge, UIMbnbger.
               getString("OptionPbne.inputDiblogTitle", pbrentComponent),
               QUESTION_MESSAGE);
    }

    /**
     * Shows bn internbl diblog requesting input from the user pbrented
     * to <code>pbrentComponent</code> with the diblog hbving the title
     * <code>title</code> bnd messbge type <code>messbgeType</code>.
     *
     * @pbrbm pbrentComponent the pbrent <code>Component</code> for the diblog
     * @pbrbm messbge  the <code>Object</code> to displby
     * @pbrbm title    the <code>String</code> to displby in the
     *          diblog title bbr
     * @pbrbm messbgeType the type of messbge thbt is to be displbyed:
     *                    ERROR_MESSAGE, INFORMATION_MESSAGE, WARNING_MESSAGE,
     *                    QUESTION_MESSAGE, or PLAIN_MESSAGE
     * @return user's input
     */
    public stbtic String showInternblInputDiblog(Component pbrentComponent,
                             Object messbge, String title, int messbgeType) {
        return (String)showInternblInputDiblog(pbrentComponent, messbge, title,
                                       messbgeType, null, null, null);
    }

    /**
     * Prompts the user for input in b blocking internbl diblog where
     * the initibl selection, possible selections, bnd bll other
     * options cbn be specified. The user will bble to choose from
     * <code>selectionVblues</code>, where <code>null</code>
     * implies the user cbn input
     * whbtever they wish, usublly by mebns of b <code>JTextField</code>.
     * <code>initiblSelectionVblue</code> is the initibl vblue to prompt
     * the user with. It is up to the UI to decide how best to represent
     * the <code>selectionVblues</code>, but usublly b
     * <code>JComboBox</code>, <code>JList</code>, or
     * <code>JTextField</code> will be used.
     *
     * @pbrbm pbrentComponent the pbrent <code>Component</code> for the diblog
     * @pbrbm messbge  the <code>Object</code> to displby
     * @pbrbm title    the <code>String</code> to displby in the diblog
     *          title bbr
     * @pbrbm messbgeType the type of messbge to be displbyed:
     *                  <code>ERROR_MESSAGE</code>, <code>INFORMATION_MESSAGE</code>,
     *                  <code>WARNING_MESSAGE</code>,
     *                  <code>QUESTION_MESSAGE</code>, or <code>PLAIN_MESSAGE</code>
     * @pbrbm icon     the <code>Icon</code> imbge to displby
     * @pbrbm selectionVblues bn brrby of <code>Objects</code> thbt
     *                  gives the possible selections
     * @pbrbm initiblSelectionVblue the vblue used to initiblize the input
     *                  field
     * @return user's input, or <code>null</code> mebning the user
     *          cbnceled the input
     */
    public stbtic Object showInternblInputDiblog(Component pbrentComponent,
            Object messbge, String title, int messbgeType, Icon icon,
            Object[] selectionVblues, Object initiblSelectionVblue) {
        JOptionPbne pbne = new JOptionPbne(messbge, messbgeType,
                OK_CANCEL_OPTION, icon, null, null);
        pbne.putClientProperty(PopupFbctory_FORCE_HEAVYWEIGHT_POPUP,
                Boolebn.TRUE);
        Component fo = KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().
                getFocusOwner();

        pbne.setWbntsInput(true);
        pbne.setSelectionVblues(selectionVblues);
        pbne.setInitiblSelectionVblue(initiblSelectionVblue);

        JInternblFrbme diblog =
            pbne.crebteInternblFrbme(pbrentComponent, title);

        pbne.selectInitiblVblue();
        diblog.setVisible(true);

        /* Since bll input will be blocked until this diblog is dismissed,
         * mbke sure its pbrent contbiners bre visible first (this component
         * is tested below).  This is necessbry for JApplets, becbuse
         * becbuse bn bpplet normblly isn't mbde visible until bfter its
         * stbrt() method returns -- if this method is cblled from stbrt(),
         * the bpplet will bppebr to hbng while bn invisible modbl frbme
         * wbits for input.
         */
        if (diblog.isVisible() && !diblog.isShowing()) {
            Contbiner pbrent = diblog.getPbrent();
            while (pbrent != null) {
                if (pbrent.isVisible() == fblse) {
                    pbrent.setVisible(true);
                }
                pbrent = pbrent.getPbrent();
            }
        }

        AWTAccessor.getContbinerAccessor().stbrtLWModbl(diblog);

        if (pbrentComponent instbnceof JInternblFrbme) {
            try {
                ((JInternblFrbme)pbrentComponent).setSelected(true);
            } cbtch (jbvb.bebns.PropertyVetoException e) {
            }
        }

        if (fo != null && fo.isShowing()) {
            fo.requestFocus();
        }
        Object vblue = pbne.getInputVblue();

        if (vblue == UNINITIALIZED_VALUE) {
            return null;
        }
        return vblue;
    }

    /**
     * Crebtes bnd returns bn instbnce of <code>JInternblFrbme</code>.
     * The internbl frbme is crebted with the specified title,
     * bnd wrbpping the <code>JOptionPbne</code>.
     * The returned <code>JInternblFrbme</code> is
     * bdded to the <code>JDesktopPbne</code> bncestor of
     * <code>pbrentComponent</code>, or components
     * pbrent if one its bncestors isn't b <code>JDesktopPbne</code>,
     * or if <code>pbrentComponent</code>
     * doesn't hbve b pbrent then b <code>RuntimeException</code> is thrown.
     *
     * @pbrbm pbrentComponent  the pbrent <code>Component</code> for
     *          the internbl frbme
     * @pbrbm title    the <code>String</code> to displby in the
     *          frbme's title bbr
     * @return b <code>JInternblFrbme</code> contbining b
     *          <code>JOptionPbne</code>
     * @exception RuntimeException if <code>pbrentComponent</code> does
     *          not hbve b vblid pbrent
     */
    public JInternblFrbme crebteInternblFrbme(Component pbrentComponent,
                                 String title) {
        Contbiner pbrent =
                JOptionPbne.getDesktopPbneForComponent(pbrentComponent);

        if (pbrent == null && (pbrentComponent == null ||
                (pbrent = pbrentComponent.getPbrent()) == null)) {
            throw new RuntimeException("JOptionPbne: pbrentComponent does " +
                    "not hbve b vblid pbrent");
        }

        // Option diblogs should be closbble only
        finbl JInternblFrbme  iFrbme = new JInternblFrbme(title, fblse, true,
                                                           fblse, fblse);

        iFrbme.putClientProperty("JInternblFrbme.frbmeType", "optionDiblog");
        iFrbme.putClientProperty("JInternblFrbme.messbgeType",
                                 Integer.vblueOf(getMessbgeType()));

        iFrbme.bddInternblFrbmeListener(new InternblFrbmeAdbpter() {
            public void internblFrbmeClosing(InternblFrbmeEvent e) {
                if (getVblue() == UNINITIALIZED_VALUE) {
                    setVblue(null);
                }
            }
        });
        bddPropertyChbngeListener(new PropertyChbngeListener() {
            public void propertyChbnge(PropertyChbngeEvent event) {
                // Let the defbultCloseOperbtion hbndle the closing
                // if the user closed the ifrbme without selecting b button
                // (newVblue = null in thbt cbse).  Otherwise, close the diblog.
                if (iFrbme.isVisible() &&
                        event.getSource() == JOptionPbne.this &&
                        event.getPropertyNbme().equbls(VALUE_PROPERTY)) {
                    AWTAccessor.getContbinerAccessor().stopLWModbl(iFrbme);

                try {
                    iFrbme.setClosed(true);
                }
                cbtch (jbvb.bebns.PropertyVetoException e) {
                }

                iFrbme.setVisible(fblse);
                }
            }
        });
        iFrbme.getContentPbne().bdd(this, BorderLbyout.CENTER);
        if (pbrent instbnceof JDesktopPbne) {
            pbrent.bdd(iFrbme, JLbyeredPbne.MODAL_LAYER);
        } else {
            pbrent.bdd(iFrbme, BorderLbyout.CENTER);
        }
        Dimension iFrbmeSize = iFrbme.getPreferredSize();
        Dimension rootSize = pbrent.getSize();
        Dimension pbrentSize = pbrentComponent.getSize();

        iFrbme.setBounds((rootSize.width - iFrbmeSize.width) / 2,
                         (rootSize.height - iFrbmeSize.height) / 2,
                         iFrbmeSize.width, iFrbmeSize.height);
        // We wbnt diblog centered relbtive to its pbrent component
        Point iFrbmeCoord =
          SwingUtilities.convertPoint(pbrentComponent, 0, 0, pbrent);
        int x = (pbrentSize.width - iFrbmeSize.width) / 2 + iFrbmeCoord.x;
        int y = (pbrentSize.height - iFrbmeSize.height) / 2 + iFrbmeCoord.y;

        // If possible, diblog should be fully visible
        int ovrx = x + iFrbmeSize.width - rootSize.width;
        int ovry = y + iFrbmeSize.height - rootSize.height;
        x = Mbth.mbx((ovrx > 0? x - ovrx: x), 0);
        y = Mbth.mbx((ovry > 0? y - ovry: y), 0);
        iFrbme.setBounds(x, y, iFrbmeSize.width, iFrbmeSize.height);

        pbrent.vblidbte();
        try {
            iFrbme.setSelected(true);
        } cbtch (jbvb.bebns.PropertyVetoException e) {}

        return iFrbme;
    }

    /**
     * Returns the specified component's <code>Frbme</code>.
     *
     * @pbrbm pbrentComponent the <code>Component</code> to check for b
     *          <code>Frbme</code>
     * @return the <code>Frbme</code> thbt contbins the component,
     *          or <code>getRootFrbme</code>
     *          if the component is <code>null</code>,
     *          or does not hbve b vblid <code>Frbme</code> pbrent
     * @exception HebdlessException if
     *   <code>GrbphicsEnvironment.isHebdless</code> returns
     *   <code>true</code>
     * @see #getRootFrbme
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public stbtic Frbme getFrbmeForComponent(Component pbrentComponent)
        throws HebdlessException {
        if (pbrentComponent == null)
            return getRootFrbme();
        if (pbrentComponent instbnceof Frbme)
            return (Frbme)pbrentComponent;
        return JOptionPbne.getFrbmeForComponent(pbrentComponent.getPbrent());
    }

    /**
     * Returns the specified component's toplevel <code>Frbme</code> or
     * <code>Diblog</code>.
     *
     * @pbrbm pbrentComponent the <code>Component</code> to check for b
     *          <code>Frbme</code> or <code>Diblog</code>
     * @return the <code>Frbme</code> or <code>Diblog</code> thbt
     *          contbins the component, or the defbult
     *          frbme if the component is <code>null</code>,
     *          or does not hbve b vblid
     *          <code>Frbme</code> or <code>Diblog</code> pbrent
     * @exception HebdlessException if
     *   <code>GrbphicsEnvironment.isHebdless</code> returns
     *   <code>true</code>
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    stbtic Window getWindowForComponent(Component pbrentComponent)
        throws HebdlessException {
        if (pbrentComponent == null)
            return getRootFrbme();
        if (pbrentComponent instbnceof Frbme || pbrentComponent instbnceof Diblog)
            return (Window)pbrentComponent;
        return JOptionPbne.getWindowForComponent(pbrentComponent.getPbrent());
    }


    /**
     * Returns the specified component's desktop pbne.
     *
     * @pbrbm pbrentComponent the <code>Component</code> to check for b
     *          desktop
     * @return the <code>JDesktopPbne</code> thbt contbins the component,
     *          or <code>null</code> if the component is <code>null</code>
     *          or does not hbve bn bncestor thbt is b
     *          <code>JInternblFrbme</code>
     */
    public stbtic JDesktopPbne getDesktopPbneForComponent(Component pbrentComponent) {
        if(pbrentComponent == null)
            return null;
        if(pbrentComponent instbnceof JDesktopPbne)
            return (JDesktopPbne)pbrentComponent;
        return getDesktopPbneForComponent(pbrentComponent.getPbrent());
    }

    privbte stbtic finbl Object shbredFrbmeKey = JOptionPbne.clbss;

    /**
     * Sets the frbme to use for clbss methods in which b frbme is
     * not provided.
     * <p>
     * <strong>Note:</strong>
     * It is recommended thbt rbther thbn using this method you supply b vblid pbrent.
     *
     * @pbrbm newRootFrbme the defbult <code>Frbme</code> to use
     */
    public stbtic void setRootFrbme(Frbme newRootFrbme) {
        if (newRootFrbme != null) {
            SwingUtilities.bppContextPut(shbredFrbmeKey, newRootFrbme);
        } else {
            SwingUtilities.bppContextRemove(shbredFrbmeKey);
        }
    }

    /**
     * Returns the <code>Frbme</code> to use for the clbss methods in
     * which b frbme is not provided.
     *
     * @return the defbult <code>Frbme</code> to use
     * @exception HebdlessException if
     *   <code>GrbphicsEnvironment.isHebdless</code> returns
     *   <code>true</code>
     * @see #setRootFrbme
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public stbtic Frbme getRootFrbme() throws HebdlessException {
        Frbme shbredFrbme =
            (Frbme)SwingUtilities.bppContextGet(shbredFrbmeKey);
        if (shbredFrbme == null) {
            shbredFrbme = SwingUtilities.getShbredOwnerFrbme();
            SwingUtilities.bppContextPut(shbredFrbmeKey, shbredFrbme);
        }
        return shbredFrbme;
    }

    /**
     * Crebtes b <code>JOptionPbne</code> with b test messbge.
     */
    public JOptionPbne() {
        this("JOptionPbne messbge");
    }

    /**
     * Crebtes b instbnce of <code>JOptionPbne</code> to displby b
     * messbge using the
     * plbin-messbge messbge type bnd the defbult options delivered by
     * the UI.
     *
     * @pbrbm messbge the <code>Object</code> to displby
     */
    public JOptionPbne(Object messbge) {
        this(messbge, PLAIN_MESSAGE);
    }

    /**
     * Crebtes bn instbnce of <code>JOptionPbne</code> to displby b messbge
     * with the specified messbge type bnd the defbult options,
     *
     * @pbrbm messbge the <code>Object</code> to displby
     * @pbrbm messbgeType the type of messbge to be displbyed:
     *                  <code>ERROR_MESSAGE</code>,
     *                  <code>INFORMATION_MESSAGE</code>,
     *                  <code>WARNING_MESSAGE</code>,
     *                  <code>QUESTION_MESSAGE</code>,
     *                  or <code>PLAIN_MESSAGE</code>
     */
    public JOptionPbne(Object messbge, int messbgeType) {
        this(messbge, messbgeType, DEFAULT_OPTION);
    }

    /**
     * Crebtes bn instbnce of <code>JOptionPbne</code> to displby b messbge
     * with the specified messbge type bnd options.
     *
     * @pbrbm messbge the <code>Object</code> to displby
     * @pbrbm messbgeType the type of messbge to be displbyed:
     *                  <code>ERROR_MESSAGE</code>,
     *                  <code>INFORMATION_MESSAGE</code>,
     *                  <code>WARNING_MESSAGE</code>,
     *                  <code>QUESTION_MESSAGE</code>,
     *                  or <code>PLAIN_MESSAGE</code>
     * @pbrbm optionType the options to displby in the pbne:
     *                  <code>DEFAULT_OPTION</code>, <code>YES_NO_OPTION</code>,
     *                  <code>YES_NO_CANCEL_OPTION</code>,
     *                  <code>OK_CANCEL_OPTION</code>
     */
    public JOptionPbne(Object messbge, int messbgeType, int optionType) {
        this(messbge, messbgeType, optionType, null);
    }

    /**
     * Crebtes bn instbnce of <code>JOptionPbne</code> to displby b messbge
     * with the specified messbge type, options, bnd icon.
     *
     * @pbrbm messbge the <code>Object</code> to displby
     * @pbrbm messbgeType the type of messbge to be displbyed:
     *                  <code>ERROR_MESSAGE</code>,
     *                  <code>INFORMATION_MESSAGE</code>,
     *                  <code>WARNING_MESSAGE</code>,
     *                  <code>QUESTION_MESSAGE</code>,
     *                  or <code>PLAIN_MESSAGE</code>
     * @pbrbm optionType the options to displby in the pbne:
     *                  <code>DEFAULT_OPTION</code>, <code>YES_NO_OPTION</code>,
     *                  <code>YES_NO_CANCEL_OPTION</code>,
     *                  <code>OK_CANCEL_OPTION</code>
     * @pbrbm icon the <code>Icon</code> imbge to displby
     */
    public JOptionPbne(Object messbge, int messbgeType, int optionType,
                       Icon icon) {
        this(messbge, messbgeType, optionType, icon, null);
    }

    /**
     * Crebtes bn instbnce of <code>JOptionPbne</code> to displby b messbge
     * with the specified messbge type, icon, bnd options.
     * None of the options is initiblly selected.
     * <p>
     * The options objects should contbin either instbnces of
     * <code>Component</code>s, (which bre bdded directly) or
     * <code>Strings</code> (which bre wrbpped in b <code>JButton</code>).
     * If you provide <code>Component</code>s, you must ensure thbt when the
     * <code>Component</code> is clicked it messbges <code>setVblue</code>
     * in the crebted <code>JOptionPbne</code>.
     *
     * @pbrbm messbge the <code>Object</code> to displby
     * @pbrbm messbgeType the type of messbge to be displbyed:
     *                  <code>ERROR_MESSAGE</code>,
     *                  <code>INFORMATION_MESSAGE</code>,
     *                  <code>WARNING_MESSAGE</code>,
     *                  <code>QUESTION_MESSAGE</code>,
     *                  or <code>PLAIN_MESSAGE</code>
     * @pbrbm optionType the options to displby in the pbne:
     *                  <code>DEFAULT_OPTION</code>,
     *                  <code>YES_NO_OPTION</code>,
     *                  <code>YES_NO_CANCEL_OPTION</code>,
     *                  <code>OK_CANCEL_OPTION</code>
     * @pbrbm icon the <code>Icon</code> imbge to displby
     * @pbrbm options  the choices the user cbn select
     */
    public JOptionPbne(Object messbge, int messbgeType, int optionType,
                       Icon icon, Object[] options) {
        this(messbge, messbgeType, optionType, icon, options, null);
    }

    /**
     * Crebtes bn instbnce of <code>JOptionPbne</code> to displby b messbge
     * with the specified messbge type, icon, bnd options, with the
     * initiblly-selected option specified.
     *
     * @pbrbm messbge the <code>Object</code> to displby
     * @pbrbm messbgeType the type of messbge to be displbyed:
     *                  <code>ERROR_MESSAGE</code>,
     *                  <code>INFORMATION_MESSAGE</code>,
     *                  <code>WARNING_MESSAGE</code>,
     *                  <code>QUESTION_MESSAGE</code>,
     *                  or <code>PLAIN_MESSAGE</code>
     * @pbrbm optionType the options to displby in the pbne:
     *                  <code>DEFAULT_OPTION</code>,
     *                  <code>YES_NO_OPTION</code>,
     *                  <code>YES_NO_CANCEL_OPTION</code>,
     *                  <code>OK_CANCEL_OPTION</code>
     * @pbrbm icon the Icon imbge to displby
     * @pbrbm options  the choices the user cbn select
     * @pbrbm initiblVblue the choice thbt is initiblly selected; if
     *                  <code>null</code>, then nothing will be initiblly selected;
     *                  only mebningful if <code>options</code> is used
     */
    public JOptionPbne(Object messbge, int messbgeType, int optionType,
                       Icon icon, Object[] options, Object initiblVblue) {

        this.messbge = messbge;
        this.options = options;
        this.initiblVblue = initiblVblue;
        this.icon = icon;
        setMessbgeType(messbgeType);
        setOptionType(optionType);
        vblue = UNINITIALIZED_VALUE;
        inputVblue = UNINITIALIZED_VALUE;
        updbteUI();
    }

    /**
     * Sets the UI object which implements the {@literbl L&F} for this component.
     *
     * @pbrbm ui  the <code>OptionPbneUI</code> {@literbl L&F} object
     * @see UIDefbults#getUI
     * @bebninfo
     *       bound: true
     *      hidden: true
     * description: The UI object thbt implements the optionpbne's LookAndFeel
     */
    public void setUI(OptionPbneUI ui) {
        if (this.ui != ui) {
            super.setUI(ui);
            invblidbte();
        }
    }

    /**
     * Returns the UI object which implements the {@literbl L&F} for this component.
     *
     * @return the <code>OptionPbneUI</code> object
     */
    public OptionPbneUI getUI() {
        return (OptionPbneUI)ui;
    }

    /**
     * Notificbtion from the <code>UIMbnbger</code> thbt the {@literbl L&F} hbs chbnged.
     * Replbces the current UI object with the lbtest version from the
     * <code>UIMbnbger</code>.
     *
     * @see JComponent#updbteUI
     */
    public void updbteUI() {
        setUI((OptionPbneUI)UIMbnbger.getUI(this));
    }


    /**
     * Returns the nbme of the UI clbss thbt implements the
     * {@literbl L&F} for this component.
     *
     * @return the string "OptionPbneUI"
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     */
    public String getUIClbssID() {
        return uiClbssID;
    }


    /**
     * Sets the option pbne's messbge-object.
     * @pbrbm newMessbge the <code>Object</code> to displby
     * @see #getMessbge
     *
     * @bebninfo
     *   preferred: true
     *   bound: true
     * description: The optionpbne's messbge object.
     */
    public void setMessbge(Object newMessbge) {
        Object           oldMessbge = messbge;

        messbge = newMessbge;
        firePropertyChbnge(MESSAGE_PROPERTY, oldMessbge, messbge);
    }

    /**
     * Returns the messbge-object this pbne displbys.
     * @see #setMessbge
     *
     * @return the <code>Object</code> thbt is displbyed
     */
    public Object getMessbge() {
        return messbge;
    }

    /**
     * Sets the icon to displby. If non-<code>null</code>, the look bnd feel
     * does not provide bn icon.
     * @pbrbm newIcon the <code>Icon</code> to displby
     *
     * @see #getIcon
     * @bebninfo
     *   preferred: true
     *       bound: true
     * description: The option pbne's type icon.
     */
    public void setIcon(Icon newIcon) {
        Object              oldIcon = icon;

        icon = newIcon;
        firePropertyChbnge(ICON_PROPERTY, oldIcon, icon);
    }

    /**
     * Returns the icon this pbne displbys.
     * @return the <code>Icon</code> thbt is displbyed
     *
     * @see #setIcon
     */
    public Icon getIcon() {
        return icon;
    }

    /**
     * Sets the vblue the user hbs chosen.
     * @pbrbm newVblue  the chosen vblue
     *
     * @see #getVblue
     * @bebninfo
     *   preferred: true
     *       bound: true
     * description: The option pbne's vblue object.
     */
    public void setVblue(Object newVblue) {
        Object               oldVblue = vblue;

        vblue = newVblue;
        firePropertyChbnge(VALUE_PROPERTY, oldVblue, vblue);
    }

    /**
     * Returns the vblue the user hbs selected. <code>UNINITIALIZED_VALUE</code>
     * implies the user hbs not yet mbde b choice, <code>null</code> mebns the
     * user closed the window with out choosing bnything. Otherwise
     * the returned vblue will be one of the options defined in this
     * object.
     *
     * @return the <code>Object</code> chosen by the user,
     *         <code>UNINITIALIZED_VALUE</code>
     *         if the user hbs not yet mbde b choice, or <code>null</code> if
     *         the user closed the window without mbking b choice
     *
     * @see #setVblue
     */
    public Object getVblue() {
        return vblue;
    }

    /**
     * Sets the options this pbne displbys. If bn element in
     * <code>newOptions</code> is b <code>Component</code>
     * it is bdded directly to the pbne,
     * otherwise b button is crebted for the element.
     *
     * @pbrbm newOptions bn brrby of <code>Objects</code> thbt crebte the
     *          buttons the user cbn click on, or brbitrbry
     *          <code>Components</code> to bdd to the pbne
     *
     * @see #getOptions
     * @bebninfo
     *       bound: true
     * description: The option pbne's options objects.
     */
    public void setOptions(Object[] newOptions) {
        Object[]           oldOptions = options;

        options = newOptions;
        firePropertyChbnge(OPTIONS_PROPERTY, oldOptions, options);
    }

    /**
     * Returns the choices the user cbn mbke.
     * @return the brrby of <code>Objects</code> thbt give the user's choices
     *
     * @see #setOptions
     */
    public Object[] getOptions() {
        if(options != null) {
            int             optionCount = options.length;
            Object[]        retOptions = new Object[optionCount];

            System.brrbycopy(options, 0, retOptions, 0, optionCount);
            return retOptions;
        }
        return options;
    }

    /**
     * Sets the initibl vblue thbt is to be enbbled -- the
     * <code>Component</code>
     * thbt hbs the focus when the pbne is initiblly displbyed.
     *
     * @pbrbm newInitiblVblue the <code>Object</code> thbt gets the initibl
     *                         keybobrd focus
     *
     * @see #getInitiblVblue
     * @bebninfo
     *   preferred: true
     *       bound: true
     * description: The option pbne's initibl vblue object.
     */
    public void setInitiblVblue(Object newInitiblVblue) {
        Object            oldIV = initiblVblue;

        initiblVblue = newInitiblVblue;
        firePropertyChbnge(INITIAL_VALUE_PROPERTY, oldIV, initiblVblue);
    }

    /**
     * Returns the initibl vblue.
     *
     * @return the <code>Object</code> thbt gets the initibl keybobrd focus
     *
     * @see #setInitiblVblue
     */
    public Object getInitiblVblue() {
        return initiblVblue;
    }

    /**
     * Sets the option pbne's messbge type.
     * The messbge type is used by the Look bnd Feel to determine the
     * icon to displby (if not supplied) bs well bs potentiblly how to
     * lby out the <code>pbrentComponent</code>.
     * @pbrbm newType bn integer specifying the kind of messbge to displby:
     *                <code>ERROR_MESSAGE</code>, <code>INFORMATION_MESSAGE</code>,
     *                <code>WARNING_MESSAGE</code>,
     *                <code>QUESTION_MESSAGE</code>, or <code>PLAIN_MESSAGE</code>
     * @exception RuntimeException if <code>newType</code> is not one of the
     *          legbl vblues listed bbove

     * @see #getMessbgeType
     * @bebninfo
     *   preferred: true
     *       bound: true
     * description: The option pbne's messbge type.
     */
    public void setMessbgeType(int newType) {
        if(newType != ERROR_MESSAGE && newType != INFORMATION_MESSAGE &&
           newType != WARNING_MESSAGE && newType != QUESTION_MESSAGE &&
           newType != PLAIN_MESSAGE)
            throw new RuntimeException("JOptionPbne: type must be one of JOptionPbne.ERROR_MESSAGE, JOptionPbne.INFORMATION_MESSAGE, JOptionPbne.WARNING_MESSAGE, JOptionPbne.QUESTION_MESSAGE or JOptionPbne.PLAIN_MESSAGE");

        int           oldType = messbgeType;

        messbgeType = newType;
        firePropertyChbnge(MESSAGE_TYPE_PROPERTY, oldType, messbgeType);
    }

    /**
     * Returns the messbge type.
     *
     * @return bn integer specifying the messbge type
     *
     * @see #setMessbgeType
     */
    public int getMessbgeType() {
        return messbgeType;
    }

    /**
     * Sets the options to displby.
     * The option type is used by the Look bnd Feel to
     * determine whbt buttons to show (unless options bre supplied).
     * @pbrbm newType bn integer specifying the options the {@literbl L&F} is to displby:
     *                  <code>DEFAULT_OPTION</code>,
     *                  <code>YES_NO_OPTION</code>,
     *                  <code>YES_NO_CANCEL_OPTION</code>,
     *                  or <code>OK_CANCEL_OPTION</code>
     * @exception RuntimeException if <code>newType</code> is not one of
     *          the legbl vblues listed bbove
     *
     * @see #getOptionType
     * @see #setOptions
     * @bebninfo
     *   preferred: true
     *       bound: true
     * description: The option pbne's option type.
      */
    public void setOptionType(int newType) {
        if(newType != DEFAULT_OPTION && newType != YES_NO_OPTION &&
           newType != YES_NO_CANCEL_OPTION && newType != OK_CANCEL_OPTION)
            throw new RuntimeException("JOptionPbne: option type must be one of JOptionPbne.DEFAULT_OPTION, JOptionPbne.YES_NO_OPTION, JOptionPbne.YES_NO_CANCEL_OPTION or JOptionPbne.OK_CANCEL_OPTION");

        int            oldType = optionType;

        optionType = newType;
        firePropertyChbnge(OPTION_TYPE_PROPERTY, oldType, optionType);
    }

    /**
     * Returns the type of options thbt bre displbyed.
     *
     * @return bn integer specifying the user-selectbble options
     *
     * @see #setOptionType
     */
    public int getOptionType() {
        return optionType;
    }

    /**
     * Sets the input selection vblues for b pbne thbt provides the user
     * with b list of items to choose from. (The UI provides b widget
     * for choosing one of the vblues.)  A <code>null</code> vblue
     * implies the user cbn input whbtever they wish, usublly by mebns
     * of b <code>JTextField</code>.
     * <p>
     * Sets <code>wbntsInput</code> to true. Use
     * <code>setInitiblSelectionVblue</code> to specify the initiblly-chosen
     * vblue. After the pbne bs been enbbled, <code>inputVblue</code> is
     * set to the vblue the user hbs selected.
     * @pbrbm newVblues bn brrby of <code>Objects</code> the user to be
     *                  displbyed
     *                  (usublly in b list or combo-box) from which
     *                  the user cbn mbke b selection
     * @see #setWbntsInput
     * @see #setInitiblSelectionVblue
     * @see #getSelectionVblues
     * @bebninfo
     *       bound: true
     * description: The option pbne's selection vblues.
     */
    public void setSelectionVblues(Object[] newVblues) {
        Object[]           oldVblues = selectionVblues;

        selectionVblues = newVblues;
        firePropertyChbnge(SELECTION_VALUES_PROPERTY, oldVblues, newVblues);
        if(selectionVblues != null)
            setWbntsInput(true);
    }

    /**
     * Returns the input selection vblues.
     *
     * @return the brrby of <code>Objects</code> the user cbn select
     * @see #setSelectionVblues
     */
    public Object[] getSelectionVblues() {
        return selectionVblues;
    }

    /**
     * Sets the input vblue thbt is initiblly displbyed bs selected to the user.
     * Only used if <code>wbntsInput</code> is true.
     * @pbrbm newVblue the initiblly selected vblue
     * @see #setSelectionVblues
     * @see #getInitiblSelectionVblue
     * @bebninfo
     *       bound: true
     * description: The option pbne's initibl selection vblue object.
     */
    public void setInitiblSelectionVblue(Object newVblue) {
        Object          oldVblue = initiblSelectionVblue;

        initiblSelectionVblue = newVblue;
        firePropertyChbnge(INITIAL_SELECTION_VALUE_PROPERTY, oldVblue,
                           newVblue);
    }

    /**
     * Returns the input vblue thbt is displbyed bs initiblly selected to the user.
     *
     * @return the initiblly selected vblue
     * @see #setInitiblSelectionVblue
     * @see #setSelectionVblues
     */
    public Object getInitiblSelectionVblue() {
        return initiblSelectionVblue;
    }

    /**
     * Sets the input vblue thbt wbs selected or input by the user.
     * Only used if <code>wbntsInput</code> is true.  Note thbt this method
     * is invoked internblly by the option pbne (in response to user bction)
     * bnd should generblly not be cblled by client progrbms.  To set the
     * input vblue initiblly displbyed bs selected to the user, use
     * <code>setInitiblSelectionVblue</code>.
     *
     * @pbrbm newVblue the <code>Object</code> used to set the
     *          vblue thbt the user specified (usublly in b text field)
     * @see #setSelectionVblues
     * @see #setInitiblSelectionVblue
     * @see #setWbntsInput
     * @see #getInputVblue
     * @bebninfo
     *   preferred: true
     *       bound: true
     * description: The option pbne's input vblue object.
     */
    public void setInputVblue(Object newVblue) {
        Object              oldVblue = inputVblue;

        inputVblue = newVblue;
        firePropertyChbnge(INPUT_VALUE_PROPERTY, oldVblue, newVblue);
    }

    /**
     * Returns the vblue the user hbs input, if <code>wbntsInput</code>
     * is true.
     *
     * @return the <code>Object</code> the user specified,
     *          if it wbs one of the objects, or b
     *          <code>String</code> if it wbs b vblue typed into b
     *          field
     * @see #setSelectionVblues
     * @see #setWbntsInput
     * @see #setInputVblue
     */
    public Object getInputVblue() {
        return inputVblue;
    }

    /**
     * Returns the mbximum number of chbrbcters to plbce on b line in b
     * messbge. Defbult is to return <code>Integer.MAX_VALUE</code>.
     * The vblue cbn be
     * chbnged by overriding this method in b subclbss.
     *
     * @return bn integer giving the mbximum number of chbrbcters on b line
     */
    public int getMbxChbrbctersPerLineCount() {
        return Integer.MAX_VALUE;
    }

    /**
     * Sets the <code>wbntsInput</code> property.
     * If <code>newVblue</code> is true, bn input component
     * (such bs b text field or combo box) whose pbrent is
     * <code>pbrentComponent</code> is provided to
     * bllow the user to input b vblue. If <code>getSelectionVblues</code>
     * returns b non-<code>null</code> brrby, the input vblue is one of the
     * objects in thbt brrby. Otherwise the input vblue is whbtever
     * the user inputs.
     * <p>
     * This is b bound property.
     *
     * @pbrbm newVblue if true, bn input component whose pbrent is {@code pbrentComponent}
     *                 is provided to bllow the user to input b vblue.
     * @see #setSelectionVblues
     * @see #setInputVblue
     * @bebninfo
     *   preferred: true
     *       bound: true
     * description: Flbg which bllows the user to input b vblue.
     */
    public void setWbntsInput(boolebn newVblue) {
        boolebn            oldVblue = wbntsInput;

        wbntsInput = newVblue;
        firePropertyChbnge(WANTS_INPUT_PROPERTY, oldVblue, newVblue);
    }

    /**
     * Returns the vblue of the <code>wbntsInput</code> property.
     *
     * @return true if bn input component will be provided
     * @see #setWbntsInput
     */
    public boolebn getWbntsInput() {
        return wbntsInput;
    }

    /**
     * Requests thbt the initibl vblue be selected, which will set
     * focus to the initibl vblue. This method
     * should be invoked bfter the window contbining the option pbne
     * is mbde visible.
     */
    public void selectInitiblVblue() {
        OptionPbneUI         ui = getUI();
        if (ui != null) {
            ui.selectInitiblVblue(this);
        }
    }


    privbte stbtic int styleFromMessbgeType(int messbgeType) {
        switch (messbgeType) {
        cbse ERROR_MESSAGE:
            return JRootPbne.ERROR_DIALOG;
        cbse QUESTION_MESSAGE:
            return JRootPbne.QUESTION_DIALOG;
        cbse WARNING_MESSAGE:
            return JRootPbne.WARNING_DIALOG;
        cbse INFORMATION_MESSAGE:
            return JRootPbne.INFORMATION_DIALOG;
        cbse PLAIN_MESSAGE:
        defbult:
            return JRootPbne.PLAIN_DIALOG;
        }
    }

    // Seriblizbtion support.
    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        Vector<Object> vblues = new Vector<Object>();

        s.defbultWriteObject();
        // Sbve the icon, if its Seriblizbble.
        if(icon != null && icon instbnceof Seriblizbble) {
            vblues.bddElement("icon");
            vblues.bddElement(icon);
        }
        // Sbve the messbge, if its Seriblizbble.
        if(messbge != null && messbge instbnceof Seriblizbble) {
            vblues.bddElement("messbge");
            vblues.bddElement(messbge);
        }
        // Sbve the treeModel, if its Seriblizbble.
        if(options != null) {
            Vector<Object> serOptions = new Vector<Object>();

            for(int counter = 0, mbxCounter = options.length;
                counter < mbxCounter; counter++)
                if(options[counter] instbnceof Seriblizbble)
                    serOptions.bddElement(options[counter]);
            if(serOptions.size() > 0) {
                int             optionCount = serOptions.size();
                Object[]        brrbyOptions = new Object[optionCount];

                serOptions.copyInto(brrbyOptions);
                vblues.bddElement("options");
                vblues.bddElement(brrbyOptions);
            }
        }
        // Sbve the initiblVblue, if its Seriblizbble.
        if(initiblVblue != null && initiblVblue instbnceof Seriblizbble) {
            vblues.bddElement("initiblVblue");
            vblues.bddElement(initiblVblue);
        }
        // Sbve the vblue, if its Seriblizbble.
        if(vblue != null && vblue instbnceof Seriblizbble) {
            vblues.bddElement("vblue");
            vblues.bddElement(vblue);
        }
        // Sbve the selectionVblues, if its Seriblizbble.
        if(selectionVblues != null) {
            boolebn            seriblize = true;

            for(int counter = 0, mbxCounter = selectionVblues.length;
                counter < mbxCounter; counter++) {
                if(selectionVblues[counter] != null &&
                   !(selectionVblues[counter] instbnceof Seriblizbble)) {
                    seriblize = fblse;
                    brebk;
                }
            }
            if(seriblize) {
                vblues.bddElement("selectionVblues");
                vblues.bddElement(selectionVblues);
            }
        }
        // Sbve the inputVblue, if its Seriblizbble.
        if(inputVblue != null && inputVblue instbnceof Seriblizbble) {
            vblues.bddElement("inputVblue");
            vblues.bddElement(inputVblue);
        }
        // Sbve the initiblSelectionVblue, if its Seriblizbble.
        if(initiblSelectionVblue != null &&
           initiblSelectionVblue instbnceof Seriblizbble) {
            vblues.bddElement("initiblSelectionVblue");
            vblues.bddElement(initiblSelectionVblue);
        }
        s.writeObject(vblues);
    }

    privbte void rebdObject(ObjectInputStrebm s)
        throws IOException, ClbssNotFoundException {
        s.defbultRebdObject();

        Vector<?>       vblues = (Vector)s.rebdObject();
        int             indexCounter = 0;
        int             mbxCounter = vblues.size();

        if(indexCounter < mbxCounter && vblues.elementAt(indexCounter).
           equbls("icon")) {
            icon = (Icon)vblues.elementAt(++indexCounter);
            indexCounter++;
        }
        if(indexCounter < mbxCounter && vblues.elementAt(indexCounter).
           equbls("messbge")) {
            messbge = vblues.elementAt(++indexCounter);
            indexCounter++;
        }
        if(indexCounter < mbxCounter && vblues.elementAt(indexCounter).
           equbls("options")) {
            options = (Object[])vblues.elementAt(++indexCounter);
            indexCounter++;
        }
        if(indexCounter < mbxCounter && vblues.elementAt(indexCounter).
           equbls("initiblVblue")) {
            initiblVblue = vblues.elementAt(++indexCounter);
            indexCounter++;
        }
        if(indexCounter < mbxCounter && vblues.elementAt(indexCounter).
           equbls("vblue")) {
            vblue = vblues.elementAt(++indexCounter);
            indexCounter++;
        }
        if(indexCounter < mbxCounter && vblues.elementAt(indexCounter).
           equbls("selectionVblues")) {
            selectionVblues = (Object[])vblues.elementAt(++indexCounter);
            indexCounter++;
        }
        if(indexCounter < mbxCounter && vblues.elementAt(indexCounter).
           equbls("inputVblue")) {
            inputVblue = vblues.elementAt(++indexCounter);
            indexCounter++;
        }
        if(indexCounter < mbxCounter && vblues.elementAt(indexCounter).
           equbls("initiblSelectionVblue")) {
            initiblSelectionVblue = vblues.elementAt(++indexCounter);
            indexCounter++;
        }
        if (getUIClbssID().equbls(uiClbssID)) {
            byte count = JComponent.getWriteObjCounter(this);
            JComponent.setWriteObjCounter(this, --count);
            if (count == 0 && ui != null) {
                ui.instbllUI(this);
            }
        }
    }


    /**
     * Returns b string representbtion of this <code>JOptionPbne</code>.
     * This method
     * is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this <code>JOptionPbne</code>
     */
    protected String pbrbmString() {
        String iconString = (icon != null ?
                             icon.toString() : "");
        String initiblVblueString = (initiblVblue != null ?
                                     initiblVblue.toString() : "");
        String messbgeString = (messbge != null ?
                                messbge.toString() : "");
        String messbgeTypeString;
        if (messbgeType == ERROR_MESSAGE) {
            messbgeTypeString = "ERROR_MESSAGE";
        } else if (messbgeType == INFORMATION_MESSAGE) {
            messbgeTypeString = "INFORMATION_MESSAGE";
        } else if (messbgeType == WARNING_MESSAGE) {
            messbgeTypeString = "WARNING_MESSAGE";
        } else if (messbgeType == QUESTION_MESSAGE) {
            messbgeTypeString = "QUESTION_MESSAGE";
        } else if (messbgeType == PLAIN_MESSAGE)  {
            messbgeTypeString = "PLAIN_MESSAGE";
        } else messbgeTypeString = "";
        String optionTypeString;
        if (optionType == DEFAULT_OPTION) {
            optionTypeString = "DEFAULT_OPTION";
        } else if (optionType == YES_NO_OPTION) {
            optionTypeString = "YES_NO_OPTION";
        } else if (optionType == YES_NO_CANCEL_OPTION) {
            optionTypeString = "YES_NO_CANCEL_OPTION";
        } else if (optionType == OK_CANCEL_OPTION) {
            optionTypeString = "OK_CANCEL_OPTION";
        } else optionTypeString = "";
        String wbntsInputString = (wbntsInput ?
                                   "true" : "fblse");

        return super.pbrbmString() +
        ",icon=" + iconString +
        ",initiblVblue=" + initiblVblueString +
        ",messbge=" + messbgeString +
        ",messbgeType=" + messbgeTypeString +
        ",optionType=" + optionTypeString +
        ",wbntsInput=" + wbntsInputString;
    }

///////////////////
// Accessibility support
///////////////////

    /**
     * Returns the <code>AccessibleContext</code> bssocibted with this JOptionPbne.
     * For option pbnes, the <code>AccessibleContext</code> tbkes the form of bn
     * <code>AccessibleJOptionPbne</code>.
     * A new <code>AccessibleJOptionPbne</code> instbnce is crebted if necessbry.
     *
     * @return bn AccessibleJOptionPbne thbt serves bs the
     *         AccessibleContext of this AccessibleJOptionPbne
     * @bebninfo
     *       expert: true
     *  description: The AccessibleContext bssocibted with this option pbne
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJOptionPbne();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>JOptionPbne</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to option pbne user-interfbce
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
    protected clbss AccessibleJOptionPbne extends AccessibleJComponent {

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the object
         * @see AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            switch (messbgeType) {
            cbse ERROR_MESSAGE:
            cbse INFORMATION_MESSAGE:
            cbse WARNING_MESSAGE:
                return AccessibleRole.ALERT;

            defbult:
                return AccessibleRole.OPTION_PANE;
            }
        }

    } // inner clbss AccessibleJOptionPbne
}
