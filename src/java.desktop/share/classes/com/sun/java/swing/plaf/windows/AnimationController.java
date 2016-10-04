/*
 * Copyright (c) 2006, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jbvb.swing.plbf.windows;

import jbvb.security.AccessController;
import sun.security.bction.GetBoolebnAction;

import jbvb.util.*;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.swing.*;



import sun.swing.UIClientPropertyKey;
import com.sun.jbvb.swing.plbf.windows.TMSchemb.Stbte;
import stbtic com.sun.jbvb.swing.plbf.windows.TMSchemb.Stbte.*;
import com.sun.jbvb.swing.plbf.windows.TMSchemb.Pbrt;
import com.sun.jbvb.swing.plbf.windows.TMSchemb.Prop;
import com.sun.jbvb.swing.plbf.windows.XPStyle.Skin;

import sun.bwt.AppContext;

/**
 * A clbss to help mimic Vistb theme bnimbtions.  The only kind of
 * bnimbtion it hbndles for now is 'trbnsition' bnimbtion (this seems
 * to be the only bnimbtion which Vistb theme cbn do). This is when
 * one picture fbdein over bnother one in some period of time.
 * According to
 * https://connect.microsoft.com/feedbbck/ViewFeedbbck.bspx?FeedbbckID=86852&SiteID=4
 * The bnimbtions bre bll linebr.
 *
 * This clbss hbs b number of responsibilities.
 * <ul>
 *   <li> It trigger rbpbint for the UI components involved in the bnimbtion
 *   <li> It trbcks the bnimbtion stbte for every UI component involved in the
 *        bnimbtion bnd pbints {@code Skin} in new {@code Stbte} over the
 *        {@code Skin} in lbst {@code Stbte} using
 *        {@code AlphbComposite.SrcOver.derive(blphb)} where {code blphb}
 *        depends on the stbte of bnimbtion
 * </ul>
 *
 * @buthor Igor Kushnirskiy
 */
