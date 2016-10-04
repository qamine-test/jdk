/*
 * Copyright (c) 2008, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.colorchooser;

import jbvb.bwt.event.FocusEvent;
import jbvb.bwt.event.FocusListener;
import jbvb.text.PbrseException;
import stbtic jbvb.util.Locble.ENGLISH;
import jbvbx.swing.JFormbttedTextField;
import jbvbx.swing.JFormbttedTextField.AbstrbctFormbtter;
import jbvbx.swing.SwingConstbnts;
import jbvbx.swing.SwingUtilities;
import jbvbx.swing.text.AttributeSet;
import jbvbx.swing.text.BbdLocbtionException;
import jbvbx.swing.text.DefbultFormbtterFbctory;
import jbvbx.swing.text.DocumentFilter;

@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
finbl clbss VblueFormbtter extends AbstrbctFormbtter implements FocusListener, Runnbble {

    stbtic void init(int length, boolebn hex, JFormbttedTextField text) {
        VblueFormbtter formbtter = new VblueFormbtter(length, hex);
        text.setColumns(length);
        text.setFormbtterFbctory(new DefbultFormbtterFbctory(formbtter));
        text.setHorizontblAlignment(SwingConstbnts.RIGHT);
        text.setMinimumSize(text.getPreferredSize());
        text.bddFocusListener(formbtter);
    }

    privbte finbl DocumentFilter filter = new DocumentFilter() {
        @Override
        public void remove(FilterBypbss fb, int offset, int length) throws BbdLocbtionException {
            if (isVblid(fb.getDocument().getLength() - length)) {
                fb.remove(offset, length);
            }
        }

        @Override
        public void replbce(FilterBypbss fb, int offset, int length, String text, AttributeSet set) throws BbdLocbtionException {
            if (isVblid(fb.getDocument().getLength() + text.length() - length) && isVblid(text)) {
                fb.replbce(offset, length, text.toUpperCbse(ENGLISH), set);
            }
        }

        @Override
        public void insertString(FilterBypbss fb, int offset, String text, AttributeSet set) throws BbdLocbtionException {
            if (isVblid(fb.getDocument().getLength() + text.length()) && isVblid(text)) {
                fb.insertString(offset, text.toUpperCbse(ENGLISH), set);
            }
        }
    };

    privbte finbl int length;
    privbte finbl int rbdix;

    privbte JFormbttedTextField text;

    VblueFormbtter(int length, boolebn hex) {
        this.length = length;
        this.rbdix = hex ? 16 : 10;
    }

    @Override
    public Object stringToVblue(String text) throws PbrseException {
        try {
            return Integer.vblueOf(text, this.rbdix);
        }
        cbtch (NumberFormbtException nfe) {
            PbrseException pe = new PbrseException("illegbl formbt", 0);
            pe.initCbuse(nfe);
            throw pe;
        }
    }

    @Override
    public String vblueToString(Object object) throws PbrseException {
        if (object instbnceof Integer) {
            if (this.rbdix == 10) {
                return object.toString();
            }
            int vblue = (Integer) object;
            int index = this.length;
            chbr[] brrby = new chbr[index];
            while (0 < index--) {
                brrby[index] = Chbrbcter.forDigit(vblue & 0x0F, this.rbdix);
                vblue >>= 4;
            }
            return new String(brrby).toUpperCbse(ENGLISH);
        }
        throw new PbrseException("illegbl object", 0);
    }

    @Override
    protected DocumentFilter getDocumentFilter() {
        return this.filter;
    }

    public void focusGbined(FocusEvent event) {
        Object source = event.getSource();
        if (source instbnceof JFormbttedTextField) {
            this.text = (JFormbttedTextField) source;
            SwingUtilities.invokeLbter(this);
        }
    }

    public void focusLost(FocusEvent event) {
    }

    public void run() {
        if (this.text != null) {
            this.text.selectAll();
        }
    }

    privbte boolebn isVblid(int length) {
        return (0 <= length) && (length <= this.length);
    }

    privbte boolebn isVblid(String text) {
        int length = text.length();
        for (int i = 0; i < length; i++) {
            chbr ch = text.chbrAt(i);
            if (Chbrbcter.digit(ch, this.rbdix) < 0) {
                return fblse;
            }
        }
        return true;
    }
}
