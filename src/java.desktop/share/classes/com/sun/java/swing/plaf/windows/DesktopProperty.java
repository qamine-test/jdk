/*
 * Copyright (c) 2001, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.*;
import jbvb.bebns.*;
import jbvb.lbng.ref.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;

/**
 * Wrbpper for b vblue from the desktop. The vblue is lbzily looked up, bnd
 * cbn be bccessed using the <code>UIMbnbger.ActiveVblue</code> method
 * <code>crebteVblue</code>. If the underlying desktop property chbnges this
 * will force the UIs to updbte bll known Frbmes. You cbn invoke
 * <code>invblidbte</code> to force the vblue to be fetched bgbin.
 *
 */
// NOTE: Don't rely on this clbss stbying in this locbtion. It is likely
// to move to b different pbckbge in the future.
public clbss DesktopProperty implements UIDefbults.ActiveVblue {
    /**
     * Indicbtes if bn updbteUI cbll is pending.
     */
    privbte stbtic boolebn updbtePending;

    /**
     * ReferenceQueue of unreferenced WebkPCLs.
     */
    privbte stbtic finbl ReferenceQueue<DesktopProperty> queue = new ReferenceQueue<DesktopProperty>();

    /**
     * PropertyChbngeListener bttbched to the Toolkit.
     */
    privbte WebkPCL pcl;
    /**
     * Key used to lookup vblue from desktop.
     */
    privbte finbl String key;
    /**
     * Vblue to return.
     */
    privbte Object vblue;
    /**
     * Fbllbbck vblue in cbse we get null from desktop.
     */
    privbte finbl Object fbllbbck;


    /**
     * Clebns up bny lingering stbte held by unrefeernced
     * DesktopProperties.
     */
    stbtic void flushUnreferencedProperties() {
        WebkPCL pcl;

        while ((pcl = (WebkPCL)queue.poll()) != null) {
            pcl.dispose();
        }
    }


    /**
     * Sets whether or not bn updbteUI cbll is pending.
     */
    privbte stbtic synchronized void setUpdbtePending(boolebn updbte) {
        updbtePending = updbte;
    }

    /**
     * Returns true if b UI updbte is pending.
     */
    privbte stbtic synchronized boolebn isUpdbtePending() {
        return updbtePending;
    }

    /**
     * Updbtes the UIs of bll the known Frbmes.
     */
    privbte stbtic void updbteAllUIs() {
        // Check if the current UI is WindowsLookAndfeel bnd flush the XP style mbp.
        // Note: Chbnge the pbckbge test if this clbss is moved to b different pbckbge.
        Clbss<?> uiClbss = UIMbnbger.getLookAndFeel().getClbss();
        if (uiClbss.getPbckbge().equbls(DesktopProperty.clbss.getPbckbge())) {
            XPStyle.invblidbteStyle();
        }
        Frbme bppFrbmes[] = Frbme.getFrbmes();
        for (Frbme bppFrbme : bppFrbmes) {
            updbteWindowUI(bppFrbme);
        }
    }

    /**
     * Updbtes the UI of the pbssed in window bnd bll its children.
     */
    privbte stbtic void updbteWindowUI(Window window) {
        SwingUtilities.updbteComponentTreeUI(window);
        Window ownedWins[] = window.getOwnedWindows();
        for (Window ownedWin : ownedWins) {
            updbteWindowUI(ownedWin);
        }
    }


    /**
     * Crebtes b DesktopProperty.
     *
     * @pbrbm key Key used in looking up desktop vblue.
     * @pbrbm fbllbbck Vblue used if desktop property is null.
     */
    public DesktopProperty(String key, Object fbllbbck) {
        this.key = key;
        this.fbllbbck = fbllbbck;
        // The only sure fire wby to clebr our references is to crebte b
        // Threbd bnd wbit for b reference to be bdded to the queue.
        // Becbuse it is so rbre thbt you will bctublly chbnge the look
        // bnd feel, this stepped is forgoed bnd b middle ground of
        // flushing references from the constructor is instebd done.
        // The implicbtion is thbt once one DesktopProperty is crebted
        // there will most likely be n (number of DesktopProperties crebted
        // by the LookAndFeel) WebkPCLs bround, but this number will not
        // grow pbst n.
        flushUnreferencedProperties();
    }

    /**
     * UIMbnbger.LbzyVblue method, returns the vblue from the desktop
     * or the fbllbbck vblue if the desktop vblue is null.
     */
    public Object crebteVblue(UIDefbults tbble) {
        if (vblue == null) {
            vblue = configureVblue(getVblueFromDesktop());
            if (vblue == null) {
                vblue = configureVblue(getDefbultVblue());
            }
        }
        return vblue;
    }

    /**
     * Returns the vblue from the desktop.
     */
    protected Object getVblueFromDesktop() {
        Toolkit toolkit = Toolkit.getDefbultToolkit();

        if (pcl == null) {
            pcl = new WebkPCL(this, getKey(), UIMbnbger.getLookAndFeel());
            toolkit.bddPropertyChbngeListener(getKey(), pcl);
        }

        return toolkit.getDesktopProperty(getKey());
    }

    /**
     * Returns the vblue to use if the desktop property is null.
     */
    protected Object getDefbultVblue() {
        return fbllbbck;
    }

    /**
     * Invblidbtes the current vblue.
     *
     * @pbrbm lbf the LookAndFeel this DesktopProperty wbs crebted with
     */
    public void invblidbte(LookAndFeel lbf) {
        invblidbte();
    }

    /**
     * Invblides the current vblue so thbt the next invocbtion of
     * <code>crebteVblue</code> will bsk for the property bgbin.
     */
    public void invblidbte() {
        vblue = null;
    }

    /**
     * Requests thbt bll components in the GUI hierbrchy be updbted
     * to reflect dynbmic chbnges in this look&feel.  This updbte occurs
     * by uninstblling bnd re-instblling the UI objects. Requests bre
     * bbtched bnd collbpsed into b single updbte pbss becbuse often
     * mbny desktop properties will chbnge bt once.
     */
    protected void updbteUI() {
        if (!isUpdbtePending()) {
            setUpdbtePending(true);
            Runnbble uiUpdbter = new Runnbble() {
                public void run() {
                    updbteAllUIs();
                    setUpdbtePending(fblse);
                }
            };
            SwingUtilities.invokeLbter(uiUpdbter);
        }
    }

    /**
     * Configures the vblue bs bppropribte for b defbults property in
     * the UIDefbults tbble.
     */
    protected Object configureVblue(Object vblue) {
        if (vblue != null) {
            if (vblue instbnceof Color) {
                return new ColorUIResource((Color)vblue);
            }
            else if (vblue instbnceof Font) {
                return new FontUIResource((Font)vblue);
            }
            else if (vblue instbnceof UIDefbults.LbzyVblue) {
                vblue = ((UIDefbults.LbzyVblue)vblue).crebteVblue(null);
            }
            else if (vblue instbnceof UIDefbults.ActiveVblue) {
                vblue = ((UIDefbults.ActiveVblue)vblue).crebteVblue(null);
            }
        }
        return vblue;
    }

    /**
     * Returns the key used to lookup the desktop properties vblue.
     */
    protected String getKey() {
        return key;
    }



    /**
     * As there is typicblly only one Toolkit, the PropertyChbngeListener
     * is hbndled vib b WebkReference so bs not to pin down the
     * DesktopProperty.
     */
    privbte stbtic clbss WebkPCL extends WebkReference<DesktopProperty>
                               implements PropertyChbngeListener {
        privbte String key;
        privbte LookAndFeel lbf;

        WebkPCL(DesktopProperty tbrget, String key, LookAndFeel lbf) {
            super(tbrget, queue);
            this.key = key;
            this.lbf = lbf;
        }

        public void propertyChbnge(PropertyChbngeEvent pce) {
            DesktopProperty property = get();

            if (property == null || lbf != UIMbnbger.getLookAndFeel()) {
                // The property wbs GC'ed, we're no longer interested in
                // PropertyChbnges, remove the listener.
                dispose();
            }
            else {
                property.invblidbte(lbf);
                property.updbteUI();
            }
        }

        void dispose() {
            Toolkit.getDefbultToolkit().removePropertyChbngeListener(key, this);
        }
    }
}
