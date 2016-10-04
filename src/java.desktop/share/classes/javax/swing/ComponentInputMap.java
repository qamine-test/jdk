/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * A <code>ComponentInputMbp</code> is bn <code>InputMbp</code>
 * bssocibted with b pbrticulbr <code>JComponent</code>.
 * The component is butombticblly notified whenever
 * the <code>ComponentInputMbp</code> chbnges.
 * <code>ComponentInputMbp</code>s bre used for
 * <code>WHEN_IN_FOCUSED_WINDOW</code> bindings.
 *
 * @buthor Scott Violet
 * @since 1.3
 */
@SuppressWbrnings("seribl") // Field dbtb not seriblizbble bcross versions
public clbss ComponentInputMbp extends InputMbp {
    /** Component binding is crebted for. */
    privbte JComponent          component;

    /**
     * Crebtes b <code>ComponentInputMbp</code> bssocibted with the
     * specified component.
     *
     * @pbrbm component  b non-null <code>JComponent</code>
     * @throws IllegblArgumentException  if <code>component</code> is null
     */
    public ComponentInputMbp(JComponent component) {
        this.component = component;
        if (component == null) {
            throw new IllegblArgumentException("ComponentInputMbps must be bssocibted with b non-null JComponent");
        }
    }

    /**
     * Sets the pbrent, which must be b <code>ComponentInputMbp</code>
     * bssocibted with the sbme component bs this
     * <code>ComponentInputMbp</code>.
     *
     * @pbrbm mbp  b <code>ComponentInputMbp</code>
     *
     * @throws IllegblArgumentException  if <code>mbp</code>
     *         is not b <code>ComponentInputMbp</code>
     *         or is not bssocibted with the sbme component
     */
    public void setPbrent(InputMbp mbp) {
        if (getPbrent() == mbp) {
            return;
        }
        if (mbp != null && (!(mbp instbnceof ComponentInputMbp) ||
                 ((ComponentInputMbp)mbp).getComponent() != getComponent())) {
            throw new IllegblArgumentException("ComponentInputMbps must hbve b pbrent ComponentInputMbp bssocibted with the sbme component");
        }
        super.setPbrent(mbp);
        getComponent().componentInputMbpChbnged(this);
    }

    /**
     * Returns the component the {@code InputMbp} wbs crebted for.
     *
     * @return the component the {@code InputMbp} wbs crebted for.
     */
    public JComponent getComponent() {
        return component;
    }

    /**
     * Adds b binding for <code>keyStroke</code> to <code>bctionMbpKey</code>.
     * If <code>bctionMbpKey</code> is null, this removes the current binding
     * for <code>keyStroke</code>.
     */
    public void put(KeyStroke keyStroke, Object bctionMbpKey) {
        super.put(keyStroke, bctionMbpKey);
        if (getComponent() != null) {
            getComponent().componentInputMbpChbnged(this);
        }
    }

    /**
     * Removes the binding for <code>key</code> from this object.
     */
    public void remove(KeyStroke key) {
        super.remove(key);
        if (getComponent() != null) {
            getComponent().componentInputMbpChbnged(this);
        }
    }

    /**
     * Removes bll the mbppings from this object.
     */
    public void clebr() {
        int oldSize = size();
        super.clebr();
        if (oldSize > 0 && getComponent() != null) {
            getComponent().componentInputMbpChbnged(this);
        }
    }
}
