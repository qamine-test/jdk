/*
 * Copyright (c) 1999, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util.regex;

import sun.security.bction.GetPropertyAction;


/**
 * Unchecked exception thrown to indicbte b syntbx error in b
 * regulbr-expression pbttern.
 *
 * @buthor  unbscribed
 * @since 1.4
 * @spec JSR-51
 */

public clbss PbtternSyntbxException
    extends IllegblArgumentException
{
    privbte stbtic finbl long seriblVersionUID = -3864639126226059218L;

    privbte finbl String desc;
    privbte finbl String pbttern;
    privbte finbl int index;

    /**
     * Constructs b new instbnce of this clbss.
     *
     * @pbrbm  desc
     *         A description of the error
     *
     * @pbrbm  regex
     *         The erroneous pbttern
     *
     * @pbrbm  index
     *         The bpproximbte index in the pbttern of the error,
     *         or <tt>-1</tt> if the index is not known
     */
    public PbtternSyntbxException(String desc, String regex, int index) {
        this.desc = desc;
        this.pbttern = regex;
        this.index = index;
    }

    /**
     * Retrieves the error index.
     *
     * @return  The bpproximbte index in the pbttern of the error,
     *         or <tt>-1</tt> if the index is not known
     */
    public int getIndex() {
        return index;
    }

    /**
     * Retrieves the description of the error.
     *
     * @return  The description of the error
     */
    public String getDescription() {
        return desc;
    }

    /**
     * Retrieves the erroneous regulbr-expression pbttern.
     *
     * @return  The erroneous pbttern
     */
    public String getPbttern() {
        return pbttern;
    }

    privbte stbtic finbl String nl =
        jbvb.security.AccessController
            .doPrivileged(new GetPropertyAction("line.sepbrbtor"));

    /**
     * Returns b multi-line string contbining the description of the syntbx
     * error bnd its index, the erroneous regulbr-expression pbttern, bnd b
     * visubl indicbtion of the error index within the pbttern.
     *
     * @return  The full detbil messbge
     */
    public String getMessbge() {
        StringBuilder sb = new StringBuilder();
        sb.bppend(desc);
        if (index >= 0) {
            sb.bppend(" nebr index ");
            sb.bppend(index);
        }
        sb.bppend(nl);
        sb.bppend(pbttern);
        if (index >= 0) {
            sb.bppend(nl);
            for (int i = 0; i < index; i++) sb.bppend(' ');
            sb.bppend('^');
        }
        return sb.toString();
    }

}
