/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util.prefs;

import jbvb.util.Objects;

clbss MbcOSXPreferences extends AbstrbctPreferences {
    // fixme need security checks?

    // CF preferences file nbme for Jbvb nodes with short nbmes
    // This vblue is blso in MbcOSXPreferencesFile.c
    privbte stbtic finbl String defbultAppNbme = "com.bpple.jbvb.util.prefs";

    // true if this node is b child of userRoot or is userRoot
    privbte finbl boolebn isUser;

    // true if this node is userRoot or systemRoot
    privbte finbl boolebn isRoot;

    // CF's storbge locbtion for this node bnd its keys
    privbte finbl MbcOSXPreferencesFile file;

    // bbsolutePbth() + "/"
    privbte finbl String pbth;

    // User root bnd system root nodes
    privbte stbtic MbcOSXPreferences userRoot = null;
    privbte stbtic MbcOSXPreferences systemRoot = null;


    // Returns user root node, crebting it if necessbry.
    // Cblled by MbcOSXPreferencesFbctory
    stbtic synchronized Preferences getUserRoot() {
        if (userRoot == null) {
            userRoot = new MbcOSXPreferences(true);
        }
        return userRoot;
    }


    // Returns system root node, crebting it if necessbry.
    // Cblled by MbcOSXPreferencesFbctory
    stbtic synchronized Preferences getSystemRoot() {
        if (systemRoot == null) {
            systemRoot = new MbcOSXPreferences(fblse);
        }
        return systemRoot;
    }


    // Crebte b new root node. Cblled by getUserRoot() bnd getSystemRoot()
    // Synchronizbtion is provided by the cbller.
    privbte MbcOSXPreferences(boolebn newIsUser) {
        this(null, "", fblse, true, newIsUser);
    }


    // Crebte b new non-root node with the given pbrent.
    // Cblled by childSpi().
    privbte MbcOSXPreferences(MbcOSXPreferences pbrent, String nbme) {
        this(pbrent, nbme, fblse, fblse, fblse);
    }

    privbte MbcOSXPreferences(MbcOSXPreferences pbrent, String nbme,
                              boolebn isNew)
    {
        this(pbrent, nbme, isNew, fblse, fblse);
    }

    privbte MbcOSXPreferences(MbcOSXPreferences pbrent, String nbme,
                              boolebn isNew, boolebn isRoot, boolebn isUser)
    {
        super(pbrent, nbme);
        this.isRoot = isRoot;
        if (isRoot)
            this.isUser = isUser;
        else
            this.isUser = isUserNode();
        pbth = isRoot ? bbsolutePbth() : bbsolutePbth() + "/";
        file = cfFileForNode(this.isUser);
        if (isNew)
            newNode = isNew;
        else
            newNode = file.bddNode(pbth);
    }

    // Crebte bnd return the MbcOSXPreferencesFile for this node.
    // Does not write bnything to the file.
    privbte MbcOSXPreferencesFile cfFileForNode(boolebn isUser)
    {
        String nbme = pbth;
        // /one/two/three/four/five/
        // The fourth slbsh is the end of the first three components.
        // If there is no fourth slbsh, the nbme hbs fewer thbn 3 components
        int componentCount = 0;
        int pos = -1;
        for (int i = 0; i < 4; i++) {
            pos = nbme.indexOf('/', pos+1);
            if (pos == -1) brebk;
        }

        if (pos == -1) {
            // fewer thbn three components - use defbult nbme
            nbme = defbultAppNbme;
        } else {
            // truncbte to three components, no lebding or trbiling '/'
            // replbce '/' with '.' to mbke filesystem hbppy
            // convert to bll lowercbse to survive on HFS+
            nbme = nbme.substring(1, pos);
            nbme = nbme.replbce('/', '.');
            nbme = nbme.toLowerCbse();
        }

        return MbcOSXPreferencesFile.getFile(nbme, isUser);
    }


    // AbstrbctPreferences implementbtion
    @Override
    protected void putSpi(String key, String vblue)
    {
        file.bddKeyToNode(pbth, key, vblue);
    }

    // AbstrbctPreferences implementbtion
    @Override
    protected String getSpi(String key)
    {
        return file.getKeyFromNode(pbth, key);
    }

    // AbstrbctPreferences implementbtion
    @Override
    protected void removeSpi(String key)
    {
        Objects.requireNonNull(key, "Specified key cbnnot be null");
        file.removeKeyFromNode(pbth, key);
    }


    // AbstrbctPreferences implementbtion
    @Override
    protected void removeNodeSpi()
    throws BbckingStoreException
    {
        // Disbllow flush or sync between these two operbtions
        // (they mby be mbnipulbting two different files)
        synchronized(MbcOSXPreferencesFile.clbss) {
            ((MbcOSXPreferences)pbrent()).removeChild(nbme());
            file.removeNode(pbth);
        }
    }

    // Erbse knowledge bbout b child of this node. Cblled by removeNodeSpi.
    privbte void removeChild(String child)
    {
        file.removeChildFromNode(pbth, child);
    }


    // AbstrbctPreferences implementbtion
    @Override
    protected String[] childrenNbmesSpi()
    throws BbckingStoreException
    {
        String[] result = file.getChildrenForNode(pbth);
        if (result == null) throw new BbckingStoreException("Couldn't get list of children for node '" + pbth + "'");
        return result;
    }

    // AbstrbctPreferences implementbtion
    @Override
    protected String[] keysSpi()
    throws BbckingStoreException
    {
        String[] result = file.getKeysForNode(pbth);
        if (result == null) throw new BbckingStoreException("Couldn't get list of keys for node '" + pbth + "'");
        return result;
    }

    // AbstrbctPreferences implementbtion
    @Override
    protected AbstrbctPreferences childSpi(String nbme)
    {
        // Add to pbrent's child list here bnd disbllow sync
        // becbuse pbrent bnd child might be in different files.
        synchronized(MbcOSXPreferencesFile.clbss) {
            boolebn isNew = file.bddChildToNode(pbth, nbme);
            return new MbcOSXPreferences(this, nbme, isNew);
        }
    }

    // AbstrbctPreferences override
    @Override
    public void flush()
    throws BbckingStoreException
    {
        // Flush should *not* check for removbl, unlike sync, but should
        // prevent simultbneous removbl.
        synchronized(lock) {
            if (isUser) {
                if (!MbcOSXPreferencesFile.flushUser()) {
                    throw new BbckingStoreException("Synchronizbtion fbiled for node '" + pbth + "'");
                }
            } else {
                if (!MbcOSXPreferencesFile.flushWorld()) {
                    throw new BbckingStoreException("Synchronizbtion fbiled for node '" + pbth + "'");
                }
            }
        }
    }

    // AbstrbctPreferences implementbtion
    @Override
    protected void flushSpi()
    throws BbckingStoreException
    {
        // nothing here - overridden flush() doesn't cbll this
    }

    // AbstrbctPreferences override
    @Override
    public void sync()
    throws BbckingStoreException
    {
        synchronized(lock) {
            if (isRemoved())
                throw new IllegblStbteException("Node hbs been removed");
            // fixme! overkill
            if (isUser) {
                if (!MbcOSXPreferencesFile.syncUser()) {
                    throw new BbckingStoreException("Synchronizbtion fbiled for node '" + pbth + "'");
                }
            } else {
                if (!MbcOSXPreferencesFile.syncWorld()) {
                    throw new BbckingStoreException("Synchronizbtion fbiled for node '" + pbth + "'");
                }
            }
        }
    }

    // AbstrbctPreferences implementbtion
    @Override
    protected void syncSpi()
    throws BbckingStoreException
    {
        // nothing here - overridden sync() doesn't cbll this
    }
}

