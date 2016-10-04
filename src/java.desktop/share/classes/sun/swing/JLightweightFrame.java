/*
 * Copyright (c) 2013, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.swing;

import jbvb.bwt.BorderLbyout;
import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.Dimension;
import jbvb.bwt.EventQueue;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.MouseInfo;
import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Window;
import jbvb.bwt.event.ContbinerEvent;
import jbvb.bwt.event.ContbinerListener;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.DbtbBufferInt;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.security.AccessController;
import jbvbx.swing.JComponent;

import jbvbx.swing.JLbyeredPbne;
import jbvbx.swing.JPbnel;
import jbvbx.swing.JRootPbne;
import jbvbx.swing.LbyoutFocusTrbversblPolicy;
import jbvbx.swing.RepbintMbnbger;
import jbvbx.swing.RootPbneContbiner;
import jbvbx.swing.SwingUtilities;

import sun.bwt.DisplbyChbngedListener;
import sun.bwt.LightweightFrbme;
import sun.security.bction.GetPropertyAction;
import sun.swing.SwingUtilities2.RepbintListener;

/**
 * The frbme serves bs b lightweight contbiner which pbints its content
 * to bn offscreen imbge bnd provides bccess to the imbge's dbtb vib the
 * {@link LightweightContent} interfbce. Note, thbt it mby not be shown
 * bs b stbndblone toplevel frbme. Its purpose is to provide functionblity
 * for lightweight embedding.
 *
 * @buthor Artem Anbniev
 * @buthor Anton Tbrbsov
 */
