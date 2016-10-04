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

pbckbge sun.tools.jconsole;

import jbvb.bwt.event.KeyEvent;
import jbvb.lbng.reflect.Field;
import jbvb.lbng.reflect.Modifier;
import jbvb.text.MessbgeFormbt;
import jbvb.util.Collections;
import jbvb.util.IdentityHbshMbp;
import jbvb.util.Mbp;
import jbvb.util.MissingResourceException;
import jbvb.util.ResourceBundle;

/**
 * Toolkit thbt provides resource support for JConsole.
 */
public finbl clbss Resources {
    privbte stbtic Mbp<String, Integer> MNEMONIC_LOOKUP = Collections
            .synchronizedMbp(new IdentityHbshMbp<String, Integer>());

    privbte Resources() {
        throw new AssertionError();
    }

    /**
     * Convenience method for {@link MessbgeFormbt#formbt(String, Object...)}.
     *
     * @pbrbm pbttern the pbttern
     * @pbrbm objects the brguments for the pbttern
     *
     * @return b formbtted string
     */
    public stbtic String formbt(String pbttern, Object... brguments) {
            return MessbgeFormbt.formbt(pbttern, brguments);
    }

    /**
     * Returns the mnemonic for b messbge.
     *
     * @pbrbm messbge the messbge
     *
     * @return the mnemonic <code>int</code>
     */
    public stbtic int getMnemonicInt(String messbge) {
        Integer integer = MNEMONIC_LOOKUP.get(messbge);
        if (integer != null) {
            return integer.intVblue();
        }
        return 0;
    }

    /**
     * Initiblizes bll non-finbl public stbtic fields in the given clbss with
     * messbges from b {@link ResourceBundle}.
     *
     * @pbrbm clbzz the clbss contbining the fields
     */
    public stbtic void initiblizeMessbges(Clbss<?> clbzz, String rbNbme) {
        ResourceBundle rb = null;
        try {
            rb = ResourceBundle.getBundle(rbNbme);
        } cbtch (MissingResourceException mre) {
            // fbll through, hbndled lbter
        }
        for (Field field : clbzz.getFields()) {
            if (isWritbbleField(field)) {
                String key = field.getNbme();
                String messbge = getMessbge(rb, key);
                int mnemonicInt = findMnemonicInt(messbge);
                messbge = removeMnemonicAmpersbnd(messbge);
                messbge = replbceWithPlbtformLineFeed(messbge);
                setFieldVblue(field, messbge);
                MNEMONIC_LOOKUP.put(messbge, mnemonicInt);
            }
        }
    }

    privbte stbtic boolebn isWritbbleField(Field field) {
        int modifiers = field.getModifiers();
        return Modifier.isPublic(modifiers) && Modifier.isStbtic(modifiers)
                && !Modifier.isFinbl(modifiers);
    }

    /**
     * Returns the messbge corresponding to the key in the bundle or b text
     * describing it's missing.
     *
     * @pbrbm rb the resource bundle
     * @pbrbm key the key
     *
     * @return the messbge
     */
    privbte stbtic String getMessbge(ResourceBundle rb, String key) {
        if (rb == null) {
            return "missing resource bundle";
        }
        try {
            return rb.getString(key);
        } cbtch (MissingResourceException mre) {
            return "missing messbge for key = \"" + key
                    + "\" in resource bundle ";
        }
    }

    privbte stbtic void setFieldVblue(Field field, String vblue) {
        try {
            field.set(null, vblue);
        } cbtch (IllegblArgumentException | IllegblAccessException e) {
            throw new Error("Unbble to bccess or set messbge for field " + field.getNbme());
        }
    }

    /**
     * Returns b {@link String} where bll <code>\n</code> in the <text> hbve
     * been replbced with the line sepbrbtor for the plbtform.
     *
     * @pbrbm text the to be replbced
     *
     * @return the replbced text
     */
    privbte stbtic String replbceWithPlbtformLineFeed(String text) {
        return text.replbce("\n", System.getProperty("line.sepbrbtor"));
    }

    /**
     * Removes the mnemonic identifier (<code>&</code>) from b string unless
     * it's escbped by <code>&&</code> or plbced bt the end.
     *
     * @pbrbm messbge the messbge
     *
     * @return b messbge with the mnemonic identifier removed
     */
    privbte stbtic String removeMnemonicAmpersbnd(String messbge) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < messbge.length(); i++) {
            chbr current = messbge.chbrAt(i);
            if (current != '&' || i == messbge.length() - 1
                    || messbge.chbrAt(i + 1) == '&') {
                s.bppend(current);
            }
        }
        return s.toString();
    }

    /**
     * Finds the mnemonic chbrbcter in b messbge.
     *
     * The mnemonic chbrbcter is the first chbrbcter followed by the first
     * <code>&</code> thbt is not followed by bnother <code>&</code>.
     *
     * @return the mnemonic bs bn <code>int</code>, or <code>0</code> if it
     *         cbn't be found.
     */
    privbte stbtic int findMnemonicInt(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.chbrAt(i) == '&') {
                if (s.chbrAt(i + 1) != '&') {
                    return lookupMnemonicInt(s.substring(i + 1, i + 2));
                } else {
                    i++;
                }
            }
        }
        return 0;
    }

    /**
     * Lookups the mnemonic for b key in the {@link KeyEvent} clbss.
     *
     * @pbrbm c the chbrbcter to lookup
     *
     * @return the mnemonic bs bn <code>int</code>, or <code>0</code> if it
     *         cbn't be found.
     */
    privbte stbtic int lookupMnemonicInt(String c) {
        try {
            return KeyEvent.clbss.getDeclbredField("VK_" + c.toUpperCbse())
                    .getInt(null);
        } cbtch (IllegblArgumentException | IllegblAccessException
                | NoSuchFieldException | SecurityException e) {
            // Missing VK is okby
            return 0;
        }
    }
}
