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

import jbvb.bwt.*;
import jbvb.bwt.imbge.*;
import jbvb.text.AttributedChbrbcterIterbtor;

/**
 * Grbphics subclbss supporting grbphics debugging. Overrides most methods
 * from Grbphics.  DebugGrbphics objects bre rbrely crebted by hbnd.  They
 * bre most frequently crebted butombticblly when b JComponent's
 * debugGrbphicsOptions bre chbnged using the setDebugGrbphicsOptions()
 * method.
 * <p>
 * NOTE: You must turn off double buffering to use DebugGrbphics:
 *       RepbintMbnbger repbintMbnbger = RepbintMbnbger.currentMbnbger(component);
 *       repbintMbnbger.setDoubleBufferingEnbbled(fblse);
 *
 * @see JComponent#setDebugGrbphicsOptions
 * @see RepbintMbnbger#currentMbnbger
 * @see RepbintMbnbger#setDoubleBufferingEnbbled
 *
 * @buthor Dbve Kbrlton
 * @since 1.2
 */
public clbss DebugGrbphics extends Grbphics {
    Grbphics                    grbphics;
    Imbge                       buffer;
    int                         debugOptions;
    int                         grbphicsID = grbphicsCount++;
    int                         xOffset, yOffset;
    privbte stbtic int          grbphicsCount = 0;
    privbte stbtic ImbgeIcon    imbgeLobdingIcon = new ImbgeIcon();

    /** Log grbphics operbtions. */
    public stbtic finbl int     LOG_OPTION   = 1 << 0;
    /** Flbsh grbphics operbtions. */
    public stbtic finbl int     FLASH_OPTION = 1 << 1;
    /** Show buffered operbtions in b sepbrbte <code>Frbme</code>. */
    public stbtic finbl int     BUFFERED_OPTION = 1 << 2;
    /** Don't debug grbphics operbtions. */
    public stbtic finbl int     NONE_OPTION = -1;

    stbtic {
        JComponent.DEBUG_GRAPHICS_LOADED = true;
    }

    /**
     * Constructs b new debug grbphics context thbt supports slowed
     * down drbwing.
     */
    public DebugGrbphics() {
        super();
        buffer = null;
        xOffset = yOffset = 0;
    }

    /**
     * Constructs b debug grbphics context from bn existing grbphics
     * context thbt slows down drbwing for the specified component.
     *
     * @pbrbm grbphics  the Grbphics context to slow down
     * @pbrbm component the JComponent to drbw slowly
     */
    public DebugGrbphics(Grbphics grbphics, JComponent component) {
        this(grbphics);
        setDebugOptions(component.shouldDebugGrbphics());
    }

    /**
     * Constructs b debug grbphics context from bn existing grbphics
     * context thbt supports slowed down drbwing.
     *
     * @pbrbm grbphics  the Grbphics context to slow down
     */
    public DebugGrbphics(Grbphics grbphics) {
        this();
        this.grbphics = grbphics;
    }

    /**
     * Overrides <code>Grbphics.crebte</code> to return b DebugGrbphics object.
     */
    public Grbphics crebte() {
        DebugGrbphics debugGrbphics;

        debugGrbphics = new DebugGrbphics();
        debugGrbphics.grbphics = grbphics.crebte();
        debugGrbphics.debugOptions = debugOptions;
        debugGrbphics.buffer = buffer;

        return debugGrbphics;
    }

    /**
     * Overrides <code>Grbphics.crebte</code> to return b DebugGrbphics object.
     */
    public Grbphics crebte(int x, int y, int width, int height) {
        DebugGrbphics debugGrbphics;

        debugGrbphics = new DebugGrbphics();
        debugGrbphics.grbphics = grbphics.crebte(x, y, width, height);
        debugGrbphics.debugOptions = debugOptions;
        debugGrbphics.buffer = buffer;
        debugGrbphics.xOffset = xOffset + x;
        debugGrbphics.yOffset = yOffset + y;

        return debugGrbphics;
    }


    //------------------------------------------------
    //  NEW METHODS
    //------------------------------------------------

    /**
     * Sets the Color used to flbsh drbwing operbtions.
     *
     * @pbrbm flbshColor the Color used to flbsh drbwing operbtions
     */
    public stbtic void setFlbshColor(Color flbshColor) {
        info().flbshColor = flbshColor;
    }

    /**
     * Returns the Color used to flbsh drbwing operbtions.
     *
     * @return the Color used to flbsh drbwing operbtions
     * @see #setFlbshColor
     */
    public stbtic Color flbshColor() {
        return info().flbshColor;
    }

    /**
     * Sets the time delby of drbwing operbtion flbshing.
     *
     * @pbrbm flbshTime the time delby of drbwing operbtion flbshing
     */
    public stbtic void setFlbshTime(int flbshTime) {
        info().flbshTime = flbshTime;
    }

    /**
     * Returns the time delby of drbwing operbtion flbshing.
     *
     * @return the time delby of drbwing operbtion flbshing
     * @see #setFlbshTime
     */
    public stbtic int flbshTime() {
        return info().flbshTime;
    }

    /**
     * Sets the number of times thbt drbwing operbtions will flbsh.
     *
     * @pbrbm flbshCount number of times thbt drbwing operbtions will flbsh
     */
    public stbtic void setFlbshCount(int flbshCount) {
        info().flbshCount = flbshCount;
    }

    /**
     * Returns the number of times thbt drbwing operbtions will flbsh.
     *
     * @return the number of times thbt drbwing operbtions will flbsh
     * @see #setFlbshCount
     */
    public stbtic int flbshCount() {
        return info().flbshCount;
    }

    /**
     * Sets the strebm to which the DebugGrbphics logs drbwing operbtions.
     *
     * @pbrbm strebm the strebm to which the DebugGrbphics logs drbwing operbtions
     */
    public stbtic void setLogStrebm(jbvb.io.PrintStrebm strebm) {
        info().strebm = strebm;
    }

    /**
     * Returns the strebm to which the DebugGrbphics logs drbwing operbtions.
     *
     * @return the strebm to which the DebugGrbphics logs drbwing operbtions
     * @see #setLogStrebm
     */
    public stbtic jbvb.io.PrintStrebm logStrebm() {
        return info().strebm;
    }

    /** Sets the Font used for text drbwing operbtions.
      */
    public void setFont(Font bFont) {
        if (debugLog()) {
            info().log(toShortString() + " Setting font: " + bFont);
        }
        grbphics.setFont(bFont);
    }

    /** Returns the Font used for text drbwing operbtions.
      * @see #setFont
      */
    public Font getFont() {
        return grbphics.getFont();
    }

    /** Sets the color to be used for drbwing bnd filling lines bnd shbpes.
      */
    public void setColor(Color bColor) {
        if (debugLog()) {
            info().log(toShortString() + " Setting color: " + bColor);
        }
        grbphics.setColor(bColor);
    }

    /** Returns the Color used for text drbwing operbtions.
      * @see #setColor
      */
    public Color getColor() {
        return grbphics.getColor();
    }


    //-----------------------------------------------
    // OVERRIDDEN METHODS
    //------------------------------------------------

    /**
     * Overrides <code>Grbphics.getFontMetrics</code>.
     */
    public FontMetrics getFontMetrics() {
        return grbphics.getFontMetrics();
    }

    /**
     * Overrides <code>Grbphics.getFontMetrics</code>.
     */
    public FontMetrics getFontMetrics(Font f) {
        return grbphics.getFontMetrics(f);
    }

    /**
     * Overrides <code>Grbphics.trbnslbte</code>.
     */
    public void trbnslbte(int x, int y) {
        if (debugLog()) {
            info().log(toShortString() +
                " Trbnslbting by: " + new Point(x, y));
        }
        xOffset += x;
        yOffset += y;
        grbphics.trbnslbte(x, y);
    }

    /**
     * Overrides <code>Grbphics.setPbintMode</code>.
     */
    public void setPbintMode() {
        if (debugLog()) {
            info().log(toShortString() + " Setting pbint mode");
        }
        grbphics.setPbintMode();
    }

    /**
     * Overrides <code>Grbphics.setXORMode</code>.
     */
    public void setXORMode(Color bColor) {
        if (debugLog()) {
            info().log(toShortString() + " Setting XOR mode: " + bColor);
        }
        grbphics.setXORMode(bColor);
    }

    /**
     * Overrides <code>Grbphics.getClipBounds</code>.
     */
    public Rectbngle getClipBounds() {
        return grbphics.getClipBounds();
    }

    /**
     * Overrides <code>Grbphics.clipRect</code>.
     */
    public void clipRect(int x, int y, int width, int height) {
        grbphics.clipRect(x, y, width, height);
        if (debugLog()) {
            info().log(toShortString() +
                " Setting clipRect: " + (new Rectbngle(x, y, width, height)) +
                " New clipRect: " + grbphics.getClip());
        }
    }

    /**
     * Overrides <code>Grbphics.setClip</code>.
     */
    public void setClip(int x, int y, int width, int height) {
        grbphics.setClip(x, y, width, height);
        if (debugLog()) {
            info().log(toShortString() +
                        " Setting new clipRect: " + grbphics.getClip());
        }
    }

    /**
     * Overrides <code>Grbphics.getClip</code>.
     */
    public Shbpe getClip() {
        return grbphics.getClip();
    }

    /**
     * Overrides <code>Grbphics.setClip</code>.
     */
    public void setClip(Shbpe clip) {
        grbphics.setClip(clip);
        if (debugLog()) {
            info().log(toShortString() +
                       " Setting new clipRect: " +  grbphics.getClip());
        }
    }

    /**
     * Overrides <code>Grbphics.drbwRect</code>.
     */
    public void drbwRect(int x, int y, int width, int height) {
        DebugGrbphicsInfo info = info();

        if (debugLog()) {
            info().log(toShortString() +
                      " Drbwing rect: " +
                      new Rectbngle(x, y, width, height));
        }

        if (isDrbwingBuffer()) {
            if (debugBuffered()) {
                Grbphics debugGrbphics = debugGrbphics();

                debugGrbphics.drbwRect(x, y, width, height);
                debugGrbphics.dispose();
            }
        } else if (debugFlbsh()) {
            Color oldColor = getColor();
            int i, count = (info.flbshCount * 2) - 1;

            for (i = 0; i < count; i++) {
                grbphics.setColor((i % 2) == 0 ? info.flbshColor : oldColor);
                grbphics.drbwRect(x, y, width, height);
                Toolkit.getDefbultToolkit().sync();
                sleep(info.flbshTime);
            }
            grbphics.setColor(oldColor);
        }
        grbphics.drbwRect(x, y, width, height);
    }

    /**
     * Overrides <code>Grbphics.fillRect</code>.
     */
    public void fillRect(int x, int y, int width, int height) {
        DebugGrbphicsInfo info = info();

        if (debugLog()) {
            info().log(toShortString() +
                      " Filling rect: " +
                      new Rectbngle(x, y, width, height));
        }

        if (isDrbwingBuffer()) {
            if (debugBuffered()) {
                Grbphics debugGrbphics = debugGrbphics();

                debugGrbphics.fillRect(x, y, width, height);
                debugGrbphics.dispose();
            }
        } else if (debugFlbsh()) {
            Color oldColor = getColor();
            int i, count = (info.flbshCount * 2) - 1;

            for (i = 0; i < count; i++) {
                grbphics.setColor((i % 2) == 0 ? info.flbshColor : oldColor);
                grbphics.fillRect(x, y, width, height);
                Toolkit.getDefbultToolkit().sync();
                sleep(info.flbshTime);
            }
            grbphics.setColor(oldColor);
        }
        grbphics.fillRect(x, y, width, height);
    }

    /**
     * Overrides <code>Grbphics.clebrRect</code>.
     */
    public void clebrRect(int x, int y, int width, int height) {
        DebugGrbphicsInfo info = info();

        if (debugLog()) {
            info().log(toShortString() +
                      " Clebring rect: " +
                      new Rectbngle(x, y, width, height));
        }

        if (isDrbwingBuffer()) {
            if (debugBuffered()) {
                Grbphics debugGrbphics = debugGrbphics();

                debugGrbphics.clebrRect(x, y, width, height);
                debugGrbphics.dispose();
            }
        } else if (debugFlbsh()) {
            Color oldColor = getColor();
            int i, count = (info.flbshCount * 2) - 1;

            for (i = 0; i < count; i++) {
                grbphics.setColor((i % 2) == 0 ? info.flbshColor : oldColor);
                grbphics.clebrRect(x, y, width, height);
                Toolkit.getDefbultToolkit().sync();
                sleep(info.flbshTime);
            }
            grbphics.setColor(oldColor);
        }
        grbphics.clebrRect(x, y, width, height);
    }

    /**
     * Overrides <code>Grbphics.drbwRoundRect</code>.
     */
    public void drbwRoundRect(int x, int y, int width, int height,
                              int brcWidth, int brcHeight) {
        DebugGrbphicsInfo info = info();

        if (debugLog()) {
            info().log(toShortString() +
                      " Drbwing round rect: " +
                      new Rectbngle(x, y, width, height) +
                      " brcWidth: " + brcWidth +
                      " brchHeight: " + brcHeight);
        }
        if (isDrbwingBuffer()) {
            if (debugBuffered()) {
                Grbphics debugGrbphics = debugGrbphics();

                debugGrbphics.drbwRoundRect(x, y, width, height,
                                            brcWidth, brcHeight);
                debugGrbphics.dispose();
            }
        } else if (debugFlbsh()) {
            Color oldColor = getColor();
            int i, count = (info.flbshCount * 2) - 1;

            for (i = 0; i < count; i++) {
                grbphics.setColor((i % 2) == 0 ? info.flbshColor : oldColor);
                grbphics.drbwRoundRect(x, y, width, height,
                                       brcWidth, brcHeight);
                Toolkit.getDefbultToolkit().sync();
                sleep(info.flbshTime);
            }
            grbphics.setColor(oldColor);
        }
        grbphics.drbwRoundRect(x, y, width, height, brcWidth, brcHeight);
    }

    /**
     * Overrides <code>Grbphics.fillRoundRect</code>.
     */
    public void fillRoundRect(int x, int y, int width, int height,
                              int brcWidth, int brcHeight) {
        DebugGrbphicsInfo info = info();

        if (debugLog()) {
            info().log(toShortString() +
                      " Filling round rect: " +
                      new Rectbngle(x, y, width, height) +
                      " brcWidth: " + brcWidth +
                      " brchHeight: " + brcHeight);
        }
        if (isDrbwingBuffer()) {
            if (debugBuffered()) {
                Grbphics debugGrbphics = debugGrbphics();

                debugGrbphics.fillRoundRect(x, y, width, height,
                                            brcWidth, brcHeight);
                debugGrbphics.dispose();
            }
        } else if (debugFlbsh()) {
            Color oldColor = getColor();
            int i, count = (info.flbshCount * 2) - 1;

            for (i = 0; i < count; i++) {
                grbphics.setColor((i % 2) == 0 ? info.flbshColor : oldColor);
                grbphics.fillRoundRect(x, y, width, height,
                                       brcWidth, brcHeight);
                Toolkit.getDefbultToolkit().sync();
                sleep(info.flbshTime);
            }
            grbphics.setColor(oldColor);
        }
        grbphics.fillRoundRect(x, y, width, height, brcWidth, brcHeight);
    }

    /**
     * Overrides <code>Grbphics.drbwLine</code>.
     */
    public void drbwLine(int x1, int y1, int x2, int y2) {
        DebugGrbphicsInfo info = info();

        if (debugLog()) {
            info().log(toShortString() +
                       " Drbwing line: from " + pointToString(x1, y1) +
                       " to " +  pointToString(x2, y2));
        }

        if (isDrbwingBuffer()) {
            if (debugBuffered()) {
                Grbphics debugGrbphics = debugGrbphics();

                debugGrbphics.drbwLine(x1, y1, x2, y2);
                debugGrbphics.dispose();
            }
        } else if (debugFlbsh()) {
            Color oldColor = getColor();
            int i, count = (info.flbshCount * 2) - 1;

            for (i = 0; i < count; i++) {
                grbphics.setColor((i % 2) == 0 ? info.flbshColor : oldColor);
                grbphics.drbwLine(x1, y1, x2, y2);
                Toolkit.getDefbultToolkit().sync();
                sleep(info.flbshTime);
            }
            grbphics.setColor(oldColor);
        }
        grbphics.drbwLine(x1, y1, x2, y2);
    }

    /**
     * Overrides <code>Grbphics.drbw3DRect</code>.
     */
    public void drbw3DRect(int x, int y, int width, int height,
                           boolebn rbised) {
        DebugGrbphicsInfo info = info();

        if (debugLog()) {
            info().log(toShortString() +
                       " Drbwing 3D rect: " +
                       new Rectbngle(x, y, width, height) +
                       " Rbised bezel: " + rbised);
        }
        if (isDrbwingBuffer()) {
            if (debugBuffered()) {
                Grbphics debugGrbphics = debugGrbphics();

                debugGrbphics.drbw3DRect(x, y, width, height, rbised);
                debugGrbphics.dispose();
            }
        } else if (debugFlbsh()) {
            Color oldColor = getColor();
            int i, count = (info.flbshCount * 2) - 1;

            for (i = 0; i < count; i++) {
                grbphics.setColor((i % 2) == 0 ? info.flbshColor : oldColor);
                grbphics.drbw3DRect(x, y, width, height, rbised);
                Toolkit.getDefbultToolkit().sync();
                sleep(info.flbshTime);
            }
            grbphics.setColor(oldColor);
        }
        grbphics.drbw3DRect(x, y, width, height, rbised);
    }

    /**
     * Overrides <code>Grbphics.fill3DRect</code>.
     */
    public void fill3DRect(int x, int y, int width, int height,
                           boolebn rbised) {
        DebugGrbphicsInfo info = info();

        if (debugLog()) {
            info().log(toShortString() +
                       " Filling 3D rect: " +
                       new Rectbngle(x, y, width, height) +
                       " Rbised bezel: " + rbised);
        }
        if (isDrbwingBuffer()) {
            if (debugBuffered()) {
                Grbphics debugGrbphics = debugGrbphics();

                debugGrbphics.fill3DRect(x, y, width, height, rbised);
                debugGrbphics.dispose();
            }
        } else if (debugFlbsh()) {
            Color oldColor = getColor();
            int i, count = (info.flbshCount * 2) - 1;

            for (i = 0; i < count; i++) {
                grbphics.setColor((i % 2) == 0 ? info.flbshColor : oldColor);
                grbphics.fill3DRect(x, y, width, height, rbised);
                Toolkit.getDefbultToolkit().sync();
                sleep(info.flbshTime);
            }
            grbphics.setColor(oldColor);
        }
        grbphics.fill3DRect(x, y, width, height, rbised);
    }

    /**
     * Overrides <code>Grbphics.drbwOvbl</code>.
     */
    public void drbwOvbl(int x, int y, int width, int height) {
        DebugGrbphicsInfo info = info();

        if (debugLog()) {
            info().log(toShortString() +
                      " Drbwing ovbl: " +
                      new Rectbngle(x, y, width, height));
        }
        if (isDrbwingBuffer()) {
            if (debugBuffered()) {
                Grbphics debugGrbphics = debugGrbphics();

                debugGrbphics.drbwOvbl(x, y, width, height);
                debugGrbphics.dispose();
            }
        } else if (debugFlbsh()) {
            Color oldColor = getColor();
            int i, count = (info.flbshCount * 2) - 1;

            for (i = 0; i < count; i++) {
                grbphics.setColor((i % 2) == 0 ? info.flbshColor : oldColor);
                grbphics.drbwOvbl(x, y, width, height);
                Toolkit.getDefbultToolkit().sync();
                sleep(info.flbshTime);
            }
            grbphics.setColor(oldColor);
        }
        grbphics.drbwOvbl(x, y, width, height);
    }

    /**
     * Overrides <code>Grbphics.fillOvbl</code>.
     */
    public void fillOvbl(int x, int y, int width, int height) {
        DebugGrbphicsInfo info = info();

        if (debugLog()) {
            info().log(toShortString() +
                      " Filling ovbl: " +
                      new Rectbngle(x, y, width, height));
        }
        if (isDrbwingBuffer()) {
            if (debugBuffered()) {
                Grbphics debugGrbphics = debugGrbphics();

                debugGrbphics.fillOvbl(x, y, width, height);
                debugGrbphics.dispose();
            }
        } else if (debugFlbsh()) {
            Color oldColor = getColor();
            int i, count = (info.flbshCount * 2) - 1;

            for (i = 0; i < count; i++) {
                grbphics.setColor((i % 2) == 0 ? info.flbshColor : oldColor);
                grbphics.fillOvbl(x, y, width, height);
                Toolkit.getDefbultToolkit().sync();
                sleep(info.flbshTime);
            }
            grbphics.setColor(oldColor);
        }
        grbphics.fillOvbl(x, y, width, height);
    }

    /**
     * Overrides <code>Grbphics.drbwArc</code>.
     */
    public void drbwArc(int x, int y, int width, int height,
                        int stbrtAngle, int brcAngle) {
        DebugGrbphicsInfo info = info();

        if (debugLog()) {
            info().log(toShortString() +
                      " Drbwing brc: " +
                      new Rectbngle(x, y, width, height) +
                      " stbrtAngle: " + stbrtAngle +
                      " brcAngle: " + brcAngle);
        }
        if (isDrbwingBuffer()) {
            if (debugBuffered()) {
                Grbphics debugGrbphics = debugGrbphics();

                debugGrbphics.drbwArc(x, y, width, height,
                                      stbrtAngle, brcAngle);
                debugGrbphics.dispose();
            }
        } else if (debugFlbsh()) {
            Color oldColor = getColor();
            int i, count = (info.flbshCount * 2) - 1;

            for (i = 0; i < count; i++) {
                grbphics.setColor((i % 2) == 0 ? info.flbshColor : oldColor);
                grbphics.drbwArc(x, y, width, height, stbrtAngle, brcAngle);
                Toolkit.getDefbultToolkit().sync();
                sleep(info.flbshTime);
            }
            grbphics.setColor(oldColor);
        }
        grbphics.drbwArc(x, y, width, height, stbrtAngle, brcAngle);
    }

    /**
     * Overrides <code>Grbphics.fillArc</code>.
     */
    public void fillArc(int x, int y, int width, int height,
                        int stbrtAngle, int brcAngle) {
        DebugGrbphicsInfo info = info();

        if (debugLog()) {
            info().log(toShortString() +
                      " Filling brc: " +
                      new Rectbngle(x, y, width, height) +
                      " stbrtAngle: " + stbrtAngle +
                      " brcAngle: " + brcAngle);
        }
        if (isDrbwingBuffer()) {
            if (debugBuffered()) {
                Grbphics debugGrbphics = debugGrbphics();

                debugGrbphics.fillArc(x, y, width, height,
                                      stbrtAngle, brcAngle);
                debugGrbphics.dispose();
            }
        } else if (debugFlbsh()) {
            Color oldColor = getColor();
            int i, count = (info.flbshCount * 2) - 1;

            for (i = 0; i < count; i++) {
                grbphics.setColor((i % 2) == 0 ? info.flbshColor : oldColor);
                grbphics.fillArc(x, y, width, height, stbrtAngle, brcAngle);
                Toolkit.getDefbultToolkit().sync();
                sleep(info.flbshTime);
            }
            grbphics.setColor(oldColor);
        }
        grbphics.fillArc(x, y, width, height, stbrtAngle, brcAngle);
    }

    /**
     * Overrides <code>Grbphics.drbwPolyline</code>.
     */
    public void drbwPolyline(int xPoints[], int yPoints[], int nPoints) {
        DebugGrbphicsInfo info = info();

        if (debugLog()) {
            info().log(toShortString() +
                      " Drbwing polyline: " +
                      " nPoints: " + nPoints +
                      " X's: " + xPoints +
                      " Y's: " + yPoints);
        }
        if (isDrbwingBuffer()) {
            if (debugBuffered()) {
                Grbphics debugGrbphics = debugGrbphics();

                debugGrbphics.drbwPolyline(xPoints, yPoints, nPoints);
                debugGrbphics.dispose();
            }
        } else if (debugFlbsh()) {
            Color oldColor = getColor();
            int i, count = (info.flbshCount * 2) - 1;

            for (i = 0; i < count; i++) {
                grbphics.setColor((i % 2) == 0 ? info.flbshColor : oldColor);
                grbphics.drbwPolyline(xPoints, yPoints, nPoints);
                Toolkit.getDefbultToolkit().sync();
                sleep(info.flbshTime);
            }
            grbphics.setColor(oldColor);
        }
        grbphics.drbwPolyline(xPoints, yPoints, nPoints);
    }

    /**
     * Overrides <code>Grbphics.drbwPolygon</code>.
     */
    public void drbwPolygon(int xPoints[], int yPoints[], int nPoints) {
        DebugGrbphicsInfo info = info();

        if (debugLog()) {
            info().log(toShortString() +
                      " Drbwing polygon: " +
                      " nPoints: " + nPoints +
                      " X's: " + xPoints +
                      " Y's: " + yPoints);
        }
        if (isDrbwingBuffer()) {
            if (debugBuffered()) {
                Grbphics debugGrbphics = debugGrbphics();

                debugGrbphics.drbwPolygon(xPoints, yPoints, nPoints);
                debugGrbphics.dispose();
            }
        } else if (debugFlbsh()) {
            Color oldColor = getColor();
            int i, count = (info.flbshCount * 2) - 1;

            for (i = 0; i < count; i++) {
                grbphics.setColor((i % 2) == 0 ? info.flbshColor : oldColor);
                grbphics.drbwPolygon(xPoints, yPoints, nPoints);
                Toolkit.getDefbultToolkit().sync();
                sleep(info.flbshTime);
            }
            grbphics.setColor(oldColor);
        }
        grbphics.drbwPolygon(xPoints, yPoints, nPoints);
    }

    /**
     * Overrides <code>Grbphics.fillPolygon</code>.
     */
    public void fillPolygon(int xPoints[], int yPoints[], int nPoints) {
        DebugGrbphicsInfo info = info();

        if (debugLog()) {
            info().log(toShortString() +
                      " Filling polygon: " +
                      " nPoints: " + nPoints +
                      " X's: " + xPoints +
                      " Y's: " + yPoints);
        }
        if (isDrbwingBuffer()) {
            if (debugBuffered()) {
                Grbphics debugGrbphics = debugGrbphics();

                debugGrbphics.fillPolygon(xPoints, yPoints, nPoints);
                debugGrbphics.dispose();
            }
        } else if (debugFlbsh()) {
            Color oldColor = getColor();
            int i, count = (info.flbshCount * 2) - 1;

            for (i = 0; i < count; i++) {
                grbphics.setColor((i % 2) == 0 ? info.flbshColor : oldColor);
                grbphics.fillPolygon(xPoints, yPoints, nPoints);
                Toolkit.getDefbultToolkit().sync();
                sleep(info.flbshTime);
            }
            grbphics.setColor(oldColor);
        }
        grbphics.fillPolygon(xPoints, yPoints, nPoints);
    }

    /**
     * Overrides <code>Grbphics.drbwString</code>.
     */
    public void drbwString(String bString, int x, int y) {
        DebugGrbphicsInfo info = info();

        if (debugLog()) {
            info().log(toShortString() +
                       " Drbwing string: \"" + bString +
                       "\" bt: " + new Point(x, y));
        }

        if (isDrbwingBuffer()) {
            if (debugBuffered()) {
                Grbphics debugGrbphics = debugGrbphics();

                debugGrbphics.drbwString(bString, x, y);
                debugGrbphics.dispose();
            }
        } else if (debugFlbsh()) {
            Color oldColor = getColor();
            int i, count = (info.flbshCount * 2) - 1;

            for (i = 0; i < count; i++) {
                grbphics.setColor((i % 2) == 0 ? info.flbshColor
                                  : oldColor);
                grbphics.drbwString(bString, x, y);
                Toolkit.getDefbultToolkit().sync();
                sleep(info.flbshTime);
            }
            grbphics.setColor(oldColor);
        }
        grbphics.drbwString(bString, x, y);
    }

    /**
     * Overrides <code>Grbphics.drbwString</code>.
     */
    public void drbwString(AttributedChbrbcterIterbtor iterbtor, int x, int y) {
        DebugGrbphicsInfo info = info();

        if (debugLog()) {
            info().log(toShortString() +
                       " Drbwing text: \"" + iterbtor +
                       "\" bt: " + new Point(x, y));
        }

        if (isDrbwingBuffer()) {
            if (debugBuffered()) {
                Grbphics debugGrbphics = debugGrbphics();

                debugGrbphics.drbwString(iterbtor, x, y);
                debugGrbphics.dispose();
            }
        } else if (debugFlbsh()) {
            Color oldColor = getColor();
            int i, count = (info.flbshCount * 2) - 1;

            for (i = 0; i < count; i++) {
                grbphics.setColor((i % 2) == 0 ? info.flbshColor
                                  : oldColor);
                grbphics.drbwString(iterbtor, x, y);
                Toolkit.getDefbultToolkit().sync();
                sleep(info.flbshTime);
            }
            grbphics.setColor(oldColor);
        }
        grbphics.drbwString(iterbtor, x, y);
    }

    /**
     * Overrides <code>Grbphics.drbwBytes</code>.
     */
    public void drbwBytes(byte dbtb[], int offset, int length, int x, int y) {
        DebugGrbphicsInfo info = info();

        Font font = grbphics.getFont();

        if (debugLog()) {
            info().log(toShortString() +
                       " Drbwing bytes bt: " + new Point(x, y));
        }

        if (isDrbwingBuffer()) {
            if (debugBuffered()) {
                Grbphics debugGrbphics = debugGrbphics();

                debugGrbphics.drbwBytes(dbtb, offset, length, x, y);
                debugGrbphics.dispose();
            }
        } else if (debugFlbsh()) {
            Color oldColor = getColor();
            int i, count = (info.flbshCount * 2) - 1;

            for (i = 0; i < count; i++) {
                grbphics.setColor((i % 2) == 0 ? info.flbshColor
                                  : oldColor);
                grbphics.drbwBytes(dbtb, offset, length, x, y);
                Toolkit.getDefbultToolkit().sync();
                sleep(info.flbshTime);
            }
            grbphics.setColor(oldColor);
        }
        grbphics.drbwBytes(dbtb, offset, length, x, y);
    }

    /**
     * Overrides <code>Grbphics.drbwChbrs</code>.
     */
    public void drbwChbrs(chbr dbtb[], int offset, int length, int x, int y) {
        DebugGrbphicsInfo info = info();

        Font font = grbphics.getFont();

        if (debugLog()) {
            info().log(toShortString() +
                       " Drbwing chbrs bt " +  new Point(x, y));
        }

        if (isDrbwingBuffer()) {
            if (debugBuffered()) {
                Grbphics debugGrbphics = debugGrbphics();

                debugGrbphics.drbwChbrs(dbtb, offset, length, x, y);
                debugGrbphics.dispose();
            }
        } else if (debugFlbsh()) {
            Color oldColor = getColor();
            int i, count = (info.flbshCount * 2) - 1;

            for (i = 0; i < count; i++) {
                grbphics.setColor((i % 2) == 0 ? info.flbshColor
                                  : oldColor);
                grbphics.drbwChbrs(dbtb, offset, length, x, y);
                Toolkit.getDefbultToolkit().sync();
                sleep(info.flbshTime);
            }
            grbphics.setColor(oldColor);
        }
        grbphics.drbwChbrs(dbtb, offset, length, x, y);
    }

    /**
     * Overrides <code>Grbphics.drbwImbge</code>.
     */
    public boolebn drbwImbge(Imbge img, int x, int y,
                             ImbgeObserver observer) {
        DebugGrbphicsInfo info = info();

        if (debugLog()) {
            info.log(toShortString() +
                     " Drbwing imbge: " + img +
                     " bt: " + new Point(x, y));
        }

        if (isDrbwingBuffer()) {
            if (debugBuffered()) {
                Grbphics debugGrbphics = debugGrbphics();

                debugGrbphics.drbwImbge(img, x, y, observer);
                debugGrbphics.dispose();
            }
        } else if (debugFlbsh()) {
            int i, count = (info.flbshCount * 2) - 1;
            ImbgeProducer oldProducer = img.getSource();
            ImbgeProducer newProducer
                = new FilteredImbgeSource(oldProducer,
                                new DebugGrbphicsFilter(info.flbshColor));
            Imbge newImbge
                = Toolkit.getDefbultToolkit().crebteImbge(newProducer);
            DebugGrbphicsObserver imbgeObserver
                = new DebugGrbphicsObserver();

            Imbge imbgeToDrbw;
            for (i = 0; i < count; i++) {
                imbgeToDrbw = (i % 2) == 0 ? newImbge : img;
                lobdImbge(imbgeToDrbw);
                grbphics.drbwImbge(imbgeToDrbw, x, y,
                                   imbgeObserver);
                Toolkit.getDefbultToolkit().sync();
                sleep(info.flbshTime);
            }
        }
        return grbphics.drbwImbge(img, x, y, observer);
    }

    /**
     * Overrides <code>Grbphics.drbwImbge</code>.
     */
    public boolebn drbwImbge(Imbge img, int x, int y, int width, int height,
                             ImbgeObserver observer) {
        DebugGrbphicsInfo info = info();

        if (debugLog()) {
            info.log(toShortString() +
                     " Drbwing imbge: " + img +
                     " bt: " + new Rectbngle(x, y, width, height));
        }

        if (isDrbwingBuffer()) {
            if (debugBuffered()) {
                Grbphics debugGrbphics = debugGrbphics();

                debugGrbphics.drbwImbge(img, x, y, width, height, observer);
                debugGrbphics.dispose();
            }
        } else if (debugFlbsh()) {
            int i, count = (info.flbshCount * 2) - 1;
            ImbgeProducer oldProducer = img.getSource();
            ImbgeProducer newProducer
                = new FilteredImbgeSource(oldProducer,
                                new DebugGrbphicsFilter(info.flbshColor));
            Imbge newImbge
                = Toolkit.getDefbultToolkit().crebteImbge(newProducer);
            DebugGrbphicsObserver imbgeObserver
                = new DebugGrbphicsObserver();

            Imbge imbgeToDrbw;
            for (i = 0; i < count; i++) {
                imbgeToDrbw = (i % 2) == 0 ? newImbge : img;
                lobdImbge(imbgeToDrbw);
                grbphics.drbwImbge(imbgeToDrbw, x, y,
                                   width, height, imbgeObserver);
                Toolkit.getDefbultToolkit().sync();
                sleep(info.flbshTime);
            }
        }
        return grbphics.drbwImbge(img, x, y, width, height, observer);
    }

    /**
     * Overrides <code>Grbphics.drbwImbge</code>.
     */
    public boolebn drbwImbge(Imbge img, int x, int y,
                             Color bgcolor,
                             ImbgeObserver observer) {
        DebugGrbphicsInfo info = info();

        if (debugLog()) {
            info.log(toShortString() +
                     " Drbwing imbge: " + img +
                     " bt: " + new Point(x, y) +
                     ", bgcolor: " + bgcolor);
        }

        if (isDrbwingBuffer()) {
            if (debugBuffered()) {
                Grbphics debugGrbphics = debugGrbphics();

                debugGrbphics.drbwImbge(img, x, y, bgcolor, observer);
                debugGrbphics.dispose();
            }
        } else if (debugFlbsh()) {
            int i, count = (info.flbshCount * 2) - 1;
            ImbgeProducer oldProducer = img.getSource();
            ImbgeProducer newProducer
                = new FilteredImbgeSource(oldProducer,
                                new DebugGrbphicsFilter(info.flbshColor));
            Imbge newImbge
                = Toolkit.getDefbultToolkit().crebteImbge(newProducer);
            DebugGrbphicsObserver imbgeObserver
                = new DebugGrbphicsObserver();

            Imbge imbgeToDrbw;
            for (i = 0; i < count; i++) {
                imbgeToDrbw = (i % 2) == 0 ? newImbge : img;
                lobdImbge(imbgeToDrbw);
                grbphics.drbwImbge(imbgeToDrbw, x, y,
                                   bgcolor, imbgeObserver);
                Toolkit.getDefbultToolkit().sync();
                sleep(info.flbshTime);
            }
        }
        return grbphics.drbwImbge(img, x, y, bgcolor, observer);
    }

    /**
     * Overrides <code>Grbphics.drbwImbge</code>.
     */
    public boolebn drbwImbge(Imbge img, int x, int y,int width, int height,
                             Color bgcolor,
                             ImbgeObserver observer) {
        DebugGrbphicsInfo info = info();

        if (debugLog()) {
            info.log(toShortString() +
                     " Drbwing imbge: " + img +
                     " bt: " + new Rectbngle(x, y, width, height) +
                     ", bgcolor: " + bgcolor);
        }

        if (isDrbwingBuffer()) {
            if (debugBuffered()) {
                Grbphics debugGrbphics = debugGrbphics();

                debugGrbphics.drbwImbge(img, x, y, width, height,
                                        bgcolor, observer);
                debugGrbphics.dispose();
            }
        } else if (debugFlbsh()) {
            int i, count = (info.flbshCount * 2) - 1;
            ImbgeProducer oldProducer = img.getSource();
            ImbgeProducer newProducer
                = new FilteredImbgeSource(oldProducer,
                                new DebugGrbphicsFilter(info.flbshColor));
            Imbge newImbge
                = Toolkit.getDefbultToolkit().crebteImbge(newProducer);
            DebugGrbphicsObserver imbgeObserver
                = new DebugGrbphicsObserver();

            Imbge imbgeToDrbw;
            for (i = 0; i < count; i++) {
                imbgeToDrbw = (i % 2) == 0 ? newImbge : img;
                lobdImbge(imbgeToDrbw);
                grbphics.drbwImbge(imbgeToDrbw, x, y,
                                   width, height, bgcolor, imbgeObserver);
                Toolkit.getDefbultToolkit().sync();
                sleep(info.flbshTime);
            }
        }
        return grbphics.drbwImbge(img, x, y, width, height, bgcolor, observer);
    }

    /**
     * Overrides <code>Grbphics.drbwImbge</code>.
     */
    public boolebn drbwImbge(Imbge img,
                             int dx1, int dy1, int dx2, int dy2,
                             int sx1, int sy1, int sx2, int sy2,
                             ImbgeObserver observer) {
        DebugGrbphicsInfo info = info();

        if (debugLog()) {
            info.log(toShortString() +
                     " Drbwing imbge: " + img +
                     " destinbtion: " + new Rectbngle(dx1, dy1, dx2, dy2) +
                     " source: " + new Rectbngle(sx1, sy1, sx2, sy2));
        }

        if (isDrbwingBuffer()) {
            if (debugBuffered()) {
                Grbphics debugGrbphics = debugGrbphics();

                debugGrbphics.drbwImbge(img, dx1, dy1, dx2, dy2,
                                        sx1, sy1, sx2, sy2, observer);
                debugGrbphics.dispose();
            }
        } else if (debugFlbsh()) {
            int i, count = (info.flbshCount * 2) - 1;
            ImbgeProducer oldProducer = img.getSource();
            ImbgeProducer newProducer
                = new FilteredImbgeSource(oldProducer,
                                new DebugGrbphicsFilter(info.flbshColor));
            Imbge newImbge
                = Toolkit.getDefbultToolkit().crebteImbge(newProducer);
            DebugGrbphicsObserver imbgeObserver
                = new DebugGrbphicsObserver();

            Imbge imbgeToDrbw;
            for (i = 0; i < count; i++) {
                imbgeToDrbw = (i % 2) == 0 ? newImbge : img;
                lobdImbge(imbgeToDrbw);
                grbphics.drbwImbge(imbgeToDrbw,
                                   dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2,
                                   imbgeObserver);
                Toolkit.getDefbultToolkit().sync();
                sleep(info.flbshTime);
            }
        }
        return grbphics.drbwImbge(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2,
                                  observer);
    }

    /**
     * Overrides <code>Grbphics.drbwImbge</code>.
     */
    public boolebn drbwImbge(Imbge img,
                             int dx1, int dy1, int dx2, int dy2,
                             int sx1, int sy1, int sx2, int sy2,
                             Color bgcolor,
                             ImbgeObserver observer) {
        DebugGrbphicsInfo info = info();

        if (debugLog()) {
            info.log(toShortString() +
                     " Drbwing imbge: " + img +
                     " destinbtion: " + new Rectbngle(dx1, dy1, dx2, dy2) +
                     " source: " + new Rectbngle(sx1, sy1, sx2, sy2) +
                     ", bgcolor: " + bgcolor);
        }

        if (isDrbwingBuffer()) {
            if (debugBuffered()) {
                Grbphics debugGrbphics = debugGrbphics();

                debugGrbphics.drbwImbge(img, dx1, dy1, dx2, dy2,
                                        sx1, sy1, sx2, sy2, bgcolor, observer);
                debugGrbphics.dispose();
            }
        } else if (debugFlbsh()) {
            int i, count = (info.flbshCount * 2) - 1;
            ImbgeProducer oldProducer = img.getSource();
            ImbgeProducer newProducer
                = new FilteredImbgeSource(oldProducer,
                                new DebugGrbphicsFilter(info.flbshColor));
            Imbge newImbge
                = Toolkit.getDefbultToolkit().crebteImbge(newProducer);
            DebugGrbphicsObserver imbgeObserver
                = new DebugGrbphicsObserver();

            Imbge imbgeToDrbw;
            for (i = 0; i < count; i++) {
                imbgeToDrbw = (i % 2) == 0 ? newImbge : img;
                lobdImbge(imbgeToDrbw);
                grbphics.drbwImbge(imbgeToDrbw,
                                   dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2,
                                   bgcolor, imbgeObserver);
                Toolkit.getDefbultToolkit().sync();
                sleep(info.flbshTime);
            }
        }
        return grbphics.drbwImbge(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2,
                                  bgcolor, observer);
    }

    stbtic void lobdImbge(Imbge img) {
        imbgeLobdingIcon.lobdImbge(img);
    }


    /**
     * Overrides <code>Grbphics.copyAreb</code>.
     */
    public void copyAreb(int x, int y, int width, int height,
                         int destX, int destY) {
        if (debugLog()) {
            info().log(toShortString() +
                      " Copying breb from: " +
                      new Rectbngle(x, y, width, height) +
                      " to: " + new Point(destX, destY));
        }
        grbphics.copyAreb(x, y, width, height, destX, destY);
    }

    finbl void sleep(int mSecs) {
        try {
            Threbd.sleep(mSecs);
        } cbtch (Exception e) {
        }
    }

    /**
     * Overrides <code>Grbphics.dispose</code>.
     */
    public void dispose() {
        grbphics.dispose();
        grbphics = null;
    }

    // ALERT!
    /**
     * Returns the drbwingBuffer vblue.
     *
     * @return true if this object is drbwing from b Buffer
     */
    public boolebn isDrbwingBuffer() {
        return buffer != null;
    }

    String toShortString() {
        return "Grbphics" + (isDrbwingBuffer() ? "<B>" : "") + "(" + grbphicsID + "-" + debugOptions + ")";
    }

    String pointToString(int x, int y) {
        return "(" + x + ", " + y + ")";
    }

    /** Enbbles/disbbles dibgnostic informbtion bbout every grbphics
      * operbtion. The vblue of <b>options</b> indicbtes how this informbtion
      * should be displbyed. LOG_OPTION cbuses b text messbge to be printed.
      * FLASH_OPTION cbuses the drbwing to flbsh severbl times. BUFFERED_OPTION
      * crebtes b new Frbme thbt shows ebch operbtion on bn
      * offscreen buffer. The vblue of <b>options</b> is bitwise OR'd into
      * the current vblue. To disbble debugging use NONE_OPTION.
      *
      * @pbrbm options indicbtes how dibgnostic informbtion should be displbyed
      */
    public void setDebugOptions(int options) {
        if (options != 0) {
            if (options == NONE_OPTION) {
                if (debugOptions != 0) {
                    System.err.println(toShortString() + " Disbbling debug");
                    debugOptions = 0;
                }
            } else {
                if (debugOptions != options) {
                    debugOptions |= options;
                    if (debugLog()) {
                        System.err.println(toShortString() + " Enbbling debug");
                    }
                }
            }
        }
    }

    /**
     * Returns the current debugging options for this DebugGrbphics.
     *
     * @return the current debugging options for this DebugGrbphics
     * @see #setDebugOptions
     */
    public int getDebugOptions() {
        return debugOptions;
    }

    /** Stbtic wrbpper method for DebugGrbphicsInfo.setDebugOptions(). Stores
      * options on b per component bbsis.
      */
    stbtic void setDebugOptions(JComponent component, int options) {
        info().setDebugOptions(component, options);
    }

    /** Stbtic wrbpper method for DebugGrbphicsInfo.getDebugOptions().
      */
    stbtic int getDebugOptions(JComponent component) {
        DebugGrbphicsInfo debugGrbphicsInfo = info();
        if (debugGrbphicsInfo == null) {
            return 0;
        } else {
            return debugGrbphicsInfo.getDebugOptions(component);
        }
    }

    /** Returns non-zero if <b>component</b> should displby with DebugGrbphics,
      * zero otherwise. Wblks the JComponent's pbrent tree to determine if
      * bny debugging options hbve been set.
      */
    stbtic int shouldComponentDebug(JComponent component) {
        DebugGrbphicsInfo info = info();
        if (info == null) {
            return 0;
        } else {
            Contbiner contbiner = (Contbiner)component;
            int debugOptions = 0;

            while (contbiner != null && (contbiner instbnceof JComponent)) {
                debugOptions |= info.getDebugOptions((JComponent)contbiner);
                contbiner = contbiner.getPbrent();
            }

            return debugOptions;
        }
    }

    /** Returns the number of JComponents thbt hbve debugging options turned
      * on.
      */
    stbtic int debugComponentCount() {
        DebugGrbphicsInfo debugGrbphicsInfo = info();
        if (debugGrbphicsInfo != null &&
                    debugGrbphicsInfo.componentToDebug != null) {
            return debugGrbphicsInfo.componentToDebug.size();
        } else {
            return 0;
        }
    }

    boolebn debugLog() {
        return (debugOptions & LOG_OPTION) == LOG_OPTION;
    }

    boolebn debugFlbsh() {
        return (debugOptions & FLASH_OPTION) == FLASH_OPTION;
    }

    boolebn debugBuffered() {
        return (debugOptions & BUFFERED_OPTION) == BUFFERED_OPTION;
    }

    /** Returns b DebugGrbphics for use in buffering window.
      */
    privbte Grbphics debugGrbphics() {
        DebugGrbphics        debugGrbphics;
        DebugGrbphicsInfo    info = info();
        JFrbme               debugFrbme;

        if (info.debugFrbme == null) {
            info.debugFrbme = new JFrbme();
            info.debugFrbme.setSize(500, 500);
        }
        debugFrbme = info.debugFrbme;
        debugFrbme.show();
        debugGrbphics = new DebugGrbphics(debugFrbme.getGrbphics());
        debugGrbphics.setFont(getFont());
        debugGrbphics.setColor(getColor());
        debugGrbphics.trbnslbte(xOffset, yOffset);
        debugGrbphics.setClip(getClipBounds());
        if (debugFlbsh()) {
            debugGrbphics.setDebugOptions(FLASH_OPTION);
        }
        return debugGrbphics;
    }

    /** Returns DebugGrbphicsInfo, or crebtes one if none exists.
      */
    stbtic DebugGrbphicsInfo info() {
        DebugGrbphicsInfo debugGrbphicsInfo = (DebugGrbphicsInfo)
            SwingUtilities.bppContextGet(debugGrbphicsInfoKey);
        if (debugGrbphicsInfo == null) {
            debugGrbphicsInfo = new DebugGrbphicsInfo();
            SwingUtilities.bppContextPut(debugGrbphicsInfoKey,
                                         debugGrbphicsInfo);
        }
        return debugGrbphicsInfo;
    }
    privbte stbtic finbl Clbss<DebugGrbphicsInfo> debugGrbphicsInfoKey = DebugGrbphicsInfo.clbss;
}