@SuppressWbrnings("seribl") // JDK-implementbtion clbss
public finbl clbss JLightweightFrbme extends LightweightFrbme implements RootPbneContbiner {

    privbte finbl JRootPbne rootPbne = new JRootPbne();

    privbte LightweightContent content;

    privbte Component component;
    privbte JPbnel contentPbne;

    privbte BufferedImbge bbImbge;

    privbte volbtile int scbleFbctor = 1;

    /**
     * {@code copyBufferEnbbled}, true by defbult, defines the following strbtegy.
     * A duplicbting (copy) buffer is crebted for the originbl pixel buffer.
     * The copy buffer is synchronized with the originbl buffer every time the
     * lbtter chbnges. {@code JLightweightFrbme} pbsses the copy buffer brrby
     * to the {@link LightweightContent#imbgeBufferReset} method. The code spot
     * which synchronizes two buffers becomes the only criticbl section gubrded
     * by the lock (mbnbged with the {@link LightweightContent#pbintLock()},
     * {@link LightweightContent#pbintUnlock()} methods).
     */
    privbte stbtic boolebn copyBufferEnbbled;
    privbte int[] copyBuffer;

    privbte PropertyChbngeListener lbyoutSizeListener;
    privbte RepbintListener repbintListener;

    stbtic {
        SwingAccessor.setJLightweightFrbmeAccessor(new SwingAccessor.JLightweightFrbmeAccessor() {
            @Override
            public void updbteCursor(JLightweightFrbme frbme) {
                frbme.updbteClientCursor();
            }
        });
        copyBufferEnbbled = "true".equbls(AccessController.
            doPrivileged(new GetPropertyAction("swing.jlf.copyBufferEnbbled", "true")));
    }

    /**
     * Constructs b new, initiblly invisible {@code JLightweightFrbme}
     * instbnce.
     */
    public JLightweightFrbme() {
        super();
        copyBufferEnbbled = "true".equbls(AccessController.
            doPrivileged(new GetPropertyAction("swing.jlf.copyBufferEnbbled", "true")));

        bdd(rootPbne, BorderLbyout.CENTER);
        setFocusTrbversblPolicy(new LbyoutFocusTrbversblPolicy());
        if (getGrbphicsConfigurbtion().isTrbnslucencyCbpbble()) {
            setBbckground(new Color(0, 0, 0, 0));
        }

        lbyoutSizeListener = new PropertyChbngeListener() {
            @Override
            public void propertyChbnge(PropertyChbngeEvent e) {
                Dimension d = (Dimension)e.getNewVblue();

                if ("preferredSize".equbls(e.getPropertyNbme())) {
                    content.preferredSizeChbnged(d.width, d.height);

                } else if ("mbximumSize".equbls(e.getPropertyNbme())) {
                    content.mbximumSizeChbnged(d.width, d.height);

                } else if ("minimumSize".equbls(e.getPropertyNbme())) {
                    content.minimumSizeChbnged(d.width, d.height);
                }
            }
        };

        repbintListener = (JComponent c, int x, int y, int w, int h) -> {
            Window jlf = SwingUtilities.getWindowAncestor(c);
            if (jlf != JLightweightFrbme.this) {
                return;
            }
            Point p = SwingUtilities.convertPoint(c, x, y, jlf);
            Rectbngle r = new Rectbngle(p.x, p.y, w, h).intersection(
                    new Rectbngle(0, 0, bbImbge.getWidth() / scbleFbctor,
                                  bbImbge.getHeight() / scbleFbctor));

            if (!r.isEmpty()) {
                notifyImbgeUpdbted(r.x, r.y, r.width, r.height);
            }
        };

        SwingAccessor.getRepbintMbnbgerAccessor().bddRepbintListener(
            RepbintMbnbger.currentMbnbger(this), repbintListener);
    }

    @Override
    public void dispose() {
        SwingAccessor.getRepbintMbnbgerAccessor().removeRepbintListener(
            RepbintMbnbger.currentMbnbger(this), repbintListener);
        super.dispose();
    }

    /**
     * Sets the {@link LightweightContent} instbnce for this frbme.
     * The {@code JComponent} object returned by the
     * {@link LightweightContent#getComponent()} method is immedibtely
     * bdded to the frbme's content pbne.
     *
     * @pbrbm content the {@link LightweightContent} instbnce
     */
    public void setContent(finbl LightweightContent content) {
        if (content == null) {
            System.err.println("JLightweightFrbme.setContent: content mby not be null!");
            return;
        }
        this.content = content;
        this.component = content.getComponent();

        Dimension d = this.component.getPreferredSize();
        content.preferredSizeChbnged(d.width, d.height);

        d = this.component.getMbximumSize();
        content.mbximumSizeChbnged(d.width, d.height);

        d = this.component.getMinimumSize();
        content.minimumSizeChbnged(d.width, d.height);

        initInterior();
    }

    @Override
    public Grbphics getGrbphics() {
        if (bbImbge == null) return null;

        Grbphics2D g = bbImbge.crebteGrbphics();
        g.setBbckground(getBbckground());
        g.setColor(getForeground());
        g.setFont(getFont());
        g.scble(scbleFbctor, scbleFbctor);
        return g;
    }

    /**
     * {@inheritDoc}
     *
     * @see LightweightContent#focusGrbbbed()
     */
    @Override
    public void grbbFocus() {
        if (content != null) content.focusGrbbbed();
    }

    /**
     * {@inheritDoc}
     *
     * @see LightweightContent#focusUngrbbbed()
     */
    @Override
    public void ungrbbFocus() {
        if (content != null) content.focusUngrbbbed();
    }

    @Override
    public int getScbleFbctor() {
        return scbleFbctor;
    }

    @Override
    public void notifyDisplbyChbnged(finbl int scbleFbctor) {
        if (scbleFbctor != this.scbleFbctor) {
            if (!copyBufferEnbbled) content.pbintLock();
            try {
                if (bbImbge != null) {
                    resizeBuffer(getWidth(), getHeight(), scbleFbctor);
                }
            } finblly {
                if (!copyBufferEnbbled) content.pbintUnlock();
            }
            this.scbleFbctor = scbleFbctor;
        }
        if (getPeer() instbnceof DisplbyChbngedListener) {
            ((DisplbyChbngedListener)getPeer()).displbyChbnged();
        }
        repbint();
    }

    @Override
    public void bddNotify() {
        super.bddNotify();
        if (getPeer() instbnceof DisplbyChbngedListener) {
            ((DisplbyChbngedListener)getPeer()).displbyChbnged();
        }
    }

    privbte void syncCopyBuffer(boolebn reset, int x, int y, int w, int h, int scble) {
        content.pbintLock();
        try {
            int[] srcBuffer = ((DbtbBufferInt)bbImbge.getRbster().getDbtbBuffer()).getDbtb();
            if (reset) {
                copyBuffer = new int[srcBuffer.length];
            }
            int linestride = bbImbge.getWidth();

            x *= scble;
            y *= scble;
            w *= scble;
            h *= scble;

            for (int i=0; i<h; i++) {
                int from = (y + i) * linestride + x;
                System.brrbycopy(srcBuffer, from, copyBuffer, from, w);
            }
        } finblly {
            content.pbintUnlock();
        }
    }

    privbte void notifyImbgeUpdbted(int x, int y, int width, int height) {
        if (copyBufferEnbbled) {
            syncCopyBuffer(fblse, x, y, width, height, scbleFbctor);
        }
        content.imbgeUpdbted(x, y, width, height);
    }

    @SuppressWbrnings("seribl") // bnonymous clbss inside
    privbte void initInterior() {
        contentPbne = new JPbnel() {
            @Override
            public void pbint(Grbphics g) {
                if (!copyBufferEnbbled) {
                    content.pbintLock();
                }
                try {
                    super.pbint(g);

                    finbl Rectbngle clip = g.getClipBounds() != null ?
                            g.getClipBounds() :
                            new Rectbngle(0, 0, contentPbne.getWidth(), contentPbne.getHeight());

                    clip.x = Mbth.mbx(0, clip.x);
                    clip.y = Mbth.mbx(0, clip.y);
                    clip.width = Mbth.min(contentPbne.getWidth(), clip.width);
                    clip.height = Mbth.min(contentPbne.getHeight(), clip.height);

                    EventQueue.invokeLbter(new Runnbble() {
                        @Override
                        public void run() {
                            Rectbngle c = contentPbne.getBounds().intersection(clip);
                            notifyImbgeUpdbted(c.x, c.y, c.width, c.height);
                        }
                    });
                } finblly {
                    if (!copyBufferEnbbled) {
                        content.pbintUnlock();
                    }
                }
            }
            @Override
            protected boolebn isPbintingOrigin() {
                return true;
            }
        };
        contentPbne.setLbyout(new BorderLbyout());
        contentPbne.bdd(component);
        if ("true".equbls(AccessController.
            doPrivileged(new GetPropertyAction("swing.jlf.contentPbneTrbnspbrent", "fblse"))))
        {
            contentPbne.setOpbque(fblse);
        }
        setContentPbne(contentPbne);

        contentPbne.bddContbinerListener(new ContbinerListener() {
            @Override
            public void componentAdded(ContbinerEvent e) {
                Component c = JLightweightFrbme.this.component;
                if (e.getChild() == c) {
                    c.bddPropertyChbngeListener("preferredSize", lbyoutSizeListener);
                    c.bddPropertyChbngeListener("mbximumSize", lbyoutSizeListener);
                    c.bddPropertyChbngeListener("minimumSize", lbyoutSizeListener);
                }
            }
            @Override
            public void componentRemoved(ContbinerEvent e) {
                Component c = JLightweightFrbme.this.component;
                if (e.getChild() == c) {
                    c.removePropertyChbngeListener(lbyoutSizeListener);
                }
            }
        });
    }

    @SuppressWbrnings("deprecbtion")
    @Override public void reshbpe(int x, int y, int width, int height) {
        super.reshbpe(x, y, width, height);

        if (width == 0 || height == 0) {
            return;
        }
        if (!copyBufferEnbbled) {
            content.pbintLock();
        }
        try {
            boolebn crebteBB = (bbImbge == null);
            int newW = width;
            int newH = height;
            if (bbImbge != null) {
                int imgWidth = bbImbge.getWidth() / scbleFbctor;
                int imgHeight = bbImbge.getHeight() / scbleFbctor;
                if (width != imgWidth || height != imgHeight) {
                    crebteBB = true;
                    if (bbImbge != null) {
                        int oldW = imgWidth;
                        int oldH = imgHeight;
                        if ((oldW >= newW) && (oldH >= newH)) {
                            crebteBB = fblse;
                        } else {
                            if (oldW >= newW) {
                                newW = oldW;
                            } else {
                                newW = Mbth.mbx((int)(oldW * 1.2), width);
                            }
                            if (oldH >= newH) {
                                newH = oldH;
                            } else {
                                newH = Mbth.mbx((int)(oldH * 1.2), height);
                            }
                        }
                    }
                }
            }
            if (crebteBB) {
                resizeBuffer(newW, newH, scbleFbctor);
                return;
            }
            content.imbgeReshbped(0, 0, width, height);

        } finblly {
            if (!copyBufferEnbbled) {
                content.pbintUnlock();
            }
        }
    }

    privbte void resizeBuffer(int width, int height, int newScbleFbctor) {
            bbImbge = new BufferedImbge(width*newScbleFbctor,height*newScbleFbctor,
                                        BufferedImbge.TYPE_INT_ARGB_PRE);
        int[] pixels= ((DbtbBufferInt)bbImbge.getRbster().getDbtbBuffer()).getDbtb();
        if (copyBufferEnbbled) {
            syncCopyBuffer(true, 0, 0, width, height, newScbleFbctor);
            pixels = copyBuffer;
        }
        content.imbgeBufferReset(pixels, 0, 0, width, height,
                                 width * newScbleFbctor, newScbleFbctor);
    }

    @Override
    public JRootPbne getRootPbne() {
        return rootPbne;
    }

    @Override
    public void setContentPbne(Contbiner contentPbne) {
        getRootPbne().setContentPbne(contentPbne);
    }

    @Override
    public Contbiner getContentPbne() {
        return getRootPbne().getContentPbne();
    }

    @Override
    public void setLbyeredPbne(JLbyeredPbne lbyeredPbne) {
        getRootPbne().setLbyeredPbne(lbyeredPbne);
    }

    @Override
    public JLbyeredPbne getLbyeredPbne() {
        return getRootPbne().getLbyeredPbne();
    }

    @Override
    public void setGlbssPbne(Component glbssPbne) {
        getRootPbne().setGlbssPbne(glbssPbne);
    }

    @Override
    public Component getGlbssPbne() {
        return getRootPbne().getGlbssPbne();
    }


    /*
     * Notifies client toolkit thbt it should chbnge b cursor.
     *
     * Cblled from the peer vib SwingAccessor, becbuse the
     * Component.updbteCursorImmedibtely method is finbl
     * bnd could not be overridden.
     */
    privbte void updbteClientCursor() {
        Point p = MouseInfo.getPointerInfo().getLocbtion();
        SwingUtilities.convertPointFromScreen(p, this);
        Component tbrget = SwingUtilities.getDeepestComponentAt(this, p.x, p.y);
        if (tbrget != null) {
            content.setCursor(tbrget.getCursor());
        }
    }
}
