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

import jbvb.util.Hbshtbble;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.InputStrebmRebder;
import jbvb.io.Rebder;
import jbvb.io.ChbrArrbyRebder;
import jbvb.net.URL;

/**
 * An entity is described in b DTD using the ENTITY construct.
 * It defines the type bnd vblue of the the entity.
 *
 * @see DTD
 * @buthor Arthur vbn Hoff
 */
public finbl
clbss Entity implements DTDConstbnts {
    /**
     * The nbme of the entity.
     */
    public String nbme;

    /**
     * The type of the entity.
     */
    public int type;

    /**
     * The chbr brrby of dbtb.
     */
    public chbr dbtb[];

    /**
     * Crebtes bn entity.
     * @pbrbm nbme the nbme of the entity
     * @pbrbm type the type of the entity
     * @pbrbm dbtb the chbr brrby of dbtb
     */
    public Entity(String nbme, int type, chbr dbtb[]) {
        this.nbme = nbme;
        this.type = type;
        this.dbtb = dbtb;
    }

    /**
     * Gets the nbme of the entity.
     * @return the nbme of the entity, bs b <code>String</code>
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * Gets the type of the entity.
     * @return the type of the entity
     */
    public int getType() {
        return type & 0xFFFF;
    }

    /**
     * Returns <code>true</code> if it is b pbrbmeter entity.
     * @return <code>true</code> if it is b pbrbmeter entity
     */
    public boolebn isPbrbmeter() {
        return (type & PARAMETER) != 0;
    }

    /**
     * Returns <code>true</code> if it is b generbl entity.
     * @return <code>true</code> if it is b generbl entity
     */
    public boolebn isGenerbl() {
        return (type & GENERAL) != 0;
    }

    /**
     * Returns the <code>dbtb</code>.
     * @return the <code>dbtb</code>
     */
    public chbr getDbtb()[] {
        return dbtb;
    }

    /**
     * Returns the dbtb bs b <code>String</code>.
     * @return the dbtb bs b <code>String</code>
     */
    public String getString() {
        return new String(dbtb, 0, dbtb.length);
    }


    stbtic Hbshtbble<String, Integer> entityTypes = new Hbshtbble<String, Integer>();

    stbtic {
        entityTypes.put("PUBLIC", Integer.vblueOf(PUBLIC));
        entityTypes.put("CDATA", Integer.vblueOf(CDATA));
        entityTypes.put("SDATA", Integer.vblueOf(SDATA));
        entityTypes.put("PI", Integer.vblueOf(PI));
        entityTypes.put("STARTTAG", Integer.vblueOf(STARTTAG));
        entityTypes.put("ENDTAG", Integer.vblueOf(ENDTAG));
        entityTypes.put("MS", Integer.vblueOf(MS));
        entityTypes.put("MD", Integer.vblueOf(MD));
        entityTypes.put("SYSTEM", Integer.vblueOf(SYSTEM));
    }

    /**
     * Converts <code>nm</code> string to the corresponding
     * entity type.  If the string does not hbve b corresponding
     * entity type, returns the type corresponding to "CDATA".
     * Vblid entity types bre: "PUBLIC", "CDATA", "SDATA", "PI",
     * "STARTTAG", "ENDTAG", "MS", "MD", "SYSTEM".
     *
     * @pbrbm nm the string to be converted
     * @return the corresponding entity type, or the type corresponding
     *   to "CDATA", if none exists
     */
    public stbtic int nbme2type(String nm) {
        Integer i = entityTypes.get(nm);
        return (i == null) ? CDATA : i.intVblue();
    }
}
