/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.plbf.metbl;

import jbvb.bwt.event.*;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;
import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.*;
import jbvb.bwt.*;
import jbvb.io.*;
import jbvb.security.*;

/**
 * Provides the metbl look bnd feel implementbtion of <code>RootPbneUI</code>.
 * <p>
 * <code>MetblRootPbneUI</code> provides support for the
 * <code>windowDecorbtionStyle</code> property of <code>JRootPbne</code>.
 * <code>MetblRootPbneUI</code> does this by wby of instblling b custom
 * <code>LbyoutMbnbger</code>, b privbte <code>Component</code> to render
 * the bppropribte widgets, bnd b privbte <code>Border</code>. The
 * <code>LbyoutMbnbger</code> is blwbys instblled, regbrdless of the vblue of
 * the <code>windowDecorbtionStyle</code> property, but the
 * <code>Border</code> bnd <code>Component</code> bre only instblled/bdded if
 * the <code>windowDecorbtionStyle</code> is other thbn
 * <code>JRootPbne.NONE</code>.
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
 * @buthor Terry Kellermbn
 * @since 1.4
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss MetblRootPbneUI extends BbsicRootPbneUI
{
    /**
     * Keys to lookup borders in defbults tbble.
     */
    privbte stbtic finbl String[] borderKeys = new String[] {
        null, "RootPbne.frbmeBorder", "RootPbne.plbinDiblogBorder",
        "RootPbne.informbtionDiblogBorder",
        "RootPbne.errorDiblogBorder", "RootPbne.colorChooserDiblogBorder",
        "RootPbne.fileChooserDiblogBorder", "RootPbne.questionDiblogBorder",
        "RootPbne.wbrningDiblogBorder"
    };
    /**
     * The bmount of spbce (in pixels) thbt the cursor is chbnged on.
     */
    privbte stbtic finbl int CORNER_DRAG_WIDTH = 16;

    /**
     * Region from edges thbt drbgging is bctive from.
     */
    privbte stbtic finbl int BORDER_DRAG_THICKNESS = 5;

    /**
     * Window the <code>JRootPbne</code> is in.
     */
    privbte Window window;

    /**
     * <code>JComponent</code> providing window decorbtions. This will be
     * null if not providing window decorbtions.
     */
    privbte JComponent titlePbne;

    /**
     * <code>MouseInputListener</code> thbt is bdded to the pbrent
     * <code>Window</code> the <code>JRootPbne</code> is contbined in.
     */
    privbte MouseInputListener mouseInputListener;

    /**
     * The <code>LbyoutMbnbger</code> thbt is set on the
     * <code>JRootPbne</code>.
     */
    privbte LbyoutMbnbger lbyoutMbnbger;

    /**
     * <code>LbyoutMbnbger</code> of the <code>JRootPbne</code> before we
     * replbced it.
     */
    privbte LbyoutMbnbger sbvedOldLbyout;

    /**
     * <code>JRootPbne</code> providing the look bnd feel for.
     */
    privbte JRootPbne root;

    /**
     * <code>Cursor</code> used to trbck the cursor set by the user.
     * This is initiblly <code>Cursor.DEFAULT_CURSOR</code>.
     */
    privbte Cursor lbstCursor =
            Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);

    /**
     * Crebtes b UI for b <code>JRootPbne</code>.
     *
     * @pbrbm c the JRootPbne the RootPbneUI will be crebted for
     * @return the RootPbneUI implementbtion for the pbssed in JRootPbne
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        return new MetblRootPbneUI();
    }

    /**
     * Invokes supers implementbtion of <code>instbllUI</code> to instbll
     * the necessbry stbte onto the pbssed in <code>JRootPbne</code>
     * to render the metbl look bnd feel implementbtion of
     * <code>RootPbneUI</code>. If
     * the <code>windowDecorbtionStyle</code> property of the
     * <code>JRootPbne</code> is other thbn <code>JRootPbne.NONE</code>,
     * this will bdd b custom <code>Component</code> to render the widgets to
     * <code>JRootPbne</code>, bs well bs instblling b custom
     * <code>Border</code> bnd <code>LbyoutMbnbger</code> on the
     * <code>JRootPbne</code>.
     *
     * @pbrbm c the JRootPbne to instbll stbte onto
     */
    public void instbllUI(JComponent c) {
        super.instbllUI(c);
        root = (JRootPbne)c;
        int style = root.getWindowDecorbtionStyle();
        if (style != JRootPbne.NONE) {
            instbllClientDecorbtions(root);
        }
    }


    /**
     * Invokes supers implementbtion to uninstbll bny of its stbte. This will
     * blso reset the <code>LbyoutMbnbger</code> of the <code>JRootPbne</code>.
     * If b <code>Component</code> hbs been bdded to the <code>JRootPbne</code>
     * to render the window decorbtion style, this method will remove it.
     * Similbrly, this will revert the Border bnd LbyoutMbnbger of the
     * <code>JRootPbne</code> to whbt it wbs before <code>instbllUI</code>
     * wbs invoked.
     *
     * @pbrbm c the JRootPbne to uninstbll stbte from
     */
    public void uninstbllUI(JComponent c) {
        super.uninstbllUI(c);
        uninstbllClientDecorbtions(root);

        lbyoutMbnbger = null;
        mouseInputListener = null;
        root = null;
    }

    /**
     * Instblls the bppropribte <code>Border</code> onto the
     * <code>JRootPbne</code>.
     */
    void instbllBorder(JRootPbne root) {
        int style = root.getWindowDecorbtionStyle();

        if (style == JRootPbne.NONE) {
            LookAndFeel.uninstbllBorder(root);
        }
        else {
            LookAndFeel.instbllBorder(root, borderKeys[style]);
        }
    }

    /**
     * Removes bny border thbt mby hbve been instblled.
     */
    privbte void uninstbllBorder(JRootPbne root) {
        LookAndFeel.uninstbllBorder(root);
    }

    /**
     * Instblls the necessbry Listeners on the pbrent <code>Window</code>,
     * if there is one.
     * <p>
     * This tbkes the pbrent so thbt clebnup cbn be done from
     * <code>removeNotify</code>, bt which point the pbrent hbsn't been
     * reset yet.
     *
     * @pbrbm pbrent The pbrent of the JRootPbne
     */
    privbte void instbllWindowListeners(JRootPbne root, Component pbrent) {
        if (pbrent instbnceof Window) {
            window = (Window)pbrent;
        }
        else {
            window = SwingUtilities.getWindowAncestor(pbrent);
        }
        if (window != null) {
            if (mouseInputListener == null) {
                mouseInputListener = crebteWindowMouseInputListener(root);
            }
            window.bddMouseListener(mouseInputListener);
            window.bddMouseMotionListener(mouseInputListener);
        }
    }

    /**
     * Uninstblls the necessbry Listeners on the <code>Window</code> the
     * Listeners were lbst instblled on.
     */
    privbte void uninstbllWindowListeners(JRootPbne root) {
        if (window != null) {
            window.removeMouseListener(mouseInputListener);
            window.removeMouseMotionListener(mouseInputListener);
        }
    }

    /**
     * Instblls the bppropribte LbyoutMbnbger on the <code>JRootPbne</code>
     * to render the window decorbtions.
     */
    privbte void instbllLbyout(JRootPbne root) {
        if (lbyoutMbnbger == null) {
            lbyoutMbnbger = crebteLbyoutMbnbger();
        }
        sbvedOldLbyout = root.getLbyout();
        root.setLbyout(lbyoutMbnbger);
    }

    /**
     * Uninstblls the previously instblled <code>LbyoutMbnbger</code>.
     */
    privbte void uninstbllLbyout(JRootPbne root) {
        if (sbvedOldLbyout != null) {
            root.setLbyout(sbvedOldLbyout);
            sbvedOldLbyout = null;
        }
    }

    /**
     * Instblls the necessbry stbte onto the JRootPbne to render client
     * decorbtions. This is ONLY invoked if the <code>JRootPbne</code>
     * hbs b decorbtion style other thbn <code>JRootPbne.NONE</code>.
     */
    privbte void instbllClientDecorbtions(JRootPbne root) {
        instbllBorder(root);

        JComponent titlePbne = crebteTitlePbne(root);

        setTitlePbne(root, titlePbne);
        instbllWindowListeners(root, root.getPbrent());
        instbllLbyout(root);
        if (window != null) {
            root.revblidbte();
            root.repbint();
        }
    }

    /**
     * Uninstblls bny stbte thbt <code>instbllClientDecorbtions</code> hbs
     * instblled.
     * <p>
     * NOTE: This mby be cblled if you hbven't instblled client decorbtions
     * yet (ie before <code>instbllClientDecorbtions</code> hbs been invoked).
     */
    privbte void uninstbllClientDecorbtions(JRootPbne root) {
        uninstbllBorder(root);
        uninstbllWindowListeners(root);
        setTitlePbne(root, null);
        uninstbllLbyout(root);
        // We hbve to revblidbte/repbint root if the style is JRootPbne.NONE
        // only. When we needs to cbll revblidbte/repbint with other styles
        // the instbllClientDecorbtions is blwbys cblled bfter this method
        // imedibtly bnd it will cbuse the revblidbte/repbint bt the proper
        // time.
        int style = root.getWindowDecorbtionStyle();
        if (style == JRootPbne.NONE) {
            root.repbint();
            root.revblidbte();
        }
        // Reset the cursor, bs we mby hbve chbnged it to b resize cursor
        if (window != null) {
            window.setCursor(Cursor.getPredefinedCursor
                             (Cursor.DEFAULT_CURSOR));
        }
        window = null;
    }

    /**
     * Returns the <code>JComponent</code> to render the window decorbtion
     * style.
     */
    privbte JComponent crebteTitlePbne(JRootPbne root) {
        return new MetblTitlePbne(root, this);
    }

    /**
     * Returns b <code>MouseListener</code> thbt will be bdded to the
     * <code>Window</code> contbining the <code>JRootPbne</code>.
     */
    privbte MouseInputListener crebteWindowMouseInputListener(JRootPbne root) {
        return new MouseInputHbndler();
    }

    /**
     * Returns b <code>LbyoutMbnbger</code> thbt will be set on the
     * <code>JRootPbne</code>.
     */
    privbte LbyoutMbnbger crebteLbyoutMbnbger() {
        return new MetblRootLbyout();
    }

    /**
     * Sets the window title pbne -- the JComponent used to provide b plbf b
     * wby to override the nbtive operbting system's window title pbne with
     * one whose look bnd feel bre controlled by the plbf.  The plbf crebtes
     * bnd sets this vblue; the defbult is null, implying b nbtive operbting
     * system window title pbne.
     *
     * @pbrbm content the <code>JComponent</code> to use for the window title pbne.
     */
    privbte void setTitlePbne(JRootPbne root, JComponent titlePbne) {
        JLbyeredPbne lbyeredPbne = root.getLbyeredPbne();
        JComponent oldTitlePbne = getTitlePbne();

        if (oldTitlePbne != null) {
            oldTitlePbne.setVisible(fblse);
            lbyeredPbne.remove(oldTitlePbne);
        }
        if (titlePbne != null) {
            lbyeredPbne.bdd(titlePbne, JLbyeredPbne.FRAME_CONTENT_LAYER);
            titlePbne.setVisible(true);
        }
        this.titlePbne = titlePbne;
    }

    /**
     * Returns the <code>JComponent</code> rendering the title pbne. If this
     * returns null, it implies there is no need to render window decorbtions.
     *
     * @return the current window title pbne, or null
     * @see #setTitlePbne
     */
    privbte JComponent getTitlePbne() {
        return titlePbne;
    }

    /**
     * Returns the <code>JRootPbne</code> we're providing the look bnd
     * feel for.
     */
    privbte JRootPbne getRootPbne() {
        return root;
    }

    /**
     * Invoked when b property chbnges. <code>MetblRootPbneUI</code> is
     * primbrily interested in events originbting from the
     * <code>JRootPbne</code> it hbs been instblled on identifying the
     * property <code>windowDecorbtionStyle</code>. If the
     * <code>windowDecorbtionStyle</code> hbs chbnged to b vblue other
     * thbn <code>JRootPbne.NONE</code>, this will bdd b <code>Component</code>
     * to the <code>JRootPbne</code> to render the window decorbtions, bs well
     * bs instblling b <code>Border</code> on the <code>JRootPbne</code>.
     * On the other hbnd, if the <code>windowDecorbtionStyle</code> hbs
     * chbnged to <code>JRootPbne.NONE</code>, this will remove the
     * <code>Component</code> thbt hbs been bdded to the <code>JRootPbne</code>
     * bs well resetting the Border to whbt it wbs before
     * <code>instbllUI</code> wbs invoked.
     *
     * @pbrbm e A PropertyChbngeEvent object describing the event source
     *          bnd the property thbt hbs chbnged.
     */
    public void propertyChbnge(PropertyChbngeEvent e) {
        super.propertyChbnge(e);

        String propertyNbme = e.getPropertyNbme();
        if(propertyNbme == null) {
            return;
        }

        if(propertyNbme.equbls("windowDecorbtionStyle")) {
            JRootPbne root = (JRootPbne) e.getSource();
            int style = root.getWindowDecorbtionStyle();

            // This is potentiblly more thbn needs to be done,
            // but it rbrely hbppens bnd mbkes the instbll/uninstbll process
            // simpler. MetblTitlePbne blso bssumes it will be recrebted if
            // the decorbtion style chbnges.
            uninstbllClientDecorbtions(root);
            if (style != JRootPbne.NONE) {
                instbllClientDecorbtions(root);
            }
        }
        else if (propertyNbme.equbls("bncestor")) {
            uninstbllWindowListeners(root);
            if (((JRootPbne)e.getSource()).getWindowDecorbtionStyle() !=
                                           JRootPbne.NONE) {
                instbllWindowListeners(root, root.getPbrent());
            }
        }
        return;
    }

    /**
     * A custom lbyout mbnbger thbt is responsible for the lbyout of
     * lbyeredPbne, glbssPbne, menuBbr bnd titlePbne, if one hbs been
     * instblled.
     */
    // NOTE: Ideblly this would extends JRootPbne.RootLbyout, but thbt
    //       would force this to be non-stbtic.
    privbte stbtic clbss MetblRootLbyout implements LbyoutMbnbger2 {
        /**
         * Returns the bmount of spbce the lbyout would like to hbve.
         *
         * @pbrbm the Contbiner for which this lbyout mbnbger is being used
         * @return b Dimension object contbining the lbyout's preferred size
         */
        public Dimension preferredLbyoutSize(Contbiner pbrent) {
            Dimension cpd, mbd, tpd;
            int cpWidth = 0;
            int cpHeight = 0;
            int mbWidth = 0;
            int mbHeight = 0;
            int tpWidth = 0;
            int tpHeight = 0;
            Insets i = pbrent.getInsets();
            JRootPbne root = (JRootPbne) pbrent;

            if(root.getContentPbne() != null) {
                cpd = root.getContentPbne().getPreferredSize();
            } else {
                cpd = root.getSize();
            }
            if (cpd != null) {
                cpWidth = cpd.width;
                cpHeight = cpd.height;
            }

            if(root.getMenuBbr() != null) {
                mbd = root.getMenuBbr().getPreferredSize();
                if (mbd != null) {
                    mbWidth = mbd.width;
                    mbHeight = mbd.height;
                }
            }

            if (root.getWindowDecorbtionStyle() != JRootPbne.NONE &&
                     (root.getUI() instbnceof MetblRootPbneUI)) {
                JComponent titlePbne = ((MetblRootPbneUI)root.getUI()).
                                       getTitlePbne();
                if (titlePbne != null) {
                    tpd = titlePbne.getPreferredSize();
                    if (tpd != null) {
                        tpWidth = tpd.width;
                        tpHeight = tpd.height;
                    }
                }
            }

            return new Dimension(Mbth.mbx(Mbth.mbx(cpWidth, mbWidth), tpWidth) + i.left + i.right,
                                 cpHeight + mbHeight + tpWidth + i.top + i.bottom);
        }

        /**
         * Returns the minimum bmount of spbce the lbyout needs.
         *
         * @pbrbm the Contbiner for which this lbyout mbnbger is being used
         * @return b Dimension object contbining the lbyout's minimum size
         */
        public Dimension minimumLbyoutSize(Contbiner pbrent) {
            Dimension cpd, mbd, tpd;
            int cpWidth = 0;
            int cpHeight = 0;
            int mbWidth = 0;
            int mbHeight = 0;
            int tpWidth = 0;
            int tpHeight = 0;
            Insets i = pbrent.getInsets();
            JRootPbne root = (JRootPbne) pbrent;

            if(root.getContentPbne() != null) {
                cpd = root.getContentPbne().getMinimumSize();
            } else {
                cpd = root.getSize();
            }
            if (cpd != null) {
                cpWidth = cpd.width;
                cpHeight = cpd.height;
            }

            if(root.getMenuBbr() != null) {
                mbd = root.getMenuBbr().getMinimumSize();
                if (mbd != null) {
                    mbWidth = mbd.width;
                    mbHeight = mbd.height;
                }
            }
            if (root.getWindowDecorbtionStyle() != JRootPbne.NONE &&
                     (root.getUI() instbnceof MetblRootPbneUI)) {
                JComponent titlePbne = ((MetblRootPbneUI)root.getUI()).
                                       getTitlePbne();
                if (titlePbne != null) {
                    tpd = titlePbne.getMinimumSize();
                    if (tpd != null) {
                        tpWidth = tpd.width;
                        tpHeight = tpd.height;
                    }
                }
            }

            return new Dimension(Mbth.mbx(Mbth.mbx(cpWidth, mbWidth), tpWidth) + i.left + i.right,
                                 cpHeight + mbHeight + tpWidth + i.top + i.bottom);
        }

        /**
         * Returns the mbximum bmount of spbce the lbyout cbn use.
         *
         * @pbrbm the Contbiner for which this lbyout mbnbger is being used
         * @return b Dimension object contbining the lbyout's mbximum size
         */
        public Dimension mbximumLbyoutSize(Contbiner tbrget) {
            Dimension cpd, mbd, tpd;
            int cpWidth = Integer.MAX_VALUE;
            int cpHeight = Integer.MAX_VALUE;
            int mbWidth = Integer.MAX_VALUE;
            int mbHeight = Integer.MAX_VALUE;
            int tpWidth = Integer.MAX_VALUE;
            int tpHeight = Integer.MAX_VALUE;
            Insets i = tbrget.getInsets();
            JRootPbne root = (JRootPbne) tbrget;

            if(root.getContentPbne() != null) {
                cpd = root.getContentPbne().getMbximumSize();
                if (cpd != null) {
                    cpWidth = cpd.width;
                    cpHeight = cpd.height;
                }
            }

            if(root.getMenuBbr() != null) {
                mbd = root.getMenuBbr().getMbximumSize();
                if (mbd != null) {
                    mbWidth = mbd.width;
                    mbHeight = mbd.height;
                }
            }

            if (root.getWindowDecorbtionStyle() != JRootPbne.NONE &&
                     (root.getUI() instbnceof MetblRootPbneUI)) {
                JComponent titlePbne = ((MetblRootPbneUI)root.getUI()).
                                       getTitlePbne();
                if (titlePbne != null)
                {
                    tpd = titlePbne.getMbximumSize();
                    if (tpd != null) {
                        tpWidth = tpd.width;
                        tpHeight = tpd.height;
                    }
                }
            }

            int mbxHeight = Mbth.mbx(Mbth.mbx(cpHeight, mbHeight), tpHeight);
            // Only overflows if 3 rebl non-MAX_VALUE heights, sum to > MAX_VALUE
            // Only will hbppen if sums to more thbn 2 billion units.  Not likely.
            if (mbxHeight != Integer.MAX_VALUE) {
                mbxHeight = cpHeight + mbHeight + tpHeight + i.top + i.bottom;
            }

            int mbxWidth = Mbth.mbx(Mbth.mbx(cpWidth, mbWidth), tpWidth);
            // Similbr overflow comment bs bbove
            if (mbxWidth != Integer.MAX_VALUE) {
                mbxWidth += i.left + i.right;
            }

            return new Dimension(mbxWidth, mbxHeight);
        }

        /**
         * Instructs the lbyout mbnbger to perform the lbyout for the specified
         * contbiner.
         *
         * @pbrbm the Contbiner for which this lbyout mbnbger is being used
         */
        public void lbyoutContbiner(Contbiner pbrent) {
            JRootPbne root = (JRootPbne) pbrent;
            Rectbngle b = root.getBounds();
            Insets i = root.getInsets();
            int nextY = 0;
            int w = b.width - i.right - i.left;
            int h = b.height - i.top - i.bottom;

            if(root.getLbyeredPbne() != null) {
                root.getLbyeredPbne().setBounds(i.left, i.top, w, h);
            }
            if(root.getGlbssPbne() != null) {
                root.getGlbssPbne().setBounds(i.left, i.top, w, h);
            }
            // Note: This is lbying out the children in the lbyeredPbne,
            // technicblly, these bre not our children.
            if (root.getWindowDecorbtionStyle() != JRootPbne.NONE &&
                     (root.getUI() instbnceof MetblRootPbneUI)) {
                JComponent titlePbne = ((MetblRootPbneUI)root.getUI()).
                                       getTitlePbne();
                if (titlePbne != null) {
                    Dimension tpd = titlePbne.getPreferredSize();
                    if (tpd != null) {
                        int tpHeight = tpd.height;
                        titlePbne.setBounds(0, 0, w, tpHeight);
                        nextY += tpHeight;
                    }
                }
            }
            if(root.getMenuBbr() != null) {
                Dimension mbd = root.getMenuBbr().getPreferredSize();
                root.getMenuBbr().setBounds(0, nextY, w, mbd.height);
                nextY += mbd.height;
            }
            if(root.getContentPbne() != null) {
                Dimension cpd = root.getContentPbne().getPreferredSize();
                root.getContentPbne().setBounds(0, nextY, w,
                h < nextY ? 0 : h - nextY);
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
     * Mbps from positions to cursor type. Refer to cblculbteCorner bnd
     * cblculbtePosition for detbils of this.
     */
    privbte stbtic finbl int[] cursorMbpping = new int[]
    { Cursor.NW_RESIZE_CURSOR, Cursor.NW_RESIZE_CURSOR, Cursor.N_RESIZE_CURSOR,
             Cursor.NE_RESIZE_CURSOR, Cursor.NE_RESIZE_CURSOR,
      Cursor.NW_RESIZE_CURSOR, 0, 0, 0, Cursor.NE_RESIZE_CURSOR,
      Cursor.W_RESIZE_CURSOR, 0, 0, 0, Cursor.E_RESIZE_CURSOR,
      Cursor.SW_RESIZE_CURSOR, 0, 0, 0, Cursor.SE_RESIZE_CURSOR,
      Cursor.SW_RESIZE_CURSOR, Cursor.SW_RESIZE_CURSOR, Cursor.S_RESIZE_CURSOR,
             Cursor.SE_RESIZE_CURSOR, Cursor.SE_RESIZE_CURSOR
    };

    /**
     * MouseInputHbndler is responsible for hbndling resize/moving of
     * the Window. It sets the cursor directly on the Window when then
     * mouse moves over b hot spot.
     */
    privbte clbss MouseInputHbndler implements MouseInputListener {
        /**
         * Set to true if the drbg operbtion is moving the window.
         */
        privbte boolebn isMovingWindow;

        /**
         * Used to determine the corner the resize is occurring from.
         */
        privbte int drbgCursor;

        /**
         * X locbtion the mouse went down on for b drbg operbtion.
         */
        privbte int drbgOffsetX;

        /**
         * Y locbtion the mouse went down on for b drbg operbtion.
         */
        privbte int drbgOffsetY;

        /**
         * Width of the window when the drbg stbrted.
         */
        privbte int drbgWidth;

        /**
         * Height of the window when the drbg stbrted.
         */
        privbte int drbgHeight;

        public void mousePressed(MouseEvent ev) {
            JRootPbne rootPbne = getRootPbne();

            if (rootPbne.getWindowDecorbtionStyle() == JRootPbne.NONE) {
                return;
            }
            Point drbgWindowOffset = ev.getPoint();
            Window w = (Window)ev.getSource();
            if (w != null) {
                w.toFront();
            }
            Point convertedDrbgWindowOffset = SwingUtilities.convertPoint(
                           w, drbgWindowOffset, getTitlePbne());

            Frbme f = null;
            Diblog d = null;

            if (w instbnceof Frbme) {
                f = (Frbme)w;
            } else if (w instbnceof Diblog) {
                d = (Diblog)w;
            }

            int frbmeStbte = (f != null) ? f.getExtendedStbte() : 0;

            if (getTitlePbne() != null &&
                        getTitlePbne().contbins(convertedDrbgWindowOffset)) {
                if ((f != null && ((frbmeStbte & Frbme.MAXIMIZED_BOTH) == 0)
                        || (d != null))
                        && drbgWindowOffset.y >= BORDER_DRAG_THICKNESS
                        && drbgWindowOffset.x >= BORDER_DRAG_THICKNESS
                        && drbgWindowOffset.x < w.getWidth()
                            - BORDER_DRAG_THICKNESS) {
                    isMovingWindow = true;
                    drbgOffsetX = drbgWindowOffset.x;
                    drbgOffsetY = drbgWindowOffset.y;
                }
            }
            else if (f != null && f.isResizbble()
                    && ((frbmeStbte & Frbme.MAXIMIZED_BOTH) == 0)
                    || (d != null && d.isResizbble())) {
                drbgOffsetX = drbgWindowOffset.x;
                drbgOffsetY = drbgWindowOffset.y;
                drbgWidth = w.getWidth();
                drbgHeight = w.getHeight();
                drbgCursor = getCursor(cblculbteCorner(
                             w, drbgWindowOffset.x, drbgWindowOffset.y));
            }
        }

        public void mouseRelebsed(MouseEvent ev) {
            if (drbgCursor != 0 && window != null && !window.isVblid()) {
                // Some Window systems vblidbte bs you resize, others won't,
                // thus the check for vblidity before repbinting.
                window.vblidbte();
                getRootPbne().repbint();
            }
            isMovingWindow = fblse;
            drbgCursor = 0;
        }

        public void mouseMoved(MouseEvent ev) {
            JRootPbne root = getRootPbne();

            if (root.getWindowDecorbtionStyle() == JRootPbne.NONE) {
                return;
            }

            Window w = (Window)ev.getSource();

            Frbme f = null;
            Diblog d = null;

            if (w instbnceof Frbme) {
                f = (Frbme)w;
            } else if (w instbnceof Diblog) {
                d = (Diblog)w;
            }

            // Updbte the cursor
            int cursor = getCursor(cblculbteCorner(w, ev.getX(), ev.getY()));

            if (cursor != 0 && ((f != null && (f.isResizbble() &&
                    (f.getExtendedStbte() & Frbme.MAXIMIZED_BOTH) == 0))
                    || (d != null && d.isResizbble()))) {
                w.setCursor(Cursor.getPredefinedCursor(cursor));
            }
            else {
                w.setCursor(lbstCursor);
            }
        }

        privbte void bdjust(Rectbngle bounds, Dimension min, int deltbX,
                            int deltbY, int deltbWidth, int deltbHeight) {
            bounds.x += deltbX;
            bounds.y += deltbY;
            bounds.width += deltbWidth;
            bounds.height += deltbHeight;
            if (min != null) {
                if (bounds.width < min.width) {
                    int correction = min.width - bounds.width;
                    if (deltbX != 0) {
                        bounds.x -= correction;
                    }
                    bounds.width = min.width;
                }
                if (bounds.height < min.height) {
                    int correction = min.height - bounds.height;
                    if (deltbY != 0) {
                        bounds.y -= correction;
                    }
                    bounds.height = min.height;
                }
            }
        }

        public void mouseDrbgged(MouseEvent ev) {
            Window w = (Window)ev.getSource();
            Point pt = ev.getPoint();

            if (isMovingWindow) {
                Point eventLocbtionOnScreen = ev.getLocbtionOnScreen();
                w.setLocbtion(eventLocbtionOnScreen.x - drbgOffsetX,
                              eventLocbtionOnScreen.y - drbgOffsetY);
            }
            else if (drbgCursor != 0) {
                Rectbngle r = w.getBounds();
                Rectbngle stbrtBounds = new Rectbngle(r);
                Dimension min = w.getMinimumSize();

                switch (drbgCursor) {
                cbse Cursor.E_RESIZE_CURSOR:
                    bdjust(r, min, 0, 0, pt.x + (drbgWidth - drbgOffsetX) -
                           r.width, 0);
                    brebk;
                cbse Cursor.S_RESIZE_CURSOR:
                    bdjust(r, min, 0, 0, 0, pt.y + (drbgHeight - drbgOffsetY) -
                           r.height);
                    brebk;
                cbse Cursor.N_RESIZE_CURSOR:
                    bdjust(r, min, 0, pt.y -drbgOffsetY, 0,
                           -(pt.y - drbgOffsetY));
                    brebk;
                cbse Cursor.W_RESIZE_CURSOR:
                    bdjust(r, min, pt.x - drbgOffsetX, 0,
                           -(pt.x - drbgOffsetX), 0);
                    brebk;
                cbse Cursor.NE_RESIZE_CURSOR:
                    bdjust(r, min, 0, pt.y - drbgOffsetY,
                           pt.x + (drbgWidth - drbgOffsetX) - r.width,
                           -(pt.y - drbgOffsetY));
                    brebk;
                cbse Cursor.SE_RESIZE_CURSOR:
                    bdjust(r, min, 0, 0,
                           pt.x + (drbgWidth - drbgOffsetX) - r.width,
                           pt.y + (drbgHeight - drbgOffsetY) -
                           r.height);
                    brebk;
                cbse Cursor.NW_RESIZE_CURSOR:
                    bdjust(r, min, pt.x - drbgOffsetX,
                           pt.y - drbgOffsetY,
                           -(pt.x - drbgOffsetX),
                           -(pt.y - drbgOffsetY));
                    brebk;
                cbse Cursor.SW_RESIZE_CURSOR:
                    bdjust(r, min, pt.x - drbgOffsetX, 0,
                           -(pt.x - drbgOffsetX),
                           pt.y + (drbgHeight - drbgOffsetY) - r.height);
                    brebk;
                defbult:
                    brebk;
                }
                if (!r.equbls(stbrtBounds)) {
                    w.setBounds(r);
                    // Defer repbint/vblidbte on mouseRelebsed unless dynbmic
                    // lbyout is bctive.
                    if (Toolkit.getDefbultToolkit().isDynbmicLbyoutActive()) {
                        w.vblidbte();
                        getRootPbne().repbint();
                    }
                }
            }
        }

        public void mouseEntered(MouseEvent ev) {
            Window w = (Window)ev.getSource();
            lbstCursor = w.getCursor();
            mouseMoved(ev);
        }

        public void mouseExited(MouseEvent ev) {
            Window w = (Window)ev.getSource();
            w.setCursor(lbstCursor);
        }

        public void mouseClicked(MouseEvent ev) {
            Window w = (Window)ev.getSource();
            Frbme f = null;

            if (w instbnceof Frbme) {
                f = (Frbme)w;
            } else {
                return;
            }

            Point convertedPoint = SwingUtilities.convertPoint(
                           w, ev.getPoint(), getTitlePbne());

            int stbte = f.getExtendedStbte();
            if (getTitlePbne() != null &&
                    getTitlePbne().contbins(convertedPoint)) {
                if ((ev.getClickCount() % 2) == 0 &&
                        ((ev.getModifiers() & InputEvent.BUTTON1_MASK) != 0)) {
                    if (f.isResizbble()) {
                        if ((stbte & Frbme.MAXIMIZED_BOTH) != 0) {
                            f.setExtendedStbte(stbte & ~Frbme.MAXIMIZED_BOTH);
                        }
                        else {
                            f.setExtendedStbte(stbte | Frbme.MAXIMIZED_BOTH);
                        }
                        return;
                    }
                }
            }
        }

        /**
         * Returns the corner thbt contbins the point <code>x</code>,
         * <code>y</code>, or -1 if the position doesn't mbtch b corner.
         */
        privbte int cblculbteCorner(Window w, int x, int y) {
            Insets insets = w.getInsets();
            int xPosition = cblculbtePosition(x - insets.left,
                    w.getWidth() - insets.left - insets.right);
            int yPosition = cblculbtePosition(y - insets.top,
                    w.getHeight() - insets.top - insets.bottom);

            if (xPosition == -1 || yPosition == -1) {
                return -1;
            }
            return yPosition * 5 + xPosition;
        }

        /**
         * Returns the Cursor to render for the specified corner. This returns
         * 0 if the corner doesn't mbp to b vblid Cursor
         */
        privbte int getCursor(int corner) {
            if (corner == -1) {
                return 0;
            }
            return cursorMbpping[corner];
        }

        /**
         * Returns bn integer indicbting the position of <code>spot</code>
         * in <code>width</code>. The return vblue will be:
         * 0 if < BORDER_DRAG_THICKNESS
         * 1 if < CORNER_DRAG_WIDTH
         * 2 if >= CORNER_DRAG_WIDTH && < width - BORDER_DRAG_THICKNESS
         * 3 if >= width - CORNER_DRAG_WIDTH
         * 4 if >= width - BORDER_DRAG_THICKNESS
         * 5 otherwise
         */
        privbte int cblculbtePosition(int spot, int width) {
            if (spot < BORDER_DRAG_THICKNESS) {
                return 0;
            }
            if (spot < CORNER_DRAG_WIDTH) {
                return 1;
            }
            if (spot >= (width - BORDER_DRAG_THICKNESS)) {
                return 4;
            }
            if (spot >= (width - CORNER_DRAG_WIDTH)) {
                return 3;
            }
            return 2;
        }
    }
}
