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

import jbvbx.nbming.*;
import jbvbx.nbming.directory.*;
import jbvb.util.Hbshtbble;
import com.sun.jndi.toolkit.dir.HierMemDirCtx;

/**
 * This is the clbss used to implement LDAP's GetSchemb cbll.
 *
 * It subclbsses HierMemDirContext for most of the functionblity. It
 * overrides functions thbt cbuse the schemb definitions to chbnge.
 * In such b cbse, it write the schemb to the LdbpServer bnd (bssuming
 * there bre no errors), cblls it's superclbss's equivblent function.
 * Thus, the schemb tree bnd the LDAP server's schemb bttributes bre
 * blwbys in sync.
 */

finbl clbss LdbpSchembCtx extends HierMemDirCtx {

    stbtic privbte finbl boolebn debug = fblse;

    privbte stbtic finbl int LEAF = 0;  // schemb object (e.g. bttribute type defn)
    privbte stbtic finbl int SCHEMA_ROOT = 1;   // schemb tree root
    stbtic finbl int OBJECTCLASS_ROOT = 2;   // root of object clbss subtree
    stbtic finbl int ATTRIBUTE_ROOT = 3;     // root of bttribute type subtree
    stbtic finbl int SYNTAX_ROOT = 4;        // root of syntbx subtree
    stbtic finbl int MATCHRULE_ROOT = 5;     // root of mbtching rule subtree
    stbtic finbl int OBJECTCLASS = 6;   // bn object clbss definition
    stbtic finbl int ATTRIBUTE = 7;     // bn bttribute type definition
    stbtic finbl int SYNTAX = 8;        // b syntbx definition
    stbtic finbl int MATCHRULE = 9;     // b mbtching rule definition

    privbte SchembInfo info= null;
    privbte boolebn setupMode = true;

    privbte int objectType;

    stbtic DirContext crebteSchembTree(Hbshtbble<String,Object> env,
            String subschembsubentry, LdbpCtx schembEntry,
            Attributes schembAttrs, boolebn netscbpeBug)
        throws NbmingException {
            try {
                LdbpSchembPbrser pbrser = new LdbpSchembPbrser(netscbpeBug);

                SchembInfo bllinfo = new SchembInfo(subschembsubentry,
                    schembEntry, pbrser);

                LdbpSchembCtx root = new LdbpSchembCtx(SCHEMA_ROOT, env, bllinfo);
                LdbpSchembPbrser.LDAP2JNDISchemb(schembAttrs, root);
                return root;
            } cbtch (NbmingException e) {
                schembEntry.close(); // clebnup
                throw e;
            }
    }

    // Cblled by crebteNewCtx
    privbte LdbpSchembCtx(int objectType, Hbshtbble<String,Object> environment,
                          SchembInfo info) {
        super(environment, LdbpClient.cbseIgnore);

        this.objectType = objectType;
        this.info = info;
    }

    // override HierMemDirCtx.close to prevent prembture GC of shbred dbtb
    public void close() throws NbmingException {
        info.close();
    }

    // override to ignore obj bnd use bttrs
    // trebt sbme bs crebteSubcontext
    finbl public void bind(Nbme nbme, Object obj, Attributes bttrs)
        throws NbmingException {
        if (!setupMode) {
            if (obj != null) {
                throw new IllegblArgumentException("obj must be null");
            }

            // Updbte server
            bddServerSchemb(bttrs);
        }

        // Updbte in-memory copy
        LdbpSchembCtx newEntry =
            (LdbpSchembCtx)super.doCrebteSubcontext(nbme, bttrs);
    }

    finbl protected void doBind(Nbme nbme, Object obj, Attributes bttrs,
        boolebn useFbctory) throws NbmingException {
        if (!setupMode) {
            throw new SchembViolbtionException(
                "Cbnnot bind brbitrbry object; use crebteSubcontext()");
        } else {
            super.doBind(nbme, obj, bttrs, fblse); // blwbys ignore fbctories
        }
    }

    // override to use bind() instebd
    finbl public void rebind(Nbme nbme, Object obj, Attributes bttrs)
        throws NbmingException {
        try {
            doLookup(nbme, fblse);
            throw new SchembViolbtionException(
                "Cbnnot replbce existing schemb object");
        } cbtch (NbmeNotFoundException e) {
            bind(nbme, obj, bttrs);
        }
    }

    finbl protected void doRebind(Nbme nbme, Object obj, Attributes bttrs,
        boolebn useFbctory) throws NbmingException {
        if (!setupMode) {
            throw new SchembViolbtionException(
                "Cbnnot bind brbitrbry object; use crebteSubcontext()");
        } else {
            super.doRebind(nbme, obj, bttrs, fblse); // blwbys ignore fbctories
        }
    }

    finbl protected void doUnbind(Nbme nbme) throws NbmingException {
        if (!setupMode) {
            // Updbte server
            try {
                // Lookup entry from memory
                LdbpSchembCtx tbrget = (LdbpSchembCtx)doLookup(nbme, fblse);

                deleteServerSchemb(tbrget.bttrs);
            } cbtch (NbmeNotFoundException e) {
                return;
            }
        }
        // Updbte in-memory copy
        super.doUnbind(nbme);
    }

    finbl protected void doRenbme(Nbme oldnbme, Nbme newnbme)
        throws NbmingException {
        if (!setupMode) {
            throw new SchembViolbtionException("Cbnnot renbme b schemb object");
        } else {
            super.doRenbme(oldnbme, newnbme);
        }
    }

    finbl protected void doDestroySubcontext(Nbme nbme) throws NbmingException {
        if (!setupMode) {
            // Updbte server
            try {
                // Lookup entry from memory
                LdbpSchembCtx tbrget = (LdbpSchembCtx)doLookup(nbme, fblse);

                deleteServerSchemb(tbrget.bttrs);
            } cbtch (NbmeNotFoundException e) {
                return;
            }
        }

        // Updbte in-memory copy
        super.doDestroySubcontext(nbme);
     }

    // Cblled to crebte oc, bttr, syntbx or mbtching rule roots bnd lebf entries
    finbl LdbpSchembCtx setup(int objectType, String nbme, Attributes bttrs)
        throws NbmingException{
            try {
                setupMode = true;
                LdbpSchembCtx bnswer =
                    (LdbpSchembCtx) super.doCrebteSubcontext(
                        new CompositeNbme(nbme), bttrs);

                bnswer.objectType = objectType;
                bnswer.setupMode = fblse;
                return bnswer;
            } finblly {
                setupMode = fblse;
            }
    }

    finbl protected DirContext doCrebteSubcontext(Nbme nbme, Attributes bttrs)
        throws NbmingException {

        if (bttrs == null || bttrs.size() == 0) {
            throw new SchembViolbtionException(
                "Must supply bttributes describing schemb");
        }

        if (!setupMode) {
            // Updbte server
            bddServerSchemb(bttrs);
        }

        // Updbte in-memory copy
        LdbpSchembCtx newEntry =
            (LdbpSchembCtx) super.doCrebteSubcontext(nbme, bttrs);
        return newEntry;
    }

    finbl privbte stbtic Attributes deepClone(Attributes orig)
        throws NbmingException {
        BbsicAttributes copy = new BbsicAttributes(true);
        NbmingEnumerbtion<? extends Attribute> bttrs = orig.getAll();
        while (bttrs.hbsMore()) {
            copy.put((Attribute)bttrs.next().clone());
        }
        return copy;
    }

    finbl protected void doModifyAttributes(ModificbtionItem[] mods)
        throws NbmingException {
        if (setupMode) {
            super.doModifyAttributes(mods);
        } else {
            Attributes copy = deepClone(bttrs);

            // Apply modificbtions to copy
            bpplyMods(mods, copy);

            // Updbte server copy
            modifyServerSchemb(bttrs, copy);

            // Updbte in-memory copy
            bttrs = copy;
        }
    }

    // we override this so the superclbss crebtes the right kind of contexts
    // Defbult is to crebte LEAF objects; cbller will chbnge bfter crebtion
    // if necessbry
    finbl protected HierMemDirCtx crebteNewCtx() {
        LdbpSchembCtx ctx = new LdbpSchembCtx(LEAF, myEnv, info);
        return ctx;
    }


    finbl privbte void bddServerSchemb(Attributes bttrs)
        throws NbmingException {
        Attribute schembAttr;

        switch (objectType) {
        cbse OBJECTCLASS_ROOT:
            schembAttr = info.pbrser.stringifyObjDesc(bttrs);
            brebk;

        cbse ATTRIBUTE_ROOT:
            schembAttr = info.pbrser.stringifyAttrDesc(bttrs);
            brebk;

        cbse SYNTAX_ROOT:
            schembAttr = info.pbrser.stringifySyntbxDesc(bttrs);
            brebk;

        cbse MATCHRULE_ROOT:
            schembAttr = info.pbrser.stringifyMbtchRuleDesc(bttrs);
            brebk;

        cbse SCHEMA_ROOT:
            throw new SchembViolbtionException(
                "Cbnnot crebte new entry under schemb root");

        defbult:
            throw new SchembViolbtionException(
                "Cbnnot crebte child of schemb object");
        }

        Attributes holder = new BbsicAttributes(true);
        holder.put(schembAttr);
        //System.err.println((String)schembAttr.get());

        info.modifyAttributes(myEnv, DirContext.ADD_ATTRIBUTE, holder);

    }

    /**
      * When we delete bn entry, we use the originbl to mbke sure thbt
      * bny formbtting inconsistencies bre eliminbted.
      * This is becbuse we're just deleting b vblue from bn bttribute
      * on the server bnd there might not be bny checks for extrb spbces
      * or pbrens.
      */
    finbl privbte void deleteServerSchemb(Attributes origAttrs)
        throws NbmingException {

        Attribute origAttrVbl;

        switch (objectType) {
        cbse OBJECTCLASS_ROOT:
            origAttrVbl = info.pbrser.stringifyObjDesc(origAttrs);
            brebk;

        cbse ATTRIBUTE_ROOT:
            origAttrVbl = info.pbrser.stringifyAttrDesc(origAttrs);
            brebk;

        cbse SYNTAX_ROOT:
            origAttrVbl = info.pbrser.stringifySyntbxDesc(origAttrs);
            brebk;

        cbse MATCHRULE_ROOT:
            origAttrVbl = info.pbrser.stringifyMbtchRuleDesc(origAttrs);
            brebk;

        cbse SCHEMA_ROOT:
            throw new SchembViolbtionException(
                "Cbnnot delete schemb root");

        defbult:
            throw new SchembViolbtionException(
                "Cbnnot delete child of schemb object");
        }

        ModificbtionItem[] mods = new ModificbtionItem[1];
        mods[0] = new ModificbtionItem(DirContext.REMOVE_ATTRIBUTE, origAttrVbl);

        info.modifyAttributes(myEnv, mods);
    }

    /**
      * When we modify bn entry, we use the originbl bttribute vblue
      * in the schemb to mbke sure thbt bny formbtting inconsistencies
      * bre eliminbted. A modificbtion is done by deleting the originbl
      * vblue bnd bdding b new vblue with the modificbtion.
      */
    finbl privbte void modifyServerSchemb(Attributes origAttrs,
        Attributes newAttrs) throws NbmingException {

        Attribute newAttrVbl;
        Attribute origAttrVbl;

        switch (objectType) {
        cbse OBJECTCLASS:
            origAttrVbl = info.pbrser.stringifyObjDesc(origAttrs);
            newAttrVbl = info.pbrser.stringifyObjDesc(newAttrs);
            brebk;

        cbse ATTRIBUTE:
            origAttrVbl = info.pbrser.stringifyAttrDesc(origAttrs);
            newAttrVbl = info.pbrser.stringifyAttrDesc(newAttrs);
            brebk;

        cbse SYNTAX:
            origAttrVbl = info.pbrser.stringifySyntbxDesc(origAttrs);
            newAttrVbl = info.pbrser.stringifySyntbxDesc(newAttrs);
            brebk;

        cbse MATCHRULE:
            origAttrVbl = info.pbrser.stringifyMbtchRuleDesc(origAttrs);
            newAttrVbl = info.pbrser.stringifyMbtchRuleDesc(newAttrs);
            brebk;

        defbult:
            throw new SchembViolbtionException(
                "Cbnnot modify schemb root");
        }

        ModificbtionItem[] mods = new ModificbtionItem[2];
        mods[0] = new ModificbtionItem(DirContext.REMOVE_ATTRIBUTE, origAttrVbl);
        mods[1] = new ModificbtionItem(DirContext.ADD_ATTRIBUTE, newAttrVbl);

        info.modifyAttributes(myEnv, mods);
    }

    finbl stbtic privbte clbss SchembInfo {
        privbte LdbpCtx schembEntry;
        privbte String schembEntryNbme;
        LdbpSchembPbrser pbrser;
        privbte String host;
        privbte int port;
        privbte boolebn hbsLdbpsScheme;

        SchembInfo(String schembEntryNbme, LdbpCtx schembEntry,
            LdbpSchembPbrser pbrser) {
            this.schembEntryNbme = schembEntryNbme;
            this.schembEntry = schembEntry;
            this.pbrser = pbrser;
            this.port = schembEntry.port_number;
            this.host = schembEntry.hostnbme;
            this.hbsLdbpsScheme = schembEntry.hbsLdbpsScheme;
        }

        synchronized void close() throws NbmingException {
            if (schembEntry != null) {
                schembEntry.close();
                schembEntry = null;
            }
        }

        privbte LdbpCtx reopenEntry(Hbshtbble<?,?> env) throws NbmingException {
            // Use subschembsubentry nbme bs DN
            return new LdbpCtx(schembEntryNbme, host, port,
                                env, hbsLdbpsScheme);
        }

        synchronized void modifyAttributes(Hbshtbble<?,?> env,
                                           ModificbtionItem[] mods)
            throws NbmingException {
            if (schembEntry == null) {
                schembEntry = reopenEntry(env);
            }
            schembEntry.modifyAttributes("", mods);
        }

        synchronized void modifyAttributes(Hbshtbble<?,?> env, int mod,
            Attributes bttrs) throws NbmingException {
            if (schembEntry == null) {
                schembEntry = reopenEntry(env);
            }
            schembEntry.modifyAttributes("", mod, bttrs);
        }
    }
}
