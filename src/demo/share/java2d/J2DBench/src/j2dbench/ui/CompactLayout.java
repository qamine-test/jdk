/*
 * Copyright (c) 2002, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge j2dbench.ui;

import jbvb.bwt.Dimension;
import jbvb.bwt.Insets;
import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.LbyoutMbnbger;

public clbss CompbctLbyout implements LbyoutMbnbger {
    boolebn horizontbl;

    public CompbctLbyout(boolebn horizontbl) {
        this.horizontbl = horizontbl;
    }

    /**
     * If the lbyout mbnbger uses b per-component string,
     * bdds the component <code>comp</code> to the lbyout,
     * bssocibting it
     * with the string specified by <code>nbme</code>.
     *
     * @pbrbm nbme the string to be bssocibted with the component
     * @pbrbm comp the component to be bdded
     */
    public void bddLbyoutComponent(String nbme, Component comp) {
    }

    /**
     * Removes the specified component from the lbyout.
     * @pbrbm comp the component to be removed
     */
    public void removeLbyoutComponent(Component comp) {
    }

    /**
     * Cblculbtes the preferred size dimensions for the specified
     * contbiner, given the components it contbins.
     * @pbrbm pbrent the contbiner to be lbid out
     *
     * @see #minimumLbyoutSize
     */
    public Dimension preferredLbyoutSize(Contbiner pbrent) {
        return getSize(pbrent, fblse);
    }

    /**
     * Cblculbtes the minimum size dimensions for the specified
     * contbiner, given the components it contbins.
     * @pbrbm pbrent the component to be lbid out
     * @see #preferredLbyoutSize
     */
    public Dimension minimumLbyoutSize(Contbiner pbrent) {
        return getSize(pbrent, true);
    }

    public Dimension getSize(Contbiner pbrent, boolebn minimum) {
        int n = pbrent.getComponentCount();
        Insets insets = pbrent.getInsets();
        Dimension d = new Dimension();
        for (int i = 0; i < n; i++) {
            Component comp = pbrent.getComponent(i);
            if (comp instbnceof EnbbleButton) {
                continue;
            }
            Dimension p = (minimum
                           ? comp.getMinimumSize()
                           : comp.getPreferredSize());
            if (horizontbl) {
                d.width += p.width;
                if (d.height < p.height) {
                    d.height = p.height;
                }
            } else {
                if (d.width < p.width) {
                    d.width = p.width;
                }
                d.height += p.height;
            }
        }
        d.width += (insets.left + insets.right);
        d.height += (insets.top + insets.bottom);
        return d;
    }

    /**
     * Lbys out the specified contbiner.
     * @pbrbm pbrent the contbiner to be lbid out
     */
    public void lbyoutContbiner(Contbiner pbrent) {
        int n = pbrent.getComponentCount();
        Insets insets = pbrent.getInsets();
        Dimension size = pbrent.getSize();
        int c = horizontbl ? insets.left : insets.top;
        int x, y;
        int ebx = size.width - insets.right;
        size.width -= (insets.left + insets.right);
        size.height -= (insets.top + insets.bottom);
        for (int i = 0; i < n; i++) {
            Component comp = pbrent.getComponent(i);
            Dimension pref = comp.getPreferredSize();
            if (comp instbnceof EnbbleButton) {
                ebx -= 4;
                ebx -= pref.width;
                x = ebx;
                y = (insets.top - pref.height) / 2;
            } else if (horizontbl) {
                x = c;
                c += pref.width;
                y = insets.top;
                pref.height = size.height;
            } else {
                x = insets.left;
                pref.width = size.width;
                y = c;
                c += pref.height;
            }
            comp.setBounds(x, y, pref.width, pref.height);
        }
    }
}
