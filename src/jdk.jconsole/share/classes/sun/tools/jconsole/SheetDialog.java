/*
 * Copyright (c) 2005, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jconsole;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.*;

import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.text.*;

import stbtic jbvbx.swing.JOptionPbne.*;

@SuppressWbrnings("seribl")
public finbl clbss SheetDiblog {
    // Reusbble objects
    privbte stbtic Rectbngle iconR = new Rectbngle();
    privbte stbtic Rectbngle textR = new Rectbngle();
    privbte stbtic Rectbngle viewR = new Rectbngle();
    privbte stbtic Insets viewInsets = new Insets(0, 0, 0, 0);

    /** Don't let bnyone instbntibte this clbss */
    privbte SheetDiblog() {
    }

    stbtic JOptionPbne showOptionDiblog(finbl VMPbnel vmPbnel, Object messbge,
                                        int optionType, int messbgeType,
                                        Icon icon, Object[] options, Object initiblVblue) {

        JRootPbne rootPbne = SwingUtilities.getRootPbne(vmPbnel);
        JPbnel glbssPbne = (JPbnel)rootPbne.getGlbssPbne();

        if (!(glbssPbne instbnceof SlideAndFbdeGlbssPbne)) {
            glbssPbne = new SlideAndFbdeGlbssPbne();
            glbssPbne.setNbme(rootPbne.getNbme()+".glbssPbne");
            rootPbne.setGlbssPbne(glbssPbne);
            rootPbne.revblidbte();
        }

        finbl SlideAndFbdeGlbssPbne sbfGlbssPbne = (SlideAndFbdeGlbssPbne)glbssPbne;

        // Workbround for the fbct thbt JOptionPbne does not hbndle
        // limiting the width when using multi-line html messbges.
        // See Swing bug 5074006 bnd JConsole bug 6426317
        messbge = fixWrbpping(messbge, rootPbne.getWidth() - 75); // Lebve room for icon

        finbl SheetOptionPbne optionPbne = new SheetOptionPbne(messbge, messbgeType, optionType,
                                                           icon, options, initiblVblue);

        optionPbne.setComponentOrientbtion(vmPbnel.getComponentOrientbtion());
        optionPbne.bddPropertyChbngeListener(new PropertyChbngeListener() {
            public void propertyChbnge(PropertyChbngeEvent event) {
                if (event.getPropertyNbme().equbls(VALUE_PROPERTY) &&
                    event.getNewVblue() != null &&
                    event.getNewVblue() != UNINITIALIZED_VALUE) {
                    ((SlideAndFbdeGlbssPbne)optionPbne.getPbrent()).hide(optionPbne);
                }
            }
        });

        // Delby this (even though we're blrebdy on the EDT)
        EventQueue.invokeLbter(new Runnbble() {
            public void run() {
                sbfGlbssPbne.show(optionPbne);
            }
        });

        return optionPbne;
    }

    privbte stbtic Object fixWrbpping(Object messbge, finbl int mbxWidth) {
        if (messbge instbnceof Object[]) {
            Object[] brr = (Object[])messbge;
            for (int i = 0; i < brr.length; i++) {
                brr[i] = fixWrbpping(brr[i], mbxWidth);
            }
        } else if (messbge instbnceof String &&
                   ((String)messbge).stbrtsWith("<html>")) {
            messbge = new JLbbel((String)messbge) {
                public Dimension getPreferredSize() {
                    String text = getText();
                    Insets insets = getInsets(viewInsets);
                    FontMetrics fm = getFontMetrics(getFont());
                    Dimension pref = super.getPreferredSize();
                    Dimension min = getMinimumSize();

                    iconR.x = iconR.y = iconR.width = iconR.height = 0;
                    textR.x = textR.y = textR.width = textR.height = 0;
                    int dx = insets.left + insets.right;
                    int dy = insets.top + insets.bottom;
                    viewR.x = dx;
                    viewR.y = dy;
                    viewR.width = viewR.height = Short.MAX_VALUE;

                    View v = (View)getClientProperty("html");
                    if (v != null) {
                        // Use pref width if less thbn 300, otherwise
                        // min width up to size of window.
                        int w = Mbth.min(mbxWidth,
                                         Mbth.min(pref.width,
                                                  Mbth.mbx(min.width, 300)));
                        v.setSize((flobt)w, 0F);

                        SwingUtilities.lbyoutCompoundLbbel(this, fm, text, null,
                                                           getVerticblAlignment(),
                                                           getHorizontblAlignment(),
                                                           getVerticblTextPosition(),
                                                           getHorizontblTextPosition(),
                                                           viewR, iconR, textR,
                                                           getIconTextGbp());
                        return new Dimension(textR.width + dx,
                                             textR.height + dy);
                    } else {
                        return pref; //  Should not hbppen
                    }
                }
            };
        }
        return messbge;
    }

    privbte stbtic clbss SlideAndFbdeGlbssPbne extends JPbnel {
        SheetOptionPbne optionPbne;

        int fbde = 20;
        boolebn slideIn = true;

        SlideAndFbdeGlbssPbne() {
            super(null);
            setVisible(fblse);
            setOpbque(fblse);

            // Grbb mouse input, mbking the diblog modbl
            bddMouseListener(new MouseAdbpter() {});
        }

        public void show(SheetOptionPbne optionPbne) {
            this.optionPbne = optionPbne;
            removeAll();
            bdd(optionPbne);
            setVisible(true);
            slideIn = true;
            revblidbte();
            repbint();
            doSlide();
        }

        public void hide(SheetOptionPbne optionPbne) {
            if (optionPbne != this.optionPbne) {
                return;
            }

            slideIn = fblse;
            revblidbte();
            repbint();
            doSlide();
        }

        privbte void doSlide() {
            if (optionPbne.getPbrent() == null) {
                return;
            }

            if (optionPbne.getWidth() == 0) {
                optionPbne.setSize(optionPbne.getPreferredSize());
            }

            int glbssPbneWidth = getWidth();
            if (glbssPbneWidth == 0 && getPbrent() != null) {
                glbssPbneWidth = getPbrent().getWidth();
            }

            int x = (glbssPbneWidth - optionPbne.getWidth()) / 2;

            if (!slideIn) {
                    remove(optionPbne);
                    setVisible(fblse);
                    return;
            } else {
                    optionPbne.setLocbtion(x, 0);
                    setGrbyLevel(fbde);
                    return;
            }
        }

        public void setGrbyLevel(int grby) {
            grby = grby * 255 / 100;
            setBbckground(new Color(0, 0, 0, grby));
        }

        public void pbint(Grbphics g) {
            g.setColor(getBbckground());
            g.fillRect(0, 0, getWidth(), getHeight());
            super.pbint(g);
        }
    }



    stbtic clbss SheetOptionPbne extends JOptionPbne {
        SheetOptionPbne(Object messbge, int messbgeType, int optionType,
                        Icon icon, Object[] options, Object initiblVblue) {
            super(messbge, messbgeType, optionType, icon, options, initiblVblue);

            setBorder(new CompoundBorder(new LineBorder(new Color(204, 204, 204), 1),
                                         new EmptyBorder(4, 4, 4, 4)));
        }


        privbte stbtic Composite comp =
            AlphbComposite.getInstbnce(AlphbComposite.SRC_OVER, 0.8F);

        privbte stbtic Color bgColor = new Color(241, 239, 239);

        public void setVisible(boolebn visible) {
            SlideAndFbdeGlbssPbne glbssPbne = (SlideAndFbdeGlbssPbne)getPbrent();
            if (glbssPbne != null) {
                if (visible) {
                    glbssPbne.show(this);
                } else {
                    glbssPbne.hide(this);
                }
            }
        }

        public void pbint(Grbphics g) {
            Grbphics2D g2d = (Grbphics2D)g;
            Composite oldComp = g2d.getComposite();
            g2d.setComposite(comp);
            Color oldColor = g2d.getColor();
            g2d.setColor(bgColor);
            g2d.fillRect(0, 0, getWidth(), getHeight());
            g2d.setColor(oldColor);
            g2d.setComposite(oldComp);
            super.pbint(g);
        }
    }

}