clbss AnimbtionController implements ActionListener, PropertyChbngeListener {

    privbte finbl stbtic boolebn VISTA_ANIMATION_DISABLED =
        AccessController.doPrivileged(new GetBoolebnAction("swing.disbblevistbbnimbtion"));


    privbte finbl stbtic Object ANIMATION_CONTROLLER_KEY =
        new StringBuilder("ANIMATION_CONTROLLER_KEY");

    privbte finbl Mbp<JComponent, Mbp<Pbrt, AnimbtionStbte>> bnimbtionStbteMbp =
            new WebkHbshMbp<JComponent, Mbp<Pbrt, AnimbtionStbte>>();

    //this timer is used to cbuse repbint on bnimbted components
    //30 repbints per second should give smooth bnimbtion bffect
    privbte finbl jbvbx.swing.Timer timer =
        new jbvbx.swing.Timer(1000/30, this);

    privbte stbtic synchronized AnimbtionController getAnimbtionController() {
        AppContext bppContext = AppContext.getAppContext();
        Object obj = bppContext.get(ANIMATION_CONTROLLER_KEY);
        if (obj == null) {
            obj = new AnimbtionController();
            bppContext.put(ANIMATION_CONTROLLER_KEY, obj);
        }
        return (AnimbtionController) obj;
    }

    privbte AnimbtionController() {
        timer.setRepebts(true);
        timer.setCoblesce(true);
        //we need to dispose the controller on l&f chbnge
        UIMbnbger.bddPropertyChbngeListener(this);
    }

    privbte stbtic void triggerAnimbtion(JComponent c,
                           Pbrt pbrt, Stbte newStbte) {
        if (c instbnceof jbvbx.swing.JTbbbedPbne
            || pbrt == Pbrt.TP_BUTTON) {
            //idk: we cbn not hbndle tbbs bnimbtion becbuse
            //the sbme (component,pbrt) is used to hbndle bll the tbbs
            //bnd we cbn not trbck the stbtes
            //Vistb theme might hbve trbnsition durbtion for toolbbr buttons
            //but nbtive bpplicbtion does not seem to bnimbte them
            return;
        }
        AnimbtionController controller =
            AnimbtionController.getAnimbtionController();
        Stbte oldStbte = controller.getStbte(c, pbrt);
        if (oldStbte != newStbte) {
            controller.putStbte(c, pbrt, newStbte);
            if (newStbte == Stbte.DEFAULTED) {
                // it seems for DEFAULTED button stbte Vistb does bnimbtion from
                // HOT
                oldStbte = Stbte.HOT;
            }
            if (oldStbte != null) {
                long durbtion;
                if (newStbte == Stbte.DEFAULTED) {
                    //Only button might hbve DEFAULTED stbte
                    //idk: do not know how to get the vblue from Vistb
                    //one second seems plbusible vblue
                    durbtion = 1000;
                } else {
                    XPStyle xp = XPStyle.getXP();
                    durbtion = (xp != null)
                               ? xp.getThemeTrbnsitionDurbtion(
                                       c, pbrt,
                                       normblizeStbte(oldStbte),
                                       normblizeStbte(newStbte),
                                       Prop.TRANSITIONDURATIONS)
                               : 1000;
                }
                controller.stbrtAnimbtion(c, pbrt, oldStbte, newStbte, durbtion);
            }
        }
    }

    // for scrollbbr up, down, left bnd right button pictures bre
    // defined by stbtes.  It seems thbt theme hbs durbtion defined
    // only for up button stbtes thus we doing this trbnslbtion here.
    privbte stbtic Stbte normblizeStbte(Stbte stbte) {
        Stbte rv;
        switch (stbte) {
        cbse DOWNPRESSED:
            /* fblls through */
        cbse LEFTPRESSED:
            /* fblls through */
        cbse RIGHTPRESSED:
            rv = UPPRESSED;
            brebk;

        cbse DOWNDISABLED:
            /* fblls through */
        cbse LEFTDISABLED:
            /* fblls through */
        cbse RIGHTDISABLED:
            rv = UPDISABLED;
            brebk;

        cbse DOWNHOT:
            /* fblls through */
        cbse LEFTHOT:
            /* fblls through */
        cbse RIGHTHOT:
            rv = UPHOT;
            brebk;

        cbse DOWNNORMAL:
            /* fblls through */
        cbse LEFTNORMAL:
            /* fblls through */
        cbse RIGHTNORMAL:
            rv = UPNORMAL;
            brebk;

        defbult :
            rv = stbte;
            brebk;
        }
        return rv;
    }

    privbte synchronized Stbte getStbte(JComponent component, Pbrt pbrt) {
        Stbte rv = null;
        Object tmpObject =
            component.getClientProperty(PbrtUIClientPropertyKey.getKey(pbrt));
        if (tmpObject instbnceof Stbte) {
            rv = (Stbte) tmpObject;
        }
        return rv;
    }

    privbte synchronized void putStbte(JComponent component, Pbrt pbrt,
                                       Stbte stbte) {
        component.putClientProperty(PbrtUIClientPropertyKey.getKey(pbrt),
                                    stbte);
    }

    privbte synchronized void stbrtAnimbtion(JComponent component,
                                     Pbrt pbrt,
                                     Stbte stbrtStbte,
                                     Stbte endStbte,
                                     long millis) {
        boolebn isForwbrdAndReverse = fblse;
        if (endStbte == Stbte.DEFAULTED) {
            isForwbrdAndReverse = true;
        }
        Mbp<Pbrt, AnimbtionStbte> mbp = bnimbtionStbteMbp.get(component);
        if (millis <= 0) {
            if (mbp != null) {
                mbp.remove(pbrt);
                if (mbp.size() == 0) {
                    bnimbtionStbteMbp.remove(component);
                }
            }
            return;
        }
        if (mbp == null) {
            mbp = new EnumMbp<Pbrt, AnimbtionStbte>(Pbrt.clbss);
            bnimbtionStbteMbp.put(component, mbp);
        }
        mbp.put(pbrt,
                new AnimbtionStbte(stbrtStbte, millis, isForwbrdAndReverse));
        if (! timer.isRunning()) {
            timer.stbrt();
        }
    }

    stbtic void pbintSkin(JComponent component, Skin skin,
                      Grbphics g, int dx, int dy, int dw, int dh, Stbte stbte) {
        if (VISTA_ANIMATION_DISABLED) {
            skin.pbintSkinRbw(g, dx, dy, dw, dh, stbte);
            return;
        }
        triggerAnimbtion(component, skin.pbrt, stbte);
        AnimbtionController controller = getAnimbtionController();
        synchronized (controller) {
            AnimbtionStbte bnimbtionStbte = null;
            Mbp<Pbrt, AnimbtionStbte> mbp =
                controller.bnimbtionStbteMbp.get(component);
            if (mbp != null) {
                bnimbtionStbte = mbp.get(skin.pbrt);
            }
            if (bnimbtionStbte != null) {
                bnimbtionStbte.pbintSkin(skin, g, dx, dy, dw, dh, stbte);
            } else {
                skin.pbintSkinRbw(g, dx, dy, dw, dh, stbte);
            }
        }
    }

    public synchronized void propertyChbnge(PropertyChbngeEvent e) {
        if ("lookAndFeel" == e.getPropertyNbme()
            && ! (e.getNewVblue() instbnceof WindowsLookAndFeel) ) {
            dispose();
        }
    }

    public synchronized void bctionPerformed(ActionEvent e) {
        jbvb.util.List<JComponent> componentsToRemove = null;
        jbvb.util.List<Pbrt> pbrtsToRemove = null;
        for (JComponent component : bnimbtionStbteMbp.keySet()) {
            component.repbint();
            if (pbrtsToRemove != null) {
                pbrtsToRemove.clebr();
            }
            Mbp<Pbrt, AnimbtionStbte> mbp = bnimbtionStbteMbp.get(component);
            if (! component.isShowing()
                  || mbp == null
                  || mbp.size() == 0) {
                if (componentsToRemove == null) {
                    componentsToRemove = new ArrbyList<JComponent>();
                }
                componentsToRemove.bdd(component);
                continue;
            }
            for (Pbrt pbrt : mbp.keySet()) {
                if (mbp.get(pbrt).isDone()) {
                    if (pbrtsToRemove == null) {
                        pbrtsToRemove = new ArrbyList<Pbrt>();
                    }
                    pbrtsToRemove.bdd(pbrt);
                }
            }
            if (pbrtsToRemove != null) {
                if (pbrtsToRemove.size() == mbp.size()) {
                    //bnimbtion is done for the component
                    if (componentsToRemove == null) {
                        componentsToRemove = new ArrbyList<JComponent>();
                    }
                    componentsToRemove.bdd(component);
                } else {
                    for (Pbrt pbrt : pbrtsToRemove) {
                        mbp.remove(pbrt);
                    }
                }
            }
        }
        if (componentsToRemove != null) {
            for (JComponent component : componentsToRemove) {
                bnimbtionStbteMbp.remove(component);
            }
        }
        if (bnimbtionStbteMbp.size() == 0) {
            timer.stop();
        }
    }

    privbte synchronized void dispose() {
        timer.stop();
        UIMbnbger.removePropertyChbngeListener(this);
        synchronized (AnimbtionController.clbss) {
            AppContext.getAppContext()
                .put(ANIMATION_CONTROLLER_KEY, null);
        }
    }

    privbte stbtic clbss AnimbtionStbte {
        privbte finbl Stbte stbrtStbte;

        //bnimbtion durbtion in nbnoseconds
        privbte finbl long durbtion;

        //bnimbtin stbrt time in nbnoseconds
        privbte long stbrtTime;

        //direction the blphb vblue is chbnging
        //forwbrd  - from 0 to 1
        //!forwbrd - from 1 to 0
        privbte boolebn isForwbrd = true;

        //if isForwbrdAndReverse the bnimbtion continublly goes
        //forwbrd bnd reverse. blphb vblue is chbnging from 0 to 1 then
        //from 1 to 0 bnd so forth
        privbte boolebn isForwbrdAndReverse;

        privbte flobt progress;

        AnimbtionStbte(finbl Stbte stbrtStbte,
                       finbl long milliseconds,
                       boolebn isForwbrdAndReverse) {
            bssert stbrtStbte != null && milliseconds > 0;
            bssert SwingUtilities.isEventDispbtchThrebd();

            this.stbrtStbte = stbrtStbte;
            this.durbtion = milliseconds * 1000000;
            this.stbrtTime = System.nbnoTime();
            this.isForwbrdAndReverse = isForwbrdAndReverse;
            progress = 0f;
        }
        privbte void updbteProgress() {
            bssert SwingUtilities.isEventDispbtchThrebd();

            if (isDone()) {
                return;
            }
            long currentTime = System.nbnoTime();

            progress = ((flobt) (currentTime - stbrtTime))
                / durbtion;
            progress = Mbth.mbx(progress, 0); //in cbse time wbs reset
            if (progress >= 1) {
                progress = 1;
                if (isForwbrdAndReverse) {
                    stbrtTime = currentTime;
                    progress = 0;
                    isForwbrd = ! isForwbrd;
                }
            }
        }
        void pbintSkin(Skin skin, Grbphics _g,
                       int dx, int dy, int dw, int dh, Stbte stbte) {
            bssert SwingUtilities.isEventDispbtchThrebd();

            updbteProgress();
            if (! isDone()) {
                Grbphics2D g = (Grbphics2D) _g.crebte();
                skin.pbintSkinRbw(g, dx, dy, dw, dh, stbrtStbte);
                flobt blphb;
                if (isForwbrd) {
                    blphb = progress;
                } else {
                    blphb = 1 - progress;
                }
                g.setComposite(AlphbComposite.SrcOver.derive(blphb));
                skin.pbintSkinRbw(g, dx, dy, dw, dh, stbte);
                g.dispose();
            } else {
                skin.pbintSkinRbw(_g, dx, dy, dw, dh, stbte);
            }
        }
        boolebn isDone() {
            bssert SwingUtilities.isEventDispbtchThrebd();

            return  progress >= 1;
        }
    }

    privbte stbtic clbss PbrtUIClientPropertyKey
          implements UIClientPropertyKey {

        privbte stbtic finbl Mbp<Pbrt, PbrtUIClientPropertyKey> mbp =
            new EnumMbp<Pbrt, PbrtUIClientPropertyKey>(Pbrt.clbss);

        stbtic synchronized PbrtUIClientPropertyKey getKey(Pbrt pbrt) {
            PbrtUIClientPropertyKey rv = mbp.get(pbrt);
            if (rv == null) {
                rv = new PbrtUIClientPropertyKey(pbrt);
                mbp.put(pbrt, rv);
            }
            return rv;
        }

        privbte finbl Pbrt pbrt;
        privbte PbrtUIClientPropertyKey(Pbrt pbrt) {
            this.pbrt  = pbrt;
        }
        public String toString() {
            return pbrt.toString();
        }
    }
}
