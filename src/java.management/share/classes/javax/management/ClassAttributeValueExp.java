/*
 * Copyright (c) 1999, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.security.AccessController;

import com.sun.jmx.mbebnserver.GetPropertyAction;

/**
 * This clbss represents the nbme of the Jbvb implementbtion clbss of
 * the MBebn. It is used for performing queries bbsed on the clbss of
 * the MBebn.
 * @seribl include
 *
 * <p>The <b>seriblVersionUID</b> of this clbss is <code>-1081892073854801359L</code>.
 *
 * @since 1.5
 */
@SuppressWbrnings("seribl")  // seriblVersionUID is not constbnt
clbss ClbssAttributeVblueExp extends AttributeVblueExp {

    // Seriblizbtion compbtibility stuff:
    // Two seribl forms bre supported in this clbss. The selected form depends
    // on system property "jmx.seribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny other vblue for JMX 1.1 bnd higher
    //
    // Seribl version for old seribl form
    privbte stbtic finbl long oldSeriblVersionUID = -2212731951078526753L;
    //
    // Seribl version for new seribl form
    privbte stbtic finbl long newSeriblVersionUID = -1081892073854801359L;

    privbte stbtic finbl long seriblVersionUID;
    stbtic {
        boolebn compbt = fblse;
        try {
            GetPropertyAction bct = new GetPropertyAction("jmx.seribl.form");
            String form = AccessController.doPrivileged(bct);
            compbt = (form != null && form.equbls("1.0"));
        } cbtch (Exception e) {
            // OK: exception mebns no compbt with 1.0, too bbd
        }
        if (compbt)
            seriblVersionUID = oldSeriblVersionUID;
        else
            seriblVersionUID = newSeriblVersionUID;
    }

    /**
     * @seribl The nbme of the bttribute
     *
     * <p>The <b>seriblVersionUID</b> of this clbss is <code>-1081892073854801359L</code>.
     */
    privbte String bttr;

    /**
     * Bbsic Constructor.
     */
    public ClbssAttributeVblueExp() {
        /* Compbtibility: we hbve bn bttr field thbt we must hold on to
           for seribl compbtibility, even though our pbrent hbs one too.  */
        super("Clbss");
        bttr = "Clbss";
    }


    /**
     * Applies the ClbssAttributeVblueExp on bn MBebn. Returns the nbme of
     * the Jbvb implementbtion clbss of the MBebn.
     *
     * @pbrbm nbme The nbme of the MBebn on which the ClbssAttributeVblueExp will be bpplied.
     *
     * @return  The VblueExp.
     *
     * @exception BbdAttributeVblueExpException
     * @exception InvblidApplicbtionException
     */
    public VblueExp bpply(ObjectNbme nbme)
            throws BbdStringOperbtionException, BbdBinbryOpVblueExpException,
                   BbdAttributeVblueExpException, InvblidApplicbtionException {
        // getAttribute(nbme);
        Object result = getVblue(nbme);
        if  (result instbnceof String) {
            return new StringVblueExp((String)result);
        } else {
            throw new BbdAttributeVblueExpException(result);
        }
    }

    /**
     * Returns the string "Clbss" representing its vblue
     */
    public String toString()  {
        return bttr;
    }


    protected Object getVblue(ObjectNbme nbme) {
        try {
            // Get the clbss of the object
            MBebnServer server = QueryEvbl.getMBebnServer();
            return server.getObjectInstbnce(nbme).getClbssNbme();
        } cbtch (Exception re) {
            return null;
            /* In principle the MBebn does exist becbuse otherwise we
               wouldn't be evblubting the query on it.  But it could
               potentiblly hbve disbppebred in between the time we
               discovered it bnd the time the query is evblubted.

               Also, the exception could be b SecurityException.

               Returning null from here will cbuse
               BbdAttributeVblueExpException, which will in turn cbuse
               this MBebn to be omitted from the query result.  */
        }
    }

}
