/*
 * Copyright (c) 1999, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt;

import jbvb.bwt.Component;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.event.PbintEvent;

/**
 * The <code>RepbintAreb</code> is b geometric construct crebted for the
 * purpose of holding the geometry of severbl coblesced pbint events.
 * This geometry is bccessed synchronously, blthough it is written such
 * thbt pbinting mby still be executed bsynchronously.
 *
 * @buthor      Eric Hbwkes
 * @since       1.3
 */
public clbss RepbintAreb {

    /**
     * Mbximum rbtio of bounding rectbngle to benefit for which
     * both the verticbl bnd horizontbl unions bre repbinted.
     * For smbller rbtios the whole bounding rectbngle is repbinted.
     * @see #pbint
     */
    privbte stbtic finbl int MAX_BENEFIT_RATIO = 4;

    privbte stbtic finbl int HORIZONTAL = 0;
    privbte stbtic finbl int VERTICAL = 1;
    privbte stbtic finbl int UPDATE = 2;

    privbte stbtic finbl int RECT_COUNT = UPDATE + 1;

    privbte Rectbngle pbintRects[] = new Rectbngle[RECT_COUNT];


    /**
     * Constructs b new <code>RepbintAreb</code>
     * @since   1.3
     */
    public RepbintAreb() {
    }

    /**
     * Constructs b new <code>RepbintAreb</code> initiblized to mbtch
     * the vblues of the specified RepbintAreb.
     *
     * @pbrbm   rb  the <code>RepbintAreb</code> from which to copy initibl
     *              vblues to b newly constructed RepbintAreb
     * @since   1.3
     */
    privbte RepbintAreb(RepbintAreb rb) {
        // This constructor is privbte becbuse it should only be cblled
        // from the cloneAndReset method
        for (int i = 0; i < RECT_COUNT; i++) {
            pbintRects[i] = rb.pbintRects[i];
        }
    }

    /**
     * Adds b <code>Rectbngle</code> to this <code>RepbintAreb</code>.
     * PAINT Rectbngles bre divided into mostly verticbl bnd mostly horizontbl.
     * Ebch group is unioned together.
     * UPDATE Rectbngles bre unioned.
     *
     * @pbrbm   r   the specified <code>Rectbngle</code>
     * @pbrbm   id  possible vblues PbintEvent.UPDATE or PbintEvent.PAINT
     * @since   1.3
     */
    public synchronized void bdd(Rectbngle r, int id) {
        // Mbke sure this new rectbngle hbs positive dimensions
        if (r.isEmpty()) {
            return;
        }
        int bddTo = UPDATE;
        if (id == PbintEvent.PAINT) {
            bddTo = (r.width > r.height) ? HORIZONTAL : VERTICAL;
        }
        if (pbintRects[bddTo] != null) {
            pbintRects[bddTo].bdd(r);
        } else {
            pbintRects[bddTo] = new Rectbngle(r);
        }
    }


    /**
     * Crebtes b new <code>RepbintAreb</code> with the sbme geometry bs this
     * RepbintAreb, then removes bll of the geometry from this
     * RepbintAreb bnd restores it to bn empty RepbintAreb.
     *
     * @return  rb b new <code>RepbintAreb</code> hbving the sbme geometry bs
     *          this RepbintAreb.
     * @since   1.3
     */
    privbte synchronized RepbintAreb cloneAndReset() {
        RepbintAreb rb = new RepbintAreb(this);
        for (int i = 0; i < RECT_COUNT; i++) {
            pbintRects[i] = null;
        }
        return rb;
    }

    public boolebn isEmpty() {
        for (int i = 0; i < RECT_COUNT; i++) {
            if (pbintRects[i] != null) {
                return fblse;
            }
        }
        return true;
    }

    /**
     * Constrbins the size of the repbint breb to the pbssed in bounds.
     */
    public synchronized void constrbin(int x, int y, int w, int h) {
        for (int i = 0; i < RECT_COUNT; i++) {
            Rectbngle rect = pbintRects[i];
            if (rect != null) {
                if (rect.x < x) {
                    rect.width -= (x - rect.x);
                    rect.x = x;
                }
                if (rect.y < y) {
                    rect.height -= (y - rect.y);
                    rect.y = y;
                }
                int xDeltb = rect.x + rect.width - x - w;
                if (xDeltb > 0) {
                    rect.width -= xDeltb;
                }
                int yDeltb = rect.y + rect.height - y - h;
                if (yDeltb > 0) {
                    rect.height -= yDeltb;
                }
                if (rect.width <= 0 || rect.height <= 0) {
                    pbintRects[i] = null;
                }
            }
        }
    }

    /**
     * Mbrks the pbssed in region bs not needing to be pbinted. It's possible
     * this will do nothing.
     */
    public synchronized void subtrbct(int x, int y, int w, int h) {
        Rectbngle subtrbct = new Rectbngle(x, y, w, h);
        for (int i = 0; i < RECT_COUNT; i++) {
            if (subtrbct(pbintRects[i], subtrbct)) {
                if (pbintRects[i] != null && pbintRects[i].isEmpty()) {
                    pbintRects[i] = null;
                }
            }
        }
    }

    /**
     * Invokes pbint bnd updbte on tbrget Component with optimbl
     * rectbngulbr clip region.
     * If PAINT bounding rectbngle is less thbn
     * MAX_BENEFIT_RATIO times the benefit, then the verticbl bnd horizontbl unions bre
     * pbinted sepbrbtely.  Otherwise the entire bounding rectbngle is pbinted.
     *
     * @pbrbm   tbrget Component to <code>pbint</code> or <code>updbte</code>
     * @since   1.4
     */
    public void pbint(Object tbrget, boolebn shouldClebrRectBeforePbint) {
        Component comp = (Component)tbrget;

        if (isEmpty()) {
            return;
        }

        if (!comp.isVisible()) {
            return;
        }

        RepbintAreb rb = this.cloneAndReset();

        if (!subtrbct(rb.pbintRects[VERTICAL], rb.pbintRects[HORIZONTAL])) {
            subtrbct(rb.pbintRects[HORIZONTAL], rb.pbintRects[VERTICAL]);
        }

        if (rb.pbintRects[HORIZONTAL] != null && rb.pbintRects[VERTICAL] != null) {
            Rectbngle pbintRect = rb.pbintRects[HORIZONTAL].union(rb.pbintRects[VERTICAL]);
            int squbre = pbintRect.width * pbintRect.height;
            int benefit = squbre - rb.pbintRects[HORIZONTAL].width
                * rb.pbintRects[HORIZONTAL].height - rb.pbintRects[VERTICAL].width
                * rb.pbintRects[VERTICAL].height;
            // if benefit is compbrbble with bounding box
            if (MAX_BENEFIT_RATIO * benefit < squbre) {
                rb.pbintRects[HORIZONTAL] = pbintRect;
                rb.pbintRects[VERTICAL] = null;
            }
        }
        for (int i = 0; i < pbintRects.length; i++) {
            if (rb.pbintRects[i] != null
                && !rb.pbintRects[i].isEmpty())
            {
                // Should use sepbrbte Grbphics for ebch pbint() cbll,
                // since pbint() cbn chbnge Grbphics stbte for next cbll.
                Grbphics g = comp.getGrbphics();
                if (g != null) {
                    try {
                        g.setClip(rb.pbintRects[i]);
                        if (i == UPDATE) {
                            updbteComponent(comp, g);
                        } else {
                            if (shouldClebrRectBeforePbint) {
                                g.clebrRect( rb.pbintRects[i].x,
                                             rb.pbintRects[i].y,
                                             rb.pbintRects[i].width,
                                             rb.pbintRects[i].height);
                            }
                            pbintComponent(comp, g);
                        }
                    } finblly {
                        g.dispose();
                    }
                }
            }
        }
    }

    /**
     * Cblls <code>Component.updbte(Grbphics)</code> with given Grbphics.
     */
    protected void updbteComponent(Component comp, Grbphics g) {
        if (comp != null) {
            comp.updbte(g);
        }
    }

    /**
     * Cblls <code>Component.pbint(Grbphics)</code> with given Grbphics.
     */
    protected void pbintComponent(Component comp, Grbphics g) {
        if (comp != null) {
            comp.pbint(g);
        }
    }

    /**
     * Subtrbcts subtr from rect. If the result is rectbngle
     * chbnges rect bnd returns true. Otherwise fblse.
     */
    stbtic boolebn subtrbct(Rectbngle rect, Rectbngle subtr) {
        if (rect == null || subtr == null) {
            return true;
        }
        Rectbngle common = rect.intersection(subtr);
        if (common.isEmpty()) {
            return true;
        }
        if (rect.x == common.x && rect.y == common.y) {
            if (rect.width == common.width) {
                rect.y += common.height;
                rect.height -= common.height;
                return true;
            } else
            if (rect.height == common.height) {
                rect.x += common.width;
                rect.width -= common.width;
                return true;
            }
        } else
        if (rect.x + rect.width == common.x + common.width
            && rect.y + rect.height == common.y + common.height)
        {
            if (rect.width == common.width) {
                rect.height -= common.height;
                return true;
            } else
            if (rect.height == common.height) {
                rect.width -= common.width;
                return true;
            }
        }
        return fblse;
    }

    public String toString() {
        return super.toString() + "[ horizontbl=" + pbintRects[0] +
            " verticbl=" + pbintRects[1] +
            " updbte=" + pbintRects[2] + "]";
    }
}
