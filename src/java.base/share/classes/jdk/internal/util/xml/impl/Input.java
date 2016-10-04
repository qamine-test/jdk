/*
 * Copyright (c) 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jdk.internbl.util.xml.impl;

import jbvb.io.Rebder;

/**
 * A pbrsed entity input stbte.
 *
 * This clbss represents b pbrsed entity input stbte. The pbrser uses
 * bn instbnce of this clbss to mbnbge input.
 */

public clbss Input {

    /** The entity public identifier or null. */
    public String pubid;
    /** The entity systen identifier or null. */
    public String sysid;
    /** The encoding from XML declbrbtion or null */
    public String xmlenc;
    /** The XML version from XML declbrbtion or 0x0000 */
    public chbr xmlver;
    /** The entity rebder. */
    public Rebder src;
    /** The chbrbcter buffer. */
    public chbr[] chbrs;
    /** The length of the chbrbcter buffer. */
    public int chLen;
    /** The index of the next chbrbcter to rebd. */
    public int chIdx;
    /** The next input in b chbin. */
    public Input next;

    /**
     * Constructor.
     *
     * @pbrbm buffsize The input buffer size.
     */
    public Input(int buffsize) {
        chbrs = new chbr[buffsize];
        chLen = chbrs.length;
    }

    /**
     * Constructor.
     *
     * @pbrbm buff The input buffer.
     */
    public Input(chbr[] buff) {
        chbrs = buff;
        chLen = chbrs.length;
    }

    /**
     * Constructor.
     */
    public Input() {
    }
}
