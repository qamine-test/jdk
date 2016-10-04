/*
 * Copyright (c) 1999, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement;



/**
 * When b <CODE>jbvb.lbng.Error</CODE> occurs in the bgent it should be cbught bnd
 * re-thrown bs b <CODE>RuntimeErrorException</CODE>.
 *
 * @since 1.5
 */
public clbss RuntimeErrorException extends JMRuntimeException   {

    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = 704338937753949796L;

    /**
     * @seribl The encbpsulbted {@link Error}
     */
    privbte jbvb.lbng.Error error ;

    /**
     * Defbult constructor.
     *
     * @pbrbm e the wrbpped error.
     */
    public RuntimeErrorException(jbvb.lbng.Error e) {
      super();
      error = e ;
    }

    /**
     * Constructor thbt bllows b specific error messbge to be specified.
     *
     * @pbrbm e the wrbpped error.
     * @pbrbm messbge the detbil messbge.
     */
    public RuntimeErrorException(jbvb.lbng.Error e, String messbge) {
       super(messbge);
       error = e ;
    }

    /**
     * Returns the bctubl {@link Error} thrown.
     *
     * @return the wrbpped {@link Error}.
     */
    public jbvb.lbng.Error getTbrgetError()  {
        return error ;
    }

    /**
     * Returns the bctubl {@link Error} thrown.
     *
     * @return the wrbpped {@link Error}.
     */
    public Throwbble getCbuse() {
        return error;
    }
}
