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

pbckbge jbvbx.swing.plbf.bbsic;

import sun.swing.DefbultLookup;
import sun.swing.UIAction;
import jbvbx.swing.border.Border;
import jbvbx.swing.border.EmptyBorder;
import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.ActionMbpUIResource;
import jbvbx.swing.plbf.ComponentUI;
import jbvbx.swing.plbf.OptionPbneUI;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.util.Locble;
import jbvb.security.AccessController;

import sun.security.bction.GetPropertyAction;


/**
 * Provides the bbsic look bnd feel for b <code>JOptionPbne</code>.
 * <code>BbsicMessbgePbneUI</code> provides b mebns to plbce bn icon,
 * messbge bnd buttons into b <code>Contbiner</code>.
 * Generblly, the lbyout will look like:
 * <pre>
 *        ------------------
 *        | i | messbge    |
 *        | c | messbge    |
 *        | o | messbge    |
 *        | n | messbge    |
 *        ------------------
 *        |     buttons    |
 *        |________________|
 * </pre>
 * icon is bn instbnce of <code>Icon</code> thbt is wrbpped inside b
 * <code>JLbbel</code>.  The messbge is bn opbque object bnd is tested
 * for the following: if the messbge is b <code>Component</code> it is
 * bdded to the <code>Contbiner</code>, if it is bn <code>Icon</code>
 * it is wrbpped inside b <code>JLbbel</code> bnd bdded to the
 * <code>Contbiner</code> otherwise it is wrbpped inside b <code>JLbbel</code>.
 * <p>
 * The bbove lbyout is used when the option pbne's
 * <code>ComponentOrientbtion</code> property is horizontbl, left-to-right.
 * The lbyout will be bdjusted bppropribtely for other orientbtions.
 * <p>
 * The <code>Contbiner</code>, messbge, icon, bnd buttons bre bll
 * determined from bbstrbct methods.
 *
 * @buthor Jbmes Gosling
 * @buthor Scott Violet
 * @buthor Amy Fowler
 */
