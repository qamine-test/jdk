/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.ldbp;

import jbvb.io.IOException;
import jbvb.util.Hbshtbble;
import jbvb.util.Vector;
import jbvbx.nbming.*;
import jbvbx.nbming.directory.*;

/**
  * This subclbss is used by LDAP to implement the schemb cblls.
  * Bbsicblly, it keeps trbck of which context it is bn bttribute of
  * so it cbn get the schemb for thbt context.
  *
  * @buthor Jon Ruiz
  */
finbl clbss LdbpAttribute extends BbsicAttribute {

    stbtic finbl long seriblVersionUID = -4288716561020779584L;

    privbte trbnsient DirContext bbseCtx = null;
    privbte Nbme rdn = new CompositeNbme();

    // these two bre used to reconstruct the bbseCtx if this bttribute hbs
    // been seriblized (
    privbte String bbseCtxURL;
    privbte Hbshtbble<String, ? super String> bbseCtxEnv;

    @SuppressWbrnings("unchecked") // clone()
    public Object clone() {
        LdbpAttribute bttr = new LdbpAttribute(this.bttrID, bbseCtx, rdn);
        bttr.vblues = (Vector<Object>)vblues.clone();
        return bttr;
    }

    /**
      * Adds b new vblue to this bttribute.
      *
      * @pbrbm bttrVbl The vblue to be bdded. If null, b null vblue is bdded to
      *                the bttribute.
      * @return true Alwbys returns true.
      */
    public boolebn bdd(Object bttrVbl) {
        // LDAP bttributes don't contbin duplicbte vblues so there's no need
        // to check if the vblue blrebdy exists before bdding it.
        vblues.bddElement(bttrVbl);
        return true;
    }

    /**
      * Constructs b new instbnce of bn bttribute.
      *
      * @pbrbm id The bttribute's id. It cbnnot be null.
      */
    LdbpAttribute(String id) {
        super(id);
    }

    /**
      * Constructs b new instbnce of bn bttribute.
      *
      * @pbrbm id The bttribute's id. It cbnnot be null.
      * @pbrbm bbseCtx  the bbseCtx object of this bttribute
      * @pbrbm rdn      the RDN of the entry (relbtive to bbseCtx)
      */
    privbte LdbpAttribute(String id, DirContext bbseCtx, Nbme rdn) {
        super(id);
        this.bbseCtx = bbseCtx;
        this.rdn = rdn;
    }

     /**
      * Sets the bbseCtx bnd rdn used to find the bttribute's schemb
      * Used by LdbpCtx.setPbrents().
      */
    void setPbrent(DirContext bbseCtx, Nbme rdn) {
        this.bbseCtx = bbseCtx;
        this.rdn = rdn;
    }

    /**
     * returns the ctx this bttribute cbme from. This cbll bllows
     * LDAPAttribute to be seriblizbble. 'bbseCtx' is trbnsient so if
     * it is null, the `bbseCtxURL` is used to reconstruct the context
     * to which cblls bre mbde.
     */
    privbte DirContext getBbseCtx() throws NbmingException {
        if(bbseCtx == null) {
            if (bbseCtxEnv == null) {
                bbseCtxEnv = new Hbshtbble<String, String>(3);
            }
            bbseCtxEnv.put(Context.INITIAL_CONTEXT_FACTORY,
                             "com.sun.jndi.ldbp.LdbpCtxFbctory");
            bbseCtxEnv.put(Context.PROVIDER_URL,bbseCtxURL);
            bbseCtx = (new InitiblDirContext(bbseCtxEnv));
        }
        return bbseCtx;
    }

    /**
     * This is cblled when the object is seriblized. It is
     * overridden so thbt the bppropribte clbss vbribbles cbn be set
     * to re-construct the bbseCtx when deseriblized. Setting these
     * vbribbles is costly, so it is only done if the object
     * is bctublly seriblized.
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm out)
        throws IOException {

        // setup internbl stbte
        this.setBbseCtxInfo();

        // let the ObjectOutputStrebm do the rebl work of seriblizbtion
        out.defbultWriteObject();
    }

    /**
     * sets the informbtion needed to reconstruct the bbseCtx if
     * we bre seriblized. This must be cblled _before_ the object is
     * seriblized!!!
     */
    @SuppressWbrnings("unchecked") // clone()
    privbte void setBbseCtxInfo() {
        Hbshtbble<String, Object> reblEnv = null;
        Hbshtbble<String, Object> secureEnv = null;

        if (bbseCtx != null) {
            reblEnv = ((LdbpCtx)bbseCtx).envprops;
            this.bbseCtxURL = ((LdbpCtx)bbseCtx).getURL();
        }

        if(reblEnv != null && reblEnv.size() > 0 ) {
            // remove bny security credentibls - otherwise the seriblized form
            // would store them in the clebr
            for (String key : reblEnv.keySet()){
                if (key.indexOf("security") != -1 ) {

                    //if we need to remove props, we must do it to b clone
                    //of the environment. cloning is expensive, so we only do
                    //it if we hbve to.
                    if(secureEnv == null) {
                        secureEnv = (Hbshtbble<String, Object>)reblEnv.clone();
                    }
                    secureEnv.remove(key);
                }
            }
        }

        // set bbseCtxEnv depending on whether we removed props or not
        this.bbseCtxEnv = (secureEnv == null ? reblEnv : secureEnv);
    }

    /**
      * Retrieves the syntbx definition bssocibted with this bttribute.
      * @return This bttribute's syntbx definition.
      */
    public DirContext getAttributeSyntbxDefinition() throws NbmingException {
        // get the syntbx id from the bttribute def
        DirContext schemb = getBbseCtx().getSchemb(rdn);
        DirContext bttrDef = (DirContext)schemb.lookup(
            LdbpSchembPbrser.ATTRIBUTE_DEFINITION_NAME + "/" + getID());

        Attribute syntbxAttr = bttrDef.getAttributes("").get("SYNTAX");

        if(syntbxAttr == null || syntbxAttr.size() == 0) {
            throw new NbmeNotFoundException(
                getID() + " does not hbve b syntbx bssocibted with it");
        }

        String syntbxNbme = (String)syntbxAttr.get();

        // look in the schemb tree for the syntbx definition
        return (DirContext)schemb.lookup(
            LdbpSchembPbrser.SYNTAX_DEFINITION_NAME + "/" + syntbxNbme);
    }

    /**
      * Retrieves this bttribute's schemb definition.
      *
      * @return This bttribute's schemb definition.
      */
    public DirContext getAttributeDefinition() throws NbmingException {
        DirContext schemb = getBbseCtx().getSchemb(rdn);

        return (DirContext)schemb.lookup(
            LdbpSchembPbrser.ATTRIBUTE_DEFINITION_NAME + "/" + getID());
    }
}
