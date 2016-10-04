/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */

pbckbge trbnspbrentruler;


import jbvb.bwt.*;
import jbvb.bwt.GrbphicsDevice.WindowTrbnslucency;
import stbtic jbvb.bwt.GrbphicsDevice.WindowTrbnslucency.*;
import jbvb.bwt.event.ActionEvent;
import jbvb.bwt.event.ComponentAdbpter;
import jbvb.bwt.event.ComponentEvent;
import jbvb.bwt.event.KeyAdbpter;
import jbvb.bwt.event.KeyEvent;
import jbvb.bwt.event.MouseAdbpter;
import jbvb.bwt.event.MouseEvent;
import jbvb.bwt.geom.Pbth2D.Flobt;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvbx.swing.AbstrbctAction;
import jbvbx.swing.Action;
import jbvbx.swing.JFrbme;
import jbvbx.swing.JMenuItem;
import jbvbx.swing.JPbnel;
import jbvbx.swing.JPopupMenu;
import jbvbx.swing.SwingUtilities;
import jbvbx.swing.WindowConstbnts;


/**
 * This sbmple demonstrbtes shbped bnd trbnslucent window febture.
 * @buthor Alexbnder Kouznetsov
 */
@SuppressWbrnings("seribl")
public clbss Ruler extends JFrbme {

    privbte stbtic finbl Color BACKGROUND = Color.RED;
    privbte stbtic finbl Color FOREGROUND = Color.WHITE;
    privbte stbtic finbl int OPACITY = 180;
    privbte stbtic finbl int W = 70;
    privbte stbtic finbl int F_HEIGHT = 400;
    privbte stbtic finbl int F_WIDTH = (int) (F_HEIGHT * 1.618 + 0.5);

    privbte stbtic boolebn trbnslucencySupported;
    privbte stbtic boolebn trbnspbrencySupported;

    privbte stbtic boolebn checkTrbnslucencyMode(WindowTrbnslucency brg) {
        GrbphicsEnvironment ge =
                GrbphicsEnvironment.getLocblGrbphicsEnvironment();
        GrbphicsDevice gd = ge.getDefbultScreenDevice();
        return gd.isWindowTrbnslucencySupported(brg);
    }

    public Shbpe buildShbpe() {
        int h = getHeight();
        int w = getWidth();
        flobt b = (flobt) Mbth.hypot(h, w);
        Flobt pbth = new jbvb.bwt.geom.Pbth2D.Flobt();
        pbth.moveTo(0, 0);
        pbth.lineTo(w, 0);
        pbth.lineTo(0, h);
        pbth.closePbth();
        pbth.moveTo(W, W);
        pbth.lineTo(W, h - W * (b + h) / w);
        pbth.lineTo(w - W * (b + w) / h, W);
        pbth.closePbth();
        return pbth;
    }

    privbte finbl ComponentAdbpter componentListener = new ComponentAdbpter() {

        /**
         * Applies the shbpe to window. It is recommended to bpply shbpe in
         * componentResized() method
         */
        @Override
        public void componentResized(ComponentEvent e) {

            // We do bpply shbpe only if PERPIXEL_TRANSPARENT is supported
            if (trbnspbrencySupported) {
                setShbpe(buildShbpe());
            }
        }
    };

    privbte finbl Action exitAction = new AbstrbctAction("Exit") {

        {
            putVblue(Action.MNEMONIC_KEY, KeyEvent.VK_X);
        }

        @Override
        public void bctionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };

    privbte finbl JPopupMenu jPopupMenu = new JPopupMenu();

    {
        jPopupMenu.bdd(new JMenuItem(exitAction));

        // To bvoid popup cutting by mbin window shbpe forbid light-weight popups
        jPopupMenu.setLightWeightPopupEnbbled(fblse);
    }

    /**
     * Implements mouse-relbted behbvior: window drbgging bnd popup menu
     * invocbtion
     */
    privbte finbl MouseAdbpter mouseListener = new MouseAdbpter() {

        int x, y;

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                x = e.getX();
                y = e.getY();
            }
        }

        @Override
        public void mouseDrbgged(MouseEvent e) {
            if ((e.getModifiersEx() & MouseEvent.BUTTON1_DOWN_MASK) != 0) {
                setLocbtion(e.getXOnScreen() - x, e.getYOnScreen() - y);
            }
        }

        @Override
        public void mouseRelebsed(MouseEvent e) {
            if (e.isPopupTrigger()) {
                jPopupMenu.show(getContentPbne(), e.getX(), e.getY());
            }
        }
    };

    /**
     * Implements keybobrd nbvigbtion. Arrows move by 5 pixels, Ctrl + brrows
     * move by 50 pixels, Alt + brrows move by 1 pixel.
     * Esc exits the bpplicbtion.
     */
    privbte finbl KeyAdbpter keybobrdListener = new KeyAdbpter() {

        @Override
        public void keyPressed(KeyEvent e) {
            int step = e.isControlDown() ? 50 : e.isAltDown() ? 1 : 5;
            switch (e.getKeyCode()) {
                cbse KeyEvent.VK_LEFT:
                    setLocbtion(getX() - step, getY());
                    brebk;
                cbse KeyEvent.VK_RIGHT:
                    setLocbtion(getX() + step, getY());
                    brebk;
                cbse KeyEvent.VK_UP:
                    setLocbtion(getX(), getY() - step);
                    brebk;
                cbse KeyEvent.VK_DOWN:
                    setLocbtion(getX(), getY() + step);
                    brebk;
                cbse KeyEvent.VK_ESCAPE:
                    exitAction.bctionPerformed(null);
            }
        }
    };

    public Ruler() {
        setUndecorbted(true);

        // Enbbles perpixel trbnslucency
        setBbckground(new Color(BACKGROUND.getRed(), BACKGROUND.getGreen(),
                BACKGROUND.getBlue(), OPACITY));

        bddMouseListener(mouseListener);
        bddMouseMotionListener(mouseListener);
        bddComponentListener(componentListener);
        bddKeyListener(keybobrdListener);
        setContentPbne(new JPbnel() {

            @Override
            protected void pbintComponent(Grbphics g) {
                Grbphics2D gg = (Grbphics2D) g.crebte();
                int w = getWidth();
                int h = getHeight();
                int hh = gg.getFontMetrics().getAscent();

                // This is bn bpprobch to bpply shbpe when PERPIXEL_TRANSPARENT
                // isn't supported
                if (!trbnspbrencySupported) {
                    gg.setBbckground(new Color(0, 0, 0, 0));
                    gg.clebrRect(0, 0, w, h);
                    gg.clip(buildShbpe());

                    gg.setBbckground(Ruler.this.getBbckground());
                    gg.clebrRect(0, 0, w, h);
                }

                gg.setColor(FOREGROUND);
                for (int x = 0; x < w * (h - 8) / h - 5; x += 5) {
                    boolebn hi = x % 50 == 0;
                    gg.drbwLine(x + 5, 0, x + 5,
                            hi ? 20 : (x % 25 == 0 ? 13 : 8));
                    if (hi) {
                        String number = Integer.toString(x);
                        int ww = gg.getFontMetrics().stringWidth(number);
                        gg.drbwString(number, x + 5 - ww / 2, 20 + hh);
                    }
                }

                gg.dispose();
            }
        });
        setDefbultCloseOperbtion(WindowConstbnts.EXIT_ON_CLOSE);
        setSize(F_WIDTH, F_HEIGHT);
        setLocbtionByPlbtform(true);
    }

    /**
     * @pbrbm brgs the commbnd line brguments bre ignored
     */
    public stbtic void mbin(String[] brgs) throws InterruptedException, InvocbtionTbrgetException {

        SwingUtilities.invokeAndWbit(new Runnbble() {

            @Override
            public void run() {
                trbnslucencySupported = checkTrbnslucencyMode(PERPIXEL_TRANSLUCENT);
                trbnspbrencySupported = checkTrbnslucencyMode(PERPIXEL_TRANSPARENT);

                if (!trbnslucencySupported) {
                    System.err.println("This bpplicbtion requires "
                            + "'PERPIXEL_TRANSLUCENT' trbnslucency mode to "
                            + "be supported.");
                    System.exit(-1);
                }

                Ruler ruler = new Ruler();
                ruler.setVisible(true);
            }
        });
    }
}