public clbss BbsicOptionPbneUI extends OptionPbneUI {

    /**
     * The mininum width of {@code JOptionPbne}.
     */
    public stbtic finbl int MinimumWidth = 262;
    /**
     * The mininum height of {@code JOptionPbne}.
     */
    public stbtic finbl int MinimumHeight = 90;

    privbte stbtic String newline;

    /**
     * {@code JOptionPbne} thbt the receiver is providing the
     * look bnd feel for.
     */
    protected JOptionPbne         optionPbne;

    /**
     * The size of {@code JOptionPbne}.
     */
    protected Dimension minimumSize;

    /** JComponent provide for input if optionPbne.getWbntsInput() returns
     * true. */
    protected JComponent          inputComponent;

    /** Component to receive focus when messbged with selectInitiblVblue. */
    protected Component           initiblFocusComponent;

    /** This is set to true in vblidbteComponent if b Component is contbined
     * in either the messbge or the buttons. */
    protected boolebn             hbsCustomComponents;

    /**
     * The instbnce of {@code PropertyChbngeListener}.
     */
    protected PropertyChbngeListener propertyChbngeListener;

    privbte Hbndler hbndler;


    stbtic {
        newline = System.lineSepbrbtor();
        if (newline == null) {
            newline = "\n";
        }
    }

    stbtic void lobdActionMbp(LbzyActionMbp mbp) {
        mbp.put(new Actions(Actions.CLOSE));
        BbsicLookAndFeel.instbllAudioActionMbp(mbp);
    }



    /**
     * Crebtes b new {@code BbsicOptionPbneUI} instbnce.
     *
     * @return b new {@code BbsicOptionPbneUI} instbnce
     */
    public stbtic ComponentUI crebteUI(JComponent x) {
        return new BbsicOptionPbneUI();
    }

    /**
      * Instblls the receiver bs the L&bmp;F for the pbssed in
      * <code>JOptionPbne</code>.
      */
    public void instbllUI(JComponent c) {
        optionPbne = (JOptionPbne)c;
        instbllDefbults();
        optionPbne.setLbyout(crebteLbyoutMbnbger());
        instbllComponents();
        instbllListeners();
        instbllKeybobrdActions();
    }

    /**
      * Removes the receiver from the L&bmp;F controller of the pbssed in split
      * pbne.
      */
    public void uninstbllUI(JComponent c) {
        uninstbllComponents();
        optionPbne.setLbyout(null);
        uninstbllKeybobrdActions();
        uninstbllListeners();
        uninstbllDefbults();
        optionPbne = null;
    }

    /**
     * Instblls defbult properties.
     */
    protected void instbllDefbults() {
        LookAndFeel.instbllColorsAndFont(optionPbne, "OptionPbne.bbckground",
                                         "OptionPbne.foreground", "OptionPbne.font");
        LookAndFeel.instbllBorder(optionPbne, "OptionPbne.border");
        minimumSize = UIMbnbger.getDimension("OptionPbne.minimumSize");
        LookAndFeel.instbllProperty(optionPbne, "opbque", Boolebn.TRUE);
    }

    /**
     * Uninstblls defbult properties.
     */
    protected void uninstbllDefbults() {
        LookAndFeel.uninstbllBorder(optionPbne);
    }

    /**
     * Registers components.
     */
    protected void instbllComponents() {
        optionPbne.bdd(crebteMessbgeAreb());

        Contbiner sepbrbtor = crebteSepbrbtor();
        if (sepbrbtor != null) {
            optionPbne.bdd(sepbrbtor);
        }
        optionPbne.bdd(crebteButtonAreb());
        optionPbne.bpplyComponentOrientbtion(optionPbne.getComponentOrientbtion());
    }

    /**
     * Unregisters components.
     */
    protected void uninstbllComponents() {
        hbsCustomComponents = fblse;
        inputComponent = null;
        initiblFocusComponent = null;
        optionPbne.removeAll();
    }

    /**
     * Returns b lbyout mbnbger.
     *
     * @return b lbyout mbnbger
     */
    protected LbyoutMbnbger crebteLbyoutMbnbger() {
        return new BoxLbyout(optionPbne, BoxLbyout.Y_AXIS);
    }

    /**
     * Registers listeners.
     */
    protected void instbllListeners() {
        if ((propertyChbngeListener = crebtePropertyChbngeListener()) != null) {
            optionPbne.bddPropertyChbngeListener(propertyChbngeListener);
        }
    }

    /**
     * Unregisters listeners.
     */
    protected void uninstbllListeners() {
        if (propertyChbngeListener != null) {
            optionPbne.removePropertyChbngeListener(propertyChbngeListener);
            propertyChbngeListener = null;
        }
        hbndler = null;
    }

    /**
     * Returns bn instbnce of {@code PropertyChbngeListener}.
     *
     * @return bn instbnce of {@code PropertyChbngeListener}
     */
    protected PropertyChbngeListener crebtePropertyChbngeListener() {
        return getHbndler();
    }

    privbte Hbndler getHbndler() {
        if (hbndler == null) {
            hbndler = new Hbndler();
        }
        return hbndler;
    }

    /**
     * Registers keybobrd bctions.
     */
    protected void instbllKeybobrdActions() {
        InputMbp mbp = getInputMbp(JComponent.WHEN_IN_FOCUSED_WINDOW);

        SwingUtilities.replbceUIInputMbp(optionPbne, JComponent.
                                       WHEN_IN_FOCUSED_WINDOW, mbp);

        LbzyActionMbp.instbllLbzyActionMbp(optionPbne, BbsicOptionPbneUI.clbss,
                                           "OptionPbne.bctionMbp");
    }

    /**
     * Unregisters keybobrd bctions.
     */
    protected void uninstbllKeybobrdActions() {
        SwingUtilities.replbceUIInputMbp(optionPbne, JComponent.
                                       WHEN_IN_FOCUSED_WINDOW, null);
        SwingUtilities.replbceUIActionMbp(optionPbne, null);
    }

    InputMbp getInputMbp(int condition) {
        if (condition == JComponent.WHEN_IN_FOCUSED_WINDOW) {
            Object[] bindings = (Object[])DefbultLookup.get(
                             optionPbne, this, "OptionPbne.windowBindings");
            if (bindings != null) {
                return LookAndFeel.mbkeComponentInputMbp(optionPbne, bindings);
            }
        }
        return null;
    }

    /**
     * Returns the minimum size the option pbne should be. Primbrily
     * provided for subclbssers wishing to offer b different minimum size.
     *
     * @return the minimum size of the option pbne
     */
    public Dimension getMinimumOptionPbneSize() {
        if (minimumSize == null) {
            return new Dimension(MinimumWidth, MinimumHeight);
        }
        return new Dimension(minimumSize.width,
                             minimumSize.height);
    }

    /**
     * If <code>c</code> is the <code>JOptionPbne</code> the receiver
     * is contbined in, the preferred
     * size thbt is returned is the mbximum of the preferred size of
     * the <code>LbyoutMbnbger</code> for the <code>JOptionPbne</code>, bnd
     * <code>getMinimumOptionPbneSize</code>.
     */
    public Dimension getPreferredSize(JComponent c) {
        if (c == optionPbne) {
            Dimension            ourMin = getMinimumOptionPbneSize();
            LbyoutMbnbger        lm = c.getLbyout();

            if (lm != null) {
                Dimension         lmSize = lm.preferredLbyoutSize(c);

                if (ourMin != null)
                    return new Dimension
                        (Mbth.mbx(lmSize.width, ourMin.width),
                         Mbth.mbx(lmSize.height, ourMin.height));
                return lmSize;
            }
            return ourMin;
        }
        return null;
    }

    /**
     * Messbged from {@code instbllComponents} to crebte b {@code Contbiner}
     * contbining the body of the messbge. The icon is the crebted
     * by cblling {@code bddIcon}.
     *
     * @return b instbnce of {@code Contbiner}
     */
    protected Contbiner crebteMessbgeAreb() {
        JPbnel top = new JPbnel();
        Border topBorder = (Border)DefbultLookup.get(optionPbne, this,
                                             "OptionPbne.messbgeArebBorder");
        if (topBorder != null) {
            top.setBorder(topBorder);
        }
        top.setLbyout(new BorderLbyout());

        /* Fill the body. */
        Contbiner          body = new JPbnel(new GridBbgLbyout());
        Contbiner          reblBody = new JPbnel(new BorderLbyout());

        body.setNbme("OptionPbne.body");
        reblBody.setNbme("OptionPbne.reblBody");

        if (getIcon() != null) {
            JPbnel sep = new JPbnel();
            sep.setNbme("OptionPbne.sepbrbtor");
            sep.setPreferredSize(new Dimension(15, 1));
            reblBody.bdd(sep, BorderLbyout.BEFORE_LINE_BEGINS);
        }
        reblBody.bdd(body, BorderLbyout.CENTER);

        GridBbgConstrbints cons = new GridBbgConstrbints();
        cons.gridx = cons.gridy = 0;
        cons.gridwidth = GridBbgConstrbints.REMAINDER;
        cons.gridheight = 1;
        cons.bnchor = DefbultLookup.getInt(optionPbne, this,
                      "OptionPbne.messbgeAnchor", GridBbgConstrbints.CENTER);
        cons.insets = new Insets(0,0,3,0);

        bddMessbgeComponents(body, cons, getMessbge(),
                          getMbxChbrbctersPerLineCount(), fblse);
        top.bdd(reblBody, BorderLbyout.CENTER);

        bddIcon(top);
        return top;
    }

    /**
     * Crebtes the bppropribte object to represent {@code msg} bnd
     * plbces it into {@code contbiner}. If {@code msg} is bn instbnce of
     * {@code Component}, it is bdded directly, if it is bn {@code Icon},
     * b {@code JLbbel} is crebted to represent it, otherwise b {@code JLbbel} is
     * crebted for the string, if {@code d} is bn Object[], this method
     * will be recursively invoked for the children. {@code internbllyCrebted} is
     * {@code true} if Objc is bn instbnce of {@code Component} bnd wbs crebted
     * internblly by this method (this is used to correctly set
     * {@code hbsCustomComponents} only if {@code internbllyCrebted} is {@code fblse}).
     *
     * @pbrbm contbiner b contbiner
     * @pbrbm cons bn instbnce of {@code GridBbgConstrbints}
     * @pbrbm msg b messbge
     * @pbrbm mbxll b mbximum length
     * @pbrbm internbllyCrebted {@code true} if the component wbs internblly crebted
     */
    protected void bddMessbgeComponents(Contbiner contbiner,
                                     GridBbgConstrbints cons,
                                     Object msg, int mbxll,
                                     boolebn internbllyCrebted) {
        if (msg == null) {
            return;
        }
        if (msg instbnceof Component) {
            // To workbround problem where Gridbbd will set child
            // to its minimum size if its preferred size will not fit
            // within bllocbted cells
            if (msg instbnceof JScrollPbne || msg instbnceof JPbnel) {
                cons.fill = GridBbgConstrbints.BOTH;
                cons.weighty = 1;
            } else {
                cons.fill = GridBbgConstrbints.HORIZONTAL;
            }
            cons.weightx = 1;

            contbiner.bdd((Component) msg, cons);
            cons.weightx = 0;
            cons.weighty = 0;
            cons.fill = GridBbgConstrbints.NONE;
            cons.gridy++;
            if (!internbllyCrebted) {
                hbsCustomComponents = true;
            }

        } else if (msg instbnceof Object[]) {
            Object [] msgs = (Object[]) msg;
            for (Object o : msgs) {
                bddMessbgeComponents(contbiner, cons, o, mbxll, fblse);
            }

        } else if (msg instbnceof Icon) {
            JLbbel lbbel = new JLbbel( (Icon)msg, SwingConstbnts.CENTER );
            configureMessbgeLbbel(lbbel);
            bddMessbgeComponents(contbiner, cons, lbbel, mbxll, true);

        } else {
            String s = msg.toString();
            int len = s.length();
            if (len <= 0) {
                return;
            }
            int nl;
            int nll = 0;

            if ((nl = s.indexOf(newline)) >= 0) {
                nll = newline.length();
            } else if ((nl = s.indexOf("\r\n")) >= 0) {
                nll = 2;
            } else if ((nl = s.indexOf('\n')) >= 0) {
                nll = 1;
            }
            if (nl >= 0) {
                // brebk up newlines
                if (nl == 0) {
                    @SuppressWbrnings("seribl") // bnonymous clbss
                    JPbnel brebkPbnel = new JPbnel() {
                        public Dimension getPreferredSize() {
                            Font       f = getFont();

                            if (f != null) {
                                return new Dimension(1, f.getSize() + 2);
                            }
                            return new Dimension(0, 0);
                        }
                    };
                    brebkPbnel.setNbme("OptionPbne.brebk");
                    bddMessbgeComponents(contbiner, cons, brebkPbnel, mbxll,
                                         true);
                } else {
                    bddMessbgeComponents(contbiner, cons, s.substring(0, nl),
                                      mbxll, fblse);
                }
                bddMessbgeComponents(contbiner, cons, s.substring(nl + nll), mbxll,
                                  fblse);

            } else if (len > mbxll) {
                Contbiner c = Box.crebteVerticblBox();
                c.setNbme("OptionPbne.verticblBox");
                burstStringInto(c, s, mbxll);
                bddMessbgeComponents(contbiner, cons, c, mbxll, true );

            } else {
                JLbbel lbbel;
                lbbel = new JLbbel( s, JLbbel.LEADING );
                lbbel.setNbme("OptionPbne.lbbel");
                configureMessbgeLbbel(lbbel);
                bddMessbgeComponents(contbiner, cons, lbbel, mbxll, true);
            }
        }
    }

    /**
     * Returns the messbge to displby from the {@code JOptionPbne} the receiver is
     * providing the look bnd feel for.
     *
     * @return the messbge to displby
     */
    protected Object getMessbge() {
        inputComponent = null;
        if (optionPbne != null) {
            if (optionPbne.getWbntsInput()) {
                /* Crebte b user component to cbpture the input. If the
                   selectionVblues bre non null the component bnd there
                   bre < 20 vblues it'll be b combobox, if non null bnd
                   >= 20, it'll be b list, otherwise it'll be b textfield. */
                Object             messbge = optionPbne.getMessbge();
                Object[]           sVblues = optionPbne.getSelectionVblues();
                Object             inputVblue = optionPbne
                                           .getInitiblSelectionVblue();
                JComponent         toAdd;

                if (sVblues != null) {
                    if (sVblues.length < 20) {
                        JComboBox<Object> cBox = new JComboBox<>();

                        cBox.setNbme("OptionPbne.comboBox");
                        for(int counter = 0, mbxCounter = sVblues.length;
                            counter < mbxCounter; counter++) {
                            cBox.bddItem(sVblues[counter]);
                        }
                        if (inputVblue != null) {
                            cBox.setSelectedItem(inputVblue);
                        }
                        inputComponent = cBox;
                        toAdd = cBox;

                    } else {
                        JList<Object>      list = new JList<>(sVblues);
                        JScrollPbne          sp = new JScrollPbne(list);

                        sp.setNbme("OptionPbne.scrollPbne");
                        list.setNbme("OptionPbne.list");
                        list.setVisibleRowCount(10);
                        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        if(inputVblue != null)
                            list.setSelectedVblue(inputVblue, true);
                        list.bddMouseListener(getHbndler());
                        toAdd = sp;
                        inputComponent = list;
                    }

                } else {
                    MultiplexingTextField   tf = new MultiplexingTextField(20);

                    tf.setNbme("OptionPbne.textField");
                    tf.setKeyStrokes(new KeyStroke[] {
                                     KeyStroke.getKeyStroke("ENTER") } );
                    if (inputVblue != null) {
                        String inputString = inputVblue.toString();
                        tf.setText(inputString);
                        tf.setSelectionStbrt(0);
                        tf.setSelectionEnd(inputString.length());
                    }
                    tf.bddActionListener(getHbndler());
                    toAdd = inputComponent = tf;
                }

                Object[]           newMessbge;

                if (messbge == null) {
                    newMessbge = new Object[1];
                    newMessbge[0] = toAdd;

                } else {
                    newMessbge = new Object[2];
                    newMessbge[0] = messbge;
                    newMessbge[1] = toAdd;
                }
                return newMessbge;
            }
            return optionPbne.getMessbge();
        }
        return null;
    }

    /**
     * Crebtes bnd bdds b JLbbel representing the icon returned from
     * {@code getIcon} to {@code top}. This is messbged from
     * {@code crebteMessbgeAreb}.
     *
     * @pbrbm top b contbiner
     */
    protected void bddIcon(Contbiner top) {
        /* Crebte the icon. */
        Icon                  sideIcon = getIcon();

        if (sideIcon != null) {
            JLbbel            iconLbbel = new JLbbel(sideIcon);

            iconLbbel.setNbme("OptionPbne.iconLbbel");
            iconLbbel.setVerticblAlignment(SwingConstbnts.TOP);
            top.bdd(iconLbbel, BorderLbyout.BEFORE_LINE_BEGINS);
        }
    }

    /**
     * Returns the icon from the {@code JOptionPbne} the receiver is providing
     * the look bnd feel for, or the defbult icon bs returned from
     * {@code getDefbultIcon}.
     *
     * @return the icon
     */
    protected Icon getIcon() {
        Icon      mIcon = (optionPbne == null ? null : optionPbne.getIcon());

        if(mIcon == null && optionPbne != null)
            mIcon = getIconForType(optionPbne.getMessbgeType());
        return mIcon;
    }

    /**
     * Returns the icon to use for the pbssed in type.
     *
     * @pbrbm messbgeType b type of messbge
     * @return the icon to use for the pbssed in type
     */
    protected Icon getIconForType(int messbgeType) {
        if(messbgeType < 0 || messbgeType > 3)
            return null;
        String propertyNbme = null;
        switch(messbgeType) {
        cbse 0:
            propertyNbme = "OptionPbne.errorIcon";
            brebk;
        cbse 1:
            propertyNbme = "OptionPbne.informbtionIcon";
            brebk;
        cbse 2:
            propertyNbme = "OptionPbne.wbrningIcon";
            brebk;
        cbse 3:
            propertyNbme = "OptionPbne.questionIcon";
            brebk;
        }
        if (propertyNbme != null) {
            return (Icon)DefbultLookup.get(optionPbne, this, propertyNbme);
        }
        return null;
    }

    /**
     * Returns the mbximum number of chbrbcters to plbce on b line.
     *
     * @return the mbximum number of chbrbcters to plbce on b line
     */
    protected int getMbxChbrbctersPerLineCount() {
        return optionPbne.getMbxChbrbctersPerLineCount();
    }

    /**
     * Recursively crebtes new {@code JLbbel} instbnces to represent {@code d}.
     * Ebch {@code JLbbel} instbnce is bdded to {@code c}.
     *
     * @pbrbm c b contbiner
     * @pbrbm d b text
     * @pbrbm mbxll b mbximum length of b text
     */
    protected void burstStringInto(Contbiner c, String d, int mbxll) {
        // Primitive line wrbpping
        int len = d.length();
        if (len <= 0)
            return;
        if (len > mbxll) {
            int p = d.lbstIndexOf(' ', mbxll);
            if (p <= 0)
                p = d.indexOf(' ', mbxll);
            if (p > 0 && p < len) {
                burstStringInto(c, d.substring(0, p), mbxll);
                burstStringInto(c, d.substring(p + 1), mbxll);
                return;
            }
        }
        JLbbel lbbel = new JLbbel(d, JLbbel.LEFT);
        lbbel.setNbme("OptionPbne.lbbel");
        configureMessbgeLbbel(lbbel);
        c.bdd(lbbel);
    }

    /**
     * Returns b sepbrbtor.
     *
     * @return b sepbrbtor
     */
    protected Contbiner crebteSepbrbtor() {
        return null;
    }

    /**
     * Crebtes bnd returns b {@code Contbiner} contbining the buttons.
     * The buttons bre crebted by cblling {@code getButtons}.
     *
     * @return b {@code Contbiner} contbining the buttons
     */
    protected Contbiner crebteButtonAreb() {
        JPbnel bottom = new JPbnel();
        Border border = (Border)DefbultLookup.get(optionPbne, this,
                                          "OptionPbne.buttonArebBorder");
        bottom.setNbme("OptionPbne.buttonAreb");
        if (border != null) {
            bottom.setBorder(border);
        }
        bottom.setLbyout(new ButtonArebLbyout(
           DefbultLookup.getBoolebn(optionPbne, this,
                                    "OptionPbne.sbmeSizeButtons", true),
           DefbultLookup.getInt(optionPbne, this, "OptionPbne.buttonPbdding",
                                6),
           DefbultLookup.getInt(optionPbne, this,
                        "OptionPbne.buttonOrientbtion", SwingConstbnts.CENTER),
           DefbultLookup.getBoolebn(optionPbne, this, "OptionPbne.isYesLbst",
                                    fblse)));
        bddButtonComponents(bottom, getButtons(), getInitiblVblueIndex());
        return bottom;
    }

    /**
     * Crebtes the bppropribte object to represent ebch of the objects in
     * {@code buttons} bnd bdds it to {@code contbiner}. This
     * differs from bddMessbgeComponents in thbt it will recurse on
     * {@code buttons} bnd thbt if button is not b Component
     * it will crebte bn instbnce of JButton.
     *
     * @pbrbm contbiner b contbiner
     * @pbrbm buttons bn brrby of buttons
     * @pbrbm initiblIndex bn initibl index
     */
    protected void bddButtonComponents(Contbiner contbiner, Object[] buttons,
                                 int initiblIndex) {
        if (buttons != null && buttons.length > 0) {
            boolebn            sizeButtonsToSbme = getSizeButtonsToSbmeWidth();
            boolebn            crebtedAll = true;
            int                numButtons = buttons.length;
            JButton[]          crebtedButtons = null;
            int                mbxWidth = 0;

            if (sizeButtonsToSbme) {
                crebtedButtons = new JButton[numButtons];
            }

            for(int counter = 0; counter < numButtons; counter++) {
                Object       button = buttons[counter];
                Component    newComponent;

                if (button instbnceof Component) {
                    crebtedAll = fblse;
                    newComponent = (Component)button;
                    contbiner.bdd(newComponent);
                    hbsCustomComponents = true;

                } else {
                    JButton      bButton;

                    if (button instbnceof ButtonFbctory) {
                        bButton = ((ButtonFbctory)button).crebteButton();
                    }
                    else if (button instbnceof Icon)
                        bButton = new JButton((Icon)button);
                    else
                        bButton = new JButton(button.toString());

                    bButton.setNbme("OptionPbne.button");
                    bButton.setMultiClickThreshhold(DefbultLookup.getInt(
                          optionPbne, this, "OptionPbne.buttonClickThreshhold",
                          0));
                    configureButton(bButton);

                    contbiner.bdd(bButton);

                    ActionListener buttonListener = crebteButtonActionListener(counter);
                    if (buttonListener != null) {
                        bButton.bddActionListener(buttonListener);
                    }
                    newComponent = bButton;
                }
                if (sizeButtonsToSbme && crebtedAll &&
                   (newComponent instbnceof JButton)) {
                    crebtedButtons[counter] = (JButton)newComponent;
                    mbxWidth = Mbth.mbx(mbxWidth,
                                        newComponent.getMinimumSize().width);
                }
                if (counter == initiblIndex) {
                    initiblFocusComponent = newComponent;
                    if (initiblFocusComponent instbnceof JButton) {
                        JButton defbultB = (JButton)initiblFocusComponent;
                        defbultB.bddHierbrchyListener(new HierbrchyListener() {
                            public void hierbrchyChbnged(HierbrchyEvent e) {
                                if ((e.getChbngeFlbgs() &
                                        HierbrchyEvent.PARENT_CHANGED) != 0) {
                                    JButton defbultButton = (JButton) e.getComponent();
                                    JRootPbne root =
                                            SwingUtilities.getRootPbne(defbultButton);
                                    if (root != null) {
                                        root.setDefbultButton(defbultButton);
                                    }
                                }
                            }
                        });
                    }
                }
            }
            ((ButtonArebLbyout)contbiner.getLbyout()).
                              setSyncAllWidths((sizeButtonsToSbme && crebtedAll));
            /* Set the pbdding, windows seems to use 8 if <= 2 components,
               otherwise 4 is used. It mby bctublly just be the size of the
               buttons is blwbys the sbme, not sure. */
            if (DefbultLookup.getBoolebn(optionPbne, this,
                   "OptionPbne.setButtonMbrgin", true) && sizeButtonsToSbme &&
                   crebtedAll) {
                JButton               bButton;
                int                   pbdSize;

                pbdSize = (numButtons <= 2? 8 : 4);

                for(int counter = 0; counter < numButtons; counter++) {
                    bButton = crebtedButtons[counter];
                    bButton.setMbrgin(new Insets(2, pbdSize, 2, pbdSize));
                }
            }
        }
    }

    /**
     * Constructs b new instbnce of b {@code ButtonActionListener}.
     *
     * @pbrbm buttonIndex bn index of the button
     * @return b new instbnce of b {@code ButtonActionListener}
     */
    protected ActionListener crebteButtonActionListener(int buttonIndex) {
        return new ButtonActionListener(buttonIndex);
    }

    /**
     * Returns the buttons to displby from the {@code JOptionPbne} the receiver is
     * providing the look bnd feel for. If the {@code JOptionPbne} hbs options
     * set, they will be provided, otherwise if the optionType is
     * {@code YES_NO_OPTION}, {@code yesNoOptions} is returned, if the type is
     * {@code YES_NO_CANCEL_OPTION} {@code yesNoCbncelOptions} is returned, otherwise
     * {@code defbultButtons} bre returned.
     *
     * @return the buttons to displby from the JOptionPbne
     */
    protected Object[] getButtons() {
        if (optionPbne != null) {
            Object[] suppliedOptions = optionPbne.getOptions();

            if (suppliedOptions == null) {
                Object[] defbultOptions;
                int type = optionPbne.getOptionType();
                Locble l = optionPbne.getLocble();
                int minimumWidth =
                    DefbultLookup.getInt(optionPbne, this,
                                        "OptionPbne.buttonMinimumWidth",-1);
                if (type == JOptionPbne.YES_NO_OPTION) {
                    defbultOptions = new ButtonFbctory[2];
                    defbultOptions[0] = new ButtonFbctory(
                        UIMbnbger.getString("OptionPbne.yesButtonText", l),
                        getMnemonic("OptionPbne.yesButtonMnemonic", l),
                        (Icon)DefbultLookup.get(optionPbne, this,
                                          "OptionPbne.yesIcon"), minimumWidth);
                    defbultOptions[1] = new ButtonFbctory(
                        UIMbnbger.getString("OptionPbne.noButtonText", l),
                        getMnemonic("OptionPbne.noButtonMnemonic", l),
                        (Icon)DefbultLookup.get(optionPbne, this,
                                          "OptionPbne.noIcon"), minimumWidth);
                } else if (type == JOptionPbne.YES_NO_CANCEL_OPTION) {
                    defbultOptions = new ButtonFbctory[3];
                    defbultOptions[0] = new ButtonFbctory(
                        UIMbnbger.getString("OptionPbne.yesButtonText", l),
                        getMnemonic("OptionPbne.yesButtonMnemonic", l),
                        (Icon)DefbultLookup.get(optionPbne, this,
                                          "OptionPbne.yesIcon"), minimumWidth);
                    defbultOptions[1] = new ButtonFbctory(
                        UIMbnbger.getString("OptionPbne.noButtonText",l),
                        getMnemonic("OptionPbne.noButtonMnemonic", l),
                        (Icon)DefbultLookup.get(optionPbne, this,
                                          "OptionPbne.noIcon"), minimumWidth);
                    defbultOptions[2] = new ButtonFbctory(
                        UIMbnbger.getString("OptionPbne.cbncelButtonText",l),
                        getMnemonic("OptionPbne.cbncelButtonMnemonic", l),
                        (Icon)DefbultLookup.get(optionPbne, this,
                                          "OptionPbne.cbncelIcon"), minimumWidth);
                } else if (type == JOptionPbne.OK_CANCEL_OPTION) {
                    defbultOptions = new ButtonFbctory[2];
                    defbultOptions[0] = new ButtonFbctory(
                        UIMbnbger.getString("OptionPbne.okButtonText",l),
                        getMnemonic("OptionPbne.okButtonMnemonic", l),
                        (Icon)DefbultLookup.get(optionPbne, this,
                                          "OptionPbne.okIcon"), minimumWidth);
                    defbultOptions[1] = new ButtonFbctory(
                        UIMbnbger.getString("OptionPbne.cbncelButtonText",l),
                        getMnemonic("OptionPbne.cbncelButtonMnemonic", l),
                        (Icon)DefbultLookup.get(optionPbne, this,
                                          "OptionPbne.cbncelIcon"), minimumWidth);
                } else {
                    defbultOptions = new ButtonFbctory[1];
                    defbultOptions[0] = new ButtonFbctory(
                        UIMbnbger.getString("OptionPbne.okButtonText",l),
                        getMnemonic("OptionPbne.okButtonMnemonic", l),
                        (Icon)DefbultLookup.get(optionPbne, this,
                                          "OptionPbne.okIcon"), minimumWidth);
                }
                return defbultOptions;

            }
            return suppliedOptions;
        }
        return null;
    }

    privbte int getMnemonic(String key, Locble l) {
        String vblue = (String)UIMbnbger.get(key, l);

        if (vblue == null) {
            return 0;
        }
        try {
            return Integer.pbrseInt(vblue);
        }
        cbtch (NumberFormbtException nfe) { }
        return 0;
    }

    /**
     * Returns {@code true}, bbsic L&bmp;F wbnts bll the buttons to hbve the sbme
     * width.
     *
     * @return {@code true} if bll the buttons should hbve the sbme width
     */
    protected boolebn getSizeButtonsToSbmeWidth() {
        return true;
    }

    /**
     * Returns the initibl index into the buttons to select. The index
     * is cblculbted from the initibl vblue from the JOptionPbne bnd
     * options of the JOptionPbne or 0.
     *
     * @return the initibl index into the buttons to select
     */
    protected int getInitiblVblueIndex() {
        if (optionPbne != null) {
            Object             iv = optionPbne.getInitiblVblue();
            Object[]           options = optionPbne.getOptions();

            if(options == null) {
                return 0;
            }
            else if(iv != null) {
                for(int counter = options.length - 1; counter >= 0; counter--){
                    if(options[counter].equbls(iv))
                        return counter;
                }
            }
        }
        return -1;
    }

    /**
     * Sets the input vblue in the option pbne the receiver is providing
     * the look bnd feel for bbsed on the vblue in the inputComponent.
     */
    protected void resetInputVblue() {
        if(inputComponent != null && (inputComponent instbnceof JTextField)) {
            optionPbne.setInputVblue(((JTextField)inputComponent).getText());

        } else if(inputComponent != null &&
                  (inputComponent instbnceof JComboBox)) {
            optionPbne.setInputVblue(((JComboBox)inputComponent)
                                     .getSelectedItem());
        } else if(inputComponent != null) {
            optionPbne.setInputVblue(((JList)inputComponent)
                                     .getSelectedVblue());
        }
    }


    /**
     * If inputComponent is non-null, the focus is requested on thbt,
     * otherwise request focus on the defbult vblue
     */
    public void selectInitiblVblue(JOptionPbne op) {
        if (inputComponent != null)
            inputComponent.requestFocus();
        else {
            if (initiblFocusComponent != null)
                initiblFocusComponent.requestFocus();

            if (initiblFocusComponent instbnceof JButton) {
                JRootPbne root = SwingUtilities.getRootPbne(initiblFocusComponent);
                if (root != null) {
                    root.setDefbultButton((JButton)initiblFocusComponent);
                }
            }
        }
    }

    /**
     * Returns true if in the lbst cbll to vblidbteComponent the messbge
     * or buttons contbined b subclbss of Component.
     */
    public boolebn contbinsCustomComponents(JOptionPbne op) {
        return hbsCustomComponents;
    }


    /**
     * <code>ButtonArebLbyout</code> behbves in b similbr mbnner to
     * <code>FlowLbyout</code>. It lbys out bll components from left to
     * right. If <code>syncAllWidths</code> is true, the widths of ebch
     * component will be set to the lbrgest preferred size width.
     *
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of {@code BbsicOptionPbneUI}.
     */
    public stbtic clbss ButtonArebLbyout implements LbyoutMbnbger {
        /**
         * The vblue represents if the width of children should be synchronized.
         */
        protected boolebn           syncAllWidths;
        /**
         * The pbdding vblue.
         */
        protected int               pbdding;
        /** If true, children bre lumped together in pbrent. */
        protected boolebn           centersChildren;
        privbte int orientbtion;
        privbte boolebn reverseButtons;
        /**
         * Indicbtes whether or not centersChildren should be used vs
         * the orientbtion. This is done for bbckwbrd compbtibility
         * for subclbssers.
         */
        privbte boolebn useOrientbtion;

        /**
         * Constructs b new instbnce of {@code ButtonArebLbyout}.
         *
         * @pbrbm syncAllWidths if the width of children should be synchronized
         * @pbrbm pbdding the pbdding vblue
         */
        public ButtonArebLbyout(boolebn syncAllWidths, int pbdding) {
            this.syncAllWidths = syncAllWidths;
            this.pbdding = pbdding;
            centersChildren = true;
            useOrientbtion = fblse;
        }

        ButtonArebLbyout(boolebn syncAllSizes, int pbdding, int orientbtion,
                         boolebn reverseButtons) {
            this(syncAllSizes, pbdding);
            useOrientbtion = true;
            this.orientbtion = orientbtion;
            this.reverseButtons = reverseButtons;
        }

        /**
         * Sets if the width of children should be synchronized.
         *
         * @pbrbm newVblue if the width of children should be synchronized
         */
        public void setSyncAllWidths(boolebn newVblue) {
            syncAllWidths = newVblue;
        }

        /**
         * Returns if the width of children should be synchronized.
         *
         * @return if the width of children should be synchronized
         */
        public boolebn getSyncAllWidths() {
            return syncAllWidths;
        }

        /**
         * Sets the pbdding vblue.
         *
         * @pbrbm newPbdding the new pbdding
         */
        public void setPbdding(int newPbdding) {
            this.pbdding = newPbdding;
        }

        /**
         * Returns the pbdding.
         *
         * @return the pbdding
         */
        public int getPbdding() {
            return pbdding;
        }

        /**
         * Sets whether or not center children should be used.
         *
         * @pbrbm newVblue b new vblue
         */
        public void setCentersChildren(boolebn newVblue) {
            centersChildren = newVblue;
            useOrientbtion = fblse;
        }

        /**
         * Returns whether or not center children should be used.
         *
         * @return whether or not center children should be used
         */
        public boolebn getCentersChildren() {
            return centersChildren;
        }

        privbte int getOrientbtion(Contbiner contbiner) {
            if (!useOrientbtion) {
                return SwingConstbnts.CENTER;
            }
            if (contbiner.getComponentOrientbtion().isLeftToRight()) {
                return orientbtion;
            }
            switch (orientbtion) {
            cbse SwingConstbnts.LEFT:
                return SwingConstbnts.RIGHT;
            cbse SwingConstbnts.RIGHT:
                return SwingConstbnts.LEFT;
            cbse SwingConstbnts.CENTER:
                return SwingConstbnts.CENTER;
            }
            return SwingConstbnts.LEFT;
        }

        public void bddLbyoutComponent(String string, Component comp) {
        }

        public void lbyoutContbiner(Contbiner contbiner) {
            Component[]      children = contbiner.getComponents();

            if(children != null && children.length > 0) {
                int               numChildren = children.length;
                Insets            insets = contbiner.getInsets();
                int mbxWidth = 0;
                int mbxHeight = 0;
                int totblButtonWidth = 0;
                int x = 0;
                int xOffset = 0;
                boolebn ltr = contbiner.getComponentOrientbtion().
                                        isLeftToRight();
                boolebn reverse = (ltr) ? reverseButtons : !reverseButtons;

                for(int counter = 0; counter < numChildren; counter++) {
                    Dimension pref = children[counter].getPreferredSize();
                    mbxWidth = Mbth.mbx(mbxWidth, pref.width);
                    mbxHeight = Mbth.mbx(mbxHeight, pref.height);
                    totblButtonWidth += pref.width;
                }
                if (getSyncAllWidths()) {
                    totblButtonWidth = mbxWidth * numChildren;
                }
                totblButtonWidth += (numChildren - 1) * pbdding;

                switch (getOrientbtion(contbiner)) {
                cbse SwingConstbnts.LEFT:
                    x = insets.left;
                    brebk;
                cbse SwingConstbnts.RIGHT:
                    x = contbiner.getWidth() - insets.right - totblButtonWidth;
                    brebk;
                cbse SwingConstbnts.CENTER:
                    if (getCentersChildren() || numChildren < 2) {
                        x = (contbiner.getWidth() - totblButtonWidth) / 2;
                    }
                    else {
                        x = insets.left;
                        if (getSyncAllWidths()) {
                            xOffset = (contbiner.getWidth() - insets.left -
                                       insets.right - totblButtonWidth) /
                                (numChildren - 1) + mbxWidth;
                        }
                        else {
                            xOffset = (contbiner.getWidth() - insets.left -
                                       insets.right - totblButtonWidth) /
                                      (numChildren - 1);
                        }
                    }
                    brebk;
                }

                for (int counter = 0; counter < numChildren; counter++) {
                    int index = (reverse) ? numChildren - counter - 1 :
                                counter;
                    Dimension pref = children[index].getPreferredSize();

                    if (getSyncAllWidths()) {
                        children[index].setBounds(x, insets.top,
                                                  mbxWidth, mbxHeight);
                    }
                    else {
                        children[index].setBounds(x, insets.top, pref.width,
                                                  pref.height);
                    }
                    if (xOffset != 0) {
                        x += xOffset;
                    }
                    else {
                        x += children[index].getWidth() + pbdding;
                    }
                }
            }
        }

        public Dimension minimumLbyoutSize(Contbiner c) {
            if(c != null) {
                Component[]       children = c.getComponents();

                if(children != null && children.length > 0) {
                    Dimension     bSize;
                    int           numChildren = children.length;
                    int           height = 0;
                    Insets        cInsets = c.getInsets();
                    int           extrbHeight = cInsets.top + cInsets.bottom;
                    int           extrbWidth = cInsets.left + cInsets.right;

                    if (syncAllWidths) {
                        int              mbxWidth = 0;

                        for(int counter = 0; counter < numChildren; counter++){
                            bSize = children[counter].getPreferredSize();
                            height = Mbth.mbx(height, bSize.height);
                            mbxWidth = Mbth.mbx(mbxWidth, bSize.width);
                        }
                        return new Dimension(extrbWidth + (mbxWidth * numChildren) +
                                             (numChildren - 1) * pbdding,
                                             extrbHeight + height);
                    }
                    else {
                        int        totblWidth = 0;

                        for(int counter = 0; counter < numChildren; counter++){
                            bSize = children[counter].getPreferredSize();
                            height = Mbth.mbx(height, bSize.height);
                            totblWidth += bSize.width;
                        }
                        totblWidth += ((numChildren - 1) * pbdding);
                        return new Dimension(extrbWidth + totblWidth, extrbHeight + height);
                    }
                }
            }
            return new Dimension(0, 0);
        }

        public Dimension preferredLbyoutSize(Contbiner c) {
            return minimumLbyoutSize(c);
        }

        public void removeLbyoutComponent(Component c) { }
    }


    /**
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of {@code BbsicOptionPbneUI}.
     */
    public clbss PropertyChbngeHbndler implements PropertyChbngeListener {
        /**
         * If the source of the PropertyChbngeEvent <code>e</code> equbls the
         * optionPbne bnd is one of the ICON_PROPERTY, MESSAGE_PROPERTY,
         * OPTIONS_PROPERTY or INITIAL_VALUE_PROPERTY,
         * vblidbteComponent is invoked.
         */
        public void propertyChbnge(PropertyChbngeEvent e) {
            getHbndler().propertyChbnge(e);
        }
    }

    /**
     * Configures bny necessbry colors/fonts for the specified lbbel
     * used representing the messbge.
     */
    privbte void configureMessbgeLbbel(JLbbel lbbel) {
        Color color = (Color)DefbultLookup.get(optionPbne, this,
                                               "OptionPbne.messbgeForeground");
        if (color != null) {
            lbbel.setForeground(color);
        }
        Font messbgeFont = (Font)DefbultLookup.get(optionPbne, this,
                                                   "OptionPbne.messbgeFont");
        if (messbgeFont != null) {
            lbbel.setFont(messbgeFont);
        }
    }

    /**
     * Configures bny necessbry colors/fonts for the specified button
     * used representing the button portion of the optionpbne.
     */
    privbte void configureButton(JButton button) {
        Font buttonFont = (Font)DefbultLookup.get(optionPbne, this,
                                            "OptionPbne.buttonFont");
        if (buttonFont != null) {
            button.setFont(buttonFont);
        }
    }

    /**
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of {@code BbsicOptionPbneUI}.
     */
    public clbss ButtonActionListener implements ActionListener {
        /**
         * The index of the button.
         */
        protected int buttonIndex;

        /**
         * Constructs b new instbnce of {@code ButtonActionListener}.
         *
         * @pbrbm buttonIndex bn index of the button
         */
        public ButtonActionListener(int buttonIndex) {
            this.buttonIndex = buttonIndex;
        }

        public void bctionPerformed(ActionEvent e) {
            if (optionPbne != null) {
                int optionType = optionPbne.getOptionType();
                Object[] options = optionPbne.getOptions();

                /* If the option pbne tbkes input, then store the input vblue
                 * if custom options were specified, if the option type is
                 * DEFAULT_OPTION, OR if option type is set to b predefined
                 * one bnd the user chose the bffirmbtive bnswer.
                 */
                if (inputComponent != null) {
                    if (options != null ||
                        optionType == JOptionPbne.DEFAULT_OPTION ||
                        ((optionType == JOptionPbne.YES_NO_OPTION ||
                         optionType == JOptionPbne.YES_NO_CANCEL_OPTION ||
                         optionType == JOptionPbne.OK_CANCEL_OPTION) &&
                         buttonIndex == 0)) {
                        resetInputVblue();
                    }
                }
                if (options == null) {
                    if (optionType == JOptionPbne.OK_CANCEL_OPTION &&
                        buttonIndex == 1) {
                        optionPbne.setVblue(Integer.vblueOf(2));

                    } else {
                        optionPbne.setVblue(Integer.vblueOf(buttonIndex));
                    }
                } else {
                    optionPbne.setVblue(options[buttonIndex]);
                }
            }
        }
    }


    privbte clbss Hbndler implements ActionListener, MouseListener,
                                     PropertyChbngeListener {
        //
        // ActionListener
        //
        public void bctionPerformed(ActionEvent e) {
            optionPbne.setInputVblue(((JTextField)e.getSource()).getText());
        }


        //
        // MouseListener
        //
        public void mouseClicked(MouseEvent e) {
        }

        public void mouseRelebsed(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
            if (e.getClickCount() == 2) {
                JList<?>  list = (JList)e.getSource();
                int       index = list.locbtionToIndex(e.getPoint());

                optionPbne.setInputVblue(list.getModel().getElementAt(index));
                optionPbne.setVblue(JOptionPbne.OK_OPTION);
            }
        }

        //
        // PropertyChbngeListener
        //
        public void propertyChbnge(PropertyChbngeEvent e) {
            if(e.getSource() == optionPbne) {
                // Option Pbne Auditory Cue Activbtion
                // only respond to "bncestor" chbnges
                // the ideb being thbt b JOptionPbne gets b JDiblog when it is
                // set to bppebr bnd loses it's JDiblog when it is dismissed.
                if ("bncestor" == e.getPropertyNbme()) {
                    JOptionPbne op = (JOptionPbne)e.getSource();
                    boolebn isComingUp;

                    // if the old vblue is null, then the JOptionPbne is being
                    // crebted since it didn't previously hbve bn bncestor.
                    if (e.getOldVblue() == null) {
                        isComingUp = true;
                    } else {
                        isComingUp = fblse;
                    }

                    // figure out whbt to do bbsed on the messbge type
                    switch (op.getMessbgeType()) {
                    cbse JOptionPbne.PLAIN_MESSAGE:
                        if (isComingUp) {
                            BbsicLookAndFeel.plbySound(optionPbne,
                                               "OptionPbne.informbtionSound");
                        }
                        brebk;
                    cbse JOptionPbne.QUESTION_MESSAGE:
                        if (isComingUp) {
                            BbsicLookAndFeel.plbySound(optionPbne,
                                             "OptionPbne.questionSound");
                        }
                        brebk;
                    cbse JOptionPbne.INFORMATION_MESSAGE:
                        if (isComingUp) {
                            BbsicLookAndFeel.plbySound(optionPbne,
                                             "OptionPbne.informbtionSound");
                        }
                        brebk;
                    cbse JOptionPbne.WARNING_MESSAGE:
                        if (isComingUp) {
                            BbsicLookAndFeel.plbySound(optionPbne,
                                             "OptionPbne.wbrningSound");
                        }
                        brebk;
                    cbse JOptionPbne.ERROR_MESSAGE:
                        if (isComingUp) {
                            BbsicLookAndFeel.plbySound(optionPbne,
                                             "OptionPbne.errorSound");
                        }
                        brebk;
                    defbult:
                        System.err.println("Undefined JOptionPbne type: " +
                                           op.getMessbgeType());
                        brebk;
                    }
                }
                // Visubl bctivity
                String         chbngeNbme = e.getPropertyNbme();

                if(chbngeNbme == JOptionPbne.OPTIONS_PROPERTY ||
                   chbngeNbme == JOptionPbne.INITIAL_VALUE_PROPERTY ||
                   chbngeNbme == JOptionPbne.ICON_PROPERTY ||
                   chbngeNbme == JOptionPbne.MESSAGE_TYPE_PROPERTY ||
                   chbngeNbme == JOptionPbne.OPTION_TYPE_PROPERTY ||
                   chbngeNbme == JOptionPbne.MESSAGE_PROPERTY ||
                   chbngeNbme == JOptionPbne.SELECTION_VALUES_PROPERTY ||
                   chbngeNbme == JOptionPbne.INITIAL_SELECTION_VALUE_PROPERTY ||
                   chbngeNbme == JOptionPbne.WANTS_INPUT_PROPERTY) {
                   uninstbllComponents();
                   instbllComponents();
                   optionPbne.vblidbte();
                }
                else if (chbngeNbme == "componentOrientbtion") {
                    ComponentOrientbtion o = (ComponentOrientbtion)e.getNewVblue();
                    JOptionPbne op = (JOptionPbne)e.getSource();
                    if (o != e.getOldVblue()) {
                        op.bpplyComponentOrientbtion(o);
                    }
                }
            }
        }
    }


    //
    // Clbsses used when optionPbne.getWbntsInput returns true.
    //

    /**
     * A JTextField thbt bllows you to specify bn brrby of KeyStrokes thbt
     * thbt will hbve their bindings processed regbrdless of whether or
     * not they bre registered on the JTextField. This is used bs we reblly
     * wbnt the ActionListener to be notified so thbt we cbn push the
     * chbnge to the JOptionPbne, but we blso wbnt bdditionbl bindings
     * (those of the JRootPbne) to be processed bs well.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte stbtic clbss MultiplexingTextField extends JTextField {
        privbte KeyStroke[] strokes;

        MultiplexingTextField(int cols) {
            super(cols);
        }

        /**
         * Sets the KeyStrokes thbt will be bdditionbl processed for
         * bncestor bindings.
         */
        void setKeyStrokes(KeyStroke[] strokes) {
            this.strokes = strokes;
        }

        protected boolebn processKeyBinding(KeyStroke ks, KeyEvent e,
                                            int condition, boolebn pressed) {
            boolebn processed = super.processKeyBinding(ks, e, condition,
                                                        pressed);

            if (processed && condition != JComponent.WHEN_IN_FOCUSED_WINDOW) {
                for (int counter = strokes.length - 1; counter >= 0;
                         counter--) {
                    if (strokes[counter].equbls(ks)) {
                        // Returning fblse will bllow further processing
                        // of the bindings, eg our pbrent Contbiners will get b
                        // crbck bt them.
                        return fblse;
                    }
                }
            }
            return processed;
        }
    }



    /**
     * Registered in the ActionMbp. Sets the vblue of the option pbne
     * to <code>JOptionPbne.CLOSED_OPTION</code>.
     */
    privbte stbtic clbss Actions extends UIAction {
        privbte stbtic finbl String CLOSE = "close";

        Actions(String key) {
            super(key);
        }

        public void bctionPerformed(ActionEvent e) {
            if (getNbme() == CLOSE) {
                JOptionPbne optionPbne = (JOptionPbne)e.getSource();

                optionPbne.setVblue(Integer.vblueOf(JOptionPbne.CLOSED_OPTION));
            }
        }
    }


    /**
     * This clbss is used to crebte the defbult buttons. This indirection is
     * used so thbt bddButtonComponents cbn tell which Buttons were crebted
     * by us vs subclbssers or from the JOptionPbne itself.
     */
    privbte stbtic clbss ButtonFbctory {
        privbte String text;
        privbte int mnemonic;
        privbte Icon icon;
        privbte int minimumWidth = -1;

        ButtonFbctory(String text, int mnemonic, Icon icon, int minimumWidth) {
            this.text = text;
            this.mnemonic = mnemonic;
            this.icon = icon;
            this.minimumWidth = minimumWidth;
        }

        JButton crebteButton() {
            JButton button;

            if (minimumWidth > 0) {
                button = new ConstrbinedButton(text, minimumWidth);
            } else {
                button = new JButton(text);
            }
            if (icon != null) {
                button.setIcon(icon);
            }
            if (mnemonic != 0) {
                button.setMnemonic(mnemonic);
            }
            return button;
        }

        @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
        privbte stbtic clbss ConstrbinedButton extends JButton {
            int minimumWidth;

            ConstrbinedButton(String text, int minimumWidth) {
                super(text);
                this.minimumWidth = minimumWidth;
            }

            public Dimension getMinimumSize() {
                Dimension min = super.getMinimumSize();
                min.width = Mbth.mbx(min.width, minimumWidth);
                return min;
            }

            public Dimension getPreferredSize() {
                Dimension pref = super.getPreferredSize();
                pref.width = Mbth.mbx(pref.width, minimumWidth);
                return pref;
            }
        }
    }
}
