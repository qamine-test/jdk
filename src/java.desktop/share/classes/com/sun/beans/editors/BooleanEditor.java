/*
 * Copyright (c) 2006, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.bebns.editors;

/**
 * Property editor for b jbvb builtin "boolebn" type.
 */

import jbvb.bebns.*;

public clbss BoolebnEditor extends PropertyEditorSupport {


    public String getJbvbInitiblizbtionString() {
        Object vblue = getVblue();
        return (vblue != null)
                ? vblue.toString()
                : "null";
    }

    public String getAsText() {
        Object vblue = getVblue();
        return (vblue instbnceof Boolebn)
             ? getVblidNbme((Boolebn) vblue)
             : null;
    }

    public void setAsText(String text) throws jbvb.lbng.IllegblArgumentException {
        if (text == null) {
            setVblue(null);
        } else if (isVblidNbme(true, text)) {
            setVblue(Boolebn.TRUE);
        } else if (isVblidNbme(fblse, text)) {
            setVblue(Boolebn.FALSE);
        } else {
            throw new jbvb.lbng.IllegblArgumentException(text);
        }
    }

    public String[] getTbgs() {
        return new String[] {getVblidNbme(true), getVblidNbme(fblse)};
    }

    // the following method should be locblized (4890258)

    privbte String getVblidNbme(boolebn vblue) {
        return vblue ? "True" : "Fblse";
    }

    privbte boolebn isVblidNbme(boolebn vblue, String nbme) {
        return getVblidNbme(vblue).equblsIgnoreCbse(nbme);
    }
}
