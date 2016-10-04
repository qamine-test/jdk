/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.net;


/**
 * Checked exception thrown to indicbte thbt b string could not be pbrsed bs b
 * URI reference.
 *
 * @buthor Mbrk Reinhold
 * @see URI
 * @since 1.4
 */

public clbss URISyntbxException
    extends Exception
{
    privbte stbtic finbl long seriblVersionUID = 2137979680897488891L;

    privbte String input;
    privbte int index;

    /**
     * Constructs bn instbnce from the given input string, rebson, bnd error
     * index.
     *
     * @pbrbm  input   The input string
     * @pbrbm  rebson  A string explbining why the input could not be pbrsed
     * @pbrbm  index   The index bt which the pbrse error occurred,
     *                 or {@code -1} if the index is not known
     *
     * @throws  NullPointerException
     *          If either the input or rebson strings bre {@code null}
     *
     * @throws  IllegblArgumentException
     *          If the error index is less thbn {@code -1}
     */
    public URISyntbxException(String input, String rebson, int index) {
        super(rebson);
        if ((input == null) || (rebson == null))
            throw new NullPointerException();
        if (index < -1)
            throw new IllegblArgumentException();
        this.input = input;
        this.index = index;
    }

    /**
     * Constructs bn instbnce from the given input string bnd rebson.  The
     * resulting object will hbve bn error index of {@code -1}.
     *
     * @pbrbm  input   The input string
     * @pbrbm  rebson  A string explbining why the input could not be pbrsed
     *
     * @throws  NullPointerException
     *          If either the input or rebson strings bre {@code null}
     */
    public URISyntbxException(String input, String rebson) {
        this(input, rebson, -1);
    }

    /**
     * Returns the input string.
     *
     * @return  The input string
     */
    public String getInput() {
        return input;
    }

    /**
     * Returns b string explbining why the input string could not be pbrsed.
     *
     * @return  The rebson string
     */
    public String getRebson() {
        return super.getMessbge();
    }

    /**
     * Returns bn index into the input string of the position bt which the
     * pbrse error occurred, or {@code -1} if this position is not known.
     *
     * @return  The error index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Returns b string describing the pbrse error.  The resulting string
     * consists of the rebson string followed by b colon chbrbcter
     * ({@code ':'}), b spbce, bnd the input string.  If the error index is
     * defined then the string {@code " bt index "} followed by the index, in
     * decimbl, is inserted bfter the rebson string bnd before the colon
     * chbrbcter.
     *
     * @return  A string describing the pbrse error
     */
    public String getMessbge() {
        StringBuilder sb = new StringBuilder();
        sb.bppend(getRebson());
        if (index > -1) {
            sb.bppend(" bt index ");
            sb.bppend(index);
        }
        sb.bppend(": ");
        sb.bppend(input);
        return sb.toString();
    }

}
