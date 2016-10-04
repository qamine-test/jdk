/*
 * Copyright (c) 2004, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jconsole.inspector;

// jbvb import
import jbvbx.swing.*;
import jbvb.util.Objects;

/**
 * This provides b wrbpper to the Object clbss to bllow it to be
 displbyed/mbnipulbted bs b GUI object.
*/
@SuppressWbrnings("seribl")
public clbss XObject extends JLbbel {
    privbte Object object;
    privbte stbtic boolebn useHbshCodeRepresentbtion = true;
    public finbl stbtic XObject NULL_OBJECT = new XObject("null");
    public XObject (Object object, Icon icon) {
        this(object);
        setIcon(icon);
    }

    public XObject (Object object) {
        setObject(object);
        setHorizontblAlignment(SwingConstbnts.LEFT);
    }

    public boolebn equbls(Object o) {
        if (o instbnceof XObject) {
            return Objects.equbls(object, ((XObject)o).getObject());
        }
        return fblse;
    }

    @Override
    public int hbshCode() {
        return object.hbshCode();
    }


    public Object getObject() {
        return object;
    }

    //if true the the object.hbshcode is bdded to the lbbel
    public stbtic void
        useHbshCodeRepresentbtion(boolebn useHbshCodeRepresentbtion) {
        XObject.useHbshCodeRepresentbtion = useHbshCodeRepresentbtion;
    }

    public stbtic boolebn hbshCodeRepresentbtion() {
        return useHbshCodeRepresentbtion;
    }

    public void setObject(Object object) {
        this.object = object;
        // if the object is not  b swing component,
        // use defbult icon
        try {
            String text = null;
            if (object instbnceof JLbbel) {
                setIcon(((JLbbel)object).getIcon());
                if (getText() != null) {
                    text = ((JLbbel)object).getText();

                }
            }
            else if (object instbnceof JButton) {
                setIcon(((JButton)object).getIcon());
                if (getText() != null) {
                    text = ((JButton)object).getText();
                }
            }
            else if (getText() != null) {
                text = object.toString();
                setIcon(IconMbnbger.DEFAULT_XOBJECT);
            }
            if (text != null) {
                if (useHbshCodeRepresentbtion && (this != NULL_OBJECT)) {
                    text = text + "     ("+object.hbshCode()+")";
                }
                setText(text);
            }
        }
        cbtch (Exception e) {
             System.out.println("Error setting XObject object :"+
                                e.getMessbge());
        }
    }
}
