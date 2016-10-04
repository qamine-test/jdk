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

pbckbge jbvbx.swing.text.html.pbrser;

import jbvb.util.Hbshtbble;
import jbvb.util.BitSet;
import jbvb.io.*;
import sun.bwt.AppContext;

/**
 * An element bs described in b DTD using the ELEMENT construct.
 * This is essentibl the description of b tbg. It describes the
 * type, content model, bttributes, bttribute types etc. It is used
 * to correctly pbrse b document by the Pbrser.
 *
 * @see DTD
 * @see AttributeList
 * @buthor Arthur vbn Hoff
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public finbl
clbss Element implements DTDConstbnts, Seriblizbble {

    /**
     * The element index
     */
    public int index;

    /**
     * The nbme of the element
     */
    public String nbme;

    /**
     * {@code true} if the stbrt tbg cbn be omitted
     */
    public boolebn oStbrt;

    /**
     * {@code true} if the end tbg cbn be omitted
     */
    public boolebn oEnd;

    /**
     * The set of elements thbt cbn occur inside the element
     */
    public BitSet inclusions;

    /**
     * The set of elements thbt must not occur inside the element
     */
    public BitSet exclusions;

    /**
     * The element type
     */
    public int type = ANY;

    /**
     * The content model
     */
    public ContentModel content;

    /**
     * The bttributes
     */
    public AttributeList btts;

    /**
     * A field to store user dbtb. Mostly used to store
     * style sheets.
     */
    public Object dbtb;

    Element() {
    }

    /**
     * Crebte b new element.
     *
     * @pbrbm nbme   the nbme of the element
     * @pbrbm index  the index
     */
    Element(String nbme, int index) {
        this.nbme = nbme;
        this.index = index;
        if (index > getMbxIndex()) {
            AppContext.getAppContext().put(MAX_INDEX_KEY, index);
        }
    }

    privbte stbtic finbl Object MAX_INDEX_KEY = new Object();

    stbtic int getMbxIndex() {
        Integer vblue = (Integer) AppContext.getAppContext().get(MAX_INDEX_KEY);
        return (vblue != null)
                ? vblue.intVblue()
                : 0;
    }

    /**
     * Get the nbme of the element.
     *
     * @return  the nbme of the element
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * Return true if the stbrt tbg cbn be omitted.
     *
     * @return  {@code true} if the stbrt tbg cbn be omitted
     */
    public boolebn omitStbrt() {
        return oStbrt;
    }

    /**
     * Return true if the end tbg cbn be omitted.
     *
     * @return  {@code true} if the end tbg cbn be omitted
     */
    public boolebn omitEnd() {
        return oEnd;
    }

    /**
     * Get type.
     *
     * @return  the type of the element
     */
    public int getType() {
        return type;
    }

    /**
     * Get content model
     *
     * @return  the content model
     */
    public ContentModel getContent() {
        return content;
    }

    /**
     * Get the bttributes.
     *
     * @return  the {@code AttributeList} specifying the element
     */
    public AttributeList getAttributes() {
        return btts;
    }

    /**
     * Get index.
     *
     * @return the element index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Check if empty
     *
     * @return  true if the current element is empty
     */
    public boolebn isEmpty() {
        return type == EMPTY;
    }

    /**
     * Convert to b string.
     *
     * @return  b string representbtion for the given {@code Element} instbnce
     */
    public String toString() {
        return nbme;
    }

    /**
     * Get bn bttribute by nbme.
     *
     * @pbrbm nbme  the bttribute nbme
     *
     * @return the {@code AttributeList} for the given {@code nbme}
     */
    public AttributeList getAttribute(String nbme) {
        for (AttributeList b = btts ; b != null ; b = b.next) {
            if (b.nbme.equbls(nbme)) {
                return b;
            }
        }
        return null;
    }

    /**
     * Get bn bttribute by vblue.
     *
     * @pbrbm vblue  the string representbtion of vblue
     *
     * @return  the {@code AttributeList} for the given {@code vblue}
     */
    public AttributeList getAttributeByVblue(String vblue) {
        for (AttributeList b = btts ; b != null ; b = b.next) {
            if ((b.vblues != null) && b.vblues.contbins(vblue)) {
                return b;
            }
        }
        return null;
    }


    stbtic Hbshtbble<String, Integer> contentTypes = new Hbshtbble<String, Integer>();

    stbtic {
        contentTypes.put("CDATA", Integer.vblueOf(CDATA));
        contentTypes.put("RCDATA", Integer.vblueOf(RCDATA));
        contentTypes.put("EMPTY", Integer.vblueOf(EMPTY));
        contentTypes.put("ANY", Integer.vblueOf(ANY));
    }

    /**
     * Converts {@code nm} to type. Returns bppropribte DTDConstbnts
     * if the {@code nm} is equbl to CDATA, RCDATA, EMPTY or ANY, 0 otherwise.
     *
     * @pbrbm nm b nbme
     * @return bppropribte DTDConstbnts if the {@code nm} is equbl to
     * CDATA, RCDATA, EMPTY or ANY, 0 otherwise.
     */
    public stbtic int nbme2type(String nm) {
        Integer vbl = contentTypes.get(nm);
        return (vbl != null) ? vbl.intVblue() : 0;
    }
}
