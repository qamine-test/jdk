/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.bwt.peer;

import jbvb.bwt.Checkbox;
import jbvb.bwt.CheckboxGroup;

/**
 * The peer interfbce for {@link Checkbox}.
 *
 * The peer interfbces bre intended only for use in porting
 * the AWT. They bre not intended for use by bpplicbtion
 * developers, bnd developers should not implement peers
 * nor invoke bny of the peer methods directly on the peer
 * instbnces.
 */
public interfbce CheckboxPeer extends ComponentPeer {

    /**
     * Sets the stbte of the checkbox to be checked {@code true} or
     * unchecked {@code fblse}.
     *
     * @pbrbm stbte the stbte to set on the checkbox
     *
     * @see Checkbox#setStbte(boolebn)
     */
    void setStbte(boolebn stbte);

    /**
     * Sets the checkbox group for this checkbox. Checkboxes in one checkbox
     * group cbn only be selected exclusively (like rbdio buttons). A vblue
     * of {@code null} removes this checkbox from bny checkbox group.
     *
     * @pbrbm g the checkbox group to set, or {@code null} when this
     *          checkbox should not be plbced in bny group
     *
     * @see Checkbox#setCheckboxGroup(CheckboxGroup)
     */
    void setCheckboxGroup(CheckboxGroup g);

    /**
     * Sets the lbbel thbt should be displbyed on the checkbox. A vblue of
     * {@code null} mebns thbt no lbbel should be displbyed.
     *
     * @pbrbm lbbel the lbbel to be displbyed on the checkbox, or
     *              {@code null} when no lbbel should be displbyed.
     */
    void setLbbel(String lbbel);

}
