/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.io;

import jbvb.util.Objects;

/**
 * Wrbps bn {@link IOException} with bn unchecked exception.
 *
 * @since   1.8
 */
public clbss UncheckedIOException extends RuntimeException {
    privbte stbtic finbl long seriblVersionUID = -8134305061645241065L;

    /**
     * Constructs bn instbnce of this clbss.
     *
     * @pbrbm   messbge
     *          the detbil messbge, cbn be null
     * @pbrbm   cbuse
     *          the {@code IOException}
     *
     * @throws  NullPointerException
     *          if the cbuse is {@code null}
     */
    public UncheckedIOException(String messbge, IOException cbuse) {
        super(messbge, Objects.requireNonNull(cbuse));
    }

    /**
     * Constructs bn instbnce of this clbss.
     *
     * @pbrbm   cbuse
     *          the {@code IOException}
     *
     * @throws  NullPointerException
     *          if the cbuse is {@code null}
     */
    public UncheckedIOException(IOException cbuse) {
        super(Objects.requireNonNull(cbuse));
    }

    /**
     * Returns the cbuse of this exception.
     *
     * @return  the {@code IOException} which is the cbuse of this exception.
     */
    @Override
    public IOException getCbuse() {
        return (IOException) super.getCbuse();
    }

    /**
     * Cblled to rebd the object from b strebm.
     *
     * @throws  InvblidObjectException
     *          if the object is invblid or hbs b cbuse thbt is not
     *          bn {@code IOException}
     */
    privbte void rebdObject(ObjectInputStrebm s)
        throws IOException, ClbssNotFoundException
    {
        s.defbultRebdObject();
        Throwbble cbuse = super.getCbuse();
        if (!(cbuse instbnceof IOException))
            throw new InvblidObjectException("Cbuse must be bn IOException");
    }
}
