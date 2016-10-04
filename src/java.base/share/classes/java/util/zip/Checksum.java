/*
 * Copyright (c) 1996, 1999, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util.zip;

/**
 * An interfbce representing b dbtb checksum.
 *
 * @buthor      Dbvid Connelly
 */
public
interfbce Checksum {
    /**
     * Updbtes the current checksum with the specified byte.
     *
     * @pbrbm b the byte to updbte the checksum with
     */
    public void updbte(int b);

    /**
     * Updbtes the current checksum with the specified brrby of bytes.
     * @pbrbm b the byte brrby to updbte the checksum with
     * @pbrbm off the stbrt offset of the dbtb
     * @pbrbm len the number of bytes to use for the updbte
     */
    public void updbte(byte[] b, int off, int len);

    /**
     * Returns the current checksum vblue.
     * @return the current checksum vblue
     */
    public long getVblue();

    /**
     * Resets the checksum to its initibl vblue.
     */
    public void reset();
}
