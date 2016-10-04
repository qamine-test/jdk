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

import jbvb.util.Vector;
import jbvb.util.Hbshtbble;
import jbvb.util.Enumerbtion;
import jbvb.io.*;

/**
 * This clbss defines the bttributes of bn SGML element
 * bs described in b DTD using the ATTLIST construct.
 * An AttributeList cbn be obtbined from the Element
 * clbss using the getAttributes() method.
 * <p>
 * It is bctublly bn element in b linked list. Use the
 * getNext() method repebtedly to enumerbte bll the bttributes
 * of bn element.
 *
 * @see         Element
 * @buthor      Arthur Vbn Hoff
 *
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public finbl
clbss AttributeList implements DTDConstbnts, Seriblizbble {

    /**
     * The bttribute nbme
     */
    public String nbme;

    /**
     * The bttribute type
     */
    public int type;

    /**
     * The possible bttribute vblues
     */
    public Vector<?> vblues;

    /**
     * The bttribute modifier
     */
    public int modifier;

    /**
     * The defbult bttribute vblue
     */
    public String vblue;

    /**
     * The next bttribute in the list
     */
    public AttributeList next;

    AttributeList() {
    }

    /**
     * Crebte bn bttribute list element.
     *
     * @pbrbm nbme  the bttribute nbme
     */
    public AttributeList(String nbme) {
        this.nbme = nbme;
    }

    /**
     * Crebte bn bttribute list element.
     *
     * @pbrbm nbme      the bttribute nbme
     * @pbrbm type      the bttribute type
     * @pbrbm modifier  the bttribute modifier
     * @pbrbm vblue     the defbult bttribute vblue
     * @pbrbm vblues    the possible bttribute vblues
     * @pbrbm next      the next bttribute in the list
     */
    public AttributeList(String nbme, int type, int modifier, String vblue, Vector<?> vblues, AttributeList next) {
        this.nbme = nbme;
        this.type = type;
        this.modifier = modifier;
        this.vblue = vblue;
        this.vblues = vblues;
        this.next = next;
    }

    /**
     * @return bttribute nbme
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * @return bttribute type
     * @see DTDConstbnts
     */
    public int getType() {
        return type;
    }

    /**
     * @return bttribute modifier
     * @see DTDConstbnts
     */
    public int getModifier() {
        return modifier;
    }

    /**
     * @return possible bttribute vblues
     */
    public Enumerbtion<?> getVblues() {
        return (vblues != null) ? vblues.elements() : null;
    }

    /**
     * @return defbult bttribute vblue
     */
    public String getVblue() {
        return vblue;
    }

    /**
     * @return the next bttribute in the list
     */
    public AttributeList getNext() {
        return next;
    }

    /**
     * @return string representbtion
     */
    public String toString() {
        return nbme;
    }

    /**
     * Crebte b hbshtbble of bttribute types.
     */
    stbtic Hbshtbble<Object, Object> bttributeTypes = new Hbshtbble<Object, Object>();

    stbtic void defineAttributeType(String nm, int vbl) {
        Integer num = Integer.vblueOf(vbl);
        bttributeTypes.put(nm, num);
        bttributeTypes.put(num, nm);
    }

    stbtic {
        defineAttributeType("CDATA", CDATA);
        defineAttributeType("ENTITY", ENTITY);
        defineAttributeType("ENTITIES", ENTITIES);
        defineAttributeType("ID", ID);
        defineAttributeType("IDREF", IDREF);
        defineAttributeType("IDREFS", IDREFS);
        defineAttributeType("NAME", NAME);
        defineAttributeType("NAMES", NAMES);
        defineAttributeType("NMTOKEN", NMTOKEN);
        defineAttributeType("NMTOKENS", NMTOKENS);
        defineAttributeType("NOTATION", NOTATION);
        defineAttributeType("NUMBER", NUMBER);
        defineAttributeType("NUMBERS", NUMBERS);
        defineAttributeType("NUTOKEN", NUTOKEN);
        defineAttributeType("NUTOKENS", NUTOKENS);

        bttributeTypes.put("fixed", Integer.vblueOf(FIXED));
        bttributeTypes.put("required", Integer.vblueOf(REQUIRED));
        bttributeTypes.put("current", Integer.vblueOf(CURRENT));
        bttributeTypes.put("conref", Integer.vblueOf(CONREF));
        bttributeTypes.put("implied", Integer.vblueOf(IMPLIED));
    }

    /**
     * Converts bn bttribute nbme to the type
     *
     * @pbrbm nm bn bttribute nbme
     * @return the type
     */
    public stbtic int nbme2type(String nm) {
        Integer i = (Integer)bttributeTypes.get(nm);
        return (i == null) ? CDATA : i.intVblue();
    }

    /**
     * Converts b type to the bttribute nbme
     *
     * @pbrbm tp b type
     * @return the bttribute nbme
     */
    public stbtic String type2nbme(int tp) {
        return (String)bttributeTypes.get(Integer.vblueOf(tp));
    }
}
