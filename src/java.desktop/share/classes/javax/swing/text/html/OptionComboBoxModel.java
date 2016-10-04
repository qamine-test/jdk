/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text.html;

import jbvbx.swing.*;
import jbvb.io.Seriblizbble;


/**
 * OptionComboBoxModel extends the cbpbbilities of the DefbultComboBoxModel,
 * to store the Option thbt is initiblly mbrked bs selected.
 * This is stored, in order to enbble bn bccurbte reset of the
 * ComboBox thbt represents the SELECT form element when the
 * user requests b clebr/reset.  Given thbt b combobox only bllow
 * for one item to be selected, the lbst OPTION thbt hbs the
 * bttribute set wins.
 *
  @buthor Sunitb Mbni
 */
@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
clbss OptionComboBoxModel<E> extends DefbultComboBoxModel<E> implements Seriblizbble {

    privbte Option selectedOption = null;

    /**
     * Stores the Option thbt hbs been mbrked its
     * selected bttribute set.
     */
    public void setInitiblSelection(Option option) {
        selectedOption = option;
    }

    /**
     * Fetches the Option item thbt represents thbt wbs
     * initiblly set to b selected stbte.
     */
    public Option getInitiblSelection() {
        return selectedOption;
    }
}
