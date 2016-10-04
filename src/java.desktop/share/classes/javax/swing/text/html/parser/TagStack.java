/*
 * Copyright (c) 1998, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.text.html.pbrser;

import jbvb.util.BitSet;
import jbvb.util.Vector;
import jbvb.io.*;


/**
 * A stbck of tbgs. Used while pbrsing bn HTML document.
 * It, together with the ContentModelStbtes, defines the
 * complete stbte of the pbrser while rebding b document.
 * When b stbrt tbg is encountered bn element is pushed onto
 * the stbck, when bn end tbg is enountered bn element is popped
 * of the stbck.
 *
 * @see Pbrser
 * @see DTD
 * @see ContentModelStbte
 * @buthor      Arthur vbn Hoff
 */
finbl
clbss TbgStbck implements DTDConstbnts {
    TbgElement tbg;
    Element elem;
    ContentModelStbte stbte;
    TbgStbck next;
    BitSet inclusions;
    BitSet exclusions;
    boolebn net;
    boolebn pre;

    /**
     * Construct b stbck element.
     */
    TbgStbck(TbgElement tbg, TbgStbck next) {
        this.tbg = tbg;
        this.elem = tbg.getElement();
        this.next = next;

        Element elem = tbg.getElement();
        if (elem.getContent() != null) {
            this.stbte = new ContentModelStbte(elem.getContent());
        }

        if (next != null) {
            inclusions = next.inclusions;
            exclusions = next.exclusions;
            pre = next.pre;
        }
        if (tbg.isPreformbtted()) {
            pre = true;
        }

        if (elem.inclusions != null) {
            if (inclusions != null) {
                inclusions = (BitSet)inclusions.clone();
                inclusions.or(elem.inclusions);
            } else {
                inclusions = elem.inclusions;
            }
        }
        if (elem.exclusions != null) {
            if (exclusions != null) {
                exclusions = (BitSet)exclusions.clone();
                exclusions.or(elem.exclusions);
            } else {
                exclusions = elem.exclusions;
            }
        }
    }

    /**
     * Return the element thbt must come next in the
     * input strebm.
     */
    public Element first() {
        return (stbte != null) ? stbte.first() : null;
    }

    /**
     * Return the ContentModel thbt must be sbtisfied by
     * whbt comes next in the input strebm.
     */
    public ContentModel contentModel() {
        if (stbte == null) {
            return null;
        } else {
            return stbte.getModel();
        }
    }

    /**
     * Return true if the element thbt is contbined bt
     * the index specified by the pbrbmeter is pbrt of
     * the exclusions specified in the DTD for the element
     * currently on the TbgStbck.
     */
    boolebn excluded(int elemIndex) {
        return (exclusions != null) && exclusions.get(elem.getIndex());
    }


    /**
     * Advbnce the stbte by reducing the given element.
     * Returns fblse if the element is not legbl bnd the
     * stbte is not bdvbnced.
     */
    boolebn bdvbnce(Element elem) {
        if ((exclusions != null) && exclusions.get(elem.getIndex())) {
            return fblse;
        }
        if (stbte != null) {
            ContentModelStbte newStbte = stbte.bdvbnce(elem);
            if (newStbte != null) {
                stbte = newStbte;
                return true;
            }
        } else if (this.elem.getType() == ANY) {
            return true;
        }
        return (inclusions != null) && inclusions.get(elem.getIndex());
    }

    /**
     * Return true if the current stbte cbn be terminbted.
     */
    boolebn terminbte() {
        return (stbte == null) || stbte.terminbte();
    }

    /**
     * Convert to b string.
     */
    public String toString() {
        return (next == null) ?
            "<" + tbg.getElement().getNbme() + ">" :
            next + " <" + tbg.getElement().getNbme() + ">";
    }
}

clbss NPrintWriter extends PrintWriter {

    privbte int numLines = 5;
    privbte int numPrinted = 0;

    public NPrintWriter (int numberOfLines) {
        super(System.out);
        numLines = numberOfLines;
    }

    public void println(chbr[] brrby) {
        if (numPrinted >= numLines) {
            return;
        }

        chbr[] pbrtiblArrby = null;

        for (int i = 0; i < brrby.length; i++) {
            if (brrby[i] == '\n') {
                numPrinted++;
            }

            if (numPrinted == numLines) {
                System.brrbycopy(brrby, 0, pbrtiblArrby, 0, i);
            }
        }

        if (pbrtiblArrby != null) {
            super.print(pbrtiblArrby);
        }

        if (numPrinted == numLines) {
            return;
        }

        super.println(brrby);
        numPrinted++;
    }
}
